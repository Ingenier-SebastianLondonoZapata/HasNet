package inventario.servicio;

import Enums.TipoProducto;
import Modelo.Inventario.MovimientoInventario;
import Modelo.Ventas.FilaProductoTabla;
import Modelo.Ventas.ResultadoValidacionInventario;
import Utilidades.Utilidades;
import clases.productos.ndProducto;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Valida si hay inventario suficiente para procesar un documento de venta. No
 * conoce componentes Swing — recibe datos puros y devuelve resultados puros. La
 * validación de márgenes de utilidad es responsabilidad de squemaFacturacion.
 */
public class ServicioValidacionFactura {

    private static final String ID_BOLSA = "PROD-000000032";
    private static final String ERROR_EXPLOTAR = "Error al explotar componentes de ";

    private final CargadorProducto cargadorProducto;

    public ServicioValidacionFactura(CargadorProducto cargadorProducto) {
        this.cargadorProducto = cargadorProducto;
    }

    /**
     * Acumula las cantidades necesarias por ID de producto (directos y
     * discosteo) y valida contra stock en un único paso, evitando duplicados y
     * sumando correctamente cuando el mismo insumo aparece en varios contextos.
     */
    public ResultadoValidacionInventario validar(List<FilaProductoTabla> filas, String baseUtilizada) {
        boolean tieneBolsa = false;

        ServicioDiscosteo servicioDiscosteo = new ServicioDiscosteo(cargadorProducto);

        Map<String, BigDecimal> cantidadAcumulada = new LinkedHashMap<String, BigDecimal>();
        Map<String, ndProducto> productosPorId = new LinkedHashMap<String, ndProducto>();

        for (FilaProductoTabla fila : filas) {
            ndProducto producto = cargadorProducto.cargar(fila.getIdProducto(), baseUtilizada);

            if (ID_BOLSA.equals(fila.getIdProducto())) {
                tieneBolsa = true;
            }

            if (TipoProducto.PRODUCTO_DISENADO.getValue().equals(producto.getUsuario())) {
                acumularComponentesDiscosteo(producto, fila, baseUtilizada, servicioDiscosteo,
                        cantidadAcumulada, productosPorId);
            } else if (producto.getManejaInventario()) {
                acumularProductoNormal(producto, fila, cantidadAcumulada, productosPorId);
            }
        }

        return new ResultadoValidacionInventario(validarAcumulados(cantidadAcumulada, productosPorId), tieneBolsa);
    }

    private void acumularProductoNormal(ndProducto producto, FilaProductoTabla fila,
            Map<String, BigDecimal> cantidadAcumulada, Map<String, ndProducto> productosPorId) {
        String id = producto.getIdSistema();
        BigDecimal acumulado = cantidadAcumulada.containsKey(id) ? cantidadAcumulada.get(id) : BigDecimal.ZERO;
        cantidadAcumulada.put(id, acumulado.add(fila.getCantidad()));
        productosPorId.put(id, producto);
    }

    private void acumularComponentesDiscosteo(ndProducto producto, FilaProductoTabla fila, String baseUtilizada, ServicioDiscosteo servicioDiscosteo,
            Map<String, BigDecimal> cantidadAcumulada, Map<String, ndProducto> productosPorId) {

        List<MovimientoInventario> componentes;

        try {
            componentes = servicioDiscosteo.explotar(producto, fila.getPreparacion(), baseUtilizada, fila.getCantidad());
        } catch (SQLException e) {
            System.err.println(ERROR_EXPLOTAR + producto.getIdSistema() + ": " + e.getMessage());
            return;
        }

        for (MovimientoInventario componente : componentes) {
            ndProducto insumo = componente.getProducto();
            if (insumo == null) {
                continue;
            }

            String id = insumo.getIdSistema();
            BigDecimal acumulado = cantidadAcumulada.containsKey(id) ? cantidadAcumulada.get(id) : BigDecimal.ZERO;
            cantidadAcumulada.put(id, acumulado.add(componente.getCantidad()));
            productosPorId.put(id, insumo);
        }
    }

    private List<Object[]> validarAcumulados(Map<String, BigDecimal> cantidadAcumulada, Map<String, ndProducto> productosPorId) {

        List<Object[]> sinInventario = new ArrayList<>();

        for (Map.Entry<String, BigDecimal> entry : cantidadAcumulada.entrySet()) {
            ndProducto producto = productosPorId.get(entry.getKey());
            BigDecimal necesario = entry.getValue();
            BigDecimal disponible = Utilidades.convertirBigDecimal(producto.getFisicoInventario());

            if (disponible.subtract(necesario).compareTo(BigDecimal.ZERO) < 0) {
                sinInventario.add(new Object[]{
                    producto.getIdSistema(),
                    producto.getDescripcion(),
                    disponible,
                    necesario
                });
            }
        }

        return sinInventario;
    }
}
