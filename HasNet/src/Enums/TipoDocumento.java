package Enums;

public enum TipoDocumento {

    FACTURACION("facturacion", "FACT"),
    ANULAR_FACTURACION("anular_facturacion", ""),
    PEDIDO("pedido", "PEDIDO"),
    MESA("mesa", "CONGELADA"),
    ORDER_SERVICIO("orden", "OSERV"),
    CUENTA_COBRO("cuentaCobro", "CCOBRO"),
    COTIZACION("cotizacion", "COTI"),
    PLAN_SEPARE("separe", "SEPARE"),
    ANULAR_PLAN_SEPARE("anularPlanSepare", ""),
    CREDITO("credito", ""),
    NOTA_DEBITO("notaDebito", ""),
    NOTA_CREDITO("notaCredito", ""),
    AJUSTE_ENTRADA("ajusteEntrada", ""),
    ANULAR_AJUSTE_ENTRADA("anularAjusteEntrada", ""),
    AJUSTE_SALIDA("ajusteSalida", ""),
    ANULAR_AJUSTE_SALIDA("anularAjusteSalida", ""),
    COMPRA("ingreso", ""),
    ANULAR_COMPRA("anularCompra", ""),
    ORDEN_COMPRA("ordenCompra", ""),
    ANULAR_ORDEN_COMPRA("anularOrdenCompra", ""),
    EGRESO("egreso", ""),
    INVENTARIO_INICIAL("inventarioInicial", "");

    private final String valor;
    private final String prefijoGeneral;

    TipoDocumento(String valor, String prefijoGeneral) {
        this.valor = valor;
        this.prefijoGeneral = prefijoGeneral;
    }

    public String getValor() {
        return valor;
    }

    public String getPrefijoGeneral() {
        return prefijoGeneral;
    }

    public static TipoDocumento fromValue(String valor) {
        for (TipoDocumento tipo : values()) {
            if (tipo.valor.equals(valor)) {
                return tipo;
            }
        }

        throw new IllegalArgumentException("Tipo documento no soportado: " + valor);
    }

    public static String obtenerPrefijoGeneralPorValor(String valor) {
        return fromValue(valor).getPrefijoGeneral();
    }
}
