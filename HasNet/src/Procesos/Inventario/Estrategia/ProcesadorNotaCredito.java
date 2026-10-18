package Procesos.Inventario.Estrategia;

import Enums.Tablas;
import Enums.EstadosDetalleProducto;
import Modelo.Inventario.DetalleProducto;
import Modelo.Inventario.InformacionAdicional;
import Modelo.Inventario.MovimientoInventario;
import Utilidades.BaseDatos.SentenciaSql;
import Modelo.Productos.ModeloProducto;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

public class ProcesadorNotaCredito extends AbstractProcesadorMovimiento {

    @Override
    protected SentenciaSql generarSqlInventario(MovimientoInventario movimiento, String idBodega, InformacionAdicional informacionAdicional) {
        ModeloProducto producto = movimiento.getProducto();
        BigDecimal cantidad = movimiento.getCantidad();

        BigDecimal notaCredito = producto.getNotaCredito().add(cantidad);
        producto.setNotaCredito(notaCredito);

        if (!Boolean.TRUE.equals(producto.getManejaInventario())) {
            String sql = "UPDATE " + Tablas.INVENTARIO.getNombre() + " SET notaCredito = ? WHERE idSistema = ? AND idBodega = ?";
            return new SentenciaSql(sql, notaCredito, producto.getIdSistema(), idBodega);
        }

        BigDecimal inventario = producto.getInventario().add(cantidad);
        BigDecimal fisicoInventario = producto.getFisicoInventario().add(cantidad);
        producto.setInventario(inventario);
        producto.setFisicoInventario(fisicoInventario);

        String sql = "UPDATE " + Tablas.INVENTARIO.getNombre() + " SET "
                + "inventario = ?, fisicoInventario = ?, notaCredito = ? "
                + "WHERE idSistema = ? AND idBodega = ?";

        return new SentenciaSql(sql,
                inventario,
                fisicoInventario,
                notaCredito,
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

    @Override
    protected List<DetalleProducto> detallesAPersistir(List<DetalleProducto> detallesProductos) {
        return Collections.emptyList();
    }
}
