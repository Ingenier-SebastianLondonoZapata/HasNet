package DAO.Inventario;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DaoInventario {

    public void ejecutarBatch(Connection conn, List<String> sqls) throws SQLException {
        try (Statement st = conn.createStatement()) {
            for (String sql : sqls) {
                st.addBatch(sql);
            }

            st.executeBatch();
        }
    }
    
}
