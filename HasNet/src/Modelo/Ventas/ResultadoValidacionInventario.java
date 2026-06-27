package Modelo.Ventas;

import java.util.List;

public class ResultadoValidacionInventario {

    private final List<Object[]> productosSinInventario;
    private final List<Object[]> productosSinInventarioDis;
    private final boolean tieneBolsa;

    public ResultadoValidacionInventario(
            List<Object[]> productosSinInventario,
            List<Object[]> productosSinInventarioDis,
            boolean tieneBolsa) {
        this.productosSinInventario = productosSinInventario;
        this.productosSinInventarioDis = productosSinInventarioDis;
        this.tieneBolsa = tieneBolsa;
    }

    public boolean hayProductosSinInventario() {
        return !productosSinInventario.isEmpty() || !productosSinInventarioDis.isEmpty();
    }

    public boolean tieneBolsa() {
        return tieneBolsa;
    }

    public Object[][] getProductosSinInventarioTabla() {
        return productosSinInventario.toArray(new Object[0][]);
    }

    public Object[][] getProductosSinInventarioDisTabla() {
        return productosSinInventarioDis.toArray(new Object[0][]);
    }
}
