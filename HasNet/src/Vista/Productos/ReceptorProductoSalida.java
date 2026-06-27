package Vista.Productos;

public interface ReceptorProductoSalida {
    
    void cargarProducto(String codigo, String cantidad, int plu, String imei, String lote,
                        String idProd, String talla, String color, String temp, String fechaVence);
}
