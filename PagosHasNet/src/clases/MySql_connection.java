package clases;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySql_connection {

    private static Connection conexion = null;
    private static MySql_connection mysql_connection;

    public static MySql_connection getMySql_connection() {
        if (mysql_connection == null) {
            mysql_connection = new MySql_connection();
        }
        return mysql_connection;
    }

    private MySql_connection() {
        String urlConexionBD = "jdbc:mysql://hasnet-pagos.cpku0888y21s.us-east-2.rds.amazonaws.com/HSPagos?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(urlConexionBD, "HasNet", "Sebask_2025*");
            
            if (conexion != null) {
                System.out.println("Conexión a la base de datos exitosa");
            } else {
                System.out.println("No se pudo conectar a la base de datos");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySql_connection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MySql_connection.class.getName()).log(Level.SEVERE, null, ex);
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
