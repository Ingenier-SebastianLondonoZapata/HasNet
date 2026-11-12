package Dao.Ventas;

import Dao.Documentos.DaoDocumentos;
import Enums.TipoDocumento;
import Modelo.Ventas.ModeloDetalleOrdenServicio;
import Utilidades.BaseDatos.MySql_connection;
import Utilidades.Constantes;
import clases.Ventas.ndOServicio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoOrdenServicio {

    private static final Logger LOGGER = Logger.getLogger(DaoOrdenServicio.class.getName());
    private final Connection conexion;
    private final DaoDocumentos daoDocumentos = new DaoDocumentos();

    public DaoOrdenServicio() {
        this.conexion = MySql_connection.getInstancia(Constantes.BASE_DATOS_PRINCIPAL).getConnection();
    }

    public boolean guardarVehiculo(ndOServicio nodo) {
        String sql = "INSERT INTO bdOServicio(id, placa, tipo, modelo, numeroChasis, fechaCompra, marca, km, numeroMotor, color, problema) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setString(1, nodo.getId());
            pstmt.setString(2, nodo.getPlaca());
            pstmt.setString(3, nodo.getTipo());
            pstmt.setString(4, nodo.getModelo());
            pstmt.setString(5, nodo.getNumeroChasis());
            pstmt.setString(6, nodo.getFechaCompra());
            pstmt.setString(7, nodo.getMarca());
            pstmt.setString(8, nodo.getKm());
            pstmt.setString(9, nodo.getNumeroMotor());
            pstmt.setString(10, nodo.getColor());
            pstmt.setString(11, nodo.getProblema());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al guardar vehículo orden de servicio: " + nodo.getId(), e);
            return false;
        }
    }

    public void guardarDetalle(List<ModeloDetalleOrdenServicio> detalles) {
        if (detalles == null || detalles.isEmpty()) {
            return;
        }
        String sql = "INSERT INTO bdDetalleOrdenServicio(ordenServicio, idParte, nombreParte, inventario, "
                + "problemasDerecha, problemasIzquierda, observaciones, num) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        for (ModeloDetalleOrdenServicio detalle : detalles) {
            try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
                pstmt.setString(1, detalle.getIdOrden());
                pstmt.setString(2, detalle.getIdParte());
                pstmt.setString(3, detalle.getNombreParte());
                pstmt.setBoolean(4, detalle.isInventario());
                pstmt.setString(5, detalle.getProblemasDerecha());
                pstmt.setString(6, detalle.getProblemasIzquierda());
                pstmt.setString(7, detalle.getObservaciones());
                pstmt.setInt(8, detalle.getNum());
                pstmt.executeUpdate();
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "Error al guardar detalle orden de servicio: " + detalle.getIdOrden(), e);
            }
        }
    }

    public boolean modificarEstadoOrden(String estado, String idDocumento) {
        return daoDocumentos.actualizarEstadoPorFactura(TipoDocumento.ORDER_SERVICIO, idDocumento, estado, null);
    }

    public boolean eliminarVehiculo(String id) {
        String sql = "DELETE FROM bdOServicio WHERE id = ?";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setString(1, id);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al eliminar vehículo orden de servicio: " + id, e);
            return false;
        }
    }

    public boolean eliminarDetalle(String idOrden) {
        String sql = "DELETE FROM bdDetalleOrdenServicio WHERE ordenServicio = ?";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setString(1, idOrden);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al eliminar detalle orden de servicio: " + idOrden, e);
            return false;
        }
    }
}
