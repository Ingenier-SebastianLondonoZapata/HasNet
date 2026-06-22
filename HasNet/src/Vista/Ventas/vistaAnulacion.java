package Vista.Ventas;

import Enums.TipoDocumento;
import Enums.enumBodegas;
import Modelo.Inventario.DetalleProducto;
import Modelo.Inventario.MovimientoInventario;
import clases.Cartera.ndCxc;
import clases.Instancias;
import Utilidades.CambiarColorTablaReimpresionYAnulacion;
import Utilidades.Constantes;
import clases.Ventas.ndCongelada;
import Modelo.Terceros.ModeloContacto;
import inventario.servicio.ServicioInventario;
import Utilidades.Utilidades;
import Vista.Productos.VistaInventarioInicial;
import clases.Ventas.ndFactura;
import clases.Ventas.ndPlanSepare;
import clases.big;
import clases.metodosGenerales;
import clases.productos.ndProducto;
import Vista.Solicitudes.vistaSolicitarPermisos;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class vistaAnulacion extends javax.swing.JInternalFrame {

    private String TIPO_FACTURACION_DE_FACTURA;

    DefaultTableModel modeloPro;
    DefaultTableModel modelo;
    metodosGenerales metodos = new metodosGenerales();
    Instancias instancias;
    TableRowSorter modeloOrdenado;
    ndFactura nodo;
    ndPlanSepare nodo1;
    ndCongelada nodo2;
    Object[] datos;
    DecimalFormat df = new DecimalFormat("#.00");

    //Barra de titulo
    private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension dimBarra = null;
    private int posicionFact = 1;

    //Manejo de lista de precios 
    ArrayList<String[]> precios = new ArrayList<>();
    ArrayList<String> codigos = new ArrayList<>();

    public void seleccionar() {
        tapPanel1.setSelectedIndex(0);
    }

    public vistaAnulacion() {
        initComponents();

        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
        dimBarra = Barra.getPreferredSize();
        Barra.setSize(0, 0);
        Barra.setPreferredSize(new Dimension(0, 0));
        setBorder(null);
        repaint();

        tapPanel1.setSelectedIndex(0);
        tapPanel1.setEnabledAt(1, false);
        tapPanel1.setEnabledAt(0, false);

        instancias = Instancias.getInstancias();
        consultarMaestros();
        txtVendedor.setText(this.instancias.getUsuario());

        pnlInvisible.setVisible(false);
        modelo = (DefaultTableModel) tblRegistros.getModel();
        btnActualizar.setText("VER DATOS");
        tblRegistros.setAutoCreateRowSorter(true);

        modeloOrdenado = new TableRowSorter<>(modelo);
        tblRegistros.setRowSorter(modeloOrdenado);

        filtrar.setSelected(true);

        tblRegistros.setDefaultRenderer(Object.class, new CambiarColorTablaReimpresionYAnulacion());
    }

    public void consultarMaestros() {
        datos = instancias.getSql().getDatosMaestra();
        if ((Boolean) datos[57]) {
            txtConseManual.setVisible(true);
            lbNit7.setVisible(true);
            tblRegistros.getColumnModel().getColumn(8).setMinWidth(100);
            tblRegistros.getColumnModel().getColumn(8).setPreferredWidth(100);
            tblRegistros.getColumnModel().getColumn(8).setMaxWidth(100);
        } else {
            txtConseManual.setVisible(false);
            lbNit7.setVisible(false);
            tblRegistros.getColumnModel().getColumn(8).setMinWidth(0);
            tblRegistros.getColumnModel().getColumn(8).setPreferredWidth(0);
            tblRegistros.getColumnModel().getColumn(8).setMaxWidth(0);
        }
    }

    public void cargarTabla() {
        tblRegistros.getColumnModel().getColumn(0).setMinWidth(0);
        tblRegistros.getColumnModel().getColumn(0).setPreferredWidth(0);
        tblRegistros.getColumnModel().getColumn(0).setMaxWidth(0);
        tblRegistros.getColumnModel().getColumn(1).setMinWidth(120);
        tblRegistros.getColumnModel().getColumn(1).setPreferredWidth(150);
        tblRegistros.getColumnModel().getColumn(1).setMaxWidth(180);
        posicionFact = 1;
        this.repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel8 = new javax.swing.JPanel();
        tapPanel1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblRegistros = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        lbNit2 = new javax.swing.JLabel();
        txtConsecutivo = new javax.swing.JTextField();
        lbNit3 = new javax.swing.JLabel();
        txtAnho = new javax.swing.JTextField();
        lbNit7 = new javax.swing.JLabel();
        txtConseManual = new javax.swing.JTextField();
        lbNit4 = new javax.swing.JLabel();
        txtMes = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        lbNit6 = new javax.swing.JLabel();
        cmbTipo = new javax.swing.JComboBox();
        filtrar = new javax.swing.JCheckBox();
        lbNit8 = new javax.swing.JLabel();
        dtInicio = new datechooser.beans.DateChooserCombo();
        lbNit9 = new javax.swing.JLabel();
        dtFinal = new datechooser.beans.DateChooserCombo();
        scrFormulario = new javax.swing.JScrollPane();
        pnlFormulario = new javax.swing.JPanel();
        scrProductos = new javax.swing.JScrollPane();
        tblProductos = new javax.swing.JTable();
        cmbListas = new javax.swing.JComboBox();
        jPanel1 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        lbNit = new javax.swing.JLabel();
        txtNit = new javax.swing.JTextField();
        lbNombre = new javax.swing.JLabel();
        lbDireccion = new javax.swing.JLabel();
        lbVendedor = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        txtVendedor = new javax.swing.JTextField();
        lbTelefono = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        btnAnular = new javax.swing.JButton();
        btnBuscTerceros1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        txtSubTotal = new javax.swing.JTextField();
        lbSubtotal = new javax.swing.JLabel();
        lbTotalDescuento = new javax.swing.JLabel();
        txtTotalDescuentos = new javax.swing.JTextField();
        txtIva = new javax.swing.JLabel();
        txtTotalIva = new javax.swing.JTextField();
        etiqTotal = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        lbFechaFactura = new javax.swing.JLabel();
        txtFechaFactura = new javax.swing.JLabel();
        lbVencimiento = new javax.swing.JLabel();
        txtVencimiento = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        txtDiasPlazo = new javax.swing.JTextField();
        txtDiasPlazo1 = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtObservaciones = new javax.swing.JTextArea();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        txtTargeta = new javax.swing.JTextField();
        lbTargeta = new javax.swing.JLabel();
        txtCheque = new javax.swing.JTextField();
        lbCheque = new javax.swing.JLabel();
        lbNC = new javax.swing.JLabel();
        txtNC = new javax.swing.JTextField();
        txtEfectivo = new javax.swing.JTextField();
        lbEfectivo = new javax.swing.JLabel();
        pnlInvisible = new javax.swing.JPanel();
        lbNoFactura = new javax.swing.JLabel();
        txtBodega = new javax.swing.JTextField();

        setTitle("Factura");

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        tblRegistros.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        tblRegistros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "FACTURA", "FACTURA", "FECHA", "NIT CLIENTE", "CLIENTE", "VENDEDOR", "TOTAL", "TERMINAL", "CONSE.MANUAL", "TIPO_FACTURAl"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblRegistros.setRowHeight(24);
        tblRegistros.getTableHeader().setReorderingAllowed(false);
        tblRegistros.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                tblRegistrosAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        tblRegistros.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblRegistrosMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblRegistrosMouseReleased(evt);
            }
        });
        tblRegistros.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tblRegistrosPropertyChange(evt);
            }
        });
        tblRegistros.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblRegistrosKeyReleased(evt);
            }
        });
        tblRegistros.addVetoableChangeListener(new java.beans.VetoableChangeListener() {
            public void vetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {
                tblRegistrosVetoableChange(evt);
            }
        });
        jScrollPane1.setViewportView(tblRegistros);
        if (tblRegistros.getColumnModel().getColumnCount() > 0) {
            tblRegistros.getColumnModel().getColumn(8).setMinWidth(100);
            tblRegistros.getColumnModel().getColumn(8).setPreferredWidth(100);
            tblRegistros.getColumnModel().getColumn(8).setMaxWidth(100);
            tblRegistros.getColumnModel().getColumn(9).setMinWidth(0);
            tblRegistros.getColumnModel().getColumn(9).setPreferredWidth(0);
            tblRegistros.getColumnModel().getColumn(9).setMaxWidth(0);
        }

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Filtros", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 0, 12))); // NOI18N

        lbNit2.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbNit2.setText("Factura:");

        txtConsecutivo.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtConsecutivo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtConsecutivoKeyReleased(evt);
            }
        });

        lbNit3.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbNit3.setText("CC/Nit:");

        txtAnho.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtAnho.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAnhoKeyReleased(evt);
            }
        });

        lbNit7.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbNit7.setText("Conse. manual:");

        txtConseManual.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtConseManual.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtConseManualKeyReleased(evt);
            }
        });

        lbNit4.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbNit4.setText("Nombre cliente:");

        txtMes.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtMes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMesActionPerformed(evt);
            }
        });
        txtMes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMesKeyReleased(evt);
            }
        });

        btnBuscar.setBackground(new java.awt.Color(247, 220, 111));
        btnBuscar.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar.png"))); // NOI18N
        btnBuscar.setText("VER");
        btnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnBuscar.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnActualizar.setBackground(new java.awt.Color(46, 204, 113));
        btnActualizar.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/actualizar.png"))); // NOI18N
        btnActualizar.setText("ACTUALIZAR");
        btnActualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnActualizar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnActualizar.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        lbNit6.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit6.setText("Tipo:");

        cmbTipo.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        cmbTipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "FACTURA", "PLAN SEPARE", "NOTA DEBITO", "PLANTILLAS" }));
        cmbTipo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbTipoItemStateChanged(evt);
            }
        });

        filtrar.setBackground(new java.awt.Color(255, 255, 255));
        filtrar.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        filtrar.setText("¿Filtrar por fecha?");
        filtrar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                filtrarItemStateChanged(evt);
            }
        });

        lbNit8.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit8.setText("Fecha inicial:");

        dtInicio.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 12));
        dtInicio.addCommitListener(new datechooser.events.CommitListener() {
            public void onCommit(datechooser.events.CommitEvent evt) {
                dtInicioOnCommit(evt);
            }
        });

        lbNit9.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit9.setText("Fecha final:");

        dtFinal.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 12));
        dtFinal.addCommitListener(new datechooser.events.CommitListener() {
            public void onCommit(datechooser.events.CommitEvent evt) {
                dtFinalOnCommit(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbNit3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbNit6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbNit2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmbTipo, 0, 145, Short.MAX_VALUE)
                    .addComponent(txtAnho, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtConsecutivo))
                .addGap(30, 30, 30)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(filtrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbNit4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                    .addComponent(lbNit7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(lbNit8)
                        .addGap(3, 3, 3)
                        .addComponent(dtInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(lbNit9)
                        .addGap(3, 3, 3)
                        .addComponent(dtFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtConseManual, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMes))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 115, Short.MAX_VALUE)
                .addComponent(btnActualizar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBuscar)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbTipo)
                            .addComponent(lbNit6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(filtrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbNit8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dtInicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbNit9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dtFinal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtConsecutivo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                            .addComponent(lbNit4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtMes, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbNit2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbNit3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtAnho, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                            .addComponent(lbNit7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtConseManual))))
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(11, 11, 11))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13))
        );

        tapPanel1.addTab("Lista de facturas", jPanel2);

        scrFormulario.setBorder(null);

        pnlFormulario.setBackground(new java.awt.Color(255, 255, 255));

        tblProductos.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        tblProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Descripción", "Valor/Unit", "Cantidad", "Subtotal", "Descuento", "%", "Iva", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblProductos.setRowHeight(24);
        tblProductos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblProductos.getTableHeader().setReorderingAllowed(false);
        tblProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductosMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblProductosMouseReleased(evt);
            }
        });
        tblProductos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblProductosKeyReleased(evt);
            }
        });
        scrProductos.setViewportView(tblProductos);

        cmbListas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "L1", "L2", "L3", "L4" }));
        cmbListas.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbListasItemStateChanged(evt);
            }
        });
        cmbListas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbListasActionPerformed(evt);
            }
        });

        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel1MouseEntered(evt);
            }
        });
        jPanel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jPanel1MouseMoved(evt);
            }
        });
        jPanel1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jPanel1FocusGained(evt);
            }
        });
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Información del cliente", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 0, 12))); // NOI18N

        lbNit.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit.setText("CC/Nit:");

        txtNit.setEditable(false);
        txtNit.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtNit.setName("CC/NIT"); // NOI18N
        txtNit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNitKeyReleased(evt);
            }
        });

        lbNombre.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbNombre.setText("Nombre:");

        lbDireccion.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion.setText("Dirección:");

        lbVendedor.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbVendedor.setText("Vendedor:");

        txtNombre.setEditable(false);
        txtNombre.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtNombre.setName("Nombre"); // NOI18N

        txtDireccion.setEditable(false);
        txtDireccion.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtDireccion.setName("Direccion"); // NOI18N

        txtVendedor.setEditable(false);
        txtVendedor.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtVendedor.setName("Vendedor"); // NOI18N

        lbTelefono.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTelefono.setText("Telefono:");

        txtTelefono.setEditable(false);
        txtTelefono.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtTelefono.setName("Telefono"); // NOI18N

        btnAnular.setBackground(new java.awt.Color(241, 148, 138));
        btnAnular.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnAnular.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/borrar2.png"))); // NOI18N
        btnAnular.setText("ANULAR ");
        btnAnular.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAnular.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnAnular.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnAnular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnularActionPerformed(evt);
            }
        });

        btnBuscTerceros1.setBackground(new java.awt.Color(102, 204, 255));
        btnBuscTerceros1.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnBuscTerceros1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/anterior.png"))); // NOI18N
        btnBuscTerceros1.setText("VOLVER ");
        btnBuscTerceros1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscTerceros1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnBuscTerceros1.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnBuscTerceros1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscTerceros1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnBuscTerceros1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAnular, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lbNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbDireccion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbVendedor, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbNit, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtDireccion, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNombre, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtVendedor)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(txtNit, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbTelefono)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(5, 5, 5))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbNit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNit)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbNombre)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtDireccion)
                    .addComponent(lbDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbVendedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAnular, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscTerceros1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Información de la factura", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 0, 14))); // NOI18N

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        txtSubTotal.setEditable(false);
        txtSubTotal.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtSubTotal.setText("0");

        lbSubtotal.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbSubtotal.setText("Subtotal sin IVA");

        lbTotalDescuento.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTotalDescuento.setText("Total descuentos");

        txtTotalDescuentos.setEditable(false);
        txtTotalDescuentos.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtTotalDescuentos.setText("0");

        txtIva.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        txtIva.setText("IVA");

        txtTotalIva.setEditable(false);
        txtTotalIva.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtTotalIva.setText("0");

        etiqTotal.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        etiqTotal.setText("Total");

        txtTotal.setEditable(false);
        txtTotal.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtTotal.setText("0");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtIva, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbTotalDescuento, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbSubtotal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSubTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                            .addComponent(txtTotalDescuentos, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtTotalIva)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(etiqTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotal)))
                .addGap(5, 5, 5))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbSubtotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTotalDescuentos)
                    .addComponent(lbTotalDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTotalIva)
                    .addComponent(txtIva, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiqTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        lbFechaFactura.setBackground(new java.awt.Color(204, 204, 204));
        lbFechaFactura.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        lbFechaFactura.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbFechaFactura.setText("Fecha factura");
        lbFechaFactura.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lbFechaFactura.setOpaque(true);

        txtFechaFactura.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtFechaFactura.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtFechaFactura.setText(" ");
        txtFechaFactura.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lbVencimiento.setBackground(new java.awt.Color(204, 204, 204));
        lbVencimiento.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        lbVencimiento.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbVencimiento.setText("Vencimiento");
        lbVencimiento.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lbVencimiento.setOpaque(true);

        txtVencimiento.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtVencimiento.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtVencimiento.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtDiasPlazo.setEditable(false);
        txtDiasPlazo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtDiasPlazo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDiasPlazo.setName("Plazo"); // NOI18N
        txtDiasPlazo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDiasPlazoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDiasPlazoKeyTyped(evt);
            }
        });

        txtDiasPlazo1.setEditable(false);
        txtDiasPlazo1.setBackground(new java.awt.Color(255, 255, 255));
        txtDiasPlazo1.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        txtDiasPlazo1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDiasPlazo1.setText(" Dias de plazo");
        txtDiasPlazo1.setBorder(null);
        txtDiasPlazo1.setName("Plazo"); // NOI18N
        txtDiasPlazo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDiasPlazo1ActionPerformed(evt);
            }
        });
        txtDiasPlazo1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDiasPlazo1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDiasPlazo1KeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(53, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtFechaFactura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbFechaFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtVencimiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbVencimiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addComponent(txtDiasPlazo1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtDiasPlazo, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDiasPlazo1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDiasPlazo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbVencimiento)
                    .addComponent(lbFechaFactura))
                .addGap(0, 0, 0)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFechaFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtVencimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Observaciones", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 0, 12))); // NOI18N

        txtObservaciones.setColumns(20);
        txtObservaciones.setLineWrap(true);
        txtObservaciones.setRows(5);
        txtObservaciones.setText("\n");
        txtObservaciones.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtObservaciones.setEnabled(false);
        jScrollPane2.setViewportView(txtObservaciones);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 785, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        txtTargeta.setEditable(false);
        txtTargeta.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtTargeta.setText("0");
        txtTargeta.setName("Targeta"); // NOI18N
        txtTargeta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTargetaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTargetaKeyTyped(evt);
            }
        });

        lbTargeta.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTargeta.setText("Tarjeta:");

        txtCheque.setEditable(false);
        txtCheque.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtCheque.setText("0");
        txtCheque.setName("Cheque"); // NOI18N
        txtCheque.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtChequeKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtChequeKeyTyped(evt);
            }
        });

        lbCheque.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbCheque.setText("Cheque:");

        lbNC.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbNC.setText("N.C:");

        txtNC.setEditable(false);
        txtNC.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtNC.setText("0");
        txtNC.setName("N.C"); // NOI18N
        txtNC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNCKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNCKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNCKeyTyped(evt);
            }
        });

        txtEfectivo.setEditable(false);
        txtEfectivo.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtEfectivo.setText("0");
        txtEfectivo.setName("Efectivo"); // NOI18N

        lbEfectivo.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbEfectivo.setText("Efectivo:");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbNC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbEfectivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNC, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                            .addComponent(txtEfectivo)))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbTargeta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbCheque, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCheque)
                            .addComponent(txtTargeta))))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbNC, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNC, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbEfectivo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtEfectivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(3, 3, 3)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCheque, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCheque))
                .addGap(3, 3, 3)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTargeta, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTargeta))
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(46, Short.MAX_VALUE)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(54, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout pnlFormularioLayout = new javax.swing.GroupLayout(pnlFormulario);
        pnlFormulario.setLayout(pnlFormularioLayout);
        pnlFormularioLayout.setHorizontalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrProductos)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmbListas, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
        );
        pnlFormularioLayout.setVerticalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlFormularioLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(99, 99, 99)
                        .addComponent(cmbListas, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlFormularioLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlFormularioLayout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 3, Short.MAX_VALUE))
                    .addComponent(scrProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        scrFormulario.setViewportView(pnlFormulario);

        tapPanel1.addTab("Vista previa", scrFormulario);

        lbNoFactura.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbNoFactura.setForeground(new java.awt.Color(255, 0, 0));
        lbNoFactura.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNoFactura.setText("3");
        lbNoFactura.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtBodega.setEditable(false);
        txtBodega.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtBodega.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtBodega.setName("CC/NIT"); // NOI18N
        txtBodega.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBodegaKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout pnlInvisibleLayout = new javax.swing.GroupLayout(pnlInvisible);
        pnlInvisible.setLayout(pnlInvisibleLayout);
        pnlInvisibleLayout.setHorizontalGroup(
            pnlInvisibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInvisibleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbNoFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtBodega, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlInvisibleLayout.setVerticalGroup(
            pnlInvisibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInvisibleLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(pnlInvisibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNoFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBodega, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(tapPanel1)
                        .addContainerGap())
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(pnlInvisible, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(10, 10, 10))))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tapPanel1)
                .addGap(5, 5, 5)
                .addComponent(pnlInvisible, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(4, 4, 4))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblProductosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblProductosKeyReleased

    }//GEN-LAST:event_tblProductosKeyReleased

    private void txtNitKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNitKeyReleased

    }//GEN-LAST:event_txtNitKeyReleased

    private void tblProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductosMouseClicked

    }//GEN-LAST:event_tblProductosMouseClicked

    private void txtNCKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNCKeyReleased


    }//GEN-LAST:event_txtNCKeyReleased

    private void txtChequeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtChequeKeyReleased

    }//GEN-LAST:event_txtChequeKeyReleased

    private void txtTargetaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTargetaKeyReleased

    }//GEN-LAST:event_txtTargetaKeyReleased

    private void txtNCKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNCKeyPressed

    }//GEN-LAST:event_txtNCKeyPressed

    private void txtNCKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNCKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtNCKeyTyped

    private void txtChequeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtChequeKeyTyped
        if (!txtDiasPlazo.getText().equals("") && !txtDiasPlazo.getText().equals("0")) {
            evt.consume();
        }

        metodos.soloNum(evt);
    }//GEN-LAST:event_txtChequeKeyTyped

    private void txtTargetaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTargetaKeyTyped
        if (!txtDiasPlazo.getText().equals("") && !txtDiasPlazo.getText().equals("0")) {
            evt.consume();
        }

        metodos.soloNum(evt);
    }//GEN-LAST:event_txtTargetaKeyTyped

    private void tblProductosMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductosMouseReleased

    }//GEN-LAST:event_tblProductosMouseReleased

    private void jPanel1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPanel1FocusGained

    }//GEN-LAST:event_jPanel1FocusGained

    private void jPanel1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseMoved

    }//GEN-LAST:event_jPanel1MouseMoved

    private void jPanel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseEntered

    }//GEN-LAST:event_jPanel1MouseEntered

    private void txtDiasPlazo1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiasPlazo1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiasPlazo1KeyReleased

    private void txtDiasPlazo1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiasPlazo1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiasPlazo1KeyTyped

    private void txtDiasPlazoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiasPlazoKeyReleased

    }//GEN-LAST:event_txtDiasPlazoKeyReleased

    private void txtDiasPlazoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiasPlazoKeyTyped

    }//GEN-LAST:event_txtDiasPlazoKeyTyped

    private void txtDiasPlazo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiasPlazo1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiasPlazo1ActionPerformed

    private void cmbListasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbListasActionPerformed


    }//GEN-LAST:event_cmbListasActionPerformed

    private void cmbListasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbListasItemStateChanged

    }//GEN-LAST:event_cmbListasItemStateChanged

    private void tblRegistrosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblRegistrosMouseClicked
        if (cmbTipo.getSelectedItem().equals("FACTURA")) {
            TIPO_FACTURACION_DE_FACTURA = tblRegistros.getValueAt(tblRegistros.getSelectedRow(), 9).toString();
            txtConsecutivo.setText(((String) tblRegistros.getValueAt(tblRegistros.getSelectedRow(), posicionFact)).replaceAll("FACT-", ""));
        } else if (cmbTipo.getSelectedItem().equals("PLAN SEPARE")) {
            txtConsecutivo.setText(((String) tblRegistros.getValueAt(tblRegistros.getSelectedRow(), posicionFact)).replaceAll("SEPARE-", ""));
        } else if (cmbTipo.getSelectedItem().equals("PLANTILLAS")) {
            txtConsecutivo.setText(((String) tblRegistros.getValueAt(tblRegistros.getSelectedRow(), posicionFact)).replaceAll("CCOBRO-", ""));
        } else {
            txtConsecutivo.setText(((String) tblRegistros.getValueAt(tblRegistros.getSelectedRow(), posicionFact)).replaceAll("ND-", ""));
        }

        if (evt.getClickCount() == 2) {
            btnBuscarActionPerformed(null);
        }
    }//GEN-LAST:event_tblRegistrosMouseClicked

    private void btnAnularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnularActionPerformed
        if (!instancias.getUsuario().equals("ADMIN")) {
            vistaSolicitarPermisos permisos = new vistaSolicitarPermisos(null, true,
                    "ANULACIÓN FACTURA", "ANULACION", "FACT-" + lbNoFactura.getText(), "anulacion");
            permisos.setLocationRelativeTo(null);
            permisos.setVisible(true);
            return;
        } else {
            if (metodos.msgPregunta(this, "¿Desea continuar?") != 0) {
                return;
            } else {
                String nota = "";
                nota = metodos.msgIngresar(null, "NOTA: ");

                if (nota == null) {
                    return;
                }

                if (nota.equals("")) {
                    return;
                }

                anularFactura(nota);
            }
        }
    }//GEN-LAST:event_btnAnularActionPerformed

    private void btnBuscTerceros1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscTerceros1ActionPerformed
        tapPanel1.setSelectedIndex(0);
        instancias.getMenu().cambiarTitulo("ANULACIÓN DE FACTURAS");
        btnAnular.setVisible(true);
    }//GEN-LAST:event_btnBuscTerceros1ActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        if (cmbTipo.getSelectedItem().equals("FACTURA") || cmbTipo.getSelectedItem().equals("NOTA DEBITO")) {

            if (TIPO_FACTURACION_DE_FACTURA != null && (Constantes.esFacturacionElectronica(TIPO_FACTURACION_DE_FACTURA))) {
                btnAnular.setVisible(false);
            } else {
                btnAnular.setVisible(true);
            }

            if (cmbTipo.getSelectedItem().equals("FACTURA")) {
                if (instancias.getSql().getRegistrosAbonos("FACT-" + txtConsecutivo.getText()).length > 0) {
                    metodos.msgAdvertencia(this, "Esta factura tiene abonos y no puede ser anulada");
                    return;
                }
            } else {
                if (instancias.getSql().getRegistrosAbonos("ND-" + txtConsecutivo.getText()).length > 0) {
                    metodos.msgAdvertencia(this, "Esta nota debito tiene abonos y no puede ser anulada");
                    return;
                }
            }

            if (cmbTipo.getSelectedItem().equals("FACTURA")) {
                nodo = instancias.getSql().getDatosFactura("FACT-" + txtConsecutivo.getText());
                if (nodo.isAnulada()) {
                    metodos.msgError(this, "Esta factura ya esta anulada");
                    return;
                }
            } else {
                nodo = instancias.getSql().getDatosFactura("ND-" + txtConsecutivo.getText());
                if (nodo.isAnulada()) {
                    metodos.msgError(this, "Esta nota debito ya esta anulada");
                    return;
                }
            }

            if (cmbTipo.getSelectedItem().equals("FACTURA")) {
                tblProductos.setModel(instancias.getSql().getRegistrosVentas("FACT-" + txtConsecutivo.getText(), "factura"));
            } else {
                tblProductos.setModel(instancias.getSql().getRegistrosVentas("ND-" + txtConsecutivo.getText(), "factura"));
            }

            tblProductos.getColumnModel().getColumn(14).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(14).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(14).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(13).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(13).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(13).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(12).setMinWidth(80);
            tblProductos.getColumnModel().getColumn(12).setPreferredWidth(100);
            tblProductos.getColumnModel().getColumn(12).setMaxWidth(140);
            tblProductos.getColumnModel().getColumn(11).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(11).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(11).setMaxWidth(0);

        } else if (cmbTipo.getSelectedItem().equals("PLAN SEPARE")) {
            btnAnular.setVisible(true);
            if (instancias.getSql().getRegistrosAbonos("SEPARE-" + txtConsecutivo.getText()).length > 0) {
                metodos.msgError(this, "Tiene abonos y no puede ser anulado");
                return;
            }

            nodo1 = instancias.getSql().getDatosPlanSepare("SEPARE-" + txtConsecutivo.getText());

            if (nodo1.isAnulada()) {
                metodos.msgError(this, "Este plan separe ya se encuentra anulado");
                return;
            }

            tblProductos.setModel(instancias.getSql().getRegistrosVentasSepares("SEPARE-" + txtConsecutivo.getText(), "idFactura"));
        } else if (cmbTipo.getSelectedItem().equals("PLANTILLAS")) {
            btnAnular.setVisible(true);
            nodo2 = instancias.getSql().getDatosCuentaCobro("CCOBRO-" + txtConsecutivo.getText());

            if (nodo2.isAnulada()) {
                metodos.msgError(this, "Esta plantilla ya se encuentra anulada");
                return;
            }

            tblProductos.setModel(instancias.getSql().getRegistrosPlantillas1("CCOBRO-" + txtConsecutivo.getText()));
        }

        if (!cmbTipo.getSelectedItem().equals("PLANTILLAS")) {
            tblProductos.getColumnModel().getColumn(11).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(11).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(11).setMaxWidth(0);
        }

        tblProductos.getColumnModel().getColumn(10).setMinWidth(0);
        tblProductos.getColumnModel().getColumn(10).setPreferredWidth(0);
        tblProductos.getColumnModel().getColumn(10).setMaxWidth(0);
        tblProductos.getColumnModel().getColumn(9).setMinWidth(0);
        tblProductos.getColumnModel().getColumn(9).setPreferredWidth(0);
        tblProductos.getColumnModel().getColumn(9).setMaxWidth(0);
        tblProductos.getColumnModel().getColumn(8).setMinWidth(80);
        tblProductos.getColumnModel().getColumn(8).setPreferredWidth(100);
        tblProductos.getColumnModel().getColumn(8).setMaxWidth(140);
        tblProductos.getColumnModel().getColumn(7).setMinWidth(80);
        tblProductos.getColumnModel().getColumn(7).setPreferredWidth(100);
        tblProductos.getColumnModel().getColumn(7).setMaxWidth(140);
        tblProductos.getColumnModel().getColumn(6).setMinWidth(20);
        tblProductos.getColumnModel().getColumn(6).setPreferredWidth(50);
        tblProductos.getColumnModel().getColumn(6).setMaxWidth(70);
        tblProductos.getColumnModel().getColumn(5).setMinWidth(80);
        tblProductos.getColumnModel().getColumn(5).setPreferredWidth(100);
        tblProductos.getColumnModel().getColumn(5).setMaxWidth(140);
        tblProductos.getColumnModel().getColumn(4).setMinWidth(80);
        tblProductos.getColumnModel().getColumn(4).setPreferredWidth(100);
        tblProductos.getColumnModel().getColumn(4).setMaxWidth(140);
        tblProductos.getColumnModel().getColumn(3).setMinWidth(20);
        tblProductos.getColumnModel().getColumn(3).setPreferredWidth(60);
        tblProductos.getColumnModel().getColumn(3).setMaxWidth(80);
        tblProductos.getColumnModel().getColumn(2).setMinWidth(80);
        tblProductos.getColumnModel().getColumn(2).setPreferredWidth(100);
        tblProductos.getColumnModel().getColumn(2).setMaxWidth(140);
        tblProductos.getColumnModel().getColumn(0).setMinWidth(80);
        tblProductos.getColumnModel().getColumn(0).setPreferredWidth(120);
        tblProductos.getColumnModel().getColumn(0).setMaxWidth(250);

        if (instancias.getConfiguraciones().getTipoImpresion().equals("Sin-Codigo")) {
            tblProductos.getColumnModel().getColumn(0).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(0).setMaxWidth(0);
        }

        for (int i = 0; i < tblProductos.getRowCount(); i++) {
            tblProductos.setValueAt(big.setMoneda(big.getBigDecimal((String) tblProductos.getValueAt(i, 2))), i, 2);
            tblProductos.setValueAt(big.setMoneda(big.getBigDecimal((String) tblProductos.getValueAt(i, 4))), i, 4);
            tblProductos.setValueAt(big.setMoneda(big.getBigDecimal((String) tblProductos.getValueAt(i, 5))), i, 5);
            tblProductos.setValueAt(big.setMoneda(big.getBigDecimal((String) tblProductos.getValueAt(i, 7))), i, 7);
            tblProductos.setValueAt(big.setMoneda(big.getBigDecimal((String) tblProductos.getValueAt(i, 8))), i, 8);

            if (cmbTipo.getSelectedItem().equals("FACTURA") || cmbTipo.getSelectedItem().equals("NOTA DEBITO")) {
                tblProductos.setValueAt(big.setMoneda(big.getBigDecimal((String) tblProductos.getValueAt(i, 12))), i, 12);
            }
        }

        if (cmbTipo.getSelectedItem().equals("FACTURA") || cmbTipo.getSelectedItem().equals("NOTA DEBITO")) {
            ModeloContacto nodoCliente = instancias.getSql().getDatosTercero(nodo.getCliente());

            txtNit.setText(nodoCliente.getId());
            txtVendedor.setText(nodo.getVendedor());
            txtFechaFactura.setText(metodos.fecha(nodo.getFechaFactura()));
            txtVencimiento.setText(metodos.fecha(nodo.getFechaVencimiento()));
            txtSubTotal.setText(big.setMoneda(big.getBigDecimal(nodo.getSubtotalGeneral())));
            txtTotalDescuentos.setText(big.setMoneda(big.getBigDecimal(nodo.getDescuentoGeneral())));
            txtTotalIva.setText(big.setMoneda(big.getBigDecimal(nodo.getIvaGeneral())));
            txtTotal.setText(big.setMoneda(big.getBigDecimal(nodo.getTotalGeneral())));
            txtEfectivo.setText(big.setMoneda(big.getBigDecimal(nodo.getEfectivoGeneral())));
            txtNC.setText(big.setMoneda(big.getBigDecimal(nodo.getNcGeneral())));

            if (nodo.getBodega() != null) {
                if (nodo.getBodega().equals("")) {
                    txtBodega.setText("123-22");
                } else {
                    txtBodega.setText(nodo.getBodega());
                }
            } else {
                txtBodega.setText("123-22");
            }

            txtCheque.setText(big.setMoneda(big.getBigDecimal(nodo.getChequeGeneral())));
            txtTargeta.setText(big.setMoneda(big.getBigDecimal(nodo.getTargetaGeneral())));
            txtObservaciones.setText(nodo.getObservacion());
            lbNoFactura.setText(txtConsecutivo.getText());

            if (nodoCliente.getNombre() != null) {
                txtNombre.setText(nodoCliente.getNombre());
                txtDireccion.setText(nodoCliente.getDireccion());
                txtTelefono.setText(nodoCliente.getTelefono());
            }

            try {
                ndCxc nodoCxc = instancias.getSql().getDatosCxc(nodo.getIdFactura());
                txtDiasPlazo.setText("" + nodoCxc.getPlazo());
            } catch (Exception ex) {
                System.out.println("Error, no es factura a credito");
            }

            tapPanel1.setSelectedIndex(1);
        } else if (cmbTipo.getSelectedItem().equals("PLAN SEPARE")) {
            ModeloContacto nodoCliente = instancias.getSql().getDatosTercero(nodo1.getCliente());

            txtNit.setText(nodoCliente.getId());

            txtVendedor.setText(nodo1.getVendedor());
            txtFechaFactura.setText(metodos.fecha(nodo1.getFechaFactura()));
            txtVencimiento.setText(metodos.fecha(nodo1.getFechaVencimiento()));

            txtSubTotal.setText(big.setMoneda(big.getBigDecimal(nodo1.getSubtotalGeneral())));
            txtTotalDescuentos.setText(big.setMoneda(big.getBigDecimal(nodo1.getDescuentoGeneral())));
            txtTotalIva.setText(big.setMoneda(big.getBigDecimal(nodo1.getIvaGeneral())));
            txtTotal.setText(big.setMoneda(big.getBigDecimal(nodo1.getTotalGeneral())));
            txtEfectivo.setText(big.setMoneda(big.getBigDecimal(nodo1.getEfectivoGeneral())));
            txtNC.setText(big.setMoneda(big.getBigDecimal(nodo1.getNcGeneral())));

            if (nodo1.getBodega() != null) {
                if (nodo1.getBodega().equals("")) {
                    txtBodega.setText("123-22");
                } else {
                    txtBodega.setText(nodo1.getBodega());
                }
            } else {
                txtBodega.setText("123-22");
            }

            txtCheque.setText(big.setMoneda(big.getBigDecimal(nodo1.getChequeGeneral())));
            txtTargeta.setText(big.setMoneda(big.getBigDecimal(nodo1.getTargetaGeneral())));
            txtObservaciones.setText(nodo1.getObservacion());
            lbNoFactura.setText(txtConsecutivo.getText());

            if (nodoCliente.getNombre() != null) {
                txtNombre.setText(nodoCliente.getNombre());
                txtDireccion.setText(nodoCliente.getDireccion());
                txtTelefono.setText(nodoCliente.getTelefono());
            }

            try {
                ndCxc nodoCxc = instancias.getSql().getDatosCxc(nodo1.getIdFactura());
                txtDiasPlazo.setText("" + nodoCxc.getPlazo());
            } catch (Exception ex) {
                System.out.println("Error, no es factura a credito");
            }

            tapPanel1.setSelectedIndex(1);
        } else if (cmbTipo.getSelectedItem().equals("PLANTILLAS")) {
            ModeloContacto nodoCliente = instancias.getSql().getDatosTercero(nodo2.getCliente());

            txtNit.setText(nodoCliente.getId());

            txtVendedor.setText(nodo2.getVendedor());
            txtFechaFactura.setText(metodos.fecha(nodo2.getFechaFactura()));
            txtVencimiento.setText(metodos.fecha(nodo2.getFechaVencimiento()));
            txtSubTotal.setText(big.setMoneda(big.getBigDecimal(nodo2.getSubtotalGeneral())));
            txtTotalDescuentos.setText(big.setMoneda(big.getBigDecimal(nodo2.getDescuentoGeneral())));
            txtTotalIva.setText(big.setMoneda(big.getBigDecimal(nodo2.getIvaGeneral())));
            txtTotal.setText(big.setMoneda(big.getBigDecimal(nodo2.getTotalGeneral())));
            txtEfectivo.setText(big.setMoneda(big.getBigDecimal(nodo2.getEfectivoGeneral())));
            txtNC.setText(big.setMoneda(big.getBigDecimal(nodo2.getNcGeneral())));

            if (nodo2.getBodega() != null) {
                if (nodo2.getBodega().equals("")) {
                    txtBodega.setText("123-22");
                } else {
                    txtBodega.setText(nodo2.getBodega());
                }
            } else {
                txtBodega.setText("123-22");
            }

            txtCheque.setText(big.setMoneda(big.getBigDecimal(nodo2.getChequeGeneral())));
            txtTargeta.setText(big.setMoneda(big.getBigDecimal(nodo2.getTargetaGeneral())));
            txtObservaciones.setText(nodo2.getObservacion());
            lbNoFactura.setText(txtConsecutivo.getText());

            if (nodoCliente.getNombre() != null) {
                txtNombre.setText(nodoCliente.getNombre());
                txtDireccion.setText(nodoCliente.getDireccion());
                txtTelefono.setText(nodoCliente.getTelefono());
            }

            tapPanel1.setSelectedIndex(1);
        }

        if (cmbTipo.getSelectedItem().equals("FACTURA")) {
            instancias.getMenu().cambiarTitulo("ANULACIÓN DE " + "FACT-" + txtConsecutivo.getText());
        } else if (cmbTipo.getSelectedItem().equals("NOTA DEBITO")) {
            instancias.getMenu().cambiarTitulo("ANULACIÓN DE " + "ND-" + txtConsecutivo.getText());
        } else if (cmbTipo.getSelectedItem().equals("PLANTILLAS")) {
            instancias.getMenu().cambiarTitulo("ANULACIÓN DE " + "PLANTILLA-" + txtConsecutivo.getText());
        } else {
            instancias.getMenu().cambiarTitulo("ANULACIÓN DE " + "SEPARE-" + txtConsecutivo.getText());
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        actualizarTabla();
    }//GEN-LAST:event_btnActualizarActionPerformed

    public void anularFactura(String nota) {

        if (cmbTipo.getSelectedItem().equals("PLAN SEPARE")) {
            if (!instancias.getSql().modificarPlanSepareFechaAnulacion(metodos.fechaConsulta(metodosGenerales.fecha()), instancias.getUsuario(), nodo1.getFactura(),
                    nota, true, (String) instancias.getSql().getNumConsecutivo("ANULA")[0])) {
                metodos.msgError(this, "Error al agregar la fecha de la anulación");
                return;
            }

            if (!txtDiasPlazo.getText().equals("") || !txtDiasPlazo.getText().equals("0")) {
                if (!instancias.getSql().modificarEstadoCxcFactura(nodo1.getFactura(), "ANULADA")) {
                    metodos.msgError(this, "Error al cambiar el estado de la Cxc");
                    return;
                }
            }
        } else if (cmbTipo.getSelectedItem().equals("PLANTILLAS")) {
            if (!instancias.getSql().modificarPlantillaFechaAnulacion(metodos.fechaConsulta(metodosGenerales.fecha()), instancias.getUsuario(), nodo2.getIdFactura(),
                    nota, true, (String) instancias.getSql().getNumConsecutivo("ANULA")[0])) {
                metodos.msgError(this, "Error al agregar la fecha de la anulación");
                return;
            }
        } else {
            if (!instancias.getSql().modificarFacturaFechaAnulacion(metodos.fechaConsulta(metodosGenerales.fecha()), instancias.getUsuario(), nodo.getFactura(),
                    nota, true, (String) instancias.getSql().getNumConsecutivo("ANULA")[0])) {
                metodos.msgError(this, "Error al agregar la fecha de la anulación");
                return;
            }

            if (!instancias.getSql().anularFacturaVerificadorFacturas(nodo.getFactura())) {
                metodos.msgError(this, "Error al agregar la fecha de la anulación");
                return;
            }

            //Si fue una factura de cartera, anula esta.
            if (!txtDiasPlazo.getText().equals("") || !txtDiasPlazo.getText().equals("0")) {
                if (!instancias.getSql().modificarEstadoCxcFactura(nodo.getFactura(), "ANULADA")) {
                    metodos.msgError(this, "Error al cambiar el estado de la Cxc");
                    return;
                }
            }
        }

        String baseUtilizada = txtBodega.getText();
        if (instancias.getConfiguraciones().isInventarioBodegas()) {
            if (baseUtilizada.equals("123-22")) {
                baseUtilizada = "bdProductos";
            } else if (baseUtilizada.equals("BODEGA-1")) {
                baseUtilizada = "bdProductosBodega1";
            } else if (baseUtilizada.equals("BODEGA-2")) {
                baseUtilizada = "bdProductosBodega2";
            } else if (baseUtilizada.equals("BODEGA-3")) {
                baseUtilizada = "bdProductosBodega3";
            } else if (baseUtilizada.equals("BODEGA-4")) {
                baseUtilizada = "bdProductosBodega4";
            }
        } else {
            baseUtilizada = "bdProductos";
        }

        if (cmbTipo.getSelectedItem().equals("FACTURA")) {

            for (int i = 0; i < tblProductos.getRowCount(); i++) {

                ndProducto producto = instancias.getSql().getDatosProducto(tblProductos.getValueAt(i, 14).toString(), baseUtilizada);

                if (!producto.getUsuario().equals("ADMIN")) {

                    String preparacion = "";
                    try {
                        preparacion = tblProductos.getValueAt(i, 10).toString();
                    } catch (Exception e) {
                    }

                    if (preparacion.equals("")) {
                        Object[][] productos = instancias.getSql().getCantidadesDiscosteo(tblProductos.getValueAt(i, 14).toString());
                        for (int k = 0; k < productos.length; k++) {
                            String codigo = productos[k][0].toString();
                            String cant = productos[k][1].toString();
                            ndProducto insumo = instancias.getSql().getDatosProducto(codigo, baseUtilizada);

                            double cantidad;
                            double inventario;
                            double fisicoInventario;

                            try {
                                cantidad = Double.parseDouble(insumo.getAnulada().replace(",", "."));
                            } catch (Exception e) {
                                cantidad = 0;
                            }

                            try {
                                inventario = Double.parseDouble(insumo.getInventario().replace(",", "."));
                            } catch (Exception e) {
                                inventario = 0;
                            }

                            try {
                                fisicoInventario = Double.parseDouble(insumo.getFisicoInventario().replace(",", "."));
                            } catch (Exception e) {
                                fisicoInventario = Double.parseDouble(insumo.getInventario().replace(",", "."));
                            }

                            double cantidadTabla;
                            try {
                                cantidadTabla = Double.parseDouble(cant);
                            } catch (Exception e) {
                                cantidadTabla = Double.parseDouble(cant.substring(0, cant.length() - 2));
                            }

                            inventario = inventario + cantidadTabla;
                            fisicoInventario = fisicoInventario + cantidadTabla;
                            double total = cantidad + cantidadTabla;

                            String total1 = String.valueOf(df.format(total)).replace(".", ",");
                            String inventario1 = String.valueOf(df.format(inventario)).replace(".", ",");
                            String fisicoInventario1 = String.valueOf(df.format(fisicoInventario)).replace(".", ",");

                            instancias.getSql().modificarInventario("anulacion", total1, codigo, baseUtilizada);
                            instancias.getSql().modificarInventario("inventario", inventario1, codigo, baseUtilizada);
                            instancias.getSql().modificarInventario("fisicoInventario", fisicoInventario1, codigo, baseUtilizada);
                        }
                    } else {
                        String opciones2[];
                        String opciones = preparacion.split("; ")[3];
                        if (!opciones.equals("")) {
                            opciones2 = opciones.split(", ");
                            for (int k = 0; k < opciones2.length; k++) {

                                String principal = opciones2[k].split("/")[0];
                                String codigo = opciones2[k].split("/")[1];
                                String cant = opciones2[k].split("/")[2];
                                String estado = opciones2[k].split("/")[3];

                                if (estado.equals(" true")) {

                                    ndProducto insumo = instancias.getSql().getDatosProducto(codigo, baseUtilizada);

                                    double cantidad;
                                    double inventario;
                                    double fisicoInventario;

                                    try {
                                        cantidad = Double.parseDouble(insumo.getAnulada().replace(",", "."));
                                    } catch (Exception e) {
                                        cantidad = 0;
                                    }

                                    try {
                                        inventario = Double.parseDouble(insumo.getInventario().replace(",", "."));
                                    } catch (Exception e) {
                                        inventario = 0;
                                    }

                                    try {
                                        fisicoInventario = Double.parseDouble(insumo.getFisicoInventario().replace(",", "."));
                                    } catch (Exception e) {
                                        fisicoInventario = Double.parseDouble(insumo.getInventario().replace(",", "."));
                                    }

                                    double cantidadTabla;
                                    try {
                                        cantidadTabla = Double.parseDouble(cant);
                                    } catch (Exception e) {
                                        cantidadTabla = Double.parseDouble(cant.substring(0, cant.length() - 2));
                                    }

                                    inventario = inventario + cantidadTabla;
                                    fisicoInventario = fisicoInventario + cantidadTabla;
                                    double total = cantidad + cantidadTabla;

                                    String total1 = String.valueOf(df.format(total)).replace(".", ",");
                                    String inventario1 = String.valueOf(df.format(inventario)).replace(".", ",");
                                    String fisicoInventario1 = String.valueOf(df.format(fisicoInventario)).replace(".", ",");

                                    if (!insumo.getGrupo().equals("GRP-02")) {
                                        instancias.getSql().modificarInventario("anulacion", total1, codigo, baseUtilizada);
                                        instancias.getSql().modificarInventario("inventario", inventario1, codigo, baseUtilizada);
                                        instancias.getSql().modificarInventario("fisicoInventario", fisicoInventario1, codigo, baseUtilizada);
                                    }
                                }
                            }
                        }
                    }

                    String idCosteo = "";
                    try {
                        idCosteo = tblProductos.getValueAt(i, 11).toString();
                    } catch (Exception e) {
                    }

                    if (!idCosteo.equals("")) {
                        instancias.getSql().anularDocumento(idCosteo, "bdCosteo");
                    }
                }

                double cantidad;
                double inventario;
                double fisicoInventario;
                double costeo;

                try {
                    cantidad = Double.parseDouble(producto.getAnulada().replace(",", "."));
                } catch (Exception e) {
                    cantidad = 0;
                }

                try {
                    inventario = Double.parseDouble(producto.getInventario().replace(",", "."));
                } catch (Exception e) {
                    inventario = 0;
                }

                try {
                    fisicoInventario = Double.parseDouble(producto.getFisicoInventario().replace(",", "."));
                } catch (Exception e) {
                    fisicoInventario = Double.parseDouble(producto.getInventario().replace(",", "."));
                }

                try {
                    costeo = Double.parseDouble(producto.getCosteo().replace(",", "."));
                } catch (Exception e) {
                    costeo = 0;
                }

                double cantidadTabla = Double.parseDouble(tblProductos.getValueAt(i, 13).toString().replace(",", "."));

                inventario = inventario + cantidadTabla;
                fisicoInventario = fisicoInventario + cantidadTabla;
                costeo = costeo - cantidadTabla;
                double total = cantidad + cantidadTabla;

                String total1 = String.valueOf(df.format(total)).replace(".", ",");
                String inventario1 = String.valueOf(df.format(inventario)).replace(".", ",");
                String fisicoInventario1 = String.valueOf(df.format(fisicoInventario)).replace(".", ",");
                String costeo1 = String.valueOf(df.format(costeo)).replace(".", ",");

                if (producto.getUsuario().equals("ADMIN")) {
                    instancias.getSql().modificarInventario("anulacion", total1, tblProductos.getValueAt(i, 14).toString(), baseUtilizada);
                    instancias.getSql().modificarInventario("inventario", inventario1, tblProductos.getValueAt(i, 14).toString(), baseUtilizada);
                    instancias.getSql().modificarInventario("fisicoInventario", fisicoInventario1, tblProductos.getValueAt(i, 14).toString(), baseUtilizada);
                } else {
                    instancias.getSql().modificarInventario("anulacion", total1, tblProductos.getValueAt(i, 14).toString(), baseUtilizada);
                    instancias.getSql().modificarInventario("costeo", costeo1, tblProductos.getValueAt(i, 14).toString(), baseUtilizada);
                }

                String idProd = "";
                try {
                    idProd = tblProductos.getValueAt(i, 9).toString();
                } catch (Exception e) {
                }

                String tipo = "";
                if (producto.getTipoProducto() != null) {
                    if (producto.getTipoProducto().equals("IMEI")) {
                        tipo = "Imei";
                    } else if (producto.getTipoProducto().equals("Fecha/Lote")) {
                        tipo = "Fecha/Lote";
                    } else if (producto.getTipoProducto().equals("Color")) {
                        tipo = "Color";
                    } else if (producto.getTipoProducto().equals("Serial")) {
                        tipo = "Serial";
                    } else if (producto.getTipoProducto().equals("Talla")) {
                        tipo = "Talla";
                    } else if (producto.getTipoProducto().equals("ColorTalla")) {
                        tipo = "ColorTalla";
                    } else if (producto.getTipoProducto().equals("SerialColor")) {
                        tipo = "SerialColor";
                    } else {
                        tipo = "";
                    }
                }

                if (!idProd.equals("")) {
                    if (tipo.equals("Imei") || tipo.equals("Serial") || tipo.equals("SerialColor")) {
                        instancias.getSql().modificarEstadoDetalleProductos(idProd, "DISPONIBLE");
//                        instancias.getSql().anularVentaDetalladoInventario(idProd);
                    } else {
                        Double cantidadActual = Double.parseDouble(instancias.getSql().getCantidadProductos(idProd).replace(",", "."));
                        cantidadActual = cantidadActual + cantidadTabla;
                        String cantidadFinal = String.valueOf(df.format(cantidadActual)).replace(".", ",");
//OJO IMPORTANTE ORGANIZAR ESTA PARTE                        
//instancias.getSql().modificarCantidadesDetalleProductos(idProd, cantidadFinal);
                    }
                }
            }
        } else if (cmbTipo.getSelectedItem().equals("PLAN SEPARE")) {
            TipoDocumento tipoMovimiento = TipoDocumento.ANULAR_PLAN_SEPARE;
            List<DetalleProducto> detallesProductos = new ArrayList<>();
            String tablaUtilizada = enumBodegas.TipoBodega.BODEGA_PRINCIPAL.getNombreTabla();
            List<MovimientoInventario> productos = generarListadoProductos(tablaUtilizada);
            String numeroAnulacion = "Anulación-" + (String) instancias.getSql().getNumConsecutivo("ANULA")[0];
            ServicioInventario servicioInventario = new ServicioInventario(productos, detallesProductos, tipoMovimiento, numeroAnulacion, 
                    tablaUtilizada, instancias.getUsuario(), null);

            try {
                servicioInventario.procesarMovimiento();
            } catch (SQLException ex) {
                Logger.getLogger(VistaInventarioInicial.class.getName()).log(Level.SEVERE, null, ex);
                return;
            }
        }

        metodos.msgExito(this, "Registro anulado con éxito");
        btnActualizarActionPerformed(null);

        if (!instancias.getSql().aumentarConsecutivo("ANULA", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("ANULA")[0]) + 1)) {
            metodos.msgError(this, "Hubo un problema al guardar en el consecutivo de la anulación");
        }

        tapPanel1.setSelectedIndex(0);
    }

    private List<MovimientoInventario> generarListadoProductos(String tablaUtilizada) {

        List<MovimientoInventario> movimientos = new ArrayList<>();

        for (int i = 0; i < tblProductos.getRowCount(); i++) {
            ndProducto producto = instancias.getSql().getDatosProducto(obtenerValorTabla(i, 10), tablaUtilizada);
            BigDecimal cantidad = Utilidades.convertirBigDecimal(tblProductos.getValueAt(i, 9).toString());
            String idDetalleProducto = obtenerValorTabla(i, 11);

            MovimientoInventario inventario = new MovimientoInventario(producto, cantidad, BigDecimal.ZERO, idDetalleProducto);
            movimientos.add(inventario);
        }

        return movimientos;
    }

    private String obtenerValorTabla(int row, int col) {
        Object value = tblProductos.getValueAt(row, col);
        return value != null ? value.toString() : "";
    }

    public void actualizarTabla() {
        consultarMaestros();
        btnActualizar.setText("ACTUALIZAR");

        String condicion = "";
        if (!instancias.isVisualizarTodasLasFacturas()) {
            if (!instancias.getUsuario().equals("ADMIN")) {
                condicion = " AND terminal='" + instancias.getTerminal() + "' ";
            }

            if (filtrar.isSelected()) {
                String ini = metodos.desdeDate(dtInicio.getCurrent()), fin = metodos.desdeDate(dtFinal.getCurrent());
                condicion = condicion + " AND (((fechaFactura)>='" + ini + "') And ((fechaFactura)<='" + fin + "'))";
            }
        }

        Object[][] facturas;
        if (cmbTipo.getSelectedItem().equals("PLAN SEPARE")) {
            facturas = instancias.getSql().getRegistrosSepares(condicion);
        } else if (cmbTipo.getSelectedItem().equals("FACTURA")) {
            condicion = condicion + " AND (factura LIKE 'FACT-%') ";
            facturas = instancias.getSql().getRegistrosFacturas(condicion);
        } else if (cmbTipo.getSelectedItem().equals("NOTA DEBITO")) {
            condicion = condicion + " AND (factura LIKE 'ND-%') ";
            facturas = instancias.getSql().getRegistrosFacturas(condicion);
        } else {
            facturas = instancias.getSql().getRegistrosPlantillas(condicion);
        }

        modelo = (DefaultTableModel) tblRegistros.getModel();

        int j = tblRegistros.getRowCount();
        for (int i = 0; i < j; i++) {
            modelo.removeRow(0);
        }

        for (int i = 0; i < facturas.length; i++) {
            modelo.addRow(facturas[i]);
        }

        for (int i = 0; i < tblRegistros.getRowCount(); i++) {
            try {
                tblRegistros.setValueAt(metodos.fecha((String) tblRegistros.getValueAt(i, 2)), i, 2);
                tblRegistros.setValueAt(big.setMoneda(big.getBigDecimal((String) tblRegistros.getValueAt(i, 6))), i, 6);
            } catch (Exception e) {
            }
        }

        if (tblRegistros.getColumnModel().getColumnCount() > 0) {
            tblRegistros.getColumnModel().getColumn(2).setMinWidth(105);
            tblRegistros.getColumnModel().getColumn(2).setPreferredWidth(105);
            tblRegistros.getColumnModel().getColumn(2).setMaxWidth(105);
            tblRegistros.getColumnModel().getColumn(3).setMinWidth(120);
            tblRegistros.getColumnModel().getColumn(3).setPreferredWidth(165);
            tblRegistros.getColumnModel().getColumn(3).setMaxWidth(200);
            tblRegistros.getColumnModel().getColumn(6).setMinWidth(100);
            tblRegistros.getColumnModel().getColumn(6).setPreferredWidth(120);
            tblRegistros.getColumnModel().getColumn(6).setMaxWidth(160);
            tblRegistros.getColumnModel().getColumn(7).setMinWidth(80);
            tblRegistros.getColumnModel().getColumn(7).setPreferredWidth(80);
            tblRegistros.getColumnModel().getColumn(7).setMaxWidth(80);
        }
    }
    private void tblRegistrosVetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {//GEN-FIRST:event_tblRegistrosVetoableChange

    }//GEN-LAST:event_tblRegistrosVetoableChange

    private void tblRegistrosPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tblRegistrosPropertyChange

    }//GEN-LAST:event_tblRegistrosPropertyChange

    private void tblRegistrosAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_tblRegistrosAncestorAdded

    }//GEN-LAST:event_tblRegistrosAncestorAdded

    private void tblRegistrosMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblRegistrosMouseReleased
        txtConsecutivo.setText(((String) tblRegistros.getValueAt(tblRegistros.getSelectedRow(), 1)).replaceAll("FACT-", ""));
    }//GEN-LAST:event_tblRegistrosMouseReleased

    private void txtAnhoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAnhoKeyReleased
        modeloOrdenado.setRowFilter(RowFilter.regexFilter("(?i)" + txtAnho.getText(), 3));
    }//GEN-LAST:event_txtAnhoKeyReleased

    private void txtMesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMesKeyReleased

        modelo = (DefaultTableModel) tblRegistros.getModel();
        modeloOrdenado = new TableRowSorter<>(modelo);
        tblRegistros.setRowSorter(modeloOrdenado);

        modeloOrdenado.setRowFilter(RowFilter.regexFilter("(?i)" + txtMes.getText(), 4));
    }//GEN-LAST:event_txtMesKeyReleased

    private void dtInicioOnCommit(datechooser.events.CommitEvent evt) {//GEN-FIRST:event_dtInicioOnCommit
        btnActualizarActionPerformed(null);
    }//GEN-LAST:event_dtInicioOnCommit

    private void dtFinalOnCommit(datechooser.events.CommitEvent evt) {//GEN-FIRST:event_dtFinalOnCommit
        btnActualizarActionPerformed(null);
    }//GEN-LAST:event_dtFinalOnCommit

    private void txtConsecutivoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtConsecutivoKeyReleased
        try {
            int i;
            for (i = 0; i < tblRegistros.getRowCount(); i++) {
                if (cmbTipo.getSelectedItem().equals("FACTURA")) {
                    if (tblRegistros.getValueAt(i, posicionFact).equals("FACT-" + txtConsecutivo.getText())) {
                        tblRegistros.setColumnSelectionInterval(0, 0);
                        tblRegistros.setRowSelectionInterval(i, i);
                        tblRegistros.getSelectionModel().setSelectionInterval(i, i);
                        tblRegistros.scrollRectToVisible(new Rectangle(tblRegistros.getCellRect(i, 0, true)));
                        break;
                    }
                } else if (cmbTipo.getSelectedItem().equals("PLAN SEPARE")) {
                    if (tblRegistros.getValueAt(i, posicionFact).equals("SEPARE-" + txtConsecutivo.getText())) {
                        tblRegistros.setColumnSelectionInterval(0, 0);
                        tblRegistros.setRowSelectionInterval(i, i);
                        tblRegistros.getSelectionModel().setSelectionInterval(i, i);
                        tblRegistros.scrollRectToVisible(new Rectangle(tblRegistros.getCellRect(i, 0, true)));
                        break;
                    }
                }
            }

            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                btnBuscarActionPerformed(null);
            }

        } catch (Exception e) {

        }
    }//GEN-LAST:event_txtConsecutivoKeyReleased

    private void cmbTipoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbTipoItemStateChanged
        btnActualizarActionPerformed(null);
    }//GEN-LAST:event_cmbTipoItemStateChanged

    private void tblRegistrosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblRegistrosKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (tblRegistros.getRowCount() > 1) {
                tblRegistros.getSelectionModel().setSelectionInterval(tblRegistros.getSelectionModel().getMinSelectionIndex() - 1, tblRegistros.getSelectionModel().getMinSelectionIndex() - 1);
            }
            btnBuscarActionPerformed(null);
        }
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            tblRegistros.scrollRectToVisible(tblRegistros.getCellRect(tblRegistros.getSelectedRow(), 0, true));
        }
    }//GEN-LAST:event_tblRegistrosKeyReleased

    private void txtConseManualKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtConseManualKeyReleased
        modeloOrdenado.setRowFilter(RowFilter.regexFilter("(?i)" + txtConseManual.getText(), 8));
        txtMes.setText("");
        txtAnho.setText("");
        txtConsecutivo.setText("");
    }//GEN-LAST:event_txtConseManualKeyReleased

    private void txtMesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMesActionPerformed

    private void filtrarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_filtrarItemStateChanged
        actualizarTabla();
    }//GEN-LAST:event_filtrarItemStateChanged

    private void txtBodegaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBodegaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBodegaKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAnular;
    private javax.swing.JButton btnBuscTerceros1;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JComboBox cmbListas;
    private javax.swing.JComboBox cmbTipo;
    private datechooser.beans.DateChooserCombo dtFinal;
    private datechooser.beans.DateChooserCombo dtInicio;
    private javax.swing.JLabel etiqTotal;
    private javax.swing.JCheckBox filtrar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbCheque;
    private javax.swing.JLabel lbDireccion;
    private javax.swing.JLabel lbEfectivo;
    private javax.swing.JLabel lbFechaFactura;
    private javax.swing.JLabel lbNC;
    private javax.swing.JLabel lbNit;
    private javax.swing.JLabel lbNit2;
    private javax.swing.JLabel lbNit3;
    private javax.swing.JLabel lbNit4;
    private javax.swing.JLabel lbNit6;
    private javax.swing.JLabel lbNit7;
    private javax.swing.JLabel lbNit8;
    private javax.swing.JLabel lbNit9;
    private javax.swing.JLabel lbNoFactura;
    private javax.swing.JLabel lbNombre;
    private javax.swing.JLabel lbSubtotal;
    private javax.swing.JLabel lbTargeta;
    private javax.swing.JLabel lbTelefono;
    private javax.swing.JLabel lbTotalDescuento;
    private javax.swing.JLabel lbVencimiento;
    private javax.swing.JLabel lbVendedor;
    private javax.swing.JPanel pnlFormulario;
    private javax.swing.JPanel pnlInvisible;
    private javax.swing.JScrollPane scrFormulario;
    private javax.swing.JScrollPane scrProductos;
    private javax.swing.JTabbedPane tapPanel1;
    private javax.swing.JTable tblProductos;
    private javax.swing.JTable tblRegistros;
    private javax.swing.JTextField txtAnho;
    private javax.swing.JTextField txtBodega;
    private javax.swing.JTextField txtCheque;
    private javax.swing.JTextField txtConseManual;
    private javax.swing.JTextField txtConsecutivo;
    private javax.swing.JTextField txtDiasPlazo;
    private javax.swing.JTextField txtDiasPlazo1;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtEfectivo;
    private javax.swing.JLabel txtFechaFactura;
    private javax.swing.JLabel txtIva;
    private javax.swing.JTextField txtMes;
    private javax.swing.JTextField txtNC;
    private javax.swing.JTextField txtNit;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextArea txtObservaciones;
    private javax.swing.JTextField txtSubTotal;
    private javax.swing.JTextField txtTargeta;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JTextField txtTotalDescuentos;
    private javax.swing.JTextField txtTotalIva;
    private javax.swing.JLabel txtVencimiento;
    private javax.swing.JTextField txtVendedor;
    // End of variables declaration//GEN-END:variables
}
