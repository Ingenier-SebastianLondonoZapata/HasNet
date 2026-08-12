package formularios.Veterinario;

import clases.Instancias;
import Utilidades.BaseDatos.SQL;
import clases.metodosGenerales;
import clases.productos.ndProducto;
import Vista.Productos.VistaBuscadorProductos;
import java.awt.event.KeyEvent;

public class dlgAplicacionMedicamentos extends javax.swing.JDialog {

    String idMedicamento = "";
    String idHospitalizacion = "";
    private Instancias instancias;
    metodosGenerales metodos = new metodosGenerales();

    public dlgAplicacionMedicamentos(java.awt.Frame parent, boolean modal, Object[] horario, String medicamento, String id, String idHosp) {
        super(parent, modal);
        initComponents();

        this.setLocationRelativeTo(null);
        this.setTitle("Administrar...");

        instancias = Instancias.getInstancias();
        txtId.setVisible(false);
        lbTitulo.setText(medicamento);
        idMedicamento = id;
        idHospitalizacion = idHosp;

        for (int i = 0; i < horario.length; i++) {
            cmbHoras.addItem(horario[i]);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grpEncabezados = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        lbTitulo = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtNota = new javax.swing.JTextArea();
        btnGuardar = new javax.swing.JButton();
        lbNit2 = new javax.swing.JLabel();
        cmbHoras = new javax.swing.JComboBox();
        txtMedicamento = new javax.swing.JTextField();
        lbProducto = new javax.swing.JLabel();
        btnBusProd = new javax.swing.JButton();
        lbProducto1 = new javax.swing.JLabel();
        txtCant = new javax.swing.JTextField();
        txtId = new javax.swing.JTextField();
        lbNit4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        lbTitulo.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        lbTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitulo.setText("# NOMBRE MEDICAMENTO");

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        txtNota.setColumns(20);
        txtNota.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        txtNota.setRows(2);
        txtNota.setText("NOTA:");
        jScrollPane2.setViewportView(txtNota);

        btnGuardar.setBackground(new java.awt.Color(46, 204, 113));
        btnGuardar.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
        btnGuardar.setText("GUARDAR ");
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnGuardar.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        lbNit2.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lbNit2.setText("Horas establecidas:");

        cmbHoras.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N

        txtMedicamento.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        txtMedicamento.setName("combo"); // NOI18N
        txtMedicamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMedicamentoActionPerformed(evt);
            }
        });
        txtMedicamento.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtMedicamentoFocusGained(evt);
            }
        });
        txtMedicamento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMedicamentoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMedicamentoKeyReleased(evt);
            }
        });

        lbProducto.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lbProducto.setText("Medicamento:");
        lbProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lbProductoKeyReleased(evt);
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

        lbProducto1.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lbProducto1.setText("Cant:");
        lbProducto1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lbProducto1KeyReleased(evt);
            }
        });

        txtCant.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        txtCant.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCant.setText("1");
        txtCant.setName("combo"); // NOI18N
        txtCant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantActionPerformed(evt);
            }
        });
        txtCant.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCantFocusGained(evt);
            }
        });
        txtCant.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCantKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCantKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantKeyTyped(evt);
            }
        });

        txtId.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        txtId.setName("combo"); // NOI18N
        txtId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdActionPerformed(evt);
            }
        });
        txtId.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtIdFocusGained(evt);
            }
        });
        txtId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtIdKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIdKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(lbNit2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbHoras, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(lbProducto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtMedicamento, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, 0)
                        .addComponent(btnBusProd, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(lbProducto1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCant, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnGuardar)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(lbNit2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmbHoras, javax.swing.GroupLayout.Alignment.LEADING)))
                .addGap(24, 24, 24)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtMedicamento, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(lbProducto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBusProd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbProducto1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCant, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(6, 6, 6)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnGuardar)
                .addGap(5, 5, 5))
        );

        lbNit4.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        lbNit4.setText("X");
        lbNit4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbNit4MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lbTitulo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(20, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lbNit4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(lbNit4)
                .addGap(4, 4, 4)
                .addComponent(lbTitulo)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (txtId.getText().equals("") || txtMedicamento.getText().equals("")) {
            metodos.msgAdvertencia(null, "No ha cargado ningún medicamento");
        }

        if (metodos.msgPregunta(null, "¿Desea continuar?") == 0) {
            if (!instancias.getSql().agregarMedicamentosAplicados(idHospitalizacion, idMedicamento, lbTitulo.getText(), txtCant.getText(),
                    txtId.getText(), instancias.getUsuario(), metodosGenerales.fecha(), cmbHoras.getSelectedItem().toString(),
                    txtNota.getText(), metodosGenerales.fecha(), metodosGenerales.hora())) {
                metodos.msgError(null, "Hubo un problema al guardar el aplique del medicamento");
                return;
            } else {
                String campo = "";
                if (cmbHoras.getSelectedItem().equals("1:00")) {
                    campo = "estadoUna";
                } else if (cmbHoras.getSelectedItem().equals("2:00")) {
                    campo = "estadoDos";
                } else if (cmbHoras.getSelectedItem().equals("3:00")) {
                    campo = "estadoTres";
                } else if (cmbHoras.getSelectedItem().equals("4:00")) {
                    campo = "estadoCuatro";
                } else if (cmbHoras.getSelectedItem().equals("5:00")) {
                    campo = "estadoCinco";
                } else if (cmbHoras.getSelectedItem().equals("6:00")) {
                    campo = "estadoSeis";
                } else if (cmbHoras.getSelectedItem().equals("7:00")) {
                    campo = "estadoSiete";
                } else if (cmbHoras.getSelectedItem().equals("8:00")) {
                    campo = "estadoOcho";
                } else if (cmbHoras.getSelectedItem().equals("9:00")) {
                    campo = "estadoNueve";
                } else if (cmbHoras.getSelectedItem().equals("10:00")) {
                    campo = "estadoDiez";
                } else if (cmbHoras.getSelectedItem().equals("11:00")) {
                    campo = "estadoOnce";
                } else if (cmbHoras.getSelectedItem().equals("12:00")) {
                    campo = "estadoDoce";
                } else if (cmbHoras.getSelectedItem().equals("13:00")) {
                    campo = "estadoTrece";
                } else if (cmbHoras.getSelectedItem().equals("14:00")) {
                    campo = "estadoCatorce";
                } else if (cmbHoras.getSelectedItem().equals("15:00")) {
                    campo = "estadoQuince";
                } else if (cmbHoras.getSelectedItem().equals("16:00")) {
                    campo = "estadoDiesiseis";
                } else if (cmbHoras.getSelectedItem().equals("17:00")) {
                    campo = "estadoDiesisiete";
                } else if (cmbHoras.getSelectedItem().equals("18:00")) {
                    campo = "estadoDiesiocho";
                } else if (cmbHoras.getSelectedItem().equals("19:00")) {
                    campo = "estadoDiesinueve";
                } else if (cmbHoras.getSelectedItem().equals("20:00")) {
                    campo = "estadoVeinte";
                } else if (cmbHoras.getSelectedItem().equals("21:00")) {
                    campo = "estadoVeintiuno";
                } else if (cmbHoras.getSelectedItem().equals("22:00")) {
                    campo = "estadoVeintidos";
                } else if (cmbHoras.getSelectedItem().equals("23:00")) {
                    campo = "estadoVeintitres";
                } else if (cmbHoras.getSelectedItem().equals("24:00")) {
                    campo = "estadoVeinticuatro";
                }

                instancias.getHospitalizacion().cargarProductoHospitalizacion(txtId.getText(), txtCant.getText());
                instancias.getSql().cambiarEstadoDeMedicamento(idHospitalizacion, idMedicamento, campo);
                metodos.msgExito(null, "Medicamento aplicado con exito !");
                dispose();
            }
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void lbNit4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbNit4MouseClicked
        this.dispose();
    }//GEN-LAST:event_lbNit4MouseClicked

    private void txtMedicamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMedicamentoActionPerformed

    }//GEN-LAST:event_txtMedicamentoActionPerformed

    private void txtMedicamentoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMedicamentoFocusGained

    }//GEN-LAST:event_txtMedicamentoFocusGained

    private void txtMedicamentoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMedicamentoKeyPressed

    }//GEN-LAST:event_txtMedicamentoKeyPressed

    private void txtMedicamentoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMedicamentoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String codigo = txtMedicamento.getText().replace("'", "//");
            String cant = txtCant.getText();

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
                txtId.setText(nodo.getIdSistema());
                txtMedicamento.setText(nodo.getDescripcion());
            } else {
                ventanaProductos(codigo);
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            btnGuardar.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_MULTIPLY) {
            double cantidad = 1;
            try {
                cantidad = Double.parseDouble(txtMedicamento.getText().replace("*", ""));
            } catch (Exception e) {
            }
            txtCant.setText(String.valueOf(cantidad));
            if (txtCant.getText().substring(txtCant.getText().length() - 1, txtCant.getText().length()).equals("0")) {
                txtCant.setText(txtCant.getText().substring(0, txtCant.getText().length() - 2));
            }
            txtMedicamento.setText("");
        }
    }//GEN-LAST:event_txtMedicamentoKeyReleased

    private void lbProductoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lbProductoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtMedicamento.requestFocus();
        }
    }//GEN-LAST:event_lbProductoKeyReleased

    private void btnBusProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBusProdActionPerformed
        ventanaProductos("");
    }//GEN-LAST:event_btnBusProdActionPerformed

    private void lbProducto1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lbProducto1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_lbProducto1KeyReleased

    private void txtCantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantActionPerformed

    private void txtCantFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCantFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantFocusGained

    private void txtCantKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantKeyPressed

    private void txtCantKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantKeyReleased

    private void txtIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdActionPerformed

    private void txtIdFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtIdFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdFocusGained

    private void txtIdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdKeyPressed

    private void txtIdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdKeyReleased

    private void txtCantKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtCantKeyTyped

    public void ventanaProductos(String codigo) {
        VistaBuscadorProductos buscar = new VistaBuscadorProductos(null, true, false, "");
        buscar.setLocationRelativeTo(null);
        instancias.setBusProductos(buscar);
        instancias.setCampoActual(txtMedicamento);
        txtMedicamento.requestFocus();
        buscar.noEncontrado(codigo.replace("'", "//"));
        buscar.show();
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
            java.util.logging.Logger.getLogger(dlgAplicacionMedicamentos.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(dlgAplicacionMedicamentos.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(dlgAplicacionMedicamentos.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dlgAplicacionMedicamentos.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                dlgAplicacionMedicamentos dialog = new dlgAplicacionMedicamentos(new javax.swing.JFrame(), true, null, "", "", "");
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
    private javax.swing.JButton btnBusProd;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox cmbHoras;
    private javax.swing.ButtonGroup grpEncabezados;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbNit2;
    private javax.swing.JLabel lbNit4;
    private javax.swing.JLabel lbProducto;
    private javax.swing.JLabel lbProducto1;
    private javax.swing.JLabel lbTitulo;
    private javax.swing.JTextField txtCant;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtMedicamento;
    private javax.swing.JTextArea txtNota;
    // End of variables declaration//GEN-END:variables
}
