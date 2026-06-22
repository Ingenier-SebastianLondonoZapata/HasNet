package estrategiainventario;

import Modelo.Inventario.DetalleProducto;
import Modelo.Inventario.InformacionAdicional;
import Modelo.Inventario.MovimientoInventario;
import java.sql.SQLException;
import java.util.List;

public interface ProcesadorMovimiento {

    void procesar(List<MovimientoInventario> productos, List<DetalleProducto> detallesProductos, 
            String numeroDocumento, String tablaUtilizada, String usuario, InformacionAdicional informacionAdicional) throws SQLException;

}
