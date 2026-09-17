package Modelo.Cartera;

import java.math.BigDecimal;

/**
 * Dinero que se aplica a una cuenta por pagar dentro de un pago.
 */
public class AbonoCuenta {

    private final String ingreso;
    private final String documento;
    private final BigDecimal valorCuenta;
    private final BigDecimal abonadoAnterior;
    private final BigDecimal valorAbono;
    private final BigDecimal saldoRestante;

    public AbonoCuenta(String ingreso, String documento, BigDecimal valorCuenta, BigDecimal abonadoAnterior,
            BigDecimal valorAbono, BigDecimal saldoRestante) {
        this.ingreso = ingreso;
        this.documento = documento;
        this.valorCuenta = valorCuenta;
        this.abonadoAnterior = abonadoAnterior;
        this.valorAbono = valorAbono;
        this.saldoRestante = saldoRestante;
    }

    /**
     * Identificador con el que se mueve la cuenta: el No. de ingreso en cuentas
     * por pagar y la factura interna en cuentas por cobrar.
     */
    public String getIngreso() {
        return ingreso;
    }

    /**
     * Documento que ve el usuario (No. de factura).
     */
    public String getDocumento() {
        return documento;
    }

    public BigDecimal getValorCuenta() {
        return valorCuenta;
    }

    public BigDecimal getAbonadoAnterior() {
        return abonadoAnterior;
    }

    public BigDecimal getValorAbono() {
        return valorAbono;
    }

    public BigDecimal getSaldoRestante() {
        return saldoRestante;
    }
}
