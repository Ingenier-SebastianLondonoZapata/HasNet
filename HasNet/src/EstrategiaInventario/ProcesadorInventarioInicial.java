package EstrategiaInventario;

import Modelo.Inventario.DetalleProducto;
import Modelo.Inventario.InformacionAdicional;
import Modelo.Inventario.MovimientoInventario;
import Modelo.Inventario.PonderadoPendiente;
import Servicio.Inventario.ServicioActualizacionPonderado;
import Servicio.Inventario.ServicioTransaccionInventario;
import Utilidades.Inventario.UtilidadInventario;
import Utilidades.Utilidades;
import clases.productos.ndProducto;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProcesadorInventarioInicial extends AbstractProcesadorMovimiento {

    private final ServicioActualizacionPonderado servicioPonderado = new ServicioActualizacionPonderado();
    private final ServicioTransaccionInventario servicioTransaccionInventario = new ServicioTransaccionInventario();

    @Override
    public void procesar(List<MovimientoInventario> movimientos, List<DetalleProducto> detallesProductos,
            String numeroDocumento, String tablaUtilizada, String usuario, InformacionAdicional informacionAdicional) throws SQLException {

        List<String> sqlInventario = new ArrayList<>();
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

    private String generarSqlInventario(MovimientoInventario movimiento, String tablaUtilizada) {
        ndProducto producto = movimiento.getProducto();
        BigDecimal cantidad = movimiento.getCantidad();

        BigDecimal inventario = Utilidades.convertirBigDecimal(producto.getInventario()).add(cantidad);
        BigDecimal fisicoInventario = Utilidades.convertirBigDecimal(producto.getFisicoInventario()).add(cantidad);
        BigDecimal inventarioInicial = Utilidades.convertirBigDecimal(producto.getInventarioInicial()).add(cantidad);

        return "UPDATE " + tablaUtilizada + " SET "
                + "inventario = '" + UtilidadInventario.formatear(inventario) + "', "
                + "fisicoInventario = '" + UtilidadInventario.formatear(fisicoInventario) + "', "
                + "inventarioInicial = '" + UtilidadInventario.formatear(inventarioInicial) + "' "
                + "WHERE idSistema = '" + producto.getIdSistema() + "'";
    }
}
