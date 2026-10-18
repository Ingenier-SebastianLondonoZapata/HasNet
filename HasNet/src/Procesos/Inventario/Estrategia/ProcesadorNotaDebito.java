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

public class ProcesadorNotaDebito extends AbstractProcesadorMovimiento {

    @Override
    protected SentenciaSql generarSqlInventario(MovimientoInventario movimiento, String idBodega, InformacionAdicional informacionAdicional) {
        ModeloProducto producto = movimiento.getProducto();
        BigDecimal cantidad = movimiento.getCantidad();

        BigDecimal notaDebito = producto.getNotaDebito().add(cantidad);
        producto.setNotaDebito(notaDebito);

        if (!Boolean.TRUE.equals(producto.getManejaInventario())) {
            String sql = "UPDATE " + Tablas.INVENTARIO.getNombre() + " SET notaDebito = ? WHERE idSistema = ? AND idBodega = ?";
            return new SentenciaSql(sql, notaDebito, producto.getIdSistema(), idBodega);
        }

        String sql = "UPDATE " + Tablas.INVENTARIO.getNombre() + " SET "
                + "notaDebito = ? "
                + "WHERE idSistema = ? AND idBodega = ?";

        return new SentenciaSql(sql,
                notaDebito,
                producto.getIdSistema(), idBodega);
    }

    @Override
    protected List<DetalleProducto> detallesAPersistir(List<DetalleProducto> detallesProductos) {
        return Collections.emptyList();
    }
}
