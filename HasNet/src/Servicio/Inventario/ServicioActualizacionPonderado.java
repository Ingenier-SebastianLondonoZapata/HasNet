package Servicio.Inventario;

import DAO.Inventario.DaoPonderado;
import Modelo.Inventario.UltimoPonderado;
import Enums.HistoricoPonderados;
import Modelo.Inventario.PonderadoPendiente;
import Utilidades.Utilidades;
import clases.productos.ndProducto;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;

public class ServicioActualizacionPonderado {

    private final DaoPonderado daoPonderado = new DaoPonderado();

    public UltimoPonderado obtenerUltimoPonderado(String producto) throws SQLException {
        return daoPonderado.obtenerUltimoPonderado(producto);
    }

    public PonderadoPendiente calcular(ndProducto producto, BigDecimal cantidadIngresada, BigDecimal valorProducto) throws SQLException {
        UltimoPonderado ultimo = daoPonderado.obtenerUltimoPonderado(producto.getIdSistema());
        BigDecimal inventarioAnterior = Utilidades.convertirBigDecimal(producto.getInventario());
        BigDecimal ponderadoAnterior = ultimo.getNuevoPonderado();
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

    public void crearPonderadoInicial(ndProducto producto, BigDecimal costoInicial, String usuario) throws SQLException {
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
