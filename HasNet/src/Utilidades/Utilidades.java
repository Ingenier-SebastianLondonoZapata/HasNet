/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

/**
 *
 * @author sebastian.londono
 */
public class Utilidades {

    public static Object[][] objetoVacio() {
        return new Object[0][0];
    }

    private static final DateTimeFormatter FORMATOS_FECHA = new DateTimeFormatterBuilder()
            .appendOptional(DateTimeFormatter.ISO_LOCAL_DATE)
            .appendOptional(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
            .toFormatter();

    public static LocalDate convertirFecha(String value) {
        try {
            return LocalDate.parse(value, FORMATOS_FECHA);
        } catch (Exception e) {
            return null;
        }
    }

    public static BigDecimal convertirBigDecimal(String valor) {
        if (valor == null || valor.trim().isEmpty()) {
            return BigDecimal.ZERO;
        }

        try {
            String limpio = valor.trim();
            if (limpio.contains(",")) {
                limpio = limpio.replace(".", "").replace(",", ".");
            }
            return new BigDecimal(limpio);
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

    public static String formatearCantidadVista(BigDecimal cantidad) {
        if (cantidad == null || cantidad.compareTo(BigDecimal.ZERO) == 0) {
            return "0";
        }

        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setDecimalSeparator(',');
        simbolos.setGroupingSeparator('.');
        DecimalFormat formato = new DecimalFormat("#,##0.####", simbolos);
        return formato.format(cantidad.setScale(Constantes.MAX_DECIMALES_CANTIDAD, BigDecimal.ROUND_HALF_UP).stripTrailingZeros());
    }

    public static String formatearCantidadVista(String valor) {
        return formatearCantidadVista(convertirBigDecimal(valor));
    }
}
