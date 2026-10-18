package Procesos.Inventario.Estrategia;

import Enums.Tablas;
import Modelo.Inventario.InformacionAdicional;
import Modelo.Inventario.MovimientoInventario;
import Utilidades.BaseDatos.SentenciaSql;
import Modelo.Productos.ModeloProducto;
import java.math.BigDecimal;

public class ProcesadorOrdenCompra extends AbstractProcesadorMovimiento {

    @Override
    protected SentenciaSql generarSqlInventario(MovimientoInventario movimiento, String idBodega, InformacionAdicional informacionAdicional) {
        ModeloProducto producto = movimiento.getProducto();
        BigDecimal cantidad = movimiento.getCantidad();

        BigDecimal enTransito = producto.getEnTransito().add(cantidad);
        producto.setEnTransito(enTransito);

        String sql = "UPDATE " + Tablas.INVENTARIO.getNombre() + " SET "
                + "enTransito = ? "
                + "WHERE idSistema = ? AND idBodega = ?";

        return new SentenciaSql(sql,
                enTransito,
                producto.getIdSistema(), idBodega);
    }
}
