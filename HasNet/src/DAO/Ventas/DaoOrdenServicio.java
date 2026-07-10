package dao.Ventas;

import Modelo.Ventas.ModeloDetalleOrdenServicio;
import Utilidades.BaseDatos.MySql_connection;
import Utilidades.Constantes;
import Utilidades.Utilidades;
import clases.Ventas.ndOServicio;
import clases.Ventas.ndOServicio1;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoOrdenServicio {

    private static final Logger LOGGER = Logger.getLogger(DaoOrdenServicio.class.getName());
    private final Connection conexion;

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

    public boolean guardarLineas(List<ndOServicio1> lineas, String idFactura) {
        if (lineas == null || lineas.isEmpty()) {
            return true;
        }
        String sql = "INSERT INTO bdOServicio1(idFactura, cliente, vendedor, red, fechaFactura, fechaVencimiento, "
                + "comprobante, cotizacion, anulada, anula, credito, cxc, usuario, observacion, anulada1, anula1, credito1, cxc1, usuario1, "
                + "fechaAlerta, terminal, estadoGeneral, estado2, factura, resolucion, fechaAnulacion, cuadreAnulacion, usuarioAnula, placa, "
                + "garantia, diasGarantia, rango, terminos, notaAnulacion, conseMesa, producto, NC, concepto, descripcion, plu, estado, tercero, preparacion, bodega, "
                + "efectivoGeneral, ncGeneral, chequeGeneral, targetaGeneral, totalGeneral, descuentoGeneral, ivaGeneral, subtotalGeneral, "
                + "rtIva, rtIca, rtFuente, otros, devuelta, copago, lista, cantidad, descuento, total, iva, subtotal, utilidad, porcDescuento, cant2, porcIva, utilidad1) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        for (ndOServicio1 linea : lineas) {
            try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
                pstmt.setString(1, linea.getIdFactura());
                pstmt.setString(2, linea.getCliente());
                pstmt.setString(3, linea.getVendedor());
                pstmt.setString(4, linea.getRed());
                pstmt.setString(5, linea.getFechaFactura());
                pstmt.setString(6, linea.getFechaVencimiento());
                pstmt.setString(7, linea.getComprobante());
                pstmt.setString(8, linea.getCotizacion());
                pstmt.setBoolean(9, linea.isAnulada());
                pstmt.setString(10, linea.getAnula());
                pstmt.setBoolean(11, linea.isCredito());
                pstmt.setString(12, linea.getCxc());
                pstmt.setString(13, linea.getUsuario());
                pstmt.setString(14, linea.getObservacion());
                pstmt.setBoolean(15, linea.isAnulada1());
                pstmt.setString(16, linea.getAnula1());
                pstmt.setBoolean(17, linea.isCredito1());
                pstmt.setString(18, linea.getCxc1());
                pstmt.setString(19, linea.getUsuario1());
                pstmt.setString(20, linea.getFechaAlerta());
                pstmt.setString(21, linea.getTerminal());
                pstmt.setString(22, linea.getEstadoGeneral());
                pstmt.setString(23, linea.getEstado2());
                pstmt.setString(24, linea.getFactura());
                pstmt.setString(25, linea.getResolucion());
                pstmt.setString(26, linea.getFechaAnulacion());
                pstmt.setString(27, linea.getCuadreAnulacion());
                pstmt.setString(28, linea.getUsuarioAnula());
                pstmt.setString(29, linea.getPlaca());
                pstmt.setString(30, linea.getGarantia());
                pstmt.setString(31, linea.getDiasGarantia());
                pstmt.setString(32, linea.getRango());
                pstmt.setString(33, linea.getTerminos());
                pstmt.setString(34, linea.getNotaAnulacion());
                pstmt.setString(35, linea.getConseMesa());
                pstmt.setString(36, linea.getProducto());
                pstmt.setString(37, linea.getNC());
                pstmt.setString(38, linea.getConcepto());
                pstmt.setString(39, linea.getDescripcion());
                pstmt.setString(40, linea.getPlu());
                pstmt.setString(41, linea.getEstado());
                pstmt.setString(42, linea.getTercero());
                pstmt.setString(43, linea.getPreparacion());
                pstmt.setString(44, linea.getBodega());

                pstmt.setBigDecimal(45, Utilidades.convertirBigDecimal(linea.getEfectivoGeneral()));
                pstmt.setBigDecimal(46, Utilidades.convertirBigDecimal(linea.getNcGeneral()));
                pstmt.setBigDecimal(47, Utilidades.convertirBigDecimal(linea.getChequeGeneral()));
                pstmt.setBigDecimal(48, Utilidades.convertirBigDecimal(linea.getTargetaGeneral()));
                pstmt.setBigDecimal(49, Utilidades.convertirBigDecimal(linea.getTotalGeneral()));
                pstmt.setBigDecimal(50, Utilidades.convertirBigDecimal(linea.getDescuentoGeneral()));
                pstmt.setBigDecimal(51, Utilidades.convertirBigDecimal(linea.getIvaGeneral()));
                pstmt.setBigDecimal(52, Utilidades.convertirBigDecimal(linea.getSubtotalGeneral()));
                pstmt.setBigDecimal(53, Utilidades.convertirBigDecimal(linea.getRtIva()));
                pstmt.setBigDecimal(54, Utilidades.convertirBigDecimal(linea.getRtIca()));
                pstmt.setBigDecimal(55, Utilidades.convertirBigDecimal(linea.getRtFuente()));
                pstmt.setBigDecimal(56, Utilidades.convertirBigDecimal(linea.getOtros()));
                pstmt.setBigDecimal(57, Utilidades.convertirBigDecimal(linea.getDevuelta()));
                pstmt.setBigDecimal(58, Utilidades.convertirBigDecimal(linea.getCopago()));
                pstmt.setBigDecimal(59, Utilidades.convertirBigDecimal(linea.getLista()));
                pstmt.setBigDecimal(60, Utilidades.convertirBigDecimal(linea.getCantidad()));
                pstmt.setBigDecimal(61, Utilidades.convertirBigDecimal(linea.getDescuento()));
                pstmt.setBigDecimal(62, Utilidades.convertirBigDecimal(linea.getTotal()));
                pstmt.setBigDecimal(63, Utilidades.convertirBigDecimal(linea.getIva()));
                pstmt.setBigDecimal(64, Utilidades.convertirBigDecimal(linea.getSubtotal()));
                pstmt.setBigDecimal(65, Utilidades.convertirBigDecimal(linea.getUtilidad()));
                pstmt.setBigDecimal(66, Utilidades.convertirBigDecimal(linea.getPorcDescuento()));
                pstmt.setBigDecimal(67, Utilidades.convertirBigDecimal(linea.getCant2()));
                pstmt.setBigDecimal(68, Utilidades.convertirBigDecimal(linea.getPorcIva()));
                pstmt.setBigDecimal(69, Utilidades.convertirBigDecimal(linea.getUtilidad1()));

                if (pstmt.executeUpdate() == 0) {
                    eliminarLineas(idFactura);
                    return false;
                }
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "Error al guardar línea orden de servicio: " + idFactura, e);
                eliminarLineas(idFactura);
                return false;
            }
        }
        return true;
    }

    public boolean eliminarLineas(String idFactura) {
        String sql = "DELETE FROM bdOServicio1 WHERE idFactura = ?";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setString(1, idFactura);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al eliminar líneas orden de servicio: " + idFactura, e);
            return false;
        }
    }
}
