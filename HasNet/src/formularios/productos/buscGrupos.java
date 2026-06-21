package formularios.productos;

import Vista.Productos.ingreso;
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

public class buscGrupos extends javax.swing.JDialog {

    DefaultTableModel modelo;
    TableRowSorter modeloOrdenado;
    Instancias instancias;
    private String opc = "";
    private String desde1 = "";
    private VistaFactura factura;
    private ingreso ingreso;

    public buscGrupos(java.awt.Frame parent, boolean modal, String desde) {
        super(parent, modal);
        initComponents();

        desde1 = desde;
        instancias = Instancias.getInstancias();

        tblRegistros.setModel(instancias.getSql().getRegistrosGrupos2());
        modelo = (DefaultTableModel) tblRegistros.getModel();

        modeloOrdenado = new TableRowSorter<>(modelo);
        tblRegistros.setRowSorter(modeloOrdenado);

        txtId.requestFocus();

        filtro.add(rd1);
        filtro.add(rd2);

        try {
            tblRegistros.getSelectionModel().setSelectionInterval(0, 0);
        } catch (ArrayIndexOutOfBoundsException exp) {
        }

        if (tblRegistros.getColumnModel().getColumnCount() > 0) {
            tblRegistros.getColumnModel().getColumn(0).setMinWidth(90);
            tblRegistros.getColumnModel().getColumn(0).setPreferredWidth(90);
            tblRegistros.getColumnModel().getColumn(0).setMaxWidth(90);
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

    public VistaFactura getFactura() {
        return factura;
    }

    public void setFactura(VistaFactura factura) {
        this.factura = factura;
    }

    public ingreso getIngreso() {
        return ingreso;
    }

    public void setIngreso(ingreso ingreso) {
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
        rd1 = new javax.swing.JRadioButton();
        rd2 = new javax.swing.JRadioButton();
        txtId = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblRegistros = new javax.swing.JTable();
        btnSeleccionar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("GRUPOS");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        rd1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        rd1.setSelected(true);
        rd1.setText("Descripción");

        rd2.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        rd2.setText("Codigo");
        rd2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rd2ActionPerformed(evt);
            }
        });

        txtId.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
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

        tblRegistros.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        tblRegistros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Grupo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
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
        if (tblRegistros.getColumnModel().getColumnCount() > 0) {
            tblRegistros.getColumnModel().getColumn(0).setMinWidth(90);
            tblRegistros.getColumnModel().getColumn(0).setPreferredWidth(90);
            tblRegistros.getColumnModel().getColumn(0).setMaxWidth(90);
        }

        btnSeleccionar.setBackground(new java.awt.Color(204, 204, 204));
        btnSeleccionar.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        btnSeleccionar.setText("SELECCIONAR");
        btnSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtId)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(rd1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rd2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSeleccionar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rd1)
                    .addComponent(rd2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSeleccionar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                tblRegistros.scrollRectToVisible(tblRegistros.getCellRect(tblRegistros.getSelectedRow(), 0, true));

            } catch (ArrayIndexOutOfBoundsException exp) {
            }
            return;
        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnSeleccionarActionPerformed(null);
            return;
        }

        int i = 0;

        if (rd1.isSelected()) {
            i = 1;
        }

        modeloOrdenado.setRowFilter(RowFilter.regexFilter("(?i)" + txtId.getText(), i));

        try {
            tblRegistros.getSelectionModel().setSelectionInterval(0, 0);
        } catch (ArrayIndexOutOfBoundsException exp) {
        }
    }//GEN-LAST:event_txtIdKeyReleased

    private void tblRegistrosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblRegistrosMouseClicked
        if (evt.getClickCount() > 1) {
            btnSeleccionarActionPerformed(null);
        }
    }//GEN-LAST:event_tblRegistrosMouseClicked

    private void tblRegistrosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblRegistrosKeyReleased

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (tblRegistros.getRowCount() > 1) {
                tblRegistros.getSelectionModel().setSelectionInterval(tblRegistros.getSelectionModel().getMinSelectionIndex() - 1, tblRegistros.getSelectionModel().getMinSelectionIndex() - 1);
            }
            btnSeleccionarActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            tblRegistros.scrollRectToVisible(tblRegistros.getCellRect(tblRegistros.getSelectedRow(), 0, true));
        }
    }//GEN-LAST:event_tblRegistrosKeyReleased

    private void txtIdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtIdMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdMouseClicked

    private void rd2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rd2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rd2ActionPerformed

    private void btnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarActionPerformed
        instancias.setValorCampo((String) tblRegistros.getValueAt(tblRegistros.getSelectedRow(), 0));
        this.dispose();

        if (desde1.equals("")) {
            metodosGenerales.presionarEnter(instancias.getCampoActual());
        }
    }//GEN-LAST:event_btnSeleccionarActionPerformed

    public void noEncontrado(String codigo) {
        txtId.setText(codigo);
        modeloOrdenado.setRowFilter(RowFilter.regexFilter("(?i)" + codigo, 1));
        try {
            tblRegistros.getSelectionModel().setSelectionInterval(0, 0);
        } catch (ArrayIndexOutOfBoundsException exp) {
        }
    }

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                buscGrupos dialog = new buscGrupos(new javax.swing.JFrame(), true, "");
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
    private javax.swing.JButton btnSeleccionar;
    private javax.swing.ButtonGroup filtro;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rd1;
    private javax.swing.JRadioButton rd2;
    private javax.swing.JTable tblRegistros;
    private javax.swing.JTextField txtId;
    // End of variables declaration//GEN-END:variables
}
