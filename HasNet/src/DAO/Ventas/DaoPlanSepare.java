package dao.Ventas;

import Utilidades.BaseDatos.MySql_connection;
import Utilidades.Constantes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DaoPlanSepare {

    private final Connection conexion = MySql_connection.getInstancia(Constantes.BASE_DATOS_PRINCIPAL).getConnection();

    public boolean modificarEstadoPlanSepare(String estado, String idFacturaGenerada, String idDocumento) {
        String sql = "UPDATE bdPlanSepare SET estadoGeneral = ?, estado2 = ? WHERE idFactura = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, estado);
            stmt.setString(2, idFacturaGenerada);
            stmt.setString(3, idDocumento);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al modificar estado plan separe: " + e.getMessage());
            return false;
        }
    }
}
