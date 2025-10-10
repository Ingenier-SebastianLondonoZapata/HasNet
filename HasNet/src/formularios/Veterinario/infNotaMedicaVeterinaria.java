package formularios.Veterinario;

import clases.Instancias;
import clases.Medico.ndNotaEnfermeria;
import Utilidades.BaseDatos.SQL;
//import clases.Ventas.ndNotaEnfermeria;
import clases.metodosGenerales;
import Modelo.Terceros.ModeloContacto;
import formularios.terceros.buscClientes;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import javax.swing.JComponent;
import javax.swing.table.DefaultTableModel;

public class infNotaMedicaVeterinaria extends javax.swing.JInternalFrame {

    metodosGenerales metodos = new metodosGenerales();
    private Instancias instancias;
    private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension dimBarra = null;
    DefaultTableModel modelo;

    public infNotaMedicaVeterinaria() {
        initComponents();

        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
        dimBarra = Barra.getPreferredSize();
        Barra.setSize(0, 0);
        Barra.setPreferredSize(new Dimension(0, 0));
        setBorder(null);
        repaint();

        instancias = Instancias.getInstancias();
        txtId.setText("NOTAP-" + instancias.getSql().getNumConsecutivo("NOTAP")[0]);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrFormulario = new javax.swing.JScrollPane();
        pnlFormulario = new javax.swing.JPanel();
        lbNit10 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        lbRazon6 = new javax.swing.JLabel();
        lbRazon4 = new javax.swing.JLabel();
        lbRazon2 = new javax.swing.JLabel();
        txtNacimiento = new javax.swing.JTextField();
        txtTipoDocu = new javax.swing.JTextField();
        txtNombres = new javax.swing.JTextField();
        txtIdentificacion = new javax.swing.JTextField();
        lbRazon5 = new javax.swing.JLabel();
        txtEdad = new javax.swing.JTextField();
        lbRazon1 = new javax.swing.JLabel();
        txtSexo = new javax.swing.JTextField();
        lbRazon3 = new javax.swing.JLabel();
        lbRazon7 = new javax.swing.JLabel();
        txtEstado = new javax.swing.JTextField();
        lbRazon9 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        btnBuscTerceros = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtInfo = new javax.swing.JTextArea();
        jComboBox1 = new javax.swing.JComboBox();
        btnGuardar1 = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();

        setTitle("Factura");

        pnlFormulario.setBackground(new java.awt.Color(255, 255, 255));

        lbNit10.setFont(new java.awt.Font("Bell MT", 1, 36)); // NOI18N
        lbNit10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit10.setText("NOTA DE ENFERMERIA");

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        lbRazon6.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbRazon6.setText("Estado Civil:");

        lbRazon4.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbRazon4.setText("Sexo:");

        lbRazon2.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbRazon2.setText("Tipo De Documento:");

        txtNacimiento.setEditable(false);
        txtNacimiento.setName("Fecha De Nacimiento"); // NOI18N
        txtNacimiento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNacimientoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNacimientoKeyTyped(evt);
            }
        });

        txtTipoDocu.setEditable(false);
        txtTipoDocu.setName("Tipo De Documento"); // NOI18N
        txtTipoDocu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTipoDocuKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTipoDocuKeyTyped(evt);
            }
        });

        txtNombres.setEditable(false);
        txtNombres.setName("Nombre Y Apellidos"); // NOI18N
        txtNombres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombresActionPerformed(evt);
            }
        });
        txtNombres.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombresKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombresKeyTyped(evt);
            }
        });

        txtIdentificacion.setName("No. Identificacion"); // NOI18N
        txtIdentificacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIdentificacionKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIdentificacionKeyTyped(evt);
            }
        });

        lbRazon5.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbRazon5.setText("Fecha De Nac:");

        txtEdad.setEditable(false);
        txtEdad.setName("Edad"); // NOI18N
        txtEdad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEdadKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEdadKeyTyped(evt);
            }
        });

        lbRazon1.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbRazon1.setText("No. Identificación:");

        txtSexo.setEditable(false);
        txtSexo.setName("Sexo"); // NOI18N
        txtSexo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSexoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSexoKeyTyped(evt);
            }
        });

        lbRazon3.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbRazon3.setText("Nombre Y Apellidos:");

        lbRazon7.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbRazon7.setText("Edad:");

        txtEstado.setEditable(false);
        txtEstado.setName("Estado Civil"); // NOI18N
        txtEstado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEstadoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEstadoKeyTyped(evt);
            }
        });

        lbRazon9.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbRazon9.setText("NOTA No.:");

        txtId.setEditable(false);
        txtId.setName("Edad"); // NOI18N
        txtId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIdKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIdKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lbRazon6, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                                    .addComponent(lbRazon7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(5, 5, 5)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtEstado)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                                .addComponent(lbRazon2, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)
                                .addComponent(txtTipoDocu, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbRazon9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(lbRazon5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                                .addComponent(lbRazon4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(lbRazon1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(txtIdentificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(lbRazon3, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 544, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbRazon1)
                    .addComponent(txtIdentificacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbRazon3)
                    .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbRazon2)
                    .addComponent(txtTipoDocu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbRazon4))
                .addGap(10, 10, 10)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbRazon6)
                    .addComponent(lbRazon5)
                    .addComponent(txtNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbRazon7)
                    .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbRazon9))
                .addGap(10, 10, 10))
        );

        btnBuscTerceros.setBackground(new java.awt.Color(247, 196, 69));
        btnBuscTerceros.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnBuscTerceros.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscTerceros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cliente.png"))); // NOI18N
        btnBuscTerceros.setText("TERCEROS");
        btnBuscTerceros.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscTerceros.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnBuscTerceros.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnBuscTerceros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscTercerosActionPerformed(evt);
            }
        });

        btnNuevo.setBackground(new java.awt.Color(255, 204, 51));
        btnNuevo.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnNuevo.setForeground(new java.awt.Color(255, 255, 255));
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/nuevo.png"))); // NOI18N
        btnNuevo.setText("NUEVO     ");
        btnNuevo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNuevo.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnNuevo.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnGuardar.setBackground(new java.awt.Color(0, 153, 153));
        btnGuardar.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
        btnGuardar.setText("GUARDAR   ");
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar.setEnabled(false);
        btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnGuardar.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        btnGuardar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                btnGuardarKeyReleased(evt);
            }
        });

        jLabel5.setBackground(new java.awt.Color(102, 153, 0));
        jLabel5.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("INFORMACIÓN NOTA DE ENFERMERIA");
        jLabel5.setOpaque(true);

        txtInfo.setColumns(20);
        txtInfo.setLineWrap(true);
        txtInfo.setRows(5);
        txtInfo.setName("información de sesión"); // NOI18N
        jScrollPane1.setViewportView(txtInfo);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "HOY-NUEVO" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        btnGuardar1.setBackground(new java.awt.Color(0, 153, 153));
        btnGuardar1.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnGuardar1.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/imprimir.png"))); // NOI18N
        btnGuardar1.setText("IMPRIMIR");
        btnGuardar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnGuardar1.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnGuardar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardar1ActionPerformed(evt);
            }
        });
        btnGuardar1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                btnGuardar1KeyReleased(evt);
            }
        });

        btnModificar.setBackground(new java.awt.Color(0, 0, 0));
        btnModificar.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnModificar.setForeground(new java.awt.Color(255, 255, 255));
        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/actualizar.png"))); // NOI18N
        btnModificar.setText("MODIFICAR  ");
        btnModificar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnModificar.setEnabled(false);
        btnModificar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnModificar.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        btnModificar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                btnModificarKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout pnlFormularioLayout = new javax.swing.GroupLayout(pnlFormulario);
        pnlFormulario.setLayout(pnlFormularioLayout);
        pnlFormularioLayout.setHorizontalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlFormularioLayout.createSequentialGroup()
                        .addComponent(btnBuscTerceros)
                        .addGap(15, 15, 15)
                        .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(btnModificar)
                        .addGap(15, 15, 15)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(btnGuardar1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 725, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 725, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(15, Short.MAX_VALUE))
            .addComponent(lbNit10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlFormularioLayout.setVerticalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addComponent(lbNit10, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnModificar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnNuevo)
                        .addComponent(btnGuardar)
                        .addComponent(btnBuscTerceros)
                        .addComponent(btnGuardar1)))
                .addGap(20, 20, 20))
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
            .addComponent(scrFormulario)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtTipoDocuKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTipoDocuKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTipoDocuKeyReleased

    private void txtTipoDocuKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTipoDocuKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTipoDocuKeyTyped

    private void txtNombresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombresActionPerformed

    private void txtNombresKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombresKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombresKeyReleased

    private void txtNombresKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombresKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombresKeyTyped

    private void txtIdentificacionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdentificacionKeyReleased

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            nuevaSesion(txtIdentificacion.getText());
        } else {
            limpiar();
        }

    }//GEN-LAST:event_txtIdentificacionKeyReleased

    private void txtIdentificacionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdentificacionKeyTyped

    }//GEN-LAST:event_txtIdentificacionKeyTyped

    private void txtEdadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEdadKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEdadKeyReleased

    private void txtEdadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEdadKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEdadKeyTyped

    private void txtSexoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSexoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSexoKeyReleased

    private void txtSexoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSexoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSexoKeyTyped

    private void txtEstadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEstadoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEstadoKeyReleased

    private void txtEstadoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEstadoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEstadoKeyTyped

    private void btnBuscTercerosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscTercerosActionPerformed
        ventanaTerceros("");
    }//GEN-LAST:event_btnBuscTercerosActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        btnGuardar.setEnabled(true);
        txtInfo.setEditable(true);
        limpiar();
        txtIdentificacion.setText("");
        txtInfo.requestFocus();
        txtId.setText("NOTAP-" + instancias.getSql().getNumConsecutivo("NOTAP")[0]);
        txtInfo.setText("");

        jComboBox1.removeAllItems();
        jComboBox1.addItem("HOY-NUEVO");
        jComboBox1.setSelectedIndex(0);
    }//GEN-LAST:event_btnNuevoActionPerformed

    public void limpiar() {
        txtEdad.setText("");
        txtEstado.setText("");
        txtInfo.setText("");
        txtNacimiento.setText("");
        txtNombres.setText("");
        txtSexo.setText("");
        txtTipoDocu.setText("");
    }

    public void nuevaSesion(String nit) {

        ModeloContacto Tercero = instancias.getSql().getDatosTercero(nit);

        if (Tercero.getId() != null) {

            txtIdentificacion.setText(Tercero.getId());
            txtTipoDocu.setText(Tercero.getTipo());
            txtNombres.setText(Tercero.getpNombre() + " " + Tercero.getsNombre() + " " + Tercero.getpApellido() + " " + Tercero.getsApellido());
            txtSexo.setText(Tercero.getSexo());
            txtEstado.setText(Tercero.getEstado());
            txtNacimiento.setText(metodos.fecha(Tercero.getNacimiento()));
            try {
                txtEdad.setText(metodosGenerales.calcularEdad(metodos.fecha(Tercero.getNacimiento())) + "");
            } catch (Exception e) {
            }

//        txtHistoria.setText(nodo.getId());
            actualizarCMB(Tercero.getId());
            txtInfo.requestFocus();

            return;
        }

        ventanaTerceros(nit);

    }

    private void actualizarCMB(String id) {
        Object[][] sesion = instancias.getSql().getNotasEnfermeria(id);
        jComboBox1.removeAllItems();
        jComboBox1.addItem("HOY-NUEVO");
        try {
            for (int i = 0; i < sesion.length; i++) {
                jComboBox1.addItem(sesion[i][0].toString().replace("NOTAP-", "") + "-" + sesion[i][1]);
            }
        } catch (Exception e) {
        }
        jComboBox1.setSelectedIndex(0);
    }

    public void ventanaTerceros(String nit) {
        buscClientes buscar = new buscClientes(instancias.getMenu(), rootPaneCheckingEnabled, false, null, "");
        buscar.setLocationRelativeTo(null);
        instancias.setBusClientes(buscar);
        instancias.setCampoActual(txtIdentificacion);
        txtIdentificacion.requestFocus();
        buscar.noEncontrado(nit);
        buscar.show();
    }

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        Object[] campos = {txtId, txtIdentificacion};
        String faltantes = metodos.camposVacios(campos);
        Object[] campos2 = {txtInfo};
        String faltantes2 = metodos.camposVacios(campos2);
        if (!faltantes.equals("")) {
            metodos.msgAdvertencia(this, "No puede continuar, faltan los siguientes campos: " + faltantes);
            return;
        } else if (!faltantes2.equals("")) {
            metodos.msgAdvertencia(this, "Faltan los siguientes campos: " + faltantes2);
        }
        if (metodos.msgPregunta(this, "¿Desea continuar?") == 0) {

            try {
                String id = "NOTAP-" + instancias.getSql().getNumConsecutivo("NOTAP")[0];
                ndNotaEnfermeria nodo = instancias.getSql().getDatosNotasEnfermeria(txtId.getText());
                if (nodo.getId() != null) {
                    metodos.msgError(this, "Esta nota ya existe");
                    txtId.requestFocus();
                    return;
                }
                Object[] vector = {id, txtIdentificacion.getText(), metodosGenerales.fecha(), txtInfo.getText()};
                nodo = metodos.llenarNotaEnfermeria(vector);
                if (!instancias.getSql().agregarNotasEnfermeria(nodo)) {
                    metodos.msgError(this, "Hubo un problema al guardar la nota");
                    return;
                }
                txtId.setText("NOTAP-" + instancias.getSql().getNumConsecutivo("NOTAP")[0]);
                if (!instancias.getSql().aumentarConsecutivo("NOTAP", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("NOTAP")[0]) + 1)) {
                    metodos.msgError(this, "Hubo un problema al guardar en el consecutivo de la nota");
                    return;
                }

                metodos.msgExito(this, "Nota registrada con éxito");
                if (metodos.msgPregunta(this, "¿Desea imprimir?") == 0) {

                    instancias.getReporte().verNotaEnfermeria(id, instancias.getInformacionEmpresa(), txtIdentificacion.getText(), txtTipoDocu.getText(),
                            txtNombres.getText(), txtSexo.getText(), txtEstado.getText(), txtEdad.getText() + " Años", txtNacimiento.getText(), false);

                }
                actualizarCMB(txtIdentificacion.getText());
                btnNuevoActionPerformed(evt);
            } catch (Exception e) {
                metodos.msgError(this, "Hubo un problema al guardar la nota");
            }
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnGuardarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnGuardarKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnGuardarActionPerformed(null);
        }
    }//GEN-LAST:event_btnGuardarKeyReleased

    private void txtIdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdKeyReleased

    private void txtIdKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdKeyTyped

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        if (jComboBox1.getSelectedIndex() == 0) {
            txtInfo.setText("");
            btnGuardar.setEnabled(true);
            btnModificar.setEnabled(false);

        } else {
            try {
                String id = jComboBox1.getSelectedItem().toString().split("-")[0];
                ndNotaEnfermeria nodo = instancias.getSql().getDatosNotasEnfermeria("NOTAP-" + id);
                txtInfo.setText(nodo.getInformacion());
                btnModificar.setEnabled(true);
                btnGuardar.setEnabled(false);

            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void btnGuardar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar1ActionPerformed
        if (!txtIdentificacion.getText().equals("")) {
            infReimpresiones impresion = new infReimpresiones();
            impresion.cargarRegistros(txtIdentificacion.getText(), "nota", instancias);
            impresion.setVisible(true);
        }
    }//GEN-LAST:event_btnGuardar1ActionPerformed

    private void btnGuardar1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnGuardar1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGuardar1KeyReleased

    private void txtNacimientoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNacimientoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNacimientoKeyTyped

    private void txtNacimientoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNacimientoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNacimientoKeyReleased

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        Object[] campos = {txtId};
        String faltantes = metodos.camposVacios(campos);
        if (!faltantes.equals("")) {
            metodos.msgAdvertencia(this, "No puede continuar, faltan los siguientes campos: " + faltantes);
            return;
        }
        if (metodos.msgPregunta(this, "¿Desea continuar?") == 0) {

//            try {
            String id = "NOTAP-" + jComboBox1.getSelectedItem().toString().substring(0, 1);
            System.out.println("id " + id);

            Object[] vector = {id, txtIdentificacion.getText(), metodosGenerales.fecha(), txtInfo.getText()};
            ndNotaEnfermeria nodo = metodos.llenarNotaEnfermeria(vector);
            if (!instancias.getSql().modificarNotasEnfermeria(nodo)) {
                metodos.msgError(this, "Hubo un problema al modificar la nota de proceso");
                return;
            }
            metodos.msgExito(this, "Nota de proceso modificada con éxito");

            //btnNuevoActionPerformed(evt);
            if (metodos.msgPregunta(this, "¿Desea imprimir?") == 0) {
                instancias.getReporte().verNotaEnfermeria(id, instancias.getInformacionEmpresa(), txtIdentificacion.getText(), txtTipoDocu.getText(),
                        txtNombres.getText(), txtSexo.getText(), txtEstado.getText(), txtEdad.getText() + " Años", txtNacimiento.getText(), false);
            }
            jComboBox1.setSelectedIndex(0);
//            } catch (Exception e) {
//                metodos.msgError(this, "Hubo un problema al modificar la nota de proceso");
//            }
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnModificarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnModificarKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnModificarActionPerformed(null);
        }
    }//GEN-LAST:event_btnModificarKeyReleased
    public void imprimir(String id) {
        if (id.equals("TODOS")) {
        } else {
            instancias.getReporte().verNotaEnfermeria(id, instancias.getInformacionEmpresa(), txtIdentificacion.getText(), txtTipoDocu.getText(),
                    txtNombres.getText(), txtSexo.getText(), txtEstado.getText(), txtEdad.getText() + " Años", txtNacimiento.getText(), false);
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscTerceros;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnGuardar1;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbNit10;
    private javax.swing.JLabel lbRazon1;
    private javax.swing.JLabel lbRazon2;
    private javax.swing.JLabel lbRazon3;
    private javax.swing.JLabel lbRazon4;
    private javax.swing.JLabel lbRazon5;
    private javax.swing.JLabel lbRazon6;
    private javax.swing.JLabel lbRazon7;
    private javax.swing.JLabel lbRazon9;
    private javax.swing.JPanel pnlFormulario;
    private javax.swing.JScrollPane scrFormulario;
    private javax.swing.JTextField txtEdad;
    private javax.swing.JTextField txtEstado;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtIdentificacion;
    private javax.swing.JTextArea txtInfo;
    private javax.swing.JTextField txtNacimiento;
    private javax.swing.JTextField txtNombres;
    private javax.swing.JTextField txtSexo;
    private javax.swing.JTextField txtTipoDocu;
    // End of variables declaration//GEN-END:variables
}
