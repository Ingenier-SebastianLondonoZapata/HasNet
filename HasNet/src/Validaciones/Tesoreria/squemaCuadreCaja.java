package Validaciones.Tesoreria;

import Controlador.Alertas.ControladorAlertas;
import clases.Instancias;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class squemaCuadreCaja {

    private final String simboloMoneda = Instancias.getInstancias().getSimbolo();

    public boolean validacionesCuadre(BigDecimal totalVentas, BigDecimal efectivo, BigDecimal cheque, BigDecimal tarjeta,
            BigDecimal notaCredito, int cantidadCongeladas, boolean esRestaurante) {

        List<Object> errores_validacion = new ArrayList<>();

        if (totalVentas.compareTo(BigDecimal.ZERO) == 0) {
            errores_validacion.add("No se puede guardar sin facturas");
        }

        boolean alMenosUnValor = efectivo.compareTo(BigDecimal.ZERO) > 0
                || cheque.compareTo(BigDecimal.ZERO) > 0
                || tarjeta.compareTo(BigDecimal.ZERO) > 0
                || notaCredito.compareTo(BigDecimal.ZERO) > 0;

        if (!alMenosUnValor) {
            errores_validacion.add("Debe ingresar al menos un valor mayor a " + simboloMoneda + "0 en efectivo, cheque, tarjeta o nota crédito");
        }

        if (cantidadCongeladas > 0) {
            errores_validacion.add(esRestaurante ? "¡Hay mesas sin finalizar!" : "¡Hay congeladas sin finalizar!");
        }

        if (errores_validacion.size() > 0) {
            ControladorAlertas.alertaGrandeListado("Se presentaron los siguientes errores...", errores_validacion, false);
            return false;
        }

        return true;
    }
}
