/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validaciones.Menu;

import Controlador.Alertas.controladorAlertas;
import Modelos.JSONCreacion.modeloCreacion;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sebastian.londono
 */
public class validacionesMenu {

    controladorAlertas alertas = new controladorAlertas();

    public boolean validaciones(modeloCreacion modelo) {

        List<Object> errores_validacion = new ArrayList<>();

        if (modelo.getIdentificacion().equals("")) {
            errores_validacion.add("Debe ingresar la identificacion");
        }

        if (modelo.getNombres().equals("")) {
            errores_validacion.add("Debe ingresar la razón social");
        }

        if (modelo.getPrimerApellido().equals("")) {
            errores_validacion.add("Debe ingresar el primer apellido");
        }

        if (modelo.getDireccion().equals("")) {
            errores_validacion.add("Debe ingresar la dirección");
        }

        if (modelo.getTelefono().equals("")) {
            errores_validacion.add("Debe ingresar el celular");
        }

        if (modelo.getEmail().equals("")) {
            errores_validacion.add("Debe ingresar el email que recivirá los errores");
        }

        if (modelo.getEmailRemitente().equals("")) {
            errores_validacion.add("Debe ingresar el email desde donde se enviaran los correos");
        }

        if (modelo.getEmailDefecto().equals("")) {
            errores_validacion.add("Debe ingresar el email por si rebota algún correo de un cliente");
        }
        
        if (modelo.getCdDaneCiudad().equals("")) {
            errores_validacion.add("Debe seleccionar la ciudad");
        }

        if (modelo.getCodigoPostal().equals(" ")) {
            errores_validacion.add("Debe seleccionar el código postal");
        }

        if (modelo.getCodigoCIIU().equals("")) {
            errores_validacion.add("Debe ingresar el código ciiu");
        }

        if (modelo.getImagenHeaderIzquierda().equals("")) {
            errores_validacion.add("Debe ingresar el logo en base64");
        }

        if (modelo.getIdentificadorPruebasDIAN().equals("")) {
            errores_validacion.add("Debe ingresar el identificador de pruebas DIAN");
        }
        
        if (errores_validacion.size() > 0) {
            alertas.alertaGrandeListado("Se presentaron los siguientes errores...", errores_validacion);
            return false;
        }

        return true;
    }
}
