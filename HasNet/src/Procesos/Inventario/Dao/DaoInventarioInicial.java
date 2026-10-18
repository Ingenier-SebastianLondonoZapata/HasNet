package Procesos.Inventario.Dao;

import Enums.Tablas;
import Estrategia.AbstractDao;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoInventarioInicial extends AbstractDao {

    private static final String TIPO_INGRESO = "INVENTARIO INICIAL";
    private static final String ESTADO_INGRESO = "PENDIENTE";

    public boolean existeInventarioInicial(String idSistema) throws SQLException {
        String sql = "SELECT 1 FROM " + Tablas.INVENTARIO_INICIAL.getNombre() + " WHERE producto = ? LIMIT 1";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, idSistema);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    public void insertarInventarioInicial(String idSistema, BigDecimal costoUnidad, BigDecimal cantidad) throws SQLException {
        String sql = "INSERT INTO " + Tablas.INVENTARIO_INICIAL.getNombre()
                + " (producto, costo, conteo1, conteo2, conteo3, total, auditoria1, auditoria2, totalAuditoria, finalizado, bodega)"
                + " VALUES (?, ?, ?, '0', '0', ?, '', '', '', '', '')";

        String cantidadTexto = cantidad.stripTrailingZeros().toPlainString();
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, idSistema);
            ps.setString(2, costoUnidad.toPlainString());
            ps.setString(3, cantidadTexto);
            ps.setString(4, cantidadTexto);
            ps.executeUpdate();
        }
    }

    public boolean existeIngreso(String idIngreso) throws SQLException {
        String sql = "SELECT 1 FROM " + Tablas.INGRESO.getNombre() + " WHERE id = ? LIMIT 1";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, idIngreso);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    public void insertarIngresoInicial(String idIngreso, BigDecimal total, String fecha, String hora, String usuario, String terminal) throws SQLException {
        String sql = "INSERT INTO " + Tablas.INGRESO.getNombre()
                + " (id, proveedor, fechaFactura, fechaVencimiento, tipo, concepto, credito, cxp, usuario, terminal, observaciones, hora, estado, bodega,"
                + " modeloContable, total, descuentos, iva, subtotal, rtIva, rtFuente, porRtFuente, impoconsumo, rtIca, efectivo, cheque, tarjeta,"
                + " descFinanciero, descProntoPago)"
                + " VALUES (?, NULL, ?, ?, ?, ' ', ?, '', ?, ?, '', ?, ?, '', '', ?, 0, 0, ?, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, idIngreso);
            ps.setString(2, fecha);
            ps.setString(3, fecha);
            ps.setString(4, TIPO_INGRESO);
            ps.setBoolean(5, false);
            ps.setString(6, usuario);
            ps.setString(7, terminal);
            ps.setString(8, hora);
            ps.setString(9, ESTADO_INGRESO);
            ps.setBigDecimal(10, total);
            ps.setBigDecimal(11, total);
            ps.executeUpdate();
        }
    }

    public void actualizarTotalIngreso(String idIngreso, String hora) throws SQLException {
        String sql = "UPDATE " + Tablas.INGRESO.getNombre() + " SET total = ?, subtotal = ?, hora = ? WHERE id = ?";

        BigDecimal total = obtenerTotalIngresoInicial();
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setBigDecimal(1, total);
            ps.setBigDecimal(2, total);
            ps.setString(3, hora);
            ps.setString(4, idIngreso);
            ps.executeUpdate();
        }
    }

    private BigDecimal obtenerTotalIngresoInicial() throws SQLException {
        try (PreparedStatement ps = getConnection().prepareStatement("SELECT total FROM sumaIng0"); ResultSet rs = ps.executeQuery()) {
            BigDecimal total = rs.next() ? rs.getBigDecimal("total") : null;
            return total == null ? BigDecimal.ZERO : total;
        }
    }

    public void insertarCompra(String idIngreso, String idSistema, String descripcion, BigDecimal cantidad, BigDecimal costoUnidad,
            BigDecimal total, String usuario) throws SQLException {
        String sql = "INSERT INTO " + Tablas.COMPRA.getNombre()
                + " (ingreso, producto, cantidad, porIva, descripcion, plu, cant2, bodega, usuario, tipoDescuento, valor, descuento, total, iva,"
                + " subtotal, valorPlu, impoconsumo, porcImpo)"
                + " VALUES (?, ?, ?, 0, ?, '1', ?, '', ?, '', ?, 0, ?, 0, ?, ?, 0, 0)";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, idIngreso);
            ps.setString(2, idSistema);
            ps.setBigDecimal(3, cantidad);
            ps.setString(4, descripcion);
            ps.setBigDecimal(5, cantidad);
            ps.setString(6, usuario);
            ps.setBigDecimal(7, costoUnidad);
            ps.setBigDecimal(8, total);
            ps.setBigDecimal(9, total);
            ps.setBigDecimal(10, costoUnidad);
            ps.executeUpdate();
        }
    }
}
