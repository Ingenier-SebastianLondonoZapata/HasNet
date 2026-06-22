package formularios.terceros;

import clases.Instancias;
import clases.metodosGenerales;
import java.awt.Dimension;
import java.util.Calendar;
import javax.swing.JComponent;
import javax.swing.table.DefaultTableModel;

public class infAlertasCumpleanos extends javax.swing.JInternalFrame {

    metodosGenerales metodos = new metodosGenerales();
    private Instancias instancias;
    private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    DefaultTableModel modelo;

    public infAlertasCumpleanos() {
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
//        if (cmbTipo.getSelectedIndex() == 1) {
        tblRegistros.setModel(instancias.getSql().getRegistrosAlertasCumpleanos(fecha));
        if (tblRegistros.getColumnModel().getColumnCount() > 0) {
            tblRegistros.getColumnModel().getColumn(6).setMinWidth(0);
            tblRegistros.getColumnModel().getColumn(6).setPreferredWidth(0);
            tblRegistros.getColumnModel().getColumn(6).setMaxWidth(0);
        }

        for (int i = 0; i < tblRegistros.getRowCount(); i++) {
            tblRegistros.setValueAt(metodos.fecha(tblRegistros.getValueAt(i, 2).toString()), i, 2);
        }

//        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        scrFormulario = new javax.swing.JScrollPane();
        pnlFormulario = new javax.swing.JPanel();
        lbNit10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblRegistros = new javax.swing.JTable();
        dtFecha = new datechooser.beans.DateChooserCombo();
        jLabel1 = new javax.swing.JLabel();
        lbNit8 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jMonthChooser1 = new com.toedter.calendar.JMonthChooser();
        btnExcel = new javax.swing.JRadioButton();
        btnImpresa = new javax.swing.JRadioButton();

        setTitle("Factura");

        pnlFormulario.setBackground(new java.awt.Color(255, 255, 255));

        lbNit10.setFont(new java.awt.Font("Bell MT", 1, 36)); // NOI18N
        lbNit10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit10.setText("ALERTAS CUMPLEAÑOS ");

        tblRegistros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Num Id", "Cliente", "Fecha Nac", "Teléfono", "Celular", "Dirección", "Se Gestionó"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblRegistros);
        if (tblRegistros.getColumnModel().getColumnCount() > 0) {
            tblRegistros.getColumnModel().getColumn(6).setMinWidth(0);
            tblRegistros.getColumnModel().getColumn(6).setPreferredWidth(0);
            tblRegistros.getColumnModel().getColumn(6).setMaxWidth(0);
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

    jLabel1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
    jLabel1.setText("FECHA:");

    lbNit8.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
    lbNit8.setText("Mes:");

    jButton4.setBackground(new java.awt.Color(153, 255, 102));
    jButton4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
    jButton4.setText("IMPRIMIR");
    jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    jButton4.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton4ActionPerformed(evt);
        }
    });

    btnExcel.setBackground(new java.awt.Color(255, 255, 255));
    buttonGroup1.add(btnExcel);
    btnExcel.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
    btnExcel.setText("Hoja de calculo");
    btnExcel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    btnExcel.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

    btnImpresa.setBackground(new java.awt.Color(255, 255, 255));
    buttonGroup1.add(btnImpresa);
    btnImpresa.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
    btnImpresa.setSelected(true);
    btnImpresa.setText("Impresa");
    btnImpresa.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    btnImpresa.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

    javax.swing.GroupLayout pnlFormularioLayout = new javax.swing.GroupLayout(pnlFormulario);
    pnlFormulario.setLayout(pnlFormularioLayout);
    pnlFormularioLayout.setHorizontalGroup(
        pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
        .addComponent(lbNit10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlFormularioLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlFormularioLayout.createSequentialGroup()
                    .addComponent(jLabel1)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(dtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(pnlFormularioLayout.createSequentialGroup()
                    .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1)
                        .addGroup(pnlFormularioLayout.createSequentialGroup()
                            .addComponent(lbNit8, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jMonthChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(btnImpresa)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnExcel)
                            .addGap(30, 30, 30)
                            .addComponent(jButton4)
                            .addGap(0, 0, Short.MAX_VALUE)))
                    .addContainerGap())))
    );
    pnlFormularioLayout.setVerticalGroup(
        pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(pnlFormularioLayout.createSequentialGroup()
            .addComponent(lbNit10, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(jLabel1)
                .addComponent(dtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(20, 20, 20)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE)
            .addGap(18, 18, 18)
            .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnImpresa)
                    .addComponent(btnExcel))
                .addComponent(jMonthChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lbNit8))
            .addGap(19, 19, 19))
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

    private void dtFechaOnCommit(datechooser.events.CommitEvent evt) {//GEN-FIRST:event_dtFechaOnCommit
        cargarTabla();
    }//GEN-LAST:event_dtFechaOnCommit

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        String tipo = "";

        if (btnExcel.isSelected()) {
            tipo = "xls";
            instancias.setRutaAguardar(metodos.obtenerRuta(this, "Reporte de Cumpleaños " + metodosGenerales.dia() + " de " + metodosGenerales.mesEnPalabra() + " del " + metodosGenerales.anho()));
        }

        int mes = jMonthChooser1.getMonth();
        mes = mes + 1;
        String consulta = "where Month(fecha)= '" + mes + "' ";
        instancias.getReporte().verRepAlertasCumpleanos(consulta, tipo);

    }//GEN-LAST:event_jButton4ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton btnExcel;
    private javax.swing.JRadioButton btnImpresa;
    private javax.swing.ButtonGroup buttonGroup1;
    private datechooser.beans.DateChooserCombo dtFecha;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private com.toedter.calendar.JMonthChooser jMonthChooser1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbNit10;
    private javax.swing.JLabel lbNit8;
    private javax.swing.JPanel pnlFormulario;
    private javax.swing.JScrollPane scrFormulario;
    private javax.swing.JTable tblRegistros;
    // End of variables declaration//GEN-END:variables
}
