package Procesos.Inventario.Servicio;

import Enums.TipoProducto;
import Modelo.Inventario.MovimientoInventario;
import Modelo.Ventas.FilaProductoTabla;
import Modelo.Ventas.ResultadoValidacionInventario;
import Modelo.Productos.ModeloProducto;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ServicioValidacionFactura {

    private static final String ID_BOLSA = "PROD-000000032";
    private static final String ERROR_EXPLOTAR = "Error al explotar componentes de ";

    private final CargadorProducto cargadorProducto;

    public ServicioValidacionFactura(CargadorProducto cargadorProducto) {
        this.cargadorProducto = cargadorProducto;
    }

    public ResultadoValidacionInventario validar(List<FilaProductoTabla> filas, String idBodega) {
        boolean tieneBolsa = false;

        ServicioDiscosteo servicioDiscosteo = new ServicioDiscosteo(cargadorProducto);

        Map<String, BigDecimal> cantidadAcumulada = new LinkedHashMap<>();
        Map<String, ModeloProducto> productosPorId = new LinkedHashMap<>();

        for (FilaProductoTabla fila : filas) {
            ModeloProducto producto = cargadorProducto.cargar(fila.getIdProducto(), idBodega);

            if (ID_BOLSA.equals(fila.getIdProducto())) {
                tieneBolsa = true;
            }

            if (TipoProducto.PRODUCTO_DISENADO.getValue().equals(producto.getUsuario())) {
                acumularComponentesDiscosteo(producto, fila, idBodega, servicioDiscosteo,
                        cantidadAcumulada, productosPorId);
            } else if (producto.getManejaInventario()) {
                acumularProductoNormal(producto, fila, cantidadAcumulada, productosPorId);
            }
        }

        return new ResultadoValidacionInventario(validarAcumulados(cantidadAcumulada, productosPorId), tieneBolsa);
    }

    private void acumularProductoNormal(ModeloProducto producto, FilaProductoTabla fila,
            Map<String, BigDecimal> cantidadAcumulada, Map<String, ModeloProducto> productosPorId) {
        String id = producto.getIdSistema();
        BigDecimal acumulado = cantidadAcumulada.containsKey(id) ? cantidadAcumulada.get(id) : BigDecimal.ZERO;
        cantidadAcumulada.put(id, acumulado.add(fila.getCantidad()));
        productosPorId.put(id, producto);
    }

    private void acumularComponentesDiscosteo(ModeloProducto producto, FilaProductoTabla fila, String idBodega, ServicioDiscosteo servicioDiscosteo,
            Map<String, BigDecimal> cantidadAcumulada, Map<String, ModeloProducto> productosPorId) {

        List<MovimientoInventario> componentes;

        try {
            componentes = servicioDiscosteo.explotar(producto, fila.getPreparacion(), idBodega, fila.getCantidad());
        } catch (SQLException e) {
            System.err.println(ERROR_EXPLOTAR + producto.getIdSistema() + ": " + e.getMessage());
            return;
        }

        for (MovimientoInventario componente : componentes) {
            ModeloProducto insumo = componente.getProducto();
            if (insumo == null) {
                continue;
            }

            String id = insumo.getIdSistema();
            BigDecimal acumulado = cantidadAcumulada.containsKey(id) ? cantidadAcumulada.get(id) : BigDecimal.ZERO;
            cantidadAcumulada.put(id, acumulado.add(componente.getCantidad()));
            productosPorId.put(id, insumo);
        }
    }

    private List<Object[]> validarAcumulados(Map<String, BigDecimal> cantidadAcumulada, Map<String, ModeloProducto> productosPorId) {

        List<Object[]> sinInventario = new ArrayList<>();

        for (Map.Entry<String, BigDecimal> entry : cantidadAcumulada.entrySet()) {
            ModeloProducto producto = productosPorId.get(entry.getKey());
            BigDecimal necesario = entry.getValue();
            BigDecimal disponible = producto.getFisicoInventario();

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
