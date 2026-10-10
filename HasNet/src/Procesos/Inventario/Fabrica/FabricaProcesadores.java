package Procesos.Inventario.Fabrica;

import Enums.TipoDocumento;
import Procesos.Inventario.Estrategia.ProcesadorAjusteEntrada;
import Procesos.Inventario.Estrategia.ProcesadorAjusteSalida;
import Procesos.Inventario.Estrategia.ProcesadorAnularAjusteEntrada;
import Procesos.Inventario.Estrategia.ProcesadorAnularFacturacion;
import Procesos.Inventario.Estrategia.ProcesadorAnularAjusteSalida;
import Procesos.Inventario.Estrategia.ProcesadorAnularPlanSepare;
import Procesos.Inventario.Estrategia.ProcesadorAnularPedido;
import Procesos.Inventario.Estrategia.ProcesadorAnularMesa;
import Procesos.Inventario.Estrategia.ProcesadorAnularOrdenServicio;
import Procesos.Inventario.Estrategia.ProcesadorFacturacion;
import Procesos.Inventario.Estrategia.ProcesadorCompra;
import Procesos.Inventario.Estrategia.ProcesadorInventarioInicial;
import Procesos.Inventario.Estrategia.ProcesadorMesa;
import Procesos.Inventario.Estrategia.ProcesadorMovimiento;
import Procesos.Inventario.Estrategia.ProcesadorNotaCredito;
import Procesos.Inventario.Estrategia.ProcesadorNotaDebito;
import Procesos.Inventario.Estrategia.ProcesadorAnularCompra;
import Procesos.Inventario.Estrategia.ProcesadorAnularOrdenCompra;
import Procesos.Inventario.Estrategia.ProcesadorOrdenCompra;
import Procesos.Inventario.Estrategia.ProcesadorOrdenServicio;
import Procesos.Inventario.Estrategia.ProcesadorPedido;
import Procesos.Inventario.Estrategia.ProcesadorPlanSepare;
import java.util.EnumMap;
import java.util.Map;

public class FabricaProcesadores {

    private static final Map<TipoDocumento, ProcesadorMovimiento> PROCESADORES = construirRegistro();
    private static final String MOVIMIENTO_NO_SOPORTADO = "Movimiento no soportado: ";

    private FabricaProcesadores() {
    }

    private static Map<TipoDocumento, ProcesadorMovimiento> construirRegistro() {
        Map<TipoDocumento, ProcesadorMovimiento> registro = new EnumMap<>(TipoDocumento.class);
        registro.put(TipoDocumento.FACTURACION, new ProcesadorFacturacion());
        registro.put(TipoDocumento.ANULAR_FACTURACION, new ProcesadorAnularFacturacion());
        registro.put(TipoDocumento.MESA, new ProcesadorMesa());
        registro.put(TipoDocumento.PEDIDO, new ProcesadorPedido());
        registro.put(TipoDocumento.PLAN_SEPARE, new ProcesadorPlanSepare());
        registro.put(TipoDocumento.ORDER_SERVICIO, new ProcesadorOrdenServicio());
        registro.put(TipoDocumento.NOTA_DEBITO, new ProcesadorNotaDebito());
        registro.put(TipoDocumento.NOTA_CREDITO, new ProcesadorNotaCredito());
        registro.put(TipoDocumento.INVENTARIO_INICIAL, new ProcesadorInventarioInicial());
        registro.put(TipoDocumento.COMPRA, new ProcesadorCompra());
        registro.put(TipoDocumento.ANULAR_COMPRA, new ProcesadorAnularCompra());
        registro.put(TipoDocumento.ORDEN_COMPRA, new ProcesadorOrdenCompra());
        registro.put(TipoDocumento.ANULAR_ORDEN_COMPRA, new ProcesadorAnularOrdenCompra());
        registro.put(TipoDocumento.AJUSTE_ENTRADA, new ProcesadorAjusteEntrada());
        registro.put(TipoDocumento.ANULAR_AJUSTE_ENTRADA, new ProcesadorAnularAjusteEntrada());
        registro.put(TipoDocumento.AJUSTE_SALIDA, new ProcesadorAjusteSalida());
        registro.put(TipoDocumento.ANULAR_AJUSTE_SALIDA, new ProcesadorAnularAjusteSalida());
        registro.put(TipoDocumento.ANULAR_PLAN_SEPARE, new ProcesadorAnularPlanSepare());
        registro.put(TipoDocumento.ANULAR_PEDIDO, new ProcesadorAnularPedido());
        registro.put(TipoDocumento.ANULAR_MESA, new ProcesadorAnularMesa());
        registro.put(TipoDocumento.ANULAR_ORDER_SERVICIO, new ProcesadorAnularOrdenServicio());

        return registro;
    }

    public static ProcesadorMovimiento obtener(TipoDocumento tipoDocumento) {
        ProcesadorMovimiento procesador = PROCESADORES.get(tipoDocumento);
        if (procesador == null) {
            throw new IllegalArgumentException(MOVIMIENTO_NO_SOPORTADO + tipoDocumento);
        }

        return procesador;
    }
}
