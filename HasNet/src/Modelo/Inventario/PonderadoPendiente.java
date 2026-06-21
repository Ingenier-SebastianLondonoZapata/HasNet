package Modelo.Inventario;

import java.math.BigDecimal;

public class PonderadoPendiente {

    private String producto;
    private BigDecimal ponderadoAnterior;
    private BigDecimal inventarioAnterior;
    private BigDecimal cantidadIngresada;
    private BigDecimal ponderadoNuevo;
    private BigDecimal inventarioNuevo;
    private BigDecimal ultimoCosto;

    public PonderadoPendiente() {
    }

    public PonderadoPendiente(
            String producto,
            BigDecimal ponderadoAnterior,
            BigDecimal inventarioAnterior,
            BigDecimal cantidadIngresada,
            BigDecimal ponderadoNuevo,
            BigDecimal inventarioNuevo,
            BigDecimal ultimoCosto) {

        this.producto = producto;
        this.ponderadoAnterior = ponderadoAnterior;
        this.inventarioAnterior = inventarioAnterior;
        this.cantidadIngresada = cantidadIngresada;
        this.ponderadoNuevo = ponderadoNuevo;
        this.inventarioNuevo = inventarioNuevo;
        this.ultimoCosto = ultimoCosto;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public BigDecimal getPonderadoAnterior() {
        return ponderadoAnterior;
    }

    public void setPonderadoAnterior(BigDecimal ponderadoAnterior) {
        this.ponderadoAnterior = ponderadoAnterior;
    }

    public BigDecimal getInventarioAnterior() {
        return inventarioAnterior;
    }

    public void setInventarioAnterior(BigDecimal inventarioAnterior) {
        this.inventarioAnterior = inventarioAnterior;
    }

    public BigDecimal getCantidadIngresada() {
        return cantidadIngresada;
    }

    public void setCantidadIngresada(BigDecimal cantidadIngresada) {
        this.cantidadIngresada = cantidadIngresada;
    }

    public BigDecimal getPonderadoNuevo() {
        return ponderadoNuevo;
    }

    public void setPonderadoNuevo(BigDecimal ponderadoNuevo) {
        this.ponderadoNuevo = ponderadoNuevo;
    }

    public BigDecimal getInventarioNuevo() {
        return inventarioNuevo;
    }

    public void setInventarioNuevo(BigDecimal inventarioNuevo) {
        this.inventarioNuevo = inventarioNuevo;
    }

    public BigDecimal getUltimoCosto() {
        return ultimoCosto;
    }

    public void setUltimoCosto(BigDecimal ultimoCosto) {
        this.ultimoCosto = ultimoCosto;
    }
}
