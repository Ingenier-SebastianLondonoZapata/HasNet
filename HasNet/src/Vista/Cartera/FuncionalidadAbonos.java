package Vista.Cartera;

import Controlador.Alertas.ControladorAlertas;
import Modelo.Cartera.AbonoCuenta;
import Modelo.Cartera.MediosPagoAbono;
import clases.Instancias;
import clases.big;
import clases.metodosGenerales;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * Logica de negocio de los abonos a cuentas por cobrar.
 *
 * Es la unica implementacion del guardado de un abono: la usan tanto el abono
 * por factura como el abono general, de modo que ambos flujos escriben siempre
 * los mismos registros.
 */
public class FuncionalidadAbonos {

    private static final String CONSECUTIVO_ABONO = "ABONO";
    private static final String TIPO_CXC = "ABONO";
    private static final String ESTADO_CXC = "ABONO-REALIZADO";

    /**
     * Las cuentas por cobrar no manejan tolerancia: se cancelan cuando el saldo
     * llega a cero.
     */
    private static final BigDecimal SALDO_MINIMO = BigDecimal.ZERO;

    private final Instancias instancias;
    private final RegistroAbonos registroAbonos;
    private final metodosGenerales metodos = new metodosGenerales();

    public FuncionalidadAbonos(Instancias instancias) {
        this.instancias = instancias;
        this.registroAbonos = new RegistroAbonos(instancias);
    }

    /**
     * Consecutivo interno del abono ("ABONO-12").
     */
    public String obtenerConsecutivoAbono() {
        return CONSECUTIVO_ABONO + "-" + obtenerNumeroConsecutivo();
    }

    /**
     * Consecutivo del abono con el prefijo de la empresa ("ABONO-A12"). Es el
     * que queda en los documentos y en los reportes.
     */
    public String obtenerReferenciaAbono() {
        return CONSECUTIVO_ABONO + "-" + obtenerConsecutivoMostrado();
    }

    /**
     * Consecutivo que se le muestra al usuario ("A12").
     */
    public String obtenerConsecutivoMostrado() {
        return obtenerPrefijo() + obtenerNumeroConsecutivo();
    }

    public boolean aumentarConsecutivo(String tipo) {
        return registroAbonos.aumentarConsecutivo(tipo);
    }

    /**
     * Lista los documentos (facturas) que se estan abonando.
     */
    public String unirDocumentos(List<AbonoCuenta> cuentas) {
        return registroAbonos.unirDocumentos(cuentas);
    }

    public boolean quedaSaldada(AbonoCuenta cuenta) {
        return cuenta.getSaldoRestante().compareTo(SALDO_MINIMO) <= 0;
    }

    /**
     * Guarda el abono completo: el encabezado, el movimiento de caja y, por cada
     * cuenta, la CxC, el detalle del abono y la cancelacion cuando la cuenta
     * queda saldada. Al final descuenta las notas credito utilizadas.
     *
     * @return false si alguna operacion obligatoria fallo.
     */
    public boolean registrarAbono(String idAbono, String referencia, String idTercero, String comprobante,
            List<AbonoCuenta> cuentas, MediosPagoAbono medios) {

        if (!guardarAbono(idAbono, referencia, idTercero, comprobante, cuentas, medios)) {
            return false;
        }

        descontarNotasCredito(idTercero, medios.getNotaCredito());

        return true;
    }

    /**
     * Guarda el abono de un interes de mora sobre la factura generada para el
     * cobro. La factura de mora se paga completa, asi que queda cancelada.
     *
     * No descuenta notas credito: el abono de las cuotas se guarda con el mismo
     * medio de pago y las descontaria dos veces.
     */
    public boolean registrarAbonoMora(String idAbono, String referencia, String idTercero, String comprobante,
            AbonoCuenta cuenta, MediosPagoAbono medios) {

        return guardarAbono(idAbono, referencia, idTercero, comprobante, Arrays.asList(cuenta), medios);
    }

    private boolean guardarAbono(String idAbono, String referencia, String idTercero, String comprobante,
            List<AbonoCuenta> cuentas, MediosPagoAbono medios) {

        if (!registroAbonos.guardarEncabezado(idAbono, registroAbonos.unirDocumentos(cuentas), idTercero, comprobante,
                cuentas, referencia)) {
            return false;
        }

        if (!guardarMovimientoCaja(idAbono, referencia, idTercero, registroAbonos.unirCuentas(cuentas),
                registroAbonos.sumarAbonos(cuentas), medios)) {
            return false;
        }

        for (AbonoCuenta cuenta : cuentas) {
            if (!guardarAbonoCuenta(referencia, idTercero, cuenta)) {
                return false;
            }
        }

        return true;
    }

    private boolean guardarMovimientoCaja(String idAbono, String referencia, String idTercero, String documentos,
            BigDecimal valorAbono, MediosPagoAbono medios) {

        String fecha = registroAbonos.fechaActual();

        Object[] vector = {idAbono, idTercero, "", "", fecha, fecha, medios.getEfectivo(), medios.getNotaCredito(),
            medios.getCheque(), medios.getTarjeta(), documentos, obtenerNumeroConsecutivo(), "", false, false,
            valorAbono, medios.getDescuentoFinanciero(), "0", "0", "", instancias.getUsuario(), medios.getRteIva(),
            medios.getRteIca(), medios.getRteFuente(), medios.getDescuentoPago(), "", fecha, instancias.getTerminal(),
            0, "PENDIENTE", referencia, instancias.getResolucion(), "0", "", "", "", "", "", "", "", ""};

        if (!instancias.getSql().agregarAbono(metodos.llenarAbonos(vector))) {
            ControladorAlertas.alertFail("Hubo un problema al guardar el abono en caja");
            return false;
        }

        return true;
    }

    private boolean guardarAbonoCuenta(String referencia, String idTercero, AbonoCuenta cuenta) {

        //bdCxc: factura, tipo, estado, recibo, valor, plazo, vencimiento, usuario, terminal, cuotas, factura2
        Object[] vectCxc = {cuenta.getIngreso(), TIPO_CXC, ESTADO_CXC, referencia, cuenta.getValorAbono(), "0",
            registroAbonos.fechaActual(), instancias.getUsuario(), instancias.getTerminal(), false,
            cuenta.getDocumento()};

        if (!instancias.getSql().agregarCxc(metodos.llenarCxc(vectCxc))) {
            ControladorAlertas.alertFail("Hubo un problema al guardar el abono en las cuentas por cobrar");
            return false;
        }

        if (!registroAbonos.guardarDetalle(cuenta.getDocumento(), referencia, idTercero, cuenta)) {
            return false;
        }

        if (quedaSaldada(cuenta) && !instancias.getSql().cancelarCxc(cuenta.getIngreso())) {
            ControladorAlertas.alertFail("Error al cancelar la cuenta");
            return false;
        }

        return true;
    }

    /**
     * Descuenta del saldo de las notas credito del cliente el valor que se uso
     * como medio de pago.
     */
    public void descontarNotasCredito(String idTercero, BigDecimal valorUtilizado) {

        if (valorUtilizado.compareTo(BigDecimal.ZERO) <= 0) {
            return;
        }

        BigDecimal pendiente = valorUtilizado;

        for (Object[] nota : instancias.getSql().getNcCliente(idTercero)) {
            if (pendiente.compareTo(BigDecimal.ZERO) < 0) {
                break;
            }

            BigDecimal saldoNota = big.getBigDecimal(nota[0]);
            String idNota = (String) nota[1];
            String saldoActualizado = saldoNota.compareTo(pendiente) <= 0 ? "0"
                    : String.valueOf(saldoNota.subtract(pendiente));

            if (!instancias.getSql().descontarNc(idNota, saldoActualizado)) {
                ControladorAlertas.alertFail("Error al modificar el saldo de la nota credito: " + idNota);
            }

            pendiente = pendiente.subtract(saldoNota);
        }
    }

    private String obtenerNumeroConsecutivo() {
        return (String) instancias.getSql().getNumConsecutivo(CONSECUTIVO_ABONO)[0];
    }

    private String obtenerPrefijo() {
        String prefijo = "";

        try {
            prefijo = instancias.getIdAbono();
        } catch (Exception e) {
            prefijo = "";
        }

        return prefijo == null ? "" : prefijo;
    }
}
