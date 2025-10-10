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

public class infColesterol extends javax.swing.JInternalFrame {

    metodosGenerales metodos = new metodosGenerales();
    private Instancias instancias;
    private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension dimBarra = null;
    DefaultTableModel modelo;
    SQL sql= new SQL();

    public infColesterol() {
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
        jPanel4 = new javax.swing.JPanel();
        lbNit17 = new javax.swing.JLabel();
        lbNit18 = new javax.swing.JLabel();
        lbNit19 = new javax.swing.JLabel();
        lbNit20 = new javax.swing.JLabel();
        txtResultados = new javax.swing.JTextField();
        lbNit23 = new javax.swing.JLabel();
        lbNit28 = new javax.swing.JLabel();
        lbNit29 = new javax.swing.JLabel();
        lbNit30 = new javax.swing.JLabel();
        lbNit31 = new javax.swing.JLabel();
        lbNit32 = new javax.swing.JLabel();
        lbNit34 = new javax.swing.JLabel();
        lbNit35 = new javax.swing.JLabel();
        lbNit36 = new javax.swing.JLabel();
        lbNit37 = new javax.swing.JLabel();
        lbNit33 = new javax.swing.JLabel();
        lbNit39 = new javax.swing.JLabel();
        lbNit40 = new javax.swing.JLabel();
        lbNit41 = new javax.swing.JLabel();
        lbNit27 = new javax.swing.JLabel();
        lbNit42 = new javax.swing.JLabel();
        lbNit38 = new javax.swing.JLabel();
        txtResultados1 = new javax.swing.JTextField();
        lbNit21 = new javax.swing.JLabel();
        lbNit22 = new javax.swing.JLabel();
        lbNit24 = new javax.swing.JLabel();
        lbNit25 = new javax.swing.JLabel();
        lbNit43 = new javax.swing.JLabel();
        lbNit44 = new javax.swing.JLabel();
        lbNit45 = new javax.swing.JLabel();
        lbNit46 = new javax.swing.JLabel();
        txtResultados2 = new javax.swing.JTextField();
        txtResultados3 = new javax.swing.JTextField();
        lbNit47 = new javax.swing.JLabel();
        lbNit48 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        setTitle("Factura");

        pnlFormulario.setBackground(new java.awt.Color(255, 255, 255));

        lbNit10.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        lbNit10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit10.setText("COLESTEROL TOTAL / TRIGLICERIDOS");

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
                .addContainerGap(276, Short.MAX_VALUE)
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

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        lbNit17.setBackground(new java.awt.Color(204, 204, 204));
        lbNit17.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        lbNit17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit17.setText("ASPECTO DEL SUERO");
        lbNit17.setOpaque(true);

        lbNit18.setBackground(new java.awt.Color(204, 204, 204));
        lbNit18.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        lbNit18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit18.setOpaque(true);

        lbNit19.setBackground(new java.awt.Color(204, 204, 204));
        lbNit19.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        lbNit19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit19.setText(" ");
        lbNit19.setOpaque(true);

        lbNit20.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbNit20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit20.setText("0 a 200");

        txtResultados.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        txtResultados.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtResultados.setName("No. Identificacion"); // NOI18N
        txtResultados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtResultadosActionPerformed(evt);
            }
        });
        txtResultados.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtResultadosKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtResultadosKeyTyped(evt);
            }
        });

        lbNit23.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbNit23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit23.setText("COLESTEROL TOTAL");

        lbNit28.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbNit28.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNit28.setText("Resultado:");

        lbNit29.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbNit29.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNit29.setText("Niños");

        lbNit30.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lbNit30.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNit30.setText("Conveniente < 170 mg/dl");

        lbNit31.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lbNit31.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNit31.setText("Riesgo moderado 170 a 199 mg/dl");

        lbNit32.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lbNit32.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNit32.setText("Elevado > 200 mg/dl");

        lbNit34.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbNit34.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNit34.setText("Adultos");

        lbNit35.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lbNit35.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNit35.setText("Elevado > 240 mg/dl");

        lbNit36.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lbNit36.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNit36.setText("Riesgo moderado 200 a 239 mg/dl");

        lbNit37.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lbNit37.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNit37.setText("Conveniente < 200 mg/dl");

        lbNit33.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbNit33.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNit33.setText("Resultado:");

        lbNit39.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lbNit39.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNit39.setText("Normal < 150 mg/dl");

        lbNit40.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lbNit40.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNit40.setText("Limite Elevado 150 - 199 mg/dl");

        lbNit41.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lbNit41.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNit41.setText("Elevado 200 - 499 mg/dl");

        lbNit27.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbNit27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit27.setText("TRIGLICERIDOS");

        lbNit42.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lbNit42.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNit42.setText("Muy Elevado > 500 mg/dl");

        lbNit38.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbNit38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit38.setText("30 a 150");

        txtResultados1.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        txtResultados1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtResultados1.setName("No. Identificacion"); // NOI18N
        txtResultados1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtResultados1ActionPerformed(evt);
            }
        });
        txtResultados1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtResultados1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtResultados1KeyTyped(evt);
            }
        });

        lbNit21.setBackground(new java.awt.Color(204, 204, 204));
        lbNit21.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        lbNit21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit21.setText("VALOR NORMAL");
        lbNit21.setOpaque(true);

        lbNit22.setBackground(new java.awt.Color(204, 204, 204));
        lbNit22.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        lbNit22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit22.setText("RESULTADOS");
        lbNit22.setOpaque(true);

        lbNit24.setBackground(new java.awt.Color(204, 204, 204));
        lbNit24.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        lbNit24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit24.setText("ANALISIS");
        lbNit24.setOpaque(true);

        lbNit25.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbNit25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit25.setText("COLESTEROL TOTAL");

        lbNit43.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbNit43.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit43.setText("TRIGLICERIDOS");

        lbNit44.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lbNit44.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit44.setText("Conveniente < 170 mg/dl");

        lbNit45.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lbNit45.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit45.setText("Riesgo moderado 170 a 199 mg/dl");

        lbNit46.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lbNit46.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit46.setText("Elevado > 200 mg/dl");

        txtResultados2.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        txtResultados2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtResultados2.setName("No. Identificacion"); // NOI18N
        txtResultados2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtResultados2ActionPerformed(evt);
            }
        });
        txtResultados2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtResultados2KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtResultados2KeyTyped(evt);
            }
        });

        txtResultados3.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        txtResultados3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtResultados3.setName("No. Identificacion"); // NOI18N
        txtResultados3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtResultados3ActionPerformed(evt);
            }
        });
        txtResultados3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtResultados3KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtResultados3KeyTyped(evt);
            }
        });

        lbNit47.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lbNit47.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit47.setText("Mujeres 35 - 150 mg/dl");

        lbNit48.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lbNit48.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit48.setText("Hombres 40 - 150 mg/dl");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(lbNit34, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbNit30, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbNit29, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbNit31, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
                                .addComponent(lbNit32, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(lbNit37, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbNit36, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbNit35, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(lbNit33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lbNit42, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lbNit40, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
                                            .addComponent(lbNit41, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lbNit39, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lbNit17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lbNit28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lbNit23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lbNit27, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(6, 6, 6)))
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtResultados)
                                    .addComponent(lbNit18, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
                                    .addComponent(txtResultados1, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lbNit25, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
                                    .addComponent(lbNit24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbNit43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbNit22, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtResultados2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtResultados3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbNit44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbNit19, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
                            .addComponent(lbNit20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbNit38, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbNit21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbNit47, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbNit48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(644, 644, 644)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbNit46, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbNit45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNit24)
                    .addComponent(lbNit22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbNit21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lbNit44)
                        .addGap(3, 3, 3)
                        .addComponent(lbNit45, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(lbNit46, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE))
                    .addComponent(txtResultados2)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(lbNit25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtResultados3, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lbNit48, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbNit47))
                    .addComponent(lbNit43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(35, 35, 35)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNit17)
                    .addComponent(lbNit18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbNit19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtResultados, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addComponent(lbNit23)
                        .addGap(5, 5, 5)
                        .addComponent(lbNit28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbNit29)
                        .addGap(0, 0, 0)
                        .addComponent(lbNit30)
                        .addGap(0, 0, 0)
                        .addComponent(lbNit31, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(lbNit32)
                        .addGap(10, 10, 10)
                        .addComponent(lbNit34)
                        .addGap(0, 0, 0)
                        .addComponent(lbNit37)
                        .addGap(0, 0, 0)
                        .addComponent(lbNit36, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(lbNit35))
                    .addComponent(lbNit20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(35, 35, 35)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtResultados1)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lbNit27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbNit33)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbNit39)
                        .addGap(0, 0, 0)
                        .addComponent(lbNit40, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(lbNit41)
                        .addGap(0, 0, 0)
                        .addComponent(lbNit42))
                    .addComponent(lbNit38, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 5, Short.MAX_VALUE))
        );

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
                .addContainerGap(23, Short.MAX_VALUE)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 949, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbNit10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlFormularioLayout.setVerticalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbNit10, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        Object[] campos = {txtIdentificacion, txtResultados, txtResultados1};
        String faltantes = metodos.camposVacios(campos);

        if (!faltantes.equals("")) {
            metodos.msgAdvertencia(this, "No puede continuar, faltan los siguientes campos: " + faltantes);
            return;
        }

        if (metodos.msgPregunta(this, "¿Desea continuar?") == 0) {

            String id = "COLESTEROL-" + instancias.getSql().getNumConsecutivo("COLESTEROL")[0];

            if (!instancias.getSql().agregarColesterol(id, txtIdentificacion.getText(), txtNumeroOrden.getText(), txtRemitido.getText(), metodosGenerales.fecha(),
                    txtResultados.getText(), txtTecnica.getText(), txtObservaciones.getText(), txtResultados1.getText(), txtResultados2.getText(), txtResultados3.getText())) {
                metodos.msgError(this, "Hubo un problema al guardar");
                return;
            }

            if (!instancias.getSql().aumentarConsecutivo("COLESTEROL", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("COLESTEROL")[0]) + 1)) {
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

                instancias.getReporte().ver_Examen(id, instancias.getInformacionEmpresa(), legal, pie, txtEdad.getText(), "colesterol");
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
            impresion.cargarRegistros(txtIdentificacion.getText(), "colesterol", instancias);
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
        txtResultados.setText("");
        txtResultados1.setText("");
        txtResultados3.setText("");
        txtResultados2.setText("");
        txtObservaciones.setText("");
        txtRemitido.setText("");
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnLimpiarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnLimpiarKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLimpiarKeyReleased

    private void txtResultadosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtResultadosKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtResultadosKeyTyped

    private void txtResultadosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtResultadosKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtResultadosKeyReleased

    private void txtResultadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtResultadosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtResultadosActionPerformed

    private void txtResultados1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtResultados1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtResultados1ActionPerformed

    private void txtResultados1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtResultados1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtResultados1KeyReleased

    private void txtResultados1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtResultados1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtResultados1KeyTyped

    private void txtResultados2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtResultados2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtResultados2ActionPerformed

    private void txtResultados2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtResultados2KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtResultados2KeyReleased

    private void txtResultados2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtResultados2KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtResultados2KeyTyped

    private void txtResultados3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtResultados3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtResultados3ActionPerformed

    private void txtResultados3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtResultados3KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtResultados3KeyReleased

    private void txtResultados3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtResultados3KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtResultados3KeyTyped

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
        instancias.getReporte().ver_Examen(consecutivo, instancias.getInformacionEmpresa(), legal, pie, txtEdad.getText(), "colesterol");
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbNit10;
    private javax.swing.JLabel lbNit15;
    private javax.swing.JLabel lbNit16;
    private javax.swing.JLabel lbNit17;
    private javax.swing.JLabel lbNit18;
    private javax.swing.JLabel lbNit19;
    private javax.swing.JLabel lbNit20;
    private javax.swing.JLabel lbNit21;
    private javax.swing.JLabel lbNit22;
    private javax.swing.JLabel lbNit23;
    private javax.swing.JLabel lbNit24;
    private javax.swing.JLabel lbNit25;
    private javax.swing.JLabel lbNit27;
    private javax.swing.JLabel lbNit28;
    private javax.swing.JLabel lbNit29;
    private javax.swing.JLabel lbNit30;
    private javax.swing.JLabel lbNit31;
    private javax.swing.JLabel lbNit32;
    private javax.swing.JLabel lbNit33;
    private javax.swing.JLabel lbNit34;
    private javax.swing.JLabel lbNit35;
    private javax.swing.JLabel lbNit36;
    private javax.swing.JLabel lbNit37;
    private javax.swing.JLabel lbNit38;
    private javax.swing.JLabel lbNit39;
    private javax.swing.JLabel lbNit40;
    private javax.swing.JLabel lbNit41;
    private javax.swing.JLabel lbNit42;
    private javax.swing.JLabel lbNit43;
    private javax.swing.JLabel lbNit44;
    private javax.swing.JLabel lbNit45;
    private javax.swing.JLabel lbNit46;
    private javax.swing.JLabel lbNit47;
    private javax.swing.JLabel lbNit48;
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
    private javax.swing.JTextField txtEps;
    private javax.swing.JTextField txtIdentificacion;
    private javax.swing.JTextField txtNombres;
    private javax.swing.JTextField txtNumeroOrden;
    private javax.swing.JTextArea txtObservaciones;
    private javax.swing.JTextField txtRemitido;
    private javax.swing.JTextField txtResultados;
    private javax.swing.JTextField txtResultados1;
    private javax.swing.JTextField txtResultados2;
    private javax.swing.JTextField txtResultados3;
    private javax.swing.JTextField txtSexo;
    private javax.swing.JTextArea txtTecnica;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
