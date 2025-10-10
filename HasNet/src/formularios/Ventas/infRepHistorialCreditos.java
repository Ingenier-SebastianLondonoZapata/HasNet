package formularios.Ventas;

import clases.Instancias;
import Utilidades.BaseDatos.SQL;
import clases.metodosGenerales;
import Modelo.Terceros.ModeloContacto;
import formularios.terceros.buscClientes;
import java.awt.event.KeyEvent;
import java.util.Calendar;
import javax.swing.JComboBox;

public class infRepHistorialCreditos extends javax.swing.JPanel {

    metodosGenerales metodos = new metodosGenerales();
    private Instancias instancias;

    public infRepHistorialCreditos() {
        initComponents();
        instancias = Instancias.getInstancias();
        dtInicio.setSelectedDate(Calendar.getInstance());
        dtFinal.setSelectedDate(Calendar.getInstance());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrFormulario = new javax.swing.JScrollPane();
        pnlFormulario = new javax.swing.JPanel();
        lbNit10 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        dtInicio = new datechooser.beans.DateChooserCombo();
        lbNit8 = new javax.swing.JLabel();
        lbNit9 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        dtFinal = new datechooser.beans.DateChooserCombo();
        btnImpresa = new javax.swing.JRadioButton();
        btnExcel = new javax.swing.JRadioButton();
        lbNit11 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnRemCliente = new javax.swing.JButton();
        txtCliente = new javax.swing.JTextField();
        lbNit2 = new javax.swing.JLabel();
        btnAggCliente = new javax.swing.JButton();
        lbNit = new javax.swing.JLabel();
        cmbCientes = new javax.swing.JComboBox();
        txtNomClient = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        btnRemCliente1 = new javax.swing.JButton();
        lbNit3 = new javax.swing.JLabel();
        btnAggCliente1 = new javax.swing.JButton();
        cmbCientes1 = new javax.swing.JComboBox();
        cmbVendedor = new javax.swing.JComboBox();

        pnlFormulario.setBackground(new java.awt.Color(255, 255, 255));

        lbNit10.setFont(new java.awt.Font("Bell MT", 1, 36)); // NOI18N
        lbNit10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit10.setText("REPORTE DE CREDITOS");

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 14))); // NOI18N

        try {
            dtInicio.setDefaultPeriods(new datechooser.model.multiple.PeriodSet(new datechooser.model.multiple.Period(new java.util.GregorianCalendar(2014, 3, 1),
                new java.util.GregorianCalendar(2014, 3, 1))));
    } catch (datechooser.model.exeptions.IncompatibleDataExeption e1) {
        e1.printStackTrace();
    }
    dtInicio.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 12));

    lbNit8.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
    lbNit8.setText("Fecha inicial");

    lbNit9.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
    lbNit9.setText("Fecha final");

    jButton1.setBackground(new java.awt.Color(153, 255, 102));
    jButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
    jButton1.setText("GENERAR");
    jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    jButton1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton1ActionPerformed(evt);
        }
    });

    try {
        dtFinal.setDefaultPeriods(new datechooser.model.multiple.PeriodSet(new datechooser.model.multiple.Period(new java.util.GregorianCalendar(2014, 3, 1),
            new java.util.GregorianCalendar(2014, 3, 1))));
} catch (datechooser.model.exeptions.IncompatibleDataExeption e1) {
    e1.printStackTrace();
    }
    dtFinal.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 12));

    btnImpresa.setBackground(new java.awt.Color(255, 255, 255));
    btnImpresa.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
    btnImpresa.setSelected(true);
    btnImpresa.setText("En pantalla");
    btnImpresa.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    btnImpresa.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

    btnExcel.setBackground(new java.awt.Color(255, 255, 255));
    btnExcel.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
    btnExcel.setText("Hoja de calculo");
    btnExcel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    btnExcel.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

    lbNit11.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
    lbNit11.setText("Versión:");

    javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
    jPanel6.setLayout(jPanel6Layout);
    jPanel6Layout.setHorizontalGroup(
        jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel6Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lbNit8)
                .addComponent(lbNit11))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addComponent(dtInicio, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                    .addGap(18, 18, 18)
                    .addComponent(lbNit9)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(dtFinal, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                    .addGap(38, 38, 38)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addComponent(btnImpresa)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(btnExcel)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addContainerGap())
    );
    jPanel6Layout.setVerticalGroup(
        jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel6Layout.createSequentialGroup()
            .addGap(13, 13, 13)
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addComponent(jButton1)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnImpresa)
                        .addComponent(btnExcel)
                        .addComponent(lbNit11))
                    .addGap(0, 11, Short.MAX_VALUE))
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lbNit9)
                        .addComponent(lbNit8)
                        .addComponent(dtInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(dtFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
    );

    jPanel2.setBackground(new java.awt.Color(255, 255, 255));
    jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 14))); // NOI18N

    btnRemCliente.setBackground(new java.awt.Color(255, 102, 102));
    btnRemCliente.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    btnRemCliente.setForeground(new java.awt.Color(153, 0, 0));
    btnRemCliente.setText("REMOVER SELECCIÓN");
    btnRemCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    btnRemCliente.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnRemClienteActionPerformed(evt);
        }
    });

    txtCliente.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyReleased(java.awt.event.KeyEvent evt) {
            txtClienteKeyReleased(evt);
        }
    });

    lbNit2.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
    lbNit2.setText("Nombre:");

    btnAggCliente.setBackground(new java.awt.Color(102, 153, 0));
    btnAggCliente.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    btnAggCliente.setForeground(new java.awt.Color(255, 255, 255));
    btnAggCliente.setText("AGREGAR");
    btnAggCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    btnAggCliente.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnAggClienteActionPerformed(evt);
        }
    });

    lbNit.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
    lbNit.setText("CC/Nit:");

    txtNomClient.setEditable(false);

    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
    jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel2Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(lbNit2, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                .addComponent(lbNit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(txtNomClient)
                .addComponent(txtCliente))
            .addGap(18, 18, 18)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(btnRemCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAggCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(18, 18, 18)
            .addComponent(cmbCientes, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addContainerGap())
    );
    jPanel2Layout.setVerticalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel2Layout.createSequentialGroup()
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbNit)
                        .addComponent(btnAggCliente))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNomClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbNit2)
                        .addComponent(btnRemCliente)))
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(9, 9, 9)
                    .addComponent(cmbCientes, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    jPanel3.setBackground(new java.awt.Color(255, 255, 255));
    jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Vendedor", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 14))); // NOI18N

    btnRemCliente1.setBackground(new java.awt.Color(255, 102, 102));
    btnRemCliente1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    btnRemCliente1.setForeground(new java.awt.Color(153, 0, 0));
    btnRemCliente1.setText("REMOVER SELECCIÓN");
    btnRemCliente1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    btnRemCliente1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnRemCliente1ActionPerformed(evt);
        }
    });

    lbNit3.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
    lbNit3.setText("Nombre:");

    btnAggCliente1.setBackground(new java.awt.Color(102, 153, 0));
    btnAggCliente1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    btnAggCliente1.setForeground(new java.awt.Color(255, 255, 255));
    btnAggCliente1.setText("AGREGAR");
    btnAggCliente1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    btnAggCliente1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnAggCliente1ActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
    jPanel3.setLayout(jPanel3Layout);
    jPanel3Layout.setHorizontalGroup(
        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel3Layout.createSequentialGroup()
            .addGap(4, 4, 4)
            .addComponent(lbNit3, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(cmbVendedor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGap(18, 18, 18)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(btnRemCliente1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAggCliente1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(18, 18, 18)
            .addComponent(cmbCientes1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addContainerGap())
    );
    jPanel3Layout.setVerticalGroup(
        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel3Layout.createSequentialGroup()
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addComponent(btnAggCliente1)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btnRemCliente1))
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(9, 9, 9)
                    .addComponent(cmbCientes1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cmbVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbNit3))))
            .addContainerGap())
    );

    javax.swing.GroupLayout pnlFormularioLayout = new javax.swing.GroupLayout(pnlFormulario);
    pnlFormulario.setLayout(pnlFormularioLayout);
    pnlFormularioLayout.setHorizontalGroup(
        pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(lbNit10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFormularioLayout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    pnlFormularioLayout.setVerticalGroup(
        pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(pnlFormularioLayout.createSequentialGroup()
            .addComponent(lbNit10, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        .addComponent(scrFormulario, javax.swing.GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE)
    );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        if (metodos.msgPregunta(null, "¿Esta seguro?") == 0) {
            String sql = "where";

            if (cmbCientes.getItemCount() > 0) {
                sql = sql + " (";

                for (int i = 0; i < cmbCientes.getItemCount(); i++) {
                    sql = sql + " cliente='" + cmbCientes.getItemAt(i) + "' OR";
                }

                sql = sql.substring(0, sql.length() - 2) + ") AND";
            }

            if (cmbCientes1.getItemCount() > 0) {
                sql = sql + " (";

                for (int i = 0; i < cmbCientes1.getItemCount(); i++) {
                    sql = sql + " vendedor='" + cmbCientes1.getItemAt(i) + "' OR";
                }

                sql = sql.substring(0, sql.length() - 2) + ") AND";
            }

            String ini = metodos.desdeDate(dtInicio.getCurrent()), fin = metodos.desdeDate(dtFinal.getCurrent());

            sql = sql + " (((fechaFactura)>=#" + ini + "#) And ((fechaFactura)<=#" + fin + "#))";

            System.out.println(sql);

            // sql = sql + " ((fechaFactura)>=#" + "01/04/2014" + "# And (fechaFactura)<=#" + "4/5/2014" + "#)";
            String enca = "";

            dtInicio.setFormat(0);
            dtFinal.setFormat(0);

            if (dtInicio.getText().equals(dtFinal.getText())) {
                enca = "Creditos del " + ini;
            } else {
                enca = "Creditos entre el " + ini + " y el " + fin;
            }

            dtInicio.setFormat(2);
            dtFinal.setFormat(2);

            String tipo = "";

            if (btnExcel.isSelected()) {
                tipo = "xls";
                instancias.setRutaAguardar(metodos.obtenerRuta(null, "Reporte de creditos " + metodosGenerales.dia() + " de " + metodosGenerales.mesEnPalabra() + " del " + metodosGenerales.anho()));
            }

            instancias.getReporte().ver_RepCreditos(sql, enca, tipo);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnRemClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemClienteActionPerformed
        cmbCientes.removeItem(cmbCientes.getSelectedItem());
    }//GEN-LAST:event_btnRemClienteActionPerformed

    private void txtClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClienteKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cargarCliente(txtCliente.getText());
        } else {
            txtNomClient.setText("");
        }
    }//GEN-LAST:event_txtClienteKeyReleased

    private void btnAggClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAggClienteActionPerformed

        if (txtNomClient.getText().equals("")) {
            metodos.msgError(null, "Cargue primero un cliente");
        } else {

            if (yaEsta(cmbCientes, txtCliente.getText())) {
                metodos.msgAdvertencia(null, "Ya cargo este cliente");
            } else {
                cmbCientes.addItem(txtCliente.getText());
                cmbCientes.setSelectedItem(txtCliente.getText());
            }

            txtCliente.setText("");
            txtNomClient.setText("");
        }
    }//GEN-LAST:event_btnAggClienteActionPerformed

    private void btnRemCliente1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemCliente1ActionPerformed
        cmbCientes1.removeItem(cmbCientes1.getSelectedItem());
    }//GEN-LAST:event_btnRemCliente1ActionPerformed

    private void btnAggCliente1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAggCliente1ActionPerformed
        if (yaEsta(cmbCientes1, cmbVendedor.getSelectedItem().toString())) {
            metodos.msgAdvertencia(null, "Ya cargo este vendedor");
        } else {
            cmbCientes1.addItem(cmbVendedor.getSelectedItem());
            cmbCientes1.setSelectedItem(cmbVendedor.getSelectedItem());
        }
    }//GEN-LAST:event_btnAggCliente1ActionPerformed

    public void cargarCliente(String nit) {

        ModeloContacto nodo = instancias.getSql().getDatosTercero(nit);

        if (nodo.getId() != null) {
            txtNomClient.setText(nodo.getNombre());
            //btnAggClienteActionPerformed(null);
            return;
        }
        ventanaTerceros(nit);
    }

    public void ventanaTerceros(String nit) {
        buscClientes buscar = new buscClientes(instancias.getMenu(), true, false, null, "");
        buscar.setLocationRelativeTo(null);
        instancias.setBusClientes(buscar);
        instancias.setCampoActual(txtCliente);
        txtCliente.requestFocus();
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
    private javax.swing.JButton btnAggCliente1;
    private javax.swing.JRadioButton btnExcel;
    private javax.swing.JRadioButton btnImpresa;
    private javax.swing.JButton btnRemCliente;
    private javax.swing.JButton btnRemCliente1;
    private javax.swing.JComboBox cmbCientes;
    private javax.swing.JComboBox cmbCientes1;
    private javax.swing.JComboBox cmbVendedor;
    private datechooser.beans.DateChooserCombo dtFinal;
    private datechooser.beans.DateChooserCombo dtInicio;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JLabel lbNit;
    private javax.swing.JLabel lbNit10;
    private javax.swing.JLabel lbNit11;
    private javax.swing.JLabel lbNit2;
    private javax.swing.JLabel lbNit3;
    private javax.swing.JLabel lbNit8;
    private javax.swing.JLabel lbNit9;
    private javax.swing.JPanel pnlFormulario;
    private javax.swing.JScrollPane scrFormulario;
    private javax.swing.JTextField txtCliente;
    private javax.swing.JTextField txtNomClient;
    // End of variables declaration//GEN-END:variables
}
