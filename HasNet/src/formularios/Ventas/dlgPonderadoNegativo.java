package formularios.Ventas;

import formularios.productos.*;
import formularios.Parqueadero.*;
import clases.Instancias;
import Utilidades.BaseDatos.SQL;
import clases.big;
import clases.metodosGenerales;
import clases.productos.ndProducto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.KeyStroke;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class dlgPonderadoNegativo extends javax.swing.JDialog {

    private Instancias instancias;
    DefaultTableModel modelo;
    TableRowSorter modeloOrdenado;
    metodosGenerales metodos = new metodosGenerales();
    Object[] prod;
    String simbolo = "";

    public dlgPonderadoNegativo(java.awt.Frame parent, boolean modal, Object[] productos) {
        super(parent, modal);
        initComponents();

        this.setLocationRelativeTo(null);
        this.setTitle("ORDENES DE SERVICIO PENDIENTES");

        instancias = Instancias.getInstancias();

        try {
            if (productos.length > 0) {
                prod = productos;
                cargarTablas();
            }
        } catch (Exception e) {
            cargarTablas();
        }

        modelo = (DefaultTableModel) tblTraslados.getModel();
        modeloOrdenado = new TableRowSorter<>(modelo);
        tblTraslados.setRowSorter(modeloOrdenado);

        this.simbolo = instancias.getSimbolo();

        this.getRootPane().registerKeyboardAction(accion("cerrar", this), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTraslados = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        lbEtiqueta6 = new javax.swing.JLabel();
        txtValor1 = new javax.swing.JTextField();
        txtFinalizar2 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtGrupo = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        txtFinalizar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 30)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("PONDERADOS");

        tblTraslados.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        tblTraslados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Producto", "Descripción", "Grupo", "Costo Pond", "Ultimo Costo", "Nuevo Pond", "Sel"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTraslados.setRowHeight(24);
        tblTraslados.getTableHeader().setReorderingAllowed(false);
        tblTraslados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTrasladosMouseClicked(evt);
            }
        });
        tblTraslados.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblTrasladosKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblTraslados);
        if (tblTraslados.getColumnModel().getColumnCount() > 0) {
            tblTraslados.getColumnModel().getColumn(0).setMinWidth(0);
            tblTraslados.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblTraslados.getColumnModel().getColumn(0).setMaxWidth(0);
            tblTraslados.getColumnModel().getColumn(2).setMinWidth(80);
            tblTraslados.getColumnModel().getColumn(2).setPreferredWidth(80);
            tblTraslados.getColumnModel().getColumn(2).setMaxWidth(80);
            tblTraslados.getColumnModel().getColumn(3).setMinWidth(100);
            tblTraslados.getColumnModel().getColumn(3).setPreferredWidth(100);
            tblTraslados.getColumnModel().getColumn(3).setMaxWidth(100);
            tblTraslados.getColumnModel().getColumn(4).setMinWidth(100);
            tblTraslados.getColumnModel().getColumn(4).setPreferredWidth(100);
            tblTraslados.getColumnModel().getColumn(4).setMaxWidth(100);
            tblTraslados.getColumnModel().getColumn(5).setMinWidth(100);
            tblTraslados.getColumnModel().getColumn(5).setPreferredWidth(100);
            tblTraslados.getColumnModel().getColumn(5).setMaxWidth(100);
            tblTraslados.getColumnModel().getColumn(6).setMinWidth(25);
            tblTraslados.getColumnModel().getColumn(6).setPreferredWidth(25);
            tblTraslados.getColumnModel().getColumn(6).setMaxWidth(25);
        }

        jLabel4.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel4.setText("DESCRIPCIÓN:");

        txtDescripcion.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtDescripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDescripcionKeyReleased(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        lbEtiqueta6.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lbEtiqueta6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbEtiqueta6.setText("Porcentaje:");

        txtValor1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtValor1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtValor1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtValor1KeyReleased(evt);
            }
        });

        txtFinalizar2.setBackground(new java.awt.Color(204, 204, 204));
        txtFinalizar2.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        txtFinalizar2.setForeground(new java.awt.Color(255, 255, 255));
        txtFinalizar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/boton-de-suma3.png"))); // NOI18N
        txtFinalizar2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txtFinalizar2.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        txtFinalizar2.setMargin(new java.awt.Insets(2, 7, 2, 5));
        txtFinalizar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFinalizar2ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jButton2.setText("Todos");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jButton3.setText("Quitar Selección");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jButton4.setText("Sin Ponderado");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbEtiqueta6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtValor1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFinalizar2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 154, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jButton4)
                .addGap(3, 3, 3)
                .addComponent(jButton3)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(txtValor1)
                    .addComponent(lbEtiqueta6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtFinalizar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );

        jLabel5.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel5.setText("GRUPO:");

        txtGrupo.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtGrupo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtGrupoKeyReleased(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        txtFinalizar.setBackground(new java.awt.Color(46, 204, 113));
        txtFinalizar.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        txtFinalizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/agregar.png"))); // NOI18N
        txtFinalizar.setText("ASIGNAR PONDERADOS");
        txtFinalizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txtFinalizar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        txtFinalizar.setMargin(new java.awt.Insets(2, 7, 2, 5));
        txtFinalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFinalizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtFinalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtFinalizar)
                .addGap(5, 5, 5))
        );

        jLabel3.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("X");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtGrupo)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel2)
                .addGap(5, 5, 5)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDescripcion)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtFinalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFinalizarActionPerformed
        for (int i = 0; i < tblTraslados.getRowCount(); i++) {
            if ((Boolean) tblTraslados.getValueAt(i, 6)) {

                DecimalFormat df = new DecimalFormat("#.000");
                BigDecimal num1, num2;

                try {
                    num1 = big.getMoneda(tblTraslados.getValueAt(i, 5).toString());
                } catch (Exception e) {
                    num1 = BigDecimal.ZERO;
                }

                try {
                    num2 = big.getMoneda(tblTraslados.getValueAt(i, 3).toString());
                } catch (Exception e) {
                    num2 = BigDecimal.ZERO;
                }

                if (!instancias.getSql().agregarPonderado(metodos.fechaConsulta(metodosGenerales.fecha()), tblTraslados.getValueAt(i, 0).toString(),
                        big.getMoneda(tblTraslados.getValueAt(i, 3).toString()), "0", "0", num1, "0", instancias.getUsuario(), num2, "")) {
                    metodos.msgError(null, "Error al guardar el ponderado");
                }

                if (tblTraslados.getValueAt(i, 3).toString().equals("N/A")) {
                    if (!instancias.getSql().agregarUltimoPonderado(metodos.fechaConsulta(metodosGenerales.fechaHora()), tblTraslados.getValueAt(i, 0).toString(),
                            big.getMoneda(tblTraslados.getValueAt(i, 3).toString()), "0", "0", num1, "0", instancias.getUsuario(), num2, "")) {
                        metodos.msgError(null, "Error al guardar el ponderado");
                    }
                } else {
                    if (!instancias.getSql().modificarPonderado(metodos.fechaConsulta(metodosGenerales.fechaHora()), tblTraslados.getValueAt(i, 0).toString(),
                            big.getMoneda(tblTraslados.getValueAt(i, 3).toString()), "0", "0", num1, "0", instancias.getUsuario(), num2, "")) {
                        metodos.msgError(null, "Error al guardar el ponderado");
                    }
                }
            }
        }

        metodos.msgExito(null, "Ponderados actualizado");
        this.dispose();
    }//GEN-LAST:event_txtFinalizarActionPerformed

    private void tblTrasladosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTrasladosMouseClicked

    }//GEN-LAST:event_tblTrasladosMouseClicked

    private void txtDescripcionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionKeyReleased
        modeloOrdenado.setRowFilter(RowFilter.regexFilter("(?i)" + txtDescripcion.getText(), 1));
        txtGrupo.setText("");
    }//GEN-LAST:event_txtDescripcionKeyReleased

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        for (int i = 0; i < tblTraslados.getRowCount(); i++) {
            tblTraslados.setValueAt(true, i, 6);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        for (int i = 0; i < tblTraslados.getRowCount(); i++) {
            tblTraslados.setValueAt(false, i, 6);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtGrupoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGrupoKeyReleased
        modeloOrdenado.setRowFilter(RowFilter.regexFilter("(?i)" + txtGrupo.getText(), 2));
        txtDescripcion.setText("");
    }//GEN-LAST:event_txtGrupoKeyReleased

    private void tblTrasladosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblTrasladosKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (tblTraslados.getSelectedColumn() == 5) {
                tblTraslados.setValueAt(big.setMoneda(big.getMoneda(tblTraslados.getValueAt(tblTraslados.getSelectedRow(), 5).toString())), tblTraslados.getSelectedRow(), 5);
            }
        }
    }//GEN-LAST:event_tblTrasladosKeyReleased

    private void txtValor1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValor1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValor1KeyReleased

    private void txtFinalizar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFinalizar2ActionPerformed
        for (int i = 0; i < tblTraslados.getRowCount(); i++) {
            if ((Boolean) tblTraslados.getValueAt(i, 6)) {
                Double ultimoCosto = Double.parseDouble(big.setNumero(big.getMoneda(tblTraslados.getValueAt(i, 4).toString())).replace(".", "").replace(",", "."));
                Double numero = Double.parseDouble(txtValor1.getText().replace(",", "."));
                Double total = numero * ultimoCosto;
                Double total1 = total / 100;
                total1 = total1 + ultimoCosto;
                tblTraslados.setValueAt(big.setMoneda(big.getBigDecimal(total1)), i, 5);
            }
        }
    }//GEN-LAST:event_txtFinalizar2ActionPerformed

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        this.dispose();
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        for (int i = 0; i < tblTraslados.getRowCount(); i++) {
            if (tblTraslados.getValueAt(i, 3).toString().equals("N/A")) {
                tblTraslados.setValueAt(true, i, 6);
            }
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    public void cargarTablas() {

        DefaultTableModel modeloTabla = (DefaultTableModel) tblTraslados.getModel();
        while (tblTraslados.getRowCount() > 0) {
            modeloTabla.removeRow(0);
        }

        Object[][] datos = instancias.getSql().getUltimosPonderados();

        for (int i = 0; i < datos.length; i++) {
            modeloTabla.addRow(new Object[]{"", "", "", "", "", "", false});
        }

        for (int i = 0; i < datos.length; i++) {
            tblTraslados.setValueAt(datos[i][0], i, 0);
            tblTraslados.setValueAt(datos[i][3], i, 1);
            tblTraslados.setValueAt(datos[i][4], i, 2);

            try {
                tblTraslados.setValueAt(big.setMoneda(big.getBigDecimal(datos[i][1].toString())), i, 3);
            } catch (Exception e) {
                tblTraslados.setValueAt("N/A", i, 3);
            }

            try {
                tblTraslados.setValueAt(big.setMoneda(big.getBigDecimal(datos[i][2].toString())), i, 4);
            } catch (Exception e) {
                tblTraslados.setValueAt("N/A", i, 4);
            }

            try {
                tblTraslados.setValueAt(big.setMoneda(big.getBigDecimal(datos[i][1].toString())), i, 5);
            } catch (Exception e) {
                tblTraslados.setValueAt("N/A", i, 5);
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
            java.util.logging.Logger.getLogger(dlgPonderadoNegativo.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(dlgPonderadoNegativo.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(dlgPonderadoNegativo.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dlgPonderadoNegativo.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                dlgPonderadoNegativo dialog = new dlgPonderadoNegativo(new javax.swing.JFrame(), true, null);
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
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbEtiqueta6;
    private javax.swing.JTable tblTraslados;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JButton txtFinalizar;
    private javax.swing.JButton txtFinalizar2;
    private javax.swing.JTextField txtGrupo;
    private javax.swing.JTextField txtValor1;
    // End of variables declaration//GEN-END:variables
}
