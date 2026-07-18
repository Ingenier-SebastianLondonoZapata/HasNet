package dao.Ventas;

import Utilidades.BaseDatos.MySql_connection;
import Utilidades.Constantes;
import dao.Generales.DaoGenerales;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DaoPedido {

    DaoGenerales daoGenerales = new DaoGenerales();
    Connection conexion = MySql_connection.getInstancia(Constantes.BASE_DATOS_PRINCIPAL).getConnection();

    public boolean modificarEstadoPedido(String estado, String idFacturaGenerada, String idDocumento) {
        String sql = "UPDATE bdPedido SET estadoGeneral = ?, estado2 = ? WHERE factura = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, estado);
            stmt.setString(2, idFacturaGenerada);
            stmt.setString(3, idDocumento);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al modificar estado pedido: " + e.getMessage());
            return false;
        }
    }
}
