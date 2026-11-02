package Modelo.Productos;

import java.math.BigDecimal;

public class LineaArmado {

    private final String producto;
    private final BigDecimal cantidadUnitaria;
    private final BigDecimal cantidadTotal;

    public LineaArmado(String producto, BigDecimal cantidadUnitaria, BigDecimal cantidadTotal) {
        this.producto = producto;
        this.cantidadUnitaria = cantidadUnitaria;
        this.cantidadTotal = cantidadTotal;
    }

    public String getProducto() {
        return producto;
    }

    public BigDecimal getCantidadUnitaria() {
        return cantidadUnitaria == null ? BigDecimal.ZERO : cantidadUnitaria;
    }

    public BigDecimal getCantidadTotal() {
        return cantidadTotal == null ? BigDecimal.ZERO : cantidadTotal;
    }
}
