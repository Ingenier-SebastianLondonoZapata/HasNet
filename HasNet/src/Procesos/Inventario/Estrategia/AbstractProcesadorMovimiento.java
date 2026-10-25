package Procesos.Inventario.Estrategia;

import Enums.Tablas;
import Dao.Terceros.DaoBodegas;
import Utilidades.Contactos.Bodegas;
import Modelo.Inventario.DetalleProducto;
import Modelo.Inventario.InformacionAdicional;
import Modelo.Inventario.MovimientoInventario;
import Modelo.Inventario.PonderadoPendiente;
import Utilidades.BaseDatos.SentenciaSql;
import Modelo.Productos.ModeloProducto;
import Procesos.Inventario.Servicio.ServicioTransaccionInventario;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractProcesadorMovimiento implements ProcesadorMovimiento {

    protected final ServicioTransaccionInventario servicioTransaccionInventario = new ServicioTransaccionInventario();

    private final DaoBodegas daoBodegas = new DaoBodegas();

    @Override
    public void procesar(List<MovimientoInventario> movimientos, List<DetalleProducto> detallesProductos,
            String numeroDocumento, String idBodega, String usuario, InformacionAdicional informacionAdicional) throws SQLException {

        String bodega = Bodegas.resolver(idBodega);
        if (!daoBodegas.existe(bodega)) {
            throw new IllegalArgumentException("Bodega no válida: " + idBodega);
        }

        List<SentenciaSql> sentencias = new ArrayList<>();
        List<PonderadoPendiente> ponderados = new ArrayList<>();
        Map<String, ModeloProducto> productosCanonicos = new HashMap<>();

        for (MovimientoInventario movimiento : movimientos) {
            MovimientoInventario efectivo = canonizar(movimiento, productosCanonicos);
            agregarSentenciasDetalle(sentencias, efectivo, informacionAdicional);
            SentenciaSql sentenciaInventario = generarSqlInventario(efectivo, bodega, informacionAdicional);
            if (sentenciaInventario != null) {
                sentencias.add(comoUpsert(sentenciaInventario));
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

    private static final Pattern ACTUALIZACION_INVENTARIO = Pattern.compile(
            "^UPDATE (\\w+) SET (.+) WHERE idSistema = \\? AND idBodega = \\?$", Pattern.DOTALL);
    private static final Pattern ASIGNACION = Pattern.compile("^\\s*(\\w+) = \\?\\s*$");

    private SentenciaSql comoUpsert(SentenciaSql actualizacion) {
        Matcher matcher = ACTUALIZACION_INVENTARIO.matcher(actualizacion.getSql());
        if (!matcher.matches()) {
            throw new IllegalStateException("Sentencia de inventario no soportada: " + actualizacion.getSql());
        }

        List<String> columnas = new ArrayList<>();
        for (String asignacion : matcher.group(2).split(",")) {
            Matcher columna = ASIGNACION.matcher(asignacion);
            if (!columna.matches()) {
                throw new IllegalStateException("Asignación de inventario no soportada: " + asignacion);
            }
            columnas.add(columna.group(1));
        }

        List<Object> originales = actualizacion.getParametros();
        if (originales.size() != columnas.size() + 2) {
            throw new IllegalStateException("Los parámetros no coinciden con las columnas de: " + actualizacion.getSql());
        }

        List<String> actualizar = new ArrayList<>();
        for (String columna : columnas) {
            actualizar.add(columna + " = VALUES(" + columna + ")");
        }

        List<Object> parametros = new ArrayList<>();
        parametros.add(originales.get(columnas.size()));
        parametros.add(originales.get(columnas.size() + 1));
        parametros.addAll(originales.subList(0, columnas.size()));

        String sql = "INSERT INTO " + matcher.group(1) + " (idSistema, idBodega, " + String.join(", ", columnas) + ") VALUES ("
                + String.join(", ", java.util.Collections.nCopies(columnas.size() + 2, "?")) + ") ON DUPLICATE KEY UPDATE "
                + String.join(", ", actualizar);

        return new SentenciaSql(sql, parametros.toArray());
    }

    private MovimientoInventario canonizar(MovimientoInventario movimiento, Map<String, ModeloProducto> canonicos) {
        ModeloProducto producto = movimiento.getProducto();
        String idSistema = producto.getIdSistema();

        ModeloProducto canonico = canonicos.get(idSistema);
        if (canonico == null) {
            canonicos.put(idSistema, producto);
            return movimiento;
        }

        return new MovimientoInventario(canonico, movimiento.getCantidad(), movimiento.getValorProducto(),
                movimiento.getIdDetalleProducto(), movimiento.esArmado());
    }

    protected abstract SentenciaSql generarSqlInventario(MovimientoInventario movimiento, String idBodega, InformacionAdicional informacionAdicional);

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
