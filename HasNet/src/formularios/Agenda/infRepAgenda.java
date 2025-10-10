package formularios.Agenda;

import formularios.terceros.buscClientes;
import clases.Instancias;
import Modelo.Terceros.ModeloContacto;
import clases.metodosGenerales;
import formularios.terceros.buscEmpleados;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.util.Calendar;
import javax.swing.JComboBox;
import javax.swing.JComponent;

public class infRepAgenda extends javax.swing.JInternalFrame {

    metodosGenerales metodos = new metodosGenerales();
    private Instancias instancias;
    private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension dimBarra = null;
   
    public infRepAgenda(String tipo) {

        initComponents();

        pnlInvisible.setVisible(false);

        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
        dimBarra = Barra.getPreferredSize();
        Barra.setSize(0, 0);
        Barra.setPreferredSize(new Dimension(0, 0));
        setBorder(null);
        repaint();
        instancias = Instancias.getInstancias();

        dtInicio.setFormat(2);
        dtFinal.setFormat(2);
        dtInicio.setSelectedDate(metodos.haciaDate2(metodosGenerales.fecha()));
        dtFinal.setSelectedDate(metodos.haciaDate2(metodosGenerales.fecha()));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoVersion = new javax.swing.ButtonGroup();
        scrFormulario = new javax.swing.JScrollPane();
        pnlFormulario = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        cmbMedico = new javax.swing.JComboBox();
        cmbPaciente = new javax.swing.JComboBox();
        jPanel11 = new javax.swing.JPanel();
        btnAggPaciente = new javax.swing.JButton();
        btnRemPaciente = new javax.swing.JButton();
        btnAggCliente = new javax.swing.JButton();
        btnRemCliente1 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        lbNit2 = new javax.swing.JLabel();
        lbNit10 = new javax.swing.JLabel();
        txtCliente = new javax.swing.JTextField();
        txtPaciente = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        lbNit8 = new javax.swing.JLabel();
        lbNit14 = new javax.swing.JLabel();
        btnImpresa = new javax.swing.JRadioButton();
        dtInicio = new datechooser.beans.DateChooserCombo();
        lbNit9 = new javax.swing.JLabel();
        dtFinal = new datechooser.beans.DateChooserCombo();
        chkExcel = new javax.swing.JRadioButton();
        jSeparator1 = new javax.swing.JSeparator();
        rdRegistradas = new javax.swing.JCheckBox();
        rdReAgendadas = new javax.swing.JCheckBox();
        rdConfirmada = new javax.swing.JCheckBox();
        rdAtendido = new javax.swing.JCheckBox();
        rdNoVino = new javax.swing.JCheckBox();
        rdCancelada = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        pnlInvisible = new javax.swing.JPanel();
        txtNomClient = new javax.swing.JTextField();

        setTitle("Factura");

        pnlFormulario.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel1.setText("Si seleccionas un solo profesional el reporte de la agenda saldra en un formato especial para este.");

        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel2.setText("NOTA:");

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbMedico, 0, 214, Short.MAX_VALUE)
                    .addComponent(cmbPaciente, 0, 214, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmbMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(cmbPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

        btnAggPaciente.setBackground(new java.awt.Color(204, 204, 204));
        btnAggPaciente.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnAggPaciente.setForeground(new java.awt.Color(255, 255, 255));
        btnAggPaciente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/agregar.png"))); // NOI18N
        btnAggPaciente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAggPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAggPacienteActionPerformed(evt);
            }
        });

        btnRemPaciente.setBackground(new java.awt.Color(204, 204, 204));
        btnRemPaciente.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnRemPaciente.setForeground(new java.awt.Color(153, 0, 0));
        btnRemPaciente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/borrar2.png"))); // NOI18N
        btnRemPaciente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRemPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemPacienteActionPerformed(evt);
            }
        });

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

        btnRemCliente1.setBackground(new java.awt.Color(204, 204, 204));
        btnRemCliente1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnRemCliente1.setForeground(new java.awt.Color(153, 0, 0));
        btnRemCliente1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/borrar2.png"))); // NOI18N
        btnRemCliente1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRemCliente1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemCliente1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(btnAggCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRemCliente1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(btnAggPaciente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRemPaciente)))
                .addContainerGap(11, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAggCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRemCliente1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(37, 37, 37)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRemPaciente)
                    .addComponent(btnAggPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        lbNit2.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit2.setText("Profesional:");

        lbNit10.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit10.setText("Cliente:");

        txtCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtClienteKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtClienteKeyReleased(evt);
            }
        });

        txtPaciente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPacienteKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPacienteKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCliente, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtPaciente)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbNit2)
                            .addComponent(lbNit10))
                        .addGap(0, 149, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(lbNit2)
                .addGap(0, 0, 0)
                .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(lbNit10)
                .addGap(0, 0, 0)
                .addComponent(txtPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        lbNit8.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lbNit8.setText("Fecha inicial:");

        lbNit14.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lbNit14.setText("Versión:");

        btnImpresa.setBackground(new java.awt.Color(255, 255, 255));
        btnImpresa.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnImpresa.setSelected(true);
        btnImpresa.setText("En pantalla");
        btnImpresa.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnImpresa.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        dtInicio.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 12));

        lbNit9.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lbNit9.setText("Fecha final:");

        dtFinal.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 12));

        chkExcel.setBackground(new java.awt.Color(255, 255, 255));
        chkExcel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        chkExcel.setText("Hoja de calculo");
        chkExcel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        chkExcel.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        rdRegistradas.setBackground(new java.awt.Color(255, 255, 255));
        rdRegistradas.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        rdRegistradas.setSelected(true);
        rdRegistradas.setText("Registradas");

        rdReAgendadas.setBackground(new java.awt.Color(255, 255, 255));
        rdReAgendadas.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        rdReAgendadas.setSelected(true);
        rdReAgendadas.setText("Reagendada");

        rdConfirmada.setBackground(new java.awt.Color(255, 255, 255));
        rdConfirmada.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        rdConfirmada.setText("Confirmada");

        rdAtendido.setBackground(new java.awt.Color(255, 255, 255));
        rdAtendido.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        rdAtendido.setText("Atendido");

        rdNoVino.setBackground(new java.awt.Color(255, 255, 255));
        rdNoVino.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        rdNoVino.setText("No vino");

        rdCancelada.setBackground(new java.awt.Color(255, 255, 255));
        rdCancelada.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        rdCancelada.setText("Cancelada");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbNit8)
                            .addComponent(lbNit14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dtInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnImpresa))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(lbNit9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dtFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(chkExcel)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(rdRegistradas)
                        .addGap(5, 5, 5)
                        .addComponent(rdReAgendadas)
                        .addGap(5, 5, 5)
                        .addComponent(rdConfirmada)
                        .addGap(5, 5, 5)
                        .addComponent(rdAtendido)
                        .addGap(5, 5, 5)
                        .addComponent(rdNoVino, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(rdCancelada)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdRegistradas)
                    .addComponent(rdReAgendadas)
                    .addComponent(rdConfirmada)
                    .addComponent(rdAtendido)
                    .addComponent(rdNoVino)
                    .addComponent(rdCancelada))
                .addGap(10, 10, 10)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbNit9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbNit8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dtInicio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dtFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnImpresa)
                    .addComponent(lbNit14)
                    .addComponent(chkExcel))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jButton4.setBackground(new java.awt.Color(46, 204, 113));
        jButton4.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jButton4.setText("IMPRIMIR");
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                .addContainerGap())
        );

        txtNomClient.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNomClientKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNomClientKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout pnlInvisibleLayout = new javax.swing.GroupLayout(pnlInvisible);
        pnlInvisible.setLayout(pnlInvisibleLayout);
        pnlInvisibleLayout.setHorizontalGroup(
            pnlInvisibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtNomClient)
        );
        pnlInvisibleLayout.setVerticalGroup(
            pnlInvisibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlInvisibleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtNomClient, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlFormularioLayout = new javax.swing.GroupLayout(pnlFormulario);
        pnlFormulario.setLayout(pnlFormularioLayout);
        pnlFormularioLayout.setHorizontalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlFormularioLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlFormularioLayout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlFormularioLayout.createSequentialGroup()
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(pnlInvisible, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlFormularioLayout.setVerticalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlInvisible, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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
            .addComponent(scrFormulario, javax.swing.GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAggPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAggPacienteActionPerformed
        if (txtPaciente.getText().equals("")) {
            metodos.msgError(null, "Cargue primero un Paciente");
        } else {

            if (yaEsta(cmbPaciente, txtPaciente.getText())) {
                metodos.msgAdvertencia(null, "Ya cargo este Paciente");
            } else {
                cmbPaciente.addItem(txtPaciente.getText());
                cmbPaciente.setSelectedItem(txtPaciente.getText());
            }

            txtPaciente.setText("");
        }
    }//GEN-LAST:event_btnAggPacienteActionPerformed

    private void btnRemPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemPacienteActionPerformed
        cmbPaciente.removeItem(cmbPaciente.getSelectedItem());
    }//GEN-LAST:event_btnRemPacienteActionPerformed

    private void btnAggClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAggClienteActionPerformed
        if (txtNomClient.getText().equals("")) {
            metodos.msgError(null, "Cargue primero un Paciente");
        } else {

            if (yaEsta(cmbMedico, txtCliente.getText())) {
                metodos.msgAdvertencia(null, "Ya cargo este Paciente");
            } else {
                cmbMedico.addItem(txtNomClient.getText());
                cmbMedico.setSelectedItem(txtNomClient.getText());
            }

            txtCliente.setText("");
            txtNomClient.setText("");
        }
    }//GEN-LAST:event_btnAggClienteActionPerformed

    private void btnRemCliente1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemCliente1ActionPerformed
        cmbMedico.removeItem(cmbMedico.getSelectedItem());
    }//GEN-LAST:event_btnRemCliente1ActionPerformed

    private void txtClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClienteKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtClienteKeyPressed

    private void txtClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClienteKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cargarMedico(txtCliente.getText());
        } else {
            txtNomClient.setText("");
        }
    }//GEN-LAST:event_txtClienteKeyReleased

    private void txtPacienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPacienteKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPacienteKeyPressed

    private void txtPacienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPacienteKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cargarPaciente(txtPaciente.getText());
        }
    }//GEN-LAST:event_txtPacienteKeyReleased

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if (metodos.msgPregunta(null, "¿Esta seguro?") == 0) {
            String sql = "where";

            if (cmbMedico.getItemCount() > 0) {
                sql = sql + " (";

                for (int i = 0; i < cmbMedico.getItemCount(); i++) {
                    sql = sql + " aCargo='" + cmbMedico.getItemAt(i) + "' OR";
                }

                sql = sql.substring(0, sql.length() - 2) + ") AND";
            }

            if (cmbPaciente.getItemCount() > 0) {
                sql = sql + " (";

                for (int i = 0; i < cmbPaciente.getItemCount(); i++) {
                    sql = sql + " cliente='" + cmbPaciente.getItemAt(i) + "' OR";
                }

                sql = sql.substring(0, sql.length() - 2) + ") AND";
            }

            int rdSeleccionado = 0;
            boolean select = false;
            if (rdCancelada.isSelected() && rdConfirmada.isSelected() && rdReAgendadas.isSelected() && rdRegistradas.isSelected()) {

            } else {

                if (rdAtendido.isSelected()) {
                    rdSeleccionado++;
                }

                if (rdConfirmada.isSelected()) {
                    rdSeleccionado++;
                }

                if (rdReAgendadas.isSelected()) {
                    rdSeleccionado++;
                }

                if (rdRegistradas.isSelected()) {
                    rdSeleccionado++;
                }

                if (rdNoVino.isSelected()) {
                    rdSeleccionado++;
                }

                if (rdCancelada.isSelected()) {
                    rdSeleccionado++;
                }

                if (rdSeleccionado > 0) {
                    sql = sql + " (";
                    select = true;
                }

                if (rdAtendido.isSelected()) {
                    sql = sql + "(estado = 'Atendido') OR";
                    rdSeleccionado--;
                }

                if (rdConfirmada.isSelected()) {
                    sql = sql + "(estado = 'Confirmada') OR";
                    rdSeleccionado--;
                }
                if (rdReAgendadas.isSelected()) {
                    sql = sql + "(estado = 'Reagendada') OR";
                    rdSeleccionado--;
                }
                if (rdRegistradas.isSelected()) {
                    sql = sql + "(estado = 'Registrada') OR";
                    rdSeleccionado--;
                }
                if (rdNoVino.isSelected()) {
                    sql = sql + "(estado = 'No Vino') OR";
                    rdSeleccionado--;
                }
                if (rdCancelada.isSelected()) {
                    sql = sql + "(estado = 'Cancelada') OR";
                    rdSeleccionado--;
                }
            }

            if (select) {
                sql = sql.substring(0, sql.length() - 2) + ") AND";
            }

            String ini = metodos.desdeDate(dtInicio.getCurrent()), fin = metodos.desdeDate(dtFinal.getCurrent());

            sql = sql + " (((fecha)>= '" + ini + "') And ((fecha)<='" + fin + "'))";

            System.out.println("CONSULTA " + sql);

            String enca = "";
            String medico = "";

            dtInicio.setFormat(0);
            dtFinal.setFormat(0);

            dtInicio.setFormat(2);
            dtFinal.setFormat(2);
            String tipo = "";

            if (dtInicio.getText().equals(dtFinal.getText())) {
                enca = "Agenda del " + ini;
            } else {
                enca = "Agendas entre el " + ini + " y el " + fin;
            }

            if (rdReAgendadas.isSelected() && (!rdCancelada.isSelected() && !rdConfirmada.isSelected() && !rdRegistradas.isSelected())) {
                tipo = "ReAgendada";
            }

            if (cmbMedico.getItemCount() == 1) {
                tipo = "Medico";
                medico = cmbMedico.getSelectedItem().toString();
            }

            if (chkExcel.isSelected()) {
                instancias.setRutaAguardar(metodos.obtenerRuta(null, "Reporte de agendas del " + metodosGenerales.dia() + " de " + metodosGenerales.mesEnPalabra() + " del " + metodosGenerales.anho()));
                tipo = tipo + "xls";
            }

            instancias.getReporte().verRepCitasMedicas(sql, enca, tipo, medico);

        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void txtNomClientKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomClientKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomClientKeyPressed

    private void txtNomClientKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomClientKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomClientKeyReleased

    public boolean yaEsta(JComboBox combo, String dato) {
        boolean sw = false;
        for (int i = 0; i < combo.getItemCount(); i++) {
            if (((String) combo.getItemAt(i)).equals(dato)) {
                return true;
            }
        }
        return sw;
    }

    public void setInstancias(Instancias instancias) {
        this.instancias = instancias;

        dtInicio.setSelectedDate(Calendar.getInstance());
        dtFinal.setSelectedDate(Calendar.getInstance());
    }

    public void cargarMedico(String nit) {
        ModeloContacto nodo = instancias.getSql().getDatosTercero(nit);

        if (nodo.getId() != null) {
            txtNomClient.setText(nodo.getNombre());
            btnAggClienteActionPerformed(null);
            return;
        }
        ventanaEmpleado(nit);
    }

    public void cargarPaciente(String nit) {
        ModeloContacto nodo = instancias.getSql().getDatosTercero(nit);

        if (nodo.getId() != null) {
            if (nodo.isActivo()) {
                metodos.msgError(null, "Este cliente esta inactivado");
                lbNit10.requestFocus();
                return;
            } else {
                txtPaciente.setText(nodo.getIdSistema());
            }
            btnAggPacienteActionPerformed(null);
            return;
        }
        ventanaPaciente(nit);
    }

    public void ventanaPaciente(String nit) {
        buscClientes buscar = new buscClientes(instancias.getMenu(), rootPaneCheckingEnabled, false, null, "");
        buscar.setLocationRelativeTo(null);
        instancias.setBusClientes(buscar);
        instancias.setCampoActual(txtPaciente);
        txtPaciente.requestFocus();
        buscar.noEncontrado(nit);
        buscar.show();
    }

    public void ventanaEmpleado(String nit) {
        buscEmpleados buscar = new buscEmpleados(instancias.getMenu(), rootPaneCheckingEnabled);
        buscar.setLocationRelativeTo(null);
        instancias.setBuscEmpleados(buscar);
        instancias.setCampoActual(txtCliente);
        txtCliente.requestFocus();
        buscar.noEncontrado(nit);
        buscar.show();
    }

//    public void setTipo() {
//        switch (tipo) {
//            case "peluqueria":
//                lbCargo.setText("A CARGO:");
//                lbCliente.setText("CLIENTE");
//                break;
//            case "agenda":
//                lbCargo.setText("A CARGO:");
//                lbCliente.setText("CLIENTE");
//                break;
//        }
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAggCliente;
    private javax.swing.JButton btnAggPaciente;
    private javax.swing.JRadioButton btnImpresa;
    private javax.swing.JButton btnRemCliente1;
    private javax.swing.JButton btnRemPaciente;
    private javax.swing.JRadioButton chkExcel;
    private javax.swing.JComboBox cmbMedico;
    private javax.swing.JComboBox cmbPaciente;
    private datechooser.beans.DateChooserCombo dtFinal;
    private datechooser.beans.DateChooserCombo dtInicio;
    private javax.swing.ButtonGroup grupoVersion;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbNit10;
    private javax.swing.JLabel lbNit14;
    private javax.swing.JLabel lbNit2;
    private javax.swing.JLabel lbNit8;
    private javax.swing.JLabel lbNit9;
    private javax.swing.JPanel pnlFormulario;
    private javax.swing.JPanel pnlInvisible;
    private javax.swing.JCheckBox rdAtendido;
    private javax.swing.JCheckBox rdCancelada;
    private javax.swing.JCheckBox rdConfirmada;
    private javax.swing.JCheckBox rdNoVino;
    private javax.swing.JCheckBox rdReAgendadas;
    private javax.swing.JCheckBox rdRegistradas;
    private javax.swing.JScrollPane scrFormulario;
    private javax.swing.JTextField txtCliente;
    private javax.swing.JTextField txtNomClient;
    private javax.swing.JTextField txtPaciente;
    // End of variables declaration//GEN-END:variables
}
