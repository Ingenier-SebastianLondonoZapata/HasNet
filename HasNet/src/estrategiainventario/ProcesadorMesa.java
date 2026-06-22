package estrategiainventario;

import Enums.EstadosDetalleProducto;
import Enums.Tablas;
import Enums.TipoProducto;
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

public class ProcesadorMesa extends AbstractProcesadorMovimiento {

    private final ServicioTransaccionInventario servicioTransaccionInventario = new ServicioTransaccionInventario();

    @Override
    public void procesar(List<MovimientoInventario> movimientos, List<DetalleProducto> detallesProductos, String numeroDocumento,
            String tablaUtilizada, String usuario, InformacionAdicional informacionAdicional) throws SQLException {

        List<SentenciaSql> sqlInventario = new ArrayList<>();

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

    private SentenciaSql generarSqlInventario(MovimientoInventario movimiento, String tablaUtilizada) {
        ndProducto producto = movimiento.getProducto();
        BigDecimal cantidad = movimiento.getCantidad();

        BigDecimal fisicoInventario = Utilidades.convertirBigDecimal(producto.getFisicoInventario()).subtract(cantidad);
        BigDecimal congelada = Utilidades.convertirBigDecimal(producto.getCongelada()).add(cantidad);
        boolean esProductoNormal = TipoProducto.GENERICO.getValue().equals(producto.getUsuario());

        List<Object> parametros = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE ").append(ValidadorTabla.validar(tablaUtilizada)).append(" SET ");

        if (esProductoNormal) {
            sql.append("congelada = ?, ");
            parametros.add(UtilidadInventario.formatear(congelada));
        }

        sql.append("fisicoInventario = ? WHERE idSistema = ?");
        parametros.add(UtilidadInventario.formatear(fisicoInventario));
        parametros.add(producto.getIdSistema());

        return new SentenciaSql(sql.toString(), parametros.toArray());
    }

    private SentenciaSql generarSqlDetalleInventario(MovimientoInventario movimiento) {
        BigDecimal cantidad = movimiento.getCantidad();
        String idDetalleProducto = movimiento.getIdDetalleProducto();

        String sql = "UPDATE " + Tablas.DETALLE_PRODUCTO.getNombre() + " SET "
                + "cantidadDisponible = cantidadDisponible - ?, "
                + "estado = CASE WHEN (cantidadDisponible - ?) <= 0 THEN ? ELSE estado END "
                + "WHERE Id = ?";

        return new SentenciaSql(sql,
                cantidad,
                cantidad,
                EstadosDetalleProducto.EN_MESA.getNombre(),
                idDetalleProducto);
    }
}
