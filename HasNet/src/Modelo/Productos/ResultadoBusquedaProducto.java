package Modelo.Productos;

import clases.productos.ndProducto;

public class ResultadoBusquedaProducto {

    private final ndProducto producto;
    private final String codigoResuelto;

    public ResultadoBusquedaProducto(ndProducto producto, String codigoResuelto) {
        this.producto = producto;
        this.codigoResuelto = codigoResuelto;
    }

    public ndProducto getProducto() {
        return producto;
    }

    public String getCodigoResuelto() {
        return codigoResuelto;
    }
}
