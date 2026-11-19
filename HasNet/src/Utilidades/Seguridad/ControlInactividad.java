package Utilidades.Seguridad;

import clases.Instancias;
import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public final class ControlInactividad {

    private static final long LIMITE_INACTIVIDAD = TimeUnit.HOURS.toNanos(1);
    private static final long AVISO_PREVIO = TimeUnit.MINUTES.toNanos(1);
    private static final long LIMITE_SIN_RESPUESTA = TimeUnit.MINUTES.toNanos(2);
    private static final int INTERVALO_REVISION_MS = 1000;

    private static volatile long ultimaActividad = System.nanoTime();
    private static volatile long ultimoPulsoInterfaz = System.nanoTime();
    private static volatile boolean iniciado;
    private static Timer revision;
    private static JDialog aviso;
    private static JLabel lbCuentaRegresiva;

    private ControlInactividad() {
    }

    public static synchronized void iniciar() {
        if (iniciado) {
            registrarActividad();
            return;
        }
        iniciado = true;
        registrarActividad();
        Toolkit.getDefaultToolkit().addAWTEventListener(evento -> registrarActividad(),
                AWTEvent.KEY_EVENT_MASK | AWTEvent.MOUSE_EVENT_MASK | AWTEvent.MOUSE_MOTION_EVENT_MASK | AWTEvent.MOUSE_WHEEL_EVENT_MASK);
        revision = new Timer(INTERVALO_REVISION_MS, e -> revisar());
        revision.start();
    }

    public static boolean isInterfazRespondiendo() {
        return !iniciado || System.nanoTime() - ultimoPulsoInterfaz < LIMITE_SIN_RESPUESTA;
    }

    private static void registrarActividad() {
        ultimaActividad = System.nanoTime();
    }

    private static void revisar() {
        long ahora = System.nanoTime();
        ultimoPulsoInterfaz = ahora;

        if (Instancias.getInstancias().getProgres() != null) {
            registrarActividad();
        }

        long inactivo = ahora - ultimaActividad;
        if (inactivo >= LIMITE_INACTIVIDAD) {
            cerrarPorInactividad();
        } else if (inactivo >= LIMITE_INACTIVIDAD - AVISO_PREVIO) {
            mostrarAviso(TimeUnit.NANOSECONDS.toSeconds(LIMITE_INACTIVIDAD - inactivo) + 1);
        } else {
            ocultarAviso();
        }
    }

    private static void mostrarAviso(long segundos) {
        if (aviso == null) {
            aviso = new JDialog();
            aviso.setUndecorated(true);
            aviso.setAlwaysOnTop(true);
            aviso.setModal(false);

            JPanel contenido = new JPanel(new BorderLayout(10, 10));
            contenido.setBackground(Color.WHITE);
            contenido.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK),
                    BorderFactory.createEmptyBorder(20, 30, 20, 30)));
            JLabel titulo = new JLabel("El sistema se cerrará por inactividad", SwingConstants.CENTER);
            titulo.setFont(new Font("Arial", Font.BOLD, 24));
            lbCuentaRegresiva = new JLabel("", SwingConstants.CENTER);
            lbCuentaRegresiva.setFont(new Font("Arial", Font.PLAIN, 18));
            JButton btnContinuar = new JButton("CONTINUAR TRABAJANDO");
            btnContinuar.setBackground(new Color(131, 225, 71));
            btnContinuar.setFont(new Font("Arial", Font.PLAIN, 14));
            btnContinuar.addActionListener(e -> {
                registrarActividad();
                ocultarAviso();
            });
            contenido.add(titulo, BorderLayout.NORTH);
            contenido.add(lbCuentaRegresiva, BorderLayout.CENTER);
            contenido.add(btnContinuar, BorderLayout.SOUTH);
            aviso.setContentPane(contenido);
        }
        lbCuentaRegresiva.setText("Se cerrará en " + segundos + " segundos. Mueva el mouse o presione una tecla para continuar.");
        aviso.pack();
        if (!aviso.isVisible()) {
            aviso.setLocationRelativeTo(null);
            aviso.setVisible(true);
        }
    }

    private static void ocultarAviso() {
        if (aviso != null && aviso.isVisible()) {
            aviso.setVisible(false);
        }
    }

    private static void cerrarPorInactividad() {
        revision.stop();
        Logger.getLogger(ControlInactividad.class.getName()).log(Level.INFO, "Cierre del sistema por una hora de inactividad");
        try {
            if (Instancias.getInstancias().getUsuario() != null) {
                Instancias.getInstancias().getSql().usuarioActivo("OFF", "");
            }
        } catch (RuntimeException ex) {
            Logger.getLogger(ControlInactividad.class.getName()).log(Level.SEVERE, "No se pudo cerrar la sesión del usuario", ex);
        }
        ControlSesionesTerminal.liberar();
        System.exit(0);
    }
}
