package inventario.estrategia;

import Modelo.Inventario.InformacionAdicional;
import Modelo.Inventario.MovimientoInventario;
import Utilidades.BaseDatos.SentenciaSql;
import Utilidades.BaseDatos.ValidadorTabla;
import Utilidades.Inventario.UtilidadInventario;
import Utilidades.Utilidades;
import clases.productos.ndProducto;
import java.math.BigDecimal;

public class ProcesadorAjusteEntrada extends AbstractProcesadorMovimiento {

    @Override
    protected SentenciaSql generarSqlInventario(MovimientoInventario movimiento, String tablaUtilizada, InformacionAdicional informacionAdicional) {
        ndProducto producto = movimiento.getProducto();
        BigDecimal cantidad = movimiento.getCantidad();

        BigDecimal ajusteEntrada = Utilidades.convertirBigDecimal(producto.getAjusteEntrada()).add(cantidad);
        producto.setAjusteEntrada(UtilidadInventario.formatear(ajusteEntrada));

        if (!Boolean.TRUE.equals(producto.getManejaInventario())) {
            String sql = "UPDATE " + ValidadorTabla.validar(tablaUtilizada) + " SET ajusteEntrada = ? WHERE idSistema = ?";
            return new SentenciaSql(sql, UtilidadInventario.formatear(ajusteEntrada), producto.getIdSistema());
        }

        BigDecimal inventario = Utilidades.convertirBigDecimal(producto.getInventario()).add(cantidad);
        BigDecimal fisicoInventario = Utilidades.convertirBigDecimal(producto.getFisicoInventario()).add(cantidad);
        producto.setInventario(UtilidadInventario.formatear(inventario));
        producto.setFisicoInventario(UtilidadInventario.formatear(fisicoInventario));

        String sql = "UPDATE " + ValidadorTabla.validar(tablaUtilizada) + " SET "
                + "inventario = ?, fisicoInventario = ?, ajusteEntrada = ? "
                + "WHERE idSistema = ?";

        return new SentenciaSql(sql,
                UtilidadInventario.formatear(inventario),
                UtilidadInventario.formatear(fisicoInventario),
                UtilidadInventario.formatear(ajusteEntrada),
                producto.getIdSistema());
    }
}
