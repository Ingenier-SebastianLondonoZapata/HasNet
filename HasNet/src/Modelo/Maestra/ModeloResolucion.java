/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Maestra;

/**
 *
 * @author sebastian.londono
 */
public class ModeloResolucion {

    String descripcionResolucion, tipoResolucion, disenho, numeroResolucion, prefijo, fechaInicio, fechaFinal;
    int idResolucion, numeracionDel, numeracionHasta, consecutivo;

    public Integer getIdResolucion() {
        return idResolucion;
    }

    public void setIdResolucion(int idResolucion) {
        this.idResolucion = idResolucion;
    }

    public String getDescripcionResolucion() {
        return descripcionResolucion;
    }

    public void setDescripcionResolucion(String descripcionResolucion) {
        this.descripcionResolucion = descripcionResolucion;
    }

    public String getTipoResolucion() {
        return tipoResolucion;
    }

    public void setTipoResolucion(String tipoResolucion) {
        this.tipoResolucion = tipoResolucion;
    }

    public String getNumeroResolucion() {
        return numeroResolucion;
    }

    public void setNumeroResolucion(String numeroResolucion) {
        this.numeroResolucion = numeroResolucion;
    }

    public String getPrefijo() {
        return prefijo;
    }

    public void setPrefijo(String prefijo) {
        this.prefijo = prefijo;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(String fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public Integer getNumeracionDel() {
        return numeracionDel;
    }

    public void setNumeracionDel(int numeracionDel) {
        this.numeracionDel = numeracionDel;
    }

    public Integer getNumeracionHasta() {
        return numeracionHasta;
    }

    public void setNumeracionHasta(int numeracionHasta) {
        this.numeracionHasta = numeracionHasta;
    }

    public String getDisenho() {
        return disenho;
    }

    public void setDisenho(String disenho) {
        this.disenho = disenho;
    }

    public int getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(int consecutivo) {
        this.consecutivo = consecutivo;
    }

    public ModeloResolucion() {
    }

    public ModeloResolucion(int idResolucion, String descripcionResolucion, String tipoResolucion, String disenho, String numeroResolucion, String prefijo, String fechaInicio, String fechaFinal, int numeracionDel, int numeracionHasta) {
        this.idResolucion = idResolucion;
        this.descripcionResolucion = descripcionResolucion;
        this.tipoResolucion = tipoResolucion;
        this.disenho = disenho;
        this.numeroResolucion = numeroResolucion;
        this.prefijo = prefijo;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
        this.numeracionDel = numeracionDel;
        this.numeracionHasta = numeracionHasta;
    }

}
