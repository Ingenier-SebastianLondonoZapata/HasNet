package Vista.Productos;

import Controlador.Alertas.ControladorAlertas;
import Enums.DetalleTipoProducto;
import Enums.EstadosDetalleProducto;
import Enums.TipoDocumento;
import Enums.enumBodegas;
import Modelo.Inventario.DetalleProducto;
import Modelo.Inventario.LineaInventarioInicial;
import Modelo.Productos.ModeloProducto;
import Modelo.Inventario.LineaImportacionInventarioInicial;
import Modelo.Inventario.ResultadoImportacionInventarioInicial;
import Procesos.Inventario.Servicio.ServicioImportacionInventarioInicial;
import Procesos.Inventario.Servicio.ServicioInventarioInicial;
import Procesos.Inventario.Vista.VistaMovimientoDetalleProducto;
import Utilidades.DetalleProducto.UtilidadesDetalleProducto;
import Utilidades.ImportacionesExcel.DescargadorPlantillas;
import Utilidades.Utilidades;
import clases.Instancias;
import clases.big;
import clases.metodosGenerales;
import Dao.Productos.DaoProducto;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import jxl.read.biff.BiffException;

/**
 * Inventario inicial de muchos productos a la vez. Cada producto es una fila de
 * la tabla con la cantidad a ingresar, el costo por unidad y el total; arriba
 * se agregan por código y también se puede cargar un Excel (.xls). Los
 * productos con tipo (imei, serial, color, talla, lote) guardan su detalle en
 * {@code tblDetalle} (oculta), que usa la misma ventana de detalle de siempre.
 */
public class VistaInventarioInicial extends javax.swing.JInternalFrame implements ReceptorDetallado {

    private static final int COL_CODIGO = 0;
    private static final int COL_DESCRIPCION = 1;
    private static final int COL_TIPO = 2;
    private static final int COL_CANTIDAD = 3;
    private static final int COL_COSTO = 4;
    private static final int COL_TOTAL = 5;
    private static final int COL_DETALLE = 6;
    private static final int COL_ID = 7;
    private static final int MAXIMO_ERRORES_MOSTRADOS = 60;

    private final DaoProducto daoProducto = new DaoProducto();
    private final ControladorAlertas alertas = new ControladorAlertas();
    private final ServicioInventarioInicial servicioInventarioInicial = new ServicioInventarioInicial();

    String simbolo = "";
    metodosGenerales metodos = new metodosGenerales();
    private Instancias instancias;
    private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    DefaultTableModel modeloPro1;

    // Productos de la carga (idSistema -> producto), en el orden en que se agregaron
    private final Map<String, ModeloProducto> productosCargados = new LinkedHashMap<>();
    private DefaultTableModel modeloInventario;
    private JTable tblInventario;
    private JLabel lbResumen;
    private JLabel lbCostoGeneral;
    private boolean recalculando;
    private boolean excelCargado;

    public VistaInventarioInicial() {
        initComponents();

        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
        Barra.setSize(0, 0);
        Barra.setPreferredSize(new Dimension(0, 0));
        setBorder(null);
        repaint();

        modeloPro1 = (DefaultTableModel) tblDetalle.getModel();
        instancias = Instancias.getInstancias();
        pnlInvisible.setVisible(false);
        simbolo = instancias.getSimbolo();

        construirInterfaz();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoInventarios = new javax.swing.ButtonGroup();
        scrFormulario = new javax.swing.JScrollPane();
        pnlInventarioInicial = new javax.swing.JPanel();
        pnlInvisible = new javax.swing.JPanel();
        txtProducto1 = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblDetalle = new javax.swing.JTable();
        pnlBotones = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        pnlCargarProducto = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtProducto = new javax.swing.JTextField();
        btnBusProd = new javax.swing.JButton();
        btnCargarPlantilla = new javax.swing.JButton();
        btnQuitarProducto = new javax.swing.JButton();
        btnDescargarPlantilla = new javax.swing.JButton();
        pnlTabla = new javax.swing.JPanel();

        setTitle("Factura");

        pnlInventarioInicial.setBackground(new java.awt.Color(255, 255, 255));

        txtProducto1.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        txtProducto1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtProducto1.setDisabledTextColor(new java.awt.Color(255, 0, 0));
        txtProducto1.setEnabled(false);
        txtProducto1.setName("Codigo"); // NOI18N
        txtProducto1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtProducto1KeyReleased(evt);
            }
        });

        tblDetalle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cod", "Imei", "Lote", "F.Vence", "Temp", "cant", "descripcion", "color", "talla"
            }
        ));
        jScrollPane4.setViewportView(tblDetalle);

        javax.swing.GroupLayout pnlInvisibleLayout = new javax.swing.GroupLayout(pnlInvisible);
        pnlInvisible.setLayout(pnlInvisibleLayout);
        pnlInvisibleLayout.setHorizontalGroup(
            pnlInvisibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInvisibleLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlInvisibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlInvisibleLayout.createSequentialGroup()
                        .addComponent(txtProducto1, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane4))
                .addContainerGap())
        );
        pnlInvisibleLayout.setVerticalGroup(
            pnlInvisibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInvisibleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtProducto1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pnlBotones.setBackground(new java.awt.Color(255, 255, 255));
        pnlBotones.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        btnGuardar.setBackground(new java.awt.Color(46, 204, 113));
        btnGuardar.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
        btnGuardar.setText("GUARDAR  ");
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

        btnLimpiar.setBackground(new java.awt.Color(204, 204, 204));
        btnLimpiar.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/limpiar.png"))); // NOI18N
        btnLimpiar.setText("LIMPIAR  ");
        btnLimpiar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLimpiar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnLimpiar.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlBotonesLayout = new javax.swing.GroupLayout(pnlBotones);
        pnlBotones.setLayout(pnlBotonesLayout);
        pnlBotonesLayout.setHorizontalGroup(
            pnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBotonesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnGuardar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLimpiar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlBotonesLayout.setVerticalGroup(
            pnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBotonesLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(pnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnLimpiar))
                .addGap(10, 10, 10))
        );

        pnlCargarProducto.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cargar producto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 13))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jLabel2.setText("Codigo:");
        jLabel2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jLabel2KeyReleased(evt);
            }
        });

        txtProducto.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtProducto.setName("Codigo"); // NOI18N
        txtProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtProductoKeyReleased(evt);
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

        btnCargarPlantilla.setBackground(new java.awt.Color(46, 204, 113));
        btnCargarPlantilla.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnCargarPlantilla.setText("Cargar plantilla");
        btnCargarPlantilla.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCargarPlantilla.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnCargarPlantilla.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnCargarPlantilla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarPlantillaActionPerformed(evt);
            }
        });
        btnCargarPlantilla.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                btnCargarPlantillaKeyReleased(evt);
            }
        });

        btnQuitarProducto.setBackground(new java.awt.Color(241, 148, 138));
        btnQuitarProducto.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnQuitarProducto.setText("Quitar producto");
        btnQuitarProducto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnQuitarProducto.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnQuitarProducto.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnQuitarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitarProductoActionPerformed(evt);
            }
        });
        btnQuitarProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                btnQuitarProductoKeyReleased(evt);
            }
        });

        btnDescargarPlantilla.setBackground(new java.awt.Color(46, 204, 113));
        btnDescargarPlantilla.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnDescargarPlantilla.setText("Descargar plantilla ");
        btnDescargarPlantilla.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDescargarPlantilla.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnDescargarPlantilla.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnDescargarPlantilla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDescargarPlantillaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlCargarProductoLayout = new javax.swing.GroupLayout(pnlCargarProducto);
        pnlCargarProducto.setLayout(pnlCargarProductoLayout);
        pnlCargarProductoLayout.setHorizontalGroup(
            pnlCargarProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCargarProductoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBusProd, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addComponent(btnDescargarPlantilla)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCargarPlantilla, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnQuitarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlCargarProductoLayout.setVerticalGroup(
            pnlCargarProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCargarProductoLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(pnlCargarProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtProducto, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(btnBusProd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnQuitarProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCargarProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnCargarPlantilla, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnDescargarPlantilla)))
                .addGap(9, 9, 9))
        );

        txtProducto.getAccessibleContext().setAccessibleName("");

        pnlTabla.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnlTablaLayout = new javax.swing.GroupLayout(pnlTabla);
        pnlTabla.setLayout(pnlTablaLayout);
        pnlTablaLayout.setHorizontalGroup(
            pnlTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlTablaLayout.setVerticalGroup(
            pnlTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 71, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlInventarioInicialLayout = new javax.swing.GroupLayout(pnlInventarioInicial);
        pnlInventarioInicial.setLayout(pnlInventarioInicialLayout);
        pnlInventarioInicialLayout.setHorizontalGroup(
            pnlInventarioInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlInventarioInicialLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(pnlInventarioInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(pnlBotones, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlTabla, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlCargarProducto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlInvisible, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15))
        );
        pnlInventarioInicialLayout.setVerticalGroup(
            pnlInventarioInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInventarioInicialLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlCargarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlTabla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(pnlInvisible, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        scrFormulario.setViewportView(pnlInventarioInicial);

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

    // ------------------------------------------------------------------------------------------------
    // Interfaz: la tabla de productos reemplaza los campos de un solo producto
    // ------------------------------------------------------------------------------------------------
    /**
     * Arma la pantalla de varios productos reutilizando los controles que ya
     * existen (código, buscar, guardar, limpiar). Los campos del formulario
     * anterior (conteos, costo, total) siguen declarados pero ya no se
     * muestran; se pueden borrar del diseño.
     */
    private void construirInterfaz() {
        modeloInventario = new DefaultTableModel(new Object[][]{},
                new String[]{"Código", "Descripción", "Tipo", "Cantidad", "Costo unidad", "Total costo", "Detalle", "idSistema"}) {
                    @Override
                    public boolean isCellEditable(int fila, int columna) {
                        if (columna == COL_COSTO) {
                            return true;
                        }
                        return columna == COL_CANTIDAD && String.valueOf(getValueAt(fila, COL_TIPO)).isEmpty();
                    }
                };

        tblInventario = new JTable(modeloInventario);
        tblInventario.setFont(new Font("Century Gothic", Font.PLAIN, 14));
        tblInventario.setRowHeight(26);
        tblInventario.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblInventario.getTableHeader().setReorderingAllowed(false);
        tblInventario.setSurrendersFocusOnKeystroke(true);

        int[] anchos = {120, 320, 90, 90, 120, 130, 100};
        for (int i = 0; i < anchos.length; i++) {
            tblInventario.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }
        tblInventario.getColumnModel().getColumn(COL_ID).setMinWidth(0);
        tblInventario.getColumnModel().getColumn(COL_ID).setPreferredWidth(0);
        tblInventario.getColumnModel().getColumn(COL_ID).setMaxWidth(0);

        modeloInventario.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent evento) {
                if (!recalculando && evento.getType() == TableModelEvent.UPDATE
                        && (evento.getColumn() == COL_CANTIDAD || evento.getColumn() == COL_COSTO)) {
                    recalcularFila(evento.getFirstRow());
                }
            }
        });

        tblInventario.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int fila = tblInventario.rowAtPoint(evt.getPoint());
                int columna = tblInventario.columnAtPoint(evt.getPoint());
                if (fila >= 0 && columna == COL_DETALLE && !tipoDeFila(fila).isEmpty()) {
                    abrirDetalle(fila);
                }
            }
        });

        lbResumen = new JLabel(" ");
        lbResumen.setFont(new Font("Arial", Font.PLAIN, 14));
        lbCostoGeneral = new JLabel(" ");
        lbCostoGeneral.setFont(new Font("Arial", Font.BOLD, 18));

        txtProducto.setPreferredSize(new Dimension(380, 34));
        btnBusProd.setPreferredSize(new Dimension(40, 34));

        JPanel superior = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 8));
        superior.setBackground(Color.WHITE);
        superior.add(jLabel2);
        superior.add(txtProducto);
        superior.add(btnBusProd);
        superior.add(btnCargarPlantilla);
        superior.add(btnQuitarProducto);
        superior.add(btnDescargarPlantilla);

        JPanel acciones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 4));
        acciones.setBackground(Color.WHITE);
        acciones.add(lbCostoGeneral);
        acciones.add(btnLimpiar);
        acciones.add(btnGuardar);

        JPanel inferior = new JPanel(new BorderLayout());
        inferior.setBackground(Color.WHITE);
        inferior.add(lbResumen, BorderLayout.WEST);
        inferior.add(acciones, BorderLayout.EAST);

        pnlTabla.removeAll();
        pnlTabla.setLayout(new BorderLayout(0, 8));
        pnlTabla.add(superior, BorderLayout.NORTH);
        pnlTabla.add(new JScrollPane(tblInventario), BorderLayout.CENTER);
        pnlTabla.add(inferior, BorderLayout.SOUTH);

        getContentPane().removeAll();
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(pnlTabla, BorderLayout.CENTER);

        actualizarTotales();
    }

    private JButton crearBoton(String texto, Color fondo) {
        JButton boton = new JButton(texto);
        boton.setBackground(fondo);
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("Century Gothic", Font.BOLD, 14));
        boton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        return boton;
    }

    // ------------------------------------------------------------------------------------------------
    // Agregar productos
    // ------------------------------------------------------------------------------------------------
    private void txtProductoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProductoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cargarProducto(txtProducto.getText().replace("'", "//"), 1);
        }
    }//GEN-LAST:event_txtProductoKeyReleased

    private void btnBusProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBusProdActionPerformed
        ventanaProductos("");
    }//GEN-LAST:event_btnBusProdActionPerformed

    private void jLabel2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jLabel2KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtProducto.requestFocus();
        }
    }//GEN-LAST:event_jLabel2KeyReleased

    /**
     * Agrega a la tabla el producto con el código indicado (también lo llama el
     * buscador de productos).
     */
    public void cargarProducto(String codigo, int plu) {
        if (codigo == null || codigo.trim().isEmpty()) {
            ventanaProductos("");
            return;
        }

        ModeloProducto nodo = daoProducto.obtenerDatosProducto(codigo, enumBodegas.ID_BODEGA_PRINCIPAL);
        if (nodo.getIdSistema() == null) {
            ventanaProductos(codigo);
            return;
        }

        String error = agregarProducto(nodo, BigDecimal.ZERO, true);
        if (error != null) {
            if (error.contains("inactivo")) {
                ControladorAlertas.alertFail(error);
                txtProducto.requestFocus();
            } else {
                ControladorAlertas.alert(error);
            }
        }
    }

    /**
     * Lo llama el buscador de productos cuando se seleccionan varios a la vez:
     * {codigo, cantidad escrita (0 si no puso)}.
     */
    public void cargarProductos(Object[][] productos) {
        List<Object> omitidos = new ArrayList<>();

        for (Object[] seleccionado : productos) {
            String codigo = seleccionado[0].toString();
            ModeloProducto nodo = daoProducto.obtenerDatosProducto(codigo, enumBodegas.ID_BODEGA_PRINCIPAL);
            if (nodo.getIdSistema() == null) {
                omitidos.add("El producto " + codigo + " no existe");
                continue;
            }

            BigDecimal cantidad = Utilidades.convertirBigDecimal(seleccionado[1].toString());
            // con varios productos no se abre el detalle uno por uno: se llena desde "Ver detalle" de cada fila
            String error = agregarProducto(nodo, cantidad, productos.length == 1);
            if (error != null) {
                omitidos.add(nodo.getCodigo() + ": " + error);
            }
        }

        if (!omitidos.isEmpty()) {
            ControladorAlertas.alertaGrandeListado("No se agregaron " + omitidos.size() + " productos:", omitidos, false);
        }
    }

    private String agregarProducto(ModeloProducto nodo, BigDecimal cantidadInicial, boolean abrirDetalle) {
        String id = nodo.getIdSistema();

        if (instancias.getSql().getProdActivo(id)) {
            txtProducto.setText("");
            return "Este producto esta inactivo";
        }

        if (productosCargados.containsKey(id)) {
            seleccionarFila(id);
            txtProducto.setText("");
            return "El producto ya está en la lista";
        }

        try {
            if (servicioInventarioInicial.tieneInventarioInicial(id)) {
                txtProducto.setText("");
                return "Este producto ya tiene inventario inicial";
            }
        } catch (SQLException ex) {
            Logger.getLogger(VistaInventarioInicial.class.getName()).log(Level.SEVERE, null, ex);
            return "No se pudo consultar el inventario inicial del producto";
        }

        BigDecimal costo;
        try {
            costo = servicioInventarioInicial.obtenerCostoSugerido(id);
        } catch (SQLException ex) {
            Logger.getLogger(VistaInventarioInicial.class.getName()).log(Level.SEVERE, null, ex);
            return "No se pudo consultar el último ponderado del producto";
        }

        String tipo = DetalleTipoProducto.obtenerTipoProducto(nodo.getTipoProducto());
        BigDecimal cantidad = tipo.isEmpty() ? cantidadInicial : BigDecimal.ZERO;
        productosCargados.put(id, nodo);
        agregarFila(nodo, tipo, cantidad, costo);
        txtProducto.setText("");

        int fila = modeloInventario.getRowCount() - 1;
        tblInventario.setRowSelectionInterval(fila, fila);
        tblInventario.scrollRectToVisible(tblInventario.getCellRect(fila, 0, true));

        if (!abrirDetalle) {
            return null;
        }

        if (tipo.isEmpty()) {
            tblInventario.editCellAt(fila, COL_CANTIDAD);
            if (tblInventario.getEditorComponent() != null) {
                tblInventario.getEditorComponent().requestFocus();
            }
        } else {
            abrirDetalle(fila);
        }
        return null;
    }

    private void agregarFila(ModeloProducto producto, String tipo, BigDecimal cantidad, BigDecimal costo) {
        recalculando = true;
        try {
            modeloInventario.addRow(new Object[]{producto.getCodigo(), producto.getDescripcion(), tipo,
                Utilidades.formatearCantidadVista(cantidad), big.setMoneda(costo), big.setMoneda(cantidad.multiply(costo)),
                tipo.isEmpty() ? "" : "Ver detalle", producto.getIdSistema()});
        } finally {
            recalculando = false;
        }
        actualizarTotales();
    }

    private void seleccionarFila(String idSistema) {
        int fila = buscarFila(idSistema);
        if (fila >= 0) {
            tblInventario.setRowSelectionInterval(fila, fila);
            tblInventario.scrollRectToVisible(tblInventario.getCellRect(fila, 0, true));
        }
    }

    private int buscarFila(String idSistema) {
        for (int i = 0; i < modeloInventario.getRowCount(); i++) {
            if (idSistema.equals(String.valueOf(modeloInventario.getValueAt(i, COL_ID)))) {
                return i;
            }
        }
        return -1;
    }

    private void quitarProductoSeleccionado() {
        int fila = tblInventario.getSelectedRow();
        if (fila < 0) {
            ControladorAlertas.alert("Seleccione el producto que desea quitar");
            return;
        }

        if (tblInventario.isEditing()) {
            tblInventario.getCellEditor().stopCellEditing();
        }

        String id = String.valueOf(modeloInventario.getValueAt(fila, COL_ID));
        eliminarRegistros(id);
        productosCargados.remove(id);
        modeloInventario.removeRow(fila);
        actualizarTotales();
    }

    // ------------------------------------------------------------------------------------------------
    // Cantidades, costos y totales
    // ------------------------------------------------------------------------------------------------
    private String tipoDeFila(int fila) {
        return String.valueOf(modeloInventario.getValueAt(fila, COL_TIPO));
    }

    private BigDecimal cantidadDeFila(int fila) {
        return Utilidades.convertirBigDecimal(String.valueOf(modeloInventario.getValueAt(fila, COL_CANTIDAD)));
    }

    private BigDecimal costoDeFila(int fila) {
        try {
            return big.getMoneda(String.valueOf(modeloInventario.getValueAt(fila, COL_COSTO)));
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }

    /**
     * Vuelve a dar formato a la cantidad y al costo que escribió el usuario y
     * calcula el total de la fila.
     */
    private void recalcularFila(int fila) {
        if (fila < 0 || fila >= modeloInventario.getRowCount()) {
            return;
        }

        BigDecimal cantidad = cantidadDeFila(fila);
        BigDecimal costo = costoDeFila(fila);

        recalculando = true;
        try {
            modeloInventario.setValueAt(Utilidades.formatearCantidadVista(cantidad), fila, COL_CANTIDAD);
            modeloInventario.setValueAt(big.setMoneda(costo), fila, COL_COSTO);
            modeloInventario.setValueAt(big.setMoneda(cantidad.multiply(costo)), fila, COL_TOTAL);
        } finally {
            recalculando = false;
        }

        actualizarTotales();
    }

    private void actualizarTotales() {
        BigDecimal unidades = BigDecimal.ZERO;
        BigDecimal costoGeneral = BigDecimal.ZERO;

        for (int i = 0; i < modeloInventario.getRowCount(); i++) {
            BigDecimal cantidad = cantidadDeFila(i);
            unidades = unidades.add(cantidad);
            costoGeneral = costoGeneral.add(cantidad.multiply(costoDeFila(i)));
        }

        lbResumen.setText("  Productos: " + modeloInventario.getRowCount() + "     Unidades: " + Utilidades.formatearCantidadVista(unidades));
        lbCostoGeneral.setText("Costo general: " + big.setMoneda(costoGeneral));
    }

    /**
     * Lo llama la ventana de detalle al confirmar: la cantidad del producto es
     * la suma de su detalle.
     */
    public void actualizarCantidadProducto(String idSistema, BigDecimal cantidad) {
        int fila = buscarFila(idSistema);
        if (fila >= 0) {
            modeloInventario.setValueAt(Utilidades.formatearCantidadVista(cantidad), fila, COL_CANTIDAD);
        }
    }

    // ------------------------------------------------------------------------------------------------
    // Detalle de los productos con tipo (imei, serial, color, talla, lote)
    // ------------------------------------------------------------------------------------------------
    private void abrirDetalle(int fila) {
        String id = String.valueOf(modeloInventario.getValueAt(fila, COL_ID));
        ModeloProducto nodo = productosCargados.get(id);

        VistaMovimientoDetalleProducto compraDetallada = new VistaMovimientoDetalleProducto(null, true, nodo, detallesDeProducto(id),
                "Entrada", TipoDocumento.INVENTARIO_INICIAL.getValor(), BigDecimal.ZERO);
        compraDetallada.setLocationRelativeTo(null);
        compraDetallada.setVisible(true);
    }

    /**
     * Detalle ya digitado de un producto, para volver a mostrarlo en la ventana
     * de detalle.
     */
    private List<DetalleProducto> detallesDeProducto(String idSistema) {
        List<DetalleProducto> detalles = new ArrayList<>();

        for (int i = 0; i < tblDetalle.getRowCount(); i++) {
            if (!idSistema.equals(obtenerValorTabla(i, 0))) {
                continue;
            }

            LocalDate fechaVence = Utilidades.convertirFecha(obtenerValorTabla(i, 3));
            BigDecimal cantidad = Utilidades.convertirBigDecimal(obtenerValorTabla(i, 5));
            detalles.add(new DetalleProducto("", idSistema, obtenerValorTabla(i, 6), obtenerValorTabla(i, 1), obtenerValorTabla(i, 2),
                    obtenerValorTabla(i, 7), obtenerValorTabla(i, 8), fechaVence, obtenerValorTabla(i, 4),
                    EstadosDetalleProducto.DISPONIBLE.getNombre(), enumBodegas.TipoBodega.BODEGA_PRINCIPAL.getValue(), cantidad, cantidad));
        }

        return detalles;
    }

    private List<DetalleProducto> generarDetalleProductos() {
        UtilidadesDetalleProducto utilidadesDetalleProducto = new UtilidadesDetalleProducto(tblDetalle);
        return utilidadesDetalleProducto.generarDetallesProductos(EstadosDetalleProducto.DISPONIBLE.getNombre());
    }

    private String obtenerValorTabla(int row, int col) {
        Object value = tblDetalle.getValueAt(row, col);
        return value != null ? value.toString() : "";
    }

    @Override
    public void cargarDetallado(String prod, String imei, String lote, String fechaVence, String temp,
            BigDecimal cantidad, String nombre, String color, String talla) {
        modeloPro1.addRow(new Object[]{prod, imei, lote, fechaVence, temp, cantidad, nombre, color, talla});
    }

    /**
     * Borra el detalle de todos los productos.
     */
    public void eliminarRegistros() {
        while (modeloPro1.getRowCount() > 0) {
            modeloPro1.removeRow(0);
        }
    }

    /**
     * Borra el detalle de un solo producto.
     */
    public void eliminarRegistros(String idSistema) {
        for (int i = modeloPro1.getRowCount() - 1; i >= 0; i--) {
            if (idSistema.equals(obtenerValorTabla(i, 0))) {
                modeloPro1.removeRow(i);
            }
        }
    }

    // ------------------------------------------------------------------------------------------------
    // Excel
    // ------------------------------------------------------------------------------------------------
    private void cargarDesdeExcel() {
        if (excelCargado) {
            Boolean continuar = ControladorAlertas.option("Ya se cargó un Excel. Si continúa se limpiarán los productos cargados actualmente. ¿Desea continuar?");
            if (continuar == null || !continuar) {
                return;
            }
            limpiar();
        }

        JFileChooser examinar = new JFileChooser();
        examinar.setDialogTitle("Inventario inicial desde Excel (.xls)");
        examinar.setFileFilter(new FileNameExtensionFilter("Excel 97-2003 (*.xls)", "xls"));
        if (examinar.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) {
            return;
        }

        File archivo = examinar.getSelectedFile().getAbsoluteFile();

        ResultadoImportacionInventarioInicial resultado;
        try {
            resultado = new ServicioImportacionInventarioInicial(instancias.getConfiguraciones().isProductosDetallados()).importar(archivo, new HashSet<>(productosCargados.keySet()),
                    identificadoresCargados());
        } catch (IOException | BiffException ex) {
            Logger.getLogger(VistaInventarioInicial.class.getName()).log(Level.SEVERE, null, ex);
            ControladorAlertas.alertFail("No se pudo leer el archivo. Debe ser un Excel 97-2003 (.xls)");
            return;
        }

        for (LineaImportacionInventarioInicial linea : resultado.getLineas()) {
            productosCargados.put(linea.getProducto().getIdSistema(), linea.getProducto());
            for (Object[] detalle : linea.getDetalles()) {
                modeloPro1.addRow(detalle);
            }
            agregarFila(linea.getProducto(), linea.getTipo(), linea.getCantidad(), linea.getCostoUnidad());
        }
        excelCargado = !resultado.getLineas().isEmpty();

        if (resultado.getLineas().isEmpty() && !resultado.tieneErrores()) {
            ControladorAlertas.alert("El archivo no tiene productos para cargar");
            return;
        }

        if (!resultado.getAvisos().isEmpty()) {
            mostrarListado("Se cargaron productos combinados o con costos distintos. Revise:", resultado.getAvisos());
        }

        if (resultado.tieneErrores()) {
            mostrarListado("No se cargaron " + resultado.getErrores().size() + " filas o productos:", resultado.getErrores());
        }

        if (!resultado.getLineas().isEmpty()) {
            ControladorAlertas.alertSuccess("Se cargaron " + resultado.getLineas().size() + " productos desde el archivo");
        }
    }

    private void mostrarListado(String mensaje, List<String> detalles) {
        ControladorAlertas.alertaGrandeListado(mensaje, new ArrayList<Object>(detalles), false);
    }

    private Set<String> identificadoresCargados() {
        Set<String> identificadores = new HashSet<>();
        for (int i = 0; i < tblDetalle.getRowCount(); i++) {
            String identificador = obtenerValorTabla(i, 1);
            if (!identificador.isEmpty()) {
                identificadores.add(identificador);
            }
        }
        return identificadores;
    }

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        guardar();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnGuardarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnGuardarKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            guardar();
        }
    }//GEN-LAST:event_btnGuardarKeyReleased

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiar();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void guardar() {
        if (tblInventario.isEditing()) {
            tblInventario.getCellEditor().stopCellEditing();
        }

        if (modeloInventario.getRowCount() == 0) {
            ControladorAlertas.alert("Agregue al menos un producto");
            return;
        }

        List<LineaInventarioInicial> lineas = new ArrayList<>();
        BigDecimal costoGeneral = BigDecimal.ZERO;
        List<Object> errores = new ArrayList<>();
        int primeraFilaConError = -1;

        for (int i = 0; i < modeloInventario.getRowCount(); i++) {
            String id = String.valueOf(modeloInventario.getValueAt(i, COL_ID));
            String codigo = String.valueOf(modeloInventario.getValueAt(i, COL_CODIGO));
            BigDecimal cantidad = cantidadDeFila(i);
            BigDecimal costo = costoDeFila(i);
            
            boolean conError = false;

            if (cantidad.signum() <= 0) {
                errores.add("La cantidad del producto " + codigo + " debe ser mayor a 0");
                conError = true;
            }

            if (costo.compareTo(BigDecimal.ZERO) <= 0) {
                errores.add("El costo del producto " + codigo + " debe ser mayor a 0");
                conError = true;
            }

            if (conError) {
                if (primeraFilaConError < 0) {
                    primeraFilaConError = i;
                }
                continue;
            }

            LineaInventarioInicial linea = new LineaInventarioInicial(productosCargados.get(id), cantidad, costo);
            lineas.add(linea);
            costoGeneral = costoGeneral.add(linea.getTotalCosto());
        }

        if (!errores.isEmpty()) {
            tblInventario.setRowSelectionInterval(primeraFilaConError, primeraFilaConError);
            tblInventario.scrollRectToVisible(tblInventario.getCellRect(primeraFilaConError, 0, true));
            ControladorAlertas.alertaGrandeListado("Corrija los siguientes errores para guardar el inventario inicial:", errores, false);
            return;
        }

        if (!ControladorAlertas.option("¿Guardar el inventario inicial de " + lineas.size() + " productos por un costo general de "
                + big.setMoneda(costoGeneral) + "?")) {
            return;
        }

        try {
            servicioInventarioInicial.guardar(lineas, generarDetalleProductos());
        } catch (SQLException | RuntimeException ex) {
            Logger.getLogger(VistaInventarioInicial.class.getName()).log(Level.SEVERE, null, ex);
            ControladorAlertas.alertFail(ex.getMessage() == null ? "No se pudo guardar el inventario inicial" : ex.getMessage());
            return;
        }

        ControladorAlertas.alertSuccess("Inventario agregado con éxito");
        limpiar();
    }

    private void limpiar() {
        if (tblInventario.isEditing()) {
            tblInventario.getCellEditor().cancelCellEditing();
        }

        while (modeloInventario.getRowCount() > 0) {
            modeloInventario.removeRow(0);
        }

        productosCargados.clear();
        excelCargado = false;
        eliminarRegistros();
        txtProducto.setText("");
        actualizarTotales();
        txtProducto.requestFocus();
    }

    public void ventanaProductos(String codigo) {
        VistaBuscadorProductos buscar = new VistaBuscadorProductos(null, true, false, "");
        buscar.setOpc("inventarioInicial");
        buscar.setLocationRelativeTo(null);
        instancias.setBusProductos(buscar);
        instancias.setCampoActual(txtProducto);
        txtProducto.requestFocus();
        buscar.noEncontrado(codigo.replace("'", "//"));
        buscar.show();
    }

    private void btnCargarPlantillaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarPlantillaActionPerformed
        cargarDesdeExcel();
    }//GEN-LAST:event_btnCargarPlantillaActionPerformed

    private void btnCargarPlantillaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnCargarPlantillaKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cargarDesdeExcel();
        }
    }//GEN-LAST:event_btnCargarPlantillaKeyReleased

    private void txtProducto1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProducto1KeyReleased
    }//GEN-LAST:event_txtProducto1KeyReleased

    private void btnQuitarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarProductoActionPerformed
        quitarProductoSeleccionado();
    }//GEN-LAST:event_btnQuitarProductoActionPerformed

    private void btnQuitarProductoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnQuitarProductoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_btnQuitarProductoKeyReleased

    private void btnDescargarPlantillaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDescargarPlantillaActionPerformed
        String plantilla = instancias.getConfiguraciones().isProductosDetallados()
                ? DescargadorPlantillas.CARGAR_INVENTARIO_DETALLADO : DescargadorPlantillas.CARGAR_INVENTARIO;
        DescargadorPlantillas.descargar(this, plantilla);
    }//GEN-LAST:event_btnDescargarPlantillaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBusProd;
    private javax.swing.JButton btnCargarPlantilla;
    private javax.swing.JButton btnDescargarPlantilla;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnQuitarProducto;
    private javax.swing.ButtonGroup grupoInventarios;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPanel pnlBotones;
    private javax.swing.JPanel pnlCargarProducto;
    private javax.swing.JPanel pnlInventarioInicial;
    private javax.swing.JPanel pnlInvisible;
    private javax.swing.JPanel pnlTabla;
    private javax.swing.JScrollPane scrFormulario;
    private javax.swing.JTable tblDetalle;
    private javax.swing.JTextField txtProducto;
    private javax.swing.JTextField txtProducto1;
    // End of variables declaration//GEN-END:variables
}
