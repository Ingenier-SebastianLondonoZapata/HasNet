package EstrategiaInventario;

import Enums.EstadosDetalleProducto;
import Enums.Tablas;
import Modelo.Inventario.DetalleProducto;
import Modelo.Inventario.InformacionAdicional;
import Modelo.Inventario.MovimientoInventario;
import Modelo.Inventario.PonderadoPendiente;
import Servicio.Inventario.ServicioTransaccionInventario;
import Utilidades.Inventario.UtilidadInventario;
import Utilidades.Utilidades;
import clases.productos.ndProducto;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProcesadorFacturacion extends AbstractProcesadorMovimiento {

    private final ServicioTransaccionInventario servicioTransaccionInventario = new ServicioTransaccionInventario();

    @Override
    public void procesar(List<MovimientoInventario> movimientos, List<DetalleProducto> detallesProductos, String numeroDocumento,
            String tablaUtilizada, String usuario, InformacionAdicional informacionAdicional) throws SQLException {

        List<String> sqlInventario = new ArrayList<>();

        boolean aplicarSqlDetallado = !(informacionAdicional.isVieneDesdePedido()
                || informacionAdicional.isVieneDesdePlanSepare()
                || informacionAdicional.isVieneDesdeOrdenServicio());

        for (MovimientoInventario movimiento : movimientos) {
            if (!movimiento.getIdDetalleProducto().isEmpty()) {
                if (aplicarSqlDetallado) {
                    sqlInventario.add(generarSqlDetalleInventario(movimiento));
                } else {
                    sqlInventario.add(generarSqlActualizarEstadoDetalleInventario(movimiento));
                }
            }

            sqlInventario.add(generarSqlInventario(movimiento, tablaUtilizada, informacionAdicional));
        }

        servicioTransaccionInventario.ejecutarIngreso(
                sqlInventario,
                new ArrayList<PonderadoPendiente>(),
                new ArrayList<DetalleProducto>(),
                usuario,
                numeroDocumento);
    }

    private String generarSqlInventario(MovimientoInventario movimiento, String tablaUtilizada, InformacionAdicional informacionAdicional) {
        ndProducto producto = movimiento.getProducto();
        BigDecimal cantidad = movimiento.getCantidad();

        BigDecimal fisicoInventario = Utilidades.convertirBigDecimal(producto.getFisicoInventario()).subtract(cantidad);
        BigDecimal inventario = Utilidades.convertirBigDecimal(producto.getInventario()).subtract(cantidad);
        BigDecimal ventas = Utilidades.convertirBigDecimal(producto.getVentas()).add(cantidad);

        BigDecimal pedidos = Utilidades.convertirBigDecimal(producto.getPedidos()).subtract(cantidad);
        BigDecimal planSepare = Utilidades.convertirBigDecimal(producto.getPlanSepare()).subtract(cantidad);
        BigDecimal ordenServicio = Utilidades.convertirBigDecimal(producto.getOrdenServicio()).subtract(cantidad);

        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE ").append(tablaUtilizada).append(" SET ")
                .append("inventario = '").append(UtilidadInventario.formatear(inventario)).append("', ")
                .append("fisicoInventario = '").append(UtilidadInventario.formatear(fisicoInventario)).append("', ")
                .append("ventas = '").append(UtilidadInventario.formatear(ventas)).append("'");

        if (informacionAdicional.isVieneDesdePedido()) {
            sql.append(", pedidos = '").append(UtilidadInventario.formatear(pedidos)).append("'");
        }

        if (informacionAdicional.isVieneDesdePlanSepare()) {
            sql.append(", planSepare = '").append(UtilidadInventario.formatear(planSepare)).append("'");
        }

        if (informacionAdicional.isVieneDesdeOrdenServicio()) {
            sql.append(", orderServicio = '").append(UtilidadInventario.formatear(ordenServicio)).append("'");
        }

        sql.append(" WHERE idSistema = '").append(producto.getIdSistema()).append("'");

        return sql.toString();
    }

    private String generarSqlDetalleInventario(MovimientoInventario movimiento) {
        BigDecimal cantidad = movimiento.getCantidad();
        String idDetalleProducto = movimiento.getIdDetalleProducto();

        return "UPDATE " + Tablas.DETALLE_PRODUCTO.getNombre() + " SET "
                + "cantidadDisponible = cantidadDisponible - " + cantidad + ", "
                + "estado = CASE "
                + "WHEN (cantidadDisponible - " + cantidad + ") <= 0 THEN '" + EstadosDetalleProducto.NO_DISPONIBLE.getNombre() + "' ELSE estado "
                + "END "
                + "WHERE Id = '" + idDetalleProducto + "'";
    }

    private String generarSqlActualizarEstadoDetalleInventario(MovimientoInventario movimiento) {
        String idDetalleProducto = movimiento.getIdDetalleProducto();

        return "UPDATE " + Tablas.DETALLE_PRODUCTO.getNombre() + " SET "
                + "estado = '" + EstadosDetalleProducto.NO_DISPONIBLE.getNombre() + "' "
                + "WHERE Id = '" + idDetalleProducto + "' AND cantidadDisponible <= 0";
    }
}
