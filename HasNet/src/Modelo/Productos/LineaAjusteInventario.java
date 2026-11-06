package Modelo.Productos;

import java.math.BigDecimal;

public class LineaAjusteInventario {

    private String idSistema;
    private String descripcion;
    private String plu;
    private String idDetalleProducto;
    private String observaciones;
    private String detalle;
    private BigDecimal cantidad = BigDecimal.ZERO;
    private BigDecimal cantidadInventario = BigDecimal.ZERO;
    private BigDecimal valorUnitario = BigDecimal.ZERO;
    private BigDecimal subtotal = BigDecimal.ZERO;
    private BigDecimal porcentajeIva = BigDecimal.ZERO;
    private BigDecimal iva = BigDecimal.ZERO;
    private BigDecimal total = BigDecimal.ZERO;
    private BigDecimal costo = BigDecimal.ZERO;
    private BigDecimal porcentajeImpoconsumo = BigDecimal.ZERO;
    private BigDecimal impoconsumo = BigDecimal.ZERO;

    public String getIdSistema() {
        return idSistema;
    }

    public void setIdSistema(String idSistema) {
        this.idSistema = idSistema;
    }

    public String getDescripcion() {
        return texto(descripcion);
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPlu() {
        return texto(plu);
    }

    public void setPlu(String plu) {
        this.plu = plu;
    }

    public String getIdDetalleProducto() {
        return texto(idDetalleProducto);
    }

    public void setIdDetalleProducto(String idDetalleProducto) {
        this.idDetalleProducto = idDetalleProducto;
    }

    public String getObservaciones() {
        return texto(observaciones);
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getDetalle() {
        return texto(detalle);
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public BigDecimal getCantidad() {
        return numero(cantidad);
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getCantidadInventario() {
        return numero(cantidadInventario);
    }

    public void setCantidadInventario(BigDecimal cantidadInventario) {
        this.cantidadInventario = cantidadInventario;
    }

    public BigDecimal getValorUnitario() {
        return numero(valorUnitario);
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public BigDecimal getSubtotal() {
        return numero(subtotal);
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getPorcentajeIva() {
        return numero(porcentajeIva);
    }

    public void setPorcentajeIva(BigDecimal porcentajeIva) {
        this.porcentajeIva = porcentajeIva;
    }

    public BigDecimal getIva() {
        return numero(iva);
    }

    public void setIva(BigDecimal iva) {
        this.iva = iva;
    }

    public BigDecimal getTotal() {
        return numero(total);
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getCosto() {
        return numero(costo);
    }

    public void setCosto(BigDecimal costo) {
        this.costo = costo;
    }

    public BigDecimal getPorcentajeImpoconsumo() {
        return numero(porcentajeImpoconsumo);
    }

    public void setPorcentajeImpoconsumo(BigDecimal porcentajeImpoconsumo) {
        this.porcentajeImpoconsumo = porcentajeImpoconsumo;
    }

    public BigDecimal getImpoconsumo() {
        return numero(impoconsumo);
    }

    public void setImpoconsumo(BigDecimal impoconsumo) {
        this.impoconsumo = impoconsumo;
    }

    private String texto(String valor) {
        return valor == null ? "" : valor;
    }

    private BigDecimal numero(BigDecimal valor) {
        return valor == null ? BigDecimal.ZERO : valor;
    }
}
