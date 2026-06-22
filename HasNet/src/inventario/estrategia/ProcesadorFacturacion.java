package inventario.estrategia;

import Enums.EstadosDetalleProducto;
import Enums.Tablas;
import Modelo.Inventario.DetalleProducto;
import Modelo.Inventario.InformacionAdicional;
import Modelo.Inventario.MovimientoInventario;
import Utilidades.BaseDatos.SentenciaSql;
import Utilidades.BaseDatos.ValidadorTabla;
import Utilidades.Inventario.UtilidadInventario;
import Utilidades.Utilidades;
import clases.productos.ndProducto;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProcesadorFacturacion extends AbstractProcesadorMovimiento {

    @Override
    protected SentenciaSql generarSqlInventario(MovimientoInventario movimiento, String tablaUtilizada, InformacionAdicional informacionAdicional) {
        ndProducto producto = movimiento.getProducto();
        BigDecimal cantidad = movimiento.getCantidad();

        BigDecimal fisicoInventario = Utilidades.convertirBigDecimal(producto.getFisicoInventario()).subtract(cantidad);
        BigDecimal inventario = Utilidades.convertirBigDecimal(producto.getInventario()).subtract(cantidad);
        BigDecimal ventas = Utilidades.convertirBigDecimal(producto.getVentas()).add(cantidad);

        BigDecimal pedidos = Utilidades.convertirBigDecimal(producto.getPedidos()).subtract(cantidad);
        BigDecimal planSepare = Utilidades.convertirBigDecimal(producto.getPlanSepare()).subtract(cantidad);
        BigDecimal ordenServicio = Utilidades.convertirBigDecimal(producto.getOrdenServicio()).subtract(cantidad);

        List<Object> parametros = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE ").append(ValidadorTabla.validar(tablaUtilizada)).append(" SET ")
                .append("inventario = ?, fisicoInventario = ?, ventas = ?");
        parametros.add(UtilidadInventario.formatear(inventario));
        parametros.add(UtilidadInventario.formatear(fisicoInventario));
        parametros.add(UtilidadInventario.formatear(ventas));

        if (informacionAdicional.isVieneDesdePedido()) {
            sql.append(", pedidos = ?");
            parametros.add(UtilidadInventario.formatear(pedidos));
        }

        if (informacionAdicional.isVieneDesdePlanSepare()) {
            sql.append(", planSepare = ?");
            parametros.add(UtilidadInventario.formatear(planSepare));
        }

        if (informacionAdicional.isVieneDesdeOrdenServicio()) {
            sql.append(", ordenServicio = ?");
            parametros.add(UtilidadInventario.formatear(ordenServicio));
        }

        sql.append(" WHERE idSistema = ?");
        parametros.add(producto.getIdSistema());

        return new SentenciaSql(sql.toString(), parametros.toArray());
    }

    @Override
    protected void agregarSentenciasDetalle(List<SentenciaSql> sentencias, MovimientoInventario movimiento, InformacionAdicional informacionAdicional) {
        if (movimiento.getIdDetalleProducto().isEmpty()) {
            return;
        }

        boolean aplicarSqlDetallado = !(informacionAdicional.isVieneDesdePedido()
                || informacionAdicional.isVieneDesdePlanSepare()
                || informacionAdicional.isVieneDesdeOrdenServicio());

        if (aplicarSqlDetallado) {
            sentencias.add(sqlDetalleDescontarConEstado(
                    movimiento.getIdDetalleProducto(),
                    movimiento.getCantidad(),
                    EstadosDetalleProducto.NO_DISPONIBLE.getNombre()));
        } else {
            sentencias.add(generarSqlActualizarEstadoDetalle(movimiento));
        }
    }

    @Override
    protected List<DetalleProducto> detallesAPersistir(List<DetalleProducto> detallesProductos) {
        return Collections.emptyList();
    }

    private SentenciaSql generarSqlActualizarEstadoDetalle(MovimientoInventario movimiento) {
        String sql = "UPDATE " + Tablas.DETALLE_PRODUCTO.getNombre() + " SET "
                + "estado = ? WHERE Id = ? AND cantidadDisponible <= 0";

        return new SentenciaSql(sql,
                EstadosDetalleProducto.NO_DISPONIBLE.getNombre(),
                movimiento.getIdDetalleProducto());
    }
}
