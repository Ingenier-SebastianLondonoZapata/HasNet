package formularios.Ventas;

import clases.Instancias;
import clases.metodosGenerales;
import clases.productos.ndProducto;
import Modelo.Terceros.ModeloContacto;
import Vista.Productos.VistaBuscadorProductos;
import formularios.terceros.buscClientes;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;

public class infRepAnulas extends javax.swing.JPanel {

    metodosGenerales metodos = new metodosGenerales();
    private Instancias instancias;
 
    public infRepAnulas() {
        initComponents();

        dtInicio.setFormat(2);
        dtFinal.setFormat(2);
        instancias = Instancias.getInstancias();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        scrFormulario = new javax.swing.JScrollPane();
        pnlFormulario = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        lbNit1 = new javax.swing.JLabel();
        txtCliente = new javax.swing.JTextField();
        lbPlaca = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        btnAggCliente = new javax.swing.JButton();
        btnRemCliente = new javax.swing.JButton();
        btnAggProd = new javax.swing.JButton();
        btnRemProd = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        cmbCientes = new javax.swing.JComboBox();
        cmbProducto = new javax.swing.JComboBox();
        jPanel6 = new javax.swing.JPanel();
        lbNit9 = new javax.swing.JLabel();
        dtInicio = new datechooser.beans.DateChooserCombo();
        dtFinal = new datechooser.beans.DateChooserCombo();
        lbNit12 = new javax.swing.JLabel();
        btnImpresa = new javax.swing.JRadioButton();
        btnExcel = new javax.swing.JRadioButton();
        lbNit11 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        pnlFormulario.setBackground(new java.awt.Color(255, 255, 255));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        lbNit1.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit1.setText("Cliente");

        txtCliente.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtClienteKeyReleased(evt);
            }
        });

        lbPlaca.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbPlaca.setText("Producto:");

        txtCodigo.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbNit1, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
                    .addComponent(txtCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
                    .addComponent(lbPlaca, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(lbNit1)
                .addGap(0, 0, 0)
                .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(lbPlaca)
                .addGap(0, 0, 0)
                .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        btnAggCliente.setBackground(new java.awt.Color(204, 204, 204));
        btnAggCliente.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnAggCliente.setForeground(new java.awt.Color(255, 255, 255));
        btnAggCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/agregar.png"))); // NOI18N
        btnAggCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAggCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAggClienteActionPerformed(evt);
            }
        });

        btnRemCliente.setBackground(new java.awt.Color(204, 204, 204));
        btnRemCliente.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnRemCliente.setForeground(new java.awt.Color(153, 0, 0));
        btnRemCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/borrar2.png"))); // NOI18N
        btnRemCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRemCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemClienteActionPerformed(evt);
            }
        });

        btnAggProd.setBackground(new java.awt.Color(204, 204, 204));
        btnAggProd.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnAggProd.setForeground(new java.awt.Color(255, 255, 255));
        btnAggProd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/agregar.png"))); // NOI18N
        btnAggProd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAggProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAggProdActionPerformed(evt);
            }
        });

        btnRemProd.setBackground(new java.awt.Color(204, 204, 204));
        btnRemProd.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnRemProd.setForeground(new java.awt.Color(153, 0, 0));
        btnRemProd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/borrar2.png"))); // NOI18N
        btnRemProd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRemProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemProdActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(btnAggCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRemCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(btnAggProd, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRemProd, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(11, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAggCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRemCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAggProd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRemProd))
                .addContainerGap())
        );

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

        cmbCientes.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbCientes, 0, 214, Short.MAX_VALUE)
                    .addComponent(cmbProducto, 0, 214, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(cmbCientes, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cmbProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        lbNit9.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lbNit9.setText("Fecha final");

        dtInicio.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 12));

        dtFinal.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 12));

        lbNit12.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lbNit12.setText("Versión:");

        btnImpresa.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(btnImpresa);
        btnImpresa.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnImpresa.setSelected(true);
        btnImpresa.setText("En pantalla");
        btnImpresa.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnImpresa.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        btnExcel.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(btnExcel);
        btnExcel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnExcel.setText("Hoja de calculo");
        btnExcel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnExcel.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        lbNit11.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lbNit11.setText("Fecha inicial:");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbNit12)
                    .addComponent(lbNit11, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnImpresa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dtInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(35, 35, 35)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(lbNit9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dtFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnExcel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(dtInicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnImpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lbNit9, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                                .addComponent(dtFinal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(lbNit11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(lbNit12))
                            .addComponent(btnExcel))))
                .addContainerGap())
        );

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jButton6.setBackground(new java.awt.Color(46, 204, 113));
        jButton6.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jButton6.setText("Generar");
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("El reporte se basa en la fecha de la factura anulada");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout pnlFormularioLayout = new javax.swing.GroupLayout(pnlFormulario);
        pnlFormulario.setLayout(pnlFormularioLayout);
        pnlFormularioLayout.setHorizontalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlFormularioLayout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        pnlFormularioLayout.setVerticalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        scrFormulario.setViewportView(pnlFormulario);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrFormulario)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrFormulario)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClienteKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cargarCliente(txtCliente.getText());
        }
    }//GEN-LAST:event_txtClienteKeyReleased

    private void txtCodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cargarProducto(txtCodigo.getText());
        }
    }//GEN-LAST:event_txtCodigoKeyReleased

    private void btnAggClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAggClienteActionPerformed
        if (txtCliente.getText().equals("")) {
            metodos.msgError(null, "Cargue primero un cliente");
        } else {

            if (yaEsta(cmbCientes, txtCliente.getText())) {
                metodos.msgAdvertencia(null, "Ya cargo este cliente");
            } else {
                cmbCientes.addItem(txtCliente.getText());
                cmbCientes.setSelectedItem(txtCliente.getText());
            }

            txtCliente.setText("");
        }
    }//GEN-LAST:event_btnAggClienteActionPerformed

    private void btnRemClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemClienteActionPerformed
        cmbCientes.removeItem(cmbCientes.getSelectedItem());
    }//GEN-LAST:event_btnRemClienteActionPerformed

    private void btnAggProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAggProdActionPerformed
        if (txtCodigo.getText().equals("")) {
            metodos.msgError(null, "Cargue primero un producto");
        } else {
            ndProducto nodo = null;

            String CodigoProd = "", codigo = txtCodigo.getText();
            Object[][] listado = instancias.getSql().getCodigosRelacionados(codigo, " where codigo");
            if (listado.length > 0) {
                codigo = listado[0][0].toString();
            }

            nodo = instancias.getSql().getDatosProducto(codigo, "bdProductos");
            if (nodo.getIdSistema() != null) {
                CodigoProd = nodo.getIdSistema();
            }

            if (CodigoProd.equals("")) {
                ventanaProducto(codigo);
                return;
            } else {
                txtCodigo.setText(nodo.getIdSistema());
                if (yaEsta(cmbProducto, txtCodigo.getText())) {
                    metodos.msgAdvertencia(null, "Ya cargo este producto");
                } else {
                    cmbProducto.addItem(txtCodigo.getText());
                    cmbProducto.setSelectedItem(txtCodigo.getText());
                }
            }

            txtCodigo.setText("");
        }
    }//GEN-LAST:event_btnAggProdActionPerformed

    private void btnRemProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemProdActionPerformed
        cmbProducto.removeItem(cmbProducto.getSelectedItem());
    }//GEN-LAST:event_btnRemProdActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        if (metodos.msgPregunta(null, "¿Esta seguro?") == 0) {
            String sql = "where";

            if (cmbCientes.getItemCount() > 0) {
                sql = sql + " (";

                for (int i = 0; i < cmbCientes.getItemCount(); i++) {
                    sql = sql + " cliente='" + cmbCientes.getItemAt(i) + "' OR";
                }

                sql = sql.substring(0, sql.length() - 2) + ") AND";
            }

            if (cmbProducto.getItemCount() > 0) {
                sql = sql + " (";

                for (int i = 0; i < cmbProducto.getItemCount(); i++) {
                    sql = sql + " producto='" + cmbProducto.getItemAt(i) + "' OR";
                }

                sql = sql.substring(0, sql.length() - 2) + ") AND";
            }

            String ini = metodos.desdeDate(dtInicio.getCurrent()), fin = metodos.desdeDate(dtFinal.getCurrent());

            sql = sql + " ((fechaAnulacion)>= '" + ini + "' And (fechaAnulacion) <= '" + fin + "')";

            String enca = "";

            dtInicio.setFormat(0);
            dtFinal.setFormat(0);

            if (dtInicio.getText().equals(dtFinal.getText())) {
                enca = "Anulaciones del " + dtInicio.getText();
            } else {
                enca = "Anulaciones entre el " + dtInicio.getText() + " y el " + dtFinal.getText();
            }

            dtInicio.setFormat(2);
            dtFinal.setFormat(2);

            String tipo = "";

            if (btnExcel.isSelected()) {
                tipo = "xls";
                instancias.setRutaAguardar(metodos.obtenerRuta(null, "Reporte de anulaciones " + metodosGenerales.dia() + " de " + metodosGenerales.mesEnPalabra() + " del " + metodosGenerales.anho()));
            }

            instancias.getReporte().ver_RepAnulas(sql, enca, tipo);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    public void cargarCliente(String nit) {

        ModeloContacto nodo = instancias.getSql().getDatosTercero(nit);

        if (nodo.getId() != null) {
            txtCliente.setText(nodo.getIdSistema());
            btnAggClienteActionPerformed(null);
            return;
        }
        ventanaTerceros(nit);
    }

    public void ventanaTerceros(String nit) {
        buscClientes buscar = new buscClientes(instancias.getMenu(), true, false, null, "");
        buscar.setOpc("factura");
        buscar.setLocationRelativeTo(null);
        instancias.setBusClientes(buscar);
        instancias.setCampoActual(txtCliente);
        txtCliente.requestFocus();
        buscar.noEncontrado(nit);
        buscar.show();
    }

    public void cargarProducto(String codigo) {
        ndProducto nodo = null;

        String CodigoProd = "";
        if (codigo.equals("")) {
            CodigoProd = "";
        } else {
            Object[][] listado = instancias.getSql().getCodigosRelacionados(codigo, " where codigo");
            if (listado.length > 0) {
                codigo = listado[0][0].toString();
            }

            nodo = instancias.getSql().getDatosProducto(codigo, "bdProductos");
            if (nodo.getIdSistema() != null) {
                CodigoProd = nodo.getIdSistema();
            }
        }

        if (!CodigoProd.equals("")) {
            txtCodigo.setText(nodo.getIdSistema());
            btnAggProdActionPerformed(null);
            return;
        }

        ventanaProducto(codigo);
    }

    public void ventanaProducto(String nit) {
        VistaBuscadorProductos buscar = new VistaBuscadorProductos(instancias.getMenu(), true, false, "", "productos1");
        buscar.setLocationRelativeTo(null);
        instancias.setBusProductos(buscar);
        instancias.setCampoActual(txtCodigo);
        txtCodigo.requestFocus();
//        buscar.setInstancia(instancias);
        buscar.noEncontrado(nit);
        buscar.show();
    }

    public boolean yaEsta(JComboBox combo, String dato) {
        boolean sw = false;

        for (int i = 0; i < combo.getItemCount(); i++) {
            if (((String) combo.getItemAt(i)).equals(dato)) {
                return true;
            }
        }

        return sw;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAggCliente;
    private javax.swing.JButton btnAggProd;
    private javax.swing.JRadioButton btnExcel;
    private javax.swing.JRadioButton btnImpresa;
    private javax.swing.JButton btnRemCliente;
    private javax.swing.JButton btnRemProd;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cmbCientes;
    private javax.swing.JComboBox cmbProducto;
    private datechooser.beans.DateChooserCombo dtFinal;
    private datechooser.beans.DateChooserCombo dtInicio;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbNit1;
    private javax.swing.JLabel lbNit11;
    private javax.swing.JLabel lbNit12;
    private javax.swing.JLabel lbNit9;
    private javax.swing.JLabel lbPlaca;
    private javax.swing.JPanel pnlFormulario;
    private javax.swing.JScrollPane scrFormulario;
    private javax.swing.JTextField txtCliente;
    private javax.swing.JTextField txtCodigo;
    // End of variables declaration//GEN-END:variables
}
