package Utilidades.BaseDatos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySql_connection {

    private static Connection conexion;
    private static MySql_connection mysql_connection;

    private final String nombreBaseDatos;

    private MySql_connection(String nombreBaseDatos) {
        this.nombreBaseDatos = nombreBaseDatos;
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
        String rutaBaseDatos = leerArchivoPropiedades();
        String sURL = "jdbc:mysql://" + rutaBaseDatos + ":3306/" + nombreBaseDatos;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(sURL, "click", "cl1ckP4$4yMzf");
            System.out.println("Conexión a la base de datos exitosa");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MySql_connection.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    public static void desconectar() throws SQLException {
        if (conexion != null && !conexion.isClosed()) {
            conexion.close();
            conexion = null;
        }
    }

    private static String leerArchivoPropiedades() {
        String texto;
        StringBuilder resultado = new StringBuilder();

        try (BufferedReader contenido = new BufferedReader(new FileReader("C:\\Java.old\\Propiedades.txt"))) {
            while ((texto = contenido.readLine()) != null) {
                resultado.append(texto);
            }
        } catch (Exception e) {
            System.out.println("Error al leer el archivo de propiedades " + e.getMessage());
        }

        return resultado.toString();
    }
}

/*public class MySql_connection {

 private static Connection conexion = null;
 private static MySql_connection mysql_connection;

 public static MySql_connection getMySql_connection(String nombreBaseDatos, Boolean esDesdeMultiempresas) {
 if (esDesdeMultiempresas) {
 try {
 desconectar();
 } catch (SQLException ex) {
 Logger.getLogger(MySql_connection.class.getName()).log(Level.SEVERE, null, ex);
 }

 System.out.println("La base de datos utilizada: " + nombreBaseDatos);
 mysql_connection = new MySql_connection(nombreBaseDatos);
 } else {
 if (mysql_connection == null) {
 mysql_connection = new MySql_connection(nombreBaseDatos);
 }
 }

 return mysql_connection;
 }

 private MySql_connection(String nombreBaseDatos) {
 String rutaBaseDatos = leerArchivoPropiedades();
 String sURL = "jdbc:mysql://" + rutaBaseDatos + ":3306/" + nombreBaseDatos;

 try {
 Class.forName("com.mysql.cj.jdbc.Driver");
 conexion = DriverManager.getConnection(sURL, "click", "cl1ckP4$4yMzf");
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

 public static MySql_connection getInstancia(String nombreBaseDatos) {
 if (mysql_connection == null) {
 mysql_connection = new MySql_connection(nombreBaseDatos);
 }
 return mysql_connection;
 }   

 public static void desconectar() throws SQLException {
 if (conexion != null) {
 conexion.close();
 }
 }

 private static String leerArchivoPropiedades() {
 String texto = "", resultado = "";
 try {
 FileReader lector = new FileReader("C:\\Java.old\\Propiedades.txt");
 BufferedReader contenido = new BufferedReader(lector);
 while ((texto = contenido.readLine()) != null) {
 resultado = resultado + texto;
 }
 } catch (Exception e) {
 System.out.println("Error al leer el archivo de propiedades" + e);
 }

 return resultado;
 }
 }
 */
