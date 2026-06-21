package Modelo.Inventario;

import java.math.BigDecimal;

public class CalculoPonderado {

    private final BigDecimal ponderadoAnterior;
    private final BigDecimal inventarioAnterior;
    private final BigDecimal cantidadIngresada;
    private final BigDecimal inventarioNuevo;
    private final BigDecimal ponderadoNuevo;
    private final BigDecimal ultimoCosto;

    public CalculoPonderado(
            BigDecimal ponderadoAnterior,
            BigDecimal inventarioAnterior,
            BigDecimal cantidadIngresada,
            BigDecimal ponderadoNuevo,
            BigDecimal inventarioNuevo,
            BigDecimal ultimoCosto) {

        this.ponderadoAnterior = ponderadoAnterior;
        this.inventarioAnterior = inventarioAnterior;
        this.cantidadIngresada = cantidadIngresada;
        this.ultimoCosto = ultimoCosto;
        this.inventarioNuevo = inventarioNuevo;
        this.ponderadoNuevo = ponderadoNuevo;
    }

    public BigDecimal getPonderadoAnterior() {
        return ponderadoAnterior;
    }

    public BigDecimal getInventarioAnterior() {
        return inventarioAnterior;
    }

    public BigDecimal getCantidadIngresada() {
        return cantidadIngresada;
    }

    public BigDecimal getUltimoCosto() {
        return ultimoCosto;
    }

    public BigDecimal getInventarioNuevo() {
        return inventarioNuevo;
    }

    public BigDecimal getPonderadoNuevo() {
        return ponderadoNuevo;
    }
}
