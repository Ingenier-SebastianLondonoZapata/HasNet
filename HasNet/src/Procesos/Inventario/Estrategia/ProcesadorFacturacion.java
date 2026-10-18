package Procesos.Inventario.Estrategia;

import Enums.Tablas;
import Enums.EstadosDetalleProducto;
import Modelo.Inventario.DetalleProducto;
import Modelo.Inventario.InformacionAdicional;
import Modelo.Inventario.MovimientoInventario;
import Utilidades.BaseDatos.SentenciaSql;
import Modelo.Productos.ModeloProducto;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProcesadorFacturacion extends AbstractProcesadorMovimiento {

    @Override
    protected SentenciaSql generarSqlInventario(MovimientoInventario movimiento, String idBodega, InformacionAdicional informacionAdicional) {
        ModeloProducto producto = movimiento.getProducto();
        BigDecimal cantidad = movimiento.getCantidad();

        boolean manejaInventario = Boolean.TRUE.equals(producto.getManejaInventario());

        String columnaAcumulada = movimiento.esArmado() ? "armado" : "ventas";
        BigDecimal valorAcumulado = movimiento.esArmado()
                ? producto.getArmado().add(cantidad)
                : producto.getVentas().add(cantidad);

        List<Object> parametros = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE ").append(Tablas.INVENTARIO.getNombre()).append(" SET ")
                .append(columnaAcumulada).append(" = ?");
        parametros.add(valorAcumulado);

        if (movimiento.esArmado()) {
            producto.setArmado(valorAcumulado);
        } else {
            producto.setVentas(valorAcumulado);
        }

        if (manejaInventario) {
            BigDecimal inventario = producto.getInventario().subtract(cantidad);
            BigDecimal fisicoInventario = producto.getFisicoInventario().subtract(cantidad);
            sql.append(", inventario = ?, fisicoInventario = ?");
            parametros.add(inventario);
            parametros.add(fisicoInventario);
            producto.setInventario(inventario);
            producto.setFisicoInventario(fisicoInventario);
        }

        if (informacionAdicional.isVieneDesdePedido()) {
            BigDecimal pedidos = producto.getPedidos().subtract(cantidad);
            sql.append(", pedidos = ?");
            parametros.add(pedidos);
            producto.setPedidos(pedidos);
        }

        if (informacionAdicional.isVieneDesdePlanSepare()) {
            BigDecimal planSepare = producto.getPlanSepare().subtract(cantidad);
            sql.append(", planSepare = ?");
            parametros.add(planSepare);
            producto.setPlanSepare(planSepare);
        }

        if (informacionAdicional.isVieneDesdeOrdenServicio()) {
            BigDecimal ordenServicio = producto.getOrdenServicio().subtract(cantidad);
            sql.append(", ordenServicio = ?");
            parametros.add(ordenServicio);
            producto.setOrdenServicio(ordenServicio);
        }

        sql.append(" WHERE idSistema = ? AND idBodega = ?");
        parametros.add(producto.getIdSistema());
        parametros.add(idBodega);

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
