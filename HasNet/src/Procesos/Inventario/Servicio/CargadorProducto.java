package Procesos.Inventario.Servicio;

import Modelo.Productos.ModeloProducto;

public interface CargadorProducto {

    ModeloProducto cargar(String codigo, String idBodega);
}
