package Consumidor.Contactos;

import Controlador.Alertas.ControladorAlertas;
import Controlador.BarraProceso.controladorBarraProceso;
import Controlador.ConsumirServicio.ControladorConsumirServicioREST;
import Modelo.ConsumirServicio.RespuestaServicio;
import Modelo.Contactos.ContactoApiDTO;
import Modelo.Contactos.ModeloConsultaContacto;
import Validaciones.Contactos.SquemaRespuestaContacto;
import clases.Instancias;
import enviroments.enviroments;
import formularios.esperandoRespuesta;
import org.json.JSONException;
import org.json.JSONObject;

public class ConsumidorContactos {

    private final Instancias instancias = Instancias.getInstancias();
    private final enviroments variablesEntorno = new enviroments();
    private final ControladorConsumirServicioREST consumirServicio = new ControladorConsumirServicioREST();
    private final SquemaRespuestaContacto validacionesRespuesta = new SquemaRespuestaContacto();

    public ContactoApiDTO consultarContacto(ModeloConsultaContacto consulta) {
        if (consulta == null || !consulta.estaCompleta()) {
            ControladorAlertas.alert("Debe ingresar el tipo y el número de identificación");
            return null;
        }

        if (!hayDatosDelEmisor()) {
            ControladorAlertas.alertFail("Para consultar el contacto debe tener activa la facturación electrónica e iniciar sesión nuevamente");
            return null;
        }

        String json = crearJSON(consulta);

        if (json == null) {
            return null;
        }

        variablesEntorno.generarEnviroments(instancias.getNitEmisor(), instancias);
        RespuestaServicio respuesta = consultarConBarraDeProceso(json);
        System.out.println("Respuesta consulta contacto: " + respuesta.getCuerpo());

        if (!validacionesRespuesta.respuestaEsValida(respuesta)) {
            return null;
        }

        return leerContacto(respuesta.getCuerpo());
    }

    private boolean hayDatosDelEmisor() {
        String nitEmisor = instancias.getNitEmisor();
        String token = instancias.getTokenEmisor();
        return nitEmisor != null && !nitEmisor.trim().isEmpty() && token != null && !token.trim().isEmpty();
    }

    private String crearJSON(ModeloConsultaContacto consulta) {
        try {
            return new JSONObject()
                    .put("tipoIdentificacion", consulta.getTipoIdentificacion())
                    .put("numeroIdentificacion", consulta.getNumeroIdentificacion())
                    .toString();
        } catch (JSONException ex) {
            System.err.println("Error al crear el JSON de la consulta del contacto: " + ex);
            ControladorAlertas.alertFail("No fue posible construir la consulta del contacto");
            return null;
        }
    }

    private RespuestaServicio consultarConBarraDeProceso(String json) {
        iniciarBarraDeProceso();
        try {
            return consumirServicio.ejecutarPost(variablesEntorno.urlCreacionContactos, json);
        } finally {
            detenerBarraDeProceso();
        }
    }

    private void iniciarBarraDeProceso() {
        esperandoRespuesta barra = new esperandoRespuesta(new controladorBarraProceso(), instancias, "CONSULTANDO EL CONTACTO");
        barra.show();
    }

    private void detenerBarraDeProceso() {
        if (instancias.getProgres() == null) {
            return;
        }

        instancias.getProgres().detener(true);
        instancias.setProgres(null);
    }

    private ContactoApiDTO leerContacto(String cuerpo) {
        ContactoApiDTO contacto;

        try {
            contacto = ContactoApiDTO.desdeRespuesta(cuerpo);
        } catch (JSONException ex) {
            System.err.println("Error al leer la respuesta del contacto: " + ex);
            ControladorAlertas.alertFail("No fue posible leer la información devuelta por el servicio");
            return null;
        }

        if (contacto.estaVacio()) {
            String estado = contacto.getEstado();
            ControladorAlertas.alert(estado.isEmpty() ? "El servicio no devolvió datos para esta identificación" : estado);
            return null;
        }

        return contacto;
    }
}
