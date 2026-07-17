package dao.Ventas;

import Utilidades.BaseDatos.MySql_connection;
import Utilidades.Constantes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

public class DaoCotizacion {

    private static final Logger LOGGER = Logger.getLogger(DaoCotizacion.class.getName());
    private final Connection conexion;

    public DaoCotizacion() {
        this.conexion = MySql_connection.getInstancia(Constantes.BASE_DATOS_PRINCIPAL).getConnection();
    }

    public boolean modificarEstadoCotizacion(String estado, String idDocumento) {
        String sql = "UPDATE bdCotizacion SET estadoGeneral = ? WHERE factura = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, estado);
            stmt.setString(2, idDocumento);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al modificar estado cotizacion: " + e.getMessage());
            return false;
        }
    }
}
