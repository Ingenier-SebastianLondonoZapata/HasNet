package formularios.creditos;

import clases.Instancias;
import clases.Medico.ndEps;
import Utilidades.BaseDatos.SQL;
import clases.big;
import clases.metodosGenerales;
import Modelo.Terceros.ModeloContacto;
import formularios.Labotario.infAcidoUrico;
import formularios.terceros.buscClientes;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class infEmpresas extends javax.swing.JInternalFrame {

    metodosGenerales metodos = new metodosGenerales();
    private Instancias instancias;
    SQL sql= new SQL();;

    private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension dimBarra = null;
    Object[][] grupos;
    DefaultTableModel modelo;

    public infEmpresas() {
        initComponents();

        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
        dimBarra = Barra.getPreferredSize();
        Barra.setSize(0, 0);
        Barra.setPreferredSize(new Dimension(0, 0));
        setBorder(null);
        repaint();

        modelo = (DefaultTableModel) tblProductos.getModel();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrFormulario = new javax.swing.JScrollPane();
        pnlFormulario = new javax.swing.JPanel();
        lbNit10 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        txtId = new javax.swing.JTextField();
        lbRazon1 = new javax.swing.JLabel();
        lbRazon4 = new javax.swing.JLabel();
        txtDato2 = new javax.swing.JTextField();
        btnBuscTerceros = new javax.swing.JButton();
        lbRazon2 = new javax.swing.JLabel();
        txtRazon = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btnActualizar1 = new javax.swing.JButton();
        btnGuardar1 = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProductos = new javax.swing.JTable();

        setTitle("Ayuda Diagnostica");

        pnlFormulario.setBackground(new java.awt.Color(255, 255, 255));

        lbNit10.setFont(new java.awt.Font("Bell MT", 1, 36)); // NOI18N
        lbNit10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit10.setText("CONVENIOS");

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        txtId.setName("Codigo"); // NOI18N
        txtId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIdKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIdKeyTyped(evt);
            }
        });

        lbRazon1.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbRazon1.setText("Nit:");

        lbRazon4.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbRazon4.setText("Convenio:");

        txtDato2.setEditable(false);

        btnBuscTerceros.setBackground(new java.awt.Color(247, 196, 69));
        btnBuscTerceros.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnBuscTerceros.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscTerceros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cliente.png"))); // NOI18N
        btnBuscTerceros.setText("CLIENTES      ");
        btnBuscTerceros.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscTerceros.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnBuscTerceros.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnBuscTerceros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscTercerosActionPerformed(evt);
            }
        });

        lbRazon2.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbRazon2.setText("Razon social:");

        txtRazon.setEditable(false);
        txtRazon.setName("Codigo"); // NOI18N
        txtRazon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtRazonKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRazonKeyTyped(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 13)); // NOI18N
        jLabel1.setText("Los porcentajes podran ser modificados, solo despues de guardar el convenio.");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbRazon2, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                    .addComponent(lbRazon4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbRazon1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtId)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(txtDato2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBuscTerceros, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtRazon))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(txtId)
                        .addGap(18, 18, 18))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(lbRazon1)
                        .addGap(9, 9, 9)))
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtRazon)
                    .addComponent(lbRazon2))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbRazon4)
                            .addComponent(txtDato2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(12, 12, 12))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addComponent(btnBuscTerceros)
                        .addContainerGap())))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        btnActualizar1.setBackground(new java.awt.Color(0, 0, 0));
        btnActualizar1.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnActualizar1.setForeground(new java.awt.Color(255, 255, 255));
        btnActualizar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/actualizar.png"))); // NOI18N
        btnActualizar1.setText("MODIFICAR  ");
        btnActualizar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnActualizar1.setEnabled(false);
        btnActualizar1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnActualizar1.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnActualizar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizar1ActionPerformed(evt);
            }
        });

        btnGuardar1.setBackground(new java.awt.Color(0, 153, 153));
        btnGuardar1.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnGuardar1.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
        btnGuardar1.setText("GUARDAR       ");
        btnGuardar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnGuardar1.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnGuardar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardar1ActionPerformed(evt);
            }
        });
        btnGuardar1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                btnGuardar1KeyReleased(evt);
            }
        });

        btnBuscar.setBackground(new java.awt.Color(204, 102, 255));
        btnBuscar.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar.png"))); // NOI18N
        btnBuscar.setText("BUSCAR        ");
        btnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnBuscar.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnNuevo.setBackground(new java.awt.Color(255, 204, 51));
        btnNuevo.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnNuevo.setForeground(new java.awt.Color(255, 255, 255));
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/nuevo.png"))); // NOI18N
        btnNuevo.setText("NUEVO         ");
        btnNuevo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNuevo.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnNuevo.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBuscar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardar1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnActualizar1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnBuscar)
                        .addComponent(btnNuevo))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnGuardar1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnActualizar1)))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        tblProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Producto", "Porcentaje", "Porcentaje convenio"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblProductos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblProductosKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblProductos);
        if (tblProductos.getColumnModel().getColumnCount() > 0) {
            tblProductos.getColumnModel().getColumn(0).setMinWidth(100);
            tblProductos.getColumnModel().getColumn(0).setPreferredWidth(100);
            tblProductos.getColumnModel().getColumn(0).setMaxWidth(100);
            tblProductos.getColumnModel().getColumn(2).setMinWidth(150);
            tblProductos.getColumnModel().getColumn(2).setPreferredWidth(150);
            tblProductos.getColumnModel().getColumn(2).setMaxWidth(150);
            tblProductos.getColumnModel().getColumn(3).setMinWidth(150);
            tblProductos.getColumnModel().getColumn(3).setPreferredWidth(150);
            tblProductos.getColumnModel().getColumn(3).setMaxWidth(150);
        }

        javax.swing.GroupLayout pnlFormularioLayout = new javax.swing.GroupLayout(pnlFormulario);
        pnlFormulario.setLayout(pnlFormularioLayout);
        pnlFormularioLayout.setHorizontalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbNit10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlFormularioLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        pnlFormularioLayout.setVerticalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addComponent(lbNit10, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        scrFormulario.setViewportView(pnlFormulario);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrFormulario)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(scrFormulario)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtIdKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdKeyTyped

    }//GEN-LAST:event_txtIdKeyTyped

    private void txtIdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            ndEps nodo = instancias.getSql().getDatosEps(txtId.getText());

            if (nodo.getId() == null) {
                ModeloContacto nodoDos = instancias.getSql().getDatosTercero(txtId.getText());

                if (nodoDos.getId() == null) {
                    metodos.msgAdvertencia(this, "El Cliente no existe");
                } else {
                    txtRazon.setText(nodoDos.getNombre());
                }

            } else {
                cargarEps(txtId.getText());
            }
        } else {
            txtRazon.setText("");
        }
    }//GEN-LAST:event_txtIdKeyReleased

    private void btnGuardar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar1ActionPerformed
        Object[] campos = {txtId};
        String faltantes = metodos.camposVacios(campos);
        Object[] campos2 = {""};
        String faltantes2 = metodos.camposVacios(campos2);

        ModeloContacto nodoDos = instancias.getSql().getDatosTercero(txtId.getText());

        if (nodoDos.getId() != null) {
            txtRazon.setText(nodoDos.getNombre());
        }

        if (txtRazon.getText().equals("")) {
            metodos.msgAdvertencia(this, "Cargue un cliente valido");
            ventanaTerceros("");
            return;
        } else if (!faltantes.equals("")) {
            metodos.msgAdvertencia(this, "No puede continuar, faltan los siguientes campos: " + faltantes);
            return;
        } else if (!faltantes2.equals("")) {
            metodos.msgAdvertencia(this, "Faltan los siguientes campos: " + faltantes2);
        }
        if (metodos.msgPregunta(this, "¿Desea continuar?") == 0) {

            try {
                ndEps nodo = instancias.getSql().getDatosEps(txtId.getText());
                if (nodo.getId() != null) {
                    metodos.msgError(this, "La empresa ya existe");
                    txtId.requestFocus();
                    return;
                }

                Object[] vector = {txtId.getText(), "", txtDato2.getText(), "", "",
                    "", "", big.getMoneda("0"), big.getMoneda("0")};

                nodo = metodos.llenarEps(vector);
                if (!instancias.getSql().agregarEps(nodo)) {
                    metodos.msgError(this, "Hubo un problema al guardar La empresa");
                    return;
                }

                //CAMBIAR CONSECUTIVO INGRESO
                if (!sql.aumentarConsecutivo("CONVENIO", Integer.parseInt((String) sql.getNumConsecutivo("CONVENIO")[0]) + 1)) {
                    metodos.msgError(this, "Hubo un problema al guardar en el consecutivo del ingreso");
                }

                metodos.msgExito(this, "Empresa registrado con éxito");

                btnNuevoActionPerformed(evt);
            } catch (Exception e) {
                metodos.msgError(this, "Hubo un problema al guardar La empresa");
            }
        }
    }//GEN-LAST:event_btnGuardar1ActionPerformed

    private void btnGuardar1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnGuardar1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGuardar1KeyReleased

    private void btnActualizar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizar1ActionPerformed
        Object[] campos = {txtId};
        String faltantes = metodos.camposVacios(campos);
        Object[] campos2 = {""};
        String faltantes2 = metodos.camposVacios(campos2);
        if (!faltantes.equals("")) {
            metodos.msgAdvertencia(this, "No puede continuar, faltan los siguientes campos: " + faltantes);
            return;
        } else if (!faltantes2.equals("")) {
            metodos.msgAdvertencia(this, "Faltan los siguientes campos: " + faltantes2);
        }
        if (metodos.msgPregunta(this, "¿Desea continuar?") == 0) {

            try {

                Object[] vector = {txtId.getText(), "", txtDato2.getText(), "", "",
                    "", "", big.getMoneda("0"), big.getMoneda("0")};

                ndEps nodo = metodos.llenarEps(vector);
                if (!instancias.getSql().modificarEps(nodo)) {
                    metodos.msgError(this, "Hubo un problema al modificar La empresa");
                    return;
                }

                tblProductos.removeEditor();

                int j = modelo.getRowCount();

                for (int i = 0; i < j; i++) {

                    try {
                        modelo.setValueAt(big.setMoneda(big.getMoneda(modelo.getValueAt(i, 3).toString())), i, 3);
                    } catch (Exception e) {
                        modelo.setValueAt("0", i, 3);
                    }

                    if (!instancias.getSql().modificarLista(modelo.getValueAt(i, 0).toString(), txtDato2.getText(), big.getMoneda(modelo.getValueAt(i, 3).toString()).toString())) {
                        metodos.msgError(this, "Hubo un error al modificar el porcentaje de " + modelo.getValueAt(i, 1).toString());
                    }
                }

                metodos.msgExito(this, "Eempresa modificado con éxito");

                btnNuevoActionPerformed(evt);
            } catch (Exception e) {
                metodos.msgError(this, "Hubo un problema al modificar La empresa");
            }
        }
    }//GEN-LAST:event_btnActualizar1ActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        cargarEps(txtId.getText());
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        txtId.setEditable(true);

        for (int x = 0; x < jPanel11.getComponentCount(); x++) {
            if (jPanel11.getComponent(x) instanceof JTextField) {
                JTextField textField = (JTextField) jPanel11.getComponent(x);
                textField.setText("");
            }
        }

        int j = modelo.getRowCount();

        for (int i = 0; i < j; i++) {
            modelo.removeRow(0);
        }

        btnActualizar1.setEnabled(false);
        btnGuardar1.setEnabled(true);
        txtDato2.setText("" + sql.getNumConsecutivo("CONVENIO")[0]);

    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnBuscTercerosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscTercerosActionPerformed
        ventanaTerceros("");
    }//GEN-LAST:event_btnBuscTercerosActionPerformed

    private void txtRazonKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRazonKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRazonKeyReleased

    private void txtRazonKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRazonKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRazonKeyTyped

    private void tblProductosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblProductosKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            tblProductos.removeEditor();

            int x = tblProductos.getSelectedRow();

            try {
                tblProductos.setValueAt(big.setMoneda(big.getMoneda(tblProductos.getValueAt(x, 3).toString())), x, 3);

                if (tblProductos.getRowCount() - 1 > x) {
                    tblProductos.editCellAt(x + 1, 3);
                    tblProductos.setRowSelectionInterval(x + 1, x + 1);
                }
            } catch (Exception e) {
                tblProductos.setValueAt("$ 0", x, 3);
                tblProductos.editCellAt(x, 3);
                tblProductos.setRowSelectionInterval(x, x);
            }
            tblProductos.transferFocus();

        }
    }//GEN-LAST:event_tblProductosKeyReleased

    public void setInstancias(Instancias instancias) {
        this.instancias = instancias;
        btnNuevoActionPerformed(null);
//        instancias.getClientes().activarEmpresas();
    }

    public void cargarEps(String Id) {

        ndEps nodo = instancias.getSql().getDatosEps(Id);

        if (nodo.getId() != null) {

            ModeloContacto nodoDos = instancias.getSql().getDatosTercero(txtId.getText());

            if (nodoDos.getId() != null) {
                txtRazon.setText(nodoDos.getNombre());
            }

            txtDato2.setText(nodo.getDato2());
            btnActualizar1.setEnabled(true);
            btnGuardar1.setEnabled(false);
            txtId.setEditable(false);

            int x = metodos.msgPregunta(this, "Cargar los porcentajes de convenio igual a porcentajes originales\nNota: Recomendable primera vez");

            tblProductos.setModel(instancias.getSql().getPorcentajesConvenio(nodo.getDato2()));
            modelo = (DefaultTableModel) tblProductos.getModel();

            int j = modelo.getRowCount();

            for (int i = 0; i < j; i++) {

                try {
                    modelo.setValueAt(big.setMoneda(big.getBigDecimal(modelo.getValueAt(i, 2))), i, 2);
                } catch (Exception e) {
                    modelo.setValueAt("0", i, 2);
                }

                if (x == 0) {
                    modelo.setValueAt(modelo.getValueAt(i, 2), i, 3);
                } else {
                    try {
                        modelo.setValueAt(big.setNumero(big.getBigDecimal(modelo.getValueAt(i, 3))), i, 3);
                    } catch (Exception e) {
                        modelo.setValueAt("0", i, 3);
                    }
                }

            }

            return;
        }

        metodos.msgError(this, "La empresa no existe");
    }

    public void ventanaTerceros(String nit) {
        buscClientes buscar = new buscClientes(instancias.getMenu(), rootPaneCheckingEnabled, false, null, "");
        buscar.setLocationRelativeTo(null);
        instancias.setBusClientes(buscar);
        instancias.setCampoActual(txtId);
        txtId.requestFocus();
        buscar.noEncontrado(nit);
        buscar.show();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar1;
    private javax.swing.JButton btnBuscTerceros;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnGuardar1;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbNit10;
    private javax.swing.JLabel lbRazon1;
    private javax.swing.JLabel lbRazon2;
    private javax.swing.JLabel lbRazon4;
    private javax.swing.JPanel pnlFormulario;
    private javax.swing.JScrollPane scrFormulario;
    private javax.swing.JTable tblProductos;
    private javax.swing.JTextField txtDato2;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtRazon;
    // End of variables declaration//GEN-END:variables

    private void cargando(String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
