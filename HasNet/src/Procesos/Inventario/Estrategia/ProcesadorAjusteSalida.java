package Procesos.Inventario.Estrategia;

import Enums.Tablas;
import Enums.EstadosDetalleProducto;
import Modelo.Inventario.InformacionAdicional;
import Modelo.Inventario.MovimientoInventario;
import Utilidades.BaseDatos.SentenciaSql;
import Modelo.Productos.ModeloProducto;
import java.math.BigDecimal;
import java.util.List;

public class ProcesadorAjusteSalida extends AbstractProcesadorMovimiento {

    @Override
    protected SentenciaSql generarSqlInventario(MovimientoInventario movimiento, String idBodega, InformacionAdicional informacionAdicional) {
        ModeloProducto producto = movimiento.getProducto();
        BigDecimal cantidad = movimiento.getCantidad();

        BigDecimal ajusteSalida = producto.getAjusteSalida().add(cantidad);
        producto.setAjusteSalida(ajusteSalida);

        if (!Boolean.TRUE.equals(producto.getManejaInventario())) {
            String sql = "UPDATE " + Tablas.INVENTARIO.getNombre() + " SET ajusteSalida = ? WHERE idSistema = ? AND idBodega = ?";
            return new SentenciaSql(sql, ajusteSalida, producto.getIdSistema(), idBodega);
        }

        BigDecimal inventario = producto.getInventario().subtract(cantidad);
        BigDecimal fisicoInventario = producto.getFisicoInventario().subtract(cantidad);
        producto.setInventario(inventario);
        producto.setFisicoInventario(fisicoInventario);

        String sql = "UPDATE " + Tablas.INVENTARIO.getNombre() + " SET "
                + "inventario = ?, fisicoInventario = ?, ajusteSalida = ? "
                + "WHERE idSistema = ? AND idBodega = ?";

        return new SentenciaSql(sql,
                inventario,
                fisicoInventario,
                ajusteSalida,
                producto.getIdSistema(), idBodega);
    }

    @Override
    protected void agregarSentenciasDetalle(List<SentenciaSql> sentencias, MovimientoInventario movimiento, InformacionAdicional informacionAdicional) {
        if (!movimiento.getIdDetalleProducto().isEmpty()) {
            sentencias.add(sqlDetalleDescontarConEstado(
                    movimiento.getIdDetalleProducto(),
                    movimiento.getCantidad(),
                    EstadosDetalleProducto.NO_DISPONIBLE.getNombre()));
        }
    }
}
