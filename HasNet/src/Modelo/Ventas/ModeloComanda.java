package Modelo.Ventas;

import java.math.BigDecimal;

public class ModeloComanda {

    private String congelada;
    private String factura;
    private String codigo;
    private String producto;
    private String opciones;
    private String ingredientes;
    private String adiciones;
    private String aderezos;
    private BigDecimal cantidad;
    private String observaciones;
    private int turno;
    private String pedido;
    private String consecutivo;

    public ModeloComanda() {
    }

    public ModeloComanda(String congelada, String factura, String codigo, String producto,
            String opciones, String ingredientes, String adiciones, String aderezos,
            BigDecimal cantidad, String observaciones, int turno, String pedido, String consecutivo) {
        this.congelada = congelada;
        this.factura = factura;
        this.codigo = codigo;
        this.producto = producto;
        this.opciones = opciones;
        this.ingredientes = ingredientes;
        this.adiciones = adiciones;
        this.aderezos = aderezos;
        this.cantidad = cantidad;
        this.observaciones = observaciones;
        this.turno = turno;
        this.pedido = pedido;
        this.consecutivo = consecutivo;
    }

    public String getCongelada() {
        return congelada;
    }

    public void setCongelada(String congelada) {
        this.congelada = congelada;
    }

    public String getFactura() {
        return factura;
    }

    public void setFactura(String factura) {
        this.factura = factura;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getOpciones() {
        return opciones;
    }

    public void setOpciones(String opciones) {
        this.opciones = opciones;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getAdiciones() {
        return adiciones;
    }

    public void setAdiciones(String adiciones) {
        this.adiciones = adiciones;
    }

    public String getAderezos() {
        return aderezos;
    }

    public void setAderezos(String aderezos) {
        this.aderezos = aderezos;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public int getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

    public String getPedido() {
        return pedido;
    }

    public void setPedido(String pedido) {
        this.pedido = pedido;
    }

    public String getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(String consecutivo) {
        this.consecutivo = consecutivo;
    }
}
