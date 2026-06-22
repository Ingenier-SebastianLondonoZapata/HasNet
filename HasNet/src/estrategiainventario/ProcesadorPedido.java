package estrategiainventario;

import Enums.EstadosDetalleProducto;
import Enums.Tablas;
import Modelo.Inventario.DetalleProducto;
import Modelo.Inventario.InformacionAdicional;
import Modelo.Inventario.MovimientoInventario;
import Modelo.Inventario.PonderadoPendiente;
import inventario.servicio.ServicioTransaccionInventario;
import Utilidades.BaseDatos.SentenciaSql;
import Utilidades.BaseDatos.ValidadorTabla;
import Utilidades.Inventario.UtilidadInventario;
import Utilidades.Utilidades;
import clases.productos.ndProducto;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProcesadorPedido extends AbstractProcesadorMovimiento {

    private final ServicioTransaccionInventario servicioTransaccionInventario = new ServicioTransaccionInventario();

    @Override
    public void procesar(List<MovimientoInventario> movimientos, List<DetalleProducto> detallesProductos, String numeroDocumento,
            String tablaUtilizada, String usuario, InformacionAdicional informacionAdicional) throws SQLException {

        List<SentenciaSql> sqlInventario = new ArrayList<>();

        for (MovimientoInventario movimiento : movimientos) {
            if (!movimiento.getIdDetalleProducto().isEmpty()) {
                sqlInventario.add(generarSqlDetalleInventario(movimiento));
            }

            sqlInventario.add(generarSqlInventario(movimiento, tablaUtilizada));
        }

        servicioTransaccionInventario.ejecutarIngreso(
                sqlInventario,
                new ArrayList<PonderadoPendiente>(),
                new ArrayList<DetalleProducto>(),
                usuario,
                numeroDocumento);
    }

    private SentenciaSql generarSqlInventario(MovimientoInventario movimiento, String tablaUtilizada) {
        ndProducto producto = movimiento.getProducto();
        BigDecimal cantidad = movimiento.getCantidad();

        BigDecimal fisicoInventario = Utilidades.convertirBigDecimal(producto.getFisicoInventario()).subtract(cantidad);
        BigDecimal pedidos = Utilidades.convertirBigDecimal(producto.getPedidos()).add(cantidad);

        String sql = "UPDATE " + ValidadorTabla.validar(tablaUtilizada) + " SET "
                + "fisicoInventario = ?, pedidos = ? "
                + "WHERE idSistema = ?";

        return new SentenciaSql(sql,
                UtilidadInventario.formatear(fisicoInventario),
                UtilidadInventario.formatear(pedidos),
                producto.getIdSistema());
    }

    private SentenciaSql generarSqlDetalleInventario(MovimientoInventario movimiento) {
        BigDecimal cantidad = movimiento.getCantidad();
        String idDetalleProducto = movimiento.getIdDetalleProducto();

        String sql = "UPDATE " + Tablas.DETALLE_PRODUCTO.getNombre()
                + " SET cantidadDisponible = cantidadDisponible - ?"
                + ", estado = CASE WHEN (cantidadDisponible - ?) <= 0 THEN ? ELSE estado END "
                + "WHERE Id = ?";

        return new SentenciaSql(sql,
                cantidad,
                cantidad,
                EstadosDetalleProducto.EN_PEDIDO.getNombre(),
                idDetalleProducto);
    }
}
