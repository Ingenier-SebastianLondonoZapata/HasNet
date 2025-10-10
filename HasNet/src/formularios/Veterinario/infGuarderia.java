package formularios.Veterinario;

import clases.Instancias;
import Utilidades.BaseDatos.SQL;
import clases.Veterinario.ndGuarderia;
import clases.Veterinario.ndMascota;
import Modelo.Terceros.ModeloContacto;
import clases.big;
import clases.metodosGenerales;
import clases.productos.ndProducto;
import formularios.terceros.buscClientes;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class infGuarderia extends javax.swing.JInternalFrame {

    metodosGenerales metodos = new metodosGenerales();
    private Instancias instancias;

    private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension dimBarra = null;
    DefaultTableModel modelo;
    String idGuarderia = "";

    public void actualizarPrecios() {
        
        ndProducto nodo = instancias.getSql().getDatosProducto("GD1", "bdProductos");
        try {
            txtValorHora.setText(big.setMoneda(big.getBigDecimal(nodo.getL1())));
        } catch (Exception e) {
            txtValorHora.setText("Sin establecer");
        }

        ndProducto nodo1 = instancias.getSql().getDatosProducto("GD2", "bdProductos");
        try {
            txtValorDia.setText(big.setMoneda(big.getBigDecimal(nodo1.getL1())));
        } catch (Exception e) {
            txtValorDia.setText("Sin establecer");
        }
    }

    public infGuarderia() {

        initComponents();

        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
        dimBarra = Barra.getPreferredSize();
        Barra.setSize(0, 0);
        Barra.setPreferredSize(new Dimension(0, 0));
        setBorder(null);
        repaint();

        modelo = (DefaultTableModel) tblGuarderia.getModel();
        instancias = Instancias.getInstancias();

        Object[][] datos = instancias.getSql().getRegistrosGuarderia();
        for (Object[] dato : datos) {
            dato[2] = metodos.fecha(dato[2].toString());
            modelo.addRow(dato);
        }

        txtIdTercero.setVisible(false);
        actualizarPrecios();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPasswordField1 = new javax.swing.JPasswordField();
        scrFormulario = new javax.swing.JScrollPane();
        pnlFormulario = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblGuarderia = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        lbRazon3 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        lbRazon1 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        lbRazon2 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        lbRazon = new javax.swing.JLabel();
        txtCodigoMascota = new javax.swing.JTextField();
        lbRazon4 = new javax.swing.JLabel();
        txtMascota = new javax.swing.JTextField();
        lbRazon17 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtObservacion = new javax.swing.JTextArea();
        btnBuscMascotas = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btnAggProductos = new javax.swing.JButton();
        btnBuscTerceros = new javax.swing.JButton();
        txtIdTercero = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        lbRazon6 = new javax.swing.JLabel();
        lbRazon10 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        lbRazon7 = new javax.swing.JLabel();
        txtValorDia = new javax.swing.JTextField();
        lbRazon12 = new javax.swing.JLabel();
        txtValorHora = new javax.swing.JTextField();
        txtTotalHoras = new javax.swing.JTextField();
        lbRazon8 = new javax.swing.JLabel();
        lbRazon11 = new javax.swing.JLabel();
        txthEntrada = new javax.swing.JTextField();
        txtfEntrada = new javax.swing.JTextField();
        txtTotalDias = new javax.swing.JTextField();
        lbRazon15 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();

        jPasswordField1.setText("jPasswordField1");

        setTitle("Factura");

        pnlFormulario.setBackground(new java.awt.Color(255, 255, 255));

        tblGuarderia.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        tblGuarderia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Mascota", "Fecha ingreso", "Hora", "id"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblGuarderia.setRowHeight(24);
        tblGuarderia.getTableHeader().setReorderingAllowed(false);
        tblGuarderia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblGuarderiaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblGuarderia);
        if (tblGuarderia.getColumnModel().getColumnCount() > 0) {
            tblGuarderia.getColumnModel().getColumn(0).setMinWidth(150);
            tblGuarderia.getColumnModel().getColumn(0).setPreferredWidth(200);
            tblGuarderia.getColumnModel().getColumn(0).setMaxWidth(350);
            tblGuarderia.getColumnModel().getColumn(2).setMinWidth(130);
            tblGuarderia.getColumnModel().getColumn(2).setPreferredWidth(130);
            tblGuarderia.getColumnModel().getColumn(2).setMaxWidth(130);
            tblGuarderia.getColumnModel().getColumn(3).setMinWidth(100);
            tblGuarderia.getColumnModel().getColumn(3).setPreferredWidth(100);
            tblGuarderia.getColumnModel().getColumn(3).setMaxWidth(100);
            tblGuarderia.getColumnModel().getColumn(4).setMinWidth(0);
            tblGuarderia.getColumnModel().getColumn(4).setPreferredWidth(0);
            tblGuarderia.getColumnModel().getColumn(4).setMaxWidth(0);
        }

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        lbRazon3.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbRazon3.setText("CC/NIT:");

        txtId.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtId.setName("Descripción"); // NOI18N
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

        lbRazon1.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbRazon1.setText("Cliente:");

        txtNombre.setEditable(false);
        txtNombre.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtNombre.setName("Descripción"); // NOI18N
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreKeyReleased(evt);
            }
        });

        lbRazon2.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbRazon2.setText("Telefono:");

        txtTelefono.setEditable(false);
        txtTelefono.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtTelefono.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTelefono.setName("Descripción"); // NOI18N
        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyReleased(evt);
            }
        });

        lbRazon.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbRazon.setText("Codigo:");

        txtCodigoMascota.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtCodigoMascota.setName("Descripción"); // NOI18N
        txtCodigoMascota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoMascotaActionPerformed(evt);
            }
        });
        txtCodigoMascota.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoMascotaKeyReleased(evt);
            }
        });

        lbRazon4.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbRazon4.setText("Mascota:");

        txtMascota.setEditable(false);
        txtMascota.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtMascota.setName("Nombre Mascota"); // NOI18N
        txtMascota.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMascotaKeyReleased(evt);
            }
        });

        lbRazon17.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbRazon17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbRazon17.setText("Observaciones");

        txtObservacion.setColumns(20);
        txtObservacion.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtObservacion.setLineWrap(true);
        txtObservacion.setRows(3);
        txtObservacion.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jScrollPane2.setViewportView(txtObservacion);

        btnBuscMascotas.setBackground(new java.awt.Color(204, 204, 204));
        btnBuscMascotas.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnBuscMascotas.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscMascotas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar1.png"))); // NOI18N
        btnBuscMascotas.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnBuscMascotas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscMascotas.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnBuscMascotas.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnBuscMascotas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscMascotasActionPerformed(evt);
            }
        });

        btnGuardar.setBackground(new java.awt.Color(46, 204, 113));
        btnGuardar.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
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

        btnNuevo.setBackground(new java.awt.Color(204, 204, 204));
        btnNuevo.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/nuevo.png"))); // NOI18N
        btnNuevo.setText("NUEVO");
        btnNuevo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNuevo.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnNuevo.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnAggProductos.setBackground(new java.awt.Color(204, 204, 204));
        btnAggProductos.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnAggProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar.png"))); // NOI18N
        btnAggProductos.setText("AGREGAR PRODUCTOS");
        btnAggProductos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAggProductos.setEnabled(false);
        btnAggProductos.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnAggProductos.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnAggProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAggProductosActionPerformed(evt);
            }
        });

        btnBuscTerceros.setBackground(new java.awt.Color(204, 204, 204));
        btnBuscTerceros.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnBuscTerceros.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscTerceros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar1.png"))); // NOI18N
        btnBuscTerceros.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnBuscTerceros.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscTerceros.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnBuscTerceros.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnBuscTerceros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscTercerosActionPerformed(evt);
            }
        });

        txtIdTercero.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtIdTercero.setName("Descripción"); // NOI18N
        txtIdTercero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdTerceroActionPerformed(evt);
            }
        });
        txtIdTercero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIdTerceroKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbRazon4, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMascota))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lbRazon1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbRazon3, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBuscTerceros, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lbRazon2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtTelefono))
                            .addComponent(txtNombre)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbRazon, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCodigoMascota, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBuscMascotas, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtIdTercero, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbRazon17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(106, 106, 106)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnAggProductos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(118, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lbRazon3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtId, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbRazon2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTelefono)
                    .addComponent(btnBuscTerceros, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbRazon1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNombre))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbRazon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCodigoMascota)
                    .addComponent(btnBuscMascotas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtIdTercero))
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbRazon4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtMascota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(lbRazon17)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnNuevo))
                .addGap(3, 3, 3)
                .addComponent(btnAggProductos)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        lbRazon6.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbRazon6.setText("Fecha Entrada:");

        lbRazon10.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbRazon10.setText("Total días:");

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));

        lbRazon7.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbRazon7.setText("Valor por día:");

        txtValorDia.setEditable(false);
        txtValorDia.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtValorDia.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtValorDia.setText("0");
        txtValorDia.setName("Descripción"); // NOI18N
        txtValorDia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtValorDiaKeyReleased(evt);
            }
        });

        lbRazon12.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbRazon12.setText("Valor por hora:");

        txtValorHora.setEditable(false);
        txtValorHora.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtValorHora.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtValorHora.setText("0");
        txtValorHora.setName("Descripción"); // NOI18N
        txtValorHora.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtValorHoraKeyReleased(evt);
            }
        });

        txtTotalHoras.setEditable(false);
        txtTotalHoras.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtTotalHoras.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTotalHoras.setName("Descripción"); // NOI18N
        txtTotalHoras.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTotalHorasKeyReleased(evt);
            }
        });

        lbRazon8.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbRazon8.setText("Total horas:");

        lbRazon11.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbRazon11.setText("Hora Entrada:");

        txthEntrada.setEditable(false);
        txthEntrada.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txthEntrada.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txthEntrada.setName("Descripción"); // NOI18N
        txthEntrada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txthEntradaKeyReleased(evt);
            }
        });

        txtfEntrada.setEditable(false);
        txtfEntrada.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtfEntrada.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtfEntrada.setName("Descripción"); // NOI18N
        txtfEntrada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtfEntradaKeyReleased(evt);
            }
        });

        txtTotalDias.setEditable(false);
        txtTotalDias.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtTotalDias.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTotalDias.setName("Descripción"); // NOI18N
        txtTotalDias.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTotalDiasKeyReleased(evt);
            }
        });

        lbRazon15.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbRazon15.setText("TOTAL GUARDERIA:");

        txtTotal.setEditable(false);
        txtTotal.setFont(new java.awt.Font("Century Gothic", 1, 26)); // NOI18N
        txtTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTotal.setName("Descripción"); // NOI18N
        txtTotal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTotalKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lbRazon15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbRazon6, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbRazon10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtfEntrada, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                            .addComponent(txtTotalDias))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbRazon11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbRazon8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txthEntrada, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                            .addComponent(txtTotalHoras)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lbRazon7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtValorDia, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbRazon12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtValorHora, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(14, 14, 14))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lbRazon6)
                            .addComponent(txtfEntrada, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lbRazon11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txthEntrada)))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbRazon10, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTotalDias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbRazon8)
                        .addComponent(txtTotalHoras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtValorDia, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(lbRazon7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtValorHora, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbRazon12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTotal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbRazon15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlFormularioLayout = new javax.swing.GroupLayout(pnlFormulario);
        pnlFormulario.setLayout(pnlFormularioLayout);
        pnlFormularioLayout.setHorizontalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 499, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlFormularioLayout.setVerticalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 683, Short.MAX_VALUE)
                    .addGroup(pnlFormularioLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 125, Short.MAX_VALUE)))
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
            .addComponent(scrFormulario)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        Object[] campos = {txtNombre, txtMascota};
        String faltantes = metodos.camposVacios(campos);

        if (!faltantes.equals("")) {
            metodos.msgAdvertencia(this, "No puede continuar, faltan los siguientes campos: " + faltantes);
            return;
        }

        if (metodos.msgPregunta(this, "¿Desea continuar?") == 0) {
            if (btnGuardar.getText().equalsIgnoreCase("GUARDAR")) {
                Guardar();
            } else {
                Salida();
            }
        }

        actualizarTabla();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        for (int x = 0; x < jPanel1.getComponentCount(); x++) {
            if (jPanel1.getComponent(x) instanceof JTextField) {
                JTextField textField = (JTextField) jPanel1.getComponent(x);
                textField.setText("");
            }
        }

        for (int x = 0; x < jPanel2.getComponentCount(); x++) {
            if (jPanel2.getComponent(x) instanceof JTextField) {
                JTextField textField = (JTextField) jPanel2.getComponent(x);
                textField.setText("");
            }
        }

        txtObservacion.setText("");
        btnGuardar.setBackground(new java.awt.Color(46, 204, 113));
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
        btnGuardar.setText("GUARDAR");

        btnAggProductos.setEnabled(false);
        idGuarderia = "";
        actualizarPrecios();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void tblGuarderiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGuarderiaMouseClicked
        if (evt.getClickCount() == 1) {
            btnNuevoActionPerformed(null);
            txtCodigoMascota.setText(tblGuarderia.getValueAt(tblGuarderia.getSelectedRow(), 0).toString());
            cargarTercero(txtCodigoMascota.getText());
            btnAggProductos.setEnabled(true);
        }
    }//GEN-LAST:event_tblGuarderiaMouseClicked

    private void btnAggProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAggProductosActionPerformed
        dlgAgregarProductosHistorial productos = new dlgAgregarProductosHistorial(null, true, idGuarderia, txtIdTercero.getText(),
                txtCodigoMascota.getText());
        productos.setVisible(true);
    }//GEN-LAST:event_btnAggProductosActionPerformed

    private void txtIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdActionPerformed

    private void txtIdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cargarCliente(txtId.getText());
        } else {
            String codigo = txtId.getText();
            btnNuevoActionPerformed(null);
            txtId.setText(codigo);
        }
    }//GEN-LAST:event_txtIdKeyReleased

    private void txtNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreKeyReleased

    private void txtTelefonoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoKeyReleased

    private void txtCodigoMascotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoMascotaActionPerformed

    }//GEN-LAST:event_txtCodigoMascotaActionPerformed

    private void txtCodigoMascotaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoMascotaKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cargarTercero(txtCodigoMascota.getText());
        } else {
            txtMascota.setText("");
        }
    }//GEN-LAST:event_txtCodigoMascotaKeyReleased

    private void txtMascotaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMascotaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMascotaKeyReleased

    private void txtfEntradaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtfEntradaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtfEntradaKeyReleased

    private void txthEntradaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txthEntradaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txthEntradaKeyReleased

    private void txtTotalDiasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalDiasKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalDiasKeyReleased

    private void txtTotalHorasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalHorasKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalHorasKeyReleased

    private void txtTotalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalKeyReleased

    private void btnBuscMascotasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscMascotasActionPerformed
        ventanaMascotas(txtCodigoMascota.getText());
    }//GEN-LAST:event_btnBuscMascotasActionPerformed

    private void btnBuscTercerosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscTercerosActionPerformed
        ventanaTerceros("");
    }//GEN-LAST:event_btnBuscTercerosActionPerformed

    private void txtValorDiaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorDiaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValorDiaKeyReleased

    private void txtValorHoraKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorHoraKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValorHoraKeyReleased

    private void txtIdTerceroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdTerceroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdTerceroActionPerformed

    private void txtIdTerceroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdTerceroKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdTerceroKeyReleased

    public void ventanaTerceros(String nit) {
        buscClientes buscar = new buscClientes(instancias.getMenu(), true, false, null, "");
        buscar.setLocationRelativeTo(null);
        instancias.setBusClientes(buscar);
        instancias.setCampoActual(txtId);
        txtId.requestFocus();
        buscar.noEncontrado(nit);
        buscar.show();
    }

    public void cargarCliente(String nit) {
        txtCodigoMascota.setText("");
        txtMascota.setText("");

        ModeloContacto nodo = instancias.getSql().getDatosTercero(nit);

        if (nodo.getId() != null) {
            txtCodigoMascota.setText(nit + "-");
            txtNombre.setText(nodo.getCompleta());
            txtId.setText(nodo.getId());
            txtIdTercero.setText(nodo.getIdSistema());
            txtTelefono.setText(nodo.getTelefono());
            ventanaMascotas(txtCodigoMascota.getText());
            return;
        }

        ventanaTerceros(nit);
    }

    public void cargarTerceroViejo(String Id) {
        ndMascota nodo = instancias.getSql().getDatosMascota(Id);
        ModeloContacto cliente = instancias.getSql().getDatosTercero(nodo.getCliente());
        if (nodo.getCodigo() != null) {
            txtMascota.setText(nodo.getNombre());
            txtNombre.setText(cliente.getNombre());
            txtTelefono.setText(cliente.getTelefono());
            boolean esta = false;
            for (int i = 0; i < tblGuarderia.getRowCount(); i++) {
                if (txtCodigoMascota.getText().equalsIgnoreCase(tblGuarderia.getValueAt(i, 0).toString())) {
                    esta = true;
                    break;
                }
            }

            if (esta) {
//                ndGuarderia nodoDos = instancias.getSql().getDatosGuarderia(txtCodigoMascota.getText());
//                this.codigo = nodoDos.getId();
//                txtfEntrada.setText(metodos.fecha(nodoDos.getfIngreso()));
//                try {
//                    txthEntrada.setText(Integer.parseInt(nodoDos.gethIngreso()) / 60 + ":" + Integer.parseInt(nodoDos.gethIngreso()) % 60);
//                } catch (Exception e) {
//                }
//                txtfSalida.setText(metodosGenerales.fecha());
//                txthSalida.setText(metodosGenerales.hora());
//                int ingreso = Integer.parseInt(nodoDos.gethIngreso());
//                System.out.println("ingreso: " + ingreso);
//                int salida = ((Integer.parseInt(metodosGenerales.hora().split(":")[0]) * 60) + Integer.parseInt(metodosGenerales.hora().split(":")[1])) - ingreso;
//                txtHoras.setText(metodos.restarFecha(txtfEntrada.getText(), txtfSalida.getText()) + "");
//                if (txtHoras.getText().equalsIgnoreCase("0")) {
//                    txtHoras.setText("1");
//                }
//                txtTotal.setText(big.setMoneda(big.getMoneda(txtValor.getText()).multiply(big.getBigDecimal(ingreso))));
            } else {
                txthEntrada.setText(metodosGenerales.hora());
                txtfEntrada.setText(metodosGenerales.fecha());
                txtTotalHoras.setText("0");
//                txtTotal.setText("0");
                this.idGuarderia = (String) null;
            }
            return;
        } else {
            ventanaMascotas(Id);
        }
    }

    public void cargarTercero(String Id) {

        ndMascota nodo = instancias.getSql().getDatosMascota(Id);
        ModeloContacto cliente = instancias.getSql().getDatosTercero(nodo.getCliente());

        if (nodo.getCodigo() != null) {
            txtMascota.setText(nodo.getNombre());
            txtId.setText(cliente.getId());
            txtIdTercero.setText(cliente.getIdSistema());
            txtNombre.setText(cliente.getNombre());
            txtTelefono.setText(cliente.getTelefono());

            if (nodo.getObservacion() != null) {
                txtObservacion.setText(nodo.getObservacion());
            }

            btnAggProductos.requestFocus();

            boolean esta = false;
            for (int i = 0; i < tblGuarderia.getRowCount(); i++) {
                if (txtCodigoMascota.getText().equalsIgnoreCase(tblGuarderia.getValueAt(i, 0).toString())) {
                    esta = true;
                    this.idGuarderia = tblGuarderia.getValueAt(i, 4).toString();
                    break;
                }
            }

            if (esta) {
                ndGuarderia nodoGuarderia = instancias.getSql().getDatosGuarderia(idGuarderia);

                txtfEntrada.setText(metodos.fecha(nodoGuarderia.getfIngreso()));
                txthEntrada.setText(nodoGuarderia.gethIngreso());

                Long dias = metodos.restarFecha(metodos.fecha(nodoGuarderia.getfIngreso()), metodosGenerales.fecha());
                String horaActual = metodosGenerales.hora();
                String horaEntrada = txthEntrada.getText();

                int actualHora = Integer.parseInt(horaActual.split(":")[0]);
                int actualMinuto = Integer.parseInt(horaActual.split(":")[1]);

                int entradaHora = Integer.parseInt(horaEntrada.split(":")[0]);
                int entradaMinuto = Integer.parseInt(horaEntrada.split(":")[1]);

                int totalHoras;

                if (actualHora > entradaHora) {
                    totalHoras = actualHora - entradaHora;
                } else {
                    if (dias == 0) {
                        dias = dias;
                    } else {
                        dias = dias - 1;
                    }

                    totalHoras = (actualHora + 24) - entradaHora;
                }

                if (totalHoras == 24) {
                    totalHoras = 0;
                }

                if (actualMinuto >= entradaMinuto) {
                    totalHoras = totalHoras + 1;
                }

                txtTotalDias.setText(dias.toString());
                txtTotalHoras.setText(String.valueOf(totalHoras));

                BigDecimal totalPorHoras = big.getMoneda(txtValorHora.getText()).multiply(big.getBigDecimal(txtTotalHoras.getText()));
                BigDecimal totalPorDias = big.getMoneda(txtValorDia.getText()).multiply(big.getBigDecimal(dias));
                BigDecimal totalAPagar = totalPorHoras.add(totalPorDias);

                if (btnGuardar.getText().equalsIgnoreCase("GUARDAR")) {
                    txtCodigoMascota.setEditable(false);
                }

                txtTotal.setText(big.setMoneda(totalAPagar));
                btnGuardar.setBackground(new java.awt.Color(241, 148, 138));
                btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/salida.png"))); // NOI18N
                btnGuardar.setText("SALIDA");

            } else {
                btnGuardar.setBackground(new java.awt.Color(46, 204, 113));
                btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
                btnGuardar.setText("GUARDAR");
            }
        } else {
            this.idGuarderia = (String) null;
            ventanaMascotas(Id);
        }
    }

    public void ventanaProductos(String codigo) {
//        buscProductos buscar = new buscProductos(null, rootPaneCheckingEnabled);
//        buscar.setLocationRelativeTo(null);
//        buscar.setOpc("guarderia");
//        buscar.setAgenda(true);
//        instancias.setBusProductos(buscar);
//        instancias.setCampoActual(txtCodProducto);
//        txtCodProducto.requestFocus();
//        buscar.setInstancia(instancias, false);
//        buscar.noEncontrado(codigo.replace("'", "//"));
//        buscar.show();
    }

    public void ventanaMascotas(String nit) {
        buscMascotas buscar = new buscMascotas(instancias.getMenu(), rootPaneCheckingEnabled, "guarderia", "");
        buscar.setLocationRelativeTo(null);
        buscar.setGuarderia(this);
        instancias.setBuscMascotas(buscar);
        instancias.setCampoActual(txtCodigoMascota);
        txtCodigoMascota.requestFocus();
        buscar.setInstancia(instancias);
        buscar.noEncontrado(nit);
        buscar.show();
    }

    public void actualizarTabla() {

        Object[][] datos = instancias.getSql().getRegistrosGuarderia();

        int j = tblGuarderia.getRowCount();
        for (int i = 0; i < j; i++) {
            modelo.removeRow(0);
        }

        for (Object[] dato : datos) {
            dato[2] = metodos.fecha(dato[2].toString());
            modelo.addRow(dato);
        }

    }

    public int positivo(int numero) {
        int numero2;

        if (numero == 60) {
            numero2 = 0;
        } else if (numero < 0) {
            numero2 = numero * -1;
        } else {
            numero2 = numero;
        }

        return numero2;
    }

    public String agregarCeros(int numero) {
        String cadena;

        if (numero == 60) {
            cadena = "00";
        } else if (numero < 10) {
            cadena = "0" + numero;
        } else {
            cadena = "" + numero;
        }
        return cadena;
    }

    public void Guardar() {

        String id = "";
        if (idGuarderia.equals("")) {
            id = "GUARDERIA-" + instancias.getSql().getNumConsecutivo("GUARDERIA")[0];
        } else {
            id = this.idGuarderia;
        }

        Object[] vector = {id, txtCodigoMascota.getText(), txtMascota.getText(), metodos.fechaConsulta(metodosGenerales.fecha()),
            metodos.fechaConsulta(metodosGenerales.fecha()), metodosGenerales.hora(), metodosGenerales.hora(), txtTotalDias.getText(), "0",
            "0", "0", "0", txtObservacion.getText(), "", instancias.getUsuario(), instancias.getTerminal(), false, txtIdTercero.getText()
        };

        ndGuarderia nodo = metodos.llenarGuarderia(vector);

        if (!instancias.getSql().agregarGuarderia(nodo)) {
            metodos.msgError(this, "Hubo un error al guardar este registro");
            return;
        }

        modelo.addRow(new Object[]{txtCodigoMascota.getText(), txtNombre.getText(), metodosGenerales.fecha(), metodosGenerales.hora(), id});
        if (!instancias.getSql().aumentarConsecutivo("GUARDERIA", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("GUARDERIA")[0]) + 1)) {
            metodos.msgError(this, "Hubo un problema al guardar en el consecutivo");
        }

        metodos.msgExito(this, "Guardería registrada con éxito");
        txtCodigoMascota.requestFocus();
        idGuarderia = "";
        btnNuevoActionPerformed(null);
    }

    public void Salida() {
        instancias.getFactura().desdeGuarderia(txtIdTercero.getText(), idGuarderia, txtTotalHoras.getText(), txtTotalDias.getText());
        instancias.getSql().modificarGuarderia(idGuarderia, txtTotalHoras.getText(), txtTotalDias.getText(), big.getMoneda(txtTotal.getText()));
        btnNuevoActionPerformed(null);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAggProductos;
    private javax.swing.JButton btnBuscMascotas;
    private javax.swing.JButton btnBuscTerceros;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lbRazon;
    private javax.swing.JLabel lbRazon1;
    private javax.swing.JLabel lbRazon10;
    private javax.swing.JLabel lbRazon11;
    private javax.swing.JLabel lbRazon12;
    private javax.swing.JLabel lbRazon15;
    private javax.swing.JLabel lbRazon17;
    private javax.swing.JLabel lbRazon2;
    private javax.swing.JLabel lbRazon3;
    private javax.swing.JLabel lbRazon4;
    private javax.swing.JLabel lbRazon6;
    private javax.swing.JLabel lbRazon7;
    private javax.swing.JLabel lbRazon8;
    private javax.swing.JPanel pnlFormulario;
    private javax.swing.JScrollPane scrFormulario;
    private javax.swing.JTable tblGuarderia;
    private javax.swing.JTextField txtCodigoMascota;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtIdTercero;
    private javax.swing.JTextField txtMascota;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextArea txtObservacion;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JTextField txtTotalDias;
    private javax.swing.JTextField txtTotalHoras;
    private javax.swing.JTextField txtValorDia;
    private javax.swing.JTextField txtValorHora;
    private javax.swing.JTextField txtfEntrada;
    private javax.swing.JTextField txthEntrada;
    // End of variables declaration//GEN-END:variables

}
