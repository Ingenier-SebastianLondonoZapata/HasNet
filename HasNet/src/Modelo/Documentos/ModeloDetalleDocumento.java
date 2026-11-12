package Modelo.Documentos;

import java.math.BigDecimal;

public class ModeloDetalleDocumento {

    private int linea;
    private String producto = "";
    private String descripcion = "";
    private BigDecimal precio = BigDecimal.ZERO;
    private BigDecimal cantidad = BigDecimal.ZERO;
    private BigDecimal cantidadUnidades = BigDecimal.ZERO;
    private int plu;
    private BigDecimal descuento = BigDecimal.ZERO;
    private BigDecimal porcentajeDescuento = BigDecimal.ZERO;
    private BigDecimal subtotal = BigDecimal.ZERO;
    private BigDecimal iva = BigDecimal.ZERO;
    private int porcentajeIva;
    private BigDecimal impoconsumo = BigDecimal.ZERO;
    private int porcentajeImpoconsumo;
    private BigDecimal total = BigDecimal.ZERO;
    private BigDecimal utilidad = BigDecimal.ZERO;
    private int utilidad1;
    private BigDecimal costo = BigDecimal.ZERO;
    private String estado = "";
    private String tercero = "";
    private String preparacion = "";
    private String rango = "";
    private String lote = "";
    private String idProd = "";
    private String detalle = "";
    private String consecutivoLinea;

    public int getLinea() {
        return linea;
    }

    public void setLinea(int linea) {
        this.linea = linea;
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

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getCantidadUnidades() {
        return cantidadUnidades;
    }

    public void setCantidadUnidades(BigDecimal cantidadUnidades) {
        this.cantidadUnidades = cantidadUnidades;
    }

    public int getPlu() {
        return plu;
    }

    public void setPlu(int plu) {
        this.plu = plu;
    }

    public BigDecimal getDescuento() {
        return descuento;
    }

    public void setDescuento(BigDecimal descuento) {
        this.descuento = descuento;
    }

    public BigDecimal getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    public void setPorcentajeDescuento(BigDecimal porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getIva() {
        return iva;
    }

    public void setIva(BigDecimal iva) {
        this.iva = iva;
    }

    public int getPorcentajeIva() {
        return porcentajeIva;
    }

    public void setPorcentajeIva(int porcentajeIva) {
        this.porcentajeIva = porcentajeIva;
    }

    public BigDecimal getImpoconsumo() {
        return impoconsumo;
    }

    public void setImpoconsumo(BigDecimal impoconsumo) {
        this.impoconsumo = impoconsumo;
    }

    public int getPorcentajeImpoconsumo() {
        return porcentajeImpoconsumo;
    }

    public void setPorcentajeImpoconsumo(int porcentajeImpoconsumo) {
        this.porcentajeImpoconsumo = porcentajeImpoconsumo;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getUtilidad() {
        return utilidad;
    }

    public void setUtilidad(BigDecimal utilidad) {
        this.utilidad = utilidad;
    }

    public int getUtilidad1() {
        return utilidad1;
    }

    public void setUtilidad1(int utilidad1) {
        this.utilidad1 = utilidad1;
    }

    public BigDecimal getCosto() {
        return costo;
    }

    public void setCosto(BigDecimal costo) {
        this.costo = costo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTercero() {
        return tercero;
    }

    public void setTercero(String tercero) {
        this.tercero = tercero;
    }

    public String getPreparacion() {
        return preparacion;
    }

    public void setPreparacion(String preparacion) {
        this.preparacion = preparacion;
    }

    public String getRango() {
        return rango;
    }

    public void setRango(String rango) {
        this.rango = rango;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public String getIdProd() {
        return idProd;
    }

    public void setIdProd(String idProd) {
        this.idProd = idProd;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getConsecutivoLinea() {
        return consecutivoLinea;
    }

    public void setConsecutivoLinea(String consecutivoLinea) {
        this.consecutivoLinea = consecutivoLinea;
    }
}
