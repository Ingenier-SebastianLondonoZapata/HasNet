package formularios.recordatorios;

import clases.Instancias;
import clases.cambiarColorTabla;
import clases.metodosGenerales;
import clases.recordatorios.ndRecordatorio;
import Modelo.Terceros.ModeloContacto;
import formularios.terceros.buscClientes;
import formularios.terceros.buscEmpleados;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class infRecordatorios extends javax.swing.JInternalFrame {

    metodosGenerales metodos = new metodosGenerales();
    private Instancias instancias;
    private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension dimBarra = null;
    DefaultTableModel modelo;
    String info;
    TableRowSorter modeloOrdenado;

    private infRecordatorios agenda;

    public infRecordatorios getAgenda() {
        return agenda;
    }

    public void setAgenda(infRecordatorios agenda) {
        this.agenda = agenda;
    }

    public void llamarRecordatorios() {
        this.show();
        try {
            this.setSelected(true);
            System.out.println("entro a llamarl el recordatorios");
        } catch (PropertyVetoException ex) {
            Logger.getLogger(infRecordatorios.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setVisible(true);
    }

    public infRecordatorios() {
        initComponents();
        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
        dimBarra = Barra.getPreferredSize();
        Barra.setSize(0, 0);
        Barra.setPreferredSize(new Dimension(0, 0));
        setBorder(null);
        repaint();
        dtFecha.setFormat(2);

        btnConfirmarCita.setEnabled(false);
        btnCancelar.setEnabled(false);

        tblRegistros.setDefaultRenderer(Object.class, new cambiarColorTabla(15, 0));

        this.instancias = instancias;
//        tblRegistros.setAutoCreateRowSorter(true);
//        modeloOrdenado = new TableRowSorter<>(modelo);
//        tblRegistros.setRowSorter(modeloOrdenado);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtId1 = new javax.swing.JTextField();
        grupoAlerta = new javax.swing.ButtonGroup();
        jLabel6 = new javax.swing.JLabel();
        scrFormulario = new javax.swing.JScrollPane();
        pnlFormulario = new javax.swing.JPanel();
        lbNit10 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lbltipodoc = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        btnConfirmarCita = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        btnLimpiar = new javax.swing.JButton();
        lblMedicoNuevo = new javax.swing.JLabel();
        cmbTipoRecordatorio = new javax.swing.JComboBox();
        btnCancelar = new javax.swing.JButton();
        lbltipodoc1 = new javax.swing.JLabel();
        txtCita = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        txtIdMotivo = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        dtFecha = new datechooser.beans.DateChooserCombo();
        lbltipodoc2 = new javax.swing.JLabel();
        cmbHora1 = new javax.swing.JComboBox();
        cmbHora2 = new javax.swing.JComboBox();
        lbltipodoc3 = new javax.swing.JLabel();
        lblMedicoNuevo1 = new javax.swing.JLabel();
        txtAsunto = new javax.swing.JTextField();
        btnBuscPaciente = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtCelular = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        lblMedicoNuevo2 = new javax.swing.JLabel();
        cmbEstado = new javax.swing.JComboBox();
        rdDia = new javax.swing.JRadioButton();
        rdHora = new javax.swing.JRadioButton();
        lblMedicoNuevo3 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        lbTipoTercero = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblRegistros = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        lblMedicoNuevo4 = new javax.swing.JLabel();
        cmbEstado1 = new javax.swing.JComboBox();
        lblMedicoNuevo5 = new javax.swing.JLabel();
        cmbTipoRecordatorio1 = new javax.swing.JComboBox();
        lblMedicoNuevo6 = new javax.swing.JLabel();
        txtFiltroNombre = new javax.swing.JTextField();

        txtId1.setName("Tipo Documento"); // NOI18N
        txtId1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtId1KeyReleased(evt);
            }
        });

        jLabel6.setText("jLabel6");

        setTitle("Factura");

        pnlFormulario.setBackground(new java.awt.Color(255, 255, 255));

        lbNit10.setFont(new java.awt.Font("Bell MT", 1, 36)); // NOI18N
        lbNit10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit10.setText("RECORDATORIOS");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lbltipodoc.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbltipodoc.setText("Tipo Documento:");

        txtId.setName("Tipo Documento"); // NOI18N
        txtId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIdKeyReleased(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jLabel3.setText("Nombre Completo:");

        txtNombre.setEditable(false);
        txtNombre.setName("Primer Nombre"); // NOI18N

        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jLabel2.setText("Telefono:");

        txtTelefono.setEditable(false);
        txtTelefono.setName("Telefono"); // NOI18N

        btnGuardar.setBackground(new java.awt.Color(0, 153, 153));
        btnGuardar.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
        btnGuardar.setText("GUARDAR   ");
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

        btnConfirmarCita.setBackground(new java.awt.Color(0, 153, 153));
        btnConfirmarCita.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnConfirmarCita.setForeground(new java.awt.Color(255, 255, 255));
        btnConfirmarCita.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/historia.png"))); // NOI18N
        btnConfirmarCita.setText("FINALIZAR");
        btnConfirmarCita.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnConfirmarCita.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnConfirmarCita.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnConfirmarCita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarCitaActionPerformed(evt);
            }
        });

        btnLimpiar.setBackground(new java.awt.Color(102, 102, 255));
        btnLimpiar.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnLimpiar.setForeground(new java.awt.Color(255, 255, 255));
        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/limpiar.png"))); // NOI18N
        btnLimpiar.setText("LIMPIAR ");
        btnLimpiar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLimpiar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnLimpiar.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        lblMedicoNuevo.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lblMedicoNuevo.setText("Tipo:");

        cmbTipoRecordatorio.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cmbTipoRecordatorio.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "LLAMADA", "RECORDATORIO", "CITA", "CARTERA", "OTRO" }));
        cmbTipoRecordatorio.setName("Nuevo Medico"); // NOI18N
        cmbTipoRecordatorio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbTipoRecordatorioItemStateChanged(evt);
            }
        });

        btnCancelar.setBackground(new java.awt.Color(204, 204, 204));
        btnCancelar.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/borrar2.png"))); // NOI18N
        btnCancelar.setText("CANCELAR");
        btnCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancelar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnCancelar.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        lbltipodoc1.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbltipodoc1.setText("Record. No.:");

        txtCita.setEditable(false);
        txtCita.setName("Tipo Documento"); // NOI18N
        txtCita.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCitaKeyReleased(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("Descripción:");

        txtDescripcion.setColumns(20);
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setRows(3);
        txtDescripcion.setDragEnabled(true);
        jScrollPane2.setViewportView(txtDescripcion);

        txtIdMotivo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIdMotivoKeyReleased(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jLabel1.setText("FECHA:");

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
    dtFecha.addSelectionChangedListener(new datechooser.events.SelectionChangedListener() {
        public void onSelectionChange(datechooser.events.SelectionChangedEvent evt) {
            dtFechaOnSelectionChange(evt);
        }
    });

    lbltipodoc2.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
    lbltipodoc2.setText("Hora:");

    cmbHora1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", " " }));

    cmbHora2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));

    lbltipodoc3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    lbltipodoc3.setText(":");

    lblMedicoNuevo1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    lblMedicoNuevo1.setText("Asunto:");

    txtAsunto.setName("Tipo Documento"); // NOI18N
    txtAsunto.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyReleased(java.awt.event.KeyEvent evt) {
            txtAsuntoKeyReleased(evt);
        }
    });

    btnBuscPaciente.setBackground(new java.awt.Color(204, 255, 204));
    btnBuscPaciente.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
    btnBuscPaciente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/listadoPequeño.png"))); // NOI18N
    btnBuscPaciente.setText("Clientes");
    btnBuscPaciente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    btnBuscPaciente.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
    btnBuscPaciente.setMargin(new java.awt.Insets(2, 14, 2, 5));
    btnBuscPaciente.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnBuscPacienteActionPerformed(evt);
        }
    });

    jLabel4.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
    jLabel4.setText("Celular:");

    txtCelular.setEditable(false);
    txtCelular.setName("Telefono"); // NOI18N

    jLabel5.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
    jLabel5.setText("Dirección:");

    txtDireccion.setEditable(false);
    txtDireccion.setName("Telefono"); // NOI18N

    lblMedicoNuevo2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
    lblMedicoNuevo2.setText("Estado:");

    cmbEstado.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
    cmbEstado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "PENDIENTE", "FINALIZADO", "CANCELADO", " " }));
    cmbEstado.setName("Nuevo Medico"); // NOI18N
    cmbEstado.addItemListener(new java.awt.event.ItemListener() {
        public void itemStateChanged(java.awt.event.ItemEvent evt) {
            cmbEstadoItemStateChanged(evt);
        }
    });

    rdDia.setBackground(new java.awt.Color(255, 255, 255));
    grupoAlerta.add(rdDia);
    rdDia.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
    rdDia.setSelected(true);
    rdDia.setText("Día");

    rdHora.setBackground(new java.awt.Color(255, 255, 255));
    grupoAlerta.add(rdHora);
    rdHora.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
    rdHora.setText("Hora");

    lblMedicoNuevo3.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
    lblMedicoNuevo3.setText("Alerta Por:");

    lbTipoTercero.setForeground(new java.awt.Color(255, 255, 255));

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jSeparator1)
        .addComponent(jSeparator2)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(lbltipodoc1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtCita, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(lblMedicoNuevo3)
                            .addGap(18, 18, 18)
                            .addComponent(rdDia, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(rdHora))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(lblMedicoNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(10, 10, 10)
                            .addComponent(cmbTipoRecordatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtIdMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)
                            .addComponent(btnBuscPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbltipodoc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(10, 10, 10)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
                                        .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtDireccion))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(12, 12, 12)
                                        .addComponent(lbTipoTercero))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                                    .addComponent(btnConfirmarCita, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(15, 15, 15)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnLimpiar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(lblMedicoNuevo2, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                                    .addComponent(lblMedicoNuevo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(10, 10, 10)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cmbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtAsunto, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbltipodoc2, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cmbHora1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(lbltipodoc3, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(cmbHora2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49))))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(rdDia)
                .addComponent(rdHora)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMedicoNuevo3)
                    .addComponent(lbltipodoc1)))
            .addGap(5, 5, 5)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel1)
                        .addComponent(dtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(70, 70, 70)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblMedicoNuevo)
                        .addComponent(cmbTipoRecordatorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(1, 1, 1)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbltipodoc2)
                        .addComponent(cmbHora1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmbHora2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbltipodoc3))))
            .addGap(39, 39, 39)
            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(btnBuscPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(15, 15, 15)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lbltipodoc)
                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lbTipoTercero))
            .addGap(10, 10, 10)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel3)
                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(10, 10, 10)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel2)
                .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(10, 10, 10)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel4)
                .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(13, 13, 13)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel5)
                .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(18, 18, 18)
            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(15, 15, 15)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblMedicoNuevo1)
                .addComponent(txtAsunto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel12)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(15, 15, 15)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblMedicoNuevo2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(cmbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(25, 25, 25)
            .addComponent(txtIdMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 0, 0)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnGuardar)
                .addComponent(btnLimpiar))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnConfirmarCita)
                .addComponent(btnCancelar))
            .addGap(15, 15, 15))
    );

    tblRegistros.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    tblRegistros.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {
            "Id", "Fecha", "Hora", "Tipo", "Tercero", "Nombre", "Asunto", "Descripción", "estado", "edad"
        }
    ) {
        boolean[] canEdit = new boolean [] {
            false, false, false, false, false, false, false, false, false, false
        };

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit [columnIndex];
        }
    });
    tblRegistros.setRowHeight(18);
    tblRegistros.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            tblRegistrosMouseClicked(evt);
        }
    });
    jScrollPane1.setViewportView(tblRegistros);
    if (tblRegistros.getColumnModel().getColumnCount() > 0) {
        tblRegistros.getColumnModel().getColumn(0).setMinWidth(0);
        tblRegistros.getColumnModel().getColumn(0).setPreferredWidth(0);
        tblRegistros.getColumnModel().getColumn(0).setMaxWidth(0);
        tblRegistros.getColumnModel().getColumn(1).setPreferredWidth(100);
        tblRegistros.getColumnModel().getColumn(1).setMaxWidth(100);
        tblRegistros.getColumnModel().getColumn(2).setPreferredWidth(70);
        tblRegistros.getColumnModel().getColumn(2).setMaxWidth(70);
        tblRegistros.getColumnModel().getColumn(3).setPreferredWidth(100);
        tblRegistros.getColumnModel().getColumn(3).setMaxWidth(100);
        tblRegistros.getColumnModel().getColumn(4).setPreferredWidth(120);
        tblRegistros.getColumnModel().getColumn(4).setMaxWidth(120);
        tblRegistros.getColumnModel().getColumn(5).setPreferredWidth(150);
        tblRegistros.getColumnModel().getColumn(5).setMaxWidth(150);
        tblRegistros.getColumnModel().getColumn(6).setPreferredWidth(150);
    }

    jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            jPanel2MouseEntered(evt);
        }
    });
    jPanel2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
        public void mouseMoved(java.awt.event.MouseEvent evt) {
            jPanel2MouseMoved(evt);
        }
    });
    jPanel2.addFocusListener(new java.awt.event.FocusAdapter() {
        public void focusGained(java.awt.event.FocusEvent evt) {
            jPanel2FocusGained(evt);
        }
    });
    jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    jPanel3.setBackground(new java.awt.Color(255, 255, 255));
    jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Filtros"));

    lblMedicoNuevo4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    lblMedicoNuevo4.setText("Estado:");

    cmbEstado1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    cmbEstado1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "PENDIENTE", "FINALIZADO", "CANCELADO" }));
    cmbEstado1.setName("Nuevo Medico"); // NOI18N
    cmbEstado1.addItemListener(new java.awt.event.ItemListener() {
        public void itemStateChanged(java.awt.event.ItemEvent evt) {
            cmbEstado1ItemStateChanged(evt);
        }
    });

    lblMedicoNuevo5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    lblMedicoNuevo5.setText("Tipo:");

    cmbTipoRecordatorio1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    cmbTipoRecordatorio1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "LLAMADA", "RECORDATORIO", "CITA", "CARTERA", "OTRO" }));
    cmbTipoRecordatorio1.setName("Nuevo Medico"); // NOI18N
    cmbTipoRecordatorio1.addItemListener(new java.awt.event.ItemListener() {
        public void itemStateChanged(java.awt.event.ItemEvent evt) {
            cmbTipoRecordatorio1ItemStateChanged(evt);
        }
    });
    cmbTipoRecordatorio1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            cmbTipoRecordatorio1ActionPerformed(evt);
        }
    });

    lblMedicoNuevo6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    lblMedicoNuevo6.setText("Nombre:");

    txtFiltroNombre.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    txtFiltroNombre.setName("Primer Nombre"); // NOI18N
    txtFiltroNombre.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyReleased(java.awt.event.KeyEvent evt) {
            txtFiltroNombreKeyReleased(evt);
        }
    });

    javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
    jPanel3.setLayout(jPanel3Layout);
    jPanel3Layout.setHorizontalGroup(
        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel3Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addComponent(lblMedicoNuevo6, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                .addComponent(lblMedicoNuevo4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblMedicoNuevo5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGap(10, 10, 10)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmbEstado1, 0, 150, Short.MAX_VALUE)
                    .addComponent(cmbTipoRecordatorio1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addComponent(txtFiltroNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap())
    );
    jPanel3Layout.setVerticalGroup(
        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel3Layout.createSequentialGroup()
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblMedicoNuevo4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(cmbEstado1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(10, 10, 10)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblMedicoNuevo5)
                .addComponent(cmbTipoRecordatorio1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(10, 10, 10)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblMedicoNuevo6)
                .addComponent(txtFiltroNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap())
    );

    javax.swing.GroupLayout pnlFormularioLayout = new javax.swing.GroupLayout(pnlFormulario);
    pnlFormulario.setLayout(pnlFormularioLayout);
    pnlFormularioLayout.setHorizontalGroup(
        pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
        .addGroup(pnlFormularioLayout.createSequentialGroup()
            .addGap(30, 30, 30)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(40, 40, 40)
            .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGap(30, 30, 30))
        .addGroup(pnlFormularioLayout.createSequentialGroup()
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 0, 0)
            .addComponent(lbNit10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    pnlFormularioLayout.setVerticalGroup(
        pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(pnlFormularioLayout.createSequentialGroup()
            .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lbNit10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlFormularioLayout.createSequentialGroup()
                    .addGap(10, 10, 10)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(pnlFormularioLayout.createSequentialGroup()
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 622, Short.MAX_VALUE)))
            .addGap(20, 20, 20))
    );

    scrFormulario.setViewportView(pnlFormulario);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addComponent(scrFormulario, javax.swing.GroupLayout.DEFAULT_SIZE, 1123, Short.MAX_VALUE)
            .addGap(0, 0, 0))
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addComponent(scrFormulario, javax.swing.GroupLayout.DEFAULT_SIZE, 802, Short.MAX_VALUE)
            .addGap(4, 4, 4))
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        Object[] campos = {txtCita};
        String faltantes = metodos.camposVacios(campos);

        if (!faltantes.equals("")) {
            metodos.msgAdvertencia(this, "No puede continuar, faltan los siguientes campos: " + faltantes);
            return;
        }
        if (metodos.msgPregunta(this, "¿Desea continuar?") == 0) {
            //
//            try {
            if (btnGuardar.getText().equals("GUARDAR")) {
                String cita = instancias.getSql().getNumConsecutivo("RECORDATORIO")[0].toString();
                String tipoAlerta;
                if (rdDia.isSelected()) {
                    tipoAlerta = "dia";
                } else {
                    tipoAlerta = "hora";
                }
                Object[] vector = {cita, metodos.desdeDate(dtFecha.getCurrent()), cmbHora1.getSelectedItem() + ":" + cmbHora2.getSelectedItem(),
                    cmbTipoRecordatorio.getSelectedItem(), lbTipoTercero.getText(), txtId.getText(), txtAsunto.getText(), txtDescripcion.getText(),
                    cmbEstado.getSelectedItem(), tipoAlerta, instancias.getUsuario(), metodosGenerales.fecha()
                };

                ndRecordatorio nodo = metodos.llenarRecordatorio(vector);

                if (!instancias.getSql().agregarRecordatorio(nodo)) {
                    metodos.msgError(this, "Hubo un problema al guardar la Agenda");
                    return;
                }

                metodos.msgExito(this, "Recordatorio registrado con exito");

                if (!instancias.getSql().aumentarConsecutivo("RECORDATORIO", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("RECORDATORIO")[0]) + 1)) {
                    metodos.msgError(this, "Hubo un problema al guardar en el consecutivo de la cita");
                }
            } else {
                String tipoAlerta;
                if (rdDia.isSelected()) {
                    tipoAlerta = "dia";
                } else {
                    tipoAlerta = "hora";
                }

                ndRecordatorio nodo = instancias.getSql().getDatosRecordatorio(txtCita.getText());

                Object[] vector = {txtCita.getText(), metodos.desdeDate(dtFecha.getCurrent()), cmbHora1.getSelectedItem() + ":" + cmbHora2.getSelectedItem(),
                    cmbTipoRecordatorio.getSelectedItem(), lbTipoTercero.getText(), txtId.getText(), txtAsunto.getText(), txtDescripcion.getText(),
                    cmbEstado.getSelectedItem(), tipoAlerta, nodo.getUsuario(), nodo.getFechaRegistro()
                };
                nodo = metodos.llenarRecordatorio(vector);

                if (!instancias.getSql().modificarRecordatorio(nodo)) {
                    metodos.msgError(this, "Hubo un problema al guardar la Agenda");
                    return;
                }
                metodos.msgExito(this, "Recordatorio modificado con exito");
            }

            btnLimpiarActionPerformed(null);
            cmbEstado1.setSelectedItem("PENDIENTE");
            cargarTabla("PENDIENTE");
//            metodos.alertaRecordatorios();

        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnGuardarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnGuardarKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGuardarKeyReleased

    public void cargarTabla(String estado) {

//        Object[][] agendas = sql.getRegistrosRecordatorios(estado);
//        modelo = (DefaultTableModel) tblRegistros.getModel();
//        int j = tblRegistros.getRowCount();
//        for (int i = 0; i < j; i++) {
//            modelo.removeRow(0);
//        }
//
//        int x = 0;
//        for (Object[] agenda1 : agendas) {
//            modelo.addRow(agenda1);
//            tblRegistros.setValueAt(metodos.fecha(tblRegistros.getValueAt(x, 1).toString()), x, 1);
//            tblRegistros.setValueAt(metodos.restarFecha(metodosGenerales.fecha(), tblRegistros.getValueAt(x, 1).toString()), x, 9);
//            x++;
//        }
    }

    private void btnBuscPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscPacienteActionPerformed
        ventanaTerceros("");
    }//GEN-LAST:event_btnBuscPacienteActionPerformed

    private void txtIdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cargarCliente(txtId.getText());
        } else {
            txtNombre.setText("");
            txtTelefono.setText("");
            txtCelular.setText("");
            txtDireccion.setText("");
            lbltipodoc.setText("Tipo Documento:");

        }
    }//GEN-LAST:event_txtIdKeyReleased

//    public void desabilitarTodos() {
//        txtDescripcion.setText("");
//        txtId.setEnabled(false);
//        btnCancelar.setEnabled(false);
//        btnBuscPaciente.setEnabled(false);
//        btnConfirmarCita.setEnabled(false);
//        btnLimpiar.setEnabled(false);
//        tblRegistros.setEnabled(false);
//    }
//    public void habilitarTodos() {
////        for (int x = 0; x < jPanel1.getComponentCount(); x++) {
////            if (jPanel1.getComponent(x) instanceof JTextField) {
////                JTextField textField = (JTextField) jPanel1.getComponent(x);
////                textField.setText("");
////            }
////        }
////        int j = tblRegistros.getRowCount();
////        for (int i = 0; i < j; i++) {
////            modelo.removeRow(0);
////        }
//
//        txtDescripcion.setText("");
////        desactivarCita();
//        txtId.setEnabled(true);
//        btnBuscPaciente.setEnabled(true);
//        btnGuardar.setEnabled(true);
//        btnLimpiar.setEnabled(true);
//        tblRegistros.setEnabled(true);
//
////        cmbMedico.setEnabled(true);
//    }

    private void tblRegistrosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblRegistrosMouseClicked
//        try {
//            btnLimpiarActionPerformed(null);
        int fila = tblRegistros.getSelectedRow();
        String id = tblRegistros.getValueAt(fila, 0).toString();

        if (id.equalsIgnoreCase("")) {
            btnLimpiarActionPerformed(null);
//                txthora.setText(tblRegistros.getValueAt(tblRegistros.getSelectedRow(), 0).toString());
        } else if (evt.getClickCount() == 1) {
            cargarClienteAgenda(tblRegistros.getValueAt(tblRegistros.getSelectedRow(), 0).toString());
        }
//            else {
//                if (tblRegistros.getValueAt(tblRegistros.getSelectedRow(), 4).equals("Confirmada")) {
//                } else {
//                    if (metodos.msgPregunta(this, "¿Desar Confirmar la Cita?") == 0) {
//                        confirmarOrden();
//                    }
//                }
//            }
//        } catch (Exception e) {
//        }

    }//GEN-LAST:event_tblRegistrosMouseClicked

//    public void desactivarCita() {
//        dtFecha.setEnabled(false);
//        btnBuscPaciente.setEnabled(false);
//        btnGuardar.setEnabled(false);
//        lblMedicoNuevo.setVisible(true);
//        cmbTipoRecordatorio.setVisible(true);
//        txtDescripcion.setEnabled(true);
//        btnConfirmarCita.setEnabled(false);
//        cmbTipoRecordatorio.removeAllItems();
//
//    }
//
//    public void activarCita() {
//        dtFecha.setEnabled(true);
//        btnBuscPaciente.setEnabled(true);
//        btnGuardar.setEnabled(true);
//        lblMedicoNuevo.setVisible(false);
//        cmbTipoRecordatorio.setVisible(false);
//        txtDescripcion.setEnabled(true);
//    }

    private void dtFechaOnSelectionChange(datechooser.events.SelectionChangedEvent evt) {//GEN-FIRST:event_dtFechaOnSelectionChange


    }//GEN-LAST:event_dtFechaOnSelectionChange

    private void dtFechaOnCommit(datechooser.events.CommitEvent evt) {//GEN-FIRST:event_dtFechaOnCommit

    }//GEN-LAST:event_dtFechaOnCommit


    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        for (int x = 0; x < jPanel1.getComponentCount(); x++) {
            if (jPanel1.getComponent(x) instanceof JTextField) {
                JTextField textField = (JTextField) jPanel1.getComponent(x);
                textField.setText("");
            }
        }

//        activarCita();
        txtDescripcion.setText("");
        dtFecha.setSelectedDate(metodos.haciaDate2(metodosGenerales.fecha()));
        dtFecha.setEnabled(true);
        txtId.setEnabled(true);
        btnBuscPaciente.setEnabled(true);
        txtDescripcion.setEnabled(true);

        btnConfirmarCita.setEnabled(false);
        btnCancelar.setEnabled(false);
        btnGuardar.setText("GUARDAR");
        cargarTabla("PENDIENTE");
        cmbHora1.setSelectedIndex(0);
        cmbHora2.setSelectedIndex(0);
        cmbTipoRecordatorio.setSelectedIndex(0);
        txtCita.setText(instancias.getSql().getNumConsecutivo("RECORDATORIO")[0].toString());

    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void cmbTipoRecordatorioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbTipoRecordatorioItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbTipoRecordatorioItemStateChanged

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed

        if (tblRegistros.getValueAt(tblRegistros.getSelectedRow(), 8).equals("FINALIZADO") || tblRegistros.getValueAt(tblRegistros.getSelectedRow(), 8).equals("CANCELADO")) {
            metodos.msgError(this, "Esta cita ya se ecuentra finalizada");
        } else {
            instancias.getSql().cambiarEstadoRecordatorio("CANCELADO", txtCita.getText());
            btnLimpiarActionPerformed(null);
            cargarTabla("PENDIENTE");
            metodos.msgExito(this, "Recordatorio Cancelado");
//            metodos.alertaRecordatorios();
        }
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtId1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtId1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtId1KeyReleased

    private void txtCitaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCitaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCitaKeyReleased

    private void jPanel2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseEntered

    }//GEN-LAST:event_jPanel2MouseEntered

    private void jPanel2MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseMoved

    }//GEN-LAST:event_jPanel2MouseMoved

    private void jPanel2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPanel2FocusGained

    }//GEN-LAST:event_jPanel2FocusGained

    private void txtIdMotivoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdMotivoKeyReleased

    }//GEN-LAST:event_txtIdMotivoKeyReleased

    private void txtAsuntoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAsuntoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAsuntoKeyReleased

    private void btnConfirmarCitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarCitaActionPerformed

        if (tblRegistros.getValueAt(tblRegistros.getSelectedRow(), 8).equals("FINALIZADO") || tblRegistros.getValueAt(tblRegistros.getSelectedRow(), 8).equals("CANCELADO")) {
            metodos.msgError(this, "Esta cita ya se ecuentra finalizada");
        } else {
            instancias.getSql().cambiarEstadoRecordatorio("FINALIZADO", txtCita.getText());
            btnLimpiarActionPerformed(null);
            cargarTabla("PENDIENTE");
            metodos.msgExito(this, "Recordatorio finalizado");
        }


    }//GEN-LAST:event_btnConfirmarCitaActionPerformed

    private void cmbEstadoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbEstadoItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbEstadoItemStateChanged

    private void cmbEstado1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbEstado1ItemStateChanged
        cargarTabla(cmbEstado1.getSelectedItem().toString());
    }//GEN-LAST:event_cmbEstado1ItemStateChanged

    private void cmbTipoRecordatorio1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbTipoRecordatorio1ItemStateChanged
        if (cmbTipoRecordatorio1.getSelectedIndex() > 0) {
            modeloOrdenado.setRowFilter(RowFilter.regexFilter("(?i)" + cmbTipoRecordatorio1.getSelectedItem().toString(), 3));
        }

    }//GEN-LAST:event_cmbTipoRecordatorio1ItemStateChanged

    private void cmbTipoRecordatorio1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTipoRecordatorio1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbTipoRecordatorio1ActionPerformed

    private void txtFiltroNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFiltroNombreKeyReleased
        modeloOrdenado.setRowFilter(RowFilter.regexFilter("(?i)" + txtFiltroNombre.getText(), 5));
    }//GEN-LAST:event_txtFiltroNombreKeyReleased

    public void setInstancias(Instancias instancias) {
        this.instancias = instancias;
        dtFecha.setSelectedDate(Calendar.getInstance());
        txtCita.setText(instancias.getSql().getNumConsecutivo("RECORDATORIO")[0].toString());
        cargarTabla("PENDIENTE");
        tblRegistros.setAutoCreateRowSorter(true);

        modeloOrdenado = new TableRowSorter<>(modelo);
        tblRegistros.setRowSorter(modeloOrdenado);
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

    public void ventanaEmpleados(String nit) {
        buscEmpleados buscar = new buscEmpleados(instancias.getMenu(), rootPaneCheckingEnabled);
        buscar.setLocationRelativeTo(null);
        instancias.setBuscEmpleados(buscar);
        instancias.setCampoActual(txtId);
        txtId.requestFocus();
        buscar.noEncontrado(nit);
        buscar.show();
    }
    
    public void ventanaProveedor(String nit) {
        buscClientes buscar = new buscClientes(instancias.getMenu(), false, true, null, "");
        buscar.setLocationRelativeTo(null);
        buscar.setTipoBuscador("");
        instancias.setBusClientes(buscar);
        instancias.setCampoActual(txtId);
        txtId.requestFocus();
        buscar.noEncontrado(nit);
        buscar.show();
    }

    public void cargarNuevoPaciente(String id, String nombre, String telefono) {
        txtId.setText(id);
        txtNombre.setText(nombre);
        txtTelefono.setText(telefono);
    }

    public void cargarCliente(String nit) {

        ModeloContacto nodo = instancias.getSql().getDatosTercero(nit);

        if (nodo.getId() != null) {
            txtNombre.setText(nodo.getCompleta());
            txtId.setText(nodo.getId());
//            lbltipodoc.setText(nodo.getTipo() + ":");
            txtTelefono.setText(nodo.getTelefono());
            txtCelular.setText(nodo.getCelular());
            txtDireccion.setText(nodo.getDireccion());
            lbTipoTercero.setText("cliente");
            return;
        }
    }

    public void setDatosEmpresa(String info) {
        this.info = info;
    }

    public void cargarClienteAgenda(String nit) {

        btnConfirmarCita.setEnabled(true);
        btnCancelar.setEnabled(true);
        btnGuardar.setEnabled(true);

        ndRecordatorio nodoAgenda = instancias.getSql().getDatosRecordatorio(nit);

        if (nodoAgenda != null) {
            txtCita.setText(nodoAgenda.getId());
            txtDescripcion.setText(nodoAgenda.getDescripcion());
            txtAsunto.setText(nodoAgenda.getAsunto());
            lbTipoTercero.setText(nodoAgenda.getTipoTercero());
            cmbTipoRecordatorio.setSelectedItem(nodoAgenda.getTipo());
            cmbEstado.setSelectedItem(nodoAgenda.getEstado());
            dtFecha.setSelectedDate(metodos.haciaDate2(metodos.fecha(nodoAgenda.getFecha())));
            if (nodoAgenda.getAlerta().equals("hora")) {
                rdHora.setSelected(true);
            } else {
                rdDia.setSelected(true);
            }
            String[] split = nodoAgenda.getHora().split(":");

            cmbHora1.setSelectedItem(split[0]);
            cmbHora2.setSelectedItem(split[1]);

            ModeloContacto tercero = instancias.getSql().getDatosTercero(nodoAgenda.getTercero());

            if (tercero.getId() != null) {
                txtNombre.setText(tercero.getNombre());
                txtId.setText(nodoAgenda.getTercero());
                txtTelefono.setText(tercero.getTelefono());
                txtCelular.setText(tercero.getCelular());
                txtDireccion.setText(tercero.getDireccion());
            } else {
                txtNombre.setText("");
                txtId.setText("");
                txtTelefono.setText("");
                txtCelular.setText("");
                txtDireccion.setText("");

            }
            btnGuardar.setText("MODIFICAR");
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscPaciente;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnConfirmarCita;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JComboBox cmbEstado;
    private javax.swing.JComboBox cmbEstado1;
    private javax.swing.JComboBox cmbHora1;
    private javax.swing.JComboBox cmbHora2;
    private javax.swing.JComboBox cmbTipoRecordatorio;
    private javax.swing.JComboBox cmbTipoRecordatorio1;
    private datechooser.beans.DateChooserCombo dtFecha;
    private javax.swing.ButtonGroup grupoAlerta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lbNit10;
    private javax.swing.JLabel lbTipoTercero;
    private javax.swing.JLabel lblMedicoNuevo;
    private javax.swing.JLabel lblMedicoNuevo1;
    private javax.swing.JLabel lblMedicoNuevo2;
    private javax.swing.JLabel lblMedicoNuevo3;
    private javax.swing.JLabel lblMedicoNuevo4;
    private javax.swing.JLabel lblMedicoNuevo5;
    private javax.swing.JLabel lblMedicoNuevo6;
    private javax.swing.JLabel lbltipodoc;
    private javax.swing.JLabel lbltipodoc1;
    private javax.swing.JLabel lbltipodoc2;
    private javax.swing.JLabel lbltipodoc3;
    private javax.swing.JPanel pnlFormulario;
    private javax.swing.JRadioButton rdDia;
    private javax.swing.JRadioButton rdHora;
    private javax.swing.JScrollPane scrFormulario;
    private javax.swing.JTable tblRegistros;
    private javax.swing.JTextField txtAsunto;
    private javax.swing.JTextField txtCelular;
    private javax.swing.JTextField txtCita;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtFiltroNombre;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtId1;
    private javax.swing.JTextField txtIdMotivo;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
