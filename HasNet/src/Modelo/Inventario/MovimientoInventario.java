package Modelo.Inventario;

import clases.productos.ndProducto;
import java.math.BigDecimal;

public class MovimientoInventario {

    private final ndProducto producto;
    private final BigDecimal cantidad;
    private final BigDecimal valorProducto;
    private final String idDetalleProducto;

    public MovimientoInventario(ndProducto producto, BigDecimal cantidad, BigDecimal valorProducto, String idDetalleProducto) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.valorProducto = valorProducto;
        this.idDetalleProducto = idDetalleProducto;
    }

    public ndProducto getProducto() {
        return producto;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public BigDecimal getValorProducto() {
        return valorProducto;
    }

    public String getIdDetalleProducto() {
        return idDetalleProducto;
    }
}
