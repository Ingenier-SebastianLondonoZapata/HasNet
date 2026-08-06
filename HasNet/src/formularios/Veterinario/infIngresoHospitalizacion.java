package formularios.Veterinario;

import Vista.Productos.VistaBuscadorProductos;
import clases.Instancias;
import Utilidades.BaseDatos.SQL;
import clases.Veterinario.ndIngresoHospitalizacion;
import clases.Veterinario.ndMascota;
import Modelo.Terceros.ModeloContacto;
import clases.big;
import clases.metodosGenerales;
import clases.productos.ndProducto;
import formularios.terceros.buscClientes;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class infIngresoHospitalizacion extends javax.swing.JInternalFrame {

    metodosGenerales metodos = new metodosGenerales();
    private Instancias instancias;

    private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension dimBarra = null;
    DefaultTableModel modelo;
    String codigo, simbolo;

    public infIngresoHospitalizacion() {

        initComponents();

        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
        dimBarra = Barra.getPreferredSize();
        Barra.setSize(0, 0);
        Barra.setPreferredSize(new Dimension(0, 0));
        setBorder(null);
        repaint();
        instancias = Instancias.getInstancias();
        modelo = (DefaultTableModel) tblHospitalizacion.getModel();
        codigo = (String) null;

        cargarRegistros();

        ndProducto nodo = instancias.getSql().getDatosProducto("HSP1", "bdProductos");
        try {
            txtValorHora.setText(big.setMoneda(big.getBigDecimal(nodo.getL1())));
        } catch (Exception e) {
            txtValorHora.setText(big.setMoneda(big.getBigDecimal("1000")));
        }

        ndProducto nodo1 = instancias.getSql().getDatosProducto("HSP2", "bdProductos");
        try {
            txtValorDia.setText(big.setMoneda(big.getBigDecimal(nodo1.getL1())));
        } catch (Exception e) {
            txtValorDia.setText(big.setMoneda(big.getBigDecimal("5000")));
        }

        btnControlMedico.setVisible(false);
        txtIdSistema.setVisible(false);
        this.simbolo = instancias.getSimbolo();
    }

    public void nuevaMascota(String codigo) {
        txtCodigoMascota.setText(codigo);
        cargarTercero(codigo);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrFormulario = new javax.swing.JScrollPane();
        pnlFormulario = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHospitalizacion = new javax.swing.JTable();
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
        btnGuardar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btnBuscMascota = new javax.swing.JButton();
        btnBuscTerceros = new javax.swing.JButton();
        btnInforme = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        lbRazon6 = new javax.swing.JLabel();
        txtfEntrada = new javax.swing.JTextField();
        lbRazon10 = new javax.swing.JLabel();
        txtDias = new javax.swing.JTextField();
        lbRazon8 = new javax.swing.JLabel();
        lbRazon11 = new javax.swing.JLabel();
        txthEntrada = new javax.swing.JTextField();
        txtHoras = new javax.swing.JTextField();
        lbRazon15 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        lbRazon12 = new javax.swing.JLabel();
        txtValorDia = new javax.swing.JTextField();
        lbRazon7 = new javax.swing.JLabel();
        txtValorHora = new javax.swing.JTextField();
        btnControlMedico = new javax.swing.JButton();
        txtIdSistema = new javax.swing.JTextField();

        setTitle("Factura");

        pnlFormulario.setBackground(new java.awt.Color(255, 255, 255));

        tblHospitalizacion.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        tblHospitalizacion.setModel(new javax.swing.table.DefaultTableModel(
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
        tblHospitalizacion.setRowHeight(24);
        tblHospitalizacion.getTableHeader().setReorderingAllowed(false);
        tblHospitalizacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHospitalizacionMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblHospitalizacion);
        if (tblHospitalizacion.getColumnModel().getColumnCount() > 0) {
            tblHospitalizacion.getColumnModel().getColumn(4).setMinWidth(0);
            tblHospitalizacion.getColumnModel().getColumn(4).setPreferredWidth(0);
            tblHospitalizacion.getColumnModel().getColumn(4).setMaxWidth(0);
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
        txtNombre.setName("Cliente"); // NOI18N
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreKeyReleased(evt);
            }
        });

        lbRazon2.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbRazon2.setText("Telefono:");

        txtTelefono.setEditable(false);
        txtTelefono.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
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
        lbRazon17.setText("Observaciones:");

        txtObservacion.setColumns(20);
        txtObservacion.setLineWrap(true);
        txtObservacion.setRows(3);
        jScrollPane2.setViewportView(txtObservacion);

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

        btnBuscMascota.setBackground(new java.awt.Color(204, 204, 204));
        btnBuscMascota.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnBuscMascota.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscMascota.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar1.png"))); // NOI18N
        btnBuscMascota.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnBuscMascota.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscMascota.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnBuscMascota.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnBuscMascota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscMascotaActionPerformed(evt);
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

        btnInforme.setBackground(new java.awt.Color(247, 220, 111));
        btnInforme.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnInforme.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/imprimir.png"))); // NOI18N
        btnInforme.setText("IMP. INFORME");
        btnInforme.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnInforme.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnInforme.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnInforme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInformeActionPerformed(evt);
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
                    .addComponent(lbRazon17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(lbRazon1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbRazon3, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnBuscTerceros, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(lbRazon2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtNombre)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lbRazon, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCodigoMascota, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBuscMascota, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 4, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnInforme)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGuardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                    .addComponent(btnBuscMascota, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtMascota)
                    .addComponent(lbRazon4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbRazon17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnNuevo)
                    .addComponent(btnInforme))
                .addGap(19, 19, 19))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        lbRazon6.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbRazon6.setText("Fecha Entrada:");

        txtfEntrada.setEditable(false);
        txtfEntrada.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtfEntrada.setText("12/12/2018");
        txtfEntrada.setName("Descripción"); // NOI18N
        txtfEntrada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtfEntradaKeyReleased(evt);
            }
        });

        lbRazon10.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbRazon10.setText("Total días:");

        txtDias.setEditable(false);
        txtDias.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtDias.setText("0");
        txtDias.setName("Descripción"); // NOI18N
        txtDias.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDiasKeyReleased(evt);
            }
        });

        lbRazon8.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbRazon8.setText("Total horas:");

        lbRazon11.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbRazon11.setText("Hora:");

        txthEntrada.setEditable(false);
        txthEntrada.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txthEntrada.setName("Descripción"); // NOI18N
        txthEntrada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txthEntradaKeyReleased(evt);
            }
        });

        txtHoras.setEditable(false);
        txtHoras.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtHoras.setText("0");
        txtHoras.setName("Descripción"); // NOI18N
        txtHoras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHorasActionPerformed(evt);
            }
        });
        txtHoras.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtHorasKeyReleased(evt);
            }
        });

        lbRazon15.setFont(new java.awt.Font("Century Gothic", 1, 15)); // NOI18N
        lbRazon15.setText("TOTAL A PAGAR:");

        txtTotal.setEditable(false);
        txtTotal.setFont(new java.awt.Font("Century Gothic", 1, 26)); // NOI18N
        txtTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTotal.setText("0");
        txtTotal.setName("Descripción"); // NOI18N
        txtTotal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTotalKeyReleased(evt);
            }
        });

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        lbRazon12.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbRazon12.setText("Valor por día:");

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

        lbRazon7.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbRazon7.setText("Valor por hora:");

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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lbRazon7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtValorHora, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                        .addComponent(lbRazon12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtValorDia, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lbRazon15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbRazon6)
                            .addComponent(lbRazon10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtfEntrada)
                                    .addComponent(txtDias, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(28, 28, 28)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lbRazon8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbRazon11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txthEntrada)
                                    .addComponent(txtHoras, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
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
                            .addComponent(txtDias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbRazon8)
                        .addComponent(txtHoras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbRazon15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtValorDia, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbRazon12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtValorHora, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lbRazon7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );

        btnControlMedico.setBackground(new java.awt.Color(0, 0, 0));
        btnControlMedico.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnControlMedico.setForeground(new java.awt.Color(255, 255, 255));
        btnControlMedico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/historia.png"))); // NOI18N
        btnControlMedico.setText("DETALLADO");
        btnControlMedico.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnControlMedico.setEnabled(false);
        btnControlMedico.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnControlMedico.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnControlMedico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnControlMedicoActionPerformed(evt);
            }
        });

        txtIdSistema.setEditable(false);
        txtIdSistema.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtIdSistema.setName("Descripción"); // NOI18N
        txtIdSistema.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIdSistemaKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout pnlFormularioLayout = new javax.swing.GroupLayout(pnlFormulario);
        pnlFormulario.setLayout(pnlFormularioLayout);
        pnlFormularioLayout.setHorizontalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlFormularioLayout.createSequentialGroup()
                        .addComponent(btnControlMedico)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIdSistema, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlFormularioLayout.setVerticalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlFormularioLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnControlMedico)
                            .addComponent(txtIdSistema, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 61, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE))
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
            .addGroup(layout.createSequentialGroup()
                .addComponent(scrFormulario)
                .addGap(0, 0, 0))
        );

        getAccessibleContext().setAccessibleName("Ingreso hospitalización");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCodigoMascotaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoMascotaKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cargarTercero(txtCodigoMascota.getText());
        } else {
            txtMascota.setText("");
        }
    }//GEN-LAST:event_txtCodigoMascotaKeyReleased

    private void txtNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreKeyReleased

    private void txtTelefonoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoKeyReleased

    private void txtCodigoMascotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoMascotaActionPerformed

    }//GEN-LAST:event_txtCodigoMascotaActionPerformed

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

        cargarRegistros();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void txtfEntradaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtfEntradaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtfEntradaKeyReleased

    private void txthEntradaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txthEntradaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txthEntradaKeyReleased

    private void txtDiasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiasKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiasKeyReleased

    private void txtHorasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHorasKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHorasKeyReleased

    private void txtTotalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalKeyReleased

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        for (int x = 0; x < jPanel1.getComponentCount(); x++) {
            if (jPanel1.getComponent(x) instanceof JTextField) {
                JTextField textField = (JTextField) jPanel1.getComponent(x);
                textField.setText("");
            }
        }

        txtTotal.setText(this.simbolo + " 0");
        txtDias.setText(this.simbolo + " 0");
        txtHoras.setText(this.simbolo + " 0");

        txtIdSistema.setText("");
        txtObservacion.setText("");

        btnControlMedico.setEnabled(false);
        this.codigo = (String) null;

//        txtValorHoraDia.setText(big.setMoneda(big.getBigDecimal("0")));
//        txtValorDia.setText(big.setMoneda(big.getBigDecimal("0")));
        btnGuardar.setEnabled(true);
        txtObservacion.setEditable(true);
        txtCodigoMascota.setEditable(true);
//        txtCodProducto.setEditable(true);
        btnGuardar.setBackground(new java.awt.Color(46, 204, 113));
        btnGuardar.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(0, 0, 0));
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
        btnGuardar.setText("GUARDAR");
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void txtMascotaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMascotaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMascotaKeyReleased

    private void tblHospitalizacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHospitalizacionMouseClicked
        if (evt.getClickCount() == 1) {
            btnNuevoActionPerformed(null);
            txtCodigoMascota.setText(tblHospitalizacion.getValueAt(tblHospitalizacion.getSelectedRow(), 0).toString());
            cargarTercero(txtCodigoMascota.getText());
            btnControlMedico.setEnabled(true);
        }

        if (evt.getClickCount() == 2) {
            btnNuevoActionPerformed(null);
            txtCodigoMascota.setText(tblHospitalizacion.getValueAt(tblHospitalizacion.getSelectedRow(), 0).toString());
            cargarTercero(txtCodigoMascota.getText());
            btnControlMedicoActionPerformed(null);
        }
    }//GEN-LAST:event_tblHospitalizacionMouseClicked

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

    private void btnControlMedicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnControlMedicoActionPerformed
        try {
            if (txtCodigoMascota.getText() != null) {
                instancias.getHospitalizacion().setMascota(txtCodigoMascota.getText(), codigo,
                        tblHospitalizacion.getValueAt(tblHospitalizacion.getSelectedRow(), 4).toString());
                instancias.getHospitalizacion().setSelected(true);
                instancias.getMenu().cambiarTitulo("HOSPITALIZACIÓN DE " + txtMascota.getText().toUpperCase());
            } else {
                metodos.msgError(this, "Cargue primero una mascota");
            }
        } catch (PropertyVetoException ex) {
            Logger.getLogger(infMascotas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnControlMedicoActionPerformed

    private void btnBuscMascotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscMascotaActionPerformed
        ventanaMascotas(txtCodigoMascota.getText());
    }//GEN-LAST:event_btnBuscMascotaActionPerformed

    private void btnBuscTercerosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscTercerosActionPerformed
        ventanaTerceros("");
    }//GEN-LAST:event_btnBuscTercerosActionPerformed

    private void btnInformeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInformeActionPerformed
        if (btnGuardar.getText().equals("SALIDA")) {
            instancias.getReporte().ver_Hospitalizacion(this.codigo);
        } else {
            String consecutivo = "INGHOSPITAL-" + metodos.msgIngresarEnter(this, "Documento a reimprimir");
            if (consecutivo.equals("INGHOSPITAL-")) {
                return;
            }
            instancias.getReporte().ver_Hospitalizacion(consecutivo);
        }
    }//GEN-LAST:event_btnInformeActionPerformed

    private void txtHorasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHorasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHorasActionPerformed

    private void txtValorDiaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorDiaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValorDiaKeyReleased

    private void txtValorHoraKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorHoraKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValorHoraKeyReleased

    private void txtIdSistemaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdSistemaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdSistemaKeyReleased

    public void cargarRegistros() {
        while (tblHospitalizacion.getRowCount() > 0) {
            modelo.removeRow(0);
        }

        Object[][] datos = instancias.getSql().getRegistrosIngresoHospital();
        for (Object[] dato : datos) {
            dato[2] = metodos.fecha(dato[2].toString());
            modelo.addRow(dato);
        }
    }

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
            txtIdSistema.setText(nodo.getIdSistema());
            txtId.setText(nodo.getId());
            txtTelefono.setText(nodo.getTelefono());
            ventanaMascotas(txtCodigoMascota.getText());
            return;
        }

        Object[][] nuevoPaciente = instancias.getSql().getNuevoTercero(nit);
        try {
            if (!nuevoPaciente[0][0].toString().equalsIgnoreCase("") || !nuevoPaciente[0][0].toString().equalsIgnoreCase(null)) {
                txtNombre.setText(nuevoPaciente[0][1].toString());
                txtId.setText(nuevoPaciente[0][0].toString());
                txtTelefono.setText(nuevoPaciente[0][2].toString());
                txtCodigoMascota.setText(nit + "-");
                ventanaMascotas(txtCodigoMascota.getText());
                return;
            }
        } catch (Exception e) {
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
            for (int i = 0; i < tblHospitalizacion.getRowCount(); i++) {
                if (txtCodigoMascota.getText().equalsIgnoreCase(tblHospitalizacion.getValueAt(i, 0).toString())) {
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
//                int salida = ((Integer.parseInt(metodosGenerales.hora().split(":")[0]) * 60) + Integer.parseInt(metodosGenerales.hora().split(":")[1])) - ingreso;
//                txtHoras.setText(metodos.restarFecha(txtfEntrada.getText(), txtfSalida.getText()) + "");
//                if (txtHoras.getText().equalsIgnoreCase("0")) {
//                    txtHoras.setText("1");
//                }
//                txtTotal.setText(big.setMoneda(big.getMoneda(txtValor.getText()).multiply(big.getBigDecimal(ingreso))));
            } else {
                txthEntrada.setText(metodosGenerales.hora());
                txtfEntrada.setText(metodosGenerales.fecha());
                txtHoras.setText("0");
//                txtTotal.setText("0");
                this.codigo = (String) null;
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
            txtIdSistema.setText(cliente.getIdSistema());
            txtNombre.setText(cliente.getNombre());
            txtTelefono.setText(cliente.getTelefono());

            boolean esta = false;

            for (int i = 0; i < tblHospitalizacion.getRowCount(); i++) {
                if (txtCodigoMascota.getText().equalsIgnoreCase(tblHospitalizacion.getValueAt(i, 0).toString())) {
                    esta = true;
                    this.codigo = tblHospitalizacion.getValueAt(i, 4).toString();
                    break;
                }
            }

            if (esta) {

                //si la mascota ya se encuentra registrada para una salida.
                ndIngresoHospitalizacion nodoGuarderia = instancias.getSql().getDatosIngresoHospitalizacion(codigo);
                txtfEntrada.setText(metodos.fecha(nodoGuarderia.getfIngreso()));

                txthEntrada.setText(nodoGuarderia.gethIngreso());
                txtObservacion.setText(nodoGuarderia.getObservacion());

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

                txtDias.setText(dias.toString());
                txtHoras.setText(String.valueOf(totalHoras));

                if (btnGuardar.getText().equalsIgnoreCase("GUARDAR")) {
                    txtCodigoMascota.setEditable(false);
                    txtObservacion.setEditable(false);
                }

                cargarTotales();

                btnGuardar.setBackground(new java.awt.Color(255, 153, 153));
                btnGuardar.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
                btnGuardar.setForeground(new java.awt.Color(0, 0, 0));
                btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/salida.png"))); // NOI18N
                btnGuardar.setText("SALIDA");

            } else {
//                System.out.println("entro al else de la condicion");

                String hora = metodosGenerales.hora();
                String[] split = hora.split(":");
                if (Integer.parseInt(split[0]) < 10) {
                    split[0] = "0" + split[0];
                }
                if (Integer.parseInt(split[1]) < 10) {
                    split[1] = "0" + split[1];
                }
                String horaEntrada = split[0] + ":" + split[1];
                txthEntrada.setText(horaEntrada);
                txtfEntrada.setText(metodosGenerales.fecha());
                txtHoras.setText("0");
                txtDias.setText("0");
                txtTotal.setText(big.setMoneda(big.getBigDecimal("0")));
//                txtValorHoraDia.setText(big.setMoneda(big.getBigDecimal("0")));
//                txtValorDia.setText(big.setMoneda(big.getBigDecimal("0")));
//                ventanaProductos(txtProducto.getText());
                this.codigo = (String) null;

                btnGuardar.setBackground(new java.awt.Color(46, 204, 113));
                btnGuardar.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
                btnGuardar.setForeground(new java.awt.Color(0, 0, 0));
                btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
                btnGuardar.setText("GUARDAR");

            }

        } else {
            this.codigo = (String) null;
            ventanaMascotas(Id);
        }

    }

    public void ventanaProductos(String codigo) {
//        VistaBuscadorProductos buscar = new VistaBuscadorProductos(null, rootPaneCheckingEnabled);
//        buscar.setLocationRelativeTo(null);
//        buscar.setOpc("ingresoHospital");
//        buscar.setAgenda(true);
//        instancias.setBusProductos(buscar);
//        instancias.setCampoActual(txtCodProducto);
//        txtCodProducto.requestFocus();
//        buscar.setInstancia(instancias, false);
//        buscar.noEncontrado(codigo.replace("'", "//"));
//        buscar.show();
    }

    public void ventanaMascotas(String nit) {
        buscMascotas buscar = new buscMascotas(instancias.getMenu(), rootPaneCheckingEnabled, "ingresoHospital", "");
        buscar.setLocationRelativeTo(null);
        buscar.setIngresoHospitalizacion(this);
        instancias.setBuscMascotas(buscar);
        instancias.setCampoActual(txtCodigoMascota);
        txtCodigoMascota.requestFocus();
        buscar.setInstancia(instancias);
        buscar.noEncontrado(nit);
        buscar.show();
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

    public void cargarTotales() {
        BigDecimal totalPorHoras = big.getMoneda(txtValorHora.getText()).multiply(big.getBigDecimal(txtHoras.getText()));
        BigDecimal totalPorDias = big.getMoneda(txtValorDia.getText()).multiply(big.getBigDecimal(txtDias.getText()));
        BigDecimal totalAPagar = totalPorHoras.add(totalPorDias);

        if (btnGuardar.getText().equalsIgnoreCase("GUARDAR")) {
            txtCodigoMascota.setEditable(false);
        }

        txtTotal.setText(big.setMoneda(totalAPagar));
    }

    public void Guardar() {

        String salida = metodos.fechaConsulta(metodosGenerales.fecha());
        String id = "";
        String IdHospitalizacion = "";

        if (this.codigo == null) {
            id = "INGHOSPITAL-" + instancias.getSql().getNumConsecutivo("INGHOSPITAL")[0];
            IdHospitalizacion = instancias.getSql().getNumConsecutivo("INGHOSPITAL")[0].toString();
        } else {
            id = this.codigo;
        }

        if (!instancias.getSql().agregarHospitalizacion(IdHospitalizacion, txtCodigoMascota.getText(), metodos.fechaConsulta(metodosGenerales.fechaHora()),
                instancias.getUsuario())) {
            metodos.msgError(this, "Error al guardar hospitalización");
            return;
        }

        Object[] vector = {id, txtCodigoMascota.getText(), txtMascota.getText(), metodos.fechaConsulta(txtfEntrada.getText()),
            salida, txthEntrada.getText(), "", txtDias.getText(), "0", big.getMoneda(txtTotal.getText()) + "",
            "", "", txtObservacion.getText(), "", instancias.getUsuario(), instancias.getTerminal(), false, txtIdSistema.getText()
        };

        ndIngresoHospitalizacion nodo = metodos.llenarIngresoHospitalizacion(vector);
        if (!instancias.getSql().agregarIngresoHospital(nodo)) {
            metodos.msgError(this, "Hubo un error al guardar este registro");
            return;
        }
        modelo.addRow(new Object[]{txtCodigoMascota.getText(), txtNombre.getText(), txtfEntrada.getText(), txthEntrada.getText()});

//        instancias.getReporte().verTirilla1(instancias.getInformacionEmpresa(), instancias.getLegal(), id, "Original");
        if (!instancias.getSql().aumentarConsecutivo("INGHOSPITAL", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("INGHOSPITAL")[0]) + 1)) {
            metodos.msgError(this, "Hubo un problema al guardar en el consecutivo");
        }

        if (!instancias.getSql().aumentarConsecutivo("HOSPITALIZACION", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("HOSPITALIZACION")[0]) + 1)) {
            metodos.msgError(this, "Hubo un problema al guardar en el consecutivo de los controles de hospitalización");
        }

        metodos.msgExito(this, "Ingreso registrado con éxito");
        txtCodigoMascota.requestFocus();
        this.codigo = null;
        btnNuevoActionPerformed(null);
    }

    public void Salida() {
        instancias.getFactura().desdeHospitalizacion(txtId.getText(), this.codigo, txtHoras.getText(), txtDias.getText());
        instancias.getFacturaContenedor().setSelected(true);
        btnNuevoActionPerformed(null);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscMascota;
    private javax.swing.JButton btnBuscTerceros;
    private javax.swing.JButton btnControlMedico;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnInforme;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
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
    private javax.swing.JTable tblHospitalizacion;
    private javax.swing.JTextField txtCodigoMascota;
    private javax.swing.JTextField txtDias;
    private javax.swing.JTextField txtHoras;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtIdSistema;
    private javax.swing.JTextField txtMascota;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextArea txtObservacion;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JTextField txtValorDia;
    private javax.swing.JTextField txtValorHora;
    private javax.swing.JTextField txtfEntrada;
    private javax.swing.JTextField txthEntrada;
    // End of variables declaration//GEN-END:variables

}
