package Modelo.Inventario;

import Modelo.Productos.ModeloProducto;
import java.math.BigDecimal;

public class LineaInventarioInicial {

    private final ModeloProducto producto;
    private final BigDecimal cantidad;
    private final BigDecimal costoUnidad;

    public LineaInventarioInicial(ModeloProducto producto, BigDecimal cantidad, BigDecimal costoUnidad) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.costoUnidad = costoUnidad;
    }

    public ModeloProducto getProducto() {
        return producto;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public BigDecimal getCostoUnidad() {
        return costoUnidad;
    }

    public BigDecimal getTotalCosto() {
        return cantidad.multiply(costoUnidad);
    }
}
