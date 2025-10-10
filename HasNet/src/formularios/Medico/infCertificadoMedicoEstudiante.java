package formularios.Medico;

import clases.Instancias;
import clases.Medico.ndCertificadoMedico;
import clases.Medico.ndHistoriaClinica;
import Utilidades.BaseDatos.SQL;
import clases.metodosGenerales;
import Modelo.Terceros.ModeloContacto;
import formularios.terceros.buscClientes;
import java.awt.Dimension;
import java.awt.event.KeyEvent;

public class infCertificadoMedicoEstudiante extends javax.swing.JPanel {

    metodosGenerales metodos = new metodosGenerales();
    private Instancias instancias;
    ndHistoriaClinica historia;
    private Dimension dimBarra = null;

    public infCertificadoMedicoEstudiante() {
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
        jPanel5 = new javax.swing.JPanel();
        txtdirigido = new javax.swing.JTextField();
        lblTipoId1 = new javax.swing.JLabel();
        txtIdSistema = new javax.swing.JTextField();
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
        jLabel17 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtHijos = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtEscolaridad = new javax.swing.JTextField();
        btnBuscTerceros = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        btnReimprimir = new javax.swing.JButton();
        btnGuardar1 = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane46 = new javax.swing.JScrollPane();
        txtFamiliares = new javax.swing.JTextArea();
        jScrollPane27 = new javax.swing.JScrollPane();
        txtHabitos = new javax.swing.JTextArea();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        lbDireccion32 = new javax.swing.JLabel();
        txtPeso = new javax.swing.JTextField();
        lbDireccion19 = new javax.swing.JLabel();
        txtFr = new javax.swing.JTextField();
        lbDireccion29 = new javax.swing.JLabel();
        txtFc = new javax.swing.JTextField();
        txtEstatura = new javax.swing.JTextField();
        lbDireccion28 = new javax.swing.JLabel();
        lbDireccion30 = new javax.swing.JLabel();
        lbDireccion31 = new javax.swing.JLabel();
        txtAbdominal = new javax.swing.JTextField();
        txtimc = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        lbDireccion35 = new javax.swing.JLabel();
        jScrollPane16 = new javax.swing.JScrollPane();
        txtVisual = new javax.swing.JTextArea();
        lbDireccion44 = new javax.swing.JLabel();
        jScrollPane18 = new javax.swing.JScrollPane();
        txtPa = new javax.swing.JTextArea();
        lbDireccion34 = new javax.swing.JLabel();
        jScrollPane17 = new javax.swing.JScrollPane();
        txtOjos = new javax.swing.JTextArea();
        jScrollPane34 = new javax.swing.JScrollPane();
        txtBoca = new javax.swing.JTextArea();
        lbDireccion39 = new javax.swing.JLabel();
        jScrollPane24 = new javax.swing.JScrollPane();
        txtOidos = new javax.swing.JTextArea();
        lbDireccion37 = new javax.swing.JLabel();
        lbDireccion24 = new javax.swing.JLabel();
        jScrollPane58 = new javax.swing.JScrollPane();
        txtPiel = new javax.swing.JTextArea();
        lbDireccion22 = new javax.swing.JLabel();
        jScrollPane57 = new javax.swing.JScrollPane();
        txtAbdomen = new javax.swing.JTextArea();
        jScrollPane26 = new javax.swing.JScrollPane();
        txtNariz = new javax.swing.JTextArea();
        lbDireccion38 = new javax.swing.JLabel();
        jScrollPane56 = new javax.swing.JScrollPane();
        txtCardiopulmonar = new javax.swing.JTextArea();
        lbDireccion18 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        txtExtremidades = new javax.swing.JTextArea();
        lbDireccion58 = new javax.swing.JLabel();
        lbDireccion55 = new javax.swing.JLabel();
        jScrollPane37 = new javax.swing.JScrollPane();
        txtGenito = new javax.swing.JTextArea();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane59 = new javax.swing.JScrollPane();
        txtConducta = new javax.swing.JTextArea();
        jScrollPane61 = new javax.swing.JScrollPane();
        txtCie = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        txtdirigido.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtdirigido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdirigidoActionPerformed(evt);
            }
        });
        txtdirigido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtdirigidoKeyReleased(evt);
            }
        });

        lblTipoId1.setBackground(new java.awt.Color(255, 255, 255));
        lblTipoId1.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lblTipoId1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTipoId1.setText("DIRIGIDO A:");
        lblTipoId1.setOpaque(true);

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

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTipoId1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtdirigido, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtIdSistema)
                .addGap(185, 185, 185))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTipoId1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtdirigido, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(txtIdSistema, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );

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

        jLabel17.setBackground(new java.awt.Color(255, 255, 255));
        jLabel17.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel17.setText("Teléfono");
        jLabel17.setOpaque(true);

        txtTelefono.setEditable(false);
        txtTelefono.setBackground(new java.awt.Color(255, 255, 255));
        txtTelefono.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtTelefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelefonoActionPerformed(evt);
            }
        });

        jLabel13.setBackground(new java.awt.Color(255, 255, 255));
        jLabel13.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel13.setText("Hijos");
        jLabel13.setOpaque(true);

        txtHijos.setEditable(false);
        txtHijos.setBackground(new java.awt.Color(255, 255, 255));
        txtHijos.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtHijos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHijosActionPerformed(evt);
            }
        });

        jLabel18.setBackground(new java.awt.Color(255, 255, 255));
        jLabel18.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel18.setText("Escolaridad");
        jLabel18.setOpaque(true);

        txtEscolaridad.setEditable(false);
        txtEscolaridad.setBackground(new java.awt.Color(255, 255, 255));
        txtEscolaridad.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtEscolaridad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEscolaridadActionPerformed(evt);
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
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtlugar, javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtHijos, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
                                .addGap(554, 554, 554))
                            .addComponent(txtDireccion)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtNombreCompleto, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtEscolaridad)))))
                .addContainerGap())
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
                    .addComponent(txtEscolaridad, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHijos, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                .addGap(2, 2, 2))
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
        btnGuardar1.setText("GUARDAR    ");
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

        txtFamiliares.setColumns(20);
        txtFamiliares.setLineWrap(true);
        txtFamiliares.setRows(3);
        txtFamiliares.setAutoscrolls(false);
        txtFamiliares.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFamiliaresKeyReleased(evt);
            }
        });
        jScrollPane46.setViewportView(txtFamiliares);

        txtHabitos.setColumns(20);
        txtHabitos.setLineWrap(true);
        txtHabitos.setRows(2);
        txtHabitos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtHabitosKeyReleased(evt);
            }
        });
        jScrollPane27.setViewportView(txtHabitos);

        jLabel19.setBackground(new java.awt.Color(255, 255, 255));
        jLabel19.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("PERSONALES");
        jLabel19.setOpaque(true);

        jLabel20.setBackground(new java.awt.Color(255, 255, 255));
        jLabel20.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("FAMILIARES");
        jLabel20.setOpaque(true);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane46, javax.swing.GroupLayout.DEFAULT_SIZE, 891, Short.MAX_VALUE)
                    .addComponent(jScrollPane27)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane46, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane27, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Antecedentes", jPanel4);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setName(""); // NOI18N

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        lbDireccion32.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion32.setText("Peso (Kg):");

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

        lbDireccion19.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion19.setText("Estado Nutricional:");

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

        lbDireccion29.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion29.setText("FC:");

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

        lbDireccion28.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion28.setText("TALLA:");

        lbDireccion30.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion30.setText("IMC:");

        lbDireccion31.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion31.setText("P.Abdominal:");

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(71, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbDireccion19, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbDireccion32, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPeso, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFr, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbDireccion29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbDireccion28))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtEstatura)
                    .addComponent(txtFc, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbDireccion31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbDireccion30, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtimc, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAbdominal, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(71, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtimc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(txtEstatura, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbDireccion30, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbDireccion28, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(txtPeso)
                    .addComponent(lbDireccion32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtFr, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtFc, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbDireccion31, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                            .addComponent(lbDireccion29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbDireccion19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(5, 5, 5))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(txtAbdominal, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        lbDireccion35.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbDireccion35.setText("Agudeza visual OD");

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

        lbDireccion44.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion44.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbDireccion44.setText("Agudeza visual OI");

        txtPa.setColumns(20);
        txtPa.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtPa.setLineWrap(true);
        txtPa.setRows(2);
        txtPa.setAutoscrolls(false);
        txtPa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPaKeyReleased(evt);
            }
        });
        jScrollPane18.setViewportView(txtPa);

        lbDireccion34.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbDireccion34.setText("Ojos");

        txtOjos.setColumns(20);
        txtOjos.setLineWrap(true);
        txtOjos.setRows(2);
        txtOjos.setAutoscrolls(false);
        txtOjos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtOjosKeyReleased(evt);
            }
        });
        jScrollPane17.setViewportView(txtOjos);

        txtBoca.setColumns(20);
        txtBoca.setLineWrap(true);
        txtBoca.setRows(2);
        txtBoca.setAutoscrolls(false);
        txtBoca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBocaKeyReleased(evt);
            }
        });
        jScrollPane34.setViewportView(txtBoca);

        lbDireccion39.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbDireccion39.setText("Boca");

        txtOidos.setColumns(20);
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

        lbDireccion24.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbDireccion24.setText("Piel");

        txtPiel.setColumns(20);
        txtPiel.setLineWrap(true);
        txtPiel.setRows(2);
        txtPiel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPielKeyReleased(evt);
            }
        });
        jScrollPane58.setViewportView(txtPiel);

        lbDireccion22.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbDireccion22.setText("Abdomen");

        txtAbdomen.setColumns(20);
        txtAbdomen.setRows(2);
        txtAbdomen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAbdomenKeyReleased(evt);
            }
        });
        jScrollPane57.setViewportView(txtAbdomen);

        txtNariz.setColumns(20);
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

        txtCardiopulmonar.setColumns(20);
        txtCardiopulmonar.setLineWrap(true);
        txtCardiopulmonar.setRows(2);
        txtCardiopulmonar.setAutoscrolls(false);
        txtCardiopulmonar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCardiopulmonarKeyReleased(evt);
            }
        });
        jScrollPane56.setViewportView(txtCardiopulmonar);

        lbDireccion18.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbDireccion18.setText("Pulmones");

        txtExtremidades.setColumns(20);
        txtExtremidades.setLineWrap(true);
        txtExtremidades.setRows(2);
        txtExtremidades.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtExtremidadesKeyReleased(evt);
            }
        });
        jScrollPane7.setViewportView(txtExtremidades);

        lbDireccion58.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion58.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbDireccion58.setText("Extremidades");

        lbDireccion55.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion55.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbDireccion55.setText("Genitales");

        txtGenito.setColumns(20);
        txtGenito.setLineWrap(true);
        txtGenito.setRows(2);
        txtGenito.setAutoscrolls(false);
        txtGenito.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtGenitoKeyReleased(evt);
            }
        });
        jScrollPane37.setViewportView(txtGenito);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane24, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)
                            .addComponent(jScrollPane26)
                            .addComponent(lbDireccion35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbDireccion37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbDireccion38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane56)
                            .addComponent(lbDireccion18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbDireccion34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(53, 53, 53)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane18, javax.swing.GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)
                            .addComponent(jScrollPane34)
                            .addComponent(jScrollPane58)
                            .addComponent(jScrollPane57)
                            .addComponent(jScrollPane7)
                            .addComponent(lbDireccion44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbDireccion39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbDireccion58, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbDireccion22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbDireccion24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jScrollPane37)
                    .addComponent(lbDireccion55, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbDireccion35)
                    .addComponent(lbDireccion44))
                .addGap(2, 2, 2)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane16)
                    .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbDireccion34)
                    .addComponent(lbDireccion39))
                .addGap(2, 2, 2)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane17)
                    .addComponent(jScrollPane34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbDireccion37)
                    .addComponent(lbDireccion24))
                .addGap(2, 2, 2)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane24)
                    .addComponent(jScrollPane58, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbDireccion38)
                    .addComponent(lbDireccion22))
                .addGap(2, 2, 2)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane26)
                    .addComponent(jScrollPane57, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbDireccion18)
                    .addComponent(lbDireccion58))
                .addGap(2, 2, 2)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane56)
                    .addComponent(jScrollPane7))
                .addGap(18, 18, 18)
                .addComponent(lbDireccion55)
                .addGap(4, 4, 4)
                .addComponent(jScrollPane37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Examen Fisico", jPanel8);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        txtConducta.setColumns(20);
        txtConducta.setLineWrap(true);
        txtConducta.setRows(2);
        txtConducta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtConductaKeyReleased(evt);
            }
        });
        jScrollPane59.setViewportView(txtConducta);

        txtCie.setColumns(20);
        txtCie.setLineWrap(true);
        txtCie.setRows(2);
        txtCie.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCieKeyReleased(evt);
            }
        });
        jScrollPane61.setViewportView(txtCie);

        jLabel6.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("HALLAZGOS");

        jLabel10.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("RECOMENDACIONES");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane61, javax.swing.GroupLayout.DEFAULT_SIZE, 891, Short.MAX_VALUE)
                    .addComponent(jScrollPane59)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane61, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane59, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Otros", jPanel6);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 623, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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

    private void txtFamiliaresKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFamiliaresKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtHabitos.requestFocus();
        }
    }//GEN-LAST:event_txtFamiliaresKeyReleased

    private void txtHabitosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHabitosKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtPeso.requestFocus();
        }
    }//GEN-LAST:event_txtHabitosKeyReleased

    private void txtExtremidadesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtExtremidadesKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtCie.requestFocus();
        }
    }//GEN-LAST:event_txtExtremidadesKeyReleased

    private void txtFrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFrActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFrActionPerformed

    private void txtFrKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFrKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_TAB || evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtEstatura.requestFocus();
        }
    }//GEN-LAST:event_txtFrKeyReleased

    private void txtFrKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFrKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFrKeyTyped

    private void txtPesoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPesoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPesoActionPerformed

    private void txtPesoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_TAB || evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtFc.requestFocus();
        }
    }//GEN-LAST:event_txtPesoKeyReleased

    private void txtPesoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPesoKeyTyped

    private void txtEstaturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEstaturaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEstaturaActionPerformed

    private void txtEstaturaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEstaturaKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                double x = Double.parseDouble(txtPeso.getText()) / (Double.parseDouble(txtEstatura.getText()) * Double.parseDouble(txtEstatura.getText()));
                txtimc.setText(Math.round(x) + "");
                txtVisual.requestFocus();
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
            txtFr.requestFocus();
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
            txtOjos.requestFocus();
        }
    }//GEN-LAST:event_txtVisualKeyReleased

    private void txtCardiopulmonarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCardiopulmonarKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtAbdomen.requestFocus();
        }
    }//GEN-LAST:event_txtCardiopulmonarKeyReleased

    private void txtAbdomenKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAbdomenKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtGenito.requestFocus();
        }
    }//GEN-LAST:event_txtAbdomenKeyReleased

    private void txtPielKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPielKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtCardiopulmonar.requestFocus();
        }
    }//GEN-LAST:event_txtPielKeyReleased

    private void txtOjosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtOjosKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtOidos.requestFocus();
        }
    }//GEN-LAST:event_txtOjosKeyReleased

    private void txtOidosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtOidosKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtBoca.requestFocus();
        }
    }//GEN-LAST:event_txtOidosKeyReleased

    private void txtNarizKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNarizKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtPiel.requestFocus();
        }
    }//GEN-LAST:event_txtNarizKeyReleased

    private void txtBocaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBocaKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtNariz.requestFocus();
        }
    }//GEN-LAST:event_txtBocaKeyReleased

    private void txtGenitoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGenitoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtExtremidades.requestFocus();
        }
    }//GEN-LAST:event_txtGenitoKeyReleased

    private void txtimcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtimcActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtimcActionPerformed

    private void txtimcKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtimcKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_TAB || evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtVisual.requestFocus();
        }
    }//GEN-LAST:event_txtimcKeyReleased

    private void txtimcKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtimcKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtimcKeyTyped

    private void txtPaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPaKeyReleased

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
            txtHijos.setText("");
            txtEscolaridad.setText("");
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

    private void txtHijosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHijosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHijosActionPerformed

    private void txtEscolaridadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEscolaridadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEscolaridadActionPerformed

    private void txtConductaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtConductaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtConductaKeyReleased

    private void txtCieKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCieKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtConducta.requestFocus();
        }
    }//GEN-LAST:event_txtCieKeyReleased

    private void txtdirigidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdirigidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdirigidoActionPerformed

    private void txtdirigidoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdirigidoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_TAB || evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtId.requestFocus();
        }
    }//GEN-LAST:event_txtdirigidoKeyReleased

    private void btnReimprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReimprimirActionPerformed
        infReimpresionExamenes reimpresion = new infReimpresionExamenes();
        reimpresion.show();
        reimpresion.setNombre(txtNombreCompleto.getText());
        reimpresion.cargarRegistros(txtIdSistema.getText(), "certificadoEstudiante", instancias);
    }//GEN-LAST:event_btnReimprimirActionPerformed

    private void btnGuardar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar1ActionPerformed
        if (metodos.msgPregunta(null, "¿Desea continuar? ") == 0) {

            String conse = "CERTIFICADOMEDEST-" + instancias.getSql().getNumConsecutivo("CERTIFICADOMEDEST")[0];
            ndCertificadoMedico nodo = instancias.getSql().getDatosCertificadoMedico(conse, "Estudiante");

            if (nodo.getId() != null) {
                if (!instancias.getSql().aumentarConsecutivo("CERTIFICADOMEDEST", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("CERTIFICADOMEDEST")[0]) + 1)) {
                    metodos.msgError(null, "Hubo un problema al aumentar el consecutivo del certificado");
                }

                conse = "CERTIFICADOMEDEST-" + instancias.getSql().getNumConsecutivo("CERTIFICADOMEDEST")[0];
                while (instancias.getSql().getDatosRemision(conse).getId() != null) {

                    if (!instancias.getSql().aumentarConsecutivo("CERTIFICADOMEDEST", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("CERTIFICADOMEDEST")[0]) + 1)) {
                        metodos.msgError(null, "Hubo un problema al aumentar el consecutivo del certificado");
                    }
                    conse = "CERTIFICADOMEDEST-" + instancias.getSql().getNumConsecutivo("CERTIFICADOMEDEST")[0];
                }
            }

            String history = "";

            try {
                history = historia.getId();
            } catch (Exception e) {
                history = "";
            }

            if (history == null) {
                history = "";
            }

            Object[] vector = {conse, history, "", txtdirigido.getText(), "", "", "", "", "", "", txtPeso.getText(), txtEstatura.getText(),
                txtFc.getText(), txtFr.getText(), txtPa.getText(), "", txtAbdominal.getText(), txtimc.getText(), "", txtOjos.getText(),
                txtVisual.getText(), txtOidos.getText(), txtNariz.getText(), txtBoca.getText(), "", txtCardiopulmonar.getText(), "",
                txtAbdomen.getText(), txtGenito.getText(), "", txtExtremidades.getText(),
                "", txtPiel.getText(), "", txtCie.getText(), txtConducta.getText(), "", "", "", "", "", instancias.getUsuario(),
                metodos.fechaConsulta(metodosGenerales.fecha()), "", "", "", "", "", txtFamiliares.getText(), txtHabitos.getText(), ""};

            nodo = metodos.llenarCertificadoMedico(vector);

            if (!instancias.getSql().agregarCertificadoMedico(nodo, "Estudiante")) {
                metodos.msgError(null, "Error al guardar la Consulta");
                return;
            } else {
                metodos.msgExito(null, "Certificado generado con exito!");
            }

            if (!instancias.getSql().aumentarConsecutivo("CERTIFICADOMEDEST", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("CERTIFICADOMEDEST")[0]) + 1)) {
                metodos.msgError(null, "Hubo un problema al guardar en el consecutivo del certificado");
                return;
            }

            instancias.getReporte().verCertificadoMedico(instancias.getInformacionEmpresaCompleto(), conse, txtEdad.getText(), "Estudiante");
            Limpiar();
        }
    }//GEN-LAST:event_btnGuardar1ActionPerformed

    private void btnBuscTercerosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscTercerosActionPerformed
        ventanaTerceros("");
    }//GEN-LAST:event_btnBuscTercerosActionPerformed

    private void txtIdSistemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdSistemaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdSistemaActionPerformed

    private void txtIdSistemaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdSistemaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdSistemaKeyReleased

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        Limpiar();
    }//GEN-LAST:event_btnNuevoActionPerformed

    public void reimprimir(String consecutivo) {
//        ndCertificadoMedico nodo = instancias.getSql().getDatosCertificadoMedico(consecutivo, "Estudiante");
        instancias.getReporte().verCertificadoMedico(instancias.getInformacionEmpresa(), consecutivo, txtEdad.getText(), "Estudiante");
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
        txtdirigido.setText("");
        txtIdSistema.setText("");
        txtPeso.setText("");
        txtEstatura.setText("");
        txtFc.setText("");
        txtFr.setText("");
        txtPa.setText("");
        txtAbdominal.setText("");
        txtVisual.setText("");
        txtCardiopulmonar.setText("");
        txtAbdomen.setText("");
        txtPiel.setText("");
        txtExtremidades.setText("");
        txtConducta.setText("");
        txtPeso.setText("");
        txtEstatura.setText("");
        txtFc.setText("");
        txtFr.setText("");
        txtPa.setText("");
        txtAbdominal.setText("");
        txtOjos.setText("");
        txtVisual.setText("");
        txtOidos.setText("");
        txtNariz.setText("");
        txtBoca.setText("");
        txtCardiopulmonar.setText("");
        txtAbdomen.setText("");
        txtGenito.setText("");
        txtExtremidades.setText("");
        txtPiel.setText("");
        txtConducta.setText("");

        txtId.setText("");
        txtNombreCompleto.setText("");
        txtFechaNacimiento.setText("");
        txtlugar.setText("");
        txtEdad.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
        txtEstadoCivil.setText("");
        txtHijos.setText("");
        txtEscolaridad.setText("");

        txtCie.setText("");
        txtFamiliares.setText("");
        txtHabitos.setText("");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscTerceros;
    private javax.swing.JButton btnGuardar1;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnReimprimir;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup grupoTipo;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane24;
    private javax.swing.JScrollPane jScrollPane26;
    private javax.swing.JScrollPane jScrollPane27;
    private javax.swing.JScrollPane jScrollPane34;
    private javax.swing.JScrollPane jScrollPane37;
    private javax.swing.JScrollPane jScrollPane46;
    private javax.swing.JScrollPane jScrollPane56;
    private javax.swing.JScrollPane jScrollPane57;
    private javax.swing.JScrollPane jScrollPane58;
    private javax.swing.JScrollPane jScrollPane59;
    private javax.swing.JScrollPane jScrollPane61;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lbDireccion18;
    private javax.swing.JLabel lbDireccion19;
    private javax.swing.JLabel lbDireccion22;
    private javax.swing.JLabel lbDireccion24;
    private javax.swing.JLabel lbDireccion28;
    private javax.swing.JLabel lbDireccion29;
    private javax.swing.JLabel lbDireccion30;
    private javax.swing.JLabel lbDireccion31;
    private javax.swing.JLabel lbDireccion32;
    private javax.swing.JLabel lbDireccion34;
    private javax.swing.JLabel lbDireccion35;
    private javax.swing.JLabel lbDireccion37;
    private javax.swing.JLabel lbDireccion38;
    private javax.swing.JLabel lbDireccion39;
    private javax.swing.JLabel lbDireccion44;
    private javax.swing.JLabel lbDireccion55;
    private javax.swing.JLabel lbDireccion58;
    private javax.swing.JLabel lblTipoId;
    private javax.swing.JLabel lblTipoId1;
    private javax.swing.JScrollPane scrFormulario;
    private javax.swing.JTextArea txtAbdomen;
    private javax.swing.JTextField txtAbdominal;
    private javax.swing.JTextArea txtBoca;
    private javax.swing.JTextArea txtCardiopulmonar;
    private javax.swing.JTextArea txtCie;
    private javax.swing.JTextArea txtConducta;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtEdad;
    private javax.swing.JTextField txtEscolaridad;
    private javax.swing.JTextField txtEstadoCivil;
    private javax.swing.JTextField txtEstatura;
    private javax.swing.JTextArea txtExtremidades;
    private javax.swing.JTextArea txtFamiliares;
    private javax.swing.JTextField txtFc;
    private javax.swing.JTextField txtFechaNacimiento;
    private javax.swing.JTextField txtFr;
    private javax.swing.JTextArea txtGenito;
    private javax.swing.JTextArea txtHabitos;
    private javax.swing.JTextField txtHijos;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtIdSistema;
    private javax.swing.JTextArea txtNariz;
    private javax.swing.JTextField txtNombreCompleto;
    private javax.swing.JTextArea txtOidos;
    private javax.swing.JTextArea txtOjos;
    private javax.swing.JTextArea txtPa;
    private javax.swing.JTextField txtPeso;
    private javax.swing.JTextArea txtPiel;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextArea txtVisual;
    private javax.swing.JTextField txtdirigido;
    private javax.swing.JTextField txtimc;
    private javax.swing.JTextField txtlugar;
    // End of variables declaration//GEN-END:variables
}
