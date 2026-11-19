package Consumidor.DocumentoSoporte;

import Controlador.DocumentoSoporte.*;
import Controlador.ConsumirServicio.ControladorConsumirServicioREST;
import Controlador.Alertas.ControladorAlertas;
import Modelo.DocumentoSoporte.Entrada.ModeloDocumentoSoporte;
import Utilidades.Constantes;
import Validaciones.DocumentosElectronicos.squemaDocumentosElectronicos;
import clases.Instancias;
import clases.metodosGenerales;
import enviroments.enviroments;
import formularios.esperandoRespuesta;
import org.json.JSONException;
import org.json.JSONObject;

public class ConsumidorDocumentoSoporte {

    Instancias instancias = Instancias.getInstancias();
    metodosGenerales metodos = new metodosGenerales();

    enviroments variablesEntorno = new enviroments();
    ControladorAlertas alertas = new ControladorAlertas();
    ControladorConsumirServicioREST consumirServicio = new ControladorConsumirServicioREST();
    squemaDocumentosElectronicos validacionesDocumentosElectronicos = new squemaDocumentosElectronicos();
    controladorCrearJSON controladorCrearJSON = new controladorCrearJSON();

    public boolean generarDocumentoSoporte(ModeloDocumentoSoporte modeloDocumentoSoporte, boolean esNotaCredito) throws Exception {
        if (modeloDocumentoSoporte == null) {
            return false;
        }

        JSONObject JSONCompleto = null;

        try {
            JSONCompleto = controladorCrearJSON.crearJSON(modeloDocumentoSoporte, esNotaCredito, instancias.getCanalFacturacion());
        } catch (JSONException ex) {
            System.err.println("Error al crear el JSON: " + ex);
        }

        System.out.println("JSONCompleto: " + JSONCompleto);
        String identificadorCompra = modeloDocumentoSoporte.getDsPrefijo() + "-" + modeloDocumentoSoporte.getDsNumeroFactura();
        return enviarJSONDocumentoSoporte(JSONCompleto, identificadorCompra);
    }

    private boolean enviarJSONDocumentoSoporte(JSONObject JSON, String identificadorFactura) throws Exception {
        variablesEntorno.generarEnviroments(instancias.getNitEmisor(), instancias);
        String cuerpo = JSON.toString();
        String respuesta = esperandoRespuesta.ejecutar("ENVIANDO DOCUMENTO SOPORTE",
                () -> consumirServicio.consumirServicioREST(variablesEntorno.urlDocumentoSoporte, cuerpo));
        Boolean datosCorrectos = validacionesDocumentosElectronicos.validaciones_respuesta(respuesta);

        if (datosCorrectos) {
            generarRegistro(identificadorFactura);
        }

        return datosCorrectos;
    }

    private void generarRegistro(String identificadorCompra) {
        String identificadorCliente = Constantes.leerIdentificadorCliente();
        instancias.getSqlPagos().agregarRegistroFactura(identificadorCliente, identificadorCompra, metodosGenerales.fechaHora());
        instancias.getSqlPagos().consumirDocumentoElectronico(identificadorCliente);
    }
}
