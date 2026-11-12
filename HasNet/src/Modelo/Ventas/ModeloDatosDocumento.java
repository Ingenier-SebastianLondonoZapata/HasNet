package Modelo.Ventas;

import Modelo.Documentos.ModeloDocumento;
import Utilidades.Fechas;
import java.math.BigDecimal;

public class ModeloDatosDocumento {

    String identificadorFactura, identificadorCliente, vendedor, fechaFactura, fechaVencimiento, observaciones, resolucion, bodega, estadoGeneral, estado2;
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

    public String getEstadoGeneral() {
        return estadoGeneral;
    }

    public void setEstadoGeneral(String estadoGeneral) {
        this.estadoGeneral = estadoGeneral;
    }

    public String getEstado2() {
        return estado2;
    }

    public void setEstado2(String estado2) {
        this.estado2 = estado2;
    }

    public ModeloDatosDocumento(String identificadorFactura, String identificadorCliente, String vendedor, String fechaFactura, String fechaVencimiento, String observaciones, String resolucion, BigDecimal subtotalGeneral, BigDecimal descuentoGeneral, BigDecimal ivaGeneral, BigDecimal impoconsumoGeneral, BigDecimal totalGeneral, boolean esAnulado, String bodega, String estadoGeneral, String estado2) {
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
        this.estadoGeneral = estadoGeneral;
        this.estado2 = estado2;
    }

    public static ModeloDatosDocumento construirModelo(ModeloDocumento documento) {
        return new ModeloDatosDocumento(
                documento.getFactura(),
                documento.getCliente(),
                documento.getVendedor(),
                Fechas.formatearFecha1(documento.getFechaFactura()),
                Fechas.formatearFecha1(documento.getFechaVencimiento()),
                documento.getObservacion(),
                documento.getResolucion(),
                documento.getSubtotal(),
                documento.getDescuento(),
                documento.getIva(),
                documento.getImpoconsumo(),
                documento.getTotal(),
                documento.isAnulada(),
                documento.getBodega(),
                documento.getEstadoGeneral(),
                documento.getEstado2()
        );
    }

}
