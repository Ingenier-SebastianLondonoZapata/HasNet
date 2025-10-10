package Vista.Cartera;

import Consumidor.FacturacionElectronica.consumidorFacturacionElectronica;
import Controlador.Alertas.ControladorAlertas;
import Controlador.FacturacionElectronica.controladorFacturacionElectronica;
import Enums.enumTipoIdentificacion;
import Enums.enumTipoPersona;
import Enums.enumTipoDocumento;
import Modelo.FacturacionElectronica.Entrada.ModeloConsultaFacturaElectronica;
import Modelo.DocumentosElectronicos.ModeloDescuentos;
import Modelo.DocumentosElectronicos.ModeloDetalleProductos;
import Modelo.DocumentosElectronicos.ModeloDetalleImpuestos;
import Modelo.FacturacionElectronica.Entrada.ModeloFacturacionElectronica;
import Modelo.FacturacionElectronica.Salida.ConsultaFacturaElectronicaDTO;
import Utilidades.Constantes;
import Validaciones.Facturacion.squemaFacturacion;
import Validaciones.FacturacionElectronica.squemaFacturacionElectronica;
import clases.Cartera.ndNc;
import clases.Instancias;
import Modelo.Terceros.ModeloContacto;
import clases.Ventas.ndFactura;
import clases.Ventas.ndNotasCredito;
import clases.big;
import clases.metodosGenerales;
import clases.productos.ndProducto;
import Vista.Solicitudes.vistaSolicitarPermisos;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class vistaNotaCredito extends javax.swing.JInternalFrame {

    private ControladorAlertas alertas = new ControladorAlertas();
    private squemaFacturacion squemaFacturacion = new squemaFacturacion();
    private squemaFacturacionElectronica squemaFacturacionElectronica = new squemaFacturacionElectronica();
    private consumidorFacturacionElectronica consumidorFacturacionElectronica = new consumidorFacturacionElectronica();

    private DecimalFormat formatoDosDecimales = new DecimalFormat("#.00");
    private BigDecimal porcentajeReteFuente = BigDecimal.ZERO;
    private boolean contieneReteIva = false;

    private String CUFE_FACTURA_ELECTRONICA;

    DefaultTableModel modeloPro;
    DefaultTableModel modelo;
    metodosGenerales metodos = new metodosGenerales();
    Instancias instancias;
    TableRowSorter modeloOrdenado;
    ndFactura nodo;
    Object[] datos;
    DecimalFormat df = new DecimalFormat("#.00");
    String simbolo = "";

    //Barra de titulo
    private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension dimBarra = null;
    private String terminal;

    public vistaNotaCredito() {
        initComponents();

        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
        dimBarra = Barra.getPreferredSize();
        Barra.setSize(0, 0);
        Barra.setPreferredSize(new Dimension(0, 0));
        setBorder(null);
        repaint();

        instancias = Instancias.getInstancias();

        this.simbolo = instancias.getSimbolo();

        String dsPrefijo = "";
        if (instancias.getIdNC() != null) {
            dsPrefijo = instancias.getIdNC();
        }

        lbNoNC.setText(dsPrefijo + (String) instancias.getSql().getNumConsecutivo("NC")[0]);
        txtVendedor.setText(this.instancias.getUsuario());

        if (!instancias.getRegimen().equals("")) {
            cmbPorcentajeIva.setSelectedIndex(0);
            cmbPorcentajeIva.setEnabled(false);
            lbIva.setEnabled(false);
        } else {
            cmbPorcentajeIva.setSelectedIndex(3);
        }

        txtBodega.setVisible(false);
        pnlFormulario.registerKeyboardAction(accion("guardar"), "guardar", KeyStroke.getKeyStroke(KeyEvent.VK_G, Event.CTRL_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
        pnlFormulario.registerKeyboardAction(accion("limpiar"), "limpiar", KeyStroke.getKeyStroke(KeyEvent.VK_L, Event.CTRL_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);

        if (!instancias.getConfiguraciones().isFacturaElectronica()) {
            rdPos.setVisible(false);
        }

        pnlInvisible.setVisible(false);
        consultarMaestros();
    }

    private ActionListener accion(final String opc) {
        ActionListener a = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (opc) {
                    case "limpiar":
                        if ((btnLimpiar.isEnabled()) && (btnLimpiar.isVisible())) {
                            btnLimpiarActionPerformed(null);
                        }
                        break;
                    case "guardar":
                        if ((btnGuardar.isEnabled()) && (btnGuardar.isVisible())) {
                            btnGuardarActionPerformed(null);
                        }
                        break;
                }
            }
        };
        return a;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        popBorrar = new javax.swing.JMenuItem();
        buttonGroup1 = new javax.swing.ButtonGroup();
        scrFormulario = new javax.swing.JScrollPane();
        pnlFormulario = new javax.swing.JPanel();
        cmbListas = new javax.swing.JComboBox();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        lbNit = new javax.swing.JLabel();
        txtNit = new javax.swing.JTextField();
        lbNit1 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtVendedor = new javax.swing.JTextField();
        lbVendedor = new javax.swing.JLabel();
        lbDireccion1 = new javax.swing.JLabel();
        cmbVendedor = new javax.swing.JComboBox();
        lbVendedor1 = new javax.swing.JLabel();
        scrProductos = new javax.swing.JScrollPane();
        tblProductos = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        lbCargar = new javax.swing.JLabel();
        txtFactura = new javax.swing.JTextField();
        lbFacturaNo = new javax.swing.JLabel();
        lbNoNC = new javax.swing.JLabel();
        rdReembolsarSi = new javax.swing.JRadioButton();
        rdReembolsarNo = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        rdPos = new javax.swing.JRadioButton();
        jPanel6 = new javax.swing.JPanel();
        lbCodigo = new javax.swing.JLabel();
        txtValorEfectivo = new javax.swing.JTextField();
        lbCreditos = new javax.swing.JLabel();
        txtValorTotal = new javax.swing.JTextField();
        lbCodigo1 = new javax.swing.JLabel();
        txtTotalNc = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtObservaciones = new javax.swing.JTextArea();
        lbIva = new javax.swing.JLabel();
        cmbPorcentajeIva = new javax.swing.JComboBox<>();
        lbCodigo2 = new javax.swing.JLabel();
        txtIvaEfectivo = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        btnAnular = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnReimprimir = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        txtTotal = new javax.swing.JLabel();
        lbSubtotal1 = new javax.swing.JLabel();
        txtSubTotal = new javax.swing.JLabel();
        txtIva3 = new javax.swing.JLabel();
        txtTotalIva = new javax.swing.JLabel();
        lbTotalDescuento = new javax.swing.JLabel();
        txtTotalDescuentos = new javax.swing.JLabel();
        lbImpoconsumo = new javax.swing.JLabel();
        txtTotalImpoconsumo = new javax.swing.JLabel();
        txtRtf = new javax.swing.JLabel();
        txtRiva = new javax.swing.JLabel();
        lbTotalDescuento1 = new javax.swing.JLabel();
        txtCantUnidades = new javax.swing.JLabel();
        lbTotalDescuento2 = new javax.swing.JLabel();
        txtCantProductos = new javax.swing.JLabel();
        lbReteIva = new javax.swing.JLabel();
        lbRtf = new javax.swing.JLabel();
        pnlInvisible = new javax.swing.JPanel();
        txtBodega = new javax.swing.JTextField();
        txtIdSistema = new javax.swing.JTextField();
        txtBodega2 = new javax.swing.JTextField();
        txtRtfPorc = new javax.swing.JTextField();
        jScrollPane12 = new javax.swing.JScrollPane();
        tblComprobantes = new javax.swing.JTable();
        lbVendedor3 = new javax.swing.JLabel();
        cmbConcepto = new javax.swing.JComboBox();

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

        pnlFormulario.setBackground(new java.awt.Color(255, 255, 255));

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

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

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

        lbNit1.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit1.setText("Telefono:");

        txtTelefono.setEditable(false);
        txtTelefono.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtTelefono.setName("CC/NIT"); // NOI18N
        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyReleased(evt);
            }
        });

        txtNombre.setEditable(false);
        txtNombre.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtNombre.setName("Nombre"); // NOI18N
        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });

        txtVendedor.setEditable(false);
        txtVendedor.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtVendedor.setName("Vendedor"); // NOI18N

        lbVendedor.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbVendedor.setText("Vendedor:");

        lbDireccion1.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion1.setText("Nombre:");

        cmbVendedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbVendedorMouseClicked(evt);
            }
        });

        lbVendedor1.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbVendedor1.setText("Vendedor NC:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbDireccion1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbNit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtNit, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                                .addGap(21, 21, 21)
                                .addComponent(lbNit1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtNombre)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbVendedor1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbVendedor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtVendedor))))
                .addGap(12, 12, 12))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbNit, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbNit1)
                        .addComponent(txtNit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(3, 3, 3)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbDireccion1))
                .addGap(3, 3, 3)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtVendedor)
                    .addComponent(lbVendedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbVendedor1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tblProductos.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        tblProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Descripción", "Valor/Unit", "Cantidad", "Subtotal", "Descuento", "%", "Iva", "Total", "Plu", "Cant", "CantInicial", "DescInicial", "Imei", "idProd"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false, false, false, false, false, true, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblProductos.setComponentPopupMenu(jPopupMenu1);
        tblProductos.setRowHeight(24);
        tblProductos.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        tblProductos.getTableHeader().setReorderingAllowed(false);
        tblProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductosMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tblProductosMouseEntered(evt);
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
        if (tblProductos.getColumnModel().getColumnCount() > 0) {
            tblProductos.getColumnModel().getColumn(0).setMinWidth(100);
            tblProductos.getColumnModel().getColumn(0).setPreferredWidth(100);
            tblProductos.getColumnModel().getColumn(0).setMaxWidth(100);
            tblProductos.getColumnModel().getColumn(3).setMinWidth(80);
            tblProductos.getColumnModel().getColumn(3).setPreferredWidth(80);
            tblProductos.getColumnModel().getColumn(3).setMaxWidth(80);
            tblProductos.getColumnModel().getColumn(9).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(9).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(9).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(10).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(10).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(10).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(11).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(11).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(11).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(12).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(12).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(12).setMaxWidth(0);
        }

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        lbCargar.setBackground(new java.awt.Color(204, 204, 204));
        lbCargar.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lbCargar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCargar.setText("FACTURA *");
        lbCargar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lbCargar.setOpaque(true);

        txtFactura.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtFactura.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFactura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFacturaKeyReleased(evt);
            }
        });

        lbFacturaNo.setBackground(new java.awt.Color(204, 204, 204));
        lbFacturaNo.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lbFacturaNo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbFacturaNo.setText("Nota credito");
        lbFacturaNo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lbFacturaNo.setOpaque(true);

        lbNoNC.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbNoNC.setForeground(new java.awt.Color(255, 0, 0));
        lbNoNC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNoNC.setText("3");
        lbNoNC.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        rdReembolsarSi.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdReembolsarSi);
        rdReembolsarSi.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        rdReembolsarSi.setText("SI");

        rdReembolsarNo.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdReembolsarNo);
        rdReembolsarNo.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        rdReembolsarNo.setSelected(true);
        rdReembolsarNo.setText("NO");

        jLabel3.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jLabel3.setText("¿Reembolso Dinero?");

        rdPos.setText("NOTA CRÉDITO POS");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rdReembolsarSi, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rdReembolsarNo)
                        .addGap(66, 66, 66))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbCargar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(lbFacturaNo, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lbNoNC, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(rdPos)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdReembolsarSi, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdReembolsarNo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbFacturaNo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbCargar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtFactura)
                    .addComponent(lbNoNC, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdPos, javax.swing.GroupLayout.PREFERRED_SIZE, 22, Short.MAX_VALUE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        lbCodigo.setBackground(new java.awt.Color(204, 204, 204));
        lbCodigo.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        lbCodigo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCodigo.setText("EFECTIVO");
        lbCodigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lbCodigo.setOpaque(true);

        txtValorEfectivo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtValorEfectivo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtValorEfectivo.setText("0");
        txtValorEfectivo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtValorEfectivoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtValorEfectivoKeyTyped(evt);
            }
        });

        lbCreditos.setBackground(new java.awt.Color(204, 204, 204));
        lbCreditos.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        lbCreditos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCreditos.setText("TOTAL");
        lbCreditos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lbCreditos.setOpaque(true);

        txtValorTotal.setEditable(false);
        txtValorTotal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtValorTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtValorTotal.setText("0");

        lbCodigo1.setBackground(new java.awt.Color(204, 204, 204));
        lbCodigo1.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        lbCodigo1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCodigo1.setText("NC RESTANTE");
        lbCodigo1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lbCodigo1.setOpaque(true);

        txtTotalNc.setEditable(false);
        txtTotalNc.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtTotalNc.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTotalNc.setText("0");
        txtTotalNc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTotalNcKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTotalNcKeyTyped(evt);
            }
        });

        txtObservaciones.setColumns(20);
        txtObservaciones.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        txtObservaciones.setLineWrap(true);
        txtObservaciones.setRows(2);
        txtObservaciones.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtObservaciones.setEnabled(false);
        jScrollPane1.setViewportView(txtObservaciones);

        lbIva.setBackground(new java.awt.Color(204, 204, 204));
        lbIva.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        lbIva.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbIva.setText("% Iva");
        lbIva.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lbIva.setOpaque(true);

        cmbPorcentajeIva.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "5", "16", "19" }));
        cmbPorcentajeIva.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbPorcentajeIvaItemStateChanged(evt);
            }
        });

        lbCodigo2.setBackground(new java.awt.Color(204, 204, 204));
        lbCodigo2.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        lbCodigo2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCodigo2.setText("VALOR IVA");
        lbCodigo2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lbCodigo2.setOpaque(true);

        txtIvaEfectivo.setEditable(false);
        txtIvaEfectivo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtIvaEfectivo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtIvaEfectivo.setText("0");
        txtIvaEfectivo.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtIvaEfectivo.setEnabled(false);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbPorcentajeIva, 0, 52, Short.MAX_VALUE)
                            .addComponent(lbIva, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtValorEfectivo, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbCodigo2, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIvaEfectivo, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbCreditos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbCodigo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTotalNc, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbIva, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbCodigo2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(cmbPorcentajeIva, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtValorEfectivo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                            .addComponent(txtIvaEfectivo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbCreditos, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbCodigo1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTotalNc, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(5, 5, 5)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        btnAnular.setBackground(new java.awt.Color(241, 148, 138));
        btnAnular.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnAnular.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/borrar2.png"))); // NOI18N
        btnAnular.setText("ANULAR ");
        btnAnular.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnAnular.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnAnular.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnAnular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnularActionPerformed(evt);
            }
        });

        btnGuardar.setBackground(new java.awt.Color(46, 204, 113));
        btnGuardar.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
        btnGuardar.setText("GUARDAR");
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnGuardar.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnLimpiar.setBackground(new java.awt.Color(204, 204, 204));
        btnLimpiar.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/limpiar.png"))); // NOI18N
        btnLimpiar.setText("LIMPIAR");
        btnLimpiar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnLimpiar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnLimpiar.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        btnReimprimir.setBackground(new java.awt.Color(247, 220, 111));
        btnReimprimir.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnReimprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/imprimir.png"))); // NOI18N
        btnReimprimir.setText("REIMPRIMIR");
        btnReimprimir.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnReimprimir.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnReimprimir.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnReimprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReimprimirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAnular, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnReimprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAnular)
                    .addComponent(btnGuardar)
                    .addComponent(btnLimpiar)
                    .addComponent(btnReimprimir))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        txtTotal.setFont(new java.awt.Font("Century Gothic", 1, 30)); // NOI18N
        txtTotal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        txtTotal.setText("Total: 0");

        lbSubtotal1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbSubtotal1.setText("Subtotal:");

        txtSubTotal.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txtSubTotal.setText("0");

        txtIva3.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txtIva3.setText("IVA:");

        txtTotalIva.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txtTotalIva.setText("0");

        lbTotalDescuento.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbTotalDescuento.setText("Descuentos:");
        lbTotalDescuento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbTotalDescuentoMouseClicked(evt);
            }
        });

        txtTotalDescuentos.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txtTotalDescuentos.setText("0");

        lbImpoconsumo.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbImpoconsumo.setText("Impoconsumo:");

        txtTotalImpoconsumo.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txtTotalImpoconsumo.setText("0");

        txtRtf.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txtRtf.setText("0");

        txtRiva.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txtRiva.setText("0");

        lbTotalDescuento1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbTotalDescuento1.setText("N° Unit:");
        lbTotalDescuento1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbTotalDescuento1MouseClicked(evt);
            }
        });

        txtCantUnidades.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txtCantUnidades.setText("0");
        txtCantUnidades.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtCantUnidadesMouseClicked(evt);
            }
        });

        lbTotalDescuento2.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbTotalDescuento2.setText("N° Prod:");
        lbTotalDescuento2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbTotalDescuento2MouseClicked(evt);
            }
        });

        txtCantProductos.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txtCantProductos.setText("0");
        txtCantProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtCantProductosMouseClicked(evt);
            }
        });

        lbReteIva.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbReteIva.setText("Rete Iva:");

        lbRtf.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbRtf.setText("RtF:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lbSubtotal1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtIva3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbTotalDescuento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbTotalDescuento1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbTotalDescuento2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTotalIva, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtSubTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTotalDescuentos, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCantUnidades, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtCantProductos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lbImpoconsumo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbReteIva, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 7, Short.MAX_VALUE))
                            .addComponent(lbRtf, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTotalImpoconsumo, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                            .addComponent(txtRiva, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtRtf, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(txtTotal)
                .addGap(3, 3, 3)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbImpoconsumo)
                            .addComponent(txtTotalImpoconsumo, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtRiva)
                            .addComponent(lbReteIva)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbSubtotal1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTotalIva)
                            .addComponent(txtIva3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbTotalDescuento)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtRtf, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lbRtf))
                            .addComponent(txtTotalDescuentos))))
                .addGap(3, 3, 3)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTotalDescuento1)
                    .addComponent(txtCantUnidades))
                .addGap(3, 3, 3)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTotalDescuento2)
                    .addComponent(txtCantProductos))
                .addGap(5, 5, 5))
        );

        txtBodega.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtBodega.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtBodega.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBodegaKeyReleased(evt);
            }
        });

        txtIdSistema.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtIdSistema.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtIdSistema.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIdSistemaKeyReleased(evt);
            }
        });

        txtBodega2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtBodega2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtBodega2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBodega2KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout pnlInvisibleLayout = new javax.swing.GroupLayout(pnlInvisible);
        pnlInvisible.setLayout(pnlInvisibleLayout);
        pnlInvisibleLayout.setHorizontalGroup(
            pnlInvisibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInvisibleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtBodega, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtIdSistema, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBodega2, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtRtfPorc, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlInvisibleLayout.setVerticalGroup(
            pnlInvisibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInvisibleLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(pnlInvisibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBodega, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdSistema, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBodega2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRtfPorc, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );

        tblComprobantes.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        tblComprobantes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, "NOTA CRÉDITO ELECTRÓNICA",  new Boolean(true)},
                {null, "NOTA CRÉDITO ADMINISTRATIVA", null}
            },
            new String [] {
                "Id", "Comprobante", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblComprobantes.setRowHeight(25);
        tblComprobantes.getTableHeader().setResizingAllowed(false);
        tblComprobantes.getTableHeader().setReorderingAllowed(false);
        tblComprobantes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblComprobantesMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tblComprobantesMouseExited(evt);
            }
        });
        jScrollPane12.setViewportView(tblComprobantes);
        if (tblComprobantes.getColumnModel().getColumnCount() > 0) {
            tblComprobantes.getColumnModel().getColumn(0).setMinWidth(0);
            tblComprobantes.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblComprobantes.getColumnModel().getColumn(0).setMaxWidth(0);
            tblComprobantes.getColumnModel().getColumn(2).setMinWidth(20);
            tblComprobantes.getColumnModel().getColumn(2).setPreferredWidth(20);
            tblComprobantes.getColumnModel().getColumn(2).setMaxWidth(20);
        }

        lbVendedor3.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbVendedor3.setText("Concepto:");

        cmbConcepto.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cmbConcepto.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECCIONAR CONCEPTO...", "DEVOLUCION_BIENES", "ANULACION_FACTURA", "REBAJA_APLICADA", "AJUSTE_PRECIO", "OTROS" }));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnlInvisible, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scrProductos)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(lbVendedor3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbConcepto, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbVendedor3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbConcepto, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrProductos, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlInvisible, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout pnlFormularioLayout = new javax.swing.GroupLayout(pnlFormulario);
        pnlFormulario.setLayout(pnlFormularioLayout);
        pnlFormularioLayout.setHorizontalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbListas, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        pnlFormularioLayout.setVerticalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlFormularioLayout.createSequentialGroup()
                        .addGap(273, 273, 273)
                        .addComponent(cmbListas, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlFormularioLayout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        scrFormulario.setViewportView(pnlFormulario);

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(scrFormulario)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtFacturaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFacturaKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cargarFactura("FACT-" + txtFactura.getText());
        } else {
            btnLimpiarActionPerformed(null);
        }
    }//GEN-LAST:event_txtFacturaKeyReleased

    private void jPanel1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPanel1FocusGained

    }//GEN-LAST:event_jPanel1FocusGained

    private void jPanel1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseMoved

    }//GEN-LAST:event_jPanel1MouseMoved

    private void jPanel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseEntered

    }//GEN-LAST:event_jPanel1MouseEntered

    private void cmbListasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbListasActionPerformed

    }//GEN-LAST:event_cmbListasActionPerformed

    private void cmbListasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbListasItemStateChanged

    }//GEN-LAST:event_cmbListasItemStateChanged

    private void tblProductosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblProductosKeyReleased
        BigDecimal iva, totalIva = BigDecimal.ZERO, impoconsumo, totalImpoconsumo = BigDecimal.ZERO, subtotalGeneral, valor, cantidad, descuento, subtotal,
                total = BigDecimal.ZERO, cantidadInicial, porcentaje2, porcentaje;

        int fila = tblProductos.getSelectedRow(), i = 3, j = 1;

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            try {
                int res = big.getBigDecimal(big.getMoneda((String) tblProductos.getValueAt(fila, i))).compareTo(big.getBigDecimal("1"));

                if (res == -1 || String.valueOf(tblProductos.getValueAt(fila, i)).equals("")) {
                    tblProductos.setValueAt(j, tblProductos.getSelectedRow(), i);
                }

            } catch (NumberFormatException e) {
                tblProductos.setValueAt(j, tblProductos.getSelectedRow(), i);
            }

            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                if (tblProductos.getSelectedColumn() == 3) {
                    Double num = 0.0, num1 = 0.0;

                    try {
                        num = Double.parseDouble(tblProductos.getValueAt(tblProductos.getSelectedRow(), 3).toString());
                    } catch (Exception e) {
                    }

                    try {
                        num1 = Double.parseDouble(tblProductos.getValueAt(tblProductos.getSelectedRow(), 13).toString());
                    } catch (Exception e) {
                    }

                    if (num > num1) {
                        tblProductos.setValueAt("1", tblProductos.getSelectedRow(), 3);
                        tblProductosKeyReleased(evt);
                        metodos.msgError(this, "Cantidad mayor a la de la venta");
                        return;
                    }
                }
            }

//            tblProductos.setValueAt(big.setMoneda(big.getMoneda(String.valueOf(tblProductos.getValueAt(fila, 5)))), fila, 5);
//
//            valor = big.getMoneda(String.valueOf(tblProductos.getValueAt(fila, 2)));
//            cantidad = big.getMoneda(String.valueOf(tblProductos.getValueAt(fila, 3)).replace(".", ","));
//            descuento = big.getMoneda(String.valueOf(tblProductos.getValueAt(fila, 5)));
//            iva = big.getBigDecimal(String.valueOf(tblProductos.getValueAt(fila, 6)));
//            subtotal = (valor.multiply(cantidad)).subtract(descuento);
//            iva = (big.getBigDecimal(subtotal).multiply(iva)).divide(new BigDecimal("100"));
//            total = subtotal.add(iva);
//
//            tblProductos.setValueAt(big.setNumero(cantidad), fila, 3);
//            tblProductos.setValueAt(big.setMoneda(subtotal), fila, 4);
//            tblProductos.setValueAt(big.setMonedaExacta(iva), fila, 7);
//            tblProductos.setValueAt(big.setMoneda(total), fila, 8);
            tblProductos.setValueAt(big.setMoneda(big.getMoneda(String.valueOf(tblProductos.getValueAt(fila, 5)))), fila, 5);

            valor = big.getMoneda(tblProductos.getValueAt(fila, 2).toString());
            cantidad = big.getMoneda(String.valueOf(tblProductos.getValueAt(fila, 3)).replace(".", ","));
            cantidadInicial = big.getMoneda(String.valueOf(tblProductos.getValueAt(fila, 13)).replace(".", ","));
            descuento = big.getMoneda(String.valueOf(tblProductos.getValueAt(fila, 14)));

            descuento = descuento.divide(cantidadInicial);
            descuento = descuento.multiply(cantidad);
            subtotal = valor.multiply(cantidad);

            iva = big.getMoneda(String.valueOf(tblProductos.getValueAt(fila, 6).toString().replace(".", ",")));
            iva = (iva.divide(big.getBigDecimal("100"))).add(big.getBigDecimal("1"));

            impoconsumo = big.getMoneda(String.valueOf(tblProductos.getValueAt(fila, 8).toString().replace(".", ",")));
            impoconsumo = (impoconsumo.divide(big.getBigDecimal("100"))).add(big.getBigDecimal("1"));

            subtotal = subtotal.subtract(descuento);
            subtotalGeneral = subtotal;

            if (instancias.isPvpConIva() || instancias.isPvpConImpoconsumo()) {
                BigDecimal subIva = BigDecimal.ZERO, subImpo = BigDecimal.ZERO;
                if (instancias.isPvpConIva() && instancias.isPvpConImpoconsumo()) {
                    //CALCULAMOS EL IVA
                    subIva = subtotal.divide(iva, 2, RoundingMode.HALF_UP);
                    totalIva = subtotal.subtract(subIva);

                    //CALCULAMOS EL IMPOCONSUMO
                    subImpo = subtotal.divide(impoconsumo, 2, RoundingMode.HALF_UP);
                    totalImpoconsumo = subtotal.subtract(subImpo);

                    //CALCULAMOS EL SUBTOTAL
                    subtotal = subtotal.subtract(totalImpoconsumo).subtract(totalIva);
                } else if (instancias.isPvpConIva()) {
                    subIva = subtotal.divide(iva, 2, RoundingMode.HALF_UP);
                    totalIva = subtotal.subtract(subIva);
                    subtotal = subtotal.subtract(totalIva);
                } else if (instancias.isPvpConImpoconsumo()) {
                    subImpo = subtotal.divide(impoconsumo, 2, RoundingMode.HALF_UP);
                    totalImpoconsumo = subtotal.subtract(subImpo);
                    subtotal = subtotal.subtract(totalImpoconsumo);
                }
            }

            if (!instancias.isPvpConIva()) {
                iva = big.getMoneda(String.valueOf(tblProductos.getValueAt(fila, 6)));
                iva = (iva.divide(big.getBigDecimal("100"))).add(big.getBigDecimal("1"));
                totalIva = subtotalGeneral.multiply(iva);
                totalIva = totalIva.subtract(subtotalGeneral);
            }

            if (!instancias.isPvpConImpoconsumo()) {
                impoconsumo = big.getMoneda(String.valueOf(tblProductos.getValueAt(fila, 8)));
                impoconsumo = (impoconsumo.divide(big.getBigDecimal("100"))).add(big.getBigDecimal("1"));
                totalImpoconsumo = subtotalGeneral.multiply(impoconsumo);
                totalImpoconsumo = totalImpoconsumo.subtract(subtotalGeneral);
            }

            total = subtotal.add(totalIva).add(totalImpoconsumo);

            tblProductos.setValueAt(big.setMoneda(subtotal), fila, 4);
            tblProductos.setValueAt(big.setMoneda(descuento), fila, 5);
            tblProductos.setValueAt(big.setMoneda(totalIva), fila, 7);
            tblProductos.setValueAt(big.setMoneda(totalImpoconsumo), fila, 9);
            tblProductos.setValueAt(big.setMoneda(total), fila, 10);

            switch (tblProductos.getValueAt(fila, 11).toString()) {
                case "1":
                    tblProductos.setValueAt(cantidad, fila, 12);
                    break;
                case "2":
                    tblProductos.setValueAt(cantidad.multiply(big.getBigDecimal(instancias.getSql().getDatosProducto(tblProductos.getValueAt(fila, 0).toString(), "bdProductos").getCantidad2())), fila, 12);
                    break;
                case "3":
                    tblProductos.setValueAt(cantidad.multiply(big.getBigDecimal(instancias.getSql().getDatosProducto(tblProductos.getValueAt(fila, 0).toString(), "bdProductos").getCantidad3())), fila, 12);
                    break;
                case "4":
                    tblProductos.setValueAt(cantidad.multiply(big.getBigDecimal(instancias.getSql().getDatosProducto(tblProductos.getValueAt(fila, 0).toString(), "bdProductos").getCantidad4())), fila, 12);
                    break;
            }
            cargarTotales();
        }

    }//GEN-LAST:event_tblProductosKeyReleased

    private void tblProductosMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductosMouseReleased

    }//GEN-LAST:event_tblProductosMouseReleased

    private void tblProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductosMouseClicked

    }//GEN-LAST:event_tblProductosMouseClicked

    private void txtNitKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNitKeyReleased

    }//GEN-LAST:event_txtNitKeyReleased

    private void txtValorEfectivoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorEfectivoKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtValorEfectivoKeyTyped

    private void txtValorEfectivoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorEfectivoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtValorTotal.requestFocus();
            return;
        }

        if (txtValorEfectivo.getText().equals("") || txtValorEfectivo.getText().equals(this.simbolo) || txtValorEfectivo.getText().equals(this.simbolo + " ")) {
            txtValorEfectivo.setText("0");
        }

        txtValorEfectivo.setText(big.setMoneda(big.getMoneda(txtValorEfectivo.getText())));
        calcularValorEfectivo();
    }//GEN-LAST:event_txtValorEfectivoKeyReleased

    private void tblProductosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductosMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tblProductosMouseEntered

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        String vendedor = "";
        try {
            vendedor = cmbVendedor.getSelectedItem().toString();
        } catch (Exception e) {
        }

        if (esFacturacionElectronica()) {
            if (CUFE_FACTURA_ELECTRONICA.isEmpty()) {
                alertas.bigAlert("No se puede generar nota crédito electrónica a una factura sin cufe");
                return;
            }

            ModeloContacto datosCliente = instancias.getSql().getDatosTercero(txtIdSistema.getText());

            Object[] datosValidacion = new Object[]{cmbConcepto.getSelectedIndex() == 0, txtObservaciones.getText()};
            if (!squemaFacturacionElectronica.validaciones_facturacionElectronica(datosCliente, vendedor, datosValidacion, false, true)) {
                return;
            }
        }

        String tipoFacturacion = "";
        if (esFacturacionElectronica()) {
            tipoFacturacion = Constantes.FACTURACION_ELECTRONICA;
        }

        if (!squemaFacturacion.validaciones_detalle_facturacion(tblProductos, tipoFacturacion, enumTipoDocumento.TipoDocumento.NOTA_CREDITO.getValue())) {
            return;
        }

        if (!instancias.getUsuario().equals("ADMIN")) {
            vistaSolicitarPermisos permisos = new vistaSolicitarPermisos(null, true, "NOTAS CREDITO", "NOTA-CREDITO",
                    big.setNumero(big.getMoneda(txtTotal.getText().replace("Total: ", ""))), "notaCredito");
            permisos.setLocationRelativeTo(null);
            permisos.setVisible(true);
            return;
        } else {
            if (metodos.msgPregunta(this, "¿Desea continuar?") != 0) {
                return;
            }
        }

        realizarNc();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        CUFE_FACTURA_ELECTRONICA = "";
        rdPos.setSelected(false);
        porcentajeReteFuente = BigDecimal.ZERO;
        contieneReteIva = false;
        txtObservaciones.setEnabled(false);
        txtBodega.setText("");
        txtNit.setText("");
        txtNombre.setText("");
        txtVendedor.setText("");
        txtTelefono.setText("");
        txtValorEfectivo.setText(this.simbolo + " 0");
        txtTotalNc.setText(this.simbolo + " 0");
        txtTotalImpoconsumo.setText(this.simbolo + " 0");
        txtTotalDescuentos.setText(this.simbolo + " 0");
        txtValorTotal.setText(this.simbolo + " 0");
        txtRiva.setText(this.simbolo + " 0");
        txtRtf.setText(this.simbolo + " 0");
        txtIvaEfectivo.setText(this.simbolo + " 0");
        txtObservaciones.setText("");

        DefaultTableModel y = (DefaultTableModel) tblProductos.getModel();

        int i, j = tblProductos.getRowCount();

        for (i = 0; i < j; i++) {
            y.removeRow(0);
        }

        txtSubTotal.setText(this.simbolo + " 0");
        txtTotal.setText("Total: " + this.simbolo + " 0");
        txtTotalIva.setText(this.simbolo + " 0");

        txtFactura.requestFocus();

        if (evt != null) {
            txtFactura.setText("");
        }

        rdReembolsarNo.setSelected(true);
        terminal = null;

        String dsPrefijo = "";
        if (instancias.getIdNC() != null) {
            dsPrefijo = instancias.getIdNC();
        }

        lbNoNC.setText(dsPrefijo + (String) instancias.getSql().getNumConsecutivo("NC")[0]);
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void popBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popBorrarActionPerformed
        if (tblProductos.getSelectedRow() > -1) {
            int fila[] = tblProductos.getSelectedRows();

            DefaultTableModel modelo = (DefaultTableModel) tblProductos.getModel();

            for (int i = 0; i < fila.length; i++) {
                modelo.removeRow(fila[i]);
            }

            cargarTotales();
        } else {
            metodos.msgAdvertencia(this, "Seleccione un producto");
        }
    }//GEN-LAST:event_popBorrarActionPerformed

    private void btnAnularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnularActionPerformed
        String consecutivo = "NC-" + metodos.msgIngresarEnter(this, "Documento a anular");

        boolean anulado = instancias.getSql().getDocumentoAnulado("bdNc", "Where id ='" + consecutivo + "' ");
        if (anulado) {
            metodos.msgError(null, "El documento esta anulado");
            return;
        }

        if (!instancias.getSql().estaSinUtilizar(consecutivo)) {
            metodos.msgError(this, "La nota credito ya ha sido usada");
            return;
        }

        if (metodos.msgPregunta(this, "¿Anular esta nota credito?") == 0) {

            if (!instancias.getSql().anularDocumento(consecutivo, "bdNc")) {
                metodos.msgError(this, "Hubo un problema al anular la nota credito");
            }

            if (!instancias.getSql().descontarNc(consecutivo, "0")) {
                metodos.msgError(this, "Hubo un problema al anular la nota credito");
            }

            Object[][] notasCredito = instancias.getSql().getNotasCredito(consecutivo);

            for (int i = 0; i < notasCredito.length; i++) {

                ndProducto producto = instancias.getSql().getDatosProducto(notasCredito[i][1].toString(), "bdProductos");
                double cantidad;
                double inventario;
                double fisicoInventario;

                try {
                    cantidad = Double.parseDouble(producto.getNc().replace(",", "."));
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
                    fisicoInventario = Double.parseDouble(producto.getInventario());
                }

                if ("EFECTIVO".equals(producto.getCodigo())) {

                } else {
                    inventario = inventario - Double.parseDouble(notasCredito[i][6].toString().substring(1, notasCredito[i][6].toString().length()));
                    fisicoInventario = fisicoInventario - Double.parseDouble(notasCredito[i][6].toString().substring(1, notasCredito[i][6].toString().length()));
                    double total = cantidad - Double.parseDouble(notasCredito[i][6].toString().substring(1, notasCredito[i][6].toString().length()));

                    String inventario1 = String.valueOf(df.format(inventario)).replace(".", ",");
                    String fisicoInventario1 = String.valueOf(df.format(fisicoInventario)).replace(".", ",");
                    String total1 = String.valueOf(df.format(total)).replace(".", ",");

                    instancias.getSql().modificarInventario("nc", total1, notasCredito[i][1].toString(), "bdProductos");
                    instancias.getSql().modificarInventario("inventario", inventario1, notasCredito[i][1].toString(), "bdProductos");
                    instancias.getSql().modificarInventario("fisicoInventario", fisicoInventario1, notasCredito[i][1].toString(), "bdProductos");
                }
            }

            metodos.msgExito(this, "Nota credito anulada con éxito");
        }
    }//GEN-LAST:event_btnAnularActionPerformed

    private void btnReimprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReimprimirActionPerformed
        String consecutivo = "NC-" + metodos.msgIngresarEnter(this, "Documento a reimprimir");

        boolean anulado = false;
        try {
            anulado = instancias.getSql().getDocumentoAnulado("bdNc", "Where id ='" + consecutivo + "' ");
        } catch (Exception e) {
            metodos.msgAdvertenciaAjustado(this, "La nota credito no existe");
            return;
        }

        if (anulado) {
            metodos.msgError(null, "Este documento se encuentra anulado");
            return;
        }

        ndNc nodoNc = instancias.getSql().getDatosNotaCredito(consecutivo);
        String infoEmpresa = metodosGenerales.convertToMultiline(instancias.getInformacionEmpresaReimpresion() + "" + nodoNc.getResolucion());
        instancias.getReporte().ver_Nc(consecutivo, "", infoEmpresa);
    }//GEN-LAST:event_btnReimprimirActionPerformed

    private void txtTotalNcKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalNcKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalNcKeyReleased

    private void txtTotalNcKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalNcKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalNcKeyTyped

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void txtTelefonoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoKeyReleased

    private void txtBodegaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBodegaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBodegaKeyReleased

    private void lbTotalDescuentoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbTotalDescuentoMouseClicked

    }//GEN-LAST:event_lbTotalDescuentoMouseClicked

    private void lbTotalDescuento1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbTotalDescuento1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lbTotalDescuento1MouseClicked

    private void txtCantUnidadesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCantUnidadesMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantUnidadesMouseClicked

    private void lbTotalDescuento2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbTotalDescuento2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lbTotalDescuento2MouseClicked

    private void txtCantProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCantProductosMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantProductosMouseClicked

    private void txtIdSistemaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdSistemaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdSistemaKeyReleased

    private void txtBodega2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBodega2KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBodega2KeyReleased

    private void cmbVendedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbVendedorMouseClicked

    }//GEN-LAST:event_cmbVendedorMouseClicked

    private void cmbPorcentajeIvaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbPorcentajeIvaItemStateChanged
        calcularValorEfectivo();
    }//GEN-LAST:event_cmbPorcentajeIvaItemStateChanged

    private void tblComprobantesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblComprobantesMouseClicked
        for (int i = 0; i < tblComprobantes.getRowCount(); i++) {
            tblComprobantes.setValueAt(false, i, 2);
        }

        if (tblComprobantes.getSelectedRow() == -1) {
            tblComprobantes.setValueAt(true, 0, 2);
        } else {
            tblComprobantes.setValueAt(true, tblComprobantes.getSelectedRow(), 2);
        }
    }//GEN-LAST:event_tblComprobantesMouseClicked

    private void tblComprobantesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblComprobantesMouseExited
        for (int i = 0; i < tblComprobantes.getRowCount(); i++) {
            tblComprobantes.setValueAt(false, i, 2);
        }

        if (tblComprobantes.getSelectedRow() == -1) {
            tblComprobantes.setValueAt(true, 0, 2);
        } else {
            tblComprobantes.setValueAt(true, tblComprobantes.getSelectedRow(), 2);
        }
    }//GEN-LAST:event_tblComprobantesMouseExited

    private void calcularValorEfectivo() {
        BigDecimal iva = big.getMoneda(txtValorEfectivo.getText()).multiply(big.getBigDecimal(cmbPorcentajeIva.getSelectedItem())).
                divide(big.getBigDecimal("100"), 2, RoundingMode.HALF_UP);
        txtIvaEfectivo.setText(big.setMoneda(iva));

        txtValorTotal.setText(big.setMoneda(((big.getBigDecimal(cmbPorcentajeIva.getSelectedItem()).divide(big.getBigDecimal("100")))
                .multiply(big.getMoneda(txtValorEfectivo.getText()))).add(big.getMoneda(txtValorEfectivo.getText()))));

        cargarTotales();
    }

    private boolean esFacturacionElectronica() {
        return instancias.getConfiguraciones().isFacturaElectronica() && (Boolean) tblComprobantes.getValueAt(0, 2) == true;
    }

    public void cargarFactura(String factura) {
        btnLimpiarActionPerformed(null);
        txtFactura.setText(factura.replace("FACT-", ""));

        ndFactura nodo = instancias.getSql().getDatosFactura(factura);

        if (nodo.getIdFactura() == null) {
            metodos.msgAdvertenciaAjustado(this, "Esta factura no existe");
            return;
        }

        if (nodo.isAnulada()) {
            metodos.msgError(this, "Esta factura se encuentra anulada");
            return;
        }

        if (nodo.getModeloContable().equals(Constantes.FACTURACION_ELECTRONICA)) {
            try {
                ConsultaFacturaElectronicaDTO datosFactura = consumidorFacturacionElectronica.consultarFacturaElectronica(factura.replace("FACT-", ""));
                if (datosFactura.getCufe().isEmpty()) {
                    alertas.alertFail("Factura sin cufe");
                    return;
                }

                CUFE_FACTURA_ELECTRONICA = datosFactura.getCufe();
            } catch (Exception ex) {
                alertas.alertFail("Error al consultar la factura electrónica");
                return;
            }
        }

        try {
            Object[][] nc = instancias.getSql().getDatosNc(nodo.getFactura());

            if (nc[0][0] != null) {
                double v1 = Double.parseDouble(nc[0][1].toString());
                double v2;
                try {
                    v2 = Double.parseDouble(nc[0][2].toString());
                } catch (Exception e) {
                    v2 = Double.parseDouble(nc[0][2].toString().substring(2, nc[0][2].toString().length()).replace(".", ""));
                }

                if (v1 < v2) {
                    double valor = v2 - v1;
                    BigDecimal valor2 = big.getBigDecimal(valor);
                    txtTotalNc.setText(big.setMoneda((valor2)));
                } else {
                    metodos.msgAdvertenciaAjustado(this, "Limite de notas creditos");
                    return;
                }
            }
        } catch (Exception e) {
            txtTotalNc.setText(big.setMoneda(big.getBigDecimal(nodo.getTotalGeneral())));
        }

        txtRtfPorc.setText(nodo.getOtros());

        tblProductos.setModel(instancias.getSql().getRegistrosVentasPlu(nodo.getFactura()));

        txtBodega.setText(nodo.getBodega());
        tblProductos.getColumnModel().getColumn(0).setMinWidth(0);
        tblProductos.getColumnModel().getColumn(0).setPreferredWidth(0);
        tblProductos.getColumnModel().getColumn(0).setMaxWidth(0);
        tblProductos.getColumnModel().getColumn(2).setMinWidth(100);
        tblProductos.getColumnModel().getColumn(2).setPreferredWidth(120);
        tblProductos.getColumnModel().getColumn(2).setMaxWidth(150);
        tblProductos.getColumnModel().getColumn(3).setMinWidth(30);
        tblProductos.getColumnModel().getColumn(3).setPreferredWidth(50);
        tblProductos.getColumnModel().getColumn(3).setMaxWidth(60);
        tblProductos.getColumnModel().getColumn(4).setMinWidth(100);
        tblProductos.getColumnModel().getColumn(4).setPreferredWidth(120);
        tblProductos.getColumnModel().getColumn(4).setMaxWidth(150);
        tblProductos.getColumnModel().getColumn(5).setMinWidth(100);
        tblProductos.getColumnModel().getColumn(5).setPreferredWidth(120);
        tblProductos.getColumnModel().getColumn(5).setMaxWidth(150);
        tblProductos.getColumnModel().getColumn(6).setMinWidth(40);
        tblProductos.getColumnModel().getColumn(6).setPreferredWidth(40);
        tblProductos.getColumnModel().getColumn(6).setMaxWidth(40);
        tblProductos.getColumnModel().getColumn(7).setMinWidth(100);
        tblProductos.getColumnModel().getColumn(7).setPreferredWidth(120);
        tblProductos.getColumnModel().getColumn(7).setMaxWidth(150);
        tblProductos.getColumnModel().getColumn(8).setMinWidth(45);
        tblProductos.getColumnModel().getColumn(8).setPreferredWidth(45);
        tblProductos.getColumnModel().getColumn(8).setMaxWidth(45);
        tblProductos.getColumnModel().getColumn(9).setMinWidth(100);
        tblProductos.getColumnModel().getColumn(9).setPreferredWidth(120);
        tblProductos.getColumnModel().getColumn(9).setMaxWidth(150);
        tblProductos.getColumnModel().getColumn(10).setMinWidth(100);
        tblProductos.getColumnModel().getColumn(10).setPreferredWidth(120);
        tblProductos.getColumnModel().getColumn(10).setMaxWidth(150);
        tblProductos.getColumnModel().getColumn(11).setMinWidth(0);
        tblProductos.getColumnModel().getColumn(11).setPreferredWidth(0);
        tblProductos.getColumnModel().getColumn(11).setMaxWidth(0);
        tblProductos.getColumnModel().getColumn(12).setMinWidth(0);
        tblProductos.getColumnModel().getColumn(12).setPreferredWidth(0);
        tblProductos.getColumnModel().getColumn(12).setMaxWidth(0);
        tblProductos.getColumnModel().getColumn(13).setMinWidth(0);
        tblProductos.getColumnModel().getColumn(13).setPreferredWidth(0);
        tblProductos.getColumnModel().getColumn(13).setMaxWidth(0);
        tblProductos.getColumnModel().getColumn(14).setMinWidth(100);
        tblProductos.getColumnModel().getColumn(14).setPreferredWidth(120);
        tblProductos.getColumnModel().getColumn(14).setMaxWidth(150);
        tblProductos.getColumnModel().getColumn(15).setMinWidth(0);
        tblProductos.getColumnModel().getColumn(15).setPreferredWidth(0);
        tblProductos.getColumnModel().getColumn(15).setMaxWidth(0);
        tblProductos.getColumnModel().getColumn(16).setMinWidth(0);
        tblProductos.getColumnModel().getColumn(16).setPreferredWidth(0);
        tblProductos.getColumnModel().getColumn(16).setMaxWidth(0);
        tblProductos.getColumnModel().getColumn(17).setMinWidth(0);
        tblProductos.getColumnModel().getColumn(17).setPreferredWidth(0);
        tblProductos.getColumnModel().getColumn(17).setMaxWidth(0);
        tblProductos.getColumnModel().getColumn(18).setMinWidth(0);
        tblProductos.getColumnModel().getColumn(18).setPreferredWidth(0);
        tblProductos.getColumnModel().getColumn(18).setMaxWidth(0);
        tblProductos.getColumnModel().getColumn(19).setMinWidth(0);
        tblProductos.getColumnModel().getColumn(19).setPreferredWidth(0);
        tblProductos.getColumnModel().getColumn(19).setMaxWidth(0);
        tblProductos.getColumnModel().getColumn(20).setMinWidth(0);
        tblProductos.getColumnModel().getColumn(20).setPreferredWidth(0);
        tblProductos.getColumnModel().getColumn(20).setMaxWidth(0);
        tblProductos.getColumnModel().getColumn(21).setMinWidth(0);
        tblProductos.getColumnModel().getColumn(21).setPreferredWidth(0);
        tblProductos.getColumnModel().getColumn(21).setMaxWidth(0);
        tblProductos.getColumnModel().getColumn(22).setMinWidth(0);
        tblProductos.getColumnModel().getColumn(22).setPreferredWidth(0);
        tblProductos.getColumnModel().getColumn(22).setMaxWidth(0);

        for (int i = 0; i < tblProductos.getRowCount(); i++) {
            tblProductos.setValueAt(big.setMoneda(big.getBigDecimal((String) tblProductos.getValueAt(i, 2))), i, 2);
            //tblProductos.setValueAt(big.setNumero(big.getBigDecimal((String) tblProductos.getValueAt(i, 3))), i, 3);
            tblProductos.setValueAt(big.setNumero(big.getBigDecimal((String) tblProductos.getValueAt(i, 10))), i, 10);
//            tblProductos.setValueAt(big.getBigDecimal((String) tblProductos.getValueAt(i, 11)), i, 11);
            tblProductos.setValueAt(big.setMoneda(big.getBigDecimal((String) tblProductos.getValueAt(i, 4))), i, 4);
            tblProductos.setValueAt(big.setMoneda(big.getBigDecimal((String) tblProductos.getValueAt(i, 5))), i, 5);
            tblProductos.setValueAt(big.setMoneda(big.getBigDecimal((String) tblProductos.getValueAt(i, 14))), i, 14);
            tblProductos.setValueAt(big.setMoneda(big.getBigDecimal((String) tblProductos.getValueAt(i, 7))), i, 7);
            tblProductos.setValueAt(big.setMoneda(big.getBigDecimal((String) tblProductos.getValueAt(i, 9))), i, 9);
            tblProductos.setValueAt(big.setMoneda(big.getMoneda((String) tblProductos.getValueAt(i, 10))), i, 10);
        }

        ModeloContacto nodoCliente = instancias.getSql().getDatosTercero(nodo.getCliente());
        txtIdSistema.setText(nodoCliente.getIdSistema());
        txtNit.setText(nodoCliente.getId());
        txtVendedor.setText(nodo.getVendedor());

        if (big.getBigDecimal(nodo.getRtIva()).compareTo(BigDecimal.ZERO) > 0) {
            txtRiva.setText(big.setMonedaExacta(big.getBigDecimal(nodo.getRtIva())));
        }

        if (big.getBigDecimal(nodo.getRtFuente()).compareTo(BigDecimal.ZERO) > 0) {
            txtRtf.setText(big.setMonedaExacta(big.getBigDecimal(nodo.getRtFuente())));
        }

        if (nodoCliente.getNombre() != null) {
            txtNombre.setText(nodoCliente.getNombre());
            txtTelefono.setText(nodoCliente.getTelefono());
        }

        if (Constantes.FACTURACION_ELECTRONICA_POS.equals(nodo.getModeloContable())) {
            rdPos.setSelected(true);
        }

        txtObservaciones.setEnabled(true);
        cargarTotales();

        this.porcentajeReteFuente = big.getMoneda(txtRtf.getText()).multiply(big.getBigDecimal(100)).divide(big.getMoneda(txtSubTotal.getText()), 2, RoundingMode.HALF_UP);
        if (big.getBigDecimal(nodo.getRtIva()).compareTo(BigDecimal.ZERO) > 0) {
            this.contieneReteIva = true;
        }
    }

    public Object[] getOcultarIvaPanama() {
        return new Object[]{tblProductos, lbReteIva, txtRiva, lbRtf, txtRtf, txtTotalImpoconsumo,
            lbImpoconsumo};
    }

    public Object[] getOcultarIva() {
        return new Object[]{tblProductos, lbReteIva, txtRiva, cmbPorcentajeIva, txtTotalIva, lbRtf, txtRtf, txtTotalImpoconsumo,
            lbImpoconsumo};
    }

    private String obtenerPrefijoNotaCredito() {
        String dsPrefijo = "";
        if (instancias.getIdNC() != null) {
            dsPrefijo = instancias.getIdNC();
        }

        return dsPrefijo;
    }

    private String obtenerRegimen(boolean esResponsableIva) {
        String regimenAdquirente = "SIMPLE";
        if (esResponsableIva) {
            regimenAdquirente = "ORDINARIO";
        }

        return regimenAdquirente;
    }

    private String obtenerResponsabilidadesFiscales() {
        String[] responsabilidades = datos[113].toString().split(", ");
        String responsabilidadesFiscales = "";
        for (int i = 0; i < responsabilidades.length; i++) {
            responsabilidadesFiscales = responsabilidadesFiscales + responsabilidades[i].split(" /")[0] + ";";
        }

        if (!responsabilidadesFiscales.equals("")) {
            responsabilidadesFiscales = responsabilidadesFiscales.substring(0, responsabilidadesFiscales.length() - 1);
        }

        return responsabilidadesFiscales;
    }

    private String[] obtenerDatosFactura() {
        String referenciaFactura = txtFactura.getText();
        String[] datosFactura = new String[2];
        int i = 0;

        // Encuentra el índice donde comienza el número de la factura
        while (i < referenciaFactura.length() && !Character.isDigit(referenciaFactura.charAt(i))) {
            i++;
        }

        // Divide la cadena en prefijo y número
        String dsPrefijoFacturaReferencia = referenciaFactura.substring(0, i);
        String dsNumeroFacturaReferencia = referenciaFactura.substring(i);

        datosFactura[0] = dsPrefijoFacturaReferencia;
        datosFactura[1] = dsNumeroFacturaReferencia;
        return datosFactura;
    }

    private Object[][] obtenerImpuestosPorProducto(ModeloDetalleProductos detalleProducto, int filaProducto) {
        int secuencia = 0;
        Object[][] informacionImpuestosFactura = new Object[2][4];
        BigDecimal baseProducto = big.getMoneda(tblProductos.getValueAt(filaProducto, 4).toString());

        if (detalleProducto.getValorIva().compareTo(BigDecimal.ZERO) > 0) {
            informacionImpuestosFactura[secuencia][0] = baseProducto;
            informacionImpuestosFactura[secuencia][1] = detalleProducto.getValorIva();
            informacionImpuestosFactura[secuencia][2] = formatoDosDecimales.format(detalleProducto.getPorcentajeIva()).replace(",", ".");
            informacionImpuestosFactura[secuencia][3] = "IVA";
            secuencia++;
        }

        if (detalleProducto.getValorConsumo().compareTo(BigDecimal.ZERO) > 0) {
            informacionImpuestosFactura[secuencia][0] = baseProducto;
            informacionImpuestosFactura[secuencia][1] = detalleProducto.getValorConsumo();
            informacionImpuestosFactura[secuencia][2] = formatoDosDecimales.format(detalleProducto.getPorcentajeConsumo()).replace(",", ".");
            informacionImpuestosFactura[secuencia][3] = "INC";
            secuencia++;
        }

        return informacionImpuestosFactura;
    }

    private ModeloDescuentos[] obtenerDescuentosFactura(BigDecimal descuentosDocumento) {

        String codigoDescuento = "", descripcionDescuento = "";
        for (int i = 0; i < tblProductos.getRowCount(); i++) {
            if (!"Sin-Permiso".equals(tblProductos.getValueAt(i, 20).toString())) {
                codigoDescuento = tblProductos.getValueAt(i, 20).toString().split("///")[0];
                try {
                    descripcionDescuento = tblProductos.getValueAt(i, 20).toString().split("///")[1];
                } catch (Exception e) {
                }
            }
        }

        BigDecimal valorBase = big.getMoneda(txtTotalDescuentos.getText()).add(big.getMoneda(txtSubTotal.getText()));
        ModeloDescuentos[] informacionDescuentos = new ModeloDescuentos[1];

        if (descuentosDocumento.compareTo(BigDecimal.ZERO) > 0) {
            ModeloDescuentos modeloDescuento = new ModeloDescuentos();
            modeloDescuento.setTipo(false);
            modeloDescuento.setRazonDescuento(descripcionDescuento);
            modeloDescuento.setValorDescuento(formatoDosDecimales.format(descuentosDocumento).replace(",", "."));
            modeloDescuento.setValorBase(formatoDosDecimales.format(valorBase).replace(",", "."));

            BigDecimal porcentajeDescuento = descuentosDocumento.multiply(big.getBigDecimal(100)).divide(valorBase, 0, RoundingMode.HALF_UP);
            modeloDescuento.setPorcentaje(formatoDosDecimales.format(porcentajeDescuento).replace(",", "."));
            modeloDescuento.setCodigoDescuento(codigoDescuento);
            informacionDescuentos[0] = modeloDescuento;
        }

        return informacionDescuentos;
    }

    private ModeloDescuentos[] obtenerDescuentosProducto(ModeloDetalleProductos informacionProducto, int filaProducto) {

        String codigoDescuento = "", descripcionDescuento = "";
        if (!"".equals(tblProductos.getValueAt(filaProducto, 20).toString())) {
            codigoDescuento = tblProductos.getValueAt(filaProducto, 20).toString().split("///")[0];
            try {
                descripcionDescuento = tblProductos.getValueAt(filaProducto, 20).toString().split("///")[1];
            } catch (Exception e) {
            }
        }

        BigDecimal valorBase = big.getMoneda(tblProductos.getValueAt(filaProducto, 4).toString());
        BigDecimal descuentoProducto = big.getMoneda(tblProductos.getValueAt(filaProducto, 5).toString());
        ModeloDescuentos[] informacionDescuentos = new ModeloDescuentos[1];

        if (descuentoProducto.compareTo(BigDecimal.ZERO) > 0) {
            ModeloDescuentos modeloDescuento = new ModeloDescuentos();
            modeloDescuento.setTipo(false);
            modeloDescuento.setRazonDescuento(descripcionDescuento);
            modeloDescuento.setValorDescuento(formatoDosDecimales.format(descuentoProducto).replace(",", "."));
            modeloDescuento.setValorBase(formatoDosDecimales.format(valorBase).replace(",", "."));

            BigDecimal porcentajeDescuento = descuentoProducto.multiply(BigDecimal.valueOf(100)).divide(valorBase.add(descuentoProducto), 0, RoundingMode.HALF_UP);
            modeloDescuento.setPorcentaje(formatoDosDecimales.format(porcentajeDescuento).replace(",", "."));
            modeloDescuento.setCodigoDescuento(codigoDescuento);
            informacionDescuentos[0] = modeloDescuento;
        }

        return informacionDescuentos;
    }

    private ModeloDetalleProductos obtenerProductoEfectivo(String notaCredito) {
        ModeloDetalleProductos modeloIndividual = new ModeloDetalleProductos();
        modeloIndividual.setNumeroFactura(notaCredito);
        modeloIndividual.setCodigoArticulo("EFECTIVO");
        modeloIndividual.setEstandarProducto("UNSPSC");
        modeloIndividual.setDescripcionArticulo("EFECTIVO");
        modeloIndividual.setPorcentajeIva(big.getBigDecimal(cmbPorcentajeIva.getSelectedItem().toString()));
        modeloIndividual.setPorcentajeConsumo(BigDecimal.ZERO);
        modeloIndividual.setCantidad("1");
        modeloIndividual.setPrecioUnitario(big.getMoneda(txtValorEfectivo.getText()));
        modeloIndividual.setValorTotalArticulo(big.getMoneda(txtValorEfectivo.getText()));
        BigDecimal valorIva = big.getMoneda(txtValorEfectivo.getText()).multiply(big.getBigDecimal(cmbPorcentajeIva.getSelectedItem())).divide(big.getBigDecimal(100), 2, RoundingMode.HALF_UP);
        modeloIndividual.setValorIva(valorIva);
        modeloIndividual.setValorConsumo(BigDecimal.ZERO);
        modeloIndividual.setUnidadMedida("UNIDAD");
        modeloIndividual.setValorTotalBruto(big.getMoneda(txtValorEfectivo.getText()));
        modeloIndividual.setValorTotalImpuestosRetenciones(big.getMoneda(txtValorEfectivo.getText()).add(valorIva));
        modeloIndividual.setCodigoVendedor(instancias.getSql().getIdEmpleado(cmbVendedor.getSelectedItem().toString()));
        modeloIndividual.setObservacionDetalle("");

        ModeloDescuentos[] descuentoProducto = new ModeloDescuentos[0];
        modeloIndividual.setDescuentoProducto(descuentoProducto);

        Object[][] impuestosProducto = new Object[1][4];
        impuestosProducto[0][0] = big.getMoneda(txtValorEfectivo.getText());
        impuestosProducto[0][1] = valorIva;
        impuestosProducto[0][2] = formatoDosDecimales.format(Integer.parseInt(cmbPorcentajeIva.getSelectedItem().toString())).replace(",", ".");
        impuestosProducto[0][3] = "IVA";

        modeloIndividual.setImpuestosProducto(impuestosProducto);

        return modeloIndividual;
    }

    private ModeloDetalleProductos[] obtenerDetalleProductos(String notaCredito) {

        int cantidadTotal = big.getMoneda(txtValorEfectivo.getText()).compareTo(BigDecimal.ZERO) > 0 ? tblProductos.getRowCount() + 1 : tblProductos.getRowCount();
        ModeloDetalleProductos[] detalladoProductos = new ModeloDetalleProductos[cantidadTotal];

        for (int i = 0; i < tblProductos.getRowCount(); i++) {
            ModeloDetalleProductos modeloIndividual = new ModeloDetalleProductos();
            modeloIndividual.setNumeroFactura(notaCredito);
            modeloIndividual.setCodigoArticulo(tblProductos.getValueAt(i, 0).toString());
            modeloIndividual.setEstandarProducto("UNSPSC");
            modeloIndividual.setDescripcionArticulo(tblProductos.getValueAt(i, Constantes.COLUMNA_DESCRIPCION_PRODUCTO).toString());
            modeloIndividual.setPorcentajeIva(big.getBigDecimal(tblProductos.getValueAt(i, 6).toString()));
            modeloIndividual.setPorcentajeConsumo(big.getBigDecimal(tblProductos.getValueAt(i, 8).toString()));
            modeloIndividual.setCantidad(tblProductos.getValueAt(i, 3).toString());
            modeloIndividual.setPrecioUnitario(big.getMoneda(tblProductos.getValueAt(i, 2).toString()));
            modeloIndividual.setValorTotalArticulo(big.getMoneda(tblProductos.getValueAt(i, 10).toString()));

            BigDecimal totalIva = big.getMoneda(tblProductos.getValueAt(i, 7).toString());
            BigDecimal totalImpoconsumo = big.getMoneda(tblProductos.getValueAt(i, 9).toString());
            modeloIndividual.setValorIva(totalIva);
            modeloIndividual.setValorConsumo(totalImpoconsumo);
            modeloIndividual.setUnidadMedida(tblProductos.getValueAt(i, 21).toString());

            BigDecimal valorTotalBruto = big.getMoneda(tblProductos.getValueAt(i, 2).toString()).multiply(big.getBigDecimal(tblProductos.getValueAt(i, 3).toString()));
            if (instancias.isPvpConIva()) {
                valorTotalBruto = valorTotalBruto.subtract(totalIva);
            }
            if (instancias.isPvpConImpoconsumo()) {
                valorTotalBruto = valorTotalBruto.subtract(totalImpoconsumo);
            }

            modeloIndividual.setValorTotalBruto(valorTotalBruto);
            modeloIndividual.setValorTotalImpuestosRetenciones(big.getMoneda(tblProductos.getValueAt(i, 9).toString()).add(big.getMoneda(tblProductos.getValueAt(i, 7).toString())));
            modeloIndividual.setCodigoVendedor(instancias.getSql().getIdEmpleado(cmbVendedor.getSelectedItem().toString()));
            modeloIndividual.setObservacionDetalle("");

            ModeloDescuentos[] resultadosDescuentos = obtenerDescuentosProducto(modeloIndividual, i);
            modeloIndividual.setDescuentoProducto(resultadosDescuentos);

            Object[][] impuestosProducto = obtenerImpuestosPorProducto(modeloIndividual, i);
            modeloIndividual.setImpuestosProducto(impuestosProducto);

            detalladoProductos[i] = modeloIndividual;
        }

        if (big.getMoneda(txtValorEfectivo.getText()).compareTo(BigDecimal.ZERO) > 0) {
            detalladoProductos[tblProductos.getRowCount()] = obtenerProductoEfectivo(notaCredito);
        }

        return detalladoProductos;
    }

    private ModeloFacturacionElectronica crearModeloFacturacionEletronica(String notaCredito, ModeloContacto datosCliente) {

        ModeloFacturacionElectronica modeloFacturacionElectronica = new ModeloFacturacionElectronica();
        modeloFacturacionElectronica.setDsPrefijo(obtenerPrefijoNotaCredito());
        modeloFacturacionElectronica.setDsNumeroFactura(notaCredito.replace("NC-", "").replace(obtenerPrefijoNotaCredito(), ""));
        modeloFacturacionElectronica.setDsVendedor(cmbVendedor.getSelectedItem().toString());
        modeloFacturacionElectronica.setFechaEmision(metodos.fecha4(metodosGenerales.fecha()) + " " + metodosGenerales.fechaHora().split(" ")[1]);
        modeloFacturacionElectronica.setFechaVencimiento(metodos.fecha4(metodosGenerales.fecha()));
        modeloFacturacionElectronica.setEmailAdquiriente(datosCliente.getEmail());
        modeloFacturacionElectronica.setTipoIdentificacionAdquiriente(enumTipoIdentificacion.obtenerTipoIdentificacion(datosCliente.getTipo()));
        modeloFacturacionElectronica.setIdentificacionAdquiriente(datosCliente.getId());
        modeloFacturacionElectronica.setDigitoVerificacionAdquiriente(datosCliente.getId().split("-")[1]);
        modeloFacturacionElectronica.setCodigoPostalAdquirente(datosCliente.getCodigoPostal());
        modeloFacturacionElectronica.setTipoPersonaAdquiriente(enumTipoPersona.obtenerTipoPersona(datosCliente.getNaturaleza()));

        if (datosCliente.getTipo().equals(enumTipoIdentificacion.TipoIdentificacion.NIT.getValue())) {
            modeloFacturacionElectronica.setNombresAdquiriente(datosCliente.getNombre());
            modeloFacturacionElectronica.setPrimerApellido(datosCliente.getNombre());
        } else {
            modeloFacturacionElectronica.setNombresAdquiriente(datosCliente.getpNombre());
            modeloFacturacionElectronica.setSegundoNombre(datosCliente.getsNombre());
            modeloFacturacionElectronica.setPrimerApellido(datosCliente.getpApellido());
            modeloFacturacionElectronica.setSegundoApellido(datosCliente.getsApellido());
        }

        modeloFacturacionElectronica.setDireccionAdquiriente(datosCliente.getDireccion());
        modeloFacturacionElectronica.setAdquirenteResponsable(datosCliente.isResponsableIva());
        modeloFacturacionElectronica.setRegimenAdquirente(obtenerRegimen(datosCliente.isResponsableIva()));
        modeloFacturacionElectronica.setTelefonoAdquiriente(datosCliente.getTelefono());

        Object[][] datosDepartamentoYCiudad = instancias.getSql().getCodigoLugar(datosCliente.getDepartamento(), datosCliente.getCiudad());
        if (null != datosDepartamentoYCiudad[0][1]) {
            String cdDaneCiudad = datosDepartamentoYCiudad[0][1].toString();
            modeloFacturacionElectronica.setCdDaneCiudad(cdDaneCiudad.substring(2, cdDaneCiudad.length()));
            modeloFacturacionElectronica.setDsNombreCiudad(datosCliente.getCiudad());
            modeloFacturacionElectronica.setCdDaneDepartamento(datosDepartamentoYCiudad[0][0].toString());
            modeloFacturacionElectronica.setDsNombreDepartamento(datosCliente.getDepartamento());
            modeloFacturacionElectronica.setCdIsoPais("CO");
            modeloFacturacionElectronica.setDsNombrePais(datosCliente.getPais());
        } else {
            alertas.alertFail("Revisar la ciudad y departamento del cliente");
            return null;
        }

        modeloFacturacionElectronica.setSnDistribucionFisica("N");
        modeloFacturacionElectronica.setValorNeto(big.getMoneda(txtTotal.getText().replace("Total: ", "")));
        modeloFacturacionElectronica.setDsObservacion(txtObservaciones.getText());
        modeloFacturacionElectronica.setTipoDocumentoElectronico("NOTA_CREDITO");

        BigDecimal porcentajeIva = big.getMoneda(txtTotalIva.getText()).divide(big.getMoneda(txtSubTotal.getText()), 2, BigDecimal.ROUND_HALF_UP).multiply(big.getBigDecimal(100));
        modeloFacturacionElectronica.setPorcentajeIva(porcentajeIva);

        BigDecimal porcentajeConsumo = big.getMoneda(txtTotalImpoconsumo.getText()).divide(big.getMoneda(txtSubTotal.getText()), 2, BigDecimal.ROUND_HALF_UP).multiply(big.getBigDecimal(100));
        modeloFacturacionElectronica.setPorcentajeConsumo(porcentajeConsumo);

        modeloFacturacionElectronica.setDsPorcentajeReteFuente(this.porcentajeReteFuente);
        modeloFacturacionElectronica.setDsRetencionFuente(big.getMoneda(txtRtf.getText()));
        modeloFacturacionElectronica.setDsPorcentajeReteIva(big.getMoneda(txtRiva.getText()).compareTo(BigDecimal.ZERO) > 0 ? big.getBigDecimal(15) : BigDecimal.ZERO);
        modeloFacturacionElectronica.setDsRetencionIva(big.getMoneda(txtRiva.getText()));

        BigDecimal porcentajeDescuento = big.getMoneda(txtTotalDescuentos.getText()).divide(big.getMoneda(txtSubTotal.getText()), 2, BigDecimal.ROUND_HALF_UP).multiply(big.getBigDecimal(100));
        modeloFacturacionElectronica.setDsPorcentajeDescuento(porcentajeDescuento);
        modeloFacturacionElectronica.setDsDescuento(big.getMoneda(txtTotalDescuentos.getText()));

        BigDecimal valorBaseImponible = BigDecimal.ZERO;
        for (int i = 0; i < tblProductos.getRowCount(); i++) {
            if (Integer.parseInt(tblProductos.getValueAt(i, 6).toString()) > 0 || Integer.parseInt(tblProductos.getValueAt(i, 8).toString()) > 0) {
                valorBaseImponible = valorBaseImponible.add(big.getMoneda(tblProductos.getValueAt(i, 4).toString()));
            }
        }

        if (big.getMoneda(txtValorEfectivo.getText()).compareTo(BigDecimal.ZERO) > 0) {
            valorBaseImponible = valorBaseImponible.add(big.getMoneda(txtValorEfectivo.getText()));
        }

        modeloFacturacionElectronica.setValorBaseImponible(valorBaseImponible);
        modeloFacturacionElectronica.setValorBrutoMasTributos(big.getMoneda(txtTotal.getText().replace("Total: ", "")).add(big.getMoneda(txtTotalDescuentos.getText())));
        modeloFacturacionElectronica.setDescuentoTotal(big.getMoneda(txtTotalDescuentos.getText()));
        modeloFacturacionElectronica.setCargoTotal(BigDecimal.ZERO);
        modeloFacturacionElectronica.setAnticipoTotal(BigDecimal.ZERO);
        modeloFacturacionElectronica.setValorTotalImpuestoConsumo(big.getMoneda(txtTotalImpoconsumo.getText()));
        modeloFacturacionElectronica.setMoneda("COP");
        modeloFacturacionElectronica.setValorBruto(big.getMoneda(txtSubTotal.getText()).add(big.getMoneda(txtTotalDescuentos.getText())));
        modeloFacturacionElectronica.setValorIva(big.getMoneda(txtTotalIva.getText()));

        String tipoOperacion = rdPos.isSelected() ? "NOTA_CREDITO_POS" : "NOTA_CREDITO_REFERENCIA";
        int tipoPlantilla = rdPos.isSelected() ? 2 : 1;
        modeloFacturacionElectronica.setTipoOperacion(tipoOperacion);
        modeloFacturacionElectronica.setCdTipoPlantilla(tipoPlantilla);

        modeloFacturacionElectronica.setVersionDian("2");
        modeloFacturacionElectronica.setResponsabilidadesFiscales(obtenerResponsabilidadesFiscales());

        modeloFacturacionElectronica.setPrefijoFacturaReferencia(obtenerDatosFactura()[0]);
        modeloFacturacionElectronica.setNumeroFacturaReferencia(obtenerDatosFactura()[1]);
        modeloFacturacionElectronica.setCufe(CUFE_FACTURA_ELECTRONICA);
        modeloFacturacionElectronica.setConceptoNotaCredito(cmbConcepto.getSelectedItem().toString());
        modeloFacturacionElectronica.setDescripcionNotaCredito(txtObservaciones.getText());

        ModeloDescuentos[] resultadosDescuentos = obtenerDescuentosFactura(modeloFacturacionElectronica.getDescuentoTotal());
        modeloFacturacionElectronica.setDescuentosFactura(resultadosDescuentos);

        Object[] informacionDePagos = obtenerInformacionDePagos();
        modeloFacturacionElectronica.setFormaPago(informacionDePagos[0].toString());
        modeloFacturacionElectronica.setMedioPago(informacionDePagos[1].toString());
        modeloFacturacionElectronica.setFechaVencimientoPago(informacionDePagos[2].toString());
        modeloFacturacionElectronica.setIdPago(informacionDePagos[3].toString());

        ModeloDetalleImpuestos resultadosImpuestos = obtenerImpuestosFacturas(this.porcentajeReteFuente);
        modeloFacturacionElectronica.setImpuestosFactura(resultadosImpuestos);

        ModeloDetalleProductos[] detalleProductos = obtenerDetalleProductos(notaCredito);
        modeloFacturacionElectronica.setDetalleProductos(detalleProductos);

        return modeloFacturacionElectronica;
    }

    private Object[] obtenerInformacionDePagos() {
        String minutosFactura = metodosGenerales.hora().split(":")[0];
        String segundosFactura = metodosGenerales.hora().split(":")[1];
        if (minutosFactura.length() == 1) {
            minutosFactura = "0" + minutosFactura;
        }

        if (segundosFactura.length() == 1) {
            segundosFactura = "0" + segundosFactura;
        }

        Object[] informacionMetodosPagos = new Object[4];
        if (rdReembolsarSi.isSelected()) {
            informacionMetodosPagos[0] = "CONTADO";
            informacionMetodosPagos[1] = "EFECTIVO";
            informacionMetodosPagos[2] = metodos.fechaConsulta(metodosGenerales.fecha()) + " " + minutosFactura + ":" + segundosFactura + ":00";
            informacionMetodosPagos[3] = "NC-DEVOL";
        } else {
            informacionMetodosPagos[0] = "CONTADO";
            informacionMetodosPagos[1] = "BONOS";
            informacionMetodosPagos[2] = metodos.fechaConsulta(metodosGenerales.fecha()) + " " + minutosFactura + ":" + segundosFactura + ":00";
            informacionMetodosPagos[3] = "NC-DEVOL";
        }

        return informacionMetodosPagos;
    }

    private ModeloDetalleImpuestos obtenerImpuestosFacturas(BigDecimal dsPorcentajeReteFuente) {

        List<Integer> ivas = new ArrayList<Integer>();
        List<Integer> impoconsumos = new ArrayList<Integer>();
        for (int i = 0; i < tblProductos.getRowCount(); i++) {
            int porcentajeIva = Integer.parseInt(tblProductos.getValueAt(i, 6).toString());
            int porcentajeConsumo = Integer.parseInt(tblProductos.getValueAt(i, 8).toString());

            if (porcentajeIva != 0 && !ivas.contains(porcentajeIva)) {
                ivas.add(porcentajeIva);
            }

            if (porcentajeConsumo != 0 && !impoconsumos.contains(porcentajeConsumo)) {
                impoconsumos.add(porcentajeConsumo);
            }
        }

        if (big.getMoneda(txtValorEfectivo.getText()).compareTo(BigDecimal.ZERO) > 0) {
            int ivaSeleccionado = Integer.parseInt(cmbPorcentajeIva.getSelectedItem().toString());
            if (ivaSeleccionado != 0 && !ivas.contains(ivaSeleccionado)) {
                ivas.add(ivaSeleccionado);
            }
        }

        Object[][] informacionImpuestoIva = new Object[ivas.size()][4];
        Object[][] informacionImpuestoImpoconsumo = new Object[impoconsumos.size()][4];
        Object[][] informacionReteIva = new Object[1][4];
        Object[][] informacionReteFuente = new Object[1][4];

        for (int i = 0; i < ivas.size(); i++) {
            BigDecimal subtotal = BigDecimal.ZERO;
            BigDecimal impuesto = BigDecimal.ZERO;
            for (int j = 0; j < tblProductos.getRowCount(); j++) {
                if (Integer.parseInt(tblProductos.getValueAt(j, 6).toString()) == ivas.get(i)) {
                    subtotal = subtotal.add(big.getMoneda(tblProductos.getValueAt(j, 4).toString()));
                    impuesto = impuesto.add(big.getMoneda(tblProductos.getValueAt(j, 7).toString()));
                }
            }

            if (big.getMoneda(txtValorEfectivo.getText()).compareTo(BigDecimal.ZERO) > 0
                    && ivas.get(i) == Integer.parseInt(cmbPorcentajeIva.getSelectedItem().toString())) {
                subtotal = subtotal.add(big.getMoneda(txtValorEfectivo.getText()));
                impuesto = impuesto.add(big.getMoneda(txtIvaEfectivo.getText()));
            }

            informacionImpuestoIva[i][0] = subtotal;
            informacionImpuestoIva[i][1] = impuesto;
            informacionImpuestoIva[i][2] = formatoDosDecimales.format(ivas.get(i)).replace(",", ".");
            informacionImpuestoIva[i][3] = "IVA";
        }

        for (int i = 0; i < impoconsumos.size(); i++) {
            BigDecimal subtotal = BigDecimal.ZERO;
            BigDecimal impuesto = BigDecimal.ZERO;
            for (int j = 0; j < tblProductos.getRowCount(); j++) {
                if (Integer.parseInt(tblProductos.getValueAt(j, 8).toString()) == impoconsumos.get(i)) {
                    subtotal = subtotal.add(big.getMoneda(tblProductos.getValueAt(j, 4).toString()));
                    impuesto = impuesto.add(big.getMoneda(tblProductos.getValueAt(j, 9).toString()));
                }
            }

            informacionImpuestoImpoconsumo[i][0] = subtotal;
            informacionImpuestoImpoconsumo[i][1] = impuesto;
            informacionImpuestoImpoconsumo[i][2] = formatoDosDecimales.format(impoconsumos.get(i)).replace(",", ".");
            informacionImpuestoImpoconsumo[i][3] = "INC";
        }

        if (big.getMoneda(txtRiva.getText()).compareTo(BigDecimal.ZERO) > 0) {
            informacionReteIva[0][0] = big.getMoneda(txtTotalIva.getText());
            informacionReteIva[0][1] = big.getMoneda(txtRiva.getText());
            informacionReteIva[0][2] = "15.00";
            informacionReteIva[0][3] = "RETE_IVA";
        }

        if (big.getMoneda(txtRtf.getText()).compareTo(BigDecimal.ZERO) > 0) {
            informacionReteFuente[0][0] = big.getMoneda(txtSubTotal.getText());
            informacionReteFuente[0][1] = big.getMoneda(txtRtf.getText());

            String porcentajeReteFuente = formatoDosDecimales.format(dsPorcentajeReteFuente).replace(",", ".");
            if (dsPorcentajeReteFuente.toString().startsWith("0")) {
                porcentajeReteFuente = "0" + formatoDosDecimales.format(dsPorcentajeReteFuente).replace(",", ".");
            }
            informacionReteFuente[0][2] = porcentajeReteFuente;
            informacionReteFuente[0][3] = "RETE_FUENTE";
        }

        ModeloDetalleImpuestos detalleImpuestos = new ModeloDetalleImpuestos();
        detalleImpuestos.setImpuestosIvas(informacionImpuestoIva);
        detalleImpuestos.setImpuestosImpoconsumo(informacionImpuestoImpoconsumo);
        detalleImpuestos.setImpuestosReteIva(informacionReteIva);
        detalleImpuestos.setImpuestosReteFuente(informacionReteFuente);

        return detalleImpuestos;
    }

    public void realizarNc() {

        if (instancias.getConfiguraciones().isFacturaElectronica()) {
            if (txtNit.getText().equals("1010")) {
                metodos.msgAdvertencia(this, "No se puede realizar la NC a este cliente");
                return;
            }

            if (txtObservaciones.getText().equals("")) {
                metodos.msgAdvertencia(null, "Debe ingresar la observación de la nota credito");
                return;
            }
        }

        if (txtNombre.getText().equals("")) {
            metodos.msgError(this, "Cargue una factura");
            txtFactura.requestFocus();
            return;
        }

        if (tblProductos.getRowCount() == 0 && txtValorEfectivo.getText().equals(this.simbolo + " 0")) {
            metodos.msgError(this, "No hay producto ni valor en efectivo");
            txtValorEfectivo.requestFocus();
            return;
        }

        double v2 = new Double(big.getMoneda(txtTotal.getText().replace("Total: ", "")).toString());
        double v3 = new Double(big.getMoneda(txtTotalNc.getText()).toString());

        if (v2 > v3) {
            Object[][] nc = instancias.getSql().getDatosNc("FACT-" + txtFactura.getText());

            if (nc.length > 0) {
                metodos.msgAdvertenciaAjustado(this, "Esta factura ya tiene nota creditos");
            } else {
                metodos.msgAdvertenciaAjustado(this, "Valor mayor al de la factura");
            }

            return;
        }

        String dsPrefijo = "";
        if (instancias.getIdNC() != null) {
            dsPrefijo = instancias.getIdNC();
        }

        String factura = "NC-" + dsPrefijo + instancias.getSql().getNumConsecutivo("NC")[0];

        if (instancias.getConfiguraciones().isFacturaElectronica() && (Boolean) tblComprobantes.getValueAt(0, 2) == true) {
            boolean facturaElectronicaExitosa = false;
            ModeloContacto datosCliente = instancias.getSql().getDatosTercero(txtIdSistema.getText());
            ModeloFacturacionElectronica modeloFacturacionElectronica = crearModeloFacturacionEletronica(factura, datosCliente);

            try {
                facturaElectronicaExitosa = consumidorFacturacionElectronica.generarFacturacionElectronica(modeloFacturacionElectronica, false, false, true, false);
            } catch (Exception ex) {
                System.err.println("Hubo un error al enviar el JSON de la factura electronica: " + ex);
            }

            if (!facturaElectronicaExitosa) {
                return;
            }
        }

        ndFactura nodoCaja = instancias.getSql().getDatosFactura("FACT-" + txtFactura.getText());

        //PROCESO GUARDAR VENTA
        for (int i = 0; i < tblProductos.getRowCount(); i++) {
            Object vectVenta[] = {nodoCaja.getFactura() + " NC", tblProductos.getValueAt(i, 0).toString().replace("'", "//"),
                big.getMoneda((String) tblProductos.getValueAt(i, 2)).multiply(big.getBigDecimal("-1")), "-" + tblProductos.getValueAt(i, 12),
                big.getMoneda((String) tblProductos.getValueAt(i, 5)).multiply(big.getBigDecimal("-1")),
                big.getMoneda((String) tblProductos.getValueAt(i, 10)).multiply(big.getBigDecimal("-1")),
                big.getMoneda((String) tblProductos.getValueAt(i, 7)).multiply(big.getBigDecimal("-1")),
                big.getMoneda((String) tblProductos.getValueAt(i, 4)).multiply(big.getBigDecimal("-1")),
                "NC-" + lbNoNC.getText(), "0", "0", tblProductos.getValueAt(i, 1), tblProductos.getValueAt(i, 11),
                "-" + tblProductos.getValueAt(i, 3), "REALIZADO", big.getBigDecimal(tblProductos.getValueAt(i, 6)).toString(),
                "", big.getMoneda("0"), "", "", tblProductos.getValueAt(i, 15), tblProductos.getValueAt(i, 16),
                big.getBigDecimal(tblProductos.getValueAt(i, 8)).toString(), big.getMoneda((String) tblProductos.getValueAt(i, 9)).multiply(big.getBigDecimal("-1"))};

            ndNotasCredito nodoVent = metodos.llenarNotasCredito(vectVenta);

            if (!instancias.getSql().agregarNotasCredito(nodoVent)) {
                metodos.msgError(this, "Error guardar la nota credito");
            }
        }

        if (!txtValorTotal.getText().equals(this.simbolo + " 0")) {
            ndProducto nodo = instancias.getSql().getDatosProducto("EFECTIVO", "bdProductos");
            Object vectVenta[] = {
                nodoCaja.getFactura() + " NC", nodo.getIdSistema(),
                big.getMoneda(txtValorEfectivo.getText()).multiply(big.getBigDecimal("-1")), "-" + 1, big.getMoneda("0"),
                big.getMoneda(txtValorTotal.getText()).add(big.getMoneda(txtIvaEfectivo.getText())).multiply(big.getBigDecimal("-1")),
                big.getMoneda(txtIvaEfectivo.getText()).multiply(big.getBigDecimal("-1")),
                big.getMoneda(txtValorEfectivo.getText()).multiply(big.getBigDecimal("-1")), "NC-" + lbNoNC.getText(),
                big.getBigDecimal("0"), "0", "EFECTIVO", "1", "1", "REALIZADO", cmbPorcentajeIva.getSelectedItem().toString(),
                "", big.getMoneda("0"), "", "", "", "", "0", "0"
            };

            ndNotasCredito nodoVent = metodos.llenarNotasCredito(vectVenta);

            if (!instancias.getSql().agregarNotasCredito(nodoVent)) {
                metodos.msgError(this, "Error al guardar efectivo NC");
            }
        }

        Object[] vector = {factura, nodoCaja.getCliente(), nodoCaja.getFactura(), big.getMoneda(txtTotal.getText().replace("Total: ", "")),
            big.getMoneda(txtTotal.getText().replace("Total: ", "")), false, instancias.getUsuario(), metodos.fechaConsulta(metodosGenerales.fecha()),
            txtVendedor.getText(), instancias.getTerminal(), nodoCaja.getResolucion(),
            rdReembolsarSi.isSelected(), metodosGenerales.hora(), txtBodega.getText(), txtObservaciones.getText()};

        ndNc nodo = metodos.llenarNc(vector);

        if (!instancias.getSql().agregarNc(nodo)) {
            metodos.msgError(this, "Error al guardar la NC");
            return;
        }

        for (int i = 0; i < tblProductos.getRowCount(); i++) {

            ndProducto producto = instancias.getSql().getDatosProducto(tblProductos.getValueAt(i, 0).toString(), "bdProductos");

            double cantidad;
            double inventario;
            double fisicoInventario;

            try {
                cantidad = Double.parseDouble(producto.getNc().replace(",", "."));
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

            inventario = inventario + Double.parseDouble(tblProductos.getValueAt(i, 12).toString().replace(",", "."));
            fisicoInventario = fisicoInventario + Double.parseDouble(tblProductos.getValueAt(i, 12).toString().replace(",", "."));
            double total = cantidad + Double.parseDouble(tblProductos.getValueAt(i, 12).toString().replace(",", "."));

            String inventario1 = String.valueOf(df.format(inventario)).replace(".", ",");
            String fisicoInventario1 = String.valueOf(df.format(fisicoInventario)).replace(".", ",");
            String total1 = String.valueOf(df.format(total)).replace(".", ",");

            String baseUtilizada = nodoCaja.getBodega();
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

            instancias.getSql().modificarInventario("nc", total1, tblProductos.getValueAt(i, 0).toString(), baseUtilizada);
            instancias.getSql().modificarInventario("inventario", inventario1, tblProductos.getValueAt(i, 0).toString(), baseUtilizada);
            instancias.getSql().modificarInventario("fisicoInventario", fisicoInventario1, tblProductos.getValueAt(i, 0).toString(), baseUtilizada);

            // DESCONTAR DEL INVENTARIO DETALLADO //
            if (instancias.getConfiguraciones().isProductosDetallados()) {
                String cod = tblProductos.getValueAt(i, 16).toString();

                if (!cod.equals("")) {

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

                    if (tipo.equals("Imei") || tipo.equals("Serial") || tipo.equals("SerialColor")) {
                        instancias.getSql().modificarEstadoDetalleProductos(cod, "DISPONIBLE");
                    } else {
                        Double cantidadActual = Double.parseDouble(instancias.getSql().getCantidadProductos(cod).replace(",", "."));
                        cantidadActual = cantidadActual + Double.parseDouble(tblProductos.getValueAt(i, 12).toString().replace(",", "."));
                        String cantidadFinal = String.valueOf(df.format(cantidadActual)).replace(".", ",");
                        instancias.getSql().modificarCantidadesDetalleProductos(cod, cantidadFinal);
                    }
                }
            }
            // FIN DE DESCONTAR DEL INVENTARIO SEPARADO // 
        }

        if (!instancias.getSql().aumentarConsecutivo("NC", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("NC")[0]) + 1)) {
            metodos.msgError(this, "Hubo un problema al guardar en el consecutivo de la nota credito");
        }

        //System.out.println("entonces ");
        lbNoNC.setText(dsPrefijo + (String) instancias.getSql().getNumConsecutivo("NC")[0]);
        metodos.msgExito(this, "Nota credito exitosa");
        String observaciones = txtObservaciones.getText();

        btnLimpiarActionPerformed(null);

        if (metodos.msgPregunta(this, "¿Desea imprimir?") == 0) {
            String infoEmpresa = metodosGenerales.convertToMultiline(instancias.getInformacionEmpresaReimpresion() + "" + nodo.getResolucion());
            instancias.getReporte().ver_Nc(factura, observaciones, infoEmpresa);
        }
        //System.out.println("como fueque");
        txtFactura.setText("");
    }

    public void consultarMaestros() {
        datos = instancias.getSql().getDatosMaestra();
    }

    public void setVendedores(String[] Vendedores) {
        cmbVendedor.removeAllItems();
        for (String Vendedore : Vendedores) {
            cmbVendedor.addItem(Vendedore);
        }
    }

    public void cargarTotales() {

        int i;
        BigDecimal subtotal = big.getBigDecimal("0"), iva = big.getBigDecimal("0"), impuesto = big.getBigDecimal("0"), total = big.getBigDecimal("0"),
                descuentos = big.getBigDecimal("0"), impoconsumo = big.getBigDecimal("0");

        for (i = 0; i < tblProductos.getRowCount(); i++) {
            subtotal = subtotal.add(big.getMoneda(String.valueOf(tblProductos.getValueAt(i, 4))));
            descuentos = descuentos.add(big.getMoneda(String.valueOf(tblProductos.getValueAt(i, 5))));
            impoconsumo = impoconsumo.add(big.getMoneda(String.valueOf(tblProductos.getValueAt(i, 9))));
            iva = iva.add(big.getMoneda(String.valueOf(tblProductos.getValueAt(i, 7))));
            total = total.add(big.getMoneda(String.valueOf(tblProductos.getValueAt(i, 10))));
        }

        //Le agregamos lo de efectivo
        BigDecimal ivaEfectivo = BigDecimal.ZERO;
        try {
            ivaEfectivo = big.getMoneda(txtIvaEfectivo.getText());
        } catch (Exception e) {
        }

        subtotal = subtotal.add(big.getMoneda(txtValorEfectivo.getText()));
        total = total.add(big.getMoneda(txtValorTotal.getText()));
        iva = iva.add(ivaEfectivo);

        if (this.porcentajeReteFuente.compareTo(BigDecimal.ZERO) > 0) {
            txtRtf.setText(big.setMonedaExacta(subtotal.multiply(this.porcentajeReteFuente).divide(big.getBigDecimal(100), 2, RoundingMode.HALF_UP)));
        }

        if (contieneReteIva) {
            txtRiva.setText(big.setMonedaExacta(iva.multiply(big.getBigDecimal(15)).divide(big.getBigDecimal(100), 2, RoundingMode.HALF_UP)));
        }

        txtSubTotal.setText(big.setMoneda(subtotal));
        txtTotalIva.setText(big.setMoneda(iva));
        txtTotalImpoconsumo.setText(big.setMoneda(impoconsumo));
        txtTotalDescuentos.setText(big.setMoneda(descuentos));
        txtTotal.setText("Total: " + big.setMoneda(total));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnular;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnReimprimir;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cmbConcepto;
    private javax.swing.JComboBox cmbListas;
    private javax.swing.JComboBox<String> cmbPorcentajeIva;
    private javax.swing.JComboBox cmbVendedor;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JLabel lbCargar;
    private javax.swing.JLabel lbCodigo;
    private javax.swing.JLabel lbCodigo1;
    private javax.swing.JLabel lbCodigo2;
    private javax.swing.JLabel lbCreditos;
    private javax.swing.JLabel lbDireccion1;
    private javax.swing.JLabel lbFacturaNo;
    private javax.swing.JLabel lbImpoconsumo;
    private javax.swing.JLabel lbIva;
    private javax.swing.JLabel lbNit;
    private javax.swing.JLabel lbNit1;
    private javax.swing.JLabel lbNoNC;
    private javax.swing.JLabel lbReteIva;
    private javax.swing.JLabel lbRtf;
    private javax.swing.JLabel lbSubtotal1;
    private javax.swing.JLabel lbTotalDescuento;
    private javax.swing.JLabel lbTotalDescuento1;
    private javax.swing.JLabel lbTotalDescuento2;
    private javax.swing.JLabel lbVendedor;
    private javax.swing.JLabel lbVendedor1;
    private javax.swing.JLabel lbVendedor3;
    private javax.swing.JPanel pnlFormulario;
    private javax.swing.JPanel pnlInvisible;
    private javax.swing.JMenuItem popBorrar;
    private javax.swing.JRadioButton rdPos;
    private javax.swing.JRadioButton rdReembolsarNo;
    private javax.swing.JRadioButton rdReembolsarSi;
    private javax.swing.JScrollPane scrFormulario;
    private javax.swing.JScrollPane scrProductos;
    private javax.swing.JTable tblComprobantes;
    private javax.swing.JTable tblProductos;
    private javax.swing.JTextField txtBodega;
    private javax.swing.JTextField txtBodega2;
    private javax.swing.JLabel txtCantProductos;
    private javax.swing.JLabel txtCantUnidades;
    private javax.swing.JTextField txtFactura;
    private javax.swing.JTextField txtIdSistema;
    private javax.swing.JLabel txtIva3;
    private javax.swing.JTextField txtIvaEfectivo;
    private javax.swing.JTextField txtNit;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextArea txtObservaciones;
    private javax.swing.JLabel txtRiva;
    private javax.swing.JLabel txtRtf;
    private javax.swing.JTextField txtRtfPorc;
    private javax.swing.JLabel txtSubTotal;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JLabel txtTotal;
    private javax.swing.JLabel txtTotalDescuentos;
    private javax.swing.JLabel txtTotalImpoconsumo;
    private javax.swing.JLabel txtTotalIva;
    private javax.swing.JTextField txtTotalNc;
    private javax.swing.JTextField txtValorEfectivo;
    private javax.swing.JTextField txtValorTotal;
    private javax.swing.JTextField txtVendedor;
    // End of variables declaration//GEN-END:variables
}
