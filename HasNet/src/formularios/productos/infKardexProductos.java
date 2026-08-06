package formularios.productos;

import Vista.Productos.VistaBuscadorProductos;
import clases.Instancias;
import Utilidades.BaseDatos.SQL;
import clases.big;
import clases.metodosGenerales;
import clases.productos.ndProducto;
import Modelo.Maestra.modeloConfiguracion;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class infKardexProductos extends javax.swing.JInternalFrame {

    metodosGenerales metodos = new metodosGenerales();
    private Instancias instancias;
    private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension dimBarra = null;
    Object[][] grupos;
    modeloConfiguracion nodoConf;
    TableRowSorter modeloOrdenado;
    String simbolo = "";

    public infKardexProductos() {

        initComponents();

        instancias = Instancias.getInstancias();
        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
        dimBarra = Barra.getPreferredSize();
        Barra.setSize(0, 0);
        Barra.setPreferredSize(new Dimension(0, 0));
        setBorder(null);
        repaint();

        DefaultTableModel modelo = (DefaultTableModel) tblResultados.getModel();
        modeloOrdenado = new TableRowSorter<>(modelo);
        tblResultados.setRowSorter(modeloOrdenado);

        pnlInvisible.setVisible(false);
        pnlHistorialCostos.setVisible(false);

        simbolo = instancias.getSimbolo();

        if (!instancias.getConfiguraciones().isInventarioBodegas()) {
            tabPrincipal.remove(4);
            tabPrincipal.remove(3);
            tabPrincipal.remove(2);
            tabPrincipal.remove(1);
        }
    }

    private ActionListener accion(final String opc) {
        ActionListener a = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (opc) {

                }
            }
        };
        return a;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrFormulario = new javax.swing.JScrollPane();
        pnlFormulario = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        lbNit = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        lbNit1 = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        btnBuscar1 = new javax.swing.JButton();
        jPanel16 = new javax.swing.JPanel();
        lbFoto = new javax.swing.JLabel();
        tabPrincipal = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        lbNit11 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblEntradas = new javax.swing.JTable();
        lbNit17 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblSalidas = new javax.swing.JTable();
        lbNit18 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblSepares = new javax.swing.JTable();
        lbNit15 = new javax.swing.JLabel();
        txtInventario = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        lbNit16 = new javax.swing.JLabel();
        txtFisicoInventario = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        lbNit12 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tblEntradas1 = new javax.swing.JTable();
        lbNit19 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        tblSalidas1 = new javax.swing.JTable();
        lbNit20 = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        tblSepares1 = new javax.swing.JTable();
        lbNit21 = new javax.swing.JLabel();
        txtInventario1 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        lbNit22 = new javax.swing.JLabel();
        txtFisicoInventario1 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        lbNit14 = new javax.swing.JLabel();
        jScrollPane12 = new javax.swing.JScrollPane();
        tblEntradas2 = new javax.swing.JTable();
        lbNit23 = new javax.swing.JLabel();
        jScrollPane13 = new javax.swing.JScrollPane();
        tblSalidas2 = new javax.swing.JTable();
        lbNit24 = new javax.swing.JLabel();
        jScrollPane14 = new javax.swing.JScrollPane();
        tblSepares2 = new javax.swing.JTable();
        lbNit25 = new javax.swing.JLabel();
        txtInventario2 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        lbNit26 = new javax.swing.JLabel();
        txtFisicoInventario2 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        lbNit27 = new javax.swing.JLabel();
        jScrollPane15 = new javax.swing.JScrollPane();
        tblEntradas3 = new javax.swing.JTable();
        lbNit48 = new javax.swing.JLabel();
        jScrollPane16 = new javax.swing.JScrollPane();
        tblSalidas3 = new javax.swing.JTable();
        lbNit49 = new javax.swing.JLabel();
        jScrollPane17 = new javax.swing.JScrollPane();
        tblSepares3 = new javax.swing.JTable();
        lbNit50 = new javax.swing.JLabel();
        txtInventario3 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        lbNit51 = new javax.swing.JLabel();
        txtFisicoInventario3 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        lbNit52 = new javax.swing.JLabel();
        jScrollPane30 = new javax.swing.JScrollPane();
        tblEntradas4 = new javax.swing.JTable();
        lbNit53 = new javax.swing.JLabel();
        jScrollPane31 = new javax.swing.JScrollPane();
        tblSalidas4 = new javax.swing.JTable();
        lbNit54 = new javax.swing.JLabel();
        jScrollPane32 = new javax.swing.JScrollPane();
        tblSepares4 = new javax.swing.JTable();
        lbNit55 = new javax.swing.JLabel();
        txtInventario4 = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        lbNit56 = new javax.swing.JLabel();
        txtFisicoInventario4 = new javax.swing.JLabel();
        btnHistorial = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblResultados = new javax.swing.JTable();
        lbTitulo = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNit = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtDocumento = new javax.swing.JTextField();
        txtFecha = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        pnlInvisible = new javax.swing.JPanel();
        txtIdSistema = new javax.swing.JTextField();
        pnlHistorialCostos = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblPonderado = new javax.swing.JTable();
        lbNit13 = new javax.swing.JLabel();

        setTitle("Factura");

        pnlFormulario.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 0, 14))); // NOI18N

        lbNit.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit.setText("Codigo:");

        txtCodigo.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
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

        lbNit1.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbNit1.setText("Descripción:");

        txtDescripcion.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtDescripcion.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtDescripcion.setEnabled(false);
        txtDescripcion.setName("Codigo"); // NOI18N
        txtDescripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDescripcionKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDescripcionKeyTyped(evt);
            }
        });

        btnBuscar1.setBackground(new java.awt.Color(204, 204, 204));
        btnBuscar1.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnBuscar1.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/borrar.png"))); // NOI18N
        btnBuscar1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnBuscar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscar1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnBuscar1.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnBuscar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbNit1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbNit, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtCodigo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                    .addComponent(lbNit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBuscar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbNit1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));

        lbFoto.setBackground(new java.awt.Color(204, 204, 204));
        lbFoto.setToolTipText("Esta herramienta solo recibe formatos JPG y PNG. Al montar la imagen recibe el tamaño de 250x250.");
        lbFoto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lbFoto.setMaximumSize(new java.awt.Dimension(200, 200));
        lbFoto.setMinimumSize(new java.awt.Dimension(200, 200));
        lbFoto.setName(""); // NOI18N
        lbFoto.setOpaque(true);
        lbFoto.setPreferredSize(new java.awt.Dimension(200, 200));
        lbFoto.setRequestFocusEnabled(false);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        lbNit11.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbNit11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit11.setText("ENTRADAS");

        tblEntradas.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        tblEntradas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Inv. Inicial", null, " VER DETALLES"},
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
        jScrollPane6.setViewportView(tblEntradas);
        if (tblEntradas.getColumnModel().getColumnCount() > 0) {
            tblEntradas.getColumnModel().getColumn(0).setMinWidth(40);
            tblEntradas.getColumnModel().getColumn(0).setPreferredWidth(140);
            tblEntradas.getColumnModel().getColumn(0).setMaxWidth(140);
            tblEntradas.getColumnModel().getColumn(1).setMinWidth(40);
            tblEntradas.getColumnModel().getColumn(1).setPreferredWidth(40);
            tblEntradas.getColumnModel().getColumn(1).setMaxWidth(100);
            tblEntradas.getColumnModel().getColumn(2).setMinWidth(100);
            tblEntradas.getColumnModel().getColumn(2).setPreferredWidth(100);
            tblEntradas.getColumnModel().getColumn(2).setMaxWidth(100);
        }

        lbNit17.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbNit17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit17.setText("SALIDAS");

        tblSalidas.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        tblSalidas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Ventas", null, " VER DETALLES"},
                {"Ajustes. S", null, " VER DETALLES"},
                {"Costeo", null, " VER DETALLES"},
                {"Armado", null, " VER DETALLES"}
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
        jScrollPane7.setViewportView(tblSalidas);
        if (tblSalidas.getColumnModel().getColumnCount() > 0) {
            tblSalidas.getColumnModel().getColumn(0).setMinWidth(40);
            tblSalidas.getColumnModel().getColumn(0).setPreferredWidth(140);
            tblSalidas.getColumnModel().getColumn(0).setMaxWidth(140);
            tblSalidas.getColumnModel().getColumn(1).setMinWidth(40);
            tblSalidas.getColumnModel().getColumn(1).setPreferredWidth(40);
            tblSalidas.getColumnModel().getColumn(1).setMaxWidth(100);
            tblSalidas.getColumnModel().getColumn(2).setMinWidth(100);
            tblSalidas.getColumnModel().getColumn(2).setPreferredWidth(100);
            tblSalidas.getColumnModel().getColumn(2).setMaxWidth(100);
        }

        lbNit18.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbNit18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit18.setText("SEPARES");

        tblSepares.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        tblSepares.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"P. Separe", null, " VER DETALLES"},
                {"O. Servicio", null, " VER DETALLES"},
                {"Pedidos", null, " VER DETALLES"},
                {"Congeladas", null, " VER DETALLES"},
                {"Traslados Bod", null, " VER DETALLES"}
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
        tblSepares.setRowHeight(20);
        tblSepares.getTableHeader().setReorderingAllowed(false);
        tblSepares.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSeparesMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tblSepares);
        if (tblSepares.getColumnModel().getColumnCount() > 0) {
            tblSepares.getColumnModel().getColumn(0).setMinWidth(40);
            tblSepares.getColumnModel().getColumn(0).setPreferredWidth(140);
            tblSepares.getColumnModel().getColumn(0).setMaxWidth(140);
            tblSepares.getColumnModel().getColumn(1).setMinWidth(40);
            tblSepares.getColumnModel().getColumn(1).setPreferredWidth(40);
            tblSepares.getColumnModel().getColumn(1).setMaxWidth(100);
            tblSepares.getColumnModel().getColumn(2).setMinWidth(100);
            tblSepares.getColumnModel().getColumn(2).setPreferredWidth(100);
            tblSepares.getColumnModel().getColumn(2).setMaxWidth(100);
        }

        lbNit15.setFont(new java.awt.Font("Century Gothic", 2, 20)); // NOI18N
        lbNit15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNit15.setText("INVENTARIO:");

        txtInventario.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        txtInventario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lbNit16.setFont(new java.awt.Font("Century Gothic", 2, 20)); // NOI18N
        lbNit16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNit16.setText("FISICO INVENTARIO:");

        txtFisicoInventario.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        txtFisicoInventario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 711, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(lbNit11, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbNit17, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(lbNit18, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(lbNit15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtInventario, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
                        .addComponent(lbNit16, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFisicoInventario, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbNit15, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtInventario, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbNit16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtFisicoInventario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(2, 2, 2)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(lbNit18)
                        .addGap(3, 3, 3)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(lbNit17)
                        .addGap(3, 3, 3)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(lbNit11)
                        .addGap(3, 3, 3)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15))
        );

        tabPrincipal.addTab("Bodega Principal", jPanel5);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        lbNit12.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbNit12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit12.setText("ENTRADAS");

        tblEntradas1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        tblEntradas1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Inv. Inicial", null, " VER DETALLES"},
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
        tblEntradas1.setRowHeight(20);
        tblEntradas1.getTableHeader().setReorderingAllowed(false);
        tblEntradas1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEntradas1MouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(tblEntradas1);
        if (tblEntradas1.getColumnModel().getColumnCount() > 0) {
            tblEntradas1.getColumnModel().getColumn(0).setMinWidth(40);
            tblEntradas1.getColumnModel().getColumn(0).setPreferredWidth(140);
            tblEntradas1.getColumnModel().getColumn(0).setMaxWidth(140);
            tblEntradas1.getColumnModel().getColumn(1).setMinWidth(40);
            tblEntradas1.getColumnModel().getColumn(1).setPreferredWidth(40);
            tblEntradas1.getColumnModel().getColumn(1).setMaxWidth(100);
            tblEntradas1.getColumnModel().getColumn(2).setMinWidth(100);
            tblEntradas1.getColumnModel().getColumn(2).setPreferredWidth(100);
            tblEntradas1.getColumnModel().getColumn(2).setMaxWidth(100);
        }

        lbNit19.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbNit19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit19.setText("SALIDAS");

        tblSalidas1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        tblSalidas1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Ventas", null, " VER DETALLES"},
                {"Ajustes. S", null, " VER DETALLES"},
                {"Costeo", null, " VER DETALLES"},
                {"Armado", null, " VER DETALLES"}
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
        tblSalidas1.setRowHeight(20);
        tblSalidas1.getTableHeader().setReorderingAllowed(false);
        tblSalidas1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSalidas1MouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(tblSalidas1);
        if (tblSalidas1.getColumnModel().getColumnCount() > 0) {
            tblSalidas1.getColumnModel().getColumn(0).setMinWidth(40);
            tblSalidas1.getColumnModel().getColumn(0).setPreferredWidth(140);
            tblSalidas1.getColumnModel().getColumn(0).setMaxWidth(140);
            tblSalidas1.getColumnModel().getColumn(1).setMinWidth(40);
            tblSalidas1.getColumnModel().getColumn(1).setPreferredWidth(40);
            tblSalidas1.getColumnModel().getColumn(1).setMaxWidth(100);
            tblSalidas1.getColumnModel().getColumn(2).setMinWidth(100);
            tblSalidas1.getColumnModel().getColumn(2).setPreferredWidth(100);
            tblSalidas1.getColumnModel().getColumn(2).setMaxWidth(100);
        }

        lbNit20.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbNit20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit20.setText("SEPARES");

        tblSepares1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        tblSepares1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"P. Separe", null, " VER DETALLES"},
                {"O. Servicio", null, " VER DETALLES"},
                {"Pedidos", null, " VER DETALLES"},
                {"Congeladas", null, " VER DETALLES"},
                {"Traslados Bod", null, " VER DETALLES"}
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
        tblSepares1.setRowHeight(20);
        tblSepares1.getTableHeader().setReorderingAllowed(false);
        tblSepares1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSepares1MouseClicked(evt);
            }
        });
        jScrollPane11.setViewportView(tblSepares1);
        if (tblSepares1.getColumnModel().getColumnCount() > 0) {
            tblSepares1.getColumnModel().getColumn(0).setMinWidth(40);
            tblSepares1.getColumnModel().getColumn(0).setPreferredWidth(140);
            tblSepares1.getColumnModel().getColumn(0).setMaxWidth(140);
            tblSepares1.getColumnModel().getColumn(1).setMinWidth(40);
            tblSepares1.getColumnModel().getColumn(1).setPreferredWidth(40);
            tblSepares1.getColumnModel().getColumn(1).setMaxWidth(100);
            tblSepares1.getColumnModel().getColumn(2).setMinWidth(100);
            tblSepares1.getColumnModel().getColumn(2).setPreferredWidth(100);
            tblSepares1.getColumnModel().getColumn(2).setMaxWidth(100);
        }

        lbNit21.setFont(new java.awt.Font("Century Gothic", 2, 20)); // NOI18N
        lbNit21.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNit21.setText("INVENTARIO:");

        txtInventario1.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        txtInventario1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lbNit22.setFont(new java.awt.Font("Century Gothic", 2, 20)); // NOI18N
        lbNit22.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNit22.setText("FISICO INVENTARIO:");

        txtFisicoInventario1.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        txtFisicoInventario1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 711, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(lbNit12, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbNit19, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
                            .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(lbNit20, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(lbNit21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtInventario1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
                        .addComponent(lbNit22, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFisicoInventario1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbNit21, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtInventario1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbNit22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtFisicoInventario1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(2, 2, 2)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(lbNit20)
                        .addGap(3, 3, 3)
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(lbNit19)
                        .addGap(3, 3, 3)
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(lbNit12)
                        .addGap(3, 3, 3)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15))
        );

        tabPrincipal.addTab("Bodega 1", jPanel6);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        lbNit14.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbNit14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit14.setText("ENTRADAS");

        tblEntradas2.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        tblEntradas2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Inv. Inicial", null, " VER DETALLES"},
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
        tblEntradas2.setRowHeight(20);
        tblEntradas2.getTableHeader().setReorderingAllowed(false);
        tblEntradas2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEntradas2MouseClicked(evt);
            }
        });
        jScrollPane12.setViewportView(tblEntradas2);
        if (tblEntradas2.getColumnModel().getColumnCount() > 0) {
            tblEntradas2.getColumnModel().getColumn(0).setMinWidth(40);
            tblEntradas2.getColumnModel().getColumn(0).setPreferredWidth(140);
            tblEntradas2.getColumnModel().getColumn(0).setMaxWidth(140);
            tblEntradas2.getColumnModel().getColumn(1).setMinWidth(40);
            tblEntradas2.getColumnModel().getColumn(1).setPreferredWidth(40);
            tblEntradas2.getColumnModel().getColumn(1).setMaxWidth(100);
            tblEntradas2.getColumnModel().getColumn(2).setMinWidth(100);
            tblEntradas2.getColumnModel().getColumn(2).setPreferredWidth(100);
            tblEntradas2.getColumnModel().getColumn(2).setMaxWidth(100);
        }

        lbNit23.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbNit23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit23.setText("SALIDAS");

        tblSalidas2.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        tblSalidas2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Ventas", null, " VER DETALLES"},
                {"Ajustes. S", null, " VER DETALLES"},
                {"Costeo", null, " VER DETALLES"},
                {"Armado", null, " VER DETALLES"}
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
        tblSalidas2.setRowHeight(20);
        tblSalidas2.getTableHeader().setReorderingAllowed(false);
        tblSalidas2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSalidas2MouseClicked(evt);
            }
        });
        jScrollPane13.setViewportView(tblSalidas2);
        if (tblSalidas2.getColumnModel().getColumnCount() > 0) {
            tblSalidas2.getColumnModel().getColumn(0).setMinWidth(40);
            tblSalidas2.getColumnModel().getColumn(0).setPreferredWidth(140);
            tblSalidas2.getColumnModel().getColumn(0).setMaxWidth(140);
            tblSalidas2.getColumnModel().getColumn(1).setMinWidth(40);
            tblSalidas2.getColumnModel().getColumn(1).setPreferredWidth(40);
            tblSalidas2.getColumnModel().getColumn(1).setMaxWidth(100);
            tblSalidas2.getColumnModel().getColumn(2).setMinWidth(100);
            tblSalidas2.getColumnModel().getColumn(2).setPreferredWidth(100);
            tblSalidas2.getColumnModel().getColumn(2).setMaxWidth(100);
        }

        lbNit24.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbNit24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit24.setText("SEPARES");

        tblSepares2.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        tblSepares2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"P. Separe", null, " VER DETALLES"},
                {"O. Servicio", null, " VER DETALLES"},
                {"Pedidos", null, " VER DETALLES"},
                {"Congeladas", null, " VER DETALLES"},
                {"Traslados Bod", null, " VER DETALLES"}
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
        tblSepares2.setRowHeight(20);
        tblSepares2.getTableHeader().setReorderingAllowed(false);
        tblSepares2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSepares2MouseClicked(evt);
            }
        });
        jScrollPane14.setViewportView(tblSepares2);
        if (tblSepares2.getColumnModel().getColumnCount() > 0) {
            tblSepares2.getColumnModel().getColumn(0).setMinWidth(40);
            tblSepares2.getColumnModel().getColumn(0).setPreferredWidth(140);
            tblSepares2.getColumnModel().getColumn(0).setMaxWidth(140);
            tblSepares2.getColumnModel().getColumn(1).setMinWidth(40);
            tblSepares2.getColumnModel().getColumn(1).setPreferredWidth(40);
            tblSepares2.getColumnModel().getColumn(1).setMaxWidth(100);
            tblSepares2.getColumnModel().getColumn(2).setMinWidth(100);
            tblSepares2.getColumnModel().getColumn(2).setPreferredWidth(100);
            tblSepares2.getColumnModel().getColumn(2).setMaxWidth(100);
        }

        lbNit25.setFont(new java.awt.Font("Century Gothic", 2, 20)); // NOI18N
        lbNit25.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNit25.setText("INVENTARIO:");

        txtInventario2.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        txtInventario2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lbNit26.setFont(new java.awt.Font("Century Gothic", 2, 20)); // NOI18N
        lbNit26.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNit26.setText("FISICO INVENTARIO:");

        txtFisicoInventario2.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        txtFisicoInventario2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 711, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(lbNit14, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbNit23, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
                            .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(lbNit24, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(lbNit25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtInventario2, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
                        .addComponent(lbNit26, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFisicoInventario2, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbNit25, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtInventario2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbNit26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtFisicoInventario2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(2, 2, 2)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(lbNit24)
                        .addGap(3, 3, 3)
                        .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(lbNit23)
                        .addGap(3, 3, 3)
                        .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(lbNit14)
                        .addGap(3, 3, 3)
                        .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15))
        );

        tabPrincipal.addTab("Bodega 2", jPanel7);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        lbNit27.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbNit27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit27.setText("ENTRADAS");

        tblEntradas3.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        tblEntradas3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Inv. Inicial", null, " VER DETALLES"},
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
        tblEntradas3.setRowHeight(20);
        tblEntradas3.getTableHeader().setReorderingAllowed(false);
        tblEntradas3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEntradas3MouseClicked(evt);
            }
        });
        jScrollPane15.setViewportView(tblEntradas3);
        if (tblEntradas3.getColumnModel().getColumnCount() > 0) {
            tblEntradas3.getColumnModel().getColumn(0).setMinWidth(40);
            tblEntradas3.getColumnModel().getColumn(0).setPreferredWidth(140);
            tblEntradas3.getColumnModel().getColumn(0).setMaxWidth(140);
            tblEntradas3.getColumnModel().getColumn(1).setMinWidth(40);
            tblEntradas3.getColumnModel().getColumn(1).setPreferredWidth(40);
            tblEntradas3.getColumnModel().getColumn(1).setMaxWidth(100);
            tblEntradas3.getColumnModel().getColumn(2).setMinWidth(100);
            tblEntradas3.getColumnModel().getColumn(2).setPreferredWidth(100);
            tblEntradas3.getColumnModel().getColumn(2).setMaxWidth(100);
        }

        lbNit48.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbNit48.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit48.setText("SALIDAS");

        tblSalidas3.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        tblSalidas3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Ventas", null, " VER DETALLES"},
                {"Ajustes. S", null, " VER DETALLES"},
                {"Costeo", null, " VER DETALLES"},
                {"Armado", null, " VER DETALLES"}
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
        tblSalidas3.setRowHeight(20);
        tblSalidas3.getTableHeader().setReorderingAllowed(false);
        tblSalidas3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSalidas3MouseClicked(evt);
            }
        });
        jScrollPane16.setViewportView(tblSalidas3);
        if (tblSalidas3.getColumnModel().getColumnCount() > 0) {
            tblSalidas3.getColumnModel().getColumn(0).setMinWidth(40);
            tblSalidas3.getColumnModel().getColumn(0).setPreferredWidth(140);
            tblSalidas3.getColumnModel().getColumn(0).setMaxWidth(140);
            tblSalidas3.getColumnModel().getColumn(1).setMinWidth(40);
            tblSalidas3.getColumnModel().getColumn(1).setPreferredWidth(40);
            tblSalidas3.getColumnModel().getColumn(1).setMaxWidth(100);
            tblSalidas3.getColumnModel().getColumn(2).setMinWidth(100);
            tblSalidas3.getColumnModel().getColumn(2).setPreferredWidth(100);
            tblSalidas3.getColumnModel().getColumn(2).setMaxWidth(100);
        }

        lbNit49.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbNit49.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit49.setText("SEPARES");

        tblSepares3.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        tblSepares3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"P. Separe", null, " VER DETALLES"},
                {"O. Servicio", null, " VER DETALLES"},
                {"Pedidos", null, " VER DETALLES"},
                {"Congeladas", null, " VER DETALLES"},
                {"Traslados Bod", null, " VER DETALLES"}
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
        tblSepares3.setRowHeight(20);
        tblSepares3.getTableHeader().setReorderingAllowed(false);
        tblSepares3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSepares3MouseClicked(evt);
            }
        });
        jScrollPane17.setViewportView(tblSepares3);
        if (tblSepares3.getColumnModel().getColumnCount() > 0) {
            tblSepares3.getColumnModel().getColumn(0).setMinWidth(40);
            tblSepares3.getColumnModel().getColumn(0).setPreferredWidth(140);
            tblSepares3.getColumnModel().getColumn(0).setMaxWidth(140);
            tblSepares3.getColumnModel().getColumn(1).setMinWidth(40);
            tblSepares3.getColumnModel().getColumn(1).setPreferredWidth(40);
            tblSepares3.getColumnModel().getColumn(1).setMaxWidth(100);
            tblSepares3.getColumnModel().getColumn(2).setMinWidth(100);
            tblSepares3.getColumnModel().getColumn(2).setPreferredWidth(100);
            tblSepares3.getColumnModel().getColumn(2).setMaxWidth(100);
        }

        lbNit50.setFont(new java.awt.Font("Century Gothic", 2, 20)); // NOI18N
        lbNit50.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNit50.setText("INVENTARIO:");

        txtInventario3.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        txtInventario3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lbNit51.setFont(new java.awt.Font("Century Gothic", 2, 20)); // NOI18N
        lbNit51.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNit51.setText("FISICO INVENTARIO:");

        txtFisicoInventario3.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        txtFisicoInventario3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 711, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(lbNit27, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbNit48, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
                            .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(lbNit49, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(lbNit50)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtInventario3, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
                        .addComponent(lbNit51, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFisicoInventario3, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbNit50, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtInventario3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbNit51, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtFisicoInventario3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(2, 2, 2)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(lbNit49)
                        .addGap(3, 3, 3)
                        .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(lbNit48)
                        .addGap(3, 3, 3)
                        .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(lbNit27)
                        .addGap(3, 3, 3)
                        .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15))
        );

        tabPrincipal.addTab("Bodega 3", jPanel8);

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        lbNit52.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbNit52.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit52.setText("ENTRADAS");

        tblEntradas4.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        tblEntradas4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Inv. Inicial", null, " VER DETALLES"},
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
        tblEntradas4.setRowHeight(20);
        tblEntradas4.getTableHeader().setReorderingAllowed(false);
        tblEntradas4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEntradas4MouseClicked(evt);
            }
        });
        jScrollPane30.setViewportView(tblEntradas4);
        if (tblEntradas4.getColumnModel().getColumnCount() > 0) {
            tblEntradas4.getColumnModel().getColumn(0).setMinWidth(40);
            tblEntradas4.getColumnModel().getColumn(0).setPreferredWidth(140);
            tblEntradas4.getColumnModel().getColumn(0).setMaxWidth(140);
            tblEntradas4.getColumnModel().getColumn(1).setMinWidth(40);
            tblEntradas4.getColumnModel().getColumn(1).setPreferredWidth(40);
            tblEntradas4.getColumnModel().getColumn(1).setMaxWidth(100);
            tblEntradas4.getColumnModel().getColumn(2).setMinWidth(100);
            tblEntradas4.getColumnModel().getColumn(2).setPreferredWidth(100);
            tblEntradas4.getColumnModel().getColumn(2).setMaxWidth(100);
        }

        lbNit53.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbNit53.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit53.setText("SALIDAS");

        tblSalidas4.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        tblSalidas4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Ventas", null, " VER DETALLES"},
                {"Ajustes. S", null, " VER DETALLES"},
                {"Costeo", null, " VER DETALLES"},
                {"Armado", null, " VER DETALLES"}
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
        tblSalidas4.setRowHeight(20);
        tblSalidas4.getTableHeader().setReorderingAllowed(false);
        tblSalidas4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSalidas4MouseClicked(evt);
            }
        });
        jScrollPane31.setViewportView(tblSalidas4);
        if (tblSalidas4.getColumnModel().getColumnCount() > 0) {
            tblSalidas4.getColumnModel().getColumn(0).setMinWidth(40);
            tblSalidas4.getColumnModel().getColumn(0).setPreferredWidth(140);
            tblSalidas4.getColumnModel().getColumn(0).setMaxWidth(140);
            tblSalidas4.getColumnModel().getColumn(1).setMinWidth(40);
            tblSalidas4.getColumnModel().getColumn(1).setPreferredWidth(40);
            tblSalidas4.getColumnModel().getColumn(1).setMaxWidth(100);
            tblSalidas4.getColumnModel().getColumn(2).setMinWidth(100);
            tblSalidas4.getColumnModel().getColumn(2).setPreferredWidth(100);
            tblSalidas4.getColumnModel().getColumn(2).setMaxWidth(100);
        }

        lbNit54.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbNit54.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit54.setText("SEPARES");

        tblSepares4.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        tblSepares4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"P. Separe", null, " VER DETALLES"},
                {"O. Servicio", null, " VER DETALLES"},
                {"Pedidos", null, " VER DETALLES"},
                {"Congeladas", null, " VER DETALLES"},
                {"Traslados Bod", null, " VER DETALLES"}
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
        tblSepares4.setRowHeight(20);
        tblSepares4.getTableHeader().setReorderingAllowed(false);
        tblSepares4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSepares4MouseClicked(evt);
            }
        });
        jScrollPane32.setViewportView(tblSepares4);
        if (tblSepares4.getColumnModel().getColumnCount() > 0) {
            tblSepares4.getColumnModel().getColumn(0).setMinWidth(40);
            tblSepares4.getColumnModel().getColumn(0).setPreferredWidth(140);
            tblSepares4.getColumnModel().getColumn(0).setMaxWidth(140);
            tblSepares4.getColumnModel().getColumn(1).setMinWidth(40);
            tblSepares4.getColumnModel().getColumn(1).setPreferredWidth(40);
            tblSepares4.getColumnModel().getColumn(1).setMaxWidth(100);
            tblSepares4.getColumnModel().getColumn(2).setMinWidth(100);
            tblSepares4.getColumnModel().getColumn(2).setPreferredWidth(100);
            tblSepares4.getColumnModel().getColumn(2).setMaxWidth(100);
        }

        lbNit55.setFont(new java.awt.Font("Century Gothic", 2, 20)); // NOI18N
        lbNit55.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNit55.setText("INVENTARIO:");

        txtInventario4.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        txtInventario4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lbNit56.setFont(new java.awt.Font("Century Gothic", 2, 20)); // NOI18N
        lbNit56.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNit56.setText("FISICO INVENTARIO:");

        txtFisicoInventario4.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        txtFisicoInventario4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 711, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane30, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(lbNit52, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbNit53, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
                            .addComponent(jScrollPane31, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane32, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(lbNit54, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(lbNit55)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtInventario4, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
                        .addComponent(lbNit56, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFisicoInventario4, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbNit55, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtInventario4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbNit56, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtFisicoInventario4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(2, 2, 2)
                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(lbNit54)
                        .addGap(3, 3, 3)
                        .addComponent(jScrollPane32, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(lbNit53)
                        .addGap(3, 3, 3)
                        .addComponent(jScrollPane31, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(lbNit52)
                        .addGap(3, 3, 3)
                        .addComponent(jScrollPane30, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15))
        );

        tabPrincipal.addTab("Bodega 4", jPanel10);

        btnHistorial.setBackground(new java.awt.Color(204, 204, 204));
        btnHistorial.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnHistorial.setForeground(new java.awt.Color(255, 255, 255));
        btnHistorial.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/boton-de-suma.png"))); // NOI18N
        btnHistorial.setText("Ver historial de costos");
        btnHistorial.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnHistorial.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHistorial.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnHistorial.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnHistorial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHistorialActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap(65, Short.MAX_VALUE)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnHistorial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(tabPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 753, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(82, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel16Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbFoto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(tabPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addComponent(btnHistorial, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        tblResultados.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        tblResultados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "# Documento", "ID", "Descripcion ID", "F. Movimiento", "Valor", "Cant", "Subtotal", "Descuento", "Iva", "% Iva", "Total", "Usuario"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblResultados.setRowHeight(24);
        tblResultados.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(tblResultados);
        if (tblResultados.getColumnModel().getColumnCount() > 0) {
            tblResultados.getColumnModel().getColumn(0).setMinWidth(50);
            tblResultados.getColumnModel().getColumn(0).setPreferredWidth(120);
            tblResultados.getColumnModel().getColumn(0).setMaxWidth(150);
            tblResultados.getColumnModel().getColumn(1).setMinWidth(50);
            tblResultados.getColumnModel().getColumn(1).setPreferredWidth(80);
            tblResultados.getColumnModel().getColumn(1).setMaxWidth(140);
            tblResultados.getColumnModel().getColumn(3).setMinWidth(80);
            tblResultados.getColumnModel().getColumn(3).setPreferredWidth(80);
            tblResultados.getColumnModel().getColumn(3).setMaxWidth(80);
            tblResultados.getColumnModel().getColumn(4).setMinWidth(60);
            tblResultados.getColumnModel().getColumn(4).setPreferredWidth(80);
            tblResultados.getColumnModel().getColumn(4).setMaxWidth(150);
            tblResultados.getColumnModel().getColumn(5).setMinWidth(30);
            tblResultados.getColumnModel().getColumn(5).setPreferredWidth(50);
            tblResultados.getColumnModel().getColumn(5).setMaxWidth(100);
            tblResultados.getColumnModel().getColumn(6).setMinWidth(60);
            tblResultados.getColumnModel().getColumn(6).setPreferredWidth(80);
            tblResultados.getColumnModel().getColumn(6).setMaxWidth(150);
            tblResultados.getColumnModel().getColumn(7).setMinWidth(60);
            tblResultados.getColumnModel().getColumn(7).setPreferredWidth(80);
            tblResultados.getColumnModel().getColumn(7).setMaxWidth(150);
            tblResultados.getColumnModel().getColumn(8).setMinWidth(60);
            tblResultados.getColumnModel().getColumn(8).setPreferredWidth(80);
            tblResultados.getColumnModel().getColumn(8).setMaxWidth(150);
            tblResultados.getColumnModel().getColumn(9).setMinWidth(30);
            tblResultados.getColumnModel().getColumn(9).setPreferredWidth(50);
            tblResultados.getColumnModel().getColumn(9).setMaxWidth(100);
            tblResultados.getColumnModel().getColumn(10).setMinWidth(60);
            tblResultados.getColumnModel().getColumn(10).setPreferredWidth(80);
            tblResultados.getColumnModel().getColumn(10).setMaxWidth(150);
            tblResultados.getColumnModel().getColumn(11).setMinWidth(50);
            tblResultados.getColumnModel().getColumn(11).setPreferredWidth(70);
            tblResultados.getColumnModel().getColumn(11).setMaxWidth(150);
        }

        lbTitulo.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        lbTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitulo.setText(" ");

        jLabel4.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel4.setText("Fecha:");

        txtNit.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtNit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNitKeyReleased(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel2.setText("Nit:");

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel1.setText("# Documento:");

        txtDocumento.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtDocumento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDocumentoKeyReleased(evt);
            }
        });

        txtFecha.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtFecha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFechaKeyReleased(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel3.setText("Nombre:");

        txtNombre.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addComponent(lbTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
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
                        .addComponent(txtNombre)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(lbTitulo)
                .addGap(5, 5, 5)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNit, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );

        txtIdSistema.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtIdSistema.setName("Codigo"); // NOI18N
        txtIdSistema.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIdSistemaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIdSistemaKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout pnlInvisibleLayout = new javax.swing.GroupLayout(pnlInvisible);
        pnlInvisible.setLayout(pnlInvisibleLayout);
        pnlInvisibleLayout.setHorizontalGroup(
            pnlInvisibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInvisibleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtIdSistema, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlInvisibleLayout.setVerticalGroup(
            pnlInvisibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInvisibleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtIdSistema, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );

        pnlHistorialCostos.setBackground(new java.awt.Color(255, 255, 255));
        pnlHistorialCostos.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        tblPonderado.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        tblPonderado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fecha", "Pond. Antiguo", "Cant. Antigua", "Pond. Nuevo", "Cant. Nueva", "Ultimo C.", "Usuario"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPonderado.setRowHeight(20);
        tblPonderado.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tblPonderado);
        if (tblPonderado.getColumnModel().getColumnCount() > 0) {
            tblPonderado.getColumnModel().getColumn(0).setMinWidth(80);
            tblPonderado.getColumnModel().getColumn(0).setPreferredWidth(110);
            tblPonderado.getColumnModel().getColumn(0).setMaxWidth(130);
            tblPonderado.getColumnModel().getColumn(1).setMinWidth(60);
            tblPonderado.getColumnModel().getColumn(1).setPreferredWidth(130);
            tblPonderado.getColumnModel().getColumn(1).setMaxWidth(180);
            tblPonderado.getColumnModel().getColumn(2).setMinWidth(50);
            tblPonderado.getColumnModel().getColumn(2).setPreferredWidth(110);
            tblPonderado.getColumnModel().getColumn(2).setMaxWidth(140);
            tblPonderado.getColumnModel().getColumn(3).setMinWidth(60);
            tblPonderado.getColumnModel().getColumn(3).setPreferredWidth(130);
            tblPonderado.getColumnModel().getColumn(3).setMaxWidth(180);
            tblPonderado.getColumnModel().getColumn(4).setMinWidth(30);
            tblPonderado.getColumnModel().getColumn(4).setPreferredWidth(110);
            tblPonderado.getColumnModel().getColumn(4).setMaxWidth(140);
            tblPonderado.getColumnModel().getColumn(5).setMinWidth(60);
            tblPonderado.getColumnModel().getColumn(5).setPreferredWidth(130);
            tblPonderado.getColumnModel().getColumn(5).setMaxWidth(180);
        }

        lbNit13.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lbNit13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit13.setText("HISTORIAL COSTO PONDERADO");

        javax.swing.GroupLayout pnlHistorialCostosLayout = new javax.swing.GroupLayout(pnlHistorialCostos);
        pnlHistorialCostos.setLayout(pnlHistorialCostosLayout);
        pnlHistorialCostosLayout.setHorizontalGroup(
            pnlHistorialCostosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHistorialCostosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlHistorialCostosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbNit13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        pnlHistorialCostosLayout.setVerticalGroup(
            pnlHistorialCostosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHistorialCostosLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(lbNit13, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlInvisible, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlHistorialCostos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlHistorialCostos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5)
                .addComponent(pnlInvisible, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout pnlFormularioLayout = new javax.swing.GroupLayout(pnlFormulario);
        pnlFormulario.setLayout(pnlFormularioLayout);
        pnlFormularioLayout.setHorizontalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlFormularioLayout.setVerticalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        scrFormulario.setViewportView(pnlFormulario);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrFormulario, javax.swing.GroupLayout.DEFAULT_SIZE, 1128, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrFormulario, javax.swing.GroupLayout.DEFAULT_SIZE, 783, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            ndProducto nodo = instancias.getSql().getDatosProducto(txtCodigo.getText().replace("'", "//"), "bdProductos");

            if (nodo.getCodigo() == null) {
                ventanaProductos(txtCodigo.getText());
            } else {
                cargarProducto(txtCodigo.getText());
            }
        } else {
            txtIdSistema.setText("");

            tblEntradas.setValueAt("", 0, 1);
            tblEntradas.setValueAt("", 1, 1);
            tblEntradas.setValueAt("", 2, 1);
            tblEntradas.setValueAt("", 3, 1);
            tblEntradas.setValueAt("", 4, 1);
            tblEntradas1.setValueAt("", 0, 1);
            tblEntradas1.setValueAt("", 1, 1);
            tblEntradas1.setValueAt("", 2, 1);
            tblEntradas1.setValueAt("", 3, 1);
            tblEntradas1.setValueAt("", 4, 1);
            tblEntradas2.setValueAt("", 0, 1);
            tblEntradas2.setValueAt("", 1, 1);
            tblEntradas2.setValueAt("", 2, 1);
            tblEntradas2.setValueAt("", 3, 1);
            tblEntradas2.setValueAt("", 4, 1);
            tblEntradas3.setValueAt("", 0, 1);
            tblEntradas3.setValueAt("", 1, 1);
            tblEntradas3.setValueAt("", 2, 1);
            tblEntradas3.setValueAt("", 3, 1);
            tblEntradas3.setValueAt("", 4, 1);
            tblEntradas4.setValueAt("", 0, 1);
            tblEntradas4.setValueAt("", 1, 1);
            tblEntradas4.setValueAt("", 2, 1);
            tblEntradas4.setValueAt("", 3, 1);
            tblEntradas4.setValueAt("", 4, 1);

            tblSalidas.setValueAt("", 0, 1);
            tblSalidas.setValueAt("", 1, 1);
            tblSalidas.setValueAt("", 2, 1);
            tblSalidas.setValueAt("", 3, 1);
            tblSalidas1.setValueAt("", 0, 1);
            tblSalidas1.setValueAt("", 1, 1);
            tblSalidas1.setValueAt("", 2, 1);
            tblSalidas1.setValueAt("", 3, 1);
            tblSalidas2.setValueAt("", 0, 1);
            tblSalidas2.setValueAt("", 1, 1);
            tblSalidas2.setValueAt("", 2, 1);
            tblSalidas2.setValueAt("", 3, 1);
            tblSalidas3.setValueAt("", 0, 1);
            tblSalidas3.setValueAt("", 1, 1);
            tblSalidas3.setValueAt("", 2, 1);
            tblSalidas3.setValueAt("", 3, 1);
            tblSalidas4.setValueAt("", 0, 1);
            tblSalidas4.setValueAt("", 1, 1);
            tblSalidas4.setValueAt("", 2, 1);
            tblSalidas4.setValueAt("", 3, 1);

            tblSepares.setValueAt("", 0, 1);
            tblSepares.setValueAt("", 1, 1);
            tblSepares.setValueAt("", 2, 1);
            tblSepares.setValueAt("", 3, 1);
            tblSepares.setValueAt("", 4, 1);
            tblSepares1.setValueAt("", 0, 1);
            tblSepares1.setValueAt("", 1, 1);
            tblSepares1.setValueAt("", 2, 1);
            tblSepares1.setValueAt("", 3, 1);
            tblSepares1.setValueAt("", 4, 1);
            tblSepares2.setValueAt("", 0, 1);
            tblSepares2.setValueAt("", 1, 1);
            tblSepares2.setValueAt("", 2, 1);
            tblSepares2.setValueAt("", 3, 1);
            tblSepares2.setValueAt("", 4, 1);
            tblSepares3.setValueAt("", 0, 1);
            tblSepares3.setValueAt("", 1, 1);
            tblSepares3.setValueAt("", 2, 1);
            tblSepares3.setValueAt("", 3, 1);
            tblSepares3.setValueAt("", 4, 1);
            tblSepares4.setValueAt("", 0, 1);
            tblSepares4.setValueAt("", 1, 1);
            tblSepares4.setValueAt("", 2, 1);
            tblSepares4.setValueAt("", 3, 1);
            tblSepares4.setValueAt("", 4, 1);

            txtDescripcion.setText("");
            txtFisicoInventario.setText("");
            txtInventario.setText("");
            txtFisicoInventario1.setText("");
            txtInventario1.setText("");
            txtFisicoInventario2.setText("");
            txtInventario2.setText("");
            txtFisicoInventario3.setText("");
            txtInventario3.setText("");
            txtFisicoInventario4.setText("");
            txtInventario4.setText("");

            DefaultTableModel modelo = (DefaultTableModel) tblPonderado.getModel();
            DefaultTableModel modelo1 = (DefaultTableModel) tblResultados.getModel();

            while (tblResultados.getRowCount() > 0) {
                modelo1.removeRow(0);
            }
            while (tblPonderado.getRowCount() > 0) {
                modelo.removeRow(0);
            }

            lbFoto.setIcon(null);
            lbFoto.repaint();
            lbTitulo.setText("");
        }
    }//GEN-LAST:event_txtCodigoKeyReleased

    private void txtCodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyTyped

    }//GEN-LAST:event_txtCodigoKeyTyped

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

    private void txtFechaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFechaKeyReleased
        modeloOrdenado.setRowFilter(RowFilter.regexFilter("(?i)" + txtFecha.getText(), 3));
        txtNombre.setText("");
        txtDocumento.setText("");
        txtNit.setText("");
    }//GEN-LAST:event_txtFechaKeyReleased

    private void txtNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyReleased
        modeloOrdenado.setRowFilter(RowFilter.regexFilter("(?i)" + txtNombre.getText(), 2));
        txtFecha.setText("");
        txtDocumento.setText("");
        txtNit.setText("");
    }//GEN-LAST:event_txtNombreKeyReleased

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        cargarProducto(txtCodigo.getText());
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void txtIdSistemaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdSistemaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdSistemaKeyReleased

    private void txtIdSistemaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdSistemaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdSistemaKeyTyped

    private void txtDescripcionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescripcionKeyReleased

    private void txtDescripcionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescripcionKeyTyped

    private void btnBuscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscar1ActionPerformed
        txtIdSistema.setText("");
        txtCodigo.setText("");

        tblEntradas.setValueAt("", 0, 1);
        tblEntradas.setValueAt("", 1, 1);
        tblEntradas.setValueAt("", 2, 1);
        tblEntradas.setValueAt("", 3, 1);
        tblEntradas.setValueAt("", 4, 1);

        tblSalidas.setValueAt("", 0, 1);
        tblSalidas.setValueAt("", 1, 1);
        tblSalidas.setValueAt("", 2, 1);
        tblSalidas.setValueAt("", 3, 1);

        tblSepares.setValueAt("", 0, 1);
        tblSepares.setValueAt("", 1, 1);
        tblSepares.setValueAt("", 2, 1);
        tblSepares.setValueAt("", 3, 1);
        tblSepares.setValueAt("", 4, 1);

        txtDescripcion.setText("");
        txtFisicoInventario.setText("");
        txtInventario.setText("");

        DefaultTableModel modelo = (DefaultTableModel) tblPonderado.getModel();
        DefaultTableModel modelo1 = (DefaultTableModel) tblResultados.getModel();

        while (tblResultados.getRowCount() > 0) {
            modelo1.removeRow(0);
        }
        while (tblPonderado.getRowCount() > 0) {
            modelo.removeRow(0);
        }

        lbFoto.setIcon(null);
        lbFoto.repaint();

        lbTitulo.setText("");
    }//GEN-LAST:event_btnBuscar1ActionPerformed

    private void tblEntradasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEntradasMouseClicked
        if (!txtCodigo.getText().equals("")) {

            DefaultTableModel modelo = (DefaultTableModel) tblResultados.getModel();

            Object[][] resultados;

            if (tblEntradas.getSelectedColumn() == 2 && tblEntradas.getSelectedRow() == 0) {
                if (!lbTitulo.getText().equals("INVENTARIO INICIAL")) {
                    lbTitulo.setText("INVENTARIO INICIAL");

                    while (tblResultados.getRowCount() > 0) {
                        modelo.removeRow(0);
                    }

                    resultados = instancias.getSql().getProductosInvInicialKardex(txtIdSistema.getText());
                    for (int i = 0; i < resultados.length; i++) {
                        Object[][] encabezados = instancias.getSql().getProveedorIngreso(resultados[i][0].toString(), "123-22");
                        if (encabezados.length > 0) {
                            modelo.addRow(new Object[]{"INV-INICIAL", encabezados[0][0], encabezados[0][1], metodos.fecha(encabezados[0][2].toString()), big.setMoneda(big.getBigDecimal(resultados[i][2].toString())), resultados[i][1],
                                big.setMoneda(big.getBigDecimal(resultados[i][4])), big.setMoneda(big.getBigDecimal(resultados[i][3])), big.setMoneda(big.getBigDecimal(resultados[i][5])),
                                big.setNumero(big.getBigDecimal(resultados[i][6])), big.setMoneda(big.getBigDecimal(resultados[i][7])), encabezados[0][3]});
                        }
                    }
                }
            } else if (tblEntradas.getSelectedColumn() == 2 && tblEntradas.getSelectedRow() == 1) {
                if (!lbTitulo.getText().equals("COMPRAS")) {
                    lbTitulo.setText("COMPRAS");
                    while (tblResultados.getRowCount() > 0) {
                        modelo.removeRow(0);
                    }

                    resultados = instancias.getSql().getProductosComprasKardex(txtIdSistema.getText());
                    for (int i = 0; i < resultados.length; i++) {
                        Object[][] encabezados = instancias.getSql().getProveedorIngreso(resultados[i][0].toString(), "123-22");
                        if (encabezados.length > 0) {
                            modelo.addRow(new Object[]{resultados[i][0], encabezados[0][0], encabezados[0][1], metodos.fecha(encabezados[0][2].toString()), big.setMoneda(big.getBigDecimal(resultados[i][2].toString())), resultados[i][1],
                                big.setMoneda(big.getBigDecimal(resultados[i][4])), big.setMoneda(big.getBigDecimal(resultados[i][3])), big.setMoneda(big.getBigDecimal(resultados[i][5])),
                                big.setNumero(big.getBigDecimal(resultados[i][6])), big.setMoneda(big.getBigDecimal(resultados[i][7])), encabezados[0][3]});
                        }
                    }
                }
            } else if (tblEntradas.getSelectedColumn() == 2 && tblEntradas.getSelectedRow() == 2) {
                if (!lbTitulo.getText().equals("AJUSTES DE ENTRADA")) {
                    lbTitulo.setText("AJUSTES DE ENTRADA");

                    while (tblResultados.getRowCount() > 0) {
                        modelo.removeRow(0);
                    }

                    resultados = instancias.getSql().getProductosAjustesKardex(txtIdSistema.getText(), "ajustesEntrada", "123-22");
                    for (int i = 0; i < resultados.length; i++) {
                        modelo.addRow(new Object[]{resultados[i][0], "", "", metodos.fecha(resultados[i][1].toString()), big.setMoneda(big.getBigDecimal(resultados[i][3].toString())),
                            resultados[i][2], big.setMoneda(big.getBigDecimal(resultados[i][4])), this.simbolo + " 0", this.simbolo + " 0", "0", big.setMoneda(big.getBigDecimal(resultados[i][4])), resultados[i][5]});
                    }
                }
            } else if (tblEntradas.getSelectedColumn() == 2 && tblEntradas.getSelectedRow() == 3) {
                if (!lbTitulo.getText().equals("ANULADAS")) {
                    lbTitulo.setText("ANULADAS");

                    while (tblResultados.getRowCount() > 0) {
                        modelo.removeRow(0);
                    }

                    resultados = instancias.getSql().getProductosAnuladaKardex(txtIdSistema.getText(), "anulacion", "IdCliente", "123-22");
                    for (int i = 0; i < resultados.length; i++) {
                        modelo.addRow(new Object[]{resultados[i][0], resultados[i][1], resultados[i][2], metodos.fecha(resultados[i][3].toString()),
                            big.setMoneda(big.getBigDecimal(resultados[i][4].toString())), resultados[i][5], big.setMoneda(big.getBigDecimal(resultados[i][6])),
                            big.setMoneda(big.getBigDecimal(resultados[i][7])), big.setMoneda(big.getBigDecimal(resultados[i][8])), resultados[i][9],
                            big.setMoneda(big.getBigDecimal(resultados[i][10])), resultados[i][11]});
                    }
                }
            } else if (tblEntradas.getSelectedColumn() == 2 && tblEntradas.getSelectedRow() == 4) {
                if (!lbTitulo.getText().equals("NOTAS CREDITO")) {
                    lbTitulo.setText("NOTAS CREDITO");
                    while (tblResultados.getRowCount() > 0) {
                        modelo.removeRow(0);
                    }

                    resultados = instancias.getSql().getProductosNotaCreditoKardex(txtIdSistema.getText(), "123-22");
                    for (int i = 0; i < resultados.length; i++) {
                        modelo.addRow(new Object[]{resultados[i][0], resultados[i][1], resultados[i][2], metodos.fecha(resultados[i][3].toString()),
                            big.setMoneda(big.getBigDecimal(resultados[i][4].toString())), resultados[i][5], big.setMoneda(big.getBigDecimal(resultados[i][6])),
                            big.setMoneda(big.getBigDecimal(resultados[i][7])), big.setMoneda(big.getBigDecimal(resultados[i][8])), resultados[i][9],
                            big.setMoneda(big.getBigDecimal(resultados[i][10])), resultados[i][11]});
                    }
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

                    resultados = instancias.getSql().getProductosTipoFacturacionKardex(txtIdSistema.getText(), "factura", "123-22");

                    for (int i = 0; i < resultados.length; i++) {
                        modelo.addRow(new Object[]{resultados[i][0], resultados[i][1], resultados[i][2], metodos.fecha(resultados[i][3].toString()),
                            big.setMoneda(big.getBigDecimal(resultados[i][4].toString())), resultados[i][5], big.setMoneda(big.getBigDecimal(resultados[i][6])),
                            big.setMoneda(big.getBigDecimal(resultados[i][7])), big.setMoneda(big.getBigDecimal(resultados[i][8])), resultados[i][9],
                            big.setMoneda(big.getBigDecimal(resultados[i][10])), resultados[i][11]});
                    }
                }
            } else if (tblSalidas.getSelectedColumn() == 2 && tblSalidas.getSelectedRow() == 1) {
                if (!lbTitulo.getText().equals("AJUSTES DE SALIDA")) {
                    lbTitulo.setText("AJUSTES DE SALIDA");
                    while (tblResultados.getRowCount() > 0) {
                        modelo.removeRow(0);
                    }

                    resultados = instancias.getSql().getProductosAjustesKardex(txtIdSistema.getText(), "ajustesSalida", "123-22");
                    for (int i = 0; i < resultados.length; i++) {
                        modelo.addRow(new Object[]{resultados[i][0], "", "", metodos.fecha(resultados[i][1].toString()), big.setMoneda(big.getBigDecimal(resultados[i][3].toString())),
                            resultados[i][2], big.setMoneda(big.getBigDecimal(resultados[i][4])), this.simbolo + " 0", this.simbolo + " 0", "0", big.setMoneda(big.getBigDecimal(resultados[i][4])), resultados[i][5]});
                    }
                }
            } else if (tblSalidas.getSelectedColumn() == 2 && tblSalidas.getSelectedRow() == 2) {
                if (!lbTitulo.getText().equals("COSTEOS")) {
                    lbTitulo.setText("COSTEOS");

                    while (tblResultados.getRowCount() > 0) {
                        modelo.removeRow(0);
                    }

                    resultados = instancias.getSql().getProductosTipoFacturacionKardex(txtIdSistema.getText(), "anulacion", "123-22");

                    for (int i = 0; i < resultados.length; i++) {
                        modelo.addRow(new Object[]{resultados[i][0], resultados[i][1], resultados[i][2], metodos.fecha(resultados[i][3].toString()),
                            big.setMoneda(big.getBigDecimal(resultados[i][4].toString())), resultados[i][5], big.setMoneda(big.getBigDecimal(resultados[i][6])),
                            big.setMoneda(big.getBigDecimal(resultados[i][7])), big.setMoneda(big.getBigDecimal(resultados[i][8])), resultados[i][9],
                            big.setMoneda(big.getBigDecimal(resultados[i][10])), resultados[i][11]});
                    }
                }
            } else if (tblSalidas.getSelectedColumn() == 2 && tblSalidas.getSelectedRow() == 3) {
                if (!lbTitulo.getText().equals("ARMADOS")) {
                    lbTitulo.setText("ARMADOS");

                    while (tblResultados.getRowCount() > 0) {
                        modelo.removeRow(0);
                    }

                    resultados = instancias.getSql().getProductosTipoFacturacionKardex(txtIdSistema.getText(), "anulacion", "123-22");

                    for (int i = 0; i < resultados.length; i++) {
                        modelo.addRow(new Object[]{resultados[i][0], resultados[i][1], resultados[i][2], metodos.fecha(resultados[i][3].toString()),
                            big.setMoneda(big.getBigDecimal(resultados[i][4].toString())), resultados[i][5], big.setMoneda(big.getBigDecimal(resultados[i][6])),
                            big.setMoneda(big.getBigDecimal(resultados[i][7])), big.setMoneda(big.getBigDecimal(resultados[i][8])), resultados[i][9],
                            big.setMoneda(big.getBigDecimal(resultados[i][10])), resultados[i][11]});
                    }
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

                    resultados = instancias.getSql().getProductosTipoSepareKardex(txtIdSistema.getText(), "planSepare", "IdTercero", "123-22");

                    for (int i = 0; i < resultados.length; i++) {
                        modelo.addRow(new Object[]{resultados[i][0], resultados[i][1], resultados[i][2], metodos.fecha(resultados[i][3].toString()),
                            big.setMoneda(big.getBigDecimal(resultados[i][4].toString())), resultados[i][5], big.setMoneda(big.getBigDecimal(resultados[i][6])),
                            big.setMoneda(big.getBigDecimal(resultados[i][7])), big.setMoneda(big.getBigDecimal(resultados[i][8])), resultados[i][9],
                            big.setMoneda(big.getBigDecimal(resultados[i][10])), resultados[i][11]});
                    }
                }
            } else if (tblSepares.getSelectedColumn() == 2 && tblSepares.getSelectedRow() == 1) {
                if (!lbTitulo.getText().equals("ORDENES DE SERVICIO")) {
                    lbTitulo.setText("ORDENES DE SERVICIO");

                    while (tblResultados.getRowCount() > 0) {
                        modelo.removeRow(0);
                    }

                    resultados = instancias.getSql().getProductosTipoSepareKardex(txtIdSistema.getText(), "oServicio", "IdCliente", "123-22");

                    for (int i = 0; i < resultados.length; i++) {
                        modelo.addRow(new Object[]{resultados[i][0], resultados[i][1], resultados[i][2], metodos.fecha(resultados[i][3].toString()),
                            big.setMoneda(big.getBigDecimal(resultados[i][4].toString())), resultados[i][5], big.setMoneda(big.getBigDecimal(resultados[i][6])),
                            big.setMoneda(big.getBigDecimal(resultados[i][7])), big.setMoneda(big.getBigDecimal(resultados[i][8])), resultados[i][9],
                            big.setMoneda(big.getBigDecimal(resultados[i][10])), resultados[i][11]});
                    }
                }
            } else if (tblSepares.getSelectedColumn() == 2 && tblSepares.getSelectedRow() == 2) {
                if (!lbTitulo.getText().equals("PEDIDOS")) {
                    lbTitulo.setText("PEDIDOS");

                    while (tblResultados.getRowCount() > 0) {
                        modelo.removeRow(0);
                    }

                    resultados = instancias.getSql().getProductosTipoSepareKardex(txtIdSistema.getText(), "pedidos", "IdTercero", "123-22");

                    for (int i = 0; i < resultados.length; i++) {
                        modelo.addRow(new Object[]{resultados[i][0], resultados[i][1], resultados[i][2], metodos.fecha(resultados[i][3].toString()),
                            big.setMoneda(big.getBigDecimal(resultados[i][4].toString())), resultados[i][5], big.setMoneda(big.getBigDecimal(resultados[i][6])),
                            big.setMoneda(big.getBigDecimal(resultados[i][7])), big.setMoneda(big.getBigDecimal(resultados[i][8])), resultados[i][9],
                            big.setMoneda(big.getBigDecimal(resultados[i][10])), resultados[i][11]});
                    }
                }
            } else if (tblSepares.getSelectedColumn() == 2 && tblSepares.getSelectedRow() == 3) {
                if (!lbTitulo.getText().equals("CONGELADA")) {
                    lbTitulo.setText("CONGELADA");
                    while (tblResultados.getRowCount() > 0) {
                        modelo.removeRow(0);
                    }

                    resultados = instancias.getSql().getProductosCongeladaKardex(txtIdSistema.getText(), "congeladas", "IdTercero", "123-22");
                    for (int i = 0; i < resultados.length; i++) {
                        modelo.addRow(new Object[]{resultados[i][0], resultados[i][1], resultados[i][2], metodos.fecha(resultados[i][3].toString()),
                            big.setMoneda(big.getBigDecimal(resultados[i][4].toString())), resultados[i][5], big.setMoneda(big.getBigDecimal(resultados[i][6])),
                            big.setMoneda(big.getBigDecimal(resultados[i][7])), big.setMoneda(big.getBigDecimal(resultados[i][8])), resultados[i][9],
                            big.setMoneda(big.getBigDecimal(resultados[i][10])), resultados[i][11]});
                    }
                }
            } else if (tblSepares.getSelectedColumn() == 2 && tblSepares.getSelectedRow() == 4) {
                if (!lbTitulo.getText().equals("TRASLADOS ENTRE BODEGAS")) {
                    lbTitulo.setText("TRASLADOS ENTRE BODEGAS");
                    while (tblResultados.getRowCount() > 0) {
                        modelo.removeRow(0);
                    }

                    resultados = instancias.getSql().getProductosAjustesKardex1(txtIdSistema.getText(), "trasladosInternos");
                    for (int i = 0; i < resultados.length; i++) {
                        modelo.addRow(new Object[]{resultados[i][0], "", "", metodos.fecha(resultados[i][1].toString()), big.setMoneda(big.getBigDecimal(resultados[i][3].toString())),
                            resultados[i][2], big.setMoneda(big.getBigDecimal(resultados[i][4])), this.simbolo + " 0", this.simbolo + " 0", "0", big.setMoneda(big.getBigDecimal(resultados[i][4])), resultados[i][5]});
                    }

//                    resultados = sql.getProductosCongeladaKardex(txtIdSistema.getText(), "trasladosinternos", "", "123-22");
//                    for (int i = 0; i < resultados.length; i++) {
//                        modelo.addRow(new Object[]{resultados[i][0], resultados[i][1], resultados[i][2], metodos.fecha(resultados[i][3].toString()),
//                            big.setMoneda(big.getBigDecimal(resultados[i][4].toString())), resultados[i][5], big.setMoneda(big.getBigDecimal(resultados[i][6])),
//                            big.setMoneda(big.getBigDecimal(resultados[i][7])), big.setMoneda(big.getBigDecimal(resultados[i][8])), resultados[i][9],
//                            big.setMoneda(big.getBigDecimal(resultados[i][10])), resultados[i][11]});
//                    }
                }
            }
        }
    }//GEN-LAST:event_tblSeparesMouseClicked

    private void btnHistorialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistorialActionPerformed
        if (pnlHistorialCostos.isVisible()) {
            pnlHistorialCostos.setVisible(false);
            btnHistorial.setText("Ver historial de costos");
            btnHistorial.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/boton-de-suma.png")));
        } else {
            btnHistorial.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/boton-resta.png")));
            btnHistorial.setText("Ocultar historial de costos");
            pnlHistorialCostos.setVisible(true);
        }
    }//GEN-LAST:event_btnHistorialActionPerformed

    private void tblEntradas1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEntradas1MouseClicked
        if (!txtCodigo.getText().equals("")) {

            DefaultTableModel modelo = (DefaultTableModel) tblResultados.getModel();

            Object[][] resultados;

            if (tblEntradas1.getSelectedColumn() == 2 && tblEntradas1.getSelectedRow() == 0) {
                if (!lbTitulo.getText().equals("INVENTARIO INICIAL")) {
                    lbTitulo.setText("INVENTARIO INICIAL");

                    while (tblResultados.getRowCount() > 0) {
                        modelo.removeRow(0);
                    }

                    resultados = instancias.getSql().getProductosInvInicialKardex(txtIdSistema.getText());
                    for (int i = 0; i < resultados.length; i++) {
                        Object[][] encabezados = instancias.getSql().getProveedorIngreso(resultados[i][0].toString(), "BODEGA-1");
                        if (encabezados.length > 0) {
                            modelo.addRow(new Object[]{"INV-INICIAL", encabezados[0][0], encabezados[0][1], metodos.fecha(encabezados[0][2].toString()), big.setMoneda(big.getBigDecimal(resultados[i][2].toString())), resultados[i][1],
                                big.setMoneda(big.getBigDecimal(resultados[i][4])), big.setMoneda(big.getBigDecimal(resultados[i][3])), big.setMoneda(big.getBigDecimal(resultados[i][5])),
                                big.setNumero(big.getBigDecimal(resultados[i][6])), big.setMoneda(big.getBigDecimal(resultados[i][7])), encabezados[0][3]});
                        }
                    }
                }
            } else if (tblEntradas1.getSelectedColumn() == 2 && tblEntradas1.getSelectedRow() == 1) {
                if (!lbTitulo.getText().equals("COMPRAS")) {
                    lbTitulo.setText("COMPRAS");
                    while (tblResultados.getRowCount() > 0) {
                        modelo.removeRow(0);
                    }

                    resultados = instancias.getSql().getProductosComprasKardex(txtIdSistema.getText());
                    for (int i = 0; i < resultados.length; i++) {
                        Object[][] encabezados = instancias.getSql().getProveedorIngreso(resultados[i][0].toString(), "BODEGA-1");
                        if (encabezados.length > 0) {
                            modelo.addRow(new Object[]{resultados[i][0], encabezados[0][0], encabezados[0][1], metodos.fecha(encabezados[0][2].toString()), big.setMoneda(big.getBigDecimal(resultados[i][2].toString())), resultados[i][1],
                                big.setMoneda(big.getBigDecimal(resultados[i][4])), big.setMoneda(big.getBigDecimal(resultados[i][3])), big.setMoneda(big.getBigDecimal(resultados[i][5])),
                                big.setNumero(big.getBigDecimal(resultados[i][6])), big.setMoneda(big.getBigDecimal(resultados[i][7])), encabezados[0][3]});
                        }
                    }
                }
            } else if (tblEntradas1.getSelectedColumn() == 2 && tblEntradas1.getSelectedRow() == 2) {
                if (!lbTitulo.getText().equals("AJUSTES DE ENTRADA")) {
                    lbTitulo.setText("AJUSTES DE ENTRADA");

                    while (tblResultados.getRowCount() > 0) {
                        modelo.removeRow(0);
                    }

                    resultados = instancias.getSql().getProductosAjustesKardex(txtIdSistema.getText(), "ajustesEntrada", "BODEGA-1");
                    for (int i = 0; i < resultados.length; i++) {
                        modelo.addRow(new Object[]{resultados[i][0], "", "", metodos.fecha(resultados[i][1].toString()), big.setMoneda(big.getBigDecimal(resultados[i][3].toString())),
                            resultados[i][2], big.setMoneda(big.getBigDecimal(resultados[i][4])), this.simbolo + " 0", this.simbolo + " 0", "0", big.setMoneda(big.getBigDecimal(resultados[i][4])), resultados[i][5]});
                    }
                }
            } else if (tblEntradas1.getSelectedColumn() == 2 && tblEntradas1.getSelectedRow() == 3) {
                if (!lbTitulo.getText().equals("ANULADAS")) {
                    lbTitulo.setText("ANULADAS");

                    while (tblResultados.getRowCount() > 0) {
                        modelo.removeRow(0);
                    }

                    resultados = instancias.getSql().getProductosAnuladaKardex(txtIdSistema.getText(), "anulacion", "IdCliente", "BODEGA-1");
                    for (int i = 0; i < resultados.length; i++) {
                        modelo.addRow(new Object[]{resultados[i][0], resultados[i][1], resultados[i][2], metodos.fecha(resultados[i][3].toString()),
                            big.setMoneda(big.getBigDecimal(resultados[i][4].toString())), resultados[i][5], big.setMoneda(big.getBigDecimal(resultados[i][6])),
                            big.setMoneda(big.getBigDecimal(resultados[i][7])), big.setMoneda(big.getBigDecimal(resultados[i][8])), resultados[i][9],
                            big.setMoneda(big.getBigDecimal(resultados[i][10])), resultados[i][11]});
                    }
                }
            } else if (tblEntradas1.getSelectedColumn() == 2 && tblEntradas1.getSelectedRow() == 4) {
                if (!lbTitulo.getText().equals("NOTAS CREDITO")) {
                    lbTitulo.setText("NOTAS CREDITO");
                    while (tblResultados.getRowCount() > 0) {
                        modelo.removeRow(0);
                    }

                    resultados = instancias.getSql().getProductosNotaCreditoKardex(txtIdSistema.getText(), "BODEGA-1");
                    for (int i = 0; i < resultados.length; i++) {
                        modelo.addRow(new Object[]{resultados[i][0], resultados[i][1], resultados[i][2], metodos.fecha(resultados[i][3].toString()),
                            big.setMoneda(big.getBigDecimal(resultados[i][4].toString())), resultados[i][5], big.setMoneda(big.getBigDecimal(resultados[i][6])),
                            big.setMoneda(big.getBigDecimal(resultados[i][7])), big.setMoneda(big.getBigDecimal(resultados[i][8])), resultados[i][9],
                            big.setMoneda(big.getBigDecimal(resultados[i][10])), resultados[i][11]});
                    }
                }
            }
        }
    }//GEN-LAST:event_tblEntradas1MouseClicked

    private void tblSalidas1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSalidas1MouseClicked
        if (!txtCodigo.getText().equals("")) {

            DefaultTableModel modelo = (DefaultTableModel) tblResultados.getModel();

            Object[][] resultados;

            if (tblSalidas1.getSelectedColumn() == 2 && tblSalidas1.getSelectedRow() == 0) {
                if (!lbTitulo.getText().equals("VENTAS")) {
                    lbTitulo.setText("VENTAS");

                    while (tblResultados.getRowCount() > 0) {
                        modelo.removeRow(0);
                    }

                    resultados = instancias.getSql().getProductosTipoFacturacionKardex(txtIdSistema.getText(), "factura", "BODEGA-1");

                    for (int i = 0; i < resultados.length; i++) {
                        modelo.addRow(new Object[]{resultados[i][0], resultados[i][1], resultados[i][2], metodos.fecha(resultados[i][3].toString()),
                            big.setMoneda(big.getBigDecimal(resultados[i][4].toString())), resultados[i][5], big.setMoneda(big.getBigDecimal(resultados[i][6])),
                            big.setMoneda(big.getBigDecimal(resultados[i][7])), big.setMoneda(big.getBigDecimal(resultados[i][8])), resultados[i][9],
                            big.setMoneda(big.getBigDecimal(resultados[i][10])), resultados[i][11]});
                    }
                }
            } else if (tblSalidas1.getSelectedColumn() == 2 && tblSalidas1.getSelectedRow() == 1) {
                if (!lbTitulo.getText().equals("AJUSTES DE SALIDA")) {
                    lbTitulo.setText("AJUSTES DE SALIDA");
                    while (tblResultados.getRowCount() > 0) {
                        modelo.removeRow(0);
                    }

                    resultados = instancias.getSql().getProductosAjustesKardex(txtIdSistema.getText(), "ajustesSalida", "BODEGA-1");
                    for (int i = 0; i < resultados.length; i++) {
                        modelo.addRow(new Object[]{resultados[i][0], "", "", metodos.fecha(resultados[i][1].toString()), big.setMoneda(big.getBigDecimal(resultados[i][3].toString())),
                            resultados[i][2], big.setMoneda(big.getBigDecimal(resultados[i][4])), this.simbolo + " 0", this.simbolo + " 0", "0", big.setMoneda(big.getBigDecimal(resultados[i][4])), resultados[i][5]});
                    }
                }
            } else if (tblSalidas1.getSelectedColumn() == 2 && tblSalidas1.getSelectedRow() == 2) {
                if (!lbTitulo.getText().equals("COSTEOS")) {
                    lbTitulo.setText("COSTEOS");

                    while (tblResultados.getRowCount() > 0) {
                        modelo.removeRow(0);
                    }

                    resultados = instancias.getSql().getProductosTipoFacturacionKardex(txtIdSistema.getText(), "anulacion", "BODEGA-1");

                    for (int i = 0; i < resultados.length; i++) {
                        modelo.addRow(new Object[]{resultados[i][0], resultados[i][1], resultados[i][2], metodos.fecha(resultados[i][3].toString()),
                            big.setMoneda(big.getBigDecimal(resultados[i][4].toString())), resultados[i][5], big.setMoneda(big.getBigDecimal(resultados[i][6])),
                            big.setMoneda(big.getBigDecimal(resultados[i][7])), big.setMoneda(big.getBigDecimal(resultados[i][8])), resultados[i][9],
                            big.setMoneda(big.getBigDecimal(resultados[i][10])), resultados[i][11]});
                    }
                }
            } else if (tblSalidas1.getSelectedColumn() == 2 && tblSalidas1.getSelectedRow() == 3) {
                if (!lbTitulo.getText().equals("ARMADOS")) {
                    lbTitulo.setText("ARMADOS");

                    while (tblResultados.getRowCount() > 0) {
                        modelo.removeRow(0);
                    }

                    resultados = instancias.getSql().getProductosTipoFacturacionKardex(txtIdSistema.getText(), "anulacion", "BODEGA-1");

                    for (int i = 0; i < resultados.length; i++) {
                        modelo.addRow(new Object[]{resultados[i][0], resultados[i][1], resultados[i][2], metodos.fecha(resultados[i][3].toString()),
                            big.setMoneda(big.getBigDecimal(resultados[i][4].toString())), resultados[i][5], big.setMoneda(big.getBigDecimal(resultados[i][6])),
                            big.setMoneda(big.getBigDecimal(resultados[i][7])), big.setMoneda(big.getBigDecimal(resultados[i][8])), resultados[i][9],
                            big.setMoneda(big.getBigDecimal(resultados[i][10])), resultados[i][11]});
                    }
                }
            }
        }
    }//GEN-LAST:event_tblSalidas1MouseClicked

    private void tblSepares1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSepares1MouseClicked
        if (!txtCodigo.getText().equals("")) {

            DefaultTableModel modelo = (DefaultTableModel) tblResultados.getModel();

            Object[][] resultados;

            if (tblSepares1.getSelectedColumn() == 2 && tblSepares1.getSelectedRow() == 0) {
                if (!lbTitulo.getText().equals("PLAN SEPARES")) {
                    lbTitulo.setText("PLAN SEPARES");

                    while (tblResultados.getRowCount() > 0) {
                        modelo.removeRow(0);
                    }

                    resultados = instancias.getSql().getProductosTipoSepareKardex(txtIdSistema.getText(), "planSepare", "IdTercero", "BODEGA-1");

                    for (int i = 0; i < resultados.length; i++) {
                        modelo.addRow(new Object[]{resultados[i][0], resultados[i][1], resultados[i][2], metodos.fecha(resultados[i][3].toString()),
                            big.setMoneda(big.getBigDecimal(resultados[i][4].toString())), resultados[i][5], big.setMoneda(big.getBigDecimal(resultados[i][6])),
                            big.setMoneda(big.getBigDecimal(resultados[i][7])), big.setMoneda(big.getBigDecimal(resultados[i][8])), resultados[i][9],
                            big.setMoneda(big.getBigDecimal(resultados[i][10])), resultados[i][11]});
                    }
                }
            } else if (tblSepares1.getSelectedColumn() == 2 && tblSepares1.getSelectedRow() == 1) {
                if (!lbTitulo.getText().equals("ORDENES DE SERVICIO")) {
                    lbTitulo.setText("ORDENES DE SERVICIO");

                    while (tblResultados.getRowCount() > 0) {
                        modelo.removeRow(0);
                    }

                    resultados = instancias.getSql().getProductosTipoSepareKardex(txtIdSistema.getText(), "oServicio", "IdCliente", "BODEGA-1");

                    for (int i = 0; i < resultados.length; i++) {
                        modelo.addRow(new Object[]{resultados[i][0], resultados[i][1], resultados[i][2], metodos.fecha(resultados[i][3].toString()),
                            big.setMoneda(big.getBigDecimal(resultados[i][4].toString())), resultados[i][5], big.setMoneda(big.getBigDecimal(resultados[i][6])),
                            big.setMoneda(big.getBigDecimal(resultados[i][7])), big.setMoneda(big.getBigDecimal(resultados[i][8])), resultados[i][9],
                            big.setMoneda(big.getBigDecimal(resultados[i][10])), resultados[i][11]});
                    }
                }
            } else if (tblSepares1.getSelectedColumn() == 2 && tblSepares1.getSelectedRow() == 2) {
                if (!lbTitulo.getText().equals("PEDIDOS")) {
                    lbTitulo.setText("PEDIDOS");

                    while (tblResultados.getRowCount() > 0) {
                        modelo.removeRow(0);
                    }

                    resultados = instancias.getSql().getProductosTipoSepareKardex(txtIdSistema.getText(), "pedidos", "IdTercero", "BODEGA-1");

                    for (int i = 0; i < resultados.length; i++) {
                        modelo.addRow(new Object[]{resultados[i][0], resultados[i][1], resultados[i][2], metodos.fecha(resultados[i][3].toString()),
                            big.setMoneda(big.getBigDecimal(resultados[i][4].toString())), resultados[i][5], big.setMoneda(big.getBigDecimal(resultados[i][6])),
                            big.setMoneda(big.getBigDecimal(resultados[i][7])), big.setMoneda(big.getBigDecimal(resultados[i][8])), resultados[i][9],
                            big.setMoneda(big.getBigDecimal(resultados[i][10])), resultados[i][11]});
                    }
                }
            } else if (tblSepares1.getSelectedColumn() == 2 && tblSepares1.getSelectedRow() == 3) {
                if (!lbTitulo.getText().equals("CONGELADA")) {
                    lbTitulo.setText("CONGELADA");
                    while (tblResultados.getRowCount() > 0) {
                        modelo.removeRow(0);
                    }

                    resultados = instancias.getSql().getProductosCongeladaKardex(txtIdSistema.getText(), "congeladas", "IdTercero", "BODEGA-1");
                    for (int i = 0; i < resultados.length; i++) {
                        modelo.addRow(new Object[]{resultados[i][0], resultados[i][1], resultados[i][2], metodos.fecha(resultados[i][3].toString()),
                            big.setMoneda(big.getBigDecimal(resultados[i][4].toString())), resultados[i][5], big.setMoneda(big.getBigDecimal(resultados[i][6])),
                            big.setMoneda(big.getBigDecimal(resultados[i][7])), big.setMoneda(big.getBigDecimal(resultados[i][8])), resultados[i][9],
                            big.setMoneda(big.getBigDecimal(resultados[i][10])), resultados[i][11]});
                    }
                }
            } else if (tblSepares1.getSelectedColumn() == 2 && tblSepares1.getSelectedRow() == 4) {
                if (!lbTitulo.getText().equals("TRASLADOS ENTRE BODEGAS")) {
                    lbTitulo.setText("TRASLADOS ENTRE BODEGAS");
                    while (tblResultados.getRowCount() > 0) {
                        modelo.removeRow(0);
                    }

                    resultados = instancias.getSql().getProductosAjustesKardex1(txtIdSistema.getText(), "trasladosInternos");
                    for (int i = 0; i < resultados.length; i++) {
                        modelo.addRow(new Object[]{resultados[i][0], "", "", metodos.fecha(resultados[i][1].toString()), big.setMoneda(big.getBigDecimal(resultados[i][3].toString())),
                            resultados[i][2], big.setMoneda(big.getBigDecimal(resultados[i][4])), this.simbolo + " 0", this.simbolo + " 0", "0", big.setMoneda(big.getBigDecimal(resultados[i][4])), resultados[i][5]});
                    }

//                    resultados = sql.getProductosCongeladaKardex(txtIdSistema.getText(), "trasladosinternos", "", "123-22");
//                    for (int i = 0; i < resultados.length; i++) {
//                        modelo.addRow(new Object[]{resultados[i][0], resultados[i][1], resultados[i][2], metodos.fecha(resultados[i][3].toString()),
//                            big.setMoneda(big.getBigDecimal(resultados[i][4].toString())), resultados[i][5], big.setMoneda(big.getBigDecimal(resultados[i][6])),
//                            big.setMoneda(big.getBigDecimal(resultados[i][7])), big.setMoneda(big.getBigDecimal(resultados[i][8])), resultados[i][9],
//                            big.setMoneda(big.getBigDecimal(resultados[i][10])), resultados[i][11]});
//                    }
                }
            }
        }
    }//GEN-LAST:event_tblSepares1MouseClicked

    private void tblEntradas2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEntradas2MouseClicked
        if (!txtCodigo.getText().equals("")) {

            DefaultTableModel modelo = (DefaultTableModel) tblResultados.getModel();

            Object[][] resultados;

            if (tblEntradas2.getSelectedColumn() == 2 && tblEntradas2.getSelectedRow() == 0) {
                if (!lbTitulo.getText().equals("INVENTARIO INICIAL")) {
                    lbTitulo.setText("INVENTARIO INICIAL");

                    while (tblResultados.getRowCount() > 0) {
                        modelo.removeRow(0);
                    }

                    resultados = instancias.getSql().getProductosInvInicialKardex(txtIdSistema.getText());
                    for (int i = 0; i < resultados.length; i++) {
                        Object[][] encabezados = instancias.getSql().getProveedorIngreso(resultados[i][0].toString(), "BODEGA-2");
                        if (encabezados.length > 0) {
                            modelo.addRow(new Object[]{"INV-INICIAL", encabezados[0][0], encabezados[0][1], metodos.fecha(encabezados[0][2].toString()), big.setMoneda(big.getBigDecimal(resultados[i][2].toString())), resultados[i][1],
                                big.setMoneda(big.getBigDecimal(resultados[i][4])), big.setMoneda(big.getBigDecimal(resultados[i][3])), big.setMoneda(big.getBigDecimal(resultados[i][5])),
                                big.setNumero(big.getBigDecimal(resultados[i][6])), big.setMoneda(big.getBigDecimal(resultados[i][7])), encabezados[0][3]});
                        }
                    }
                }
            } else if (tblEntradas2.getSelectedColumn() == 2 && tblEntradas2.getSelectedRow() == 1) {
                if (!lbTitulo.getText().equals("COMPRAS")) {
                    lbTitulo.setText("COMPRAS");
                    while (tblResultados.getRowCount() > 0) {
                        modelo.removeRow(0);
                    }

                    resultados = instancias.getSql().getProductosComprasKardex(txtIdSistema.getText());
                    for (int i = 0; i < resultados.length; i++) {
                        Object[][] encabezados = instancias.getSql().getProveedorIngreso(resultados[i][0].toString(), "BODEGA-2");
                        if (encabezados.length > 0) {
                            modelo.addRow(new Object[]{resultados[i][0], encabezados[0][0], encabezados[0][1], metodos.fecha(encabezados[0][2].toString()), big.setMoneda(big.getBigDecimal(resultados[i][2].toString())), resultados[i][1],
                                big.setMoneda(big.getBigDecimal(resultados[i][4])), big.setMoneda(big.getBigDecimal(resultados[i][3])), big.setMoneda(big.getBigDecimal(resultados[i][5])),
                                big.setNumero(big.getBigDecimal(resultados[i][6])), big.setMoneda(big.getBigDecimal(resultados[i][7])), encabezados[0][3]});
                        }
                    }
                }
            } else if (tblEntradas2.getSelectedColumn() == 2 && tblEntradas2.getSelectedRow() == 2) {
                if (!lbTitulo.getText().equals("AJUSTES DE ENTRADA")) {
                    lbTitulo.setText("AJUSTES DE ENTRADA");

                    while (tblResultados.getRowCount() > 0) {
                        modelo.removeRow(0);
                    }

                    resultados = instancias.getSql().getProductosAjustesKardex(txtIdSistema.getText(), "ajustesEntrada", "BODEGA-2");
                    for (int i = 0; i < resultados.length; i++) {
                        modelo.addRow(new Object[]{resultados[i][0], "", "", metodos.fecha(resultados[i][1].toString()), big.setMoneda(big.getBigDecimal(resultados[i][3].toString())),
                            resultados[i][2], big.setMoneda(big.getBigDecimal(resultados[i][4])), this.simbolo + " 0", this.simbolo + " 0", "0", big.setMoneda(big.getBigDecimal(resultados[i][4])), resultados[i][5]});
                    }
                }
            } else if (tblEntradas2.getSelectedColumn() == 2 && tblEntradas2.getSelectedRow() == 3) {
                if (!lbTitulo.getText().equals("ANULADAS")) {
                    lbTitulo.setText("ANULADAS");

                    while (tblResultados.getRowCount() > 0) {
                        modelo.removeRow(0);
                    }

                    resultados = instancias.getSql().getProductosAnuladaKardex(txtIdSistema.getText(), "anulacion", "IdCliente", "BODEGA-2");
                    for (int i = 0; i < resultados.length; i++) {
                        modelo.addRow(new Object[]{resultados[i][0], resultados[i][1], resultados[i][2], metodos.fecha(resultados[i][3].toString()),
                            big.setMoneda(big.getBigDecimal(resultados[i][4].toString())), resultados[i][5], big.setMoneda(big.getBigDecimal(resultados[i][6])),
                            big.setMoneda(big.getBigDecimal(resultados[i][7])), big.setMoneda(big.getBigDecimal(resultados[i][8])), resultados[i][9],
                            big.setMoneda(big.getBigDecimal(resultados[i][10])), resultados[i][11]});
                    }
                }
            } else if (tblEntradas2.getSelectedColumn() == 2 && tblEntradas2.getSelectedRow() == 4) {
                if (!lbTitulo.getText().equals("NOTAS CREDITO")) {
                    lbTitulo.setText("NOTAS CREDITO");
                    while (tblResultados.getRowCount() > 0) {
                        modelo.removeRow(0);
                    }

                    resultados = instancias.getSql().getProductosNotaCreditoKardex(txtIdSistema.getText(), "BODEGA-2");
                    for (int i = 0; i < resultados.length; i++) {
                        modelo.addRow(new Object[]{resultados[i][0], resultados[i][1], resultados[i][2], metodos.fecha(resultados[i][3].toString()),
                            big.setMoneda(big.getBigDecimal(resultados[i][4].toString())), resultados[i][5], big.setMoneda(big.getBigDecimal(resultados[i][6])),
                            big.setMoneda(big.getBigDecimal(resultados[i][7])), big.setMoneda(big.getBigDecimal(resultados[i][8])), resultados[i][9],
                            big.setMoneda(big.getBigDecimal(resultados[i][10])), resultados[i][11]});
                    }
                }
            }
        }
    }//GEN-LAST:event_tblEntradas2MouseClicked

    private void tblSalidas2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSalidas2MouseClicked
        if (!txtCodigo.getText().equals("")) {

            DefaultTableModel modelo = (DefaultTableModel) tblResultados.getModel();

            Object[][] resultados;

            if (tblSalidas2.getSelectedColumn() == 2 && tblSalidas2.getSelectedRow() == 0) {
                if (!lbTitulo.getText().equals("VENTAS")) {
                    lbTitulo.setText("VENTAS");

                    while (tblResultados.getRowCount() > 0) {
                        modelo.removeRow(0);
                    }

                    resultados = instancias.getSql().getProductosTipoFacturacionKardex(txtIdSistema.getText(), "factura", "BODEGA-2");

                    for (int i = 0; i < resultados.length; i++) {
                        modelo.addRow(new Object[]{resultados[i][0], resultados[i][1], resultados[i][2], metodos.fecha(resultados[i][3].toString()),
                            big.setMoneda(big.getBigDecimal(resultados[i][4].toString())), resultados[i][5], big.setMoneda(big.getBigDecimal(resultados[i][6])),
                            big.setMoneda(big.getBigDecimal(resultados[i][7])), big.setMoneda(big.getBigDecimal(resultados[i][8])), resultados[i][9],
                            big.setMoneda(big.getBigDecimal(resultados[i][10])), resultados[i][11]});
                    }
                }
            } else if (tblSalidas2.getSelectedColumn() == 2 && tblSalidas2.getSelectedRow() == 1) {
                if (!lbTitulo.getText().equals("AJUSTES DE SALIDA")) {
                    lbTitulo.setText("AJUSTES DE SALIDA");
                    while (tblResultados.getRowCount() > 0) {
                        modelo.removeRow(0);
                    }

                    resultados = instancias.getSql().getProductosAjustesKardex(txtIdSistema.getText(), "ajustesSalida", "BODEGA-2");
                    for (int i = 0; i < resultados.length; i++) {
                        modelo.addRow(new Object[]{resultados[i][0], "", "", metodos.fecha(resultados[i][1].toString()), big.setMoneda(big.getBigDecimal(resultados[i][3].toString())),
                            resultados[i][2], big.setMoneda(big.getBigDecimal(resultados[i][4])), this.simbolo + " 0", this.simbolo + " 0", "0", big.setMoneda(big.getBigDecimal(resultados[i][4])), resultados[i][5]});
                    }
                }
            } else if (tblSalidas2.getSelectedColumn() == 2 && tblSalidas2.getSelectedRow() == 2) {
                if (!lbTitulo.getText().equals("COSTEOS")) {
                    lbTitulo.setText("COSTEOS");

                    while (tblResultados.getRowCount() > 0) {
                        modelo.removeRow(0);
                    }

                    resultados = instancias.getSql().getProductosTipoFacturacionKardex(txtIdSistema.getText(), "anulacion", "BODEGA-2");

                    for (int i = 0; i < resultados.length; i++) {
                        modelo.addRow(new Object[]{resultados[i][0], resultados[i][1], resultados[i][2], metodos.fecha(resultados[i][3].toString()),
                            big.setMoneda(big.getBigDecimal(resultados[i][4].toString())), resultados[i][5], big.setMoneda(big.getBigDecimal(resultados[i][6])),
                            big.setMoneda(big.getBigDecimal(resultados[i][7])), big.setMoneda(big.getBigDecimal(resultados[i][8])), resultados[i][9],
                            big.setMoneda(big.getBigDecimal(resultados[i][10])), resultados[i][11]});
                    }
                }
            } else if (tblSalidas2.getSelectedColumn() == 2 && tblSalidas2.getSelectedRow() == 3) {
                if (!lbTitulo.getText().equals("ARMADOS")) {
                    lbTitulo.setText("ARMADOS");

                    while (tblResultados.getRowCount() > 0) {
                        modelo.removeRow(0);
                    }

                    resultados = instancias.getSql().getProductosTipoFacturacionKardex(txtIdSistema.getText(), "anulacion", "BODEGA-2");

                    for (int i = 0; i < resultados.length; i++) {
                        modelo.addRow(new Object[]{resultados[i][0], resultados[i][1], resultados[i][2], metodos.fecha(resultados[i][3].toString()),
                            big.setMoneda(big.getBigDecimal(resultados[i][4].toString())), resultados[i][5], big.setMoneda(big.getBigDecimal(resultados[i][6])),
                            big.setMoneda(big.getBigDecimal(resultados[i][7])), big.setMoneda(big.getBigDecimal(resultados[i][8])), resultados[i][9],
                            big.setMoneda(big.getBigDecimal(resultados[i][10])), resultados[i][11]});
                    }
                }
            }
        }
    }//GEN-LAST:event_tblSalidas2MouseClicked

    private void tblSepares2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSepares2MouseClicked
        if (!txtCodigo.getText().equals("")) {

            DefaultTableModel modelo = (DefaultTableModel) tblResultados.getModel();

            Object[][] resultados;

            if (tblSepares2.getSelectedColumn() == 2 && tblSepares2.getSelectedRow() == 0) {
                if (!lbTitulo.getText().equals("PLAN SEPARES")) {
                    lbTitulo.setText("PLAN SEPARES");

                    while (tblResultados.getRowCount() > 0) {
                        modelo.removeRow(0);
                    }

                    resultados = instancias.getSql().getProductosTipoSepareKardex(txtIdSistema.getText(), "planSepare", "IdTercero", "BODEGA-2");

                    for (int i = 0; i < resultados.length; i++) {
                        modelo.addRow(new Object[]{resultados[i][0], resultados[i][1], resultados[i][2], metodos.fecha(resultados[i][3].toString()),
                            big.setMoneda(big.getBigDecimal(resultados[i][4].toString())), resultados[i][5], big.setMoneda(big.getBigDecimal(resultados[i][6])),
                            big.setMoneda(big.getBigDecimal(resultados[i][7])), big.setMoneda(big.getBigDecimal(resultados[i][8])), resultados[i][9],
                            big.setMoneda(big.getBigDecimal(resultados[i][10])), resultados[i][11]});
                    }
                }
            } else if (tblSepares2.getSelectedColumn() == 2 && tblSepares2.getSelectedRow() == 1) {
                if (!lbTitulo.getText().equals("ORDENES DE SERVICIO")) {
                    lbTitulo.setText("ORDENES DE SERVICIO");

                    while (tblResultados.getRowCount() > 0) {
                        modelo.removeRow(0);
                    }

                    resultados = instancias.getSql().getProductosTipoSepareKardex(txtIdSistema.getText(), "oServicio", "IdCliente", "BODEGA-2");

                    for (int i = 0; i < resultados.length; i++) {
                        modelo.addRow(new Object[]{resultados[i][0], resultados[i][1], resultados[i][2], metodos.fecha(resultados[i][3].toString()),
                            big.setMoneda(big.getBigDecimal(resultados[i][4].toString())), resultados[i][5], big.setMoneda(big.getBigDecimal(resultados[i][6])),
                            big.setMoneda(big.getBigDecimal(resultados[i][7])), big.setMoneda(big.getBigDecimal(resultados[i][8])), resultados[i][9],
                            big.setMoneda(big.getBigDecimal(resultados[i][10])), resultados[i][11]});
                    }
                }
            } else if (tblSepares2.getSelectedColumn() == 2 && tblSepares2.getSelectedRow() == 2) {
                if (!lbTitulo.getText().equals("PEDIDOS")) {
                    lbTitulo.setText("PEDIDOS");

                    while (tblResultados.getRowCount() > 0) {
                        modelo.removeRow(0);
                    }

                    resultados = instancias.getSql().getProductosTipoSepareKardex(txtIdSistema.getText(), "pedidos", "IdTercero", "BODEGA-2");

                    for (int i = 0; i < resultados.length; i++) {
                        modelo.addRow(new Object[]{resultados[i][0], resultados[i][1], resultados[i][2], metodos.fecha(resultados[i][3].toString()),
                            big.setMoneda(big.getBigDecimal(resultados[i][4].toString())), resultados[i][5], big.setMoneda(big.getBigDecimal(resultados[i][6])),
                            big.setMoneda(big.getBigDecimal(resultados[i][7])), big.setMoneda(big.getBigDecimal(resultados[i][8])), resultados[i][9],
                            big.setMoneda(big.getBigDecimal(resultados[i][10])), resultados[i][11]});
                    }
                }
            } else if (tblSepares2.getSelectedColumn() == 2 && tblSepares2.getSelectedRow() == 3) {
                if (!lbTitulo.getText().equals("CONGELADA")) {
                    lbTitulo.setText("CONGELADA");
                    while (tblResultados.getRowCount() > 0) {
                        modelo.removeRow(0);
                    }

                    resultados = instancias.getSql().getProductosCongeladaKardex(txtIdSistema.getText(), "congeladas", "IdTercero", "BODEGA-2");
                    for (int i = 0; i < resultados.length; i++) {
                        modelo.addRow(new Object[]{resultados[i][0], resultados[i][1], resultados[i][2], metodos.fecha(resultados[i][3].toString()),
                            big.setMoneda(big.getBigDecimal(resultados[i][4].toString())), resultados[i][5], big.setMoneda(big.getBigDecimal(resultados[i][6])),
                            big.setMoneda(big.getBigDecimal(resultados[i][7])), big.setMoneda(big.getBigDecimal(resultados[i][8])), resultados[i][9],
                            big.setMoneda(big.getBigDecimal(resultados[i][10])), resultados[i][11]});
                    }
                }
            } else if (tblSepares2.getSelectedColumn() == 2 && tblSepares2.getSelectedRow() == 4) {
                if (!lbTitulo.getText().equals("TRASLADOS ENTRE BODEGAS")) {
                    lbTitulo.setText("TRASLADOS ENTRE BODEGAS");
                    while (tblResultados.getRowCount() > 0) {
                        modelo.removeRow(0);
                    }

                    resultados = instancias.getSql().getProductosAjustesKardex1(txtIdSistema.getText(), "trasladosInternos");
                    for (int i = 0; i < resultados.length; i++) {
                        modelo.addRow(new Object[]{resultados[i][0], "", "", metodos.fecha(resultados[i][1].toString()), big.setMoneda(big.getBigDecimal(resultados[i][3].toString())),
                            resultados[i][2], big.setMoneda(big.getBigDecimal(resultados[i][4])), this.simbolo + " 0", this.simbolo + " 0", "0", big.setMoneda(big.getBigDecimal(resultados[i][4])), resultados[i][5]});
                    }

//                    resultados = sql.getProductosCongeladaKardex(txtIdSistema.getText(), "trasladosinternos", "", "123-22");
//                    for (int i = 0; i < resultados.length; i++) {
//                        modelo.addRow(new Object[]{resultados[i][0], resultados[i][1], resultados[i][2], metodos.fecha(resultados[i][3].toString()),
//                            big.setMoneda(big.getBigDecimal(resultados[i][4].toString())), resultados[i][5], big.setMoneda(big.getBigDecimal(resultados[i][6])),
//                            big.setMoneda(big.getBigDecimal(resultados[i][7])), big.setMoneda(big.getBigDecimal(resultados[i][8])), resultados[i][9],
//                            big.setMoneda(big.getBigDecimal(resultados[i][10])), resultados[i][11]});
//                    }
                }
            }
        }
    }//GEN-LAST:event_tblSepares2MouseClicked

    private void tblEntradas3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEntradas3MouseClicked
        if (!txtCodigo.getText().equals("")) {

            DefaultTableModel modelo = (DefaultTableModel) tblResultados.getModel();

            Object[][] resultados;

            if (tblEntradas3.getSelectedColumn() == 2 && tblEntradas3.getSelectedRow() == 0) {
                if (!lbTitulo.getText().equals("INVENTARIO INICIAL")) {
                    lbTitulo.setText("INVENTARIO INICIAL");

                    while (tblResultados.getRowCount() > 0) {
                        modelo.removeRow(0);
                    }

                    resultados = instancias.getSql().getProductosInvInicialKardex(txtIdSistema.getText());
                    for (int i = 0; i < resultados.length; i++) {
                        Object[][] encabezados = instancias.getSql().getProveedorIngreso(resultados[i][0].toString(), "BODEGA-3");
                        if (encabezados.length > 0) {
                            modelo.addRow(new Object[]{"INV-INICIAL", encabezados[0][0], encabezados[0][1], metodos.fecha(encabezados[0][2].toString()), big.setMoneda(big.getBigDecimal(resultados[i][2].toString())), resultados[i][1],
                                big.setMoneda(big.getBigDecimal(resultados[i][4])), big.setMoneda(big.getBigDecimal(resultados[i][3])), big.setMoneda(big.getBigDecimal(resultados[i][5])),
                                big.setNumero(big.getBigDecimal(resultados[i][6])), big.setMoneda(big.getBigDecimal(resultados[i][7])), encabezados[0][3]});
                        }
                    }
                }
            } else if (tblEntradas3.getSelectedColumn() == 2 && tblEntradas3.getSelectedRow() == 1) {
                if (!lbTitulo.getText().equals("COMPRAS")) {
                    lbTitulo.setText("COMPRAS");
                    while (tblResultados.getRowCount() > 0) {
                        modelo.removeRow(0);
                    }

                    resultados = instancias.getSql().getProductosComprasKardex(txtIdSistema.getText());
                    for (int i = 0; i < resultados.length; i++) {
                        Object[][] encabezados = instancias.getSql().getProveedorIngreso(resultados[i][0].toString(), "BODEGA-3");
                        if (encabezados.length > 0) {
                            modelo.addRow(new Object[]{resultados[i][0], encabezados[0][0], encabezados[0][1], metodos.fecha(encabezados[0][2].toString()), big.setMoneda(big.getBigDecimal(resultados[i][2].toString())), resultados[i][1],
                                big.setMoneda(big.getBigDecimal(resultados[i][4])), big.setMoneda(big.getBigDecimal(resultados[i][3])), big.setMoneda(big.getBigDecimal(resultados[i][5])),
                                big.setNumero(big.getBigDecimal(resultados[i][6])), big.setMoneda(big.getBigDecimal(resultados[i][7])), encabezados[0][3]});
                        }
                    }
                }
            } else if (tblEntradas3.getSelectedColumn() == 2 && tblEntradas3.getSelectedRow() == 2) {
                if (!lbTitulo.getText().equals("AJUSTES DE ENTRADA")) {
                    lbTitulo.setText("AJUSTES DE ENTRADA");

                    while (tblResultados.getRowCount() > 0) {
                        modelo.removeRow(0);
                    }

                    resultados = instancias.getSql().getProductosAjustesKardex(txtIdSistema.getText(), "ajustesEntrada", "BODEGA-3");
                    for (int i = 0; i < resultados.length; i++) {
                        modelo.addRow(new Object[]{resultados[i][0], "", "", metodos.fecha(resultados[i][1].toString()), big.setMoneda(big.getBigDecimal(resultados[i][3].toString())),
                            resultados[i][2], big.setMoneda(big.getBigDecimal(resultados[i][4])), this.simbolo + " 0", this.simbolo + " 0", "0", big.setMoneda(big.getBigDecimal(resultados[i][4])), resultados[i][5]});
                    }
                }
            } else if (tblEntradas3.getSelectedColumn() == 2 && tblEntradas3.getSelectedRow() == 3) {
                if (!lbTitulo.getText().equals("ANULADAS")) {
                    lbTitulo.setText("ANULADAS");

                    while (tblResultados.getRowCount() > 0) {
                        modelo.removeRow(0);
                    }

                    resultados = instancias.getSql().getProductosAnuladaKardex(txtIdSistema.getText(), "anulacion", "IdCliente", "BODEGA-3");
                    for (int i = 0; i < resultados.length; i++) {
                        modelo.addRow(new Object[]{resultados[i][0], resultados[i][1], resultados[i][2], metodos.fecha(resultados[i][3].toString()),
                            big.setMoneda(big.getBigDecimal(resultados[i][4].toString())), resultados[i][5], big.setMoneda(big.getBigDecimal(resultados[i][6])),
                            big.setMoneda(big.getBigDecimal(resultados[i][7])), big.setMoneda(big.getBigDecimal(resultados[i][8])), resultados[i][9],
                            big.setMoneda(big.getBigDecimal(resultados[i][10])), resultados[i][11]});
                    }
                }
            } else if (tblEntradas3.getSelectedColumn() == 2 && tblEntradas3.getSelectedRow() == 4) {
                if (!lbTitulo.getText().equals("NOTAS CREDITO")) {
                    lbTitulo.setText("NOTAS CREDITO");
                    while (tblResultados.getRowCount() > 0) {
                        modelo.removeRow(0);
                    }

                    resultados = instancias.getSql().getProductosNotaCreditoKardex(txtIdSistema.getText(), "BODEGA-3");
                    for (int i = 0; i < resultados.length; i++) {
                        modelo.addRow(new Object[]{resultados[i][0], resultados[i][1], resultados[i][2], metodos.fecha(resultados[i][3].toString()),
                            big.setMoneda(big.getBigDecimal(resultados[i][4].toString())), resultados[i][5], big.setMoneda(big.getBigDecimal(resultados[i][6])),
                            big.setMoneda(big.getBigDecimal(resultados[i][7])), big.setMoneda(big.getBigDecimal(resultados[i][8])), resultados[i][9],
                            big.setMoneda(big.getBigDecimal(resultados[i][10])), resultados[i][11]});
                    }
                }
            }
        }
    }//GEN-LAST:event_tblEntradas3MouseClicked

    private void tblSalidas3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSalidas3MouseClicked
        if (!txtCodigo.getText().equals("")) {

            DefaultTableModel modelo = (DefaultTableModel) tblResultados.getModel();

            Object[][] resultados;

            if (tblSalidas3.getSelectedColumn() == 2 && tblSalidas3.getSelectedRow() == 0) {
                if (!lbTitulo.getText().equals("VENTAS")) {
                    lbTitulo.setText("VENTAS");

                    while (tblResultados.getRowCount() > 0) {
                        modelo.removeRow(0);
                    }

                    resultados = instancias.getSql().getProductosTipoFacturacionKardex(txtIdSistema.getText(), "factura", "BODEGA-3");

                    for (int i = 0; i < resultados.length; i++) {
                        modelo.addRow(new Object[]{resultados[i][0], resultados[i][1], resultados[i][2], metodos.fecha(resultados[i][3].toString()),
                            big.setMoneda(big.getBigDecimal(resultados[i][4].toString())), resultados[i][5], big.setMoneda(big.getBigDecimal(resultados[i][6])),
                            big.setMoneda(big.getBigDecimal(resultados[i][7])), big.setMoneda(big.getBigDecimal(resultados[i][8])), resultados[i][9],
                            big.setMoneda(big.getBigDecimal(resultados[i][10])), resultados[i][11]});
                    }
                }
            } else if (tblSalidas3.getSelectedColumn() == 2 && tblSalidas3.getSelectedRow() == 1) {
                if (!lbTitulo.getText().equals("AJUSTES DE SALIDA")) {
                    lbTitulo.setText("AJUSTES DE SALIDA");
                    while (tblResultados.getRowCount() > 0) {
                        modelo.removeRow(0);
                    }

                    resultados = instancias.getSql().getProductosAjustesKardex(txtIdSistema.getText(), "ajustesSalida", "BODEGA-3");
                    for (int i = 0; i < resultados.length; i++) {
                        modelo.addRow(new Object[]{resultados[i][0], "", "", metodos.fecha(resultados[i][1].toString()), big.setMoneda(big.getBigDecimal(resultados[i][3].toString())),
                            resultados[i][2], big.setMoneda(big.getBigDecimal(resultados[i][4])), this.simbolo + " 0", this.simbolo + " 0", "0", big.setMoneda(big.getBigDecimal(resultados[i][4])), resultados[i][5]});
                    }
                }
            } else if (tblSalidas3.getSelectedColumn() == 2 && tblSalidas3.getSelectedRow() == 2) {
                if (!lbTitulo.getText().equals("COSTEOS")) {
                    lbTitulo.setText("COSTEOS");

                    while (tblResultados.getRowCount() > 0) {
                        modelo.removeRow(0);
                    }

                    resultados = instancias.getSql().getProductosTipoFacturacionKardex(txtIdSistema.getText(), "anulacion", "BODEGA-3");

                    for (int i = 0; i < resultados.length; i++) {
                        modelo.addRow(new Object[]{resultados[i][0], resultados[i][1], resultados[i][2], metodos.fecha(resultados[i][3].toString()),
                            big.setMoneda(big.getBigDecimal(resultados[i][4].toString())), resultados[i][5], big.setMoneda(big.getBigDecimal(resultados[i][6])),
                            big.setMoneda(big.getBigDecimal(resultados[i][7])), big.setMoneda(big.getBigDecimal(resultados[i][8])), resultados[i][9],
                            big.setMoneda(big.getBigDecimal(resultados[i][10])), resultados[i][11]});
                    }
                }
            } else if (tblSalidas3.getSelectedColumn() == 2 && tblSalidas3.getSelectedRow() == 3) {
                if (!lbTitulo.getText().equals("ARMADOS")) {
                    lbTitulo.setText("ARMADOS");

                    while (tblResultados.getRowCount() > 0) {
                        modelo.removeRow(0);
                    }

                    resultados = instancias.getSql().getProductosTipoFacturacionKardex(txtIdSistema.getText(), "anulacion", "BODEGA-3");

                    for (int i = 0; i < resultados.length; i++) {
                        modelo.addRow(new Object[]{resultados[i][0], resultados[i][1], resultados[i][2], metodos.fecha(resultados[i][3].toString()),
                            big.setMoneda(big.getBigDecimal(resultados[i][4].toString())), resultados[i][5], big.setMoneda(big.getBigDecimal(resultados[i][6])),
                            big.setMoneda(big.getBigDecimal(resultados[i][7])), big.setMoneda(big.getBigDecimal(resultados[i][8])), resultados[i][9],
                            big.setMoneda(big.getBigDecimal(resultados[i][10])), resultados[i][11]});
                    }
                }
            }
        }
    }//GEN-LAST:event_tblSalidas3MouseClicked

    private void tblSepares3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSepares3MouseClicked
        if (!txtCodigo.getText().equals("")) {

            DefaultTableModel modelo = (DefaultTableModel) tblResultados.getModel();

            Object[][] resultados;

            if (tblSepares3.getSelectedColumn() == 2 && tblSepares3.getSelectedRow() == 0) {
                if (!lbTitulo.getText().equals("PLAN SEPARES")) {
                    lbTitulo.setText("PLAN SEPARES");

                    while (tblResultados.getRowCount() > 0) {
                        modelo.removeRow(0);
                    }

                    resultados = instancias.getSql().getProductosTipoSepareKardex(txtIdSistema.getText(), "planSepare", "IdTercero", "BODEGA-3");

                    for (int i = 0; i < resultados.length; i++) {
                        modelo.addRow(new Object[]{resultados[i][0], resultados[i][1], resultados[i][2], metodos.fecha(resultados[i][3].toString()),
                            big.setMoneda(big.getBigDecimal(resultados[i][4].toString())), resultados[i][5], big.setMoneda(big.getBigDecimal(resultados[i][6])),
                            big.setMoneda(big.getBigDecimal(resultados[i][7])), big.setMoneda(big.getBigDecimal(resultados[i][8])), resultados[i][9],
                            big.setMoneda(big.getBigDecimal(resultados[i][10])), resultados[i][11]});
                    }
                }
            } else if (tblSepares3.getSelectedColumn() == 2 && tblSepares3.getSelectedRow() == 1) {
                if (!lbTitulo.getText().equals("ORDENES DE SERVICIO")) {
                    lbTitulo.setText("ORDENES DE SERVICIO");

                    while (tblResultados.getRowCount() > 0) {
                        modelo.removeRow(0);
                    }

                    resultados = instancias.getSql().getProductosTipoSepareKardex(txtIdSistema.getText(), "oServicio", "IdCliente", "BODEGA-3");

                    for (int i = 0; i < resultados.length; i++) {
                        modelo.addRow(new Object[]{resultados[i][0], resultados[i][1], resultados[i][2], metodos.fecha(resultados[i][3].toString()),
                            big.setMoneda(big.getBigDecimal(resultados[i][4].toString())), resultados[i][5], big.setMoneda(big.getBigDecimal(resultados[i][6])),
                            big.setMoneda(big.getBigDecimal(resultados[i][7])), big.setMoneda(big.getBigDecimal(resultados[i][8])), resultados[i][9],
                            big.setMoneda(big.getBigDecimal(resultados[i][10])), resultados[i][11]});
                    }
                }
            } else if (tblSepares3.getSelectedColumn() == 2 && tblSepares3.getSelectedRow() == 2) {
                if (!lbTitulo.getText().equals("PEDIDOS")) {
                    lbTitulo.setText("PEDIDOS");

                    while (tblResultados.getRowCount() > 0) {
                        modelo.removeRow(0);
                    }

                    resultados = instancias.getSql().getProductosTipoSepareKardex(txtIdSistema.getText(), "pedidos", "IdTercero", "BODEGA-3");

                    for (int i = 0; i < resultados.length; i++) {
                        modelo.addRow(new Object[]{resultados[i][0], resultados[i][1], resultados[i][2], metodos.fecha(resultados[i][3].toString()),
                            big.setMoneda(big.getBigDecimal(resultados[i][4].toString())), resultados[i][5], big.setMoneda(big.getBigDecimal(resultados[i][6])),
                            big.setMoneda(big.getBigDecimal(resultados[i][7])), big.setMoneda(big.getBigDecimal(resultados[i][8])), resultados[i][9],
                            big.setMoneda(big.getBigDecimal(resultados[i][10])), resultados[i][11]});
                    }
                }
            } else if (tblSepares3.getSelectedColumn() == 2 && tblSepares3.getSelectedRow() == 3) {
                if (!lbTitulo.getText().equals("CONGELADA")) {
                    lbTitulo.setText("CONGELADA");
                    while (tblResultados.getRowCount() > 0) {
                        modelo.removeRow(0);
                    }

                    resultados = instancias.getSql().getProductosCongeladaKardex(txtIdSistema.getText(), "congeladas", "IdTercero", "BODEGA-3");
                    for (int i = 0; i < resultados.length; i++) {
                        modelo.addRow(new Object[]{resultados[i][0], resultados[i][1], resultados[i][2], metodos.fecha(resultados[i][3].toString()),
                            big.setMoneda(big.getBigDecimal(resultados[i][4].toString())), resultados[i][5], big.setMoneda(big.getBigDecimal(resultados[i][6])),
                            big.setMoneda(big.getBigDecimal(resultados[i][7])), big.setMoneda(big.getBigDecimal(resultados[i][8])), resultados[i][9],
                            big.setMoneda(big.getBigDecimal(resultados[i][10])), resultados[i][11]});
                    }
                }
            } else if (tblSepares3.getSelectedColumn() == 2 && tblSepares3.getSelectedRow() == 4) {
                if (!lbTitulo.getText().equals("TRASLADOS ENTRE BODEGAS")) {
                    lbTitulo.setText("TRASLADOS ENTRE BODEGAS");
                    while (tblResultados.getRowCount() > 0) {
                        modelo.removeRow(0);
                    }

                    resultados = instancias.getSql().getProductosAjustesKardex1(txtIdSistema.getText(), "trasladosInternos");
                    for (int i = 0; i < resultados.length; i++) {
                        modelo.addRow(new Object[]{resultados[i][0], "", "", metodos.fecha(resultados[i][1].toString()), big.setMoneda(big.getBigDecimal(resultados[i][3].toString())),
                            resultados[i][2], big.setMoneda(big.getBigDecimal(resultados[i][4])), this.simbolo + " 0", this.simbolo + " 0", "0", big.setMoneda(big.getBigDecimal(resultados[i][4])), resultados[i][5]});
                    }

//                    resultados = sql.getProductosCongeladaKardex(txtIdSistema.getText(), "trasladosinternos", "", "123-22");
//                    for (int i = 0; i < resultados.length; i++) {
//                        modelo.addRow(new Object[]{resultados[i][0], resultados[i][1], resultados[i][2], metodos.fecha(resultados[i][3].toString()),
//                            big.setMoneda(big.getBigDecimal(resultados[i][4].toString())), resultados[i][5], big.setMoneda(big.getBigDecimal(resultados[i][6])),
//                            big.setMoneda(big.getBigDecimal(resultados[i][7])), big.setMoneda(big.getBigDecimal(resultados[i][8])), resultados[i][9],
//                            big.setMoneda(big.getBigDecimal(resultados[i][10])), resultados[i][11]});
//                    }
                }
            }
        }
    }//GEN-LAST:event_tblSepares3MouseClicked

    private void tblEntradas4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEntradas4MouseClicked
        if (!txtCodigo.getText().equals("")) {

            DefaultTableModel modelo = (DefaultTableModel) tblResultados.getModel();

            Object[][] resultados;

            if (tblEntradas4.getSelectedColumn() == 2 && tblEntradas4.getSelectedRow() == 0) {
                if (!lbTitulo.getText().equals("INVENTARIO INICIAL")) {
                    lbTitulo.setText("INVENTARIO INICIAL");

                    while (tblResultados.getRowCount() > 0) {
                        modelo.removeRow(0);
                    }

                    resultados = instancias.getSql().getProductosInvInicialKardex(txtIdSistema.getText());
                    for (int i = 0; i < resultados.length; i++) {
                        Object[][] encabezados = instancias.getSql().getProveedorIngreso(resultados[i][0].toString(), "BODEGA-4");
                        if (encabezados.length > 0) {
                            modelo.addRow(new Object[]{"INV-INICIAL", encabezados[0][0], encabezados[0][1], metodos.fecha(encabezados[0][2].toString()), big.setMoneda(big.getBigDecimal(resultados[i][2].toString())), resultados[i][1],
                                big.setMoneda(big.getBigDecimal(resultados[i][4])), big.setMoneda(big.getBigDecimal(resultados[i][3])), big.setMoneda(big.getBigDecimal(resultados[i][5])),
                                big.setNumero(big.getBigDecimal(resultados[i][6])), big.setMoneda(big.getBigDecimal(resultados[i][7])), encabezados[0][3]});
                        }
                    }
                }
            } else if (tblEntradas4.getSelectedColumn() == 2 && tblEntradas4.getSelectedRow() == 1) {
                if (!lbTitulo.getText().equals("COMPRAS")) {
                    lbTitulo.setText("COMPRAS");
                    while (tblResultados.getRowCount() > 0) {
                        modelo.removeRow(0);
                    }

                    resultados = instancias.getSql().getProductosComprasKardex(txtIdSistema.getText());
                    for (int i = 0; i < resultados.length; i++) {
                        Object[][] encabezados = instancias.getSql().getProveedorIngreso(resultados[i][0].toString(), "BODEGA-4");
                        if (encabezados.length > 0) {
                            modelo.addRow(new Object[]{resultados[i][0], encabezados[0][0], encabezados[0][1], metodos.fecha(encabezados[0][2].toString()), big.setMoneda(big.getBigDecimal(resultados[i][2].toString())), resultados[i][1],
                                big.setMoneda(big.getBigDecimal(resultados[i][4])), big.setMoneda(big.getBigDecimal(resultados[i][3])), big.setMoneda(big.getBigDecimal(resultados[i][5])),
                                big.setNumero(big.getBigDecimal(resultados[i][6])), big.setMoneda(big.getBigDecimal(resultados[i][7])), encabezados[0][3]});
                        }
                    }
                }
            } else if (tblEntradas4.getSelectedColumn() == 2 && tblEntradas4.getSelectedRow() == 2) {
                if (!lbTitulo.getText().equals("AJUSTES DE ENTRADA")) {
                    lbTitulo.setText("AJUSTES DE ENTRADA");

                    while (tblResultados.getRowCount() > 0) {
                        modelo.removeRow(0);
                    }

                    resultados = instancias.getSql().getProductosAjustesKardex(txtIdSistema.getText(), "ajustesEntrada", "BODEGA-4");
                    for (int i = 0; i < resultados.length; i++) {
                        modelo.addRow(new Object[]{resultados[i][0], "", "", metodos.fecha(resultados[i][1].toString()), big.setMoneda(big.getBigDecimal(resultados[i][3].toString())),
                            resultados[i][2], big.setMoneda(big.getBigDecimal(resultados[i][4])), this.simbolo + " 0", this.simbolo + " 0", "0", big.setMoneda(big.getBigDecimal(resultados[i][4])), resultados[i][5]});
                    }
                }
            } else if (tblEntradas4.getSelectedColumn() == 2 && tblEntradas4.getSelectedRow() == 3) {
                if (!lbTitulo.getText().equals("ANULADAS")) {
                    lbTitulo.setText("ANULADAS");

                    while (tblResultados.getRowCount() > 0) {
                        modelo.removeRow(0);
                    }

                    resultados = instancias.getSql().getProductosAnuladaKardex(txtIdSistema.getText(), "anulacion", "IdCliente", "BODEGA-4");
                    for (int i = 0; i < resultados.length; i++) {
                        modelo.addRow(new Object[]{resultados[i][0], resultados[i][1], resultados[i][2], metodos.fecha(resultados[i][3].toString()),
                            big.setMoneda(big.getBigDecimal(resultados[i][4].toString())), resultados[i][5], big.setMoneda(big.getBigDecimal(resultados[i][6])),
                            big.setMoneda(big.getBigDecimal(resultados[i][7])), big.setMoneda(big.getBigDecimal(resultados[i][8])), resultados[i][9],
                            big.setMoneda(big.getBigDecimal(resultados[i][10])), resultados[i][11]});
                    }
                }
            } else if (tblEntradas4.getSelectedColumn() == 2 && tblEntradas4.getSelectedRow() == 4) {
                if (!lbTitulo.getText().equals("NOTAS CREDITO")) {
                    lbTitulo.setText("NOTAS CREDITO");
                    while (tblResultados.getRowCount() > 0) {
                        modelo.removeRow(0);
                    }

                    resultados = instancias.getSql().getProductosNotaCreditoKardex(txtIdSistema.getText(), "BODEGA-4");
                    for (int i = 0; i < resultados.length; i++) {
                        modelo.addRow(new Object[]{resultados[i][0], resultados[i][1], resultados[i][2], metodos.fecha(resultados[i][3].toString()),
                            big.setMoneda(big.getBigDecimal(resultados[i][4].toString())), resultados[i][5], big.setMoneda(big.getBigDecimal(resultados[i][6])),
                            big.setMoneda(big.getBigDecimal(resultados[i][7])), big.setMoneda(big.getBigDecimal(resultados[i][8])), resultados[i][9],
                            big.setMoneda(big.getBigDecimal(resultados[i][10])), resultados[i][11]});
                    }
                }
            }
        }
    }//GEN-LAST:event_tblEntradas4MouseClicked

    private void tblSalidas4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSalidas4MouseClicked
        if (!txtCodigo.getText().equals("")) {

            DefaultTableModel modelo = (DefaultTableModel) tblResultados.getModel();

            Object[][] resultados;

            if (tblSalidas4.getSelectedColumn() == 2 && tblSalidas4.getSelectedRow() == 0) {
                if (!lbTitulo.getText().equals("VENTAS")) {
                    lbTitulo.setText("VENTAS");

                    while (tblResultados.getRowCount() > 0) {
                        modelo.removeRow(0);
                    }

                    resultados = instancias.getSql().getProductosTipoFacturacionKardex(txtIdSistema.getText(), "factura", "BODEGA-4");

                    for (int i = 0; i < resultados.length; i++) {
                        modelo.addRow(new Object[]{resultados[i][0], resultados[i][1], resultados[i][2], metodos.fecha(resultados[i][3].toString()),
                            big.setMoneda(big.getBigDecimal(resultados[i][4].toString())), resultados[i][5], big.setMoneda(big.getBigDecimal(resultados[i][6])),
                            big.setMoneda(big.getBigDecimal(resultados[i][7])), big.setMoneda(big.getBigDecimal(resultados[i][8])), resultados[i][9],
                            big.setMoneda(big.getBigDecimal(resultados[i][10])), resultados[i][11]});
                    }
                }
            } else if (tblSalidas4.getSelectedColumn() == 2 && tblSalidas4.getSelectedRow() == 1) {
                if (!lbTitulo.getText().equals("AJUSTES DE SALIDA")) {
                    lbTitulo.setText("AJUSTES DE SALIDA");
                    while (tblResultados.getRowCount() > 0) {
                        modelo.removeRow(0);
                    }

                    resultados = instancias.getSql().getProductosAjustesKardex(txtIdSistema.getText(), "ajustesSalida", "BODEGA-4");
                    for (int i = 0; i < resultados.length; i++) {
                        modelo.addRow(new Object[]{resultados[i][0], "", "", metodos.fecha(resultados[i][1].toString()), big.setMoneda(big.getBigDecimal(resultados[i][3].toString())),
                            resultados[i][2], big.setMoneda(big.getBigDecimal(resultados[i][4])), this.simbolo + " 0", this.simbolo + " 0", "0", big.setMoneda(big.getBigDecimal(resultados[i][4])), resultados[i][5]});
                    }
                }
            } else if (tblSalidas4.getSelectedColumn() == 2 && tblSalidas4.getSelectedRow() == 2) {
                if (!lbTitulo.getText().equals("COSTEOS")) {
                    lbTitulo.setText("COSTEOS");

                    while (tblResultados.getRowCount() > 0) {
                        modelo.removeRow(0);
                    }

                    resultados = instancias.getSql().getProductosTipoFacturacionKardex(txtIdSistema.getText(), "anulacion", "BODEGA-4");

                    for (int i = 0; i < resultados.length; i++) {
                        modelo.addRow(new Object[]{resultados[i][0], resultados[i][1], resultados[i][2], metodos.fecha(resultados[i][3].toString()),
                            big.setMoneda(big.getBigDecimal(resultados[i][4].toString())), resultados[i][5], big.setMoneda(big.getBigDecimal(resultados[i][6])),
                            big.setMoneda(big.getBigDecimal(resultados[i][7])), big.setMoneda(big.getBigDecimal(resultados[i][8])), resultados[i][9],
                            big.setMoneda(big.getBigDecimal(resultados[i][10])), resultados[i][11]});
                    }
                }
            } else if (tblSalidas4.getSelectedColumn() == 2 && tblSalidas4.getSelectedRow() == 3) {
                if (!lbTitulo.getText().equals("ARMADOS")) {
                    lbTitulo.setText("ARMADOS");

                    while (tblResultados.getRowCount() > 0) {
                        modelo.removeRow(0);
                    }

                    resultados = instancias.getSql().getProductosTipoFacturacionKardex(txtIdSistema.getText(), "anulacion", "BODEGA-4");

                    for (int i = 0; i < resultados.length; i++) {
                        modelo.addRow(new Object[]{resultados[i][0], resultados[i][1], resultados[i][2], metodos.fecha(resultados[i][3].toString()),
                            big.setMoneda(big.getBigDecimal(resultados[i][4].toString())), resultados[i][5], big.setMoneda(big.getBigDecimal(resultados[i][6])),
                            big.setMoneda(big.getBigDecimal(resultados[i][7])), big.setMoneda(big.getBigDecimal(resultados[i][8])), resultados[i][9],
                            big.setMoneda(big.getBigDecimal(resultados[i][10])), resultados[i][11]});
                    }
                }
            }
        }
    }//GEN-LAST:event_tblSalidas4MouseClicked

    private void tblSepares4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSepares4MouseClicked
        if (!txtCodigo.getText().equals("")) {

            DefaultTableModel modelo = (DefaultTableModel) tblResultados.getModel();

            Object[][] resultados;

            if (tblSepares4.getSelectedColumn() == 2 && tblSepares4.getSelectedRow() == 0) {
                if (!lbTitulo.getText().equals("PLAN SEPARES")) {
                    lbTitulo.setText("PLAN SEPARES");

                    while (tblResultados.getRowCount() > 0) {
                        modelo.removeRow(0);
                    }

                    resultados = instancias.getSql().getProductosTipoSepareKardex(txtIdSistema.getText(), "planSepare", "IdTercero", "BODEGA-2");

                    for (int i = 0; i < resultados.length; i++) {
                        modelo.addRow(new Object[]{resultados[i][0], resultados[i][1], resultados[i][2], metodos.fecha(resultados[i][3].toString()),
                            big.setMoneda(big.getBigDecimal(resultados[i][4].toString())), resultados[i][5], big.setMoneda(big.getBigDecimal(resultados[i][6])),
                            big.setMoneda(big.getBigDecimal(resultados[i][7])), big.setMoneda(big.getBigDecimal(resultados[i][8])), resultados[i][9],
                            big.setMoneda(big.getBigDecimal(resultados[i][10])), resultados[i][11]});
                    }
                }
            } else if (tblSepares2.getSelectedColumn() == 2 && tblSepares2.getSelectedRow() == 1) {
                if (!lbTitulo.getText().equals("ORDENES DE SERVICIO")) {
                    lbTitulo.setText("ORDENES DE SERVICIO");

                    while (tblResultados.getRowCount() > 0) {
                        modelo.removeRow(0);
                    }

                    resultados = instancias.getSql().getProductosTipoSepareKardex(txtIdSistema.getText(), "oServicio", "IdCliente", "BODEGA-2");

                    for (int i = 0; i < resultados.length; i++) {
                        modelo.addRow(new Object[]{resultados[i][0], resultados[i][1], resultados[i][2], metodos.fecha(resultados[i][3].toString()),
                            big.setMoneda(big.getBigDecimal(resultados[i][4].toString())), resultados[i][5], big.setMoneda(big.getBigDecimal(resultados[i][6])),
                            big.setMoneda(big.getBigDecimal(resultados[i][7])), big.setMoneda(big.getBigDecimal(resultados[i][8])), resultados[i][9],
                            big.setMoneda(big.getBigDecimal(resultados[i][10])), resultados[i][11]});
                    }
                }
            } else if (tblSepares2.getSelectedColumn() == 2 && tblSepares2.getSelectedRow() == 2) {
                if (!lbTitulo.getText().equals("PEDIDOS")) {
                    lbTitulo.setText("PEDIDOS");

                    while (tblResultados.getRowCount() > 0) {
                        modelo.removeRow(0);
                    }

                    resultados = instancias.getSql().getProductosTipoSepareKardex(txtIdSistema.getText(), "pedidos", "IdTercero", "BODEGA-2");

                    for (int i = 0; i < resultados.length; i++) {
                        modelo.addRow(new Object[]{resultados[i][0], resultados[i][1], resultados[i][2], metodos.fecha(resultados[i][3].toString()),
                            big.setMoneda(big.getBigDecimal(resultados[i][4].toString())), resultados[i][5], big.setMoneda(big.getBigDecimal(resultados[i][6])),
                            big.setMoneda(big.getBigDecimal(resultados[i][7])), big.setMoneda(big.getBigDecimal(resultados[i][8])), resultados[i][9],
                            big.setMoneda(big.getBigDecimal(resultados[i][10])), resultados[i][11]});
                    }
                }
            } else if (tblSepares2.getSelectedColumn() == 2 && tblSepares2.getSelectedRow() == 3) {
                if (!lbTitulo.getText().equals("CONGELADA")) {
                    lbTitulo.setText("CONGELADA");
                    while (tblResultados.getRowCount() > 0) {
                        modelo.removeRow(0);
                    }

                    resultados = instancias.getSql().getProductosCongeladaKardex(txtIdSistema.getText(), "congeladas", "IdTercero", "BODEGA-2");
                    for (int i = 0; i < resultados.length; i++) {
                        modelo.addRow(new Object[]{resultados[i][0], resultados[i][1], resultados[i][2], metodos.fecha(resultados[i][3].toString()),
                            big.setMoneda(big.getBigDecimal(resultados[i][4].toString())), resultados[i][5], big.setMoneda(big.getBigDecimal(resultados[i][6])),
                            big.setMoneda(big.getBigDecimal(resultados[i][7])), big.setMoneda(big.getBigDecimal(resultados[i][8])), resultados[i][9],
                            big.setMoneda(big.getBigDecimal(resultados[i][10])), resultados[i][11]});
                    }
                }
            } else if (tblSepares2.getSelectedColumn() == 2 && tblSepares2.getSelectedRow() == 4) {
                if (!lbTitulo.getText().equals("TRASLADOS ENTRE BODEGAS")) {
                    lbTitulo.setText("TRASLADOS ENTRE BODEGAS");
                    while (tblResultados.getRowCount() > 0) {
                        modelo.removeRow(0);
                    }

                    resultados = instancias.getSql().getProductosAjustesKardex1(txtIdSistema.getText(), "trasladosInternos");
                    for (int i = 0; i < resultados.length; i++) {
                        modelo.addRow(new Object[]{resultados[i][0], "", "", metodos.fecha(resultados[i][1].toString()), big.setMoneda(big.getBigDecimal(resultados[i][3].toString())),
                            resultados[i][2], big.setMoneda(big.getBigDecimal(resultados[i][4])), this.simbolo + " 0", this.simbolo + " 0", "0", big.setMoneda(big.getBigDecimal(resultados[i][4])), resultados[i][5]});
                    }

//                    resultados = sql.getProductosCongeladaKardex(txtIdSistema.getText(), "trasladosinternos", "", "123-22");
//                    for (int i = 0; i < resultados.length; i++) {
//                        modelo.addRow(new Object[]{resultados[i][0], resultados[i][1], resultados[i][2], metodos.fecha(resultados[i][3].toString()),
//                            big.setMoneda(big.getBigDecimal(resultados[i][4].toString())), resultados[i][5], big.setMoneda(big.getBigDecimal(resultados[i][6])),
//                            big.setMoneda(big.getBigDecimal(resultados[i][7])), big.setMoneda(big.getBigDecimal(resultados[i][8])), resultados[i][9],
//                            big.setMoneda(big.getBigDecimal(resultados[i][10])), resultados[i][11]});
//                    }
                }
            }
        }
    }//GEN-LAST:event_tblSepares4MouseClicked

    public void cargarProducto(String cod) {

        if (cod.equals("")) {
            ventanaProductos(cod);
        } else {
            ndProducto nodo = null;
            ndProducto nodo1 = null;
            ndProducto nodo2 = null;
            ndProducto nodo3 = null;
            ndProducto nodo4 = null;

            String CodigoProd = "";
            if (cod.equals("")) {
                CodigoProd = "";
            } else {
                Object[][] listado = instancias.getSql().getCodigosRelacionados(cod, " where codigo");
                if (listado.length > 0) {
                    cod = listado[0][0].toString();
                }

                nodo = instancias.getSql().getDatosProducto(cod, "bdProductos");
                if (instancias.getConfiguraciones().isInventarioBodegas()) {
                    nodo1 = instancias.getSql().getDatosProducto(cod, "bdProductosBodega1");
                    nodo2 = instancias.getSql().getDatosProducto(cod, "bdProductosBodega2");
                    nodo3 = instancias.getSql().getDatosProducto(cod, "bdProductosBodega3");
                    nodo4 = instancias.getSql().getDatosProducto(cod, "bdProductosBodega4");
                }

                if (nodo.getIdSistema() != null) {
                    CodigoProd = nodo.getIdSistema();
                }
            }

            if (!CodigoProd.equals("")) {

                txtDescripcion.setText(nodo.getDescripcion());
                txtIdSistema.setText(nodo.getIdSistema());

                txtFisicoInventario.setText(nodo.getFisicoInventario());
                txtInventario.setText(nodo.getInventario());
                if (txtFisicoInventario.getText().equals(",00")) {
                    txtFisicoInventario.setText("0");
                }
                if (txtInventario.getText().equals(",00")) {
                    txtInventario.setText("0");
                }

                if (instancias.getConfiguraciones().isInventarioBodegas()) {

                    if (nodo1.getCodigo() != null) {
                        txtFisicoInventario1.setText(nodo1.getFisicoInventario());
                        txtInventario1.setText(nodo1.getInventario());
                        if (txtFisicoInventario1.getText().equals(",00")) {
                            txtFisicoInventario1.setText("0");
                        }
                        if (txtInventario1.getText().equals(",00")) {
                            txtInventario1.setText("0");
                        }
                    }

                    if (nodo2.getCodigo() != null) {
                        txtFisicoInventario2.setText(nodo2.getFisicoInventario());
                        txtInventario2.setText(nodo2.getInventario());
                        if (txtFisicoInventario2.getText().equals(",00")) {
                            txtFisicoInventario2.setText("0");
                        }
                        if (txtInventario2.getText().equals(",00")) {
                            txtInventario2.setText("0");
                        }
                    }

                    if (nodo3.getCodigo() != null) {
                        txtFisicoInventario3.setText(nodo3.getFisicoInventario());
                        txtInventario3.setText(nodo3.getInventario());
                        if (txtFisicoInventario3.getText().equals(",00")) {
                            txtFisicoInventario3.setText("0");
                        }
                        if (txtInventario3.getText().equals(",00")) {
                            txtInventario3.setText("0");
                        }
                    }

                    if (nodo4.getCodigo() != null) {
                        txtFisicoInventario4.setText(nodo4.getFisicoInventario());
                        txtInventario4.setText(nodo4.getInventario());
                        if (txtFisicoInventario4.getText().equals(",00")) {
                            txtFisicoInventario4.setText("0");
                        }
                        if (txtInventario4.getText().equals(",00")) {
                            txtInventario4.setText("0");
                        }
                    }
                }

                txtCodigo.setText(nodo.getCodigo());

                DefaultTableModel modelo = (DefaultTableModel) tblPonderado.getModel();
                while (tblPonderado.getRowCount() > 0) {
                    modelo.removeRow(0);
                }

                ImageIcon fot = new ImageIcon(System.getProperty("user.dir") + "\\imagenes\\productos\\" + txtIdSistema.getText() + ".jpg");
                Icon icono = new ImageIcon(fot.getImage().getScaledInstance(lbFoto.getWidth(), lbFoto.getHeight(), Image.SCALE_DEFAULT));
                lbFoto.setIcon(icono);
                this.repaint();

                //INVENTARIO INICIAL
                Double invInicial = Double.parseDouble(instancias.getSql().getExistenciaProducto(CodigoProd, "totalizadoInvInicial").replace(",", "."));
//                Object[][] invInicial1 = sql.getTotalizadoInvInicial1(CodigoProd);
//                double total1;
//                try {
//                    total1 = Double.parseDouble(invInicial1[0][1].toString().replace(",", "."));
//                } catch (Exception e) {
//                    total1 = 0;
//                }
//                invInicial = invInicial + total1;
                //FIN INVENTARIO INICIAL

                Double compras, compras1, compras2, compras3, compras4;
                compras = Double.parseDouble(instancias.getSql().getExistenciaProducto(CodigoProd, "totalizadoCompras").replace(",", "."));
                compras1 = Double.parseDouble(instancias.getSql().getExistenciaProducto(CodigoProd, "totalizadoCompras1").replace(",", "."));
                compras2 = Double.parseDouble(instancias.getSql().getExistenciaProducto(CodigoProd, "totalizadoCompras2").replace(",", "."));
                compras3 = Double.parseDouble(instancias.getSql().getExistenciaProducto(CodigoProd, "totalizadoCompras3").replace(",", "."));
                compras4 = Double.parseDouble(instancias.getSql().getExistenciaProducto(CodigoProd, "totalizadoCompras4").replace(",", "."));

                Double ajusteEntrada, ajusteEntrada1, ajusteEntrada2, ajusteEntrada3, ajusteEntrada4;
                ajusteEntrada = Double.parseDouble(instancias.getSql().getExistenciaProducto(CodigoProd, "totalizadoAjusteEntrada").replace(",", "."));
                ajusteEntrada1 = Double.parseDouble(instancias.getSql().getExistenciaProducto(CodigoProd, "totalizadoAjusteEntrada1").replace(",", "."));
                ajusteEntrada2 = Double.parseDouble(instancias.getSql().getExistenciaProducto(CodigoProd, "totalizadoAjusteEntrada2").replace(",", "."));
                ajusteEntrada3 = Double.parseDouble(instancias.getSql().getExistenciaProducto(CodigoProd, "totalizadoAjusteEntrada3").replace(",", "."));
                ajusteEntrada4 = Double.parseDouble(instancias.getSql().getExistenciaProducto(CodigoProd, "totalizadoAjusteEntrada4").replace(",", "."));

                Double anuladas, anuladas1, anuladas2, anuladas3, anuladas4;
                anuladas = Double.parseDouble(instancias.getSql().getExistenciaProducto(CodigoProd, "totalizadoAnuladas").replace(",", "."));
                anuladas1 = Double.parseDouble(instancias.getSql().getExistenciaProducto(CodigoProd, "totalizadoAnuladas1").replace(",", "."));
                anuladas2 = Double.parseDouble(instancias.getSql().getExistenciaProducto(CodigoProd, "totalizadoAnuladas2").replace(",", "."));
                anuladas3 = Double.parseDouble(instancias.getSql().getExistenciaProducto(CodigoProd, "totalizadoAnuladas3").replace(",", "."));
                anuladas4 = Double.parseDouble(instancias.getSql().getExistenciaProducto(CodigoProd, "totalizadoAnuladas4").replace(",", "."));

                Double nc, nc1, nc2, nc3, nc4;
                nc = Double.parseDouble(instancias.getSql().getExistenciaProducto(CodigoProd, "totalizadoNotasCredito").replace(",", "."));
                nc1 = Double.parseDouble(instancias.getSql().getExistenciaProducto(CodigoProd, "totalizadoNotasCredito1").replace(",", "."));
                nc2 = Double.parseDouble(instancias.getSql().getExistenciaProducto(CodigoProd, "totalizadoNotasCredito2").replace(",", "."));
                nc3 = Double.parseDouble(instancias.getSql().getExistenciaProducto(CodigoProd, "totalizadoNotasCredito3").replace(",", "."));
                nc4 = Double.parseDouble(instancias.getSql().getExistenciaProducto(CodigoProd, "totalizadoNotasCredito4").replace(",", "."));

                Double ventas, ventas1, ventas2, ventas3, ventas4;
                ventas = Double.parseDouble(instancias.getSql().getExistenciaProducto(CodigoProd, "totalizadoVentas").replace(",", "."));
                ventas1 = Double.parseDouble(instancias.getSql().getExistenciaProducto(CodigoProd, "totalizadoVentas1").replace(",", "."));
                ventas2 = Double.parseDouble(instancias.getSql().getExistenciaProducto(CodigoProd, "totalizadoVentas2").replace(",", "."));
                ventas3 = Double.parseDouble(instancias.getSql().getExistenciaProducto(CodigoProd, "totalizadoVentas3").replace(",", "."));
                ventas4 = Double.parseDouble(instancias.getSql().getExistenciaProducto(CodigoProd, "totalizadoVentas4").replace(",", "."));

                Double ajusteSalida, ajusteSalida1, ajusteSalida2, ajusteSalida3, ajusteSalida4;
                ajusteSalida = Double.parseDouble(instancias.getSql().getExistenciaProducto(CodigoProd, "totalizadoAjusteSalida").replace(",", "."));
                ajusteSalida1 = Double.parseDouble(instancias.getSql().getExistenciaProducto(CodigoProd, "totalizadoAjusteSalida1").replace(",", "."));
                ajusteSalida2 = Double.parseDouble(instancias.getSql().getExistenciaProducto(CodigoProd, "totalizadoAjusteSalida2").replace(",", "."));
                ajusteSalida3 = Double.parseDouble(instancias.getSql().getExistenciaProducto(CodigoProd, "totalizadoAjusteSalida3").replace(",", "."));
                ajusteSalida4 = Double.parseDouble(instancias.getSql().getExistenciaProducto(CodigoProd, "totalizadoAjusteSalida4").replace(",", "."));

                Double armado, armado1, armado2, armado3, armado4;
                armado = Double.parseDouble(instancias.getSql().getExistenciaProducto(CodigoProd, "totalizadoArmado").replace(",", "."));
                armado1 = Double.parseDouble(instancias.getSql().getExistenciaProducto(CodigoProd, "totalizadoArmado1").replace(",", "."));
                armado2 = Double.parseDouble(instancias.getSql().getExistenciaProducto(CodigoProd, "totalizadoArmado2").replace(",", "."));
                armado3 = Double.parseDouble(instancias.getSql().getExistenciaProducto(CodigoProd, "totalizadoArmado3").replace(",", "."));
                armado4 = Double.parseDouble(instancias.getSql().getExistenciaProducto(CodigoProd, "totalizadoArmado4").replace(",", "."));

                Double costeo, costeo1, costeo2, costeo3, costeo4;
                costeo = Double.parseDouble(instancias.getSql().getExistenciaProducto(CodigoProd, "totalizadoCosteo").replace(",", "."));
                costeo1 = Double.parseDouble(instancias.getSql().getExistenciaProducto(CodigoProd, "totalizadoCosteo1").replace(",", "."));
                costeo2 = Double.parseDouble(instancias.getSql().getExistenciaProducto(CodigoProd, "totalizadoCosteo2").replace(",", "."));
                costeo3 = Double.parseDouble(instancias.getSql().getExistenciaProducto(CodigoProd, "totalizadoCosteo3").replace(",", "."));
                costeo4 = Double.parseDouble(instancias.getSql().getExistenciaProducto(CodigoProd, "totalizadoCosteo4").replace(",", "."));

                Double planSepare, planSepare1, planSepare2, planSepare3, planSepare4;
                planSepare = Double.parseDouble(instancias.getSql().getExistenciaProducto(CodigoProd, "totalizadoPlanSepares").replace(",", "."));
                planSepare1 = Double.parseDouble(instancias.getSql().getExistenciaProducto(CodigoProd, "totalizadoPlanSepares1").replace(",", "."));
                planSepare2 = Double.parseDouble(instancias.getSql().getExistenciaProducto(CodigoProd, "totalizadoPlanSepares2").replace(",", "."));
                planSepare3 = Double.parseDouble(instancias.getSql().getExistenciaProducto(CodigoProd, "totalizadoPlanSepares3").replace(",", "."));
                planSepare4 = Double.parseDouble(instancias.getSql().getExistenciaProducto(CodigoProd, "totalizadoPlanSepares4").replace(",", "."));

                Double ordenServicio, ordenServicio1, ordenServicio2, ordenServicio3, ordenServicio4;
                ordenServicio = Double.parseDouble(instancias.getSql().getExistenciaProducto(CodigoProd, "totalizadoOServicio").replace(",", "."));
                ordenServicio1 = Double.parseDouble(instancias.getSql().getExistenciaProducto(CodigoProd, "totalizadoOServicio1").replace(",", "."));
                ordenServicio2 = Double.parseDouble(instancias.getSql().getExistenciaProducto(CodigoProd, "totalizadoOServicio2").replace(",", "."));
                ordenServicio3 = Double.parseDouble(instancias.getSql().getExistenciaProducto(CodigoProd, "totalizadoOServicio3").replace(",", "."));
                ordenServicio4 = Double.parseDouble(instancias.getSql().getExistenciaProducto(CodigoProd, "totalizadoOServicio4").replace(",", "."));

                Double pedido, pedido1, pedido2, pedido3, pedido4;
                pedido = Double.parseDouble(instancias.getSql().getExistenciaProducto(CodigoProd, "totalizadoPedidos").replace(",", "."));
                pedido1 = Double.parseDouble(instancias.getSql().getExistenciaProducto(CodigoProd, "totalizadoPedidos1").replace(",", "."));
                pedido2 = Double.parseDouble(instancias.getSql().getExistenciaProducto(CodigoProd, "totalizadoPedidos2").replace(",", "."));
                pedido3 = Double.parseDouble(instancias.getSql().getExistenciaProducto(CodigoProd, "totalizadoPedidos3").replace(",", "."));
                pedido4 = Double.parseDouble(instancias.getSql().getExistenciaProducto(CodigoProd, "totalizadoPedidos4").replace(",", "."));

                Double congelada, congelada1, congelada2, congelada3, congelada4;
                congelada = Double.parseDouble(instancias.getSql().getExistenciaProducto(CodigoProd, "totalizadoCongelada").replace(",", "."));
                congelada1 = Double.parseDouble(instancias.getSql().getExistenciaProducto(CodigoProd, "totalizadoCongelada1").replace(",", "."));
                congelada2 = Double.parseDouble(instancias.getSql().getExistenciaProducto(CodigoProd, "totalizadoCongelada2").replace(",", "."));
                congelada3 = Double.parseDouble(instancias.getSql().getExistenciaProducto(CodigoProd, "totalizadoCongelada3").replace(",", "."));
                congelada4 = Double.parseDouble(instancias.getSql().getExistenciaProducto(CodigoProd, "totalizadoCongelada4").replace(",", "."));

                Double trasladoBod;
                trasladoBod = Double.parseDouble(instancias.getSql().getExistenciaProducto(CodigoProd, "totalizadoTrasladoBod").replace(",", "."));

                tblEntradas.setValueAt(invInicial, 0, 1);
                tblEntradas.setValueAt(compras, 1, 1);
                tblEntradas.setValueAt(ajusteEntrada, 2, 1);
                tblEntradas.setValueAt(anuladas, 3, 1);
                tblEntradas.setValueAt(nc, 4, 1);
                tblEntradas1.setValueAt("0.0", 0, 1);
                tblEntradas1.setValueAt(compras1, 1, 1);
                tblEntradas1.setValueAt(ajusteEntrada1, 2, 1);
                tblEntradas1.setValueAt(anuladas1, 3, 1);
                tblEntradas1.setValueAt(nc1, 4, 1);
                tblEntradas2.setValueAt("0.0", 0, 1);
                tblEntradas2.setValueAt(compras2, 1, 1);
                tblEntradas2.setValueAt(ajusteEntrada2, 2, 1);
                tblEntradas2.setValueAt(anuladas2, 3, 1);
                tblEntradas2.setValueAt(nc2, 4, 1);
                tblEntradas3.setValueAt("0.0", 0, 1);
                tblEntradas3.setValueAt(compras3, 1, 1);
                tblEntradas3.setValueAt(ajusteEntrada3, 2, 1);
                tblEntradas3.setValueAt(anuladas3, 3, 1);
                tblEntradas3.setValueAt(nc3, 4, 1);
                tblEntradas4.setValueAt("0.0", 0, 1);
                tblEntradas4.setValueAt(compras4, 1, 1);
                tblEntradas4.setValueAt(ajusteEntrada4, 2, 1);
                tblEntradas4.setValueAt(anuladas4, 3, 1);
                tblEntradas4.setValueAt(nc4, 4, 1);

                tblSalidas.setValueAt(ventas, 0, 1);
                tblSalidas.setValueAt(ajusteSalida, 1, 1);
                tblSalidas.setValueAt(costeo, 2, 1);
                tblSalidas.setValueAt(armado, 3, 1);
                tblSalidas1.setValueAt(ventas1, 0, 1);
                tblSalidas1.setValueAt(ajusteSalida1, 1, 1);
                tblSalidas1.setValueAt(costeo1, 2, 1);
                tblSalidas1.setValueAt(armado1, 3, 1);
                tblSalidas2.setValueAt(ventas2, 0, 1);
                tblSalidas2.setValueAt(ajusteSalida2, 1, 1);
                tblSalidas2.setValueAt(costeo2, 2, 1);
                tblSalidas2.setValueAt(armado2, 3, 1);
                tblSalidas3.setValueAt(ventas3, 0, 1);
                tblSalidas3.setValueAt(ajusteSalida3, 1, 1);
                tblSalidas3.setValueAt(costeo3, 2, 1);
                tblSalidas3.setValueAt(armado3, 3, 1);
                tblSalidas4.setValueAt(ventas4, 0, 1);
                tblSalidas4.setValueAt(ajusteSalida4, 1, 1);
                tblSalidas4.setValueAt(costeo4, 2, 1);
                tblSalidas4.setValueAt(armado4, 3, 1);

                tblSepares.setValueAt(planSepare, 0, 1);
                tblSepares.setValueAt(ordenServicio, 1, 1);
                tblSepares.setValueAt(pedido, 2, 1);
                tblSepares.setValueAt(congelada, 3, 1);
                tblSepares.setValueAt(trasladoBod, 4, 1);

                tblSepares1.setValueAt(planSepare1, 0, 1);
                tblSepares1.setValueAt(ordenServicio1, 1, 1);
                tblSepares1.setValueAt(pedido1, 2, 1);
                tblSepares1.setValueAt(congelada1, 3, 1);
                tblSepares1.setValueAt("0.0", 4, 1);
                tblSepares2.setValueAt(planSepare2, 0, 1);
                tblSepares2.setValueAt(ordenServicio2, 1, 1);
                tblSepares2.setValueAt(pedido2, 2, 1);
                tblSepares2.setValueAt(congelada2, 3, 1);
                tblSepares2.setValueAt("0.0", 4, 1);
                tblSepares3.setValueAt(planSepare3, 0, 1);
                tblSepares3.setValueAt(ordenServicio3, 1, 1);
                tblSepares3.setValueAt(pedido3, 2, 1);
                tblSepares3.setValueAt(congelada3, 3, 1);
                tblSepares3.setValueAt("0.0", 4, 1);
                tblSepares4.setValueAt(planSepare4, 0, 1);
                tblSepares4.setValueAt(ordenServicio4, 1, 1);
                tblSepares4.setValueAt(pedido4, 2, 1);
                tblSepares4.setValueAt(congelada4, 3, 1);
                tblSepares4.setValueAt("0.0", 4, 1);

                Object[][] ponderados = instancias.getSql().getPonderadosProd(CodigoProd);
                for (int i = ponderados.length - 1; i >= 0; i--) {
                    modelo.addRow(new Object[]{metodos.fecha(ponderados[i][0].toString()),
                        big.setMoneda(big.getMoneda(ponderados[i][1].toString().replace(".", "").replace(",", "."))), ponderados[i][2],
                        big.setMoneda(big.getMoneda(ponderados[i][3].toString().replace(".", ","))), ponderados[i][4],
                        big.setMoneda(big.getMoneda(ponderados[i][6].toString().replace(".", "").replace(",", "."))),
                        ponderados[i][5]});
                }

                lbFoto.requestFocus();
                return;
            }

            metodos.msgError(this, "El producto no existe");
        }
    }

    public void ventanaProductos(String codigo) {
        VistaBuscadorProductos buscar = new VistaBuscadorProductos(null, true, true, "", "productos1");
        buscar.setOpc("");
        buscar.setLocationRelativeTo(null);
        instancias.setBusProductos(buscar);
        instancias.setCampoActual(txtCodigo);
        txtCodigo.requestFocus();
        buscar.noEncontrado(codigo.replace("'", "//"));
        buscar.show();
    }

    private void limpiar(JPanel panel) {
        for (int x = 0; x < panel.getComponentCount(); x++) {
            if (panel.getComponent(x) instanceof JTextField) {
                JTextField textField = (JTextField) panel.getComponent(x);
                textField.setText("");
            }

            if (panel.getComponent(x) instanceof JCheckBox) {
                JCheckBox checkbox = (JCheckBox) panel.getComponent(x);
                checkbox.setSelected(false);
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnBuscar1;
    private javax.swing.JButton btnHistorial;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane30;
    private javax.swing.JScrollPane jScrollPane31;
    private javax.swing.JScrollPane jScrollPane32;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JLabel lbFoto;
    private javax.swing.JLabel lbNit;
    private javax.swing.JLabel lbNit1;
    private javax.swing.JLabel lbNit11;
    private javax.swing.JLabel lbNit12;
    private javax.swing.JLabel lbNit13;
    private javax.swing.JLabel lbNit14;
    private javax.swing.JLabel lbNit15;
    private javax.swing.JLabel lbNit16;
    private javax.swing.JLabel lbNit17;
    private javax.swing.JLabel lbNit18;
    private javax.swing.JLabel lbNit19;
    private javax.swing.JLabel lbNit20;
    private javax.swing.JLabel lbNit21;
    private javax.swing.JLabel lbNit22;
    private javax.swing.JLabel lbNit23;
    private javax.swing.JLabel lbNit24;
    private javax.swing.JLabel lbNit25;
    private javax.swing.JLabel lbNit26;
    private javax.swing.JLabel lbNit27;
    private javax.swing.JLabel lbNit48;
    private javax.swing.JLabel lbNit49;
    private javax.swing.JLabel lbNit50;
    private javax.swing.JLabel lbNit51;
    private javax.swing.JLabel lbNit52;
    private javax.swing.JLabel lbNit53;
    private javax.swing.JLabel lbNit54;
    private javax.swing.JLabel lbNit55;
    private javax.swing.JLabel lbNit56;
    private javax.swing.JLabel lbTitulo;
    private javax.swing.JPanel pnlFormulario;
    private javax.swing.JPanel pnlHistorialCostos;
    private javax.swing.JPanel pnlInvisible;
    private javax.swing.JScrollPane scrFormulario;
    private javax.swing.JTabbedPane tabPrincipal;
    private javax.swing.JTable tblEntradas;
    private javax.swing.JTable tblEntradas1;
    private javax.swing.JTable tblEntradas2;
    private javax.swing.JTable tblEntradas3;
    private javax.swing.JTable tblEntradas4;
    private javax.swing.JTable tblPonderado;
    private javax.swing.JTable tblResultados;
    private javax.swing.JTable tblSalidas;
    private javax.swing.JTable tblSalidas1;
    private javax.swing.JTable tblSalidas2;
    private javax.swing.JTable tblSalidas3;
    private javax.swing.JTable tblSalidas4;
    private javax.swing.JTable tblSepares;
    private javax.swing.JTable tblSepares1;
    private javax.swing.JTable tblSepares2;
    private javax.swing.JTable tblSepares3;
    private javax.swing.JTable tblSepares4;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtDocumento;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JLabel txtFisicoInventario;
    private javax.swing.JLabel txtFisicoInventario1;
    private javax.swing.JLabel txtFisicoInventario2;
    private javax.swing.JLabel txtFisicoInventario3;
    private javax.swing.JLabel txtFisicoInventario4;
    private javax.swing.JTextField txtIdSistema;
    private javax.swing.JLabel txtInventario;
    private javax.swing.JLabel txtInventario1;
    private javax.swing.JLabel txtInventario2;
    private javax.swing.JLabel txtInventario3;
    private javax.swing.JLabel txtInventario4;
    private javax.swing.JTextField txtNit;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
