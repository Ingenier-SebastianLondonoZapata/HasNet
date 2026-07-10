package Modelo.Ventas;

import Modelo.Terceros.ModeloContacto;
import javax.swing.JTable;

public class ModeloValidacionFactura {

    private ModeloContacto datosCliente;
    private String tipoProceso = "";
    private String titulo = "";
    private String nit = "";
    private String cantIncremento = "";
    private boolean saltarPasos = false;
    private boolean checkCupo = false;
    private String diasPlazo = "0";
    private String cupo = "0";
    private boolean servicioAutomotor = false;
    private JTable tblArticulos;
    private boolean facturaCredito = false;
    private String interes = "0";
    private boolean sisteCredito = false;
    private int diasPlazoInt = 0;
    private String cuotas = "0";
    private int tipoPlazoIndex = 0;
    private String fechaDesenvolso = "";
    private int filasTablaCuotas = 0;

    public ModeloValidacionFactura(ModeloContacto datosCliente) {
        this.datosCliente = datosCliente;
    }

    public ModeloContacto getDatosCliente() {
        return datosCliente;
    }

    public void setDatosCliente(ModeloContacto datosCliente) {
        this.datosCliente = datosCliente;
    }

    public String getTipoProceso() {
        return tipoProceso;
    }

    public void setTipoProceso(String tipoProceso) {
        this.tipoProceso = tipoProceso;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getCantIncremento() {
        return cantIncremento;
    }

    public void setCantIncremento(String cantIncremento) {
        this.cantIncremento = cantIncremento;
    }

    public boolean isSaltarPasos() {
        return saltarPasos;
    }

    public void setSaltarPasos(boolean saltarPasos) {
        this.saltarPasos = saltarPasos;
    }

    public boolean isCheckCupo() {
        return checkCupo;
    }

    public void setCheckCupo(boolean checkCupo) {
        this.checkCupo = checkCupo;
    }

    public String getDiasPlazo() {
        return diasPlazo;
    }

    public void setDiasPlazo(String diasPlazo) {
        this.diasPlazo = diasPlazo;
    }

    public String getCupo() {
        return cupo;
    }

    public void setCupo(String cupo) {
        this.cupo = cupo;
    }

    public boolean isServicioAutomotor() {
        return servicioAutomotor;
    }

    public void setServicioAutomotor(boolean servicioAutomotor) {
        this.servicioAutomotor = servicioAutomotor;
    }

    public JTable getTblArticulos() {
        return tblArticulos;
    }

    public void setTblArticulos(JTable tblArticulos) {
        this.tblArticulos = tblArticulos;
    }

    public boolean isFacturaCredito() {
        return facturaCredito;
    }

    public void setFacturaCredito(boolean facturaCredito) {
        this.facturaCredito = facturaCredito;
    }

    public String getInteres() {
        return interes;
    }

    public void setInteres(String interes) {
        this.interes = interes;
    }

    public boolean isSisteCredito() {
        return sisteCredito;
    }

    public void setSisteCredito(boolean sisteCredito) {
        this.sisteCredito = sisteCredito;
    }

    public int getDiasPlazoInt() {
        return diasPlazoInt;
    }

    public void setDiasPlazoInt(int diasPlazoInt) {
        this.diasPlazoInt = diasPlazoInt;
    }

    public String getCuotas() {
        return cuotas;
    }

    public void setCuotas(String cuotas) {
        this.cuotas = cuotas;
    }

    public int getTipoPlazoIndex() {
        return tipoPlazoIndex;
    }

    public void setTipoPlazoIndex(int tipoPlazoIndex) {
        this.tipoPlazoIndex = tipoPlazoIndex;
    }

    public String getFechaDesenvolso() {
        return fechaDesenvolso;
    }

    public void setFechaDesenvolso(String fechaDesenvolso) {
        this.fechaDesenvolso = fechaDesenvolso;
    }

    public int getFilasTablaCuotas() {
        return filasTablaCuotas;
    }

    public void setFilasTablaCuotas(int filasTablaCuotas) {
        this.filasTablaCuotas = filasTablaCuotas;
    }
}
