package formularios.productos;

import clases.Instancias;
import clases.big;
import clases.metodosGenerales;
import clases.productos.ndGrupo;
import clases.productos.ndProducto;
import Modelo.Terceros.ModeloContacto;
import formularios.terceros.buscClientes;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import javax.swing.JComboBox;

public class infInventario extends javax.swing.JPanel {

    metodosGenerales metodos = new metodosGenerales();
    private Instancias instancias;
    private Dimension dimBarra = null;

    public infInventario() {
        initComponents();

        setBorder(null);
        repaint();
        instancias = Instancias.getInstancias();

        if (instancias.getConfiguraciones().isProductosDetallados()) {
            lbTerminal1.setVisible(true);
            txtColor.setVisible(true);
            btnAggColor.setVisible(true);
            btnRemColor.setVisible(true);
            cmbColor.setVisible(true);

            lbImei.setVisible(true);
            txtImeil.setVisible(true);
            btnAggImeil.setVisible(true);
            btnRemImeil.setVisible(true);
            cmbImeil.setVisible(true);

            btnDetallado.setVisible(true);
        } else {
            btnDetallado.setVisible(false);
            lbTerminal1.setVisible(false);
            txtColor.setVisible(false);
            btnAggColor.setVisible(false);
            btnRemColor.setVisible(false);

            lbImei.setVisible(false);
            txtImeil.setVisible(false);
            btnAggImeil.setVisible(false);
            btnRemImeil.setVisible(false);
            cmbImeil.setVisible(false);

            cmbColor.setVisible(false);
        }

        if (instancias.getConfiguraciones().isInventarioBodegas()) {
            lbBodega.setVisible(true);
            cmbBodegas.setVisible(true);

            Object[][] bodegas = instancias.getSql().getTodasBodegas();
            for (int i = 0; i < bodegas.length; i++) {
                cmbBodegas.addItem(bodegas[i][1]);
            }
        } else {
            lbBodega.setVisible(false);
            cmbBodegas.setVisible(false);
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
        btnExcel = new javax.swing.JRadioButton();
        btnImpresa = new javax.swing.JRadioButton();
        lbNit12 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        btnDetallado = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        lbNit = new javax.swing.JLabel();
        lbNit10 = new javax.swing.JLabel();
        lbTerminal = new javax.swing.JLabel();
        txtProveedor = new javax.swing.JTextField();
        txtGrupo = new javax.swing.JTextField();
        txtCodigo = new javax.swing.JTextField();
        lbTerminal1 = new javax.swing.JLabel();
        txtColor = new javax.swing.JTextField();
        lbImei = new javax.swing.JLabel();
        txtImeil = new javax.swing.JTextField();
        lbBodega = new javax.swing.JLabel();
        cmbBodegas = new javax.swing.JComboBox();
        jPanel14 = new javax.swing.JPanel();
        btnAggProd1 = new javax.swing.JButton();
        btnRemProd1 = new javax.swing.JButton();
        btnAggProv = new javax.swing.JButton();
        btnRemProv = new javax.swing.JButton();
        btnAggProd = new javax.swing.JButton();
        btnRemProd = new javax.swing.JButton();
        btnAggColor = new javax.swing.JButton();
        btnRemColor = new javax.swing.JButton();
        btnAggImeil = new javax.swing.JButton();
        btnRemImeil = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        cmbProveedor = new javax.swing.JComboBox();
        cmbProducto1 = new javax.swing.JComboBox();
        cmbProducto = new javax.swing.JComboBox();
        cmbColor = new javax.swing.JComboBox();
        cmbImeil = new javax.swing.JComboBox();
        jPanel4 = new javax.swing.JPanel();
        rbtRango = new javax.swing.JRadioButton();
        txtEntre = new javax.swing.JTextField();
        lbNit15 = new javax.swing.JLabel();
        txtEntre1 = new javax.swing.JTextField();
        rbtFechas = new javax.swing.JRadioButton();
        dtInicio = new datechooser.beans.DateChooserCombo();
        dtFinal = new datechooser.beans.DateChooserCombo();
        lbNit20 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();

        pnlFormulario.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        btnExcel.setBackground(new java.awt.Color(255, 255, 255));
        grupoTipo.add(btnExcel);
        btnExcel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnExcel.setText("Hoja de calculo");
        btnExcel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnExcel.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        btnImpresa.setBackground(new java.awt.Color(255, 255, 255));
        grupoTipo.add(btnImpresa);
        btnImpresa.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnImpresa.setSelected(true);
        btnImpresa.setText("En Pantalla");
        btnImpresa.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnImpresa.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        lbNit12.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbNit12.setText("Versión:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(128, 128, 128)
                .addComponent(lbNit12)
                .addGap(18, 18, 18)
                .addComponent(btnImpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnExcel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lbNit12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnImpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(5, 5, 5))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        btnDetallado.setBackground(new java.awt.Color(46, 204, 113));
        btnDetallado.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        btnDetallado.setText("DETALLADO");
        btnDetallado.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDetallado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetalladoActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(46, 204, 113));
        jButton2.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jButton2.setText("GENERAL");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(46, 204, 113));
        jButton3.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jButton3.setText("GRAFICOS");
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDetallado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDetallado, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE))
                .addGap(15, 15, 15))
        );

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));

        lbNit.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit.setText("Proveedor");

        lbNit10.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit10.setText("Grupo");

        lbTerminal.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbTerminal.setText("Producto");

        txtProveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtProveedorKeyReleased(evt);
            }
        });

        txtGrupo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGrupoActionPerformed(evt);
            }
        });
        txtGrupo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtGrupoKeyReleased(evt);
            }
        });

        txtCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoActionPerformed(evt);
            }
        });
        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoKeyReleased(evt);
            }
        });

        lbTerminal1.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbTerminal1.setText("Color");

        txtColor.setBackground(new java.awt.Color(255, 255, 204));
        txtColor.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtColor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtColor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtColorKeyReleased(evt);
            }
        });

        lbImei.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbImei.setText("Imei");

        txtImeil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtImeilActionPerformed(evt);
            }
        });
        txtImeil.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtImeilKeyReleased(evt);
            }
        });

        lbBodega.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbBodega.setText("Bodega");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbNit, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
                    .addComponent(lbNit10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbTerminal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtProveedor)
                    .addComponent(txtGrupo)
                    .addComponent(txtCodigo)
                    .addComponent(lbTerminal1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtColor)
                    .addComponent(lbImei, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtImeil)
                    .addComponent(lbBodega, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbBodegas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(lbNit)
                .addGap(0, 0, 0)
                .addComponent(txtProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(lbNit10)
                .addGap(0, 0, 0)
                .addComponent(txtGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(lbTerminal)
                .addGap(0, 0, 0)
                .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(lbTerminal1)
                .addGap(0, 0, 0)
                .addComponent(txtColor, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(lbImei)
                .addGap(0, 0, 0)
                .addComponent(txtImeil, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbBodega)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbBodegas, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));

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

        btnAggColor.setBackground(new java.awt.Color(204, 204, 204));
        btnAggColor.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnAggColor.setForeground(new java.awt.Color(255, 255, 255));
        btnAggColor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/agregar.png"))); // NOI18N
        btnAggColor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAggColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAggColorActionPerformed(evt);
            }
        });

        btnRemColor.setBackground(new java.awt.Color(204, 204, 204));
        btnRemColor.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnRemColor.setForeground(new java.awt.Color(153, 0, 0));
        btnRemColor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/borrar2.png"))); // NOI18N
        btnRemColor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRemColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemColorActionPerformed(evt);
            }
        });

        btnAggImeil.setBackground(new java.awt.Color(204, 204, 204));
        btnAggImeil.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnAggImeil.setForeground(new java.awt.Color(255, 255, 255));
        btnAggImeil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/agregar.png"))); // NOI18N
        btnAggImeil.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAggImeil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAggImeilActionPerformed(evt);
            }
        });

        btnRemImeil.setBackground(new java.awt.Color(204, 204, 204));
        btnRemImeil.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnRemImeil.setForeground(new java.awt.Color(153, 0, 0));
        btnRemImeil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/borrar2.png"))); // NOI18N
        btnRemImeil.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRemImeil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemImeilActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(btnAggProv, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRemProv))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(btnAggProd1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRemProd1))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(btnAggProd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRemProd))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(btnAggColor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRemColor))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(btnAggImeil)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRemImeil)))
                .addContainerGap(11, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAggProv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRemProv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(33, 33, 33)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRemProd1)
                    .addComponent(btnAggProd1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRemProd)
                    .addComponent(btnAggProd, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRemColor)
                    .addComponent(btnAggColor, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRemImeil)
                    .addComponent(btnAggImeil, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));

        cmbProveedor.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        cmbProducto1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        cmbProducto.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        cmbColor.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        cmbImeil.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbProveedor, 0, 214, Short.MAX_VALUE)
                    .addComponent(cmbProducto1, 0, 214, Short.MAX_VALUE)
                    .addComponent(cmbProducto, 0, 214, Short.MAX_VALUE)
                    .addComponent(cmbColor, 0, 214, Short.MAX_VALUE)
                    .addComponent(cmbImeil, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(cmbProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(cmbProducto1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(cmbProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(cmbColor, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(cmbImeil, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        rbtRango.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        rbtRango.setText("Entre rangos de inventario");

        txtEntre.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtEntre.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        lbNit15.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lbNit15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit15.setText("Y");

        txtEntre1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtEntre1.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        rbtFechas.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        rbtFechas.setText("Entre rangos de fechas de compra");

        dtInicio.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 12));

        dtFinal.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 12));

        lbNit20.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lbNit20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit20.setText("Y");

        jCheckBox1.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jCheckBox1.setSelected(true);
        jCheckBox1.setText("SIN INVENTARIO");

        jCheckBox2.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox2.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jCheckBox2.setSelected(true);
        jCheckBox2.setText("BAJA");

        jCheckBox3.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox3.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jCheckBox3.setSelected(true);
        jCheckBox3.setText("MEDIA");

        jCheckBox4.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox4.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jCheckBox4.setSelected(true);
        jCheckBox4.setText("ALTA");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(rbtFechas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rbtRango, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(dtInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(txtEntre, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE))
                .addGap(5, 5, 5)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbNit15, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNit20, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(dtFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(txtEntre1, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE))
                .addGap(83, 83, 83))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(122, 122, 122)
                .addComponent(jCheckBox1)
                .addGap(18, 18, 18)
                .addComponent(jCheckBox2)
                .addGap(18, 18, 18)
                .addComponent(jCheckBox3)
                .addGap(18, 18, 18)
                .addComponent(jCheckBox4)
                .addContainerGap(173, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtEntre1)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rbtRango)
                        .addComponent(txtEntre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbNit15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(rbtFechas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dtInicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dtFinal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbNit20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(26, 26, 26)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox1)
                    .addComponent(jCheckBox2)
                    .addComponent(jCheckBox3)
                    .addComponent(jCheckBox4))
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout pnlFormularioLayout = new javax.swing.GroupLayout(pnlFormulario);
        pnlFormulario.setLayout(pnlFormularioLayout);
        pnlFormularioLayout.setHorizontalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnlFormularioLayout.createSequentialGroup()
                            .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlFormularioLayout.setVerticalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            .addGroup(layout.createSequentialGroup()
                .addComponent(scrFormulario, javax.swing.GroupLayout.DEFAULT_SIZE, 725, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtProveedorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProveedorKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cargarProveedor(txtProveedor.getText());
        }
    }//GEN-LAST:event_txtProveedorKeyReleased

    private void txtCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoActionPerformed

    private void txtCodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cargarProducto(txtCodigo.getText());
        }
    }//GEN-LAST:event_txtCodigoKeyReleased

    private void txtGrupoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGrupoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGrupoActionPerformed

    private void txtGrupoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGrupoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cargarGrupo(txtGrupo.getText());
        }
    }//GEN-LAST:event_txtGrupoKeyReleased

    private void txtColorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtColorKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String color = txtColor.getText();

            if (!txtColor.getText().equals("")) {
                btnAggColorActionPerformed(null);
            } else {
                ventanaColores1(txtColor.getText());
            }
        } else {
            txtColor.setText("");
        }
    }//GEN-LAST:event_txtColorKeyReleased

    private void btnAggProd1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAggProd1ActionPerformed
        if (txtGrupo.getText().equals("")) {
            metodos.msgError(null, "Cargue primero un grupo");
        } else {

            if (yaEsta(cmbProducto1, txtGrupo.getText())) {
                metodos.msgAdvertencia(null, "Ya cargo este producto");
            } else {
                cmbProducto1.addItem(txtGrupo.getText());
                cmbProducto1.setSelectedItem(txtGrupo.getText());
            }

            txtGrupo.setText("");
        }
    }//GEN-LAST:event_btnAggProd1ActionPerformed

    private void btnRemProd1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemProd1ActionPerformed
        cmbProducto1.removeItem(cmbProducto1.getSelectedItem());
    }//GEN-LAST:event_btnRemProd1ActionPerformed

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

    private void btnAggColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAggColorActionPerformed
        if (txtColor.getText().equals("")) {
            metodos.msgError(null, "Cargue primero un color");
        } else {

            if (yaEsta(cmbColor, txtColor.getText())) {
                metodos.msgAdvertencia(null, "Ya cargo este imeil");
            } else {
                cmbColor.addItem(txtColor.getText());
                cmbColor.setSelectedItem(txtColor.getText());
            }

            txtColor.setText("");
        }
    }//GEN-LAST:event_btnAggColorActionPerformed

    private void btnRemColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemColorActionPerformed
        cmbColor.removeItem(cmbColor.getSelectedItem());
    }//GEN-LAST:event_btnRemColorActionPerformed

    private void txtImeilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtImeilActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtImeilActionPerformed

    private void txtImeilKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtImeilKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtImeilKeyReleased

    private void btnAggImeilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAggImeilActionPerformed
        if (txtImeil.getText().equals("")) {
            metodos.msgError(null, "Cargue primero un imeil");
        } else {

            if (yaEsta(cmbImeil, txtImeil.getText())) {
                metodos.msgAdvertencia(null, "Ya cargo este imeil");
            } else {
                cmbImeil.addItem(txtImeil.getText());
                cmbImeil.setSelectedItem(txtImeil.getText());
            }

            txtImeil.setText("");
        }
    }//GEN-LAST:event_btnAggImeilActionPerformed

    private void btnRemImeilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemImeilActionPerformed
        cmbImeil.removeItem(cmbImeil.getSelectedItem());
    }//GEN-LAST:event_btnRemImeilActionPerformed

    private void btnDetalladoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetalladoActionPerformed
        String tipo = "";

        if (metodos.msgPregunta(null, "¿Esta seguro?") == 0) {

            String sql = "where ";

            if (cmbProveedor.getItemCount() > 0) {
                sql = sql + " (";
                for (int i = 0; i < cmbProveedor.getItemCount(); i++) {
                    sql = sql + " Proveedor='" + cmbProveedor.getItemAt(i) + "' OR";
                }
                sql = sql.substring(0, sql.length() - 2) + ") AND";
            }

            if (cmbImeil.getItemCount() > 0) {
                sql = sql + " (";
                for (int i = 0; i < cmbImeil.getItemCount(); i++) {
                    sql = sql + " imei ='" + cmbImeil.getItemAt(i) + "' OR";
                }
                sql = sql.substring(0, sql.length() - 2) + ") AND";
            }

            if (cmbColor.getItemCount() > 0) {
                sql = sql + " (";
                for (int i = 0; i < cmbColor.getItemCount(); i++) {
                    sql = sql + " color ='" + cmbColor.getItemAt(i) + "' OR";
                }
                sql = sql.substring(0, sql.length() - 2) + ") AND";
            }

            String codigoBodega = "";
            if (instancias.getConfiguraciones().isInventarioBodegas()) {
                String bodega = instancias.getSql().getBaseBodega(cmbBodegas.getSelectedItem().toString());
                if (bodega.equals("bdProductos")) {
                    codigoBodega = "123-22";
                } else if (bodega.equals("bdProductosBodega1")) {
                    codigoBodega = "BODEGA-1";
                } else if (bodega.equals("bdProductosBodega2")) {
                    codigoBodega = "BODEGA-2";
                } else if (bodega.equals("bdProductosBodega3")) {
                    codigoBodega = "BODEGA-3";
                } else if (bodega.equals("bdProductosBodega4")) {
                    codigoBodega = "BODEGA-4";
                }
            } else {
                codigoBodega = "123-22";
            }

            sql = sql + " (bodega = '" + codigoBodega + "') AND";

            if (cmbProducto.getItemCount() > 0) {
                sql = sql + " (";
                for (int i = 0; i < cmbProducto.getItemCount(); i++) {
                    sql = sql + " idSistema ='" + cmbProducto.getItemAt(i) + "' OR";
                }
                sql = sql.substring(0, sql.length() - 2) + ") AND";
            }

            if (cmbProducto1.getItemCount() > 0) {
                sql = sql + " (";
                for (int i = 0; i < cmbProducto1.getItemCount(); i++) {
                    sql = sql + " Grupo='" + cmbProducto1.getItemAt(i) + "' OR";
                }
                sql = sql.substring(0, sql.length() - 2) + ")";
            }

            System.out.println(sql.substring(sql.length() - 3, sql.length()));

            if (sql.substring(sql.length() - 3, sql.length()).equals("AND")) {
                sql = sql.substring(0, sql.length() - 3);
            }

            if (sql.length() < 7) {
                sql = "";
            }

            if (btnExcel.isSelected()) {
                tipo = "xls";
                instancias.setRutaAguardar(metodos.obtenerRuta(null, "Reporte inventario de productos detallado " + metodosGenerales.dia() + " de " + metodosGenerales.mesEnPalabra() + " del " + metodosGenerales.anho()));
            }

            instancias.getReporte().verInventarioDetallado(sql, tipo);
        }
    }//GEN-LAST:event_btnDetalladoActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
//        String sql = "fechaFactura <= #" + metodosGenerales.fecha() + "# ";
//        if (metodos.msgPregunta(null, "¿Desea imprimir reporte de venta por producto de clientes?") == 0) {
//            instancias.getReporte().ver_RepClienteVentaProd(consecutivo, instancias.getInformacionEmpresa(), false, sql);
//        }

        String tipo = "";
        if (!jCheckBox1.isSelected() && !jCheckBox2.isSelected() && !jCheckBox3.isSelected() && !jCheckBox4.isSelected()) {
            metodos.msgError(null, "Debe seleccionar almenos un estado");
            return;
        }
        if (metodos.msgPregunta(null, "¿Esta seguro?") == 0) {

            String sql = "", base = "";
            if (instancias.getConfiguraciones().isInventarioBodegas()) {
                String bodega = instancias.getSql().getBaseBodega(cmbBodegas.getSelectedItem().toString());
                if (bodega.equals("bdProductos")) {
                    base = "inventario ";
                } else if (bodega.equals("bdProductosBodega1")) {
                    base = "inventario1 ";
                } else if (bodega.equals("bdProductosBodega2")) {
                    base = "inventario2 ";
                } else if (bodega.equals("bdProductosBodega3")) {
                    base = "inventario3 ";
                } else if (bodega.equals("bdProductosBodega4")) {
                    base = "inventario4 ";
                }
            } else {
                base = "inventario ";
            }

            //instancias.getSql().modificarFechaHistorico(metodos.desdeDate(dtFinal.getCurrent()));
            sql = sql + "where (manejaInventario = true) AND";

            if (rbtRango.isSelected()) {
                String inicio = txtEntre.getText();
                String final1 = txtEntre1.getText();

                if (inicio.equals("") || final1.equals("")) {
                    metodos.msgError(null, "Debe ingresar el rango de inventario");
                    return;
                }
                sql = sql + " (inv >= " + inicio + " AND inv <= " + final1 + " ) AND ";
            }

            if (jCheckBox1.isSelected()) {
                sql = sql + " (estado='SIN INVENTARIO' OR";
            }

            if (jCheckBox2.isSelected()) {
                if (sql.equals("where (manejaInventario = true) AND")) {
                    sql = sql + " (estado='BAJO' OR";
                } else {
                    sql = sql + " estado='BAJO' OR";
                }
            }

            if (jCheckBox3.isSelected()) {
                if (sql.equals("where (manejaInventario = true) AND")) {
                    sql = sql + " (estado='MEDIO' OR";
                } else {
                    sql = sql + " estado='MEDIO' OR";
                }
            }

            if (jCheckBox4.isSelected()) {
                if (sql.equals("where (manejaInventario = true) AND")) {
                    sql = sql + " (estado='ALTO' OR";
                } else {
                    sql = sql + " estado='ALTO' OR";
                }
            }
            if (!sql.equals("where (manejaInventario = true) AND")) {
                sql = sql.substring(0, sql.length() - 2) + ") AND";
            }

            if (cmbProveedor.getItemCount() > 0) {
                sql = sql + " (";
                for (int i = 0; i < cmbProveedor.getItemCount(); i++) {
                    sql = sql + " Proveedor='" + cmbProveedor.getItemAt(i) + "' OR";
                }
                sql = sql.substring(0, sql.length() - 2) + ") AND";
            }

            if (rbtFechas.isSelected()) {
                String ini = metodos.desdeDate(dtInicio.getCurrent()), fin = metodos.desdeDate(dtFinal.getCurrent());
                sql = sql + " ((fecha)>= '" + ini + "' And (fecha)<= '" + fin + "' )";
            }

            if (cmbProducto.getItemCount() > 0) {
                sql = sql + " (";
                for (int i = 0; i < cmbProducto.getItemCount(); i++) {
                    sql = sql + " Codigo='" + cmbProducto.getItemAt(i) + "' OR";
                }
                sql = sql.substring(0, sql.length() - 2) + ") AND";
            }

            if (cmbProducto1.getItemCount() > 0) {
                sql = sql + " (";
                for (int i = 0; i < cmbProducto1.getItemCount(); i++) {
                    sql = sql + " Grupo='" + cmbProducto1.getItemAt(i) + "' OR";
                }
                sql = sql.substring(0, sql.length() - 2) + ")";
            }

            if (sql.substring(sql.length() - 3, sql.length()).equals("AND")) {
                sql = sql.substring(0, sql.length() - 3);
            }
            if (sql.length() < 7) {
                sql = "";
            }

            System.out.println("sql: " + sql);
            //actualizarHistorico();
            if (btnExcel.isSelected()) {
                tipo = "xls";
                instancias.setRutaAguardar(metodos.obtenerRuta(null, "Reporte inventario de productos " + metodosGenerales.dia() + " de " + metodosGenerales.mesEnPalabra() + " del " + metodosGenerales.anho()));
            }
            instancias.getReporte().verHistorico(base + " " + sql, tipo);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String tipo = "";
        if (!jCheckBox1.isSelected() && !jCheckBox2.isSelected() && !jCheckBox3.isSelected() && !jCheckBox4.isSelected()) {
            metodos.msgError(null, "Debe seleccionar almenos un estado");
            return;
        }
        if (metodos.msgPregunta(null, "¿Esta seguro?") == 0) {

            String sql = "", base = "";
            if (instancias.getConfiguraciones().isInventarioBodegas()) {
                String bodega = instancias.getSql().getBaseBodega(cmbBodegas.getSelectedItem().toString());
                if (bodega.equals("bdProductos")) {
                    base = "inventario ";
                } else if (bodega.equals("bdProductosBodega1")) {
                    base = "inventario1 ";
                } else if (bodega.equals("bdProductosBodega2")) {
                    base = "inventario2 ";
                } else if (bodega.equals("bdProductosBodega3")) {
                    base = "inventario3 ";
                } else if (bodega.equals("bdProductosBodega4")) {
                    base = "inventario4 ";
                }
            } else {
                base = "inventario ";
            }

            //instancias.getSql().modificarFechaHistorico(metodos.desdeDate(dtFinal.getCurrent()));
            sql = sql + "where (manejaInventario = true) AND";

            if (rbtRango.isSelected()) {
                String inicio = txtEntre.getText();
                String final1 = txtEntre1.getText();

                if (inicio.equals("") || final1.equals("")) {
                    metodos.msgError(null, "Debe ingresar el rango de inventario");
                    return;
                }
                sql = sql + " (inv >= " + inicio + " AND inv <= " + final1 + " ) AND ";
            }

            if (jCheckBox1.isSelected()) {
                sql = sql + " (estado='SIN INVENTARIO' OR";
            }

            if (jCheckBox2.isSelected()) {
                if (sql.equals("where (manejaInventario = true) AND")) {
                    sql = sql + " (estado='BAJO' OR";
                } else {
                    sql = sql + " estado='BAJO' OR";
                }
            }

            if (jCheckBox3.isSelected()) {
                if (sql.equals("where (manejaInventario = true) AND")) {
                    sql = sql + " (estado='MEDIO' OR";
                } else {
                    sql = sql + " estado='MEDIO' OR";
                }
            }

            if (jCheckBox4.isSelected()) {
                if (sql.equals("where (manejaInventario = true) AND")) {
                    sql = sql + " (estado='ALTO' OR";
                } else {
                    sql = sql + " estado='ALTO' OR";
                }
            }
            if (!sql.equals("where (manejaInventario = true) AND")) {
                sql = sql.substring(0, sql.length() - 2) + ") AND";
            }

            if (cmbProveedor.getItemCount() > 0) {
                sql = sql + " (";
                for (int i = 0; i < cmbProveedor.getItemCount(); i++) {
                    sql = sql + " Proveedor='" + cmbProveedor.getItemAt(i) + "' OR";
                }
                sql = sql.substring(0, sql.length() - 2) + ") AND";
            }

            if (rbtFechas.isSelected()) {
                String ini = metodos.desdeDate(dtInicio.getCurrent()), fin = metodos.desdeDate(dtFinal.getCurrent());
                sql = sql + " ((fecha)>= '" + ini + "' And (fecha)<= '" + fin + "' )";
            }

            if (cmbProducto.getItemCount() > 0) {
                sql = sql + " (";
                for (int i = 0; i < cmbProducto.getItemCount(); i++) {
                    sql = sql + " Codigo='" + cmbProducto.getItemAt(i) + "' OR";
                }
                sql = sql.substring(0, sql.length() - 2) + ") AND";
            }

            if (cmbProducto1.getItemCount() > 0) {
                sql = sql + " (";
                for (int i = 0; i < cmbProducto1.getItemCount(); i++) {
                    sql = sql + " Grupo='" + cmbProducto1.getItemAt(i) + "' OR";
                }
                sql = sql.substring(0, sql.length() - 2) + ")";
            }

            if (sql.substring(sql.length() - 3, sql.length()).equals("AND")) {
                sql = sql.substring(0, sql.length() - 3);
            }
            if (sql.length() < 7) {
                sql = "";
            }

            if (btnExcel.isSelected()) {
                tipo = "xls";
                instancias.setRutaAguardar(metodos.obtenerRuta(null, "Reporte inventario de productos " + metodosGenerales.dia() + " de " + metodosGenerales.mesEnPalabra() + " del " + metodosGenerales.anho()));
            }

            instancias.getReporte().ver_RepProdImagenes(base + " " + sql, instancias.getInformacionEmpresa(), false);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    public void ventanaColores1(String nit) {
        buscColores buscar = new buscColores(instancias.getMenu(), false);
        buscar.setLocationRelativeTo(null);
        instancias.setBuscColores(buscar);
        instancias.setCampoActual(txtColor);
        txtColor.requestFocus();
        buscar.setInstancia(instancias);
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

    public void cargarGrupo(String nit) {
        ndGrupo nodo = instancias.getSql().getDatosGrupo2(nit);

        if (nodo.getCodigo() != null) {
            btnAggProd1ActionPerformed(null);
            return;
        }
        ventanaGrupo(nit);
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
    
    public void ventanaGrupo(String nit) {
        buscGrupos buscar = new buscGrupos(instancias.getMenu(), true, "");
        buscar.setLocationRelativeTo(null);
        instancias.setBuscGrupos(buscar);
        instancias.setCampoActual(txtGrupo);
        txtGrupo.requestFocus();
        buscar.noEncontrado(nit);
        buscar.show();
    }

    public void ventanaProducto(String nit) {
        buscProductos buscar = new buscProductos(instancias.getMenu(), true, false, "", "productos1");
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

    public void actualizar() {
        Object[][] productos = instancias.getSql().getProductos();

        for (Object[] producto : productos) {
            Object[][] datos = instancias.getSql().getCostosProducto(producto[0].toString());
            BigDecimal cantidad = big.getBigDecimal(instancias.getSql().getInvProd(producto[0].toString())[0]);
            BigDecimal aCostear = BigDecimal.ZERO;
            BigDecimal costo = BigDecimal.ZERO;

            if (cantidad == null) {
                cantidad = BigDecimal.ZERO;
            }

            if (costo.compareTo(cantidad) == -1) {
                for (Object[] dato : datos) {
                    BigDecimal cant = big.getBigDecimal(dato[0]);
                    BigDecimal val = big.getBigDecimal(dato[1]);
                    if (aCostear.add(cant).compareTo(cantidad) == -1) {
                        aCostear = aCostear.add(cant);
                        costo = costo.add(val.multiply(cant));
                    } else if (aCostear.add(cant).compareTo(cantidad) == 0) {
                        aCostear = cantidad;
                        costo = costo.add(val.multiply(cant));
                        break;
                    } else {
                        aCostear = cantidad.subtract(aCostear);
                        costo = costo.add(val.multiply(aCostear));
                        break;
                    }
                }
            }

            if (cantidad.compareTo(BigDecimal.ZERO) != 0) {
                costo = costo.divide(cantidad, 0, RoundingMode.HALF_UP);
            }

            instancias.getSql().modificarCosto(producto[0].toString(), costo.toString());
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAggColor;
    private javax.swing.JButton btnAggImeil;
    private javax.swing.JButton btnAggProd;
    private javax.swing.JButton btnAggProd1;
    private javax.swing.JButton btnAggProv;
    private javax.swing.JButton btnDetallado;
    private javax.swing.JRadioButton btnExcel;
    private javax.swing.JRadioButton btnImpresa;
    private javax.swing.JButton btnRemColor;
    private javax.swing.JButton btnRemImeil;
    private javax.swing.JButton btnRemProd;
    private javax.swing.JButton btnRemProd1;
    private javax.swing.JButton btnRemProv;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cmbBodegas;
    private javax.swing.JComboBox cmbColor;
    private javax.swing.JComboBox cmbImeil;
    private javax.swing.JComboBox cmbProducto;
    private javax.swing.JComboBox cmbProducto1;
    private javax.swing.JComboBox cmbProveedor;
    private datechooser.beans.DateChooserCombo dtFinal;
    private datechooser.beans.DateChooserCombo dtInicio;
    private javax.swing.ButtonGroup grupoTipo;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JLabel lbBodega;
    private javax.swing.JLabel lbImei;
    private javax.swing.JLabel lbNit;
    private javax.swing.JLabel lbNit10;
    private javax.swing.JLabel lbNit12;
    private javax.swing.JLabel lbNit15;
    private javax.swing.JLabel lbNit20;
    private javax.swing.JLabel lbTerminal;
    private javax.swing.JLabel lbTerminal1;
    private javax.swing.JPanel pnlFormulario;
    private javax.swing.JRadioButton rbtFechas;
    private javax.swing.JRadioButton rbtRango;
    private javax.swing.JScrollPane scrFormulario;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtColor;
    private javax.swing.JTextField txtEntre;
    private javax.swing.JTextField txtEntre1;
    private javax.swing.JTextField txtGrupo;
    private javax.swing.JTextField txtImeil;
    private javax.swing.JTextField txtProveedor;
    // End of variables declaration//GEN-END:variables
}
