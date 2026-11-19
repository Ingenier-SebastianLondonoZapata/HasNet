package clases;

import Modelos.Ingresos.modeloEstadoCliente;
import Modelos.Ingresos.modeloPlanFacturacion;
import Modelos.Ingresos.modeloVentaPaquete;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SQLIngresos {

    public static final String INSTALACION_SERVIDOR = "INSTALACION_SERVIDOR";
    public static final String INSTALACION_TERMINAL = "INSTALACION_TERMINAL";
    public static final String MENSUALIDAD_SERVIDOR = "MENSUALIDAD_SERVIDOR";
    public static final String MENSUALIDAD_TERMINAL = "MENSUALIDAD_TERMINAL";

    public static final String MOV_LICENCIA = "LICENCIA";
    public static final String MOV_INSTALACION_TERMINALES = "INSTALACION_TERMINALES";
    public static final String MOV_MENSUALIDAD_SERVIDOR = "MENSUALIDAD_SERVIDOR";
    public static final String MOV_MENSUALIDAD_TERMINALES = "MENSUALIDAD_TERMINALES";
    public static final String MOV_TERMINALES_PENDIENTES = "TERMINALES_PENDIENTES";
    public static final String MOV_PLAN_MENSUAL = "PLAN_MENSUAL";
    public static final String MOV_PAQUETE = "PAQUETE";

    public static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter FORMATO_PERIODO = DateTimeFormatter.ofPattern("yyyy-MM");

    private final MySql_connection mysql = MySql_connection.getMySql_connection();

    private interface Operacion<T> {

        T ejecutar(Connection conexion) throws SQLException;
    }

    private Connection conexion() throws SQLException {
        Connection conexion = mysql.getConnection();
        if (conexion == null) {
            throw new SQLException("No hay conexión con la base de datos de pagos");
        }
        return conexion;
    }

    private <T> T enTransaccion(Operacion<T> operacion) throws SQLException {
        Connection conexion = conexion();
        boolean autoCommitPrevio = conexion.getAutoCommit();
        conexion.setAutoCommit(false);
        try {
            T resultado = operacion.ejecutar(conexion);
            conexion.commit();
            return resultado;
        } catch (SQLException | RuntimeException ex) {
            conexion.rollback();
            throw ex;
        } finally {
            conexion.setAutoCommit(autoCommitPrevio);
        }
    }

    public static LocalDate hoy() {
        return LocalDate.now();
    }

    public static final String PARAMETRO_INFORMACION_LEGAL = "INFORMACION_LEGAL";

    public String obtenerParametroGlobal(String clave) throws SQLException {
        try (PreparedStatement consulta = conexion().prepareStatement("SELECT valor FROM bdParametrosGlobales WHERE clave = ?")) {
            consulta.setString(1, clave);
            try (ResultSet resultado = consulta.executeQuery()) {
                return resultado.next() ? resultado.getString(1) : null;
            }
        }
    }

    public void guardarParametroGlobal(String clave, String valor) throws SQLException {
        try (PreparedStatement sentencia = conexion().prepareStatement(
                "INSERT INTO bdParametrosGlobales (clave, valor) VALUES (?, ?) ON DUPLICATE KEY UPDATE valor = VALUES(valor)")) {
            sentencia.setString(1, clave);
            sentencia.setString(2, valor);
            sentencia.executeUpdate();
        }
    }

    public List<Object[]> listarTarifas() throws SQLException {
        List<Object[]> tarifas = new ArrayList<>();
        try (PreparedStatement consulta = conexion().prepareStatement(
                "SELECT concepto, descripcion, valor FROM bdTarifas ORDER BY FIELD(concepto, ?, ?, ?, ?), concepto")) {
            consulta.setString(1, INSTALACION_SERVIDOR);
            consulta.setString(2, INSTALACION_TERMINAL);
            consulta.setString(3, MENSUALIDAD_SERVIDOR);
            consulta.setString(4, MENSUALIDAD_TERMINAL);
            try (ResultSet resultado = consulta.executeQuery()) {
                while (resultado.next()) {
                    tarifas.add(new Object[]{resultado.getString(1), resultado.getString(2), resultado.getBigDecimal(3)});
                }
            }
        }
        return tarifas;
    }

    public BigDecimal obtenerTarifa(String concepto) throws SQLException {
        return tarifa(conexion(), concepto);
    }

    public void actualizarTarifa(String concepto, BigDecimal valor) throws SQLException {
        if (valor == null || valor.signum() < 0) {
            throw new IllegalStateException("El valor de la tarifa no puede ser negativo");
        }
        try (PreparedStatement actualizacion = conexion().prepareStatement("UPDATE bdTarifas SET valor = ? WHERE concepto = ?")) {
            actualizacion.setBigDecimal(1, valor);
            actualizacion.setString(2, concepto);
            actualizacion.executeUpdate();
        }
    }

    private BigDecimal tarifa(Connection conexion, String concepto) throws SQLException {
        try (PreparedStatement consulta = conexion.prepareStatement("SELECT valor FROM bdTarifas WHERE concepto = ?")) {
            consulta.setString(1, concepto);
            try (ResultSet resultado = consulta.executeQuery()) {
                if (!resultado.next()) {
                    throw new IllegalStateException("No está configurada la tarifa " + concepto + ". Revise la pestaña de tarifas.");
                }
                return resultado.getBigDecimal(1);
            }
        }
    }

    public List<modeloPlanFacturacion> listarPlanes(boolean soloActivos) throws SQLException {
        List<modeloPlanFacturacion> planes = new ArrayList<>();
        String sql = "SELECT idPlan, nombre, tipo, numeroDocumentos, precio, activo FROM bdPlanesFacturacion"
                + (soloActivos ? " WHERE activo = 1" : "") + " ORDER BY tipo, numeroDocumentos";
        try (PreparedStatement consulta = conexion().prepareStatement(sql);
                ResultSet resultado = consulta.executeQuery()) {
            while (resultado.next()) {
                planes.add(leerPlan(resultado));
            }
        }
        return planes;
    }

    public modeloPlanFacturacion obtenerPlan(int idPlan) throws SQLException {
        return plan(conexion(), idPlan);
    }

    private modeloPlanFacturacion plan(Connection conexion, int idPlan) throws SQLException {
        try (PreparedStatement consulta = conexion.prepareStatement(
                "SELECT idPlan, nombre, tipo, numeroDocumentos, precio, activo FROM bdPlanesFacturacion WHERE idPlan = ?")) {
            consulta.setInt(1, idPlan);
            try (ResultSet resultado = consulta.executeQuery()) {
                return resultado.next() ? leerPlan(resultado) : null;
            }
        }
    }

    private modeloPlanFacturacion leerPlan(ResultSet resultado) throws SQLException {
        return new modeloPlanFacturacion(resultado.getInt("idPlan"), resultado.getString("nombre"), resultado.getString("tipo"),
                resultado.getInt("numeroDocumentos"), resultado.getBigDecimal("precio"), resultado.getBoolean("activo"));
    }

    public void guardarPlan(modeloPlanFacturacion plan) throws SQLException {
        if (plan.getNombre() == null || plan.getNombre().trim().isEmpty()) {
            throw new IllegalStateException("Ingrese el nombre del paquete");
        }
        if (plan.getNumeroDocumentos() <= 0) {
            throw new IllegalStateException("El número de documentos debe ser mayor a cero");
        }
        if (plan.getPrecio() == null || plan.getPrecio().signum() < 0) {
            throw new IllegalStateException("El precio no puede ser negativo");
        }
        String sql = plan.getIdPlan() > 0
                ? "UPDATE bdPlanesFacturacion SET nombre = ?, tipo = ?, numeroDocumentos = ?, precio = ?, activo = ? WHERE idPlan = ?"
                : "INSERT INTO bdPlanesFacturacion (nombre, tipo, numeroDocumentos, precio, activo) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement sentencia = conexion().prepareStatement(sql)) {
            sentencia.setString(1, plan.getNombre().trim());
            sentencia.setString(2, plan.getTipo());
            sentencia.setInt(3, plan.getNumeroDocumentos());
            sentencia.setBigDecimal(4, plan.getPrecio());
            sentencia.setBoolean(5, plan.isActivo());
            if (plan.getIdPlan() > 0) {
                sentencia.setInt(6, plan.getIdPlan());
            }
            sentencia.executeUpdate();
        }
    }

    public modeloEstadoCliente obtenerEstadoCliente(String idCliente) throws SQLException {
        return estadoCliente(conexion(), idCliente, false);
    }

    public static final int MAXIMO_MESES_ADELANTADOS = 12;

    public void reiniciarMesesDocumentos() throws SQLException {
        reiniciarMesesDocumentos(conexion(), null);
    }

    private void reiniciarMesesDocumentos(Connection conexion, String idCliente) throws SQLException {
        try (PreparedStatement actualizacion = conexion.prepareStatement(
                "UPDATE bdClientes SET numeroFEMensuales = documentosPorMesFE, mesFEActual = TIMESTAMPDIFF(MONTH, inicioCoberturaFE, CURDATE()) "
                + "WHERE documentosPorMesFE > 0 AND inicioCoberturaFE IS NOT NULL AND CURDATE() >= inicioCoberturaFE "
                + "AND CURDATE() < vigenciaFEMensuales AND TIMESTAMPDIFF(MONTH, inicioCoberturaFE, CURDATE()) > mesFEActual"
                + (idCliente == null ? "" : " AND idCliente = ?"))) {
            if (idCliente != null) {
                actualizacion.setString(1, idCliente);
            }
            actualizacion.executeUpdate();
        }
    }

    public static int mesesRestantesCobertura(LocalDate fechaLimite, LocalDate hoy) {
        if (fechaLimite == null || fechaLimite.isBefore(hoy)) {
            return 1;
        }
        return (int) java.time.temporal.ChronoUnit.MONTHS.between(hoy, fechaLimite) + 1;
    }

    private modeloEstadoCliente estadoCliente(Connection conexion, String idCliente, boolean bloquear) throws SQLException {
        reiniciarMesesDocumentos(conexion, idCliente);
        try (PreparedStatement consulta = conexion.prepareStatement(
                "SELECT idCliente, nit, nombre, numeroTerminales, terminalesPagadas, numeroFEDisponibles, numeroFEMensuales, "
                + "vigenciaFEMensuales, idPlanMensual, idPlanMensualSiguiente, diasDespuesGabelaBloqueo FROM bdClientes WHERE idCliente = ?"
                + (bloquear ? " FOR UPDATE" : ""))) {
            consulta.setString(1, idCliente);
            try (ResultSet resultado = consulta.executeQuery()) {
                if (!resultado.next()) {
                    throw new IllegalStateException("El cliente " + idCliente + " no existe");
                }
                modeloEstadoCliente estado = new modeloEstadoCliente();
                estado.setIdCliente(resultado.getString("idCliente"));
                estado.setNit(resultado.getString("nit"));
                estado.setNombre(resultado.getString("nombre"));
                estado.setNumeroTerminales(resultado.getInt("numeroTerminales"));
                estado.setTerminalesPagadas(resultado.getInt("terminalesPagadas"));
                estado.setNumeroFEDisponibles(resultado.getInt("numeroFEDisponibles"));
                estado.setNumeroFEMensuales(resultado.getInt("numeroFEMensuales"));
                Date vigencia = resultado.getDate("vigenciaFEMensuales");
                estado.setVigenciaFEMensuales(vigencia == null ? null : vigencia.toLocalDate());
                estado.setIdPlanMensual(enteroNulo(resultado, "idPlanMensual"));
                estado.setIdPlanMensualSiguiente(enteroNulo(resultado, "idPlanMensualSiguiente"));
                estado.setDiasGabela(resultado.getInt("diasDespuesGabelaBloqueo"));
                return estado;
            }
        }
    }

    private static Integer enteroNulo(ResultSet resultado, String columna) throws SQLException {
        int valor = resultado.getInt(columna);
        return resultado.wasNull() ? null : valor;
    }

    public void registrarVentaLicencia(String idCliente) throws SQLException {
        enTransaccion(conexion -> {
            registrarMovimiento(conexion, idCliente, MOV_LICENCIA, "Licencia del servidor (incluye una terminal)", 1,
                    tarifa(conexion, INSTALACION_SERVIDOR), null);
            return null;
        });
    }

    public int actualizarTerminalesContratadas(String idCliente, int nuevasTerminales, boolean cobrarInstalacion) throws SQLException {
        if (nuevasTerminales < 0) {
            throw new IllegalStateException("El número de terminales no puede ser negativo");
        }
        return enTransaccion(conexion -> {
            modeloEstadoCliente estado = estadoCliente(conexion, idCliente, true);
            int diferencia = nuevasTerminales - estado.getNumeroTerminales();
            if (diferencia == 0) {
                return 0;
            }
            int pagadas = diferencia > 0 ? estado.getTerminalesPagadas() + diferencia : Math.min(estado.getTerminalesPagadas(), nuevasTerminales);
            try (PreparedStatement actualizacion = conexion.prepareStatement(
                    "UPDATE bdClientes SET numeroTerminales = ?, terminalesPagadas = ? WHERE idCliente = ?")) {
                actualizacion.setInt(1, nuevasTerminales);
                actualizacion.setInt(2, pagadas);
                actualizacion.setString(3, idCliente);
                actualizacion.executeUpdate();
            }
            if (diferencia > 0 && cobrarInstalacion) {
                BigDecimal valor = tarifa(conexion, INSTALACION_TERMINAL).multiply(BigDecimal.valueOf(diferencia));
                registrarMovimiento(conexion, idCliente, MOV_INSTALACION_TERMINALES, "Instalación de " + diferencia + " terminal(es) adicional(es)",
                        diferencia, valor, null);
            }
            return diferencia;
        });
    }

    public LocalDate calcularInicioPeriodo(String fechaLimiteTexto, int diasGabela, LocalDate hoy) {
        LocalDate fechaLimite = LocalDate.parse(fechaLimiteTexto, FORMATO_FECHA);
        return hoy.isAfter(fechaLimite.plusDays(diasGabela)) ? hoy : fechaLimite;
    }

    public BigDecimal registrarPagoMensual(String idCliente, int idPago, int terminales, boolean incluirPlan, int meses) throws SQLException {
        if (meses < 1 || meses > MAXIMO_MESES_ADELANTADOS) {
            throw new IllegalStateException("Se pueden pagar entre 1 y " + MAXIMO_MESES_ADELANTADOS + " meses");
        }
        return enTransaccion(conexion -> {
            modeloEstadoCliente estado = estadoCliente(conexion, idCliente, true);
            if (terminales < 0 || terminales > estado.getNumeroTerminales()) {
                throw new IllegalStateException("El cliente tiene " + estado.getNumeroTerminales() + " terminales adicionales contratadas");
            }

            String fechaLimiteTexto = fechaLimitePendiente(conexion, idCliente, idPago);
            if (fechaLimiteTexto == null) {
                throw new IllegalStateException("La cuota ya fue pagada o no existe");
            }

            LocalDate hoy = hoy();
            LocalDate inicio = calcularInicioPeriodo(fechaLimiteTexto, estado.getDiasGabela(), hoy);
            LocalDate nuevaFechaLimite = inicio.plusMonths(meses);
            boolean pagoAnticipado = inicio.isAfter(hoy);
            BigDecimal cantidadMeses = BigDecimal.valueOf(meses);

            Integer idPlanAplicar = estado.planParaSiguientePeriodo();
            modeloPlanFacturacion plan = null;
            if (incluirPlan && idPlanAplicar != null) {
                plan = plan(conexion, idPlanAplicar);
                if (plan != null && !plan.isMensual()) {
                    plan = null;
                }
            }

            BigDecimal valorServidor = tarifa(conexion, MENSUALIDAD_SERVIDOR).multiply(cantidadMeses);
            BigDecimal valorTerminales = tarifa(conexion, MENSUALIDAD_TERMINAL).multiply(BigDecimal.valueOf(terminales)).multiply(cantidadMeses);
            BigDecimal valorPlan = plan == null ? BigDecimal.ZERO : plan.getPrecio().multiply(cantidadMeses);
            BigDecimal total = valorServidor.add(valorTerminales).add(valorPlan);

            try (PreparedStatement actualizacion = conexion.prepareStatement(
                    "UPDATE bdPagos SET estado = 'PAGADO', fechaPago = ?, terminalesPagadas = ?, valorPagado = ?, mesesPagados = ? WHERE idPago = ?")) {
                actualizacion.setString(1, hoy.format(FORMATO_FECHA));
                actualizacion.setInt(2, terminales);
                actualizacion.setBigDecimal(3, total);
                actualizacion.setInt(4, meses);
                actualizacion.setInt(5, idPago);
                actualizacion.executeUpdate();
            }

            try (PreparedStatement insercion = conexion.prepareStatement(
                    "INSERT INTO bdPagos (idCliente, fechaLimite, estado) VALUES (?, ?, 'PENDIENTE')")) {
                insercion.setString(1, idCliente);
                insercion.setString(2, nuevaFechaLimite.format(FORMATO_FECHA));
                insercion.executeUpdate();
            }

            int documentosActuales = estado.documentosMensualesVigentes(hoy);
            int documentosMensuales;
            LocalDate vigencia;
            LocalDate inicioCobertura;
            int mesActual;
            if (plan != null) {
                documentosMensuales = pagoAnticipado ? documentosActuales : plan.getNumeroDocumentos();
                vigencia = nuevaFechaLimite;
                inicioCobertura = inicio;
                mesActual = pagoAnticipado ? -1 : 0;
            } else {
                documentosMensuales = pagoAnticipado ? documentosActuales : 0;
                vigencia = pagoAnticipado && documentosActuales > 0 ? estado.getVigenciaFEMensuales() : null;
                inicioCobertura = null;
                mesActual = 0;
            }

            try (PreparedStatement actualizacion = conexion.prepareStatement(
                    "UPDATE bdClientes SET terminalesPagadas = ?, idPlanMensual = ?, idPlanMensualSiguiente = NULL, "
                    + "numeroFEMensuales = ?, vigenciaFEMensuales = ?, documentosPorMesFE = ?, inicioCoberturaFE = ?, mesFEActual = ? "
                    + "WHERE idCliente = ?")) {
                actualizacion.setInt(1, terminales);
                if (idPlanAplicar == null) {
                    actualizacion.setNull(2, Types.INTEGER);
                } else {
                    actualizacion.setInt(2, idPlanAplicar);
                }
                actualizacion.setInt(3, documentosMensuales);
                if (vigencia == null) {
                    actualizacion.setNull(4, Types.DATE);
                } else {
                    actualizacion.setDate(4, Date.valueOf(vigencia));
                }
                actualizacion.setInt(5, plan == null ? 0 : plan.getNumeroDocumentos());
                if (inicioCobertura == null) {
                    actualizacion.setNull(6, Types.DATE);
                } else {
                    actualizacion.setDate(6, Date.valueOf(inicioCobertura));
                }
                actualizacion.setInt(7, mesActual);
                actualizacion.setString(8, idCliente);
                actualizacion.executeUpdate();
            }

            String periodo = inicio.format(FORMATO_FECHA) + " al " + nuevaFechaLimite.format(FORMATO_FECHA);
            String porMeses = meses == 1 ? "" : " x " + meses + " meses";
            registrarMovimiento(conexion, idCliente, MOV_MENSUALIDAD_SERVIDOR, "Mensualidad servidor" + porMeses + " " + periodo,
                    meses, valorServidor, idPago);
            if (terminales > 0) {
                registrarMovimiento(conexion, idCliente, MOV_MENSUALIDAD_TERMINALES, "Mensualidad " + terminales + " terminal(es)" + porMeses
                        + " " + periodo, terminales, valorTerminales, idPago);
            }
            if (plan != null) {
                registrarMovimiento(conexion, idCliente, MOV_PLAN_MENSUAL, "Plan mensual " + plan.getNombre() + porMeses + " " + periodo,
                        plan.getNumeroDocumentos(), valorPlan, idPago);
                registrarPaqueteCliente(conexion, idCliente, plan, hoy, meses);
            }
            return total;
        });
    }

    private String fechaLimitePendiente(Connection conexion, String idCliente, int idPago) throws SQLException {
        try (PreparedStatement consulta = conexion.prepareStatement(
                "SELECT fechaLimite FROM bdPagos WHERE idPago = ? AND idCliente = ? AND estado = 'PENDIENTE' FOR UPDATE")) {
            consulta.setInt(1, idPago);
            consulta.setString(2, idCliente);
            try (ResultSet resultado = consulta.executeQuery()) {
                return resultado.next() ? resultado.getString(1) : null;
            }
        }
    }

    private String fechaLimiteCuotaActual(Connection conexion, String idCliente) throws SQLException {
        try (PreparedStatement consulta = conexion.prepareStatement(
                "SELECT fechaLimite FROM bdPagos WHERE idCliente = ? AND estado = 'PENDIENTE' ORDER BY idPago DESC LIMIT 1")) {
            consulta.setString(1, idCliente);
            try (ResultSet resultado = consulta.executeQuery()) {
                return resultado.next() ? resultado.getString(1) : null;
            }
        }
    }

    public BigDecimal registrarPagoTerminalesPendientes(String idCliente, int cantidad) throws SQLException {
        return enTransaccion(conexion -> {
            modeloEstadoCliente estado = estadoCliente(conexion, idCliente, true);
            if (cantidad <= 0 || cantidad > estado.getTerminalesPendientes()) {
                throw new IllegalStateException("El cliente tiene " + estado.getTerminalesPendientes() + " terminales pendientes por pagar");
            }
            String fechaLimite = fechaLimiteCuotaActual(conexion, idCliente);
            int meses = mesesRestantesCobertura(fechaLimite == null ? null : LocalDate.parse(fechaLimite, FORMATO_FECHA), hoy());
            BigDecimal valor = tarifa(conexion, MENSUALIDAD_TERMINAL).multiply(BigDecimal.valueOf(cantidad)).multiply(BigDecimal.valueOf(meses));
            try (PreparedStatement actualizacion = conexion.prepareStatement(
                    "UPDATE bdClientes SET terminalesPagadas = terminalesPagadas + ? WHERE idCliente = ?")) {
                actualizacion.setInt(1, cantidad);
                actualizacion.setString(2, idCliente);
                actualizacion.executeUpdate();
            }
            registrarMovimiento(conexion, idCliente, MOV_TERMINALES_PENDIENTES, "Pago de " + cantidad + " terminal(es) pendiente(s)"
                    + (meses == 1 ? "" : " x " + meses + " meses") + (fechaLimite == null ? "" : " hasta " + fechaLimite), cantidad, valor, null);
            return valor;
        });
    }

    public modeloVentaPaquete previsualizarVenta(String idCliente, int idPlan) throws SQLException {
        Connection conexion = conexion();
        return decidirVenta(conexion, estadoCliente(conexion, idCliente, false), planVendible(conexion, idPlan), hoy());
    }

    private modeloPlanFacturacion planVendible(Connection conexion, int idPlan) throws SQLException {
        modeloPlanFacturacion plan = plan(conexion, idPlan);
        if (plan == null || !plan.isActivo()) {
            throw new IllegalStateException("El paquete seleccionado no existe o está inactivo");
        }
        return plan;
    }

    private modeloVentaPaquete decidirVenta(Connection conexion, modeloEstadoCliente estado, modeloPlanFacturacion plan, LocalDate hoy)
            throws SQLException {
        if (!plan.isMensual()) {
            return new modeloVentaPaquete(modeloVentaPaquete.Accion.CARGAR_SIN_VENCIMIENTO, plan, 1, plan.getPrecio(), null,
                    "Se cargarán " + big.setNumero(plan.getNumeroDocumentos()) + " documentos sin vencimiento. Se suman a los "
                    + big.setNumero(estado.getNumeroFEDisponibles()) + " que el cliente ya tiene.");
        }

        if (estado.isPlanMensualVigente(hoy)) {
            String vigente = " vigente hasta el " + estado.getVigenciaFEMensuales().format(FORMATO_FECHA);
            if (Integer.valueOf(plan.getIdPlan()).equals(estado.getIdPlanMensual())) {
                if (estado.getIdPlanMensualSiguiente() == null) {
                    return new modeloVentaPaquete(modeloVentaPaquete.Accion.YA_TIENE_PLAN, plan, 0, BigDecimal.ZERO, null,
                            "El cliente ya tiene este plan mensual" + vigente + ".");
                }
                return new modeloVentaPaquete(modeloVentaPaquete.Accion.ANULAR_CAMBIO, plan, 0, BigDecimal.ZERO, null,
                        "Se anulará el cambio programado y el cliente conservará este plan. No se cobra nada ahora.");
            }
            return new modeloVentaPaquete(modeloVentaPaquete.Accion.PROGRAMAR_CAMBIO, plan, 0, BigDecimal.ZERO, null,
                    "El cliente ya tiene un plan mensual" + vigente + ". Este plan se aplicará y cobrará en el próximo pago mensual. "
                    + "No se cobra nada ahora.");
        }

        String fechaLimiteTexto = fechaLimiteCuotaActual(conexion, estado.getIdCliente());
        LocalDate vigencia = fechaLimiteTexto == null ? null : LocalDate.parse(fechaLimiteTexto, FORMATO_FECHA);
        if (vigencia == null || vigencia.isBefore(hoy)) {
            return new modeloVentaPaquete(modeloVentaPaquete.Accion.PROGRAMAR_POR_VENCIDA, plan, 0, BigDecimal.ZERO, null,
                    "El cliente tiene la mensualidad vencida. El plan quedará asignado y se cobrará al registrar el pago mensual. "
                    + "No se cobra nada ahora.");
        }

        int meses = mesesRestantesCobertura(vigencia, hoy);
        return new modeloVentaPaquete(modeloVentaPaquete.Accion.ACTIVAR_MENSUAL, plan, meses,
                plan.getPrecio().multiply(BigDecimal.valueOf(meses)), vigencia,
                "Se activa de inmediato: " + big.setNumero(plan.getNumeroDocumentos()) + " documentos por mes (no acumulables) hasta el "
                + fechaLimiteTexto + "." + (meses == 1 ? "" : " Se cobran " + meses
                + " meses porque el cliente tiene la mensualidad pagada hasta esa fecha.") + " Luego se renueva con cada pago mensual.");
    }

    public String venderPaquete(String idCliente, int idPlan) throws SQLException {
        return enTransaccion(conexion -> {
            modeloPlanFacturacion plan = planVendible(conexion, idPlan);
            modeloEstadoCliente estado = estadoCliente(conexion, idCliente, true);
            LocalDate hoy = hoy();
            modeloVentaPaquete venta = decidirVenta(conexion, estado, plan, hoy);

            switch (venta.getAccion()) {
                case CARGAR_SIN_VENCIMIENTO:
                    try (PreparedStatement actualizacion = conexion.prepareStatement(
                            "UPDATE bdClientes SET numeroFEDisponibles = numeroFEDisponibles + ? WHERE idCliente = ?")) {
                        actualizacion.setInt(1, plan.getNumeroDocumentos());
                        actualizacion.setString(2, idCliente);
                        actualizacion.executeUpdate();
                    }
                    registrarMovimiento(conexion, idCliente, MOV_PAQUETE, "Paquete sin vencimiento " + plan.getNombre(),
                            plan.getNumeroDocumentos(), plan.getPrecio(), null);
                    registrarPaqueteCliente(conexion, idCliente, plan, hoy, 1);
                    return "Se cargaron " + big.setNumero(plan.getNumeroDocumentos()) + " documentos sin vencimiento.";
                case ANULAR_CAMBIO:
                    programarPlanSiguiente(conexion, idCliente, null);
                    return "Se anuló el cambio programado. El cliente conserva el plan " + plan.getNombre() + ".";
                case PROGRAMAR_CAMBIO:
                    programarPlanSiguiente(conexion, idCliente, idPlan);
                    return "El cambio al plan " + plan.getNombre() + " se aplicará en el próximo pago mensual.";
                case PROGRAMAR_POR_VENCIDA:
                    programarPlanSiguiente(conexion, idCliente, idPlan);
                    return "El plan " + plan.getNombre() + " quedó asignado y se cobrará al registrar el pago mensual.";
                case ACTIVAR_MENSUAL:
                    return activarPlanMensual(conexion, idCliente, venta, hoy);
                default:
                    throw new IllegalStateException(venta.getDescripcion());
            }
        });
    }

    private String activarPlanMensual(Connection conexion, String idCliente, modeloVentaPaquete venta, LocalDate hoy) throws SQLException {
        modeloPlanFacturacion plan = venta.getPlan();
        LocalDate vigencia = venta.getVigencia();
        int meses = venta.getMeses();
        try (PreparedStatement actualizacion = conexion.prepareStatement(
                "UPDATE bdClientes SET idPlanMensual = ?, idPlanMensualSiguiente = NULL, numeroFEMensuales = ?, vigenciaFEMensuales = ?, "
                + "documentosPorMesFE = ?, inicioCoberturaFE = ?, mesFEActual = 0 WHERE idCliente = ?")) {
            actualizacion.setInt(1, plan.getIdPlan());
            actualizacion.setInt(2, plan.getNumeroDocumentos());
            actualizacion.setDate(3, Date.valueOf(vigencia));
            actualizacion.setInt(4, plan.getNumeroDocumentos());
            actualizacion.setDate(5, Date.valueOf(vigencia.minusMonths(meses)));
            actualizacion.setString(6, idCliente);
            actualizacion.executeUpdate();
        }
        String fechaLimite = vigencia.format(FORMATO_FECHA);
        String porMeses = meses == 1 ? "" : " x " + meses + " meses";
        registrarMovimiento(conexion, idCliente, MOV_PLAN_MENSUAL, "Plan mensual " + plan.getNombre() + porMeses + " hasta " + fechaLimite,
                plan.getNumeroDocumentos(), venta.getValor(), null);
        registrarPaqueteCliente(conexion, idCliente, plan, hoy, meses);
        return "Plan mensual activado: " + big.setNumero(plan.getNumeroDocumentos()) + " documentos por mes hasta el " + fechaLimite
                + (meses == 1 ? "" : " (" + meses + " meses cobrados)") + ". Se renovará con cada pago mensual.";
    }

    public List<Object[]> listarSaldosClientes() throws SQLException {
        reiniciarMesesDocumentos(conexion(), null);
        List<Object[]> clientes = new ArrayList<>();
        try (PreparedStatement consulta = conexion().prepareStatement(
                "SELECT c.idCliente, c.nit, c.nombre, c.numeroFEDisponibles, "
                + "IF(c.vigenciaFEMensuales >= ?, c.numeroFEMensuales, 0) AS mensuales, "
                + "IF(c.vigenciaFEMensuales >= ?, c.vigenciaFEMensuales, NULL) AS vigencia, "
                + "p.nombre AS plan, s.nombre AS planSiguiente, c.idPlanMensualSiguiente "
                + "FROM bdClientes c LEFT JOIN bdPlanesFacturacion p ON p.idPlan = c.idPlanMensual "
                + "LEFT JOIN bdPlanesFacturacion s ON s.idPlan = c.idPlanMensualSiguiente ORDER BY c.nombre")) {
            Date hoy = Date.valueOf(hoy());
            consulta.setDate(1, hoy);
            consulta.setDate(2, hoy);
            try (ResultSet resultado = consulta.executeQuery()) {
                while (resultado.next()) {
                    Date vigencia = resultado.getDate("vigencia");
                    String plan = resultado.getString("plan");
                    Integer siguiente = enteroNulo(resultado, "idPlanMensualSiguiente");
                    if (siguiente != null) {
                        plan = (plan == null ? "Ninguno" : plan) + (siguiente <= 0 ? " (se cancela)" : " → " + resultado.getString("planSiguiente"));
                    }
                    int sinVencimiento = resultado.getInt("numeroFEDisponibles");
                    int mensuales = resultado.getInt("mensuales");
                    clientes.add(new Object[]{resultado.getString("idCliente"), resultado.getString("nit"), resultado.getString("nombre"),
                        sinVencimiento, mensuales, vigencia == null ? "" : vigencia.toLocalDate().format(FORMATO_FECHA),
                        plan == null ? "" : plan, sinVencimiento + mensuales});
                }
            }
        }
        return clientes;
    }

    public static final String MOV_GASTO = "GASTO";
    public static final java.util.Map<String, String> TIPOS_MOVIMIENTO = new java.util.LinkedHashMap<>();

    static {
        TIPOS_MOVIMIENTO.put(MOV_LICENCIA, "Licencia");
        TIPOS_MOVIMIENTO.put(MOV_INSTALACION_TERMINALES, "Instalación de terminales");
        TIPOS_MOVIMIENTO.put(MOV_MENSUALIDAD_SERVIDOR, "Mensualidad servidor");
        TIPOS_MOVIMIENTO.put(MOV_MENSUALIDAD_TERMINALES, "Mensualidad terminales");
        TIPOS_MOVIMIENTO.put(MOV_TERMINALES_PENDIENTES, "Terminales pendientes");
        TIPOS_MOVIMIENTO.put(MOV_PLAN_MENSUAL, "Plan mensual");
        TIPOS_MOVIMIENTO.put(MOV_PAQUETE, "Paquete sin vencimiento");
        TIPOS_MOVIMIENTO.put(MOV_GASTO, "Gasto");
    }

    public static String nombreTipoMovimiento(String tipo) {
        return TIPOS_MOVIMIENTO.getOrDefault(tipo, tipo);
    }

    public List<Object[]> reporteMovimientos(LocalDate desde, LocalDate hasta, String tipo, String cliente) throws SQLException {
        if (desde == null || hasta == null || hasta.isBefore(desde)) {
            throw new IllegalStateException("Seleccione un rango de fechas válido");
        }
        String textoCliente = cliente == null ? "" : cliente.trim();
        boolean incluirIngresos = !MOV_GASTO.equals(tipo);
        boolean incluirGastos = (tipo == null || MOV_GASTO.equals(tipo)) && textoCliente.isEmpty();
        List<Object[]> movimientos = new ArrayList<>();

        if (incluirIngresos) {
            StringBuilder sql = new StringBuilder("SELECT m.fecha, m.tipo, c.nit, c.nombre, m.descripcion, m.cantidad, m.valor "
                    + "FROM bdMovimientosIngresos m LEFT JOIN bdClientes c ON c.idCliente = m.idCliente WHERE m.fecha BETWEEN ? AND ?");
            if (tipo != null) {
                sql.append(" AND m.tipo = ?");
            }
            if (!textoCliente.isEmpty()) {
                sql.append(" AND (c.nit LIKE ? OR c.nombre LIKE ?)");
            }
            try (PreparedStatement consulta = conexion().prepareStatement(sql.toString())) {
                int indice = 1;
                consulta.setDate(indice++, Date.valueOf(desde));
                consulta.setDate(indice++, Date.valueOf(hasta));
                if (tipo != null) {
                    consulta.setString(indice++, tipo);
                }
                if (!textoCliente.isEmpty()) {
                    consulta.setString(indice++, "%" + textoCliente + "%");
                    consulta.setString(indice, "%" + textoCliente + "%");
                }
                try (ResultSet resultado = consulta.executeQuery()) {
                    while (resultado.next()) {
                        movimientos.add(new Object[]{resultado.getDate(1).toLocalDate(), resultado.getString(2), resultado.getString(3),
                            resultado.getString(4), resultado.getString(5), resultado.getInt(6), resultado.getBigDecimal(7)});
                    }
                }
            }
        }

        if (incluirGastos) {
            try (PreparedStatement consulta = conexion().prepareStatement(
                    "SELECT fecha, periodo, concepto, valor FROM bdGastosMensuales WHERE periodo BETWEEN ? AND ?")) {
                consulta.setString(1, YearMonth.from(desde).format(FORMATO_PERIODO));
                consulta.setString(2, YearMonth.from(hasta).format(FORMATO_PERIODO));
                try (ResultSet resultado = consulta.executeQuery()) {
                    while (resultado.next()) {
                        movimientos.add(new Object[]{resultado.getDate(1).toLocalDate(), MOV_GASTO, "", "",
                            resultado.getString(3) + " (gasto de " + resultado.getString(2) + ")", 1, resultado.getBigDecimal(4).negate()});
                    }
                }
            }
        }

        movimientos.sort((a, b) -> ((LocalDate) a[0]).compareTo((LocalDate) b[0]));
        return movimientos;
    }

    public void cancelarPlanMensual(String idCliente) throws SQLException {
        enTransaccion(conexion -> {
            modeloEstadoCliente estado = estadoCliente(conexion, idCliente, true);
            if (estado.getIdPlanMensual() == null && estado.getIdPlanMensualSiguiente() == null) {
                throw new IllegalStateException("El cliente no tiene plan mensual");
            }
            programarPlanSiguiente(conexion, idCliente, 0);
            return null;
        });
    }

    private void programarPlanSiguiente(Connection conexion, String idCliente, Integer idPlan) throws SQLException {
        try (PreparedStatement actualizacion = conexion.prepareStatement("UPDATE bdClientes SET idPlanMensualSiguiente = ? WHERE idCliente = ?")) {
            if (idPlan == null) {
                actualizacion.setNull(1, Types.INTEGER);
            } else {
                actualizacion.setInt(1, idPlan);
            }
            actualizacion.setString(2, idCliente);
            actualizacion.executeUpdate();
        }
    }

    private void registrarPaqueteCliente(Connection conexion, String idCliente, modeloPlanFacturacion plan, LocalDate fecha, int meses) throws SQLException {
        try (PreparedStatement insercion = conexion.prepareStatement(
                "INSERT INTO bdPaquetesClientes (idCliente, numeroFacturas, valorUnitario, valorTotal, fecha, idPlan, tipoPlan) VALUES (?, ?, ?, ?, ?, ?, ?)")) {
            insercion.setString(1, idCliente);
            insercion.setInt(2, plan.getNumeroDocumentos() * meses);
            insercion.setBigDecimal(3, plan.getPrecio().divide(BigDecimal.valueOf(plan.getNumeroDocumentos()), 0, RoundingMode.HALF_UP));
            insercion.setBigDecimal(4, plan.getPrecio().multiply(BigDecimal.valueOf(meses)));
            insercion.setString(5, fecha.format(FORMATO_FECHA));
            insercion.setInt(6, plan.getIdPlan());
            insercion.setString(7, plan.getTipo());
            insercion.executeUpdate();
        }
    }

    private void registrarMovimiento(Connection conexion, String idCliente, String tipo, String descripcion, int cantidad,
            BigDecimal valor, Integer idPago) throws SQLException {
        try (PreparedStatement insercion = conexion.prepareStatement(
                "INSERT INTO bdMovimientosIngresos (idCliente, fecha, tipo, descripcion, cantidad, valor, idPago) VALUES (?, ?, ?, ?, ?, ?, ?)")) {
            insercion.setString(1, idCliente);
            insercion.setDate(2, Date.valueOf(hoy()));
            insercion.setString(3, tipo);
            insercion.setString(4, descripcion.length() > 255 ? descripcion.substring(0, 255) : descripcion);
            insercion.setInt(5, cantidad);
            insercion.setBigDecimal(6, valor);
            if (idPago == null) {
                insercion.setNull(7, Types.INTEGER);
            } else {
                insercion.setInt(7, idPago);
            }
            insercion.executeUpdate();
        }
    }

    public List<Object[]> movimientosDelPeriodo(YearMonth periodo) throws SQLException {
        List<Object[]> movimientos = new ArrayList<>();
        try (PreparedStatement consulta = conexion().prepareStatement(
                "SELECT m.fecha, c.nit, c.nombre, m.tipo, m.descripcion, m.cantidad, m.valor FROM bdMovimientosIngresos m "
                + "LEFT JOIN bdClientes c ON c.idCliente = m.idCliente WHERE m.fecha BETWEEN ? AND ? ORDER BY m.fecha, m.idMovimiento")) {
            consulta.setDate(1, Date.valueOf(periodo.atDay(1)));
            consulta.setDate(2, Date.valueOf(periodo.atEndOfMonth()));
            try (ResultSet resultado = consulta.executeQuery()) {
                while (resultado.next()) {
                    movimientos.add(new Object[]{resultado.getDate(1).toLocalDate(), resultado.getString(2), resultado.getString(3),
                        resultado.getString(4), resultado.getString(5), resultado.getInt(6), resultado.getBigDecimal(7)});
                }
            }
        }
        return movimientos;
    }

    public List<Object[]> gastosDelPeriodo(YearMonth periodo) throws SQLException {
        List<Object[]> gastos = new ArrayList<>();
        try (PreparedStatement consulta = conexion().prepareStatement(
                "SELECT idGasto, fecha, concepto, valor FROM bdGastosMensuales WHERE periodo = ? ORDER BY fecha, idGasto")) {
            consulta.setString(1, periodo.format(FORMATO_PERIODO));
            try (ResultSet resultado = consulta.executeQuery()) {
                while (resultado.next()) {
                    gastos.add(new Object[]{resultado.getInt(1), resultado.getDate(2).toLocalDate(), resultado.getString(3), resultado.getBigDecimal(4)});
                }
            }
        }
        return gastos;
    }

    public void agregarGasto(YearMonth periodo, String concepto, BigDecimal valor) throws SQLException {
        if (concepto == null || concepto.trim().isEmpty()) {
            throw new IllegalStateException("Ingrese el concepto del gasto");
        }
        if (valor == null || valor.signum() <= 0) {
            throw new IllegalStateException("El valor del gasto debe ser mayor a cero");
        }
        enTransaccion(conexion -> {
            validarPeriodoAbierto(conexion, periodo);
            try (PreparedStatement insercion = conexion.prepareStatement(
                    "INSERT INTO bdGastosMensuales (periodo, fecha, concepto, valor) VALUES (?, ?, ?, ?)")) {
                insercion.setString(1, periodo.format(FORMATO_PERIODO));
                insercion.setDate(2, Date.valueOf(hoy()));
                insercion.setString(3, concepto.trim().length() > 150 ? concepto.trim().substring(0, 150) : concepto.trim());
                insercion.setBigDecimal(4, valor);
                insercion.executeUpdate();
            }
            return null;
        });
    }

    public void eliminarGasto(YearMonth periodo, int idGasto) throws SQLException {
        enTransaccion(conexion -> {
            validarPeriodoAbierto(conexion, periodo);
            try (PreparedStatement eliminacion = conexion.prepareStatement("DELETE FROM bdGastosMensuales WHERE idGasto = ? AND periodo = ?")) {
                eliminacion.setInt(1, idGasto);
                eliminacion.setString(2, periodo.format(FORMATO_PERIODO));
                eliminacion.executeUpdate();
            }
            return null;
        });
    }

    private void validarPeriodoAbierto(Connection conexion, YearMonth periodo) throws SQLException {
        if (cierre(conexion, periodo) != null) {
            throw new IllegalStateException("El mes " + periodo.format(FORMATO_PERIODO) + " ya fue marcado como repartido");
        }
    }

    public Object[] obtenerCierre(YearMonth periodo) throws SQLException {
        return cierre(conexion(), periodo);
    }

    private Object[] cierre(Connection conexion, YearMonth periodo) throws SQLException {
        try (PreparedStatement consulta = conexion.prepareStatement(
                "SELECT fechaReparto, observacion, ingresos, gastos, utilidad FROM bdCierresMensuales WHERE periodo = ?")) {
            consulta.setString(1, periodo.format(FORMATO_PERIODO));
            try (ResultSet resultado = consulta.executeQuery()) {
                if (!resultado.next()) {
                    return null;
                }
                return new Object[]{resultado.getTimestamp(1).toLocalDateTime(), resultado.getString(2), resultado.getBigDecimal(3),
                    resultado.getBigDecimal(4), resultado.getBigDecimal(5)};
            }
        }
    }

    public void marcarRepartido(YearMonth periodo, String observacion) throws SQLException {
        enTransaccion(conexion -> {
            validarPeriodoAbierto(conexion, periodo);
            BigDecimal ingresos = suma(conexion, "SELECT COALESCE(SUM(valor), 0) FROM bdMovimientosIngresos WHERE fecha BETWEEN ? AND ?",
                    Date.valueOf(periodo.atDay(1)), Date.valueOf(periodo.atEndOfMonth()));
            BigDecimal gastos = suma(conexion, "SELECT COALESCE(SUM(valor), 0) FROM bdGastosMensuales WHERE periodo = ?",
                    periodo.format(FORMATO_PERIODO));
            try (PreparedStatement insercion = conexion.prepareStatement(
                    "INSERT INTO bdCierresMensuales (periodo, ingresos, gastos, utilidad, fechaReparto, observacion) VALUES (?, ?, ?, ?, ?, ?)")) {
                insercion.setString(1, periodo.format(FORMATO_PERIODO));
                insercion.setBigDecimal(2, ingresos);
                insercion.setBigDecimal(3, gastos);
                insercion.setBigDecimal(4, ingresos.subtract(gastos));
                insercion.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
                insercion.setString(6, observacion == null ? null : (observacion.length() > 255 ? observacion.substring(0, 255) : observacion));
                insercion.executeUpdate();
            }
            return null;
        });
    }

    public void anularReparto(YearMonth periodo) throws SQLException {
        try (PreparedStatement eliminacion = conexion().prepareStatement("DELETE FROM bdCierresMensuales WHERE periodo = ?")) {
            eliminacion.setString(1, periodo.format(FORMATO_PERIODO));
            eliminacion.executeUpdate();
        }
    }

    private BigDecimal suma(Connection conexion, String sql, Object... parametros) throws SQLException {
        try (PreparedStatement consulta = conexion.prepareStatement(sql)) {
            for (int i = 0; i < parametros.length; i++) {
                consulta.setObject(i + 1, parametros[i]);
            }
            try (ResultSet resultado = consulta.executeQuery()) {
                return resultado.next() ? resultado.getBigDecimal(1) : BigDecimal.ZERO;
            }
        }
    }
}
