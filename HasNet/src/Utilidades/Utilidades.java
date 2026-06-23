/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author sebastian.londono
 */
public class Utilidades {

    public static Object[][] objetoVacio() {
        return new Object[0][0];
    }

    public static LocalDate convertirFecha(String value) {
        try {
            return LocalDate.parse(value);
        } catch (Exception e) {
            return null;
        }
    }

    public static BigDecimal convertirBigDecimal(String valor) {
        if (valor == null || valor.trim().isEmpty()) {
            return BigDecimal.ZERO;
        }

        try {
            return new BigDecimal(valor.trim().replace(",", "."));
        } catch (NumberFormatException e) {
            return BigDecimal.ZERO;
        }
    }

    public static String formatearCantidad(String valor) {
        return formatearCantidad(Utilidades.convertirBigDecimal(valor));
    }

    public static String formatearCantidad(BigDecimal cantidad) {
        if (cantidad == null || cantidad.compareTo(BigDecimal.ZERO) == 0) {
            return "0";
        }

        return cantidad.setScale(Constantes.MAX_DECIMALES_CANTIDAD, BigDecimal.ROUND_HALF_UP)
                .stripTrailingZeros()
                .toPlainString()
                .replace(".", ",");
    }
}
