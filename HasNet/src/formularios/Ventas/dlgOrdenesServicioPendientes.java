package formularios.Ventas;

import clases.IconCellRenderer;
import formularios.productos.*;
import formularios.Parqueadero.*;
import clases.Instancias;
import Utilidades.BaseDatos.SQL;
import clases.big;
import clases.metodosGenerales;
import Vista.Solicitudes.vistaSolicitarPermisos;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.KeyStroke;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class dlgOrdenesServicioPendientes extends javax.swing.JDialog {

    private Instancias instancias;

    DefaultTableModel modelo;
    TableRowSorter modeloOrdenado;
    metodosGenerales metodos = new metodosGenerales();

    public dlgOrdenesServicioPendientes(java.awt.Frame parent, boolean modal, String cliente) {
        super(parent, modal);
        initComponents();

        tblTraslados.setDefaultRenderer(Object.class, new IconCellRenderer());

        this.setLocationRelativeTo(null);
        this.setTitle("Ordenes Pendientes...");
        instancias = Instancias.getInstancias();
        modelo = (DefaultTableModel) tblTraslados.getModel();
        modeloOrdenado = new TableRowSorter<>(modelo);
        tblTraslados.setRowSorter(modeloOrdenado);
        cargarDatos();

        this.getRootPane().registerKeyboardAction(accion("cerrar", this), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
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

        grpEncabezados = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtFinalizar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTraslados = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        txtId = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtOrigen = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 30)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("ORDENES DE SERVICIO");

        txtFinalizar.setBackground(new java.awt.Color(46, 204, 113));
        txtFinalizar.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        txtFinalizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/agregar.png"))); // NOI18N
        txtFinalizar.setText("FACTURAR");
        txtFinalizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txtFinalizar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        txtFinalizar.setMargin(new java.awt.Insets(2, 7, 2, 5));
        txtFinalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFinalizarActionPerformed(evt);
            }
        });

        tblTraslados.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        tblTraslados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Fecha", "Cliente", "Nombre", "Total", "Fact.", "Plazo", "Detallado", "", "Bodega"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, true, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTraslados.setRowHeight(24);
        tblTraslados.getTableHeader().setReorderingAllowed(false);
        tblTraslados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTrasladosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblTraslados);
        if (tblTraslados.getColumnModel().getColumnCount() > 0) {
            tblTraslados.getColumnModel().getColumn(0).setMinWidth(100);
            tblTraslados.getColumnModel().getColumn(0).setPreferredWidth(100);
            tblTraslados.getColumnModel().getColumn(0).setMaxWidth(100);
            tblTraslados.getColumnModel().getColumn(1).setMinWidth(95);
            tblTraslados.getColumnModel().getColumn(1).setPreferredWidth(95);
            tblTraslados.getColumnModel().getColumn(1).setMaxWidth(95);
            tblTraslados.getColumnModel().getColumn(2).setMinWidth(50);
            tblTraslados.getColumnModel().getColumn(2).setPreferredWidth(80);
            tblTraslados.getColumnModel().getColumn(2).setMaxWidth(120);
            tblTraslados.getColumnModel().getColumn(4).setMinWidth(50);
            tblTraslados.getColumnModel().getColumn(4).setPreferredWidth(90);
            tblTraslados.getColumnModel().getColumn(4).setMaxWidth(130);
            tblTraslados.getColumnModel().getColumn(5).setMinWidth(15);
            tblTraslados.getColumnModel().getColumn(5).setPreferredWidth(30);
            tblTraslados.getColumnModel().getColumn(5).setMaxWidth(50);
            tblTraslados.getColumnModel().getColumn(6).setMinWidth(35);
            tblTraslados.getColumnModel().getColumn(6).setPreferredWidth(50);
            tblTraslados.getColumnModel().getColumn(6).setMaxWidth(75);
            tblTraslados.getColumnModel().getColumn(7).setMinWidth(0);
            tblTraslados.getColumnModel().getColumn(7).setPreferredWidth(0);
            tblTraslados.getColumnModel().getColumn(7).setMaxWidth(0);
            tblTraslados.getColumnModel().getColumn(8).setMinWidth(40);
            tblTraslados.getColumnModel().getColumn(8).setPreferredWidth(40);
            tblTraslados.getColumnModel().getColumn(8).setMaxWidth(40);
            tblTraslados.getColumnModel().getColumn(9).setMinWidth(80);
            tblTraslados.getColumnModel().getColumn(9).setPreferredWidth(100);
            tblTraslados.getColumnModel().getColumn(9).setMaxWidth(150);
        }

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        txtId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIdKeyReleased(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jLabel1.setText("ID:");

        jLabel4.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jLabel4.setText("CEDULA:");

        txtOrigen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtOrigenKeyReleased(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jLabel6.setText("NOMBRE:");

        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNombre))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 844, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(txtFinalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtFinalizar)
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

    private void txtFinalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFinalizarActionPerformed
        for (int i = 0; i < tblTraslados.getRowCount(); i++) {
            if ((Boolean) tblTraslados.getValueAt(i, 5)) {
                instancias.getFactura().facturarOrdenesServicios(tblTraslados.getValueAt(i, 0).toString(), tblTraslados.getValueAt(i, 6).toString(),
                        tblTraslados.getValueAt(i, 9).toString());
            }
        }

        this.dispose();
    }//GEN-LAST:event_txtFinalizarActionPerformed

    private void tblTrasladosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTrasladosMouseClicked
        if (tblTraslados.getSelectedColumn() == 8) {
            if (!instancias.getUsuario().equals("ADMIN")) {
                vistaSolicitarPermisos permisos = new vistaSolicitarPermisos(null, true,
                        "ANULACIÓN ORDEN", "ANULACION", tblTraslados.getValueAt(tblTraslados.getSelectedRow(), 0).toString(), "anulacion");
                permisos.setLocationRelativeTo(null);
                permisos.setVisible(true);
                return;
            } else {
                if (metodos.msgPregunta(null, "¿Anular orden de servicio?") != 0) {
                    return;
                } else {
                    String nota = "";
                    nota = metodos.msgIngresar(null, "NOTA: ");

                    if (nota.equals("")) {
                        return;
                    }

                    instancias.getOrdenServicio().cargarOrdenesServicios(tblTraslados.getValueAt(tblTraslados.getSelectedRow(), 0).toString());

                    String bodega = "";
                    try {
                        bodega = tblTraslados.getValueAt(tblTraslados.getSelectedRow(), 9).toString();
                    } catch (Exception e) {
                    }

                    if ("".equals(bodega)) {
                        bodega = "123-22";
                    }

                    String baseUtilizada = obtenerBase(bodega);
                    instancias.getOrdenServicio().anularOrdenServicio(nota, tblTraslados.getValueAt(tblTraslados.getSelectedRow(), 0).toString(), baseUtilizada);
                    this.dispose();
                }
            }
        }

        if (evt.getClickCount() == 2) {
            instancias.getOrdenServicio().cargarOrdenesServicios(tblTraslados.getValueAt(tblTraslados.getSelectedRow(), 0).toString());
            this.dispose();
        }
    }//GEN-LAST:event_tblTrasladosMouseClicked

    private void txtIdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdKeyReleased
        modeloOrdenado.setRowFilter(RowFilter.regexFilter("(?i)" + txtId.getText(), 0));
        txtOrigen.setText("");
        txtNombre.setText("");
    }//GEN-LAST:event_txtIdKeyReleased

    private void txtOrigenKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtOrigenKeyReleased
        modeloOrdenado.setRowFilter(RowFilter.regexFilter("(?i)" + txtOrigen.getText(), 2));
        txtId.setText("");
        txtNombre.setText("");
    }//GEN-LAST:event_txtOrigenKeyReleased

    private void txtNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyReleased
        modeloOrdenado.setRowFilter(RowFilter.regexFilter("(?i)" + txtNombre.getText(), 3));
        txtId.setText("");
        txtOrigen.setText("");
    }//GEN-LAST:event_txtNombreKeyReleased

    public String obtenerBase(String baseUtilizada) {
        if (baseUtilizada.equals("123-22")) {
            baseUtilizada = "bdProductos";
        } else if (baseUtilizada.equals("BODEGA-1")) {
            baseUtilizada = "bdProductosBodega1";
        } else if (baseUtilizada.equals("BODEGA-2")) {
            baseUtilizada = "bdProductosBodega2";
        } else if (baseUtilizada.equals("BODEGA-3")) {
            baseUtilizada = "bdProductosBodega3";
        } else if (baseUtilizada.equals("BODEGA-4")) {
            baseUtilizada = "bdProductosBodega4";
        }
        return baseUtilizada;
    }

    public void cargarDatos() {

        Icon icono = null;
        ImageIcon fot = new ImageIcon(getClass().getResource("/imagenes/borrar.png"));
        icono = new ImageIcon(fot.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT));

        Object[][] datos = instancias.getSql().getOrdenesServicioPendientes();
        DefaultTableModel modeloTabla = (DefaultTableModel) tblTraslados.getModel();

        while (tblTraslados.getRowCount() > 0) {
            modeloTabla.removeRow(0);
        }

        for (int i = 0; i < datos.length; i++) {
            modeloTabla.addRow(new Object[]{"", "", "", "", "", false, "", "    VER"});
        }

        for (int i = 0; i < datos.length; i++) {
            tblTraslados.setValueAt(datos[i][0], i, 0);
            tblTraslados.setValueAt(metodos.fecha(datos[i][1].toString()), i, 1);
            tblTraslados.setValueAt(datos[i][2], i, 2);
            tblTraslados.setValueAt(datos[i][4], i, 3);
            tblTraslados.setValueAt(big.setMoneda(big.getBigDecimal(datos[i][3])), i, 4);
            tblTraslados.setValueAt(new JLabel(icono), i, 8);
            tblTraslados.setValueAt(datos[i][5], i, 9);
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
            java.util.logging.Logger.getLogger(dlgOrdenesServicioPendientes.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(dlgOrdenesServicioPendientes.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(dlgOrdenesServicioPendientes.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dlgOrdenesServicioPendientes.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                dlgOrdenesServicioPendientes dialog = new dlgOrdenesServicioPendientes(new javax.swing.JFrame(), true, null);
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
    private javax.swing.ButtonGroup grpEncabezados;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblTraslados;
    private javax.swing.JButton txtFinalizar;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtOrigen;
    // End of variables declaration//GEN-END:variables
}
