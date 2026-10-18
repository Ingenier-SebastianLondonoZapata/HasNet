package Procesos.Inventario.Estrategia;

import Enums.Tablas;
import Modelo.Inventario.DetalleProducto;
import Modelo.Inventario.InformacionAdicional;
import Modelo.Inventario.MovimientoInventario;
import Utilidades.BaseDatos.SentenciaSql;
import Modelo.Productos.ModeloProducto;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProcesadorAnularOrdenCompra extends AbstractProcesadorMovimiento {

    @Override
    protected SentenciaSql generarSqlInventario(MovimientoInventario movimiento, String idBodega, InformacionAdicional informacionAdicional) {
        ModeloProducto producto = movimiento.getProducto();
        BigDecimal cantidad = movimiento.getCantidad();

        BigDecimal enTransito = producto.getEnTransito().subtract(cantidad);
        if (enTransito.compareTo(BigDecimal.ZERO) < 0) {
            enTransito = BigDecimal.ZERO;
        }
        producto.setEnTransito(enTransito);

        String sql = "UPDATE " + Tablas.INVENTARIO.getNombre() + " SET "
                + "enTransito = ? "
                + "WHERE idSistema = ? AND idBodega = ?";

        return new SentenciaSql(sql,
                enTransito,
                producto.getIdSistema(), idBodega);
    }

    @Override
    protected void agregarSentenciasFinales(List<SentenciaSql> sentencias, String numeroDocumento) {
        String sql = "DELETE FROM " + Tablas.DETALLE_PRODUCTO.getNombre() + " WHERE numIngreso = ?";
        sentencias.add(new SentenciaSql(sql, numeroDocumento));
    }

    @Override
    protected List<DetalleProducto> detallesAPersistir(List<DetalleProducto> detallesProductos) {
        return new ArrayList<>();
    }
}
