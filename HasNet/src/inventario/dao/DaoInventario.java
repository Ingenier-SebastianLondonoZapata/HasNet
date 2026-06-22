package inventario.dao;

import Utilidades.BaseDatos.SentenciaSql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DaoInventario {

    /**
     * Ejecuta una lista de sentencias SQL parametrizadas sobre la conexión dada.
     * Cada {@link SentenciaSql} se ejecuta con un {@link PreparedStatement}, de
     * modo que los valores se enlazan de forma segura (sin concatenación).
     */
    public void ejecutarSentencias(Connection conn, List<SentenciaSql> sentencias) throws SQLException {
        for (SentenciaSql sentencia : sentencias) {
            try (PreparedStatement ps = conn.prepareStatement(sentencia.getSql())) {
                List<Object> parametros = sentencia.getParametros();
                for (int i = 0; i < parametros.size(); i++) {
                    ps.setObject(i + 1, parametros.get(i));
                }

                ps.executeUpdate();
            }
        }
    }

}
