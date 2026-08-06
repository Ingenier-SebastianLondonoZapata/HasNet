package formularios.Ventas;

import Vista.Productos.VistaBuscadorProductos;
import clases.Instancias;
import Utilidades.BaseDatos.SQL;
import clases.metodosGenerales;
import Vista.Ventas.VistaFactura;
import formularios.productos.*;
import java.awt.event.KeyEvent;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class buscReporteFacturaPedido extends javax.swing.JDialog {

    DefaultTableModel modelo;
    TableRowSorter modeloOrdenado;
    Instancias instancias;
    private String opc = "";
    private VistaFactura factura;
    private SQL sql;



    public VistaFactura getFactura() {
        return factura;
    }

    public void setFactura(VistaFactura factura) {
        this.factura = factura;
    }

    public buscReporteFacturaPedido(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tblRegistros = new javax.swing.JTable();
        txtId = new javax.swing.JTextField();
        btnEste = new javax.swing.JButton();
        rd1 = new javax.swing.JRadioButton();
        rd2 = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("CLIENTES");

        tblRegistros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "axsd", "sdfsdf", "asdas"
            }
        ));
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
        if (tblRegistros.getColumnModel().getColumnCount() > 0) {
            tblRegistros.getColumnModel().getColumn(2).setMinWidth(0);
            tblRegistros.getColumnModel().getColumn(2).setPreferredWidth(0);
            tblRegistros.getColumnModel().getColumn(2).setMaxWidth(0);
        }

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

        btnEste.setText("Seleccionar");
        btnEste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEsteActionPerformed(evt);
            }
        });

        rd1.setSelected(true);
        rd1.setText("Nombre");

        rd2.setText("Pedido");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 502, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtId)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(rd1)
                        .addGap(18, 18, 18)
                        .addComponent(rd2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEste)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEste)
                    .addComponent(rd1)
                    .addComponent(rd2))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtIdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdKeyReleased
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

        int i = 0;

        if (rd2.isSelected()) {
            i = 2;
        }

        modeloOrdenado.setRowFilter(RowFilter.regexFilter("(?i)" + txtId.getText(), i));

        try {
            tblRegistros.getSelectionModel().setSelectionInterval(0, 0);
        } catch (ArrayIndexOutOfBoundsException exp) {
        }

    }//GEN-LAST:event_txtIdKeyReleased

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

    private void btnEsteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEsteActionPerformed
        String consulta = "";
        consulta = (String) tblRegistros.getValueAt(tblRegistros.getSelectedRow(), 3);
        System.out.println("Consulta: " + consulta);
        String id = (String) tblRegistros.getValueAt(tblRegistros.getSelectedRow(), 0);

        instancias.getReporte().totalProductosPedidos(id, consulta, instancias.getInformacionEmpresaCompleto(), false);
        this.dispose();
    }//GEN-LAST:event_btnEsteActionPerformed

    public void setInstancia(Instancias instancia) {
        instancias = instancia;
        sql = instancias.getSql();
        try {
            tblRegistros.getSelectionModel().setSelectionInterval(0, 0);
        } catch (ArrayIndexOutOfBoundsException exp) {
        }
    }

    public void noEncontrado(String codigo) {

        txtId.setText(codigo);

        tblRegistros.setModel(instancias.getSql().getReporteFacturaPedidos());
        modelo = (DefaultTableModel) tblRegistros.getModel();
        modeloOrdenado = new TableRowSorter<>(modelo);
        tblRegistros.setRowSorter(modeloOrdenado);

        txtId.requestFocus();

        filtro.add(rd1);
        filtro.add(rd2);

        modeloOrdenado.setRowFilter(RowFilter.regexFilter("(?i)" + codigo, 1));
        try {
            tblRegistros.getSelectionModel().setSelectionInterval(0, 0);
        } catch (ArrayIndexOutOfBoundsException exp) {
        }

        if (tblRegistros.getColumnModel().getColumnCount() > 0) {
            tblRegistros.getColumnModel().getColumn(3).setMinWidth(0);
            tblRegistros.getColumnModel().getColumn(3).setPreferredWidth(0);
            tblRegistros.getColumnModel().getColumn(3).setMaxWidth(0);
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
            java.util.logging.Logger.getLogger(VistaBuscadorProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaBuscadorProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaBuscadorProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaBuscadorProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                buscReporteFacturaPedido dialog = new buscReporteFacturaPedido(new javax.swing.JFrame(), true);
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
    private javax.swing.ButtonGroup filtro;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rd1;
    private javax.swing.JRadioButton rd2;
    private javax.swing.JTable tblRegistros;
    private javax.swing.JTextField txtId;
    // End of variables declaration//GEN-END:variables
}
