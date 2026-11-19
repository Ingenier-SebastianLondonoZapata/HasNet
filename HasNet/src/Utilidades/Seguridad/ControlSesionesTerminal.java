package Utilidades.Seguridad;

import Controlador.Alertas.ControladorAlertas;
import Utilidades.Constantes;
import clases.Instancias;
import java.sql.SQLException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;

public final class ControlSesionesTerminal {

    private static final int MINUTOS_SENAL = 1;
    private static final int MINUTOS_VENCIMIENTO = 3;

    private static final RepositorioSesionesTerminal repositorio = new RepositorioSesionesTerminal();
    private static ScheduledExecutorService senal;
    private static String idTerminalActiva;
    private static int terminalesPermitidas;
    private static boolean avisoMostrado;

    private ControlSesionesTerminal() {
    }

    public static synchronized String iniciar() {
        String idCliente = Constantes.leerIdentificadorCliente();
        String idTerminal = Constantes.obtenerIdTerminal();
        if (idCliente.isEmpty() || idTerminal.isEmpty()) {
            return "La terminal no está activada. Ejecute la herramienta de activación.";
        }
        int permitidas;
        try {
            permitidas = 1 + Instancias.getInstancias().getSqlPagos().obtenerTerminalesPagadas(idCliente);
        } catch (SQLException ex) {
            Logger.getLogger(ControlSesionesTerminal.class.getName()).log(Level.SEVERE, "No se pudo consultar las terminales pagadas", ex);
            return "No se pudo consultar el número de terminales pagadas. Verifique la conexión a internet.";
        }
        try {
            if (!repositorio.reservar(idTerminal, permitidas, MINUTOS_VENCIMIENTO)) {
                return "Ya hay " + permitidas + " terminal(es) abiertas, que es el máximo pagado este mes."
                        + " Cierre el sistema en otra terminal o comuníquese con el proveedor para pagar más terminales.";
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControlSesionesTerminal.class.getName()).log(Level.SEVERE, "No se pudo validar las terminales abiertas", ex);
            return "No se pudo validar el número de terminales abiertas. Verifique la conexión con el servidor.";
        }
        idTerminalActiva = idTerminal;
        terminalesPermitidas = permitidas;
        avisoMostrado = false;
        iniciarSenal();
        return null;
    }

    private static void iniciarSenal() {
        if (senal != null) {
            return;
        }
        senal = Executors.newSingleThreadScheduledExecutor(tarea -> {
            Thread hilo = new Thread(tarea, "senal-sesion-terminal");
            hilo.setDaemon(true);
            return hilo;
        });
        senal.scheduleWithFixedDelay(ControlSesionesTerminal::enviarSenal, MINUTOS_SENAL, MINUTOS_SENAL, TimeUnit.MINUTES);
        Runtime.getRuntime().addShutdownHook(new Thread(ControlSesionesTerminal::liberar, "liberar-sesion-terminal"));
    }

    private static void enviarSenal() {
        String idTerminal;
        int permitidas;
        synchronized (ControlSesionesTerminal.class) {
            idTerminal = idTerminalActiva;
            permitidas = terminalesPermitidas;
        }
        if (idTerminal == null || !ControlInactividad.isInterfazRespondiendo()) {
            return;
        }
        try {
            boolean vigente = repositorio.renovar(idTerminal, MINUTOS_VENCIMIENTO)
                    || repositorio.reservar(idTerminal, permitidas, MINUTOS_VENCIMIENTO);
            if (!vigente && !avisoMostrado) {
                avisoMostrado = true;
                SwingUtilities.invokeLater(() -> ControladorAlertas.alertFail(
                        "Se superó el número de terminales pagadas. Guarde su trabajo y cierre el sistema en esta terminal."));
            } else if (vigente) {
                avisoMostrado = false;
            }
        } catch (SQLException | RuntimeException ex) {
            Logger.getLogger(ControlSesionesTerminal.class.getName()).log(Level.WARNING, "No se pudo enviar la señal de la terminal", ex);
        }
    }

    public static void liberar() {
        String idTerminal;
        synchronized (ControlSesionesTerminal.class) {
            idTerminal = idTerminalActiva;
            idTerminalActiva = null;
        }
        if (idTerminal != null) {
            repositorio.liberar(idTerminal);
        }
    }
}
