package inventario.servicio;

import Enums.TipoProducto;
import Modelo.Inventario.ComponenteDiscosteo;
import Modelo.Inventario.MovimientoInventario;
import Utilidades.Ventas.ParserPreparacion;
import clases.productos.ndProducto;
import dao.Productos.DaoDiseno;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ServicioDiscosteo {

    private final DaoDiseno daoDiseno;
    private final CargadorProducto cargadorProducto;

    public ServicioDiscosteo(CargadorProducto cargadorProducto) {
        this.daoDiseno = new DaoDiseno();
        this.cargadorProducto = cargadorProducto;
    }

    public List<MovimientoInventario> explotarSiEsDiscosteo(ndProducto producto, String preparacion, String tablaUtilizada, BigDecimal cantidadDiscosteo) throws SQLException {
        if (esGenerico(producto)) {
            return Collections.emptyList();
        }

        return explotar(producto, preparacion, tablaUtilizada, cantidadDiscosteo);
    }

    public List<MovimientoInventario> explotar(ndProducto discosteo, String preparacion, String tablaUtilizada, BigDecimal cantidadDiscosteo) throws SQLException {
        List<ComponenteDiscosteo> componentes = obtenerComponentes(discosteo, preparacion);
        return listaMovimientos(componentes, tablaUtilizada, cantidadDiscosteo);
    }

    private List<ComponenteDiscosteo> obtenerComponentes(ndProducto discosteo, String preparacion) throws SQLException {
        if (ParserPreparacion.tienePreparacion(preparacion)) {
            return ParserPreparacion.componentesActivos(preparacion);
        }

        return daoDiseno.obtenerComponentes(discosteo.getIdSistema());
    }

    private List<MovimientoInventario> listaMovimientos(List<ComponenteDiscosteo> componentes, String tablaUtilizada, BigDecimal cantidadDiscosteo) {
        List<MovimientoInventario> movimientos = new ArrayList<>();

        for (ComponenteDiscosteo componente : componentes) {
            ndProducto insumo = cargadorProducto.cargar(componente.getCodigoInsumo(), tablaUtilizada);
            if (insumo == null) {
                continue;
            }

            BigDecimal cantidad = componente.getCantidad().multiply(cantidadDiscosteo);
            movimientos.add(new MovimientoInventario(insumo, cantidad, BigDecimal.ZERO, "", true));
        }

        return movimientos;
    }

    private boolean esGenerico(ndProducto producto) {
        return TipoProducto.GENERICO.getValue().equals(producto.getUsuario());
    }
}
