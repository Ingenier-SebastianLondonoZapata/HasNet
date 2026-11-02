package Procesos.Inventario.Servicio;

import Procesos.Inventario.Dao.DaoPonderado;
import Modelo.Inventario.UltimoPonderado;
import Enums.HistoricoPonderados;
import Modelo.Inventario.PonderadoPendiente;
import Modelo.Productos.ModeloProducto;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.List;

public class ServicioActualizacionPonderado {

    private final DaoPonderado daoPonderado = new DaoPonderado();

    public UltimoPonderado obtenerUltimoPonderado(String producto) throws SQLException {
        return daoPonderado.obtenerUltimoPonderado(producto);
    }

    public UltimoPonderado obtenerOCrearUltimoPonderado(String idProducto, String usuario) throws SQLException {
        UltimoPonderado ultimo = daoPonderado.obtenerUltimoPonderado(idProducto);
        if (!ultimo.getFechaCompra().isEmpty()) {
            return ultimo;
        }

        PonderadoPendiente calculo = new PonderadoPendiente(idProducto, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO,
                BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
        daoPonderado.crearUltimoPonderado(idProducto, BigDecimal.ZERO, usuario);
        daoPonderado.guardarHistorico(calculo, usuario, HistoricoPonderados.CREACION_PRODUCTO.getNombre());
        return daoPonderado.obtenerUltimoPonderado(idProducto);
    }

    public PonderadoPendiente calcular(ModeloProducto producto, BigDecimal cantidadIngresada, BigDecimal valorProducto,
            List<PonderadoPendiente> pendientes) throws SQLException {
        BigDecimal inventarioAnterior = producto.getInventario();
        if(inventarioAnterior.compareTo(BigDecimal.ZERO) < 0) {
            inventarioAnterior = BigDecimal.ZERO;
        }

        BigDecimal ponderadoAnterior = obtenerPonderadoVigente(producto.getIdSistema(), pendientes);
        BigDecimal inventarioNuevo = inventarioAnterior.add(cantidadIngresada);
        BigDecimal ultimoCosto = valorProducto;
        BigDecimal totalAnterior = inventarioAnterior.multiply(ponderadoAnterior);
        BigDecimal totalNuevo = cantidadIngresada.multiply(ultimoCosto);
        BigDecimal valorInventario = totalAnterior.add(totalNuevo);
        BigDecimal ponderadoNuevo = valorInventario.divide(inventarioNuevo, 6, RoundingMode.HALF_UP);
        
        PonderadoPendiente resultado = new PonderadoPendiente();
        resultado.setProducto(producto.getIdSistema());
        resultado.setPonderadoAnterior(ponderadoAnterior);
        resultado.setInventarioAnterior(inventarioAnterior);
        resultado.setCantidadIngresada(cantidadIngresada);
        resultado.setPonderadoNuevo(ponderadoNuevo);
        resultado.setInventarioNuevo(inventarioNuevo);
        resultado.setUltimoCosto(ultimoCosto);
        return resultado;
    }

    private BigDecimal obtenerPonderadoVigente(String idProducto, List<PonderadoPendiente> pendientes) throws SQLException {
        if (pendientes != null) {
            for (int i = pendientes.size() - 1; i >= 0; i--) {
                PonderadoPendiente pendiente = pendientes.get(i);
                if (idProducto.equals(pendiente.getProducto())) {
                    return pendiente.getPonderadoNuevo();
                }
            }
        }
        return daoPonderado.obtenerUltimoPonderado(idProducto).getNuevoPonderado();
    }

    public void crearPonderadoInicial(ModeloProducto producto, BigDecimal costoInicial, String usuario) throws SQLException {
        PonderadoPendiente calculo = new PonderadoPendiente(
                producto.getIdSistema(),
                BigDecimal.ZERO,
                BigDecimal.ZERO,
                BigDecimal.ZERO,
                costoInicial,
                BigDecimal.ZERO,
                costoInicial);

        daoPonderado.crearUltimoPonderado(producto.getIdSistema(), costoInicial, usuario);
        daoPonderado.guardarHistorico(calculo, usuario, HistoricoPonderados.CREACION_PRODUCTO.getNombre());
    }
}
