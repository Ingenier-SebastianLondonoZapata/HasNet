package configuracion;

import clases.Instancias;
import clases.metodosGenerales;
import clases.msgPermisos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;

public class dlgPermisos extends javax.swing.JDialog {

    private metodosGenerales metodos;
    private Instancias instancias;
    DefaultTableModel modelo;
    DefaultTableModel modelo1;
    msgPermisos solicitudes;
    int fila = 0;
    Boolean desdeHilo = false;

    public dlgPermisos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);

        modelo = (DefaultTableModel) tblPendientes.getModel();
        modelo1 = (DefaultTableModel) tblHistorial.getModel();

        instancias = Instancias.getInstancias();
        metodos = new metodosGenerales();

        btnActualizarActionPerformed(null);
        this.getRootPane().registerKeyboardAction(accion("cerrar", this), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private ActionListener accion(final String opc, final JDialog ventana) {
        ActionListener a = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (opc) {
                    case "cerrar":
                        ventana.dispose();
                        break;
                }
            }
        };
        return a;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnActualizar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblPendientes = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        btnAceptar = new javax.swing.JButton();
        btnRechazar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtHora = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtTipo = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtNota = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtEstado = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblHistorial = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("CONFIGURACIONES");
        setUndecorated(true);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("SOLICITUDES PENDIENTES");

        btnActualizar.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/actualizar.png"))); // NOI18N
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        tblPendientes.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        tblPendientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Tipo", "Valor", "Nota", "Hora", "Fecha", "Usuario", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPendientes.setInheritsPopupMenu(true);
        tblPendientes.setRowHeight(22);
        tblPendientes.getTableHeader().setReorderingAllowed(false);
        tblPendientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPendientesMouseClicked(evt);
            }
        });
        tblPendientes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblPendientesKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tblPendientes);
        if (tblPendientes.getColumnModel().getColumnCount() > 0) {
            tblPendientes.getColumnModel().getColumn(0).setMinWidth(0);
            tblPendientes.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblPendientes.getColumnModel().getColumn(0).setMaxWidth(0);
            tblPendientes.getColumnModel().getColumn(1).setMinWidth(50);
            tblPendientes.getColumnModel().getColumn(1).setPreferredWidth(85);
            tblPendientes.getColumnModel().getColumn(1).setMaxWidth(100);
            tblPendientes.getColumnModel().getColumn(2).setMinWidth(30);
            tblPendientes.getColumnModel().getColumn(2).setPreferredWidth(40);
            tblPendientes.getColumnModel().getColumn(2).setMaxWidth(60);
            tblPendientes.getColumnModel().getColumn(3).setMinWidth(0);
            tblPendientes.getColumnModel().getColumn(3).setPreferredWidth(0);
            tblPendientes.getColumnModel().getColumn(3).setMaxWidth(0);
            tblPendientes.getColumnModel().getColumn(4).setMinWidth(55);
            tblPendientes.getColumnModel().getColumn(4).setPreferredWidth(55);
            tblPendientes.getColumnModel().getColumn(4).setMaxWidth(55);
            tblPendientes.getColumnModel().getColumn(5).setMinWidth(0);
            tblPendientes.getColumnModel().getColumn(5).setPreferredWidth(0);
            tblPendientes.getColumnModel().getColumn(5).setMaxWidth(0);
            tblPendientes.getColumnModel().getColumn(7).setMinWidth(0);
            tblPendientes.getColumnModel().getColumn(7).setPreferredWidth(0);
            tblPendientes.getColumnModel().getColumn(7).setMaxWidth(0);
        }

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detalle", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 0, 14))); // NOI18N

        btnAceptar.setBackground(new java.awt.Color(0, 204, 102));
        btnAceptar.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnAceptar.setText("ACEPTAR");
        btnAceptar.setBorder(null);
        btnAceptar.setEnabled(false);
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        btnRechazar.setBackground(new java.awt.Color(255, 153, 153));
        btnRechazar.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnRechazar.setText("RECHAZAR");
        btnRechazar.setBorder(null);
        btnRechazar.setEnabled(false);
        btnRechazar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRechazarActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel2.setText("HORA DE LA SOLICITUD:");

        txtHora.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtHora.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtHora.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtHora.setEnabled(false);

        jLabel3.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel3.setText("TIPO DE SOLICITUD:");

        txtTipo.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtTipo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTipo.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtTipo.setEnabled(false);

        jLabel4.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel4.setText("NOTA:");

        txtNota.setColumns(20);
        txtNota.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtNota.setRows(5);
        txtNota.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtNota.setEnabled(false);
        jScrollPane1.setViewportView(txtNota);

        jLabel5.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel5.setText("USUARIO:");

        txtUsuario.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtUsuario.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtUsuario.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtUsuario.setEnabled(false);

        jLabel6.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel6.setText("ESTADO:");

        txtEstado.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtEstado.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtEstado.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtEstado.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(197, Short.MAX_VALUE)
                .addComponent(btnRechazar, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(191, 191, 191))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtHora, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(45, 45, 45)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                                    .addComponent(txtEstado)))
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2)
                    .addComponent(txtHora)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtUsuario)))
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(txtTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAceptar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRechazar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Solicitudes", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        tblHistorial.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        tblHistorial.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Tipo", "Valor", "Nota", "Hora", "Fecha", "Usuario", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHistorial.setInheritsPopupMenu(true);
        tblHistorial.setRowHeight(22);
        tblHistorial.getTableHeader().setReorderingAllowed(false);
        tblHistorial.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHistorialMouseClicked(evt);
            }
        });
        tblHistorial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblHistorialKeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(tblHistorial);
        if (tblHistorial.getColumnModel().getColumnCount() > 0) {
            tblHistorial.getColumnModel().getColumn(0).setMinWidth(0);
            tblHistorial.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblHistorial.getColumnModel().getColumn(0).setMaxWidth(0);
            tblHistorial.getColumnModel().getColumn(1).setMinWidth(50);
            tblHistorial.getColumnModel().getColumn(1).setPreferredWidth(85);
            tblHistorial.getColumnModel().getColumn(1).setMaxWidth(100);
            tblHistorial.getColumnModel().getColumn(2).setMinWidth(30);
            tblHistorial.getColumnModel().getColumn(2).setPreferredWidth(40);
            tblHistorial.getColumnModel().getColumn(2).setMaxWidth(60);
            tblHistorial.getColumnModel().getColumn(4).setMinWidth(55);
            tblHistorial.getColumnModel().getColumn(4).setPreferredWidth(55);
            tblHistorial.getColumnModel().getColumn(4).setMaxWidth(55);
            tblHistorial.getColumnModel().getColumn(5).setMinWidth(0);
            tblHistorial.getColumnModel().getColumn(5).setPreferredWidth(0);
            tblHistorial.getColumnModel().getColumn(5).setMaxWidth(0);
            tblHistorial.getColumnModel().getColumn(6).setMinWidth(40);
            tblHistorial.getColumnModel().getColumn(6).setPreferredWidth(75);
            tblHistorial.getColumnModel().getColumn(6).setMaxWidth(90);
            tblHistorial.getColumnModel().getColumn(7).setMinWidth(140);
            tblHistorial.getColumnModel().getColumn(7).setPreferredWidth(140);
            tblHistorial.getColumnModel().getColumn(7).setMaxWidth(140);
        }

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 670, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 660, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Historial Diario", jPanel3);

        jLabel7.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("X");
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });

        txtId.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        txtId.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtId.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtId.setEnabled(false);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel7)
                .addGap(0, 0, 0)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void tblPendientesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblPendientesKeyReleased

    }//GEN-LAST:event_tblPendientesKeyReleased

    private void tblPendientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPendientesMouseClicked
        if (!desdeHilo) {
            fila = tblPendientes.getSelectedRow();
        }

        txtEstado.setText("");
        txtHora.setText("");
        txtTipo.setText("");
        txtUsuario.setText("");
        txtNota.setText("");
        txtId.setText("");
        btnAceptar.setEnabled(true);
        btnRechazar.setEnabled(true);

        txtId.setText(tblPendientes.getValueAt(fila, 0).toString());
        txtEstado.setText(tblPendientes.getValueAt(fila, 7).toString());
        txtHora.setText(tblPendientes.getValueAt(fila, 4).toString());
        txtTipo.setText(tblPendientes.getValueAt(fila, 1).toString());
        txtUsuario.setText(tblPendientes.getValueAt(fila, 6).toString());
        txtNota.setText(tblPendientes.getValueAt(fila, 3).toString());

        solicitudes = new msgPermisos(txtId.getText());
        solicitudes.start();

        desdeHilo = false;
    }//GEN-LAST:event_tblPendientesMouseClicked

    private void btnRechazarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRechazarActionPerformed
        instancias.getSql().cambiarEstadoGeneral("RECHAZADA", txtId.getText(), " bdPermisos");
        txtEstado.setText("");
        txtHora.setText("");
        txtTipo.setText("");
        txtUsuario.setText("");
        txtNota.setText("");
        txtId.setText("");

        btnAceptar.setEnabled(false);
        btnRechazar.setEnabled(false);
        metodos.msgExito(null, "Solicitud rechazada con exito.");
        btnActualizarActionPerformed(evt);
    }//GEN-LAST:event_btnRechazarActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        instancias.getSql().cambiarEstadoGeneral("ACEPTADA", txtId.getText(), " bdPermisos");
        txtEstado.setText("");
        txtHora.setText("");
        txtTipo.setText("");
        txtUsuario.setText("");
        txtNota.setText("");
        txtId.setText("");
        btnAceptar.setEnabled(false);
        btnRechazar.setEnabled(false);
        metodos.msgExito(null, "Solicitud aceptada con exito.");
        btnActualizarActionPerformed(evt);
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        while (tblHistorial.getRowCount() > 0) {
            modelo1.removeRow(0);
        }
        while (tblPendientes.getRowCount() > 0) {
            modelo.removeRow(0);
        }

        Object[][] pendientes1 = instancias.getSql().getPermisosPendientes(" where (((fecha) >= '" + metodos.fechaConsulta(metodosGenerales.fecha())
                + "') And ((fecha)<= '" + metodos.fechaConsulta(metodosGenerales.fecha()) + "')) ");
        Object[][] pendientes = instancias.getSql().getPermisosPendientes(" where estado = 'PENDIENTE'; ");

        for (Object[] pend : pendientes) {
            String hora = pend[5].toString().substring(0, pend[5].toString().length() - 3);
            modelo.addRow(new Object[]{pend[0], pend[1], pend[2], pend[3], hora, pend[5].toString(), pend[6], pend[7]});
        }

        for (Object[] pend : pendientes1) {
            String hora = pend[5].toString().substring(0, pend[5].toString().length() - 3);
            modelo1.addRow(new Object[]{pend[0], pend[1], pend[2], pend[3], hora, pend[5].toString(), pend[6], pend[7]});
        }

        txtEstado.setText("");
        txtHora.setText("");
        txtTipo.setText("");
        txtUsuario.setText("");
        txtNota.setText("");
        txtId.setText("");
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        this.dispose();
    }//GEN-LAST:event_jLabel7MouseClicked

    private void tblHistorialMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHistorialMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblHistorialMouseClicked

    private void tblHistorialKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblHistorialKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tblHistorialKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnRechazar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tblHistorial;
    private javax.swing.JTable tblPendientes;
    private javax.swing.JTextField txtEstado;
    private javax.swing.JTextField txtHora;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextArea txtNota;
    private javax.swing.JTextField txtTipo;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
