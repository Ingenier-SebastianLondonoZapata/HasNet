package formularios.Medico;

import clases.Instancias;
import clases.Medico.ndInformacionPrestador;
import clases.metodosGenerales;
import Modelo.Terceros.ModeloContacto;
import formularios.terceros.buscClientes;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;

public class infAutorizacionServicios extends javax.swing.JInternalFrame {

    metodosGenerales metodos = new metodosGenerales();
    private Instancias instancias;
    private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension dimBarra = null;
    DefaultTableModel modelo;

    public infAutorizacionServicios() {
        initComponents();

        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
        dimBarra = Barra.getPreferredSize();
        Barra.setSize(0, 0);
        Barra.setPreferredSize(new Dimension(0, 0));
        setBorder(null);
        repaint();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        popBorrar = new javax.swing.JMenuItem();
        scrFormulario = new javax.swing.JScrollPane();
        pnlFormulario = new javax.swing.JPanel();
        lbNit10 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lbCambiarTamano = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        lbRazon2 = new javax.swing.JLabel();
        txtNacimiento = new javax.swing.JTextField();
        txtTipoDocu = new javax.swing.JTextField();
        txtNombres = new javax.swing.JTextField();
        txtIdentificacion = new javax.swing.JTextField();
        lbRazon5 = new javax.swing.JLabel();
        lbRazon1 = new javax.swing.JLabel();
        lbRazon3 = new javax.swing.JLabel();
        lbRazon23 = new javax.swing.JLabel();
        txtEstado1 = new javax.swing.JTextField();
        lbRazon24 = new javax.swing.JLabel();
        txtNombres8 = new javax.swing.JTextField();
        lbRazon25 = new javax.swing.JLabel();
        cmbDepartamento2 = new javax.swing.JComboBox();
        cmbDepartamento3 = new javax.swing.JComboBox();
        lbRazon28 = new javax.swing.JLabel();
        lbRazon29 = new javax.swing.JLabel();
        txtNombres9 = new javax.swing.JTextField();
        lbRazon30 = new javax.swing.JLabel();
        txtEstado2 = new javax.swing.JTextField();
        lbRazon31 = new javax.swing.JLabel();
        cmbRegimen = new javax.swing.JComboBox();
        btnBuscTerceros = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        lbRazon9 = new javax.swing.JLabel();
        txtNombres1 = new javax.swing.JTextField();
        lbRazon10 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        txtNombres2 = new javax.swing.JTextField();
        lbRazon11 = new javax.swing.JLabel();
        lbRazon12 = new javax.swing.JLabel();
        txtNombres3 = new javax.swing.JTextField();
        lbRazon13 = new javax.swing.JLabel();
        txtNombres4 = new javax.swing.JTextField();
        lbRazon14 = new javax.swing.JLabel();
        txtNombres5 = new javax.swing.JTextField();
        lbRazon15 = new javax.swing.JLabel();
        lbRazon16 = new javax.swing.JLabel();
        txtNombres6 = new javax.swing.JTextField();
        cmbDepartamento = new javax.swing.JComboBox();
        lbRazon17 = new javax.swing.JLabel();
        cmbMunicipio = new javax.swing.JComboBox();
        lbRazon20 = new javax.swing.JLabel();
        lbRazon21 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        lbRazon22 = new javax.swing.JLabel();
        txtNombres7 = new javax.swing.JTextField();
        lbNit = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        lbRazon32 = new javax.swing.JLabel();
        cmbRegimen1 = new javax.swing.JComboBox();
        lbRazon33 = new javax.swing.JLabel();
        cmbRegimen2 = new javax.swing.JComboBox();
        lbRazon34 = new javax.swing.JLabel();
        cmbRegimen3 = new javax.swing.JComboBox();
        cmbRegimen4 = new javax.swing.JComboBox();
        lbRazon35 = new javax.swing.JLabel();
        cmbRegimen5 = new javax.swing.JComboBox();
        lbRazon36 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        lbRazon37 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        lbRazon38 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        lbRazon39 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        lbRazon40 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        lbRazon41 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCups = new javax.swing.JTable();
        lbRazon42 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        lbEmail18 = new javax.swing.JLabel();
        txtCie1 = new javax.swing.JTextField();
        txtDiagnostico1 = new javax.swing.JTextField();
        txtDiagnostico2 = new javax.swing.JTextField();
        txtCie2 = new javax.swing.JTextField();
        lbEmail19 = new javax.swing.JLabel();
        lbEmail26 = new javax.swing.JLabel();
        txtCie3 = new javax.swing.JTextField();
        txtDiagnostico3 = new javax.swing.JTextField();
        jTextField12 = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        lbEmail20 = new javax.swing.JLabel();
        txtDiagnostico4 = new javax.swing.JTextField();
        lbEmail21 = new javax.swing.JLabel();
        lbRazon43 = new javax.swing.JLabel();
        txtNombres10 = new javax.swing.JTextField();
        lbRazon44 = new javax.swing.JLabel();
        txtNombres11 = new javax.swing.JTextField();
        lbRazon45 = new javax.swing.JLabel();
        txtNombres12 = new javax.swing.JTextField();
        lbEmail22 = new javax.swing.JLabel();
        txtDiagnostico5 = new javax.swing.JTextField();
        lbEmail23 = new javax.swing.JLabel();
        txtNombres13 = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnBuscTerceros1 = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();

        popBorrar.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        popBorrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/eliminar-cancelar-icono-4935-16.png"))); // NOI18N
        popBorrar.setText("Borrar");
        popBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                popBorrarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(popBorrar);

        setTitle("Factura");

        pnlFormulario.setBackground(new java.awt.Color(255, 255, 255));

        lbNit10.setFont(new java.awt.Font("Bell MT", 1, 36)); // NOI18N
        lbNit10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit10.setText("AUTORIZACION SERVICIOS");

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
                .addGap(16, 16, 16))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(lbCambiarTamano)
                .addGap(31, 31, 31))
        );

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos Paciente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 14))); // NOI18N

        lbRazon2.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbRazon2.setText("Tipo De Documento:");

        txtNacimiento.setEditable(false);
        txtNacimiento.setName("Fecha De Nacimiento"); // NOI18N
        txtNacimiento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNacimientoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNacimientoKeyTyped(evt);
            }
        });

        txtTipoDocu.setEditable(false);
        txtTipoDocu.setName("Tipo De Documento"); // NOI18N
        txtTipoDocu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTipoDocuKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTipoDocuKeyTyped(evt);
            }
        });

        txtNombres.setEditable(false);
        txtNombres.setName("Nombre Y Apellidos"); // NOI18N
        txtNombres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombresActionPerformed(evt);
            }
        });
        txtNombres.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombresKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombresKeyTyped(evt);
            }
        });

        txtIdentificacion.setEditable(false);
        txtIdentificacion.setName("No. Identificacion"); // NOI18N
        txtIdentificacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIdentificacionKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIdentificacionKeyTyped(evt);
            }
        });

        lbRazon5.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbRazon5.setText("Fecha De Nacimiento:");

        lbRazon1.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbRazon1.setText("No. Identificación:");

        lbRazon3.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbRazon3.setText("Nombre Y Apellidos:");

        lbRazon23.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbRazon23.setText("Dirección:");

        txtEstado1.setEditable(false);
        txtEstado1.setName("Estado Civil"); // NOI18N
        txtEstado1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEstado1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEstado1KeyTyped(evt);
            }
        });

        lbRazon24.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbRazon24.setText("Telefono:");

        txtNombres8.setEditable(false);
        txtNombres8.setName("Nombre Y Apellidos"); // NOI18N
        txtNombres8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombres8ActionPerformed(evt);
            }
        });
        txtNombres8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombres8KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombres8KeyTyped(evt);
            }
        });

        lbRazon25.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbRazon25.setText("Departamento:");

        cmbDepartamento2.setEnabled(false);

        cmbDepartamento3.setEnabled(false);

        lbRazon28.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbRazon28.setText("Municipio:");

        lbRazon29.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbRazon29.setText("Telefono Celular:");

        txtNombres9.setEditable(false);
        txtNombres9.setName("Nombre Y Apellidos"); // NOI18N
        txtNombres9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombres9ActionPerformed(evt);
            }
        });
        txtNombres9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombres9KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombres9KeyTyped(evt);
            }
        });

        lbRazon30.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbRazon30.setText("Correo Electronico:");

        txtEstado2.setEditable(false);
        txtEstado2.setName("Estado Civil"); // NOI18N
        txtEstado2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEstado2KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEstado2KeyTyped(evt);
            }
        });

        lbRazon31.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbRazon31.setText("Cobertura Salud:");

        cmbRegimen.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Contributivo", "Subsidiado parcial", "Subsidiado total", "Población Pobre No Asegurada Con SISBEN", "Población Pobre No Asegurada Sin SISBEN", "Desplazado", "Plan adicional de salud", "Vinculado", "Particular", "Otro" }));
        cmbRegimen.setEnabled(false);
        cmbRegimen.setName("Regimen"); // NOI18N
        cmbRegimen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbRegimenKeyReleased(evt);
            }
        });

        btnBuscTerceros.setBackground(new java.awt.Color(204, 255, 204));
        btnBuscTerceros.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnBuscTerceros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cliente.png"))); // NOI18N
        btnBuscTerceros.setText("Nuevo Paciente");
        btnBuscTerceros.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscTerceros.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnBuscTerceros.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnBuscTerceros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscTercerosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lbRazon30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbRazon24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbRazon23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbRazon1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbRazon3, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                        .addComponent(lbRazon25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(lbRazon5, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(57, 57, 57)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtEstado1, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtNombres, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel11Layout.createSequentialGroup()
                            .addComponent(txtIdentificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(33, 33, 33)
                            .addComponent(lbRazon2, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtTipoDocu, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnBuscTerceros)
                        .addGroup(jPanel11Layout.createSequentialGroup()
                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(cmbDepartamento2, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtNombres8, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtEstado2, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(30, 30, 30)
                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lbRazon28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbRazon29, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                                .addComponent(lbRazon31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(cmbDepartamento3, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtNombres9)
                                .addComponent(cmbRegimen, 0, 243, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbRazon1)
                    .addComponent(txtIdentificacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbRazon2)
                    .addComponent(txtTipoDocu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbRazon3)
                    .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbRazon5)
                    .addComponent(txtNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbRazon23)
                    .addComponent(txtEstado1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombres8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbRazon24)
                    .addComponent(txtNombres9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbRazon29))
                .addGap(4, 4, 4)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cmbDepartamento3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbRazon28))
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cmbDepartamento2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbRazon25)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbRazon31)
                    .addComponent(cmbRegimen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEstado2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbRazon30))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBuscTerceros, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Información Del Prestador", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 14))); // NOI18N

        lbRazon9.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbRazon9.setText("Nombre:");

        txtNombres1.setName("Nombre Prestador"); // NOI18N
        txtNombres1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombres1ActionPerformed(evt);
            }
        });
        txtNombres1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombres1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombres1KeyTyped(evt);
            }
        });

        lbRazon10.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbRazon10.setText("Tipo:");

        jRadioButton1.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setSelected(true);
        jRadioButton1.setText("NIT");

        jRadioButton2.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("CC");

        txtNombres2.setName("Nombre Y Apellidos"); // NOI18N
        txtNombres2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombres2ActionPerformed(evt);
            }
        });
        txtNombres2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombres2KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombres2KeyTyped(evt);
            }
        });

        lbRazon11.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbRazon11.setText("Numero Id:");
        lbRazon11.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lbRazon11KeyReleased(evt);
            }
        });

        lbRazon12.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbRazon12.setText("Codigo:");

        txtNombres3.setName("Codigo Prestador"); // NOI18N
        txtNombres3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombres3ActionPerformed(evt);
            }
        });
        txtNombres3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombres3KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombres3KeyTyped(evt);
            }
        });

        lbRazon13.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbRazon13.setText("Dirección:");

        txtNombres4.setName("Direccion Prestador"); // NOI18N
        txtNombres4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombres4ActionPerformed(evt);
            }
        });
        txtNombres4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombres4KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombres4KeyTyped(evt);
            }
        });

        lbRazon14.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbRazon14.setText("Telefono:");

        txtNombres5.setName("Indicativo Telefono Prestador"); // NOI18N
        txtNombres5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombres5ActionPerformed(evt);
            }
        });
        txtNombres5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombres5KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombres5KeyTyped(evt);
            }
        });

        lbRazon15.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbRazon15.setText("Indicativo: ");

        lbRazon16.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbRazon16.setText("Numero:");

        txtNombres6.setName("Numero Prestador"); // NOI18N
        txtNombres6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombres6ActionPerformed(evt);
            }
        });
        txtNombres6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombres6KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombres6KeyTyped(evt);
            }
        });

        cmbDepartamento.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbDepartamentoItemStateChanged(evt);
            }
        });

        lbRazon17.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbRazon17.setText("Departamento:");

        lbRazon20.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbRazon20.setText("Municipio:");

        lbRazon21.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbRazon21.setText("Entidad a la que se le solicita autorización:");

        jTextField3.setName("Entidad"); // NOI18N

        lbRazon22.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbRazon22.setText("Codigo:");

        txtNombres7.setName("Codigo Entidad"); // NOI18N
        txtNombres7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombres7ActionPerformed(evt);
            }
        });
        txtNombres7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombres7KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombres7KeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbRazon21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)
                        .addComponent(lbRazon22, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNombres7, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbRazon9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbRazon12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbRazon14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbRazon17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE))
                        .addGap(63, 63, 63)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(lbRazon15, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(4, 4, 4)
                                    .addComponent(txtNombres5, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(txtNombres3)
                                .addComponent(txtNombres1, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cmbDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbRazon20, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                            .addComponent(lbRazon16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbRazon13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbRazon10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jRadioButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbRazon11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNombres2, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE))
                            .addComponent(txtNombres4)
                            .addComponent(txtNombres6)
                            .addComponent(cmbMunicipio, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNombres1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombres2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbRazon9)
                        .addComponent(lbRazon10)
                        .addComponent(jRadioButton1)
                        .addComponent(jRadioButton2)
                        .addComponent(lbRazon11)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNombres3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbRazon12)
                        .addComponent(lbRazon13)
                        .addComponent(txtNombres4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNombres6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbRazon16)
                        .addComponent(lbRazon15)
                        .addComponent(lbRazon14))
                    .addComponent(txtNombres5, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbRazon17)
                        .addComponent(lbRazon20)
                        .addComponent(cmbMunicipio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbRazon21)
                    .addComponent(lbRazon22)
                    .addComponent(txtNombres7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12))
        );

        lbNit.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbNit.setText("Numero Solicitud:");

        txtId.setEditable(false);
        txtId.setName("Incapacidad Numero"); // NOI18N
        txtId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdActionPerformed(evt);
            }
        });
        txtId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIdKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIdKeyTyped(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Información De La Atención Y Servicios Solicitados", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 14))); // NOI18N

        lbRazon32.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbRazon32.setText("Origen de la atención:");

        cmbRegimen1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Enfermedad General", "Enfermedad Profesional", "Accidente de trabajo", "Accidente de tránsito", "Evento Catastrófico" }));
        cmbRegimen1.setName("Regimen"); // NOI18N
        cmbRegimen1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbRegimen1ItemStateChanged(evt);
            }
        });
        cmbRegimen1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbRegimen1KeyReleased(evt);
            }
        });

        lbRazon33.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbRazon33.setText("Tipo de servicios solicitados:");

        cmbRegimen2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Posterior a la atención inicial de urgencias", "Servicios electivos" }));
        cmbRegimen2.setName("Regimen"); // NOI18N
        cmbRegimen2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbRegimen2KeyReleased(evt);
            }
        });

        lbRazon34.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbRazon34.setText("Prioridad de la atención:");

        cmbRegimen3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Prioritaria", "No prioritaria" }));
        cmbRegimen3.setName("Regimen"); // NOI18N
        cmbRegimen3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbRegimen3KeyReleased(evt);
            }
        });

        cmbRegimen4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Accidente de tránsito" }));
        cmbRegimen4.setEnabled(false);
        cmbRegimen4.setName("Regimen"); // NOI18N
        cmbRegimen4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbRegimen4KeyReleased(evt);
            }
        });

        lbRazon35.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbRazon35.setText("Ubicación del Paciente al momento de la solicitud de autorizacion:");

        cmbRegimen5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Consulta Externa", "Urgencias", "Hospitalización" }));
        cmbRegimen5.setName("Regimen"); // NOI18N
        cmbRegimen5.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbRegimen5ItemStateChanged(evt);
            }
        });
        cmbRegimen5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbRegimen5KeyReleased(evt);
            }
        });

        lbRazon36.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbRazon36.setText("Servicio:");

        jTextField6.setEditable(false);

        lbRazon37.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbRazon37.setText("Cama:");

        jTextField7.setEditable(false);

        lbRazon38.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbRazon38.setText("Manejo Integral Según Guía:");

        lbRazon39.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbRazon39.setText("CUP:");

        jTextField9.setEditable(false);
        jTextField9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField9KeyReleased(evt);
            }
        });

        lbRazon40.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbRazon40.setText("Cantidad:");

        jTextField10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField10KeyTyped(evt);
            }
        });

        lbRazon41.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbRazon41.setText("Descripción:");

        jButton1.setText("Agregar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        tblCups.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CUPS", "Cant.", "Descripción"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCups.setComponentPopupMenu(jPopupMenu1);
        jScrollPane1.setViewportView(tblCups);
        if (tblCups.getColumnModel().getColumnCount() > 0) {
            tblCups.getColumnModel().getColumn(0).setMinWidth(100);
            tblCups.getColumnModel().getColumn(0).setPreferredWidth(100);
            tblCups.getColumnModel().getColumn(0).setMaxWidth(100);
            tblCups.getColumnModel().getColumn(1).setMinWidth(50);
            tblCups.getColumnModel().getColumn(1).setPreferredWidth(50);
            tblCups.getColumnModel().getColumn(1).setMaxWidth(50);
        }

        lbRazon42.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbRazon42.setText("Justificación clínica:");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "CIE 10", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 14))); // NOI18N

        lbEmail18.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbEmail18.setText("Principal:");

        txtCie1.setEditable(false);
        txtCie1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCie1KeyReleased(evt);
            }
        });

        txtDiagnostico1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDiagnostico1KeyReleased(evt);
            }
        });

        txtDiagnostico2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDiagnostico2KeyReleased(evt);
            }
        });

        txtCie2.setEditable(false);
        txtCie2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCie2FocusGained(evt);
            }
        });
        txtCie2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCie2KeyReleased(evt);
            }
        });

        lbEmail19.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbEmail19.setText("Relacionado:");

        lbEmail26.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbEmail26.setText("Relacionado:");

        txtCie3.setEditable(false);
        txtCie3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCie3FocusGained(evt);
            }
        });
        txtCie3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCie3KeyReleased(evt);
            }
        });

        txtDiagnostico3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDiagnostico3KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lbEmail19, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbEmail26, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbEmail18, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtCie3)
                    .addComponent(txtCie1)
                    .addComponent(txtCie2, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtDiagnostico2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDiagnostico1)
                    .addComponent(txtDiagnostico3))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbEmail18)
                    .addComponent(txtDiagnostico1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCie1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbEmail19)
                    .addComponent(txtDiagnostico2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCie2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCie3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbEmail26)
                    .addComponent(txtDiagnostico3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTextField12.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField12KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lbRazon42, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbRazon32, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbRazon33))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(cmbRegimen2, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lbRazon34)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbRegimen3, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(cmbRegimen1, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cmbRegimen4, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(lbRazon35, javax.swing.GroupLayout.PREFERRED_SIZE, 499, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbRegimen5, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbRazon39, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbRazon40))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField10, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                                    .addComponent(jTextField9)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lbRazon36)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField6))
                            .addComponent(lbRazon38, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField8)
                            .addComponent(jTextField12)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lbRazon37)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lbRazon41)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField11))))
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbRazon32)
                    .addComponent(cmbRegimen1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbRegimen4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbRazon33)
                    .addComponent(cmbRegimen2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbRazon34)
                    .addComponent(cmbRegimen3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbRazon35)
                    .addComponent(cmbRegimen5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbRazon36)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbRazon37)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbRazon38))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbRazon39)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbRazon40)
                    .addComponent(lbRazon41)
                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbRazon42)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Información De La Persona Que Solicita", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 14))); // NOI18N

        lbEmail20.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbEmail20.setText("Nombre del que solicita:");

        txtDiagnostico4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDiagnostico4KeyReleased(evt);
            }
        });

        lbEmail21.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbEmail21.setText("Teléfono:");

        lbRazon43.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbRazon43.setText("Indicativo: ");

        txtNombres10.setName("Nombre Y Apellidos"); // NOI18N
        txtNombres10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombres10ActionPerformed(evt);
            }
        });
        txtNombres10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombres10KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombres10KeyTyped(evt);
            }
        });

        lbRazon44.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbRazon44.setText("Numero:");

        txtNombres11.setName("Nombre Y Apellidos"); // NOI18N
        txtNombres11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombres11ActionPerformed(evt);
            }
        });
        txtNombres11.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombres11KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombres11KeyTyped(evt);
            }
        });

        lbRazon45.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbRazon45.setText("Extensión:");

        txtNombres12.setName("Nombre Y Apellidos"); // NOI18N
        txtNombres12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombres12ActionPerformed(evt);
            }
        });
        txtNombres12.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombres12KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombres12KeyTyped(evt);
            }
        });

        lbEmail22.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbEmail22.setText("Cargo y actividad:");

        txtDiagnostico5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDiagnostico5KeyReleased(evt);
            }
        });

        lbEmail23.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbEmail23.setText("Teléfono celular:");

        txtNombres13.setName("Nombre Y Apellidos"); // NOI18N
        txtNombres13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombres13ActionPerformed(evt);
            }
        });
        txtNombres13.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombres13KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombres13KeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lbEmail23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbEmail22, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbEmail20, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addComponent(lbEmail21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbRazon43, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDiagnostico4)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(txtNombres13, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(txtNombres10, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbRazon44)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNombres11, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbRazon45)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNombres12))
                    .addComponent(txtDiagnostico5))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbEmail20)
                    .addComponent(txtDiagnostico4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbEmail21)
                    .addComponent(lbRazon43)
                    .addComponent(txtNombres10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbRazon44)
                    .addComponent(txtNombres11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbRazon45)
                    .addComponent(txtNombres12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbEmail22)
                    .addComponent(txtDiagnostico5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbEmail23)
                    .addComponent(txtNombres13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        btnGuardar.setBackground(new java.awt.Color(0, 153, 153));
        btnGuardar.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
        btnGuardar.setText("GUARDAR       ");
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnGuardar.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnLimpiar.setBackground(new java.awt.Color(102, 102, 255));
        btnLimpiar.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnLimpiar.setForeground(new java.awt.Color(255, 255, 255));
        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/limpiar.png"))); // NOI18N
        btnLimpiar.setText("LIMPIAR          ");
        btnLimpiar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLimpiar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnLimpiar.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        btnBuscTerceros1.setBackground(new java.awt.Color(204, 204, 204));
        btnBuscTerceros1.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnBuscTerceros1.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscTerceros1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/imprimir.png"))); // NOI18N
        btnBuscTerceros1.setText("IMPRIMIR");
        btnBuscTerceros1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscTerceros1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnBuscTerceros1.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnBuscTerceros1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscTerceros1ActionPerformed(evt);
            }
        });

        btnEliminar.setBackground(new java.awt.Color(204, 204, 204));
        btnEliminar.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/borrar2.png"))); // NOI18N
        btnEliminar.setText("ANULAR");
        btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnEliminar.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlFormularioLayout = new javax.swing.GroupLayout(pnlFormulario);
        pnlFormulario.setLayout(pnlFormularioLayout);
        pnlFormularioLayout.setHorizontalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(pnlFormularioLayout.createSequentialGroup()
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lbNit10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(pnlFormularioLayout.createSequentialGroup()
                            .addComponent(lbNit)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlFormularioLayout.createSequentialGroup()
                        .addComponent(btnGuardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnLimpiar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscTerceros1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlFormularioLayout.setVerticalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbNit10, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNit)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlFormularioLayout.createSequentialGroup()
                        .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnGuardar)
                            .addComponent(btnLimpiar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(1, 1, 1))
                    .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnBuscTerceros1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEliminar)))
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
            .addComponent(scrFormulario, javax.swing.GroupLayout.DEFAULT_SIZE, 589, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lbCambiarTamanoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbCambiarTamanoMouseClicked
        boolean seVe = instancias.getMenu().getSeVeElMenu();

        if (seVe) {
            instancias.getMenu().ocultarMenu("");
        } else {
            instancias.getMenu().expandirMenu();
        }

    }//GEN-LAST:event_lbCambiarTamanoMouseClicked

    private void lbCambiarTamanoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbCambiarTamanoMouseEntered

    }//GEN-LAST:event_lbCambiarTamanoMouseEntered

    private void lbCambiarTamanoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbCambiarTamanoMouseExited

    }//GEN-LAST:event_lbCambiarTamanoMouseExited

    private void jPanel3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseEntered

    }//GEN-LAST:event_jPanel3MouseEntered

    private void txtNacimientoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNacimientoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNacimientoKeyReleased

    private void txtNacimientoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNacimientoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNacimientoKeyTyped

    private void txtTipoDocuKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTipoDocuKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTipoDocuKeyReleased

    private void txtTipoDocuKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTipoDocuKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTipoDocuKeyTyped

    private void txtNombresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombresActionPerformed

    private void txtNombresKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombresKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombresKeyReleased

    private void txtNombresKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombresKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombresKeyTyped

    private void txtIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdActionPerformed

    }//GEN-LAST:event_txtIdActionPerformed

    private void txtIdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdKeyReleased

    }//GEN-LAST:event_txtIdKeyReleased

    private void txtIdKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdKeyTyped

    private void txtIdentificacionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdentificacionKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            setPaciente(txtIdentificacion.getText());
        } else {
            limpiar();
        }
    }//GEN-LAST:event_txtIdentificacionKeyReleased

    private void txtIdentificacionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdentificacionKeyTyped

    }//GEN-LAST:event_txtIdentificacionKeyTyped

    private void txtNombres1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombres1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombres1ActionPerformed

    private void txtNombres1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombres1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombres1KeyReleased

    private void txtNombres1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombres1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombres1KeyTyped

    private void txtNombres2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombres2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombres2ActionPerformed

    private void txtNombres2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombres2KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombres2KeyReleased

    private void txtNombres2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombres2KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombres2KeyTyped

    private void txtNombres3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombres3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombres3ActionPerformed

    private void txtNombres3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombres3KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombres3KeyReleased

    private void txtNombres3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombres3KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombres3KeyTyped

    private void txtNombres4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombres4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombres4ActionPerformed

    private void txtNombres4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombres4KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombres4KeyReleased

    private void txtNombres4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombres4KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombres4KeyTyped

    private void txtNombres5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombres5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombres5ActionPerformed

    private void txtNombres5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombres5KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombres5KeyReleased

    private void txtNombres5KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombres5KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombres5KeyTyped

    private void txtNombres6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombres6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombres6ActionPerformed

    private void txtNombres6KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombres6KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombres6KeyReleased

    private void txtNombres6KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombres6KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombres6KeyTyped

    private void txtNombres7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombres7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombres7ActionPerformed

    private void txtNombres7KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombres7KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombres7KeyReleased

    private void txtNombres7KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombres7KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombres7KeyTyped

    private void txtEstado1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEstado1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEstado1KeyReleased

    private void txtEstado1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEstado1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEstado1KeyTyped

    private void txtNombres8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombres8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombres8ActionPerformed

    private void txtNombres8KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombres8KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombres8KeyReleased

    private void txtNombres8KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombres8KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombres8KeyTyped

    private void txtNombres9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombres9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombres9ActionPerformed

    private void txtNombres9KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombres9KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombres9KeyReleased

    private void txtNombres9KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombres9KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombres9KeyTyped

    private void txtEstado2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEstado2KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEstado2KeyReleased

    private void txtEstado2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEstado2KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEstado2KeyTyped

    private void cmbRegimenKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbRegimenKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

        }
    }//GEN-LAST:event_cmbRegimenKeyReleased

    private void cmbRegimen1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbRegimen1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbRegimen1KeyReleased

    private void cmbRegimen2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbRegimen2KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbRegimen2KeyReleased

    private void cmbRegimen3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbRegimen3KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbRegimen3KeyReleased

    private void cmbRegimen4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbRegimen4KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbRegimen4KeyReleased

    private void cmbRegimen5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbRegimen5KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbRegimen5KeyReleased

    private void txtCie1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCie1KeyReleased

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

    private void txtCie2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCie2FocusGained
        txtDiagnostico2.requestFocus();
    }//GEN-LAST:event_txtCie2FocusGained

    private void txtCie2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCie2KeyReleased

    }//GEN-LAST:event_txtCie2KeyReleased

    private void txtCie3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCie3FocusGained
        txtDiagnostico3.requestFocus();
    }//GEN-LAST:event_txtCie3FocusGained

    private void txtCie3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCie3KeyReleased

    }//GEN-LAST:event_txtCie3KeyReleased

    private void txtDiagnostico3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiagnostico3KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            ventanaCies(txtDiagnostico3.getText(), txtDiagnostico3);
        } else {
            txtCie3.setText("");
        }
    }//GEN-LAST:event_txtDiagnostico3KeyReleased

    private void txtDiagnostico4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiagnostico4KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiagnostico4KeyReleased

    private void txtNombres10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombres10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombres10ActionPerformed

    private void txtNombres10KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombres10KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombres10KeyReleased

    private void txtNombres10KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombres10KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombres10KeyTyped

    private void txtNombres11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombres11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombres11ActionPerformed

    private void txtNombres11KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombres11KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombres11KeyReleased

    private void txtNombres11KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombres11KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombres11KeyTyped

    private void txtNombres12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombres12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombres12ActionPerformed

    private void txtNombres12KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombres12KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombres12KeyReleased

    private void txtNombres12KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombres12KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombres12KeyTyped

    private void txtDiagnostico5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiagnostico5KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiagnostico5KeyReleased

    private void txtNombres13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombres13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombres13ActionPerformed

    private void txtNombres13KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombres13KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombres13KeyReleased

    private void txtNombres13KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombres13KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombres13KeyTyped

    private void btnBuscTercerosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscTercerosActionPerformed
        ventanaTerceros("");
    }//GEN-LAST:event_btnBuscTercerosActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiar();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void jTextField9KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField9KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                jTextField12.setText(instancias.getSql().getNombreCup(jTextField9.getText()));
            } catch (Exception e) {
                jTextField12.setText("");
            }
        }
    }//GEN-LAST:event_jTextField9KeyReleased

    private void jTextField12KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField12KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            ventanaCups(jTextField12.getText());
        } else {
            jTextField9.setText("");
        }
    }//GEN-LAST:event_jTextField12KeyReleased

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        Object[] campos = {txtNombres1, txtNombres2, txtNombres3, txtNombres4, txtNombres5, txtNombres6, txtNombres7, jTextField3};
        String faltantes = metodos.camposVacios(campos);
        String tipo = "NIT";
        if (!faltantes.equals("")) {
            metodos.msgAdvertencia(this, "No puede continuar, faltan los siguientes campos: " + faltantes);
            return;
        }
        if (jRadioButton2.isSelected()) {
            tipo = "CC";
        }
        if (metodos.msgPregunta(this, "¿Desea continuar?") == 0) {
            Object[] vector = {"1", txtNombres1.getText(), tipo, txtNombres2.getText(), txtNombres3.getText(), txtNombres4.getText(), txtNombres5.getText(),
                txtNombres6.getText(), cmbDepartamento.getSelectedItem(), cmbMunicipio.getSelectedItem(), jTextField3.getText(), txtNombres7.getText(), instancias.getUsuario()};

            ndInformacionPrestador nodo = metodos.llenarInformacionPrestador(vector);
            if (!instancias.getSql().modificarInformacionPrestador(nodo)) {
                metodos.msgError(this, "Hubo un problema al modificar la información del prestador");
                return;
            }
            String id = "ASERV-" + instancias.getSql().getNumConsecutivo("ASERV")[0];
            Object[] vector2 = {id, txtIdentificacion.getText(), cmbRegimen1.getSelectedItem(), cmbRegimen4.getSelectedItem(),
                cmbRegimen2.getSelectedItem(), cmbRegimen3.getSelectedItem(), cmbRegimen5.getSelectedItem(), jTextField6.getText(), jTextField7.getText(), jTextField8.getText(),
                cupTabla(0), cupTabla(1), cupTabla(2), cupTabla(3), cupTabla(4), cupTabla(5), cupTabla(6), cupTabla(7), cupTabla(8), cupTabla(9), cupTabla(10), cupTabla(11), cupTabla(12), cupTabla(13),
                cupTabla(14), cupTabla(15), cupTabla(16), cupTabla(17), cupTabla(18), cupTabla(19), jTextArea1.getText(), txtCie1.getText(), txtCie2.getText(), txtCie3.getText(),
                txtDiagnostico4.getText(), txtNombres10.getText(), txtNombres11.getText(), txtNombres12.getText(), txtDiagnostico5.getText(), txtNombres13.getText(),
                metodosGenerales.fecha(), metodosGenerales.hora(), instancias.getUsuario()};

            if (!instancias.getSql().agregarAutorizacionServicios(vector2)) {
                metodos.msgError(this, "Hubo un problema al guardar la autorizacion de servicios");
                return;
            }

            if (!instancias.getSql().aumentarConsecutivo("ASERV", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("ASERV")[0]) + 1)) {
                metodos.msgError(this, "Hubo un problema al guardar en el consecutivo de la autorizacion de servicios");
            }

            instancias.getReporte().verAutorizacionServicios(id);

            limpiar();

            metodos.msgExito(this, "Autorizacion de servicios agregada con éxito");
        }
    }//GEN-LAST:event_btnGuardarActionPerformed
    private String cupTabla(int r) {
        try {
            String aux;
            aux = tblCups.getValueAt(r, 0).toString();
            aux = aux + "&" + tblCups.getValueAt(r, 1).toString();
            aux = aux + "&" + tblCups.getValueAt(r, 2).toString();
            return aux;
        } catch (Exception e) {
            return "";
        }
    }
    private void cmbRegimen1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbRegimen1ItemStateChanged
        if (cmbRegimen1.getSelectedIndex() == 3) {
            cmbRegimen4.setEnabled(true);
        } else {
            cmbRegimen4.setEnabled(false);
            cmbRegimen4.setSelectedIndex(0);
        }
    }//GEN-LAST:event_cmbRegimen1ItemStateChanged

    private void cmbRegimen5ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbRegimen5ItemStateChanged
        if (cmbRegimen5.getSelectedIndex() == 3) {
            jTextField6.setEditable(true);
            jTextField7.setEditable(true);
        } else {
            jTextField6.setEditable(false);
            jTextField7.setEditable(false);

            jTextField6.setText("");
            jTextField7.setText("");
        }
    }//GEN-LAST:event_cmbRegimen5ItemStateChanged

    private void jTextField10KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField10KeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_jTextField10KeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (!jTextField9.getText().equals("") && !jTextField10.getText().equals("")) {
            if (tblCups.getRowCount() > 19) {
                metodos.msgError(this, "Solo se permiten 20 cups por autorización");
                return;
            }
            for (int i = 0; i < tblCups.getRowCount(); i++) {
                if (tblCups.getValueAt(i, 0).equals(jTextField9.getText())) {
                    metodos.msgError(this, "Ya a ingresado este cup");
                    return;
                }
            }
            ((DefaultTableModel) tblCups.getModel()).addRow(new Object[]{jTextField9.getText(), jTextField10.getText(), jTextField11.getText()});
            jTextField9.setText("");
            jTextField10.setText("");
            jTextField11.setText("");
            jTextField12.setText("");
        } else {
            metodos.msgError(this, "Debe ingresar un cup con su respectiva cantidad");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void popBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popBorrarActionPerformed

        if (tblCups.getSelectedRow() > -1) {
            int fila = tblCups.getSelectedRow();

            DefaultTableModel modelo = (DefaultTableModel) tblCups.getModel();
            modelo.removeRow(fila);

        } else {
            metodos.msgAdvertencia(this, "Seleccione un cup");
        }
    }//GEN-LAST:event_popBorrarActionPerformed

    private void btnBuscTerceros1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscTerceros1ActionPerformed
        if (txtIdentificacion.getText().equals("")) {
            metodos.msgError(this, "No ha seleccionado un paciente");
            return;
        }
        infReimpresionExamenes reimpresion = new infReimpresionExamenes();
        reimpresion.cargarRegistros(txtIdentificacion.getText(), "autorizacion", instancias);
        reimpresion.show();

        //instancias.getReporte().verAutorizacionServicios("ASERV-201");
    }//GEN-LAST:event_btnBuscTerceros1ActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        if (txtIdentificacion.getText().equals("")) {
            metodos.msgError(this, "No ha seleccionado un paciente");
            return;
        }
        infReimpresionExamenes reimpresion = new infReimpresionExamenes();
        reimpresion.cargarRegistros(txtIdentificacion.getText(), "autorizacionAnular", instancias);
        reimpresion.show();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void cmbDepartamentoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbDepartamentoItemStateChanged
        consultarMunicipios(cmbDepartamento.getSelectedItem().toString());
    }//GEN-LAST:event_cmbDepartamentoItemStateChanged

    private void lbRazon11KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lbRazon11KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtNombres2.requestFocus();
        }
    }//GEN-LAST:event_lbRazon11KeyReleased
    public void reimprimir(String consecutivo) {
        instancias.getReporte().verAutorizacionServicios(consecutivo);
    }

    public void consultarMunicipios(String departamento) {
        Object[][] mun = instancias.getSql().getMunicipios(departamento);

        cmbMunicipio.removeAllItems();
        cmbMunicipio.addItem(" ");
        for (int i = 0; i < mun.length; i++) {
            if (null != mun[i][0]) {
                cmbMunicipio.addItem(mun[i][0]);
            }
        }
    }

    public void anular(String consecutivo) {
        instancias.getSql().anularAutorizacion(consecutivo);
        metodos.msgExito(this, "Autorizacion de servicio anulada con exito");
    }

    public void setInstancias(Instancias instancias) {
        this.instancias = instancias;

        Object[][] dep = instancias.getSql().getDepartamentos();
        cmbDepartamento2.removeAllItems();
        for (int i = 0; i < dep.length; i++) {
            cmbDepartamento.addItem(dep[i][0]);
            cmbDepartamento2.addItem(dep[i][0]);
        }

        try {
            ndInformacionPrestador nodo = instancias.getSql().getDatosInformacionPrestador();
            txtNombres1.setText(nodo.getNombre());
            if (nodo.getTipo().equals("NIT")) {
                jRadioButton1.setSelected(true);
            } else {
                jRadioButton2.setSelected(true);
            }
            txtNombres2.setText(nodo.getNumeroId());
            txtNombres3.setText(nodo.getCodigoHabilitador());
            txtNombres4.setText(nodo.getDireccion());
            txtNombres5.setText(nodo.getIndicativo());
            txtNombres6.setText(nodo.getNumero());
            cmbDepartamento.setSelectedItem(nodo.getDepartamento());
            cmbMunicipio.setSelectedItem(nodo.getMunicipio());
            jTextField3.setText(nodo.getEntidad());
            txtNombres7.setText(nodo.getCodigoEntidad());

            txtId.setText((String) instancias.getSql().getNumConsecutivo("ASERV")[0]);
        } catch (Exception e) {
            System.out.println("error al leer información del prestador de servicio.");
        }

    }

    public void ventanaCups(String codigo) {
        buscCups buscar = new buscCups(null, rootPaneCheckingEnabled);
        buscar.setLocationRelativeTo(null);
        instancias.setBuscCups(buscar);
        instancias.setCampoActual(jTextField9);
        jTextField9.requestFocus();
        buscar.noEncontrado(codigo.replace("'", "//"));
        buscar.show();
    }

    public void setPaciente(String paciente) {
        System.out.println(paciente);
        ModeloContacto Paciente = instancias.getSql().getDatosTercero(paciente);
        System.out.println(Paciente.getId());
        if (Paciente.getId() == null) {
            ventanaTerceros(paciente);
        } else {
            if (Paciente.isActivo()) {
                metodos.msgError(null, "Este cliente esta inactivado");
                lbRazon11.requestFocus();
                return;
            }
            txtNombres.setText(Paciente.getNombre());
            txtTipoDocu.setText(Paciente.getTipo());
            txtNacimiento.setText(metodos.fecha(Paciente.getNacimiento()));
            txtEstado1.setText(Paciente.getDireccion());
            txtNombres8.setText(Paciente.getTelefono());
            txtNombres9.setText(Paciente.getCelular());
            cmbDepartamento2.setSelectedItem(Paciente.getDepartamento());
            cmbDepartamento3.setSelectedItem(Paciente.getCiudad());
            txtEstado2.setText(Paciente.getEmail());
            cmbRegimen.setSelectedItem(Paciente.getRegimen());
        }
    }

    public void ventanaTerceros(String nit) {
        buscClientes buscar = new buscClientes(instancias.getMenu(), rootPaneCheckingEnabled, false, null, "");
        buscar.setLocationRelativeTo(null);
        instancias.setBusClientes(buscar);
        instancias.setCampoActual(txtIdentificacion);
        txtId.requestFocus();
        buscar.noEncontrado(nit);
        buscar.show();
    }

    public void ventanaCies(String codigo, JTextField campo) {
        buscCies buscar = new buscCies(null, rootPaneCheckingEnabled);
        buscar.setOpc(2);
        buscar.setLocationRelativeTo(null);
        instancias.setBuscCies(buscar);
        instancias.setCampoActual(campo);
        campo.requestFocus();
        buscar.noEncontrado(codigo.replace("'", "//"));
        buscar.show();
    }

    public void setCie(JTextField campo, String codigo) {
        try {
            if (campo == txtDiagnostico1) {
                txtCie1.setText(codigo);
                txtCie2.requestFocus();
                txtDiagnostico1.setText(instancias.getSql().getNombreCie(codigo));
            }
            if (campo == txtDiagnostico2) {
                txtCie2.setText(codigo);
                txtCie3.requestFocus();
                txtDiagnostico2.setText(instancias.getSql().getNombreCie(codigo));
            }
            if (campo == txtDiagnostico3) {
                txtCie3.setText(codigo);
                txtDiagnostico3.setText(instancias.getSql().getNombreCie(codigo));
            }
        } catch (Exception e) {
            campo.setText("");
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscTerceros;
    private javax.swing.JButton btnBuscTerceros1;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cmbDepartamento;
    private javax.swing.JComboBox cmbDepartamento2;
    private javax.swing.JComboBox cmbDepartamento3;
    private javax.swing.JComboBox cmbMunicipio;
    private javax.swing.JComboBox cmbRegimen;
    private javax.swing.JComboBox cmbRegimen1;
    private javax.swing.JComboBox cmbRegimen2;
    private javax.swing.JComboBox cmbRegimen3;
    private javax.swing.JComboBox cmbRegimen4;
    private javax.swing.JComboBox cmbRegimen5;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JLabel lbCambiarTamano;
    private javax.swing.JLabel lbEmail18;
    private javax.swing.JLabel lbEmail19;
    private javax.swing.JLabel lbEmail20;
    private javax.swing.JLabel lbEmail21;
    private javax.swing.JLabel lbEmail22;
    private javax.swing.JLabel lbEmail23;
    private javax.swing.JLabel lbEmail26;
    private javax.swing.JLabel lbNit;
    private javax.swing.JLabel lbNit10;
    private javax.swing.JLabel lbRazon1;
    private javax.swing.JLabel lbRazon10;
    private javax.swing.JLabel lbRazon11;
    private javax.swing.JLabel lbRazon12;
    private javax.swing.JLabel lbRazon13;
    private javax.swing.JLabel lbRazon14;
    private javax.swing.JLabel lbRazon15;
    private javax.swing.JLabel lbRazon16;
    private javax.swing.JLabel lbRazon17;
    private javax.swing.JLabel lbRazon2;
    private javax.swing.JLabel lbRazon20;
    private javax.swing.JLabel lbRazon21;
    private javax.swing.JLabel lbRazon22;
    private javax.swing.JLabel lbRazon23;
    private javax.swing.JLabel lbRazon24;
    private javax.swing.JLabel lbRazon25;
    private javax.swing.JLabel lbRazon28;
    private javax.swing.JLabel lbRazon29;
    private javax.swing.JLabel lbRazon3;
    private javax.swing.JLabel lbRazon30;
    private javax.swing.JLabel lbRazon31;
    private javax.swing.JLabel lbRazon32;
    private javax.swing.JLabel lbRazon33;
    private javax.swing.JLabel lbRazon34;
    private javax.swing.JLabel lbRazon35;
    private javax.swing.JLabel lbRazon36;
    private javax.swing.JLabel lbRazon37;
    private javax.swing.JLabel lbRazon38;
    private javax.swing.JLabel lbRazon39;
    private javax.swing.JLabel lbRazon40;
    private javax.swing.JLabel lbRazon41;
    private javax.swing.JLabel lbRazon42;
    private javax.swing.JLabel lbRazon43;
    private javax.swing.JLabel lbRazon44;
    private javax.swing.JLabel lbRazon45;
    private javax.swing.JLabel lbRazon5;
    private javax.swing.JLabel lbRazon9;
    private javax.swing.JPanel pnlFormulario;
    private javax.swing.JMenuItem popBorrar;
    private javax.swing.JScrollPane scrFormulario;
    private javax.swing.JTable tblCups;
    private javax.swing.JTextField txtCie1;
    private javax.swing.JTextField txtCie2;
    private javax.swing.JTextField txtCie3;
    private javax.swing.JTextField txtDiagnostico1;
    private javax.swing.JTextField txtDiagnostico2;
    private javax.swing.JTextField txtDiagnostico3;
    private javax.swing.JTextField txtDiagnostico4;
    private javax.swing.JTextField txtDiagnostico5;
    private javax.swing.JTextField txtEstado1;
    private javax.swing.JTextField txtEstado2;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtIdentificacion;
    private javax.swing.JTextField txtNacimiento;
    private javax.swing.JTextField txtNombres;
    private javax.swing.JTextField txtNombres1;
    private javax.swing.JTextField txtNombres10;
    private javax.swing.JTextField txtNombres11;
    private javax.swing.JTextField txtNombres12;
    private javax.swing.JTextField txtNombres13;
    private javax.swing.JTextField txtNombres2;
    private javax.swing.JTextField txtNombres3;
    private javax.swing.JTextField txtNombres4;
    private javax.swing.JTextField txtNombres5;
    private javax.swing.JTextField txtNombres6;
    private javax.swing.JTextField txtNombres7;
    private javax.swing.JTextField txtNombres8;
    private javax.swing.JTextField txtNombres9;
    private javax.swing.JTextField txtTipoDocu;
    // End of variables declaration//GEN-END:variables

    private void limpiar() {
        /*for (int x = 0; x < jPanel1.getComponentCount(); x++) {
         if (jPanel1.getComponent(x) instanceof JTextField) {
         JTextField textField = (JTextField) jPanel1.getComponent(x);
         textField.setText("");
         } else if (jPanel1.getComponent(x) instanceof JComboBox) {
         JComboBox textField = (JComboBox) jPanel1.getComponent(x);
         textField.setSelectedIndex(0);
         }
         }*/

        for (int x = 0; x < jPanel11.getComponentCount(); x++) {
            if (jPanel11.getComponent(x) instanceof JTextField) {
                JTextField textField = (JTextField) jPanel11.getComponent(x);
                textField.setText("");
            } else if (jPanel11.getComponent(x) instanceof JComboBox) {
                JComboBox textField = (JComboBox) jPanel11.getComponent(x);
                textField.setSelectedIndex(0);
            }
        }

        for (int x = 0; x < jPanel2.getComponentCount(); x++) {
            if (jPanel2.getComponent(x) instanceof JTextField) {
                JTextField textField = (JTextField) jPanel2.getComponent(x);
                textField.setText("");
            } else if (jPanel2.getComponent(x) instanceof JComboBox) {
                JComboBox textField = (JComboBox) jPanel2.getComponent(x);
                textField.setSelectedIndex(0);
            }
        }

        jTextArea1.setText("");

        for (int x = 0; x < jPanel4.getComponentCount(); x++) {
            if (jPanel4.getComponent(x) instanceof JTextField) {
                JTextField textField = (JTextField) jPanel4.getComponent(x);
                textField.setText("");
            } else if (jPanel4.getComponent(x) instanceof JComboBox) {
                JComboBox textField = (JComboBox) jPanel4.getComponent(x);
                textField.setSelectedIndex(0);
            }
        }

        for (int x = 0; x < jPanel5.getComponentCount(); x++) {
            if (jPanel5.getComponent(x) instanceof JTextField) {
                JTextField textField = (JTextField) jPanel5.getComponent(x);
                textField.setText("");
            } else if (jPanel5.getComponent(x) instanceof JComboBox) {
                JComboBox textField = (JComboBox) jPanel5.getComponent(x);
                textField.setSelectedIndex(0);
            }
        }

        try {
            DefaultTableModel model = (DefaultTableModel) tblCups.getModel();
            while (true) {
                model.removeRow(0);
            }
        } catch (Exception e) {
        }

        txtId.setText((String) instancias.getSql().getNumConsecutivo("ASERV")[0]);

    }
}
