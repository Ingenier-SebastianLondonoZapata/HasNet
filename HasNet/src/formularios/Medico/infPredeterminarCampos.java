package formularios.Medico;

import clases.Instancias;
import clases.Medico.ndCamposOrdenPredeterminada;
import clases.Medico.ndOrdenServicio;
import Utilidades.BaseDatos.SQL;
import clases.big;
import clases.metodosGenerales;
import clases.productos.ndProducto;
import formularios.productos.buscProductos;
import java.awt.event.KeyEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class infPredeterminarCampos extends javax.swing.JDialog {

    DefaultTableModel modelo;
    TableRowSorter modeloOrdenado;
    Instancias instancias;
    private String valor;
    metodosGenerales metodos = new metodosGenerales();

    public infPredeterminarCampos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        instancias = Instancias.getInstancias();

        if (instancias.getConfiguraciones().isLaboratorio()) {
            lbRazon11.setVisible(false);
            txtaCobrar.setVisible(false);
            txtaCobrar1.setVisible(false);
            txtValor.setVisible(false);
            lbRazon19.setVisible(false);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoTipo = new javax.swing.ButtonGroup();
        pnlFormulario = new javax.swing.JPanel();
        lbNit10 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lbCambiarTamano = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        lbRazon4 = new javax.swing.JLabel();
        lbRazon = new javax.swing.JLabel();
        lbRazon5 = new javax.swing.JLabel();
        lbRazon11 = new javax.swing.JLabel();
        lbRazon13 = new javax.swing.JLabel();
        lbRazon14 = new javax.swing.JLabel();
        lbRazon15 = new javax.swing.JLabel();
        cmbConcepto = new javax.swing.JComboBox();
        cmbAmbito = new javax.swing.JComboBox();
        cmbFinalidad = new javax.swing.JComboBox();
        cmbCausa = new javax.swing.JComboBox();
        cmbPersonal = new javax.swing.JComboBox();
        txtaCobrar = new javax.swing.JTextField();
        cmbForma = new javax.swing.JComboBox();
        lbRazon19 = new javax.swing.JLabel();
        txtValor = new javax.swing.JTextField();
        lbRazon23 = new javax.swing.JLabel();
        rdProcedimiento = new javax.swing.JRadioButton();
        rdConsulta = new javax.swing.JRadioButton();
        lbRazon25 = new javax.swing.JLabel();
        cmbActoQuirurgico = new javax.swing.JComboBox();
        txtaCobrar1 = new javax.swing.JTextField();
        lbRazon28 = new javax.swing.JLabel();
        cmbCodigoConsulta = new javax.swing.JComboBox();
        btnGuardar1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("PROCESOS");

        pnlFormulario.setBackground(new java.awt.Color(255, 255, 255));

        lbNit10.setFont(new java.awt.Font("Bell MT", 1, 36)); // NOI18N
        lbNit10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit10.setText("ORDEN DE SERVICIO");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel3MouseEntered(evt);
            }
        });

        lbCambiarTamano.setBackground(new java.awt.Color(153, 255, 153));
        lbCambiarTamano.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbCambiarTamano.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/expandir.png"))); // NOI18N
        lbCambiarTamano.setToolTipText("Expandir/Contraer Menu");
        lbCambiarTamano.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        lbCambiarTamano.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbCambiarTamano.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        lbCambiarTamano.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        lbCambiarTamano.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbCambiarTamanoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbCambiarTamanoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbCambiarTamanoMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(lbCambiarTamano)
                .addGap(23, 23, 23))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(lbCambiarTamano)
                .addGap(31, 31, 31))
        );

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        lbRazon4.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbRazon4.setText("Concepto:");

        lbRazon.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbRazon.setText("Causa Externa:");

        lbRazon5.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbRazon5.setText("Finalidad:");

        lbRazon11.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbRazon11.setText("Descripción Servicio:");

        lbRazon13.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbRazon13.setText("Ambito Procedimiento:");

        lbRazon14.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbRazon14.setText("Personal que atiende:");

        lbRazon15.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbRazon15.setText("Forma:");

        cmbConcepto.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Consultas", "Procedimientos de diagnósticos", "Procedimientos terapéuticos no quirúrgicos", "Procedimientos terapéuticos quirúrgicos", "Procedimientos de promoción y prevención", "Estancias", "Honorarios", "Derechos de sala", "Materiales e insumos", "Banco de sangre", "Prótesis y órtesis", "Medicamentos POS", "Medicamentos no POS", "Traslado de pacientes" }));
        cmbConcepto.setName("Concepto"); // NOI18N
        cmbConcepto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbConceptoKeyReleased(evt);
            }
        });

        cmbAmbito.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ambulatorio", "Hospitalario", "De urgencia" }));
        cmbAmbito.setName("Ambito Procedimiento"); // NOI18N
        cmbAmbito.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbAmbitoKeyReleased(evt);
            }
        });

        cmbFinalidad.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "No aplica", "Atención del parto (puerperio)", "Atención del recién nacido", "Atención en planificación familiar", "Detección de alteraciones de crecimiento y desarrollo del menor de diez años", "Detección de alteración del desarrollo joven", "Detección de alteraciones del embarazo", "Detección de alteraciones del adulto", "Detección de alteraciones de agudeza visual", "Detección de enfermedad profesional", "Diagnóstico", "Terapéutico", "Protección específica", "Detección temprana de enfermedad general", "Detección temprana de enfermedad profesional" }));
        cmbFinalidad.setName("Finalidad"); // NOI18N
        cmbFinalidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbFinalidadKeyReleased(evt);
            }
        });

        cmbCausa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Enfermedad general", "Accidente de trabajo", "Accidente de tránsito", "Accidente rábico", "Accidente ofídico", "Otro tipo de accidente", "Evento catastrófico", "Lesión por agresión", "Lesión auto infligida", "Sospecha de maltrato físico", "Sospecha de abuso sexual", "Sospecha de violencia sexual", "Sospecha de maltrato emocional", "Enfermedad profesional", "Otra" }));
        cmbCausa.setName("Causa Externa"); // NOI18N
        cmbCausa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbCausaKeyReleased(evt);
            }
        });

        cmbPersonal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Médico (a) general", "Médico (A) especialista", "Enfermera (o)", "Auxiliar de enfermería", "Otro" }));
        cmbPersonal.setName("Personal"); // NOI18N
        cmbPersonal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbPersonalKeyReleased(evt);
            }
        });

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

        cmbForma.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Consulta externa o programada", "Urgencias", "Remitido", "Nacido en la institución" }));
        cmbForma.setName("Forma"); // NOI18N
        cmbForma.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbFormaKeyReleased(evt);
            }
        });

        lbRazon19.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbRazon19.setText("Valor:");

        txtValor.setEditable(false);
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

        lbRazon23.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbRazon23.setText("Tipo:");

        rdProcedimiento.setBackground(new java.awt.Color(255, 255, 255));
        grupoTipo.add(rdProcedimiento);
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

        rdConsulta.setBackground(new java.awt.Color(255, 255, 255));
        grupoTipo.add(rdConsulta);
        rdConsulta.setSelected(true);
        rdConsulta.setText("Consulta");
        rdConsulta.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdConsultaItemStateChanged(evt);
            }
        });

        lbRazon25.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbRazon25.setText("Acto quirurgico:");

        cmbActoQuirurgico.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Unico o unilateral", "Múltiple o bilateral, misma vía, diferente especialidad", "Múltiple o bilateral, misma vía, igual especialidad", "Múltiple o bilateral, diferente vía, diferente especialidad", "Múltiple o bilateral, diferente vía, igual especialidad" }));

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

        lbRazon28.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbRazon28.setText("Codigo Consulta:");

        cmbCodigoConsulta.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Consulta de Medicina General electiva", "Consulta de Medicina Especializada electiva", "Consulta de Medicina General de control", "Consulta de Medicina Especializada de control" }));
        cmbCodigoConsulta.setName("Forma"); // NOI18N
        cmbCodigoConsulta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbCodigoConsultaKeyReleased(evt);
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

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(165, 165, 165)
                        .addComponent(txtaCobrar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtaCobrar1))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbRazon5, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbRazon14, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbRazon15, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbRazon28, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbCodigoConsulta, 0, 272, Short.MAX_VALUE)
                            .addComponent(cmbPersonal, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbForma, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(lbRazon23, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(rdProcedimiento)
                        .addGap(18, 18, 18)
                        .addComponent(rdConsulta)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(lbRazon11, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmbFinalidad, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lbRazon13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbRazon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbRazon4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbRazon25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(lbRazon19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addComponent(btnGuardar1)
                        .addGap(15, 15, 15))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cmbConcepto, javax.swing.GroupLayout.Alignment.LEADING, 0, 251, Short.MAX_VALUE)
                            .addComponent(cmbCausa, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtValor))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cmbAmbito, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbActoQuirurgico, 0, 1, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbRazon23)
                            .addComponent(rdProcedimiento)
                            .addComponent(rdConsulta))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbRazon5)
                            .addComponent(cmbFinalidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbRazon11)
                            .addComponent(txtaCobrar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtaCobrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbRazon19))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbRazon14)
                            .addComponent(cmbPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbRazon15)
                            .addComponent(cmbForma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbRazon28)
                            .addComponent(cmbCodigoConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbConcepto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbRazon4))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbCausa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbRazon))
                        .addGap(14, 14, 14)
                        .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbRazon25)
                            .addComponent(cmbActoQuirurgico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbAmbito, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbRazon13))
                        .addGap(18, 18, 18)
                        .addComponent(btnGuardar1)))
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout pnlFormularioLayout = new javax.swing.GroupLayout(pnlFormulario);
        pnlFormulario.setLayout(pnlFormularioLayout);
        pnlFormularioLayout.setHorizontalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFormularioLayout.createSequentialGroup()
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFormularioLayout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbNit10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlFormularioLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlFormularioLayout.setVerticalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbNit10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlFormulario, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(pnlFormulario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbConceptoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbConceptoKeyReleased

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

    }//GEN-LAST:event_cmbCausaKeyReleased

    private void cmbPersonalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbPersonalKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbForma.requestFocus();
        }
    }//GEN-LAST:event_cmbPersonalKeyReleased

    private void txtaCobrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtaCobrarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtaCobrarActionPerformed

    private void txtaCobrarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtaCobrarKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cargarProducto(txtaCobrar.getText(), 1);
        } else {
            txtaCobrar1.setText("");
            txtValor.setText("");
        }
    }//GEN-LAST:event_txtaCobrarKeyReleased

    private void txtaCobrarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtaCobrarKeyTyped

    }//GEN-LAST:event_txtaCobrarKeyTyped

    private void cmbFormaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbFormaKeyReleased

    }//GEN-LAST:event_cmbFormaKeyReleased

    private void txtValorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtValorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValorActionPerformed

    private void txtValorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValorKeyReleased

    private void txtValorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValorKeyTyped

    private void rdProcedimientoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rdProcedimientoStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_rdProcedimientoStateChanged

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

    private void txtaCobrar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtaCobrar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtaCobrar1ActionPerformed

    private void txtaCobrar1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtaCobrar1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtaCobrar1KeyReleased

    private void txtaCobrar1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtaCobrar1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtaCobrar1KeyTyped

    private void cmbCodigoConsultaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbCodigoConsultaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbCodigoConsultaKeyReleased

    private void btnGuardar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar1ActionPerformed
        boolean registrar = false;

        Object[] campos = {};
        String faltantes = metodos.camposVacios(campos);
        Object[] campos2 = {txtaCobrar,};
        String faltantes2 = metodos.camposVacios(campos2);
        if (!faltantes.equals("")) {
            metodos.msgAdvertencia(null, "No puede continuar, faltan los siguientes campos: " + faltantes);
            return;
        } else if (!faltantes2.equals("")) {
            metodos.msgAdvertencia(null, "Faltan los siguientes campos: " + faltantes2);
        }
        if (metodos.msgPregunta(null, "¿Desea continuar?") == 0) {
            //
            try {

                String valor = "0";
                try {
                    ndProducto prod = instancias.getSql().getDatosProducto(txtaCobrar.getText(), "bdProductos");
                    System.out.println(valor);
                    valor = prod.getL1();
                } catch (Exception e) {

                }
                ndCamposOrdenPredeterminada nodo = instancias.getSql().getDatosCamposOrdenPredeterminada("1");

                if (nodo.getId() == null) {
                    registrar = true;
                }

                String tipo = "procedimiento";

                if (rdConsulta.isSelected()) {
                    tipo = "consulta";
                }

                //guardar en ordenServicio
                Object[] vector = {"1", tipo, cmbConcepto.getSelectedItem().toString(),
                    cmbFinalidad.getSelectedItem().toString(),
                    cmbCausa.getSelectedItem().toString(), txtaCobrar.getText(),
                    cmbPersonal.getSelectedItem().toString(), actoQuirurgicoDesc(cmbActoQuirurgico.getSelectedItem().toString()), cmbForma.getSelectedItem().toString(),
                    cmbAmbito.getSelectedItem(), cmbCodigoConsulta.getSelectedItem()};

                nodo = metodos.llenarCamposOrdenPredeterminada(vector);

                if (registrar) {
                    if (!instancias.getSql().agregarCamposOrdenPredeterminada(nodo)) {
                        metodos.msgError(null, "Hubo un problema al guardar la Orden Predeterminada");
                        return;
                    }

                } else {
                    if (!instancias.getSql().modificarCamposOrdenPredeterminada(nodo)) {
                        metodos.msgError(null, "Hubo un problema al modificar la Orden Predeterminada");
                        return;
                    }

                }
                metodos.msgExito(null, "Orden Predeterminada modificada con éxito");
                this.dispose();
            } catch (Exception e) {
                metodos.msgError(null, "Hubo un problema al guardar el OrdenServicio");
            }
        }
    }//GEN-LAST:event_btnGuardar1ActionPerformed

    private void btnGuardar1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnGuardar1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGuardar1KeyReleased

    private void jPanel3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseEntered
        lbCambiarTamano.setVisible(true);
    }//GEN-LAST:event_jPanel3MouseEntered

    private void lbCambiarTamanoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbCambiarTamanoMouseExited
        lbCambiarTamano.setVisible(false);
    }//GEN-LAST:event_lbCambiarTamanoMouseExited

    private void lbCambiarTamanoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbCambiarTamanoMouseEntered
        lbCambiarTamano.setVisible(true);
    }//GEN-LAST:event_lbCambiarTamanoMouseEntered

    private void lbCambiarTamanoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbCambiarTamanoMouseClicked
        boolean seVe = instancias.getMenu().getSeVeElMenu();

        if (seVe) {
            instancias.getMenu().ocultarMenu("");
        } else {
            instancias.getMenu().expandirMenu();
        }

        lbCambiarTamano.setVisible(false);
    }//GEN-LAST:event_lbCambiarTamanoMouseClicked

    public void setInstancia(Instancias instancia) {
        instancias = instancia;
        cargarDatos();

    }

    public void cargarDatos() {

        try {
            ndCamposOrdenPredeterminada nodo = instancias.getSql().getDatosCamposOrdenPredeterminada("1");

            if (nodo.getTipo().equalsIgnoreCase("consulta")) {
                rdConsulta.setSelected(true);
            } else {
                rdProcedimiento.setSelected(true);
            }
            cmbConcepto.setSelectedItem(nodo.getConcepto());
            cmbFinalidad.setSelectedItem(nodo.getFinalidad());
            cmbCausa.setSelectedItem(nodo.getCausaExterna());
            cmbPersonal.setSelectedItem(nodo.getPersonal());
            cmbActoQuirurgico.setSelectedItem(actoQuirurgicoCod(nodo.getActoQuirurgico()));
            cmbForma.setSelectedItem(nodo.getForma());
            cmbAmbito.setSelectedItem(nodo.getAmbito());
            cmbCodigoConsulta.setSelectedItem(nodo.getConsulta());
            txtaCobrar.setText(nodo.getServicio());
            cargarProducto(txtaCobrar.getText(), 0);
        } catch (Exception e) {
            System.out.println("No ha predeterminado campos de la orden");
        }

    }

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
            java.util.logging.Logger.getLogger(infPredeterminarCampos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(infPredeterminarCampos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(infPredeterminarCampos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(infPredeterminarCampos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                infPredeterminarCampos dialog = new infPredeterminarCampos(new javax.swing.JFrame(), true);
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

    public void cargarOrdenServicio(String Id) {
        ndOrdenServicio nodo = instancias.getSql().getDatosOrdenServicio(Id);
        cargarOrdenServicio(nodo);
    }

    public void cargarOrdenServicio(ndOrdenServicio nodo) {
        if (nodo.getId() != null) {
            cmbConcepto.setSelectedItem(nodo.getConcepto());
            txtaCobrar.setText(nodo.getAcobrar());
            cmbFinalidad.setSelectedItem(nodo.getFinalidad());
            cmbCausa.setSelectedItem(nodo.getCausa());
            cmbAmbito.setSelectedItem(nodo.getAmbito());
            cmbPersonal.setSelectedItem(nodo.getPersonal());
            cmbForma.setSelectedItem(nodo.getForma());

            try {
                cmbCodigoConsulta.setSelectedItem(nodo.getCodigoConsulta());
            } catch (Exception e) {
                System.out.println("codigo consulta: " + e);
            }
            cargarProducto(nodo.getAcobrar(), 0);

            //txtOrden.requestFocus();
            if (nodo.getTipo().equals("consulta")) {
                rdConsulta.setSelected(true);
            } else {
                rdProcedimiento.setSelected(true);
            }

            cmbActoQuirurgico.setSelectedItem(actoQuirurgicoCod(nodo.getActoQuirurgico()));
            btnGuardar1.setEnabled(false);
            return;
        }
//        txtOrden.requestFocus();
    }

    public void cargarProducto(String codigo, int sw) {

        ndProducto nodo = instancias.getSql().getDatosProducto(codigo, "bdProductos");

        if (nodo.getCodigo() != null) {
            txtValor.setText(big.setMoneda(big.getBigDecimal(nodo.getL1())));
            valor = txtValor.getText();
            txtaCobrar1.setText(nodo.getDescripcion());
            return;
        }
        if (sw == 1) {
            ventanaProductos(codigo);
        }
    }

    public void ventanaProductos(String codigo) {
        buscProductos buscar = new buscProductos(null, rootPaneCheckingEnabled, false, "", "productos1");
        buscar.setLocationRelativeTo(null);
        instancias.setBusProductos(buscar);
        instancias.setCampoActual(txtaCobrar);
        txtaCobrar.requestFocus();
        buscar.noEncontrado(codigo.replace("'", "//"));
        buscar.show();
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar1;
    private javax.swing.JComboBox cmbActoQuirurgico;
    private javax.swing.JComboBox cmbAmbito;
    private javax.swing.JComboBox cmbCausa;
    private javax.swing.JComboBox cmbCodigoConsulta;
    private javax.swing.JComboBox cmbConcepto;
    private javax.swing.JComboBox cmbFinalidad;
    private javax.swing.JComboBox cmbForma;
    private javax.swing.JComboBox cmbPersonal;
    private javax.swing.ButtonGroup grupoTipo;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lbCambiarTamano;
    private javax.swing.JLabel lbNit10;
    private javax.swing.JLabel lbRazon;
    private javax.swing.JLabel lbRazon11;
    private javax.swing.JLabel lbRazon13;
    private javax.swing.JLabel lbRazon14;
    private javax.swing.JLabel lbRazon15;
    private javax.swing.JLabel lbRazon19;
    private javax.swing.JLabel lbRazon23;
    private javax.swing.JLabel lbRazon25;
    private javax.swing.JLabel lbRazon28;
    private javax.swing.JLabel lbRazon4;
    private javax.swing.JLabel lbRazon5;
    private javax.swing.JPanel pnlFormulario;
    private javax.swing.JRadioButton rdConsulta;
    private javax.swing.JRadioButton rdProcedimiento;
    private javax.swing.JTextField txtValor;
    private javax.swing.JTextField txtaCobrar;
    private javax.swing.JTextField txtaCobrar1;
    // End of variables declaration//GEN-END:variables
}
