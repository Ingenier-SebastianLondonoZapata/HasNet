package Consumidor.FacturacionElectronica;

import Controlador.FacturacionElectronica.*;
import Controlador.ConsumirServicio.ControladorConsumirServicioREST;
import Controlador.Alertas.ControladorAlertas;
import Modelo.FacturacionElectronica.Entrada.ModeloConsultaFacturaElectronica;
import Modelo.FacturacionElectronica.Entrada.ModeloDatosFacturaElectronica;
import Modelo.FacturacionElectronica.Entrada.ModeloFacturacionElectronica;
import Modelo.FacturacionElectronica.Salida.ConsultaFacturaElectronicaDTO;
import Utilidades.Constantes;
import Validaciones.DocumentosElectronicos.squemaDocumentosElectronicos;
import clases.Instancias;
import clases.metodosGenerales;
import enviroments.enviroments;
import formularios.esperandoRespuesta;
import org.json.JSONException;
import org.json.JSONObject;

public class ConsumidorFacturacionElectronica {

    Instancias instancias = Instancias.getInstancias();
    metodosGenerales metodos = new metodosGenerales();

    enviroments variablesEntorno = new enviroments();
    ControladorAlertas alertas = new ControladorAlertas();
    ControladorConsumirServicioREST consumirServicio = new ControladorConsumirServicioREST();
    squemaDocumentosElectronicos validacionesDocumentosElectronicos = new squemaDocumentosElectronicos();
    ControladorCrearJSON controladorCrearJSON = new ControladorCrearJSON();
    ControladorConsultarFacturaJSON controladorConsultarFacturaJSON = new ControladorConsultarFacturaJSON();

    public boolean generarFacturacionElectronica(ModeloFacturacionElectronica modeloFacturacionElectronica, boolean esNotaDebito, boolean esNotaDebitoPos,
            boolean esNotaCredito, boolean esFacturacionPos) throws Exception {
        if (modeloFacturacionElectronica == null) {
            return false;
        }

        JSONObject JSONCompleto = null;

        try {
            JSONCompleto = controladorCrearJSON.crearJSON(modeloFacturacionElectronica, esNotaDebito, esNotaDebitoPos,
                    esNotaCredito, esFacturacionPos, instancias.getCanalFacturacion());
        } catch (JSONException ex) {
            System.err.println("Error al crear el JSON: " + ex);
        }

        System.out.println("JSONCompleto: " + JSONCompleto);
        String identificadorFactura = modeloFacturacionElectronica.getDsPrefijo() + "-" + modeloFacturacionElectronica.getDsNumeroFactura();
        return enviarJSONFacturacionElectronica(JSONCompleto, identificadorFactura, tituloCargando(esNotaDebito, esNotaCredito));
    }

    public ConsultaFacturaElectronicaDTO consultarFacturaElectronica(String factura) throws Exception {
        ModeloConsultaFacturaElectronica modeloConsulta = crearModeloConsulta(factura);
        ControladorFacturacionElectronica controladorFacturacion = new ControladorFacturacionElectronica();
        ConsultaFacturaElectronicaDTO datosFacturaElectronica = controladorFacturacion.consultarFacturaElectronica(modeloConsulta);
        return datosFacturaElectronica;
    }

    private boolean enviarJSONFacturacionElectronica(JSONObject JSON, String identificadorFactura, String tituloCargando) throws Exception {
        variablesEntorno.generarEnviroments(instancias.getNitEmisor(), instancias);
        String cuerpo = JSON.toString();
        String respuesta = esperandoRespuesta.ejecutar(tituloCargando,
                () -> consumirServicio.consumirServicioREST(variablesEntorno.urlFacturacion, cuerpo));
        Boolean datosCorrectos = validacionesDocumentosElectronicos.validaciones_respuesta(respuesta);

        if (datosCorrectos) {
            generarRegistro(identificadorFactura);
        }

        return datosCorrectos;
    }

    private void generarRegistro(String identificadorFactura) {
        String identificadorCliente = Constantes.leerIdentificadorCliente();
        instancias.getSqlPagos().agregarRegistroFactura(identificadorCliente, identificadorFactura, metodosGenerales.fechaHora());
        instancias.getSqlPagos().consumirDocumentoElectronico(identificadorCliente);
    }

    private String tituloCargando(boolean esNotaDebito, boolean esNotaCredito) {
        if (esNotaDebito) {
            return "ENVIANDO NOTA DÉBITO ELECTRÓNICA";
        }
        if (esNotaCredito) {
            return "ENVIANDO NOTA CRÉDITO ELECTRÓNICA";
        }
        return "ENVIANDO FACTURA ELECTRÓNICA";
    }

    private ModeloConsultaFacturaElectronica crearModeloConsulta(String consecutivo) {
        ModeloDatosFacturaElectronica[] datosFacturas = new ModeloDatosFacturaElectronica[1];
        ModeloDatosFacturaElectronica factura = new ModeloDatosFacturaElectronica();
        factura.setPrejio(consecutivo.replaceAll("[0-9]", ""));
        factura.setNumero(Integer.parseInt(consecutivo.replaceAll("[^0-9]", "")));
        datosFacturas[0] = factura;

        ModeloConsultaFacturaElectronica modeloConsulta = new ModeloConsultaFacturaElectronica();
        modeloConsulta.setIdentificacion(instancias.getNitEmisor());
        modeloConsulta.setTipoIdentificacion(instancias.getTipoDocumentoEmisor());
        modeloConsulta.setFacturas(datosFacturas);

        return modeloConsulta;
    }
}
