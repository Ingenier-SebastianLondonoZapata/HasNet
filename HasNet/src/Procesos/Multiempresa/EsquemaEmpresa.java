package Procesos.Multiempresa;

import Utilidades.BaseDatos.CredencialesBaseDatos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class EsquemaEmpresa {

    private static final Set<String> TABLAS_SOLO_PRINCIPAL = new LinkedHashSet<>(Arrays.asList("bdmultiempresa", "bdusuarioempresa"));

    private EsquemaEmpresa() {
    }

    static String id(String nombre) {
        return "`" + CredencialesBaseDatos.validarNombreBaseDatos(nombre) + "`";
    }

    static String identificador(String nombre) {
        return "`" + nombre.replace("`", "``") + "`";
    }

    static String tabla(String esquema, String tabla) {
        return id(esquema) + "." + identificador(tabla);
    }

    static boolean existeBaseDatos(Connection conexion, String esquema) throws SQLException {
        try (PreparedStatement stmt = conexion.prepareStatement("SELECT COUNT(*) FROM information_schema.SCHEMATA WHERE LOWER(SCHEMA_NAME) = LOWER(?)")) {
            stmt.setString(1, esquema);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        }
    }

    static String juegoCaracteres(Connection conexion, String esquema) throws SQLException {
        try (PreparedStatement stmt = conexion.prepareStatement("SELECT DEFAULT_CHARACTER_SET_NAME FROM information_schema.SCHEMATA WHERE LOWER(SCHEMA_NAME) = LOWER(?)")) {
            stmt.setString(1, esquema);
            try (ResultSet rs = stmt.executeQuery()) {
                String valor = rs.next() ? rs.getString(1) : null;
                return valor == null || !valor.matches("[A-Za-z0-9_]+") ? "latin1" : valor;
            }
        }
    }

    static List<String> tablas(Connection conexion, String esquema) throws SQLException {
        List<String> tablas = new ArrayList<>();
        try (PreparedStatement stmt = conexion.prepareStatement("SELECT TABLE_NAME FROM information_schema.TABLES WHERE LOWER(TABLE_SCHEMA) = LOWER(?) "
                + "AND TABLE_TYPE = 'BASE TABLE' ORDER BY TABLE_NAME")) {
            stmt.setString(1, esquema);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    if (!TABLAS_SOLO_PRINCIPAL.contains(rs.getString(1).toLowerCase(Locale.ROOT))) {
                        tablas.add(rs.getString(1));
                    }
                }
            }
        }
        return tablas;
    }

    static Map<String, String> vistas(Connection conexion, String esquema) throws SQLException {
        Map<String, String> vistas = new LinkedHashMap<>();
        try (PreparedStatement stmt = conexion.prepareStatement("SELECT TABLE_NAME, VIEW_DEFINITION FROM information_schema.VIEWS "
                + "WHERE LOWER(TABLE_SCHEMA) = LOWER(?) ORDER BY TABLE_NAME")) {
            stmt.setString(1, esquema);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    vistas.put(rs.getString(1), rs.getString(2));
                }
            }
        }
        return vistas;
    }

    static Set<String> nombresColumnas(Connection conexion, String esquema, String tabla) throws SQLException {
        Set<String> columnas = new LinkedHashSet<>();
        try (PreparedStatement stmt = conexion.prepareStatement("SELECT COLUMN_NAME FROM information_schema.COLUMNS "
                + "WHERE LOWER(TABLE_SCHEMA) = LOWER(?) AND LOWER(TABLE_NAME) = LOWER(?) ORDER BY ORDINAL_POSITION")) {
            stmt.setString(1, esquema);
            stmt.setString(2, tabla);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    columnas.add(rs.getString(1));
                }
            }
        }
        return columnas;
    }

    static void crearTabla(Connection conexion, String origen, String destino, String tabla) throws SQLException {
        try (Statement sentencia = conexion.createStatement()) {
            sentencia.execute("CREATE TABLE IF NOT EXISTS " + tabla(destino, tabla) + " LIKE " + tabla(origen, tabla));
        }
    }

    static String calificarVista(String definicion, String origen, String destino) {
        Pattern calificador = Pattern.compile("`" + Pattern.quote(origen) + "`\\.", Pattern.CASE_INSENSITIVE);
        return calificador.matcher(definicion).replaceAll(Matcher.quoteReplacement(id(destino) + "."));
    }

    static void crearVistas(Connection conexion, String origen, String destino, Map<String, String> vistas) throws SQLException {
        Map<String, String> pendientes = new LinkedHashMap<>(vistas);
        SQLException ultimoError = null;
        boolean avance = true;
        while (!pendientes.isEmpty() && avance) {
            avance = false;
            for (String vista : new ArrayList<>(pendientes.keySet())) {
                try (Statement sentencia = conexion.createStatement()) {
                    sentencia.execute("CREATE OR REPLACE VIEW " + tabla(destino, vista) + " AS " + calificarVista(pendientes.get(vista), origen, destino));
                    pendientes.remove(vista);
                    avance = true;
                } catch (SQLException ex) {
                    ultimoError = ex;
                }
            }
        }
        if (!pendientes.isEmpty()) {
            throw new SQLException("No se pudieron crear las vistas " + pendientes.keySet(), ultimoError);
        }
    }

    static int copiarDatos(Connection conexion, String origen, String destino, String tabla, String condicion) throws SQLException {
        Set<String> columnasOrigen = new LinkedHashSet<>();
        for (String columna : nombresColumnas(conexion, origen, tabla)) {
            columnasOrigen.add(columna.toLowerCase(Locale.ROOT));
        }
        List<String> comunes = new ArrayList<>();
        for (String columna : nombresColumnas(conexion, destino, tabla)) {
            if (columnasOrigen.contains(columna.toLowerCase(Locale.ROOT))) {
                comunes.add(identificador(columna));
            }
        }
        if (comunes.isEmpty()) {
            return 0;
        }
        String lista = String.join(", ", comunes);
        try (Statement sentencia = conexion.createStatement()) {
            return sentencia.executeUpdate("INSERT INTO " + tabla(destino, tabla) + " (" + lista + ") SELECT " + lista
                    + " FROM " + tabla(origen, tabla) + (condicion == null || condicion.isEmpty() ? "" : " WHERE " + condicion));
        }
    }
}
