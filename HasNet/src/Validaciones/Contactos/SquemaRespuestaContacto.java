package Validaciones.Contactos;

import Controlador.Alertas.ControladorAlertas;
import Modelo.ConsumirServicio.RespuestaServicio;

public class SquemaRespuestaContacto {

    public boolean respuestaEsValida(RespuestaServicio respuesta) {
        if (respuesta.huboErrorDeConexion()) {
            ControladorAlertas.alertFail("No fue posible conectarse con el servicio: " + respuesta.getMensajeError());
            return false;
        }

        String cuerpo = respuesta.getCuerpo().trim();

        if (!respuesta.isExitosa()) {
            System.err.println("Respuesta con error del servicio: " + cuerpo);
            ControladorAlertas.alertFail("El servicio respondió con el código " + respuesta.getCodigoHttp());
            return false;
        }

        if (!cuerpo.startsWith("{")) {
            System.err.println("Respuesta diferente a JSON: " + cuerpo);
            ControladorAlertas.alertFail("El servicio respondió en un formato no esperado");
            return false;
        }

        return true;
    }
}
