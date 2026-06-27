package Modelo.Ventas;

import java.math.BigDecimal;

/**
 * Datos de una fila de tblProductos relevantes para la validación de inventario.
 * Solo contiene información que la vista conoce; el stock físico lo obtiene
 * el servicio directamente del nodo de producto (getFisicoInventario).
 */
public class FilaProductoTabla {

    private final String idProducto;
    private final String preparacion;
    private final BigDecimal cantidad;
    private final String descripcion;

    public FilaProductoTabla(
            String idProducto,
            String preparacion,
            BigDecimal cantidad,
            String descripcion) {
        this.idProducto = idProducto;
        this.preparacion = preparacion;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
    }

    public String getIdProducto() { return idProducto; }
    public String getPreparacion() { return preparacion; }
    public BigDecimal getCantidad() { return cantidad; }
    public String getDescripcion() { return descripcion; }
}
