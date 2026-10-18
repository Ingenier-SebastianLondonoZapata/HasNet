package Modelo.Inventario;

import Modelo.Productos.ModeloProducto;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class LineaImportacionInventarioInicial {

    private final ModeloProducto producto;
    private final String tipo;
    private BigDecimal cantidad = BigDecimal.ZERO;
    private BigDecimal costoUnidad;
    private final List<Object[]> detalles = new ArrayList<>();

    public LineaImportacionInventarioInicial(ModeloProducto producto, String tipo) {
        this.producto = producto;
        this.tipo = tipo;
    }

    public ModeloProducto getProducto() {
        return producto;
    }

    public String getTipo() {
        return tipo;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void sumarCantidad(BigDecimal valor) {
        cantidad = cantidad.add(valor);
    }

    public BigDecimal getCostoUnidad() {
        return costoUnidad;
    }

    public void setCostoUnidad(BigDecimal costoUnidad) {
        this.costoUnidad = costoUnidad;
    }

    public List<Object[]> getDetalles() {
        return detalles;
    }
}
