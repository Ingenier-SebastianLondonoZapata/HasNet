package inventario.fabrica;

import Enums.TipoDocumento;
import inventario.estrategia.ProcesadorAjusteEntrada;
import inventario.estrategia.ProcesadorAjusteSalida;
import inventario.estrategia.ProcesadorAnularAjusteEntrada;
import inventario.estrategia.ProcesadorAnularFacturacion;
import inventario.estrategia.ProcesadorAnularAjusteSalida;
import inventario.estrategia.ProcesadorAnularPlanSepare;
import inventario.estrategia.ProcesadorFacturacion;
import inventario.estrategia.ProcesadorIngreso;
import inventario.estrategia.ProcesadorInventarioInicial;
import inventario.estrategia.ProcesadorMesa;
import inventario.estrategia.ProcesadorMovimiento;
import inventario.estrategia.ProcesadorNotaCredito;
import inventario.estrategia.ProcesadorNotaDebito;
import inventario.estrategia.ProcesadorOrdenServicio;
import inventario.estrategia.ProcesadorPedido;
import inventario.estrategia.ProcesadorPlanSepare;
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
        registro.put(TipoDocumento.COMPRA, new ProcesadorIngreso());
        registro.put(TipoDocumento.AJUSTE_ENTRADA, new ProcesadorAjusteEntrada());
        registro.put(TipoDocumento.ANULAR_AJUSTE_ENTRADA, new ProcesadorAnularAjusteEntrada());
        registro.put(TipoDocumento.AJUSTE_SALIDA, new ProcesadorAjusteSalida());
        registro.put(TipoDocumento.ANULAR_AJUSTE_SALIDA, new ProcesadorAnularAjusteSalida());
        registro.put(TipoDocumento.ANULAR_PLAN_SEPARE, new ProcesadorAnularPlanSepare());

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
