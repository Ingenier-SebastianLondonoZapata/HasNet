/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades.Inventario;

import java.util.List;

/**
 *
 * @author sebastian.londono
 */
public class DatosMovimientoInventario {

    private String tipoMovimiento;
    List<MovimientoInventario> productos;

    public DatosMovimientoInventario(String tipoMovimiento, List<MovimientoInventario> productos) {
        this.tipoMovimiento = tipoMovimiento;
        this.productos = productos;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public List<MovimientoInventario> getProductos() {
        return productos;
    }

    public void setProductos(List<MovimientoInventario> productos) {
        this.productos = productos;
    }
}
