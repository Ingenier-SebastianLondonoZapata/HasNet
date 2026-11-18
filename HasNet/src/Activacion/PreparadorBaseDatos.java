package Activacion;

import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public final class PreparadorBaseDatos {

    private static final int ACCESO_DENEGADO = 1045;
    private static final String CARACTERES = "ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz23456789";

    private PreparadorBaseDatos() {
    }

    public static List<String> preparar(ActivadorTerminal.Datos datos) throws SQLException {
        List<String> avisos = new ArrayList<>();
        Connection conexion;
        try {
            conexion = conectarComoRoot(datos.puertoLocal);
        } catch (SQLException ex) {
            if (ex.getErrorCode() == ACCESO_DENEGADO) {
                return avisos;
            }
            throw new IllegalStateException("No se pudo conectar al servicio de base de datos HasNetDB: " + ex.getMessage(), ex);
        }

        try (Connection root = conexion; Statement sentencia = root.createStatement()) {
            if (!existeBaseDatos(root, datos.baseDatosLocal)) {
                throw new IllegalStateException("La base de datos inicial no se creó. Revise el archivo importacion.log en la carpeta mariadb\\instalacion.");
            }
            for (String host : new String[]{"localhost", "%"}) {
                if (existeUsuario(root, datos.usuarioLocal, host)) {
                    try (PreparedStatement clave = root.prepareStatement("SET PASSWORD FOR " + cuenta(datos.usuarioLocal, host) + " = PASSWORD(?)")) {
                        clave.setString(1, datos.claveLocal);
                        clave.execute();
                    }
                } else {
                    try (PreparedStatement creacion = root.prepareStatement("CREATE USER " + cuenta(datos.usuarioLocal, host) + " IDENTIFIED BY ?")) {
                        creacion.setString(1, datos.claveLocal);
                        creacion.execute();
                    }
                }
                sentencia.execute("GRANT ALL PRIVILEGES ON `bd%`.* TO " + cuenta(datos.usuarioLocal, host));
            }
            sentencia.executeUpdate("DELETE FROM mysql.user WHERE User = ''");
            sentencia.execute("DROP DATABASE IF EXISTS test");
            sentencia.executeUpdate("DELETE FROM mysql.db WHERE Db = 'test' OR Db = 'test\\_%'");

            List<String> hostsRoot = new ArrayList<>();
            try (ResultSet resultado = sentencia.executeQuery("SELECT Host FROM mysql.user WHERE User = 'root'")) {
                while (resultado.next()) {
                    hostsRoot.add(resultado.getString(1));
                }
            }
            String claveRoot = claveAleatoria(24);
            for (String host : hostsRoot) {
                try (PreparedStatement clave = root.prepareStatement("SET PASSWORD FOR " + cuenta("root", host) + " = PASSWORD(?)")) {
                    clave.setString(1, claveRoot);
                    clave.execute();
                }
            }
            sentencia.execute("FLUSH PRIVILEGES");
        }

        avisos.add("Base de datos preparada: usuario " + datos.usuarioLocal + " creado y acceso de root protegido");
        return avisos;
    }

    private static Connection conectarComoRoot(String puerto) throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            throw new SQLException("No se encontró el driver de MySQL", ex);
        }
        Properties propiedades = new Properties();
        propiedades.setProperty("user", "root");
        propiedades.setProperty("password", "");
        propiedades.setProperty("connectTimeout", "15000");
        propiedades.setProperty("allowLoadLocalInfile", "false");
        String puertoValidado = puerto != null && puerto.trim().matches("\\d{1,5}") ? puerto.trim() : "3306";
        return DriverManager.getConnection("jdbc:mysql://localhost:" + puertoValidado + "/mysql", propiedades);
    }

    private static boolean existeBaseDatos(Connection conexion, String nombre) throws SQLException {
        try (PreparedStatement consulta = conexion.prepareStatement(
                "SELECT COUNT(*) FROM information_schema.SCHEMATA WHERE LOWER(SCHEMA_NAME) = LOWER(?)")) {
            consulta.setString(1, nombre);
            try (ResultSet resultado = consulta.executeQuery()) {
                return resultado.next() && resultado.getInt(1) > 0;
            }
        }
    }

    private static boolean existeUsuario(Connection conexion, String usuario, String host) throws SQLException {
        try (PreparedStatement consulta = conexion.prepareStatement("SELECT COUNT(*) FROM mysql.user WHERE User = ? AND Host = ?")) {
            consulta.setString(1, usuario);
            consulta.setString(2, host);
            try (ResultSet resultado = consulta.executeQuery()) {
                return resultado.next() && resultado.getInt(1) > 0;
            }
        }
    }

    private static String cuenta(String usuario, String host) {
        if (!usuario.matches("[A-Za-z0-9_]{1,32}")) {
            throw new IllegalStateException("Nombre de usuario de base de datos inválido");
        }
        return "'" + usuario + "'@'" + host.replace("\\", "\\\\").replace("'", "\\'") + "'";
    }

    private static String claveAleatoria(int longitud) {
        SecureRandom aleatorio = new SecureRandom();
        StringBuilder clave = new StringBuilder(longitud);
        for (int i = 0; i < longitud; i++) {
            clave.append(CARACTERES.charAt(aleatorio.nextInt(CARACTERES.length())));
        }
        return clave.toString();
    }
}
