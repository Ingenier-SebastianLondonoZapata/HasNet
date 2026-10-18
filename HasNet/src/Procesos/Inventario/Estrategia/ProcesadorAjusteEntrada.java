package Procesos.Inventario.Estrategia;

import Enums.Tablas;
import Modelo.Inventario.InformacionAdicional;
import Modelo.Inventario.MovimientoInventario;
import Utilidades.BaseDatos.SentenciaSql;
import Modelo.Productos.ModeloProducto;
import java.math.BigDecimal;

public class ProcesadorAjusteEntrada extends AbstractProcesadorMovimiento {

    @Override
    protected SentenciaSql generarSqlInventario(MovimientoInventario movimiento, String idBodega, InformacionAdicional informacionAdicional) {
        ModeloProducto producto = movimiento.getProducto();
        BigDecimal cantidad = movimiento.getCantidad();

        BigDecimal ajusteEntrada = producto.getAjusteEntrada().add(cantidad);
        producto.setAjusteEntrada(ajusteEntrada);

        if (!Boolean.TRUE.equals(producto.getManejaInventario())) {
            String sql = "UPDATE " + Tablas.INVENTARIO.getNombre() + " SET ajusteEntrada = ? WHERE idSistema = ? AND idBodega = ?";
            return new SentenciaSql(sql, ajusteEntrada, producto.getIdSistema(), idBodega);
        }

        BigDecimal inventario = producto.getInventario().add(cantidad);
        BigDecimal fisicoInventario = producto.getFisicoInventario().add(cantidad);
        producto.setInventario(inventario);
        producto.setFisicoInventario(fisicoInventario);

        String sql = "UPDATE " + Tablas.INVENTARIO.getNombre() + " SET "
                + "inventario = ?, fisicoInventario = ?, ajusteEntrada = ? "
                + "WHERE idSistema = ? AND idBodega = ?";

        return new SentenciaSql(sql,
                inventario,
                fisicoInventario,
                ajusteEntrada,
                producto.getIdSistema(), idBodega);
    }
}
