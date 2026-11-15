package Modelo.Cartera;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CuentaPendiente {

    private String factura = "";
    private String facturaInterna = "";
    private LocalDate fechaFactura;
    private LocalDate fechaVencimiento;
    private BigDecimal valor = BigDecimal.ZERO;
    private BigDecimal saldo = BigDecimal.ZERO;
    private String edadCartera = "";
    private String estadoVencimiento = "";

    public String getFactura() {
        return factura;
    }

    public void setFactura(String factura) {
        this.factura = factura == null ? "" : factura;
    }

    public String getFacturaInterna() {
        return facturaInterna;
    }

    public void setFacturaInterna(String facturaInterna) {
        this.facturaInterna = facturaInterna == null ? "" : facturaInterna;
    }

    public LocalDate getFechaFactura() {
        return fechaFactura;
    }

    public void setFechaFactura(LocalDate fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor == null ? BigDecimal.ZERO : valor;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo == null ? BigDecimal.ZERO : saldo;
    }

    public BigDecimal getAbonadoAnterior() {
        return valor.subtract(saldo);
    }

    public String getEdadCartera() {
        return edadCartera;
    }

    public void setEdadCartera(String edadCartera) {
        this.edadCartera = edadCartera == null ? "" : edadCartera;
    }

    public String getEstadoVencimiento() {
        return estadoVencimiento;
    }

    public void setEstadoVencimiento(String estadoVencimiento) {
        this.estadoVencimiento = estadoVencimiento == null ? "" : estadoVencimiento;
    }
}
