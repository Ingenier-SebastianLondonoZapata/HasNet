package Utilidades.Seguridad;

import Dao.Configuraciones.DaoUsuarios;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class SesionUsuario {

    public static final int MINUTOS_VENCIMIENTO = 3;

    private static String usuarioActivo;
    private static String terminalActiva;
    private static boolean ganchoRegistrado;

    private SesionUsuario() {
    }

    public static synchronized boolean abrir(String usuario, String terminal) {
        if (!new DaoUsuarios().reservarSesion(usuario, terminal, MINUTOS_VENCIMIENTO)) {
            return false;
        }
        usuarioActivo = usuario;
        terminalActiva = terminal;
        if (!ganchoRegistrado) {
            Runtime.getRuntime().addShutdownHook(new Thread(SesionUsuario::cerrar, "cerrar-sesion-usuario"));
            ganchoRegistrado = true;
        }
        return true;
    }

    public static synchronized void cerrar() {
        if (usuarioActivo == null) {
            return;
        }
        try {
            new DaoUsuarios().liberarSesion(usuarioActivo, terminalActiva);
        } catch (RuntimeException ex) {
            Logger.getLogger(SesionUsuario.class.getName()).log(Level.SEVERE, "No se pudo cerrar la sesión del usuario " + usuarioActivo, ex);
        } finally {
            usuarioActivo = null;
            terminalActiva = null;
        }
    }
}
