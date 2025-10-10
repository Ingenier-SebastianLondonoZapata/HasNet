package Consumidor.FacturacionElectronica;

import Controlador.FacturacionElectronica.*;
import Controlador.ConsumirServicio.controladorConsumirServicioREST;
import Controlador.Alertas.ControladorAlertas;
import Controlador.BarraProceso.controladorBarraProceso;
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

public class consumidorFacturacionElectronica {

    Instancias instancias = Instancias.getInstancias();
    metodosGenerales metodos = new metodosGenerales();

    enviroments variablesEntorno = new enviroments();
    ControladorAlertas alertas = new ControladorAlertas();
    controladorConsumirServicioREST consumirServicio = new controladorConsumirServicioREST();
    squemaDocumentosElectronicos validacionesDocumentosElectronicos = new squemaDocumentosElectronicos();
    controladorCrearJSON controladorCrearJSON = new controladorCrearJSON();
    controladorConsultarFacturaJSON controladorConsultarFacturaJSON = new controladorConsultarFacturaJSON();

    public boolean generarFacturacionElectronica(ModeloFacturacionElectronica modeloFacturacionElectronica, boolean esNotaDebito, boolean esNotaDebitoPos,
            boolean esNotaCredito, boolean esFacturacionPos) throws Exception {
        if (modeloFacturacionElectronica == null) {
            return false;
        }

        JSONObject JSONCompleto = null;
        iniciarLoader(esNotaDebito, esNotaCredito);

        try {
            JSONCompleto = controladorCrearJSON.crearJSON(modeloFacturacionElectronica, esNotaDebito, esNotaDebitoPos,
                    esNotaCredito, esFacturacionPos, instancias.getCanalFacturacion());
        } catch (JSONException ex) {
            System.err.println("Error al crear el JSON: " + ex);
        }

        System.out.println("JSONCompleto: " + JSONCompleto);
        String identificadorFactura = modeloFacturacionElectronica.getDsPrefijo() + "-" + modeloFacturacionElectronica.getDsNumeroFactura();
        return enviarJSONFacturacionElectronica(JSONCompleto, identificadorFactura);
    }

    public ConsultaFacturaElectronicaDTO consultarFacturaElectronica(String factura) throws Exception {
        ModeloConsultaFacturaElectronica modeloConsulta = crearModeloConsulta(factura);
        controladorFacturacionElectronica controladorFacturacion = new controladorFacturacionElectronica();
        ConsultaFacturaElectronicaDTO datosFacturaElectronica = controladorFacturacion.consultarFacturaElectronica(modeloConsulta);
        return datosFacturaElectronica;
    }

    private boolean enviarJSONFacturacionElectronica(JSONObject JSON, String identificadorFactura) throws Exception {
        variablesEntorno.generarEnviroments(instancias.getNitEmisor(), instancias);
        String respuesta = consumirServicio.consumirServicioREST(variablesEntorno.urlFacturacion, JSON.toString());
        instancias.getProgres().detener(true);
        instancias.setProgres(null);
        Boolean datosCorrectos = validacionesDocumentosElectronicos.validaciones_respuesta(respuesta);

        if (datosCorrectos) {
            generarRegistro(identificadorFactura);
        }

        return datosCorrectos;
    }

    private void generarRegistro(String identificadorFactura) {
        String identificadorCliente = Constantes.leerIdentificadorCliente();
        instancias.getSqlPagos().agregarRegistroFactura(identificadorCliente, identificadorFactura, metodosGenerales.fechaHora());
        instancias.getSqlPagos().disminuirConsecutivo(identificadorCliente,
                Integer.parseInt(instancias.getSqlPagos().obtenerNumeroConsecutivo(identificadorCliente)[0].toString()) - 1);
    }

    private void iniciarLoader(boolean esNotaDebito, boolean esNotaCredito) {
        controladorBarraProceso controladorBarra = new controladorBarraProceso();
        String tituloCargando = "ENVIANDO FACTURA ELECTRÓNICA";
        if (esNotaDebito) {
            tituloCargando = "ENVIANDO NOTA DEBITO ELECTRÓNICA";
        } else if (esNotaCredito) {
            tituloCargando = "ENVIANDO NOTA CRÉDITO ELECTRÓNICA";
        }

        esperandoRespuesta barra = new esperandoRespuesta(controladorBarra, Instancias.getInstancias(), tituloCargando);
        barra.show();
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
