package formularios.Tesoreria;

import clases.Instancias;
import Utilidades.BaseDatos.SQL;
import clases.big;
import clases.metodosGenerales;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import javax.swing.JDialog;
import javax.swing.table.DefaultTableModel;

public class dlgMovimientosPendientes extends javax.swing.JDialog {

    private Instancias instancias;
    DefaultTableModel modelo;
    String factura;
    metodosGenerales metodos = new metodosGenerales();

    public dlgMovimientosPendientes(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        instancias = Instancias.getInstancias();
        jLabel8.setVisible(false);
        cmbBancos.removeAllItems();
        cmbBancos.addItem("Escoja un banco...");
        Object[][] bancos = instancias.getSql().getBancos();
        for (int i = 0; i < bancos.length; i++) {
            String nombre = bancos[i][0] + " - " + bancos[i][1];
            cmbBancos.addItem(nombre);
        }

        modelo = (DefaultTableModel) tblRegistros.getModel();

        while (tblRegistros.getRowCount() > 0) {
            modelo.removeRow(0);
        }

        Object[][] registros = instancias.getSql().getBancosPendientes();

        for (int i = 0; i < registros.length; i++) {
            modelo.addRow(new Object[]{registros[i][0].toString(), registros[i][4].toString(), registros[i][2].toString(), registros[i][3].toString(), registros[i][1].toString(), false});
        }

        for (int i = 0; i < registros.length; i++) {
            tblRegistros.setValueAt(metodos.fecha(tblRegistros.getValueAt(i, 1).toString()), i, 1);
            tblRegistros.setValueAt(big.setMoneda(big.getMoneda(tblRegistros.getValueAt(i, 4).toString())), i, 4);
        }

        this.setLocationRelativeTo(null);
        this.setTitle("PENDIENTES");
        metodosGenerales metodos = new metodosGenerales();

//        this.getRootPane().registerKeyboardAction(accion("cerrar", this), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
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

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblRegistros = new javax.swing.JTable();
        btnGuardar = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        txtSaldoActual = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        lbNit3 = new javax.swing.JLabel();
        cmbBancos = new javax.swing.JComboBox();
        jSeparator1 = new javax.swing.JSeparator();
        txtValorTotal = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        tblRegistros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Fecha", "Nota", "Usuario", "Valor", "Sel"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblRegistros.setRowHeight(20);
        tblRegistros.getTableHeader().setReorderingAllowed(false);
        tblRegistros.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblRegistrosMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tblRegistrosMouseExited(evt);
            }
        });
        jScrollPane2.setViewportView(tblRegistros);
        if (tblRegistros.getColumnModel().getColumnCount() > 0) {
            tblRegistros.getColumnModel().getColumn(0).setMinWidth(0);
            tblRegistros.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblRegistros.getColumnModel().getColumn(0).setMaxWidth(0);
            tblRegistros.getColumnModel().getColumn(1).setMinWidth(95);
            tblRegistros.getColumnModel().getColumn(1).setPreferredWidth(95);
            tblRegistros.getColumnModel().getColumn(1).setMaxWidth(95);
            tblRegistros.getColumnModel().getColumn(3).setMinWidth(110);
            tblRegistros.getColumnModel().getColumn(3).setPreferredWidth(110);
            tblRegistros.getColumnModel().getColumn(3).setMaxWidth(110);
            tblRegistros.getColumnModel().getColumn(4).setMinWidth(110);
            tblRegistros.getColumnModel().getColumn(4).setPreferredWidth(110);
            tblRegistros.getColumnModel().getColumn(4).setMaxWidth(110);
            tblRegistros.getColumnModel().getColumn(5).setMinWidth(50);
            tblRegistros.getColumnModel().getColumn(5).setPreferredWidth(50);
            tblRegistros.getColumnModel().getColumn(5).setMaxWidth(50);
        }

        btnGuardar.setBackground(new java.awt.Color(0, 153, 153));
        btnGuardar.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
        btnGuardar.setText("REALIZAR CONSIGNACIÓN");
        btnGuardar.setToolTipText("Ctrl+G");
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnGuardar.setMargin(new java.awt.Insets(2, 14, 2, 5));
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

        jCheckBox1.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jCheckBox1.setText("Seleccionar todo");
        jCheckBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox1ItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 814, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jCheckBox1)
                            .addComponent(btnGuardar))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jCheckBox1)
                .addGap(5, 5, 5)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel4.setFont(new java.awt.Font("Century Gothic", 1, 30)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("MOVIMIENTO");

        txtSaldoActual.setFont(new java.awt.Font("Century Gothic", 1, 30)); // NOI18N
        txtSaldoActual.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtSaldoActual.setText("0");

        jLabel7.setFont(new java.awt.Font("Century Gothic", 1, 30)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("SALDO FINAL");

        jLabel8.setFont(new java.awt.Font("Century Gothic", 1, 30)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("-");

        jLabel9.setFont(new java.awt.Font("Century Gothic", 1, 30)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("SALDO ACTUAL");

        txtTotal.setFont(new java.awt.Font("Century Gothic", 1, 30)); // NOI18N
        txtTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtTotal.setText("0");

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        lbNit3.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbNit3.setText("CUENTA:");
        lbNit3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lbNit3KeyReleased(evt);
            }
        });

        cmbBancos.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        cmbBancos.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbBancosItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbNit3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmbBancos, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(229, 229, 229))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmbBancos)
                    .addComponent(lbNit3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txtValorTotal.setFont(new java.awt.Font("Century Gothic", 1, 30)); // NOI18N
        txtValorTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtValorTotal.setText("0");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 568, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGap(61, 61, 61)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtSaldoActual, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
                                            .addComponent(txtValorTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSaldoActual, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        try {
            String cuenta = cmbBancos.getSelectedItem().toString();
            if (cuenta.equals("Escoja un banco...")) {
                metodos.msgError(null, "Seleccione un banco");
                return;
            }
        } catch (Exception e) {
        }

        Boolean entro = false;
        for (int i = 0; i < tblRegistros.getRowCount(); i++) {
            if ((Boolean) tblRegistros.getValueAt(i, 5)) {
                entro = true;
            }
        }

        if (!entro) {
            metodos.msgError(null, "Debe seleccionar un registro");
            txtValorTotal.requestFocus();
            return;
        }

        if (metodos.msgPregunta(null, "¿Desea continuar?") == 0) {

            String tipo = "Entrada";
            String numero = cmbBancos.getSelectedItem().toString().split(" - ")[1];
            String cuenta = cmbBancos.getSelectedItem().toString().split(" - ")[0];

            String nota = "";
            for (int i = 0; i < tblRegistros.getRowCount(); i++) {
                if ((Boolean) tblRegistros.getValueAt(i, 5)) {
                    nota = nota + tblRegistros.getValueAt(i, 2) + ", ";
                }
            }
            nota = nota.substring(0, nota.length() - 2);

            Object[] vector = {cuenta, numero, nota, instancias.getUsuario(), metodosGenerales.fechaHora(), tipo};

            Object[] vector1 = {big.getMoneda(txtSaldoActual.getText()), big.getMoneda(txtValorTotal.getText()), big.getMoneda(txtTotal.getText())};

            if (!instancias.getSql().agregarMovimientoBanco(vector, vector1)) {
                metodos.msgError(null, "Hubo un problema al guardar el movimiento");
                return;
            } else {

                instancias.getSql().modificarMontoBanco(big.getMoneda(txtTotal.getText()), numero);
                instancias.getBancos().refrescarDatos();

                for (int i = 0; i < tblRegistros.getRowCount(); i++) {
                    if ((Boolean) tblRegistros.getValueAt(i, 5)) {
                        instancias.getSql().eliminar_registro("bdMovimientosBancosPendientes", " Id = " + tblRegistros.getValueAt(i, 0));
                    }
                }

                instancias.getCaja().cargarTabla();
                metodos.msgExito(null, "Movimiento registrado con exito");

                this.dispose();
            }
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnGuardarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnGuardarKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnGuardarActionPerformed(null);
        }
    }//GEN-LAST:event_btnGuardarKeyReleased

    private void lbNit3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lbNit3KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_lbNit3KeyReleased

    private void cmbBancosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbBancosItemStateChanged
        String monto = "0";
        try {
            monto = instancias.getSql().montoBanco(cmbBancos.getSelectedItem().toString().split(" - ")[1]);
        } catch (Exception e) {
        }
        txtSaldoActual.setText(big.setMoneda(big.getMoneda(monto)));
        calcularTotal();
    }//GEN-LAST:event_cmbBancosItemStateChanged

    private void tblRegistrosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblRegistrosMouseClicked
        BigDecimal total = BigDecimal.ZERO;

        for (int i = 0; i < tblRegistros.getRowCount(); i++) {
            if ((Boolean) tblRegistros.getValueAt(i, 5)) {
                total = total.add(big.getMoneda(tblRegistros.getValueAt(i, 4).toString()));
            }
        }

        txtValorTotal.setText(big.setMoneda(total));
        calcularTotal();
    }//GEN-LAST:event_tblRegistrosMouseClicked

    private void tblRegistrosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblRegistrosMouseExited
        BigDecimal total = BigDecimal.ZERO;

        for (int i = 0; i < tblRegistros.getRowCount(); i++) {
            if ((Boolean) tblRegistros.getValueAt(i, 5)) {
                total = total.add(big.getMoneda(tblRegistros.getValueAt(i, 4).toString()));
            }
        }

        txtValorTotal.setText(big.setMoneda(total));
        calcularTotal();
    }//GEN-LAST:event_tblRegistrosMouseExited

    private void jCheckBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox1ItemStateChanged
        if (jCheckBox1.isSelected()) {
            for (int i = 0; i < tblRegistros.getRowCount(); i++) {
                tblRegistros.setValueAt(true, i, 5);
            }

            BigDecimal total = BigDecimal.ZERO;
            for (int i = 0; i < tblRegistros.getRowCount(); i++) {
                if ((Boolean) tblRegistros.getValueAt(i, 5)) {
                    total = total.add(big.getMoneda(tblRegistros.getValueAt(i, 4).toString()));
                }
            }
            txtValorTotal.setText(big.setMoneda(total));
            calcularTotal();
        } else {
            for (int i = 0; i < tblRegistros.getRowCount(); i++) {
                tblRegistros.setValueAt(false, i, 5);
            }
            BigDecimal total = BigDecimal.ZERO;
            for (int i = 0; i < tblRegistros.getRowCount(); i++) {
                if ((Boolean) tblRegistros.getValueAt(i, 5)) {
                    total = total.add(big.getMoneda(tblRegistros.getValueAt(i, 4).toString()));
                }
            }
            txtValorTotal.setText(big.setMoneda(total));
            calcularTotal();
        }
    }//GEN-LAST:event_jCheckBox1ItemStateChanged

    public void calcularTotal() {
        BigDecimal valor = big.getMoneda(txtValorTotal.getText());
        BigDecimal actual = big.getMoneda(txtSaldoActual.getText());
        BigDecimal total;
        total = actual.add(valor);
        txtTotal.setText(big.setMoneda(total));
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(dlgMovimientosPendientes.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(dlgMovimientosPendientes.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(dlgMovimientosPendientes.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dlgMovimientosPendientes.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                dlgMovimientosPendientes dialog = new dlgMovimientosPendientes(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox cmbBancos;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lbNit3;
    private javax.swing.JTable tblRegistros;
    private javax.swing.JLabel txtSaldoActual;
    private javax.swing.JLabel txtTotal;
    private javax.swing.JLabel txtValorTotal;
    // End of variables declaration//GEN-END:variables
}
