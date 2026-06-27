package formularios.Veterinario;

import Modelo.Inventario.UltimoPonderado;
import inventario.servicio.ServicioActualizacionPonderado;
import Vista.Productos.VistaInventarioInicial;
import clases.Instancias;
import clases.big;
import clases.metodosGenerales;
import clases.productos.ndProducto;
import formularios.productos.buscProductos;
import inventario.vista.VistaMovimientoDetalleProducto;
import formularios.productos.seleccionarPLU;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;

import javax.swing.table.DefaultTableModel;

public class infAgregarProductosHistorial extends javax.swing.JInternalFrame {

    private ServicioActualizacionPonderado servicioActualizacionPonderado = new ServicioActualizacionPonderado();
    
    DefaultTableModel modeloPro;
    metodosGenerales metodos = new metodosGenerales();
    Instancias instancias;
    private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension dimBarra = null;
    DefaultTableModel modelo;
    Object[] datos;

    private boolean plu = false;

    public infAgregarProductosHistorial() {

        initComponents();

        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
        dimBarra = Barra.getPreferredSize();
        Barra.setSize(0, 0);
        Barra.setPreferredSize(new Dimension(0, 0));
        setBorder(null);
        repaint();

        instancias = Instancias.getInstancias();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrFormulario = new javax.swing.JScrollPane();
        pnlFormulario = new javax.swing.JPanel();
        lbNit5 = new javax.swing.JLabel();
        scrProductos = new javax.swing.JScrollPane();
        tblProductos = new javax.swing.JTable();
        txtCant = new javax.swing.JTextField();
        lbNit = new javax.swing.JLabel();
        txtCodProducto = new javax.swing.JTextField();
        btnBusProd = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        lbTitulo5 = new javax.swing.JLabel();
        scrProductos1 = new javax.swing.JScrollPane();
        tblProductos1 = new javax.swing.JTable();
        lbNit7 = new javax.swing.JLabel();
        lbNit8 = new javax.swing.JLabel();
        txtValor = new javax.swing.JLabel();

        setTitle("Factura");

        pnlFormulario.setBackground(new java.awt.Color(255, 255, 255));

        lbNit5.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbNit5.setText("Cant:");

        tblProductos.setFont(new java.awt.Font("Century Gothic", 0, 22)); // NOI18N
        tblProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Descripción", "Valor/Unit", "Cant", "Total", "Serial", "Lote", "Costo", "idProd", "Nota", "cant2", "plu", "Inv. Inicial", "Inv. Final", "iva"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true, false, false, false, false, false, true, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblProductos.setRowHeight(29);
        tblProductos.getTableHeader().setReorderingAllowed(false);
        tblProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductosMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblProductosMouseReleased(evt);
            }
        });
        tblProductos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblProductosKeyReleased(evt);
            }
        });
        scrProductos.setViewportView(tblProductos);

        txtCant.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        txtCant.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCant.setText("1");
        txtCant.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtCant.setEnabled(false);
        txtCant.setName("combo"); // NOI18N
        txtCant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantActionPerformed(evt);
            }
        });
        txtCant.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCantKeyReleased(evt);
            }
        });

        lbNit.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbNit.setText("Productos:");

        txtCodProducto.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        txtCodProducto.setName("combo"); // NOI18N
        txtCodProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodProductoActionPerformed(evt);
            }
        });
        txtCodProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodProductoKeyReleased(evt);
            }
        });

        btnBusProd.setBackground(new java.awt.Color(204, 204, 204));
        btnBusProd.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnBusProd.setForeground(new java.awt.Color(255, 255, 255));
        btnBusProd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar1.png"))); // NOI18N
        btnBusProd.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnBusProd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBusProd.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnBusProd.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnBusProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBusProdActionPerformed(evt);
            }
        });

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        lbTitulo5.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lbTitulo5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbTitulo5.setText("Historial de compras");

        tblProductos1.setFont(new java.awt.Font("Century Gothic", 0, 22)); // NOI18N
        tblProductos1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Descripción", "Valor/Unit", "Cant", "Total", "Serial", "Lote", "Costo", "idProd", "Nota", "cant2", "plu", "Inv. Inicial", "Inv. Final", "iva"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true, false, false, false, false, false, true, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblProductos1.setRowHeight(29);
        tblProductos1.getTableHeader().setReorderingAllowed(false);
        tblProductos1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductos1MouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblProductos1MouseReleased(evt);
            }
        });
        tblProductos1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblProductos1KeyReleased(evt);
            }
        });
        scrProductos1.setViewportView(tblProductos1);

        lbNit7.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        lbNit7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit7.setText("PRODUCTOS ADICIONALES AL SERVICIO");

        lbNit8.setFont(new java.awt.Font("Century Gothic", 1, 28)); // NOI18N
        lbNit8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbNit8.setText("TOTAL:");

        txtValor.setFont(new java.awt.Font("Century Gothic", 1, 28)); // NOI18N
        txtValor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtValor.setText("0");

        javax.swing.GroupLayout pnlFormularioLayout = new javax.swing.GroupLayout(pnlFormulario);
        pnlFormulario.setLayout(pnlFormularioLayout);
        pnlFormularioLayout.setHorizontalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbNit7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlFormularioLayout.createSequentialGroup()
                        .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnlFormularioLayout.createSequentialGroup()
                                .addComponent(lbNit5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCant, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24)
                                .addComponent(lbNit)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCodProducto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBusProd, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(scrProductos, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE))
                        .addGap(15, 15, 15)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlFormularioLayout.createSequentialGroup()
                                .addComponent(lbNit8, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtValor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(lbTitulo5, javax.swing.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)
                            .addComponent(scrProductos1, javax.swing.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE))))
                .addContainerGap())
        );
        pnlFormularioLayout.setVerticalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbNit7, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                .addGap(15, 15, 15)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator3)
                    .addGroup(pnlFormularioLayout.createSequentialGroup()
                        .addComponent(lbTitulo5, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scrProductos1, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbNit8, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                            .addComponent(txtValor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(pnlFormularioLayout.createSequentialGroup()
                        .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtCodProducto)
                                .addComponent(txtCant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lbNit5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbNit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnBusProd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scrProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addGap(145, 145, 145))
        );

        scrFormulario.setViewportView(pnlFormulario);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(scrFormulario)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(scrFormulario, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductosMouseClicked

    }//GEN-LAST:event_tblProductosMouseClicked

    private void tblProductosMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductosMouseReleased

    }//GEN-LAST:event_tblProductosMouseReleased

    private void tblProductosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblProductosKeyReleased
        BigDecimal valor, cantidad, iva, total, cantFacturar, rest, totalN;

        int fila = tblProductos.getSelectedRow(), i = 3, j = 1;

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            //Si se esta cambiando el campo de cantidad

            if (tblProductos.getSelectedColumn() == 2) {
                tblProductos.setValueAt(big.setMoneda(big.getMoneda(tblProductos.getValueAt(fila, 2).toString())), fila, 2);
                tblProductos.editCellAt(tblProductos.getSelectedRow(), 3);
                tblProductos.setColumnSelectionInterval(3, 3);
                tblProductos.transferFocus();
            } else if (tblProductos.getSelectedColumn() == 3) {
                tblProductos.editCellAt(tblProductos.getSelectedRow(), 9);
                tblProductos.setColumnSelectionInterval(9, 9);
                tblProductos.transferFocus();
            } else if (tblProductos.getSelectedColumn() == 9) {
                txtCodProducto.requestFocus();
            }

            try {
                int res = big.getBigDecimal(big.getMoneda((String) tblProductos.getValueAt(fila, i))).compareTo(big.getBigDecimal("1"));
                if (res == -1 || String.valueOf(tblProductos.getValueAt(fila, i)).equals("")) {
                    tblProductos.setValueAt(j, tblProductos.getSelectedRow(), i);
                }
            } catch (NumberFormatException e) {
                tblProductos.setValueAt(j, tblProductos.getSelectedRow(), i);
            }

            ndProducto nodo = instancias.getSql().getDatosProducto(tblProductos.getValueAt(fila, 0).toString(), "bdProductos");

            valor = big.getMoneda(String.valueOf(tblProductos.getValueAt(fila, 2)));
            cantidad = big.getMoneda(String.valueOf(tblProductos.getValueAt(fila, 3)).replace(".", ","));
            total = valor.multiply(cantidad);

            iva = total.multiply(big.getBigDecimal(nodo.getIva()));
            iva = iva.divide(big.getBigDecimal("100"), 3, RoundingMode.CEILING);
            total = total.add(iva);

            tblProductos.setValueAt(big.setMoneda(total), fila, 4);
            tblProductos.setValueAt(big.setMoneda(iva), fila, 14);

            tblProductos.setValueAt(big.setNumero(cantidad), fila, 3);

            switch (tblProductos.getValueAt(fila, 11).toString()) {
                case "1":
                    tblProductos.setValueAt(cantidad, fila, 10);
                    break;
                case "2":
                    tblProductos.setValueAt(cantidad.multiply(big.getBigDecimal(nodo.getCantidad2())), fila, 10);
                    break;
                case "3":
                    tblProductos.setValueAt(cantidad.multiply(big.getBigDecimal(nodo.getCantidad3())), fila, 10);
                    break;
                case "4":
                    tblProductos.setValueAt(cantidad.multiply(big.getBigDecimal(nodo.getCantidad4())), fila, 10);
                    break;
            }

            tblProductos.setValueAt(Double.valueOf(tblProductos.getValueAt(tblProductos.getSelectedRow(), 12).toString().replace(",", ".")) - Double.valueOf(tblProductos.getValueAt(tblProductos.getSelectedRow(), 3).toString().replace(",", ".")), tblProductos.getSelectedRow(), 13);

            cargarTotales();

            //            if (tblProductos.getColumnCount() > 5) {
            //                cantFacturar = big.getMoneda(String.valueOf(tblProductos.getValueAt(fila, 5)));
            //                rest = cantidad.subtract(cantFacturar);
            //                tblProductos.setValueAt(rest, fila, 6);
            //                if (rest.signum() == -1) {
            //                    metodos.msgError(this, "No puede facturar mas de la cantidad de productos del traslado");
            //                    tblProductos.setValueAt("0", fila, 5);
            //                    tblProductos.setValueAt(tblProductos.getValueAt(fila, 3), fila, 6);
            //                    tblProductos.setValueAt("0", fila, 7);
            //                }
            //
            //                totalN = valor.multiply(big.getMoneda(String.valueOf(tblProductos.getValueAt(fila, 7))));
            //                tblProductos.setValueAt(big.setMoneda(totalN), fila, 8);
            //            }
        }
    }//GEN-LAST:event_tblProductosKeyReleased

    private void txtCantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantActionPerformed

    private void txtCantKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantKeyReleased

    private void txtCodProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodProductoActionPerformed

    private void txtCodProductoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodProductoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String codigo = txtCodProducto.getText();
            plu = true;
            cargarProducto(codigo.replace("'", "//"), txtCant.getText(), "", "", "", 1);
        } else if (evt.getKeyCode() == KeyEvent.VK_MULTIPLY) {
            double cantidad = 1;
            try {
                cantidad = Double.parseDouble(txtCodProducto.getText().replace("*", ""));
            } catch (Exception e) {
            }
            txtCant.setText(String.valueOf(cantidad));
            if (txtCant.getText().substring(txtCant.getText().length() - 1, txtCant.getText().length()).equals("0")) {
                txtCant.setText(txtCant.getText().substring(0, txtCant.getText().length() - 2));
            }
            txtCodProducto.setText("");
        }
    }//GEN-LAST:event_txtCodProductoKeyReleased

    private void btnBusProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBusProdActionPerformed
        ventanaProductos("");
    }//GEN-LAST:event_btnBusProdActionPerformed

    private void tblProductos1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductos1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblProductos1MouseClicked

    private void tblProductos1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductos1MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tblProductos1MouseReleased

    private void tblProductos1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblProductos1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tblProductos1KeyReleased

    public void ventanaProductos(String codigo) {
        buscProductos buscar = new buscProductos(null, true, false, "", "productos1");
        buscar.setLocationRelativeTo(null);
        instancias.setBusProductos(buscar);
        instancias.setCampoActual(txtCodProducto);
        txtCodProducto.requestFocus();
//        buscar.setInstancia(instancias);
        buscar.noEncontrado(codigo.replace("'", "//"));
        buscar.show();
    }

    public void cargarProducto(String codigo, String cantidad, String imei, String lote, String idProd, int plu) {

        for (int j = 0; j < tblProductos.getRowCount(); j++) {
            String idPro = "";
            try {
                idPro = tblProductos.getValueAt(j, 10).toString();
            } catch (Exception e) {
            }

            if (!idPro.equals("")) {
                if (idPro.equals(idProd)) {
                    if (!imei.equals("")) {
                        metodos.msgError(this, "El imei '" + imei + "' ya esta cargado.");
                        return;
                    } else {
                        metodos.msgError(this, "Este producto con el lote '" + lote + "' ya se cargó.");
                        return;
                    }
                }
            }
        }

//        for (int j = 0; j < tblProductos.getRowCount(); j++) {
//            if (codigo.equalsIgnoreCase((String) tblProductos.getValueAt(j, 0))) {
//                metodos.msgAdvertencia(this, "El producto ya esta cargado");
//                tblProductos.setColumnSelectionInterval(3, 3);
//                tblProductos.setRowSelectionInterval(j, j);
//                tblProductos.editCellAt(j, 3);
//                tblProductos.transferFocus();
//                txtCodProducto.setText("");
//                return;
//            }
//        }
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
            if (nodo.getCodigo() != null) {
                codigoProd = nodo.getCodigo();
            }
        }

        if (!codigoProd.equals("")) {
            if (instancias.getSql().getProdActivo(nodo.getCodigo())) {
                metodos.msgError(null, "Este producto esta inactivo");
                lbNit.requestFocus();
                return;
            }

            if (this.plu) {
                this.plu = false;
                int cant = 0;
                if (nodo.isPlu2()) {
                    cant++;
                }
                if (nodo.isPlu3()) {
                    cant++;
                }
                if (nodo.isPlu4()) {
                    cant++;
                }
                if (nodo.getPlu5()) {
                    cant++;
                }
                if (nodo.getPlu6()) {
                    cant++;
                }
                if (nodo.getPlu7()) {
                    cant++;
                }
                if (nodo.getPlu8()) {
                    cant++;
                }
                if (cant > 0) {
                    seleccionarPLU pluu = new seleccionarPLU(null, true, "bdProductos");
                    pluu.setInstancias(instancias, nodo.getCodigo());
                    pluu.setOpc("trasladoBodega");
                    pluu.setVisible(true);
                    return;
                }
            }

            String cant2 = "1";
            String desc = nodo.getDescripcion();
            String lista = nodo.getL1();
            switch (plu) {
                case 2:
                    cant2 = nodo.getCantidad2();
                    desc = nodo.getDescripcion2();
                    lista = nodo.getL2();
                    break;
                case 3:
                    cant2 = nodo.getCantidad3();
                    desc = nodo.getDescripcion3();
                    lista = nodo.getL3();
                    break;
                case 4:
                    cant2 = nodo.getCantidad4();
                    desc = nodo.getDescripcion4();
                    lista = nodo.getL4();
                    break;
                case 5:
                    cant2 = nodo.getCantidad5();
                    desc = nodo.getDescripcion5();
                    lista = nodo.getL5();
                    break;
                case 6:
                    cant2 = nodo.getCantidad6();
                    desc = nodo.getDescripcion6();
                    lista = nodo.getL6();
                    break;
                case 7:
                    cant2 = nodo.getCantidad7();
                    desc = nodo.getDescripcion7();
                    lista = nodo.getL7();
                    break;
                case 8:
                    cant2 = nodo.getCantidad8();
                    desc = nodo.getDescripcion8();
                    lista = nodo.getL8();
                    break;
            }

            String tipo = "";
            try {
                if (nodo.getTipoProducto().equals("IMEI")) {
                    tipo = "Imei";
                } else if (nodo.getTipoProducto().equals("Fecha/Lote")) {
                    tipo = "Fecha/Lote";
                } else if (nodo.getTipoProducto().equals("Color")) {
                    tipo = "Color";
                } else if (nodo.getTipoProducto().equals("Serial")) {
                    tipo = "Serial";
                } else {
                    tipo = "";
                }
            } catch (Exception e) {
            }

            if (!tipo.equals("") && imei.equals("") && lote.equals("")) {

                VistaMovimientoDetalleProducto compraDetallada = new VistaMovimientoDetalleProducto(null, true, nodo, null, "Salida", "traslados", BigDecimal.ZERO);
                compraDetallada.setLocationRelativeTo(null);
                compraDetallada.setVisible(true);
                return;
            } else {
                BigDecimal costoPonderado = BigDecimal.ZERO;
                try {
                    UltimoPonderado ultimoPonderado = servicioActualizacionPonderado.obtenerUltimoPonderado(nodo.getIdSistema());
                    costoPonderado = ultimoPonderado.getNuevoPonderado();
                } catch (SQLException ex) {
                    Logger.getLogger(VistaInventarioInicial.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                String cant = nodo.getFisicoInventario().replace(".", ",");
                Double res = 0.0;

                try {
                    res = Double.parseDouble(cant.replace(",", ".")) - Double.parseDouble(cantidad);
                } catch (Exception e) {
                    try {
                        res = big.getBigDecimal(cant.replace(",", ".")).subtract(big.getBigDecimal(cantidad.replace(",", "."))).doubleValue();
                    } catch (Exception ex) {
                        res = Double.parseDouble(cant.replace(",", ".")) - Integer.parseInt(cantidad);
                    }
                }

                BigDecimal listaPrecio = big.getBigDecimal(nodo.getL1());
                BigDecimal iva = big.getBigDecimal(nodo.getIva());

                if (iva.compareTo(BigDecimal.ZERO) > 0) {
                    iva = listaPrecio.multiply(iva);
                    iva = iva.divide(big.getBigDecimal("100"), 3, RoundingMode.CEILING);
                }

                modeloPro.addRow(new Object[]{nodo.getIdSistema(), nodo.getDescripcion(), big.setMoneda(listaPrecio),
                    cantidad, big.setMoneda(big.getBigDecimal(listaPrecio).multiply(big.getBigDecimal(cantidad)).add(iva)), imei, "", big.setMoneda(costoPonderado), idProd, "",
                    (big.getBigDecimal(cant2).multiply(big.getMoneda(cantidad))), plu, cant, res, big.setMoneda(iva.multiply(big.getBigDecimal(cantidad)))});

                tblProductos.setColumnSelectionInterval(9, 9);
                tblProductos.setRowSelectionInterval(tblProductos.getRowCount() - 1, tblProductos.getRowCount() - 1);
                tblProductos.editCellAt(tblProductos.getRowCount() - 1, 9);
                tblProductos.transferFocus();

                cargarTotales();
                txtCodProducto.setText("");
                txtCant.setText(datos[87].toString());
                return;
            }
        }

        ventanaProductos(codigo);
    }

    public void cargarTotales() {
        BigDecimal total = BigDecimal.ZERO;
        for (int i = 0; i < tblProductos.getRowCount(); i++) {
            total = total.add(big.getMoneda(tblProductos.getValueAt(i, 4).toString()));
        }
        txtValor.setText(big.setMoneda(total));
    }

    public void consultarMaestros() {
        datos = instancias.getSql().getDatosMaestra();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBusProd;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lbNit;
    private javax.swing.JLabel lbNit5;
    private javax.swing.JLabel lbNit7;
    private javax.swing.JLabel lbNit8;
    private javax.swing.JLabel lbTitulo5;
    private javax.swing.JPanel pnlFormulario;
    private javax.swing.JScrollPane scrFormulario;
    private javax.swing.JScrollPane scrProductos;
    private javax.swing.JScrollPane scrProductos1;
    private javax.swing.JTable tblProductos;
    private javax.swing.JTable tblProductos1;
    private javax.swing.JTextField txtCant;
    private javax.swing.JTextField txtCodProducto;
    private javax.swing.JLabel txtValor;
    // End of variables declaration//GEN-END:variables
}
