package formularios.Ventas;

import dao.InicioSesion.DaoInicioSesion;
import Modelo.InicioSesion.Terminal;
import clases.Instancias;
import clases.metodosGenerales;
import clases.productos.ndProducto;
import Modelo.Terceros.ModeloContacto;
import Vista.Productos.VistaBuscadorProductos;
import formularios.terceros.buscBodegas;
import formularios.terceros.buscClientes;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.JComboBox;

public class infRepSepares extends javax.swing.JPanel {

    private final DaoInicioSesion daoInicioSesion = new DaoInicioSesion();
    metodosGenerales metodos = new metodosGenerales();
    private Instancias instancias;

    public infRepSepares() {
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

        if (!instancias.getConfiguraciones().isInventarioBodegas()) {
            btnAggBodega.setVisible(false);
            btnRemoverBodega.setVisible(false);
            txtBodega.setVisible(false);
            cmbBodega.setVisible(false);
            lbBodega.setVisible(false);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        scrFormulario = new javax.swing.JScrollPane();
        pnlFormulario = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        lbNit1 = new javax.swing.JLabel();
        lbNit12 = new javax.swing.JLabel();
        txtCliente = new javax.swing.JTextField();
        cmbTerminal = new javax.swing.JComboBox();
        lbTerminal = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        lbBodega = new javax.swing.JLabel();
        txtBodega = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        btnAggProd = new javax.swing.JButton();
        btnRemProd = new javax.swing.JButton();
        btnAggCliente = new javax.swing.JButton();
        btnRemCliente = new javax.swing.JButton();
        btnAggTerminal = new javax.swing.JButton();
        btnRemTerminal = new javax.swing.JButton();
        btnAggBodega = new javax.swing.JButton();
        btnRemoverBodega = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        cmbCientes = new javax.swing.JComboBox();
        cmbProducto = new javax.swing.JComboBox();
        cmbTerminal2 = new javax.swing.JComboBox();
        cmbBodega = new javax.swing.JComboBox();
        jPanel6 = new javax.swing.JPanel();
        lbNit9 = new javax.swing.JLabel();
        dtInicio = new datechooser.beans.DateChooserCombo();
        dtFinal = new datechooser.beans.DateChooserCombo();
        lbNit13 = new javax.swing.JLabel();
        btnImpresa = new javax.swing.JRadioButton();
        btnExcel = new javax.swing.JRadioButton();
        lbNit11 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();

        pnlFormulario.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        lbNit1.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit1.setText("CC/Nit:");

        lbNit12.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit12.setText("Producto:");

        txtCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtClienteKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtClienteKeyReleased(evt);
            }
        });

        cmbTerminal.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N

        lbTerminal.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbTerminal.setText("Terminal:");

        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodigoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoKeyReleased(evt);
            }
        });

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

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCliente, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cmbTerminal, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbNit1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbNit12, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbTerminal, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 143, Short.MAX_VALUE))
                    .addComponent(txtCodigo)
                    .addComponent(lbBodega, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtBodega))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(lbNit1)
                .addGap(4, 4, 4)
                .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(lbNit12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(lbTerminal)
                .addGap(3, 3, 3)
                .addComponent(cmbTerminal, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(lbBodega)
                .addGap(0, 0, 0)
                .addComponent(txtBodega, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

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

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(btnAggCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRemCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(btnAggProd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRemProd))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(btnAggTerminal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRemTerminal))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(btnAggBodega)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRemoverBodega)))
                .addContainerGap(11, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAggCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRemCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(37, 37, 37)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRemProd)
                    .addComponent(btnAggProd, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRemTerminal)
                    .addComponent(btnAggTerminal, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRemoverBodega)
                    .addComponent(btnAggBodega, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        cmbCientes.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        cmbProducto.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        cmbTerminal2.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        cmbBodega.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbCientes, 0, 214, Short.MAX_VALUE)
                    .addComponent(cmbProducto, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbTerminal2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbBodega, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(cmbCientes, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(cmbProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(cmbTerminal2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cmbBodega, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        lbNit9.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lbNit9.setText("Fecha final");

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

        btnExcel.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(btnExcel);
        btnExcel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnExcel.setText("Hoja de calculo");
        btnExcel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnExcel.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        lbNit11.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lbNit11.setText("Fecha inicial:");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbNit13)
                    .addComponent(lbNit11, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnImpresa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dtInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(35, 35, 35)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(lbNit9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dtFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnExcel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(dtInicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnImpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lbNit9, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                                .addComponent(dtFinal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(lbNit11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(lbNit13))
                            .addComponent(btnExcel))))
                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jButton6.setBackground(new java.awt.Color(46, 204, 113));
        jButton6.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jButton6.setText("Generar Totales");
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setBackground(new java.awt.Color(46, 204, 113));
        jButton7.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jButton7.setText("Generar Detallado");
        jButton7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE))
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
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlFormularioLayout.setVerticalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
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

    private void txtClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClienteKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtClienteKeyPressed

    private void txtClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClienteKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cargarCliente(txtCliente.getText());
        }
    }//GEN-LAST:event_txtClienteKeyReleased

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
        }
    }//GEN-LAST:event_btnAggClienteActionPerformed

    private void btnRemClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemClienteActionPerformed
        cmbCientes.removeItem(cmbCientes.getSelectedItem());
    }//GEN-LAST:event_btnRemClienteActionPerformed

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

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
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

            if (cmbCientes.getItemCount() > 0) {
                sql = sql + " (";

                for (int i = 0; i < cmbCientes.getItemCount(); i++) {
                    sql = sql + " cliente='" + cmbCientes.getItemAt(i) + "' OR";
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

            String ini = metodos.desdeDate(dtInicio.getCurrent()), fin = metodos.desdeDate(dtFinal.getCurrent());
            fin = metodos.sumarFecha(metodos.fecha(fin), 1);
            fin = metodos.fechaConsulta(fin);
            sql = sql + " (((fechaFactura)>= '" + ini + "') And ((fechaFactura)<='" + fin + "'))";

            System.out.println(sql);

            // sql = sql + " ((fechaFactura)>=#" + "01/04/2014" + "# And (fechaFactura)<=#" + "4/5/2014" + "#)";
            String enca = "";

            dtInicio.setFormat(0);
            dtFinal.setFormat(0);

            if (dtInicio.getText().equals(dtFinal.getText())) {
                enca = "Separes del " + ini;
            } else {
                enca = "Separes entre el " + ini + " y el " + fin;
            }

            dtInicio.setFormat(2);
            dtFinal.setFormat(2);

            String tipo = "";

            if (btnExcel.isSelected()) {
                instancias.setRutaAguardar(metodos.obtenerRuta(null, "Reporte de separes corto " + metodosGenerales.dia() + " de " + metodosGenerales.mesEnPalabra() + " del " + metodosGenerales.anho()));
                tipo = "xls";
            }

            instancias.getReporte().ver_RepSepares2(sql, enca, tipo);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
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

            if (cmbCientes.getItemCount() > 0) {
                sql = sql + " (";

                for (int i = 0; i < cmbCientes.getItemCount(); i++) {
                    sql = sql + " cliente='" + cmbCientes.getItemAt(i) + "' OR";
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

            String ini = metodos.desdeDate(dtInicio.getCurrent()), fin = metodos.desdeDate(dtFinal.getCurrent());
            fin = metodos.sumarFecha(metodos.fecha(fin), 1);
            fin = metodos.fechaConsulta(fin);

            sql = sql + " (((fechaFactura)>= '" + ini + "') And ((fechaFactura)<='" + fin + "'))";

            System.out.println(sql);

            // sql = sql + " ((fechaFactura)>=#" + "01/04/2014" + "# And (fechaFactura)<=#" + "4/5/2014" + "#)";
            String enca = "";

            dtInicio.setFormat(0);
            dtFinal.setFormat(0);

            if (dtInicio.getText().equals(dtFinal.getText())) {
                enca = "Separes del " + ini;
            } else {
                enca = "Separes entre el " + ini + " y el " + fin;
            }

            dtInicio.setFormat(2);
            dtFinal.setFormat(2);

            String tipo = "";

            if (btnExcel.isSelected()) {
                tipo = "xls";
                instancias.setRutaAguardar(metodos.obtenerRuta(null, "Reporte de Separes " + metodosGenerales.dia() + " de " + metodosGenerales.mesEnPalabra() + " del " + metodosGenerales.anho()));
            }

            instancias.getReporte().ver_RepSepares(sql, enca, tipo);
        }
    }//GEN-LAST:event_jButton7ActionPerformed

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
        buscClientes buscar = new buscClientes(instancias.getMenu(), true, false, null, "");
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
        VistaBuscadorProductos buscar = new VistaBuscadorProductos(instancias.getMenu(), true, false, "", "productos1");
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAggBodega;
    private javax.swing.JButton btnAggCliente;
    private javax.swing.JButton btnAggProd;
    private javax.swing.JButton btnAggTerminal;
    private javax.swing.JRadioButton btnExcel;
    private javax.swing.JRadioButton btnImpresa;
    private javax.swing.JButton btnRemCliente;
    private javax.swing.JButton btnRemProd;
    private javax.swing.JButton btnRemTerminal;
    private javax.swing.JButton btnRemoverBodega;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cmbBodega;
    private javax.swing.JComboBox cmbCientes;
    private javax.swing.JComboBox cmbProducto;
    private javax.swing.JComboBox cmbTerminal;
    private javax.swing.JComboBox cmbTerminal2;
    private datechooser.beans.DateChooserCombo dtFinal;
    private datechooser.beans.DateChooserCombo dtInicio;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbBodega;
    private javax.swing.JLabel lbNit1;
    private javax.swing.JLabel lbNit11;
    private javax.swing.JLabel lbNit12;
    private javax.swing.JLabel lbNit13;
    private javax.swing.JLabel lbNit9;
    private javax.swing.JLabel lbTerminal;
    private javax.swing.JPanel pnlFormulario;
    private javax.swing.JScrollPane scrFormulario;
    private javax.swing.JTextField txtBodega;
    private javax.swing.JTextField txtCliente;
    private javax.swing.JTextField txtCodigo;
    // End of variables declaration//GEN-END:variables
}
