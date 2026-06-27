package inventario.estrategia;

import Enums.EstadosDetalleProducto;
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

public class ProcesadorAnularCompra extends AbstractProcesadorMovimiento {

    @Override
    protected SentenciaSql generarSqlInventario(MovimientoInventario movimiento, String tablaUtilizada, InformacionAdicional informacionAdicional) {
        ndProducto producto = movimiento.getProducto();
        BigDecimal cantidad = movimiento.getCantidad();

        BigDecimal compras = Utilidades.convertirBigDecimal(producto.getCompras()).subtract(cantidad);
        if (compras.compareTo(BigDecimal.ZERO) < 0) {
            compras = BigDecimal.ZERO;
        }

        if (!Boolean.TRUE.equals(producto.getManejaInventario())) {
            String sql = "UPDATE " + ValidadorTabla.validar(tablaUtilizada) + " SET compras = ? WHERE idSistema = ?";
            return new SentenciaSql(sql, UtilidadInventario.formatear(compras), producto.getIdSistema());
        }

        BigDecimal inventario = Utilidades.convertirBigDecimal(producto.getInventario()).subtract(cantidad);
        BigDecimal fisicoInventario = Utilidades.convertirBigDecimal(producto.getFisicoInventario()).subtract(cantidad);

        String sql = "UPDATE " + ValidadorTabla.validar(tablaUtilizada) + " SET "
                + "inventario = ?, fisicoInventario = ?, compras = ? "
                + "WHERE idSistema = ?";

        return new SentenciaSql(sql,
                UtilidadInventario.formatear(inventario),
                UtilidadInventario.formatear(fisicoInventario),
                UtilidadInventario.formatear(compras),
                producto.getIdSistema());
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
