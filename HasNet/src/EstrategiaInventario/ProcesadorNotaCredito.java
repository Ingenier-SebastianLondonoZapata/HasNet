package EstrategiaInventario;

import Enums.EstadosDetalleProducto;
import Enums.Tablas;
import Modelo.Inventario.DetalleProducto;
import Modelo.Inventario.InformacionAdicional;
import Modelo.Inventario.MovimientoInventario;
import Modelo.Inventario.PonderadoPendiente;
import Servicio.Inventario.ServicioTransaccionInventario;
import Utilidades.Inventario.UtilidadInventario;
import Utilidades.Utilidades;
import clases.productos.ndProducto;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProcesadorNotaCredito extends AbstractProcesadorMovimiento {

    private final ServicioTransaccionInventario servicioTransaccionInventario = new ServicioTransaccionInventario();

    @Override
    public void procesar(List<MovimientoInventario> movimientos, List<DetalleProducto> detallesProductos, String numeroDocumento,
            String tablaUtilizada, String usuario, InformacionAdicional informacionAdicional) throws SQLException {

        List<String> sqlInventario = new ArrayList<>();

        for (MovimientoInventario movimiento : movimientos) {
            if (!movimiento.getIdDetalleProducto().isEmpty()) {
                sqlInventario.add(generarSqlDetalleInventario(movimiento));
            }

            sqlInventario.add(generarSqlInventario(movimiento, tablaUtilizada));
        }

        servicioTransaccionInventario.ejecutarIngreso(
                sqlInventario,
                new ArrayList<PonderadoPendiente>(),
                new ArrayList<DetalleProducto>(),
                usuario,
                numeroDocumento);
    }

    private String generarSqlInventario(MovimientoInventario movimiento, String tablaUtilizada) {
        ndProducto producto = movimiento.getProducto();
        BigDecimal cantidad = movimiento.getCantidad();

        BigDecimal inventario = Utilidades.convertirBigDecimal(producto.getInventario()).add(cantidad);
        BigDecimal fisicoInventario = Utilidades.convertirBigDecimal(producto.getFisicoInventario()).add(cantidad);
        BigDecimal notaCredito = Utilidades.convertirBigDecimal(producto.getNc()).add(cantidad);

        return "UPDATE " + tablaUtilizada + " SET "
                + "inventario = '" + UtilidadInventario.formatear(inventario) + "', "
                + "fisicoInventario = '" + UtilidadInventario.formatear(fisicoInventario) + "', "
                + "nc = '" + UtilidadInventario.formatear(notaCredito) + "' "
                + "WHERE idSistema = '" + producto.getIdSistema() + "'";
    }

    private String generarSqlDetalleInventario(MovimientoInventario movimiento) {
        BigDecimal cantidad = movimiento.getCantidad();
        String idDetalleProducto = movimiento.getIdDetalleProducto();

        return "UPDATE " + Tablas.DETALLE_PRODUCTO.getNombre() + " SET "
                + "cantidadDisponible = cantidadDisponible + " + cantidad + ", "
                + "estado = '" + EstadosDetalleProducto.DISPONIBLE.getNombre() + "' "
                + "WHERE Id = '" + idDetalleProducto + "'";
    }
}
