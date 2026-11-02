package Procesos.Inventario.Estrategia;

import Enums.Tablas;
import Modelo.Inventario.InformacionAdicional;
import Modelo.Inventario.MovimientoInventario;
import Utilidades.BaseDatos.SentenciaSql;
import Modelo.Productos.ModeloProducto;
import java.math.BigDecimal;

public class ProcesadorArmado extends AbstractProcesadorMovimiento {

    @Override
    protected SentenciaSql generarSqlInventario(MovimientoInventario movimiento, String idBodega, InformacionAdicional informacionAdicional) {
        return movimiento.esArmado()
                ? consumirInsumo(movimiento, idBodega)
                : producirTerminado(movimiento, idBodega);
    }

    private SentenciaSql producirTerminado(MovimientoInventario movimiento, String idBodega) {
        ModeloProducto producto = movimiento.getProducto();
        BigDecimal cantidad = movimiento.getCantidad();

        BigDecimal costeo = producto.getCosteo().add(cantidad);
        producto.setCosteo(costeo);

        if (!Boolean.TRUE.equals(producto.getManejaInventario())) {
            String sql = "UPDATE " + Tablas.INVENTARIO.getNombre() + " SET costeo = ? WHERE idSistema = ? AND idBodega = ?";
            return new SentenciaSql(sql, costeo, producto.getIdSistema(), idBodega);
        }

        BigDecimal inventario = producto.getInventario().add(cantidad);
        BigDecimal fisicoInventario = producto.getFisicoInventario().add(cantidad);
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

    private SentenciaSql consumirInsumo(MovimientoInventario movimiento, String idBodega) {
        ModeloProducto insumo = movimiento.getProducto();
        BigDecimal cantidad = movimiento.getCantidad();

        BigDecimal armado = insumo.getArmado().add(cantidad);
        insumo.setArmado(armado);

        if (!Boolean.TRUE.equals(insumo.getManejaInventario())) {
            String sql = "UPDATE " + Tablas.INVENTARIO.getNombre() + " SET armado = ? WHERE idSistema = ? AND idBodega = ?";
            return new SentenciaSql(sql, armado, insumo.getIdSistema(), idBodega);
        }

        BigDecimal inventario = insumo.getInventario().subtract(cantidad);
        BigDecimal fisicoInventario = insumo.getFisicoInventario().subtract(cantidad);
        insumo.setInventario(inventario);
        insumo.setFisicoInventario(fisicoInventario);

        String sql = "UPDATE " + Tablas.INVENTARIO.getNombre() + " SET "
                + "inventario = ?, fisicoInventario = ?, armado = ? "
                + "WHERE idSistema = ? AND idBodega = ?";

        return new SentenciaSql(sql,
                inventario,
                fisicoInventario,
                armado,
                insumo.getIdSistema(), idBodega);
    }
}
