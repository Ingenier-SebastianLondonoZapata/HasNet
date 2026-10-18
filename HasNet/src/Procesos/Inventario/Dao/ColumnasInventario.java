package Procesos.Inventario.Dao;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public final class ColumnasInventario {

    public static final String[] COLUMNAS = {
        "inventario", "fisicoInventario", "inventarioInicial", "compras", "ventas", "armado", "anulacion", "pedidos", "planSepare",
        "ordenServicio", "notaCredito", "notaDebito", "ajusteEntrada", "ajusteSalida", "ajusteInventario", "costeo", "congelada",
        "trasladoBod", "trasladoInternoEntrada", "trasladoInternoSalida", "enTransito"};

    private static final Set<String> CONJUNTO = Collections.unmodifiableSet(new HashSet<>(Arrays.asList(COLUMNAS)));

    private ColumnasInventario() {
    }

    public static boolean contiene(String columna) {
        return CONJUNTO.contains(columna);
    }

    public static String lista() {
        return String.join(", ", COLUMNAS);
    }
}
