package Modelo.Inventario;

import java.math.BigDecimal;

public class ComponenteDiscosteo {

    private final String codigoInsumo;
    private final BigDecimal cantidad;

    public ComponenteDiscosteo(String codigoInsumo, BigDecimal cantidad) {
        this.codigoInsumo = codigoInsumo;
        this.cantidad = cantidad;
    }

    public String getCodigoInsumo() {
        return codigoInsumo;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }
}
