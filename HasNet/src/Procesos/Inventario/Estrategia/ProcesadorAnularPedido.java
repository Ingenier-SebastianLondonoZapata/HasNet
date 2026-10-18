package Procesos.Inventario.Estrategia;

import Enums.Tablas;
import Enums.EstadosDetalleProducto;
import Modelo.Inventario.DetalleProducto;
import Modelo.Inventario.InformacionAdicional;
import Modelo.Inventario.MovimientoInventario;
import Utilidades.BaseDatos.SentenciaSql;
import Modelo.Productos.ModeloProducto;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

public class ProcesadorAnularPedido extends AbstractProcesadorMovimiento {

    @Override
    protected SentenciaSql generarSqlInventario(MovimientoInventario movimiento, String idBodega, InformacionAdicional informacionAdicional) {
        ModeloProducto producto = movimiento.getProducto();
        BigDecimal cantidad = movimiento.getCantidad();

        BigDecimal pedidos = producto.getPedidos().subtract(cantidad);
        producto.setPedidos(pedidos);

        if (!Boolean.TRUE.equals(producto.getManejaInventario())) {
            String sql = "UPDATE " + Tablas.INVENTARIO.getNombre() + " SET pedidos = ? WHERE idSistema = ? AND idBodega = ?";
            return new SentenciaSql(sql, pedidos, producto.getIdSistema(), idBodega);
        }

        BigDecimal fisicoInventario = producto.getFisicoInventario().add(cantidad);
        producto.setFisicoInventario(fisicoInventario);

        String sql = "UPDATE " + Tablas.INVENTARIO.getNombre() + " SET "
                + "fisicoInventario = ?, pedidos = ? "
                + "WHERE idSistema = ? AND idBodega = ?";

        return new SentenciaSql(sql,
                fisicoInventario,
                pedidos,
                producto.getIdSistema(), idBodega);
    }

    @Override
    protected void agregarSentenciasDetalle(List<SentenciaSql> sentencias, MovimientoInventario movimiento, InformacionAdicional informacionAdicional) {
        if (!movimiento.getIdDetalleProducto().isEmpty()) {
            sentencias.add(sqlDetalleReintegrarConEstado(
                    movimiento.getIdDetalleProducto(),
                    movimiento.getCantidad(),
                    EstadosDetalleProducto.DISPONIBLE.getNombre()));
        }
    }

    @Override
    protected List<DetalleProducto> detallesAPersistir(List<DetalleProducto> detallesProductos) {
        return Collections.emptyList();
    }
}
