package Vista.Cartera;

import Consumidor.FacturacionElectronica.consumidorFacturacionElectronica;
import Controlador.Alertas.ControladorAlertas;
import Enums.TipoDocumento;
import Enums.enumBodegas;
import Enums.enumTipoIdentificacion;
import Enums.enumTipoPersona;
import Modelo.DocumentosElectronicos.ModeloDescuentos;
import Modelo.DocumentosElectronicos.ModeloDetalleProductos;
import Modelo.DocumentosElectronicos.ModeloDetalleImpuestos;
import Modelo.FacturacionElectronica.Entrada.ModeloFacturacionElectronica;
import Modelo.Inventario.DetalleProducto;
import Modelo.Inventario.MovimientoInventario;
import Modelo.Inventario.UltimoPonderado;
import clases.Cartera.ndCxc;
import clases.IconCellRenderer;
import clases.Instancias;
import Utilidades.Constantes;
import Validaciones.Facturacion.squemaFacturacion;
import Validaciones.FacturacionElectronica.squemaFacturacionElectronica;
import Vista.Ventas.VistaDevuelta;
import Vista.Ventas.VistaMetodoPagos;
import clases.Ventas.ndFactura;
import clases.big;
import clases.metodosGenerales;
import clases.productos.ndProducto;
import Modelo.Terceros.ModeloContacto;
import Modelo.Ventas.OpcionPreparacion;
import Utilidades.Ventas.ParserPreparacion;
import inventario.servicio.ServicioActualizacionPonderado;
import inventario.servicio.ServicioInventario;
import Utilidades.Utilidades;
import Vista.Productos.VistaInventarioInicial;
import formularios.Ventas.dlgInformacionCliente;
import formularios.Ventas.dlgProductosSinInventario;
import Vista.Ventas.VistaProductosSinUtilidad;
import formularios.Ventas.dlgTipoDescuento;
import formularios.productos.buscProductos;
import inventario.vista.VistaMovimientoDetalleProducto;
import formularios.productos.seleccionarPLU;
import formularios.terceros.buscBodegas;
import formularios.terceros.buscClientes;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

public class VistaNotaDebito extends javax.swing.JInternalFrame {

    private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension dimBarra = null;

    Instancias instancias;
    metodosGenerales metodos = new metodosGenerales();
    private DecimalFormat formatoDosDecimales = new DecimalFormat("#.00");

    private ControladorAlertas alertas = new ControladorAlertas();
    private squemaFacturacion squemaFacturacion = new squemaFacturacion();
    private squemaFacturacionElectronica squemaFacturacionElectronica = new squemaFacturacionElectronica();
    private consumidorFacturacionElectronica consumidorFacturacionElectronica = new consumidorFacturacionElectronica();

    private ServicioActualizacionPonderado servicioActualizacionPonderado = new ServicioActualizacionPonderado();

    Object[] datos;

    int cantDias = 0;
    String valorFila = null, simbolo = "";
    Boolean plu = false, saltarPasosFactura = false;
    DefaultTableModel modeloPro;
    DefaultTableModel modeloInventario;
    DecimalFormat df = new DecimalFormat("#.00");

    public Boolean getPlu() {
        return plu;
    }

    public void setPlu(Boolean plu) {
        this.plu = plu;
    }

    public int getCantDias() {
        return cantDias;
    }

    public void setCantDias(int cantDias) {
        this.cantDias = cantDias;
    }

    public VistaNotaDebito() {
        initComponents();

        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
        dimBarra = Barra.getPreferredSize();
        Barra.setSize(0, 0);
        Barra.setPreferredSize(new Dimension(0, 0));
        setBorder(null);
        repaint();

        instancias = Instancias.getInstancias();
        this.simbolo = instancias.getSimbolo();

        pnlOcultar.setVisible(false);
        String dsPrefijo = "";
        if (instancias.getIdND() != null) {
            dsPrefijo = instancias.getIdND();
        }

        lbNoFactura.setText(dsPrefijo + instancias.getSql().getNumConsecutivo("ND")[0].toString());

        consultarMaestros();

        txtIdSistema.setVisible(false);
        tblProductos.setDefaultRenderer(Object.class, new IconCellRenderer());
        tblProductos.getColumnModel().getColumn(1).setCellRenderer(new WordWrapCellRenderer());

        if (!instancias.getConfiguraciones().isInventarioBodegas()) {
            lbBodega.setVisible(false);
            txtBodega.setVisible(false);
        }

        if (instancias.getConfiguraciones().getTipoImpresion().equals("Sin-Codigo")) {
            tblProductos.getColumnModel().getColumn(0).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(0).setMaxWidth(0);
        }

        try {
            if (datos[17].equals("SI")) {
                if (tblProductos.getColumnModel().getColumnCount() > 0) {
                    tblProductos.getColumnModel().getColumn(10).setMinWidth(50);
//                    tblProductos.getColumnModel().getColumn(10).setPreferredWidth(150);
                    tblProductos.getColumnModel().getColumn(10).setMaxWidth(125);
                }
            } else {
                tblProductos.getColumnModel().getColumn(10).setMinWidth(0);
                tblProductos.getColumnModel().getColumn(10).setPreferredWidth(0);
                tblProductos.getColumnModel().getColumn(10).setMaxWidth(0);
            }
        } catch (Exception e) {
        }

        if (instancias.getConfiguraciones().isProductosDetallados()) {
            tblProductos.getColumnModel().getColumn(27).setMinWidth(100);
            tblProductos.getColumnModel().getColumn(27).setPreferredWidth(200);
            tblProductos.getColumnModel().getColumn(27).setMaxWidth(300);
        } else {
            if (instancias.getConfiguraciones().isParqueadero()) {
                tblProductos.getColumnModel().getColumn(27).setMinWidth(100);
                tblProductos.getColumnModel().getColumn(27).setPreferredWidth(200);
                tblProductos.getColumnModel().getColumn(27).setMaxWidth(300);
            } else {
                tblProductos.getColumnModel().getColumn(27).setMinWidth(0);
                tblProductos.getColumnModel().getColumn(27).setPreferredWidth(0);
                tblProductos.getColumnModel().getColumn(27).setMaxWidth(0);
            }
        }

        if (datos[6].toString().equals("porcentaje")) {
            tblProductos.getColumnModel().getColumn(5).setMinWidth(45);
            tblProductos.getColumnModel().getColumn(5).setPreferredWidth(45);
            tblProductos.getColumnModel().getColumn(5).setMaxWidth(45);
            tblProductos.getColumnModel().getColumn(6).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(6).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(6).setMaxWidth(0);
        } else {
            tblProductos.getColumnModel().getColumn(5).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(5).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(5).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(6).setMinWidth(100);
            tblProductos.getColumnModel().getColumn(6).setPreferredWidth(100);
            tblProductos.getColumnModel().getColumn(6).setMaxWidth(100);
        }

        tblInventario.removeEditor();
        modeloPro = (DefaultTableModel) tblProductos.getModel();

//        combobox en tabla de inventario
        TableColumn tc = tblInventario.getColumnModel().getColumn(0);
        TableCellEditor tce = new DefaultCellEditor(cmbListas);
        tc.setCellEditor(tce);

        txtFechaFactura.setText(metodosGenerales.fecha());
        txtVencimiento.setText(metodosGenerales.fecha());
        setBorder(null);
        repaint();

        txtNit.requestFocus();
        btnReImprimir.setVisible(false);
        tblComprobantes.setValueAt(true, 0, 2);
        tblComprobantes.setRowSelectionInterval(0, 0);

        if (!instancias.getConfiguraciones().isFacturaElectronica()) {
            rdPos.setVisible(false);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        popBorrar = new javax.swing.JMenuItem();
        scrFormulario = new javax.swing.JScrollPane();
        pnlFormulario = new javax.swing.JPanel();
        pnlInformacion = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        lbNit = new javax.swing.JLabel();
        txtNit = new javax.swing.JTextField();
        btnBuscTerceros = new javax.swing.JButton();
        lbNit1 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        btnBuscTerceros1 = new javax.swing.JButton();
        cmbVendedor = new javax.swing.JComboBox();
        lbVendedor = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        lbFacturaNo = new javax.swing.JTextField();
        lbNoFactura = new javax.swing.JTextField();
        lbDiasPlazo = new javax.swing.JLabel();
        txtDiasPlazo = new javax.swing.JTextField();
        lbRelacion = new javax.swing.JLabel();
        txtRelacion = new javax.swing.JTextField();
        lbRelacion1 = new javax.swing.JLabel();
        txtFactRelacion = new javax.swing.JTextField();
        rdPos = new javax.swing.JRadioButton();
        pnlComprobante = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        tblComprobantes = new javax.swing.JTable();
        lbTipoOperacion1 = new javax.swing.JLabel();
        cmbConcepto = new javax.swing.JComboBox();
        pnlOcultar = new javax.swing.JPanel();
        txtIdSistema = new javax.swing.JTextField();
        cmbListas = new javax.swing.JComboBox();
        txtFechaFactura = new javax.swing.JTextField();
        txtCartera = new javax.swing.JTextField();
        cmbListaPrecio = new javax.swing.JComboBox();
        txtVencimiento = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        btnLimpiar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnReImprimir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtObservaciones = new javax.swing.JTextArea();
        lbObservaciones = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        txtTotal = new javax.swing.JLabel();
        lbSubtotal = new javax.swing.JLabel();
        txtSubTotal = new javax.swing.JLabel();
        txtIva = new javax.swing.JLabel();
        txtTotalIva = new javax.swing.JLabel();
        lbTotalDescuento = new javax.swing.JLabel();
        txtTotalDescuentos = new javax.swing.JLabel();
        lbImpuestoBolsa = new javax.swing.JLabel();
        lbImpoconsumo = new javax.swing.JLabel();
        txtImpuesto = new javax.swing.JLabel();
        txtTotalImpoconsumo = new javax.swing.JLabel();
        cmbRtf = new javax.swing.JComboBox();
        txtRtf = new javax.swing.JLabel();
        chkReteIva = new javax.swing.JCheckBox();
        txtRiva = new javax.swing.JLabel();
        lbTotalDescuento1 = new javax.swing.JLabel();
        txtCantUnidades = new javax.swing.JLabel();
        lbTotalDescuento2 = new javax.swing.JLabel();
        txtCantProductos = new javax.swing.JLabel();
        lbCar = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        scrProductos1 = new javax.swing.JScrollPane();
        tblProductos = new javax.swing.JTable();
        lbProducto = new javax.swing.JLabel();
        txtCodProducto = new javax.swing.JTextField();
        btnBusProd = new javax.swing.JButton();
        scrInventario = new javax.swing.JScrollPane();
        tblInventario = new javax.swing.JTable();
        lbProducto1 = new javax.swing.JLabel();
        txtCant = new javax.swing.JTextField();
        txtPorcentaje = new javax.swing.JTextField();
        lbVendedor1 = new javax.swing.JLabel();
        lbBodega = new javax.swing.JLabel();
        txtBodega = new javax.swing.JTextField();

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

        pnlInformacion.setBackground(new java.awt.Color(255, 255, 255));
        pnlInformacion.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        lbNit.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit.setText("CC/Nit");
        lbNit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbNitMouseClicked(evt);
            }
        });
        lbNit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lbNitKeyReleased(evt);
            }
        });

        txtNit.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtNit.setName("CC/NIT"); // NOI18N
        txtNit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNitActionPerformed(evt);
            }
        });
        txtNit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNitKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNitKeyTyped(evt);
            }
        });

        btnBuscTerceros.setBackground(new java.awt.Color(204, 204, 204));
        btnBuscTerceros.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnBuscTerceros.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscTerceros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar1.png"))); // NOI18N
        btnBuscTerceros.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnBuscTerceros.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnBuscTerceros.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnBuscTerceros.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnBuscTerceros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscTercerosActionPerformed(evt);
            }
        });

        lbNit1.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit1.setText("Razón social");
        lbNit1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lbNit1KeyReleased(evt);
            }
        });

        txtNombre.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtNombre.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtNombre.setEnabled(false);
        txtNombre.setName("Cliente"); // NOI18N
        txtNombre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtNombreMouseClicked(evt);
            }
        });

        btnBuscTerceros1.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscTerceros1.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnBuscTerceros1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscarInfo.png"))); // NOI18N
        btnBuscTerceros1.setBorder(null);
        btnBuscTerceros1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnBuscTerceros1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnBuscTerceros1.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnBuscTerceros1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscTerceros1ActionPerformed(evt);
            }
        });

        cmbVendedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbVendedorMouseClicked(evt);
            }
        });

        lbVendedor.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbVendedor.setText("Vendedor");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmbVendedor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(txtNit, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscTerceros, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbNit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbVendedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbNit1)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addComponent(btnBuscTerceros1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(lbNit, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnBuscTerceros)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtNit)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnBuscTerceros1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(lbNit1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(lbVendedor)
                .addGap(3, 3, 3)
                .addComponent(cmbVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        lbFacturaNo.setEditable(false);
        lbFacturaNo.setBackground(new java.awt.Color(255, 255, 255));
        lbFacturaNo.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbFacturaNo.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        lbFacturaNo.setText("Nota Debito No.");
        lbFacturaNo.setBorder(null);
        lbFacturaNo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lbFacturaNoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                lbFacturaNoKeyTyped(evt);
            }
        });

        lbNoFactura.setEditable(false);
        lbNoFactura.setBackground(new java.awt.Color(255, 255, 255));
        lbNoFactura.setFont(new java.awt.Font("Century Gothic", 1, 22)); // NOI18N
        lbNoFactura.setForeground(new java.awt.Color(255, 0, 0));
        lbNoFactura.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lbNoFactura.setText("1");
        lbNoFactura.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lbNoFactura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lbNoFacturaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                lbNoFacturaKeyTyped(evt);
            }
        });

        lbDiasPlazo.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbDiasPlazo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbDiasPlazo.setText("Días de Plazo:");
        lbDiasPlazo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbDiasPlazoMouseClicked(evt);
            }
        });

        txtDiasPlazo.setBackground(new java.awt.Color(255, 204, 204));
        txtDiasPlazo.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        txtDiasPlazo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDiasPlazo.setText("0");
        txtDiasPlazo.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtDiasPlazo.setName("Plazo"); // NOI18N
        txtDiasPlazo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDiasPlazoActionPerformed(evt);
            }
        });
        txtDiasPlazo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDiasPlazoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDiasPlazoKeyTyped(evt);
            }
        });

        lbRelacion.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lbRelacion.setText("Factura relacionada:");

        txtRelacion.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        txtRelacion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtRelacion.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtRelacion.setName("Plazo"); // NOI18N
        txtRelacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtRelacionKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRelacionKeyTyped(evt);
            }
        });

        lbRelacion1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lbRelacion1.setText("Factura válida:");

        txtFactRelacion.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        txtFactRelacion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFactRelacion.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtFactRelacion.setEnabled(false);
        txtFactRelacion.setName("Plazo"); // NOI18N
        txtFactRelacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFactRelacionKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFactRelacionKeyTyped(evt);
            }
        });

        rdPos.setText("NOTA DÉBITO POS");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rdPos)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbRelacion1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbDiasPlazo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbFacturaNo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(lbRelacion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtDiasPlazo)
                            .addComponent(lbNoFactura, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                            .addComponent(txtRelacion)
                            .addComponent(txtFactRelacion))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbNoFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbFacturaNo))
                .addGap(2, 2, 2)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtDiasPlazo, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbDiasPlazo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(2, 2, 2)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtRelacion, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbRelacion, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFactRelacion, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbRelacion1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdPos)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlComprobante.setBackground(new java.awt.Color(255, 255, 255));

        tblComprobantes.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        tblComprobantes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, "NOTA DEBITO ELECTRÓNICA", null},
                {null, "NOTA DEBITO ADMINISTRATIVA", null}
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
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tblComprobantesMouseEntered(evt);
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

        lbTipoOperacion1.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbTipoOperacion1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbTipoOperacion1.setText("Concepto:");

        cmbConcepto.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cmbConcepto.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECCIONAR...", "INTERESES", "GASTOS_COBRAR", "CAMBIO_VALOR", "OTROS" }));

        javax.swing.GroupLayout pnlComprobanteLayout = new javax.swing.GroupLayout(pnlComprobante);
        pnlComprobante.setLayout(pnlComprobanteLayout);
        pnlComprobanteLayout.setHorizontalGroup(
            pnlComprobanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
            .addGroup(pnlComprobanteLayout.createSequentialGroup()
                .addComponent(lbTipoOperacion1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbConcepto, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlComprobanteLayout.setVerticalGroup(
            pnlComprobanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlComprobanteLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlComprobanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmbConcepto, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(lbTipoOperacion1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlInformacionLayout = new javax.swing.GroupLayout(pnlInformacion);
        pnlInformacion.setLayout(pnlInformacionLayout);
        pnlInformacionLayout.setHorizontalGroup(
            pnlInformacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInformacionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addComponent(pnlComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlInformacionLayout.setVerticalGroup(
            pnlInformacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInformacionLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(pnlInformacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(pnlComprobante, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        txtIdSistema.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        txtIdSistema.setForeground(new java.awt.Color(255, 51, 51));
        txtIdSistema.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtIdSistema.setDisabledTextColor(new java.awt.Color(255, 51, 51));
        txtIdSistema.setEnabled(false);
        txtIdSistema.setName("CC/NIT"); // NOI18N
        txtIdSistema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdSistemaActionPerformed(evt);
            }
        });
        txtIdSistema.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIdSistemaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIdSistemaKeyTyped(evt);
            }
        });

        cmbListas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "L1", "L2", "L3", "L4", "L5", "L6", "L7", "L8" }));
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

        txtFechaFactura.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        txtFechaFactura.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFechaFactura.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtFechaFactura.setEnabled(false);
        txtFechaFactura.setName("Plazo"); // NOI18N
        txtFechaFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaFacturaActionPerformed(evt);
            }
        });
        txtFechaFactura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFechaFacturaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFechaFacturaKeyTyped(evt);
            }
        });

        txtCartera.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        txtCartera.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCartera.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtCartera.setEnabled(false);
        txtCartera.setName("Plazo"); // NOI18N
        txtCartera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCarteraActionPerformed(evt);
            }
        });
        txtCartera.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCarteraKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCarteraKeyTyped(evt);
            }
        });

        cmbListaPrecio.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "L1", "L2", "L3", "L4", "L5", "L6", "L7", "L8" }));

        txtVencimiento.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        txtVencimiento.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtVencimiento.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtVencimiento.setEnabled(false);
        txtVencimiento.setName("Plazo"); // NOI18N
        txtVencimiento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtVencimientoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtVencimientoKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout pnlOcultarLayout = new javax.swing.GroupLayout(pnlOcultar);
        pnlOcultar.setLayout(pnlOcultarLayout);
        pnlOcultarLayout.setHorizontalGroup(
            pnlOcultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOcultarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtIdSistema, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbListas, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFechaFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCartera, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(120, 120, 120)
                .addComponent(cmbListaPrecio, 0, 1, Short.MAX_VALUE)
                .addGap(386, 386, 386)
                .addComponent(txtVencimiento)
                .addContainerGap())
        );
        pnlOcultarLayout.setVerticalGroup(
            pnlOcultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOcultarLayout.createSequentialGroup()
                .addGroup(pnlOcultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlOcultarLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(pnlOcultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtIdSistema, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlOcultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cmbListas, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtFechaFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtCartera, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(pnlOcultarLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlOcultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbListaPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtVencimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        btnLimpiar.setBackground(new java.awt.Color(204, 204, 204));
        btnLimpiar.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/limpiar.png"))); // NOI18N
        btnLimpiar.setText("LIMPIAR");
        btnLimpiar.setToolTipText("Ctrl+L");
        btnLimpiar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnLimpiar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnLimpiar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnLimpiar.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        btnGuardar.setBackground(new java.awt.Color(46, 204, 113));
        btnGuardar.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
        btnGuardar.setText("GUARDAR");
        btnGuardar.setToolTipText("Ctrl+G");
        btnGuardar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
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

        btnReImprimir.setBackground(new java.awt.Color(247, 220, 111));
        btnReImprimir.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnReImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/imprimir.png"))); // NOI18N
        btnReImprimir.setText("REIMPRIMIR");
        btnReImprimir.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnReImprimir.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnReImprimir.setEnabled(false);
        btnReImprimir.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnReImprimir.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnReImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReImprimirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(btnReImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );

        txtObservaciones.setColumns(20);
        txtObservaciones.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtObservaciones.setLineWrap(true);
        txtObservaciones.setRows(2);
        txtObservaciones.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtObservacionesKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(txtObservaciones);

        lbObservaciones.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbObservaciones.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbObservaciones.setText("Observaciones");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        txtTotal.setFont(new java.awt.Font("Century Gothic", 1, 30)); // NOI18N
        txtTotal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        txtTotal.setText("Total: 0");

        lbSubtotal.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbSubtotal.setText("Subtotal:");

        txtSubTotal.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txtSubTotal.setText("0");

        txtIva.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txtIva.setText("IVA:");

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

        lbImpuestoBolsa.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbImpuestoBolsa.setText("Imp. Bolsa:");

        lbImpoconsumo.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbImpoconsumo.setText("Impoconsumo:");

        txtImpuesto.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txtImpuesto.setText("0");

        txtTotalImpoconsumo.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txtTotalImpoconsumo.setText("0");

        cmbRtf.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        cmbRtf.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "RtF", "0.5", "1", "1.5", "2", "2.5", "3", "3.5", "4", "6", "7", "10", "20" }));
        cmbRtf.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbRtfItemStateChanged(evt);
            }
        });
        cmbRtf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbRtfActionPerformed(evt);
            }
        });

        txtRtf.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txtRtf.setText("0");

        chkReteIva.setBackground(new java.awt.Color(255, 255, 255));
        chkReteIva.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        chkReteIva.setText("Rete Iva");
        chkReteIva.setAlignmentY(0.0F);
        chkReteIva.setBorder(null);
        chkReteIva.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkReteIva.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        chkReteIva.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkReteIvaItemStateChanged(evt);
            }
        });
        chkReteIva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkReteIvaActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lbSubtotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtIva, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbTotalDescuento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbTotalDescuento1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbTotalDescuento2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTotalIva, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtSubTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTotalDescuentos, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCantUnidades, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtCantProductos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(38, 38, 38)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(cmbRtf, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(14, 14, 14))
                                    .addComponent(lbImpuestoBolsa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtImpuesto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(txtRtf, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(chkReteIva, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbImpoconsumo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTotalImpoconsumo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtRiva, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addComponent(txtTotal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 528, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(txtTotal)
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbImpuestoBolsa, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtImpuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbImpoconsumo)
                            .addComponent(txtTotalImpoconsumo, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(chkReteIva, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtRiva)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTotalIva)
                            .addComponent(txtIva, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbTotalDescuento)
                            .addComponent(txtTotalDescuentos))))
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cmbRtf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtRtf, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbTotalDescuento1)
                            .addComponent(txtCantUnidades))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbTotalDescuento2)
                            .addComponent(txtCantProductos))))
                .addGap(5, 5, 5))
        );

        lbCar.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbCar.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbCar.setText("300 ");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(lbObservaciones)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbCar))
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbObservaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbCar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 18))); // NOI18N

        tblProductos.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        tblProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Descripción", "Valor/Unit", "Cant.", "Subtotal", "Desc %", "Desc", "Iva %", "Impo", "Total", "Ubicación", "Referencia", "plu", "cant2", "ponderado", "Utilidad", "Estado", "Copago", "datoGrupo", "Pago Tercero", "Utilidad1", "Orden/Aviso", "Borrar", "Impo %", "Orden", "Aviso", "F. Entrega", "Detalle", "Lote", "IdProd", "paraComanda", "permisoDesc", "idSistema", "Iva", "Grupo", "Estandar", "Medida", "ControlInv"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, false, true, true, false, true, false, false, false, false, false, false, false, false, true, false, true, true, true, false, true, true, true, true, false, false, false, true, true, true, true, true, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblProductos.setComponentPopupMenu(jPopupMenu1);
        tblProductos.setMinimumSize(new java.awt.Dimension(105, 203));
        tblProductos.setRowHeight(31);
        tblProductos.getTableHeader().setReorderingAllowed(false);
        tblProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductosMouseClicked(evt);
            }
        });
        tblProductos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblProductosKeyReleased(evt);
            }
        });
        scrProductos1.setViewportView(tblProductos);
        if (tblProductos.getColumnModel().getColumnCount() > 0) {
            tblProductos.getColumnModel().getColumn(0).setMinWidth(50);
            tblProductos.getColumnModel().getColumn(0).setPreferredWidth(100);
            tblProductos.getColumnModel().getColumn(0).setMaxWidth(200);
            tblProductos.getColumnModel().getColumn(1).setMinWidth(200);
            tblProductos.getColumnModel().getColumn(2).setMinWidth(80);
            tblProductos.getColumnModel().getColumn(2).setPreferredWidth(100);
            tblProductos.getColumnModel().getColumn(2).setMaxWidth(150);
            tblProductos.getColumnModel().getColumn(3).setMinWidth(35);
            tblProductos.getColumnModel().getColumn(3).setPreferredWidth(35);
            tblProductos.getColumnModel().getColumn(3).setMaxWidth(35);
            tblProductos.getColumnModel().getColumn(4).setMinWidth(80);
            tblProductos.getColumnModel().getColumn(4).setPreferredWidth(100);
            tblProductos.getColumnModel().getColumn(4).setMaxWidth(150);
            tblProductos.getColumnModel().getColumn(5).setMinWidth(50);
            tblProductos.getColumnModel().getColumn(5).setPreferredWidth(50);
            tblProductos.getColumnModel().getColumn(5).setMaxWidth(50);
            tblProductos.getColumnModel().getColumn(6).setMinWidth(100);
            tblProductos.getColumnModel().getColumn(6).setPreferredWidth(100);
            tblProductos.getColumnModel().getColumn(6).setMaxWidth(100);
            tblProductos.getColumnModel().getColumn(7).setMinWidth(35);
            tblProductos.getColumnModel().getColumn(7).setPreferredWidth(35);
            tblProductos.getColumnModel().getColumn(7).setMaxWidth(35);
            tblProductos.getColumnModel().getColumn(8).setMinWidth(80);
            tblProductos.getColumnModel().getColumn(8).setPreferredWidth(100);
            tblProductos.getColumnModel().getColumn(8).setMaxWidth(140);
            tblProductos.getColumnModel().getColumn(9).setMinWidth(80);
            tblProductos.getColumnModel().getColumn(9).setPreferredWidth(100);
            tblProductos.getColumnModel().getColumn(9).setMaxWidth(150);
            tblProductos.getColumnModel().getColumn(10).setMinWidth(50);
            tblProductos.getColumnModel().getColumn(10).setMaxWidth(125);
            tblProductos.getColumnModel().getColumn(11).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(11).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(11).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(12).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(12).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(12).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(13).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(13).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(13).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(14).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(14).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(14).setMaxWidth(0);
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
            tblProductos.getColumnModel().getColumn(22).setMinWidth(40);
            tblProductos.getColumnModel().getColumn(22).setPreferredWidth(40);
            tblProductos.getColumnModel().getColumn(22).setMaxWidth(40);
            tblProductos.getColumnModel().getColumn(23).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(23).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(23).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(24).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(24).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(24).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(25).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(25).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(25).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(26).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(26).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(26).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(27).setMinWidth(100);
            tblProductos.getColumnModel().getColumn(27).setPreferredWidth(250);
            tblProductos.getColumnModel().getColumn(27).setMaxWidth(300);
            tblProductos.getColumnModel().getColumn(28).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(28).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(28).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(29).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(29).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(29).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(30).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(30).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(30).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(31).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(31).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(31).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(32).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(32).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(32).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(33).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(33).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(33).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(34).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(34).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(34).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(35).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(35).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(35).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(36).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(36).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(36).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(37).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(37).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(37).setMaxWidth(0);
        }

        lbProducto.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbProducto.setText("Producto:");
        lbProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbProductoMouseClicked(evt);
            }
        });
        lbProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lbProductoKeyReleased(evt);
            }
        });

        txtCodProducto.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        txtCodProducto.setName("combo"); // NOI18N
        txtCodProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodProductoActionPerformed(evt);
            }
        });
        txtCodProducto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCodProductoFocusGained(evt);
            }
        });
        txtCodProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodProductoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodProductoKeyReleased(evt);
            }
        });

        btnBusProd.setBackground(new java.awt.Color(204, 204, 204));
        btnBusProd.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnBusProd.setForeground(new java.awt.Color(255, 255, 255));
        btnBusProd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar1.png"))); // NOI18N
        btnBusProd.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnBusProd.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnBusProd.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnBusProd.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnBusProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBusProdActionPerformed(evt);
            }
        });

        tblInventario.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        tblInventario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "Actual", "Final"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblInventario.setToolTipText("Doble Click para cambiar la lista");
        tblInventario.setMinimumSize(new java.awt.Dimension(45, 203));
        tblInventario.setRowHeight(31);
        tblInventario.getTableHeader().setReorderingAllowed(false);
        tblInventario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblInventarioMouseClicked(evt);
            }
        });
        scrInventario.setViewportView(tblInventario);

        lbProducto1.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbProducto1.setText("Cant:");
        lbProducto1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lbProducto1KeyReleased(evt);
            }
        });

        txtCant.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        txtCant.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCant.setText("1");
        txtCant.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtCant.setEnabled(false);
        txtCant.setName("combo"); // NOI18N
        txtCant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantActionPerformed(evt);
            }
        });
        txtCant.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCantFocusGained(evt);
            }
        });
        txtCant.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCantKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCantKeyReleased(evt);
            }
        });

        txtPorcentaje.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        txtPorcentaje.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtPorcentaje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPorcentajeActionPerformed(evt);
            }
        });
        txtPorcentaje.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPorcentajeKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPorcentajeKeyTyped(evt);
            }
        });

        lbVendedor1.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbVendedor1.setText("Desc: %");

        lbBodega.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbBodega.setText("Bodega:");
        lbBodega.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lbBodegaKeyReleased(evt);
            }
        });

        txtBodega.setBackground(new java.awt.Color(255, 204, 204));
        txtBodega.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        txtBodega.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtBodega.setText("123-22");
        txtBodega.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtBodega.setName("combo"); // NOI18N
        txtBodega.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtBodegaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtBodegaMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtBodegaMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                txtBodegaMouseReleased(evt);
            }
        });
        txtBodega.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBodegaActionPerformed(evt);
            }
        });
        txtBodega.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtBodegaFocusGained(evt);
            }
        });
        txtBodega.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBodegaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBodegaKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(scrProductos1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scrInventario, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lbProducto1)
                        .addGap(2, 2, 2)
                        .addComponent(txtCant, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addComponent(lbBodega)
                        .addGap(1, 1, 1)
                        .addComponent(txtBodega, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addComponent(lbProducto)
                        .addGap(1, 1, 1)
                        .addComponent(txtCodProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(btnBusProd, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbVendedor1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtPorcentaje, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbProducto1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtCant, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lbBodega, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtCodProducto, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lbProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnBusProd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtBodega, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtPorcentaje, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addComponent(lbVendedor1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)))))
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrProductos1, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
                    .addComponent(scrInventario, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout pnlFormularioLayout = new javax.swing.GroupLayout(pnlFormulario);
        pnlFormulario.setLayout(pnlFormularioLayout);
        pnlFormularioLayout.setHorizontalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlOcultar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlInformacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10))
        );
        pnlFormularioLayout.setVerticalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(pnlInformacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlOcultar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        scrFormulario.setViewportView(pnlFormulario);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(scrFormulario, javax.swing.GroupLayout.DEFAULT_SIZE, 1126, Short.MAX_VALUE)
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

    private void lbNitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbNitMouseClicked

    }//GEN-LAST:event_lbNitMouseClicked

    private void lbNitKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lbNitKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_lbNitKeyReleased

    private void txtNitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNitActionPerformed

    private void txtNitKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNitKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cargarCliente(txtNit.getText());
        } else if (!txtNombre.getText().equals("")) {
            txtIdSistema.setText("");
            txtDiasPlazo.setText("0");
            txtNombre.setText("");
            txtObservaciones.setText("");
        }
    }//GEN-LAST:event_txtNitKeyReleased

    private void txtNitKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNitKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNitKeyTyped

    private void btnBuscTercerosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscTercerosActionPerformed
        ventanaTerceros("");
    }//GEN-LAST:event_btnBuscTercerosActionPerformed

    private void lbNit1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lbNit1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_lbNit1KeyReleased

    private void txtNombreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNombreMouseClicked

    }//GEN-LAST:event_txtNombreMouseClicked

    private void btnBuscTerceros1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscTerceros1ActionPerformed
        dlgInformacionCliente notaCliente = new dlgInformacionCliente(null, true, txtIdSistema.getText());
        notaCliente.setLocationRelativeTo(null);
        notaCliente.setVisible(true);
    }//GEN-LAST:event_btnBuscTerceros1ActionPerformed

    private void cmbVendedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbVendedorMouseClicked

    }//GEN-LAST:event_cmbVendedorMouseClicked

    private void lbFacturaNoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lbFacturaNoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_lbFacturaNoKeyReleased

    private void lbFacturaNoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lbFacturaNoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_lbFacturaNoKeyTyped

    private void lbNoFacturaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lbNoFacturaKeyReleased

    }//GEN-LAST:event_lbNoFacturaKeyReleased

    private void lbNoFacturaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lbNoFacturaKeyTyped

    }//GEN-LAST:event_lbNoFacturaKeyTyped

    private void lbDiasPlazoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbDiasPlazoMouseClicked

    }//GEN-LAST:event_lbDiasPlazoMouseClicked

    private void txtDiasPlazoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiasPlazoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiasPlazoActionPerformed

    private void txtDiasPlazoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiasPlazoKeyReleased
        calcularDiasPlazo(evt);
    }//GEN-LAST:event_txtDiasPlazoKeyReleased

    private void txtDiasPlazoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiasPlazoKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtDiasPlazoKeyTyped

    private void txtVencimientoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtVencimientoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVencimientoKeyReleased

    private void txtVencimientoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtVencimientoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVencimientoKeyTyped

    private void txtIdSistemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdSistemaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdSistemaActionPerformed

    private void txtIdSistemaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdSistemaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdSistemaKeyReleased

    private void txtIdSistemaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdSistemaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdSistemaKeyTyped

    private void cmbListasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbListasItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbListasItemStateChanged

    private void cmbListasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbListasActionPerformed
        if (tblInventario.getSelectedRow() != -1) {

            String baseUtilizada = obtenerBase();

            ndProducto codigo = instancias.getSql().getDatosProducto(tblProductos.getValueAt(tblInventario.getSelectedRow(), 32).toString(), baseUtilizada);
            String valor = "";

            switch ((String) tblInventario.getValueAt(tblInventario.getSelectedRow(), 0)) {
                case "L1":
                    valor = big.setMonedaExacta(big.getBigDecimal(codigo.getL1()));
                    break;
                case "L2":
                    valor = big.setMonedaExacta(big.getBigDecimal(codigo.getL2()));
                    break;
                case "L3":
                    valor = big.setMonedaExacta(big.getBigDecimal(codigo.getL3()));
                    break;
                case "L4":
                    valor = big.setMonedaExacta(big.getBigDecimal(codigo.getL4()));
                    break;
                case "L5":
                    valor = big.setMonedaExacta(big.getBigDecimal(codigo.getL5()));
                    break;
                case "L6":
                    valor = big.setMonedaExacta(big.getBigDecimal(codigo.getL6()));
                    break;
                case "L7":
                    valor = big.setMonedaExacta(big.getBigDecimal(codigo.getL7()));
                    break;
                case "L8":
                    valor = big.setMonedaExacta(big.getBigDecimal(codigo.getL8()));
                    break;
            }

            tblProductos.setColumnSelectionInterval(3, 3);
            tblProductos.setColumnSelectionInterval(6, 6);
            tblProductos.setRowSelectionInterval(tblInventario.getSelectedRow(), tblInventario.getSelectedRow());
            tblProductos.setValueAt(valor, tblInventario.getSelectedRow(), 2);
            //            tblProductos.editCellAt(tblInventario.getSelectedRow(), 6);
            tblProductos.transferFocus();

            //simulando enter sobre el producto
            KeyEvent evento = new KeyEvent(tblInventario, 0, 0, 0, 0);
            evento.setKeyCode(KeyEvent.VK_ENTER);
            tblProductosKeyReleased(evento);
        }
    }//GEN-LAST:event_cmbListasActionPerformed

    private void txtFechaFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaFacturaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaFacturaActionPerformed

    private void txtFechaFacturaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFechaFacturaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaFacturaKeyReleased

    private void txtFechaFacturaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFechaFacturaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaFacturaKeyTyped

    private void txtCarteraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCarteraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCarteraActionPerformed

    private void txtCarteraKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCarteraKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCarteraKeyReleased

    private void txtCarteraKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCarteraKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCarteraKeyTyped

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        if (metodos.msgPregunta(null, "¿Desea limpiar la nota debito?") == 0) {
            limpiar(true);
        }
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        instancias.setCancelarFactura(false);

        if (btnGuardar.getText().equals("GUARDAR")) {
            validacionInicialFactura(true);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnGuardarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnGuardarKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnGuardarActionPerformed(null);
        }
    }//GEN-LAST:event_btnGuardarKeyReleased

    private void btnReImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReImprimirActionPerformed

//        instancias.getReporte().ver_Separe("SEPARE-" + txtCargar.getText(), txtObservaciones.getText(),
//                instancias.getInformacionEmpresaCompleto(), legal, "", instancias.getPie(), this.getTipo(), "",
//                !(Boolean) datos[71]);

    }//GEN-LAST:event_btnReImprimirActionPerformed

    private void txtObservacionesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtObservacionesKeyReleased
        int cantidad = txtObservaciones.getText().length();
        cantidad = 300 - cantidad;

        lbCar.setText(String.valueOf(cantidad));

        if (cantidad <= 0) {
            txtObservaciones.setText(txtObservaciones.getText().substring(0, 300));
            lbCar.setText("0");
        }
    }//GEN-LAST:event_txtObservacionesKeyReleased

    private void lbTotalDescuentoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbTotalDescuentoMouseClicked

    }//GEN-LAST:event_lbTotalDescuentoMouseClicked

    private void cmbRtfItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbRtfItemStateChanged
        cargarTotales();
    }//GEN-LAST:event_cmbRtfItemStateChanged

    private void cmbRtfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbRtfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbRtfActionPerformed

    private void chkReteIvaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkReteIvaItemStateChanged
        cargarTotales();
    }//GEN-LAST:event_chkReteIvaItemStateChanged

    private void chkReteIvaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkReteIvaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkReteIvaActionPerformed

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

    private void tblProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductosMouseClicked
        String baseUtilizada = obtenerBase();
        String dato = "";

        ndProducto nodo = instancias.getSql().getDatosProducto(tblProductos.getValueAt(tblProductos.getSelectedRow(), 32).toString(), baseUtilizada);

        String tipo = "", productoEn = "";
        if (nodo.getTipoProd() != null) {
            if (nodo.getTipoProd().equals("Variable") || nodo.getTipoProd().equals("Fijas")) {
                productoEn = "Desarrollo";
            }
        }

        if (evt.getClickCount() >= 1 && tblProductos.getSelectedColumn() == 3) {

            if (nodo.getTipoProducto() != null) {
                if (nodo.getTipoProducto().equals("IMEI")) {
                    tipo = "Imei";
                } else if (nodo.getTipoProducto().equals("Fecha/Lote")) {
                    tipo = "Fecha/Lote";
                } else if (nodo.getTipoProducto().equals("Color")) {
                    tipo = "Color";
                } else if (nodo.getTipoProducto().equals("Serial")) {
                    tipo = "Serial";
                } else if (nodo.getTipoProducto().equals("Talla")) {
                    tipo = "Talla";
                } else if (nodo.getTipoProducto().equals("ColorTalla")) {
                    tipo = "ColorTalla";
                } else if (nodo.getTipoProducto().equals("SerialColor")) {
                    tipo = "SerialColor";
                } else {
                    tipo = "";
                }
            }

            if (!productoEn.equals("")) {
                metodos.msgAdvertenciaAjustado(null, "La cantidad no se puede modificar");
                return;
            }

            if (!tipo.equals("")) {
                metodos.msgAdvertenciaAjustado(null, "La cantidad no se puede modificar");
                return;
            }

        } else if (tblProductos.getSelectedColumn() == 5) {
            BigDecimal subtotal = big.getMoneda(tblProductos.getValueAt(tblProductos.getSelectedRow(), 2).toString()).
                    multiply(big.getBigDecimal(tblProductos.getValueAt(tblProductos.getSelectedRow(), 3).toString().replace(".", ",")));

            dlgTipoDescuento descuentoProd = new dlgTipoDescuento(null, tblProductos.getValueAt(tblProductos.getSelectedRow(), 5).toString(),
                    tblProductos.getValueAt(tblProductos.getSelectedRow(), 6).toString(), tblProductos.getSelectedRow(),
                    tblProductos.getValueAt(tblProductos.getSelectedRow(), 31).toString(), subtotal, "");
            descuentoProd.setVisible(true);
        }

        try {
            dato = tblProductos.getValueAt(tblProductos.getSelectedRow(), tblProductos.getSelectedColumn()).toString();
        } catch (Exception e) {
        }

        if (!dato.equals("")) {
            valorFila = dato;
        }

        if (tblProductos.getSelectedColumn() != 22) {
            calcularTabla(tblProductos.getSelectedRow(), false);
        }

        if (tblProductos.getSelectedColumn() == 22) {
            int fila = tblProductos.getSelectedRow();
            if (tblProductos.getValueAt(fila, 16).equals("REALIZADO")) {
                metodos.msgAdvertencia(null, "No puede Borrar este producto");
                return;
            }

            eliminarFila();
        }
    }//GEN-LAST:event_tblProductosMouseClicked

    private void tblProductosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblProductosKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
            popBorrarActionPerformed(null);
            return;
        }

        String baseUtilizada = obtenerBase();

        int fila = tblProductos.getSelectedRow(), i = 2, j = 0;

        try {
            if (tblProductos.getValueAt(fila, 16).equals("REALIZADO") && valorFila != null) {
                tblProductos.setValueAt(valorFila, fila, tblProductos.getSelectedColumn());
                valorFila = null;
                return;
            }
        } catch (Exception e) {
            return;
        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB || evt.getKeyCode() == KeyEvent.VK_UP || evt.getKeyCode() == KeyEvent.VK_DOWN
                || evt.getKeyCode() == KeyEvent.VK_LEFT || evt.getKeyCode() == KeyEvent.VK_RIGHT) {

            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                fila = fila - 1;
            } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
                fila = fila + 1;
            }

            if (fila < 0) {
                fila = 0;
            }
            if (tblProductos.getRowCount() == fila) {
                fila = fila - 1;
            }

            if (instancias.isLector()) {
                if (tblProductos.getSelectedColumn() == 1) {
                    if (!(Boolean) datos[51]) {
                        ndProducto nodo = instancias.getSql().getDatosProducto(tblProductos.getValueAt(fila, 32).toString(), baseUtilizada);
                        tblProductos.setValueAt(nodo.getDescripcion(), fila, 1);
                    }
                } else if (tblProductos.getSelectedColumn() == 2) {
                    if (!(Boolean) datos[61]) {
                        ndProducto nodo = instancias.getSql().getDatosProducto(tblProductos.getValueAt(fila, 32).toString(), baseUtilizada);
                        tblProductos.setValueAt(big.setMoneda(big.getBigDecimal(nodo.getL1())), fila, 2);
                    }

                    if (tblProductos.getValueAt(fila, 2).toString().equalsIgnoreCase("")) {
                        tblProductos.setValueAt("0", fila, 3);
                    }
                } else if (tblProductos.getSelectedColumn() == 3) {
                    if (tblProductos.getValueAt(fila, 3).toString().equalsIgnoreCase("") || tblProductos.getValueAt(fila, 3).toString().equalsIgnoreCase("0")) {
                        //                        tblProductos.setValueAt("1", fila, 3);
                    }
                    i = 3;
                }

                if (evt.getKeyCode() == KeyEvent.VK_UP || evt.getKeyCode() == KeyEvent.VK_DOWN
                        || evt.getKeyCode() == KeyEvent.VK_LEFT || evt.getKeyCode() == KeyEvent.VK_RIGHT) {

                } else {
                    txtCodProducto.requestFocus();
                }

            } else {
                if (tblProductos.getSelectedColumn() == 0) {

                    try {
                        int r = tblProductos.getSelectedRow();
                        tblProductos.changeSelection(r, 0, false, false);
                        tblProductos.removeEditor();
                        tblInventario.removeEditor();

                        tblProductos.editCellAt(r, 3);
                        tblProductos.setColumnSelectionInterval(3, 3);
                        tblProductos.transferFocus();
                    } catch (Exception e) {
                    }

                } else if (tblProductos.getSelectedColumn() == 1) {

                    ndProducto nodo = instancias.getSql().getDatosProducto(tblProductos.getValueAt(fila, 32).toString(), baseUtilizada);

                    if (!(Boolean) datos[51]) {
                        tblProductos.setValueAt(nodo.getDescripcion(), fila, 1);
                    }

                    tblProductos.editCellAt(fila, 3);
                    tblProductos.setColumnSelectionInterval(3, 3);
                    tblProductos.transferFocus();

                } else if (tblProductos.getSelectedColumn() == 2) {
                    if (tblProductos.getValueAt(fila, 32).equals("IMP01")) {
                        tblProductos.setValueAt(this.simbolo + " 0", fila, 2);
                    }

                    if (tblProductos.getValueAt(fila, 2).toString().equalsIgnoreCase("")) {
                        tblProductos.setValueAt("0", fila, 3);
                    }

                    if (!(Boolean) datos[51]) {
                        ndProducto nodo = instancias.getSql().getDatosProducto(tblProductos.getValueAt(fila, 32).toString(), baseUtilizada);
                        tblProductos.setValueAt(nodo.getDescripcion(), fila, 1);
                    }

                    if (!(Boolean) datos[61]) {
                        ndProducto nodo = instancias.getSql().getDatosProducto(tblProductos.getValueAt(fila, 32).toString(), baseUtilizada);

                        String lista = tblInventario.getValueAt(tblProductos.getSelectedRow(), 0).toString();
                        if (lista.equals("L1")) {
                            tblProductos.setValueAt(big.setMoneda(big.getBigDecimal(nodo.getL1())), tblProductos.getSelectedRow(), 2);
                        } else if (lista.equals("L2")) {
                            tblProductos.setValueAt(big.setMoneda(big.getBigDecimal(nodo.getL2())), tblProductos.getSelectedRow(), 2);
                        } else if (lista.equals("L3")) {
                            tblProductos.setValueAt(big.setMoneda(big.getBigDecimal(nodo.getL3())), tblProductos.getSelectedRow(), 2);
                        } else if (lista.equals("L4")) {
                            tblProductos.setValueAt(big.setMoneda(big.getBigDecimal(nodo.getL4())), tblProductos.getSelectedRow(), 2);
                        }
                    }

                    tblProductos.editCellAt(fila, 3);
                    tblProductos.setColumnSelectionInterval(3, 3);
                    tblProductos.transferFocus();
                } else if (tblProductos.getSelectedColumn() == 3) {

                    if (!(Boolean) datos[61]) {
                        ndProducto nodo = instancias.getSql().getDatosProducto(tblProductos.getValueAt(fila, 32).toString(), baseUtilizada);

                        String lista = tblInventario.getValueAt(tblProductos.getSelectedRow(), 0).toString();
                        if (lista.equals("L1")) {
                            tblProductos.setValueAt(big.setMoneda(big.getBigDecimal(nodo.getL1())), tblProductos.getSelectedRow(), 2);
                        } else if (lista.equals("L2")) {
                            tblProductos.setValueAt(big.setMoneda(big.getBigDecimal(nodo.getL2())), tblProductos.getSelectedRow(), 2);
                        } else if (lista.equals("L3")) {
                            tblProductos.setValueAt(big.setMoneda(big.getBigDecimal(nodo.getL3())), tblProductos.getSelectedRow(), 2);
                        } else if (lista.equals("L4")) {
                            tblProductos.setValueAt(big.setMoneda(big.getBigDecimal(nodo.getL4())), tblProductos.getSelectedRow(), 2);
                        }
                    }

                    String cant = tblProductos.getValueAt(tblProductos.getSelectedRow(), 3).toString();
                    ndProducto nodo = instancias.getSql().getDatosProducto(tblProductos.getValueAt(fila, 32).toString(), baseUtilizada);

                    String tipo = "";
                    if (nodo.getTipoProducto() != null) {
                        if (nodo.getTipoProducto().equals("IMEI")) {
                            tipo = "Imei";
                        } else if (nodo.getTipoProducto().equals("Fecha/Lote")) {
                            tipo = "Fecha/Lote";
                        } else if (nodo.getTipoProducto().equals("Color")) {
                            tipo = "Color";
                        } else if (nodo.getTipoProducto().equals("Serial")) {
                            tipo = "Serial";
                        } else if (nodo.getTipoProducto().equals("Talla")) {
                            tipo = "Talla";
                        } else if (nodo.getTipoProducto().equals("ColorTalla")) {
                            tipo = "ColorTalla";
                        } else if (nodo.getTipoProducto().equals("SerialColor")) {
                            tipo = "SerialColor";
                        } else {
                            tipo = "";
                        }
                    }

                    if (!tipo.equals("")) {
                        tblProductos.setValueAt(cant, fila, 3);
                    }

                    if (tblProductos.getValueAt(fila, 3).toString().equalsIgnoreCase("") || tblProductos.getValueAt(fila, 3).toString().equalsIgnoreCase("0")) {
                        //                        tblProductos.setValueAt("1", fila, 3);
                    }

                    tblProductos.editCellAt(fila, 5);
                    tblProductos.setColumnSelectionInterval(5, 5);
                    tblProductos.transferFocus();
                    i = 3;
                } else if (tblProductos.getSelectedColumn() == 5) {
                    // Si se esta cambiando el campo de descuento

                    if (evt.getKeyCode() == KeyEvent.VK_UP || evt.getKeyCode() == KeyEvent.VK_DOWN
                            || evt.getKeyCode() == KeyEvent.VK_LEFT || evt.getKeyCode() == KeyEvent.VK_RIGHT) {

                    } else {
                        txtCodProducto.requestFocus();
                    }

                    i = 5;
                    j = 0;
                }
            }

            if (tblProductos.getSelectedColumn() == 17) {
                tblProductos.editCellAt(tblProductos.getSelectedRow(), 17);
                tblProductos.setColumnSelectionInterval(17, 17);
                try {
                    tblProductos.setValueAt(big.setMoneda(big.getMoneda(tblProductos.getValueAt(fila, 17).toString())), fila, 17);
                } catch (Exception e) {
                    tblProductos.setValueAt(big.setMoneda(big.getBigDecimal("0")), fila, 17);
                }
                tblProductos.transferFocus();

            }

            if (tblProductos.getSelectedColumn() == 19) {
                try {
                    tblProductos.setValueAt(big.setMoneda(big.getMoneda(tblProductos.getValueAt(fila, 19).toString())), fila, 19);
                    BigDecimal resta = big.getMoneda(tblProductos.getValueAt(fila, 9).toString()).subtract(big.getMoneda(tblProductos.getValueAt(fila, 19).toString()));
                    tblProductos.setValueAt(resta, fila, 20);

                    if (resta.compareTo(BigDecimal.ZERO) < 0) {
                        metodos.msgAdvertencia(this, "No tiene ninguna utilidad!");
                        tblProductos.setValueAt(big.setMoneda(big.getBigDecimal("0")), fila, 19);
                    }

                } catch (Exception e) {
                    tblProductos.setValueAt(big.setMoneda(big.getBigDecimal("0")), fila, 19);
                }
                tblProductos.transferFocus();
            }

            BigDecimal porcentajeDescuento = big.getBigDecimal(tblProductos.getValueAt(fila, 5).toString().replace(",", "."));

            int res = porcentajeDescuento.compareTo(big.getBigDecimal("0"));
            if (res == -1 || String.valueOf(tblProductos.getValueAt(fila, i)).equals("")) {
                tblProductos.setValueAt(j, fila, 5);
            }

            if (tblProductos.getSelectedColumn() == 24) {
                tblProductos.editCellAt(fila, 25);
                tblProductos.setColumnSelectionInterval(25, 25);
                tblProductos.transferFocus();
            } else if (tblProductos.getSelectedColumn() == 25) {
                tblProductos.editCellAt(fila, 26);
                tblProductos.setColumnSelectionInterval(26, 26);
                tblProductos.transferFocus();
            } else if (tblProductos.getSelectedColumn() == 26) {
                txtCodProducto.transferFocus();
            }

            calcularTabla(fila, true);
        }

        BigDecimal num1 = big.getMoneda(tblInventario.getValueAt(fila, 1).toString());
        BigDecimal num2 = big.getBigDecimal(tblProductos.getValueAt(fila, 3).toString().replace(",", "."));
        BigDecimal total = num1.subtract(num2);
        tblInventario.setValueAt(big.setNumero(total), fila, 2);
    }//GEN-LAST:event_tblProductosKeyReleased

    private void lbProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbProductoMouseClicked

    }//GEN-LAST:event_lbProductoMouseClicked

    private void lbProductoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lbProductoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtCodProducto.requestFocus();
        }
    }//GEN-LAST:event_lbProductoKeyReleased

    private void txtCodProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodProductoActionPerformed

    }//GEN-LAST:event_txtCodProductoActionPerformed

    private void txtCodProductoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCodProductoFocusGained
        cargarTotales();
    }//GEN-LAST:event_txtCodProductoFocusGained

    private void txtCodProductoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodProductoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
            popBorrarActionPerformed(null);
            return;
        }

        int fila = tblProductos.getSelectedRow(), i = 2, j = 0;

        try {
            if (tblProductos.getValueAt(fila, 16).equals("REALIZADO") && valorFila != null) {
                tblProductos.setValueAt(valorFila, fila, tblProductos.getSelectedColumn());
                valorFila = null;
                return;
            }
        } catch (Exception e) {
            return;
        }

        try {
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                if (instancias.isLector()) {

                    if (tblProductos.getSelectedColumn() == 2) {
                        if (tblProductos.getValueAt(fila, 2).toString().equalsIgnoreCase("")) {
                            tblProductos.setValueAt("0", fila, 3);
                        }
                    } else if (tblProductos.getSelectedColumn() == 3) {
                        if (tblProductos.getValueAt(fila, 3).toString().equalsIgnoreCase("") || tblProductos.getValueAt(fila, 3).toString().equalsIgnoreCase("0")) {
                            tblProductos.setValueAt("1", fila, 3);
                        }
                        i = 3;
                    }

                    txtCodProducto.requestFocus();

                } else {
                    if (tblProductos.getSelectedColumn() == 0) {

                        try {
                            int r = tblProductos.getSelectedRow();
                            tblProductos.changeSelection(r, 0, false, false);
                            tblProductos.removeEditor();
                            tblInventario.removeEditor();

                            tblProductos.editCellAt(r, 3);
                            tblProductos.setColumnSelectionInterval(3, 3);
                            tblProductos.transferFocus();
                        } catch (Exception e) {
                        }

                    } else if (tblProductos.getSelectedColumn() == 2) {
                        if (tblProductos.getValueAt(fila, 2).toString().equalsIgnoreCase("")) {
                            tblProductos.setValueAt("0", fila, 3);
                        }
                        tblProductos.editCellAt(tblProductos.getSelectedRow(), 3);
                        tblProductos.setColumnSelectionInterval(3, 3);
                        tblProductos.transferFocus();

                    } else if (tblProductos.getSelectedColumn() == 3) {
                        if (tblProductos.getValueAt(fila, 3).toString().equalsIgnoreCase("") || tblProductos.getValueAt(fila, 3).toString().equalsIgnoreCase("0")) {
                            //                            tblProductos.setValueAt("1", fila, 3);
                        }
                        tblProductos.editCellAt(tblProductos.getSelectedRow(), 5);
                        tblProductos.setColumnSelectionInterval(5, 5);
                        tblProductos.transferFocus();

                        i = 3;

                    } else if (tblProductos.getSelectedColumn() == 5) {
                        // Si se esta cambiando el campo de descuento
                        txtCodProducto.requestFocus();
                        i = 5;
                        j = 0;
                    }

                }

                if (tblProductos.getSelectedColumn() == 17) {

                    tblProductos.editCellAt(tblProductos.getSelectedRow(), 17);
                    tblProductos.setColumnSelectionInterval(17, 17);
                    try {
                        tblProductos.setValueAt(big.setMoneda(big.getMoneda(tblProductos.getValueAt(fila, 17).toString())), fila, 17);
                    } catch (Exception e) {
                        tblProductos.setValueAt(big.setMoneda(big.getBigDecimal("0")), fila, 17);
                    }
                    tblProductos.transferFocus();

                }

                if (tblProductos.getSelectedColumn() == 19) {
                    try {
                        tblProductos.setValueAt(big.setMoneda(big.getMoneda(tblProductos.getValueAt(fila, 19).toString())), fila, 19);
                        BigDecimal resta = big.getMoneda(tblProductos.getValueAt(fila, 9).toString()).subtract(big.getMoneda(tblProductos.getValueAt(fila, 19).toString()));
                        tblProductos.setValueAt(resta, fila, 20);

                        if (resta.compareTo(BigDecimal.ZERO) < 0) {
                            metodos.msgAdvertencia(this, "No tiene ninguna utilidad!");
                        }
                    } catch (Exception e) {
                        tblProductos.setValueAt(big.setMoneda(big.getBigDecimal("0")), fila, 19);
                    }
                    tblProductos.transferFocus();
                }

                int res = big.getBigDecimal(big.getMoneda(tblProductos.getValueAt(fila, i).toString().replace(".", ","))).compareTo(big.getBigDecimal("0"));
                if (res == -1 || String.valueOf(tblProductos.getValueAt(fila, i)).equals("")) {
                    tblProductos.setValueAt(j, tblProductos.getSelectedRow(), i);
                }

                calcularTabla(fila, true);
            }
        } catch (Exception e) {
            try {
                BigDecimal auxx = big.getBigDecimal(big.getMoneda(tblProductos.getValueAt(fila, 2).toString()));
                tblProductos.setValueAt(big.setMoneda(auxx), fila, 2);
            } catch (Exception ex) {
                System.out.println(ex);
                tblProductos.setValueAt(this.simbolo + " 0", fila, 2);
            }

            try {
                BigDecimal auxx = big.getBigDecimal(tblProductos.getValueAt(fila, 3).toString().replace(",", "."));
                tblProductos.setValueAt(auxx.toString().replace(".", ","), fila, 3);
            } catch (Exception ex) {
                tblProductos.setValueAt(1, fila, 3);
            }

            tblProductos.setValueAt(this.simbolo + " 0", fila, 6);
            tblProductos.setValueAt("0", fila, 5);

            calcularTabla(fila, true);
        }

        BigDecimal num1 = big.getMoneda(tblInventario.getValueAt(fila, 1).toString());
        BigDecimal num2 = big.getBigDecimal(tblProductos.getValueAt(fila, 3).toString().replace(",", "."));
        BigDecimal total = num1.subtract(num2);
        tblInventario.setValueAt(big.setNumero(total), fila, 2);
    }//GEN-LAST:event_txtCodProductoKeyPressed

    private void txtCodProductoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodProductoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String codigo = txtCodProducto.getText().replace("'", "//");
            plu = true;

            String cant = txtCant.getText();
            cargarProducto(codigo, cant, 1, "", "", "", true, "", "", "", "", "");
        } else if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            btnGuardar.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_MULTIPLY) {
            double cantidad = 1;
            try {
                cantidad = Double.parseDouble(txtCodProducto.getText().replace("*", ""));
            } catch (Exception e) {
            }
            txtCant.setText(String.valueOf(cantidad));
            if (txtCant.getText().substring(txtCant.getText().length() - 1, txtCant.getText().length()).equals("0")) {
                txtCant.setText(txtCant.getText().substring(0, txtCant.getText().length() - 2));
            }
            txtCodProducto.setText("");
        }
    }//GEN-LAST:event_txtCodProductoKeyReleased

    private void btnBusProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBusProdActionPerformed
        ventanaProductos("");
    }//GEN-LAST:event_btnBusProdActionPerformed

    private void tblInventarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblInventarioMouseClicked

    }//GEN-LAST:event_tblInventarioMouseClicked

    private void lbProducto1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lbProducto1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_lbProducto1KeyReleased

    private void txtCantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantActionPerformed

    private void txtCantFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCantFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantFocusGained

    private void txtCantKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantKeyPressed

    private void txtCantKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantKeyReleased

    private void txtPorcentajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPorcentajeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPorcentajeActionPerformed

    private void txtPorcentajeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPorcentajeKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

//            if (topeDescuento) {
//                if (Integer.parseInt(txtPorcentaje.getText()) > 20) {
//                    txtPorcentaje.setText("20");
//                }
//            }
            tblProductos.removeEditor();

            int xyz = tblProductos.getRowCount();

            if (xyz > 0) {
                for (int y = 0; y < xyz; y++) {
                    tblProductos.setValueAt("0", y, 5);
                    tblProductos.setValueAt(this.simbolo + " 0", y, 6);
                    calcularTabla(y, false);
                }
            }

            if (xyz > 0) {
                BigDecimal porcentaje = big.getBigDecimal(txtPorcentaje.getText());
                for (int y = 0; y < xyz; y++) {
                    tblProductos.setValueAt(porcentaje, y, 5);
                    tblProductos.setValueAt(big.setMoneda(porcentaje.multiply(big.getMoneda(tblProductos.getValueAt(y, 2).toString()).divide(big.getBigDecimal("100")))), y, 6);
                    calcularTabla(y, false);
                }
            }

            txtPorcentaje.setBackground(Color.WHITE);
            txtPorcentaje.setForeground(Color.BLACK);

            cargarTotales();
            txtPorcentaje.requestFocus();
        } else {
            txtPorcentaje.setBackground(new Color(251, 238, 152));
            txtPorcentaje.setForeground(new Color(146, 137, 77));
        }
    }//GEN-LAST:event_txtPorcentajeKeyReleased

    private void txtPorcentajeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPorcentajeKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtPorcentajeKeyTyped

    private void lbBodegaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lbBodegaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_lbBodegaKeyReleased

    private void txtBodegaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtBodegaMouseClicked
        if (txtBodega.isEnabled()) {
            if (!txtBodega.getText().equals("")) {
                if (tblProductos.getRowCount() > 0) {
                    if (metodos.msgPregunta(null, "¿Limpiar nota debito?") != 0) {
                        txtCodProducto.requestFocus();
                        return;
                    } else {
                        while (tblProductos.getRowCount() > 0) {
                            modeloPro.removeRow(0);
                        }
                        while (tblInventario.getRowCount() > 0) {
                            modeloInventario.removeRow(0);
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_txtBodegaMouseClicked

    private void txtBodegaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtBodegaMouseEntered

    }//GEN-LAST:event_txtBodegaMouseEntered

    private void txtBodegaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtBodegaMousePressed
        if (txtBodega.isEnabled()) {
            if (!txtBodega.getText().equals("")) {
                if (tblProductos.getRowCount() > 0) {
                    if (metodos.msgPregunta(null, "¿Limpiar nota debito?") != 0) {
                        txtCodProducto.requestFocus();
                        return;
                    } else {
                        while (tblProductos.getRowCount() > 0) {
                            modeloPro.removeRow(0);
                        }
                        while (tblInventario.getRowCount() > 0) {
                            modeloInventario.removeRow(0);
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_txtBodegaMousePressed

    private void txtBodegaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtBodegaMouseReleased
        if (txtBodega.isEnabled()) {
            if (!txtBodega.getText().equals("")) {
                if (tblProductos.getRowCount() > 0) {
                    if (metodos.msgPregunta(null, "¿Limpiar nota debito?") != 0) {
                        txtCodProducto.requestFocus();
                        return;
                    } else {
                        while (tblProductos.getRowCount() > 0) {
                            modeloPro.removeRow(0);
                        }
                        while (tblInventario.getRowCount() > 0) {
                            modeloInventario.removeRow(0);
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_txtBodegaMouseReleased

    private void txtBodegaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBodegaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBodegaActionPerformed

    private void txtBodegaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBodegaFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBodegaFocusGained

    private void txtBodegaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBodegaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBodegaKeyPressed

    private void txtBodegaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBodegaKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txtBodega.getText().equals("")) {
                ventanaBodegas("");
            } else {
                txtCodProducto.requestFocus();
            }
        } else {
            txtBodega.setText("");
            if (tblProductos.getRowCount() > 0) {
                while (tblProductos.getRowCount() > 0) {
                    modeloPro.removeRow(0);
                }
                while (tblInventario.getRowCount() > 0) {
                    modeloInventario.removeRow(0);
                }
            }
        }
    }//GEN-LAST:event_txtBodegaKeyReleased

    private void tblComprobantesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblComprobantesMouseClicked
        for (int i = 0; i < tblComprobantes.getRowCount(); i++) {
            tblComprobantes.setValueAt(false, i, 2);
        }

        if (tblComprobantes.getSelectedRow() == -1) {
            tblComprobantes.setValueAt(true, 0, 2);
            if (tblComprobantes.getSelectedRow() == 0) {
                txtRelacion.setVisible(true);
                txtFactRelacion.setVisible(true);
                lbRelacion.setVisible(true);
                lbRelacion1.setVisible(true);
            } else {
                txtRelacion.setVisible(false);
                txtFactRelacion.setVisible(false);
                lbRelacion.setVisible(false);
                lbRelacion1.setVisible(false);
            }
        } else {
            tblComprobantes.setValueAt(true, tblComprobantes.getSelectedRow(), 2);
            if (tblComprobantes.getSelectedRow() == 0) {
                txtRelacion.setVisible(true);
                txtFactRelacion.setVisible(true);
                lbRelacion.setVisible(true);
                lbRelacion1.setVisible(true);
            } else {
                txtRelacion.setVisible(false);
                txtFactRelacion.setVisible(false);
                lbRelacion.setVisible(false);
                lbRelacion1.setVisible(false);
            }
        }
    }//GEN-LAST:event_tblComprobantesMouseClicked

    private void tblComprobantesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblComprobantesMouseEntered

    }//GEN-LAST:event_tblComprobantesMouseEntered

    private void tblComprobantesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblComprobantesMouseExited
        for (int i = 0; i < tblComprobantes.getRowCount(); i++) {
            tblComprobantes.setValueAt(false, i, 2);
        }

        if (tblComprobantes.getSelectedRow() == -1) {
            tblComprobantes.setValueAt(true, 0, 2);
            if (tblComprobantes.getSelectedRow() == 0) {
                txtRelacion.setVisible(true);
                txtFactRelacion.setVisible(true);
                lbRelacion.setVisible(true);
                lbRelacion1.setVisible(true);
            } else {
                txtRelacion.setVisible(false);
                txtFactRelacion.setVisible(false);
                lbRelacion.setVisible(false);
                lbRelacion1.setVisible(false);
            }
        } else {
            tblComprobantes.setValueAt(true, tblComprobantes.getSelectedRow(), 2);
            if (tblComprobantes.getSelectedRow() == 0) {
                txtRelacion.setVisible(true);
                txtFactRelacion.setVisible(true);
                lbRelacion.setVisible(true);
                lbRelacion1.setVisible(true);
            } else {
                txtRelacion.setVisible(false);
                txtFactRelacion.setVisible(false);
                lbRelacion.setVisible(false);
                lbRelacion1.setVisible(false);
            }
        }
    }//GEN-LAST:event_tblComprobantesMouseExited

    private void txtRelacionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRelacionKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            ndFactura datosFactura = instancias.getSql().getDatosFactura("FACT-" + txtRelacion.getText());
            if (datosFactura.getFactura() == null) {
                metodos.msgAdvertenciaAjustado(this, "La factura no existe");
            } else {
                txtFactRelacion.setText("Válida");
            }
        } else {
            txtFactRelacion.setText("");
        }
    }//GEN-LAST:event_txtRelacionKeyReleased

    private void txtRelacionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRelacionKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRelacionKeyTyped

    private void txtFactRelacionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFactRelacionKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFactRelacionKeyReleased

    private void txtFactRelacionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFactRelacionKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFactRelacionKeyTyped

    private void calcularTabla(int fila, boolean mostrarAlerta) {

        String baseUtilizada = obtenerBase();
        ndProducto nodo = instancias.getSql().getDatosProducto(tblProductos.getValueAt(fila, 32).toString(), baseUtilizada);

        BigDecimal valor = BigDecimal.ZERO, cantidad = BigDecimal.ZERO, descuento, subtotal, total, porcentaje2,
                compra, utilidadMax, utilidadMin, copago, valorDescuento;
        Boolean entro = false;

        try {
            valorDescuento = big.getMoneda(String.valueOf(tblProductos.getValueAt(fila, 6)));
        } catch (Exception e) {
            valorDescuento = BigDecimal.ZERO;
        }

        tblProductos.setValueAt(big.setMonedaExacta(valorDescuento), fila, 6);

        try {
            valor = big.getMoneda(String.valueOf(tblProductos.getValueAt(fila, 2)));

            if (!(Boolean) datos[61]) {
                String lista = tblInventario.getValueAt(fila, 0).toString();
                if (lista.equals("L1")) {
                    if (valor.compareTo(big.getBigDecimal(nodo.getL1())) != 0) {
                        tblProductos.setValueAt(big.setMoneda(big.getBigDecimal(nodo.getL1())), fila, 2);
                    }
                } else if (lista.equals("L2")) {
                    if (valor.compareTo(big.getBigDecimal(nodo.getL2())) != 0) {
                        tblProductos.setValueAt(big.setMoneda(big.getBigDecimal(nodo.getL2())), fila, 2);
                    }
                } else if (lista.equals("L3")) {
                    if (valor.compareTo(big.getBigDecimal(nodo.getL3())) != 0) {
                        tblProductos.setValueAt(big.setMoneda(big.getBigDecimal(nodo.getL3())), fila, 2);
                    }
                } else if (lista.equals("L4")) {
                    if (valor.compareTo(big.getBigDecimal(nodo.getL4())) != 0) {
                        tblProductos.setValueAt(big.setMoneda(big.getBigDecimal(nodo.getL4())), fila, 2);
                    }
                }
            }

            tblProductos.setValueAt(big.setMoneda(valor), fila, 2);
        } catch (Exception e) {
            String lista = tblInventario.getValueAt(fila, 0).toString();
            if (lista.equals("L1")) {
                if (valor.compareTo(big.getBigDecimal(nodo.getL1())) != 0) {
                    tblProductos.setValueAt(big.setMoneda(big.getBigDecimal(nodo.getL1())), fila, 2);
                }
            } else if (lista.equals("L2")) {
                if (valor.compareTo(big.getBigDecimal(nodo.getL2())) != 0) {
                    tblProductos.setValueAt(big.setMoneda(big.getBigDecimal(nodo.getL2())), fila, 2);
                }
            } else if (lista.equals("L3")) {
                if (valor.compareTo(big.getBigDecimal(nodo.getL3())) != 0) {
                    tblProductos.setValueAt(big.setMoneda(big.getBigDecimal(nodo.getL3())), fila, 2);
                }
            } else if (lista.equals("L4")) {
                if (valor.compareTo(big.getBigDecimal(nodo.getL4())) != 0) {
                    tblProductos.setValueAt(big.setMoneda(big.getBigDecimal(nodo.getL4())), fila, 2);
                }
            }
        }

        try {
            cantidad = big.getMoneda(String.valueOf(tblProductos.getValueAt(fila, 3)).replace(".", ","));
        } catch (Exception e) {
            cantidad = big.getBigDecimal("1");
        }

        String tipo = "", productoEn = "";
        try {
            if (nodo.getTipoProd().equals("Variable") || nodo.getTipoProd().equals("Fijas")) {
                productoEn = "Desarrollo";
            }
        } catch (Exception e) {
        }

        if (nodo.getTipoProducto() != null) {
            if (nodo.getTipoProducto().equals("IMEI")) {
                tipo = "Imei";
            } else if (nodo.getTipoProducto().equals("Fecha/Lote")) {
                tipo = "Fecha/Lote";
            } else if (nodo.getTipoProducto().equals("Color")) {
                tipo = "Color";
            } else if (nodo.getTipoProducto().equals("Serial")) {
                tipo = "Serial";
            } else if (nodo.getTipoProducto().equals("Talla")) {
                tipo = "Talla";
            } else if (nodo.getTipoProducto().equals("ColorTalla")) {
                tipo = "ColorTalla";
            } else if (nodo.getTipoProducto().equals("SerialColor")) {
                tipo = "SerialColor";
            } else {
                tipo = "";
            }
        }

        if (!productoEn.equals("")) {
            cantidad = BigDecimal.ONE;
        }

        subtotal = valor.multiply(cantidad);
        copago = big.getMoneda(String.valueOf(tblProductos.getValueAt(fila, 17)));

        if (tblProductos.getValueAt(fila, 6).toString().equals("") || tblProductos.getValueAt(fila, 5).toString().equals("")) {
            porcentaje2 = big.getBigDecimal("0");
            descuento = big.getMoneda("0");
        } else {
            Object[] datos = calcularDescuento(fila, subtotal, mostrarAlerta);
            descuento = (BigDecimal) datos[0];
            porcentaje2 = (BigDecimal) datos[1];
        }

        BigDecimal totalIva = BigDecimal.ZERO, totalImpoconsumo = BigDecimal.ZERO;
        BigDecimal porcentajeIva = big.getMoneda(String.valueOf(tblProductos.getValueAt(fila, 7))).divide(BigDecimal.valueOf(100));
        BigDecimal porcentajeImpoconsumo = big.getMoneda(String.valueOf(tblProductos.getValueAt(fila, 23))).divide(BigDecimal.valueOf(100));
        BigDecimal valorBaseProducto = BigDecimal.ONE;
        if (instancias.isPvpConIva()) {
            valorBaseProducto = valorBaseProducto.add(porcentajeIva);
        }

        if (instancias.isPvpConImpoconsumo()) {
            valorBaseProducto = valorBaseProducto.add(porcentajeImpoconsumo);
        }

        valorBaseProducto = subtotal.divide(valorBaseProducto, 2);

        if (instancias.isPvpConIva()) {
            totalIva = valorBaseProducto.multiply(porcentajeIva);
            subtotal = subtotal.subtract(totalIva);
        } else {
            totalIva = subtotal.multiply(porcentajeIva);
        }

        if (instancias.isPvpConImpoconsumo()) {
            totalImpoconsumo = valorBaseProducto.multiply(porcentajeImpoconsumo);
            subtotal = subtotal.subtract(totalImpoconsumo);
        } else {
            totalImpoconsumo = subtotal.multiply(porcentajeImpoconsumo);
        }

        subtotal = subtotal.subtract(descuento);
        total = subtotal.add(totalIva).add(totalImpoconsumo);

        tblProductos.setValueAt(big.setMoneda(subtotal), fila, 4);
        tblProductos.setValueAt(big.setMoneda(totalIva), fila, 33);
        tblProductos.setValueAt(big.setMoneda(totalImpoconsumo), fila, 8);
        tblProductos.setValueAt(big.setMoneda(total), fila, 9);

        tblProductos.setValueAt(big.setNumero(porcentaje2), fila, 5);
        tblProductos.setValueAt(big.setMonedaExacta(descuento), fila, 6);
        tblProductos.setValueAt(big.setMoneda(copago), fila, 17);

        switch (tblProductos.getValueAt(fila, 12).toString()) {
            case "1":
                tblProductos.setValueAt(cantidad, fila, 13);
                break;
            case "2":
                tblProductos.setValueAt(cantidad.multiply(big.getBigDecimal(nodo.getCantidad2())), fila, 13);
                break;
            case "3":
                tblProductos.setValueAt(cantidad.multiply(big.getBigDecimal(nodo.getCantidad3())), fila, 13);
                break;
            case "4":
                tblProductos.setValueAt(cantidad.multiply(big.getBigDecimal(nodo.getCantidad4())), fila, 13);
                break;
            case "5":
                tblProductos.setValueAt(cantidad.multiply(big.getBigDecimal(nodo.getCantidad5())), fila, 13);
                break;
            case "6":
                tblProductos.setValueAt(cantidad.multiply(big.getBigDecimal(nodo.getCantidad6())), fila, 13);
                break;
            case "7":
                tblProductos.setValueAt(cantidad.multiply(big.getBigDecimal(nodo.getCantidad7())), fila, 13);
                break;
            case "8":
                tblProductos.setValueAt(cantidad.multiply(big.getBigDecimal(nodo.getCantidad8())), fila, 13);
                break;
        }

        tblProductos.setValueAt(cantidad, fila, 3);

        BigDecimal costo = BigDecimal.ZERO;
        try {
            UltimoPonderado ultimoPonderado = servicioActualizacionPonderado.obtenerUltimoPonderado(nodo.getIdSistema());
            costo = ultimoPonderado.getNuevoPonderado();
        } catch (SQLException ex) {
            Logger.getLogger(VistaInventarioInicial.class.getName()).log(Level.SEVERE, null, ex);
            alertas.bigAlert("No se pudo consultar el último ponderado del producto");
        }

        BigDecimal utilidad, valorUnidad;
        valorUnidad = big.getMoneda(tblProductos.getValueAt(fila, 4).toString()).divide(big.getBigDecimal(tblProductos.getValueAt(fila, 13)), 2, RoundingMode.HALF_UP);

        utilidad = valorUnidad.subtract(costo);
        utilidad = utilidad.multiply(big.getBigDecimal(tblProductos.getValueAt(fila, 3).toString().replace(",", ".")));
        tblProductos.setValueAt(big.setMoneda(utilidad), fila, 14);

        if (nodo.getIva().equals("0") || nodo.getIva().equals(".00")) {
            compra = costo.multiply(big.getBigDecimal(nodo.getIvaC())).divide(new BigDecimal("100")).add(big.getBigDecimal(costo));
        } else {
            compra = costo;
        }

        utilidadMin = (compra.multiply(big.getBigDecimal(nodo.getMinima())).divide(new BigDecimal("100"))).add(compra).setScale(0, BigDecimal.ROUND_HALF_DOWN);
        utilidadMax = (compra.multiply(big.getBigDecimal(nodo.getMaxima())).divide(new BigDecimal("100"))).add(compra).setScale(0, BigDecimal.ROUND_HALF_DOWN);

        if (valor.compareTo(utilidadMin) == -1) {
            tblProductos.setValueAt("ERROR1", fila, 15);
        } else if (valor.compareTo(utilidadMax) == 1) {
            tblProductos.setValueAt("ERROR2", fila, 15);
        } else {
            tblProductos.setValueAt("OK", fila, 15);
        }

        cargarTotales();
    }

    public void cargarTotales() {
        int i;
        BigDecimal subtotal = BigDecimal.ZERO, iva = BigDecimal.ZERO, impoconsumo = BigDecimal.ZERO, total = BigDecimal.ZERO, descuentos = BigDecimal.ZERO,
                rtf = BigDecimal.ZERO, rti = BigDecimal.ZERO, copago = BigDecimal.ZERO, cantUnidades = BigDecimal.ZERO, impuesto = BigDecimal.ZERO;

        BigDecimal valorBolsa;
        valorBolsa = big.getMoneda(datos[53].toString());

        for (i = 0; i < tblProductos.getRowCount(); i++) {
            if (!tblProductos.getValueAt(i, 16).equals("REALIZADO")) {

                if (tblProductos.getValueAt(i, 32).equals("PROD-000000032")) {
                    impuesto = valorBolsa.multiply(big.getBigDecimal(tblProductos.getValueAt(i, 3)));
                }

                subtotal = subtotal.add(big.getMoneda(String.valueOf(tblProductos.getValueAt(i, 4))));
                descuentos = descuentos.add(big.getMoneda(String.valueOf(tblProductos.getValueAt(i, 6))));

                impoconsumo = impoconsumo.add(big.getMoneda(String.valueOf(tblProductos.getValueAt(i, 8))));
                iva = iva.add(big.getMoneda(String.valueOf(tblProductos.getValueAt(i, 33))));

                total = total.add(big.getMoneda(String.valueOf(tblProductos.getValueAt(i, 9))));
                copago = copago.add(big.getMoneda(String.valueOf(tblProductos.getValueAt(i, 17))));
            }
            cantUnidades = cantUnidades.add(big.getBigDecimal(tblProductos.getValueAt(i, 3).toString().replace(",", ".")));
        }

        if (cmbRtf.getSelectedIndex() > 0) {
            rtf = ((subtotal.subtract(descuentos))).multiply(big.getBigDecimal(cmbRtf.getSelectedItem())).divide(big.getBigDecimal("100"), 2, RoundingMode.HALF_DOWN);
        }

        if (chkReteIva.isSelected()) {
            rti = iva.multiply(big.getBigDecimal("15")).divide(big.getBigDecimal("100"), 2, RoundingMode.HALF_DOWN);
        }

        txtSubTotal.setText(big.setMoneda(subtotal));
        txtTotalDescuentos.setText(big.setMoneda(descuentos));
        txtTotalIva.setText(big.setMoneda(iva));
        txtTotalImpoconsumo.setText(big.setMoneda(impoconsumo));

        txtImpuesto.setText(big.setMoneda(impuesto));

        txtTotal.setText("Total: " + big.setMoneda(total.add(impuesto)));

        txtRtf.setText(big.setMonedaExacta(rtf));
        txtRiva.setText(big.setMonedaExacta(rti));

        txtCantProductos.setText(Integer.toString(tblProductos.getRowCount()));
        txtCantUnidades.setText(cantUnidades.toString());
    }

    private void calcularDiasPlazo(java.awt.event.KeyEvent evt) {
        if (txtNombre.getText().equals("")) {
            metodos.msgAdvertenciaAjustado(null, "Debe ingresar un cliente");
            txtDiasPlazo.setText("0");
            txtNit.requestFocus();
        }

        if (txtNit.getText().equals("1010")) {
            txtNit.requestFocus();
            txtDiasPlazo.setText("0");
            return;
        }

        try {
            txtVencimiento.setText(metodos.sumarFecha(txtFechaFactura.getText(), Integer.parseInt(txtDiasPlazo.getText())));
        } catch (NumberFormatException exep) {
            txtVencimiento.setText(metodos.sumarFecha(txtFechaFactura.getText(), 0));
        }

        if (evt != null) {
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                txtCodProducto.requestFocus();
            }
        }
    }

    public void ventanaBodegas(String nit) {
        buscBodegas buscar = new buscBodegas(instancias.getMenu(), true, "INTERNA");
        buscar.setLocationRelativeTo(null);
        instancias.setBuscBodegas(buscar);
        instancias.setCampoActual(txtBodega);
        txtBodega.requestFocus();
        buscar.noEncontrado(nit);
        buscar.show();
    }

    public void consultarMaestros() {
        datos = instancias.getSql().getDatosMaestra();
    }

    public void ventanaProductos(String codigo) {
        String base = txtBodega.getText();
        if (base.equals("123-22")) {
            base = "productos1";
        } else if (base.equals("BODEGA-1")) {
            base = "productos1bodega1";
        } else if (base.equals("BODEGA-2")) {
            base = "productos1bodega2";
        } else if (base.equals("BODEGA-3")) {
            base = "productos1bodega3";
        } else if (base.equals("BODEGA-4")) {
            base = "productos1bodega4";
        } else {
            base = "productos1";
        }

        buscProductos buscar = new buscProductos(null, true, false, "facturacion", base);
        buscar.setOpc("NotaDebito");
        buscar.setLocationRelativeTo(null);
        instancias.setBusProductos(buscar);
        instancias.setCampoActual(txtCodProducto);
        txtCodProducto.requestFocus();
        buscar.noEncontrado(codigo.replace("'", "//"));
        buscar.show();
    }

    public void ventanaTerceros(String nit) {
        buscClientes buscar = new buscClientes(instancias.getMenu(), true, false, null, "");
        buscar.setLocationRelativeTo(null);
        instancias.setBusClientes(buscar);
        instancias.setCampoActual(txtNit);
        txtNit.requestFocus();
        buscar.noEncontrado(nit);
        buscar.show();

    }

    static class WordWrapCellRenderer extends JTextArea implements TableCellRenderer {

        WordWrapCellRenderer() {
            setLineWrap(true);
            setWrapStyleWord(true);
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText(value.toString());
            setSize(table.getColumnModel().getColumn(column).getWidth(), 30);

            if (getPreferredSize().height > 30) {
                if (table.getRowHeight(row) != getPreferredSize().height) {
                    table.setRowHeight(row, getPreferredSize().height);
                }
            } else {
                if (table.getRowHeight(row) != getPreferredSize().height) {
                    table.setRowHeight(row, 30);
                }
            }

            if (isSelected) {
                setForeground(new Color(0, 0, 0));
                setBackground(table.getSelectionBackground());
                Font font = new Font("Century Gothic", Font.PLAIN, 15);
                setFont(font);
            } else {
                setForeground(new Color(0, 0, 0));
                setBackground(table.getBackground());
                Font font = new Font("Century Gothic", Font.PLAIN, 15);
                setFont(font);
            }
            return this;
        }
    }

    public void cargarProductos1(Object[][] productos) {
        String cantEstablecida = txtCant.getText();
        String baseUtilizada = obtenerBase();

        for (int i = 0; i < productos.length; i++) {
            ndProducto nodo = instancias.getSql().getDatosProducto(productos[i][0].toString(), baseUtilizada);

            String codigo = productos[i][0].toString();
            String cantidad = productos[i][1].toString();
            if (cantidad.equals("0")) {
                cantidad = cantEstablecida;
            }

            this.plu = true;
            if (nodo.getUsuario().equals("ADMIN")) {
                cargarProducto(codigo, cantidad, 1, "", "", "", true, "", "", "", "", "");
            } else {
                for (int j = 0; j < Integer.parseInt(cantidad); j++) {
                    cargarProducto(codigo, "1", 1, "", "", "", true, "", "", "", "", "");
                }
            }
        }

        tblProductos.changeSelection(tblProductos.getRowCount() - 1, 0, false, false);
        tblProductos.removeEditor();
        tblInventario.removeEditor();

        if (tblProductos.editCellAt(tblProductos.getRowCount() - 1, 0)) {
            tblProductos.setColumnSelectionInterval(0, 0);
            tblProductos.transferFocus();
        }

        if (datos[97].toString().equals("Valor")) {
            tblProductos.editCellAt(tblInventario.getRowCount() - 1, 2);
            tblProductos.setColumnSelectionInterval(2, 2);
            tblProductos.transferFocus();
        } else {
            tblProductos.editCellAt(tblInventario.getRowCount() - 1, 3);
            tblProductos.setColumnSelectionInterval(3, 3);
            tblProductos.transferFocus();
        }
    }

    public void cargarProducto(String codigo, String cantidad, int plu, String imei, String lote, String idProd, Boolean agrupar, String talla, String color,
            String temp, String fechaVence, String detalleMensualidad) {

        String baseUtilizada = obtenerBase();
        ndProducto nodo = null;

        String CodigoProd = "";
        if (codigo.equals("")) {
            CodigoProd = "";
        } else {
            Object[][] listado = instancias.getSql().getCodigosRelacionados(codigo, " where codigo");
            if (listado.length > 0) {
                codigo = listado[0][0].toString();
            }

            nodo = instancias.getSql().getDatosProducto(codigo, baseUtilizada);
            if (nodo.getIdSistema() != null) {
                CodigoProd = nodo.getIdSistema();
            }
        }

        if (!CodigoProd.equals("")) {
            if (codigo.equals(nodo.getCodigo2())) {
                plu = 2;
            } else if (codigo.equals(nodo.getCodigo3())) {
                plu = 3;
            } else if (codigo.equals(nodo.getCodigo4())) {
                plu = 4;
            } else if (codigo.equals(nodo.getCodigo5())) {
                plu = 5;
            } else if (codigo.equals(nodo.getCodigo6())) {
                plu = 6;
            } else if (codigo.equals(nodo.getCodigo7())) {
                plu = 7;
            } else if (codigo.equals(nodo.getCodigo8())) {
                plu = 8;
            }

            if (instancias.getConfiguraciones().isProductosDetallados()) {
                for (int j = 0; j < tblProductos.getRowCount(); j++) {
                    String idPro = tblProductos.getValueAt(j, 29).toString();
                    if (!idPro.equals("")) {
                        if (idPro.equals(idProd)) {
                            if (!imei.equals("")) {
                                metodos.msgError(this, "El imei '" + imei + "' ya esta cargado.");
                                return;
                            } else {
                                metodos.msgError(this, "Este producto con el lote '" + lote + "' ya se cargó.");
                                return;

                            }
                        }
                    }
                }
            }

            tblProductos.setDefaultRenderer(Object.class, new IconCellRenderer());

            if (cantidad.contains(
                    ".")) {
                cantidad = cantidad.replace(".", ",");
            }

            if (agrupar) {
                if (nodo.getCodigo() != null) {
                    if (nodo.getGrupo() != null) {
                        if (nodo.getCodigo().equals("IMP01") || nodo.getGrupo().equals("GRP-02")) {
                            for (int j = 0; j < tblProductos.getRowCount(); j++) {
                                if (nodo.getIdSistema().equalsIgnoreCase((String) tblProductos.getValueAt(j, 32)) && (plu + "").equals(((int) tblProductos.getValueAt(j, 12)) + "")) {
                                    tblProductos.setValueAt((big.getMoneda(tblProductos.getValueAt(j, 3).toString().replace(".", ",")).add(big.getMoneda(cantidad))).toString().replace(".", ","), j, 3);
                                    txtCodProducto.setText("");
                                    tblProductos.setColumnSelectionInterval(0, 0);
                                    tblProductos.setRowSelectionInterval(j, j);
                                    KeyEvent x = new KeyEvent(this, WIDTH, WIDTH, WIDTH, KeyEvent.VK_ENTER);
                                    tblProductosKeyReleased(x);
                                    if (instancias.isLector()) {
                                        txtCodProducto.requestFocus();
                                    } else {
                                        tblProductos.editCellAt(0, 3);
                                        tblProductos.setColumnSelectionInterval(3, 3);
                                        tblProductos.transferFocus();
                                    }
                                    return;
                                }
                            }
                        }
                    }
                }
            }

            if (agrupar) {
                try {
                    if ((Boolean) datos[50]) {
                        if (nodo.getUsuario().equals("ADMIN")) {
                            for (int j = 0; j < tblProductos.getRowCount(); j++) {
                                if (nodo.getIdSistema().equalsIgnoreCase((String) tblProductos.getValueAt(j, 32)) && (plu + "").equals(((int) tblProductos.getValueAt(j, 12)) + "")) {
                                    tblProductos.setValueAt((big.getMoneda(tblProductos.getValueAt(j, 3).toString().replace(".", ",")).add(big.getMoneda(cantidad))).toString().replace(".", ","), j, 3);
                                    txtCodProducto.setText("");

                                    tblProductos.setColumnSelectionInterval(0, 0);
                                    tblProductos.setRowSelectionInterval(j, j);

                                    KeyEvent x = new KeyEvent(this, WIDTH, WIDTH, WIDTH, KeyEvent.VK_ENTER);
                                    tblProductosKeyReleased(x);

                                    if (instancias.isLector()) {
                                        txtCodProducto.requestFocus();
                                    } else {
                                        tblProductos.editCellAt(0, 3);
                                        tblProductos.setColumnSelectionInterval(3, 3);
                                        tblProductos.transferFocus();
                                    }

                                    txtCant.setText(datos[87].toString());

                                    return;
                                }
                            }
                        } else {

                        }
                    }
                } catch (Exception e) {
                }
            }

            if (instancias.getSql().getProdActivo(nodo.getCodigo())) {
                metodos.msgError(null, "Este producto esta inactivo");
                lbProducto.requestFocus();
                return;
            }

            String tipo = "", productoEn = "";

            if (nodo.getTipoProd()
                    != null) {
                if (nodo.getTipoProd().equals("Variable")) {
                    productoEn = "Desarrollo";
                    cantidad = "1";
                }
            }

            tipo = Enums.DetalleTipoProducto.obtenerTipoProducto(nodo.getTipoProducto());

            if (!tipo.equals("") && idProd.equals("")) {
                VistaMovimientoDetalleProducto compraDetallada = new VistaMovimientoDetalleProducto(null, true, nodo, null, "Salida", TipoDocumento.NOTA_DEBITO.getValor(), BigDecimal.ZERO);
                compraDetallada.setLocationRelativeTo(null);
                compraDetallada.setVisible(true);
                return;

            } else {

                if (this.plu) {

                    this.plu = false;

                    int cant = 0;

                    if (nodo.isPlu2()) {
                        cant++;
                    }
                    if (nodo.isPlu3()) {
                        cant++;
                    }
                    if (nodo.isPlu4()) {
                        cant++;
                    }
                    if (nodo.getPlu5()) {
                        cant++;
                    }
                    if (nodo.getPlu6()) {
                        cant++;
                    }
                    if (nodo.getPlu7()) {
                        cant++;
                    }
                    if (nodo.getPlu8()) {
                        cant++;
                    }

                    if (cant > 0) {
                        seleccionarPLU pluu = new seleccionarPLU(null, true, obtenerBase());
                        pluu.setNotaDebito(this);
                        pluu.setInstancias(instancias, nodo.getIdSistema());
                        pluu.setOpc("NotaDebito");
                        pluu.setVisible(true);
                        return;
                    }
                }

                modeloInventario = (DefaultTableModel) tblInventario.getModel();
                BigDecimal aux = new BigDecimal("0.0"), iva, valor;
                valor = big.getBigDecimal(nodo.getL1());
                iva = big.getBigDecimal(nodo.getIva());
                iva = (iva.divide(big.getBigDecimal("100"))).add(big.getBigDecimal("1"));
                aux = valor.divide(iva, 2);
                aux = valor.subtract(aux);

                String cant = nodo.getFisicoInventario();
                cant = cant.replace(".", ",");

                aux = aux.divide(new BigDecimal("100"));
                //
                String cant2 = "1";
                String desc = nodo.getDescripcion();
                String lista = nodo.getL1(), lista1 = "L1";
                switch (plu) {
                    case 2:
                        cant2 = nodo.getCantidad2();
                        desc = nodo.getDescripcion2();
                        lista = nodo.getL2();
                        cant = "" + big.getMoneda(cant).divide(big.getBigDecimal(nodo.getCantidad2()), 2, RoundingMode.HALF_DOWN);
                        lista1 = "L2";
                        break;
                    case 3:
                        cant2 = nodo.getCantidad3();
                        desc = nodo.getDescripcion3();
                        lista = nodo.getL3();
                        cant = "" + big.getMoneda(cant).divide(big.getBigDecimal(nodo.getCantidad3()), 2, RoundingMode.HALF_DOWN);
                        lista1 = "L3";
                        break;
                    case 4:
                        cant2 = nodo.getCantidad4();
                        desc = nodo.getDescripcion4();
                        lista = nodo.getL4();
                        cant = "" + big.getMoneda(cant).divide(big.getBigDecimal(nodo.getCantidad4()), 2, RoundingMode.HALF_DOWN);
                        lista1 = "L4";
                        break;
                    case 5:
                        cant2 = nodo.getCantidad5();
                        desc = nodo.getDescripcion5();
                        lista = nodo.getL5();
                        cant = "" + big.getMoneda(cant).divide(big.getBigDecimal(nodo.getCantidad5()), 2, RoundingMode.HALF_DOWN);
                        lista1 = "L5";
                        break;
                    case 6:
                        cant2 = nodo.getCantidad6();
                        desc = nodo.getDescripcion6();
                        lista = nodo.getL6();
                        cant = "" + big.getMoneda(cant).divide(big.getBigDecimal(nodo.getCantidad6()), 2, RoundingMode.HALF_DOWN);
                        lista1 = "L6";
                        break;
                    case 7:
                        cant2 = nodo.getCantidad7();
                        desc = nodo.getDescripcion7();
                        lista = nodo.getL7();
                        cant = "" + big.getMoneda(cant).divide(big.getBigDecimal(nodo.getCantidad7()), 2, RoundingMode.HALF_DOWN);
                        lista1 = "L7";
                        break;
                    case 8:
                        cant2 = nodo.getCantidad8();
                        desc = nodo.getDescripcion8();
                        lista = nodo.getL8();
                        cant = "" + big.getMoneda(cant).divide(big.getBigDecimal(nodo.getCantidad8()), 2, RoundingMode.HALF_DOWN);
                        lista1 = "L8";
                        break;
                }
                Double res = 0.0;

                try {
                    res = Double.parseDouble(cant.replace(",", ".")) - Double.parseDouble(cantidad);
                } catch (Exception e) {
                    try {
                        res = big.getBigDecimal(cant.replace(",", ".")).subtract(big.getBigDecimal(cantidad.replace(",", "."))).doubleValue();
                    } catch (Exception ex) {
                        res = Double.parseDouble(cant.replace(",", ".")) - Integer.parseInt(cantidad);
                    }
                }

                if (cant.equals(",00")) {
                    cant = "0";
                }

                modeloInventario.addRow(new Object[]{lista1, big.setNumero(big.getBigDecimal(cant.replace(",", "."))), res});

                boolean datosGrupo = true;
                try {
                    datosGrupo = (boolean) instancias.getSql().getDatosGrupo(nodo.getGrupo())[1];
                } catch (Exception e) {
                    datosGrupo = true;
                }

                Icon icono = null;
                ImageIcon fot = new ImageIcon(getClass().getResource("/imagenes/eliminar.png"));
                icono = new ImageIcon(fot.getImage().getScaledInstance(30, 25, Image.SCALE_DEFAULT));

                String cadena = "";
                if (instancias.getConfiguraciones().isRestaurante()) {

                } else {
//                    if (!idProd.equals("")) {
//                        cadena = idProd;
//                    }
                }

                String grupo = "";
                if (nodo.getGrupo() != null) {
                    grupo = nodo.getGrupo();
                }

                String detalle = "";
                if (!imei.equals("")) {
                    detalle = imei;
                }

                if (!color.equals("")) {
                    if (detalle.equals("")) {
                        detalle = color;
                    } else {
                        detalle = detalle + "-" + color;
                    }
                }

                if (!talla.equals("")) {
                    if (detalle.equals("")) {
                        detalle = talla;
                    } else {
                        detalle = detalle + "-" + talla;
                    }
                }

                if (!lote.equals("")) {
                    detalle = lote;
                }

                if (!fechaVence.equals("")) {
                    if (detalle.equals("")) {
                        detalle = fechaVence;
                    } else {
                        detalle = detalle + "-" + fechaVence;
                    }
                }

                if (!detalleMensualidad.equals("")) {
                    detalle = detalleMensualidad;
                }

                modeloPro.addRow(new Object[]{nodo.getCodigo(), desc, big.setMoneda(big.getBigDecimal(lista)),
                    cantidad, big.setMoneda(big.getBigDecimal(lista)), "0", "0", big.setMoneda(big.getBigDecimal(nodo.getIva())).replace(this.simbolo + " ", ""),
                    this.simbolo + " 0", big.setMoneda(big.getBigDecimal(lista)),
                    nodo.getUbicacion1(), nodo.getReferencia(), plu,
                    (big.getBigDecimal(cant2).multiply(big.getMoneda(cantidad))), this.simbolo + " 0", "", "PENDIENTE", this.simbolo + " 0", datosGrupo, this.simbolo + " 0",
                    this.simbolo + " 0", cadena, new JLabel(icono), big.setMonedaExacta(big.getBigDecimal(nodo.getImpoconsumoVenta())).replace(this.simbolo + " ", ""), "", "", "", detalle,
                    lote, idProd, "Nuevo", "Sin-Permiso", nodo.getIdSistema(), big.setMoneda(big.getBigDecimal(aux)), grupo, nodo.getCodContable(), nodo.getUnd(),
                    nodo.getManejaInventario()});
                txtCodProducto.setText("");

                tblProductos.scrollRectToVisible(tblProductos.getCellRect(tblProductos.getRowCount() - 1, 0, true));
                cargarTotales();

                tblProductos.setColumnSelectionInterval(0, 0);
                tblProductos.setRowSelectionInterval(modeloPro.getRowCount() - 1, modeloPro.getRowCount() - 1);

                if (!this.plu) {
                    if (cmbListaPrecio.getSelectedIndex() > 0) {
                        cmbListas.setSelectedItem(cmbListaPrecio.getSelectedItem());
                        tblInventario.setValueAt(cmbListaPrecio.getSelectedItem(), tblInventario.getRowCount() - 1, 0);
                        tblInventario.setColumnSelectionInterval(0, 0);
                        tblInventario.setRowSelectionInterval(tblInventario.getRowCount() - 1, tblInventario.getRowCount() - 1);
                        cambiarListaCliente();
                    }
                }
            }

            calcularTabla(modeloPro.getRowCount() - 1, false);
            txtCant.setText(datos[87].toString());

            if (instancias.isLector()) {
                txtCodProducto.requestFocus();
            } else {
                if (datos[97].toString().equals("Valor")) {
                    tblProductos.editCellAt(tblInventario.getRowCount() - 1, 2);
                    tblProductos.setColumnSelectionInterval(2, 2);
                    tblProductos.transferFocus();
                } else {
                    tblProductos.editCellAt(tblInventario.getRowCount() - 1, 3);
                    tblProductos.setColumnSelectionInterval(3, 3);
                    tblProductos.transferFocus();
                }
            }

            return;
        }

        if (codigo.equals("")) {
            ventanaProductos(codigo);
        } else {
            metodos.msgError(this, "El codigo no existe");
            txtCodProducto.setText("");
            lbProducto.requestFocus();
        }
    }

    public void eliminarFila() {
        int fila = tblProductos.getSelectedRow();
        if (tblProductos.getValueAt(fila, 16).equals("REALIZADO")) {
            metodos.msgAdvertencia(null, "No puede Borrar este producto");
            return;
        }

        DefaultTableModel modelo = (DefaultTableModel) tblProductos.getModel();
        modelo.removeRow(fila);

        modeloInventario = (DefaultTableModel) tblInventario.getModel();
        modeloInventario.removeRow(fila);

        tblInventario.removeEditor();
        tblProductos.removeEditor();
        cargarTotales();
    }

    public String obtenerBase() {
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
        return baseUtilizada;
    }

    public void cambiarListaCliente() {
        if (tblInventario.getSelectedRow() != -1) {
            String baseUtilizada = obtenerBase();
            ndProducto codigo = instancias.getSql().getDatosProducto(tblProductos.getValueAt(tblInventario.getSelectedRow(), 32).toString(), baseUtilizada);
            String valor = "";

            switch ((String) tblInventario.getValueAt(tblInventario.getSelectedRow(), 0)) {
                case "L1":
                    valor = big.setMonedaExacta(big.getBigDecimal(codigo.getL1()));
                    break;
                case "L2":
                    valor = big.setMonedaExacta(big.getBigDecimal(codigo.getL2()));
                    break;
                case "L3":
                    valor = big.setMonedaExacta(big.getBigDecimal(codigo.getL3()));
                    break;
                case "L4":
                    valor = big.setMonedaExacta(big.getBigDecimal(codigo.getL4()));
                    break;
                case "L5":
                    valor = big.setMonedaExacta(big.getBigDecimal(codigo.getL5()));
                    break;
                case "L6":
                    valor = big.setMonedaExacta(big.getBigDecimal(codigo.getL6()));
                    break;
                case "L7":
                    valor = big.setMonedaExacta(big.getBigDecimal(codigo.getL7()));
                    break;
                case "L8":
                    valor = big.setMonedaExacta(big.getBigDecimal(codigo.getL8()));
                    break;
            }

            tblProductos.setColumnSelectionInterval(3, 3);
            tblProductos.setRowSelectionInterval(tblInventario.getSelectedRow(), tblInventario.getSelectedRow());
            tblProductos.setValueAt(valor, tblInventario.getSelectedRow(), 2);
            tblProductos.transferFocus();

            //simulando enter sobre el producto
            KeyEvent evento = new KeyEvent(tblInventario, 0, 0, 0, 0);
            evento.setKeyCode(KeyEvent.VK_ENTER);
            tblProductosKeyReleased(evento);
        }
    }

    private Object[] calcularDescuento(int fila, BigDecimal subtotal, boolean mostrarAlerta) {
        BigDecimal descuento = big.getBigDecimal("0");
        BigDecimal porcentaje = big.getBigDecimal("0");
        BigDecimal porcentaje2 = big.getBigDecimal("0");

        switch (instancias.getDescuento()) {
            case "porcentaje":
                if (!tblProductos.getValueAt(fila, 5).toString().equals("0.0") && !tblProductos.getValueAt(fila, 5).toString().equals("0")) {
                    if ((boolean) tblProductos.getValueAt(fila, 18)) {

                        if (tblProductos.getValueAt(fila, 5).toString().equals("")) {
                            porcentaje2 = big.getBigDecimal("0");
                        } else {
                            porcentaje2 = big.getBigDecimal(tblProductos.getValueAt(fila, 5).toString().replace(",", ".")).divide(big.getBigDecimal("100"));
                        }
                        porcentaje = big.getBigDecimal(porcentaje2);
                        descuento = big.getMoneda(big.setNumero(subtotal.multiply(porcentaje)));
                        porcentaje2 = porcentaje2.multiply(big.getBigDecimal("100"));
                    } else {
                        if (mostrarAlerta) {
                            metodos.msgAdvertencia(null, "Este producto no puede generar descuento.");
                        }
                        porcentaje2 = big.getBigDecimal("0");
                        descuento = big.getMoneda("0");
                    }
                } else {
                    porcentaje2 = big.getBigDecimal("0");
                    descuento = big.getMoneda("0");
                }
                break;
            case "peso":
                if (!tblProductos.getValueAt(fila, 6).toString().equals(this.simbolo + " 0")) {
                    if ((boolean) tblProductos.getValueAt(fila, 18)) {
                        descuento = big.getMoneda(tblProductos.getValueAt(fila, 6).toString());
                        porcentaje2 = big.getBigDecimal(descuento.multiply(big.getBigDecimal("100")).divide(subtotal, 2, RoundingMode.HALF_DOWN));
                    } else {
                        if (mostrarAlerta) {
                            metodos.msgAdvertencia(null, "Este producto no puede generar descuento.");
                        }
                        porcentaje2 = big.getBigDecimal("0");
                        descuento = big.getMoneda("0");
                    }
                } else {
                    porcentaje2 = big.getBigDecimal("0");
                    descuento = big.getMoneda("0");
                }
                break;
        }
        return new Object[]{descuento, porcentaje2};
    }

    public void cargarCliente(String nit) {
        ModeloContacto nodo = instancias.getSql().getDatosTercero(nit);

        if (nodo.getIdSistema() != null) {

            if (nodo.isActivo()) {
                metodos.msgError(null, "Este cliente esta inactivado");
                lbNit.requestFocus();
                return;
            }

            txtNombre.setEnabled(false);
            txtNombre.setEditable(false);

            txtIdSistema.setText(nodo.getIdSistema());
            txtNit.setText(nodo.getId());
            txtNombre.setText(nodo.getNombre());
            txtObservaciones.setText(nodo.getPlacas());

            try {
                String nombreVendedor = "";
                if (!nodo.getVendedor().equals("")) {
                    nombreVendedor = instancias.getSql().getNombreEmpleado(nodo.getVendedor());
                    cmbVendedor.setSelectedItem(nombreVendedor);
                }
            } catch (Exception e) {
            }

            try {
                cmbListaPrecio.setSelectedItem(nodo.getLista());
            } catch (Exception e) {
                cmbListaPrecio.setSelectedIndex(0);
            }

            txtCodProducto.requestFocus();

            if ((Boolean) datos[95]) {
                txtDiasPlazo.setText(nodo.getPlazo());
                calcularDiasPlazo(null);
            }

            return;
        }

        ventanaTerceros(nit);
    }

    public void limpiar(boolean actualizar) {

        rdPos.setSelected(false);
        lbNit.requestFocus();
        tblProductos.removeEditor();
        tblInventario.removeEditor();
        txtCant.setText(datos[87].toString());
        tblProductos.setEnabled(true);

        txtNombre.setEnabled(false);
        txtNombre.setEditable(false);

        btnGuardar.setEnabled(true);
        btnGuardar.setVisible(true);

        txtRtf.setText(this.simbolo + " 0");
        txtRiva.setText(this.simbolo + " 0");
        cmbRtf.setSelectedIndex(0);
        chkReteIva.setSelected(false);
        txtObservaciones.setText("");
        txtPorcentaje.setText("");
        txtCantProductos.setText("0");
        txtCantUnidades.setText("0");

        txtRelacion.setText("");
        txtFactRelacion.setText("");

        btnReImprimir.setEnabled(false);

        tblInventario.removeEditor();
        modeloInventario = (DefaultTableModel) tblInventario.getModel();

        while (tblProductos.getRowCount() > 0) {
            modeloPro.removeRow(0);
        }

        while (tblInventario.getRowCount() > 0) {
            modeloInventario.removeRow(0);
        }

        txtSubTotal.setText(this.simbolo + " 0");
        txtTotal.setText("Total: " + this.simbolo + " 0");
        txtTotalIva.setText(this.simbolo + " 0");
        txtTotalImpoconsumo.setText(this.simbolo + " 0");
        txtTotalDescuentos.setText(this.simbolo + " 0");

        if (actualizar) {
            txtNit.setText("");
            txtNombre.setText("");
            txtDiasPlazo.setText("0");
            txtVencimiento.setText(txtFechaFactura.getText());
        }

        String dsPrefijo = "";
        if (instancias.getIdND() != null) {
            dsPrefijo = instancias.getIdND();
        }
        lbNoFactura.setText(dsPrefijo + instancias.getSql().getNumConsecutivo("ND")[0].toString());

        instancias.setDevuelta(BigDecimal.ZERO);
        instancias.setEfectivoDevuelta(big.getBigDecimal("0"));
        instancias.setFranquisia("");
        instancias.setComision("");
        instancias.setValorComision(big.getBigDecimal("0"));
        instancias.setTotalFacturaComision(big.getBigDecimal("0"));
        instancias.setNcDevuelta(big.getBigDecimal("0"));
        instancias.setTarjetaDevuelta(big.getBigDecimal("0"));
        instancias.setChequeDevuelta(big.getBigDecimal("0"));
        instancias.setTarjetaCredito(big.getBigDecimal("0"));
        instancias.setPropina(big.getBigDecimal("0"));
        instancias.setPorcPropina("0");
        instancias.setCancelarFactura(false);

        tblProductos.removeEditor();
        tblInventario.removeEditor();
    }

    public void setVendedores(String[] Vendedores) {
        cmbVendedor.removeAllItems();
        for (String Vendedore : Vendedores) {
            cmbVendedor.addItem(Vendedore);
        }
    }

    private boolean esFacturacionElectronica() {
        return instancias.getConfiguraciones().isFacturaElectronica() && (Boolean) tblComprobantes.getValueAt(0, 2) == true;
    }

    private String validacionInicialFactura(boolean imprimir) {

        String vendedor = "";
        try {
            vendedor = cmbVendedor.getSelectedItem().toString();
        } catch (Exception e) {
        }

        ModeloContacto datosCliente = instancias.getSql().getDatosTercero(txtIdSistema.getText());
        if (!squemaFacturacion.validaciones_facturacion(datosCliente)) {
            return "";
        }

        if (esFacturacionElectronica()) {
            Object[] datosValidacion = new Object[]{txtFactRelacion.getText(), txtObservaciones.getText(), cmbConcepto.getSelectedIndex() == 0};
            if (!squemaFacturacionElectronica.validaciones_facturacionElectronica(datosCliente, vendedor, datosValidacion, true, false)) {
                return "";
            }
        }

        String tipoFacturacion = "";
        if (esFacturacionElectronica()) {
            if (rdPos.isSelected()) {
                tipoFacturacion = Constantes.FACTURACION_ELECTRONICA_POS;
            } else {
                tipoFacturacion = Constantes.FACTURACION_ELECTRONICA;
            }
        }

        if (!squemaFacturacion.validaciones_detalle_facturacion(tblProductos, tipoFacturacion, TipoDocumento.NOTA_DEBITO.getValor())) {
            return "";
        }

        //VALIDAMOS LAS DESCRIPCIONES DE LOS PRODUCTOS
        if (tblProductos.getRowCount() > 0) {
            for (int i = 0; i < tblProductos.getRowCount(); i++) {
                calcularTabla(i, true);
            }
        }

        tblProductos.removeEditor();
        tblInventario.removeEditor();
        Boolean bolsa = false;
        Boolean facturarSinInventario = (Boolean) datos[79];

        int cantProdFact = 0;
        String baseUtilizada = obtenerBase();
        if (!saltarPasosFactura) {
            //HACEMOS CONTEO DE LOS ITEMS DE LOS PRODUCTOS PREPARADOS
            for (int i = 0; i < tblProductos.getRowCount(); i++) {
                ndProducto nodo = instancias.getSql().getDatosProducto(tblProductos.getValueAt(i, 32).toString(), baseUtilizada);
                if (nodo.getUsuario().equals("FACTURA")) {
                    String opciones = "";
                    try {
                        opciones = tblProductos.getValueAt(i, 21).toString().split("; ")[1];
                    } catch (Exception e) {
                    }

                    if (opciones.equals("")) {
                        Object[][] productos = instancias.getSql().getCantidadesDiscosteo(tblProductos.getValueAt(i, 32).toString());
                        cantProdFact = cantProdFact + productos.length;
                    } else {
                        cantProdFact = cantProdFact + opciones.split(", ").length;
                    }
                }
            }

            //CREAMOS LOS OBJETOS
            Object[][] productosSinInventario = new Object[tblProductos.getRowCount()][4];
            Object[][] productosUtilidades = new Object[tblProductos.getRowCount()][3];
            Object[][] productosSinInventarioDis = new Object[cantProdFact][5];

            Boolean entro = false, entroUtilidad = false;
            int ser = 0, ser1 = 0, contadorUtilidades = 0;

            //INICIAMOS CON LA VALIDACIÓN DEL INVENTARIO
            for (int i = 0; i < tblProductos.getRowCount(); i++) {

                //VALIDAMOS QUE LA FACTURA INCLUYA LA BOLSA
                if (tblProductos.getValueAt(i, 32).equals("PROD-000000032")) {
                    bolsa = true;
                }

                ndProducto nodo = instancias.getSql().getDatosProducto(tblProductos.getValueAt(i, 32).toString(), baseUtilizada);

                BigDecimal num = BigDecimal.ZERO;
                try {
                    num = big.getBigDecimal(tblInventario.getValueAt(i, 2).toString().replace(",", "."));
                } catch (Exception e) {
                    num = big.getBigDecimal(tblInventario.getValueAt(i, 2).toString().replace(".", "").replace(",", "."));
                }

                //SI ES UN PRODUCTO CON DISEÑO
                if (nodo.getUsuario().equals("FACTURA")) {
                    String opciones = "";

                    try {
                        opciones = tblProductos.getValueAt(i, 21).toString().split("; ")[1];
                    } catch (Exception e) {
                    }

                    if (!opciones.equals("")) {
                        for (OpcionPreparacion opcion : ParserPreparacion.opcionesDeSegmento(opciones)) {
                            String codigo = opcion.getCodigo();
                            String cant = opcion.getCantidad();
                            String estado = opcion.getEstado();

                            if (estado.equals(" true")) {
                                ndProducto nodo1 = instancias.getSql().getDatosProducto(codigo, baseUtilizada);
                                Double cant1 = Double.parseDouble(nodo1.getFisicoInventario().replace(",", "."));
                                Double total = cant1 - Double.parseDouble(cant.replace(",", "."));

                                if (total < 0) {
                                    productosSinInventarioDis[ser1][0] = nodo1.getIdSistema();
                                    productosSinInventarioDis[ser1][1] = nodo1.getDescripcion();
                                    productosSinInventarioDis[ser1][2] = cant1;
                                    productosSinInventarioDis[ser1][3] = total;
                                    productosSinInventarioDis[ser1][4] = cant;
                                    entro = true;
                                    ser1++;
                                }
                            }
                        }
                    } else {
                        Object[][] productos = instancias.getSql().getCantidadesDiscosteo(tblProductos.getValueAt(i, 32).toString());
                        for (int k = 0; k < productos.length; k++) {
                            String codigo = productos[k][0].toString();
                            String cant = productos[k][1].toString();

                            ndProducto insumo = instancias.getSql().getDatosProducto(codigo, baseUtilizada);
                            Double cant1 = Double.parseDouble(insumo.getFisicoInventario().replace(",", "."));
                            Double total = cant1 - Double.parseDouble(cant.replace(",", "."));

                            if (total < 0) {
                                productosSinInventarioDis[ser1][0] = insumo.getIdSistema();
                                productosSinInventarioDis[ser1][1] = insumo.getDescripcion();
                                productosSinInventarioDis[ser1][2] = cant1;
                                productosSinInventarioDis[ser1][3] = total;
                                productosSinInventarioDis[ser1][4] = cant;
                                entro = true;
                                ser1++;
                            }
                        }
                    }
                } else {
                    //SI ES UN PRODUCTO NORMAL
                    if (num.compareTo(BigDecimal.ZERO) < 0) {
                        if (nodo.getManejaInventario()) {
                            productosSinInventario[ser][0] = nodo.getIdSistema();
                            productosSinInventario[ser][1] = nodo.getDescripcion();
                            productosSinInventario[ser][2] = tblInventario.getValueAt(i, 1);
                            productosSinInventario[ser][3] = tblInventario.getValueAt(i, 2);
                            entro = true;
                            ser++;
                        } else {
                            System.out.println("Este producto no maneja inventario.");
                        }
                    }
                }

                //VALIDAMOS LAS UTILIDADES DE LOS PRODUCTOS
                if (tblProductos.getValueAt(i, 15).equals("ERROR1")) {
                    productosUtilidades[contadorUtilidades][0] = tblProductos.getValueAt(i, 32);
                    productosUtilidades[contadorUtilidades][1] = tblProductos.getValueAt(i, 1);
                    productosUtilidades[contadorUtilidades][2] = "Utilidad minima sobrepasada";
                    contadorUtilidades++;
                    entroUtilidad = true;
                } else if (tblProductos.getValueAt(i, 15).equals("ERROR2")) {
                    productosUtilidades[contadorUtilidades][0] = tblProductos.getValueAt(i, 32);
                    productosUtilidades[contadorUtilidades][1] = tblProductos.getValueAt(i, 1);
                    productosUtilidades[contadorUtilidades][2] = "Utilidad maxima sobrepasada";
                    contadorUtilidades++;
                    entroUtilidad = true;
                }
            }
            //FIN DE VALIDACIÓN DEL INVENTARIO

            //VALIDAMOS SI LOS PRODUCTOS PREPARADOS TIENE ALGUNA ADICCIÓN PARA AGREGARLOS A LA FACTURA
            if (instancias.getConfiguraciones().isRestaurante()) {
                for (int i = 0; i < tblProductos.getRowCount(); i++) {
                    ndProducto nodo = instancias.getSql().getDatosProducto(tblProductos.getValueAt(i, 32).toString(), baseUtilizada);

                    if (nodo.getUsuario().equals("FACTURA")) {
                        String opciones = "";

                        try {
                            opciones = tblProductos.getValueAt(i, 21).toString().split("; ")[1];
                        } catch (Exception e) {
                        }

                        if (!opciones.equals("")) {
                            for (OpcionPreparacion opcion : ParserPreparacion.opcionesDeSegmento(opciones)) {
                                String codigo = opcion.getCodigo();
                                String cant = opcion.getCantidad();
                                String estado = opcion.getEstado();

                                if (estado.equals(" true")) {
                                    ndProducto nodo1 = instancias.getSql().getDatosProducto(codigo, baseUtilizada);

                                    if (nodo1.getGrupo() != null) {
                                        if (nodo1.getGrupo().equals("GRP-02")) {
                                            cargarProducto(codigo, cant, 1, "", "", "", false, "", "", "", "", "");
                                            tblProductos.setValueAt("PRODUCTO-AGREGADO", tblProductos.getRowCount() - 1, 31);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            //VALIDAMOS SI TIENE ALGUNA ALERTA DE UTILIDAD PARA MOSTRARLO
            if (entroUtilidad) {
                if (instancias.isMensajeUtilidad()) {
                    VistaProductosSinUtilidad prodSinUtilidad = new VistaProductosSinUtilidad(null, true, productosUtilidades, instancias.isUtilidad());
                    prodSinUtilidad.setVisible(true);

                    if (instancias.getCancelarFactura()) {
                        instancias.setCancelarFactura(false);
                        return "";
                    }
                }
            }

            //VALIDAMOS SI TIENE ALGUNA ALERTA DE PRODUCTOS SIN INVENTARIO PARA MOSTRARLO
            if (entro) {
                if (!facturarSinInventario) {
                    metodos.msgError(this, "No tiene inventario suficiente");
                    return "";
                } else {
                    dlgProductosSinInventario prodSinInventario = new dlgProductosSinInventario(null, true, productosSinInventario,
                            productosSinInventarioDis);
                    prodSinInventario.setVisible(true);

                    if (instancias.getCancelarFactura()) {
                        instancias.setCancelarFactura(false);
                        return "";
                    }
                }
            }

            //SI ES UNA FACTURA Y ES REGIMEN COMÚN, SE VALIDA EL NUMERO DE BOLSAS A FACTURAR
            if (instancias.getRegimen().equals("")) {
                if (!bolsa) {
                    if (!instancias.getConfiguraciones().isParqueadero()) {
                        if ((Boolean) datos[52]) {
                            int num = 0;
                            try {
                                num = Integer.parseInt(metodos.msgIngresarEnter(null, "Ingrese # de bolsas"));
                            } catch (Exception e) {
                                metodos.msgError(this, "Número no válido");
                                return "";
                            }

                            if (num > 0) {
                                cargarProducto("IMP01", String.valueOf(num), 1, "", "", "", true, "", "", "", "", "");
                            }
                        }
                    }
                }
                entro = false;
            }
        }

        //VALIDAMOS SI HACE PAGO A TERCEROS EN LA FACTURA
        int contador = 0;
        for (int i = 0; i < tblProductos.getRowCount(); i++) {
            BigDecimal pago = big.getMoneda(tblProductos.getValueAt(i, 19).toString());
            if (pago.compareTo(BigDecimal.ZERO) > 0) {
                BigDecimal venta = big.getMoneda(tblProductos.getValueAt(i, 9).toString());
                BigDecimal pago1 = big.getMoneda(tblProductos.getValueAt(i, 19).toString());

                BigDecimal resta = venta.subtract(pago1);

                tblProductos.setValueAt(resta, i, 20);
                if (resta.compareTo(BigDecimal.ZERO) < 0) {
                    contador = contador + 1;
                }
            }
        }

        if (contador > 0) {
            metodos.msgAdvertencia(this, "¡Hay algunos productos que no tienen utilidad!");
        }
        //FIN DE VALIDACION DE PAGO A TERCEROS

        return facturar(null, imprimir, "");
    }

    private String facturar(VistaMetodoPagos devuelta, boolean imprimir, String desde) {

        //SI ES MESA O ESTA ACTIVO SALTAR PASOS DE FACTURA, NO MOSTRAR EL MODULO DE DEVUELTA
        if (saltarPasosFactura) {
            devuelta = new VistaMetodoPagos(instancias.getMenu(), true, big.getMoneda(txtTotal.getText().replace("Total: ", "")),
                    instancias, "", txtIdSistema.getText(), big.getMoneda(txtSubTotal.getText()));
        }

        //OBTENEMOS LA BASE DE LA BODEGA QUE SE ESTA UTILIZANDO
        String baseUtilizada = obtenerBase();

        //MODULO DE DEVUELTA
        if (devuelta == null) {
            //VALIDAMOS SI LA FACTURA ES A CONTADO
            if (txtFechaFactura.getText().equals(txtVencimiento.getText())) {
                devuelta = new VistaMetodoPagos(null, true, big.getMoneda(txtTotal.getText().replace("Total: ", "")),
                        instancias, "NotaDebito", txtIdSistema.getText(), big.getMoneda(txtSubTotal.getText()));
                devuelta.show();
            } else {
                instancias.setEfectivoDevuelta(big.getBigDecimal("0"));
                instancias.setNcDevuelta(big.getBigDecimal("0"));
                instancias.setTarjetaDevuelta(big.getBigDecimal("0"));
                instancias.setTarjetaCredito(big.getBigDecimal("0"));
                instancias.setChequeDevuelta(big.getBigDecimal("0"));
                instancias.setDevuelta(BigDecimal.ZERO);
                instancias.setPorcPropina("0");
                instancias.setPropina(big.getBigDecimal("0"));
                instancias.setTotalPropina(big.getBigDecimal("0"));
            }
        } else {
            if (txtFechaFactura.getText().equals(txtVencimiento.getText())) {
                instancias.setEfectivoDevuelta(big.getBigDecimal(big.getMoneda(txtTotal.getText().replace("Total: ", ""))));
                instancias.setNcDevuelta(big.getBigDecimal("0"));
                instancias.setTarjetaDevuelta(big.getBigDecimal("0"));
                instancias.setTarjetaCredito(big.getBigDecimal("0"));
                instancias.setChequeDevuelta(big.getBigDecimal("0"));
                instancias.setDevuelta(BigDecimal.ZERO);
                instancias.setPorcPropina("0");
                instancias.setPropina(big.getBigDecimal("0"));
                instancias.setTotalPropina(big.getBigDecimal("0"));
            } else {
                instancias.setEfectivoDevuelta(big.getBigDecimal("0"));
                instancias.setNcDevuelta(big.getBigDecimal("0"));
                instancias.setTarjetaDevuelta(big.getBigDecimal("0"));
                instancias.setTarjetaCredito(big.getBigDecimal("0"));
                instancias.setChequeDevuelta(big.getBigDecimal("0"));
                instancias.setDevuelta(BigDecimal.ZERO);
                instancias.setPorcPropina("0");
                instancias.setPropina(big.getBigDecimal("0"));
                instancias.setTotalPropina(big.getBigDecimal("0"));
            }
        }

        //VALIDAMOS QUE SI CANCELARON LA FACTURA, SE REESTABLEZCAN AL VALOR INICIAL
        if (instancias.getCancelarFactura()) {
            System.out.println("DEVOLVIO LA FACTURA");
            instancias.setEfectivoDevuelta(big.getBigDecimal("0"));
            instancias.setNcDevuelta(big.getBigDecimal("0"));
            instancias.setTarjetaDevuelta(big.getBigDecimal("0"));
            instancias.setTarjetaCredito(big.getBigDecimal("0"));
            instancias.setChequeDevuelta(big.getBigDecimal("0"));
            instancias.setDevuelta(BigDecimal.ZERO);
            instancias.setPorcPropina("0");
            instancias.setPropina(big.getBigDecimal("0"));
            instancias.setTotalPropina(big.getBigDecimal("0"));
            return "";
        }

        //OBTENEMOS EL CONSECUTIVO DE CUALQUIER TIPO DE DOCUMENTO
        String prefijo = "";
        if (instancias.getIdND() != null) {
            prefijo = instancias.getIdND();
        }

        String factura = "", factura2 = "";
        factura = "ND-" + prefijo + instancias.getSql().getNumConsecutivoFact1("ND")[0].toString();
        factura2 = factura;

        String por = "";
        if (cmbRtf.getSelectedIndex() == 0) {
            por = "0";
        } else {
            por = cmbRtf.getSelectedItem().toString();
        }

        String estado = "PENDIENTE";

        //OBTENEMOS EL VENDEDOR DE LA FACTURA
        String vendedor = "";
        try {
            vendedor = cmbVendedor.getSelectedItem().toString();
        } catch (Exception e) {
            vendedor = "";
        }

        ndFactura nodo;

        //AGREGAMOS EL REGISTRO TOTALIZADO EN VERIFICADOR DE FACTURAS 
        instancias.getSql().agregarVerificarFactura(factura, txtIdSistema.getText(), factura2, instancias.getTerminal(),
                big.getMoneda(txtTotal.getText().replace("Total: ", "")), metodos.fechaConsulta(metodosGenerales.fechaHora()),
                metodos.fechaConsulta(txtVencimiento.getText()), metodos.fechaConsulta(metodos.sumarFecha(txtVencimiento.getText(), cantDias)),
                vendedor, "SIN-CONSECUTIVO", "", "");

        //OBTENEMOS LA HORA EN LA QUE SE REALIZA LA FACTURA
        String hora = metodosGenerales.hora();

        //AGREGAR FORMAS DE PAGOS
        if (instancias.getEfectivoDevuelta().compareTo(BigDecimal.ZERO) > 0) {
            String idPago = "PAGO-" + instancias.getSql().getNumConsecutivoFact1("FORMAPAGO")[0].toString();
            instancias.getSql().agregarFormaPago(idPago, factura2, "10", "EFECTIVO", instancias.getEfectivoDevuelta(), "",
                    metodos.fechaConsulta(metodosGenerales.fecha()), hora, instancias.getUsuario());
            instancias.getSql().aumentarConsecutivo("FORMAPAGO", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("FORMAPAGO")[0]) + 1);
        }

        if (instancias.getChequeDevuelta().compareTo(BigDecimal.ZERO) > 0) {
            String idPago = "PAGO-" + instancias.getSql().getNumConsecutivoFact1("FORMAPAGO")[0].toString();
            instancias.getSql().agregarFormaPago(idPago, factura2, "20", "CHEQUE", instancias.getChequeDevuelta(), "",
                    metodos.fechaConsulta(metodosGenerales.fecha()), hora, instancias.getUsuario());
            instancias.getSql().aumentarConsecutivo("FORMAPAGO", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("FORMAPAGO")[0]) + 1);
        }

        if (instancias.getTarjetaDevuelta().compareTo(BigDecimal.ZERO) > 0) {
            String idPago = "PAGO-" + instancias.getSql().getNumConsecutivoFact1("FORMAPAGO")[0].toString();
            instancias.getSql().agregarFormaPago(idPago, factura2, "49", "TARJETA_DEBITO", instancias.getTarjetaDevuelta(), "",
                    metodos.fechaConsulta(metodosGenerales.fecha()), hora, instancias.getUsuario());
            instancias.getSql().aumentarConsecutivo("FORMAPAGO", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("FORMAPAGO")[0]) + 1);
        }

        if (instancias.getTarjetaCredito().compareTo(BigDecimal.ZERO) > 0) {
            String idPago = "PAGO-" + instancias.getSql().getNumConsecutivoFact1("FORMAPAGO")[0].toString();
            instancias.getSql().agregarFormaPago(idPago, factura2, "48", "TARJETA_CREDITO", instancias.getTarjetaCredito(), "",
                    metodos.fechaConsulta(metodosGenerales.fecha()), hora, instancias.getUsuario());
            instancias.getSql().aumentarConsecutivo("FORMAPAGO", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("FORMAPAGO")[0]) + 1);
        }

        if (instancias.getConfiguraciones().isFacturaElectronica() && (Boolean) tblComprobantes.getValueAt(0, 2) == true) {
            boolean facturaElectronicaExitosa = false;
            ModeloContacto datosCliente = instancias.getSql().getDatosTercero(txtIdSistema.getText());
            ModeloFacturacionElectronica modeloFacturacionElectronica = crearModeloFacturacionEletronica(factura, factura2, datosCliente);

            try {
                boolean notaDebitoPos = rdPos.isSelected();
                facturaElectronicaExitosa = consumidorFacturacionElectronica.generarFacturacionElectronica(
                        modeloFacturacionElectronica, !notaDebitoPos, notaDebitoPos, false, false);
            } catch (Exception ex) {
                System.err.println("Hubo un error al enviar el JSON de la factura electronica: " + ex);
            }

            if (!facturaElectronicaExitosa) {
                return "";
            }
        }

        for (int i = 0; i < tblProductos.getRowCount(); i++) {

            if (!tblProductos.getValueAt(i, 16).equals("REALIZADO")) {
                String imei = "", lote = "", idProd = "";

                if (instancias.getConfiguraciones().isProductosDetallados()) {
                    imei = tblProductos.getValueAt(i, 27).toString();
                    lote = tblProductos.getValueAt(i, 28).toString();
                    idProd = tblProductos.getValueAt(i, 29).toString();
                } else if (instancias.getConfiguraciones().isParqueadero()) {
                    imei = tblProductos.getValueAt(i, 27).toString();
                }

                BigDecimal porcDesc;
                try {
                    porcDesc = big.getBigDecimal(tblProductos.getValueAt(i, 5).toString().replace(",", ".")).setScale(2, RoundingMode.HALF_DOWN);
                } catch (Exception e) {
                    porcDesc = BigDecimal.ZERO;
                }

                String preparacion = "";

                try {
                    preparacion = tblProductos.getValueAt(i, 21).toString();
                } catch (Exception e) {
                }

                String bodega = txtBodega.getText();
                if (!instancias.getConfiguraciones().isInventarioBodegas()) {
                    bodega = "123-22";
                }

                if (instancias.getSql().getDatosProducto(tblProductos.getValueAt(i, 32).toString(), baseUtilizada).getUsuario().equalsIgnoreCase("FACTURA")) {
                    instancias.getArmado().facturarPreparado(tblProductos.getValueAt(i, 32).toString(), tblProductos.getValueAt(i, 3).toString(),
                            preparacion, bodega);
                }

                String garantia = "", cotizacionesAsociadas = "", mesFacturar = "", turno = "", placa = "", loteCuentasCobro = "", numPedido = "", consecutivoCosteo = "",
                        congelada = "";

                BigDecimal copago = BigDecimal.ZERO;

                BigDecimal ponderado = BigDecimal.ZERO;
                try {
                    UltimoPonderado ultimoPonderado = servicioActualizacionPonderado.obtenerUltimoPonderado(tblProductos.getValueAt(i, 32).toString());
                    ponderado = ultimoPonderado.getNuevoPonderado();
                } catch (SQLException ex) {
                    Logger.getLogger(VistaInventarioInicial.class.getName()).log(Level.SEVERE, null, ex);
                    alertas.bigAlert("No se pudo consultar el último ponderado del producto");
                }

                String tipoFacturacion = Constantes.FACTURACION_NORMAL;
                if (instancias.getConfiguraciones().isFacturaElectronica() && (Boolean) tblComprobantes.getValueAt(0, 2) == true) {
                    if (rdPos.isSelected()) {
                        tipoFacturacion = Constantes.FACTURACION_ELECTRONICA_POS;
                    } else {
                        tipoFacturacion = Constantes.FACTURACION_ELECTRONICA;
                    }
                }

                Object[] vector = {factura, txtIdSistema.getText(), vendedor, "", metodos.fechaConsulta(metodosGenerales.fechaHora()),
                    metodos.fechaConsulta(txtVencimiento.getText()), instancias.getEfectivoDevuelta(), instancias.getNcDevuelta(), instancias.getChequeDevuelta(),
                    instancias.getTarjetaDevuelta(), big.getMoneda(txtTotal.getText().replace("Total: ", "")),
                    big.getMoneda(txtTotalDescuentos.getText()),
                    big.getMoneda(txtTotalIva.getText()), big.getMoneda(txtSubTotal.getText()), cotizacionesAsociadas,
                    factura.replace("ND-", ""), false, "", !txtFechaFactura.getText().equals(txtVencimiento.getText()),
                    loteCuentasCobro, instancias.getUsuario(), big.getMoneda(txtRiva.getText()), big.getMoneda("0"), big.getMoneda(txtRtf.getText()),
                    big.getMoneda(por), txtObservaciones.getText(), false, "", false, "", "",
                    metodos.fechaConsulta(metodos.sumarFecha(txtVencimiento.getText(), cantDias)), instancias.getTerminal(),
                    estado, "", instancias.getDevuelta(), factura2, instancias.getResolucion(), metodos.fechaConsulta(metodosGenerales.fecha()), "", "",
                    copago, placa, garantia, numPedido, tblProductos.getValueAt(i, 31), "",
                    "", congelada, tblProductos.getValueAt(i, 32), big.getMoneda((String) tblProductos.getValueAt(i, 2)),
                    tblProductos.getValueAt(i, 3).toString().replace(",", "."), big.getMoneda((String) tblProductos.getValueAt(i, 6)),
                    big.getMoneda((String) tblProductos.getValueAt(i, 9)), big.getMoneda((String) tblProductos.getValueAt(i, 33)),
                    big.getMoneda((String) tblProductos.getValueAt(i, 4)), "", big.getMoneda((String) tblProductos.getValueAt(i, 14)), "",
                    porcDesc + "", tblProductos.getValueAt(i, 1), tblProductos.getValueAt(i, 12) + "", tblProductos.getValueAt(i, 13).toString().replace(",", "."),
                    "PENDIENTE", tblProductos.getValueAt(i, 7), tblProductos.getValueAt(i, 19), big.getMoneda((String) tblProductos.getValueAt(i, 20)),
                    preparacion, big.getMoneda(txtImpuesto.getText()), turno, big.getMoneda(txtTotalImpoconsumo.getText()), instancias.getFranquisia(),
                    instancias.getComision(), instancias.getValorComision(), instancias.getTotalFacturaComision(), imei, lote, idProd,
                    mesFacturar, instancias.getTarjetaCredito(), instancias.getTotalPropina(), instancias.getPorcPropina(),
                    consecutivoCosteo, hora, tblProductos.getValueAt(i, 23).toString().replace(".", "").replace(",", "."),
                    big.getMoneda((String) tblProductos.getValueAt(i, 8)), false, txtBodega.getText(), ponderado, tipoFacturacion
                };

                nodo = metodos.llenarFactura(vector);

                if (!instancias.getSql().agregarFactura(nodo)) {
                    boolean noPuedaGuardar = false;

                    instancias.getSql().eliminarFactura(factura);
                    while (!noPuedaGuardar) {
                        noPuedaGuardar = instancias.getSql().eliminarFactura(factura);
                    }

                    metodos.msgError(null, "Error al guardar la factura");
                    return null;
                }
            }
        }

        TipoDocumento tipoMovimiento = TipoDocumento.NOTA_DEBITO;
        String tablaUtilizada = enumBodegas.TipoBodega.BODEGA_PRINCIPAL.getNombreTabla();
        List<MovimientoInventario> productos = generarListadoProductos(tablaUtilizada);
        ServicioInventario servicioInventario = new ServicioInventario(productos, new ArrayList<DetalleProducto>(), tipoMovimiento, "", tablaUtilizada, instancias.getUsuario(), null);

        try {
            servicioInventario.procesarMovimiento();
        } catch (SQLException ex) {
            Logger.getLogger(VistaInventarioInicial.class.getName()).log(Level.SEVERE, null, ex);
        }

        //PROCESO GUARDAR CUENTA POR COBRAR
        if (!txtFechaFactura.getText().equals(txtVencimiento.getText())) {
            if (txtDiasPlazo.getText().equals("")) {
                txtDiasPlazo.setText("0");
            }

            Object[] vectCxc = {factura, "NOTADEBITO", "PEND", "", big.getMoneda(txtTotal.getText().replace("Total: ", "")), txtDiasPlazo.getText(),
                metodos.fechaConsulta(txtVencimiento.getText()), instancias.getUsuario(), instancias.getTerminal(), false, factura2};

            ndCxc nodoCxc = metodos.llenarCxc(vectCxc);

            if (!instancias.getSql().agregarCxc(nodoCxc)) {
                metodos.msgError(null, "Error al guardar la ND en cartera");
            }
        }

        if (!instancias.getSql().aumentarConsecutivo("ND", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("ND")[0]) + 1)) {
            metodos.msgError(this, "Error al aumentar consecutivo de ND");
        }

        lbNoFactura.setText(obtenerPrefijoNotaDebito() + instancias.getSql().getNumConsecutivo("ND")[0].toString());
        lbProducto.requestFocus();

        if (!saltarPasosFactura) {
            lbObservaciones.requestFocus();
            metodos.msgExito(null, "Nota debito Exitosa");
        }

        if (txtDiasPlazo.getText()
                .equals("0")) {
            if ((Boolean) datos[91]) {
                if (!saltarPasosFactura) {
                    VistaDevuelta devueltaTotal = new VistaDevuelta(instancias.getMenu(), true, instancias, instancias.getDevuelta());
                    devueltaTotal.setVisible(true);
                }
            }
        }

//        if (imprimir) {
//            imprimir(factura, factura2);
//        }
        if (instancias.isUbicacion()) {
            try {
                if (metodos.msgPregunta(null, "¿Desea imprimir ubicación?") == 0) {
                    instancias.getReporte().ver_ubicacion(factura2, false);
                }
            } catch (Exception e) {
            }
        }

        saltarPasosFactura = false;

        limpiar(
                true);

        return factura2;
    }

    private List<MovimientoInventario> generarListadoProductos(String tablaUtilizada) {

        String SIN_ID_PRODUCTO_DETALLE = "";
        List<MovimientoInventario> movimientos = new ArrayList<>();

        for (int i = 0; i < tblProductos.getRowCount(); i++) {
            ndProducto producto = instancias.getSql().getDatosProducto(tblProductos.getValueAt(i, 32).toString(), tablaUtilizada);
            BigDecimal cantidad = Utilidades.convertirBigDecimal(tblProductos.getValueAt(i, 13).toString());
            MovimientoInventario inventario = new MovimientoInventario(producto, cantidad, BigDecimal.ZERO, SIN_ID_PRODUCTO_DETALLE);
            movimientos.add(inventario);
        }

        return movimientos;
    }

    private String obtenerValorTabla(int row, int col) {
        Object value = tblProductos.getValueAt(row, col);
        return value != null ? value.toString() : "";
    }

    private ModeloDetalleImpuestos obtenerImpuestosFacturas(BigDecimal dsPorcentajeReteFuente) {

        List<Integer> ivas = new ArrayList<>();
        List<Integer> impoconsumos = new ArrayList<>();
        for (int i = 0; i < tblProductos.getRowCount(); i++) {
            int porcentajeIva = Integer.parseInt(tblProductos.getValueAt(i, Constantes.COLUMNA_PORCENTAJE_IVA).toString());
            int porcentajeConsumo = Integer.parseInt(tblProductos.getValueAt(i, Constantes.COLUMNA_PORCENTAJE_IMPOCONSUMO).toString());

            if (porcentajeIva != 0 && !ivas.contains(porcentajeIva)) {
                ivas.add(porcentajeIva);
            }

            if (porcentajeConsumo != 0 && !impoconsumos.contains(porcentajeConsumo)) {
                impoconsumos.add(porcentajeConsumo);
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
                if (Integer.parseInt(tblProductos.getValueAt(j, Constantes.COLUMNA_PORCENTAJE_IVA).toString()) == ivas.get(i)) {
                    subtotal = subtotal.add(big.getMoneda(tblProductos.getValueAt(j, Constantes.COLUMNA_SUBTOTAL).toString()));
                    impuesto = impuesto.add(big.getMoneda(tblProductos.getValueAt(j, Constantes.COLUMNA_VALOR_IVA).toString()));
                }
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
                if (Integer.parseInt(tblProductos.getValueAt(j, Constantes.COLUMNA_PORCENTAJE_IMPOCONSUMO).toString()) == impoconsumos.get(i)) {
                    subtotal = subtotal.add(big.getMoneda(tblProductos.getValueAt(j, Constantes.COLUMNA_SUBTOTAL).toString()));
                    impuesto = impuesto.add(big.getMoneda(tblProductos.getValueAt(j, Constantes.COLUMNA_VALOR_IMPOCONSUMO).toString()));
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

    private ModeloFacturacionElectronica crearModeloFacturacionEletronica(String factura, String factura2, ModeloContacto datosCliente) {

        ModeloFacturacionElectronica modeloFacturacionElectronica = new ModeloFacturacionElectronica();
        modeloFacturacionElectronica.setDsPrefijo(obtenerPrefijoNotaDebito());
        modeloFacturacionElectronica.setDsNumeroFactura(factura.replace("ND-", "").replace(obtenerPrefijoNotaDebito(), ""));
        modeloFacturacionElectronica.setDsVendedor(cmbVendedor.getSelectedItem().toString());
        modeloFacturacionElectronica.setFechaEmision(metodos.fecha4(metodosGenerales.fecha()) + " " + metodosGenerales.fechaHora().split(" ")[1]);
        modeloFacturacionElectronica.setFechaVencimiento(metodos.fecha4(txtVencimiento.getText()));
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
        modeloFacturacionElectronica.setTipoDocumentoElectronico("NOTA_DEBITO");

        BigDecimal porcentajeIva = big.getMoneda(txtTotalIva.getText()).divide(big.getMoneda(txtSubTotal.getText()), 2, BigDecimal.ROUND_HALF_UP).multiply(big.getBigDecimal(100));
        modeloFacturacionElectronica.setPorcentajeIva(porcentajeIva);

        BigDecimal porcentajeConsumo = big.getMoneda(txtTotalImpoconsumo.getText()).divide(big.getMoneda(txtSubTotal.getText()), 2, BigDecimal.ROUND_HALF_UP).multiply(big.getBigDecimal(100));
        modeloFacturacionElectronica.setPorcentajeConsumo(porcentajeConsumo);

        modeloFacturacionElectronica.setDsPorcentajeReteFuente(obtenerPorcentajeRetencionFuente());
        modeloFacturacionElectronica.setDsRetencionFuente(big.getMoneda(txtRtf.getText()));
        modeloFacturacionElectronica.setDsPorcentajeReteIva(chkReteIva.isSelected() ? big.getBigDecimal(15) : BigDecimal.ZERO);
        modeloFacturacionElectronica.setDsRetencionIva(big.getMoneda(txtRiva.getText()));

        BigDecimal porcentajeDescuento = big.getMoneda(txtTotalDescuentos.getText()).divide(big.getMoneda(txtSubTotal.getText()), 2, BigDecimal.ROUND_HALF_UP).multiply(big.getBigDecimal(100));
        modeloFacturacionElectronica.setDsPorcentajeDescuento(porcentajeDescuento);
        modeloFacturacionElectronica.setDsDescuento(big.getMoneda(txtTotalDescuentos.getText()));

        BigDecimal valorBaseImponible = BigDecimal.ZERO;
        for (int i = 0; i < tblProductos.getRowCount(); i++) {
            if (Integer.parseInt(tblProductos.getValueAt(i, Constantes.COLUMNA_PORCENTAJE_IVA).toString()) > 0
                    || Integer.parseInt(tblProductos.getValueAt(i, Constantes.COLUMNA_PORCENTAJE_IMPOCONSUMO).toString()) > 0) {
                valorBaseImponible = valorBaseImponible.add(big.getMoneda(tblProductos.getValueAt(i, Constantes.COLUMNA_SUBTOTAL).toString()));
            }
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

        String tipoOperacion = rdPos.isSelected() ? "NOTA_DEBITO_POS" : "NOTA_DEBITO_REFERENCIA";
        int tipoPlantilla = rdPos.isSelected() ? 2 : 1;
        modeloFacturacionElectronica.setTipoOperacion(tipoOperacion);
        modeloFacturacionElectronica.setCdTipoPlantilla(tipoPlantilla);

        modeloFacturacionElectronica.setVersionDian("2");
        modeloFacturacionElectronica.setResponsabilidadesFiscales(obtenerResponsabilidadesFiscales());

        modeloFacturacionElectronica.setPrefijoFacturaReferencia(obtenerDatosFactura()[0]);
        modeloFacturacionElectronica.setNumeroFacturaReferencia(obtenerDatosFactura()[1]);
        modeloFacturacionElectronica.setConceptoNotaDebito(cmbConcepto.getSelectedItem().toString());
        modeloFacturacionElectronica.setDescripcionNotaDebito(txtObservaciones.getText());

        ModeloDescuentos[] resultadosDescuentos = obtenerDescuentosFactura(modeloFacturacionElectronica.getDescuentoTotal());
        modeloFacturacionElectronica.setDescuentosFactura(resultadosDescuentos);

        Object[] informacionDePagos = obtenerInformacionDePagos(factura2);
        modeloFacturacionElectronica.setFormaPago(informacionDePagos[0].toString());
        modeloFacturacionElectronica.setMedioPago(informacionDePagos[1].toString());
        modeloFacturacionElectronica.setFechaVencimientoPago(informacionDePagos[2].toString());
        modeloFacturacionElectronica.setIdPago(informacionDePagos[3].toString());

        ModeloDetalleImpuestos resultadosImpuestos = obtenerImpuestosFacturas(obtenerPorcentajeRetencionFuente());
        modeloFacturacionElectronica.setImpuestosFactura(resultadosImpuestos);

        ModeloDetalleProductos[] detalleProductos = obtenerDetalleProductos(factura);
        modeloFacturacionElectronica.setDetalleProductos(detalleProductos);

        return modeloFacturacionElectronica;
    }

    private ModeloDetalleProductos[] obtenerDetalleProductos(String factura) {

        ModeloDetalleProductos[] detalladoProductos = new ModeloDetalleProductos[tblProductos.getRowCount()];

        for (int i = 0; i < tblProductos.getRowCount(); i++) {
            ModeloDetalleProductos modeloIndividual = new ModeloDetalleProductos();
            modeloIndividual.setNumeroFactura(factura);
            modeloIndividual.setCodigoArticulo(tblProductos.getValueAt(i, Constantes.COLUMNA_CODIGO_PRODUCTO).toString());
            modeloIndividual.setEstandarProducto("UNSPSC");
            modeloIndividual.setDescripcionArticulo(tblProductos.getValueAt(i, Constantes.COLUMNA_DESCRIPCION_PRODUCTO).toString());
            modeloIndividual.setPorcentajeIva(big.getBigDecimal(tblProductos.getValueAt(i, Constantes.COLUMNA_PORCENTAJE_IVA).toString()));
            modeloIndividual.setPorcentajeConsumo(big.getBigDecimal(tblProductos.getValueAt(i, Constantes.COLUMNA_PORCENTAJE_IMPOCONSUMO).toString()));
            modeloIndividual.setCantidad(tblProductos.getValueAt(i, Constantes.COLUMNA_CANTIDAD).toString());
            modeloIndividual.setPrecioUnitario(big.getMoneda(tblProductos.getValueAt(i, Constantes.COLUMNA_VALOR_PRODUCTO).toString()));
            modeloIndividual.setValorTotalArticulo(big.getMoneda(tblProductos.getValueAt(i, 9).toString()));

            BigDecimal totalIva = big.getMoneda(tblProductos.getValueAt(i, Constantes.COLUMNA_VALOR_IVA).toString());
            BigDecimal totalImpoconsumo = big.getMoneda(tblProductos.getValueAt(i, 8).toString());
            modeloIndividual.setValorIva(totalIva);
            modeloIndividual.setValorConsumo(totalImpoconsumo);
            modeloIndividual.setUnidadMedida(tblProductos.getValueAt(i, Constantes.COLUMNA_UNIDAD_MEDIDA_NOTA_DEBITO).toString());

            BigDecimal valorTotalBruto = big.getMoneda(tblProductos.getValueAt(i, 2).toString()).multiply(big.getBigDecimal(tblProductos.getValueAt(i, 3).toString()));
            if (instancias.isPvpConIva()) {
                valorTotalBruto = valorTotalBruto.subtract(totalIva);
            }
            if (instancias.isPvpConImpoconsumo()) {
                valorTotalBruto = valorTotalBruto.subtract(totalImpoconsumo);
            }

            modeloIndividual.setValorTotalBruto(valorTotalBruto);
            modeloIndividual.setUnidadesEmpaque(big.getBigDecimal(tblProductos.getValueAt(i, 13)).divide(big.getBigDecimal(tblProductos.getValueAt(i, 3)), 2, RoundingMode.HALF_UP));
            modeloIndividual.setValorTotalImpuestosRetenciones(big.getMoneda(tblProductos.getValueAt(i, 8).toString()).add(big.getMoneda(tblProductos.getValueAt(i, 33).toString())));
            modeloIndividual.setCodigoVendedor(instancias.getSql().getIdEmpleado(cmbVendedor.getSelectedItem().toString()));
            modeloIndividual.setObservacionDetalle(tblProductos.getValueAt(i, 27).toString());

            ModeloDescuentos[] resultadosDescuentos = obtenerDescuentosProducto(modeloIndividual, i);
            modeloIndividual.setDescuentoProducto(resultadosDescuentos);

            Object[][] impuestosProducto = obtenerImpuestosPorProducto(modeloIndividual, i);
            modeloIndividual.setImpuestosProducto(impuestosProducto);

            detalladoProductos[i] = modeloIndividual;
        }

        return detalladoProductos;
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

    private ModeloDescuentos[] obtenerDescuentosProducto(ModeloDetalleProductos informacionProducto, int filaProducto) {

        String codigoDescuento = "", descripcionDescuento = "";
        if (!"".equals(tblProductos.getValueAt(filaProducto, 31).toString())) {
            codigoDescuento = tblProductos.getValueAt(filaProducto, 31).toString().split("///")[0];
            try {
                descripcionDescuento = tblProductos.getValueAt(filaProducto, 31).toString().split("///")[1];
            } catch (Exception e) {
            }
        }

        BigDecimal valorBase = big.getMoneda(tblProductos.getValueAt(filaProducto, 4).toString());
        BigDecimal descuentoProducto = big.getMoneda(tblProductos.getValueAt(filaProducto, 6).toString());
        ModeloDescuentos[] informacionDescuentos = new ModeloDescuentos[1];

        if (big.getBigDecimal(tblProductos.getValueAt(filaProducto, 5)).compareTo(BigDecimal.ZERO) > 0) {
            ModeloDescuentos modeloDescuento = new ModeloDescuentos();
            modeloDescuento.setTipo(false);
            modeloDescuento.setRazonDescuento(descripcionDescuento);
            modeloDescuento.setValorDescuento(formatoDosDecimales.format(descuentoProducto).replace(",", "."));
            modeloDescuento.setValorBase(formatoDosDecimales.format(valorBase).replace(",", "."));
            modeloDescuento.setPorcentaje(formatoDosDecimales.format(big.getBigDecimal(tblProductos.getValueAt(filaProducto, 5))).replace(",", "."));
            modeloDescuento.setCodigoDescuento(codigoDescuento);
            informacionDescuentos[0] = modeloDescuento;
        }

        return informacionDescuentos;
    }

    private ModeloDescuentos[] obtenerDescuentosFactura(BigDecimal descuentosDocumento) {

        String codigoDescuento = "", descripcionDescuento = "";
        if (!"".equals(tblProductos.getValueAt(0, 31).toString())) {
            codigoDescuento = tblProductos.getValueAt(0, 31).toString().split("///")[0];
            try {
                descripcionDescuento = tblProductos.getValueAt(0, 31).toString().split("///")[1];
            } catch (Exception e) {
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

    private Object[] obtenerInformacionDePagos(String numeroFactura) {
        String fechaVencimiento = metodos.fecha4(txtVencimiento.getText());
        String minutosFactura = metodosGenerales.hora().split(":")[0];
        String segundosFactura = metodosGenerales.hora().split(":")[1];

        if (minutosFactura.length() == 1) {
            minutosFactura = "0" + minutosFactura;
        }

        if (segundosFactura.length() == 1) {
            segundosFactura = "0" + segundosFactura;
        }

        Object[] informacionMetodosPagos = new Object[4];
        if (!txtFechaFactura.getText().equals(txtVencimiento.getText())) {
            informacionMetodosPagos[0] = "CREDITO";
            informacionMetodosPagos[1] = "CREDITO_ACH";
            informacionMetodosPagos[2] = fechaVencimiento + " " + minutosFactura + ":" + segundosFactura + ":00";
            informacionMetodosPagos[3] = "";
        } else {
            if (instancias.getEfectivoDevuelta().compareTo(BigDecimal.ZERO) > 0) {
                informacionMetodosPagos[0] = "CONTADO";
                informacionMetodosPagos[1] = "EFECTIVO";
                informacionMetodosPagos[2] = fechaVencimiento + " " + minutosFactura + ":" + segundosFactura + ":00";
                informacionMetodosPagos[3] = instancias.getSql().getIDPago(numeroFactura, "EFECTIVO");
            } else if (instancias.getChequeDevuelta().compareTo(BigDecimal.ZERO) > 0 && instancias.getEfectivoDevuelta().compareTo(BigDecimal.ZERO) == 0) {
                informacionMetodosPagos[0] = "CONTADO";
                informacionMetodosPagos[1] = "CHEQUE";
                informacionMetodosPagos[2] = fechaVencimiento + " " + minutosFactura + ":" + segundosFactura + ":00";
                informacionMetodosPagos[3] = instancias.getSql().getIDPago(numeroFactura, "CHEQUE");
            } else if (instancias.getTarjetaCredito().compareTo(BigDecimal.ZERO) > 0 && instancias.getEfectivoDevuelta().compareTo(BigDecimal.ZERO) == 0) {
                informacionMetodosPagos[0] = "CONTADO";
                informacionMetodosPagos[1] = "TARJETA_CREDITO";
                informacionMetodosPagos[2] = fechaVencimiento + " " + minutosFactura + ":" + segundosFactura + ":00";
                informacionMetodosPagos[3] = instancias.getSql().getIDPago(numeroFactura, "TARJETA_CREDITO");
            } else if (instancias.getTarjetaDevuelta().compareTo(BigDecimal.ZERO) > 0 && instancias.getEfectivoDevuelta().compareTo(BigDecimal.ZERO) == 0) {
                informacionMetodosPagos[0] = "CONTADO";
                informacionMetodosPagos[1] = "TARJETA_DEBITO";
                informacionMetodosPagos[2] = fechaVencimiento + " " + minutosFactura + ":" + segundosFactura + ":00";
                informacionMetodosPagos[3] = instancias.getSql().getIDPago(numeroFactura, "TARJETA_DEBITO");
            }
        }

        return informacionMetodosPagos;
    }

    private String[] obtenerDatosFactura() {
        String[] datosFactura = new String[2];
        String dsNumeroFacturaReferencia = "";
        String dsPrefijoFacturaReferencia = "";
        String referenciaFactura = txtRelacion.getText();
        int inicio = 0;
        int inicio1 = 1;
        for (int i = 0; i < referenciaFactura.length(); i++) {
            try {
                if (Integer.parseInt(referenciaFactura.substring(inicio, inicio1)) > 0) {
                    dsPrefijoFacturaReferencia = referenciaFactura.substring(0, inicio);
                    dsNumeroFacturaReferencia = referenciaFactura.substring(inicio, referenciaFactura.length());
                    break;
                }
            } catch (Exception e) {
            }

            inicio++;
            inicio1++;
        }

        datosFactura[0] = dsPrefijoFacturaReferencia;
        datosFactura[1] = dsNumeroFacturaReferencia;
        return datosFactura;
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

    private BigDecimal obtenerPorcentajeRetencionFuente() {
        BigDecimal porcentajeRetencion = BigDecimal.ZERO;
        if (cmbRtf.getSelectedIndex() > 0) {
            porcentajeRetencion = big.getBigDecimal(cmbRtf.getSelectedItem());
        }

        return porcentajeRetencion;
    }

    private String obtenerRegimen(boolean esResponsableIva) {
        String regimenAdquirente = "SIMPLE";
        if (esResponsableIva) {
            regimenAdquirente = "ORDINARIO";
        }

        return regimenAdquirente;
    }

    private String obtenerPrefijoNotaDebito() {
        String dsPrefijo = "";
        if (instancias.getIdND() != null) {
            dsPrefijo = instancias.getIdND();
        }

        return dsPrefijo;
    }

    public Object[] getOcultarIvaPanama() {
        return new Object[]{tblProductos, chkReteIva, txtRiva, cmbRtf, txtRtf, txtImpuesto, txtTotalImpoconsumo,
            lbImpoconsumo, lbImpuestoBolsa};
    }

    public Object[] getOcultarIva() {
        return new Object[]{tblProductos, chkReteIva, txtRiva, txtIva, txtTotalIva, cmbRtf, txtRtf, txtImpuesto, txtTotalImpoconsumo,
            lbImpoconsumo, lbImpuestoBolsa};
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBusProd;
    private javax.swing.JButton btnBuscTerceros;
    private javax.swing.JButton btnBuscTerceros1;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnReImprimir;
    private javax.swing.JCheckBox chkReteIva;
    private javax.swing.JComboBox cmbConcepto;
    private javax.swing.JComboBox cmbListaPrecio;
    private javax.swing.JComboBox cmbListas;
    private javax.swing.JComboBox cmbRtf;
    private javax.swing.JComboBox cmbVendedor;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JLabel lbBodega;
    private javax.swing.JLabel lbCar;
    private javax.swing.JLabel lbDiasPlazo;
    private javax.swing.JTextField lbFacturaNo;
    private javax.swing.JLabel lbImpoconsumo;
    private javax.swing.JLabel lbImpuestoBolsa;
    private javax.swing.JLabel lbNit;
    private javax.swing.JLabel lbNit1;
    private javax.swing.JTextField lbNoFactura;
    private javax.swing.JLabel lbObservaciones;
    private javax.swing.JLabel lbProducto;
    private javax.swing.JLabel lbProducto1;
    private javax.swing.JLabel lbRelacion;
    private javax.swing.JLabel lbRelacion1;
    private javax.swing.JLabel lbSubtotal;
    private javax.swing.JLabel lbTipoOperacion1;
    private javax.swing.JLabel lbTotalDescuento;
    private javax.swing.JLabel lbTotalDescuento1;
    private javax.swing.JLabel lbTotalDescuento2;
    private javax.swing.JLabel lbVendedor;
    private javax.swing.JLabel lbVendedor1;
    private javax.swing.JPanel pnlComprobante;
    private javax.swing.JPanel pnlFormulario;
    private javax.swing.JPanel pnlInformacion;
    private javax.swing.JPanel pnlOcultar;
    private javax.swing.JMenuItem popBorrar;
    private javax.swing.JRadioButton rdPos;
    private javax.swing.JScrollPane scrFormulario;
    private javax.swing.JScrollPane scrInventario;
    private javax.swing.JScrollPane scrProductos1;
    private javax.swing.JTable tblComprobantes;
    private javax.swing.JTable tblInventario;
    private javax.swing.JTable tblProductos;
    private javax.swing.JTextField txtBodega;
    private javax.swing.JTextField txtCant;
    private javax.swing.JLabel txtCantProductos;
    private javax.swing.JLabel txtCantUnidades;
    private javax.swing.JTextField txtCartera;
    private javax.swing.JTextField txtCodProducto;
    private javax.swing.JTextField txtDiasPlazo;
    private javax.swing.JTextField txtFactRelacion;
    private javax.swing.JTextField txtFechaFactura;
    private javax.swing.JTextField txtIdSistema;
    private javax.swing.JLabel txtImpuesto;
    private javax.swing.JLabel txtIva;
    private javax.swing.JTextField txtNit;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextArea txtObservaciones;
    private javax.swing.JTextField txtPorcentaje;
    private javax.swing.JTextField txtRelacion;
    private javax.swing.JLabel txtRiva;
    private javax.swing.JLabel txtRtf;
    private javax.swing.JLabel txtSubTotal;
    private javax.swing.JLabel txtTotal;
    private javax.swing.JLabel txtTotalDescuentos;
    private javax.swing.JLabel txtTotalImpoconsumo;
    private javax.swing.JLabel txtTotalIva;
    private javax.swing.JTextField txtVencimiento;
    // End of variables declaration//GEN-END:variables
}
