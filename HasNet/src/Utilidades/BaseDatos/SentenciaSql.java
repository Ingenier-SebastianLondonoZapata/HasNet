package Utilidades.BaseDatos;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class SentenciaSql {

    private final String sql;
    private final List<Object> parametros;

    public SentenciaSql(String sql, Object... parametros) {
        this.sql = sql;
        this.parametros = (parametros == null)
                ? Collections.<Object>emptyList()
                : Arrays.asList(parametros);
    }

    public String getSql() {
        return sql;
    }

    public List<Object> getParametros() {
        return parametros;
    }
}
