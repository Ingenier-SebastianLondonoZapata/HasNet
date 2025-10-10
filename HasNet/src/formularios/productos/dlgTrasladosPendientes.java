package formularios.productos;

import clases.Instancias;
import Utilidades.BaseDatos.SQL;
import clases.cambiarColorTabla;
import clases.metodosGenerales;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.DefaultCellEditor;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.KeyStroke;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

public class dlgTrasladosPendientes extends javax.swing.JDialog {

    private Instancias instancias;
    DefaultTableModel modelo;
    TableRowSorter modeloOrdenado;
    metodosGenerales metodos = new metodosGenerales();

    public dlgTrasladosPendientes(java.awt.Frame parent, boolean modal, String cliente) {
        super(parent, modal);
        initComponents();

        tblPrestamos.setDefaultRenderer(Object.class, new cambiarColorTabla(24, 0));

        TableColumn tcr1 = tblPrestamos.getColumnModel().getColumn(5);
        TableCellEditor tcer1 = new DefaultCellEditor(cmbMovimientos);
        tcr1.setCellEditor(tcer1);

        this.setLocationRelativeTo(null);
        this.setTitle("TRASLADOS PENDIENTES");

        instancias = Instancias.getInstancias();

        Object[][] datos = instancias.getSql().getTrasladosPendientes();
        DefaultTableModel modeloTabla = (DefaultTableModel) tblPrestamos.getModel();

        for (int i = 0; i < datos.length; i++) {
            modeloTabla.addRow(new Object[]{"", "", "", "", "", "", "", "DETALLE"});
        }

        for (int i = 0; i < datos.length; i++) {
            tblPrestamos.setValueAt(datos[i][0], i, 0);
            tblPrestamos.setValueAt(metodos.fecha(datos[i][3].toString()), i, 1);
            tblPrestamos.setValueAt(datos[i][1] + ", " + datos[i][6], i, 2);
            tblPrestamos.setValueAt(datos[i][2] + ", " + datos[i][5], i, 3);
            tblPrestamos.setValueAt(metodos.restarFecha(metodosGenerales.fecha(), metodos.fecha(datos[i][3].toString())) + Long.parseLong(datos[i][4].toString()), i, 4);

            tblPrestamos.setValueAt(" Devolver", i, 5);

            if (datos[i][2].equals("123-22")) {
                tblPrestamos.setValueAt("", i, 8);
            } else {
                tblPrestamos.setValueAt(" Facturar", i, 8);
            }
        }

        modelo = (DefaultTableModel) tblPrestamos.getModel();
        modeloOrdenado = new TableRowSorter<>(modelo);
        tblPrestamos.setRowSorter(modeloOrdenado);

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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPrestamos = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        txtOrigen = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtDestino = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cmbMovimientos = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 30)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("PRESTAMOS PENDIENTES");

        jLabel3.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("N°:");

        txtId.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        txtId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIdKeyReleased(evt);
            }
        });

        tblPrestamos.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        tblPrestamos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "N°", "Fecha", "Bodega de origen", "Bodega de destino", "Edad", "", "Plazo", "VER", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPrestamos.setRowHeight(24);
        tblPrestamos.getTableHeader().setReorderingAllowed(false);
        tblPrestamos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPrestamosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPrestamos);
        if (tblPrestamos.getColumnModel().getColumnCount() > 0) {
            tblPrestamos.getColumnModel().getColumn(0).setMinWidth(70);
            tblPrestamos.getColumnModel().getColumn(0).setPreferredWidth(70);
            tblPrestamos.getColumnModel().getColumn(0).setMaxWidth(70);
            tblPrestamos.getColumnModel().getColumn(1).setMinWidth(100);
            tblPrestamos.getColumnModel().getColumn(1).setPreferredWidth(100);
            tblPrestamos.getColumnModel().getColumn(1).setMaxWidth(100);
            tblPrestamos.getColumnModel().getColumn(4).setMinWidth(40);
            tblPrestamos.getColumnModel().getColumn(4).setPreferredWidth(40);
            tblPrestamos.getColumnModel().getColumn(4).setMaxWidth(40);
            tblPrestamos.getColumnModel().getColumn(5).setMinWidth(80);
            tblPrestamos.getColumnModel().getColumn(5).setPreferredWidth(80);
            tblPrestamos.getColumnModel().getColumn(5).setMaxWidth(80);
            tblPrestamos.getColumnModel().getColumn(6).setMinWidth(40);
            tblPrestamos.getColumnModel().getColumn(6).setPreferredWidth(40);
            tblPrestamos.getColumnModel().getColumn(6).setMaxWidth(40);
            tblPrestamos.getColumnModel().getColumn(7).setMinWidth(0);
            tblPrestamos.getColumnModel().getColumn(7).setPreferredWidth(0);
            tblPrestamos.getColumnModel().getColumn(7).setMaxWidth(0);
            tblPrestamos.getColumnModel().getColumn(8).setMinWidth(80);
            tblPrestamos.getColumnModel().getColumn(8).setPreferredWidth(80);
            tblPrestamos.getColumnModel().getColumn(8).setMaxWidth(80);
        }

        jLabel5.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Origen:");

        txtOrigen.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        txtOrigen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtOrigenKeyReleased(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Destino:");

        txtDestino.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        txtDestino.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDestinoKeyReleased(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("X");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        cmbMovimientos.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        cmbMovimientos.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Facturar", "Devolver" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 830, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cmbMovimientos, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(21, 21, 21)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDestino, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)))
                        .addGap(20, 20, 20))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel2)))
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 515, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtId, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtOrigen)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtDestino)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12)
                .addComponent(cmbMovimientos, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtIdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdKeyReleased
        modeloOrdenado.setRowFilter(RowFilter.regexFilter("(?i)" + txtId.getText(), 0));
        txtOrigen.setText("");
        txtDestino.setText("");
    }//GEN-LAST:event_txtIdKeyReleased

    private void tblPrestamosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPrestamosMouseClicked
        int fila = tblPrestamos.getSelectedRow();

        if (evt.getClickCount() == 2) {
            instancias.getPrestamos().desdeTraslados(tblPrestamos.getValueAt(tblPrestamos.getSelectedRow(), 0).toString());
            this.dispose();
        }

        if (tblPrestamos.getSelectedColumn() == 5) {
            if (metodos.msgPregunta(null, "¿Desea continuar?") == 0) {
                instancias.getPrestamos().facturarTraslados1(tblPrestamos.getValueAt(tblPrestamos.getSelectedRow(), 0).toString());
                metodos.msgExito(null, "Traslado liquidado correctamente.");
                this.dispose();
                return;
            }
        }

        if (tblPrestamos.getSelectedColumn() == 7) {
            dlgDetalleTrasladosPendientes traslados = new dlgDetalleTrasladosPendientes(null, true, tblPrestamos.getValueAt(tblPrestamos.getSelectedRow(), 0).toString());
            traslados.setLocationRelativeTo(null);
            traslados.setVisible(true);
        }

        if (tblPrestamos.getSelectedColumn() == 8) {
            if (!tblPrestamos.getValueAt(fila, 8).equals("")) {

                int diasPlazo = 0;

                try {
                    diasPlazo = Integer.parseInt(tblPrestamos.getValueAt(tblPrestamos.getSelectedRow(), 6).toString());
                } catch (Exception e) {
                    diasPlazo = 0;
                }

                String mensaje = "";
                if (diasPlazo <= 0) {
                    mensaje = "Sin plazo. ";
                }

                if (metodos.msgPregunta(null, mensaje + "¿Desea continuar?") == 0) {
                    instancias.getPrestamos().facturarTraslados(tblPrestamos.getValueAt(tblPrestamos.getSelectedRow(), 0).toString(), "" + diasPlazo);
                    metodos.msgExito(null, "Traslado liquidado correctamente.");
                    this.dispose();
                }
            }
        }
    }//GEN-LAST:event_tblPrestamosMouseClicked

    private void txtOrigenKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtOrigenKeyReleased
        modeloOrdenado.setRowFilter(RowFilter.regexFilter("(?i)" + txtOrigen.getText(), 2));
        txtId.setText("");
        txtDestino.setText("");
    }//GEN-LAST:event_txtOrigenKeyReleased

    private void txtDestinoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDestinoKeyReleased
        modeloOrdenado.setRowFilter(RowFilter.regexFilter("(?i)" + txtDestino.getText(), 3));
        txtOrigen.setText("");
        txtId.setText("");
    }//GEN-LAST:event_txtDestinoKeyReleased

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        this.dispose();
    }//GEN-LAST:event_jLabel4MouseClicked

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
            java.util.logging.Logger.getLogger(dlgTrasladosPendientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(dlgTrasladosPendientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(dlgTrasladosPendientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dlgTrasladosPendientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                dlgTrasladosPendientes dialog = new dlgTrasladosPendientes(new javax.swing.JFrame(), true, null);
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
    private javax.swing.JComboBox cmbMovimientos;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblPrestamos;
    private javax.swing.JTextField txtDestino;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtOrigen;
    // End of variables declaration//GEN-END:variables
}
