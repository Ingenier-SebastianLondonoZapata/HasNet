package Activacion;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

public class VistaActivacion extends JFrame {

    private final JRadioButton rdServidor = new JRadioButton("Servidor (la base de datos está en este equipo)");
    private final JRadioButton rdExtension = new JRadioButton("Extensión (se conecta al servidor)");
    private final JTextField txtIpServidor = new JTextField();
    private final JLabel lbPaquete = new JLabel(" ");
    private final JButton btnPaquete = new JButton("Seleccionar paquete...");
    private final JPasswordField txtClaveInstalacion = new JPasswordField();
    private final JTextField txtIdentificadorCliente = new JTextField();
    private final JLabel lbCliente = new JLabel(" ");
    private final JComboBox<String> cmbTerminal = new JComboBox<>(new String[]{ActivadorTerminal.NUEVA_TERMINAL});
    private final JCheckBox chkProteger = new JCheckBox("Restringir permisos de "
            + Utilidades.BaseDatos.CredencialesBaseDatos.rutaConfiguracionActual().getParent(), true);
    private final JTextArea txtEstado = new JTextArea(5, 40);
    private final JButton btnProbar = new JButton("Probar conexión");
    private final JButton btnActivar = new JButton("Activar terminal");

    private Path paquete;
    private final boolean prepararBaseDatos;

    public VistaActivacion() {
        this(null, false);
    }

    public VistaActivacion(Boolean esServidor, boolean prepararBaseDatos) {
        super("Activación de terminal HasNet");
        this.prepararBaseDatos = prepararBaseDatos;
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        construir();
        cargar(ActivadorTerminal.cargarExistente());
        if (esServidor != null) {
            rdServidor.setSelected(esServidor);
            rdExtension.setSelected(!esServidor);
            rdServidor.setEnabled(esServidor);
            rdExtension.setEnabled(!esServidor);
            txtIpServidor.setEnabled(!esServidor);
        }
        establecerPaquete(PaqueteInstalacion.buscar());
        pack();
        setMinimumSize(new Dimension(560, getHeight()));
        setLocationRelativeTo(null);
    }

    private void construir() {
        ButtonGroup grupoTipo = new ButtonGroup();
        grupoTipo.add(rdServidor);
        grupoTipo.add(rdExtension);
        rdServidor.setSelected(true);
        rdServidor.addActionListener(e -> txtIpServidor.setEnabled(false));
        rdExtension.addActionListener(e -> {
            txtIpServidor.setEnabled(true);
            txtIpServidor.requestFocus();
        });
        txtIpServidor.setEnabled(false);

        JPanel contenido = new JPanel(new GridBagLayout());
        contenido.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        int fila = 0;

        fila = seccion(contenido, fila, "Tipo de equipo");
        fila = completo(contenido, fila, rdServidor);
        fila = completo(contenido, fila, rdExtension);
        fila = campo(contenido, fila, "IP o nombre del servidor", txtIpServidor);

        fila = seccion(contenido, fila, "Autorización");
        fila = campo(contenido, fila, "Paquete de instalación", lbPaquete);
        fila = campo(contenido, fila, "", btnPaquete);
        fila = campo(contenido, fila, "Clave de instalación", txtClaveInstalacion);

        fila = seccion(contenido, fila, "Licencia y terminal");
        fila = campo(contenido, fila, "Identificador del cliente", txtIdentificadorCliente);
        fila = completo(contenido, fila, lbCliente);
        fila = campo(contenido, fila, "Terminal", cmbTerminal);
        fila = completo(contenido, fila, chkProteger);

        JPanel botones = new JPanel();
        botones.add(btnProbar);
        botones.add(btnActivar);
        fila = completo(contenido, fila, botones);

        txtEstado.setEditable(false);
        txtEstado.setLineWrap(true);
        txtEstado.setWrapStyleWord(true);
        GridBagConstraints restricciones = restricciones(0, fila);
        restricciones.gridwidth = 2;
        restricciones.fill = GridBagConstraints.BOTH;
        restricciones.weightx = 1;
        restricciones.weighty = 1;
        contenido.add(new JScrollPane(txtEstado), restricciones);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(contenido, BorderLayout.CENTER);

        btnPaquete.addActionListener(e -> seleccionarPaquete());
        btnProbar.addActionListener(e -> probar());
        btnActivar.addActionListener(e -> activar());
    }

    private int seccion(JPanel panel, int fila, String titulo) {
        JLabel etiqueta = new JLabel(titulo);
        etiqueta.setFont(etiqueta.getFont().deriveFont(Font.BOLD, 13f));
        GridBagConstraints restricciones = restricciones(0, fila);
        restricciones.gridwidth = 2;
        restricciones.insets = new Insets(fila == 0 ? 0 : 12, 0, 4, 0);
        panel.add(etiqueta, restricciones);
        return fila + 1;
    }

    private int campo(JPanel panel, int fila, String etiqueta, JComponent componente) {
        panel.add(new JLabel(etiqueta), restricciones(0, fila));
        GridBagConstraints restricciones = restricciones(1, fila);
        restricciones.fill = componente instanceof JButton ? GridBagConstraints.NONE : GridBagConstraints.HORIZONTAL;
        restricciones.weightx = 1;
        panel.add(componente, restricciones);
        return fila + 1;
    }

    private int completo(JPanel panel, int fila, JComponent componente) {
        GridBagConstraints restricciones = restricciones(0, fila);
        restricciones.gridwidth = 2;
        panel.add(componente, restricciones);
        return fila + 1;
    }

    private GridBagConstraints restricciones(int columna, int fila) {
        GridBagConstraints restricciones = new GridBagConstraints();
        restricciones.gridx = columna;
        restricciones.gridy = fila;
        restricciones.anchor = GridBagConstraints.WEST;
        restricciones.insets = new Insets(3, 0, 3, 8);
        return restricciones;
    }

    private void cargar(ActivadorTerminal.Datos datos) {
        boolean esServidor = "localhost".equalsIgnoreCase(datos.hostLocal) || "127.0.0.1".equals(datos.hostLocal);
        rdServidor.setSelected(esServidor);
        rdExtension.setSelected(!esServidor);
        txtIpServidor.setEnabled(!esServidor);
        txtIpServidor.setText(esServidor ? "" : datos.hostLocal);
        txtIdentificadorCliente.setText(datos.identificadorCliente);
        DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>(new String[]{ActivadorTerminal.NUEVA_TERMINAL});
        if (!datos.terminalActual.isEmpty()) {
            modelo.addElement(datos.terminalActual);
            modelo.setSelectedItem(datos.terminalActual);
            estado("Este equipo está activado como " + datos.terminalActual + ". Puede reasignarlo o crear una terminal nueva.");
        }
        cmbTerminal.setModel(modelo);
    }

    private void establecerPaquete(Path ruta) {
        paquete = ruta;
        lbPaquete.setText(ruta == null ? "No se encontró " + PaqueteInstalacion.NOMBRE_ARCHIVO : ruta.getFileName() + " (" + ruta.getParent() + ")");
        lbPaquete.setToolTipText(ruta == null ? null : ruta.toString());
    }

    private void seleccionarPaquete() {
        JFileChooser selector = new JFileChooser(paquete != null ? paquete.getParent().toFile() : null);
        selector.setFileFilter(new FileNameExtensionFilter("Paquete de instalación HasNet", "dat"));
        if (selector.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            establecerPaquete(selector.getSelectedFile().toPath());
        }
    }

    private ActivadorTerminal.Datos leerFormulario() throws java.io.IOException {
        if (paquete == null) {
            throw new IllegalStateException("No se encontró el paquete de instalación " + PaqueteInstalacion.NOMBRE_ARCHIVO
                    + ". Cópielo junto a HasNet.jar o selecciónelo.");
        }
        ActivadorTerminal.Datos datos = new ActivadorTerminal.Datos();
        char[] clave = txtClaveInstalacion.getPassword();
        try {
            PaqueteInstalacion.aplicar(paquete, clave, datos);
        } finally {
            Arrays.fill(clave, ' ');
        }
        datos.hostLocal = rdServidor.isSelected() ? "localhost" : txtIpServidor.getText().trim();
        if (datos.hostLocal.isEmpty()) {
            throw new IllegalStateException("Escriba la IP o el nombre del servidor");
        }
        datos.identificadorCliente = txtIdentificadorCliente.getText().trim();
        datos.terminal = String.valueOf(cmbTerminal.getSelectedItem());
        datos.protegerCarpeta = chkProteger.isSelected();
        return datos;
    }

    private void probar() {
        ejecutar("Probando conexiones...", () -> {
            ActivadorTerminal.Datos datos = leerFormulario();
            if (prepararBaseDatos && rdServidor.isSelected()) {
                PreparadorBaseDatos.preparar(datos);
            }
            return new Object[]{ActivadorTerminal.validarCliente(datos), ActivadorTerminal.listarTerminales(datos)};
        }, resultado -> {
            lbCliente.setText("Cliente: " + resultado[0]);
            Object seleccionada = cmbTerminal.getSelectedItem();
            DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>(new String[]{ActivadorTerminal.NUEVA_TERMINAL});
            @SuppressWarnings("unchecked")
            List<String> terminales = (List<String>) resultado[1];
            for (String terminal : terminales) {
                modelo.addElement(terminal);
            }
            if (seleccionada != null && terminales.contains(seleccionada.toString())) {
                modelo.setSelectedItem(seleccionada);
            }
            cmbTerminal.setModel(modelo);
            estado("Conexiones correctas. Terminales registradas: " + (terminales.isEmpty() ? "ninguna" : String.join(", ", terminales)));
        });
    }

    private void activar() {
        String terminal = String.valueOf(cmbTerminal.getSelectedItem());
        String accion = ActivadorTerminal.NUEVA_TERMINAL.equals(terminal)
                ? "registrar una terminal nueva" : "reasignar " + terminal + " a este equipo (el equipo anterior dejará de funcionar como " + terminal + ")";
        if (JOptionPane.showConfirmDialog(this, "¿Desea " + accion + "?", "Confirmar activación", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) {
            return;
        }
        ejecutar("Activando...", () -> {
            ActivadorTerminal.Datos datos = leerFormulario();
            List<String> preparacion = prepararBaseDatos && rdServidor.isSelected()
                    ? PreparadorBaseDatos.preparar(datos) : java.util.Collections.<String>emptyList();
            ActivadorTerminal.Resultado resultado = ActivadorTerminal.activar(datos);
            resultado.avisos.addAll(0, preparacion);
            return resultado;
        }, resultado -> {
            StringBuilder mensaje = new StringBuilder("Equipo activado como ").append(resultado.terminal)
                    .append("\nCliente: ").append(resultado.cliente);
            for (String aviso : resultado.avisos) {
                mensaje.append("\n- ").append(aviso);
            }
            estado(mensaje.toString());
            txtClaveInstalacion.setText("");
            cargar(ActivadorTerminal.cargarExistente());
            JOptionPane.showMessageDialog(this, mensaje.toString(), "Activación completada", JOptionPane.INFORMATION_MESSAGE);
        });
    }

    private <T> void ejecutar(String mensaje, Callable<T> tarea, Consumer<T> alTerminar) {
        estado(mensaje);
        btnProbar.setEnabled(false);
        btnActivar.setEnabled(false);
        new SwingWorker<T, Void>() {
            @Override
            protected T doInBackground() throws Exception {
                return tarea.call();
            }

            @Override
            protected void done() {
                btnProbar.setEnabled(true);
                btnActivar.setEnabled(true);
                try {
                    alTerminar.accept(get());
                } catch (java.util.concurrent.ExecutionException ex) {
                    error(ex.getCause());
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }
        }.execute();
    }

    private void estado(String texto) {
        txtEstado.setText(texto);
        txtEstado.setCaretPosition(0);
    }

    private void error(Throwable ex) {
        String mensaje = ex.getMessage() == null ? ex.toString() : ex.getMessage();
        estado("Error: " + mensaje);
        JOptionPane.showMessageDialog(this, mensaje, "No se pudo completar", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            UIManager.getLookAndFeel();
        }
        List<String> argumentos = Arrays.asList(args);
        Boolean esServidor = argumentos.contains("--servidor") ? Boolean.TRUE : argumentos.contains("--extension") ? Boolean.FALSE : null;
        boolean preparar = argumentos.contains("--preparar-bd");
        SwingUtilities.invokeLater(() -> new VistaActivacion(esServidor, preparar).setVisible(true));
    }
}
