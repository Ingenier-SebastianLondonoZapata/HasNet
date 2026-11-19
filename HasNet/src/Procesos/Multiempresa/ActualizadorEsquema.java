package Procesos.Multiempresa;

import Utilidades.BaseDatos.MySql_connection;
import Utilidades.Constantes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class ActualizadorEsquema {

    private static final Logger LOGGER = Logger.getLogger(ActualizadorEsquema.class.getName());
    private static final String BLOQUEO = "hasnet_actualizacion_esquema";
    private static final int SEGUNDOS_ESPERA_BLOQUEO = 120;
    private static final String TABLA_MIGRACIONES = "bdMigracionesEsquema";
    private static final String TABLA_CONTROL = "bdControlEsquema";

    private ActualizadorEsquema() {
    }

    public static void preparar(String baseDatos) throws SQLException {
        validarCatalogo();
        String principal = Constantes.BASE_DATOS_PRINCIPAL;
        boolean esPrincipal = principal.equalsIgnoreCase(baseDatos);
        try (Connection conexion = MySql_connection.crearConexionPrincipal()) {
            bloquear(conexion);
            try {
                aplicarMigraciones(conexion, principal);
                if (!esPrincipal) {
                    aplicarMigraciones(conexion, baseDatos);
                    SincronizadorEsquema.sincronizar(conexion, principal, baseDatos);
                }
                DatosBaseEmpresa.asegurar(conexion, esPrincipal ? principal : baseDatos);
            } finally {
                liberar(conexion);
            }
        }
    }

    static void asegurarTablasControl(Connection conexion, String esquema) throws SQLException {
        try (Statement sentencia = conexion.createStatement()) {
            sentencia.execute("CREATE TABLE IF NOT EXISTS " + EsquemaEmpresa.tabla(esquema, TABLA_MIGRACIONES)
                    + " (id VARCHAR(100) NOT NULL, fechaAplicacion DATETIME NOT NULL, PRIMARY KEY (id)) ENGINE=InnoDB DEFAULT CHARSET=utf8");
            sentencia.execute("CREATE TABLE IF NOT EXISTS " + EsquemaEmpresa.tabla(esquema, TABLA_CONTROL)
                    + " (clave VARCHAR(50) NOT NULL, valor VARCHAR(255) NULL, fecha DATETIME NULL, PRIMARY KEY (clave)) ENGINE=InnoDB DEFAULT CHARSET=utf8");
        }
    }

    static void heredarMigraciones(Connection conexion, String destino) throws SQLException {
        String principal = Constantes.BASE_DATOS_PRINCIPAL;
        asegurarTablasControl(conexion, principal);
        asegurarTablasControl(conexion, destino);
        try (Statement sentencia = conexion.createStatement()) {
            sentencia.executeUpdate("INSERT IGNORE INTO " + EsquemaEmpresa.tabla(destino, TABLA_MIGRACIONES) + " (id, fechaAplicacion) SELECT id, NOW() FROM "
                    + EsquemaEmpresa.tabla(principal, TABLA_MIGRACIONES));
        }
    }

    static String leerControl(Connection conexion, String esquema, String clave) throws SQLException {
        asegurarTablasControl(conexion, esquema);
        try (PreparedStatement stmt = conexion.prepareStatement("SELECT valor FROM " + EsquemaEmpresa.tabla(esquema, TABLA_CONTROL) + " WHERE clave = ?")) {
            stmt.setString(1, clave);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() ? rs.getString(1) : null;
            }
        }
    }

    static void guardarControl(Connection conexion, String esquema, String clave, String valor) throws SQLException {
        asegurarTablasControl(conexion, esquema);
        try (PreparedStatement stmt = conexion.prepareStatement("REPLACE INTO " + EsquemaEmpresa.tabla(esquema, TABLA_CONTROL)
                + " (clave, valor, fecha) VALUES (?, ?, NOW())")) {
            stmt.setString(1, clave);
            stmt.setString(2, valor);
            stmt.executeUpdate();
        }
    }

    private static void aplicarMigraciones(Connection conexion, String esquema) throws SQLException {
        asegurarTablasControl(conexion, esquema);
        if (CatalogoMigraciones.todas().isEmpty()) {
            return;
        }
        Set<String> aplicadas = new HashSet<>();
        try (Statement sentencia = conexion.createStatement();
                ResultSet rs = sentencia.executeQuery("SELECT id FROM " + EsquemaEmpresa.tabla(esquema, TABLA_MIGRACIONES))) {
            while (rs.next()) {
                aplicadas.add(rs.getString(1));
            }
        }
        String catalogoAnterior = conexion.getCatalog();
        try {
            conexion.setCatalog(EsquemaEmpresa.id(esquema).replace("`", ""));
            for (Migracion migracion : CatalogoMigraciones.todas()) {
                if (aplicadas.contains(migracion.getId())) {
                    continue;
                }
                for (String sql : migracion.getSentencias()) {
                    try (Statement sentencia = conexion.createStatement()) {
                        sentencia.execute(sql);
                    } catch (SQLException ex) {
                        throw new SQLException("La migración " + migracion.getId() + " falló en " + esquema + ": " + ex.getMessage(), ex);
                    }
                }
                try (PreparedStatement registro = conexion.prepareStatement("INSERT INTO " + EsquemaEmpresa.tabla(esquema, TABLA_MIGRACIONES)
                        + " (id, fechaAplicacion) VALUES (?, NOW())")) {
                    registro.setString(1, migracion.getId());
                    registro.executeUpdate();
                }
                LOGGER.log(Level.INFO, "Migración {0} aplicada en {1}", new Object[]{migracion.getId(), esquema});
            }
        } finally {
            conexion.setCatalog(catalogoAnterior);
        }
    }

    private static void validarCatalogo() {
        Set<String> ids = new HashSet<>();
        for (Migracion migracion : CatalogoMigraciones.todas()) {
            if (!ids.add(migracion.getId())) {
                throw new IllegalStateException("La migración " + migracion.getId() + " está repetida en el catálogo");
            }
        }
    }

    private static void bloquear(Connection conexion) throws SQLException {
        try (PreparedStatement stmt = conexion.prepareStatement("SELECT GET_LOCK(?, ?)")) {
            stmt.setString(1, BLOQUEO);
            stmt.setInt(2, SEGUNDOS_ESPERA_BLOQUEO);
            try (ResultSet rs = stmt.executeQuery()) {
                if (!rs.next() || rs.getInt(1) != 1) {
                    throw new SQLException("Otra terminal está actualizando la estructura de la base de datos; intente de nuevo en unos minutos");
                }
            }
        }
    }

    private static void liberar(Connection conexion) {
        try (PreparedStatement stmt = conexion.prepareStatement("SELECT RELEASE_LOCK(?)")) {
            stmt.setString(1, BLOQUEO);
            stmt.executeQuery().close();
        } catch (SQLException ex) {
            LOGGER.log(Level.WARNING, "No se pudo liberar el bloqueo de actualización de esquema", ex);
        }
    }
}
