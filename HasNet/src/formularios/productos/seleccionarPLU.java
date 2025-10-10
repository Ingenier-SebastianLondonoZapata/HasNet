package formularios.productos;

import clases.Instancias;
import clases.big;
import clases.cambiarColorTabla;
import clases.metodosGenerales;
import clases.productos.ndProducto;
import Vista.Cartera.vistaNotaDebito;
import Vista.Ventas.vistaFactura;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;

public class seleccionarPLU extends javax.swing.JDialog {

    private Instancias instancias;
    private String codigo;
    private String opc, baseUtilizada;
    private vistaFactura factura;
    private vistaNotaDebito notaDebito;

    public vistaFactura getFactura() {
        return factura;
    }

    public void setFactura(vistaFactura factura) {
        this.factura = factura;
    }

    public vistaNotaDebito getNotaDebito() {
        return notaDebito;
    }

    public void setNotaDebito(vistaNotaDebito notaDebito) {
        this.notaDebito = notaDebito;
    }

    public seleccionarPLU(java.awt.Frame parent, boolean modal, String base) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        baseUtilizada = base;
        tblRegistros.setDefaultRenderer(Object.class, new cambiarColorTabla(1, 0));
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

    public String getOpc() {
        return opc;
    }

    public void setOpc(String opc) {
        this.opc = opc;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblRegistros = new javax.swing.JTable();
        btnEste = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Seleccionar PLU");

        tblRegistros.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        tblRegistros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "PLU", "Descripción", "Referencia", "Ubicación", "Cant", "Inv", "Ingresar"
            }
        ));
        tblRegistros.setCellSelectionEnabled(true);
        tblRegistros.setRowHeight(24);
        tblRegistros.setSelectionBackground(new java.awt.Color(0, 0, 0));
        tblRegistros.getTableHeader().setReorderingAllowed(false);
        tblRegistros.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblRegistrosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblRegistros);
        if (tblRegistros.getColumnModel().getColumnCount() > 0) {
            tblRegistros.getColumnModel().getColumn(0).setMinWidth(100);
            tblRegistros.getColumnModel().getColumn(0).setPreferredWidth(120);
            tblRegistros.getColumnModel().getColumn(0).setMaxWidth(150);
        }

        btnEste.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnEste.setText("Ingresar");
        btnEste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEsteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnEste, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 886, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnEste)
                .addGap(10, 10, 10))
        );

        getAccessibleContext().setAccessibleName("PLU");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEsteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEsteActionPerformed
//        try {
        switch (opc) {
            case "NotaDebito":
                notaDebito.setPlu(false);
                for (int i = 0; i < tblRegistros.getRowCount(); i++) {
                    if (big.getMoneda(tblRegistros.getValueAt(i, 6).toString()).compareTo(BigDecimal.ZERO) == 1) {
                        notaDebito.cargarProducto(codigo, (((String) tblRegistros.getValueAt(i, 6))), (int) tblRegistros.getValueAt(i, 7), "",
                                "", "", false, "", "", "", "", "");
                    }
                }
                this.dispose();
                break;
            case "factura":
                factura.setPlu(false);
                for (int i = 0; i < tblRegistros.getRowCount(); i++) {
                    if (big.getMoneda(tblRegistros.getValueAt(i, 6).toString()).compareTo(BigDecimal.ZERO) == 1) {
                        factura.cargarProducto(codigo, (((String) tblRegistros.getValueAt(i, 6))), (int) tblRegistros.getValueAt(i, 7), "",
                                "", "", false, "", "", "", "", "");
                    }
                }
                this.dispose();
                break;
            case "ordenCompra":
                instancias.getOrdenCompra().setPlu(false);
                for (int i = 0; i < tblRegistros.getRowCount(); i++) {
                    if (big.getMoneda(tblRegistros.getValueAt(i, 6).toString()).compareTo(BigDecimal.ZERO) == 1) {
                        instancias.getOrdenCompra().cargarProducto(codigo, (((String) tblRegistros.getValueAt(i, 6))),
                                (int) tblRegistros.getValueAt(i, 7), "");
                    }
                }
                this.dispose();
                break;
            case "ingreso":
                instancias.getIngresos().setPlu(false);
                for (int i = 0; i < tblRegistros.getRowCount(); i++) {
                    if (big.getMoneda(tblRegistros.getValueAt(i, 6).toString()).compareTo(BigDecimal.ZERO) == 1) {
                        instancias.getIngresos().cargarProducto(codigo, (((String) tblRegistros.getValueAt(i, 6))),
                                (int) tblRegistros.getValueAt(i, 7), "");
                    }
                }
                this.dispose();
                break;
            case "diseño":
                instancias.getCosteo().setPlu(false);
                for (int i = 0; i < tblRegistros.getRowCount(); i++) {
                    if (big.getMoneda(tblRegistros.getValueAt(i, 6).toString()).compareTo(BigDecimal.ZERO) == 1) {
                        instancias.getCosteo().cargarProducto(codigo, (((String) tblRegistros.getValueAt(i, 6))), (int) tblRegistros.getValueAt(i, 7));
                        break;
                    }
                }
                this.dispose();
                break;
            case "ajuste":
                instancias.getuInt().setPlu(false);
                for (int i = 0; i < tblRegistros.getRowCount(); i++) {
                    if (big.getMoneda(tblRegistros.getValueAt(i, 6).toString()).compareTo(BigDecimal.ZERO) == 1) {
                        instancias.getuInt().cargarProducto(codigo, (((String) tblRegistros.getValueAt(i, 6))), (int) tblRegistros.getValueAt(i, 7), "", "", "", "", "", "", "");
                        break;
                    }
                }
                this.dispose();
                break;
            case "trasladoInterno":
                instancias.getTrasladosInternos().setPlu(false);
                for (int i = 0; i < tblRegistros.getRowCount(); i++) {
                    if (big.getMoneda(tblRegistros.getValueAt(i, 6).toString()).compareTo(BigDecimal.ZERO) == 1) {
                        instancias.getTrasladosInternos().cargarProducto(codigo, (((String) tblRegistros.getValueAt(i, 6))), (int) tblRegistros.getValueAt(i, 7), "", "", "",
                                "", "", "", "");
                        break;
                    }
                }
                this.dispose();
                break;
            case "traslado":
                instancias.getPrestamos().setPlu(false);
                for (int i = 0; i < tblRegistros.getRowCount(); i++) {
                    if (big.getMoneda(tblRegistros.getValueAt(i, 6).toString()).compareTo(BigDecimal.ZERO) == 1) {
                        instancias.getPrestamos().cargarProducto(codigo, (((String) tblRegistros.getValueAt(i, 6))), (int) tblRegistros.getValueAt(i, 7),
                                "", "", "", "", "", "", "");
                        break;
                    }
                }
                this.dispose();
                break;
            default:
                instancias.setValorCampo((String) tblRegistros.getValueAt(tblRegistros.getSelectedRow(), 0));
                this.dispose();
                metodosGenerales.presionarEnter(instancias.getCampoActual());
                break;
        }

//        } catch (Exception e) {
//
//            instancias.setValorCampo((String) tblRegistros.getValueAt(tblRegistros.getSelectedRow(), 0));
//            this.dispose();
//            metodosGenerales.presionarEnter(instancias.getCampoActual());
//        }

    }//GEN-LAST:event_btnEsteActionPerformed

    private void tblRegistrosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblRegistrosMouseClicked
        if (evt.getClickCount() == 2) {
            tblRegistros.setValueAt("1", tblRegistros.getSelectedRow(), 6);
            btnEsteActionPerformed(null);
        }
    }//GEN-LAST:event_tblRegistrosMouseClicked

    public void setInstancias(Instancias instancias, String cod) {
        this.instancias = instancias;
        this.codigo = cod;

        ndProducto producto = instancias.getSql().getDatosProducto(codigo, baseUtilizada);

        String inv = producto.getFisicoInventario();
        inv = inv.replace(".", ",");

        int cant = 1;
        if (producto.isPlu2()) {
            cant++;
        }
        if (producto.isPlu3()) {
            cant++;
        }
        if (producto.isPlu4()) {
            cant++;
        }
        if (producto.getPlu5()) {
            cant++;
        }
        if (producto.getPlu6()) {
            cant++;
        }
        if (producto.getPlu7()) {
            cant++;
        }
        if (producto.getPlu8()) {
            cant++;
        }

        Object[][] data = new Object[cant][8];
        Object[] columnNames = {"PLU", "Descripción", "Referencia", "Ubicación", "Cant", "Inv", "Ingresar", "plu"};
        cant = 0;
        data[cant][0] = producto.getCodigo() + "-1";
        data[cant][1] = producto.getDescripcion();
        data[cant][3] = producto.getUbicacion1();
        data[cant][4] = 1;
        data[cant][5] = inv;
        data[cant][6] = 0;
        data[cant][7] = 1;
        cant++;

        if (producto.isPlu2()) {
            data[cant][0] = producto.getCodigo2();
            data[cant][1] = producto.getDescripcion2();
            data[cant][3] = producto.getUbicacion2();
            data[cant][4] = producto.getCantidad2();
            data[cant][5] = big.getMoneda(inv).divide(big.getBigDecimal(producto.getCantidad2()), 2, RoundingMode.HALF_DOWN);
            data[cant][6] = 0;
            data[cant][7] = 2;
            cant++;
        }

        if (producto.isPlu3()) {
            data[cant][0] = producto.getCodigo3();
            data[cant][1] = producto.getDescripcion3();
            data[cant][3] = producto.getUbicacion3();
            data[cant][4] = producto.getCantidad3();
            data[cant][5] = big.getMoneda(inv).divide(big.getBigDecimal(producto.getCantidad3()), 2, RoundingMode.HALF_DOWN);
            data[cant][6] = 0;
            data[cant][7] = 3;
            cant++;
        }

        if (producto.isPlu4()) {
            data[cant][0] = producto.getCodigo4();
            data[cant][1] = producto.getDescripcion4();
            data[cant][3] = producto.getUbicacion4();
            data[cant][4] = producto.getCantidad4();
            data[cant][5] = big.getMoneda(inv).divide(big.getBigDecimal(producto.getCantidad4()), 2, RoundingMode.HALF_DOWN);
            data[cant][6] = 0;
            data[cant][7] = 4;
            cant++;
        }

        if (producto.getPlu5()) {
            data[cant][0] = producto.getCodigo5();
            data[cant][1] = producto.getDescripcion5();
            data[cant][3] = producto.getUbicacion5();
            data[cant][4] = producto.getCantidad5();
            data[cant][5] = big.getMoneda(inv).divide(big.getBigDecimal(producto.getCantidad5()), 2, RoundingMode.HALF_DOWN);
            data[cant][6] = 0;
            data[cant][7] = 5;
            cant++;
        }

        if (producto.getPlu6()) {
            data[cant][0] = producto.getCodigo6();
            data[cant][1] = producto.getDescripcion6();
            data[cant][3] = producto.getUbicacion6();
            data[cant][4] = producto.getCantidad6();
            data[cant][5] = big.getMoneda(inv).divide(big.getBigDecimal(producto.getCantidad6()), 2, RoundingMode.HALF_DOWN);
            data[cant][6] = 0;
            data[cant][7] = 6;
            cant++;
        }

        if (producto.getPlu7()) {
            data[cant][0] = producto.getCodigo7();
            data[cant][1] = producto.getDescripcion7();
            data[cant][3] = producto.getUbicacion7();
            data[cant][4] = producto.getCantidad7();
            data[cant][5] = big.getMoneda(inv).divide(big.getBigDecimal(producto.getCantidad7()), 2, RoundingMode.HALF_DOWN);
            data[cant][6] = 0;
            data[cant][7] = 7;
            cant++;
        }

        if (producto.getPlu8()) {
            data[cant][0] = producto.getCodigo8();
            data[cant][1] = producto.getDescripcion8();
            data[cant][3] = producto.getUbicacion8();
            data[cant][4] = producto.getCantidad8();
            data[cant][5] = big.getMoneda(inv).divide(big.getBigDecimal(producto.getCantidad8()), 2, RoundingMode.HALF_DOWN);
            data[cant][6] = 0;
            data[cant][7] = 8;
            cant++;
        }

        for (int i = 0; i < 4; i++) {
            try {
                data[i][2] = producto.getReferencia();
            } catch (Exception e) {
                break;
            }
        }

        tblRegistros.setModel(new DefaultTableModel(data, columnNames) {
            public boolean isCellEditable(int row, int column) {
                if (column == 6) {
                    return true;
                }
                return false;
            }
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        });

        if (tblRegistros.getColumnModel().getColumnCount() > 0) {

            tblRegistros.getColumnModel().getColumn(0).setMinWidth(100);
            tblRegistros.getColumnModel().getColumn(0).setPreferredWidth(120);
            tblRegistros.getColumnModel().getColumn(0).setMaxWidth(150);

//            tblRegistros.getColumnModel().getColumn(1).setMinWidth(200);
            tblRegistros.getColumnModel().getColumn(1).setPreferredWidth(200);
//            tblRegistros.getColumnModel().getColumn(1).setMaxWidth(200);

            tblRegistros.getColumnModel().getColumn(4).setPreferredWidth(60);
            tblRegistros.getColumnModel().getColumn(4).setMaxWidth(60);

            tblRegistros.getColumnModel().getColumn(5).setMinWidth(60);
            tblRegistros.getColumnModel().getColumn(5).setPreferredWidth(80);
            tblRegistros.getColumnModel().getColumn(5).setMaxWidth(100);

            tblRegistros.getColumnModel().getColumn(5).setMinWidth(64);
            tblRegistros.getColumnModel().getColumn(6).setPreferredWidth(64);
            tblRegistros.getColumnModel().getColumn(6).setMaxWidth(64);

            tblRegistros.getColumnModel().getColumn(7).setMinWidth(0);
            tblRegistros.getColumnModel().getColumn(7).setPreferredWidth(0);
            tblRegistros.getColumnModel().getColumn(7).setMaxWidth(0);
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
            java.util.logging.Logger.getLogger(seleccionarPLU.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(seleccionarPLU.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(seleccionarPLU.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(seleccionarPLU.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                seleccionarPLU dialog = new seleccionarPLU(new javax.swing.JFrame(), true, "");
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblRegistros;
    // End of variables declaration//GEN-END:variables
}
