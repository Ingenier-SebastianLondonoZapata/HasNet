package Utilidades.BaseDatos;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySql_connection1 {

    private static Connection conexion = null;
    private static MySql_connection1 mysql_connection;

    public static MySql_connection1 getMySql_connection() {
        if (mysql_connection == null) {
            mysql_connection = new MySql_connection1();
        }
        return mysql_connection;
    }

    private MySql_connection1() {
        String urlConexionBD = "jdbc:mysql://hasnet-db.cjauamo246oj.us-east-2.rds.amazonaws.com/HSPagos?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
        //mysql -h hasnet-db.cjauamo246oj.us-east-2.rds.amazonaws.com -P 3306 -u HasNetUser -p --ssl-mode=VERIFY_IDENTITY --ssl-ca=./global-bundle.pem
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(urlConexionBD, "HasNetUser", "cE4l~86jt4e:");

            if (conexion != null) {
                System.out.println("Conexión a la base de datos de pagos exitosa");
            } else {
                System.out.println("No se pudo conectar a la base de datos");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySql_connection1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MySql_connection1.class.getName()).log(Level.SEVERE, null, ex);
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
