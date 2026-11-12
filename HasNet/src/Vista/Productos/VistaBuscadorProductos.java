package Vista.Productos;

import Vista.Buscadores.BuscadoresCatalogo;
import Vista.Buscadores.VistaBuscadorCatalogo;

import Utilidades.DatosMaestra;
import Dao.Productos.DaoBuscadorProductos;
import Dao.Productos.DaoProducto;

import Modelo.Productos.FiltroBuscadorProductos;
import Modelo.Productos.ProductoBuscador;
import Utilidades.Contactos.Bodegas;
import Utilidades.Utilidades;
import Vista.Ventas.VistaFactura;
import clases.IconCellRenderer;
import clases.Instancias;
import clases.big;
import clases.metodosGenerales;
import clases.productos.ndGrupo;
import com.mxrck.autocompleter.TextAutoCompleter;
import formularios.productos.verLogo;
import Controlador.Alertas.ControladorAlertas;
import Modelo.Productos.ModeloProducto;
import Procesos.Inventario.Servicio.ServicioActualizacionPonderado;
import java.awt.Image;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Vector;
import java.util.regex.Pattern;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.KeyStroke;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public final class VistaBuscadorProductos extends javax.swing.JDialog {

    private static final Logger LOGGER = Logger.getLogger(VistaBuscadorProductos.class.getName());

    private static final Icon ICONO_VER = escalarIcono("/imagenes/verLogo.png", 22, 18);
    private static final Icon ICONO_PLU = escalarIcono("/imagenes/PLU.png", 40, 20);

    private final DaoProducto daoProducto = new DaoProducto();
    private final DaoBuscadorProductos daoBuscadorProductos = new DaoBuscadorProductos();

    private boolean buscarInactivos;
    private FiltroBuscadorProductos filtroAplicado;

    DefaultTableModel modelo;
    TableRowSorter modeloOrdenado;
    Instancias instancias;

    private final String TIPO_BUSQUEDA;
    private final TextAutoCompleter autoCompletar;

    private String opc;
    private VistaFactura factura;
    private VistaIngreso ingreso;

    private String claseBuscador = "";
    int pos = 0;

    public String getClaseBuscador() {
        return claseBuscador;
    }

    public void setClaseBuscador(String claseBuscador) {
        this.claseBuscador = claseBuscador;
    }

    public VistaBuscadorProductos(java.awt.Frame parent, boolean modal, boolean buscarInactivos, String tipoBusqueda) {
        super(parent, modal);
        initComponents();

        ajustarAreaVisible();

        tblRegistros.setDefaultRenderer(Object.class, new IconCellRenderer());
        instancias = Instancias.getInstancias();

        TIPO_BUSQUEDA = tipoBusqueda;
        autoCompletar = new TextAutoCompleter(txtGrupo);

        cargarTablaProductos(buscarInactivos);

        this.getRootPane().registerKeyboardAction(accion("cerrar", this), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void ajustarAreaVisible() {
        java.awt.Rectangle area = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        setBounds(area);
    }

    @Override
    public void setLocationRelativeTo(java.awt.Component c) {
        ajustarAreaVisible();
    }

    @Override
    public void setVisible(boolean visible) {
        if (visible) {
            ajustarAreaVisible();
        }
        super.setVisible(visible);
    }

    private ActionListener accion(final String opc, final JDialog ventana) {
        ActionListener a = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (opc) {
                    case "cerrar":
                        ventana.dispose();
                        break;
                }
            }
        };
        return a;
    }

    public void cargarTablaProductos(boolean buscarInactivos) {
        this.buscarInactivos = buscarInactivos;
        recargarProductos();
    }

    private void recargarProductos() {
        FiltroBuscadorProductos filtro = new FiltroBuscadorProductos(buscarInactivos, claseBuscador, txtGrupo.getText(),
                TIPO_BUSQUEDA, Bodegas.predeterminada(), DatosMaestra.isPrecioVentaConIva());

        if (filtro.equals(filtroAplicado)) {
            txtId.requestFocus();
            return;
        }

        List<ProductoBuscador> productos;
        try {
            productos = daoBuscadorProductos.buscar(filtro);
            filtroAplicado = filtro;
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error al consultar los productos del buscador", ex);
            ControladorAlertas.alertFail("No fue posible consultar los productos");
            productos = new ArrayList<>();
            filtroAplicado = null;
        }

        mostrarProductos(productos);

        try {
            tblRegistros.getSelectionModel().setSelectionInterval(1, 1);
        } catch (ArrayIndexOutOfBoundsException exp) {
        }

        txtId.requestFocus();
    }

    private void mostrarProductos(List<ProductoBuscador> productos) {
        modelo = new ModeloTablaBuscadorProductos(construirFilas(productos));

        tblRegistros.setRowSorter(null);
        tblRegistros.setModel(modelo);
        configurarColumnas();

        modeloOrdenado = new TableRowSorter<>(modelo);
        tblRegistros.setRowSorter(modeloOrdenado);
    }

    private Object[][] construirFilas(List<ProductoBuscador> productos) {
        JLabel ver = new JLabel(ICONO_VER);
        JLabel plu = new JLabel(ICONO_PLU);

        Object[][] filas = new Object[productos.size()][];
        for (int i = 0; i < productos.size(); i++) {
            filas[i] = construirFila(productos.get(i), ver, plu);
        }
        return filas;
    }

    private Object[] construirFila(ProductoBuscador producto, JLabel ver, JLabel plu) {
        return new Object[]{
            producto.getCodigo(),
            producto.getDescripcion(),
            producto.getReferencia(),
            formatearPrecio(producto.getPrecio()),
            formatearInventario(producto),
            producto.getUbicacion(),
            false,
            "",
            producto.getGrupo(),
            formatearCosto(producto.getUltimoCosto()),
            producto.getIdSistema(),
            ver,
            producto.getUsuario(),
            producto.isManejaInventario() ? "1" : "0",
            producto.isTienePlu() ? plu : "",
            producto.getIndVentas(),
            producto.getDescripcionLarga()
        };
    }

    private static String formatearPrecio(String precio) {
        try {
            return big.setMoneda(new BigDecimal(precio.trim()));
        } catch (RuntimeException e) {
            return big.setMoneda(BigDecimal.ZERO);
        }
    }

    private static String formatearInventario(ProductoBuscador producto) {
        if ("FACTURA".equals(producto.getUsuario()) || !producto.isManejaInventario()) {
            return "N/A";
        }
        return Utilidades.formatearCantidadVista(producto.getFisicoInventario());
    }

    private static String formatearCosto(String costo) {
        if (costo == null) {
            return "#ERROR";
        }
        try {
            return big.setMoneda(new BigDecimal(costo.trim().replace(",", ".")));
        } catch (RuntimeException e) {
            return "SIN COSTO";
        }
    }

    private static Icon escalarIcono(String ruta, int ancho, int alto) {
        ImageIcon imagen = new ImageIcon(VistaBuscadorProductos.class.getResource(ruta));
        return new ImageIcon(imagen.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));
    }

    private RowFilter<Object, Object> filtroTexto(String texto, int columna) {
        return RowFilter.regexFilter("(?i)" + Pattern.quote(texto), columna);
    }

    private void configurarColumnas() {
        if (tblRegistros.getColumnModel().getColumnCount() > 0) {
            tblRegistros.getColumnModel().getColumn(0).setMinWidth(125);
            tblRegistros.getColumnModel().getColumn(0).setPreferredWidth(125);
            tblRegistros.getColumnModel().getColumn(0).setMaxWidth(200);
            tblRegistros.getColumnModel().getColumn(2).setMinWidth(90);
            tblRegistros.getColumnModel().getColumn(2).setPreferredWidth(90);
            tblRegistros.getColumnModel().getColumn(2).setMaxWidth(90);
            tblRegistros.getColumnModel().getColumn(3).setMinWidth(70);
            tblRegistros.getColumnModel().getColumn(3).setPreferredWidth(80);
            tblRegistros.getColumnModel().getColumn(3).setMaxWidth(130);
            tblRegistros.getColumnModel().getColumn(4).setMinWidth(50);
            tblRegistros.getColumnModel().getColumn(4).setPreferredWidth(70);
            tblRegistros.getColumnModel().getColumn(4).setMaxWidth(130);
            tblRegistros.getColumnModel().getColumn(5).setMinWidth(75);
            tblRegistros.getColumnModel().getColumn(5).setPreferredWidth(105);
            tblRegistros.getColumnModel().getColumn(5).setMaxWidth(130);
            tblRegistros.getColumnModel().getColumn(6).setMinWidth(30);
            tblRegistros.getColumnModel().getColumn(6).setPreferredWidth(30);
            tblRegistros.getColumnModel().getColumn(6).setMaxWidth(30);
            tblRegistros.getColumnModel().getColumn(7).setMinWidth(45);
            tblRegistros.getColumnModel().getColumn(7).setPreferredWidth(45);
            tblRegistros.getColumnModel().getColumn(7).setMaxWidth(45);
            tblRegistros.getColumnModel().getColumn(8).setMinWidth(0);
            tblRegistros.getColumnModel().getColumn(8).setPreferredWidth(0);
            tblRegistros.getColumnModel().getColumn(8).setMaxWidth(0);

            if (instancias.getUsuario().equals("ADMIN")) {
                tblRegistros.getColumnModel().getColumn(9).setMinWidth(70);
                tblRegistros.getColumnModel().getColumn(9).setPreferredWidth(80);
                tblRegistros.getColumnModel().getColumn(9).setMaxWidth(130);
            } else {
                tblRegistros.getColumnModel().getColumn(9).setMinWidth(0);
                tblRegistros.getColumnModel().getColumn(9).setPreferredWidth(0);
                tblRegistros.getColumnModel().getColumn(9).setMaxWidth(0);
            }

            tblRegistros.getColumnModel().getColumn(10).setMinWidth(0);
            tblRegistros.getColumnModel().getColumn(10).setPreferredWidth(0);
            tblRegistros.getColumnModel().getColumn(10).setMaxWidth(0);
            tblRegistros.getColumnModel().getColumn(11).setMinWidth(30);
            tblRegistros.getColumnModel().getColumn(11).setPreferredWidth(30);
            tblRegistros.getColumnModel().getColumn(11).setMaxWidth(30);
            tblRegistros.getColumnModel().getColumn(12).setMinWidth(0);
            tblRegistros.getColumnModel().getColumn(12).setPreferredWidth(0);
            tblRegistros.getColumnModel().getColumn(12).setMaxWidth(0);
            tblRegistros.getColumnModel().getColumn(13).setMinWidth(0);
            tblRegistros.getColumnModel().getColumn(13).setPreferredWidth(0);
            tblRegistros.getColumnModel().getColumn(13).setMaxWidth(0);
            tblRegistros.getColumnModel().getColumn(14).setMinWidth(40);
            tblRegistros.getColumnModel().getColumn(14).setPreferredWidth(40);
            tblRegistros.getColumnModel().getColumn(14).setMaxWidth(40);
            tblRegistros.getColumnModel().getColumn(15).setMinWidth(0);
            tblRegistros.getColumnModel().getColumn(15).setPreferredWidth(0);
            tblRegistros.getColumnModel().getColumn(15).setMaxWidth(0);
            tblRegistros.getColumnModel().getColumn(16).setMinWidth(0);
            tblRegistros.getColumnModel().getColumn(16).setPreferredWidth(0);
            tblRegistros.getColumnModel().getColumn(16).setMaxWidth(0);
        }
    }

    public VistaFactura getFactura() {
        return factura;
    }

    public void setFactura(VistaFactura factura) {
        this.factura = factura;
    }

    public VistaIngreso getIngreso() {
        return ingreso;
    }

    public void setIngreso(VistaIngreso ingreso) {
        this.ingreso = ingreso;
    }

    public String getOpc() {
        return opc;
    }

    public void setOpc(String opc) {
        this.opc = opc;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        filtro = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        btnLimpiarFiltroGrupo = new javax.swing.JButton();
        btnBuscarGrupos = new javax.swing.JButton();
        lbTelefono = new javax.swing.JLabel();
        txtGrupo = new javax.swing.JTextField();
        lbBuscador1 = new javax.swing.JLabel();
        lbBuscador = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblRegistros = new javax.swing.JTable();
        btnIzquierda = new javax.swing.JButton();
        btnDerecha = new javax.swing.JButton();
        btnNuevoProducto = new javax.swing.JButton();
        btnIngresar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Buscador de productos...");
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        btnLimpiarFiltroGrupo.setBackground(new java.awt.Color(204, 204, 204));
        btnLimpiarFiltroGrupo.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        btnLimpiarFiltroGrupo.setText("LIMPIAR FILTRO");
        btnLimpiarFiltroGrupo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarFiltroGrupoActionPerformed(evt);
            }
        });

        btnBuscarGrupos.setBackground(new java.awt.Color(204, 204, 204));
        btnBuscarGrupos.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        btnBuscarGrupos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar1.png"))); // NOI18N
        btnBuscarGrupos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarGruposActionPerformed(evt);
            }
        });

        lbTelefono.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbTelefono.setText("Grupo seleccionado:");

        txtGrupo.setBackground(new java.awt.Color(220, 220, 220));
        txtGrupo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtGrupo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtGrupo.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtGrupo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtGrupoFocusGained(evt);
            }
        });
        txtGrupo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtGrupoKeyReleased(evt);
            }
        });

        lbBuscador1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbBuscador1.setText("Filtro actual:");

        lbBuscador.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbBuscador.setText("Codigo");

        txtId.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txtId.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtIdMouseClicked(evt);
            }
        });
        txtId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIdKeyReleased(evt);
            }
        });

        tblRegistros.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tblRegistros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"001", "Celular", null, null, null, null, null, null, null},
                {"002", "Computador", null, null, null, null, null, null, null},
                {"003", "CPU", null, null, null, null, null, null, null},
                {"004", "Smartphone", null, null, null, null, null, null, null}
            },
            new String [] {
                "Codigo", "Descripcion", "Referencia", "P.V.P", "Inv", "Ubicación", "Seleccionar", "Cantidad", "idSistema"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblRegistros.setRowHeight(20);
        tblRegistros.getTableHeader().setReorderingAllowed(false);
        tblRegistros.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblRegistrosMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tblRegistrosMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblRegistrosMousePressed(evt);
            }
        });
        tblRegistros.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblRegistrosKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblRegistros);
        if (tblRegistros.getColumnModel().getColumnCount() > 0) {
            tblRegistros.getColumnModel().getColumn(6).setMinWidth(80);
            tblRegistros.getColumnModel().getColumn(6).setPreferredWidth(80);
            tblRegistros.getColumnModel().getColumn(6).setMaxWidth(80);
            tblRegistros.getColumnModel().getColumn(8).setMinWidth(0);
            tblRegistros.getColumnModel().getColumn(8).setPreferredWidth(0);
            tblRegistros.getColumnModel().getColumn(8).setMaxWidth(0);
        }

        btnIzquierda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/izquierda1.png"))); // NOI18N
        btnIzquierda.setToolTipText("Teclado, tecla izquierda/Filtro anterior");
        btnIzquierda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIzquierdaActionPerformed(evt);
            }
        });
        btnIzquierda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                btnIzquierdaKeyReleased(evt);
            }
        });

        btnDerecha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/derecha1.png"))); // NOI18N
        btnDerecha.setToolTipText("Teclado, tecla derecha/Filtro siguiente");
        btnDerecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDerechaActionPerformed(evt);
            }
        });
        btnDerecha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                btnDerechaKeyReleased(evt);
            }
        });

        btnNuevoProducto.setBackground(new java.awt.Color(46, 204, 113));
        btnNuevoProducto.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnNuevoProducto.setText("NUEVO PRODUCTO");
        btnNuevoProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoProductoActionPerformed(evt);
            }
        });

        btnIngresar.setBackground(new java.awt.Color(204, 204, 204));
        btnIngresar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnIngresar.setText("INGRESAR");
        btnIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtId)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(txtGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscarGrupos)
                        .addGap(2, 2, 2)
                        .addComponent(btnLimpiarFiltroGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                        .addComponent(lbBuscador1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(lbBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnIzquierda, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDerecha, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnNuevoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lbTelefono, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtGrupo, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnBuscarGrupos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLimpiarFiltroGrupo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnDerecha, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(btnIzquierda, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(lbBuscador, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                        .addComponent(lbBuscador1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(2, 2, 2)
                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnNuevoProducto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnIngresar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarActionPerformed
        txtId.setText("");
        tblRegistros.removeEditor();
        modeloOrdenado.setRowFilter(RowFilter.regexFilter("", 0));

        //REVISAR SI INGRESARON ALGUNA CANTIDAD SIN EL CHULO
        for (int i = 0; i < tblRegistros.getRowCount(); i++) {
            if (!tblRegistros.getValueAt(i, 7).equals("")) {
                tblRegistros.setValueAt(true, i, 6);
            }
        }

        //HACEMOS CONTEO DE CUANTOS PRODUCTOS SELECCIONARON
        int cantSeleccionada = 0;
        for (int i = 0; i < tblRegistros.getRowCount(); i++) {
            if ((Boolean) tblRegistros.getValueAt(i, 6)) {
                cantSeleccionada++;
            }
        }

        //CREAMOS OBJETO TIPO MATRIZ, PARA ASIGNAR CODIGO Y CANTIDAD DE LOS PRODUCTOS SELECCIONADOS
        Object[][] cantidad = new Object[cantSeleccionada][2];

        int ser = 0;
        for (int i = 0; i < tblRegistros.getRowCount(); i++) {
            if ((Boolean) tblRegistros.getValueAt(i, 6)) {
                if (tblRegistros.getValueAt(i, 7).equals("")) {
                    cantidad[ser][0] = tblRegistros.getValueAt(i, 10).toString();
                    cantidad[ser][1] = 0;
                    ser++;
                } else {
                    cantidad[ser][0] = tblRegistros.getValueAt(i, 10).toString();
                    cantidad[ser][1] = tblRegistros.getValueAt(i, 7).toString();
                    ser++;
                }
            }
        }

        //SI HAY PRODUCTOS SELECCIONADOS LOS CARGAMOS.
        if (cantSeleccionada > 0) {
            String idSeleccionado = tblRegistros.getSelectedRow() < 0 ? (String) cantidad[0][0]
                    : (String) tblRegistros.getValueAt(tblRegistros.getSelectedRow(), 10);
            entregarProductos(cantidad, idSeleccionado);
        }
    }//GEN-LAST:event_btnIngresarActionPerformed

    private void entregarProductos(Object[][] cantidad, String idSeleccionado) {
        if (opc == null) {
            opc = "";
        }

        if (opc.equals("")) {
            instancias.setValorCampo(idSeleccionado);
            this.dispose();
            metodosGenerales.presionarEnter(instancias.getCampoActual());
        } else {
            switch (opc) {
                case "factura":
                    this.dispose();
                    factura.cargarProductos1(cantidad);
                    break;
                case "cotizacion":
                    this.dispose();
                    instancias.getCotiza().cargarProductos1(cantidad);
                    break;
                case "ingreso":
                    this.dispose();
                    instancias.getIngresos().cargarProductos(cantidad);
                    break;
                case "ordenCompra":
                    this.dispose();
                    instancias.getOrdenCompra().cargarProductos(cantidad);
                    break;
                case "trasladoInterno":
                    this.dispose();
                    instancias.getTrasladosInternos().cargarProductos(cantidad);
                    break;
                case "ajuste":
                    this.dispose();
                    instancias.getVistaAjusteInventario().cargarProductos(cantidad);
                    break;
                case "diseño":
                    this.dispose();
                    instancias.getCosteo().cargarProductos(cantidad);
                    break;
                case "producto":
                    this.dispose();
                    instancias.getProductos().cargarProductos(cantidad);
                    break;
                case "inventarioInicial":
                    this.dispose();
                    instancias.getInventarioInicial().cargarProductos(cantidad);
                    break;
            }
        }
    }

    private void txtIdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdKeyReleased
        filtrar(evt);
    }//GEN-LAST:event_txtIdKeyReleased

    public void filtrar(java.awt.event.KeyEvent evt) {
        try {
            if ((evt.getKeyCode() == 40 || evt.getKeyCode() == 38)) {
                int aumento = -1;

                if (evt.getKeyCode() == 40) {
                    aumento = 1;
                }

                int fila = tblRegistros.getSelectionModel().getMinSelectionIndex() + aumento;
                try {

                    if (fila == tblRegistros.getRowCount() && aumento == 1) {
                        fila = 0;
                    }

                    tblRegistros.getSelectionModel().setSelectionInterval(fila, fila);
                    tblRegistros.scrollRectToVisible(tblRegistros.getCellRect(tblRegistros.getSelectedRow(), 0, true));

                } catch (ArrayIndexOutOfBoundsException exp) {
                }
                return;
            }

            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                tblRegistros.setValueAt(true, tblRegistros.getSelectedRow(), 6);
                btnIngresarActionPerformed(null);
                return;
            }
        } catch (Exception e) {
        }

        if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (pos == 5) {
                pos = 0;
            } else if (pos == 2) {
                pos = 5;
            } else {
                pos = pos + 1;
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            if (pos == 5) {
                pos = 2;
            } else if (pos == 0) {
                pos = 5;
            } else {
                pos = pos - 1;
            }
        } else {
            pos = 0;
        }

        switch (pos) {
            case 0:
                modeloOrdenado.setRowFilter(filtroTexto(txtId.getText(), 0));
                lbBuscador.setText("Codigo");
                if (tblRegistros.getRowCount() == 0) {
                    modeloOrdenado.setRowFilter(filtroTexto(txtId.getText(), 1));
                    lbBuscador.setText("Descripción");
                    if (tblRegistros.getRowCount() == 0) {
                        modeloOrdenado.setRowFilter(filtroTexto(txtId.getText(), 2));
                        lbBuscador.setText("Referencia");
                        if (tblRegistros.getRowCount() == 0) {
                            modeloOrdenado.setRowFilter(filtroTexto(txtId.getText(), 5));
                            lbBuscador.setText("Ubicación");
                        }
                    }
                }
                break;
            case 1:
                lbBuscador.setText("Descripción");
                modeloOrdenado.setRowFilter(filtroTexto(txtId.getText(), 1));
                break;
            case 2:
                lbBuscador.setText("Referencia ");
                modeloOrdenado.setRowFilter(filtroTexto(txtId.getText(), 2));
                break;
            case 5:
                lbBuscador.setText("Ubicación");
                modeloOrdenado.setRowFilter(filtroTexto(txtId.getText(), 5));
                break;
            default:
                break;
        }

        try {
            tblRegistros.getSelectionModel().setSelectionInterval(0, 0);
        } catch (ArrayIndexOutOfBoundsException exp) {
        }
    }

    private void tblRegistrosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblRegistrosMouseClicked
        int filaSeleccionada = tblRegistros.getSelectedRow();
        int columnaSeleccionada = tblRegistros.getSelectedColumn();

        if (evt.getClickCount() > 1) {
            btnIngresarActionPerformed(null);
        }

        if (evt.getClickCount() > 0 && filaSeleccionada >= 0) {
            if (columnaSeleccionada == 11) {
                verLogo verLogo = new verLogo(null, true, tblRegistros.getValueAt(filaSeleccionada, 10).toString());
                verLogo.setLocationRelativeTo(null);
                verLogo.setVisible(true);
                txtId.requestFocus();
            } else {
                boolean estadoColumna = Boolean.parseBoolean(tblRegistros.getValueAt(tblRegistros.getSelectedRow(), 6).toString());
                tblRegistros.setValueAt(!estadoColumna, filaSeleccionada, 6);
                if (estadoColumna) {
                    tblRegistros.setValueAt("", filaSeleccionada, 7);
                } else {
                    if (opc != null && !opc.isEmpty()) {
                        tblRegistros.editCellAt(tblRegistros.getSelectedRow(), 7);
                        tblRegistros.setColumnSelectionInterval(7, 7);
                        tblRegistros.transferFocus();
                        tblRegistros.setValueAt(true, tblRegistros.getSelectedRow(), 6);
                        txtId.setText("");
                    }
                }
            }
        }
    }//GEN-LAST:event_tblRegistrosMouseClicked

    private void tblRegistrosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblRegistrosKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER && tblRegistros.getSelectedColumn() == 7) {
            txtId.requestFocus();
        }

//        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
//            if (tblRegistros.getSelectedRow() > 0) {
//                tblRegistros.setValueAt(true, tblRegistros.getSelectedRow(), 6);
//                btnEsteActionPerformed(null);
//            }
//        }
        if (tblRegistros.getSelectedColumn() == 7) {
            if (!tblRegistros.getValueAt(tblRegistros.getSelectedRow(), 7).equals("")) {
                tblRegistros.setValueAt(true, tblRegistros.getSelectedRow(), 6);
                tblRegistros.removeEditor();
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            if (tblRegistros.getRowCount() > 1) {
                tblRegistros.getSelectionModel().setSelectionInterval(tblRegistros.getSelectionModel().getMinSelectionIndex() - 1, tblRegistros.getSelectionModel().getMinSelectionIndex() - 1);
                tblRegistros.setValueAt(true, tblRegistros.getSelectionModel().getMinSelectionIndex() - 1, tblRegistros.getSelectionModel().getMinSelectionIndex() - 1);
            }

            btnIngresarActionPerformed(null);
        }
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            tblRegistros.scrollRectToVisible(tblRegistros.getCellRect(tblRegistros.getSelectedRow(), 0, true));
        }
    }//GEN-LAST:event_tblRegistrosKeyReleased

    private void txtIdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtIdMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdMouseClicked

    private void btnNuevoProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoProductoActionPerformed
        registrarProducto();
    }//GEN-LAST:event_btnNuevoProductoActionPerformed

    private void registrarProducto() {
        VistaProductoRapido modal = new VistaProductoRapido(instancias.getMenu(), true);
        modal.preparar(txtId.getText(), esRegimenComun());
        modal.setVisible(true);

        if (!modal.isAceptado()) {
            txtId.requestFocus();
            return;
        }

        ModeloProducto producto = modal.getProducto();
        if (!guardarProducto(producto)) {
            return;
        }

        ControladorAlertas.alertSuccess("Producto registrado con éxito");
        entregarProductos(new Object[][]{{producto.getIdSistema(), 0}}, producto.getIdSistema());
    }

    private boolean guardarProducto(ModeloProducto producto) {
        int consecutivo = Integer.parseInt(instancias.getSql().getNumConsecutivo("PROD")[0].toString());
        producto.setIdSistema("PROD-" + String.format("%08d", consecutivo));

        if (!daoProducto.agregarProducto(producto)) {
            ControladorAlertas.alertFail("No fue posible guardar el producto");
            return false;
        }

        if (!instancias.getSql().aumentarConsecutivo("PROD", consecutivo + 1)) {
            ControladorAlertas.alertFail("Error al guardar en el consecutivo del producto");
        }

        try {
            new ServicioActualizacionPonderado().crearPonderadoInicial(producto, BigDecimal.ZERO, instancias.getUsuario());
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error al crear el ponderado inicial del producto " + producto.getIdSistema(), ex);
            ControladorAlertas.alertFail("Error al crear el costo inicial del producto");
        }
        return true;
    }

    private boolean esRegimenComun() {
        return !"SinIva".equals(instancias.getRegimen());
    }

    private void txtGrupoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGrupoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            ndGrupo nodo = instancias.getSql().getDatosGrupo2(txtGrupo.getText());

            if (nodo.getCodigo() != null) {
                recargarProductos();
            } else {
                ventanaGrupos(txtGrupo.getText());
            }
        } else {
            txtGrupo.setText("");
        }
    }//GEN-LAST:event_txtGrupoKeyReleased

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed

    }//GEN-LAST:event_formKeyPressed

    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_formKeyReleased

    private void btnDerechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDerechaActionPerformed
        KeyEvent x = new KeyEvent(this, WIDTH, WIDTH, WIDTH, KeyEvent.VK_RIGHT);
        txtIdKeyReleased(x);
    }//GEN-LAST:event_btnDerechaActionPerformed

    private void btnDerechaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnDerechaKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            KeyEvent x = new KeyEvent(this, WIDTH, WIDTH, WIDTH, KeyEvent.VK_RIGHT);
            txtIdKeyReleased(x);
        }
        if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            KeyEvent x = new KeyEvent(this, WIDTH, WIDTH, WIDTH, KeyEvent.VK_LEFT);
            txtIdKeyReleased(x);
        }
    }//GEN-LAST:event_btnDerechaKeyReleased

    private void btnIzquierdaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIzquierdaActionPerformed
        KeyEvent x = new KeyEvent(this, WIDTH, WIDTH, WIDTH, KeyEvent.VK_LEFT);
        txtIdKeyReleased(x);
    }//GEN-LAST:event_btnIzquierdaActionPerformed

    private void btnIzquierdaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnIzquierdaKeyReleased

        if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            KeyEvent x = new KeyEvent(this, WIDTH, WIDTH, WIDTH, KeyEvent.VK_LEFT);
            txtIdKeyReleased(x);
        }
        if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            KeyEvent x = new KeyEvent(this, WIDTH, WIDTH, WIDTH, KeyEvent.VK_RIGHT);
            txtIdKeyReleased(x);
        }
    }//GEN-LAST:event_btnIzquierdaKeyReleased

    private void tblRegistrosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblRegistrosMouseEntered
        int columna = tblRegistros.getSelectedColumn();

        if (evt.getClickCount() >= 1 && columna == 8) {
            txtId.setText("");
            txtId.requestFocus();
        }
    }//GEN-LAST:event_tblRegistrosMouseEntered

    private void tblRegistrosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblRegistrosMousePressed
        int columna = tblRegistros.getSelectedColumn();

        if (evt.getClickCount() >= 1 && columna == 8) {
            txtId.setText("");
            txtId.requestFocus();
        }
    }//GEN-LAST:event_tblRegistrosMousePressed

    private void btnBuscarGruposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarGruposActionPerformed
        ventanaGrupos(txtGrupo.getText());
    }//GEN-LAST:event_btnBuscarGruposActionPerformed

    private void btnLimpiarFiltroGrupoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarFiltroGrupoActionPerformed
        txtGrupo.setText("");
        recargarProductos();
    }//GEN-LAST:event_btnLimpiarFiltroGrupoActionPerformed

    private void txtGrupoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtGrupoFocusGained
        recargarProductos();
    }//GEN-LAST:event_txtGrupoFocusGained

    public void setSeleccionados(String cod) {
        for (int j = 0; j < modelo.getRowCount(); j++) {
            if (modelo.getValueAt(j, 10).equals(cod)) {
                modelo.setValueAt(true, j, 6);
                break;
            }
        }
    }

    public void ventanaGrupos(String codigo) {
        VistaBuscadorCatalogo buscar = BuscadoresCatalogo.gruposActivos(null, true, false);
        buscar.setLocationRelativeTo(null);
        instancias.setCampoActual(txtGrupo);
        txtGrupo.requestFocus();
//        cargarTablaProductos(false);
        buscar.show();
    }

    public void setSeleccionados(Vector v) {
        for (int i = 0; i < v.size(); i++) {
            for (int j = 0; j < modelo.getRowCount(); j++) {
                if (modelo.getValueAt(j, 10).equals(v.get(i))) {
                    modelo.setValueAt(true, j, 6);
                    break;
                }
            }
        }
    }

    public void noEncontrado(String codigo) {
        txtId.setText(codigo);
        modeloOrdenado.setRowFilter(filtroTexto(codigo, 1));
        try {
            tblRegistros.getSelectionModel().setSelectionInterval(0, 0);
        } catch (ArrayIndexOutOfBoundsException exp) {
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                VistaBuscadorProductos dialog = new VistaBuscadorProductos(new javax.swing.JFrame(), true, false, null);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarGrupos;
    private javax.swing.JButton btnDerecha;
    private javax.swing.JButton btnIngresar;
    private javax.swing.JButton btnIzquierda;
    private javax.swing.JButton btnLimpiarFiltroGrupo;
    private javax.swing.JButton btnNuevoProducto;
    private javax.swing.ButtonGroup filtro;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbBuscador;
    private javax.swing.JLabel lbBuscador1;
    private javax.swing.JLabel lbTelefono;
    private javax.swing.JTable tblRegistros;
    private javax.swing.JTextField txtGrupo;
    private javax.swing.JTextField txtId;
    // End of variables declaration//GEN-END:variables
}
