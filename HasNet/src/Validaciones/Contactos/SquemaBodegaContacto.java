package Validaciones.Contactos;

import Controlador.Alertas.ControladorAlertas;
import Modelo.Contactos.ModeloBodega;
import Utilidades.Contactos.Bodegas;
import java.util.ArrayList;
import java.util.List;

public class SquemaBodegaContacto {

    public boolean validarBodega(ModeloBodega bodega, List<String> nombresRegistrados) {

        List<Object> errores_validacion = new ArrayList<>();
        String nombre = texto(bodega.getNombre());

        if (nombre.isEmpty()) {
            errores_validacion.add("Debe ingresar el nombre de la bodega");
        } else if (estaRegistrado(nombre, nombresRegistrados)) {
            errores_validacion.add("La bodega " + nombre + " ya está registrada");
        }

        if (Bodegas.TIPO_EXTERNA.equalsIgnoreCase(texto(bodega.getTipoBodega())) && texto(bodega.getContacto()).isEmpty()) {
            errores_validacion.add("Debe guardar el contacto antes de agregarle bodegas");
        }

        if (errores_validacion.size() > 0) {
            ControladorAlertas.alertaGrandeListado("Se presentaron los siguientes errores...", errores_validacion, false);
            return false;
        } else {
            return true;
        }
    }

    private boolean estaRegistrado(String nombre, List<String> nombresRegistrados) {
        if (nombresRegistrados == null) {
            return false;
        }

        for (String registrado : nombresRegistrados) {
            if (nombre.equalsIgnoreCase(texto(registrado))) {
                return true;
            }
        }
        return false;
    }

    private String texto(String valor) {
        return valor == null ? "" : valor.trim();
    }
}
