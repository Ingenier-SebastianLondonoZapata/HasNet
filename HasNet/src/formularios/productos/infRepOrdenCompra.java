package formularios.productos;

import Vista.Productos.VistaBuscadorProductos;
import dao.InicioSesion.DaoInicioSesion;
import Modelo.InicioSesion.Terminal;
import clases.Instancias;
import clases.metodosGenerales;
import clases.productos.ndGrupo;
import clases.productos.ndProducto;
import Modelo.Terceros.ModeloContacto;
import formularios.terceros.buscClientes;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.JComboBox;

public class infRepOrdenCompra extends javax.swing.JPanel {

    private final DaoInicioSesion daoInicioSesion = new DaoInicioSesion();
    metodosGenerales metodos = new metodosGenerales();
    private Instancias instancias;
    private Dimension dimBarra = null;

    public infRepOrdenCompra() {
        initComponents();

        dtInicio.setFormat(2);
        dtFinal.setFormat(2);

        instancias = Instancias.getInstancias();

        List<Terminal> terminales = daoInicioSesion.obtenerTerminales();
        for (Terminal terminal : terminales) {
            cmbTerminal.addItem(terminal.getTerminal());
        }

        if (!instancias.getUsuario().equals("ADMIN")) {
            lbTerminal.setVisible(false);
            cmbTerminal.setVisible(false);
            cmbTerminal2.setVisible(false);
            btnAggTerminal.setVisible(false);
            btnRemTerminal.setVisible(false);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup1 = new javax.swing.ButtonGroup();
        scrFormulario = new javax.swing.JScrollPane();
        pnlFormulario = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        rdNoAnulados = new javax.swing.JRadioButton();
        lbNit15 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lbNit1 = new javax.swing.JLabel();
        lbNit12 = new javax.swing.JLabel();
        lbTerminal = new javax.swing.JLabel();
        txtProveedor = new javax.swing.JTextField();
        lbProducto = new javax.swing.JLabel();
        txtGrupo = new javax.swing.JTextField();
        txtCodigo = new javax.swing.JTextField();
        cmbTerminal = new javax.swing.JComboBox();
        jPanel10 = new javax.swing.JPanel();
        btnAggCliente = new javax.swing.JButton();
        btnRemCliente16 = new javax.swing.JButton();
        btnAggProv = new javax.swing.JButton();
        btnRemProv = new javax.swing.JButton();
        btnAggProd = new javax.swing.JButton();
        btnRemProd = new javax.swing.JButton();
        btnAggTerminal = new javax.swing.JButton();
        btnRemTerminal = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        cmbProveedor = new javax.swing.JComboBox();
        cmbCientes = new javax.swing.JComboBox();
        cmbProducto = new javax.swing.JComboBox();
        cmbTerminal2 = new javax.swing.JComboBox();
        jPanel5 = new javax.swing.JPanel();
        lbNit8 = new javax.swing.JLabel();
        lbNit11 = new javax.swing.JLabel();
        btnImpresa = new javax.swing.JRadioButton();
        dtInicio = new datechooser.beans.DateChooserCombo();
        lbNit9 = new javax.swing.JLabel();
        dtFinal = new datechooser.beans.DateChooserCombo();
        chkExcel = new javax.swing.JRadioButton();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel3 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();

        pnlFormulario.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        buttonGroup1.add(rdNoAnulados);
        rdNoAnulados.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        rdNoAnulados.setSelected(true);
        rdNoAnulados.setText("Realizadas");

        lbNit15.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit15.setText("Estado:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbNit15, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdNoAnulados))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(lbNit15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdNoAnulados)
                .addGap(10, 10, 10))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        lbNit1.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit1.setText("Proveedor:");

        lbNit12.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit12.setText("Grupo");

        lbTerminal.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbTerminal.setText("Terminal:");

        txtProveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtProveedorKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtProveedorKeyReleased(evt);
            }
        });

        lbProducto.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbProducto.setText("Producto:");

        txtGrupo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtGrupoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtGrupoKeyReleased(evt);
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

        cmbTerminal.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtProveedor, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbNit12, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbTerminal, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbNit1))
                        .addGap(0, 143, Short.MAX_VALUE))
                    .addComponent(txtGrupo)
                    .addComponent(txtCodigo)
                    .addComponent(cmbTerminal, 0, 236, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(lbNit1)
                .addGap(0, 0, 0)
                .addComponent(txtProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(lbNit12)
                .addGap(0, 0, 0)
                .addComponent(txtGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(lbProducto)
                .addGap(0, 0, 0)
                .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(lbTerminal)
                .addGap(0, 0, 0)
                .addComponent(cmbTerminal, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

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

        btnRemCliente16.setBackground(new java.awt.Color(204, 204, 204));
        btnRemCliente16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnRemCliente16.setForeground(new java.awt.Color(153, 0, 0));
        btnRemCliente16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/borrar2.png"))); // NOI18N
        btnRemCliente16.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRemCliente16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemCliente16ActionPerformed(evt);
            }
        });

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

        btnAggTerminal.setBackground(new java.awt.Color(204, 204, 204));
        btnAggTerminal.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnAggTerminal.setForeground(new java.awt.Color(255, 255, 255));
        btnAggTerminal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/agregar.png"))); // NOI18N
        btnAggTerminal.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAggTerminal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAggTerminalActionPerformed(evt);
            }
        });

        btnRemTerminal.setBackground(new java.awt.Color(204, 204, 204));
        btnRemTerminal.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnRemTerminal.setForeground(new java.awt.Color(153, 0, 0));
        btnRemTerminal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/borrar2.png"))); // NOI18N
        btnRemTerminal.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRemTerminal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemTerminalActionPerformed(evt);
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
                        .addComponent(btnAggProv, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRemProv, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(btnAggCliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRemCliente16))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(btnAggProd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRemProd))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(btnAggTerminal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRemTerminal)))
                .addContainerGap(11, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAggProv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRemProv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(37, 37, 37)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRemCliente16)
                    .addComponent(btnAggCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRemProd)
                    .addComponent(btnAggProd, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRemTerminal)
                    .addComponent(btnAggTerminal, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

        cmbTerminal2.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbProveedor, 0, 214, Short.MAX_VALUE)
                    .addComponent(cmbCientes, 0, 214, Short.MAX_VALUE)
                    .addComponent(cmbProducto, 0, 214, Short.MAX_VALUE)
                    .addComponent(cmbTerminal2, 0, 214, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmbProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(cmbCientes, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(cmbProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(cmbTerminal2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        lbNit8.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lbNit8.setText("Fecha inicial:");

        lbNit11.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lbNit11.setText("Versión:");

        btnImpresa.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup2.add(btnImpresa);
        btnImpresa.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnImpresa.setSelected(true);
        btnImpresa.setText("En pantalla");
        btnImpresa.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnImpresa.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        dtInicio.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 12));

        lbNit9.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lbNit9.setText("Fecha final:");

        dtFinal.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 12));

        chkExcel.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup2.add(chkExcel);
        chkExcel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        chkExcel.setText("Hoja de calculo");
        chkExcel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        chkExcel.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbNit8)
                    .addComponent(lbNit11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dtInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnImpresa))
                .addGap(22, 22, 22)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(lbNit9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dtFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(chkExcel))
                .addContainerGap(152, Short.MAX_VALUE))
            .addComponent(jSeparator1)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbNit9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbNit8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dtInicio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dtFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnImpresa)
                    .addComponent(lbNit11)
                    .addComponent(chkExcel))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jButton4.setBackground(new java.awt.Color(46, 204, 113));
        jButton4.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jButton4.setText("GENERAR");
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlFormularioLayout = new javax.swing.GroupLayout(pnlFormulario);
        pnlFormulario.setLayout(pnlFormularioLayout);
        pnlFormularioLayout.setHorizontalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlFormularioLayout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlFormularioLayout.setVerticalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
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

    private void txtProveedorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProveedorKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProveedorKeyPressed

    private void txtProveedorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProveedorKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cargarProveedor(txtProveedor.getText());
        }
    }//GEN-LAST:event_txtProveedorKeyReleased

    private void txtGrupoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGrupoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGrupoKeyPressed

    private void txtGrupoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGrupoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cargarGrupo(txtGrupo.getText());
        } else {
            //            txtDescripcionGrupo.setText("");
        }
    }//GEN-LAST:event_txtGrupoKeyReleased

    private void txtCodigoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoKeyPressed

    private void txtCodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cargarProducto(txtCodigo.getText());
        }
    }//GEN-LAST:event_txtCodigoKeyReleased

    private void btnAggClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAggClienteActionPerformed
        if (txtGrupo.getText().equals("")) {
            metodos.msgError(null, "Cargue primero un grupo");
        } else {

            if (yaEsta(cmbCientes, txtGrupo.getText())) {
                metodos.msgAdvertencia(null, "Ya cargo este grupo");
            } else {
                cmbCientes.addItem(txtGrupo.getText());
                cmbCientes.setSelectedItem(txtGrupo.getText());
            }

            txtGrupo.setText("");
        }
    }//GEN-LAST:event_btnAggClienteActionPerformed

    private void btnRemCliente16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemCliente16ActionPerformed
        cmbCientes.removeItem(cmbCientes.getSelectedItem());
    }//GEN-LAST:event_btnRemCliente16ActionPerformed

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

    private void btnAggTerminalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAggTerminalActionPerformed
        for (int i = 0; i < cmbTerminal2.getItemCount(); i++) {
            if (cmbTerminal2.getItemAt(i).equals(cmbTerminal.getSelectedItem())) {
                metodos.msgError(null, "Ya has ingresado esta terminal");
                return;
            }
        }
        cmbTerminal2.addItem(cmbTerminal.getSelectedItem());
    }//GEN-LAST:event_btnAggTerminalActionPerformed

    private void btnRemTerminalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemTerminalActionPerformed
        if (cmbTerminal2.getItemCount() == 0) {
            metodos.msgError(null, "No hay ninguna terminal seleccioanda");
            return;
        }
        cmbTerminal2.removeItem(cmbTerminal2.getSelectedItem());
    }//GEN-LAST:event_btnRemTerminalActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        if (metodos.msgPregunta(null, "¿Esta seguro?") == 0) {
            String sql = "where";

            if (instancias.getUsuario().equals("ADMIN")) {
                if (cmbTerminal2.getItemCount() > 0) {
                    sql = sql + " (";
                    for (int i = 0; i < cmbTerminal2.getItemCount(); i++) {
                        sql = sql + " terminal='" + cmbTerminal2.getItemAt(i) + "' OR";
                    }
                    sql = sql.substring(0, sql.length() - 2) + ") AND";
                }
            } else {
                if (!instancias.isVisualizarTodasLasFacturas()) {
                    sql = sql + " terminal='" + instancias.getTerminal() + "' AND";
                }
            }

            if (cmbProveedor.getItemCount() > 0) {
                sql = sql + " (";
                for (int i = 0; i < cmbProveedor.getItemCount(); i++) {
                    sql = sql + " proveedor='" + cmbProveedor.getItemAt(i) + "' OR";
                }
                sql = sql.substring(0, sql.length() - 2) + ") AND";
            }

            if (cmbProducto.getItemCount() > 0) {
                sql = sql + " (";

                for (int i = 0; i < cmbProducto.getItemCount(); i++) {
                    sql = sql + " producto='" + cmbProducto.getItemAt(i) + "' OR";
                }

                sql = sql.substring(0, sql.length() - 2) + ") AND";
            }

            if (cmbCientes.getItemCount() > 0) {
                sql = sql + " (";

                for (int i = 0; i < cmbCientes.getItemCount(); i++) {
                    sql = sql + " Grupo='" + cmbCientes.getItemAt(i) + "' OR";
                }

                sql = sql.substring(0, sql.length() - 2) + ") AND";
            }

            String ini = metodos.desdeDate(dtInicio.getCurrent()), fin = metodos.desdeDate(dtFinal.getCurrent());

            sql = sql + " (((fechaFactura)>='" + ini + "') And ((fechaFactura)<='" + fin + "'))";

            String enca = "";

            dtInicio.setFormat(0);
            dtFinal.setFormat(0);

            if (rdNoAnulados.isSelected()) {
                sql = sql + " And (anulada=false)";
                if (dtInicio.getText().equals(dtFinal.getText())) {
                    enca = "Ordenes de compra del " + ini;
                } else {
                    enca = "Ordenes de compra entre el " + ini + " y el " + fin;
                }
            } else {
                sql = sql + " And (anulada=true)";
                if (dtInicio.getText().equals(dtFinal.getText())) {
                    enca = "Ordenes de compra ANULADOS del " + ini;
                } else {
                    enca = "Ordenes de compra ANULADOS entre el " + ini + " y el " + fin;
                }
            }

            dtInicio.setFormat(2);
            dtFinal.setFormat(2);

            String tipo = "";

            if (chkExcel.isSelected()) {
                tipo = "xls";
                instancias.setRutaAguardar(metodos.obtenerRuta(null, "Reporte ordenes de compra " + metodosGenerales.dia() + " de " + metodosGenerales.mesEnPalabra() + " del " + metodosGenerales.anho()));
            }
            System.out.println("Consulta " + sql);
            instancias.getReporte().ver_RepOrdenCompra(sql, enca, tipo);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    public void cargarProveedor(String nit) {
        ModeloContacto nodo = instancias.getSql().getDatosTercero(nit);

        if (nodo.getId() != null) {
            txtProveedor.setText(nodo.getIdSistema());
            btnAggProvActionPerformed(null);
            return;
        }
        ventanaTerceros(nit);
    }

    public void cargarProducto(String nit) {

        if (nit.equals("")) {
            ventanaProducto(nit);
            return;
        }

        ndProducto nodo = instancias.getSql().getDatosProducto(nit, "bdProductos");

        if (nodo.getCodigo() != null) {
            txtCodigo.setText(nodo.getIdSistema());
            btnAggProdActionPerformed(null);
            return;
        }
        ventanaProducto(nit);
    }

    public void ventanaTerceros(String nit) {
        buscClientes buscar = new buscClientes(instancias.getMenu(), true, false, null, "");
        buscar.setOpc("ingreso");
        buscar.setLocationRelativeTo(null);
        instancias.setBusClientes(buscar);
        instancias.setCampoActual(txtProveedor);
        txtProveedor.requestFocus();
        buscar.noEncontrado(nit);
        buscar.show();
    }

    public void ventanaProducto(String nit) {
        VistaBuscadorProductos buscar = new VistaBuscadorProductos(instancias.getMenu(), true, false, "");
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

    public void cargarGrupo(String nit) {
        ndGrupo nodo = instancias.getSql().getDatosGrupo2(nit);

        if (nodo.getCodigo() != null) {
//            txtDescripcionGrupo.setText(nodo.getNombre());
            btnAggClienteActionPerformed(null);
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAggCliente;
    private javax.swing.JButton btnAggProd;
    private javax.swing.JButton btnAggProv;
    private javax.swing.JButton btnAggTerminal;
    private javax.swing.JRadioButton btnImpresa;
    private javax.swing.JButton btnRemCliente16;
    private javax.swing.JButton btnRemProd;
    private javax.swing.JButton btnRemProv;
    private javax.swing.JButton btnRemTerminal;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JRadioButton chkExcel;
    private javax.swing.JComboBox cmbCientes;
    private javax.swing.JComboBox cmbProducto;
    private javax.swing.JComboBox cmbProveedor;
    private javax.swing.JComboBox cmbTerminal;
    private javax.swing.JComboBox cmbTerminal2;
    private datechooser.beans.DateChooserCombo dtFinal;
    private datechooser.beans.DateChooserCombo dtInicio;
    private javax.swing.JButton jButton4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbNit1;
    private javax.swing.JLabel lbNit11;
    private javax.swing.JLabel lbNit12;
    private javax.swing.JLabel lbNit15;
    private javax.swing.JLabel lbNit8;
    private javax.swing.JLabel lbNit9;
    private javax.swing.JLabel lbProducto;
    private javax.swing.JLabel lbTerminal;
    private javax.swing.JPanel pnlFormulario;
    private javax.swing.JRadioButton rdNoAnulados;
    private javax.swing.JScrollPane scrFormulario;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtGrupo;
    private javax.swing.JTextField txtProveedor;
    // End of variables declaration//GEN-END:variables
}
