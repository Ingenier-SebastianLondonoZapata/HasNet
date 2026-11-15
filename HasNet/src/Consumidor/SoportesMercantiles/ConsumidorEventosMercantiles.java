package Consumidor.SoportesMercantiles;

import Controlador.ConsumirServicio.ControladorConsumirServicioREST;
import Enums.EventoMercantil;
import Modelo.ConsumirServicio.RespuestaServicio;
import Modelo.SoportesMercantiles.DocumentoMercantil;
import Modelo.SoportesMercantiles.ResultadoEventoMercantil;
import clases.Instancias;
import enviroments.enviroments;
import java.text.Normalizer;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ConsumidorEventosMercantiles {

    private static final String PORTAL = "INTEGRACION_API";
    private static final String RESPONSABLE = "ADQUIRIENTE";
    private static final String EVENTO_YA_REGISTRADO = "ya tiene registrado el evento";

    private final Instancias instancias = Instancias.getInstancias();
    private final enviroments variablesEntorno = new enviroments();
    private final ControladorConsumirServicioREST consumirServicio = new ControladorConsumirServicioREST();

    public boolean hayDatosDelEmisor() {
        String nitEmisor = instancias.getNitEmisor();
        String token = instancias.getTokenEmisor();
        return nitEmisor != null && !nitEmisor.trim().isEmpty() && token != null && !token.trim().isEmpty();
    }

    public ResultadoEventoMercantil enviarEvento(DocumentoMercantil documento, EventoMercantil evento) {
        String json;
        try {
            json = crearJSON(documento, evento);
        } catch (JSONException ex) {
            return error(ResultadoEventoMercantil.ERROR, "No fue posible construir el JSON del evento: " + ex.getMessage());
        }

        variablesEntorno.generarEnviroments(instancias.getNitEmisor(), instancias);
        RespuestaServicio respuesta = consumirServicio.ejecutarPost(variablesEntorno.urlEventosMercantiles, json);
        System.out.println("Respuesta evento mercantil " + evento + " (" + documento.getIdentificador() + "): " + respuesta.getCuerpo());
        return leerRespuesta(respuesta);
    }

    String crearJSON(DocumentoMercantil documento, EventoMercantil evento) throws JSONException {
        JSONObject json = new JSONObject().put("portal", PORTAL);
        if (documento.tieneXml()) {
            json.put("xmlattachdocumentb64", documento.getXmlBase64());
        } else {
            json.put("cufeattachdocument", documento.getCufe());
        }
        return json.put("responsable", RESPONSABLE)
                .put("estadoEvento", evento.name())
                .toString();
    }

    ResultadoEventoMercantil leerRespuesta(RespuestaServicio respuesta) {
        if (respuesta.huboErrorDeConexion()) {
            return error(ResultadoEventoMercantil.ERROR_CONEXION, "No hubo conexión con el servicio: " + respuesta.getMensajeError());
        }

        if (respuesta.getCodigoHttp() == 401 || respuesta.getCodigoHttp() == 403) {
            return error(ResultadoEventoMercantil.ERROR_AUTORIZACION,
                    "El servicio rechazó el token de seguridad (HTTP " + respuesta.getCodigoHttp() + "). Inicie sesión nuevamente");
        }

        JSONObject cuerpo = convertir(respuesta.getCuerpo());
        if (cuerpo == null) {
            String texto = respuesta.getCuerpo().length() > 300 ? respuesta.getCuerpo().substring(0, 300) + "..." : respuesta.getCuerpo();
            return error(ResultadoEventoMercantil.ERROR, "Respuesta no válida del servicio (HTTP " + respuesta.getCodigoHttp() + "): " + texto);
        }

        ResultadoEventoMercantil resultado = new ResultadoEventoMercantil();
        resultado.setPrefijoFactura(cuerpo.optString("prefijoyfactura", ""));
        resultado.setCufe(cuerpo.optString("cufe", ""));
        resultado.setUuid(cuerpo.optString("uuid", ""));
        resultado.setFecha(cuerpo.optString("fecha", ""));

        JSONObject estado = cuerpo.optJSONObject("estado");
        if (estado == null) {
            resultado.setEstado(ResultadoEventoMercantil.ERROR);
            resultado.setDescripcion("El servicio no devolvió el estado del evento");
            return resultado;
        }

        resultado.setEstado(estado.optString("codigo", ResultadoEventoMercantil.ERROR));
        resultado.setDescripcion(estado.optString("descripcion", ""));
        agregarErrores(resultado, estado.opt("errores"));
        String erroraplicativo = estado.optString("erroraplicativo", "");
        if (!erroraplicativo.trim().isEmpty() && !"null".equalsIgnoreCase(erroraplicativo.trim())) {
            resultado.getErrores().add(erroraplicativo.trim());
        }

        if (!resultado.isCompletado() && yaEstabaRegistrado(resultado)) {
            resultado.setEstado(ResultadoEventoMercantil.YA_REGISTRADO);
        }
        return resultado;
    }

    private void agregarErrores(ResultadoEventoMercantil resultado, Object errores) {
        if (errores instanceof JSONArray) {
            JSONArray lista = (JSONArray) errores;
            for (int i = 0; i < lista.length(); i++) {
                String error = lista.optString(i, "").trim();
                if (!error.isEmpty()) {
                    resultado.getErrores().add(error);
                }
            }
        } else if (errores != null && !JSONObject.NULL.equals(errores) && !errores.toString().trim().isEmpty()) {
            resultado.getErrores().add(errores.toString().trim());
        }
    }

    private boolean yaEstabaRegistrado(ResultadoEventoMercantil resultado) {
        String texto = normalizar(resultado.getDescripcion() + " " + resultado.getErroresTexto());
        return texto.contains(EVENTO_YA_REGISTRADO);
    }

    private JSONObject convertir(String cuerpo) {
        String texto = cuerpo == null ? "" : cuerpo.trim();
        try {
            if (texto.startsWith("{")) {
                return new JSONObject(texto);
            }
            if (texto.startsWith("[")) {
                JSONArray arreglo = new JSONArray(texto);
                return arreglo.length() > 0 ? arreglo.optJSONObject(0) : null;
            }
        } catch (JSONException ex) {
            System.err.println("Respuesta de evento mercantil no es JSON: " + ex.getMessage());
        }
        return null;
    }

    private ResultadoEventoMercantil error(String estado, String descripcion) {
        ResultadoEventoMercantil resultado = new ResultadoEventoMercantil();
        resultado.setEstado(estado);
        resultado.setDescripcion(descripcion);
        return resultado;
    }

    private static String normalizar(String valor) {
        return Normalizer.normalize(valor == null ? "" : valor, Normalizer.Form.NFD).replaceAll("\\p{M}", "").toLowerCase();
    }
}
