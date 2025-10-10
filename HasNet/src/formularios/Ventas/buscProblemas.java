package formularios.Ventas;

import Vista.Ventas.vistaFactura;
import Vista.Productos.vistaIngresos;
import formularios.productos.*;
import clases.Instancias;
import clases.cambiarColorTabla;
import clases.metodosGenerales;
import clases.productos.ndGrupo;
import java.util.Vector;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;

public final class buscProblemas extends javax.swing.JDialog {

    DefaultTableModel modelo;
    TableRowSorter modeloOrdenado;
    Instancias instancias;
    private String opc;
    private vistaFactura factura;
    private vistaIngresos ingreso;
    private String claseBuscador = "";
    int pos = 0;
    metodosGenerales metodos = new metodosGenerales();

    public String getClaseBuscador() {
        return claseBuscador;
    }

    public void setClaseBuscador(String claseBuscador) {
        this.claseBuscador = claseBuscador;
    }

    public buscProblemas(java.awt.Frame parent, boolean modal, boolean buscarInactivos) {
        super(parent, modal);
        initComponents();
        instancias = Instancias.getInstancias();
        cargarTablaProductos(buscarInactivos);
        tblRegistros.setDefaultRenderer(Object.class, new cambiarColorTabla(16, 0));
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

    public void cargarTablaProductos(boolean buscarInactivos) {
        Object[][] datos = instancias.getSql().getRegistrosGrupos3();
        for (int i = 0; i < datos.length; i++) {
            ndGrupo grupo = new ndGrupo();
            grupo.setCodigo(datos[i][0].toString());
            grupo.setNombre(datos[i][1].toString());
        }
        //Siempre va a estar con la consulta, si es true osea, necesita buscar tambien los falsos ira vacia y buscara todo.
        String consulta = "";

        tblRegistros.setModel(instancias.getSql().getRegistrosProblemas());

        if (tblRegistros.getColumnModel().getColumnCount() > 0) {
            tblRegistros.getColumnModel().getColumn(0).setMinWidth(0);
            tblRegistros.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblRegistros.getColumnModel().getColumn(0).setMaxWidth(0);
            tblRegistros.getColumnModel().getColumn(2).setMinWidth(0);
            tblRegistros.getColumnModel().getColumn(2).setPreferredWidth(0);
            tblRegistros.getColumnModel().getColumn(2).setMaxWidth(0);
        }

        modelo = (DefaultTableModel) tblRegistros.getModel();

        modeloOrdenado = new TableRowSorter<>(modelo);
        tblRegistros.setRowSorter(modeloOrdenado);

        txtId.requestFocus();

        try {
            tblRegistros.getSelectionModel().setSelectionInterval(1, 1);
        } catch (ArrayIndexOutOfBoundsException exp) {
        }
    }

    public vistaFactura getFactura() {
        return factura;
    }

    public void setFactura(vistaFactura factura) {
        this.factura = factura;
    }

    public vistaIngresos getIngreso() {
        return ingreso;
    }

    public void setIngreso(vistaIngresos ingreso) {
        this.ingreso = ingreso;
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tblRegistros = new javax.swing.JTable();
        lbBuscador4 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        lbBuscador3 = new javax.swing.JLabel();
        txtId1 = new javax.swing.JTextField();
        btnEste = new javax.swing.JButton();
        btnEste1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Problemas del automotor...");
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        tblRegistros.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        tblRegistros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Problema", "Sel"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblRegistros.setRowHeight(24);
        tblRegistros.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblRegistrosMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tblRegistrosMouseEntered(evt);
            }
        });
        tblRegistros.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblRegistrosKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblRegistros);
        if (tblRegistros.getColumnModel().getColumnCount() > 0) {
            tblRegistros.getColumnModel().getColumn(0).setMinWidth(0);
            tblRegistros.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblRegistros.getColumnModel().getColumn(0).setMaxWidth(0);
            tblRegistros.getColumnModel().getColumn(2).setMinWidth(0);
            tblRegistros.getColumnModel().getColumn(2).setPreferredWidth(0);
            tblRegistros.getColumnModel().getColumn(2).setMaxWidth(0);
        }

        lbBuscador4.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbBuscador4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbBuscador4.setText("BUSCADOR:");

        txtId.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtIdMouseClicked(evt);
            }
        });
        txtId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIdKeyReleased(evt);
            }
        });

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));

        lbBuscador3.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbBuscador3.setText("Problemas encontrados:");

        txtId1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtId1.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtId1.setEnabled(false);
        txtId1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtId1MouseClicked(evt);
            }
        });
        txtId1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtId1KeyReleased(evt);
            }
        });

        btnEste.setBackground(new java.awt.Color(46, 204, 113));
        btnEste.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnEste.setText("Cargar Seleccionados");
        btnEste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEsteActionPerformed(evt);
            }
        });

        btnEste1.setBackground(new java.awt.Color(46, 204, 113));
        btnEste1.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        btnEste1.setText("REGISTRAR");
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
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbBuscador3)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(txtId1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnEste)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbBuscador4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtId)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEste1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtId)
                    .addComponent(lbBuscador4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEste1, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbBuscador3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtId1, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEste)
                .addContainerGap())
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
        if (opc.equals("orden")) {
            instancias.getOrdenServicio().cargarProblema(txtId1.getText().substring(0, txtId1.getText().length() - 2), "1");
            this.dispose();
        } else {
            instancias.getOrdenServicio().cargarProblema(txtId1.getText().substring(0, txtId1.getText().length() - 2), "2");
            this.dispose();
        }
    }//GEN-LAST:event_btnEsteActionPerformed

    private void txtIdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdKeyReleased
        filtrar(evt);
    }//GEN-LAST:event_txtIdKeyReleased

    public void filtrar(java.awt.event.KeyEvent evt) {
        try {
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
                    tblRegistros.scrollRectToVisible(tblRegistros.getCellRect(tblRegistros.getSelectedRow(), 0, true));

                } catch (ArrayIndexOutOfBoundsException exp) {
                }
                return;
            }

            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                btnEsteActionPerformed(null);
                return;
            }
        } catch (Exception e) {
        }

        System.out.println("pos 1 " + pos);
        if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (pos == 5) {
                pos = 0;
            } else if (pos == 2) {
                pos = 5;
            } else {
                pos = pos + 1;
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            if (pos == 5) {
                pos = 2;
            } else if (pos == 0) {
                pos = 5;
            } else {
                pos = pos - 1;
            }
        } else {
            pos = 0;
        }
        System.out.println("pos 2 " + pos);

        modeloOrdenado.setRowFilter(RowFilter.regexFilter("(?i)" + txtId.getText(), 1));

        try {
            tblRegistros.getSelectionModel().setSelectionInterval(0, 0);
        } catch (ArrayIndexOutOfBoundsException exp) {
        }
    }

    private void tblRegistrosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblRegistrosMouseClicked
        if (evt.getClickCount() >= 1) {
            if (!(Boolean) tblRegistros.getValueAt(tblRegistros.getSelectedRow(), 2)) {
                txtId1.setText(txtId1.getText() + tblRegistros.getValueAt(tblRegistros.getSelectedRow(), 1) + ", ");
                tblRegistros.setValueAt(true, tblRegistros.getSelectedRow(), 2);
            } else {
                tblRegistros.setValueAt(false, tblRegistros.getSelectedRow(), 2);

                String[] texto1 = txtId1.getText().split(", ");
                txtId1.setText("");
                for (int i = 0; i < texto1.length; i++) {
                    if (!texto1[i].equals(tblRegistros.getValueAt(tblRegistros.getSelectedRow(), 1))) {
                        txtId1.setText(txtId1.getText() + texto1[i] + ", ");
                    }
                }

                String[] texto = txtId1.getText().split(", ");
                txtId1.setText("");
                for (int i = 0; i < texto.length; i++) {
                    if (!texto[i].equals(tblRegistros.getValueAt(tblRegistros.getSelectedRow(), 0))) {
                        txtId1.setText(txtId1.getText() + texto[i] + ", ");
                    }
                }

                if (txtId1.getText().equals(", ") || txtId.getText().equals(",")) {
                    txtId1.setText("");
                }
            }
        }

        tblRegistros.removeEditor();
        txtId1.requestFocus();
    }//GEN-LAST:event_tblRegistrosMouseClicked

    private void tblRegistrosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblRegistrosKeyReleased
//        if (tblRegistros.getSelectedColumn() == 9) {
//            if (!tblRegistros.getValueAt(tblRegistros.getSelectedRow(), 9).equals("")) {
//                tblRegistros.setValueAt(true, tblRegistros.getSelectedRow(), 8);
//                tblRegistros.removeEditor();
//            } else {
//                tblRegistros.setValueAt(false, tblRegistros.getSelectedRow(), 8);
//                tblRegistros.removeEditor();
//            }
//        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
//            if (tblRegistros.getRowCount() > 1) {
//                tblRegistros.getSelectionModel().setSelectionInterval(tblRegistros.getSelectionModel().getMinSelectionIndex() - 1, tblRegistros.getSelectionModel().getMinSelectionIndex() - 1);
//            }
//            btnEsteActionPerformed(null);
//        }
//        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
//            tblRegistros.scrollRectToVisible(tblRegistros.getCellRect(tblRegistros.getSelectedRow(), 0, true));
//        }

    }//GEN-LAST:event_tblRegistrosKeyReleased

    private void txtIdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtIdMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdMouseClicked

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed

    }//GEN-LAST:event_formKeyPressed

    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_formKeyReleased

    private void txtId1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtId1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtId1MouseClicked

    private void txtId1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtId1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtId1KeyReleased

    private void tblRegistrosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblRegistrosMouseEntered
//        if (evt.getClickCount() >= 1 && tblRegistros.getSelectedColumn() == 2) {
//            if ((Boolean) tblRegistros.getValueAt(tblRegistros.getSelectedRow(), 2)) {
//                txtId1.setText(txtId1.getText() + tblRegistros.getValueAt(tblRegistros.getSelectedRow(), 1) + ", ");
//            } else {
//                String[] texto = txtId1.getText().split(", ");
//                txtId1.setText("");
//                for (int i = 0; i < texto.length; i++) {
//                    if (!texto[i].equals(tblRegistros.getValueAt(tblRegistros.getSelectedRow(), 1))) {
//                        txtId1.setText(txtId1.getText() + texto[i] + ", ");
//                    }
//                }
//            }
//        }
//
//        if (evt.getClickCount() > 1) {
//            txtId1.setText(tblRegistros.getValueAt(tblRegistros.getSelectedRow(), 1).toString() + "  ");
//            btnEsteActionPerformed(null);
//        }
    }//GEN-LAST:event_tblRegistrosMouseEntered

    private void btnEste1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEste1ActionPerformed
        if (txtId.getText().equals("")) {
            metodos.msgAdvertencia(null, "Ingrese el problema");
            return;
        }

        if (metodos.msgPregunta(null, "¿Desea continuar?") == 0) {

            if (!instancias.getSql().agregarProblema(txtId.getText().toUpperCase())) {
                metodos.msgError(null, "Error al guardar el problema");
                return;
            } else {
                metodos.msgExito(null, "Problema guardado con exito");
            }
            this.dispose();
        }
    }//GEN-LAST:event_btnEste1ActionPerformed
    public void setSeleccionados(String cod) {
        for (int j = 0; j < modelo.getRowCount(); j++) {
            if (modelo.getValueAt(j, 0).equals(cod)) {
                modelo.setValueAt(true, j, 6);
                break;
            }
        }
    }

    public void setSeleccionados(Vector v) {
        for (int i = 0; i < v.size(); i++) {
            for (int j = 0; j < modelo.getRowCount(); j++) {
                if (modelo.getValueAt(j, 0).equals(v.get(i))) {
                    modelo.setValueAt(true, j, 6);
                    break;
                }
            }
        }
    }

    /**
     *
     * @param instancia
     */

    public void noEncontrado(String codigo) {
        txtId.setText(codigo);
        modeloOrdenado.setRowFilter(RowFilter.regexFilter("(?i)" + codigo, 1));
        try {
            tblRegistros.getSelectionModel().setSelectionInterval(0, 0);
        } catch (ArrayIndexOutOfBoundsException exp) {
        }
    }

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
            java.util.logging.Logger.getLogger(buscProblemas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(buscProblemas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(buscProblemas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(buscProblemas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
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
                buscProblemas dialog = new buscProblemas(new javax.swing.JFrame(), true, false);
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
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lbBuscador3;
    private javax.swing.JLabel lbBuscador4;
    private javax.swing.JTable tblRegistros;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtId1;
    // End of variables declaration//GEN-END:variables
//
//    private void registerKeyboardAction(ActionListener accionTeclas, String cerrar, KeyStroke CTRL_G, int WHEN_IN_FOCUSED_WINDOW) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
}
