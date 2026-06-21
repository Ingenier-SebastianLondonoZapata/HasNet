package DAO.Inventario;

import Enums.Tablas;
import Estrategia.AbstractDao;
import Modelo.Inventario.DetalleProducto;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class DaoDetalleProducto extends AbstractDao {

    public void actualizarConsecutivo(Connection conn, int cantidadRegistros) throws SQLException {
        String sql = "UPDATE " + Tablas.CONSECUTIVOS.getNombre() + " SET numero = numero + ? WHERE Id = 'DETALLEPROD'";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, cantidadRegistros);
            ps.executeUpdate();
        }
    }

    public void guardarDetalle(Connection conn, DetalleProducto detalle, String usuario, String numeroDocumento) throws SQLException {

        String sql = "INSERT INTO bdDetalleProductos(Id, producto, descripcion, imei, lote, color, talla, fechaVencimiento, temperatura, "
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
