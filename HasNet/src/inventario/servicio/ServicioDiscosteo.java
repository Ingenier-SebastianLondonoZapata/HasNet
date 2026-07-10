package inventario.servicio;

import Enums.TipoProducto;
import Modelo.Inventario.ComponenteDiscosteo;
import Modelo.Inventario.MovimientoInventario;
import Modelo.Inventario.UltimoPonderado;
import Utilidades.Ventas.ParserPreparacion;
import clases.productos.ndProducto;
import dao.Productos.DaoDiseno;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServicioDiscosteo {

    private final DaoDiseno daoDiseno;
    private final CargadorProducto cargadorProducto;
    private final ServicioActualizacionPonderado servicioActualizacionPonderado;

    public ServicioDiscosteo(CargadorProducto cargadorProducto) {
        this.daoDiseno = new DaoDiseno();
        this.cargadorProducto = cargadorProducto;
        this.servicioActualizacionPonderado = new ServicioActualizacionPonderado();
    }

    public ServicioDiscosteo(CargadorProducto cargadorProducto, ServicioActualizacionPonderado servicioActualizacionPonderado) {
        this.daoDiseno = new DaoDiseno();
        this.cargadorProducto = cargadorProducto;
        this.servicioActualizacionPonderado = servicioActualizacionPonderado;
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

    public BigDecimal calcularCostoPreparacion(ndProducto producto, String preparacion, String tablaUtilizada, BigDecimal cantidad) throws SQLException {
        BigDecimal costoTotal = BigDecimal.ZERO;

        List<MovimientoInventario> movimientos = explotarSiEsDiscosteo(producto, preparacion, tablaUtilizada, cantidad);

        for (MovimientoInventario movimiento : movimientos) {
            ndProducto insumo = movimiento.getProducto();
            BigDecimal cantidadInsumo = movimiento.getCantidad();

            try {
                UltimoPonderado ultimoPonderado = servicioActualizacionPonderado.obtenerUltimoPonderado(insumo.getIdSistema());
                BigDecimal ponderado = ultimoPonderado.getNuevoPonderado();
                BigDecimal costoInsumo = cantidadInsumo.multiply(ponderado);
                costoTotal = costoTotal.add(costoInsumo);
            } catch (SQLException ex) {
                Logger.getLogger(ServicioDiscosteo.class.getName()).log(Level.SEVERE,
                        "Error al obtener ponderado del insumo: " + insumo.getIdSistema(), ex);
                throw ex;
            }
        }

        return costoTotal;
    }
}
