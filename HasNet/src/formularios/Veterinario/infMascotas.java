package formularios.Veterinario;

import clases.ImagePreviewPanel;
import clases.Instancias;
import clases.Veterinario.ndHistoria;
import Modelo.Terceros.ModeloContacto;
import clases.metodosGenerales;
import clases.Veterinario.ndMascota;
import datechooser.beans.DateChooserCombo;
import formularios.productos.buscColores;
import formularios.terceros.buscClientes;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JTextField;

public class infMascotas extends javax.swing.JInternalFrame {

    metodosGenerales metodos = new metodosGenerales();
    Instancias instancias;

    private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension dimBarra = null;

    public infMascotas() {

        initComponents();
        lbInfoObligatorios1.setText(metodosGenerales.convertToMultiline("Los campos marcados (*) son obligatorios."));

        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
        dimBarra = Barra.getPreferredSize();
        Barra.setSize(0, 0);
        Barra.setPreferredSize(new Dimension(0, 0));
        setBorder(null);
        repaint();

        buttonGroup1.add(rdAve);
        buttonGroup1.add(rdPerro);
        buttonGroup1.add(rdGato);
        btnNuevoActionPerformed(null);
        txtIdTercero.setVisible(false);

        instancias = Instancias.getInstancias();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
        buttonGroup1 = new javax.swing.ButtonGroup();
        scrFormulario = new javax.swing.JScrollPane();
        pnlFormulario = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnBuscTerceros = new javax.swing.JButton();
        btnBuscTerceros1 = new javax.swing.JButton();
        btnHistoria = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        lbRazon1 = new javax.swing.JLabel();
        lbNit1 = new javax.swing.JLabel();
        txtRazon1 = new javax.swing.JTextField();
        txtNit1 = new javax.swing.JTextField();
        lbTelefono2 = new javax.swing.JLabel();
        lbCelular1 = new javax.swing.JLabel();
        txtCelular1 = new javax.swing.JTextField();
        txtTelefono1 = new javax.swing.JTextField();
        lbDireccion1 = new javax.swing.JLabel();
        txtDireccion1 = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        lbInfoObligatorios1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lbRazon = new javax.swing.JLabel();
        lbNit = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtCodigo = new javax.swing.JTextField();
        lbPerro = new javax.swing.JLabel();
        lbGato = new javax.swing.JLabel();
        lbAve = new javax.swing.JLabel();
        rdGato = new javax.swing.JRadioButton();
        rdAve = new javax.swing.JRadioButton();
        rdPerro = new javax.swing.JRadioButton();
        lbTelefono = new javax.swing.JLabel();
        lbCelular = new javax.swing.JLabel();
        cmbSexo = new javax.swing.JComboBox();
        lbCelular2 = new javax.swing.JLabel();
        lbDireccion2 = new javax.swing.JLabel();
        lbDireccion = new javax.swing.JLabel();
        txtEdad = new javax.swing.JTextField();
        lbDireccion3 = new javax.swing.JLabel();
        lbEmail = new javax.swing.JLabel();
        txtChip = new javax.swing.JTextField();
        txtFecha = new datechooser.beans.DateChooserCombo();
        txtAlimentacion = new javax.swing.JTextField();
        txtRaza = new javax.swing.JTextField();
        txtColor = new javax.swing.JTextField();
        txtTemperamento = new javax.swing.JTextField();
        lbDireccion4 = new javax.swing.JLabel();
        lbDireccion5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtObservaciones1 = new javax.swing.JTextArea();
        lbEmail1 = new javax.swing.JLabel();
        txtObservaciones = new javax.swing.JTextField();
        btnNuevo1 = new javax.swing.JButton();
        lbFoto = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        txtIdTercero = new javax.swing.JTextField();

        jRadioButtonMenuItem1.setSelected(true);
        jRadioButtonMenuItem1.setText("jRadioButtonMenuItem1");

        setTitle("Factura");

        pnlFormulario.setBackground(new java.awt.Color(255, 255, 255));
        pnlFormulario.setToolTipText("");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        btnNuevo.setBackground(new java.awt.Color(204, 204, 204));
        btnNuevo.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/nuevo.png"))); // NOI18N
        btnNuevo.setText("NUEVO    ");
        btnNuevo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNuevo.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnNuevo.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnGuardar.setBackground(new java.awt.Color(46, 204, 113));
        btnGuardar.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
        btnGuardar.setText("GUARDAR    ");
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnGuardar.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnActualizar.setBackground(new java.awt.Color(93, 173, 226));
        btnActualizar.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/actualizar.png"))); // NOI18N
        btnActualizar.setText("MODIFICAR");
        btnActualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnActualizar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnActualizar.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnBuscTerceros.setBackground(new java.awt.Color(230, 126, 34));
        btnBuscTerceros.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnBuscTerceros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/terceroIco.png"))); // NOI18N
        btnBuscTerceros.setText("CLIENTES   ");
        btnBuscTerceros.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscTerceros.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnBuscTerceros.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnBuscTerceros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscTercerosActionPerformed(evt);
            }
        });

        btnBuscTerceros1.setBackground(new java.awt.Color(153, 153, 255));
        btnBuscTerceros1.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnBuscTerceros1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/mascotas.png"))); // NOI18N
        btnBuscTerceros1.setText("MASCOTAS");
        btnBuscTerceros1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscTerceros1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnBuscTerceros1.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnBuscTerceros1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscTerceros1ActionPerformed(evt);
            }
        });

        btnHistoria.setBackground(new java.awt.Color(255, 255, 255));
        btnHistoria.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnHistoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/historia.png"))); // NOI18N
        btnHistoria.setText("HISTORIA    ");
        btnHistoria.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHistoria.setEnabled(false);
        btnHistoria.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnHistoria.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnHistoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHistoriaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(57, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnBuscTerceros1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBuscTerceros, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                    .addComponent(btnActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnHistoria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(58, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNuevo)
                    .addComponent(btnGuardar))
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnActualizar)
                    .addComponent(btnBuscTerceros1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnHistoria)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(btnBuscTerceros, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 18))); // NOI18N

        lbRazon1.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbRazon1.setText("Nombre:");

        lbNit1.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit1.setText("Identificación: *");

        txtRazon1.setEditable(false);
        txtRazon1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtRazon1.setName("El cliente no existe"); // NOI18N
        txtRazon1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtRazon1KeyReleased(evt);
            }
        });

        txtNit1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtNit1.setName("Nit"); // NOI18N
        txtNit1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNit1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNit1KeyTyped(evt);
            }
        });

        lbTelefono2.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTelefono2.setText("Teléfono:");

        lbCelular1.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbCelular1.setText("Celular:");

        txtCelular1.setEditable(false);
        txtCelular1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtCelular1.setName("Celular"); // NOI18N
        txtCelular1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCelular1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCelular1KeyTyped(evt);
            }
        });

        txtTelefono1.setEditable(false);
        txtTelefono1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtTelefono1.setName("Teléfono"); // NOI18N
        txtTelefono1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTelefono1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefono1KeyTyped(evt);
            }
        });

        lbDireccion1.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion1.setText("Dirección:");

        txtDireccion1.setEditable(false);
        txtDireccion1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtDireccion1.setName("Dirección"); // NOI18N
        txtDireccion1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDireccion1ActionPerformed(evt);
            }
        });
        txtDireccion1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDireccion1KeyReleased(evt);
            }
        });

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jSeparator2)
                        .addContainerGap())
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lbNit1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNit1)
                        .addGap(89, 89, 89))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbRazon1)
                            .addComponent(lbTelefono2)
                            .addComponent(lbDireccion1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDireccion1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtRazon1)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(txtTelefono1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lbCelular1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCelular1, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNit1)
                    .addComponent(lbNit1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbRazon1)
                    .addComponent(txtRazon1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbTelefono2)
                        .addComponent(txtTelefono1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCelular1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbCelular1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDireccion1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbDireccion1))
                .addContainerGap())
        );

        lbInfoObligatorios1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lbInfoObligatorios1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbInfoObligatorios1.setText("Los campos marcados (*) son obligatorios.");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mascota", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 18))); // NOI18N

        lbRazon.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbRazon.setText("Nombre: *");

        lbNit.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit.setText("Identificación:");

        txtNombre.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtNombre.setText("ZEUS");
        txtNombre.setName("Nombre"); // NOI18N
        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreKeyReleased(evt);
            }
        });

        txtCodigo.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtCodigo.setText("123-BRU");
        txtCodigo.setName("Codigo"); // NOI18N
        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodigoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoKeyTyped(evt);
            }
        });

        lbPerro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/perro.png"))); // NOI18N

        lbGato.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/gato.png"))); // NOI18N

        lbAve.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ave.png"))); // NOI18N

        rdGato.setBackground(new java.awt.Color(255, 255, 255));
        rdGato.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        rdGato.setText("Gato");
        rdGato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdGatoActionPerformed(evt);
            }
        });

        rdAve.setBackground(new java.awt.Color(255, 255, 255));
        rdAve.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        rdAve.setText("Ave");
        rdAve.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdAveActionPerformed(evt);
            }
        });

        rdPerro.setBackground(new java.awt.Color(255, 255, 255));
        rdPerro.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        rdPerro.setText("Perro");
        rdPerro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdPerroActionPerformed(evt);
            }
        });

        lbTelefono.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTelefono.setText("Raza:");

        lbCelular.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbCelular.setText("Sexo:");

        cmbSexo.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        cmbSexo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Macho", "Hembra" }));
        cmbSexo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbSexoKeyReleased(evt);
            }
        });

        lbCelular2.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbCelular2.setText("Color:");

        lbDireccion2.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbDireccion2.setText("F. de nacimiento: *");

        lbDireccion.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbDireccion.setText("Edad: *");

        txtEdad.setEditable(false);
        txtEdad.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtEdad.setText("2 AÑOS");
        txtEdad.setName("Edad"); // NOI18N
        txtEdad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEdadActionPerformed(evt);
            }
        });
        txtEdad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEdadKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEdadKeyTyped(evt);
            }
        });

        lbDireccion3.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion3.setText("Alimentación:");

        lbEmail.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbEmail.setText("Frecuencia:");

        txtChip.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtChip.setName("Chip"); // NOI18N
        txtChip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtChipActionPerformed(evt);
            }
        });
        txtChip.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtChipKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtChipKeyTyped(evt);
            }
        });

        try {
            txtFecha.setDefaultPeriods(new datechooser.model.multiple.PeriodSet());
        } catch (datechooser.model.exeptions.IncompatibleDataExeption e1) {
            e1.printStackTrace();
        }
        txtFecha.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 12));
        txtFecha.addCommitListener(new datechooser.events.CommitListener() {
            public void onCommit(datechooser.events.CommitEvent evt) {
                txtFechaOnCommit(evt);
            }
        });
        txtFecha.addSelectionChangedListener(new datechooser.events.SelectionChangedListener() {
            public void onSelectionChange(datechooser.events.SelectionChangedEvent evt) {
                txtFechaOnSelectionChange(evt);
            }
        });

        txtAlimentacion.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtAlimentacion.setName("Chip"); // NOI18N
        txtAlimentacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAlimentacionActionPerformed(evt);
            }
        });
        txtAlimentacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAlimentacionKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAlimentacionKeyTyped(evt);
            }
        });

        txtRaza.setBackground(new java.awt.Color(255, 204, 204));
        txtRaza.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtRaza.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtRaza.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtRazaKeyReleased(evt);
            }
        });

        txtColor.setBackground(new java.awt.Color(255, 204, 204));
        txtColor.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtColor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtColor.setName("Chip"); // NOI18N
        txtColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtColorActionPerformed(evt);
            }
        });
        txtColor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtColorKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtColorKeyTyped(evt);
            }
        });

        txtTemperamento.setBackground(new java.awt.Color(255, 204, 204));
        txtTemperamento.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtTemperamento.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTemperamento.setName("Chip"); // NOI18N
        txtTemperamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTemperamentoActionPerformed(evt);
            }
        });
        txtTemperamento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTemperamentoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTemperamentoKeyTyped(evt);
            }
        });

        lbDireccion4.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion4.setText("Temperamento:");

        lbDireccion5.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion5.setText("Observaciones:");

        txtObservaciones1.setColumns(20);
        txtObservaciones1.setRows(2);
        jScrollPane1.setViewportView(txtObservaciones1);

        lbEmail1.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbEmail1.setText("E.Reproductivo:");

        txtObservaciones.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        btnNuevo1.setBackground(new java.awt.Color(255, 255, 255));
        btnNuevo1.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnNuevo1.setForeground(new java.awt.Color(255, 255, 255));
        btnNuevo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cargarImagen.png"))); // NOI18N
        btnNuevo1.setToolTipText("Subir");
        btnNuevo1.setBorder(null);
        btnNuevo1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNuevo1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnNuevo1.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnNuevo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevo1ActionPerformed(evt);
            }
        });

        lbFoto.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lbFoto.setToolTipText("");
        lbFoto.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(153, 153, 0))); // NOI18N

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lbEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbDireccion5, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                                .addComponent(lbRazon, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lbDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lbTelefono, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbCelular2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtChip, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtRaza, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtEdad, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                                        .addComponent(txtNombre, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtColor, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addGap(14, 14, 14)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(lbDireccion2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(lbCelular, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGap(25, 25, 25)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                                .addComponent(cmbSexo, 0, 123, Short.MAX_VALUE)))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(lbEmail1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(lbDireccion4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                                                .addComponent(lbDireccion3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                    .addGap(19, 19, 19)
                                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(txtAlimentacion)
                                                        .addComponent(txtObservaciones)))
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                    .addGap(18, 18, 18)
                                                    .addComponent(txtTemperamento, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(0, 1, Short.MAX_VALUE))))))
                                .addComponent(jScrollPane1)))
                        .addComponent(jSeparator1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(rdGato, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbGato, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lbPerro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(rdPerro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lbAve, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(rdAve, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lbNit)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(26, 26, 26)
                        .addComponent(lbFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(btnNuevo1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbNit, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdGato)
                            .addComponent(rdPerro)
                            .addComponent(rdAve))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbGato, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbPerro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbAve, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(lbFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNuevo1))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbRazon)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbDireccion2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbCelular, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbSexo)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbDireccion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbDireccion4, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                        .addComponent(txtTemperamento, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                        .addComponent(lbTelefono, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
                    .addComponent(txtRaza, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtColor, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbDireccion3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAlimentacion, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbCelular2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtChip, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbEmail1, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                            .addComponent(txtObservaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
                    .addComponent(lbDireccion5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(11, 11, 11))
        );

        txtIdTercero.setEditable(false);
        txtIdTercero.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtIdTercero.setName("El cliente no existe"); // NOI18N
        txtIdTercero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIdTerceroKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout pnlFormularioLayout = new javax.swing.GroupLayout(pnlFormulario);
        pnlFormulario.setLayout(pnlFormularioLayout);
        pnlFormularioLayout.setHorizontalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlFormularioLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbInfoObligatorios1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(39, 39, 39))
                    .addGroup(pnlFormularioLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtIdTercero, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlFormularioLayout.setVerticalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlFormularioLayout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(lbInfoObligatorios1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIdTercero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        scrFormulario.setViewportView(pnlFormulario);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrFormulario)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(scrFormulario)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtChipKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtChipKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtChipKeyTyped

    private void txtChipKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtChipKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtChipKeyReleased

    private void txtChipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtChipActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtChipActionPerformed

    private void txtEdadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEdadKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEdadKeyTyped

    private void txtEdadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEdadKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEdadKeyReleased

    private void txtEdadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEdadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEdadActionPerformed

    private void cmbSexoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbSexoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtFecha.requestFocus();
        }
    }//GEN-LAST:event_cmbSexoKeyReleased

    private void btnNuevo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevo1ActionPerformed

        JFileChooser chooser = new JFileChooser();
        ImagePreviewPanel preview = new ImagePreviewPanel();
        chooser.setAccessory(preview);
        chooser.addPropertyChangeListener(preview);

        int respuesta = chooser.showSaveDialog(this);

        if (respuesta == JFileChooser.APPROVE_OPTION) {
            Path FROM = Paths.get(chooser.getSelectedFile().getAbsolutePath());
            String id = "";

            if (lbFoto.getToolTipText().equalsIgnoreCase("")) {
                id = Arrays.toString(instancias.getSql().getNumConsecutivo("IMG"));
                lbFoto.setToolTipText(id);

                if (!instancias.getSql().aumentarConsecutivo("IMG", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("IMG")[0]) + 1)) {
                    metodos.msgError(this, "Hubo un problema al guardar el codigo de la imagen");
                }
            } else {
                id = lbFoto.getToolTipText();
            }

//            Path TO = Paths.get("D:\\Dropbox\\imagenesClick\\IMG-" + id + ".jpg");
            Path TO = Paths.get(System.getProperty("user.dir") + "\\imagenes\\veterinaria\\mascotas\\IMG-" + id + ".jpg");

            //sobreescribir el fichero de destino, si existe, y copiar
            // los atributos, incluyendo los permisos rwx
            CopyOption[] options = new CopyOption[]{
                StandardCopyOption.REPLACE_EXISTING,
                StandardCopyOption.COPY_ATTRIBUTES
            };

            try {
                Files.copy(FROM, TO, options);

                ImageIcon fot = new ImageIcon(chooser.getSelectedFile().getAbsolutePath());
                Icon icono = new ImageIcon(fot.getImage().getScaledInstance(lbFoto.getWidth(), lbFoto.getHeight(), Image.SCALE_DEFAULT));
                lbFoto.setIcon(icono);
                this.repaint();

            } catch (Exception ex) {
            }

        }
    }//GEN-LAST:event_btnNuevo1ActionPerformed

    private void rdPerroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdPerroActionPerformed
        razas();
    }//GEN-LAST:event_rdPerroActionPerformed

    private void rdAveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdAveActionPerformed
        razas();
    }//GEN-LAST:event_rdAveActionPerformed

    private void rdGatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdGatoActionPerformed
        razas();
    }//GEN-LAST:event_rdGatoActionPerformed

    private void txtCodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyTyped

    }//GEN-LAST:event_txtCodigoKeyTyped

    private void txtCodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cargarMascota(txtCodigo.getText());
        } else {
            String codigo = txtCodigo.getText();
            for (int x = 0; x < jPanel1.getComponentCount(); x++) {
                if (jPanel1.getComponent(x) instanceof JTextField) {
                    JTextField textField = (JTextField) jPanel1.getComponent(x);
                    textField.setText("");
                }
            }
            txtCodigo.setText(codigo);
        }
    }//GEN-LAST:event_txtCodigoKeyReleased

    private void txtNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyReleased
        cargarCliente(txtNit1.getText());

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtFecha.requestFocus();
        }
    }//GEN-LAST:event_txtNombreKeyReleased

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void txtDireccion1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccion1KeyReleased

    }//GEN-LAST:event_txtDireccion1KeyReleased

    private void txtDireccion1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDireccion1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDireccion1ActionPerformed

    private void txtTelefono1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefono1KeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtTelefono1KeyTyped

    private void txtTelefono1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefono1KeyReleased

    }//GEN-LAST:event_txtTelefono1KeyReleased

    private void txtCelular1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCelular1KeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtCelular1KeyTyped

    private void txtCelular1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCelular1KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtFecha.requestFocus();
        }
    }//GEN-LAST:event_txtCelular1KeyReleased

    private void txtNit1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNit1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNit1KeyTyped

    private void txtNit1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNit1KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cargarCliente(txtNit1.getText());
        } else {
            String nit = txtNit1.getText();
            btnNuevoActionPerformed(null);
            txtNit1.setText(nit);
        }
    }//GEN-LAST:event_txtNit1KeyReleased

    private void txtRazon1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRazon1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRazon1KeyReleased

    private void btnBuscTerceros1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscTerceros1ActionPerformed
        ventanaMascotas(txtCodigo.getText());
    }//GEN-LAST:event_btnBuscTerceros1ActionPerformed

    private void btnBuscTercerosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscTercerosActionPerformed
        ventanaTerceros(txtNit1.getText());
    }//GEN-LAST:event_btnBuscTercerosActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        if (!rdGato.isSelected() && !rdAve.isSelected() && !rdPerro.isSelected()) {
            metodos.msgAdvertenciaAjustado(this, "Debe seleccionar la especie");
            return;
        }

        Object[] campos = {txtCodigo, txtNombre, txtNit1, txtRazon1};
        String faltantes = metodos.camposVacios(campos);

        if (!faltantes.equals("")) {
            metodos.msgAdvertencia(this, "No puede continuar, faltan los siguientes campos: " + faltantes);
            return;
        }

        if (metodos.msgPregunta(this, "¿Desea continuar?") == 0) {

            String especie = "";

            if (rdAve.isSelected()) {
                especie = "Ave";
            } else if (rdGato.isSelected()) {
                especie = "Gato";
            } else if (rdPerro.isSelected()) {
                especie = "Perro";
            }

            String mascota = txtCodigo.getText();

            Object[] vector = {mascota, txtIdTercero.getText(), txtNombre.getText(), especie, txtRaza.getText().toString(),
                cmbSexo.getSelectedItem().toString(), txtFecha.getText(), txtChip.getText(), txtColor.getText(),
                txtAlimentacion.getText(), txtObservaciones.getText(), lbFoto.getToolTipText(), txtTemperamento.getText(), txtObservaciones1.getText()};

            ndMascota nodo = metodos.llenarMascota(vector);

            instancias.getSql().modificarMascota(nodo);

            metodos.msgExito(this, "Mascota modificada con éxito");

            btnNuevoActionPerformed(evt);
            txtTemperamento.setText("");
            txtObservaciones1.setText("");

//            cargarMascota(mascota);
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (!rdGato.isSelected() && !rdAve.isSelected() && !rdPerro.isSelected()) {
            metodos.msgAdvertenciaAjustado(this, "Debe seleccionar la especie");
            return;
        }

        Object[] campos = {txtCodigo, txtNombre, txtEdad};
        String faltantes = metodos.camposVacios(campos);

        Object[] campos2 = {};
        String faltantes2 = metodos.camposVacios(campos2);

        if (txtRazon1.getText().equals("")) {
            metodos.msgAdvertencia(this, "No puede continuar, no ha ingresado el cliente ");
            return;
        }

        if (!faltantes.equals("")) {
            metodos.msgAdvertencia(this, "No puede continuar, faltan los siguientes campos: " + faltantes);
            return;
        } else if (!faltantes2.equals("")) {
            metodos.msgAdvertencia(this, "Faltan los siguientes campos: " + faltantes2);
        }

        if (metodos.msgPregunta(this, "¿Desea continuar?") == 0) {

            try {
                ndMascota nodo = instancias.getSql().getDatosMascota(txtCodigo.getText());

                if (nodo.getCliente() != null) {
                    metodos.msgError(this, "La mascota ya existe");
                    txtCodigo.requestFocus();
                    return;
                }

                String especie = "";

                if (rdAve.isSelected()) {
                    especie = "Ave";
                } else if (rdGato.isSelected()) {
                    especie = "Gato";
                } else if (rdPerro.isSelected()) {
                    especie = "Perro";
                }

                Object[] vector = {txtIdTercero.getText(), txtCodigo.getText(), txtNombre.getText(), especie, txtRaza.getText(),
                    cmbSexo.getSelectedItem().toString(), txtFecha.getText(), txtChip.getText(), txtColor.getText(),
                    txtAlimentacion.getText(), txtObservaciones.getText(), lbFoto.getToolTipText(), txtTemperamento.getText(), txtObservaciones1.getText()};

                nodo = metodos.llenarMascota(vector);

                if (!instancias.getSql().agregarMascota(nodo)) {
                    metodos.msgError(this, "Hubo un problema al guardar la mascota");
                    return;
                }

                Object[] vectorHist = {"HIST-" + instancias.getSql().getNumConsecutivo("HIST")[0], txtCodigo.getText(), "", "", "", "",
                    "", "", instancias.getUsuarioLog().getUsuario(), metodos.fechaConsulta(metodosGenerales.fecha())};

                ndHistoria nodoHist = metodos.llenarHistoria(vectorHist);

                if (!instancias.getSql().agregarHistoria(nodoHist)) {
                    metodos.msgError(this, "Hubo un problema al abrir la historia clinica");
                    return;
                }

                if (!instancias.getSql().aumentarConsecutivo("HIST", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("HIST")[0]) + 1)) {
                    metodos.msgError(this, "Hubo un problema al guardar en el consecutivo de las historias clinicas");
                }

                metodos.msgExito(this, "Mascota registrado con éxito");
                btnActualizar.setEnabled(true);
                btnGuardar.setEnabled(false);
                btnHistoria.setEnabled(true);
                txtCodigo.setEditable(false);

//                btnNuevoActionPerformed(evt);
            } catch (Exception e) {
                metodos.msgError(this, "Hubo un problema al guardar la mascota");
            }
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        txtCodigo.setEditable(true);

        for (int x = 0; x < jPanel1.getComponentCount(); x++) {
            if (jPanel1.getComponent(x) instanceof JTextField) {
                JTextField textField = (JTextField) jPanel1.getComponent(x);
                textField.setText("");
            }
        }

        for (int x = 0; x < jPanel4.getComponentCount(); x++) {
            if (jPanel4.getComponent(x) instanceof JTextField) {
                JTextField textField = (JTextField) jPanel4.getComponent(x);
                textField.setText("");
            }
        }

        cmbSexo.setSelectedIndex(0);
        txtTemperamento.setText("");
        txtObservaciones1.setText("");
        txtRaza.setText("");
        txtColor.setText("");
        txtIdTercero.setText("");
        txtChip.setText("");
        txtObservaciones.setText("");

        buttonGroup1.clearSelection();

        lbFoto.setIcon(null);
        this.repaint();
        razas();
        txtNit1.requestFocus();
        btnHistoria.setEnabled(false);
        btnActualizar.setEnabled(false);
        btnGuardar.setEnabled(true);

        try {
            instancias.getHistoria().limpiarPantalla();
            instancias.getHospitalizacion().limpiarPantalla();
        } catch (Exception e) {
        }

        txtFecha.setSelectedDate(metodos.haciaDate(metodos.fecha3(metodosGenerales.fecha())));
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void txtCodigoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoKeyPressed

    private void btnHistoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistoriaActionPerformed
        try {
//            if (instancias.getUsuarioLog().isHistoria()) {
            if (!txtCodigo.isEditable()) {
                instancias.getHistoria().setMascota(txtCodigo.getText());
                instancias.getHistoria().setInstancias(instancias);
                instancias.getHistoria().setSelected(true);
                instancias.getMenu().cambiarTitulo("HISTORIA DE " + txtNombre.getText().toUpperCase());
            } else {
                metodos.msgError(this, "Cargue primero una mascota");
            }
//            } else {
//                metodos.msgAdvertencia(null, "No tiene permisos para esta función");
//            }
        } catch (PropertyVetoException ex) {
            Logger.getLogger(infMascotas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnHistoriaActionPerformed

    private void txtFechaOnCommit(datechooser.events.CommitEvent evt) {//GEN-FIRST:event_txtFechaOnCommit
        calcularEdad();
    }//GEN-LAST:event_txtFechaOnCommit

    private void txtFechaOnSelectionChange(datechooser.events.SelectionChangedEvent evt) {//GEN-FIRST:event_txtFechaOnSelectionChange

    }//GEN-LAST:event_txtFechaOnSelectionChange

    private void txtRazaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRazaKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!txtRaza.getText().equals("")) {
                txtTemperamento.requestFocus();
            } else {
                ventanaRazas(txtRaza.getText());
            }
        } else {
            txtRaza.setText("");
        }
    }//GEN-LAST:event_txtRazaKeyReleased

    private void txtColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtColorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtColorActionPerformed

    private void txtColorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtColorKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!txtColor.getText().equals("")) {
                btnNuevo1.requestFocus();
            } else {
                ventanaColores(txtColor.getText());
                txtAlimentacion.requestFocus();
            }
        } else {
            txtColor.setText("");
        }
    }//GEN-LAST:event_txtColorKeyReleased

    private void txtColorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtColorKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtColorKeyTyped

    private void txtTemperamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTemperamentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTemperamentoActionPerformed

    private void txtTemperamentoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTemperamentoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!txtTemperamento.getText().equals("")) {
                btnNuevo1.requestFocus();
            } else {
                ventanaTemperamentos(txtTemperamento.getText());
            }
        } else {
            txtTemperamento.setText("");
        }
    }//GEN-LAST:event_txtTemperamentoKeyReleased

    private void txtTemperamentoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTemperamentoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTemperamentoKeyTyped

    private void txtAlimentacionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAlimentacionKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAlimentacionKeyTyped

    private void txtAlimentacionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAlimentacionKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAlimentacionKeyReleased

    private void txtAlimentacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAlimentacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAlimentacionActionPerformed

    private void txtIdTerceroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdTerceroKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdTerceroKeyReleased

    public void calcularEdad() {
        int anho = metodosGenerales.calcularEdad(txtFecha.getText());
        int meses = metodosGenerales.calcularEdadMeses(txtFecha.getText());
        if (anho > 0) {
            txtEdad.setText(anho + " años y " + meses + " meses");
        } else {
            txtEdad.setText(meses + " meses");
        }
    }

    public void ventanaRazas(String nit) {
        String tipo = "";
        if (rdPerro.isSelected()) {
            tipo = "Perro";
        } else if (rdGato.isSelected()) {
            tipo = "Gato";
        } else if (rdAve.isSelected()) {
            tipo = "Ave";
        }

        if (tipo.equals("")) {
            metodos.msgAdvertencia(this, "Seleccione el tipo de mascota");
            return;
        }

        buscRazas buscar = new buscRazas(instancias.getMenu(), rootPaneCheckingEnabled, tipo);
        buscar.setLocationRelativeTo(null);
        instancias.setBuscRazas(buscar);
        instancias.setCampoActual(txtRaza);
        txtRaza.requestFocus();
        buscar.setInstancia(instancias);
        buscar.noEncontrado(nit);
        buscar.show();
    }

    public void ventanaColores(String nit) {
        buscColores buscar = new buscColores(instancias.getMenu(), rootPaneCheckingEnabled);
        buscar.setLocationRelativeTo(null);
        instancias.setBuscColores(buscar);
        instancias.setCampoActual(txtColor);
        txtColor.requestFocus();
        buscar.setInstancia(instancias);
        buscar.noEncontrado(nit);
        buscar.show();
    }

    public void ventanaTemperamentos(String nit) {
        buscTemperamento buscar = new buscTemperamento(instancias.getMenu(), rootPaneCheckingEnabled);
        buscar.setLocationRelativeTo(null);
        instancias.setBuscTemperamento(buscar);
        instancias.setCampoActual(txtTemperamento);
        txtTemperamento.requestFocus();
        buscar.setInstancia(instancias);
        buscar.noEncontrado(nit);
        buscar.show();
    }

    public void grabarVacuna(DateChooserCombo campo, Object datos[]) {

        if (!campo.getText().equalsIgnoreCase("")) {
            if (!instancias.getSql().agregarVacuna(datos)) {
                metodos.msgError(this, "Hubo un problema al guardar el concepto");
                return;
            }
        }

    }

    public void setInstancias(Instancias instancias) {
        this.instancias = instancias;
    }

    public void razas() {
//        if (rdAve.isSelected()) {
//            lbAve.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ave.png")));
//        } else {
//            lbAve.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ave.jpg")));
//        }
//
//        if (rdGato.isSelected()) {
//            lbGato.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/gato.png")));
//        } else {
//            lbGato.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/gato.jpg")));
//        }
//
//        if (rdPerro.isSelected()) {
//            lbPerro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/perro.png")));
//        } else {
//            lbPerro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/perro.jpg")));
//        }
    }

    public void cargarCliente(String nit) {

        ModeloContacto nodo = instancias.getSql().getDatosTercero(nit);

        if (nodo.getId() != null) {

            String nombre = "";

            try {
                nombre = txtNombre.getText().substring(0, 3);
            } catch (IndexOutOfBoundsException e) {
                nombre = txtNombre.getText();
            }

            if (txtCodigo.isEditable()) {
                txtCodigo.setText(nodo.getId() + "-" + nombre);
            }

            txtNit1.setText(nodo.getId());
            txtIdTercero.setText(nodo.getIdSistema());
            txtRazon1.setText(nodo.getNombre());
            txtTelefono1.setText(nodo.getTelefono());
            txtDireccion1.setText(nodo.getDireccion());
            txtCelular1.setText(nodo.getCelular());
            txtNombre.requestFocus();
            return;
        }
        ventanaTerceros(nit);
    }

    public void cargarMascota(String nit) {

        ndMascota nodo = instancias.getSql().getDatosMascota(nit);
        ModeloContacto cliente = instancias.getSql().getDatosTercero(nodo.getCliente());
        if (nodo.getCodigo() != null) {
            txtNombre.setText(cliente.getNombre());
            txtCodigo.setEditable(false);
            txtNit1.setText(nodo.getCliente());
            cargarCliente(nodo.getCliente());
            txtNombre.setText(nodo.getNombre());
            txtRaza.setText(nodo.getRaza());

            String fecha = nodo.getEdad();
            if (fecha.length() == 9) {
                fecha = "0" + fecha;
            }
            txtFecha.setSelectedDate(metodos.haciaDate2(fecha));

            txtChip.setText(nodo.getRestricciones());
            cmbSexo.setSelectedItem(nodo.getGenero());
            txtColor.setText(nodo.getColor());
            txtAlimentacion.setText(nodo.getAlimentacion());
            txtObservaciones.setText(nodo.getObservaciones());
            txtObservaciones1.setText(nodo.getObservacion());
            txtTemperamento.setText(nodo.getTemperamento());

//            ImageIcon fot = new ImageIcon("D:\\Dropbox\\imagenesClick\\IMG-" + nodo.getFoto() + ".jpg");
            ImageIcon fot = new ImageIcon(Paths.get(System.getProperty("user.dir") + "\\imagenes\\veterinaria\\mascotas\\IMG-" + nodo.getFoto() + ".jpg").toString());
            Icon icono = new ImageIcon(fot.getImage().getScaledInstance(lbFoto.getWidth(), lbFoto.getHeight(), Image.SCALE_DEFAULT));
            lbFoto.setIcon(icono);
            this.repaint();

            switch (nodo.getExpecie()) {
                case "Perro":
                    rdPerro.setSelected(true);
                    break;
                case "Ave":
                    rdAve.setSelected(true);
                    break;
                case "Gato":
                    rdGato.setSelected(true);
                    break;
            }

            txtCodigo.setText(nit);

            razas();

            btnActualizar.setEnabled(true);
            btnGuardar.setEnabled(false);
            btnHistoria.setEnabled(true);
            calcularEdad();
            return;
        }

        ventanaMascotas(nit);

    }

    public void ventanaTerceros(String nit) {
        buscClientes buscar = new buscClientes(instancias.getMenu(), true, false, null, "");
        buscar.setOpc("mascota");
        buscar.setLocationRelativeTo(null);
        instancias.setBusClientes(buscar);
        instancias.setCampoActual(txtNit1);
        txtNit1.requestFocus();
        buscar.noEncontrado(nit);
        buscar.show();
    }

    public void vacunas(JComboBox combo, String id) {

        Object[][] fechas = instancias.getSql().getVacunaciones(txtCodigo.getText(), id);
        Object[] datos = new Object[fechas.length];
        int j = fechas.length - 1;

        for (int i = 0; i < fechas.length; i++) {
            datos[j--] = fechas[i][1].toString();
        }

        combo.setModel(new javax.swing.DefaultComboBoxModel(datos));

    }

    public void ventanaMascotas(String nit) {
        buscMascotas buscar = new buscMascotas(instancias.getMenu(), rootPaneCheckingEnabled, "", txtNit1.getText());
        buscar.setLocationRelativeTo(null);
        instancias.setBuscMascotas(buscar);
        instancias.setCampoActual(txtCodigo);
        txtCodigo.requestFocus();
        buscar.setInstancia(instancias);
        buscar.noEncontrado(nit);
        buscar.show();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnBuscTerceros;
    private javax.swing.JButton btnBuscTerceros1;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnHistoria;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnNuevo1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cmbSexo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lbAve;
    private javax.swing.JLabel lbCelular;
    private javax.swing.JLabel lbCelular1;
    private javax.swing.JLabel lbCelular2;
    private javax.swing.JLabel lbDireccion;
    private javax.swing.JLabel lbDireccion1;
    private javax.swing.JLabel lbDireccion2;
    private javax.swing.JLabel lbDireccion3;
    private javax.swing.JLabel lbDireccion4;
    private javax.swing.JLabel lbDireccion5;
    private javax.swing.JLabel lbEmail;
    private javax.swing.JLabel lbEmail1;
    private javax.swing.JLabel lbFoto;
    private javax.swing.JLabel lbGato;
    private javax.swing.JLabel lbInfoObligatorios1;
    private javax.swing.JLabel lbNit;
    private javax.swing.JLabel lbNit1;
    private javax.swing.JLabel lbPerro;
    private javax.swing.JLabel lbRazon;
    private javax.swing.JLabel lbRazon1;
    private javax.swing.JLabel lbTelefono;
    private javax.swing.JLabel lbTelefono2;
    private javax.swing.JPanel pnlFormulario;
    private javax.swing.JRadioButton rdAve;
    private javax.swing.JRadioButton rdGato;
    private javax.swing.JRadioButton rdPerro;
    private javax.swing.JScrollPane scrFormulario;
    private javax.swing.JTextField txtAlimentacion;
    private javax.swing.JTextField txtCelular1;
    private javax.swing.JTextField txtChip;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtColor;
    private javax.swing.JTextField txtDireccion1;
    private javax.swing.JTextField txtEdad;
    private datechooser.beans.DateChooserCombo txtFecha;
    private javax.swing.JTextField txtIdTercero;
    private javax.swing.JTextField txtNit1;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtObservaciones;
    private javax.swing.JTextArea txtObservaciones1;
    private javax.swing.JTextField txtRaza;
    private javax.swing.JTextField txtRazon1;
    private javax.swing.JTextField txtTelefono1;
    private javax.swing.JTextField txtTemperamento;
    // End of variables declaration//GEN-END:variables
}
