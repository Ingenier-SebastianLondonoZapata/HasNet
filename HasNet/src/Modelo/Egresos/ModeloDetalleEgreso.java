package Modelo.Egresos;

import java.math.BigDecimal;

public class ModeloDetalleEgreso {

    private String egreso, codigo, descripcion, factura, codigoUsuario;
    private BigDecimal valor, iva, subtotal;
    private int porcentajeIva;

    public String getEgreso() {
        return egreso;
    }

    public void setEgreso(String egreso) {
        this.egreso = egreso;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFactura() {
        return factura;
    }

    public void setFactura(String factura) {
        this.factura = factura;
    }

    public String getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(String codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getIva() {
        return iva;
    }

    public void setIva(BigDecimal iva) {
        this.iva = iva;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public int getPorcentajeIva() {
        return porcentajeIva;
    }

    public void setPorcentajeIva(int porcentajeIva) {
        this.porcentajeIva = porcentajeIva;
    }

    public ModeloDetalleEgreso(String egreso, String codigo, String descripcion, String factura, String codigoUsuario, BigDecimal valor, BigDecimal iva, BigDecimal subtotal, int porcentajeIva) {
        this.egreso = egreso;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.factura = factura;
        this.codigoUsuario = codigoUsuario;
        this.valor = valor;
        this.iva = iva;
        this.subtotal = subtotal;
        this.porcentajeIva = porcentajeIva;
    }

}
