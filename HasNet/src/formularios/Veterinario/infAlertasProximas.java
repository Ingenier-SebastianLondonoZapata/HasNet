package formularios.Veterinario;

import clases.Instancias;
import Utilidades.BaseDatos.SQL;
import clases.metodosGenerales;
import java.awt.Dimension;
import java.util.Calendar;
import javax.swing.JComponent;
import javax.swing.table.DefaultTableModel;

public class infAlertasProximas extends javax.swing.JInternalFrame {

    metodosGenerales metodos = new metodosGenerales();
    private Instancias instancias;
    private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    DefaultTableModel modelo;

    public infAlertasProximas() {
        initComponents();
        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
        Barra.setSize(0, 0);
        Barra.setPreferredSize(new Dimension(0, 0));
        setBorder(null);
        repaint();
        instancias = Instancias.getInstancias();
        dtFecha.setSelectedDate(Calendar.getInstance());
    }

    public void cargarTabla() {
        String fecha = metodos.desdeDate(dtFecha.getSelectedDate());

        if (cmbTipo.getSelectedIndex() == 1) {
            tblRegistros.setModel(instancias.getSql().getRegistrosAlertasVacunas(fecha));
            if (tblRegistros.getColumnModel().getColumnCount() > 0) {
                tblRegistros.getColumnModel().getColumn(9).setMinWidth(0);
                tblRegistros.getColumnModel().getColumn(9).setPreferredWidth(0);
                tblRegistros.getColumnModel().getColumn(9).setMaxWidth(0);
            }
        } else {
            tblRegistros.setModel(instancias.getSql().getRegistrosAlertasPeluquerias(fecha));
            if (tblRegistros.getColumnModel().getColumnCount() > 0) {
                tblRegistros.getColumnModel().getColumn(9).setMinWidth(0);
                tblRegistros.getColumnModel().getColumn(9).setPreferredWidth(0);
                tblRegistros.getColumnModel().getColumn(9).setMaxWidth(0);
            }
        }

        for (int i = 0; i < tblRegistros.getRowCount(); i++) {
            tblRegistros.setValueAt(metodos.fecha(tblRegistros.getValueAt(i, 6).toString()), i, 6);
            tblRegistros.setValueAt(metodos.fecha(tblRegistros.getValueAt(i, 7).toString()), i, 7);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrFormulario = new javax.swing.JScrollPane();
        pnlFormulario = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblRegistros = new javax.swing.JTable();
        dtFecha = new datechooser.beans.DateChooserCombo();
        jLabel1 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        cmbTipo = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();

        setTitle("Factura");

        pnlFormulario.setBackground(new java.awt.Color(255, 255, 255));

        tblRegistros.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        tblRegistros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cod Mascota", "Mascota", "Cliente", "Teléfono", "Celular", "Vacuna", "Fecha Vacuna", "Fecha Sig", "Se Gestionó", "id"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblRegistros.setRowHeight(24);
        tblRegistros.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblRegistros);
        if (tblRegistros.getColumnModel().getColumnCount() > 0) {
            tblRegistros.getColumnModel().getColumn(9).setMinWidth(0);
            tblRegistros.getColumnModel().getColumn(9).setPreferredWidth(0);
            tblRegistros.getColumnModel().getColumn(9).setMaxWidth(0);
        }

        dtFecha.setWeekStyle(datechooser.view.WeekDaysStyle.SHORT);
        try {
            dtFecha.setDefaultPeriods(new datechooser.model.multiple.PeriodSet(new datechooser.model.multiple.Period(new java.util.GregorianCalendar(2014, 3, 1),
                new java.util.GregorianCalendar(2014, 3, 1))));
    } catch (datechooser.model.exeptions.IncompatibleDataExeption e1) {
        e1.printStackTrace();
    }
    dtFecha.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 16));
    dtFecha.addCommitListener(new datechooser.events.CommitListener() {
        public void onCommit(datechooser.events.CommitEvent evt) {
            dtFechaOnCommit(evt);
        }
    });

    jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
    jLabel1.setText("FECHA:");

    btnGuardar.setBackground(new java.awt.Color(46, 204, 113));
    btnGuardar.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
    btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
    btnGuardar.setText("GUARDAR");
    btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
    btnGuardar.setMargin(new java.awt.Insets(2, 7, 2, 5));
    btnGuardar.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnGuardarActionPerformed(evt);
        }
    });
    btnGuardar.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyReleased(java.awt.event.KeyEvent evt) {
            btnGuardarKeyReleased(evt);
        }
    });

    jButton3.setBackground(new java.awt.Color(241, 148, 138));
    jButton3.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
    jButton3.setText("REPORTES");
    jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    jButton3.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton3ActionPerformed(evt);
        }
    });

    cmbTipo.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
    cmbTipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "PELUQUERIA", "VACUNACIÓN" }));
    cmbTipo.addItemListener(new java.awt.event.ItemListener() {
        public void itemStateChanged(java.awt.event.ItemEvent evt) {
            cmbTipoItemStateChanged(evt);
        }
    });

    jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
    jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabel2.setText("TIPO:");

    javax.swing.GroupLayout pnlFormularioLayout = new javax.swing.GroupLayout(pnlFormulario);
    pnlFormulario.setLayout(pnlFormularioLayout);
    pnlFormularioLayout.setHorizontalGroup(
        pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
        .addGroup(pnlFormularioLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 802, Short.MAX_VALUE)
                .addGroup(pnlFormularioLayout.createSequentialGroup()
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(dtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(cmbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(pnlFormularioLayout.createSequentialGroup()
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addContainerGap())
    );
    pnlFormularioLayout.setVerticalGroup(
        pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(pnlFormularioLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                .addComponent(dtFecha, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cmbTipo, javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGap(10, 10, 10)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGap(20, 20, 20))
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

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        Boolean entro = false;
        for (int i = 0; i < tblRegistros.getRowCount(); i++) {
            if (tblRegistros.getValueAt(i, 8).equals(true)) {
                entro = true;
            }
        }

        if (entro) {
            if (metodos.msgPregunta(null, "¿Desea continuar?") == 0) {
                for (int i = 0; i < tblRegistros.getRowCount(); i++) {
                    if (tblRegistros.getValueAt(i, 8).equals(true)) {
                        String id = tblRegistros.getValueAt(i, 9).toString();

                        if (cmbTipo.getSelectedIndex() == 1) {
                            if (!instancias.getSql().modificarGestionVacunacion(id, true, metodos.fechaConsulta(metodosGenerales.fechaHora()))) {
                                metodos.msgError(null, "Error al modificar la alerta: " + id);
                                return;
                            }
                        } else {
                            if (!instancias.getSql().modificarGestionPeluqueria(id, true, metodos.fechaConsulta(metodosGenerales.fechaHora()))) {
                                metodos.msgError(null, "Error al modificar la alerta: " + id);
                                return;
                            }
                        }
                    }
                }
                metodos.msgExito(this, "Modificado con exito");
                cargarTabla();
            }
        } else {
            metodos.msgError(this, "No ha seleccionado ninguno");
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnGuardarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnGuardarKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGuardarKeyReleased

    private void dtFechaOnCommit(datechooser.events.CommitEvent evt) {//GEN-FIRST:event_dtFechaOnCommit
        cargarTabla();
    }//GEN-LAST:event_dtFechaOnCommit

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        infRepAlertasProximas buscar = new infRepAlertasProximas(instancias.getMenu(), rootPaneCheckingEnabled);
        buscar.setLocationRelativeTo(null);
        instancias.setRepAlertasProximas(buscar);
        buscar.setInstancia(instancias);
        buscar.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void cmbTipoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbTipoItemStateChanged
        cargarTabla();
    }//GEN-LAST:event_cmbTipoItemStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox cmbTipo;
    private datechooser.beans.DateChooserCombo dtFecha;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnlFormulario;
    private javax.swing.JScrollPane scrFormulario;
    private javax.swing.JTable tblRegistros;
    // End of variables declaration//GEN-END:variables
}
