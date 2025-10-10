package formularios.terceros;

import clases.ImagePreviewPanel;
import clases.Instancias;
import clases.Medico.ndEpsPrecargados;
import clases.terceros.ndEmpleado;
import clases.big;
import clases.metodosGenerales;
import Modelo.Terceros.ModeloContacto;
import formularios.Medico.buscEpsPrecargadas;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.nio.file.CopyOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;

public class infEmpleados extends javax.swing.JInternalFrame {

    metodosGenerales metodos = new metodosGenerales();
    private Instancias instancias;
    private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension dimBarra = null;

    public infEmpleados() {

        initComponents();

        lbInfoObligatorios.setText(metodosGenerales.convertToMultiline("Los campos marcados (*) son obligatorios."));

        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
        dimBarra = Barra.getPreferredSize();
        Barra.setSize(0, 0);
        Barra.setPreferredSize(new Dimension(0, 0));
        setBorder(null);
        repaint();

        instancias = Instancias.getInstancias();

        Object[][] usuarios = instancias.getSql().getUsuarios();
        cmbUsuarios.addItem("");
        for (Object[] usuario : usuarios) {
            cmbUsuarios.addItem(usuario[0]);
        }

        String consecutivo = "";
        int num = Integer.parseInt(instancias.getSql().getNumConsecutivo("TERCERO")[0].toString());
        consecutivo = String.valueOf(num);
        for (int i = 0; i < 8 - String.valueOf(num).length(); i++) {
            consecutivo = "0" + consecutivo;
        }
        txtIdSistema.setText("TERC-" + consecutivo);
        pnlInvisible.setVisible(false);

        pnlFormulario.registerKeyboardAction(accion("guardar"), "guardar", KeyStroke.getKeyStroke(KeyEvent.VK_G, Event.CTRL_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
        pnlFormulario.registerKeyboardAction(accion("limpiar"), "limpiar", KeyStroke.getKeyStroke(KeyEvent.VK_L, Event.CTRL_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
        pnlFormulario.registerKeyboardAction(accion("terceros"), "terceros", KeyStroke.getKeyStroke(KeyEvent.VK_T, Event.CTRL_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
        pnlFormulario.registerKeyboardAction(accion("modificar"), "modificar", KeyStroke.getKeyStroke(KeyEvent.VK_M, Event.CTRL_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
    }

    private ActionListener accion(final String opc) {
        ActionListener a = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (opc) {
                    case "limpiar":
                        if ((btnNuevo.isEnabled()) && (btnNuevo.isVisible())) {
                            btnNuevoActionPerformed(null);
                        }
                        break;
                    case "guardar":
                        if ((btnGuardar.isEnabled()) && (btnGuardar.isVisible())) {
                            btnGuardarActionPerformed(null);
                        }
                        break;
                    case "terceros":
                        if ((btnBuscTerceros.isEnabled()) && (btnBuscTerceros.isVisible())) {
                            btnBuscTercerosActionPerformed(null);
                        }
                        break;
                    case "modificar":
                        if ((btnActualizar.isEnabled()) && (btnActualizar.isVisible())) {
                            btnActualizarActionPerformed(null);
                        }
                        break;
                }
            }
        };
        return a;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoAgendar = new javax.swing.ButtonGroup();
        scrFormulario = new javax.swing.JScrollPane();
        pnlFormulario = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        txtCelular = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        lbTelefono = new javax.swing.JLabel();
        lbCelular = new javax.swing.JLabel();
        lbRazon = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        lbFecha = new javax.swing.JLabel();
        lbDireccion = new javax.swing.JLabel();
        lbNit = new javax.swing.JLabel();
        lbEmail = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtDpto = new javax.swing.JTextField();
        txtNit = new javax.swing.JTextField();
        lbDepartamento = new javax.swing.JLabel();
        txtCiudad = new javax.swing.JTextField();
        lbCiudad = new javax.swing.JLabel();
        lbPlazo = new javax.swing.JLabel();
        lbCiudad1 = new javax.swing.JLabel();
        txtEps = new javax.swing.JTextField();
        lbCiudad2 = new javax.swing.JLabel();
        txtArl = new javax.swing.JTextField();
        lbCiudad3 = new javax.swing.JLabel();
        txtccf = new javax.swing.JTextField();
        dtFechaNacimiento = new com.toedter.calendar.JDateChooser();
        txtCargo = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        lbFecha1 = new javax.swing.JLabel();
        lbFecha2 = new javax.swing.JLabel();
        lbCupo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lbPlazo1 = new javax.swing.JLabel();
        rdSiAgendar = new javax.swing.JRadioButton();
        rdNoAgendar = new javax.swing.JRadioButton();
        txtSalario = new javax.swing.JTextField();
        cmbUsuarios = new javax.swing.JComboBox();
        jSeparator3 = new javax.swing.JSeparator();
        dtFechaIngreso = new com.toedter.calendar.JDateChooser();
        dtFechaRetiro = new com.toedter.calendar.JDateChooser();
        txtDigito = new javax.swing.JTextField();
        lbNit1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnNuevo = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnBuscTerceros = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        lbFoto = new javax.swing.JLabel();
        btnImagen = new javax.swing.JButton();
        lbInfoObligatorios = new javax.swing.JLabel();
        pnlInvisible = new javax.swing.JPanel();
        lbRazon2 = new javax.swing.JLabel();
        txtIdSistema = new javax.swing.JTextField();

        setTitle("Factura");

        pnlFormulario.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos personales", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 0, 14))); // NOI18N

        txtCelular.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtCelular.setName("Celular"); // NOI18N
        txtCelular.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCelularKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCelularKeyTyped(evt);
            }
        });

        txtTelefono.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtTelefono.setName("Teléfono"); // NOI18N
        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyTyped(evt);
            }
        });

        lbTelefono.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbTelefono.setText("Teléfono: *");

        lbCelular.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbCelular.setText("Celular:");

        lbRazon.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbRazon.setText("Nombre completo: *");
        lbRazon.setName(""); // NOI18N

        txtDireccion.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtDireccion.setName("Dirección"); // NOI18N
        txtDireccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDireccionActionPerformed(evt);
            }
        });
        txtDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDireccionKeyReleased(evt);
            }
        });

        lbFecha.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbFecha.setText("Fecha de nacimiento: ");

        lbDireccion.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion.setText("Dirección:");

        lbNit.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit.setText("CC/Nit: *");
        lbNit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lbNitKeyReleased(evt);
            }
        });

        lbEmail.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbEmail.setText("E-mail:");

        txtNombre.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtNombre.setName("Nombre completo"); // NOI18N
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreKeyReleased(evt);
            }
        });

        txtEmail.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtEmail.setName("E-mail"); // NOI18N
        txtEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEmailKeyReleased(evt);
            }
        });

        txtDpto.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtDpto.setName("Departamento"); // NOI18N
        txtDpto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDptoKeyReleased(evt);
            }
        });

        txtNit.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtNit.setName("CC/NIT"); // NOI18N
        txtNit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNitActionPerformed(evt);
            }
        });
        txtNit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNitKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNitKeyTyped(evt);
            }
        });

        lbDepartamento.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDepartamento.setText("Departamento:");

        txtCiudad.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtCiudad.setName("Ciudad"); // NOI18N
        txtCiudad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCiudadKeyReleased(evt);
            }
        });

        lbCiudad.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbCiudad.setText("Ciudad:");

        lbPlazo.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbPlazo.setText("Cargo: *");

        lbCiudad1.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbCiudad1.setText("EPS:");

        txtEps.setBackground(new java.awt.Color(255, 204, 204));
        txtEps.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtEps.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtEps.setName("Ciudad"); // NOI18N
        txtEps.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEpsKeyReleased(evt);
            }
        });

        lbCiudad2.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbCiudad2.setText("ARL:");

        txtArl.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtArl.setName("Ciudad"); // NOI18N
        txtArl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtArlKeyReleased(evt);
            }
        });

        lbCiudad3.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbCiudad3.setText("CCF:");

        txtccf.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtccf.setName("Ciudad"); // NOI18N
        txtccf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtccfKeyReleased(evt);
            }
        });

        dtFechaNacimiento.setDateFormatString("dd/MM/yyyy");
        dtFechaNacimiento.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        dtFechaNacimiento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                dtFechaNacimientoKeyReleased(evt);
            }
        });

        txtCargo.setBackground(new java.awt.Color(255, 204, 204));
        txtCargo.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtCargo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCargo.setName("Cargo"); // NOI18N
        txtCargo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCargoKeyReleased(evt);
            }
        });

        lbFecha1.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbFecha1.setText("Fecha de ingreso:");

        lbFecha2.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbFecha2.setText("Fecha de retiro: ");

        lbCupo.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbCupo.setText("Salario:");

        jLabel1.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel1.setText("Agendar:");

        lbPlazo1.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbPlazo1.setText("Usuario Asignado:");

        rdSiAgendar.setBackground(new java.awt.Color(255, 255, 255));
        grupoAgendar.add(rdSiAgendar);
        rdSiAgendar.setText("SI");

        rdNoAgendar.setBackground(new java.awt.Color(255, 255, 255));
        grupoAgendar.add(rdNoAgendar);
        rdNoAgendar.setSelected(true);
        rdNoAgendar.setText("NO");

        txtSalario.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtSalario.setName("Salario"); // NOI18N
        txtSalario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSalarioKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSalarioKeyTyped(evt);
            }
        });

        cmbUsuarios.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbUsuariosItemStateChanged(evt);
            }
        });
        cmbUsuarios.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cmbUsuariosFocusGained(evt);
            }
        });

        dtFechaIngreso.setDateFormatString("dd/MM/yyyy");
        dtFechaIngreso.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        dtFechaIngreso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                dtFechaIngresoKeyReleased(evt);
            }
        });

        dtFechaRetiro.setDateFormatString("dd/MM/yyyy");
        dtFechaRetiro.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        dtFechaRetiro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                dtFechaRetiroKeyReleased(evt);
            }
        });

        txtDigito.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtDigito.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDigito.setName("CC/NIT"); // NOI18N
        txtDigito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDigitoActionPerformed(evt);
            }
        });
        txtDigito.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDigitoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDigitoKeyTyped(evt);
            }
        });

        lbNit1.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit1.setText("-");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2)
                    .addComponent(jSeparator3)
                    .addComponent(jSeparator1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(lbCiudad3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtccf))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lbCiudad2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtArl))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lbCiudad1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtEps, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbCupo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbPlazo1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(rdSiAgendar)
                                .addGap(10, 10, 10)
                                .addComponent(rdNoAgendar))
                            .addComponent(txtSalario)
                            .addComponent(cmbUsuarios, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(lbRazon)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtNombre))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(lbNit)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtNit, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lbNit1, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(2, 2, 2)
                                    .addComponent(txtDigito, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(lbPlazo, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtCargo, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(lbEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lbDireccion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lbTelefono, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lbDepartamento, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(txtTelefono, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                                                .addComponent(txtDpto))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(lbCiudad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(lbCelular, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(txtCiudad, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                                                .addComponent(txtCelular)))
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtDireccion, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(lbFecha2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbFecha1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(dtFechaRetiro, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                                    .addComponent(dtFechaIngreso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(dtFechaNacimiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbNit, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(lbPlazo, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(txtDigito, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(lbNit1, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(txtCargo, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(txtNit, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNombre)
                    .addComponent(lbRazon, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbCiudad, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(lbDepartamento, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(txtDpto, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(txtCiudad, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbTelefono, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(lbCelular, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(txtCelular, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbDireccion, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(lbEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dtFechaNacimiento, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(lbFecha, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbFecha1, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(dtFechaIngreso, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbFecha2, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(dtFechaRetiro, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lbCiudad1, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                        .addComponent(txtEps, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rdSiAgendar, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rdNoAgendar, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbCupo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtSalario, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtArl, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(lbCiudad2, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lbPlazo1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(txtccf, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(cmbUsuarios, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(lbCiudad3, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        btnNuevo.setBackground(new java.awt.Color(204, 204, 204));
        btnNuevo.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/nuevo.png"))); // NOI18N
        btnNuevo.setText("NUEVO         ");
        btnNuevo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNuevo.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnNuevo.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnActualizar.setBackground(new java.awt.Color(93, 173, 226));
        btnActualizar.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/actualizar.png"))); // NOI18N
        btnActualizar.setText("MODIFICAR  ");
        btnActualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnActualizar.setEnabled(false);
        btnActualizar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnActualizar.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnEliminar.setBackground(new java.awt.Color(241, 148, 138));
        btnEliminar.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/borrar2.png"))); // NOI18N
        btnEliminar.setText("INACTIVAR     ");
        btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnEliminar.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnGuardar.setBackground(new java.awt.Color(46, 204, 113));
        btnGuardar.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
        btnGuardar.setText("GUARDAR       ");
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnGuardar.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnBuscTerceros.setBackground(new java.awt.Color(247, 220, 111));
        btnBuscTerceros.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnBuscTerceros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cliente.png"))); // NOI18N
        btnBuscTerceros.setText("LISTADO      ");
        btnBuscTerceros.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscTerceros.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnBuscTerceros.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnBuscTerceros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscTercerosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnGuardar)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(btnBuscTerceros, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNuevo)
                    .addComponent(btnGuardar))
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnActualizar)
                    .addComponent(btnBuscTerceros))
                .addGap(3, 3, 3)
                .addComponent(btnEliminar))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        lbFoto.setBackground(new java.awt.Color(204, 204, 204));
        lbFoto.setToolTipText("Esta herramienta solo recibe formatos JPG y PNG. Al montar la imagen recibe el tamaño de 250x250.");
        lbFoto.setMaximumSize(new java.awt.Dimension(200, 200));
        lbFoto.setMinimumSize(new java.awt.Dimension(200, 200));
        lbFoto.setName(""); // NOI18N
        lbFoto.setOpaque(true);
        lbFoto.setPreferredSize(new java.awt.Dimension(200, 200));
        lbFoto.setRequestFocusEnabled(false);

        btnImagen.setBackground(new java.awt.Color(204, 204, 204));
        btnImagen.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnImagen.setForeground(new java.awt.Color(255, 255, 255));
        btnImagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/webcam.png"))); // NOI18N
        btnImagen.setText("AGREGAR IMAGEN    ");
        btnImagen.setToolTipText("");
        btnImagen.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnImagen.setEnabled(false);
        btnImagen.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnImagen.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImagenActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(lbFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnImagen)
                .addContainerGap())
        );

        lbInfoObligatorios.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        lbInfoObligatorios.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbInfoObligatorios.setText("Los campos marcados (*) son obligatorios.");

        lbRazon2.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbRazon2.setText("CODIGO DEL SISTEMA:");
        lbRazon2.setName(""); // NOI18N

        txtIdSistema.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        txtIdSistema.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtIdSistema.setDisabledTextColor(new java.awt.Color(255, 51, 0));
        txtIdSistema.setEnabled(false);
        txtIdSistema.setName("Nombre completo"); // NOI18N
        txtIdSistema.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIdSistemaKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout pnlInvisibleLayout = new javax.swing.GroupLayout(pnlInvisible);
        pnlInvisible.setLayout(pnlInvisibleLayout);
        pnlInvisibleLayout.setHorizontalGroup(
            pnlInvisibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInvisibleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbRazon2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtIdSistema, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(438, 438, 438))
        );
        pnlInvisibleLayout.setVerticalGroup(
            pnlInvisibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInvisibleLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlInvisibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtIdSistema, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbRazon2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlFormularioLayout = new javax.swing.GroupLayout(pnlFormulario);
        pnlFormulario.setLayout(pnlFormularioLayout);
        pnlFormularioLayout.setHorizontalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlFormularioLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlInvisible, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlFormularioLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbInfoObligatorios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlFormularioLayout.setVerticalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlFormularioLayout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(lbInfoObligatorios, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlInvisible, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        scrFormulario.setViewportView(pnlFormulario);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(scrFormulario)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrFormulario, javax.swing.GroupLayout.DEFAULT_SIZE, 622, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNitKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNitKeyTyped

    }//GEN-LAST:event_txtNitKeyTyped

    private void txtNitKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNitKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            ModeloContacto nodo = instancias.getSql().getDatosTercero(txtNit.getText());
            if (nodo.getId() == null) {
                txtDigito.requestFocus();
            } else {
                cargarCliente(txtNit.getText());
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtDigito.requestFocus();
        }
    }//GEN-LAST:event_txtNitKeyReleased

    private void txtDptoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDptoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtCiudad.requestFocus();
        }
    }//GEN-LAST:event_txtDptoKeyReleased

    private void txtEmailKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtEps.requestFocus();
        }
    }//GEN-LAST:event_txtEmailKeyReleased

    private void txtNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtDpto.requestFocus();
        }
    }//GEN-LAST:event_txtNombreKeyReleased

    private void txtDireccionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtEmail.requestFocus();
        }
    }//GEN-LAST:event_txtDireccionKeyReleased

    private void txtDireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDireccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDireccionActionPerformed

    private void txtTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtTelefonoKeyTyped

    private void txtTelefonoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtCelular.requestFocus();
        }
    }//GEN-LAST:event_txtTelefonoKeyReleased

    private void txtSalarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSalarioKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtSalarioKeyTyped

    private void txtSalarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSalarioKeyReleased
        txtSalario.setText(big.setMoneda(big.getMoneda(txtSalario.getText())));
    }//GEN-LAST:event_txtSalarioKeyReleased

    private void txtCelularKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCelularKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtCelularKeyTyped

    private void txtCelularKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCelularKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtDireccion.requestFocus();
        }
    }//GEN-LAST:event_txtCelularKeyReleased

    private void txtCiudadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCiudadKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtTelefono.requestFocus();
        }
    }//GEN-LAST:event_txtCiudadKeyReleased

    private void txtNitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNitActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        lbFoto.setIcon(null);
        repaint();

        btnImagen.setEnabled(false);
        txtNit.setEditable(true);

        for (int x = 0; x < jPanel1.getComponentCount(); x++) {
            if (jPanel1.getComponent(x) instanceof JTextField) {
                JTextField textField = (JTextField) jPanel1.getComponent(x);
                textField.setText("");
            }
        }

        String consecutivo = "";
        int num = Integer.parseInt(instancias.getSql().getNumConsecutivo("TERCERO")[0].toString());
        consecutivo = String.valueOf(num);
        for (int i = 0; i < 8 - String.valueOf(num).length(); i++) {
            consecutivo = "0" + consecutivo;
        }
        txtIdSistema.setText("TERC-" + consecutivo);

        dtFechaNacimiento.setCalendar(null);
        dtFechaIngreso.setCalendar(null);
        dtFechaRetiro.setCalendar(null);

        cmbUsuarios.setSelectedIndex(0);
        txtNombre.requestFocus();
        btnActualizar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnGuardar.setEnabled(true);
        rdNoAgendar.setSelected(true);
        btnEliminar.setText("INACTIVAR     ");
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/borrar2.png")));
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        Object[] campos = {txtNit, txtNombre, txtCargo};
        String faltantes = metodos.camposVacios(campos);

//        Object[] campos2 = {txtTelefono, txtCelular, txtEmail, txtDpto, txtCiudad, txtSalario, dtFechaNacimiento, txtFechaIng, txtFechaRet};
//        String faltantes2 = metodos.camposVacios(campos2);
        if (!faltantes.equals("")) {
            metodos.msgAdvertencia(this, "No puede continuar, faltan los siguientes campos: " + faltantes);
            return;
        } else if (txtEmail.getText().equals("") && txtTelefono.getText().equals("") && txtCelular.getText().equals("")) {
            metodos.msgAdvertencia(this, "No puede continuar, ingrese almenos un medio de comunicación");
            txtTelefono.requestFocus();
            return;
        } else if (!metodosGenerales.emailEsCorrecto(txtEmail.getText()) && !txtEmail.getText().equals("")) {
            metodos.msgAdvertencia(this, "No puede continuar, ingrese un E-mail correcto");
            txtEmail.requestFocus();
            return;
        }
//        else if (!faltantes2.equals("")) {
//            metodos.msgAdvertencia(this, "Faltan los siguientes campos: " + faltantes2);
//        }

        if (metodos.msgPregunta(this, "¿Desea continuar?") == 0) {

            if (txtSalario.getText().equals("")) {
                txtSalario.setText("0");
            }

            ModeloContacto nodo = instancias.getSql().getDatosTercero(txtNit.getText());

            String fechaNacimiento = "";
            String fechaIngreso = "";
            String fechaRetiro = "";

            try {
                fechaIngreso = metodos.desdeDate(dtFechaIngreso.getCalendar());
            } catch (Exception e) {
                fechaIngreso = metodos.fechaConsulta(metodosGenerales.fecha());
            }

            try {
                fechaRetiro = metodos.desdeDate(dtFechaRetiro.getCalendar());
            } catch (Exception e) {
                fechaRetiro = metodos.fechaConsulta(metodosGenerales.fecha());
            }

            try {
                fechaNacimiento = metodos.desdeDate(dtFechaNacimiento.getCalendar());
            } catch (Exception e) {
                fechaNacimiento = metodos.fechaConsulta(metodosGenerales.fecha());
            }

            String digito = txtDigito.getText();
            if (digito.equals("")) {
                digito = "";
            } else {
                digito = "-" + digito;
            }

            String eps = txtEps.getText();
            if ("".equals(eps)) {
                eps = null;
            }

            Object[] vector = {txtIdSistema.getText(), txtNit.getText() + digito, txtNombre.getText(), txtTelefono.getText(), txtCelular.getText(), txtDireccion.getText(), txtCiudad.getText(),
                txtEmail.getText(), txtDpto.getText(), nodo.getFecha(), instancias.getUsuario(), "", txtNombre.getText(), "", "", "", "", "", "", "",
                false, "", big.getMoneda("0"), "", "COLOMBIA", "", "", "", "", "", "", "", fechaNacimiento, "", eps, instancias.getTerminal(), "",
                "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", txtCargo.getText(), "", "", "", "", big.getMoneda(txtSalario.getText()),
                fechaIngreso, fechaRetiro, rdSiAgendar.isSelected(), cmbUsuarios.getSelectedItem(), txtArl.getText(), txtccf.getText(), "EMPLEADO",
                "", false
            };

            nodo = metodos.llenarTercero(vector);

            if (!instancias.getSql().modificarTercero(nodo)) {
                metodos.msgError(this, "Hubo un problema al modificar el empleado");
                return;
            }

            if (instancias.getConfiguraciones().isVeterinaria()) {
                instancias.getPeluqueria().titulosTabla();
            }

            if (rdSiAgendar.isSelected()) {
                if (instancias.getConfiguraciones().isAgenda()) {
                    instancias.getAgendaConsulta().titulosTabla();
                }
            }

            metodos.msgExito(this, "Empleado modificado con éxito");

            if (cmbUsuarios.getSelectedIndex() != 0) {
                instancias.getMaestra().llenarVendedores(txtNombre.getText());
            } else {
                instancias.getMaestra().llenarVendedores("");
            }

            btnNuevoActionPerformed(evt);
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        if (btnEliminar.getText().equalsIgnoreCase("ACTIVAR       ")) {
            if (metodos.msgPregunta(this, "¿Activar este registro?") == 0) {
                instancias.getSql().activarCliente(txtNit.getText());
                metodos.msgExito(this, "Empleado activado con éxito");
                btnNuevoActionPerformed(evt);
                return;
            }
        }

        if (metodos.msgPregunta(this, "¿Inactivar este registro?") == 0) {
            instancias.getSql().eliminarCliente(txtNit.getText());
            metodos.msgAdvertencia(this, "Empleado inactivado con exito");
            btnNuevoActionPerformed(evt);
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        Object[] campos = {txtNit, txtNombre, txtCargo};
        String faltantes = metodos.camposVacios(campos);

        if (!faltantes.equals("")) {
            metodos.msgAdvertencia(this, "No puede continuar, faltan los siguientes campos: " + faltantes);
            return;
        } else if (txtEmail.getText().equals("") && txtTelefono.getText().equals("") && txtCelular.getText().equals("")) {
            metodos.msgAdvertencia(this, "No puede continuar, ingrese almenos un medio de comunicación: télefono, email o celular.");
            txtTelefono.requestFocus();
            return;
        } else if (!metodosGenerales.emailEsCorrecto(txtEmail.getText()) && !txtEmail.getText().equals("")) {
            metodos.msgAdvertencia(this, "No puede continuar, ingrese un E-mail correcto");
            txtEmail.requestFocus();
            return;
        }
//        else if (!faltantes2.equals("")) {
//            metodos.msgAdvertencia(this, "Faltan los siguientes campos: " + faltantes2);
//        }

        if (metodos.msgPregunta(this, "¿Desea continuar?") == 0) {

            String digito = txtDigito.getText();
            if (digito.equals("")) {
                digito = "";
            } else {
                digito = "-" + digito;
            }

            ModeloContacto nodo = instancias.getSql().getDatosTercero(txtNit.getText() + digito);

            if (nodo.getId() != null) {
                metodos.msgError(this, "El empleado ya existe");
                txtNit.requestFocus();
                return;
            }

            if (txtSalario.getText().equals("")) {
                txtSalario.setText("0");
            }

            String fechaNacimiento = "";
            String fechaIngreso = "";
            String fechaRetiro = "";

            try {
                fechaIngreso = metodos.desdeDate(dtFechaIngreso.getCalendar());
            } catch (Exception e) {
                fechaIngreso = metodos.fechaConsulta(metodosGenerales.fecha());
            }

            try {
                fechaRetiro = metodos.desdeDate(dtFechaRetiro.getCalendar());
            } catch (Exception e) {
                fechaRetiro = metodos.fechaConsulta(metodosGenerales.fecha());
            }

            try {
                fechaNacimiento = metodos.desdeDate(dtFechaNacimiento.getCalendar());
            } catch (Exception e) {
                fechaNacimiento = metodos.fechaConsulta(metodosGenerales.fecha());
            }

            String consecutivo = "";
            int num = Integer.parseInt(instancias.getSql().getNumConsecutivo("TERCERO")[0].toString());
            consecutivo = String.valueOf(num);
            for (int i = 0; i < 8 - String.valueOf(num).length(); i++) {
                consecutivo = "0" + consecutivo;
            }

            String eps = txtEps.getText();
            if ("".equals(eps)) {
                eps = null;
            }

            Object[] vector = {"TERC-" + consecutivo, txtNit.getText() + digito, txtNombre.getText(), txtTelefono.getText(), txtCelular.getText(), txtDireccion.getText(), txtCiudad.getText(),
                txtEmail.getText(), txtDpto.getText(), metodos.fechaConsulta(metodosGenerales.fecha()), instancias.getUsuario(), "", txtNombre.getText(), "", "", "", "", "", "", "",
                false, "", big.getMoneda("0"), "", "COLOMBIA", "", "", "", "", "", "", "", fechaNacimiento, "", eps, instancias.getTerminal(), "",
                "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", txtCargo.getText(), "", "", "", "", big.getMoneda(txtSalario.getText()),
                fechaIngreso, fechaRetiro, rdSiAgendar.isSelected(), cmbUsuarios.getSelectedItem(), txtArl.getText(), txtccf.getText(), "EMPLEADO",
                "", false
            };

            nodo = metodos.llenarTercero(vector);

            if (!instancias.getSql().agregarTercero(nodo)) {
                metodos.msgError(this, "Error al guardar el empleado");
                return;
            }

            if (!instancias.getSql().aumentarConsecutivo("TERCERO", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("TERCERO")[0]) + 1)) {
                metodos.msgError(null, "Hubo un problema al guardar en el consecutivo del tercero");
            }

            if (rdSiAgendar.isSelected()) {
                if (instancias.getConfiguraciones().isAgenda()) {
                    instancias.getAgendaConsulta().titulosTabla();
                }
            }

            if (instancias.getConfiguraciones().isVeterinaria()) {
                instancias.getPeluqueria().titulosTabla();
            }

            metodos.msgExito(this, "Empleado registrado con éxito");

            if (cmbUsuarios.getSelectedIndex() != 0) {
                instancias.getMaestra().llenarVendedores(txtNombre.getText());
            } else {
                instancias.getMaestra().llenarVendedores("");
            }

            btnNuevoActionPerformed(evt);
            lbNit.requestFocus();
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnBuscTercerosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscTercerosActionPerformed
        ventanaTerceros("");
    }//GEN-LAST:event_btnBuscTercerosActionPerformed

    private void cmbUsuariosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbUsuariosItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbUsuariosItemStateChanged

    private void cmbUsuariosFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbUsuariosFocusGained
        if (!instancias.getUsuario().equals("ADMIN")) {
            dtFechaIngreso.requestFocus();
            metodos.msgError(this, "No tiene permisos para abrir esta lista");
        }
    }//GEN-LAST:event_cmbUsuariosFocusGained

    private void txtEpsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEpsKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            cargarEps(txtEps.getText());
        } else {
            txtEps.setText("");
        }
    }//GEN-LAST:event_txtEpsKeyReleased

    private void txtArlKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtArlKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtccf.requestFocus();
        }
    }//GEN-LAST:event_txtArlKeyReleased

    private void txtccfKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtccfKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtccfKeyReleased

    private void dtFechaNacimientoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dtFechaNacimientoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtEps.requestFocus();
        }
    }//GEN-LAST:event_dtFechaNacimientoKeyReleased

    private void txtCargoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCargoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!txtCargo.getText().equals("")) {
                txtNombre.requestFocus();
            } else {
                ventanaCargos(txtCargo.getText());
            }
        }
    }//GEN-LAST:event_txtCargoKeyReleased

    private void dtFechaIngresoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dtFechaIngresoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_dtFechaIngresoKeyReleased

    private void dtFechaRetiroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dtFechaRetiroKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_dtFechaRetiroKeyReleased

    private void btnImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImagenActionPerformed
        JFileChooser chooser = new JFileChooser();
        ImagePreviewPanel preview = new ImagePreviewPanel();
        chooser.setAccessory(preview);
        chooser.addPropertyChangeListener(preview);

        FileNameExtensionFilter filtroImagen = new FileNameExtensionFilter("JPG, PNG & GIF", "jpg", "png", "gif");
        chooser.setFileFilter(filtroImagen);

        int respuesta = chooser.showSaveDialog(this);

        if (respuesta == JFileChooser.APPROVE_OPTION) {
            Path FROM = Paths.get(chooser.getSelectedFile().getAbsolutePath());

            String id = "";

            if (lbFoto.getToolTipText().equalsIgnoreCase("")) {
                id = Arrays.toString(instancias.getSql().getNumConsecutivo("IMG"));

            } else {
                id = lbFoto.getToolTipText();
            }

            Path TO = Paths.get(System.getProperty("user.dir") + "\\imagenes\\empleados\\" + txtIdSistema.getText() + ".jpg");

            CopyOption[] options = new CopyOption[]{
                StandardCopyOption.REPLACE_EXISTING,
                StandardCopyOption.COPY_ATTRIBUTES
            };

            try {
                metodos.montarImagenTerceros(FROM.toString(), TO.toString());
                ImageIcon fot = new ImageIcon(chooser.getSelectedFile().getAbsolutePath());
                Icon icono = new ImageIcon(fot.getImage().getScaledInstance(lbFoto.getWidth(), lbFoto.getHeight(), Image.SCALE_DEFAULT));
                lbFoto.setIcon(icono);
                this.repaint();
            } catch (Exception ex) {
                metodos.msgError(this, "Hubo un error al cargar el archivo");
            }
        }
    }//GEN-LAST:event_btnImagenActionPerformed

    private void txtIdSistemaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdSistemaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdSistemaKeyReleased

    private void txtDigitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDigitoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDigitoActionPerformed

    private void txtDigitoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDigitoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtCargo.requestFocus();
        }
    }//GEN-LAST:event_txtDigitoKeyReleased

    private void txtDigitoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDigitoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDigitoKeyTyped

    private void lbNitKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lbNitKeyReleased
        txtNit.requestFocus();
    }//GEN-LAST:event_lbNitKeyReleased

    public void cargarEps(String nit) {
        ndEpsPrecargados nodo = instancias.getSql().getDatosEpsPrecargados(nit);
        if (nodo.getId() != null) {
            txtArl.requestFocus();
            return;
        }
        ventanaEps(nit);
    }

    public void ventanaEps(String nit) {
        buscEpsPrecargadas buscar = new buscEpsPrecargadas(instancias.getMenu(), rootPaneCheckingEnabled);
        buscar.setLocationRelativeTo(null);
        instancias.setBuscEpsPre(buscar);
        instancias.setCampoActual(txtEps);
        txtEps.requestFocus();
        buscar.noEncontrado(nit);
        buscar.show();
    }

    public void ventanaCargos(String nit) {
        buscCargos buscar = new buscCargos(instancias.getMenu(), rootPaneCheckingEnabled);
        buscar.setLocationRelativeTo(null);
//        instancias.setBuscRazas(buscar);
        instancias.setCampoActual(txtCargo);
        txtCargo.requestFocus();
        buscar.setInstancia(instancias);
        buscar.noEncontrado(nit);
        buscar.show();
    }

    public void cargarCliente(String nit) {

        if (nit.equals("")) {
            metodos.msgAdvertencia(this, "Debe ingresar el documento");
        } else {

            ModeloContacto nodo = instancias.getSql().getDatosTercero(nit);

            if (nodo.getId() != null) {

                txtIdSistema.setText(nodo.getIdSistema());

                ImageIcon fot = new ImageIcon(System.getProperty("user.dir") + "\\imagenes\\empleados\\" + txtIdSistema.getText() + ".jpg");
                Icon icono = new ImageIcon(fot.getImage().getScaledInstance(lbFoto.getWidth(), lbFoto.getHeight(), Image.SCALE_DEFAULT));
                lbFoto.setIcon(icono);
                this.repaint();

                btnImagen.setEnabled(true);

                txtNit.setText(nodo.getId().split("-")[0]);
                try {
                    txtDigito.setText(nodo.getId().split("-")[1]);
                } catch (Exception e) {
                    txtDigito.setText("");
                }

                txtNombre.setText(nodo.getNombre());
                txtTelefono.setText(nodo.getTelefono());
                txtDireccion.setText(nodo.getDireccion());
                txtCelular.setText(nodo.getCelular());

                try {
                    txtEmail.setText(nodo.getEmail());
                } catch (Exception e) {
                }

                try {
                    txtDpto.setText(nodo.getDepartamento());
                } catch (Exception e) {
                }
                try {
                    txtCiudad.setText(nodo.getCiudad());
                } catch (Exception e) {
                }

                try {
                    dtFechaNacimiento.setCalendar(metodos.haciaDate(nodo.getNacimiento()));
                } catch (Exception e) {
                }

                try {
                    dtFechaIngreso.setCalendar(metodos.haciaDate(nodo.getIngreso()));
                } catch (Exception e) {
                }

                try {
                    dtFechaRetiro.setCalendar(metodos.haciaDate(nodo.getRetiro()));
                } catch (Exception e) {
                }

                try {
                    txtSalario.setText(big.setMoneda(big.getBigDecimal(nodo.getSalario())));
                } catch (Exception e) {
                }

                try {
                    txtCargo.setText(nodo.getCargo());
                } catch (Exception e) {
                }

                try {
                    txtEps.setText(nodo.getEps());
                } catch (Exception e) {
                }

                try {
                    txtArl.setText(nodo.getArl());
                } catch (Exception e) {
                }

                try {
                    txtccf.setText(nodo.getCcf());
                } catch (Exception e) {
                }

                try {
                    cmbUsuarios.setSelectedItem(nodo.getUsuarioAsociado());
                } catch (Exception e) {
                }

                if (nodo.isActivo()) {
                    btnEliminar.setText("ACTIVAR       ");
                    btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/agregar.png")));
                } else {
                    btnEliminar.setText("INACTIVAR     ");
                    btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/borrar2.png")));
                }

                if (nodo.isAgendar()) {
                    rdSiAgendar.setSelected(true);
                } else {
                    rdNoAgendar.setSelected(true);
                }

                txtNombre.requestFocus();
                btnActualizar.setEnabled(true);
                btnEliminar.setEnabled(true);
                btnGuardar.setEnabled(false);
                return;
            }
        }
    }

    public void ventanaTerceros(String nit) {
        buscEmpleados buscar = new buscEmpleados(instancias.getMenu(), rootPaneCheckingEnabled);
        buscar.setLocationRelativeTo(null);
        instancias.setBuscEmpleados(buscar);
        instancias.setCampoActual(txtNit);
        txtNit.requestFocus();
        buscar.noEncontrado(nit);
        buscar.show();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnBuscTerceros;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnImagen;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JComboBox cmbUsuarios;
    private com.toedter.calendar.JDateChooser dtFechaIngreso;
    private com.toedter.calendar.JDateChooser dtFechaNacimiento;
    private com.toedter.calendar.JDateChooser dtFechaRetiro;
    private javax.swing.ButtonGroup grupoAgendar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lbCelular;
    private javax.swing.JLabel lbCiudad;
    private javax.swing.JLabel lbCiudad1;
    private javax.swing.JLabel lbCiudad2;
    private javax.swing.JLabel lbCiudad3;
    private javax.swing.JLabel lbCupo;
    private javax.swing.JLabel lbDepartamento;
    private javax.swing.JLabel lbDireccion;
    private javax.swing.JLabel lbEmail;
    private javax.swing.JLabel lbFecha;
    private javax.swing.JLabel lbFecha1;
    private javax.swing.JLabel lbFecha2;
    private javax.swing.JLabel lbFoto;
    private javax.swing.JLabel lbInfoObligatorios;
    private javax.swing.JLabel lbNit;
    private javax.swing.JLabel lbNit1;
    private javax.swing.JLabel lbPlazo;
    private javax.swing.JLabel lbPlazo1;
    private javax.swing.JLabel lbRazon;
    private javax.swing.JLabel lbRazon2;
    private javax.swing.JLabel lbTelefono;
    private javax.swing.JPanel pnlFormulario;
    private javax.swing.JPanel pnlInvisible;
    private javax.swing.JRadioButton rdNoAgendar;
    private javax.swing.JRadioButton rdSiAgendar;
    private javax.swing.JScrollPane scrFormulario;
    private javax.swing.JTextField txtArl;
    private javax.swing.JTextField txtCargo;
    private javax.swing.JTextField txtCelular;
    private javax.swing.JTextField txtCiudad;
    private javax.swing.JTextField txtDigito;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtDpto;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtEps;
    private javax.swing.JTextField txtIdSistema;
    private javax.swing.JTextField txtNit;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtSalario;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtccf;
    // End of variables declaration//GEN-END:variables
}
