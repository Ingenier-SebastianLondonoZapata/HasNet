package inventario.estrategia;

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

public class ProcesadorNotaDebito extends AbstractProcesadorMovimiento {

    @Override
    protected SentenciaSql generarSqlInventario(MovimientoInventario movimiento, String tablaUtilizada, InformacionAdicional informacionAdicional) {
        ndProducto producto = movimiento.getProducto();
        BigDecimal cantidad = movimiento.getCantidad();

        BigDecimal notaDebito = Utilidades.convertirBigDecimal(producto.getNotaDebito()).add(cantidad);
        producto.setNotaDebito(UtilidadInventario.formatear(notaDebito));

        if (!Boolean.TRUE.equals(producto.getManejaInventario())) {
            String sql = "UPDATE " + ValidadorTabla.validar(tablaUtilizada) + " SET notaDebito = ? WHERE idSistema = ?";
            return new SentenciaSql(sql, UtilidadInventario.formatear(notaDebito), producto.getIdSistema());
        }

        String sql = "UPDATE " + ValidadorTabla.validar(tablaUtilizada) + " SET "
                + "notaDebito = ? "
                + "WHERE idSistema = ?";

        return new SentenciaSql(sql,
                UtilidadInventario.formatear(notaDebito),
                producto.getIdSistema());
    }

    @Override
    protected List<DetalleProducto> detallesAPersistir(List<DetalleProducto> detallesProductos) {
        return Collections.emptyList();
    }
}
