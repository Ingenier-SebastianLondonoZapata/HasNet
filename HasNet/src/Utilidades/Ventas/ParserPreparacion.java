package Utilidades.Ventas;

import Modelo.Inventario.ComponenteDiscosteo;
import Modelo.Ventas.OpcionPreparacion;
import Utilidades.Utilidades;
import java.util.ArrayList;
import java.util.List;

public final class ParserPreparacion {

    private static final String SEPARADOR_SEGMENTOS = "; ";
    private static final String SEPARADOR_OPCIONES = ", ";
    private static final String SEPARADOR_CAMPOS = "/";

    private static final int INDICE_ADEREZOS = 0;
    private static final int INDICE_OPCIONES = 1;
    private static final int INDICE_OBSERVACIONES = 2;

    private static final int CAMPOS_POR_OPCION = 4;
    private static final int CAMPO_PRINCIPAL = 0;
    private static final int CAMPO_CODIGO = 1;
    private static final int CAMPO_CANTIDAD = 2;
    private static final int CAMPO_ESTADO = 3;

    private ParserPreparacion() {
    }

    public static boolean tienePreparacion(String preparacion) {
        return !segmento(preparacion, INDICE_OPCIONES).isEmpty();
    }

    public static String aderezos(String preparacion) {
        return segmento(preparacion, INDICE_ADEREZOS);
    }

    public static String observaciones(String preparacion) {
        return segmento(preparacion, INDICE_OBSERVACIONES);
    }

    public static List<OpcionPreparacion> opciones(String preparacion) {
        return opcionesDeSegmento(segmento(preparacion, INDICE_OPCIONES));
    }

    public static List<OpcionPreparacion> opcionesDeSegmento(String opciones) {
        List<OpcionPreparacion> resultado = new ArrayList<>();

        if (opciones == null || opciones.trim().isEmpty()) {
            return resultado;
        }

        for (String opcion : opciones.split(SEPARADOR_OPCIONES)) {
            String[] campos = opcion.split(SEPARADOR_CAMPOS);
            if (campos.length < CAMPOS_POR_OPCION) {
                continue;
            }

            resultado.add(new OpcionPreparacion(
                    campos[CAMPO_PRINCIPAL], campos[CAMPO_CODIGO], Utilidades.convertirBigDecimal(campos[CAMPO_CANTIDAD]), campos[CAMPO_ESTADO]));
        }

        return resultado;
    }

    public static List<ComponenteDiscosteo> componentesActivos(String preparacion) {
        List<ComponenteDiscosteo> componentes = new ArrayList<>();

        for (OpcionPreparacion opcion : opciones(preparacion)) {
            if (!opcion.esAdicion() && opcion.activa()) {
                componentes.add(new ComponenteDiscosteo(opcion.getCodigo(), opcion.getCantidad()));
            }
        }

        return componentes;
    }

    private static String segmento(String preparacion, int indice) {
        if (preparacion == null) {
            return "";
        }

        String[] segmentos = preparacion.split(SEPARADOR_SEGMENTOS);
        if (segmentos.length <= indice) {
            return "";
        }

        return segmentos[indice].trim();
    }
}
