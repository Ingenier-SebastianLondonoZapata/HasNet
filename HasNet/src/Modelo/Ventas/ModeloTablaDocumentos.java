/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Ventas;

import java.math.BigDecimal;

/**
 *
 * @author sebastian.londono
 */
public class ModeloTablaDocumentos {

    String idFactura, factura, fechaFactura, identificadorCliente, nombreCliente, vendedor, terminal, turno, tipoFactura;
    BigDecimal totalGeneral;

    public ModeloTablaDocumentos() {
        //DO NOTHING
    }

    public String getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(String idFactura) {
        this.idFactura = idFactura;
    }

    public String getFactura() {
        return factura;
    }

    public void setFactura(String factura) {
        this.factura = factura;
    }

    public String getFechaFactura() {
        return fechaFactura;
    }

    public void setFechaFactura(String fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    public String getIdentificadorCliente() {
        return identificadorCliente;
    }

    public void setIdentificadorCliente(String identificadorCliente) {
        this.identificadorCliente = identificadorCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getTipoFactura() {
        return tipoFactura;
    }

    public void setTipoFactura(String tipoFactura) {
        this.tipoFactura = tipoFactura;
    }

    public BigDecimal getTotalGeneral() {
        return totalGeneral;
    }

    public void setTotalGeneral(BigDecimal totalGeneral) {
        this.totalGeneral = totalGeneral;
    }

    public ModeloTablaDocumentos(String idFactura, String factura, String fechaFactura, String identificadorCliente, String nombreCliente, String vendedor, String terminal, String turno, String tipoFactura, BigDecimal totalGeneral) {
        this.idFactura = idFactura;
        this.factura = factura;
        this.fechaFactura = fechaFactura;
        this.identificadorCliente = identificadorCliente;
        this.nombreCliente = nombreCliente;
        this.vendedor = vendedor;
        this.terminal = terminal;
        this.turno = turno;
        this.tipoFactura = tipoFactura;
        this.totalGeneral = totalGeneral;
    }
}
