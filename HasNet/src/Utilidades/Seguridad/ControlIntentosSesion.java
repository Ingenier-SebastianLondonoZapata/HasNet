package Utilidades.Seguridad;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public final class ControlIntentosSesion {

    private static final int INTENTOS_PERMITIDOS = 5;
    private static final long MILISEGUNDOS_BLOQUEO = 5L * 60L * 1000L;

    private static final Map<String, Registro> REGISTROS = new HashMap<>();

    private ControlIntentosSesion() {
    }

    public static synchronized long minutosBloqueoRestantes(String usuario) {
        Registro registro = REGISTROS.get(clave(usuario));
        if (registro == null || registro.bloqueadoHasta == 0) {
            return 0;
        }
        long restante = registro.bloqueadoHasta - System.currentTimeMillis();
        if (restante <= 0) {
            REGISTROS.remove(clave(usuario));
            return 0;
        }
        return Math.max(1, (restante + 59_999L) / 60_000L);
    }

    public static synchronized int registrarFallo(String usuario) {
        Registro registro = REGISTROS.computeIfAbsent(clave(usuario), k -> new Registro());
        registro.fallos++;
        if (registro.fallos >= INTENTOS_PERMITIDOS) {
            registro.bloqueadoHasta = System.currentTimeMillis() + MILISEGUNDOS_BLOQUEO;
            registro.fallos = 0;
            return 0;
        }
        return INTENTOS_PERMITIDOS - registro.fallos;
    }

    public static synchronized void registrarExito(String usuario) {
        REGISTROS.remove(clave(usuario));
    }

    private static String clave(String usuario) {
        return usuario == null ? "" : usuario.trim().toUpperCase(Locale.ROOT);
    }

    private static final class Registro {

        private int fallos;
        private long bloqueadoHasta;
    }
}
