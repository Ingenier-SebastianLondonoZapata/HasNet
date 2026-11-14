package Modelo.Ventas;

import java.math.BigDecimal;

public class MovimientoDocumento {

    private final String tipoComprobante;
    private final String factura;
    private final String consecutivo;
    private final String prefijoGeneral;
    private final String vendedor;
    private final String congelada;
    private final String turno;
    private final String fechaFactura;
    private final BigDecimal copago;
    private final String porcentajeReteFuente;

    public String getTipoComprobante() {
        return tipoComprobante;
    }

    public String getFactura() {
        return factura;
    }

    public String getConsecutivo() {
        return consecutivo;
    }

    public String getPrefijoGeneral() {
        return prefijoGeneral;
    }

    public String getVendedor() {
        return vendedor;
    }

    public String getCongelada() {
        return congelada;
    }

    public String getTurno() {
        return turno;
    }

    public int getTurnoNumerico() {
        try {
            return Integer.parseInt(turno.trim());
        } catch (NumberFormatException | NullPointerException e) {
            return 0;
        }
    }

    public String getFechaFactura() {
        return fechaFactura;
    }

    public BigDecimal getCopago() {
        return copago;
    }

    public String getPorcentajeReteFuente() {
        return porcentajeReteFuente;
    }

    public MovimientoDocumento(String tipoComprobante, String factura, String consecutivo, String prefijoGeneral, String vendedor, String congelada, String turno, String fechaFactura, BigDecimal copago, String porcentajeReteFuente) {
        this.tipoComprobante = tipoComprobante;
        this.factura = factura;
        this.consecutivo = consecutivo;
        this.prefijoGeneral = prefijoGeneral;
        this.vendedor = vendedor;
        this.congelada = congelada;
        this.turno = turno;
        this.fechaFactura = fechaFactura;
        this.copago = copago;
        this.porcentajeReteFuente = porcentajeReteFuente;
    }
}
