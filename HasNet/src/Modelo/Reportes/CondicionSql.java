package Modelo.Reportes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

public class CondicionSql {

    private final List<String> condiciones = new ArrayList<>();

    public static String texto(String valor) {
        return "'" + (valor == null ? "" : valor.replace("\\", "\\\\").replace("'", "''")) + "'";
    }

    public CondicionSql y(String condicion) {
        if (condicion != null && !condicion.trim().isEmpty()) {
            condiciones.add("(" + condicion.trim() + ")");
        }
        return this;
    }

    public CondicionSql ySi(boolean aplicar, String condicion) {
        return aplicar ? y(condicion) : this;
    }

    public CondicionSql en(String columna, Collection<String> valores) {
        return alguno(valores, valor -> columna + " = " + texto(valor));
    }

    public CondicionSql alguno(Collection<String> valores, Function<String, String> condicionPorValor) {
        if (valores == null || valores.isEmpty()) {
            return this;
        }
        List<String> partes = new ArrayList<>();
        for (String valor : valores) {
            String parte = condicionPorValor.apply(valor);
            if (parte != null && !parte.trim().isEmpty()) {
                partes.add(parte);
            }
        }
        return partes.isEmpty() ? this : y(String.join(" OR ", partes));
    }

    public CondicionSql entreFechas(String columna, String inicial, String finalInclusive) {
        if (inicial == null || finalInclusive == null) {
            return this;
        }
        return y(columna + " >= " + texto(inicial) + " AND " + columna + " < DATE_ADD(" + texto(finalInclusive) + ", INTERVAL 1 DAY)");
    }

    public boolean estaVacia() {
        return condiciones.isEmpty();
    }

    public String sinWhere() {
        return String.join(" AND ", condiciones);
    }

    public String where() {
        return estaVacia() ? "" : "WHERE " + sinWhere();
    }

    @Override
    public String toString() {
        return where();
    }
}
