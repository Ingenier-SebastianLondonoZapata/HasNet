package formularios.Ventas;

import DAO.InicioSesion.DaoInicioSesion;
import Modelo.InicioSesion.Terminal;
import clases.Instancias;
import clases.metodosGenerales;
import clases.productos.ndProducto;
import Modelo.Terceros.ModeloContacto;
import formularios.productos.buscGrupos;
import formularios.productos.buscProductos;
import formularios.terceros.buscBodegas;
import formularios.terceros.buscClientes;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.JComboBox;

public class infRepVentas extends javax.swing.JPanel {

    private final DaoInicioSesion daoInicioSesion = new DaoInicioSesion();
    metodosGenerales metodos = new metodosGenerales();
    private Instancias instancias;
    private Dimension dimBarra = null;

    public infRepVentas() {
        initComponents();

        dtInicio.setFormat(2);
        dtFinal.setFormat(2);

        instancias = Instancias.getInstancias();

        List<Terminal> terminales = daoInicioSesion.obtenerTerminales();
        for (Terminal terminal : terminales) {
            cmbTerminal.addItem(terminal.getTerminal());
        }

        if (!instancias.getUsuario().equals("ADMIN")) {
            lbTerm.setVisible(false);
            cmbTerminal.setVisible(false);
            cmbTerminal2.setVisible(false);
            btnAggTerminal.setVisible(false);
            btnRemTerminal.setVisible(false);
        }

        if (!instancias.getConfiguraciones().isInventarioBodegas()) {
            btnAggBodega.setVisible(false);
            btnRemoverBodega.setVisible(false);
            txtBodega.setVisible(false);
            cmbBodega.setVisible(false);
            lbBodega.setVisible(false);
        }

        setVendedores();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoTipo = new javax.swing.ButtonGroup();
        jRadioButton1 = new javax.swing.JRadioButton();
        scrFormulario = new javax.swing.JScrollPane();
        pnlFormulario = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        lbNit8 = new javax.swing.JLabel();
        lbNit11 = new javax.swing.JLabel();
        btnImpresa = new javax.swing.JRadioButton();
        dtInicio = new datechooser.beans.DateChooserCombo();
        lbNit9 = new javax.swing.JLabel();
        dtFinal = new datechooser.beans.DateChooserCombo();
        chkExcel = new javax.swing.JRadioButton();
        jPanel8 = new javax.swing.JPanel();
        lbNit1 = new javax.swing.JLabel();
        lbNit17 = new javax.swing.JLabel();
        lbTerminal = new javax.swing.JLabel();
        cmbTipo = new javax.swing.JComboBox();
        cmbVendedor = new javax.swing.JComboBox();
        txtCliente = new javax.swing.JTextField();
        lbNit2 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        lbNit4 = new javax.swing.JLabel();
        txtCodGrupo = new javax.swing.JTextField();
        lbTerm = new javax.swing.JLabel();
        cmbTerminal = new javax.swing.JComboBox();
        lbTerm1 = new javax.swing.JLabel();
        cmbTipoFact = new javax.swing.JComboBox();
        lbBodega = new javax.swing.JLabel();
        txtBodega = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        btnAggProd1 = new javax.swing.JButton();
        btnRemProd1 = new javax.swing.JButton();
        btnAggCliente = new javax.swing.JButton();
        btnRemCliente = new javax.swing.JButton();
        btnAggVendedor = new javax.swing.JButton();
        btnRemVendedor = new javax.swing.JButton();
        btnAggProd = new javax.swing.JButton();
        btnRemProd = new javax.swing.JButton();
        btnAggGrupo = new javax.swing.JButton();
        btnRemGrupo = new javax.swing.JButton();
        btnAggTerminal = new javax.swing.JButton();
        btnRemTerminal = new javax.swing.JButton();
        btnAggTerminal1 = new javax.swing.JButton();
        btnRemTerminal1 = new javax.swing.JButton();
        btnAggBodega = new javax.swing.JButton();
        btnRemoverBodega = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        cmbCientes = new javax.swing.JComboBox();
        cmbTipo2 = new javax.swing.JComboBox();
        cmbVendedor2 = new javax.swing.JComboBox();
        cmbProducto = new javax.swing.JComboBox();
        cmbGrupo = new javax.swing.JComboBox();
        cmbTerminal2 = new javax.swing.JComboBox();
        cmbTipoFact1 = new javax.swing.JComboBox();
        cmbBodega = new javax.swing.JComboBox();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        lbNit13 = new javax.swing.JLabel();
        rbFiltroHora = new javax.swing.JRadioButton();
        cmbInicio = new javax.swing.JComboBox();
        lbNit14 = new javax.swing.JLabel();
        cmbFin = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        jRadioButton1.setText("jRadioButton1");

        pnlFormulario.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

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

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbNit8)
                    .addComponent(lbNit11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dtInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnImpresa))
                .addGap(22, 22, 22)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lbNit9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dtFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(chkExcel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbNit9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbNit8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dtInicio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dtFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnImpresa)
                    .addComponent(lbNit11)
                    .addComponent(chkExcel))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        lbNit1.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit1.setText("Cliente:");

        lbNit17.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit17.setText("Forma de pago:");

        lbTerminal.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbTerminal.setText("Vendedor");

        cmbTipo.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        cmbTipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "CONTADO", "CREDITO", "SISTECREDITO" }));

        cmbVendedor.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        txtCliente.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtClienteKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtClienteKeyReleased(evt);
            }
        });

        lbNit2.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit2.setText("Producto:");

        txtCodigo.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodigoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoKeyReleased(evt);
            }
        });

        lbNit4.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit4.setText("Grupo:");

        txtCodGrupo.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtCodGrupo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodGrupoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodGrupoKeyReleased(evt);
            }
        });

        lbTerm.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbTerm.setText("Terminal:");

        cmbTerminal.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        lbTerm1.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbTerm1.setText("Facturas:");

        cmbTipoFact.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        cmbTipoFact.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Con impoconsumo", "Con iva", "Ambas", "Exceptas" }));

        lbBodega.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbBodega.setText("Bodega:");

        txtBodega.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtBodega.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBodegaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBodegaKeyReleased(evt);
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
                    .addComponent(lbNit17, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
                    .addComponent(cmbTipo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbTerminal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbVendedor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCliente)
                    .addComponent(lbNit2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCodigo)
                    .addComponent(lbNit4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCodGrupo)
                    .addComponent(lbTerm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbTerminal, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbTerm1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbTipoFact, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbBodega, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtBodega))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(lbNit1)
                .addGap(3, 3, 3)
                .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(lbNit17)
                .addGap(3, 3, 3)
                .addComponent(cmbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(lbTerminal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(lbNit2)
                .addGap(3, 3, 3)
                .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(lbNit4)
                .addGap(0, 0, 0)
                .addComponent(txtCodGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(lbTerm)
                .addGap(0, 0, 0)
                .addComponent(cmbTerminal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(lbTerm1)
                .addGap(0, 0, 0)
                .addComponent(cmbTipoFact, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(lbBodega)
                .addGap(0, 0, 0)
                .addComponent(txtBodega, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

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

        btnAggVendedor.setBackground(new java.awt.Color(204, 204, 204));
        btnAggVendedor.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnAggVendedor.setForeground(new java.awt.Color(255, 255, 255));
        btnAggVendedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/agregar.png"))); // NOI18N
        btnAggVendedor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAggVendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAggVendedorActionPerformed(evt);
            }
        });

        btnRemVendedor.setBackground(new java.awt.Color(204, 204, 204));
        btnRemVendedor.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnRemVendedor.setForeground(new java.awt.Color(153, 0, 0));
        btnRemVendedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/borrar2.png"))); // NOI18N
        btnRemVendedor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRemVendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemVendedorActionPerformed(evt);
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

        btnAggTerminal1.setBackground(new java.awt.Color(204, 204, 204));
        btnAggTerminal1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnAggTerminal1.setForeground(new java.awt.Color(255, 255, 255));
        btnAggTerminal1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/agregar.png"))); // NOI18N
        btnAggTerminal1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAggTerminal1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAggTerminal1ActionPerformed(evt);
            }
        });

        btnRemTerminal1.setBackground(new java.awt.Color(204, 204, 204));
        btnRemTerminal1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnRemTerminal1.setForeground(new java.awt.Color(153, 0, 0));
        btnRemTerminal1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/borrar2.png"))); // NOI18N
        btnRemTerminal1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRemTerminal1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemTerminal1ActionPerformed(evt);
            }
        });

        btnAggBodega.setBackground(new java.awt.Color(204, 204, 204));
        btnAggBodega.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnAggBodega.setForeground(new java.awt.Color(255, 255, 255));
        btnAggBodega.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/agregar.png"))); // NOI18N
        btnAggBodega.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAggBodega.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAggBodegaActionPerformed(evt);
            }
        });

        btnRemoverBodega.setBackground(new java.awt.Color(204, 204, 204));
        btnRemoverBodega.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnRemoverBodega.setForeground(new java.awt.Color(153, 0, 0));
        btnRemoverBodega.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/borrar2.png"))); // NOI18N
        btnRemoverBodega.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRemoverBodega.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverBodegaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addGap(0, 1, Short.MAX_VALUE)
                        .addComponent(btnAggProd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRemProd))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(btnAggCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnRemCliente))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(btnAggProd1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnRemProd1))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(btnAggVendedor)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnRemVendedor))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(btnAggGrupo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnRemGrupo))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(btnAggTerminal)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnRemTerminal))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(btnAggTerminal1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnRemTerminal1))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(btnAggBodega)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnRemoverBodega)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAggCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRemCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(31, 31, 31)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRemProd1)
                    .addComponent(btnAggProd1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRemVendedor)
                    .addComponent(btnAggVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRemProd)
                    .addComponent(btnAggProd, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRemGrupo)
                    .addComponent(btnAggGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRemTerminal)
                    .addComponent(btnAggTerminal, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRemTerminal1)
                    .addComponent(btnAggTerminal1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRemoverBodega)
                    .addComponent(btnAggBodega, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));

        cmbCientes.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        cmbTipo2.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        cmbVendedor2.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        cmbProducto.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        cmbGrupo.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        cmbTerminal2.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        cmbTipoFact1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        cmbBodega.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbCientes, 0, 214, Short.MAX_VALUE)
                    .addComponent(cmbTipo2, 0, 214, Short.MAX_VALUE)
                    .addComponent(cmbVendedor2, 0, 214, Short.MAX_VALUE)
                    .addComponent(cmbProducto, 0, 214, Short.MAX_VALUE)
                    .addComponent(cmbGrupo, 0, 214, Short.MAX_VALUE)
                    .addComponent(cmbTerminal2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbTipoFact1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbBodega, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(cmbCientes, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(cmbTipo2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(cmbVendedor2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(cmbProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(cmbGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(cmbTerminal2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(cmbTipoFact1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cmbBodega, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        lbNit13.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbNit13.setText("Hora Inicial:");

        rbFiltroHora.setBackground(new java.awt.Color(255, 255, 255));
        rbFiltroHora.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        rbFiltroHora.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        rbFiltroHora.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        cmbInicio.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        cmbInicio.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1:00", "2:00", "3:00", "4:00", "5:00", "6:00", "7:00", "8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00", "24:00" }));

        lbNit14.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbNit14.setText("Hora Final:");

        cmbFin.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        cmbFin.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1:00", "2:00", "3:00", "4:00", "5:00", "6:00", "7:00", "8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00", "24:00" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rbFiltroHora)
                .addGap(5, 5, 5)
                .addComponent(lbNit13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addComponent(lbNit14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lbNit14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rbFiltroHora, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbNit13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbInicio, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbFin, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jButton4.setBackground(new java.awt.Color(46, 204, 113));
        jButton4.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jButton4.setText("Generar Totalizados");
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
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(114, 114, 114)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout pnlFormularioLayout = new javax.swing.GroupLayout(pnlFormulario);
        pnlFormulario.setLayout(pnlFormularioLayout);
        pnlFormularioLayout.setHorizontalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlFormularioLayout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator1)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        pnlFormularioLayout.setVerticalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        scrFormulario.setViewportView(pnlFormulario);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrFormulario)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrFormulario)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClienteKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cargarCliente(txtCliente.getText());
        }
    }//GEN-LAST:event_txtClienteKeyReleased

    private void txtClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClienteKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtClienteKeyPressed

    private void btnAggProd1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAggProd1ActionPerformed
        for (int i = 0; i < cmbTipo2.getItemCount(); i++) {
            if (cmbTipo2.getItemAt(i).equals(cmbTipo.getSelectedItem())) {
                metodos.msgError(null, "Ya has ingresado este tipo de pago");
                return;
            }
        }
        cmbTipo2.addItem(cmbTipo.getSelectedItem());
    }//GEN-LAST:event_btnAggProd1ActionPerformed

    private void btnRemProd1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemProd1ActionPerformed
        if (cmbTipo2.getItemCount() == 0) {
            metodos.msgError(null, "Ningún tipo seleccionado");
            return;
        }
        cmbTipo2.removeItem(cmbTipo2.getSelectedItem());
    }//GEN-LAST:event_btnRemProd1ActionPerformed

    private void btnAggClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAggClienteActionPerformed
        if (txtCliente.getText().equals("")) {
            metodos.msgError(null, "Cargue primero un cliente");
        } else {

            if (yaEsta(cmbCientes, txtCliente.getText())) {
                metodos.msgAdvertencia(null, "Ya cargo este cliente");
            } else {
                cmbCientes.addItem(txtCliente.getText());
                cmbCientes.setSelectedItem(txtCliente.getText());
            }

            txtCliente.setText("");
            txtCliente.setText("");
        }
    }//GEN-LAST:event_btnAggClienteActionPerformed

    private void btnRemClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemClienteActionPerformed
        cmbCientes.removeItem(cmbCientes.getSelectedItem());
    }//GEN-LAST:event_btnRemClienteActionPerformed

    private void btnAggVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAggVendedorActionPerformed
        for (int i = 0; i < cmbVendedor2.getItemCount(); i++) {
            if (cmbVendedor2.getItemAt(i).equals(cmbVendedor.getSelectedItem())) {
                metodos.msgError(null, "Ya has ingresado este vendedor");
                return;
            }
        }
        cmbVendedor2.addItem(cmbVendedor.getSelectedItem());
    }//GEN-LAST:event_btnAggVendedorActionPerformed

    private void btnRemVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemVendedorActionPerformed
        if (cmbVendedor2.getItemCount() == 0) {
            metodos.msgError(null, "No hay ninguna terminal seleccioanda");
            return;
        }
        cmbVendedor2.removeItem(cmbVendedor2.getSelectedItem());
    }//GEN-LAST:event_btnRemVendedorActionPerformed

    private void txtCodigoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoKeyPressed

    private void txtCodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cargarProducto(txtCodigo.getText());
        }
    }//GEN-LAST:event_txtCodigoKeyReleased

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

    private void txtCodGrupoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodGrupoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodGrupoKeyPressed

    private void txtCodGrupoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodGrupoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cargarGrupo(txtCodGrupo.getText());
        } else {
//            txtGrupo.setText("");
        }
    }//GEN-LAST:event_txtCodGrupoKeyReleased

    private void btnAggGrupoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAggGrupoActionPerformed
        if (yaEsta(cmbGrupo, txtCodGrupo.getText())) {
            metodos.msgAdvertencia(null, "Ya cargo este grupo");
        } else {
            cmbGrupo.addItem(txtCodGrupo.getText());
            cmbGrupo.setSelectedItem(txtCodGrupo.getText());
        }

        txtCodGrupo.setText("");
//        txtGrupo.setText("");
    }//GEN-LAST:event_btnAggGrupoActionPerformed

    private void btnRemGrupoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemGrupoActionPerformed
        if (cmbGrupo.getItemCount() == 0) {
            metodos.msgError(null, "No hay ningun grupo seleccioando");
            return;
        }
        cmbGrupo.removeItem(cmbGrupo.getSelectedItem());
    }//GEN-LAST:event_btnRemGrupoActionPerformed

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
            metodos.msgError(null, "No hay ninguna terminal seleccionada");
            return;
        }
        cmbTerminal2.removeItem(cmbTerminal2.getSelectedItem());
    }//GEN-LAST:event_btnRemTerminalActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if (metodos.msgPregunta(null, "¿Esta seguro?") == 0) {
            String sql = "where (anulada = false) AND ";

            if (instancias.getUsuario().equals("ADMIN")) {
                if (cmbTerminal2.getItemCount() > 0) {
                    sql = sql + " (";

                    for (int i = 0; i < cmbTerminal2.getItemCount(); i++) {
                        sql = sql + " bdfactura.terminal='" + cmbTerminal2.getItemAt(i) + "' OR";
                    }

                    sql = sql.substring(0, sql.length() - 2) + ") AND";
                }
            } else {
                if (!instancias.isVisualizarTodasLasFacturas()) {
                    sql = sql + " bdfactura.terminal='" + instancias.getTerminal() + "' AND";
                }

            }

            if (cmbTipo2.getItemCount() > 0) {
                sql = sql + " (";

                for (int i = 0; i < cmbTipo2.getItemCount(); i++) {
                    String aux = "";
                    if (cmbTipo2.getItemAt(i).equals("CREDITO")) {
                        aux = "true";
                        sql = sql + " credito=" + aux + " OR";
                    } else if (cmbTipo2.getItemAt(i).equals("CONTADO")) {
                        aux = "false";
                        sql = sql + " credito=" + aux + " OR";
                    } else if (cmbTipo2.getItemAt(i).equals("SISTECREDITO")) {
                        sql = sql + " sisteCredito = 1 OR";
                    }
                }
                sql = sql.substring(0, sql.length() - 2) + ") AND";
            }

//            if (rbtImpoconsumo.isSelected()) {
//                sql = sql + " (";
//                sql = sql + " impuesto > 0) AND";
//            }
            if (cmbCientes.getItemCount() > 0) {
                sql = sql + " (";
                for (int i = 0; i < cmbCientes.getItemCount(); i++) {
                    sql = sql + " idSistema ='" + cmbCientes.getItemAt(i) + "' OR";
                }
                sql = sql.substring(0, sql.length() - 2) + ") AND";
            }

            if (cmbTipoFact1.getItemCount() > 0) {
                sql = sql + " (";
                for (int i = 0; i < cmbTipoFact1.getItemCount(); i++) {
                    if (cmbTipoFact1.getItemAt(i).equals("Con impoconsumo")) {
                        sql = sql + " bdfactura.impoconsumo > 0 OR";
                    } else if (cmbTipoFact1.getItemAt(i).equals("Con iva")) {
                        sql = sql + " bdfactura.ivaGeneral > 0 OR";
                    } else if (cmbTipoFact1.getItemAt(i).equals("Ambas")) {
                        sql = sql + " bdfactura.ivaGeneral > 0 and bdfactura.impoconsumo > 0 OR";
                    } else if (cmbTipoFact1.getItemAt(i).equals("Exceptas")) {
                        sql = sql + " bdfactura.ivaGeneral <= 0 and bdfactura.impoconsumo <= 0 OR";
                    }
                }
                sql = sql.substring(0, sql.length() - 2) + ") AND";
            }

            if (cmbVendedor2.getItemCount() > 0) {
                sql = sql + " (";
                for (int i = 0; i < cmbVendedor2.getItemCount(); i++) {
                    sql = sql + " bdfactura.vendedor='" + cmbVendedor2.getItemAt(i) + "' OR";
                }
                sql = sql.substring(0, sql.length() - 2) + ") AND";
            }

            if (cmbBodega.getItemCount() > 0) {
                sql = sql + " (";
                for (int i = 0; i < cmbBodega.getItemCount(); i++) {
                    sql = sql + " bodega = '" + cmbBodega.getItemAt(i) + "' OR";
                }
                sql = sql.substring(0, sql.length() - 2) + ") AND";
            }

            String ini = metodos.desdeDate2(dtInicio.getCurrent()), fin = metodos.desdeDate(dtFinal.getCurrent());

            fin = metodos.sumarFecha(metodos.fecha(fin), 0);
            fin = metodos.fechaConsulta(fin);
            ini = metodos.fechaConsulta(ini);

            String horaInicial = cmbInicio.getSelectedItem().toString();
            String horaFinal = cmbFin.getSelectedItem().toString();

            if (rbFiltroHora.isSelected()) {
                sql = sql + " (hora >= '" + horaInicial + "' And hora <= '" + horaFinal + "') AND";
            }

            sql = sql + " (((fechaFactura) >= '" + ini + "') And ((fechaFactura)<= '" + fin + "'))";

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

            if (chkExcel.isSelected()) {
                instancias.setRutaAguardar(metodos.obtenerRuta(null, "Reporte de ventas corto " + metodosGenerales.dia() + " de " + metodosGenerales.mesEnPalabra() + " del " + metodosGenerales.anho()));
                tipo = "xls";
            }

            String enca2 = "Filtros por: ";
            for (int i = 0; i < cmbVendedor2.getItemCount(); i++) {
                enca2 = enca2 + cmbVendedor2.getItemAt(i) + ", ";
            }

            if (enca2.equals("Filtros por: ")) {
                enca2 = "";
            }

            System.out.println("condicion: " + sql);
            instancias.getReporte().ver_RepVentas2(sql, enca, tipo, enca2);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        if (metodos.msgPregunta(null, "¿Esta seguro?") == 0) {

            String sql = "where (anulada = false) AND ";

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

            if (cmbTipo2.getItemCount() > 0) {
                sql = sql + " (";

                for (int i = 0; i < cmbTipo2.getItemCount(); i++) {
                    String aux = "";
                    if (cmbTipo2.getItemAt(i).equals("CREDITO")) {
                        sql = sql + " credito=" + true + " OR";
                    } else if (cmbTipo2.getItemAt(i).equals("CONTADO")) {
                        sql = sql + " credito=" + false + " OR";
                    } else if (cmbTipo2.getItemAt(i).equals("SISTECREDITO")) {
                        sql = sql + " sisteCredito = 1 OR";
                    }
                }
                sql = sql.substring(0, sql.length() - 2) + ") AND";
            }

            if (cmbCientes.getItemCount() > 0) {
                sql = sql + " (";
                for (int i = 0; i < cmbCientes.getItemCount(); i++) {
                    sql = sql + " cliente='" + cmbCientes.getItemAt(i) + "' OR";
                }
                sql = sql.substring(0, sql.length() - 2) + ") AND";
            }

            if (cmbTipoFact1.getItemCount() > 0) {
                sql = sql + " (";
                for (int i = 0; i < cmbTipoFact1.getItemCount(); i++) {
                    if (cmbTipoFact1.getItemAt(i).equals("Con impoconsumo")) {
                        sql = sql + " bdfactura.impoconsumo > 0 OR";
                    } else if (cmbTipoFact1.getItemAt(i).equals("Con iva")) {
                        sql = sql + " bdfactura.iva > 0 OR";
                    } else if (cmbTipoFact1.getItemAt(i).equals("Ambas")) {
                        sql = sql + " bdfactura.iva > 0 and bdfactura.impoconsumo > 0 OR";
                    } else if (cmbTipoFact1.getItemAt(i).equals("Exceptas")) {
                        sql = sql + "bdfactura.iva <= 0 and bdfactura.impoconsumo <= 0 OR";
                    }
                }
                sql = sql.substring(0, sql.length() - 2) + ") AND";
            }

            if (cmbVendedor2.getItemCount() > 0) {
                sql = sql + " (";
                for (int i = 0; i < cmbVendedor2.getItemCount(); i++) {
                    sql = sql + " bdfactura.vendedor='" + cmbVendedor2.getItemAt(i) + "' OR";
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

            String ini = metodos.desdeDate(dtInicio.getCurrent()), fin = metodos.desdeDate(dtFinal.getCurrent());

            String horaInicial = cmbInicio.getSelectedItem().toString();
            String horaFinal = cmbFin.getSelectedItem().toString();

            fin = metodos.sumarFecha(metodos.fecha(fin), 1);
            fin = metodos.fechaConsulta(fin);

            if (rbFiltroHora.isSelected()) {
                sql = sql + " (hora >= '" + horaInicial + "' And hora <= '" + horaFinal + "') AND";
            }

            sql = sql + " (((fechaFactura) >='" + ini + "') And ((fechaFactura)<='" + fin + "'))";

            System.out.println("Consulta Rep Ventas : " + sql);

            // sql = sql + " ((fechaFactura)>=#" + "01/04/2014" + "# And (fechaFactura)<=#" + "4/5/2014" + "#)";
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

            if (chkExcel.isSelected()) {
                instancias.setRutaAguardar(metodos.obtenerRuta(null, "Reporte de ventas " + metodosGenerales.dia() + " de " + metodosGenerales.mesEnPalabra() + " del " + metodosGenerales.anho()));
                tipo = "xls";
            }

            String enca2 = "Filtros por: ";
            for (int i = 0; i < cmbGrupo.getItemCount(); i++) {
                enca2 = enca2 + cmbGrupo.getItemAt(i) + ", ";
            }
            if (enca2.equals("Filtros por: ")) {
                enca2 = "";
            }

            System.out.println("sql: " + sql);
            instancias.getReporte().ver_RepVentas(sql, enca, tipo, enca2);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void btnAggTerminal1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAggTerminal1ActionPerformed
        for (int i = 0; i < cmbTipoFact1.getItemCount(); i++) {
            if (cmbTipoFact1.getItemAt(i).equals(cmbTipoFact.getSelectedItem())) {
                metodos.msgError(null, "Ya has ingresado este tipo");
                return;
            }
        }
        cmbTipoFact1.addItem(cmbTipoFact.getSelectedItem());
    }//GEN-LAST:event_btnAggTerminal1ActionPerformed

    private void btnRemTerminal1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemTerminal1ActionPerformed
        if (cmbTipoFact1.getItemCount() == 0) {
            metodos.msgError(null, "No hay ninguna tipo");
            return;
        }
        cmbTipoFact1.removeItem(cmbTipoFact1.getSelectedItem());
    }//GEN-LAST:event_btnRemTerminal1ActionPerformed

    private void txtBodegaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBodegaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBodegaKeyPressed

    private void txtBodegaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBodegaKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txtBodega.getText().equals("")) {
                ventanaBodegas("");
            } else {
                btnAggBodegaActionPerformed(null);
            }
        } else {
            txtBodega.setText("");
        }
    }//GEN-LAST:event_txtBodegaKeyReleased

    private void btnAggBodegaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAggBodegaActionPerformed
        if (yaEsta(cmbBodega, txtBodega.getText())) {
            metodos.msgAdvertencia(null, "Ya cargo esta bodega");
        } else {
            cmbBodega.addItem(txtBodega.getText());
            cmbBodega.setSelectedItem(txtBodega.getText());
        }

        txtBodega.setText("");
    }//GEN-LAST:event_btnAggBodegaActionPerformed

    private void btnRemoverBodegaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverBodegaActionPerformed
        if (cmbBodega.getItemCount() == 0) {
            metodos.msgError(null, "Seleccione una bodega");
            return;
        }
        cmbBodega.removeItem(cmbBodega.getSelectedItem());
    }//GEN-LAST:event_btnRemoverBodegaActionPerformed

    public void ventanaBodegas(String nit) {
        buscBodegas buscar = new buscBodegas(instancias.getMenu(), true, "INTERNA");
        buscar.setLocationRelativeTo(null);
        instancias.setBuscBodegas(buscar);
        instancias.setCampoActual(txtBodega);
        txtBodega.requestFocus();
        buscar.noEncontrado(nit);
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
        buscClientes buscar = new buscClientes(instancias.getMenu(), false, false, null, "");
        buscar.setOpc("factura");
        buscar.setLocationRelativeTo(null);
        instancias.setBusClientes(buscar);
        instancias.setCampoActual(txtCliente);
        txtCliente.requestFocus();
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
        buscProductos buscar = new buscProductos(instancias.getMenu(), false, false, "", "productos1");
        buscar.setLocationRelativeTo(null);
        instancias.setBusProductos(buscar);
        instancias.setCampoActual(txtCodigo);
        txtCodigo.requestFocus();
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

    public void ventanaGrupos(String codigo) {
        buscGrupos buscar = new buscGrupos(null, true, "");
        buscar.setLocationRelativeTo(null);
        instancias.setBuscGrupos(buscar);
        instancias.setCampoActual(txtCodGrupo);
        txtCodGrupo.requestFocus();
        buscar.noEncontrado(codigo.replace("'", "//"));
        buscar.show();
    }

    public void cargarGrupo(String codigo) {
        String grupo = "";
        try {
            grupo = instancias.getSql().getDatosGrupoNombre(codigo.replace("'", "//"));
            cmbGrupo.addItem(codigo);
            txtCodGrupo.setText("");
            cmbGrupo.setSelectedItem(codigo);
        } catch (Exception e) {
            ventanaGrupos(codigo);
        }
    }

    public void setVendedores() {
        String[] vendedores;

        Object[][] vendedoresMatriz = instancias.getSql().getVendedores();
        Object[][] domiciliariosMatriz = instancias.getSql().getDomiciliarios();

        vendedores = new String[vendedoresMatriz.length + domiciliariosMatriz.length + 1];
        vendedores[0] = " ";

        int ser = 0;
        for (int i = 0; i < vendedoresMatriz.length; i++) {
            vendedores[ser + 1] = vendedoresMatriz[i][0].toString();
            ser++;
        }

        for (int i = 0; i < domiciliariosMatriz.length; i++) {
            vendedores[ser + 1] = domiciliariosMatriz[i][0].toString();
            ser++;
        }

        cmbVendedor.removeAllItems();
        for (int i = 0; i < vendedores.length; i++) {
            cmbVendedor.addItem(vendedores[i]);
        }

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAggBodega;
    private javax.swing.JButton btnAggCliente;
    private javax.swing.JButton btnAggGrupo;
    private javax.swing.JButton btnAggProd;
    private javax.swing.JButton btnAggProd1;
    private javax.swing.JButton btnAggTerminal;
    private javax.swing.JButton btnAggTerminal1;
    private javax.swing.JButton btnAggVendedor;
    private javax.swing.JRadioButton btnImpresa;
    private javax.swing.JButton btnRemCliente;
    private javax.swing.JButton btnRemGrupo;
    private javax.swing.JButton btnRemProd;
    private javax.swing.JButton btnRemProd1;
    private javax.swing.JButton btnRemTerminal;
    private javax.swing.JButton btnRemTerminal1;
    private javax.swing.JButton btnRemVendedor;
    private javax.swing.JButton btnRemoverBodega;
    private javax.swing.JRadioButton chkExcel;
    private javax.swing.JComboBox cmbBodega;
    private javax.swing.JComboBox cmbCientes;
    private javax.swing.JComboBox cmbFin;
    private javax.swing.JComboBox cmbGrupo;
    private javax.swing.JComboBox cmbInicio;
    private javax.swing.JComboBox cmbProducto;
    private javax.swing.JComboBox cmbTerminal;
    private javax.swing.JComboBox cmbTerminal2;
    private javax.swing.JComboBox cmbTipo;
    private javax.swing.JComboBox cmbTipo2;
    private javax.swing.JComboBox cmbTipoFact;
    private javax.swing.JComboBox cmbTipoFact1;
    private javax.swing.JComboBox cmbVendedor;
    private javax.swing.JComboBox cmbVendedor2;
    private datechooser.beans.DateChooserCombo dtFinal;
    private datechooser.beans.DateChooserCombo dtInicio;
    private javax.swing.ButtonGroup grupoTipo;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbBodega;
    private javax.swing.JLabel lbNit1;
    private javax.swing.JLabel lbNit11;
    private javax.swing.JLabel lbNit13;
    private javax.swing.JLabel lbNit14;
    private javax.swing.JLabel lbNit17;
    private javax.swing.JLabel lbNit2;
    private javax.swing.JLabel lbNit4;
    private javax.swing.JLabel lbNit8;
    private javax.swing.JLabel lbNit9;
    private javax.swing.JLabel lbTerm;
    private javax.swing.JLabel lbTerm1;
    private javax.swing.JLabel lbTerminal;
    private javax.swing.JPanel pnlFormulario;
    private javax.swing.JRadioButton rbFiltroHora;
    private javax.swing.JScrollPane scrFormulario;
    private javax.swing.JTextField txtBodega;
    private javax.swing.JTextField txtCliente;
    private javax.swing.JTextField txtCodGrupo;
    private javax.swing.JTextField txtCodigo;
    // End of variables declaration//GEN-END:variables
}
