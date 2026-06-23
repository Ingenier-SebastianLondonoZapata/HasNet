package inventario.estrategia;

import Enums.EstadosDetalleProducto;
import Enums.TipoProducto;
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

public class ProcesadorAnularFacturacion extends AbstractProcesadorMovimiento {

    @Override
    protected SentenciaSql generarSqlInventario(MovimientoInventario movimiento, String tablaUtilizada, InformacionAdicional informacionAdicional) {
        ndProducto producto = movimiento.getProducto();
        BigDecimal cantidad = movimiento.getCantidad();
        BigDecimal anulacion = Utilidades.convertirBigDecimal(producto.getAnulada()).add(cantidad);
        producto.setAnulada(UtilidadInventario.formatear(anulacion));
        String tipoProducto = producto.getUsuario();

        boolean descuentaInventario = TipoProducto.GENERICO.getValue().equals(tipoProducto) || TipoProducto.PRODUCTO_COSTEADO.getValue().equals(tipoProducto);

        if (descuentaInventario) {
            if (!Boolean.TRUE.equals(producto.getManejaInventario())) {
                String sql = "UPDATE " + ValidadorTabla.validar(tablaUtilizada) + " SET anulacion = ? WHERE idSistema = ?";
                return new SentenciaSql(sql, UtilidadInventario.formatear(anulacion), producto.getIdSistema());
            }

            BigDecimal inventario = Utilidades.convertirBigDecimal(producto.getInventario()).add(cantidad);
            BigDecimal fisicoInventario = Utilidades.convertirBigDecimal(producto.getFisicoInventario()).add(cantidad);
            producto.setInventario(UtilidadInventario.formatear(inventario));
            producto.setFisicoInventario(UtilidadInventario.formatear(fisicoInventario));

            String sql = "UPDATE " + ValidadorTabla.validar(tablaUtilizada) + " SET "
                    + "inventario = ?, fisicoInventario = ?, anulacion = ? "
                    + "WHERE idSistema = ?";

            return new SentenciaSql(sql,
                    UtilidadInventario.formatear(inventario),
                    UtilidadInventario.formatear(fisicoInventario),
                    UtilidadInventario.formatear(anulacion),
                    producto.getIdSistema());
        }

        BigDecimal costeo = Utilidades.convertirBigDecimal(producto.getCosteo());
        if (TipoProducto.PRODUCTO_COSTEADO.getValue().equals(tipoProducto)) {
            costeo = costeo.subtract(cantidad);
        }
        producto.setCosteo(UtilidadInventario.formatear(costeo));

        String sql = "UPDATE " + ValidadorTabla.validar(tablaUtilizada) + " SET "
                + "anulacion = ?, costeo = ? "
                + "WHERE idSistema = ?";

        return new SentenciaSql(sql,
                UtilidadInventario.formatear(anulacion),
                UtilidadInventario.formatear(costeo),
                producto.getIdSistema());
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
