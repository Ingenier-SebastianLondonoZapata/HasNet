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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProcesadorMesa extends AbstractProcesadorMovimiento {

    @Override
    protected SentenciaSql generarSqlInventario(MovimientoInventario movimiento, String idBodega, InformacionAdicional informacionAdicional) {
        ModeloProducto producto = movimiento.getProducto();
        BigDecimal cantidad = movimiento.getCantidad();

        boolean esProductoNormal = TipoProducto.GENERICO.getValue().equals(producto.getUsuario());
        boolean manejaInventario = Boolean.TRUE.equals(producto.getManejaInventario());

        List<Object> parametros = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE ").append(Tablas.INVENTARIO.getNombre()).append(" SET ");

        if (esProductoNormal) {
            BigDecimal congelada = producto.getCongelada().add(cantidad);
            sql.append("congelada = ?");
            parametros.add(congelada);
            producto.setCongelada(congelada);
        }

        if (manejaInventario) {
            BigDecimal fisicoInventario = producto.getFisicoInventario().subtract(cantidad);
            if (!parametros.isEmpty()) {
                sql.append(", ");
            }
            sql.append("fisicoInventario = ?");
            parametros.add(fisicoInventario);
            producto.setFisicoInventario(fisicoInventario);
        }

        if (parametros.isEmpty()) {
            return null;
        }

        sql.append(" WHERE idSistema = ? AND idBodega = ?");
        parametros.add(producto.getIdSistema());
        parametros.add(idBodega);

        return new SentenciaSql(sql.toString(), parametros.toArray());
    }

    @Override
    protected void agregarSentenciasDetalle(List<SentenciaSql> sentencias, MovimientoInventario movimiento, InformacionAdicional informacionAdicional) {
        if (!movimiento.getIdDetalleProducto().isEmpty()) {
            sentencias.add(sqlDetalleDescontarConEstado(
                    movimiento.getIdDetalleProducto(),
                    movimiento.getCantidad(),
                    EstadosDetalleProducto.EN_MESA.getNombre()));
        }
    }

    @Override
    protected List<DetalleProducto> detallesAPersistir(List<DetalleProducto> detallesProductos) {
        return Collections.emptyList();
    }
}
