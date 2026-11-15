package enviroments;

import clases.Instancias;

public class enviroments {

    private static final String URL_PRUEBAS = "https://alfauat.dominadigital.com.co/api/";
    private static final String URL_PRODUCCION = "https://alfaprod.dominadigital.com.co/api/";

    public String urlGenerarToken;
    public String urlRegistrarResolucion;
    public String urlFacturacion;
    public String urlConsultarFactura;
    public String urlDocumentoSoporte;
    public String urlCreacionContactos;
    public String urlEventosMercantiles;

    public void generarEnviroments(String nitEmisor, Instancias instancias) {
        String base = instancias.isPruebasFacturacionElectronica() ? URL_PRUEBAS : URL_PRODUCCION;

        urlGenerarToken = base + "GenerarTokenJWT/" + nitEmisor;
        urlFacturacion = base + "ReceptorFacturaJson/" + nitEmisor;
        urlRegistrarResolucion = base + "RegistrarResoluciones/" + nitEmisor;
        urlConsultarFactura = base + "ConsultarEstadoFactura";
        urlDocumentoSoporte = base + "ReceptorSoporteAdquisicionJson/" + nitEmisor;
        urlCreacionContactos = base + "ConsultaInformacionDIAN/" + nitEmisor;
        urlEventosMercantiles = base + "ReceptorEventoJson/" + nitEmisor;
    }
}
