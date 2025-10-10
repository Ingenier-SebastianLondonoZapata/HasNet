package formularios.Oftalmologia;

import clases.Instancias;
import clases.Medico.ndHistoriaClinica;
import clases.Medico.ndIncapacidad;
import clases.Oftalmologia.ndIncapacidadOf;
import Utilidades.BaseDatos.SQL;
import clases.big;
import clases.metodosGenerales;
import Modelo.Terceros.ModeloContacto;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JTextField;

public class infIncapacidadOf extends javax.swing.JInternalFrame {

    metodosGenerales metodos = new metodosGenerales();
    private Instancias instancias;

    private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension dimBarra = null;
    String consulta1;

    public infIncapacidadOf() {

        initComponents();
        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
        dimBarra = Barra.getPreferredSize();
        Barra.setSize(0, 0);
        Barra.setPreferredSize(new Dimension(0, 0));
        setBorder(null);
        repaint();
        instancias = Instancias.getInstancias();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrFormulario = new javax.swing.JScrollPane();
        pnlFormulario = new javax.swing.JPanel();
        lbNit10 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        lbNit = new javax.swing.JLabel();
        lbRazon6 = new javax.swing.JLabel();
        lbRazon4 = new javax.swing.JLabel();
        lbRazon2 = new javax.swing.JLabel();
        txtNacimiento = new javax.swing.JTextField();
        txtTipoDocu = new javax.swing.JTextField();
        txtNombres = new javax.swing.JTextField();
        txtId = new javax.swing.JTextField();
        txtIdentificacion = new javax.swing.JTextField();
        lbRazon5 = new javax.swing.JLabel();
        txtEdad = new javax.swing.JTextField();
        lbRazon1 = new javax.swing.JLabel();
        txtSexo = new javax.swing.JTextField();
        lbRazon3 = new javax.swing.JLabel();
        lbRazon7 = new javax.swing.JLabel();
        txtEstado = new javax.swing.JTextField();
        lbRazon8 = new javax.swing.JLabel();
        txtHistoria = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        lbRazon9 = new javax.swing.JLabel();
        txtFechaInicio = new javax.swing.JTextField();
        lbRazon10 = new javax.swing.JLabel();
        txtDias = new javax.swing.JTextField();
        lbRazon11 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        txtFechaVencimiento = new javax.swing.JTextField();
        lbRazon12 = new javax.swing.JLabel();
        txtCup = new javax.swing.JTextField();
        lbRazon13 = new javax.swing.JLabel();
        lbRazon14 = new javax.swing.JLabel();
        txtCie = new javax.swing.JTextField();
        lbRazon15 = new javax.swing.JLabel();
        txtDiagnostico = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        btnBuscTerceros2 = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();

        setTitle("Incapacidad");

        pnlFormulario.setBackground(new java.awt.Color(255, 255, 255));

        lbNit10.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        lbNit10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit10.setText("INCAPACIDAD");

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        lbNit.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbNit.setText("Incapacidad Numero: *");

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

        txtId.setEditable(false);
        txtId.setName("Incapacidad Numero"); // NOI18N
        txtId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIdKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIdKeyTyped(evt);
            }
        });

        txtIdentificacion.setEditable(false);
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
        lbRazon5.setText("Fecha De Nacimiento:");

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

        lbRazon8.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbRazon8.setText("Historia:");

        txtHistoria.setEditable(false);
        txtHistoria.setName("Historia"); // NOI18N
        txtHistoria.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtHistoriaKeyReleased(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Volver");
        jLabel11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/anterior_1.png"))); // NOI18N
        jLabel9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
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
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbRazon3)
                            .addComponent(lbRazon6)
                            .addComponent(lbRazon8, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbRazon1))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNombres)
                            .addComponent(txtIdentificacion, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtHistoria, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtEstado, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbRazon2, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lbRazon5, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                                .addComponent(lbRazon4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbNit, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbRazon7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtId, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                    .addComponent(txtEdad, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtNacimiento, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtSexo, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtTipoDocu, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(22, 22, 22)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbRazon1)
                            .addComponent(txtIdentificacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbRazon2)
                            .addComponent(txtTipoDocu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbRazon3)
                            .addComponent(txtSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbRazon5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbRazon6)
                            .addComponent(txtNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbRazon4)))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbRazon8)
                        .addComponent(txtHistoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbRazon7)
                        .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNit))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        lbRazon9.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbRazon9.setText("Fecha Inicio:");

        txtFechaInicio.setEditable(false);
        txtFechaInicio.setName("Fecha De Inicio"); // NOI18N
        txtFechaInicio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFechaInicioKeyReleased(evt);
            }
        });

        lbRazon10.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbRazon10.setText("Dias:");

        txtDias.setName("Dias"); // NOI18N
        txtDias.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDiasKeyReleased(evt);
            }
        });

        lbRazon11.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbRazon11.setText("Fecha Vencimiento:");

        txtCodigo.setEditable(false);
        txtCodigo.setName("Codigo"); // NOI18N
        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoKeyReleased(evt);
            }
        });

        txtFechaVencimiento.setEditable(false);
        txtFechaVencimiento.setName("Fecha De Vencimiento"); // NOI18N
        txtFechaVencimiento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFechaVencimientoKeyReleased(evt);
            }
        });

        lbRazon12.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbRazon12.setText("Cup:");

        txtCup.setEditable(false);
        txtCup.setName("CUP"); // NOI18N
        txtCup.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCupKeyReleased(evt);
            }
        });

        lbRazon13.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbRazon13.setText("Procedimiento:");

        lbRazon14.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbRazon14.setText("Cie:");

        txtCie.setEditable(false);
        txtCie.setName("Codigo"); // NOI18N
        txtCie.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCieKeyReleased(evt);
            }
        });

        lbRazon15.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbRazon15.setText("Diagnostico:");

        txtDiagnostico.setEditable(false);
        txtDiagnostico.setName("CUP"); // NOI18N
        txtDiagnostico.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDiagnosticoKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(lbRazon14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCie))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(lbRazon9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(lbRazon11)
                        .addGap(16, 16, 16)
                        .addComponent(txtFechaVencimiento)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbRazon13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbRazon10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbRazon15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(txtDias, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                        .addGap(10, 10, 10)
                        .addComponent(lbRazon12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtCup)
                    .addComponent(txtDiagnostico, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbRazon9)
                    .addComponent(txtFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbRazon10)
                    .addComponent(txtDias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbRazon12)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbRazon13)
                    .addComponent(txtCup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFechaVencimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbRazon11))
                .addGap(19, 19, 19)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbRazon14)
                    .addComponent(txtCie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbRazon15)
                    .addComponent(txtDiagnostico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        txtDescripcion.setColumns(20);
        txtDescripcion.setRows(3);
        txtDescripcion.setName("Descripcion"); // NOI18N
        txtDescripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDescripcionKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(txtDescripcion);

        jLabel4.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        jLabel4.setText("Descripcion:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        btnGuardar.setBackground(new java.awt.Color(0, 153, 153));
        btnGuardar.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
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
        btnGuardar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                btnGuardarKeyReleased(evt);
            }
        });

        btnBuscTerceros2.setBackground(new java.awt.Color(204, 204, 204));
        btnBuscTerceros2.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnBuscTerceros2.setForeground(new java.awt.Color(255, 255, 255));
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel5.setBackground(new java.awt.Color(102, 153, 0));
        jLabel5.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("INFORMACION INCAPACIDAD");
        jLabel5.setOpaque(true);

        javax.swing.GroupLayout pnlFormularioLayout = new javax.swing.GroupLayout(pnlFormulario);
        pnlFormulario.setLayout(pnlFormularioLayout);
        pnlFormularioLayout.setHorizontalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addContainerGap(11, Short.MAX_VALUE)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbNit10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(11, Short.MAX_VALUE))
        );
        pnlFormularioLayout.setVerticalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addComponent(lbNit10, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            .addComponent(scrFormulario)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnGuardarKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnGuardarActionPerformed(null);
        }
    }//GEN-LAST:event_btnGuardarKeyReleased

    public void reimprimir(String consecutivo) {
        instancias.getReporte().verIncapacidadOf(consecutivo, instancias.getInformacionEmpresa(), txtIdentificacion.getText(), txtTipoDocu.getText(),
                txtNombres.getText(), txtSexo.getText(), txtEstado.getText(), txtNacimiento.getText(), txtEdad.getText());
    }

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        Object[] campos = {txtId};
        String faltantes = metodos.camposVacios(campos);
        Object[] campos2 = {txtHistoria, txtFechaInicio, txtFechaVencimiento, txtCup};
        String faltantes2 = metodos.camposVacios(campos2);

        if (!faltantes.equals("")) {
            metodos.msgAdvertencia(this, "No puede continuar, faltan los siguientes campos: " + faltantes);
            return;
        } else if (!faltantes2.equals("")) {
            metodos.msgAdvertencia(this, "Faltan los siguientes campos: " + faltantes2);
        }

        if (metodos.msgPregunta(this, "¿Desea continuar?") == 0) {

            String id = "INCOF-" + instancias.getSql().getNumConsecutivo("INCOF")[0];
            ndIncapacidadOf nodo = instancias.getSql().getDatosIncapacidadOf(id);

            if (nodo.getId() != null) {
                metodos.msgError(this, "La Incapacidad ya existe");
                txtId.requestFocus();
                return;
            }

            if (txtDias.getText().equals("")) {
                txtDias.setText("0");
            }

            Object[] vector = {id, consulta1, txtFechaInicio.getText(), txtFechaVencimiento.getText(),
                txtCodigo.getText(), txtDescripcion.getText(), big.getBigDecimal(txtDias.getText()), txtCie.getText(), txtHistoria.getText()};

            nodo = metodos.llenarIncapacidadOf(vector);

            if (!instancias.getSql().agregarIncapacidadOf(nodo)) {
                metodos.msgError(this, "Hubo un problema al guardar la Incapacidad");
                return;
            }

            //CAMBIAR CONSECUTIVO INCAPACIDAD
            if (!instancias.getSql().aumentarConsecutivo("INCOF", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("INCOF")[0]) + 1)) {
                metodos.msgError(this, "Hubo un problema al guardar en el consecutivo de la incapacidad");
            }

            txtId.setText("INCOF-" + instancias.getSql().getNumConsecutivo("INCOF")[0]);

            limpiar();

            metodos.msgExito(this, "Incapacidad registrado con éxito");

            ndHistoriaClinica historia = instancias.getSql().getDatosHistClinica(txtHistoria.getText());
            String con[] = historia.getConsecutivos().split("&");
            int aux = Integer.parseInt(con[3]);
            aux++;
            instancias.getSql().modificarConsecutivoHistoria(historia.getId(), con[0] + "&" + con[1] + "&" + con[2] + "&" + aux);

            if (metodos.msgPregunta(this, "¿Desea imprimir?") == 0) {
                instancias.getReporte().verIncapacidadOf(id, instancias.getInformacionEmpresa(), txtIdentificacion.getText(), txtTipoDocu.getText(),
                        txtNombres.getText(), txtSexo.getText(), txtEstado.getText(), txtNacimiento.getText(), txtEdad.getText());
            }

            try {
                instancias.getHojaIngreso().setSelected(true);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(infHojaIngreso.class.getName()).log(Level.SEVERE, null, ex);
            }

//                btnNuevoActionPerformed(evt);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    public void limpiar() {
        txtDias.setText("");
        txtDescripcion.setText("");
        try {
            txtFechaVencimiento.setText(metodos.sumarFecha(txtFechaInicio.getText(), Integer.parseInt(txtDias.getText()) - 1));
        } catch (NumberFormatException exep) {
            txtFechaVencimiento.setText(metodos.sumarFecha(txtFechaInicio.getText(), 0));
        }
    }

    private void txtHistoriaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHistoriaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHistoriaKeyReleased

    private void txtEstadoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEstadoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEstadoKeyTyped

    private void txtEstadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEstadoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEstadoKeyReleased

    private void txtSexoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSexoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSexoKeyTyped

    private void txtSexoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSexoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSexoKeyReleased

    private void txtEdadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEdadKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEdadKeyTyped

    private void txtEdadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEdadKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEdadKeyReleased

    private void txtIdentificacionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdentificacionKeyTyped

    }//GEN-LAST:event_txtIdentificacionKeyTyped

    private void txtIdentificacionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdentificacionKeyReleased

    }//GEN-LAST:event_txtIdentificacionKeyReleased

    private void txtIdKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdKeyTyped

    private void txtIdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtDias.requestFocus();
        }

    }//GEN-LAST:event_txtIdKeyReleased

    private void txtTipoDocuKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTipoDocuKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTipoDocuKeyTyped

    private void txtTipoDocuKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTipoDocuKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTipoDocuKeyReleased

    private void txtNacimientoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNacimientoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNacimientoKeyTyped

    private void txtNacimientoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNacimientoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNacimientoKeyReleased

    private void txtDescripcionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnGuardar.requestFocus();
        }
    }//GEN-LAST:event_txtDescripcionKeyReleased

    private void txtFechaInicioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFechaInicioKeyReleased

    }//GEN-LAST:event_txtFechaInicioKeyReleased

    private void txtDiasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiasKeyReleased
        try {
            txtFechaVencimiento.setText(metodos.sumarFecha(txtFechaInicio.getText(), Integer.parseInt(txtDias.getText()) - 1));
        } catch (NumberFormatException exep) {
            txtFechaVencimiento.setText(metodos.sumarFecha(txtFechaInicio.getText(), 0));
        }
    }//GEN-LAST:event_txtDiasKeyReleased

    private void txtCodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoKeyReleased

    private void txtFechaVencimientoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFechaVencimientoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaVencimientoKeyReleased

    private void txtCupKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCupKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCupKeyReleased

    private void txtNombresKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombresKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombresKeyTyped

    private void txtNombresKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombresKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombresKeyReleased

    private void txtNombresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombresActionPerformed

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        try {
            instancias.getHojaIngreso().setSelected(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(infHojaIngreso.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLabel11MouseClicked

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        try {
            instancias.getHojaIngreso().setSelected(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(infHojaIngreso.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLabel9MouseClicked

    private void btnBuscTerceros2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscTerceros2ActionPerformed
//        String consecutivo = "INC-" + metodos.msgIngresar(this, "Ingrese número del documento que desea reimprimir");
//
////        nuevaIncapacidad(instancias.getSql().getDatosHistClinica(instancias.getSql().getDatosConsultaClinica(instancias.getSql().getDatosIncapacidad(consecutivo).getConsulta()).getHistoria()), "", "","","");
//        instancias.getReporte().verIncapacidad(consecutivo, instancias.getFactura().getInfo(), txtIdentificacion.getText(), txtTipoDocu.getText(),
//                txtNombres.getText(), txtSexo.getText(), txtEstado.getText(), txtNacimiento.getText(), txtEdad.getText());

        infReimpresionExamenes reimpresion = new infReimpresionExamenes();
        reimpresion.show();
        reimpresion.cargarRegistros(txtIdentificacion.getText(), "incapacidad", instancias, txtHistoria.getText());
    }//GEN-LAST:event_btnBuscTerceros2ActionPerformed

    private void txtCieKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCieKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCieKeyReleased

    private void txtDiagnosticoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiagnosticoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiagnosticoKeyReleased

    public void setInstancias(Instancias instancias) {
        this.instancias = instancias;
        txtFechaInicio.setText(metodosGenerales.fecha());
        txtFechaVencimiento.setText(metodosGenerales.fecha());
        txtId.setText("INCOF-" + instancias.getSql().getNumConsecutivo("INCOF")[0]);
    }

    public void cargarIncapacidad(String Id) {

        ndIncapacidad nodo = instancias.getSql().getDatosIncapacidad(Id);

        if (nodo.getId() != null) {
            txtHistoria.setText(nodo.getConsulta());
            txtFechaInicio.setText(nodo.getFechainicio());
            txtFechaVencimiento.setText(nodo.getFechavencimiento());
            txtCodigo.setText(nodo.getCup());
            txtCup.setText(instancias.getSql().getNombreCup(nodo.getCup()));
            txtCie.setText(nodo.getCie());
            txtDiagnostico.setText(instancias.getSql().getNombreCie(nodo.getCie()));
            txtDescripcion.setText(nodo.getDescripcion());
            txtHistoria.requestFocus();
            return;
        }
    }

    public void nuevaIncapacidad(String historia, String cup, String procedimiento, String cie, String diagnostico, String id, String consulta) {

        instancias = Instancias.getInstancias();

        consulta1 = consulta;

        try {
            this.setSelected(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(infIncapacidadOf.class.getName()).log(Level.SEVERE, null, ex);
        }

        ModeloContacto Paciente = instancias.getSql().getDatosTercero(id);

        txtId.setText("INCOF-" + instancias.getSql().getNumConsecutivo("INCOF")[0]);
        txtFechaInicio.setText(metodosGenerales.fecha());
        txtFechaVencimiento.setText(metodosGenerales.fecha());

        txtIdentificacion.setText(Paciente.getId());
        txtTipoDocu.setText(Paciente.getTipo());
        txtNombres.setText(Paciente.getpNombre() + " " + Paciente.getsNombre() + " " + Paciente.getpApellido() + " " + Paciente.getsApellido());
        txtSexo.setText(Paciente.getSexo());
        txtEstado.setText(Paciente.getEstado());
        txtNacimiento.setText(metodos.fecha(Paciente.getNacimiento()));
        txtEdad.setText(metodosGenerales.calcularEdad(metodos.fecha(Paciente.getNacimiento())) + "");
        txtHistoria.setText(historia);
        txtCup.setText(procedimiento);
        txtCodigo.setText(cup);
        txtCie.setText(cie);
        txtDiagnostico.setText(diagnostico);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscTerceros2;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JLabel lbNit;
    private javax.swing.JLabel lbNit10;
    private javax.swing.JLabel lbRazon1;
    private javax.swing.JLabel lbRazon10;
    private javax.swing.JLabel lbRazon11;
    private javax.swing.JLabel lbRazon12;
    private javax.swing.JLabel lbRazon13;
    private javax.swing.JLabel lbRazon14;
    private javax.swing.JLabel lbRazon15;
    private javax.swing.JLabel lbRazon2;
    private javax.swing.JLabel lbRazon3;
    private javax.swing.JLabel lbRazon4;
    private javax.swing.JLabel lbRazon5;
    private javax.swing.JLabel lbRazon6;
    private javax.swing.JLabel lbRazon7;
    private javax.swing.JLabel lbRazon8;
    private javax.swing.JLabel lbRazon9;
    private javax.swing.JPanel pnlFormulario;
    private javax.swing.JScrollPane scrFormulario;
    private javax.swing.JTextField txtCie;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtCup;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtDiagnostico;
    private javax.swing.JTextField txtDias;
    private javax.swing.JTextField txtEdad;
    private javax.swing.JTextField txtEstado;
    private javax.swing.JTextField txtFechaInicio;
    private javax.swing.JTextField txtFechaVencimiento;
    private javax.swing.JTextField txtHistoria;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtIdentificacion;
    private javax.swing.JTextField txtNacimiento;
    private javax.swing.JTextField txtNombres;
    private javax.swing.JTextField txtSexo;
    private javax.swing.JTextField txtTipoDocu;
    // End of variables declaration//GEN-END:variables

    private void cargando(String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
