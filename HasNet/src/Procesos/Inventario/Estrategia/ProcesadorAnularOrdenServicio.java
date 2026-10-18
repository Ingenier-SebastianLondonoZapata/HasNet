package Procesos.Inventario.Estrategia;

import Enums.Tablas;
import Modelo.Inventario.DetalleProducto;
import Modelo.Inventario.InformacionAdicional;
import Modelo.Inventario.MovimientoInventario;
import Utilidades.BaseDatos.SentenciaSql;
import Modelo.Productos.ModeloProducto;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

public class ProcesadorAnularOrdenServicio extends AbstractProcesadorMovimiento {

    @Override
    protected SentenciaSql generarSqlInventario(MovimientoInventario movimiento, String idBodega, InformacionAdicional informacionAdicional) {
        ModeloProducto producto = movimiento.getProducto();
        BigDecimal cantidad = movimiento.getCantidad();

        BigDecimal ordenServicio = producto.getOrdenServicio().subtract(cantidad);
        producto.setOrdenServicio(ordenServicio);

        if (!Boolean.TRUE.equals(producto.getManejaInventario())) {
            String sql = "UPDATE " + Tablas.INVENTARIO.getNombre() + " SET ordenServicio = ? WHERE idSistema = ? AND idBodega = ?";
            return new SentenciaSql(sql, ordenServicio, producto.getIdSistema(), idBodega);
        }

        BigDecimal fisicoInventario = producto.getFisicoInventario().add(cantidad);
        producto.setFisicoInventario(fisicoInventario);

        String sql = "UPDATE " + Tablas.INVENTARIO.getNombre() + " SET "
                + "fisicoInventario = ?, ordenServicio = ? "
                + "WHERE idSistema = ? AND idBodega = ?";

        return new SentenciaSql(sql,
                fisicoInventario,
                ordenServicio,
                producto.getIdSistema(), idBodega);
    }

    @Override
    protected List<DetalleProducto> detallesAPersistir(List<DetalleProducto> detallesProductos) {
        return Collections.emptyList();
    }
}
