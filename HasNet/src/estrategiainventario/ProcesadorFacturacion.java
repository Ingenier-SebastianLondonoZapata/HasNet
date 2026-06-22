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

public class ProcesadorFacturacion extends AbstractProcesadorMovimiento {

    private final ServicioTransaccionInventario servicioTransaccionInventario = new ServicioTransaccionInventario();

    @Override
    public void procesar(List<MovimientoInventario> movimientos, List<DetalleProducto> detallesProductos, String numeroDocumento,
            String tablaUtilizada, String usuario, InformacionAdicional informacionAdicional) throws SQLException {

        List<SentenciaSql> sqlInventario = new ArrayList<>();

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

    private SentenciaSql generarSqlInventario(MovimientoInventario movimiento, String tablaUtilizada, InformacionAdicional informacionAdicional) {
        ndProducto producto = movimiento.getProducto();
        BigDecimal cantidad = movimiento.getCantidad();

        BigDecimal fisicoInventario = Utilidades.convertirBigDecimal(producto.getFisicoInventario()).subtract(cantidad);
        BigDecimal inventario = Utilidades.convertirBigDecimal(producto.getInventario()).subtract(cantidad);
        BigDecimal ventas = Utilidades.convertirBigDecimal(producto.getVentas()).add(cantidad);

        BigDecimal pedidos = Utilidades.convertirBigDecimal(producto.getPedidos()).subtract(cantidad);
        BigDecimal planSepare = Utilidades.convertirBigDecimal(producto.getPlanSepare()).subtract(cantidad);
        BigDecimal ordenServicio = Utilidades.convertirBigDecimal(producto.getOrdenServicio()).subtract(cantidad);

        List<Object> parametros = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE ").append(ValidadorTabla.validar(tablaUtilizada)).append(" SET ")
                .append("inventario = ?, fisicoInventario = ?, ventas = ?");
        parametros.add(UtilidadInventario.formatear(inventario));
        parametros.add(UtilidadInventario.formatear(fisicoInventario));
        parametros.add(UtilidadInventario.formatear(ventas));

        if (informacionAdicional.isVieneDesdePedido()) {
            sql.append(", pedidos = ?");
            parametros.add(UtilidadInventario.formatear(pedidos));
        }

        if (informacionAdicional.isVieneDesdePlanSepare()) {
            sql.append(", planSepare = ?");
            parametros.add(UtilidadInventario.formatear(planSepare));
        }

        if (informacionAdicional.isVieneDesdeOrdenServicio()) {
            sql.append(", ordenServicio = ?");
            parametros.add(UtilidadInventario.formatear(ordenServicio));
        }

        sql.append(" WHERE idSistema = ?");
        parametros.add(producto.getIdSistema());

        return new SentenciaSql(sql.toString(), parametros.toArray());
    }

    private SentenciaSql generarSqlDetalleInventario(MovimientoInventario movimiento) {
        BigDecimal cantidad = movimiento.getCantidad();
        String idDetalleProducto = movimiento.getIdDetalleProducto();

        String sql = "UPDATE " + Tablas.DETALLE_PRODUCTO.getNombre() + " SET "
                + "cantidadDisponible = cantidadDisponible - ?, "
                + "estado = CASE WHEN (cantidadDisponible - ?) <= 0 THEN ? ELSE estado END "
                + "WHERE Id = ?";

        return new SentenciaSql(sql,
                cantidad,
                cantidad,
                EstadosDetalleProducto.NO_DISPONIBLE.getNombre(),
                idDetalleProducto);
    }

    private SentenciaSql generarSqlActualizarEstadoDetalleInventario(MovimientoInventario movimiento) {
        String idDetalleProducto = movimiento.getIdDetalleProducto();

        String sql = "UPDATE " + Tablas.DETALLE_PRODUCTO.getNombre() + " SET "
                + "estado = ? WHERE Id = ? AND cantidadDisponible <= 0";

        return new SentenciaSql(sql,
                EstadosDetalleProducto.NO_DISPONIBLE.getNombre(),
                idDetalleProducto);
    }
}
