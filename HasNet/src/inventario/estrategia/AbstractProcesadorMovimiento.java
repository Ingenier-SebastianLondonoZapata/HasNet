package inventario.estrategia;

import Enums.Tablas;
import Modelo.Inventario.DetalleProducto;
import Modelo.Inventario.InformacionAdicional;
import Modelo.Inventario.MovimientoInventario;
import Modelo.Inventario.PonderadoPendiente;
import Utilidades.BaseDatos.SentenciaSql;
import inventario.servicio.ServicioTransaccionInventario;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractProcesadorMovimiento implements ProcesadorMovimiento {

    protected final ServicioTransaccionInventario servicioTransaccionInventario = new ServicioTransaccionInventario();

    @Override
    public void procesar(List<MovimientoInventario> movimientos, List<DetalleProducto> detallesProductos,
            String numeroDocumento, String tablaUtilizada, String usuario, InformacionAdicional informacionAdicional) throws SQLException {

        List<SentenciaSql> sentencias = new ArrayList<>();
        List<PonderadoPendiente> ponderados = new ArrayList<>();

        for (MovimientoInventario movimiento : movimientos) {
            agregarSentenciasDetalle(sentencias, movimiento, informacionAdicional);
            sentencias.add(generarSqlInventario(movimiento, tablaUtilizada, informacionAdicional));
            agregarPonderado(ponderados, movimiento);
        }

        agregarSentenciasFinales(sentencias, numeroDocumento);

        servicioTransaccionInventario.ejecutarIngreso(
                sentencias,
                ponderados,
                detallesAPersistir(detallesProductos),
                usuario,
                numeroDocumento);
    }

    protected abstract SentenciaSql generarSqlInventario(MovimientoInventario movimiento, String tablaUtilizada, InformacionAdicional informacionAdicional);

    protected void agregarSentenciasDetalle(List<SentenciaSql> sentencias, MovimientoInventario movimiento,
            InformacionAdicional informacionAdicional) {
    }

    protected void agregarPonderado(List<PonderadoPendiente> ponderados, MovimientoInventario movimiento) throws SQLException {
    }

    protected void agregarSentenciasFinales(List<SentenciaSql> sentencias, String numeroDocumento) {
    }

    protected List<DetalleProducto> detallesAPersistir(List<DetalleProducto> detallesProductos) {
        return detallesProductos;
    }

    protected SentenciaSql sqlDetalleDescontarConEstado(String idDetalleProducto, BigDecimal cantidad, String estadoSiSeAgota) {
        String sql = "UPDATE " + Tablas.DETALLE_PRODUCTO.getNombre()
                + " SET cantidadDisponible = cantidadDisponible - ?, "
                + "estado = CASE WHEN (cantidadDisponible - ?) <= 0 THEN ? ELSE estado END "
                + "WHERE Id = ?";

        return new SentenciaSql(sql, cantidad, cantidad, estadoSiSeAgota, idDetalleProducto);
    }

    protected SentenciaSql sqlDetalleReintegrarConEstado(String idDetalleProducto, BigDecimal cantidad, String estado) {
        String sql = "UPDATE " + Tablas.DETALLE_PRODUCTO.getNombre()
                + " SET cantidadDisponible = cantidadDisponible + ?, estado = ? "
                + "WHERE Id = ?";

        return new SentenciaSql(sql, cantidad, estado, idDetalleProducto);
    }
}
