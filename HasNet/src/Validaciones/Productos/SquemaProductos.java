package Validaciones.Productos;

import Controlador.Alertas.ControladorAlertas;
import Modelo.Productos.ModeloProducto;
import Utilidades.Constantes;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SquemaProductos {

    private static final int LONGITUD_MAXIMA_DESCRIPCION = 255;

    public boolean validarGuardar(ModeloProducto datos, BigDecimal precioPrincipal, BigDecimal costo, boolean codigoExiste,
            boolean referenciaExiste, boolean requiereUnidad, boolean aplicaIva, boolean aplicaImpoconsumo) {

        List<Object> errores = new ArrayList<>();
        List<Object> alertas = new ArrayList<>();

        if (estaVacio(datos.getCodigo()) && estaVacio(datos.getCodigoBarras())) {
            errores.add("Debe ingresar el código o el código de barras del producto");
        } else if (codigoExiste) {
            errores.add("Ya existe un producto con el código " + codigoPrincipal(datos));
        }

        validarComunes(datos, precioPrincipal, costo, referenciaExiste, requiereUnidad, aplicaIva, aplicaImpoconsumo, errores);

        if (estaVacio(datos.getIva())) {
            alertas.add("No ha ingresado el IVA de compra");
        }

        if (estaVacio(datos.getProveedor())) {
            alertas.add("No ha seleccionado el proveedor");
        }

        if (estaVacio(datos.getMinimo())) {
            alertas.add("No ha ingresado el punto mínimo");
        }

        return mostrarResultado(errores, alertas);
    }

    public boolean validarModificar(ModeloProducto datos, BigDecimal precioPrincipal, BigDecimal costo,
            boolean referenciaExiste, boolean requiereUnidad, boolean aplicaIva, boolean aplicaImpoconsumo) {

        List<Object> errores = new ArrayList<>();
        List<Object> alertas = new ArrayList<>();

        if (estaVacio(datos.getIdSistema())) {
            errores.add("Debe seleccionar el producto a modificar");
        }

        validarComunes(datos, precioPrincipal, costo, referenciaExiste, requiereUnidad, aplicaIva, aplicaImpoconsumo, errores);

        if (estaVacio(datos.getSubgrupo())) {
            alertas.add("No ha seleccionado el subgrupo");
        }

        if (estaVacio(datos.getIva())) {
            alertas.add("No ha ingresado el IVA de compra");
        }

        if (estaVacio(datos.getProveedor())) {
            alertas.add("No ha seleccionado el proveedor");
        }

        if (estaVacio(datos.getReferencia())) {
            alertas.add("No ha ingresado la referencia");
        }

        return mostrarResultado(errores, alertas);
    }

    private void validarComunes(ModeloProducto datos, BigDecimal precioPrincipal, BigDecimal costo, boolean referenciaExiste,
            boolean requiereUnidad, boolean aplicaIva, boolean aplicaImpoconsumo, List<Object> errores) {

        if (estaVacio(datos.getDescripcion())) {
            errores.add("Debe ingresar la descripción");
        } else if (datos.getDescripcion().length() > LONGITUD_MAXIMA_DESCRIPCION) {
            errores.add("La descripción no puede superar " + LONGITUD_MAXIMA_DESCRIPCION + " caracteres");
        }

        if (requiereUnidad && estaVacio(datos.getUnd())) {
            errores.add("Debe ingresar la unidad de medida");
        }

        if (precioPrincipal == null || precioPrincipal.signum() <= 0) {
            errores.add("El precio de la lista 1 debe ser mayor a cero");
        }

        if (costo == null) {
            errores.add("El costo tiene un formato inválido");
        } else if (costo.signum() < 0) {
            errores.add("El costo no puede ser negativo");
        }

        if (referenciaExiste) {
            errores.add("Ya existe un producto con la referencia " + datos.getReferencia());
        }

        validarTarifa(datos.getIva(), aplicaIva, "IVA", Constantes.TARIFAS_IVAS_PERMITIDOS, errores);
        validarTarifa(datos.getImpoconsumoVenta(), aplicaImpoconsumo, "impoconsumo", Constantes.TARIFAS_IMPOCONSUMO_PERMITIDOS, errores);
    }

    private void validarTarifa(String valor, boolean aplica, String nombre, List<Integer> permitidos, List<Object> errores) {
        Integer porcentaje = convertirEntero(valor);

        if (porcentaje == null) {
            errores.add("El " + nombre + " debe ser un número entero");
            return;
        }

        if (!aplica) {
            return;
        }

        if (porcentaje == 0) {
            errores.add("Debe ingresar el " + nombre);
        } else if (!permitidos.contains(porcentaje)) {
            errores.add(nombre.toUpperCase() + " no válido. Permitidos: " + permitidos);
        }
    }

    private boolean mostrarResultado(List<Object> errores, List<Object> alertas) {
        if (!errores.isEmpty()) {
            ControladorAlertas.alertaGrandeListado("Se presentaron los siguientes errores...", errores, false);
            return false;
        }

        if (!alertas.isEmpty()) {
            return ControladorAlertas.alertaGrandeListado("Se presentaron las siguientes alertas, ¿desea continuar?", alertas, true);
        }

        return ControladorAlertas.option("¿Desea continuar?");
    }

    private Integer convertirEntero(String valor) {
        if (estaVacio(valor)) {
            return 0;
        }
        try {
            return Integer.parseInt(valor.trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private String codigoPrincipal(ModeloProducto datos) {
        return estaVacio(datos.getCodigo()) ? datos.getCodigoBarras() : datos.getCodigo();
    }

    private boolean estaVacio(String valor) {
        return valor == null || valor.trim().isEmpty();
    }
}
