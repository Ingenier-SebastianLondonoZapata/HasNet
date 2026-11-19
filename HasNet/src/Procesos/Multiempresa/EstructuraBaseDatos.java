package Procesos.Multiempresa;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class EstructuraBaseDatos {

    final Map<String, String> tablas = new TreeMap<>();
    final Map<String, Map<String, Columna>> columnas = new TreeMap<>();
    final Map<String, Map<String, Indice>> indices = new TreeMap<>();
    final Map<String, String> nombresVistas = new TreeMap<>();
    final Map<String, String> vistas = new TreeMap<>();
    private final String esquema;

    private EstructuraBaseDatos(String esquema) {
        this.esquema = esquema;
    }

    static EstructuraBaseDatos leer(Connection conexion, String esquema) throws SQLException {
        EstructuraBaseDatos estructura = new EstructuraBaseDatos(esquema);
        for (String tabla : EsquemaEmpresa.tablas(conexion, esquema)) {
            String clave = clave(tabla);
            estructura.tablas.put(clave, tabla);
            estructura.columnas.put(clave, new TreeMap<>());
            estructura.indices.put(clave, new TreeMap<>());
        }

        try (PreparedStatement stmt = conexion.prepareStatement("SELECT TABLE_NAME, COLUMN_NAME, COLUMN_TYPE, IS_NULLABLE, COLUMN_DEFAULT, EXTRA "
                + "FROM information_schema.COLUMNS WHERE LOWER(TABLE_SCHEMA) = LOWER(?) ORDER BY TABLE_NAME, ORDINAL_POSITION")) {
            stmt.setString(1, esquema);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Map<String, Columna> columnasTabla = estructura.columnas.get(clave(rs.getString("TABLE_NAME")));
                    if (columnasTabla != null) {
                        Columna columna = new Columna(rs.getString("COLUMN_NAME"), rs.getString("COLUMN_TYPE"),
                                "YES".equalsIgnoreCase(rs.getString("IS_NULLABLE")), rs.getString("COLUMN_DEFAULT"), rs.getString("EXTRA"));
                        columnasTabla.put(clave(columna.nombre), columna);
                    }
                }
            }
        }

        try (PreparedStatement stmt = conexion.prepareStatement("SELECT TABLE_NAME, INDEX_NAME, NON_UNIQUE, COLUMN_NAME, SUB_PART, INDEX_TYPE "
                + "FROM information_schema.STATISTICS WHERE LOWER(TABLE_SCHEMA) = LOWER(?) ORDER BY TABLE_NAME, INDEX_NAME, SEQ_IN_INDEX")) {
            stmt.setString(1, esquema);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Map<String, Indice> indicesTabla = estructura.indices.get(clave(rs.getString("TABLE_NAME")));
                    if (indicesTabla == null) {
                        continue;
                    }
                    String nombre = rs.getString("INDEX_NAME");
                    Indice indice = indicesTabla.get(clave(nombre));
                    if (indice == null) {
                        indice = new Indice(nombre, rs.getInt("NON_UNIQUE") == 0, rs.getString("INDEX_TYPE"));
                        indicesTabla.put(clave(nombre), indice);
                    }
                    String subParte = rs.getString("SUB_PART");
                    indice.columnas.add(EsquemaEmpresa.identificador(rs.getString("COLUMN_NAME")) + (subParte == null ? "" : "(" + subParte + ")"));
                }
            }
        }

        for (Map.Entry<String, String> vista : EsquemaEmpresa.vistas(conexion, esquema).entrySet()) {
            estructura.nombresVistas.put(clave(vista.getKey()), vista.getKey());
            estructura.vistas.put(clave(vista.getKey()), vista.getValue());
        }
        return estructura;
    }

    String vistaSinCalificar(String clave) {
        String definicion = vistas.get(clave);
        return definicion == null ? null : quitarCalificador(definicion, esquema);
    }

    String huella() {
        StringBuilder texto = new StringBuilder();
        for (String tabla : tablas.keySet()) {
            texto.append("T:").append(tabla).append('\n');
            for (Map.Entry<String, Columna> columna : columnas.get(tabla).entrySet()) {
                texto.append("C:").append(columna.getKey()).append('|').append(columna.getValue().firma()).append('\n');
            }
            for (Map.Entry<String, Indice> indice : indices.get(tabla).entrySet()) {
                texto.append("I:").append(indice.getKey()).append('|').append(indice.getValue().firma()).append('\n');
            }
        }
        for (String vista : vistas.keySet()) {
            texto.append("V:").append(vista).append('|').append(vistaSinCalificar(vista)).append('\n');
        }
        try {
            byte[] resumen = MessageDigest.getInstance("SHA-256").digest(texto.toString().getBytes(StandardCharsets.UTF_8));
            StringBuilder hexadecimal = new StringBuilder();
            for (byte b : resumen) {
                hexadecimal.append(String.format("%02x", b));
            }
            return hexadecimal.toString();
        } catch (NoSuchAlgorithmException ex) {
            throw new IllegalStateException(ex);
        }
    }

    static String quitarCalificador(String definicion, String esquema) {
        return Pattern.compile("`" + Pattern.quote(esquema) + "`\\.", Pattern.CASE_INSENSITIVE).matcher(definicion).replaceAll(Matcher.quoteReplacement(""));
    }

    static String clave(String nombre) {
        return nombre.toLowerCase(Locale.ROOT);
    }

    static final class Columna {

        final String nombre;
        final String tipo;
        final boolean nulo;
        final String valorDefecto;
        final boolean autoIncremento;
        final boolean actualizaFecha;

        Columna(String nombre, String tipo, boolean nulo, String valorDefecto, String extra) {
            String extraMinuscula = extra == null ? "" : extra.toLowerCase(Locale.ROOT);
            this.nombre = nombre;
            this.tipo = tipo;
            this.nulo = nulo;
            this.valorDefecto = valorDefecto == null || "NULL".equalsIgnoreCase(valorDefecto) ? null : valorDefecto;
            this.autoIncremento = extraMinuscula.contains("auto_increment");
            this.actualizaFecha = extraMinuscula.contains("on update");
        }

        String firma() {
            return tipo.toLowerCase(Locale.ROOT) + '|' + nulo + '|' + valorDefecto + '|' + autoIncremento + '|' + actualizaFecha;
        }

        boolean igualA(Columna otra) {
            return otra != null && Objects.equals(firma(), otra.firma());
        }

        String definicion() {
            StringBuilder definicion = new StringBuilder(tipo).append(nulo ? " NULL" : " NOT NULL");
            if (valorDefecto == null) {
                if (nulo) {
                    definicion.append(" DEFAULT NULL");
                }
            } else if (valorDefecto.toUpperCase(Locale.ROOT).startsWith("CURRENT_TIMESTAMP") || valorDefecto.startsWith("'")) {
                definicion.append(" DEFAULT ").append(valorDefecto);
            } else {
                definicion.append(" DEFAULT '").append(valorDefecto.replace("\\", "\\\\").replace("'", "''")).append("'");
            }
            if (autoIncremento) {
                definicion.append(" AUTO_INCREMENT");
            }
            if (actualizaFecha) {
                definicion.append(" ON UPDATE CURRENT_TIMESTAMP");
            }
            return definicion.toString();
        }
    }

    static final class Indice {

        final String nombre;
        final boolean unico;
        final String tipo;
        final List<String> columnas = new ArrayList<>();

        Indice(String nombre, boolean unico, String tipo) {
            this.nombre = nombre;
            this.unico = unico;
            this.tipo = tipo == null ? "BTREE" : tipo.toUpperCase(Locale.ROOT);
        }

        boolean esPrimario() {
            return "PRIMARY".equalsIgnoreCase(nombre);
        }

        String firma() {
            return unico + "|" + tipo + "|" + String.join(",", columnas).toLowerCase(Locale.ROOT);
        }

        String definicion() {
            String clase = "FULLTEXT".equals(tipo) ? "FULLTEXT INDEX " : "SPATIAL".equals(tipo) ? "SPATIAL INDEX " : unico ? "UNIQUE INDEX " : "INDEX ";
            return clase + EsquemaEmpresa.identificador(nombre) + " (" + String.join(", ", columnas) + ")";
        }
    }
}
