package formularios.Parqueadero;

import clases.Instancias;
import Utilidades.BaseDatos.SQL;
import clases.cambiarColorTabla;
import clases.metodosGenerales;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;

public class dlgCascos extends javax.swing.JDialog {

    private Instancias instancias;
    metodosGenerales metodos = new metodosGenerales();
    String codigo1;
    String placa1;

    public dlgCascos(java.awt.Frame parent, boolean modal, String placa) {
        super(parent, modal);
        initComponents();

        placa1 = placa;
        tblCascos.setDefaultRenderer(Object.class, new cambiarColorTabla(11, 0));
        pnlInvisible.setVisible(false);

        this.setSize(java.awt.Toolkit.getDefaultToolkit().getScreenSize().width, java.awt.Toolkit.getDefaultToolkit().getScreenSize().height);
        this.setTitle("Estante de cascos");

        metodosGenerales metodos = new metodosGenerales();

        Object[][] datos1 = instancias.getSql().getDatosMaestraParqueadero();

        tblCascos.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        tblCascos.setRowHeight(40);
        tblCascos.getTableHeader().setResizingAllowed(false);
        tblCascos.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tblCascos);

        for (int i = Integer.parseInt(datos1[0][1].toString()); i < 26; i++) {
            if (tblCascos.getColumnModel().getColumnCount() > 0) {
                tblCascos.getColumnModel().getColumn(i).setMinWidth(0);
                tblCascos.getColumnModel().getColumn(i).setPreferredWidth(0);
                tblCascos.getColumnModel().getColumn(i).setMaxWidth(0);
            }
        }

        DefaultTableModel model = (DefaultTableModel) tblCascos.getModel();

        for (int i = 1; i <= Integer.parseInt(datos1[0][0].toString()); i++) {
            model.addRow(new Object[]{"A" + i, "B" + i, "C" + i, "D" + i, "E" + i, "F" + i, "G" + i, "H" + i, "I" + i, "J" + i, "K" + i, "L" + i, "M" + i, "N" + i, "O" + i, "P" + i, "Q" + i, "R" + i, "S" + i, "T" + i, "U" + i, "V" + i, "W" + i, "X" + i, "Y" + i, "Z" + i});
        }

        Object[][] datos = instancias.getSql().getDatosCascos();

        for (Object[] dato : datos) {
            for (int i = 0; i < tblCascos.getRowCount(); i++) {
                for (int j = 0; j < tblCascos.getColumnCount(); j++) {
                    if (dato[1] != null) {
                        if (!dato[1].toString().equals("")) {
                            if (dato[0].equals(tblCascos.getValueAt(i, j))) {
                                tblCascos.setValueAt(dato[0] + ". " + dato[1], i, j);
                            }
                        }
                    }
                }
            }
        }

        instancias = Instancias.getInstancias();
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
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCascos = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        pnlInvisible = new javax.swing.JPanel();
        txtCascos = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        tblCascos.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        tblCascos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCascos.setRowHeight(25);
        tblCascos.getTableHeader().setResizingAllowed(false);
        tblCascos.getTableHeader().setReorderingAllowed(false);
        tblCascos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCascosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblCascos);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        btnGuardar.setBackground(new java.awt.Color(46, 204, 113));
        btnGuardar.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
        btnGuardar.setText("GUARDAR");
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnGuardar.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(btnGuardar)
                .addGap(5, 5, 5))
        );

        txtCascos.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N

        javax.swing.GroupLayout pnlInvisibleLayout = new javax.swing.GroupLayout(pnlInvisible);
        pnlInvisible.setLayout(pnlInvisibleLayout);
        pnlInvisibleLayout.setHorizontalGroup(
            pnlInvisibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInvisibleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtCascos)
                .addContainerGap())
        );
        pnlInvisibleLayout.setVerticalGroup(
            pnlInvisibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInvisibleLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(txtCascos, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 765, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlInvisible, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlInvisible, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 541, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
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

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        String cascos = txtCascos.getText();

        if (cascos.equals("")) {
            metodos.msgAdvertencia(null, "No ha seleccionado ningún lugar para guardar el casco");
            return;
        }

        Object[] espacios = cascos.split(", ");

        for (Object espacio : espacios) {
            instancias.getSql().asignarCasco(espacio.toString(), placa1, "");
        }

        metodos.msgExito(null, "Cascos guardados con exito.");
        instancias.setProcesoCompletoCascos(true);
        this.dispose();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void tblCascosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCascosMouseClicked
        String id = tblCascos.getValueAt(tblCascos.getSelectedRow(), tblCascos.getSelectedColumn()).toString();

        if (id.length() >= 5 && !id.contains("Seleccionado")) {
            metodos.msgAdvertenciaAjustado(null, "Este espacio esta ocupado.");
        } else {
            String[] validador = id.split(", ");
            if (validador.length > 1) {

                tblCascos.setValueAt(validador[0], tblCascos.getSelectedRow(), tblCascos.getSelectedColumn());

                String cascosGuardados[] = txtCascos.getText().split(", ");
                String cascos = "";

                for (int i = 0; i < cascosGuardados.length; i++) {
                    if (!cascosGuardados[i].equals(validador[0])) {
                        cascos = cascos + cascosGuardados[i] + ", ";
                    }
                }

                txtCascos.setText(cascos);
            } else {
                tblCascos.setValueAt(id + ", Seleccionado", tblCascos.getSelectedRow(), tblCascos.getSelectedColumn());
                txtCascos.setText(txtCascos.getText() + id + ", ");
            }
        }

    }//GEN-LAST:event_tblCascosMouseClicked

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
            java.util.logging.Logger.getLogger(dlgCascos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(dlgCascos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(dlgCascos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dlgCascos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                dlgCascos dialog = new dlgCascos(new javax.swing.JFrame(), true, "");
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel pnlInvisible;
    private javax.swing.JTable tblCascos;
    private javax.swing.JTextField txtCascos;
    // End of variables declaration//GEN-END:variables
}
