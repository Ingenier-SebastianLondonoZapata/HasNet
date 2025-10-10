package Controlador.FacturacionElectronica;

import Controlador.ConsumirServicio.controladorConsumirServicioREST;
import Controlador.Alertas.controladorAlertas;
import clases.Instancias;
import clases.metodosGenerales;
import enviroments.enviroments;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class controladorFacturacionElectronica {

    Instancias instancias = Instancias.getInstancias();
    metodosGenerales metodos = new metodosGenerales();

    enviroments variablesEntorno = new enviroments();
    controladorAlertas alertas = new controladorAlertas();
    controladorConsumirServicioREST consumirServicio = new controladorConsumirServicioREST();

    private static final String ERRORDIAN = "ERRORDIAN";
    private static final String ERROR = "ERROR";

    public boolean enviarJSONCreacionEmisor(String JSON, boolean conURLProduccion) throws Exception {
        variablesEntorno.generarEnviroments(conURLProduccion);
        String respuesta = consumirServicio.consumirServicioREST(variablesEntorno.urlCrearEmisor, JSON, variablesEntorno.tokenAutorizacion);
        return validaciones_respuesta(respuesta);
    }

    public boolean validaciones_respuesta(String respuesta) throws JSONException {
        JSONObject respuestaJSON = new JSONObject(respuesta);
        System.out.println("Respuesta web service: " + respuesta);

        String estadoTransaccion = respuestaJSON.getJSONObject("estado").getString("codigo");
        if (estadoTransaccion.equals(ERROR) || estadoTransaccion.equals(ERRORDIAN)) {
            List<Object> listadoDetalles = new ArrayList<>();
            JSONArray listadoErrores = respuestaJSON.getJSONObject("estado").getJSONArray("errores");

            for (int i = 0; i < listadoErrores.length(); i++) {
                listadoDetalles.add(listadoErrores.get(i));
            }

            alertas.alertaGrandeListado("Se presentaron los siguientes errores...", listadoDetalles);
            return false;
        } else {
            return true;
        }
    }

}
