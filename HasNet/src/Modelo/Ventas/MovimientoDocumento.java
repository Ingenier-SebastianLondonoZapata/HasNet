package Modelo.Ventas;

import java.math.BigDecimal;

public class MovimientoDocumento {

    private final String tipoComprobante;
    private final String factura;
    private final String factura2;
    private final String prefijoGeneral;
    private final String vendedor;
    private final String congelada;
    private final int turno;
    private final String fechaFactura;
    private final BigDecimal copago;
    private final String porcentajeReteFuente;

    public String getTipoComprobante() {
        return tipoComprobante;
    }

    public String getFactura() {
        return factura;
    }

    public String getFactura2() {
        return factura2;
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

    public int getTurno() {
        return turno;
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

    public MovimientoDocumento(String tipoComprobante, String factura, String factura2, String prefijoGeneral, String vendedor, String congelada, int turno, String fechaFactura, BigDecimal copago, String porcentajeReteFuente) {
        this.tipoComprobante = tipoComprobante;
        this.factura = factura;
        this.factura2 = factura2;
        this.prefijoGeneral = prefijoGeneral;
        this.vendedor = vendedor;
        this.congelada = congelada;
        this.turno = turno;
        this.fechaFactura = fechaFactura;
        this.copago = copago;
        this.porcentajeReteFuente = porcentajeReteFuente;
    }
}
