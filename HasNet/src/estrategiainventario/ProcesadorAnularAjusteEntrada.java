package estrategiainventario;

import Enums.EstadosDetalleProducto;
import Enums.Tablas;
import Modelo.Inventario.DetalleProducto;
import Modelo.Inventario.InformacionAdicional;
import Modelo.Inventario.MovimientoInventario;
import Modelo.Inventario.PonderadoPendiente;
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

public class ProcesadorAnularAjusteEntrada extends AbstractProcesadorMovimiento {

    private final ServicioTransaccionInventario servicioTransaccionInventario = new ServicioTransaccionInventario();

    @Override
    public void procesar(List<MovimientoInventario> movimientos, List<DetalleProducto> detallesProductos,
            String numeroDocumento, String tablaUtilizada, String usuario, InformacionAdicional informacionAdicional) throws SQLException {

        List<SentenciaSql> sqlInventario = new ArrayList<>();
        List<PonderadoPendiente> ponderados = new ArrayList<>();

        for (MovimientoInventario movimiento : movimientos) {
            sqlInventario.add(generarSqlInventario(movimiento, tablaUtilizada));
        }

        sqlInventario.add(generarSqlInventarioDetalle(numeroDocumento));

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

        BigDecimal inventario = Utilidades.convertirBigDecimal(producto.getInventario()).subtract(cantidad);
        BigDecimal fisicoInventario = Utilidades.convertirBigDecimal(producto.getFisicoInventario()).subtract(cantidad);
        BigDecimal ajusteEntrada = Utilidades.convertirBigDecimal(producto.getAjusteEntrada()).subtract(cantidad);

        String sql = "UPDATE " + ValidadorTabla.validar(tablaUtilizada) + " SET "
                + "inventario = ?, fisicoInventario = ?, ajusteEntrada = ? "
                + "WHERE idSistema = ?";

        return new SentenciaSql(sql,
                UtilidadInventario.formatear(inventario),
                UtilidadInventario.formatear(fisicoInventario),
                UtilidadInventario.formatear(ajusteEntrada),
                producto.getIdSistema());
    }

    public SentenciaSql generarSqlInventarioDetalle(String idAjuste) {
        String sql = "UPDATE " + Tablas.DETALLE_PRODUCTO.getNombre()
                + " SET estado = ? WHERE numIngreso = ?";

        return new SentenciaSql(sql,
                EstadosDetalleProducto.ANULADO.getNombre(),
                idAjuste);
    }
}
