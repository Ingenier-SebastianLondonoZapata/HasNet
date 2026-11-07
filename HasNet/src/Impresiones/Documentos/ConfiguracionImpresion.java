package Impresiones.Documentos;

import Utilidades.DatosMaestra;

public final class ConfiguracionImpresion {

    private final boolean previsualizar;
    private final boolean preguntarCopias;
    private final int numeroCopias;

    private ConfiguracionImpresion(boolean previsualizar, boolean preguntarCopias, String numeroCopias) {
        this.previsualizar = previsualizar;
        this.preguntarCopias = preguntarCopias;
        this.numeroCopias = convertirCopias(numeroCopias);
    }

    public static ConfiguracionImpresion de(TipoDocumentoImpresion tipo) {
        switch (tipo) {
            case FACTURA:
                return new ConfiguracionImpresion(DatosMaestra.isPrevisualizarFactura(),
                        DatosMaestra.isPreguntarCopiasFactura(), DatosMaestra.getNumeroCopiasFactura());
            case ORDEN_SERVICIO:
                return new ConfiguracionImpresion(DatosMaestra.isPrevisualizarOrdenServicio(),
                        DatosMaestra.isPreguntarCopiasOrdenServicio(), DatosMaestra.getNumeroCopiasOrdenServicio());
            case COTIZACION:
                return new ConfiguracionImpresion(DatosMaestra.isPrevisualizarCotizacion(),
                        DatosMaestra.isPreguntarCopiasCotizacion(), DatosMaestra.getNumeroCopiasCotizacion());
            case PLAN_SEPARE:
                return new ConfiguracionImpresion(DatosMaestra.isPrevisualizarPlanSepare(),
                        DatosMaestra.isPreguntarCopiasPlanSepare(), DatosMaestra.getNumeroCopiasPlanSepare());
            case PEDIDO:
                return new ConfiguracionImpresion(DatosMaestra.isPrevisualizarPedido(),
                        DatosMaestra.isPreguntarCopiasPedido(), DatosMaestra.getNumeroCopiasPedido());
            default:
                throw new IllegalArgumentException("Tipo de documento sin configuración de impresión: " + tipo);
        }
    }

    static int convertirCopias(String valor) {
        if (valor == null) {
            return 0;
        }
        try {
            return Math.max(0, Integer.parseInt(valor.trim()));
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public boolean isPrevisualizar() {
        return previsualizar;
    }

    public boolean isPreguntarCopias() {
        return preguntarCopias;
    }

    public int getNumeroCopias() {
        return numeroCopias;
    }
}
