package Modelo.Inventario;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DetalleProducto {

    private String consecutivo;
    private String producto;
    private String descripcion;
    private String imei;
    private String lote;
    private String color;
    private String talla;
    private LocalDate fechaVencimiento;
    private String temperatura;
    private String estado;
    private String bodega;
    private BigDecimal cantidad;
    private BigDecimal cantidadDisponible;

    public String getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(String consecutivo) {
        this.consecutivo = consecutivo;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(String temperatura) {
        this.temperatura = temperatura;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getBodega() {
        return bodega;
    }

    public void setBodega(String bodega) {
        this.bodega = bodega;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(BigDecimal cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public DetalleProducto(String consecutivo, String producto, String descripcion, String imei, String lote, String color, String talla, LocalDate fechaVencimiento, String temperatura, String estado, String bodega, BigDecimal cantidad, BigDecimal cantidadDisponible) {
        this.consecutivo = consecutivo;
        this.producto = producto;
        this.descripcion = descripcion;
        this.imei = imei;
        this.lote = lote;
        this.color = color;
        this.talla = talla;
        this.fechaVencimiento = fechaVencimiento;
        this.temperatura = temperatura;
        this.estado = estado;
        this.bodega = bodega;
        this.cantidad = cantidad;
        this.cantidadDisponible = cantidadDisponible;
    }
}
