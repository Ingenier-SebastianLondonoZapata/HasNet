package Vista.Ventas;

import clases.Instancias;
import clases.cambiarColorTabla;
import clases.convertirNumeroALetras;
import clases.metodosGenerales;
import clases.productos.ndProducto;
import formularios.productos.buscProductos;
import Modelo.Ventas.OpcionPreparacion;
import Utilidades.Utilidades;
import Utilidades.Ventas.ParserPreparacion;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class VistaPreparacion extends javax.swing.JInternalFrame {

    metodosGenerales metodos = new metodosGenerales();
    private final Instancias instancias;
    String filas;
    String lista, codigoPrincipal, lugarDesde;
    boolean mesaCongelada1;
    TableRowSorter modeloOrdenado1;
    TableRowSorter modeloOrdenado2;
    DefaultTableModel tablaPrincipal;

    public JButton getBtnGuardar() {
        return btnGuardar;
    }

    public void setBtnGuardar(JButton btnGuardar) {
        this.btnGuardar = btnGuardar;
    }

    private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension dimBarra = null;

    convertirNumeroALetras convertirNumeroALetras = new convertirNumeroALetras();

    public VistaPreparacion() {
        initComponents();
        instancias = Instancias.getInstancias();
        tblProductosPrincipales.setDefaultRenderer(Object.class, new cambiarColorTabla(12, 0));

        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
        dimBarra = Barra.getPreferredSize();
        Barra.setSize(0, 0);
        Barra.setPreferredSize(new Dimension(0, 0));
        setBorder(null);
        repaint();

        tablaPrincipal = (DefaultTableModel) tblProductosPrincipales.getModel();
        DefaultTableModel modelo2 = (DefaultTableModel) tblAderezo.getModel();

        modeloOrdenado1 = new TableRowSorter<>(tablaPrincipal);
        tblProductosPrincipales.setRowSorter(modeloOrdenado1);

        modeloOrdenado2 = new TableRowSorter<>(modelo2);
        tblAderezo.setRowSorter(modeloOrdenado2);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        popBorrar = new javax.swing.JMenuItem();
        scrFormulario = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        pnlDetalles = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtAderezo = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtProducto = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtObservaciones = new javax.swing.JTextArea();
        btnGuardar = new javax.swing.JButton();
        pnlAderezos = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblAderezo = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtBuscar2 = new javax.swing.JTextField();
        pnlPrincipal = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblProductosPrincipales = new javax.swing.JTable();
        lbAdiciones = new javax.swing.JLabel();
        txtBuscar1 = new javax.swing.JTextField();
        txtCodProducto = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        btnBusProd = new javax.swing.JButton();
        lbTitulo = new javax.swing.JLabel();
        btnDevolverAFactura = new javax.swing.JLabel();
        lblDevolverAFactura = new javax.swing.JLabel();

        popBorrar.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        popBorrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/eliminar-cancelar-icono-4935-16.png"))); // NOI18N
        popBorrar.setText("Borrar");
        popBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                popBorrarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(popBorrar);

        setTitle("Factura");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        pnlDetalles.setBackground(new java.awt.Color(255, 255, 255));
        pnlDetalles.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel6.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("PREPARACIÓN");

        txtAderezo.setColumns(20);
        txtAderezo.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtAderezo.setLineWrap(true);
        txtAderezo.setRows(2);
        txtAderezo.setWrapStyleWord(true);
        txtAderezo.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtAderezo.setEnabled(false);
        jScrollPane5.setViewportView(txtAderezo);

        jLabel7.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("ADEREZOS");

        txtProducto.setColumns(20);
        txtProducto.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtProducto.setLineWrap(true);
        txtProducto.setRows(2);
        txtProducto.setWrapStyleWord(true);
        txtProducto.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtProducto.setEnabled(false);
        jScrollPane4.setViewportView(txtProducto);

        jLabel3.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("OBSERVACIONES");

        txtObservaciones.setColumns(20);
        txtObservaciones.setLineWrap(true);
        txtObservaciones.setRows(2);
        txtObservaciones.setWrapStyleWord(true);
        jScrollPane1.setViewportView(txtObservaciones);

        btnGuardar.setBackground(new java.awt.Color(46, 204, 113));
        btnGuardar.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
        btnGuardar.setText("GUARDAR");
        btnGuardar.setToolTipText("Ctrl+G");
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

        javax.swing.GroupLayout pnlDetallesLayout = new javax.swing.GroupLayout(pnlDetalles);
        pnlDetalles.setLayout(pnlDetallesLayout);
        pnlDetallesLayout.setHorizontalGroup(
            pnlDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetallesLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(pnlDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDetallesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnGuardar)
                .addGap(222, 222, 222))
        );
        pnlDetallesLayout.setVerticalGroup(
            pnlDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetallesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnGuardar)
                .addContainerGap())
        );

        pnlAderezos.setBackground(new java.awt.Color(255, 255, 255));
        pnlAderezos.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        tblAderezo.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        tblAderezo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Descripcion", "Sel"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblAderezo.setRowHeight(25);
        tblAderezo.getTableHeader().setReorderingAllowed(false);
        tblAderezo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAderezoMouseClicked(evt);
            }
        });
        tblAderezo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblAderezoKeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(tblAderezo);
        if (tblAderezo.getColumnModel().getColumnCount() > 0) {
            tblAderezo.getColumnModel().getColumn(0).setMinWidth(0);
            tblAderezo.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblAderezo.getColumnModel().getColumn(0).setMaxWidth(0);
            tblAderezo.getColumnModel().getColumn(2).setMinWidth(50);
            tblAderezo.getColumnModel().getColumn(2).setPreferredWidth(50);
            tblAderezo.getColumnModel().getColumn(2).setMaxWidth(50);
        }

        jLabel5.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("ADEREZO");

        jLabel16.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel16.setText("BUSCAR:");

        txtBuscar2.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtBuscar2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscar2KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout pnlAderezosLayout = new javax.swing.GroupLayout(pnlAderezos);
        pnlAderezos.setLayout(pnlAderezosLayout);
        pnlAderezosLayout.setHorizontalGroup(
            pnlAderezosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAderezosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlAderezosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlAderezosLayout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(pnlAderezosLayout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
                        .addGap(40, 40, 40))
                    .addGroup(pnlAderezosLayout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBuscar2)
                        .addContainerGap())))
        );
        pnlAderezosLayout.setVerticalGroup(
            pnlAderezosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAderezosLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlAderezosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addContainerGap())
        );

        pnlPrincipal.setBackground(new java.awt.Color(255, 255, 255));
        pnlPrincipal.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        tblProductosPrincipales.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        tblProductosPrincipales.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Principal", "Codigo", "Producto", "Cant", "Con", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblProductosPrincipales.setRowHeight(25);
        tblProductosPrincipales.getTableHeader().setReorderingAllowed(false);
        tblProductosPrincipales.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductosPrincipalesMouseClicked(evt);
            }
        });
        tblProductosPrincipales.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblProductosPrincipalesKeyReleased(evt);
            }
        });
        jScrollPane7.setViewportView(tblProductosPrincipales);
        if (tblProductosPrincipales.getColumnModel().getColumnCount() > 0) {
            tblProductosPrincipales.getColumnModel().getColumn(0).setMinWidth(0);
            tblProductosPrincipales.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblProductosPrincipales.getColumnModel().getColumn(0).setMaxWidth(0);
            tblProductosPrincipales.getColumnModel().getColumn(1).setMinWidth(0);
            tblProductosPrincipales.getColumnModel().getColumn(1).setPreferredWidth(0);
            tblProductosPrincipales.getColumnModel().getColumn(1).setMaxWidth(0);
            tblProductosPrincipales.getColumnModel().getColumn(3).setMinWidth(50);
            tblProductosPrincipales.getColumnModel().getColumn(3).setPreferredWidth(90);
            tblProductosPrincipales.getColumnModel().getColumn(3).setMaxWidth(130);
            tblProductosPrincipales.getColumnModel().getColumn(4).setMinWidth(45);
            tblProductosPrincipales.getColumnModel().getColumn(4).setPreferredWidth(45);
            tblProductosPrincipales.getColumnModel().getColumn(4).setMaxWidth(45);
            tblProductosPrincipales.getColumnModel().getColumn(5).setMinWidth(82);
            tblProductosPrincipales.getColumnModel().getColumn(5).setPreferredWidth(82);
            tblProductosPrincipales.getColumnModel().getColumn(5).setMaxWidth(82);
        }

        lbAdiciones.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbAdiciones.setText("ADICIONES:");

        txtBuscar1.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtBuscar1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscar1KeyReleased(evt);
            }
        });

        txtCodProducto.setName("combo"); // NOI18N
        txtCodProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodProductoActionPerformed(evt);
            }
        });
        txtCodProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodProductoKeyReleased(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Century Gothic", 1, 15)); // NOI18N
        jLabel13.setText("Filtrar:");

        btnBusProd.setBackground(new java.awt.Color(204, 204, 204));
        btnBusProd.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnBusProd.setForeground(new java.awt.Color(255, 255, 255));
        btnBusProd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar1.png"))); // NOI18N
        btnBusProd.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnBusProd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBusProd.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnBusProd.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnBusProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBusProdActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlPrincipalLayout = new javax.swing.GroupLayout(pnlPrincipal);
        pnlPrincipal.setLayout(pnlPrincipalLayout);
        pnlPrincipalLayout.setHorizontalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7)
                    .addGroup(pnlPrincipalLayout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnlPrincipalLayout.createSequentialGroup()
                        .addComponent(lbAdiciones)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCodProducto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBusProd, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlPrincipalLayout.setVerticalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtBuscar1)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                .addGap(3, 3, 3)
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlPrincipalLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lbAdiciones, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE))
                    .addComponent(txtCodProducto)
                    .addComponent(btnBusProd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10))
        );

        lbTitulo.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        lbTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitulo.setText("PRODUCTO");

        btnDevolverAFactura.setBackground(new java.awt.Color(255, 255, 255));
        btnDevolverAFactura.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnDevolverAFactura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/anterior.png"))); // NOI18N
        btnDevolverAFactura.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDevolverAFactura.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDevolverAFacturaMouseClicked(evt);
            }
        });

        lblDevolverAFactura.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblDevolverAFactura.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDevolverAFactura.setText("Volver");
        lblDevolverAFactura.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblDevolverAFactura.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDevolverAFacturaMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(pnlPrincipal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(pnlAderezos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlDetalles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnDevolverAFactura, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                            .addComponent(lblDevolverAFactura, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnDevolverAFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(lblDevolverAFactura)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlDetalles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlAderezos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(47, 47, 47))
        );

        scrFormulario.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
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

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void popBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popBorrarActionPerformed

    }//GEN-LAST:event_popBorrarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (!validarCantidades()) {
            return;
        }

        // Segmentos separados por "; " que leen ParserPreparacion y cargarDatos:
        // [0] aderezos, [1] opciones, [2] observaciones.
        String[] segmentos = {construirAderezos(), construirOpciones(), txtObservaciones.getText()};
        enviarPreparacion(unirSegmentos(segmentos));
    }//GEN-LAST:event_btnGuardarActionPerformed

    private static String unirSegmentos(String[] segmentos) {
        StringBuilder cadena = new StringBuilder();
        for (int i = 0; i < segmentos.length; i++) {
            if (i > 0) {
                cadena.append("; ");
            }
            cadena.append(segmentos[i]);
        }
        return cadena.toString();
    }

    private boolean validarCantidades() {
        for (int i = 0; i < tblProductosPrincipales.getRowCount(); i++) {
            try {
                BigDecimal cantidad = Utilidades.convertirBigDecimal(tblProductosPrincipales.getValueAt(i, 3).toString());
                tblProductosPrincipales.setValueAt(Utilidades.formatearCantidad(cantidad), i, 3);
            } catch (Exception e) {
                metodos.msgAdvertenciaAjustado(null, "Todos los productos deben de tener cantidades.");
                return false;
            }
        }
        return true;
    }

    private String construirAderezos() {
        StringBuilder aderezos = new StringBuilder();
        for (int i = 0; i < tblAderezo.getRowCount(); i++) {
            if ((Boolean) tblAderezo.getValueAt(i, 2)) {
                if (aderezos.length() > 0) {
                    aderezos.append(", ");
                }
                aderezos.append(tblAderezo.getValueAt(i, 0));
            }
        }
        return aderezos.toString();
    }

    private String construirOpciones() {
        StringBuilder opciones = new StringBuilder();
        for (int i = 0; i < tblProductosPrincipales.getRowCount(); i++) {
            String principal = tblProductosPrincipales.getValueAt(i, 0).toString();
            if (principal.equals("ADICION")) {
                principal = principal + "-" + i;
            }

            if (opciones.length() > 0) {
                opciones.append(", ");
            }
            opciones.append(principal).append("/")
                    .append(tblProductosPrincipales.getValueAt(i, 1)).append("/")
                    .append(tblProductosPrincipales.getValueAt(i, 3)).append("/ ")
                    .append(tblProductosPrincipales.getValueAt(i, 4));
        }
        return opciones.toString();
    }

    private void enviarPreparacion(String cadena) {
        if (lugarDesde.equals("pedido")) {
            instancias.getMenu().expandirMenu();
            instancias.getPedido().cargarPreparacion(filas, cadena, codigoPrincipal);
            instancias.getPedidoContenedor().setSelected(true);
        } else if (mesaCongelada1) {
            mesaCongelada1 = false;
            instancias.getMesa().getPnlFactura().cargarPreparacion(filas, cadena, codigoPrincipal);
            instancias.getMesa().setSelected(true);
        } else {
            instancias.getMenu().expandirMenu();
            instancias.getFactura().cargarPreparacion(filas, cadena, codigoPrincipal);
            instancias.getFacturaContenedor().setSelected(true);
        }
    }

    private void btnGuardarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnGuardarKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnGuardarActionPerformed(null);
        }
    }//GEN-LAST:event_btnGuardarKeyReleased

    private void tblAderezoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAderezoMouseClicked
        if ((Boolean) tblAderezo.getValueAt(tblAderezo.getSelectedRow(), 2) == false) {
            txtAderezo.setText(txtAderezo.getText() + tblAderezo.getValueAt(tblAderezo.getSelectedRow(), 1) + ", ");
            tblAderezo.setValueAt(true, tblAderezo.getSelectedRow(), 2);
        } else {
            String[] texto = txtAderezo.getText().split(", ");
            txtAderezo.setText("");
            for (int i = 0; i < texto.length; i++) {
                if (!texto[i].equals(tblAderezo.getValueAt(tblAderezo.getSelectedRow(), 1))) {
                    txtAderezo.setText(txtAderezo.getText() + texto[i] + ", ");
                }
            }
            tblAderezo.setValueAt(false, tblAderezo.getSelectedRow(), 2);
        }
    }//GEN-LAST:event_tblAderezoMouseClicked

    private void tblAderezoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblAderezoKeyReleased

    }//GEN-LAST:event_tblAderezoKeyReleased

    private void txtBuscar2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscar2KeyReleased
        modeloOrdenado2.setRowFilter(RowFilter.regexFilter("(?i)" + txtBuscar2.getText(), 1));
    }//GEN-LAST:event_txtBuscar2KeyReleased

    private void tblProductosPrincipalesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductosPrincipalesMouseClicked
        if (tblProductosPrincipales.getSelectedColumn() == 5) {
            if (tblProductosPrincipales.getValueAt(tblProductosPrincipales.getSelectedRow(), 5).equals(" CAMBIAR")) {
                VistaProductosCambio relacionados = new VistaProductosCambio(null, true,
                        tblProductosPrincipales.getValueAt(tblProductosPrincipales.getSelectedRow(), 0).toString(), codigoPrincipal,
                        String.valueOf(tblProductosPrincipales.getSelectedRow()),
                        tblProductosPrincipales.getValueAt(tblProductosPrincipales.getSelectedRow(), 2).toString());
                relacionados.setLocationRelativeTo(null);
                relacionados.setVisible(true);
            } else if (tblProductosPrincipales.getValueAt(tblProductosPrincipales.getSelectedRow(), 5).equals("   QUITAR")) {
                tablaPrincipal.removeRow(tblProductosPrincipales.getSelectedRow());
            }
        }

        if (tblProductosPrincipales.getSelectedColumn() == 4) {

            if (tblProductosPrincipales.getValueAt(tblProductosPrincipales.getSelectedRow(), 5).equals("   QUITAR")) {
                tablaPrincipal.removeRow(tblProductosPrincipales.getSelectedRow());
                return;
            }

            if ((Boolean) tblProductosPrincipales.getValueAt(tblProductosPrincipales.getSelectedRow(), 4)) {
                String[] texto = txtProducto.getText().split(", ");
                txtProducto.setText("");
                for (int i = 0; i < texto.length; i++) {
                    if (!texto[i].equals("Sin " + tblProductosPrincipales.getValueAt(tblProductosPrincipales.getSelectedRow(), 2))) {
                        txtProducto.setText(txtProducto.getText() + texto[i] + ", ");
                    }
                }
                tblProductosPrincipales.setValueAt(true, tblProductosPrincipales.getSelectedRow(), 4);
            } else {
                txtProducto.setText(txtProducto.getText() + "Sin " + tblProductosPrincipales.getValueAt(tblProductosPrincipales.getSelectedRow(), 2) + ", ");
                tblProductosPrincipales.setValueAt(false, tblProductosPrincipales.getSelectedRow(), 4);
            }
        }
    }//GEN-LAST:event_tblProductosPrincipalesMouseClicked

    private void tblProductosPrincipalesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblProductosPrincipalesKeyReleased

        if (evt.getKeyCode() == KeyEvent.VK_ENTER && tblProductosPrincipales.getSelectedColumn() == 3) {
            tblProductosPrincipales.setValueAt(
                    tblProductosPrincipales.getValueAt(tblProductosPrincipales.getSelectedRow(), 3).toString().replace(",", "."),
                    tblProductosPrincipales.getSelectedRow(), 3);
        }
    }//GEN-LAST:event_tblProductosPrincipalesKeyReleased

    private void txtBuscar1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscar1KeyReleased
        modeloOrdenado1.setRowFilter(RowFilter.regexFilter("(?i)" + txtBuscar1.getText(), 2));
    }//GEN-LAST:event_txtBuscar1KeyReleased

    private void btnDevolverAFacturaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDevolverAFacturaMouseClicked
        if (lugarDesde.equals("pedido")) {
            instancias.getMenu().expandirMenu();
            instancias.getPedidoContenedor().setSelected(true);
        } else {
            if (mesaCongelada1) {
                mesaCongelada1 = false;
                instancias.getMesa().setSelected(true);
            } else {
                instancias.getFacturaContenedor().setSelected(true);
                instancias.getMenu().expandirMenu();
            }
        }
    }//GEN-LAST:event_btnDevolverAFacturaMouseClicked

    private void lblDevolverAFacturaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDevolverAFacturaMouseClicked
        if (lugarDesde.equals("pedido")) {
            instancias.getMenu().expandirMenu();
            instancias.getPedidoContenedor().setSelected(true);
        } else {
            if (mesaCongelada1) {
                mesaCongelada1 = false;
                instancias.getMesa().setSelected(true);
            } else {
                instancias.getFacturaContenedor().setSelected(true);
                instancias.getMenu().expandirMenu();
            }
        }
    }//GEN-LAST:event_lblDevolverAFacturaMouseClicked

    private void txtCodProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodProductoActionPerformed

    }//GEN-LAST:event_txtCodProductoActionPerformed

    private void txtCodProductoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodProductoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String codigo = txtCodProducto.getText().replace("'", "//");
            cargarProducto(codigo, "1");
        } else if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            btnGuardar.requestFocus();
        }
    }//GEN-LAST:event_txtCodProductoKeyReleased

    private void btnBusProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBusProdActionPerformed
        ventanaProductos("");
    }//GEN-LAST:event_btnBusProdActionPerformed

    public void cargarProducto(String codigo, String cantidad) {

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
            if (nodo.getCodigo() != null) {
                CodigoProd = nodo.getCodigo();
            }
        }

        if (!CodigoProd.equals("")) {

            if (instancias.getSql().getProdActivo(nodo.getCodigo())) {
                metodos.msgError(null, "Este producto esta inactivo");
                lbAdiciones.requestFocus();
                return;
            }

            tablaPrincipal.addRow(new Object[]{"ADICION", nodo.getIdSistema(), nodo.getDescripcion(), "1", true, "   QUITAR"});
            txtCodProducto.setText("");
            return;
        }

        ventanaProductos(codigo);
    }

    public void ventanaProductos(String codigo) {
        buscProductos buscar = new buscProductos(null, true, false, "adiciones", "productos1");
        buscar.setLocationRelativeTo(null);
        instancias.setBusProductos(buscar);
        instancias.setCampoActual(txtCodProducto);
        buscar.noEncontrado(codigo.replace("'", "//"));
        buscar.show();
    }

    public void cambiarDeProducto(int fila, String cod, String desc, String cant) {
        tblProductosPrincipales.setValueAt(cod, fila, 1);
        tblProductosPrincipales.setValueAt(desc, fila, 2);
        tblProductosPrincipales.setValueAt(cant, fila, 3);
    }

    public void cargarDatos(String prod, String lista, boolean mesaCongelada, String fila, String lugar) {

        if (lugar.equals("pedido")) {
            lugarDesde = lugar;
        } else {
            lugarDesde = "";
        }

        txtObservaciones.setText("");
        txtProducto.setText("");
        txtAderezo.setText("");

        filas = fila;
        codigoPrincipal = prod;
        mesaCongelada1 = mesaCongelada;

        ndProducto nodo = instancias.getSql().getDatosProducto(prod, "bdProductos");
        if (nodo.getCodigo() != null) {
            lbTitulo.setText(nodo.getDescripcion());
        }

        cargarTablas();

        if (lista.equals("")) {
            return;
        }

        marcarAderezosSeleccionados(ParserPreparacion.aderezos(lista));

        List<OpcionPreparacion> opciones = ParserPreparacion.opciones(lista);
        aplicarCambiosYAdiciones(opciones);
        aplicarOpcionesAComponentesBase(opciones);

        txtObservaciones.setText(ParserPreparacion.observaciones(lista));
    }

    /**
     * Marca en la tabla de aderezos los que vienen en la preparación.
     */
    private void marcarAderezosSeleccionados(String aderezos) {
        for (String aderezo : aderezos.split(", ")) {
            for (int j = 0; j < tblAderezo.getRowCount(); j++) {
                if (tblAderezo.getValueAt(j, 0).equals(aderezo)) {
                    tblAderezo.setValueAt(true, j, 2);
                    txtAderezo.setText(txtAderezo.getText() + tblAderezo.getValueAt(j, 1) + ", ");
                }
            }
        }
    }

    /**
     * Primera pasada: si el "principal" de la opción coincide con un producto
     * de la tabla, aplica el cambio (código/descripción/cantidad/estado); si no
     * coincide y es una adición, la agrega como fila nueva.
     */
    private void aplicarCambiosYAdiciones(List<OpcionPreparacion> opciones) {
        for (OpcionPreparacion opcion : opciones) {
            int fila = filaDeProductoPrincipal(opcion.getPrincipal());

            if (fila >= 0) {
                ndProducto nodo = instancias.getSql().getDatosProducto(opcion.getCodigo(), "bdProductos");
                tblProductosPrincipales.setValueAt(opcion.getCodigo(), fila, 1);
                tblProductosPrincipales.setValueAt(nodo.getDescripcion(), fila, 2);
                tblProductosPrincipales.setValueAt(opcion.getCantidad(), fila, 3);
                marcarEstado(fila, opcion.activa());
            } else if (opcion.getPrincipal().contains("ADICION")) {
                ndProducto nodo = instancias.getSql().getDatosProducto(opcion.getCodigo(), "bdProductos");
                tablaPrincipal.addRow(new Object[]{"ADICION", opcion.getCodigo(), nodo.getDescripcion(), opcion.getCantidad(), true, "   QUITAR"});
            }
        }
    }

    /**
     * Segunda pasada: aplica cantidad y estado a los componentes base del
     * discosteo (filas sin opción de cambio/quitar, col. 5 vacía) cuyo código
     * coincide con el de la opción.
     */
    private void aplicarOpcionesAComponentesBase(List<OpcionPreparacion> opciones) {
        for (OpcionPreparacion opcion : opciones) {
            for (int j = 0; j < tblProductosPrincipales.getRowCount(); j++) {
                if (tblProductosPrincipales.getValueAt(j, 1).equals(opcion.getCodigo())
                        && tblProductosPrincipales.getValueAt(j, 5).equals("")) {
                    tblProductosPrincipales.setValueAt(opcion.getCantidad(), j, 3);
                    marcarEstado(j, opcion.activa());
                    break;
                }
            }
        }
    }

    /**
     * Fila cuyo producto principal (col. 0) coincide con el código dado, o -1.
     */
    private int filaDeProductoPrincipal(String principal) {
        if (principal.equals("")) {
            return -1;
        }
        for (int j = 0; j < tblProductosPrincipales.getRowCount(); j++) {
            if (tblProductosPrincipales.getValueAt(j, 0).equals(principal)) {
                return j;
            }
        }
        return -1;
    }

    /**
     * Marca la fila como activa/inactiva (col. 4); si se quitó, lo anota en
     * txtProducto.
     */
    private void marcarEstado(int fila, boolean activa) {
        tblProductosPrincipales.setValueAt(activa, fila, 4);
        if (!activa) {
            txtProducto.setText(txtProducto.getText() + "Sin " + tblProductosPrincipales.getValueAt(fila, 2) + ", ");
        }
    }

    public void cargarTablas() {

        boolean conImpuestos = false;
        if (instancias.isPvpConIva()) {
            conImpuestos = true;
        }

        Object[][] listado1 = instancias.getSql().getDatosProductosGrupo("GRP-03", conImpuestos);
        DefaultTableModel model1 = (DefaultTableModel) tblAderezo.getModel();
        while (tblAderezo.getRowCount() > 0) {
            model1.removeRow(0);
        }
        if (listado1.length > 0) {
            for (int i = 0; i < listado1.length; i++) {
                model1.addRow(new Object[]{listado1[i][4], listado1[i][1], false});
            }
        }

        while (tblProductosPrincipales.getRowCount() > 0) {
            tablaPrincipal.removeRow(0);
        }

        Object[][] datos = instancias.getSql().getDatosProductosDiscosteo(codigoPrincipal);
        if (datos.length > 0) {
            for (int i = 0; i < datos.length; i++) {
                tablaPrincipal.addRow(new Object[]{datos[i][0], datos[i][0], datos[i][3], datos[i][1], true, " CAMBIAR"});
            }
        }

        /* CARGAMOS LOS INGREDIENTES DEL PRODUCTO */
        Object[][] datos1 = instancias.getSql().getDatosProductosDiscosteo1(codigoPrincipal);
        if (datos1.length > 0) {
            for (int i = 0; i < datos1.length; i++) {
                tablaPrincipal.addRow(new Object[]{"", datos1[i][0], datos1[i][3], datos1[i][1], true, ""});
            }
        }
        /* TERMINAMOS DE CARGAR LOS PRODUCTOS */
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBusProd;
    private javax.swing.JLabel btnDevolverAFactura;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JLabel lbAdiciones;
    private javax.swing.JLabel lbTitulo;
    private javax.swing.JLabel lblDevolverAFactura;
    private javax.swing.JPanel pnlAderezos;
    private javax.swing.JPanel pnlDetalles;
    private javax.swing.JPanel pnlPrincipal;
    private javax.swing.JMenuItem popBorrar;
    private javax.swing.JScrollPane scrFormulario;
    private javax.swing.JTable tblAderezo;
    private javax.swing.JTable tblProductosPrincipales;
    private javax.swing.JTextArea txtAderezo;
    private javax.swing.JTextField txtBuscar1;
    private javax.swing.JTextField txtBuscar2;
    private javax.swing.JTextField txtCodProducto;
    private javax.swing.JTextArea txtObservaciones;
    private javax.swing.JTextArea txtProducto;
    // End of variables declaration//GEN-END:variables
}
