package Vista.Configuraciones;

import clases.Instancias;
import configuracion.dlgAlertaPermiso;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;

public class hiloSolicitudesPermisos extends Thread {

    private static final Logger LOGGER = Logger.getLogger(hiloSolicitudesPermisos.class.getName());
    private static final int SEGUNDOS_ENTRE_CONSULTAS = 7;

    private final Instancias instancias = Instancias.getInstancias();
    private volatile boolean activo = true;

    public hiloSolicitudesPermisos() {
        super("solicitudes-permisos");
        setDaemon(true);
    }

    public void detener() {
        activo = false;
        interrupt();
    }

    @Override
    public void run() {
        int cantidadAnterior = -1;

        while (activo && !isInterrupted() && "ADMIN".equals(instancias.getUsuario())) {
            int pendientes = contarPendientes();

            if (pendientes >= 0) {
                if (cantidadAnterior >= 0 && pendientes > cantidadAnterior) {
                    mostrarAlertaNuevaSolicitud();
                }
                actualizarBuzon(pendientes);
                cantidadAnterior = pendientes;
            }

            esperar(SEGUNDOS_ENTRE_CONSULTAS);
        }
    }

    private int contarPendientes() {
        try {
            Object[][] pendientes = instancias.getSql().getPermisosPendientes(" where estado = 'PENDIENTE'; ");
            return pendientes == null ? 0 : pendientes.length;
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "No se pudieron consultar las solicitudes de permiso pendientes", e);
            return -1;
        }
    }

    private void actualizarBuzon(final int pendientes) {
        SwingUtilities.invokeLater(() -> {
            if (activo && instancias.getMenu() != null) {
                instancias.getMenu().actualizarPermisos(pendientes, pendientes > 0, true);
            }
        });
    }

    private void mostrarAlertaNuevaSolicitud() {
        try {
            SwingUtilities.invokeAndWait(() -> {
                if (!activo) {
                    return;
                }
                dlgAlertaPermiso alertaPermiso = new dlgAlertaPermiso(null, true);
                alertaPermiso.setLocationRelativeTo(null);
                alertaPermiso.setVisible(true);
            });
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (InvocationTargetException e) {
            LOGGER.log(Level.WARNING, "No se pudo mostrar la alerta de solicitud de permiso", e.getCause());
        }
    }

    private void esperar(int segundos) {
        try {
            Thread.sleep(segundos * 1000L);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}
