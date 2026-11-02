package Procesos.Inventario.Estrategia;

import Enums.Tablas;
import Modelo.Inventario.InformacionAdicional;
import Modelo.Inventario.MovimientoInventario;
import Utilidades.BaseDatos.SentenciaSql;
import Modelo.Productos.ModeloProducto;
import java.math.BigDecimal;

public class ProcesadorAnularArmado extends AbstractProcesadorMovimiento {

    @Override
    protected SentenciaSql generarSqlInventario(MovimientoInventario movimiento, String idBodega, InformacionAdicional informacionAdicional) {
        return movimiento.esArmado()
                ? devolverInsumo(movimiento, idBodega)
                : deshacerTerminado(movimiento, idBodega);
    }

    private SentenciaSql deshacerTerminado(MovimientoInventario movimiento, String idBodega) {
        ModeloProducto producto = movimiento.getProducto();
        BigDecimal cantidad = movimiento.getCantidad();

        BigDecimal costeo = producto.getCosteo().subtract(cantidad);
        producto.setCosteo(costeo);

        if (!Boolean.TRUE.equals(producto.getManejaInventario())) {
            String sql = "UPDATE " + Tablas.INVENTARIO.getNombre() + " SET costeo = ? WHERE idSistema = ? AND idBodega = ?";
            return new SentenciaSql(sql, costeo, producto.getIdSistema(), idBodega);
        }

        BigDecimal inventario = producto.getInventario().subtract(cantidad);
        BigDecimal fisicoInventario = producto.getFisicoInventario().subtract(cantidad);
        producto.setInventario(inventario);
        producto.setFisicoInventario(fisicoInventario);

        String sql = "UPDATE " + Tablas.INVENTARIO.getNombre() + " SET "
                + "inventario = ?, fisicoInventario = ?, costeo = ? "
                + "WHERE idSistema = ? AND idBodega = ?";

        return new SentenciaSql(sql,
                inventario,
                fisicoInventario,
                costeo,
                producto.getIdSistema(), idBodega);
    }

    private SentenciaSql devolverInsumo(MovimientoInventario movimiento, String idBodega) {
        ModeloProducto insumo = movimiento.getProducto();
        BigDecimal cantidad = movimiento.getCantidad();

        BigDecimal anulacion = insumo.getAnulada().add(cantidad);
        insumo.setAnulada(anulacion);

        if (!Boolean.TRUE.equals(insumo.getManejaInventario())) {
            String sql = "UPDATE " + Tablas.INVENTARIO.getNombre() + " SET anulacion = ? WHERE idSistema = ? AND idBodega = ?";
            return new SentenciaSql(sql, anulacion, insumo.getIdSistema(), idBodega);
        }

        BigDecimal inventario = insumo.getInventario().add(cantidad);
        BigDecimal fisicoInventario = insumo.getFisicoInventario().add(cantidad);
        insumo.setInventario(inventario);
        insumo.setFisicoInventario(fisicoInventario);

        String sql = "UPDATE " + Tablas.INVENTARIO.getNombre() + " SET "
                + "inventario = ?, fisicoInventario = ?, anulacion = ? "
                + "WHERE idSistema = ? AND idBodega = ?";

        return new SentenciaSql(sql,
                inventario,
                fisicoInventario,
                anulacion,
                insumo.getIdSistema(), idBodega);
    }
}
