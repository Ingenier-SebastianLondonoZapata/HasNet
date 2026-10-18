package Procesos.Inventario.Estrategia;

import Enums.Tablas;
import Modelo.Inventario.InformacionAdicional;
import Modelo.Inventario.MovimientoInventario;
import Modelo.Inventario.PonderadoPendiente;
import Utilidades.BaseDatos.SentenciaSql;
import Modelo.Productos.ModeloProducto;
import Procesos.Inventario.Servicio.ServicioActualizacionPonderado;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class ProcesadorCompra extends AbstractProcesadorMovimiento {

    private final ServicioActualizacionPonderado servicioPonderado = new ServicioActualizacionPonderado();

    @Override
    protected SentenciaSql generarSqlInventario(MovimientoInventario movimiento, String idBodega, InformacionAdicional informacionAdicional) {
        ModeloProducto producto = movimiento.getProducto();
        BigDecimal cantidad = movimiento.getCantidad();

        BigDecimal compras = producto.getCompras().add(cantidad);
        producto.setCompras(compras);

        if (!Boolean.TRUE.equals(producto.getManejaInventario())) {
            String sql = "UPDATE " + Tablas.INVENTARIO.getNombre() + " SET compras = ? WHERE idSistema = ? AND idBodega = ?";
            return new SentenciaSql(sql, compras, producto.getIdSistema(), idBodega);
        }

        BigDecimal inventario = producto.getInventario().add(cantidad);
        BigDecimal fisicoInventario = producto.getFisicoInventario().add(cantidad);
        producto.setInventario(inventario);
        producto.setFisicoInventario(fisicoInventario);

        String sql = "UPDATE " + Tablas.INVENTARIO.getNombre() + " SET "
                + "inventario = ?, fisicoInventario = ?, compras = ? "
                + "WHERE idSistema = ? AND idBodega = ?";

        return new SentenciaSql(sql,
                inventario,
                fisicoInventario,
                compras,
                producto.getIdSistema(), 
                idBodega);
    }

    @Override
    protected void agregarPonderado(List<PonderadoPendiente> ponderados, MovimientoInventario movimiento) throws SQLException {
        ponderados.add(servicioPonderado.calcular(
                movimiento.getProducto(),
                movimiento.getCantidad(),
                movimiento.getValorProducto()));
    }
}
