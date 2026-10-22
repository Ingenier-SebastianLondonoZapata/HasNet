package Dao.Tesoreria;

import Modelo.Tesoreria.ModeloDocumentoCuadre;
import Utilidades.BaseDatos.MySql_connection;
import Utilidades.Constantes;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Consultas del cuadre de caja. Todas reciben la fecha (yyyy-MM-dd) y el usuario;
 * si el usuario es null (cuadre de ADMIN) se consultan los datos de todos los usuarios.
 * Consulta directamente las tablas bd* (sin vistas).
 */
public class DaoCuadreCaja {

    private static final Logger LOGGER = Logger.getLogger(DaoCuadreCaja.class.getName());
    private final Connection conexion = MySql_connection.getInstancia(Constantes.BASE_DATOS_PRINCIPAL).getConnection();

    public List<ModeloDocumentoCuadre> obtenerFacturas(String fecha, String usuario) {
        List<Object> params = new ArrayList<>();
        params.add(fecha);
        // bdFactura guarda una fila por producto: se agrupa por documento (los valores de cabecera se repiten).
        String sql = "SELECT MAX(f.factura), MAX(f.efectivoGeneral), MAX(f.targetaGeneral), MAX(f.tarjetaCredito), "
                + "MAX(f.chequeGeneral), MAX(f.ncGeneral), MAX(f.totalGeneral), "
                + "(SELECT COALESCE(SUM(p.cuotaInicial), 0) FROM bdPrestamo p WHERE p.factura = f.idFactura), "
                + "MAX(f.terminal), MAX(f.comprobante), MAX(f.credito), MAX(f.conseMesa), MAX(f.totalPropina), MAX(f.sisteCredito) "
                + "FROM bdFactura f WHERE f.estadoGeneral = 'PENDIENTE' AND f.anulada = 0 AND f.fechaFactura = ?"
                + filtroUsuario("f.usuario", usuario, params)
                // La factura generada al saldar un plan separe es solo informativa (el dinero ya entró por los abonos):
                // bdPlanSepare.estado2 guarda el idFactura de esa factura. Requiere el índice idx_planSepare_estado2.
                + " AND NOT EXISTS (SELECT 1 FROM bdPlanSepare s WHERE s.estado2 = f.idFactura)"
                + " GROUP BY f.idFactura ORDER BY CAST(SUBSTR(f.idFactura, 6) AS UNSIGNED)";

        List<ModeloDocumentoCuadre> documentos = new ArrayList<>();
        for (Object[] fila : consultar(sql, params)) {
            ModeloDocumentoCuadre doc = new ModeloDocumentoCuadre(ModeloDocumentoCuadre.Tipo.FACTURA);
            doc.setId(texto(fila[0]));
            doc.setReferencia(doc.getId());
            doc.setEfectivo(numero(fila[1]));
            doc.setTarjeta(numero(fila[2]));
            doc.setTarjetaCredito(numero(fila[3]));
            doc.setCheque(numero(fila[4]));
            doc.setNotaCredito(numero(fila[5]));
            doc.setTotal(numero(fila[6]));
            doc.setCuotaInicial(numero(fila[7]));
            doc.setTerminal(texto(fila[8]));
            doc.setComprobante(texto(fila[9]));
            doc.setCredito(verdadero(fila[10]));
            doc.setLugar(texto(fila[11]));
            doc.setPropina(numero(fila[12]));
            doc.setSisteCredito(verdadero(fila[13]));
            documentos.add(doc);
        }
        return documentos;
    }

    public List<ModeloDocumentoCuadre> obtenerPlanesSepare(String fecha, String usuario) {
        List<Object> params = new ArrayList<>();
        params.add(fecha);
        String sql = "SELECT s.idFactura, MAX(s.efectivoGeneral), MAX(s.targetaGeneral), MAX(s.chequeGeneral), "
                + "MAX(s.ncGeneral), MAX(s.totalGeneral), MAX(s.terminal), MAX(s.comprobante) "
                + "FROM bdPlanSepare s WHERE s.estadoGeneral = 'PENDIENTE' AND s.anulada = 0 AND s.fechaFactura = ?"
                + filtroUsuario("s.usuario", usuario, params)
                + " GROUP BY s.idFactura ORDER BY CAST(SUBSTR(s.idFactura, 8) AS UNSIGNED)";

        List<ModeloDocumentoCuadre> documentos = new ArrayList<>();
        for (Object[] fila : consultar(sql, params)) {
            ModeloDocumentoCuadre doc = new ModeloDocumentoCuadre(ModeloDocumentoCuadre.Tipo.PLAN_SEPARE);
            doc.setId(texto(fila[0]));
            doc.setReferencia(doc.getId());
            doc.setEfectivo(numero(fila[1]));
            doc.setTarjeta(numero(fila[2]));
            doc.setCheque(numero(fila[3]));
            doc.setNotaCredito(numero(fila[4]));
            doc.setTotal(numero(fila[5]));
            doc.setTerminal(texto(fila[6]));
            doc.setComprobante(texto(fila[7]));
            documentos.add(doc);
        }
        return documentos;
    }

    public List<ModeloDocumentoCuadre> obtenerAbonos(String fecha, String usuario) {
        List<Object> params = new ArrayList<>();
        params.add(fecha);
        String sql = "SELECT a.factura, a.id, a.efectivo, a.targeta, a.cheque, a.nc, a.total, a.terminal, a.comprobante "
                + "FROM bdAbonos a WHERE a.estado = 'PENDIENTE' AND a.anulada = 0 AND a.fechaFactura = ?"
                + filtroUsuario("a.usuario", usuario, params)
                + " ORDER BY LENGTH(a.factura), a.factura";

        List<ModeloDocumentoCuadre> documentos = new ArrayList<>();
        for (Object[] fila : consultar(sql, params)) {
            ModeloDocumentoCuadre doc = new ModeloDocumentoCuadre(ModeloDocumentoCuadre.Tipo.ABONO);
            doc.setId(texto(fila[0]));
            doc.setReferencia(texto(fila[1]));
            doc.setEfectivo(numero(fila[2]));
            doc.setTarjeta(numero(fila[3]));
            doc.setCheque(numero(fila[4]));
            doc.setNotaCredito(numero(fila[5]));
            doc.setTotal(numero(fila[6]));
            doc.setTerminal(texto(fila[7]));
            doc.setComprobante(texto(fila[8]));
            documentos.add(doc);
        }
        return documentos;
    }

    public List<ModeloDocumentoCuadre> obtenerNotasCredito(String fecha, String usuario) {
        List<Object> params = new ArrayList<>();
        params.add(fecha);
        String sql = "SELECT n.Id, n.factura, n.valor, n.terminal, n.reembolsar "
                + "FROM bdNc n WHERE n.anulada = 0 AND (n.cuadre IS NULL OR n.cuadre = '') AND n.fecha = ?"
                + filtroUsuario("n.usuario", usuario, params)
                + " ORDER BY LENGTH(n.Id), n.Id";

        List<ModeloDocumentoCuadre> documentos = new ArrayList<>();
        for (Object[] fila : consultar(sql, params)) {
            ModeloDocumentoCuadre doc = new ModeloDocumentoCuadre(ModeloDocumentoCuadre.Tipo.NOTA_CREDITO);
            doc.setId(texto(fila[0]));
            doc.setReferencia(texto(fila[1]));
            doc.setNotaCredito(numero(fila[2]));
            doc.setTotal(numero(fila[2]));
            doc.setTerminal(texto(fila[3]));
            doc.setReembolsada(verdadero(fila[4]));
            documentos.add(doc);
        }
        return documentos;
    }

    /** Egresos (gastos) pendientes de cuadre, para que el usuario vea el movimiento. */
    public List<ModeloDocumentoCuadre> obtenerEgresos(String fecha, String usuario) {
        List<Object> params = new ArrayList<>();
        params.add(fecha);
        String sql = "SELECT e.Id, e.efectivo, e.banco, e.cheque, e.total, e.terminal "
                + "FROM bdEgreso e WHERE e.estado = 'PENDIENTE' AND e.anulada = 0 AND DATE(e.fecha) = ?"
                + filtroUsuario("e.usuario", usuario, params)
                + " ORDER BY LENGTH(e.Id), e.Id";

        List<ModeloDocumentoCuadre> documentos = new ArrayList<>();
        for (Object[] fila : consultar(sql, params)) {
            ModeloDocumentoCuadre doc = new ModeloDocumentoCuadre(ModeloDocumentoCuadre.Tipo.EGRESO);
            doc.setId(texto(fila[0]));
            doc.setReferencia(doc.getId());
            doc.setEfectivo(numero(fila[1]));
            doc.setTarjeta(numero(fila[2]));
            doc.setCheque(numero(fila[3]));
            doc.setTotal(numero(fila[4]));
            doc.setTerminal(texto(fila[5]));
            documentos.add(doc);
        }
        return documentos;
    }

    /** Suma de las recogidas parciales activas (aún no cuadradas). */
    public BigDecimal obtenerTotalRecogidaParcial(String fecha, String usuario) {
        List<Object> params = new ArrayList<>();
        params.add(fecha);
        String sql = "SELECT COALESCE(SUM(r.total), 0) FROM bdRecogidaParcial r "
                + "WHERE r.activo = 1 AND DATE(r.fecha) = ?"
                + filtroUsuario("r.usuario", usuario, params);
        return consultarNumero(sql, params);
    }

    /** Suma de las bases de caja activas (aún no cuadradas). */
    public BigDecimal obtenerTotalBase(String fecha, String usuario) {
        List<Object> params = new ArrayList<>();
        params.add(fecha);
        String sql = "SELECT COALESCE(SUM(b.total), 0) FROM bdBaseCuadreCaja b "
                + "WHERE b.activo = 1 AND DATE(b.fecha) = ?"
                + filtroUsuario("b.usuario", usuario, params);
        return consultarNumero(sql, params);
    }

    /** Suma del total de las líneas del producto domicilio en facturas pendientes de cuadre. */
    public BigDecimal obtenerTotalProductoDomicilio(String idProductoDomicilio, String fecha, String usuario) {
        List<Object> params = new ArrayList<>();
        params.add(idProductoDomicilio);
        params.add(fecha);
        String sql = "SELECT COALESCE(SUM(f.total), 0) FROM bdFactura f "
                + "WHERE f.producto = ? AND f.estadoGeneral = 'PENDIENTE' AND f.anulada = 0 AND f.fechaFactura = ?"
                + filtroUsuario("f.usuario", usuario, params);
        return consultarNumero(sql, params);
    }

    /**
     * Marca como REALIZADO todos los documentos que entraron al cuadre (los mismos que
     * devuelven las consultas obtener*), de forma atómica: si algo falla se revierte todo.
     * Con usuario null (cuadre de ADMIN) afecta a todos los usuarios; si no, solo los de ese usuario.
     */
    public boolean marcarDocumentosCuadrados(String documento, String fecha, String usuario) {
        boolean autoCommitPrevio = true;
        try {
            autoCommitPrevio = conexion.getAutoCommit();
            conexion.setAutoCommit(false);

            // Facturas (se excluye la factura informativa de plan separe, igual que en obtenerFacturas)
            actualizar("UPDATE bdFactura f SET f.red = ?, f.estadoGeneral = 'REALIZADO' "
                    + "WHERE f.estadoGeneral = 'PENDIENTE' AND f.anulada = 0 AND f.fechaFactura = ?",
                    "f.usuario", usuario,
                    " AND NOT EXISTS (SELECT 1 FROM bdPlanSepare s WHERE s.estado2 = f.idFactura)", documento, fecha);

            actualizar("UPDATE bdPlanSepare s SET s.red = ?, s.estadoGeneral = 'REALIZADO' "
                    + "WHERE s.estadoGeneral = 'PENDIENTE' AND s.anulada = 0 AND s.fechaFactura = ?",
                    "s.usuario", usuario, "", documento, fecha);

            actualizar("UPDATE bdAbonos a SET a.red = ?, a.estado = 'REALIZADO' "
                    + "WHERE a.estado = 'PENDIENTE' AND a.anulada = 0 AND a.fechaFactura = ?",
                    "a.usuario", usuario, "", documento, fecha);

            actualizar("UPDATE bdNc n SET n.cuadre = ? "
                    + "WHERE n.anulada = 0 AND (n.cuadre IS NULL OR n.cuadre = '') AND n.fecha = ?",
                    "n.usuario", usuario, "", documento, fecha);

            actualizar("UPDATE bdEgreso e SET e.modeloContable = ?, e.estado = 'REALIZADO' "
                    + "WHERE e.estado = 'PENDIENTE' AND e.anulada = 0 AND DATE(e.fecha) = ?",
                    "e.usuario", usuario, "", documento, fecha);

            // Facturas anuladas ese día: se asocian al cuadre por el usuario que las anuló
            actualizar("UPDATE bdFactura f SET f.cuadreAnulacion = ? "
                    + "WHERE f.fechaAnulacion = ? AND (f.cuadreAnulacion IS NULL OR f.cuadreAnulacion = '')",
                    "f.usuarioAnula", usuario, "", documento, fecha);

            actualizar("UPDATE bdRecogidaParcial r SET r.activo = 0 "
                    + "WHERE r.activo = 1 AND DATE(r.fecha) = ?",
                    "r.usuario", usuario, "", fecha);

            actualizar("UPDATE bdBaseCuadreCaja b SET b.activo = 0, b.idCuadre = ? "
                    + "WHERE b.activo = 1 AND DATE(b.fecha) = ?",
                    "b.usuario", usuario, "", documento, fecha);

            conexion.commit();
            return true;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al marcar los documentos del cuadre " + documento, e);
            try {
                conexion.rollback();
            } catch (SQLException re) {
                LOGGER.log(Level.SEVERE, "Error en rollback del cuadre " + documento, re);
            }
            return false;
        } finally {
            try {
                conexion.setAutoCommit(autoCommitPrevio);
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "Error restaurando autoCommit", e);
            }
        }
    }

    /** Ejecuta un UPDATE con sus parámetros + filtro de usuario opcional + condición extra al final. */
    private void actualizar(String sql, String columnaUsuario, String usuario, String condicionExtra, Object... valores)
            throws SQLException {
        List<Object> params = new ArrayList<>(Arrays.asList(valores));
        String completo = sql + filtroUsuario(columnaUsuario, usuario, params) + condicionExtra;

        try (PreparedStatement stmt = conexion.prepareStatement(completo)) {
            for (int i = 0; i < params.size(); i++) {
                stmt.setObject(i + 1, params.get(i));
            }
            stmt.executeUpdate();
        }
    }

    // -------------------------------------------------------------------------
    // Utilidades internas
    // -------------------------------------------------------------------------

    private String filtroUsuario(String columna, String usuario, List<Object> params) {
        if (usuario == null) {
            return "";
        }
        params.add(usuario);
        return " AND " + columna + " = ?";
    }

    private List<Object[]> consultar(String sql, List<Object> params) {
        List<Object[]> filas = new ArrayList<>();
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            for (int i = 0; i < params.size(); i++) {
                stmt.setObject(i + 1, params.get(i));
            }
            try (ResultSet rs = stmt.executeQuery()) {
                ResultSetMetaData meta = rs.getMetaData();
                int columnas = meta.getColumnCount();
                while (rs.next()) {
                    Object[] fila = new Object[columnas];
                    for (int i = 0; i < columnas; i++) {
                        fila[i] = rs.getObject(i + 1);
                    }
                    filas.add(fila);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error en consulta del cuadre de caja: " + sql, e);
        }
        return filas;
    }

    private BigDecimal consultarNumero(String sql, List<Object> params) {
        List<Object[]> filas = consultar(sql, params);
        if (filas.isEmpty()) {
            return BigDecimal.ZERO;
        }
        return numero(filas.get(0)[0]);
    }

    private static String texto(Object valor) {
        return valor == null ? "" : valor.toString();
    }

    private static BigDecimal numero(Object valor) {
        if (valor == null) {
            return BigDecimal.ZERO;
        }
        if (valor instanceof BigDecimal) {
            return (BigDecimal) valor;
        }
        try {
            return new BigDecimal(valor.toString().trim());
        } catch (NumberFormatException e) {
            return BigDecimal.ZERO;
        }
    }

    private static boolean verdadero(Object valor) {
        if (valor == null) {
            return false;
        }
        if (valor instanceof Boolean) {
            return (Boolean) valor;
        }
        String texto = valor.toString().trim();
        return texto.equals("1") || texto.equalsIgnoreCase("true");
    }
}
