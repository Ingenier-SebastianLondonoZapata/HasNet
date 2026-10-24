package Modelo.Terceros;

public class ModeloConsultaContacto {

    private final String tipoIdentificacion;
    private final String numeroIdentificacion;

    public ModeloConsultaContacto(String tipoIdentificacion, String numeroIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion == null ? "" : tipoIdentificacion.trim();
        this.numeroIdentificacion = numeroIdentificacion == null ? "" : numeroIdentificacion.trim();
    }

    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public boolean estaCompleta() {
        return !tipoIdentificacion.isEmpty() && !numeroIdentificacion.isEmpty();
    }
}
