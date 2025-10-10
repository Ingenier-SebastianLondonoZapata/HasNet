package formularios.Medico;

import clases.Instancias;
import clases.Medico.ndHistoriaClinica;
import clases.Medico.ndIncapacidad;
import clases.Medico.ndRemision;
import clases.big;
import clases.metodosGenerales;
import clases.ndUsuario;
import Modelo.Terceros.ModeloContacto;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

public class infRemision extends javax.swing.JInternalFrame {

    metodosGenerales metodos = new metodosGenerales();
    private Instancias instancias;

    private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension dimBarra = null;
    ModeloContacto Paciente;

    public infRemision() {
        initComponents();
        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
        dimBarra = Barra.getPreferredSize();
        Barra.setSize(0, 0);
        Barra.setPreferredSize(new Dimension(0, 0));
        setBorder(null);
        repaint();
        instancias = Instancias.getInstancias();
        pnlFormulario.registerKeyboardAction(accion("guardar"), "guardar", KeyStroke.getKeyStroke(KeyEvent.VK_G, Event.CTRL_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
    }

    private ActionListener accion(final String opc) {
        ActionListener a = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (opc) {
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        scrFormulario = new javax.swing.JScrollPane();
        pnlFormulario = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        lbRazon1 = new javax.swing.JLabel();
        chkUrgente = new javax.swing.JCheckBox();
        chkPrioritaria = new javax.swing.JCheckBox();
        jPanel14 = new javax.swing.JPanel();
        lbRazon9 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtResumen = new javax.swing.JTextArea();
        jSeparator3 = new javax.swing.JSeparator();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtTratamientos = new javax.swing.JTextArea();
        lbRazon10 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        lbRazon11 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtAyudas = new javax.swing.JTextArea();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtMotivo = new javax.swing.JTextArea();
        lbRazon12 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        lbRazon13 = new javax.swing.JLabel();
        txtCie1 = new javax.swing.JTextField();
        txtDiagnostico1 = new javax.swing.JTextField();
        txtCie2 = new javax.swing.JTextField();
        txtDiagnostico2 = new javax.swing.JTextField();
        lbRazon14 = new javax.swing.JLabel();
        lbRazon15 = new javax.swing.JLabel();
        rbtMediacarta = new javax.swing.JRadioButton();
        rbtCarta = new javax.swing.JRadioButton();
        lbNit5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        btnBuscTerceros2 = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        lbRazon2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtEspecialidad = new javax.swing.JTextArea();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();

        setTitle("Incapacidad");

        pnlFormulario.setBackground(new java.awt.Color(255, 255, 255));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        lbRazon1.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbRazon1.setText("TIPO DE REMISIÓN");

        buttonGroup1.add(chkUrgente);
        chkUrgente.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        chkUrgente.setText("Urgente");

        buttonGroup1.add(chkPrioritaria);
        chkPrioritaria.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        chkPrioritaria.setText("Prioritaria");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chkUrgente, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(chkPrioritaria)
                .addContainerGap(10, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbRazon1)
                .addGap(37, 37, 37))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbRazon1)
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkUrgente)
                    .addComponent(chkPrioritaria))
                .addGap(7, 7, 7))
        );

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        lbRazon9.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbRazon9.setText("RESUMEN DE HALLAZGOS");

        txtResumen.setColumns(20);
        txtResumen.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        txtResumen.setLineWrap(true);
        txtResumen.setRows(1);
        txtResumen.setName("Descripcion"); // NOI18N
        txtResumen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtResumenKeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(txtResumen);

        txtTratamientos.setColumns(20);
        txtTratamientos.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        txtTratamientos.setLineWrap(true);
        txtTratamientos.setRows(1);
        txtTratamientos.setName("Descripcion"); // NOI18N
        txtTratamientos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTratamientosKeyReleased(evt);
            }
        });
        jScrollPane4.setViewportView(txtTratamientos);

        lbRazon10.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbRazon10.setText("TRATAMIENTOS RECIBIDOS");

        lbRazon11.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbRazon11.setText("AYUDAS DIAGNOSTICAS");

        txtAyudas.setColumns(20);
        txtAyudas.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        txtAyudas.setLineWrap(true);
        txtAyudas.setRows(1);
        txtAyudas.setName("Descripcion"); // NOI18N
        txtAyudas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAyudasKeyReleased(evt);
            }
        });
        jScrollPane5.setViewportView(txtAyudas);

        txtMotivo.setColumns(20);
        txtMotivo.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        txtMotivo.setLineWrap(true);
        txtMotivo.setRows(1);
        txtMotivo.setName("Descripcion"); // NOI18N
        txtMotivo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMotivoKeyReleased(evt);
            }
        });
        jScrollPane6.setViewportView(txtMotivo);

        lbRazon12.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbRazon12.setText("MOTIVO DE REMISIÓN");

        jSeparator6.setForeground(new java.awt.Color(0, 0, 0));

        lbRazon13.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbRazon13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbRazon13.setText("DIAGNÓSTICO ");

        txtCie1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtCie1.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtCie1.setEnabled(false);
        txtCie1.setName("Cie"); // NOI18N
        txtCie1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCie1FocusLost(evt);
            }
        });
        txtCie1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCie1KeyReleased(evt);
            }
        });

        txtDiagnostico1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtDiagnostico1.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtDiagnostico1.setEnabled(false);
        txtDiagnostico1.setName("Diagnostico"); // NOI18N
        txtDiagnostico1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDiagnostico1KeyReleased(evt);
            }
        });

        txtCie2.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtCie2.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtCie2.setEnabled(false);
        txtCie2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCie2FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCie2FocusLost(evt);
            }
        });
        txtCie2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCie2KeyReleased(evt);
            }
        });

        txtDiagnostico2.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtDiagnostico2.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtDiagnostico2.setEnabled(false);
        txtDiagnostico2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDiagnostico2KeyReleased(evt);
            }
        });

        lbRazon14.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbRazon14.setText("1.");

        lbRazon15.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbRazon15.setText("2.");

        buttonGroup2.add(rbtMediacarta);
        rbtMediacarta.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        rbtMediacarta.setText("Media carta");

        buttonGroup2.add(rbtCarta);
        rbtCarta.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        rbtCarta.setSelected(true);
        rbtCarta.setText("Carta");

        lbNit5.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbNit5.setText("Tipo Impresión:");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 925, Short.MAX_VALUE)
                    .addComponent(jSeparator3)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 925, Short.MAX_VALUE)
                    .addComponent(jSeparator4)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 925, Short.MAX_VALUE)
                    .addComponent(jSeparator5)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 925, Short.MAX_VALUE)
                    .addComponent(jSeparator6)
                    .addComponent(lbRazon13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(lbRazon14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCie1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDiagnostico1))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(lbRazon15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCie2, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDiagnostico2))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addComponent(lbNit5)
                                .addGap(18, 18, 18)
                                .addComponent(rbtCarta)
                                .addGap(10, 10, 10)
                                .addComponent(rbtMediacarta))
                            .addComponent(lbRazon9)
                            .addComponent(lbRazon10)
                            .addComponent(lbRazon11)
                            .addComponent(lbRazon12))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbRazon9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(lbRazon10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(lbRazon11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(lbRazon12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(lbRazon13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(lbRazon14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbRazon15, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCie1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDiagnostico1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCie2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDiagnostico2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rbtCarta)
                        .addComponent(rbtMediacarta))
                    .addComponent(lbNit5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

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
        btnGuardar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                btnGuardarKeyReleased(evt);
            }
        });

        btnBuscTerceros2.setBackground(new java.awt.Color(247, 220, 111));
        btnBuscTerceros2.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnBuscTerceros2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/imprimir.png"))); // NOI18N
        btnBuscTerceros2.setText("REIMPRIMIR");
        btnBuscTerceros2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscTerceros2.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnBuscTerceros2.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnBuscTerceros2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscTerceros2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnGuardar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBuscTerceros2, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnBuscTerceros2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        lbRazon2.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbRazon2.setText("ESPECIALIDAD Y/O PROCEDIMIENTO");

        txtEspecialidad.setColumns(20);
        txtEspecialidad.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        txtEspecialidad.setLineWrap(true);
        txtEspecialidad.setRows(1);
        txtEspecialidad.setName("Especialidad"); // NOI18N
        txtEspecialidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEspecialidadKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(txtEspecialidad);

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/anterior_2.png"))); // NOI18N
        jLabel10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Volver");
        jLabel12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbRazon2)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 633, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel12)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(lbRazon2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2)))
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlFormularioLayout = new javax.swing.GroupLayout(pnlFormulario);
        pnlFormulario.setLayout(pnlFormularioLayout);
        pnlFormularioLayout.setHorizontalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlFormularioLayout.createSequentialGroup()
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        pnlFormularioLayout.setVerticalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
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
            .addComponent(scrFormulario, javax.swing.GroupLayout.DEFAULT_SIZE, 789, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnGuardarKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnGuardarActionPerformed(null);
        }
    }//GEN-LAST:event_btnGuardarKeyReleased

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        String tipo = "";
        Boolean seleccionado = false;
        if (chkPrioritaria.isSelected()) {
            seleccionado = true;
            tipo = "Prioritaria";
        } else if (chkUrgente.isSelected()) {
            seleccionado = true;
            tipo = "Urgente";
        }

        if (!seleccionado) {
            metodos.msgError(this, "Seleccione el tipo de remisión");
            return;
        }

        Object[] campos = {txtCie1, txtDiagnostico1, txtEspecialidad};
        String faltantes = metodos.camposVacios(campos);

        if (!faltantes.equals("")) {
            metodos.msgAdvertencia(this, "No puede continuar, faltan los siguientes campos: " + faltantes);
            return;
        }

        if (metodos.msgPregunta(this, "¿Desea continuar?") == 0) {

            String conse = "REMISION-" + instancias.getSql().getNumConsecutivo("REMISION")[0];
            String especialidad = txtEspecialidad.getText();
            String resumen = txtResumen.getText();
            String tratamientos = txtTratamientos.getText();
            String ayudas = txtAyudas.getText();
            String motivos = txtMotivo.getText();
            String diag1 = txtCie1.getText();
            String diag2 = txtCie2.getText();
            String nombre1 = txtDiagnostico1.getText();
            String nombre2 = txtDiagnostico2.getText();
            String nombres = Paciente.getpNombre() + Paciente.getsNombre();
            String apellidos = Paciente.getpApellido() + Paciente.getsApellido();
            String tipoId = Paciente.getTipo();
            String identificacion = Paciente.getId();
            String telefono = Paciente.getTelefono();

            String edad = "";
            try {
                edad = metodosGenerales.calcularEdad2(metodos.fecha(Paciente.getNacimiento())) + "";
            } catch (Exception e) {
            }

            ndUsuario medico1 = instancias.getUsuarioLog();
            String medico;
            String cedulaMedico;

            try {
                Object[][] datosMedico = instancias.getSql().getEmpleadoUsuario(medico1.getUsuario());
                medico = datosMedico[0][1].toString();
                cedulaMedico = datosMedico[0][0].toString();
            } catch (Exception e) {
                ndUsuario nodo1 = instancias.getSql().getDatosUsuario(medico1.getUsuario());
                medico = nodo1.getNombre();
                cedulaMedico = nodo1.getUsuario();
            }

            Object[] vector = {conse, metodos.fechaConsulta(metodosGenerales.fechaHora()), tipo, especialidad, nombres, apellidos, edad,
                tipoId, identificacion, telefono, resumen, tratamientos, ayudas, motivos, diag1, diag2, nombre1, nombre2,
                medico, cedulaMedico, ""};

            ndRemision nodo = metodos.llenarRemision(vector);

            if (!instancias.getSql().agregarRemision(nodo)) {
                metodos.msgError(this, "Hubo un problema al guardar la remisión");
                return;
            }

            //CAMBIAR CONSECUTIVO INCAPACIDAD
            if (!instancias.getSql().aumentarConsecutivo("REMISION", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("REMISION")[0]) + 1)) {
                metodos.msgError(this, "Hubo un problema al guardar en el consecutivo de la remisión");
            }

            limpiar();

            metodos.msgExito(this, "Remisión registrado con éxito");
            if (metodos.msgPregunta(this, "¿Desea imprimir?") == 0) {
                String tipo1 = "";
                if (rbtCarta.isSelected()) {
                    tipo1 = "Completa";
                }
                instancias.getReporte().verRemision(conse, instancias.getInformacionEmpresa(), instancias.getUsuario(), tipo1);
            }

            try {
                instancias.getHistoriaClinica().setSelected(true);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(infHistoriaMedica.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    public void limpiar() {
        chkPrioritaria.setSelected(false);
        chkUrgente.setSelected(false);

        txtEspecialidad.setText("");
        txtResumen.setText("");
        txtTratamientos.setText("");
        txtAyudas.setText("");
        txtMotivo.setText("");

        txtCie1.setText("");
        txtCie2.setText("");
        txtDiagnostico1.setText("");
        txtDiagnostico2.setText("");
    }

    private void btnBuscTerceros2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscTerceros2ActionPerformed
        infReimpresionExamenes reimpresion = new infReimpresionExamenes();
        reimpresion.show();
        reimpresion.setNombre(Paciente.getNombre());
        reimpresion.cargarRegistros(Paciente.getId(), "remision", instancias);
    }//GEN-LAST:event_btnBuscTerceros2ActionPerformed

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
        try {
            instancias.getHistoriaClinica().setSelected(true);
            instancias.getMenu().cambiarTitulo("HISTORIA CLINICA");
        } catch (PropertyVetoException ex) {
            Logger.getLogger(infHistoriaMedica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLabel10MouseClicked

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
        try {
            instancias.getHistoriaClinica().setSelected(true);
            instancias.getMenu().cambiarTitulo("HISTORIA CLINICA");
        } catch (PropertyVetoException ex) {
            Logger.getLogger(infHistoriaMedica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLabel12MouseClicked

    private void txtEspecialidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEspecialidadKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEspecialidadKeyReleased

    private void txtResumenKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtResumenKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtResumenKeyReleased

    private void txtTratamientosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTratamientosKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTratamientosKeyReleased

    private void txtAyudasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAyudasKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAyudasKeyReleased

    private void txtMotivoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMotivoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMotivoKeyReleased

    private void txtCie1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCie1FocusLost
        if (txtDiagnostico1.getText().equals("")) {
            txtCie1.setText("");
        }
    }//GEN-LAST:event_txtCie1FocusLost

    private void txtCie1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCie1KeyReleased

    }//GEN-LAST:event_txtCie1KeyReleased

    private void txtDiagnostico1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiagnostico1KeyReleased

    }//GEN-LAST:event_txtDiagnostico1KeyReleased

    private void txtCie2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCie2FocusGained

    }//GEN-LAST:event_txtCie2FocusGained

    private void txtCie2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCie2FocusLost
        if (txtDiagnostico2.getText().equals("")) {
            txtCie2.setText("");
        }
    }//GEN-LAST:event_txtCie2FocusLost

    private void txtCie2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCie2KeyReleased

    }//GEN-LAST:event_txtCie2KeyReleased

    private void txtDiagnostico2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiagnostico2KeyReleased

    }//GEN-LAST:event_txtDiagnostico2KeyReleased

//    public void setInstancias(Instancias instancias) {
//        this.instancias = instancias;
//        txtFechaInicio.setText(metodosGenerales.fecha());
//        txtFechaVencimiento.setText(metodosGenerales.fecha());
//        txtId.setText("INC-" + instancias.getSql().getNumConsecutivo("INC")[0]);
//    }
//    public void cargarIncapacidad(String Id) {
//
//        ndIncapacidad nodo = instancias.getSql().getDatosIncapacidad(Id);
//
//        if (nodo.getId() != null) {
//            historiaClinica = nodo.getConsulta();
//            txtFechaInicio.setText(nodo.getFechainicio());
//            txtFechaVencimiento.setText(nodo.getFechavencimiento());
//            txtCodigo.setText(nodo.getCup());
//            txtCup.setText(instancias.getSql().getNombreCup(nodo.getCup()));
//            txtCie.setText(nodo.getCie());
//            txtDiagnostico.setText(instancias.getSql().getNombreCie(nodo.getCie()));
//            txtDescripcion.setText(nodo.getDescripcion());
//
//            return;
//        }
//    }
    public void nuevaRemision(String id, String cie1, String cie2, String nombre1, String nombre2) {

        try {
            this.setSelected(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(infHistoriaMedica.class.getName()).log(Level.SEVERE, null, ex);
        }

        Paciente = instancias.getSql().getDatosTercero(id);
        txtCie1.setText(cie1);
        txtCie2.setText(cie2);
        txtDiagnostico1.setText(nombre1);
        txtDiagnostico2.setText(nombre2);
    }

    public void reimprimir(String consecutivo) {
//        ndRemision nodo = instancias.getSql().getDatosRemision(consecutivo);
        String tipo = "";
        if (rbtCarta.isSelected()) {
            tipo = "Completa";
        }
        instancias.getReporte().verRemision(consecutivo, instancias.getInformacionEmpresa(), instancias.getUsuario(), tipo);

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscTerceros2;
    private javax.swing.JButton btnGuardar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JCheckBox chkPrioritaria;
    private javax.swing.JCheckBox chkUrgente;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JLabel lbNit5;
    private javax.swing.JLabel lbRazon1;
    private javax.swing.JLabel lbRazon10;
    private javax.swing.JLabel lbRazon11;
    private javax.swing.JLabel lbRazon12;
    private javax.swing.JLabel lbRazon13;
    private javax.swing.JLabel lbRazon14;
    private javax.swing.JLabel lbRazon15;
    private javax.swing.JLabel lbRazon2;
    private javax.swing.JLabel lbRazon9;
    private javax.swing.JPanel pnlFormulario;
    private javax.swing.JRadioButton rbtCarta;
    private javax.swing.JRadioButton rbtMediacarta;
    private javax.swing.JScrollPane scrFormulario;
    private javax.swing.JTextArea txtAyudas;
    private javax.swing.JTextField txtCie1;
    private javax.swing.JTextField txtCie2;
    private javax.swing.JTextField txtDiagnostico1;
    private javax.swing.JTextField txtDiagnostico2;
    private javax.swing.JTextArea txtEspecialidad;
    private javax.swing.JTextArea txtMotivo;
    private javax.swing.JTextArea txtResumen;
    private javax.swing.JTextArea txtTratamientos;
    // End of variables declaration//GEN-END:variables

    private void cargando(String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
