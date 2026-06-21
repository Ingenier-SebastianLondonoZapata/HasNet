package Vista.Productos;

import Consumidor.DocumentoSoporte.consumidorDocumentoSoporte;
import Controlador.Alertas.ControladorAlertas;
import Controlador.BarraProceso.controladorBarraProceso;
import Controlador.BarraProceso.jcThread;
import DAO.Configuraciones.DaoResoluciones;
import Enums.TipoDocumento;
import Enums.enumBodegas;
import Modelo.DocumentoSoporte.Entrada.ModeloDocumentoSoporte;
import Modelo.DocumentosElectronicos.ModeloDescuentos;
import Modelo.DocumentosElectronicos.ModeloDetalleImpuestos;
import Modelo.DocumentosElectronicos.ModeloDetalleProductos;
import Modelo.Inventario.DetalleProducto;
import Modelo.Inventario.MovimientoInventario;
import Modelo.Inventario.UltimoPonderado;
import Modelo.Maestra.ModeloResolucion;
import Utilidades.Constantes;
import Validaciones.Compras.squemaCompras;
import Validaciones.DocumentoSoporte.squemaDocumentoSoporte;
import Vista.BarraProceso.vistaBarraProceso;
import clases.Cartera.ndCxp;
import clases.IconCellRenderer;
import clases.Instancias;
import clases.big;
import clases.metodosGenerales;
import clases.productos.ndCompra;
import clases.productos.ndIngreso;
import clases.productos.ndProducto;
import Modelo.Terceros.ModeloContacto;
import Servicio.Inventario.ServicioActualizacionPonderado;
import Servicio.Inventario.ServicioInventario;
import Utilidades.DatosMaestra;
import Utilidades.DetalleProducto.UtilidadesDetalleProducto;
import Utilidades.DocumentosElectronicos;
import Utilidades.Numeros;
import Utilidades.Utilidades;
import formularios.Tesoreria.dlgTipoEgreso;
import formularios.Ventas.dlgInformacionCliente;
import formularios.Ventas.dlgTipoDescuento;
import formularios.productos.buscProductos;
import formularios.productos.dlgCompraDetallada1;
import formularios.productos.dlgDetalleCompra;
import formularios.productos.seleccionarPLU;
import formularios.terceros.buscBodegas;
import formularios.terceros.buscClientes;
import java.awt.Color;
import java.awt.Event;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import static java.awt.image.ImageObserver.WIDTH;
import java.io.File;
import java.io.IOException;
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
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class VistaIngreso extends javax.swing.JPanel {

    private ServicioActualizacionPonderado servicioActualizacionPonderado = new ServicioActualizacionPonderado();

    private final DocumentosElectronicos documentosElectronicos = new DocumentosElectronicos();
    private final consumidorDocumentoSoporte consumidorDocumentoSoporte = new consumidorDocumentoSoporte();
    private final ControladorAlertas alertas = new ControladorAlertas();
    private final squemaCompras squemaCompras = new squemaCompras();
    private final squemaDocumentoSoporte squemaDocumentoSoporte = new squemaDocumentoSoporte();
    private ModeloContacto DATOS_CLIENTE_CARGADO = null;
    private final DaoResoluciones daoResoluciones = new DaoResoluciones();

    DefaultTableModel modeloComprobantes;

    private boolean DESCUENTO_GENERAL_CARGADO = false;
    private String tipoProceso;

    String simbolo = "";
    DefaultTableModel modeloPro;
    DefaultTableModel modeloPro1;
    metodosGenerales metodos = new metodosGenerales();
    Instancias instancias;
    int ultFila;

    private boolean plu = false, saltarPasos = false, cancelarCompra = false, preguntaLimpiar = true;

    jcThread barra2;
    DecimalFormat df = new DecimalFormat("#.00");
    Icon icono = null;

    public boolean isCancelarCompra() {
        return cancelarCompra;
    }

    public void setCancelarCompra(boolean cancelarCompra) {
        this.cancelarCompra = cancelarCompra;
    }

    public VistaIngreso(String tipo) {
        initComponents();

        instancias = Instancias.getInstancias();
        simbolo = instancias.getSimbolo();

        tblProductos.setDefaultRenderer(Object.class, new IconCellRenderer());
        ImageIcon fot = new ImageIcon(getClass().getResource("/imagenes/eliminar.png"));
        icono = new ImageIcon(fot.getImage().getScaledInstance(30, 25, Image.SCALE_DEFAULT));

        jtblComprobantes.setVisible(false);
        pnlInvisible.setVisible(false);
        setBorder(null);
        repaint();

        modeloPro = (DefaultTableModel) tblProductos.getModel();
        modeloPro1 = (DefaultTableModel) tblDetalle.getModel();
        modeloComprobantes = (DefaultTableModel) tblComprobantes.getModel();

        txtFechaFactura.setFormat(2);
        txtFechaFactura.setText(metodosGenerales.fecha());
        txtVencimiento.setText(metodosGenerales.fecha());

        setTipo(tipo);
        this.tipoProceso = tipo;

        if (instancias.getConfiguraciones().isInventarioBodegas()) {
            TableColumn tcr1 = tblProductos.getColumnModel().getColumn(19);
            TableCellEditor tcer1 = new DefaultCellEditor(cmbBodegas);
            tcr1.setCellEditor(tcer1);

            Object[][] bodegas = instancias.getSql().getTodasBodegas();

            for (int i = 0; i < bodegas.length; i++) {
                cmbBodegas.addItem(bodegas[i][1]);
            }
        } else {
            tblProductos.getColumnModel().getColumn(19).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(19).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(19).setMaxWidth(0);
        }

        pnlFormulario.registerKeyboardAction(accion("guardar"), "guardar", KeyStroke.getKeyStroke(KeyEvent.VK_G, Event.CTRL_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
        pnlFormulario.registerKeyboardAction(accion("limpiar"), "limpiar", KeyStroke.getKeyStroke(KeyEvent.VK_L, Event.CTRL_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
        pnlFormulario.registerKeyboardAction(accion("proveedor"), "proveedor", KeyStroke.getKeyStroke(KeyEvent.VK_T, Event.CTRL_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
        pnlFormulario.registerKeyboardAction(accion("productos"), "productos", KeyStroke.getKeyStroke(KeyEvent.VK_P, Event.CTRL_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
        pnlFormulario.registerKeyboardAction(accion("valor"), "valor", KeyStroke.getKeyStroke(KeyEvent.VK_V, Event.ALT_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
        pnlFormulario.registerKeyboardAction(accion("cantidad"), "cantidad", KeyStroke.getKeyStroke(KeyEvent.VK_C, Event.ALT_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);

        txtCant.setText(DatosMaestra.getCantidadEstablecidaAlCargar());

        if (tipo.equals("ingreso")) {
            btnImportarExcel.setVisible(true);
        } else {
            btnImportarExcel.setEnabled(false);
        }

        if (!instancias.getRegimen().equals("")) {
            txtTotalIva.setVisible(false);
            txtIva.setVisible(false);
            etiqTotal1.setVisible(false);
            txtTotalImpoconsumo.setVisible(false);
            jCheckBox1.setVisible(false);
            txtRiva.setVisible(false);
            cmbRtf.setVisible(false);
            txtRtf.setVisible(false);
            lbSubtotal.setText("Subtotal:");
        }

        if (instancias.getConfiguraciones().getTipoImpresion().equals("Sin-Codigo")) {
            tblProductos.getColumnModel().getColumn(0).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(0).setMaxWidth(0);
        }

        if (!instancias.getConfiguraciones().isInventarioBodegas()) {
            lbBodega.setVisible(false);
            txtBodega.setVisible(false);
        }

        if (instancias.getDescuento().equals("peso")) {
            tblProductos.getColumnModel().getColumn(5).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(5).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(5).setMaxWidth(0);
        } else {
            tblProductos.getColumnModel().getColumn(6).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(6).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(6).setMaxWidth(0);
        }
        txtCodProducto.requestFocus();
    }

    public void setTipo(String tipo) {

        switch (tipo) {
            case "ingreso":
                lbFacturaNo.setText("Ingreso No.");
                lbFechaFactura.setText("Fecha Compra");
                jtblComprobantes.setVisible(true);
                cargarPreCompra();
                actualizarTablaResoluciones();
                break;
            case "ordenCompra":
                lbFacturaNo.setText("Orden No.");
                lbNoFactura.setText((String) instancias.getSql().getNumConsecutivo("ORDENCOMPRA")[0]);
                lbFechaFactura.setText("Fecha Orden");

                txtNumero.setVisible(false);
                cmbTipo.setVisible(false);
                lbTipo.setVisible(false);
                lbNumero.setVisible(false);

                cmbCargar.setSelectedIndex(1);
                cmbCargar.setEnabled(false);
                btnAnular.setVisible(false);
                break;
            default:
                break;
        }
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
                    case "proveedor":
                        if ((btnAnular.isEnabled()) && (btnAnular.isVisible())) {
                            btnBuscTercerosActionPerformed(null);
                        }
                        break;
                    case "productos":
                        if ((btnBusProd.isEnabled()) && (btnBusProd.isVisible())) {
                            btnBusProdActionPerformed(null);
                        }
                        break;
                    case "valor":
                        if (tblProductos.getSelectedRow() > -1) {
                            tblProductos.editCellAt(tblProductos.getSelectedRow(), 2);
                            tblProductos.setColumnSelectionInterval(2, 2);
                            tblProductos.transferFocus();
                        }
                        break;
                    case "cantidad":
                        if (tblProductos.getSelectedRow() > -1) {
                            tblProductos.editCellAt(tblProductos.getSelectedRow(), 3);
                            tblProductos.setColumnSelectionInterval(3, 3);
                            tblProductos.transferFocus();
                        }
                        break;
                    case "cargarAutomatico":
                        break;
                }
            }
        };
        return a;
    }

    public boolean isPlu() {
        return plu;
    }

    public void setPlu(boolean plu) {
        this.plu = plu;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        popBorrar = new javax.swing.JMenuItem();
        grpTipoDescuento = new javax.swing.ButtonGroup();
        buttonGroup1 = new javax.swing.ButtonGroup();
        pnlFormulario = new javax.swing.JPanel();
        pblBotones = new javax.swing.JPanel();
        txtCodProducto = new javax.swing.JTextField();
        lbProducto = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtObservaciones = new javax.swing.JTextArea();
        lbProducto1 = new javax.swing.JLabel();
        cmbBodegas = new javax.swing.JComboBox();
        btnBusProd = new javax.swing.JButton();
        lbProducto2 = new javax.swing.JLabel();
        txtCant = new javax.swing.JTextField();
        lbBodega = new javax.swing.JLabel();
        txtBodega = new javax.swing.JTextField();
        scrollTblProductos = new javax.swing.JScrollPane();
        tblProductos = new javax.swing.JTable();
        jPanel10 = new javax.swing.JPanel();
        btnAnular = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnBuscTerceros2 = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        etiqTotal = new javax.swing.JLabel();
        txtTotal = new javax.swing.JLabel();
        lbSubtotal = new javax.swing.JLabel();
        txtIva = new javax.swing.JLabel();
        lbTotalDescuento = new javax.swing.JLabel();
        txtSubTotal = new javax.swing.JLabel();
        etiqTotal1 = new javax.swing.JLabel();
        txtTotalDescuentos = new javax.swing.JLabel();
        txtTotalIva = new javax.swing.JLabel();
        txtTotalImpoconsumo = new javax.swing.JLabel();
        cmbRtf = new javax.swing.JComboBox();
        jCheckBox1 = new javax.swing.JCheckBox();
        txtRtf = new javax.swing.JLabel();
        txtRiva = new javax.swing.JLabel();
        lbNumero1 = new javax.swing.JLabel();
        lbNumero2 = new javax.swing.JLabel();
        txtCantProductos = new javax.swing.JLabel();
        txtCantUnidades = new javax.swing.JLabel();
        btnBuscTerceros3 = new javax.swing.JButton();
        pnlInvisible = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblDetalle = new javax.swing.JTable();
        txtVencimiento = new datechooser.beans.DateChooserCombo();
        jPanel5 = new javax.swing.JPanel();
        lbNoFactura = new javax.swing.JLabel();
        lbFacturaNo = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lbNit = new javax.swing.JLabel();
        txtNit = new javax.swing.JTextField();
        lbNombre = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        btnBuscTerceros = new javax.swing.JButton();
        btnInformacionCliente = new javax.swing.JButton();
        lbNombre1 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        btnImportarExcel = new javax.swing.JButton();
        txtPorcentaje = new javax.swing.JTextField();
        lbVendedor1 = new javax.swing.JLabel();
        lbNumero3 = new javax.swing.JLabel();
        cmbTipoImpresion = new javax.swing.JComboBox();
        cmbCargar = new javax.swing.JComboBox();
        txtCargarCompra = new javax.swing.JTextField();
        jtblComprobantes = new javax.swing.JScrollPane();
        tblComprobantes = new javax.swing.JTable();
        lbFechaFactura = new javax.swing.JLabel();
        txtFechaFactura = new datechooser.beans.DateChooserCombo();
        lbTipo = new javax.swing.JLabel();
        cmbTipo = new javax.swing.JComboBox();
        lbNumero = new javax.swing.JLabel();
        txtDiasPlazo1 = new javax.swing.JLabel();
        txtDiasPlazo = new javax.swing.JTextField();
        txtNumero = new javax.swing.JTextField();

        popBorrar.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        popBorrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/eliminar-cancelar-icono-4935-16.png"))); // NOI18N
        popBorrar.setText("Borrar");
        popBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                popBorrarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(popBorrar);

        pnlFormulario.setBackground(new java.awt.Color(255, 255, 255));
        pnlFormulario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlFormularioMouseClicked(evt);
            }
        });

        pblBotones.setBackground(new java.awt.Color(255, 255, 255));
        pblBotones.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        txtCodProducto.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        txtCodProducto.setName("combo"); // NOI18N
        txtCodProducto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCodProductoFocusGained(evt);
            }
        });
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

        lbProducto.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbProducto.setText("Producto:");
        lbProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lbProductoKeyReleased(evt);
            }
        });

        txtObservaciones.setColumns(20);
        txtObservaciones.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        txtObservaciones.setLineWrap(true);
        txtObservaciones.setRows(2);
        txtObservaciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtObservacionesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(txtObservaciones);

        lbProducto1.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbProducto1.setText("Observaciones");
        lbProducto1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lbProducto1KeyReleased(evt);
            }
        });

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

        lbProducto2.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbProducto2.setText("Cant:");
        lbProducto2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lbProducto2KeyReleased(evt);
            }
        });

        txtCant.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        txtCant.setHorizontalAlignment(javax.swing.JTextField.CENTER);
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
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCantKeyReleased(evt);
            }
        });

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

        javax.swing.GroupLayout pblBotonesLayout = new javax.swing.GroupLayout(pblBotones);
        pblBotones.setLayout(pblBotonesLayout);
        pblBotonesLayout.setHorizontalGroup(
            pblBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pblBotonesLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(pblBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbProducto1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)
                    .addGroup(pblBotonesLayout.createSequentialGroup()
                        .addComponent(lbProducto2)
                        .addGap(2, 2, 2)
                        .addGroup(pblBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cmbBodegas, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pblBotonesLayout.createSequentialGroup()
                                .addComponent(txtCant, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(15, 15, 15)
                                .addComponent(lbBodega)
                                .addGap(1, 1, 1)
                                .addComponent(txtBodega, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lbProducto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCodProducto, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addGap(2, 2, 2)
                                .addComponent(btnBusProd, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(5, 5, 5))
        );
        pblBotonesLayout.setVerticalGroup(
            pblBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pblBotonesLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(pblBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnBusProd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbBodega, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBodega, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pblBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbProducto2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtCant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtCodProducto))
                .addGap(3, 3, 3)
                .addComponent(cmbBodegas, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbProducto1)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane2)
                .addContainerGap())
        );

        tblProductos.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        tblProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Descripcion", "Valor/Unit", "Cant", "Subtotal", "Desc %", "Desc", "Iva%", "Impo%", "Impo", "Medida", "Ultimo costo", "Lista 1 (Vacio)", "plu", "cant", "Ponderado", "% Aum (Vacio)", "Utilidad", "% Rent.", "Bodega", "Impoconsumo", "Total", "Iva", "Borrar", "idSistema", "Descuento"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true, false, true, true, false, false, false, false, false, true, false, false, false, true, false, false, true, false, false, true, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblProductos.setComponentPopupMenu(jPopupMenu1);
        tblProductos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblProductos.setRowHeight(25);
        tblProductos.getTableHeader().setReorderingAllowed(false);
        tblProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductosMouseClicked(evt);
            }
        });
        tblProductos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblProductosKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblProductosKeyReleased(evt);
            }
        });
        scrollTblProductos.setViewportView(tblProductos);
        if (tblProductos.getColumnModel().getColumnCount() > 0) {
            tblProductos.getColumnModel().getColumn(0).setMinWidth(100);
            tblProductos.getColumnModel().getColumn(0).setPreferredWidth(200);
            tblProductos.getColumnModel().getColumn(0).setMaxWidth(300);
            tblProductos.getColumnModel().getColumn(2).setMinWidth(100);
            tblProductos.getColumnModel().getColumn(2).setPreferredWidth(120);
            tblProductos.getColumnModel().getColumn(2).setMaxWidth(150);
            tblProductos.getColumnModel().getColumn(3).setMinWidth(65);
            tblProductos.getColumnModel().getColumn(3).setPreferredWidth(65);
            tblProductos.getColumnModel().getColumn(3).setMaxWidth(80);
            tblProductos.getColumnModel().getColumn(4).setMinWidth(100);
            tblProductos.getColumnModel().getColumn(4).setPreferredWidth(120);
            tblProductos.getColumnModel().getColumn(4).setMaxWidth(150);
            tblProductos.getColumnModel().getColumn(5).setMinWidth(40);
            tblProductos.getColumnModel().getColumn(5).setPreferredWidth(45);
            tblProductos.getColumnModel().getColumn(5).setMaxWidth(50);
            tblProductos.getColumnModel().getColumn(6).setMinWidth(60);
            tblProductos.getColumnModel().getColumn(6).setPreferredWidth(80);
            tblProductos.getColumnModel().getColumn(6).setMaxWidth(120);
            tblProductos.getColumnModel().getColumn(7).setMinWidth(30);
            tblProductos.getColumnModel().getColumn(7).setPreferredWidth(45);
            tblProductos.getColumnModel().getColumn(7).setMaxWidth(55);
            tblProductos.getColumnModel().getColumn(8).setMinWidth(30);
            tblProductos.getColumnModel().getColumn(8).setPreferredWidth(45);
            tblProductos.getColumnModel().getColumn(8).setMaxWidth(55);
            tblProductos.getColumnModel().getColumn(9).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(9).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(9).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(10).setMinWidth(50);
            tblProductos.getColumnModel().getColumn(10).setPreferredWidth(70);
            tblProductos.getColumnModel().getColumn(10).setMaxWidth(80);
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
            tblProductos.getColumnModel().getColumn(21).setMinWidth(100);
            tblProductos.getColumnModel().getColumn(21).setPreferredWidth(120);
            tblProductos.getColumnModel().getColumn(21).setMaxWidth(150);
            tblProductos.getColumnModel().getColumn(22).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(22).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(22).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(23).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(23).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(23).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(24).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(24).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(24).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(25).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(25).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(25).setMaxWidth(0);
        }

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        btnAnular.setBackground(new java.awt.Color(241, 148, 138));
        btnAnular.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnAnular.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/borrar2.png"))); // NOI18N
        btnAnular.setText("ANULAR");
        btnAnular.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAnular.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnAnular.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnAnular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnularActionPerformed(evt);
            }
        });

        btnGuardar.setBackground(new java.awt.Color(46, 204, 113));
        btnGuardar.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
        btnGuardar.setText("GUARDAR");
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnGuardar.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnBuscTerceros2.setBackground(new java.awt.Color(247, 220, 111));
        btnBuscTerceros2.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnBuscTerceros2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/imprimir.png"))); // NOI18N
        btnBuscTerceros2.setText("REIMPRIMIR");
        btnBuscTerceros2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscTerceros2.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnBuscTerceros2.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnBuscTerceros2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscTerceros2ActionPerformed(evt);
            }
        });

        btnLimpiar.setBackground(new java.awt.Color(204, 204, 204));
        btnLimpiar.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/limpiar.png"))); // NOI18N
        btnLimpiar.setText("LIMPIAR");
        btnLimpiar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLimpiar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnLimpiar.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnBuscTerceros2, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                    .addComponent(btnAnular, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLimpiar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addComponent(btnGuardar)
                .addGap(2, 2, 2)
                .addComponent(btnLimpiar)
                .addGap(2, 2, 2)
                .addComponent(btnBuscTerceros2)
                .addGap(2, 2, 2)
                .addComponent(btnAnular)
                .addGap(2, 2, 2))
        );

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        etiqTotal.setFont(new java.awt.Font("Century Gothic", 1, 22)); // NOI18N
        etiqTotal.setText("Total:");

        txtTotal.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        txtTotal.setText("0");

        lbSubtotal.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbSubtotal.setText("Subtotal sin IVA:");

        txtIva.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        txtIva.setText("IVA:");

        lbTotalDescuento.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTotalDescuento.setText("Descuentos:");

        txtSubTotal.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtSubTotal.setText("0");

        etiqTotal1.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        etiqTotal1.setText("Impoconsumo:");

        txtTotalDescuentos.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtTotalDescuentos.setText("0");

        txtTotalIva.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtTotalIva.setText("0");

        txtTotalImpoconsumo.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtTotalImpoconsumo.setText("0");

        cmbRtf.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        cmbRtf.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Retefuente", "0.5", "1", "1.5", "2", "2.5", "3", "3.5", "4", "6", "7", "10", "20" }));
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

        jCheckBox1.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jCheckBox1.setText("Rete Iva   ");
        jCheckBox1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jCheckBox1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jCheckBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox1ItemStateChanged(evt);
            }
        });
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        txtRtf.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtRtf.setText("0");

        txtRiva.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtRiva.setText("0");

        lbNumero1.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbNumero1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNumero1.setText("Cant Productos:");

        lbNumero2.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbNumero2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNumero2.setText("Cant Unidades:");

        txtCantProductos.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtCantProductos.setText("0");

        txtCantUnidades.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtCantUnidades.setText("0");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(etiqTotal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lbNumero1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbTotalDescuento, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbSubtotal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbRtf, javax.swing.GroupLayout.Alignment.LEADING, 0, 127, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtRtf, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTotalDescuentos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtSubTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                            .addComponent(txtCantProductos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtIva, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(etiqTotal1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jCheckBox1, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                            .addComponent(lbNumero2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtRiva, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTotalImpoconsumo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(txtTotalIva, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtCantUnidades, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(etiqTotal)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIva, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTotalIva, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTotalDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTotalDescuentos, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTotalImpoconsumo, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(etiqTotal1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtRiva, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jCheckBox1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cmbRtf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtRtf, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(3, 3, 3)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbNumero2)
                        .addComponent(txtCantUnidades, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbNumero1, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
                        .addComponent(txtCantProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnBuscTerceros3.setBackground(new java.awt.Color(204, 204, 204));
        btnBuscTerceros3.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnBuscTerceros3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/boton-de-suma.png"))); // NOI18N
        btnBuscTerceros3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscTerceros3.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnBuscTerceros3.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnBuscTerceros3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscTerceros3ActionPerformed(evt);
            }
        });

        tblDetalle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cod", "Imei", "Lote", "F.Vence", "Temp", "cant", "descripcion", "color", "talla"
            }
        ));
        jScrollPane3.setViewportView(tblDetalle);

        txtVencimiento.setFieldFont(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 12));

        javax.swing.GroupLayout pnlInvisibleLayout = new javax.swing.GroupLayout(pnlInvisible);
        pnlInvisible.setLayout(pnlInvisibleLayout);
        pnlInvisibleLayout.setHorizontalGroup(
            pnlInvisibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInvisibleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 918, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(162, 162, 162)
                .addComponent(txtVencimiento, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlInvisibleLayout.setVerticalGroup(
            pnlInvisibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInvisibleLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlInvisibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(pnlInvisibleLayout.createSequentialGroup()
                        .addComponent(txtVencimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 64, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        lbNoFactura.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbNoFactura.setForeground(new java.awt.Color(255, 0, 0));
        lbNoFactura.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNoFactura.setText("3");
        lbNoFactura.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbFacturaNo.setBackground(new java.awt.Color(204, 204, 204));
        lbFacturaNo.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbFacturaNo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbFacturaNo.setText("Ingreso No.");
        lbFacturaNo.setOpaque(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMaximumSize(new java.awt.Dimension(580, 580));

        lbNit.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lbNit.setText("Nit:");

        txtNit.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtNit.setName("CC/NIT"); // NOI18N
        txtNit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNitKeyReleased(evt);
            }
        });

        lbNombre.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbNombre.setText("Proveedor:");

        txtNombre.setEditable(false);
        txtNombre.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtNombre.setName("Nombre"); // NOI18N

        btnBuscTerceros.setBackground(new java.awt.Color(204, 204, 204));
        btnBuscTerceros.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnBuscTerceros.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscTerceros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar1.png"))); // NOI18N
        btnBuscTerceros.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnBuscTerceros.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscTerceros.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnBuscTerceros.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnBuscTerceros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscTercerosActionPerformed(evt);
            }
        });

        btnInformacionCliente.setBackground(new java.awt.Color(255, 255, 255));
        btnInformacionCliente.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        btnInformacionCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscarInfo.png"))); // NOI18N
        btnInformacionCliente.setText("Ver datos");
        btnInformacionCliente.setBorder(null);
        btnInformacionCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnInformacionCliente.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnInformacionCliente.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnInformacionCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInformacionClienteActionPerformed(evt);
            }
        });

        lbNombre1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbNombre1.setText("Teléfono:");

        txtTelefono.setEditable(false);
        txtTelefono.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtTelefono.setName("Nombre"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbNit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbNombre1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtNit, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscTerceros, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnInformacionCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtNombre)
                    .addComponent(txtTelefono))
                .addGap(5, 5, 5))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtNit, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBuscTerceros, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnInformacionCliente, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbNit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(lbNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNombre1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addGap(3, 3, 3))
        );

        btnImportarExcel.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnImportarExcel.setText("IMPORTAR COMPRA");
        btnImportarExcel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnImportarExcel.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnImportarExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportarExcelActionPerformed(evt);
            }
        });

        txtPorcentaje.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPorcentaje.setText("0");
        txtPorcentaje.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPorcentajeFocusGained(evt);
            }
        });
        txtPorcentaje.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtPorcentajeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtPorcentajeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtPorcentajeMouseExited(evt);
            }
        });
        txtPorcentaje.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPorcentajeKeyReleased(evt);
            }
        });

        lbVendedor1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lbVendedor1.setText("Descuento general:");
        lbVendedor1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbVendedor1MouseClicked(evt);
            }
        });

        lbNumero3.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lbNumero3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNumero3.setText("Tipo Impresión");

        cmbTipoImpresion.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        cmbTipoImpresion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "CARTA", "POS" }));
        cmbTipoImpresion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTipoImpresionActionPerformed(evt);
            }
        });

        cmbCargar.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        cmbCargar.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Cargar Ingreso", "Cargar Orden" }));
        cmbCargar.setToolTipText("Cargar una orden o compra ya registradas.");

        txtCargarCompra.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtCargarCompra.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCargarCompra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCargarCompraKeyReleased(evt);
            }
        });

        tblComprobantes.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        tblComprobantes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Comprobante", "", "Resolucion", "fechaFin", "desde", "hasta", "tipo", "prefijo", "conse", "plantilla"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, false, false, false, false, false, false, false, true
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
        jtblComprobantes.setViewportView(tblComprobantes);
        if (tblComprobantes.getColumnModel().getColumnCount() > 0) {
            tblComprobantes.getColumnModel().getColumn(0).setMinWidth(0);
            tblComprobantes.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblComprobantes.getColumnModel().getColumn(0).setMaxWidth(0);
            tblComprobantes.getColumnModel().getColumn(2).setMinWidth(20);
            tblComprobantes.getColumnModel().getColumn(2).setPreferredWidth(20);
            tblComprobantes.getColumnModel().getColumn(2).setMaxWidth(20);
            tblComprobantes.getColumnModel().getColumn(3).setMinWidth(0);
            tblComprobantes.getColumnModel().getColumn(3).setPreferredWidth(0);
            tblComprobantes.getColumnModel().getColumn(3).setMaxWidth(0);
            tblComprobantes.getColumnModel().getColumn(4).setMinWidth(0);
            tblComprobantes.getColumnModel().getColumn(4).setPreferredWidth(0);
            tblComprobantes.getColumnModel().getColumn(4).setMaxWidth(0);
            tblComprobantes.getColumnModel().getColumn(5).setMinWidth(0);
            tblComprobantes.getColumnModel().getColumn(5).setPreferredWidth(0);
            tblComprobantes.getColumnModel().getColumn(5).setMaxWidth(0);
            tblComprobantes.getColumnModel().getColumn(6).setMinWidth(0);
            tblComprobantes.getColumnModel().getColumn(6).setPreferredWidth(0);
            tblComprobantes.getColumnModel().getColumn(6).setMaxWidth(0);
            tblComprobantes.getColumnModel().getColumn(7).setMinWidth(0);
            tblComprobantes.getColumnModel().getColumn(7).setPreferredWidth(0);
            tblComprobantes.getColumnModel().getColumn(7).setMaxWidth(0);
            tblComprobantes.getColumnModel().getColumn(8).setMinWidth(0);
            tblComprobantes.getColumnModel().getColumn(8).setPreferredWidth(0);
            tblComprobantes.getColumnModel().getColumn(8).setMaxWidth(0);
            tblComprobantes.getColumnModel().getColumn(9).setMinWidth(0);
            tblComprobantes.getColumnModel().getColumn(9).setPreferredWidth(0);
            tblComprobantes.getColumnModel().getColumn(9).setMaxWidth(0);
            tblComprobantes.getColumnModel().getColumn(10).setMinWidth(0);
            tblComprobantes.getColumnModel().getColumn(10).setPreferredWidth(0);
            tblComprobantes.getColumnModel().getColumn(10).setMaxWidth(0);
        }

        lbFechaFactura.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lbFechaFactura.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbFechaFactura.setText("Fecha Factura");

        txtFechaFactura.setFieldFont(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 12));
        txtFechaFactura.addCommitListener(new datechooser.events.CommitListener() {
            public void onCommit(datechooser.events.CommitEvent evt) {
                txtFechaFacturaOnCommit(evt);
            }
        });

        lbTipo.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lbTipo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbTipo.setText("Tipo");

        cmbTipo.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        cmbTipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "FACTURA", "DOC. EQUIVALENTE", "OTRO INGRESO" }));
        cmbTipo.setName("Tipo"); // NOI18N
        cmbTipo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbTipoItemStateChanged(evt);
            }
        });
        cmbTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTipoActionPerformed(evt);
            }
        });

        lbNumero.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lbNumero.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNumero.setText("Número");

        txtDiasPlazo1.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txtDiasPlazo1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        txtDiasPlazo1.setText("Días Plazo");

        txtDiasPlazo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtDiasPlazo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDiasPlazo.setToolTipText("Días de plazo para pagar esta factura.");
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

        txtNumero.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtNumero.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNumero.setToolTipText("Número de factura del ingreso.");
        txtNumero.setName("Número Factura"); // NOI18N
        txtNumero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNumeroKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnImportarExcel)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jtblComprobantes, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lbNumero, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbTipo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbFechaFactura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtDiasPlazo1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDiasPlazo, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFechaFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmbCargar, 0, 139, Short.MAX_VALUE)
                    .addComponent(lbVendedor1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbNumero3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbTipoImpresion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbFacturaNo, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                    .addComponent(lbNoFactura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCargarCompra)
                    .addComponent(txtPorcentaje))
                .addGap(21, 21, 21))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lbFacturaNo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbNumero3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(1, 1, 1)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lbNoFactura, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                                    .addComponent(cmbTipoImpresion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(3, 3, 3)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cmbCargar, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                                    .addComponent(txtCargarCompra, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE))
                                .addGap(3, 3, 3)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtPorcentaje, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                                    .addComponent(lbVendedor1, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3)
                                .addComponent(btnImportarExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(1, 1, 1))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lbFechaFactura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtFechaFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(3, 3, 3)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lbTipo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cmbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(3, 3, 3)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtNumero)
                                    .addComponent(lbNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(3, 3, 3)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtDiasPlazo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtDiasPlazo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jtblComprobantes, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout pnlFormularioLayout = new javax.swing.GroupLayout(pnlFormulario);
        pnlFormulario.setLayout(pnlFormularioLayout);
        pnlFormularioLayout.setHorizontalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlFormularioLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pnlInvisible, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlFormularioLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFormularioLayout.createSequentialGroup()
                                .addComponent(pblBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)
                                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlFormularioLayout.createSequentialGroup()
                                .addComponent(scrollTblProductos)
                                .addGap(0, 0, 0)
                                .addComponent(btnBuscTerceros3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(5, 5, 5))
        );
        pnlFormularioLayout.setVerticalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollTblProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnBuscTerceros3, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pblBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlInvisible, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1187, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnlFormulario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 668, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(pnlFormulario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(0, 0, 0)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtCodProductoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCodProductoFocusGained
        tblProductos.removeEditor();
        cargarTotales();
    }//GEN-LAST:event_txtCodProductoFocusGained

    private void txtCodProductoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodProductoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String codigo = txtCodProducto.getText();
            plu = true;
            cargarProducto(codigo.replace("'", "//"), txtCant.getText(), 1, "Directo");
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
        } else if (evt.getKeyCode() == KeyEvent.VK_MULTIPLY && evt.getKeyCode() == KeyEvent.VK_SHIFT) {
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

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        revalidarTabla();

        if (!saltarPasos) {
            ModeloContacto datosVendedor = DATOS_CLIENTE_CARGADO == null ? new ModeloContacto() : DATOS_CLIENTE_CARGADO;
            if (!squemaCompras.validacionesCompra(datosVendedor, tipoProceso, txtNumero.getText(), cmbTipo.getSelectedItem().toString(), txtDiasPlazo.getText())) {
                return;
            }

            if (Constantes.esDocumentoSoporte(obtenerTipoComprobante()) && tipoProceso.equals(TipoDocumento.COMPRA.getValor())) {
                if (!squemaDocumentoSoporte.validacionesDocumentoSoporte(datosVendedor, false)) {
                    return;
                }
            }

            if (!squemaCompras.validacionesDetalleCompra(tblProductos, obtenerTipoComprobante(), tipoProceso)) {
                return;
            }

            if (metodos.msgPregunta(null, "¿Desea continuar?") != 0) {
                return;
            }
        }

        cancelarCompra = false;

        boolean contado = false;
        if (!(txtDiasPlazo.getText().equals("") || txtDiasPlazo.getText().equals("0"))) {
            contado = false;
        } else {
            contado = true;
        }

        String baseUtilizada = obtenerBase();
        String ingreso = "";
        if (tipoProceso.equals("ingreso")) {
            ingreso = "ING-" + instancias.getSql().getNumConsecutivo("ING")[0];
        } else {
            ingreso = "ORDENCOMPRA-" + instancias.getSql().getNumConsecutivo("ORDENCOMPRA")[0];
        }

        String tipoComprobante = obtenerTipoComprobante();
        tipoComprobante = tipoComprobante.equals(Constantes.COMPRA_NORMAL) ? Constantes.COMPRA_NORMAL : Constantes.DOCUMENTO_SOPORTE;

        if (instancias.getConfiguraciones().isFacturaElectronica() && tipoComprobante.equals(Constantes.DOCUMENTO_SOPORTE)) {
            boolean documentoSoporteExitoso = false;
            ModeloDocumentoSoporte modeloDocumentoSoporte = crearModeloDocumentoSoporte(ingreso, DATOS_CLIENTE_CARGADO);

            try {
                documentoSoporteExitoso = consumidorDocumentoSoporte.generarDocumentoSoporte(modeloDocumentoSoporte, false);
            } catch (Exception ex) {
                System.err.println("Hubo un error al enviar el JSON de la factura electronica: " + ex);
            }

            if (!documentoSoporteExitoso) {
                return;
            }
        }

        if (saltarPasos) {
        } else {
            if (tipoProceso.equals("ingreso")) {
                if (contado) {
                    String tipo = new dlgTipoEgreso(null, true).seleccionar();
                    instancias.getEgresos().setSaltarPasos(true);
                    if (!tipo.equals("")) {
                        BigDecimal subtotal = big.getMoneda(txtTotal.getText()).subtract(big.getMoneda(txtTotalIva.getText()));
                        instancias.getEgresos().cargarEgreso(txtNit.getText(), big.getMoneda(txtTotal.getText()), txtNumero.getText(),
                                "PAGOS PROVEEDORES", "CANCELACION FACTURA", tipo, ingreso, big.getMoneda(txtTotalIva.getText()), subtotal,
                                BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, "registrandoCompra");
                    }
                }
            }
        }

        if (cancelarCompra) {
            return;
        }

        //PROCESO GUARDAR INGRESO
        String por = "";
        if (cmbRtf.getSelectedIndex() == 0) {
            por = "0";
        } else {
            por = cmbRtf.getSelectedItem().toString();
        }

        String fechaFactura = metodos.desdeDate(txtFechaFactura.getCurrent());
        String fechaVencimiento = metodos.desdeDate(txtVencimiento.getCurrent());

        Object[] vector = {ingreso, DATOS_CLIENTE_CARGADO.getIdSistema(), fechaFactura, fechaVencimiento, big.getMoneda(txtTotal.getText()), big.getMoneda(txtTotalDescuentos.getText()),
            big.getMoneda(txtTotalIva.getText()), big.getMoneda(txtSubTotal.getText()), cmbTipo.getSelectedItem(), txtNumero.getText(),
            !txtFechaFactura.getText().equals(txtVencimiento.getText()), "", instancias.getUsuario(), instancias.getTerminal(),
            big.getMoneda(txtRiva.getText()), big.getMoneda(txtRtf.getText()), por, txtObservaciones.getText(), metodosGenerales.hora(),
            big.getMoneda(txtTotalImpoconsumo.getText()), "PENDIENTE", big.getMoneda("0"), big.getMoneda("0"), big.getMoneda("0"),
            big.getMoneda("0"), big.getMoneda("0"), big.getMoneda("0"), txtBodega.getText(), ""};

        ndIngreso nodo = metodos.llenarIngreso(vector);

        if (!instancias.getSql().agregarIngreso(nodo)) {
            boolean noPuedaGuardar = false;
            instancias.getSql().eliminarIngreso(ingreso);
            while (!noPuedaGuardar) {
                noPuedaGuardar = instancias.getSql().eliminarCompra(ingreso);
            }

            metodos.msgError(null, "Hubo un problema al guardar la compra");
            return;
        }

        //PROCESO GUARDAR COMPRA
        for (int i = 0; i < tblProductos.getRowCount(); i++) {
            ndProducto producto = instancias.getSql().getDatosProducto(tblProductos.getValueAt(i, 24).toString(), baseUtilizada);
            String cant2 = "1";
            switch (tblProductos.getValueAt(i, 13).toString()) {
                case "2":
                    cant2 = producto.getCantidad2();
                    break;
                case "3":
                    cant2 = producto.getCantidad3();
                    break;
                case "4":
                    cant2 = producto.getCantidad4();
                    break;
                case "5":
                    cant2 = producto.getCantidad5();
                    break;
                case "6":
                    cant2 = producto.getCantidad6();
                    break;
                case "7":
                    cant2 = producto.getCantidad7();
                    break;
                case "8":
                    cant2 = producto.getCantidad8();
                    break;
            }

            String bodega = "BD-Principal";
            if (instancias.getConfiguraciones().isInventarioBodegas()) {
                try {
                    bodega = tblProductos.getValueAt(i, 19).toString();
                } catch (Exception e) {
                }
            }

            BigDecimal iva = BigDecimal.ZERO, impoconsumo = BigDecimal.ZERO;

            impoconsumo = big.getMoneda((String) tblProductos.getValueAt(i, 9));
            iva = big.getMoneda((String) tblProductos.getValueAt(i, 22));

            Object vectCompra[] = {ingreso, tblProductos.getValueAt(i, 24), big.getMoneda((String) tblProductos.getValueAt(i, 2)), tblProductos.getValueAt(i, 14),
                big.getMoneda((String) tblProductos.getValueAt(i, 6)), big.getMoneda((String) tblProductos.getValueAt(i, 21)), big.getMoneda((String) tblProductos.getValueAt(i, 22)),
                big.getMoneda((String) tblProductos.getValueAt(i, 4)), tblProductos.getValueAt(i, 7).toString(), tblProductos.getValueAt(i, 1), tblProductos.getValueAt(i, 13) + "",
                tblProductos.getValueAt(i, 3), big.getMoneda((String) tblProductos.getValueAt(i, 2)).divide(big.getBigDecimal(cant2), 2, RoundingMode.HALF_DOWN),
                bodega, impoconsumo, tblProductos.getValueAt(i, 8).toString(), instancias.getUsuario(), tblProductos.getValueAt(i, 25).toString()};

            ndCompra nodoComp = metodos.llenarCompra(vectCompra);

            if (!instancias.getSql().agregarCompra(nodoComp)) {
                boolean noPuedaGuardar = false;

                instancias.getSql().eliminarIngreso(ingreso);
                while (!noPuedaGuardar) {
                    noPuedaGuardar = instancias.getSql().eliminarCompra(ingreso);
                }

                metodos.msgError(null, "Error al guardar la compra");
                return;
            }
        }

        if (!txtCargarCompra.getText().equals("")) {
            instancias.getSql().cambiarEstadoGeneral("REALIZADO", "ORDENCOMPRA-" + txtCargarCompra.getText(), "bdIngreso");
        }

        if (tipoProceso.equals("ingreso")) {
            TipoDocumento tipoMovimiento = TipoDocumento.COMPRA;
            String tablaUtilizada = enumBodegas.TipoBodega.BODEGA_PRINCIPAL.getNombreTabla();
            List<MovimientoInventario> productos = generarListadoProductos(tablaUtilizada);
            List<DetalleProducto> detallesProductos = generarDetallesProductos();
            ServicioInventario servicioInventario = new ServicioInventario(productos, detallesProductos, tipoMovimiento, ingreso, tablaUtilizada, instancias.getUsuario(), null);

            try {
                servicioInventario.procesarMovimiento();
            } catch (SQLException ex) {
                Logger.getLogger(VistaInventarioInicial.class.getName()).log(Level.SEVERE, null, ex);
                return;
            }
        }

        if (tipoProceso.equals("ingreso")) {
            if (!(txtDiasPlazo.getText().equals("") || txtDiasPlazo.getText().equals("0"))) {
                Object[] vectCxp = {ingreso, "FACT-" + txtNumero.getText(), "PEND", "", metodos.desdeDate(txtVencimiento.getCurrent()),
                    instancias.getUsuario(), big.getMoneda(txtTotal.getText()),
                    txtDiasPlazo.getText(), instancias.getTerminal()};

                ndCxp nodoCxp = metodos.llenarCxp(vectCxp);

                if (!instancias.getSql().agregarCxp(nodoCxp)) {
                    metodos.msgError(null, "Hubo un problema al guardar la factura en cartera");
                }
            }
        }

        //CAMBIAR CONSECUTIVO INGRESO
        if (tipoProceso.equals("ingreso")) {
            if (!instancias.getSql().aumentarConsecutivo("ING", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("ING")[0]) + 1)) {
                metodos.msgError(null, "Hubo un problema al guardar en el consecutivo del la compra");
            }
            lbNoFactura.setText((String) instancias.getSql().getNumConsecutivo("ING")[0]);
        } else {
            if (!instancias.getSql().aumentarConsecutivo("ORDENCOMPRA", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("ORDENCOMPRA")[0]) + 1)) {
                metodos.msgError(null, "Hubo un problema al guardar en el consecutivo del la compra");
            }
            lbNoFactura.setText((String) instancias.getSql().getNumConsecutivo("ORDENCOMPRA")[0]);
        }

        if (saltarPasos) {

        } else {
            if (tipoProceso.equals("ingreso")) {

                if (cmbTipo.getSelectedItem().equals("DOC. EQUIVALENTE")) {
                    if (!instancias.getSql().aumentarConsecutivo("DOCEQUIVALENTE", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("DOCEQUIVALENTE")[0]) + 1)) {
                        metodos.msgError(null, "Error al guardar el consecutivo de documento equivalente");
                    }
                }

                metodos.msgExito(null, "Compra Exitosa");
            } else {
                metodos.msgExito(null, "Orden de compra exitosa");
            }
        }

        String tipoImp = "";
        if (cmbTipoImpresion.getSelectedIndex() == 1) {
            tipoImp = "Pos";
        }

        if (saltarPasos) {
        } else {
//                if (metodos.msgPregunta(null, "¿Desea imprimir?") == 0) {
            instancias.getReporte().verIngreso(ingreso, tipoProceso, tipoImp);
//                }
        }

        saltarPasos = false;
        preguntaLimpiar = false;
        btnLimpiarActionPerformed(evt);
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        if (preguntaLimpiar) {
            if (metodos.msgPregunta(null, "¿Esta seguro de limpiar la compra?") != 0) {
                return;
            }
        }

        txtCant.setText(DatosMaestra.getCantidadEstablecidaAlCargar());

        cmbBodegas.removeAllItems();
        if (instancias.getConfiguraciones().isInventarioBodegas()) {
            Object[][] bodegas = instancias.getSql().getTodasBodegas();
            for (int i = 0; i < bodegas.length; i++) {
                cmbBodegas.addItem(bodegas[i][1]);
            }
        }

        txtObservaciones.setText("");
        txtNit.setText("");
        txtPorcentaje.setText("0");
        txtNombre.setText("");
        txtTelefono.setText("");
        txtDiasPlazo.setText("");
        txtFechaFactura.setSelectedDate(metodos.haciaDate2(metodosGenerales.fecha()));
        txtVencimiento.setText(txtFechaFactura.getText());
        cmbTipo.setSelectedIndex(0);
        txtNumero.setText("");
        txtCargarCompra.setText("");
        txtCantProductos.setText("0");
        txtCantUnidades.setText("0");

        while (tblProductos.getRowCount() > 0) {
            modeloPro.removeRow(0);
        }

        while (tblDetalle.getRowCount() > 0) {
            modeloPro1.removeRow(0);
        }

        txtSubTotal.setText(this.simbolo + " 0");
        txtTotal.setText(this.simbolo + " 0");
        txtTotalIva.setText(this.simbolo + " 0");
        txtTotalDescuentos.setText(this.simbolo + " 0");
        txtRiva.setText(this.simbolo + " 0");
        txtTotalImpoconsumo.setText(this.simbolo + " 0");
        txtRtf.setText(this.simbolo + " 0");
        txtBodega.setText("123-22");
        cmbRtf.setSelectedIndex(0);
        jCheckBox1.setSelected(false);
        DESCUENTO_GENERAL_CARGADO = false;

        txtNit.requestFocus();

        if (tipoProceso.equals("ingreso")) {
            guardarPrecompra();
            guardarPrecompraDetallada();
        }

        preguntaLimpiar = true;
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnBuscTerceros2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscTerceros2ActionPerformed
        String consecutivo;
        if (tipoProceso.equals("ingreso")) {
            consecutivo = "ING-" + metodos.msgIngresarEnter(null, "Documento a reimprimir");
        } else {
            consecutivo = "ORDENCOMPRA-" + metodos.msgIngresarEnter(null, "Documento a reimprimir");
        }

        if (consecutivo.equals("ING-") || consecutivo.equals("ORDENCOMPRA-")) {
            return;
        }

        boolean anulado = false;
        try {
            anulado = instancias.getSql().getDocumentoAnulado("bdIngreso", "Where id='" + consecutivo + "' ");
        } catch (Exception e) {
            metodos.msgError(null, "El documento no existe");
            return;
        }

        if (anulado) {
            metodos.msgAdvertenciaAjustado(null, "El documento se encuentra anulado");
            return;
        }

        instancias.getReporte().verIngreso(consecutivo, tipoProceso);

        if (tipoProceso.equals("ingreso")) {
            if (instancias.getConfiguraciones().isProductosDetallados()) {
                instancias.getReporte().verIngresoDetalle(consecutivo);
            }
        }
    }//GEN-LAST:event_btnBuscTerceros2ActionPerformed

    private void btnAnularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnularActionPerformed
        String consecutivo = "";

        if (tipoProceso.equals("ingreso")) {
            consecutivo = "ING-" + metodos.msgIngresarEnter(null, "Documento a anular");
        }

        if (consecutivo.equals("ING-")) {
            return;
        }

        boolean anulado = instancias.getSql().getDocumentoAnulado("bdIngreso", "Where id='" + consecutivo + "' ");
        if (anulado) {
            metodos.msgAdvertenciaAjustado(null, "El documento se encuentra anulado");
            return;
        }

        txtCargarCompra.setText(consecutivo.replace("ING-", ""));
        cmbCargar.setSelectedIndex(0);

        KeyEvent x = new KeyEvent(this, WIDTH, WIDTH, WIDTH, KeyEvent.VK_ENTER);
        txtCargarCompraKeyReleased(x);
        tblProductos.removeEditor();

        if (instancias.getSql().getRegistrosPagos(consecutivo).length > 0) {
            metodos.msgError(null, "Tiene abonos, no se puede anular");
            btnLimpiarActionPerformed(evt);
            return;
        }

        String baseUtilizada = "";
        try {
            baseUtilizada = instancias.getSql().getBodegaMovimiento(consecutivo, "bdIngreso");
        } catch (Exception e) {
        }

        if ("".equals(baseUtilizada)) {
            baseUtilizada = "123-22";
        }

        txtBodega.setText(baseUtilizada);

        if (metodos.msgPregunta(null, "¿Anular esta compra?") == 0) {

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

            instancias.getSql().eliminarPonderadoIngreso(" bdPonderado ", consecutivo);

            Object[][] Productos = instancias.getSql().getProductosCompra(consecutivo);

            for (Object[] Producto : Productos) {

                String idPonderado = instancias.getSql().getConsecutivoPonderado(Producto[0].toString());
                Object[] ponderados = instancias.getSql().getUltimoPonderado1(idPonderado);

                String ingreso = "";
                if (ponderados[9] != null) {
                    ingreso = ponderados[9].toString();
                }

                if (!instancias.getSql().modificarPonderado(ponderados[8].toString(), Producto[0].toString(),
                        big.getBigDecimal(ponderados[1].toString()), String.valueOf(ponderados[2]), ponderados[3].toString(),
                        big.getBigDecimal(ponderados[4]), String.valueOf(ponderados[5]), instancias.getUsuario(),
                        big.getBigDecimal(ponderados[7]), ingreso)) {
                }

                ndProducto producto = instancias.getSql().getDatosProducto(Producto[0].toString(), baseUtilizada);
                double cantidad;
                double inventario;
                double fisicoInventario;

                try {
                    cantidad = Double.parseDouble(producto.getCompras().replace(",", "."));
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

                double inv = Double.parseDouble(Producto[1].toString().replace(",", "."));

                inventario = inventario - inv;
                fisicoInventario = fisicoInventario - inv;
                double total = cantidad - inv;

                String total1 = String.valueOf(df.format(total)).replace(".", ",");
                String inventario1 = String.valueOf(df.format(inventario)).replace(".", ",");
                String fisicoInventario1 = String.valueOf(df.format(fisicoInventario)).replace(".", ",");

                instancias.getSql().modificarInventario("compras", total1, Producto[0].toString(), baseUtilizada);
                instancias.getSql().modificarInventario("inventario", inventario1, Producto[0].toString(), baseUtilizada);
                instancias.getSql().modificarInventario("fisicoInventario", fisicoInventario1, Producto[0].toString(), baseUtilizada);

                if (instancias.getConfiguraciones().isProductosDetallados()) {
                    instancias.getSql().anularCompraDetalladoInventario(consecutivo);
                }
            }

            if (!instancias.getSql().anularDocumento(consecutivo, "bdIngreso")) {
                metodos.msgError(null, "Hubo un problema al anular la compra");
                return;
            }

            if (!instancias.getSql().modificarRegistroCxp(consecutivo, "ANULADA")) {
                metodos.msgError(null, "Hubo un problema al anular la Cxp");
                return;
            }

            String egreso = "";
            try {
                egreso = instancias.getSql().idEgresoIngresoAsociado(consecutivo);
            } catch (Exception e) {
            }

            if (!egreso.equals("")) {
                boolean egresoAnulado = instancias.getSql().getDocumentoAnulado("bdEgreso", "Where id='" + egreso + "' ");
                if (egresoAnulado) {
                    metodos.msgAdvertencia(null, "Este egreso ya se encuentra anulado");
                    return;
                }

                if (!instancias.getSql().anularDocumento(egreso, "bdEgreso")) {
                    metodos.msgError(null, "Hubo un problema al anular el egreso");
                    return;
                }
            }

            if (egreso.equals("")) {
                metodos.msgExito(null, "Compra anulada con éxito");
            } else {
                metodos.msgExito(null, "Compra y egreso anulados con éxito");
            }
        }

        preguntaLimpiar = false;
        btnLimpiarActionPerformed(evt);
    }//GEN-LAST:event_btnAnularActionPerformed

    private void lbProductoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lbProductoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtCodProducto.requestFocus();
        }
    }//GEN-LAST:event_lbProductoKeyReleased

    private void cmbRtfItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbRtfItemStateChanged
        cargarTotales();
    }//GEN-LAST:event_cmbRtfItemStateChanged

    private void cmbRtfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbRtfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbRtfActionPerformed

    private void jCheckBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox1ItemStateChanged
        cargarTotales();
    }//GEN-LAST:event_jCheckBox1ItemStateChanged

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed

    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void cmbTipoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbTipoItemStateChanged
        if (cmbTipo.getSelectedItem().equals("OTRO INGRESO")) {
            if (tipoProceso.equals("ingreso")) {
                txtNumero.setEditable(true);
                txtNumero.setText("");
                txtDiasPlazo.setEditable(true);
            }
        } else if (cmbTipo.getSelectedItem().equals("DOC. EQUIVALENTE")) {
            if (tipoProceso.equals("ingreso")) {
                txtNumero.setText((String) instancias.getSql().getNumConsecutivo("DOCEQUIVALENTE")[0]);
                txtNumero.setEditable(false);
                txtDiasPlazo.setEditable(true);
            }
        } else if (cmbTipo.getSelectedItem().equals("FACTURA")) {
            txtNumero.setEditable(true);
            txtNumero.setText("");
            txtDiasPlazo.setEditable(true);
        }
//            txtDiasPlazo.setEditable(true);
//            txtConcepto.setEditable(true);
//        } else {
//            txtDiasPlazo.setEditable(false);
//            txtConcepto.setEditable(false);
//        }
    }//GEN-LAST:event_cmbTipoItemStateChanged

    private void cmbTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbTipoActionPerformed

    private void txtNitKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNitKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cargarCliente(txtNit.getText());
        } else if (!txtNombre.getText().equals("")) {
            txtNombre.setText("");
            txtTelefono.setText("");
            DATOS_CLIENTE_CARGADO = null;
        }
    }//GEN-LAST:event_txtNitKeyReleased

    private void txtDiasPlazoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiasPlazoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiasPlazoActionPerformed

    private void txtDiasPlazoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiasPlazoKeyReleased
        try {
            txtVencimiento.setSelectedDate(metodos.haciaDate2(metodos.sumarFecha(txtFechaFactura.getText(), Integer.parseInt(txtDiasPlazo.getText()))));
        } catch (Exception e) {
            txtVencimiento.setSelectedDate(metodos.haciaDate2(metodos.sumarFecha(txtFechaFactura.getText(), 0)));
        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtCodProducto.requestFocus();
        }
    }//GEN-LAST:event_txtDiasPlazoKeyReleased

    private void txtDiasPlazoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiasPlazoKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtDiasPlazoKeyTyped

    private void tblProductosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblProductosKeyReleased
        int filaSeleccionada = tblProductos.getSelectedRow();

        if (filaSeleccionada > -1) {

            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                switch (tblProductos.getSelectedColumn()) {
                    case 1:
                        tblProductos.editCellAt(filaSeleccionada, 2);
                        tblProductos.setColumnSelectionInterval(2, 2);
                        tblProductos.transferFocus();
                        break;
                    case 2:
                        tblProductos.editCellAt(filaSeleccionada, 3);
                        tblProductos.setColumnSelectionInterval(3, 3);
                        tblProductos.transferFocus();
                        break;
                    case 3:
                    case 5:
                    case 6:
                        txtCodProducto.requestFocus();
                        break;
                    default:
                        break;
                }

                calcularTabla(filaSeleccionada);

                if (tipoProceso != null) {
                    if (tipoProceso.equals("ingreso")) {
                        guardarPrecompra();
                        guardarPrecompraDetallada();
                    }
                }
            } else if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
                popBorrarActionPerformed(null);
            }
        }
    }//GEN-LAST:event_tblProductosKeyReleased

    private void txtPorcentajeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPorcentajeKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            int xyz = tblProductos.getRowCount();
            tblProductos.removeEditor();

            if (xyz > 0) {
                for (int y = 0; y < xyz; y++) {
                    tblProductos.setValueAt("0", y, 5);
                    tblProductos.setValueAt(this.simbolo + " 0", y, 6);
                    calcularTabla(y);
                }
            }

            if (xyz > 0) {
                BigDecimal porcentaje = big.getBigDecimal(txtPorcentaje.getText());
                for (int y = 0; y < xyz; y++) {
                    tblProductos.setValueAt(porcentaje, y, 5);
                    calcularTabla(y);
                }
            }

            if (tipoProceso.equals("ingreso")) {
                guardarPrecompra();
                guardarPrecompraDetallada();
            }

            txtPorcentaje.requestFocus();
            txtPorcentaje.setBackground(Color.WHITE);
            txtPorcentaje.setForeground(Color.BLACK);
        } else {
            txtPorcentaje.setBackground(new Color(251, 238, 152));
            txtPorcentaje.setForeground(new Color(146, 137, 77));
        }
    }//GEN-LAST:event_txtPorcentajeKeyReleased

    private void txtFechaFacturaOnCommit(datechooser.events.CommitEvent evt) {//GEN-FIRST:event_txtFechaFacturaOnCommit
        try {
            txtVencimiento.setText(metodos.sumarFecha(txtFechaFactura.getText(), Integer.parseInt(txtDiasPlazo.getText())));
        } catch (NumberFormatException exep) {
            txtVencimiento.setText(metodos.sumarFecha(txtFechaFactura.getText(), 0));
        }
    }//GEN-LAST:event_txtFechaFacturaOnCommit

    private void txtCargarCompraKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCargarCompraKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String consecutivo = "";

            String id = txtCargarCompra.getText();
            if (cmbCargar.getSelectedIndex() == 0) {
                consecutivo = "ING-" + txtCargarCompra.getText();
            } else if (cmbCargar.getSelectedIndex() == 1) {
                consecutivo = "ORDENCOMPRA-" + txtCargarCompra.getText();
            } else {
                metodos.msgError(null, "Debe seleccionar una opción");
                return;
            }

            preguntaLimpiar = false;
            btnLimpiarActionPerformed(null);

            System.out.println("consecutivo " + consecutivo);
            txtCargarCompra.setText(id);

            Object[][] mat = new Object[0][0];
            mat = instancias.getSql().getProductosCargarCompra(consecutivo);

            if (mat.length > 0) {

                ndIngreso nodo = instancias.getSql().getDatosIngreso(consecutivo);

                if (this.tipoProceso.equals("ordenCompra")) {
                } else {
                    if (nodo.getEstado().equals("REALIZADO")) {
                        metodos.msgError(null, "La orden de compra ya fue cargada");
                        return;
                    }
                }

                txtNit.setText(nodo.getProveedor());
                cargarCliente(nodo.getProveedor());
                txtBodega.setText(nodo.getBodega());

                for (Object[] reg : mat) {
                    cargarProductoPreCompra((String) reg[0], new Double((String) reg[4]) + "", Integer.parseInt((String) reg[3]),
                            big.getBigDecimal(reg[5]) + "", false);

                    try {
                        BigDecimal descuento = big.getBigDecimal(reg[6]);
                        BigDecimal total = big.getBigDecimal(reg[7]).add(descuento);
                        BigDecimal porcentaje2 = big.getBigDecimal(descuento.multiply(big.getBigDecimal("100")).divide(total, 2, RoundingMode.HALF_DOWN));
                        tblProductos.setValueAt(porcentaje2, tblProductos.getRowCount() - 1, 5);
                        tblProductos.setValueAt(big.getBigDecimal(reg[6]).toString().replace(".", ","), tblProductos.getRowCount() - 1, 6);
                    } catch (Exception e) {
                        tblProductos.setValueAt(0, tblProductos.getRowCount() - 1, 5);
                        tblProductos.setValueAt(this.simbolo + " 0", tblProductos.getRowCount() - 1, 6);
                    }

                    KeyEvent x = new KeyEvent(this, WIDTH, WIDTH, WIDTH, KeyEvent.VK_ENTER);
                    tblProductosKeyReleased(x);
                }

                tblProductos.removeEditor();

            } else {
                if (cmbCargar.getSelectedIndex() == 0) {
                    metodos.msgError(null, "El ingreso no existe");
                } else {
                    metodos.msgError(null, "La orden de compra no existe");
                }
                txtCargarCompra.setText("");
            }
        }
    }//GEN-LAST:event_txtCargarCompraKeyReleased

    private void popBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popBorrarActionPerformed
        int fila = tblProductos.getSelectedRow();

        int num = tblDetalle.getRowCount();
        for (int i = num - 1; i >= 0; i--) {
            if (tblDetalle.getValueAt(i, 0).equals(tblProductos.getValueAt(fila, 24))) {
                modeloPro1.removeRow(i);
            }
        }

        modeloPro.removeRow(fila);

        if (tblProductos.getRowCount() == 0) {
            DESCUENTO_GENERAL_CARGADO = false;
        }

        cargarTotales();
        guardarPrecompra();
        guardarPrecompraDetallada();
    }//GEN-LAST:event_popBorrarActionPerformed

    private void txtNumeroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtDiasPlazo.requestFocus();
        }
    }//GEN-LAST:event_txtNumeroKeyReleased

    private void lbProducto1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lbProducto1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_lbProducto1KeyReleased

    private void txtObservacionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtObservacionesMouseClicked

    }//GEN-LAST:event_txtObservacionesMouseClicked

    private void cmbTipoImpresionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTipoImpresionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbTipoImpresionActionPerformed

    private void tblProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductosMouseClicked
        switch (tblProductos.getSelectedColumn()) {
            case 3:
                String baseUtilizada = obtenerBase();
                ndProducto nodo = instancias.getSql().getDatosProducto(tblProductos.getValueAt(tblProductos.getSelectedRow(), 24).toString(), baseUtilizada);
                String tipo = "";
                try {
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
                } catch (Exception e) {
                }

                int contador = 0;
                for (int i = 0; i < tblDetalle.getRowCount(); i++) {
                    if (tblProductos.getValueAt(tblProductos.getSelectedRow(), 24).equals(tblDetalle.getValueAt(i, 0))) {
                        contador++;
                    }
                }

                Object[][] productos = new Object[contador][7];
                int xyz = 0;
                for (int i = 0; i < tblDetalle.getRowCount(); i++) {
                    if (tblProductos.getValueAt(tblProductos.getSelectedRow(), 24).equals(tblDetalle.getValueAt(i, 0))) {
                        productos[xyz][0] = tblDetalle.getValueAt(i, 1);
                        productos[xyz][1] = tblDetalle.getValueAt(i, 2);
                        productos[xyz][2] = tblDetalle.getValueAt(i, 3);
                        productos[xyz][3] = tblDetalle.getValueAt(i, 4);
                        productos[xyz][4] = tblDetalle.getValueAt(i, 5);
                        productos[xyz][5] = tblDetalle.getValueAt(i, 7);
                        productos[xyz][6] = tblDetalle.getValueAt(i, 8);
                        xyz++;
                    }
                }

                if (!tipo.equals("") && !this.tipoProceso.equals("ordenCompra")) {
                    dlgCompraDetallada1 compraDetallada = new dlgCompraDetallada1(null, true, tipo, nodo.getIdSistema(), productos, "Entrada", "pnlIngreso", baseUtilizada, "");
                    compraDetallada.setLocationRelativeTo(null);
                    compraDetallada.setVisible(true);
                }

                break;
            case 5:
            case 6:
                abrirModalDescuentosProducto(tblProductos.getSelectedRow());
                break;
            case 23:
                for (int i = 0; i < tblDetalle.getRowCount(); i++) {
                    if (tblDetalle.getValueAt(i, 0).equals(tblProductos.getValueAt(tblProductos.getSelectedRow(), 24))) {
                        modeloPro1.removeRow(i);
                    }
                }

                modeloPro.removeRow(tblProductos.getSelectedRow());
                cargarTotales();
                guardarPrecompra();
                guardarPrecompraDetallada();
                break;
            default:
                break;
        }
    }//GEN-LAST:event_tblProductosMouseClicked

    private void btnImportarExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportarExcelActionPerformed
        DefaultTableModel model = (DefaultTableModel) tblProductos.getModel();

        JFileChooser examinar = new JFileChooser();
        examinar.setFileFilter(new FileNameExtensionFilter("Archivos excel", "xls", "xlsx"));
        int opcion = examinar.showOpenDialog(this);
        File archivoExcel = null;

        controladorBarraProceso contraladorBarra = new controladorBarraProceso();
        vistaBarraProceso barra = new vistaBarraProceso(contraladorBarra, Instancias.getInstancias());
        barra.show();
        this.barra2 = contraladorBarra.getBarra();

        if (opcion == JFileChooser.APPROVE_OPTION) {
            archivoExcel = examinar.getSelectedFile().getAbsoluteFile();

            try {
                Workbook leerExcel = Workbook.getWorkbook(archivoExcel);
                for (int hoja = 0; hoja < leerExcel.getNumberOfSheets(); hoja++) {
                    Sheet hojaP = leerExcel.getSheet(hoja);
                    int filas = hojaP.getRows();
                    for (int fila = 0; fila < filas; fila++) {
                        if (fila > 0) {
                            if (!hojaP.getCell(0, fila).getContents().equals("")) {
                                cargarProductoDesdeExcel(hojaP.getCell(0, fila).getContents(), hojaP.getCell(1, fila).getContents(), hojaP.getCell(2, fila).getContents(),
                                        hojaP.getCell(3, fila).getContents(), hojaP.getCell(4, fila).getContents(), hojaP.getCell(5, fila).getContents());
                            }
                        }
                    }
                }
            } catch (IOException | BiffException ex) {
                metodos.msgError(null, "El archivo de excel es de una versión no compatible");
                return;
            }
        }

        cargarTotales();

        this.barra2.detener(true);
    }//GEN-LAST:event_btnImportarExcelActionPerformed

    private void lbVendedor1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbVendedor1MouseClicked

    }//GEN-LAST:event_lbVendedor1MouseClicked

    private void pnlFormularioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlFormularioMouseClicked

    }//GEN-LAST:event_pnlFormularioMouseClicked

    private void btnBuscTercerosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscTercerosActionPerformed
        ventanaTerceros("");
    }//GEN-LAST:event_btnBuscTercerosActionPerformed

    private void btnBusProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBusProdActionPerformed
        ventanaProductos("");
    }//GEN-LAST:event_btnBusProdActionPerformed

    private void lbProducto2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lbProducto2KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_lbProducto2KeyReleased

    private void txtCantFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCantFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantFocusGained

    private void txtCantKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantKeyReleased

    private void txtCodProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodProductoActionPerformed

    private void txtCantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantActionPerformed

    private void btnBuscTerceros3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscTerceros3ActionPerformed
        if (tblProductos.getRowCount() <= 0) {
            metodos.msgError(null, "No ha ingresado ningún producto");
            return;
        }

        Object[][] productosCargados = new Object[tblProductos.getRowCount()][24];

        for (int i = 0; i < tblProductos.getRowCount(); i++) {
            productosCargados[i][0] = tblProductos.getValueAt(i, 24);
            productosCargados[i][1] = tblProductos.getValueAt(i, 1);
            productosCargados[i][2] = tblProductos.getValueAt(i, 2);
            productosCargados[i][3] = tblProductos.getValueAt(i, 3);
            productosCargados[i][4] = tblProductos.getValueAt(i, 4);
            productosCargados[i][5] = tblProductos.getValueAt(i, 5);
            productosCargados[i][6] = tblProductos.getValueAt(i, 6);
            productosCargados[i][7] = tblProductos.getValueAt(i, 7);
            productosCargados[i][8] = tblProductos.getValueAt(i, 8);
            productosCargados[i][9] = tblProductos.getValueAt(i, 9);
            productosCargados[i][10] = tblProductos.getValueAt(i, 10);
            productosCargados[i][11] = tblProductos.getValueAt(i, 11);
            productosCargados[i][12] = tblProductos.getValueAt(i, 12);
            productosCargados[i][13] = tblProductos.getValueAt(i, 13);
            productosCargados[i][14] = tblProductos.getValueAt(i, 14);
            productosCargados[i][15] = tblProductos.getValueAt(i, 15);
            productosCargados[i][16] = tblProductos.getValueAt(i, 16);
            productosCargados[i][17] = tblProductos.getValueAt(i, 17);
            productosCargados[i][18] = tblProductos.getValueAt(i, 18);
            productosCargados[i][19] = tblProductos.getValueAt(i, 19);
            productosCargados[i][20] = tblProductos.getValueAt(i, 20);
            productosCargados[i][21] = tblProductos.getValueAt(i, 21);
            productosCargados[i][22] = tblProductos.getValueAt(i, 22);
            productosCargados[i][23] = tblProductos.getValueAt(i, 23);
        }

        dlgDetalleCompra detalle = new dlgDetalleCompra(null, true, productosCargados);
        detalle.setVisible(true);
    }//GEN-LAST:event_btnBuscTerceros3ActionPerformed

    private void lbBodegaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lbBodegaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_lbBodegaKeyReleased

    private void txtBodegaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtBodegaMouseClicked
        if (txtBodega.isEnabled()) {
            if (!txtBodega.getText().equals("")) {
                if (tblProductos.getRowCount() > 0) {
                    if (metodos.msgPregunta(null, "¿Limpiar la compra?") != 0) {
                        txtCodProducto.requestFocus();
                        return;
                    } else {
                        while (tblProductos.getRowCount() > 0) {
                            modeloPro.removeRow(0);
                        }
                        while (tblDetalle.getRowCount() > 0) {
                            modeloPro1.removeRow(0);
                        }

                        if (tipoProceso.equals("ingreso")) {
                            guardarPrecompra();
                            guardarPrecompraDetallada();
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
                    if (metodos.msgPregunta(null, "¿Limpiar la compra?") != 0) {
                        txtCodProducto.requestFocus();
                        return;
                    } else {
                        while (tblProductos.getRowCount() > 0) {
                            modeloPro.removeRow(0);
                        }
                        while (tblDetalle.getRowCount() > 0) {
                            modeloPro1.removeRow(0);
                        }

                        if (tipoProceso.equals("ingreso")) {
                            guardarPrecompra();
                            guardarPrecompraDetallada();
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
                    if (metodos.msgPregunta(null, "¿Limpiar la compra?") != 0) {
                        txtCodProducto.requestFocus();
                        return;
                    } else {
                        while (tblProductos.getRowCount() > 0) {
                            modeloPro.removeRow(0);
                        }
                        while (tblDetalle.getRowCount() > 0) {
                            modeloPro1.removeRow(0);
                        }

                        if (tipoProceso.equals("ingreso")) {
                            guardarPrecompra();
                            guardarPrecompraDetallada();
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
                while (tblDetalle.getRowCount() > 0) {
                    modeloPro1.removeRow(0);
                }

                if (tipoProceso.equals("ingreso")) {
                    guardarPrecompra();
                    guardarPrecompraDetallada();
                }
            }
        }
    }//GEN-LAST:event_txtBodegaKeyReleased

    private void tblProductosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblProductosKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tblProductosKeyPressed

    private void btnInformacionClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInformacionClienteActionPerformed
        dlgInformacionCliente notaCliente = new dlgInformacionCliente(null, true, DATOS_CLIENTE_CARGADO.getIdSistema());
        notaCliente.setLocationRelativeTo(null);
        notaCliente.setVisible(true);
    }//GEN-LAST:event_btnInformacionClienteActionPerformed

    private void txtPorcentajeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPorcentajeMouseClicked
        if (instancias.getConfiguraciones().isFacturaElectronica()) {
            if (instancias.getDescuento().equals("peso")) {
                alertas.bigAlert("Cuando el tipo de descuento es en pesos ($), esta opción general no está disponible. "
                        + "Debes asignar el descuento directamente al producto específico.");
                txtCodProducto.requestFocus();
                return;
            }

            txtCodProducto.requestFocus();
            abrirModalDescuentos();
        }
    }//GEN-LAST:event_txtPorcentajeMouseClicked

    private void txtPorcentajeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPorcentajeMouseEntered
        if (instancias.getConfiguraciones().isFacturaElectronica()) {
            if (instancias.getDescuento().equals("peso")) {
                txtCodProducto.requestFocus();
            }
        }
    }//GEN-LAST:event_txtPorcentajeMouseEntered

    private void txtPorcentajeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPorcentajeMouseExited
        if (instancias.getConfiguraciones().isFacturaElectronica()) {
            if (instancias.getDescuento().equals("peso")) {
                txtCodProducto.requestFocus();
            }
        }
    }//GEN-LAST:event_txtPorcentajeMouseExited

    private void txtPorcentajeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPorcentajeFocusGained
        if (instancias.getConfiguraciones().isFacturaElectronica()) {
            if (instancias.getDescuento().equals("peso")) {
                txtCodProducto.requestFocus();
            } else {
                txtCodProducto.requestFocus();
                abrirModalDescuentos();
            }
        }
    }//GEN-LAST:event_txtPorcentajeFocusGained

    private void tblComprobantesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblComprobantesMouseClicked
        for (int i = 0; i < tblComprobantes.getRowCount(); i++) {
            tblComprobantes.setValueAt(false, i, 2);
        }

        if (tblComprobantes.getSelectedRow() == -1) {
            tblComprobantes.setValueAt(true, 0, 2);
            actualizarConsecutivo(0);
        } else {
            tblComprobantes.setValueAt(true, tblComprobantes.getSelectedRow(), 2);
            actualizarConsecutivo(tblComprobantes.getSelectedRow());
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
            actualizarConsecutivo(0);
        } else {
            tblComprobantes.setValueAt(true, tblComprobantes.getSelectedRow(), 2);
            actualizarConsecutivo(tblComprobantes.getSelectedRow());
        }
    }//GEN-LAST:event_tblComprobantesMouseExited

    private List<MovimientoInventario> generarListadoProductos(String tablaUtilizada) {

        List<MovimientoInventario> movimientos = new ArrayList<>();

        for (int i = 0; i < tblProductos.getRowCount(); i++) {
            ndProducto producto = instancias.getSql().getDatosProducto(tblProductos.getValueAt(i, 24).toString(), tablaUtilizada);
            BigDecimal cantidad = Utilidades.convertirBigDecimal(tblProductos.getValueAt(i, 14).toString());

            BigDecimal valorProducto;
            if (instancias.getRegimen().equals("SinIva")) {
                valorProducto = big.getMoneda(tblProductos.getValueAt(i, 21).toString());
            } else {
                valorProducto = big.getMoneda(tblProductos.getValueAt(i, 4).toString());
            }

            valorProducto = valorProducto.divide(cantidad, 4, RoundingMode.HALF_UP);
            MovimientoInventario inventario = new MovimientoInventario(producto, cantidad, valorProducto, "");
            movimientos.add(inventario);
        }

        return movimientos;
    }

    private List<DetalleProducto> generarDetallesProductos() {
        UtilidadesDetalleProducto utilidadesDetalleProducto = new UtilidadesDetalleProducto(tblDetalle);
        return utilidadesDetalleProducto.generarDetallesProductos();
    }

    private String obtenerTipoComprobante() {
        String tipoComprobante = "";
        for (int i = 0; i < tblComprobantes.getRowCount(); i++) {
            if ((Boolean) tblComprobantes.getValueAt(i, 2)) {
                if (null != tblComprobantes.getValueAt(i, 7)) {
                    tipoComprobante = tblComprobantes.getValueAt(i, 7).toString();
                }
            }
        }

        return tipoComprobante;
    }

    private void cargarPreCompra() {
        Object[][] mat1 = instancias.getSql().getProductosPrecompraDetalle("ING");
        for (Object[] reg : mat1) {
            modeloPro1.addRow(new Object[]{reg[0], reg[3], reg[4], metodos.fecha(reg[5].toString()), reg[6], reg[2], reg[1], reg[7], reg[8]});
        }

        Object[][] mat = instancias.getSql().getProductosPrecompra("ING", instancias.getUsuario());

        for (Object[] reg : mat) {
            cargarProductoPreCompra((String) reg[0], new Double((String) reg[4]) + "", Integer.parseInt((String) reg[3]), big.getBigDecimal(reg[5]) + "", false);

            BigDecimal porcentajeDescuento = BigDecimal.ZERO;
            BigDecimal descuentoAplicado = big.getBigDecimal(reg[6]);
            BigDecimal subtotalGeneral = big.getBigDecimal(reg[7]);

            if (descuentoAplicado.compareTo(BigDecimal.ZERO) > 0) {
                if (instancias.isCostoConIva()) {
                    BigDecimal totalGeneral = big.getBigDecimal(reg[10]).add(descuentoAplicado);
                    porcentajeDescuento = descuentoAplicado.multiply(big.getBigDecimal("100")).divide(totalGeneral, 2, RoundingMode.HALF_DOWN);
                } else {
                    BigDecimal totalGeneral = subtotalGeneral.add(descuentoAplicado);
                    porcentajeDescuento = descuentoAplicado.divide(totalGeneral, 2, RoundingMode.HALF_DOWN).multiply(big.getBigDecimal("100"));
                }
            }

            tblProductos.setValueAt(big.setNumero(porcentajeDescuento), tblProductos.getRowCount() - 1, 5);
            tblProductos.setValueAt(big.setMoneda(descuentoAplicado), tblProductos.getRowCount() - 1, 6);
            tblProductos.setValueAt(reg[9], tblProductos.getRowCount() - 1, 25);

            KeyEvent x = new KeyEvent(this, WIDTH, WIDTH, WIDTH, KeyEvent.VK_ENTER);
            tblProductosKeyReleased(x);
        }
    }

    public void actualizarTablaResoluciones() {
        while (tblComprobantes.getRowCount() > 0) {
            modeloComprobantes.removeRow(0);
        }

        List<ModeloResolucion> resoluciones = daoResoluciones.obtenerResoluciones(TipoDocumento.COMPRA.getValor());
        for (ModeloResolucion resolucion : resoluciones) {
            modeloComprobantes.addRow(new Object[]{resolucion.getIdResolucion(), resolucion.getDescripcionResolucion(), false, resolucion.getNumeroResolucion(), resolucion.getFechaInicio(),
                resolucion.getNumeracionDel(), resolucion.getNumeracionHasta(), resolucion.getTipoResolucion(), resolucion.getPrefijo(), resolucion.getConsecutivo(), resolucion.getDisenho()});
        }

        tblComprobantes.setValueAt(true, 0, 2);
        actualizarConsecutivo(0);
    }

    private void actualizarConsecutivo(int fila) {
        String prefijo = null == tblComprobantes.getValueAt(fila, 8) ? "" : tblComprobantes.getValueAt(fila, 8).toString();
        int consecutivo = null == tblComprobantes.getValueAt(fila, 9) ? 0 : Integer.parseInt(tblComprobantes.getValueAt(fila, 9).toString());

        if (consecutivo == 0) {
            ControladorAlertas.alert("Resolución sin consecutivo, verifique para que pueda continuar");
            return;
        }

        lbNoFactura.setText(prefijo + consecutivo);
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

    public void eliminarRegistros(String codigo) {
        for (int i = tblDetalle.getRowCount() - 1; i >= 0; i--) {
            if (tblDetalle.getValueAt(i, 0).equals(codigo)) {
                modeloPro1.removeRow(i);
            }
        }

        for (int i = 0; i < tblProductos.getRowCount(); i++) {
            if (tblProductos.getValueAt(i, 24).equals(codigo)) {
                modeloPro.removeRow(i);
            }
        }
    }

    public void cargarProducto1(String codigo, String cantidad, int plu) {

        for (int j = 0; j < tblProductos.getRowCount(); j++) {
            if (codigo.equalsIgnoreCase((String) tblProductos.getValueAt(j, 24)) && (plu + "").equals(((int) tblProductos.getValueAt(j, 13)) + "")) {
                metodos.msgAdvertencia(null, "El producto ya esta cargado");
                tblProductos.setColumnSelectionInterval(2, 2);
                tblProductos.setRowSelectionInterval(j, j);
                tblProductos.editCellAt(j, 2);
                tblProductos.transferFocus();
                txtCodProducto.setText("");
                return;
            }
        }

        String baseUtilizada = obtenerBase();
        ndProducto nodo = instancias.getSql().getDatosProducto(codigo, baseUtilizada);

        if (nodo.getCodigo() != null) {
            if (instancias.getSql().getProdActivo(nodo.getCodigo())) {
                metodos.msgError(null, "Este producto esta inactivo");
                lbProducto.requestFocus();
                return;
            }

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
                if (cant > 0) {
                    seleccionarPLU pluu = new seleccionarPLU(null, true, baseUtilizada);
                    pluu.setInstancias(instancias, nodo.getCodigo());
                    pluu.setOpc("ingreso");
                    pluu.setVisible(true);
                    return;
                }
            }

            String cant2 = "1";
            String desc = nodo.getDescripcion();
            String lista = nodo.getL1();
            switch (plu) {
                case 2:
                    cant2 = nodo.getCantidad2();
                    desc = nodo.getDescripcion2();
                    lista = nodo.getL2();
                    break;
                case 3:
                    cant2 = nodo.getCantidad3();
                    desc = nodo.getDescripcion3();
                    lista = nodo.getL3();
                    break;
                case 4:
                    cant2 = nodo.getCantidad4();
                    desc = nodo.getDescripcion4();
                    lista = nodo.getL4();
                    break;
            }
            if (cantidad.contains(".")) {
                cantidad = cantidad.replace(".", ",");
            }

            BigDecimal ponderado = BigDecimal.ZERO;
            BigDecimal ultimoCosto = BigDecimal.ZERO;
            try {
                UltimoPonderado ultimoPonderado = servicioActualizacionPonderado.obtenerUltimoPonderado(codigo);
                ponderado = ultimoPonderado.getNuevoPonderado();
                ultimoCosto = ultimoPonderado.getUltimoCosto();
            } catch (SQLException ex) {
                Logger.getLogger(VistaInventarioInicial.class.getName()).log(Level.SEVERE, null, ex);
                alertas.bigAlert("No se pudo consultar el último ponderado del producto");
            }

            BigDecimal utilidad;
            utilidad = big.getBigDecimal(nodo.getL1()).subtract(ponderado);

            modeloPro.addRow(new Object[]{nodo.getCodigo(), desc, big.setMoneda(ponderado.multiply(big.getMoneda(cant2))),
                cantidad, big.setMoneda(big.getBigDecimal("0")), "0", big.setMoneda(big.getBigDecimal("0")), big.setNumero(big.getBigDecimal(nodo.getIvaC())),
                big.setNumero(big.getBigDecimal(nodo.getImpoconsumoCompra())), "0", nodo.getUnd(), big.setMoneda(ultimoCosto),
                big.setMoneda(big.getBigDecimal(lista)), plu, (big.getBigDecimal(cant2).multiply(big.getMoneda(cantidad))), ponderado, 0, big.setMoneda(utilidad),
                "", "BD-Principal", nodo.getImpoconsumo(), this.simbolo + " 0", "0", new JLabel(icono), nodo.getIdSistema(), ""});

            tblProductos.setColumnSelectionInterval(1, 1);
            tblProductos.setRowSelectionInterval(tblProductos.getRowCount() - 1, tblProductos.getRowCount() - 1);
            tblProductos.editCellAt(tblProductos.getRowCount() - 1, 1);
            tblProductos.transferFocus();
            txtCodProducto.setText("");

            KeyEvent x = new KeyEvent(this, WIDTH, WIDTH, WIDTH, KeyEvent.VK_ENTER);
            tblProductosKeyReleased(x);
            return;
        }

        ventanaProductos(codigo);
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

    public void cargarProductoDesdeExcel(String codigo, String valorUnit, String cantidad, String descuento, String iva, String impo) {
        ndProducto nodo = null;

        String codigoProd = "";
        if (codigo.equals("")) {
            codigoProd = "";
        } else {
            Object[][] listado = instancias.getSql().getCodigosRelacionados(codigo, " where codigo");
            if (listado.length > 0) {
                codigo = listado[0][0].toString();
            }

            String baseUtilizada = obtenerBase();
            nodo = instancias.getSql().getDatosProducto(codigo, baseUtilizada);
            if (nodo.getIdSistema() != null) {
                codigoProd = nodo.getIdSistema();
            }
        }

        if (!codigoProd.equals("")) {

            if (instancias.getSql().getProdActivo(nodo.getCodigo())) {
                metodos.msgError(null, "Este producto esta inactivo");
                lbProducto.requestFocus();
                return;
            }

            String cant2 = "1";
            String desc = nodo.getDescripcion();
            String lista = nodo.getL1();

            if (cantidad.contains(".")) {
                cantidad = cantidad.replace(".", ",");
            }

            BigDecimal ponderado = BigDecimal.ZERO;
            BigDecimal ultimoCosto = BigDecimal.ZERO;
            try {
                UltimoPonderado ultimoPonderado = servicioActualizacionPonderado.obtenerUltimoPonderado(codigo);
                ponderado = ultimoPonderado.getNuevoPonderado();
                ultimoCosto = ultimoPonderado.getUltimoCosto();
            } catch (SQLException ex) {
                Logger.getLogger(VistaInventarioInicial.class.getName()).log(Level.SEVERE, null, ex);
                alertas.bigAlert("No se pudo consultar el último ponderado del producto");
            }

            BigDecimal valorUnitario = BigDecimal.ZERO;
            try {
                valorUnitario = big.getBigDecimal(valorUnit);
            } catch (Exception e) {
                try {
                    valorUnitario = big.getMoneda(valorUnit);
                } catch (Exception ef) {

                }
            }

            BigDecimal utilidad;
            utilidad = big.getBigDecimal(nodo.getL1()).subtract(ponderado);

            try {
                cmbBodegas.setSelectedIndex(0);
            } catch (Exception e) {
            }

            BigDecimal impoconsumo = BigDecimal.ZERO;
            try {
                impoconsumo = big.getBigDecimal(impo);
            } catch (Exception e) {
            }

            BigDecimal ivaCompra = BigDecimal.ZERO;
            try {
                ivaCompra = big.getBigDecimal(iva);
            } catch (Exception e) {
            }

            modeloPro.addRow(new Object[]{nodo.getCodigo(), desc, big.setMoneda(valorUnitario.multiply(big.getMoneda(cant2))), cantidad,
                big.setMoneda(big.getBigDecimal("0")), "0", big.setMoneda(big.getBigDecimal("0")), big.setNumero(ivaCompra), big.setNumero(impoconsumo),
                "0", nodo.getUnd(), big.setMoneda(ultimoCosto), big.setMoneda(big.getBigDecimal(lista)), "1", (big.getBigDecimal(cant2).multiply(big.getMoneda(cantidad))),
                big.setMoneda(ponderado), 0, big.setMoneda(utilidad), "", "BD-Principal", nodo.getImpoconsumo(), this.simbolo + " 0", "0", new JLabel(icono), nodo.getIdSistema()});

            try {
                tblProductos.setColumnSelectionInterval(1, 1);
                tblProductos.setRowSelectionInterval(tblProductos.getRowCount() - 1, tblProductos.getRowCount() - 1);
                tblProductos.editCellAt(tblProductos.getRowCount() - 1, 1);
            } catch (Exception e) {
            }

            tblProductos.transferFocus();
            txtCodProducto.setText("");
            calcularTabla(tblProductos.getRowCount() - 1);

//            KeyEvent x = new KeyEvent(this, WIDTH, WIDTH, WIDTH, KeyEvent.VK_ENTER);
//            tblProductosKeyReleased(x);
            return;

        }
    }

    public void compraExterna(String codigo, String cantidad, BigDecimal costo) {
        btnLimpiarActionPerformed(null);

        cargarProductoDetal(codigo, cantidad, costo);

        tblProductos.setValueAt(costo, tblProductos.getRowCount() - 1, 2);
        KeyEvent evento = new KeyEvent(tblProductos, 0, 0, 0, 0);
        evento.setKeyCode(KeyEvent.VK_ENTER);

        tblProductosKeyReleased(evento);
        cmbTipo.setSelectedIndex(2);
        saltarPasos = true;
        btnGuardarActionPerformed(null);
    }

    public void actualizarVariables() {
        if (instancias.isCostoConImpoconsumo()) {
            tblProductos.getColumnModel().getColumn(9).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(9).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(9).setMaxWidth(0);
        } else {
            tblProductos.getColumnModel().getColumn(9).setMinWidth(100);
            tblProductos.getColumnModel().getColumn(9).setPreferredWidth(115);
            tblProductos.getColumnModel().getColumn(9).setMaxWidth(140);
        }

    }

    public void cargarProductoDetal(String codigo, String cantidad, BigDecimal precio) {

        String baseUtilizada = obtenerBase();
        ndProducto nodo = instancias.getSql().getDatosProducto(codigo, baseUtilizada);

        if (nodo.getCodigo() != null) {

            if (cantidad.contains(".")) {
                cantidad = cantidad.replace(".", ",");
            }

            BigDecimal ponderado = BigDecimal.ZERO;
            BigDecimal ultimoCosto = BigDecimal.ZERO;
            try {
                UltimoPonderado ultimoPonderado = servicioActualizacionPonderado.obtenerUltimoPonderado(nodo.getIdSistema());
                ponderado = ultimoPonderado.getNuevoPonderado();
                ultimoCosto = ultimoPonderado.getUltimoCosto();
            } catch (SQLException ex) {
                Logger.getLogger(VistaInventarioInicial.class.getName()).log(Level.SEVERE, null, ex);
                alertas.bigAlert("No se pudo consultar el último ponderado del producto");
            }

            BigDecimal utilidad;
            utilidad = big.getBigDecimal(nodo.getL1()).subtract(ponderado);

            try {
                cmbBodegas.setSelectedIndex(0);
            } catch (Exception e) {
            }

            modeloPro.addRow(new Object[]{nodo.getCodigo(), nodo.getDescripcion(), big.setMoneda(precio),
                cantidad, big.setMoneda(big.getBigDecimal("0")), "0", big.setMoneda(big.getBigDecimal("0")), big.setNumero(big.getBigDecimal(nodo.getIvaC())),
                big.setNumero(big.getBigDecimal(nodo.getImpoconsumoCompra())), "0", nodo.getUnd(), big.setMoneda(ultimoCosto),
                big.setMoneda(big.getBigDecimal(nodo.getL1())), plu, (big.getBigDecimal("1").multiply(big.getMoneda(cantidad))), big.setMoneda(ponderado), 0,
                big.setMoneda(utilidad), "", "BD-Principal", nodo.getImpoconsumo(), this.simbolo + " 0", "0", new JLabel(icono), nodo.getIdSistema()});

            tblProductos.setColumnSelectionInterval(1, 1);
            tblProductos.setRowSelectionInterval(tblProductos.getRowCount() - 1, tblProductos.getRowCount() - 1);
            tblProductos.editCellAt(tblProductos.getRowCount() - 1, 1);
            tblProductos.transferFocus();
            txtCodProducto.setText("");

            KeyEvent x = new KeyEvent(this, WIDTH, WIDTH, WIDTH, KeyEvent.VK_ENTER);
            tblProductosKeyReleased(x);
            return;

        }
        ventanaProductos(codigo);
    }

    public void cargarProducto(String codigo, String cantidad, int plu, String formaCargo) {

//        for (int j = 0; j < tblProductos.getRowCount(); j++) {
//            if (codigo.equalsIgnoreCase((String) tblProductos.getValueAt(j, 0)) && (plu + "").equals(((int) tblProductos.getValueAt(j, 13)) + "")) {
//                metodos.msgAdvertencia(null, "El producto ya esta cargado");
//                tblProductos.setColumnSelectionInterval(2, 2);
//                tblProductos.setRowSelectionInterval(j, j);
//                tblProductos.editCellAt(j, 2);
//                tblProductos.transferFocus();
//                txtCodProducto.setText("");
//                return;
//            }
//        }
        ndProducto nodo = null;
        String baseUtilizada = obtenerBase();
        String codigoProd = "";

        if (codigo.equals("")) {
            codigoProd = "";
        } else {
            Object[][] listado = instancias.getSql().getCodigosRelacionados(codigo, " where codigo");
            if (listado.length > 0) {
                codigo = listado[0][0].toString();
            }

            nodo = instancias.getSql().getDatosProducto(codigo, baseUtilizada);
            if (nodo.getIdSistema() != null) {
                codigoProd = nodo.getIdSistema();
            }
        }

        if (!codigoProd.equals("")) {
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

            if (instancias.getSql().getProdActivo(nodo.getCodigo())) {
                metodos.msgError(null, "Este producto esta inactivo");
                lbProducto.requestFocus();
                return;
            }

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

            if (!tipo.equals("") && !this.tipoProceso.equals("ordenCompra")) {
                dlgCompraDetallada1 compraDetallada = new dlgCompraDetallada1(null, true, tipo, nodo.getIdSistema(), null, "Entrada", "pnlIngreso", baseUtilizada, "");
                compraDetallada.setLocationRelativeTo(null);
                compraDetallada.setVisible(true);
                return;
            } else {
                if (!formaCargo.equals("Directo")) {
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
                            seleccionarPLU pluu = new seleccionarPLU(null, true, baseUtilizada);
                            pluu.setInstancias(instancias, nodo.getIdSistema());
                            pluu.setOpc(this.tipoProceso);
                            pluu.setVisible(true);
                            return;
                        }
                    }
                }

                String cant2 = "1";
                String desc = nodo.getDescripcion();
                String lista = nodo.getL1();
                switch (plu) {
                    case 2:
                        cant2 = nodo.getCantidad2();
                        desc = nodo.getDescripcion2();
                        lista = nodo.getL2();
                        break;
                    case 3:
                        cant2 = nodo.getCantidad3();
                        desc = nodo.getDescripcion3();
                        lista = nodo.getL3();
                        break;
                    case 4:
                        cant2 = nodo.getCantidad4();
                        desc = nodo.getDescripcion4();
                        lista = nodo.getL4();
                        break;
                    case 5:
                        cant2 = nodo.getCantidad5();
                        desc = nodo.getDescripcion5();
                        lista = nodo.getL5();
                        break;
                    case 6:
                        cant2 = nodo.getCantidad6();
                        desc = nodo.getDescripcion6();
                        lista = nodo.getL6();
                        break;
                    case 7:
                        cant2 = nodo.getCantidad7();
                        desc = nodo.getDescripcion7();
                        lista = nodo.getL7();
                        break;
                    case 8:
                        cant2 = nodo.getCantidad8();
                        desc = nodo.getDescripcion8();
                        lista = nodo.getL8();
                        break;
                }

                if (cantidad.contains(".")) {
                    cantidad = cantidad.replace(".", ",");
                }

                BigDecimal ponderado = BigDecimal.ZERO;
                BigDecimal ultimoCosto = BigDecimal.ZERO;
                try {
                    UltimoPonderado ultimoPonderado = servicioActualizacionPonderado.obtenerUltimoPonderado(codigo);
                    ponderado = ultimoPonderado.getNuevoPonderado();
                    ultimoCosto = ultimoPonderado.getUltimoCosto();
                } catch (SQLException ex) {
                    Logger.getLogger(VistaInventarioInicial.class.getName()).log(Level.SEVERE, null, ex);
                    alertas.bigAlert("No se pudo consultar el último ponderado del producto");
                }

                BigDecimal utilidad;
                utilidad = big.getBigDecimal(nodo.getL1()).subtract(ponderado);

                try {
                    cmbBodegas.setSelectedIndex(0);
                } catch (Exception e) {
                }

                modeloPro.addRow(new Object[]{nodo.getCodigo(), desc, big.setMoneda(ponderado.multiply(big.getMoneda(cant2))),
                    cantidad, big.setMoneda(big.getBigDecimal("0")), "0", big.setMoneda(big.getBigDecimal("0")), big.setNumero(big.getBigDecimal(nodo.getIvaC())),
                    big.setNumero(big.getBigDecimal(nodo.getImpoconsumoCompra())), "0", nodo.getUnd(), big.setMoneda(ultimoCosto),
                    big.setMoneda(big.getBigDecimal(lista)), plu, (big.getBigDecimal(cant2).multiply(big.getMoneda(cantidad))), big.setMoneda(ponderado), 0,
                    big.setMoneda(utilidad), "", "BD-Principal", nodo.getImpoconsumo(), this.simbolo + " 0", "0", new JLabel(icono), nodo.getIdSistema(), ""});

                tblProductos.setColumnSelectionInterval(1, 1);
                tblProductos.setRowSelectionInterval(tblProductos.getRowCount() - 1, tblProductos.getRowCount() - 1);
                tblProductos.editCellAt(tblProductos.getRowCount() - 1, 1);
                tblProductos.transferFocus();
                txtCodProducto.setText("");

                KeyEvent x = new KeyEvent(this, WIDTH, WIDTH, WIDTH, KeyEvent.VK_ENTER);
                tblProductosKeyReleased(x);
                txtCant.setText(DatosMaestra.getCantidadEstablecidaAlCargar());

                Rectangle r;
                if (DatosMaestra.getFocoDespuesDeCargarProducto().equals("Valor")) {
                    r = tblProductos.getCellRect(tblProductos.getRowCount() - 1, 2, true);
                } else {
                    r = tblProductos.getCellRect(tblProductos.getRowCount() - 1, 3, true);
                }

                scrollTblProductos.getViewport().scrollRectToVisible(r);

//                tblProductos.setColumnSelectionInterval(0, 0);
//                tblProductos.setRowSelectionInterval(modeloPro.getRowCount() - 1, modeloPro.getRowCount() - 1);
                return;
            }
        }
        ventanaProductos(codigo);
    }

    public void cargarProductoPreCompra(String codigo, String cantidad, int plu, String valor, Boolean cargando) {

        String baseUtilizada = obtenerBase();

        ndProducto nodo = instancias.getSql().getDatosProducto(codigo, baseUtilizada);

        String tipo = "";
        try {
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
        } catch (Exception e) {
        }

        if (!tipo.equals("") && cargando) {
            dlgCompraDetallada1 compraDetallada = new dlgCompraDetallada1(null, true, tipo, nodo.getIdSistema(), null, "Entrada", "pnlIngreso", baseUtilizada, "");
            compraDetallada.setLocationRelativeTo(null);
            compraDetallada.setVisible(true);
            return;
        } else {

            if (nodo.getIdSistema() != null) {
                String cant2 = "1";
                String desc = nodo.getDescripcion();
                String lista = nodo.getL1();
                switch (plu) {
                    case 2:
                        cant2 = nodo.getCantidad2();
                        desc = nodo.getDescripcion2();
                        lista = nodo.getL2();
                        break;
                    case 3:
                        cant2 = nodo.getCantidad3();
                        desc = nodo.getDescripcion3();
                        lista = nodo.getL3();
                        break;
                    case 4:
                        cant2 = nodo.getCantidad4();
                        desc = nodo.getDescripcion4();
                        lista = nodo.getL4();
                        break;
                    case 5:
                        cant2 = nodo.getCantidad5();
                        desc = nodo.getDescripcion5();
                        lista = nodo.getL5();
                        break;
                    case 6:
                        cant2 = nodo.getCantidad6();
                        desc = nodo.getDescripcion6();
                        lista = nodo.getL6();
                        break;
                    case 7:
                        cant2 = nodo.getCantidad7();
                        desc = nodo.getDescripcion7();
                        lista = nodo.getL7();
                        break;
                    case 8:
                        cant2 = nodo.getCantidad8();
                        desc = nodo.getDescripcion8();
                        lista = nodo.getL8();
                        break;
                }
                if (cantidad.contains(".")) {
                    cantidad = cantidad.replace(".", ",");
                }

                BigDecimal ultimoCosto = BigDecimal.ZERO;
                try {
                    UltimoPonderado ultimoPonderado = servicioActualizacionPonderado.obtenerUltimoPonderado(nodo.getIdSistema());
                    ultimoCosto = ultimoPonderado.getUltimoCosto();
                } catch (SQLException ex) {
                    Logger.getLogger(VistaInventarioInicial.class.getName()).log(Level.SEVERE, null, ex);
                    alertas.bigAlert("No se pudo consultar el último ponderado del producto");
                }

                BigDecimal ponderado = big.getBigDecimal(valor + "");
                BigDecimal utilidad;
                utilidad = big.getBigDecimal(nodo.getL1()).subtract(ponderado);

                modeloPro.addRow(new Object[]{nodo.getCodigo(), desc, big.setMoneda(ponderado),
                    cantidad, big.setMoneda(big.getBigDecimal("0")), "0", big.setMoneda(big.getBigDecimal("0")), big.setNumero(big.getBigDecimal(nodo.getIvaC())),
                    big.setNumero(big.getBigDecimal(nodo.getImpoconsumoCompra())), "0", nodo.getUnd(), big.setMoneda(ultimoCosto),
                    big.setMoneda(big.getBigDecimal(lista)), plu, (big.getBigDecimal(cant2).multiply(big.getMoneda(cantidad))), big.setMoneda(ponderado), 0, big.setMoneda(utilidad),
                    "", "BD-Principal", nodo.getImpoconsumo(), this.simbolo + " 0", "0", new JLabel(icono), nodo.getIdSistema()});

                tblProductos.setColumnSelectionInterval(1, 1);
                tblProductos.setRowSelectionInterval(tblProductos.getRowCount() - 1, tblProductos.getRowCount() - 1);
                tblProductos.editCellAt(tblProductos.getRowCount() - 1, 1);
                tblProductos.transferFocus();
                txtCodProducto.setText("");

                KeyEvent x = new KeyEvent(this, WIDTH, WIDTH, WIDTH, KeyEvent.VK_ENTER);
                tblProductosKeyReleased(x);
            }
        }
    }

    public void ventanaProductos(String codigo) {
        String lugar = "sinArmados";
//        if (tipo.equals("ordenCompra")) {
//            lugar = "sinSerial";
//        }

        String base = txtBodega.getText();
        if (instancias.getConfiguraciones().isInventarioBodegas()) {
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
            }
        } else {
            base = "productos1";
        }

        if (base.equals("")) {
            metodos.msgAdvertenciaAjustado(null, "Seleccione una bodega...");
            return;
        }

        buscProductos buscar = new buscProductos(null, true, false, lugar, base);
        buscar.setOpc(tipoProceso);
        buscar.setIngreso(this);
        buscar.setLocationRelativeTo(null);
        instancias.setBusProductos(buscar);
        instancias.setCampoActual(txtCodProducto);
        txtCodProducto.requestFocus();
        buscar.noEncontrado(codigo.replace("'", "//"));
        buscar.show();
    }

    public void cargarCliente(String nit) {
        ModeloContacto nodo = instancias.getSql().getDatosTercero(nit);
        if (nodo.getId() != null) {
            txtNit.setText(nodo.getId());
            txtNombre.setText(nodo.getNombre());
            txtTelefono.setText(nodo.getTelefono());
            txtNumero.requestFocus();
            DATOS_CLIENTE_CARGADO = nodo;
            return;
        }
        ventanaTerceros(nit);
    }

    public void ventanaTerceros(String nit) {
        buscClientes buscar = new buscClientes(instancias.getMenu(), true, false, null, "");
        buscar.setOpc("ingreso");
        buscar.setLocationRelativeTo(null);
        instancias.setBusClientes(buscar);
        instancias.setCampoActual(txtNit);
        txtNit.requestFocus();
        buscar.noEncontrado(nit);
        buscar.show();
    }

    public void nuevoProveedor(String id) {
        txtNit.setText(id);
        cargarCliente(id);
        txtCodProducto.requestFocus();
    }

    public void guardarPrecompra() {
        boolean noPuedaGuardar = false;

        while (!noPuedaGuardar) {
            noPuedaGuardar = instancias.getSql().eliminarPrecompra("ING", instancias.getUsuario());
        }

        for (int i = 0; i < tblProductos.getRowCount(); i++) {
            Object vectCompra[] = {"ING", tblProductos.getValueAt(i, 24), big.getMoneda((String) tblProductos.getValueAt(i, 2)),
                tblProductos.getValueAt(i, 14), big.getMoneda((String) tblProductos.getValueAt(i, 6)), big.getMoneda((String) tblProductos.getValueAt(i, 21)),
                big.getMoneda((String) tblProductos.getValueAt(i, 22)), big.getMoneda((String) tblProductos.getValueAt(i, 4)),
                tblProductos.getValueAt(i, 7).toString(), tblProductos.getValueAt(i, 1), tblProductos.getValueAt(i, 13) + "",
                tblProductos.getValueAt(i, 3), big.getMoneda((String) tblProductos.getValueAt(i, 2)), "",
                big.getMoneda((String) tblProductos.getValueAt(i, 9)), tblProductos.getValueAt(i, 8).toString(), instancias.getUsuario(),
                tblProductos.getValueAt(i, 25).toString()};

            ndCompra nodoComp = metodos.llenarCompra(vectCompra);

            if (!instancias.getSql().agregarPrecompra(nodoComp)) {
                metodos.msgError(null, "Hubo un problema al guardar la precompra");
            }
        }
    }

    public void guardarPrecompraDetallada() {
        boolean noPuedaGuardar = false;

        while (!noPuedaGuardar) {
            noPuedaGuardar = instancias.getSql().eliminarPrecompraDetalle("ING");
        }

        for (int i = 0; i < tblDetalle.getRowCount(); i++) {
            String cant = tblDetalle.getValueAt(i, 5).toString();
            if (cant.equals("")) {
                cant = "1.0";
            }

            String fecha = tblDetalle.getValueAt(i, 3).toString();
            if (fecha.equals("")) {
                fecha = metodosGenerales.fecha();
            }

            if (!instancias.getSql().agregarPreCompraDetallada("ING", tblDetalle.getValueAt(i, 0).toString(), tblDetalle.getValueAt(i, 6).toString(),
                    cant, tblDetalle.getValueAt(i, 1).toString(), tblDetalle.getValueAt(i, 2).toString(),
                    metodos.fechaConsulta(fecha), tblDetalle.getValueAt(i, 4).toString(), tblDetalle.getValueAt(i, 7).toString(),
                    tblDetalle.getValueAt(i, 8).toString())) {
                metodos.msgError(null, "Hubo un problema al guardar la precompra detallada");
            }
        }
    }

    public void cargarDetallado(String prod, String imei, String lote, String fechaVence, String temp,
            String cant, String nombre, String color, String talla) {
        modeloPro1.addRow(new Object[]{prod, imei, lote, fechaVence, temp, cant, nombre, color, talla});
        guardarPrecompraDetallada();
    }

    public void cargarTotales() {

        int i;

        BigDecimal subtotal, iva, impoconsumo, total, descuentos, rtf, rti, cantUnidades;
        subtotal = big.getBigDecimal("0");
        iva = big.getBigDecimal("0");
        impoconsumo = big.getBigDecimal("0");
        descuentos = big.getBigDecimal("0");
        total = big.getBigDecimal("0");
        rtf = big.getBigDecimal("0");
        rti = big.getBigDecimal("0");
        cantUnidades = big.getBigDecimal("0");

        for (i = 0; i < tblProductos.getRowCount(); i++) {
            subtotal = subtotal.add(big.getMoneda(String.valueOf(tblProductos.getValueAt(i, 4))));
            descuentos = descuentos.add(big.getMoneda(String.valueOf(tblProductos.getValueAt(i, 6))));
            impoconsumo = impoconsumo.add(big.getMoneda(String.valueOf(tblProductos.getValueAt(i, 9))));
            iva = iva.add(big.getMoneda(String.valueOf(tblProductos.getValueAt(i, 22))));
            total = total.add(big.getMoneda(String.valueOf(tblProductos.getValueAt(i, 21))));
            cantUnidades = cantUnidades.add(big.getBigDecimal(tblProductos.getValueAt(i, 3).toString().replace(".", "").replace(",", ".")));
        }

        if (cmbRtf.getSelectedIndex() > 0) {
            rtf = ((subtotal.subtract(descuentos))).multiply(big.getBigDecimal(cmbRtf.getSelectedItem())).divide(big.getBigDecimal("100"), 2, RoundingMode.HALF_DOWN);
        }

        if (jCheckBox1.isSelected()) {
            rti = iva.multiply(big.getBigDecimal("15")).divide(big.getBigDecimal("100"), 2, RoundingMode.HALF_DOWN);
        }

        txtSubTotal.setText(big.setMoneda(subtotal));
        txtTotalDescuentos.setText(big.setMoneda(descuentos));
        txtTotalIva.setText(big.setMoneda(iva));
        txtTotalImpoconsumo.setText(big.setMoneda(impoconsumo));
        txtTotal.setText(big.setMoneda(total.subtract(rtf).subtract(rti)));

        txtRtf.setText(big.setMonedaExacta(rtf));
        txtRiva.setText(big.setMonedaExacta(rti));
        txtCantProductos.setText(Integer.toString(tblProductos.getRowCount()));
        txtCantUnidades.setText(cantUnidades.toString());

    }

    public void desdeTraslado(String cliente, String[][] productos) {
        btnLimpiarActionPerformed(null);

        txtNit.setText(cliente);
        cargarCliente(cliente);

        for (String[] producto : productos) {
            cargarProducto(producto[0], "1", 1, "");
        }

        tblProductos.editCellAt(tblProductos.getSelectedRow(), 6);
        tblProductos.setColumnSelectionInterval(6, 6);
        tblProductos.transferFocus();

        for (int i = 0; i < tblProductos.getRowCount(); i++) {
            tblProductos.setValueAt(productos[i][1], i, 3);

            BigDecimal valor, cantidad, descuento, iva, subtotal, total;

            valor = big.getMoneda(String.valueOf(tblProductos.getValueAt(i, 2)));
            cantidad = big.getMoneda(String.valueOf(tblProductos.getValueAt(i, 3)));
            descuento = big.getMoneda(String.valueOf(tblProductos.getValueAt(i, 6)));
            iva = big.getMoneda(String.valueOf(tblProductos.getValueAt(i, 7)));
            subtotal = (valor.multiply(cantidad)).subtract(descuento);
            iva = (big.getBigDecimal(subtotal).multiply(iva)).divide(new BigDecimal("100"));
            total = subtotal.add(iva);

            tblProductos.setValueAt(big.setMoneda(subtotal), i, 4);
            tblProductos.setValueAt(big.setMoneda(iva), i, 22);
            tblProductos.setValueAt(big.setMoneda(total), i, 21);

        }

        cargarTotales();
    }

    public void nuevoProducto(String id) {
        System.out.println("id producto nuevo " + id);
        cargarProducto(id, "1", 1, "");
    }

    public void cargarProductos(Object[][] productos) {
        String cantEstablecida = txtCant.getText();
        for (int i = 0; i < productos.length; i++) {
            this.plu = true;

            String codigo = productos[i][0].toString();
            String cantidad = productos[i][1].toString();
            if (cantidad.equals("0")) {
                cantidad = cantEstablecida;
            }
            cargarProducto(codigo, cantidad, 1, "");
        }
    }

    public void calcularTablaPreCompraValores() {
        int q = tblProductos.getRowCount();
        for (int i = 0; i < q; i++) {
            calcularTabla(i);
        }
    }

    private void calcularTabla(int fila) {

        BigDecimal valorProducto = big.getMoneda(String.valueOf(tblProductos.getValueAt(fila, 2)));
        BigDecimal cantidad = big.getBigDecimal(tblProductos.getValueAt(fila, 3).toString().replace(".", "").replace(",", "."));
        if (cantidad.compareTo(BigDecimal.ZERO) <= 0) {
            cantidad = BigDecimal.ONE;
        }

        BigDecimal subtotal = valorProducto.multiply(cantidad);
        BigDecimal descuento = big.getMoneda(tblProductos.getValueAt(fila, 6).toString());
        tblProductos.setValueAt(big.setMoneda(descuento), fila, 6);

        BigDecimal porcentajeIva = big.getBigDecimal(tblProductos.getValueAt(fila, 7).toString()).divide(big.getBigDecimal("100"));
        BigDecimal porcentajeImpoconsumo = big.getBigDecimal(tblProductos.getValueAt(fila, 8).toString()).divide(big.getBigDecimal("100"));
        BigDecimal porcentajeDescuento = BigDecimal.ZERO;;

        if (subtotal.compareTo(BigDecimal.ZERO) > 0) {
            if (instancias.getDescuento().equals("peso")) {
                porcentajeDescuento = big.getBigDecimal(descuento.multiply(big.getBigDecimal("100")).divide(subtotal, 2, RoundingMode.HALF_DOWN));
            } else {
                porcentajeDescuento = big.getBigDecimal(tblProductos.getValueAt(fila, 5).toString().replace(".", "").replace(",", "."));
                descuento = subtotal.multiply(porcentajeDescuento.divide(big.getBigDecimal("100"), 2, RoundingMode.HALF_DOWN));
            }
        }

        BigDecimal valorIva = BigDecimal.ZERO;
        BigDecimal valorImpoconsumo = BigDecimal.ZERO;
        BigDecimal totalProducto = BigDecimal.ZERO;

        if (instancias.isCostoConIva()) {
            BigDecimal totalImpuestos = porcentajeIva.add(BigDecimal.ONE).multiply(porcentajeImpoconsumo.add(BigDecimal.ONE)).setScale(2, RoundingMode.DOWN);
            BigDecimal precioBase = subtotal.divide(totalImpuestos, 2, RoundingMode.HALF_DOWN);
            valorIva = precioBase.multiply(porcentajeIva);
            valorImpoconsumo = precioBase.multiply(porcentajeImpoconsumo);
            totalProducto = subtotal;
            subtotal = precioBase;
        } else {
            valorIva = subtotal.multiply(porcentajeIva);
            valorImpoconsumo = subtotal.multiply(porcentajeImpoconsumo);
            totalProducto = subtotal.add(valorIva).add(valorImpoconsumo);
        }

        subtotal = subtotal.subtract(descuento);
        totalProducto = totalProducto.subtract(descuento);

        tblProductos.setValueAt(big.setMoneda(valorProducto), fila, 2);
        tblProductos.setValueAt(big.setNumero(cantidad), fila, 3);
        tblProductos.setValueAt(big.setMoneda(subtotal), fila, 4);
        tblProductos.setValueAt(big.setNumero(porcentajeDescuento), fila, 5);
        tblProductos.setValueAt(big.setMoneda(descuento), fila, 6);
        tblProductos.setValueAt(big.setMoneda(valorImpoconsumo), fila, 9);
        tblProductos.setValueAt(big.setMoneda(valorIva), fila, 22);
        tblProductos.setValueAt(big.setMoneda(totalProducto), fila, 21);

        /*tblProductos.setValueAt(big.setMoneda(big.getMoneda(tblProductos.getValueAt(fila, 15).toString())), fila, 15);
         tblProductos.setValueAt(big.setMoneda(big.getMoneda(tblProductos.getValueAt(fila, 17).toString())), fila, 17);*/
        actualizarCantidadPLU(fila, cantidad);
        cargarTotales();
    }

    private ModeloDocumentoSoporte crearModeloDocumentoSoporte(String factura, ModeloContacto datosCliente) {

        ModeloDocumentoSoporte modeloDocumentoSoporte = new ModeloDocumentoSoporte();

        modeloDocumentoSoporte.setDsPrefijo("");
        modeloDocumentoSoporte.setDsNumeroFactura(factura.replace("ING-", ""));

        String tipoOperacion = cmbTipoImpresion.getSelectedItem().equals("POS") ? "POS" : "ESTANDAR";
        modeloDocumentoSoporte.setTipoOperacion(tipoOperacion);
        modeloDocumentoSoporte.setFechaEmision(metodos.fecha4(metodosGenerales.fecha()) + " " + metodosGenerales.fechaHora().split(" ")[1]);
        modeloDocumentoSoporte.setFechaVencimiento(metodos.fecha4(txtVencimiento.getText()));
        modeloDocumentoSoporte.setTipoDocumentoElectronico("SOPORTE_ADQUISICION");
        //modeloDocumentoSoporte.setDsResolucionDian("PENDIENTE");

        documentosElectronicos.construirDatosCliente(modeloDocumentoSoporte, datosCliente);

        if ((txtDiasPlazo.getText().equals("") || txtDiasPlazo.getText().equals("0"))) {
            modeloDocumentoSoporte.setFormaPago("CONTADO");
            modeloDocumentoSoporte.setMedioPago("EFECTIVO");
        } else {
            modeloDocumentoSoporte.setFormaPago("CREDITO");
            modeloDocumentoSoporte.setMedioPago("EFECTIVO");
        }

        modeloDocumentoSoporte.setValorBruto(big.getMoneda(txtSubTotal.getText()).add(big.getMoneda(txtTotalDescuentos.getText())));

        BigDecimal total = big.getMoneda(txtTotal.getText().replace("Total: ", ""));
        modeloDocumentoSoporte.setValorBrutoMasTributos(total.add(big.getMoneda(txtTotalDescuentos.getText())));

        BigDecimal valorBaseImponible = BigDecimal.ZERO;
        for (int i = 0; i < tblProductos.getRowCount(); i++) {
            if (Integer.parseInt(tblProductos.getValueAt(i, 7).toString()) > 0 || Integer.parseInt(tblProductos.getValueAt(i, 8).toString()) > 0) {
                valorBaseImponible = valorBaseImponible.add(big.getMoneda(tblProductos.getValueAt(i, 4).toString()));
            }
        }

        modeloDocumentoSoporte.setValorBaseImponible(valorBaseImponible);
        modeloDocumentoSoporte.setDescuentoTotal(big.getMoneda(txtTotalDescuentos.getText()));
        modeloDocumentoSoporte.setCargoTotal(BigDecimal.ZERO);
        modeloDocumentoSoporte.setValorNeto(total);

        ModeloDetalleImpuestos resultadosImpuestos = obtenerImpuestosCompra(obtenerPorcentajeRetencionFuente());
        modeloDocumentoSoporte.setImpuestosCompra(resultadosImpuestos);

        ModeloDetalleProductos[] detalleProductos = obtenerDetalleProductos(factura);
        modeloDocumentoSoporte.setDetalleProductos(detalleProductos);

        ModeloDescuentos[] resultadosDescuentos = obtenerDescuentosFactura(big.getMoneda(txtTotalDescuentos.getText()));
        modeloDocumentoSoporte.setDescuentosFactura(resultadosDescuentos);
        return modeloDocumentoSoporte;
    }

    private ModeloDescuentos[] obtenerDescuentosFactura(BigDecimal descuentosDocumento) {

        String codigoDescuento = "", descripcionDescuento = "";
        for (int i = 0; i < tblProductos.getRowCount(); i++) {
            if (!("Sin-Permiso".equals(tblProductos.getValueAt(i, 25).toString()) || "".equals(tblProductos.getValueAt(i, 25).toString()))) {
                codigoDescuento = tblProductos.getValueAt(i, 25).toString().split("///")[0];
                descripcionDescuento = tblProductos.getValueAt(i, 25).toString().split("///")[1];
            }
        }

        BigDecimal valorBase;
        if (instancias.isCostoConIva()) {
            valorBase = big.getMoneda(txtTotal.getText().replace("Total: ", "")).add(big.getMoneda(txtTotalDescuentos.getText()));
        } else {
            valorBase = big.getMoneda(txtSubTotal.getText()).add(big.getMoneda(txtTotalDescuentos.getText()));
        }

        ModeloDescuentos[] informacionDescuentos = new ModeloDescuentos[1];

        if (descuentosDocumento.compareTo(BigDecimal.ZERO) > 0) {
            ModeloDescuentos modeloDescuento = new ModeloDescuentos();
            modeloDescuento.setTipo(false);
            modeloDescuento.setRazonDescuento(descripcionDescuento);
            modeloDescuento.setValorDescuento(Numeros.formatoDosDecimales.format(descuentosDocumento).replace(",", "."));
            modeloDescuento.setValorBase(Numeros.formatoDosDecimales.format(valorBase).replace(",", "."));
            System.out.println("valor base: " + Numeros.formatoDosDecimales.format(valorBase).replace(",", "."));

            BigDecimal porcentajeDescuento = descuentosDocumento.multiply(big.getBigDecimal(100)).divide(valorBase, 0, RoundingMode.HALF_UP);
            modeloDescuento.setPorcentaje(Numeros.formatoDosDecimales.format(porcentajeDescuento).replace(",", "."));
            System.out.println("porcentaje descuento: " + Numeros.formatoDosDecimales.format(porcentajeDescuento).replace(",", "."));
            modeloDescuento.setCodigoDescuento(codigoDescuento);
            informacionDescuentos[0] = modeloDescuento;
        }

        return informacionDescuentos;
    }

    private ModeloDetalleImpuestos obtenerImpuestosCompra(BigDecimal dsPorcentajeReteFuente) {

        List<Integer> ivas = new ArrayList<>();
        List<Integer> impoconsumos = new ArrayList<>();
        for (int i = 0; i < tblProductos.getRowCount(); i++) {
            int porcentajeIva = Integer.parseInt(tblProductos.getValueAt(i, 7).toString());
            int porcentajeConsumo = Integer.parseInt(tblProductos.getValueAt(i, 8).toString());

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
                if (Integer.parseInt(tblProductos.getValueAt(j, 7).toString()) == ivas.get(i)) {
                    subtotal = subtotal.add(big.getMoneda(tblProductos.getValueAt(j, 4).toString())).add(big.getMoneda(tblProductos.getValueAt(j, 6).toString()));
                    impuesto = impuesto.add(big.getMoneda(tblProductos.getValueAt(j, 22).toString()));
                }
            }

            informacionImpuestoIva[i][0] = subtotal;
            informacionImpuestoIva[i][1] = impuesto;
            informacionImpuestoIva[i][2] = Numeros.formatoDosDecimales.format(ivas.get(i)).replace(",", ".");
            informacionImpuestoIva[i][3] = "IVA";
        }

        for (int i = 0; i < impoconsumos.size(); i++) {
            BigDecimal subtotal = BigDecimal.ZERO;
            BigDecimal impuesto = BigDecimal.ZERO;
            for (int j = 0; j < tblProductos.getRowCount(); j++) {
                if (Integer.parseInt(tblProductos.getValueAt(j, 8).toString()) == impoconsumos.get(i)) {
                    subtotal = subtotal.add(big.getMoneda(tblProductos.getValueAt(j, 4).toString())).add(big.getMoneda(tblProductos.getValueAt(j, 6).toString()));;
                    impuesto = impuesto.add(big.getMoneda(tblProductos.getValueAt(j, 9).toString()));
                }
            }

            informacionImpuestoImpoconsumo[i][0] = subtotal;
            informacionImpuestoImpoconsumo[i][1] = impuesto;
            informacionImpuestoImpoconsumo[i][2] = Numeros.formatoDosDecimales.format(impoconsumos.get(i)).replace(",", ".");
            informacionImpuestoImpoconsumo[i][3] = "INC";
        }

        if (big.getMoneda(txtRiva.getText()).compareTo(BigDecimal.ZERO) > 0) {
            informacionReteIva[0][0] = big.getMoneda(txtTotalIva.getText());
            informacionReteIva[0][1] = big.getMoneda(txtRiva.getText());
            informacionReteIva[0][2] = "15.00";
            informacionReteIva[0][3] = "RETE_IVA";
        }

        if (big.getMoneda(txtRtf.getText()).compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal subtotal = big.getMoneda(txtSubTotal.getText());
            informacionReteFuente[0][0] = subtotal;
            informacionReteFuente[0][1] = obtenerRetencionFuente(subtotal);

            String porcentajeReteFuente = Numeros.formatoDosDecimales.format(dsPorcentajeReteFuente).replace(",", ".");
            if (dsPorcentajeReteFuente.toString().startsWith("0")) {
                porcentajeReteFuente = "0" + Numeros.formatoDosDecimales.format(dsPorcentajeReteFuente).replace(",", ".");
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

    private BigDecimal obtenerPorcentajeRetencionFuente() {
        BigDecimal porcentajeRetencion = BigDecimal.ZERO;
        if (cmbRtf.getSelectedIndex() > 0) {
            porcentajeRetencion = big.getBigDecimal(cmbRtf.getSelectedItem());
        }

        return porcentajeRetencion;
    }

    private BigDecimal obtenerRetencionFuente(BigDecimal subtotal) {
        BigDecimal retencionFuente = BigDecimal.ZERO;
        if (cmbRtf.getSelectedIndex() > 0) {
            retencionFuente = subtotal.multiply(big.getBigDecimal(cmbRtf.getSelectedItem())).divide(big.getBigDecimal("100"), 2, RoundingMode.HALF_UP);
        }

        return retencionFuente;
    }

    public void cargarDescuento(Integer fila, BigDecimal porcentajeDescuento, BigDecimal descuento, String descripcionDescuento) {
        if (null == porcentajeDescuento && null == descuento) {
            for (int i = 0; i < tblProductos.getRowCount(); i++) {
                tblProductos.setValueAt(0, i, 5);
                tblProductos.setValueAt("$ 0", i, 6);
                tblProductos.setValueAt("", i, 25);
                calcularTabla(i);
            }

            DESCUENTO_GENERAL_CARGADO = false;
        } else {
            if (null != fila) {
                if (null == porcentajeDescuento) {
                    tblProductos.setValueAt(descuento, fila, 6);
                } else {
                    tblProductos.setValueAt(porcentajeDescuento, fila, 5);
                }

                tblProductos.setValueAt(descripcionDescuento, fila, 25);
                calcularTabla(fila);
            } else {
                for (int i = 0; i < tblProductos.getRowCount(); i++) {
                    tblProductos.setValueAt(porcentajeDescuento, i, 5);
                    tblProductos.setValueAt(descripcionDescuento, i, 25);
                    calcularTabla(i);
                }

                DESCUENTO_GENERAL_CARGADO = true;
            }
        }

        guardarPrecompra();
    }

    private void abrirModalDescuentos() {
        if (this.tipoProceso.equals("ingreso") && !tblProductos.isEditing()) {
            String tipoOpcion = "Opcion-General";
            if (DESCUENTO_GENERAL_CARGADO) {
                tipoOpcion = tblProductos.getValueAt(0, 25).toString();
            }

            JFrame framePrincipal = (JFrame) SwingUtilities.getWindowAncestor(this);
            dlgTipoDescuento descuentoProd = new dlgTipoDescuento(framePrincipal, null, null, null, tipoOpcion, BigDecimal.ZERO, this.tipoProceso);
            descuentoProd.setVisible(true);
        }
    }

    private void abrirModalDescuentosProducto(int filaSelecciona) {
        if (this.tipoProceso.equals("ingreso")) {
            txtCodProducto.requestFocus();

            String descuento = "";
            String porcentajeDescuento = "";
            String tipoOpcion = tblProductos.getValueAt(filaSelecciona, 25).toString();

            BigDecimal valorProducto = big.getMoneda(String.valueOf(tblProductos.getValueAt(filaSelecciona, 2)));
            BigDecimal cantidad = big.getBigDecimal(tblProductos.getValueAt(filaSelecciona, 3).toString().replace(".", "").replace(",", "."));
            BigDecimal subtotal = valorProducto.multiply(cantidad);

            if (instancias.getDescuento().equals("peso")) {
                descuento = tblProductos.getValueAt(filaSelecciona, 6).toString();
            } else {
                porcentajeDescuento = tblProductos.getValueAt(filaSelecciona, 5).toString();
            }

            dlgTipoDescuento tipoDescuento = new dlgTipoDescuento(null, porcentajeDescuento, descuento, filaSelecciona, tipoOpcion, subtotal, this.tipoProceso);
            tipoDescuento.setVisible(true);
        }
    }

    private void actualizarCantidadPLU(int fila, BigDecimal cantidad) {
        String baseUtilizada = obtenerBase();
        String codigoProducto = tblProductos.getValueAt(fila, 24).toString();

        ndProducto producto = instancias.getSql().getDatosProducto(codigoProducto, baseUtilizada);
        int numeroPLU = Integer.parseInt(tblProductos.getValueAt(fila, 13).toString());

        BigDecimal cantidadPLU = getCantidadProductoPLU(producto, numeroPLU, cantidad);
        tblProductos.setValueAt(big.setNumero(cantidad.multiply(cantidadPLU)), fila, 14);
    }

    private BigDecimal getCantidadProductoPLU(ndProducto producto, int numeroPLU, BigDecimal cantidad) {
        switch (numeroPLU) {
            case 1:
                return BigDecimal.ONE;
            case 2:
                return big.getBigDecimal(producto.getCantidad2());
            case 3:
                return big.getBigDecimal(producto.getCantidad3());
            case 4:
                return big.getBigDecimal(producto.getCantidad4());
            case 5:
                return big.getBigDecimal(producto.getCantidad5());
            case 6:
                return big.getBigDecimal(producto.getCantidad6());
            case 7:
                return big.getBigDecimal(producto.getCantidad7());
            case 8:
                return big.getBigDecimal(producto.getCantidad8());
            default:
                return BigDecimal.ZERO;
        }
    }

    private ModeloDetalleProductos[] obtenerDetalleProductos(String factura) {

        int cantidadTotal = tblProductos.getRowCount();
        ModeloDetalleProductos[] detalladoProductos = new ModeloDetalleProductos[cantidadTotal];

        for (int i = 0; i < tblProductos.getRowCount(); i++) {
            ModeloDetalleProductos modeloIndividual = new ModeloDetalleProductos();
            modeloIndividual.setNumeroFactura(factura);
            modeloIndividual.setEstandarProducto("UNSPSC");
            modeloIndividual.setUnidadMedida(tblProductos.getValueAt(i, 10).toString());

            BigDecimal totalIva = big.getMoneda(tblProductos.getValueAt(i, 22).toString());
            BigDecimal totalImpoconsumo = big.getMoneda(tblProductos.getValueAt(i, 9).toString());
            BigDecimal valorTotalBruto = big.getMoneda(tblProductos.getValueAt(i, 2).toString()).multiply(big.getBigDecimal(tblProductos.getValueAt(i, 3).toString()));
            if (instancias.isPvpConIva()) {
                valorTotalBruto = valorTotalBruto.subtract(totalIva).subtract(totalImpoconsumo);
            }

            modeloIndividual.setValorTotalBruto(valorTotalBruto);
            modeloIndividual.setValorIva(totalIva);
            modeloIndividual.setPorcentajeIva(big.getBigDecimal(tblProductos.getValueAt(i, 7).toString()));
            modeloIndividual.setValorConsumo(totalImpoconsumo);
            modeloIndividual.setPorcentajeConsumo(big.getBigDecimal(tblProductos.getValueAt(i, 8).toString()));

            modeloIndividual.setDescripcionArticulo(tblProductos.getValueAt(i, 1).toString());
            modeloIndividual.setUnidadesEmpaque(big.getBigDecimal(tblProductos.getValueAt(i, 14)).divide(big.getBigDecimal(tblProductos.getValueAt(i, 3)), 2, RoundingMode.HALF_UP));

            modeloIndividual.setCodigoArticulo(tblProductos.getValueAt(i, 0).toString());
            modeloIndividual.setCodigoVendedor(DATOS_CLIENTE_CARGADO.getId());
            modeloIndividual.setPrecioUnitario(big.getMoneda(tblProductos.getValueAt(i, 2).toString()));
            modeloIndividual.setCantidad(tblProductos.getValueAt(i, 3).toString());

            modeloIndividual.setFechaInicio(metodos.fecha4(metodosGenerales.fecha()));
            modeloIndividual.setCodigoGeneracion("POR_OPERACION");

            ModeloDescuentos[] resultadosDescuentos = obtenerDescuentosProducto(i);
            modeloIndividual.setDescuentoProducto(resultadosDescuentos);

            Object[][] impuestosProducto = obtenerImpuestosPorProducto(modeloIndividual, i);
            modeloIndividual.setImpuestosProducto(impuestosProducto);
            detalladoProductos[i] = modeloIndividual;
        }

        return detalladoProductos;
    }

    private ModeloDescuentos[] obtenerDescuentosProducto(int filaProducto) {

        String codigoDescuento = "", descripcionDescuento = "";
        if (!"".equals(tblProductos.getValueAt(filaProducto, 25).toString())) {
            codigoDescuento = tblProductos.getValueAt(filaProducto, 25).toString().split("///")[0];
            descripcionDescuento = tblProductos.getValueAt(filaProducto, 25).toString().split("///")[1];
        }

        BigDecimal valorProducto = big.getMoneda(String.valueOf(tblProductos.getValueAt(filaProducto, 2)));
        BigDecimal cantidad = big.getBigDecimal(tblProductos.getValueAt(filaProducto, 3).toString().replace(".", "").replace(",", "."));
        BigDecimal valorBase = valorProducto.multiply(cantidad);

        BigDecimal descuentoProducto = big.getMoneda(tblProductos.getValueAt(filaProducto, 6).toString());
        ModeloDescuentos[] informacionDescuentos = new ModeloDescuentos[1];

        if (big.getBigDecimal(tblProductos.getValueAt(filaProducto, 5)).compareTo(BigDecimal.ZERO) > 0) {
            ModeloDescuentos modeloDescuento = new ModeloDescuentos();
            modeloDescuento.setTipo(false);
            modeloDescuento.setRazonDescuento(descripcionDescuento);
            modeloDescuento.setValorDescuento(Numeros.formatoDosDecimales.format(descuentoProducto).replace(",", "."));
            modeloDescuento.setValorBase(Numeros.formatoDosDecimales.format(valorBase).replace(",", "."));

            int porcentajeDescuento = Integer.parseInt(tblProductos.getValueAt(filaProducto, 5).toString());
            modeloDescuento.setPorcentaje(Numeros.formatoDosDecimales.format(porcentajeDescuento).replace(",", "."));
            modeloDescuento.setCodigoDescuento(codigoDescuento);
            informacionDescuentos[0] = modeloDescuento;
        }

        return informacionDescuentos;
    }

    private Object[][] obtenerImpuestosPorProducto(ModeloDetalleProductos detalleProducto, int filaProducto) {

        int secuencia = 0;
        Object[][] informacionImpuestosFactura = new Object[2][4];

        BigDecimal baseProducto = BigDecimal.ZERO;

        if (instancias.isCostoConIva()) {
            BigDecimal cantidad = big.getBigDecimal(tblProductos.getValueAt(filaProducto, 3).toString().replace(".", "").replace(",", "."));
            BigDecimal subtotal = detalleProducto.getPrecioUnitario().multiply(cantidad);
            BigDecimal porcentajeIva = detalleProducto.getPorcentajeIva().divide(big.getBigDecimal("100"));
            BigDecimal porcentajeImpoconsumo = detalleProducto.getPorcentajeConsumo().divide(big.getBigDecimal("100"));
            BigDecimal totalImpuestos = porcentajeIva.add(BigDecimal.ONE).multiply(porcentajeImpoconsumo.add(BigDecimal.ONE)).setScale(2, RoundingMode.DOWN);
            baseProducto = subtotal.divide(totalImpuestos, 2, RoundingMode.HALF_DOWN);
        } else {
            baseProducto = big.getMoneda(tblProductos.getValueAt(filaProducto, 4).toString())
                    .add(big.getMoneda(tblProductos.getValueAt(filaProducto, 6).toString()));
        }

        if (detalleProducto.getValorIva().compareTo(BigDecimal.ZERO) > 0) {
            informacionImpuestosFactura[secuencia][0] = baseProducto;
            informacionImpuestosFactura[secuencia][1] = detalleProducto.getValorIva();
            informacionImpuestosFactura[secuencia][2] = Numeros.formatoDosDecimales.format(detalleProducto.getPorcentajeIva()).replace(",", ".");
            informacionImpuestosFactura[secuencia][3] = "IVA";
            secuencia++;
        }

        if (detalleProducto.getValorConsumo().compareTo(BigDecimal.ZERO) > 0) {
            informacionImpuestosFactura[secuencia][0] = baseProducto;
            informacionImpuestosFactura[secuencia][1] = detalleProducto.getValorConsumo();
            informacionImpuestosFactura[secuencia][2] = Numeros.formatoDosDecimales.format(detalleProducto.getPorcentajeConsumo()).replace(",", ".");
            informacionImpuestosFactura[secuencia][3] = "INC";
            secuencia++;
        }

        return informacionImpuestosFactura;
    }

    private void revalidarTabla() {
        tblProductos.removeEditor();
        int totalLineas = tblProductos.getRowCount();
        if (totalLineas > 0) {
            for (int i = 0; i < totalLineas; i++) {
                calcularTabla(i);
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnular;
    private javax.swing.JButton btnBusProd;
    private javax.swing.JButton btnBuscTerceros;
    private javax.swing.JButton btnBuscTerceros2;
    private javax.swing.JButton btnBuscTerceros3;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnImportarExcel;
    private javax.swing.JButton btnInformacionCliente;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cmbBodegas;
    private javax.swing.JComboBox cmbCargar;
    private javax.swing.JComboBox cmbRtf;
    private javax.swing.JComboBox cmbTipo;
    private javax.swing.JComboBox cmbTipoImpresion;
    private javax.swing.JLabel etiqTotal;
    private javax.swing.JLabel etiqTotal1;
    private javax.swing.ButtonGroup grpTipoDescuento;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jtblComprobantes;
    private javax.swing.JLabel lbBodega;
    private javax.swing.JLabel lbFacturaNo;
    private javax.swing.JLabel lbFechaFactura;
    private javax.swing.JLabel lbNit;
    private javax.swing.JLabel lbNoFactura;
    private javax.swing.JLabel lbNombre;
    private javax.swing.JLabel lbNombre1;
    private javax.swing.JLabel lbNumero;
    private javax.swing.JLabel lbNumero1;
    private javax.swing.JLabel lbNumero2;
    private javax.swing.JLabel lbNumero3;
    private javax.swing.JLabel lbProducto;
    private javax.swing.JLabel lbProducto1;
    private javax.swing.JLabel lbProducto2;
    private javax.swing.JLabel lbSubtotal;
    private javax.swing.JLabel lbTipo;
    private javax.swing.JLabel lbTotalDescuento;
    private javax.swing.JLabel lbVendedor1;
    private javax.swing.JPanel pblBotones;
    private javax.swing.JPanel pnlFormulario;
    private javax.swing.JPanel pnlInvisible;
    private javax.swing.JMenuItem popBorrar;
    private javax.swing.JScrollPane scrollTblProductos;
    private javax.swing.JTable tblComprobantes;
    private javax.swing.JTable tblDetalle;
    private javax.swing.JTable tblProductos;
    private javax.swing.JTextField txtBodega;
    private javax.swing.JTextField txtCant;
    private javax.swing.JLabel txtCantProductos;
    private javax.swing.JLabel txtCantUnidades;
    private javax.swing.JTextField txtCargarCompra;
    private javax.swing.JTextField txtCodProducto;
    private javax.swing.JTextField txtDiasPlazo;
    private javax.swing.JLabel txtDiasPlazo1;
    private datechooser.beans.DateChooserCombo txtFechaFactura;
    private javax.swing.JLabel txtIva;
    private javax.swing.JTextField txtNit;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JTextArea txtObservaciones;
    private javax.swing.JTextField txtPorcentaje;
    private javax.swing.JLabel txtRiva;
    private javax.swing.JLabel txtRtf;
    private javax.swing.JLabel txtSubTotal;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JLabel txtTotal;
    private javax.swing.JLabel txtTotalDescuentos;
    private javax.swing.JLabel txtTotalImpoconsumo;
    private javax.swing.JLabel txtTotalIva;
    private datechooser.beans.DateChooserCombo txtVencimiento;
    // End of variables declaration//GEN-END:variables
}
