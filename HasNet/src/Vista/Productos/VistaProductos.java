package Vista.Productos;

import Vista.Buscadores.BuscadoresCatalogo;
import Vista.Buscadores.VistaBuscadorCatalogo;

import Controlador.Alertas.ControladorAlertas;

import Utilidades.Contactos.Bodegas;

import Dao.Productos.DaoProducto;
import Dao.Productos.DaoCatalogosProducto;
import Modelo.Productos.ModeloOpcionCatalogo;
import javax.swing.DefaultComboBoxModel;

import Modelo.Inventario.UltimoPonderado;
import Modelo.Contactos.ModeloContacto;
import clases.ImagePreviewPanel;
import clases.Instancias;
import clases.big;
import clases.metodosGenerales;
import Modelo.Productos.FilaImportacionRechazada;
import Modelo.Productos.ModeloProducto;
import Modelo.Productos.ResultadoImportacionProductos;
import Modelo.Maestra.modeloConfiguracion;
import Procesos.Inventario.Servicio.ServicioActualizacionPonderado;
import Procesos.Productos.Servicio.ServicioImportacionProductos;
import Utilidades.PanelZoomImagen;
import Utilidades.ImportacionesExcel.DescargadorPlantillas;
import Utilidades.ImportacionesExcel.EscritorProductosRechazados;
import Utilidades.ImportacionesExcel.PlantillaCreacionProductos;
import Validaciones.Productos.SquemaProductos;
import Vista.Contactos.VistaBuscadorContactos;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.CopyOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

    private static final String CONSECUTIVO_PRODUCTO = "PROD";
    private static final String PREFIJO_PRODUCTO = "PROD-";
    private static final String USUARIO_CREACION = "ADMIN";

    private final SquemaProductos squemaProductos = new SquemaProductos();
    private final DaoProducto daoProducto = new DaoProducto();
    private final DaoCatalogosProducto daoCatalogosProducto = new DaoCatalogosProducto();
    private final VistaListasPrecio panelListas = new VistaListasPrecio();
    private final ServicioActualizacionPonderado servicioActualizacionPonderado = new ServicioActualizacionPonderado();
    private final Instancias instancias;

    metodosGenerales metodos = new metodosGenerales();

    private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension dimBarra = null;

    Object[][] grupos;
    modeloConfiguracion nodoConf;
    String simbolo = "";
    private ModeloProducto productoCargado;

    public VistaProductos() {

        initComponents();
        configurarListasPrecio();
        configurarBotonesPlantilla();

        pnlInvisible.setVisible(false);

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
            pnlTiposProducto.setVisible(true);
        } else {
            pnlTiposProducto.setVisible(false);
        }

        if (!instancias.getConfiguraciones().isParqueadero()) {
            lbTelefono3.setVisible(false);
            txtPorcentaje.setVisible(false);
        }

        if (requiereUnidad()) {
            lbUnidadMedida.setText("Unidad medida: *");
            lbUnidadMedida.setFont(lbUnidadMedida.getFont().deriveFont(java.awt.Font.BOLD));
        }

        nodoConf = instancias.getSql().getDatosConfiguracion();
        txtCosto.setText(this.simbolo + " 0");
        actualizarGrupos();

        txtIdSistema.setText(PREFIJO_PRODUCTO + siguienteConsecutivo());

        pnlFormulario.registerKeyboardAction(accion("guardar"), "guardar", KeyStroke.getKeyStroke(KeyEvent.VK_G, Event.CTRL_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
        pnlFormulario.registerKeyboardAction(accion("limpiar"), "limpiar", KeyStroke.getKeyStroke(KeyEvent.VK_L, Event.CTRL_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
        pnlFormulario.registerKeyboardAction(accion("productos"), "productos", KeyStroke.getKeyStroke(KeyEvent.VK_P, Event.CTRL_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
        pnlFormulario.registerKeyboardAction(accion("modificar"), "modificar", KeyStroke.getKeyStroke(KeyEvent.VK_M, Event.CTRL_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
    }

    private void configurarBotonesPlantilla() {
        btnDescargarPlantilla.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                DescargadorPlantillas.descargar(VistaProductos.this, DescargadorPlantillas.CREAR_PRODUCTOS, new PlantillaCreacionProductos());
            }
        });

        btnDescargarPlantilla1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                DescargadorPlantillas.descargar(VistaProductos.this, DescargadorPlantillas.CREAR_PRODUCTOS_CON_INVENTARIO, new PlantillaCreacionProductos());
            }
        });
    }

    private void configurarListasPrecio() {
        pnlListasPrecios.removeAll();
        pnlListasPrecios.setLayout(new java.awt.BorderLayout());
        pnlListasPrecios.add(panelListas, java.awt.BorderLayout.CENTER);
        panelListas.setAlCambiar(new Runnable() {
            @Override
            public void run() {
                utilidad();
            }
        });
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
        return new Object[]{txtIvaCompra, lbIvaCompra, txtImpoconsumoCompra, lbImpoCompra};
    }

    public void ocultarItems(boolean ocultar) {
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
        pnlDatosProducto = new javax.swing.JPanel();
        lbNit = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        lbCodigoBarras = new javax.swing.JLabel();
        txtCodigoBarras = new javax.swing.JTextField();
        lbRazon = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        lbTelefono = new javax.swing.JLabel();
        txtGrupo = new javax.swing.JComboBox();
        lbCelular = new javax.swing.JLabel();
        cmbSubgrupo = new javax.swing.JComboBox();
        lbUnidadMedida = new javax.swing.JLabel();
        txtMedida = new javax.swing.JTextField();
        lbTelefono2 = new javax.swing.JLabel();
        rdInventarioSI = new javax.swing.JRadioButton();
        rdInventarioNO = new javax.swing.JRadioButton();
        txtFCompra = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        btnImagen = new javax.swing.JButton();
        lbFoto = new javax.swing.JLabel();
        pnlTiposProducto = new javax.swing.JPanel();
        chkNinguno = new javax.swing.JCheckBox();
        chkColor = new javax.swing.JCheckBox();
        chkImei = new javax.swing.JCheckBox();
        chkLote = new javax.swing.JCheckBox();
        chkSerial = new javax.swing.JCheckBox();
        chkTalla = new javax.swing.JCheckBox();
        chkColorTalla = new javax.swing.JCheckBox();
        chkSerialColor = new javax.swing.JCheckBox();
        pnlValoresProducto = new javax.swing.JPanel();
        pnlListasPrecios = new javax.swing.JPanel();
        pnlValores = new javax.swing.JPanel();
        txtIvaCompra = new javax.swing.JTextField();
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
        lbImpoCompra = new javax.swing.JCheckBox();
        lbIvaCompra = new javax.swing.JCheckBox();
        txtImpoconsumoCompra = new javax.swing.JTextField();
        lbTelefono3 = new javax.swing.JLabel();
        txtPorcentaje = new javax.swing.JTextField();
        pnlInformacionRelacionada = new javax.swing.JPanel();
        pnlInformacion = new javax.swing.JPanel();
        lbRazon2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescripcionLarga = new javax.swing.JTextArea();
        lbTelefono1 = new javax.swing.JLabel();
        cmbClaseBuscador = new javax.swing.JComboBox();
        lbDireccion = new javax.swing.JLabel();
        txtNombreProveedor = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        lbCupo4 = new javax.swing.JLabel();
        txtReferencia = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtTipoProd = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtMarca = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtPeso = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProductosUnificados = new javax.swing.JTable();
        btnUnificar = new javax.swing.JButton();
        txtDescripcionUnificar = new javax.swing.JTextField();
        txtCodUnificar = new javax.swing.JTextField();
        lbCiudad8 = new javax.swing.JLabel();
        pnlInvisible = new javax.swing.JPanel();
        txtIdSistema = new javax.swing.JTextField();
        txtProveedor = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        btnDescargarPlantilla = new javax.swing.JButton();
        btnDescargarPlantilla1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        btnCargarPlantilla = new javax.swing.JButton();
        btnCargarImagenProductos = new javax.swing.JButton();

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
        btnNuevo.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
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
        btnActualizar.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
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
        btnEliminar.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/borrar2.png"))); // NOI18N
        btnEliminar.setText("INACTIVAR");
        btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnEliminar.setEnabled(false);
        btnEliminar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnEliminar.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnBusProd.setBackground(new java.awt.Color(247, 220, 111));
        btnBusProd.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
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

        lbInfoObligatorios.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        lbInfoObligatorios.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbInfoObligatorios.setText("Los campos marcados con asterisco (*) son obligatorios.");
        lbInfoObligatorios.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        btnGuardar.setBackground(new java.awt.Color(46, 204, 113));
        btnGuardar.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
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
                    .addComponent(lbInfoObligatorios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        pnlDatosProducto.setBackground(new java.awt.Color(255, 255, 255));

        lbNit.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lbNit.setText("Codigo: *");
        lbNit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lbNitKeyReleased(evt);
            }
        });

        txtCodigo.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtCodigo.setName("Codigo"); // NOI18N
        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoKeyTyped(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoKeyReleased(evt);
            }
        });

        lbCodigoBarras.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lbCodigoBarras.setText("Codigo de barras:");
        lbCodigoBarras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbCodigoBarrasMouseClicked(evt);
            }
        });

        txtCodigoBarras.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtCodigoBarras.setName("Referencia"); // NOI18N
        txtCodigoBarras.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoBarrasKeyTyped(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoBarrasKeyReleased(evt);
            }
        });

        lbRazon.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbRazon.setText("Descripción: *");

        txtDescripcion.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtDescripcion.setName("Descripción"); // NOI18N
        txtDescripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDescripcionKeyReleased(evt);
            }
        });

        lbTelefono.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbTelefono.setText("Grupo:");

        txtGrupo.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
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

        lbCelular.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbCelular.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbCelular.setText("Subgrupo:");

        cmbSubgrupo.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        cmbSubgrupo.setName("Subgrupo"); // NOI18N

        lbUnidadMedida.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbUnidadMedida.setText("Unidad medida:");

        txtMedida.setBackground(new java.awt.Color(220, 220, 220));
        txtMedida.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtMedida.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMedida.setName("Medida"); // NOI18N
        txtMedida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMedidaActionPerformed(evt);
            }
        });
        txtMedida.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMedidaKeyTyped(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMedidaKeyReleased(evt);
            }
        });

        lbTelefono2.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbTelefono2.setText("¿El producto maneja inventario?:");

        rdInventarioSI.setBackground(new java.awt.Color(255, 255, 255));
        grpInventario.add(rdInventarioSI);
        rdInventarioSI.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        rdInventarioSI.setSelected(true);
        rdInventarioSI.setText("SI");

        rdInventarioNO.setBackground(new java.awt.Color(255, 255, 255));
        grpInventario.add(rdInventarioNO);
        rdInventarioNO.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        rdInventarioNO.setText("NO");

        txtFCompra.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtFCompra.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFCompra.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtFCompra.setEnabled(false);

        jLabel5.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel5.setText("Fecha de la última compra:");

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));

        btnImagen.setBackground(new java.awt.Color(204, 204, 204));
        btnImagen.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
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

        pnlTiposProducto.setBackground(new java.awt.Color(255, 255, 255));
        pnlTiposProducto.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tipo de producto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 14))); // NOI18N

        grpTipoProd.add(chkNinguno);
        chkNinguno.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        chkNinguno.setSelected(true);
        chkNinguno.setText("Ninguno");
        chkNinguno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkNingunoActionPerformed(evt);
            }
        });

        grpTipoProd.add(chkColor);
        chkColor.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        chkColor.setText("Color");
        chkColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkColorActionPerformed(evt);
            }
        });

        grpTipoProd.add(chkImei);
        chkImei.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        chkImei.setText("IMEI");
        chkImei.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkImeiActionPerformed(evt);
            }
        });

        grpTipoProd.add(chkLote);
        chkLote.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        chkLote.setText("Lote/F. Vence");
        chkLote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkLoteActionPerformed(evt);
            }
        });

        grpTipoProd.add(chkSerial);
        chkSerial.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        chkSerial.setText("Serial");
        chkSerial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSerialActionPerformed(evt);
            }
        });

        grpTipoProd.add(chkTalla);
        chkTalla.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        chkTalla.setText("Talla");
        chkTalla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTallaActionPerformed(evt);
            }
        });

        grpTipoProd.add(chkColorTalla);
        chkColorTalla.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        chkColorTalla.setText("Color y Talla");
        chkColorTalla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkColorTallaActionPerformed(evt);
            }
        });

        grpTipoProd.add(chkSerialColor);
        chkSerialColor.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        chkSerialColor.setText("Serial y Color");
        chkSerialColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSerialColorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlTiposProductoLayout = new javax.swing.GroupLayout(pnlTiposProducto);
        pnlTiposProducto.setLayout(pnlTiposProductoLayout);
        pnlTiposProductoLayout.setHorizontalGroup(
            pnlTiposProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTiposProductoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTiposProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(chkColor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(chkNinguno, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                    .addComponent(chkImei, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(chkSerial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addGroup(pnlTiposProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(chkSerialColor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(chkLote, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(chkColorTalla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(chkTalla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlTiposProductoLayout.setVerticalGroup(
            pnlTiposProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTiposProductoLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(pnlTiposProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkNinguno, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkLote))
                .addGap(3, 3, 3)
                .addGroup(pnlTiposProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkColor, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkSerialColor, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(pnlTiposProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlTiposProductoLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(chkImei, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlTiposProductoLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(chkColorTalla, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlTiposProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlTiposProductoLayout.createSequentialGroup()
                        .addComponent(chkSerial, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(chkTalla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap(69, Short.MAX_VALUE)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addComponent(pnlTiposProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(70, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlTiposProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(btnImagen)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlDatosProductoLayout = new javax.swing.GroupLayout(pnlDatosProducto);
        pnlDatosProducto.setLayout(pnlDatosProductoLayout);
        pnlDatosProductoLayout.setHorizontalGroup(
            pnlDatosProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatosProductoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDatosProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDatosProductoLayout.createSequentialGroup()
                        .addGroup(pnlDatosProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbNit)
                            .addComponent(lbRazon)
                            .addComponent(lbTelefono)
                            .addComponent(lbUnidadMedida))
                        .addGap(10, 10, 10)
                        .addGroup(pnlDatosProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDescripcion)
                            .addGroup(pnlDatosProductoLayout.createSequentialGroup()
                                .addGroup(pnlDatosProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCodigo, 120, 191, Short.MAX_VALUE)
                                    .addComponent(txtGrupo, 120, 191, Short.MAX_VALUE)
                                    .addComponent(txtMedida, 120, 191, Short.MAX_VALUE))
                                .addGroup(pnlDatosProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlDatosProductoLayout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addGroup(pnlDatosProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lbCodigoBarras)
                                            .addComponent(lbCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(pnlDatosProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cmbSubgrupo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtCodigoBarras, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(pnlDatosProductoLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lbTelefono2)
                                        .addGap(10, 10, 10)
                                        .addComponent(rdInventarioSI)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(rdInventarioNO))))))
                    .addGroup(pnlDatosProductoLayout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlDatosProductoLayout.setVerticalGroup(
            pnlDatosProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatosProductoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDatosProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lbNit)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbCodigoBarras)
                    .addComponent(txtCodigoBarras, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(pnlDatosProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lbRazon)
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(pnlDatosProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lbTelefono)
                    .addComponent(txtGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbCelular)
                    .addComponent(cmbSubgrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(pnlDatosProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDatosProductoLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(pnlDatosProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(lbUnidadMedida)
                            .addComponent(txtMedida, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlDatosProductoLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlDatosProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(lbTelefono2)
                            .addComponent(rdInventarioSI, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdInventarioNO, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDatosProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel5)
                    .addComponent(txtFCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Datos del producto", pnlDatosProducto);

        pnlValoresProducto.setBackground(new java.awt.Color(255, 255, 255));

        pnlListasPrecios.setBackground(new java.awt.Color(255, 255, 255));
        pnlListasPrecios.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lista de precios", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 13))); // NOI18N

        javax.swing.GroupLayout pnlListasPreciosLayout = new javax.swing.GroupLayout(pnlListasPrecios);
        pnlListasPrecios.setLayout(pnlListasPreciosLayout);
        pnlListasPreciosLayout.setHorizontalGroup(
            pnlListasPreciosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlListasPreciosLayout.setVerticalGroup(
            pnlListasPreciosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 234, Short.MAX_VALUE)
        );

        pnlValores.setBackground(new java.awt.Color(255, 255, 255));
        pnlValores.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

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
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIvaCompraKeyTyped(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIvaCompraKeyReleased(evt);
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

        lbImpoCompra.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbImpoCompra.setText("Impoconsumo:");
        lbImpoCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lbImpoCompraActionPerformed(evt);
            }
        });

        lbIvaCompra.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbIvaCompra.setText("IVA:");
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
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtImpoconsumoCompraKeyTyped(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtImpoconsumoCompraKeyReleased(evt);
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

        javax.swing.GroupLayout pnlValoresLayout = new javax.swing.GroupLayout(pnlValores);
        pnlValores.setLayout(pnlValoresLayout);
        pnlValoresLayout.setHorizontalGroup(
            pnlValoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlValoresLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlValoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlValoresLayout.createSequentialGroup()
                        .addComponent(lbTelefono3)
                        .addGap(18, 18, 18)
                        .addComponent(txtPorcentaje, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlValoresLayout.createSequentialGroup()
                        .addGroup(pnlValoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lbIvaCompra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbDepartamento4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbDepartamento2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbDepartamento1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbDepartamento3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlValoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlValoresLayout.createSequentialGroup()
                                .addGroup(pnlValoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtMaximo)
                                    .addComponent(txtMinima, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCosto1, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
                                    .addComponent(txtIvaCompra))
                                .addGap(15, 15, 15)
                                .addGroup(pnlValoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(pnlValoresLayout.createSequentialGroup()
                                        .addComponent(lbImpoCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtImpoconsumoCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(45, 45, 45))
                                    .addGroup(pnlValoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtUtilidad1, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtUtilidad2, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlValoresLayout.createSequentialGroup()
                                            .addComponent(lbCupo1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addComponent(txtCosto, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(211, Short.MAX_VALUE))
        );
        pnlValoresLayout.setVerticalGroup(
            pnlValoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlValoresLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(pnlValoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lbDepartamento1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlValoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCosto1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbCupo1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(3, 3, 3)
                .addGroup(pnlValoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtMinima)
                    .addComponent(txtUtilidad1)
                    .addComponent(lbDepartamento2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(pnlValoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtUtilidad2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtMaximo, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbDepartamento4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(pnlValoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbDepartamento3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCosto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(pnlValoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlValoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtIvaCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbImpoCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtImpoconsumoCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbIvaCompra, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(pnlValoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbTelefono3, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(txtPorcentaje))
                .addGap(10, 10, 10))
        );

        txtMinimo.getAccessibleContext().setAccessibleName("");

        javax.swing.GroupLayout pnlValoresProductoLayout = new javax.swing.GroupLayout(pnlValoresProducto);
        pnlValoresProducto.setLayout(pnlValoresProductoLayout);
        pnlValoresProductoLayout.setHorizontalGroup(
            pnlValoresProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlValoresProductoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlValoresProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlListasPrecios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlValores, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlValoresProductoLayout.setVerticalGroup(
            pnlValoresProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlValoresProductoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlValores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(pnlListasPrecios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Valores del producto", pnlValoresProducto);

        pnlInformacionRelacionada.setBackground(new java.awt.Color(255, 255, 255));

        pnlInformacion.setBackground(new java.awt.Color(255, 255, 255));

        lbRazon2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbRazon2.setText("Descripción larga:");

        jScrollPane2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jScrollPane2KeyReleased(evt);
            }
        });

        txtDescripcionLarga.setColumns(20);
        txtDescripcionLarga.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtDescripcionLarga.setLineWrap(true);
        txtDescripcionLarga.setRows(2);
        txtDescripcionLarga.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDescripcionLargaKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(txtDescripcionLarga);

        lbTelefono1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
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

        lbDireccion.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbDireccion.setText("Proveedor:");

        txtNombreProveedor.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtNombreProveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreProveedorKeyReleased(evt);
            }
        });

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

        lbCupo4.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbCupo4.setText("Referencia:");

        txtReferencia.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtReferencia.setName("Referencia"); // NOI18N
        txtReferencia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtReferenciaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtReferenciaKeyTyped(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel1.setText("Tipo producto:");

        txtTipoProd.setBackground(new java.awt.Color(220, 220, 220));
        txtTipoProd.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
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

        jLabel2.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel2.setText("Marca:");

        txtMarca.setBackground(new java.awt.Color(220, 220, 220));
        txtMarca.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtMarca.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMarca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMarcaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMarcaKeyReleased(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel3.setText("Peso en Kg:");

        txtPeso.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtPeso.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPeso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPesoKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout pnlInformacionLayout = new javax.swing.GroupLayout(pnlInformacion);
        pnlInformacion.setLayout(pnlInformacionLayout);
        pnlInformacionLayout.setHorizontalGroup(
            pnlInformacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInformacionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlInformacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbCupo4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbRazon2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbTelefono1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addGroup(pnlInformacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(pnlInformacionLayout.createSequentialGroup()
                        .addGroup(pnlInformacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTipoProd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlInformacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtReferencia, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cmbClaseBuscador, javax.swing.GroupLayout.Alignment.LEADING, 0, 181, Short.MAX_VALUE)))
                        .addGap(20, 20, 20)
                        .addGroup(pnlInformacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(13, 13, 13)
                        .addGroup(pnlInformacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtPeso, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                            .addComponent(txtMarca, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                            .addComponent(txtNombreProveedor))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        pnlInformacionLayout.setVerticalGroup(
            pnlInformacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInformacionLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(pnlInformacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbRazon2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(pnlInformacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlInformacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lbTelefono1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmbClaseBuscador))
                    .addComponent(lbDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombreProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(pnlInformacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbCupo4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlInformacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtReferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(3, 3, 3)
                .addGroup(pnlInformacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(txtPeso, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(txtTipoProd, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addGap(10, 10, 10))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Códigos relacionados", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 13))); // NOI18N

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

        btnUnificar.setBackground(new java.awt.Color(46, 204, 113));
        btnUnificar.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        btnUnificar.setText("AGREGAR");
        btnUnificar.setEnabled(false);
        btnUnificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUnificarActionPerformed(evt);
            }
        });

        txtDescripcionUnificar.setEditable(false);
        txtDescripcionUnificar.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtDescripcionUnificar.setName("Descripción"); // NOI18N
        txtDescripcionUnificar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDescripcionUnificarKeyReleased(evt);
            }
        });

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
        lbCiudad8.setText("Código:");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 669, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(lbCiudad8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCodUnificar, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDescripcionUnificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUnificar, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(5, 5, 5))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbCiudad8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCodUnificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtDescripcionUnificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnUnificar)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlInformacionRelacionadaLayout = new javax.swing.GroupLayout(pnlInformacionRelacionada);
        pnlInformacionRelacionada.setLayout(pnlInformacionRelacionadaLayout);
        pnlInformacionRelacionadaLayout.setHorizontalGroup(
            pnlInformacionRelacionadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlInformacionRelacionadaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlInformacionRelacionadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlInformacionRelacionadaLayout.createSequentialGroup()
                        .addComponent(pnlInformacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlInformacionRelacionadaLayout.setVerticalGroup(
            pnlInformacionRelacionadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInformacionRelacionadaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlInformacion, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Información relacionada", pnlInformacionRelacionada);

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
                .addGap(0, 0, 0)
                .addGroup(pnlInvisibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIdSistema, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Plantillas para descargar", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 13))); // NOI18N

        btnDescargarPlantilla.setBackground(new java.awt.Color(204, 204, 204));
        btnDescargarPlantilla.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        btnDescargarPlantilla.setText("Plantilla para creación productos  ");
        btnDescargarPlantilla.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnDescargarPlantilla.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnDescargarPlantilla.setMargin(new java.awt.Insets(2, 14, 2, 5));

        btnDescargarPlantilla1.setBackground(new java.awt.Color(204, 204, 204));
        btnDescargarPlantilla1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        btnDescargarPlantilla1.setText("<html><center>Plantilla para creación productos<br>con inventario inicial</center></html>");
        btnDescargarPlantilla1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnDescargarPlantilla1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnDescargarPlantilla1.setMargin(new java.awt.Insets(2, 14, 2, 5));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Accesibilidades", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 14))); // NOI18N

        btnCargarPlantilla.setBackground(new java.awt.Color(46, 204, 113));
        btnCargarPlantilla.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnCargarPlantilla.setText("Cargar plantilla ");
        btnCargarPlantilla.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnCargarPlantilla.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnCargarPlantilla.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnCargarPlantilla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarPlantillaActionPerformed(evt);
            }
        });

        btnCargarImagenProductos.setBackground(new java.awt.Color(46, 204, 113));
        btnCargarImagenProductos.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnCargarImagenProductos.setText("<html><center>Relacionar imagen<br>a los productos</center></html>");
        btnCargarImagenProductos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnCargarImagenProductos.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnCargarImagenProductos.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnCargarImagenProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarImagenProductosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCargarPlantilla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCargarImagenProductos))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(btnCargarPlantilla, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(btnCargarImagenProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnDescargarPlantilla, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnDescargarPlantilla1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(btnDescargarPlantilla, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDescargarPlantilla1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlFormularioLayout = new javax.swing.GroupLayout(pnlFormulario);
        pnlFormulario.setLayout(pnlFormularioLayout);
        pnlFormularioLayout.setHorizontalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlFormularioLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 716, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(pnlFormularioLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pnlInvisible, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlFormularioLayout.setVerticalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 545, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlFormularioLayout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(5, 5, 5)
                .addComponent(pnlInvisible, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
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
            .addGroup(layout.createSequentialGroup()
                .addComponent(scrFormulario)
                .addGap(4, 4, 4))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyTyped

    }//GEN-LAST:event_txtCodigoKeyTyped

    private void txtCodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            ModeloProducto nodo = daoProducto.obtenerDatosProducto(txtCodigo.getText().replace("'", "//"), Bodegas.predeterminada());
            if (nodo.getCodigo() == null) {
                txtDescripcion.requestFocus();
            } else {
                cargarProducto(txtCodigo.getText());
            }
        } else {
            panelListas.setDatosProducto(txtIdSistema.getText(), txtCodigo.getText(), txtDescripcion.getText());
            txtCosto1.setText(this.simbolo + " 0");
        }
    }//GEN-LAST:event_txtCodigoKeyReleased

    private void txtDescripcionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtDescripcionLarga.requestFocus();
        } else {

            panelListas.setDatosProducto(txtIdSistema.getText(), txtCodigo.getText(), txtDescripcion.getText());
        }
    }//GEN-LAST:event_txtDescripcionKeyReleased

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        txtCodigo.setEnabled(true);
        btnImagen.setEnabled(false);
        lbFoto.setIcon(null);
        repaint();

        chkNinguno.setSelected(true);
        lbIvaCompra.setSelected(false);
        txtIvaCompra.setEnabled(false);

        txtImpoconsumoCompra.setEnabled(false);

        rdInventarioSI.setSelected(true);

        limpiar(pnlDatosProducto);
        limpiar(pnlValores);
        limpiar(pnlInformacion);
        panelListas.limpiar();

        while (true) {
            try {
                ((DefaultTableModel) tblProductosUnificados.getModel()).removeRow(0);
            } catch (Exception e) {
                break;
            }
        }

        txtGrupo.setSelectedIndex(0);
        cargarSubgrupos();
        cmbClaseBuscador.setSelectedIndex(0);
        txtMinima.setText("20");
        txtMaximo.setText("30");

        if (instancias.getConfiguraciones().isRestaurante()) {
            nodoConf = instancias.getSql().getDatosConfiguracion();
            txtImpoconsumoCompra.setText("0");
            txtIvaCompra.setText("0");
        } else {
            txtIvaCompra.setText("0");
        }

        String consecutivo = "";
        int num = Integer.parseInt(instancias.getSql().getNumConsecutivo("PROD")[0].toString());
        consecutivo = String.valueOf(num);
        for (int i = 0; i < 8 - String.valueOf(num).length(); i++) {
            consecutivo = "0" + consecutivo;
        }

        txtDescripcionLarga.setText("");
        txtIdSistema.setText("PROD-" + consecutivo);
        panelListas.setDatosProducto(txtIdSistema.getText(), "", "");
        txtCosto.setText(this.simbolo + " 0");
        btnActualizar.setEnabled(false);
        btnGuardar.setEnabled(true);
        txtCodUnificar.setEditable(false);
        btnUnificar.setEnabled(false);
        productoCargado = null;
        actualizarBotonEstadoProducto();
        jTabbedPane1.setSelectedIndex(0);
        lbNit.requestFocus();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        String idSistema = txtIdSistema.getText();
        String grupo = grupoSeleccionado();
        String tipoProducto = tipoProductoSeleccionado();
        BigDecimal costo = leerCosto();
        ModeloProducto existente = daoProducto.obtenerDatosProducto(idSistema, Bodegas.predeterminada());
        ModeloProducto datos = armarProducto(existente, idSistema, grupo, existente.getUsuario(), tipoProducto, costoOCero(costo));

        if (!squemaProductos.validarModificar(datos, panelListas.getPrecioPrincipal(), costo,
                existeReferencia(datos.getReferencia(), idSistema), requiereUnidad(),
                lbIvaCompra.isSelected(), lbImpoCompra.isSelected())) {
            return;
        }

        normalizarValores();
        ModeloProducto producto = armarProducto(existente, idSistema, grupo, existente.getUsuario(), tipoProducto, costo);

        if (!daoProducto.modificarProducto(producto)) {
            ControladorAlertas.alertFail("Error al modificar el producto");
            return;
        }

        ControladorAlertas.alertSuccess("Producto modificado con éxito");
        btnNuevoActionPerformed(evt);
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        if (productoCargado == null) {
            ControladorAlertas.alert("Debe cargar un producto");
            return;
        }

        String codigo = productoCargado.getCodigo();

        if (productoInactivo()) {
            if (!ControladorAlertas.option("¿Activar este registro?")) {
                return;
            }

            if (!instancias.getSql().activarProducto(codigo)) {
                ControladorAlertas.alertFail("No se pudo activar el producto");
                return;
            }

            ControladorAlertas.alertSuccess("Producto activado con éxito");
            btnNuevoActionPerformed(evt);
            return;
        }

        if (!ControladorAlertas.option("¿Inactivar este registro?")) {
            return;
        }

        if (estaEnCongelada(productoCargado.getIdSistema())) {
            ControladorAlertas.alert(instancias.getConfiguraciones().isRestaurante()
                    ? "¡El producto no se puede inactivar porque esta cargado en una mesa!"
                    : "¡El producto no se puede inactivar porque esta cargado en una congelada!");
            return;
        }

        if (!instancias.getSql().eliminarProducto(codigo)) {
            ControladorAlertas.alertFail("No se pudo inactivar el producto");
            return;
        }

        ControladorAlertas.alertSuccess("Producto inactivado con éxito");
        btnNuevoActionPerformed(evt);
    }//GEN-LAST:event_btnEliminarActionPerformed

    private boolean productoInactivo() {
        return productoCargado != null && instancias.getSql().getProdActivo(productoCargado.getCodigo());
    }

    private boolean estaEnCongelada(String idSistema) {
        for (Object[] congelada : instancias.getSql().getDatosCongelada()) {
            if (idSistema.equals(String.valueOf(congelada[7]))) {
                return true;
            }
        }
        return false;
    }

    private void actualizarBotonEstadoProducto() {
        btnEliminar.setEnabled(productoCargado != null);

        if (productoInactivo()) {
            btnEliminar.setText("ACTIVAR");
            btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/agregar.png")));
        } else {
            btnEliminar.setText("INACTIVAR");
            btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/borrar2.png")));
        }
    }

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

    private void txtGrupoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_txtGrupoItemStateChanged
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            cargarSubgrupos();
        }
    }//GEN-LAST:event_txtGrupoItemStateChanged

    private void txtIvaCompraKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIvaCompraKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtMinima.requestFocus();
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

    private void txtUtilidad1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUtilidad1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUtilidad1ActionPerformed

    private void txtCodUnificarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodUnificarKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            String codigo = txtCodUnificar.getText().replace("'", "//");
            ModeloProducto nodo = null;

            String CodigoProd = "";
            if (codigo.equals("")) {
                CodigoProd = "";
            } else {
                nodo = daoProducto.obtenerDatosProducto(codigo, Bodegas.predeterminada());
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
            ControladorAlertas.alert("Seleccione un producto");
        }
    }//GEN-LAST:event_popBorrarActionPerformed

    private void txtIvaCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIvaCompraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIvaCompraActionPerformed

    private void cmbClaseBuscadorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbClaseBuscadorItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbClaseBuscadorItemStateChanged

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
                metodos.montarImagenOriginal(FROM.toString(), TO.toString());
                mostrarFotoProducto(TO.toString());
            } catch (Exception ex) {
                ControladorAlertas.alertFail("Hubo un error al cargar el archivo");
            }
        }
    }//GEN-LAST:event_btnImagenActionPerformed

    private void mostrarFotoProducto(String ruta) {
        PanelZoomImagen.mostrarEnEtiqueta(lbFoto, new java.io.File(ruta));
    }

    private void chkNingunoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkNingunoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkNingunoActionPerformed

    private void chkImeiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkImeiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkImeiActionPerformed

    private void chkLoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkLoteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkLoteActionPerformed

    private void txtIdSistemaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdSistemaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdSistemaKeyReleased

    private void txtIdSistemaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdSistemaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdSistemaKeyTyped

    private void txtCodigoBarrasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoBarrasKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            ModeloProducto nodo = daoProducto.obtenerDatosProducto(txtCodigoBarras.getText().replace("'", "//"), Bodegas.predeterminada());
            if (nodo.getCodigo() == null) {
                txtDescripcion.requestFocus();
            } else {
                cargarProducto(txtCodigoBarras.getText());
            }
        } else {
            panelListas.setDatosProducto(txtIdSistema.getText(), txtCodigo.getText().isEmpty() ? txtCodigoBarras.getText() : txtCodigo.getText(), txtDescripcion.getText());
            txtCosto1.setText(this.simbolo + " 0");
        }
    }//GEN-LAST:event_txtCodigoBarrasKeyReleased

    private void txtCodigoBarrasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoBarrasKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoBarrasKeyTyped

    private void lbCodigoBarrasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbCodigoBarrasMouseClicked

    }//GEN-LAST:event_lbCodigoBarrasMouseClicked

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
        String grupo = grupoSeleccionado();
        String tipoProducto = tipoProductoSeleccionado();
        BigDecimal costo = leerCosto();
        ModeloProducto datos = armarProducto(null, txtIdSistema.getText(), grupo, USUARIO_CREACION, tipoProducto, costoOCero(costo));

        if (!squemaProductos.validarGuardar(datos, panelListas.getPrecioPrincipal(), costo,
                existeCodigo(codigoBusqueda()), existeReferencia(datos.getReferencia(), null), requiereUnidad(),
                lbIvaCompra.isSelected(), lbImpoCompra.isSelected())) {
            return;
        }

        normalizarValores();
        ModeloProducto producto = armarProducto(null, PREFIJO_PRODUCTO + siguienteConsecutivo(), grupo, USUARIO_CREACION, tipoProducto, costo);

        if (!daoProducto.agregarProducto(producto)) {
            ControladorAlertas.alertFail("Error al guardar el producto");
            return;
        }

        if (!instancias.getSql().aumentarConsecutivo(CONSECUTIVO_PRODUCTO, numeroConsecutivo() + 1)) {
            ControladorAlertas.alertFail("Error al guardar en el consecutivo del producto");
        }

        try {
            servicioActualizacionPonderado.crearPonderadoInicial(producto, costo, instancias.getUsuario());
        } catch (SQLException ex) {
            Logger.getLogger(VistaProductos.class.getName()).log(Level.SEVERE, null, ex);
        }

        lbNit.requestFocus();
        ControladorAlertas.alertSuccess("Producto registrado con éxito");
        btnNuevoActionPerformed(evt);
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void lbImpoCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lbImpoCompraActionPerformed
        if (lbImpoCompra.isSelected()) {
            txtImpoconsumoCompra.setEnabled(true);
            txtImpoconsumoCompra.setText("0");
        } else {
            txtImpoconsumoCompra.setEnabled(false);
            nodoConf = instancias.getSql().getDatosConfiguracion();
            txtImpoconsumoCompra.setText("0");
        }
    }//GEN-LAST:event_lbImpoCompraActionPerformed

    private void lbIvaCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lbIvaCompraActionPerformed
        if (lbIvaCompra.isSelected()) {
            txtIvaCompra.setEnabled(true);
            txtIvaCompra.setText("0");
        } else {
            txtIvaCompra.setEnabled(false);
            txtIvaCompra.setText("0");
        }
    }//GEN-LAST:event_lbIvaCompraActionPerformed

    private void txtImpoconsumoCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtImpoconsumoCompraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtImpoconsumoCompraActionPerformed

    private void txtImpoconsumoCompraKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtImpoconsumoCompraKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtMinima.requestFocus();
        }
    }//GEN-LAST:event_txtImpoconsumoCompraKeyReleased

    private void txtImpoconsumoCompraKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtImpoconsumoCompraKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtImpoconsumoCompraKeyTyped

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        cargarProveedor("");
    }//GEN-LAST:event_jButton2ActionPerformed


    private void chkTallaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTallaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkTallaActionPerformed

    private void chkColorTallaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkColorTallaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkColorTallaActionPerformed

    private void txtProveedorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProveedorKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtIvaCompra.requestFocus();
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
            ControladorAlertas.alertFail("No a cargado un producto para unificar");
            return;
        }

        cargarProductoTabla(txtIdSistema.getText(), txtCodUnificar.getText());

        txtCodUnificar.setText("");
        txtDescripcionUnificar.setText("");
    }//GEN-LAST:event_btnUnificarActionPerformed

    private void txtDescripcionLargaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionLargaKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            cmbClaseBuscador.requestFocus();
        }
    }//GEN-LAST:event_txtDescripcionLargaKeyReleased

    private void jScrollPane2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jScrollPane2KeyReleased

    }//GEN-LAST:event_jScrollPane2KeyReleased

    private void chkSerialColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSerialColorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkSerialColorActionPerformed

    private void btnCargarPlantillaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarPlantillaActionPerformed
        cargarDesdeExcel();
    }//GEN-LAST:event_btnCargarPlantillaActionPerformed

    private void btnCargarImagenProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarImagenProductosActionPerformed
        VistaCargarImagenesProducto fotos = new VistaCargarImagenesProducto(null, true, null, "");
        fotos.setLocationRelativeTo(null);
        fotos.setVisible(true);
    }//GEN-LAST:event_btnCargarImagenProductosActionPerformed

    private void cargarDesdeExcel() {
        JFileChooser abrir = new JFileChooser();
        abrir.setDialogTitle("Seleccione la plantilla con los productos");
        abrir.setFileFilter(new FileNameExtensionFilter("Excel (*.xls, *.xlsx)", "xls", "xlsx"));
        if (abrir.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) {
            return;
        }

        File archivo = abrir.getSelectedFile().getAbsoluteFile();
        if (!ControladorAlertas.option("¿Desea crear los productos del archivo " + archivo.getName() + "?")) {
            return;
        }

        ResultadoImportacionProductos resultado;
        setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.WAIT_CURSOR));
        try {
            resultado = new ServicioImportacionProductos().importar(archivo);
        } catch (IOException | SQLException ex) {
            Logger.getLogger(VistaProductos.class.getName()).log(Level.SEVERE, null, ex);
            ControladorAlertas.alertFail("No se pudo procesar el archivo: " + ex.getMessage());
            return;
        } finally {
            setCursor(java.awt.Cursor.getDefaultCursor());
        }

        String base = archivo.getName().replaceFirst("(?i)\\.xlsx?$", "");
        String marca = new java.text.SimpleDateFormat("yyyyMMdd_HHmmss").format(new java.util.Date());
        File archivoErrores = new File(archivo.getParentFile(), base + "_ERRORES_" + marca + ".xls");
        File archivoRepetidos = new File(archivo.getParentFile(), base + "_REPETIDOS_" + marca + ".xls");

        List<Object> detalles = new ArrayList<>();
        detalles.add("Registros leídos: " + resultado.getFilasLeidas());
        detalles.add("Productos guardados con éxito: " + resultado.getGuardados());
        detalles.add("Productos con errores: " + resultado.getErrores().size());
        detalles.add("Productos repetidos: " + resultado.getRepetidos().size());

        try {
            if (!resultado.getErrores().isEmpty()) {
                EscritorProductosRechazados.escribir(archivoErrores, resultado.isConInventario(), resultado.getErrores());
                detalles.add("Excel de errores: " + archivoErrores.getPath());
            }
            if (!resultado.getRepetidos().isEmpty()) {
                EscritorProductosRechazados.escribir(archivoRepetidos, resultado.isConInventario(), resultado.getRepetidos());
                detalles.add("Excel de repetidos: " + archivoRepetidos.getPath());
            }
        } catch (IOException ex) {
            Logger.getLogger(VistaProductos.class.getName()).log(Level.SEVERE, null, ex);
            detalles.add("No se pudieron generar los Excel de errores o repetidos: " + ex.getMessage());
        }

        agregarSeccion(detalles, "Errores:", resultado.getErrores());
        agregarSeccion(detalles, "Repetidos:", resultado.getRepetidos());
        if (!resultado.getAvisos().isEmpty()) {
            detalles.add(" ");
            detalles.add("Productos creados sin inventario (cárguelo desde Inventario inicial):");
            detalles.addAll(resultado.getAvisos());
        }

        if (resultado.getErrores().isEmpty() && resultado.getRepetidos().isEmpty() && resultado.getAvisos().isEmpty()) {
            ControladorAlertas.alertSuccess("Se guardaron con éxito los " + resultado.getGuardados() + " productos del archivo");
        } else {
            ControladorAlertas.alertaGrandeListado("Resultado de la carga de productos", detalles, false);
        }

        btnNuevoActionPerformed(null);
    }

    private void agregarSeccion(List<Object> detalles, String titulo, List<FilaImportacionRechazada> filas) {
        if (filas.isEmpty()) {
            return;
        }

        detalles.add(" ");
        detalles.add(titulo);
        for (FilaImportacionRechazada fila : filas) {
            detalles.add("Fila " + fila.getFila().getNumeroFila() + ": " + fila.getMotivo());
        }
    }

    public void ventanaTipoProd(String nit) {
        VistaBuscadorCatalogo buscar = BuscadoresCatalogo.tiposProductos(instancias.getMenu(), rootPaneCheckingEnabled);
        buscar.setLocationRelativeTo(null);
        instancias.setCampoActual(txtTipoProd);
        txtMedida.requestFocus();
        buscar.noEncontrado(nit);
        buscar.show();
    }

    public void ventanaMedidas(String nit) {
        VistaBuscadorCatalogo buscar = BuscadoresCatalogo.medidas(instancias.getMenu(), rootPaneCheckingEnabled);
        buscar.setLocationRelativeTo(null);
        instancias.setCampoActual(txtMedida);
        txtMedida.requestFocus();
        buscar.noEncontrado(nit);
        buscar.show();
    }

    public void ventanaMarcas(String nit) {
        VistaBuscadorCatalogo buscar = BuscadoresCatalogo.marcas(instancias.getMenu(), rootPaneCheckingEnabled);
        buscar.setLocationRelativeTo(null);
        instancias.setCampoActual(txtMarca);
        txtMarca.requestFocus();
        buscar.noEncontrado(nit);
        buscar.show();
    }

    public void cargarProductoTabla(String cod1, String cod2) {
        ModeloProducto nodo = daoProducto.obtenerDatosProducto(cod2, Bodegas.predeterminada());
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
        VistaBuscadorContactos buscar = new VistaBuscadorContactos(instancias.getMenu(), false, true, null, "");
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
            ControladorAlertas.alert("Debe ingresar el codigo");
        } else {
            ModeloProducto nodo = daoProducto.obtenerDatosProducto(cod.replace("'", "//"), Bodegas.predeterminada());

            if (nodo.getCodigo() != null) {

                btnImagen.setEnabled(true);

                txtIdSistema.setText(nodo.getIdSistema());
                panelListas.setProducto(nodo);
                txtCodigo.setText(nodo.getCodigo());

                if (nodo.getCodigo().equals("IMP01")) {
                    txtCodigo.setEnabled(false);
                }

                txtCodigoBarras.setText(nodo.getCodigoBarras());
                txtMarca.setText(nodo.getMarca());
                txtTipoProd.setText(nodo.getTipoProd());
                txtPeso.setText(nodo.getPeso());
                txtDescripcionLarga.setText(nodo.getDescripcionLarga());

                mostrarFotoProducto(System.getProperty("user.dir") + "\\imagenes\\productos\\" + nodo.getIdSistema() + ".jpg");

                txtPorcentaje.setText(nodo.getPorcentaje());

                String tipo = "";

                try {
                    tipo = nodo.getTipoProducto();
                } catch (Exception e) {
                }

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

                seleccionarSubgrupo(nodo.getSubgrupo());

                //CARGAMOS EL IMPOCONSUMO
                if (nodo.getImpoconsumoVenta().equals(".00")) {
                    txtImpoconsumoCompra.setText("0");
                } else {
                    txtImpoconsumoCompra.setText(nodo.getImpoconsumoVenta().substring(0, nodo.getImpoconsumoVenta().length() - 3));
                }

                //CARGAMOS EL IVA
                if (nodo.getIva().equals(".00")) {
                    txtIvaCompra.setText("0");
                } else {
                    txtIvaCompra.setText(nodo.getIva().substring(0, nodo.getIva().length() - 3));
                }

                if (txtImpoconsumoCompra.getText().equals("0") || txtImpoconsumoCompra.getText().equals("0.00")) {
                    txtImpoconsumoCompra.setEnabled(false);
                } else {
                    txtImpoconsumoCompra.setEnabled(true);
                    lbImpoCompra.setSelected(true);
                }

                if (txtIvaCompra.getText().equals("0") || txtIvaCompra.getText().equals("0.00")) {
                    txtIvaCompra.setEnabled(false);
                } else {
                    txtIvaCompra.setEnabled(true);
                    lbIvaCompra.setSelected(true);
                }

                txtMinimo.setText(nodo.getMinimo());
                txtMedida.setText(nodo.getUnd());

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

                if (nodo.getIva().equals(".00")) {
                    txtIvaCompra.setText("0");
                } else {
                    txtIvaCompra.setText(nodo.getIva().substring(0, nodo.getIva().length() - 3));
                }

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

                productoCargado = nodo;
                actualizarBotonEstadoProducto();

                txtCodUnificar.setEditable(true);
                btnUnificar.setEnabled(true);

                Object[][] datos = daoProducto.obtenerProductosUnificados(nodo.getIdSistema());
                for (int i = 0; i < datos.length; i++) {
                    ((DefaultTableModel) tblProductosUnificados.getModel()).addRow(new Object[]{datos[i][0], datos[i][6], datos[i][1]});
                }

                tblProductosUnificados.getColumnModel().getColumn(0).setMinWidth(0);
                tblProductosUnificados.getColumnModel().getColumn(0).setPreferredWidth(0);
                tblProductosUnificados.getColumnModel().getColumn(0).setMaxWidth(0);

                txtDescripcion.setText(nodo.getDescripcion());

                return;
            }
            ControladorAlertas.alertFail("El producto no existe");
        }
    }

    public void cargarProductos(Object[][] productos) {
        for (int i = 0; i < productos.length; i++) {
            String codigo = productos[i][0].toString();
            cargarProductoTabla(txtCodigo.getText(), codigo);
        }
    }

    public void ventanaProductos(String codigo) {
        VistaBuscadorProductos buscar = new VistaBuscadorProductos(null, true, true, "");
        buscar.setLocationRelativeTo(null);
        instancias.setBusProductos(buscar);
        instancias.setCampoActual(txtCodigo);
        txtCodigo.requestFocus();
        buscar.noEncontrado(codigo.replace("'", "//"));
        buscar.show();
    }

    public void ventanaProductosUnificar(String codigo) {
        VistaBuscadorProductos buscar = new VistaBuscadorProductos(null, rootPaneCheckingEnabled, false, "");
        buscar.setLocationRelativeTo(null);
        buscar.setOpc("producto");
        instancias.setBusProductos(buscar);
        instancias.setCampoActual(txtCodUnificar);
        txtCodUnificar.requestFocus();
        buscar.noEncontrado(codigo.replace("'", "//"));
        buscar.show();
    }

    public void utilidad() {

        if (txtCosto.getText().equals("")) {
            txtCosto.setText(this.simbolo + " 0");
        }

        if (txtIvaCompra.getText().equals("")) {
            txtIvaCompra.setText("0");
        }

        // El IVA ya es uno solo (compra y venta): el costo base de la utilidad es el costo ingresado.
        BigDecimal compra = big.getMoneda(txtCosto.getText());
        BigDecimal utilidades1, utilidades2;

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

        // La utilidad de cada lista de precios se calcula en la tabla de listas a partir del costo
        panelListas.setCostoBase(compra);
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

    private ModeloProducto armarProducto(ModeloProducto existente, String idSistema, String grupo, String usuario, String tipoProducto, BigDecimal costo) {
        ModeloProducto producto = new ModeloProducto();

        producto.setIdSistema(idSistema);
        producto.setCodigo(txtCodigo.getText().replace("'", "//"));
        producto.setCodigoBarras(txtCodigoBarras.getText());
        producto.setDescripcion(txtDescripcion.getText());
        producto.setDescripcionLarga(txtDescripcionLarga.getText());
        producto.setGrupo(vacioANulo(grupo));
        producto.setSubgrupo(vacioANulo(subgrupoSeleccionado()));
        producto.setProveedor(vacioANulo(txtProveedor.getText()));
        producto.setUsuario(usuario);
        producto.setTipoProducto(tipoProducto);
        producto.setReferencia(txtReferencia.getText());
        producto.setUnd(txtMedida.getText());
        producto.setMarca(txtMarca.getText());
        producto.setTipoProd(txtTipoProd.getText());
        producto.setPeso(txtPeso.getText());
        producto.setPorcentaje(txtPorcentaje.getText());
        producto.setClaseBuscador(String.valueOf(cmbClaseBuscador.getSelectedItem()));
        producto.setManejaInventario(rdInventarioSI.isSelected());

        producto.setIva(txtIvaCompra.getText());
        producto.setImpoconsumoVenta(txtImpoconsumoCompra.getText());
        producto.setCosto(costo.toString());
        producto.setMinimo(txtMinimo.getText());
        producto.setMinima(txtMinima.getText());
        producto.setMaxima(txtMaximo.getText());

        producto.setPonderado(existente == null ? "0" : existente.getPonderado());
        producto.setTerminal(existente == null ? instancias.getTerminal() : existente.getTerminal());
        producto.setIndVentas(existente == null ? "1" : existente.getIndVentas());
        producto.setLenteMarco(existente == null ? "" : existente.getLenteMarco());
        producto.setAdesivo(existente == null ? "" : existente.getAdesivo());
        producto.setColor(existente == null ? "" : existente.getColor());
        producto.setEmpaque(existente == null ? "" : existente.getEmpaque());
        producto.setComposicion(existente == null ? "" : existente.getComposicion());
        producto.setRmb(existente == null ? "" : existente.getRmb());
        producto.setCubicaje("");
        producto.setCodContable("");
        producto.setCodArancel("");
        producto.setCantMedida("");

        panelListas.aplicarA(producto);
        return producto;
    }

    private static String vacioANulo(String valor) {
        return valor == null || valor.isEmpty() ? null : valor;
    }

    private void cargarSubgrupos() {
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        modelo.addElement(ModeloOpcionCatalogo.vacia(""));
        for (ModeloOpcionCatalogo subgrupo : daoCatalogosProducto.listarSubgrupos(grupoSeleccionado())) {
            modelo.addElement(subgrupo);
        }
        cmbSubgrupo.setModel(modelo);
    }

    private void seleccionarSubgrupo(String codigo) {
        if (codigo == null || codigo.isEmpty()) {
            cmbSubgrupo.setSelectedIndex(0);
            return;
        }

        for (int i = 0; i < cmbSubgrupo.getItemCount(); i++) {
            Object item = cmbSubgrupo.getItemAt(i);
            if (item instanceof ModeloOpcionCatalogo && codigo.equals(((ModeloOpcionCatalogo) item).getCodigo())) {
                cmbSubgrupo.setSelectedIndex(i);
                return;
            }
        }

        String nombre;
        try {
            nombre = instancias.getSql().getDatosSubGrupo(codigo);
        } catch (Exception e) {
            nombre = codigo;
        }
        ModeloOpcionCatalogo actual = new ModeloOpcionCatalogo(codigo, nombre);
        cmbSubgrupo.addItem(actual);
        cmbSubgrupo.setSelectedItem(actual);
    }

    private String subgrupoSeleccionado() {
        Object item = cmbSubgrupo.getSelectedItem();
        return item instanceof ModeloOpcionCatalogo ? ((ModeloOpcionCatalogo) item).getCodigo() : "";
    }

    private String grupoSeleccionado() {
        if (txtGrupo.getSelectedIndex() > 0) {
            return grupos[txtGrupo.getSelectedIndex() - 1][0].toString();
        }
        return "";
    }

    private String tipoProductoSeleccionado() {
        if (chkImei.isSelected()) {
            return "IMEI";
        } else if (chkLote.isSelected()) {
            return "Fecha/Lote";
        } else if (chkColor.isSelected()) {
            return "Color";
        } else if (chkSerial.isSelected()) {
            return "Serial";
        } else if (chkTalla.isSelected()) {
            return "Talla";
        } else if (chkColorTalla.isSelected()) {
            return "ColorTalla";
        } else if (chkSerialColor.isSelected()) {
            return "SerialColor";
        }
        return "";
    }

    private BigDecimal leerCosto() {
        try {
            return big.getMoneda(txtCosto.getText());
        } catch (Exception e) {
            return null;
        }
    }

    private static BigDecimal costoOCero(BigDecimal costo) {
        return costo == null ? BigDecimal.ZERO : costo;
    }

    private boolean requiereUnidad() {
        return instancias.getConfiguraciones().isFacturaElectronica();
    }

    private String codigoBusqueda() {
        return txtCodigo.getText().isEmpty() ? txtCodigoBarras.getText() : txtCodigo.getText();
    }

    private boolean existeCodigo(String codigo) {
        if (codigo.isEmpty()) {
            return false;
        }
        ModeloProducto existente = daoProducto.obtenerDatosProducto(codigo, Bodegas.predeterminada());
        return existente.getIdSistema() != null || existente.getCodigo() != null;
    }

    private boolean existeReferencia(String referencia, String idSistemaExcluido) {
        if (referencia == null || referencia.isEmpty()) {
            return false;
        }
        try {
            String encontrada = idSistemaExcluido == null
                    ? instancias.getSql().getRefenciaProducto(referencia)
                    : instancias.getSql().getRefenciaProducto(referencia, idSistemaExcluido);
            return referencia.equalsIgnoreCase(encontrada);
        } catch (Exception e) {
            return false;
        }
    }

    private void normalizarValores() {
        if (txtIvaCompra.getText().trim().isEmpty()) {
            txtIvaCompra.setText("0");
        }
        if (txtImpoconsumoCompra.getText().trim().isEmpty()) {
            txtImpoconsumoCompra.setText("0");
        }
        if (txtMinimo.getText().trim().isEmpty()) {
            txtMinimo.setText("0");
        }
    }

    private int numeroConsecutivo() {
        return Integer.parseInt(instancias.getSql().getNumConsecutivo(CONSECUTIVO_PRODUCTO)[0].toString());
    }

    private String siguienteConsecutivo() {
        return String.format("%08d", numeroConsecutivo());
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
    private javax.swing.JButton btnCargarImagenProductos;
    private javax.swing.JButton btnCargarPlantilla;
    private javax.swing.JButton btnDescargarPlantilla;
    private javax.swing.JButton btnDescargarPlantilla1;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnImagen;
    private javax.swing.JButton btnNuevo;
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
    private javax.swing.JComboBox cmbSubgrupo;
    private javax.swing.ButtonGroup grpInventario;
    private javax.swing.ButtonGroup grpTipoProd;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lbCelular;
    private javax.swing.JLabel lbCiudad8;
    private javax.swing.JLabel lbCodigoBarras;
    private javax.swing.JLabel lbCupo1;
    private javax.swing.JLabel lbCupo4;
    private javax.swing.JLabel lbDepartamento1;
    private javax.swing.JLabel lbDepartamento2;
    private javax.swing.JLabel lbDepartamento3;
    private javax.swing.JLabel lbDepartamento4;
    private javax.swing.JLabel lbDireccion;
    private javax.swing.JLabel lbFoto;
    private javax.swing.JCheckBox lbImpoCompra;
    private javax.swing.JLabel lbInfoObligatorios;
    private javax.swing.JCheckBox lbIvaCompra;
    private javax.swing.JLabel lbNit;
    private javax.swing.JLabel lbRazon;
    private javax.swing.JLabel lbRazon2;
    private javax.swing.JLabel lbTelefono;
    private javax.swing.JLabel lbTelefono1;
    private javax.swing.JLabel lbTelefono2;
    private javax.swing.JLabel lbTelefono3;
    private javax.swing.JLabel lbUnidadMedida;
    private javax.swing.JPanel pnlDatosProducto;
    private javax.swing.JPanel pnlFormulario;
    private javax.swing.JPanel pnlInformacion;
    private javax.swing.JPanel pnlInformacionRelacionada;
    private javax.swing.JPanel pnlInvisible;
    private javax.swing.JPanel pnlListasPrecios;
    private javax.swing.JPanel pnlTiposProducto;
    private javax.swing.JPanel pnlValores;
    private javax.swing.JPanel pnlValoresProducto;
    private javax.swing.JMenuItem popBorrar;
    private javax.swing.JRadioButton rdInventarioNO;
    private javax.swing.JRadioButton rdInventarioSI;
    private javax.swing.JScrollPane scrFormulario;
    private javax.swing.JTable tblProductosUnificados;
    private javax.swing.JTextField txtCodUnificar;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtCodigoBarras;
    private javax.swing.JTextField txtCosto;
    private javax.swing.JTextField txtCosto1;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextArea txtDescripcionLarga;
    private javax.swing.JTextField txtDescripcionUnificar;
    private javax.swing.JTextField txtFCompra;
    private javax.swing.JComboBox txtGrupo;
    private javax.swing.JTextField txtIdSistema;
    private javax.swing.JTextField txtImpoconsumoCompra;
    private javax.swing.JTextField txtIvaCompra;
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
    private javax.swing.JTextField txtTipoProd;
    private javax.swing.JTextField txtUtilidad1;
    private javax.swing.JTextField txtUtilidad2;
    // End of variables declaration//GEN-END:variables
}
