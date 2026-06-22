package Vista.Productos;

import Modelo.Inventario.UltimoPonderado;
import Modelo.Terceros.ModeloContacto;
import clases.ImagePreviewPanel;
import clases.Instancias;
import clases.big;
import clases.metodosGenerales;
import clases.productos.ndProducto;
import Modelo.Maestra.modeloConfiguracion;
import inventario.servicio.ServicioActualizacionPonderado;
import Utilidades.Constantes;
import formularios.Ventas.dlgPonderadoNegativo;
import formularios.productos.buscMarcas;
import formularios.productos.buscMedidas;
import formularios.productos.buscProductos;
import formularios.productos.buscSubGrupos;
import formularios.productos.buscTiposProductos;
import formularios.productos.dlgCodigoRelacionados;
import formularios.productos.dlgCreacionProductos;
import formularios.productos.dlgFotos;
import formularios.terceros.buscClientes;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.CopyOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

public class VistaProductos extends javax.swing.JInternalFrame {

    private ServicioActualizacionPonderado servicioActualizacionPonderado = new ServicioActualizacionPonderado();

    metodosGenerales metodos = new metodosGenerales();
    private Instancias instancias;

    private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension dimBarra = null;
    Object[][] grupos;
    ndProducto nodoProducto;
    modeloConfiguracion nodoConf;
    String simbolo = "";

    public VistaProductos() {

        initComponents();

        pnlInvisible.setVisible(false);
        txtImpoconsumo.setVisible(false);

        lbInfoObligatorios.setText(metodosGenerales.convertToMultiline("Los campos marcados (*) son obligatorios."));
        instancias = Instancias.getInstancias();

        simbolo = instancias.getSimbolo();
        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
        dimBarra = Barra.getPreferredSize();
        Barra.setSize(0, 0);
        Barra.setPreferredSize(new Dimension(0, 0));
        setBorder(null);
        repaint();

        if (instancias.getConfiguraciones().isProductosDetallados()) {
            chkImei.setVisible(true);
            chkImei.setVisible(true);
            chkColor.setVisible(true);
            chkSerial.setVisible(true);
            chkNinguno.setVisible(true);
            chkTalla.setVisible(true);
            chkColorTalla.setVisible(true);
            chkSerialColor.setVisible(true);
        } else {
            chkSerial.setVisible(false);
            chkImei.setVisible(false);
            chkLote.setVisible(false);
            chkColor.setVisible(false);
            chkNinguno.setVisible(false);
            chkTalla.setVisible(false);
            chkColorTalla.setVisible(false);
            chkSerialColor.setVisible(false);
        }

        if (!instancias.getConfiguraciones().isParqueadero()) {
            lbTelefono3.setVisible(false);
            txtPorcentaje.setVisible(false);
        }

        nodoConf = instancias.getSql().getDatosConfiguracion();
        txtCosto.setText(this.simbolo + " 0");
        actualizarGrupos();

        String consecutivo = "";
        int num = Integer.parseInt(instancias.getSql().getNumConsecutivo("PROD")[0].toString());
        consecutivo = String.valueOf(num);
        for (int i = 0; i < 8 - String.valueOf(num).length(); i++) {
            consecutivo = "0" + consecutivo;
        }
        txtIdSistema.setText("PROD-" + consecutivo);

        pnlFormulario.registerKeyboardAction(accion("guardar"), "guardar", KeyStroke.getKeyStroke(KeyEvent.VK_G, Event.CTRL_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
        pnlFormulario.registerKeyboardAction(accion("limpiar"), "limpiar", KeyStroke.getKeyStroke(KeyEvent.VK_L, Event.CTRL_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
        pnlFormulario.registerKeyboardAction(accion("productos"), "productos", KeyStroke.getKeyStroke(KeyEvent.VK_P, Event.CTRL_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
        pnlFormulario.registerKeyboardAction(accion("modificar"), "modificar", KeyStroke.getKeyStroke(KeyEvent.VK_M, Event.CTRL_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
    }

    private ActionListener accion(final String opc) {
        ActionListener a = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (opc) {
                    case "limpiar":
                        if ((btnNuevo.isEnabled()) && (btnNuevo.isVisible())) {
                            btnNuevoActionPerformed(null);
                        }
                        break;
                    case "guardar":
                        if ((btnGuardar.isEnabled()) && (btnGuardar.isVisible())) {
                            btnGuardarActionPerformed(null);
                        }
                        break;
                    case "productos":
                        if ((btnBusProd.isEnabled()) && (btnBusProd.isVisible())) {
                            btnBusProdActionPerformed(null);
                        }
                        break;
                    case "modificar":
                        if ((btnActualizar.isEnabled()) && (btnActualizar.isVisible())) {
                            btnActualizarActionPerformed(null);
                        }
                        break;
                }
            }
        };
        return a;
    }

    public Object[] getOcultarIva() {
        return new Object[]{txtIva, lbIvaVenta, txtImpoconsumo, lbImpoVenta, txtIvaCompra, lbIvaCompra, txtImpoconsumoCompra, lbImpoCompra};
    }

    public void ocultarItems(boolean ocultar) {
        txtIva.setVisible(ocultar);
        lbIvaVenta.setVisible(ocultar);
        txtImpoconsumo.setVisible(ocultar);
        lbImpoVenta.setVisible(ocultar);
        txtIvaCompra.setVisible(ocultar);
        lbIvaCompra.setVisible(ocultar);
        txtImpoconsumoCompra.setVisible(ocultar);
        lbImpoCompra.setVisible(ocultar);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        popBorrar = new javax.swing.JMenuItem();
        grpInventario = new javax.swing.ButtonGroup();
        grpTipoProd = new javax.swing.ButtonGroup();
        scrFormulario = new javax.swing.JScrollPane();
        pnlFormulario = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnNuevo = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnBusProd = new javax.swing.JButton();
        lbInfoObligatorios = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        txtCodigoSubGrupo = new javax.swing.JTextField();
        lbTelefono3 = new javax.swing.JLabel();
        txtPorcentaje = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        txtIvaCompra = new javax.swing.JTextField();
        txtIva = new javax.swing.JTextField();
        lbDepartamento3 = new javax.swing.JLabel();
        txtCosto = new javax.swing.JTextField();
        lbDepartamento1 = new javax.swing.JLabel();
        txtCosto1 = new javax.swing.JTextField();
        lbDepartamento2 = new javax.swing.JLabel();
        txtMinima = new javax.swing.JTextField();
        txtUtilidad1 = new javax.swing.JTextField();
        txtUtilidad2 = new javax.swing.JTextField();
        txtMaximo = new javax.swing.JTextField();
        lbDepartamento4 = new javax.swing.JLabel();
        lbCupo1 = new javax.swing.JLabel();
        txtMinimo = new javax.swing.JTextField();
        txtImpoconsumo = new javax.swing.JTextField();
        lbImpoCompra = new javax.swing.JCheckBox();
        lbIvaCompra = new javax.swing.JCheckBox();
        txtImpoconsumoCompra = new javax.swing.JTextField();
        lbImpoVenta = new javax.swing.JLabel();
        lbIvaVenta = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        lbNit = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        lbCupo7 = new javax.swing.JLabel();
        txtCodigoBarras = new javax.swing.JTextField();
        lbRazon = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        lbRazon1 = new javax.swing.JLabel();
        txtDescripcionIngles = new javax.swing.JTextField();
        lbDireccion = new javax.swing.JLabel();
        txtNombreProveedor = new javax.swing.JTextField();
        lbTelefono1 = new javax.swing.JLabel();
        cmbClaseBuscador = new javax.swing.JComboBox();
        lbTelefono = new javax.swing.JLabel();
        txtGrupo = new javax.swing.JComboBox();
        lbCelular = new javax.swing.JLabel();
        txtSubgrupo = new javax.swing.JTextField();
        lbCupo4 = new javax.swing.JLabel();
        txtReferencia = new javax.swing.JTextField();
        lbTelefono2 = new javax.swing.JLabel();
        rdInventarioSI = new javax.swing.JRadioButton();
        rdInventarioNO = new javax.swing.JRadioButton();
        txtFCompra = new javax.swing.JTextField();
        txtMedida = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtTipoProd = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtMarca = new javax.swing.JTextField();
        txtPeso = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        btnRelacionados = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescripcionLarga = new javax.swing.JTextArea();
        lbRazon2 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        pnlInvisible1 = new javax.swing.JPanel();
        chkNinguno = new javax.swing.JCheckBox();
        chkColor = new javax.swing.JCheckBox();
        chkImei = new javax.swing.JCheckBox();
        chkLote = new javax.swing.JCheckBox();
        chkSerial = new javax.swing.JCheckBox();
        chkTalla = new javax.swing.JCheckBox();
        chkColorTalla = new javax.swing.JCheckBox();
        chkSerialColor = new javax.swing.JCheckBox();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        lbCiudad = new javax.swing.JLabel();
        lbCiudad1 = new javax.swing.JLabel();
        txtCodigo1 = new javax.swing.JTextField();
        txtDescripcion1 = new javax.swing.JTextField();
        lbCiudad2 = new javax.swing.JLabel();
        lbCiudad3 = new javax.swing.JLabel();
        txtL1 = new javax.swing.JTextField();
        txtUbicacion = new javax.swing.JTextField();
        lbCiudad20 = new javax.swing.JLabel();
        txtUtilidadL = new javax.swing.JTextField();
        txtUtilidadL1 = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        lbCiudad4 = new javax.swing.JLabel();
        lbCiudad5 = new javax.swing.JLabel();
        txtCodigo2 = new javax.swing.JTextField();
        txtDescripcion2 = new javax.swing.JTextField();
        lbCiudad6 = new javax.swing.JLabel();
        lbCiudad7 = new javax.swing.JLabel();
        txtL2 = new javax.swing.JTextField();
        txtUbicacion2 = new javax.swing.JTextField();
        lbCiudad9 = new javax.swing.JLabel();
        txtCantidad2 = new javax.swing.JTextField();
        plu2 = new javax.swing.JCheckBox();
        lbCiudad21 = new javax.swing.JLabel();
        txtUtilidadL2 = new javax.swing.JTextField();
        txtUtilidadL3 = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        lbCiudad10 = new javax.swing.JLabel();
        lbCiudad11 = new javax.swing.JLabel();
        txtCodigo3 = new javax.swing.JTextField();
        txtDescripcion3 = new javax.swing.JTextField();
        lbCiudad12 = new javax.swing.JLabel();
        lbCiudad13 = new javax.swing.JLabel();
        txtL3 = new javax.swing.JTextField();
        txtUbicacion3 = new javax.swing.JTextField();
        txtCantidad3 = new javax.swing.JTextField();
        plu3 = new javax.swing.JCheckBox();
        txtUtilidadL5 = new javax.swing.JTextField();
        txtUtilidadL4 = new javax.swing.JTextField();
        lbCiudad22 = new javax.swing.JLabel();
        lbCiudad14 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        lbCiudad15 = new javax.swing.JLabel();
        lbCiudad16 = new javax.swing.JLabel();
        txtCodigo4 = new javax.swing.JTextField();
        txtDescripcion4 = new javax.swing.JTextField();
        lbCiudad17 = new javax.swing.JLabel();
        lbCiudad18 = new javax.swing.JLabel();
        txtL4 = new javax.swing.JTextField();
        txtUbicacion4 = new javax.swing.JTextField();
        lbCiudad19 = new javax.swing.JLabel();
        txtCantidad4 = new javax.swing.JTextField();
        plu4 = new javax.swing.JCheckBox();
        txtUtilidadL7 = new javax.swing.JTextField();
        txtUtilidadL6 = new javax.swing.JTextField();
        lbCiudad23 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        lbCiudad24 = new javax.swing.JLabel();
        lbCiudad25 = new javax.swing.JLabel();
        txtCodigo5 = new javax.swing.JTextField();
        txtDescripcion5 = new javax.swing.JTextField();
        lbCiudad26 = new javax.swing.JLabel();
        lbCiudad27 = new javax.swing.JLabel();
        txtL5 = new javax.swing.JTextField();
        txtUbicacion5 = new javax.swing.JTextField();
        lbCiudad28 = new javax.swing.JLabel();
        txtUtilidadL8 = new javax.swing.JTextField();
        txtUtilidadL9 = new javax.swing.JTextField();
        lbCiudad47 = new javax.swing.JLabel();
        txtCantidad5 = new javax.swing.JTextField();
        plu5 = new javax.swing.JCheckBox();
        jPanel12 = new javax.swing.JPanel();
        lbCiudad29 = new javax.swing.JLabel();
        lbCiudad30 = new javax.swing.JLabel();
        txtCodigo6 = new javax.swing.JTextField();
        txtDescripcion6 = new javax.swing.JTextField();
        lbCiudad31 = new javax.swing.JLabel();
        lbCiudad32 = new javax.swing.JLabel();
        txtL6 = new javax.swing.JTextField();
        txtUbicacion6 = new javax.swing.JTextField();
        lbCiudad33 = new javax.swing.JLabel();
        txtCantidad6 = new javax.swing.JTextField();
        plu6 = new javax.swing.JCheckBox();
        lbCiudad34 = new javax.swing.JLabel();
        txtUtilidadL10 = new javax.swing.JTextField();
        txtUtilidadL11 = new javax.swing.JTextField();
        jPanel13 = new javax.swing.JPanel();
        lbCiudad35 = new javax.swing.JLabel();
        lbCiudad36 = new javax.swing.JLabel();
        txtCodigo7 = new javax.swing.JTextField();
        txtDescripcion7 = new javax.swing.JTextField();
        lbCiudad37 = new javax.swing.JLabel();
        lbCiudad38 = new javax.swing.JLabel();
        txtL7 = new javax.swing.JTextField();
        txtUbicacion7 = new javax.swing.JTextField();
        lbCiudad39 = new javax.swing.JLabel();
        txtCantidad7 = new javax.swing.JTextField();
        plu7 = new javax.swing.JCheckBox();
        txtUtilidadL13 = new javax.swing.JTextField();
        txtUtilidadL12 = new javax.swing.JTextField();
        lbCiudad40 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        lbCiudad41 = new javax.swing.JLabel();
        lbCiudad42 = new javax.swing.JLabel();
        txtCodigo8 = new javax.swing.JTextField();
        txtDescripcion8 = new javax.swing.JTextField();
        lbCiudad43 = new javax.swing.JLabel();
        lbCiudad44 = new javax.swing.JLabel();
        txtL8 = new javax.swing.JTextField();
        txtUbicacion8 = new javax.swing.JTextField();
        lbCiudad45 = new javax.swing.JLabel();
        txtCantidad8 = new javax.swing.JTextField();
        plu8 = new javax.swing.JCheckBox();
        txtUtilidadL15 = new javax.swing.JTextField();
        txtUtilidadL14 = new javax.swing.JTextField();
        lbCiudad46 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProductosUnificados = new javax.swing.JTable();
        txtCodUnificar = new javax.swing.JTextField();
        lbCiudad8 = new javax.swing.JLabel();
        txtDescripcionUnificar = new javax.swing.JTextField();
        btnUnificar = new javax.swing.JButton();
        mensaje2 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        btnImagen = new javax.swing.JButton();
        lbFoto = new javax.swing.JLabel();
        mensaje3 = new javax.swing.JLabel();
        mensaje4 = new javax.swing.JLabel();
        pnlInvisible = new javax.swing.JPanel();
        txtIdSistema = new javax.swing.JTextField();
        txtProveedor = new javax.swing.JTextField();

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

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        btnNuevo.setBackground(new java.awt.Color(204, 204, 204));
        btnNuevo.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/nuevo.png"))); // NOI18N
        btnNuevo.setText("NUEVO");
        btnNuevo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnNuevo.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnNuevo.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnActualizar.setBackground(new java.awt.Color(93, 173, 226));
        btnActualizar.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/actualizar.png"))); // NOI18N
        btnActualizar.setText("MODIFICAR");
        btnActualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnActualizar.setEnabled(false);
        btnActualizar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnActualizar.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnEliminar.setBackground(new java.awt.Color(241, 148, 138));
        btnEliminar.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/borrar2.png"))); // NOI18N
        btnEliminar.setText("INACTIVAR");
        btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnEliminar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnEliminar.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnBusProd.setBackground(new java.awt.Color(247, 220, 111));
        btnBusProd.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnBusProd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/producto.png"))); // NOI18N
        btnBusProd.setText("LISTADO");
        btnBusProd.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnBusProd.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnBusProd.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnBusProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBusProdActionPerformed(evt);
            }
        });

        lbInfoObligatorios.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        lbInfoObligatorios.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbInfoObligatorios.setText("Los campos marcados con asterisco (*) son obligatorios.");
        lbInfoObligatorios.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbInfoObligatorios, javax.swing.GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnBusProd, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnGuardar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(5, 5, 5))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnNuevo)
                    .addComponent(btnGuardar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnActualizar)
                    .addComponent(btnBusProd))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminar)
                .addGap(5, 5, 5)
                .addComponent(lbInfoObligatorios, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        txtCodigoSubGrupo.setEditable(false);
        txtCodigoSubGrupo.setName("Cod SubGrupo"); // NOI18N
        txtCodigoSubGrupo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoSubGrupoActionPerformed(evt);
            }
        });
        txtCodigoSubGrupo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoSubGrupoKeyReleased(evt);
            }
        });

        lbTelefono3.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbTelefono3.setText("Porcentaje ganancia del servicio:");

        txtPorcentaje.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtPorcentaje.setName("Subgrupo"); // NOI18N
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

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        txtIvaCompra.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtIvaCompra.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtIvaCompra.setText("0");
        txtIvaCompra.setEnabled(false);
        txtIvaCompra.setName("IVA Compra"); // NOI18N
        txtIvaCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIvaCompraActionPerformed(evt);
            }
        });
        txtIvaCompra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIvaCompraKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIvaCompraKeyTyped(evt);
            }
        });

        txtIva.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtIva.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtIva.setText("0");
        txtIva.setEnabled(false);
        txtIva.setName("IVA_VENTA"); // NOI18N
        txtIva.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIvaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIvaKeyTyped(evt);
            }
        });

        lbDepartamento3.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDepartamento3.setText("Ultimo Costo:");

        txtCosto.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtCosto.setText("0");
        txtCosto.setName("Costo"); // NOI18N
        txtCosto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCostoActionPerformed(evt);
            }
        });
        txtCosto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCostoKeyReleased(evt);
            }
        });

        lbDepartamento1.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDepartamento1.setText("Costo Ponderado:");

        txtCosto1.setEditable(false);
        txtCosto1.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtCosto1.setName("Costo"); // NOI18N
        txtCosto1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCosto1ActionPerformed(evt);
            }
        });
        txtCosto1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCosto1KeyReleased(evt);
            }
        });

        lbDepartamento2.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbDepartamento2.setText("Utilidad Minima:");

        txtMinima.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtMinima.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMinima.setText("20");
        txtMinima.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMinimaActionPerformed(evt);
            }
        });
        txtMinima.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMinimaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMinimaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMinimaKeyTyped(evt);
            }
        });

        txtUtilidad1.setEditable(false);
        txtUtilidad1.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtUtilidad1.setName("Utilidad"); // NOI18N
        txtUtilidad1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUtilidad1ActionPerformed(evt);
            }
        });
        txtUtilidad1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUtilidad1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUtilidad1KeyTyped(evt);
            }
        });

        txtUtilidad2.setEditable(false);
        txtUtilidad2.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtUtilidad2.setName("IVA"); // NOI18N
        txtUtilidad2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUtilidad2KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUtilidad2KeyTyped(evt);
            }
        });

        txtMaximo.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtMaximo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMaximo.setText("30");
        txtMaximo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaximoActionPerformed(evt);
            }
        });
        txtMaximo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMaximoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMaximoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMaximoKeyTyped(evt);
            }
        });

        lbDepartamento4.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbDepartamento4.setText("Utilidad Máxima:");

        lbCupo1.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbCupo1.setText("Punto minimo:");

        txtMinimo.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtMinimo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMinimo.setName("Punto minimo"); // NOI18N
        txtMinimo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMinimoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMinimoKeyTyped(evt);
            }
        });

        txtImpoconsumo.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtImpoconsumo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtImpoconsumo.setText("0");
        txtImpoconsumo.setEnabled(false);
        txtImpoconsumo.setName("IVA_VENTA"); // NOI18N
        txtImpoconsumo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtImpoconsumoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtImpoconsumoKeyTyped(evt);
            }
        });

        lbImpoCompra.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbImpoCompra.setText("Impoconsumo Compra:");
        lbImpoCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lbImpoCompraActionPerformed(evt);
            }
        });

        lbIvaCompra.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbIvaCompra.setText("IVA Compra: *");
        lbIvaCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lbIvaCompraActionPerformed(evt);
            }
        });

        txtImpoconsumoCompra.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtImpoconsumoCompra.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtImpoconsumoCompra.setText("0");
        txtImpoconsumoCompra.setEnabled(false);
        txtImpoconsumoCompra.setName("IVA Compra"); // NOI18N
        txtImpoconsumoCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtImpoconsumoCompraActionPerformed(evt);
            }
        });
        txtImpoconsumoCompra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtImpoconsumoCompraKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtImpoconsumoCompraKeyTyped(evt);
            }
        });

        lbImpoVenta.setFont(new java.awt.Font("Century Gothic", 1, 15)); // NOI18N
        lbImpoVenta.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbImpoVenta.setText(" Impoconsumo Venta:");

        lbIvaVenta.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbIvaVenta.setText(" IVA Venta: *");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbDepartamento4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbDepartamento2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbDepartamento1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbDepartamento3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbIvaCompra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbIvaVenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCosto, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtMaximo, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
                            .addComponent(txtIvaCompra)
                            .addComponent(txtIva)
                            .addComponent(txtMinima, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCosto1))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtUtilidad1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addComponent(lbCupo1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbImpoCompra)
                                    .addComponent(lbImpoVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtImpoconsumoCompra, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtImpoconsumo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(txtUtilidad2))))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtIvaCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbImpoCompra)
                        .addComponent(txtImpoconsumoCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbIvaCompra, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(3, 3, 3)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbIvaVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtIva, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtImpoconsumo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbImpoVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(5, 5, 5)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lbDepartamento1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCosto1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbCupo1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(5, 5, 5)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtMinima)
                    .addComponent(txtUtilidad1)
                    .addComponent(lbDepartamento2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtUtilidad2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(txtMaximo, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbDepartamento4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbDepartamento3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCosto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txtMinimo.getAccessibleContext().setAccessibleName("");

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));

        lbNit.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit.setText("Codigo: *");
        lbNit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lbNitKeyReleased(evt);
            }
        });

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

        lbCupo7.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbCupo7.setText("Codigo de barras (EAN):");
        lbCupo7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbCupo7MouseClicked(evt);
            }
        });

        txtCodigoBarras.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtCodigoBarras.setName("Referencia"); // NOI18N
        txtCodigoBarras.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoBarrasKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoBarrasKeyTyped(evt);
            }
        });

        lbRazon.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbRazon.setText("Descripción: *");

        txtDescripcion.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtDescripcion.setName("Descripción"); // NOI18N
        txtDescripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDescripcionKeyReleased(evt);
            }
        });

        lbRazon1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lbRazon1.setText("Descripción inglés:");

        txtDescripcionIngles.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtDescripcionIngles.setName("Descripción"); // NOI18N
        txtDescripcionIngles.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDescripcionInglesKeyReleased(evt);
            }
        });

        lbDireccion.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion.setText("Proveedor:");

        txtNombreProveedor.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtNombreProveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreProveedorKeyReleased(evt);
            }
        });

        lbTelefono1.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTelefono1.setText("Clase Buscador:");
        lbTelefono1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbTelefono1MouseClicked(evt);
            }
        });

        cmbClaseBuscador.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Agenda" }));
        cmbClaseBuscador.setName("Grupo"); // NOI18N
        cmbClaseBuscador.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbClaseBuscadorItemStateChanged(evt);
            }
        });

        lbTelefono.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbTelefono.setText("Grupo:");

        txtGrupo.setName("Grupo"); // NOI18N
        txtGrupo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                txtGrupoItemStateChanged(evt);
            }
        });
        txtGrupo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGrupoActionPerformed(evt);
            }
        });

        lbCelular.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbCelular.setText("Subgrupo:");

        txtSubgrupo.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtSubgrupo.setName("Subgrupo"); // NOI18N
        txtSubgrupo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSubgrupoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSubgrupoKeyTyped(evt);
            }
        });

        lbCupo4.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbCupo4.setText("Referencia:");

        txtReferencia.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtReferencia.setName("Referencia"); // NOI18N
        txtReferencia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtReferenciaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtReferenciaKeyTyped(evt);
            }
        });

        lbTelefono2.setFont(new java.awt.Font("Century Gothic", 1, 15)); // NOI18N
        lbTelefono2.setText("Maneja Inventario:");

        rdInventarioSI.setBackground(new java.awt.Color(255, 255, 255));
        grpInventario.add(rdInventarioSI);
        rdInventarioSI.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        rdInventarioSI.setSelected(true);
        rdInventarioSI.setText("SI");

        rdInventarioNO.setBackground(new java.awt.Color(255, 255, 255));
        grpInventario.add(rdInventarioNO);
        rdInventarioNO.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        rdInventarioNO.setText("NO");

        txtFCompra.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        txtFCompra.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFCompra.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtFCompra.setEnabled(false);

        txtMedida.setBackground(new java.awt.Color(255, 204, 204));
        txtMedida.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtMedida.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMedida.setName("Medida"); // NOI18N
        txtMedida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMedidaActionPerformed(evt);
            }
        });
        txtMedida.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMedidaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMedidaKeyTyped(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel1.setText("Tipo prod:");

        txtTipoProd.setBackground(new java.awt.Color(255, 204, 204));
        txtTipoProd.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtTipoProd.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTipoProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTipoProdActionPerformed(evt);
            }
        });
        txtTipoProd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTipoProdKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTipoProdKeyReleased(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel2.setText("Marca:");

        jLabel3.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel3.setText("Peso en Kg:");

        txtMarca.setBackground(new java.awt.Color(255, 204, 204));
        txtMarca.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtMarca.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMarca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMarcaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMarcaKeyReleased(evt);
            }
        });

        txtPeso.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtPeso.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPeso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPesoKeyTyped(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel4.setText("Unidad medida:");

        jLabel5.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jLabel5.setText("Ultima Compra:");

        jButton2.setBackground(new java.awt.Color(204, 204, 204));
        jButton2.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar1.png"))); // NOI18N
        jButton2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton2.setMargin(new java.awt.Insets(2, 7, 2, 5));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(204, 204, 204));
        jButton1.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar1.png"))); // NOI18N
        jButton1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton1.setMargin(new java.awt.Insets(2, 7, 2, 5));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnRelacionados.setBackground(new java.awt.Color(204, 204, 204));
        btnRelacionados.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnRelacionados.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnRelacionados.setText("CODIGOS RELACIONADOS");
        btnRelacionados.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnRelacionados.setEnabled(false);
        btnRelacionados.setOpaque(true);
        btnRelacionados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRelacionadosMouseClicked(evt);
            }
        });

        jScrollPane2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jScrollPane2KeyReleased(evt);
            }
        });

        txtDescripcionLarga.setColumns(20);
        txtDescripcionLarga.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtDescripcionLarga.setLineWrap(true);
        txtDescripcionLarga.setRows(2);
        txtDescripcionLarga.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDescripcionLargaKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(txtDescripcionLarga);

        lbRazon2.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lbRazon2.setText("Descripción larga:");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTipoProd, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                            .addComponent(txtMarca)
                            .addComponent(txtPeso))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtMedida, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                            .addComponent(txtFCompra)))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRelacionados, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lbCupo4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbTelefono, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbTelefono1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbRazon1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                            .addComponent(lbRazon, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbNit, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbRazon2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2)
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lbCupo7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCodigoBarras, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
                            .addComponent(txtDescripcion)
                            .addComponent(txtDescripcionIngles)
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtReferencia, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtGrupo, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cmbClaseBuscador, javax.swing.GroupLayout.Alignment.LEADING, 0, 160, Short.MAX_VALUE))
                                .addGap(29, 29, 29)
                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel17Layout.createSequentialGroup()
                                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                                                .addComponent(lbDireccion)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                            .addGroup(jPanel17Layout.createSequentialGroup()
                                                .addComponent(lbCelular)
                                                .addGap(7, 7, 7)))
                                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtSubgrupo)
                                            .addComponent(txtNombreProveedor))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel17Layout.createSequentialGroup()
                                        .addComponent(lbTelefono2)
                                        .addGap(18, 18, 18)
                                        .addComponent(rdInventarioSI)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(rdInventarioNO)
                                        .addGap(0, 0, Short.MAX_VALUE)))))))
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(btnRelacionados)
                .addGap(5, 5, 5)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbCupo7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCodigoBarras, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lbNit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(2, 2, 2)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(lbRazon, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbRazon2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE))
                .addGap(2, 2, 2)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtDescripcionIngles, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(lbRazon1, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbTelefono1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(txtNombreProveedor)
                        .addComponent(cmbClaseBuscador))
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSubgrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(lbCelular, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                        .addComponent(lbTelefono, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                        .addComponent(txtGrupo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbCupo4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtReferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbTelefono2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rdInventarioSI, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rdInventarioNO, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(5, 5, 5)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtTipoProd, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtMedida, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addGap(2, 2, 2)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPeso, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                .addGap(2, 2, 2))
        );

        jSeparator4.setForeground(new java.awt.Color(0, 0, 0));

        pnlInvisible1.setBackground(new java.awt.Color(255, 255, 255));

        grpTipoProd.add(chkNinguno);
        chkNinguno.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        chkNinguno.setSelected(true);
        chkNinguno.setText("Ninguno");
        chkNinguno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkNingunoActionPerformed(evt);
            }
        });

        grpTipoProd.add(chkColor);
        chkColor.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        chkColor.setText("Color");
        chkColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkColorActionPerformed(evt);
            }
        });

        grpTipoProd.add(chkImei);
        chkImei.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        chkImei.setText("IMEI");
        chkImei.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkImeiActionPerformed(evt);
            }
        });

        grpTipoProd.add(chkLote);
        chkLote.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        chkLote.setText("Lote/F. Vence");
        chkLote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkLoteActionPerformed(evt);
            }
        });

        grpTipoProd.add(chkSerial);
        chkSerial.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        chkSerial.setText("Serial");
        chkSerial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSerialActionPerformed(evt);
            }
        });

        grpTipoProd.add(chkTalla);
        chkTalla.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        chkTalla.setText("Talla");
        chkTalla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTallaActionPerformed(evt);
            }
        });

        grpTipoProd.add(chkColorTalla);
        chkColorTalla.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        chkColorTalla.setText("Color y Talla");
        chkColorTalla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkColorTallaActionPerformed(evt);
            }
        });

        grpTipoProd.add(chkSerialColor);
        chkSerialColor.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        chkSerialColor.setText("Serial y Color");
        chkSerialColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSerialColorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlInvisible1Layout = new javax.swing.GroupLayout(pnlInvisible1);
        pnlInvisible1.setLayout(pnlInvisible1Layout);
        pnlInvisible1Layout.setHorizontalGroup(
            pnlInvisible1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInvisible1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlInvisible1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkNinguno, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkImei)
                    .addComponent(chkLote)
                    .addComponent(chkColor)
                    .addComponent(chkSerial)
                    .addComponent(chkTalla)
                    .addGroup(pnlInvisible1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(chkColorTalla, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(chkSerialColor, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlInvisible1Layout.setVerticalGroup(
            pnlInvisible1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlInvisible1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(chkNinguno)
                .addGap(3, 3, 3)
                .addComponent(chkColor)
                .addGap(3, 3, 3)
                .addComponent(chkImei)
                .addGap(3, 3, 3)
                .addComponent(chkLote)
                .addGap(3, 3, 3)
                .addComponent(chkSerial)
                .addGap(2, 2, 2)
                .addComponent(chkTalla)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkColorTalla)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(chkSerialColor)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(527, Short.MAX_VALUE)
                .addComponent(txtCodigoSubGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(175, 175, 175))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 655, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lbTelefono3)
                                .addGap(18, 18, 18)
                                .addComponent(txtPorcentaje, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlInvisible1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtPorcentaje)
                                .addGap(152, 152, 152))
                            .addComponent(lbTelefono3)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(pnlInvisible1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(txtCodigoSubGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(168, 168, 168))
        );

        jTabbedPane1.addTab("Codificación", jPanel1);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "L1", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Bell MT", 1, 14), new java.awt.Color(204, 0, 0))); // NOI18N

        lbCiudad.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbCiudad.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCiudad.setText("PLU-1");

        lbCiudad1.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbCiudad1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCiudad1.setText("Descripcion");

        txtCodigo1.setEditable(false);
        txtCodigo1.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtCodigo1.setName("Codigo"); // NOI18N
        txtCodigo1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigo1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigo1KeyTyped(evt);
            }
        });

        txtDescripcion1.setEditable(false);
        txtDescripcion1.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtDescripcion1.setName("Descripción"); // NOI18N
        txtDescripcion1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDescripcion1KeyReleased(evt);
            }
        });

        lbCiudad2.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbCiudad2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCiudad2.setText("Valor  *");

        lbCiudad3.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbCiudad3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCiudad3.setText("Ubicación");

        txtL1.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtL1.setName("Lista 1"); // NOI18N
        txtL1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtL1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtL1KeyTyped(evt);
            }
        });

        txtUbicacion.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtUbicacion.setName("Descripción"); // NOI18N
        txtUbicacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUbicacionKeyReleased(evt);
            }
        });

        lbCiudad20.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbCiudad20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCiudad20.setText("Utilidad:");

        txtUtilidadL.setEditable(false);
        txtUtilidadL.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtUtilidadL.setName("Descripción"); // NOI18N
        txtUtilidadL.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUtilidadLKeyReleased(evt);
            }
        });

        txtUtilidadL1.setEditable(false);
        txtUtilidadL1.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtUtilidadL1.setName("Descripción"); // NOI18N
        txtUtilidadL1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUtilidadL1KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbCiudad20, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                    .addComponent(txtL1)
                    .addComponent(lbCiudad2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCodigo1)
                    .addComponent(lbCiudad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lbCiudad3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addComponent(txtUtilidadL, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtUtilidadL1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtDescripcion1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbCiudad1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
                    .addComponent(txtUbicacion))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCiudad)
                    .addComponent(lbCiudad1))
                .addGap(2, 2, 2)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCodigo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDescripcion1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCiudad2)
                    .addComponent(lbCiudad3))
                .addGap(2, 2, 2)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtL1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUbicacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCiudad20)
                    .addComponent(txtUtilidadL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUtilidadL1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "L2", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Bell MT", 1, 14), new java.awt.Color(204, 0, 0))); // NOI18N

        lbCiudad4.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbCiudad4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCiudad4.setText("PLU-2");

        lbCiudad5.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbCiudad5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCiudad5.setText("Descripcion");

        txtCodigo2.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtCodigo2.setName("Codigo"); // NOI18N
        txtCodigo2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigo2KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigo2KeyTyped(evt);
            }
        });

        txtDescripcion2.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtDescripcion2.setName("Descripción"); // NOI18N
        txtDescripcion2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDescripcion2KeyReleased(evt);
            }
        });

        lbCiudad6.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbCiudad6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCiudad6.setText("Valor");

        lbCiudad7.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbCiudad7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCiudad7.setText("Ubicación");

        txtL2.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtL2.setName("Lista 1"); // NOI18N
        txtL2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtL2KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtL2KeyTyped(evt);
            }
        });

        txtUbicacion2.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtUbicacion2.setName("Descripción"); // NOI18N
        txtUbicacion2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUbicacion2KeyReleased(evt);
            }
        });

        lbCiudad9.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbCiudad9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCiudad9.setText("Cant:");

        txtCantidad2.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtCantidad2.setName("Descripción"); // NOI18N
        txtCantidad2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCantidad2KeyReleased(evt);
            }
        });

        plu2.setBackground(new java.awt.Color(255, 255, 255));
        plu2.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        plu2.setText("Activar");

        lbCiudad21.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbCiudad21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCiudad21.setText("Utilidad:");

        txtUtilidadL2.setEditable(false);
        txtUtilidadL2.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtUtilidadL2.setName("Descripción"); // NOI18N
        txtUtilidadL2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUtilidadL2KeyReleased(evt);
            }
        });

        txtUtilidadL3.setEditable(false);
        txtUtilidadL3.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtUtilidadL3.setName("Descripción"); // NOI18N
        txtUtilidadL3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUtilidadL3KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtL2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbCiudad6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCodigo2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbCiudad4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbCiudad21, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addComponent(txtUtilidadL2, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtUtilidadL3, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(lbCiudad5, javax.swing.GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE)
                        .addComponent(lbCiudad7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtUbicacion2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE)
                        .addComponent(txtDescripcion2, javax.swing.GroupLayout.Alignment.LEADING))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(lbCiudad9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCantidad2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(plu2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCiudad4)
                    .addComponent(lbCiudad5))
                .addGap(2, 2, 2)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCodigo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDescripcion2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCiudad6)
                    .addComponent(lbCiudad7))
                .addGap(2, 2, 2)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtL2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUbicacion2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbCiudad9)
                        .addComponent(txtCantidad2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(plu2)
                        .addComponent(txtUtilidadL3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtUtilidadL2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(lbCiudad21)
                        .addGap(1, 1, 1)))
                .addGap(2, 2, 2))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "L3", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Bell MT", 1, 14), new java.awt.Color(204, 0, 0))); // NOI18N

        lbCiudad10.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbCiudad10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCiudad10.setText("PLU-3");

        lbCiudad11.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbCiudad11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCiudad11.setText("Descripcion");

        txtCodigo3.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtCodigo3.setName("Codigo"); // NOI18N
        txtCodigo3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigo3KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigo3KeyTyped(evt);
            }
        });

        txtDescripcion3.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtDescripcion3.setName("Descripción"); // NOI18N
        txtDescripcion3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDescripcion3KeyReleased(evt);
            }
        });

        lbCiudad12.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbCiudad12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCiudad12.setText("Valor");

        lbCiudad13.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbCiudad13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCiudad13.setText("Ubicación");

        txtL3.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtL3.setName("Lista 1"); // NOI18N
        txtL3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtL3KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtL3KeyTyped(evt);
            }
        });

        txtUbicacion3.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtUbicacion3.setName("Descripción"); // NOI18N
        txtUbicacion3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUbicacion3KeyReleased(evt);
            }
        });

        txtCantidad3.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtCantidad3.setName("Descripción"); // NOI18N
        txtCantidad3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantidad3ActionPerformed(evt);
            }
        });
        txtCantidad3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCantidad3KeyReleased(evt);
            }
        });

        plu3.setBackground(new java.awt.Color(255, 255, 255));
        plu3.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        plu3.setText("Activar");

        txtUtilidadL5.setEditable(false);
        txtUtilidadL5.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtUtilidadL5.setName("Descripción"); // NOI18N
        txtUtilidadL5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUtilidadL5KeyReleased(evt);
            }
        });

        txtUtilidadL4.setEditable(false);
        txtUtilidadL4.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtUtilidadL4.setName("Descripción"); // NOI18N
        txtUtilidadL4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUtilidadL4KeyReleased(evt);
            }
        });

        lbCiudad22.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbCiudad22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCiudad22.setText("Utilidad:");

        lbCiudad14.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbCiudad14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCiudad14.setText("Cant:");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtL3, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbCiudad12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCodigo3, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbCiudad10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbCiudad22, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(txtUtilidadL4, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtUtilidadL5, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lbCiudad14, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(txtCantidad3, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(plu3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(lbCiudad13, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUbicacion3)
                    .addComponent(txtDescripcion3)
                    .addComponent(lbCiudad11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCiudad10)
                    .addComponent(lbCiudad11))
                .addGap(2, 2, 2)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCodigo3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDescripcion3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCiudad12)
                    .addComponent(lbCiudad13))
                .addGap(2, 2, 2)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtL3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUbicacion3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(plu3)
                        .addComponent(txtCantidad3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbCiudad14))
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbCiudad22)
                        .addComponent(txtUtilidadL4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtUtilidadL5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(2, 2, 2))
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "L4", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Bell MT", 1, 14), new java.awt.Color(204, 0, 0))); // NOI18N

        lbCiudad15.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbCiudad15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCiudad15.setText("PLU-4");

        lbCiudad16.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbCiudad16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCiudad16.setText("Descripcion");

        txtCodigo4.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtCodigo4.setName("Codigo"); // NOI18N
        txtCodigo4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigo4KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigo4KeyTyped(evt);
            }
        });

        txtDescripcion4.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtDescripcion4.setName("Descripción"); // NOI18N
        txtDescripcion4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDescripcion4KeyReleased(evt);
            }
        });

        lbCiudad17.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbCiudad17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCiudad17.setText("Valor");

        lbCiudad18.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbCiudad18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCiudad18.setText("Ubicación");

        txtL4.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtL4.setName("Lista 1"); // NOI18N
        txtL4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtL4KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtL4KeyTyped(evt);
            }
        });

        txtUbicacion4.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtUbicacion4.setName("Descripción"); // NOI18N
        txtUbicacion4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUbicacion4KeyReleased(evt);
            }
        });

        lbCiudad19.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbCiudad19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCiudad19.setText("Cant:");

        txtCantidad4.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtCantidad4.setName("Descripción"); // NOI18N
        txtCantidad4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCantidad4KeyReleased(evt);
            }
        });

        plu4.setBackground(new java.awt.Color(255, 255, 255));
        plu4.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        plu4.setText("Activar");

        txtUtilidadL7.setEditable(false);
        txtUtilidadL7.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtUtilidadL7.setName("Descripción"); // NOI18N
        txtUtilidadL7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUtilidadL7KeyReleased(evt);
            }
        });

        txtUtilidadL6.setEditable(false);
        txtUtilidadL6.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtUtilidadL6.setName("Descripción"); // NOI18N
        txtUtilidadL6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUtilidadL6KeyReleased(evt);
            }
        });

        lbCiudad23.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbCiudad23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCiudad23.setText("Utilidad:");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtL4, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbCiudad17, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCodigo4, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbCiudad15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbCiudad23, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbCiudad16, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDescripcion4, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbCiudad18, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUbicacion4, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(txtUtilidadL6, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtUtilidadL7, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(lbCiudad19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCantidad4, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(plu4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCiudad15)
                    .addComponent(lbCiudad16))
                .addGap(2, 2, 2)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCodigo4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDescripcion4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCiudad17)
                    .addComponent(lbCiudad18))
                .addGap(2, 2, 2)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtL4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUbicacion4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCiudad19)
                    .addComponent(txtCantidad4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(plu4)
                    .addComponent(txtUtilidadL7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUtilidadL6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbCiudad23))
                .addGap(9, 9, 9))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Listas de precios", jPanel4);

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "L5", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Bell MT", 1, 14), new java.awt.Color(204, 0, 0))); // NOI18N

        lbCiudad24.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbCiudad24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCiudad24.setText("PLU-5");

        lbCiudad25.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbCiudad25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCiudad25.setText("Descripcion");

        txtCodigo5.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtCodigo5.setName("Codigo"); // NOI18N
        txtCodigo5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigo5KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigo5KeyTyped(evt);
            }
        });

        txtDescripcion5.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtDescripcion5.setName("Descripción"); // NOI18N
        txtDescripcion5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDescripcion5KeyReleased(evt);
            }
        });

        lbCiudad26.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbCiudad26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCiudad26.setText("Valor  *");

        lbCiudad27.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbCiudad27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCiudad27.setText("Ubicación");

        txtL5.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtL5.setName("Lista 1"); // NOI18N
        txtL5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtL5KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtL5KeyTyped(evt);
            }
        });

        txtUbicacion5.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtUbicacion5.setName("Descripción"); // NOI18N
        txtUbicacion5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUbicacion5KeyReleased(evt);
            }
        });

        lbCiudad28.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbCiudad28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCiudad28.setText("Utilidad:");

        txtUtilidadL8.setEditable(false);
        txtUtilidadL8.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtUtilidadL8.setName("Descripción"); // NOI18N
        txtUtilidadL8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUtilidadL8KeyReleased(evt);
            }
        });

        txtUtilidadL9.setEditable(false);
        txtUtilidadL9.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtUtilidadL9.setName("Descripción"); // NOI18N
        txtUtilidadL9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUtilidadL9KeyReleased(evt);
            }
        });

        lbCiudad47.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbCiudad47.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCiudad47.setText("Cant:");

        txtCantidad5.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtCantidad5.setName("Descripción"); // NOI18N

        plu5.setBackground(new java.awt.Color(255, 255, 255));
        plu5.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        plu5.setText("Activar");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbCiudad28, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                    .addComponent(txtL5)
                    .addComponent(lbCiudad26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCodigo5)
                    .addComponent(lbCiudad24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbCiudad25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbCiudad27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel11Layout.createSequentialGroup()
                                .addComponent(txtUtilidadL8, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtUtilidadL9, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(lbCiudad47)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCantidad5, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(plu5))
                            .addComponent(txtDescripcion5, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtUbicacion5, javax.swing.GroupLayout.Alignment.LEADING))
                        .addContainerGap())))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCiudad24)
                    .addComponent(lbCiudad25))
                .addGap(2, 2, 2)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCodigo5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDescripcion5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCiudad26)
                    .addComponent(lbCiudad27))
                .addGap(2, 2, 2)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtL5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUbicacion5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCiudad28)
                    .addComponent(txtUtilidadL8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUtilidadL9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(plu5)
                    .addComponent(txtCantidad5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbCiudad47)))
        );

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "L6", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Bell MT", 1, 14), new java.awt.Color(204, 0, 0))); // NOI18N

        lbCiudad29.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbCiudad29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCiudad29.setText("PLU-6");

        lbCiudad30.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbCiudad30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCiudad30.setText("Descripcion");

        txtCodigo6.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtCodigo6.setName("Codigo"); // NOI18N
        txtCodigo6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigo6KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigo6KeyTyped(evt);
            }
        });

        txtDescripcion6.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtDescripcion6.setName("Descripción"); // NOI18N
        txtDescripcion6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDescripcion6KeyReleased(evt);
            }
        });

        lbCiudad31.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbCiudad31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCiudad31.setText("Valor");

        lbCiudad32.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbCiudad32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCiudad32.setText("Ubicación");

        txtL6.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtL6.setName("Lista 1"); // NOI18N
        txtL6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtL6KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtL6KeyTyped(evt);
            }
        });

        txtUbicacion6.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtUbicacion6.setName("Descripción"); // NOI18N
        txtUbicacion6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUbicacion6KeyReleased(evt);
            }
        });

        lbCiudad33.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbCiudad33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCiudad33.setText("Cant:");

        txtCantidad6.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtCantidad6.setName("Descripción"); // NOI18N
        txtCantidad6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantidad6ActionPerformed(evt);
            }
        });
        txtCantidad6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCantidad6KeyReleased(evt);
            }
        });

        plu6.setBackground(new java.awt.Color(255, 255, 255));
        plu6.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        plu6.setText("Activar");

        lbCiudad34.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbCiudad34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCiudad34.setText("Utilidad:");

        txtUtilidadL10.setEditable(false);
        txtUtilidadL10.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtUtilidadL10.setName("Descripción"); // NOI18N
        txtUtilidadL10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUtilidadL10KeyReleased(evt);
            }
        });

        txtUtilidadL11.setEditable(false);
        txtUtilidadL11.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtUtilidadL11.setName("Descripción"); // NOI18N
        txtUtilidadL11.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUtilidadL11KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtL6, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbCiudad31, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCodigo6, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbCiudad29, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbCiudad34, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE))
                .addGap(21, 21, 21)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(txtUtilidadL10, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtUtilidadL11, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lbCiudad33)
                        .addGap(2, 2, 2)
                        .addComponent(txtCantidad6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(plu6))
                    .addComponent(txtUbicacion6)
                    .addComponent(lbCiudad30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbCiudad32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtDescripcion6))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCiudad29)
                    .addComponent(lbCiudad30))
                .addGap(2, 2, 2)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCodigo6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDescripcion6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCiudad31)
                    .addComponent(lbCiudad32))
                .addGap(2, 2, 2)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtL6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUbicacion6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbCiudad33)
                        .addComponent(txtCantidad6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(plu6)
                        .addComponent(txtUtilidadL11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtUtilidadL10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(lbCiudad34)
                        .addGap(1, 1, 1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "L7", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Bell MT", 1, 14), new java.awt.Color(204, 0, 0))); // NOI18N

        lbCiudad35.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbCiudad35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCiudad35.setText("PLU-7");

        lbCiudad36.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbCiudad36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCiudad36.setText("Descripcion");

        txtCodigo7.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtCodigo7.setName("Codigo"); // NOI18N
        txtCodigo7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigo7KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigo7KeyTyped(evt);
            }
        });

        txtDescripcion7.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtDescripcion7.setName("Descripción"); // NOI18N
        txtDescripcion7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDescripcion7KeyReleased(evt);
            }
        });

        lbCiudad37.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbCiudad37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCiudad37.setText("Valor");

        lbCiudad38.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbCiudad38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCiudad38.setText("Ubicación");

        txtL7.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtL7.setName("Lista 1"); // NOI18N
        txtL7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtL7KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtL7KeyTyped(evt);
            }
        });

        txtUbicacion7.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtUbicacion7.setName("Descripción"); // NOI18N
        txtUbicacion7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUbicacion7KeyReleased(evt);
            }
        });

        lbCiudad39.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbCiudad39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCiudad39.setText("Cant:");

        txtCantidad7.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtCantidad7.setName("Descripción"); // NOI18N
        txtCantidad7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCantidad7KeyReleased(evt);
            }
        });

        plu7.setBackground(new java.awt.Color(255, 255, 255));
        plu7.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        plu7.setText("Activar");

        txtUtilidadL13.setEditable(false);
        txtUtilidadL13.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtUtilidadL13.setName("Descripción"); // NOI18N
        txtUtilidadL13.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUtilidadL13KeyReleased(evt);
            }
        });

        txtUtilidadL12.setEditable(false);
        txtUtilidadL12.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtUtilidadL12.setName("Descripción"); // NOI18N
        txtUtilidadL12.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUtilidadL12KeyReleased(evt);
            }
        });

        lbCiudad40.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbCiudad40.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCiudad40.setText("Utilidad:");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtL7, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbCiudad37, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCodigo7, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbCiudad35, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbCiudad40, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE))
                .addGap(24, 24, 24)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(txtUtilidadL12, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtUtilidadL13, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lbCiudad39)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCantidad7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(plu7))
                    .addComponent(txtUbicacion7)
                    .addComponent(lbCiudad36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbCiudad38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtDescripcion7))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCiudad35)
                    .addComponent(lbCiudad36))
                .addGap(2, 2, 2)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCodigo7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDescripcion7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCiudad37)
                    .addComponent(lbCiudad38))
                .addGap(2, 2, 2)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtL7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUbicacion7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbCiudad39)
                        .addComponent(txtCantidad7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(plu7))
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbCiudad40)
                        .addComponent(txtUtilidadL12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtUtilidadL13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "L8", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Bell MT", 1, 14), new java.awt.Color(204, 0, 0))); // NOI18N

        lbCiudad41.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbCiudad41.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCiudad41.setText("PLU-8");

        lbCiudad42.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbCiudad42.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCiudad42.setText("Descripcion");

        txtCodigo8.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtCodigo8.setName("Codigo"); // NOI18N
        txtCodigo8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigo8KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigo8KeyTyped(evt);
            }
        });

        txtDescripcion8.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtDescripcion8.setName("Descripción"); // NOI18N
        txtDescripcion8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDescripcion8KeyReleased(evt);
            }
        });

        lbCiudad43.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbCiudad43.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCiudad43.setText("Valor");

        lbCiudad44.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbCiudad44.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCiudad44.setText("Ubicación");

        txtL8.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtL8.setName("Lista 1"); // NOI18N
        txtL8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtL8KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtL8KeyTyped(evt);
            }
        });

        txtUbicacion8.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtUbicacion8.setName("Descripción"); // NOI18N
        txtUbicacion8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUbicacion8KeyReleased(evt);
            }
        });

        lbCiudad45.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbCiudad45.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCiudad45.setText("Cant:");

        txtCantidad8.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtCantidad8.setName("Descripción"); // NOI18N
        txtCantidad8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantidad8ActionPerformed(evt);
            }
        });
        txtCantidad8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCantidad8KeyReleased(evt);
            }
        });

        plu8.setBackground(new java.awt.Color(255, 255, 255));
        plu8.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        plu8.setText("Activar");

        txtUtilidadL15.setEditable(false);
        txtUtilidadL15.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtUtilidadL15.setName("Descripción"); // NOI18N
        txtUtilidadL15.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUtilidadL15KeyReleased(evt);
            }
        });

        txtUtilidadL14.setEditable(false);
        txtUtilidadL14.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtUtilidadL14.setName("Descripción"); // NOI18N
        txtUtilidadL14.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUtilidadL14KeyReleased(evt);
            }
        });

        lbCiudad46.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbCiudad46.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCiudad46.setText("Utilidad:");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtL8, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbCiudad43, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCodigo8, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbCiudad41, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbCiudad46, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbCiudad42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addComponent(txtUtilidadL14, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtUtilidadL15, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addComponent(lbCiudad45)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCantidad8, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(plu8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(txtDescripcion8, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtUbicacion8)
                            .addComponent(lbCiudad44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 1, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCiudad41)
                    .addComponent(lbCiudad42))
                .addGap(2, 2, 2)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCodigo8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDescripcion8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCiudad43)
                    .addComponent(lbCiudad44))
                .addGap(2, 2, 2)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtL8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUbicacion8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCiudad45)
                    .addComponent(txtCantidad8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(plu8)
                    .addComponent(txtUtilidadL15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUtilidadL14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbCiudad46))
                .addGap(9, 9, 9))
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Listas de precios", jPanel10);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        tblProductosUnificados.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        tblProductosUnificados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "idSistema", "Codigo", "Descripcion"
            }
        ));
        tblProductosUnificados.setComponentPopupMenu(jPopupMenu1);
        tblProductosUnificados.setRowHeight(24);
        jScrollPane1.setViewportView(tblProductosUnificados);
        if (tblProductosUnificados.getColumnModel().getColumnCount() > 0) {
            tblProductosUnificados.getColumnModel().getColumn(1).setMinWidth(150);
            tblProductosUnificados.getColumnModel().getColumn(1).setPreferredWidth(200);
            tblProductosUnificados.getColumnModel().getColumn(1).setMaxWidth(300);
        }

        txtCodUnificar.setEditable(false);
        txtCodUnificar.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtCodUnificar.setName("Codigo"); // NOI18N
        txtCodUnificar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodUnificarKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodUnificarKeyTyped(evt);
            }
        });

        lbCiudad8.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbCiudad8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbCiudad8.setText("Codigo:");

        txtDescripcionUnificar.setEditable(false);
        txtDescripcionUnificar.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtDescripcionUnificar.setName("Descripción"); // NOI18N
        txtDescripcionUnificar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDescripcionUnificarKeyReleased(evt);
            }
        });

        btnUnificar.setBackground(new java.awt.Color(46, 204, 113));
        btnUnificar.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        btnUnificar.setText("AGREGAR");
        btnUnificar.setEnabled(false);
        btnUnificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUnificarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 688, Short.MAX_VALUE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(lbCiudad8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCodUnificar, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDescripcionUnificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUnificar, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbCiudad8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCodUnificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtDescripcionUnificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnUnificar)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 635, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );

        jTabbedPane1.addTab("Unificar", jPanel9);

        mensaje2.setBackground(new java.awt.Color(204, 204, 255));
        mensaje2.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        mensaje2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mensaje2.setText("PONDERADO DE LOS PRODUCTOS");
        mensaje2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        mensaje2.setOpaque(true);
        mensaje2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mensaje2MouseClicked(evt);
            }
        });

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));

        btnImagen.setBackground(new java.awt.Color(204, 204, 204));
        btnImagen.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnImagen.setForeground(new java.awt.Color(255, 255, 255));
        btnImagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/webcam.png"))); // NOI18N
        btnImagen.setText("AGREGAR IMAGEN    ");
        btnImagen.setToolTipText("");
        btnImagen.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnImagen.setEnabled(false);
        btnImagen.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnImagen.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImagenActionPerformed(evt);
            }
        });

        lbFoto.setBackground(new java.awt.Color(204, 204, 204));
        lbFoto.setToolTipText("Esta herramienta solo recibe formatos JPG y PNG. Al montar la imagen recibe el tamaño de 250x250.");
        lbFoto.setMaximumSize(new java.awt.Dimension(200, 200));
        lbFoto.setMinimumSize(new java.awt.Dimension(200, 200));
        lbFoto.setName(""); // NOI18N
        lbFoto.setOpaque(true);
        lbFoto.setPreferredSize(new java.awt.Dimension(200, 200));
        lbFoto.setRequestFocusEnabled(false);

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnImagen)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        mensaje3.setBackground(new java.awt.Color(204, 204, 255));
        mensaje3.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        mensaje3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mensaje3.setText("IMAGENES DE LOS PRODUCTOS");
        mensaje3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        mensaje3.setOpaque(true);
        mensaje3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mensaje3MouseClicked(evt);
            }
        });

        mensaje4.setBackground(new java.awt.Color(204, 204, 255));
        mensaje4.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        mensaje4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mensaje4.setText("IMPORTAR BASE DE EXCEL");
        mensaje4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        mensaje4.setOpaque(true);
        mensaje4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mensaje4MouseClicked(evt);
            }
        });

        txtIdSistema.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        txtIdSistema.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtIdSistema.setDisabledTextColor(new java.awt.Color(255, 51, 0));
        txtIdSistema.setEnabled(false);
        txtIdSistema.setName("Codigo"); // NOI18N
        txtIdSistema.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIdSistemaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIdSistemaKeyTyped(evt);
            }
        });

        txtProveedor.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        txtProveedor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtProveedor.setDisabledTextColor(new java.awt.Color(255, 51, 0));
        txtProveedor.setEnabled(false);
        txtProveedor.setName("Proveedor"); // NOI18N
        txtProveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtProveedorKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtProveedorKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout pnlInvisibleLayout = new javax.swing.GroupLayout(pnlInvisible);
        pnlInvisible.setLayout(pnlInvisibleLayout);
        pnlInvisibleLayout.setHorizontalGroup(
            pnlInvisibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInvisibleLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(txtIdSistema, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlInvisibleLayout.setVerticalGroup(
            pnlInvisibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlInvisibleLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlInvisibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIdSistema, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlFormularioLayout = new javax.swing.GroupLayout(pnlFormulario);
        pnlFormulario.setLayout(pnlFormularioLayout);
        pnlFormularioLayout.setHorizontalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 713, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(mensaje4, javax.swing.GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
                    .addComponent(mensaje2, javax.swing.GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
                    .addComponent(mensaje3, javax.swing.GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
                    .addComponent(pnlInvisible, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        pnlFormularioLayout.setVerticalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlFormularioLayout.createSequentialGroup()
                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mensaje3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(mensaje2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(mensaje4, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pnlInvisible, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 749, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("Productos");

        scrFormulario.setViewportView(pnlFormulario);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrFormulario)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrFormulario)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyTyped

    }//GEN-LAST:event_txtCodigoKeyTyped

    private void txtCodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            ndProducto nodo = instancias.getSql().getDatosProducto(txtCodigo.getText().replace("'", "//"), "bdProductos");
            if (nodo.getCodigo() == null) {
                txtDescripcion.requestFocus();
            } else {
                cargarProducto(txtCodigo.getText());
            }
        } else {
            txtCodigo1.setText(txtCodigo.getText() + "-1");
            txtCodigo2.setText(txtCodigo.getText() + "-2");
            txtCodigo3.setText(txtCodigo.getText() + "-3");
            txtCodigo4.setText(txtCodigo.getText() + "-4");
            txtCodigo5.setText(txtCodigo.getText() + "-5");
            txtCodigo6.setText(txtCodigo.getText() + "-6");
            txtCodigo7.setText(txtCodigo.getText() + "-7");
            txtCodigo8.setText(txtCodigo.getText() + "-8");
            txtCosto1.setText(this.simbolo + " 0");
        }
    }//GEN-LAST:event_txtCodigoKeyReleased

    private void txtL1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtL1KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtL2.requestFocus();
        } else {
            txtL1.setText(big.setMoneda(big.getMoneda(txtL1.getText())));
            txtL2.setText(txtL1.getText());
            txtL3.setText(txtL1.getText());
            txtL4.setText(txtL1.getText());
            txtL5.setText(txtL1.getText());
            txtL6.setText(txtL1.getText());
            txtL7.setText(txtL1.getText());
            txtL8.setText(txtL1.getText());
        }
        utilidad();
    }//GEN-LAST:event_txtL1KeyReleased

    private void txtDescripcionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtDescripcionLarga.requestFocus();
        } else {

            txtDescripcion1.setText(txtDescripcion.getText());
            txtDescripcion2.setText(txtDescripcion.getText());
            txtDescripcion3.setText(txtDescripcion.getText());
            txtDescripcion4.setText(txtDescripcion.getText());
            txtDescripcion5.setText(txtDescripcion.getText());
            txtDescripcion6.setText(txtDescripcion.getText());
            txtDescripcion7.setText(txtDescripcion.getText());
            txtDescripcion8.setText(txtDescripcion.getText());
        }
    }//GEN-LAST:event_txtDescripcionKeyReleased

    private void txtSubgrupoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSubgrupoKeyTyped

    }//GEN-LAST:event_txtSubgrupoKeyTyped

    private void txtSubgrupoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSubgrupoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                String grupo = instancias.getSql().getDatosGrupoPorNombre(txtGrupo.getSelectedItem().toString());
                instancias.setValor(grupo);
                ventanaSubGrupos(txtSubgrupo.getText());
            } catch (Exception e) {
                metodos.msgError(this, "No ha cargado un grupo");
            }
        } else {
            txtCodigoSubGrupo.setText("");
        }

    }//GEN-LAST:event_txtSubgrupoKeyReleased

    private void txtL1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtL1KeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtL1KeyTyped

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        txtCodigo.setEnabled(true);
        txtProveedor.setText("");
        btnImagen.setEnabled(false);
        btnRelacionados.setEnabled(false);
        lbFoto.setIcon(null);
        repaint();

        chkNinguno.setSelected(true);

        lbIvaCompra.setSelected(false);
        txtImpoconsumo.setEnabled(false);
        txtImpoconsumoCompra.setEnabled(false);
        txtIva.setEnabled(false);
        txtIvaCompra.setEnabled(false);

        rdInventarioSI.setSelected(true);

        limpiar(jPanel1);
        limpiar(jPanel3);
        limpiar(jPanel5);
        limpiar(jPanel6);
        limpiar(jPanel7);
        limpiar(jPanel8);
        limpiar(jPanel10);
        limpiar(jPanel17);
        limpiar(jPanel11);
        limpiar(jPanel12);
        limpiar(jPanel13);
        limpiar(jPanel14);

        while (true) {
            try {
                ((DefaultTableModel) tblProductosUnificados.getModel()).removeRow(0);
            } catch (Exception e) {
                break;
            }
        }

        txtGrupo.setSelectedIndex(0);
        cmbClaseBuscador.setSelectedIndex(0);
        txtMinima.setText("20");
        txtMaximo.setText("30");

        if (instancias.getConfiguraciones().isRestaurante()) {
            nodoConf = instancias.getSql().getDatosConfiguracion();
            txtImpoconsumoCompra.setText("0");
            txtImpoconsumo.setText("0");
            txtIvaCompra.setText("0");
            txtIva.setText("0");
        } else {
            txtIvaCompra.setText("0");
            txtIva.setText("0");
        }

        String consecutivo = "";
        int num = Integer.parseInt(instancias.getSql().getNumConsecutivo("PROD")[0].toString());
        consecutivo = String.valueOf(num);
        for (int i = 0; i < 8 - String.valueOf(num).length(); i++) {
            consecutivo = "0" + consecutivo;
        }

        txtDescripcionLarga.setText("");
        txtIdSistema.setText("PROD-" + consecutivo);
        txtCosto.setText(this.simbolo + " 0");
        btnActualizar.setEnabled(false);
        btnGuardar.setEnabled(true);
        txtCodUnificar.setEditable(false);
        btnUnificar.setEnabled(false);
        btnEliminar.setText("INACTIVAR");
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/borrar2.png")));
        jTabbedPane1.setSelectedIndex(0);
        lbNit.requestFocus();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        Object[] campos = null;
        if (instancias.getConfiguraciones().isFacturaElectronica()) {
            campos = new Object[]{txtDescripcion, txtL1, txtCosto, txtMedida};
        } else {
            campos = new Object[]{txtDescripcion, txtL1, txtCosto};
        }

        String faltantes = metodos.camposVacios(campos);

        Object[] campos2 = {txtSubgrupo, txtIva, txtProveedor, txtL2, txtL3, txtL4, txtL5, txtL6, txtL7, txtL8, txtReferencia};
        String faltantes2 = metodos.camposVacios(campos2);

        if (!faltantes.equals("")) {
            metodos.msgAdvertencia(this, "No puede continuar, faltan los siguientes campos: " + faltantes);
            return;
        } else if (!faltantes2.equals("")) {
            metodos.msgAdvertencia(this, "Faltan los siguientes campos: " + faltantes2);
        }

        if (plu2.isSelected() && txtCantidad2.getText().equals("")) {
            metodos.msgError(this, "No ha ingresado cantidad en el plu 2");
            return;
        } else if (plu3.isSelected() && txtCantidad3.getText().equals("")) {
            metodos.msgError(this, "No ha ingresado cantidad en el plu 3");
            return;
        } else if (plu4.isSelected() && txtCantidad4.getText().equals("")) {
            metodos.msgError(this, "No ha ingresado cantidad en el plu 4");
            return;
        } else if (plu5.isSelected() && txtCantidad5.getText().equals("")) {
            metodos.msgError(this, "No ha ingresado cantidad en el plu 5");
            return;
        } else if (plu6.isSelected() && txtCantidad6.getText().equals("")) {
            metodos.msgError(this, "No ha ingresado cantidad en el plu 6");
            return;
        } else if (plu7.isSelected() && txtCantidad7.getText().equals("")) {
            metodos.msgError(this, "No ha ingresado cantidad en el plu 7");
            return;
        } else if (plu8.isSelected() && txtCantidad8.getText().equals("")) {
            metodos.msgError(this, "No ha ingresado cantidad en el plu 8");
            return;
        }

        if (metodos.msgPregunta(this, "¿Desea continuar?") == 0) {

            if (txtDescripcion.getText().length() > 255) {
                metodos.msgAdvertenciaAjustado(this, "Descripción muy larga");
                return;
            }

//            try {
            try {
                if (txtReferencia.getText().equalsIgnoreCase(instancias.getSql().getRefenciaProducto(txtReferencia.getText(), txtIdSistema.getText()))) {
                    if (!txtReferencia.getText().equalsIgnoreCase("")) {
                        metodos.msgError(this, "Esta referencia ya existe");
                        return;
                    }
                }

            } catch (Exception e) {
            }

            if (lbIvaCompra.isSelected()) {
                if (txtIva.getText().equals("0") && txtIvaCompra.getText().equals("0")) {
                    metodos.msgAdvertenciaAjustado(this, "Debe ingresar el iva");
                    return;
                }

                int porcentajeIva = Integer.parseInt(txtIva.getText());
                if (!Constantes.TARIFAS_IVAS_PERMITIDOS.contains(porcentajeIva)) {
                    metodos.msgAdvertenciaAjustado(this, "IVA no válido. Permitidos: " + Constantes.TARIFAS_IVAS_PERMITIDOS.toString());
                    return;
                }
            }

            if (lbImpoCompra.isSelected()) {
                if (txtImpoconsumo.getText().equals("0") && txtImpoconsumoCompra.getText().equals("0")) {
                    metodos.msgAdvertenciaAjustado(this, "Debe ingresar el impoconsumo");
                    return;
                }

                int porcentajeImpoconsumo = Integer.parseInt(txtImpoconsumo.getText());
                if (!Constantes.TARIFAS_IMPOCONSUMO_PERMITIDOS.contains(porcentajeImpoconsumo)) {
                    metodos.msgAdvertenciaAjustado(this, "IMPOCONSUMO no válido. Permitidos: " + Constantes.TARIFAS_IMPOCONSUMO_PERMITIDOS.toString());
                    return;
                }
            }

            if (txtL2.getText().equals("")) {
                txtL2.setText("0");
            }
            if (txtL3.getText().equals("")) {
                txtL3.setText("0");
            }
            if (txtL4.getText().equals("")) {
                txtL4.setText("0");
            }
            if (txtL5.getText().equals("")) {
                txtL5.setText("0");
            }
            if (txtL6.getText().equals("")) {
                txtL6.setText("0");
            }
            if (txtL7.getText().equals("")) {
                txtL7.setText("0");
            }
            if (txtL8.getText().equals("")) {
                txtL8.setText("0");
            }
            if (txtIva.getText().equals("")) {
                txtIva.setText("0");
            }
            if (txtMinimo.getText().equals("")) {
                txtMinimo.setText("0");
            }

            int ivaVenta, ivaCompra, impoVenta, impoCompra;
            try {
                ivaVenta = Integer.parseInt(txtIva.getText());
            } catch (Exception e) {
                metodos.msgAdvertenciaAjustado(this, "El IVA debe ser un número entero");
                return;
            }
            try {
                ivaCompra = Integer.parseInt(txtIvaCompra.getText());
            } catch (Exception e) {
                metodos.msgAdvertenciaAjustado(this, "El IVA debe ser un número entero");
                return;
            }
            try {
                impoVenta = Integer.parseInt(txtImpoconsumo.getText());
            } catch (Exception e) {
                metodos.msgAdvertenciaAjustado(this, "El IMPO debe ser un número entero");
                return;
            }
            try {
                impoCompra = Integer.parseInt(txtImpoconsumoCompra.getText());
            } catch (Exception e) {
                metodos.msgAdvertenciaAjustado(this, "El IMPO debe ser un número entero");
                return;
            }

            String grupo = "";
            if (txtGrupo.getSelectedIndex() > 0) {
                grupo = grupos[txtGrupo.getSelectedIndex() - 1][0].toString();
            }

            ndProducto producto = instancias.getSql().getDatosProducto(txtIdSistema.getText(), "bdProductos");

            ndProducto producto1 = null, producto2 = null, producto3 = null, producto4 = null;
            if (instancias.getConfiguraciones().isInventarioBodegas()) {
                producto1 = instancias.getSql().getDatosProducto(txtIdSistema.getText(), "bdProductosBodega1");
                producto2 = instancias.getSql().getDatosProducto(txtIdSistema.getText(), "bdProductosBodega2");
                producto3 = instancias.getSql().getDatosProducto(txtIdSistema.getText(), "bdProductosBodega3");
                producto4 = instancias.getSql().getDatosProducto(txtIdSistema.getText(), "bdProductosBodega4");
            }

            String tipo = producto.getUsuario();
            String tipo1 = "";

            if (chkImei.isSelected()) {
                tipo1 = "IMEI";
            } else if (chkLote.isSelected()) {
                tipo1 = "Fecha/Lote";
            } else if (chkColor.isSelected()) {
                tipo1 = "Color";
            } else if (chkSerial.isSelected()) {
                tipo1 = "Serial";
            } else if (chkTalla.isSelected()) {
                tipo1 = "Talla";
            } else if (chkColorTalla.isSelected()) {
                tipo1 = "ColorTalla";
            } else if (chkSerialColor.isSelected()) {
                tipo1 = "SerialColor";
            }

            llenarNodoProducto(producto, grupo, tipo, tipo1);
            if (!instancias.getSql().modificarProducto(nodoProducto, "bdProductos")) {
                metodos.msgError(this, "Error al modificar el producto");
                return;
            }

            if (instancias.getConfiguraciones().isInventarioBodegas()) {
                llenarNodoProducto(producto1, grupo, tipo, tipo1);
                if (!instancias.getSql().modificarProducto(nodoProducto, "bdProductosBodega1")) {
                    metodos.msgError(this, "Error al modificar el producto");
                }

                llenarNodoProducto(producto2, grupo, tipo, tipo1);
                if (!instancias.getSql().modificarProducto(nodoProducto, "bdProductosBodega2")) {
                    metodos.msgError(this, "Error al modificar el producto");
                }

                llenarNodoProducto(producto3, grupo, tipo, tipo1);
                if (!instancias.getSql().modificarProducto(nodoProducto, "bdProductosBodega3")) {
                    metodos.msgError(this, "Error al modificar el producto");
                }

                llenarNodoProducto(producto4, grupo, tipo, tipo1);
                if (!instancias.getSql().modificarProducto(nodoProducto, "bdProductosBodega4")) {
                    metodos.msgError(this, "Error al modificar el producto");
                }
            }

            metodos.msgExito(this, "Producto modificado con éxito");
            btnNuevoActionPerformed(evt);
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        if (btnEliminar.getText().equalsIgnoreCase("ACTIVAR")) {
            if (metodos.msgPregunta(this, "¿Activar este registro?") == 0) {
                instancias.getSql().activarProducto(txtCodigo.getText());
                metodos.msgExito(this, "Producto activado con éxito");
                btnNuevoActionPerformed(evt);
                return;
            }
        }

        if (metodos.msgPregunta(this, "¿Inactivar este registro?") == 0) {

            Object[][] congeladas = instancias.getSql().getDatosCongelada();
            if (congeladas.length > 0) {
                for (int i = 0; i < congeladas.length; i++) {
                    if (congeladas[i][7].toString().equals(txtIdSistema.getText())) {
                        if (instancias.getConfiguraciones().isRestaurante()) {
                            metodos.msgAdvertencia(this, "¡El producto no se puede inactivar porque esta cargado en una mesa!");
                            return;
                        } else {
                            metodos.msgAdvertencia(this, "¡El producto no se puede inactivar porque esta cargado en una congelada!");
                            return;
                        }
                    }
                }
            }

            instancias.getSql().eliminarProducto(txtCodigo.getText());
            metodos.msgExito(this, "Producto inactivado con éxito");
            btnNuevoActionPerformed(evt);
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnBusProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBusProdActionPerformed
        jTabbedPane1.setSelectedIndex(0);
        ventanaProductos("");
    }//GEN-LAST:event_btnBusProdActionPerformed

    private void txtMinimoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMinimoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMinimoKeyReleased

    private void txtMinimoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMinimoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMinimoKeyTyped

    private void txtReferenciaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtReferenciaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtReferenciaKeyReleased

    private void txtReferenciaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtReferenciaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtReferenciaKeyTyped

    private void txtCostoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCostoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCostoActionPerformed

    private void txtCostoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCostoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtMinima.requestFocus();
        } else {

            if (txtCosto.getText().equals("") || txtCosto.getText().equals(this.simbolo) || txtCosto.getText().equals(this.simbolo + " ")) {
                txtCosto.setText("0");
            }

            txtCosto.setText(big.setMoneda(big.getMoneda(txtCosto.getText())));

            if (txtIvaCompra.getText().equals("")) {
                txtIvaCompra.setText("0");
            }
            utilidad();
        }
    }//GEN-LAST:event_txtCostoKeyReleased

    private void txtUtilidad1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUtilidad1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUtilidad1KeyReleased

    private void txtUtilidad1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUtilidad1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUtilidad1KeyTyped

    private void txtUtilidad2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUtilidad2KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUtilidad2KeyReleased

    private void txtUtilidad2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUtilidad2KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUtilidad2KeyTyped

    private void txtMinimaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMinimaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMinimaActionPerformed

    private void txtMinimaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMinimaKeyReleased
        if (txtMinima.getText().equals("")) {
            txtMinima.setText("0");
        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtMaximo.requestFocus();
        } else {
            utilidad();
        }
    }//GEN-LAST:event_txtMinimaKeyReleased

    private void txtMinimaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMinimaKeyPressed

    }//GEN-LAST:event_txtMinimaKeyPressed

    private void txtNombreProveedorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreProveedorKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cargarProveedor(txtNombreProveedor.getText());
        } else {
            txtProveedor.setText("");
        }
    }//GEN-LAST:event_txtNombreProveedorKeyReleased

    private void txtCodigoSubGrupoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoSubGrupoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoSubGrupoActionPerformed

    private void txtCodigoSubGrupoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoSubGrupoKeyReleased
        cargarSubGrupo(txtCodigoSubGrupo.getText());
    }//GEN-LAST:event_txtCodigoSubGrupoKeyReleased

    private void txtGrupoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_txtGrupoItemStateChanged
        txtCodigoSubGrupo.setText("");
        txtSubgrupo.setText("");
    }//GEN-LAST:event_txtGrupoItemStateChanged

    private void txtIvaCompraKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIvaCompraKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtIva.requestFocus();
        } else {
            txtIva.setText(txtIvaCompra.getText());
        }
        utilidad();
    }//GEN-LAST:event_txtIvaCompraKeyReleased

    private void txtIvaCompraKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIvaCompraKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtIvaCompraKeyTyped

    private void txtCosto1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCosto1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCosto1ActionPerformed

    private void txtCosto1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCosto1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCosto1KeyReleased

    private void txtMaximoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaximoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaximoActionPerformed

    private void txtMaximoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaximoKeyPressed

    }//GEN-LAST:event_txtMaximoKeyPressed

    private void txtMaximoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaximoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtMedida.requestFocus();
        } else {
            utilidad();
        }
    }//GEN-LAST:event_txtMaximoKeyReleased

    private void txtCodigo1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigo1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigo1KeyReleased

    private void txtCodigo1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigo1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigo1KeyTyped

    private void txtDescripcion1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcion1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescripcion1KeyReleased

    private void txtUbicacionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUbicacionKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUbicacionKeyReleased

    private void txtCodigo2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigo2KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigo2KeyReleased

    private void txtCodigo2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigo2KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigo2KeyTyped

    private void txtDescripcion2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcion2KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescripcion2KeyReleased

    private void txtL2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtL2KeyReleased
        txtL2.setText(big.setMoneda(big.getMoneda(txtL2.getText())));
        utilidad();
    }//GEN-LAST:event_txtL2KeyReleased

    private void txtL2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtL2KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtL2KeyTyped

    private void txtUbicacion2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUbicacion2KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUbicacion2KeyReleased

    private void txtCantidad2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidad2KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidad2KeyReleased

    private void txtCodigo3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigo3KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigo3KeyReleased

    private void txtCodigo3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigo3KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigo3KeyTyped

    private void txtDescripcion3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcion3KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescripcion3KeyReleased

    private void txtL3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtL3KeyReleased
        txtL3.setText(big.setMoneda(big.getMoneda(txtL3.getText())));
        utilidad();
    }//GEN-LAST:event_txtL3KeyReleased

    private void txtL3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtL3KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtL3KeyTyped

    private void txtUbicacion3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUbicacion3KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUbicacion3KeyReleased

    private void txtCantidad3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidad3KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidad3KeyReleased

    private void txtCodigo4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigo4KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigo4KeyReleased

    private void txtCodigo4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigo4KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigo4KeyTyped

    private void txtDescripcion4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcion4KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescripcion4KeyReleased

    private void txtL4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtL4KeyReleased
        txtL4.setText(big.setMoneda(big.getMoneda(txtL4.getText())));
        utilidad();
    }//GEN-LAST:event_txtL4KeyReleased

    private void txtL4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtL4KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtL4KeyTyped

    private void txtUbicacion4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUbicacion4KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUbicacion4KeyReleased

    private void txtCantidad4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidad4KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidad4KeyReleased

    private void txtIvaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIvaKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtIvaKeyTyped

    private void txtIvaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIvaKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            utilidad();
            txtMinima.requestFocus();
        } else {
            txtIva.setText(big.setNumero(big.getBigDecimal(txtIva.getText())));
        }
    }//GEN-LAST:event_txtIvaKeyReleased

    private void txtUtilidad1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUtilidad1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUtilidad1ActionPerformed

    private void txtCodUnificarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodUnificarKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            String codigo = txtCodUnificar.getText().replace("'", "//");
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
                txtDescripcionUnificar.setText(nodo.getDescripcion());
                btnUnificarActionPerformed(null);

            } else {
                ventanaProductosUnificar(txtCodUnificar.getText().replace("'", "//"));
            }
        }
    }//GEN-LAST:event_txtCodUnificarKeyReleased

    private void txtCodUnificarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodUnificarKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodUnificarKeyTyped

    private void txtDescripcionUnificarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionUnificarKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescripcionUnificarKeyReleased

    private void popBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popBorrarActionPerformed
        if (tblProductosUnificados.getSelectedRow() > -1) {
            int fila = tblProductosUnificados.getSelectedRow();
            String codigo = (String) tblProductosUnificados.getValueAt(fila, 0);

            instancias.getSql().eliminarProductoUnificado(txtIdSistema.getText(), codigo);

            DefaultTableModel modelo = (DefaultTableModel) tblProductosUnificados.getModel();
            modelo.removeRow(fila);
        } else {
            metodos.msgAdvertencia(this, "Seleccione un producto");
        }
    }//GEN-LAST:event_popBorrarActionPerformed

    private void txtUtilidadLKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUtilidadLKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUtilidadLKeyReleased

    private void txtUtilidadL1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUtilidadL1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUtilidadL1KeyReleased

    private void txtUtilidadL2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUtilidadL2KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUtilidadL2KeyReleased

    private void txtUtilidadL3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUtilidadL3KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUtilidadL3KeyReleased

    private void txtUtilidadL5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUtilidadL5KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUtilidadL5KeyReleased

    private void txtUtilidadL4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUtilidadL4KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUtilidadL4KeyReleased

    private void txtUtilidadL7KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUtilidadL7KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUtilidadL7KeyReleased

    private void txtUtilidadL6KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUtilidadL6KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUtilidadL6KeyReleased

    private void txtIvaCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIvaCompraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIvaCompraActionPerformed

    private void cmbClaseBuscadorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbClaseBuscadorItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbClaseBuscadorItemStateChanged

    private void txtCodigo5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigo5KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigo5KeyReleased

    private void txtCodigo5KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigo5KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigo5KeyTyped

    private void txtDescripcion5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcion5KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescripcion5KeyReleased

    private void txtL5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtL5KeyReleased
        txtL5.setText(big.setMoneda(big.getMoneda(txtL5.getText())));
        utilidad();
    }//GEN-LAST:event_txtL5KeyReleased

    private void txtL5KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtL5KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtL5KeyTyped

    private void txtUbicacion5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUbicacion5KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUbicacion5KeyReleased

    private void txtUtilidadL8KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUtilidadL8KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUtilidadL8KeyReleased

    private void txtUtilidadL9KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUtilidadL9KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUtilidadL9KeyReleased

    private void txtCodigo6KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigo6KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigo6KeyReleased

    private void txtCodigo6KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigo6KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigo6KeyTyped

    private void txtDescripcion6KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcion6KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescripcion6KeyReleased

    private void txtL6KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtL6KeyReleased
        txtL6.setText(big.setMoneda(big.getMoneda(txtL6.getText())));
        utilidad();
    }//GEN-LAST:event_txtL6KeyReleased

    private void txtL6KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtL6KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtL6KeyTyped

    private void txtUbicacion6KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUbicacion6KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUbicacion6KeyReleased

    private void txtCantidad6KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidad6KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidad6KeyReleased

    private void txtUtilidadL10KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUtilidadL10KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUtilidadL10KeyReleased

    private void txtUtilidadL11KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUtilidadL11KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUtilidadL11KeyReleased

    private void txtCodigo7KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigo7KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigo7KeyReleased

    private void txtCodigo7KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigo7KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigo7KeyTyped

    private void txtDescripcion7KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcion7KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescripcion7KeyReleased

    private void txtL7KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtL7KeyReleased
        txtL7.setText(big.setMoneda(big.getMoneda(txtL7.getText())));
        utilidad();
    }//GEN-LAST:event_txtL7KeyReleased

    private void txtL7KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtL7KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtL7KeyTyped

    private void txtUbicacion7KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUbicacion7KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUbicacion7KeyReleased

    private void txtCantidad7KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidad7KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidad7KeyReleased

    private void txtUtilidadL13KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUtilidadL13KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUtilidadL13KeyReleased

    private void txtUtilidadL12KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUtilidadL12KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUtilidadL12KeyReleased

    private void txtCodigo8KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigo8KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigo8KeyReleased

    private void txtCodigo8KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigo8KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigo8KeyTyped

    private void txtDescripcion8KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcion8KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescripcion8KeyReleased

    private void txtL8KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtL8KeyReleased
        txtL8.setText(big.setMoneda(big.getMoneda(txtL8.getText())));
        utilidad();
    }//GEN-LAST:event_txtL8KeyReleased

    private void txtL8KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtL8KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtL8KeyTyped

    private void txtUbicacion8KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUbicacion8KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUbicacion8KeyReleased

    private void txtCantidad8KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidad8KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidad8KeyReleased

    private void txtUtilidadL15KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUtilidadL15KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUtilidadL15KeyReleased

    private void txtUtilidadL14KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUtilidadL14KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUtilidadL14KeyReleased

    private void txtCantidad6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantidad6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidad6ActionPerformed

    private void txtPorcentajeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPorcentajeKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPorcentajeKeyReleased

    private void txtPorcentajeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPorcentajeKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPorcentajeKeyTyped

    private void txtPorcentajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPorcentajeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPorcentajeActionPerformed

    private void txtGrupoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGrupoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGrupoActionPerformed

    private void txtCantidad3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantidad3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidad3ActionPerformed

    private void txtCantidad8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantidad8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidad8ActionPerformed

    private void txtImpoconsumoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtImpoconsumoKeyReleased
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtImpoconsumoKeyReleased

    private void txtImpoconsumoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtImpoconsumoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtImpoconsumoKeyTyped

    private void mensaje2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mensaje2MouseClicked
        dlgPonderadoNegativo ponderadoNegativo = new dlgPonderadoNegativo(null, true, null);
        ponderadoNegativo.setVisible(true);
    }//GEN-LAST:event_mensaje2MouseClicked

    private void txtDescripcionInglesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionInglesKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescripcionInglesKeyReleased

    private void lbTelefono1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbTelefono1MouseClicked

    }//GEN-LAST:event_lbTelefono1MouseClicked

    private void btnImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImagenActionPerformed
        JFileChooser chooser = new JFileChooser();
        ImagePreviewPanel preview = new ImagePreviewPanel();
        chooser.setAccessory(preview);
        chooser.addPropertyChangeListener(preview);

        FileNameExtensionFilter filtroImagen = new FileNameExtensionFilter("JPG, PNG & GIF", "jpg", "png", "gif");
        chooser.setFileFilter(filtroImagen);

        int respuesta = chooser.showSaveDialog(this);

        if (respuesta == JFileChooser.APPROVE_OPTION) {
            Path FROM = Paths.get(chooser.getSelectedFile().getAbsolutePath());

            String id = "";

            if (lbFoto.getToolTipText().equalsIgnoreCase("")) {
                id = Arrays.toString(instancias.getSql().getNumConsecutivo("IMG"));
            } else {
                id = lbFoto.getToolTipText();
            }

            Path TO = Paths.get(System.getProperty("user.dir") + "\\imagenes\\productos\\" + txtIdSistema.getText() + ".jpg");

            //sobreescribir el fichero de destino, si existe, y copiar
            // los atributos, incluyendo los permisos rwx
            CopyOption[] options = new CopyOption[]{
                StandardCopyOption.REPLACE_EXISTING,
                StandardCopyOption.COPY_ATTRIBUTES
            };

            try {
                metodos.montarImagenTerceros(FROM.toString(), TO.toString());
                ImageIcon fot = new ImageIcon(chooser.getSelectedFile().getAbsolutePath());
                Icon icono = new ImageIcon(fot.getImage().getScaledInstance(lbFoto.getWidth(), lbFoto.getHeight(), Image.SCALE_DEFAULT));
                lbFoto.setIcon(icono);
                this.repaint();
            } catch (Exception ex) {
                metodos.msgError(this, "Hubo un error al cargar el archivo");
            }
        }
    }//GEN-LAST:event_btnImagenActionPerformed

    private void mensaje3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mensaje3MouseClicked
        dlgFotos fotos = new dlgFotos(null, true, null, "");
        fotos.setLocationRelativeTo(null);
        fotos.setVisible(true);
    }//GEN-LAST:event_mensaje3MouseClicked

    private void chkNingunoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkNingunoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkNingunoActionPerformed

    private void chkImeiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkImeiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkImeiActionPerformed

    private void chkLoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkLoteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkLoteActionPerformed

    private void mensaje4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mensaje4MouseClicked
        dlgCreacionProductos creacionProd = new dlgCreacionProductos(null, true, null);
        creacionProd.setVisible(true);
    }//GEN-LAST:event_mensaje4MouseClicked

    private void txtIdSistemaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdSistemaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdSistemaKeyReleased

    private void txtIdSistemaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdSistemaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdSistemaKeyTyped

    private void txtCodigoBarrasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoBarrasKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            ndProducto nodo = instancias.getSql().getDatosProducto(txtCodigoBarras.getText().replace("'", "//"), "bdProductos");
            if (nodo.getCodigo() == null) {
                txtDescripcion.requestFocus();
            } else {
                cargarProducto(txtCodigoBarras.getText());
            }
        } else {
            txtCodigo1.setText(txtCodigoBarras.getText() + "-1");
            txtCodigo2.setText(txtCodigoBarras.getText() + "-2");
            txtCodigo3.setText(txtCodigoBarras.getText() + "-3");
            txtCodigo4.setText(txtCodigoBarras.getText() + "-4");
            txtCodigo5.setText(txtCodigoBarras.getText() + "-5");
            txtCodigo6.setText(txtCodigoBarras.getText() + "-6");
            txtCodigo7.setText(txtCodigoBarras.getText() + "-7");
            txtCodigo8.setText(txtCodigoBarras.getText() + "-8");
            txtCosto1.setText(this.simbolo + " 0");
        }
    }//GEN-LAST:event_txtCodigoBarrasKeyReleased

    private void txtCodigoBarrasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoBarrasKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoBarrasKeyTyped

    private void lbCupo7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbCupo7MouseClicked

    }//GEN-LAST:event_lbCupo7MouseClicked

    private void txtMedidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMedidaActionPerformed

    }//GEN-LAST:event_txtMedidaActionPerformed

    private void txtMedidaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMedidaKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String medida = txtMedida.getText();
            if (!txtMedida.getText().equals("")) {
                btnNuevo.requestFocus();
            } else {
                ventanaMedidas(txtMedida.getText());
            }
        } else {
            txtMedida.setText("");
        }
    }//GEN-LAST:event_txtMedidaKeyReleased

    private void txtMedidaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMedidaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMedidaKeyTyped

    private void chkColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkColorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkColorActionPerformed

    private void txtTipoProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTipoProdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTipoProdActionPerformed

    private void txtTipoProdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTipoProdKeyPressed

    }//GEN-LAST:event_txtTipoProdKeyPressed

    private void txtMarcaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMarcaKeyPressed

    }//GEN-LAST:event_txtMarcaKeyPressed

    private void txtTipoProdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTipoProdKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!txtTipoProd.getText().equals("")) {
                btnNuevo.requestFocus();
            } else {
                ventanaTipoProd(txtTipoProd.getText());
            }
        } else {
            txtTipoProd.setText("");
        }
    }//GEN-LAST:event_txtTipoProdKeyReleased

    private void txtMarcaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMarcaKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!txtMarca.getText().equals("")) {
                btnNuevo.requestFocus();
            } else {
                ventanaMarcas(txtMarca.getText());
            }
        } else {
            txtMarca.setText("");
        }
    }//GEN-LAST:event_txtMarcaKeyReleased

    private void txtPesoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesoKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtPesoKeyTyped

    private void chkSerialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSerialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkSerialActionPerformed

    private void lbNitKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lbNitKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtCodigo.requestFocus();
        }
    }//GEN-LAST:event_lbNitKeyReleased

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        String cod = txtCodigo.getText();
        String codBarras = txtCodigoBarras.getText();

        if (cod.equals("") && codBarras.equals("")) {
            metodos.msgError(this, "Debe ingresar el codigo del producto");
            return;
        }

        Object[] campos = null;
        if (instancias.getConfiguraciones().isFacturaElectronica()) {
            campos = new Object[]{txtDescripcion, txtL1, txtMedida};
        } else {
            campos = new Object[]{txtDescripcion, txtL1};
        }

        String faltantes = metodos.camposVacios(campos);

        Object[] campos2 = {txtIva, txtIvaCompra, txtL2, txtProveedor, txtMinimo};
        String faltantes2 = metodos.camposVacios(campos2);

        if (!faltantes.equals("")) {
            metodos.msgAdvertencia(this, "No puede continuar, faltan los siguientes campos: " + faltantes);
            return;
        } else if (!faltantes2.equals("")) {
            metodos.msgAdvertencia(this, "Faltan los siguientes campos: " + faltantes2);
        }

        if (plu2.isSelected() && txtCantidad2.getText().equals("")) {
            metodos.msgError(this, "No ha ingresado cantidad en el plu 2");
            return;
        } else if (plu3.isSelected() && txtCantidad3.getText().equals("")) {
            metodos.msgError(this, "No ha ingresado cantidad en el plu 3");
            return;
        } else if (plu4.isSelected() && txtCantidad4.getText().equals("")) {
            metodos.msgError(this, "No ha ingresado cantidad en el plu 4");
            return;
        } else if (plu5.isSelected() && txtCantidad5.getText().equals("")) {
            metodos.msgError(this, "No ha ingresado cantidad en el plu 5");
            return;
        } else if (plu6.isSelected() && txtCantidad6.getText().equals("")) {
            metodos.msgError(this, "No ha ingresado cantidad en el plu 6");
            return;
        } else if (plu7.isSelected() && txtCantidad7.getText().equals("")) {
            metodos.msgError(this, "No ha ingresado cantidad en el plu 7");
            return;
        } else if (plu8.isSelected() && txtCantidad8.getText().equals("")) {
            metodos.msgError(this, "No ha ingresado cantidad en el plu 8");
            return;
        }

        if (metodos.msgPregunta(this, "¿Desea continuar?") == 0) {

            if (txtDescripcion.getText().length() > 255) {
                metodos.msgAdvertenciaAjustado(this, "Descripción muy larga");
                return;
            }

            //try {
            String codigo = "";
            if (txtCodigo.getText().equals("")) {
                codigo = txtCodigoBarras.getText();
            } else {
                codigo = txtCodigo.getText();
            }

            ndProducto nodo = instancias.getSql().getDatosProducto(codigo, "bdProductos");
            ndProducto nodo1 = instancias.getSql().getDatosProducto(txtCodigo2.getText(), "bdProductos");

            try {
                if (txtReferencia.getText().equalsIgnoreCase(instancias.getSql().getRefenciaProducto(txtReferencia.getText()))) {
                    if (!txtReferencia.getText().equalsIgnoreCase("")) {
                        metodos.msgError(this, "Esta referencia ya existe");
                        return;
                    }
                }
            } catch (Exception e) {
            }

            if (nodo1.getIdSistema() != null) {
                metodos.msgAdvertenciaAjustado(this, "El codigo del PLU ya existe");
                txtCodigo.requestFocus();
                return;
            }

            if (nodo.getIdSistema() != null) {
                metodos.msgAdvertenciaAjustado(this, "El codigo ya existe");
                txtCodigo.requestFocus();
                return;
            }

            if (nodo.getCodigo() != null) {
                metodos.msgAdvertenciaAjustado(this, "El codigo ya existe");
                txtCodigo.requestFocus();
                return;
            }

//            if (nodo.getCodigoBarras() != null) {
//                metodos.msgError(this, "El codigo de barras ya existe");
//                txtCodigoBarras.requestFocus();
//                return;
//            }
            if (txtIva.getText().equals("")) {
                txtIva.setText("0");
            }

            if (txtIvaCompra.getText().equals("")) {
                txtIvaCompra.setText("0");
            }

            if (txtImpoconsumo.getText().equals("")) {
                txtImpoconsumo.setText("0");
            }

            if (txtImpoconsumoCompra.getText().equals("")) {
                txtImpoconsumoCompra.setText("0");
            }

            if (txtIvaCompra.getText().equals("")) {
                utilidad();
            }

            if (lbIvaCompra.isSelected()) {
                if (txtIva.getText().equals("0") && txtIvaCompra.getText().equals("0")) {
                    metodos.msgAdvertenciaAjustado(this, "Debe ingresar el iva");
                    return;
                }

                int porcentajeIva = Integer.parseInt(txtIva.getText());
                if (!Constantes.TARIFAS_IVAS_PERMITIDOS.contains(porcentajeIva)) {
                    metodos.msgAdvertenciaAjustado(this, "IVA no válido. Permitidos: " + Constantes.TARIFAS_IVAS_PERMITIDOS.toString());
                    return;
                }
            }

            if (lbImpoCompra.isSelected()) {
                if (txtImpoconsumo.getText().equals("0") && txtImpoconsumoCompra.getText().equals("0")) {
                    metodos.msgAdvertenciaAjustado(this, "Debe ingresar el impoconsumo");
                    return;
                }

                int porcentajeImpoconsumo = Integer.parseInt(txtImpoconsumo.getText());
                if (!Constantes.TARIFAS_IMPOCONSUMO_PERMITIDOS.contains(porcentajeImpoconsumo)) {
                    metodos.msgAdvertenciaAjustado(this, "IMPOCONSUMO no válido. Permitidos: " + Constantes.TARIFAS_IMPOCONSUMO_PERMITIDOS.toString());
                    return;
                }
            }

            if (txtL2.getText().equals("")) {
                txtL2.setText("0");
            }
            if (txtL3.getText().equals("")) {
                txtL3.setText("0");
            }
            if (txtL4.getText().equals("")) {
                txtL4.setText("0");
            }
            if (txtL5.getText().equals("")) {
                txtL5.setText("0");
            }
            if (txtL6.getText().equals("")) {
                txtL6.setText("0");
            }
            if (txtL7.getText().equals("")) {
                txtL7.setText("0");
            }
            if (txtL8.getText().equals("")) {
                txtL8.setText("0");
            }

            if (txtIva.getText().equals("")) {
                txtIva.setText("0");
            }

            int ivaVenta, ivaCompra, impoVenta, impoCompra;
            try {
                ivaVenta = Integer.parseInt(txtIva.getText());
            } catch (Exception e) {
                metodos.msgAdvertenciaAjustado(this, "El IVA debe ser un número entero");
                return;
            }
            try {
                ivaCompra = Integer.parseInt(txtIvaCompra.getText());
            } catch (Exception e) {
                metodos.msgAdvertenciaAjustado(this, "El IVA debe ser un número entero");
                return;
            }
            try {
                impoVenta = Integer.parseInt(txtImpoconsumo.getText());
            } catch (Exception e) {
                metodos.msgAdvertenciaAjustado(this, "El IMPO debe ser un número entero");
                return;
            }
            try {
                impoCompra = Integer.parseInt(txtImpoconsumoCompra.getText());
            } catch (Exception e) {
                metodos.msgAdvertenciaAjustado(this, "El IMPO debe ser un número entero");
                return;
            }

            BigDecimal costoProducto = BigDecimal.ZERO;
            try {
                costoProducto = big.getMoneda(txtCosto.getText());
            } catch (Exception e) {
                metodos.msgAdvertenciaAjustado(this, "Costo con formato invalidó");
                return;
            }

            if (txtMinimo.getText().equals("")) {
                txtMinimo.setText("0");
            }

            String grupo = "";

            if (txtGrupo.getSelectedIndex() > 0) {
                grupo = grupos[txtGrupo.getSelectedIndex() - 1][0].toString();
            }

            String tipo = "";
            if (chkImei.isSelected()) {
                tipo = "IMEI";
            } else if (chkLote.isSelected()) {
                tipo = "Fecha/Lote";
            } else if (chkColor.isSelected()) {
                tipo = "Color";
            } else if (chkSerial.isSelected()) {
                tipo = "Serial";
            } else if (chkTalla.isSelected()) {
                tipo = "Talla";
            } else if (chkColorTalla.isSelected()) {
                tipo = "ColorTalla";
            } else if (chkSerialColor.isSelected()) {
                tipo = "SerialColor";
            }

            String consecutivo = "";
            int num = Integer.parseInt(instancias.getSql().getNumConsecutivo("PROD")[0].toString());
            consecutivo = String.valueOf(num);
            for (int i = 0; i < 8 - String.valueOf(num).length(); i++) {
                consecutivo = "0" + consecutivo;
            }

            if ("".equals(grupo)) {
                grupo = null;
            }

            String subgrupo = txtCodigoSubGrupo.getText();
            if ("".equals(subgrupo)) {
                subgrupo = null;
            }

            String proveedor = txtProveedor.getText();
            if ("".equals(proveedor)) {
                proveedor = null;
            }

            Object[] vector = {"PROD-" + consecutivo, txtCodigo.getText().replace("'", "//"), txtCodigoBarras.getText(), txtDescripcion.getText(),
                grupo, subgrupo, proveedor, txtIva.getText(), big.getMoneda(txtL1.getText()), big.getMoneda(txtL2.getText()),
                big.getMoneda(txtL3.getText()), big.getMoneda(txtL4.getText()), big.getMoneda(txtL5.getText()), big.getMoneda(txtL6.getText()),
                big.getMoneda(txtL7.getText()), big.getMoneda(txtL8.getText()), "ADMIN", txtMinimo.getText(), txtMedida.getText(),
                txtReferencia.getText(), costoProducto, txtMinima.getText(), txtMaximo.getText(), txtUbicacion.getText(),
                txtDescripcion2.getText(), txtUbicacion2.getText(), txtCantidad2.getText(),
                txtDescripcion3.getText(), txtUbicacion3.getText(), txtCantidad3.getText(),
                txtDescripcion4.getText(), txtUbicacion4.getText(), txtCantidad4.getText(),
                txtDescripcion5.getText(), txtUbicacion5.getText(), txtCantidad5.getText(),
                txtDescripcion6.getText(), txtUbicacion6.getText(), txtCantidad6.getText(),
                txtDescripcion7.getText(), txtUbicacion7.getText(), txtCantidad7.getText(),
                txtDescripcion8.getText(), txtUbicacion8.getText(), txtCantidad8.getText(),
                plu2.isSelected(), plu3.isSelected(), plu4.isSelected(), plu5.isSelected(), plu6.isSelected(), plu7.isSelected(),
                plu8.isSelected(), txtIvaCompra.getText(), 0, instancias.getTerminal(), "0", cmbClaseBuscador.getSelectedItem(), rdInventarioSI.isSelected(),
                txtPorcentaje.getText(), "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0",
                false, txtDescripcionIngles.getText(), "", txtPeso.getText(),
                "", "", tipo, "", txtMarca.getText(), txtTipoProd.getText(), "0",
                txtCodigo2.getText(), txtCodigo3.getText(), txtCodigo4.getText(), txtCodigo5.getText(), txtCodigo6.getText(), txtCodigo7.getText(),
                txtCodigo8.getText(), "", "", "", "", "", "", "1", txtImpoconsumo.getText(), txtImpoconsumoCompra.getText(), txtDescripcionLarga.getText(), "0"};

            nodo = metodos.llenarProducto(vector);

            if (!instancias.getSql().agregarProducto(nodo, "bdProductos")) {
                metodos.msgError(this, "Error al guardar el producto");
                return;
            }

            if (!instancias.getSql().agregarProducto(nodo, "bdProductosBodega1")) {
                metodos.msgError(this, "Error al guardar el producto");
                return;
            }
            if (!instancias.getSql().agregarProducto(nodo, "bdProductosBodega2")) {
                metodos.msgError(this, "Error al guardar el producto");
                return;
            }
            if (!instancias.getSql().agregarProducto(nodo, "bdProductosBodega3")) {
                metodos.msgError(this, "Error al guardar el producto");
                return;
            }
            if (!instancias.getSql().agregarProducto(nodo, "bdProductosBodega4")) {
                metodos.msgError(this, "Error al guardar el producto");
                return;
            }

            if (!instancias.getSql().aumentarConsecutivo("PROD", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("PROD")[0]) + 1)) {
                metodos.msgError(this, "Error al guardar en el consecutivo del producto");
            }

            ServicioActualizacionPonderado servicioActualizacionPonderado = new ServicioActualizacionPonderado();

            try {
                servicioActualizacionPonderado.crearPonderadoInicial(nodo, costoProducto, instancias.getUsuario());
            } catch (SQLException ex) {
                Logger.getLogger(VistaProductos.class.getName()).log(Level.SEVERE, null, ex);
            }

            lbNit.requestFocus();
            metodos.msgExito(this, "Producto registrado con éxito");
            btnNuevoActionPerformed(evt);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void lbImpoCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lbImpoCompraActionPerformed
        if (lbImpoCompra.isSelected()) {
            txtImpoconsumo.setEnabled(true);
            txtImpoconsumo.setText("0");
            txtImpoconsumoCompra.setEnabled(true);
            txtImpoconsumoCompra.setText("0");
        } else {
            txtImpoconsumo.setEnabled(false);
            nodoConf = instancias.getSql().getDatosConfiguracion();
            txtImpoconsumo.setText("0");
            txtImpoconsumoCompra.setEnabled(false);
            txtImpoconsumoCompra.setText("0");
        }
    }//GEN-LAST:event_lbImpoCompraActionPerformed

    private void lbIvaCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lbIvaCompraActionPerformed
        if (lbIvaCompra.isSelected()) {
            txtIva.setEnabled(true);
            txtIva.setText("0");
            txtIvaCompra.setEnabled(true);
            txtIvaCompra.setText("0");
        } else {
            txtIva.setEnabled(false);
            txtIva.setText("0");
            txtIvaCompra.setEnabled(false);
            txtIvaCompra.setText("0");
        }
    }//GEN-LAST:event_lbIvaCompraActionPerformed

    private void txtImpoconsumoCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtImpoconsumoCompraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtImpoconsumoCompraActionPerformed

    private void txtImpoconsumoCompraKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtImpoconsumoCompraKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtImpoconsumo.requestFocus();
        } else {
            txtImpoconsumo.setText(txtImpoconsumoCompra.getText());
        }
    }//GEN-LAST:event_txtImpoconsumoCompraKeyReleased

    private void txtImpoconsumoCompraKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtImpoconsumoCompraKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtImpoconsumoCompraKeyTyped

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        cargarProveedor("");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            String grupo = instancias.getSql().getDatosGrupoPorNombre(txtGrupo.getSelectedItem().toString());
            instancias.setValor(grupo);
            ventanaSubGrupos(txtSubgrupo.getText());
        } catch (Exception e) {
            metodos.msgError(this, "No ha cargado un grupo");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnRelacionadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRelacionadosMouseClicked
        if (btnRelacionados.isEnabled()) {
            dlgCodigoRelacionados relacionados = new dlgCodigoRelacionados(null, true, txtIdSistema.getText());
            relacionados.setLocationRelativeTo(null);
            relacionados.setVisible(true);
        }
    }//GEN-LAST:event_btnRelacionadosMouseClicked

    private void chkTallaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTallaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkTallaActionPerformed

    private void chkColorTallaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkColorTallaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkColorTallaActionPerformed

    private void txtProveedorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProveedorKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtIva.requestFocus();
        }
    }//GEN-LAST:event_txtProveedorKeyReleased

    private void txtProveedorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProveedorKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProveedorKeyTyped

    private void txtMaximoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaximoKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtMaximoKeyTyped

    private void txtMinimaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMinimaKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtMinimaKeyTyped

    private void btnUnificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUnificarActionPerformed
        if (txtDescripcionUnificar.getText().equals("")) {
            metodos.msgError(this, "No a cargado un producto para unificar");
            return;
        }

        cargarProductoTabla(txtIdSistema.getText(), txtCodUnificar.getText());

        txtCodUnificar.setText("");
        txtDescripcionUnificar.setText("");
    }//GEN-LAST:event_btnUnificarActionPerformed

    private void txtDescripcionLargaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionLargaKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtDescripcionIngles.requestFocus();
        }
    }//GEN-LAST:event_txtDescripcionLargaKeyReleased

    private void jScrollPane2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jScrollPane2KeyReleased

    }//GEN-LAST:event_jScrollPane2KeyReleased

    private void chkSerialColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSerialColorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkSerialColorActionPerformed

    public void ventanaTipoProd(String nit) {
        buscTiposProductos buscar = new buscTiposProductos(instancias.getMenu(), rootPaneCheckingEnabled);
        buscar.setLocationRelativeTo(null);
        instancias.setBuscTiposProductos(buscar);
        instancias.setCampoActual(txtTipoProd);
        txtMedida.requestFocus();
        buscar.setInstancia(instancias);
        buscar.noEncontrado(nit);
        buscar.show();
    }

    public void ventanaMedidas(String nit) {
        buscMedidas buscar = new buscMedidas(instancias.getMenu(), rootPaneCheckingEnabled);
        buscar.setLocationRelativeTo(null);
        instancias.setBuscMedidas(buscar);
        instancias.setCampoActual(txtMedida);
        txtMedida.requestFocus();
        buscar.setInstancia(instancias);
        buscar.noEncontrado(nit);
        buscar.show();
    }

    public void ventanaMarcas(String nit) {
        buscMarcas buscar = new buscMarcas(instancias.getMenu(), rootPaneCheckingEnabled);
        buscar.setLocationRelativeTo(null);
        instancias.setBuscMarcas(buscar);
        instancias.setCampoActual(txtMarca);
        txtMarca.requestFocus();
        buscar.setInstancia(instancias);
        buscar.noEncontrado(nit);
        buscar.show();
    }

    public void cargarProductoTabla(String cod1, String cod2) {
        ndProducto nodo = instancias.getSql().getDatosProducto(cod2, "bdProductos");
        instancias.getSql().agregarProductosUnificados(new Object[]{cod1, nodo.getIdSistema(), instancias.getUsuario()});
        ((DefaultTableModel) tblProductosUnificados.getModel()).addRow(
                new Object[]{nodo.getIdSistema(), nodo.getCodigo(), nodo.getDescripcion()});
    }

    public void cargarProveedor(String nit) {

        ModeloContacto nodo = instancias.getSql().getDatosTercero(nit);

        if (nodo.getId() != null) {
            txtProveedor.setText(nodo.getIdSistema());
            txtNombreProveedor.setText(nodo.getNombre());
            txtIvaCompra.requestFocus();
            return;
        }
        ventanaProveedor(nit);
    }

    public void ventanaProveedor(String nit) {
        buscClientes buscar = new buscClientes(instancias.getMenu(), false, true, null, "");
        buscar.setLocationRelativeTo(null);
        buscar.setTipoBuscador("");
        instancias.setBusClientes(buscar);
        instancias.setCampoActual(txtNombreProveedor);
        txtNombreProveedor.requestFocus();
        buscar.noEncontrado(nit);
        buscar.show();
    }

    public void actualizarGrupos() {
        txtGrupo.removeAllItems();
        txtGrupo.addItem("");

        grupos = instancias.getSql().getRegistrosGrupos();
        for (Object[] grupo : grupos) {
            txtGrupo.addItem(grupo[1]);
        }
    }

    public void cargarProducto(String cod) {

        if (cod.equals("")) {
            metodos.msgAdvertencia(this, "Debe ingresar el codigo");
        } else {
            ndProducto nodo = instancias.getSql().getDatosProducto(cod.replace("'", "//"), "bdProductos");

            if (nodo.getCodigo() != null) {

                btnImagen.setEnabled(true);
                btnRelacionados.setEnabled(true);

                txtIdSistema.setText(nodo.getIdSistema());
                txtCodigo.setText(nodo.getCodigo());

                if (nodo.getCodigo().equals("IMP01")) {
                    txtCodigo.setEnabled(false);
                }

                txtCodigoBarras.setText(nodo.getCodigoBarras());
                txtMarca.setText(nodo.getMarca());
                txtTipoProd.setText(nodo.getTipoProd());
                txtPeso.setText(nodo.getPeso());
                txtDescripcionIngles.setText(nodo.getDescripcionIngles());
                txtDescripcionLarga.setText(nodo.getDescripcionLarga());

                ImageIcon fot = new ImageIcon(System.getProperty("user.dir") + "\\imagenes\\productos\\" + nodo.getIdSistema() + ".jpg");
                Icon icono = new ImageIcon(fot.getImage().getScaledInstance(lbFoto.getWidth(), lbFoto.getHeight(), Image.SCALE_DEFAULT));
                lbFoto.setIcon(icono);
                this.repaint();

                txtPorcentaje.setText(nodo.getPorcentaje());

                String tipo = "";

                try {
                    tipo = nodo.getTipoProducto();
                } catch (Exception e) {
                }

                try {
                    if (tipo.equals("Fecha/Lote")) {
                        chkLote.setSelected(true);
                    } else if (tipo.equals("IMEI")) {
                        chkImei.setSelected(true);
                    } else if (tipo.equals("Color")) {
                        chkColor.setSelected(true);
                    } else if (tipo.equals("Talla")) {
                        chkTalla.setSelected(true);
                    } else if (tipo.equals("ColorTalla")) {
                        chkColorTalla.setSelected(true);
                    } else if (tipo.equals("Serial")) {
                        chkSerial.setSelected(true);
                    } else if (tipo.equals("SerialColor")) {
                        chkSerialColor.setSelected(true);
                    } else {
                        chkNinguno.setSelected(true);
                    }
                } catch (Exception e) {
                    chkNinguno.setSelected(true);
                }

                txtCodigo1.setText(txtCodigo.getText() + "-1");

                txtCodigo2.setText(nodo.getCodigo2());
                txtCodigo3.setText(nodo.getCodigo3());
                txtCodigo4.setText(nodo.getCodigo4());
                txtCodigo5.setText(nodo.getCodigo5());
                txtCodigo6.setText(nodo.getCodigo6());
                txtCodigo7.setText(nodo.getCodigo7());
                txtCodigo8.setText(nodo.getCodigo8());

                try {
                    txtGrupo.setSelectedItem(instancias.getSql().getDatosGrupoNombre(nodo.getGrupo()));
                } catch (Exception e) {

                }

                txtProveedor.setText(nodo.getProveedor());
                try {
                    ModeloContacto nodo2 = instancias.getSql().getDatosTercero(nodo.getProveedor());
                    if (nodo2.getNombre().equals("")) {
                        //ACA NUNCA ENTRARA SI ES ERROR, SE VA AL CATCH
                    }
                    txtNombreProveedor.setText(nodo2.getNombre());
                } catch (Exception e) {
                    txtNombreProveedor.setText(nodo.getProveedor());
                }

                txtCodigoSubGrupo.setText(nodo.getSubgrupo());
                try {
                    //Si el Sub grupo si esta creado desde el grupo.
                    txtSubgrupo.setText(instancias.getSql().getDatosSubGrupo(txtCodigoSubGrupo.getText()));
                } catch (Exception e) {
                    //Si no esta creado en el grupo y es un valor diferente.
                    txtSubgrupo.setText(txtCodigoSubGrupo.getText());
                }

                //CARGAMOS EL IMPOCONSUMO DE VENTAS
                if (nodo.getImpoconsumoVenta().equals(".00")) {
                    txtImpoconsumo.setText("0");
                } else {
                    txtImpoconsumo.setText(nodo.getImpoconsumoVenta().substring(0, nodo.getImpoconsumoVenta().length() - 3));
                }

                //CARGAMOS EL IMPOCONSUMO DE COMPRAS
                if (nodo.getImpoconsumoCompra().equals(".00")) {
                    txtImpoconsumoCompra.setText("0");
                } else {
                    txtImpoconsumoCompra.setText(nodo.getImpoconsumoCompra().substring(0, nodo.getImpoconsumoCompra().length() - 3));
                }

                //CARGAMOS EL IVA DE VENTAS
                if (nodo.getIva().equals(".00")) {
                    txtIva.setText("0");
                } else {
                    txtIva.setText(nodo.getIva().substring(0, nodo.getIva().length() - 3));
                }

                //CARGAMOS EL IVA DE COMPRAS
                if (nodo.getIvaC().equals(".00")) {
                    txtIvaCompra.setText("0");
                } else {
                    txtIvaCompra.setText(nodo.getIvaC().substring(0, nodo.getIvaC().length() - 3));
                }

                if (txtImpoconsumo.getText().equals("0") || txtImpoconsumo.getText().equals("0.00")) {
                    txtImpoconsumo.setEnabled(false);
                    txtImpoconsumoCompra.setEnabled(false);
                } else {
                    txtImpoconsumo.setEnabled(true);
                    txtImpoconsumoCompra.setEnabled(true);
                    lbImpoCompra.setSelected(true);
                }

                if (txtIva.getText().equals("0") || txtIva.getText().equals("0.00")) {
                    txtIva.setEnabled(false);
                    txtIvaCompra.setEnabled(false);
                } else {
                    txtIva.setEnabled(true);
                    txtIvaCompra.setEnabled(true);
                    lbIvaCompra.setSelected(true);
                }

                txtL1.setText(big.setMoneda(big.getBigDecimal(nodo.getL1())));
                txtL2.setText(big.setMoneda(big.getBigDecimal(nodo.getL2())));
                txtL3.setText(big.setMoneda(big.getBigDecimal(nodo.getL3())));
                txtL4.setText(big.setMoneda(big.getBigDecimal(nodo.getL4())));
                txtL5.setText(big.setMoneda(big.getBigDecimal(nodo.getL5())));
                txtL6.setText(big.setMoneda(big.getBigDecimal(nodo.getL6())));
                txtL7.setText(big.setMoneda(big.getBigDecimal(nodo.getL7())));
                txtL8.setText(big.setMoneda(big.getBigDecimal(nodo.getL8())));
                txtMinimo.setText(nodo.getMinimo());
                txtMedida.setText(nodo.getUnd());

                txtUbicacion.setText(nodo.getUbicacion1());
                txtUbicacion2.setText(nodo.getUbicacion2());
                txtUbicacion3.setText(nodo.getUbicacion3());
                txtUbicacion4.setText(nodo.getUbicacion4());
                txtUbicacion5.setText(nodo.getUbicacion5());
                txtUbicacion6.setText(nodo.getUbicacion6());
                txtUbicacion7.setText(nodo.getUbicacion7());
                txtUbicacion8.setText(nodo.getUbicacion8());

                txtCantidad2.setText(nodo.getCantidad2());
                txtCantidad3.setText(nodo.getCantidad3());
                txtCantidad4.setText(nodo.getCantidad4());
                txtCantidad5.setText(nodo.getCantidad5());
                txtCantidad6.setText(nodo.getCantidad6());
                txtCantidad7.setText(nodo.getCantidad7());
                txtCantidad8.setText(nodo.getCantidad8());

                if (!nodo.getReferencia().equals("null")) {
                    txtReferencia.setText(nodo.getReferencia());
                } else {
                    txtReferencia.setText("");
                }

                BigDecimal ponderado = BigDecimal.ZERO;
                BigDecimal ultimoCosto = BigDecimal.ZERO;
                String fechaCompra = "";

                try {
                    UltimoPonderado ultimoPonderado = servicioActualizacionPonderado.obtenerUltimoPonderado(nodo.getIdSistema());
                    ponderado = ultimoPonderado.getNuevoPonderado();
                    ultimoCosto = ultimoPonderado.getUltimoCosto();
                    fechaCompra = ultimoPonderado.getFechaCompra();
                } catch (SQLException ex) {
                    Logger.getLogger(VistaInventarioInicial.class.getName()).log(Level.SEVERE, null, ex);
                }

                txtFCompra.setText(fechaCompra);
                txtCosto.setText(big.setMoneda(ultimoCosto));
                txtCosto1.setText(big.setMoneda(ponderado));

                if (!nodo.getMinima().equals("null")) {
                    txtMinima.setText(nodo.getMinima());
                } else {
                    txtMinima.setText("0");
                }
                if (!nodo.getMaxima().equals("null")) {
                    txtMaximo.setText(nodo.getMaxima());
                } else {
                    txtMaximo.setText("0");
                }

                if (nodo.getIvaC().equals(".00")) {
                    txtIvaCompra.setText("0");
                } else {
                    txtIvaCompra.setText(nodo.getIvaC().substring(0, nodo.getIvaC().length() - 3));
                }

                plu2.setSelected(nodo.isPlu2());
                plu3.setSelected(nodo.isPlu3());
                plu4.setSelected(nodo.isPlu4());

                cmbClaseBuscador.setSelectedItem(nodo.getClaseBuscador());

                txtDescripcion.requestFocus();
                btnActualizar.setEnabled(true);
                btnGuardar.setEnabled(false);

                try {
                    rdInventarioSI.setSelected(nodo.getManejaInventario());
                } catch (Exception e) {
                    rdInventarioNO.setSelected(true);
                }

                utilidad();

                if (instancias.getSql().getProdActivo(txtCodigo.getText())) {
                    btnEliminar.setText("ACTIVAR");
                    btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/agregar.png")));
                }

                txtCodUnificar.setEditable(true);
                btnUnificar.setEnabled(true);

                Object[][] datos = instancias.getSql().getDatosProductosUnificados(nodo.getIdSistema());
                for (int i = 0; i < datos.length; i++) {
                    ((DefaultTableModel) tblProductosUnificados.getModel()).addRow(new Object[]{datos[i][0], datos[i][6], datos[i][1]});
                }

                tblProductosUnificados.getColumnModel().getColumn(0).setMinWidth(0);
                tblProductosUnificados.getColumnModel().getColumn(0).setPreferredWidth(0);
                tblProductosUnificados.getColumnModel().getColumn(0).setMaxWidth(0);

                txtDescripcion.setText(nodo.getDescripcion());
                txtDescripcion1.setText(nodo.getDescripcion());
                txtDescripcion2.setText(nodo.getDescripcion2());
                txtDescripcion3.setText(nodo.getDescripcion3());
                txtDescripcion4.setText(nodo.getDescripcion4());
                txtDescripcion5.setText(nodo.getDescripcion5());
                txtDescripcion6.setText(nodo.getDescripcion6());
                txtDescripcion7.setText(nodo.getDescripcion7());
                txtDescripcion8.setText(nodo.getDescripcion8());

                return;
            }
            metodos.msgError(this, "El producto no existe");
        }
    }

    public void cargarProductos(Object[][] productos) {
        for (int i = 0; i < productos.length; i++) {
            String codigo = productos[i][0].toString();
            cargarProductoTabla(txtCodigo.getText(), codigo);
        }
    }

    public void ventanaProductos(String codigo) {
        buscProductos buscar = new buscProductos(null, true, true, "", "productos1");
        buscar.setLocationRelativeTo(null);
        instancias.setBusProductos(buscar);
        instancias.setCampoActual(txtCodigo);
        txtCodigo.requestFocus();
        buscar.noEncontrado(codigo.replace("'", "//"));
        buscar.show();
    }

    public void ventanaProductosUnificar(String codigo) {
        buscProductos buscar = new buscProductos(null, rootPaneCheckingEnabled, false, "", "productos1");
        buscar.setLocationRelativeTo(null);
        buscar.setOpc("producto");
        instancias.setBusProductos(buscar);
        instancias.setCampoActual(txtCodUnificar);
        txtCodUnificar.requestFocus();
        buscar.noEncontrado(codigo.replace("'", "//"));
        buscar.show();
    }

    public void codificador1(String cod, String grupo, String subgrupo) {
        txtCodigo.setText(cod);
        txtSubgrupo.setText(grupo);
        txtGrupo.setSelectedItem(subgrupo);
    }

    public void utilidad() {

        if (txtCosto.getText().equals("")) {
            txtCosto.setText(this.simbolo + " 0");
        }

        if (txtIva.getText().equals("")) {
            txtIva.setText("0");
        }

        if (txtIvaCompra.getText().equals("")) {
            txtIvaCompra.setText("0");
        }

        BigDecimal compra, utilidades1, utilidades2, porcentaje, utilidad;
        if ((txtIva.getText().equals("0") || txtIva.getText().equals(".00")) && !instancias.isCostoConIva()) {
            compra = ((big.getMoneda(txtCosto.getText()).multiply(big.getBigDecimal(txtIvaCompra.getText()))).divide(new BigDecimal("100"))).add(big.getMoneda(txtCosto.getText()));
        } else {
            compra = big.getMoneda(txtCosto.getText());
        }

//        utilidades1 = (compra.multiply(big.getBigDecimal(txtMinima.getText())).divide(new BigDecimal("100"))).add(compra);
//        utilidades2 = (compra.multiply(big.getBigDecimal(txtMaximo.getText())).divide(new BigDecimal("100"))).add(compra);
        Integer campo, campo1;
        campo = Integer.parseInt(txtMinima.getText());
        campo1 = Integer.parseInt(txtMaximo.getText());

        utilidades1 = compra.multiply(big.getBigDecimal(campo).divide(big.getBigDecimal(100), 3, RoundingMode.HALF_DOWN));
        utilidades1 = utilidades1.add(compra);

        utilidades2 = compra.multiply(big.getBigDecimal(campo1).divide(big.getBigDecimal(100), 3, RoundingMode.HALF_DOWN));
        utilidades2 = utilidades2.add(compra);

        txtUtilidad1.setText(big.setMoneda(utilidades1.setScale(0, BigDecimal.ROUND_HALF_DOWN)));
        txtUtilidad2.setText(big.setMoneda(utilidades2.setScale(0, BigDecimal.ROUND_HALF_DOWN)));

        try {
            utilidad = big.getMoneda(txtL1.getText()).subtract(compra);
            porcentaje = utilidad.divide(compra, 2, RoundingMode.HALF_UP).multiply(big.getBigDecimal("100"));
            txtUtilidadL.setText(big.setMoneda(utilidad));
            txtUtilidadL1.setText("% " + porcentaje.doubleValue());
        } catch (Exception e) {
            txtUtilidadL.setText(this.simbolo + " 0");
            txtUtilidadL1.setText("% 0");
        }

        try {
            utilidad = big.getMoneda(txtL2.getText()).subtract(compra);
            porcentaje = utilidad.divide(compra, 2, RoundingMode.HALF_UP).multiply(big.getBigDecimal("100"));
            txtUtilidadL2.setText(big.setMoneda(utilidad));
            txtUtilidadL3.setText("% " + porcentaje.doubleValue());
        } catch (Exception e) {
            txtUtilidadL2.setText(this.simbolo + " 0");
            txtUtilidadL3.setText("% 0");
        }

        try {
            utilidad = big.getMoneda(txtL3.getText()).subtract(compra);
            porcentaje = utilidad.divide(compra, 2, RoundingMode.HALF_UP).multiply(big.getBigDecimal("100"));
            txtUtilidadL4.setText(big.setMoneda(utilidad));
            txtUtilidadL5.setText("% " + porcentaje.doubleValue());
        } catch (Exception e) {
            txtUtilidadL4.setText(this.simbolo + " 0");
            txtUtilidadL5.setText("% 0");
        }

        try {
            utilidad = big.getMoneda(txtL4.getText()).subtract(compra);
            porcentaje = utilidad.divide(compra, 2, RoundingMode.HALF_UP).multiply(big.getBigDecimal("100"));
            txtUtilidadL6.setText(big.setMoneda(utilidad));
            txtUtilidadL7.setText("% " + porcentaje.doubleValue());
        } catch (Exception e) {
            txtUtilidadL6.setText(this.simbolo + " 0");
            txtUtilidadL7.setText("% 0");
        }

        try {
            utilidad = big.getMoneda(txtL5.getText()).subtract(compra);
            porcentaje = utilidad.divide(compra, 2, RoundingMode.HALF_UP).multiply(big.getBigDecimal("100"));
            txtUtilidadL8.setText(big.setMoneda(utilidad));
            txtUtilidadL9.setText("% " + porcentaje.doubleValue());
        } catch (Exception e) {
            txtUtilidadL8.setText(this.simbolo + " 0");
            txtUtilidadL9.setText("% 0");
        }

        try {
            utilidad = big.getMoneda(txtL6.getText()).subtract(compra);
            porcentaje = utilidad.divide(compra, 2, RoundingMode.HALF_UP).multiply(big.getBigDecimal("100"));
            txtUtilidadL10.setText(big.setMoneda(utilidad));
            txtUtilidadL11.setText("% " + porcentaje.doubleValue());
        } catch (Exception e) {
            txtUtilidadL10.setText(this.simbolo + " 0");
            txtUtilidadL11.setText("% 0");
        }

        try {
            utilidad = big.getMoneda(txtL7.getText()).subtract(compra);
            porcentaje = utilidad.divide(compra, 2, RoundingMode.HALF_UP).multiply(big.getBigDecimal("100"));
            txtUtilidadL12.setText(big.setMoneda(utilidad));
            txtUtilidadL13.setText("% " + porcentaje.doubleValue());
        } catch (Exception e) {
            txtUtilidadL12.setText(this.simbolo + " 0");
            txtUtilidadL13.setText("% 0");
        }

        try {
            utilidad = big.getMoneda(txtL8.getText()).subtract(compra);
            porcentaje = utilidad.divide(compra, 2, RoundingMode.HALF_UP).multiply(big.getBigDecimal("100"));
            txtUtilidadL14.setText(big.setMoneda(utilidad));
            txtUtilidadL15.setText("% " + porcentaje.doubleValue());
        } catch (Exception e) {
            txtUtilidadL14.setText(this.simbolo + " 0");
            txtUtilidadL15.setText("% 0");
        }
    }

    public void ventanaSubGrupos(String codigo) {
        buscSubGrupos buscar = new buscSubGrupos(null, rootPaneCheckingEnabled);
        buscar.setLocationRelativeTo(null);
        instancias.setBuscSubGrupos(buscar);
//        instancias.setValor(txtCodigo.getText());
        instancias.setCampoActual(txtCodigoSubGrupo);
        txtCodigoSubGrupo.requestFocus();
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

    public void cargarSubGrupo(String codigo) {
        txtSubgrupo.setText(instancias.getSql().getDatosSubGrupo(codigo.replace("'", "//")));
        txtNombreProveedor.requestFocus();
    }

    public void llenarNodoProducto(ndProducto producto, String grupo, String tipo, String tipo1) {
        if ("".equals(grupo)) {
            grupo = null;
        }

        String subgrupo = txtCodigoSubGrupo.getText();
        if ("".equals(subgrupo)) {
            subgrupo = null;
        }

        String proveedor = txtProveedor.getText();
        if ("".equals(proveedor)) {
            proveedor = null;
        }

        Object[] vector = {txtIdSistema.getText(), txtCodigo.getText().replace("'", "//"), txtCodigoBarras.getText(), txtDescripcion.getText(),
            grupo, subgrupo, proveedor, txtIva.getText(), big.getMoneda(txtL1.getText()), big.getMoneda(txtL2.getText()),
            big.getMoneda(txtL3.getText()), big.getMoneda(txtL4.getText()), big.getMoneda(txtL5.getText()), big.getMoneda(txtL6.getText()),
            big.getMoneda(txtL7.getText()), big.getMoneda(txtL8.getText()), tipo, txtMinimo.getText(), txtMedida.getText(),
            txtReferencia.getText(), big.getMoneda(txtCosto.getText()), txtMinima.getText(), txtMaximo.getText(), txtUbicacion.getText(),
            txtDescripcion2.getText(), txtUbicacion2.getText(), txtCantidad2.getText(),
            txtDescripcion3.getText(), txtUbicacion3.getText(), txtCantidad3.getText(),
            txtDescripcion4.getText(), txtUbicacion4.getText(), txtCantidad4.getText(),
            txtDescripcion5.getText(), txtUbicacion5.getText(), txtCantidad5.getText(),
            txtDescripcion6.getText(), txtUbicacion6.getText(), txtCantidad6.getText(),
            txtDescripcion7.getText(), txtUbicacion7.getText(), txtCantidad7.getText(),
            txtDescripcion8.getText(), txtUbicacion8.getText(), txtCantidad8.getText(),
            plu2.isSelected(), plu3.isSelected(), plu4.isSelected(), plu5.isSelected(), plu6.isSelected(), plu7.isSelected(),
            plu8.isSelected(), txtIvaCompra.getText(), producto.getPonderado(), producto.getTerminal(), producto.getInventario(),
            cmbClaseBuscador.getSelectedItem(), rdInventarioSI.isSelected(), txtPorcentaje.getText(), producto.getCompras(), producto.getVentas(),
            producto.getNc(), producto.getAjusteEntrada(), producto.getAjusteSalida(), producto.getPlanSepare(), producto.getPedidos(), producto.getAnulada(),
            producto.getInventarioInicial(), producto.getAjusteInventario(), producto.getFisicoInventario(), producto.getArmado(), producto.getCosteo(),
            producto.getOrdenServicio(), producto.getCongelada(), producto.getTrasladoBod(), producto.getTrasladoInternoEntrada(), producto.getTrasladoInternoSalida(),
            false, txtDescripcionIngles.getText(), "", txtPeso.getText(), "", "",
            tipo1, "", txtMarca.getText(), txtTipoProd.getText(), producto.getEnTransito(), txtCodigo2.getText(), txtCodigo3.getText(),
            txtCodigo4.getText(), txtCodigo5.getText(), txtCodigo6.getText(), txtCodigo7.getText(), txtCodigo8.getText(), producto.getLenteMarco(),
            producto.getAdesivo(), producto.getColor(), producto.getEmpaque(), producto.getComposicion(), producto.getRmb(), producto.getIndVentas(),
            txtImpoconsumo.getText(), txtImpoconsumoCompra.getText(), txtDescripcionLarga.getText(), "0"};
        nodoProducto = metodos.llenarProducto(vector);
    }

//    public void estilos() {
//        try {
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(infProductos.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            Logger.getLogger(infProductos.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            Logger.getLogger(infProductos.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (UnsupportedLookAndFeelException ex) {
//            Logger.getLogger(infProductos.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnBusProd;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnImagen;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JLabel btnRelacionados;
    private javax.swing.JButton btnUnificar;
    private javax.swing.JCheckBox chkColor;
    private javax.swing.JCheckBox chkColorTalla;
    private javax.swing.JCheckBox chkImei;
    private javax.swing.JCheckBox chkLote;
    private javax.swing.JCheckBox chkNinguno;
    private javax.swing.JCheckBox chkSerial;
    private javax.swing.JCheckBox chkSerialColor;
    private javax.swing.JCheckBox chkTalla;
    private javax.swing.JComboBox cmbClaseBuscador;
    private javax.swing.ButtonGroup grpInventario;
    private javax.swing.ButtonGroup grpTipoProd;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lbCelular;
    private javax.swing.JLabel lbCiudad;
    private javax.swing.JLabel lbCiudad1;
    private javax.swing.JLabel lbCiudad10;
    private javax.swing.JLabel lbCiudad11;
    private javax.swing.JLabel lbCiudad12;
    private javax.swing.JLabel lbCiudad13;
    private javax.swing.JLabel lbCiudad14;
    private javax.swing.JLabel lbCiudad15;
    private javax.swing.JLabel lbCiudad16;
    private javax.swing.JLabel lbCiudad17;
    private javax.swing.JLabel lbCiudad18;
    private javax.swing.JLabel lbCiudad19;
    private javax.swing.JLabel lbCiudad2;
    private javax.swing.JLabel lbCiudad20;
    private javax.swing.JLabel lbCiudad21;
    private javax.swing.JLabel lbCiudad22;
    private javax.swing.JLabel lbCiudad23;
    private javax.swing.JLabel lbCiudad24;
    private javax.swing.JLabel lbCiudad25;
    private javax.swing.JLabel lbCiudad26;
    private javax.swing.JLabel lbCiudad27;
    private javax.swing.JLabel lbCiudad28;
    private javax.swing.JLabel lbCiudad29;
    private javax.swing.JLabel lbCiudad3;
    private javax.swing.JLabel lbCiudad30;
    private javax.swing.JLabel lbCiudad31;
    private javax.swing.JLabel lbCiudad32;
    private javax.swing.JLabel lbCiudad33;
    private javax.swing.JLabel lbCiudad34;
    private javax.swing.JLabel lbCiudad35;
    private javax.swing.JLabel lbCiudad36;
    private javax.swing.JLabel lbCiudad37;
    private javax.swing.JLabel lbCiudad38;
    private javax.swing.JLabel lbCiudad39;
    private javax.swing.JLabel lbCiudad4;
    private javax.swing.JLabel lbCiudad40;
    private javax.swing.JLabel lbCiudad41;
    private javax.swing.JLabel lbCiudad42;
    private javax.swing.JLabel lbCiudad43;
    private javax.swing.JLabel lbCiudad44;
    private javax.swing.JLabel lbCiudad45;
    private javax.swing.JLabel lbCiudad46;
    private javax.swing.JLabel lbCiudad47;
    private javax.swing.JLabel lbCiudad5;
    private javax.swing.JLabel lbCiudad6;
    private javax.swing.JLabel lbCiudad7;
    private javax.swing.JLabel lbCiudad8;
    private javax.swing.JLabel lbCiudad9;
    private javax.swing.JLabel lbCupo1;
    private javax.swing.JLabel lbCupo4;
    private javax.swing.JLabel lbCupo7;
    private javax.swing.JLabel lbDepartamento1;
    private javax.swing.JLabel lbDepartamento2;
    private javax.swing.JLabel lbDepartamento3;
    private javax.swing.JLabel lbDepartamento4;
    private javax.swing.JLabel lbDireccion;
    private javax.swing.JLabel lbFoto;
    private javax.swing.JCheckBox lbImpoCompra;
    private javax.swing.JLabel lbImpoVenta;
    private javax.swing.JLabel lbInfoObligatorios;
    private javax.swing.JCheckBox lbIvaCompra;
    private javax.swing.JLabel lbIvaVenta;
    private javax.swing.JLabel lbNit;
    private javax.swing.JLabel lbRazon;
    private javax.swing.JLabel lbRazon1;
    private javax.swing.JLabel lbRazon2;
    private javax.swing.JLabel lbTelefono;
    private javax.swing.JLabel lbTelefono1;
    private javax.swing.JLabel lbTelefono2;
    private javax.swing.JLabel lbTelefono3;
    private javax.swing.JLabel mensaje2;
    private javax.swing.JLabel mensaje3;
    private javax.swing.JLabel mensaje4;
    private javax.swing.JCheckBox plu2;
    private javax.swing.JCheckBox plu3;
    private javax.swing.JCheckBox plu4;
    private javax.swing.JCheckBox plu5;
    private javax.swing.JCheckBox plu6;
    private javax.swing.JCheckBox plu7;
    private javax.swing.JCheckBox plu8;
    private javax.swing.JPanel pnlFormulario;
    private javax.swing.JPanel pnlInvisible;
    private javax.swing.JPanel pnlInvisible1;
    private javax.swing.JMenuItem popBorrar;
    private javax.swing.JRadioButton rdInventarioNO;
    private javax.swing.JRadioButton rdInventarioSI;
    private javax.swing.JScrollPane scrFormulario;
    private javax.swing.JTable tblProductosUnificados;
    private javax.swing.JTextField txtCantidad2;
    private javax.swing.JTextField txtCantidad3;
    private javax.swing.JTextField txtCantidad4;
    private javax.swing.JTextField txtCantidad5;
    private javax.swing.JTextField txtCantidad6;
    private javax.swing.JTextField txtCantidad7;
    private javax.swing.JTextField txtCantidad8;
    private javax.swing.JTextField txtCodUnificar;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtCodigo1;
    private javax.swing.JTextField txtCodigo2;
    private javax.swing.JTextField txtCodigo3;
    private javax.swing.JTextField txtCodigo4;
    private javax.swing.JTextField txtCodigo5;
    private javax.swing.JTextField txtCodigo6;
    private javax.swing.JTextField txtCodigo7;
    private javax.swing.JTextField txtCodigo8;
    private javax.swing.JTextField txtCodigoBarras;
    private javax.swing.JTextField txtCodigoSubGrupo;
    private javax.swing.JTextField txtCosto;
    private javax.swing.JTextField txtCosto1;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtDescripcion1;
    private javax.swing.JTextField txtDescripcion2;
    private javax.swing.JTextField txtDescripcion3;
    private javax.swing.JTextField txtDescripcion4;
    private javax.swing.JTextField txtDescripcion5;
    private javax.swing.JTextField txtDescripcion6;
    private javax.swing.JTextField txtDescripcion7;
    private javax.swing.JTextField txtDescripcion8;
    private javax.swing.JTextField txtDescripcionIngles;
    private javax.swing.JTextArea txtDescripcionLarga;
    private javax.swing.JTextField txtDescripcionUnificar;
    private javax.swing.JTextField txtFCompra;
    private javax.swing.JComboBox txtGrupo;
    private javax.swing.JTextField txtIdSistema;
    private javax.swing.JTextField txtImpoconsumo;
    private javax.swing.JTextField txtImpoconsumoCompra;
    private javax.swing.JTextField txtIva;
    private javax.swing.JTextField txtIvaCompra;
    private javax.swing.JTextField txtL1;
    private javax.swing.JTextField txtL2;
    private javax.swing.JTextField txtL3;
    private javax.swing.JTextField txtL4;
    private javax.swing.JTextField txtL5;
    private javax.swing.JTextField txtL6;
    private javax.swing.JTextField txtL7;
    private javax.swing.JTextField txtL8;
    private javax.swing.JTextField txtMarca;
    private javax.swing.JTextField txtMaximo;
    private javax.swing.JTextField txtMedida;
    private javax.swing.JTextField txtMinima;
    private javax.swing.JTextField txtMinimo;
    private javax.swing.JTextField txtNombreProveedor;
    private javax.swing.JTextField txtPeso;
    private javax.swing.JTextField txtPorcentaje;
    private javax.swing.JTextField txtProveedor;
    private javax.swing.JTextField txtReferencia;
    private javax.swing.JTextField txtSubgrupo;
    private javax.swing.JTextField txtTipoProd;
    private javax.swing.JTextField txtUbicacion;
    private javax.swing.JTextField txtUbicacion2;
    private javax.swing.JTextField txtUbicacion3;
    private javax.swing.JTextField txtUbicacion4;
    private javax.swing.JTextField txtUbicacion5;
    private javax.swing.JTextField txtUbicacion6;
    private javax.swing.JTextField txtUbicacion7;
    private javax.swing.JTextField txtUbicacion8;
    private javax.swing.JTextField txtUtilidad1;
    private javax.swing.JTextField txtUtilidad2;
    private javax.swing.JTextField txtUtilidadL;
    private javax.swing.JTextField txtUtilidadL1;
    private javax.swing.JTextField txtUtilidadL10;
    private javax.swing.JTextField txtUtilidadL11;
    private javax.swing.JTextField txtUtilidadL12;
    private javax.swing.JTextField txtUtilidadL13;
    private javax.swing.JTextField txtUtilidadL14;
    private javax.swing.JTextField txtUtilidadL15;
    private javax.swing.JTextField txtUtilidadL2;
    private javax.swing.JTextField txtUtilidadL3;
    private javax.swing.JTextField txtUtilidadL4;
    private javax.swing.JTextField txtUtilidadL5;
    private javax.swing.JTextField txtUtilidadL6;
    private javax.swing.JTextField txtUtilidadL7;
    private javax.swing.JTextField txtUtilidadL8;
    private javax.swing.JTextField txtUtilidadL9;
    // End of variables declaration//GEN-END:variables
}
