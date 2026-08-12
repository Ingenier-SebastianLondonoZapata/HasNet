package formularios.Medico;

import clases.Instancias;
import clases.Medico.ndConvenio;
import clases.Medico.ndEpsPrecargados;
import clases.Medico.ndOrdenServicio;
import Utilidades.BaseDatos.SQL;
import clases.Ventas.ndFactura;
import clases.big;
import clases.metodosGenerales;
import clases.productos.ndProducto;
import Modelo.Terceros.ModeloContacto;
import Vista.Tesoreria.VistaEgresos;
import Vista.Productos.VistaBuscadorProductos;
import formularios.terceros.buscClientes;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;

public class infOrdenServicioMedico extends javax.swing.JInternalFrame {

    metodosGenerales metodos = new metodosGenerales();
    private Instancias instancias;

    private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension dimBarra = null;
    private String valor, convenio = "", simbolo = "";
    DefaultTableModel modelo;

    public infOrdenServicioMedico() {

        initComponents();

        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
        dimBarra = Barra.getPreferredSize();
        Barra.setSize(0, 0);
        Barra.setPreferredSize(new Dimension(0, 0));
        setBorder(null);
        repaint();

        instancias = Instancias.getInstancias();

        this.simbolo = instancias.getSimbolo();
        txtValor.setText(this.simbolo + " 0");
        txtCopago.setText(this.simbolo + " 0");
        txtDescuento.setText(this.simbolo + " 0");
        txtComision.setText(this.simbolo + " 0");
        txtCuota.setText(this.simbolo + " 0");

        btnLimpiarActionPerformed(null);
        cmbMedico.removeAllItems();
        Object[][] aux = instancias.getSql().getEmpleadosCargo("Medico");
        cmbMedico.addItem("");
        for (int i = 0; i < aux.length; i++) {
            cmbMedico.addItem(aux[i][1].toString());
        }

        txtCopago.setEnabled(false);
        txtCuota.setEnabled(false);
        txtAutorizacion.setEnabled(false);
        txtPoliza.setEnabled(false);
        pnlInvisible.setVisible(false);

        pnlFormulario.registerKeyboardAction(accion("guardar"), "guardar", KeyStroke.getKeyStroke(KeyEvent.VK_G, Event.CTRL_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
        pnlFormulario.registerKeyboardAction(accion("limpiar"), "limpiar", KeyStroke.getKeyStroke(KeyEvent.VK_L, Event.CTRL_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
    }

    private ActionListener accion(final String opc) {
        ActionListener a = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (opc) {
                    case "limpiar":
                        if ((btnLimpiar.isEnabled()) && (btnLimpiar.isVisible())) {
                            btnLimpiarActionPerformed(null);
                        }
                        break;
                    case "guardar":
                        if ((btnGuardar1.isEnabled()) && (btnGuardar1.isVisible())) {
                            btnGuardar1ActionPerformed(null);
                        }
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
        scrFormulario = new javax.swing.JScrollPane();
        pnlFormulario = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        lbRazon29 = new javax.swing.JLabel();
        cmbMedico = new javax.swing.JComboBox();
        lbRazon4 = new javax.swing.JLabel();
        lbRazon5 = new javax.swing.JLabel();
        cmbConcepto = new javax.swing.JComboBox();
        cmbFinalidad = new javax.swing.JComboBox();
        lbRazon14 = new javax.swing.JLabel();
        lbRazon13 = new javax.swing.JLabel();
        cmbPersonal = new javax.swing.JComboBox();
        cmbAmbito = new javax.swing.JComboBox();
        txtAutorizacion = new javax.swing.JTextField();
        lbRazon6 = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        lbRazon12 = new javax.swing.JLabel();
        txtCopago = new javax.swing.JTextField();
        lbRazon8 = new javax.swing.JLabel();
        txtValor = new javax.swing.JTextField();
        lbRazon19 = new javax.swing.JLabel();
        txtaCobrar = new javax.swing.JTextField();
        lbRazon11 = new javax.swing.JLabel();
        cmbConvenio = new javax.swing.JComboBox();
        lbRazon20 = new javax.swing.JLabel();
        txtEps = new javax.swing.JTextField();
        lbRazon21 = new javax.swing.JLabel();
        txtPaciente = new javax.swing.JTextField();
        lbPaciente = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        lbRazon1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lbRazon = new javax.swing.JLabel();
        lbRazon25 = new javax.swing.JLabel();
        lbRazon24 = new javax.swing.JLabel();
        lbRazon7 = new javax.swing.JLabel();
        cmbCausa = new javax.swing.JComboBox();
        cmbActoQuirurgico = new javax.swing.JComboBox();
        txtCodigoIps = new javax.swing.JTextField();
        txtCuota = new javax.swing.JTextField();
        txtaCobrar1 = new javax.swing.JTextField();
        lbRazon10 = new javax.swing.JLabel();
        txtDescuento = new javax.swing.JTextField();
        lbNit = new javax.swing.JLabel();
        txtComision = new javax.swing.JTextField();
        lbRazon26 = new javax.swing.JLabel();
        txtCantidad1 = new javax.swing.JTextField();
        lbRazon3 = new javax.swing.JLabel();
        txtPoliza = new javax.swing.JTextField();
        lbRazon22 = new javax.swing.JLabel();
        lbRazon18 = new javax.swing.JLabel();
        lbRazon30 = new javax.swing.JLabel();
        lbRazon27 = new javax.swing.JLabel();
        txtConsecutivo = new javax.swing.JTextField();
        txtEps1 = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        dtFecha = new datechooser.beans.DateChooserCombo();
        rdConsulta = new javax.swing.JRadioButton();
        rdProcedimiento = new javax.swing.JRadioButton();
        lbRazon23 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lbRazon15 = new javax.swing.JLabel();
        lbRazon28 = new javax.swing.JLabel();
        lbRazon16 = new javax.swing.JLabel();
        lbRazon17 = new javax.swing.JLabel();
        txtDiagnostico = new javax.swing.JTextField();
        txtProcedimiento = new javax.swing.JTextField();
        txtProcedimiento1 = new javax.swing.JTextField();
        txtDiagnostico1 = new javax.swing.JTextField();
        cmbCodigoConsulta = new javax.swing.JComboBox();
        cmbForma = new javax.swing.JComboBox();
        jPanel4 = new javax.swing.JPanel();
        btnGuardar1 = new javax.swing.JButton();
        btnActualizar1 = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnBuscTerceros = new javax.swing.JButton();
        btnAnular = new javax.swing.JButton();
        pnlInvisible = new javax.swing.JPanel();
        txtIdProd = new javax.swing.JTextField();
        txtIdTercero = new javax.swing.JTextField();

        setTitle("Ayuda Diagnostica");

        pnlFormulario.setBackground(new java.awt.Color(255, 255, 255));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        lbRazon29.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbRazon29.setText("Medico Atiende:");

        cmbMedico.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        cmbMedico.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " " }));
        cmbMedico.setName("Forma"); // NOI18N
        cmbMedico.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbMedicoKeyReleased(evt);
            }
        });

        lbRazon4.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbRazon4.setText("Concepto:");

        lbRazon5.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbRazon5.setText("Finalidad:");

        cmbConcepto.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        cmbConcepto.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Consultas", "Procedimientos de diagnósticos", "Procedimientos terapéuticos no quirúrgicos", "Procedimientos terapéuticos quirúrgicos", "Procedimientos de promoción y prevención", "Estancias", "Honorarios", "Derechos de sala", "Materiales e insumos", "Banco de sangre", "Prótesis y órtesis", "Medicamentos POS", "Medicamentos no POS", "Traslado de pacientes" }));
        cmbConcepto.setName("Concepto"); // NOI18N
        cmbConcepto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbConceptoKeyReleased(evt);
            }
        });

        cmbFinalidad.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        cmbFinalidad.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "No aplica", "Atención del parto (puerperio)", "Atención del recién nacido", "Atención en planificación familiar", "Detección de alteraciones de crecimiento y desarrollo del menor de diez años", "Detección de alteración del desarrollo joven", "Detección de alteraciones del embarazo", "Detección de alteraciones del adulto", "Detección de alteraciones de agudeza visual", "Detección de enfermedad profesional", "Diagnóstico", "Terapéutico", "Protección específica", "Detección temprana de enfermedad general", "Detección temprana de enfermedad profesional" }));
        cmbFinalidad.setName("Finalidad"); // NOI18N
        cmbFinalidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbFinalidadKeyReleased(evt);
            }
        });

        lbRazon14.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbRazon14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbRazon14.setText("Personal que atiende:");

        lbRazon13.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbRazon13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbRazon13.setText("Ambito Procedimiento:");

        cmbPersonal.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        cmbPersonal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Médico (a) general", "Médico (A) especialista", "Enfermera (o)", "Auxiliar de enfermería", "Otro" }));
        cmbPersonal.setName("Personal"); // NOI18N
        cmbPersonal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbPersonalKeyReleased(evt);
            }
        });

        cmbAmbito.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        cmbAmbito.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ambulatorio", "Hospitalario", "De urgencia" }));
        cmbAmbito.setName("Ambito Procedimiento"); // NOI18N
        cmbAmbito.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbAmbitoKeyReleased(evt);
            }
        });

        txtAutorizacion.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtAutorizacion.setName("Numero De Autorizacion"); // NOI18N
        txtAutorizacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAutorizacionKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAutorizacionKeyTyped(evt);
            }
        });

        lbRazon6.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbRazon6.setText("No. Autorización:");

        txtCantidad.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtCantidad.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCantidad.setText("1");
        txtCantidad.setName("Cantidad"); // NOI18N
        txtCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCantidadKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadKeyTyped(evt);
            }
        });

        lbRazon12.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbRazon12.setText("Cant.Autorizada:");

        txtCopago.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtCopago.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCopago.setText("0");
        txtCopago.setName("Copago"); // NOI18N
        txtCopago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCopagoActionPerformed(evt);
            }
        });
        txtCopago.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCopagoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCopagoKeyTyped(evt);
            }
        });

        lbRazon8.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbRazon8.setText("Copago:");

        txtValor.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtValor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtValor.setText("0");
        txtValor.setName("Cantidad"); // NOI18N
        txtValor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtValorActionPerformed(evt);
            }
        });
        txtValor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtValorKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtValorKeyTyped(evt);
            }
        });

        lbRazon19.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbRazon19.setText("Valor:");

        txtaCobrar.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtaCobrar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtaCobrar.setName("Cobro"); // NOI18N
        txtaCobrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtaCobrarActionPerformed(evt);
            }
        });
        txtaCobrar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtaCobrarKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtaCobrarKeyTyped(evt);
            }
        });

        lbRazon11.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbRazon11.setText("Desc. Servicio:");

        cmbConvenio.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cmbConvenio.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "NO", "SI" }));
        cmbConvenio.setName("Causa Externa"); // NOI18N
        cmbConvenio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbConvenioItemStateChanged(evt);
            }
        });
        cmbConvenio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbConvenioKeyReleased(evt);
            }
        });

        lbRazon20.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbRazon20.setText("Convenio:");

        txtEps.setEditable(false);
        txtEps.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtEps.setToolTipText(" ");
        txtEps.setName("Eps"); // NOI18N
        txtEps.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEpsKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEpsKeyTyped(evt);
            }
        });

        lbRazon21.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbRazon21.setText("EPS:");

        txtPaciente.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtPaciente.setName("Codigo"); // NOI18N
        txtPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPacienteActionPerformed(evt);
            }
        });
        txtPaciente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPacienteKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPacienteKeyTyped(evt);
            }
        });

        lbPaciente.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbPaciente.setText("Paciente:");
        lbPaciente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lbPacienteKeyReleased(evt);
            }
        });

        txtId.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtId.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtId.setName("Codigo"); // NOI18N
        txtId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIdKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIdKeyTyped(evt);
            }
        });

        lbRazon1.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbRazon1.setText("No. Orden:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lbRazon4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbRazon29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbRazon6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbRazon12, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                                    .addComponent(lbRazon8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbRazon19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbRazon11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbRazon5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(4, 4, 4))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lbRazon20, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbRazon21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cmbConcepto, 0, 0, Short.MAX_VALUE)
                                    .addComponent(cmbMedico, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtAutorizacion)
                                    .addComponent(cmbFinalidad, 0, 0, Short.MAX_VALUE)
                                    .addComponent(txtEps)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtaCobrar, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                                            .addComponent(txtValor)
                                            .addComponent(txtCopago)
                                            .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addGap(10, 10, 10))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(cmbConvenio, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lbPaciente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbRazon1, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtId)
                                    .addComponent(txtPaciente)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lbRazon14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbRazon13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cmbPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbAmbito, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbRazon1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtEps, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbRazon21, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbConvenio, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbRazon20, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtaCobrar, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbRazon11, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbRazon19, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCopago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbRazon8, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbRazon12, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbRazon14, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbRazon13, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbAmbito, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtAutorizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbRazon6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbRazon29, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)
                        .addComponent(lbRazon4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cmbConcepto, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbRazon5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbFinalidad, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txtCopago.getAccessibleContext().setAccessibleName("Copago");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        lbRazon.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbRazon.setText("Causa Externa:");

        lbRazon25.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbRazon25.setText("Acto quirurgico:");

        lbRazon24.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbRazon24.setText("Codigo Ips:");

        lbRazon7.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbRazon7.setText("Cuota Moderadora:");

        cmbCausa.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        cmbCausa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Enfermedad general", "Accidente de trabajo", "Accidente de tránsito", "Accidente rábico", "Accidente ofídico", "Otro tipo de accidente", "Evento catastrófico", "Lesión por agresión", "Lesión auto infligida", "Sospecha de maltrato físico", "Sospecha de abuso sexual", "Sospecha de violencia sexual", "Sospecha de maltrato emocional", "Enfermedad profesional", "Otra" }));
        cmbCausa.setName("Causa Externa"); // NOI18N
        cmbCausa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbCausaKeyReleased(evt);
            }
        });

        cmbActoQuirurgico.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        cmbActoQuirurgico.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Unico o unilateral", "Múltiple o bilateral, misma vía, diferente especialidad", "Múltiple o bilateral, misma vía, igual especialidad", "Múltiple o bilateral, diferente vía, diferente especialidad", "Múltiple o bilateral, diferente vía, igual especialidad" }));

        txtCodigoIps.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtCodigoIps.setName("Comision"); // NOI18N
        txtCodigoIps.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoIpsKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoIpsKeyTyped(evt);
            }
        });

        txtCuota.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtCuota.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCuota.setText("0");
        txtCuota.setName("Cuota Moderadora"); // NOI18N
        txtCuota.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCuotaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCuotaKeyTyped(evt);
            }
        });

        txtaCobrar1.setEditable(false);
        txtaCobrar1.setName("Cobro"); // NOI18N
        txtaCobrar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtaCobrar1ActionPerformed(evt);
            }
        });
        txtaCobrar1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtaCobrar1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtaCobrar1KeyTyped(evt);
            }
        });

        lbRazon10.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbRazon10.setText("Descuento:");

        txtDescuento.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtDescuento.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDescuento.setText("0");
        txtDescuento.setName("Descuento"); // NOI18N
        txtDescuento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDescuentoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDescuentoKeyTyped(evt);
            }
        });

        lbNit.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbNit.setText("Comision:");

        txtComision.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtComision.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtComision.setText("0");
        txtComision.setName("Comision"); // NOI18N
        txtComision.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtComisionKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtComisionKeyTyped(evt);
            }
        });

        lbRazon26.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbRazon26.setText("Cant.Realizada:");

        txtCantidad1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtCantidad1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCantidad1.setText("1");
        txtCantidad1.setName("Cantidad"); // NOI18N
        txtCantidad1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantidad1ActionPerformed(evt);
            }
        });
        txtCantidad1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCantidad1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidad1KeyTyped(evt);
            }
        });

        lbRazon3.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbRazon3.setText("No. Poliza:");

        txtPoliza.setName("Numero De Pöliza"); // NOI18N
        txtPoliza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPolizaActionPerformed(evt);
            }
        });
        txtPoliza.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPolizaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPolizaKeyTyped(evt);
            }
        });

        lbRazon22.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbRazon22.setText("Fecha:");

        lbRazon18.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbRazon18.setText("Nombre:");

        lbRazon30.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbRazon30.setText("Nombre Eps:");

        lbRazon27.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbRazon27.setText("Conse. Eps:");

        txtConsecutivo.setEditable(false);
        txtConsecutivo.setName("Orden"); // NOI18N
        txtConsecutivo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtConsecutivoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtConsecutivoKeyTyped(evt);
            }
        });

        txtEps1.setEditable(false);
        txtEps1.setToolTipText(" ");
        txtEps1.setName("EPS"); // NOI18N
        txtEps1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEps1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEps1KeyTyped(evt);
            }
        });

        txtNombre.setEditable(false);
        txtNombre.setName("Paciente"); // NOI18N
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });

        dtFecha.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 12));

        rdConsulta.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdConsulta);
        rdConsulta.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        rdConsulta.setSelected(true);
        rdConsulta.setText("Consulta");
        rdConsulta.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdConsultaItemStateChanged(evt);
            }
        });

        rdProcedimiento.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdProcedimiento);
        rdProcedimiento.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        rdProcedimiento.setText("Procedimiento");
        rdProcedimiento.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rdProcedimientoStateChanged(evt);
            }
        });
        rdProcedimiento.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdProcedimientoItemStateChanged(evt);
            }
        });

        lbRazon23.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbRazon23.setText("Tipo:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtaCobrar1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(lbRazon26, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbNit, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbRazon10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbRazon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbRazon25, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbRazon7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbRazon24, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(lbRazon3, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbCausa, 0, 0, Short.MAX_VALUE)
                            .addComponent(cmbActoQuirurgico, 0, 0, Short.MAX_VALUE)
                            .addComponent(txtCodigoIps)
                            .addComponent(txtCuota)
                            .addComponent(txtDescuento)
                            .addComponent(txtComision)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtPoliza, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCantidad1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lbRazon23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbRazon18, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbRazon30, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                            .addComponent(lbRazon27, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbRazon22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(rdProcedimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rdConsulta, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE))
                            .addComponent(dtFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtConsecutivo)
                            .addComponent(txtEps1)
                            .addComponent(txtNombre))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rdProcedimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rdConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbRazon23, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbRazon22, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbRazon18, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lbRazon30, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lbRazon27, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(15, 15, 15)
                                .addComponent(txtaCobrar1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbRazon10, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(3, 3, 3)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbNit, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtComision, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(3, 3, 3)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbRazon26, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCantidad1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(txtConsecutivo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(txtEps1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbRazon7, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCuota, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbRazon3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPoliza, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbRazon, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbCausa, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbRazon25, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbActoQuirurgico, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbRazon24, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCodigoIps, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        lbRazon15.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbRazon15.setText("Forma:");

        lbRazon28.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbRazon28.setText("Codigo Consulta:");

        lbRazon16.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbRazon16.setText("Diagnostico:");

        lbRazon17.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbRazon17.setText("Procedimiento:");

        txtDiagnostico.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDiagnostico.setName("Cobro"); // NOI18N
        txtDiagnostico.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDiagnosticoFocusGained(evt);
            }
        });
        txtDiagnostico.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDiagnosticoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDiagnosticoKeyTyped(evt);
            }
        });

        txtProcedimiento.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtProcedimiento.setName("Cobro"); // NOI18N
        txtProcedimiento.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtProcedimientoFocusGained(evt);
            }
        });
        txtProcedimiento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtProcedimientoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtProcedimientoKeyTyped(evt);
            }
        });

        txtProcedimiento1.setEditable(false);
        txtProcedimiento1.setName("Cobro"); // NOI18N
        txtProcedimiento1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtProcedimiento1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtProcedimiento1KeyTyped(evt);
            }
        });

        txtDiagnostico1.setEditable(false);
        txtDiagnostico1.setName("Cobro"); // NOI18N
        txtDiagnostico1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDiagnostico1ActionPerformed(evt);
            }
        });
        txtDiagnostico1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDiagnostico1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDiagnostico1KeyTyped(evt);
            }
        });

        cmbCodigoConsulta.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        cmbCodigoConsulta.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Consulta de Medicina General electiva", "Consulta de Medicina Especializada electiva", "Consulta de Medicina General de control", "Consulta de Medicina Especializada de control" }));
        cmbCodigoConsulta.setName("Forma"); // NOI18N
        cmbCodigoConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCodigoConsultaActionPerformed(evt);
            }
        });
        cmbCodigoConsulta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbCodigoConsultaKeyReleased(evt);
            }
        });

        cmbForma.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        cmbForma.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Consulta externa o programada", "Urgencias", "Remitido", "Nacido en la institución" }));
        cmbForma.setName("Forma"); // NOI18N
        cmbForma.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbFormaKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lbRazon17, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbRazon28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbRazon16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbRazon15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtDiagnostico, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                            .addComponent(txtProcedimiento))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtProcedimiento1)
                            .addComponent(txtDiagnostico1)))
                    .addComponent(cmbCodigoConsulta, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbForma, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cmbForma, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbRazon15, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbRazon28, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(cmbCodigoConsulta))
                .addGap(5, 5, 5)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbRazon16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtDiagnostico, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtDiagnostico1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(3, 3, 3)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbRazon17, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtProcedimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtProcedimiento1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 482, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(2, 2, 2)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        btnGuardar1.setBackground(new java.awt.Color(46, 204, 113));
        btnGuardar1.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnGuardar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
        btnGuardar1.setText("GUARDAR");
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

        btnActualizar1.setBackground(new java.awt.Color(93, 173, 226));
        btnActualizar1.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnActualizar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/actualizar.png"))); // NOI18N
        btnActualizar1.setText("MODIFICAR");
        btnActualizar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnActualizar1.setEnabled(false);
        btnActualizar1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnActualizar1.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnActualizar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizar1ActionPerformed(evt);
            }
        });

        btnLimpiar.setBackground(new java.awt.Color(204, 204, 204));
        btnLimpiar.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/limpiar.png"))); // NOI18N
        btnLimpiar.setText("LIMPIAR");
        btnLimpiar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLimpiar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnLimpiar.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        btnBuscTerceros.setBackground(new java.awt.Color(247, 220, 111));
        btnBuscTerceros.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnBuscTerceros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/imprimir.png"))); // NOI18N
        btnBuscTerceros.setText("IMPRIMIR");
        btnBuscTerceros.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscTerceros.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnBuscTerceros.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnBuscTerceros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscTercerosActionPerformed(evt);
            }
        });

        btnAnular.setBackground(new java.awt.Color(241, 148, 138));
        btnAnular.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnAnular.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/borrar2.png"))); // NOI18N
        btnAnular.setText("ANULAR");
        btnAnular.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAnular.setEnabled(false);
        btnAnular.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnAnular.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnAnular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnularActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnGuardar1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAnular, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(btnActualizar1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBuscTerceros, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnActualizar1)
                    .addComponent(btnLimpiar)
                    .addComponent(btnGuardar1)
                    .addComponent(btnAnular)
                    .addComponent(btnBuscTerceros, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txtIdProd.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtIdProd.setName("Cobro"); // NOI18N
        txtIdProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdProdActionPerformed(evt);
            }
        });
        txtIdProd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIdProdKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIdProdKeyTyped(evt);
            }
        });

        txtIdTercero.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtIdTercero.setName("Cobro"); // NOI18N
        txtIdTercero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdTerceroActionPerformed(evt);
            }
        });
        txtIdTercero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIdTerceroKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIdTerceroKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout pnlInvisibleLayout = new javax.swing.GroupLayout(pnlInvisible);
        pnlInvisible.setLayout(pnlInvisibleLayout);
        pnlInvisibleLayout.setHorizontalGroup(
            pnlInvisibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInvisibleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtIdProd)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtIdTercero)
                .addGap(418, 418, 418))
        );
        pnlInvisibleLayout.setVerticalGroup(
            pnlInvisibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInvisibleLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlInvisibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIdProd, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdTercero, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                        .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(pnlInvisible, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlFormularioLayout.setVerticalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlInvisible, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
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

    private void txtCopagoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCopagoKeyReleased
        if (txtCopago.getText().equals("") || txtCopago.getText().equals(this.simbolo) || txtCopago.getText().equals(this.simbolo + " ")) {
            txtCopago.setText(this.simbolo + " 0");
        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtAutorizacion.requestFocus();
        } else {
            txtCopago.setText(big.setMoneda(big.getMoneda(txtCopago.getText())));
        }
    }//GEN-LAST:event_txtCopagoKeyReleased

    private void txtAutorizacionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAutorizacionKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAutorizacionKeyTyped

    private void txtAutorizacionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAutorizacionKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbMedico.requestFocus();
        }
    }//GEN-LAST:event_txtAutorizacionKeyReleased

    private void txtCuotaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCuotaKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtCuotaKeyTyped

    private void txtCuotaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCuotaKeyReleased
        if (txtCuota.getText().equals("") || txtCuota.getText().equals(this.simbolo) || txtCuota.getText().equals(this.simbolo + " ")) {
            txtCuota.setText("0");
        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbFinalidad.requestFocus();
        } else {
            txtCuota.setText(big.setMoneda(big.getMoneda(txtCuota.getText())));
        }
    }//GEN-LAST:event_txtCuotaKeyReleased

    private void txtCantidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!txtValor.getText().equals("")) {
                txtValor.setText(big.setMoneda(big.getMoneda(valor).multiply(big.getBigDecimal(txtCantidad.getText()))));
            }
            txtDescuento.requestFocus();
        }
    }//GEN-LAST:event_txtCantidadKeyReleased

    private void txtIdKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdKeyTyped

    }//GEN-LAST:event_txtIdKeyTyped

    private void txtIdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cargarOrdenServicio(txtId.getText());
        }
    }//GEN-LAST:event_txtIdKeyReleased

    private void txtComisionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtComisionKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtComisionKeyTyped

    private void txtComisionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtComisionKeyReleased
        if (txtComision.getText().equals("") || txtComision.getText().equals(this.simbolo) || txtComision.getText().equals(this.simbolo + " ")) {
            txtComision.setText("0");
        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtCantidad.requestFocus();
        } else {
            txtComision.setText(big.setMoneda(big.getMoneda(txtComision.getText())));
        }
    }//GEN-LAST:event_txtComisionKeyReleased

    private void txtPolizaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPolizaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPolizaKeyTyped

    private void txtPolizaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPolizaKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbConcepto.requestFocus();
        }
    }//GEN-LAST:event_txtPolizaKeyReleased

    private void txtPolizaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPolizaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPolizaActionPerformed

    private void txtDescuentoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescuentoKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtDescuentoKeyTyped

    private void txtDescuentoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescuentoKeyReleased
        if (txtDescuento.getText().equals("") || txtDescuento.getText().equals(this.simbolo) || txtDescuento.getText().equals(this.simbolo + " ")) {
            txtDescuento.setText("0");
        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtaCobrar.requestFocus();
        } else {
            txtDescuento.setText(big.setMoneda(big.getMoneda(txtDescuento.getText())));
        }
    }//GEN-LAST:event_txtDescuentoKeyReleased

    private void txtCopagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCopagoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCopagoActionPerformed

    private void cmbConceptoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbConceptoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtAutorizacion.requestFocus();
        }
    }//GEN-LAST:event_cmbConceptoKeyReleased

    private void cmbAmbitoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbAmbitoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbPersonal.requestFocus();
        }
    }//GEN-LAST:event_cmbAmbitoKeyReleased

    private void cmbFinalidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbFinalidadKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbCausa.requestFocus();
        }
    }//GEN-LAST:event_cmbFinalidadKeyReleased

    private void cmbCausaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbCausaKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtCopago.requestFocus();
        }
    }//GEN-LAST:event_cmbCausaKeyReleased

    private void cmbPersonalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbPersonalKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbForma.requestFocus();
        }
    }//GEN-LAST:event_cmbPersonalKeyReleased

    private void txtaCobrarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtaCobrarKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txtConsecutivo.getText().equals("")) {
                metodos.msgError(this, "Ingrese primero un paciente");
                txtPaciente.requestFocus();
                return;
            }
            cargarProducto(txtaCobrar.getText(), 1);
        } else {
            txtaCobrar1.setText("");
        }
    }//GEN-LAST:event_txtaCobrarKeyReleased

    private void txtaCobrarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtaCobrarKeyTyped

    }//GEN-LAST:event_txtaCobrarKeyTyped

    private void cmbFormaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbFormaKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtDiagnostico.requestFocus();
        }
    }//GEN-LAST:event_cmbFormaKeyReleased

    private String actoQuirurgicoDesc(String dato) {
        String aux = "";
        switch (dato) {
            case "Unico o unilateral":
                return "1";
            case "Múltiple o bilateral, misma vía, diferente especialidad":
                return "2";
            case "Múltiple o bilateral, misma vía, igual especialidad":
                return "3";
            case "Múltiple o bilateral, diferente vía, diferente especialidad":
                return "4";
            case "Múltiple o bilateral, diferente vía, igual especialidad":
                return "5";
        }
        return aux;
    }

    private String actoQuirurgicoCod(String dato) {
        String aux = "";
        switch (dato) {
            case "1":
                return "Unico o unilateral ";
            case "2":
                return "Múltiple o bilateral, misma vía, diferente especialidad";
            case "3":
                return "Múltiple o bilateral, misma vía, igual especialidad";
            case "4":
                return "Múltiple o bilateral, diferente vía, diferente especialidad";
            case "5":
                return "Múltiple o bilateral, diferente vía, igual especialidad";
        }
        return aux;
    }
    private void btnGuardar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar1ActionPerformed
        Object[] campos = {txtId, txtaCobrar, txtNombre, txtEps1};
        String faltantes = metodos.camposVacios(campos);
        boolean continuar = false;

        if (!txtAutorizacion.getText().equals("")) {
            ndOrdenServicio nodo2 = instancias.getSql().getDatosOrdenServicioAutorizacion(txtAutorizacion.getText());
            if (nodo2.getId() != null) {
                metodos.msgAdvertencia(this, "El Número de autorización ya existe en la orden " + nodo2.getId());
                if (metodos.msgPregunta(this, "¿Desea continuar?") != 0) {
                    txtAutorizacion.requestFocus();
                    return;
                }
                continuar = true;
            }
        }

        if (!faltantes.equals("")) {
            metodos.msgAdvertencia(this, "No puede continuar, faltan los siguientes campos: " + faltantes);
            return;
        }

        if (cmbConvenio.getSelectedItem().equals("NO")) {
            convenio = "";
        } else {
            Object[] campos2 = {txtPoliza, txtAutorizacion, txtCopago};
            String faltantes2 = metodos.camposVacios(campos2);
            if (!faltantes2.equals("")) {
                metodos.msgAdvertencia(this, "Faltan los siguientes campos: " + faltantes2);

                if (metodos.msgPregunta(this, "¿Desea continuar?") != 0) {
                    return;
                }
            } else {
                if (!continuar) {
                    if (metodos.msgPregunta(this, "¿Desea continuar?") != 0) {
                        return;
                    }
                }

            }
        }

//        try {
        String consecutivo = "";
//        String valor = "0";
//        try {
//            ndProducto prod = instancias.getSql().getDatosProducto(txtaCobrar.getText());
//            System.out.println(valor);
//            valor = prod.getL1();
//        } catch (Exception e) {
//            valor = "0";
//        }

        ndOrdenServicio nodo = instancias.getSql().getDatosOrdenServicio(txtId.getText());

        if (nodo.getId() != null) {
            metodos.msgError(this, "La orden ya existe");
            txtId.requestFocus();

            if (!instancias.getSql().aumentarConsecutivo("ORDEN", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("ORDEN")[0]) + 1)) {
                metodos.msgError(this, "Hubo un problema al guardar en el consecutivo de la orden");
            }

            return;
        }

        if (txtCopago.getText().equals("")) {
            txtCopago.setText(this.simbolo + " 0");
        }
        if (txtComision.getText().equals("")) {
            txtComision.setText("0");
        }
        if (txtDescuento.getText().equals("")) {
            txtDescuento.setText("0");
        }
        if (txtCantidad.getText().equals("")) {
            txtCantidad.setText("0");
        }
        if (txtCantidad1.getText().equals("")) {
            txtCantidad1.setText("0");
        }
        if (txtCuota.getText().equals("")) {
            txtCuota.setText("0");
        }

        if (txtDiagnostico1.getText().equals("")) {
            txtDiagnostico.setText("");
        }

        if (txtProcedimiento1.getText().equals("")) {
            txtProcedimiento.setText("");
        }

        String tipo = "procedimiento";

        if (rdConsulta.isSelected()) {
            tipo = "consulta";
        }
        consecutivo = txtConsecutivo.getText().substring(0, 5);
        consecutivo = consecutivo + "-" + instancias.getSql().getNumConsecutivoEps(consecutivo)[0];

        String factura = facturar();

        //ESTE CODIGO HACE LA FACTURA NO TIENE EPS, SI TIENE EPS DEBE TENER UN COPAGO SINO NO LA REALIZA YA QUE
        //SE DEDUCE QUE LA FACTURA LA PAGARA LA EPS POR ESTA RAZON NO SE SACA, PERO EL CODIGO LEGAL DE LOS RIPS
        //DICE QUE CADA ORDEN DEBE LLEVAR UNA FACTURA POR ESO SE ESTA QUITANDO LA CONDICION Y REALIZANDOSE ABAJO.
//        if ((cmbConvenio.getSelectedItem().equals("SI") && big.getBigDecimal(big.getMoneda(txtCopago.getText())).compareTo(BigDecimal.ZERO) > 0) || (cmbConvenio.getSelectedItem().equals("NO"))) {
//            factura = facturar();
//        }
        String idMedico = "";
        if (cmbMedico.getSelectedIndex() > 0) {
            idMedico = instancias.getSql().getIdEmpleado(cmbMedico.getSelectedItem().toString());
        }

        //guardar en ordenServicio
        String orden = instancias.getSql().getNumConsecutivo("ORDEN")[0].toString();
        Object[] vector = {orden, factura, instancias.getUsuario(), metodos.desdeDate(dtFecha.getCurrent()),
            txtPoliza.getText(), cmbConcepto.getSelectedItem().toString(), txtIdProd.getText(),
            txtAutorizacion.getText(), cmbFinalidad.getSelectedItem().toString(),
            cmbCausa.getSelectedItem().toString(), cmbAmbito.getSelectedItem().toString(),
            cmbPersonal.getSelectedItem().toString(), cmbForma.getSelectedItem().toString(),
            big.getMoneda(txtCopago.getText()), big.getMoneda(txtComision.getText()),
            big.getMoneda(txtDescuento.getText()), big.getMoneda(txtValor.getText()),
            big.getMoneda(txtCantidad.getText()), big.getMoneda(txtCuota.getText()),
            txtIdTercero.getText(), txtDiagnostico.getText(), txtProcedimiento.getText(),
            metodos.fechaConsulta(metodosGenerales.fechaHora()), tipo, txtCodigoIps.getText(), consecutivo.substring(6),
            actoQuirurgicoDesc(cmbActoQuirurgico.getSelectedItem().toString()), txtCantidad1.getText(),
            consecutivo, cmbCodigoConsulta.getSelectedItem(), cmbMedico.getSelectedItem(), metodosGenerales.hora(),
            convenio, idMedico};

        nodo = metodos.llenarOrdenServicio(vector);

        if (!instancias.getSql().agregarOrdenServicio(nodo)) {
            metodos.msgError(this, "Error al guardar la orden");
            return;
        }
        //actualizar consecutivo en eps
        if (!instancias.getSql().aumentarConsecutivoEps(consecutivo.substring(0, 5), Integer.parseInt((String) instancias.getSql().getNumConsecutivoEps(consecutivo.substring(0, 5))[0]) + 1)) {
            metodos.msgError(this, "Hubo un problema al guardar en el consecutivo de la orden");
        }
        /*if (!instancias.getSql().modificarConsecutivoEps(txtEps.getText(), "" + consecutivo)) {
         metodos.msgError(this, "Hubo un problema al actualizar el consecutivo de la eps");
         return;
         }*/

        if (instancias.isImprimirOrdenMedica()) {
            if (metodos.msgPregunta(this, "¿Desea imprimir la orden Nro." + orden + "?") == 0) {
                instancias.getReporte().verOrdenServicio(txtId.getText());
            }
        } else {
            metodos.msgExito(this, "Orden Nro " + orden + " exitosa");
        }

        for (int x = 0; x < jPanel11.getComponentCount(); x++) {
            if (jPanel11.getComponent(x) instanceof JTextField) {
                JTextField textField = (JTextField) jPanel11.getComponent(x);
                textField.setText("");
            } else {
                if (jPanel11.getComponent(x) instanceof JComboBox) {
                    JComboBox textField = (JComboBox) jPanel11.getComponent(x);
                    textField.setSelectedIndex(0);
                }
            }
        }
        txtCantidad.setText("1");
        txtCantidad1.setText("1");

        if (!instancias.getSql().aumentarConsecutivo("ORDEN", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("ORDEN")[0]) + 1)) {
            metodos.msgError(this, "Hubo un problema al guardar en el consecutivo de la orden");
        }

        //AGENDA...
        //Si hay alguna cita de la agenda activa se cambia el estado a confirmada.
//        try {
        String cliente = txtId.getText();
        String cita = "";
        try {
            cita = instancias.getSql().getAgendasDelDia(cliente, metodos.fechaConsulta(metodosGenerales.fecha()));
        } catch (Exception e) {
        }
        if (!cita.equalsIgnoreCase("")) {
            if (!instancias.getSql().modificarCita(cita, "Confirmada")) {
                metodos.msgError(null, "No fue posible modificar el estado de la Cita");
            }
            try {
                instancias.getAgendaConsulta().setSelected(true);
            } catch (Exception e) {
            }
            instancias.getAgendaConsulta().cargarAgendas(instancias.getMedico());
        }

        btnLimpiarActionPerformed(evt);
//        } catch (Exception e) {
//            metodos.msgError(this, "Hubo un problema al guardar el OrdenServicio");
//        }

    }//GEN-LAST:event_btnGuardar1ActionPerformed

    private void btnGuardar1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnGuardar1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGuardar1KeyReleased

    private void btnActualizar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizar1ActionPerformed
        Object[] campos = {txtId};
        String faltantes = metodos.camposVacios(campos);
        Object[] campos2 = {txtPoliza, txtaCobrar, txtAutorizacion};
        String faltantes2 = metodos.camposVacios(campos2);
        if (!faltantes.equals("")) {
            metodos.msgAdvertencia(this, "No puede continuar, faltan los siguientes campos: " + faltantes);
            return;
        } else if (!faltantes2.equals("")) {
            metodos.msgAdvertencia(this, "Faltan los siguientes campos: " + faltantes2);
        }
        if (metodos.msgPregunta(this, "¿Desea continuar?") == 0) {

            //try {
//            String valor = "0";
//            try {
//                ndProducto prod = instancias.getSql().getDatosProducto(txtaCobrar.getText());
//                valor = prod.getL1();
//            } catch (Exception e) {
//                valor = "0";
//            }
            if (txtCopago.getText().equals("")) {
                txtCopago.setText("0");
            }
            if (txtComision.getText().equals("")) {
                txtComision.setText("0");
            }
            if (txtDescuento.getText().equals("")) {
                txtDescuento.setText("0");
            }
            if (txtCantidad.getText().equals("")) {
                txtCantidad.setText("0");
            }
            if (txtCuota.getText().equals("")) {
                txtCuota.setText("0");
            }

            String tipo = "procedimiento";

            if (rdConsulta.isSelected()) {
                tipo = "consulta";
            }
            ndOrdenServicio nodoDos = instancias.getSql().getDatosOrdenServicio(txtId.getText());
            if (nodoDos.getConvenio().equals("")) {
                if (cmbConvenio.getSelectedItem().equals("SI")) {
                    nodoDos.setConvenio(convenio);
                }
            }

            String idMedico = "";
            if (cmbMedico.getSelectedIndex() > 0) {
                idMedico = instancias.getSql().getIdEmpleado(cmbMedico.getSelectedItem().toString());
            }

            Object[] vector = {txtId.getText(), nodoDos.getOrden(), instancias.getUsuario(), metodos.desdeDate(dtFecha.getCurrent()),
                txtPoliza.getText(), cmbConcepto.getSelectedItem().toString(), txtIdProd.getText(),
                txtAutorizacion.getText(), cmbFinalidad.getSelectedItem().toString(),
                cmbCausa.getSelectedItem().toString(), cmbAmbito.getSelectedItem().toString(),
                cmbPersonal.getSelectedItem().toString(), cmbForma.getSelectedItem().toString(),
                big.getMoneda(txtCopago.getText()), big.getMoneda(txtComision.getText()),
                big.getMoneda(txtDescuento.getText()), big.getMoneda(txtValor.getText()),
                big.getMoneda(txtCantidad.getText()), big.getMoneda(txtCuota.getText()),
                txtIdTercero.getText(), txtDiagnostico.getText(), txtProcedimiento.getText(), metodos.fechaConsulta(metodosGenerales.fechaHora()),
                tipo, txtCodigoIps.getText(), instancias.getSql().getDatosOrdenServicio(txtId.getText()).getCodigoConsecutivo(),
                actoQuirurgicoDesc(cmbActoQuirurgico.getSelectedItem().toString()), txtCantidad1.getText(), txtConsecutivo.getText(),
                cmbCodigoConsulta.getSelectedItem(), cmbMedico.getSelectedItem(), nodoDos.getHora(), nodoDos.getConvenio(), idMedico};

            ndOrdenServicio nodo = metodos.llenarOrdenServicio(vector);

            if (!nodoDos.getAcobrar().equalsIgnoreCase(nodo.getAcobrar()) || !nodoDos.getPaciente().equalsIgnoreCase(nodo.getPaciente())
                    || !nodoDos.getCopago().equalsIgnoreCase(nodo.getCopago()) || !nodoDos.getDescuento().equalsIgnoreCase(nodo.getDescuento())) {

                if (!nodoDos.getOrden().equals("")) {
                    ndFactura factura = instancias.getSql().getDatosFactura(nodoDos.getOrden());

                    if (!instancias.getSql().modificarFacturaFechaAnulacion(metodos.fechaConsulta(metodosGenerales.fecha()), instancias.getUsuario(), factura.getFactura(),
                            "Modificación de la orden medica", true, (String) instancias.getSql().getNumConsecutivo("ANULA")[0])) {
                        metodos.msgError(this, "Error al agregar la fecha de la anulación");
                        return;
                    }

                    if (!instancias.getSql().anularFacturaVerificadorFacturas(factura.getFactura())) {
                        metodos.msgError(this, "Error al agregar la fecha de la anulación");
                        return;
                    }

                    if (!instancias.getSql().aumentarConsecutivo("ANULA", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("ANULA")[0]) + 1)) {
                        metodos.msgError(this, "Hubo un problema al guardar en el consecutivo de la anulación");
                    }

                    metodos.msgExito(this, "Factura anulada con éxito");
                }

                //ESTE CODIGO HACE LA FACTURA NO TIENE EPS, SI TIENE EPS DEBE TENER UN COPAGO SINO NO LA REALIZA YA QUE
                //SE DEDUCE QUE LA FACTURA LA PAGARA LA EPS POR ESTA RAZON NO SE SACA, PERO EL CODIGO LEGAL DE LOS RIPS
                //DICE QUE CADA ORDEN DEBE LLEVAR UNA FACTURA POR ESO SE ESTA QUITANDO LA CONDICION Y REALIZANDOSE ABAJO.
//                if ((cmbConvenio.getSelectedItem().equals("SI") && big.getBigDecimal(big.getMoneda(txtCopago.getText())).compareTo(BigDecimal.ZERO) > 0) || (cmbConvenio.getSelectedItem().equals("NO"))) {
//                    nodo.setOrden(facturar());
//                }
                nodo.setOrden(facturar());
            }

            if (!instancias.getSql().modificarOrdenServicio(nodo)) {
                metodos.msgError(this, "Error al modificar la orden");
                return;
            } else {
                metodos.msgExito(this, "Orden modificado con éxito");
            }

            if (metodos.msgPregunta(this, "¿Desea imprimir?") == 0) {
                instancias.getReporte().verOrdenServicio(txtId.getText());
            }

            for (int x = 0; x < jPanel11.getComponentCount(); x++) {
                if (jPanel11.getComponent(x) instanceof JTextField) {
                    JTextField textField = (JTextField) jPanel11.getComponent(x);
                    textField.setText("");
                } else {
                    if (jPanel11.getComponent(x) instanceof JComboBox) {
                        JComboBox textField = (JComboBox) jPanel11.getComponent(x);
                        textField.setSelectedIndex(0);
                    }
                }
            }
            txtCantidad.setText("1");

            btnLimpiarActionPerformed(evt);
//            } catch (Exception e) {
//                metodos.msgError(this, "Hubo un problema al modificar el OrdenServicio");
//            }
        }
    }//GEN-LAST:event_btnActualizar1ActionPerformed

    private void txtCopagoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCopagoKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtCopagoKeyTyped

    private void txtCantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtCantidadKeyTyped

    private void txtDiagnosticoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiagnosticoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                txtDiagnostico1.setText(instancias.getSql().getNombreCie(txtDiagnostico.getText()));
            } catch (Exception e) {
                ventanaCies(txtDiagnostico.getText());
            }
        } else {
            txtDiagnostico1.setText("");
        }
    }//GEN-LAST:event_txtDiagnosticoKeyReleased

    private void txtDiagnosticoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiagnosticoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiagnosticoKeyTyped

    private void txtProcedimientoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProcedimientoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                txtProcedimiento1.setText(instancias.getSql().getNombreCup(txtProcedimiento.getText()));
            } catch (Exception e) {
                ventanaCups(txtProcedimiento.getText());
            }
        } else {
            txtProcedimiento1.setText("");
        }
    }//GEN-LAST:event_txtProcedimientoKeyReleased

    private void txtProcedimientoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProcedimientoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProcedimientoKeyTyped

    private void txtPacienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPacienteKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cargarCliente(txtPaciente.getText());
        } else if (!txtNombre.getText().equals("")) {
            txtNombre.setText("");
            txtIdTercero.setText("");
            convenio = "";
        }
    }//GEN-LAST:event_txtPacienteKeyReleased

    private void txtPacienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPacienteKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPacienteKeyTyped

    private void txtNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreKeyReleased

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped

    }//GEN-LAST:event_txtNombreKeyTyped

    private void txtPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPacienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPacienteActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        for (int x = 0; x < jPanel1.getComponentCount(); x++) {
            if (jPanel1.getComponent(x) instanceof JTextField) {
                JTextField textField = (JTextField) jPanel1.getComponent(x);
                textField.setText("");
            } else {
                if (jPanel1.getComponent(x) instanceof JComboBox) {
                    JComboBox textField = (JComboBox) jPanel1.getComponent(x);
                    textField.setSelectedIndex(0);
                }
            }
        }

        for (int x = 0; x < jPanel2.getComponentCount(); x++) {
            if (jPanel2.getComponent(x) instanceof JTextField) {
                JTextField textField = (JTextField) jPanel2.getComponent(x);
                textField.setText("");
            } else {
                if (jPanel2.getComponent(x) instanceof JComboBox) {
                    JComboBox textField = (JComboBox) jPanel2.getComponent(x);
                    textField.setSelectedIndex(0);
                }
            }
        }

        for (int x = 0; x < jPanel3.getComponentCount(); x++) {
            if (jPanel3.getComponent(x) instanceof JTextField) {
                JTextField textField = (JTextField) jPanel3.getComponent(x);
                textField.setText("");
            } else {
                if (jPanel3.getComponent(x) instanceof JComboBox) {
                    JComboBox textField = (JComboBox) jPanel3.getComponent(x);
                    textField.setSelectedIndex(0);
                }
            }
        }

        txtCantidad.setText("1");
        txtCantidad1.setText("1");

        txtValor.setText(this.simbolo + " 0");
        txtCopago.setText(this.simbolo + " 0");
        txtDescuento.setText(this.simbolo + " 0");
        txtComision.setText(this.simbolo + " 0");
        txtCuota.setText(this.simbolo + " 0");

        txtEps.setToolTipText(" ");
        txtId.setText("" + instancias.getSql().getNumConsecutivo("ORDEN")[0]);
        btnActualizar1.setEnabled(false);
        btnAnular.setEnabled(false);
        btnGuardar1.setEnabled(true);
        convenio = "";
        dtFecha.setSelectedDate(Calendar.getInstance());
//        txtOrden.setText("FACT-" + instancias.getSql().getNumConsecutivo("FACT")[0]);
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnBuscTercerosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscTercerosActionPerformed
        if (metodos.msgPregunta(this, "¿Desea imprimir?") == 0) {
            instancias.getReporte().verOrdenServicio(txtId.getText());
        }
    }//GEN-LAST:event_btnBuscTercerosActionPerformed

    private void txtValorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorKeyReleased
        if (txtValor.getText().equals("") || txtValor.getText().equals(this.simbolo) || txtValor.getText().equals(this.simbolo + " ")) {
            txtValor.setText("0");
        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtCopago.requestFocus();
        } else {
            txtValor.setText(big.setMoneda(big.getMoneda(txtValor.getText())));
        }
    }//GEN-LAST:event_txtValorKeyReleased

    private void txtValorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValorKeyTyped

    private void txtEpsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEpsKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEpsKeyReleased

    private void txtEpsKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEpsKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEpsKeyTyped

    private void cmbConvenioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbConvenioKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbConvenioKeyReleased

    private void cmbConvenioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbConvenioItemStateChanged
        if (cmbConvenio.getSelectedItem().equals("NO")) {
            txtCopago.setText(this.simbolo + " 0");
            txtCuota.setText(this.simbolo + " 0");
            txtAutorizacion.setText("");
            txtPoliza.setText("");
            txtCopago.setEnabled(false);
            txtCuota.setEnabled(false);
            txtAutorizacion.setEnabled(false);
            txtPoliza.setEnabled(false);
        } else {
            txtCopago.setEnabled(true);
            txtCuota.setEnabled(true);
            txtAutorizacion.setEnabled(true);
            txtPoliza.setEnabled(true);
        }

//        cargarProducto(txtaCobrar.getText(), 1);
    }//GEN-LAST:event_cmbConvenioItemStateChanged

    private void txtProcedimiento1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProcedimiento1KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                txtProcedimiento.setText(instancias.getSql().getNombreCup(txtProcedimiento1.getText()));
            } catch (Exception e) {
                txtProcedimiento.setText("");
            }
        }
    }//GEN-LAST:event_txtProcedimiento1KeyReleased

    private void txtProcedimiento1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProcedimiento1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProcedimiento1KeyTyped

    private void txtDiagnostico1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiagnostico1KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                txtDiagnostico.setText(instancias.getSql().getNombreCie(txtDiagnostico1.getText()));
            } catch (Exception e) {
                txtDiagnostico.setText("");
            }
        }
    }//GEN-LAST:event_txtDiagnostico1KeyReleased

    private void txtDiagnostico1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiagnostico1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiagnostico1KeyTyped

    private void txtCodigoIpsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoIpsKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoIpsKeyReleased

    private void txtCodigoIpsKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoIpsKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoIpsKeyTyped

    private void txtValorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtValorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValorActionPerformed

    private void txtCantidad1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidad1KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!txtValor.getText().equals("")) {
                txtValor.setText(big.setMoneda(big.getMoneda(valor).multiply(big.getBigDecimal(txtCantidad.getText()))));
            }
        }
    }//GEN-LAST:event_txtCantidad1KeyReleased

    private void txtCantidad1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidad1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidad1KeyTyped

    private void txtDiagnostico1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiagnostico1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiagnostico1ActionPerformed

    private void txtaCobrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtaCobrarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtaCobrarActionPerformed

    private void txtCantidad1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantidad1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidad1ActionPerformed

    private void txtaCobrar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtaCobrar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtaCobrar1ActionPerformed

    private void txtaCobrar1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtaCobrar1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtaCobrar1KeyReleased

    private void txtaCobrar1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtaCobrar1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtaCobrar1KeyTyped

    private void rdProcedimientoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdProcedimientoItemStateChanged
        /*        if (rdProcedimiento.isSelected()) {
         cmbActoQuirurgico.setEnabled(true);
         } else {
         cmbActoQuirurgico.setEnabled(false);
         }*/
    }//GEN-LAST:event_rdProcedimientoItemStateChanged

    private void rdConsultaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdConsultaItemStateChanged
        /*        if (rdConsulta.isSelected()) {
         cmbActoQuirurgico.setEnabled(false);
         txtDiagnostico.setEnabled(false);
         txtProcedimiento.setEnabled(false);
         txtAutorizacion.setEnabled(true);
         } else {
         cmbActoQuirurgico.setEnabled(true);
         txtDiagnostico.setEnabled(true);
         txtProcedimiento.setEnabled(true);
         txtAutorizacion.setEnabled(true);
         }*/
    }//GEN-LAST:event_rdConsultaItemStateChanged

    private void rdProcedimientoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rdProcedimientoStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_rdProcedimientoStateChanged

    private void txtEps1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEps1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEps1KeyReleased

    private void txtEps1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEps1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEps1KeyTyped

    private void txtConsecutivoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtConsecutivoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtConsecutivoKeyReleased

    private void txtConsecutivoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtConsecutivoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtConsecutivoKeyTyped

    private void cmbCodigoConsultaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbCodigoConsultaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbCodigoConsultaKeyReleased

    private void cmbMedicoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbMedicoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbMedicoKeyReleased

    private void lbPacienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lbPacienteKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtPaciente.requestFocus();
        }
    }//GEN-LAST:event_lbPacienteKeyReleased

    private void btnAnularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnularActionPerformed
        String consecutivo = txtId.getText();

        if (metodos.msgPregunta(this, "¿Esta seguro de anular esta orden?") == 0) {

            if (!instancias.getSql().inactivarOrden(consecutivo)) {
                metodos.msgError(this, "Hubo un problema al anular el ingreso");
                return;
            }

            ndOrdenServicio nodoDos = instancias.getSql().getDatosOrdenServicio(txtId.getText());

            if (!nodoDos.getOrden().equals("")) {
                ndFactura factura = instancias.getSql().getDatosFactura(nodoDos.getOrden());

                if (!instancias.getSql().modificarFacturaFechaAnulacion(metodos.fechaConsulta(metodosGenerales.fecha()), instancias.getUsuario(), factura.getFactura(),
                        "Modificación de la orden medica", true, (String) instancias.getSql().getNumConsecutivo("ANULA")[0])) {
                    metodos.msgError(this, "Error al agregar la fecha de la anulación");
                    return;
                }

                if (!instancias.getSql().anularFacturaVerificadorFacturas(factura.getFactura())) {
                    metodos.msgError(this, "Error al agregar la fecha de la anulación");
                    return;
                }

                if (!instancias.getSql().aumentarConsecutivo("ANULA", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("ANULA")[0]) + 1)) {
                    metodos.msgError(this, "Hubo un problema al guardar en el consecutivo de la anulación");
                }
            }

            metodos.msgExito(this, "Orden y factura anulada con éxito");
            btnLimpiarActionPerformed(null);
        }
    }//GEN-LAST:event_btnAnularActionPerformed

    private void cmbCodigoConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCodigoConsultaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbCodigoConsultaActionPerformed

    private void txtIdProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdProdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdProdActionPerformed

    private void txtIdProdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdProdKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdProdKeyReleased

    private void txtIdProdKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdProdKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdProdKeyTyped

    private void txtDiagnosticoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDiagnosticoFocusGained
        try {
            txtDiagnostico1.setText(instancias.getSql().getNombreCie(txtDiagnostico.getText()));
        } catch (Exception e) {

        }
    }//GEN-LAST:event_txtDiagnosticoFocusGained

    private void txtProcedimientoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtProcedimientoFocusGained
        try {
            txtProcedimiento1.setText(instancias.getSql().getNombreCup(txtProcedimiento.getText()));
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtProcedimientoFocusGained

    private void txtIdTerceroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdTerceroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdTerceroActionPerformed

    private void txtIdTerceroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdTerceroKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdTerceroKeyReleased

    private void txtIdTerceroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdTerceroKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdTerceroKeyTyped

//    public void setInstancias(Instancias instancias) {
//        this.instancias = instancias;
//        btnLimpiarActionPerformed(null);
//        cmbMedico.removeAllItems();
//        Object[][] aux = instancias.getSql().getEmpleadosCargo("Medico");
//        cmbMedico.addItem("");
//        for (int i = 0; i < aux.length; i++) {
//            cmbMedico.addItem(aux[i][1].toString());
//        }
//    }
    public void cargarOrdenServicio(String Id) {
        ndOrdenServicio nodo = instancias.getSql().getDatosOrdenServicio(Id);
        cargarOrdenServicio(nodo);
    }

    public void cargarOrdenServicio(ndOrdenServicio nodo) {

        if (nodo.getId() != null) {
            String estadoInac = instancias.getSql().getEstadoOrdenServicio(nodo.getOrden());

            if (estadoInac.equals("true")) {
                metodos.msgAdvertenciaAjustado(this, "La orden esta anulada");
                btnLimpiarActionPerformed(null);
                return;
            }

            txtId.setText(nodo.getId());
            txtPoliza.setText(nodo.getPoliza());
            cmbConcepto.setSelectedItem(nodo.getConcepto());
            txtaCobrar.setText(nodo.getAcobrar());
            txtAutorizacion.setText(nodo.getAutorizacion());
            cmbFinalidad.setSelectedItem(nodo.getFinalidad());
            cmbCausa.setSelectedItem(nodo.getCausa());
            cmbAmbito.setSelectedItem(nodo.getAmbito());
            cmbPersonal.setSelectedItem(nodo.getPersonal());
            cmbForma.setSelectedItem(nodo.getForma());
            txtComision.setText(big.setMoneda(big.getBigDecimal(nodo.getComision())));
            txtDescuento.setText(big.setMoneda(big.getBigDecimal(nodo.getDescuento())));
            txtCopago.setText(big.setMoneda(big.getBigDecimal(nodo.getCopago())));
            txtCantidad.setText(big.setNumero(big.getBigDecimal(nodo.getCantidad())));
            txtCantidad1.setText(big.setNumero(big.getBigDecimal(nodo.getCantidadRealizada())));
            txtCuota.setText(big.setMoneda(big.getBigDecimal(nodo.getCuota())));
            txtCodigoIps.setText(nodo.getCodigoIps());
            dtFecha.setSelectedDate(metodos.haciaDate(nodo.getFecha()));
            cmbMedico.setSelectedItem(nodo.getMedico());

            ModeloContacto nodo2 = instancias.getSql().getDatosTercero(nodo.getPaciente());
            if (nodo2.getId() != null) {
                txtPaciente.setText(nodo2.getId());
                txtNombre.setText(nodo2.getNombre());
                txtEps.setText(nodo2.getEps());
                txtEps.setToolTipText(instancias.getSql().getDatosEps(nodo2.getEps()).getDato2());
                txtEps1.setText(instancias.getSql().getDatosEpsPrecargados(nodo2.getEps()).getNombre());
                txtIdTercero.setText(nodo2.getIdSistema());
            }

            try {
                txtConsecutivo.setText(nodo.getConsecutivoEps());
            } catch (Exception e) {
                System.out.println("consecutivo eps :" + e);
            }
            try {
                cmbCodigoConsulta.setSelectedItem(nodo.getCodigoConsulta());
            } catch (Exception e) {
                System.out.println("codigo consulta: " + e);
            }

            cargarProducto(nodo.getAcobrar(), 0);

            txtDiagnostico.setText(nodo.getCie());
            txtProcedimiento.setText(nodo.getCup());

            if (!txtDiagnostico.getText().equals("")) {
                metodos.presionarEnter(txtDiagnostico);
            }

            if (!txtProcedimiento.getText().equals("")) {
                metodos.presionarEnter(txtProcedimiento);
            }

            //txtOrden.requestFocus();
            if (nodo.getTipo().equals("consulta")) {
                rdConsulta.setSelected(true);
            } else {
                rdProcedimiento.setSelected(true);
            }

            cmbActoQuirurgico.setSelectedItem(actoQuirurgicoCod(nodo.getActoQuirurgico()));
            btnActualizar1.setEnabled(true);
            btnGuardar1.setEnabled(false);
            btnAnular.setEnabled(true);
            return;
        }
    }

    public void cargarProducto(String codigo, int sw) {

        ndProducto nodo = null;

        String codigoProd = "";
        if (codigo.equals("")) {
            codigoProd = "";
        } else {
            Object[][] listado = instancias.getSql().getCodigosRelacionados(codigo, " where codigo");
            if (listado.length > 0) {
                codigo = listado[0][0].toString();
            }

            nodo = instancias.getSql().getDatosProducto(codigo, "bdProductos");
            if (nodo.getIdSistema() != null) {
                codigoProd = nodo.getIdSistema();
            }
        }

        if (!codigoProd.equals("")) {

            txtaCobrar.setText(nodo.getCodigo());
            txtIdProd.setText(nodo.getIdSistema());
            txtValor.requestFocus();

            try {
                if (cmbConvenio.getSelectedItem().equals("SI") && !convenio.equals("")) {
                    ndConvenio nodo2 = instancias.getSql().getDatosConvenio(convenio, "convenio");
                    if (nodo2.getTipoFactura().equals("ISS")) {
                        System.out.println("aca va la consulta a la base de datos de iss");
                        txtValor.setText(big.setMoneda(big.getBigDecimal(nodo.getL1()).multiply(BigDecimal.ONE.add(big.getBigDecimal(nodo2.getPorcentaje()).divide(big.getBigDecimal("100"), 2, RoundingMode.HALF_DOWN)))));
                    } else if (nodo2.getTipoFactura().equals("SOAT")) {
                        System.out.println("aca va la consulta a la base de datos de soat");
                        txtValor.setText(big.setMoneda(big.getBigDecimal(nodo.getL1()).multiply(BigDecimal.ONE.add(big.getBigDecimal(nodo2.getPorcentaje()).divide(big.getBigDecimal("100"), 2, RoundingMode.HALF_DOWN)))));
                    } else {
                        txtValor.setText(big.setMoneda(big.getBigDecimal(nodo.getL1())));
                    }
                } else {
                    txtValor.setText(big.setMoneda(big.getBigDecimal(nodo.getL1())));
                }
                valor = txtValor.getText();
                txtaCobrar1.setText(nodo.getDescripcion());
            } catch (NullPointerException e) {
                txtValor.setText(big.setMoneda(big.getBigDecimal(nodo.getL1())));
                txtaCobrar1.setText(nodo.getDescripcion());
                valor = txtValor.getText();
            }
            return;
        }

        if (sw == 1) {
            ventanaProductos(codigo);
        }
    }

    public void ventanaProductos(String codigo) {
        VistaBuscadorProductos buscar = new VistaBuscadorProductos(null, rootPaneCheckingEnabled, false, "");
        buscar.setLocationRelativeTo(null);
        instancias.setBusProductos(buscar);
        instancias.setCampoActual(txtaCobrar);
        txtaCobrar.requestFocus();
        buscar.noEncontrado(codigo.replace("'", "//"));
        buscar.show();
    }

    public void cargarCliente(String nit) {
        ModeloContacto nodo = instancias.getSql().getDatosTercero(nit);

        if (nodo.getId() != null) {

            if (nodo.isActivo()) {
                metodos.msgError(null, "Este cliente esta inactivado");
                lbPaciente.requestFocus();
                return;
            }

            if (nodo.getEps() == null) {
                metodos.msgAdvertencia(this, "El paciente debe tener una EPS relacionada");
                return;
            } else {
                if (!nodo.getEps().equals("0001")) {
                    cmbConvenio.setSelectedItem("SI");
                }
            }

            txtIdTercero.setText(nodo.getIdSistema());
            txtPaciente.setText(nodo.getId());
            txtNombre.setText(nodo.getNombre());
            txtEps.setText(nodo.getEps());
            txtEps.setToolTipText(instancias.getSql().getDatosEps(nodo.getEps()).getDato2());

            ndEpsPrecargados nodo2 = instancias.getSql().getDatosEpsPrecargados(nodo.getEps());
            txtEps1.setText(nodo2.getNombre());
            txtConsecutivo.setText(nodo2.getConsecutivo() + "-" + instancias.getSql().getNumConsecutivoEps(nodo2.getConsecutivo())[0]);

            Object[] datosConvenio = instancias.getSql().getDatosConvenioPendiente(nodo.getConvenioActual(), "AND estado = 'PENDIENTE'");
            if (datosConvenio[4] != null) {
                convenio = datosConvenio[4].toString();
            }

            txtaCobrar.requestFocus();

            ventanaOrden(nit);
            return;

        }
        ventanaTerceros(nit);
    }

    private void ventanaOrden(String paciente) {
        infReimpresionExamenes reimpresion = new infReimpresionExamenes();
        reimpresion.cargarRegistros(paciente, "orden", instancias);
    }

    public void ventanaTerceros(String nit) {
        buscClientes buscar = new buscClientes(instancias.getMenu(), rootPaneCheckingEnabled, false, null, "");
        buscar.setLocationRelativeTo(null);
        instancias.setBusClientes(buscar);
        instancias.setCampoActual(txtPaciente);
        txtPaciente.requestFocus();
        buscar.noEncontrado(nit);
        buscar.show();
    }

    public void ventanaCies(String codigo) {
        buscCies buscar = new buscCies(null, rootPaneCheckingEnabled);
        buscar.setLocationRelativeTo(null);
        instancias.setBuscCies(buscar);
        instancias.setCampoActual(txtDiagnostico);
        txtDiagnostico.requestFocus();
//        buscar.setInstancia(instancias);
        buscar.noEncontrado(codigo.replace("'", "//"));
        buscar.show();
    }

    public void ventanaCups(String codigo) {
        buscCups buscar = new buscCups(null, rootPaneCheckingEnabled);
        buscar.setLocationRelativeTo(null);
        instancias.setBuscCups(buscar);
        instancias.setCampoActual(txtProcedimiento);
        txtProcedimiento.requestFocus();
//        buscar.setInstancia(instancias);
        buscar.noEncontrado(codigo.replace("'", "//"));
        buscar.show();
    }

    public String facturar() {

        String[] e = new String[5];
        e[0] = txtaCobrar.getText(); //Codigo a cobrar
        e[1] = txtCantidad.getText(); //Cantidad
        e[2] = txtDescuento.getText(); //Descuento
        e[3] = txtValor.getText(); //Valor a pagar
        e[4] = txtCopago.getText(); //Copago

        if (cmbConvenio.getSelectedItem().equals("SI")) {
            return instancias.getFactura().desdeOrdenMedica(txtIdTercero.getText(), e, true);
        } else {
            return instancias.getFactura().desdeOrdenMedica(txtIdTercero.getText(), e, false);
        }

    }

    public void cargarClienteDesdeAgenda(String nit, String medico) {
        btnLimpiarActionPerformed(null);

        cmbMedico.setSelectedItem(medico);
        ModeloContacto nodo = instancias.getSql().getDatosTercero(nit);

        try {
            this.setSelected(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(VistaEgresos.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (nodo.getId() != null) {
            txtPaciente.setText(nodo.getId());
            txtNombre.setText(nodo.getNombre());
            txtEps.setText(nodo.getEps());
            txtEps.setToolTipText(instancias.getSql().getDatosEps(nodo.getEps()).getDato2());

            try {
                ndEpsPrecargados nodo2 = instancias.getSql().getDatosEpsPrecargados(nodo.getEps());
                txtEps1.setText(nodo2.getNombre());
                txtConsecutivo.setText(nodo2.getConsecutivo() + "-" + instancias.getSql().getNumConsecutivoEps(nodo2.getConsecutivo())[0]);
            } catch (Exception e) {
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar1;
    private javax.swing.JButton btnAnular;
    private javax.swing.JButton btnBuscTerceros;
    private javax.swing.JButton btnGuardar1;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cmbActoQuirurgico;
    private javax.swing.JComboBox cmbAmbito;
    private javax.swing.JComboBox cmbCausa;
    private javax.swing.JComboBox cmbCodigoConsulta;
    private javax.swing.JComboBox cmbConcepto;
    private javax.swing.JComboBox cmbConvenio;
    private javax.swing.JComboBox cmbFinalidad;
    private javax.swing.JComboBox cmbForma;
    private javax.swing.JComboBox cmbMedico;
    private javax.swing.JComboBox cmbPersonal;
    private datechooser.beans.DateChooserCombo dtFecha;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel lbNit;
    private javax.swing.JLabel lbPaciente;
    private javax.swing.JLabel lbRazon;
    private javax.swing.JLabel lbRazon1;
    private javax.swing.JLabel lbRazon10;
    private javax.swing.JLabel lbRazon11;
    private javax.swing.JLabel lbRazon12;
    private javax.swing.JLabel lbRazon13;
    private javax.swing.JLabel lbRazon14;
    private javax.swing.JLabel lbRazon15;
    private javax.swing.JLabel lbRazon16;
    private javax.swing.JLabel lbRazon17;
    private javax.swing.JLabel lbRazon18;
    private javax.swing.JLabel lbRazon19;
    private javax.swing.JLabel lbRazon20;
    private javax.swing.JLabel lbRazon21;
    private javax.swing.JLabel lbRazon22;
    private javax.swing.JLabel lbRazon23;
    private javax.swing.JLabel lbRazon24;
    private javax.swing.JLabel lbRazon25;
    private javax.swing.JLabel lbRazon26;
    private javax.swing.JLabel lbRazon27;
    private javax.swing.JLabel lbRazon28;
    private javax.swing.JLabel lbRazon29;
    private javax.swing.JLabel lbRazon3;
    private javax.swing.JLabel lbRazon30;
    private javax.swing.JLabel lbRazon4;
    private javax.swing.JLabel lbRazon5;
    private javax.swing.JLabel lbRazon6;
    private javax.swing.JLabel lbRazon7;
    private javax.swing.JLabel lbRazon8;
    private javax.swing.JPanel pnlFormulario;
    private javax.swing.JPanel pnlInvisible;
    private javax.swing.JRadioButton rdConsulta;
    private javax.swing.JRadioButton rdProcedimiento;
    private javax.swing.JScrollPane scrFormulario;
    private javax.swing.JTextField txtAutorizacion;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtCantidad1;
    private javax.swing.JTextField txtCodigoIps;
    private javax.swing.JTextField txtComision;
    private javax.swing.JTextField txtConsecutivo;
    private javax.swing.JTextField txtCopago;
    private javax.swing.JTextField txtCuota;
    private javax.swing.JTextField txtDescuento;
    private javax.swing.JTextField txtDiagnostico;
    private javax.swing.JTextField txtDiagnostico1;
    private javax.swing.JTextField txtEps;
    private javax.swing.JTextField txtEps1;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtIdProd;
    private javax.swing.JTextField txtIdTercero;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPaciente;
    private javax.swing.JTextField txtPoliza;
    private javax.swing.JTextField txtProcedimiento;
    private javax.swing.JTextField txtProcedimiento1;
    private javax.swing.JTextField txtValor;
    private javax.swing.JTextField txtaCobrar;
    private javax.swing.JTextField txtaCobrar1;
    // End of variables declaration//GEN-END:variables

    private void cargando(String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
