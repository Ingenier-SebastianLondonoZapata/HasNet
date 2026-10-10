package Procesos.Inventario.Servicio;

import clases.productos.ndProducto;

public interface CargadorProducto {

    ndProducto cargar(String codigo, String tablaUtilizada);
}
