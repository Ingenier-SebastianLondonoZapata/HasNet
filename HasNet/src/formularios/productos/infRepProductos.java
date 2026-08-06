package formularios.productos;

import Vista.Productos.VistaBuscadorProductos;
import clases.Instancias;
import clases.metodosGenerales;
import clases.productos.ndGrupo;
import clases.productos.ndProducto;
import Modelo.Terceros.ModeloContacto;
import formularios.terceros.buscClientes;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;

public class infRepProductos extends javax.swing.JPanel {

    metodosGenerales metodos = new metodosGenerales();
    private Instancias instancias;
    private Dimension dimBarra = null;

    public infRepProductos() {
        initComponents();

        buttonGroup1.add(jRadioButton1);
        buttonGroup1.add(jRadioButton2);
        buttonGroup1.add(jRadioButton3);
        buttonGroup1.add(jRadioButton4);
        buttonGroup1.add(jRadioButton5);
        instancias = Instancias.getInstancias();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoTipo = new javax.swing.ButtonGroup();
        buttonGroup1 = new javax.swing.ButtonGroup();
        scrFormulario = new javax.swing.JScrollPane();
        pnlFormulario = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lbNit = new javax.swing.JLabel();
        lbNit8 = new javax.swing.JLabel();
        lbNit14 = new javax.swing.JLabel();
        txtGrupo = new javax.swing.JTextField();
        lbTerminal = new javax.swing.JLabel();
        txtProveedor = new javax.swing.JTextField();
        txtCodigo = new javax.swing.JTextField();
        cmbTipo = new javax.swing.JComboBox();
        jPanel9 = new javax.swing.JPanel();
        btnAggProv = new javax.swing.JButton();
        btnRemProv = new javax.swing.JButton();
        btnAggGrupo = new javax.swing.JButton();
        btnRemGrupo = new javax.swing.JButton();
        btnAggProd = new javax.swing.JButton();
        btnRemProd = new javax.swing.JButton();
        btnAggProd1 = new javax.swing.JButton();
        btnRemProd1 = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        cmbGrupo = new javax.swing.JComboBox();
        cmbProveedor = new javax.swing.JComboBox();
        cmbProducto = new javax.swing.JComboBox();
        cmbTipo2 = new javax.swing.JComboBox();
        jPanel1 = new javax.swing.JPanel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jRadioButton5 = new javax.swing.JRadioButton();
        lbNit15 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lbNit16 = new javax.swing.JLabel();
        btnImpresa = new javax.swing.JRadioButton();
        btnExcel = new javax.swing.JRadioButton();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();

        pnlFormulario.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        lbNit.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit.setText("Grupo:");

        lbNit8.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit8.setText("Proveedor:");

        lbNit14.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit14.setText("Estado:");

        txtGrupo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtGrupoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtGrupoKeyReleased(evt);
            }
        });

        lbTerminal.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbTerminal.setText("Producto:");

        txtProveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtProveedorKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtProveedorKeyReleased(evt);
            }
        });

        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodigoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoKeyReleased(evt);
            }
        });

        cmbTipo.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        cmbTipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ACTIVOS", "INACTIVOS" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtGrupo, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbNit, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbNit8, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbNit14, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbTerminal, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 143, Short.MAX_VALUE))
                    .addComponent(txtProveedor)
                    .addComponent(txtCodigo)
                    .addComponent(cmbTipo, 0, 236, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(lbNit)
                .addGap(0, 0, 0)
                .addComponent(txtGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(lbNit8)
                .addGap(0, 0, 0)
                .addComponent(txtProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(lbTerminal)
                .addGap(0, 0, 0)
                .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(lbNit14)
                .addGap(0, 0, 0)
                .addComponent(cmbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

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

        btnAggGrupo.setBackground(new java.awt.Color(204, 204, 204));
        btnAggGrupo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnAggGrupo.setForeground(new java.awt.Color(255, 255, 255));
        btnAggGrupo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/agregar.png"))); // NOI18N
        btnAggGrupo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAggGrupo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAggGrupoActionPerformed(evt);
            }
        });

        btnRemGrupo.setBackground(new java.awt.Color(204, 204, 204));
        btnRemGrupo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnRemGrupo.setForeground(new java.awt.Color(153, 0, 0));
        btnRemGrupo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/borrar2.png"))); // NOI18N
        btnRemGrupo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRemGrupo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemGrupoActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(btnAggGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRemGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(btnAggProv)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRemProv))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(btnAggProd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRemProd))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(btnAggProd1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRemProd1)))
                .addContainerGap(11, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAggGrupo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRemGrupo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(37, 37, 37)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRemProv)
                    .addComponent(btnAggProv, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRemProd)
                    .addComponent(btnAggProd, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRemProd1)
                    .addComponent(btnAggProd1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        cmbTipo2.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbGrupo, 0, 214, Short.MAX_VALUE)
                    .addComponent(cmbProveedor, 0, 214, Short.MAX_VALUE)
                    .addComponent(cmbProducto, 0, 214, Short.MAX_VALUE)
                    .addComponent(cmbTipo2, 0, 214, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmbGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(cmbProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(cmbProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(cmbTipo2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jRadioButton1.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jRadioButton1.setText("Codigo");

        jRadioButton2.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jRadioButton2.setSelected(true);
        jRadioButton2.setText("Descripcion");

        jRadioButton3.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(jRadioButton3);
        jRadioButton3.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jRadioButton3.setText("Grupo");
        jRadioButton3.setName("GrupoDesc"); // NOI18N

        jRadioButton4.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(jRadioButton4);
        jRadioButton4.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jRadioButton4.setText("Subgrupo");
        jRadioButton4.setName("SubGrupoDesc"); // NOI18N

        jRadioButton5.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(jRadioButton5);
        jRadioButton5.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jRadioButton5.setText("Proveedor");
        jRadioButton5.setName("nombreProveedor"); // NOI18N

        lbNit15.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit15.setText("Ordenar por:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(lbNit15, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButton1)
                .addGap(18, 18, 18)
                .addComponent(jRadioButton2)
                .addGap(18, 18, 18)
                .addComponent(jRadioButton3)
                .addGap(18, 18, 18)
                .addComponent(jRadioButton4)
                .addGap(18, 18, 18)
                .addComponent(jRadioButton5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2)
                    .addComponent(jRadioButton3)
                    .addComponent(jRadioButton4)
                    .addComponent(jRadioButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbNit15, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        lbNit16.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lbNit16.setText("Versión:");

        btnImpresa.setBackground(new java.awt.Color(255, 255, 255));
        grupoTipo.add(btnImpresa);
        btnImpresa.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnImpresa.setSelected(true);
        btnImpresa.setText("En Pantalla");
        btnImpresa.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnImpresa.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        btnExcel.setBackground(new java.awt.Color(255, 255, 255));
        grupoTipo.add(btnExcel);
        btnExcel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnExcel.setText("Hoja de calculo");
        btnExcel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnExcel.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbNit16, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(btnImpresa)
                .addGap(18, 18, 18)
                .addComponent(btnExcel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jSeparator1)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnImpresa)
                    .addComponent(btnExcel)
                    .addComponent(lbNit16, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jButton2.setBackground(new java.awt.Color(46, 204, 113));
        jButton2.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        jButton2.setText("Generar");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlFormularioLayout = new javax.swing.GroupLayout(pnlFormulario);
        pnlFormulario.setLayout(pnlFormularioLayout);
        pnlFormularioLayout.setHorizontalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnlFormularioLayout.createSequentialGroup()
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlFormularioLayout.setVerticalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        scrFormulario.setViewportView(pnlFormulario);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(scrFormulario)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrFormulario)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtGrupoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGrupoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGrupoKeyPressed

    private void txtGrupoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGrupoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cargarGrupo(txtGrupo.getText());
        }
    }//GEN-LAST:event_txtGrupoKeyReleased

    private void btnAggProvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAggProvActionPerformed
        if (txtProveedor.getText().equals("")) {
            metodos.msgError(null, "Cargue primero un proveedor");
        } else {

            if (yaEsta(cmbProveedor, txtProveedor.getText())) {
                metodos.msgAdvertencia(null, "Ya cargo este proveedor");
            } else {
                cmbProveedor.addItem(txtProveedor.getText());
                cmbProveedor.setSelectedItem(txtProveedor.getText());
            }

            txtProveedor.setText("");
        }
    }//GEN-LAST:event_btnAggProvActionPerformed

    private void btnRemProvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemProvActionPerformed
        cmbProveedor.removeItem(cmbProveedor.getSelectedItem());
    }//GEN-LAST:event_btnRemProvActionPerformed

    private void btnAggGrupoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAggGrupoActionPerformed
        if (txtGrupo.getText().equals("")) {
            metodos.msgError(null, "Cargue primero un grupo");
        } else {
            if (yaEsta(cmbGrupo, txtGrupo.getText())) {
                metodos.msgAdvertencia(null, "Ya cargo este producto");
            } else {
                cmbGrupo.addItem(txtGrupo.getText());
                cmbGrupo.setSelectedItem(txtGrupo.getText());
            }

            txtGrupo.setText("");
        }
    }//GEN-LAST:event_btnAggGrupoActionPerformed

    private void btnRemGrupoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemGrupoActionPerformed
        cmbGrupo.removeItem(cmbGrupo.getSelectedItem());
    }//GEN-LAST:event_btnRemGrupoActionPerformed

    private void btnAggProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAggProdActionPerformed
        if (txtCodigo.getText().equals("")) {
            metodos.msgError(null, "Cargue primero un producto");
        } else {
            ndProducto nodo = null;

            String CodigoProd = "", codigo = txtCodigo.getText();
            Object[][] listado = instancias.getSql().getCodigosRelacionados(codigo, " where codigo");
            if (listado.length > 0) {
                codigo = listado[0][0].toString();
            }

            nodo = instancias.getSql().getDatosProducto(codigo, "bdProductos");
            if (nodo.getIdSistema() != null) {
                CodigoProd = nodo.getIdSistema();
            }

            if (CodigoProd.equals("")) {
                ventanaProducto(codigo);
                return;
            } else {
                txtCodigo.setText(nodo.getIdSistema());
                if (yaEsta(cmbProducto, txtCodigo.getText())) {
                    metodos.msgAdvertencia(null, "Ya cargo este producto");
                } else {
                    cmbProducto.addItem(txtCodigo.getText());
                    cmbProducto.setSelectedItem(txtCodigo.getText());
                }
            }

            txtCodigo.setText("");
        }
    }//GEN-LAST:event_btnAggProdActionPerformed

    private void btnRemProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemProdActionPerformed
        cmbProducto.removeItem(cmbProducto.getSelectedItem());
    }//GEN-LAST:event_btnRemProdActionPerformed

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

    private void btnAggProd1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAggProd1ActionPerformed
        for (int i = 0; i < cmbTipo2.getItemCount(); i++) {
            if (cmbTipo2.getItemAt(i).equals(cmbTipo.getSelectedItem())) {
                metodos.msgError(null, "Ya ha ingresado este estado");
                return;
            }
        }
        cmbTipo2.addItem(cmbTipo.getSelectedItem());
    }//GEN-LAST:event_btnAggProd1ActionPerformed

    private void btnRemProd1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemProd1ActionPerformed
        if (cmbTipo2.getItemCount() == 0) {
            metodos.msgError(null, "Ningún estado seleccionado");
            return;
        }
        cmbTipo2.removeItem(cmbTipo2.getSelectedItem());
    }//GEN-LAST:event_btnRemProd1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (metodos.msgPregunta(null, "¿Esta seguro?") == 0) {
            String sql = "where";

            if (cmbGrupo.getItemCount() > 0) {
                sql = sql + " (";

                for (int i = 0; i < cmbGrupo.getItemCount(); i++) {
                    sql = sql + " Grupo='" + cmbGrupo.getItemAt(i) + "' OR";
                }

                sql = sql.substring(0, sql.length() - 2) + ") AND";
            }

            if (cmbProveedor.getItemCount() > 0) {
                sql = sql + " (";

                for (int i = 0; i < cmbProveedor.getItemCount(); i++) {
                    sql = sql + " Proveedor='" + cmbProveedor.getItemAt(i) + "' OR";
                }

                sql = sql.substring(0, sql.length() - 2) + ") AND";
            }

            if (cmbProducto.getItemCount() > 0) {
                sql = sql + " (";

                for (int i = 0; i < cmbProducto.getItemCount(); i++) {
                    sql = sql + " idSistema ='" + cmbProducto.getItemAt(i) + "' OR";
                }

                sql = sql.substring(0, sql.length() - 2) + ") AND";
            }

            if (cmbTipo2.getItemCount() > 0) {
                sql = sql + " (";

                for (int i = 0; i < cmbTipo2.getItemCount(); i++) {
                    Boolean estado;
                    if (cmbTipo2.getItemAt(i).equals("ACTIVOS")) {
                        estado = false;
                    } else {
                        estado = true;
                    }

                    sql = sql + " activo = " + estado + " OR";
                }
                sql = sql.substring(0, sql.length() - 2) + ") AND";
            }

            sql = sql + " ((codigo)<>'') ORDER BY " + getOrden() + " ASC;";
            System.out.println("sql " + sql);

            String enca = "";
            String tipo = "";

            if (btnExcel.isSelected()) {
                tipo = "xls";
                instancias.setRutaAguardar(metodos.obtenerRuta(null, "Reporte de productos " + metodosGenerales.dia() + " de " + metodosGenerales.mesEnPalabra() + " del " + metodosGenerales.anho()));
            }

            if (instancias.getRegimen().equals("SinIva")) {
                instancias.getReporte().ver_RepProductos(sql, enca, tipo);
            } else {
                if (instancias.isPvpConIva()) {
                    instancias.getReporte().ver_RepProductosComunSumandoIva(sql, enca, tipo);
                } else {
                    instancias.getReporte().ver_RepProductosComunDiviendoIva(sql, enca, tipo);
                }
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    public String getOrden() {
        if (jRadioButton1.isSelected()) {
            return jRadioButton1.getText();
        }
        if (jRadioButton2.isSelected()) {
            return jRadioButton2.getText();
        }
        if (jRadioButton3.isSelected()) {
            return jRadioButton3.getName();
        }
        if (jRadioButton4.isSelected()) {
            return jRadioButton4.getName();
        }
        if (jRadioButton5.isSelected()) {
            return jRadioButton5.getName();
        }
        return "";
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

    public void cargarProveedor(String nit) {
        ModeloContacto nodo = instancias.getSql().getDatosTercero(nit);
        if (nodo.getId() != null) {
            txtProveedor.setText(nodo.getIdSistema());
            btnAggProvActionPerformed(null);
            return;
        }
        ventanaProveedor(nit);
    }

    public void ventanaProveedor(String nit) {
        buscClientes buscar = new buscClientes(instancias.getMenu(), false, true, null, "");
        buscar.setLocationRelativeTo(null);
        buscar.setTipoBuscador("");
        instancias.setBusClientes(buscar);
        instancias.setCampoActual(txtProveedor);
        txtProveedor.requestFocus();
        buscar.noEncontrado(nit);
        buscar.show();
    }
    
    public void cargarGrupo(String nit) {
        ndGrupo nodo = instancias.getSql().getDatosGrupo2(nit);

        if (nodo.getCodigo() != null) {
            btnAggGrupoActionPerformed(null);
            return;
        }
        ventanaGrupo(nit);
    }

    public void ventanaGrupo(String nit) {
        buscGrupos buscar = new buscGrupos(instancias.getMenu(), false, "");
        buscar.setLocationRelativeTo(null);
        instancias.setBuscGrupos(buscar);
        instancias.setCampoActual(txtGrupo);
        txtGrupo.requestFocus();
        buscar.noEncontrado(nit);
        buscar.show();
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
            txtCodigo.setText(nodo.getIdSistema());
            btnAggProdActionPerformed(null);
            return;
        }

        ventanaProducto(codigo);
    }

    public void ventanaProducto(String nit) {
        VistaBuscadorProductos buscar = new VistaBuscadorProductos(instancias.getMenu(), false, false, "", "productos1");
        buscar.setLocationRelativeTo(null);
        instancias.setBusProductos(buscar);
        instancias.setCampoActual(txtCodigo);
        txtCodigo.requestFocus();
//        buscar.setInstancia(instancias);
        buscar.noEncontrado(nit);
        buscar.show();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAggGrupo;
    private javax.swing.JButton btnAggProd;
    private javax.swing.JButton btnAggProd1;
    private javax.swing.JButton btnAggProv;
    private javax.swing.JRadioButton btnExcel;
    private javax.swing.JRadioButton btnImpresa;
    private javax.swing.JButton btnRemGrupo;
    private javax.swing.JButton btnRemProd;
    private javax.swing.JButton btnRemProd1;
    private javax.swing.JButton btnRemProv;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cmbGrupo;
    private javax.swing.JComboBox cmbProducto;
    private javax.swing.JComboBox cmbProveedor;
    private javax.swing.JComboBox cmbTipo;
    private javax.swing.JComboBox cmbTipo2;
    private javax.swing.ButtonGroup grupoTipo;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbNit;
    private javax.swing.JLabel lbNit14;
    private javax.swing.JLabel lbNit15;
    private javax.swing.JLabel lbNit16;
    private javax.swing.JLabel lbNit8;
    private javax.swing.JLabel lbTerminal;
    private javax.swing.JPanel pnlFormulario;
    private javax.swing.JScrollPane scrFormulario;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtGrupo;
    private javax.swing.JTextField txtProveedor;
    // End of variables declaration//GEN-END:variables
}
