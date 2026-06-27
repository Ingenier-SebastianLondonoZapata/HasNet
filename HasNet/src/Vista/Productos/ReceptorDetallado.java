package Vista.Productos;

import java.math.BigDecimal;

public interface ReceptorDetallado {
    
    void cargarDetallado(String prod, String imei, String lote, String fechaVence,
                         String temp, BigDecimal cantidad, String nombre, String color, String talla);
}
