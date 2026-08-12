package Vista.Restaurante;

import Utilidades.Utilidades;
import clases.Instancias;
import clases.metodosGenerales;
import clases.productos.ndProducto;
import Modelo.Terceros.ModeloContacto;
import Vista.Productos.VistaBuscadorProductos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;

public class VistaProductosCambio extends javax.swing.JDialog {

    private Instancias instancias;
    metodosGenerales metodos = new metodosGenerales();
    String factura;
    String prodPrimero1;
    ndProducto nodo;
    int fila1 = 0;
    DefaultTableModel modelo;

    public VistaProductosCambio(java.awt.Frame parent, boolean modal, String prodPrimero, String codPrincipal, String fila, String actual) {
        super(parent, modal);
        initComponents();

        modelo = (DefaultTableModel) tblListado.getModel();

        try {
            fila1 = Integer.parseInt(fila) + 1;
        } catch (Exception e) {
        }

        metodosGenerales metodos = new metodosGenerales();

        this.setLocationRelativeTo(null);
        this.setTitle("Productos para cambio");

        prodPrimero1 = prodPrimero;
        instancias = Instancias.getInstancias();

        nodo = instancias.getSql().getDatosProducto(codPrincipal, "bdProductos");
        lbProducto.setText(nodo.getDescripcion());

        cargarTabla();

        if (!fila.equals("")) {
            lbSeleccionados.setVisible(false);
            lbRelacionados.setText("Cambiar por:");
            txtNombre.setVisible(false);
            lbProducto.setText(actual);
            txtCod.setVisible(false);
            btnGuardar.setText("CARGAR");

            for (int i = 0; i < tblListado.getRowCount(); i++) {
                if (tblListado.getValueAt(i, 1).equals(actual)) {
                    modelo.removeRow(i);
                    break;
                }
            }
        }

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

        jPopupMenu1 = new javax.swing.JPopupMenu();
        popBorrar = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        txtCampo = new javax.swing.JTextField();
        lbSeleccionados = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblListado = new javax.swing.JTable();
        txtCod = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        lbProducto = new javax.swing.JLabel();
        txtNombre = new javax.swing.JLabel();
        lbRelacionados = new javax.swing.JLabel();

        popBorrar.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        popBorrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/eliminar-cancelar-icono-4935-16.png"))); // NOI18N
        popBorrar.setText("Borrar");
        popBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                popBorrarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(popBorrar);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        txtCampo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCampoKeyReleased(evt);
            }
        });

        lbSeleccionados.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbSeleccionados.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbSeleccionados.setText("Seleccionado");

        tblListado.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        tblListado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Descripcion", "Cant"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblListado.setComponentPopupMenu(jPopupMenu1);
        tblListado.setRowHeight(27);
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
            tblListado.getColumnModel().getColumn(2).setMinWidth(50);
            tblListado.getColumnModel().getColumn(2).setPreferredWidth(90);
            tblListado.getColumnModel().getColumn(2).setMaxWidth(130);
        }

        txtCod.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        txtCod.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtCod.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtCod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodKeyReleased(evt);
            }
        });

        btnGuardar.setBackground(new java.awt.Color(46, 204, 113));
        btnGuardar.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
        btnGuardar.setText("GUARDAR");
        btnGuardar.setToolTipText("Ctrl+I");
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

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        lbProducto.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        lbProducto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbProducto.setText("NOMBRE PRODUCTO");

        txtNombre.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        txtNombre.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        txtNombre.setText("NOMBRE PRODUCTO SELECCIONADO");

        lbRelacionados.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbRelacionados.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbRelacionados.setText("Relacionados");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1)
                            .addComponent(lbProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 507, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(txtCampo, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lbRelacionados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbSeleccionados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtCod, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(181, 181, 181))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbSeleccionados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtCod, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbRelacionados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtCampo, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
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

    private void txtCampoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCampoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            ModeloContacto nodo = instancias.getSql().getDatosTercero(txtCampo.getText());
            tblListado.setValueAt(nodo.getId(), tblListado.getSelectedRow(), 3);
            tblListado.setValueAt(nodo.getNombre(), tblListado.getSelectedRow(), 4);
        }
    }//GEN-LAST:event_txtCampoKeyReleased

    private void tblListadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblListadoKeyReleased

    }//GEN-LAST:event_tblListadoKeyReleased

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (btnGuardar.getText().equals("CARGAR")) {
            BigDecimal cantidad = Utilidades.convertirBigDecimal(tblListado.getValueAt(tblListado.getSelectedRow(), 2).toString());

            instancias.getPreparacion().cambiarDeProducto(fila1 - 1, tblListado.getValueAt(tblListado.getSelectedRow(), 0).toString(),
                    tblListado.getValueAt(tblListado.getSelectedRow(), 1).toString(), cantidad);
        } else {
            if (tblListado.getRowCount() <= 0) {
                metodos.msgError(null, "No ha cargado ningun producto relacionado");
                return;
            }

            for (int i = 0; i < tblListado.getRowCount(); i++) {
                try {
                    Double cant = Double.parseDouble(tblListado.getValueAt(i, 2).toString().replace(",", "."));
                } catch (Exception e) {
                    metodos.msgError(null, "Ingrese cantidad válida de " + tblListado.getValueAt(i, 1));
                    return;
                }
            }

            try {
                instancias.getSql().eliminar_registro("bdRelacionados", " prod = '" + prodPrimero1 + "' and idProducto = '" + nodo.getIdSistema() + "' ");
                for (int i = 0; i < tblListado.getRowCount(); i++) {
                    if (!instancias.getSql().agregarProductosRelacionados(nodo.getIdSistema(), prodPrimero1, txtNombre.getText(),
                            tblListado.getValueAt(i, 0).toString(), tblListado.getValueAt(i, 1).toString(),
                            tblListado.getValueAt(i, 2).toString().replace(",", "."))) {
                        metodos.msgError(null, "Hubo un error al guardar los productos relacionados");
                        return;
                    }
                }
            } catch (Exception e) {
                metodos.msgError(null, "Para relacionar un cambio, el diseño ya debe estar guardado");
                return;
            }

            metodos.msgExito(null, "Productos guardados con exito!");
        }

        this.dispose();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnGuardarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnGuardarKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGuardarKeyReleased

    private void txtCodKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String codigo = txtCod.getText();
            cargarProducto(codigo.replace("'", "//"));
        }
    }//GEN-LAST:event_txtCodKeyReleased

    private void popBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popBorrarActionPerformed
        if (tblListado.getSelectedRow() > -1) {
            int fila = tblListado.getSelectedRow();

            DefaultTableModel modelo = (DefaultTableModel) tblListado.getModel();
            modelo.removeRow(fila);
        } else {
            metodos.msgAdvertencia(null, "Seleccione un producto");
        }
    }//GEN-LAST:event_popBorrarActionPerformed

    private void tblListadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblListadoMouseClicked
        if (evt.getClickCount() > 1 && btnGuardar.getText().equals("CARGAR")) {
            btnGuardarActionPerformed(null);
        }
    }//GEN-LAST:event_tblListadoMouseClicked

    public void cargarProducto(String codigo) {

        for (int j = 0; j < tblListado.getRowCount(); j++) {
            if (codigo.equalsIgnoreCase((String) tblListado.getValueAt(j, 0))) {
                metodos.msgAdvertencia(null, "El producto ya esta cargado");
                tblListado.setColumnSelectionInterval(3, 3);
                tblListado.setRowSelectionInterval(j, j);
                tblListado.editCellAt(j, 3);
                tblListado.transferFocus();
                txtCod.setText("");
                return;
            }
        }

        ndProducto nodo = null;

        String codigoProd = "";
        if (codigo.equals("")) {
            codigoProd = "";
        } else {
            Object[][] listado = instancias.getSql().getCodigosRelacionados(codigo, " where codigo");
            if (listado.length > 0) {
                codigo = listado[0][0].toString();
            }

            nodo = instancias.getSql().getDatosProducto(codigo, "bdProductos");
            if (nodo.getIdSistema() != null) {
                codigoProd = nodo.getIdSistema();
            }
        }

        if (!codigoProd.equals("")) {
            DefaultTableModel model = (DefaultTableModel) tblListado.getModel();
            model.addRow(new Object[]{nodo.getIdSistema(), nodo.getDescripcion()});
            txtCod.setText("");
            return;
        }
        ventanaProductos(codigo);
    }

    public void ventanaProductos(String codigo) {
        VistaBuscadorProductos buscar = new VistaBuscadorProductos(instancias.getMenu(), rootPaneCheckingEnabled, false, "");
        buscar.setLocationRelativeTo(null);
        instancias.setBusProductos(buscar);
        instancias.setCampoActual(txtCod);
        txtCod.requestFocus();
        buscar.noEncontrado(codigo);
        buscar.show();
    }

    public void cargarTabla() {
        ndProducto nodo1 = instancias.getSql().getDatosProducto(prodPrimero1, "bdProductos");

        txtNombre.setText(nodo.getDescripcion());

        Object[][] listado = instancias.getSql().getProductosRelacionados(nodo1.getIdSistema(), nodo.getIdSistema());

        if (listado.length > 0) {

            try {
                if (fila1 > 0) {
                    ndProducto nodo = instancias.getSql().getDatosProducto(listado[0][1].toString(), "bdProductos");
                    modelo.addRow(new Object[]{listado[0][1], nodo.getDescripcion()});
                }
            } catch (Exception e) {
            }

            for (int i = 0; i < listado.length; i++) {
                modelo.addRow(new Object[]{listado[i][3], listado[i][4], listado[i][5]});
            }
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
            java.util.logging.Logger.getLogger(VistaProductosCambio.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaProductosCambio.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaProductosCambio.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaProductosCambio.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                VistaProductosCambio dialog = new VistaProductosCambio(new javax.swing.JFrame(), true, null, "", "", "");
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
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbProducto;
    private javax.swing.JLabel lbRelacionados;
    private javax.swing.JLabel lbSeleccionados;
    private javax.swing.JMenuItem popBorrar;
    private javax.swing.JTable tblListado;
    private javax.swing.JTextField txtCampo;
    private javax.swing.JTextField txtCod;
    private javax.swing.JLabel txtNombre;
    // End of variables declaration//GEN-END:variables
}
