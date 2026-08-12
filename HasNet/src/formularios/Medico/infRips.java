package formularios.Medico;

import formularios.Medico.*;
import clases.Instancias;
import clases.Medico.ndEpsPrecargados;
import clases.big;
import clases.metodosGenerales;
import clases.productos.ndProducto;
import Vista.Productos.VistaBuscadorProductos;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import javax.swing.JComponent;
import javax.swing.table.DefaultTableModel;

public class infRips extends javax.swing.JInternalFrame {

    metodosGenerales metodos = new metodosGenerales();
    private Instancias instancias;
    private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension dimBarra = null;
    DefaultTableModel modelo;

    public infRips() {
        initComponents();

        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
        dimBarra = Barra.getPreferredSize();
        Barra.setSize(0, 0);
        Barra.setPreferredSize(new Dimension(0, 0));
        setBorder(null);
        repaint();
        instancias = Instancias.getInstancias();
        buttonGroup1.add(jRadioButton1);
        buttonGroup1.add(jRadioButton2);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        popBorrar = new javax.swing.JMenuItem();
        scrFormulario = new javax.swing.JScrollPane();
        pnlFormulario = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        txtId = new javax.swing.JTextField();
        lbRazon1 = new javax.swing.JLabel();
        lbRazon2 = new javax.swing.JLabel();
        txtRazon = new javax.swing.JTextField();
        lbNit8 = new javax.swing.JLabel();
        dtInicio = new datechooser.beans.DateChooserCombo();
        lbNit9 = new javax.swing.JLabel();
        dtFinal = new datechooser.beans.DateChooserCombo();
        lbCiudad6 = new javax.swing.JLabel();
        cmbRegimen = new javax.swing.JComboBox();
        lbNit11 = new javax.swing.JLabel();
        txtNumFacturas = new javax.swing.JTextField();
        lbNit14 = new javax.swing.JLabel();
        dtFechaFactura = new datechooser.beans.DateChooserCombo();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jButton4 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        lbRazon3 = new javax.swing.JLabel();
        txtId1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        chUs = new javax.swing.JCheckBox();
        chAf = new javax.swing.JCheckBox();
        chAd = new javax.swing.JCheckBox();
        chAc = new javax.swing.JCheckBox();
        chAp = new javax.swing.JCheckBox();
        chCt = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        popBorrar.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        popBorrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/eliminar-cancelar-icono-4935-16.png"))); // NOI18N
        popBorrar.setText("Borrar");
        popBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                popBorrarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(popBorrar);

        setTitle("Factura");

        pnlFormulario.setBackground(new java.awt.Color(255, 255, 255));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Filtros", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 18))); // NOI18N

        txtId.setName("Codigo"); // NOI18N
        txtId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIdKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIdKeyTyped(evt);
            }
        });

        lbRazon1.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbRazon1.setText("EPS:");

        lbRazon2.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbRazon2.setText("Nombre:");

        txtRazon.setEditable(false);
        txtRazon.setName("Codigo"); // NOI18N
        txtRazon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtRazonKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRazonKeyTyped(evt);
            }
        });

        lbNit8.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit8.setText("Fecha inicial:");

        dtInicio.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 12));

        lbNit9.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit9.setText("Fecha final:");

        dtFinal.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 12));

        lbCiudad6.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbCiudad6.setText("Regimen:");

        cmbRegimen.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        cmbRegimen.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione ...", "Contributivo", "Subsidiado parcial", "Subsidiado total", "Población Pobre No Asegurada Con SISBEN", "Población Pobre No Asegurada Sin SISBEN", "Desplazado", "Plan adicional de salud", "Vinculado", "Particular", "Otro" }));
        cmbRegimen.setName("Regimen"); // NOI18N
        cmbRegimen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbRegimenKeyReleased(evt);
            }
        });

        lbNit11.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit11.setText("Factura #:");

        lbNit14.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit14.setText("Fecha Elab, y/o Factura:");

        dtFechaFactura.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 12));

        jRadioButton1.setBackground(new java.awt.Color(255, 255, 255));
        jRadioButton1.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jRadioButton1.setText("Generales");
        jRadioButton1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButton1ItemStateChanged(evt);
            }
        });

        jRadioButton2.setBackground(new java.awt.Color(255, 255, 255));
        jRadioButton2.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jRadioButton2.setSelected(true);
        jRadioButton2.setText("Otros");
        jRadioButton2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButton2ItemStateChanged(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(204, 204, 204));
        jButton4.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jButton4.setText("Limpiar");
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lbNit9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbNit8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbRazon1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dtInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(dtFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lbRazon2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbCiudad6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbNit14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(dtFechaFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                        .addComponent(jRadioButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButton2))
                    .addComponent(cmbRegimen, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtRazon))
                .addGap(28, 28, 28)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(lbNit11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNumFacturas, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton4))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lbNit8)
                        .addComponent(dtInicio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbNit14, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dtFechaFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jRadioButton1)
                        .addComponent(jRadioButton2)
                        .addComponent(lbNit11)
                        .addComponent(txtNumFacturas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(lbNit9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(213, 213, 213))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lbCiudad6)
                                        .addComponent(cmbRegimen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(dtFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(3, 3, 3)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lbRazon2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtId, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                                        .addComponent(lbRazon1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtRazon))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Procedimientos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 18))); // NOI18N

        lbRazon3.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbRazon3.setText("Codigo:");

        txtId1.setName("Codigo"); // NOI18N
        txtId1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtId1KeyReleased(evt);
            }
        });

        jTable1.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Descripción", "Cantidad", "Subtotal", "Copago", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setComponentPopupMenu(jPopupMenu1);
        jTable1.setRowHeight(24);
        jScrollPane1.setViewportView(jTable1);

        jButton2.setBackground(new java.awt.Color(204, 204, 204));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton2.setText("Todos");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbRazon3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtId1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbRazon3)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtId1, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                        .addComponent(jButton2)))
                .addGap(6, 6, 6)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ordenes", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 18))); // NOI18N

        jTable2.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Tipo Id", "Nombre", "Procedimiento", "Eps", "Numero Autorizacion", "Cie", "Cup", "Cant. Realizada", "Copago", "Valor", "Seleccionar"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.setRowHeight(24);
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        chUs.setBackground(new java.awt.Color(255, 255, 255));
        chUs.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        chUs.setSelected(true);
        chUs.setText("US");

        chAf.setBackground(new java.awt.Color(255, 255, 255));
        chAf.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        chAf.setSelected(true);
        chAf.setText("AF");

        chAd.setBackground(new java.awt.Color(255, 255, 255));
        chAd.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        chAd.setSelected(true);
        chAd.setText("AD");

        chAc.setBackground(new java.awt.Color(255, 255, 255));
        chAc.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        chAc.setSelected(true);
        chAc.setText("AC");

        chAp.setBackground(new java.awt.Color(255, 255, 255));
        chAp.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        chAp.setSelected(true);
        chAp.setText("AP");

        chCt.setBackground(new java.awt.Color(255, 255, 255));
        chCt.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        chCt.setSelected(true);
        chCt.setText("CT");

        jButton1.setBackground(new java.awt.Color(204, 204, 204));
        jButton1.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jButton1.setText("Generar");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(204, 204, 204));
        jButton3.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jButton3.setText("Detalle Cargo Y Plano Excel");
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(chUs)
                        .addGap(18, 18, 18)
                        .addComponent(chAf)
                        .addGap(18, 18, 18)
                        .addComponent(chAd)
                        .addGap(18, 18, 18)
                        .addComponent(chAc)
                        .addGap(18, 18, 18)
                        .addComponent(chAp)
                        .addGap(18, 18, 18)
                        .addComponent(chCt)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chUs)
                    .addComponent(chAf)
                    .addComponent(chAd)
                    .addComponent(chAc)
                    .addComponent(chAp)
                    .addComponent(chCt)
                    .addComponent(jButton1)
                    .addComponent(jButton3))
                .addGap(21, 21, 21)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout pnlFormularioLayout = new javax.swing.GroupLayout(pnlFormulario);
        pnlFormulario.setLayout(pnlFormularioLayout);
        pnlFormularioLayout.setHorizontalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlFormularioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlFormularioLayout.setVerticalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
            .addComponent(scrFormulario, javax.swing.GroupLayout.DEFAULT_SIZE, 806, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtIdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cargarEps(txtId.getText());
        } else {
            txtRazon.setText("");
        }
    }//GEN-LAST:event_txtIdKeyReleased

    public void cargarEps(String nit) {

        ndEpsPrecargados nodo = instancias.getSql().getDatosEpsPrecargados(nit);

        if (nodo.getId() != null) {
            txtRazon.setText(nodo.getNombre());
            txtRazon.requestFocus();
            return;
        }
        ventanaEps(nit);
    }

    public void ventanaEps(String nit) {
        buscEpsPrecargadas buscar = new buscEpsPrecargadas(instancias.getMenu(), rootPaneCheckingEnabled);
        buscar.setLocationRelativeTo(null);
        instancias.setBuscEpsPre(buscar);
        instancias.setCampoActual(txtId);
        txtId.requestFocus();
        buscar.noEncontrado(nit);
        buscar.show();
    }
    private void txtIdKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdKeyTyped

    }//GEN-LAST:event_txtIdKeyTyped

    private void txtRazonKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRazonKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRazonKeyReleased

    private void txtRazonKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRazonKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRazonKeyTyped

    private void cmbRegimenKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbRegimenKeyReleased

    }//GEN-LAST:event_cmbRegimenKeyReleased

    private String condicionOrden() {
        return " WHERE estado2='ON-" + metodosGenerales.fecha() + "'";
    }

    private String formatoRips(Object[][] datos, Integer fecha) {
        String us = "";
        for (int i = 0; i < datos.length; i++) {
            for (int j = 0; j < datos[i].length; j++) {
                if (fecha != null) {
                    if (j == fecha) {
                        String miFecha = "";
//                        if (datos[i][j].toString().length() > 10) {
//                            String[] splitFecha = datos[i][j].toString().substring(0, 11).replace("-", "/").split("/");
//                            if (splitFecha[0].length() == 4) {
//                                miFecha = splitFecha[2] + "/" + splitFecha[1] + "/" + splitFecha[0];
//                            } else {
//                                miFecha = splitFecha[0] + "/" + splitFecha[1] + "/" + splitFecha[2];
//                            }
//                        } else {
                        System.out.println("fecha " + datos[i][j].toString());
                        String[] splitFecha = datos[i][j].toString().substring(0, 10).replace("-", "/").split("/");

                        if (splitFecha[0].length() == 4) {
                            miFecha = splitFecha[2] + "/" + splitFecha[1] + "/" + splitFecha[0];
                        } else {
                            miFecha = splitFecha[0] + "/" + splitFecha[1] + "/" + splitFecha[2];
//                            }
                        }

                        us = us + "" + miFecha + ",";

                    } else {
                        us = us + datos[i][j] + ",";
                    }
                } else {
                    us = us + datos[i][j] + ",";
                }
            }

            us = us.substring(0, us.length() - 1);
            us = us + "\n";
        }
        return us;
    }


    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String base = "", base1 = "", base2 = "", base3 = "", base4 = "", base5 = "";
        if (instancias.getConfiguraciones().isMedico()) {
            base = "bdClickPpal";
            base1 = "af";
            base2 = "us";
            base3 = "ad";
            base4 = "ap";
            base5 = "ac";
        } else if (instancias.getConfiguraciones().isOftalmologia()) {
            base = "ripsOftalmologia";
            base1 = "afOftalmologia";
            base2 = "usOftalmologia";
            base3 = "adOftalmologia";
            base4 = "apOftalmologia";
            base5 = "acOftalmologia";
        } else if (instancias.getConfiguraciones().isLaboratorio()) {
            base = "ripsLaboratorio";
            base1 = "afLaboratorio";
            base2 = "usLaboratorio";
            base3 = "adLaboratorio";
            base4 = "apLaboratorio";
            base5 = "acLaboratorio";
        }

        Object[][] datosPrestador = instancias.getSql().getCodigoPrestadorServicio(base);

        String codPrestadorServicios = datosPrestador[0][0].toString();
        String razonSocialPrestadorServicios = datosPrestador[0][1].toString();
        String tipoPrestadorServicio = datosPrestador[0][2].toString();
        String idPrestardorServicio = datosPrestador[0][3].toString();

        if (razonSocialPrestadorServicios.length() > 60) {
            metodos.msgError(this, "Por favor verifique el nombre del prestador de servicio para poder continuar.");
            return;
        }

        if (codPrestadorServicios.length() < 10 || codPrestadorServicios.length() > 10) {
            metodos.msgError(this, "Por favor verifique el codigo del prestador de servicio para poder continuar.");
            return;
        }

        if (tipoPrestadorServicio.equals("") || tipoPrestadorServicio.length() != 2) {
            metodos.msgError(this, "Por favor verifique el tipo del prestador de servicio para poder continuar.");
            return;
        }
        if (idPrestardorServicio.equals("")) {
            metodos.msgError(this, "Por favor id del prestador de servicio para poder continuar.");
            return;
        }

        String mes = metodosGenerales.mes();
        String anho = metodosGenerales.anho();

        if (mes.length() == 1) {
            mes = "0" + mes;
        }

        String us = "US" + mes + "" + anho,
                af = "AF" + mes + "" + anho,
                ad = "AD" + mes + "" + anho,
                ap = "AP" + mes + "" + anho,
                ac = "AC" + mes + "" + anho;

        int cantUs = 0, cantAf = 0, cantAd = 0, cantAp = 0, cantAc = 0;

        String sql = "";

        // AF
        if (chAf.isSelected()) {
            if (sql.equals("")) {
                sql = condicionOrden();
            }

            Object[][] datos;
            if (!txtNumFacturas.getText().equals("")) {
                datos = instancias.getSql().getAf(sql, new String[]{txtNumFacturas.getText(), metodos.fecha(metodos.desdeDate(dtFechaFactura.getSelectedDate())), metodos.fecha(metodos.desdeDate(dtInicio.getSelectedDate())), metodos.fecha(metodos.desdeDate(dtFinal.getSelectedDate())), ""}, base1);
            } else {
                System.out.println("basesss " + base1);
                datos = instancias.getSql().getAfGenerales(sql, new String[]{"", metodos.fecha(metodos.desdeDate(dtFechaFactura.getSelectedDate())), metodos.fecha(metodos.desdeDate(dtInicio.getSelectedDate())), metodos.fecha(metodos.desdeDate(dtFinal.getSelectedDate())), ""}, base1);
            }

            cantAf = datos.length;
            int mensajeId = 0;
            for (int i = 0; i < cantAf; i++) {
                //Si el nombre de la entidad administradora (eps) es mayor a 30 caracteres.
                int idEntidadAdministradora = datos[i][8].toString().length();
                if (idEntidadAdministradora > 6 && mensajeId == 0) {
                    metodos.msgError(this, "Tiene un ERROR en el id " + datos[i][8] + " nombre: " + datos[i][9] + ", debe tener 6 caracteres, ARCHIVO AF");
                    mensajeId++;
                }
                int longNombreEntidadAdministradora = datos[i][9].toString().length();
                if (longNombreEntidadAdministradora > 30) {
                    datos[i][9] = datos[i][9].toString().substring(0, 30);
                }
            }
            metodosGenerales.generarArchivoPlano("rips/" + af + ".txt", formatoRips(datos, 5).replace("null", ""));
        }
        // FIN DE LOS AF

        // US        
        if (chUs.isSelected()) {
            sql = condicionOrden();
            if (!sql.equals("")) {
                Object[][] datos = instancias.getSql().getUs(sql, base2);
                Object[][] datos2 = new Object[datos.length][datos[0].length];
                String esta[] = new String[datos.length];
                int cont = 0;
                boolean aux = false;
                for (int i = 0; i < datos.length; i++) {
                    for (int j = 0; j < cont; j++) {
                        if (esta[j].equals(datos[i][1])) {
                            aux = true;
                            break;
                        }
                    }
                    if (!aux) {
                        esta[cont] = datos[i][1].toString();
                        for (int j = 0; j < datos[i].length; j++) {
                            datos2[cont][j] = datos[i][j];
                        }
                        cont++;
                    }
                    aux = false;
                }
                Object[][] datos3 = new Object[cont][datos[0].length];
                for (int i = 0; i < cont; i++) {
                    for (int j = 0; j < datos2[i].length; j++) {
                        datos3[i][j] = datos2[i][j];
                    }
                }
                cantUs = datos3.length;
                int mensajeId = 0;
                int mensajeIdEntidad = 0;
                for (int i = 0; i < cantUs; i++) {
                    if (datos3[i][0].toString().length() != 2 && mensajeId == 0) {
                        metodos.msgError(this, "Tiene un ERROR en el TIPO ID, del id: " + datos[i][1] + ", debe tener 2 caracteres, ARCHIVO US");
                        mensajeId++;
                    }

                    int idEntidadAdministradora = datos3[i][2].toString().length();
                    if (idEntidadAdministradora > 6 && mensajeIdEntidad == 0) {
                        metodos.msgError(this, "Tiene un ERROR en el codigo entidad " + datos3[i][2] + ", debe tener 6 caracteres, ARCHIVO US");
                        mensajeIdEntidad++;
                    }

                    if (datos3[i][4].toString().length() > 30) {
                        datos3[i][4] = datos3[i][4].toString().substring(0, 30);
                    }
                    if (datos3[i][5].toString().length() > 30) {
                        datos3[i][5] = datos3[i][5].toString().substring(0, 30);
                    }
                    if (datos3[i][6].toString().length() > 20) {
                        datos3[i][6] = datos3[i][6].toString().substring(0, 20);
                    }
                    if (datos3[i][7].toString().length() > 20) {
                        datos3[i][7] = datos3[i][7].toString().substring(0, 20);
                    }
                    if (datos3[i][10].toString().length() > 1) {
                        datos3[i][10] = datos3[i][10].toString().substring(0, 1);
                    }
                }
                metodosGenerales.generarArchivoPlano("rips/" + us + ".txt", formatoRips(datos3, null).replace("null", ""));
            }
        }
        // FIN US

        // INICIO AD
        if (chAd.isSelected()) {
            if (sql.equals("")) {
                sql = condicionOrden();
            }
            Object[][] datos;

            if (!txtNumFacturas.getText().equals("")) {
                datos = instancias.getSql().getAd(sql, new String[]{txtNumFacturas.getText()}, base3);
            } else {
                datos = instancias.getSql().getAdGenerales(sql, new String[]{""}, base3);
            }

            cantAd = datos.length;
            int mensajeId = 0;
            for (int i = 0; i < cantAd; i++) {
                if ((datos[i][2].toString().length() != 2 || datos[i][2].toString().equals("")) && mensajeId == 0) {
                    metodos.msgError(this, "Tiene un ERROR en el concepto " + datos[i][2] + ", ES DE OBLIGACIÓN REVISARLO, ARCHIVO AD");
                    mensajeId++;

                }
            }
            metodosGenerales.generarArchivoPlano("rips/" + ad + ".txt", formatoRips(datos, null).replace("null", ""));
        }
        // FIN AD

        // INICIO AP
        if (chAp.isSelected()) {
            if (sql.equals("")) {
                sql = condicionOrden();
            }
            Object[][] datos;
            if (!txtNumFacturas.getText().equals("")) {
                datos = instancias.getSql().getAp(sql, new String[]{txtNumFacturas.getText()}, base4);
            } else {
                datos = instancias.getSql().getApGenerales(sql, new String[]{""}, base4);
            }

            cantAp = datos.length;
            metodosGenerales.generarArchivoPlano("rips/" + ap + ".txt", formatoRips(datos, 4).replace("null", ""));
        }
        // FIN AP

        // INICIO AC
        if (chAc.isSelected()) {
            if (sql.equals("")) {
                sql = condicionOrden();
            }
            Object[][] datos;
            if (!txtNumFacturas.getText().equals("")) {
                datos = instancias.getSql().getAc(sql, new String[]{txtNumFacturas.getText()}, base5);
            } else {
                datos = instancias.getSql().getAcGenerales(sql, new String[]{""}, base5);
            }
            int mensajeId = 0;
            for (int i = 0; i < datos.length; i++) {
                datos[i][4] = metodos.fecha(datos[i][4].toString());
                if (datos[i][2].toString().length() != 2 && mensajeId == 0) {
                    metodos.msgError(this, "Tiene un ERROR en el tipo id, del id: " + datos[i][3] + ", debe tener 2 caracteres, ARCHIVO AC");
                    mensajeId++;
                }
            }
            cantAc = datos.length;
            metodosGenerales.generarArchivoPlano("rips/" + ac + ".txt", formatoRips(datos, 4).replace("null", ""));
        }
        // FIN AC

        if (chCt.isSelected()) {
            String datos = "";
//            String codPrestadoraServicios = instancias.getSql().getDatosMaestra()[41].toString();
//            System.out.println("llenar datos maestra desde INF RIPS");

            if (cantUs > 0) {
                datos = datos + codPrestadorServicios + "," + metodosGenerales.fecha() + "," + us + "," + cantUs + "\n";
            }
            if (cantAf > 0) {
                datos = datos + codPrestadorServicios + "," + metodosGenerales.fecha() + "," + af + "," + cantAf + "\n";
            }
            if (cantAd > 0) {
                datos = datos + codPrestadorServicios + "," + metodosGenerales.fecha() + "," + ad + "," + cantAd + "\n";
            }
            if (cantAc > 0) {
                datos = datos + codPrestadorServicios + "," + metodosGenerales.fecha() + "," + ac + "," + cantAc + "\n";
            }
            if (cantAp > 0) {
                datos = datos + codPrestadorServicios + "," + metodosGenerales.fecha() + "," + ap + "," + cantAp + "\n";
            }

            metodosGenerales.generarArchivoPlano("rips/CT" + mes + "" + anho + ".txt", datos.replace("null", ""));
        }

        metodos.msgExito(this,
                "Rips Generados con exito");

    }//GEN-LAST:event_jButton1ActionPerformed
    private String condicion() {
        String ini = metodos.desdeDate(dtInicio.getCurrent()), fin = metodos.desdeDate(dtFinal.getCurrent());

        String sql = " AND (((fecha) >= '" + ini + "') And ((fecha)<='" + fin + "')) ";
        if (!txtRazon.getText().equals("")) {
            sql = sql + " AND eps='" + txtId.getText() + "'";
            if (cmbRegimen.getSelectedIndex() != 0) {
                sql = sql + " AND tipoUsuarioDesc ='" + cmbRegimen.getSelectedItem() + "'";
            }
        } else {
            if (cmbRegimen.getSelectedIndex() != 0) {
                sql = sql + " AND tipoUsuarioDesc ='" + cmbRegimen.getSelectedItem() + "'";
            }
        }

        return sql;
    }
    private void jRadioButton1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButton1ItemStateChanged
        if (jRadioButton1.isSelected()) {
            txtId.setEditable(false);
            txtRazon.setEditable(false);
            cmbRegimen.setEditable(false);
            txtNumFacturas.setEditable(false);
            txtId1.setEditable(false);
            chUs.setEnabled(false);
            chUs.setSelected(true);
            chAf.setEnabled(false);
            chAf.setSelected(true);
            chAd.setEnabled(false);
            chAd.setSelected(true);
            chAc.setEnabled(false);
            chAc.setSelected(true);
            chAp.setEnabled(false);
            chAp.setSelected(true);
            chCt.setEnabled(false);
            chCt.setSelected(true);
        } else {
            txtId.setEditable(true);
            txtRazon.setEditable(true);
            cmbRegimen.setEditable(true);
            txtNumFacturas.setEditable(true);
            txtId1.setEditable(true);
            chUs.setEnabled(true);
            chUs.setSelected(true);
            chAf.setEnabled(true);
            chAf.setSelected(true);
            chAd.setEnabled(true);
            chAd.setSelected(true);
            chAc.setEnabled(true);
            chAc.setSelected(true);
            chAp.setEnabled(true);
            chAp.setSelected(true);
            chCt.setEnabled(true);
            chCt.setSelected(true);
        }
        this.limpiar();
    }//GEN-LAST:event_jRadioButton1ItemStateChanged

    private void jRadioButton2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButton2ItemStateChanged
        if (jRadioButton1.isSelected()) {
            txtId.setEditable(false);
            txtRazon.setEditable(false);
            cmbRegimen.setEditable(false);
            txtNumFacturas.setEditable(false);
            txtId1.setEditable(false);
            chUs.setEnabled(false);
            chUs.setSelected(true);
            chAf.setEnabled(false);
            chAf.setSelected(true);
            chAd.setEnabled(false);
            chAd.setSelected(true);
            chAc.setEnabled(false);
            chAc.setSelected(true);
            chAp.setEnabled(false);
            chAp.setSelected(true);
            chCt.setEnabled(false);
            chCt.setSelected(true);
        } else {
            txtId.setEditable(true);
            txtRazon.setEditable(true);
            cmbRegimen.setEditable(true);
            txtNumFacturas.setEditable(true);
            txtId1.setEditable(true);
            chUs.setEnabled(true);
            chUs.setSelected(true);
            chAf.setEnabled(true);
            chAf.setSelected(true);
            chAd.setEnabled(true);
            chAd.setSelected(true);
            chAc.setEnabled(true);
            chAc.setSelected(true);
            chAp.setEnabled(true);
            chAp.setSelected(true);
            chCt.setEnabled(true);
            chCt.setSelected(true);
        }
        this.limpiar();
    }//GEN-LAST:event_jRadioButton2ItemStateChanged

    private void txtId1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtId1KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            ndProducto nodo = instancias.getSql().getDatosProducto(txtId1.getText(), "bdProductos");
            if (nodo.getCodigo() != null) {
                for (int i = 0; i < jTable1.getRowCount(); i++) {
                    if (jTable1.getValueAt(i, 0).equals(nodo.getCodigo())) {
                        txtId1.setText("");
                        metodos.msgError(this, "Este procedimiento ya esta cargado");
                        return;
                    }
                }

                if (jTable2.getRowCount() > 0 && jTable1.getRowCount() == 0) {
                    if (instancias.getConfiguraciones().isMedico()) {
                        instancias.getSql().cambiarEstadoOrdenTotal("bdOrdenServicio");
                    } else if (instancias.getConfiguraciones().isOftalmologia()) {
                        instancias.getSql().cambiarEstadoOrdenTotal("bdOrdenServicioOftalmologia");
                    } else if (instancias.getConfiguraciones().isLaboratorio()) {
                        instancias.getSql().cambiarEstadoOrdenTotal("bdOrdenServicioLaboratorio");
                    }
                }

                String sql = condicion() + " AND (codigoTarifa='" + nodo.getCodigo() + "'";

                for (int i = 0; i < jTable1.getRowCount(); i++) {
                    sql = sql + " OR codigoTarifa='" + jTable1.getValueAt(i, 0) + "'";
                }
                sql = sql + ")";

                if (instancias.getConfiguraciones().isMedico()) {
                    jTable2.setModel(instancias.getSql().getRegistrosRips(sql, "bdClickPpal", "bdOrdenServicio"));
                } else if (instancias.getConfiguraciones().isOftalmologia()) {
                    jTable2.setModel(instancias.getSql().getRegistrosRips(sql, "ripsOftalmologia", "bdOrdenServicioOftalmologia"));
                } else if (instancias.getConfiguraciones().isLaboratorio()) {
                    jTable2.setModel(instancias.getSql().getRegistrosRips(sql, "ripsLaboratorio", "bdOrdenServicioLaboratorio"));
                }

                BigDecimal subtotal, copago = BigDecimal.ZERO, total = BigDecimal.ZERO;
                int cantidad = 0;
                for (int i = 0; i < jTable2.getRowCount(); i++) {
                    if (jTable2.getValueAt(i, 4).equals(nodo.getCodigo())) {
                        if (((Boolean) jTable2.getValueAt(i, 13))) {
                            cantidad = cantidad + Integer.parseInt(jTable2.getValueAt(i, 10).toString());
                            copago = copago.add(big.getBigDecimal(jTable2.getValueAt(i, 11).toString()));
                        }
                    }
                }
                if (cantidad > 0) {
                    subtotal = big.getBigDecimal(nodo.getL1()).multiply(big.getBigDecimal(cantidad));
                    ((DefaultTableModel) jTable1.getModel()).addRow(new Object[]{nodo.getCodigo(), nodo.getDescripcion(), cantidad, big.setMoneda(subtotal), big.setMoneda(copago), big.setMoneda(subtotal.subtract(copago))});
                } else {
                    metodos.msgError(this, "No se a encontrado ninguna orden en este rango de fecha asociado al procedimiento " + nodo.getCodigo());
                }
                txtId1.setText("");
            } else {
                ventanaProductos(txtId1.getText());
            }
        }
    }//GEN-LAST:event_txtId1KeyReleased
    public Integer codigoTabla(String codigo) {
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            if (jTable1.getValueAt(i, 0).equals(codigo)) {
                return i;
            }
        }
        return null;
    }
    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        try {
            if (!((Boolean) jTable2.getValueAt(jTable2.getSelectedRow(), 13))) {
                if (jTable2.getSelectedColumn() == 13) {
                    int f = jTable2.getSelectedRow();
                    String codigo = jTable2.getValueAt(f, 4).toString();
                    int fPro = codigoTabla(codigo);
                    ndProducto nodo = instancias.getSql().getDatosProducto(codigo, "bdProductos");
                    BigDecimal copago = big.getBigDecimal(jTable2.getValueAt(f, 11).toString());
                    BigDecimal valorUnidad = big.getBigDecimal(nodo.getL1());

                    jTable1.setValueAt((Integer.parseInt(jTable1.getValueAt(fPro, 2).toString()) - 1), fPro, 2);
                    jTable1.setValueAt(big.setMoneda(big.getBigDecimal(big.getMoneda(jTable1.getValueAt(fPro, 3).toString())).subtract(valorUnidad)), fPro, 3);
                    jTable1.setValueAt(big.setMoneda((big.getBigDecimal(big.getMoneda(jTable1.getValueAt(fPro, 4).toString())).subtract(copago))), fPro, 4);
                    jTable1.setValueAt(big.setMoneda((big.getBigDecimal(big.getMoneda(jTable1.getValueAt(fPro, 3).toString())).subtract(big.getBigDecimal(big.getMoneda(jTable1.getValueAt(fPro, 4).toString()))))), fPro, 5);

                    instancias.getSql().cambiarEstadoOrden(jTable2.getValueAt(f, 0).toString(), "OFF-" + metodosGenerales.fecha());
                }
            } else {
                if (jTable2.getSelectedColumn() == 13) {
                    int f = jTable2.getSelectedRow();
                    String codigo = jTable2.getValueAt(f, 4).toString();
                    int fPro = codigoTabla(codigo);
                    ndProducto nodo = instancias.getSql().getDatosProducto(codigo, "bdProductos");
                    BigDecimal copago = big.getBigDecimal(jTable2.getValueAt(f, 11).toString());
                    BigDecimal valorUnidad = big.getBigDecimal(nodo.getL1());

                    jTable1.setValueAt((Integer.parseInt(jTable1.getValueAt(fPro, 2).toString()) + 1), fPro, 2);
                    jTable1.setValueAt(big.setMoneda((big.getBigDecimal(big.getMoneda(jTable1.getValueAt(fPro, 3).toString())).add(valorUnidad))), fPro, 3);
                    jTable1.setValueAt(big.setMoneda((big.getBigDecimal(big.getMoneda(jTable1.getValueAt(fPro, 4).toString())).add(copago))), fPro, 4);
                    jTable1.setValueAt(big.setMoneda((big.getBigDecimal(big.getMoneda(jTable1.getValueAt(fPro, 3).toString())).subtract(big.getBigDecimal(big.getMoneda(jTable1.getValueAt(fPro, 4).toString()))))), fPro, 5);

                    instancias.getSql().cambiarEstadoOrden(jTable2.getValueAt(f, 0).toString(), "ON-" + metodosGenerales.fecha());
                }
            }
        } catch (Exception e) {
            if (jTable2.getSelectedColumn() == 13) {
                if (!((Boolean) jTable2.getValueAt(jTable2.getSelectedRow(), 13))) {
                    instancias.getSql().cambiarEstadoOrden(jTable2.getValueAt(jTable2.getSelectedRow(), 0).toString(), "OFF-" + metodosGenerales.fecha());
                } else {
                    instancias.getSql().cambiarEstadoOrden(jTable2.getValueAt(jTable2.getSelectedRow(), 0).toString(), "ON-" + metodosGenerales.fecha());
                }
            }
            System.out.println(e);
        }
    }//GEN-LAST:event_jTable2MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        DefaultTableModel c = (DefaultTableModel) jTable1.getModel();

        int h = jTable1.getRowCount();
        for (int m = 0; m < h; m++) {
            c.removeRow(0);
        }

        if (instancias.getConfiguraciones().isMedico()) {
            jTable2.setModel(instancias.getSql().getRegistrosRips(condicion(), "bdClickPpal", "bdOrdenServicio"));
        } else if (instancias.getConfiguraciones().isOftalmologia()) {
            jTable2.setModel(instancias.getSql().getRegistrosRips(condicion(), "ripsOftalmologia", "bdOrdenServicioOftalmologia"));
        } else if (instancias.getConfiguraciones().isLaboratorio()) {
            jTable2.setModel(instancias.getSql().getRegistrosRips(condicion(), "ripsLaboratorio", "bdOrdenServicioLaboratorio"));
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private String formatoInfo(String dato, int numero) {
        if (dato.length() > numero) {
            return "Este dato tiene muchos digitos " + dato;
        }
        int cantRestante = numero - dato.length();
        String aux = "";
        for (int i = 0; i < cantRestante; i++) {
            aux = aux + "0";
        }
        return aux + dato;
    }

    private String valorTotal(int c) {
        BigDecimal total = BigDecimal.ZERO;
        for (int i = 0; i < jTable2.getRowCount(); i++) {
            if ((Boolean) jTable2.getValueAt(i, 13)) {
                total = total.add(big.getBigDecimal(jTable2.getValueAt(i, c)));
            }
        }
        return total.toString();
    }

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        instancias.setRutaAguardar(metodos.obtenerRuta(this, "Detalle de cargo FACT-" + txtNumFacturas.getText()));
        Object[] datosEmpresa = instancias.getSql().getDatosMaestra();
        System.out.println("llenar datos maestra desde INF RIPS");
        instancias.getReporte().detalleCargo(condicionOrden(), datosEmpresa[8].toString(), datosEmpresa[9].toString(), datosEmpresa[13].toString(), txtNumFacturas.getText());
        System.out.println("select * from detalleCargo " + condicionOrden());

        instancias.setRutaAguardar(metodos.obtenerRuta(this, "Plano FACT-" + txtNumFacturas.getText()));
        String uno = formatoInfo("0", 1) + formatoInfo("1", 6) + formatoInfo("1", 6) + metodosGenerales.fecha() + formatoInfo("1", 3) + formatoInfo("NI", 2) + formatoInfo("900713295", 15) + formatoInfo("0", 1);
        String dos = formatoInfo("1", 1) + formatoInfo("0", 1) + metodos.fecha2(dtFechaFactura.getText()) + formatoInfo("FACT- ", 6) + formatoInfo(txtNumFacturas.getText(), 10) + formatoInfo(valorTotal(12), 12) + formatoInfo("0", 10) + formatoInfo("0", 10) + formatoInfo(valorTotal(11), 10) + formatoInfo("0", 10) + formatoInfo("0", 10) + formatoInfo("1", 1);
        instancias.getReporte().planoFactura(condicionOrden(), txtNumFacturas.getText(), uno, dos);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void popBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popBorrarActionPerformed
        if (jTable1.getSelectedRow() > -1) {
            int fila = jTable1.getSelectedRow();
            DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
            modelo.removeRow(fila);

            String sql = condicion();
            for (int i = 0; i < jTable1.getRowCount(); i++) {
                if (i == 0) {
                    sql = sql + " AND codigoTarifa='" + jTable1.getValueAt(i, 0) + "'";
                } else {
                    sql = sql + " OR codigoTarifa='" + jTable1.getValueAt(i, 0) + "'";
                }
            }

            if (instancias.getConfiguraciones().isMedico()) {
                jTable2.setModel(instancias.getSql().getRegistrosRips(sql, "bdClickPpal", "bdOrdenServicio"));
            } else if (instancias.getConfiguraciones().isOftalmologia()) {
                jTable2.setModel(instancias.getSql().getRegistrosRips(sql, "ripsOftalmologia", "bdOrdenServicioOftalmologia"));
            } else if (instancias.getConfiguraciones().isLaboratorio()) {
                jTable2.setModel(instancias.getSql().getRegistrosRips(sql, "ripsLaboratorio", "bdOrdenServicioLaboratorio"));
            }

        } else {
            metodos.msgAdvertencia(this, "Seleccione un producto");
        }
    }//GEN-LAST:event_popBorrarActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        limpiar();
        limpiarTabla();
        limpiarTabla2();

        if (instancias.getConfiguraciones().isMedico()) {
            instancias.getSql().cambiarEstadoOrdenTotal("bdOrdenServicio");
        } else if (instancias.getConfiguraciones().isOftalmologia()) {
            instancias.getSql().cambiarEstadoOrdenTotal("bdOrdenServicioOftalmologia");
        } else if (instancias.getConfiguraciones().isLaboratorio()) {
            instancias.getSql().cambiarEstadoOrdenTotal("bdOrdenServicioLaboratorio");
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    public void ventanaProductos(String codigo) {
        VistaBuscadorProductos buscar = new VistaBuscadorProductos(null, rootPaneCheckingEnabled, false, "");
        buscar.setLocationRelativeTo(null);
        instancias.setBusProductos(buscar);
        instancias.setCampoActual(txtId1);
        txtId1.requestFocus();
        buscar.noEncontrado(codigo.replace("'", "//"));
        buscar.show();
    }

    private void limpiar() {
        txtId.setText("");
        txtRazon.setText("");
        cmbRegimen.setSelectedIndex(0);
        txtNumFacturas.setText("");
        txtId1.setText("");
        chUs.setSelected(true);
        chAf.setSelected(true);
        chAd.setSelected(true);
        chAc.setSelected(true);
        chAp.setSelected(true);
        chCt.setSelected(true);
    }

    public void limpiarTabla() {
        int r = jTable2.getRowCount();
        modelo = (DefaultTableModel) jTable2.getModel();
        for (int i = 0; i < r; i++) {
            modelo.removeRow(0);
        }
    }

    public void limpiarTabla2() {
        int r = jTable1.getRowCount();
        modelo = (DefaultTableModel) jTable1.getModel();
        for (int i = 0; i < r; i++) {
            modelo.removeRow(0);
        }
    }

//    public void setInstancias(Instancias instancias) {
//        this.instancias = instancias;
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox chAc;
    private javax.swing.JCheckBox chAd;
    private javax.swing.JCheckBox chAf;
    private javax.swing.JCheckBox chAp;
    private javax.swing.JCheckBox chCt;
    private javax.swing.JCheckBox chUs;
    private javax.swing.JComboBox cmbRegimen;
    private datechooser.beans.DateChooserCombo dtFechaFactura;
    private datechooser.beans.DateChooserCombo dtFinal;
    private datechooser.beans.DateChooserCombo dtInicio;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel lbCiudad6;
    private javax.swing.JLabel lbNit11;
    private javax.swing.JLabel lbNit14;
    private javax.swing.JLabel lbNit8;
    private javax.swing.JLabel lbNit9;
    private javax.swing.JLabel lbRazon1;
    private javax.swing.JLabel lbRazon2;
    private javax.swing.JLabel lbRazon3;
    private javax.swing.JPanel pnlFormulario;
    private javax.swing.JMenuItem popBorrar;
    private javax.swing.JScrollPane scrFormulario;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtId1;
    private javax.swing.JTextField txtNumFacturas;
    private javax.swing.JTextField txtRazon;
    // End of variables declaration//GEN-END:variables
}
