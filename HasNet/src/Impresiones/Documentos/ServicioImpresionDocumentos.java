package Impresiones.Documentos;

import clases.metodosGenerales;

public class ServicioImpresionDocumentos {

    public static final int ORIGINAL = 0;
    private static final long PAUSA_ENTRE_IMPRESIONES_MS = 500;

    private final metodosGenerales metodos = new metodosGenerales();

    public void imprimir(TipoDocumentoImpresion tipo, ImpresionDocumento impresion) {
        ConfiguracionImpresion configuracion = ConfiguracionImpresion.de(tipo);

        if (configuracion.isPrevisualizar()) {
            impresion.generar(ORIGINAL, false);
            return;
        }

        impresion.generar(ORIGINAL, true);

        int copias = configuracion.isPreguntarCopias() ? solicitarCopias() : configuracion.getNumeroCopias();
        for (int copia = 1; copia <= copias; copia++) {
            pausar();
            impresion.generar(copia, true);
        }
    }

    public static String etiquetaCopia(int numeroCopia) {
        return numeroCopia == ORIGINAL ? "Original" : "Copia " + numeroCopia;
    }

    public static String etiquetaSoloCopias(int numeroCopia) {
        return numeroCopia == ORIGINAL ? "" : "Copia " + numeroCopia;
    }

    public static void pausar() {
        try {
            Thread.sleep(PAUSA_ENTRE_IMPRESIONES_MS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private int solicitarCopias() {
        try {
            return ConfiguracionImpresion.convertirCopias(metodos.msgIngresarEnter(null, "Cantidad de copias"));
        } catch (Exception e) {
            return 0;
        }
    }
}
