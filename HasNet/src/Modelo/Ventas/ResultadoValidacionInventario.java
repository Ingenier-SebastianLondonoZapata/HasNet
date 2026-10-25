package Modelo.Ventas;

import java.util.List;

public class ResultadoValidacionInventario {

    private final List<Object[]> productosSinInventario;
    private final boolean tieneBolsa;

    public ResultadoValidacionInventario(List<Object[]> productosSinInventario, boolean tieneBolsa) {
        this.productosSinInventario = productosSinInventario;
        this.tieneBolsa = tieneBolsa;
    }

    public boolean hayProductosSinInventario() {
        return !productosSinInventario.isEmpty();
    }

    public boolean tieneBolsa() {
        return tieneBolsa;
    }

    public Object[][] getProductosSinInventario() {
        return productosSinInventario.toArray(new Object[0][]);
    }
}
