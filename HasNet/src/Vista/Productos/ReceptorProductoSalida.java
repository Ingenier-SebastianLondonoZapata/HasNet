package Vista.Productos;

import java.math.BigDecimal;

public interface ReceptorProductoSalida {
    
    void cargarProducto(String codigo, BigDecimal cantidad, int plu, String imei, String lote,
                        String idProd, String talla, String color, String temp, String fechaVence);
}
