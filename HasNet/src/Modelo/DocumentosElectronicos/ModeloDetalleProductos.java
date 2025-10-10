/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DocumentosElectronicos;

import java.math.BigDecimal;

/**
 *
 * @author sebastian.londono
 */
public class ModeloDetalleProductos {

    String numeroFactura;
    String codigoArticulo;
    String estandarProducto;
    String descripcionArticulo;
    String observacionDetalle;
    BigDecimal porcentajeIva;
    BigDecimal porcentajeConsumo;
    String cantidad;
    BigDecimal precioUnitario;
    BigDecimal valorTotalArticulo;
    BigDecimal valorIva;
    BigDecimal valorConsumo;
    String unidadMedida;
    BigDecimal valorTotalBruto;
    BigDecimal unidadesEmpaque;
    BigDecimal valorTotalImpuestosRetenciones;
    String codigoVendedor;
    
    //Para documento soporte
    String fechaInicio;
    String codigoGeneracion;

    Object[][] impuestosProducto;
    ModeloDescuentos[] descuentoProducto;

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public String getCodigoArticulo() {
        return codigoArticulo;
    }

    public void setCodigoArticulo(String codigoArticulo) {
        this.codigoArticulo = codigoArticulo;
    }

    public String getEstandarProducto() {
        return estandarProducto;
    }

    public void setEstandarProducto(String estandarProducto) {
        this.estandarProducto = estandarProducto;
    }

    public String getDescripcionArticulo() {
        return descripcionArticulo;
    }

    public void setDescripcionArticulo(String descripcionArticulo) {
        this.descripcionArticulo = descripcionArticulo;
    }

    public String getObservacionDetalle() {
        return observacionDetalle;
    }

    public void setObservacionDetalle(String observacionDetalle) {
        this.observacionDetalle = observacionDetalle;
    }

    public BigDecimal getPorcentajeIva() {
        return porcentajeIva;
    }

    public void setPorcentajeIva(BigDecimal porcentajeIva) {
        this.porcentajeIva = porcentajeIva;
    }

    public BigDecimal getPorcentajeConsumo() {
        return porcentajeConsumo;
    }

    public void setPorcentajeConsumo(BigDecimal porcentajeConsumo) {
        this.porcentajeConsumo = porcentajeConsumo;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public BigDecimal getValorTotalArticulo() {
        return valorTotalArticulo;
    }

    public void setValorTotalArticulo(BigDecimal valorTotalArticulo) {
        this.valorTotalArticulo = valorTotalArticulo;
    }

    public BigDecimal getValorIva() {
        return valorIva;
    }

    public void setValorIva(BigDecimal valorIva) {
        this.valorIva = valorIva;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public BigDecimal getValorTotalBruto() {
        return valorTotalBruto;
    }

    public void setValorTotalBruto(BigDecimal valorTotalBruto) {
        this.valorTotalBruto = valorTotalBruto;
    }

    public BigDecimal getUnidadesEmpaque() {
        return unidadesEmpaque;
    }

    public void setUnidadesEmpaque(BigDecimal unidadesEmpaque) {
        this.unidadesEmpaque = unidadesEmpaque;
    }

    public BigDecimal getValorTotalImpuestosRetenciones() {
        return valorTotalImpuestosRetenciones;
    }

    public void setValorTotalImpuestosRetenciones(BigDecimal valorTotalImpuestosRetenciones) {
        this.valorTotalImpuestosRetenciones = valorTotalImpuestosRetenciones;
    }

    public String getCodigoVendedor() {
        return codigoVendedor;
    }

    public void setCodigoVendedor(String codigoVendedor) {
        this.codigoVendedor = codigoVendedor;
    }

    public Object[][] getImpuestosProducto() {
        return impuestosProducto;
    }

    public void setImpuestosProducto(Object[][] impuestosProducto) {
        this.impuestosProducto = impuestosProducto;
    }

    public BigDecimal getValorConsumo() {
        return valorConsumo;
    }

    public void setValorConsumo(BigDecimal valorConsumo) {
        this.valorConsumo = valorConsumo;
    }

    public ModeloDescuentos[] getDescuentoProducto() {
        return descuentoProducto;
    }

    public void setDescuentoProducto(ModeloDescuentos[] descuentoProducto) {
        this.descuentoProducto = descuentoProducto;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getCodigoGeneracion() {
        return codigoGeneracion;
    }

    public void setCodigoGeneracion(String codigoGeneracion) {
        this.codigoGeneracion = codigoGeneracion;
    }
}
