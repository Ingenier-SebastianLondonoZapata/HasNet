package Enums;

public enum TipoDocumento {

    FACTURACION("facturacion"),
    ANULAR_FACTURACION("anular_facturacion"),
    
    PEDIDO("pedido"),
    MESA("mesa"),
    ORDER_SERVICIO("orden"),
    CUENTA_COBRO("cuentaCobro"),
    COTIZACION("cotizacion"),
    PLAN_SEPARE("separe"),
    ANULAR_PLAN_SEPARE("anularPlanSepare"),
    CREDITO("credito"),

    NOTA_DEBITO("notaDebito"),
    NOTA_CREDITO("notaCredito"),
    
    AJUSTE_ENTRADA("ajusteEntrada"),
    ANULAR_AJUSTE_ENTRADA("anularAjusteEntrada"),
    AJUSTE_SALIDA("ajusteSalida"),
    ANULAR_AJUSTE_SALIDA("anularAjusteSalida"),

    COMPRA("ingreso"),
    ANULAR_COMPRA("anularCompra"),
    ORDEN_COMPRA("ordenCompra"),
    ANULAR_ORDEN_COMPRA("anularOrdenCompra"),
    EGRESO("egreso"),

    INVENTARIO_INICIAL("inventarioInicial");

    private final String valor;

    TipoDocumento(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    public static TipoDocumento fromValue(String valor) {
        for (TipoDocumento tipo : values()) {
            if (tipo.valor.equals(valor)) {
                return tipo;
            }
        }

        throw new IllegalArgumentException("Tipo documento no soportado: " + valor);
    }
}