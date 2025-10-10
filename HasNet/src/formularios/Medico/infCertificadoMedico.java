package formularios.Medico;

import clases.Instancias;
import clases.Medico.ndCertificadoMedico;
import clases.Medico.ndHistoriaClinica;
import clases.metodosGenerales;
import Modelo.Terceros.ModeloContacto;
import formularios.terceros.buscClientes;
import java.awt.Dimension;
import java.awt.event.KeyEvent;

public class infCertificadoMedico extends javax.swing.JPanel {

    metodosGenerales metodos = new metodosGenerales();
    private Instancias instancias;
    ndHistoriaClinica historia;
    private Dimension dimBarra = null;

    public infCertificadoMedico() {
        initComponents();

        txtIdSistema.setVisible(false);
        instancias = Instancias.getInstancias();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoTipo = new javax.swing.ButtonGroup();
        buttonGroup1 = new javax.swing.ButtonGroup();
        scrFormulario = new javax.swing.JScrollPane();
        jPanel7 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lblTipoId = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtNombreCompleto = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtEstadoCivil = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtFechaNacimiento = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtEdad = new javax.swing.JTextField();
        txtId = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtlugar = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        btnBuscTerceros = new javax.swing.JButton();
        txtIdSistema = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        btnReimprimir = new javax.swing.JButton();
        btnGuardar1 = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        lbDireccion40 = new javax.swing.JLabel();
        jScrollPane27 = new javax.swing.JScrollPane();
        txtHabitos = new javax.swing.JTextArea();
        jScrollPane39 = new javax.swing.JScrollPane();
        txtPatologicos = new javax.swing.JTextArea();
        lbDireccion17 = new javax.swing.JLabel();
        lbDireccion61 = new javax.swing.JLabel();
        jScrollPane41 = new javax.swing.JScrollPane();
        txtQuirurgicos = new javax.swing.JTextArea();
        lbDireccion62 = new javax.swing.JLabel();
        jScrollPane42 = new javax.swing.JScrollPane();
        txtAlergicos = new javax.swing.JTextArea();
        lbDireccion64 = new javax.swing.JLabel();
        jScrollPane44 = new javax.swing.JScrollPane();
        txtTraumaticos = new javax.swing.JTextArea();
        lbDireccion65 = new javax.swing.JLabel();
        jScrollPane45 = new javax.swing.JScrollPane();
        txtGinecoobstetricos = new javax.swing.JTextArea();
        jScrollPane46 = new javax.swing.JScrollPane();
        txtFamiliares = new javax.swing.JTextArea();
        lbDireccion66 = new javax.swing.JLabel();
        lbDireccion41 = new javax.swing.JLabel();
        jScrollPane25 = new javax.swing.JScrollPane();
        txtInspeccion = new javax.swing.JTextArea();
        lbDireccion42 = new javax.swing.JLabel();
        jScrollPane28 = new javax.swing.JScrollPane();
        txtImpresionDiagnostica = new javax.swing.JTextArea();
        jPanel8 = new javax.swing.JPanel();
        lbDireccion16 = new javax.swing.JLabel();
        lbDireccion18 = new javax.swing.JLabel();
        lbDireccion22 = new javax.swing.JLabel();
        lbDireccion24 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        txtExtremidades = new javax.swing.JTextArea();
        lbDireccion30 = new javax.swing.JLabel();
        txtimc = new javax.swing.JTextField();
        txtFr = new javax.swing.JTextField();
        lbDireccion19 = new javax.swing.JLabel();
        lbDireccion21 = new javax.swing.JLabel();
        txttemp = new javax.swing.JTextField();
        txtPeso = new javax.swing.JTextField();
        txtPa = new javax.swing.JTextField();
        lbDireccion28 = new javax.swing.JLabel();
        txtEstatura = new javax.swing.JTextField();
        txtAbdominal = new javax.swing.JTextField();
        txtFc = new javax.swing.JTextField();
        lbDireccion29 = new javax.swing.JLabel();
        lbDireccion31 = new javax.swing.JLabel();
        lbDireccion32 = new javax.swing.JLabel();
        lbDireccion33 = new javax.swing.JLabel();
        jScrollPane16 = new javax.swing.JScrollPane();
        txtVisual = new javax.swing.JTextArea();
        jScrollPane56 = new javax.swing.JScrollPane();
        txtCardiopulmonar = new javax.swing.JTextArea();
        jScrollPane57 = new javax.swing.JScrollPane();
        txtAbdomen = new javax.swing.JTextArea();
        jScrollPane58 = new javax.swing.JScrollPane();
        txtPiel = new javax.swing.JTextArea();
        lbDireccion34 = new javax.swing.JLabel();
        lbDireccion35 = new javax.swing.JLabel();
        jScrollPane17 = new javax.swing.JScrollPane();
        txtOjos = new javax.swing.JTextArea();
        jScrollPane18 = new javax.swing.JScrollPane();
        txtCabeza = new javax.swing.JTextArea();
        jScrollPane24 = new javax.swing.JScrollPane();
        txtOidos = new javax.swing.JTextArea();
        lbDireccion37 = new javax.swing.JLabel();
        jScrollPane26 = new javax.swing.JScrollPane();
        txtNariz = new javax.swing.JTextArea();
        lbDireccion38 = new javax.swing.JLabel();
        lbDireccion39 = new javax.swing.JLabel();
        jScrollPane34 = new javax.swing.JScrollPane();
        txtBoca = new javax.swing.JTextArea();
        jScrollPane35 = new javax.swing.JScrollPane();
        txtCuello = new javax.swing.JTextArea();
        lbDireccion53 = new javax.swing.JLabel();
        jScrollPane36 = new javax.swing.JScrollPane();
        txtTorax = new javax.swing.JTextArea();
        lbDireccion54 = new javax.swing.JLabel();
        jScrollPane37 = new javax.swing.JScrollPane();
        txtGenito = new javax.swing.JTextArea();
        lbDireccion55 = new javax.swing.JLabel();
        jScrollPane38 = new javax.swing.JScrollPane();
        txtAno = new javax.swing.JTextArea();
        lbDireccion57 = new javax.swing.JLabel();
        lbDireccion58 = new javax.swing.JLabel();
        lbDireccion59 = new javax.swing.JLabel();
        jScrollPane40 = new javax.swing.JScrollPane();
        txtNeurologico = new javax.swing.JTextArea();
        lbDireccion60 = new javax.swing.JLabel();
        jScrollPane60 = new javax.swing.JScrollPane();
        txtOsteoMuscular = new javax.swing.JTextArea();
        lbDireccion25 = new javax.swing.JLabel();
        jScrollPane61 = new javax.swing.JScrollPane();
        txtMuscular = new javax.swing.JTextArea();

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "INFORMACIÓN PERSONAL", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 0, 12))); // NOI18N

        lblTipoId.setBackground(new java.awt.Color(255, 255, 255));
        lblTipoId.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lblTipoId.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTipoId.setText("Documento");
        lblTipoId.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblTipoId.setOpaque(true);

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("Nombre");
        jLabel5.setOpaque(true);

        txtNombreCompleto.setEditable(false);
        txtNombreCompleto.setBackground(new java.awt.Color(255, 255, 255));
        txtNombreCompleto.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtNombreCompleto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreCompletoActionPerformed(evt);
            }
        });

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel8.setText("Estado Civil");
        jLabel8.setOpaque(true);

        txtEstadoCivil.setEditable(false);
        txtEstadoCivil.setBackground(new java.awt.Color(255, 255, 255));
        txtEstadoCivil.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtEstadoCivil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEstadoCivilActionPerformed(evt);
            }
        });

        jLabel12.setBackground(new java.awt.Color(255, 255, 255));
        jLabel12.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel12.setText("F. Nacimiento");
        jLabel12.setOpaque(true);

        txtFechaNacimiento.setEditable(false);
        txtFechaNacimiento.setBackground(new java.awt.Color(255, 255, 255));
        txtFechaNacimiento.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtFechaNacimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaNacimientoActionPerformed(evt);
            }
        });

        jLabel14.setBackground(new java.awt.Color(255, 255, 255));
        jLabel14.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel14.setText("Edad");
        jLabel14.setOpaque(true);

        txtEdad.setEditable(false);
        txtEdad.setBackground(new java.awt.Color(255, 255, 255));
        txtEdad.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtEdad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEdadActionPerformed(evt);
            }
        });

        txtId.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtId.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdActionPerformed(evt);
            }
        });
        txtId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIdKeyReleased(evt);
            }
        });

        jLabel15.setBackground(new java.awt.Color(255, 255, 255));
        jLabel15.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel15.setText("Lugar");
        jLabel15.setOpaque(true);

        txtlugar.setEditable(false);
        txtlugar.setBackground(new java.awt.Color(255, 255, 255));
        txtlugar.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtlugar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtlugarActionPerformed(evt);
            }
        });

        jLabel16.setBackground(new java.awt.Color(255, 255, 255));
        jLabel16.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel16.setText("Dirección");
        jLabel16.setOpaque(true);

        txtDireccion.setEditable(false);
        txtDireccion.setBackground(new java.awt.Color(255, 255, 255));
        txtDireccion.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtDireccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDireccionActionPerformed(evt);
            }
        });

        jLabel18.setBackground(new java.awt.Color(255, 255, 255));
        jLabel18.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel18.setText("Teléfono");
        jLabel18.setOpaque(true);

        txtTelefono.setEditable(false);
        txtTelefono.setBackground(new java.awt.Color(255, 255, 255));
        txtTelefono.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtTelefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelefonoActionPerformed(evt);
            }
        });

        btnBuscTerceros.setBackground(new java.awt.Color(204, 204, 204));
        btnBuscTerceros.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnBuscTerceros.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscTerceros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar1.png"))); // NOI18N
        btnBuscTerceros.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnBuscTerceros.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscTerceros.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnBuscTerceros.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnBuscTerceros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscTercerosActionPerformed(evt);
            }
        });

        txtIdSistema.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtIdSistema.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtIdSistema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdSistemaActionPerformed(evt);
            }
        });
        txtIdSistema.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIdSistemaKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(340, 340, 340)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtFechaNacimiento, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                            .addComponent(txtEstadoCivil))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtIdSistema, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtlugar, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(157, 157, 157))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblTipoId, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(txtId)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBuscTerceros, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(564, 564, 564))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtDireccion, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(txtNombreCompleto, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblTipoId, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(txtId, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(btnBuscTerceros, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombreCompleto, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtlugar, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEstadoCivil, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdSistema, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
                .addGap(3, 3, 3))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        btnReimprimir.setBackground(new java.awt.Color(247, 220, 111));
        btnReimprimir.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnReimprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/imprimir.png"))); // NOI18N
        btnReimprimir.setText("REIMPRIMIR");
        btnReimprimir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReimprimir.setEnabled(false);
        btnReimprimir.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnReimprimir.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnReimprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReimprimirActionPerformed(evt);
            }
        });

        btnGuardar1.setBackground(new java.awt.Color(46, 204, 113));
        btnGuardar1.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnGuardar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
        btnGuardar1.setText("GUARDAR      ");
        btnGuardar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnGuardar1.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnGuardar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardar1ActionPerformed(evt);
            }
        });

        btnNuevo.setBackground(new java.awt.Color(204, 204, 204));
        btnNuevo.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/nuevo.png"))); // NOI18N
        btnNuevo.setText("NUEVO      ");
        btnNuevo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNuevo.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnNuevo.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnNuevo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardar1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnReimprimir)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnReimprimir)
                    .addComponent(btnGuardar1)
                    .addComponent(btnNuevo))
                .addGap(3, 3, 3))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        lbDireccion40.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion40.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbDireccion40.setText("HABITOS");

        txtHabitos.setColumns(20);
        txtHabitos.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtHabitos.setRows(2);
        txtHabitos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtHabitosKeyReleased(evt);
            }
        });
        jScrollPane27.setViewportView(txtHabitos);

        txtPatologicos.setColumns(20);
        txtPatologicos.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtPatologicos.setLineWrap(true);
        txtPatologicos.setRows(2);
        txtPatologicos.setAutoscrolls(false);
        txtPatologicos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPatologicosKeyReleased(evt);
            }
        });
        jScrollPane39.setViewportView(txtPatologicos);

        lbDireccion17.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbDireccion17.setText("PATOLOGICOS");

        lbDireccion61.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion61.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbDireccion61.setText("QUIRURGICOS");

        txtQuirurgicos.setColumns(20);
        txtQuirurgicos.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtQuirurgicos.setLineWrap(true);
        txtQuirurgicos.setRows(2);
        txtQuirurgicos.setAutoscrolls(false);
        txtQuirurgicos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtQuirurgicosKeyReleased(evt);
            }
        });
        jScrollPane41.setViewportView(txtQuirurgicos);

        lbDireccion62.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion62.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbDireccion62.setText("ALERGICOS");

        txtAlergicos.setColumns(20);
        txtAlergicos.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtAlergicos.setLineWrap(true);
        txtAlergicos.setRows(2);
        txtAlergicos.setAutoscrolls(false);
        txtAlergicos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAlergicosKeyReleased(evt);
            }
        });
        jScrollPane42.setViewportView(txtAlergicos);

        lbDireccion64.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion64.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbDireccion64.setText("TRAUMATICOS");

        txtTraumaticos.setColumns(20);
        txtTraumaticos.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtTraumaticos.setLineWrap(true);
        txtTraumaticos.setRows(2);
        txtTraumaticos.setAutoscrolls(false);
        txtTraumaticos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTraumaticosKeyReleased(evt);
            }
        });
        jScrollPane44.setViewportView(txtTraumaticos);

        lbDireccion65.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion65.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbDireccion65.setText("GINECO-OBSTETRICOS");

        txtGinecoobstetricos.setColumns(20);
        txtGinecoobstetricos.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtGinecoobstetricos.setRows(2);
        txtGinecoobstetricos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtGinecoobstetricosKeyReleased(evt);
            }
        });
        jScrollPane45.setViewportView(txtGinecoobstetricos);

        txtFamiliares.setColumns(20);
        txtFamiliares.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtFamiliares.setLineWrap(true);
        txtFamiliares.setRows(2);
        txtFamiliares.setAutoscrolls(false);
        txtFamiliares.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFamiliaresKeyReleased(evt);
            }
        });
        jScrollPane46.setViewportView(txtFamiliares);

        lbDireccion66.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion66.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbDireccion66.setText("FAMILIARES");

        lbDireccion41.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion41.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbDireccion41.setText("ANTECEDENTES LABORALES");

        txtInspeccion.setColumns(20);
        txtInspeccion.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtInspeccion.setLineWrap(true);
        txtInspeccion.setRows(2);
        txtInspeccion.setAutoscrolls(false);
        txtInspeccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtInspeccionKeyReleased(evt);
            }
        });
        jScrollPane25.setViewportView(txtInspeccion);

        lbDireccion42.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion42.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbDireccion42.setText("IMPRESIÓN DIAGNOSTICA");

        txtImpresionDiagnostica.setColumns(20);
        txtImpresionDiagnostica.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtImpresionDiagnostica.setLineWrap(true);
        txtImpresionDiagnostica.setRows(2);
        txtImpresionDiagnostica.setAutoscrolls(false);
        txtImpresionDiagnostica.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtImpresionDiagnosticaKeyReleased(evt);
            }
        });
        jScrollPane28.setViewportView(txtImpresionDiagnostica);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbDireccion41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane25)
                    .addComponent(lbDireccion42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane28)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane46, javax.swing.GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
                                    .addComponent(lbDireccion66, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(36, 36, 36)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane39, javax.swing.GroupLayout.DEFAULT_SIZE, 452, Short.MAX_VALUE)
                                    .addComponent(lbDireccion17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbDireccion64, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane41)
                                    .addComponent(jScrollPane42, javax.swing.GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
                                    .addComponent(lbDireccion61, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbDireccion62, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(36, 36, 36)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane44, javax.swing.GroupLayout.DEFAULT_SIZE, 452, Short.MAX_VALUE)
                                    .addComponent(jScrollPane45)
                                    .addComponent(lbDireccion65, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jScrollPane27)
                            .addComponent(lbDireccion40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 5, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbDireccion66)
                    .addComponent(lbDireccion17))
                .addGap(0, 0, 0)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane46, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
                    .addComponent(jScrollPane39))
                .addGap(25, 25, 25)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbDireccion61)
                    .addComponent(lbDireccion64))
                .addGap(0, 0, 0)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane41, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                    .addComponent(jScrollPane44))
                .addGap(25, 25, 25)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbDireccion62)
                    .addComponent(lbDireccion65))
                .addGap(0, 0, 0)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane42, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
                    .addComponent(jScrollPane45))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 27, Short.MAX_VALUE)
                .addComponent(lbDireccion40)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane27, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(lbDireccion41)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane25, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbDireccion42)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane28, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        jTabbedPane1.addTab("Antecedentes", jPanel4);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setName(""); // NOI18N

        lbDireccion16.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbDireccion16.setText("Cabeza");

        lbDireccion18.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbDireccion18.setText("Pulmones");

        lbDireccion22.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbDireccion22.setText("Abdomen");

        lbDireccion24.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbDireccion24.setText("Piel y Anexos");

        txtExtremidades.setColumns(20);
        txtExtremidades.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtExtremidades.setLineWrap(true);
        txtExtremidades.setRows(2);
        txtExtremidades.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtExtremidadesKeyReleased(evt);
            }
        });
        jScrollPane7.setViewportView(txtExtremidades);

        lbDireccion30.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion30.setText("IMC:");

        txtimc.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtimc.setName("TLLC"); // NOI18N
        txtimc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtimcActionPerformed(evt);
            }
        });
        txtimc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtimcKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtimcKeyTyped(evt);
            }
        });

        txtFr.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtFr.setName("TLLC"); // NOI18N
        txtFr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFrActionPerformed(evt);
            }
        });
        txtFr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFrKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFrKeyTyped(evt);
            }
        });

        lbDireccion19.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion19.setText("FR:");

        lbDireccion21.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion21.setText("TEMP:");

        txttemp.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txttemp.setName("FC"); // NOI18N
        txttemp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttempActionPerformed(evt);
            }
        });
        txttemp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txttempKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txttempKeyTyped(evt);
            }
        });

        txtPeso.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtPeso.setName("FR"); // NOI18N
        txtPeso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPesoActionPerformed(evt);
            }
        });
        txtPeso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPesoKeyTyped(evt);
            }
        });

        txtPa.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtPa.setName("Tº"); // NOI18N
        txtPa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPaActionPerformed(evt);
            }
        });
        txtPa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPaKeyTyped(evt);
            }
        });

        lbDireccion28.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion28.setText("TALLA:");

        txtEstatura.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtEstatura.setName("Pulso"); // NOI18N
        txtEstatura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEstaturaActionPerformed(evt);
            }
        });
        txtEstatura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEstaturaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEstaturaKeyTyped(evt);
            }
        });

        txtAbdominal.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtAbdominal.setName("Actitud"); // NOI18N
        txtAbdominal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAbdominalActionPerformed(evt);
            }
        });
        txtAbdominal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAbdominalKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAbdominalKeyTyped(evt);
            }
        });

        txtFc.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtFc.setName("TLLC"); // NOI18N
        txtFc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFcActionPerformed(evt);
            }
        });
        txtFc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFcKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFcKeyTyped(evt);
            }
        });

        lbDireccion29.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion29.setText("FC:");

        lbDireccion31.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion31.setText("P.Abdominal:");

        lbDireccion32.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion32.setText("Peso (Kg):");

        lbDireccion33.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion33.setText("PA (mmHg:)");

        txtVisual.setColumns(20);
        txtVisual.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtVisual.setLineWrap(true);
        txtVisual.setRows(2);
        txtVisual.setAutoscrolls(false);
        txtVisual.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtVisualKeyReleased(evt);
            }
        });
        jScrollPane16.setViewportView(txtVisual);

        txtCardiopulmonar.setColumns(20);
        txtCardiopulmonar.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtCardiopulmonar.setLineWrap(true);
        txtCardiopulmonar.setRows(2);
        txtCardiopulmonar.setAutoscrolls(false);
        txtCardiopulmonar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCardiopulmonarKeyReleased(evt);
            }
        });
        jScrollPane56.setViewportView(txtCardiopulmonar);

        txtAbdomen.setColumns(20);
        txtAbdomen.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtAbdomen.setRows(2);
        txtAbdomen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAbdomenKeyReleased(evt);
            }
        });
        jScrollPane57.setViewportView(txtAbdomen);

        txtPiel.setColumns(20);
        txtPiel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtPiel.setLineWrap(true);
        txtPiel.setRows(2);
        txtPiel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPielKeyReleased(evt);
            }
        });
        jScrollPane58.setViewportView(txtPiel);

        lbDireccion34.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbDireccion34.setText("Ojos");

        lbDireccion35.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbDireccion35.setText("Agudeza Visual ");

        txtOjos.setColumns(20);
        txtOjos.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtOjos.setLineWrap(true);
        txtOjos.setRows(2);
        txtOjos.setAutoscrolls(false);
        txtOjos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtOjosKeyReleased(evt);
            }
        });
        jScrollPane17.setViewportView(txtOjos);

        txtCabeza.setColumns(20);
        txtCabeza.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtCabeza.setLineWrap(true);
        txtCabeza.setRows(2);
        txtCabeza.setAutoscrolls(false);
        txtCabeza.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCabezaKeyReleased(evt);
            }
        });
        jScrollPane18.setViewportView(txtCabeza);

        txtOidos.setColumns(20);
        txtOidos.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtOidos.setRows(2);
        txtOidos.setAutoscrolls(false);
        txtOidos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtOidosKeyReleased(evt);
            }
        });
        jScrollPane24.setViewportView(txtOidos);

        lbDireccion37.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbDireccion37.setText("Oídos");

        txtNariz.setColumns(20);
        txtNariz.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtNariz.setLineWrap(true);
        txtNariz.setRows(2);
        txtNariz.setAutoscrolls(false);
        txtNariz.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNarizKeyReleased(evt);
            }
        });
        jScrollPane26.setViewportView(txtNariz);

        lbDireccion38.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbDireccion38.setText("Nariz");

        lbDireccion39.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbDireccion39.setText("Orofaringe");

        txtBoca.setColumns(20);
        txtBoca.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtBoca.setLineWrap(true);
        txtBoca.setRows(2);
        txtBoca.setAutoscrolls(false);
        txtBoca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBocaKeyReleased(evt);
            }
        });
        jScrollPane34.setViewportView(txtBoca);

        txtCuello.setColumns(20);
        txtCuello.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtCuello.setLineWrap(true);
        txtCuello.setRows(2);
        txtCuello.setAutoscrolls(false);
        txtCuello.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCuelloKeyReleased(evt);
            }
        });
        jScrollPane35.setViewportView(txtCuello);

        lbDireccion53.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion53.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbDireccion53.setText("Cuello");

        txtTorax.setColumns(20);
        txtTorax.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtTorax.setLineWrap(true);
        txtTorax.setRows(2);
        txtTorax.setAutoscrolls(false);
        txtTorax.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtToraxKeyReleased(evt);
            }
        });
        jScrollPane36.setViewportView(txtTorax);

        lbDireccion54.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion54.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbDireccion54.setText("Torax");

        txtGenito.setColumns(20);
        txtGenito.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtGenito.setLineWrap(true);
        txtGenito.setRows(2);
        txtGenito.setAutoscrolls(false);
        txtGenito.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtGenitoKeyReleased(evt);
            }
        });
        jScrollPane37.setViewportView(txtGenito);

        lbDireccion55.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion55.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbDireccion55.setText("Genito urinario");

        txtAno.setColumns(20);
        txtAno.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtAno.setLineWrap(true);
        txtAno.setRows(2);
        txtAno.setAutoscrolls(false);
        txtAno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAnoKeyReleased(evt);
            }
        });
        jScrollPane38.setViewportView(txtAno);

        lbDireccion57.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion57.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbDireccion57.setText("SNC");

        lbDireccion58.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion58.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbDireccion58.setText("Extremidades");

        lbDireccion59.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion59.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbDireccion59.setText("Corazón");

        txtNeurologico.setColumns(20);
        txtNeurologico.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtNeurologico.setLineWrap(true);
        txtNeurologico.setRows(2);
        txtNeurologico.setAutoscrolls(false);
        txtNeurologico.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNeurologicoKeyReleased(evt);
            }
        });
        jScrollPane40.setViewportView(txtNeurologico);

        lbDireccion60.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion60.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbDireccion60.setText("Columna");

        txtOsteoMuscular.setColumns(20);
        txtOsteoMuscular.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtOsteoMuscular.setLineWrap(true);
        txtOsteoMuscular.setRows(2);
        txtOsteoMuscular.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtOsteoMuscularKeyReleased(evt);
            }
        });
        jScrollPane60.setViewportView(txtOsteoMuscular);

        lbDireccion25.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbDireccion25.setText("Osteo muscular");

        txtMuscular.setColumns(20);
        txtMuscular.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtMuscular.setLineWrap(true);
        txtMuscular.setRows(2);
        txtMuscular.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMuscularKeyReleased(evt);
            }
        });
        jScrollPane61.setViewportView(txtMuscular);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbDireccion25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(0, 22, Short.MAX_VALUE)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbDireccion19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbDireccion28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtFr, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                            .addComponent(txtEstatura))
                        .addGap(44, 44, 44)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbDireccion21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbDireccion29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtFc, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txttemp, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(48, 48, 48)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbDireccion33, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbDireccion31))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtPa, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                            .addComponent(txtAbdominal))
                        .addGap(38, 38, 38)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbDireccion30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbDireccion32, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtPeso, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                            .addComponent(txtimc)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jScrollPane56, javax.swing.GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE)
                                .addComponent(jScrollPane16, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jScrollPane18, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jScrollPane24, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jScrollPane34)
                                .addComponent(lbDireccion22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane57)
                                .addComponent(lbDireccion58, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbDireccion39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbDireccion16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbDireccion37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbDireccion35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbDireccion18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(lbDireccion24, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE)
                                .addComponent(jScrollPane58, javax.swing.GroupLayout.Alignment.LEADING)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane26)
                            .addComponent(jScrollPane40)
                            .addComponent(jScrollPane36)
                            .addComponent(jScrollPane37)
                            .addComponent(jScrollPane60)
                            .addComponent(jScrollPane35)
                            .addComponent(jScrollPane17, javax.swing.GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE)
                            .addComponent(lbDireccion60, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbDireccion57, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane38)
                            .addComponent(lbDireccion55, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbDireccion54, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbDireccion53, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbDireccion34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbDireccion59, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbDireccion38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jScrollPane61, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPeso, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFr, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbDireccion19)
                        .addComponent(lbDireccion29, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbDireccion33)
                        .addComponent(txtPa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbDireccion32)))
                .addGap(3, 3, 3)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(lbDireccion31, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtAbdominal, javax.swing.GroupLayout.Alignment.LEADING))
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(lbDireccion30, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtimc, javax.swing.GroupLayout.Alignment.LEADING))
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtEstatura, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbDireccion28)
                        .addComponent(lbDireccion21)
                        .addComponent(txttemp, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbDireccion16)
                    .addComponent(lbDireccion53))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane18)
                    .addComponent(jScrollPane35))
                .addGap(15, 15, 15)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbDireccion37)
                    .addComponent(lbDireccion34))
                .addGap(0, 0, 0)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane24)
                    .addComponent(jScrollPane17))
                .addGap(15, 15, 15)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbDireccion35)
                    .addComponent(lbDireccion38, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane26)
                    .addComponent(jScrollPane16))
                .addGap(15, 15, 15)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbDireccion59, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbDireccion39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane40)
                    .addComponent(jScrollPane34))
                .addGap(15, 15, 15)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbDireccion18)
                    .addComponent(lbDireccion54))
                .addGap(0, 0, 0)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane56)
                    .addComponent(jScrollPane36))
                .addGap(15, 15, 15)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbDireccion22)
                    .addComponent(lbDireccion55))
                .addGap(0, 0, 0)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane57)
                    .addComponent(jScrollPane37))
                .addGap(15, 15, 15)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbDireccion58)
                    .addComponent(lbDireccion60))
                .addGap(0, 0, 0)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane7)
                    .addComponent(jScrollPane60))
                .addGap(15, 15, 15)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbDireccion24)
                    .addComponent(lbDireccion57))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane58)
                    .addComponent(jScrollPane38))
                .addGap(18, 18, 18)
                .addComponent(lbDireccion25)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane61, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );

        jTabbedPane1.addTab("Examen Fisico", jPanel8);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 834, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        scrFormulario.setViewportView(jPanel7);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrFormulario)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrFormulario)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtNombreCompletoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreCompletoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreCompletoActionPerformed

    private void txtEstadoCivilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEstadoCivilActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEstadoCivilActionPerformed

    private void txtFechaNacimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaNacimientoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaNacimientoActionPerformed

    private void txtEdadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEdadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEdadActionPerformed

    private void txtIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdActionPerformed

    private void txtIdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            setPaciente(txtId.getText());
        } else {
            txtIdSistema.setText("");
            txtNombreCompleto.setText("");
            txtFechaNacimiento.setText("");
            txtlugar.setText("");
            txtEdad.setText("");
            txtDireccion.setText("");
            txtTelefono.setText("");
            txtEstadoCivil.setText("");
            btnReimprimir.setEnabled(false);
        }
    }//GEN-LAST:event_txtIdKeyReleased

    private void txtlugarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtlugarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtlugarActionPerformed

    private void txtDireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDireccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDireccionActionPerformed

    private void txtTelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoActionPerformed

    private void btnReimprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReimprimirActionPerformed
        infReimpresionExamenes reimpresion = new infReimpresionExamenes();
        reimpresion.show();
        reimpresion.setNombre(txtNombreCompleto.getText());
        reimpresion.cargarRegistros(txtIdSistema.getText(), "certificadoMedico", instancias);
    }//GEN-LAST:event_btnReimprimirActionPerformed

    private void btnGuardar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar1ActionPerformed
        if (metodos.msgPregunta(null, "¿Desea continuar?") == 0) {

            String conse = "CERTIFICADOMED-" + instancias.getSql().getNumConsecutivo("CERTIFICADOMED")[0];
            ndCertificadoMedico nodo = instancias.getSql().getDatosCertificadoMedico(conse, "");

            if (nodo.getId() != null) {
                if (!instancias.getSql().aumentarConsecutivo("CERTIFICADOMED", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("CERTIFICADOMED")[0]) + 1)) {
                    metodos.msgError(null, "Hubo un problema al aumentar el consecutivo del certificado");
                }

                conse = "CERTIFICADOMED-" + instancias.getSql().getNumConsecutivo("CERTIFICADOMED")[0];
                while (instancias.getSql().getDatosRemision(conse).getId() != null) {
                    if (!instancias.getSql().aumentarConsecutivo("CERTIFICADOMED", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("CERTIFICADOMED")[0]) + 1)) {
                        metodos.msgError(null, "Hubo un problema al aumentar el consecutivo del certificado");
                    }
                    conse = "CERTIFICADOMED-" + instancias.getSql().getNumConsecutivo("CERTIFICADOMED")[0];
                }
            }

            Object[] vector = {conse, historia.getId(), "", "", "", "", "", "", txtInspeccion.getText(), txttemp.getText(), txtPeso.getText(),
                txtEstatura.getText(), txtFc.getText(), txtFr.getText(), txtPa.getText(), "", txtAbdominal.getText(), txtimc.getText(),
                txtCabeza.getText(), txtOjos.getText(), txtVisual.getText(), txtOidos.getText(), txtNariz.getText(), txtBoca.getText(),
                txtCuello.getText(), txtCardiopulmonar.getText(), txtTorax.getText(), txtAbdomen.getText(), txtGenito.getText(), txtAno.getText(),
                txtExtremidades.getText(), txtNeurologico.getText(), txtPiel.getText(), txtOsteoMuscular.getText(), "", txtImpresionDiagnostica.getText(),
                "", "", "", "", "", instancias.getUsuario(), metodos.fechaConsulta(metodosGenerales.fecha()), txtPatologicos.getText(), txtQuirurgicos.getText(),
                txtAlergicos.getText(), txtTraumaticos.getText(), txtGinecoobstetricos.getText(), txtFamiliares.getText(), txtHabitos.getText(), txtMuscular.getText()};

            nodo = metodos.llenarCertificadoMedico(vector);

            if (!instancias.getSql().agregarCertificadoMedico(nodo, "")) {
                metodos.msgError(null, "Error al guardar la consulta");
                return;
            } else {
                metodos.msgExito(null, "Certificado generado con exito!");
            }

            if (!instancias.getSql().aumentarConsecutivo("CERTIFICADOMED", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("CERTIFICADOMED")[0]) + 1)) {
                metodos.msgError(null, "Hubo un problema al guardar en el consecutivo del certificado");
                return;
            }

            instancias.getReporte().verCertificadoMedico(instancias.getInformacionEmpresaCompleto(), conse, txtEdad.getText(), "");
            Limpiar();
        }
    }//GEN-LAST:event_btnGuardar1ActionPerformed

    private void btnBuscTercerosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscTercerosActionPerformed
        ventanaTerceros("");
    }//GEN-LAST:event_btnBuscTercerosActionPerformed

    private void txtHabitosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHabitosKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtInspeccion.requestFocus();
        }
    }//GEN-LAST:event_txtHabitosKeyReleased

    private void txtPatologicosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPatologicosKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtQuirurgicos.requestFocus();
        }
    }//GEN-LAST:event_txtPatologicosKeyReleased

    private void txtQuirurgicosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQuirurgicosKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtTraumaticos.requestFocus();
        }
    }//GEN-LAST:event_txtQuirurgicosKeyReleased

    private void txtAlergicosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAlergicosKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtGinecoobstetricos.requestFocus();
        }
    }//GEN-LAST:event_txtAlergicosKeyReleased

    private void txtTraumaticosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTraumaticosKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtAlergicos.requestFocus();
        }
    }//GEN-LAST:event_txtTraumaticosKeyReleased

    private void txtGinecoobstetricosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGinecoobstetricosKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtHabitos.requestFocus();
        }
    }//GEN-LAST:event_txtGinecoobstetricosKeyReleased

    private void txtFamiliaresKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFamiliaresKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtPatologicos.requestFocus();
        }
    }//GEN-LAST:event_txtFamiliaresKeyReleased

    private void txtExtremidadesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtExtremidadesKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtOsteoMuscular.requestFocus();
        }
    }//GEN-LAST:event_txtExtremidadesKeyReleased

    private void txtimcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtimcActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtimcActionPerformed

    private void txtimcKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtimcKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_TAB || evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtCabeza.requestFocus();
        }
    }//GEN-LAST:event_txtimcKeyReleased

    private void txtimcKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtimcKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtimcKeyTyped

    private void txtFrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFrActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFrActionPerformed

    private void txtFrKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFrKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_TAB || evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtFc.requestFocus();
        }
    }//GEN-LAST:event_txtFrKeyReleased

    private void txtFrKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFrKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFrKeyTyped

    private void txttempActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttempActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttempActionPerformed

    private void txttempKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttempKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_TAB || evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtAbdominal.requestFocus();
        }
    }//GEN-LAST:event_txttempKeyReleased

    private void txttempKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttempKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txttempKeyTyped

    private void txtPesoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPesoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPesoActionPerformed

    private void txtPesoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_TAB || evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtEstatura.requestFocus();
        }
    }//GEN-LAST:event_txtPesoKeyReleased

    private void txtPesoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPesoKeyTyped

    private void txtPaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPaActionPerformed

    private void txtPaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPaKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_TAB || evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtPeso.requestFocus();
        }
    }//GEN-LAST:event_txtPaKeyReleased

    private void txtPaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPaKeyTyped

    private void txtEstaturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEstaturaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEstaturaActionPerformed

    private void txtEstaturaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEstaturaKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                double x = Double.parseDouble(txtPeso.getText()) / (Double.parseDouble(txtEstatura.getText()) * Double.parseDouble(txtEstatura.getText()));
                txtimc.setText(Math.round(x) + "");
                txttemp.requestFocus();
            } catch (Exception e) {
                txtimc.setText("");
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            metodos.msgError(null, "Esta función solo sirve con ENTER");
        }
    }//GEN-LAST:event_txtEstaturaKeyReleased

    private void txtEstaturaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEstaturaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEstaturaKeyTyped

    private void txtAbdominalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAbdominalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAbdominalActionPerformed

    private void txtAbdominalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAbdominalKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_TAB || evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtCabeza.requestFocus();
        }
    }//GEN-LAST:event_txtAbdominalKeyReleased

    private void txtAbdominalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAbdominalKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAbdominalKeyTyped

    private void txtFcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFcActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFcActionPerformed

    private void txtFcKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFcKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_TAB || evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtPa.requestFocus();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_txtFcKeyReleased

    private void txtFcKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFcKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFcKeyTyped

    private void txtVisualKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtVisualKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtNariz.requestFocus();
        }
    }//GEN-LAST:event_txtVisualKeyReleased

    private void txtCardiopulmonarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCardiopulmonarKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtTorax.requestFocus();
        }
    }//GEN-LAST:event_txtCardiopulmonarKeyReleased

    private void txtAbdomenKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAbdomenKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtGenito.requestFocus();
        }
    }//GEN-LAST:event_txtAbdomenKeyReleased

    private void txtPielKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPielKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtAno.requestFocus();
        }
    }//GEN-LAST:event_txtPielKeyReleased

    private void txtOjosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtOjosKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtOidos.requestFocus();
        }
    }//GEN-LAST:event_txtOjosKeyReleased

    private void txtCabezaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCabezaKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtOjos.requestFocus();
        }
    }//GEN-LAST:event_txtCabezaKeyReleased

    private void txtOidosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtOidosKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtBoca.requestFocus();
        }
    }//GEN-LAST:event_txtOidosKeyReleased

    private void txtNarizKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNarizKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtCuello.requestFocus();
        }
    }//GEN-LAST:event_txtNarizKeyReleased

    private void txtBocaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBocaKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtVisual.requestFocus();
        }
    }//GEN-LAST:event_txtBocaKeyReleased

    private void txtCuelloKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCuelloKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtNeurologico.requestFocus();
        }
    }//GEN-LAST:event_txtCuelloKeyReleased

    private void txtToraxKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtToraxKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtAbdomen.requestFocus();
        }
    }//GEN-LAST:event_txtToraxKeyReleased

    private void txtGenitoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGenitoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtExtremidades.requestFocus();
        }
    }//GEN-LAST:event_txtGenitoKeyReleased

    private void txtAnoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAnoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtImpresionDiagnostica.requestFocus();
        }
    }//GEN-LAST:event_txtAnoKeyReleased

    private void txtNeurologicoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNeurologicoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtCardiopulmonar.requestFocus();
        }
    }//GEN-LAST:event_txtNeurologicoKeyReleased

    private void txtOsteoMuscularKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtOsteoMuscularKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtPiel.requestFocus();
        }
    }//GEN-LAST:event_txtOsteoMuscularKeyReleased

    private void txtMuscularKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMuscularKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMuscularKeyReleased

    private void txtInspeccionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtInspeccionKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtFr.requestFocus();
        }
    }//GEN-LAST:event_txtInspeccionKeyReleased

    private void txtImpresionDiagnosticaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtImpresionDiagnosticaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtImpresionDiagnosticaKeyReleased

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        Limpiar();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void txtIdSistemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdSistemaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdSistemaActionPerformed

    private void txtIdSistemaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdSistemaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdSistemaKeyReleased

    public void reimprimir(String consecutivo) {
        ndCertificadoMedico nodo = instancias.getSql().getDatosCertificadoMedico(consecutivo, "");
        instancias.getReporte().verCertificadoMedico(instancias.getInformacionEmpresa(), consecutivo, txtEdad.getText(), "");
    }

    public void setPaciente(String paciente) {

        ModeloContacto Paciente = instancias.getSql().getDatosTercero(paciente);

        if (Paciente.getId() == null) {
            ventanaTerceros(paciente);
        } else {

            ndHistoriaClinica nodo = instancias.getSql().getDatosHistoriaClinica(Paciente.getIdSistema());
            historia = nodo;

            btnReimprimir.setEnabled(true);
            txtNombreCompleto.setText(Paciente.getNombre());
            txtIdSistema.setText(Paciente.getIdSistema());

            try {
                txtNombreCompleto.setText(Paciente.getNombre());
            } catch (Exception e) {
                txtNombreCompleto.setText("");
            }

            try {
                txtFechaNacimiento.setText(metodos.fecha(Paciente.getNacimiento()));
            } catch (Exception e) {
                txtFechaNacimiento.setText("");
            }

            try {
                txtlugar.setText(Paciente.getCiudad());
            } catch (Exception e) {
                txtlugar.setText("");
            }

            try {
                txtEdad.setText(metodosGenerales.calcularEdad(metodos.fecha(Paciente.getNacimiento())) + "");
            } catch (Exception e) {
                txtEdad.setText("");
            }

            try {
                txtDireccion.setText(Paciente.getDireccion());
            } catch (Exception e) {
                txtDireccion.setText("");
            }

            try {
                txtTelefono.setText(Paciente.getTelefono());
            } catch (Exception e) {
                txtTelefono.setText("");
            }

            try {
                txtEstadoCivil.setText(Paciente.getEstado());
            } catch (Exception e) {
                txtEstadoCivil.setText("");
            }
        }
    }

    public void ventanaTerceros(String nit) {
        buscClientes buscar = new buscClientes(instancias.getMenu(), true, false, null, "");
        buscar.setLocationRelativeTo(null);
        instancias.setBusClientes(buscar);
        instancias.setCampoActual(txtId);
        txtId.requestFocus();
        buscar.noEncontrado(nit);
        buscar.show();
    }

    public void Limpiar() {
        btnReimprimir.setEnabled(false);
        txtIdSistema.setText("");
        txttemp.setText("");
        txtPeso.setText("");
        txtEstatura.setText("");
        txtFc.setText("");
        txtFr.setText("");
        txtPa.setText("");
        txtAbdominal.setText("");
        txtimc.setText("");
        txtVisual.setText("");
        txtCardiopulmonar.setText("");
        txtAbdomen.setText("");
        txtPiel.setText("");
        txtExtremidades.setText("");
        txtImpresionDiagnostica.setText("");
        txtInspeccion.setText("");
        txttemp.setText("");
        txtPeso.setText("");
        txtEstatura.setText("");
        txtFc.setText("");
        txtFr.setText("");
        txtPa.setText("");
        txtAbdominal.setText("");
        txtimc.setText("");
        txtCabeza.setText("");
        txtOjos.setText("");
        txtVisual.setText("");
        txtOidos.setText("");
        txtNariz.setText("");
        txtBoca.setText("");
        txtCuello.setText("");
        txtCardiopulmonar.setText("");
        txtTorax.setText("");
        txtAbdomen.setText("");
        txtGenito.setText("");
        txtAno.setText("");
        txtExtremidades.setText("");
        txtNeurologico.setText("");
        txtPiel.setText("");
        txtOsteoMuscular.setText("");
        txtImpresionDiagnostica.setText("");
        txtMuscular.setText("");
        txtId.setText("");
        txtNombreCompleto.setText("");
        txtFechaNacimiento.setText("");
        txtlugar.setText("");
        txtEdad.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
        txtEstadoCivil.setText("");
        txtFamiliares.setText("");
        txtPatologicos.setText("");
        txtQuirurgicos.setText("");
        txtTraumaticos.setText("");
        txtAlergicos.setText("");
        txtGinecoobstetricos.setText("");
        txtHabitos.setText("");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscTerceros;
    private javax.swing.JButton btnGuardar1;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnReimprimir;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup grupoTipo;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane24;
    private javax.swing.JScrollPane jScrollPane25;
    private javax.swing.JScrollPane jScrollPane26;
    private javax.swing.JScrollPane jScrollPane27;
    private javax.swing.JScrollPane jScrollPane28;
    private javax.swing.JScrollPane jScrollPane34;
    private javax.swing.JScrollPane jScrollPane35;
    private javax.swing.JScrollPane jScrollPane36;
    private javax.swing.JScrollPane jScrollPane37;
    private javax.swing.JScrollPane jScrollPane38;
    private javax.swing.JScrollPane jScrollPane39;
    private javax.swing.JScrollPane jScrollPane40;
    private javax.swing.JScrollPane jScrollPane41;
    private javax.swing.JScrollPane jScrollPane42;
    private javax.swing.JScrollPane jScrollPane44;
    private javax.swing.JScrollPane jScrollPane45;
    private javax.swing.JScrollPane jScrollPane46;
    private javax.swing.JScrollPane jScrollPane56;
    private javax.swing.JScrollPane jScrollPane57;
    private javax.swing.JScrollPane jScrollPane58;
    private javax.swing.JScrollPane jScrollPane60;
    private javax.swing.JScrollPane jScrollPane61;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lbDireccion16;
    private javax.swing.JLabel lbDireccion17;
    private javax.swing.JLabel lbDireccion18;
    private javax.swing.JLabel lbDireccion19;
    private javax.swing.JLabel lbDireccion21;
    private javax.swing.JLabel lbDireccion22;
    private javax.swing.JLabel lbDireccion24;
    private javax.swing.JLabel lbDireccion25;
    private javax.swing.JLabel lbDireccion28;
    private javax.swing.JLabel lbDireccion29;
    private javax.swing.JLabel lbDireccion30;
    private javax.swing.JLabel lbDireccion31;
    private javax.swing.JLabel lbDireccion32;
    private javax.swing.JLabel lbDireccion33;
    private javax.swing.JLabel lbDireccion34;
    private javax.swing.JLabel lbDireccion35;
    private javax.swing.JLabel lbDireccion37;
    private javax.swing.JLabel lbDireccion38;
    private javax.swing.JLabel lbDireccion39;
    private javax.swing.JLabel lbDireccion40;
    private javax.swing.JLabel lbDireccion41;
    private javax.swing.JLabel lbDireccion42;
    private javax.swing.JLabel lbDireccion53;
    private javax.swing.JLabel lbDireccion54;
    private javax.swing.JLabel lbDireccion55;
    private javax.swing.JLabel lbDireccion57;
    private javax.swing.JLabel lbDireccion58;
    private javax.swing.JLabel lbDireccion59;
    private javax.swing.JLabel lbDireccion60;
    private javax.swing.JLabel lbDireccion61;
    private javax.swing.JLabel lbDireccion62;
    private javax.swing.JLabel lbDireccion64;
    private javax.swing.JLabel lbDireccion65;
    private javax.swing.JLabel lbDireccion66;
    private javax.swing.JLabel lblTipoId;
    private javax.swing.JScrollPane scrFormulario;
    private javax.swing.JTextArea txtAbdomen;
    private javax.swing.JTextField txtAbdominal;
    private javax.swing.JTextArea txtAlergicos;
    private javax.swing.JTextArea txtAno;
    private javax.swing.JTextArea txtBoca;
    private javax.swing.JTextArea txtCabeza;
    private javax.swing.JTextArea txtCardiopulmonar;
    private javax.swing.JTextArea txtCuello;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtEdad;
    private javax.swing.JTextField txtEstadoCivil;
    private javax.swing.JTextField txtEstatura;
    private javax.swing.JTextArea txtExtremidades;
    private javax.swing.JTextArea txtFamiliares;
    private javax.swing.JTextField txtFc;
    private javax.swing.JTextField txtFechaNacimiento;
    private javax.swing.JTextField txtFr;
    private javax.swing.JTextArea txtGenito;
    private javax.swing.JTextArea txtGinecoobstetricos;
    private javax.swing.JTextArea txtHabitos;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtIdSistema;
    private javax.swing.JTextArea txtImpresionDiagnostica;
    private javax.swing.JTextArea txtInspeccion;
    private javax.swing.JTextArea txtMuscular;
    private javax.swing.JTextArea txtNariz;
    private javax.swing.JTextArea txtNeurologico;
    private javax.swing.JTextField txtNombreCompleto;
    private javax.swing.JTextArea txtOidos;
    private javax.swing.JTextArea txtOjos;
    private javax.swing.JTextArea txtOsteoMuscular;
    private javax.swing.JTextField txtPa;
    private javax.swing.JTextArea txtPatologicos;
    private javax.swing.JTextField txtPeso;
    private javax.swing.JTextArea txtPiel;
    private javax.swing.JTextArea txtQuirurgicos;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextArea txtTorax;
    private javax.swing.JTextArea txtTraumaticos;
    private javax.swing.JTextArea txtVisual;
    private javax.swing.JTextField txtimc;
    private javax.swing.JTextField txtlugar;
    private javax.swing.JTextField txttemp;
    // End of variables declaration//GEN-END:variables
}
