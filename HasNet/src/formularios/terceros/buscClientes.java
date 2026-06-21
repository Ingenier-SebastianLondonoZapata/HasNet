package formularios.terceros;

import clases.Instancias;
import clases.metodosGenerales;
import Vista.Ventas.VistaFactura;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.KeyStroke;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class buscClientes extends javax.swing.JDialog {

    DefaultTableModel modelo;
    TableRowSorter modeloOrdenado;
    Instancias instancias;
    private String opc = "";
    private VistaFactura factura;
    private boolean buscarInactivos;
    private String tipoBuscador = "";

    public String getTipoBuscador() {
        return tipoBuscador;
    }

    public void setTipoBuscador(String tipoBuscador) {
        this.tipoBuscador = tipoBuscador;
    }

    public VistaFactura getFactura() {
        return factura;
    }

    public void setFactura(VistaFactura factura) {
        this.factura = factura;
    }

    public buscClientes(java.awt.Frame parent, boolean modal, boolean buscarInactivos, Object[][] datos, String cod) {
        super(parent, modal);
        initComponents();

        this.instancias = Instancias.getInstancias();

        try {
            tblRegistros.getSelectionModel().setSelectionInterval(0, 0);
        } catch (ArrayIndexOutOfBoundsException exp) {
        }

        this.buscarInactivos = buscarInactivos;

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

    public String getOpc() {
        return opc;
    }

    public void setOpc(String opc) {
        this.opc = opc;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        filtro = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        rd4 = new javax.swing.JRadioButton();
        rd1 = new javax.swing.JRadioButton();
        rd2 = new javax.swing.JRadioButton();
        rdTelefono = new javax.swing.JRadioButton();
        txtId = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblRegistros = new javax.swing.JTable();
        btnEste = new javax.swing.JButton();
        btnEste1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Buscador de clientes...");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        rd4.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        rd4.setSelected(true);
        rd4.setText("General");
        rd4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rd4ItemStateChanged(evt);
            }
        });
        rd4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rd4ActionPerformed(evt);
            }
        });

        rd1.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        rd1.setText("Nombre");
        rd1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rd1ItemStateChanged(evt);
            }
        });

        rd2.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        rd2.setText("CC/Nit");
        rd2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rd2ItemStateChanged(evt);
            }
        });

        rdTelefono.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        rdTelefono.setText("Teléfono");
        rdTelefono.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdTelefonoItemStateChanged(evt);
            }
        });

        txtId.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
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

        tblRegistros.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        tblRegistros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Codigo", "Nombre", "Dirección", "Teléfono"
            }
        ));
        tblRegistros.setRowHeight(24);
        tblRegistros.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblRegistrosMouseClicked(evt);
            }
        });
        tblRegistros.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblRegistrosKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblRegistros);

        btnEste.setBackground(new java.awt.Color(204, 204, 204));
        btnEste.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        btnEste.setText("SELECCIONAR");
        btnEste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEsteActionPerformed(evt);
            }
        });

        btnEste1.setBackground(new java.awt.Color(46, 204, 113));
        btnEste1.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        btnEste1.setText("NUEVO");
        btnEste1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEste1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 726, Short.MAX_VALUE)
                    .addComponent(txtId)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(rd4)
                        .addGap(10, 10, 10)
                        .addComponent(rd1)
                        .addGap(10, 10, 10)
                        .addComponent(rd2)
                        .addGap(10, 10, 10)
                        .addComponent(rdTelefono)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnEste1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEste)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rd1)
                    .addComponent(rd2)
                    .addComponent(rdTelefono)
                    .addComponent(rd4))
                .addGap(1, 1, 1)
                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnEste, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEste1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEsteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEsteActionPerformed
        if (opc.equals("factura1")) {
            instancias.setValorCampo((String) tblRegistros.getValueAt(tblRegistros.getSelectedRow(), 0));
            this.dispose();
            metodosGenerales.presionarEnter(instancias.getCampoActual());
        } else {
            instancias.setValorCampo((String) tblRegistros.getValueAt(tblRegistros.getSelectedRow(), 0));
            this.dispose();
            metodosGenerales.presionarEnter(instancias.getCampoActual());
        }
    }//GEN-LAST:event_btnEsteActionPerformed

    private void txtIdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdKeyReleased
        filtrar(evt);
    }//GEN-LAST:event_txtIdKeyReleased

    public void filtrar(java.awt.event.KeyEvent evt) {
        if ((evt.getKeyCode() == 40 || evt.getKeyCode() == 38)) {
            int aumento = -1;

            if (evt.getKeyCode() == 40) {
                aumento = 1;
            }

            int fila = tblRegistros.getSelectionModel().getMinSelectionIndex() + aumento;
            try {

                if (fila == tblRegistros.getRowCount() && aumento == 1) {
                    fila = 0;
                }

                tblRegistros.getSelectionModel().setSelectionInterval(fila, fila);

            } catch (ArrayIndexOutOfBoundsException exp) {
            }
            return;
        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnEsteActionPerformed(null);
            return;
        }

        int i = -1;
        if (rd4.isSelected()) {
            modeloOrdenado.setRowFilter(RowFilter.regexFilter("(?i)" + txtId.getText(), 0));
            if (tblRegistros.getRowCount() == 0) {
                modeloOrdenado.setRowFilter(RowFilter.regexFilter("(?i)" + txtId.getText(), 1));
                if (tblRegistros.getRowCount() == 0) {
                    modeloOrdenado.setRowFilter(RowFilter.regexFilter("(?i)" + txtId.getText(), 2));
                    if (tblRegistros.getRowCount() == 0) {
                        modeloOrdenado.setRowFilter(RowFilter.regexFilter("(?i)" + txtId.getText(), 3));
                    }
                }
            }
        } else if (rd2.isSelected()) {
            i = 0;
        } else if (rd1.isSelected()) {
            i = 1;
        } else if (rdTelefono.isSelected()) {
            i = 3;
        }
        if (i > -1) {
            modeloOrdenado.setRowFilter(RowFilter.regexFilter("(?i)" + txtId.getText(), i));
        }

        try {
            tblRegistros.getSelectionModel().setSelectionInterval(0, 0);
        } catch (ArrayIndexOutOfBoundsException exp) {
        }

    }
    private void txtIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdActionPerformed

    private void tblRegistrosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblRegistrosKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (tblRegistros.getRowCount() > 1) {
                tblRegistros.getSelectionModel().setSelectionInterval(tblRegistros.getSelectionModel().getMinSelectionIndex() - 1, tblRegistros.getSelectionModel().getMinSelectionIndex() - 1);
            }
            btnEsteActionPerformed(null);
        }
    }//GEN-LAST:event_tblRegistrosKeyReleased

    private void tblRegistrosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblRegistrosMouseClicked
        if (evt.getClickCount() > 1) {
            btnEsteActionPerformed(null);
        }
    }//GEN-LAST:event_tblRegistrosMouseClicked

    private void btnEste1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEste1ActionPerformed
        this.dispose();

        nuevoTercero nuevo = new nuevoTercero(null, true, new metodosGenerales(), instancias, opc);
        if (opc.equals("factura")) {
            nuevo.setFactura(factura);
        }
        nuevo.setVisible(true);
    }//GEN-LAST:event_btnEste1ActionPerformed

    private void rd4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rd4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rd4ActionPerformed

    private void rd1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rd1ItemStateChanged
        KeyEvent x = new KeyEvent(this, WIDTH, WIDTH, WIDTH, KeyEvent.CTRL_MASK);
        filtrar(x);
    }//GEN-LAST:event_rd1ItemStateChanged

    private void rd2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rd2ItemStateChanged
        KeyEvent x = new KeyEvent(this, WIDTH, WIDTH, WIDTH, KeyEvent.CTRL_MASK);
        filtrar(x);
    }//GEN-LAST:event_rd2ItemStateChanged

    private void rdTelefonoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdTelefonoItemStateChanged
        KeyEvent x = new KeyEvent(this, WIDTH, WIDTH, WIDTH, KeyEvent.CTRL_MASK);
        filtrar(x);
    }//GEN-LAST:event_rdTelefonoItemStateChanged

    private void rd4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rd4ItemStateChanged
        KeyEvent x = new KeyEvent(this, WIDTH, WIDTH, WIDTH, KeyEvent.CTRL_MASK);
        filtrar(x);
    }//GEN-LAST:event_rd4ItemStateChanged

    public void noEncontrado(String codigo) {

        txtId.setText(codigo);

        //Siempre va a estar con la consulta, si es true osea, necesita buscar tambien los falsos ira vacia y buscara todo.
        String consulta = "";

        if (!buscarInactivos) {
            consulta = " Where activo = false ";
        }

        if (!opc.equals("factura") && !opc.equals("factura1") && !opc.equals("egreso") && !opc.equals("ingreso")) {
            if (consulta.equals("")) {
                consulta = " Where tipoTercero = 'TERCERO' OR tipoTercero = 'PROVEEDOR' ";
            } else {
                consulta = consulta + " AND tipoTercero = 'TERCERO' OR tipoTercero = 'PROVEEDOR' ";
            }
        }

        if (tipoBuscador.equals("convenio")) {
            if (consulta.equals("")) {
                consulta = " Where convenio <> '' ";
            } else {
                consulta = consulta + " AND convenio <> '' ";
            }
        }

        System.out.println("consulta " + consulta);
        tblRegistros.setModel(instancias.getSql().getRegistrosTerceros(consulta));
        modelo = (DefaultTableModel) tblRegistros.getModel();
        modeloOrdenado = new TableRowSorter<>(modelo);
        tblRegistros.setRowSorter(modeloOrdenado);

        txtId.requestFocus();

        filtro.add(rd1);
        filtro.add(rd2);
        filtro.add(rdTelefono);
        filtro.add(rd4);

        modeloOrdenado.setRowFilter(RowFilter.regexFilter("(?i)" + codigo, 1));
        try {
            tblRegistros.getSelectionModel().setSelectionInterval(0, 0);
        } catch (ArrayIndexOutOfBoundsException exp) {
        }

        if (tblRegistros.getColumnModel().getColumnCount() > 0) {
            tblRegistros.getColumnModel().getColumn(0).setMinWidth(130);
            tblRegistros.getColumnModel().getColumn(0).setPreferredWidth(130);
            tblRegistros.getColumnModel().getColumn(0).setMaxWidth(130);

            tblRegistros.getColumnModel().getColumn(2).setMinWidth(0);
            tblRegistros.getColumnModel().getColumn(2).setPreferredWidth(0);
            tblRegistros.getColumnModel().getColumn(2).setMaxWidth(0);

            tblRegistros.getColumnModel().getColumn(3).setMinWidth(130);
            tblRegistros.getColumnModel().getColumn(3).setPreferredWidth(130);
            tblRegistros.getColumnModel().getColumn(3).setMaxWidth(130);

            tblRegistros.getColumnModel().getColumn(4).setMinWidth(0);
            tblRegistros.getColumnModel().getColumn(4).setPreferredWidth(0);
            tblRegistros.getColumnModel().getColumn(4).setMaxWidth(0);

            tblRegistros.getColumnModel().getColumn(5).setMinWidth(100);
            tblRegistros.getColumnModel().getColumn(5).setPreferredWidth(120);
            tblRegistros.getColumnModel().getColumn(5).setMaxWidth(150);

        }
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
            java.util.logging.Logger.getLogger(buscClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(buscClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(buscClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(buscClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                buscClientes dialog = new buscClientes(new javax.swing.JFrame(), true, false, null, "");
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
    private javax.swing.JButton btnEste;
    private javax.swing.JButton btnEste1;
    private javax.swing.ButtonGroup filtro;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rd1;
    private javax.swing.JRadioButton rd2;
    private javax.swing.JRadioButton rd4;
    private javax.swing.JRadioButton rdTelefono;
    private javax.swing.JTable tblRegistros;
    private javax.swing.JTextField txtId;
    // End of variables declaration//GEN-END:variables
}
