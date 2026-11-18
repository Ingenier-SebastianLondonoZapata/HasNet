package Utilidades.Seguridad;

import Utilidades.BaseDatos.CredencialesBaseDatos;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class IdentidadInstalacion {

    public static final String CLAVE_TOKEN_TERMINAL = "terminal.token";
    public static final String CLAVE_NOMBRE_TERMINAL = "terminal.nombre";
    public static final String CLAVE_IDENTIFICADOR_CLIENTE = "cliente.identificador";

    private static final Logger LOG = Logger.getLogger(IdentidadInstalacion.class.getName());
    private static final SecureRandom ALEATORIO = new SecureRandom();

    private IdentidadInstalacion() {
    }

    public static String obtenerIdTerminal() {
        try {
            return hashToken(CredencialesBaseDatos.obtenerSecreto(CLAVE_TOKEN_TERMINAL));
        } catch (IllegalStateException ex) {
            LOG.log(Level.SEVERE, "La terminal no está activada: {0}", ex.getMessage());
            return "";
        }
    }

    public static String obtenerIdentificadorCliente() {
        try {
            return CredencialesBaseDatos.obtenerSecreto(CLAVE_IDENTIFICADOR_CLIENTE);
        } catch (IllegalStateException ex) {
            LOG.log(Level.SEVERE, "No está configurado el identificador del cliente: {0}", ex.getMessage());
            return "";
        }
    }

    public static String generarToken() {
        byte[] bytes = new byte[32];
        ALEATORIO.nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }

    public static String hashToken(String token) {
        try {
            byte[] hash = MessageDigest.getInstance("SHA-256").digest(token.getBytes(StandardCharsets.UTF_8));
            StringBuilder hex = new StringBuilder(hash.length * 2);
            for (byte b : hash) {
                hex.append(String.format("%02x", b));
            }
            return hex.toString();
        } catch (NoSuchAlgorithmException ex) {
            throw new IllegalStateException("SHA-256 no disponible", ex);
        }
    }
}
