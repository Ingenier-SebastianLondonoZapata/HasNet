package Vista.Tesoreria;

import clases.Instancias;
import clases.metodosGenerales;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import javax.swing.JComponent;
import javax.swing.table.DefaultTableModel;

public class vistaCodigosEgresos extends javax.swing.JInternalFrame {

    metodosGenerales metodos = new metodosGenerales();
    private Instancias instancias;
    private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension dimBarra = null;
    private String codigo;
    DefaultTableModel modelo;

    public vistaCodigosEgresos() {

        initComponents();

        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
        dimBarra = Barra.getPreferredSize();
        Barra.setSize(0, 0);
        Barra.setPreferredSize(new Dimension(0, 0));
        setBorder(null);
        repaint();

        instancias = Instancias.getInstancias();
        modelo = (DefaultTableModel) tblConceptos.getModel();

        actualizarTabla();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblConceptos = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        lbTelefono1 = new javax.swing.JLabel();
        lbTelefono = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        txtConcepto = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        lbTelefono2 = new javax.swing.JLabel();
        btnEliminar = new javax.swing.JButton();

        setTitle("Codigos egresos...");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        tblConceptos.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        tblConceptos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Concepto", "Codigo Interno", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblConceptos.setRowHeight(24);
        tblConceptos.getTableHeader().setReorderingAllowed(false);
        tblConceptos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblConceptosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblConceptos);
        if (tblConceptos.getColumnModel().getColumnCount() > 0) {
            tblConceptos.getColumnModel().getColumn(0).setMinWidth(40);
            tblConceptos.getColumnModel().getColumn(0).setPreferredWidth(70);
            tblConceptos.getColumnModel().getColumn(0).setMaxWidth(150);
            tblConceptos.getColumnModel().getColumn(2).setMinWidth(0);
            tblConceptos.getColumnModel().getColumn(2).setPreferredWidth(0);
            tblConceptos.getColumnModel().getColumn(2).setMaxWidth(0);
            tblConceptos.getColumnModel().getColumn(3).setMinWidth(50);
            tblConceptos.getColumnModel().getColumn(3).setPreferredWidth(70);
            tblConceptos.getColumnModel().getColumn(3).setMaxWidth(100);
        }

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14))); // NOI18N

        lbTelefono1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lbTelefono1.setText("Descripción concepto:");

        lbTelefono.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lbTelefono.setText("Codigo concepto:");

        txtCodigo.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoKeyReleased(evt);
            }
        });

        txtConcepto.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtConcepto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtConceptoKeyTyped(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(93, 173, 226));
        jButton2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jButton2.setText("MODIFICAR");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.setEnabled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        btnGuardar.setBackground(new java.awt.Color(46, 204, 113));
        btnGuardar.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnGuardar.setText("AGREGAR");
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        lbTelefono2.setFont(new java.awt.Font("Arial", 1, 28)); // NOI18N
        lbTelefono2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTelefono2.setText("CONCEPTOS");

        btnEliminar.setBackground(new java.awt.Color(241, 148, 138));
        btnEliminar.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnEliminar.setText("INACTIVAR");
        btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminar.setEnabled(false);
        btnEliminar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnEliminar.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 57, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(69, 69, 69))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lbTelefono2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(lbTelefono1, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                                    .addComponent(lbTelefono, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE)
                                    .addComponent(txtConcepto))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lbTelefono2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTelefono)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTelefono1)
                    .addComponent(txtConcepto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 941, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (btnGuardar.getText().equals("CANCELAR")) {
            btnGuardar.setText("AGREGAR");
            txtConcepto.setText("");
            txtCodigo.setText("");
            jButton2.setEnabled(false);
            btnEliminar.setEnabled(false);
        } else {

            if (txtConcepto.getText().equals("")) {
                metodos.msgAdvertenciaAjustado(this, "Debe ingresar un concepto");
                return;
            }

            if (txtCodigo.getText().equals("")) {
                metodos.msgAdvertenciaAjustado(this, "Debe ingresar un codigo");
                return;
            }

            Object[][] datos = instancias.getSql().getCodsEgresos();

            String codigo = txtCodigo.getText();
            String concepto = txtConcepto.getText();

            for (int i = 0; i < datos.length; i++) {
                if (datos[i][1].equals(concepto)) {
                    metodos.msgAdvertenciaAjustado(this, "El concepto ya existe");
                    txtConcepto.setText("");
                    return;
                }

                if (datos[i][0].equals(codigo)) {
                    metodos.msgAdvertenciaAjustado(this, "El codigo ya existe");
                    txtCodigo.setText("");
                    return;
                }
            }

            if (metodos.msgPregunta(this, "¿Desea continuar?") == 0) {

                if (!instancias.getSql().agregarCodEgreso(txtConcepto.getText(), txtCodigo.getText())) {
                    metodos.msgError(this, "Error al guardar el concepto");
                    return;
                }
                
                metodos.msgExito(this, "Concepto registrado con exito");
                actualizarTabla();
                actualizar();
                txtCodigo.setText("");
                limpiar();
            }
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (!txtCodigo.getText().equals("") && codigo != null) {
            if (!instancias.getSql().modificarCodEgreso(new Object[]{codigo, txtConcepto.getText(), txtCodigo.getText()})) {
                metodos.msgError(this, "Error al modificar el concepto");
                return;
            }

            txtCodigo.setText("");
            limpiar();
            actualizarTabla();
            actualizar();
            metodos.msgExito(this, "Concepto modificado con exito");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtConceptoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtConceptoKeyTyped

    }//GEN-LAST:event_txtConceptoKeyTyped

    private void txtCodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!txtCodigo.getText().equals("")) {
                cargarCodigo(txtCodigo.getText());
            }
        } else {
            //            limpiar();
        }
    }//GEN-LAST:event_txtCodigoKeyReleased

    private void tblConceptosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblConceptosMouseClicked
        int f = tblConceptos.getSelectedRow();
        if (f != -1) {
            if (evt.getClickCount() == 2) {

                if (tblConceptos.getValueAt(f, 3).equals("INACTIVO")) {
                    btnEliminar.setText("ACTIVAR");
                } else {
                    btnEliminar.setText("INACTIVAR");
                }

                if (tblConceptos.getValueAt(f, 1).equals("PAGOS PROVEEDORES")) {
                    jButton2.setEnabled(false);
                    btnEliminar.setEnabled(false);
                } else {
                    jButton2.setEnabled(true);
                    btnEliminar.setEnabled(true);
                }

                txtCodigo.setText(tblConceptos.getValueAt(f, 0).toString());
                txtConcepto.setText(tblConceptos.getValueAt(f, 1).toString());
                this.codigo = tblConceptos.getValueAt(f, 2).toString();
                btnGuardar.setText("CANCELAR");

            }
        }
    }//GEN-LAST:event_tblConceptosMouseClicked

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        if (btnEliminar.getText().equalsIgnoreCase("ACTIVAR")) {
            if (metodos.msgPregunta(this, "¿Activar este concepto?") == 0) {
                instancias.getSql().activarRegistro1(this.codigo, "codsEgresos");
                metodos.msgExito(this, "Concepto activado con éxito");
                btnGuardarActionPerformed(evt);
                actualizarTabla();
                return;
            }
        }

        if (metodos.msgPregunta(this, "¿Inactivar este concepto?") == 0) {
            instancias.getSql().inactivarRegistro1(this.codigo, "codsEgresos");
            metodos.msgExito(this, "Concepto inactivado con éxito");
            btnGuardarActionPerformed(evt);
        }

        actualizarTabla();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void actualizarTabla() {
        String columNames[] = {
            "Codigo", "Concepto", "Codigo Interno", "Estado"
        };

        modelo.setDataVector(instancias.getSql().getCodsEgresos(), columNames);

        if (tblConceptos.getColumnModel().getColumnCount() > 0) {
            tblConceptos.getColumnModel().getColumn(0).setMinWidth(75);
            tblConceptos.getColumnModel().getColumn(0).setPreferredWidth(105);
            tblConceptos.getColumnModel().getColumn(0).setMaxWidth(150);
            tblConceptos.getColumnModel().getColumn(2).setMinWidth(0);
            tblConceptos.getColumnModel().getColumn(2).setPreferredWidth(0);
            tblConceptos.getColumnModel().getColumn(2).setMaxWidth(0);
            tblConceptos.getColumnModel().getColumn(3).setMinWidth(50);
            tblConceptos.getColumnModel().getColumn(3).setPreferredWidth(80);
            tblConceptos.getColumnModel().getColumn(3).setMaxWidth(110);
        }

        for (int i = 0; i < tblConceptos.getRowCount(); i++) {
            if (tblConceptos.getValueAt(i, 3).equals("0")) {
                tblConceptos.setValueAt("ACTIVO", i, 3);
            } else {
                tblConceptos.setValueAt("INACTIVO", i, 3);
            }
        }

    }

    private void actualizar() {
//        instancias.getEgresos().actualizarConceptos();
        instancias.getRepEgresos().actualizarConceptos();
    }

    private void cargarCodigo(String codigo) {
        for (int i = 0; i < tblConceptos.getRowCount(); i++) {
            if (tblConceptos.getValueAt(i, 0).equals(codigo)) {
                txtConcepto.setText(tblConceptos.getValueAt(i, 1).toString());
                this.codigo = tblConceptos.getValueAt(i, 2).toString();
//                btnGuardar.setEnabled(false);
                jButton2.setEnabled(true);
                return;
            }
        }
        limpiar();
        metodos.msgAdvertenciaAjustado(this, "El codigo no existe");
    }

    private void limpiar() {
        this.codigo = null;
        txtConcepto.setText("");
        btnGuardar.setEnabled(true);
        jButton2.setEnabled(false);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbTelefono;
    private javax.swing.JLabel lbTelefono1;
    private javax.swing.JLabel lbTelefono2;
    private javax.swing.JTable tblConceptos;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtConcepto;
    // End of variables declaration//GEN-END:variables
}
