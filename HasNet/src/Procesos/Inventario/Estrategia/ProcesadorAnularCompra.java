package Procesos.Inventario.Estrategia;

import Enums.EstadosDetalleProducto;
import Enums.Tablas;
import Modelo.Inventario.DetalleProducto;
import Modelo.Inventario.InformacionAdicional;
import Modelo.Inventario.MovimientoInventario;
import Utilidades.BaseDatos.SentenciaSql;
import Modelo.Productos.ModeloProducto;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProcesadorAnularCompra extends AbstractProcesadorMovimiento {

    @Override
    protected SentenciaSql generarSqlInventario(MovimientoInventario movimiento, String idBodega, InformacionAdicional informacionAdicional) {
        ModeloProducto producto = movimiento.getProducto();
        BigDecimal cantidad = movimiento.getCantidad();

        BigDecimal compras = producto.getCompras().subtract(cantidad);
        if (compras.compareTo(BigDecimal.ZERO) < 0) {
            compras = BigDecimal.ZERO;
        }
        producto.setCompras(compras);

        if (!Boolean.TRUE.equals(producto.getManejaInventario())) {
            String sql = "UPDATE " + Tablas.INVENTARIO.getNombre() + " SET compras = ? WHERE idSistema = ? AND idBodega = ?";
            return new SentenciaSql(sql, compras, producto.getIdSistema(), idBodega);
        }

        BigDecimal inventario = producto.getInventario().subtract(cantidad);
        BigDecimal fisicoInventario = producto.getFisicoInventario().subtract(cantidad);
        producto.setInventario(inventario);
        producto.setFisicoInventario(fisicoInventario);

        String sql = "UPDATE " + Tablas.INVENTARIO.getNombre() + " SET "
                + "inventario = ?, fisicoInventario = ?, compras = ? "
                + "WHERE idSistema = ? AND idBodega = ?";

        return new SentenciaSql(sql,
                inventario,
                fisicoInventario,
                compras,
                producto.getIdSistema(), idBodega);
    }

    @Override
    protected void agregarSentenciasFinales(List<SentenciaSql> sentencias, String numeroDocumento) {
        String sql = "UPDATE " + Tablas.DETALLE_PRODUCTO.getNombre()
                + " SET estado = ? WHERE numIngreso = ?";
        sentencias.add(new SentenciaSql(sql, EstadosDetalleProducto.ANULADO.getNombre(), numeroDocumento));
    }

    @Override
    protected List<DetalleProducto> detallesAPersistir(List<DetalleProducto> detallesProductos) {
        return new ArrayList<>();
    }
}
