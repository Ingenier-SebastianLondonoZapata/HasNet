package Modelo.Cartera;

import java.math.BigDecimal;

/**
 * Valores digitados en el formulario de abonos a cuentas por pagar: medios de
 * pago, retenciones y descuentos.
 */
public class MediosPagoAbono {

    private final BigDecimal efectivo;
    private final BigDecimal cheque;
    private final BigDecimal tarjeta;
    private final BigDecimal notaCredito;
    private final BigDecimal rteIva;
    private final BigDecimal rteIca;
    private final BigDecimal rteFuente;
    private final BigDecimal descuentoPago;
    private final BigDecimal descuentoFinanciero;

    /**
     * El orden es: medios de pago (efectivo, cheque, tarjeta, nota credito),
     * retenciones (iva, ica, fuente) y descuentos (pago, financiero). Las
     * cuentas por pagar no manejan nota credito y envian cero.
     */
    public MediosPagoAbono(BigDecimal efectivo, BigDecimal cheque, BigDecimal tarjeta, BigDecimal notaCredito,
            BigDecimal rteIva, BigDecimal rteIca, BigDecimal rteFuente, BigDecimal descuentoPago,
            BigDecimal descuentoFinanciero) {
        this.efectivo = efectivo;
        this.cheque = cheque;
        this.tarjeta = tarjeta;
        this.notaCredito = notaCredito;
        this.rteIva = rteIva;
        this.rteIca = rteIca;
        this.rteFuente = rteFuente;
        this.descuentoPago = descuentoPago;
        this.descuentoFinanciero = descuentoFinanciero;
    }

    /**
     * Total que se reparte entre las cuentas por pagar.
     */
    public BigDecimal getTotalAbono() {
        return efectivo.add(cheque).add(tarjeta).add(notaCredito).add(rteIva).add(rteIca)
                .add(rteFuente).add(descuentoPago).add(descuentoFinanciero);
    }

    /**
     * Total que se registra como egreso de tesoreria.
     *
     * @param incluirDescuentoPago el abono general suma el descuento de pago al
     * egreso y el abono por factura no; se conserva el comportamiento de cada
     * flujo.
     */
    public BigDecimal getTotalEgreso(boolean incluirDescuentoPago) {
        BigDecimal total = efectivo.add(cheque).add(tarjeta).add(rteIva).add(rteIca).add(rteFuente);
        return incluirDescuentoPago ? total.add(descuentoPago) : total;
    }

    public BigDecimal getEfectivo() {
        return efectivo;
    }

    public BigDecimal getCheque() {
        return cheque;
    }

    public BigDecimal getTarjeta() {
        return tarjeta;
    }

    public BigDecimal getNotaCredito() {
        return notaCredito;
    }

    public BigDecimal getRteIva() {
        return rteIva;
    }

    public BigDecimal getRteIca() {
        return rteIca;
    }

    public BigDecimal getRteFuente() {
        return rteFuente;
    }

    public BigDecimal getDescuentoPago() {
        return descuentoPago;
    }

    public BigDecimal getDescuentoFinanciero() {
        return descuentoFinanciero;
    }
}
