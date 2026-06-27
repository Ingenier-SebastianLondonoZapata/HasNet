package inventario.servicio;

import Modelo.Inventario.MovimientoInventario;
import Modelo.Ventas.FilaProductoTabla;
import Modelo.Ventas.ResultadoValidacionInventario;
import Utilidades.Utilidades;
import clases.productos.ndProducto;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Valida si hay inventario suficiente para procesar un documento de venta.
 * No conoce componentes Swing — recibe datos puros y devuelve resultados puros.
 * La validación de márgenes de utilidad es responsabilidad de squemaFacturacion.
 */
public class ServicioValidacionFactura {

    private static final String ID_BOLSA = "PROD-000000032";
    private static final String TIPO_DISCOSTEO = "FACTURA";
    private static final String ERROR_EXPLOTAR = "Error al explotar componentes de ";

    private final CargadorProducto cargadorProducto;

    public ServicioValidacionFactura(CargadorProducto cargadorProducto) {
        this.cargadorProducto = cargadorProducto;
    }

    public ResultadoValidacionInventario validar(List<FilaProductoTabla> filas, String baseUtilizada) {
        List<Object[]> productosSinInventario = new ArrayList<>();
        List<Object[]> productosSinInventarioDiscosteo = new ArrayList<>();
        boolean tieneBolsa = false;

        ServicioDiscosteo servicioDiscosteo = new ServicioDiscosteo(cargadorProducto);

        for (FilaProductoTabla fila : filas) {
            ndProducto producto = cargadorProducto.cargar(fila.getIdProducto(), baseUtilizada);

            if (ID_BOLSA.equals(fila.getIdProducto())) {
                tieneBolsa = true;
            }

            if (!producto.getManejaInventario()) continue;

            if (TIPO_DISCOSTEO.equals(producto.getUsuario())) {
                validarComponentesDiscosteo(producto, fila, baseUtilizada, servicioDiscosteo, productosSinInventarioDiscosteo);
            } else {
                validarInventarioNormal(producto, productosSinInventario);
            }
        }

        return new ResultadoValidacionInventario(productosSinInventario, productosSinInventarioDiscosteo, tieneBolsa);
    }

    private void validarInventarioNormal(ndProducto producto, List<Object[]> productosSinInventario) {
        BigDecimal fisicoInventario = Utilidades.convertirBigDecimal(producto.getFisicoInventario());
        if (fisicoInventario.compareTo(BigDecimal.ZERO) < 0) {
            productosSinInventario.add(new Object[]{
                producto.getIdSistema(),
                producto.getDescripcion(),
                producto.getFisicoInventario(),
                fisicoInventario
            });
        }
    }

    private void validarComponentesDiscosteo(
            ndProducto producto,
            FilaProductoTabla fila,
            String baseUtilizada,
            ServicioDiscosteo servicioDiscosteo,
            List<Object[]> productosSinInventarioDis) {
        List<MovimientoInventario> componentes;
        try {
            componentes = servicioDiscosteo.explotar(producto, fila.getPreparacion(), baseUtilizada, fila.getCantidad());
        } catch (SQLException e) {
            System.err.println(ERROR_EXPLOTAR + producto.getIdSistema() + ": " + e.getMessage());
            return;
        }

        for (MovimientoInventario componente : componentes) {
            ndProducto insumo = componente.getProducto();
            if (insumo == null) continue;

            BigDecimal disponible = Utilidades.convertirBigDecimal(insumo.getFisicoInventario());
            BigDecimal faltante = disponible.subtract(componente.getCantidad());

            if (faltante.compareTo(BigDecimal.ZERO) < 0) {
                productosSinInventarioDis.add(new Object[]{
                    insumo.getIdSistema(),
                    insumo.getDescripcion(),
                    disponible,
                    faltante,
                    componente.getCantidad()
                });
            }
        }
    }
}
