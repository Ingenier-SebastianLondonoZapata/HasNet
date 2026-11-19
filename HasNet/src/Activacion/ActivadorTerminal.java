package Activacion;

import Utilidades.BaseDatos.CredencialesBaseDatos;
import Utilidades.Constantes;
import Utilidades.Seguridad.IdentidadInstalacion;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.AtomicMoveNotSupportedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.crypto.SecretKey;

public final class ActivadorTerminal {

    public static final String NUEVA_TERMINAL = "Nueva terminal";
    public static final String PARAMETROS_PAGOS_DEFECTO = "useUnicode=true&serverTimezone=UTC&sslMode=REQUIRED";

    private static final Pattern HOST_VALIDO = Pattern.compile("[A-Za-z0-9._-]{1,255}");
    private static final Pattern NOMBRE_TERMINAL = Pattern.compile("TERM-(\\d{1,5})");
    private ActivadorTerminal() {
    }

    public static final class Datos {

        public String hostLocal = "localhost";
        public String puertoLocal = "3306";
        public String baseDatosLocal = Constantes.BASE_DATOS_PRINCIPAL;
        public String usuarioLocal = "";
        public String claveLocal = "";
        public String parametrosLocal = "";
        public String hostPagos = "";
        public String puertoPagos = "3306";
        public String baseDatosPagos = "HSPagos";
        public String usuarioPagos = "";
        public String clavePagos = "";
        public String parametrosPagos = PARAMETROS_PAGOS_DEFECTO;
        public String identificadorCliente = "";
        public String terminalActual = "";
        public String terminal = NUEVA_TERMINAL;
        public boolean protegerCarpeta = true;
    }

    public static final class Resultado {

        public final String terminal;
        public final String cliente;
        public final List<String> avisos;

        Resultado(String terminal, String cliente, List<String> avisos) {
            this.terminal = terminal;
            this.cliente = cliente;
            this.avisos = avisos;
        }
    }

    public static Datos cargarExistente() {
        Path ruta = CredencialesBaseDatos.rutaConfiguracionActual();
        Path rutaClave = CredencialesBaseDatos.rutaClaveActual();
        if (!Files.isReadable(ruta) || !Files.isReadable(rutaClave)) {
            return new Datos();
        }
        try {
            Datos datos = leerDatos(ruta, CredencialesBaseDatos.leerClave(rutaClave));
            datos.terminal = datos.terminalActual.isEmpty() ? NUEVA_TERMINAL : datos.terminalActual;
            return datos;
        } catch (IllegalStateException | IOException ex) {
            return new Datos();
        }
    }

    private static Datos leerDatos(Path ruta, SecretKey clave) throws IOException {
        Properties propiedades = new Properties();
        try (InputStream entrada = Files.newInputStream(ruta)) {
            propiedades.load(new InputStreamReader(entrada, StandardCharsets.UTF_8));
        }
        Datos datos = new Datos();
        datos.hostLocal = valor(propiedades, "local.host", datos.hostLocal);
        datos.puertoLocal = valor(propiedades, "local.puerto", datos.puertoLocal);
        datos.usuarioLocal = valor(propiedades, "local.usuario", "");
        datos.claveLocal = CredencialesBaseDatos.descifrarCon(valor(propiedades, "local.clave", ""), clave);
        datos.parametrosLocal = valor(propiedades, "local.parametros", "");
        datos.hostPagos = valor(propiedades, "pagos.host", "");
        datos.puertoPagos = valor(propiedades, "pagos.puerto", datos.puertoPagos);
        datos.baseDatosPagos = valor(propiedades, "pagos.baseDatos", datos.baseDatosPagos);
        datos.usuarioPagos = valor(propiedades, "pagos.usuario", "");
        datos.clavePagos = CredencialesBaseDatos.descifrarCon(valor(propiedades, "pagos.clave", ""), clave);
        datos.parametrosPagos = valor(propiedades, "pagos.parametros", PARAMETROS_PAGOS_DEFECTO);
        datos.identificadorCliente = CredencialesBaseDatos.descifrarCon(valor(propiedades, IdentidadInstalacion.CLAVE_IDENTIFICADOR_CLIENTE, ""), clave);
        datos.terminalActual = valor(propiedades, IdentidadInstalacion.CLAVE_NOMBRE_TERMINAL, "");
        return datos;
    }

    private static String valor(Properties propiedades, String llave, String defecto) {
        String valor = propiedades.getProperty(llave);
        return valor == null || valor.trim().isEmpty() ? defecto : valor.trim();
    }

    public static String validarCliente(Datos datos) throws SQLException {
        requerido(datos.identificadorCliente, "el identificador del cliente");
        try (Connection conexion = conectarPagos(datos);
                PreparedStatement consulta = conexion.prepareStatement("SELECT nombre, nit FROM bdClientes WHERE idCliente = ?")) {
            consulta.setString(1, datos.identificadorCliente.trim());
            try (ResultSet resultado = consulta.executeQuery()) {
                if (!resultado.next()) {
                    throw new IllegalStateException("El cliente no está registrado en la aplicación de pagos. Regístrelo primero en PagosHasNet.");
                }
                return resultado.getString("nombre") + " (NIT " + resultado.getString("nit") + ")";
            }
        }
    }

    public static int terminalesPermitidas(Datos datos) throws SQLException {
        try (Connection conexion = conectarPagos(datos);
                PreparedStatement consulta = conexion.prepareStatement("SELECT numeroTerminales FROM bdClientes WHERE idCliente = ?")) {
            consulta.setString(1, datos.identificadorCliente.trim());
            try (ResultSet resultado = consulta.executeQuery()) {
                if (!resultado.next()) {
                    throw new IllegalStateException("El cliente no está registrado en la aplicación de pagos. Regístrelo primero en PagosHasNet.");
                }
                return 1 + resultado.getInt(1);
            }
        }
    }

    private static void validarCupoTerminales(Datos datos, Connection conexionLocal) throws SQLException {
        int permitidas = terminalesPermitidas(datos);
        int activadas;
        try (Statement consulta = conexionLocal.createStatement();
                ResultSet resultado = consulta.executeQuery("SELECT COUNT(*) FROM bdTerminales")) {
            activadas = resultado.next() ? resultado.getInt(1) : 0;
        }
        if (activadas >= permitidas) {
            throw new IllegalStateException("El cliente tiene contratadas " + permitidas + " terminal(es) (servidor + " + (permitidas - 1)
                    + " adicionales) y ya hay " + activadas + " activada(s). Amplíe el número de terminales en PagosHasNet"
                    + " o reasigne una terminal existente.");
        }
    }

    public static List<String> listarTerminales(Datos datos) throws SQLException {
        List<String> terminales = new ArrayList<>();
        try (Connection conexion = conectarLocal(datos)) {
            asegurarTablaTerminales(conexion);
            try (Statement consulta = conexion.createStatement();
                    ResultSet resultado = consulta.executeQuery("SELECT terminal FROM bdTerminales ORDER BY terminal")) {
                while (resultado.next()) {
                    terminales.add(resultado.getString(1));
                }
            }
        }
        return terminales;
    }

    public static Resultado activar(Datos datos) throws SQLException, IOException {
        List<String> avisos = new ArrayList<>();
        requerido(datos.hostLocal, "la dirección del servidor de base de datos");
        requerido(datos.usuarioLocal, "el usuario de la base de datos local");
        requerido(datos.claveLocal, "la contraseña de la base de datos local");
        requerido(datos.hostPagos, "el servidor de la base de datos de pagos");
        requerido(datos.usuarioPagos, "el usuario de la base de datos de pagos");
        requerido(datos.clavePagos, "la contraseña de la base de datos de pagos");

        String cliente = validarCliente(datos);

        if (CredencialesBaseDatos.generarClaveSiNoExiste()) {
            avisos.add("Se creó una clave maestra nueva en " + CredencialesBaseDatos.rutaClaveActual());
        }

        String token = IdentidadInstalacion.generarToken();
        String equipo = obtenerNombreEquipo();
        Path destino = CredencialesBaseDatos.rutaConfiguracionActual();
        Path temporal = destino.resolveSibling(destino.getFileName() + ".nuevo");

        String terminal;
        try (Connection conexion = conectarLocal(datos)) {
            asegurarTablaTerminales(conexion);
            if (NUEVA_TERMINAL.equals(datos.terminal)) {
                validarCupoTerminales(datos, conexion);
            }
            terminal = NUEVA_TERMINAL.equals(datos.terminal) ? siguienteNombreTerminal(conexion) : datos.terminal;
            escribirConfiguracion(temporal, datos, terminal, token);
            registrarTerminal(conexion, datos, terminal, IdentidadInstalacion.hashToken(token), equipo);
        } catch (SQLException | RuntimeException ex) {
            Files.deleteIfExists(temporal);
            throw ex;
        }

        try {
            Files.move(temporal, destino, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
        } catch (AtomicMoveNotSupportedException ex) {
            Files.move(temporal, destino, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            throw new IOException("La terminal " + terminal + " quedó registrada pero no se pudo guardar " + destino
                    + ". Ejecute de nuevo la activación como administrador y elija reasignar " + terminal + ".", ex);
        }
        CredencialesBaseDatos.recargar();

        if (datos.protegerCarpeta) {
            String error = protegerCarpeta(destino.getParent());
            avisos.add(error == null ? "Permisos de " + destino.getParent() + " restringidos (solo administradores pueden modificar)"
                    : "No se pudieron restringir los permisos: " + error);
        }

        return new Resultado(terminal, cliente, avisos);
    }

    private static void asegurarTablaTerminales(Connection conexion) throws SQLException {
        try (Statement sentencia = conexion.createStatement()) {
            if (!existeTabla(conexion, "bdTerminales")) {
                if (existeTabla(conexion, "bdLogErrores")) {
                    sentencia.execute("RENAME TABLE bdLogErrores TO bdTerminales");
                } else {
                    sentencia.execute("CREATE TABLE bdTerminales ("
                            + "Id VARCHAR(128) NULL, terminal VARCHAR(10) NOT NULL, ubicacionCaja VARCHAR(255) NULL, "
                            + "equipo VARCHAR(100) NULL, fechaActivacion DATETIME NULL, ultimaSenal DATETIME NULL, "
                            + "UNIQUE KEY uk_bdTerminales_terminal (terminal), UNIQUE KEY uk_bdTerminales_id (Id)"
                            + ") ENGINE=InnoDB DEFAULT CHARSET=utf8");
                }
            }
            if (!existeColumna(conexion, "bdTerminales", "equipo")) {
                sentencia.execute("ALTER TABLE bdTerminales MODIFY Id VARCHAR(128) NULL, MODIFY terminal VARCHAR(10) NOT NULL, "
                        + "ADD COLUMN equipo VARCHAR(100) NULL, ADD COLUMN fechaActivacion DATETIME NULL");
            }
            if (!existeColumna(conexion, "bdTerminales", "ultimaSenal")) {
                sentencia.execute("ALTER TABLE bdTerminales ADD COLUMN ultimaSenal DATETIME NULL");
            }
            if (!existeIndice(conexion, "bdTerminales", "uk_bdTerminales_terminal")) {
                sentencia.execute("ALTER TABLE bdTerminales ADD UNIQUE KEY uk_bdTerminales_terminal (terminal)");
            }
            if (!existeIndice(conexion, "bdTerminales", "uk_bdTerminales_id")) {
                sentencia.execute("ALTER TABLE bdTerminales ADD UNIQUE KEY uk_bdTerminales_id (Id)");
            }
            migrarPrefijosAMaestra(conexion, sentencia);
            if (existeColumna(conexion, "bdTerminales", "codigo1")) {
                sentencia.execute("ALTER TABLE bdTerminales CHANGE COLUMN codigo1 ubicacionCaja VARCHAR(255) NULL");
            }
            if (existeColumna(conexion, "bdTerminales", "codigo2")) {
                sentencia.execute("ALTER TABLE bdTerminales DROP COLUMN codigo2");
            }
        }
    }

    private static void migrarPrefijosAMaestra(Connection conexion, Statement sentencia) throws SQLException {
        if (!existeTabla(conexion, "bdMaestra")) {
            return;
        }
        if (!existeColumna(conexion, "bdMaestra", "prefijoNotaCredito")) {
            sentencia.execute("ALTER TABLE bdMaestra ADD COLUMN prefijoNotaCredito VARCHAR(10) NULL, ADD COLUMN prefijoNotaDebito VARCHAR(10) NULL, "
                    + "ADD COLUMN prefijoAbonos VARCHAR(10) NULL, ADD COLUMN prefijoEgresos VARCHAR(10) NULL");
        }
        if (existeColumna(conexion, "bdTerminales", "prefijoNotaCredito")) {
            sentencia.executeUpdate("UPDATE bdMaestra m JOIN (SELECT prefijoNotaCredito, prefijoNotaDebito, prefijoAbonos, prefijoEgresos FROM bdTerminales "
                    + "ORDER BY terminal = 'TERM-1' DESC, terminal LIMIT 1) t "
                    + "SET m.prefijoNotaCredito = COALESCE(m.prefijoNotaCredito, t.prefijoNotaCredito), "
                    + "m.prefijoNotaDebito = COALESCE(m.prefijoNotaDebito, t.prefijoNotaDebito), "
                    + "m.prefijoAbonos = COALESCE(m.prefijoAbonos, t.prefijoAbonos), "
                    + "m.prefijoEgresos = COALESCE(m.prefijoEgresos, t.prefijoEgresos) WHERE m.Id = 1");
            sentencia.execute("ALTER TABLE bdTerminales DROP COLUMN prefijoNotaCredito, DROP COLUMN prefijoNotaDebito, "
                    + "DROP COLUMN prefijoAbonos, DROP COLUMN prefijoEgresos");
        }
        sentencia.executeUpdate("UPDATE bdMaestra SET prefijoNotaCredito = COALESCE(prefijoNotaCredito, 'NC'), "
                + "prefijoNotaDebito = COALESCE(prefijoNotaDebito, 'ND'), prefijoAbonos = COALESCE(prefijoAbonos, 'A'), "
                + "prefijoEgresos = COALESCE(prefijoEgresos, 'E') WHERE Id = 1");
    }

    private static boolean existeTabla(Connection conexion, String tabla) throws SQLException {
        return contar(conexion, "SELECT COUNT(*) FROM information_schema.TABLES WHERE TABLE_SCHEMA = DATABASE() AND LOWER(TABLE_NAME) = LOWER(?)", tabla) > 0;
    }

    private static boolean existeColumna(Connection conexion, String tabla, String columna) throws SQLException {
        return contar(conexion, "SELECT COUNT(*) FROM information_schema.COLUMNS WHERE TABLE_SCHEMA = DATABASE() AND LOWER(TABLE_NAME) = LOWER(?) "
                + "AND LOWER(COLUMN_NAME) = LOWER(?)", tabla, columna) > 0;
    }

    private static boolean existeIndice(Connection conexion, String tabla, String indice) throws SQLException {
        return contar(conexion, "SELECT COUNT(*) FROM information_schema.STATISTICS WHERE TABLE_SCHEMA = DATABASE() AND LOWER(TABLE_NAME) = LOWER(?) "
                + "AND LOWER(INDEX_NAME) = LOWER(?)", tabla, indice) > 0;
    }

    private static int contar(Connection conexion, String sql, String... parametros) throws SQLException {
        try (PreparedStatement consulta = conexion.prepareStatement(sql)) {
            for (int i = 0; i < parametros.length; i++) {
                consulta.setString(i + 1, parametros[i]);
            }
            try (ResultSet resultado = consulta.executeQuery()) {
                return resultado.next() ? resultado.getInt(1) : 0;
            }
        }
    }

    private static String siguienteNombreTerminal(Connection conexion) throws SQLException {
        int mayor = 0;
        try (Statement consulta = conexion.createStatement();
                ResultSet resultado = consulta.executeQuery("SELECT terminal FROM bdTerminales")) {
            while (resultado.next()) {
                Matcher coincidencia = NOMBRE_TERMINAL.matcher(String.valueOf(resultado.getString(1)));
                if (coincidencia.matches()) {
                    mayor = Math.max(mayor, Integer.parseInt(coincidencia.group(1)));
                }
            }
        }
        return "TERM-" + (mayor + 1);
    }

    private static void registrarTerminal(Connection conexion, Datos datos, String terminal, String hashToken, String equipo) throws SQLException {
        boolean autoCommitPrevio = conexion.getAutoCommit();
        conexion.setAutoCommit(false);
        try {
            if (NUEVA_TERMINAL.equals(datos.terminal)) {
                try (PreparedStatement insercion = conexion.prepareStatement(
                        "INSERT INTO bdTerminales (Id, terminal, ubicacionCaja, equipo, fechaActivacion) VALUES (?, ?, ?, ?, NOW())")) {
                    insercion.setString(1, hashToken);
                    insercion.setString(2, terminal);
                    insercion.setString(3, ubicacionCajaPorDefecto(conexion));
                    insercion.setString(4, equipo);
                    insercion.executeUpdate();
                }
                Matcher numero = NOMBRE_TERMINAL.matcher(terminal);
                if (numero.matches()) {
                    try (PreparedStatement consecutivo = conexion.prepareStatement(
                            "UPDATE bdConsecutivos SET numero = GREATEST(CAST(numero AS UNSIGNED), ?) WHERE Id = 'TERM'")) {
                        consecutivo.setInt(1, Integer.parseInt(numero.group(1)) + 1);
                        consecutivo.executeUpdate();
                    }
                }
            } else {
                try (PreparedStatement actualizacion = conexion.prepareStatement(
                        "UPDATE bdTerminales SET Id = ?, equipo = ?, fechaActivacion = NOW() WHERE terminal = ?")) {
                    actualizacion.setString(1, hashToken);
                    actualizacion.setString(2, equipo);
                    actualizacion.setString(3, terminal);
                    if (actualizacion.executeUpdate() != 1) {
                        throw new IllegalStateException("La terminal " + terminal + " no existe en esta base de datos. "
                                + "Use \"Probar conexión\" y elija \"" + NUEVA_TERMINAL + "\" o una terminal de la lista.");
                    }
                }
            }
            conexion.commit();
        } catch (SQLException | RuntimeException ex) {
            conexion.rollback();
            throw ex;
        } finally {
            conexion.setAutoCommit(autoCommitPrevio);
        }
    }

    private static String ubicacionCajaPorDefecto(Connection conexion) throws SQLException {
        try (Statement consulta = conexion.createStatement();
                ResultSet resultado = consulta.executeQuery("SELECT ubicacionCaja FROM bdTerminales "
                        + "WHERE ubicacionCaja IS NOT NULL AND ubicacionCaja <> '' ORDER BY terminal = 'TERM-1' DESC, terminal LIMIT 1")) {
            return resultado.next() ? resultado.getString(1) : "";
        }
    }

    private static void escribirConfiguracion(Path ruta, Datos datos, String terminal, String token) throws IOException {
        StringBuilder contenido = new StringBuilder();
        contenido.append("# Configuración de la instalación HasNet generada por la herramienta de activación el ")
                .append(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date())).append("\n");
        contenido.append("# Contiene la identidad de esta terminal: no copiar a otros equipos.\n\n");
        linea(contenido, "local.host", datos.hostLocal.trim());
        linea(contenido, "local.puerto", datos.puertoLocal.trim());
        linea(contenido, "local.usuario", datos.usuarioLocal.trim());
        linea(contenido, "local.clave", CredencialesBaseDatos.envolverCifrado(datos.claveLocal));
        linea(contenido, "local.parametros", datos.parametrosLocal.trim());
        contenido.append("\n");
        linea(contenido, "pagos.host", datos.hostPagos.trim());
        linea(contenido, "pagos.puerto", datos.puertoPagos.trim());
        linea(contenido, "pagos.baseDatos", datos.baseDatosPagos.trim());
        linea(contenido, "pagos.usuario", datos.usuarioPagos.trim());
        linea(contenido, "pagos.clave", CredencialesBaseDatos.envolverCifrado(datos.clavePagos));
        linea(contenido, "pagos.parametros", datos.parametrosPagos.trim());
        contenido.append("\n");
        linea(contenido, IdentidadInstalacion.CLAVE_IDENTIFICADOR_CLIENTE, CredencialesBaseDatos.envolverCifrado(datos.identificadorCliente.trim()));
        linea(contenido, IdentidadInstalacion.CLAVE_NOMBRE_TERMINAL, terminal);
        linea(contenido, IdentidadInstalacion.CLAVE_TOKEN_TERMINAL, CredencialesBaseDatos.envolverCifrado(token));

        if (ruta.getParent() != null) {
            Files.createDirectories(ruta.getParent());
        }
        Files.write(ruta, contenido.toString().getBytes(StandardCharsets.UTF_8));
    }

    private static void linea(StringBuilder contenido, String llave, String valor) {
        contenido.append(llave).append('=').append(valor == null ? "" : valor.replace("\\", "\\\\")).append('\n');
    }

    static Connection conectarLocal(Datos datos) throws SQLException {
        return conectar(datos.hostLocal, datos.puertoLocal, datos.baseDatosLocal, datos.parametrosLocal, datos.usuarioLocal, datos.claveLocal);
    }

    static Connection conectarPagos(Datos datos) throws SQLException {
        return conectar(datos.hostPagos, datos.puertoPagos, datos.baseDatosPagos, datos.parametrosPagos, datos.usuarioPagos, datos.clavePagos);
    }

    private static Connection conectar(String host, String puerto, String baseDatos, String parametros, String usuario, String clave) throws SQLException {
        String hostValidado = host == null ? "" : host.trim();
        if (!HOST_VALIDO.matcher(hostValidado).matches()) {
            throw new IllegalStateException("Dirección de servidor inválida: " + host);
        }
        if (puerto == null || !puerto.trim().matches("\\d{1,5}")) {
            throw new IllegalStateException("Puerto inválido: " + puerto);
        }
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            throw new SQLException("No se encontró el driver de MySQL", ex);
        }
        String url = "jdbc:mysql://" + hostValidado + ":" + puerto.trim() + "/" + CredencialesBaseDatos.validarNombreBaseDatos(baseDatos)
                + (parametros == null || parametros.trim().isEmpty() ? "" : "?" + parametros.trim());
        Properties propiedades = new Properties();
        propiedades.setProperty("user", usuario == null ? "" : usuario.trim());
        propiedades.setProperty("password", clave == null ? "" : clave);
        propiedades.setProperty("allowLoadLocalInfile", "false");
        propiedades.setProperty("connectTimeout", "10000");
        return DriverManager.getConnection(url, propiedades);
    }

    private static String protegerCarpeta(Path carpeta) {
        try {
            Process proceso = new ProcessBuilder("icacls", carpeta.toString(), "/inheritance:r",
                    "/grant:r", "*S-1-5-32-544:(OI)(CI)F", "/grant:r", "*S-1-5-18:(OI)(CI)F", "/grant:r", "*S-1-5-32-545:(OI)(CI)RX")
                    .redirectErrorStream(true).start();
            StringBuilder salida = new StringBuilder();
            try (InputStream entrada = proceso.getInputStream()) {
                byte[] buffer = new byte[1024];
                int leidos;
                while ((leidos = entrada.read(buffer)) > 0) {
                    salida.append(new String(buffer, 0, leidos, StandardCharsets.ISO_8859_1));
                }
            }
            return proceso.waitFor() == 0 ? null : salida.toString().trim();
        } catch (IOException ex) {
            return ex.getMessage();
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            return ex.getMessage();
        }
    }

    private static String obtenerNombreEquipo() {
        String equipo = System.getenv("COMPUTERNAME");
        if (equipo == null || equipo.trim().isEmpty()) {
            equipo = System.getProperty("user.name", "");
        }
        return equipo.length() > 100 ? equipo.substring(0, 100) : equipo;
    }

    private static void requerido(String valor, String descripcion) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalStateException("Falta " + descripcion);
        }
    }
}
