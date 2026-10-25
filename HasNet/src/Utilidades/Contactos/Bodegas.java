package Utilidades.Contactos;

import Utilidades.DatosMaestra;

public final class Bodegas {

    public static final String PRINCIPAL = "BODEGA-1";
    public static final String TIPO_INTERNA = "INTERNA";
    public static final String TIPO_EXTERNA = "EXTERNA";

    public static final String ESTADO_ACTIVO = "1";
    public static final String ESTADO_INACTIVO = "0";
    public static final String ETIQUETA_ACTIVO = "ACTIVO";
    public static final String ETIQUETA_INACTIVO = "INACTIVO";
    
    public static final String AJUSTE_EXTERNO = "123-11";

    private Bodegas() {
    }

    public static String predeterminada() {
        String configurada = DatosMaestra.getBodega();
        return configurada.isEmpty() ? PRINCIPAL : configurada;
    }

    public static String resolver(String bodega) {
        return estaVacio(bodega) ? predeterminada() : bodega.trim();
    }

    public static String resolver(String bodega, boolean esInventarioPorBodega) {
        return esInventarioPorBodega ? resolver(bodega) : predeterminada();
    }

    public static boolean esPrincipal(String bodega) {
        return bodega != null && PRINCIPAL.equalsIgnoreCase(bodega.trim());
    }

    public static boolean esEstadoActivo(String estado) {
        if (estado == null) {
            return false;
        }

        String valor = estado.trim();
        return valor.equals(ESTADO_ACTIVO) || valor.equalsIgnoreCase(ETIQUETA_ACTIVO) || valor.equalsIgnoreCase("true");
    }

    public static String estado(boolean activa) {
        return activa ? ESTADO_ACTIVO : ESTADO_INACTIVO;
    }

    public static String estado(String estado) {
        return estado(esEstadoActivo(estado));
    }

    public static String etiqueta(boolean activa) {
        return activa ? ETIQUETA_ACTIVO : ETIQUETA_INACTIVO;
    }

    private static boolean estaVacio(String valor) {
        return valor == null || valor.trim().isEmpty();
    }
}
