package formularios.Parqueadero;

import clases.Instancias;
import clases.Parqueadero.ndLavado;
import Utilidades.BaseDatos.SQL;
import clases.big;
import clases.metodosGenerales;
import clases.productos.ndProducto;
import Modelo.Terceros.ModeloContacto;
import Vista.Solicitudes.vistaSolicitarPermisos;
import formularios.Tesoreria.dlgTipoEgreso;
import formularios.productos.buscProductos;
import formularios.terceros.buscClientes;
import formularios.terceros.buscEmpleados;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.DefaultCellEditor;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;

public class infLavadero extends javax.swing.JInternalFrame {

    metodosGenerales metodos = new metodosGenerales();
    private Instancias instancias;
    private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension dimBarra = null;
    DefaultTableModel modelo;
    String factura, simbolo = "";
    Boolean desdeId = false;

    public infLavadero() {
        initComponents();
        this.instancias = Instancias.getInstancias();
        btnReImprimir.setVisible(false);
        btnAnular.setVisible(false);

        instancias = Instancias.getInstancias();
        simbolo = instancias.getSimbolo();

        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
        dimBarra = Barra.getPreferredSize();
        Barra.setSize(0, 0);
        Barra.setPreferredSize(new Dimension(0, 0));
        setBorder(null);
        repaint();

        Object[][] empleados = instancias.getSql().getEmpleados();
        for (int i = 0; i < empleados.length; i++) {
            cmbEmpleados.addItem(empleados[i][1].toString());
        }

        TableColumn tc = tblServicios.getColumnModel().getColumn(3);
        TableCellEditor tce = new DefaultCellEditor(cmbEmpleados);
        tc.setCellEditor(tce);
        cargarTabla();
        lbNoFactura.setText(instancias.getSql().getNumConsecutivo("LAV")[0].toString());

        txtoServicio.setVisible(false);
        txtIdSistema.setVisible(false);
        txtIdSistema1.setVisible(false);
    }

    public String getFactura() {
        return factura;
    }

    public void setFactura(String factura) {
        this.factura = factura;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        popBorrar = new javax.swing.JMenuItem();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        popBorrar1 = new javax.swing.JMenuItem();
        scrFormulario = new javax.swing.JScrollPane();
        pnlFormulario = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        lbNit2 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        lbRazon2 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        lbDepartamento1 = new javax.swing.JLabel();
        lbDepartamento2 = new javax.swing.JLabel();
        txtTipoVehiculo = new javax.swing.JTextField();
        lbDepartamento3 = new javax.swing.JLabel();
        txtMarca = new javax.swing.JTextField();
        cmbEmpleados = new javax.swing.JComboBox();
        txtPlaca = new javax.swing.JTextField();
        btnBusTercero = new javax.swing.JButton();
        btnBusProd2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        lbNit11 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtObservaciones = new javax.swing.JTextArea();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        lbNit1 = new javax.swing.JLabel();
        txtServicio = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblServicios = new javax.swing.JTable();
        btnBusProd = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        btnReImprimir = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnFacturar = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        lbNit15 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        rbtCredito = new javax.swing.JRadioButton();
        lbNit12 = new javax.swing.JLabel();
        txtDiasPlazo = new javax.swing.JTextField();
        btnAnular = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        lbNit9 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        txtL1 = new javax.swing.JTextField();
        lbDepartamento = new javax.swing.JLabel();
        lbNit10 = new javax.swing.JLabel();
        dtFechaRealizado = new datechooser.beans.DateChooserCombo();
        jPanel5 = new javax.swing.JPanel();
        lbNit3 = new javax.swing.JLabel();
        txtEmpleado1 = new javax.swing.JTextField();
        lbRazon1 = new javax.swing.JLabel();
        txtRazon1 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblLiquidaciones = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btnGuardar2 = new javax.swing.JButton();
        cmbEstados = new javax.swing.JComboBox();
        lbRazon3 = new javax.swing.JLabel();
        btnBuscTerceros1 = new javax.swing.JButton();
        btnGuardar1 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        lbFacturaNo = new javax.swing.JTextField();
        lbNoFactura = new javax.swing.JTextField();
        btnBuscTerceros = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        txtoServicio = new javax.swing.JTextField();
        txtIdSistema = new javax.swing.JTextField();
        txtIdSistema1 = new javax.swing.JTextField();

        popBorrar.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        popBorrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/eliminar-cancelar-icono-4935-16.png"))); // NOI18N
        popBorrar.setText("Borrar");
        popBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                popBorrarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(popBorrar);

        popBorrar1.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        popBorrar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/eliminar-cancelar-icono-4935-16.png"))); // NOI18N
        popBorrar1.setText("Borrar");
        popBorrar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                popBorrar1ActionPerformed(evt);
            }
        });
        jPopupMenu2.add(popBorrar1);

        setTitle("Factura");

        pnlFormulario.setBackground(new java.awt.Color(255, 255, 255));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 16))); // NOI18N

        lbNit2.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit2.setText("Documento: *");

        txtNombre.setEditable(false);
        txtNombre.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtNombre.setName("Nombre"); // NOI18N
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreKeyReleased(evt);
            }
        });

        lbRazon2.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbRazon2.setText("Nombre:");

        txtId.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtId.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtId.setName("Codigo"); // NOI18N
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

        lbDepartamento1.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbDepartamento1.setText("Placa:");

        lbDepartamento2.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDepartamento2.setText("Tipo vehiculo:");

        txtTipoVehiculo.setEditable(false);
        txtTipoVehiculo.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtTipoVehiculo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTipoVehiculo.setName("Placa"); // NOI18N
        txtTipoVehiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTipoVehiculoActionPerformed(evt);
            }
        });
        txtTipoVehiculo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTipoVehiculoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTipoVehiculoKeyTyped(evt);
            }
        });

        lbDepartamento3.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDepartamento3.setText("Marca:");

        txtMarca.setEditable(false);
        txtMarca.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtMarca.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMarca.setName("Placa"); // NOI18N
        txtMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMarcaActionPerformed(evt);
            }
        });
        txtMarca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMarcaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMarcaKeyTyped(evt);
            }
        });

        cmbEmpleados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cmbEmpleadosMouseExited(evt);
            }
        });
        cmbEmpleados.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbEmpleadosItemStateChanged(evt);
            }
        });

        txtPlaca.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtPlaca.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPlaca.setName("Placa"); // NOI18N
        txtPlaca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPlacaActionPerformed(evt);
            }
        });
        txtPlaca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPlacaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPlacaKeyTyped(evt);
            }
        });

        btnBusTercero.setBackground(new java.awt.Color(204, 204, 204));
        btnBusTercero.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnBusTercero.setForeground(new java.awt.Color(255, 255, 255));
        btnBusTercero.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar1.png"))); // NOI18N
        btnBusTercero.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnBusTercero.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBusTercero.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnBusTercero.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnBusTercero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBusTerceroActionPerformed(evt);
            }
        });

        btnBusProd2.setBackground(new java.awt.Color(204, 204, 204));
        btnBusProd2.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnBusProd2.setForeground(new java.awt.Color(255, 255, 255));
        btnBusProd2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar1.png"))); // NOI18N
        btnBusProd2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnBusProd2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBusProd2.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnBusProd2.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnBusProd2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBusProd2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lbDepartamento1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbDepartamento2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbRazon2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbNit2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(cmbEmpleados, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtNombre, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtId, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                            .addComponent(txtPlaca))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnBusTercero, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBusProd2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addComponent(txtTipoVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lbDepartamento3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtMarca, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(cmbEmpleados, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lbNit2)
                                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnBusTercero, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lbDepartamento1)
                                .addComponent(txtPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnBusProd2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbRazon2)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbDepartamento2)
                            .addComponent(txtTipoVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbDepartamento3)
                            .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        lbNit11.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbNit11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit11.setText("Observaciones");

        txtObservaciones.setColumns(20);
        txtObservaciones.setLineWrap(true);
        txtObservaciones.setRows(2);
        jScrollPane3.setViewportView(txtObservaciones);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addComponent(lbNit11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(lbNit11)
                .addGap(3, 3, 3)
                .addComponent(jScrollPane3)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        lbNit1.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit1.setText("Codigo:");

        txtServicio.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtServicio.setName("Codigo"); // NOI18N
        txtServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtServicioActionPerformed(evt);
            }
        });
        txtServicio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtServicioKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtServicioKeyTyped(evt);
            }
        });

        tblServicios.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        tblServicios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Servicio", "Precio", "Empleado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblServicios.setComponentPopupMenu(jPopupMenu1);
        tblServicios.setRowHeight(23);
        tblServicios.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblServiciosKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblServiciosKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblServicios);
        if (tblServicios.getColumnModel().getColumnCount() > 0) {
            tblServicios.getColumnModel().getColumn(0).setMinWidth(0);
            tblServicios.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblServicios.getColumnModel().getColumn(0).setMaxWidth(0);
            tblServicios.getColumnModel().getColumn(2).setMinWidth(100);
            tblServicios.getColumnModel().getColumn(2).setPreferredWidth(100);
            tblServicios.getColumnModel().getColumn(2).setMaxWidth(100);
            tblServicios.getColumnModel().getColumn(3).setMinWidth(250);
            tblServicios.getColumnModel().getColumn(3).setPreferredWidth(250);
            tblServicios.getColumnModel().getColumn(3).setMaxWidth(250);
        }

        btnBusProd.setBackground(new java.awt.Color(204, 204, 204));
        btnBusProd.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnBusProd.setForeground(new java.awt.Color(255, 255, 255));
        btnBusProd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar1.png"))); // NOI18N
        btnBusProd.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnBusProd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBusProd.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnBusProd.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnBusProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBusProdActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        btnReImprimir.setBackground(new java.awt.Color(247, 220, 111));
        btnReImprimir.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnReImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/imprimir.png"))); // NOI18N
        btnReImprimir.setText("IMPRIMIR");
        btnReImprimir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReImprimir.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnReImprimir.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnReImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReImprimirActionPerformed(evt);
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

        btnFacturar.setBackground(new java.awt.Color(46, 204, 113));
        btnFacturar.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnFacturar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/caja Registradora.png"))); // NOI18N
        btnFacturar.setText("FACTURAR");
        btnFacturar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnFacturar.setEnabled(false);
        btnFacturar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnFacturar.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnFacturar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFacturarActionPerformed(evt);
            }
        });

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        lbNit15.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbNit15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit15.setText("Forma de pago");

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jRadioButton1.setSelected(true);
        jRadioButton1.setText("Contado");
        jRadioButton1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButton1ItemStateChanged(evt);
            }
        });

        buttonGroup1.add(rbtCredito);
        rbtCredito.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rbtCredito.setText("Credito");
        rbtCredito.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rbtCreditoItemStateChanged(evt);
            }
        });

        lbNit12.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lbNit12.setText("Plazo:");

        txtDiasPlazo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDiasPlazo.setEnabled(false);
        txtDiasPlazo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDiasPlazoKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbNit15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jRadioButton1)
                        .addGap(5, 5, 5)
                        .addComponent(rbtCredito)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbNit12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDiasPlazo, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(lbNit15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDiasPlazo, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jRadioButton1)
                        .addComponent(rbtCredito)
                        .addComponent(lbNit12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        btnAnular.setBackground(new java.awt.Color(241, 148, 138));
        btnAnular.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnAnular.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/borrar2.png"))); // NOI18N
        btnAnular.setText("ANULAR ");
        btnAnular.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAnular, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnReImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnFacturar, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 51, Short.MAX_VALUE)
                    .addComponent(btnFacturar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                    .addComponent(btnReImprimir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAnular, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        lbNit9.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbNit9.setText("Fecha registrado:");

        txtFecha.setEditable(false);
        txtFecha.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtL1.setEditable(false);
        txtL1.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        txtL1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtL1.setText("0");
        txtL1.setName("Lista 1"); // NOI18N
        txtL1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtL1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtL1KeyTyped(evt);
            }
        });

        lbDepartamento.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        lbDepartamento.setText("Total:");

        lbNit10.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbNit10.setText("Fecha realizado:");

        dtFechaRealizado.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 12));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(lbNit9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(lbNit10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dtFechaRealizado, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 161, Short.MAX_VALUE)
                .addComponent(lbDepartamento)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtL1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(dtFechaRealizado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbNit9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtFecha)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtL1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbNit10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lbNit1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBusProd, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnBusProd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtServicio, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbNit1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );

        jTabbedPane1.addTab("Nuevos servicios", jPanel2);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        lbNit3.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit3.setText("Documento: ");

        txtEmpleado1.setName("CC/NIT"); // NOI18N
        txtEmpleado1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmpleado1ActionPerformed(evt);
            }
        });
        txtEmpleado1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEmpleado1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEmpleado1KeyTyped(evt);
            }
        });

        lbRazon1.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbRazon1.setText("Nombre:");
        lbRazon1.setName(""); // NOI18N

        txtRazon1.setEditable(false);
        txtRazon1.setName("Nombre completo"); // NOI18N
        txtRazon1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtRazon1KeyReleased(evt);
            }
        });

        tblLiquidaciones.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        tblLiquidaciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Fecha", "Cliente", "Servicio", "Placa", "Tipo", "Marca", "Valor", "Utilidad", "Subtotal a pagar", "IdServicio", "A"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblLiquidaciones.setComponentPopupMenu(jPopupMenu2);
        tblLiquidaciones.setRowHeight(20);
        tblLiquidaciones.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tblLiquidaciones);
        if (tblLiquidaciones.getColumnModel().getColumnCount() > 0) {
            tblLiquidaciones.getColumnModel().getColumn(0).setMinWidth(35);
            tblLiquidaciones.getColumnModel().getColumn(0).setPreferredWidth(35);
            tblLiquidaciones.getColumnModel().getColumn(0).setMaxWidth(35);
            tblLiquidaciones.getColumnModel().getColumn(1).setMinWidth(90);
            tblLiquidaciones.getColumnModel().getColumn(1).setPreferredWidth(90);
            tblLiquidaciones.getColumnModel().getColumn(1).setMaxWidth(90);
            tblLiquidaciones.getColumnModel().getColumn(4).setMinWidth(60);
            tblLiquidaciones.getColumnModel().getColumn(4).setPreferredWidth(60);
            tblLiquidaciones.getColumnModel().getColumn(4).setMaxWidth(60);
            tblLiquidaciones.getColumnModel().getColumn(5).setMinWidth(75);
            tblLiquidaciones.getColumnModel().getColumn(5).setPreferredWidth(75);
            tblLiquidaciones.getColumnModel().getColumn(5).setMaxWidth(75);
            tblLiquidaciones.getColumnModel().getColumn(6).setMinWidth(100);
            tblLiquidaciones.getColumnModel().getColumn(6).setPreferredWidth(100);
            tblLiquidaciones.getColumnModel().getColumn(6).setMaxWidth(100);
            tblLiquidaciones.getColumnModel().getColumn(7).setMinWidth(90);
            tblLiquidaciones.getColumnModel().getColumn(7).setPreferredWidth(90);
            tblLiquidaciones.getColumnModel().getColumn(7).setMaxWidth(90);
            tblLiquidaciones.getColumnModel().getColumn(8).setMinWidth(90);
            tblLiquidaciones.getColumnModel().getColumn(8).setPreferredWidth(90);
            tblLiquidaciones.getColumnModel().getColumn(8).setMaxWidth(90);
            tblLiquidaciones.getColumnModel().getColumn(9).setMinWidth(100);
            tblLiquidaciones.getColumnModel().getColumn(9).setPreferredWidth(100);
            tblLiquidaciones.getColumnModel().getColumn(9).setMaxWidth(100);
            tblLiquidaciones.getColumnModel().getColumn(10).setMinWidth(0);
            tblLiquidaciones.getColumnModel().getColumn(10).setPreferredWidth(0);
            tblLiquidaciones.getColumnModel().getColumn(10).setMaxWidth(0);
        }

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel1.setText("Total a pagar: 0");

        btnGuardar2.setBackground(new java.awt.Color(46, 204, 113));
        btnGuardar2.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnGuardar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/facturar.png"))); // NOI18N
        btnGuardar2.setText("LIQUIDAR");
        btnGuardar2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar2.setEnabled(false);
        btnGuardar2.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnGuardar2.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnGuardar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardar2ActionPerformed(evt);
            }
        });

        cmbEstados.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        cmbEstados.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "PENDIENTE", "PAGADO" }));
        cmbEstados.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbEstadosItemStateChanged(evt);
            }
        });
        cmbEstados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbEstadosActionPerformed(evt);
            }
        });

        lbRazon3.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbRazon3.setText("Estado:");
        lbRazon3.setName(""); // NOI18N

        btnBuscTerceros1.setBackground(new java.awt.Color(204, 204, 204));
        btnBuscTerceros1.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnBuscTerceros1.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscTerceros1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar1.png"))); // NOI18N
        btnBuscTerceros1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnBuscTerceros1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscTerceros1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnBuscTerceros1.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnBuscTerceros1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscTerceros1ActionPerformed(evt);
            }
        });

        btnGuardar1.setBackground(new java.awt.Color(204, 204, 204));
        btnGuardar1.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnGuardar1.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar1.png"))); // NOI18N
        btnGuardar1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnGuardar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnGuardar1.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnGuardar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(138, 138, 138)
                        .addComponent(btnGuardar2, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(lbNit3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEmpleado1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(btnBuscTerceros1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(lbRazon1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtRazon1, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                        .addComponent(lbRazon3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbEstados, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGuardar1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmbEstados)
                    .addComponent(lbRazon1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtEmpleado1)
                    .addComponent(btnBuscTerceros1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbNit3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtRazon1)
                    .addComponent(lbRazon3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnGuardar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardar2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Liquidaciones", jPanel5);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        lbFacturaNo.setEditable(false);
        lbFacturaNo.setBackground(new java.awt.Color(204, 204, 204));
        lbFacturaNo.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbFacturaNo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lbFacturaNo.setText("LAVADO No.");
        lbFacturaNo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lbFacturaNoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                lbFacturaNoKeyTyped(evt);
            }
        });

        lbNoFactura.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbNoFactura.setForeground(new java.awt.Color(255, 0, 51));
        lbNoFactura.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lbNoFactura.setText("1");
        lbNoFactura.setName("Codigo"); // NOI18N
        lbNoFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lbNoFacturaActionPerformed(evt);
            }
        });
        lbNoFactura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lbNoFacturaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                lbNoFacturaKeyTyped(evt);
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

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbFacturaNo, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                            .addComponent(lbNoFactura))
                        .addGap(0, 0, 0)
                        .addComponent(btnBuscTerceros, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addComponent(btnLimpiar)
                        .addContainerGap())))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnBuscTerceros, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(lbFacturaNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(lbNoFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnLimpiar))
        );

        txtoServicio.setFont(new java.awt.Font("Century Gothic", 2, 18)); // NOI18N
        txtoServicio.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtoServicio.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtoServicio.setEnabled(false);

        txtIdSistema.setFont(new java.awt.Font("Century Gothic", 2, 18)); // NOI18N
        txtIdSistema.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtIdSistema.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtIdSistema.setEnabled(false);

        txtIdSistema1.setFont(new java.awt.Font("Century Gothic", 2, 18)); // NOI18N
        txtIdSistema1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtIdSistema1.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtIdSistema1.setEnabled(false);
        txtIdSistema1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdSistema1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlFormularioLayout = new javax.swing.GroupLayout(pnlFormulario);
        pnlFormulario.setLayout(pnlFormularioLayout);
        pnlFormularioLayout.setHorizontalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFormularioLayout.createSequentialGroup()
                        .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTabbedPane1)
                            .addGroup(pnlFormularioLayout.createSequentialGroup()
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(10, 10, 10))
                    .addGroup(pnlFormularioLayout.createSequentialGroup()
                        .addComponent(txtoServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIdSistema, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIdSistema1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        pnlFormularioLayout.setVerticalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtoServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdSistema, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdSistema1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
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

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtServicioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtServicioKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cargarProducto(txtServicio.getText());
            cargarTotales();
        }
    }//GEN-LAST:event_txtServicioKeyReleased

    private void txtServicioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtServicioKeyTyped

    }//GEN-LAST:event_txtServicioKeyTyped

    private void txtL1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtL1KeyReleased

    }//GEN-LAST:event_txtL1KeyReleased

    private void txtL1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtL1KeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtL1KeyTyped

    private void txtServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtServicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtServicioActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (btnGuardar.getText().equals("GUARDAR")) {
            JTextField[] campos = {txtNombre, txtPlaca};
            String faltantes = metodos.camposVacios(campos);

            if (!faltantes.equals("")) {
                metodos.msgAdvertencia(this, "No puede continuar, faltan los siguientes campos: " + faltantes);
                return;
            }

            for (int i = 0; i < tblServicios.getRowCount(); i++) {
                String empleado = "";
                try {
                    empleado = tblServicios.getValueAt(i, 3).toString();
                } catch (Exception e) {
                    empleado = "";
                }

                if (empleado.equals("")) {
                    metodos.msgAdvertencia(this, "No se puede guardar sin haberle asignado un empleado a cada uno de los servicios.");
                    return;
                }
            }

            Object[][] lavados = instancias.getSql().getLavadoPendiente(txtIdSistema.getText());
            if (lavados.length > 0) {
                metodos.msgAdvertencia(this, "Este cliente tiene un servicio sin terminar");
                if (metodos.msgPregunta(this, "¿Desea continuar?") == 1) {
                    return;
                }
            }

            if (tblServicios.getRowCount() <= 0) {
                metodos.msgError(this, "Debe ingresar almenos un producto o servicio");
                return;
            }

            Object[][] productos = new Object[tblServicios.getRowCount()][4];
            for (int i = 0; i < tblServicios.getRowCount(); i++) {
                productos[i][0] = tblServicios.getValueAt(i, 0);
                productos[i][1] = "1";
                productos[i][2] = 1;
                productos[i][3] = tblServicios.getValueAt(i, 2);
            }

            String conse = "LAV-" + instancias.getSql().getNumConsecutivo("LAV")[0];
            String conseOrden = instancias.getOrdenServicio().desdeLavadero(productos, txtId.getText(), txtPlaca.getText(), txtObservaciones.getText());

            System.out.println("tres dos uno cero: " + conseOrden);

            Object[] vector = {conse, txtObservaciones.getText(), txtL1.getText(), instancias.getUsuario(),
                metodos.fechaConsulta(metodosGenerales.fechaHora()), metodos.desdeDate(dtFechaRealizado.getCurrent()),
                txtPlaca.getText(), txtIdSistema.getText(), txtNombre.getText(), "PENDIENTE", txtTipoVehiculo.getText(), txtMarca.getText(), conseOrden};

            ndLavado nodo = metodos.llenarLavado(vector);

            if (!instancias.getSql().agregarLavado(nodo)) {
                metodos.msgError(this, "Error al guardar el lavado.");
                return;
            }

            for (int i = 0; i < tblServicios.getRowCount(); i++) {
                String id = "";
                try {
                    id = instancias.getSql().getIdEmpleado(tblServicios.getValueAt(i, 3).toString());
                } catch (Exception e) {
                    id = "";
                }

                Object[] info = new Object[]{conse, tblServicios.getValueAt(i, 0), tblServicios.getValueAt(i, 1),
                    big.getMoneda(tblServicios.getValueAt(i, 2).toString()), "PENDIENTE", id};

                if (!instancias.getSql().agregarProductosLavado(info)) {
                    metodos.msgError(this, "Error al guardar el detalle");
                    return;
                }
            }

            if (!instancias.getSql().aumentarConsecutivo("LAV", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("LAV")[0]) + 1)) {
                metodos.msgError(this, "Hubo un problema al guardar en el consecutivo del traslado");
            }

            metodos.msgExito(this, "Lavado registrado con éxito");
            btnLimpiarActionPerformed(null);

        } else if (btnGuardar.getText().equals("MODIFICAR")) {

            JTextField[] campos = {txtNombre};
            String faltantes = metodos.camposVacios(campos);

            if (tblServicios.getRowCount() <= 0) {
                metodos.msgError(this, "Debe ingresar almenos un producto o servicio");
                return;
            }

            if (!faltantes.equals("")) {
                metodos.msgAdvertencia(this, "No puede continuar, faltan los siguientes campos: " + faltantes);
                return;
            }

            instancias.getSql().eliminar_registro("bdLavadoProductos", "IdLavado = '" + lbNoFactura.getText() + "' AND liquidado = 'PENDIENTE' ");
            instancias.getSql().eliminar_registro("bdLavado", "id = '" + lbNoFactura.getText() + "'");

            String conse = txtoServicio.getText();
            Object[] vector = {lbNoFactura.getText(), txtObservaciones.getText(), txtL1.getText(), instancias.getUsuario(), metodos.fechaConsulta(metodosGenerales.fechaHora()),
                metodos.desdeDate(dtFechaRealizado.getCurrent()), txtPlaca.getText(), txtId.getText(), txtNombre.getText(), "PENDIENTE", txtTipoVehiculo.getText(),
                txtMarca.getText(), conse};

            ndLavado nodo = metodos.llenarLavado(vector);

            if (!instancias.getSql().agregarLavado(nodo)) {
                metodos.msgError(this, "Hubo un problema al modificar el registro");
                return;
            }

            String id;

            Object[][] serviciosLavados = instancias.getSql().getServiciosLavado(lbNoFactura.getText());

            for (int i = 0; i < tblServicios.getRowCount(); i++) {

                Boolean entro = false;
                for (int ser = 0; ser < serviciosLavados.length; ser++) {
                    if (serviciosLavados[ser][1].equals(tblServicios.getValueAt(i, 0))) {
                        entro = true;
                    }
                }

                if (!entro) {
                    try {
                        id = instancias.getSql().getIdEmpleado(tblServicios.getValueAt(i, 3).toString());
                    } catch (Exception e) {
                        id = "";
                    }

                    Object[] info = new Object[]{lbNoFactura.getText(), tblServicios.getValueAt(i, 0), tblServicios.getValueAt(i, 1),
                        big.getMoneda(tblServicios.getValueAt(i, 2).toString()), "PENDIENTE", id};

                    if (!instancias.getSql().agregarProductosLavado(info)) {
                        metodos.msgError(this, "Hubo un error al modificar el registro");
                        return;
                    }
                }
            }

            Object[][] productos = new Object[tblServicios.getRowCount()][4];
            for (int i = 0; i < tblServicios.getRowCount(); i++) {
                productos[i][0] = tblServicios.getValueAt(i, 0);
                productos[i][1] = "1";
                productos[i][2] = 1;
                productos[i][3] = tblServicios.getValueAt(i, 2);
            }

            System.out.println("que dice este men: " + txtoServicio.getText());

            instancias.getOrdenServicio().actualizarOrdenServicio(productos, txtoServicio.getText());

//            metodos.msgExito(this, "Lavado modificado con éxito");
            btnLimpiarActionPerformed(null);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void txtNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreKeyReleased

    private void txtIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdActionPerformed

    private void txtIdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            ModeloContacto nodo = instancias.getSql().getDatosTercero(txtId.getText());

            if (nodo.getId() != null) {
                desdeId = true;
                cargarTercero(txtId.getText());
            } else {
                ventanaTerceros("");
            }
        } else {
            txtIdSistema.setText("");
            txtNombre.setText("");
            txtPlaca.setText("");
            txtMarca.setText("");
            txtTipoVehiculo.setText("");
        }
    }//GEN-LAST:event_txtIdKeyReleased

    private void txtIdKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdKeyTyped

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        txtoServicio.setText("");
        btnReImprimir.setVisible(false);
        btnAnular.setVisible(false);
        jRadioButton1.setSelected(true);
        txtId.setEnabled(true);
        txtPlaca.setEnabled(true);
        btnFacturar.setEnabled(false);
        btnGuardar2.setEnabled(false);
        lbNoFactura.setEnabled(true);
        btnBusTercero.setEnabled(true);
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png")));
        btnGuardar.setText("GUARDAR");

        DefaultTableModel modelo = (DefaultTableModel) tblServicios.getModel();
        DefaultTableModel modelo1 = (DefaultTableModel) tblLiquidaciones.getModel();

        while (tblServicios.getRowCount() > 0) {
            modelo.removeRow(0);
        }

        while (tblLiquidaciones.getRowCount() > 0) {
            modelo1.removeRow(0);
        }

        lbNoFactura.setText(instancias.getSql().getNumConsecutivo("LAV")[0].toString());

        txtL1.setText(this.simbolo + " 0");
        txtEmpleado1.setText("");
        txtDiasPlazo.setText("");
        txtObservaciones.setText("");
        txtTipoVehiculo.setText("");
        txtMarca.setText("");
        txtRazon1.setText("");
        jLabel1.setText("Total a pagar: " + this.simbolo + " 0");
        txtId.setText("");
        txtNombre.setText("");
        txtPlaca.setText("");
        txtServicio.setText("");
        cargarTabla();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void popBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popBorrarActionPerformed
        if (tblServicios.getSelectedRow() > -1) {
            int fila = tblServicios.getSelectedRow();

            DefaultTableModel modelo = (DefaultTableModel) tblServicios.getModel();
            modelo.removeRow(fila);

//            tblProductos.removeEditor();
            cargarTotales();
        } else {
            metodos.msgAdvertencia(null, "Seleccione un producto");
        }
    }//GEN-LAST:event_popBorrarActionPerformed

    private void tblServiciosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblServiciosKeyReleased
        for (int i = 0; i < tblServicios.getRowCount(); i++) {
            try {
                tblServicios.setValueAt(big.setMoneda(big.getMoneda(tblServicios.getValueAt(i, 2).toString())), i, 2);
            } catch (Exception e) {
                tblServicios.setValueAt(big.setMoneda(big.getMoneda("0")), i, 2);
            }
        }
        cargarTotales();
    }//GEN-LAST:event_tblServiciosKeyReleased

    private void txtEmpleado1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmpleado1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmpleado1ActionPerformed

    private void txtEmpleado1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmpleado1KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cargarEmpleado1(txtEmpleado1.getText());
        } else {
            txtRazon1.setText("");
            cargarTabla();
            btnGuardar2.setEnabled(false);
        }
    }//GEN-LAST:event_txtEmpleado1KeyReleased

    private void txtEmpleado1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmpleado1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmpleado1KeyTyped

    private void txtRazon1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRazon1KeyReleased

    }//GEN-LAST:event_txtRazon1KeyReleased

    private void btnGuardar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar2ActionPerformed
        Object[][] datos = instancias.getSql().getDatosMaestraParqueadero();

        String egreso, tipo = "";

        System.out.println("df" + datos[0][2].toString());
        if (datos[0][2].toString().equals("0")) {
            if (metodos.msgPregunta(this, "¿Desea generar egreso?") == 0) {
                egreso = "SI";
                tipo = new dlgTipoEgreso(null, true).seleccionar();
            } else {
                egreso = "NO";
            }
        } else {
            egreso = "SI";
            tipo = new dlgTipoEgreso(null, true).seleccionar();
        }

//        if (metodos.msgPregunta(this, "¿Desea generar egreso?") == 0) {
//            tipo = new dlgTipoEgreso(null, true).seleccionar();
//        }
        for (int i = 0; i < tblLiquidaciones.getRowCount(); i++) {
            if ((Boolean) tblLiquidaciones.getValueAt(i, 11)) {

                String tipoVehiculo, marca;

                try {
                    tipoVehiculo = tblLiquidaciones.getValueAt(i, 5).toString();
                } catch (Exception e) {
                    tipoVehiculo = "";
                }

                try {
                    marca = tblLiquidaciones.getValueAt(i, 6).toString();
                } catch (Exception e) {
                    marca = "";
                }

                instancias.getSql().agregarPagos(instancias.getSql().getNumConsecutivo("PAGOS")[0].toString(), tblLiquidaciones.getValueAt(i, 0).toString(), tblLiquidaciones.getValueAt(i, 10).toString(),
                        tblLiquidaciones.getValueAt(i, 4).toString(), tipoVehiculo, marca, tblLiquidaciones.getValueAt(i, 12).toString(),
                        big.getMoneda(tblLiquidaciones.getValueAt(i, 7).toString()));

                System.out.println("mallll: " + tblLiquidaciones.getValueAt(i, 10).toString());

                instancias.getSql().cambiarEstadoLavado(tblLiquidaciones.getValueAt(i, 0).toString(), "PAGADO", tblLiquidaciones.getValueAt(i, 10).toString(), tblLiquidaciones.getValueAt(i, 9).toString().substring(2, tblLiquidaciones.getValueAt(i, 9).toString().length()).replace(".", ""), egreso);
            }
        }

        BigDecimal total = BigDecimal.ZERO;
        for (int i = 0; i < tblLiquidaciones.getRowCount(); i++) {
            if ((Boolean) tblLiquidaciones.getValueAt(i, 11)) {
                total = total.add(big.getMoneda(tblLiquidaciones.getValueAt(i, 9).toString()));
            }
        }

        if (egreso.equals("SI")) {
            instancias.getEgresos().setSaltarPasos(true);
            instancias.getEgresos().desdeLavadero("1010", total.toString(), "",
                    "LIQUIDACIÓN PERSONAL DE LAVADO", "LIQUIDACIÓN A (" + txtEmpleado1.getText() + ") " + txtRazon1.getText() + " POR PRESTAR LOS SERVICIOS DE LAVADO", "", tipo);
        }

        metodos.msgExito(this, "Pago realizado exitosamente");

        DefaultTableModel modelo1 = (DefaultTableModel) tblLiquidaciones.getModel();

        while (tblLiquidaciones.getRowCount() > 0) {
            modelo1.removeRow(0);
        }

        txtEmpleado1.setText("");
        txtRazon1.setText("");
        jLabel1.setText("Total a pagar: " + this.simbolo + " 0");

//        instancias.getReporte().verPagos(instancias.getSql().getNumConsecutivo("PAGOS")[0].toString());
        if (!instancias.getSql().aumentarConsecutivo("PAGOS", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("PAGOS")[0]) + 1)) {
            metodos.msgError(this, "Hubo un problema al guardar en el consecutivo del traslado");
        }

        cargarTabla();
        btnLimpiarActionPerformed(evt);
    }//GEN-LAST:event_btnGuardar2ActionPerformed

    private void popBorrar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popBorrar1ActionPerformed
        if (tblLiquidaciones.getSelectedRow() > -1) {
            int fila = tblLiquidaciones.getSelectedRow();

            DefaultTableModel modelo = (DefaultTableModel) tblLiquidaciones.getModel();
            modelo.removeRow(fila);

            BigDecimal total = BigDecimal.ZERO;
            for (int i = 0; i < tblLiquidaciones.getRowCount(); i++) {
                total = total.add(big.getMoneda(tblLiquidaciones.getValueAt(i, 9).toString()));
            }
            jLabel1.setText("Total a pagar: " + big.setMoneda(total));
        } else {
            metodos.msgAdvertencia(null, "Seleccione un producto");
        }
    }//GEN-LAST:event_popBorrar1ActionPerformed

    private void txtTipoVehiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTipoVehiculoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTipoVehiculoActionPerformed

    private void txtTipoVehiculoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTipoVehiculoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTipoVehiculoKeyReleased

    private void txtTipoVehiculoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTipoVehiculoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTipoVehiculoKeyTyped

    private void txtMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMarcaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMarcaActionPerformed

    private void txtMarcaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMarcaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMarcaKeyReleased

    private void txtMarcaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMarcaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMarcaKeyTyped

    private void btnFacturarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFacturarActionPerformed
        for (int i = 0; i < tblServicios.getRowCount(); i++) {
            if (tblServicios.getValueAt(i, 3).equals("")) {
                metodos.msgError(this, "No se puede facturar sin haberle asignado un empleado a cada uno de los servicios.");
                return;
            }
        }

        String diasPlazo = "";

        if (rbtCredito.isSelected()) {
            if (txtDiasPlazo.getText().equals("")) {
                metodos.msgError(this, "Ingrese los dias de plazo");
                return;
            }
        }

        diasPlazo = txtDiasPlazo.getText();

        if (metodos.msgPregunta(this, "¿Desea continuar?") == 0) {

            Object[][] productos = new Object[tblServicios.getRowCount()][4];

            for (int i = 0; i < tblServicios.getRowCount(); i++) {
                productos[i][0] = tblServicios.getValueAt(i, 0);
                productos[i][1] = "1";
                productos[i][2] = 1;
                productos[i][3] = tblServicios.getValueAt(i, 2);
            }

            instancias.getFactura().desdeLavadero(productos, txtId.getText(), diasPlazo, txtPlaca.getText(), txtObservaciones.getText());
            instancias.getSql().cambiarEstadoLavado1(lbNoFactura.getText(), "FACTURADO", factura);
            factura = "";
            btnLimpiarActionPerformed(evt);
        }
    }//GEN-LAST:event_btnFacturarActionPerformed

    private void tblServiciosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblServiciosKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tblServiciosKeyPressed

    private void cmbEmpleadosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbEmpleadosItemStateChanged

    }//GEN-LAST:event_cmbEmpleadosItemStateChanged

    private void cmbEmpleadosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbEmpleadosMouseExited

    }//GEN-LAST:event_cmbEmpleadosMouseExited

    private void lbFacturaNoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lbFacturaNoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_lbFacturaNoKeyReleased

    private void lbFacturaNoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lbFacturaNoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_lbFacturaNoKeyTyped

    private void lbNoFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lbNoFacturaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lbNoFacturaActionPerformed

    private void lbNoFacturaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lbNoFacturaKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cargarLavado("LAV-" + lbNoFactura.getText());
        }
    }//GEN-LAST:event_lbNoFacturaKeyReleased

    private void lbNoFacturaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lbNoFacturaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_lbNoFacturaKeyTyped

    private void cmbEstadosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbEstadosItemStateChanged
        if (txtEmpleado1.getText().equals("")) {
            cargarTabla();
        } else {
            cargarTablaEmpleado();
            if (cmbEstados.getSelectedIndex() == 0) {
                btnGuardar2.setEnabled(true);
            } else if (cmbEstados.getSelectedIndex() == 1) {
                btnGuardar2.setEnabled(false);
            }
        }
    }//GEN-LAST:event_cmbEstadosItemStateChanged

    private void txtPlacaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPlacaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPlacaActionPerformed

    private void txtPlacaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPlacaKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            String placa = txtPlaca.getText();

            Object[][] name = instancias.getSql().getUsuarioPlaca(placa);

            if (name.length > 0) {

                txtId.setText(name[0][0].toString());
                cargarTercero(name[0][0].toString());

                try {
                    txtTipoVehiculo.setText(name[0][2].toString());
                } catch (Exception e) {
                    txtTipoVehiculo.setText("");
                }

                try {
                    txtMarca.setText(name[0][3].toString());
                } catch (Exception e) {
                    txtMarca.setText("");
                }

                txtPlaca.setText(placa);

                btnBusTercero.requestFocus();
            } else {
                txtTipoVehiculo.setText("");
                txtMarca.setText("");

                String condi = txtIdSistema.getText();
                ventanaPlacas(txtPlaca.getText(), condi);
            }
        }
    }//GEN-LAST:event_txtPlacaKeyReleased

    private void txtPlacaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPlacaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPlacaKeyTyped

    private void txtDiasPlazoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiasPlazoKeyReleased
        rbtCredito.setSelected(true);
    }//GEN-LAST:event_txtDiasPlazoKeyReleased

    private void btnReImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReImprimirActionPerformed
        instancias.getReporte().ver_oServicio("OSERV-" + txtoServicio.getText(), txtObservaciones.getText(), false, "", "Normal");
    }//GEN-LAST:event_btnReImprimirActionPerformed

    private void cmbEstadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbEstadosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbEstadosActionPerformed

    private void btnBusTerceroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBusTerceroActionPerformed
        ventanaTerceros("");
    }//GEN-LAST:event_btnBusTerceroActionPerformed

    private void btnBusProd2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBusProd2ActionPerformed
        String condi = txtIdSistema.getText();
        ventanaPlacas(txtPlaca.getText(), condi);
    }//GEN-LAST:event_btnBusProd2ActionPerformed

    private void btnBuscTercerosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscTercerosActionPerformed
        dlgLavadosPendientes pendientes = new dlgLavadosPendientes(null, true, "");
        pendientes.setLocationRelativeTo(null);
        pendientes.setVisible(true);
    }//GEN-LAST:event_btnBuscTercerosActionPerformed

    private void btnBusProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBusProdActionPerformed
        ventanaProductos("");
    }//GEN-LAST:event_btnBusProdActionPerformed

    private void btnBuscTerceros1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscTerceros1ActionPerformed
        ventanaEmpleados1("");
    }//GEN-LAST:event_btnBuscTerceros1ActionPerformed

    private void btnGuardar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar1ActionPerformed
        if (txtEmpleado1.getText().equals("")) {
            metodos.msgError(this, "Ingrese el empleado a consultar");
            return;
        }

        cargarTablaEmpleado();
    }//GEN-LAST:event_btnGuardar1ActionPerformed

    private void txtIdSistema1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdSistema1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdSistema1ActionPerformed

    private void btnAnularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnularActionPerformed
        if (!instancias.getUsuario().equals("ADMIN")) {
            vistaSolicitarPermisos permisos = new vistaSolicitarPermisos(null, true,
                    "ANULACIÓN LAVADERO", "ANULACION", lbNoFactura.getText(), "anulacion");
            permisos.setLocationRelativeTo(null);
            permisos.setVisible(true);
            return;
        } else {
            if (metodos.msgPregunta(this, "¿Desea anular el lavado?") != 0) {
                return;
            } else {
                String nota = "";
                nota = metodos.msgIngresar(null, "NOTA: ");

                if (nota == null) {
                    return;
                }

                if (nota.equals("")) {
                    return;
                }

                instancias.getSql().cambiarEstadoLavado1(lbNoFactura.getText(), "ANULADO", nota);
                //instancias.getOrdenServicio().cargarOrdenesServicios(txtoServicio.getText());
                instancias.getOrdenServicio().anularOrdenServicio(nota, txtoServicio.getText(), "bdProductos");
                btnLimpiarActionPerformed(evt);

                metodos.msgExito(this, "Lavado anulado con exito");
            }
        }
    }//GEN-LAST:event_btnAnularActionPerformed

    private void rbtCreditoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rbtCreditoItemStateChanged
        if (rbtCredito.isSelected()) {
            txtDiasPlazo.setEnabled(true);
        } else {
            txtDiasPlazo.setEnabled(false);
        }
    }//GEN-LAST:event_rbtCreditoItemStateChanged

    private void jRadioButton1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButton1ItemStateChanged
        if (rbtCredito.isSelected()) {
            txtDiasPlazo.setEnabled(true);
        } else {
            txtDiasPlazo.setEnabled(false);
        }
    }//GEN-LAST:event_jRadioButton1ItemStateChanged

    public void ventanaPlacas(String nit, String condi) {
        buscPlacas buscar = new buscPlacas(instancias.getMenu(), rootPaneCheckingEnabled, condi);
        buscar.setLocationRelativeTo(null);
        buscar.setInstancia(instancias);
        instancias.setBuscPlacas(buscar);
        instancias.setCampoActual(txtPlaca);
        txtPlaca.requestFocus();
        buscar.noEncontrado(nit);
        buscar.show();
    }

    public void cargarLavado(String idLavado) {

        Object[][] lavadero = instancias.getSql().getDatosLavado(idLavado);

        try {
            if (!lavadero[0][6].toString().equals("PENDIENTE")) {
                metodos.msgError(this, "El lavado ya se facturó");
                btnLimpiarActionPerformed(null);
                return;
            }
        } catch (Exception e) {
            metodos.msgError(this, "El lavado no existe");
            btnLimpiarActionPerformed(null);
            return;
        }

        if (lavadero[0][0] != null) {
            btnReImprimir.setVisible(true);
            btnAnular.setVisible(true);
            lbNoFactura.setText(idLavado);
            txtId.setText(lavadero[0][5].toString());
            cargarTercero(lavadero[0][5].toString());
            txtPlaca.setText(lavadero[0][13].toString());
            txtL1.setText(lavadero[0][2].toString());

            if (lavadero[0][1] != null) {
                txtObservaciones.setText(lavadero[0][1].toString());
            }

            if (lavadero[0][1] != null) {
                txtMarca.setText(lavadero[0][16].toString());
            }

            if (lavadero[0][1] != null) {
                txtTipoVehiculo.setText(lavadero[0][15].toString());
            }

            DefaultTableModel modelo = (DefaultTableModel) tblServicios.getModel();

            while (tblServicios.getRowCount() > 0) {
                modelo.removeRow(0);
            }

            for (int i = 0; i < lavadero.length; i++) {

                String nombre;

                try {
                    nombre = instancias.getSql().getNombreEmpleado(lavadero[i][12].toString());
                } catch (Exception e) {
                    nombre = "";
                }

                modelo.addRow(new Object[]{lavadero[i][7], lavadero[i][8], big.setMoneda(big.getBigDecimal(lavadero[i][9])), nombre});
            }

            dtFechaRealizado.setSelectedDate(metodos.haciaDate(lavadero[0][4].toString()));
            txtFecha.setText(metodos.fecha(lavadero[0][3].toString()));

            txtoServicio.setText(lavadero[0][14].toString());
            System.out.println("envio " + lavadero[0][14].toString());

            btnBusTercero.setEnabled(false);
            btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/actualizar.png")));
            btnGuardar.setText("MODIFICAR");
            lbNoFactura.setEnabled(false);
            btnFacturar.setEnabled(true);
            txtPlaca.setEnabled(false);
            txtId.setEnabled(false);
        } else {
            metodos.msgError(this, "El lavado no existe");
        }
    }

    public void desdeLavadero(String traslado) {
//        traslado = traslado.substring(6, traslado.length());
        lbNoFactura.setText(traslado);
        cargarLavado(traslado);
    }

    public void cargarTotales() {
        BigDecimal total = BigDecimal.ZERO;

        for (int i = 0; i < tblServicios.getRowCount(); i++) {
            total = total.add(big.getMoneda(tblServicios.getValueAt(i, 2).toString()));
        }
        txtL1.setText(big.setMoneda(total));
    }

    public void setInstancias(Instancias instancias) {
        this.instancias = instancias;
        txtFecha.setText(metodosGenerales.fecha());
    }

    public void cargarProductos(TreeMap<String, String> productos) {
        for (Map.Entry<String, String> entry : productos.entrySet()) {
            cargarProducto(entry.getKey());
        }
        cargarTotales();
    }

    public void cargarProducto(String codigo) {

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
            DefaultTableModel modelo = (DefaultTableModel) tblServicios.getModel();
            modelo.addRow(new Object[]{nodo.getIdSistema(), nodo.getDescripcion(), big.setMoneda(big.getBigDecimal(nodo.getL1()))});
            txtServicio.setText("");
            btnGuardar.requestFocus();
            return;
        }

        ventanaProductos(codigo);
    }

    public void cargarEmpleado1(String nit) {
        ModeloContacto nodo = instancias.getSql().getDatosTercero(nit);

        if (nodo.getId() != null) {
            txtRazon1.setText(nodo.getNombre());
            txtIdSistema1.setText(nodo.getIdSistema());
            btnGuardar1.requestFocus();
            return;
        }
        ventanaEmpleados1(nit);
    }

    public void ventanaEmpleados1(String nit) {
        buscEmpleados buscar = new buscEmpleados(instancias.getMenu(), rootPaneCheckingEnabled);
        buscar.setLocationRelativeTo(null);
        instancias.setBuscEmpleados(buscar);
        instancias.setCampoActual(txtEmpleado1);
        txtEmpleado1.requestFocus();
//        buscar.setInstancia(instancias);
        buscar.noEncontrado(nit);
        buscar.show();
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

    public void ventanaProductos(String codigo) {
        buscProductos buscar = new buscProductos(null, true, false, "lavado", "productos1");
        buscar.setLocationRelativeTo(null);
        instancias.setBusProductos(buscar);
        instancias.setCampoActual(txtServicio);
        txtServicio.requestFocus();
        buscar.noEncontrado(codigo.replace("'", "//"));
        buscar.show();
    }

    public void ventanaProductos1(String codigo) {
        buscProductos buscar = new buscProductos(null, rootPaneCheckingEnabled, false, "", "productos1");
        buscar.setLocationRelativeTo(null);
        instancias.setBusProductos(buscar);
        instancias.setCampoActual(txtServicio);
        txtServicio.requestFocus();
//        buscar.setInstancia(instancias);
        buscar.noEncontrado(codigo.replace("'", "//"));
        buscar.show();
    }

    public void cargarTabla() {

        tblLiquidaciones.setModel(instancias.getSql().getTodasLiquidaciones(" where liquidado = '" + cmbEstados.getSelectedItem() + "' AND estadoLavado = 'FACTURADO' "));

        if (tblLiquidaciones.getColumnModel().getColumnCount() > 0) {
            tblLiquidaciones.getColumnModel().getColumn(0).setMinWidth(35);
            tblLiquidaciones.getColumnModel().getColumn(0).setPreferredWidth(35);
            tblLiquidaciones.getColumnModel().getColumn(0).setMaxWidth(35);

            tblLiquidaciones.getColumnModel().getColumn(1).setMinWidth(75);
            tblLiquidaciones.getColumnModel().getColumn(1).setPreferredWidth(75);
            tblLiquidaciones.getColumnModel().getColumn(1).setMaxWidth(75);

            tblLiquidaciones.getColumnModel().getColumn(4).setMinWidth(60);
            tblLiquidaciones.getColumnModel().getColumn(4).setPreferredWidth(60);
            tblLiquidaciones.getColumnModel().getColumn(4).setMaxWidth(60);
            tblLiquidaciones.getColumnModel().getColumn(5).setMinWidth(85);
            tblLiquidaciones.getColumnModel().getColumn(5).setPreferredWidth(85);
            tblLiquidaciones.getColumnModel().getColumn(5).setMaxWidth(85);
            tblLiquidaciones.getColumnModel().getColumn(6).setMinWidth(100);
            tblLiquidaciones.getColumnModel().getColumn(6).setPreferredWidth(100);
            tblLiquidaciones.getColumnModel().getColumn(6).setMaxWidth(100);

            tblLiquidaciones.getColumnModel().getColumn(7).setMinWidth(70);
            tblLiquidaciones.getColumnModel().getColumn(7).setPreferredWidth(70);
            tblLiquidaciones.getColumnModel().getColumn(7).setMaxWidth(70);

            tblLiquidaciones.getColumnModel().getColumn(8).setMinWidth(70);
            tblLiquidaciones.getColumnModel().getColumn(8).setPreferredWidth(70);
            tblLiquidaciones.getColumnModel().getColumn(8).setMaxWidth(70);

            tblLiquidaciones.getColumnModel().getColumn(9).setMinWidth(70);
            tblLiquidaciones.getColumnModel().getColumn(9).setPreferredWidth(70);
            tblLiquidaciones.getColumnModel().getColumn(9).setMaxWidth(70);

            tblLiquidaciones.getColumnModel().getColumn(10).setMinWidth(0);
            tblLiquidaciones.getColumnModel().getColumn(10).setPreferredWidth(0);
            tblLiquidaciones.getColumnModel().getColumn(10).setMaxWidth(0);
            tblLiquidaciones.getColumnModel().getColumn(11).setMinWidth(30);
            tblLiquidaciones.getColumnModel().getColumn(11).setPreferredWidth(30);
            tblLiquidaciones.getColumnModel().getColumn(11).setMaxWidth(30);
            tblLiquidaciones.getColumnModel().getColumn(13).setMinWidth(85);
            tblLiquidaciones.getColumnModel().getColumn(13).setPreferredWidth(85);
            tblLiquidaciones.getColumnModel().getColumn(13).setMaxWidth(85);
        }

        BigDecimal total = BigDecimal.ZERO;
        BigDecimal utilidad, apagar = BigDecimal.ZERO;

        for (int i = 0; i < tblLiquidaciones.getRowCount(); i++) {

            ndProducto nodo = instancias.getSql().getDatosProducto(tblLiquidaciones.getValueAt(i, 10).toString(), "bdProductos");

            BigDecimal porcentaje;

            try {
                if (nodo.getPorcentaje() == null || nodo.getPorcentaje().equals("null")) {
                    porcentaje = big.getBigDecimal("0");
                } else {
                    porcentaje = big.getBigDecimal(nodo.getPorcentaje());
                }

            } catch (Exception e) {
                porcentaje = big.getBigDecimal("0");
            }
//            try {

            apagar = porcentaje.multiply(big.getMoneda(tblLiquidaciones.getValueAt(i, 7).toString()).divide(big.getBigDecimal(100)));
            utilidad = big.getMoneda(tblLiquidaciones.getValueAt(i, 7).toString()).subtract(apagar);
//            } catch (Exception e) {
//                apagar = big.getBigDecimal("0");
//                utilidad = big.getBigDecimal("0");
//            }

            tblLiquidaciones.setValueAt(tblLiquidaciones.getValueAt(i, 0).toString().replace("LAV-", ""), i, 0);
            tblLiquidaciones.setValueAt(metodos.fecha(tblLiquidaciones.getValueAt(i, 1).toString()), i, 1);
            tblLiquidaciones.setValueAt(big.setMoneda(big.getBigDecimal(tblLiquidaciones.getValueAt(i, 7).toString())), i, 7);
            tblLiquidaciones.setValueAt(big.setMoneda(utilidad), i, 8);
            tblLiquidaciones.setValueAt(big.setMoneda(apagar), i, 9);
            total = total.add(big.getMoneda(tblLiquidaciones.getValueAt(i, 9).toString()));
        }

        if (cmbEstados.getSelectedIndex() == 0) {
            jLabel1.setText("Total a pagar: " + big.setMoneda(total));
        } else if (cmbEstados.getSelectedIndex() == 1) {
            jLabel1.setText("Total pagado: " + big.setMoneda(total));
        }
    }

    public void cargarTablaEmpleado() {

        tblLiquidaciones.setModel(instancias.getSql().getLiquidacionPendiente(" where empleado = '" + txtIdSistema1.getText() + "' "
                + "and liquidado = '" + cmbEstados.getSelectedItem() + "' AND estadoLavado = 'FACTURADO' "));

        if (tblLiquidaciones.getColumnModel().getColumnCount() > 0) {
            tblLiquidaciones.getColumnModel().getColumn(0).setMinWidth(35);
            tblLiquidaciones.getColumnModel().getColumn(0).setPreferredWidth(35);
            tblLiquidaciones.getColumnModel().getColumn(0).setMaxWidth(25);
            tblLiquidaciones.getColumnModel().getColumn(1).setMinWidth(90);
            tblLiquidaciones.getColumnModel().getColumn(1).setPreferredWidth(90);
            tblLiquidaciones.getColumnModel().getColumn(1).setMaxWidth(90);
            tblLiquidaciones.getColumnModel().getColumn(4).setMinWidth(60);
            tblLiquidaciones.getColumnModel().getColumn(4).setPreferredWidth(60);
            tblLiquidaciones.getColumnModel().getColumn(4).setMaxWidth(60);
            tblLiquidaciones.getColumnModel().getColumn(5).setMinWidth(85);
            tblLiquidaciones.getColumnModel().getColumn(5).setPreferredWidth(85);
            tblLiquidaciones.getColumnModel().getColumn(5).setMaxWidth(85);
            tblLiquidaciones.getColumnModel().getColumn(6).setMinWidth(100);
            tblLiquidaciones.getColumnModel().getColumn(6).setPreferredWidth(100);
            tblLiquidaciones.getColumnModel().getColumn(6).setMaxWidth(100);
            tblLiquidaciones.getColumnModel().getColumn(7).setMinWidth(90);
            tblLiquidaciones.getColumnModel().getColumn(7).setPreferredWidth(90);
            tblLiquidaciones.getColumnModel().getColumn(7).setMaxWidth(90);
            tblLiquidaciones.getColumnModel().getColumn(8).setMinWidth(90);
            tblLiquidaciones.getColumnModel().getColumn(8).setPreferredWidth(90);
            tblLiquidaciones.getColumnModel().getColumn(8).setMaxWidth(90);
            tblLiquidaciones.getColumnModel().getColumn(9).setMinWidth(100);
            tblLiquidaciones.getColumnModel().getColumn(9).setPreferredWidth(100);
            tblLiquidaciones.getColumnModel().getColumn(9).setMaxWidth(100);
            tblLiquidaciones.getColumnModel().getColumn(10).setMinWidth(0);
            tblLiquidaciones.getColumnModel().getColumn(10).setPreferredWidth(0);
            tblLiquidaciones.getColumnModel().getColumn(10).setMaxWidth(0);
            tblLiquidaciones.getColumnModel().getColumn(11).setMinWidth(30);
            tblLiquidaciones.getColumnModel().getColumn(11).setPreferredWidth(30);
            tblLiquidaciones.getColumnModel().getColumn(11).setMaxWidth(30);
        }

//        Object[][] pendientes = sql.getTodasLiquidaciones(estado);
//        DefaultTableModel modelo = (DefaultTableModel) tblLiquidaciones.getModel();
//
//        while (tblLiquidaciones.getRowCount() > 0) {
//            modelo.removeRow(0);
//        }
//
//        for (int i = 0; i < pendientes.length; i++) {
//            modelo.addRow(new Object[]{pendientes[i][0], pendientes[i][1].toString().substring(0, 10), pendientes[i][2], pendientes[i][3],
//                pendientes[i][4], pendientes[i][5], pendientes[i][6], big.setMoneda(big.getBigDecimal(pendientes[i][7])), "", "", pendientes[i][8]});
//        }
//
        BigDecimal total = BigDecimal.ZERO;
        BigDecimal utilidad, apagar = BigDecimal.ZERO;

        for (int i = 0; i < tblLiquidaciones.getRowCount(); i++) {
            tblLiquidaciones.setValueAt(metodos.fecha(tblLiquidaciones.getValueAt(i, 1).toString()), i, 1);

            ndProducto nodo = instancias.getSql().getDatosProducto(tblLiquidaciones.getValueAt(i, 10).toString(), "bdProductos");

            BigDecimal porcentaje;
            if (nodo.getPorcentaje() != null) {
                porcentaje = big.getBigDecimal(nodo.getPorcentaje());
            } else {
                porcentaje = BigDecimal.ZERO;
            }

            BigDecimal valor = big.getBigDecimal(tblLiquidaciones.getValueAt(i, 7).toString());
            apagar = porcentaje.multiply(valor.divide(big.getBigDecimal(100)));
            utilidad = big.getMoneda(tblLiquidaciones.getValueAt(i, 7).toString()).subtract(apagar);

            tblLiquidaciones.setValueAt(tblLiquidaciones.getValueAt(i, 0).toString().replace("LAV-", ""), i, 0);
            tblLiquidaciones.setValueAt(big.setMoneda(big.getBigDecimal(tblLiquidaciones.getValueAt(i, 7).toString())), i, 7);
            tblLiquidaciones.setValueAt(big.setMoneda(utilidad), i, 8);
            tblLiquidaciones.setValueAt(big.setMoneda(apagar), i, 9);
            total = total.add(big.getMoneda(tblLiquidaciones.getValueAt(i, 9).toString()));
        }

        if (cmbEstados.getSelectedIndex() == 0) {
            jLabel1.setText("Total a pagar: " + big.setMoneda(total));
        } else if (cmbEstados.getSelectedIndex() == 1) {
            jLabel1.setText("Total pagado: " + big.setMoneda(total));
        }
        btnGuardar2.setEnabled(true);
    }

    public void cargarTercero(String Id) {
        ModeloContacto nodo = instancias.getSql().getDatosTercero(Id);

        if (nodo.getId() != null) {
            txtId.setText(nodo.getId());
            txtIdSistema.setText(nodo.getIdSistema());
            txtNombre.setText(nodo.getNombre());
            txtPlaca.setText("");
            txtMarca.setText("");
            txtTipoVehiculo.setText("");
            return;
        }
        ventanaTerceros(Id);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnular;
    private javax.swing.JButton btnBusProd;
    private javax.swing.JButton btnBusProd2;
    private javax.swing.JButton btnBusTercero;
    private javax.swing.JButton btnBuscTerceros;
    private javax.swing.JButton btnBuscTerceros1;
    private javax.swing.JButton btnFacturar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnGuardar1;
    private javax.swing.JButton btnGuardar2;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnReImprimir;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cmbEmpleados;
    private javax.swing.JComboBox cmbEstados;
    private datechooser.beans.DateChooserCombo dtFechaRealizado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lbDepartamento;
    private javax.swing.JLabel lbDepartamento1;
    private javax.swing.JLabel lbDepartamento2;
    private javax.swing.JLabel lbDepartamento3;
    private javax.swing.JTextField lbFacturaNo;
    private javax.swing.JLabel lbNit1;
    private javax.swing.JLabel lbNit10;
    private javax.swing.JLabel lbNit11;
    private javax.swing.JLabel lbNit12;
    private javax.swing.JLabel lbNit15;
    private javax.swing.JLabel lbNit2;
    private javax.swing.JLabel lbNit3;
    private javax.swing.JLabel lbNit9;
    private javax.swing.JTextField lbNoFactura;
    private javax.swing.JLabel lbRazon1;
    private javax.swing.JLabel lbRazon2;
    private javax.swing.JLabel lbRazon3;
    private javax.swing.JPanel pnlFormulario;
    private javax.swing.JMenuItem popBorrar;
    private javax.swing.JMenuItem popBorrar1;
    private javax.swing.JRadioButton rbtCredito;
    private javax.swing.JScrollPane scrFormulario;
    private javax.swing.JTable tblLiquidaciones;
    private javax.swing.JTable tblServicios;
    private javax.swing.JTextField txtDiasPlazo;
    private javax.swing.JTextField txtEmpleado1;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtIdSistema;
    private javax.swing.JTextField txtIdSistema1;
    private javax.swing.JTextField txtL1;
    private javax.swing.JTextField txtMarca;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextArea txtObservaciones;
    private javax.swing.JTextField txtPlaca;
    private javax.swing.JTextField txtRazon1;
    private javax.swing.JTextField txtServicio;
    private javax.swing.JTextField txtTipoVehiculo;
    private javax.swing.JTextField txtoServicio;
    // End of variables declaration//GEN-END:variables
}
