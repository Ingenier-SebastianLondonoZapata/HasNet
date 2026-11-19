package Utilidades.Seguridad;

import Utilidades.BaseDatos.CredencialesBaseDatos;
import Utilidades.Constantes;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

final class RepositorioSesionesTerminal {

    private static final String PREFIJO = "local";
    private static final String BLOQUEO = "hasnet_terminales_abiertas";
    private static final String MILISEGUNDOS_CONEXION = "5000";
    private static final String MILISEGUNDOS_LECTURA = "5000";

    private Connection conexion;
    private boolean columnaVerificada;

    synchronized boolean reservar(String idTerminal, int permitidas, int minutosVencimiento) throws SQLException {
        try {
            Connection actual = conexion();
            asegurarColumna(actual);
            try (PreparedStatement adquirir = actual.prepareStatement("SELECT GET_LOCK(?, 5)")) {
                adquirir.setString(1, BLOQUEO);
                try (ResultSet resultado = adquirir.executeQuery()) {
                    if (!resultado.next() || resultado.getInt(1) != 1) {
                        throw new SQLException("No se pudo validar el número de terminales abiertas");
                    }
                }
            }
            try {
                try (PreparedStatement contar = actual.prepareStatement(
                        "SELECT COUNT(*) FROM bdTerminales WHERE Id <> ? AND ultimaSenal >= NOW() - INTERVAL ? MINUTE")) {
                    contar.setString(1, idTerminal);
                    contar.setInt(2, minutosVencimiento);
                    try (ResultSet resultado = contar.executeQuery()) {
                        if (resultado.next() && resultado.getInt(1) >= permitidas) {
                            return false;
                        }
                    }
                }
                try (PreparedStatement marcar = actual.prepareStatement("UPDATE bdTerminales SET ultimaSenal = NOW() WHERE Id = ?")) {
                    marcar.setString(1, idTerminal);
                    if (marcar.executeUpdate() == 0) {
                        throw new SQLException("La terminal no está activada en esta base de datos");
                    }
                }
                return true;
            } finally {
                try (PreparedStatement liberar = actual.prepareStatement("SELECT RELEASE_LOCK(?)")) {
                    liberar.setString(1, BLOQUEO);
                    liberar.executeQuery().close();
                }
            }
        } catch (SQLException ex) {
            descartarConexion();
            throw ex;
        }
    }

    synchronized boolean renovar(String idTerminal, int minutosVencimiento) throws SQLException {
        try (PreparedStatement renovar = conexion().prepareStatement(
                "UPDATE bdTerminales SET ultimaSenal = NOW() WHERE Id = ? AND ultimaSenal >= NOW() - INTERVAL ? MINUTE")) {
            renovar.setString(1, idTerminal);
            renovar.setInt(2, minutosVencimiento);
            return renovar.executeUpdate() > 0;
        } catch (SQLException ex) {
            descartarConexion();
            throw ex;
        }
    }

    synchronized void liberar(String idTerminal) {
        try (PreparedStatement liberar = conexion().prepareStatement("UPDATE bdTerminales SET ultimaSenal = NULL WHERE Id = ?")) {
            liberar.setString(1, idTerminal);
            liberar.executeUpdate();
        } catch (SQLException | RuntimeException ex) {
            Logger.getLogger(RepositorioSesionesTerminal.class.getName()).log(Level.WARNING, "No se pudo liberar la terminal", ex);
        } finally {
            descartarConexion();
        }
    }

    private void asegurarColumna(Connection actual) throws SQLException {
        if (columnaVerificada) {
            return;
        }
        try (PreparedStatement consulta = actual.prepareStatement("SELECT COUNT(*) FROM information_schema.COLUMNS "
                + "WHERE TABLE_SCHEMA = DATABASE() AND LOWER(TABLE_NAME) = 'bdterminales' AND LOWER(COLUMN_NAME) = 'ultimasenal'");
                ResultSet resultado = consulta.executeQuery()) {
            if (resultado.next() && resultado.getInt(1) == 0) {
                try (Statement sentencia = actual.createStatement()) {
                    sentencia.execute("ALTER TABLE bdTerminales ADD COLUMN ultimaSenal DATETIME NULL");
                }
            }
        }
        columnaVerificada = true;
    }

    private Connection conexion() throws SQLException {
        if (conexion != null && !conexion.isClosed()) {
            return conexion;
        }
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            throw new SQLException("No se encontró el driver de MySQL", ex);
        }
        try {
            String url = CredencialesBaseDatos.construirUrl(CredencialesBaseDatos.obtenerHostLocal(), PREFIJO,
                    Constantes.BASE_DATOS_PRINCIPAL, "");
            Properties propiedades = CredencialesBaseDatos.propiedadesConexion(PREFIJO);
            propiedades.setProperty("connectTimeout", MILISEGUNDOS_CONEXION);
            propiedades.setProperty("socketTimeout", MILISEGUNDOS_LECTURA);
            conexion = DriverManager.getConnection(url, propiedades);
            return conexion;
        } catch (IllegalStateException ex) {
            throw new SQLException("No se pudo leer la configuración de la base de datos local", ex);
        }
    }

    private void descartarConexion() {
        if (conexion == null) {
            return;
        }
        try {
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(RepositorioSesionesTerminal.class.getName()).log(Level.FINE, "Error al cerrar la conexión de terminales", ex);
        }
        conexion = null;
    }
}
