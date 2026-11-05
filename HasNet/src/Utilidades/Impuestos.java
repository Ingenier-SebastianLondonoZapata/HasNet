package Utilidades;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Cálculos de impuestos compartidos por los documentos.
 */
public final class Impuestos {

    private Impuestos() {
    }

    /**
     * Separa la base y los impuestos de un valor que ya los trae incluidos
     * (opción "Precios con impuestos").
     *
     * La base se obtiene dividiendo por (1 + %IVA + %impoconsumo) de los
     * impuestos incluidos. Con un solo impuesto el resultado es el mismo de
     * siempre: base = valor / (1 + %), impuesto = valor - base.
     *
     * @param valor valor con impuestos incluidos
     * @param factorIva 1 + %IVA (por ejemplo 1.19)
     * @param factorImpoconsumo 1 + %impoconsumo (por ejemplo 1.08)
     * @param ivaIncluido el valor trae el IVA incluido
     * @param impoconsumoIncluido el valor trae el impoconsumo incluido
     * @return {base, iva, impoconsumo}; el impuesto que no está incluido
     * vuelve en cero
     */
    public static BigDecimal[] desglosarIncluidos(BigDecimal valor, BigDecimal factorIva, BigDecimal factorImpoconsumo,
            boolean ivaIncluido, boolean impoconsumoIncluido) {
        BigDecimal porcentajeIva = ivaIncluido ? factorIva.subtract(BigDecimal.ONE) : BigDecimal.ZERO;
        BigDecimal porcentajeImpoconsumo = impoconsumoIncluido ? factorImpoconsumo.subtract(BigDecimal.ONE) : BigDecimal.ZERO;

        BigDecimal divisor = BigDecimal.ONE.add(porcentajeIva).add(porcentajeImpoconsumo);
        BigDecimal base = valor.divide(divisor, 2, RoundingMode.HALF_UP);
        BigDecimal impuestos = valor.subtract(base);

        BigDecimal iva;
        BigDecimal impoconsumo;
        if (ivaIncluido && impoconsumoIncluido) {
            // El IVA sobre la base; el impoconsumo es el resto, así la suma
            // base + IVA + impoconsumo da exactamente el valor
            iva = base.multiply(porcentajeIva).setScale(2, RoundingMode.HALF_UP);
            impoconsumo = impuestos.subtract(iva);
        } else if (ivaIncluido) {
            iva = impuestos;
            impoconsumo = BigDecimal.ZERO;
        } else {
            iva = BigDecimal.ZERO;
            impoconsumo = impuestos;
        }
        return new BigDecimal[]{base, iva, impoconsumo};
    }
}
