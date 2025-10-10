/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Ventas;

import Utilidades.Fechas;
import clases.big;
import java.math.BigDecimal;

/**
 *
 * @author sebastian.londono
 */
public class ModeloDatosDocumento {

    String identificadorFactura, identificadorCliente, vendedor, fechaFactura, fechaVencimiento, observaciones, resolucion, bodega;
    BigDecimal subtotalGeneral, descuentoGeneral, ivaGeneral, impoconsumoGeneral, totalGeneral;
    boolean esAnulado;

    public ModeloDatosDocumento() {
        //DO NOTHING
    }

    public boolean isEsAnulado() {
        return esAnulado;
    }

    public void setEsAnulado(boolean esAnulado) {
        this.esAnulado = esAnulado;
    }

    public String getResolucion() {
        return resolucion;
    }

    public void setResolucion(String resolucion) {
        this.resolucion = resolucion;
    }

    public String getIdentificadorFactura() {
        return identificadorFactura;
    }

    public void setIdentificadorFactura(String identificadorFactura) {
        this.identificadorFactura = identificadorFactura;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getIdentificadorCliente() {
        return identificadorCliente;
    }

    public void setIdentificadorCliente(String identificadorCliente) {
        this.identificadorCliente = identificadorCliente;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public String getFechaFactura() {
        return fechaFactura;
    }

    public void setFechaFactura(String fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public BigDecimal getSubtotalGeneral() {
        return subtotalGeneral;
    }

    public void setSubtotalGeneral(BigDecimal subtotalGeneral) {
        this.subtotalGeneral = subtotalGeneral;
    }

    public BigDecimal getDescuentoGeneral() {
        return descuentoGeneral;
    }

    public void setDescuentoGeneral(BigDecimal descuentoGeneral) {
        this.descuentoGeneral = descuentoGeneral;
    }

    public BigDecimal getIvaGeneral() {
        return ivaGeneral;
    }

    public void setIvaGeneral(BigDecimal ivaGeneral) {
        this.ivaGeneral = ivaGeneral;
    }

    public BigDecimal getImpoconsumoGeneral() {
        return impoconsumoGeneral;
    }

    public void setImpoconsumoGeneral(BigDecimal impoconsumoGeneral) {
        this.impoconsumoGeneral = impoconsumoGeneral;
    }

    public BigDecimal getTotalGeneral() {
        return totalGeneral;
    }

    public void setTotalGeneral(BigDecimal totalGeneral) {
        this.totalGeneral = totalGeneral;
    }

    public String getBodega() {
        return bodega;
    }

    public void setBodega(String bodega) {
        this.bodega = bodega;
    }

    public ModeloDatosDocumento(String identificadorFactura, String identificadorCliente, String vendedor, String fechaFactura, String fechaVencimiento, String observaciones, String resolucion, BigDecimal subtotalGeneral, BigDecimal descuentoGeneral, BigDecimal ivaGeneral, BigDecimal impoconsumoGeneral, BigDecimal totalGeneral, boolean esAnulado, String bodega) {
        this.identificadorFactura = identificadorFactura;
        this.identificadorCliente = identificadorCliente;
        this.vendedor = vendedor;
        this.fechaFactura = fechaFactura;
        this.fechaVencimiento = fechaVencimiento;
        this.observaciones = observaciones;
        this.resolucion = resolucion;
        this.subtotalGeneral = subtotalGeneral;
        this.descuentoGeneral = descuentoGeneral;
        this.ivaGeneral = ivaGeneral;
        this.impoconsumoGeneral = impoconsumoGeneral;
        this.totalGeneral = totalGeneral;
        this.esAnulado = esAnulado;
        this.bodega = bodega;
    }

    public static ModeloDatosDocumento construirModelo(Object datos, BigDecimal impoConsumo) {

        boolean esAnulada = obtenerValor(datos, "isAnulada");
        return new ModeloDatosDocumento(
                String.valueOf(obtenerValor(datos, "getFactura")),
                String.valueOf(obtenerValor(datos, "getCliente")),
                String.valueOf(obtenerValor(datos, "getVendedor")),
                Fechas.formatearFecha1(obtenerFecha(datos, "getFechaFactura")),
                Fechas.formatearFecha1(obtenerFecha(datos, "getFechaVencimiento")),
                String.valueOf(obtenerValor(datos, "getObservacion")),
                String.valueOf(obtenerValor(datos, "getResolucion")),
                big.getBigDecimal(obtenerNumero(datos, "getSubtotalGeneral")),
                big.getBigDecimal(obtenerNumero(datos, "getDescuentoGeneral")),
                big.getBigDecimal(obtenerNumero(datos, "getIvaGeneral")),
                impoConsumo,
                big.getBigDecimal(obtenerNumero(datos, "getTotalGeneral")),
                esAnulada,
                String.valueOf(obtenerValor(datos, "getBodega"))
        );
    }

    @SuppressWarnings("unchecked")
    private static <T> T obtenerValor(Object obj, String metodo) {
        try {
            return (T) obj.getClass().getMethod(metodo).invoke(obj);
        } catch (Exception e) {
            throw new RuntimeException("Error obteniendo valor: " + metodo, e);
        }
    }

    private static String obtenerFecha(Object obj, String metodo) {
        return String.valueOf(obtenerValor(obj, metodo));
    }

    private static Number obtenerNumero(Object obj, String metodo) {
        Object valor = obtenerValor(obj, metodo);

        if (valor instanceof Number) {
            return (Number) valor;
        } else if (valor instanceof String) {
            try {
                return new BigDecimal((String) valor);
            } catch (NumberFormatException e) {
                throw new RuntimeException("No se pudo convertir el String a BigDecimal: " + valor, e);
            }
        }

        throw new RuntimeException("Tipo no válido para número en método: " + metodo);
    }

}
