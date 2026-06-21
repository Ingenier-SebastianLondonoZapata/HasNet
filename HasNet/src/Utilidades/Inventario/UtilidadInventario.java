package Utilidades.Inventario;

import java.math.BigDecimal;

public class UtilidadInventario {

    private UtilidadInventario() {
    }

    public static String formatear(BigDecimal valor) {
        if (valor == null) {
            return "0";
        }

        return valor.toPlainString().replace(".", ",");
    }
}
