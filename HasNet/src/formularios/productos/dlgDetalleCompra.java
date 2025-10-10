package formularios.productos;

import clases.Instancias;
import clases.big;
import clases.metodosGenerales;
import clases.productos.ndProducto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.KeyStroke;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class dlgDetalleCompra extends javax.swing.JDialog {

    private Instancias instancias;
    DefaultTableModel modelo;
    TableRowSorter modeloOrdenado;
    metodosGenerales metodos = new metodosGenerales();
    Object[] prod;

    public dlgDetalleCompra(java.awt.Frame parent, boolean modal, Object[][] productos) {
        super(parent, modal);
        initComponents();

        this.setLocationRelativeTo(null);
        this.setTitle("Más información...");

        instancias = Instancias.getInstancias();

        modelo = (DefaultTableModel) tblProductos.getModel();
        modeloOrdenado = new TableRowSorter<>(modelo);
        tblProductos.setRowSorter(modeloOrdenado);

        int conteo = 0;
        for (Object[] producto : productos) {
            modelo.addRow(producto);
            KeyEvent x = new KeyEvent(this, WIDTH, WIDTH, WIDTH, KeyEvent.VK_ENTER);
            calcularTabla(conteo);
            conteo = conteo + 1;
        }

        this.getRootPane().registerKeyboardAction(accion("cerrar", this), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private ActionListener accion(final String opc, final JDialog ventana) {
        ActionListener a = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (opc) {
                    case "cerrar":
                        instancias.setCancelarFactura(false);
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProductos = new javax.swing.JTable();
        jPanel14 = new javax.swing.JPanel();
        chkPonderado = new javax.swing.JCheckBox();
        chkCosto = new javax.swing.JCheckBox();
        txtCodProducto = new javax.swing.JTextField();
        lbProducto = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 28)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("X");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        tblProductos.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        tblProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Descripcion", "Valor/Unit", "Cant", "Subtotal", "Desc %", "Desc", "Iva %", "Impo %", "Impo", "Medida", "Ultimo costo", "Lista 1", "plu", "cant", "Ponderado", "% Aum", "Utilidad", "% Rent.", "Bodega", "Impoconsumo", "Total", "Iva", "Borrar"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true, false, false, false, false, false, false, false, false, true, false, false, false, true, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblProductos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblProductos.setRowHeight(24);
        tblProductos.getTableHeader().setReorderingAllowed(false);
        tblProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductosMouseClicked(evt);
            }
        });
        tblProductos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblProductosKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblProductos);
        if (tblProductos.getColumnModel().getColumnCount() > 0) {
            tblProductos.getColumnModel().getColumn(0).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(0).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(2).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(2).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(2).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(3).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(3).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(3).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(4).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(4).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(4).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(5).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(5).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(5).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(6).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(6).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(6).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(7).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(7).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(7).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(8).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(8).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(8).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(9).setMinWidth(70);
            tblProductos.getColumnModel().getColumn(9).setPreferredWidth(100);
            tblProductos.getColumnModel().getColumn(9).setMaxWidth(130);
            tblProductos.getColumnModel().getColumn(10).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(10).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(10).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(11).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(11).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(11).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(12).setMinWidth(100);
            tblProductos.getColumnModel().getColumn(12).setPreferredWidth(120);
            tblProductos.getColumnModel().getColumn(12).setMaxWidth(150);
            tblProductos.getColumnModel().getColumn(13).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(13).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(13).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(14).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(14).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(14).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(15).setMinWidth(100);
            tblProductos.getColumnModel().getColumn(15).setPreferredWidth(120);
            tblProductos.getColumnModel().getColumn(15).setMaxWidth(150);
            tblProductos.getColumnModel().getColumn(16).setMinWidth(60);
            tblProductos.getColumnModel().getColumn(16).setPreferredWidth(60);
            tblProductos.getColumnModel().getColumn(16).setMaxWidth(60);
            tblProductos.getColumnModel().getColumn(17).setMinWidth(100);
            tblProductos.getColumnModel().getColumn(17).setPreferredWidth(120);
            tblProductos.getColumnModel().getColumn(17).setMaxWidth(150);
            tblProductos.getColumnModel().getColumn(18).setMinWidth(100);
            tblProductos.getColumnModel().getColumn(18).setPreferredWidth(100);
            tblProductos.getColumnModel().getColumn(18).setMaxWidth(100);
            tblProductos.getColumnModel().getColumn(19).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(19).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(19).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(20).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(20).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(20).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(21).setMinWidth(100);
            tblProductos.getColumnModel().getColumn(21).setPreferredWidth(120);
            tblProductos.getColumnModel().getColumn(21).setMaxWidth(150);
            tblProductos.getColumnModel().getColumn(22).setMinWidth(70);
            tblProductos.getColumnModel().getColumn(22).setPreferredWidth(100);
            tblProductos.getColumnModel().getColumn(22).setMaxWidth(130);
            tblProductos.getColumnModel().getColumn(23).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(23).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(23).setMaxWidth(0);
        }

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));

        buttonGroup1.add(chkPonderado);
        chkPonderado.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        chkPonderado.setText("Utilidad por ponderado");
        chkPonderado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkPonderado.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkPonderadoItemStateChanged(evt);
            }
        });

        buttonGroup1.add(chkCosto);
        chkCosto.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        chkCosto.setSelected(true);
        chkCosto.setText("Utilidad por costo");
        chkCosto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkCosto.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkCostoItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(chkPonderado, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(chkCosto, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkPonderado, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkCosto, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        txtCodProducto.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        txtCodProducto.setName("combo"); // NOI18N
        txtCodProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodProductoActionPerformed(evt);
            }
        });
        txtCodProducto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCodProductoFocusGained(evt);
            }
        });
        txtCodProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodProductoKeyReleased(evt);
            }
        });

        lbProducto.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbProducto.setText("BUSCADOR:");
        lbProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lbProductoKeyReleased(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Century Gothic", 1, 30)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("UTILIDADES");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1037, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbProducto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCodProducto))
                    .addComponent(jPanel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(25, 25, 25))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(5, 5, 5)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCodProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
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

    private void tblProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductosMouseClicked
//        if (tblProductos.getSelectedColumn() == 3) {
//            ndProducto nodo = sql.getDatosProducto(tblProductos.getValueAt(tblProductos.getSelectedRow(), 0).toString(), "bdProductos");
//
//            String tipo = "";
//            try {
//                if (nodo.getTipoProducto().equals("IMEI")) {
//                    tipo = "Imei";
//                } else if (nodo.getTipoProducto().equals("Fecha/Lote")) {
//                    tipo = "Fecha/Lote";
//                } else if (nodo.getTipoProducto().equals("Color")) {
//                    tipo = "Color";
//                } else if (nodo.getTipoProducto().equals("Serial")) {
//                    tipo = "Serial";
//                } else {
//                    tipo = "";
//                }
//            } catch (Exception e) {
//            }
//
//            int contador = 0;
//            for (int i = 0; i < tblDetalle.getRowCount(); i++) {
//                if (tblProductos.getValueAt(tblProductos.getSelectedRow(), 0).equals(tblDetalle.getValueAt(i, 0))) {
//                    contador++;
//                }
//            }
//
//            Object[][] productos = new Object[contador][6];
//
//            int xyz = 0;
//            for (int i = 0; i < tblDetalle.getRowCount(); i++) {
//                if (tblProductos.getValueAt(tblProductos.getSelectedRow(), 0).equals(tblDetalle.getValueAt(i, 0))) {
//                    productos[xyz][0] = tblDetalle.getValueAt(i, 1);
//                    productos[xyz][1] = tblDetalle.getValueAt(i, 5);
//                    productos[xyz][2] = tblDetalle.getValueAt(i, 3);
//                    productos[xyz][3] = tblDetalle.getValueAt(i, 2);
//                    productos[xyz][4] = tblDetalle.getValueAt(i, 4);
//                    productos[xyz][5] = tblDetalle.getValueAt(i, 7);
//                    xyz++;
//                }
//            }
//
//            if (!tipo.equals("")) {
//                dlgCompraDetallada compraDetallada = new dlgCompraDetallada(null, true, tipo, nodo.getIdSistema(), productos, "Entrada", "pnlIngreso",
//                        "bdProductos");
//                compraDetallada.setLocationRelativeTo(null);
//                compraDetallada.setVisible(true);
//            }
//        }
    }//GEN-LAST:event_tblProductosMouseClicked

    private void tblProductosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblProductosKeyReleased
        int fila = tblProductos.getSelectedRow(), i = 2, j = 0;

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            //Si se esta cambiando el campo de valor

            if (tblProductos.getSelectedColumn() == 12) {
                if (tblProductos.getValueAt(fila, 12).equals("")) {
                    tblProductos.setValueAt(big.setMoneda(big.getBigDecimal("0")), fila, 12);
                } else {
                    try {
                        tblProductos.setValueAt(big.setMoneda(big.getBigDecimal(tblProductos.getValueAt(fila, 12))), fila, 12);
                    } catch (Exception e) {
                        tblProductos.setValueAt(big.setMoneda(big.getMoneda(tblProductos.getValueAt(fila, 12).toString())), fila, 12);
                    }

                    ndProducto nodo = instancias.getSql().getDatosProducto(tblProductos.getValueAt(tblProductos.getSelectedRow(), 0).toString(), "bdProductos");
                    if (metodos.msgPregunta(null, "¿Desea cambiar el PVP?") == 0) {
                        nodo.setL1(big.getBigDecimal(big.getMoneda(tblProductos.getValueAt(tblProductos.getSelectedRow(), 12).toString())).toString());

                        if (!instancias.getSql().modificarProducto(nodo, "bdProductos")) {
                            metodos.msgError(null, "Hubo un problema al modificar el producto");
                            return;
                        }
                        if (!instancias.getSql().modificarProducto(nodo, "bdProductosBodega1")) {
                            metodos.msgError(null, "Hubo un problema al modificar el producto");
                            return;
                        }

                        if (!instancias.getSql().modificarProducto(nodo, "bdProductosBodega2")) {
                            metodos.msgError(null, "Hubo un problema al modificar el producto");
                            return;
                        }

                        if (!instancias.getSql().modificarProducto(nodo, "bdProductosBodega3")) {
                            metodos.msgError(null, "Hubo un problema al modificar el producto");
                            return;
                        }

                        if (!instancias.getSql().modificarProducto(nodo, "bdProductosBodega4")) {
                            metodos.msgError(null, "Hubo un problema al modificar el producto");
                            return;
                        }
                        metodos.msgExito(null, "Valor actualizado con éxito");

                        BigDecimal utilidad = big.getMoneda(tblProductos.getValueAt(fila, 12).toString()).subtract(big.getMoneda(tblProductos.getValueAt(fila, 15).toString()));
                        tblProductos.setValueAt(big.setMoneda(utilidad), fila, 17);
//                        txtCodProducto.requestFocus();
                        i = 12;
                    } else {
                        tblProductos.setValueAt(big.setMoneda(big.getBigDecimal(nodo.getL1())), fila, 12);
                    }

                    j = 0;
                }
            } else if (tblProductos.getSelectedColumn() == 16) {

                if (tblProductos.getValueAt(fila, 16).equals("")) {
                    tblProductos.setValueAt(big.getBigDecimal("0"), fila, 16);
                } else {
                    try {
                        tblProductos.setValueAt(big.getBigDecimal(tblProductos.getValueAt(fila, 16)), fila, 16);
                    } catch (Exception e) {
                        tblProductos.setValueAt(big.getMoneda(tblProductos.getValueAt(fila, 16).toString()), fila, 16);
                    }

                    if (metodos.msgPregunta(null, "¿Desea cambiar el valor de la lista de este producto?") == 0) {
                        BigDecimal valor = big.getMoneda(String.valueOf(tblProductos.getValueAt(fila, 2)));
                        BigDecimal aumento = big.getBigDecimal(String.valueOf(tblProductos.getValueAt(fila, 16)));
                        BigDecimal iva, total;

                        iva = big.getMoneda(String.valueOf(tblProductos.getValueAt(fila, 7)));
                        iva = (iva.divide(big.getBigDecimal("100"))).add(big.getBigDecimal("1"));
                        total = valor.multiply(iva);
                        total = total.multiply(aumento.divide(big.getBigDecimal("100")).add(big.getBigDecimal("1")));
                        ndProducto nodo = instancias.getSql().getDatosProducto(tblProductos.getValueAt(fila, 0).toString(), "bdProductos");
                        nodo.setL1(big.getMoneda(big.setMonedaExacta(total)).toString());
                        if (!instancias.getSql().modificarProducto(nodo, "bdProductos")) {
                            metodos.msgError(null, "Hubo un problema al modificar el producto");
                            return;
                        }
                        if (!instancias.getSql().modificarProducto(nodo, "bdProductosBodega1")) {
                            metodos.msgError(null, "Hubo un problema al modificar el producto");
                            return;
                        }

                        if (!instancias.getSql().modificarProducto(nodo, "bdProductosBodega2")) {
                            metodos.msgError(null, "Hubo un problema al modificar el producto");
                            return;
                        }

                        if (!instancias.getSql().modificarProducto(nodo, "bdProductosBodega3")) {
                            metodos.msgError(null, "Hubo un problema al modificar el producto");
                            return;
                        }

                        if (!instancias.getSql().modificarProducto(nodo, "bdProductosBodega4")) {
                            metodos.msgError(null, "Hubo un problema al modificar el producto");
                            return;
                        }

                        metodos.msgExito(null, "Valor actualizado con éxito");

                        tblProductos.setValueAt(big.setMonedaExacta(total), fila, 12);
                        BigDecimal utilidad = big.getMoneda(tblProductos.getValueAt(fila, 12).toString()).subtract(big.getMoneda(tblProductos.getValueAt(fila, 15).toString()));
                        tblProductos.setValueAt(big.setMoneda(utilidad), fila, 17);
//                        txtCodProducto.requestFocus();
                        i = 12;
                    } else {
                        tblProductos.setValueAt("0", fila, 16);
                    }
                    j = 0;

                }
            }

            calcularTabla(fila);
        }

    }//GEN-LAST:event_tblProductosKeyReleased

    private void chkPonderadoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkPonderadoItemStateChanged
        for (int i = 0; i < tblProductos.getRowCount(); i++) {
            calcularTabla(i);
        }
    }//GEN-LAST:event_chkPonderadoItemStateChanged

    private void chkCostoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkCostoItemStateChanged
        for (int i = 0; i < tblProductos.getRowCount(); i++) {
            calcularTabla(i);
        }
    }//GEN-LAST:event_chkCostoItemStateChanged

    private void txtCodProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodProductoActionPerformed

    private void txtCodProductoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCodProductoFocusGained

    }//GEN-LAST:event_txtCodProductoFocusGained

    private void txtCodProductoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodProductoKeyReleased
        modeloOrdenado.setRowFilter(RowFilter.regexFilter("(?i)" + txtCodProducto.getText(), 1));
    }//GEN-LAST:event_txtCodProductoKeyReleased

    private void lbProductoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lbProductoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtCodProducto.requestFocus();
        }
    }//GEN-LAST:event_lbProductoKeyReleased

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        this.dispose();
    }//GEN-LAST:event_jLabel2MouseClicked

    private void calcularTabla(int fila) {

        BigDecimal iva, totalIva = BigDecimal.ZERO, impoconsumo, totalImpoconsumo = BigDecimal.ZERO, subtotalGeneral, valor, cantidad, descuento, subtotal, total, porcentaje, porcentaje2;

        tblProductos.setValueAt(big.setMoneda(big.getMoneda(String.valueOf(tblProductos.getValueAt(fila, 6)))), fila, 6);
        valor = big.getMoneda(String.valueOf(tblProductos.getValueAt(fila, 2)));

        cantidad = big.getMoneda(String.valueOf(tblProductos.getValueAt(fila, 3)).replace(".", ","));
        subtotal = valor.multiply(cantidad);

        iva = big.getMoneda(String.valueOf(tblProductos.getValueAt(fila, 7)));
        iva = (iva.divide(big.getBigDecimal("100"))).add(big.getBigDecimal("1"));

        impoconsumo = big.getMoneda(String.valueOf(tblProductos.getValueAt(fila, 8)));
        impoconsumo = (impoconsumo.divide(big.getBigDecimal("100"))).add(big.getBigDecimal("1"));

        if (tblProductos.getSelectedColumn() == 6) {
            descuento = big.getMoneda(tblProductos.getValueAt(fila, 6).toString());
            porcentaje2 = big.getBigDecimal(descuento.multiply(big.getBigDecimal("100")).divide(subtotal, 2, RoundingMode.HALF_DOWN));
        } else {
            if (tblProductos.getValueAt(fila, 5).toString().equals("")) {
                porcentaje2 = big.getBigDecimal("0");
            } else {
                porcentaje2 = big.getBigDecimal(tblProductos.getValueAt(fila, 5).toString().replace(",", ".")).divide(big.getBigDecimal("100"));
            }
            porcentaje = big.getBigDecimal(porcentaje2);
            descuento = big.getMoneda(big.setNumero(subtotal.multiply(porcentaje)));
            porcentaje2 = porcentaje2.multiply(big.getBigDecimal("100"));
        }

        subtotal = subtotal.subtract(descuento);
        subtotalGeneral = subtotal;

        if (instancias.isCostoConIva() || instancias.isCostoConImpoconsumo()) {
            BigDecimal subIva = BigDecimal.ZERO, subImpo = BigDecimal.ZERO;
            if (instancias.isCostoConIva() && instancias.isCostoConImpoconsumo()) {
                //CALCULAMOS EL IVA
                subIva = subtotal.divide(iva, 2, RoundingMode.HALF_UP);
                totalIva = subtotal.subtract(subIva);

                //CALCULAMOS EL IMPOCONSUMO
                subImpo = subtotal.divide(impoconsumo, 2, RoundingMode.HALF_UP);
                totalImpoconsumo = subtotal.subtract(subImpo);

                //CALCULAMOS EL SUBTOTAL
                subtotal = subtotal.subtract(totalImpoconsumo).subtract(totalIva);
            } else if (instancias.isCostoConIva()) {
                subIva = subtotal.divide(iva, 2, RoundingMode.HALF_UP);
                totalIva = subtotal.subtract(subIva);
                subtotal = subtotal.subtract(totalIva);
            } else if (instancias.isCostoConImpoconsumo()) {
                subImpo = subtotal.divide(impoconsumo, 2, RoundingMode.HALF_UP);
                totalImpoconsumo = subtotal.subtract(subImpo);
                subtotal = subtotal.subtract(totalImpoconsumo);
            }
        }

        if (!instancias.isCostoConIva()) {
            iva = big.getMoneda(String.valueOf(tblProductos.getValueAt(fila, 7)));
            iva = (iva.divide(big.getBigDecimal("100"))).add(big.getBigDecimal("1"));
            totalIva = subtotalGeneral.multiply(iva);
            totalIva = totalIva.subtract(subtotalGeneral);
        }

        if (!instancias.isCostoConImpoconsumo()) {
            impoconsumo = big.getMoneda(String.valueOf(tblProductos.getValueAt(fila, 8)));
            impoconsumo = (impoconsumo.divide(big.getBigDecimal("100"))).add(big.getBigDecimal("1"));

            totalImpoconsumo = subtotalGeneral.multiply(impoconsumo);
            totalImpoconsumo = totalImpoconsumo.subtract(subtotalGeneral);
        }

        total = subtotal.add(totalIva).add(totalImpoconsumo);

        tblProductos.setValueAt(big.setMoneda(big.getMoneda(tblProductos.getValueAt(fila, 15).toString())), fila, 15);
        tblProductos.setValueAt(big.setMoneda(big.getMoneda(tblProductos.getValueAt(fila, 17).toString())), fila, 17);

        tblProductos.setValueAt(big.setMoneda(valor), fila, 2);
        //tblProductos.setValueAt(big.setNumero(cantidad), fila, 3);
        tblProductos.setValueAt(big.setMoneda(totalImpoconsumo), fila, 9);
        tblProductos.setValueAt(big.setMoneda(subtotal), fila, 4);
        tblProductos.setValueAt(porcentaje2, fila, 5);
        tblProductos.setValueAt(big.setMoneda(descuento), fila, 6);
        tblProductos.setValueAt(big.setMoneda(totalIva), fila, 22);
        tblProductos.setValueAt(big.setMoneda(total), fila, 23);

        switch (tblProductos.getValueAt(fila, 13).toString()) {
            case "1":
                tblProductos.setValueAt(cantidad, fila, 14);
                break;
            case "2":
                tblProductos.setValueAt(cantidad.multiply(big.getBigDecimal(
                        instancias.getSql().getDatosProducto(tblProductos.getValueAt(fila, 0).toString(), "bdProductos").getCantidad2())), fila, 14);
                break;
            case "3":
                tblProductos.setValueAt(cantidad.multiply(big.getBigDecimal(
                        instancias.getSql().getDatosProducto(tblProductos.getValueAt(fila, 0).toString(), "bdProductos").getCantidad3())), fila, 14);
                break;
            case "4":
                tblProductos.setValueAt(cantidad.multiply(big.getBigDecimal(
                        instancias.getSql().getDatosProducto(tblProductos.getValueAt(fila, 0).toString(), "bdProductos").getCantidad4())), fila, 14);
                break;
            case "5":
                tblProductos.setValueAt(cantidad.multiply(big.getBigDecimal(
                        instancias.getSql().getDatosProducto(tblProductos.getValueAt(fila, 0).toString(), "bdProductos").getCantidad5())), fila, 14);
                break;
            case "6":
                tblProductos.setValueAt(cantidad.multiply(big.getBigDecimal(
                        instancias.getSql().getDatosProducto(tblProductos.getValueAt(fila, 0).toString(), "bdProductos").getCantidad6())), fila, 14);
                break;
            case "7":
                tblProductos.setValueAt(cantidad.multiply(big.getBigDecimal(
                        instancias.getSql().getDatosProducto(tblProductos.getValueAt(fila, 0).toString(), "bdProductos").getCantidad7())), fila, 14);
                break;
            case "8":
                tblProductos.setValueAt(cantidad.multiply(big.getBigDecimal(
                        instancias.getSql().getDatosProducto(tblProductos.getValueAt(fila, 0).toString(), "bdProductos").getCantidad8())), fila, 14);
                break;
        }

        ndProducto producto = instancias.getSql().getDatosProducto(tblProductos.getValueAt(fila, 0).toString(), "bdProductos");

        Object[] ultimoPonderado = instancias.getSql().getUltimoPonderado(tblProductos.getValueAt(fila, 0).toString());

        //CALCULAMOS LO QUE HABIA VIEJO
        BigDecimal inv = big.getBigDecimal(producto.getInventario().replace(",", "."));
        BigDecimal ponderadoViejo = big.getBigDecimal(ultimoPonderado[4].toString());

        BigDecimal totalViejo;
        if (inv.compareTo(BigDecimal.ZERO) >= 0) {
            totalViejo = inv.multiply(ponderadoViejo);
        } else {
            totalViejo = BigDecimal.ZERO;
        }

        //CALCULAMOS LO QUE ENTRA NUEVO
        BigDecimal nuevoPrecio;
        BigDecimal cant = big.getBigDecimal(tblProductos.getValueAt(fila, 14).toString().replace(",", "."));

        if (instancias.getRegimen().equals("SinIva")) {
            nuevoPrecio = big.getMoneda(tblProductos.getValueAt(fila, 23).toString());
        } else {
            nuevoPrecio = big.getMoneda(tblProductos.getValueAt(fila, 4).toString());
        }

        nuevoPrecio = nuevoPrecio.divide(cant, 3, RoundingMode.CEILING);
        BigDecimal totalNuevo = cant.multiply(nuevoPrecio);

        //NUEVO PONDERADO
        BigDecimal nuevoPonderado;

        if (inv.compareTo(BigDecimal.ZERO) <= 0) {
            nuevoPonderado = nuevoPrecio;
        } else {
            BigDecimal inventario = inv.add(cant);
            nuevoPonderado = totalNuevo.add(totalViejo);
            nuevoPonderado = nuevoPonderado.divide(big.getBigDecimal(inventario), 3, RoundingMode.CEILING);
        }
        tblProductos.setValueAt(big.setMoneda(nuevoPonderado), fila, 15);

        BigDecimal utilidadRent, porcentajeRent;

        if (chkPonderado.isSelected()) {
            BigDecimal valorProd = big.getMoneda(tblProductos.getValueAt(fila, 12).toString());
            BigDecimal costoProd = big.getMoneda(tblProductos.getValueAt(fila, 15).toString());
            utilidadRent = valorProd.subtract(costoProd);

            if (valorProd.compareTo(BigDecimal.ZERO) > 0) {
                porcentajeRent = utilidadRent.divide(valorProd, 3, RoundingMode.CEILING);
            } else {
                porcentajeRent = utilidadRent.divide(big.getBigDecimal("1"));
            }

            porcentajeRent = porcentajeRent.multiply(big.getBigDecimal("100"));
            tblProductos.setValueAt(big.setMoneda(utilidadRent), fila, 17);
            tblProductos.setValueAt(big.setNumero(porcentajeRent) + "%", fila, 18);
        } else {
            BigDecimal valorProd = big.getMoneda(tblProductos.getValueAt(fila, 12).toString());
            BigDecimal costoProd = big.getMoneda(tblProductos.getValueAt(fila, 2).toString());
            utilidadRent = valorProd.subtract(costoProd);

            if (valorProd.compareTo(BigDecimal.ZERO) > 0) {
                porcentajeRent = utilidadRent.divide(valorProd, 3, RoundingMode.CEILING);
            } else {
                porcentajeRent = utilidadRent.divide(big.getBigDecimal("1"));
            }

            porcentajeRent = porcentajeRent.multiply(big.getBigDecimal("100"));
            tblProductos.setValueAt(big.setMoneda(utilidadRent), fila, 17);
            tblProductos.setValueAt(big.setNumero(porcentajeRent) + "%", fila, 18);
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
            java.util.logging.Logger.getLogger(dlgDetalleCompra.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(dlgDetalleCompra.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(dlgDetalleCompra.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dlgDetalleCompra.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                dlgDetalleCompra dialog = new dlgDetalleCompra(new javax.swing.JFrame(), true, null);
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
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox chkCosto;
    private javax.swing.JCheckBox chkPonderado;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbProducto;
    private javax.swing.JTable tblProductos;
    private javax.swing.JTextField txtCodProducto;
    // End of variables declaration//GEN-END:variables
}
