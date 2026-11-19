package Vista.Configuraciones.Multiempresa;

import Controlador.Alertas.ControladorAlertas;
import Dao.Multiempresa.DaoEmpresas;
import Modelo.Multiempresa.Empresa;
import Modelo.Multiempresa.OpcionesCopiaEmpresa;
import Procesos.Multiempresa.CreadorEmpresa;
import clases.Instancias;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.WindowConstants;

public class DialogoEmpresa extends JDialog {

    private static final Font FUENTE = new Font("Arial", Font.PLAIN, 15);
    private static final String[] TIPOS_IDENTIFICACION = {"NIT", "CEDULA_CIUDADANIA", "CEDULA_EXTRANJERIA", "PASAPORTE"};

    private final Empresa empresa;
    private final JTextField txtNombre = new JTextField(28);
    private final JComboBox<String> cmbTipo = new JComboBox<>(TIPOS_IDENTIFICACION);
    private final JTextField txtNit = new JTextField(28);
    private final JCheckBox chkGrupos = new JCheckBox("Grupos y subgrupos");
    private final JCheckBox chkProductos = new JCheckBox("Productos (sin inventario, con el último costo)");
    private final JCheckBox chkContactos = new JCheckBox("Contactos (clientes, proveedores y empleados)");
    private final JCheckBox chkDisenos = new JCheckBox("Diseños de productos");
    private final JLabel lbProgreso = new JLabel(" ");
    private final JProgressBar barra = new JProgressBar();
    private final JButton btnGuardar = new JButton();
    private final JButton btnCancelar = new JButton("Cancelar");
    private boolean guardado;
    private boolean procesando;

    public DialogoEmpresa(Window propietario, Empresa empresa) {
        super(propietario, empresa == null ? "Nueva empresa" : "Modificar empresa", ModalityType.APPLICATION_MODAL);
        this.empresa = empresa;
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                if (!procesando) {
                    dispose();
                }
            }
        });
        construir();
        if (empresa != null) {
            txtNombre.setText(empresa.getNombre());
            txtNit.setText(empresa.getNit());
            if (empresa.getTipoIdentificacion() != null) {
                cmbTipo.setSelectedItem(empresa.getTipoIdentificacion());
            }
        }
        pack();
        setResizable(false);
        setLocationRelativeTo(propietario);
    }

    public boolean isGuardado() {
        return guardado;
    }

    private void construir() {
        JPanel contenido = new JPanel(new GridBagLayout());
        contenido.setBackground(Color.WHITE);
        contenido.setBorder(BorderFactory.createEmptyBorder(16, 20, 12, 20));
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.WEST;
        c.weightx = 1;

        agregar(contenido, c, etiqueta("Nombre de la empresa"), 0);
        agregar(contenido, c, estilo(txtNombre), 10);
        agregar(contenido, c, etiqueta("Tipo de identificación"), 0);
        agregar(contenido, c, estilo(cmbTipo), 10);
        agregar(contenido, c, etiqueta("Número de identificación (sin dígito de verificación)"), 0);
        agregar(contenido, c, estilo(txtNit), 14);

        if (empresa == null) {
            JLabel titulo = etiqueta("Información a copiar de la empresa actual");
            titulo.setFont(FUENTE.deriveFont(Font.BOLD));
            agregar(contenido, c, titulo, 4);
            for (JCheckBox opcion : new JCheckBox[]{chkGrupos, chkProductos, chkContactos, chkDisenos}) {
                opcion.setFont(FUENTE);
                opcion.setOpaque(false);
                agregar(contenido, c, opcion, 0);
            }
            chkProductos.addActionListener(e -> sincronizarOpciones());
            chkDisenos.addActionListener(e -> sincronizarOpciones());
            JLabel nota = etiqueta("Se crea una base de datos independiente con la configuración base, la bodega principal y sin resoluciones.");
            nota.setFont(new Font("Arial", Font.ITALIC, 12));
            nota.setForeground(new Color(110, 110, 110));
            c.insets = new Insets(10, 0, 4, 0);
            contenido.add(nota, c);
            barra.setIndeterminate(true);
            barra.setVisible(false);
            lbProgreso.setFont(new Font("Arial", Font.PLAIN, 13));
            agregar(contenido, c, lbProgreso, 2);
            agregar(contenido, c, barra, 0);
        }

        btnGuardar.setText(empresa == null ? "Crear empresa" : "Guardar cambios");
        btnGuardar.setFont(FUENTE);
        btnGuardar.setBackground(new Color(46, 204, 113));
        btnCancelar.setFont(FUENTE);
        btnGuardar.addActionListener(e -> guardar());
        btnCancelar.addActionListener(e -> dispose());

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        botones.setBackground(Color.WHITE);
        botones.setBorder(BorderFactory.createEmptyBorder(0, 20, 16, 20));
        botones.add(btnCancelar);
        botones.add(btnGuardar);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(contenido, BorderLayout.CENTER);
        getContentPane().add(botones, BorderLayout.SOUTH);
        getRootPane().setDefaultButton(btnGuardar);
    }

    private void agregar(JPanel panel, GridBagConstraints c, JComponent componente, int espacioInferior) {
        c.insets = new Insets(2, 0, espacioInferior, 0);
        panel.add(componente, c);
    }

    private JLabel etiqueta(String texto) {
        JLabel etiqueta = new JLabel(texto);
        etiqueta.setFont(new Font("Arial", Font.PLAIN, 14));
        return etiqueta;
    }

    private JComponent estilo(JComponent campo) {
        campo.setFont(FUENTE);
        campo.setPreferredSize(new Dimension(420, 32));
        return campo;
    }

    private void sincronizarOpciones() {
        if (chkDisenos.isSelected()) {
            chkProductos.setSelected(true);
        }
        if (chkProductos.isSelected()) {
            chkGrupos.setSelected(true);
        }
        chkGrupos.setEnabled(!chkProductos.isSelected());
        chkProductos.setEnabled(!chkDisenos.isSelected());
    }

    private boolean validar() {
        String nombre = txtNombre.getText().trim();
        String nit = txtNit.getText().trim();
        if (nombre.length() < 3) {
            ControladorAlertas.alert("Ingrese el nombre de la empresa (mínimo 3 caracteres)");
            txtNombre.requestFocus();
            return false;
        }
        if (nombre.length() > 100) {
            ControladorAlertas.alert("El nombre de la empresa no puede superar 100 caracteres");
            txtNombre.requestFocus();
            return false;
        }
        if (!nit.matches("[0-9A-Za-z-]{3,30}")) {
            ControladorAlertas.alert("Ingrese un número de identificación válido (solo números, letras o guion)");
            txtNit.requestFocus();
            return false;
        }
        DaoEmpresas dao = new DaoEmpresas();
        Empresa principal = dao.empresaPrincipal();
        for (Empresa otra : dao.listar(false)) {
            boolean misma = empresa != null && otra.getId().equals(empresa.getId());
            if (!misma && nit.equalsIgnoreCase(otra.getNit())) {
                ControladorAlertas.alert("Ya existe una empresa registrada con la identificación " + nit);
                return false;
            }
        }
        if (nit.equalsIgnoreCase(principal.getNit())) {
            ControladorAlertas.alert("La identificación coincide con la empresa principal");
            return false;
        }
        return true;
    }

    private Empresa capturar() {
        Empresa datos = new Empresa();
        datos.setNombre(txtNombre.getText().trim().toUpperCase());
        datos.setNit(txtNit.getText().trim());
        datos.setTipoIdentificacion((String) cmbTipo.getSelectedItem());
        return datos;
    }

    private void guardar() {
        if (procesando || !validar()) {
            return;
        }
        if (empresa != null) {
            Empresa datos = capturar();
            datos.setId(empresa.getId());
            datos.setBaseDatos(empresa.getBaseDatos());
            datos.setActiva(empresa.isActiva());
            if (new DaoEmpresas().actualizar(datos)) {
                ControladorAlertas.alertSuccess("Empresa modificada con éxito");
                guardado = true;
                dispose();
            } else {
                ControladorAlertas.alertFail("No se pudo modificar la empresa");
            }
            return;
        }
        if (!ControladorAlertas.option("Se creará la empresa " + txtNombre.getText().trim().toUpperCase() + " con una base de datos independiente. ¿Desea continuar?")) {
            return;
        }
        crear();
    }

    private void crear() {
        OpcionesCopiaEmpresa opciones = new OpcionesCopiaEmpresa();
        opciones.setGrupos(chkGrupos.isSelected());
        opciones.setProductos(chkProductos.isSelected());
        opciones.setContactos(chkContactos.isSelected());
        opciones.setDisenos(chkDisenos.isSelected());
        Empresa datos = capturar();
        String usuario = Instancias.getInstancias().getUsuario();

        procesando = true;
        habilitar(false);
        barra.setVisible(true);
        pack();

        new SwingWorker<Empresa, String>() {
            @Override
            protected Empresa doInBackground() throws Exception {
                return new CreadorEmpresa().crear(datos, opciones, usuario, this::publish);
            }

            @Override
            protected void process(List<String> mensajes) {
                lbProgreso.setText(mensajes.get(mensajes.size() - 1) + "...");
            }

            @Override
            protected void done() {
                procesando = false;
                barra.setVisible(false);
                try {
                    Empresa creada = get();
                    guardado = true;
                    ControladorAlertas.alertSuccess("Empresa " + creada.getNombre() + " creada con éxito. Asigne en \"Accesos de usuarios\" quién puede ingresar a ella.");
                    dispose();
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    terminarConError(ex);
                } catch (ExecutionException ex) {
                    terminarConError(ex.getCause() == null ? ex : ex.getCause());
                }
            }
        }.execute();
    }

    private void terminarConError(Throwable error) {
        Logger.getLogger(DialogoEmpresa.class.getName()).log(Level.SEVERE, "No se pudo crear la empresa", error);
        lbProgreso.setText(" ");
        habilitar(true);
        ControladorAlertas.alertFail("No se pudo crear la empresa. No se realizó ningún cambio. Detalle: " + error.getMessage());
    }

    private void habilitar(boolean habilitado) {
        for (JComponent componente : new JComponent[]{txtNombre, cmbTipo, txtNit, chkContactos, btnGuardar, btnCancelar}) {
            componente.setEnabled(habilitado);
        }
        chkDisenos.setEnabled(habilitado);
        if (habilitado) {
            sincronizarOpciones();
        } else {
            chkGrupos.setEnabled(false);
            chkProductos.setEnabled(false);
        }
    }
}
