package Vista.Productos;

import Dao.Productos.DaoProducto;

import Utilidades.Utilidades;
import clases.Instancias;
import clases.big;
import clases.cambiarColorTabla;
import clases.metodosGenerales;
import Modelo.Productos.ModeloListaPrecio;
import Modelo.Productos.ModeloProducto;
import Vista.Cartera.VistaNotaDebito;
import Vista.Ventas.VistaFactura;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;

public class VistaSeleccionarPLU extends javax.swing.JDialog {
    private final DaoProducto daoProducto = new DaoProducto();

    private Instancias instancias;
    private String codigo;
    private String opc, baseUtilizada;
    private VistaFactura factura;
    private VistaNotaDebito notaDebito;

    public VistaFactura getFactura() {
        return factura;
    }

    public void setFactura(VistaFactura factura) {
        this.factura = factura;
    }

    public VistaNotaDebito getNotaDebito() {
        return notaDebito;
    }

    public void setNotaDebito(VistaNotaDebito notaDebito) {
        this.notaDebito = notaDebito;
    }

    public VistaSeleccionarPLU(java.awt.Frame parent, boolean modal, String base) {
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
        btnIngresar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Seleccionar PLU");

        tblRegistros.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        tblRegistros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código PLU", "Descripción", "Referencia", "Ubicación", "Cantidad unidades", "Inventario", "Cantidad a ingresar"
            }
        ));
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

        btnIngresar.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnIngresar.setText("Ingresar");
        btnIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarActionPerformed(evt);
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
                        .addComponent(btnIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addComponent(btnIngresar)
                .addGap(10, 10, 10))
        );

        getAccessibleContext().setAccessibleName("PLU");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarActionPerformed
        for (int i = 0; i < tblRegistros.getRowCount(); i++) {
            BigDecimal cantidadAIngresar = Utilidades.convertirBigDecimal(tblRegistros.getValueAt(i, 6).toString());
            int numeroPlu = Integer.parseInt(tblRegistros.getValueAt(i, 7).toString());

            if (cantidadAIngresar.compareTo(BigDecimal.ZERO) > 0) {
                switch (opc) {
                    case "NotaDebito":
                        notaDebito.setPlu(false);
                        notaDebito.cargarProducto(codigo, (((String) tblRegistros.getValueAt(i, 6))), numeroPlu, "",
                                "", "", false, "", "", "", "", "");
                        break;
                    case "factura":
                        factura.setPlu(false);
                        factura.cargarProducto(codigo, cantidadAIngresar, numeroPlu, "", "", "", true, "", "", "", "", "");
                        break;
                    case "ordenCompra":
                        instancias.getOrdenCompra().setPlu(false);
                        instancias.getOrdenCompra().cargarProducto(codigo, cantidadAIngresar, numeroPlu, "");
                        break;
                    case "ingreso":
                        instancias.getIngresos().setPlu(false);
                        instancias.getIngresos().cargarProducto(codigo, cantidadAIngresar, numeroPlu, "");
                        break;
                    case "diseño":
                        instancias.getCosteo().setPlu(false);
                        instancias.getCosteo().cargarProducto(codigo, (((String) tblRegistros.getValueAt(i, 6))), numeroPlu);
                        break;
                    case "ajuste":
                        instancias.getVistaAjusteInventario().setPlu(false);
                        instancias.getVistaAjusteInventario().cargarProducto(codigo, cantidadAIngresar, numeroPlu, "", "", "", "", "", "", "");
                        break;
                    case "trasladoInterno":
                        instancias.getTrasladosInternos().setPlu(false);
                        instancias.getTrasladosInternos().cargarProducto(codigo, cantidadAIngresar, numeroPlu, "", "", "", "", "", "", "");
                        break;
                    case "traslado":
                        instancias.getPrestamos().setPlu(false);
                        instancias.getPrestamos().cargarProducto(codigo, cantidadAIngresar, numeroPlu, "", "", "", "", "", "", "");
                        break;
                    default:
                        instancias.setValorCampo((String) tblRegistros.getValueAt(tblRegistros.getSelectedRow(), 0));
                        metodosGenerales.presionarEnter(instancias.getCampoActual());
                        break;
                }
            }
        }

        this.dispose();
    }//GEN-LAST:event_btnIngresarActionPerformed

    private void tblRegistrosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblRegistrosMouseClicked
        if (evt.getClickCount() == 2) {
            tblRegistros.setValueAt("1", tblRegistros.getSelectedRow(), 6);
            btnIngresarActionPerformed(null);
        }
    }//GEN-LAST:event_tblRegistrosMouseClicked

    public void setInstancias(Instancias instancias, String cod) {
        this.instancias = instancias;
        this.codigo = cod;

        ModeloProducto producto = daoProducto.obtenerDatosProducto(codigo, baseUtilizada);

        String inv = Utilidades.formatearCantidad(producto.getFisicoInventario());

        List<ModeloListaPrecio> plus = producto.getListasPlu();

        Object[][] data = new Object[plus.size() + 1][8];
        Object[] columnNames = {"PLU", "Descripción", "Referencia", "Ubicación", "Cant", "Inv", "Ingresar", "plu"};
        int cant = 0;
        data[cant][0] = producto.getCodigo() + "-1";
        data[cant][1] = producto.getDescripcion();
        data[cant][3] = producto.getUbicacion1();
        data[cant][4] = 1;
        data[cant][5] = inv;
        data[cant][6] = 0;
        data[cant][7] = ModeloListaPrecio.LISTA_PRINCIPAL;
        cant++;

        for (ModeloListaPrecio lista : plus) {
            data[cant][0] = lista.getCodigo();
            data[cant][1] = lista.getDescripcion();
            data[cant][3] = lista.getUbicacion();
            data[cant][4] = lista.getCantidad().stripTrailingZeros().toPlainString();
            data[cant][5] = big.getMoneda(inv).divide(lista.getCantidad(), 2, RoundingMode.HALF_DOWN);
            data[cant][6] = 0;
            data[cant][7] = lista.getNumeroLista();
            cant++;
        }

        for (Object[] fila : data) {
            fila[2] = producto.getReferencia();
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
            java.util.logging.Logger.getLogger(VistaSeleccionarPLU.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaSeleccionarPLU.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaSeleccionarPLU.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaSeleccionarPLU.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                VistaSeleccionarPLU dialog = new VistaSeleccionarPLU(new javax.swing.JFrame(), true, "");
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
    private javax.swing.JButton btnIngresar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblRegistros;
    // End of variables declaration//GEN-END:variables
}
