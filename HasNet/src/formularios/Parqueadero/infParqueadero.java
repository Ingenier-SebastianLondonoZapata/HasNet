package formularios.Parqueadero;

import clases.IconCellRenderer;
import clases.Instancias;
import clases.Parqueadero.ndParqueadero;
import Utilidades.BaseDatos.SQL;
import clases.productos.ndProducto;
import Modelo.Terceros.ModeloContacto;
import clases.big;
import clases.metodosGenerales;
import formularios.Ventas.dlgPedirContrasena;
import formularios.productos.buscProductos;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class infParqueadero extends javax.swing.JInternalFrame {

    metodosGenerales metodos = new metodosGenerales();
    private Instancias instancias;
    TableRowSorter modeloOrdenado;

    private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension dimBarra = null;

    public infParqueadero() {

        initComponents();

        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
        dimBarra = Barra.getPreferredSize();
        Barra.setSize(0, 0);
        Barra.setPreferredSize(new Dimension(0, 0));
        setBorder(null);
        repaint();
        this.instancias = Instancias.getInstancias();
        tblServicios.setDefaultRenderer(Object.class, new IconCellRenderer());
        actualizarTablas();

        btnCascos.setVisible(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        popModificar = new javax.swing.JMenuItem();
        popAnular = new javax.swing.JMenuItem();
        buttonGroup1 = new javax.swing.ButtonGroup();
        scrFormulario = new javax.swing.JScrollPane();
        pnlFormulario = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblCupo = new javax.swing.JTable();
        btnCascos = new javax.swing.JButton();
        btnGuardar3 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblServicios = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblParqueo = new javax.swing.JTable();
        txtPlaca = new javax.swing.JTextField();

        popModificar.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        popModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/actualizar pequeño.png"))); // NOI18N
        popModificar.setText("Modificar");
        popModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                popModificarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(popModificar);

        popAnular.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/eliminar-cancelar-icono-4935-16.png"))); // NOI18N
        popAnular.setText("Eliminar");
        popAnular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                popAnularActionPerformed(evt);
            }
        });
        jPopupMenu1.add(popAnular);

        setTitle("Factura");

        pnlFormulario.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Disponiblidad", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 14), new java.awt.Color(0, 0, 0))); // NOI18N

        tblCupo.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        tblCupo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Servicio", "Cupo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCupo.setComponentPopupMenu(jPopupMenu1);
        tblCupo.setRowHeight(25);
        tblCupo.getTableHeader().setReorderingAllowed(false);
        tblCupo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCupoMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblCupo);
        if (tblCupo.getColumnModel().getColumnCount() > 0) {
            tblCupo.getColumnModel().getColumn(0).setMinWidth(0);
            tblCupo.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblCupo.getColumnModel().getColumn(0).setMaxWidth(0);
            tblCupo.getColumnModel().getColumn(2).setMinWidth(50);
            tblCupo.getColumnModel().getColumn(2).setPreferredWidth(50);
            tblCupo.getColumnModel().getColumn(2).setMaxWidth(50);
        }

        btnCascos.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnCascos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/casco.png"))); // NOI18N
        btnCascos.setText(" DISPONIBLES (1) ");

        btnGuardar3.setBackground(new java.awt.Color(204, 204, 204));
        btnGuardar3.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnGuardar3.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/actualizar.png"))); // NOI18N
        btnGuardar3.setText("REFRESCAR PÁG");
        btnGuardar3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar3.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnGuardar3.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnGuardar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardar3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                    .addComponent(btnCascos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnGuardar3)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCascos, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnGuardar3)
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        tblServicios.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        tblServicios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                " ", "Servicios", " "
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblServicios.setComponentPopupMenu(jPopupMenu1);
        tblServicios.setRowHeight(130);
        tblServicios.getTableHeader().setReorderingAllowed(false);
        tblServicios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblServiciosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblServicios);
        if (tblServicios.getColumnModel().getColumnCount() > 0) {
            tblServicios.getColumnModel().getColumn(0).setMinWidth(0);
            tblServicios.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblServicios.getColumnModel().getColumn(0).setMaxWidth(0);
            tblServicios.getColumnModel().getColumn(2).setMinWidth(0);
            tblServicios.getColumnModel().getColumn(2).setPreferredWidth(0);
            tblServicios.getColumnModel().getColumn(2).setMaxWidth(0);
        }

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 556, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dentro del parqueadero", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 0, 12), new java.awt.Color(0, 0, 0))); // NOI18N

        tblParqueo.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        tblParqueo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Placa", "Servicio", "Ingreso", "Fecha", "Observación", "producto"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblParqueo.setComponentPopupMenu(jPopupMenu1);
        tblParqueo.setRowHeight(25);
        tblParqueo.getTableHeader().setReorderingAllowed(false);
        tblParqueo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblParqueoMouseClicked(evt);
            }
        });
        tblParqueo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblParqueoKeyReleased(evt);
            }
        });
        jScrollPane5.setViewportView(tblParqueo);
        if (tblParqueo.getColumnModel().getColumnCount() > 0) {
            tblParqueo.getColumnModel().getColumn(0).setMinWidth(0);
            tblParqueo.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblParqueo.getColumnModel().getColumn(0).setMaxWidth(0);
            tblParqueo.getColumnModel().getColumn(1).setMinWidth(62);
            tblParqueo.getColumnModel().getColumn(1).setPreferredWidth(62);
            tblParqueo.getColumnModel().getColumn(1).setMaxWidth(85);
            tblParqueo.getColumnModel().getColumn(2).setMinWidth(150);
            tblParqueo.getColumnModel().getColumn(2).setPreferredWidth(180);
            tblParqueo.getColumnModel().getColumn(2).setMaxWidth(250);
            tblParqueo.getColumnModel().getColumn(3).setMinWidth(60);
            tblParqueo.getColumnModel().getColumn(3).setPreferredWidth(60);
            tblParqueo.getColumnModel().getColumn(3).setMaxWidth(60);
            tblParqueo.getColumnModel().getColumn(4).setMinWidth(90);
            tblParqueo.getColumnModel().getColumn(4).setPreferredWidth(90);
            tblParqueo.getColumnModel().getColumn(4).setMaxWidth(90);
            tblParqueo.getColumnModel().getColumn(6).setMinWidth(0);
            tblParqueo.getColumnModel().getColumn(6).setPreferredWidth(0);
            tblParqueo.getColumnModel().getColumn(6).setMaxWidth(0);
        }

        txtPlaca.setBackground(new java.awt.Color(204, 204, 204));
        txtPlaca.setFont(new java.awt.Font("Century Gothic", 1, 110)); // NOI18N
        txtPlaca.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPlaca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPlacaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPlacaKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addComponent(txtPlaca))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlFormularioLayout = new javax.swing.GroupLayout(pnlFormulario);
        pnlFormulario.setLayout(pnlFormularioLayout);
        pnlFormularioLayout.setHorizontalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlFormularioLayout.setVerticalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5))
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
            .addComponent(scrFormulario)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblServiciosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblServiciosMouseClicked
        if (evt.getClickCount() >= 1) {

            String codigo = tblServicios.getValueAt(tblServicios.getSelectedRow(), 0).toString();
            int cupo = 0;

            for (int i = 0; i < tblCupo.getRowCount(); i++) {
                if (tblCupo.getValueAt(i, 0).equals(codigo)) {
                    cupo = Integer.parseInt(tblCupo.getValueAt(i, 2).toString());
                }
            }

            Object[][] datos1 = instancias.getSql().getDatosMaestraParqueadero();

            if (cupo <= 0) {
                if (datos1[0][7].equals("1")) {
                    if (metodos.msgPregunta(this, "¿No hay cupo para este servicio. ¿Desea continuar?") == 0) {
                        dlgParqueo parqueo = new dlgParqueo(null, true, tblServicios.getValueAt(tblServicios.getSelectedRow(), 2).toString(), tblServicios.getValueAt(tblServicios.getSelectedRow(), 0).toString(), "");
                        parqueo.setLocationRelativeTo(null);
                        parqueo.setVisible(true);
                    }
                } else {
                    metodos.msgError(this, "No hay cupo para este servicio.");
                }
            } else {
                dlgParqueo parqueo = new dlgParqueo(null, true, tblServicios.getValueAt(tblServicios.getSelectedRow(), 2).toString(),
                        tblServicios.getValueAt(tblServicios.getSelectedRow(), 0).toString(), "");
                parqueo.setLocationRelativeTo(null);
                parqueo.setVisible(true);
            }
        }
    }//GEN-LAST:event_tblServiciosMouseClicked

    private void popModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popModificarActionPerformed

        if (tblServicios.getSelectedRow() > -1) {
            if (!instancias.getUsuario().equals("ADMIN")) {
                dlgPedirContrasena contrasena = new dlgPedirContrasena(null, true, "modificar");
                contrasena.setLocationRelativeTo(null);
                contrasena.setVisible(true);
            } else {
                modificarParqueadero();
            }
        } else {
            metodos.msgAdvertencia(this, "Seleccione un producto");
        }
    }//GEN-LAST:event_popModificarActionPerformed

    private void popAnularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popAnularActionPerformed
        if (tblServicios.getSelectedRow() > -1) {
            if (!instancias.getUsuario().equals("ADMIN")) {
                dlgPedirContrasena contrasena = new dlgPedirContrasena(null, true, "eliminar");
                contrasena.setLocationRelativeTo(null);
                contrasena.setVisible(true);
            } else {
//                eliminarParqueadero();
            }
        } else {
            metodos.msgAdvertencia(this, "Seleccione un producto");
        }
    }//GEN-LAST:event_popAnularActionPerformed

    private void tblCupoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCupoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblCupoMouseClicked

    private void tblParqueoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblParqueoMouseClicked
        if (evt.getClickCount() == 2) {
            dlgParqueo parqueo = new dlgParqueo(null, true, tblParqueo.getValueAt(tblParqueo.getSelectedRow(), 2).toString(),
                    tblParqueo.getValueAt(tblParqueo.getSelectedRow(), 6).toString(),
                    tblParqueo.getValueAt(tblParqueo.getSelectedRow(), 0).toString());
            parqueo.setLocationRelativeTo(null);
            parqueo.setVisible(true);
        }
    }//GEN-LAST:event_tblParqueoMouseClicked

    private void btnGuardar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar3ActionPerformed
        actualizarTablas();
    }//GEN-LAST:event_btnGuardar3ActionPerformed

    private void txtPlacaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPlacaKeyReleased
        txtPlaca.setText(txtPlaca.getText().toUpperCase());

        modeloOrdenado.setRowFilter(RowFilter.regexFilter("(?i)" + txtPlaca.getText(), 1));

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            String placa = txtPlaca.getText();

            for (int i = 0; i < tblParqueo.getRowCount(); i++) {
                if (tblParqueo.getValueAt(i, 1).equals(placa)) {
                    dlgParqueo parqueo = new dlgParqueo(null, true, tblParqueo.getValueAt(i, 2).toString(), tblParqueo.getValueAt(i, 6).toString(), tblParqueo.getValueAt(i, 0).toString());
                    parqueo.setLocationRelativeTo(null);
                    parqueo.setVisible(true);
                }
            }

        }
    }//GEN-LAST:event_txtPlacaKeyReleased

    private void tblParqueoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblParqueoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (tblParqueo.getSelectedColumn() == 5) {
                if (!instancias.getSql().cambiarObservacionParqueadero(tblParqueo.getValueAt(tblParqueo.getSelectedRow(), 0).toString(), tblParqueo.getValueAt(tblParqueo.getSelectedRow(), 5).toString())) {
                    metodos.msgError(this, "No fue posible guardar la observación");
                    tblParqueo.setValueAt("", tblParqueo.getSelectedRow(), 5);
                } else {
                    metodos.msgExito(this, "Observación guardada exitosamente.");
                    txtPlaca.setText("");
                    actualizarTablas();
                    txtPlaca.requestFocus();
                }
            }
        }
    }//GEN-LAST:event_tblParqueoKeyReleased

    private void txtPlacaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPlacaKeyPressed
        txtPlaca.setText(txtPlaca.getText().toUpperCase());
    }//GEN-LAST:event_txtPlacaKeyPressed

    public void modificarParqueadero() {
//        btnNuevoActionPerformed(null);
//        txtPlaca.setText(tblAutos.getValueAt(tblAutos.getSelectedRow(), 0).toString());
//        btnGuardar.setText("MODIFICAR");
//        cargarTercero(txtPlaca.getText());
    }

//    public void eliminarParqueadero() {
//        btnNuevoActionPerformed(null);
//        ndParqueadero nodoParqueadero = instancias.getSql().getDatosParqueaderoAutos(tblAutos.getValueAt(tblAutos.getSelectedRow(), 0).toString());
//        nodoParqueadero.setEstado(true);
//        nodoParqueadero.setObservacion("CANCELADA... " + nodoParqueadero.getObservacion());
//
//        if (!instancias.getSql().modificarParqueaderoAutos(nodoParqueadero)) {
//            metodos.msgError(this, "No fue posible Eliminar el parqueo");
//            return;
//        }
//
//        modelo.removeRow(tblAutos.getSelectedRow());
//
//    }
    public void setInstancias(Instancias instancias) {
        this.instancias = instancias;
//        actualizarTabla();

    }

    public void actualizarTablas() {

        DefaultTableModel modelo = (DefaultTableModel) tblServicios.getModel();
        DefaultTableModel modelo1 = (DefaultTableModel) tblCupo.getModel();
        DefaultTableModel modelo2 = (DefaultTableModel) tblParqueo.getModel();

        modeloOrdenado = new TableRowSorter<>(modelo2);
        tblParqueo.setRowSorter(modeloOrdenado);

        while (tblServicios.getRowCount() > 0) {
            modelo.removeRow(0);
        }

        while (tblCupo.getRowCount() > 0) {
            modelo1.removeRow(0);
        }

        while (tblParqueo.getRowCount() > 0) {
            modelo2.removeRow(0);
        }

        Object[][] datos = instancias.getSql().getDatosConfig();
        if (datos.length > 0) {
            for (int i = 0; i < datos.length; i++) {
                modelo1.addRow(new Object[]{datos[i][0], datos[i][1], datos[i][14]});
            }

            for (int i = 0; i < datos.length; i++) {
                modelo.addRow(new Object[]{"", ""});
                ImageIcon img;

                modelo.setValueAt(datos[i][0], i, 0);
                modelo.setValueAt(datos[i][1], i, 2);

                if (datos[i][0].equals("PROD-000000000")) {
                    img = new ImageIcon(getClass().getResource("/imagenes/moto.png"));
                    modelo.setValueAt(new JLabel(img), i, 1);
                } else if (datos[i][0].equals("PROD-000000001")) {
                    img = new ImageIcon(getClass().getResource("/imagenes/carro.png"));
                    modelo.setValueAt(new JLabel(img), i, 1);
                } else if (datos[i][0].equals("PROD-000000002")) {
                    img = new ImageIcon(getClass().getResource("/imagenes/bus.png"));
                    modelo.setValueAt(new JLabel(img), i, 1);
                } else if (datos[i][0].equals("PROD-000000003")) {
                    img = new ImageIcon(getClass().getResource("/imagenes/camion.png"));
                    modelo.setValueAt(new JLabel(img), i, 1);
                } else if (datos[i][0].equals("PROD-000000004")) {
                    img = new ImageIcon(getClass().getResource("/imagenes/buseta.png"));
                    modelo.setValueAt(new JLabel(img), i, 1);
                } else {
                    modelo.setValueAt(datos[i][0], i, 1);
                }
            }
        }

        Object[][] parqueos = instancias.getSql().getRegistrosParqueaderoAutos();
        Object[][] conteo = new Object[datos.length][3];
        int vr = 0;

        if (parqueos.length > 0) {
            for (int i = 0; i < parqueos.length; i++) {
                modelo2.addRow(new Object[]{"", "", "", "", "", ""});

                tblParqueo.setValueAt(parqueos[i][5], i, 0);
                tblParqueo.setValueAt(parqueos[i][0], i, 1);
                tblParqueo.setValueAt(parqueos[i][3], i, 2);
                tblParqueo.setValueAt(parqueos[i][1], i, 3);
                tblParqueo.setValueAt(metodos.fecha(parqueos[i][2].toString()), i, 4);
                tblParqueo.setValueAt(parqueos[i][4], i, 5);
                tblParqueo.setValueAt(parqueos[i][6], i, 6);

                Boolean entro = false;
                Boolean existente = false;

                if (datos.length > 0) {

                    for (int j = 0; j < datos.length; j++) {
                        if (datos[j][0].equals(parqueos[i][6])) {
                            existente = true;
                        }
                    }

                    if (existente) {
                        for (int j = 0; j < vr; j++) {
                            if (conteo[j][0].equals(parqueos[i][6])) {
                                entro = true;
                                conteo[j][1] = Integer.parseInt(conteo[j][1].toString()) + 1;
                            }
                        }
                    }
                }

                if (existente) {
                    if (!entro) {
                        conteo[vr][0] = parqueos[i][6];
                        conteo[vr][1] = 1;
                        conteo[vr][2] = parqueos[i][3];
                        vr++;
                    }
                }
            }
        }

        if (vr > 0) {
            for (int i = 0; i < vr; i++) {

                for (int j = 0; j < tblCupo.getRowCount(); j++) {
                    if (conteo[i][0].equals(tblCupo.getValueAt(j, 0))) {
                        tblCupo.setValueAt(Integer.parseInt(datos[i][14].toString()) - Integer.parseInt(conteo[i][1].toString()), j, 2);
                    }

//                    modelo1.addRow(new Object[]{conteo[i][0], conteo[i][2].toString(), Integer.parseInt(datos[i][14].toString()) - Integer.parseInt(conteo[i][1].toString())});
                }
            }
        }

        txtPlaca.setText("");
    }

//    public void enviarDisponibilidad() {
//        txtNumMotos.setText(numMotos + "");
//        txtNumCarros.setText(numCarros + "");
//        txtNumOtros.setText(numOtros + "");
//    }
//    public void cargarProducto(String codigo) {
//        txtCodProducto.setText(codigo);
//        ndProducto producto = instancias.getSql().getDatosProducto(codigo);
//        txtProducto.setText(producto.getDescripcion());
//        txtValorHoraDia.setText(big.setMoneda(big.getBigDecimal(producto.getL1())));
//        txtValorHoraNoche.setText(big.setMoneda(big.getBigDecimal(producto.getL2())));
//    }
//    public void cargarTercero(String Id) {
//        Object[][] placa = instancias.getSql().getUsuarioPlaca(Id);
//
//        ndTercero nodo;
//
//        if (placa.length > 0) {
//            nodo = instancias.getSql().getDatosTercero(placa[0][0].toString());
//        } else {
//            nodo = instancias.getSql().getDatosTercero("1010");
//        }
////        if (nodo.getId() == null) {
////            nodo = instancias.getSql().getDatosTercero("1010");
////        }
//        txtNombre.setText(nodo.getNombre());
//        txtTelefono.setText(nodo.getTelefono());
//        if (nodo.getId() != null) {
//            boolean esta = false;
//
//            for (int i = 0; i < tblAutos.getRowCount(); i++) {
//                if (txtPlaca.getText().equalsIgnoreCase(tblAutos.getValueAt(i, 0).toString())) {
//                    esta = true;
//                    break;
//                }
//            }
//
//            if (esta) {
//                //si la placa ya se encuentra registrada para una salida.
//                ndParqueadero nodoParqueadero = instancias.getSql().getDatosParqueaderoAutos(Id);
//                this.codigo = nodoParqueadero.getId();
//                txtfEntrada.setText(metodos.fecha(nodoParqueadero.getfIngreso()));
//                txtCodProducto.setText(nodoParqueadero.getProducto());
//
//                ndProducto producto = instancias.getSql().getDatosProducto1(nodoParqueadero.getProducto());
//
//                txtProducto.setText(producto.getDescripcion());
//                txtValorHoraDia.setText(big.setMoneda(big.getBigDecimal(producto.getL5())));
//                txtValorHoraNoche.setText(big.setMoneda(big.getBigDecimal(producto.getL6())));
//                txtValorExtraDia.setText(big.setMoneda(big.getBigDecimal(producto.getL7())));
//                txtValorExtraNoche.setText(big.setMoneda(big.getBigDecimal(producto.getL8())));
//                txtValorDia.setText(big.setMoneda(big.getBigDecimal(producto.getL9())));
//                txtValorNoche.setText(big.setMoneda(big.getBigDecimal(producto.getL10())));
//                txtValor24Horas.setText(big.setMoneda(big.getBigDecimal(producto.getL11())));
//
//                txthEntrada.setText(nodoParqueadero.gethIngreso());
//                txtfSalida.setText(metodosGenerales.fecha());
//                txtObservacion.setText(nodoParqueadero.getObservacion());
//                txtCasco1.setText(nodoParqueadero.getUbicacion1());
//                txtCasco2.setText(nodoParqueadero.getUbicacion2());
//                txtCasco3.setText(nodoParqueadero.getUbicacion3());
//
//                try {
//                    txtTipo.setText(producto.getTipoVehiculo());
//                    if (producto.getTipoVehiculo().equalsIgnoreCase("MOTO")) {
//                        panelCascos.setVisible(true);
//                    } else {
//                        panelCascos.setVisible(false);
//                    }
//                } catch (Exception e) {
//                    txtTipo.setText("");
//                }
//
//                //Se obtiene la hora de la salida y se separa.
//                String horaSal = metodosGenerales.hora();
//                String[] splitSalida = horaSal.split(":");
//                if (Integer.parseInt(splitSalida[0]) < 10) {
//                    splitSalida[0] = "0" + splitSalida[0];
//                }
//                if (Integer.parseInt(splitSalida[1]) < 10) {
//                    splitSalida[1] = "0" + splitSalida[1];
//                }
//
//                String horaCompletaSalida = splitSalida[0] + ":" + splitSalida[1];
//                //se manda la hora en formato correcto.
//                txthSalida.setText(horaCompletaSalida);
//
//                String horaEnt = txthEntrada.getText();
//                String[] splitEntrada = horaEnt.split(":");
//                //se formatea la hora entrada y la de salida.
//                int horaEntrada = Integer.parseInt(splitEntrada[0]);
//                int minEntrada = Integer.parseInt(splitEntrada[1]);
//                int horaSalida = Integer.parseInt(splitSalida[0]);
//                int minSalida = Integer.parseInt(splitSalida[1]);
//                int horaFinalSalida = 0, minFinalSalida = 0, horasAdentro = 0, dia = 0, restante = 0,
//                        totExtraDia = 0, totExtraNoche = 0, totDia = 0, totNoche = 0, totHoraDia = 0, totHoraNoche = 0;
//                String tiempoTotal = "";
//                String strHoraTotal = "";
//                String strMinTotal = "";
//
//                if (minSalida > minEntrada) {
//                    minFinalSalida = minSalida - minEntrada;
//                } else {
//                    minFinalSalida = (60 - minEntrada) + minSalida;
//                }
//                horaFinalSalida = horaSalida - horaEntrada;
//                horaFinalSalida = positivo(horaFinalSalida);
//                minFinalSalida = positivo(minFinalSalida);
//
//                if (!metodos.fecha(nodoParqueadero.getfIngreso()).equalsIgnoreCase(metodosGenerales.fecha())) {
//                    long diasAdentro = metodos.restarFecha(metodos.fecha(nodoParqueadero.getfIngreso()), metodosGenerales.fecha());
//                    if ((horaSalida < horaEntrada) || (horaSalida == horaEntrada && minSalida < minEntrada)) {
//                        horaFinalSalida = 24 - horaFinalSalida;
//                        diasAdentro = diasAdentro - 1;
//                    }
//                    txt24Horas.setText(diasAdentro + "");
//                }
//                if (minEntrada > minSalida) {
//                    horaFinalSalida = horaFinalSalida - 1;
//                }
//
//                horaFinalSalida = positivo(horaFinalSalida);
//                minFinalSalida = positivo(minFinalSalida);
//
//                strHoraTotal = agregarCeros(horaFinalSalida);
//                strMinTotal = agregarCeros(minFinalSalida);
//
//                tiempoTotal = strHoraTotal + ":" + strMinTotal;
//                txtTiempo.setText(tiempoTotal);
//                horasAdentro = horaFinalSalida;
//                if (horasAdentro == 0 && metodos.fecha(nodoParqueadero.getfIngreso()).equalsIgnoreCase(metodosGenerales.fecha())) {
//                    if (minFinalSalida > configMinEntrada) {
//                        horasAdentro = horasAdentro + 1;
//                    }
//                } else if (minFinalSalida > configMinEntrada2 && horaEntrada != horaSalida) {
//                    horasAdentro = horasAdentro + 1;
//                }
//
//                if (horasAdentro >= config24horas) {
//                    txt24Horas.setText(Integer.parseInt(txt24Horas.getText()) + 1 + "");
//                } else {
//                    if (horaEntrada >= configHoraDia1 && horaEntrada <= configHoraDia2) {
//
//                        dia = configHoraDia2 - horaEntrada;
//                        restante = horasAdentro - dia;
////                     
//                        if (restante > 0) {
//                            int interno = horasAdentro - restante;
//
//                            if (interno > configcantHorasCobroDia) {
//                                totDia = totDia + 1;
//                            } else if (interno > configcantHorasCobroExtra) {
//                                int horasExtras = interno - configcantHorasCobroExtra;
//                                totExtraDia = totExtraDia + horasExtras;
//                                totHoraDia = totHoraDia + configcantHorasCobroExtra;
//                            }
//
//                            if (restante > configcantHorasCobroDia) {
//                                totNoche = totNoche + 1;
//
//                                restante = restante - (configHoraDia2 - configHoraDia1);
//                                if (restante > 0) {
//                                    if (restante > configcantHorasCobroDia) {
//                                        totDia = totDia + 1;
//                                    } else if (restante > configcantHorasCobroExtra) {
//                                        int horasExtras = restante - configcantHorasCobroExtra;
//                                        totExtraDia = totExtraDia + horasExtras;
//                                        if (horaSalida >= configHoraDia1 && horaSalida <= configHoraDia2) {
//                                            totHoraDia = totHoraDia + configcantHorasCobroExtra;
//                                        } else {
//                                            totHoraNoche = totHoraNoche + configcantHorasCobroExtra;
//                                        }
//                                    } else {
//                                        totHoraNoche = totHoraNoche + restante;
//                                    }
//                                }
//                            } else if (restante > configcantHorasCobroExtra) {
//                                int horasExtras = restante - configcantHorasCobroExtra;
//                                totExtraNoche = totExtraNoche + horasExtras;
//                                if (horaSalida >= configHoraDia1 && horaSalida <= configHoraDia2) {
//                                    totHoraDia = totHoraDia + configcantHorasCobroExtra;
//                                } else {
//                                    totHoraNoche = totHoraNoche + configcantHorasCobroExtra;
//                                }
//                            } else {
//                                totHoraNoche = totHoraNoche + restante;
//                            }
//                        } else {
//                            if (horasAdentro > configcantHorasCobroDia) {
//                                totDia = totDia + 1;
//                            } else {
//                                if (horasAdentro > configcantHorasCobroExtra) {
//                                    int horasExtras = horasAdentro - configcantHorasCobroExtra;
//                                    totExtraDia = totExtraDia + horasExtras;
//                                    if (horaSalida >= configHoraDia1 && horaSalida <= configHoraDia2) {
//                                        totHoraDia = totHoraDia + configcantHorasCobroExtra;
//                                    } else {
//                                        totHoraNoche = totHoraNoche + configcantHorasCobroExtra;
//                                    }
//                                } else {
//                                    totHoraDia = totHoraDia + horasAdentro;
//                                }
//                            }
//
//                        }
//                    }
//
//                    //ACA VA EL ELSE DE QUE CONTINUE CON LAS VALIDACIONES SINO SE CUMPLE.
//                    if (horaEntrada >= configHoraNoche1 || horaEntrada <= configHoraNoche2) {
//                        int i = 0;
//                        if (horaEntrada >= configHoraNoche1 && horaSalida < 24) {
//                            i = 23 - horaEntrada;
//                        }
//                        if (horaSalida <= configHoraNoche2) {
//                            dia = horaSalida + i;
//                        } else {
//                            int cantx = horaSalida - configHoraNoche2;
//                            int canty = horaSalida - cantx;
//                            dia = canty + i;
//                        }
//
//                        dia = positivo(dia);
//                        restante = horasAdentro - dia;
//                        restante = positivo(restante);
//
//                        if (restante > 0) {
//                            int interno = horasAdentro - restante;
//                            if (interno > configcantHorasCobroDia) {
//                                totNoche = totNoche + 1;
//                            } else if (interno > configcantHorasCobroExtra) {
//                                int horasExtras = interno - configcantHorasCobroExtra;
//                                totExtraNoche = totExtraNoche + horasExtras;
//                                totHoraNoche = totHoraNoche + configcantHorasCobroExtra;
//                            }
//
//                            if (restante > configcantHorasCobroDia) {
//                                totDia = totDia + 1;
//                                int configRango = configHoraDia2 - configHoraDia1;
//                                restante = restante - configRango;
//                                if (restante > 0) {
//                                    if (restante > configcantHorasCobroDia) {
//                                        totNoche = totNoche + 1;
//                                    } else if (restante > configcantHorasCobroExtra) {
//                                        int horasExtras = restante - configcantHorasCobroExtra;
//                                        totExtraNoche = totExtraNoche + horasExtras;
//                                        if (horaSalida >= configHoraDia1 && horaSalida <= configHoraDia2) {
//                                            totHoraDia = totHoraDia + configcantHorasCobroExtra;
//                                        } else {
//                                            totHoraNoche = totHoraNoche + configcantHorasCobroExtra;
//                                        }
//                                    } else {
//                                        totHoraNoche = totHoraNoche + restante;
//                                    }
//                                }
//                            } else if (restante > configcantHorasCobroExtra) {
//                                int horasExtras = restante - configcantHorasCobroExtra;
//                                totExtraDia = totExtraDia + horasExtras;
//                                if (horaSalida >= configHoraDia1 && horaSalida <= configHoraDia2) {
//                                    totHoraDia = totHoraDia + configcantHorasCobroExtra;
//                                } else {
//                                    totHoraNoche = totHoraNoche + configcantHorasCobroExtra;
//                                }
//                            } else {
//                                totHoraDia = totHoraDia + restante;
//                            }
//                        } else {
//                            if (horasAdentro > configcantHorasCobroDia) {
//                                totDia = totDia + 1;
//                            } else {
//                                if (horasAdentro > configcantHorasCobroExtra) {
//                                    int horasExtras = horasAdentro - configcantHorasCobroExtra;
//                                    totExtraNoche = totExtraNoche + horasExtras;
//                                    if (horaSalida >= configHoraDia1 && horaSalida <= configHoraDia2) {
//                                        totHoraDia = totHoraDia + configcantHorasCobroExtra;
//                                    } else {
//                                        totHoraNoche = totHoraNoche + configcantHorasCobroExtra;
//                                    }
//                                } else {
//                                    totHoraDia = totHoraDia + horasAdentro;
//                                }
//                            }
//                        }
//                    }
//                }
//
//                txtHoras.setText(horasAdentro + "");
//                txtHoraExtraDia.setText(totExtraDia + "");
//                txtHoraExtraNoche.setText(totExtraNoche + "");
//                txtHoraDía.setText(totHoraDia + "");
//                txtHoraNoche.setText(totHoraNoche + "");
//                txtDia.setText(totDia + "");
//                txtNoche.setText(totNoche + "");
//
//                if (btnGuardar.getText().equalsIgnoreCase("GUARDAR")) {
//                    btnSalida.setEnabled(true);
//                    btnGuardar.setEnabled(false);
//                    txtPlaca.setEditable(false);
//                    txtCodProducto.setEditable(false);
////                    cmbTipoVehiculo.setEnabled(false);
//                    txtCasco1.setEditable(false);
//                    txtCasco2.setEditable(false);
//                    txtCasco3.setEditable(false);
//                } else {
//                    btnSalida.setEnabled(false);
//                    btnGuardar.setEnabled(true);
//                }
//            } else {
//                ventanaProductos(txtProducto.getText());
//
//                String hora = metodosGenerales.hora();
//                String[] split = hora.split(":");
//                if (Integer.parseInt(split[0]) < 10) {
//                    split[0] = "0" + split[0];
//                }
//                if (Integer.parseInt(split[1]) < 10) {
//                    split[1] = "0" + split[1];
//                }
//                String horaEntrada = split[0] + ":" + split[1];
//                txthEntrada.setText(horaEntrada);
//                txtfEntrada.setText(metodosGenerales.fecha());
//                txtHoras.setText("0");
//                txtTiempo.setText("0");
//                txtTotal.setText(big.setMoneda(big.getBigDecimal("0")));
//                txtValorHoraDia.setText(big.setMoneda(big.getBigDecimal("0")));
//                txtValorHoraNoche.setText(big.setMoneda(big.getBigDecimal("0")));
//                txtValorDia.setText(big.setMoneda(big.getBigDecimal("0")));
//                txtValorNoche.setText(big.setMoneda(big.getBigDecimal("0")));
//                txtValor24Horas.setText(big.setMoneda(big.getBigDecimal("0")));
//                txtHoraExtraDia.setText("0");
//                txtHoraExtraNoche.setText("0");
//                this.codigo = (String) null;
//            }
//        } else {
//            this.codigo = (String) null;
//        }
//    }
    public int positivo(int numero) {
        int numero2;

        if (numero == 60) {
            numero2 = 0;
        } else if (numero < 0) {
            numero2 = numero * -1;
        } else {
            numero2 = numero;
        }

        return numero2;
    }

    public String agregarCeros(int numero) {
        String cadena;

        if (numero == 60) {
            cadena = "00";
        } else if (numero < 10) {
            cadena = "0" + numero;
        } else {
            cadena = "" + numero;
        }
        return cadena;
    }

//    public void ventanaProductos(String codigo) {
//        buscProductos buscar = new buscProductos(null, rootPaneCheckingEnabled, false, "parqueadero");
//        buscar.setLocationRelativeTo(null);
////        buscar.setParque(false);
//        instancias.setBusProductos(buscar);
//        instancias.setCampoActual(txtCodProducto);
////        buscar.setInstancia(instancias);
//        buscar.noEncontrado(codigo.replace("'", "//"));
//        buscar.show();
//    }
//    public boolean esta() {
//        String[] codigos = "236417, 60850, 189991, 92398, 71111, 226577, 56578, 112603, 185389, 242940, 2099, 229670, 175597, 67034, 222707, 214598, 92798, 73233, 174546, 180416, 141450, 35496, 198844, 208877, 132025, 120732, 84284, 62483, 74454, 248047, 101731, 152185, 75477, 199164, 218991, 36926, 59587, 170146, 5092, 231937, 215305, 148597, 168854, 39767, 71460, 135728, 27077, 55217, 176220, 145254, 213632, 38205, 33904, 183652, 155592, 41734, 149643, 125887, 104235, 184848, 37593, 142075, 201826, 245362, 58871, 12509, 231084, 134843, 119271, 81044, 94637, 19218, 25138, 230145, 235934, 225433, 30235, 244974, 906, 249186, 236445, 12980, 190681, 178406, 185273, 177197, 134825, 133796, 41720, 168557, 592, 214956, 139182, 32228, 55317, 16663, 26130, 209549, 189223, 142663, 182057, 71160, 115980, 73572, 24142, 115259, 119915, 205544, 50568, 114460, 171675, 229917, 176660, 167807, 72315, 248960, 46172, 205009, 99264, 230387, 97459, 146564, 240450, 204353, 91861, 132295, 164849, 239572, 54255, 160273, 56769, 32766, 73557, 133005, 39302, 74585, 112970, 186744, 116759, 195290, 219119, 31778, 61192, 104949, 29504, 59493, 93775, 197170, 23921, 9978, 235379, 18140, 114261, 124051, 90624, 50454, 96622, 27324, 233032, 250003, 80928, 201341, 74706, 33618, 180488, 13330, 204905, 170548, 126195, 64813, 55440, 156733, 123995, 7648, 239264, 106508, 128253, 71918, 194760, 193416, 233234, 42369, 48032, 169411, 69109, 69288, 49541, 259, 232904, 40278, 196931, 175971, 775, 147927, 179371, 12152, 118256, 42961, 115230, 71132, 26044, 232885, 211779, 164269, 60278, 213927, 16692, 53053, 162505, 195088, 114135, 240363, 173416, 239701, 112318, 223410, 205738, 131675, 75782, 179923, 121801, 127030, 109005, 249122, 181763, 144363, 56472, 84161, 109906, 147193, 196056, 153858, 72689, 168572, 179269, 204507, 77656, 154993, 249011, 75090, 143210, 84106, 219186, 164693, 167120, 233427, 171694, 149161, 229669, 48005".split(",");
//
//        for (String codigo : codigos) {
//            if (codigo.equals(txtCodProducto.getText())) {
//                if (instancias.getSql().getDatosParqueaderoCod(txtCodProducto.getText()).getId() == null) {
//                    return true;
//                }
//            }
//        }
//
//        return false;
//    }
//    public void Guardar() {
//
//        String salida = metodosGenerales.fecha();
//        String id = "";
//
//        if (this.codigo == null) {
//            id = "PRQ-" + instancias.getSql().getNumConsecutivo("PRQ")[0];
//        } else {
//            id = this.codigo;
//        }
//
//        Object[] vector = {
//            id, txtPlaca.getText(), txtCodProducto.getText(), txtfEntrada.getText(),
//            salida, txthEntrada.getText(), txthSalida.getText(), txtTiempo.getText(), txtHoras.getText(), big.getMoneda(txtTotal.getText()),
//            txtHoraDía.getText(), txtHoraNoche.getText(), txtHoraExtraDia.getText(), txtHoraExtraNoche.getText(), txtDia.getText(), txtNoche.getText(),
//            txt24Horas.getText(), txtObservacion.getText(), txtCasco1.getText(), txtCasco2.getText(), txtCasco3.getText(), false, ""
//        };
//
//        ndParqueadero nodo = metodos.llenarParqueaderoAutos(vector);
//
//        if (txtfSalida.getText().equalsIgnoreCase("")) {
//
//            if (!sql.agregarParqueaderoAutos(nodo)) {
//                metodos.msgError(this, "Hubo un error al guardar este registro");
//                return;
//            }
//
//            modelo.addRow(new Object[]{txtPlaca.getText(), txthEntrada.getText(), txtfEntrada.getText()});
//
//            instancias.getReporte().verTirilla1(instancias.getInformacionEmpresa(), instancias.getLegal(), id, "Original");
//
//            if (!instancias.getSql().aumentarConsecutivo("PRQ", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("PRQ")[0]) + 1)) {
//                metodos.msgError(this, "Hubo un problema al guardar en el consecutivo");
//            }
//        }
//        txtPlaca.requestFocus();
//        this.codigo = null;
//        btnNuevoActionPerformed(null);
//
//    }
//    public void Salida() {
//
//        if (rbtCredito.isSelected() && txtNombre.getText().equals("VENTAS DE CONTADO")) {
//            metodos.msgError(this, "No puede realizar ventas a credito a este cliente");
//            rbtContado.setSelected(true);
//            return;
//        }
//
//        String ID = "";
//
//        if (!txtfEntrada.getText().equalsIgnoreCase("")) {
//            if (metodos.msgPregunta(this, "¿Desea continuar") == 0) {
//
//                String id = this.codigo;
//                ID = id;
//
//                ndTercero nodoCliente = instancias.getSql().getDatosPlaca(txtPlaca.getText());
//
//                if (nodoCliente.getId() == null) {
//                    nodoCliente = instancias.getSql().getDatosTercero("1010");
//                }
//
//                String diasPlazo = "";
//
//                if (rbtCredito.isSelected()) {
//                    if (txtDiasPlazo.getText().equals("")) {
//                        metodos.msgError(this, "Ingrese los dias de plazo");
//                        return;
//                    }
//                }
//
//                diasPlazo = txtDiasPlazo.getText();
//
//                instancias.getFacturaContenedor().getPnlFactura().setMensualidad(false);
//                String factura = instancias.getFactura().desdeParqueadero(nodoCliente.getId(),
//                        new String[][]{new String[]{txtCodProducto.getText(), txtTotal.getText()}}, diasPlazo, txtPlaca.getText());
//
//                Object[] vector = {
//                    id, txtPlaca.getText(), txtCodProducto.getText(), txtfEntrada.getText(),
//                    txtfSalida.getText(), txthEntrada.getText(), txthSalida.getText(), txtTiempo.getText(), txtHoras.getText(), big.getMoneda(txtTotal.getText()),
//                    txtHoraDía.getText(), txtHoraNoche.getText(), txtHoraExtraDia.getText(), txtHoraExtraNoche.getText(), txtDia.getText(), txtNoche.getText(),
//                    txt24Horas.getText(), txtObservacion.getText(), txtCasco1.getText(), txtCasco2.getText(), txtCasco3.getText(), true, factura
//                };
//
//                ndParqueadero nodo = metodos.llenarParqueaderoAutos(vector);
//
//                if (!instancias.getSql().modificarParqueaderoAutos(nodo)) {
//                    metodos.msgError(this, "No fue posible Modificar el parqueo para marcar la salida");
//                    sql.anularDocumento(factura, "bdCaja");
//                    return;
//                }
//
//                for (int i = 0; i < tblAutos.getRowCount(); i++) {
//                    if (tblAutos.getValueAt(i, 0).toString().equalsIgnoreCase(txtPlaca.getText())) {
//                        modelo.removeRow(i);
//                        try {
//                            if (tblAutos.getValueAt(i, 3).equals("MOTO")) {
//                                numMotos++;
//                            } else if (tblAutos.getValueAt(i, 3).equals("AUTOMOVIL")) {
//                                numCarros++;
//                            } else {
//                                numOtros++;
//                            }
//                        } catch (Exception e) {
//                        }
//
//                        enviarDisponibilidad();
//                        break;
//                    }
//                }
////                if (metodos.msgPregunta(this, "¿Desea imprimir tirilla?") == 0) {
////                }
//            }
//
//            instancias.getReporte().verTirilla2(instancias.getInformacionEmpresa(), instancias.getLegal(), ID, "Original");
//            txtPlaca.requestFocus();
//            this.codigo = null;
//            btnNuevoActionPerformed(null);
//
//        }
//
//    }
//    public void Modificar() {
//
//        String id = this.codigo;
//        for (int i = 0; i < tblAutos.getRowCount(); i++) {
//            if (tblAutos.getValueAt(i, 0).equals(txtPlaca.getText())) {
//                metodos.msgError(this, "Esta placa ya se encuentra registrada");
//                return;
//            }
//        }
//
//        Object[] vector = {
//            id, txtPlaca.getText(), txtCodProducto.getText(), txtfEntrada.getText(),
//            txtfSalida.getText(), txthEntrada.getText(), txthSalida.getText(), txtTiempo.getText(), txtHoras.getText(), big.getMoneda(txtTotal.getText()),
//            txtHoraDía.getText(), txtHoraNoche.getText(), txtHoraExtraDia.getText(), txtHoraExtraNoche.getText(), txtDia.getText(), txtNoche.getText(),
//            txt24Horas.getText(), txtObservacion.getText(), txtCasco1.getText(), txtCasco2.getText(), txtCasco3.getText(), false, ""
//        };
//
//        ndParqueadero nodo = metodos.llenarParqueaderoAutos(vector);
//
//        if (!instancias.getSql().modificarParqueaderoAutos(nodo)) {
//            metodos.msgError(this, "No fue posible Modificar el parqueo");
//            return;
//        }
//        metodos.msgExito(this, "Modificado con exito");
//
//        txtPlaca.requestFocus();
//        this.codigo = null;
//        btnNuevoActionPerformed(null);
//        actualizarTabla();
//
//    }
//    public void cargarTotales() {
//
//        BigDecimal var1 = big.getBigDecimal(big.getMoneda(txtValorHoraDia.getText())).multiply(big.getBigDecimal(txtHoraDía.getText()));
//        BigDecimal var2 = big.getBigDecimal(big.getMoneda(txtValorHoraNoche.getText())).multiply(big.getBigDecimal(txtHoraNoche.getText()));
//        BigDecimal var3 = big.getBigDecimal(big.getMoneda(txtValorExtraDia.getText())).multiply(big.getBigDecimal(txtHoraExtraDia.getText()));
//        BigDecimal var4 = big.getBigDecimal(big.getMoneda(txtValorExtraNoche.getText())).multiply(big.getBigDecimal(txtHoraExtraNoche.getText()));
//        BigDecimal var5 = big.getBigDecimal(big.getMoneda(txtValorDia.getText())).multiply(big.getBigDecimal(txtDia.getText()));
//        BigDecimal var6 = big.getBigDecimal(big.getMoneda(txtValorNoche.getText())).multiply(big.getBigDecimal(txtNoche.getText()));
//        BigDecimal var7 = big.getBigDecimal(big.getMoneda(txtValor24Horas.getText())).multiply(big.getBigDecimal(txt24Horas.getText()));
//        BigDecimal total = var1.add(var2).add(var3).add(var4).add(var5).add(var6).add(var7);
//        txtTotal.setText(big.setMoneda(total));
//
//    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCascos;
    private javax.swing.JButton btnGuardar3;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JPanel pnlFormulario;
    private javax.swing.JMenuItem popAnular;
    private javax.swing.JMenuItem popModificar;
    private javax.swing.JScrollPane scrFormulario;
    private javax.swing.JTable tblCupo;
    private javax.swing.JTable tblParqueo;
    private javax.swing.JTable tblServicios;
    private javax.swing.JTextField txtPlaca;
    // End of variables declaration//GEN-END:variables
}
