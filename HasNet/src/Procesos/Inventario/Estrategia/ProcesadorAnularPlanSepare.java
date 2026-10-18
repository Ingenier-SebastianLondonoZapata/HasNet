package Procesos.Inventario.Estrategia;

import Enums.Tablas;
import Enums.EstadosDetalleProducto;
import Modelo.Inventario.InformacionAdicional;
import Modelo.Inventario.MovimientoInventario;
import Utilidades.BaseDatos.SentenciaSql;
import Modelo.Productos.ModeloProducto;
import java.math.BigDecimal;
import java.util.List;

public class ProcesadorAnularPlanSepare extends AbstractProcesadorMovimiento {

    @Override
    protected SentenciaSql generarSqlInventario(MovimientoInventario movimiento, String idBodega, InformacionAdicional informacionAdicional) {
        ModeloProducto producto = movimiento.getProducto();
        BigDecimal cantidad = movimiento.getCantidad();

        BigDecimal planSepare = producto.getPlanSepare().subtract(cantidad);
        producto.setPlanSepare(planSepare);

        if (!Boolean.TRUE.equals(producto.getManejaInventario())) {
            String sql = "UPDATE " + Tablas.INVENTARIO.getNombre() + " SET planSepare = ? WHERE idSistema = ? AND idBodega = ?";
            return new SentenciaSql(sql, planSepare, producto.getIdSistema(), idBodega);
        }

        BigDecimal fisicoInventario = producto.getFisicoInventario().add(cantidad);
        producto.setFisicoInventario(fisicoInventario);

        String sql = "UPDATE " + Tablas.INVENTARIO.getNombre() + " SET "
                + "fisicoInventario = ?, planSepare = ? "
                + "WHERE idSistema = ? AND idBodega = ?";

        return new SentenciaSql(sql,
                fisicoInventario,
                planSepare,
                producto.getIdSistema(), idBodega);
    }

    @Override
    protected void agregarSentenciasDetalle(List<SentenciaSql> sentencias, MovimientoInventario movimiento, InformacionAdicional informacionAdicional) {
        if (!movimiento.getIdDetalleProducto().isEmpty()) {
            sentencias.add(sqlDetalleReintegrarConEstado(
                    movimiento.getIdDetalleProducto(),
                    movimiento.getCantidad(),
                    EstadosDetalleProducto.DISPONIBLE.getNombre()));
        }
    }
}
