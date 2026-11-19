package Utilidades.BaseDatos;

import Utilidades.Constantes;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySql_connection {

    private static final String PREFIJO = "local";

    private static Connection conexion;
    private static MySql_connection mysql_connection;
    private static String baseDatosActual = Constantes.BASE_DATOS_PRINCIPAL;

    private MySql_connection() {
    }

    public static synchronized MySql_connection getInstancia(String nombreBaseDatos) {
        if (mysql_connection == null) {
            baseDatosActual = CredencialesBaseDatos.validarNombreBaseDatos(nombreBaseDatos);
            mysql_connection = new MySql_connection();
            mysql_connection.conectar();
        }

        return mysql_connection;
    }

    public static synchronized MySql_connection getMySql_connection(String nombreBaseDatos, Boolean esDesdeMultiempresas) {
        if (Boolean.TRUE.equals(esDesdeMultiempresas)) {
            cambiarBaseDatos(nombreBaseDatos);
            return mysql_connection;
        }
        return getInstancia(nombreBaseDatos);
    }

    public static synchronized void cambiarBaseDatos(String nombreBaseDatos) {
        String nueva = CredencialesBaseDatos.validarNombreBaseDatos(nombreBaseDatos);
        if (mysql_connection != null && nueva.equalsIgnoreCase(baseDatosActual) && conexionAbierta()) {
            return;
        }
        try {
            desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(MySql_connection.class.getName()).log(Level.WARNING, "No se pudo cerrar la conexión anterior", ex);
        }
        baseDatosActual = nueva;
        if (mysql_connection == null) {
            mysql_connection = new MySql_connection();
        }
        mysql_connection.conectar();
    }

    public static synchronized String getBaseDatosActual() {
        return baseDatosActual;
    }

    private static boolean conexionAbierta() {
        try {
            return conexion != null && !conexion.isClosed();
        } catch (SQLException ex) {
            return false;
        }
    }

    private void conectar() {
        try {
            conexion = abrirConexion();
        } catch (SQLException | IllegalStateException ex) {
            Logger.getLogger(MySql_connection.class.getName()).log(Level.SEVERE, "No se pudo conectar a la base de datos " + baseDatosActual, ex);
        }
    }

    private static Connection abrirConexion(String baseDatos) throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            throw new SQLException("No se encontró el driver de MySQL", ex);
        }

        String url = CredencialesBaseDatos.construirUrl(CredencialesBaseDatos.obtenerHostLocal(), PREFIJO, baseDatos, "");
        return DriverManager.getConnection(url, CredencialesBaseDatos.propiedadesConexion(PREFIJO));
    }

    private Connection abrirConexion() throws SQLException {
        return abrirConexion(baseDatosActual);
    }

    public Connection getConnection() {
        synchronized (MySql_connection.class) {
            if (!conexionAbierta()) {
                conectar();
            }
            return conexion;
        }
    }

    public Connection crearConexionIndependiente() throws SQLException {
        try {
            return abrirConexion();
        } catch (IllegalStateException ex) {
            throw new SQLException(ex.getMessage(), ex);
        }
    }

    public static Connection crearConexionPrincipal() throws SQLException {
        try {
            return abrirConexion(Constantes.BASE_DATOS_PRINCIPAL);
        } catch (IllegalStateException ex) {
            throw new SQLException(ex.getMessage(), ex);
        }
    }

    public static synchronized void desconectar() throws SQLException {
        if (conexion != null && !conexion.isClosed()) {
            conexion.close();
        }
        conexion = null;
    }
}
