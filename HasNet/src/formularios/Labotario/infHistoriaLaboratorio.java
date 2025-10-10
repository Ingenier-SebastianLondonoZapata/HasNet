package formularios.Labotario;

import formularios.Medico.*;
import clases.Instancias;
import clases.Laboratorio.ndConsultaLaboratorio;
import clases.Medico.ndCamposOrdenPredeterminada;

import clases.Medico.ndEpsPrecargados;
import clases.Medico.ndHistoriaClinica;
import clases.Medico.ndOrdenServicio;

import Utilidades.BaseDatos.SQL;
import clases.big;
import clases.cambiarColorTabla;
import clases.metodosGenerales;
import Modelo.Terceros.ModeloContacto;
import formularios.Genericos.buscPlantillaDeTextos;
import formularios.terceros.buscClientes;
import java.awt.Color;

import java.awt.Dimension;
import java.awt.Event;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;

public class infHistoriaLaboratorio extends javax.swing.JInternalFrame {

    metodosGenerales metodos = new metodosGenerales();
    private Instancias instancias;

    private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension dimBarra = null;
    private String paciente;

    ndHistoriaClinica historia;
//    ndSistemas sistemas;
    String imagenes[];
    private String orden;
    int actual;
    private JTable tabla;
    SQL sql= new SQL();
    boolean facturarOrden = false;
    Object[][] productoFacturarOrden = new Object[27][2];
    public Path FROM2;
    boolean consultaCargada = false;

    public infHistoriaLaboratorio() {
        initComponents();
        instancias = Instancias.getInstancias();

        actualizarExamenes();

        tblEx1.setDefaultRenderer(Object.class, new cambiarColorTabla(17, 0));
        tblEx2.setDefaultRenderer(Object.class, new cambiarColorTabla(18, 0));

        desactivarExamenes();

        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
        dimBarra = Barra.getPreferredSize();
        Barra.setSize(0, 0);
        Barra.setPreferredSize(new Dimension(0, 0));
        setBorder(null);
        repaint();

        scrFormulario.getVerticalScrollBar().setUnitIncrement(18);
        pnlFormulario.registerKeyboardAction(accion("guardar"), "guardar", KeyStroke.getKeyStroke(KeyEvent.VK_G, Event.CTRL_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
    }

    private ActionListener accion(final String opc) {
        ActionListener a = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                switch (opc) {
//                    case "guardar":
//                        if ((!txtEvolucionSeg.getText().equals("") || !txtObservacionesSeg.getText().equals("")) && cmbFecha.getSelectedIndex() == 0) {
//                            btnGuardarSeguimientoActionPerformed(null);
//                        } else {
//                            btnGuardarConsultaActionPerformed(null);
//                        }
//                        break;
//                }
            }
        };
        return a;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
        buttonGroup1 = new javax.swing.ButtonGroup();
        grupoFumador = new javax.swing.ButtonGroup();
        grupoIngesta = new javax.swing.ButtonGroup();
        grupoAlergias = new javax.swing.ButtonGroup();
        grupoToxicos = new javax.swing.ButtonGroup();
        grupoMedicamentos = new javax.swing.ButtonGroup();
        grupoPatologicos = new javax.swing.ButtonGroup();
        grupoPsiquiatricos = new javax.swing.ButtonGroup();
        grupoQuirurgicos = new javax.swing.ButtonGroup();
        grupoPlanificacion = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        buttonGroup5 = new javax.swing.ButtonGroup();
        buttonGroup6 = new javax.swing.ButtonGroup();
        buttonGroup7 = new javax.swing.ButtonGroup();
        buttonGroup8 = new javax.swing.ButtonGroup();
        buttonGroup9 = new javax.swing.ButtonGroup();
        buttonGroup10 = new javax.swing.ButtonGroup();
        buttonGroup11 = new javax.swing.ButtonGroup();
        buttonGroup12 = new javax.swing.ButtonGroup();
        buttonGroup13 = new javax.swing.ButtonGroup();
        jPanel18 = new javax.swing.JPanel();
        buttonGroup14 = new javax.swing.ButtonGroup();
        buttonGroup15 = new javax.swing.ButtonGroup();
        scrFormulario = new javax.swing.JScrollPane();
        pnlFormulario = new javax.swing.JPanel();
        jPanel30 = new javax.swing.JPanel();
        lbEmail14 = new javax.swing.JLabel();
        jScrollPane15 = new javax.swing.JScrollPane();
        txtMotivo = new javax.swing.JTextArea();
        lbEmail29 = new javax.swing.JLabel();
        jScrollPane23 = new javax.swing.JScrollPane();
        txtEnfermedadActual = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        lbNit12 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        lbNit = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        txtNombreCompleto = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        txtEdad = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        txtGenero = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        txtFechaNacimiento = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        lbDireccion20 = new javax.swing.JLabel();
        lbDireccion25 = new javax.swing.JLabel();
        txtResponsable = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        lbDireccion40 = new javax.swing.JLabel();
        txtTelefonoResponsable = new javax.swing.JTextField();
        lbDireccion27 = new javax.swing.JLabel();
        cmbParentesco = new javax.swing.JComboBox();
        lbDireccion22 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jPanel15 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        lbEmail34 = new javax.swing.JLabel();
        cmbTipoDiagnostico = new javax.swing.JComboBox();
        lbEmail18 = new javax.swing.JLabel();
        btnBuscCie1 = new javax.swing.JButton();
        txtCie1 = new javax.swing.JTextField();
        txtDiagnostico1 = new javax.swing.JTextField();
        lbEmail19 = new javax.swing.JLabel();
        txtCie2 = new javax.swing.JTextField();
        btnBuscCie2 = new javax.swing.JButton();
        txtDiagnostico2 = new javax.swing.JTextField();
        txtDiagnostico3 = new javax.swing.JTextField();
        txtCie3 = new javax.swing.JTextField();
        btnBuscCie3 = new javax.swing.JButton();
        lbEmail26 = new javax.swing.JLabel();
        lbEmail27 = new javax.swing.JLabel();
        btnBuscCie4 = new javax.swing.JButton();
        txtCie4 = new javax.swing.JTextField();
        txtDiagnostico4 = new javax.swing.JTextField();
        jScrollPane37 = new javax.swing.JScrollPane();
        txtDiagnosticoEspecializado = new javax.swing.JTextArea();
        btnBuscPlantilla24 = new javax.swing.JButton();
        lbEmail30 = new javax.swing.JLabel();
        lbEmail33 = new javax.swing.JLabel();
        lbEmail20 = new javax.swing.JLabel();
        jScrollPane19 = new javax.swing.JScrollPane();
        txtTratamiento = new javax.swing.JTextArea();
        btnBuscPlantilla25 = new javax.swing.JButton();
        jScrollPane20 = new javax.swing.JScrollPane();
        txtRiesgosRecomendaciones = new javax.swing.JTextArea();
        lbEmail21 = new javax.swing.JLabel();
        btnAcidoUrico = new javax.swing.JButton();
        btnAntiestreptolisina = new javax.swing.JButton();
        btnCannabinoides = new javax.swing.JButton();
        btnCocaina = new javax.swing.JButton();
        btnCoprologico = new javax.swing.JButton();
        btnCoprograma = new javax.swing.JButton();
        btnColesterol = new javax.swing.JButton();
        btnSerologia = new javax.swing.JButton();
        btnCitoquimico = new javax.swing.JButton();
        btnDirecto = new javax.swing.JButton();
        btnGlicemia = new javax.swing.JButton();
        btnHemograma = new javax.swing.JButton();
        btnHemoclasificacion = new javax.swing.JButton();
        btnTiempos = new javax.swing.JButton();
        btnCreatinina = new javax.swing.JButton();
        btnFactor = new javax.swing.JButton();
        btnHemoglobina = new javax.swing.JButton();
        btnSangreOculta = new javax.swing.JButton();
        btnKoh = new javax.swing.JButton();
        btnPrueba = new javax.swing.JButton();
        btnProteina = new javax.swing.JButton();
        btnPerfil = new javax.swing.JButton();
        lbEmail31 = new javax.swing.JLabel();
        lbEmail28 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblEx1 = new javax.swing.JTable();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblEx2 = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel17 = new javax.swing.JPanel();
        btnGuardarConsulta = new javax.swing.JButton();
        btnReimprimirConsulta = new javax.swing.JButton();
        pnlMenu = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        lista = new javax.swing.JList();
        lbLista = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        lbNit2 = new javax.swing.JLabel();
        lbNit3 = new javax.swing.JLabel();

        jRadioButtonMenuItem1.setSelected(true);
        jRadioButtonMenuItem1.setText("jRadioButtonMenuItem1");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setBackground(new java.awt.Color(255, 255, 255));
        setTitle("Factura");

        pnlFormulario.setBackground(new java.awt.Color(255, 255, 255));
        pnlFormulario.setToolTipText("");

        jPanel30.setBackground(new java.awt.Color(255, 255, 255));
        jPanel30.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        lbEmail14.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbEmail14.setText("Motivo de la consulta:");

        txtMotivo.setColumns(20);
        txtMotivo.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtMotivo.setLineWrap(true);
        txtMotivo.setRows(2);
        txtMotivo.setName("Motivo"); // NOI18N
        txtMotivo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMotivoKeyReleased(evt);
            }
        });
        jScrollPane15.setViewportView(txtMotivo);

        lbEmail29.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbEmail29.setText("Enfermedad actual:");

        txtEnfermedadActual.setColumns(20);
        txtEnfermedadActual.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtEnfermedadActual.setLineWrap(true);
        txtEnfermedadActual.setRows(2);
        txtEnfermedadActual.setName("Motivo"); // NOI18N
        txtEnfermedadActual.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEnfermedadActualKeyReleased(evt);
            }
        });
        jScrollPane23.setViewportView(txtEnfermedadActual);

        jLabel5.setBackground(new java.awt.Color(102, 153, 0));
        jLabel5.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("MOTIVO DE LA CONSULTA");
        jLabel5.setOpaque(true);

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel30Layout.createSequentialGroup()
                        .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lbEmail29, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbEmail14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane23, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 890, Short.MAX_VALUE)
                            .addComponent(jScrollPane15, javax.swing.GroupLayout.Alignment.LEADING)))
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane15, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                    .addComponent(lbEmail14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane23, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                    .addComponent(lbEmail29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lbNit12.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        lbNit12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit12.setText("      HISTORIA DE LABORATORIO");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel27.setBackground(new java.awt.Color(255, 255, 255));
        jLabel27.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel27.setText("Nombre Completo:");
        jLabel27.setOpaque(true);

        lbNit.setBackground(new java.awt.Color(255, 255, 255));
        lbNit.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNit.setText("Identificación:");
        lbNit.setOpaque(true);
        lbNit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lbNitKeyReleased(evt);
            }
        });

        txtId.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtId.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdActionPerformed(evt);
            }
        });
        txtId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIdKeyReleased(evt);
            }
        });

        txtNombreCompleto.setEditable(false);
        txtNombreCompleto.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtNombreCompleto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreCompletoActionPerformed(evt);
            }
        });

        jLabel29.setBackground(new java.awt.Color(255, 255, 255));
        jLabel29.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel29.setText("Edad:");
        jLabel29.setOpaque(true);

        txtEdad.setEditable(false);
        txtEdad.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtEdad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEdadActionPerformed(evt);
            }
        });

        jLabel30.setBackground(new java.awt.Color(255, 255, 255));
        jLabel30.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel30.setText("Genero:");
        jLabel30.setOpaque(true);

        txtGenero.setEditable(false);
        txtGenero.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtGenero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGeneroActionPerformed(evt);
            }
        });

        jLabel28.setBackground(new java.awt.Color(255, 255, 255));
        jLabel28.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel28.setText("Fecha Nacimiento:");
        jLabel28.setOpaque(true);

        txtFechaNacimiento.setEditable(false);
        txtFechaNacimiento.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtFechaNacimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaNacimientoActionPerformed(evt);
            }
        });

        lbDireccion20.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        lbDireccion20.setText("Acompañante");

        lbDireccion25.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbDireccion25.setText("Identificación:");

        txtResponsable.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtResponsable.setName("FC"); // NOI18N
        txtResponsable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtResponsableActionPerformed(evt);
            }
        });
        txtResponsable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtResponsableKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtResponsableKeyTyped(evt);
            }
        });

        txtNombre.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtNombre.setName("FC"); // NOI18N
        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });

        lbDireccion40.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbDireccion40.setText("Télefono:");

        txtTelefonoResponsable.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtTelefonoResponsable.setName("FC"); // NOI18N
        txtTelefonoResponsable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelefonoResponsableActionPerformed(evt);
            }
        });
        txtTelefonoResponsable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTelefonoResponsableKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoResponsableKeyTyped(evt);
            }
        });

        lbDireccion27.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbDireccion27.setText("Parentesco:");

        cmbParentesco.setEditable(true);
        cmbParentesco.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        cmbParentesco.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Papá", "Mamá", "Cónyuge", "Yerno", "Nuera", "Madrastra", "Padrastro", "Tio(a)", "Hermano(a)", "Abuelo(a)", "Primo(a)", "Sobrino(a)", "Amigo(a)", "Hijo(a)", "Novio(a)", "Cuñado(a)", "Suegro(a)", "Otro, Describa cual." }));
        cmbParentesco.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbParentescoKeyReleased(evt);
            }
        });

        lbDireccion22.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        lbDireccion22.setText("PACIENTE");

        jLabel31.setBackground(new java.awt.Color(255, 255, 255));
        jLabel31.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel31.setText("Nombre Completo:");
        jLabel31.setOpaque(true);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator3)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                                    .addComponent(lbNit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(5, 5, 5)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(35, 35, 35)
                                        .addComponent(jLabel28)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtFechaNacimiento))
                                    .addComponent(txtNombreCompleto))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel29)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(23, 23, 23)
                                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(13, 13, 13))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbDireccion20)
                            .addComponent(lbDireccion22, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbDireccion25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtNombre)
                                .addGap(18, 18, 18)
                                .addComponent(lbDireccion40)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTelefonoResponsable, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addComponent(lbDireccion27)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbParentesco, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtResponsable, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbDireccion22)
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNit, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtGenero, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNombreCompleto)
                    .addComponent(txtEdad)
                    .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbDireccion20)
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lbDireccion25, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(txtResponsable))
                .addGap(3, 3, 3)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtTelefonoResponsable)
                                    .addComponent(lbDireccion40, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(cmbParentesco)
                                        .addComponent(lbDireccion27, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(2, 2, 2))))
                        .addGap(15, 15, 15))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel26.setBackground(new java.awt.Color(102, 153, 0));
        jLabel26.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("DIAGNOSTICO Y TRATAMIENTO");
        jLabel26.setOpaque(true);

        lbEmail34.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbEmail34.setText("Tipo diagnóstico:");

        cmbTipoDiagnostico.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        cmbTipoDiagnostico.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Impresión diagnóstica", "Confirmado nuevo", "Confirmado repetido" }));

        lbEmail18.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbEmail18.setText("Principal:");

        btnBuscCie1.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscCie1.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnBuscCie1.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscCie1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/listadoPequeño.png"))); // NOI18N
        btnBuscCie1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        btnBuscCie1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscCie1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscCie1.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnBuscCie1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscCie1ActionPerformed(evt);
            }
        });

        txtCie1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtCie1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCie1FocusLost(evt);
            }
        });
        txtCie1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCie1KeyReleased(evt);
            }
        });

        txtDiagnostico1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtDiagnostico1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDiagnostico1KeyReleased(evt);
            }
        });

        lbEmail19.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbEmail19.setText("Relacionado:");

        txtCie2.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtCie2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCie2FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCie2FocusLost(evt);
            }
        });
        txtCie2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCie2KeyReleased(evt);
            }
        });

        btnBuscCie2.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscCie2.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnBuscCie2.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscCie2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/listadoPequeño.png"))); // NOI18N
        btnBuscCie2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        btnBuscCie2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscCie2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscCie2.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnBuscCie2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscCie2ActionPerformed(evt);
            }
        });

        txtDiagnostico2.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtDiagnostico2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDiagnostico2KeyReleased(evt);
            }
        });

        txtDiagnostico3.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtDiagnostico3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDiagnostico3KeyReleased(evt);
            }
        });

        txtCie3.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtCie3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCie3FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCie3FocusLost(evt);
            }
        });
        txtCie3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCie3KeyReleased(evt);
            }
        });

        btnBuscCie3.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscCie3.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnBuscCie3.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscCie3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/listadoPequeño.png"))); // NOI18N
        btnBuscCie3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        btnBuscCie3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscCie3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscCie3.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnBuscCie3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscCie3ActionPerformed(evt);
            }
        });

        lbEmail26.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbEmail26.setText("Relacionado:");

        lbEmail27.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbEmail27.setText("Rel.(complicación):");

        btnBuscCie4.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscCie4.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnBuscCie4.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscCie4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/listadoPequeño.png"))); // NOI18N
        btnBuscCie4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        btnBuscCie4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscCie4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscCie4.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnBuscCie4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscCie4ActionPerformed(evt);
            }
        });

        txtCie4.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtCie4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCie4FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCie4FocusLost(evt);
            }
        });
        txtCie4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCie4KeyReleased(evt);
            }
        });

        txtDiagnostico4.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtDiagnostico4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDiagnostico4KeyReleased(evt);
            }
        });

        txtDiagnosticoEspecializado.setColumns(20);
        txtDiagnosticoEspecializado.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtDiagnosticoEspecializado.setLineWrap(true);
        txtDiagnosticoEspecializado.setRows(2);
        txtDiagnosticoEspecializado.setName("Tratamiento"); // NOI18N
        txtDiagnosticoEspecializado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDiagnosticoEspecializadoKeyReleased(evt);
            }
        });
        jScrollPane37.setViewportView(txtDiagnosticoEspecializado);

        btnBuscPlantilla24.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscPlantilla24.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnBuscPlantilla24.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscPlantilla24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/listadoPequeño.png"))); // NOI18N
        btnBuscPlantilla24.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        btnBuscPlantilla24.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscPlantilla24.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscPlantilla24.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnBuscPlantilla24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscPlantilla24ActionPerformed(evt);
            }
        });

        lbEmail30.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbEmail30.setText("Diagnostico");

        lbEmail33.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbEmail33.setText("especializado:");

        lbEmail20.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbEmail20.setText("Tratamiento:");

        txtTratamiento.setColumns(20);
        txtTratamiento.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtTratamiento.setLineWrap(true);
        txtTratamiento.setRows(3);
        txtTratamiento.setName("Tratamiento"); // NOI18N
        txtTratamiento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTratamientoKeyReleased(evt);
            }
        });
        jScrollPane19.setViewportView(txtTratamiento);

        btnBuscPlantilla25.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscPlantilla25.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnBuscPlantilla25.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscPlantilla25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/listadoPequeño.png"))); // NOI18N
        btnBuscPlantilla25.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        btnBuscPlantilla25.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscPlantilla25.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscPlantilla25.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnBuscPlantilla25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscPlantilla25ActionPerformed(evt);
            }
        });

        txtRiesgosRecomendaciones.setColumns(20);
        txtRiesgosRecomendaciones.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtRiesgosRecomendaciones.setLineWrap(true);
        txtRiesgosRecomendaciones.setRows(2);
        txtRiesgosRecomendaciones.setName("Recomendaciones"); // NOI18N
        txtRiesgosRecomendaciones.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtRiesgosRecomendacionesKeyReleased(evt);
            }
        });
        jScrollPane20.setViewportView(txtRiesgosRecomendaciones);

        lbEmail21.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbEmail21.setText("Riesgos:");

        btnAcidoUrico.setBackground(new java.awt.Color(255, 255, 255));
        btnAcidoUrico.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnAcidoUrico.setText("ACIDO URICO (AU)");
        btnAcidoUrico.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAcidoUrico.setPreferredSize(new java.awt.Dimension(211, 29));
        btnAcidoUrico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAcidoUricoActionPerformed(evt);
            }
        });

        btnAntiestreptolisina.setBackground(new java.awt.Color(255, 255, 255));
        btnAntiestreptolisina.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnAntiestreptolisina.setText("ANTIESTREPTOLISINA");
        btnAntiestreptolisina.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAntiestreptolisina.setPreferredSize(new java.awt.Dimension(211, 29));
        btnAntiestreptolisina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAntiestreptolisinaActionPerformed(evt);
            }
        });

        btnCannabinoides.setBackground(new java.awt.Color(255, 255, 255));
        btnCannabinoides.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnCannabinoides.setText("CANNABINOIDES");
        btnCannabinoides.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCannabinoides.setPreferredSize(new java.awt.Dimension(211, 29));
        btnCannabinoides.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCannabinoidesActionPerformed(evt);
            }
        });

        btnCocaina.setBackground(new java.awt.Color(255, 255, 255));
        btnCocaina.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnCocaina.setText("COCAINA");
        btnCocaina.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCocaina.setPreferredSize(new java.awt.Dimension(211, 29));
        btnCocaina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCocainaActionPerformed(evt);
            }
        });

        btnCoprologico.setBackground(new java.awt.Color(255, 255, 255));
        btnCoprologico.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnCoprologico.setText("COPROLOGICO");
        btnCoprologico.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCoprologico.setPreferredSize(new java.awt.Dimension(211, 29));
        btnCoprologico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCoprologicoActionPerformed(evt);
            }
        });

        btnCoprograma.setBackground(new java.awt.Color(255, 255, 255));
        btnCoprograma.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnCoprograma.setText("COPROGRAMA");
        btnCoprograma.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCoprograma.setPreferredSize(new java.awt.Dimension(211, 29));
        btnCoprograma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCoprogramaActionPerformed(evt);
            }
        });

        btnColesterol.setBackground(new java.awt.Color(255, 255, 255));
        btnColesterol.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnColesterol.setText("COLESTEROL / TRIGLICERIDOS");
        btnColesterol.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnColesterol.setPreferredSize(new java.awt.Dimension(211, 29));
        btnColesterol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnColesterolActionPerformed(evt);
            }
        });

        btnSerologia.setBackground(new java.awt.Color(255, 255, 255));
        btnSerologia.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnSerologia.setText("SEROLOGIA");
        btnSerologia.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSerologia.setPreferredSize(new java.awt.Dimension(211, 29));
        btnSerologia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSerologiaActionPerformed(evt);
            }
        });

        btnCitoquimico.setBackground(new java.awt.Color(255, 255, 255));
        btnCitoquimico.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnCitoquimico.setText("CITOQUIMICO DE ORINA");
        btnCitoquimico.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCitoquimico.setPreferredSize(new java.awt.Dimension(211, 29));
        btnCitoquimico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCitoquimicoActionPerformed(evt);
            }
        });

        btnDirecto.setBackground(new java.awt.Color(255, 255, 255));
        btnDirecto.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnDirecto.setText("DIRECTO Y GRAM");
        btnDirecto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDirecto.setPreferredSize(new java.awt.Dimension(211, 29));
        btnDirecto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDirectoActionPerformed(evt);
            }
        });

        btnGlicemia.setBackground(new java.awt.Color(255, 255, 255));
        btnGlicemia.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnGlicemia.setText("GLICEMIA EN AYUNAS");
        btnGlicemia.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGlicemia.setPreferredSize(new java.awt.Dimension(211, 29));
        btnGlicemia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGlicemiaActionPerformed(evt);
            }
        });

        btnHemograma.setBackground(new java.awt.Color(255, 255, 255));
        btnHemograma.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnHemograma.setText("HEMOGRAMA");
        btnHemograma.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHemograma.setPreferredSize(new java.awt.Dimension(211, 29));
        btnHemograma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHemogramaActionPerformed(evt);
            }
        });

        btnHemoclasificacion.setBackground(new java.awt.Color(255, 255, 255));
        btnHemoclasificacion.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnHemoclasificacion.setText("HEMOCLASIFICACIÓN");
        btnHemoclasificacion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHemoclasificacion.setPreferredSize(new java.awt.Dimension(211, 29));
        btnHemoclasificacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHemoclasificacionActionPerformed(evt);
            }
        });

        btnTiempos.setBackground(new java.awt.Color(255, 255, 255));
        btnTiempos.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnTiempos.setText("TP / TPT");
        btnTiempos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTiempos.setPreferredSize(new java.awt.Dimension(211, 29));
        btnTiempos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTiemposActionPerformed(evt);
            }
        });

        btnCreatinina.setBackground(new java.awt.Color(255, 255, 255));
        btnCreatinina.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnCreatinina.setText("CREATININA / UREA / BUN");
        btnCreatinina.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCreatinina.setPreferredSize(new java.awt.Dimension(211, 29));
        btnCreatinina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreatininaActionPerformed(evt);
            }
        });

        btnFactor.setBackground(new java.awt.Color(255, 255, 255));
        btnFactor.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnFactor.setText(" FACTOR REUMATOIDE (RF)");
        btnFactor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnFactor.setPreferredSize(new java.awt.Dimension(211, 29));
        btnFactor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFactorActionPerformed(evt);
            }
        });

        btnHemoglobina.setBackground(new java.awt.Color(255, 255, 255));
        btnHemoglobina.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnHemoglobina.setText("HEMOGLOBINA GLICOSILADA");
        btnHemoglobina.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHemoglobina.setPreferredSize(new java.awt.Dimension(211, 29));
        btnHemoglobina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHemoglobinaActionPerformed(evt);
            }
        });

        btnSangreOculta.setBackground(new java.awt.Color(255, 255, 255));
        btnSangreOculta.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnSangreOculta.setText("SANGRE EN MATERIA FECAL");
        btnSangreOculta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSangreOculta.setPreferredSize(new java.awt.Dimension(211, 29));
        btnSangreOculta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSangreOcultaActionPerformed(evt);
            }
        });

        btnKoh.setBackground(new java.awt.Color(255, 255, 255));
        btnKoh.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnKoh.setText("KOH");
        btnKoh.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnKoh.setPreferredSize(new java.awt.Dimension(211, 29));
        btnKoh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKohActionPerformed(evt);
            }
        });

        btnPrueba.setBackground(new java.awt.Color(255, 255, 255));
        btnPrueba.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnPrueba.setText("PRUEBA DE EMBARAZO");
        btnPrueba.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPrueba.setPreferredSize(new java.awt.Dimension(211, 29));
        btnPrueba.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPruebaActionPerformed(evt);
            }
        });

        btnProteina.setBackground(new java.awt.Color(255, 255, 255));
        btnProteina.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnProteina.setText("PROTEINA C (PCR)");
        btnProteina.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnProteina.setPreferredSize(new java.awt.Dimension(211, 29));
        btnProteina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProteinaActionPerformed(evt);
            }
        });

        btnPerfil.setBackground(new java.awt.Color(255, 255, 255));
        btnPerfil.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnPerfil.setText("PERFIL LIPIDICO");
        btnPerfil.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPerfil.setPreferredSize(new java.awt.Dimension(211, 29));
        btnPerfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPerfilActionPerformed(evt);
            }
        });

        lbEmail31.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        lbEmail31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbEmail31.setText("DESARROLLO DE LOS PROCEDIMIENTOS");

        lbEmail28.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        lbEmail28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbEmail28.setText("PROCEDIMIENTOS SUGERIDOS");

        tblEx1.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        tblEx1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, "ACIDO URICO (AU)",  new Boolean(false), null},
                {null, "ANTIESTREPTOLISINA (ASO)", null, null},
                {null, "CANNABINOIDES (MARIHUANA)", null, null},
                {null, "DETERMINACION DE COCAINA", null, null},
                {null, "COPROLOGICO", null, null},
                {null, "COPROGRAMA", null, null},
                {null, "COLESTEROL TOTAL", null, null},
                {null, "TRIGLICERIDOS", null, null},
                {null, "CITOQUIMICO DE ORINA", null, null},
                {null, "CREATININA", null, null},
                {null, "UREA", null, null},
                {null, "BUN", null, null},
                {null, "GLICEMIA EN AYUNAS", null, null},
                {null, "HEMOGLOBINA GLICOSILADA A1c (HbA1c)", null, null}
            },
            new String [] {
                "Codigo", "Descripción", "", "Precio"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblEx1.setRowHeight(26);
        tblEx1.getTableHeader().setReorderingAllowed(false);
        tblEx1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEx1MouseClicked(evt);
            }
        });
        tblEx1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblEx1KeyReleased(evt);
            }
        });
        jScrollPane6.setViewportView(tblEx1);
        if (tblEx1.getColumnModel().getColumnCount() > 0) {
            tblEx1.getColumnModel().getColumn(0).setMinWidth(0);
            tblEx1.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblEx1.getColumnModel().getColumn(0).setMaxWidth(0);
            tblEx1.getColumnModel().getColumn(2).setMinWidth(0);
            tblEx1.getColumnModel().getColumn(2).setPreferredWidth(0);
            tblEx1.getColumnModel().getColumn(2).setMaxWidth(0);
            tblEx1.getColumnModel().getColumn(3).setMinWidth(0);
            tblEx1.getColumnModel().getColumn(3).setPreferredWidth(0);
            tblEx1.getColumnModel().getColumn(3).setMaxWidth(0);
        }

        tblEx2.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        tblEx2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, "HEMOCLASIFICACIÓN", null, null},
                {null, "FACTOR REUMATOIDE (RF)", null, null},
                {null, "PROTEINA C REACTIVA (PCR)", null, null},
                {null, "HEMOGRAMA COMPLETO TIPO IV METODO SEMIAUTOMATICO", null, null},
                {null, "SEROLOGIA (V.D.R.L. - USR)", null, null},
                {null, "TIEMPO DE PROTROMBINA (TP)", null, null},
                {null, "TIEMPO PARCIAL DE TROMBOPLASTINA (TPT)", null, null},
                {null, "SANGRE OCULTA EN MATERIA FECAL (HEMOGLOBINA HUMANA ESPECIFICA)", null, null},
                {null, "KOH", null, null},
                {null, "PERFIL LIPIDICO", null, null},
                {null, "PRUEBA INMUNOLOGICA DE EMBARAZO", null, null},
                {null, "DIRECTO", null, null},
                {null, "GRAM", null, null}
            },
            new String [] {
                "Codigo", "Descripción", "", "Precio"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblEx2.setRowHeight(26);
        tblEx2.getTableHeader().setReorderingAllowed(false);
        tblEx2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEx2MouseClicked(evt);
            }
        });
        tblEx2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblEx2KeyReleased(evt);
            }
        });
        jScrollPane7.setViewportView(tblEx2);
        if (tblEx2.getColumnModel().getColumnCount() > 0) {
            tblEx2.getColumnModel().getColumn(0).setMinWidth(0);
            tblEx2.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblEx2.getColumnModel().getColumn(0).setMaxWidth(0);
            tblEx2.getColumnModel().getColumn(2).setMinWidth(0);
            tblEx2.getColumnModel().getColumn(2).setPreferredWidth(0);
            tblEx2.getColumnModel().getColumn(2).setMaxWidth(0);
            tblEx2.getColumnModel().getColumn(3).setMinWidth(0);
            tblEx2.getColumnModel().getColumn(3).setPreferredWidth(0);
            tblEx2.getColumnModel().getColumn(3).setMaxWidth(0);
        }

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(lbEmail34, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addComponent(cmbTipoDiagnostico, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(709, 713, Short.MAX_VALUE))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 527, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane7))
                            .addComponent(lbEmail28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel15Layout.createSequentialGroup()
                                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(lbEmail19, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lbEmail18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lbEmail26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lbEmail27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lbEmail30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lbEmail33, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                    .addGroup(jPanel15Layout.createSequentialGroup()
                                        .addComponent(lbEmail21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(4, 4, 4)))
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(btnBuscCie2, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                                        .addComponent(btnBuscCie1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnBuscCie3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnBuscCie4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnBuscPlantilla24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(btnBuscPlantilla25, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel15Layout.createSequentialGroup()
                                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtCie3, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtCie2, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtCie1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtCie4, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(txtDiagnostico4)
                                            .addComponent(txtDiagnostico3)
                                            .addComponent(txtDiagnostico2)
                                            .addComponent(txtDiagnostico1)))
                                    .addComponent(jScrollPane20)
                                    .addComponent(jScrollPane37, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addComponent(lbEmail20, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane19))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(btnAcidoUrico, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnCoprograma, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnHemograma, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnSerologia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnKoh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel15Layout.createSequentialGroup()
                                        .addComponent(btnAntiestreptolisina, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCannabinoides, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCocaina, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                                    .addGroup(jPanel15Layout.createSequentialGroup()
                                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(btnFactor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnGlicemia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnColesterol, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnCitoquimico, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnCreatinina, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnHemoglobina, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                                        .addComponent(btnProteina, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnSangreOculta, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnPrueba, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                                    .addComponent(btnTiempos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnDirecto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnCoprologico, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnHemoclasificacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addContainerGap())))
            .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel15Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lbEmail31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbEmail34)
                    .addComponent(cmbTipoDiagnostico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtDiagnostico1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(txtCie1)
                    .addComponent(lbEmail18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBuscCie1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lbEmail19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCie2, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(btnBuscCie2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtDiagnostico2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnBuscCie3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCie3, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(txtDiagnostico3)
                    .addComponent(lbEmail26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtCie4, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(lbEmail27, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBuscCie4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtDiagnostico4))
                .addGap(15, 15, 15)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel15Layout.createSequentialGroup()
                        .addComponent(lbEmail30, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(lbEmail33)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(btnBuscPlantilla24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane37, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbEmail20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane20, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                    .addComponent(btnBuscPlantilla25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbEmail21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(lbEmail28, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAcidoUrico, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAntiestreptolisina, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCannabinoides, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCocaina, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCoprologico, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDirecto, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCitoquimico, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnColesterol, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCoprograma, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHemograma, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCreatinina, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGlicemia, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHemoclasificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTiempos, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHemoglobina, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFactor, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSerologia, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPrueba, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnKoh, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnProteina, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSangreOculta, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
            .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                    .addContainerGap(478, Short.MAX_VALUE)
                    .addComponent(lbEmail31, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(382, 382, 382)))
        );

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        btnGuardarConsulta.setBackground(new java.awt.Color(0, 153, 153));
        btnGuardarConsulta.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnGuardarConsulta.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardarConsulta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
        btnGuardarConsulta.setText("GUARDAR");
        btnGuardarConsulta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardarConsulta.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnGuardarConsulta.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnGuardarConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarConsultaActionPerformed(evt);
            }
        });

        btnReimprimirConsulta.setBackground(new java.awt.Color(0, 153, 153));
        btnReimprimirConsulta.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnReimprimirConsulta.setForeground(new java.awt.Color(255, 255, 255));
        btnReimprimirConsulta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/imprimir.png"))); // NOI18N
        btnReimprimirConsulta.setText("IMPRIMIR");
        btnReimprimirConsulta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReimprimirConsulta.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnReimprimirConsulta.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnReimprimirConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReimprimirConsultaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnReimprimirConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardarConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(411, 411, 411))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnReimprimirConsulta)
                    .addComponent(btnGuardarConsulta))
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlFormularioLayout = new javax.swing.GroupLayout(pnlFormulario);
        pnlFormulario.setLayout(pnlFormularioLayout);
        pnlFormularioLayout.setHorizontalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel17, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator6, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel30, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbNit12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        pnlFormularioLayout.setVerticalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbNit12, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        scrFormulario.setViewportView(pnlFormulario);

        pnlMenu.setBackground(new java.awt.Color(255, 255, 255));

        lista.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lista.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Hoy" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        lista.setToolTipText("");
        lista.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listaMouseClicked(evt);
            }
        });
        lista.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listaValueChanged(evt);
            }
        });
        jScrollPane8.setViewportView(lista);

        lbLista.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbLista.setText("Consultas:");

        lbNit2.setBackground(new java.awt.Color(255, 255, 255));
        lbNit2.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        lbNit2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit2.setText("IR AL INICIO");
        lbNit2.setOpaque(true);
        lbNit2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbNit2MouseClicked(evt);
            }
        });

        lbNit3.setBackground(new java.awt.Color(255, 255, 255));
        lbNit3.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        lbNit3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit3.setText("IR AL FINAL");
        lbNit3.setOpaque(true);
        lbNit3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbNit3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlMenuLayout = new javax.swing.GroupLayout(pnlMenu);
        pnlMenu.setLayout(pnlMenuLayout);
        pnlMenuLayout.setHorizontalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMenuLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbNit2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbLista, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                    .addComponent(jScrollPane8)
                    .addComponent(jSeparator9)
                    .addComponent(lbNit3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlMenuLayout.setVerticalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMenuLayout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addComponent(lbLista)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbNit2)
                .addGap(3, 3, 3)
                .addComponent(lbNit3)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrFormulario, javax.swing.GroupLayout.DEFAULT_SIZE, 1143, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrFormulario, javax.swing.GroupLayout.DEFAULT_SIZE, 740, Short.MAX_VALUE)
                    .addComponent(pnlMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
//    private String datosExamenes(JTable tabla) {
//        String aux = "";
//        for (int i = 0; i < tabla.getRowCount(); i++) {
//            aux += tabla.getValueAt(i, 0) + "&";
//        }
//        return aux;
//    }
//
//    private void cargarDatosExamen(JTable tabla, String dato) {
//        String aux[] = dato.split("&");
//        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
//        for (int i = 0; i < aux.length; i++) {
//            modelo.addRow(new String[]{aux[i]});
//        }
//    }

    private void btnGuardarConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarConsultaActionPerformed
        if (cmbTipoDiagnostico.getSelectedIndex() == 0) {
            metodos.msgError(this, "Debe seleccionar un tipo de diagnostico");
            return;
        }

        if (metodos.msgPregunta(this, "¿Desea continuar?. No podra realizar cambios posteriormente") == 0) {

            int i = 0;

            String consecutivo = "";

            if (lista.getSelectedIndex() == 0) {
                guardarOrden();

                String consulta = instancias.getSql().getNumConsecutivo("CONSULTALAB")[0].toString();

//                String historia = "";
//                if (instancias.getSql().getDatosHistoriaClinica(txtId.getText()).getId() == null) {
//                    historia = "";
//                } else {
//                    historia = instancias.getSql().getDatosHistoriaClinica(txtId.getText()).getId();
//                }
                consecutivo = consulta;
                Object[] vector = {consulta, txtId.getText(), txtResponsable.getText(), txtNombre.getText(), cmbParentesco.getSelectedItem(), txtMotivo.getText(),
                    txtEnfermedadActual.getText(), txtCie1.getText(), txtCie2.getText(), txtCie3.getText(), txtCie4.getText(), txtTratamiento.getText(), txtRiesgosRecomendaciones.getText(),
                    instancias.getUsuario(), metodosGenerales.fecha(), orden, txtTelefonoResponsable.getText(), txtDiagnosticoEspecializado.getText(), cmbTipoDiagnostico.getSelectedItem()};

                // LLENAR CUPS
                for (int j = 0; j < tblEx1.getRowCount(); j++) {
                    try {
                        if ((Boolean) tblEx1.getValueAt(j, 2)) {
                            instancias.getSql().agregarDetalleOrdenServicioLaboratorio(consulta, tblEx1.getValueAt(j, 0).toString(),
                                    tblEx1.getValueAt(j, 1).toString(), big.getMoneda(tblEx1.getValueAt(j, 3).toString()));
                        }
                    } catch (Exception e) {
                    }
                }

                for (int j = 0; j < tblEx2.getRowCount(); j++) {
                    try {
                        if ((Boolean) tblEx2.getValueAt(j, 2)) {
                            instancias.getSql().agregarDetalleOrdenServicioLaboratorio(consulta, tblEx2.getValueAt(j, 0).toString(),
                                    tblEx2.getValueAt(j, 1).toString(), big.getMoneda(tblEx2.getValueAt(j, 3).toString()));
                        }
                    } catch (Exception e) {
                    }
                }

                ndConsultaLaboratorio nodo = metodos.llenarConsultaLaboratorio(vector); //txtExtremidades
                ndOrdenServicio nodo2 = instancias.getSql().getDatosOrdenServicioLaboratorio(orden);
                nodo2.setCie(txtCie1.getText());
                nodo2.setCup(consulta);

                if (!instancias.getSql().modificarOrdenServicioLaboratorio(nodo2)) {
                    metodos.msgError(this, "Hubo un problema al modificar la orden");
                    return;
                }

                if (!instancias.getSql().agregarConsultaLaboratorio(nodo)) {
                    metodos.msgError(this, "Hubo un problema al guardar la Consulta");
                    return;
                }
                if (!instancias.getSql().aumentarConsecutivo("CONSULTALAB", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("CONSULTALAB")[0]) + 1)) {
                    metodos.msgError(this, "Hubo un problema al guardar en el consecutivo de la consulta");
                }

                i++;
            }

            if (facturarOrden) {
                facturar(productoFacturarOrden);
            }

            if (i > 0) {
                metodos.msgExito(this, "Historia guardada con exito");
                consultaEditable(true);
                setPaciente(txtId.getText());
                orden = null;
                lista.setSelectedIndex(1);
            }

            facturarOrden = false;
            productoFacturarOrden = new Object[27][1];
        }
    }//GEN-LAST:event_btnGuardarConsultaActionPerformed

    private void txtRiesgosRecomendacionesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRiesgosRecomendacionesKeyReleased

    }//GEN-LAST:event_txtRiesgosRecomendacionesKeyReleased

    private void txtTratamientoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTratamientoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtRiesgosRecomendaciones.requestFocus();
        }
    }//GEN-LAST:event_txtTratamientoKeyReleased

    private void txtMotivoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMotivoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtEnfermedadActual.requestFocus();
        }
    }//GEN-LAST:event_txtMotivoKeyReleased
    public void setCie(JTextField campo, String codigo) {
        try {
            if (campo == txtDiagnostico1) {
                if (validarCie(codigo)) {
                    txtCie1.setText(codigo);
                    txtCie2.requestFocus();
                    txtDiagnostico1.setText(instancias.getSql().getNombreCie(codigo));
                }
            }
            if (campo == txtDiagnostico2) {
                if (validarCie(codigo)) {
                    txtCie2.setText(codigo);
                    txtCie3.requestFocus();
                    txtDiagnostico2.setText(instancias.getSql().getNombreCie(codigo));
                }
            }
            if (campo == txtDiagnostico3) {
                if (validarCie(codigo)) {
                    txtCie3.setText(codigo);
                    txtCie4.requestFocus();
                    txtDiagnostico3.setText(instancias.getSql().getNombreCie(codigo));
                }
            }
            if (campo == txtDiagnostico4) {
                if (validarCie(codigo)) {
                    txtCie4.setText(codigo);
                    txtRiesgosRecomendaciones.requestFocus();
                    txtDiagnostico4.setText(instancias.getSql().getNombreCie(codigo));
                }
            }
        } catch (Exception e) {
            campo.setText("");
            ventanaCies(codigo, campo);
        }
    }

    public void desactivarExamenes() {
        btnAcidoUrico.setEnabled(false);
        btnAntiestreptolisina.setEnabled(false);
        btnCannabinoides.setEnabled(false);
        btnCocaina.setEnabled(false);
        btnCoprologico.setEnabled(false);
        btnCoprograma.setEnabled(false);
        btnColesterol.setEnabled(false);
        btnCitoquimico.setEnabled(false);
        btnCreatinina.setEnabled(false);
        btnGlicemia.setEnabled(false);
        btnHemoglobina.setEnabled(false);
        btnHemoclasificacion.setEnabled(false);
        btnFactor.setEnabled(false);
        btnProteina.setEnabled(false);
        btnHemograma.setEnabled(false);
        btnSerologia.setEnabled(false);
        btnTiempos.setEnabled(false);
        btnSangreOculta.setEnabled(false);
        btnKoh.setEnabled(false);
        btnPerfil.setEnabled(false);
        btnPrueba.setEnabled(false);
        btnDirecto.setEnabled(false);

        btnAcidoUrico.setBackground(new Color(255, 255, 255));
        btnAntiestreptolisina.setBackground(new Color(255, 255, 255));
        btnCannabinoides.setBackground(new Color(255, 255, 255));
        btnCocaina.setBackground(new Color(255, 255, 255));
        btnCoprologico.setBackground(new Color(255, 255, 255));
        btnCoprograma.setBackground(new Color(255, 255, 255));
        btnColesterol.setBackground(new Color(255, 255, 255));
        btnCitoquimico.setBackground(new Color(255, 255, 255));
        btnCreatinina.setBackground(new Color(255, 255, 255));
        btnGlicemia.setBackground(new Color(255, 255, 255));
        btnHemoglobina.setBackground(new Color(255, 255, 255));
        btnHemoclasificacion.setBackground(new Color(255, 255, 255));
        btnFactor.setBackground(new Color(255, 255, 255));
        btnProteina.setBackground(new Color(255, 255, 255));
        btnHemograma.setBackground(new Color(255, 255, 255));
        btnSerologia.setBackground(new Color(255, 255, 255));
        btnTiempos.setBackground(new Color(255, 255, 255));
        btnSangreOculta.setBackground(new Color(255, 255, 255));
        btnKoh.setBackground(new Color(255, 255, 255));
        btnPerfil.setBackground(new Color(255, 255, 255));
        btnPrueba.setBackground(new Color(255, 255, 255));
        btnDirecto.setBackground(new Color(255, 255, 255));
    }

    public boolean validarCie(String codigo) {

        Object[][] datosCie = sql.getDatosCie(codigo);
        String genero = datosCie[0][2].toString();
        String edadMin = datosCie[0][3].toString();
        String edadMax = datosCie[0][4].toString();
        boolean cumpleMinimo = true;
        boolean cumpleMaximo = true;

        System.out.println("Genero Cie " + genero);
        if (genero.equals("H") && !(txtGenero.getText().equals("Masculino") || txtGenero.getText().equals("masculino") || txtGenero.getText().equals("MASCULINO"))) {
            metodos.msgError(this, "Este Cie es de genero masculino");
            return false;
        } else if (genero.equals("M") && !(txtGenero.getText().equals("Femenino") || txtGenero.getText().equals("femenino") || txtGenero.getText().equals("FEMENINO"))) {
            metodos.msgError(this, "Este Cie es de genero femenino");
            return false;
        }

        System.out.println("edad MIN Cie " + edadMin);
        System.out.println("edad MAX Cie " + edadMax);

        if (!edadMin.equals("0")) {
            System.out.println("Entro 1 ");
            if (!edadMin.equals("000")) {
                int undMedidaCie = Integer.parseInt(edadMin.substring(0, 1));
                int medidaCie = Integer.parseInt(edadMin.substring(1));
                System.out.println("Entro 2 Und " + undMedidaCie);
                System.out.println("Entro 2 Cie " + medidaCie);
                String edadPaciente = txtEdad.getText();

                int medidaEdad = 0;
                int edad = Integer.parseInt(edadPaciente.split("-")[0]);

                if (edadPaciente.contains("Días")) {
                    medidaEdad = 2;
                } else if (edadPaciente.contains("Meses")) {
                    medidaEdad = 3;
                } else if (edadPaciente.contains("Años")) {
                    medidaEdad = 4;
                }
                System.out.println("Entro 3 " + medidaEdad);

                if (undMedidaCie == 2 && medidaEdad >= 2) {
                    System.out.println("Entro 4 ");
                    if (medidaEdad == 2) {
                        System.out.println("Entro 4.1 ");
                        cumpleMinimo = edad >= medidaCie;
                    } else {
                        System.out.println("Entro 4.2 ");
                        cumpleMinimo = true;
                    }
                } else if (undMedidaCie == 3 && medidaEdad >= 3) {
                    System.out.println("Entro 5 ");
                    if (medidaEdad == 3) {
                        System.out.println("Entro 5.1 ");
                        cumpleMinimo = edad >= medidaCie;
                    } else {
                        System.out.println("Entro 5.2 ");
                        cumpleMinimo = true;
                    }
                } else if (undMedidaCie == 4 && medidaEdad >= 4) {
                    System.out.println("Entro 6 ");
                    if (medidaEdad == 4) {
                        System.out.println("Entro 6.1 ");
                        cumpleMinimo = edad >= medidaCie;
                    } else {
                        System.out.println("Entro 6.2 ");
                        cumpleMinimo = true;
                    }
                } else {
                    cumpleMinimo = false;
                }
            }

            if (!edadMax.equals("599")) {
                int undMedidaCie = Integer.parseInt(edadMax.substring(0, 1));
                int medidaCie = Integer.parseInt(edadMax.substring(1));
                System.out.println("Entro 7 Und " + undMedidaCie);
                System.out.println("Entro 7 Cie " + medidaCie);

                String edadPaciente = txtEdad.getText();
                int medidaEdad = 0;
                int edad = Integer.parseInt(edadPaciente.split("-")[0]);

                if (edadPaciente.contains("Días")) {
                    medidaEdad = 2;
                } else if (edadPaciente.contains("Meses")) {
                    medidaEdad = 3;
                } else if (edadPaciente.contains("Años")) {
                    medidaEdad = 4;
                }
                System.out.println("Entro 8 " + medidaEdad);
                if (undMedidaCie == 2 && medidaEdad >= 2) {
                    System.out.println("Entro 9 ");
                    if (medidaEdad == 2) {
                        System.out.println("Entro 9.1 ");
                        cumpleMaximo = edad >= medidaCie;
                    } else {
                        System.out.println("Entro 9.2 ");
                        cumpleMaximo = true;
                    }
                } else if (undMedidaCie == 3 && medidaEdad >= 3) {
                    System.out.println("Entro 10 ");
                    if (medidaEdad == 3) {
                        System.out.println("Entro 10.1 ");
                        cumpleMaximo = edad >= medidaCie;
                    } else {
                        System.out.println("Entro 10.2 ");
                        cumpleMaximo = true;
                    }
                } else if (undMedidaCie == 4 && medidaEdad >= 4) {
                    System.out.println("Entro 11 ");
                    if (medidaEdad == 4) {
                        System.out.println("Entro 11.1 ");
                        cumpleMaximo = edad >= medidaCie;
                    } else {
                        System.out.println("Entro 11.2 ");
                        cumpleMaximo = true;
                    }
                } else {
                    cumpleMaximo = false;
                }
            }
        }

        if (cumpleMaximo && cumpleMinimo) {
            System.out.println("Si Cumple");
            return true;
        } else {
            if (!cumpleMaximo) {
                metodos.msgError(this, "Sobrepasa la edad maxima permitida.");
                return false;
            } else {
                metodos.msgError(this, "No cumple con la edad minima requerida.");
                return false;
            }
        }

    }
    private void txtCie1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCie1KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                setCie(txtDiagnostico1, txtCie1.getText());
            } catch (Exception e) {
                txtDiagnostico1.setText("");
            }
        } else {
            txtDiagnostico1.setText("");
        }

    }//GEN-LAST:event_txtCie1KeyReleased

    private void txtDiagnostico1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiagnostico1KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            ventanaCies(txtDiagnostico1.getText(), txtDiagnostico1);
        } else {
            txtCie1.setText("");
        }
    }//GEN-LAST:event_txtDiagnostico1KeyReleased

    private void txtDiagnostico2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiagnostico2KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            ventanaCies(txtDiagnostico2.getText(), txtDiagnostico2);
        } else {
            txtCie2.setText("");
        }
    }//GEN-LAST:event_txtDiagnostico2KeyReleased

    private void txtCie2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCie2KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                setCie(txtDiagnostico2, txtCie2.getText());
            } catch (Exception e) {
                txtDiagnostico2.setText("");
            }
        } else {
            txtDiagnostico2.setText("");
        }
    }//GEN-LAST:event_txtCie2KeyReleased

    private void txtDiagnostico3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiagnostico3KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            ventanaCies(txtDiagnostico3.getText(), txtDiagnostico3);
        } else {
            txtCie3.setText("");
        }
    }//GEN-LAST:event_txtDiagnostico3KeyReleased

    private void txtCie3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCie3KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                setCie(txtDiagnostico3, txtCie3.getText());
            } catch (Exception e) {
                txtDiagnostico3.setText("");
            }
        } else {
            txtDiagnostico3.setText("");
        }
    }//GEN-LAST:event_txtCie3KeyReleased

    private void txtDiagnostico4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiagnostico4KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            ventanaCies(txtDiagnostico4.getText(), txtDiagnostico4);
        } else {
            txtCie4.setText("");
        }
    }//GEN-LAST:event_txtDiagnostico4KeyReleased

    private void txtCie4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCie4KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                setCie(txtDiagnostico4, txtCie4.getText());
            } catch (Exception e) {
                txtDiagnostico4.setText("");
            }
        } else {
            txtDiagnostico4.setText("");
        }


    }//GEN-LAST:event_txtCie4KeyReleased
    private void regresarHistoriaC() {
        String orden;
        try {
            orden = lista.getSelectedValue().toString();
        } catch (Exception e) {
            orden = "";
        }
        if (lista.getSelectedIndex() == 0) {
            orden = "";
        }
        instancias.getHistoriaC().actualizar2(orden);
    }
    private void txtEnfermedadActualKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEnfermedadActualKeyReleased

    }//GEN-LAST:event_txtEnfermedadActualKeyReleased

    private void btnReimprimirConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReimprimirConsultaActionPerformed
        if (lista.getSelectedIndex() == 0) {
            metodos.msgError(this, "Seleccione una consulta");
            return;
        }
        ModeloContacto Paciente = instancias.getSql().getDatosTercero(paciente);

        instancias.getReporte().verRemision(lista.getSelectedValue().toString().split(" - ")[0], "" + metodosGenerales.calcularEdad2(metodos.fecha(Paciente.getNacimiento())));
    }//GEN-LAST:event_btnReimprimirConsultaActionPerformed

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void txtNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtTelefonoResponsable.requestFocus();
        }
    }//GEN-LAST:event_txtNombreKeyReleased

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreKeyTyped

    private void txtResponsableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtResponsableActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtResponsableActionPerformed

    private void txtResponsableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtResponsableKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtNombre.requestFocus();
        }
    }//GEN-LAST:event_txtResponsableKeyReleased

    private void txtResponsableKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtResponsableKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtResponsableKeyTyped

    private void cmbParentescoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbParentescoKeyReleased

    }//GEN-LAST:event_cmbParentescoKeyReleased

    private void txtCie2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCie2FocusGained

    }//GEN-LAST:event_txtCie2FocusGained

    private void txtCie3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCie3FocusGained

    }//GEN-LAST:event_txtCie3FocusGained

    private void txtCie4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCie4FocusGained

    }//GEN-LAST:event_txtCie4FocusGained

    private void txtTelefonoResponsableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefonoResponsableActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoResponsableActionPerformed

    private void txtTelefonoResponsableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoResponsableKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            cmbParentesco.requestFocus();
        }
    }//GEN-LAST:event_txtTelefonoResponsableKeyReleased

    private void txtTelefonoResponsableKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoResponsableKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoResponsableKeyTyped

    private void btnBuscCie1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscCie1ActionPerformed
        ventanaCies(txtDiagnostico1.getText(), txtDiagnostico1);
    }//GEN-LAST:event_btnBuscCie1ActionPerformed

    private void btnBuscCie2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscCie2ActionPerformed
        ventanaCies(txtDiagnostico2.getText(), txtDiagnostico2);
    }//GEN-LAST:event_btnBuscCie2ActionPerformed

    private void btnBuscCie3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscCie3ActionPerformed
        ventanaCies(txtDiagnostico3.getText(), txtDiagnostico3);
    }//GEN-LAST:event_btnBuscCie3ActionPerformed

    private void btnBuscCie4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscCie4ActionPerformed
        ventanaCies(txtDiagnostico4.getText(), txtDiagnostico4);
    }//GEN-LAST:event_btnBuscCie4ActionPerformed

    private void listaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaMouseClicked
//        pnlTitulo1.scrollRectToVisible(pnlTitulo1.getVisibleRect());
    }//GEN-LAST:event_listaMouseClicked

    private void listaValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listaValueChanged
        try {
            if (lista.getSelectedValue().toString().equalsIgnoreCase("HOY - NUEVO")) {
                consultaEditable(true);
                btnGuardarConsulta.setEnabled(true);
                tblEx1.setEnabled(true);
                tblEx2.setEnabled(true);
            } else {
                consultaEditable(false);
                btnGuardarConsulta.setEnabled(false);
                ndConsultaLaboratorio nodo = instancias.getSql().getDatosConsultaLaboratorio(lista.getSelectedValue().toString().split(" - ")[0]);
                if (nodo.getId() != null) {

                    Object[][] examenes = instancias.getSql().getExamenesRealizados(lista.getSelectedValue().toString().split(" - ")[0]);

                    for (int i = 0; i < examenes.length; i++) {
                        System.out.println("examenn " + examenes[i][0]);

                        for (int j = 0; j < tblEx1.getRowCount(); j++) {
                            if (tblEx1.getValueAt(j, 0).equals(examenes[i][0])) {
                                tblEx1.setValueAt(true, j, 2);
                                break;
                            }
                        }
                    }

                    for (int i = 0; i < examenes.length; i++) {
                        for (int j = 0; j < tblEx2.getRowCount(); j++) {
                            if (tblEx2.getValueAt(j, 0).equals(examenes[i][0])) {
                                tblEx2.setValueAt(true, j, 2);
                                break;
                            }
                        }
                    }

                    for (int i = 0; i < tblEx1.getRowCount(); i++) {
                        int fila = i;
                        Boolean estado = (Boolean) tblEx1.getValueAt(fila, 2);
                        if (estado == null) {
                            estado = false;
                        }

                        if (estado) {
                            if (fila == 0) {
                                btnAcidoUrico.setEnabled(true);
                                btnAcidoUrico.setBackground(new Color(102, 153, 0));
                                tblEx1.setValueAt(true, fila, 2);
                            } else if (fila == 1) {
                                btnAntiestreptolisina.setEnabled(true);
                                btnAntiestreptolisina.setBackground(new Color(102, 153, 0));
                                tblEx1.setValueAt(true, fila, 2);
                            } else if (fila == 2) {
                                btnCannabinoides.setEnabled(true);
                                btnCannabinoides.setBackground(new Color(102, 153, 0));
                                tblEx1.setValueAt(true, fila, 2);
                            } else if (fila == 3) {
                                btnCocaina.setEnabled(true);
                                btnCocaina.setBackground(new Color(102, 153, 0));
                                tblEx1.setValueAt(true, fila, 2);
                            } else if (fila == 4) {
                                btnCoprologico.setEnabled(true);
                                btnCoprologico.setBackground(new Color(102, 153, 0));
                                tblEx1.setValueAt(true, fila, 2);
                            } else if (fila == 5) {
                                btnCoprograma.setEnabled(true);
                                btnCoprograma.setBackground(new Color(102, 153, 0));
                                tblEx1.setValueAt(true, fila, 2);
                            } else if (fila == 6) {
                                btnColesterol.setEnabled(true);
                                btnColesterol.setBackground(new Color(102, 153, 0));
                                tblEx1.setValueAt(true, fila, 2);
                            } else if (fila == 7) {
                                btnColesterol.setEnabled(true);
                                btnColesterol.setBackground(new Color(102, 153, 0));
                                tblEx1.setValueAt(true, fila, 2);
                            } else if (fila == 8) {
                                btnCitoquimico.setEnabled(true);
                                btnCitoquimico.setBackground(new Color(102, 153, 0));
                                tblEx1.setValueAt(true, fila, 2);
                            } else if (fila == 9) {
                                btnCreatinina.setEnabled(true);
                                btnCreatinina.setBackground(new Color(102, 153, 0));
                                tblEx1.setValueAt(true, fila, 2);
                            } else if (fila == 10) {
                                btnCreatinina.setEnabled(true);
                                btnCreatinina.setBackground(new Color(102, 153, 0));
                                tblEx1.setValueAt(true, fila, 2);
                            } else if (fila == 11) {
                                btnCreatinina.setEnabled(true);
                                btnCreatinina.setBackground(new Color(102, 153, 0));
                                tblEx1.setValueAt(true, fila, 2);
                            } else if (fila == 12) {
                                btnGlicemia.setEnabled(true);
                                btnGlicemia.setBackground(new Color(102, 153, 0));
                                tblEx1.setValueAt(true, fila, 2);
                            } else if (fila == 13) {
                                btnHemoglobina.setEnabled(true);
                                btnHemoglobina.setBackground(new Color(102, 153, 0));
                                tblEx1.setValueAt(true, fila, 2);
                            }
                        }
                    }

                    for (int i = 0; i < tblEx2.getRowCount(); i++) {
                        int fila = i;
                        Boolean estado = (Boolean) tblEx2.getValueAt(fila, 2);
                        if (estado == null) {
                            estado = false;
                        }

                        if (estado) {
                            if (fila == 0) {
                                btnHemoclasificacion.setEnabled(true);
                                btnHemoclasificacion.setBackground(new Color(102, 153, 0));
                                tblEx2.setValueAt(true, fila, 2);
                            } else if (fila == 1) {
                                btnFactor.setEnabled(true);
                                btnFactor.setBackground(new Color(102, 153, 0));
                                tblEx2.setValueAt(true, fila, 2);
                            } else if (fila == 2) {
                                btnProteina.setEnabled(true);
                                btnProteina.setBackground(new Color(102, 153, 0));
                                tblEx2.setValueAt(true, fila, 2);
                            } else if (fila == 3) {
                                btnHemograma.setEnabled(true);
                                btnHemograma.setBackground(new Color(102, 153, 0));
                                tblEx2.setValueAt(true, fila, 2);
                            } else if (fila == 4) {
                                btnSerologia.setEnabled(true);
                                btnSerologia.setBackground(new Color(102, 153, 0));
                                tblEx2.setValueAt(true, fila, 2);
                            } else if (fila == 5) {
                                btnTiempos.setEnabled(true);
                                btnTiempos.setBackground(new Color(102, 153, 0));
                                tblEx2.setValueAt(true, fila, 2);
                            } else if (fila == 6) {
                                btnTiempos.setEnabled(true);
                                btnTiempos.setBackground(new Color(102, 153, 0));
                                tblEx2.setValueAt(true, fila, 2);
                            } else if (fila == 7) {
                                btnSangreOculta.setEnabled(true);
                                btnSangreOculta.setBackground(new Color(102, 153, 0));
                                tblEx2.setValueAt(true, fila, 2);
                            } else if (fila == 8) {
                                btnKoh.setEnabled(true);
                                btnKoh.setBackground(new Color(102, 153, 0));
                                tblEx2.setValueAt(true, fila, 2);
                            } else if (fila == 9) {
                                btnPerfil.setEnabled(true);
                                btnPerfil.setBackground(new Color(102, 153, 0));
                                tblEx2.setValueAt(true, fila, 2);
                            } else if (fila == 10) {
                                btnPrueba.setEnabled(true);
                                btnPrueba.setBackground(new Color(102, 153, 0));
                                tblEx2.setValueAt(true, fila, 2);
                            } else if (fila == 11) {
                                btnDirecto.setEnabled(true);
                                btnDirecto.setBackground(new Color(102, 153, 0));
                                tblEx2.setValueAt(true, fila, 2);
                            } else if (fila == 12) {
                                btnDirecto.setEnabled(true);
                                btnDirecto.setBackground(new Color(102, 153, 0));
                                tblEx2.setValueAt(true, fila, 2);
                            }
                        }
                    }

                    for (int i = 0; i < tblEx1.getRowCount(); i++) {
                        tblEx1.setValueAt(false, i, 2);
                    }

                    for (int i = 0; i < tblEx2.getRowCount(); i++) {
                        tblEx2.setValueAt(false, i, 2);
                    }

                    tblEx1.setEnabled(false);
                    tblEx2.setEnabled(false);

                    txtResponsable.setText(nodo.getResponsable());
                    txtNombre.setText(nodo.getNombre());
                    cmbParentesco.setSelectedItem(nodo.getParentesco());
                    txtMotivo.setText(nodo.getMotivo());
                    txtEnfermedadActual.setText(nodo.getProblemas());
                    txtCie1.setText(nodo.getCie1());
                    txtCie2.setText(nodo.getCie2());
                    txtCie3.setText(nodo.getCie3());
                    txtCie4.setText(nodo.getCie4());

                    txtTratamiento.setText(nodo.getTratamiento());
                    txtRiesgosRecomendaciones.setText(nodo.getRecomendaciones());

                    try {
                        txtDiagnostico1.setText(instancias.getSql().getNombreCie(txtCie1.getText()));
                    } catch (NullPointerException e) {
                    }

                    try {
                        txtDiagnostico2.setText(instancias.getSql().getNombreCie(txtCie2.getText()));
                    } catch (NullPointerException e) {
                    }

                    try {
                        txtDiagnostico3.setText(instancias.getSql().getNombreCie(txtCie3.getText()));
                    } catch (NullPointerException e) {
                    }

                    try {
                        txtDiagnostico4.setText(instancias.getSql().getNombreCie(txtCie4.getText()));
                    } catch (NullPointerException e) {
                    }

                    cmbTipoDiagnostico.setSelectedItem(nodo.getTipoDiagnostico());
                    txtTelefonoResponsable.setText(nodo.getTelefonoResponsable());
                    cmbTipoDiagnostico.setEnabled(false);
                }
            }
        } catch (Exception e) {
            lista.setSelectedIndex(0);
            consultaEditable(true);
        }
    }//GEN-LAST:event_listaValueChanged

    private void lbNitKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lbNitKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtId.requestFocus();
        }
    }//GEN-LAST:event_lbNitKeyReleased

    private void txtIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdActionPerformed

    private void txtIdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cargarCliente(txtId.getText());
        } else if (!txtNombreCompleto.getText().equals("")) {
            setPaciente(null);
        }
    }//GEN-LAST:event_txtIdKeyReleased

    private void txtFechaNacimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaNacimientoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaNacimientoActionPerformed

    private void txtEdadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEdadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEdadActionPerformed

    private void txtNombreCompletoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreCompletoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreCompletoActionPerformed

    private void txtDiagnosticoEspecializadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiagnosticoEspecializadoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtRiesgosRecomendaciones.requestFocus();
        }
    }//GEN-LAST:event_txtDiagnosticoEspecializadoKeyReleased

    private void btnBuscPlantilla24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscPlantilla24ActionPerformed
        ventanaPlantillas(txtDiagnosticoEspecializado, txtDiagnosticoEspecializado.getText());
    }//GEN-LAST:event_btnBuscPlantilla24ActionPerformed

    private void btnBuscPlantilla25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscPlantilla25ActionPerformed
        ventanaPlantillas(txtRiesgosRecomendaciones, txtRiesgosRecomendaciones.getText());
    }//GEN-LAST:event_btnBuscPlantilla25ActionPerformed

    private void txtGeneroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGeneroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGeneroActionPerformed

    private void txtCie2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCie2FocusLost
        if (txtDiagnostico2.getText().equals("")) {
            txtCie2.setText("");
        }
    }//GEN-LAST:event_txtCie2FocusLost

    private void txtCie1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCie1FocusLost
        if (txtDiagnostico1.getText().equals("")) {
            txtCie1.setText("");
        }
    }//GEN-LAST:event_txtCie1FocusLost

    private void txtCie3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCie3FocusLost
        if (txtDiagnostico3.getText().equals("")) {
            txtCie3.setText("");
        }
    }//GEN-LAST:event_txtCie3FocusLost

    private void txtCie4FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCie4FocusLost
        if (txtDiagnostico4.getText().equals("")) {
            txtCie4.setText("");
        }
    }//GEN-LAST:event_txtCie4FocusLost

    private void lbNit2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbNit2MouseClicked
        lbNit12.scrollRectToVisible(lbNit12.getVisibleRect());
    }//GEN-LAST:event_lbNit2MouseClicked

    private void lbNit3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbNit3MouseClicked
        btnGuardarConsulta.scrollRectToVisible(btnGuardarConsulta.getVisibleRect());
    }//GEN-LAST:event_lbNit3MouseClicked

    private void btnAcidoUricoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAcidoUricoActionPerformed
        if (lista.getSelectedIndex() == 0) {
            metodos.msgAdvertencia(this, "Debe de generar una consulta asociada");
            return;
        }
        instancias.getAcidoUrico().cargar(txtId.getText(), lista.getSelectedValue().toString().split(" - ")[0]);
        instancias.getMenu().ocultarMenu("preparacion");
    }//GEN-LAST:event_btnAcidoUricoActionPerformed

    private void btnAntiestreptolisinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAntiestreptolisinaActionPerformed
        if (lista.getSelectedIndex() == 0) {
            metodos.msgAdvertencia(this, "Debe de generar una consulta asociada");
            return;
        }
        instancias.getMenu().ocultarMenu("preparacion");
        instancias.getAntiestreptosilina().cargar(txtId.getText(), lista.getSelectedValue().toString().split(" - ")[0]);
    }//GEN-LAST:event_btnAntiestreptolisinaActionPerformed

    private void btnCannabinoidesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCannabinoidesActionPerformed
        if (lista.getSelectedIndex() == 0) {
            metodos.msgAdvertencia(this, "Debe de generar una consulta asociada");
            return;
        }
        instancias.getMenu().ocultarMenu("preparacion");
        instancias.getCannabinoides().cargar(txtId.getText(), lista.getSelectedValue().toString().split(" - ")[0]);
    }//GEN-LAST:event_btnCannabinoidesActionPerformed

    private void btnCocainaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCocainaActionPerformed
        if (lista.getSelectedIndex() == 0) {
            metodos.msgAdvertencia(this, "Debe de generar una consulta asociada");
            return;
        }
        instancias.getMenu().ocultarMenu("preparacion");
        instancias.getCocaina().cargar(txtId.getText(), lista.getSelectedValue().toString().split(" - ")[0]);
    }//GEN-LAST:event_btnCocainaActionPerformed

    private void btnCoprologicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCoprologicoActionPerformed
        if (lista.getSelectedIndex() == 0) {
            metodos.msgAdvertencia(this, "Debe de generar una consulta asociada");
            return;
        }
        instancias.getMenu().ocultarMenu("preparacion");
        instancias.getCoprologico().cargar(txtId.getText(), lista.getSelectedValue().toString().split(" - ")[0]);
    }//GEN-LAST:event_btnCoprologicoActionPerformed

    private void btnCoprogramaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCoprogramaActionPerformed
        if (lista.getSelectedIndex() == 0) {
            metodos.msgAdvertencia(this, "Debe de generar una consulta asociada");
            return;
        }
        instancias.getMenu().ocultarMenu("preparacion");
        instancias.getCoprograma().cargar(txtId.getText(), lista.getSelectedValue().toString().split(" - ")[0]);
    }//GEN-LAST:event_btnCoprogramaActionPerformed

    private void btnColesterolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnColesterolActionPerformed
        if (lista.getSelectedIndex() == 0) {
            metodos.msgAdvertencia(this, "Debe de generar una consulta asociada");
            return;
        }
        instancias.getMenu().ocultarMenu("preparacion");
        instancias.getColesterol().cargar(txtId.getText(), lista.getSelectedValue().toString().split(" - ")[0]);
    }//GEN-LAST:event_btnColesterolActionPerformed

    private void btnSerologiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSerologiaActionPerformed
        if (lista.getSelectedIndex() == 0) {
            metodos.msgAdvertencia(this, "Debe de generar una consulta asociada");
            return;
        }
        instancias.getMenu().ocultarMenu("preparacion");
        instancias.getSerologia().cargar(txtId.getText(), lista.getSelectedValue().toString().split(" - ")[0]);
    }//GEN-LAST:event_btnSerologiaActionPerformed

    private void btnCitoquimicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCitoquimicoActionPerformed
        if (lista.getSelectedIndex() == 0) {
            metodos.msgAdvertencia(this, "Debe de generar una consulta asociada");
            return;
        }
        instancias.getMenu().ocultarMenu("preparacion");
        instancias.getCitoquimico().cargar(txtId.getText(), lista.getSelectedValue().toString().split(" - ")[0]);
    }//GEN-LAST:event_btnCitoquimicoActionPerformed

    private void btnDirectoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDirectoActionPerformed
        if (lista.getSelectedIndex() == 0) {
            metodos.msgAdvertencia(this, "Debe de generar una consulta asociada");
            return;
        }
        instancias.getMenu().ocultarMenu("preparacion");
        instancias.getDirectos().cargar(txtId.getText(), lista.getSelectedValue().toString().split(" - ")[0]);
    }//GEN-LAST:event_btnDirectoActionPerformed

    private void btnGlicemiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGlicemiaActionPerformed
        if (lista.getSelectedIndex() == 0) {
            metodos.msgAdvertencia(this, "Debe de generar una consulta asociada");
            return;
        }
        instancias.getMenu().ocultarMenu("preparacion");
        instancias.getGlicemia().cargar(txtId.getText(), lista.getSelectedValue().toString().split(" - ")[0]);
    }//GEN-LAST:event_btnGlicemiaActionPerformed

    private void btnHemogramaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHemogramaActionPerformed
        if (lista.getSelectedIndex() == 0) {
            metodos.msgAdvertencia(this, "Debe de generar una consulta asociada");
            return;
        }
        instancias.getMenu().ocultarMenu("preparacion");
        instancias.getHemograma().cargar(txtId.getText(), lista.getSelectedValue().toString().split(" - ")[0]);
    }//GEN-LAST:event_btnHemogramaActionPerformed

    private void btnHemoclasificacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHemoclasificacionActionPerformed
        if (lista.getSelectedIndex() == 0) {
            metodos.msgAdvertencia(this, "Debe de generar una consulta asociada");
            return;
        }
        instancias.getMenu().ocultarMenu("preparacion");
        instancias.getHemoclasificacion().cargar(txtId.getText(), lista.getSelectedValue().toString().split(" - ")[0]);
    }//GEN-LAST:event_btnHemoclasificacionActionPerformed

    private void btnTiemposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTiemposActionPerformed
        if (lista.getSelectedIndex() == 0) {
            metodos.msgAdvertencia(this, "Debe de generar una consulta asociada");
            return;
        }
        instancias.getMenu().ocultarMenu("preparacion");
        instancias.getTiempos().cargar(txtId.getText(), lista.getSelectedValue().toString().split(" - ")[0]);
    }//GEN-LAST:event_btnTiemposActionPerformed

    private void btnCreatininaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreatininaActionPerformed
        if (lista.getSelectedIndex() == 0) {
            metodos.msgAdvertencia(this, "Debe de generar una consulta asociada");
            return;
        }
        instancias.getMenu().ocultarMenu("preparacion");
        instancias.getCreatinina().cargar(txtId.getText(), lista.getSelectedValue().toString().split(" - ")[0]);
    }//GEN-LAST:event_btnCreatininaActionPerformed

    private void btnFactorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFactorActionPerformed
        if (lista.getSelectedIndex() == 0) {
            metodos.msgAdvertencia(this, "Debe de generar una consulta asociada");
            return;
        }
        instancias.getMenu().ocultarMenu("preparacion");
        instancias.getAcidos().cargar(txtId.getText(), lista.getSelectedValue().toString().split(" - ")[0]);
    }//GEN-LAST:event_btnFactorActionPerformed

    private void btnHemoglobinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHemoglobinaActionPerformed
        if (txtId.getText().equals("")) {
            metodos.msgAdvertencia(this, "Debe cargar un paciente");
            return;
        }
        instancias.getMenu().ocultarMenu("preparacion");
        instancias.getHemoglobina().cargar(txtId.getText(), lista.getSelectedValue().toString().split(" - ")[0]);
    }//GEN-LAST:event_btnHemoglobinaActionPerformed

    private void btnSangreOcultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSangreOcultaActionPerformed
        if (lista.getSelectedIndex() == 0) {
            metodos.msgAdvertencia(this, "Debe de generar una consulta asociada");
            return;
        }
        instancias.getMenu().ocultarMenu("preparacion");
        instancias.getSangreOculta().cargar(txtId.getText(), lista.getSelectedValue().toString().split(" - ")[0]);
    }//GEN-LAST:event_btnSangreOcultaActionPerformed

    private void btnKohActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKohActionPerformed
        if (txtId.getText().equals("")) {
            metodos.msgAdvertencia(this, "Debe cargar un paciente");
            return;
        }
        instancias.getMenu().ocultarMenu("preparacion");
        instancias.getKoh().cargar(txtId.getText(), lista.getSelectedValue().toString().split(" - ")[0]);
    }//GEN-LAST:event_btnKohActionPerformed

    private void btnPruebaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPruebaActionPerformed
        if (lista.getSelectedIndex() == 0) {
            metodos.msgAdvertencia(this, "Debe de generar una consulta asociada");
            return;
        }
        instancias.getMenu().ocultarMenu("preparacion");
        instancias.getEmbarazo().cargar(txtId.getText(), lista.getSelectedValue().toString().split(" - ")[0]);
    }//GEN-LAST:event_btnPruebaActionPerformed

    private void btnProteinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProteinaActionPerformed
        if (lista.getSelectedIndex() == 0) {
            metodos.msgAdvertencia(this, "Debe de generar una consulta asociada");
            return;
        }
        instancias.getMenu().ocultarMenu("preparacion");
        instancias.getProteina().cargar(txtId.getText(), lista.getSelectedValue().toString().split(" - ")[0]);
    }//GEN-LAST:event_btnProteinaActionPerformed

    private void btnPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPerfilActionPerformed
        if (lista.getSelectedIndex() == 0) {
            metodos.msgAdvertencia(this, "Debe de generar una consulta asociada");
            return;
        }
        instancias.getMenu().ocultarMenu("preparacion");
        instancias.getPerfil().cargar(txtId.getText(), lista.getSelectedValue().toString().split(" - ")[0]);
    }//GEN-LAST:event_btnPerfilActionPerformed

    private void tblEx1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblEx1KeyReleased

    }//GEN-LAST:event_tblEx1KeyReleased

    private void tblEx2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblEx2KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tblEx2KeyReleased

    private void tblEx1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEx1MouseClicked
        if (evt.getClickCount() > 1) {

            int fila = tblEx1.getSelectedRow();
            Boolean estado = (Boolean) tblEx1.getValueAt(fila, 2);
            if (estado == null) {
                estado = false;
            }

            if (!estado) {
                if (fila == 0) {
                    btnAcidoUrico.setEnabled(true);
                    btnAcidoUrico.setBackground(new Color(102, 153, 0));
                    tblEx1.setValueAt(true, fila, 2);
                } else if (fila == 1) {
                    btnAntiestreptolisina.setEnabled(true);
                    btnAntiestreptolisina.setBackground(new Color(102, 153, 0));
                    tblEx1.setValueAt(true, fila, 2);
                } else if (fila == 2) {
                    btnCannabinoides.setEnabled(true);
                    btnCannabinoides.setBackground(new Color(102, 153, 0));
                    tblEx1.setValueAt(true, fila, 2);
                } else if (fila == 3) {
                    btnCocaina.setEnabled(true);
                    btnCocaina.setBackground(new Color(102, 153, 0));
                    tblEx1.setValueAt(true, fila, 2);
                } else if (fila == 4) {
                    btnCoprologico.setEnabled(true);
                    btnCoprologico.setBackground(new Color(102, 153, 0));
                    tblEx1.setValueAt(true, fila, 2);
                } else if (fila == 5) {
                    btnCoprograma.setEnabled(true);
                    btnCoprograma.setBackground(new Color(102, 153, 0));
                    tblEx1.setValueAt(true, fila, 2);
                } else if (fila == 6) {
                    btnColesterol.setEnabled(true);
                    btnColesterol.setBackground(new Color(102, 153, 0));
                    tblEx1.setValueAt(true, fila, 2);
                } else if (fila == 7) {
                    btnColesterol.setEnabled(true);
                    btnColesterol.setBackground(new Color(102, 153, 0));
                    tblEx1.setValueAt(true, fila, 2);
                } else if (fila == 8) {
                    btnCitoquimico.setEnabled(true);
                    btnCitoquimico.setBackground(new Color(102, 153, 0));
                    tblEx1.setValueAt(true, fila, 2);
                } else if (fila == 9) {
                    btnCreatinina.setEnabled(true);
                    btnCreatinina.setBackground(new Color(102, 153, 0));
                    tblEx1.setValueAt(true, fila, 2);
                } else if (fila == 10) {
                    btnCreatinina.setEnabled(true);
                    btnCreatinina.setBackground(new Color(102, 153, 0));
                    tblEx1.setValueAt(true, fila, 2);
                } else if (fila == 11) {
                    btnCreatinina.setEnabled(true);
                    btnCreatinina.setBackground(new Color(102, 153, 0));
                    tblEx1.setValueAt(true, fila, 2);
                } else if (fila == 12) {
                    btnGlicemia.setEnabled(true);
                    btnGlicemia.setBackground(new Color(102, 153, 0));
                    tblEx1.setValueAt(true, fila, 2);
                } else if (fila == 13) {
                    btnHemoglobina.setEnabled(true);
                    btnHemoglobina.setBackground(new Color(102, 153, 0));
                    tblEx1.setValueAt(true, fila, 2);
                }
            } else {
                if (fila == 0) {
                    btnAcidoUrico.setEnabled(false);
                    btnAcidoUrico.setBackground(new Color(255, 255, 255));
                    tblEx1.setValueAt(false, fila, 2);
                } else if (fila == 1) {
                    btnAntiestreptolisina.setEnabled(false);
                    btnAntiestreptolisina.setBackground(new Color(255, 255, 255));
                    tblEx1.setValueAt(false, fila, 2);
                } else if (fila == 2) {
                    btnCannabinoides.setEnabled(false);
                    btnCannabinoides.setBackground(new Color(255, 255, 255));
                    tblEx1.setValueAt(false, fila, 2);
                } else if (fila == 3) {
                    btnCocaina.setEnabled(false);
                    btnCocaina.setBackground(new Color(255, 255, 255));
                    tblEx1.setValueAt(false, fila, 2);
                } else if (fila == 4) {
                    btnCoprologico.setEnabled(false);
                    btnCoprologico.setBackground(new Color(255, 255, 255));
                    tblEx1.setValueAt(false, fila, 2);
                } else if (fila == 5) {
                    btnCoprograma.setEnabled(false);
                    btnCoprograma.setBackground(new Color(255, 255, 255));
                    tblEx1.setValueAt(false, fila, 2);
                } else if (fila == 6) {
                    Boolean estado1 = (Boolean) tblEx1.getValueAt(fila + 1, 2);
                    if (estado1 == null) {
                        estado1 = false;
                    }
                    if (!estado1) {
                        btnColesterol.setEnabled(false);
                        btnColesterol.setBackground(new Color(255, 255, 255));
                    }
                    tblEx1.setValueAt(false, fila, 2);
                } else if (fila == 7) {
                    Boolean estado1 = (Boolean) tblEx1.getValueAt(fila - 1, 2);
                    if (estado1 == null) {
                        estado1 = false;
                    }
                    if (!estado1) {
                        btnColesterol.setEnabled(false);
                        btnColesterol.setBackground(new Color(255, 255, 255));
                    }
                    tblEx1.setValueAt(false, fila, 2);
                } else if (fila == 8) {
                    btnCitoquimico.setEnabled(false);
                    btnCitoquimico.setBackground(new Color(255, 255, 255));
                    tblEx1.setValueAt(false, fila, 2);
                } else if (fila == 9) {
                    Boolean estado1 = (Boolean) tblEx1.getValueAt(fila + 1, 2);
                    if (estado1 == null) {
                        estado1 = false;
                    }
                    Boolean estado2 = (Boolean) tblEx1.getValueAt(fila + 2, 2);
                    if (estado2 == null) {
                        estado2 = false;
                    }
                    if (!estado1 && !estado2) {
                        btnCreatinina.setEnabled(false);
                        btnCreatinina.setBackground(new Color(255, 255, 255));
                    }
                    tblEx1.setValueAt(false, fila, 2);
                } else if (fila == 10) {
                    Boolean estado1 = (Boolean) tblEx1.getValueAt(fila + 1, 2);
                    if (estado1 == null) {
                        estado1 = false;
                    }
                    Boolean estado2 = (Boolean) tblEx1.getValueAt(fila - 1, 2);
                    if (estado2 == null) {
                        estado2 = false;
                    }
                    if (!estado1 && !estado2) {
                        btnCreatinina.setEnabled(false);
                        btnCreatinina.setBackground(new Color(255, 255, 255));
                    }
                    tblEx1.setValueAt(false, fila, 2);
                } else if (fila == 11) {
                    Boolean estado1 = (Boolean) tblEx1.getValueAt(fila - 1, 2);
                    if (estado1 == null) {
                        estado1 = false;
                    }
                    Boolean estado2 = (Boolean) tblEx1.getValueAt(fila - 2, 2);
                    if (estado2 == null) {
                        estado2 = false;
                    }
                    if (!estado1 && !estado2) {
                        btnCreatinina.setEnabled(false);
                        btnCreatinina.setBackground(new Color(255, 255, 255));
                    }
                    tblEx1.setValueAt(false, fila, 2);
                } else if (fila == 12) {
                    btnGlicemia.setEnabled(false);
                    btnGlicemia.setBackground(new Color(255, 255, 255));
                    tblEx1.setValueAt(false, fila, 2);
                } else if (fila == 13) {
                    btnHemoglobina.setEnabled(false);
                    btnHemoglobina.setBackground(new Color(255, 255, 255));
                    tblEx1.setValueAt(false, fila, 2);
                }
            }
        }
        tblEx1.removeEditor();
        lbEmail31.requestFocus();
    }//GEN-LAST:event_tblEx1MouseClicked

    private void tblEx2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEx2MouseClicked
        if (evt.getClickCount() > 1) {

            int fila = tblEx2.getSelectedRow();
            Boolean estado = (Boolean) tblEx2.getValueAt(fila, 2);
            if (estado == null) {
                estado = false;
            }

            if (!estado) {
                if (fila == 0) {
                    btnHemoclasificacion.setEnabled(true);
                    btnHemoclasificacion.setBackground(new Color(102, 153, 0));
                    tblEx2.setValueAt(true, fila, 2);
                } else if (fila == 1) {
                    btnFactor.setEnabled(true);
                    btnFactor.setBackground(new Color(102, 153, 0));
                    tblEx2.setValueAt(true, fila, 2);
                } else if (fila == 2) {
                    btnProteina.setEnabled(true);
                    btnProteina.setBackground(new Color(102, 153, 0));
                    tblEx2.setValueAt(true, fila, 2);
                } else if (fila == 3) {
                    btnHemograma.setEnabled(true);
                    btnHemograma.setBackground(new Color(102, 153, 0));
                    tblEx2.setValueAt(true, fila, 2);
                } else if (fila == 4) {
                    btnSerologia.setEnabled(true);
                    btnSerologia.setBackground(new Color(102, 153, 0));
                    tblEx2.setValueAt(true, fila, 2);
                } else if (fila == 5) {
                    btnTiempos.setEnabled(true);
                    btnTiempos.setBackground(new Color(102, 153, 0));
                    tblEx2.setValueAt(true, fila, 2);
                } else if (fila == 6) {
                    btnTiempos.setEnabled(true);
                    btnTiempos.setBackground(new Color(102, 153, 0));
                    tblEx2.setValueAt(true, fila, 2);
                } else if (fila == 7) {
                    btnSangreOculta.setEnabled(true);
                    btnSangreOculta.setBackground(new Color(102, 153, 0));
                    tblEx2.setValueAt(true, fila, 2);
                } else if (fila == 8) {
                    btnKoh.setEnabled(true);
                    btnKoh.setBackground(new Color(102, 153, 0));
                    tblEx2.setValueAt(true, fila, 2);
                } else if (fila == 9) {
                    btnPerfil.setEnabled(true);
                    btnPerfil.setBackground(new Color(102, 153, 0));
                    tblEx2.setValueAt(true, fila, 2);
                } else if (fila == 10) {
                    btnPrueba.setEnabled(true);
                    btnPrueba.setBackground(new Color(102, 153, 0));
                    tblEx2.setValueAt(true, fila, 2);
                } else if (fila == 11) {
                    btnDirecto.setEnabled(true);
                    btnDirecto.setBackground(new Color(102, 153, 0));
                    tblEx2.setValueAt(true, fila, 2);
                } else if (fila == 12) {
                    btnDirecto.setEnabled(true);
                    btnDirecto.setBackground(new Color(102, 153, 0));
                    tblEx2.setValueAt(true, fila, 2);
                }
            } else {
                if (fila == 0) {
                    btnHemoclasificacion.setEnabled(false);
                    btnHemoclasificacion.setBackground(new Color(255, 255, 255));
                    tblEx2.setValueAt(false, fila, 2);
                } else if (fila == 1) {
                    btnFactor.setEnabled(false);
                    btnFactor.setBackground(new Color(255, 255, 255));
                    tblEx2.setValueAt(false, fila, 2);
                } else if (fila == 2) {
                    btnProteina.setEnabled(false);
                    btnProteina.setBackground(new Color(255, 255, 255));
                    tblEx2.setValueAt(false, fila, 2);
                } else if (fila == 3) {
                    btnHemograma.setEnabled(false);
                    btnHemograma.setBackground(new Color(255, 255, 255));
                    tblEx2.setValueAt(false, fila, 2);
                } else if (fila == 4) {
                    btnSerologia.setEnabled(false);
                    btnSerologia.setBackground(new Color(255, 255, 255));
                    tblEx2.setValueAt(false, fila, 2);
                } else if (fila == 5) {
                    Boolean estado1 = (Boolean) tblEx2.getValueAt(fila + 1, 2);
                    if (estado1 == null) {
                        estado1 = false;
                    }
                    if (!estado1) {
                        btnTiempos.setEnabled(false);
                        btnTiempos.setBackground(new Color(255, 255, 255));
                    }
                    tblEx2.setValueAt(false, fila, 2);
                } else if (fila == 6) {
                    Boolean estado1 = (Boolean) tblEx2.getValueAt(fila - 1, 2);
                    if (estado1 == null) {
                        estado1 = false;
                    }
                    if (!estado1) {
                        btnTiempos.setEnabled(false);
                        btnTiempos.setBackground(new Color(255, 255, 255));
                    }
                    tblEx2.setValueAt(false, fila, 2);
                } else if (fila == 7) {
                    btnSangreOculta.setEnabled(false);
                    btnSangreOculta.setBackground(new Color(255, 255, 255));
                    tblEx2.setValueAt(false, fila, 2);
                } else if (fila == 8) {
                    btnKoh.setEnabled(false);
                    btnKoh.setBackground(new Color(255, 255, 255));
                    tblEx2.setValueAt(false, fila, 2);
                } else if (fila == 9) {
                    btnPerfil.setEnabled(false);
                    btnPerfil.setBackground(new Color(255, 255, 255));
                    tblEx2.setValueAt(false, fila, 2);
                } else if (fila == 10) {
                    btnPrueba.setEnabled(false);
                    btnPrueba.setBackground(new Color(255, 255, 255));
                    tblEx2.setValueAt(false, fila, 2);
                } else if (fila == 11) {
                    Boolean estado1 = (Boolean) tblEx2.getValueAt(fila + 1, 2);
                    if (estado1 == null) {
                        estado1 = false;
                    }
                    if (!estado1) {
                        btnDirecto.setEnabled(false);
                        btnDirecto.setBackground(new Color(255, 255, 255));
                    }
                    tblEx2.setValueAt(false, fila, 2);
                } else if (fila == 12) {
                    Boolean estado1 = (Boolean) tblEx2.getValueAt(fila - 1, 2);
                    if (estado1 == null) {
                        estado1 = false;
                    }
                    if (!estado1) {
                        btnDirecto.setEnabled(false);
                        btnDirecto.setBackground(new Color(255, 255, 255));
                    }
                    tblEx2.setValueAt(false, fila, 2);
                }
            }
        }
        tblEx2.removeEditor();
        lbEmail31.requestFocus();
    }//GEN-LAST:event_tblEx2MouseClicked

    public void ventanaPlantillas(JTextArea campo, String texto) {
        buscPlantillaDeTextos buscar = new buscPlantillaDeTextos(null, rootPaneCheckingEnabled);
        buscar.setLocationRelativeTo(null);
        instancias.setBuscPlantillaDeTextos(buscar);
        instancias.setCampoActualTextArea(campo);
        campo.requestFocus();
//        buscar.setInstancia(instancias);
        buscar.noEncontrado(texto);
        buscar.show();
    }

    public void actualizarExamenes() {
        try {
            Object[][] examenes = instancias.getSql().getExamenesLaboratorio();

            for (int i = 0; i < 14; i++) {
                try {
                    tblEx1.setValueAt(examenes[i][0], i, 0);
                    tblEx1.setValueAt(big.setMoneda(big.getMoneda(examenes[i][2].toString())), i, 3);
                } catch (Exception e) {
                }
            }

            for (int i = 14; i < 27; i++) {
                try {
                    tblEx2.setValueAt(examenes[i][0], i - 14, 0);
                    tblEx2.setValueAt(big.setMoneda(big.getMoneda(examenes[i][2].toString())), i - 14, 3);
                } catch (Exception e) {
                }
            }
        } catch (Exception e) {
        }
    }

    private void validarRadioSiNO(JRadioButton radio, JTextField texto) {
        if (radio.isSelected()) {
            texto.setEditable(true);
        } else {
            texto.setEditable(false);
        }
    }

    public void setPaciente(String paciente) {

        try {
            this.setSelected(true);

        } catch (PropertyVetoException ex) {
            Logger.getLogger(infHistoriaLaboratorio.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        ndHistoriaClinica nodo = instancias.getSql().getDatosHistoriaClinica(paciente);
        historia = nodo;
        ModeloContacto Paciente = instancias.getSql().getDatosTercero(paciente);
        txtId.setText(paciente);
        txtNombreCompleto.setText(Paciente.getNombre());
        txtGenero.setText(Paciente.getSexo());

        try {
            txtFechaNacimiento.setText(metodos.fecha(Paciente.getNacimiento()));
            txtEdad.setText(metodosGenerales.calcularEdad2(metodos.fecha(Paciente.getNacimiento())) + "");
        } catch (Exception e) {
            txtFechaNacimiento.setText("");
        }

        Object[][] fechas = instancias.getSql().getConsultasLaboratorio(txtId.getText());
        Object[] datos = new Object[fechas.length + 1];
        datos[0] = "HOY - NUEVO";
        int j = fechas.length;
        int id = 0;

        for (int i = 0; i < fechas.length; i++) {
            datos[j--] = fechas[i][0] + " - " + metodos.fecha(fechas[i][1].toString());
            if (Integer.parseInt(fechas[i][0].toString()) > id) {
                id = Integer.parseInt(fechas[i][0].toString());
            }
        }

//        if (id != 0) {
//            ndConsultaClinica nodoConsulta = instancias.getSql().getDatosConsultaClinica(Integer.toString(id));
//            if (nodo.getId() != null) {
//
//            }
//        }
        lista.setListData(datos);
        lista.setSelectedIndex(0);
        consultaCargada = true;
//        cargarArchivos();

    }

    public void consultaEditable(boolean x) {

        desactivarExamenes();

        for (int i = 0; i < tblEx1.getRowCount(); i++) {
            tblEx1.setValueAt(false, i, 2);
        }

        for (int i = 0; i < tblEx2.getRowCount(); i++) {
            tblEx2.setValueAt(false, i, 2);
        }

        txtDiagnosticoEspecializado.setEditable(x);
        cmbTipoDiagnostico.setEnabled(x);
        cmbTipoDiagnostico.setSelectedIndex(0);
        txtResponsable.setEditable(x);
        txtNombre.setEditable(x);
        txtTelefonoResponsable.setEditable(x);
        cmbParentesco.setEnabled(x);
        txtMotivo.setEditable(x);
        txtEnfermedadActual.setEditable(x);
        txtDiagnostico1.setEditable(x);
        txtDiagnostico2.setEditable(x);
        txtDiagnostico3.setEditable(x);
        txtDiagnostico4.setEditable(x);
        txtRiesgosRecomendaciones.setEditable(x);
        btnBuscCie1.setEnabled(x);
        btnBuscCie2.setEnabled(x);
        btnBuscCie3.setEnabled(x);
        btnBuscCie4.setEnabled(x);
        btnBuscPlantilla24.setEnabled(x);
        btnBuscPlantilla25.setEnabled(x);
        txtResponsable.setText("");
        txtNombre.setText("");
        cmbParentesco.setSelectedIndex(0);
        txtMotivo.setText("");
        txtEnfermedadActual.setText("");
        txtCie1.setText("");
        txtCie2.setText("");
        txtCie3.setText("");
        txtCie4.setText("");
        txtDiagnostico1.setText("");
        txtDiagnostico2.setText("");
        txtDiagnostico3.setText("");
        txtDiagnostico4.setText("");
        txtTratamiento.setText("");
        txtRiesgosRecomendaciones.setText("");
        txtTelefonoResponsable.setText("");
    }

    public void limpiarExamenes(JTable tabla) {
        int cant = tabla.getRowCount();
        for (int i = 0; i < cant; i++) {
            ((DefaultTableModel) tabla.getModel()).removeRow(0);
        }
    }

    public void ventanaAntecedentes(String codigo, JTextField campo, int tipo) {
        buscAntecedentes buscar = new buscAntecedentes(null, rootPaneCheckingEnabled);
        buscar.setLocationRelativeTo(null);
        instancias.setBuscAntecedentes(buscar);
        instancias.setCampoActual(campo);
        campo.requestFocus();
        buscar.setInstancia(instancias, tipo);
        buscar.noEncontrado(codigo.replace("'", "//"));
        buscar.show();
    }

    public void ventanaCies(String codigo, JTextField campo) {
        buscCies buscar = new buscCies(null, rootPaneCheckingEnabled);
        buscar.setOpc(3);
        buscar.setLocationRelativeTo(null);
        instancias.setBuscCies(buscar);
        instancias.setCampoActual(campo);
        campo.requestFocus();
//        buscar.setInstancia(instancias);
        buscar.noEncontrado(codigo.replace("'", "//"));
        buscar.show();
    }

    public void ventanaCups(String codigo, JTextField campo) {
        buscCups buscar = new buscCups(null, rootPaneCheckingEnabled);
        buscar.setLocationRelativeTo(null);
        instancias.setBuscCups(buscar);
        instancias.setCampoActual(campo);
        campo.requestFocus();
//        buscar.setInstancia(instancias);
        buscar.noEncontrado(codigo.replace("'", "//"));
        buscar.show();
    }

    public String getConsulta() {

        try {
            if (lista.getSelectedIndex() == 0) {
                return historia.getId();
            }
        } catch (Exception e) {
            return historia.getId();
        }

        try {
            return lista.getSelectedValue().toString().split(" - ")[0];
        } catch (NullPointerException e) {
            return (String) null;
        }
    }

    public void cargarCliente(String nit) {

        ModeloContacto Tercero = sql.getDatosTercero(nit);

        if (Tercero.getId() != null) {
            setPaciente(Tercero.getId());
            lista.setSelectedIndex(0);
            return;
        }
        ventanaTerceros(nit);
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

    public void guardarOrden() {

        String consecutivo = "";
        String convenio = "";
        String factura = "";

        ndCamposOrdenPredeterminada nodo = instancias.getSql().getDatosCamposOrdenPredeterminada("1");

        BigDecimal total = BigDecimal.ZERO;
        int contador = 0;
        for (int i = 0; i < tblEx1.getRowCount(); i++) {
            try {
                if ((Boolean) tblEx1.getValueAt(i, 2)) {
                    String codigo = tblEx1.getValueAt(i, 0).toString();
                    Object[][] datos = sql.getDatosExamenLaboratorio(codigo);
                    total = total.add(big.getMoneda(datos[0][2].toString()));
                    productoFacturarOrden[contador][0] = datos[0][3];
                    productoFacturarOrden[contador][1] = datos[0][2];
                    contador++;
                }
            } catch (Exception e) {
            }
        }

        for (int i = 0; i < tblEx2.getRowCount(); i++) {
            try {
                if ((Boolean) tblEx2.getValueAt(i, 2)) {
                    String codigo = tblEx2.getValueAt(i, 0).toString();
                    Object[][] datos = sql.getDatosExamenLaboratorio(codigo);
                    total = total.add(big.getMoneda(datos[0][2].toString()));
                    productoFacturarOrden[contador][0] = datos[0][3];
                    productoFacturarOrden[contador][1] = datos[0][2];
                    contador++;
                }
            } catch (Exception e) {
            }
        }

        System.out.println("total toal " + total);
        String totalGeneral = big.setNumero(total).replace(".", "");
        System.out.println("total todsfasdfasdal " + totalGeneral);

        for (int i = 0; i < productoFacturarOrden.length; i++) {
            try {
                System.out.println("num " + productoFacturarOrden[i][0]);
            } catch (Exception e) {
            }
        }

        ModeloContacto nodoTercero = instancias.getSql().getDatosTercero(this.paciente);
        try {
            ndEpsPrecargados nodo2 = instancias.getSql().getDatosEpsPrecargados(nodoTercero.getEps());
            consecutivo = nodo2.getConsecutivo();
            consecutivo = consecutivo + "-" + instancias.getSql().getNumConsecutivoEps(consecutivo)[0];
        } catch (Exception e) {
        }

        String consecutivo1 = "";
        if (consecutivo == null) {
            consecutivo = "";
            consecutivo1 = "";
        } else {
            consecutivo1 = consecutivo.substring(6);
        }

        facturarOrden = true;
        factura = "FACT-" + instancias.getSql().getNumConsecutivo("FACT")[0];

        //guardar en ordenServicio
        orden = "" + instancias.getSql().getNumConsecutivo("ORDENLAB")[0];

        Object[] vector = {orden, factura, instancias.getUsuario(), metodosGenerales.fecha(), "", nodo.getConcepto(), nodo.getServicio(),
            "", nodo.getFinalidad(), nodo.getCausaExterna(), nodo.getAmbito(), nodo.getPersonal(), nodo.getForma(), big.getMoneda("0"), big.getMoneda("0"),
            big.getMoneda("0"), big.getMoneda(totalGeneral), big.getMoneda("1"), big.getMoneda("0"), txtId.getText(), txtDiagnostico1.getText(), "",
            metodosGenerales.fechaHora(), nodo.getTipo(), "", consecutivo1,
            nodo.getActoQuirurgico(), "1", consecutivo, nodo.getConsulta(), instancias.getSql().getNombreEmpleadoUsuario(instancias.getUsuario()), metodosGenerales.hora(), convenio};

        ndOrdenServicio nodoOrden = metodos.llenarOrdenServicio(vector);

        if (!instancias.getSql().agregarOrdenServicioLaboratorio(nodoOrden)) {
            metodos.msgError(this, "Hubo un problema al guardar la Orden de servicio");
            return;
        }

        try {
            if (!instancias.getSql().aumentarConsecutivoEps(consecutivo.substring(0, 5), Integer.parseInt((String) instancias.getSql().getNumConsecutivoEps(consecutivo.substring(0, 5))[0]) + 1)) {
                metodos.msgError(this, "Hubo un problema al guardar en el consecutivo de la EPS");
            }
        } catch (Exception e) {
            metodos.msgAdvertencia(this, "El paciente no tiene ninguna EPS, debe tener una asociada.");
        }

        if (!instancias.getSql().aumentarConsecutivo("ORDENLAB", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("ORDENLAB")[0]) + 1)) {
            metodos.msgError(this, "Hubo un problema al guardar en el consecutivo de la orden");
        }
    }

    public void facturar(Object[][] producto) {
        String[][] e = new String[producto.length][5];

        for (int i = 0; i < producto.length; i++) {
            try {
                e[i][0] = producto[i][0].toString();
                e[i][1] = "1";
                e[i][2] = "0";
                e[i][3] = producto[i][1].toString();
                e[i][4] = "0";
            } catch (Exception j) {
            }
        }
        instancias.getFactura().desdeOrdenMedica(txtId.getText(), e, false);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAcidoUrico;
    private javax.swing.JButton btnAntiestreptolisina;
    private javax.swing.JButton btnBuscCie1;
    private javax.swing.JButton btnBuscCie2;
    private javax.swing.JButton btnBuscCie3;
    private javax.swing.JButton btnBuscCie4;
    private javax.swing.JButton btnBuscPlantilla24;
    private javax.swing.JButton btnBuscPlantilla25;
    private javax.swing.JButton btnCannabinoides;
    private javax.swing.JButton btnCitoquimico;
    private javax.swing.JButton btnCocaina;
    private javax.swing.JButton btnColesterol;
    private javax.swing.JButton btnCoprograma;
    private javax.swing.JButton btnCoprologico;
    private javax.swing.JButton btnCreatinina;
    private javax.swing.JButton btnDirecto;
    private javax.swing.JButton btnFactor;
    private javax.swing.JButton btnGlicemia;
    private javax.swing.JButton btnGuardarConsulta;
    private javax.swing.JButton btnHemoclasificacion;
    private javax.swing.JButton btnHemoglobina;
    private javax.swing.JButton btnHemograma;
    private javax.swing.JButton btnKoh;
    private javax.swing.JButton btnPerfil;
    private javax.swing.JButton btnProteina;
    private javax.swing.JButton btnPrueba;
    private javax.swing.JButton btnReimprimirConsulta;
    private javax.swing.JButton btnSangreOculta;
    private javax.swing.JButton btnSerologia;
    private javax.swing.JButton btnTiempos;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup10;
    private javax.swing.ButtonGroup buttonGroup11;
    private javax.swing.ButtonGroup buttonGroup12;
    private javax.swing.ButtonGroup buttonGroup13;
    private javax.swing.ButtonGroup buttonGroup14;
    private javax.swing.ButtonGroup buttonGroup15;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.ButtonGroup buttonGroup6;
    private javax.swing.ButtonGroup buttonGroup7;
    private javax.swing.ButtonGroup buttonGroup8;
    private javax.swing.ButtonGroup buttonGroup9;
    private javax.swing.JComboBox cmbParentesco;
    private javax.swing.JComboBox cmbTipoDiagnostico;
    private javax.swing.ButtonGroup grupoAlergias;
    private javax.swing.ButtonGroup grupoFumador;
    private javax.swing.ButtonGroup grupoIngesta;
    private javax.swing.ButtonGroup grupoMedicamentos;
    private javax.swing.ButtonGroup grupoPatologicos;
    private javax.swing.ButtonGroup grupoPlanificacion;
    private javax.swing.ButtonGroup grupoPsiquiatricos;
    private javax.swing.ButtonGroup grupoQuirurgicos;
    private javax.swing.ButtonGroup grupoToxicos;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane23;
    private javax.swing.JScrollPane jScrollPane37;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JLabel lbDireccion20;
    private javax.swing.JLabel lbDireccion22;
    private javax.swing.JLabel lbDireccion25;
    private javax.swing.JLabel lbDireccion27;
    private javax.swing.JLabel lbDireccion40;
    private javax.swing.JLabel lbEmail14;
    private javax.swing.JLabel lbEmail18;
    private javax.swing.JLabel lbEmail19;
    private javax.swing.JLabel lbEmail20;
    private javax.swing.JLabel lbEmail21;
    private javax.swing.JLabel lbEmail26;
    private javax.swing.JLabel lbEmail27;
    private javax.swing.JLabel lbEmail28;
    private javax.swing.JLabel lbEmail29;
    private javax.swing.JLabel lbEmail30;
    private javax.swing.JLabel lbEmail31;
    private javax.swing.JLabel lbEmail33;
    private javax.swing.JLabel lbEmail34;
    private javax.swing.JLabel lbLista;
    private javax.swing.JLabel lbNit;
    private javax.swing.JLabel lbNit12;
    private javax.swing.JLabel lbNit2;
    private javax.swing.JLabel lbNit3;
    private javax.swing.JList lista;
    private javax.swing.JPanel pnlFormulario;
    private javax.swing.JPanel pnlMenu;
    private javax.swing.JScrollPane scrFormulario;
    private javax.swing.JTable tblEx1;
    private javax.swing.JTable tblEx2;
    private javax.swing.JTextField txtCie1;
    private javax.swing.JTextField txtCie2;
    private javax.swing.JTextField txtCie3;
    private javax.swing.JTextField txtCie4;
    private javax.swing.JTextField txtDiagnostico1;
    private javax.swing.JTextField txtDiagnostico2;
    private javax.swing.JTextField txtDiagnostico3;
    private javax.swing.JTextField txtDiagnostico4;
    private javax.swing.JTextArea txtDiagnosticoEspecializado;
    private javax.swing.JTextField txtEdad;
    private javax.swing.JTextArea txtEnfermedadActual;
    private javax.swing.JTextField txtFechaNacimiento;
    private javax.swing.JTextField txtGenero;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextArea txtMotivo;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNombreCompleto;
    private javax.swing.JTextField txtResponsable;
    private javax.swing.JTextArea txtRiesgosRecomendaciones;
    private javax.swing.JTextField txtTelefonoResponsable;
    private javax.swing.JTextArea txtTratamiento;
    // End of variables declaration//GEN-END:variables
}
