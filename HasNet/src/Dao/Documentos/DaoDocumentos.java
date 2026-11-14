package Dao.Documentos;

import Enums.TipoDocumento;
import Modelo.Documentos.ModeloCuentaCobro;
import Modelo.Documentos.ModeloDetalleDocumento;
import Modelo.Documentos.ModeloDocumento;
import Modelo.Documentos.ModeloFactura;
import Utilidades.BaseDatos.MySql_connection;
import Utilidades.Constantes;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoDocumentos {

    public static final List<TipoDocumento> TIPOS_FACTURA = Collections.unmodifiableList(
            Arrays.asList(TipoDocumento.FACTURACION, TipoDocumento.NOTA_DEBITO));

    private static final Logger LOGGER = Logger.getLogger(DaoDocumentos.class.getName());

    private static final String COLUMNAS_ENCABEZADO = "tipoDocumento, factura,cliente, vendedor, red, "
            + "fechaFactura, horaFactura, fechaVencimiento, fechaAlerta, efectivoGeneral, ncGeneral, chequeGeneral, targetaGeneral, "
            + "tarjetaCredito, totalGeneral, descuentoGeneral, ivaGeneral, subtotalGeneral, impoconsumoGeneral, totalPropina, porcPropina, "
            + "devuelta, rtIva, rtIca, rtFuente, otros, copago, comprobante, cotizacion, credito, cxc, usuario, observacion, terminal, "
            + "estadoGeneral, estado2, resolucion, anulada, anula, fechaAnulacion, cuadreAnulacion, usuarioAnula, notaAnulacion, placa, "
            + "garantia, diasGarantia, terminos, conseMesa, turno, congelada, bodega, modeloContable, "
            + "mesFacturado, idCosteo, sisteCredito, periodicidad, inicio, "
            + "hasta, cantIncremento, fechaUltimoPago, cantFacturados";

    private static final String COLUMNAS_DETALLE = "idDocumento, linea, producto, descripcion, lista, cantidad, cant2, plu, "
            + "descuento, porcDescuento, subtotal, iva, porcIva, impoconsumo, porcImpo, total, utilidad, utilidad1, costo, "
            + "estado, tercero, preparacion, rango, lote, idProd, detalle, consecutivoLinea";

    private Connection conexion() {
        return MySql_connection.getInstancia(Constantes.BASE_DATOS_PRINCIPAL).getConnection();
    }

    public boolean guardar(ModeloDocumento documento) {
        return ejecutarEnTransaccion(conexion -> insertar(conexion, documento),
                "Error al guardar el documento " + documento.getFactura());
    }

    public boolean reemplazar(ModeloDocumento documento) {
        return ejecutarEnTransaccion(conexion -> {
            eliminar(conexion, Collections.singletonList(documento.getTipoDocumento()), "factura", documento.getFactura());
            insertar(conexion, documento);
        }, "Error al reemplazar el documento " + documento.getFactura());
    }

    public boolean existe(TipoDocumento tipo, String factura) {
        String sql = "SELECT 1 FROM bdDocumentos WHERE tipoDocumento = ? AND factura = ? LIMIT 1";
        try (PreparedStatement ps = conexion().prepareStatement(sql)) {
            ps.setString(1, tipo.getValor());
            ps.setString(2, factura);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al validar la existencia del documento " + factura, e);
            return false;
        }
    }

    public ModeloDocumento obtenerPorFactura(List<TipoDocumento> tipos, String factura) {
        return obtener(tipos, "factura", factura);
    }

    public ModeloDocumento obtenerPorFactura(TipoDocumento tipo, String factura) {
        return obtener(Collections.singletonList(tipo), "factura", factura);
    }

    public ModeloDocumento obtenerMesa(String conseMesa) {
        return obtener(Collections.singletonList(TipoDocumento.MESA), "conseMesa", conseMesa);
    }

    private ModeloDocumento obtener(List<TipoDocumento> tipos, String campo, String valor) {
        String sql = "SELECT * FROM bdDocumentos WHERE " + filtroTipos(tipos) + " AND " + columnaFiltro(campo)
                + " = ? ORDER BY idDocumento LIMIT 1";
        try (PreparedStatement ps = conexion().prepareStatement(sql)) {
            asignar(ps, parametrosTipos(tipos, valor));
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? leerEncabezado(rs) : null;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al consultar el documento " + valor, e);
            return null;
        }
    }

    private ModeloDocumento leerEncabezado(ResultSet rs) throws SQLException {
        TipoDocumento tipo = TipoDocumento.fromValue(rs.getString("tipoDocumento"));
        ModeloDocumento d = crearModelo(tipo);
        d.setIdDocumento(rs.getInt("idDocumento"));
        d.setFactura(texto(rs, "factura"));
        d.setCliente(texto(rs, "cliente"));
        d.setVendedor(texto(rs, "vendedor"));
        d.setRed(texto(rs, "red"));
        d.setFechaFactura(rs.getString("fechaFactura"));
        d.setHoraFactura(rs.getString("horaFactura"));
        d.setFechaVencimiento(rs.getString("fechaVencimiento"));
        d.setFechaAlerta(rs.getString("fechaAlerta"));
        d.setEfectivo(decimal(rs, "efectivoGeneral"));
        d.setNotaCredito(decimal(rs, "ncGeneral"));
        d.setCheque(decimal(rs, "chequeGeneral"));
        d.setTarjetaDebito(decimal(rs, "targetaGeneral"));
        d.setTotal(decimal(rs, "totalGeneral"));
        d.setDescuento(decimal(rs, "descuentoGeneral"));
        d.setIva(decimal(rs, "ivaGeneral"));
        d.setSubtotal(decimal(rs, "subtotalGeneral"));
        d.setImpoconsumo(decimal(rs, "impoconsumoGeneral"));
        d.setDevuelta(decimal(rs, "devuelta"));
        d.setReteIva(decimal(rs, "rtIva"));
        d.setReteIca(decimal(rs, "rtIca"));
        d.setReteFuente(decimal(rs, "rtFuente"));
        d.setPorcentajeReteFuente(decimal(rs, "otros"));
        d.setCopago(rs.getInt("copago"));
        d.setComprobante(texto(rs, "comprobante"));
        d.setConsecutivo(texto(rs, "cotizacion"));
        d.setCredito(rs.getBoolean("credito"));
        d.setCxc(texto(rs, "cxc"));
        d.setUsuario(texto(rs, "usuario"));
        d.setObservacion(texto(rs, "observacion"));
        d.setTerminal(texto(rs, "terminal"));
        d.setEstadoGeneral(texto(rs, "estadoGeneral"));
        d.setEstado2(texto(rs, "estado2"));
        d.setResolucion(texto(rs, "resolucion"));
        d.setAnulada(rs.getBoolean("anulada"));
        d.setAnula(texto(rs, "anula"));
        d.setFechaAnulacion(rs.getString("fechaAnulacion"));
        d.setCuadreAnulacion(texto(rs, "cuadreAnulacion"));
        d.setUsuarioAnula(texto(rs, "usuarioAnula"));
        d.setNotaAnulacion(texto(rs, "notaAnulacion"));
        d.setPlaca(texto(rs, "placa"));
        d.setGarantia(texto(rs, "garantia"));
        d.setDiasGarantia(texto(rs, "diasGarantia"));
        d.setTerminos(texto(rs, "terminos"));
        d.setConseMesa(texto(rs, "conseMesa"));
        d.setTurno(texto(rs, "turno"));
        d.setBodega(texto(rs, "bodega"));

        if (d instanceof ModeloFactura) {
            ModeloFactura f = (ModeloFactura) d;
            f.setTarjetaCredito(decimal(rs, "tarjetaCredito"));
            f.setTotalPropina(decimal(rs, "totalPropina"));
            f.setPorcentajePropina(texto(rs, "porcPropina"));
            f.setTipoComprobante(texto(rs, "modeloContable"));
            f.setCongelada(texto(rs, "congelada"));
            f.setMesFacturado(texto(rs, "mesFacturado"));
            f.setIdCosteo(texto(rs, "idCosteo"));
            f.setSisteCredito(rs.getBoolean("sisteCredito"));
        } else if (d instanceof ModeloCuentaCobro) {
            ModeloCuentaCobro c = (ModeloCuentaCobro) d;
            c.setPeriodicidad(texto(rs, "periodicidad"));
            c.setInicio(rs.getString("inicio"));
            c.setHasta(rs.getString("hasta"));
            c.setCantidadIncremento(texto(rs, "cantIncremento"));
            c.setFechaUltimoPago(rs.getString("fechaUltimoPago"));
            c.setCantidadFacturados(rs.getInt("cantFacturados"));
        }
        return d;
    }

    private ModeloDocumento crearModelo(TipoDocumento tipo) {
        if (TIPOS_FACTURA.contains(tipo)) {
            return new ModeloFactura(tipo);
        }
        if (tipo == TipoDocumento.CUENTA_COBRO) {
            return new ModeloCuentaCobro();
        }
        return new ModeloDocumento(tipo);
    }

    private String texto(ResultSet rs, String columna) throws SQLException {
        String valor = rs.getString(columna);
        return valor == null ? "" : valor;
    }

    private BigDecimal decimal(ResultSet rs, String columna) throws SQLException {
        BigDecimal valor = rs.getBigDecimal(columna);
        return valor == null ? BigDecimal.ZERO : valor;
    }

    public boolean loteAnulado(String lote) {
        String sql = "SELECT anulada FROM bdDocumentos WHERE " + filtroTipos(TIPOS_FACTURA) + " AND cxc = ? ORDER BY idDocumento DESC LIMIT 1";
        try (PreparedStatement ps = conexion().prepareStatement(sql)) {
            List<Object> parametros = parametrosTipos(TIPOS_FACTURA, lote);
            asignar(ps, parametros);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() && rs.getBoolean("anulada");
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al consultar el estado del lote " + lote, e);
            return false;
        }
    }

    public boolean eliminarPorFactura(TipoDocumento tipo, String factura) {
        return eliminarPorFactura(Collections.singletonList(tipo), factura);
    }

    public boolean eliminarPorFactura(List<TipoDocumento> tipos, String factura) {
        return ejecutarEnTransaccion(conexion -> eliminar(conexion, tipos, "factura", factura),
                "Error al eliminar el documento " + factura);
    }

    public boolean anularPorFactura(List<TipoDocumento> tipos, String factura, String fechaAnulacion, String usuarioAnula,
            String nota, String consecutivoAnulacion, String estadoGeneral) {
        return anular(tipos, "factura", factura, fechaAnulacion, usuarioAnula, nota, consecutivoAnulacion, estadoGeneral);
    }

    public boolean anularPorLote(String lote) {
        String sql = "UPDATE bdDocumentos SET anulada = 1 WHERE " + filtroTipos(TIPOS_FACTURA) + " AND cxc = ?";
        return actualizar(sql, parametrosTipos(TIPOS_FACTURA, lote), "Error al anular el lote " + lote);
    }

    public boolean actualizarEstadoPorFactura(TipoDocumento tipo, String factura, String estadoGeneral, String estado2) {
        return actualizarEstado(tipo, "factura", factura, estadoGeneral, estado2);
    }

    public boolean actualizarLineaOrden(String factura, String producto, String red, String estadoGeneral) {
        String sql = "UPDATE bdDocumentosDetalle dd INNER JOIN bdDocumentos d ON d.idDocumento = dd.idDocumento "
                + "SET dd.redLinea = ?, dd.estadoGeneralLinea = ? "
                + "WHERE d.tipoDocumento = ? AND d.factura = ? AND dd.producto = ?";
        return actualizar(sql, Arrays.<Object>asList(red, estadoGeneral, TipoDocumento.ORDER_SERVICIO.getValor(), factura, producto),
                "Error al actualizar la línea de la orden " + factura);
    }

    public boolean actualizarEstado2LineaOrden(String factura, String producto, String estado2) {
        String sql = "UPDATE bdDocumentosDetalle dd INNER JOIN bdDocumentos d ON d.idDocumento = dd.idDocumento "
                + "SET dd.estado2Linea = ? WHERE d.tipoDocumento = ? AND d.factura = ? AND dd.producto = ?";
        return actualizar(sql, Arrays.<Object>asList(estado2, TipoDocumento.ORDER_SERVICIO.getValor(), factura, producto),
                "Error al actualizar la línea de la orden " + factura);
    }

    public boolean actualizarFacturacionCuentaCobro(String factura, String fechaUltimoPago, int cantidadFacturados) {
        StringBuilder sql = new StringBuilder("UPDATE bdDocumentos SET cantFacturados = ?");
        List<Object> parametros = new ArrayList<>();
        parametros.add(cantidadFacturados);
        if (fechaUltimoPago != null) {
            sql.append(", fechaUltimoPago = ?");
            parametros.add(fecha(fechaUltimoPago));
        }
        sql.append(" WHERE tipoDocumento = ? AND factura = ?");
        parametros.add(TipoDocumento.CUENTA_COBRO.getValor());
        parametros.add(factura);
        return actualizar(sql.toString(), parametros, "Error al actualizar la cuenta de cobro " + factura);
    }

    public boolean cambiarMesa(String factura, String mesa) {
        String sql = "UPDATE bdDocumentos SET conseMesa = ? WHERE tipoDocumento = ? AND factura = ?";
        return actualizar(sql, Arrays.<Object>asList(mesa, TipoDocumento.MESA.getValor(), factura),
                "Error al cambiar la mesa del documento " + factura);
    }

    private boolean anular(List<TipoDocumento> tipos, String campo, String valor, String fechaAnulacion, String usuarioAnula,
            String nota, String consecutivoAnulacion, String estadoGeneral) {
        StringBuilder sql = new StringBuilder("UPDATE bdDocumentos SET anulada = 1, fechaAnulacion = ?, usuarioAnula = ?, notaAnulacion = ?, anula = ?");
        List<Object> parametros = new ArrayList<>(Arrays.<Object>asList(fecha(fechaAnulacion), usuarioAnula, nota, consecutivoAnulacion));
        if (estadoGeneral != null) {
            sql.append(", estadoGeneral = ?");
            parametros.add(estadoGeneral);
        }
        sql.append(" WHERE ").append(filtroTipos(tipos)).append(" AND ").append(columnaFiltro(campo)).append(" = ?");
        parametros.addAll(parametrosTipos(tipos, valor));

        boolean limpiarEstado = estadoGeneral != null;
        return ejecutarEnTransaccion(conexion -> {
            ejecutar(conexion, sql.toString(), parametros);
            if (limpiarEstado) {
                for (TipoDocumento tipo : tipos) {
                    limpiarEstadosLinea(conexion, tipo, campo, valor, false, true, false);
                }
            }
        }, "Error al anular el documento " + valor);
    }

    private boolean actualizarEstado(TipoDocumento tipo, String campo, String valor, String estadoGeneral, String estado2) {
        StringBuilder sql = new StringBuilder("UPDATE bdDocumentos SET estadoGeneral = ?");
        List<Object> parametros = new ArrayList<>();
        parametros.add(estadoGeneral);
        if (estado2 != null) {
            sql.append(", estado2 = ?");
            parametros.add(estado2);
        }
        sql.append(" WHERE tipoDocumento = ? AND ").append(columnaFiltro(campo)).append(" = ?");
        parametros.add(tipo.getValor());
        parametros.add(valor);

        return ejecutarEnTransaccion(conexion -> {
            ejecutar(conexion, sql.toString(), parametros);
            limpiarEstadosLinea(conexion, tipo, campo, valor, false, true, estado2 != null);
        }, "Error al actualizar el estado del documento " + valor);
    }

    private void limpiarEstadosLinea(Connection conexion, TipoDocumento tipo, String campo, String valor,
            boolean red, boolean estadoGeneral, boolean estado2) throws SQLException {
        if (tipo != TipoDocumento.ORDER_SERVICIO) {
            return;
        }

        List<String> asignaciones = new ArrayList<>();
        if (red) {
            asignaciones.add("dd.redLinea = NULL");
        }
        if (estadoGeneral) {
            asignaciones.add("dd.estadoGeneralLinea = NULL");
        }
        if (estado2) {
            asignaciones.add("dd.estado2Linea = NULL");
        }

        String sql = "UPDATE bdDocumentosDetalle dd INNER JOIN bdDocumentos d ON d.idDocumento = dd.idDocumento SET "
                + String.join(", ", asignaciones) + " WHERE d.tipoDocumento = ? AND d." + columnaFiltro(campo) + " = ?";
        ejecutar(conexion, sql, Arrays.<Object>asList(tipo.getValor(), valor));
    }

    private void insertar(Connection conexion, ModeloDocumento documento) throws SQLException {
        String marcadores = String.join(",", Collections.nCopies(COLUMNAS_ENCABEZADO.split(",").length, "?"));
        String sql = "INSERT INTO bdDocumentos(" + COLUMNAS_ENCABEZADO + ") VALUES (" + marcadores + ")";

        int idDocumento;
        try (PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            asignar(ps, valoresEncabezado(documento));
            ps.executeUpdate();
            try (ResultSet llaves = ps.getGeneratedKeys()) {
                if (!llaves.next()) {
                    throw new SQLException("No se obtuvo el identificador del documento " + documento.getFactura());
                }
                idDocumento = llaves.getInt(1);
            }
        }

        documento.setIdDocumento(idDocumento);
        if (documento.getDetalles().isEmpty()) {
            return;
        }

        String marcadoresDetalle = String.join(",", Collections.nCopies(COLUMNAS_DETALLE.split(",").length, "?"));
        String sqlDetalle = "INSERT INTO bdDocumentosDetalle(" + COLUMNAS_DETALLE + ") VALUES (" + marcadoresDetalle + ")";
        try (PreparedStatement ps = conexion.prepareStatement(sqlDetalle)) {
            for (ModeloDetalleDocumento detalle : documento.getDetalles()) {
                asignar(ps, valoresDetalle(idDocumento, detalle));
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private void eliminar(Connection conexion, List<TipoDocumento> tipos, String campo, String valor) throws SQLException {
        String sql = "DELETE FROM bdDocumentos WHERE " + filtroTipos(tipos) + " AND " + columnaFiltro(campo) + " = ?";
        ejecutar(conexion, sql, parametrosTipos(tipos, valor));
    }

    private List<Object> valoresEncabezado(ModeloDocumento d) {
        ModeloFactura factura = d instanceof ModeloFactura ? (ModeloFactura) d : null;
        ModeloCuentaCobro cuentaCobro = d instanceof ModeloCuentaCobro ? (ModeloCuentaCobro) d : null;

        return Arrays.<Object>asList(
                d.getTipoDocumento().getValor(), d.getFactura(), d.getCliente(), d.getVendedor(), d.getRed(),
                fecha(d.getFechaFactura()), hora(d.getHoraFactura()), fecha(d.getFechaVencimiento()), fecha(d.getFechaAlerta()),
                d.getEfectivo(), d.getNotaCredito(), d.getCheque(), d.getTarjetaDebito(),
                factura != null ? factura.getTarjetaCredito() : BigDecimal.ZERO,
                d.getTotal(), d.getDescuento(), d.getIva(), d.getSubtotal(), d.getImpoconsumo(),
                factura != null ? factura.getTotalPropina() : BigDecimal.ZERO,
                factura != null ? factura.getPorcentajePropina() : null,
                d.getDevuelta(), d.getReteIva(), d.getReteIca(), d.getReteFuente(), d.getPorcentajeReteFuente(), d.getCopago(),
                d.getComprobante(), d.getConsecutivo(), d.isCredito(), d.getCxc(), d.getUsuario(), d.getObservacion(), d.getTerminal(),
                d.getEstadoGeneral(), d.getEstado2(), d.getResolucion(), d.isAnulada(), d.getAnula(), fecha(d.getFechaAnulacion()),
                d.getCuadreAnulacion(), d.getUsuarioAnula(), d.getNotaAnulacion(), d.getPlaca(), d.getGarantia(), d.getDiasGarantia(),
                d.getTerminos(), d.getConseMesa(), d.getTurno(),
                factura != null ? factura.getCongelada() : null,
                d.getBodega(),
                factura != null ? factura.getTipoComprobante() : null,
                factura != null ? factura.getMesFacturado() : null,
                factura != null ? factura.getIdCosteo() : null,
                factura != null && factura.isSisteCredito(),
                cuentaCobro != null ? cuentaCobro.getPeriodicidad() : null,
                cuentaCobro != null ? fecha(cuentaCobro.getInicio()) : null,
                cuentaCobro != null ? fecha(cuentaCobro.getHasta()) : null,
                cuentaCobro != null ? cuentaCobro.getCantidadIncremento() : null,
                cuentaCobro != null ? fecha(cuentaCobro.getFechaUltimoPago()) : null,
                cuentaCobro != null ? cuentaCobro.getCantidadFacturados() : null);
    }

    private List<Object> valoresDetalle(int idDocumento, ModeloDetalleDocumento l) {
        return Arrays.<Object>asList(
                idDocumento, l.getLinea(), l.getProducto(), l.getDescripcion(), l.getPrecio(), l.getCantidad(), l.getCantidadUnidades(),
                l.getPlu(), l.getDescuento(), l.getPorcentajeDescuento(), l.getSubtotal(), l.getIva(), l.getPorcentajeIva(),
                l.getImpoconsumo(), l.getPorcentajeImpoconsumo(), l.getTotal(), l.getUtilidad(), l.getUtilidad1(), l.getCosto(),
                l.getEstado(), l.getTercero(), l.getPreparacion(), l.getRango(), l.getLote(),
                l.getIdProd(), l.getDetalle(), l.getConsecutivoLinea());
    }

    private boolean actualizar(String sql, List<Object> parametros, String mensajeError) {
        try {
            ejecutar(conexion(), sql, parametros);
            return true;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, mensajeError, e);
            return false;
        }
    }

    private void ejecutar(Connection conexion, String sql, List<Object> parametros) throws SQLException {
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            asignar(ps, parametros);
            ps.executeUpdate();
        }
    }

    private boolean ejecutarEnTransaccion(Operacion operacion, String mensajeError) {
        Connection conexion = conexion();
        boolean autoCommitPrevio = true;
        try {
            autoCommitPrevio = conexion.getAutoCommit();
            conexion.setAutoCommit(false);
            operacion.ejecutar(conexion);
            conexion.commit();
            return true;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, mensajeError, e);
            try {
                conexion.rollback();
            } catch (SQLException re) {
                LOGGER.log(Level.SEVERE, "Error al revertir la transacción del documento", re);
            }
            return false;
        } finally {
            try {
                conexion.setAutoCommit(autoCommitPrevio);
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "Error al restaurar el autocommit", e);
            }
        }
    }

    private void asignar(PreparedStatement ps, List<Object> valores) throws SQLException {
        for (int i = 0; i < valores.size(); i++) {
            Object valor = valores.get(i);
            int posicion = i + 1;
            if (valor == null) {
                ps.setNull(posicion, Types.NULL);
            } else if (valor instanceof BigDecimal) {
                ps.setBigDecimal(posicion, (BigDecimal) valor);
            } else if (valor instanceof Integer) {
                ps.setInt(posicion, (Integer) valor);
            } else if (valor instanceof Boolean) {
                ps.setBoolean(posicion, (Boolean) valor);
            } else {
                ps.setString(posicion, valor.toString());
            }
        }
    }

    private String filtroTipos(List<TipoDocumento> tipos) {
        return "tipoDocumento IN (" + String.join(",", Collections.nCopies(tipos.size(), "?")) + ")";
    }

    private List<Object> parametrosTipos(List<TipoDocumento> tipos, Object valor) {
        List<Object> parametros = new ArrayList<>();
        for (TipoDocumento tipo : tipos) {
            parametros.add(tipo.getValor());
        }
        parametros.add(valor);
        return parametros;
    }

    private String columnaFiltro(String campo) {
        if ("factura".equals(campo) || "conseMesa".equals(campo)) {
            return campo;
        }
        throw new IllegalArgumentException("Campo de filtro no soportado: " + campo);
    }

    private String fecha(String valor) {
        if (valor == null || valor.trim().isEmpty()) {
            return null;
        }
        String limpio = valor.trim();
        return limpio.length() > 10 ? limpio.substring(0, 10) : limpio;
    }

    private String hora(String valor) {
        return valor == null || valor.trim().isEmpty() ? null : valor.trim();
    }

    private interface Operacion {

        void ejecutar(Connection conexion) throws SQLException;
    }
}
