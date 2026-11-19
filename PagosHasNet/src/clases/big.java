/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class big {

    public static BigDecimal getBigDecimal(Object value) {
        BigDecimal ret = null;
        if (value != null) {
            if (value instanceof BigDecimal) {
                ret = (BigDecimal) value;
            } else if (value instanceof String) {
                ret = new BigDecimal((String) value);
            } else if (value instanceof BigInteger) {
                ret = new BigDecimal((BigInteger) value);
            } else if (value instanceof Number) {
                ret = new BigDecimal(((Number) value).doubleValue());
            } else {
                throw new ClassCastException("Not possible to coerce [" + value + "] from class " + value.getClass() + " into a BigDecimal.");
            }
        }
        return ret;
    }

    private static DecimalFormat formato(String patron) {
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols(new Locale("es", "CO"));
        simbolos.setGroupingSeparator('.');
        simbolos.setDecimalSeparator(',');
        return new DecimalFormat(patron, simbolos);
    }

    public static String setMoneda(BigDecimal num) {
        return formato("$ ###,###.###").format(num);
    }

    public static String setMonedaEx(BigDecimal num) {
        return formato("$ ###,###.####").format(num);
    }

    public static String setMonedaExacta(BigDecimal num) {
        return formato("$ ###,###").format(num);
    }

    public static String setNumero(BigDecimal num) {
        return formato("###,###.###").format(num);
    }

    public static String setNumero(long num) {
        return formato("###,###").format(num);
    }

    public static String setNumeroEx(BigDecimal num) {
        return formato("###,###.####").format(num);
    }

    public static BigDecimal getMoneda(String num) {
        int pos = num.length();
        if (Character.toString(num.charAt(pos - 1)).equals(",")) {
            return getBigDecimal(((num.replace("$ ", "")).replace(".", "")));
        }
        return getBigDecimal(((num.replace("$ ", "")).replace(".", "")).replace(",", "."));
    }
}
