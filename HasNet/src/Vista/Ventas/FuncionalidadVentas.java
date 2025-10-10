/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.Ventas;

import Controlador.Alertas.ControladorAlertas;
import DAO.Configuraciones.DaoResoluciones;

/**
 *
 * @author sebastian.londono
 */
public class FuncionalidadVentas {

    private final DaoResoluciones daoResoluciones = new DaoResoluciones();

    public boolean aumentarConsecutivoResolucion(int idResolucion) {
        if (!daoResoluciones.aumentarConsecutivoResolucion(idResolucion)) {
            ControladorAlertas.alertFail("Error al aumentar consecutivo de factura");
            return false;
        }
        
        return true;
    }

}
