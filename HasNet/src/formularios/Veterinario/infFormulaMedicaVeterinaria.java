package formularios.Veterinario;

import clases.Instancias;
import clases.Medico.ndFormulaMedica;
import clases.Medico.ndTablaFormula;
import clases.Veterinario.ndConsulta;
import clases.Veterinario.ndFormulaVeterinaria;
import clases.Veterinario.ndHistoria;
import clases.Veterinario.ndMascota;
import clases.big;
import clases.metodosGenerales;
import Modelo.Terceros.ModeloContacto;
import formularios.Medico.buscMedicamentos;
import formularios.productos.buscMedidas;
import formularios.terceros.buscClientes;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.table.DefaultTableModel;

public class infFormulaMedicaVeterinaria extends javax.swing.JInternalFrame {

    metodosGenerales metodos = new metodosGenerales();
    private Instancias instancias;

    private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension dimBarra = null;
    DefaultTableModel modelo;
    private String proximoControl;

    public infFormulaMedicaVeterinaria() {

        initComponents();

        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
        dimBarra = Barra.getPreferredSize();
        Barra.setSize(0, 0);
        Barra.setPreferredSize(new Dimension(0, 0));
        setBorder(null);
        repaint();

        modelo = (DefaultTableModel) tblFormula.getModel();
        instancias = Instancias.getInstancias();

        txtFecha.setText(metodosGenerales.fecha());
        txtId.setText("" + instancias.getSql().getNumConsecutivo("FORMVET")[0]);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        popBorrar = new javax.swing.JMenuItem();
        buttonGroup1 = new javax.swing.ButtonGroup();
        scrFormulario = new javax.swing.JScrollPane();
        pnlFormulario = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblFormula = new javax.swing.JTable();
        lbRazon9 = new javax.swing.JLabel();
        txtPrescripcion = new javax.swing.JTextField();
        lbRazon11 = new javax.swing.JLabel();
        txtPosologia = new javax.swing.JTextField();
        lbRazon12 = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        lbRazon13 = new javax.swing.JLabel();
        txtMedida = new javax.swing.JTextField();
        lbRazon14 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtObservacion = new javax.swing.JTextArea();
        lbNit5 = new javax.swing.JLabel();
        rbtCarta = new javax.swing.JRadioButton();
        rbtMediacarta = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        btnBuscTerceros2 = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        txtRaza = new javax.swing.JTextField();
        lbNit1 = new javax.swing.JLabel();
        txtGenero = new javax.swing.JTextField();
        lbDireccion6 = new javax.swing.JLabel();
        txtReproductivo = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        lbDireccion4 = new javax.swing.JLabel();
        lbNit2 = new javax.swing.JLabel();
        txtHistoria = new javax.swing.JTextField();
        lbEmail1 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        lbDireccion5 = new javax.swing.JLabel();
        lbRazon = new javax.swing.JLabel();
        txtEdad = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        lbNit3 = new javax.swing.JLabel();
        txtCedula = new javax.swing.JTextField();
        lbNit4 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        lbDireccion9 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        txtNombrePropietario = new javax.swing.JTextField();
        lbRazon1 = new javax.swing.JLabel();
        lbFacturaNo = new javax.swing.JTextField();
        txtId = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lbRazon3 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();

        popBorrar.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        popBorrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/eliminar-cancelar-icono-4935-16.png"))); // NOI18N
        popBorrar.setText("Borrar");
        popBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                popBorrarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(popBorrar);

        setTitle("Formula Medica");

        pnlFormulario.setBackground(new java.awt.Color(255, 255, 255));

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        tblFormula.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        tblFormula.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Medicamento", "Posología", "Cantidad", "Medida"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblFormula.setComponentPopupMenu(jPopupMenu1);
        tblFormula.setName("Tabla Formulas"); // NOI18N
        tblFormula.setRowHeight(24);
        jScrollPane2.setViewportView(tblFormula);
        if (tblFormula.getColumnModel().getColumnCount() > 0) {
            tblFormula.getColumnModel().getColumn(2).setMinWidth(30);
            tblFormula.getColumnModel().getColumn(2).setPreferredWidth(100);
            tblFormula.getColumnModel().getColumn(2).setMaxWidth(150);
        }

        lbRazon9.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbRazon9.setText("Medicamento:");

        txtPrescripcion.setBackground(new java.awt.Color(255, 204, 204));
        txtPrescripcion.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtPrescripcion.setName("Prescripcion"); // NOI18N
        txtPrescripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrescripcionActionPerformed(evt);
            }
        });
        txtPrescripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPrescripcionKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPrescripcionKeyReleased(evt);
            }
        });

        lbRazon11.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbRazon11.setText("Posologia:");

        txtPosologia.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtPosologia.setName("Posologia"); // NOI18N
        txtPosologia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPosologiaKeyReleased(evt);
            }
        });

        lbRazon12.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbRazon12.setText("Cantidad:");

        txtCantidad.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtCantidad.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCantidad.setName("Posologia"); // NOI18N
        txtCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCantidadKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadKeyTyped(evt);
            }
        });

        lbRazon13.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbRazon13.setText("Medida:");

        txtMedida.setBackground(new java.awt.Color(255, 204, 204));
        txtMedida.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtMedida.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMedida.setName("Chip"); // NOI18N
        txtMedida.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMedidaKeyReleased(evt);
            }
        });

        lbRazon14.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbRazon14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbRazon14.setText("Observaciones");

        txtObservacion.setColumns(20);
        txtObservacion.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtObservacion.setLineWrap(true);
        txtObservacion.setRows(2);
        jScrollPane1.setViewportView(txtObservacion);

        lbNit5.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbNit5.setText("Tipo Impresión:");

        buttonGroup1.add(rbtCarta);
        rbtCarta.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        rbtCarta.setSelected(true);
        rbtCarta.setText("Carta");

        buttonGroup1.add(rbtMediacarta);
        rbtMediacarta.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        rbtMediacarta.setText("Media carta");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lbRazon9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbRazon11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(5, 5, 5)
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtPrescripcion)
                                    .addComponent(txtPosologia))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lbRazon12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbRazon13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(11, 11, 11)
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtMedida)
                                    .addComponent(txtCantidad, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addComponent(lbRazon14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbNit5)
                .addGap(18, 18, 18)
                .addComponent(rbtCarta)
                .addGap(10, 10, 10)
                .addComponent(rbtMediacarta)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbRazon9, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPrescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbRazon13, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMedida, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbRazon11, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPosologia, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbRazon12, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lbRazon14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rbtCarta)
                        .addComponent(rbtMediacarta))
                    .addComponent(lbNit5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        btnGuardar.setBackground(new java.awt.Color(46, 204, 113));
        btnGuardar.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
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

        btnBuscTerceros2.setBackground(new java.awt.Color(247, 220, 111));
        btnBuscTerceros2.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnBuscTerceros2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/imprimir.png"))); // NOI18N
        btnBuscTerceros2.setText("REIMPRIMIR");
        btnBuscTerceros2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscTerceros2.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnBuscTerceros2.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnBuscTerceros2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscTerceros2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnGuardar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBuscTerceros2, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnBuscTerceros2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mascota", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 0, 14))); // NOI18N

        txtRaza.setEditable(false);
        txtRaza.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtRaza.setName("Edad"); // NOI18N
        txtRaza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRazaActionPerformed(evt);
            }
        });
        txtRaza.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtRazaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRazaKeyTyped(evt);
            }
        });

        lbNit1.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbNit1.setText("Codigo:");

        txtGenero.setEditable(false);
        txtGenero.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtGenero.setName("Edad"); // NOI18N
        txtGenero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGeneroActionPerformed(evt);
            }
        });
        txtGenero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtGeneroKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtGeneroKeyTyped(evt);
            }
        });

        lbDireccion6.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lbDireccion6.setText("Raza:");

        txtReproductivo.setEditable(false);
        txtReproductivo.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtReproductivo.setName("Edad"); // NOI18N
        txtReproductivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtReproductivoActionPerformed(evt);
            }
        });
        txtReproductivo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtReproductivoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtReproductivoKeyTyped(evt);
            }
        });

        txtNombre.setEditable(false);
        txtNombre.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtNombre.setName("Nombre"); // NOI18N
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreKeyReleased(evt);
            }
        });

        lbDireccion4.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lbDireccion4.setText("Edad: ");

        lbNit2.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lbNit2.setText("Historia:");

        txtHistoria.setEditable(false);
        txtHistoria.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtHistoria.setName("Codigo"); // NOI18N
        txtHistoria.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtHistoriaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtHistoriaKeyTyped(evt);
            }
        });

        lbEmail1.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lbEmail1.setText("E.reproductivo:");

        txtCodigo.setEditable(false);
        txtCodigo.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtCodigo.setName("Codigo"); // NOI18N
        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoKeyTyped(evt);
            }
        });

        lbDireccion5.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lbDireccion5.setText("Genero:");

        lbRazon.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbRazon.setText("Nombre:");

        txtEdad.setEditable(false);
        txtEdad.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtEdad.setName("Edad"); // NOI18N
        txtEdad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEdadActionPerformed(evt);
            }
        });
        txtEdad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEdadKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEdadKeyTyped(evt);
            }
        });

        lbNit3.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbNit3.setText("Cedula:");

        txtCedula.setEditable(false);
        txtCedula.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtCedula.setName("Codigo"); // NOI18N
        txtCedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCedulaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCedulaKeyTyped(evt);
            }
        });

        lbNit4.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lbNit4.setText("Telefono:");

        txtTelefono.setEditable(false);
        txtTelefono.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtTelefono.setName("Codigo"); // NOI18N
        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyTyped(evt);
            }
        });

        lbDireccion9.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lbDireccion9.setText("Direccion:");

        txtDireccion.setEditable(false);
        txtDireccion.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtDireccion.setName("Edad"); // NOI18N
        txtDireccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDireccionActionPerformed(evt);
            }
        });
        txtDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDireccionKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDireccionKeyTyped(evt);
            }
        });

        txtNombrePropietario.setEditable(false);
        txtNombrePropietario.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtNombrePropietario.setName("Nombre"); // NOI18N
        txtNombrePropietario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombrePropietarioActionPerformed(evt);
            }
        });
        txtNombrePropietario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombrePropietarioKeyReleased(evt);
            }
        });

        lbRazon1.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbRazon1.setText("Nombre:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbNit1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbNit2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbDireccion6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(4, 4, 4)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtRaza, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                            .addComponent(txtCodigo, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtHistoria, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lbRazon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbDireccion5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNombre)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(txtGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(21, 21, 21)
                                        .addComponent(lbDireccion4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtEdad, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE))))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(lbEmail1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtReproductivo))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(lbNit3, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(lbNit4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbDireccion9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbRazon1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDireccion, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
                            .addComponent(txtNombrePropietario))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addComponent(lbRazon)
                    .addComponent(txtCodigo)
                    .addComponent(lbNit1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lbNit2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbDireccion6)
                            .addComponent(txtRaza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lbDireccion5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtHistoria, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtGenero)
                                .addComponent(lbDireccion4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtEdad, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbEmail1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtReproductivo))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lbNit3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtCedula)
                                .addComponent(lbRazon1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtNombrePropietario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbDireccion9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lbNit4)
                                .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
        );

        lbFacturaNo.setEditable(false);
        lbFacturaNo.setBackground(new java.awt.Color(204, 204, 204));
        lbFacturaNo.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbFacturaNo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lbFacturaNo.setText("F. Medica");
        lbFacturaNo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lbFacturaNoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                lbFacturaNoKeyTyped(evt);
            }
        });

        txtId.setEditable(false);
        txtId.setBackground(new java.awt.Color(255, 255, 255));
        txtId.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtId.setForeground(new java.awt.Color(255, 0, 0));
        txtId.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtId.setText("1");
        txtId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIdKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIdKeyTyped(evt);
            }
        });

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/anterior_2.png"))); // NOI18N
        jLabel9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Volver");
        jLabel11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });

        lbRazon3.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbRazon3.setText("Fecha:");

        txtFecha.setEditable(false);
        txtFecha.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtFecha.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFecha.setName("Fecha De Examen"); // NOI18N
        txtFecha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFechaKeyReleased(evt);
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
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addComponent(lbRazon3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbFacturaNo)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(lbFacturaNo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(0, 0, 0)
                                .addComponent(jLabel11))))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbRazon3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(15, 15, 15)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout pnlFormularioLayout = new javax.swing.GroupLayout(pnlFormulario);
        pnlFormulario.setLayout(pnlFormularioLayout);
        pnlFormularioLayout.setHorizontalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFormularioLayout.createSequentialGroup()
                        .addGap(0, 71, Short.MAX_VALUE)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 72, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlFormularioLayout.setVerticalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );

        scrFormulario.setViewportView(pnlFormulario);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(scrFormulario)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(scrFormulario)
                .addGap(4, 4, 4))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnGuardarKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnGuardarActionPerformed(null);
        }
    }//GEN-LAST:event_btnGuardarKeyReleased

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (tblFormula.getRowCount() < 1) {
            metodos.msgError(this, "Falta agregar la prescripcion y la posologia");
            return;
        }

        Object[] campos = {txtId};
        String faltantes = metodos.camposVacios(campos);
        Object[] campos2 = {txtHistoria, txtFecha};
        String faltantes2 = metodos.camposVacios(campos2);

        if (!faltantes.equals("")) {
            metodos.msgAdvertencia(this, "No puede continuar, faltan los siguientes campos: " + faltantes);
            return;
        } else if (!faltantes2.equals("")) {
            metodos.msgAdvertencia(this, "Faltan los siguientes campos: " + faltantes2);
        }
        if (metodos.msgPregunta(this, "¿Desea continuar?") == 0) {

            String id = "FORMVET-" + instancias.getSql().getNumConsecutivo("FORMVET")[0];

//            ndFormulaVeterinaria nodo = instancias.getSql().getDatosFormulaMedicaVeterinaria(id);
//
//            if (nodo.getId() != null) {
//                metodos.msgError(this, "La formula ya existe");
//                txtId.requestFocus();
//                return;
//            }
            Object[] vector = {id, instancias.getHistoria().getConsulta(), metodosGenerales.fecha(), instancias.getUsuario(),
                big.getMoneda("0"), txtObservacion.getText()};

            ndFormulaVeterinaria nodo = metodos.llenarFormulaVeterinaria(vector);
            if (!instancias.getSql().agregarFormulaMedicaVeterinaria(nodo)) {
                metodos.msgError(this, "Error al guardar la formula");
                return;
            }

            ndConsulta consulta = instancias.getSql().getDatosConsulta(instancias.getHistoria().getConsulta());
            String aux2 = "";
            for (int i = 0; i < tblFormula.getRowCount(); i++) {

                String valor1 = "", valor2 = "", valor3 = "", valor4 = "";

                try {
                    valor1 = tblFormula.getValueAt(i, 0).toString();
                } catch (Exception e) {
                }

                try {
                    valor2 = tblFormula.getValueAt(i, 1).toString();
                } catch (Exception e) {
                }

                try {
                    valor3 = tblFormula.getValueAt(i, 2).toString();
                } catch (Exception e) {
                }

                try {
                    valor4 = tblFormula.getValueAt(i, 3).toString();
                } catch (Exception e) {
                }

                Object[] vector2 = {id, valor1, valor2, big.getMoneda("0"), valor3, valor4};

                ndTablaFormula nodo2 = metodos.llenarTablaFormula(vector2);
                aux2 = aux2 + "Prescripcion: " + valor1 + "\tPosologia: " + valor2 + "\n";
                if (!instancias.getSql().agregarTablaFormulaVeterinaria(nodo2)) {
                    metodos.msgError(this, "Hubo un problema al guardar el TablaFormula");
                    return;
                }
            }

            instancias.getSql().modificarConsulta(consulta.getId(), consulta.getTratamiento() + ".  " + aux2);

            //CAMBIAR CONSECUTIVO FORMULA MEDICA
            if (!instancias.getSql().aumentarConsecutivo("FORMVET", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("FORMVET")[0]) + 1)) {
                metodos.msgError(this, "Hubo un problema al guardar en el consecutivo de la formula medica");
            }

            txtId.setText("" + instancias.getSql().getNumConsecutivo("FORMVET")[0]);

            limpiar();
            metodos.msgExito(this, "Formula medica registrado con éxito");

//            ndHistoria historia = instancias.getSql().getDatosHistoria(txtHistoria.getText());
//            String con[] = historia.getConsecutivos().split("&");
//            int aux = Integer.parseInt(con[2]);
//            aux++;
//            instancias.getSql().modificarConsecutivoHistoria(historia.getId(), con[0] + "&" + con[1] + "&" + aux + "&" + con[3]);
            if (metodos.msgPregunta(this, "¿Desea imprimir?") == 0) {
                String tipo = "";
                if (rbtCarta.isSelected()) {
                    tipo = "Completa";
                }
                instancias.getReporte().verFormulaMedicaVeterinaria(id, txtNombre.getText(),
                        txtGenero.getText(), txtRaza.getText(), txtEdad.getText(), txtCodigo.getText(), txtReproductivo.getText(),
                        txtCedula.getText(), txtNombrePropietario.getText(), txtTelefono.getText(), txtDireccion.getText(),
                        proximoControl, tipo, txtHistoria.getText());
            }

            try {
                instancias.getHistoria().setSelected(true);
            } catch (PropertyVetoException ex) {
//                Logger.getLogger(infHistoriaMedica.class.getName()).log(Level.SEVERE, null, ex);
            }

//                btnNuevoActionPerformed(evt);             
//            } catch (Exception e) {
//                metodos.msgError(this, "Hubo un problema al guardar el FormulaMedica");
//            }
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    public void limpiar() {
        txtPrescripcion.setText("");
        txtPosologia.setText("");
        int i, j = tblFormula.getRowCount();

        for (i = 0; i < j; i++) {
            modelo.removeRow(0);
        }
    }

    private void txtPrescripcionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrescripcionKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!txtPrescripcion.getText().equals("")) {
                txtPosologia.requestFocus();
            } else {
                ventanaMedicamentos(txtPrescripcion.getText());
            }
        } else {
            txtPrescripcion.setText("");
        }
    }//GEN-LAST:event_txtPrescripcionKeyReleased

    private void txtPosologiaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPosologiaKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtMedida.requestFocus();
        }
    }//GEN-LAST:event_txtPosologiaKeyReleased

    private void btnBuscTerceros2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscTerceros2ActionPerformed
        infReimpresiones reimpresion = new infReimpresiones();
        reimpresion.show();
        reimpresion.setNombre(txtNombre.getText());
        reimpresion.cargarRegistros(txtCodigo.getText(), "formulaMedicaVeterinaria", instancias);
    }//GEN-LAST:event_btnBuscTerceros2ActionPerformed

    private void popBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popBorrarActionPerformed
        if (tblFormula.getSelectedRow() > -1) {
            int fila = tblFormula.getSelectedRow();

            DefaultTableModel modelo = (DefaultTableModel) tblFormula.getModel();
            modelo.removeRow(fila);

        } else {
            metodos.msgAdvertencia(this, "Seleccione un item");
        }
    }//GEN-LAST:event_popBorrarActionPerformed

    private void txtPrescripcionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrescripcionKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrescripcionKeyPressed

    private void txtCantidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            if (txtPrescripcion.getText().equals("")) {
                metodos.msgError(this, "Ingrese una prescripción");
                return;
            }

            if (txtPosologia.getText().equals("")) {
                metodos.msgError(this, "Ingrese una posología");
                return;
            }

            if (txtMedida.getText().equals("")) {
                metodos.msgError(this, "Ingrese la medida de la prescripción");
                return;
            }

            if (txtCantidad.getText().equals("")) {
                metodos.msgError(this, "Ingrese una cantidad");
                return;
            }

            String codigo = "";
            for (int i = 0; i < tblFormula.getRowCount(); i++) {
                codigo = (String) tblFormula.getValueAt(i, 0);
                if (txtPrescripcion.getText().equals(codigo)) {
                    metodos.msgError(this, "Ya ingresaste esta prescripción");
                    txtPrescripcion.setText("");
                    txtPosologia.setText("");
                    txtMedida.setText("");
                    txtCantidad.setText("");
                    txtPrescripcion.requestFocus();
                    return;
                }
            }

            Object[] fila = {txtPrescripcion.getText(), txtPosologia.getText(), txtCantidad.getText(), txtMedida.getText()};

            DefaultTableModel modelo = (DefaultTableModel) tblFormula.getModel();
            modelo.addRow(fila);

            txtPrescripcion.setText("");
            txtPosologia.setText("");
            txtCantidad.setText("");
            txtMedida.setText("");
            txtPrescripcion.requestFocus();
        }
    }//GEN-LAST:event_txtCantidadKeyReleased

    private void txtCantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtCantidadKeyTyped

    private void txtPrescripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrescripcionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrescripcionActionPerformed

    private void txtMedidaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMedidaKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!txtMedida.getText().equals("")) {
                txtCantidad.requestFocus();
            } else {
                ventanaMedidas(txtMedida.getText());
            }
        } else {
            txtMedida.setText("");
        }
    }//GEN-LAST:event_txtMedidaKeyReleased

    private void txtRazaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRazaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRazaActionPerformed

    private void txtRazaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRazaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRazaKeyReleased

    private void txtRazaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRazaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRazaKeyTyped

    private void txtGeneroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGeneroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGeneroActionPerformed

    private void txtGeneroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGeneroKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGeneroKeyReleased

    private void txtGeneroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGeneroKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGeneroKeyTyped

    private void txtReproductivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtReproductivoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtReproductivoActionPerformed

    private void txtReproductivoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtReproductivoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtReproductivoKeyReleased

    private void txtReproductivoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtReproductivoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtReproductivoKeyTyped

    private void txtNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyReleased

    }//GEN-LAST:event_txtNombreKeyReleased

    private void txtHistoriaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHistoriaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHistoriaKeyReleased

    private void txtHistoriaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHistoriaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHistoriaKeyTyped

    private void txtCodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyReleased

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtNombre.requestFocus();
        }
    }//GEN-LAST:event_txtCodigoKeyReleased

    private void txtCodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyTyped

    }//GEN-LAST:event_txtCodigoKeyTyped

    private void txtEdadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEdadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEdadActionPerformed

    private void txtEdadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEdadKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEdadKeyReleased

    private void txtEdadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEdadKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEdadKeyTyped

    private void txtCedulaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCedulaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCedulaKeyReleased

    private void txtCedulaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCedulaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCedulaKeyTyped

    private void txtTelefonoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoKeyReleased

    private void txtTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoKeyTyped

    private void txtDireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDireccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDireccionActionPerformed

    private void txtDireccionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDireccionKeyReleased

    private void txtDireccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDireccionKeyTyped

    private void txtNombrePropietarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombrePropietarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombrePropietarioActionPerformed

    private void txtNombrePropietarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombrePropietarioKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombrePropietarioKeyReleased

    private void lbFacturaNoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lbFacturaNoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_lbFacturaNoKeyReleased

    private void lbFacturaNoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lbFacturaNoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_lbFacturaNoKeyTyped

    private void txtIdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdKeyReleased
        //        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        //            if (txtId.getText().equals("")) {
        //                if (txtNombre.getText().equals("")) {
        //                    metodos.msgError(null, "Debe seleccionar un tercero primero");
        //                    return;
        //                } else {
        //                    infBuscadorCliente buscador = new infBuscadorCliente();
        //                    buscador.cargarRegistros(txtNit.getText(), "oServicio", instancias);
        //                    buscador.setVisible(true);
        //                }
        //            } else {
        //                cargarOServicio(txtId.getText());
        //            }
        //        } else {
        //            limpiar(false);
        //        }
    }//GEN-LAST:event_txtIdKeyReleased

    private void txtIdKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtIdKeyTyped

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        try {
            instancias.getHistoria().setSelected(true);
            instancias.getMenu().cambiarTitulo("HISTORIA DE " + txtNombre.getText());
        } catch (PropertyVetoException ex) {
            Logger.getLogger(infHistoria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLabel9MouseClicked

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        try {
            instancias.getHistoria().setSelected(true);
            instancias.getMenu().cambiarTitulo("HISTORIA DE " + txtNombre.getText());
        } catch (PropertyVetoException ex) {
            Logger.getLogger(infHistoria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLabel11MouseClicked

    private void txtFechaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFechaKeyReleased

    }//GEN-LAST:event_txtFechaKeyReleased

    public void reimprimir(String consecutivo) {
        String tipo = "";
        if (rbtCarta.isSelected()) {
            tipo = "Completa";
        }

        instancias.getReporte().verFormulaMedicaVeterinaria(consecutivo, txtNombre.getText(),
                txtGenero.getText(), txtRaza.getText(), txtEdad.getText(), txtCodigo.getText(), txtReproductivo.getText(),
                txtCedula.getText(), txtNombrePropietario.getText(), txtTelefono.getText(), txtDireccion.getText(), "", tipo,
                txtHistoria.getText());
    }

    public void setInstancias(Instancias instancias) {
        this.instancias = instancias;
        txtFecha.setText(metodosGenerales.fecha());
        txtId.setText("" + instancias.getSql().getNumConsecutivo("FORMVET")[0]);
    }

    public void cargarFormulaMedica(String Id) {

        ndFormulaMedica nodo = instancias.getSql().getDatosFormulaMedica(Id);

        if (nodo.getId() != null) {
//            txtHistoria.setText(nodo.getHistoria());
            txtFecha.setText(nodo.getFecha());
            txtHistoria.requestFocus();
            return;
        }
    }

    public void nuevaFormula(ndHistoria nodo, String proximoControl) {

        txtObservacion.setText("");
        this.proximoControl = proximoControl;

        try {
            this.setSelected(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(infHistoria.class.getName()).log(Level.SEVERE, null, ex);
        }

        txtHistoria.setText(nodo.getId());
        ndMascota Mascota = instancias.getSql().getDatosMascota(nodo.getMascota());
        ModeloContacto cliente = instancias.getSql().getDatosTercero(Mascota.getCliente());

        if (Mascota.getCodigo() != null) {
            txtCodigo.setText(Mascota.getCodigo());
            txtNombre.setText(Mascota.getNombre());
            txtCodigo.setEditable(false);
            txtNombre.setText(Mascota.getNombre());
            txtRaza.setText(Mascota.getRaza());

//            txtEdad.setText(metodosGenerales.calcularEdad(Mascota.getEdad()).toString());
            String fecha = Mascota.getEdad();
            if (fecha.length() == 9) {
                fecha = "0" + fecha;
            }

            int año = metodosGenerales.calcularEdad(fecha);
            int meses = metodosGenerales.calcularEdadMeses(fecha);
            if (año > 0) {
                txtEdad.setText(año + " años y " + meses + " meses");
            } else {
                txtEdad.setText(meses + " meses");
            }

            txtReproductivo.setText(Mascota.getObservaciones());
            txtGenero.setText(Mascota.getGenero());
            txtCedula.setText(cliente.getId());
            txtNombrePropietario.setText(cliente.getNombre());
            txtTelefono.setText(cliente.getTelefono());
            txtDireccion.setText(cliente.getDireccion());

        }

        txtFecha.setText(metodosGenerales.fecha());
        txtId.setText("" + instancias.getSql().getNumConsecutivo("FORMVET")[0]);
        instancias.getMenu().cambiarTitulo("FORMULA MEDICA");
    }

    public void ventanaMedidas(String nit) {
        buscMedidas buscar = new buscMedidas(null, rootPaneCheckingEnabled);
        buscar.setLocationRelativeTo(null);
        instancias.setCampoActual(txtMedida);
        txtMedida.requestFocus();
        buscar.setInstancia(instancias);
        buscar.noEncontrado(nit);
        buscar.show();
    }

    public void ventanaMedicamentos(String nit) {
        buscMedicamentosVeterinario buscar = new buscMedicamentosVeterinario(instancias.getMenu(), rootPaneCheckingEnabled);
        buscar.setLocationRelativeTo(null);
        buscar.setInstancia(instancias);
        instancias.setBuscMedicamentosVeterinario(buscar);
        instancias.setCampoActual(txtPrescripcion);
        txtPrescripcion.requestFocus();
        buscar.noEncontrado(nit);
        buscar.show();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscTerceros2;
    private javax.swing.JButton btnGuardar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbDireccion4;
    private javax.swing.JLabel lbDireccion5;
    private javax.swing.JLabel lbDireccion6;
    private javax.swing.JLabel lbDireccion9;
    private javax.swing.JLabel lbEmail1;
    private javax.swing.JTextField lbFacturaNo;
    private javax.swing.JLabel lbNit1;
    private javax.swing.JLabel lbNit2;
    private javax.swing.JLabel lbNit3;
    private javax.swing.JLabel lbNit4;
    private javax.swing.JLabel lbNit5;
    private javax.swing.JLabel lbRazon;
    private javax.swing.JLabel lbRazon1;
    private javax.swing.JLabel lbRazon11;
    private javax.swing.JLabel lbRazon12;
    private javax.swing.JLabel lbRazon13;
    private javax.swing.JLabel lbRazon14;
    private javax.swing.JLabel lbRazon3;
    private javax.swing.JLabel lbRazon9;
    private javax.swing.JPanel pnlFormulario;
    private javax.swing.JMenuItem popBorrar;
    private javax.swing.JRadioButton rbtCarta;
    private javax.swing.JRadioButton rbtMediacarta;
    private javax.swing.JScrollPane scrFormulario;
    private javax.swing.JTable tblFormula;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtEdad;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtGenero;
    private javax.swing.JTextField txtHistoria;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtMedida;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNombrePropietario;
    private javax.swing.JTextArea txtObservacion;
    private javax.swing.JTextField txtPosologia;
    private javax.swing.JTextField txtPrescripcion;
    private javax.swing.JTextField txtRaza;
    private javax.swing.JTextField txtReproductivo;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables

    private void cargando(String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
