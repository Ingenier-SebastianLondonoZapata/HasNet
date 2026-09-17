package Vista.Cartera;

import Controlador.Alertas.ControladorAlertas;
import Modelo.Cartera.AbonoCuenta;
import Modelo.Cartera.MediosPagoAbono;
import clases.Instancias;
import clases.metodosGenerales;
import formularios.Tesoreria.dlgTipoEgreso;
import java.math.BigDecimal;
import java.util.List;

/**
 * Logica de negocio de los abonos a cuentas por pagar.
 *
 * Es la unica implementacion del guardado de un pago: la usan tanto el abono
 * por factura como el abono general, de modo que ambos flujos escriben siempre
 * los mismos registros.
 */
public class FuncionalidadPagos {

    private static final String CODIGO_EGRESO = "PAGOS PROVEEDORES";
    private static final String CONCEPTO_ABONO = "ABONO FACTURA";
    private static final String CONCEPTO_CANCELACION = "CANCELACION FACTURA";
    private static final String CONSECUTIVO_PAGO = "PAGO";

    /**
     * Por debajo de este saldo la cuenta por pagar se da por cancelada.
     */
    private static final BigDecimal SALDO_MINIMO = new BigDecimal("49");

    private final Instancias instancias;
    private final RegistroAbonos registroAbonos;
    private final metodosGenerales metodos = new metodosGenerales();

    public FuncionalidadPagos(Instancias instancias) {
        this.instancias = instancias;
        this.registroAbonos = new RegistroAbonos(instancias);
    }

    /**
     * Consecutivo con el que se guardara el proximo pago.
     */
    public String obtenerConsecutivoPago() {
        return CONSECUTIVO_PAGO + "-" + (String) instancias.getSql().getNumConsecutivo(CONSECUTIVO_PAGO)[0];
    }

    public String obtenerConcepto(BigDecimal saldoRestante) {
        return saldoRestante.compareTo(BigDecimal.ZERO) <= 0 ? CONCEPTO_CANCELACION : CONCEPTO_ABONO;
    }

    public boolean aumentarConsecutivo(String tipo) {
        return registroAbonos.aumentarConsecutivo(tipo);
    }

    /**
     * Lista los documentos (facturas) que se estan pagando.
     */
    public String unirDocumentos(List<AbonoCuenta> cuentas) {
        return registroAbonos.unirDocumentos(cuentas);
    }

    /**
     * Pide el tipo de egreso y registra el egreso del pago en tesoreria. Si el
     * usuario no escoge un tipo no se registra nada.
     */
    public void registrarEgreso(String tercero, BigDecimal total, String documentos, String concepto,
            String consecutivoEgreso, MediosPagoAbono medios) {

        String tipo = new dlgTipoEgreso(null, true).seleccionar();
        instancias.getEgresos().setSaltarPasos(true);

        if (!tipo.equals("")) {
            instancias.getEgresos().cargarEgreso(tercero, total, documentos, CODIGO_EGRESO, concepto, tipo,
                    consecutivoEgreso, BigDecimal.ZERO, BigDecimal.ZERO, medios.getEfectivo(), medios.getTarjeta(),
                    medios.getCheque(), BigDecimal.ZERO, BigDecimal.ZERO, "registrandoPago");
        }
    }

    /**
     * Guarda el pago completo: el encabezado del abono, el ingreso de tesoreria
     * y, por cada cuenta, la CxP, el abono general y la cancelacion cuando la
     * cuenta queda saldada.
     *
     * @param ingresoAsociado ingreso desde el que se abrio el pago.
     * @return false si alguna operacion obligatoria fallo.
     */
    public boolean registrarPago(String consecutivoPago, String idTercero, String comprobante,
            List<AbonoCuenta> cuentas, MediosPagoAbono medios, String ingresoAsociado) {

        if (!registroAbonos.guardarEncabezado(consecutivoPago, registroAbonos.unirCuentas(cuentas), idTercero,
                comprobante, cuentas, consecutivoPago)) {
            return false;
        }

        //Si falla el ingreso se avisa, pero el pago continua
        guardarIngresoPago(consecutivoPago, idTercero, medios, registroAbonos.sumarAbonos(cuentas), ingresoAsociado);

        for (AbonoCuenta cuenta : cuentas) {
            if (!guardarAbonoCuenta(consecutivoPago, idTercero, cuenta)) {
                return false;
            }
        }

        return true;
    }

    private boolean guardarIngresoPago(String consecutivoPago, String idTercero, MediosPagoAbono medios,
            BigDecimal valorAbono, String ingresoAsociado) {

        String fecha = registroAbonos.fechaActual();

        Object[] vector = {consecutivoPago, idTercero, fecha, fecha, valorAbono, BigDecimal.ZERO, BigDecimal.ZERO,
            valorAbono, "", CONSECUTIVO_PAGO, false, ingresoAsociado, instancias.getUsuario(), instancias.getTerminal(),
            medios.getRteIva(), medios.getRteFuente(), BigDecimal.ZERO, consecutivoPago, metodosGenerales.hora(),
            BigDecimal.ZERO, "PENDIENTE", medios.getRteIca(), medios.getEfectivo(), medios.getCheque(),
            medios.getTarjeta(), medios.getDescuentoFinanciero(), medios.getDescuentoPago(), "", ""};

        if (!instancias.getSql().agregarIngreso(metodos.llenarIngreso(vector))) {
            ControladorAlertas.alertFail("Hubo un problema al guardar el ingreso del pago");
            return false;
        }

        return true;
    }

    private boolean guardarAbonoCuenta(String consecutivoPago, String idTercero, AbonoCuenta cuenta) {

        Object[] vectCxp = {cuenta.getIngreso(), CONSECUTIVO_PAGO, "PEND", consecutivoPago, registroAbonos.fechaActual(),
            instancias.getUsuario(), cuenta.getValorAbono(), BigDecimal.ZERO, instancias.getTerminal()};

        if (!instancias.getSql().agregarCxp(metodos.llenarCxp(vectCxp))) {
            ControladorAlertas.alertFail("Hubo un problema al guardar las CxP");
            return false;
        }

        if (!registroAbonos.guardarDetalle(cuenta.getIngreso(), consecutivoPago, idTercero, cuenta)) {
            return false;
        }

        if (quedaSaldada(cuenta) && !instancias.getSql().cancelarCxp(cuenta.getIngreso())) {
            ControladorAlertas.alertFail("Hubo un problema al cancelar la cuenta");
        }

        return true;
    }

    private boolean quedaSaldada(AbonoCuenta cuenta) {
        return SALDO_MINIMO.compareTo(cuenta.getSaldoRestante()) > 0;
    }
}
