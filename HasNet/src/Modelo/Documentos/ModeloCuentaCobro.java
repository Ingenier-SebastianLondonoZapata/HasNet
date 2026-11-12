package Modelo.Documentos;

import Enums.TipoDocumento;

public class ModeloCuentaCobro extends ModeloDocumento {

    private String periodicidad = "";
    private String inicio;
    private String hasta;
    private String cantidadIncremento = "";
    private String fechaUltimoPago;
    private int cantidadFacturados;

    public ModeloCuentaCobro() {
        super(TipoDocumento.CUENTA_COBRO);
    }

    public String getPeriodicidad() {
        return periodicidad;
    }

    public void setPeriodicidad(String periodicidad) {
        this.periodicidad = periodicidad;
    }

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getHasta() {
        return hasta;
    }

    public void setHasta(String hasta) {
        this.hasta = hasta;
    }

    public String getCantidadIncremento() {
        return cantidadIncremento;
    }

    public void setCantidadIncremento(String cantidadIncremento) {
        this.cantidadIncremento = cantidadIncremento;
    }

    public String getFechaUltimoPago() {
        return fechaUltimoPago;
    }

    public void setFechaUltimoPago(String fechaUltimoPago) {
        this.fechaUltimoPago = fechaUltimoPago;
    }

    public int getCantidadFacturados() {
        return cantidadFacturados;
    }

    public void setCantidadFacturados(int cantidadFacturados) {
        this.cantidadFacturados = cantidadFacturados;
    }
}
