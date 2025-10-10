package Controlador.FacturacionElectronica;

import Controlador.ConsumirServicio.controladorConsumirServicioREST;
import Controlador.Alertas.ControladorAlertas;
import Modelo.FacturacionElectronica.Entrada.ModeloConsultaFacturaElectronica;
import Modelo.FacturacionElectronica.Salida.ConsultaFacturaElectronicaDTO;
import Validaciones.DocumentosElectronicos.squemaDocumentosElectronicos;
import clases.Instancias;
import clases.metodosGenerales;
import enviroments.enviroments;
import org.json.JSONException;
import org.json.JSONObject;

public class controladorFacturacionElectronica {

    Instancias instancias = Instancias.getInstancias();
    metodosGenerales metodos = new metodosGenerales();

    enviroments variablesEntorno = new enviroments();
    ControladorAlertas alertas = new ControladorAlertas();
    controladorConsumirServicioREST consumirServicio = new controladorConsumirServicioREST();
    squemaDocumentosElectronicos validacionesDocumentosElectronicos = new squemaDocumentosElectronicos();
    controladorCrearJSON controladorCrearJSON = new controladorCrearJSON();
    controladorConsultarFacturaJSON controladorConsultarFacturaJSON = new controladorConsultarFacturaJSON();

    public boolean generarToken(String nitEmisor, String tipoDocumento) {
        Boolean datosCorrectosJson = false;
        String respuestaServicio = "";

        instancias.setNitEmisor(nitEmisor);
        instancias.setTipoDocumentoEmisor(tipoDocumento);
        variablesEntorno.generarEnviroments(nitEmisor, instancias);

        try {
            respuestaServicio = consumirServicio.consumirServicioREST(variablesEntorno.urlGenerarToken, "");
        } catch (Exception ex) {
            System.err.println("Error consumiendo el servicio rest " + ex);
            alertas.alertFail("Error generado el token");
            return false;
        }

        if (!respuestaServicio.isEmpty()) {
            try {
                datosCorrectosJson = validacionesDocumentosElectronicos.validaciones_respuesta_token(respuestaServicio);
            } catch (JSONException ex) {
                System.err.println("Error enviado token del cliente " + ex);
                alertas.alertFail("Error enviando el token");
                return false;
            }
        }

        return datosCorrectosJson;
    }

    public boolean confirmarResolucionFacturacion() throws Exception {
        variablesEntorno.generarEnviroments(instancias.getNitEmisor(), instancias);
        String respuesta = consumirServicio.consumirServicioREST(variablesEntorno.urlRegistrarResolucion, "");
        System.out.println("Respuesta: " + respuesta);
        return validacionesDocumentosElectronicos.validaciones_respuesta_resoluciones(respuesta);
    }

    public ConsultaFacturaElectronicaDTO consultarFacturaElectronica(ModeloConsultaFacturaElectronica modeloConsultarFacturaElectronica) throws Exception {
        JSONObject JSONCompleto = controladorConsultarFacturaJSON.crearJSON(modeloConsultarFacturaElectronica);
        variablesEntorno.generarEnviroments(instancias.getNitEmisor(), instancias);
        String respuesta = consumirServicio.consumirServicioREST(variablesEntorno.urlConsultarFactura, JSONCompleto.toString());
        System.out.println("Respuesta: " + respuesta);

        if (validacionesDocumentosElectronicos.validaciones_respuesta_consulta_factura_electronica(respuesta)) {
            ConsultaFacturaElectronicaDTO consultaFactura = new ConsultaFacturaElectronicaDTO();
            return consultaFactura.crearModeloFactura(respuesta);
        } else {
            return null;
        }
    }
}
