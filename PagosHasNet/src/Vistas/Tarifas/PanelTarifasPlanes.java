package Vistas.Tarifas;

import Controlador.Alertas.controladorAlertas;
import Modelos.Ingresos.modeloPlanFacturacion;
import clases.Instancias;
import clases.SQLIngresos;
import clases.big;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class PanelTarifasPlanes extends javax.swing.JPanel {

    private static final int COLUMNA_VALOR_TARIFA = 2;
    private final List<modeloPlanFacturacion> planes = new ArrayList<>();
    private int idPlanEditado;
    private boolean formateandoTarifa;

    public PanelTarifasPlanes() {
        initComponents();
        java.awt.Font fuenteEncabezado = new java.awt.Font("Century Gothic", java.awt.Font.BOLD, 15);
        tblTarifas.getTableHeader().setFont(fuenteEncabezado);
        tblPlanes.getTableHeader().setFont(fuenteEncabezado);
        tblPlanes.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblTarifas.getModel().addTableModelListener(this::formatearValorTarifa);
    }

    private void formatearValorTarifa(javax.swing.event.TableModelEvent evento) {
        int fila = evento.getFirstRow();
        if (formateandoTarifa || evento.getType() != javax.swing.event.TableModelEvent.UPDATE
                || evento.getColumn() != COLUMNA_VALOR_TARIFA || fila < 0 || fila >= tblTarifas.getModel().getRowCount()) {
            return;
        }
        Object valor = tblTarifas.getModel().getValueAt(fila, COLUMNA_VALOR_TARIFA);
        formateandoTarifa = true;
        try {
            tblTarifas.getModel().setValueAt(big.setMoneda(leerValor(valor == null ? "" : valor.toString())), fila, COLUMNA_VALOR_TARIFA);
        } catch (IllegalStateException ex) {
            controladorAlertas.alertFail(ex.getMessage());
        } finally {
            formateandoTarifa = false;
        }
    }

    private SQLIngresos sqlIngresos() {
        return Instancias.getInstancias().getSqlIngresos();
    }

    public void recargar() {
        try {
            DefaultTableModel modeloTarifas = (DefaultTableModel) tblTarifas.getModel();
            modeloTarifas.setRowCount(0);
            for (Object[] tarifa : sqlIngresos().listarTarifas()) {
                modeloTarifas.addRow(new Object[]{tarifa[0], tarifa[1], big.setMoneda((BigDecimal) tarifa[2])});
            }

            planes.clear();
            planes.addAll(sqlIngresos().listarPlanes(false));
            DefaultTableModel modeloPlanes = (DefaultTableModel) tblPlanes.getModel();
            modeloPlanes.setRowCount(0);
            for (modeloPlanFacturacion plan : planes) {
                modeloPlanes.addRow(new Object[]{plan.getIdPlan(), plan.getNombre(), plan.isMensual() ? "Mensual (vence)" : "Sin vencimiento",
                    big.setNumero(BigDecimal.valueOf(plan.getNumeroDocumentos())), big.setMoneda(plan.getPrecio()), plan.isActivo() ? "SI" : "NO"});
            }
            limpiarFormulario();

            String informacionLegal = sqlIngresos().obtenerParametroGlobal(SQLIngresos.PARAMETRO_INFORMACION_LEGAL);
            txtInformacionLegal.setText(informacionLegal == null ? "" : informacionLegal);
        } catch (SQLException ex) {
            Logger.getLogger(PanelTarifasPlanes.class.getName()).log(Level.SEVERE, "Error al cargar tarifas y paquetes", ex);
            controladorAlertas.alertFail("No se pudieron cargar las tarifas. Verifique que los scripts de la base de pagos estén aplicados.");
        }
    }

    public static BigDecimal leerValor(String texto) {
        String limpio = texto == null ? "" : texto.replace("$", "").replace(" ", "").replace(" ", "")
                .replace(".", "").replace(",", "").trim();
        if (limpio.isEmpty()) {
            throw new IllegalStateException("Ingrese un valor");
        }
        try {
            return new BigDecimal(limpio);
        } catch (NumberFormatException ex) {
            throw new IllegalStateException("Valor inválido: " + texto);
        }
    }

    private void cargarPlan(modeloPlanFacturacion plan) {
        idPlanEditado = plan.getIdPlan();
        txtNombrePlan.setText(plan.getNombre());
        cmbTipoPlan.setSelectedIndex(plan.isMensual() ? 0 : 1);
        txtDocumentos.setText(big.setNumero(BigDecimal.valueOf(plan.getNumeroDocumentos())));
        txtPrecio.setText(big.setMoneda(plan.getPrecio()));
        chkActivo.setSelected(plan.isActivo());
    }

    private void limpiarFormulario() {
        idPlanEditado = 0;
        txtNombrePlan.setText("");
        cmbTipoPlan.setSelectedIndex(0);
        txtDocumentos.setText("");
        txtPrecio.setText("");
        chkActivo.setSelected(true);
        tblPlanes.clearSelection();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTarifas = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTarifas = new javax.swing.JTable();
        btnGuardarTarifas = new javax.swing.JButton();
        pnlPaquetes = new javax.swing.JPanel();
        lbNombrePlan = new javax.swing.JLabel();
        txtNombrePlan = new javax.swing.JTextField();
        lbTipoPlan = new javax.swing.JLabel();
        cmbTipoPlan = new javax.swing.JComboBox<>();
        lbDocumentos = new javax.swing.JLabel();
        txtDocumentos = new javax.swing.JTextField();
        lbPrecio = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        chkActivo = new javax.swing.JCheckBox();
        btnNuevoPlan = new javax.swing.JButton();
        btnGuardarPlan = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblPlanes = new javax.swing.JTable();
        pnlInformacionLegal = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtInformacionLegal = new javax.swing.JTextArea();
        btnGuardarInformacionLegal = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        pnlTarifas.setBackground(new java.awt.Color(255, 255, 255));
        pnlTarifas.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tarifas de instalación y mensualidad", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14))); // NOI18N

        tblTarifas.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        tblTarifas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Concepto", "Descripción", "Valor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTarifas.setRowHeight(24);
        tblTarifas.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblTarifas);
        if (tblTarifas.getColumnModel().getColumnCount() > 0) {
            tblTarifas.getColumnModel().getColumn(0).setMinWidth(0);
            tblTarifas.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblTarifas.getColumnModel().getColumn(0).setMaxWidth(0);
            tblTarifas.getColumnModel().getColumn(2).setMinWidth(180);
            tblTarifas.getColumnModel().getColumn(2).setPreferredWidth(180);
            tblTarifas.getColumnModel().getColumn(2).setMaxWidth(180);
        }

        btnGuardarTarifas.setBackground(new java.awt.Color(46, 204, 113));
        btnGuardarTarifas.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        btnGuardarTarifas.setText("GUARDAR TARIFAS");
        btnGuardarTarifas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarTarifasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlTarifasLayout = new javax.swing.GroupLayout(pnlTarifas);
        pnlTarifas.setLayout(pnlTarifasLayout);
        pnlTarifasLayout.setHorizontalGroup(
            pnlTarifasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTarifasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTarifasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 629, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTarifasLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnGuardarTarifas, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlTarifasLayout.setVerticalGroup(
            pnlTarifasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTarifasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnGuardarTarifas, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pnlPaquetes.setBackground(new java.awt.Color(255, 255, 255));
        pnlPaquetes.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Paquetes de facturación electrónica", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14))); // NOI18N

        lbNombrePlan.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        lbNombrePlan.setText("Nombre:");

        txtNombrePlan.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N

        lbTipoPlan.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        lbTipoPlan.setText("Tipo paquete:");

        cmbTipoPlan.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        cmbTipoPlan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mensual (vence)", "Sin vencimiento" }));

        lbDocumentos.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        lbDocumentos.setText("# Documentos:");

        txtDocumentos.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        txtDocumentos.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDocumentos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDocumentosKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDocumentosKeyTyped(evt);
            }
        });

        lbPrecio.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        lbPrecio.setText("Precio:");

        txtPrecio.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        txtPrecio.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPrecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPrecioKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecioKeyTyped(evt);
            }
        });

        chkActivo.setBackground(new java.awt.Color(255, 255, 255));
        chkActivo.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        chkActivo.setSelected(true);
        chkActivo.setText("Estado activo");

        btnNuevoPlan.setBackground(new java.awt.Color(255, 204, 204));
        btnNuevoPlan.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        btnNuevoPlan.setText("NUEVO");
        btnNuevoPlan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoPlanActionPerformed(evt);
            }
        });

        btnGuardarPlan.setBackground(new java.awt.Color(46, 204, 113));
        btnGuardarPlan.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        btnGuardarPlan.setText("GUARDAR PAQUETE");
        btnGuardarPlan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarPlanActionPerformed(evt);
            }
        });

        tblPlanes.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        tblPlanes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Tipo", "Documentos", "Precio", "Activo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPlanes.setRowHeight(24);
        tblPlanes.getTableHeader().setReorderingAllowed(false);
        tblPlanes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPlanesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblPlanes);
        if (tblPlanes.getColumnModel().getColumnCount() > 0) {
            tblPlanes.getColumnModel().getColumn(0).setMinWidth(0);
            tblPlanes.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblPlanes.getColumnModel().getColumn(0).setMaxWidth(0);
        }

        javax.swing.GroupLayout pnlPaquetesLayout = new javax.swing.GroupLayout(pnlPaquetes);
        pnlPaquetes.setLayout(pnlPaquetesLayout);
        pnlPaquetesLayout.setHorizontalGroup(
            pnlPaquetesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPaquetesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPaquetesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(pnlPaquetesLayout.createSequentialGroup()
                        .addGroup(pnlPaquetesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbDocumentos)
                            .addComponent(lbNombrePlan))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlPaquetesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNombrePlan, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                            .addComponent(txtDocumentos))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlPaquetesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbTipoPlan)
                            .addComponent(lbPrecio))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlPaquetesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtPrecio, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbTipoPlan, javax.swing.GroupLayout.Alignment.LEADING, 0, 170, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(chkActivo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnNuevoPlan, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGuardarPlan, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlPaquetesLayout.setVerticalGroup(
            pnlPaquetesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPaquetesLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(pnlPaquetesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNombrePlan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombrePlan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbTipoPlan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbTipoPlan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNuevoPlan, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardarPlan, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkActivo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(pnlPaquetesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbDocumentos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDocumentos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlInformacionLegal.setBackground(new java.awt.Color(255, 255, 255));
        pnlInformacionLegal.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Información legal de impresión (aplica a todos los clientes)", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14))); // NOI18N

        txtInformacionLegal.setColumns(40);
        txtInformacionLegal.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txtInformacionLegal.setLineWrap(true);
        txtInformacionLegal.setRows(3);
        txtInformacionLegal.setWrapStyleWord(true);
        jScrollPane3.setViewportView(txtInformacionLegal);

        btnGuardarInformacionLegal.setBackground(new java.awt.Color(46, 204, 113));
        btnGuardarInformacionLegal.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        btnGuardarInformacionLegal.setText("GUARDAR INFORMACIÓN LEGAL");
        btnGuardarInformacionLegal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarInformacionLegalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlInformacionLegalLayout = new javax.swing.GroupLayout(pnlInformacionLegal);
        pnlInformacionLegal.setLayout(pnlInformacionLegalLayout);
        pnlInformacionLegalLayout.setHorizontalGroup(
            pnlInformacionLegalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInformacionLegalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlInformacionLegalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlInformacionLegalLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnGuardarInformacionLegal, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlInformacionLegalLayout.setVerticalGroup(
            pnlInformacionLegalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInformacionLegalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardarInformacionLegal, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlPaquetes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlTarifas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(5, 5, 5)
                        .addComponent(pnlInformacionLegal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlTarifas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlInformacionLegal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlPaquetes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarTarifasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarTarifasActionPerformed
        if (tblTarifas.isEditing()) {
            tblTarifas.getCellEditor().stopCellEditing();
        }
        try {
            DefaultTableModel modeloTarifas = (DefaultTableModel) tblTarifas.getModel();
            for (int i = 0; i < modeloTarifas.getRowCount(); i++) {
                sqlIngresos().actualizarTarifa(modeloTarifas.getValueAt(i, 0).toString(), leerValor(modeloTarifas.getValueAt(i, 2).toString()));
            }
            controladorAlertas.alertSuccess("Tarifas guardadas con éxito");
            recargar();
        } catch (IllegalStateException ex) {
            controladorAlertas.alertFail(ex.getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(PanelTarifasPlanes.class.getName()).log(Level.SEVERE, "Error al guardar tarifas", ex);
            controladorAlertas.alertFail("Error al guardar las tarifas");
        }
    }//GEN-LAST:event_btnGuardarTarifasActionPerformed

    private void btnNuevoPlanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoPlanActionPerformed
        limpiarFormulario();
    }//GEN-LAST:event_btnNuevoPlanActionPerformed

    private void btnGuardarPlanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarPlanActionPerformed
        try {
            int documentos;
            try {
                documentos = Integer.parseInt(txtDocumentos.getText().replaceAll("[^0-9]", ""));
            } catch (NumberFormatException ex) {
                throw new IllegalStateException("Ingrese un número de documentos válido");
            }
            modeloPlanFacturacion plan = new modeloPlanFacturacion(idPlanEditado, txtNombrePlan.getText(),
                    cmbTipoPlan.getSelectedIndex() == 0 ? modeloPlanFacturacion.TIPO_MENSUAL : modeloPlanFacturacion.TIPO_FIJO,
                    documentos, leerValor(txtPrecio.getText()), chkActivo.isSelected());
            sqlIngresos().guardarPlan(plan);
            controladorAlertas.alertSuccess("Paquete guardado con éxito");
            recargar();
        } catch (IllegalStateException ex) {
            controladorAlertas.alertFail(ex.getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(PanelTarifasPlanes.class.getName()).log(Level.SEVERE, "Error al guardar el paquete", ex);
            controladorAlertas.alertFail("Error al guardar el paquete");
        }
    }//GEN-LAST:event_btnGuardarPlanActionPerformed

    private void tblPlanesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPlanesMouseClicked
        int fila = tblPlanes.getSelectedRow();
        if (fila >= 0) {
            cargarPlan(planes.get(tblPlanes.convertRowIndexToModel(fila)));
        }
    }//GEN-LAST:event_tblPlanesMouseClicked

    private void txtDocumentosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDocumentosKeyTyped
        if (!Character.isDigit(evt.getKeyChar())) {
            evt.consume();
        }
    }//GEN-LAST:event_txtDocumentosKeyTyped

    private void txtDocumentosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDocumentosKeyReleased
        String digitos = txtDocumentos.getText().replaceAll("[^0-9]", "");
        String formateado = digitos.isEmpty() ? "" : big.setNumero(new BigDecimal(digitos));
        if (!formateado.equals(txtDocumentos.getText())) {
            txtDocumentos.setText(formateado);
        }
    }//GEN-LAST:event_txtDocumentosKeyReleased

    private void txtPrecioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioKeyTyped
        if (!Character.isDigit(evt.getKeyChar())) {
            evt.consume();
        }
    }//GEN-LAST:event_txtPrecioKeyTyped

    private void txtPrecioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioKeyReleased
        String digitos = txtPrecio.getText().replaceAll("[^0-9]", "");
        String formateado = digitos.isEmpty() ? "" : big.setMoneda(new BigDecimal(digitos));
        if (!formateado.equals(txtPrecio.getText())) {
            txtPrecio.setText(formateado);
        }
    }//GEN-LAST:event_txtPrecioKeyReleased

    private void btnGuardarInformacionLegalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarInformacionLegalActionPerformed
        try {
            sqlIngresos().guardarParametroGlobal(SQLIngresos.PARAMETRO_INFORMACION_LEGAL, txtInformacionLegal.getText().trim());
            controladorAlertas.alertSuccess("Información legal guardada para todos los clientes");
        } catch (SQLException ex) {
            Logger.getLogger(PanelTarifasPlanes.class.getName()).log(Level.SEVERE, "Error al guardar la información legal", ex);
            controladorAlertas.alertFail("Error al guardar la información legal");
        }
    }//GEN-LAST:event_btnGuardarInformacionLegalActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardarInformacionLegal;
    private javax.swing.JButton btnGuardarPlan;
    private javax.swing.JButton btnGuardarTarifas;
    private javax.swing.JButton btnNuevoPlan;
    private javax.swing.JCheckBox chkActivo;
    private javax.swing.JComboBox<String> cmbTipoPlan;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbDocumentos;
    private javax.swing.JLabel lbNombrePlan;
    private javax.swing.JLabel lbPrecio;
    private javax.swing.JLabel lbTipoPlan;
    private javax.swing.JPanel pnlInformacionLegal;
    private javax.swing.JPanel pnlPaquetes;
    private javax.swing.JPanel pnlTarifas;
    private javax.swing.JTable tblPlanes;
    private javax.swing.JTable tblTarifas;
    private javax.swing.JTextField txtDocumentos;
    private javax.swing.JTextArea txtInformacionLegal;
    private javax.swing.JTextField txtNombrePlan;
    private javax.swing.JTextField txtPrecio;
    // End of variables declaration//GEN-END:variables
}
