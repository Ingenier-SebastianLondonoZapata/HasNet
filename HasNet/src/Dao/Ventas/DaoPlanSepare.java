package Dao.Ventas;

import Utilidades.BaseDatos.MySql_connection;
import Utilidades.Constantes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    public boolean esFacturaDePlanSepare(String idFactura) {
        String sql = "SELECT 1 FROM bdPlanSepare WHERE estado2 = ? LIMIT 1";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, idFactura);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            System.err.println("Error al consultar la factura del plan separe: " + e.getMessage());
            return false;
        }
    }

    public boolean estaFacturado(String idPlanSepare) {
        String sql = "SELECT 1 FROM bdPlanSepare WHERE idFactura = ? AND estado2 IS NOT NULL AND estado2 <> '' LIMIT 1";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, idPlanSepare);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            System.err.println("Error al consultar si el plan separe está facturado: " + e.getMessage());
            return false;
        }
    }
}
