package inventario.servicio;

import Enums.TipoDocumento;
import Modelo.Inventario.MovimientoInventario;
import inventario.estrategia.ProcesadorMovimiento;
import inventario.fabrica.FabricaProcesadores;
import Modelo.Inventario.DetalleProducto;
import Modelo.Inventario.InformacionAdicional;
import java.sql.SQLException;
import java.util.List;

public class ServicioInventario {

    private final List<MovimientoInventario> productos;
    private final List<DetalleProducto> detallesProductos;
    private final TipoDocumento tipoDocumento;
    private final String numeroDocumento;
    private final String tablaUtilizada;
    private final String usuario;
    private final InformacionAdicional informacionAdicional;

    public ServicioInventario(List<MovimientoInventario> productos, List<DetalleProducto> detallesProductos,
            TipoDocumento tipoDocumento, String numeroDocumento, String tablaUtilizada, String usuario, InformacionAdicional informacionAdicional) {
        this.productos = productos;
        this.detallesProductos = detallesProductos;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.tablaUtilizada = tablaUtilizada;
        this.usuario = usuario;
        this.informacionAdicional = informacionAdicional;
    }

    public void procesarMovimiento() throws SQLException {
        ProcesadorMovimiento procesador = FabricaProcesadores.obtener(tipoDocumento);
        procesador.procesar(productos, detallesProductos, numeroDocumento, tablaUtilizada, usuario, informacionAdicional);
    }
}
