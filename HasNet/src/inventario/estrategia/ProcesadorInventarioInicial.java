package inventario.estrategia;

import Modelo.Inventario.InformacionAdicional;
import Modelo.Inventario.MovimientoInventario;
import Modelo.Inventario.PonderadoPendiente;
import Utilidades.BaseDatos.SentenciaSql;
import Utilidades.BaseDatos.ValidadorTabla;
import Utilidades.Inventario.UtilidadInventario;
import Utilidades.Utilidades;
import clases.productos.ndProducto;
import inventario.servicio.ServicioActualizacionPonderado;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class ProcesadorInventarioInicial extends AbstractProcesadorMovimiento {

    private final ServicioActualizacionPonderado servicioPonderado = new ServicioActualizacionPonderado();

    @Override
    protected SentenciaSql generarSqlInventario(MovimientoInventario movimiento, String tablaUtilizada, InformacionAdicional informacionAdicional) {
        ndProducto producto = movimiento.getProducto();
        BigDecimal cantidad = movimiento.getCantidad();

        BigDecimal inventarioInicial = Utilidades.convertirBigDecimal(producto.getInventarioInicial()).add(cantidad);
        producto.setInventarioInicial(UtilidadInventario.formatear(inventarioInicial));

        if (!Boolean.TRUE.equals(producto.getManejaInventario())) {
            String sql = "UPDATE " + ValidadorTabla.validar(tablaUtilizada) + " SET inventarioInicial = ? WHERE idSistema = ?";
            return new SentenciaSql(sql, UtilidadInventario.formatear(inventarioInicial), producto.getIdSistema());
        }

        BigDecimal inventario = Utilidades.convertirBigDecimal(producto.getInventario()).add(cantidad);
        BigDecimal fisicoInventario = Utilidades.convertirBigDecimal(producto.getFisicoInventario()).add(cantidad);
        producto.setInventario(UtilidadInventario.formatear(inventario));
        producto.setFisicoInventario(UtilidadInventario.formatear(fisicoInventario));

        String sql = "UPDATE " + ValidadorTabla.validar(tablaUtilizada) + " SET "
                + "inventario = ?, fisicoInventario = ?, inventarioInicial = ? "
                + "WHERE idSistema = ?";

        return new SentenciaSql(sql,
                UtilidadInventario.formatear(inventario),
                UtilidadInventario.formatear(fisicoInventario),
                UtilidadInventario.formatear(inventarioInicial),
                producto.getIdSistema());
    }

    @Override
    protected void agregarPonderado(List<PonderadoPendiente> ponderados, MovimientoInventario movimiento) throws SQLException {
        ponderados.add(servicioPonderado.calcular(
                movimiento.getProducto(),
                movimiento.getCantidad(),
                movimiento.getValorProducto()));
    }
}
