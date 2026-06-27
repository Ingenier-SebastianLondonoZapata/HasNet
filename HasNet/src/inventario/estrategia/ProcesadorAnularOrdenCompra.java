package inventario.estrategia;

import Enums.Tablas;
import Modelo.Inventario.DetalleProducto;
import Modelo.Inventario.InformacionAdicional;
import Modelo.Inventario.MovimientoInventario;
import Utilidades.BaseDatos.SentenciaSql;
import Utilidades.BaseDatos.ValidadorTabla;
import Utilidades.Inventario.UtilidadInventario;
import Utilidades.Utilidades;
import clases.productos.ndProducto;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProcesadorAnularOrdenCompra extends AbstractProcesadorMovimiento {

    @Override
    protected SentenciaSql generarSqlInventario(MovimientoInventario movimiento, String tablaUtilizada, InformacionAdicional informacionAdicional) {
        ndProducto producto = movimiento.getProducto();
        BigDecimal cantidad = movimiento.getCantidad();

        BigDecimal enTransito = Utilidades.convertirBigDecimal(producto.getEnTransito()).subtract(cantidad);
        if (enTransito.compareTo(BigDecimal.ZERO) < 0) {
            enTransito = BigDecimal.ZERO;
        }

        String sql = "UPDATE " + ValidadorTabla.validar(tablaUtilizada) + " SET "
                + "enTransito = ? "
                + "WHERE idSistema = ?";

        return new SentenciaSql(sql,
                UtilidadInventario.formatear(enTransito),
                producto.getIdSistema());
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
