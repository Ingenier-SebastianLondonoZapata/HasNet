package Validaciones.Productos;

import Controlador.Alertas.ControladorAlertas;
import Modelo.Productos.ModeloProducto;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SquemaProductoRapido {

    private static final int LONGITUD_MAXIMA_DESCRIPCION = 255;

    public boolean validarProducto(ModeloProducto datos, BigDecimal precioVenta, boolean existe, boolean requiereUnidad) {
        List<Object> errores = new ArrayList<>();

        if (estaVacio(datos.getCodigo())) {
            errores.add("Debe ingresar el código");
        } else if (existe) {
            errores.add("Ya existe un producto con el código " + datos.getCodigo());
        }

        if (estaVacio(datos.getDescripcion())) {
            errores.add("Debe ingresar la descripción");
        } else if (datos.getDescripcion().length() > LONGITUD_MAXIMA_DESCRIPCION) {
            errores.add("La descripción no puede superar " + LONGITUD_MAXIMA_DESCRIPCION + " caracteres");
        }

        if (precioVenta == null) {
            errores.add("Debe ingresar un precio de venta válido");
        } else if (precioVenta.compareTo(BigDecimal.ZERO) <= 0) {
            errores.add("El precio de venta debe ser mayor a cero");
        }

        if (datos.getManejaInventario() == null) {
            errores.add("Debe indicar si el producto maneja inventario");
        }

        if (requiereUnidad && estaVacio(datos.getUnd())) {
            errores.add("Debe seleccionar la unidad de medida");
        }

        if (!errores.isEmpty()) {
            ControladorAlertas.alertaGrandeListado("Se presentaron los siguientes errores...", errores, false);
            return false;
        }
        return true;
    }

    private boolean estaVacio(String valor) {
        return valor == null || valor.trim().isEmpty();
    }
}
