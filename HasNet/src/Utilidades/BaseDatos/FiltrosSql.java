package Utilidades.BaseDatos;

import java.util.ArrayList;
import java.util.List;

/**
 * Apoyo para armar las condiciones de los reportes, donde el SQL viaja como texto al jasper y no se
 * puede parametrizar.
 */
public final class FiltrosSql {

    private FiltrosSql() {
    }

    /**
     * Agrega "columna IN ('a', 'b')" solo si hay valores que filtrar.
     */
    public static void agregarEn(List<String> condiciones, String columna, List<String> valores) {
        if (valores.isEmpty()) {
            return;
        }

        List<String> literales = new ArrayList<>();
        for (String valor : valores) {
            literales.add(literal(valor));
        }

        condiciones.add(columna + " IN (" + String.join(", ", literales) + ")");
    }

    public static String literal(String valor) {
        return "'" + escapar(valor) + "'";
    }

    public static String escapar(String valor) {
        return valor == null ? "" : valor.replace("\\", "\\\\").replace("'", "''");
    }

    public static String donde(List<String> condiciones) {
        return condiciones.isEmpty() ? "" : " WHERE " + String.join(" AND ", condiciones);
    }
}
