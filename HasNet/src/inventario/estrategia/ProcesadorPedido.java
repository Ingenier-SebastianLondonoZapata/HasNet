package inventario.estrategia;

import Enums.EstadosDetalleProducto;
import Modelo.Inventario.DetalleProducto;
import Modelo.Inventario.InformacionAdicional;
import Modelo.Inventario.MovimientoInventario;
import Utilidades.BaseDatos.SentenciaSql;
import Utilidades.BaseDatos.ValidadorTabla;
import Utilidades.Inventario.UtilidadInventario;
import Utilidades.Utilidades;
import clases.productos.ndProducto;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

public class ProcesadorPedido extends AbstractProcesadorMovimiento {

    @Override
    protected SentenciaSql generarSqlInventario(MovimientoInventario movimiento, String tablaUtilizada, InformacionAdicional informacionAdicional) {
        ndProducto producto = movimiento.getProducto();
        BigDecimal cantidad = movimiento.getCantidad();

        BigDecimal pedidos = Utilidades.convertirBigDecimal(producto.getPedidos()).add(cantidad);
        producto.setPedidos(UtilidadInventario.formatear(pedidos));

        if (!Boolean.TRUE.equals(producto.getManejaInventario())) {
            String sql = "UPDATE " + ValidadorTabla.validar(tablaUtilizada) + " SET pedidos = ? WHERE idSistema = ?";
            return new SentenciaSql(sql, UtilidadInventario.formatear(pedidos), producto.getIdSistema());
        }

        BigDecimal fisicoInventario = Utilidades.convertirBigDecimal(producto.getFisicoInventario()).subtract(cantidad);
        producto.setFisicoInventario(UtilidadInventario.formatear(fisicoInventario));

        String sql = "UPDATE " + ValidadorTabla.validar(tablaUtilizada) + " SET "
                + "fisicoInventario = ?, pedidos = ? "
                + "WHERE idSistema = ?";

        return new SentenciaSql(sql,
                UtilidadInventario.formatear(fisicoInventario),
                UtilidadInventario.formatear(pedidos),
                producto.getIdSistema());
    }

    @Override
    protected void agregarSentenciasDetalle(List<SentenciaSql> sentencias, MovimientoInventario movimiento, InformacionAdicional informacionAdicional) {
        if (!movimiento.getIdDetalleProducto().isEmpty()) {
            sentencias.add(sqlDetalleDescontarConEstado(
                    movimiento.getIdDetalleProducto(),
                    movimiento.getCantidad(),
                    EstadosDetalleProducto.EN_PEDIDO.getNombre()));
        }
    }

    @Override
    protected List<DetalleProducto> detallesAPersistir(List<DetalleProducto> detallesProductos) {
        return Collections.emptyList();
    }
}
