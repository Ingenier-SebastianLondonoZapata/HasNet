package formularios.Ventas;

import formularios.terceros.*;
import clases.Instancias;
import Utilidades.BaseDatos.SQL;
import clases.big;
import clases.metodosGenerales;
import Modelo.Terceros.ModeloContacto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.KeyStroke;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class dlgProductosGrupo extends javax.swing.JDialog {

    private Instancias instancias;
    DefaultTableModel modelo;
    String factura, desde1 = "";
    TableRowSorter modeloOrdenado;
    metodosGenerales metodos = new metodosGenerales();

    public dlgProductosGrupo(java.awt.Frame parent, boolean modal, String grupo, String desde) {
        super(parent, modal);
        initComponents();

        this.setLocationRelativeTo(null);
        this.setTitle("PRODUCTOS");

        metodosGenerales metodos = new metodosGenerales();
        instancias = Instancias.getInstancias();
        desde1 = desde;

        modelo = (DefaultTableModel) tblListado.getModel();

        boolean conImpuestos = false;
        if (instancias.isPvpConIva()) {
            conImpuestos = true;
        }

        Object[][] prod = instancias.getSql().getDatosProductosGrupo("GRP-" + grupo.split("GRP-")[1], conImpuestos);
        lbNombre.setText(grupo.split("GRP-")[0]);
        for (int i = 0; i < prod.length; i++) {
            modelo.addRow(new Object[]{prod[i][0], prod[i][1], prod[i][3], ""});
        }

        for (int i = 0; i < tblListado.getRowCount(); i++) {
            tblListado.setValueAt(big.setMonedaExacta(big.getBigDecimal(tblListado.getValueAt(i, 2))), i, 2);
        }

        DefaultTableModel listado = (DefaultTableModel) tblListado.getModel();
        modeloOrdenado = new TableRowSorter<>(listado);
        tblListado.setRowSorter(modeloOrdenado);

//        cargarLista(listado);
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lbNombre = new javax.swing.JLabel();
        cmbTipo = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblListado = new javax.swing.JTable();
        txtCampo = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        btnDis = new javax.swing.JButton();
        txtBuscador = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        lbNombre.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        lbNombre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNombre.setText("HAMBURGUESAS");

        cmbTipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "LOCAL", "GENERAL" }));

        tblListado.setFont(new java.awt.Font("Century Gothic", 0, 22)); // NOI18N
        tblListado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Descripcion", "Valor", "Cant"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblListado.setRowHeight(35);
        tblListado.getTableHeader().setReorderingAllowed(false);
        tblListado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblListadoMouseClicked(evt);
            }
        });
        tblListado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblListadoKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblListado);
        if (tblListado.getColumnModel().getColumnCount() > 0) {
            tblListado.getColumnModel().getColumn(0).setMinWidth(0);
            tblListado.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblListado.getColumnModel().getColumn(0).setMaxWidth(0);
            tblListado.getColumnModel().getColumn(2).setMinWidth(120);
            tblListado.getColumnModel().getColumn(2).setPreferredWidth(120);
            tblListado.getColumnModel().getColumn(2).setMaxWidth(120);
            tblListado.getColumnModel().getColumn(3).setMinWidth(80);
            tblListado.getColumnModel().getColumn(3).setPreferredWidth(80);
            tblListado.getColumnModel().getColumn(3).setMaxWidth(80);
        }

        txtCampo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCampoKeyReleased(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(204, 204, 204));
        jButton1.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jButton1.setText("CARGAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnDis.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        btnDis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/boton-resta.png"))); // NOI18N
        btnDis.setText("Disminuir");
        btnDis.setToolTipText("Ctrl+M");
        btnDis.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        btnDis.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnDis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDisActionPerformed(evt);
            }
        });

        txtBuscador.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        txtBuscador.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtBuscador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscadorKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(cmbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtCampo, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(203, 203, 203))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(519, 519, 519)
                        .addComponent(btnDis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtBuscador)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(lbNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDis)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 477, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCampo, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblListadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblListadoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
            tblListado.setValueAt("", tblListado.getSelectedRow(), 3);
        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER && tblListado.getSelectedColumn() == 4) {

            Object[][] numero = new Object[tblListado.getRowCount()][6];

            for (int i = 0; i < tblListado.getRowCount(); i++) {
                numero[i][0] = tblListado.getValueAt(i, 0);
                numero[i][1] = tblListado.getValueAt(i, 1);
                numero[i][2] = tblListado.getValueAt(i, 2);
                numero[i][3] = tblListado.getValueAt(i, 3);
                numero[i][4] = tblListado.getValueAt(i, 4);
                numero[i][5] = tblListado.getValueAt(i, 5);
            }
            ventanaTerceros("", tblListado.getSelectedRow(), numero, tblListado.getValueAt(tblListado.getSelectedRow(), 0).toString());
        }
    }//GEN-LAST:event_tblListadoKeyReleased

    private void txtCampoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCampoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            ModeloContacto nodo = instancias.getSql().getDatosTercero(txtCampo.getText());
            tblListado.setValueAt(nodo.getId(), tblListado.getSelectedRow(), 3);
            tblListado.setValueAt(nodo.getNombre(), tblListado.getSelectedRow(), 4);
        }
    }//GEN-LAST:event_txtCampoKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Object[][] productos = new Object[tblListado.getRowCount()][2];

        int conse = 0;
        for (int i = 0; i < tblListado.getRowCount(); i++) {
            if (!tblListado.getValueAt(i, 3).equals("")) {
                productos[conse][0] = tblListado.getValueAt(i, 0);
                productos[conse][1] = tblListado.getValueAt(i, 3);
                conse = conse + 1;
            }
        }

        if (conse <= 0) {
            metodos.msgError(null, "Debe seleccionar un producto");
            return;
        } else {
            for (int i = 0; i < conse; i++) {
                if (desde1.equals("mesa")) {
                    for (int j = 0; j < Integer.parseInt(productos[i][1].toString()); j++) {
                        instancias.getMesa().getPnlFactura().cargarProducto(productos[i][0].toString(), "1", 1, "", "", "", true, "", "", "", "", "");
                    }
                } else if (desde1.equals("pedido")) {
                    for (int j = 0; j < Integer.parseInt(productos[i][1].toString()); j++) {
                        instancias.getPedido().cargarProducto(productos[i][0].toString(), "1", 1, "", "", "", true, "", "", "", "", "");
                    }
                } else if (desde1.equals("cotizacion")) {
                    for (int j = 0; j < Integer.parseInt(productos[i][1].toString()); j++) {
                        instancias.getCotiza().cargarProducto(productos[i][0].toString(), "1", 1, "", "", "", true, "", "", "", "", "");
                    }
                } else {
                    for (int j = 0; j < Integer.parseInt(productos[i][1].toString()); j++) {
                        instancias.getFactura().cargarProducto(productos[i][0].toString(), "1", 1, "", "", "", true, "", "", "", "", "");
                    }
                }
            }
            this.dispose();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnDisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDisActionPerformed
        int cant = 0;

        try {
            cant = Integer.parseInt(tblListado.getValueAt(tblListado.getSelectedRow(), 3).toString());
        } catch (Exception e) {
            cant = 0;
        }

        cant = cant - 1;

        if (cant <= 0) {
            tblListado.setValueAt("", tblListado.getSelectedRow(), 3);
        } else {
            tblListado.setValueAt(cant, tblListado.getSelectedRow(), 3);
        }
    }//GEN-LAST:event_btnDisActionPerformed

    private void tblListadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblListadoMouseClicked
        int cant = 0;

        try {
            cant = Integer.parseInt(tblListado.getValueAt(tblListado.getSelectedRow(), 3).toString());
        } catch (Exception e) {
            cant = 0;
        }

        cant = cant + 1;
        tblListado.setValueAt(cant, tblListado.getSelectedRow(), 3);
    }//GEN-LAST:event_tblListadoMouseClicked

    private void txtBuscadorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscadorKeyReleased
        modeloOrdenado.setRowFilter(RowFilter.regexFilter("(?i)" + txtBuscador.getText(), 1));
    }//GEN-LAST:event_txtBuscadorKeyReleased

    public void ventanaTerceros(String nit, int fila, Object[][] datos, String prod) {
        buscClientes buscar = new buscClientes(instancias.getMenu(), true, false, datos, prod);
        buscar.setOpc("factura1");
        buscar.setLocationRelativeTo(null);
        instancias.setBusClientes(buscar);
        instancias.setCampoActual(txtCampo);
        txtCampo.requestFocus();
        buscar.noEncontrado(nit);
        buscar.show();
    }

    public final void cargarLista(Object[][] Lista) {
        factura = Lista[0][3].toString();

        while (tblListado.getRowCount() > 0) {
            modelo.removeRow(0);
        }

        for (int i = 0; i < Lista.length; i++) {
            modelo.addRow(new Object[]{Lista[i][2], Lista[i][0], Lista[i][1], "", "", "LOCAL"});
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
            java.util.logging.Logger.getLogger(dlgProductosGrupo.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(dlgProductosGrupo.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(dlgProductosGrupo.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dlgProductosGrupo.class
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

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                dlgProductosGrupo dialog = new dlgProductosGrupo(new javax.swing.JFrame(), true, null, "");
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
    private javax.swing.JButton btnDis;
    private javax.swing.JComboBox cmbTipo;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbNombre;
    private javax.swing.JTable tblListado;
    private javax.swing.JTextField txtBuscador;
    private javax.swing.JTextField txtCampo;
    // End of variables declaration//GEN-END:variables
}
