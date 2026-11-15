package Modelo.SoportesMercantiles;

import java.time.LocalDate;

public class FiltroSoportesMercantiles {

    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String prefijo = "";
    private String folio = "";
    private String cufe = "";
    private String nombreEmisor = "";
    private String nitEmisor = "";

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getPrefijo() {
        return prefijo;
    }

    public void setPrefijo(String prefijo) {
        this.prefijo = limpiar(prefijo);
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = limpiar(folio);
    }

    public String getCufe() {
        return cufe;
    }

    public void setCufe(String cufe) {
        this.cufe = limpiar(cufe);
    }

    public String getNombreEmisor() {
        return nombreEmisor;
    }

    public void setNombreEmisor(String nombreEmisor) {
        this.nombreEmisor = limpiar(nombreEmisor);
    }

    public String getNitEmisor() {
        return nitEmisor;
    }

    public void setNitEmisor(String nitEmisor) {
        this.nitEmisor = limpiar(nitEmisor);
    }

    private static String limpiar(String valor) {
        return valor == null ? "" : valor.trim();
    }
}
