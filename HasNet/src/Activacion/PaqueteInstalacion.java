package Activacion;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Properties;
import javax.crypto.AEADBadTagException;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public final class PaqueteInstalacion {

    public static final String NOMBRE_ARCHIVO = "hasnet-instalacion.dat";

    private static final String ENCABEZADO = "HASNET-PAQUETE-1";
    private static final int ITERACIONES = 210000;
    private static final int LONGITUD_SAL = 16;
    private static final int LONGITUD_IV = 12;
    private static final String[] LLAVES = {"local.usuario", "local.clave", "local.puerto", "local.parametros",
        "pagos.host", "pagos.puerto", "pagos.baseDatos", "pagos.usuario", "pagos.clave", "pagos.parametros"};

    private PaqueteInstalacion() {
    }

    public static Path buscar() {
        for (Path candidato : candidatos()) {
            if (Files.isReadable(candidato)) {
                return candidato;
            }
        }
        return null;
    }

    private static List<Path> candidatos() {
        List<Path> candidatos = new ArrayList<>();
        candidatos.add(Paths.get(NOMBRE_ARCHIVO).toAbsolutePath());
        try {
            Path ubicacion = Paths.get(PaqueteInstalacion.class.getProtectionDomain().getCodeSource().getLocation().toURI());
            Path carpeta = Files.isDirectory(ubicacion) ? ubicacion : ubicacion.getParent();
            if (carpeta != null) {
                candidatos.add(carpeta.resolve(NOMBRE_ARCHIVO));
                if (carpeta.getParent() != null && carpeta.getParent().getParent() != null) {
                    candidatos.add(carpeta.getParent().getParent().resolve(NOMBRE_ARCHIVO));
                }
            }
        } catch (URISyntaxException | SecurityException | NullPointerException ex) {
            return candidatos;
        }
        return candidatos;
    }

    public static void aplicar(Path archivo, char[] claveInstalacion, ActivadorTerminal.Datos datos) throws IOException {
        Properties valores = abrir(archivo, claveInstalacion);
        datos.usuarioLocal = valor(valores, "local.usuario", datos.usuarioLocal);
        datos.claveLocal = valor(valores, "local.clave", datos.claveLocal);
        datos.puertoLocal = valor(valores, "local.puerto", datos.puertoLocal);
        datos.parametrosLocal = valor(valores, "local.parametros", datos.parametrosLocal);
        datos.hostPagos = valor(valores, "pagos.host", datos.hostPagos);
        datos.puertoPagos = valor(valores, "pagos.puerto", datos.puertoPagos);
        datos.baseDatosPagos = valor(valores, "pagos.baseDatos", datos.baseDatosPagos);
        datos.usuarioPagos = valor(valores, "pagos.usuario", datos.usuarioPagos);
        datos.clavePagos = valor(valores, "pagos.clave", datos.clavePagos);
        datos.parametrosPagos = valor(valores, "pagos.parametros", datos.parametrosPagos);
    }

    private static String valor(Properties valores, String llave, String defecto) {
        String valor = valores.getProperty(llave);
        return valor == null ? defecto : valor;
    }

    private static Properties abrir(Path archivo, char[] claveInstalacion) throws IOException {
        if (claveInstalacion == null || claveInstalacion.length == 0) {
            throw new IllegalStateException("Escriba la clave de instalación");
        }
        List<String> lineas = Files.readAllLines(archivo, StandardCharsets.US_ASCII);
        if (lineas.size() < 2 || !ENCABEZADO.equals(lineas.get(0).trim())) {
            throw new IllegalStateException("El archivo " + archivo + " no es un paquete de instalación válido");
        }
        try {
            byte[] datos = Base64.getDecoder().decode(lineas.get(1).trim());
            byte[] sal = Arrays.copyOfRange(datos, 0, LONGITUD_SAL);
            byte[] iv = Arrays.copyOfRange(datos, LONGITUD_SAL, LONGITUD_SAL + LONGITUD_IV);
            Cipher descifrador = Cipher.getInstance("AES/GCM/NoPadding");
            descifrador.init(Cipher.DECRYPT_MODE, derivar(claveInstalacion, sal), new GCMParameterSpec(128, iv));
            byte[] plano = descifrador.doFinal(datos, LONGITUD_SAL + LONGITUD_IV, datos.length - LONGITUD_SAL - LONGITUD_IV);
            Properties valores = new Properties();
            valores.load(new StringReader(new String(plano, StandardCharsets.UTF_8)));
            Arrays.fill(plano, (byte) 0);
            return valores;
        } catch (AEADBadTagException ex) {
            throw new IllegalStateException("Clave de instalación incorrecta");
        } catch (GeneralSecurityException | IllegalArgumentException | ArrayIndexOutOfBoundsException ex) {
            throw new IllegalStateException("El paquete de instalación está dañado", ex);
        }
    }

    public static void crear(Path archivo, Properties valores, char[] claveInstalacion) throws IOException {
        try {
            StringWriter texto = new StringWriter();
            valores.store(texto, null);
            byte[] sal = new byte[LONGITUD_SAL];
            byte[] iv = new byte[LONGITUD_IV];
            SecureRandom aleatorio = new SecureRandom();
            aleatorio.nextBytes(sal);
            aleatorio.nextBytes(iv);
            Cipher cifrador = Cipher.getInstance("AES/GCM/NoPadding");
            cifrador.init(Cipher.ENCRYPT_MODE, derivar(claveInstalacion, sal), new GCMParameterSpec(128, iv));
            byte[] cifrado = cifrador.doFinal(texto.toString().getBytes(StandardCharsets.UTF_8));
            byte[] resultado = new byte[sal.length + iv.length + cifrado.length];
            System.arraycopy(sal, 0, resultado, 0, sal.length);
            System.arraycopy(iv, 0, resultado, sal.length, iv.length);
            System.arraycopy(cifrado, 0, resultado, sal.length + iv.length, cifrado.length);
            String contenido = ENCABEZADO + "\r\n" + Base64.getEncoder().encodeToString(resultado) + "\r\n";
            Files.write(archivo, contenido.getBytes(StandardCharsets.US_ASCII));
        } catch (GeneralSecurityException ex) {
            throw new IllegalStateException("No se pudo cifrar el paquete de instalación", ex);
        }
    }

    private static SecretKey derivar(char[] clave, byte[] sal) throws GeneralSecurityException {
        PBEKeySpec especificacion = new PBEKeySpec(clave, sal, ITERACIONES, 128);
        try {
            return new SecretKeySpec(SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256").generateSecret(especificacion).getEncoded(), "AES");
        } finally {
            especificacion.clearPassword();
        }
    }

    public static void main(String[] args) throws Exception {
        if (args.length == 0 || !"crear".equals(args[0])) {
            System.out.println("Uso: java -cp ActivadorHasNet.jar " + PaqueteInstalacion.class.getName() + " crear [archivo]");
            System.out.println("Pide las credenciales de las bases de datos y la clave de instalación, y genera el paquete cifrado.");
            return;
        }
        Path destino = Paths.get(args.length > 1 && !args[1].startsWith("--") ? args[1] : NOMBRE_ARCHIVO).toAbsolutePath();
        Path credenciales = null;
        for (int i = 1; i < args.length - 1; i++) {
            if ("--credenciales".equals(args[i])) {
                credenciales = Paths.get(args[i + 1]);
            }
        }
        ActivadorTerminal.Datos actual = ActivadorTerminal.cargarExistente();
        actual.hostLocal = "localhost";

        if (credenciales != null && Files.isReadable(credenciales)) {
            Properties archivo = new Properties();
            try (java.io.Reader lector = Files.newBufferedReader(credenciales, StandardCharsets.UTF_8)) {
                archivo.load(lector);
            }
            actual.usuarioLocal = valor(archivo, "local.usuario", actual.usuarioLocal);
            actual.claveLocal = valor(archivo, "local.clave", actual.claveLocal);
            actual.hostPagos = valor(archivo, "pagos.host", actual.hostPagos);
            actual.usuarioPagos = valor(archivo, "pagos.usuario", actual.usuarioPagos);
            actual.clavePagos = valor(archivo, "pagos.clave", actual.clavePagos);
            System.out.println("Credenciales tomadas de " + credenciales.getFileName() + ".");
        } else {
            System.out.println("Credenciales que llevará el paquete (presione Enter para conservar el valor entre corchetes).");
            System.out.println();
            actual.usuarioLocal = leerTexto("Usuario de la base de datos local", actual.usuarioLocal.isEmpty() ? "hasnet" : actual.usuarioLocal);
            actual.claveLocal = leerSecreto("Contraseña de la base de datos local", actual.claveLocal);
            actual.hostPagos = leerTexto("Servidor de la base de datos de pagos", actual.hostPagos);
            actual.usuarioPagos = leerTexto("Usuario de la base de datos de pagos", actual.usuarioPagos);
            actual.clavePagos = leerSecreto("Contraseña de la base de datos de pagos", actual.clavePagos);
        }
        if (actual.usuarioLocal.isEmpty() || actual.claveLocal.isEmpty() || actual.hostPagos.isEmpty()
                || actual.usuarioPagos.isEmpty() || actual.clavePagos.isEmpty()) {
            System.out.println("Faltan datos: no se creó el paquete.");
            System.exit(1);
        }

        System.out.println();
        boolean localCorrecta = probar("Base de datos local (este equipo)", () -> ActivadorTerminal.conectarLocal(actual).close());
        boolean pagosCorrecta = probar("Base de datos de pagos", () -> ActivadorTerminal.conectarPagos(actual).close());
        if (!pagosCorrecta || !localCorrecta) {
            String aviso = !pagosCorrecta ? "La conexión a pagos falló; revise el servidor, usuario y contraseña."
                    : "La base local de este equipo no respondió con esas credenciales (normal si aquí no hay una base de HasNet).";
            System.out.println(aviso);
            if (!"s".equalsIgnoreCase(leerTexto("¿Crear el paquete de todas formas? (s/n)", "n"))) {
                System.out.println("No se creó el paquete.");
                System.exit(1);
            }
        }

        Properties valores = new Properties();
        String[] datos = {actual.usuarioLocal, actual.claveLocal, actual.puertoLocal, actual.parametrosLocal,
            actual.hostPagos, actual.puertoPagos, actual.baseDatosPagos, actual.usuarioPagos, actual.clavePagos, actual.parametrosPagos};
        for (int i = 0; i < LLAVES.length; i++) {
            valores.setProperty(LLAVES[i], datos[i] == null ? "" : datos[i]);
        }

        System.out.println();
        char[] clave = leerClave("Clave de instalación (mínimo 10 caracteres): ");
        char[] confirmacion = leerClave("Repita la clave: ");
        if (clave.length < 10 || !Arrays.equals(clave, confirmacion)) {
            System.out.println("Las claves no coinciden o tienen menos de 10 caracteres.");
            System.exit(1);
        }
        crear(destino, valores, clave);
        Arrays.fill(clave, ' ');
        Arrays.fill(confirmacion, ' ');
        System.out.println("Paquete creado en " + destino);
    }

    private static BufferedReader entrada;

    private interface Prueba {

        void ejecutar() throws Exception;
    }

    private static boolean probar(String descripcion, Prueba prueba) {
        try {
            prueba.ejecutar();
            System.out.println("  [OK]    " + descripcion);
            return true;
        } catch (Exception ex) {
            System.out.println("  [FALLA] " + descripcion + ": " + ex.getMessage());
            return false;
        }
    }

    private static String leerTexto(String mensaje, String defecto) throws IOException {
        String linea = leerLinea(mensaje + (defecto == null || defecto.isEmpty() ? "" : " [" + defecto + "]") + ": ");
        return linea == null || linea.trim().isEmpty() ? (defecto == null ? "" : defecto) : linea.trim();
    }

    private static String leerSecreto(String mensaje, String actual) throws IOException {
        char[] valor = leerClave(mensaje + (actual == null || actual.isEmpty() ? "" : " [sin cambios]") + ": ");
        String texto = new String(valor);
        Arrays.fill(valor, ' ');
        return texto.isEmpty() ? (actual == null ? "" : actual) : texto;
    }

    private static String leerLinea(String mensaje) throws IOException {
        Console consola = System.console();
        if (consola != null) {
            return consola.readLine("%s", mensaje);
        }
        System.out.print(mensaje);
        if (entrada == null) {
            entrada = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
        }
        String linea = entrada.readLine();
        return linea == null ? null : linea.replace("﻿", "");
    }

    private static char[] leerClave(String mensaje) throws IOException {
        Console consola = System.console();
        if (consola != null) {
            return consola.readPassword(mensaje);
        }
        System.out.print(mensaje);
        if (entrada == null) {
            entrada = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
        }
        String linea = entrada.readLine();
        return linea == null ? new char[0] : linea.replace("﻿", "").toCharArray();
    }
}
