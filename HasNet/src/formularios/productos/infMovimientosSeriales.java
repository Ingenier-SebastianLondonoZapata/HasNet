package formularios.productos;

import clases.Instancias;
import clases.big;
import clases.metodosGenerales;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class infMovimientosSeriales extends javax.swing.JPanel {

    String simbolo = "";
    metodosGenerales metodos = new metodosGenerales();
    private Instancias instancias;
    DefaultTableModel modelo;
    TableRowSorter modeloOrdenado;

    Object[][] ventas, compras, anulas, ajustesEntrada, ajustesSalida, prestado, congelada, separes, notasCredito;

    private Dimension dimBarra = null;

    public infMovimientosSeriales() {
        initComponents();

        instancias = Instancias.getInstancias();
        modelo = (DefaultTableModel) tblResultados.getModel();
        modeloOrdenado = new TableRowSorter<>(modelo);
        tblResultados.setRowSorter(modeloOrdenado);
        simbolo = instancias.getSimbolo();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoTipo = new javax.swing.ButtonGroup();
        jRadioButton1 = new javax.swing.JRadioButton();
        scrFormulario = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        lbNit = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel16 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblEntradas = new javax.swing.JTable();
        lbNit10 = new javax.swing.JLabel();
        lbNit12 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSalidas = new javax.swing.JTable();
        lbNit14 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblSepares = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblResultados = new javax.swing.JTable();
        lbTitulo = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtDocumento = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtNit = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();

        jRadioButton1.setText("jRadioButton1");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        lbNit.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit.setText("Serial: ");

        txtCodigo.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtCodigo.setName("Codigo"); // NOI18N
        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoKeyTyped(evt);
            }
        });

        btnBuscar.setBackground(new java.awt.Color(204, 204, 204));
        btnBuscar.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar1.png"))); // NOI18N
        btnBuscar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnBuscar.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 1076, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lbNit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbNit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));

        tblEntradas.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        tblEntradas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Compras", null, " VER DETALLES"},
                {"Ajustes. E", null, " VER DETALLES"},
                {"Anuladas", null, " VER DETALLES"},
                {"N. Credito", null, " VER DETALLES"}
            },
            new String [] {
                "", "", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblEntradas.setRowHeight(20);
        tblEntradas.getTableHeader().setReorderingAllowed(false);
        tblEntradas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEntradasMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblEntradas);
        if (tblEntradas.getColumnModel().getColumnCount() > 0) {
            tblEntradas.getColumnModel().getColumn(1).setMinWidth(25);
            tblEntradas.getColumnModel().getColumn(1).setPreferredWidth(50);
            tblEntradas.getColumnModel().getColumn(1).setMaxWidth(70);
        }

        lbNit10.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbNit10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit10.setText("ENTRADAS");

        lbNit12.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbNit12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit12.setText("SALIDAS");

        tblSalidas.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        tblSalidas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Ventas", null, " VER DETALLES"},
                {"Ajustes. S", null, " VER DETALLES"}
            },
            new String [] {
                "", "", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSalidas.setRowHeight(20);
        tblSalidas.getTableHeader().setReorderingAllowed(false);
        tblSalidas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSalidasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSalidas);
        if (tblSalidas.getColumnModel().getColumnCount() > 0) {
            tblSalidas.getColumnModel().getColumn(1).setMinWidth(25);
            tblSalidas.getColumnModel().getColumn(1).setPreferredWidth(50);
            tblSalidas.getColumnModel().getColumn(1).setMaxWidth(70);
        }

        lbNit14.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbNit14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit14.setText("SEPARES");

        tblSepares.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        tblSepares.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"P. Separe", null, " VER DETALLES"},
                {"Congeladas", null, " VER DETALLES"},
                {"Prestados", null, " VER DETALLES"}
            },
            new String [] {
                "", "", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSepares.setRowHeight(20);
        tblSepares.getTableHeader().setReorderingAllowed(false);
        tblSepares.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSeparesMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblSepares);
        if (tblSepares.getColumnModel().getColumnCount() > 0) {
            tblSepares.getColumnModel().getColumn(1).setMinWidth(25);
            tblSepares.getColumnModel().getColumn(1).setPreferredWidth(50);
            tblSepares.getColumnModel().getColumn(1).setMaxWidth(70);
        }

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbNit10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(lbNit12, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(lbNit14, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNit10)
                    .addComponent(lbNit12)
                    .addComponent(lbNit14))
                .addGap(3, 3, 3)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        tblResultados.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        tblResultados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "# Documento", "ID", "Descripcion ID", "F. Movimiento", "Subtotal", "Descuento", "Iva", "Total", "Usuario", "Nota"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblResultados.setRowHeight(20);
        tblResultados.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(tblResultados);
        if (tblResultados.getColumnModel().getColumnCount() > 0) {
            tblResultados.getColumnModel().getColumn(0).setMinWidth(80);
            tblResultados.getColumnModel().getColumn(0).setPreferredWidth(120);
            tblResultados.getColumnModel().getColumn(0).setMaxWidth(200);
            tblResultados.getColumnModel().getColumn(1).setMinWidth(100);
            tblResultados.getColumnModel().getColumn(1).setPreferredWidth(125);
            tblResultados.getColumnModel().getColumn(1).setMaxWidth(180);
            tblResultados.getColumnModel().getColumn(3).setMinWidth(99);
            tblResultados.getColumnModel().getColumn(3).setPreferredWidth(99);
            tblResultados.getColumnModel().getColumn(3).setMaxWidth(99);
            tblResultados.getColumnModel().getColumn(4).setMinWidth(80);
            tblResultados.getColumnModel().getColumn(4).setPreferredWidth(120);
            tblResultados.getColumnModel().getColumn(4).setMaxWidth(180);
            tblResultados.getColumnModel().getColumn(5).setMinWidth(80);
            tblResultados.getColumnModel().getColumn(5).setPreferredWidth(120);
            tblResultados.getColumnModel().getColumn(5).setMaxWidth(180);
            tblResultados.getColumnModel().getColumn(6).setMinWidth(80);
            tblResultados.getColumnModel().getColumn(6).setPreferredWidth(120);
            tblResultados.getColumnModel().getColumn(6).setMaxWidth(180);
            tblResultados.getColumnModel().getColumn(7).setMinWidth(80);
            tblResultados.getColumnModel().getColumn(7).setPreferredWidth(120);
            tblResultados.getColumnModel().getColumn(7).setMaxWidth(180);
            tblResultados.getColumnModel().getColumn(8).setMinWidth(80);
            tblResultados.getColumnModel().getColumn(8).setPreferredWidth(80);
            tblResultados.getColumnModel().getColumn(8).setMaxWidth(100);
            tblResultados.getColumnModel().getColumn(9).setMinWidth(0);
            tblResultados.getColumnModel().getColumn(9).setPreferredWidth(0);
            tblResultados.getColumnModel().getColumn(9).setMaxWidth(0);
        }

        lbTitulo.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        lbTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitulo.setText(" ");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addComponent(lbTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(lbTitulo)
                .addGap(5, 5, 5)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                .addGap(3, 3, 3))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel1.setText("# Documento:");

        txtDocumento.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtDocumento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDocumentoKeyReleased(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel2.setText("Nit:");

        txtNit.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtNit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNitKeyReleased(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel3.setText("Nombre:");

        txtNombre.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreKeyReleased(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel4.setText("Fecha:");

        txtFecha.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtFecha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFechaKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNit, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNombre)
                .addGap(11, 11, 11))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtFecha)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtDocumento)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNit)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNombre))
                .addGap(28, 28, 28))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        scrFormulario.setViewportView(jPanel2);

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

    private void txtCodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txtCodigo.getText().equals("")) {
                ventanaProductos(txtCodigo.getText());
            } else {
                cargarProducto(txtCodigo.getText());
            }
        } else {
            tblEntradas.setValueAt("", 0, 1);
            tblEntradas.setValueAt("", 1, 1);
            tblEntradas.setValueAt("", 2, 1);
            tblEntradas.setValueAt("", 3, 1);

            tblSalidas.setValueAt("", 0, 1);
            tblSalidas.setValueAt("", 1, 1);

            tblSepares.setValueAt("", 0, 1);
            tblSepares.setValueAt("", 1, 1);
            tblSepares.setValueAt("", 2, 1);

            txtCodigo.setText("");

            DefaultTableModel modelo1 = (DefaultTableModel) tblResultados.getModel();
            while (tblResultados.getRowCount() > 0) {
                modelo1.removeRow(0);
            }

            lbTitulo.setText("");
        }
    }//GEN-LAST:event_txtCodigoKeyReleased

    private void txtCodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyTyped

    }//GEN-LAST:event_txtCodigoKeyTyped

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        cargarProducto(txtCodigo.getText());
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void tblEntradasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEntradasMouseClicked
        if (!txtCodigo.getText().equals("")) {

            DefaultTableModel modelo = (DefaultTableModel) tblResultados.getModel();

            Object[][] resultados;
           

            if (tblEntradas.getSelectedColumn() == 2 && tblEntradas.getSelectedRow() == 0) {
                if (!lbTitulo.getText().equals("COMPRAS")) {
                    lbTitulo.setText("COMPRAS");

                    while (tblResultados.getRowCount() > 0) {
                        modelo.removeRow(0);
                    }

                    for (int i = 0; i < compras.length; i++) {
                        resultados = instancias.getSql().getComprasMovimientos(compras[i][0].toString());
                        modelo.addRow(new Object[]{resultados[0][0], resultados[0][1], resultados[0][2],
                            metodos.fecha(resultados[0][3].toString()),
                            big.setMoneda(big.getBigDecimal(resultados[0][4])),
                            big.setMoneda(big.getBigDecimal(resultados[0][5])),
                            big.setMoneda(big.getBigDecimal(resultados[0][6])),
                            big.setMoneda(big.getBigDecimal(resultados[0][7])),
                            resultados[0][8]
                        });
                    }
                    System.out.println(modelo);    

                    tblResultados.getColumnModel().getColumn(9).setMinWidth(0);
                    tblResultados.getColumnModel().getColumn(9).setPreferredWidth(0);
                    tblResultados.getColumnModel().getColumn(9).setMaxWidth(0);
                }
            } else if (tblEntradas.getSelectedColumn() == 2 && tblEntradas.getSelectedRow() == 1) {
                if (!lbTitulo.getText().equals("AJUSTES DE ENTRADA")) {
                    lbTitulo.setText("AJUSTES DE ENTRADA");

                    while (tblResultados.getRowCount() > 0) {
                        modelo.removeRow(0);
                    }

                    for (int i = 0; i < ajustesEntrada.length; i++) {
                        resultados = instancias.getSql().getAjustesMovimientos(ajustesEntrada[i][0].toString());
                        modelo.addRow(new Object[]{resultados[0][0], resultados[0][1], resultados[0][2],
                            metodos.fecha(resultados[0][3].toString()),
                            big.setMoneda(big.getMoneda(resultados[0][4].toString())),
                            this.simbolo + " 0", this.simbolo + " 0", big.setMoneda(big.getMoneda(resultados[0][4].toString())), resultados[0][5]
                        });
                    }

                    tblResultados.getColumnModel().getColumn(9).setMinWidth(0);
                    tblResultados.getColumnModel().getColumn(9).setPreferredWidth(0);
                    tblResultados.getColumnModel().getColumn(9).setMaxWidth(0);
                }
            } else if (tblEntradas.getSelectedColumn() == 2 && tblEntradas.getSelectedRow() == 2) {
                if (!lbTitulo.getText().equals("ANULADAS")) {
                    lbTitulo.setText("ANULADAS");

                    while (tblResultados.getRowCount() > 0) {
                        modelo.removeRow(0);
                    }

                    for (int i = 0; i < anulas.length; i++) {
                        resultados = instancias.getSql().getVentasMovimientos(anulas[i][0].toString(), true);
                        modelo.addRow(new Object[]{resultados[0][0], resultados[0][1], resultados[0][2],
                            metodos.fecha(resultados[0][3].toString()),
                            big.setMoneda(big.getBigDecimal(resultados[0][4])),
                            big.setMoneda(big.getBigDecimal(resultados[0][5])),
                            big.setMoneda(big.getBigDecimal(resultados[0][6])),
                            big.setMoneda(big.getBigDecimal(resultados[0][7])),
                            resultados[0][8], resultados[0][9]
                        });
                    }

                    tblResultados.getColumnModel().getColumn(9).setMinWidth(150);
                    tblResultados.getColumnModel().getColumn(9).setPreferredWidth(300);
                    tblResultados.getColumnModel().getColumn(9).setMaxWidth(500);
                    tblResultados.getColumnModel().getColumn(9).setMinWidth(150);
                    tblResultados.getColumnModel().getColumn(9).setPreferredWidth(300);
                    tblResultados.getColumnModel().getColumn(9).setMaxWidth(500);
                }
            } else if (tblEntradas.getSelectedColumn() == 2 && tblEntradas.getSelectedRow() == 3) {
                if (!lbTitulo.getText().equals("NOTAS CREDITO")) {
                    lbTitulo.setText("NOTAS CREDITO");

                    while (tblResultados.getRowCount() > 0) {
                        modelo.removeRow(0);
                    }

                    for (int i = 0; i < notasCredito.length; i++) {
                        resultados = instancias.getSql().getNotasCreditoMovimientos(notasCredito[i][0].toString());
                        modelo.addRow(new Object[]{resultados[0][0], resultados[0][1], resultados[0][2],
                            metodos.fecha(resultados[0][3].toString()),
                            big.setMoneda(big.getBigDecimal(resultados[0][4])),
                            this.simbolo + " 0", this.simbolo + " 0", this.simbolo + " 0", resultados[0][5]
                        });
                    }

                    tblResultados.getColumnModel().getColumn(9).setMinWidth(0);
                    tblResultados.getColumnModel().getColumn(9).setPreferredWidth(0);
                    tblResultados.getColumnModel().getColumn(9).setMaxWidth(0);
                }
            }
        }
    }//GEN-LAST:event_tblEntradasMouseClicked

    private void tblSalidasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSalidasMouseClicked
        if (!txtCodigo.getText().equals("")) {

            DefaultTableModel modelo = (DefaultTableModel) tblResultados.getModel();

            Object[][] resultados;

            if (tblSalidas.getSelectedColumn() == 2 && tblSalidas.getSelectedRow() == 0) {
                if (!lbTitulo.getText().equals("VENTAS")) {
                    lbTitulo.setText("VENTAS");

                    while (tblResultados.getRowCount() > 0) {
                        modelo.removeRow(0);
                    }

                    for (int i = 0; i < ventas.length; i++) {
                        resultados = instancias.getSql().getVentasMovimientos(ventas[i][0].toString(), true);
                        modelo.addRow(new Object[]{resultados[0][0], resultados[0][1], resultados[0][2],
                            metodos.fecha(resultados[0][3].toString()),
                            big.setMoneda(big.getBigDecimal(resultados[0][4])),
                            big.setMoneda(big.getBigDecimal(resultados[0][5])),
                            big.setMoneda(big.getBigDecimal(resultados[0][6])),
                            big.setMoneda(big.getBigDecimal(resultados[0][7])),
                            resultados[0][8]
                        });
                    }

                    tblResultados.getColumnModel().getColumn(9).setMinWidth(0);
                    tblResultados.getColumnModel().getColumn(9).setPreferredWidth(0);
                    tblResultados.getColumnModel().getColumn(9).setMaxWidth(0);
                }
            } else if (tblSalidas.getSelectedColumn() == 2 && tblSalidas.getSelectedRow() == 1) {
                if (!lbTitulo.getText().equals("AJUSTES DE SALIDA")) {
                    lbTitulo.setText("AJUSTES DE SALIDA");

                    while (tblResultados.getRowCount() > 0) {
                        modelo.removeRow(0);
                    }

                    for (int i = 0; i < ajustesSalida.length; i++) {
                        resultados = instancias.getSql().getAjustesMovimientos(ajustesSalida[i][0].toString());
                        modelo.addRow(new Object[]{resultados[0][0], resultados[0][1], resultados[0][2],
                            metodos.fecha(resultados[0][3].toString()),
                            big.setMoneda(big.getMoneda(resultados[0][4].toString())),
                            this.simbolo + " 0", this.simbolo + " 0", big.setMoneda(big.getMoneda(resultados[0][4].toString())), resultados[0][5]
                        });
                    }

                    tblResultados.getColumnModel().getColumn(9).setMinWidth(0);
                    tblResultados.getColumnModel().getColumn(9).setPreferredWidth(0);
                    tblResultados.getColumnModel().getColumn(9).setMaxWidth(0);
                }
            }
        }
    }//GEN-LAST:event_tblSalidasMouseClicked

    private void tblSeparesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSeparesMouseClicked
        if (!txtCodigo.getText().equals("")) {

            DefaultTableModel modelo = (DefaultTableModel) tblResultados.getModel();

            Object[][] resultados;

            if (tblSepares.getSelectedColumn() == 2 && tblSepares.getSelectedRow() == 0) {
                if (!lbTitulo.getText().equals("PLAN SEPARES")) {
                    lbTitulo.setText("PLAN SEPARES");

                    while (tblResultados.getRowCount() > 0) {
                        modelo.removeRow(0);
                    }

                    for (int i = 0; i < separes.length; i++) {
                        resultados = instancias.getSql().getSeparesMovimientos(separes[i][0].toString());
                        modelo.addRow(new Object[]{resultados[0][0], resultados[0][1], resultados[0][2],
                            metodos.fecha(resultados[0][3].toString()),
                            big.setMoneda(big.getBigDecimal(resultados[0][4])),
                            big.setMoneda(big.getBigDecimal(resultados[0][5])),
                            big.setMoneda(big.getBigDecimal(resultados[0][6])),
                            big.setMoneda(big.getBigDecimal(resultados[0][7])),
                            resultados[0][8]
                        });
                    }

                    tblResultados.getColumnModel().getColumn(9).setMinWidth(0);
                    tblResultados.getColumnModel().getColumn(9).setPreferredWidth(0);
                    tblResultados.getColumnModel().getColumn(9).setMaxWidth(0);
                }
            } else if (tblSepares.getSelectedColumn() == 2 && tblSepares.getSelectedRow() == 1) {
                if (!lbTitulo.getText().equals("CONGELADAS")) {
                    lbTitulo.setText("CONGELADAS");

                    while (tblResultados.getRowCount() > 0) {
                        modelo.removeRow(0);
                    }

                    for (int i = 0; i < congelada.length; i++) {
                        resultados = instancias.getSql().getCongeladasMovimientos(congelada[i][0].toString());
                        modelo.addRow(new Object[]{resultados[0][0], resultados[0][1], resultados[0][2],
                            metodos.fecha(resultados[0][3].toString()),
                            big.setMoneda(big.getBigDecimal(resultados[0][4])),
                            big.setMoneda(big.getBigDecimal(resultados[0][5])),
                            big.setMoneda(big.getBigDecimal(resultados[0][6])),
                            big.setMoneda(big.getBigDecimal(resultados[0][7])),
                            resultados[0][8]
                        });
                    }

                    tblResultados.getColumnModel().getColumn(9).setMinWidth(0);
                    tblResultados.getColumnModel().getColumn(9).setPreferredWidth(0);
                    tblResultados.getColumnModel().getColumn(9).setMaxWidth(0);
                }
            } else if (tblSepares.getSelectedColumn() == 2 && tblSepares.getSelectedRow() == 2) {
                if (!lbTitulo.getText().equals("PRESTADOS")) {
                    lbTitulo.setText("PRESTADOS");

                    while (tblResultados.getRowCount() > 0) {
                        modelo.removeRow(0);
                    }

                    for (int i = 0; i < prestado.length; i++) {
                        resultados = instancias.getSql().getPrestamosMovimientos(prestado[i][0].toString());
                        modelo.addRow(new Object[]{resultados[0][0], resultados[0][1], resultados[0][2],
                            metodos.fecha(resultados[0][3].toString()),
                            big.setMoneda(big.getMoneda(resultados[0][4].toString())),
                            this.simbolo + " 0", this.simbolo + " 0", big.setMoneda(big.getMoneda(resultados[0][4].toString())), resultados[0][5]
                        });
                    }

                    tblResultados.getColumnModel().getColumn(9).setMinWidth(0);
                    tblResultados.getColumnModel().getColumn(9).setPreferredWidth(0);
                    tblResultados.getColumnModel().getColumn(9).setMaxWidth(0);
                }
            }
        }
    }//GEN-LAST:event_tblSeparesMouseClicked

    private void txtDocumentoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDocumentoKeyReleased
        modeloOrdenado.setRowFilter(RowFilter.regexFilter("(?i)" + txtDocumento.getText(), 0));
        txtNombre.setText("");
        txtFecha.setText("");
        txtNit.setText("");
    }//GEN-LAST:event_txtDocumentoKeyReleased

    private void txtNitKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNitKeyReleased
        modeloOrdenado.setRowFilter(RowFilter.regexFilter("(?i)" + txtNit.getText(), 1));
        txtNombre.setText("");
        txtFecha.setText("");
        txtDocumento.setText("");
    }//GEN-LAST:event_txtNitKeyReleased

    private void txtNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyReleased
        modeloOrdenado.setRowFilter(RowFilter.regexFilter("(?i)" + txtNombre.getText(), 2));
        txtFecha.setText("");
        txtDocumento.setText("");
        txtNit.setText("");
    }//GEN-LAST:event_txtNombreKeyReleased

    private void txtFechaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFechaKeyReleased
        modeloOrdenado.setRowFilter(RowFilter.regexFilter("(?i)" + txtFecha.getText(), 3));
        txtNombre.setText("");
        txtDocumento.setText("");
        txtNit.setText("");
    }//GEN-LAST:event_txtFechaKeyReleased

    public void ventanaProductos(String codigo) {
        buscSeriales buscar = new buscSeriales(null, true, true, "");
        buscar.setOpc("producto");
        buscar.setLocationRelativeTo(null);
        instancias.setBuscSeriales(buscar);
        instancias.setCampoActual(txtCodigo);
        txtCodigo.requestFocus();
//        buscar.setInstancia(instancias);
        buscar.noEncontrado(codigo.replace("'", "//"));
        buscar.show();
    }

    public void cargarProducto(String cod) {

        //INVENTARIO INICIAL
        if (!cod.equals("")) {
            ventas = instancias.getSql().getProductosVentasImei(cod);
            congelada = instancias.getSql().getProductosCongeladaImei(cod);
            compras = instancias.getSql().getProductosComprasImei(cod);
            separes = instancias.getSql().getProductosSeparesImei(cod);
            notasCredito = instancias.getSql().getProductosNotasCreditoImei(cod);
            anulas = instancias.getSql().getProductosVentasAnuladasImei(cod);
            ajustesSalida = instancias.getSql().getProductosAjustes(cod, "Ajuste Salida");
            ajustesEntrada = instancias.getSql().getProductosAjustes(cod, "Ajustes Entrada");
            prestado = instancias.getSql().getProductosPrestamos(cod);

            tblEntradas.setValueAt(compras.length, 0, 1);
            tblEntradas.setValueAt(ajustesEntrada.length, 1, 1);
            tblEntradas.setValueAt(anulas.length, 2, 1);
            tblEntradas.setValueAt(notasCredito.length, 3, 1);
            tblSalidas.setValueAt(ventas.length, 0, 1);
            tblSalidas.setValueAt(ajustesSalida.length, 1, 1);
            tblSepares.setValueAt(separes.length, 0, 1);
            tblSepares.setValueAt(congelada.length, 1, 1);
            tblSepares.setValueAt(prestado.length, 2, 1);
        } else {
            ventanaProductos(cod);
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.ButtonGroup grupoTipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbNit;
    private javax.swing.JLabel lbNit10;
    private javax.swing.JLabel lbNit12;
    private javax.swing.JLabel lbNit14;
    private javax.swing.JLabel lbTitulo;
    private javax.swing.JScrollPane scrFormulario;
    private javax.swing.JTable tblEntradas;
    private javax.swing.JTable tblResultados;
    private javax.swing.JTable tblSalidas;
    private javax.swing.JTable tblSepares;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtDocumento;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtNit;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
