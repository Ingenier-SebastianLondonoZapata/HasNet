package Utilidades.BaseDatos;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public final class CredencialesBaseDatos {

    private static final Logger LOG = Logger.getLogger(CredencialesBaseDatos.class.getName());
    private static final String CARPETA_DEFECTO = carpetaPorDefecto();
    private static final String RUTA_CONFIGURACION_DEFECTO = CARPETA_DEFECTO + "\\conexion.properties";
    private static final String RUTA_CLAVE_DEFECTO = CARPETA_DEFECTO + "\\hasnet.key";
    private static final String PREFIJO_VARIABLES = "HASNET_";
    private static final String INICIO_CIFRADO = "ENC(";
    private static final String FIN_CIFRADO = ")";
    private static final String TRANSFORMACION = "AES/GCM/NoPadding";
    private static final int LONGITUD_IV = 12;
    private static final int LONGITUD_TAG = 128;
    private static final Pattern NOMBRE_VALIDO = Pattern.compile("[A-Za-z0-9_]{1,64}");
    private static final Pattern HOST_VALIDO = Pattern.compile("[A-Za-z0-9._-]{1,255}");

    private static Properties propiedades;

    private CredencialesBaseDatos() {
    }

    private static String carpetaPorDefecto() {
        String programData = System.getenv("ProgramData");
        return Paths.get(programData == null || programData.trim().isEmpty() ? "C:\\ProgramData" : programData.trim(), "HasNet").toString();
    }

    public static synchronized String obtener(String llave) {
        String variable = System.getenv(PREFIJO_VARIABLES + llave.toUpperCase().replace('.', '_'));
        if (variable != null && !variable.trim().isEmpty()) {
            return variable.trim();
        }

        String valor = cargarPropiedades().getProperty(llave);
        return valor == null ? null : valor.trim();
    }

    public static String obtener(String llave, String valorDefecto) {
        String valor = obtener(llave);
        return valor == null || valor.isEmpty() ? valorDefecto : valor;
    }

    public static String obtenerRequerido(String llave) {
        String valor = obtener(llave);
        if (valor == null || valor.isEmpty()) {
            throw new IllegalStateException("Falta la propiedad '" + llave + "' en la configuración de conexión " + rutaConfiguracion());
        }
        return valor;
    }

    public static String obtenerSecreto(String llave) {
        String valor = obtenerRequerido(llave);
        if (valor.startsWith(INICIO_CIFRADO) && valor.endsWith(FIN_CIFRADO)) {
            return descifrar(valor.substring(INICIO_CIFRADO.length(), valor.length() - FIN_CIFRADO.length()));
        }

        LOG.log(Level.WARNING, "La propiedad ''{0}'' está en texto plano; cífrela con: java -cp HasNet.jar {1} cifrar",
                new Object[]{llave, CredencialesBaseDatos.class.getName()});
        return valor;
    }

    public static Properties propiedadesConexion(String prefijo) {
        Properties conexion = new Properties();
        conexion.setProperty("user", obtenerRequerido(prefijo + ".usuario"));
        conexion.setProperty("password", obtenerSecreto(prefijo + ".clave"));
        conexion.setProperty("allowLoadLocalInfile", "false");
        conexion.setProperty("allowUrlInLocalInfile", "false");
        return conexion;
    }

    public static String construirUrl(String host, String prefijo, String baseDatos, String parametrosDefecto) {
        String hostValidado = host == null ? "" : host.trim();
        if (!HOST_VALIDO.matcher(hostValidado).matches()) {
            throw new IllegalStateException("Host de base de datos inválido para '" + prefijo + "'");
        }

        String nombreBaseDatos = validarNombreBaseDatos(baseDatos);
        String puerto = obtener(prefijo + ".puerto", "3306");
        if (!puerto.matches("\\d{1,5}")) {
            throw new IllegalStateException("Puerto de base de datos inválido para '" + prefijo + "'");
        }

        String parametros = obtener(prefijo + ".parametros", parametrosDefecto);
        return "jdbc:mysql://" + hostValidado + ":" + puerto + "/" + nombreBaseDatos
                + (parametros == null || parametros.isEmpty() ? "" : "?" + parametros);
    }

    public static String validarNombreBaseDatos(String nombreBaseDatos) {
        if (nombreBaseDatos == null || !NOMBRE_VALIDO.matcher(nombreBaseDatos).matches()) {
            throw new IllegalArgumentException("Nombre de base de datos inválido");
        }
        return nombreBaseDatos;
    }

    public static synchronized void recargar() {
        propiedades = null;
    }

    public static String obtenerHostLocal() {
        return obtenerRequerido("local.host");
    }

    private static synchronized Properties cargarPropiedades() {
        if (propiedades == null) {
            Properties cargadas = new Properties();
            Path ruta = rutaConfiguracion();
            if (Files.isReadable(ruta)) {
                try (InputStream entrada = Files.newInputStream(ruta)) {
                    cargadas.load(new InputStreamReader(entrada, StandardCharsets.UTF_8));
                } catch (IOException ex) {
                    LOG.log(Level.SEVERE, "No se pudo leer la configuración de conexión " + ruta, ex);
                }
            } else {
                LOG.log(Level.SEVERE, "No existe el archivo de configuración de conexión {0}", ruta);
            }
            propiedades = cargadas;
        }
        return propiedades;
    }

    private static Path rutaConfiguracion() {
        return Paths.get(primeraDefinida(System.getProperty("hasnet.config"), System.getenv("HASNET_CONFIG"), RUTA_CONFIGURACION_DEFECTO));
    }

    private static Path rutaClave() {
        return Paths.get(primeraDefinida(System.getProperty("hasnet.key"), System.getenv("HASNET_KEY_FILE"), RUTA_CLAVE_DEFECTO));
    }

    private static String primeraDefinida(String... valores) {
        for (String valor : valores) {
            if (valor != null && !valor.trim().isEmpty()) {
                return valor.trim();
            }
        }
        return null;
    }

    public static Path rutaConfiguracionActual() {
        return rutaConfiguracion();
    }

    public static Path rutaClaveActual() {
        return rutaClave();
    }

    public static boolean esCifrado(String valor) {
        return valor != null && valor.startsWith(INICIO_CIFRADO) && valor.endsWith(FIN_CIFRADO);
    }

    public static String envolverCifrado(String texto) {
        return INICIO_CIFRADO + cifrar(texto) + FIN_CIFRADO;
    }

    public static synchronized boolean generarClaveSiNoExiste() {
        String variable = System.getenv("HASNET_CLAVE_MAESTRA");
        Path ruta = rutaClave();
        if ((variable != null && !variable.trim().isEmpty()) || Files.exists(ruta)) {
            return false;
        }
        try {
            KeyGenerator generador = KeyGenerator.getInstance("AES");
            generador.init(128);
            if (ruta.getParent() != null) {
                Files.createDirectories(ruta.getParent());
            }
            Files.write(ruta, Base64.getEncoder().encode(generador.generateKey().getEncoded()));
            return true;
        } catch (IOException | GeneralSecurityException ex) {
            throw new IllegalStateException("No se pudo crear la clave maestra en " + ruta, ex);
        }
    }

    public static SecretKey leerClave(Path ruta) {
        try {
            if (!Files.isReadable(ruta)) {
                throw new IllegalStateException("No se encontró la clave maestra en " + ruta);
            }
            return construirClave(new String(Files.readAllBytes(ruta), StandardCharsets.US_ASCII));
        } catch (IOException ex) {
            throw new IllegalStateException("No se pudo leer la clave maestra " + ruta, ex);
        }
    }

    public static String descifrarCon(String valor, SecretKey clave) {
        if (valor == null) {
            return null;
        }
        String recortado = valor.trim();
        if (!esCifrado(recortado)) {
            return recortado;
        }
        return descifrar(recortado.substring(INICIO_CIFRADO.length(), recortado.length() - FIN_CIFRADO.length()), clave);
    }

    private static SecretKey construirClave(String claveBase64) {
        try {
            byte[] bytes = Base64.getDecoder().decode(claveBase64.trim());
            if (bytes.length != 16 && bytes.length != 24 && bytes.length != 32) {
                throw new IllegalStateException("La clave maestra tiene una longitud inválida");
            }
            return new SecretKeySpec(bytes, "AES");
        } catch (IllegalArgumentException ex) {
            throw new IllegalStateException("La clave maestra no es válida", ex);
        }
    }

    private static SecretKey cargarClaveMaestra() {
        String claveBase64 = System.getenv("HASNET_CLAVE_MAESTRA");
        if (claveBase64 != null && !claveBase64.trim().isEmpty()) {
            return construirClave(claveBase64);
        }
        return leerClave(rutaClave());
    }

    public static String cifrar(String texto) {
        try {
            byte[] iv = new byte[LONGITUD_IV];
            new SecureRandom().nextBytes(iv);
            Cipher cifrador = Cipher.getInstance(TRANSFORMACION);
            cifrador.init(Cipher.ENCRYPT_MODE, cargarClaveMaestra(), new GCMParameterSpec(LONGITUD_TAG, iv));
            byte[] cifrado = cifrador.doFinal(texto.getBytes(StandardCharsets.UTF_8));
            byte[] resultado = new byte[iv.length + cifrado.length];
            System.arraycopy(iv, 0, resultado, 0, iv.length);
            System.arraycopy(cifrado, 0, resultado, iv.length, cifrado.length);
            return Base64.getEncoder().encodeToString(resultado);
        } catch (GeneralSecurityException ex) {
            throw new IllegalStateException("No se pudo cifrar el valor", ex);
        }
    }

    private static String descifrar(String textoBase64) {
        return descifrar(textoBase64, cargarClaveMaestra());
    }

    private static String descifrar(String textoBase64, SecretKey clave) {
        try {
            byte[] datos = Base64.getDecoder().decode(textoBase64);
            if (datos.length <= LONGITUD_IV) {
                throw new IllegalStateException("Valor cifrado inválido");
            }
            Cipher descifrador = Cipher.getInstance(TRANSFORMACION);
            descifrador.init(Cipher.DECRYPT_MODE, clave, new GCMParameterSpec(LONGITUD_TAG, datos, 0, LONGITUD_IV));
            byte[] plano = descifrador.doFinal(datos, LONGITUD_IV, datos.length - LONGITUD_IV);
            String resultado = new String(plano, StandardCharsets.UTF_8);
            Arrays.fill(plano, (byte) 0);
            return resultado;
        } catch (GeneralSecurityException | IllegalArgumentException ex) {
            throw new IllegalStateException("No se pudo descifrar la credencial; verifique la clave maestra", ex);
        }
    }

    public static void main(String[] args) throws Exception {
        String accion = args.length > 0 ? args[0] : "";

        if ("generar-clave".equals(accion)) {
            if (generarClaveSiNoExiste()) {
                System.out.println("Clave maestra creada en " + rutaClave());
            } else {
                System.out.println("Ya existe una clave maestra; no se sobrescribe.");
            }
            return;
        }

        if ("cifrar".equals(accion)) {
            String texto;
            Console consola = System.console();
            if (consola != null) {
                char[] leido = consola.readPassword("Valor a cifrar: ");
                texto = new String(leido);
                Arrays.fill(leido, ' ');
            } else {
                texto = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8)).readLine();
                if (texto != null) {
                    texto = texto.replace("﻿", "").replace("�", "");
                }
            }
            if (texto == null || texto.isEmpty()) {
                System.out.println("No se ingresó ningún valor.");
                return;
            }
            System.out.println(INICIO_CIFRADO + cifrar(texto) + FIN_CIFRADO);
            return;
        }

        System.out.println("Uso:");
        System.out.println("  java -cp HasNet.jar " + CredencialesBaseDatos.class.getName() + " generar-clave");
        System.out.println("  java -cp HasNet.jar " + CredencialesBaseDatos.class.getName() + " cifrar");
    }
}
