package Utilidades.BaseDatos;

import Enums.Tablas;
import Enums.enumBodegas;
import java.util.HashSet;
import java.util.Set;

public final class ValidadorTabla {

    private static final Set<String> TABLAS_PERMITIDAS = construirListaBlanca();

    private ValidadorTabla() {
    }

    private static Set<String> construirListaBlanca() {
        Set<String> permitidas = new HashSet<>();

        for (enumBodegas.TipoBodega bodega : enumBodegas.TipoBodega.values()) {
            permitidas.add(bodega.getNombreTabla());
        }

        for (Tablas tabla : Tablas.values()) {
            permitidas.add(tabla.getNombre());
        }

        return permitidas;
    }

    public static String validar(String nombreTabla) {
        if (nombreTabla == null || !TABLAS_PERMITIDAS.contains(nombreTabla)) {
            throw new IllegalArgumentException("Tabla no permitida: " + nombreTabla);
        }

        return nombreTabla;
    }
}
