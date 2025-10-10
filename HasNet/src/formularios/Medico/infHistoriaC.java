package formularios.Medico;

import clases.Instancias;
import clases.Medico.ndHistoriaClinica;
import clases.Medico.ndOrdenServicio;
import clases.metodosGenerales;
import Modelo.Terceros.ModeloContacto;
import formularios.terceros.buscClientes;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.table.DefaultTableModel;

public class infHistoriaC extends javax.swing.JInternalFrame {

    metodosGenerales metodos = new metodosGenerales();
    private Instancias instancias;
    private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension dimBarra = null;
    private String consecutivo = "";
    private ModeloContacto paciente = null;
    private String cup = "", proc = "";
    DefaultTableModel modelo;
    ndHistoriaClinica historia;

    public infHistoriaC() {
        initComponents();

        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
        dimBarra = Barra.getPreferredSize();
        Barra.setSize(0, 0);
        Barra.setPreferredSize(new Dimension(0, 0));
        setBorder(null);
        repaint();
        lblNuevaAyuda.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblNuevaConsulta.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblNuevaFormula.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblNuevaIncapacidad.setCursor(new Cursor(Cursor.HAND_CURSOR));
        txtId.requestFocus();
        txtIdSistema.setVisible(false);
        btnImprimir.setEnabled(false);

        instancias = Instancias.getInstancias();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        scrFormulario = new javax.swing.JScrollPane();
        pnlFormulario = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        lbNit = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtNombreCompleto = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtEstadoCivil = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtFechaNacimiento = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtEps = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtEdad = new javax.swing.JTextField();
        txtSexo = new javax.swing.JTextField();
        txtTipoDoc = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtOrden = new javax.swing.JTextField();
        btnOrdenesPendientes = new javax.swing.JButton();
        btnImprimir = new javax.swing.JButton();
        btnBuscTerceros = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtAyuda = new javax.swing.JTextField();
        txtIncapacidad = new javax.swing.JTextField();
        txtFormula = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        lblNuevaIncapacidad = new javax.swing.JLabel();
        lblNuevaFormula = new javax.swing.JLabel();
        lblNuevaAyuda = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        lblNuevaConsulta = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        txtHistoriaClinica = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        txtFechaP = new javax.swing.JTextField();
        txtDescripcionP = new javax.swing.JTextField();
        txtCieP = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        txtFechaU = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        txtCieU = new javax.swing.JTextField();
        txtDescripcionU = new javax.swing.JTextField();
        txtIdSistema = new javax.swing.JTextField();

        setTitle("Factura");

        pnlFormulario.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153)), "Datos Paciente", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 0, 14))); // NOI18N

        lbNit.setBackground(new java.awt.Color(255, 255, 255));
        lbNit.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNit.setText("Identificación:");
        lbNit.setOpaque(true);
        lbNit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lbNitKeyReleased(evt);
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

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("Tipo documento:");
        jLabel4.setOpaque(true);

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("Nombre:");
        jLabel5.setOpaque(true);

        txtNombreCompleto.setEditable(false);
        txtNombreCompleto.setBackground(new java.awt.Color(255, 255, 255));
        txtNombreCompleto.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtNombreCompleto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreCompletoActionPerformed(evt);
            }
        });

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setText("Estado Civil:");
        jLabel6.setOpaque(true);

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setText("Sexo:");
        jLabel7.setOpaque(true);

        txtEstadoCivil.setEditable(false);
        txtEstadoCivil.setBackground(new java.awt.Color(255, 255, 255));
        txtEstadoCivil.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtEstadoCivil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEstadoCivilActionPerformed(evt);
            }
        });

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel8.setText("Fecha Nacimiento:");
        jLabel8.setOpaque(true);

        txtFechaNacimiento.setEditable(false);
        txtFechaNacimiento.setBackground(new java.awt.Color(255, 255, 255));
        txtFechaNacimiento.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtFechaNacimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaNacimientoActionPerformed(evt);
            }
        });

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel9.setText("EPS:");
        jLabel9.setOpaque(true);

        txtEps.setEditable(false);
        txtEps.setBackground(new java.awt.Color(255, 255, 255));
        txtEps.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtEps.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEpsActionPerformed(evt);
            }
        });

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel10.setText("Edad:");
        jLabel10.setOpaque(true);

        txtEdad.setEditable(false);
        txtEdad.setBackground(new java.awt.Color(255, 255, 255));
        txtEdad.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtEdad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEdadActionPerformed(evt);
            }
        });

        txtSexo.setEditable(false);
        txtSexo.setBackground(new java.awt.Color(255, 255, 255));
        txtSexo.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtSexo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtSexo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSexoActionPerformed(evt);
            }
        });
        txtSexo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSexoKeyReleased(evt);
            }
        });

        txtTipoDoc.setEditable(false);
        txtTipoDoc.setBackground(new java.awt.Color(255, 255, 255));
        txtTipoDoc.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtTipoDoc.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtTipoDoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTipoDocActionPerformed(evt);
            }
        });
        txtTipoDoc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTipoDocKeyReleased(evt);
            }
        });

        jLabel19.setBackground(new java.awt.Color(255, 255, 255));
        jLabel19.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel19.setText("Nro. Orden");
        jLabel19.setOpaque(true);

        txtOrden.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtOrden.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtOrden.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtOrdenActionPerformed(evt);
            }
        });
        txtOrden.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtOrdenKeyReleased(evt);
            }
        });

        btnOrdenesPendientes.setBackground(new java.awt.Color(46, 204, 113));
        btnOrdenesPendientes.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnOrdenesPendientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/historia.png"))); // NOI18N
        btnOrdenesPendientes.setText("ORDEN");
        btnOrdenesPendientes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOrdenesPendientes.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnOrdenesPendientes.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnOrdenesPendientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrdenesPendientesActionPerformed(evt);
            }
        });

        btnImprimir.setBackground(new java.awt.Color(247, 220, 111));
        btnImprimir.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/imprimir.png"))); // NOI18N
        btnImprimir.setText("IMPRIMIR");
        btnImprimir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnImprimir.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnImprimir.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbNit, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtEps)
                                    .addComponent(txtEstadoCivil, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(23, 23, 23)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtFechaNacimiento, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                                    .addComponent(txtOrden))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(txtNombreCompleto)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtId)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBuscTerceros, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTipoDoc, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnOrdenesPendientes, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbNit, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtTipoDoc, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnBuscTerceros, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombreCompleto, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEps, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEstadoCivil, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnOrdenesPendientes)
                    .addComponent(btnImprimir))
                .addGap(5, 5, 5))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153)), "Información", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 0, 14))); // NOI18N

        jLabel12.setBackground(new java.awt.Color(255, 255, 255));
        jLabel12.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel12.setText("Ayudas Diagnosticas:");
        jLabel12.setOpaque(true);

        jLabel13.setBackground(new java.awt.Color(255, 255, 255));
        jLabel13.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel13.setText("Formulas Medicas:");
        jLabel13.setOpaque(true);

        jLabel14.setBackground(new java.awt.Color(255, 255, 255));
        jLabel14.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel14.setText("Incapacidades:");
        jLabel14.setOpaque(true);

        txtAyuda.setEditable(false);
        txtAyuda.setBackground(new java.awt.Color(255, 255, 255));
        txtAyuda.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtAyuda.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAyuda.setText("0");
        txtAyuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAyudaActionPerformed(evt);
            }
        });

        txtIncapacidad.setEditable(false);
        txtIncapacidad.setBackground(new java.awt.Color(255, 255, 255));
        txtIncapacidad.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtIncapacidad.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtIncapacidad.setText("0");
        txtIncapacidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIncapacidadActionPerformed(evt);
            }
        });

        txtFormula.setEditable(false);
        txtFormula.setBackground(new java.awt.Color(255, 255, 255));
        txtFormula.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtFormula.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFormula.setText("0");
        txtFormula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFormulaActionPerformed(evt);
            }
        });

        jLabel15.setBackground(new java.awt.Color(255, 255, 255));
        jLabel15.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel15.setText("Nueva Incapacidad:");
        jLabel15.setOpaque(true);

        jLabel16.setBackground(new java.awt.Color(255, 255, 255));
        jLabel16.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel16.setText("Nueva Formula:");
        jLabel16.setOpaque(true);

        jLabel17.setBackground(new java.awt.Color(255, 255, 255));
        jLabel17.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel17.setText("Nueva Ayuda:");
        jLabel17.setOpaque(true);

        lblNuevaIncapacidad.setBackground(new java.awt.Color(255, 255, 255));
        lblNuevaIncapacidad.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lblNuevaIncapacidad.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNuevaIncapacidad.setText(" ");
        lblNuevaIncapacidad.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        lblNuevaIncapacidad.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lblNuevaIncapacidad.setOpaque(true);
        lblNuevaIncapacidad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNuevaIncapacidadMouseClicked(evt);
            }
        });

        lblNuevaFormula.setBackground(new java.awt.Color(255, 255, 255));
        lblNuevaFormula.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lblNuevaFormula.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNuevaFormula.setText(" ");
        lblNuevaFormula.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        lblNuevaFormula.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lblNuevaFormula.setOpaque(true);
        lblNuevaFormula.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNuevaFormulaMouseClicked(evt);
            }
        });

        lblNuevaAyuda.setBackground(new java.awt.Color(255, 255, 255));
        lblNuevaAyuda.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lblNuevaAyuda.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNuevaAyuda.setText(" ");
        lblNuevaAyuda.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        lblNuevaAyuda.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lblNuevaAyuda.setOpaque(true);
        lblNuevaAyuda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNuevaAyudaMouseClicked(evt);
            }
        });

        jLabel26.setBackground(new java.awt.Color(255, 255, 255));
        jLabel26.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel26.setText("Nueva Consulta:");
        jLabel26.setOpaque(true);

        lblNuevaConsulta.setBackground(new java.awt.Color(255, 255, 255));
        lblNuevaConsulta.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lblNuevaConsulta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNuevaConsulta.setText(" ");
        lblNuevaConsulta.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        lblNuevaConsulta.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lblNuevaConsulta.setOpaque(true);
        lblNuevaConsulta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNuevaConsultaMouseClicked(evt);
            }
        });

        jLabel28.setBackground(new java.awt.Color(255, 255, 255));
        jLabel28.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel28.setText("Historia Clinica:");
        jLabel28.setOpaque(true);

        txtHistoriaClinica.setEditable(false);
        txtHistoriaClinica.setBackground(new java.awt.Color(255, 255, 255));
        txtHistoriaClinica.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtHistoriaClinica.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtHistoriaClinica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHistoriaClinicaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtFormula, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtAyuda, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtHistoriaClinica, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtIncapacidad, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblNuevaIncapacidad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblNuevaFormula, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblNuevaAyuda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblNuevaConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(65, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHistoriaClinica, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNuevaConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAyuda, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNuevaAyuda, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFormula, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNuevaFormula, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtIncapacidad, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblNuevaIncapacidad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153)), "Consultas", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 0, 14))); // NOI18N

        jLabel23.setBackground(new java.awt.Color(204, 204, 204));
        jLabel23.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("Primera");
        jLabel23.setOpaque(true);

        jLabel24.setBackground(new java.awt.Color(255, 255, 255));
        jLabel24.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel24.setText("Fecha:");
        jLabel24.setOpaque(true);

        jLabel25.setBackground(new java.awt.Color(255, 255, 255));
        jLabel25.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel25.setText("Cie:");
        jLabel25.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel25.setOpaque(true);

        txtFechaP.setEditable(false);
        txtFechaP.setBackground(new java.awt.Color(255, 255, 255));
        txtFechaP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaPActionPerformed(evt);
            }
        });

        txtDescripcionP.setEditable(false);
        txtDescripcionP.setBackground(new java.awt.Color(255, 255, 255));
        txtDescripcionP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescripcionPActionPerformed(evt);
            }
        });

        txtCieP.setEditable(false);
        txtCieP.setBackground(new java.awt.Color(255, 255, 255));
        txtCieP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCiePActionPerformed(evt);
            }
        });

        jLabel27.setBackground(new java.awt.Color(204, 204, 204));
        jLabel27.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("Ultima");
        jLabel27.setOpaque(true);

        txtFechaU.setEditable(false);
        txtFechaU.setBackground(new java.awt.Color(255, 255, 255));
        txtFechaU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaUActionPerformed(evt);
            }
        });

        jLabel29.setBackground(new java.awt.Color(255, 255, 255));
        jLabel29.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("Fecha:");
        jLabel29.setOpaque(true);

        jLabel30.setBackground(new java.awt.Color(255, 255, 255));
        jLabel30.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setText("Cie:");
        jLabel30.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel30.setOpaque(true);

        txtCieU.setEditable(false);
        txtCieU.setBackground(new java.awt.Color(255, 255, 255));
        txtCieU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCieUActionPerformed(evt);
            }
        });

        txtDescripcionU.setEditable(false);
        txtDescripcionU.setBackground(new java.awt.Color(255, 255, 255));
        txtDescripcionU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescripcionUActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtDescripcionP)
                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFechaP, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCieP, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel29)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFechaU, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCieU, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtDescripcionU))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFechaP, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFechaU, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCieP, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCieU, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtDescripcionP, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDescripcionU, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                .addGap(14, 14, 14))
        );

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

        javax.swing.GroupLayout pnlFormularioLayout = new javax.swing.GroupLayout(pnlFormulario);
        pnlFormulario.setLayout(pnlFormularioLayout);
        pnlFormularioLayout.setHorizontalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtIdSistema, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlFormularioLayout.setVerticalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtIdSistema, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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
            .addComponent(scrFormulario)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdActionPerformed

    private void txtNombreCompletoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreCompletoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreCompletoActionPerformed

    private void txtEstadoCivilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEstadoCivilActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEstadoCivilActionPerformed

    private void txtFechaNacimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaNacimientoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaNacimientoActionPerformed

    private void txtEpsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEpsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEpsActionPerformed

    private void txtEdadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEdadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEdadActionPerformed

    private void txtIdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            setPaciente(txtId.getText());
        } else {
            limpiar();
        }
    }//GEN-LAST:event_txtIdKeyReleased

    private void txtSexoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSexoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSexoActionPerformed

    private void txtSexoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSexoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSexoKeyReleased

    private void txtTipoDocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTipoDocActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTipoDocActionPerformed

    private void txtTipoDocKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTipoDocKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTipoDocKeyReleased

    private void txtAyudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAyudaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAyudaActionPerformed

    private void txtIncapacidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIncapacidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIncapacidadActionPerformed

    private void txtFormulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFormulaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFormulaActionPerformed

    private void txtFechaPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaPActionPerformed

    private void txtDescripcionPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescripcionPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescripcionPActionPerformed

    private void txtCiePActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCiePActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCiePActionPerformed

    private void txtHistoriaClinicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHistoriaClinicaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHistoriaClinicaActionPerformed

    private void lblNuevaAyudaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNuevaAyudaMouseClicked
        if (consecutivo.equals("")) {
            metodos.msgAdvertencia(this, "Debe de generar una consulta asociada");
            return;
        }

        instancias.getAyudaDiagnostica().nuevaAyuda(historia);
    }//GEN-LAST:event_lblNuevaAyudaMouseClicked

    private void lblNuevaFormulaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNuevaFormulaMouseClicked
        if (consecutivo.equals("")) {
            metodos.msgAdvertencia(this, "Debe de generar una consulta asociada");
            return;
        }

        instancias.getFormulaMedica().nuevaFormula(historia, "", "", "", "", "", "");
    }//GEN-LAST:event_lblNuevaFormulaMouseClicked

    private void lblNuevaIncapacidadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNuevaIncapacidadMouseClicked
        if (consecutivo.equals("") || cup.equals("") || proc.equals("")) {
            metodos.msgAdvertencia(this, "Debe de generar una consulta asociada");
            return;
        }

        instancias.getIncapacidad().nuevaIncapacidad(historia, cup, proc, "", "");
    }//GEN-LAST:event_lblNuevaIncapacidadMouseClicked

    private void lblNuevaConsultaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNuevaConsultaMouseClicked
        if (!paciente.getId().equals("")) {
            boolean vacio = false;

            if (!instancias.isGeneraOrdenMedica()) {
                instancias.getHistoriaClinica().cargar(paciente.getId(), null);
                String cita = "";
                String cliente = txtId.getText();

                try {
                    cita = instancias.getSql().getAgendasDelDia(cliente, metodos.fechaConsulta(metodosGenerales.fecha()));
                } catch (Exception e) {
                }
                if (!instancias.getSql().modificarCita(cita, "Confirmada")) {
                    metodos.msgError(null, "No fue posible modificar el estado de la Cita");
                }
            } else {

                if (txtOrden.getText().equals("")) {
                    metodos.msgAdvertencia(this, "Debe tener una orden asociada, de lo contrario no podrá guardar una nueva consulta clinica");
                    vacio = true;
                }

                if (!vacio) {
                    ndOrdenServicio nodo = instancias.getSql().getDatosOrdenServicio(txtOrden.getText());

                    if (nodo.getPaciente() == null) {
                        metodos.msgError(this, "Esta orden no existe");
                        return;
                    }

                    if (!nodo.getPaciente().equals(paciente.getIdSistema())) {
                        metodos.msgError(this, "La orden no percenece al paciente");
                        return;
                    }

                    instancias.getHistoriaClinica().cargar(paciente.getId(), nodo);
                } else {
                    instancias.getHistoriaClinica().cargar(paciente.getId(), null);
                }
            }

        }
    }//GEN-LAST:event_lblNuevaConsultaMouseClicked

    private void txtFechaUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaUActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaUActionPerformed

    private void txtCieUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCieUActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCieUActionPerformed

    private void txtDescripcionUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescripcionUActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescripcionUActionPerformed

    private void txtOrdenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOrdenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOrdenActionPerformed

    private void txtOrdenKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtOrdenKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (paciente != null) {
                ventanaOrden(paciente.getId());
            }
        }

    }//GEN-LAST:event_txtOrdenKeyReleased

    private void btnOrdenesPendientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrdenesPendientesActionPerformed
        infReimpresionExamenes reimpresion = new infReimpresionExamenes();
        reimpresion.setMetodos(metodos);
        reimpresion.cargarRegistros(instancias.getSql().getNombreEmpleadoUsuario(instancias.getUsuario()),
                "ordenListado", instancias);
        reimpresion.setOpc(1);
    }//GEN-LAST:event_btnOrdenesPendientesActionPerformed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        if (!txtId.getText().equals("")) {
            ModeloContacto Paciente = instancias.getSql().getDatosTercero(txtId.getText());
            if (Paciente.getId() != null) {
                instancias.getReporte().verHistoria(historia.getId(), "" + metodosGenerales.calcularEdad2(metodos.fecha(Paciente.getNacimiento())));
            } else {
                metodos.msgError(this, "El paciente con identificación " + txtId.getText() + " no existe");
            }
        }
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void lbNitKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lbNitKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtId.requestFocus();
        }
    }//GEN-LAST:event_lbNitKeyReleased

    private void btnBuscTercerosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscTercerosActionPerformed
        ventanaTerceros("");
    }//GEN-LAST:event_btnBuscTercerosActionPerformed

    private void txtIdSistemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdSistemaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdSistemaActionPerformed

    private void txtIdSistemaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdSistemaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdSistemaKeyReleased

    public void ventanaTerceros(String nit) {
        buscClientes buscar = new buscClientes(instancias.getMenu(), rootPaneCheckingEnabled, false, null, "");
        buscar.setLocationRelativeTo(null);
        instancias.setBusClientes(buscar);
        instancias.setCampoActual(txtId);
        txtId.requestFocus();
        buscar.noEncontrado(nit);
        buscar.show();
    }

    public void setPaciente(String paciente) {

        ModeloContacto Paciente = instancias.getSql().getDatosTercero(paciente);
        this.paciente = Paciente;
        if (Paciente.getId() == null) {
            ventanaTerceros(paciente);
        } else {
            if (Paciente.isActivo()) {
                metodos.msgError(null, "Este cliente esta inactivado");
                lbNit.requestFocus();
                return;
            }

            txtId.setText(Paciente.getId());
            txtIdSistema.setText(Paciente.getIdSistema());
            txtNombreCompleto.setText(Paciente.getNombre());
            txtTipoDoc.setText(Paciente.getTipo());
            txtEdad.setText(metodosGenerales.calcularEdad2(metodos.fecha(Paciente.getNacimiento())));

            ndHistoriaClinica nodo = instancias.getSql().getDatosHistoriaClinica(Paciente.getIdSistema());
            historia = nodo;

            try {
                if (Paciente.getSexo().equalsIgnoreCase("Masculino")) {
                    txtSexo.setText("M");
                } else {
                    txtSexo.setText("F");
                }
            } catch (Exception e) {
                txtSexo.setText("");
            }

            try {
                txtEstadoCivil.setText(Paciente.getEstado());
            } catch (Exception e) {
                txtEstadoCivil.setText("");
            }

            try {
                txtFechaNacimiento.setText(metodos.fecha(Paciente.getNacimiento()));
            } catch (Exception e) {
                txtFechaNacimiento.setText("");
            }
            try {
                txtEps.setText(Paciente.getEps());
            } catch (Exception e) {
                txtEps.setText("");
            }

            btnImprimir.setEnabled(true);
            txtHistoriaClinica.setText(historia.getId());
            txtOrden.setText("");

            if (instancias.isGeneraOrdenMedica()) {
                btnOrdenesPendientes.setEnabled(true);
            }

            resultados();
        }
    }

    private void limpiar() {

        if (!instancias.isGeneraOrdenMedica()) {
            btnOrdenesPendientes.setEnabled(false);
        } else {
            btnOrdenesPendientes.setEnabled(true);
        }

        btnImprimir.setEnabled(false);
        txtNombreCompleto.setText("");
        txtTipoDoc.setText("");
        txtEdad.setText("");
        txtSexo.setText("");
        txtEstadoCivil.setText("");
        txtIdSistema.setText("");
        txtFechaNacimiento.setText("");
        txtEps.setText("");
        txtHistoriaClinica.setText("");
        txtFechaP.setText("");
        txtCieP.setText("");
        txtDescripcionP.setText("");
        txtFechaU.setText("");
        txtCieU.setText("");
        txtDescripcionU.setText("");
        txtOrden.setText("");
        txtAyuda.setText("0");
        txtFormula.setText("0");
        txtIncapacidad.setText("0");
        consecutivo = "";
        paciente = null;
        historia = null;
        cup = "";
        proc = "";

    }

    public void resultados() {
        lblNuevaConsulta.setText(instancias.getSql().getNumConsecutivo("CONSULTA")[0].toString() + "-" + metodosGenerales.fecha());
        lblNuevaConsulta.repaint();

        lblNuevaAyuda.setText("ADIAG-" + instancias.getSql().getNumConsecutivo("ADIAG")[0]);
        lblNuevaFormula.setText("FORM-" + instancias.getSql().getNumConsecutivo("FORM")[0]);
        lblNuevaIncapacidad.setText("INC-" + instancias.getSql().getNumConsecutivo("INC")[0]);

        try {
            txtAyuda.setText(historia.getConsecutivos().split("&")[1]);
            txtFormula.setText(historia.getConsecutivos().split("&")[2]);
            txtIncapacidad.setText(historia.getConsecutivos().split("&")[3]);
        } catch (Exception e) {
            String hClinica = "HSTC-" + txtId.getText();
            Object[] vector2 = {hClinica, txtIdSistema.getText(), metodos.fechaConsulta(metodosGenerales.fecha()), "",
                metodos.fechaConsulta(metodosGenerales.fecha()), metodos.fechaConsulta(metodosGenerales.fecha()), "", "", "", "", "", "0&0&0&0", "", "", ""};

            ndHistoriaClinica nodoDos = metodos.llenarHistClinica(vector2);
            instancias.getSql().agregarHistClinica(nodoDos);

            ndHistoriaClinica nodo = instancias.getSql().getDatosHistoriaClinica(txtIdSistema.getText());
            historia = nodo;

            txtAyuda.setText(historia.getConsecutivos().split("&")[1]);
            txtFormula.setText(historia.getConsecutivos().split("&")[2]);
            txtIncapacidad.setText(historia.getConsecutivos().split("&")[3]);

            txtHistoriaClinica.setText(historia.getId());
        }

        Object aux[][] = instancias.getSql().getConsultaPorHistorial(txtHistoriaClinica.getText());

        if (aux.length > 0) {
            ordenar(aux);

//            for (int i = 0; i < aux.length; i++) {
//                for (int j = 0; j < aux[i].length; j++) {
//                    System.out.print(aux[i][j] + "\t");
//                }
//            }
            String cieP = String.valueOf(aux[0][2]);
            String cieU = aux[aux.length - 1][2].toString();

            txtFechaP.setText(metodos.formatoFecha(aux[0][1].toString()));
            txtCieP.setText(cieP);
            if (!cieP.equals("")) {
                txtDescripcionP.setText(instancias.getSql().getNombreCie(cieP));
            }

            txtFechaU.setText(metodos.formatoFecha(aux[aux.length - 1][1].toString()));
            txtCieU.setText(aux[aux.length - 1][2].toString());
            if (!cieU.equals("")) {
                txtDescripcionU.setText(instancias.getSql().getNombreCie(cieU));
            }
        }

        this.repaint();
    }

    public void ordenar(Object[][] aux) {
        for (int i = 0; i < aux.length - 1; i++) {
            for (int k = i + 1; k < aux.length; k++) {
                if (Integer.parseInt(aux[i][0].toString()) > Integer.parseInt(aux[k][0].toString())) {
                    for (int j = 0; j < aux[i].length; j++) {
                        String aux2 = aux[i][j].toString();
                        aux[i][j] = aux[k][j];
                        aux[k][j] = aux2;
                    }
                }
            }

        }
    }

    public void actualizar(String consecutivo, String cup, String proc) {
//        try {
//            this.setSelected(true);
//        } catch (PropertyVetoException ex) {
//            Logger.getLogger(infHistoriaC.class.getName()).log(Level.SEVERE, null, ex);
//        }

//        setPaciente(paciente.getId());
        this.consecutivo = consecutivo;
        this.cup = cup;
        this.proc = proc;
    }

    public void actualizar2(String consecutivo) {
        this.consecutivo = consecutivo;
        try {
            this.setSelected(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(infHistoriaC.class.getName()).log(Level.SEVERE, null, ex);
        }
        setPaciente(paciente.getId());
    }

    private void ventanaOrden(String paciente) {
        infReimpresionExamenes reimpresion = new infReimpresionExamenes();
        reimpresion.cargarRegistros(paciente, "orden", instancias);
        reimpresion.setOpc(1);
    }

    public void cargarPacienteOrden(String orden, String paciente) {
        setPaciente(paciente);
        txtOrden.setText(orden);
    }

    public void cargarOrdenServicio(String orden) {
        txtOrden.setText(orden);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscTerceros;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnOrdenesPendientes;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbNit;
    private javax.swing.JLabel lblNuevaAyuda;
    private javax.swing.JLabel lblNuevaConsulta;
    private javax.swing.JLabel lblNuevaFormula;
    private javax.swing.JLabel lblNuevaIncapacidad;
    private javax.swing.JPanel pnlFormulario;
    private javax.swing.JScrollPane scrFormulario;
    private javax.swing.JTextField txtAyuda;
    private javax.swing.JTextField txtCieP;
    private javax.swing.JTextField txtCieU;
    private javax.swing.JTextField txtDescripcionP;
    private javax.swing.JTextField txtDescripcionU;
    private javax.swing.JTextField txtEdad;
    private javax.swing.JTextField txtEps;
    private javax.swing.JTextField txtEstadoCivil;
    private javax.swing.JTextField txtFechaNacimiento;
    private javax.swing.JTextField txtFechaP;
    private javax.swing.JTextField txtFechaU;
    private javax.swing.JTextField txtFormula;
    private javax.swing.JTextField txtHistoriaClinica;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtIdSistema;
    private javax.swing.JTextField txtIncapacidad;
    private javax.swing.JTextField txtNombreCompleto;
    private javax.swing.JTextField txtOrden;
    private javax.swing.JTextField txtSexo;
    private javax.swing.JTextField txtTipoDoc;
    // End of variables declaration//GEN-END:variables
}
