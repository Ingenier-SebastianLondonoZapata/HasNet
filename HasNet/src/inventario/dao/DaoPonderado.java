package inventario.dao;

import Modelo.Inventario.UltimoPonderado;
import Enums.HistoricoPonderados;
import Enums.Tablas;
import Estrategia.AbstractDao;
import Modelo.Inventario.PonderadoPendiente;
import Utilidades.Utilidades;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class DaoPonderado extends AbstractDao {

    public UltimoPonderado obtenerUltimoPonderado(String idProducto) throws SQLException {

        String sql = "SELECT nuevoPonderado, ultimoCosto, fecha FROM " + Tablas.ULTIMO_PONDERADO.getNombre() + " WHERE producto = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, idProducto);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new UltimoPonderado(
                            Utilidades.convertirBigDecimal(rs.getString("nuevoPonderado")),
                            Utilidades.convertirBigDecimal(rs.getString("ultimoCosto")),
                            rs.getString("fecha")
                    );
                }
            }
        }

        return new UltimoPonderado(BigDecimal.ZERO, BigDecimal.ZERO, "");
    }

    public void actualizarUltimoPonderado(Connection conn, String producto, PonderadoPendiente calculo, String usuario, String movimiento) throws SQLException {

        String sql = "UPDATE " + Tablas.ULTIMO_PONDERADO.getNombre() + " SET ultimoCosto=?, ponderadoAntiguo=?, cantidadAntigua=?, "
                + "cantidadEntrante=?, nuevoPonderado=?, nuevaCantidad=?, ingreso=?, usuario=?, fecha=? WHERE producto = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setBigDecimal(1, calculo.getUltimoCosto());
            ps.setBigDecimal(2, calculo.getPonderadoAnterior());
            ps.setBigDecimal(3, calculo.getInventarioAnterior());
            ps.setBigDecimal(4, calculo.getCantidadIngresada());
            ps.setBigDecimal(5, calculo.getPonderadoNuevo());
            ps.setBigDecimal(6, calculo.getInventarioNuevo());
            ps.setString(7, movimiento);
            ps.setString(8, usuario);
            ps.setTimestamp(9, Timestamp.valueOf(LocalDateTime.now()));
            ps.setString(10, producto);
            ps.executeUpdate();
        }
    }

    public void guardarHistorico(Connection conn, PonderadoPendiente calculo, String usuario, String movimiento) throws SQLException {

        String sql = "INSERT INTO " + Tablas.PONDERADO.getNombre() + " (fecha, producto, ponderadoAntiguo, cantidadAntigua, cantidadEntrante, "
                + "nuevoPonderado, nuevaCantidad, usuario, ultimoCosto, ingreso) VALUES (?,?,?,?,?,?,?,?,?,?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            ps.setString(2, calculo.getProducto());
            ps.setBigDecimal(3, calculo.getPonderadoAnterior());
            ps.setBigDecimal(4, calculo.getInventarioAnterior());
            ps.setBigDecimal(5, calculo.getCantidadIngresada());
            ps.setBigDecimal(6, calculo.getPonderadoNuevo());
            ps.setBigDecimal(7, calculo.getInventarioNuevo());
            ps.setString(8, usuario);
            ps.setBigDecimal(9, calculo.getUltimoCosto());
            ps.setString(10, movimiento);
            ps.executeUpdate();
        }
    }

    public void guardarHistorico(PonderadoPendiente calculo, String usuario, String movimiento) throws SQLException {

        String sql = "INSERT INTO " + Tablas.PONDERADO.getNombre() + " (fecha, producto, ponderadoAntiguo, cantidadAntigua, cantidadEntrante, "
                + "nuevoPonderado, nuevaCantidad, usuario, ultimoCosto, ingreso) VALUES (?,?,?,?,?,?,?,?,?,?)";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            ps.setString(2, calculo.getProducto());
            ps.setBigDecimal(3, calculo.getPonderadoAnterior());
            ps.setBigDecimal(4, calculo.getInventarioAnterior());
            ps.setBigDecimal(5, calculo.getCantidadIngresada());
            ps.setBigDecimal(6, calculo.getPonderadoNuevo());
            ps.setBigDecimal(7, calculo.getInventarioNuevo());
            ps.setString(8, usuario);
            ps.setBigDecimal(9, calculo.getUltimoCosto());
            ps.setString(10, movimiento);
            ps.executeUpdate();
        }
    }
    
    public void crearUltimoPonderado(String producto, BigDecimal costoInicial, String usuario) throws SQLException {

        String sql = "INSERT INTO " + Tablas.ULTIMO_PONDERADO.getNombre() + "(producto, nuevoPonderado, ultimoCosto, ingreso, usuario, fecha) VALUES(?,?,?,?,?,?)";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, producto);
            ps.setBigDecimal(2, costoInicial);
            ps.setBigDecimal(3, costoInicial);
            ps.setString(4, HistoricoPonderados.CREACION_PRODUCTO.getNombre());
            ps.setString(5, usuario);
            ps.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
            ps.executeUpdate();
        }
    }
}
