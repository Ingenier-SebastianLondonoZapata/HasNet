package Modelo.Inventario;

import java.math.BigDecimal;

public class UltimoPonderado {

    private final BigDecimal nuevoPonderado;
    private final BigDecimal ultimoCosto;
    private final String fechaCompra;

    public UltimoPonderado(BigDecimal nuevoPonderado, BigDecimal ultimoCosto, String fechaCompra) {
        this.nuevoPonderado = nuevoPonderado;
        this.ultimoCosto = ultimoCosto;
        this.fechaCompra = fechaCompra;
    }

    public BigDecimal getNuevoPonderado() {
        return nuevoPonderado;
    }

    public BigDecimal getUltimoCosto() {
        return ultimoCosto;
    }
    
     public String getFechaCompra() {
        return fechaCompra;
    }
}