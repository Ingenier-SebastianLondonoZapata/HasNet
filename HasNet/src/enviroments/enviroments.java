/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enviroments;

import clases.Instancias;

/**
 *
 * @author sebastian.londono
 */
public class enviroments {

    public String urlGenerarToken;
    public String urlRegistrarResolucion;
    public String urlFacturacion;
    public String urlConsultarFactura;
    public String urlDocumentoSoporte;

    public void generarEnviroments(String nitEmisor, Instancias instancias) {        
        if (instancias.isPruebasFacturacionElectronica()) {
            urlGenerarToken = "https://alfauat.dominadigital.com.co/api/GenerarTokenJWT/" + nitEmisor;
            urlFacturacion = "https://alfauat.dominadigital.com.co/api/ReceptorFacturaJson/" + nitEmisor;
            urlRegistrarResolucion = "https://alfauat.dominadigital.com.co/api/RegistrarResoluciones/" + nitEmisor;
            urlConsultarFactura = "https://alfauat.dominadigital.com.co/api/ConsultarEstadoFactura";
            urlDocumentoSoporte = "https://alfauat.dominadigital.com.co/api/ReceptorSoporteAdquisicionJson/" + nitEmisor;
        } else {
            urlGenerarToken = "https://alfaprod.dominadigital.com.co/api/GenerarTokenJWT/" + nitEmisor;
            urlFacturacion = "https://alfaprod.dominadigital.com.co/api/ReceptorFacturaJson/" + nitEmisor;
            urlRegistrarResolucion = "https://alfaprod.dominadigital.com.co/api/RegistrarResoluciones/" + nitEmisor;
            urlConsultarFactura = "https://alfaprod.dominadigital.com.co/api/ConsultarEstadoFactura";
            urlDocumentoSoporte = "https://alfaprod.dominadigital.com.co/api/ReceptorSoporteAdquisicionJson/" + nitEmisor;
        }
    }    
}
