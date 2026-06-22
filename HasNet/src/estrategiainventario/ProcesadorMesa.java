package estrategiainventario;

import Enums.EstadosDetalleProducto;
import Enums.Tablas;
import Enums.TipoProducto;
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

public class ProcesadorMesa extends AbstractProcesadorMovimiento {

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

        BigDecimal fisicoInventario = Utilidades.convertirBigDecimal(producto.getFisicoInventario()).subtract(cantidad);
        BigDecimal congelada = Utilidades.convertirBigDecimal(producto.getCongelada()).add(cantidad);
        boolean esProductoNormal = TipoProducto.GENERICO.getValue().equals(producto.getUsuario());

        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE ").append(tablaUtilizada).append(" SET ");

        if (esProductoNormal) {
            sql.append("congelada = '")
                    .append(UtilidadInventario.formatear(congelada))
                    .append("', ");
        }

        sql.append("fisicoInventario = '")
                .append(UtilidadInventario.formatear(fisicoInventario))
                .append("' WHERE idSistema = '")
                .append(producto.getIdSistema())
                .append("'");

        return sql.toString();
    }

    private String generarSqlDetalleInventario(MovimientoInventario movimiento) {
        BigDecimal cantidad = movimiento.getCantidad();
        String idDetalleProducto = movimiento.getIdDetalleProducto();

        return "UPDATE " + Tablas.DETALLE_PRODUCTO.getNombre() + " SET "
                + "cantidadDisponible = cantidadDisponible - " + cantidad + ", "
                + "estado = CASE "
                + "WHEN (cantidadDisponible - " + cantidad + ") <= 0 THEN '" + EstadosDetalleProducto.EN_MESA.getNombre() + "' ELSE estado "
                + "END "
                + "WHERE Id = '" + idDetalleProducto + "'";
    }
}
