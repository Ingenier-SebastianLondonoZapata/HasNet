package Consumidor.DocumentoSoporte;

import Controlador.DocumentoSoporte.*;
import Controlador.ConsumirServicio.controladorConsumirServicioREST;
import Controlador.Alertas.ControladorAlertas;
import Controlador.BarraProceso.controladorBarraProceso;
import Modelo.DocumentoSoporte.Entrada.ModeloDocumentoSoporte;
import Utilidades.Constantes;
import Validaciones.DocumentosElectronicos.squemaDocumentosElectronicos;
import clases.Instancias;
import clases.metodosGenerales;
import enviroments.enviroments;
import formularios.esperandoRespuesta;
import org.json.JSONException;
import org.json.JSONObject;

public class consumidorDocumentoSoporte {

    Instancias instancias = Instancias.getInstancias();
    metodosGenerales metodos = new metodosGenerales();

    enviroments variablesEntorno = new enviroments();
    ControladorAlertas alertas = new ControladorAlertas();
    controladorConsumirServicioREST consumirServicio = new controladorConsumirServicioREST();
    squemaDocumentosElectronicos validacionesDocumentosElectronicos = new squemaDocumentosElectronicos();
    controladorCrearJSON controladorCrearJSON = new controladorCrearJSON();

    public boolean generarDocumentoSoporte(ModeloDocumentoSoporte modeloDocumentoSoporte, boolean esNotaCredito) throws Exception {
        if (modeloDocumentoSoporte == null) {
            return false;
        }

        JSONObject JSONCompleto = null;
        iniciarLoader();

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
        String respuesta = consumirServicio.consumirServicioREST(variablesEntorno.urlDocumentoSoporte, JSON.toString());
        instancias.getProgres().detener(true);
        instancias.setProgres(null);
        Boolean datosCorrectos = validacionesDocumentosElectronicos.validaciones_respuesta(respuesta);

        if (datosCorrectos) {
            generarRegistro(identificadorFactura);
        }

        return datosCorrectos;
    }

    private void generarRegistro(String identificadorCompra) {
        String identificadorCliente = Constantes.leerIdentificadorCliente();
        instancias.getSqlPagos().agregarRegistroFactura(identificadorCliente, identificadorCompra, metodosGenerales.fechaHora());
        instancias.getSqlPagos().disminuirConsecutivo(identificadorCliente,
                Integer.parseInt(instancias.getSqlPagos().obtenerNumeroConsecutivo(identificadorCliente)[0].toString()) - 1);
    }

    private void iniciarLoader() {
        controladorBarraProceso controladorBarra = new controladorBarraProceso();
        esperandoRespuesta barra = new esperandoRespuesta(controladorBarra, Instancias.getInstancias(), "ENVIANDO DOCUMENTO SOPORTE");
        barra.show();
    }
}
