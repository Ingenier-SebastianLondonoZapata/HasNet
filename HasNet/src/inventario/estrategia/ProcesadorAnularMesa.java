package inventario.estrategia;

import Enums.EstadosDetalleProducto;
import Enums.TipoProducto;
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

public class ProcesadorAnularMesa extends AbstractProcesadorMovimiento {

    @Override
    protected SentenciaSql generarSqlInventario(MovimientoInventario movimiento, String tablaUtilizada, InformacionAdicional informacionAdicional) {
        ndProducto producto = movimiento.getProducto();
        BigDecimal cantidad = movimiento.getCantidad();

        boolean esProductoNormal = TipoProducto.GENERICO.getValue().equals(producto.getUsuario());
        boolean manejaInventario = Boolean.TRUE.equals(producto.getManejaInventario());

        List<Object> parametros = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE ").append(ValidadorTabla.validar(tablaUtilizada)).append(" SET ");

        if (esProductoNormal) {
            BigDecimal congelada = Utilidades.convertirBigDecimal(producto.getCongelada()).subtract(cantidad);
            sql.append("congelada = ?");
            parametros.add(UtilidadInventario.formatear(congelada));
            producto.setCongelada(UtilidadInventario.formatear(congelada));
        }

        if (manejaInventario) {
            BigDecimal fisicoInventario = Utilidades.convertirBigDecimal(producto.getFisicoInventario()).add(cantidad);
            if (!parametros.isEmpty()) {
                sql.append(", ");
            }
            sql.append("fisicoInventario = ?");
            parametros.add(UtilidadInventario.formatear(fisicoInventario));
            producto.setFisicoInventario(UtilidadInventario.formatear(fisicoInventario));
        }

        if (parametros.isEmpty()) {
            return null;
        }

        sql.append(" WHERE idSistema = ?");
        parametros.add(producto.getIdSistema());

        return new SentenciaSql(sql.toString(), parametros.toArray());
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
