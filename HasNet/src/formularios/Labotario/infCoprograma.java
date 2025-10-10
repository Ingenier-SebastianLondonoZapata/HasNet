package formularios.Labotario;

import formularios.Medico.*;
import clases.Instancias;
import clases.Medico.ndNotaEnfermeria;
import Utilidades.BaseDatos.SQL;
import clases.metodosGenerales;
import Modelo.Terceros.ModeloContacto;
import formularios.terceros.buscClientes;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;

public class infCoprograma extends javax.swing.JInternalFrame {

    metodosGenerales metodos = new metodosGenerales();
    private Instancias instancias;
    private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension dimBarra = null;
    DefaultTableModel modelo;
    SQL sql= new SQL();

    public infCoprograma() {
        initComponents();

        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
        dimBarra = Barra.getPreferredSize();
        Barra.setSize(0, 0);
        Barra.setPreferredSize(new Dimension(0, 0));
        setBorder(null);
        repaint();

        instancias = Instancias.getInstancias();
        txtEdad.setText("NOTAP-" + instancias.getSql().getNumConsecutivo("NOTAP")[0]);
        pnlFormulario.registerKeyboardAction(accion("guardar"), "guardar", KeyStroke.getKeyStroke(KeyEvent.VK_G, Event.CTRL_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
        pnlFormulario.registerKeyboardAction(accion("limpiar"), "limpiar", KeyStroke.getKeyStroke(KeyEvent.VK_L, Event.CTRL_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
        pnlFormulario.registerKeyboardAction(accion("modificar"), "modificar", KeyStroke.getKeyStroke(KeyEvent.VK_M, Event.CTRL_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
    }

    private ActionListener accion(final String opc) {
        ActionListener a = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (opc) {
                    case "limpiar":
                        if ((btnLimpiar.isEnabled())) {
                            btnLimpiarActionPerformed(null);
                        }
                        break;
                    case "guardar":
                        if ((btnGuardar.isEnabled()) && (btnGuardar.isVisible())) {
                            btnGuardarActionPerformed(null);
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

        scrFormulario = new javax.swing.JScrollPane();
        pnlFormulario = new javax.swing.JPanel();
        lbNit10 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        lbRazon6 = new javax.swing.JLabel();
        lbRazon4 = new javax.swing.JLabel();
        lbRazon2 = new javax.swing.JLabel();
        txtRemitido = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        txtNombres = new javax.swing.JTextField();
        txtIdentificacion = new javax.swing.JTextField();
        lbRazon5 = new javax.swing.JLabel();
        txtNumeroOrden = new javax.swing.JTextField();
        lbRazon1 = new javax.swing.JLabel();
        txtEps = new javax.swing.JTextField();
        lbRazon3 = new javax.swing.JLabel();
        lbRazon7 = new javax.swing.JLabel();
        txtSexo = new javax.swing.JTextField();
        lbRazon9 = new javax.swing.JLabel();
        txtEdad = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        btnImprimir = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtTecnica = new javax.swing.JTextArea();
        lbNit15 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtObservaciones = new javax.swing.JTextArea();
        lbNit16 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblTabla1 = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblTabla2 = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblTabla3 = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();

        setTitle("Factura");

        pnlFormulario.setBackground(new java.awt.Color(255, 255, 255));

        lbNit10.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        lbNit10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit10.setText("COPROGRAMA");

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        lbRazon6.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbRazon6.setText("Sexo:");

        lbRazon4.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbRazon4.setText("EPS:");

        lbRazon2.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbRazon2.setText("Telefono:");

        txtRemitido.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtRemitido.setName("Fecha De Nacimiento"); // NOI18N
        txtRemitido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtRemitidoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRemitidoKeyTyped(evt);
            }
        });

        txtTelefono.setEditable(false);
        txtTelefono.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtTelefono.setName("Tipo De Documento"); // NOI18N
        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyTyped(evt);
            }
        });

        txtNombres.setEditable(false);
        txtNombres.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
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

        txtIdentificacion.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtIdentificacion.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtIdentificacion.setEnabled(false);
        txtIdentificacion.setName("No. Identificacion"); // NOI18N
        txtIdentificacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIdentificacionKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIdentificacionKeyTyped(evt);
            }
        });

        lbRazon5.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbRazon5.setText("Remitido por:");

        txtNumeroOrden.setEditable(false);
        txtNumeroOrden.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtNumeroOrden.setName("Edad"); // NOI18N
        txtNumeroOrden.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNumeroOrdenActionPerformed(evt);
            }
        });
        txtNumeroOrden.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNumeroOrdenKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNumeroOrdenKeyTyped(evt);
            }
        });

        lbRazon1.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbRazon1.setText("No. Identificación:");

        txtEps.setEditable(false);
        txtEps.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtEps.setName("Sexo"); // NOI18N
        txtEps.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEpsKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEpsKeyTyped(evt);
            }
        });

        lbRazon3.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbRazon3.setText("Paciente:");

        lbRazon7.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbRazon7.setText("N. Registro:");

        txtSexo.setEditable(false);
        txtSexo.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtSexo.setName("Estado Civil"); // NOI18N
        txtSexo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSexoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSexoKeyTyped(evt);
            }
        });

        lbRazon9.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbRazon9.setText("Edad");

        txtEdad.setEditable(false);
        txtEdad.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtEdad.setName("Edad"); // NOI18N
        txtEdad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEdadKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEdadKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(lbRazon5, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtRemitido))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lbRazon2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbRazon7, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNumeroOrden))
                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel11Layout.createSequentialGroup()
                                    .addComponent(lbRazon1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtIdentificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(txtTelefono, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbRazon3, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                            .addComponent(lbRazon4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbRazon9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNombres)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtEps, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                                    .addComponent(txtEdad))
                                .addGap(18, 18, 18)
                                .addComponent(lbRazon6, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSexo)))))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNombres)
                    .addComponent(lbRazon3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtIdentificacion)
                    .addComponent(lbRazon1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(lbRazon2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbRazon7)
                            .addComponent(lbRazon9)
                            .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNumeroOrden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbRazon4)
                        .addComponent(txtEps, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbRazon6)
                        .addComponent(txtSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(5, 5, 5)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbRazon5)
                    .addComponent(txtRemitido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39))
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        btnImprimir.setBackground(new java.awt.Color(0, 153, 153));
        btnImprimir.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnImprimir.setForeground(new java.awt.Color(255, 255, 255));
        btnImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/imprimir.png"))); // NOI18N
        btnImprimir.setText("RE-IMPRIMIR");
        btnImprimir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnImprimir.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnImprimir.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });
        btnImprimir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                btnImprimirKeyReleased(evt);
            }
        });

        btnGuardar.setBackground(new java.awt.Color(0, 153, 153));
        btnGuardar.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
        btnGuardar.setText("GUARDAR   ");
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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

        btnLimpiar.setBackground(new java.awt.Color(0, 153, 153));
        btnLimpiar.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnLimpiar.setForeground(new java.awt.Color(255, 255, 255));
        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/limpiar.png"))); // NOI18N
        btnLimpiar.setText("LIMPIAR");
        btnLimpiar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLimpiar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnLimpiar.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        btnLimpiar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                btnLimpiarKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(274, Short.MAX_VALUE)
                .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(btnImprimir)
                .addGap(228, 228, 228))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnImprimir)
                    .addComponent(btnLimpiar))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        txtTecnica.setColumns(20);
        txtTecnica.setRows(3);
        jScrollPane2.setViewportView(txtTecnica);

        lbNit15.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbNit15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit15.setText("TECNICA");

        txtObservaciones.setColumns(20);
        txtObservaciones.setRows(3);
        jScrollPane3.setViewportView(txtObservaciones);

        lbNit16.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbNit16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit16.setText("OBSERVACIONES");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addComponent(lbNit15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 452, Short.MAX_VALUE)
                    .addComponent(lbNit16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lbNit16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lbNit15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tblTabla1.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        tblTabla1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"CONSISTENCIA", null},
                {"MOCO", null},
                {"FLORA BACTERIANA", null},
                {"ALMIDONES", null},
                {"FIBRAS MUSCULARES", null},
                {"GOTAS DE GRASA", null},
                {"CELULAS VEGETALES", null},
                {"ESPORAS DE HONGO", null},
                {"LEUCOCITOS", null},
                {"ERITROCITOS", null}
            },
            new String [] {
                "", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTabla1.setRowHeight(25);
        tblTabla1.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(tblTabla1);
        if (tblTabla1.getColumnModel().getColumnCount() > 0) {
            tblTabla1.getColumnModel().getColumn(0).setMinWidth(250);
            tblTabla1.getColumnModel().getColumn(0).setPreferredWidth(250);
            tblTabla1.getColumnModel().getColumn(0).setMaxWidth(250);
        }

        tblTabla2.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        tblTabla2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"E. HISTOLITICA", null, null},
                {"E. COLI", null, null},
                {"E. NANA", null, null},
                {"LODAMOEBA B", null, null},
                {"GIARDIA L", null, null},
                {"TRICHOMONA H", null, null},
                {"CHILOMASTIX M", null, null},
                {"BALANTIDIUM COLI", null, null}
            },
            new String [] {
                "PROTOZOOS", "TROFOZOITOS", "QUISTES"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTabla2.setRowHeight(25);
        tblTabla2.getTableHeader().setReorderingAllowed(false);
        jScrollPane5.setViewportView(tblTabla2);
        if (tblTabla2.getColumnModel().getColumnCount() > 0) {
            tblTabla2.getColumnModel().getColumn(0).setMinWidth(250);
            tblTabla2.getColumnModel().getColumn(0).setPreferredWidth(250);
            tblTabla2.getColumnModel().getColumn(0).setMaxWidth(250);
        }

        tblTabla3.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        tblTabla3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"ASCARIS", null, null},
                {"TRICOCÉFALOS", null, null},
                {"UNCINARIA", null, null},
                {"TENIA", null, null},
                {"STRONGILOIDES", null, null},
                {"LARVAS", null, null},
                {"OXIUROS", null, null},
                {"SANGRE OCULTA", null, null},
                {"PH", null, null},
                {"AZUCARES REDUCTORES", null, null},
                {"GRAM", null, null},
                {"WRIGHT", null, null}
            },
            new String [] {
                "HELMINTOS", "HUEVOS /gr", "GRADO DE INFESTACIÓN"
            }
        ));
        tblTabla3.setRowHeight(25);
        tblTabla3.getTableHeader().setReorderingAllowed(false);
        jScrollPane6.setViewportView(tblTabla3);
        if (tblTabla3.getColumnModel().getColumnCount() > 0) {
            tblTabla3.getColumnModel().getColumn(0).setMinWidth(250);
            tblTabla3.getColumnModel().getColumn(0).setPreferredWidth(250);
            tblTabla3.getColumnModel().getColumn(0).setMaxWidth(250);
        }

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/anterior_1.png"))); // NOI18N
        jLabel10.setText("VOLVER A LA CONSULTA");
        jLabel10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlFormularioLayout = new javax.swing.GroupLayout(pnlFormulario);
        pnlFormulario.setLayout(pnlFormularioLayout);
        pnlFormularioLayout.setHorizontalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 945, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane6)
                        .addComponent(jScrollPane4)
                        .addComponent(jScrollPane5)
                        .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbNit10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        pnlFormularioLayout.setVerticalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbNit10, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        scrFormulario.setViewportView(pnlFormulario);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(scrFormulario, javax.swing.GroupLayout.DEFAULT_SIZE, 951, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrFormulario)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtTelefonoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoKeyReleased

    private void txtTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoKeyTyped

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
            cargarCliente(txtIdentificacion.getText());
        }
    }//GEN-LAST:event_txtIdentificacionKeyReleased

    private void txtIdentificacionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdentificacionKeyTyped

    }//GEN-LAST:event_txtIdentificacionKeyTyped

    private void txtNumeroOrdenKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroOrdenKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumeroOrdenKeyReleased

    private void txtNumeroOrdenKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroOrdenKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumeroOrdenKeyTyped

    private void txtEpsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEpsKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEpsKeyReleased

    private void txtEpsKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEpsKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEpsKeyTyped

    private void txtSexoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSexoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSexoKeyReleased

    private void txtSexoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSexoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSexoKeyTyped

    public void cargarCliente(String nit) {

        ModeloContacto Tercero = sql.getDatosTercero(nit);

        if (Tercero.getId() != null) {
            txtIdentificacion.setText(Tercero.getId());
            txtTelefono.setText(Tercero.getTelefono());
            txtNombres.setText(Tercero.getpNombre() + " " + Tercero.getsNombre() + " " + Tercero.getpApellido() + " " + Tercero.getsApellido());
            txtEps.setText(Tercero.getEps());
            txtSexo.setText(Tercero.getSexo());
//            txtRemitido.setText(metodos.fecha(Tercero.getNacimiento()));

            try {
                txtEdad.setText(metodosGenerales.calcularEdad2(metodos.fecha(Tercero.getNacimiento())) + "");
            } catch (Exception e) {
            }

            return;
        }
        ventanaTerceros(nit);
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
        Object[] campos = {txtIdentificacion};
        String faltantes = metodos.camposVacios(campos);

        if (!faltantes.equals("")) {
            metodos.msgAdvertencia(this, "No puede continuar, faltan los siguientes campos: " + faltantes);
            return;
        }

        tblTabla1.removeEditor();
        tblTabla2.removeEditor();
        tblTabla3.removeEditor();

        if (metodos.msgPregunta(this, "¿Desea continuar?") == 0) {

            String id = "COPROGRAMA-" + instancias.getSql().getNumConsecutivo("COPROGRAMA")[0];

            String consistencia;
            try {
                consistencia = tblTabla1.getValueAt(0, 1).toString();
            } catch (Exception e) {
                consistencia = "NO SE OBSERVAN";
            }

            String moco;
            try {
                moco = tblTabla1.getValueAt(1, 1).toString();
            } catch (Exception e) {
                moco = "NO SE OBSERVAN";
            }

            String floraBacteriana;
            try {
                floraBacteriana = tblTabla1.getValueAt(2, 1).toString();
            } catch (Exception e) {
                floraBacteriana = "NO SE OBSERVAN";
            }

            String almidones;
            try {
                almidones = tblTabla1.getValueAt(3, 1).toString();
            } catch (Exception e) {
                almidones = "NO SE OBSERVAN";
            }

            String fibrasMusculares;
            try {
                fibrasMusculares = tblTabla1.getValueAt(4, 1).toString();
            } catch (Exception e) {
                fibrasMusculares = "NO SE OBSERVAN";
            }

            String gotasDeGasa;
            try {
                gotasDeGasa = tblTabla1.getValueAt(5, 1).toString();
            } catch (Exception e) {
                gotasDeGasa = "NO SE OBSERVAN";
            }

            String celulasVegetales;
            try {
                celulasVegetales = tblTabla1.getValueAt(6, 1).toString();
            } catch (Exception e) {
                celulasVegetales = "NO SE OBSERVAN";
            }

            String esporasDeHongo;
            try {
                esporasDeHongo = tblTabla1.getValueAt(7, 1).toString();
            } catch (Exception e) {
                esporasDeHongo = "NO SE OBSERVAN";
            }

            String leucocitos;
            try {
                leucocitos = tblTabla1.getValueAt(8, 1).toString();
            } catch (Exception e) {
                leucocitos = "NO SE OBSERVAN";
            }

            String eritrocitos;
            try {
                eritrocitos = tblTabla1.getValueAt(9, 1).toString();
            } catch (Exception e) {
                eritrocitos = "NO SE OBSERVAN";
            }

            //TABLA DOS
            String histoliticaTrofozoitos;
            try {
                histoliticaTrofozoitos = tblTabla2.getValueAt(0, 1).toString();
            } catch (Exception e) {
                histoliticaTrofozoitos = "NO SE OBSERVAN";
            }

            String histoliticaQuistes;
            try {
                histoliticaQuistes = tblTabla2.getValueAt(0, 2).toString();
            } catch (Exception e) {
                histoliticaQuistes = "NO SE OBSERVAN";
            }

            String coliTrofozoitos;
            try {
                coliTrofozoitos = tblTabla2.getValueAt(1, 1).toString();
            } catch (Exception e) {
                coliTrofozoitos = "NO SE OBSERVAN";
            }

            String coliQuistes;
            try {
                coliQuistes = tblTabla2.getValueAt(1, 2).toString();
            } catch (Exception e) {
                coliQuistes = "NO SE OBSERVAN";
            }

            String nanaTrofozoitos;
            try {
                nanaTrofozoitos = tblTabla2.getValueAt(2, 1).toString();
            } catch (Exception e) {
                nanaTrofozoitos = "NO SE OBSERVAN";
            }

            String nanaQuistes;
            try {
                nanaQuistes = tblTabla2.getValueAt(2, 2).toString();
            } catch (Exception e) {
                nanaQuistes = "NO SE OBSERVAN";
            }

            String lodamoebaTrofozoitos;
            try {
                lodamoebaTrofozoitos = tblTabla2.getValueAt(3, 1).toString();
            } catch (Exception e) {
                lodamoebaTrofozoitos = "NO SE OBSERVAN";
            }

            String lodamoebaQuistes;
            try {
                lodamoebaQuistes = tblTabla2.getValueAt(3, 2).toString();
            } catch (Exception e) {
                lodamoebaQuistes = "NO SE OBSERVAN";
            }

            String giardiaTrofozoitos;
            try {
                giardiaTrofozoitos = tblTabla2.getValueAt(4, 1).toString();
            } catch (Exception e) {
                giardiaTrofozoitos = "NO SE OBSERVAN";
            }

            String giardiaQuistes;
            try {
                giardiaQuistes = tblTabla2.getValueAt(4, 2).toString();
            } catch (Exception e) {
                giardiaQuistes = "NO SE OBSERVAN";
            }

            String trichomonaTrofozoitos;
            try {
                trichomonaTrofozoitos = tblTabla2.getValueAt(5, 1).toString();
            } catch (Exception e) {
                trichomonaTrofozoitos = "NO SE OBSERVAN";
            }

            String trichomonaQuistes;
            try {
                trichomonaQuistes = tblTabla2.getValueAt(5, 2).toString();
            } catch (Exception e) {
                trichomonaQuistes = "NO SE OBSERVAN";
            }

            String chilomastixTrofozoitos;
            try {
                chilomastixTrofozoitos = tblTabla2.getValueAt(6, 1).toString();
            } catch (Exception e) {
                chilomastixTrofozoitos = "NO SE OBSERVAN";
            }

            String chilomastixQuistes;
            try {
                chilomastixQuistes = tblTabla2.getValueAt(6, 2).toString();
            } catch (Exception e) {
                chilomastixQuistes = "NO SE OBSERVAN";
            }

            String balantidiumTrofozoitos;
            try {
                balantidiumTrofozoitos = tblTabla2.getValueAt(7, 1).toString();
            } catch (Exception e) {
                balantidiumTrofozoitos = "NO SE OBSERVAN";
            }

            String balantidiumQuistes;
            try {
                balantidiumQuistes = tblTabla2.getValueAt(7, 2).toString();
            } catch (Exception e) {
                balantidiumQuistes = "NO SE OBSERVAN";
            }

            //TABLA TRES
            String ascarisHuevos;
            try {
                ascarisHuevos = tblTabla3.getValueAt(0, 1).toString();
            } catch (Exception e) {
                ascarisHuevos = "NO SE OBSERVAN";
            }

            String ascarisInfestacion;
            try {
                ascarisInfestacion = tblTabla3.getValueAt(0, 2).toString();
            } catch (Exception e) {
                ascarisInfestacion = "NO SE OBSERVAN";
            }

            String tricocefalosHuevos;
            try {
                tricocefalosHuevos = tblTabla3.getValueAt(1, 1).toString();
            } catch (Exception e) {
                tricocefalosHuevos = "NO SE OBSERVAN";
            }

            String tricocefalosInfestacion;
            try {
                tricocefalosInfestacion = tblTabla3.getValueAt(1, 2).toString();
            } catch (Exception e) {
                tricocefalosInfestacion = "NO SE OBSERVAN";
            }

            String uncinariaHuevos;
            try {
                uncinariaHuevos = tblTabla3.getValueAt(2, 1).toString();
            } catch (Exception e) {
                uncinariaHuevos = "NO SE OBSERVAN";
            }

            String uncinariaInfestacion;
            try {
                uncinariaInfestacion = tblTabla3.getValueAt(2, 2).toString();
            } catch (Exception e) {
                uncinariaInfestacion = "NO SE OBSERVAN";
            }

            String teniaHuevos;
            try {
                teniaHuevos = tblTabla3.getValueAt(3, 1).toString();
            } catch (Exception e) {
                teniaHuevos = "NO SE OBSERVAN";
            }

            String teniaInfestacion;
            try {
                teniaInfestacion = tblTabla3.getValueAt(3, 2).toString();
            } catch (Exception e) {
                teniaInfestacion = "NO SE OBSERVAN";
            }

            String strongiloidesHuevos;
            try {
                strongiloidesHuevos = tblTabla3.getValueAt(4, 1).toString();
            } catch (Exception e) {
                strongiloidesHuevos = "NO SE OBSERVAN";
            }

            String strongiloidesInfestacion;
            try {
                strongiloidesInfestacion = tblTabla3.getValueAt(4, 2).toString();
            } catch (Exception e) {
                strongiloidesInfestacion = "NO SE OBSERVAN";
            }

            String larvasHuevos;
            try {
                larvasHuevos = tblTabla3.getValueAt(5, 1).toString();
            } catch (Exception e) {
                larvasHuevos = "NO SE OBSERVAN";
            }

            String larvasInfestacion;
            try {
                larvasInfestacion = tblTabla3.getValueAt(5, 2).toString();
            } catch (Exception e) {
                larvasInfestacion = "NO SE OBSERVAN";
            }

            String oxiurosHuevos;
            try {
                oxiurosHuevos = tblTabla3.getValueAt(6, 1).toString();
            } catch (Exception e) {
                oxiurosHuevos = "NO SE OBSERVAN";
            }

            String oxiurosInfestacion;
            try {
                oxiurosInfestacion = tblTabla3.getValueAt(6, 2).toString();
            } catch (Exception e) {
                oxiurosInfestacion = "NO SE OBSERVAN";
            }

            String sangreOcultaHuevos;
            try {
                sangreOcultaHuevos = tblTabla3.getValueAt(7, 1).toString();
            } catch (Exception e) {
                sangreOcultaHuevos = "NO SE OBSERVAN";
            }

            String sangreOcultaInfestacion;
            try {
                sangreOcultaInfestacion = tblTabla3.getValueAt(7, 2).toString();
            } catch (Exception e) {
                sangreOcultaInfestacion = "NO SE OBSERVAN";
            }

            String phHuevos;
            try {
                phHuevos = tblTabla3.getValueAt(8, 1).toString();
            } catch (Exception e) {
                phHuevos = "NO SE OBSERVAN";
            }

            String phInfestacion;
            try {
                phInfestacion = tblTabla3.getValueAt(8, 2).toString();
            } catch (Exception e) {
                phInfestacion = "NO SE OBSERVAN";
            }

            String azucaresHuevos;
            try {
                azucaresHuevos = tblTabla3.getValueAt(9, 1).toString();
            } catch (Exception e) {
                azucaresHuevos = "NO SE OBSERVAN";
            }

            String azucaresInfestacion;
            try {
                azucaresInfestacion = tblTabla3.getValueAt(9, 2).toString();
            } catch (Exception e) {
                azucaresInfestacion = "NO SE OBSERVAN";
            }

            String gramHuevos;
            try {
                gramHuevos = tblTabla3.getValueAt(10, 1).toString();
            } catch (Exception e) {
                gramHuevos = "NO SE OBSERVAN";
            }

            String gramInfestacion;
            try {
                gramInfestacion = tblTabla3.getValueAt(10, 2).toString();
            } catch (Exception e) {
                gramInfestacion = "NO SE OBSERVAN";
            }

            String wrightHuevos;
            try {
                wrightHuevos = tblTabla3.getValueAt(11, 1).toString();
            } catch (Exception e) {
                wrightHuevos = "NO SE OBSERVAN";
            }

            String wrightInfestacion;
            try {
                wrightInfestacion = tblTabla3.getValueAt(11, 2).toString();
            } catch (Exception e) {
                wrightInfestacion = "NO SE OBSERVAN";
            }

            if (!instancias.getSql().agregarCoprograma(id, txtIdentificacion.getText(), txtNumeroOrden.getText(), txtRemitido.getText(), metodosGenerales.fecha(),
                    "", txtTecnica.getText(), txtObservaciones.getText(), consistencia, moco, floraBacteriana, almidones, fibrasMusculares, gotasDeGasa, celulasVegetales, esporasDeHongo,
                    leucocitos, eritrocitos, histoliticaTrofozoitos, histoliticaQuistes, coliTrofozoitos, coliQuistes, nanaTrofozoitos, nanaQuistes, lodamoebaTrofozoitos,
                    lodamoebaQuistes, giardiaTrofozoitos, giardiaQuistes, trichomonaTrofozoitos, trichomonaQuistes, chilomastixTrofozoitos, chilomastixQuistes, balantidiumTrofozoitos,
                    balantidiumQuistes, ascarisHuevos, ascarisInfestacion, tricocefalosHuevos, tricocefalosInfestacion, uncinariaHuevos, uncinariaInfestacion, teniaHuevos,
                    teniaInfestacion, strongiloidesHuevos, strongiloidesInfestacion, larvasHuevos, larvasInfestacion, oxiurosHuevos, oxiurosInfestacion, sangreOcultaHuevos,
                    sangreOcultaInfestacion, phHuevos, phInfestacion, azucaresHuevos, azucaresInfestacion, gramHuevos, gramInfestacion, wrightHuevos, wrightInfestacion
            )) {
                metodos.msgError(this, "Hubo un problema al guardar");
                return;
            }

            if (!instancias.getSql().aumentarConsecutivo("COPROGRAMA", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("COPROGRAMA")[0]) + 1)) {
                metodos.msgError(this, "Hubo un problema al guardar en el consecutivo");
                return;
            }

            metodos.msgExito(this, "Examen registrado con éxito");

            if (metodos.msgPregunta(this, "¿Desea imprimir?") == 0) {
                String legal = "", pie;
                try {
                    legal = instancias.getLegal();
                } catch (Exception ex) {
                    legal = "";
                }
                try {
                    pie = instancias.getPie();
                } catch (Exception ex) {
                    pie = "";
                }

                instancias.getReporte().ver_Examen(id, instancias.getInformacionEmpresa(), legal, pie, txtEdad.getText(), "coprograma");
            }
            btnLimpiarActionPerformed(evt);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnGuardarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnGuardarKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnGuardarActionPerformed(null);
        }
    }//GEN-LAST:event_btnGuardarKeyReleased

    private void txtEdadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEdadKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEdadKeyReleased

    private void txtEdadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEdadKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEdadKeyTyped

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        if (!txtIdentificacion.getText().equals("")) {
            infReimpresionExamenesLaboratorio impresion = new infReimpresionExamenesLaboratorio();
            impresion.cargarRegistros(txtIdentificacion.getText(), "coprograma", instancias);
            impresion.setVisible(true);
        }
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void btnImprimirKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnImprimirKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_btnImprimirKeyReleased

    private void txtRemitidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRemitidoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRemitidoKeyTyped

    private void txtRemitidoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRemitidoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRemitidoKeyReleased

    private void txtNumeroOrdenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumeroOrdenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumeroOrdenActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        txtTecnica.setText("");
        txtObservaciones.setText("");
        txtRemitido.setText("");

        tblTabla1.setValueAt("", 0, 1);
        tblTabla1.setValueAt("", 1, 1);
        tblTabla1.setValueAt("", 2, 1);
        tblTabla1.setValueAt("", 3, 1);
        tblTabla1.setValueAt("", 4, 1);
        tblTabla1.setValueAt("", 5, 1);
        tblTabla1.setValueAt("", 6, 1);
        tblTabla1.setValueAt("", 7, 1);
        tblTabla1.setValueAt("", 8, 1);
        tblTabla1.setValueAt("", 9, 1);

        tblTabla2.setValueAt("", 0, 1);
        tblTabla2.setValueAt("", 0, 2);
        tblTabla2.setValueAt("", 1, 1);
        tblTabla2.setValueAt("", 1, 2);
        tblTabla2.setValueAt("", 2, 1);
        tblTabla2.setValueAt("", 2, 2);
        tblTabla2.setValueAt("", 3, 1);
        tblTabla2.setValueAt("", 3, 2);
        tblTabla2.setValueAt("", 4, 1);
        tblTabla2.setValueAt("", 4, 2);
        tblTabla2.setValueAt("", 5, 1);
        tblTabla2.setValueAt("", 5, 2);
        tblTabla2.setValueAt("", 6, 1);
        tblTabla2.setValueAt("", 6, 2);
        tblTabla2.setValueAt("", 7, 1);
        tblTabla2.setValueAt("", 7, 2);

        tblTabla3.setValueAt("", 0, 1);
        tblTabla3.setValueAt("", 0, 2);
        tblTabla3.setValueAt("", 1, 1);
        tblTabla3.setValueAt("", 1, 2);
        tblTabla3.setValueAt("", 2, 1);
        tblTabla3.setValueAt("", 2, 2);
        tblTabla3.setValueAt("", 3, 1);
        tblTabla3.setValueAt("", 3, 2);
        tblTabla3.setValueAt("", 4, 1);
        tblTabla3.setValueAt("", 4, 2);
        tblTabla3.setValueAt("", 5, 1);
        tblTabla3.setValueAt("", 5, 2);
        tblTabla3.setValueAt("", 6, 1);
        tblTabla3.setValueAt("", 6, 2);
        tblTabla3.setValueAt("", 7, 1);
        tblTabla3.setValueAt("", 7, 2);
        tblTabla3.setValueAt("", 8, 1);
        tblTabla3.setValueAt("", 8, 2);
        tblTabla3.setValueAt("", 9, 1);
        tblTabla3.setValueAt("", 9, 2);
        tblTabla3.setValueAt("", 10, 1);
        tblTabla3.setValueAt("", 10, 2);
        tblTabla3.setValueAt("", 11, 1);
        tblTabla3.setValueAt("", 11, 2);

    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnLimpiarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnLimpiarKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLimpiarKeyReleased

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
        try {
            instancias.getHistoriaLaboratorio().setSelected(true);
            instancias.getMenu().expandirMenu();
        } catch (PropertyVetoException ex) {
            Logger.getLogger(infHistoriaLaboratorio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLabel10MouseClicked

    public void reimprimir(String consecutivo) {
        String legal = "", pie;
        try {
            legal = instancias.getLegal();
        } catch (Exception ex) {
            legal = "";
        }
        try {
            pie = instancias.getPie();
        } catch (Exception ex) {
            pie = "";
        }
        instancias.getReporte().ver_Examen(consecutivo, instancias.getInformacionEmpresa(), legal, pie, txtEdad.getText(), "coprograma");
    }

    public void cargar(String nit, String numero) {

        txtNumeroOrden.setText(numero);

        try {
            this.setSelected(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(infHistoriaMedica.class.getName()).log(Level.SEVERE, null, ex);
        }

        txtIdentificacion.setText(nit);
        cargarCliente(nit);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JLabel lbNit10;
    private javax.swing.JLabel lbNit15;
    private javax.swing.JLabel lbNit16;
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
    private javax.swing.JTable tblTabla1;
    private javax.swing.JTable tblTabla2;
    private javax.swing.JTable tblTabla3;
    private javax.swing.JTextField txtEdad;
    private javax.swing.JTextField txtEps;
    private javax.swing.JTextField txtIdentificacion;
    private javax.swing.JTextField txtNombres;
    private javax.swing.JTextField txtNumeroOrden;
    private javax.swing.JTextArea txtObservaciones;
    private javax.swing.JTextField txtRemitido;
    private javax.swing.JTextField txtSexo;
    private javax.swing.JTextArea txtTecnica;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
