/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades.Inventario;

import DAO.Inventario.DaoInventario;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author sebastian.londono
 */
public class Inventario {

    private final String tablaUtilizada;
    private final DatosMovimientoInventario movimientoInventario;

    private final DaoInventario daoInventario = new DaoInventario();

    public Inventario(DatosMovimientoInventario movimientoInventario, String tablaUtilizada) {
        this.movimientoInventario = movimientoInventario;
        this.tablaUtilizada = tablaUtilizada;
    }

    public void procesarMovimiento() throws SQLException {
        switch (movimientoInventario.getTipoMovimiento()) {
            case "facturacion":
                String sql = "UPDATE " + tablaUtilizada + " SET anulacion = anulacion + ?, inventario = inventario + ?, fisicoInventario = fisicoInventario + ? WHERE idSistema = ?";
                actualizarInventario(sql, movimientoInventario);
                break;

            case "PEDIDO":
                // Un pedido reduce solo el inventario real (reserva)
                descontarReal(movimientoInventario.getProductos());
                break;

            case "PLAN_SEPARE":
                // Similar a pedido: aparta, no saca del físico aún
                descontarReal(movimientoInventario.getProductos());
                break;

            case "DEVOLUCION":
                // Una devolución devuelve al físico y al real
                aumentarFisico(movimientoInventario.getProductos());
                aumentarReal(movimientoInventario.getProductos());
                break;

            case "AJUSTE":
                // Un ajuste puede ser genérico, aquí puedes decidir
                // Por ejemplo: sumas a ambos
                aumentarFisico(movimientoInventario.getProductos());
                aumentarReal(movimientoInventario.getProductos());
                break;

            default:
                throw new IllegalArgumentException("Tipo de movimiento no soportado: " + movimientoInventario.getTipoMovimiento());
        }
    }

    private void actualizarInventario(String sql, DatosMovimientoInventario datosMovimiento) throws SQLException {
        daoInventario.actualizarInventarioBatch(sql, datosMovimiento);
    }
    
    private void descontarFisico(String Sql, List<MovimientoInventario> productos) {
        //daoInventario.descontarInventarioBatch(tablaUtilizada, productos);
    }

    private void descontarReal(List<MovimientoInventario> productos) {

    }

    private void descontarFisicoYReal(List<MovimientoInventario> productos) {

    }

    private void aumentarFisico(List<MovimientoInventario> productos) {

    }

    private void aumentarReal(List<MovimientoInventario> productos) {
    }
}
