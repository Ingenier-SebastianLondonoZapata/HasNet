package formularios;

import clases.Instancias;
import clases.metodosGenerales;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class infBuscadorCliente extends javax.swing.JFrame {

    Instancias instancias;
    metodosGenerales metodos;
    DefaultTableModel modelo;
    private infBuscadorCliente actual = this;
    private Integer opc = null;
    TableRowSorter modeloOrdenado;

    public infBuscadorCliente() {
        initComponents();
        modelo = (DefaultTableModel) jTable1.getModel();
        this.setLocationRelativeTo(null);

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                switch (jTable1.getToolTipText()) {
                    case "traslado":
                        instancias.getPrestamos().limpiarTraslado(1);
                        break;
                }
                actual.dispose();
            }
        });
        metodos = new metodosGenerales();
        txtId.requestFocus();
    }

    public void setOpc(int opc) {
        this.opc = opc;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTextField2 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        txtId = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jTextField2.setEditable(false);
        jTextField2.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel2.setText("Cliente:");

        jTable1.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Fecha", "Usuario"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton1.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jButton1.setText("Seleccionar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        txtId.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
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

        jLabel3.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel3.setText("Placa:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        if (jTable1.getSelectedRow() >= 0) {
            switch (jTable1.getToolTipText()) {
//                case "traslado":
//                    this.dispose();
//                    instancias.getTraslados().cargarTraslado(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString().split("-")[1]);
//                    break;
                case "oServicio":
                    instancias.getOrdenServicio().cargarOServicio((jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString().replace("OSERV-", "")), "OSERV-");
                    this.dispose();
                    break;
                case "oServicioPlaca":
                    instancias.getOrdenServicio().cargarOServicio((jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString().replace("OSERV-", "")), "OSERV-");
                    this.dispose();
                    break;
                case "pedido":
                    instancias.getPedido().cargarOServicio((jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString().replace("PEDIDO-", "")), "PEDIDO-");
                    this.dispose();
                    break;
            }
        } else {
            this.dispose();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtIdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtIdMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdMouseClicked

    private void txtIdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdKeyReleased
        if ((evt.getKeyCode() == 40 || evt.getKeyCode() == 38)) {
            int aumento = -1;

            if (evt.getKeyCode() == 40) {
                aumento = 1;
            }

            int fila = jTable1.getSelectionModel().getMinSelectionIndex() + aumento;
            try {

                if (fila == jTable1.getRowCount() && aumento == 1) {
                    fila = 0;
                }

                jTable1.getSelectionModel().setSelectionInterval(fila, fila);
                jTable1.scrollRectToVisible(jTable1.getCellRect(jTable1.getSelectedRow(), 0, true));

            } catch (ArrayIndexOutOfBoundsException exp) {
            }
            return;
        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jButton1ActionPerformed(null);
            return;
        }

        int i = 1;

        modeloOrdenado.setRowFilter(RowFilter.regexFilter("(?i)" + txtId.getText(), i));

        try {
            jTable1.getSelectionModel().setSelectionInterval(0, 0);
        } catch (ArrayIndexOutOfBoundsException exp) {
        }
    }//GEN-LAST:event_txtIdKeyReleased

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        if (evt.getClickCount() > 1) {
            jButton1ActionPerformed(null);
        }
    }//GEN-LAST:event_jTable1MouseClicked

    public void cargarRegistros(String paciente, String examen, Instancias instancias) {

        this.instancias = instancias;
        jTextField2.setText(instancias.getSql().getDatosTercero(paciente).getNombre());
        Object[][] datos = null;

        switch (examen) {
            case "traslado":
                datos = instancias.getSql().getRegistrosTrasladosBuscador(paciente);
                if (datos.length == 0) {
                    this.dispose();
                    JOptionPane.showMessageDialog(null, "Esta persona no tiene traslados asociados");
                    instancias.getPrestamos().limpiarTraslado(1);
                    return;
                } else {
                    this.show();
                }
                break;
            case "oServicio":
                datos = instancias.getSql().getRegistrosOServicioBuscador(paciente);
                break;
            case "oServicioPlaca":
                datos = instancias.getSql().getRegistrosOServicioBuscadorPlaca(paciente);

                System.out.println(datos.length);
                if (datos.length == 0) {
                    this.dispose();
                } else {

                    modelo = new DefaultTableModel(datos, new Object[]{"Codigo", "Fecha", "Placa", "Usuario"}) {
                        public boolean isCellEditable(int row, int column) {
                            return false;
                        }
                    };

                    jTable1.setModel(modelo);
                    this.show();
                    jTable1.setToolTipText(examen);
                    for (int i = 0; i < jTable1.getRowCount(); i++) {
                        jTable1.setValueAt(metodos.fecha(jTable1.getValueAt(i, 1).toString()), i, 1);
                    }
                    return;
                }

                break;
            case "pedido":
                datos = instancias.getSql().getRegistrosPedidosBuscador(paciente);
                break;
        }

        for (Object[] dato : datos) {
//            dato[1] = metodos.fecha(dato[1].toString());
            modelo.addRow(dato);
        }
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            jTable1.setValueAt(metodos.fecha(jTable1.getValueAt(i, 1).toString()), i, 1);
        }
        modelo = (DefaultTableModel) jTable1.getModel();

        modeloOrdenado = new TableRowSorter<>(modelo);
        jTable1.setRowSorter(modeloOrdenado);
        jTable1.setToolTipText(examen);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField txtId;
    // End of variables declaration//GEN-END:variables
}
