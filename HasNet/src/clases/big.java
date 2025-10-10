package clases;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;

public class big {

    Instancias instancias = Instancias.getInstancias();

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

    public static String setMoneda(BigDecimal num) {
        String cadenaDecimales = Instancias.getInstancias().getCadenaDecimales();
        String simbolo = Instancias.getInstancias().getSimbolo();

        DecimalFormat formato = new DecimalFormat(simbolo + " ###,###." + cadenaDecimales);
        return formato.format(num);

    }

    public static String setMonedaExacta(BigDecimal num) {
        String simbolo = Instancias.getInstancias().getSimbolo();

        DecimalFormat formato = new DecimalFormat(simbolo + " ###,###");
        return formato.format(num);
    }

    public static String setNumero(BigDecimal num) {
        String cadenaDecimales = Instancias.getInstancias().getCadenaDecimales();

        DecimalFormat formato = new DecimalFormat("###,###." + cadenaDecimales);
        return formato.format(num);
    }

    public static BigDecimal getMoneda(String num) {
        String simbolo = Instancias.getInstancias().getSimbolo();

        int pos = num.length();
        if (Character.toString(num.charAt(pos - 1)).equals(",")) {
            return getBigDecimal(((num.replace(simbolo + " ", "")).replace(".", "")));
        }
        return getBigDecimal(((num.replace(simbolo + " ", "")).replace(".", "")).replace(",", "."));
    }
}
