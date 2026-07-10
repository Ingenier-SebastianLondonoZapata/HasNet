package formularios.Ventas;

import Controlador.BarraProceso.controladorBarraProceso;
import Controlador.BarraProceso.jcThread;
import Vista.BarraProceso.vistaBarraProceso;
import formularios.terceros.*;
import clases.Instancias;
import clases.Ventas.ndFactura;
import clases.big;
import Modelo.Terceros.ModeloContacto;
import clases.metodosGenerales;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class infFacturarLotes extends javax.swing.JInternalFrame {

    metodosGenerales metodos = new metodosGenerales();
    private Instancias instancias;
    TableRowSorter modeloOrdenado;

    private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension dimBarra = null;
    DefaultTableModel clientes;
    Object[] datos;
    String simbolo = "";

    public infFacturarLotes() {

        initComponents();

        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
        dimBarra = Barra.getPreferredSize();
        Barra.setSize(0, 0);
        Barra.setPreferredSize(new Dimension(0, 0));
        setBorder(null);
        repaint();

        instancias = Instancias.getInstancias();
        txtLote.setText((String) instancias.getSql().getNumConsecutivo("LOTECCOB")[0]);

        DefaultTableModel modelo = (DefaultTableModel) tblClientes.getModel();
        modeloOrdenado = new TableRowSorter<>(modelo);
        tblClientes.setRowSorter(modeloOrdenado);

        clientes = (DefaultTableModel) tblClientes.getModel();
        datos = instancias.getSql().getDatosMaestra();

        txtFechaFactura.setFormat(2);
        txtFechaFactura.setText(metodosGenerales.fecha());
        this.simbolo = instancias.getSimbolo();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        scrFormulario = new javax.swing.JScrollPane();
        pnlFormulario = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        lbNit1 = new javax.swing.JLabel();
        txtCliente = new javax.swing.JTextField();
        lbVendedor6 = new javax.swing.JLabel();
        cmbMes = new javax.swing.JComboBox();
        jPanel11 = new javax.swing.JPanel();
        btnAggCliente = new javax.swing.JButton();
        btnRemCliente = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        cmbClientes = new javax.swing.JComboBox();
        jButton2 = new javax.swing.JButton();
        lbAbono = new javax.swing.JLabel();
        txtLote = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnLimpiar = new javax.swing.JButton();
        btnFacturar = new javax.swing.JButton();
        btnAnular = new javax.swing.JButton();
        btnReImprimir1 = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtDiasPlazo = new javax.swing.JTextField();
        txtFechaFactura = new datechooser.beans.DateChooserCombo();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        chkPendientes = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        chkTodos = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
        chkNinguno = new javax.swing.JCheckBox();
        lbAbono1 = new javax.swing.JLabel();
        txtCantidadRegistros = new javax.swing.JTextField();

        setTitle("Factura");

        pnlFormulario.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        lbNit1.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit1.setText("Cliente:");

        txtCliente.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtClienteKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtClienteKeyReleased(evt);
            }
        });

        lbVendedor6.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbVendedor6.setText("Mes a facturar:");

        cmbMes.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        cmbMes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" }));
        cmbMes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbMesItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbNit1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCliente)
                    .addComponent(lbVendedor6, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
                    .addComponent(cmbMes, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(lbNit1)
                .addGap(0, 0, 0)
                .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(lbVendedor6)
                .addGap(0, 0, 0)
                .addComponent(cmbMes, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

        btnAggCliente.setBackground(new java.awt.Color(204, 204, 204));
        btnAggCliente.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnAggCliente.setForeground(new java.awt.Color(255, 255, 255));
        btnAggCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/agregar.png"))); // NOI18N
        btnAggCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAggCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAggClienteActionPerformed(evt);
            }
        });

        btnRemCliente.setBackground(new java.awt.Color(204, 204, 204));
        btnRemCliente.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnRemCliente.setForeground(new java.awt.Color(153, 0, 0));
        btnRemCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/borrar2.png"))); // NOI18N
        btnRemCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRemCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemClienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAggCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRemCliente)
                .addContainerGap(11, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAggCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRemCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));

        cmbClientes.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        jButton2.setBackground(new java.awt.Color(204, 204, 204));
        jButton2.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jButton2.setText("GENERAR");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        lbAbono.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbAbono.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbAbono.setText("Lote No.");

        txtLote.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtLote.setForeground(new java.awt.Color(255, 0, 0));
        txtLote.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtLote.setText("1");
        txtLote.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(cmbClientes, 0, 214, Short.MAX_VALUE)
                        .addGap(60, 60, 60)
                        .addComponent(lbAbono)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtLote, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2)))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbAbono, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLote, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        btnLimpiar.setBackground(new java.awt.Color(204, 204, 204));
        btnLimpiar.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/limpiar.png"))); // NOI18N
        btnLimpiar.setText("LIMPIAR");
        btnLimpiar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLimpiar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnLimpiar.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        btnFacturar.setBackground(new java.awt.Color(46, 204, 113));
        btnFacturar.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnFacturar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
        btnFacturar.setText("FACTURAR");
        btnFacturar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnFacturar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnFacturar.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnFacturar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFacturarActionPerformed(evt);
            }
        });
        btnFacturar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                btnFacturarKeyReleased(evt);
            }
        });

        btnAnular.setBackground(new java.awt.Color(241, 148, 138));
        btnAnular.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnAnular.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/borrar2.png"))); // NOI18N
        btnAnular.setText("ANULAR");
        btnAnular.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAnular.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnAnular.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnAnular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnularActionPerformed(evt);
            }
        });

        btnReImprimir1.setBackground(new java.awt.Color(247, 220, 111));
        btnReImprimir1.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnReImprimir1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/imprimir.png"))); // NOI18N
        btnReImprimir1.setText("REIMPRIMIR");
        btnReImprimir1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReImprimir1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnReImprimir1.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnReImprimir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReImprimir1ActionPerformed(evt);
            }
        });

        btnActualizar.setBackground(new java.awt.Color(93, 173, 226));
        btnActualizar.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/actualizar.png"))); // NOI18N
        btnActualizar.setText("ACTUALIZAR");
        btnActualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnActualizar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnActualizar.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jLabel1.setText("Plazo:");

        txtDiasPlazo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtDiasPlazo.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtFechaFactura.setFieldFont(new java.awt.Font("Century Gothic", java.awt.Font.PLAIN, 12));
        txtFechaFactura.addCommitListener(new datechooser.events.CommitListener() {
            public void onCommit(datechooser.events.CommitEvent evt) {
                txtFechaFacturaOnCommit(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(btnActualizar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnReImprimir1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(btnAnular)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDiasPlazo, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFechaFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnFacturar, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtFechaFactura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnFacturar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnLimpiar)
                        .addComponent(btnAnular, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnReImprimir1)
                        .addComponent(btnActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(txtDiasPlazo, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(22, 22, 22))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel3.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel3.setText("Identificación:");

        txtId.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        txtId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIdKeyReleased(evt);
            }
        });

        chkPendientes.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(chkPendientes);
        chkPendientes.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        chkPendientes.setText("Solo pendientes");
        chkPendientes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkPendientesItemStateChanged(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel4.setText("Nombre:");

        txtNombre.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreKeyReleased(evt);
            }
        });

        chkTodos.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(chkTodos);
        chkTodos.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        chkTodos.setText("Todos");
        chkTodos.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkTodosItemStateChanged(evt);
            }
        });

        tblClientes.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Identificación", "Nombre", "Servicio", "Valor", "Pendiente", "Total", "Facturar", "idSistema", "Periodicidad", "cantParaIncremento"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, true, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblClientes.setRowHeight(22);
        tblClientes.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblClientes);
        if (tblClientes.getColumnModel().getColumnCount() > 0) {
            tblClientes.getColumnModel().getColumn(0).setMinWidth(50);
            tblClientes.getColumnModel().getColumn(0).setPreferredWidth(100);
            tblClientes.getColumnModel().getColumn(0).setMaxWidth(130);
            tblClientes.getColumnModel().getColumn(1).setMinWidth(120);
            tblClientes.getColumnModel().getColumn(1).setPreferredWidth(120);
            tblClientes.getColumnModel().getColumn(1).setMaxWidth(140);
            tblClientes.getColumnModel().getColumn(4).setMinWidth(120);
            tblClientes.getColumnModel().getColumn(4).setPreferredWidth(120);
            tblClientes.getColumnModel().getColumn(4).setMaxWidth(150);
            tblClientes.getColumnModel().getColumn(5).setMinWidth(120);
            tblClientes.getColumnModel().getColumn(5).setPreferredWidth(120);
            tblClientes.getColumnModel().getColumn(5).setMaxWidth(150);
            tblClientes.getColumnModel().getColumn(6).setMinWidth(0);
            tblClientes.getColumnModel().getColumn(6).setPreferredWidth(0);
            tblClientes.getColumnModel().getColumn(6).setMaxWidth(0);
            tblClientes.getColumnModel().getColumn(7).setMinWidth(60);
            tblClientes.getColumnModel().getColumn(7).setPreferredWidth(60);
            tblClientes.getColumnModel().getColumn(7).setMaxWidth(60);
            tblClientes.getColumnModel().getColumn(8).setMinWidth(0);
            tblClientes.getColumnModel().getColumn(8).setPreferredWidth(0);
            tblClientes.getColumnModel().getColumn(8).setMaxWidth(0);
            tblClientes.getColumnModel().getColumn(9).setMinWidth(80);
            tblClientes.getColumnModel().getColumn(9).setPreferredWidth(80);
            tblClientes.getColumnModel().getColumn(9).setMaxWidth(80);
            tblClientes.getColumnModel().getColumn(10).setMinWidth(0);
            tblClientes.getColumnModel().getColumn(10).setPreferredWidth(0);
            tblClientes.getColumnModel().getColumn(10).setMaxWidth(0);
        }

        chkNinguno.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(chkNinguno);
        chkNinguno.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        chkNinguno.setSelected(true);
        chkNinguno.setText("Ninguno");
        chkNinguno.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkNingunoItemStateChanged(evt);
            }
        });

        lbAbono1.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        lbAbono1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbAbono1.setText("Cant Registros:");

        txtCantidadRegistros.setEditable(false);
        txtCantidadRegistros.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtCantidadRegistros.setForeground(new java.awt.Color(255, 0, 0));
        txtCantidadRegistros.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCantidadRegistros.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(chkPendientes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkTodos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(chkNinguno))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lbAbono1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(txtCantidadRegistros, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                        .addComponent(chkPendientes)
                        .addComponent(chkTodos, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                        .addComponent(chkNinguno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(5, 5, 5)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                .addGap(3, 3, 3)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCantidadRegistros, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbAbono1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout pnlFormularioLayout = new javax.swing.GroupLayout(pnlFormulario);
        pnlFormulario.setLayout(pnlFormularioLayout);
        pnlFormularioLayout.setHorizontalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlFormularioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10))
        );
        pnlFormularioLayout.setVerticalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            .addComponent(scrFormulario)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnFacturarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFacturarActionPerformed
        metodos.msgAdvertenciaAjustado(this, "ESTE PROCESO ES IRREVERSIBLE");

        if (txtDiasPlazo.getText().equals("")) {
            metodos.msgAdvertenciaAjustado(this, "No ha ingresado los días de plazo");
            return;
        }

        int cantidad = 0;
        for (int i = 0; i < tblClientes.getRowCount(); i++) {
            if (tblClientes.getValueAt(i, 7).equals(true)) {
                cantidad = cantidad + 1;
            }
        }

        if (cantidad <= 0) {
            metodos.msgAdvertenciaAjustado(this, "Debe seleccionar almenos 1 registro");
            return;
        }

        int ser = 0;
        Object[][] aumentos = new Object[cantidad][5];
        for (int i = 0; i < tblClientes.getRowCount(); i++) {
            if (tblClientes.getValueAt(i, 7).equals(true)) {
                int cantFacturados = Integer.parseInt(instancias.getSql().getCantFacturado(tblClientes.getValueAt(i, 0).toString())[0].toString());
                cantFacturados = cantFacturados + 1;

                int cantParaIncremento = Integer.parseInt(tblClientes.getValueAt(i, 10).toString());
                if (cantFacturados >= cantParaIncremento) {
                    aumentos[ser][0] = tblClientes.getValueAt(i, 0);
                    aumentos[ser][1] = tblClientes.getValueAt(i, 1);
                    aumentos[ser][2] = tblClientes.getValueAt(i, 2);
                    aumentos[ser][3] = tblClientes.getValueAt(i, 6);
                    aumentos[ser][4] = big.getBigDecimal(datos[112].toString());
                    ser++;
                }
            }
        }

        if (ser > 0) {
            dlgAumentoFacturaAutomatica aumentosFact = new dlgAumentoFacturaAutomatica(null, false, aumentos);
            aumentosFact.setVisible(true);
        }

        if (metodos.msgPregunta(this, "¿Desea continuar?") == 0) {
            for (int i = 0; i < tblClientes.getRowCount(); i++) {

                if (tblClientes.getValueAt(i, 7).equals(true)) {
                    String numLote = "LOTECCOB-" + (String) instancias.getSql().getNumConsecutivo("LOTECCOB")[0];

//                    String factura = instancias.getFacturaContenedor().getPnlFactura().facturarCuentaCobro(txtDiasPlazo.getText(),
   //                         tblClientes.getValueAt(i, 0).toString(), "123-22", big.getMoneda(this.simbolo + " 0"), numLote, txtFechaFactura.getText());

                    int cantFacturados = Integer.parseInt(instancias.getSql().getCantFacturado(tblClientes.getValueAt(i, 0).toString())[0].toString());
                    cantFacturados = cantFacturados + 1;

                    int cantParaIncremento = Integer.parseInt(tblClientes.getValueAt(i, 10).toString());
                    if (cantFacturados >= cantParaIncremento) {
                        cantFacturados = 0;
                        instancias.getCuentaCobroContenedor().getPnlFactura().modificarCuentaCobro(tblClientes.getValueAt(i, 0).toString().replace("CCOBRO-", ""));
                    }

                    String mes = String.valueOf(cmbMes.getSelectedIndex());
                    if (mes.length() <= 1) {
                        mes = "0" + mes;
                    }
                    String fecha = metodosGenerales.anho() + "-" + mes + "-01";

                    if (!instancias.getSql().modificarFechaUltimoPago(tblClientes.getValueAt(i, 0).toString(), fecha, String.valueOf(cantFacturados))) {
                        metodos.msgError(this, "Error al agregar la fecha de pago");
                        return;
                    }

/*                    Object[] lote = {numLote, "", factura, metodos.fechaConsulta(metodosGenerales.fecha()), instancias.getUsuario(), tblClientes.getValueAt(i, 0).toString()};

                    if (!instancias.getSql().agregarLote(lote)) {
                        metodos.msgError(this, "Error al guardar la información del lote");
                        return;
                    }
*/
                    try {
                        Thread.sleep(2000);
                    } catch (Exception e) {
                    }

                } else {

                }
            }

            if (!instancias.getSql().aumentarConsecutivo("LOTECCOB", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("LOTECCOB")[0]) + 1)) {
                metodos.msgError(this, "Hubo un problema al guardar en el consecutivo del abono");
            }

            metodos.msgExito(this, "Facturas generadas con exito");
            btnLimpiarActionPerformed(evt);
        }
    }//GEN-LAST:event_btnFacturarActionPerformed

    private void btnFacturarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnFacturarKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_btnFacturarKeyReleased

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        cmbClientes.removeAllItems();

        DefaultTableModel y = (DefaultTableModel) tblClientes.getModel();
        int i, j = tblClientes.getRowCount();
        for (i = 0; i < j; i++) {
            y.removeRow(0);
        }

        txtCantidadRegistros.setText("0");
        txtId.setText("");
        txtNombre.setText("");
        txtDiasPlazo.setText("");
        chkNinguno.setSelected(isIcon);
        txtLote.setText((String) instancias.getSql().getNumConsecutivo("LOTECCOB")[0]);
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        cargarTablaClientes();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void chkTodosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkTodosItemStateChanged
        for (int i = 0; i < tblClientes.getRowCount(); i++) {
            tblClientes.setValueAt(true, i, 7);
        }
    }//GEN-LAST:event_chkTodosItemStateChanged

    private void btnAnularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnularActionPerformed
        String consecutivo = "";

        try {
            consecutivo = "LOTECCOB-" + metodos.msgIngresarEnter(this, "Número de lote a anular");
        } catch (Exception e) {
            return;
        }

        if (consecutivo.equals("LOTECCOB-")) {
            return;
        }

        boolean anulado = instancias.getSql().getDocumentoAnulado("bdFactura", "Where cxc='" + consecutivo + "' ");

        if (anulado) {
            metodos.msgAdvertenciaAjustado(this, "Este lote se encuentra anulado");
            return;
        }

        if (metodos.msgPregunta(this, "¿Esta seguro de anular este lote?") == 0) {

            instancias.getSql().anularLote(consecutivo);

            Object[][] facturas = instancias.getSql().facturasLote(consecutivo);

            for (Object[] factura : facturas) {
                instancias.getSql().anularFacturaVerificadorFacturas(factura[0].toString());
            }

            metodos.msgExito(this, "Lote anulado con éxito");
        }
    }//GEN-LAST:event_btnAnularActionPerformed

    private void btnReImprimir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReImprimir1ActionPerformed
        String consecutivo = "";
        try {
            consecutivo = "LOTECCOB-" + metodos.msgIngresarEnter(this, "Lote a reimprimir");
        } catch (Exception e) {
        }

        if (consecutivo.equals("LOTECCOB-")) {
            return;
        }

        boolean anulado = instancias.getSql().getDocumentoAnulado("bdFactura", "Where cxc ='" + consecutivo + "' ");

        if (anulado) {
            metodos.msgAdvertenciaAjustado(this, "Este lote se encuentra anulado");
            return;
        }

        if (metodos.msgPregunta(this, "¿Desea reimprimir este lote?") == 0) {

            System.out.println("tranqiioñlo: " + consecutivo);
            Object[][] facturas = instancias.getSql().facturasLote(consecutivo);

            for (Object[] factura : facturas) {

                String tipo = "";
                String tipoImpr = instancias.getConfiguraciones().getTipoImpresion();
                if (tipoImpr.equals("Con-Codigo")) {
                } else if (tipoImpr.equals("Sin-Codigo")) {
                    tipo = "factura" + instancias.getRegimen() + "1";
                } else if (tipoImpr.equals("Imei")) {
                    tipo = "factura" + instancias.getRegimen() + "Imei";
                }

                String titulo;
                if (instancias.getConfiguraciones().isRestaurante()) {
                    if ((Boolean) datos[54]) {
                        titulo = "Turno";
                    } else {
                        titulo = "";
                    }
                } else if ((Boolean) datos[57]) {
                    titulo = "Num Fact2";
                } else {
                    titulo = "";
                }

                String impresora = datos[82].toString();
                String impoconsumo = datos[84].toString();
                String retenciones = datos[85].toString();

                String condicion;
                if ((Boolean) datos[50] && instancias.getConfiguraciones().isRestaurante()) {
                    condicion = sentenciaImpresionFactura("agrupada", " WHERE bdFactura.factura = '" + factura[0].toString() + "' ");
                } else {
                    if (instancias.getConfiguraciones().isRestaurante()) {
                        condicion = sentenciaImpresionFactura("agrupada", " WHERE bdFactura.factura = '" + factura[0].toString() + "' ");
                    } else {
                        condicion = sentenciaImpresionFactura("", " WHERE bdFactura.factura = '" + factura[0].toString() + "' ");
                    }
                }

                String encabezado = "Reimpresion";
                if (!(Boolean) datos[80]) {
                    encabezado = "";
                }

                ndFactura nodo = instancias.getSql().getDatosFactura(factura[0].toString());
                String infoEmpresa = metodosGenerales.convertToMultiline(instancias.getInformacionEmpresaReimpresion() + "\n" + nodo.getResolucion());

                instancias.getReporte().ver_Factura("", infoEmpresa, instancias.getLegal(), encabezado, instancias.getPie(), tipo,
                        factura[0].toString(), false, titulo, impresora, impoconsumo, retenciones, condicion, false);

                try {
                    Thread.sleep(2000);
                } catch (Exception e) {
                }
            }
        }
    }//GEN-LAST:event_btnReImprimir1ActionPerformed

    private void cmbMesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbMesItemStateChanged
//        cargarTablaClientes();

        DefaultTableModel y = (DefaultTableModel) tblClientes.getModel();
        int i, j = tblClientes.getRowCount();
        for (i = 0; i < j; i++) {
            y.removeRow(0);
        }
        txtCantidadRegistros.setText("0");
    }//GEN-LAST:event_cmbMesItemStateChanged

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        cargarTablaClientes();
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void chkPendientesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkPendientesItemStateChanged
        for (int i = 0; i < tblClientes.getRowCount(); i++) {
            if (tblClientes.getValueAt(i, 5).equals(this.simbolo + " 0")) {
                tblClientes.setValueAt(false, i, 7);
            } else {
                tblClientes.setValueAt(true, i, 7);
            }
        }
    }//GEN-LAST:event_chkPendientesItemStateChanged

    private void txtIdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdKeyReleased
        modeloOrdenado.setRowFilter(RowFilter.regexFilter("(?i)" + txtId.getText(), 1));
        txtNombre.setText("");
    }//GEN-LAST:event_txtIdKeyReleased

    private void txtNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyReleased
        modeloOrdenado.setRowFilter(RowFilter.regexFilter("(?i)" + txtNombre.getText(), 2));
        txtId.setText("");
    }//GEN-LAST:event_txtNombreKeyReleased

    private void chkNingunoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkNingunoItemStateChanged
        for (int i = 0; i < tblClientes.getRowCount(); i++) {
            tblClientes.setValueAt(false, i, 7);
        }
    }//GEN-LAST:event_chkNingunoItemStateChanged

    private void txtClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClienteKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtClienteKeyPressed

    private void txtClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClienteKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cargarCliente(txtCliente.getText());
        }
    }//GEN-LAST:event_txtClienteKeyReleased

    private void btnAggClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAggClienteActionPerformed
        if (txtCliente.getText().equals("")) {
            metodos.msgError(null, "Cargue primero un cliente");
        } else {
            if (yaEsta(cmbClientes, txtCliente.getText())) {
                metodos.msgAdvertencia(null, "Ya cargo este cliente");
            } else {
                cmbClientes.addItem(txtCliente.getText());
                cmbClientes.setSelectedItem(txtCliente.getText());
                cargarTablaClientes();
            }
            txtCliente.setText("");
        }
    }//GEN-LAST:event_btnAggClienteActionPerformed

    private void btnRemClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemClienteActionPerformed
        cmbClientes.removeItem(cmbClientes.getSelectedItem());

        if (cmbClientes.getItemCount() > 0) {
            cargarTablaClientes();
        } else {
            DefaultTableModel y = (DefaultTableModel) tblClientes.getModel();
            int i, j = tblClientes.getRowCount();
            for (i = 0; i < j; i++) {
                y.removeRow(0);
            }
        }
    }//GEN-LAST:event_btnRemClienteActionPerformed

    private void txtFechaFacturaOnCommit(datechooser.events.CommitEvent evt) {//GEN-FIRST:event_txtFechaFacturaOnCommit

    }//GEN-LAST:event_txtFechaFacturaOnCommit

    public String sentenciaImpresionFactura(String tipo, String condicion) {

        String sentencia = "";

        if (tipo.equals("agrupada")) {
            sentencia = "SELECT '' AS anexoFacturacion, bdfactura.idFactura AS idFactura, CAST(SUBSTR(bdfactura.idFactura,6,100) AS SIGNED) AS ordenId, bdterceros.id AS cliente, "
                    + "bdfactura.vendedor AS vendedor, bdfactura.red AS red, bdfactura.fechaFactura AS fechaFactura, bdfactura.fechaVencimiento AS fechaVencimiento, "
                    + "bdfactura.efectivoGeneral AS efectivoGeneral, bdfactura.ncGeneral AS ncGeneral, bdfactura.chequeGeneral AS chequeGeneral, "
                    + "bdfactura.targetaGeneral AS targetaGeneral, bdfactura.totalGeneral AS totalGeneral, bdfactura.descuentoGeneral AS descuentoGeneral,"
                    + "bdfactura.ivaGeneral AS ivaGeneral, bdfactura.subtotalGeneral AS subtotalGeneral, bdfactura.comprobante AS comprobante, "
                    + "bdfactura.cotizacion AS cotizacion, bdfactura.anulada AS anulada, bdfactura.anula AS anula, bdfactura.credito AS credito, bdfactura.cxc AS cxc, "
                    + "bdfactura.usuario AS usuario, bdfactura.rtIva AS rtIva, bdfactura.rtIca AS rtIca, bdfactura.rtFuente AS rtFuente, bdfactura.otros AS otros, "
                    + "bdfactura.observacion AS observacion, bdfactura.anulada1 AS anulada1, bdfactura.anula1 AS anula1, bdfactura.credito1 AS credito1, bdfactura.cxc1 AS cxc1, "
                    + "bdfactura.usuario1 AS usuario1, bdfactura.fechaAlerta AS fechaAlerta, bdfactura.terminal AS terminal, bdfactura.estadoGeneral AS estadoGeneral, "
                    + "bdfactura.estado2 AS estado2, bdfactura.devuelta AS devuelta, bdfactura.factura AS factura, bdfactura.resolucion AS resolucion, "
                    + "bdfactura.fechaAnulacion AS fechaAnulacion, bdfactura.cuadreAnulacion AS cuadreAnulacion, bdfactura.usuarioAnula AS usuarioAnula, bdfactura.copago AS copago, "
                    + "bdfactura.garantia AS garantia, bdfactura.diasGarantia AS diasGarantia, bdfactura.rango AS rango, bdfactura.terminos AS terminos, "
                    + "bdfactura.notaAnulacion AS notaAnulacion, bdfactura.conseMesa AS conseMesa, bdfactura.producto AS producto, bdfactura.lista AS lista, "
                    + "bdfactura.cantidad AS cantidad, bdfactura.descuento AS descuento,  bdfactura.total AS total, bdfactura.iva AS iva, bdfactura.subtotal AS subtotal, "
                    + "bdfactura.NC AS NC, bdfactura.utilidad AS utilidad, bdfactura.concepto AS concepto, bdfactura.porcDescuento AS porcDescuento,  "
                    + "bdfactura.descripcion AS descripcion,  bdfactura.plu AS plu, bdfactura.cant2 AS cant2, bdfactura.estado AS estado, bdfactura.porcIva AS porcIva, "
                    + "bdfactura.tercero AS tercero, bdfactura.utilidad1 AS utilidad1, bdterceros.nombre AS nombre, bdterceros.telefono AS telefono, "
                    + "bdterceros.direccion AS direccion, IF(ISNULL(bdprestamo.cuotaInicial),0,bdprestamo.cuotaInicial) AS cuotaInicial2, bdfactura.factura AS id2, "
                    + "bdproductos.ubicacion1 AS ubicacion1, bdproductos.referencia AS referencia, (bdfactura.subtotalGeneral - bdfactura.descuentoGeneral) AS valor3, "
                    + "IF((bdfactura.producto = 'PROD-000000032'),bdfactura.impuesto,'0') AS impuesto, bdcxc.plazo AS plazo, bdterceros.nombreContacto AS nombreContacto, "
                    + "bdterceros.cargo AS cargo, bdfactura.turno AS turno, bdproductos.grupo AS Grupo, bdterceros.eps AS eps, bdepsprecargados.nombre AS nombreEps, "
                    + "bdfactura.Id AS Id, bdfactura.placa AS placa, bdplacas.tipo AS tipo, bdparqueaderoautos.fIngreso AS fIngreso, bdparqueaderoautos.fSalida AS fSalida,"
                    + "bdparqueaderoautos.hIngreso AS hIngreso, bdparqueaderoautos.hSalida AS hSalida, bdparqueaderoautos.horas AS horas, bdfactura.imei AS imei, "
                    + "bddetalleproductos.color AS color,  bdterceros.idSistema AS idSistema, bdfactura.impoconsumo AS impoconsumo, bdproductos.Codigo AS Codigo, "
                    + "bdfactura.totalPropina AS totalPropina, bdfactura.hora AS hora, bdfactura.idProd AS idProd, bdgrupo.nombre AS nombreGrupo, "
                    + "bdfactura.impoGeneral AS impoGeneral,  bdfactura.porcImpo AS porcImpo, bdfactura.bodega AS bodega "
                    + "FROM ((((((((bdfactura LEFT JOIN bdprestamo ON ((bdfactura.idFactura = bdprestamo.factura))) LEFT JOIN bdcxc ON ((bdfactura.factura = bdcxc.factura2))) "
                    + "LEFT JOIN bdplacas ON ((bdfactura.placa = bdplacas.placa))) LEFT JOIN bdparqueaderoautos ON ((bdparqueaderoautos.factura = bdfactura.factura))) "
                    + "LEFT JOIN bddetalleproductos ON ((bdfactura.idProd = bddetalleproductos.Id))) LEFT JOIN bdproductos ON ((bdfactura.producto = bdproductos.idSistema))) "
                    + "LEFT JOIN (bdterceros LEFT JOIN bdepsprecargados ON ((bdepsprecargados.Id = bdterceros.eps))) ON ((bdfactura.cliente = bdterceros.idSistema))) "
                    + "LEFT JOIN bdgrupo ON ((bdproductos.grupo = bdgrupo.codigo))) "
                    + condicion + " GROUP BY '', bdcxc.plazo,bdFactura.idFactura, CAST(SUBSTR(bdfactura.idFactura,6,100) AS SIGNED),"
                    + "bdFactura.cliente, bdFactura.vendedor, bdFactura.red, bdFactura.fechaFactura, bdFactura.fechaVencimiento, bdFactura.efectivoGeneral, bdFactura.ncGeneral,"
                    + "bdFactura.chequeGeneral, bdFactura.targetaGeneral, bdFactura.totalGeneral, bdFactura.descuentoGeneral, bdFactura.ivaGeneral, bdFactura.subtotalGeneral, "
                    + "bdFactura.comprobante, bdFactura.cotizacion, bdFactura.anulada, bdFactura.anula, bdFactura.credito, bdFactura.cxc, bdFactura.usuario, bdFactura.rtIva, "
                    + "bdFactura.rtIca, bdFactura.rtFuente, bdFactura.otros, bdFactura.anulada1, bdFactura.anula1, bdFactura.credito1, bdFactura.cxc1, bdFactura.usuario1, "
                    + "bdFactura.fechaAlerta, bdFactura.terminal, bdFactura.estadoGeneral, bdFactura.estado2, bdFactura.devuelta, bdFactura.factura, bdFactura.resolucion,"
                    + "bdFactura.fechaAnulacion, bdFactura.cuadreAnulacion, bdFactura.usuarioAnula, bdFactura.copago, bdFactura.diasGarantia, bdFactura.rango, "
                    + "bdFactura.conseMesa, bdFactura.producto, bdFactura.lista, bdFactura.NC, bdFactura.porcDescuento, bdFactura.descripcion, bdFactura.plu, bdFactura.estado, "
                    + "bdFactura.porcIva, bdFactura.tercero, bdFactura.utilidad1, bdTerceros.nombre, bdTerceros.telefono, bdTerceros.direccion,"
                    + "IF(ISNULL(bdprestamo.cuotaInicial),0,bdprestamo.cuotaInicial), bdfactura.factura, bdproductos.ubicacion1, bdproductos.referencia,"
                    + "(bdfactura.subtotalGeneral - bdfactura.descuentoGeneral), bdFactura.impuesto, bdcxc.plazo, bdterceros.nombreContacto, bdterceros.cargo, bdFactura.turno,"
                    + "bdproductos.Grupo, bdterceros.eps, bdepsprecargados.nombre, bdFactura.placa, bdplacas.tipo, bdparqueaderoautos.fIngreso, bdparqueaderoautos.fSalida,"
                    + "bdparqueaderoautos.hIngreso, bdparqueaderoautos.hSalida, bdparqueaderoautos.horas, bdFactura.imei, bddetalleproductos.color, bdterceros.idSistema, "
                    + "bdFactura.impoconsumo, bdproductos.Codigo, bdFactura.totalPropina, bdFactura.hora, bdFactura.impoGeneral, bdFactura.porcImpo,'' ";
        } else {
            sentencia = "SELECT bdfactura.idFactura AS idFactura, CAST(SUBSTR(bdfactura.idFactura,6,100) AS SIGNED) AS ordenId, bdterceros.id AS cliente, bdfactura.vendedor AS vendedor,  bdfactura.red AS red, "
                    + "bdfactura.fechaFactura AS fechaFactura, bdfactura.fechaVencimiento AS fechaVencimiento, bdfactura.efectivoGeneral AS efectivoGeneral, bdfactura.ncGeneral AS ncGeneral, "
                    + "bdfactura.chequeGeneral AS chequeGeneral, bdfactura.targetaGeneral AS targetaGeneral, bdfactura.totalGeneral AS totalGeneral, bdfactura.descuentoGeneral AS descuentoGeneral, "
                    + "bdfactura.ivaGeneral AS ivaGeneral, bdfactura.subtotalGeneral AS subtotalGeneral, bdfactura.comprobante AS comprobante, bdfactura.cotizacion AS cotizacion, bdfactura.anulada AS anulada, "
                    + "bdfactura.anula AS anula, bdfactura.credito AS credito, bdfactura.cxc AS cxc, bdfactura.usuario AS usuario, bdfactura.rtIva AS rtIva, bdfactura.rtIca AS rtIca, bdfactura.rtFuente AS rtFuente, "
                    + "bdfactura.otros AS otros, bdfactura.observacion AS observacion, bdfactura.anulada1 AS anulada1, bdfactura.anula1 AS anula1, bdfactura.credito1 AS credito1, bdfactura.cxc1 AS cxc1, "
                    + "bdfactura.usuario1 AS usuario1, bdfactura.fechaAlerta AS fechaAlerta, bdfactura.terminal AS terminal, bdfactura.estadoGeneral AS estadoGeneral, bdfactura.estado2 AS estado2, "
                    + "bdfactura.devuelta AS devuelta, bdfactura.factura AS factura, bdfactura.resolucion AS resolucion, bdfactura.fechaAnulacion AS fechaAnulacion, bdfactura.cuadreAnulacion AS cuadreAnulacion, "
                    + "bdfactura.usuarioAnula AS usuarioAnula, bdfactura.copago AS copago, bdfactura.garantia AS garantia, bdfactura.diasGarantia AS diasGarantia, bdfactura.rango AS rango, "
                    + "bdfactura.terminos AS terminos,  bdfactura.notaAnulacion AS notaAnulacion, bdfactura.conseMesa AS conseMesa, bdfactura.producto AS producto, bdfactura.lista AS lista, bdfactura.cantidad AS cantidad, "
                    + "bdfactura.descuento AS descuento,  bdfactura.total AS total, bdfactura.iva AS iva, bdfactura.subtotal AS subtotal, bdfactura.NC AS NC, bdfactura.utilidad AS utilidad, bdfactura.concepto AS concepto, "
                    + "bdfactura.porcDescuento AS porcDescuento,  bdfactura.descripcion AS descripcion,  bdfactura.plu AS plu, bdfactura.cant2 AS cant2, bdfactura.estado AS estado, bdfactura.porcIva AS porcIva, "
                    + "bdfactura.tercero AS tercero, bdfactura.utilidad1 AS utilidad1, bdterceros.nombre AS nombre, bdterceros.telefono AS telefono, bdterceros.direccion AS direccion, "
                    + "IF(ISNULL(bdprestamo.cuotaInicial),0,bdprestamo.cuotaInicial) AS cuotaInicial2, bdfactura.factura AS id2, bdproductos.ubicacion1 AS ubicacion1, bdproductos.referencia AS referencia, "
                    + "(bdfactura.subtotalGeneral - bdfactura.descuentoGeneral) AS valor3, IF((bdfactura.producto = 'PROD-000000032'),bdfactura.impuesto,'0') AS impuesto, bdcxc.plazo AS plazo, "
                    + "bdterceros.nombreContacto AS nombreContacto, bdterceros.cargo AS cargo, bdfactura.turno AS turno, bdproductos.grupo AS Grupo, bdterceros.eps AS eps, bdepsprecargados.nombre AS nombreEps, "
                    + "bdfactura.Id AS Id, bdfactura.placa AS placa,  bdplacas.tipo AS tipo, bdparqueaderoautos.fIngreso AS fIngreso,  bdparqueaderoautos.fSalida AS fSalida, bdparqueaderoautos.hIngreso AS hIngreso, "
                    + "bdparqueaderoautos.hSalida AS hSalida, bdparqueaderoautos.horas AS horas,  bdfactura.imei AS imei, bddetalleproductos.color AS color,  bdterceros.idSistema AS idSistema, "
                    + "bdfactura.impoconsumo AS impoconsumo,  bdproductos.Codigo AS Codigo, bdfactura.totalPropina AS totalPropina,  bdfactura.hora AS hora, bdfactura.idProd AS idProd, bdgrupo.nombre AS nombreGrupo, "
                    + "bdfactura.impoGeneral AS impoGeneral,  bdfactura.porcImpo AS porcImpo, bdfactura.bodega AS bodega "
                    + "FROM ((((((((bdfactura LEFT JOIN bdprestamo ON ((bdfactura.idFactura = bdprestamo.factura))) LEFT JOIN bdcxc ON ((bdfactura.factura = bdcxc.factura2))) LEFT JOIN bdplacas "
                    + "ON ((bdfactura.placa = bdplacas.placa))) LEFT JOIN bdparqueaderoautos ON ((bdparqueaderoautos.factura = bdfactura.factura))) LEFT JOIN bddetalleproductos ON ((bdfactura.idProd = bddetalleproductos.Id))) "
                    + "LEFT JOIN bdproductos ON ((bdfactura.producto = bdproductos.idSistema))) LEFT JOIN (bdterceros LEFT JOIN bdepsprecargados ON ((bdepsprecargados.Id = bdterceros.eps))) "
                    + "ON ((bdfactura.cliente = bdterceros.idSistema))) LEFT JOIN bdgrupo ON ((bdproductos.grupo = bdgrupo.codigo))) "
                    + condicion + " GROUP BY bdfactura.idFactura,CAST(SUBSTR(bdfactura.idFactura,6,100)AS SIGNED),bdterceros.id,bdfactura.vendedor,bdfactura.red,bdfactura.fechaFactura,bdfactura.fechaVencimiento,bdfactura.efectivoGeneral,"
                    + "bdfactura.ncGeneral,bdfactura.chequeGeneral,bdfactura.targetaGeneral,bdfactura.totalGeneral,bdfactura.descuentoGeneral,bdfactura.ivaGeneral,bdfactura.subtotalGeneral,bdfactura.comprobante,"
                    + "bdfactura.cotizacion,bdfactura.anulada,bdfactura.anula,bdfactura.credito,bdfactura.cxc,bdfactura.usuario,bdfactura.rtIva,bdfactura.rtIca,bdfactura.rtFuente,bdfactura.otros,bdfactura.anulada1,"
                    + "bdfactura.anula1,bdfactura.credito1,bdfactura.cxc1,bdfactura.usuario1,bdfactura.fechaAlerta,bdfactura.terminal,bdfactura.estadoGeneral,bdfactura.estado2,bdfactura.devuelta,bdfactura.resolucion,"
                    + "bdfactura.fechaAnulacion,bdfactura.cuadreAnulacion,bdfactura.usuarioAnula,bdfactura.copago,bdfactura.garantia,bdfactura.diasGarantia,bdfactura.rango,bdfactura.conseMesa,bdfactura.producto,bdfactura.lista,"
                    + "bdfactura.cantidad,bdfactura.descuento,bdfactura.total,bdfactura.iva,bdfactura.subtotal,bdfactura.NC,bdfactura.utilidad,bdfactura.porcDescuento,bdfactura.descripcion,bdfactura.plu,bdfactura.cant2,"
                    + "bdfactura.estado,bdfactura.porcIva,bdfactura.tercero,bdfactura.utilidad1,bdterceros.nombre,bdterceros.telefono,bdterceros.direccion,IF(ISNULL(bdprestamo.cuotaInicial),0,bdprestamo.cuotaInicial),"
                    + "bdfactura.factura,bdproductos.ubicacion1,bdproductos.referencia,(bdfactura.subtotalGeneral - bdfactura.descuentoGeneral),IF((bdfactura.producto = 'PROD-000000032'),bdfactura.impuesto,'0'),"
                    + "bdterceros.nombreContacto,bdterceros.cargo,bdfactura.turno,bdproductos.grupo,bdterceros.eps,bdepsprecargados.nombre,bdfactura.Id,bdfactura.placa,bdplacas.tipo,bdparqueaderoautos.fIngreso,"
                    + "bdparqueaderoautos.fSalida,bdparqueaderoautos.hIngreso,bdparqueaderoautos.hSalida,bdparqueaderoautos.horas,bdfactura.imei,bddetalleproductos.color,bdterceros.idSistema,bdfactura.impoconsumo,"
                    + "bdproductos.Codigo,bdgrupo.nombre,bdfactura.impoGeneral,bdfactura.porcImpo,bdfactura.bodega ORDER BY bdfactura.Id";
        }

        return sentencia;
    }

    jcThread barra2;

    private void cargarTablaClientes() {
        chkNinguno.setSelected(true);

        txtNombre.setText("");
        txtId.setText("");
        modeloOrdenado.setRowFilter(RowFilter.regexFilter("(?i)" + txtNombre.getText(), 2));

        DefaultTableModel modelo = (DefaultTableModel) tblClientes.getModel();

        while (tblClientes.getRowCount() > 0) {
            modelo.removeRow(0);
        }

        String returnCondicion = condicion();
        if (returnCondicion.equals("")) {
            return;
        }

        controladorBarraProceso controladorBarra = new controladorBarraProceso();
        vistaBarraProceso barra = new vistaBarraProceso(controladorBarra, Instancias.getInstancias());
        barra.show();

        this.barra2 = controladorBarra.getBarra();

        Object[][] dato = instancias.getSql().getRegistrosFacturacionAutomatica(returnCondicion);

        for (int i = 0; i < dato.length; i++) {
            Boolean agregarRegistro = true;
            if (tblClientes.getRowCount() > 0) {
                if (tblClientes.getValueAt(tblClientes.getRowCount() - 1, 0).equals(dato[i][0])) {
                    agregarRegistro = false;
                }
            }

            if (agregarRegistro) {
                String fechaUltimoPago = "", inicioFacturacion = "", finFacturacion = "", fechaEscogida;
                if (null != dato[i][4]) {
                    fechaUltimoPago = dato[i][4].toString();
                }

                inicioFacturacion = dato[i][7].toString();
                finFacturacion = dato[i][8].toString();
                if (inicioFacturacion.equals(finFacturacion)) {
                    finFacturacion = "2080-01-01";
                }

                String mes = String.valueOf(cmbMes.getSelectedIndex());
                if (String.valueOf(mes).length() == 1) {
                    mes = "0" + mes;
                }
                fechaEscogida = "01/" + mes + "/" + metodosGenerales.anho();

                if (fechaUltimoPago.equals("")) {
                    Long diferenciaInicial = metodos.restarFecha(metodos.fecha(inicioFacturacion), fechaEscogida);
                    Long diferenciaFinal = metodos.restarFecha(fechaEscogida, metodos.fecha(finFacturacion));

                    if (diferenciaInicial >= 0 && diferenciaFinal >= 0) {
                        ModeloContacto nodo = instancias.getSql().getDatosTercero(dato[i][1].toString());

                        BigDecimal cartera = big.getBigDecimal(instancias.getSql().getCarteraPendiente(dato[i][1].toString()));
                        if (cartera == null) {
                            cartera = big.getBigDecimal("0");
                        } else {
                            BigDecimal cartera1 = big.getBigDecimal(instancias.getSql().getCarteraPendiente2(dato[i][1].toString()));
                            cartera = cartera1.subtract(cartera);
                        }

                        BigDecimal total = cartera.add(big.getBigDecimal(dato[i][2].toString()));
                        clientes.addRow(new Object[]{dato[i][0], nodo.getId(), nodo.getNombre(), dato[i][3], big.setMoneda(big.getBigDecimal(dato[i][2])), big.setMoneda(cartera),
                            big.setMoneda(total), false, dato[i][1], dato[i][6], dato[i][9]});
                    }
                } else {
                    String periodicidad = dato[i][6].toString();
                    int numMes = cmbMes.getSelectedIndex();
                    int numFechaPago = Integer.parseInt(fechaUltimoPago.split("-")[1]);

                    if (!periodicidad.equals("Mensual")) {
                        numFechaPago = numFechaPago + 12;
                    }

                    if (!periodicidad.equals("Mensual")) {
                        int mesesSinFact = numFechaPago - numMes;

                        if (mesesSinFact >= 12) {
                            int numActual = Integer.parseInt(metodosGenerales.fecha().split("/")[2]);
                            int numFechaPago1 = Integer.parseInt(fechaUltimoPago.split("-")[0]);

                            if (numActual > numFechaPago1) {
                                Long diferenciaInicial = metodos.restarFecha(metodos.fecha(inicioFacturacion), fechaEscogida);
                                Long diferenciaFinal = metodos.restarFecha(fechaEscogida, metodos.fecha(finFacturacion));

                                if (diferenciaInicial >= 0 && diferenciaFinal >= 0) {

                                    ModeloContacto nodo = instancias.getSql().getDatosTercero(dato[i][1].toString());

                                    BigDecimal cartera = big.getBigDecimal(instancias.getSql().getCarteraPendiente(dato[i][1].toString()));
                                    if (cartera == null) {
                                        cartera = big.getBigDecimal("0");
                                    } else {
                                        BigDecimal cartera1 = big.getBigDecimal(instancias.getSql().getCarteraPendiente2(dato[i][1].toString()));
                                        cartera = cartera1.subtract(cartera);
                                    }

                                    BigDecimal total = cartera.add(big.getBigDecimal(dato[i][2].toString()));
                                    clientes.addRow(new Object[]{dato[i][0], nodo.getId(), nodo.getNombre(), dato[i][3], big.setMoneda(big.getBigDecimal(dato[i][2])), big.setMoneda(cartera),
                                        big.setMoneda(total), false, dato[i][1], dato[i][6], dato[i][9]});
                                }
                            }
                        }
                    } else {
                        if (numMes > numFechaPago) {
                            Long diferenciaInicial = metodos.restarFecha(metodos.fecha(inicioFacturacion), fechaEscogida);
                            Long diferenciaFinal = metodos.restarFecha(fechaEscogida, metodos.fecha(finFacturacion));

                            if (diferenciaInicial >= 0 && diferenciaFinal >= 0) {
                                ModeloContacto nodo = instancias.getSql().getDatosTercero(dato[i][1].toString());

                                BigDecimal cartera = big.getBigDecimal(instancias.getSql().getCarteraPendiente(dato[i][1].toString()));
                                if (cartera == null) {
                                    cartera = big.getBigDecimal("0");
                                } else {
                                    BigDecimal cartera1 = big.getBigDecimal(instancias.getSql().getCarteraPendiente2(dato[i][1].toString()));
                                    cartera = cartera1.subtract(cartera);
                                }

                                BigDecimal total = cartera.add(big.getBigDecimal(dato[i][2].toString()));

                                clientes.addRow(new Object[]{dato[i][0], nodo.getId(), nodo.getNombre(), dato[i][3], big.setMoneda(big.getBigDecimal(dato[i][2])), big.setMoneda(cartera),
                                    big.setMoneda(total), false, dato[i][1], dato[i][6], dato[i][9]});
                            }
                        } else {
                            int numActual = Integer.parseInt(metodosGenerales.fecha().split("/")[2]);
                            int numFechaPago1 = Integer.parseInt(fechaUltimoPago.split("-")[0]);

                            if (numActual > numFechaPago1) {
                                Long diferenciaInicial = metodos.restarFecha(metodos.fecha(inicioFacturacion), fechaEscogida);
                                Long diferenciaFinal = metodos.restarFecha(fechaEscogida, metodos.fecha(finFacturacion));

                                if (diferenciaInicial >= 0 && diferenciaFinal >= 0) {
                                    ModeloContacto nodo = instancias.getSql().getDatosTercero(dato[i][1].toString());

                                    BigDecimal cartera = big.getBigDecimal(instancias.getSql().getCarteraPendiente(dato[i][1].toString()));
                                    if (cartera == null) {
                                        cartera = big.getBigDecimal("0");
                                    } else {
                                        BigDecimal cartera1 = big.getBigDecimal(instancias.getSql().getCarteraPendiente2(dato[i][1].toString()));
                                        cartera = cartera1.subtract(cartera);
                                    }

                                    BigDecimal total = cartera.add(big.getBigDecimal(dato[i][2].toString()));
                                    clientes.addRow(new Object[]{dato[i][0], nodo.getId(), nodo.getNombre(), dato[i][3], big.setMoneda(big.getBigDecimal(dato[i][2])), big.setMoneda(cartera),
                                        big.setMoneda(total), false, dato[i][1], dato[i][6], dato[i][9]});
                                }
                            } else {
                                System.out.println("Se supone que debe estar saldado");
                            }
                        }
                    }
                }
            }
        }

        txtCantidadRegistros.setText(tblClientes.getRowCount() + "");

        this.barra2.detener(true);
    }

    private String condicion() {
        String sql = "where (estadoGeneral = 'PENDIENTE' AND anulada = 0) ";

        if (!sql.equals("") && cmbClientes.getItemCount() > 0) {
            sql = sql + "AND";
        }

        if (cmbMes.getSelectedIndex() == 0) {
            metodos.msgAdvertenciaAjustado(this, "No ha seleccionado mes a facturar");
            return "";
        }

        if (cmbClientes.getItemCount() > 0) {
            sql = sql + " (";
            for (int i = 0; i < cmbClientes.getItemCount(); i++) {
                sql = sql + " cliente = '" + cmbClientes.getItemAt(i) + "' OR";
            }
            sql = sql.substring(0, sql.length() - 2);
            sql = sql + ")";
        }

        System.out.println("Consulta Reporte Terceros: " + sql);
        return sql;
    }

    public void cargarCliente(String nit) {
        ModeloContacto nodo = instancias.getSql().getDatosTercero(nit);
        if (nodo.getId() != null) {
            txtCliente.setText(nodo.getIdSistema());
            btnAggClienteActionPerformed(null);
            return;
        }
        ventanaTerceros(nit);
    }

    public void ventanaTerceros(String nit) {
        buscClientes buscar = new buscClientes(instancias.getMenu(), true, false, null, "");
        buscar.setLocationRelativeTo(null);
        instancias.setBusClientes(buscar);
        instancias.setCampoActual(txtCliente);
        txtCliente.requestFocus();
        buscar.noEncontrado(nit);
        buscar.show();
    }

    public boolean yaEsta(JComboBox combo, String dato) {
        boolean sw = false;

        for (int i = 0; i < combo.getItemCount(); i++) {
            if (((String) combo.getItemAt(i)).equals(dato)) {
                return true;
            }
        }

        return sw;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAggCliente;
    private javax.swing.JButton btnAnular;
    private javax.swing.JButton btnFacturar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnReImprimir1;
    private javax.swing.JButton btnRemCliente;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox chkNinguno;
    private javax.swing.JCheckBox chkPendientes;
    private javax.swing.JCheckBox chkTodos;
    private javax.swing.JComboBox cmbClientes;
    private javax.swing.JComboBox cmbMes;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbAbono;
    private javax.swing.JLabel lbAbono1;
    private javax.swing.JLabel lbNit1;
    private javax.swing.JLabel lbVendedor6;
    private javax.swing.JPanel pnlFormulario;
    private javax.swing.JScrollPane scrFormulario;
    private javax.swing.JTable tblClientes;
    private javax.swing.JTextField txtCantidadRegistros;
    private javax.swing.JTextField txtCliente;
    private javax.swing.JTextField txtDiasPlazo;
    private datechooser.beans.DateChooserCombo txtFechaFactura;
    private javax.swing.JTextField txtId;
    private javax.swing.JLabel txtLote;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
