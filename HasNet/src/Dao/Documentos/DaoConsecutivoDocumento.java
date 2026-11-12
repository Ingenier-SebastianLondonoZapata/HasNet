package Dao.Documentos;

import Utilidades.BaseDatos.MySql_connection;
import Utilidades.Constantes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoConsecutivoDocumento {

    private static final String SELECT_RESOLUCION = "SELECT consecutivo, prefijo FROM bdResoluciones WHERE idResolucion = ? FOR UPDATE";
    private static final String UPDATE_RESOLUCION = "UPDATE bdResoluciones SET consecutivo = ? WHERE idResolucion = ?";
    private static final String SELECT_CONSECUTIVO = "SELECT numero FROM bdConsecutivos WHERE Id = ? FOR UPDATE";
    private static final String UPDATE_CONSECUTIVO = "UPDATE bdConsecutivos SET numero = ? WHERE Id = ?";

    public ReservaConsecutivo reservarResolucion(int idResolucion) throws SQLException {
        Connection conexion = abrirConexion();
        try (PreparedStatement ps = conexion.prepareStatement(SELECT_RESOLUCION)) {
            ps.setInt(1, idResolucion);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    throw new SQLException("No existe la resolución " + idResolucion);
                }
                return new ReservaConsecutivo(conexion, UPDATE_RESOLUCION, idResolucion, rs.getString("prefijo"), rs.getInt("consecutivo"));
            }
        } catch (SQLException e) {
            cerrar(conexion);
            throw e;
        }
    }

    public ReservaConsecutivo reservarConsecutivo(String clave) throws SQLException {
        Connection conexion = abrirConexion();
        try (PreparedStatement ps = conexion.prepareStatement(SELECT_CONSECUTIVO)) {
            ps.setString(1, clave);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    throw new SQLException("No existe el consecutivo " + clave);
                }
                return new ReservaConsecutivo(conexion, UPDATE_CONSECUTIVO, clave, "", Integer.parseInt(rs.getString("numero").trim()));
            }
        } catch (SQLException | NumberFormatException e) {
            cerrar(conexion);
            throw e instanceof SQLException ? (SQLException) e : new SQLException("Consecutivo inválido para " + clave, e);
        }
    }

    private Connection abrirConexion() throws SQLException {
        Connection conexion = MySql_connection.getInstancia(Constantes.BASE_DATOS_PRINCIPAL).crearConexionIndependiente();
        conexion.setAutoCommit(false);
        return conexion;
    }

    private void cerrar(Connection conexion) {
        try {
            conexion.rollback();
            conexion.close();
        } catch (SQLException ignorada) {
        }
    }
}
