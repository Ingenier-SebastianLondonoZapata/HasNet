package formularios;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.concurrent.atomic.AtomicReference;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;

public class esperandoRespuesta extends javax.swing.JDialog {

    public interface Tarea<T> {

        T ejecutar() throws Exception;
    }

    public esperandoRespuesta(String texto) {
        super((java.awt.Frame) null, true);
        initComponents();
        Utilidades.IconoAplicacion.aplicar(this);
        if (texto != null && !texto.trim().isEmpty()) {
            lbCargando.setTexto(texto.trim());
        }
        setBackground(new Color(0, 0, 0, 0));
        ((JComponent) getContentPane()).setOpaque(false);
        setAlwaysOnTop(true);
        pack();
        setLocationRelativeTo(null);
    }

    public static <T> T ejecutar(String texto, Tarea<T> tarea) throws Exception {
        if (!SwingUtilities.isEventDispatchThread()) {
            return ejecutarFueraDeSwing(texto, tarea);
        }

        AtomicReference<T> resultado = new AtomicReference<>();
        AtomicReference<Throwable> error = new AtomicReference<>();
        esperandoRespuesta ventana = new esperandoRespuesta(texto);
        ventana.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowOpened(java.awt.event.WindowEvent e) {
                Thread hilo = new Thread(() -> {
                    try {
                        resultado.set(tarea.ejecutar());
                    } catch (Throwable ex) {
                        error.set(ex);
                    } finally {
                        SwingUtilities.invokeLater(ventana::dispose);
                    }
                }, "espera-servicio");
                hilo.setDaemon(true);
                hilo.start();
            }
        });
        ventana.setVisible(true);
        return devolverResultado(resultado.get(), error.get());
    }

    private static <T> T ejecutarFueraDeSwing(String texto, Tarea<T> tarea) throws Exception {
        AtomicReference<esperandoRespuesta> ventana = new AtomicReference<>();
        EventQueue.invokeAndWait(() -> {
            esperandoRespuesta nueva = new esperandoRespuesta(texto);
            nueva.setModal(false);
            nueva.setVisible(true);
            ventana.set(nueva);
        });
        try {
            return tarea.ejecutar();
        } finally {
            SwingUtilities.invokeLater(() -> ventana.get().dispose());
        }
    }

    private static <T> T devolverResultado(T resultado, Throwable error) throws Exception {
        if (error instanceof Exception) {
            throw (Exception) error;
        }
        if (error instanceof Error) {
            throw (Error) error;
        }
        return resultado;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbLogo = new javax.swing.JLabel();
        lbCargando = new Vista.BarraProceso.TextoCargando();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setUndecorated(true);

        lbLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/aplicacion/cargarClick.gif"))); // NOI18N

        lbCargando.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbLogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbCargando, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lbLogo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbCargando, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Vista.BarraProceso.TextoCargando lbCargando;
    private javax.swing.JLabel lbLogo;
    // End of variables declaration//GEN-END:variables
}
