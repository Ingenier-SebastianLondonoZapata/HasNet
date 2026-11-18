package Utilidades.BaseDatos;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySql_connection {

    private static final String PREFIJO = "local";

    private static Connection conexion;
    private static MySql_connection mysql_connection;

    private final String nombreBaseDatos;

    private MySql_connection(String nombreBaseDatos) {
        this.nombreBaseDatos = CredencialesBaseDatos.validarNombreBaseDatos(nombreBaseDatos);
        conectar();
    }

    public static MySql_connection getInstancia(String nombreBaseDatos) {
        if (mysql_connection == null) {
            mysql_connection = new MySql_connection(nombreBaseDatos);
        }

        return mysql_connection;
    }

    public static MySql_connection getMySql_connection(String nombreBaseDatos, Boolean esDesdeMultiempresas) {
        if (esDesdeMultiempresas) {
            try {
                desconectar();
            } catch (SQLException ex) {
                Logger.getLogger(MySql_connection.class.getName()).log(Level.SEVERE, null, ex);
            }

            mysql_connection = new MySql_connection(nombreBaseDatos);
        } else {
            if (mysql_connection == null) {
                mysql_connection = new MySql_connection(nombreBaseDatos);
            }
        }

        return mysql_connection;
    }

    private void conectar() {
        try {
            conexion = abrirConexion();
            System.out.println("Conexión a la base de datos exitosa");
        } catch (SQLException | IllegalStateException ex) {
            Logger.getLogger(MySql_connection.class.getName()).log(Level.SEVERE, "No se pudo conectar a la base de datos " + nombreBaseDatos, ex);
        }
    }

    private Connection abrirConexion() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            throw new SQLException("No se encontró el driver de MySQL", ex);
        }

        String url = CredencialesBaseDatos.construirUrl(CredencialesBaseDatos.obtenerHostLocal(), PREFIJO, nombreBaseDatos, "");
        return DriverManager.getConnection(url, CredencialesBaseDatos.propiedadesConexion(PREFIJO));
    }

    public Connection getConnection() {
        try {
            if (conexion == null || conexion.isClosed()) {
                System.out.println("Reconectando a la base de datos...");
                conectar();
            }
        } catch (SQLException ex) {
            Logger.getLogger(MySql_connection.class.getName()).log(Level.SEVERE, null, ex);
            conectar();
        }

        return conexion;
    }

    public Connection crearConexionIndependiente() throws SQLException {
        try {
            return abrirConexion();
        } catch (IllegalStateException ex) {
            throw new SQLException(ex.getMessage(), ex);
        }
    }

    public static void desconectar() throws SQLException {
        if (conexion != null && !conexion.isClosed()) {
            conexion.close();
            conexion = null;
        }
    }
}
