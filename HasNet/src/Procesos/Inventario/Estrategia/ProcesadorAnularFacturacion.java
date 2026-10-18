package Procesos.Inventario.Estrategia;

import Enums.Tablas;
import Enums.EstadosDetalleProducto;
import Enums.TipoProducto;
import Modelo.Inventario.DetalleProducto;
import Modelo.Inventario.InformacionAdicional;
import Modelo.Inventario.MovimientoInventario;
import Utilidades.BaseDatos.SentenciaSql;
import Modelo.Productos.ModeloProducto;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

public class ProcesadorAnularFacturacion extends AbstractProcesadorMovimiento {

    @Override
    protected SentenciaSql generarSqlInventario(MovimientoInventario movimiento, String idBodega, InformacionAdicional informacionAdicional) {
        ModeloProducto producto = movimiento.getProducto();
        BigDecimal cantidad = movimiento.getCantidad();
        BigDecimal anulacion = producto.getAnulada().add(cantidad);
        producto.setAnulada(anulacion);
        String tipoProducto = producto.getUsuario();

        boolean descuentaInventario = TipoProducto.GENERICO.getValue().equals(tipoProducto) || TipoProducto.PRODUCTO_COSTEADO.getValue().equals(tipoProducto);

        if (descuentaInventario) {
            if (!Boolean.TRUE.equals(producto.getManejaInventario())) {
                String sql = "UPDATE " + Tablas.INVENTARIO.getNombre() + " SET anulacion = ? WHERE idSistema = ? AND idBodega = ?";
                return new SentenciaSql(sql, anulacion, producto.getIdSistema(), idBodega);
            }

            BigDecimal inventario = producto.getInventario().add(cantidad);
            BigDecimal fisicoInventario = producto.getFisicoInventario().add(cantidad);
            producto.setInventario(inventario);
            producto.setFisicoInventario(fisicoInventario);

            String sql = "UPDATE " + Tablas.INVENTARIO.getNombre() + " SET "
                    + "inventario = ?, fisicoInventario = ?, anulacion = ? "
                    + "WHERE idSistema = ? AND idBodega = ?";

            return new SentenciaSql(sql,
                    inventario,
                    fisicoInventario,
                    anulacion,
                    producto.getIdSistema(), idBodega);
        }

        BigDecimal costeo = producto.getCosteo();
        if (TipoProducto.PRODUCTO_COSTEADO.getValue().equals(tipoProducto)) {
            costeo = costeo.subtract(cantidad);
        }
        producto.setCosteo(costeo);

        String sql = "UPDATE " + Tablas.INVENTARIO.getNombre() + " SET "
                + "anulacion = ?, costeo = ? "
                + "WHERE idSistema = ? AND idBodega = ?";

        return new SentenciaSql(sql,
                anulacion,
                costeo,
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
