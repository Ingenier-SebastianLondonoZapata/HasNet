package Validaciones.Contactos;

import Controlador.Alertas.ControladorAlertas;
import Modelo.Contactos.ModeloContacto;
import clases.metodosGenerales;
import java.util.ArrayList;
import java.util.List;

public class SquemaContactoRapido {

    public boolean validarContacto(ModeloContacto datos, boolean facturaElectronica, ModeloContacto existente) {
        List<Object> errores = new ArrayList<>();

        if (estaVacio(datos.getTipoTercero())) {
            errores.add("Debe seleccionar el tipo de contacto");
        }

        if (estaVacio(datos.getTipo())) {
            errores.add("Debe seleccionar el tipo de identificación");
        }

        if (estaVacio(datos.getId())) {
            errores.add("Debe ingresar la identificación");
        } else if (existente != null) {
            errores.add("Ya existe un contacto con la identificación " + datos.getId());
        }

        if (estaVacio(datos.getNombre())) {
            errores.add("Debe ingresar el nombre o razón social");
        }

        if (estaVacio(datos.getTelefono())) {
            errores.add("Debe ingresar el teléfono");
        }

        if (estaVacio(datos.getEmail())) {
            if (facturaElectronica) {
                errores.add("Debe ingresar el correo electrónico");
            }
        } else if (!metodosGenerales.emailEsCorrecto(datos.getEmail())) {
            errores.add("Debe ingresar un correo electrónico correcto");
        }

        if (estaVacio(datos.getDepartamento())) {
            errores.add("Debe seleccionar el departamento");
        }

        if (estaVacio(datos.getCiudad())) {
            errores.add("Debe seleccionar la ciudad");
        }

        if (estaVacio(datos.getCodigoPostal())) {
            errores.add("Debe seleccionar el código postal");
        }

        if (!errores.isEmpty()) {
            ControladorAlertas.alertaGrandeListado("Se presentaron los siguientes errores...", errores, false);
            return false;
        }
        return true;
    }

    private boolean estaVacio(String valor) {
        return valor == null || valor.trim().isEmpty();
    }
}
