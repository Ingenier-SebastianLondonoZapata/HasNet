package formularios.Parqueadero;

import clases.Instancias;
import clases.productos.ndProducto;
import Modelo.Terceros.ModeloContacto;
import clases.metodosGenerales;
import Vista.Productos.VistaBuscadorProductos;
import formularios.productos.buscSubGrupos;
import formularios.terceros.buscClientes;
import formularios.terceros.buscEmpleados;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.util.Calendar;
import javax.swing.JComboBox;
import javax.swing.JComponent;

public class infRepLavadero1 extends javax.swing.JInternalFrame {

    metodosGenerales metodos = new metodosGenerales();
    private Instancias instancias;
    private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension dimBarra = null;

    public infRepLavadero1() {

        initComponents();

        instancias = Instancias.getInstancias();
        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
        dimBarra = Barra.getPreferredSize();
        Barra.setSize(0, 0);
        Barra.setPreferredSize(new Dimension(0, 0));
        setBorder(null);
        repaint();
//        dtInicio.setFormat(2);
//        dtFinal.setFormat(2);

        dtFinal.setSelectedDate(Calendar.getInstance());
        dtInicio.setSelectedDate(Calendar.getInstance());

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        scrFormulario = new javax.swing.JScrollPane();
        pnlFormulario = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        lbNit1 = new javax.swing.JLabel();
        lbNit12 = new javax.swing.JLabel();
        txtCliente = new javax.swing.JTextField();
        txtProveedor = new javax.swing.JTextField();
        lbBodega = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        lbBodega1 = new javax.swing.JLabel();
        rbtEstado = new javax.swing.JRadioButton();
        cmbEstados = new javax.swing.JComboBox();
        lbBodega2 = new javax.swing.JLabel();
        txtCodigo1 = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        btnAggProv = new javax.swing.JButton();
        btnRemProv = new javax.swing.JButton();
        btnAggCliente = new javax.swing.JButton();
        btnRemCliente = new javax.swing.JButton();
        btnAggProd = new javax.swing.JButton();
        btnRemProd = new javax.swing.JButton();
        btnAggProd1 = new javax.swing.JButton();
        btnRemProd1 = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        cmbClientes = new javax.swing.JComboBox();
        cmbEmpleado = new javax.swing.JComboBox();
        cmbServicio = new javax.swing.JComboBox();
        cmbSubGrupo = new javax.swing.JComboBox();
        jPanel7 = new javax.swing.JPanel();
        lbNit10 = new javax.swing.JLabel();
        dtInicio = new datechooser.beans.DateChooserCombo();
        dtFinal = new datechooser.beans.DateChooserCombo();
        lbNit13 = new javax.swing.JLabel();
        btnImpresa = new javax.swing.JRadioButton();
        lbNit14 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        btnExcel = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();

        setTitle("Factura");

        pnlFormulario.setBackground(new java.awt.Color(255, 255, 255));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        lbNit1.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit1.setText("CC/Nit:");

        lbNit12.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit12.setText("Empleado:");

        txtCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtClienteKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtClienteKeyReleased(evt);
            }
        });

        txtProveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtProveedorKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtProveedorKeyReleased(evt);
            }
        });

        lbBodega.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbBodega.setText("Servicio:");

        txtCodigo.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodigoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoKeyReleased(evt);
            }
        });

        lbBodega1.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbBodega1.setText("Estado:");

        cmbEstados.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "PENDIENTE", "PAGADO" }));

        lbBodega2.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbBodega2.setText("Subgrupo:");

        txtCodigo1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtCodigo1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodigo1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigo1KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCliente, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtProveedor)
                    .addComponent(lbBodega, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCodigo)
                    .addComponent(lbBodega1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbNit1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbNit12, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(rbtEstado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbEstados, 0, 209, Short.MAX_VALUE))
                    .addComponent(lbBodega2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCodigo1))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(lbNit1)
                .addGap(4, 4, 4)
                .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(lbNit12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(lbBodega)
                .addGap(0, 0, 0)
                .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(lbBodega2)
                .addGap(0, 0, 0)
                .addComponent(txtCodigo1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(lbBodega1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmbEstados)
                    .addComponent(rbtEstado, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        btnAggProv.setBackground(new java.awt.Color(204, 204, 204));
        btnAggProv.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnAggProv.setForeground(new java.awt.Color(255, 255, 255));
        btnAggProv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/agregar.png"))); // NOI18N
        btnAggProv.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAggProv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAggProvActionPerformed(evt);
            }
        });

        btnRemProv.setBackground(new java.awt.Color(204, 204, 204));
        btnRemProv.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnRemProv.setForeground(new java.awt.Color(153, 0, 0));
        btnRemProv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/borrar2.png"))); // NOI18N
        btnRemProv.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRemProv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemProvActionPerformed(evt);
            }
        });

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

        btnAggProd.setBackground(new java.awt.Color(204, 204, 204));
        btnAggProd.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnAggProd.setForeground(new java.awt.Color(255, 255, 255));
        btnAggProd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/agregar.png"))); // NOI18N
        btnAggProd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAggProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAggProdActionPerformed(evt);
            }
        });

        btnRemProd.setBackground(new java.awt.Color(204, 204, 204));
        btnRemProd.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnRemProd.setForeground(new java.awt.Color(153, 0, 0));
        btnRemProd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/borrar2.png"))); // NOI18N
        btnRemProd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRemProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemProdActionPerformed(evt);
            }
        });

        btnAggProd1.setBackground(new java.awt.Color(204, 204, 204));
        btnAggProd1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnAggProd1.setForeground(new java.awt.Color(255, 255, 255));
        btnAggProd1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/agregar.png"))); // NOI18N
        btnAggProd1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAggProd1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAggProd1ActionPerformed(evt);
            }
        });

        btnRemProd1.setBackground(new java.awt.Color(204, 204, 204));
        btnRemProd1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnRemProd1.setForeground(new java.awt.Color(153, 0, 0));
        btnRemProd1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/borrar2.png"))); // NOI18N
        btnRemProd1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRemProd1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemProd1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(btnAggCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRemCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(btnAggProv)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRemProv))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(btnAggProd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRemProd))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(btnAggProd1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRemProd1)))
                .addContainerGap(11, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAggCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRemCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(37, 37, 37)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRemProv)
                    .addComponent(btnAggProv, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRemProd)
                    .addComponent(btnAggProd, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRemProd1)
                    .addComponent(btnAggProd1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

        cmbClientes.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        cmbEmpleado.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        cmbServicio.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        cmbSubGrupo.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbClientes, 0, 214, Short.MAX_VALUE)
                    .addComponent(cmbEmpleado, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbServicio, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbSubGrupo, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(cmbClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(cmbEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(cmbServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(cmbSubGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        lbNit10.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lbNit10.setText("Fecha final");

        dtInicio.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 12));

        dtFinal.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 12));

        lbNit13.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lbNit13.setText("Versión:");

        btnImpresa.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(btnImpresa);
        btnImpresa.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnImpresa.setSelected(true);
        btnImpresa.setText("En pantalla");
        btnImpresa.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnImpresa.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        lbNit14.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lbNit14.setText("Fecha inicial:");

        btnExcel.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(btnExcel);
        btnExcel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnExcel.setText("Hoja de calculo");
        btnExcel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnExcel.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbNit13)
                    .addComponent(lbNit14, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnImpresa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dtInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(35, 35, 35)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(lbNit10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dtFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnExcel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(dtInicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnImpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnExcel)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lbNit10, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                                .addComponent(dtFinal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(lbNit14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                        .addGap(6, 6, 6)
                        .addComponent(lbNit13)))
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jButton4.setBackground(new java.awt.Color(46, 204, 113));
        jButton4.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jButton4.setText("Generar");
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlFormularioLayout = new javax.swing.GroupLayout(pnlFormulario);
        pnlFormulario.setLayout(pnlFormularioLayout);
        pnlFormularioLayout.setHorizontalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlFormularioLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlFormularioLayout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlFormularioLayout.setVerticalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
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

    private void txtClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClienteKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtClienteKeyPressed

    private void txtClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClienteKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cargarCliente(txtCliente.getText());
        }
    }//GEN-LAST:event_txtClienteKeyReleased

    private void txtProveedorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProveedorKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProveedorKeyPressed

    private void txtProveedorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProveedorKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cargarProveedor(txtProveedor.getText());
        }
    }//GEN-LAST:event_txtProveedorKeyReleased

    private void txtCodigoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoKeyPressed

    private void txtCodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cargarProducto(txtCodigo.getText());
        }
    }//GEN-LAST:event_txtCodigoKeyReleased

    private void btnAggProvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAggProvActionPerformed
        if (yaEsta(cmbEmpleado, txtProveedor.getText())) {
            metodos.msgAdvertenciaAjustado(null, "Ya cargo este empleado");
        } else {
            cmbEmpleado.addItem(txtProveedor.getText());
            cmbEmpleado.setSelectedItem(txtProveedor.getText());
        }
        txtProveedor.setText("");
    }//GEN-LAST:event_btnAggProvActionPerformed

    private void btnRemProvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemProvActionPerformed
        cmbEmpleado.removeItem(cmbEmpleado.getSelectedItem());
    }//GEN-LAST:event_btnRemProvActionPerformed

    private void btnAggClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAggClienteActionPerformed
        if (yaEsta(cmbClientes, txtCliente.getText())) {
            metodos.msgAdvertenciaAjustado(null, "Ya cargo este cliente");
        } else {
            cmbClientes.addItem(txtCliente.getText());
            cmbClientes.setSelectedItem(txtCliente.getText());
        }

        txtCliente.setText("");
    }//GEN-LAST:event_btnAggClienteActionPerformed

    private void btnRemClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemClienteActionPerformed
        cmbClientes.removeItem(cmbClientes.getSelectedItem());
    }//GEN-LAST:event_btnRemClienteActionPerformed

    private void btnAggProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAggProdActionPerformed
        if (yaEsta(cmbServicio, txtCodigo.getText())) {
            metodos.msgAdvertenciaAjustado(null, "Ya cargo este producto");
        } else {
            cmbServicio.addItem(txtCodigo.getText());
            cmbServicio.setSelectedItem(txtCodigo.getText());
        }

        txtCodigo.setText("");
    }//GEN-LAST:event_btnAggProdActionPerformed

    private void btnRemProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemProdActionPerformed
        cmbServicio.removeItem(cmbServicio.getSelectedItem());
    }//GEN-LAST:event_btnRemProdActionPerformed

    private void txtCodigo1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigo1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigo1KeyPressed

    private void txtCodigo1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigo1KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cargarGrupo(txtCodigo1.getText());
        }
    }//GEN-LAST:event_txtCodigo1KeyReleased

    private void btnAggProd1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAggProd1ActionPerformed
        if (yaEsta(cmbSubGrupo, txtCodigo1.getText())) {
            metodos.msgAdvertencia(null, "Ya cargo este producto");
        } else {
            cmbSubGrupo.addItem(txtCodigo1.getText());
            cmbSubGrupo.setSelectedItem(txtCodigo1.getText());
        }

        txtCodigo1.setText("");
    }//GEN-LAST:event_btnAggProd1ActionPerformed

    private void btnRemProd1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemProd1ActionPerformed
        cmbSubGrupo.removeItem(cmbSubGrupo.getSelectedItem());
    }//GEN-LAST:event_btnRemProd1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if (metodos.msgPregunta(null, "¿Esta seguro?") == 0) {
            String sql = "where";

            if (cmbClientes.getItemCount() > 0) {
                sql = sql + " (";

                for (int i = 0; i < cmbClientes.getItemCount(); i++) {
                    sql = sql + " cliente = '" + cmbClientes.getItemAt(i) + "' OR";
                }
                sql = sql.substring(0, sql.length() - 2) + ") AND";
            }

            if (cmbEmpleado.getItemCount() > 0) {
                sql = sql + " (";

                for (int i = 0; i < cmbEmpleado.getItemCount(); i++) {
                    sql = sql + " empleado = '" + cmbEmpleado.getItemAt(i) + "' OR";
                }
                sql = sql.substring(0, sql.length() - 2) + ") AND";
            }

            if (cmbServicio.getItemCount() > 0) {
                sql = sql + " (";

                for (int i = 0; i < cmbServicio.getItemCount(); i++) {
                    sql = sql + " codServicio = '" + cmbServicio.getItemAt(i) + "' OR";
                }
                sql = sql.substring(0, sql.length() - 2) + ") AND";
            }

            if (cmbSubGrupo.getItemCount() > 0) {
                sql = sql + " (";

                for (int i = 0; i < cmbSubGrupo.getItemCount(); i++) {
                    sql = sql + " Sub_grupo = '" + cmbSubGrupo.getItemAt(i) + "' OR";
                }
                sql = sql.substring(0, sql.length() - 2) + ") AND";
            }

            if (rbtEstado.isSelected()) {
                sql = sql + " liquidado = '" + cmbEstados.getSelectedItem() + "' AND";
            }

            String ini = metodos.desdeDate(dtInicio.getCurrent()), fin = metodos.desdeDate(dtFinal.getCurrent());

            sql = sql + " (((fechaRealizado)>='" + ini + "') And ((fechaRealizado)<='" + fin + "')) ";

            System.out.println(sql);
            String enca = "";

            dtInicio.setFormat(0);
            dtFinal.setFormat(0);

            if (dtInicio.getText().equals(dtFinal.getText())) {
                enca = "Ventas del " + ini;
            } else {
                enca = "Ventas entre el " + ini + " y el " + fin;
            }

            dtInicio.setFormat(2);
            dtFinal.setFormat(2);

            String tipo = "";

            if (btnExcel.isSelected()) {
                tipo = "xls";
                instancias.setRutaAguardar(metodos.obtenerRuta(this, "Reporte de ventas " + metodosGenerales.dia() + " de " + metodosGenerales.mesEnPalabra() + " del " + metodosGenerales.anho()));
            }

            instancias.getReporte().verRepLavado(sql, enca, tipo);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    public void cargarGrupo(String nit) {

        System.out.println("nit" + nit);
        if (nit.equals("")) {
            ventanaSubGrupos(nit);
        } else {
            String nombre = instancias.getSql().getDatosSubGrupo(nit);

            if (nombre != null) {
                btnAggProd1ActionPerformed(null);
                return;
            } else {
                ventanaSubGrupos(nit);
            }
        }
    }

    public void ventanaSubGrupos(String codigo) {
        buscSubGrupos buscar = new buscSubGrupos(null, rootPaneCheckingEnabled);
        buscar.setLocationRelativeTo(null);
        instancias.setBuscSubGrupos(buscar);
        instancias.setValor(txtCodigo1.getText());
        instancias.setCampoActual(txtCodigo1);
        txtCodigo1.requestFocus();
        buscar.noEncontrado(codigo.replace("'", "//"));
        buscar.show();
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
        buscClientes buscar = new buscClientes(instancias.getMenu(), rootPaneCheckingEnabled, false, null, "");
        buscar.setLocationRelativeTo(null);
        instancias.setBusClientes(buscar);
        instancias.setCampoActual(txtCliente);
        txtCliente.requestFocus();
        buscar.noEncontrado(nit);
        buscar.show();
    }

    public void cargarProveedor(String nit) {
        ModeloContacto nodo = instancias.getSql().getDatosTercero(nit);

        if (nodo.getId() != null) {
            txtProveedor.setText(nodo.getIdSistema());
            btnAggProvActionPerformed(null);
            return;
        }
        ventanaProveedor(nit);
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
            btnAggProdActionPerformed(null);
            return;
        }
        ventanaProducto(codigo);
    }

    public void ventanaProveedor(String nit) {
        buscEmpleados buscar = new buscEmpleados(instancias.getMenu(), rootPaneCheckingEnabled);
        buscar.setLocationRelativeTo(null);
        instancias.setBuscEmpleados(buscar);
        instancias.setCampoActual(txtProveedor);
        txtProveedor.requestFocus();
        buscar.noEncontrado(nit);
        buscar.show();
    }

    public void ventanaProducto(String nit) {
        VistaBuscadorProductos buscar = new VistaBuscadorProductos(null, true, false, "lavado", "productos1");
        buscar.setLocationRelativeTo(null);
        instancias.setBusProductos(buscar);
        instancias.setCampoActual(txtCodigo);
        txtCodigo.requestFocus();
//        buscar.setInstancia(instancias);
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

    public void setInstancias(Instancias instancias) {
        this.instancias = instancias;
    }

    public void reporte(String nombre) {
        lbNit10.setText(nombre);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAggCliente;
    private javax.swing.JButton btnAggProd;
    private javax.swing.JButton btnAggProd1;
    private javax.swing.JButton btnAggProv;
    private javax.swing.JRadioButton btnExcel;
    private javax.swing.JRadioButton btnImpresa;
    private javax.swing.JButton btnRemCliente;
    private javax.swing.JButton btnRemProd;
    private javax.swing.JButton btnRemProd1;
    private javax.swing.JButton btnRemProv;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cmbClientes;
    private javax.swing.JComboBox cmbEmpleado;
    private javax.swing.JComboBox cmbEstados;
    private javax.swing.JComboBox cmbServicio;
    private javax.swing.JComboBox cmbSubGrupo;
    private datechooser.beans.DateChooserCombo dtFinal;
    private datechooser.beans.DateChooserCombo dtInicio;
    private javax.swing.JButton jButton4;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbBodega;
    private javax.swing.JLabel lbBodega1;
    private javax.swing.JLabel lbBodega2;
    private javax.swing.JLabel lbNit1;
    private javax.swing.JLabel lbNit10;
    private javax.swing.JLabel lbNit12;
    private javax.swing.JLabel lbNit13;
    private javax.swing.JLabel lbNit14;
    private javax.swing.JPanel pnlFormulario;
    private javax.swing.JRadioButton rbtEstado;
    private javax.swing.JScrollPane scrFormulario;
    private javax.swing.JTextField txtCliente;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtCodigo1;
    private javax.swing.JTextField txtProveedor;
    // End of variables declaration//GEN-END:variables
}
