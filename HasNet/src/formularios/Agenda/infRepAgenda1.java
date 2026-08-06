package formularios.Agenda;

import Vista.Productos.VistaBuscadorProductos;
import dao.InicioSesion.DaoInicioSesion;
import Modelo.InicioSesion.Terminal;
import formularios.productos.*;
import clases.Instancias;
import clases.metodosGenerales;
import clases.productos.ndProducto;
import Modelo.Terceros.ModeloContacto;
import formularios.terceros.buscClientes;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.JComboBox;

public class infRepAgenda1 extends javax.swing.JPanel {

    private final DaoInicioSesion daoInicioSesion = new DaoInicioSesion();
    metodosGenerales metodos = new metodosGenerales();
    private Instancias instancias;
    private Dimension dimBarra = null;

    public infRepAgenda1() {
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
            btnRemTerminal.setVisible(false);
            btnAggTerminal.setVisible(false);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoTipo = new javax.swing.ButtonGroup();
        buttonGroup1 = new javax.swing.ButtonGroup();
        scrFormulario = new javax.swing.JScrollPane();
        pnlFormulario = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        rdNoAnulados = new javax.swing.JRadioButton();
        rdAnulados = new javax.swing.JRadioButton();
        rdTodos = new javax.swing.JRadioButton();
        lbTerminal1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lbNit2 = new javax.swing.JLabel();
        lbNit10 = new javax.swing.JLabel();
        lbNit14 = new javax.swing.JLabel();
        txtProveedor = new javax.swing.JTextField();
        lbProducto = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        txtGrupo = new javax.swing.JTextField();
        cmbTipoIngreso = new javax.swing.JComboBox();
        lbTerminal = new javax.swing.JLabel();
        cmbTerminal = new javax.swing.JComboBox();
        jPanel11 = new javax.swing.JPanel();
        btnAggProd = new javax.swing.JButton();
        btnRemProd = new javax.swing.JButton();
        btnAggProv = new javax.swing.JButton();
        btnRemProv = new javax.swing.JButton();
        btnAggGrupo = new javax.swing.JButton();
        btnRemGrupo = new javax.swing.JButton();
        btnAggTipoIngreso = new javax.swing.JButton();
        btnRemTipoIngreso = new javax.swing.JButton();
        btnAggTerminal = new javax.swing.JButton();
        btnRemTerminal = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        cmbProveedor = new javax.swing.JComboBox();
        cmbProducto = new javax.swing.JComboBox();
        cmbGrupo = new javax.swing.JComboBox();
        cmbTipoIngreso2 = new javax.swing.JComboBox();
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
        jPanel2 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        pnlFormulario.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        buttonGroup1.add(rdNoAnulados);
        rdNoAnulados.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        rdNoAnulados.setSelected(true);
        rdNoAnulados.setText("Realizados");

        buttonGroup1.add(rdAnulados);
        rdAnulados.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        rdAnulados.setText("Anulados");

        buttonGroup1.add(rdTodos);
        rdTodos.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        rdTodos.setText("Todos");

        lbTerminal1.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbTerminal1.setText("Estado:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(rdNoAnulados)
                        .addGap(18, 18, 18)
                        .addComponent(rdAnulados)
                        .addGap(10, 10, 10)
                        .addComponent(rdTodos, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbTerminal1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(lbTerminal1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdNoAnulados)
                    .addComponent(rdAnulados)
                    .addComponent(rdTodos))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        lbNit2.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit2.setText("Proveedor");

        lbNit10.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit10.setText("Producto:");

        lbNit14.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit14.setText("Tipo:");

        txtProveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtProveedorKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtProveedorKeyReleased(evt);
            }
        });

        lbProducto.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbProducto.setText("Grupo:");

        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodigoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoKeyReleased(evt);
            }
        });

        txtGrupo.setBackground(new java.awt.Color(255, 204, 204));
        txtGrupo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtGrupoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtGrupoKeyReleased(evt);
            }
        });

        cmbTipoIngreso.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        cmbTipoIngreso.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "FACTURA", "DOC. EQUIVALENTE", "OTRO INGRESO" }));

        lbTerminal.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbTerminal.setText("Terminal:");

        cmbTerminal.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtProveedor, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtCodigo)
                    .addComponent(txtGrupo)
                    .addComponent(cmbTipoIngreso, 0, 236, Short.MAX_VALUE)
                    .addComponent(cmbTerminal, 0, 236, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbNit14, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbNit2)
                            .addComponent(lbNit10)
                            .addComponent(lbTerminal, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(lbNit2)
                .addGap(0, 0, 0)
                .addComponent(txtProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(lbNit10)
                .addGap(0, 0, 0)
                .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(lbProducto)
                .addGap(0, 0, 0)
                .addComponent(txtGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(lbNit14)
                .addGap(0, 0, 0)
                .addComponent(cmbTipoIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(lbTerminal)
                .addGap(0, 0, 0)
                .addComponent(cmbTerminal, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

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

        btnAggTipoIngreso.setBackground(new java.awt.Color(204, 204, 204));
        btnAggTipoIngreso.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnAggTipoIngreso.setForeground(new java.awt.Color(255, 255, 255));
        btnAggTipoIngreso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/agregar.png"))); // NOI18N
        btnAggTipoIngreso.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAggTipoIngreso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAggTipoIngresoActionPerformed(evt);
            }
        });

        btnRemTipoIngreso.setBackground(new java.awt.Color(204, 204, 204));
        btnRemTipoIngreso.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnRemTipoIngreso.setForeground(new java.awt.Color(153, 0, 0));
        btnRemTipoIngreso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/borrar2.png"))); // NOI18N
        btnRemTipoIngreso.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRemTipoIngreso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemTipoIngresoActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(btnAggProv, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRemProv, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(btnAggProd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRemProd))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(btnAggGrupo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRemGrupo))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(btnAggTipoIngreso)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRemTipoIngreso)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAggTerminal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRemTerminal)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAggProv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRemProv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(37, 37, 37)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRemProd)
                    .addComponent(btnAggProd, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRemGrupo)
                    .addComponent(btnAggGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRemTipoIngreso)
                    .addComponent(btnAggTipoIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRemTerminal)
                    .addComponent(btnAggTerminal, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));

        cmbTipoIngreso2.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        cmbTerminal2.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbProveedor, 0, 214, Short.MAX_VALUE)
                    .addComponent(cmbProducto, 0, 214, Short.MAX_VALUE)
                    .addComponent(cmbGrupo, 0, 214, Short.MAX_VALUE)
                    .addComponent(cmbTipoIngreso2, 0, 214, Short.MAX_VALUE)
                    .addComponent(cmbTerminal2, 0, 214, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmbProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(cmbProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(cmbGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(cmbTipoIngreso2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(cmbTerminal2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        lbNit8.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lbNit8.setText("Fecha inicial:");

        lbNit11.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lbNit11.setText("Versión:");

        btnImpresa.setBackground(new java.awt.Color(255, 255, 255));
        grupoTipo.add(btnImpresa);
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
        grupoTipo.add(chkExcel);
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jButton4.setBackground(new java.awt.Color(46, 204, 113));
        jButton4.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jButton4.setText("Generar Totalizado");
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(46, 204, 113));
        jButton5.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jButton5.setText("Generar Detallado");
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlFormularioLayout = new javax.swing.GroupLayout(pnlFormulario);
        pnlFormulario.setLayout(pnlFormularioLayout);
        pnlFormularioLayout.setHorizontalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlFormularioLayout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlFormularioLayout.setVerticalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(5, 5, 5)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
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
            .addGroup(layout.createSequentialGroup()
                .addComponent(scrFormulario)
                .addGap(0, 0, 0))
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

    private void txtCodigoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoKeyPressed

    private void txtCodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cargarProducto(txtCodigo.getText());
        }
    }//GEN-LAST:event_txtCodigoKeyReleased

    private void txtGrupoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGrupoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGrupoKeyPressed

    private void txtGrupoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGrupoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cargarGrupo(txtGrupo.getText());
        } else {
            txtGrupo.setText("");
        }
    }//GEN-LAST:event_txtGrupoKeyReleased

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
                metodos.msgAdvertencia(null, "Ya cargo este grupo");
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

    private void btnAggTipoIngresoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAggTipoIngresoActionPerformed
        for (int i = 0; i < cmbTipoIngreso2.getItemCount(); i++) {
            if (cmbTipoIngreso2.getItemAt(i).equals(cmbTipoIngreso.getSelectedItem())) {
                metodos.msgError(null, "Ya has ingresado este tipo de ingreso");
                return;
            }
        }
        cmbTipoIngreso2.addItem(cmbTipoIngreso.getSelectedItem());
    }//GEN-LAST:event_btnAggTipoIngresoActionPerformed

    private void btnRemTipoIngresoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemTipoIngresoActionPerformed
        if (cmbTipoIngreso2.getItemCount() == 0) {
            metodos.msgError(null, "Ningun tipo seleccionado");
            return;
        }
        cmbTipoIngreso2.removeItem(cmbTipoIngreso2.getSelectedItem());
    }//GEN-LAST:event_btnRemTipoIngresoActionPerformed

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

//            if (cmbGrupo.getItemCount() > 0) {
//                sql = sql + " (";
//                for (int i = 0; i < cmbGrupo.getItemCount(); i++) {
//                    sql = sql + " Grupo='" + cmbGrupo.getItemAt(i) + "' OR";
//                }
//                sql = sql.substring(0, sql.length() - 2) + ") AND";
//            }
            if (cmbTipoIngreso2.getItemCount() > 0) {
                sql = sql + " (";
                for (int i = 0; i < cmbTipoIngreso2.getItemCount(); i++) {
                    sql = sql + " tipo='" + cmbTipoIngreso2.getItemAt(i) + "' OR";
                }
                sql = sql.substring(0, sql.length() - 2) + ") AND";
            }

            String ini = metodos.desdeDate(dtInicio.getCurrent()), fin = metodos.desdeDate(dtFinal.getCurrent());
            sql = sql + " (((fechaFactura)>='" + ini + "') And ((fechaFactura)<='" + fin + "'))";

            System.out.println(sql);
            String enca = "";

            dtInicio.setFormat(0);
            dtFinal.setFormat(0);

            if (rdTodos.isSelected()) {
                if (dtInicio.getText().equals(dtFinal.getText())) {
                    enca = "Ingresos del " + ini;
                } else {
                    enca = "Ingresos entre el " + ini + " y el " + fin;
                }
            } else if (rdNoAnulados.isSelected()) {
                sql = sql + " And (anulada=false)";
                if (dtInicio.getText().equals(dtFinal.getText())) {
                    enca = "Ingresos del " + ini;
                } else {
                    enca = "Ingresos entre el " + ini + " y el " + fin;
                }
            } else if (rdAnulados.isSelected()) {
                sql = sql + " And (anulada=true)";
                if (dtInicio.getText().equals(dtFinal.getText())) {
                    enca = "Ingresos ANULADOS del " + ini;
                } else {
                    enca = "Ingresos ANULADOS entre el " + ini + " y el " + fin;
                }
            }

            dtInicio.setFormat(2);
            dtFinal.setFormat(2);

            String tipo = "";
            if (chkExcel.isSelected()) {
                tipo = "xls";
                instancias.setRutaAguardar(metodos.obtenerRuta(null, "Reporte ingresos corto " + metodosGenerales.dia() + " de " + metodosGenerales.mesEnPalabra() + " del " + metodosGenerales.anho()));
            }

            instancias.getReporte().ver_RepIngresos2(sql, enca, tipo);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
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

            if (cmbGrupo.getItemCount() > 0) {
                sql = sql + " (";

                for (int i = 0; i < cmbGrupo.getItemCount(); i++) {
                    sql = sql + " Grupo='" + cmbGrupo.getItemAt(i) + "' OR";
                }

                sql = sql.substring(0, sql.length() - 2) + ") AND";
            }

            if (cmbTipoIngreso2.getItemCount() > 0) {
                sql = sql + " (";

                for (int i = 0; i < cmbTipoIngreso2.getItemCount(); i++) {
                    sql = sql + " tipo='" + cmbTipoIngreso2.getItemAt(i) + "' OR";
                }

                sql = sql.substring(0, sql.length() - 2) + ") AND";
            }

            String ini = metodos.desdeDate(dtInicio.getCurrent()), fin = metodos.desdeDate(dtFinal.getCurrent());

            sql = sql + " (((fechaFactura)>='" + ini + "') And ((fechaFactura)<='" + fin + "'))";

            String enca = "";
            dtInicio.setFormat(0);
            dtFinal.setFormat(0);

            if (rdTodos.isSelected()) {
                if (dtInicio.getText().equals(dtFinal.getText())) {
                    enca = "Ingresos del " + ini;
                } else {
                    enca = "Ingresos entre el " + ini + " y el " + fin;
                }
            } else if (rdNoAnulados.isSelected()) {
                sql = sql + " And (anulada=false)";
                if (dtInicio.getText().equals(dtFinal.getText())) {
                    enca = "Ingresos del " + ini;
                } else {
                    enca = "Ingresos entre el " + ini + " y el " + fin;
                }
            } else {
                sql = sql + " And (anulada=true)";
                if (dtInicio.getText().equals(dtFinal.getText())) {
                    enca = "Ingresos ANULADOS del " + ini;
                } else {
                    enca = "Ingresos ANULADOS entre el " + ini + " y el " + fin;
                }
            }

            if (cmbGrupo.getItemCount() > 0) {
                enca = enca + ", Grupos: ";
                for (int i = 0; i < cmbGrupo.getItemCount(); i++) {
                    try {
                        Object[] datosGrupo = instancias.getSql().getDatosGrupo(cmbGrupo.getItemAt(i).toString());
                        enca = enca + datosGrupo[0].toString() + ", ";
                    } catch (Exception e) {
                    }
                }

                enca = enca.substring(0, enca.length() - 2);
            }
            dtInicio.setFormat(2);
            dtFinal.setFormat(2);

            String tipo = "";

            if (chkExcel.isSelected()) {
                tipo = "xls";
                instancias.setRutaAguardar(metodos.obtenerRuta(null, "Reporte ingresos " + metodosGenerales.dia() + " de " + metodosGenerales.mesEnPalabra() + " del " + metodosGenerales.anho()));
            }
            System.out.println("Consulta " + sql);
            instancias.getReporte().ver_ReIngresos(sql, enca, tipo);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    public void cargarProveedor(String nit) {

        ModeloContacto nodo = instancias.getSql().getDatosTercero(nit);

        if (nodo.getId() != null) {
            txtProveedor.setText(nodo.getIdSistema());
            btnAggProvActionPerformed(null);
            return;
        }
        ventanaProveedor(nit);
    }

    public void cargarGrupo(String codigo) {
        String grupo = "";
        try {
            grupo = instancias.getSql().getDatosGrupoNombre(codigo.replace("'", "//"));
            cmbGrupo.addItem(codigo);
            txtGrupo.setText("");
            cmbGrupo.setSelectedItem(codigo);
        } catch (Exception e) {
            ventanaGrupos(codigo);
        }
    }

    public void ventanaGrupos(String codigo) {
        buscGrupos buscar = new buscGrupos(null, true, "");
        buscar.setLocationRelativeTo(null);
        instancias.setBuscGrupos(buscar);
        instancias.setCampoActual(txtGrupo);
        txtGrupo.requestFocus();
        buscar.noEncontrado(codigo.replace("'", "//"));
        buscar.show();
    }

    public void cargarProducto(String codigo) {

        ndProducto nodo = null;

        String codigoProd = "";
        if (codigo.equals("")) {
            codigoProd = "";
        } else {
            Object[][] listado = instancias.getSql().getCodigosRelacionados(codigo, " where codigo");
            if (listado.length > 0) {
                codigo = listado[0][0].toString();
            }

            nodo = instancias.getSql().getDatosProducto(codigo, "bdProductos");
            if (nodo.getCodigo() != null) {
                codigoProd = nodo.getCodigo();
            }
        }

        if (!codigoProd.equals("")) {
            txtCodigo.setText(nodo.getIdSistema());
            btnAggProdActionPerformed(null);
            return;
        }
        ventanaProducto(codigo);
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
    private javax.swing.JButton btnAggGrupo;
    private javax.swing.JButton btnAggProd;
    private javax.swing.JButton btnAggProv;
    private javax.swing.JButton btnAggTerminal;
    private javax.swing.JButton btnAggTipoIngreso;
    private javax.swing.JRadioButton btnImpresa;
    private javax.swing.JButton btnRemGrupo;
    private javax.swing.JButton btnRemProd;
    private javax.swing.JButton btnRemProv;
    private javax.swing.JButton btnRemTerminal;
    private javax.swing.JButton btnRemTipoIngreso;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JRadioButton chkExcel;
    private javax.swing.JComboBox cmbGrupo;
    private javax.swing.JComboBox cmbProducto;
    private javax.swing.JComboBox cmbProveedor;
    private javax.swing.JComboBox cmbTerminal;
    private javax.swing.JComboBox cmbTerminal2;
    private javax.swing.JComboBox cmbTipoIngreso;
    private javax.swing.JComboBox cmbTipoIngreso2;
    private datechooser.beans.DateChooserCombo dtFinal;
    private datechooser.beans.DateChooserCombo dtInicio;
    private javax.swing.ButtonGroup grupoTipo;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbNit10;
    private javax.swing.JLabel lbNit11;
    private javax.swing.JLabel lbNit14;
    private javax.swing.JLabel lbNit2;
    private javax.swing.JLabel lbNit8;
    private javax.swing.JLabel lbNit9;
    private javax.swing.JLabel lbProducto;
    private javax.swing.JLabel lbTerminal;
    private javax.swing.JLabel lbTerminal1;
    private javax.swing.JPanel pnlFormulario;
    private javax.swing.JRadioButton rdAnulados;
    private javax.swing.JRadioButton rdNoAnulados;
    private javax.swing.JRadioButton rdTodos;
    private javax.swing.JScrollPane scrFormulario;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtGrupo;
    private javax.swing.JTextField txtProveedor;
    // End of variables declaration//GEN-END:variables
}
