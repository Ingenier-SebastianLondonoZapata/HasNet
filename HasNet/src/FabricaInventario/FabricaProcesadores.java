package FabricaInventario;

import Enums.TipoDocumento;
import EstrategiaInventario.ProcesadorAjusteEntrada;
import EstrategiaInventario.ProcesadorAjusteSalida;
import EstrategiaInventario.ProcesadorAnularAjusteEntrada;
import EstrategiaInventario.ProcesadorAnularAjusteSalida;
import EstrategiaInventario.ProcesadorAnularPlanSepare;
import EstrategiaInventario.ProcesadorFacturacion;
import EstrategiaInventario.ProcesadorIngreso;
import EstrategiaInventario.ProcesadorInventarioInicial;
import EstrategiaInventario.ProcesadorMesa;
import EstrategiaInventario.ProcesadorMovimiento;
import EstrategiaInventario.ProcesadorNotaCredito;
import EstrategiaInventario.ProcesadorNotaDebito;
import EstrategiaInventario.ProcesadorOrdenServicio;
import EstrategiaInventario.ProcesadorPedido;
import EstrategiaInventario.ProcesadorPlanSepare;

public class FabricaProcesadores {

    public static ProcesadorMovimiento obtener(TipoDocumento tipoDocumento) {

        switch (tipoDocumento) {
            case FACTURACION:
                return new ProcesadorFacturacion();

            case MESA:
                return new ProcesadorMesa();

            case PEDIDO:
                return new ProcesadorPedido();

            case PLAN_SEPARE:
                return new ProcesadorPlanSepare();

            case ORDER_SERVICIO:
                return new ProcesadorOrdenServicio();

            case NOTA_DEBITO:
                return new ProcesadorNotaDebito();

            case NOTA_CREDITO:
                return new ProcesadorNotaCredito();

            case INVENTARIO_INICIAL:
                return new ProcesadorInventarioInicial();

            case COMPRA:
                return new ProcesadorIngreso();

            case AJUSTE_ENTRADA:
                return new ProcesadorAjusteEntrada();

            case ANULAR_AJUSTE_ENTRADA:
                return new ProcesadorAnularAjusteEntrada();

            case AJUSTE_SALIDA:
                return new ProcesadorAjusteSalida();

            case ANULAR_AJUSTE_SALIDA:
                return new ProcesadorAnularAjusteSalida();

            case ANULAR_PLAN_SEPARE:
                return new ProcesadorAnularPlanSepare();

            default:
                throw new IllegalArgumentException("Movimiento no soportado: " + tipoDocumento);
        }
    }
}
