package Servicio.Inventario;

import Modelo.Inventario.CalculoPonderado;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class ServicioPonderado {

    public CalculoPonderado calcularPonderado(
            BigDecimal inventarioActual,
            BigDecimal ponderadoActual,
            BigDecimal cantidadIngresada,
            BigDecimal valorTotalIngreso,
            BigDecimal ultimoCosto) {

        if (cantidadIngresada.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a cero");
        }

        BigDecimal costoUnitarioIngreso = valorTotalIngreso.divide(cantidadIngresada, 4, RoundingMode.HALF_UP);
        BigDecimal valorInventarioActual = inventarioActual.multiply(ponderadoActual);
        BigDecimal valorIngreso = cantidadIngresada.multiply(costoUnitarioIngreso);
        BigDecimal inventarioNuevo = inventarioActual.add(cantidadIngresada);
        BigDecimal ponderadoNuevo = valorInventarioActual.add(valorIngreso).divide(inventarioNuevo, 4, RoundingMode.HALF_UP);

        return new CalculoPonderado(
                ponderadoActual,
                inventarioActual,
                cantidadIngresada,
                ponderadoNuevo,
                inventarioNuevo,
                ultimoCosto);
    }
}
