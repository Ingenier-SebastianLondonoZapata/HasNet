package formularios.Veterinario;

import clases.Instancias;
import Utilidades.BaseDatos.SQL;
import clases.Veterinario.ndHistoria;
import clases.Veterinario.ndMascota;
import clases.Veterinario.ndConstanteHospitalizacion;
import clases.Veterinario.ndTablaMedicamentos;
import clases.big;
import clases.cambiarColorTabla;
import clases.metodosGenerales;
import clases.productos.ndProducto;
import Modelo.Terceros.ModeloContacto;
import Vista.Productos.VistaBuscadorProductos;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class infHospitalizacion extends javax.swing.JInternalFrame {

    metodosGenerales metodos = new metodosGenerales();
    private Instancias instancias;
    private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();

    String idHospitalizacion = "", simbolo = "";
    DefaultTableModel modelo;
    DefaultTableModel modeloPro;
    Object[] datos, posologias;

    public infHospitalizacion() {
        initComponents();

        tblMedicamentosPro.setDefaultRenderer(Object.class, new cambiarColorTabla(25, 0));

        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
        Barra.setSize(0, 0);
        Barra.setPreferredSize(new Dimension(0, 0));
        setBorder(null);
        repaint();

        modelo = (DefaultTableModel) tblProductos.getModel();
        modeloPro = (DefaultTableModel) tblMedicamentosPro.getModel();

        instancias = Instancias.getInstancias();
        txtFecha.setText(metodosGenerales.fecha());
        listaNotasMedicas.setSelectedIndex(0);
        txtCodMed.setVisible(false);

        this.simbolo = instancias.getSimbolo();

        consultarMaestros();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popMedicamentos = new javax.swing.JPopupMenu();
        popBorrar = new javax.swing.JMenuItem();
        popProductos = new javax.swing.JPopupMenu();
        popBorrar1 = new javax.swing.JMenuItem();
        scrFormulario = new javax.swing.JScrollPane();
        pnlFormulario = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        lbNit = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        lbRazon = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        lbDireccion1 = new javax.swing.JLabel();
        txtEspecie = new javax.swing.JTextField();
        lbTelefono = new javax.swing.JLabel();
        txtRaza = new javax.swing.JTextField();
        lbDireccion = new javax.swing.JLabel();
        txtEdad = new javax.swing.JTextField();
        lbNit1 = new javax.swing.JLabel();
        txtCedula = new javax.swing.JTextField();
        lbRazon1 = new javax.swing.JLabel();
        txtNombreCliente = new javax.swing.JTextField();
        lbNit2 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        lbNit3 = new javax.swing.JLabel();
        txtCelular = new javax.swing.JTextField();
        lbNit8 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lbNit9 = new javax.swing.JLabel();
        cmbConsultas = new javax.swing.JComboBox();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblMedicamentosPro = new javax.swing.JTable();
        lbRazon21 = new javax.swing.JLabel();
        txtMedicamento1 = new javax.swing.JTextField();
        btnBusProd1 = new javax.swing.JButton();
        btnGuardar2 = new javax.swing.JButton();
        btnModificar2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtPosologia = new javax.swing.JTextArea();
        lbNit6 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lbNit5 = new javax.swing.JLabel();
        txtHoraInicio = new javax.swing.JTextField();
        lbNit4 = new javax.swing.JLabel();
        txtCant = new javax.swing.JTextField();
        btnGuardar4 = new javax.swing.JButton();
        lbNit7 = new javax.swing.JLabel();
        txtCada = new javax.swing.JTextField();
        txtCodMed = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        listaNotasMedicas = new javax.swing.JList();
        jPanel5 = new javax.swing.JPanel();
        btnGuardar3 = new javax.swing.JButton();
        txtOrina = new javax.swing.JTextField();
        lbRazon12 = new javax.swing.JLabel();
        txtApetito = new javax.swing.JTextField();
        txtDefeca = new javax.swing.JTextField();
        lbRazon11 = new javax.swing.JLabel();
        lbRazon10 = new javax.swing.JLabel();
        txtMucosas = new javax.swing.JTextField();
        txtResp = new javax.swing.JTextField();
        lbRazon7 = new javax.swing.JLabel();
        lbRazon9 = new javax.swing.JLabel();
        txtTemp = new javax.swing.JTextField();
        txtCardiaca = new javax.swing.JTextField();
        lbRazon6 = new javax.swing.JLabel();
        lbRazon8 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        lbRazon13 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtNotaMedica = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        btnGuardar1 = new javax.swing.JButton();
        lbCodigo = new javax.swing.JLabel();
        txtCodProd = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblProductos = new javax.swing.JTable();
        btnBusVendedor1 = new javax.swing.JButton();
        btnModificar3 = new javax.swing.JButton();

        popBorrar.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        popBorrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/eliminar-cancelar-icono-4935-16.png"))); // NOI18N
        popBorrar.setText("Borrar");
        popBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                popBorrarActionPerformed(evt);
            }
        });
        popMedicamentos.add(popBorrar);

        popBorrar1.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        popBorrar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/eliminar-cancelar-icono-4935-16.png"))); // NOI18N
        popBorrar1.setText("Borrar");
        popBorrar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                popBorrar1ActionPerformed(evt);
            }
        });
        popProductos.add(popBorrar1);

        setTitle("Factura");

        pnlFormulario.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        lbNit.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit.setText("Codigo: ");

        txtCodigo.setEditable(false);
        txtCodigo.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtCodigo.setName("Codigo"); // NOI18N

        lbRazon.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbRazon.setText("Nombre:");

        txtNombre.setEditable(false);
        txtNombre.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtNombre.setName("Nombre"); // NOI18N
        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });

        lbDireccion1.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion1.setText("Especie:");

        txtEspecie.setEditable(false);
        txtEspecie.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtEspecie.setName("Edad"); // NOI18N
        txtEspecie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEspecieActionPerformed(evt);
            }
        });
        txtEspecie.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEspecieKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEspecieKeyTyped(evt);
            }
        });

        lbTelefono.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTelefono.setText("Raza:");

        txtRaza.setEditable(false);
        txtRaza.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        lbDireccion.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion.setText("Edad:");

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

        lbNit1.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit1.setText("CC/Nit:");

        txtCedula.setEditable(false);
        txtCedula.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtCedula.setName("Codigo"); // NOI18N

        lbRazon1.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbRazon1.setText("Nombre:");

        txtNombreCliente.setEditable(false);
        txtNombreCliente.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtNombreCliente.setName("Nombre"); // NOI18N
        txtNombreCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreClienteActionPerformed(evt);
            }
        });

        lbNit2.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbNit2.setText("Teléfono:");

        txtTelefono.setEditable(false);
        txtTelefono.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtTelefono.setName("Codigo"); // NOI18N

        lbNit3.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbNit3.setText("Celular:");

        txtCelular.setEditable(false);
        txtCelular.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtCelular.setName("Codigo"); // NOI18N

        lbNit8.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbNit8.setText("Fecha Ingreso:");

        txtFecha.setEditable(false);
        txtFecha.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Volver");
        jLabel11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/anterior_2.png"))); // NOI18N
        jLabel9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });

        lbNit9.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit9.setText("Relacionar consultar:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lbNit, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbRazon1, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                            .addComponent(lbRazon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNombreCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                            .addComponent(txtNombre)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lbNit1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lbNit9, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbConsultas, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbNit2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbDireccion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                    .addComponent(txtEdad))
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbDireccion1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbNit3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtCelular, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                    .addComponent(txtEspecie))
                .addGap(32, 32, 32)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lbNit8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtFecha))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lbTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtRaza, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel11)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbNit1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbNit3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbNit2, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtCedula, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                                .addComponent(lbRazon1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lbNit8, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(1, 1, 1)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbNit, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbRazon, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbDireccion1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEspecie, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtRaza, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbNit9, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                            .addComponent(cmbConsultas))
                        .addGap(5, 5, 5))))
        );

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        tblMedicamentosPro.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        tblMedicamentosPro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Hora"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblMedicamentosPro.setRowHeight(40);
        tblMedicamentosPro.getTableHeader().setReorderingAllowed(false);
        tblMedicamentosPro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMedicamentosProMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblMedicamentosPro);
        if (tblMedicamentosPro.getColumnModel().getColumnCount() > 0) {
            tblMedicamentosPro.getColumnModel().getColumn(0).setMinWidth(50);
            tblMedicamentosPro.getColumnModel().getColumn(0).setPreferredWidth(50);
            tblMedicamentosPro.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        lbRazon21.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbRazon21.setText("Medicamento:");

        txtMedicamento1.setBackground(new java.awt.Color(255, 204, 204));
        txtMedicamento1.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtMedicamento1.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtMedicamento1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMedicamento1ActionPerformed(evt);
            }
        });
        txtMedicamento1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMedicamento1KeyReleased(evt);
            }
        });

        btnBusProd1.setBackground(new java.awt.Color(204, 204, 204));
        btnBusProd1.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnBusProd1.setForeground(new java.awt.Color(255, 255, 255));
        btnBusProd1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar1.png"))); // NOI18N
        btnBusProd1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnBusProd1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBusProd1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnBusProd1.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnBusProd1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBusProd1ActionPerformed(evt);
            }
        });

        btnGuardar2.setBackground(new java.awt.Color(46, 204, 113));
        btnGuardar2.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnGuardar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
        btnGuardar2.setText("GUARDAR");
        btnGuardar2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar2.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnGuardar2.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnGuardar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardar2ActionPerformed(evt);
            }
        });

        btnModificar2.setBackground(new java.awt.Color(255, 255, 255));
        btnModificar2.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnModificar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/actualizar.png"))); // NOI18N
        btnModificar2.setBorder(null);
        btnModificar2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnModificar2.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnModificar2.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnModificar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificar2ActionPerformed(evt);
            }
        });

        txtPosologia.setColumns(20);
        txtPosologia.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtPosologia.setLineWrap(true);
        txtPosologia.setRows(2);
        txtPosologia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPosologiaKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(txtPosologia);

        lbNit6.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbNit6.setText("Posología:");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        lbNit5.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit5.setText("Hora inicio:");

        txtHoraInicio.setBackground(new java.awt.Color(255, 255, 204));
        txtHoraInicio.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtHoraInicio.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtHoraInicio.setName("Codigo"); // NOI18N
        txtHoraInicio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtHoraInicioKeyReleased(evt);
            }
        });

        lbNit4.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit4.setText("Cant aplicar:");

        txtCant.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtCant.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCant.setName("Codigo"); // NOI18N
        txtCant.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCantKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantKeyTyped(evt);
            }
        });

        btnGuardar4.setBackground(new java.awt.Color(204, 204, 204));
        btnGuardar4.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnGuardar4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/listado.png"))); // NOI18N
        btnGuardar4.setText("CARGAR");
        btnGuardar4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar4.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnGuardar4.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnGuardar4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardar4ActionPerformed(evt);
            }
        });

        lbNit7.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit7.setText("Cada/Hora:");

        txtCada.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtCada.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCada.setName("Codigo"); // NOI18N
        txtCada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCadaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCadaKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lbNit7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbNit4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                    .addComponent(lbNit5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtHoraInicio, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                    .addComponent(txtCant)
                    .addComponent(txtCada))
                .addGap(18, 18, 18)
                .addComponent(btnGuardar4, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(btnGuardar4, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtHoraInicio)
                    .addComponent(lbNit5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtCant)
                    .addComponent(lbNit4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbNit7, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCada, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        txtCodMed.setBackground(new java.awt.Color(255, 204, 204));
        txtCodMed.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtCodMed.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtCodMed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodMedActionPerformed(evt);
            }
        });
        txtCodMed.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodMedKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1227, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnGuardar2, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbRazon21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbNit6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtMedicamento1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBusProd1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2))
                        .addGap(43, 43, 43)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCodMed, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnModificar2)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCodMed, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnBusProd1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                            .addComponent(txtMedicamento1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnModificar2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(lbRazon21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(4, 4, 4)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbNit6, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE)
                        .addGap(6, 6, 6)))
                .addComponent(btnGuardar2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Medicamentos", jPanel2);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        listaNotasMedicas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        listaNotasMedicas.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "HOY - NUEVO" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        listaNotasMedicas.setToolTipText("");
        listaNotasMedicas.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listaNotasMedicasValueChanged(evt);
            }
        });
        jScrollPane10.setViewportView(listaNotasMedicas);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        btnGuardar3.setBackground(new java.awt.Color(46, 204, 113));
        btnGuardar3.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnGuardar3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
        btnGuardar3.setText("GUARDAR");
        btnGuardar3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar3.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnGuardar3.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnGuardar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardar3ActionPerformed(evt);
            }
        });

        txtOrina.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtOrina.setName("Orina"); // NOI18N

        lbRazon12.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbRazon12.setText("Apetito:");

        txtApetito.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtApetito.setName("Apetito"); // NOI18N

        txtDefeca.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtDefeca.setName("Defeca"); // NOI18N

        lbRazon11.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbRazon11.setText("Defeca:");

        lbRazon10.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbRazon10.setText("Orina:");

        txtMucosas.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtMucosas.setName("Mucosas"); // NOI18N

        txtResp.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtResp.setName("F Respiratoria"); // NOI18N

        lbRazon7.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbRazon7.setText("Frec. Resp:");

        lbRazon9.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbRazon9.setText("Mucosas:");

        txtTemp.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtTemp.setName("Temp"); // NOI18N

        txtCardiaca.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtCardiaca.setName("F Cardiaca"); // NOI18N

        lbRazon6.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbRazon6.setText("Frec. Cardiaca:");

        lbRazon8.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbRazon8.setText("Temperatura:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnGuardar3, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbRazon8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbRazon6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtCardiaca)
                    .addComponent(txtTemp, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbRazon9, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbRazon7, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtResp, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMucosas, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbRazon11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbRazon10, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtOrina)
                    .addComponent(txtDefeca, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(lbRazon12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtApetito, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(65, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbRazon6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(txtCardiaca, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTemp, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbRazon9, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMucosas, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbRazon8, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbRazon10, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtOrina, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lbRazon7, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtResp, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbRazon11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtDefeca, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbRazon12, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtApetito, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnGuardar3)
                .addContainerGap())
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        lbRazon13.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbRazon13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbRazon13.setText("NOTA");

        txtNotaMedica.setColumns(20);
        txtNotaMedica.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtNotaMedica.setRows(5);
        txtNotaMedica.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jScrollPane1.setViewportView(txtNotaMedica);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(lbRazon13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbRazon13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(26, 26, 26))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 621, Short.MAX_VALUE)
                        .addGap(24, 24, 24))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jTabbedPane1.addTab("Notas medicas", jPanel7);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

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

        lbCodigo.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbCodigo.setText("Codigo:");

        txtCodProd.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        txtCodProd.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtCodProd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtCodProdMouseClicked(evt);
            }
        });
        txtCodProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodProdActionPerformed(evt);
            }
        });
        txtCodProd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodProdKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodProdKeyReleased(evt);
            }
        });

        tblProductos.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        tblProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO", "Descripción", "Valor/Unit", "Cant", "Total", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblProductos.setComponentPopupMenu(popProductos);
        tblProductos.setRowHeight(24);
        tblProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductosMouseClicked(evt);
            }
        });
        tblProductos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblProductosKeyReleased(evt);
            }
        });
        jScrollPane5.setViewportView(tblProductos);
        if (tblProductos.getColumnModel().getColumnCount() > 0) {
            tblProductos.getColumnModel().getColumn(0).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(0).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(2).setMinWidth(80);
            tblProductos.getColumnModel().getColumn(2).setPreferredWidth(100);
            tblProductos.getColumnModel().getColumn(2).setMaxWidth(130);
            tblProductos.getColumnModel().getColumn(3).setMinWidth(40);
            tblProductos.getColumnModel().getColumn(3).setPreferredWidth(70);
            tblProductos.getColumnModel().getColumn(3).setMaxWidth(100);
            tblProductos.getColumnModel().getColumn(4).setMinWidth(80);
            tblProductos.getColumnModel().getColumn(4).setPreferredWidth(100);
            tblProductos.getColumnModel().getColumn(4).setMaxWidth(130);
            tblProductos.getColumnModel().getColumn(5).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(5).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(5).setMaxWidth(0);
        }

        btnBusVendedor1.setBackground(new java.awt.Color(204, 204, 204));
        btnBusVendedor1.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnBusVendedor1.setForeground(new java.awt.Color(255, 255, 255));
        btnBusVendedor1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar1.png"))); // NOI18N
        btnBusVendedor1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnBusVendedor1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBusVendedor1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnBusVendedor1.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnBusVendedor1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBusVendedor1ActionPerformed(evt);
            }
        });

        btnModificar3.setBackground(new java.awt.Color(255, 255, 255));
        btnModificar3.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnModificar3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/actualizar.png"))); // NOI18N
        btnModificar3.setBorder(null);
        btnModificar3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnModificar3.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnModificar3.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnModificar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificar3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lbCodigo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCodProd, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBusVendedor1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnModificar3))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1227, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnGuardar1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCodProd, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                            .addComponent(lbCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                            .addComponent(btnBusVendedor1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(btnModificar3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 547, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardar1)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Productos", jPanel3);

        javax.swing.GroupLayout pnlFormularioLayout = new javax.swing.GroupLayout(pnlFormulario);
        pnlFormulario.setLayout(pnlFormularioLayout);
        pnlFormularioLayout.setHorizontalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addGap(10, 10, 10))
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlFormularioLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlFormularioLayout.setVerticalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1)
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
            .addGroup(layout.createSequentialGroup()
                .addComponent(scrFormulario, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void txtEdadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEdadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEdadActionPerformed

    private void txtEdadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEdadKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEdadKeyReleased

    private void txtEdadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEdadKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEdadKeyTyped

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        try {
            if (cmbConsultas.getSelectedIndex() != 0) {
                instancias.getSql().asignarConsulta(idHospitalizacion, cmbConsultas.getSelectedItem().toString().split(" - ")[0]);
            } else {
                metodos.msgAdvertenciaAjustado(this, "Sin historia relacionada");
            }

            instancias.getIngresoHospitalizacion();
            instancias.getIngresoHospitalizacion().setSelected(true);
            instancias.getMenu().cambiarTitulo("INGRESO HOSPITALIZACIÓN");
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jLabel9MouseClicked

    public void limpiarPantalla() {
        for (int x = 0; x < jPanel4.getComponentCount(); x++) {
            if (jPanel4.getComponent(x) instanceof JTextField) {
                JTextField textField = (JTextField) jPanel4.getComponent(x);
                textField.setText("");
            }
        }

    }

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        try {
            if (cmbConsultas.getSelectedIndex() != 0) {
                instancias.getSql().asignarConsulta(idHospitalizacion, cmbConsultas.getSelectedItem().toString().split(" - ")[0]);
            } else {
                metodos.msgAdvertenciaAjustado(this, "Sin historia relacionada");
            }

            instancias.getIngresoHospitalizacion();
            instancias.getIngresoHospitalizacion().setSelected(true);
            instancias.getMenu().cambiarTitulo("INGRESO HOSPITALIZACIÓN");
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jLabel11MouseClicked

    private void popBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popBorrarActionPerformed
//        if (tblFormula.getSelectedRow() > -1) {
//            int fila = tblFormula.getSelectedRow();
//
//            if (tblFormula.getValueAt(fila, 28).equals("Nuevo")) {
//                DefaultTableModel modelo = (DefaultTableModel) tblFormula.getModel();
//                modelo.removeRow(fila);
//            } else {
//                metodos.msgError(null, "No se puede borrar este medicamento.");
//                return;
//            }
//        } else {
//            metodos.msgAdvertencia(null, "Seleccione un medicamento");
//        }
    }//GEN-LAST:event_popBorrarActionPerformed

    private void listaNotasMedicasValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listaNotasMedicasValueChanged
        try {
            if (listaNotasMedicas.getSelectedValue().toString().equalsIgnoreCase("HOY - NUEVO")) {
                txtCardiaca.setText("");
                txtResp.setText("");
                txtTemp.setText("");
                txtMucosas.setText("");
                txtOrina.setText("");
                txtDefeca.setText("");
                txtApetito.setText("");
                txtNotaMedica.setText("");
                txtNotaMedica.setEditable(true);
                txtCardiaca.setEditable(true);
                txtResp.setEditable(true);
                txtTemp.setEditable(true);
                txtMucosas.setEditable(true);
                txtOrina.setEditable(true);
                txtDefeca.setEditable(true);
                txtApetito.setEditable(true);
            } else {
                ndConstanteHospitalizacion nodo = instancias.getSql().getDatosConstanteHospitalizacion("NOTAP-" + listaNotasMedicas.getSelectedValue().toString().split(" - ")[0]);
                txtNotaMedica.setText(nodo.getInformacion());
                txtCardiaca.setText(nodo.getCardiaca());
                txtResp.setText(nodo.getResp());
                txtTemp.setText(nodo.getT());
                txtMucosas.setText(nodo.getMucosas());
                txtOrina.setText(nodo.getOrina());
                txtDefeca.setText(nodo.getDefeca());
                txtApetito.setText(nodo.getApetito());
                txtCardiaca.setEditable(false);
                txtResp.setEditable(false);
                txtTemp.setEditable(false);
                txtMucosas.setEditable(false);
                txtOrina.setEditable(false);
                txtDefeca.setEditable(false);
                txtApetito.setEditable(false);
                txtNotaMedica.setEditable(false);
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_listaNotasMedicasValueChanged

    private void txtEspecieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEspecieActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEspecieActionPerformed

    private void txtEspecieKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEspecieKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEspecieKeyReleased

    private void txtEspecieKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEspecieKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEspecieKeyTyped

    private void btnGuardar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar3ActionPerformed
        if (txtNotaMedica.getText().equals("")) {
            metodos.msgAdvertencia(null, "No ha ingresado ninguna información");
            return;
        }

        if (listaNotasMedicas.getSelectedValue().toString().equalsIgnoreCase("HOY - NUEVO")) {
            if (metodos.msgPregunta(null, "¿Desea continuar?") == 0) {

                String id = "NOTAP-" + instancias.getSql().getNumConsecutivo("NOTAP")[0];

                Object[] vector1 = {id, idHospitalizacion, metodos.fechaConsulta(metodosGenerales.fecha()), metodosGenerales.hora(), instancias.getUsuario(),
                    txtCardiaca.getText(), txtResp.getText(), txtTemp.getText(), txtMucosas.getText(), txtOrina.getText(), txtDefeca.getText(),
                    txtApetito.getText(), txtNotaMedica.getText()
                };

                ndConstanteHospitalizacion nodo1 = metodos.llenarConstantesHopitalizacion(vector1);

                if (!instancias.getSql().agregarConstanteHospitalizacion(nodo1)) {
                    metodos.msgError(this, "Hubo un problema al guardar la mascota");
                    return;
                }

                if (!instancias.getSql().aumentarConsecutivo("NOTAP", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("NOTAP")[0]) + 1)) {
                    metodos.msgError(null, "Hubo un problema al guardar en el consecutivo de la nota");
                    return;
                }

                metodos.msgExito(null, "Nota registrada con éxito");

                actualizarDiagnosticos(idHospitalizacion);
                actualizarNotasMedicas(idHospitalizacion);
            }
        }
    }//GEN-LAST:event_btnGuardar3ActionPerformed

    private void btnGuardar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar1ActionPerformed
        int ser = 0;
        for (int i = 0; i < tblProductos.getRowCount(); i++) {
            if (tblProductos.getValueAt(i, 5).equals("Activo")) {
                ser = ser + 1;
            }
        }

        if (ser <= 0) {
            metodos.msgError(null, "Debe cargar un producto nuevo");
            return;
        }

        if (metodos.msgPregunta(null, "¿Desea continuar?") == 0) {

            for (int i = 0; i < tblProductos.getRowCount(); i++) {
                if (tblProductos.getValueAt(i, 5).equals("Activo")) {
                    Object[] vector = {idHospitalizacion, tblProductos.getValueAt(i, 0), tblProductos.getValueAt(i, 1),
                        tblProductos.getValueAt(i, 3), tblProductos.getValueAt(i, 2), "", instancias.getUsuario(),
                        metodos.fechaConsulta(metodosGenerales.fecha()), metodosGenerales.hora()};

                    if (!instancias.getSql().agregarProductosProcesos(vector)) {
                        metodos.msgError(null, "Hubo un problema al guardar los productos de la peluqueria");
                        return;
                    }
                }
            }

            for (int i = 0; i < tblProductos.getRowCount(); i++) {
                tblProductos.setValueAt("Desactivado", i, 5);
            }

            metodos.msgExito(null, "¡Productos asignados exitosamente!");
        }
    }//GEN-LAST:event_btnGuardar1ActionPerformed

    private void txtCodProdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCodProdMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodProdMouseClicked

    private void txtCodProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodProdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodProdActionPerformed

    private void txtCodProdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodProdKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodProdKeyPressed

    private void txtCodProdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodProdKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String codigo = txtCodProd.getText().replace("'", "//");
            cargarProductos(codigo, "1");
        } else if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            btnGuardar1.requestFocus();
        }
    }//GEN-LAST:event_txtCodProdKeyReleased

    private void tblProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductosMouseClicked
        if (tblProductos.getSelectedColumn() == 3 && tblProductos.getValueAt(tblProductos.getSelectedRow(), 5).equals("Desactivado")) {
            metodos.msgError(this, "No se le puede cambiar la cantidad a este producto");
            return;
        }
    }//GEN-LAST:event_tblProductosMouseClicked

    private void txtNombreClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreClienteActionPerformed

    private void popBorrar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popBorrar1ActionPerformed
        if (tblProductos.getSelectedRow() > -1) {
            int fila = tblProductos.getSelectedRow();

            if (tblProductos.getValueAt(fila, 5).equals("Activo")) {
                DefaultTableModel modelo = (DefaultTableModel) tblProductos.getModel();
                modelo.removeRow(fila);
            } else {
                metodos.msgError(null, "No se puede borrar este producto.");
                return;
            }
        } else {
            metodos.msgAdvertencia(null, "Seleccione un producto");
        }
    }//GEN-LAST:event_popBorrar1ActionPerformed

    private void tblProductosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblProductosKeyReleased
        BigDecimal cant, valor;

//        tblProductos.setValueAt(big.setMoneda(big.getBigDecimal(tblProductos.getValueAt(tblProductos.getSelectedRow(), 2))), tblProductos.getSelectedRow(), 2);
        valor = big.getMoneda(tblProductos.getValueAt(tblProductos.getSelectedRow(), 2).toString());
        cant = big.getBigDecimal(tblProductos.getValueAt(tblProductos.getSelectedRow(), 3).toString());
        tblProductos.setValueAt(big.setMoneda(valor.multiply(cant)), tblProductos.getSelectedRow(), 4);
    }//GEN-LAST:event_tblProductosKeyReleased

    private void btnBusVendedor1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBusVendedor1ActionPerformed
        ventanaProductos("");
    }//GEN-LAST:event_btnBusVendedor1ActionPerformed

    private void tblMedicamentosProMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMedicamentosProMouseClicked
        if (evt.getClickCount() > 1) {
            if (tblMedicamentosPro.getSelectedColumn() != 0) {

                if (tblMedicamentosPro.getSelectedRow() == 0) {

                } else {
                    String valor = tblMedicamentosPro.getValueAt(tblMedicamentosPro.getSelectedRow(), tblMedicamentosPro.getSelectedColumn()).toString();
                    if (valor.equals("")) {
                        tblMedicamentosPro.setValueAt("Por aplicar.", tblMedicamentosPro.getSelectedRow(), tblMedicamentosPro.getSelectedColumn());
                        tblMedicamentosPro.removeEditor();
                    } else {
                        tblMedicamentosPro.setValueAt("", tblMedicamentosPro.getSelectedRow(), tblMedicamentosPro.getSelectedColumn());
                        tblMedicamentosPro.removeEditor();
                    }
                }
            }
        }

    }//GEN-LAST:event_tblMedicamentosProMouseClicked

    private void txtMedicamento1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMedicamento1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMedicamento1ActionPerformed

    private void txtMedicamento1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMedicamento1KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String idMed = "";
            try {
                idMed = instancias.getSql().getIdMedicamentoVeterinario(txtMedicamento1.getText());
            } catch (Exception e) {
            }

            if (idMed.equals("")) {
                ventanaMedicamentos1("");
            } else {
                for (int i = 0; i < tblMedicamentosPro.getColumnCount(); i++) {
                    String id = tblMedicamentosPro.getColumnName(i);
                    if (idMed.equals(id)) {
                        metodos.msgError(this, "Ya ingresaste este medicamento");
                        txtMedicamento1.setText("");
                        txtMedicamento1.requestFocus();
                        return;
                    }
                }

                txtCodMed.setText(idMed);
                txtPosologia.requestFocus();
            }
        } else {
            txtMedicamento1.setText("");
        }
    }//GEN-LAST:event_txtMedicamento1KeyReleased

    private void btnBusProd1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBusProd1ActionPerformed
        ventanaMedicamentos1("");
    }//GEN-LAST:event_btnBusProd1ActionPerformed

    private void btnGuardar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar2ActionPerformed
        if (metodos.msgPregunta(this, "¿Desea continuar?") == 0) {

            Object[][] estadosMedicamentos = instancias.getSql().getEstadoMedicamentos(idHospitalizacion);
            instancias.getSql().eliminar_registro("bdMedicamentosProcesos", " idHospitalizacion = '" + idHospitalizacion + "' ");
            instancias.getSql().eliminar_registro("bdMedicamentosProcesosAlertas", " idHospitalizacion = '" + idHospitalizacion + "' ");

            for (int i = 0; i < tblMedicamentosPro.getColumnCount(); i++) {
                for (int j = 0; j < tblMedicamentosPro.getRowCount(); j++) {

                    String valor = "";

                    try {
                        valor = tblMedicamentosPro.getValueAt(j, i).toString();
                    } catch (Exception e) {
                    }

                    if (valor.equals("Por aplicar.")) {
                        String posologia = "";

                        try {
                            posologia = tblMedicamentosPro.getValueAt(0, i).toString();
                        } catch (Exception e) {
                        }

                        Object[] vector2 = {idHospitalizacion, tblMedicamentosPro.getColumnName(i - 1), tblMedicamentosPro.getColumnName(i),
                            posologia, tblMedicamentosPro.getValueAt(j, 0)};
                        ndTablaMedicamentos nodoMedicamentos = metodos.llenarTablaMedicamentos(vector2);

                        if (!instancias.getSql().agregarTablaMedicamentos(nodoMedicamentos)) {
                            metodos.msgError(this, "Hubo un problema al guardar la tabla de medicamentos");
                            return;
                        }

                        if (!instancias.getSql().agregarTablaMedicamentosAlertas(nodoMedicamentos)) {
                            metodos.msgError(this, "Hubo un problema al guardar la tabla de medicamentos");
                            return;
                        }

                        if (!instancias.getSql().asignarMascotaHosp(idHospitalizacion, txtNombre.getText(), txtNombreCliente.getText())) {
                            metodos.msgError(this, "Hubo un problema al guardar la tabla de medicamentos");
                            return;
                        }
                    }
                }
            }

            for (int i = 0; i < estadosMedicamentos.length; i++) {
                String cod = estadosMedicamentos[i][1].toString();
                String hora = estadosMedicamentos[i][4].toString();
                String estado = estadosMedicamentos[i][5].toString();

                if (estado.equals("Realizado")) {
                    if (!instancias.getSql().cambiarEstadoMedicamento(idHospitalizacion, cod, hora)) {
                        metodos.msgError(this, "Error al actualizar estados");
                        return;
                    }
                }
            }

            metodos.msgExito(this, "Tabla actualizada con éxito");

            String hora = metodosGenerales.hora().split(":")[0];
            String minuto = metodosGenerales.hora().split(":")[1];
            int numeroHora = Integer.valueOf(hora);
            int numeroMinuto = Integer.valueOf(minuto);
            Object[][] medicamentosPendientes = null;

            try {
                medicamentosPendientes = instancias.getSql().getMedicamentosPorAplicarVeterinario("");
            } catch (Exception e) {
            }

            Boolean activarMensaje = false;

            if (medicamentosPendientes != null) {
                for (int i = 0; i < medicamentosPendientes.length; i++) {
                    if (medicamentosPendientes[i][5] != null) {
                        if (medicamentosPendientes[i][5].equals("Pendiente")) {
                            int horaEstablecida = Integer.parseInt(medicamentosPendientes[i][4].toString().split(":")[0]);
                            int minutoEstablecida = Integer.parseInt(medicamentosPendientes[i][4].toString().split(":")[0]);
                            if (numeroHora >= horaEstablecida && numeroMinuto >= minutoEstablecida) {
                                activarMensaje = true;
                                break;
                            }
                        }
                    }
                }

                if (activarMensaje) {
                    instancias.getMenu().actualizarMedicamentos(true);
                } else {
                    instancias.getMenu().actualizarMedicamentos(false);
                }
            }
        }
    }//GEN-LAST:event_btnGuardar2ActionPerformed

    private void btnModificar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificar2ActionPerformed
        actualizarMedicamentos(idHospitalizacion);
        cargarAgendas(false);
    }//GEN-LAST:event_btnModificar2ActionPerformed

    private void btnGuardar4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar4ActionPerformed
        /* CARGAR DOCTORES EN LA AGENDA GENERAL */
        Object[] medicamentos = new Object[tblMedicamentosPro.getColumnCount() + 2];
        medicamentos[0] = "Hora";
        int f = 1;

        int registros = tblMedicamentosPro.getColumnCount();

        if (registros > 1) {
            for (int i = 1; i < registros; i++) {
                medicamentos[f] = tblMedicamentosPro.getColumnName(i);
                f = f + 1;
            }
        }

        medicamentos[f] = txtCodMed.getText();
        medicamentos[f + 1] = txtMedicamento1.getText();

        tblMedicamentosPro.setModel(new javax.swing.table.DefaultTableModel(new Object[0][0], medicamentos) {
            boolean[] canEdit = new boolean[]{
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[0];
            }
        });

        for (int i = 0; i < tblMedicamentosPro.getColumnCount(); i++) {
            if (i % 2 != 0) {
                tblMedicamentosPro.getColumnModel().getColumn(i).setMinWidth(0);
                tblMedicamentosPro.getColumnModel().getColumn(i).setPreferredWidth(0);
                tblMedicamentosPro.getColumnModel().getColumn(i).setMaxWidth(0);
            }
        }

        cargarAgendas(true);

        String horaInicio = txtHoraInicio.getText();
        int cantAplicar = 0, cada = 0;

        try {
            cantAplicar = Integer.parseInt(txtCant.getText());
        } catch (Exception e) {
        }

        try {
            cada = Integer.parseInt(txtCada.getText());
        } catch (Exception e) {
        }

        for (int i = 0; i < cantAplicar; i++) {
            for (int k = 0; k < tblMedicamentosPro.getRowCount(); k++) {
                if (tblMedicamentosPro.getValueAt(k, 0).equals(horaInicio)) {
                    tblMedicamentosPro.setValueAt("Por aplicar.", k, tblMedicamentosPro.getColumnCount() - 1);
                }
            }

            int hora = Integer.parseInt(horaInicio.split(":")[0]);
            int minuto = Integer.parseInt(horaInicio.split(":")[1]);

            hora = hora + cada;

            if (hora > 24) {
                hora = hora - 24;
            }

            if (String.valueOf(minuto).length() == 1) {
                horaInicio = String.valueOf(hora) + ":0" + String.valueOf(minuto);
            } else {
                horaInicio = String.valueOf(hora) + ":" + String.valueOf(minuto);
            }

        }

        txtMedicamento1.setText("");
        txtCant.setText("");
        txtCada.setText("");
        txtHoraInicio.setText("");
        txtCodMed.setText("");
        txtMedicamento1.requestFocus();
    }//GEN-LAST:event_btnGuardar4ActionPerformed

    private void txtCantKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtCada.requestFocus();
        } else {
            calcularRango();
        }
    }//GEN-LAST:event_txtCantKeyReleased

    private void txtCodMedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodMedActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodMedActionPerformed

    private void txtCodMedKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodMedKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodMedKeyReleased

    private void txtPosologiaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPosologiaKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtHoraInicio.requestFocus();
        }
    }//GEN-LAST:event_txtPosologiaKeyReleased

    private void txtHoraInicioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHoraInicioKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txtHoraInicio.getText().equals("")) {
                ventanaHoras();
            } else {
                txtCant.requestFocus();
            }
        } else {
            txtHoraInicio.setText("");
        }
    }//GEN-LAST:event_txtHoraInicioKeyReleased

    private void btnModificar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificar3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnModificar3ActionPerformed

    private void txtCadaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCadaKeyReleased
        calcularRango();
    }//GEN-LAST:event_txtCadaKeyReleased

    private void txtCantKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtCantKeyTyped

    private void txtCadaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCadaKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtCadaKeyTyped

    public void calcularRango() {
        int cada = 0, cant = 0, total;

        try {
            cant = Integer.parseInt(txtCant.getText());
        } catch (Exception e) {
            if (!txtCant.getText().equals("")) {
                metodos.msgError(this, "Solo números enteros");
                return;
            }
        }

        try {
            cada = Integer.parseInt(txtCada.getText());
        } catch (Exception e) {
            if (!txtCada.getText().equals("")) {
                metodos.msgError(this, "Solo números enteros");
                return;
            }
        }

        total = cant * cada;
        if (total > 24) {
            metodos.msgError(this, "No se puede pasar las 24 horas del dia");
            txtCant.setText("");
            txtCada.setText("");
        }
    }

    public void ventanaHoras() {
        buscHoraHospitalizacion buscar = new buscHoraHospitalizacion(null, rootPaneCheckingEnabled);
        buscar.setLocationRelativeTo(null);
        instancias.setBuscHorasHospitalizacion(buscar);
        instancias.setCampoActual(txtHoraInicio);
        buscar.setInstancia(instancias);
        txtCant.requestFocus();
        buscar.show();
    }

    public void consultarMaestros() {
        datos = instancias.getSql().getDatosMaestra();
    }

    public void cargarProductos(String codigo, String cantidad) {

        if (cantidad.contains(".")) {
            cantidad = cantidad.replace(".", ",");
        }

        ndProducto nodo = null;

        String CodigoProd = "";
        if (codigo.equals("")) {
            CodigoProd = "";
        } else {
            Object[][] listado = instancias.getSql().getCodigosRelacionados(codigo, " where codigo");
            if (listado.length > 0) {
                codigo = listado[0][0].toString();
            }

            nodo = instancias.getSql().getDatosProducto(codigo, "bdProductos");
            if (nodo.getIdSistema() != null) {
                CodigoProd = nodo.getIdSistema();
            }
        }

        if (!CodigoProd.equals("")) {
            if (instancias.getSql().getProdActivo(nodo.getCodigo())) {
                metodos.msgError(null, "Este producto esta inactivo");
                lbCodigo.requestFocus();
                return;
            }

            modelo.addRow(new Object[]{nodo.getIdSistema(), nodo.getDescripcion(), big.setMonedaExacta(big.getBigDecimal(nodo.getL1())),
                cantidad, big.setMonedaExacta(big.getBigDecimal(nodo.getL1())), "Activo"});
            txtCodProd.setText("");
            tblProductos.scrollRectToVisible(tblProductos.getCellRect(tblProductos.getRowCount() - 1, 0, true));
//            cargarTotales();
            return;
        }

//        calcularTabla(modeloPro.getRowCount() - 1, false);
        ventanaProductos(codigo);
    }

    public void cargarAgendas(Boolean desdeBoton) {
        modeloPro = (DefaultTableModel) tblMedicamentosPro.getModel();

        while (tblMedicamentosPro.getRowCount() > 0) {
            modeloPro.removeRow(0);
        }

        String x;
        int horaIni = 6;
        int horaFin = 20;
        int minInic = 00;
        int intervalo = 10;
        int contador = 0;

        if (datos[92] != null) {
            horaIni = Integer.parseInt(datos[92].toString());
        }

        if (datos[93] != null) {
            horaFin = Integer.parseInt(datos[93].toString());
        }

        if (datos[94] != null) {
            intervalo = Integer.parseInt(datos[94].toString());
        }

        modeloPro.addRow(posologias);

        if (desdeBoton) {
            tblMedicamentosPro.setValueAt(txtPosologia.getText(), 0, tblMedicamentosPro.getColumnCount() - 1);
        }

        while (horaIni < horaFin) {
            if (minInic == 0) {
                x = horaIni + ":" + minInic + "0";
            } else {
                x = horaIni + ":" + minInic;
            }

            Object[] titulosTabla = new Object[tblMedicamentosPro.getColumnCount()];
            titulosTabla[0] = x;
            int z = 1;
            for (int i = 0; i < tblMedicamentosPro.getColumnCount() - 1; i++) {
                titulosTabla[z] = "";
                z++;
            }

            modeloPro.addRow(titulosTabla);

            contador = contador + intervalo;
            if (contador > 59) {
                horaIni = horaIni + 1;
                contador = contador - 60;
                minInic = contador;
            } else {
                minInic = contador;
            }
        }

        tblMedicamentosPro.getColumnModel().getColumn(0).setMinWidth(45);
        tblMedicamentosPro.getColumnModel().getColumn(0).setPreferredWidth(45);
        tblMedicamentosPro.getColumnModel().getColumn(0).setMaxWidth(45);

        tblMedicamentosPro.setRowHeight(0, 50);

        Object[][] medicamentosPendientes = instancias.getSql().getMedicamentosPorAplicarVeterinario(" where idHospitalizacion = '" + idHospitalizacion + "'; ");;

        for (int i = 0; i < medicamentosPendientes.length; i++) {
            for (int j = 0; j < tblMedicamentosPro.getColumnCount(); j++) {
                if (medicamentosPendientes[i][1].equals(tblMedicamentosPro.getColumnName(j))) {
                    for (int k = 0; k < tblMedicamentosPro.getRowCount(); k++) {
                        if (tblMedicamentosPro.getValueAt(k, 0).equals(medicamentosPendientes[i][4])) {
                            tblMedicamentosPro.setValueAt("Por aplicar.", k, j + 1);
                        }
                        tblMedicamentosPro.setValueAt(medicamentosPendientes[i][3], 0, j + 1);
                    }
                }
            }
        }

        txtPosologia.setText("");
    }

    public void cargarProductoHospitalizacion(String codigo, String cant) {
        cargarProductos(codigo, cant);
        for (int i = 0; i < tblProductos.getRowCount(); i++) {
            if (tblProductos.getValueAt(i, 5).equals("Activo")) {
                Object[] vector = {idHospitalizacion, tblProductos.getValueAt(i, 0), tblProductos.getValueAt(i, 1),
                    tblProductos.getValueAt(i, 3), tblProductos.getValueAt(i, 2), "", instancias.getUsuario(), metodosGenerales.fecha(),
                    metodosGenerales.hora()};

                if (!instancias.getSql().agregarProductosProcesos(vector)) {
                    metodos.msgError(null, "Hubo un problema al guardar los productos de la hospitalizacion");
                    return;
                }
            }
        }
        for (int i = 0; i < tblProductos.getRowCount(); i++) {
            tblProductos.setValueAt("Desactivado", i, 5);
        }
    }

    public void ventanaProductos(String codigo) {
        VistaBuscadorProductos buscar = new VistaBuscadorProductos(null, true, false, "", "productos1");
        buscar.setLocationRelativeTo(null);
        instancias.setBusProductos(buscar);
        instancias.setCampoActual(txtCodProd);
        txtCodProd.requestFocus();
        buscar.noEncontrado(codigo.replace("'", "//"));
        buscar.show();
    }

    public void setMascota(String mascota, String codigo, String hospitalizacion) {
        idHospitalizacion = hospitalizacion;

        cmbConsultas.removeAllItems();
        ndHistoria historia = instancias.getSql().getDatosHistoria(mascota);
        Object[][] fechas = instancias.getSql().getConsultas(historia.getId());
        cmbConsultas.addItem("Seleccionar...");
        for (int i = 0; i < fechas.length; i++) {
            cmbConsultas.addItem(fechas[i][0] + " - " + metodos.fecha(fechas[i][1].toString()));
        }

        String consulta = "";
        try {
            consulta = instancias.getSql().getConsultaAsociada(idHospitalizacion);
        } catch (Exception e) {
        }

        if (!consulta.equals("")) {
            for (int i = 0; i < cmbConsultas.getItemCount(); i++) {
                String codigoConsulta = cmbConsultas.getItemAt(i).toString().split(" - ")[0];
                if (consulta.equals(codigoConsulta)) {
                    cmbConsultas.setSelectedIndex(i);
                }
            }
        }

        codigo = codigo.substring(12, codigo.length());
        ndHistoria nodo = instancias.getSql().getDatosHistoria(mascota);
        ndMascota Mascota = instancias.getSql().getDatosMascota(mascota);

        ModeloContacto cliente = instancias.getSql().getDatosTercero(Mascota.getCliente());

        txtCodigo.setText(mascota);
        txtNombre.setText(Mascota.getNombre());
        txtRaza.setText(Mascota.getRaza());
        txtEspecie.setText(Mascota.getExpecie());

        txtCedula.setText(cliente.getId());
        txtNombreCliente.setText(cliente.getNombre());
        txtCelular.setText(cliente.getCelular());
        txtTelefono.setText(cliente.getTelefono());

        String fecha = Mascota.getEdad();
        if (fecha.length() == 9) {
            fecha = "0" + fecha;
        }

        int anho = metodosGenerales.calcularEdad(fecha);
        int meses = metodosGenerales.calcularEdadMeses(fecha);
        if (anho > 0) {
            txtEdad.setText(anho + " años y " + meses + " meses");
        } else {
            txtEdad.setText(meses + " meses");
        }

        actualizarNotasMedicas(idHospitalizacion);
        actualizarDiagnosticos(idHospitalizacion);
        actualizarProductos(idHospitalizacion);
        actualizarMedicamentos(idHospitalizacion);
        cargarAgendas(false);

    }

    private void actualizarNotasMedicas(String idHosp) {
        txtNotaMedica.setText("");

        Object[][] sesion = instancias.getSql().getNotasEnfermeriaVet(idHosp);
        listaNotasMedicas.removeAll();

        Object[] datos = new Object[sesion.length + 1];
        datos[0] = "HOY - NUEVO";
        int j = sesion.length;

        for (int i = 0; i < sesion.length; i++) {
            datos[j--] = sesion[i][0].toString().replace("NOTAP-", "") + " - " + metodos.fecha(sesion[i][1].toString());
        }

        listaNotasMedicas.setListData(datos);
        listaNotasMedicas.setSelectedIndex(0);
    }

    public void actualizarDiagnosticos(String id) {
        txtCardiaca.setText("");
        txtResp.setText("");
        txtTemp.setText("");
        txtMucosas.setText("");
        txtOrina.setText("");
        txtDefeca.setText("");
        txtApetito.setText("");

        Object[][] sesion = instancias.getSql().getSeguimientoHospitalizaciones(id, "bdConstantesHospitalizacion");
        Object[] datos = new Object[sesion.length + 1];
        datos[0] = "HOY - NUEVO";
        int j = sesion.length;

        for (int i = 0; i < sesion.length; i++) {
            datos[j--] = sesion[i][0].toString().replace("NOTAP-", "") + " - " + metodos.fecha(sesion[i][1].toString());
        }
    }

    public void actualizarMedicamentos(String id) {

        Object[][] med = instancias.getSql().getMedicamentosHospitalizacionesAgrupados(id);

        /* CARGAR DOCTORES EN LA AGENDA GENERAL */
        posologias = new Object[(med.length * 2) + 1];
        Object[] medicamentos = new Object[(med.length * 2) + 1];

        medicamentos[0] = "Hora";
        posologias[0] = "PSG:";
        int f = 1;

        int registros = med.length;
        if (registros > 0) {
            for (int i = 0; i < registros; i++) {
                medicamentos[f] = med[i][0];
                medicamentos[f + 1] = med[i][1];

                posologias[f] = "";
                posologias[f + 1] = med[i][2];
                f = f + 2;
            }
        }

        tblMedicamentosPro.setModel(new javax.swing.table.DefaultTableModel(new Object[0][0], medicamentos) {
            boolean[] canEdit = new boolean[]{
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[0];
            }
        });

        for (int i = 0; i < tblMedicamentosPro.getColumnCount(); i++) {
            if (i % 2 != 0) {
                tblMedicamentosPro.getColumnModel().getColumn(i).setMinWidth(0);
                tblMedicamentosPro.getColumnModel().getColumn(i).setPreferredWidth(0);
                tblMedicamentosPro.getColumnModel().getColumn(i).setMaxWidth(0);
            }
        }
        txtCodProd.requestFocus();
    }

    public void actualizarProductos(String id) {
        Object[][] productos = instancias.getSql().getProductosHospitalizaciones(id);

        while (tblProductos.getRowCount() > 0) {
            modelo.removeRow(0);
        }

        for (int i = 0; i < productos.length; i++) {
            modelo.addRow(new Object[]{productos[i][0], productos[i][1], productos[i][3], productos[i][2], this.simbolo + " 0", "Desactivado"});

            BigDecimal valor, cant;
            valor = big.getMoneda(tblProductos.getValueAt(i, 2).toString());
            cant = big.getBigDecimal(tblProductos.getValueAt(i, 3).toString());
            tblProductos.setValueAt(big.setMoneda(valor.multiply(cant)), i, 4);
        }
        txtCodProd.requestFocus();
    }

    public void ventanaMedicamentos1(String nit) {
        buscMedicamentosVeterinario buscar = new buscMedicamentosVeterinario(instancias.getMenu(), rootPaneCheckingEnabled);
        buscar.setLocationRelativeTo(null);
        buscar.setInstancia(instancias);
        instancias.setBuscMedicamentosVeterinario(buscar);
        instancias.setCampoActual(txtMedicamento1);
        txtMedicamento1.requestFocus();
        buscar.noEncontrado(nit);
        buscar.show();
    }

    public void validarHospitalizacionCargada() {
        if (txtCodigo.getText().equals("")) {
            try {
                instancias.getMascotas().setSelected(true);
            } catch (Exception e) {
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBusProd1;
    private javax.swing.JButton btnBusVendedor1;
    private javax.swing.JButton btnGuardar1;
    private javax.swing.JButton btnGuardar2;
    private javax.swing.JButton btnGuardar3;
    private javax.swing.JButton btnGuardar4;
    private javax.swing.JButton btnModificar2;
    private javax.swing.JButton btnModificar3;
    private javax.swing.JComboBox cmbConsultas;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lbCodigo;
    private javax.swing.JLabel lbDireccion;
    private javax.swing.JLabel lbDireccion1;
    private javax.swing.JLabel lbNit;
    private javax.swing.JLabel lbNit1;
    private javax.swing.JLabel lbNit2;
    private javax.swing.JLabel lbNit3;
    private javax.swing.JLabel lbNit4;
    private javax.swing.JLabel lbNit5;
    private javax.swing.JLabel lbNit6;
    private javax.swing.JLabel lbNit7;
    private javax.swing.JLabel lbNit8;
    private javax.swing.JLabel lbNit9;
    private javax.swing.JLabel lbRazon;
    private javax.swing.JLabel lbRazon1;
    private javax.swing.JLabel lbRazon10;
    private javax.swing.JLabel lbRazon11;
    private javax.swing.JLabel lbRazon12;
    private javax.swing.JLabel lbRazon13;
    private javax.swing.JLabel lbRazon21;
    private javax.swing.JLabel lbRazon6;
    private javax.swing.JLabel lbRazon7;
    private javax.swing.JLabel lbRazon8;
    private javax.swing.JLabel lbRazon9;
    private javax.swing.JLabel lbTelefono;
    private javax.swing.JList listaNotasMedicas;
    private javax.swing.JPanel pnlFormulario;
    private javax.swing.JMenuItem popBorrar;
    private javax.swing.JMenuItem popBorrar1;
    private javax.swing.JPopupMenu popMedicamentos;
    private javax.swing.JPopupMenu popProductos;
    private javax.swing.JScrollPane scrFormulario;
    private javax.swing.JTable tblMedicamentosPro;
    private javax.swing.JTable tblProductos;
    private javax.swing.JTextField txtApetito;
    private javax.swing.JTextField txtCada;
    private javax.swing.JTextField txtCant;
    private javax.swing.JTextField txtCardiaca;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtCelular;
    private javax.swing.JTextField txtCodMed;
    private javax.swing.JTextField txtCodProd;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtDefeca;
    private javax.swing.JTextField txtEdad;
    private javax.swing.JTextField txtEspecie;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtHoraInicio;
    private javax.swing.JTextField txtMedicamento1;
    private javax.swing.JTextField txtMucosas;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNombreCliente;
    private javax.swing.JTextArea txtNotaMedica;
    private javax.swing.JTextField txtOrina;
    private javax.swing.JTextArea txtPosologia;
    private javax.swing.JTextField txtRaza;
    private javax.swing.JTextField txtResp;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtTemp;
    // End of variables declaration//GEN-END:variables
}
