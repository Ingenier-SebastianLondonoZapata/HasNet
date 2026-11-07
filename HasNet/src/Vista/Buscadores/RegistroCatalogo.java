package Vista.Buscadores;

import java.util.function.Function;
import java.util.function.Predicate;

public class RegistroCatalogo {

    private String mensajeVacio = "Debe ingresar un valor";
    private boolean mayusculas;
    private Predicate<String> existe = valor -> false;
    private String mensajeExiste;
    private Function<String, String> pregunta = valor -> "¿Desea agregar el registro?";
    private Predicate<String> guardar = valor -> false;
    private String mensajeError = "Hubo un error al guardar";
    private String mensajeExito = "Registro ingresado con exito";

    public RegistroCatalogo mensajeVacio(String mensaje) {
        this.mensajeVacio = mensaje;
        return this;
    }

    public RegistroCatalogo enMayusculas() {
        this.mayusculas = true;
        return this;
    }

    public RegistroCatalogo existe(Predicate<String> existe) {
        this.existe = existe;
        return this;
    }

    public RegistroCatalogo mensajeExiste(String mensaje) {
        this.mensajeExiste = mensaje;
        return this;
    }

    public RegistroCatalogo pregunta(Function<String, String> pregunta) {
        this.pregunta = pregunta;
        return this;
    }

    public RegistroCatalogo guardar(Predicate<String> guardar) {
        this.guardar = guardar;
        return this;
    }

    public RegistroCatalogo mensajeError(String mensaje) {
        this.mensajeError = mensaje;
        return this;
    }

    public RegistroCatalogo mensajeExito(String mensaje) {
        this.mensajeExito = mensaje;
        return this;
    }

    public String getMensajeVacio() {
        return mensajeVacio;
    }

    public String normalizar(String texto) {
        return mayusculas ? texto.toUpperCase() : texto;
    }

    public boolean existe(String valor) {
        return existe.test(valor);
    }

    public String getMensajeExiste() {
        return mensajeExiste;
    }

    public String pregunta(String valor) {
        return pregunta.apply(valor);
    }

    public boolean guardar(String valor) {
        return guardar.test(valor);
    }

    public String getMensajeError() {
        return mensajeError;
    }

    public String getMensajeExito() {
        return mensajeExito;
    }
}
