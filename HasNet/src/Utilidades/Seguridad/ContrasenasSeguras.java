package Utilidades.Seguridad;

import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public final class ContrasenasSeguras {

    private static final String PREFIJO = "pbkdf2_sha256";
    private static final String ALGORITMO = "PBKDF2WithHmacSHA256";
    private static final int ITERACIONES = 210000;
    private static final int LONGITUD_SAL = 16;
    private static final int LONGITUD_HASH = 256;
    private static final SecureRandom ALEATORIO = new SecureRandom();

    private ContrasenasSeguras() {
    }

    public static String generarHash(char[] contrasena) {
        byte[] sal = new byte[LONGITUD_SAL];
        ALEATORIO.nextBytes(sal);
        byte[] hash = derivar(contrasena, sal, ITERACIONES, LONGITUD_HASH);
        return PREFIJO + "$" + ITERACIONES + "$" + Base64.getEncoder().encodeToString(sal) + "$" + Base64.getEncoder().encodeToString(hash);
    }

    public static String asegurarHash(String contrasena) {
        if (contrasena == null || contrasena.isEmpty() || esHash(contrasena)) {
            return contrasena;
        }
        char[] caracteres = contrasena.toCharArray();
        try {
            return generarHash(caracteres);
        } finally {
            Arrays.fill(caracteres, ' ');
        }
    }

    public static boolean esHash(String almacenada) {
        return almacenada != null && almacenada.startsWith(PREFIJO + "$");
    }

    public static boolean necesitaActualizacion(String almacenada) {
        if (!esHash(almacenada)) {
            return true;
        }
        String[] partes = almacenada.split("\\$");
        try {
            return partes.length != 4 || Integer.parseInt(partes[1]) < ITERACIONES;
        } catch (NumberFormatException ex) {
            return true;
        }
    }

    public static boolean verificar(char[] contrasena, String almacenada) {
        if (contrasena == null || contrasena.length == 0 || almacenada == null || almacenada.isEmpty()) {
            return false;
        }

        if (!esHash(almacenada)) {
            byte[] ingresada = new String(contrasena).getBytes(StandardCharsets.UTF_8);
            boolean coincide = MessageDigest.isEqual(ingresada, almacenada.getBytes(StandardCharsets.UTF_8));
            Arrays.fill(ingresada, (byte) 0);
            return coincide;
        }

        try {
            String[] partes = almacenada.split("\\$");
            if (partes.length != 4) {
                return false;
            }
            int iteraciones = Integer.parseInt(partes[1]);
            byte[] sal = Base64.getDecoder().decode(partes[2]);
            byte[] esperado = Base64.getDecoder().decode(partes[3]);
            byte[] calculado = derivar(contrasena, sal, iteraciones, esperado.length * 8);
            return MessageDigest.isEqual(esperado, calculado);
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }

    private static byte[] derivar(char[] contrasena, byte[] sal, int iteraciones, int longitudBits) {
        PBEKeySpec especificacion = new PBEKeySpec(contrasena, sal, iteraciones, longitudBits);
        try {
            return SecretKeyFactory.getInstance(ALGORITMO).generateSecret(especificacion).getEncoded();
        } catch (GeneralSecurityException ex) {
            throw new IllegalStateException("No se pudo calcular el hash de la contraseña", ex);
        } finally {
            especificacion.clearPassword();
        }
    }
}
