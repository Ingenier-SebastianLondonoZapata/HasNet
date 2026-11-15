package Modelo.Inventario;

import Modelo.Productos.ModeloProducto;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class LineaImportacionCompra {

    private final ModeloProducto producto;
    private final String tipo;
    private BigDecimal cantidad = BigDecimal.ZERO;
    private BigDecimal costoUnidad;
    private BigDecimal iva;
    private BigDecimal impoconsumo;
    private BigDecimal descuento;
    private String medida;
    private final List<Object[]> detalles = new ArrayList<>();

    public LineaImportacionCompra(ModeloProducto producto, String tipo) {
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

    public BigDecimal getIva() {
        return iva;
    }

    public void setIva(BigDecimal iva) {
        this.iva = iva;
    }

    public BigDecimal getImpoconsumo() {
        return impoconsumo;
    }

    public void setImpoconsumo(BigDecimal impoconsumo) {
        this.impoconsumo = impoconsumo;
    }

    public BigDecimal getDescuento() {
        return descuento;
    }

    public void setDescuento(BigDecimal descuento) {
        this.descuento = descuento;
    }

    public String getMedida() {
        return medida;
    }

    public void setMedida(String medida) {
        this.medida = medida;
    }

    public List<Object[]> getDetalles() {
        return detalles;
    }
}
