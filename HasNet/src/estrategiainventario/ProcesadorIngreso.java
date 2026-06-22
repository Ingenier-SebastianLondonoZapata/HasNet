package estrategiainventario;

import Modelo.Inventario.DetalleProducto;
import Modelo.Inventario.InformacionAdicional;
import Modelo.Inventario.MovimientoInventario;
import Modelo.Inventario.PonderadoPendiente;
import inventario.servicio.ServicioActualizacionPonderado;
import inventario.servicio.ServicioTransaccionInventario;
import Utilidades.BaseDatos.SentenciaSql;
import Utilidades.BaseDatos.ValidadorTabla;
import Utilidades.Inventario.UtilidadInventario;
import Utilidades.Utilidades;
import clases.productos.ndProducto;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProcesadorIngreso extends AbstractProcesadorMovimiento {

    private final ServicioActualizacionPonderado servicioPonderado = new ServicioActualizacionPonderado();
    private final ServicioTransaccionInventario servicioTransaccionInventario = new ServicioTransaccionInventario();

    @Override
    public void procesar(List<MovimientoInventario> movimientos, List<DetalleProducto> detallesProductos,
            String numeroDocumento, String tablaUtilizada, String usuario, InformacionAdicional informacionAdicional) throws SQLException {

        List<SentenciaSql> sqlInventario = new ArrayList<>();
        List<PonderadoPendiente> ponderados = new ArrayList<>();

        for (MovimientoInventario movimiento : movimientos) {

            sqlInventario.add(generarSqlInventario(movimiento, tablaUtilizada));

            PonderadoPendiente ponderado = servicioPonderado.calcular(
                    movimiento.getProducto(),
                    movimiento.getCantidad(),
                    movimiento.getValorProducto());
            ponderados.add(ponderado);
        }

        servicioTransaccionInventario.ejecutarIngreso(
                sqlInventario,
                ponderados,
                detallesProductos,
                usuario,
                numeroDocumento);
    }

    private SentenciaSql generarSqlInventario(MovimientoInventario movimiento, String tablaUtilizada) {
        ndProducto producto = movimiento.getProducto();
        BigDecimal cantidad = movimiento.getCantidad();

        BigDecimal inventario = Utilidades.convertirBigDecimal(producto.getInventario()).add(cantidad);
        BigDecimal fisicoInventario = Utilidades.convertirBigDecimal(producto.getFisicoInventario()).add(cantidad);
        BigDecimal compras = Utilidades.convertirBigDecimal(producto.getCompras()).add(cantidad);

        String sql = "UPDATE " + ValidadorTabla.validar(tablaUtilizada) + " SET "
                + "inventario = ?, fisicoInventario = ?, compras = ? "
                + "WHERE idSistema = ?";

        return new SentenciaSql(sql,
                UtilidadInventario.formatear(inventario),
                UtilidadInventario.formatear(fisicoInventario),
                UtilidadInventario.formatear(compras),
                producto.getIdSistema());
    }
}
