package formularios.productos;

import clases.Instancias;
import clases.big;
import clases.metodosGenerales;
import java.awt.Dimension;
import java.math.BigDecimal;
import javax.swing.table.DefaultTableModel;

public class infRepIvasCompras extends javax.swing.JPanel {

    String simbolo = "";
    metodosGenerales metodos = new metodosGenerales();
    private Instancias instancias;
    DefaultTableModel modelo;

    private Dimension dimBarra = null;

    public infRepIvasCompras() {
        initComponents();

        dtInicio.setFormat(2);
        dtFinal.setFormat(2);
        instancias = Instancias.getInstancias();
        modelo = (DefaultTableModel) tblResultados.getModel();
        simbolo = instancias.getSimbolo();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoTipo = new javax.swing.ButtonGroup();
        jRadioButton1 = new javax.swing.JRadioButton();
        buttonGroup1 = new javax.swing.ButtonGroup();
        scrFormulario = new javax.swing.JScrollPane();
        pnlFormulario = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblResultados = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        lbNit11 = new javax.swing.JLabel();
        btnImpresa = new javax.swing.JRadioButton();
        btnExcel = new javax.swing.JRadioButton();
        jPanel8 = new javax.swing.JPanel();
        lbNit10 = new javax.swing.JLabel();
        lbNit12 = new javax.swing.JLabel();
        dtInicio = new datechooser.beans.DateChooserCombo();
        dtFinal = new datechooser.beans.DateChooserCombo();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        rbIva = new javax.swing.JRadioButton();
        rbImpo = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        lbNit13 = new javax.swing.JLabel();
        txtBase = new javax.swing.JLabel();
        lbNit15 = new javax.swing.JLabel();
        txtSubtotal = new javax.swing.JLabel();
        txtTotal = new javax.swing.JLabel();
        lbNit18 = new javax.swing.JLabel();
        lbNit19 = new javax.swing.JLabel();
        txtDescuentos = new javax.swing.JLabel();
        lbNit21 = new javax.swing.JLabel();
        txtTotalImpuesto = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

        jRadioButton1.setText("jRadioButton1");

        pnlFormulario.setBackground(new java.awt.Color(255, 255, 255));

        tblResultados.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        tblResultados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Prod", "Cliente", "Vendedor", "%", "Base", "Subtotal", "Descuentos", "Total", "Total Impuesto"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, false, true, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblResultados.setRowHeight(24);
        tblResultados.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblResultados);
        if (tblResultados.getColumnModel().getColumnCount() > 0) {
            tblResultados.getColumnModel().getColumn(0).setMinWidth(0);
            tblResultados.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblResultados.getColumnModel().getColumn(0).setMaxWidth(0);
            tblResultados.getColumnModel().getColumn(1).setMinWidth(0);
            tblResultados.getColumnModel().getColumn(1).setPreferredWidth(0);
            tblResultados.getColumnModel().getColumn(1).setMaxWidth(0);
            tblResultados.getColumnModel().getColumn(2).setMinWidth(0);
            tblResultados.getColumnModel().getColumn(2).setPreferredWidth(0);
            tblResultados.getColumnModel().getColumn(2).setMaxWidth(0);
            tblResultados.getColumnModel().getColumn(3).setMinWidth(20);
            tblResultados.getColumnModel().getColumn(3).setPreferredWidth(40);
            tblResultados.getColumnModel().getColumn(3).setMaxWidth(70);
        }

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 14))); // NOI18N

        jButton4.setBackground(new java.awt.Color(46, 204, 113));
        jButton4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton4.setText("GENERAR");
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        lbNit11.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit11.setText("Versión:");

        btnImpresa.setBackground(new java.awt.Color(255, 255, 255));
        grupoTipo.add(btnImpresa);
        btnImpresa.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        btnImpresa.setSelected(true);
        btnImpresa.setText("En Pantalla");
        btnImpresa.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnImpresa.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnImpresa.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                btnImpresaItemStateChanged(evt);
            }
        });

        btnExcel.setBackground(new java.awt.Color(255, 255, 255));
        grupoTipo.add(btnExcel);
        btnExcel.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        btnExcel.setText("Hoja de calculo");
        btnExcel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnExcel.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbNit11)
                .addGap(18, 18, 18)
                .addComponent(btnImpresa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnExcel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton4)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbNit11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnImpresa)
                        .addComponent(btnExcel)))
                .addGap(5, 5, 5))
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        lbNit10.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        lbNit10.setText("Fecha inicial:");

        lbNit12.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        lbNit12.setText("Fecha final:");

        dtInicio.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 12));

        dtFinal.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 12));

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbNit10)
                .addGap(5, 5, 5)
                .addComponent(dtInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(123, 123, 123)
                .addComponent(lbNit12)
                .addGap(5, 5, 5)
                .addComponent(dtFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbNit12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbNit10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dtFinal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dtInicio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1))
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jButton6.setBackground(new java.awt.Color(46, 204, 113));
        jButton6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton6.setText("VER");
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setBackground(new java.awt.Color(204, 204, 204));
        jButton7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton7.setText("LIMPIAR");
        jButton7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbIva);
        rbIva.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        rbIva.setSelected(true);
        rbIva.setText("IVA");
        rbIva.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        buttonGroup1.add(rbImpo);
        rbImpo.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        rbImpo.setText("IMPOCONSUMO");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rbIva, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(rbImpo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rbIva, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                    .addComponent(rbImpo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        lbNit13.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbNit13.setText("Base:");

        txtBase.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        txtBase.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtBase.setText("0");

        lbNit15.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbNit15.setText("Subtotal:");

        txtSubtotal.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        txtSubtotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtSubtotal.setText("0");

        txtTotal.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        txtTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtTotal.setText("0");

        lbNit18.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbNit18.setText("Total:");

        lbNit19.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbNit19.setText("Descuentos:");

        txtDescuentos.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        txtDescuentos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtDescuentos.setText("0");

        lbNit21.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbNit21.setText("Total impuesto:");

        txtTotalImpuesto.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        txtTotalImpuesto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtTotalImpuesto.setText("0");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lbNit15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbNit13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtBase, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                                    .addComponent(txtSubtotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(75, 75, 75)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lbNit19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbNit18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtDescuentos, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                                    .addComponent(txtTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lbNit21)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTotalImpuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtBase, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbNit19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtDescuentos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(lbNit13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbNit15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtSubtotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbNit18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTotalImpuesto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbNit21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlFormularioLayout = new javax.swing.GroupLayout(pnlFormulario);
        pnlFormulario.setLayout(pnlFormularioLayout);
        pnlFormularioLayout.setHorizontalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 756, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlFormularioLayout.setVerticalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        scrFormulario.setViewportView(pnlFormulario);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrFormulario)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrFormulario, javax.swing.GroupLayout.DEFAULT_SIZE, 673, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnImpresaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_btnImpresaItemStateChanged

    }//GEN-LAST:event_btnImpresaItemStateChanged

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if (metodos.msgPregunta(null, "¿Esta seguro?") == 0) {
            instancias.getSql().eliminarTodo("bdGenerarIvas");

            String ini = metodos.desdeDate2(dtInicio.getCurrent()), fin = metodos.desdeDate(dtFinal.getCurrent());
            fin = metodos.sumarFecha(metodos.fecha(fin), 0);
            fin = metodos.fechaConsulta(fin);
            ini = metodos.fechaConsulta(ini);

            String enca = "";
            if (dtInicio.getText().equals(dtFinal.getText())) {
                enca = "Ivas de compra del " + ini;
            } else {
                enca = "Ivas de compra entre el " + ini + " y el " + fin;
            }

            String tipo = "";
            if (btnExcel.isSelected()) {
                if (rbIva.isSelected()) {
                    instancias.setRutaAguardar(metodos.obtenerRuta(null, "Reporte de Ivas de compra" + metodosGenerales.dia() + " de " + metodosGenerales.mesEnPalabra() + " del " + metodosGenerales.anho()));
                } else {
                    instancias.setRutaAguardar(metodos.obtenerRuta(null, "Reporte de Impoconsumo de compra" + metodosGenerales.dia() + " de " + metodosGenerales.mesEnPalabra() + " del " + metodosGenerales.anho()));
                }

                tipo = "xls";
            }

            for (int i = 0; i < tblResultados.getRowCount(); i++) {
                instancias.getSql().agregarRegistrosIvas(tblResultados.getValueAt(i, 3).toString(),
                        big.getMoneda(tblResultados.getValueAt(i, 4).toString()),
                        big.getMoneda(tblResultados.getValueAt(i, 5).toString()),
                        big.getMoneda(tblResultados.getValueAt(i, 7).toString()),
                        big.getMoneda(tblResultados.getValueAt(i, 8).toString()),
                        big.getMoneda(tblResultados.getValueAt(i, 6).toString()));
            }
            instancias.getReporte().ver_RepIvaVentas(tipo, enca);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        while (tblResultados.getRowCount() > 0) {
            modelo.removeRow(0);
        }

        String sql = " where ";
        String ini = metodos.desdeDate2(dtInicio.getCurrent()), fin = metodos.desdeDate(dtFinal.getCurrent());

        fin = metodos.sumarFecha(metodos.fecha(fin), 0);
        fin = metodos.fechaConsulta(fin);
        ini = metodos.fechaConsulta(ini);
        sql = sql + " (((fechaFactura) >= '" + ini + "') And ((fechaFactura)<= '" + fin + "'))";

        System.out.println("Consulta Rep Ventas: " + sql);

        String tipoPorc, BD;
        if (rbImpo.isSelected()) {
            tipoPorc = "porcImpo";
            BD = "repImpoCompras";
        } else {
            tipoPorc = "porcIva";
            BD = "repIvasCompras";
        }

        Object[][] ivas = instancias.getSql().getReporteDeImpuestos(sql, BD, tipoPorc);

        for (int i = 0; i < ivas.length; i++) {
            String cliente = "", producto = "", vendedor = "";

            try {
                cliente = ivas[i][6].toString();
            } catch (Exception e) {
            }

            try {
                producto = ivas[i][7].toString();
            } catch (Exception e) {
            }

            try {
                vendedor = ivas[i][8].toString();
            } catch (Exception e) {
            }

            BigDecimal total = big.getBigDecimal(ivas[i][3].toString()).subtract(big.getBigDecimal(ivas[i][4].toString()));

            modelo.addRow(new Object[]{cliente, producto, vendedor, ivas[i][1].toString(), big.setMonedaExacta(total), big.setMoneda(big.getBigDecimal(ivas[i][3].toString())),
                big.setMoneda(big.getBigDecimal(ivas[i][4].toString())), big.setMoneda(big.getBigDecimal(ivas[i][5].toString())),
                big.setMoneda(big.getBigDecimal(ivas[i][2].toString()))});
        }

        Object[][] ivasAcomulados = new Object[tblResultados.getRowCount()][6];

        int ser = 0;
        for (int i = 0; i < tblResultados.getRowCount(); i++) {
            Boolean existe = false;

            for (int j = 0; j < ivasAcomulados.length; j++) {

                String porc = "";
                try {
                    porc = ivasAcomulados[j][0].toString();
                } catch (Exception e) {
                }

                if (porc.equals(tblResultados.getValueAt(i, 3))) {
                    ivasAcomulados[j][1] = big.getBigDecimal(ivasAcomulados[j][1].toString()).add(big.getMoneda(tblResultados.getValueAt(i, 4).toString()));
                    ivasAcomulados[j][2] = big.getBigDecimal(ivasAcomulados[j][2].toString()).add(big.getMoneda(tblResultados.getValueAt(i, 5).toString()));
                    ivasAcomulados[j][3] = big.getBigDecimal(ivasAcomulados[j][3].toString()).add(big.getMoneda(tblResultados.getValueAt(i, 6).toString()));
                    ivasAcomulados[j][4] = big.getBigDecimal(ivasAcomulados[j][4].toString()).add(big.getMoneda(tblResultados.getValueAt(i, 7).toString()));
                    ivasAcomulados[j][5] = big.getBigDecimal(ivasAcomulados[j][5].toString()).add(big.getMoneda(tblResultados.getValueAt(i, 8).toString()));
                    existe = true;
                    break;
                }
            }

            if (!existe) {
                ivasAcomulados[ser][0] = big.getMoneda(tblResultados.getValueAt(i, 3).toString());
                ivasAcomulados[ser][1] = big.getMoneda(tblResultados.getValueAt(i, 4).toString());
                ivasAcomulados[ser][2] = big.getMoneda(tblResultados.getValueAt(i, 5).toString());
                ivasAcomulados[ser][3] = big.getMoneda(tblResultados.getValueAt(i, 6).toString());
                ivasAcomulados[ser][4] = big.getMoneda(tblResultados.getValueAt(i, 7).toString());
                ivasAcomulados[ser][5] = big.getMoneda(tblResultados.getValueAt(i, 8).toString());
                ser++;
            }
            existe = false;
        }

        tblResultados.getColumnModel().getColumn(0).setMinWidth(0);
        tblResultados.getColumnModel().getColumn(0).setPreferredWidth(0);
        tblResultados.getColumnModel().getColumn(0).setMaxWidth(0);
        tblResultados.getColumnModel().getColumn(1).setMinWidth(0);
        tblResultados.getColumnModel().getColumn(1).setPreferredWidth(0);
        tblResultados.getColumnModel().getColumn(1).setMaxWidth(0);
        tblResultados.getColumnModel().getColumn(2).setMinWidth(0);
        tblResultados.getColumnModel().getColumn(2).setPreferredWidth(0);
        tblResultados.getColumnModel().getColumn(2).setMaxWidth(0);

        String valores = "";
        try {
            valores = ivasAcomulados[0][0].toString();
        } catch (Exception e) {
        }

        if (valores.equals("")) {
            System.out.println("No tiene valores acomulados");
        } else {
            while (tblResultados.getRowCount() > 0) {
                modelo.removeRow(0);
            }

            for (int i = 0; i < ivasAcomulados.length; i++) {
                String porc = "";

                try {
                    porc = ivasAcomulados[i][0].toString();
                } catch (Exception e) {
                    break;
                }

                modelo.addRow(new Object[]{"", "", "", ivasAcomulados[i][0].toString(), big.setMoneda(big.getBigDecimal(ivasAcomulados[i][1].toString())),
                    big.setMoneda(big.getBigDecimal(ivasAcomulados[i][2].toString())), big.setMoneda(big.getBigDecimal(ivasAcomulados[i][3].toString())),
                    big.setMoneda(big.getBigDecimal(ivasAcomulados[i][4].toString())), big.setMoneda(big.getBigDecimal(ivasAcomulados[i][5].toString()))});
            }
        }

        BigDecimal totalBase = BigDecimal.ZERO, totalSubtotal = BigDecimal.ZERO, descuentos = BigDecimal.ZERO, total = BigDecimal.ZERO, impuesto = BigDecimal.ZERO;
        for (int i = 0; i < tblResultados.getRowCount(); i++) {
            totalBase = totalBase.add(big.getMoneda(tblResultados.getValueAt(i, 4).toString()));
            totalSubtotal = totalSubtotal.add(big.getMoneda(tblResultados.getValueAt(i, 5).toString()));
            descuentos = descuentos.add(big.getMoneda(tblResultados.getValueAt(i, 6).toString()));
            total = total.add(big.getMoneda(tblResultados.getValueAt(i, 7).toString()));
            impuesto = impuesto.add(big.getMoneda(tblResultados.getValueAt(i, 8).toString()));
        }

        txtBase.setText(big.setMoneda(totalBase));
        txtSubtotal.setText(big.setMoneda(totalSubtotal));
        txtDescuentos.setText(big.setMoneda(descuentos));
        txtTotal.setText(big.setMoneda(total));
        txtTotalImpuesto.setText(big.setMoneda(impuesto));

    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        while (tblResultados.getRowCount() > 0) {
            modelo.removeRow(0);
        }

        txtBase.setText(this.simbolo + " 0");
        txtSubtotal.setText(this.simbolo + " 0");
        txtDescuentos.setText(this.simbolo + " 0");
        txtTotal.setText(this.simbolo + " 0");
        txtTotalImpuesto.setText(this.simbolo + " 0");
    }//GEN-LAST:event_jButton7ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton btnExcel;
    private javax.swing.JRadioButton btnImpresa;
    private javax.swing.ButtonGroup buttonGroup1;
    private datechooser.beans.DateChooserCombo dtFinal;
    private datechooser.beans.DateChooserCombo dtInicio;
    private javax.swing.ButtonGroup grupoTipo;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lbNit10;
    private javax.swing.JLabel lbNit11;
    private javax.swing.JLabel lbNit12;
    private javax.swing.JLabel lbNit13;
    private javax.swing.JLabel lbNit15;
    private javax.swing.JLabel lbNit18;
    private javax.swing.JLabel lbNit19;
    private javax.swing.JLabel lbNit21;
    private javax.swing.JPanel pnlFormulario;
    private javax.swing.JRadioButton rbImpo;
    private javax.swing.JRadioButton rbIva;
    private javax.swing.JScrollPane scrFormulario;
    private javax.swing.JTable tblResultados;
    private javax.swing.JLabel txtBase;
    private javax.swing.JLabel txtDescuentos;
    private javax.swing.JLabel txtSubtotal;
    private javax.swing.JLabel txtTotal;
    private javax.swing.JLabel txtTotalImpuesto;
    // End of variables declaration//GEN-END:variables
}
