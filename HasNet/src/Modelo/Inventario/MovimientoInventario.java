package Modelo.Inventario;

import clases.productos.ndProducto;
import java.math.BigDecimal;

public class MovimientoInventario {

    private final ndProducto producto;
    private final BigDecimal cantidad;
    private final BigDecimal valorProducto;
    private final String idDetalleProducto;
    private final boolean armado;

    public MovimientoInventario(ndProducto producto, BigDecimal cantidad, BigDecimal valorProducto, String idDetalleProducto) {
        this(producto, cantidad, valorProducto, idDetalleProducto, false);
    }

    public MovimientoInventario(ndProducto producto, BigDecimal cantidad, BigDecimal valorProducto, String idDetalleProducto, boolean armado) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.valorProducto = valorProducto;
        this.idDetalleProducto = idDetalleProducto;
        this.armado = armado;
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

    public boolean esArmado() {
        return armado;
    }
}
