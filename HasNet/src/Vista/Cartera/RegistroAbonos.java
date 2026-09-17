package Vista.Cartera;

import Controlador.Alertas.ControladorAlertas;
import Modelo.Cartera.AbonoCuenta;
import clases.Instancias;
import clases.big;
import clases.convertirNumeroALetras;
import clases.metodosGenerales;
import java.math.BigDecimal;
import java.util.List;

/**
 * Registros comunes a los abonos de cartera: el encabezado del abono (bdAbono)
 * y el detalle por documento (bdAbonoGeneral).
 *
 * Los usan tanto las cuentas por pagar como las cuentas por cobrar, de modo que
 * ambos modulos escriben estas dos tablas de la misma forma.
 */
public class RegistroAbonos {

    private static final String SEPARADOR_DOCUMENTOS = ", ";

    private final Instancias instancias;
    private final metodosGenerales metodos = new metodosGenerales();
    private final convertirNumeroALetras convertirNumeroALetras = new convertirNumeroALetras();

    public RegistroAbonos(Instancias instancias) {
        this.instancias = instancias;
    }

    /**
     * Encabezado del abono con los totales de todos los documentos abonados.
     *
     * @param referencia consecutivo con el que se identifica el abono ante el
     * usuario.
     */
    public boolean guardarEncabezado(String idAbono, String documentos, String idTercero, String comprobante,
            List<AbonoCuenta> cuentas, String referencia) {

        BigDecimal valorCuentas = BigDecimal.ZERO, abonosAnteriores = BigDecimal.ZERO, saldo = BigDecimal.ZERO;

        for (AbonoCuenta cuenta : cuentas) {
            valorCuentas = valorCuentas.add(cuenta.getValorCuenta());
            abonosAnteriores = abonosAnteriores.add(cuenta.getAbonadoAnterior());
            saldo = saldo.add(cuenta.getSaldoRestante());
        }

        BigDecimal abono = sumarAbonos(cuentas);

        //bdAbono: id, numFactura, cliente, comprobante, totalFactura, abonoLetras,
        //abonosAnteriores, saldo, fecha, abono, abonoActual
        Object[] infoAbono = {idAbono, documentos, idTercero, comprobante, valorCuentas, convertirALetras(abono),
            abonosAnteriores, saldo, fechaActual(), abono, referencia};

        if (!instancias.getSql().agregarRegistroAbono(infoAbono)) {
            ControladorAlertas.alertFail("Hubo un problema al guardar el abono");
            return false;
        }

        return true;
    }

    /**
     * Detalle del abono aplicado a un documento.
     */
    public boolean guardarDetalle(String documento, String referencia, String idTercero, AbonoCuenta cuenta) {

        //bdAbonoGeneral: abonoGeneral, abono, fecha, usuario, tercero, valor, enLetra, saldoTotal
        Object[] infoAbonoGeneral = {documento, referencia, fechaActual(), instancias.getUsuario(), idTercero,
            big.setMoneda(cuenta.getValorAbono()), convertirALetras(cuenta.getValorAbono()), cuenta.getValorCuenta()};

        if (!instancias.getSql().agregarAbonoGeneral(infoAbonoGeneral)) {
            ControladorAlertas.alertFail("Hubo un problema al guardar el abono general");
            return false;
        }

        return true;
    }

    public boolean aumentarConsecutivo(String tipo) {
        int siguiente = Integer.parseInt((String) instancias.getSql().getNumConsecutivo(tipo)[0]) + 1;

        if (!instancias.getSql().aumentarConsecutivo(tipo, siguiente)) {
            ControladorAlertas.alertFail("Hubo un problema al guardar en el consecutivo de " + tipo);
            return false;
        }

        return true;
    }

    public BigDecimal sumarAbonos(List<AbonoCuenta> cuentas) {
        BigDecimal total = BigDecimal.ZERO;

        for (AbonoCuenta cuenta : cuentas) {
            total = total.add(cuenta.getValorAbono());
        }

        return total;
    }

    /**
     * Lista los documentos (facturas) que se estan abonando.
     */
    public String unirDocumentos(List<AbonoCuenta> cuentas) {
        StringBuilder documentos = new StringBuilder();

        for (AbonoCuenta cuenta : cuentas) {
            if (documentos.length() > 0) {
                documentos.append(SEPARADOR_DOCUMENTOS);
            }
            documentos.append(cuenta.getDocumento());
        }

        return documentos.toString();
    }

    /**
     * Lista los identificadores internos de las cuentas que se estan abonando.
     */
    public String unirCuentas(List<AbonoCuenta> cuentas) {
        StringBuilder ingresos = new StringBuilder();

        for (AbonoCuenta cuenta : cuentas) {
            if (ingresos.length() > 0) {
                ingresos.append(SEPARADOR_DOCUMENTOS);
            }
            ingresos.append(cuenta.getIngreso());
        }

        return ingresos.toString();
    }

    public String convertirALetras(BigDecimal valor) {
        try {
            return convertirNumeroALetras.Convertir(valor.toString());
        } catch (Exception e) {
            return "";
        }
    }

    public String fechaActual() {
        return metodos.fechaConsulta(metodosGenerales.fecha());
    }
}
