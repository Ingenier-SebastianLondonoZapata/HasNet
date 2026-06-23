package inventario.estrategia;

import Enums.Tablas;
import Modelo.Inventario.DetalleProducto;
import Modelo.Inventario.InformacionAdicional;
import Modelo.Inventario.MovimientoInventario;
import Modelo.Inventario.PonderadoPendiente;
import Utilidades.BaseDatos.SentenciaSql;
import clases.productos.ndProducto;
import inventario.servicio.ServicioTransaccionInventario;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractProcesadorMovimiento implements ProcesadorMovimiento {

    protected final ServicioTransaccionInventario servicioTransaccionInventario = new ServicioTransaccionInventario();

    @Override
    public void procesar(List<MovimientoInventario> movimientos, List<DetalleProducto> detallesProductos,
            String numeroDocumento, String tablaUtilizada, String usuario, InformacionAdicional informacionAdicional) throws SQLException {

        List<SentenciaSql> sentencias = new ArrayList<>();
        List<PonderadoPendiente> ponderados = new ArrayList<>();
        Map<String, ndProducto> productosCanonicos = new HashMap<>();

        for (MovimientoInventario movimiento : movimientos) {
            MovimientoInventario efectivo = canonizar(movimiento, productosCanonicos);
            agregarSentenciasDetalle(sentencias, efectivo, informacionAdicional);
            SentenciaSql sentenciaInventario = generarSqlInventario(efectivo, tablaUtilizada, informacionAdicional);
            if (sentenciaInventario != null) {
                sentencias.add(sentenciaInventario);
            }
            agregarPonderado(ponderados, efectivo);
        }

        agregarSentenciasFinales(sentencias, numeroDocumento);

        servicioTransaccionInventario.ejecutarIngreso(
                sentencias,
                ponderados,
                detallesAPersistir(detallesProductos),
                usuario,
                numeroDocumento);
    }

    private MovimientoInventario canonizar(MovimientoInventario movimiento, Map<String, ndProducto> canonicos) {
        ndProducto producto = movimiento.getProducto();
        String idSistema = producto.getIdSistema();

        ndProducto canonico = canonicos.get(idSistema);
        if (canonico == null) {
            canonicos.put(idSistema, producto);
            return movimiento;
        }

        return new MovimientoInventario(canonico, movimiento.getCantidad(), movimiento.getValorProducto(),
                movimiento.getIdDetalleProducto(), movimiento.esArmado());
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
