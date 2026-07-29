package inventario.estrategia;

import Enums.EstadosDetalleProducto;
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

        boolean manejaInventario = Boolean.TRUE.equals(producto.getManejaInventario());

        String columnaAcumulada = movimiento.esArmado() ? "armado" : "ventas";
        BigDecimal valorAcumulado = movimiento.esArmado()
                ? Utilidades.convertirBigDecimal(producto.getArmado()).add(cantidad)
                : Utilidades.convertirBigDecimal(producto.getVentas()).add(cantidad);

        List<Object> parametros = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE ").append(ValidadorTabla.validar(tablaUtilizada)).append(" SET ")
                .append(columnaAcumulada).append(" = ?");
        parametros.add(UtilidadInventario.formatear(valorAcumulado));

        if (movimiento.esArmado()) {
            producto.setArmado(UtilidadInventario.formatear(valorAcumulado));
        } else {
            producto.setVentas(UtilidadInventario.formatear(valorAcumulado));
        }

        if (manejaInventario) {
            BigDecimal inventario = Utilidades.convertirBigDecimal(producto.getInventario()).subtract(cantidad);
            BigDecimal fisicoInventario = Utilidades.convertirBigDecimal(producto.getFisicoInventario()).subtract(cantidad);
            sql.append(", inventario = ?, fisicoInventario = ?");
            parametros.add(UtilidadInventario.formatear(inventario));
            parametros.add(UtilidadInventario.formatear(fisicoInventario));
            producto.setInventario(UtilidadInventario.formatear(inventario));
            producto.setFisicoInventario(UtilidadInventario.formatear(fisicoInventario));
        }

        if (informacionAdicional.isVieneDesdePedido()) {
            BigDecimal pedidos = Utilidades.convertirBigDecimal(producto.getPedidos()).subtract(cantidad);
            sql.append(", pedidos = ?");
            parametros.add(UtilidadInventario.formatear(pedidos));
            producto.setPedidos(UtilidadInventario.formatear(pedidos));
        }

        if (informacionAdicional.isVieneDesdePlanSepare()) {
            BigDecimal planSepare = Utilidades.convertirBigDecimal(producto.getPlanSepare()).subtract(cantidad);
            sql.append(", planSepare = ?");
            parametros.add(UtilidadInventario.formatear(planSepare));
            producto.setPlanSepare(UtilidadInventario.formatear(planSepare));
        }

        if (informacionAdicional.isVieneDesdeOrdenServicio()) {
            BigDecimal ordenServicio = Utilidades.convertirBigDecimal(producto.getOrdenServicio()).subtract(cantidad);
            sql.append(", ordenServicio = ?");
            parametros.add(UtilidadInventario.formatear(ordenServicio));
            producto.setOrdenServicio(UtilidadInventario.formatear(ordenServicio));
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

        sentencias.add(sqlDetalleDescontarConEstado(
                movimiento.getIdDetalleProducto(),
                movimiento.getCantidad(),
                EstadosDetalleProducto.NO_DISPONIBLE.getNombre()));

    }

    @Override
    protected List<DetalleProducto> detallesAPersistir(List<DetalleProducto> detallesProductos) {
        return Collections.emptyList();
    }
}
