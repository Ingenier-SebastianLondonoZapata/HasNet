package formularios.Ventas;

import clases.Instancias;
import Utilidades.BaseDatos.SQL;
import clases.big;
import clases.metodosGenerales;
import clases.productos.ndProducto;
import Modelo.Terceros.ModeloContacto;
import formularios.productos.buscGrupos;
import Vista.Productos.VistaBuscadorProductos;
import formularios.terceros.buscClientes;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

public class infIndicadoresVentas extends javax.swing.JPanel {

    metodosGenerales metodos = new metodosGenerales();
    private Instancias instancias;
    DefaultTableModel modelo;
    private Dimension dimBarra = null;

    public infIndicadoresVentas() {
        initComponents();

        pnlCombos.setVisible(false);

        modelo = (DefaultTableModel) tblResultados.getModel();
        instancias = Instancias.getInstancias();

        rdGeneral.setSelected(true);
        pnlCombos.setVisible(true);
        pnlPrincipal.setVisible(false);
        lbFiltro1.setText("Mes:");
        cmbFiltro.removeAllItems();
        cmbFiltro.addItem("Enero");
        cmbFiltro.addItem("Febrero");
        cmbFiltro.addItem("Marzo");
        cmbFiltro.addItem("Abril");
        cmbFiltro.addItem("Mayo");
        cmbFiltro.addItem("Junio");
        cmbFiltro.addItem("Julio");
        cmbFiltro.addItem("Agosto");
        cmbFiltro.addItem("Septiembre");
        cmbFiltro.addItem("Octubre");
        cmbFiltro.addItem("Noviembre");
        cmbFiltro.addItem("Diciembre");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoTipo = new javax.swing.ButtonGroup();
        buttonGroup1 = new javax.swing.ButtonGroup();
        scrFormulario = new javax.swing.JScrollPane();
        pnlFormulario = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblResultados = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        rdGeneral = new javax.swing.JRadioButton();
        rdPorGrupos = new javax.swing.JRadioButton();
        rdCantidadesProductos = new javax.swing.JRadioButton();
        rdPorMes = new javax.swing.JRadioButton();
        rdValoresProductos = new javax.swing.JRadioButton();
        rdPorCliente = new javax.swing.JRadioButton();
        rdPorVendedor = new javax.swing.JRadioButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        pnlPrincipal = new javax.swing.JPanel();
        txtCodGrupo = new javax.swing.JTextField();
        lbFiltro = new javax.swing.JLabel();
        btnRemGrupo = new javax.swing.JButton();
        cmbGrupo = new javax.swing.JComboBox();
        btnAggGrupo = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JSeparator();
        pnlCombos = new javax.swing.JPanel();
        lbFiltro1 = new javax.swing.JLabel();
        btnRemGrupo1 = new javax.swing.JButton();
        cmbFiltro1 = new javax.swing.JComboBox();
        btnAggFiltro1 = new javax.swing.JButton();
        cmbFiltro = new javax.swing.JComboBox();
        jSeparator3 = new javax.swing.JSeparator();
        btnGuardar1 = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();

        pnlFormulario.setBackground(new java.awt.Color(255, 255, 255));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Filtros", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 0, 14))); // NOI18N

        tblResultados.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        tblResultados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Prod", "Cliente", "Nombre", "Vendedor", "Grupo", "Proveedor", "Mes", "Cant", "Subtotal", "Descuentos", "IVA", "Impoconsumo", "Total", "Utilidad"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true, true, false, false, true, true, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblResultados.setRowHeight(24);
        tblResultados.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblResultados);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1083, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Menu", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 0, 14))); // NOI18N

        buttonGroup1.add(rdGeneral);
        rdGeneral.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        rdGeneral.setSelected(true);
        rdGeneral.setText("General");
        rdGeneral.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rdGeneral.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdGeneralItemStateChanged(evt);
            }
        });

        buttonGroup1.add(rdPorGrupos);
        rdPorGrupos.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        rdPorGrupos.setText("Grupos");
        rdPorGrupos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rdPorGrupos.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdPorGruposItemStateChanged(evt);
            }
        });

        buttonGroup1.add(rdCantidadesProductos);
        rdCantidadesProductos.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        rdCantidadesProductos.setText("Mayor venta cantidades de productos");
        rdCantidadesProductos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rdCantidadesProductos.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdCantidadesProductosItemStateChanged(evt);
            }
        });

        buttonGroup1.add(rdPorMes);
        rdPorMes.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        rdPorMes.setText("Productos por mes");
        rdPorMes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rdPorMes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdPorMesItemStateChanged(evt);
            }
        });

        buttonGroup1.add(rdValoresProductos);
        rdValoresProductos.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        rdValoresProductos.setText("Valores de venta de Productos");
        rdValoresProductos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rdValoresProductos.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdValoresProductosItemStateChanged(evt);
            }
        });

        buttonGroup1.add(rdPorCliente);
        rdPorCliente.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        rdPorCliente.setText("Clientes");
        rdPorCliente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rdPorCliente.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdPorClienteItemStateChanged(evt);
            }
        });
        rdPorCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdPorClienteActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdPorVendedor);
        rdPorVendedor.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        rdPorVendedor.setText("Vendedor");
        rdPorVendedor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rdPorVendedor.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdPorVendedorItemStateChanged(evt);
            }
        });
        rdPorVendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdPorVendedorActionPerformed(evt);
            }
        });

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addComponent(rdCantidadesProductos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE)
                    .addComponent(rdValoresProductos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(rdGeneral, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                            .addComponent(rdPorCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdPorVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdPorGrupos, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(rdPorMes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(5, 5, 5))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdPorGrupos, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdPorVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdPorCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(rdPorMes, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(rdCantidadesProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rdValoresProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Filtros", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 0, 14))); // NOI18N

        pnlPrincipal.setBackground(new java.awt.Color(255, 255, 255));

        txtCodGrupo.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtCodGrupo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodGrupoKeyReleased(evt);
            }
        });

        lbFiltro.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbFiltro.setText("Grupo:");

        btnRemGrupo.setBackground(new java.awt.Color(241, 148, 138));
        btnRemGrupo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnRemGrupo.setText("REMOVER ");
        btnRemGrupo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRemGrupo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemGrupoActionPerformed(evt);
            }
        });

        btnAggGrupo.setBackground(new java.awt.Color(46, 204, 113));
        btnAggGrupo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnAggGrupo.setText("AGREGAR");
        btnAggGrupo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAggGrupo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAggGrupoActionPerformed(evt);
            }
        });

        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout pnlPrincipalLayout = new javax.swing.GroupLayout(pnlPrincipal);
        pnlPrincipal.setLayout(pnlPrincipalLayout);
        pnlPrincipalLayout.setHorizontalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlPrincipalLayout.createSequentialGroup()
                        .addComponent(lbFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCodGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnAggGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRemGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlPrincipalLayout.setVerticalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPrincipalLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator4)
                    .addGroup(pnlPrincipalLayout.createSequentialGroup()
                        .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(cmbGrupo)
                                .addComponent(lbFiltro, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                            .addComponent(txtCodGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)
                        .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnAggGrupo, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                            .addComponent(btnRemGrupo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(5, 5, 5))
        );

        pnlCombos.setBackground(new java.awt.Color(255, 255, 255));

        lbFiltro1.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbFiltro1.setText("Mes:");

        btnRemGrupo1.setBackground(new java.awt.Color(241, 148, 138));
        btnRemGrupo1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnRemGrupo1.setText("REMOVER ");
        btnRemGrupo1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRemGrupo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemGrupo1ActionPerformed(evt);
            }
        });

        cmbFiltro1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        cmbFiltro1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbFiltro1ActionPerformed(evt);
            }
        });

        btnAggFiltro1.setBackground(new java.awt.Color(46, 204, 113));
        btnAggFiltro1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnAggFiltro1.setText("AGREGAR");
        btnAggFiltro1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAggFiltro1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAggFiltro1ActionPerformed(evt);
            }
        });

        cmbFiltro.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout pnlCombosLayout = new javax.swing.GroupLayout(pnlCombos);
        pnlCombos.setLayout(pnlCombosLayout);
        pnlCombosLayout.setHorizontalGroup(
            pnlCombosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCombosLayout.createSequentialGroup()
                .addGroup(pnlCombosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCombosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbFiltro1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCombosLayout.createSequentialGroup()
                        .addGap(208, 208, 208)
                        .addComponent(btnAggFiltro1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCombosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRemGrupo1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbFiltro1, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlCombosLayout.setVerticalGroup(
            pnlCombosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCombosLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(pnlCombosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator3)
                    .addGroup(pnlCombosLayout.createSequentialGroup()
                        .addGroup(pnlCombosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlCombosLayout.createSequentialGroup()
                                .addComponent(cmbFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 3, Short.MAX_VALUE))
                            .addGroup(pnlCombosLayout.createSequentialGroup()
                                .addGroup(pnlCombosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cmbFiltro1, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                    .addComponent(lbFiltro1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(3, 3, 3)))
                        .addGroup(pnlCombosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnAggFiltro1, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                            .addComponent(btnRemGrupo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(5, 5, 5))
        );

        btnGuardar1.setBackground(new java.awt.Color(204, 204, 204));
        btnGuardar1.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnGuardar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/graficos.png"))); // NOI18N
        btnGuardar1.setText("GENERAR GRAFICOS");
        btnGuardar1.setToolTipText("Ctrl+G");
        btnGuardar1.setBorder(null);
        btnGuardar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnGuardar1.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnGuardar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardar1ActionPerformed(evt);
            }
        });
        btnGuardar1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                btnGuardar1KeyReleased(evt);
            }
        });

        btnGuardar.setBackground(new java.awt.Color(204, 204, 204));
        btnGuardar.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/verGraficos.png"))); // NOI18N
        btnGuardar.setText("VER EN PANTALLA ");
        btnGuardar.setToolTipText("Ctrl+G");
        btnGuardar.setBorder(null);
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnGuardar.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        btnGuardar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                btnGuardarKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlCombos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardar1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(pnlPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlCombos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnGuardar1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlFormularioLayout = new javax.swing.GroupLayout(pnlFormulario);
        pnlFormulario.setLayout(pnlFormularioLayout);
        pnlFormularioLayout.setHorizontalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlFormularioLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlFormularioLayout.setVerticalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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

    private void rdPorClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdPorClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdPorClienteActionPerformed

    private void rdPorVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdPorVendedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdPorVendedorActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (metodos.msgPregunta(null, "¿Esta seguro?") == 0) {

            while (tblResultados.getRowCount() > 0) {
                modelo.removeRow(0);
            }

            if (rdPorGrupos.isSelected()) {

                String sql = "";
                if (cmbGrupo.getItemCount() > 0) {
                    sql = "where ";
                    for (int i = 0; i < cmbGrupo.getItemCount(); i++) {
                        sql = sql + " factura.Grupo = '" + cmbGrupo.getItemAt(i) + "' OR";
                    }

                    sql = sql.substring(0, sql.length() - 2);
                }

                Object[][] registros = instancias.getSql().getIndicadoresPorGrupo(sql);

                for (int i = 0; i < registros.length; i++) {
                    if (registros[i][0] != null) {
                        String grupo = "", nombreGrupo = "";

                        if (registros[i][4] == null || registros[i][4].toString().equals("")) {
                            grupo = "Sin-Grupo";
                        } else {
                            grupo = registros[i][4].toString();
                        }

                        if (registros[i][7] == null || registros[i][7].toString().equals("")) {
                            nombreGrupo = "Sin-Grupo";
                        } else {
                            nombreGrupo = registros[i][7].toString();
                        }

                        modelo.addRow(new Object[]{"", "", grupo, "", nombreGrupo, "", "", "", big.setMoneda(big.getBigDecimal(registros[i][0])), big.setMoneda(big.getBigDecimal(registros[i][3])),
                            big.setMoneda(big.getBigDecimal(registros[i][1])), big.setMoneda(big.getBigDecimal(registros[i][6])), big.setMoneda(big.getBigDecimal(registros[i][2])),
                            big.setMoneda(big.getBigDecimal(registros[i][5]))});
                    }
                }

                tblResultados.getColumnModel().getColumn(0).setMinWidth(0);
                tblResultados.getColumnModel().getColumn(0).setPreferredWidth(0);
                tblResultados.getColumnModel().getColumn(0).setMaxWidth(0);
                tblResultados.getColumnModel().getColumn(1).setMinWidth(0);
                tblResultados.getColumnModel().getColumn(1).setPreferredWidth(0);
                tblResultados.getColumnModel().getColumn(1).setMaxWidth(0);

                tblResultados.getColumnModel().getColumn(2).setMinWidth(250);
                tblResultados.getColumnModel().getColumn(2).setPreferredWidth(250);
                tblResultados.getColumnModel().getColumn(2).setMaxWidth(250);
                tblResultados.getColumnModel().getColumn(4).setMinWidth(250);
                tblResultados.getColumnModel().getColumn(4).setPreferredWidth(250);
                tblResultados.getColumnModel().getColumn(4).setMaxWidth(250);

                tblResultados.getColumnModel().getColumn(3).setMinWidth(0);
                tblResultados.getColumnModel().getColumn(3).setPreferredWidth(0);
                tblResultados.getColumnModel().getColumn(3).setMaxWidth(0);
                tblResultados.getColumnModel().getColumn(5).setMinWidth(0);
                tblResultados.getColumnModel().getColumn(5).setPreferredWidth(0);
                tblResultados.getColumnModel().getColumn(5).setMaxWidth(0);
                tblResultados.getColumnModel().getColumn(6).setMinWidth(0);
                tblResultados.getColumnModel().getColumn(6).setPreferredWidth(0);
                tblResultados.getColumnModel().getColumn(6).setMaxWidth(0);
                tblResultados.getColumnModel().getColumn(7).setMinWidth(0);
                tblResultados.getColumnModel().getColumn(7).setPreferredWidth(0);
                tblResultados.getColumnModel().getColumn(7).setMaxWidth(0);

                tblResultados.getColumnModel().getColumn(4).setMinWidth(250);
                tblResultados.getColumnModel().getColumn(4).setPreferredWidth(250);
                tblResultados.getColumnModel().getColumn(4).setMaxWidth(250);
                tblResultados.getColumnModel().getColumn(2).setMinWidth(250);
                tblResultados.getColumnModel().getColumn(2).setPreferredWidth(250);
                tblResultados.getColumnModel().getColumn(2).setMaxWidth(250);

                tblResultados.revalidate();

            } else if (rdPorVendedor.isSelected()) {
                String sql = "";
                if (cmbFiltro1.getItemCount() > 0) {
                    sql = "where ";
                    for (int i = 0; i < cmbFiltro1.getItemCount(); i++) {
                        sql = sql + " factura.vendedor = '" + cmbFiltro1.getItemAt(i) + "' OR";
                    }
                    sql = sql.substring(0, sql.length() - 2);
                }

                Object[][] registros = instancias.getSql().getIndicadoresPor(sql, "vendedor");
                for (int i = 0; i < registros.length; i++) {
                    if (registros[i][0] != null) {
                        String vendedor = "";
                        if (registros[i][4] == null || registros[i][4].toString().equals("") || registros[i][4].toString().equals(" ")) {
                            vendedor = "Sin-Vendedor";
                        } else {
                            vendedor = registros[i][4].toString();
                        }
                        modelo.addRow(new Object[]{"", "", "", vendedor, "", "", "", "", big.setMoneda(big.getBigDecimal(registros[i][0])), big.setMoneda(big.getBigDecimal(registros[i][3])),
                            big.setMoneda(big.getBigDecimal(registros[i][1])), big.setMoneda(big.getBigDecimal(registros[i][6])), big.setMoneda(big.getBigDecimal(registros[i][2])),
                            big.setMoneda(big.getBigDecimal(registros[i][5]))});
                    }
                }

                tblResultados.getColumnModel().getColumn(0).setMinWidth(0);
                tblResultados.getColumnModel().getColumn(0).setPreferredWidth(0);
                tblResultados.getColumnModel().getColumn(0).setMaxWidth(0);
                tblResultados.getColumnModel().getColumn(1).setMinWidth(0);
                tblResultados.getColumnModel().getColumn(1).setPreferredWidth(0);
                tblResultados.getColumnModel().getColumn(1).setMaxWidth(0);
                tblResultados.getColumnModel().getColumn(2).setMinWidth(0);
                tblResultados.getColumnModel().getColumn(2).setPreferredWidth(0);
                tblResultados.getColumnModel().getColumn(2).setMaxWidth(0);

                tblResultados.getColumnModel().getColumn(3).setMinWidth(250);
                tblResultados.getColumnModel().getColumn(3).setPreferredWidth(250);
                tblResultados.getColumnModel().getColumn(3).setMaxWidth(250);

                tblResultados.getColumnModel().getColumn(4).setMinWidth(0);
                tblResultados.getColumnModel().getColumn(4).setPreferredWidth(0);
                tblResultados.getColumnModel().getColumn(4).setMaxWidth(0);
                tblResultados.getColumnModel().getColumn(5).setMinWidth(0);
                tblResultados.getColumnModel().getColumn(5).setPreferredWidth(0);
                tblResultados.getColumnModel().getColumn(5).setMaxWidth(0);
                tblResultados.getColumnModel().getColumn(6).setMinWidth(0);
                tblResultados.getColumnModel().getColumn(6).setPreferredWidth(0);
                tblResultados.getColumnModel().getColumn(6).setMaxWidth(0);
                tblResultados.getColumnModel().getColumn(7).setMinWidth(0);
                tblResultados.getColumnModel().getColumn(7).setPreferredWidth(0);
                tblResultados.getColumnModel().getColumn(7).setMaxWidth(0);

                tblResultados.getColumnModel().getColumn(3).setMinWidth(250);
                tblResultados.getColumnModel().getColumn(3).setPreferredWidth(250);
                tblResultados.getColumnModel().getColumn(3).setMaxWidth(250);

            } else if (rdPorCliente.isSelected()) {

                String sql = "";
                if (cmbGrupo.getItemCount() > 0) {
                    sql = "where ";
                    for (int i = 0; i < cmbGrupo.getItemCount(); i++) {
                        sql = sql + " factura.idSistema = '" + cmbGrupo.getItemAt(i) + "' OR";
                    }

                    sql = sql.substring(0, sql.length() - 2);
                }

                Object[][] registros = instancias.getSql().getIndicadoresPorCliente(sql);
                for (int i = 0; i < registros.length; i++) {
                    if (registros[i][0] != null) {
                        modelo.addRow(new Object[]{"", registros[i][4], registros[i][7], "", "", "", "", "", big.setMoneda(big.getBigDecimal(registros[i][0])), big.setMoneda(big.getBigDecimal(registros[i][3])),
                            big.setMoneda(big.getBigDecimal(registros[i][1])), big.setMoneda(big.getBigDecimal(registros[i][6])), big.setMoneda(big.getBigDecimal(registros[i][2])),
                            big.setMoneda(big.getBigDecimal(registros[i][5]))});
                    }
                }

                tblResultados.getColumnModel().getColumn(1).setMinWidth(150);
                tblResultados.getColumnModel().getColumn(1).setPreferredWidth(150);
                tblResultados.getColumnModel().getColumn(1).setMaxWidth(150);
                tblResultados.getColumnModel().getColumn(2).setMinWidth(350);
                tblResultados.getColumnModel().getColumn(2).setPreferredWidth(350);
                tblResultados.getColumnModel().getColumn(2).setMaxWidth(350);

                tblResultados.getColumnModel().getColumn(0).setMinWidth(0);
                tblResultados.getColumnModel().getColumn(0).setPreferredWidth(0);
                tblResultados.getColumnModel().getColumn(0).setMaxWidth(0);
                tblResultados.getColumnModel().getColumn(3).setMinWidth(0);
                tblResultados.getColumnModel().getColumn(3).setPreferredWidth(0);
                tblResultados.getColumnModel().getColumn(3).setMaxWidth(0);
                tblResultados.getColumnModel().getColumn(4).setMinWidth(0);
                tblResultados.getColumnModel().getColumn(4).setPreferredWidth(0);
                tblResultados.getColumnModel().getColumn(4).setMaxWidth(0);
                tblResultados.getColumnModel().getColumn(5).setMinWidth(0);
                tblResultados.getColumnModel().getColumn(5).setPreferredWidth(0);
                tblResultados.getColumnModel().getColumn(5).setMaxWidth(0);
                tblResultados.getColumnModel().getColumn(6).setMinWidth(0);
                tblResultados.getColumnModel().getColumn(6).setPreferredWidth(0);
                tblResultados.getColumnModel().getColumn(6).setMaxWidth(0);
                tblResultados.getColumnModel().getColumn(7).setMinWidth(0);
                tblResultados.getColumnModel().getColumn(7).setPreferredWidth(0);
                tblResultados.getColumnModel().getColumn(7).setMaxWidth(0);

                tblResultados.getColumnModel().getColumn(1).setMinWidth(150);
                tblResultados.getColumnModel().getColumn(1).setPreferredWidth(150);
                tblResultados.getColumnModel().getColumn(1).setMaxWidth(150);
                tblResultados.getColumnModel().getColumn(2).setMinWidth(350);
                tblResultados.getColumnModel().getColumn(2).setPreferredWidth(350);
                tblResultados.getColumnModel().getColumn(2).setMaxWidth(350);

            } else if (rdCantidadesProductos.isSelected()) {
                String sql = "";
                if (cmbGrupo.getItemCount() > 0) {
                    sql = "where ";
                    for (int i = 0; i < cmbGrupo.getItemCount(); i++) {
                        sql = sql + " factura.producto = '" + cmbGrupo.getItemAt(i) + "' OR";
                    }
                    sql = sql.substring(0, sql.length() - 2);
                }

                Object[][] registros = instancias.getSql().getIndicadoresPorProductos(sql, "cant2");
                for (int i = 0; i < registros.length; i++) {
                    if (registros[i][0] != null) {
                        modelo.addRow(new Object[]{registros[i][4], "", registros[i][7], "", "", "", "", big.setNumero(big.getBigDecimal(registros[i][8])),
                            big.setMoneda(big.getBigDecimal(registros[i][0])), big.setMoneda(big.getBigDecimal(registros[i][3])),
                            big.setMoneda(big.getBigDecimal(registros[i][1])), big.setMoneda(big.getBigDecimal(registros[i][6])), big.setMoneda(big.getBigDecimal(registros[i][2])),
                            big.setMoneda(big.getBigDecimal(registros[i][5]))});
                    }
                }

                tblResultados.getColumnModel().getColumn(0).setMinWidth(180);
                tblResultados.getColumnModel().getColumn(0).setPreferredWidth(180);
                tblResultados.getColumnModel().getColumn(0).setMaxWidth(180);
                tblResultados.getColumnModel().getColumn(7).setMinWidth(150);
                tblResultados.getColumnModel().getColumn(7).setPreferredWidth(150);
                tblResultados.getColumnModel().getColumn(7).setMaxWidth(150);
                tblResultados.getColumnModel().getColumn(2).setMinWidth(250);
                tblResultados.getColumnModel().getColumn(2).setPreferredWidth(250);
                tblResultados.getColumnModel().getColumn(2).setMaxWidth(250);

                tblResultados.getColumnModel().getColumn(1).setMinWidth(0);
                tblResultados.getColumnModel().getColumn(1).setPreferredWidth(0);
                tblResultados.getColumnModel().getColumn(1).setMaxWidth(0);
                tblResultados.getColumnModel().getColumn(3).setMinWidth(0);
                tblResultados.getColumnModel().getColumn(3).setPreferredWidth(0);
                tblResultados.getColumnModel().getColumn(3).setMaxWidth(0);
                tblResultados.getColumnModel().getColumn(4).setMinWidth(0);
                tblResultados.getColumnModel().getColumn(4).setPreferredWidth(0);
                tblResultados.getColumnModel().getColumn(4).setMaxWidth(0);
                tblResultados.getColumnModel().getColumn(5).setMinWidth(0);
                tblResultados.getColumnModel().getColumn(5).setPreferredWidth(0);
                tblResultados.getColumnModel().getColumn(5).setMaxWidth(0);
                tblResultados.getColumnModel().getColumn(6).setMinWidth(0);
                tblResultados.getColumnModel().getColumn(6).setPreferredWidth(0);
                tblResultados.getColumnModel().getColumn(6).setMaxWidth(0);

                tblResultados.getColumnModel().getColumn(0).setMinWidth(180);
                tblResultados.getColumnModel().getColumn(0).setPreferredWidth(180);
                tblResultados.getColumnModel().getColumn(0).setMaxWidth(180);
                tblResultados.getColumnModel().getColumn(7).setMinWidth(150);
                tblResultados.getColumnModel().getColumn(7).setPreferredWidth(150);
                tblResultados.getColumnModel().getColumn(7).setMaxWidth(150);
                tblResultados.getColumnModel().getColumn(2).setMinWidth(250);
                tblResultados.getColumnModel().getColumn(2).setPreferredWidth(250);
                tblResultados.getColumnModel().getColumn(2).setMaxWidth(250);

            } else if (rdValoresProductos.isSelected()) {
                String sql = "";
                if (cmbGrupo.getItemCount() > 0) {
                    sql = "where ";
                    for (int i = 0; i < cmbGrupo.getItemCount(); i++) {
                        sql = sql + " factura.producto = '" + cmbGrupo.getItemAt(i) + "' OR";
                    }
                    sql = sql.substring(0, sql.length() - 2);
                }

                Object[][] registros = instancias.getSql().getIndicadoresPorProductos(sql, "total");
                for (int i = 0; i < registros.length; i++) {
                    if (registros[i][0] != null) {
                        modelo.addRow(new Object[]{registros[i][4], "", registros[i][7], "", "", "", "", big.setNumero(big.getBigDecimal(registros[i][8])),
                            big.setMoneda(big.getBigDecimal(registros[i][0])), big.setMoneda(big.getBigDecimal(registros[i][3])),
                            big.setMoneda(big.getBigDecimal(registros[i][1])), big.setMoneda(big.getBigDecimal(registros[i][6])), big.setMoneda(big.getBigDecimal(registros[i][2])),
                            big.setMoneda(big.getBigDecimal(registros[i][5]))});
                    }
                }

                tblResultados.getColumnModel().getColumn(0).setMinWidth(180);
                tblResultados.getColumnModel().getColumn(0).setPreferredWidth(180);
                tblResultados.getColumnModel().getColumn(0).setMaxWidth(180);
                tblResultados.getColumnModel().getColumn(7).setMinWidth(150);
                tblResultados.getColumnModel().getColumn(7).setPreferredWidth(150);
                tblResultados.getColumnModel().getColumn(7).setMaxWidth(150);
                tblResultados.getColumnModel().getColumn(2).setMinWidth(250);
                tblResultados.getColumnModel().getColumn(2).setPreferredWidth(250);
                tblResultados.getColumnModel().getColumn(2).setMaxWidth(250);

                tblResultados.getColumnModel().getColumn(1).setMinWidth(0);
                tblResultados.getColumnModel().getColumn(1).setPreferredWidth(0);
                tblResultados.getColumnModel().getColumn(1).setMaxWidth(0);
                tblResultados.getColumnModel().getColumn(3).setMinWidth(0);
                tblResultados.getColumnModel().getColumn(3).setPreferredWidth(0);
                tblResultados.getColumnModel().getColumn(3).setMaxWidth(0);
                tblResultados.getColumnModel().getColumn(4).setMinWidth(0);
                tblResultados.getColumnModel().getColumn(4).setPreferredWidth(0);
                tblResultados.getColumnModel().getColumn(4).setMaxWidth(0);
                tblResultados.getColumnModel().getColumn(5).setMinWidth(0);
                tblResultados.getColumnModel().getColumn(5).setPreferredWidth(0);
                tblResultados.getColumnModel().getColumn(5).setMaxWidth(0);
                tblResultados.getColumnModel().getColumn(6).setMinWidth(0);
                tblResultados.getColumnModel().getColumn(6).setPreferredWidth(0);
                tblResultados.getColumnModel().getColumn(6).setMaxWidth(0);

                tblResultados.getColumnModel().getColumn(0).setMinWidth(180);
                tblResultados.getColumnModel().getColumn(0).setPreferredWidth(180);
                tblResultados.getColumnModel().getColumn(0).setMaxWidth(180);
                tblResultados.getColumnModel().getColumn(7).setMinWidth(150);
                tblResultados.getColumnModel().getColumn(7).setPreferredWidth(150);
                tblResultados.getColumnModel().getColumn(7).setMaxWidth(150);
                tblResultados.getColumnModel().getColumn(2).setMinWidth(250);
                tblResultados.getColumnModel().getColumn(2).setPreferredWidth(250);
                tblResultados.getColumnModel().getColumn(2).setMaxWidth(250);

            } else if (rdPorMes.isSelected()) {

                String sql = "";
                if (cmbGrupo.getItemCount() > 0) {
                    sql = "where ";
                    for (int i = 0; i < cmbGrupo.getItemCount(); i++) {
                        sql = sql + " factura.producto = '" + cmbGrupo.getItemAt(i) + "' OR";
                    }
                    sql = sql.substring(0, sql.length() - 2) + " AND";
                }

                if (cmbFiltro1.getItemCount() > 0) {
                    if (sql.equals("")) {
                        sql = "where ";
                    }
                    for (int i = 0; i < cmbFiltro1.getItemCount(); i++) {
                        sql = sql + " Month(fechaFactura) = " + cmbFiltro1.getItemAt(i) + " OR";
                    }
                    sql = sql.substring(0, sql.length() - 2);
                } else if (!sql.equals("")) {
                    sql = sql.substring(0, sql.length() - 3);
                }

                Object[][] registros = instancias.getSql().getIndicadoresPorProductoMes(sql);
                for (int i = 0; i < registros.length; i++) {
                    if (registros[i][0] != null) {

                        String mes = registros[i][4].toString();

                        if (mes.equals("1")) {
                            mes = "Enero";
                        } else if (mes.equals("2")) {
                            mes = "Febrero";
                        } else if (mes.equals("3")) {
                            mes = "Marzo";
                        } else if (mes.equals("4")) {
                            mes = "Abril";
                        } else if (mes.equals("5")) {
                            mes = "Mayo";
                        } else if (mes.equals("6")) {
                            mes = "Junio";
                        } else if (mes.equals("7")) {
                            mes = "Julio";
                        } else if (mes.equals("8")) {
                            mes = "Agosto";
                        } else if (mes.equals("9")) {
                            mes = "Septiembre";
                        } else if (mes.equals("10")) {
                            mes = "Octubre";
                        } else if (mes.equals("11")) {
                            mes = "Noviembre";
                        } else if (mes.equals("12")) {
                            mes = "Diciembre";
                        }

                        modelo.addRow(new Object[]{registros[i][7], "", registros[i][8], "", "", "", mes, "",
                            big.setMoneda(big.getBigDecimal(registros[i][0])), big.setMoneda(big.getBigDecimal(registros[i][3])),
                            big.setMoneda(big.getBigDecimal(registros[i][1])), big.setMoneda(big.getBigDecimal(registros[i][6])), big.setMoneda(big.getBigDecimal(registros[i][2])),
                            big.setMoneda(big.getBigDecimal(registros[i][5]))});
                    }
                }

                tblResultados.getColumnModel().getColumn(0).setMinWidth(150);
                tblResultados.getColumnModel().getColumn(0).setPreferredWidth(150);
                tblResultados.getColumnModel().getColumn(0).setMaxWidth(150);
                tblResultados.getColumnModel().getColumn(2).setMinWidth(250);
                tblResultados.getColumnModel().getColumn(2).setPreferredWidth(250);
                tblResultados.getColumnModel().getColumn(2).setMaxWidth(250);
                tblResultados.getColumnModel().getColumn(6).setMinWidth(250);
                tblResultados.getColumnModel().getColumn(6).setPreferredWidth(250);
                tblResultados.getColumnModel().getColumn(6).setMaxWidth(250);

                tblResultados.getColumnModel().getColumn(1).setMinWidth(0);
                tblResultados.getColumnModel().getColumn(1).setPreferredWidth(0);
                tblResultados.getColumnModel().getColumn(1).setMaxWidth(0);
                tblResultados.getColumnModel().getColumn(3).setMinWidth(0);
                tblResultados.getColumnModel().getColumn(3).setPreferredWidth(0);
                tblResultados.getColumnModel().getColumn(3).setMaxWidth(0);
                tblResultados.getColumnModel().getColumn(4).setMinWidth(0);
                tblResultados.getColumnModel().getColumn(4).setPreferredWidth(0);
                tblResultados.getColumnModel().getColumn(4).setMaxWidth(0);
                tblResultados.getColumnModel().getColumn(5).setMinWidth(0);
                tblResultados.getColumnModel().getColumn(5).setPreferredWidth(0);
                tblResultados.getColumnModel().getColumn(5).setMaxWidth(0);
                tblResultados.getColumnModel().getColumn(6).setMinWidth(250);
                tblResultados.getColumnModel().getColumn(6).setPreferredWidth(250);
                tblResultados.getColumnModel().getColumn(6).setMaxWidth(250);
                tblResultados.getColumnModel().getColumn(7).setMinWidth(0);
                tblResultados.getColumnModel().getColumn(7).setPreferredWidth(0);
                tblResultados.getColumnModel().getColumn(7).setMaxWidth(0);

                tblResultados.getColumnModel().getColumn(0).setMinWidth(150);
                tblResultados.getColumnModel().getColumn(0).setPreferredWidth(150);
                tblResultados.getColumnModel().getColumn(0).setMaxWidth(150);
                tblResultados.getColumnModel().getColumn(2).setMinWidth(250);
                tblResultados.getColumnModel().getColumn(2).setPreferredWidth(250);
                tblResultados.getColumnModel().getColumn(2).setMaxWidth(250);
                tblResultados.getColumnModel().getColumn(6).setMinWidth(250);
                tblResultados.getColumnModel().getColumn(6).setPreferredWidth(250);
                tblResultados.getColumnModel().getColumn(6).setMaxWidth(250);
            } else if (rdGeneral.isSelected()) {
                String sql = "";
                if (cmbFiltro1.getItemCount() > 0) {
                    sql = "where ";
                    for (int i = 0; i < cmbFiltro1.getItemCount(); i++) {
                        sql = sql + " Month(fechaFactura) = " + cmbFiltro1.getItemAt(i) + " OR";
                    }

                    sql = sql.substring(0, sql.length() - 2);
                }

                Object[][] registros = instancias.getSql().getIndicadoresPorGeneral(sql);
                for (int i = 0; i < registros.length; i++) {
                    if (registros[i][0] != null) {

                        String mes = registros[i][4].toString();

                        if (mes.equals("1")) {
                            mes = "Enero";
                        } else if (mes.equals("2")) {
                            mes = "Febrero";
                        } else if (mes.equals("3")) {
                            mes = "Marzo";
                        } else if (mes.equals("4")) {
                            mes = "Abril";
                        } else if (mes.equals("5")) {
                            mes = "Mayo";
                        } else if (mes.equals("6")) {
                            mes = "Junio";
                        } else if (mes.equals("7")) {
                            mes = "Julio";
                        } else if (mes.equals("8")) {
                            mes = "Agosto";
                        } else if (mes.equals("9")) {
                            mes = "Septiembre";
                        } else if (mes.equals("10")) {
                            mes = "Octubre";
                        } else if (mes.equals("11")) {
                            mes = "Noviembre";
                        } else if (mes.equals("12")) {
                            mes = "Diciembre";
                        }

                        modelo.addRow(new Object[]{"", "", "", "", "", "", mes, "",
                            big.setMoneda(big.getBigDecimal(registros[i][0])), big.setMoneda(big.getBigDecimal(registros[i][3])),
                            big.setMoneda(big.getBigDecimal(registros[i][1])), big.setMoneda(big.getBigDecimal(registros[i][6])), big.setMoneda(big.getBigDecimal(registros[i][2])),
                            big.setMoneda(big.getBigDecimal(registros[i][5]))});
                    }
                }

                tblResultados.getColumnModel().getColumn(6).setMinWidth(250);
                tblResultados.getColumnModel().getColumn(6).setPreferredWidth(250);
                tblResultados.getColumnModel().getColumn(6).setMaxWidth(250);

                tblResultados.getColumnModel().getColumn(0).setMinWidth(0);
                tblResultados.getColumnModel().getColumn(0).setPreferredWidth(0);
                tblResultados.getColumnModel().getColumn(0).setMaxWidth(0);
                tblResultados.getColumnModel().getColumn(2).setMinWidth(0);
                tblResultados.getColumnModel().getColumn(2).setPreferredWidth(0);
                tblResultados.getColumnModel().getColumn(2).setMaxWidth(0);
                tblResultados.getColumnModel().getColumn(1).setMinWidth(0);
                tblResultados.getColumnModel().getColumn(1).setPreferredWidth(0);
                tblResultados.getColumnModel().getColumn(1).setMaxWidth(0);
                tblResultados.getColumnModel().getColumn(3).setMinWidth(0);
                tblResultados.getColumnModel().getColumn(3).setPreferredWidth(0);
                tblResultados.getColumnModel().getColumn(3).setMaxWidth(0);
                tblResultados.getColumnModel().getColumn(4).setMinWidth(0);
                tblResultados.getColumnModel().getColumn(4).setPreferredWidth(0);
                tblResultados.getColumnModel().getColumn(4).setMaxWidth(0);
                tblResultados.getColumnModel().getColumn(5).setMinWidth(0);
                tblResultados.getColumnModel().getColumn(5).setPreferredWidth(0);
                tblResultados.getColumnModel().getColumn(5).setMaxWidth(0);
                tblResultados.getColumnModel().getColumn(6).setMinWidth(250);
                tblResultados.getColumnModel().getColumn(6).setPreferredWidth(250);
                tblResultados.getColumnModel().getColumn(6).setMaxWidth(250);
                tblResultados.getColumnModel().getColumn(7).setMinWidth(0);
                tblResultados.getColumnModel().getColumn(7).setPreferredWidth(0);
                tblResultados.getColumnModel().getColumn(7).setMaxWidth(0);

                tblResultados.getColumnModel().getColumn(6).setMinWidth(250);
                tblResultados.getColumnModel().getColumn(6).setPreferredWidth(250);
                tblResultados.getColumnModel().getColumn(6).setMaxWidth(250);
            }
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnGuardarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnGuardarKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnGuardarActionPerformed(null);
        }
    }//GEN-LAST:event_btnGuardarKeyReleased

    private void btnGuardar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar1ActionPerformed
        if (metodos.msgPregunta(null, "¿Esta seguro?") == 0) {

            if (rdGeneral.isSelected()) {
                String sql = "";
                if (cmbFiltro1.getItemCount() > 0) {
                    sql = "where ";
                    for (int i = 0; i < cmbFiltro1.getItemCount(); i++) {
                        sql = sql + " Month(fechaFactura) = " + cmbFiltro1.getItemAt(i) + " OR";
                    }
                    sql = sql.substring(0, sql.length() - 2);
                }
                instancias.getReporte().ver_Indicador("enGeneral", sql);
            } else if (rdPorGrupos.isSelected()) {
                String sql = "";
                if (cmbGrupo.getItemCount() > 0) {
                    sql = "where ";
                    for (int i = 0; i < cmbGrupo.getItemCount(); i++) {
                        sql = sql + " factura.Grupo = '" + cmbGrupo.getItemAt(i) + "' OR";
                    }
                    sql = sql.substring(0, sql.length() - 2);
                }
                instancias.getReporte().ver_Indicador("porGrupos", sql);
            } else if (rdPorVendedor.isSelected()) {
                String sql = "";
                if (cmbFiltro1.getItemCount() > 0) {
                    sql = "where ";
                    for (int i = 0; i < cmbFiltro1.getItemCount(); i++) {
                        sql = sql + " factura.vendedor = '" + cmbFiltro1.getItemAt(i) + "' OR";
                    }
                    sql = sql.substring(0, sql.length() - 2);
                }

                instancias.getReporte().ver_Indicador("porVendedor", sql);
            } else if (rdPorCliente.isSelected()) {
                String sql = "";
                if (cmbGrupo.getItemCount() > 0) {
                    sql = "where ";
                    for (int i = 0; i < cmbGrupo.getItemCount(); i++) {
                        sql = sql + " factura.idSistema = '" + cmbGrupo.getItemAt(i) + "' OR";
                    }

                    sql = sql.substring(0, sql.length() - 2);
                }

                instancias.getReporte().ver_Indicador("porCliente", sql);
            } else if (rdCantidadesProductos.isSelected()) {
                String sql = "";
                if (cmbGrupo.getItemCount() > 0) {
                    sql = "where ";
                    for (int i = 0; i < cmbGrupo.getItemCount(); i++) {
                        sql = sql + " factura.producto = '" + cmbGrupo.getItemAt(i) + "' OR";
                    }
                    sql = sql.substring(0, sql.length() - 2);
                }

                instancias.getReporte().ver_Indicador("cantidadesProductos", sql);
            } else if (rdValoresProductos.isSelected()) {
                String sql = "";
                if (cmbGrupo.getItemCount() > 0) {
                    sql = "where ";
                    for (int i = 0; i < cmbGrupo.getItemCount(); i++) {
                        sql = sql + " factura.producto = '" + cmbGrupo.getItemAt(i) + "' OR";
                    }
                    sql = sql.substring(0, sql.length() - 2);
                }

                instancias.getReporte().ver_Indicador("valoresProductos", sql);
            } else if (rdPorMes.isSelected()) {

                String sql = "";
                if (cmbGrupo.getItemCount() > 0) {
                    sql = "where ";
                    for (int i = 0; i < cmbGrupo.getItemCount(); i++) {
                        sql = sql + " factura.producto = '" + cmbGrupo.getItemAt(i) + "' OR";
                    }
                    sql = sql.substring(0, sql.length() - 2) + " AND";
                }

                if (cmbFiltro1.getItemCount() > 0) {
                    if (sql.equals("")) {
                        sql = "where ";
                    }
                    for (int i = 0; i < cmbFiltro1.getItemCount(); i++) {
                        sql = sql + " Month(fechaFactura) = " + cmbFiltro1.getItemAt(i) + " OR";
                    }
                    sql = sql.substring(0, sql.length() - 2);
                } else if (!sql.equals("")) {
                    sql = sql.substring(0, sql.length() - 3);
                }
                instancias.getReporte().ver_Indicador("porMes", sql);
            }
        }
    }//GEN-LAST:event_btnGuardar1ActionPerformed

    private void btnGuardar1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnGuardar1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGuardar1KeyReleased

    private void txtCodGrupoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodGrupoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (rdPorGrupos.isSelected()) {
                cargarGrupo(txtCodGrupo.getText());
            } else if (rdPorCliente.isSelected()) {
                cargarCliente(txtCodGrupo.getText());
            } else if (rdPorMes.isSelected()) {
                cargarProducto(txtCodGrupo.getText());
            } else if (rdCantidadesProductos.isSelected()) {
                cargarProducto(txtCodGrupo.getText());
            } else if (rdValoresProductos.isSelected()) {
                cargarProducto(txtCodGrupo.getText());
            }
        } else {
            //            txtGrupo.setText("");
        }
    }//GEN-LAST:event_txtCodGrupoKeyReleased

    private void btnRemGrupoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemGrupoActionPerformed
        if (cmbGrupo.getItemCount() == 0) {
            metodos.msgError(null, "No hay ningún grupo seleccioando");
            return;
        }
        cmbGrupo.removeItem(cmbGrupo.getSelectedItem());
    }//GEN-LAST:event_btnRemGrupoActionPerformed

    private void btnAggGrupoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAggGrupoActionPerformed
        if (txtCodGrupo.getText().equals("")) {

            if (rdPorGrupos.isSelected()) {
                ventanaGrupos("");
            } else if (rdPorCliente.isSelected()) {
                ventanaTerceros("");
            } else if (rdPorMes.isSelected()) {
                ventanaProducto("");
            }

        } else {
            if (yaEsta(cmbGrupo, txtCodGrupo.getText())) {
                metodos.msgAdvertencia(null, "Ya cargo este grupo");
            } else {
                cmbGrupo.addItem(txtCodGrupo.getText());
                cmbGrupo.setSelectedItem(txtCodGrupo.getText());
            }

            txtCodGrupo.setText("");
        }
    }//GEN-LAST:event_btnAggGrupoActionPerformed

    private void rdPorGruposItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdPorGruposItemStateChanged
        if (rdPorGrupos.isSelected()) {
            lbFiltro.setText("Grupos: ");
            pnlPrincipal.setVisible(true);
            pnlCombos.setVisible(false);
            cmbGrupo.removeAllItems();

            while (tblResultados.getRowCount() > 0) {
                modelo.removeRow(0);
            }
        }
    }//GEN-LAST:event_rdPorGruposItemStateChanged

    private void btnRemGrupo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemGrupo1ActionPerformed
        if (cmbFiltro1.getItemCount() == 0) {
            metodos.msgError(null, "No ha cargado nada");
            return;
        }
        cmbFiltro1.removeItem(cmbFiltro1.getSelectedItem());
    }//GEN-LAST:event_btnRemGrupo1ActionPerformed

    private void btnAggFiltro1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAggFiltro1ActionPerformed
        if (rdGeneral.isSelected() || rdPorMes.isSelected()) {
            if (yaEsta1(cmbFiltro1, cmbFiltro.getSelectedItem().toString())) {
                metodos.msgAdvertencia(null, "Ya se cargo este mes");
            } else {
                int num = cmbFiltro.getSelectedIndex() + 1;
                cmbFiltro1.addItem(num);
                cmbFiltro1.setSelectedItem(num);
            }
        } else {
            if (yaEsta(cmbFiltro1, cmbFiltro.getSelectedItem().toString())) {
                metodos.msgAdvertencia(null, "Ya se cargo este registro");
            } else {
                cmbFiltro1.addItem(cmbFiltro.getSelectedItem());
                cmbFiltro1.setSelectedItem(cmbFiltro.getSelectedItem());
            }
        }

        cmbFiltro.setSelectedIndex(0);
    }//GEN-LAST:event_btnAggFiltro1ActionPerformed

    private void rdGeneralItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdGeneralItemStateChanged
        if (rdGeneral.isSelected()) {
            pnlCombos.setVisible(true);
            pnlPrincipal.setVisible(false);

            lbFiltro1.setText("Mes:");
            cmbFiltro.removeAllItems();
            cmbFiltro.addItem("Enero");
            cmbFiltro.addItem("Febrero");
            cmbFiltro.addItem("Marzo");
            cmbFiltro.addItem("Abril");
            cmbFiltro.addItem("Mayo");
            cmbFiltro.addItem("Junio");
            cmbFiltro.addItem("Julio");
            cmbFiltro.addItem("Agosto");
            cmbFiltro.addItem("Septiembre");
            cmbFiltro.addItem("Octubre");
            cmbFiltro.addItem("Noviembre");
            cmbFiltro.addItem("Diciembre");

            while (tblResultados.getRowCount() > 0) {
                modelo.removeRow(0);
            }
        }
    }//GEN-LAST:event_rdGeneralItemStateChanged

    private void rdPorClienteItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdPorClienteItemStateChanged
        if (rdPorCliente.isSelected()) {
            pnlPrincipal.setVisible(true);
            lbFiltro.setText("Cliente:");
            pnlCombos.setVisible(false);
            cmbGrupo.removeAllItems();

            while (tblResultados.getRowCount() > 0) {
                modelo.removeRow(0);
            }
        }
    }//GEN-LAST:event_rdPorClienteItemStateChanged

    private void rdPorVendedorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdPorVendedorItemStateChanged
        if (rdPorVendedor.isSelected()) {
            pnlPrincipal.setVisible(false);
            pnlCombos.setVisible(true);
            lbFiltro1.setText("Vendedor:");

            String[] vendedores;
            Object[][] vendedoresMatriz = instancias.getSql().getVendedores();

            vendedores = new String[vendedoresMatriz.length];
            for (int i = 0; i < vendedoresMatriz.length; i++) {
                vendedores[i] = vendedoresMatriz[i][0].toString();
            }

            cmbFiltro.removeAllItems();
            for (int i = 0; i < vendedores.length; i++) {
                cmbFiltro.addItem(vendedores[i]);
            }

            while (tblResultados.getRowCount() > 0) {
                modelo.removeRow(0);
            }
        }
    }//GEN-LAST:event_rdPorVendedorItemStateChanged

    private void cmbFiltro1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbFiltro1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbFiltro1ActionPerformed

    private void rdPorMesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdPorMesItemStateChanged
        if (rdPorMes.isSelected()) {
            pnlPrincipal.setVisible(true);
            pnlCombos.setVisible(true);
            lbFiltro1.setText("Mes:");
            lbFiltro.setText("Producto:");

            cmbFiltro.removeAllItems();
            cmbFiltro1.removeAllItems();
            cmbGrupo.removeAllItems();
            cmbFiltro.addItem("Enero");
            cmbFiltro.addItem("Febrero");
            cmbFiltro.addItem("Marzo");
            cmbFiltro.addItem("Abril");
            cmbFiltro.addItem("Mayo");
            cmbFiltro.addItem("Junio");
            cmbFiltro.addItem("Julio");
            cmbFiltro.addItem("Agosto");
            cmbFiltro.addItem("Septiembre");
            cmbFiltro.addItem("Octubre");
            cmbFiltro.addItem("Noviembre");
            cmbFiltro.addItem("Diciembre");

            while (tblResultados.getRowCount() > 0) {
                modelo.removeRow(0);
            }
        }
    }//GEN-LAST:event_rdPorMesItemStateChanged

    private void rdCantidadesProductosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdCantidadesProductosItemStateChanged
        if (rdCantidadesProductos.isSelected()) {
            pnlPrincipal.setVisible(true);
            pnlCombos.setVisible(false);
            lbFiltro.setText("Producto:");
            cmbGrupo.removeAllItems();

            while (tblResultados.getRowCount() > 0) {
                modelo.removeRow(0);
            }
        }
    }//GEN-LAST:event_rdCantidadesProductosItemStateChanged

    private void rdValoresProductosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdValoresProductosItemStateChanged
        if (rdValoresProductos.isSelected()) {
            pnlPrincipal.setVisible(true);
            pnlCombos.setVisible(false);
            lbFiltro.setText("Producto:");
            cmbGrupo.removeAllItems();

            while (tblResultados.getRowCount() > 0) {
                modelo.removeRow(0);
            }
        }
    }//GEN-LAST:event_rdValoresProductosItemStateChanged

    public void cargarGrupo(String codigo) {

        if (yaEsta(cmbGrupo, codigo)) {
            metodos.msgAdvertencia(null, "Ya cargo este grupo");
            txtCodGrupo.setText("");
            return;
        }

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

    public void cargarProducto(String nit) {

        if (!nit.equals("")) {
            ndProducto nodo = instancias.getSql().getDatosProducto(nit, "bdProductos");

            if (yaEsta(cmbGrupo, nodo.getIdSistema())) {
                metodos.msgAdvertencia(null, "Ya cargo este producto");
                txtCodGrupo.setText("");
                return;
            }

            if (nodo.getCodigo() != null) {
                txtCodGrupo.setText(nodo.getIdSistema());
                btnAggGrupoActionPerformed(null);
                return;
            }
        }

        ventanaProducto(nit);
    }

    public void ventanaProducto(String nit) {
        VistaBuscadorProductos buscar = new VistaBuscadorProductos(instancias.getMenu(), false, false, "", "productos1");
        buscar.setLocationRelativeTo(null);
        instancias.setBusProductos(buscar);
        instancias.setCampoActual(txtCodGrupo);
        txtCodGrupo.requestFocus();
        buscar.noEncontrado(nit);
        buscar.show();
    }

    public void cargarCliente(String nit) {
        ModeloContacto nodo = instancias.getSql().getDatosTercero(nit);
        if (nodo.getId() != null) {
            txtCodGrupo.setText(nodo.getIdSistema());
            btnAggGrupoActionPerformed(null);
            return;
        }
        ventanaTerceros(nit);
    }

    public void ventanaTerceros(String nit) {
        buscClientes buscar = new buscClientes(instancias.getMenu(), false, false, null, "");
        buscar.setLocationRelativeTo(null);
        instancias.setBusClientes(buscar);
        instancias.setCampoActual(txtCodGrupo);
        txtCodGrupo.requestFocus();
        buscar.noEncontrado(nit);
        buscar.show();
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

    public boolean yaEsta(JComboBox combo, String dato) {
        boolean sw = false;
        for (int i = 0; i < combo.getItemCount(); i++) {
            if (((String) combo.getItemAt(i)).equals(dato)) {
                return true;
            }
        }
        return sw;
    }

    public boolean yaEsta1(JComboBox combo, String dato) {
        boolean sw = false;
        for (int i = 0; i < combo.getItemCount(); i++) {
            if (combo.getItemAt(i) == dato) {
                return true;
            }
        }
        return sw;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAggFiltro1;
    private javax.swing.JButton btnAggGrupo;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnGuardar1;
    private javax.swing.JButton btnRemGrupo;
    private javax.swing.JButton btnRemGrupo1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cmbFiltro;
    private javax.swing.JComboBox cmbFiltro1;
    private javax.swing.JComboBox cmbGrupo;
    private javax.swing.ButtonGroup grupoTipo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JLabel lbFiltro;
    private javax.swing.JLabel lbFiltro1;
    private javax.swing.JPanel pnlCombos;
    private javax.swing.JPanel pnlFormulario;
    private javax.swing.JPanel pnlPrincipal;
    private javax.swing.JRadioButton rdCantidadesProductos;
    private javax.swing.JRadioButton rdGeneral;
    private javax.swing.JRadioButton rdPorCliente;
    private javax.swing.JRadioButton rdPorGrupos;
    private javax.swing.JRadioButton rdPorMes;
    private javax.swing.JRadioButton rdPorVendedor;
    private javax.swing.JRadioButton rdValoresProductos;
    private javax.swing.JScrollPane scrFormulario;
    private javax.swing.JTable tblResultados;
    private javax.swing.JTextField txtCodGrupo;
    // End of variables declaration//GEN-END:variables
}
