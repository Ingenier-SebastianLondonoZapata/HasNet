package Utilidades.BaseDatos;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySql_connection1 {

    private static final String PREFIJO = "pagos";
    private static final String PARAMETROS_DEFECTO = "useUnicode=true&serverTimezone=UTC&sslMode=REQUIRED";

    private static Connection conexion = null;
    private static MySql_connection1 mysql_connection;

    public static MySql_connection1 getMySql_connection() {
        if (mysql_connection == null) {
            mysql_connection = new MySql_connection1();
        }
        return mysql_connection;
    }

    private MySql_connection1() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = CredencialesBaseDatos.construirUrl(
                    CredencialesBaseDatos.obtenerRequerido(PREFIJO + ".host"),
                    PREFIJO,
                    CredencialesBaseDatos.obtener(PREFIJO + ".baseDatos", "HSPagos"),
                    PARAMETROS_DEFECTO);
            conexion = DriverManager.getConnection(url, CredencialesBaseDatos.propiedadesConexion(PREFIJO));
            System.out.println("Conexión a la base de datos de pagos exitosa");
        } catch (ClassNotFoundException | SQLException | IllegalStateException ex) {
            Logger.getLogger(MySql_connection1.class.getName()).log(Level.SEVERE, "No se pudo conectar a la base de datos de pagos", ex);
        }
    }

    public Connection getConnection() {
        return conexion;
    }

    public static void desconectar() throws SQLException {
        if (conexion != null) {
            conexion.close();
        }
    }
}
