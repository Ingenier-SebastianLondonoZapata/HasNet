package inventario.dao;

import Enums.Tablas;
import Estrategia.AbstractDao;
import Modelo.Inventario.DetalleProducto;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DaoDetalleProducto extends AbstractDao {

    public void actualizarConsecutivo(Connection conn, int cantidadRegistros) throws SQLException {
        String sql = "UPDATE " + Tablas.CONSECUTIVOS.getNombre() + " SET numero = numero + ? WHERE Id = 'DETALLEPROD'";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, cantidadRegistros);
            ps.executeUpdate();
        }
    }

    public List<DetalleProducto> obtenerDetalleProductosDocumento(String documento) {
        List<DetalleProducto> detalles = new ArrayList<>();
        String sql = "SELECT producto, descripcion, cantidadComprada, imei, lote, fechaVencimiento, temperatura, color, talla "
                + "FROM " + Tablas.DETALLE_PRODUCTO.getNombre() + " WHERE numIngreso = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, documento);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    detalles.add(new DetalleProducto(
                            null,
                            rs.getString("producto"),
                            rs.getString("descripcion"),
                            rs.getString("imei"),
                            rs.getString("lote"),
                            rs.getString("color"),
                            rs.getString("talla"),
                            rs.getDate("fechaVencimiento") != null ? rs.getDate("fechaVencimiento").toLocalDate() : null,
                            rs.getString("temperatura"),
                            null,
                            null,
                            rs.getBigDecimal("cantidadComprada"),
                            null
                    ));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener precompra detalle: " + e.getMessage());
        }
        return detalles;
    }

    public List<DetalleProducto> obtenerDetalleProductosPrecargados(String ingreso) {
        List<DetalleProducto> detalles = new ArrayList<>();
        String sql = "SELECT producto, descripcion, cantidadComprada, imei, lote, fechaVencimiento, temperatura, color, talla "
                + "FROM " + Tablas.PRECARGAR_DETALLE.getNombre() + " WHERE ingreso = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, ingreso);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    detalles.add(new DetalleProducto(
                            null,
                            rs.getString("producto"),
                            rs.getString("descripcion"),
                            rs.getString("imei"),
                            rs.getString("lote"),
                            rs.getString("color"),
                            rs.getString("talla"),
                            rs.getDate("fechaVencimiento") != null ? rs.getDate("fechaVencimiento").toLocalDate() : null,
                            rs.getString("temperatura"),
                            null,
                            null,
                            rs.getBigDecimal("cantidadComprada"),
                            null
                    ));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener precompra detalle: " + e.getMessage());
        }
        return detalles;
    }

    public boolean guardarListaDetallesPrecargados(String ingreso, List<DetalleProducto> detalles) {
        if (detalles.isEmpty()) {
            return true;
        }

        String sql = "INSERT INTO " + Tablas.PRECARGAR_DETALLE.getNombre()
                + "(ingreso, producto, descripcion, cantidadComprada, imei, lote, fechaVencimiento, temperatura, color, talla) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            for (DetalleProducto detalle : detalles) {
                ps.setString(1, ingreso);
                ps.setString(2, detalle.getProducto());
                ps.setString(3, detalle.getDescripcion());
                ps.setBigDecimal(4, detalle.getCantidad());
                ps.setString(5, detalle.getImei());
                ps.setString(6, detalle.getLote());
                ps.setDate(7, detalle.getFechaVencimiento() != null ? Date.valueOf(detalle.getFechaVencimiento()) : null);
                ps.setString(8, detalle.getTemperatura());
                ps.setString(9, detalle.getColor());
                ps.setString(10, detalle.getTalla());
                ps.addBatch();
            }
            ps.executeBatch();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al guardar lista precarga detalle: " + e.getMessage());
            return false;
        }
    }

    public void guardarDetalle(Connection conn, DetalleProducto detalle, String usuario, String numeroDocumento) throws SQLException {

        String sql = "INSERT INTO " + Tablas.DETALLE_PRODUCTO.getNombre() + "(Id, producto, descripcion, imei, lote, color, talla, fechaVencimiento, temperatura, "
                + "estado, numIngreso, bodega, usuario, fecha, cantidadComprada, cantidadDisponible) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, detalle.getConsecutivo());
            ps.setString(2, detalle.getProducto());
            ps.setString(3, detalle.getDescripcion());
            ps.setString(4, detalle.getImei());
            ps.setString(5, detalle.getLote());
            ps.setString(6, detalle.getColor());
            ps.setString(7, detalle.getTalla());
            ps.setDate(8, detalle.getFechaVencimiento() != null ? Date.valueOf(detalle.getFechaVencimiento()) : null);
            ps.setString(9, detalle.getTemperatura());
            ps.setString(10, detalle.getEstado());
            ps.setString(11, numeroDocumento);
            ps.setString(12, detalle.getBodega());
            ps.setString(13, usuario);
            ps.setTimestamp(14, Timestamp.valueOf(LocalDateTime.now()));
            ps.setBigDecimal(15, detalle.getCantidad());
            ps.setBigDecimal(16, detalle.getCantidad());
            ps.executeUpdate();
        }
    }

}
