package Vista.Productos;

import Enums.TipoProducto;
import Modelo.Inventario.UltimoPonderado;
import Modelo.Productos.LineaCosteoDiseno;
import Utilidades.Utilidades;
import dao.Productos.DaoDiseno;
import inventario.servicio.ServicioActualizacionPonderado;
import clases.Instancias;
import clases.productos.ndProducto;
import clases.big;
import clases.metodosGenerales;
import formularios.productos.buscMedidas;
import formularios.productos.buscProductos;
import Vista.Restaurante.VistaProductosCambio;
import formularios.productos.seleccionarPLU;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;

public class VistaDiseno extends javax.swing.JInternalFrame {

    private final ServicioActualizacionPonderado servicioActualizacionPonderado = new ServicioActualizacionPonderado();

    String simbolo;
    DefaultTableModel modeloPro;
    metodosGenerales metodos = new metodosGenerales();
    Instancias instancias;
    Object[][] grupos;
    Object[] datos;

    //Barra de titulo
    private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension dimBarra = null;
    private boolean plu = false;

    public boolean isPlu() {
        return plu;
    }

    public void setPlu(boolean plu) {
        this.plu = plu;
    }

    public VistaDiseno() {
        initComponents();
        modeloPro = (DefaultTableModel) tblProductos.getModel();

        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
        dimBarra = Barra.getPreferredSize();
        Barra.setSize(0, 0);
        Barra.setPreferredSize(new Dimension(0, 0));
        setBorder(null);
        repaint();

        btnBusProd.setEnabled(false);
        txtCodProducto.setEnabled(false);

        instancias = Instancias.getInstancias();
        actualizarGrupos();

        simbolo = instancias.getSimbolo();

        datos = instancias.getSql().getDatosMaestra();

        if (instancias.getConfiguraciones().isRestaurante()) {
            tblProductos.getColumnModel().getColumn(5).setMinWidth(50);
            tblProductos.getColumnModel().getColumn(5).setPreferredWidth(50);
            tblProductos.getColumnModel().getColumn(5).setMaxWidth(50);
        } else {
            tblProductos.getColumnModel().getColumn(5).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(5).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(5).setMaxWidth(0);
        }

        chkManejarInventario.setText(
                "<html>"
                + "Este diseño no maneja inventario propio. Al momento de <br>"
                + "realizar la factura, el sistema descontará automáticamente <br>"
                + "del inventario las cantidades correspondientes a cada uno <br>"
                + "de los productos que componen el diseño."
                + "</html>"
        );

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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        popBorrar = new javax.swing.JMenuItem();
        buttonGroup1 = new javax.swing.ButtonGroup();
        scrFormulario = new javax.swing.JScrollPane();
        pnlFormulario = new javax.swing.JPanel();
        scrProductos = new javax.swing.JScrollPane();
        tblProductos = new javax.swing.JTable();
        lbNit7 = new javax.swing.JLabel();
        txtCostoTotal = new javax.swing.JTextField();
        pnlInformacionDiseno = new javax.swing.JPanel();
        lbNit = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        btnBusProd1 = new javax.swing.JButton();
        lbRazon1 = new javax.swing.JLabel();
        txtInventario = new javax.swing.JTextField();
        lbRazon = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        chkManejarInventario = new javax.swing.JCheckBox();
        lbCupo1 = new javax.swing.JLabel();
        txtPuntoMinimo = new javax.swing.JTextField();
        lbTelefono = new javax.swing.JLabel();
        txtGrupo = new javax.swing.JComboBox();
        lbCupo2 = new javax.swing.JLabel();
        txtMedida = new javax.swing.JTextField();
        pnlValores = new javax.swing.JPanel();
        lbEmail = new javax.swing.JLabel();
        lbDepartamento = new javax.swing.JLabel();
        lbCiudad = new javax.swing.JLabel();
        txtIva = new javax.swing.JTextField();
        txtL1 = new javax.swing.JTextField();
        txtL2 = new javax.swing.JTextField();
        lbEmail1 = new javax.swing.JLabel();
        txtImpoconsumo = new javax.swing.JTextField();
        lbFecha = new javax.swing.JLabel();
        txtL3 = new javax.swing.JTextField();
        lbCupo = new javax.swing.JLabel();
        txtL4 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        lbDepartamento4 = new javax.swing.JLabel();
        txtMaximo = new javax.swing.JTextField();
        lbDepartamento2 = new javax.swing.JLabel();
        txtMinima = new javax.swing.JTextField();
        txtUtilidad1 = new javax.swing.JTextField();
        txtUtilidad2 = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        btnActualizar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        lbNit8 = new javax.swing.JLabel();
        txtCodProducto = new javax.swing.JTextField();
        btnBusProd = new javax.swing.JButton();

        popBorrar.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        popBorrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/eliminar-cancelar-icono-4935-16.png"))); // NOI18N
        popBorrar.setText("Borrar");
        popBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                popBorrarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(popBorrar);

        setTitle("Diseño");

        pnlFormulario.setBackground(new java.awt.Color(255, 255, 255));

        tblProductos.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        tblProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Descripción", "Cantidad", "Plu", "Cant2", "Cambio", "Costo P", "Ultimo C.", "Subtotal"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, false, false, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblProductos.setComponentPopupMenu(jPopupMenu1);
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
        if (tblProductos.getColumnModel().getColumnCount() > 0) {
            tblProductos.getColumnModel().getColumn(0).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(0).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(2).setMinWidth(40);
            tblProductos.getColumnModel().getColumn(2).setPreferredWidth(75);
            tblProductos.getColumnModel().getColumn(2).setMaxWidth(100);
            tblProductos.getColumnModel().getColumn(3).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(3).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(3).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(4).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(4).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(4).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(5).setMinWidth(50);
            tblProductos.getColumnModel().getColumn(5).setPreferredWidth(50);
            tblProductos.getColumnModel().getColumn(5).setMaxWidth(50);
            tblProductos.getColumnModel().getColumn(6).setMinWidth(120);
            tblProductos.getColumnModel().getColumn(6).setPreferredWidth(120);
            tblProductos.getColumnModel().getColumn(6).setMaxWidth(120);
            tblProductos.getColumnModel().getColumn(7).setMinWidth(120);
            tblProductos.getColumnModel().getColumn(7).setPreferredWidth(120);
            tblProductos.getColumnModel().getColumn(7).setMaxWidth(120);
            tblProductos.getColumnModel().getColumn(8).setMinWidth(120);
            tblProductos.getColumnModel().getColumn(8).setPreferredWidth(120);
            tblProductos.getColumnModel().getColumn(8).setMaxWidth(120);
        }

        lbNit7.setFont(new java.awt.Font("Century Gothic", 1, 22)); // NOI18N
        lbNit7.setText("TOTAL COSTO:");

        txtCostoTotal.setEditable(false);
        txtCostoTotal.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        txtCostoTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCostoTotal.setText("0");
        txtCostoTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCostoTotalActionPerformed(evt);
            }
        });

        pnlInformacionDiseno.setBackground(new java.awt.Color(255, 255, 255));
        pnlInformacionDiseno.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        lbNit.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbNit.setText("Codigo:     *");

        txtCodigo.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtCodigo.setName("Codigo"); // NOI18N
        txtCodigo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCodigoFocusLost(evt);
            }
        });
        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoKeyTyped(evt);
            }
        });

        btnBusProd1.setBackground(new java.awt.Color(204, 204, 204));
        btnBusProd1.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnBusProd1.setForeground(new java.awt.Color(255, 255, 255));
        btnBusProd1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar1.png"))); // NOI18N
        btnBusProd1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnBusProd1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBusProd1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnBusProd1.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnBusProd1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBusProd1ActionPerformed(evt);
            }
        });

        lbRazon1.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbRazon1.setText("Inventario:");

        txtInventario.setEditable(false);
        txtInventario.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        lbRazon.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lbRazon.setText("Descripción: *");

        txtDescripcion.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtDescripcion.setName("Descripción"); // NOI18N
        txtDescripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDescripcionKeyReleased(evt);
            }
        });

        chkManejarInventario.setBackground(new java.awt.Color(255, 255, 255));
        chkManejarInventario.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        chkManejarInventario.setText("Texto");
        chkManejarInventario.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkManejarInventarioStateChanged(evt);
            }
        });

        lbCupo1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lbCupo1.setText("Punto Minimo:");

        txtPuntoMinimo.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtPuntoMinimo.setName("Punto minimo"); // NOI18N
        txtPuntoMinimo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPuntoMinimoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPuntoMinimoKeyTyped(evt);
            }
        });

        lbTelefono.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lbTelefono.setText("Grupo:");

        txtGrupo.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        lbCupo2.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lbCupo2.setText("U. Medida:");

        txtMedida.setBackground(new java.awt.Color(255, 204, 204));
        txtMedida.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtMedida.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMedida.setName("Chip"); // NOI18N
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

        pnlValores.setBackground(new java.awt.Color(255, 255, 255));
        pnlValores.setBorder(javax.swing.BorderFactory.createTitledBorder("Valores de venta"));

        lbEmail.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbEmail.setText("% IVA:");

        lbDepartamento.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbDepartamento.setText("Lista 1: *");

        lbCiudad.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lbCiudad.setText("Lista 2:");

        txtIva.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtIva.setName("IVA"); // NOI18N
        txtIva.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIvaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIvaKeyTyped(evt);
            }
        });

        txtL1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtL1.setName("Lista 1"); // NOI18N
        txtL1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtL1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtL1KeyTyped(evt);
            }
        });

        txtL2.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtL2.setName("Lista 2"); // NOI18N
        txtL2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtL2KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtL2KeyTyped(evt);
            }
        });

        lbEmail1.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbEmail1.setText("% Impo:");

        txtImpoconsumo.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtImpoconsumo.setName("IVA"); // NOI18N
        txtImpoconsumo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtImpoconsumoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtImpoconsumoKeyTyped(evt);
            }
        });

        lbFecha.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lbFecha.setText("Lista 3");

        txtL3.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtL3.setName("Lista 3"); // NOI18N
        txtL3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtL3ActionPerformed(evt);
            }
        });
        txtL3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtL3KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtL3KeyTyped(evt);
            }
        });

        lbCupo.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lbCupo.setText("Lista 4:");

        txtL4.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtL4.setName("Lista 4"); // NOI18N
        txtL4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtL4KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtL4KeyTyped(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        lbDepartamento4.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lbDepartamento4.setText("Utilidad Máxima:");

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
        });

        lbDepartamento2.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbDepartamento2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbDepartamento4))
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtMinima)
                    .addComponent(txtMaximo, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtUtilidad1)
                    .addComponent(txtUtilidad2, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtUtilidad1, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(txtMinima)
                    .addComponent(lbDepartamento2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbDepartamento4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtMaximo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(txtUtilidad2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlValoresLayout = new javax.swing.GroupLayout(pnlValores);
        pnlValores.setLayout(pnlValoresLayout);
        pnlValoresLayout.setHorizontalGroup(
            pnlValoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlValoresLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlValoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlValoresLayout.createSequentialGroup()
                        .addGroup(pnlValoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lbEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbCiudad, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbDepartamento, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlValoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtL1, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                            .addComponent(txtIva)
                            .addComponent(txtL2))
                        .addGap(30, 30, 30)
                        .addGroup(pnlValoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbEmail1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbCupo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(10, 10, 10)
                        .addGroup(pnlValoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtL4, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                            .addComponent(txtL3)
                            .addComponent(txtImpoconsumo))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlValoresLayout.setVerticalGroup(
            pnlValoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlValoresLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(pnlValoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtImpoconsumo)
                    .addComponent(lbEmail1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtIva)
                    .addComponent(lbEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addGroup(pnlValoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtL3)
                    .addComponent(txtL1)
                    .addComponent(lbFecha, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbDepartamento, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addGroup(pnlValoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbCiudad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtL2)
                    .addComponent(lbCupo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtL4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        btnActualizar.setBackground(new java.awt.Color(93, 173, 226));
        btnActualizar.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/actualizar.png"))); // NOI18N
        btnActualizar.setText("MODIFICAR");
        btnActualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnActualizar.setEnabled(false);
        btnActualizar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnActualizar.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnGuardar.setBackground(new java.awt.Color(46, 204, 113));
        btnGuardar.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
        btnGuardar.setText("GUARDAR");
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnGuardar.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnNuevo.setBackground(new java.awt.Color(204, 204, 204));
        btnNuevo.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/nuevo.png"))); // NOI18N
        btnNuevo.setText("NUEVO ");
        btnNuevo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNuevo.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnNuevo.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNuevo)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnActualizar))
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout pnlInformacionDisenoLayout = new javax.swing.GroupLayout(pnlInformacionDiseno);
        pnlInformacionDiseno.setLayout(pnlInformacionDisenoLayout);
        pnlInformacionDisenoLayout.setHorizontalGroup(
            pnlInformacionDisenoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInformacionDisenoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlInformacionDisenoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlInformacionDisenoLayout.createSequentialGroup()
                        .addGroup(pnlInformacionDisenoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lbRazon1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbRazon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbNit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlInformacionDisenoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnlInformacionDisenoLayout.createSequentialGroup()
                                .addComponent(txtInventario, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lbCupo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPuntoMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlInformacionDisenoLayout.createSequentialGroup()
                                .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBusProd1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlInformacionDisenoLayout.createSequentialGroup()
                        .addComponent(lbCupo2, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMedida, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbTelefono, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(4, 4, 4)
                        .addComponent(txtGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnlValores, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(chkManejarInventario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlInformacionDisenoLayout.setVerticalGroup(
            pnlInformacionDisenoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInformacionDisenoLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(pnlInformacionDisenoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbNit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addComponent(btnBusProd1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addGroup(pnlInformacionDisenoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbRazon, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(pnlInformacionDisenoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbRazon1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtInventario)
                    .addComponent(txtPuntoMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbCupo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addGroup(pnlInformacionDisenoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtMedida, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbTelefono, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtGrupo, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbCupo2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15)
                .addComponent(chkManejarInventario)
                .addGap(18, 18, 18)
                .addComponent(pnlValores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        lbNit8.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbNit8.setText("Cargar producto al diseño:");

        txtCodProducto.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
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

        javax.swing.GroupLayout pnlFormularioLayout = new javax.swing.GroupLayout(pnlFormulario);
        pnlFormulario.setLayout(pnlFormularioLayout);
        pnlFormularioLayout.setHorizontalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrProductos)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFormularioLayout.createSequentialGroup()
                        .addGap(119, 119, 119)
                        .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFormularioLayout.createSequentialGroup()
                                .addComponent(lbNit7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCostoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFormularioLayout.createSequentialGroup()
                                .addGap(82, 82, 82)
                                .addComponent(txtCodProducto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBusProd, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(pnlFormularioLayout.createSequentialGroup()
                        .addComponent(lbNit8)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(5, 5, 5)
                .addComponent(pnlInformacionDiseno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlFormularioLayout.setVerticalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlFormularioLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbNit8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtCodProducto, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                            .addComponent(btnBusProd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(5, 5, 5)
                        .addComponent(scrProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbNit7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtCostoTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)))
                    .addComponent(pnlInformacionDiseno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

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

    private void tblProductosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblProductosKeyReleased
        if (evt.getKeyCode() != KeyEvent.VK_ENTER) {
            return;
        }

        int fila = tblProductos.getSelectedRow();

        if (tblProductos.getSelectedColumn() == 2) {
            txtCodProducto.requestFocus();
        }

        int comparacion;
        try {
            comparacion = big.getBigDecimal(big.getMoneda((String) tblProductos.getValueAt(fila, 2))).compareTo(BigDecimal.ZERO);
        } catch (Exception e) {
            metodos.msgAdvertencia(this, "La cantidad debe ser mayor a 0");
            return;
        }

        if (comparacion <= 0) {
            tblProductos.setValueAt(1, fila, 2);
        }

        BigDecimal cantidad = Utilidades.convertirBigDecimal(tblProductos.getValueAt(fila, 2).toString());
        actualizarCantidadEquivalente(fila, cantidad);
        tblProductos.setValueAt(Utilidades.formatearCantidad(cantidad), fila, 2);
        calcularCostoDiseno();
    }//GEN-LAST:event_tblProductosKeyReleased

    private void txtCodProductoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodProductoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String codigo = txtCodProducto.getText();
            plu = true;
            cargarProducto(codigo.replace("'", "//"), "1", 1);
        }
    }//GEN-LAST:event_txtCodProductoKeyReleased

    private void tblProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductosMouseClicked
        if (evt.getClickCount() >= 2) {
            String cod = "";

            try {
                cod = tblProductos.getValueAt(tblProductos.getSelectedRow(), 0).toString();
            } catch (Exception e) {
                metodos.msgError(this, "Seleccione un producto de la tabla");
                return;
            }

            if (!(Boolean) tblProductos.getValueAt(tblProductos.getSelectedRow(), 5)) {
                metodos.msgError(this, "Debe activar la opción de cambio");
                return;
            }

            VistaProductosCambio relacionados = new VistaProductosCambio(null, true, cod, txtCodigo.getText(), "", "");
            relacionados.setLocationRelativeTo(null);
            relacionados.setVisible(true);
        }
    }//GEN-LAST:event_tblProductosMouseClicked

    private void tblProductosMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductosMouseReleased

    }//GEN-LAST:event_tblProductosMouseReleased

    private void txtCodProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodProductoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        Object[] campos = {txtCodigo, txtDescripcion, txtL1};
        String faltantes = metodos.camposVacios(campos);

        Object[] campos2;
        if (chkManejarInventario.isSelected()) {
            campos2 = new Object[]{txtIva, txtL2, txtL4, txtL3};
        } else {
            campos2 = new Object[]{txtIva, txtL2, txtL4, txtL3, txtPuntoMinimo};
        }

        String faltantes2 = metodos.camposVacios(campos2);

        if (!faltantes.equals("")) {
            metodos.msgAdvertencia(this, "No puede continuar, faltan los siguientes campos: " + faltantes);
            return;
        }

        calcularFilas();

        if (tblProductos.getRowCount() < 1) {
            metodos.msgAdvertencia(this, "No puede continuar, debe de agregar almenos un producto en la tabla");
            return;
        } else if (!faltantes2.equals("")) {
            metodos.msgAdvertencia(this, "Faltan los siguientes campos: " + faltantes2);
        }

        String consecutivo = "";
        if (metodos.msgPregunta(this, "¿Desea continuar?") == 0) {

            ndProducto nodo1 = instancias.getSql().getDatosProducto(txtCodigo.getText(), "bdProductos");
            if (nodo1.getIdSistema() != null) {
                metodos.msgAdvertenciaAjustado(this, "¡El codigo ya existe!");
                txtCodigo.requestFocus();
                return;
            }

            if (txtCodigo.isEditable()) {
                ndProducto nodo = null;

                if (txtL4.getText().equals("")) {
                    txtL4.setText("0");
                }
                if (txtL2.getText().equals("")) {
                    txtL2.setText("0");
                }
                if (txtL3.getText().equals("")) {
                    txtL3.setText("0");
                }
                if (txtIva.getText().equals("")) {
                    txtIva.setText("0");
                }
                if (txtImpoconsumo.getText().equals("")) {
                    txtImpoconsumo.setText("0");
                }
                if (txtPuntoMinimo.getText().equals("")) {
                    txtPuntoMinimo.setText("0");
                }

                Boolean manejaInv = false;
                String tipo = "";
                if (chkManejarInventario.isSelected()) {
                    tipo = "FACTURA";
                    manejaInv = false;
                } else {
                    tipo = "COSTEO";
                    manejaInv = true;
                }

                String grupo = "";
                try {
                    grupo = instancias.getSql().getDatosGrupoPorNombre(txtGrupo.getSelectedItem().toString());
                } catch (Exception e) {
                }

                int num = Integer.parseInt(instancias.getSql().getNumConsecutivo("PROD")[0].toString());
                consecutivo = String.valueOf(num);
                for (int i = 0; i < 8 - String.valueOf(num).length(); i++) {
                    consecutivo = "0" + consecutivo;
                }

                if ("".equals(grupo)) {
                    grupo = null;
                }

                Object[] vector = {"PROD-" + consecutivo, txtCodigo.getText().replace("'", "//"), "", txtDescripcion.getText(), grupo,
                    null, null, txtIva.getText(), big.getMoneda(txtL1.getText()), big.getMoneda(txtL2.getText()), big.getMoneda(txtL3.getText()),
                    big.getMoneda(txtL4.getText()), big.getMoneda("0"), big.getMoneda("0"), big.getMoneda("0"), big.getMoneda("0"),
                    tipo, txtPuntoMinimo.getText(), txtMedida.getText(), "", 0, txtMinima.getText(), txtMaximo.getText(), "", txtDescripcion.getText(), "", "",
                    txtDescripcion.getText(), "", "", txtDescripcion.getText(), "", "", txtDescripcion.getText(),
                    "", "", txtDescripcion.getText(), "", "", txtDescripcion.getText(), "", "", txtDescripcion.getText(), "", "",
                    false, false, false, false, false, false, false, 0, 0, instancias.getTerminal(), "0", "", manejaInv, "",
                    "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", false, "", "", "", "",
                    "", "", "", "", "", "0", txtCodigo.getText().replace("'", "//") + "-2", txtCodigo.getText().replace("'", "//") + "-3",
                    txtCodigo.getText().replace("'", "//") + "-4", txtCodigo.getText().replace("'", "//") + "-5", txtCodigo.getText().replace("'", "//") + "-6",
                    txtCodigo.getText().replace("'", "//") + "-7", txtCodigo.getText().replace("'", "//") + "-8", "", "", "", "", "", "", "1",
                    txtImpoconsumo.getText(), txtImpoconsumo.getText(), txtDescripcion.getText(), "0"};

                nodo = metodos.llenarProducto(vector);

                if (!instancias.getSql().agregarProducto(nodo, "bdProductos")) {
                    metodos.msgError(this, "Error al guardar el producto");
                    return;
                }
                if (!instancias.getSql().agregarProducto(nodo, "bdProductosBodega1")) {
                    metodos.msgError(this, "Error al guardar el producto1");
                    return;
                }
                if (!instancias.getSql().agregarProducto(nodo, "bdProductosBodega2")) {
                    metodos.msgError(this, "Error al guardar el producto2");
                    return;
                }
                if (!instancias.getSql().agregarProducto(nodo, "bdProductosBodega3")) {
                    metodos.msgError(this, "Error al guardar el producto3");
                    return;
                }
                if (!instancias.getSql().agregarProducto(nodo, "bdProductosBodega4")) {
                    metodos.msgError(this, "Error al guardar el producto4");
                    return;
                }
            }

            //PROCESO GUARDAR COSTEO DEL DISEÑO
            guardarLineasCosteo("PROD-" + consecutivo, instancias.getUsuario());

            if (!instancias.getSql().agregarPonderado(metodos.fechaConsulta(metodosGenerales.fechaHora()), "PROD-" + consecutivo,
                    BigDecimal.ZERO, "0", "0", BigDecimal.ZERO, "0", instancias.getUsuario(), BigDecimal.ZERO, "")) {
                metodos.msgError(this, "Error al guardar en el consecutivo del producto");
            }

            if (!instancias.getSql().agregarUltimoPonderado(metodos.fechaConsulta(metodosGenerales.fechaHora()), "PROD-" + consecutivo,
                    BigDecimal.ZERO, "0", "0", BigDecimal.ZERO, "0", instancias.getUsuario(), BigDecimal.ZERO, "")) {
                metodos.msgError(this, "Error al guardar en el consecutivo del producto");
            }

            if (!instancias.getSql().aumentarConsecutivo("PROD", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("PROD")[0]) + 1)) {
                metodos.msgError(this, "Error al guardar en el consecutivo del producto");
            }

            metodos.msgExito(this, "Diseño registrado con éxito");

            if (!chkManejarInventario.isSelected()) {
                String codigo = txtCodigo.getText();
                instancias.getArmado().cargarProductoPrincipal(codigo);
                instancias.getArmado().cargarCodigo(codigo);
                try {
                    instancias.getArmado().setSelected(true);
                } catch (Exception e) {
                }
            }

            btnNuevoActionPerformed(evt);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void popBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popBorrarActionPerformed
        if (tblProductos.getSelectedRow() > -1) {
            int fila = tblProductos.getSelectedRow();

            DefaultTableModel modelo = (DefaultTableModel) tblProductos.getModel();
            modelo.removeRow(fila);
            calcularCostoDiseno();
        } else {
            metodos.msgAdvertencia(this, "Seleccione un producto");
        }
    }//GEN-LAST:event_popBorrarActionPerformed

    private void txtL4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtL4KeyReleased

        txtL4.setText(big.setMoneda(big.getMoneda(txtL4.getText())));

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtPuntoMinimo.requestFocus();
        }
    }//GEN-LAST:event_txtL4KeyReleased

    private void txtL4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtL4KeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtL4KeyTyped

    private void txtDescripcionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtGrupo.requestFocus();
        } else {
            if (!txtCodigo.getText().equals("") && !txtDescripcion.getText().equals("")) {
                btnBusProd.setEnabled(true);
                txtCodProducto.setEnabled(true);
            } else {
                btnBusProd.setEnabled(false);
                txtCodProducto.setEnabled(false);
            }
        }
    }//GEN-LAST:event_txtDescripcionKeyReleased

    private void txtIvaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIvaKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtL1.requestFocus();
        }
    }//GEN-LAST:event_txtIvaKeyReleased

    private void txtIvaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIvaKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtIvaKeyTyped

    private void txtL3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtL3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtL3ActionPerformed

    private void txtL3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtL3KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtL4.requestFocus();
        } else {
            txtL3.setText(big.setMoneda(big.getMoneda(txtL3.getText())));
        }
    }//GEN-LAST:event_txtL3KeyReleased

    private void txtL3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtL3KeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtL3KeyTyped

    private void txtL1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtL1KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtL2.requestFocus();
        } else {
            txtL1.setText(big.setMoneda(big.getMoneda(txtL1.getText())));
            if (btnGuardar.isEnabled()) {
                txtL2.setText(txtL1.getText());
                txtL3.setText(txtL1.getText());
                txtL4.setText(txtL1.getText());
            }
        }
    }//GEN-LAST:event_txtL1KeyReleased

    private void txtL1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtL1KeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtL1KeyTyped

    private void txtCodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyReleased
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_ENTER:
                buscarDisenoPorCodigo();
                break;
            case KeyEvent.VK_TAB:
                txtDescripcion.requestFocus();
                break;
            default:
                actualizarHabilitacionBusqueda();
        }
    }//GEN-LAST:event_txtCodigoKeyReleased

    private void buscarDisenoPorCodigo() {
        String codigo = txtCodigo.getText();
        if (codigo.equals("")) {
            ventanaProductos2();
            return;
        }

        ndProducto nodo = instancias.getSql().getDatosProducto(codigo, "bdProductos");
        boolean existe = nodo != null && nodo.getIdSistema() != null && !nodo.getIdSistema().equals("");

        if (!existe) {
            habilitarBusquedaProducto(false);
            txtDescripcion.requestFocus();
            return;
        }

        if (nodo != null && !nodo.getUsuario().equals(TipoProducto.GENERICO.getValue())) {
            cargarProductoDiseno(codigo);
        }
    }

    private void actualizarHabilitacionBusqueda() {
        boolean habilitar = !txtCodigo.getText().equals("") && !txtDescripcion.getText().equals("");
        habilitarBusquedaProducto(habilitar);
    }

    private void habilitarBusquedaProducto(boolean habilitar) {
        btnBusProd.setEnabled(habilitar);
        txtCodProducto.setEnabled(habilitar);
    }

    private void txtCodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyTyped

    }//GEN-LAST:event_txtCodigoKeyTyped

    private void txtL2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtL2KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtL3.requestFocus();
        } else {
            txtL2.setText(big.setMoneda(big.getMoneda(txtL2.getText())));
        }
    }//GEN-LAST:event_txtL2KeyReleased

    private void txtL2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtL2KeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtL2KeyTyped

    private void txtPuntoMinimoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPuntoMinimoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPuntoMinimoKeyReleased

    private void txtPuntoMinimoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPuntoMinimoKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtPuntoMinimoKeyTyped

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        for (int i = 0; i < tblProductos.getRowCount(); i++) {
            BigDecimal cantidad = BigDecimal.ZERO;

            try {
                cantidad = Utilidades.convertirBigDecimal(tblProductos.getValueAt(i, 2).toString());
            } catch (Exception e) {
            }

            if (cantidad.compareTo(BigDecimal.ZERO) <= 0) {
                metodos.msgAdvertencia(this, "La cantidad debe ser mayor a 0");
                return;
            }
        }

        calcularFilas();

        if (metodos.msgPregunta(this, "¿Desea continuar?") == 0) {
            tblProductos.removeEditor();

            Boolean manejaInv = false;
            String tipo = "";
            if (chkManejarInventario.isSelected()) {
                manejaInv = false;
                tipo = "FACTURA";
            } else {
                manejaInv = true;
                tipo = "COSTEO";
            }

            ndProducto nodo = instancias.getSql().getDatosProducto(txtCodigo.getText(), "bdProductos");
            ndProducto nodo1 = null, nodo2 = null, nodo3 = null, nodo4 = null;
            if (instancias.getConfiguraciones().isInventarioBodegas()) {
                nodo1 = instancias.getSql().getDatosProducto(txtCodigo.getText(), "bdProductosBodega1");
                nodo2 = instancias.getSql().getDatosProducto(txtCodigo.getText(), "bdProductosBodega2");
                nodo3 = instancias.getSql().getDatosProducto(txtCodigo.getText(), "bdProductosBodega3");
                nodo4 = instancias.getSql().getDatosProducto(txtCodigo.getText(), "bdProductosBodega4");
            }

            String grupo = "";
            try {
                grupo = instancias.getSql().getDatosGrupoPorNombre(txtGrupo.getSelectedItem().toString());
            } catch (Exception e) {
            }

            if ("".equals(grupo)) {
                grupo = null;
            }

            nodo.setGrupo(grupo);
            nodo.setUsuario(tipo);
            nodo.setTipoProd("FIJAS");
            nodo.setUnd(txtMedida.getText());
            nodo.setDescripcion(txtDescripcion.getText());
            nodo.setL1(big.getMoneda(txtL1.getText()).toString());
            nodo.setL2(big.getMoneda(txtL2.getText()).toString());
            nodo.setL3(big.getMoneda(txtL3.getText()).toString());
            nodo.setL4(big.getMoneda(txtL4.getText()).toString());
            nodo.setManejaInventario(manejaInv);
            nodo.setMinima(txtMinima.getText());
            nodo.setMaxima(txtMaximo.getText());
            nodo.setImpoconsumoCompra(txtImpoconsumo.getText());
            nodo.setImpoconsumoVenta(txtImpoconsumo.getText());
            nodo.setIva(txtIva.getText());
            nodo.setIvaC(txtIva.getText());

            if (instancias.getConfiguraciones().isInventarioBodegas()) {
                nodo1.setGrupo(grupo);
                nodo1.setUsuario(tipo);
                nodo1.setTipoProd("FIJAS");
                nodo1.setUnd(txtMedida.getText());
                nodo1.setDescripcion(txtDescripcion.getText());
                nodo1.setL1(big.getMoneda(txtL1.getText()).toString());
                nodo1.setL2(big.getMoneda(txtL2.getText()).toString());
                nodo1.setL3(big.getMoneda(txtL3.getText()).toString());
                nodo1.setL4(big.getMoneda(txtL4.getText()).toString());
                nodo1.setManejaInventario(manejaInv);
                nodo1.setMinima(txtMinima.getText());
                nodo1.setMaxima(txtMaximo.getText());
                nodo1.setImpoconsumoCompra(txtImpoconsumo.getText());
                nodo1.setImpoconsumoVenta(txtImpoconsumo.getText());
                nodo1.setIva(txtIva.getText());
                nodo1.setIvaC(txtIva.getText());

                nodo2.setGrupo(grupo);
                nodo2.setUsuario(tipo);
                nodo2.setTipoProd("FIJAS");
                nodo2.setUnd(txtMedida.getText());
                nodo2.setDescripcion(txtDescripcion.getText());
                nodo2.setL1(big.getMoneda(txtL1.getText()).toString());
                nodo2.setL2(big.getMoneda(txtL2.getText()).toString());
                nodo2.setL3(big.getMoneda(txtL3.getText()).toString());
                nodo2.setL4(big.getMoneda(txtL4.getText()).toString());
                nodo2.setManejaInventario(manejaInv);
                nodo2.setMinima(txtMinima.getText());
                nodo2.setMaxima(txtMaximo.getText());
                nodo2.setImpoconsumoCompra(txtImpoconsumo.getText());
                nodo2.setImpoconsumoVenta(txtImpoconsumo.getText());
                nodo2.setIva(txtIva.getText());
                nodo2.setIvaC(txtIva.getText());

                nodo3.setGrupo(grupo);
                nodo3.setUsuario(tipo);
                nodo3.setTipoProd("FIJAS");
                nodo3.setUnd(txtMedida.getText());
                nodo3.setDescripcion(txtDescripcion.getText());
                nodo3.setL1(big.getMoneda(txtL1.getText()).toString());
                nodo3.setL2(big.getMoneda(txtL2.getText()).toString());
                nodo3.setL3(big.getMoneda(txtL3.getText()).toString());
                nodo3.setL4(big.getMoneda(txtL4.getText()).toString());
                nodo3.setManejaInventario(manejaInv);
                nodo3.setMinima(txtMinima.getText());
                nodo3.setMaxima(txtMaximo.getText());
                nodo3.setImpoconsumoCompra(txtImpoconsumo.getText());
                nodo3.setImpoconsumoVenta(txtImpoconsumo.getText());
                nodo3.setIva(txtIva.getText());
                nodo3.setIvaC(txtIva.getText());

                nodo4.setGrupo(grupo);
                nodo4.setUsuario(tipo);
                nodo4.setTipoProd("FIJAS");
                nodo4.setUnd(txtMedida.getText());
                nodo4.setDescripcion(txtDescripcion.getText());
                nodo4.setL1(big.getMoneda(txtL1.getText()).toString());
                nodo4.setL2(big.getMoneda(txtL2.getText()).toString());
                nodo4.setL3(big.getMoneda(txtL3.getText()).toString());
                nodo4.setL4(big.getMoneda(txtL4.getText()).toString());
                nodo4.setManejaInventario(manejaInv);
                nodo4.setMinima(txtMinima.getText());
                nodo4.setMaxima(txtMaximo.getText());
                nodo4.setImpoconsumoCompra(txtImpoconsumo.getText());
                nodo4.setImpoconsumoVenta(txtImpoconsumo.getText());
                nodo4.setIva(txtIva.getText());
                nodo4.setIvaC(txtIva.getText());
            }

            if (!instancias.getSql().modificarProducto(nodo, "bdProductos")) {
                metodos.msgError(this, "Hubo un problema al modificar el diseño");
            }

            if (instancias.getConfiguraciones().isInventarioBodegas()) {
                if (!instancias.getSql().modificarProducto(nodo1, "bdProductosBodega1")) {
                    metodos.msgError(this, "Hubo un problema al modificar el diseño");
                }
                if (!instancias.getSql().modificarProducto(nodo2, "bdProductosBodega2")) {
                    metodos.msgError(this, "Hubo un problema al modificar el diseño");
                }
                if (!instancias.getSql().modificarProducto(nodo3, "bdProductosBodega3")) {
                    metodos.msgError(this, "Hubo un problema al modificar el diseño");
                }
                if (!instancias.getSql().modificarProducto(nodo4, "bdProductosBodega4")) {
                    metodos.msgError(this, "Hubo un problema al modificar el diseño");
                }
            }

            boolean noPuedaGuardar = false;
            while (!noPuedaGuardar) {
                noPuedaGuardar = instancias.getSql().eliminarProdCosteo(nodo.getIdSistema());
            }

            //PROCESO GUARDAR COSTEO DEL DISEÑO
            guardarLineasCosteo(nodo.getIdSistema(), tipo);

            metodos.msgExito(this, "Diseño modificado con éxito");

            if (!chkManejarInventario.isSelected()) {
                instancias.getArmado().cargarProductoPrincipal(txtCodigo.getText());
                instancias.getArmado().cargarCodigo(txtCodigo.getText());
                try {
                    instancias.getArmado().setSelected(true);
                } catch (Exception e) {
                }
            }

            btnNuevoActionPerformed(evt);
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        btnBusProd.setEnabled(false);
        txtCodProducto.setEnabled(false);
        chkManejarInventario.setSelected(false);
        txtCodigo.setEditable(true);

        for (int x = 0; x < pnlInformacionDiseno.getComponentCount(); x++) {
            if (pnlInformacionDiseno.getComponent(x) instanceof JTextField) {
                JTextField textField = (JTextField) pnlInformacionDiseno.getComponent(x);
                textField.setText("");
            }
        }

        for (int x = 0; x < pnlValores.getComponentCount(); x++) {
            if (pnlValores.getComponent(x) instanceof JTextField) {
                JTextField textField = (JTextField) pnlValores.getComponent(x);
                textField.setText("");
            }
        }

        txtGrupo.setSelectedIndex(0);
        txtCostoTotal.setText(this.simbolo + " 0");

        DefaultTableModel y = (DefaultTableModel) tblProductos.getModel();
        int i, j = tblProductos.getRowCount();
        for (i = 0; i < j; i++) {
            y.removeRow(0);
        }

        txtCodigo.requestFocus();
        btnActualizar.setEnabled(false);
        btnGuardar.setEnabled(true);

        txtMinima.setText("20");
        txtMaximo.setText("30");
        txtUtilidad1.setText(this.simbolo + " 0");
        txtUtilidad2.setText(this.simbolo + " 0");
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void txtCodigoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCodigoFocusLost

    }//GEN-LAST:event_txtCodigoFocusLost

    private void chkManejarInventarioStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkManejarInventarioStateChanged
        txtPuntoMinimo.setEnabled(!chkManejarInventario.isSelected());
    }//GEN-LAST:event_chkManejarInventarioStateChanged

    private void txtCostoTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCostoTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCostoTotalActionPerformed

    private void txtMedidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMedidaActionPerformed

    }//GEN-LAST:event_txtMedidaActionPerformed

    private void txtMedidaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMedidaKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
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

    private void txtMinimaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMinimaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMinimaActionPerformed

    private void txtMinimaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMinimaKeyPressed
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtMinimaKeyPressed

    private void txtMinimaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMinimaKeyReleased
        if (txtMinima.getText().equals("")) {
            txtMinima.setText("0");
        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtMaximo.requestFocus();
        }

        calcularUtilidad();
    }//GEN-LAST:event_txtMinimaKeyReleased

    private void txtUtilidad1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUtilidad1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUtilidad1ActionPerformed

    private void txtUtilidad1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUtilidad1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUtilidad1KeyReleased

    private void txtUtilidad1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUtilidad1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUtilidad1KeyTyped

    private void txtMaximoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaximoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaximoActionPerformed

    private void txtMaximoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaximoKeyPressed
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtMaximoKeyPressed

    private void txtMaximoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaximoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtMedida.requestFocus();
        }

        calcularUtilidad();
    }//GEN-LAST:event_txtMaximoKeyReleased

    private void txtUtilidad2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUtilidad2KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUtilidad2KeyReleased

    private void txtUtilidad2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUtilidad2KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUtilidad2KeyTyped

    private void btnBusProd1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBusProd1ActionPerformed
        ventanaProductos2();
    }//GEN-LAST:event_btnBusProd1ActionPerformed

    private void btnBusProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBusProdActionPerformed
        ventanaProductos("");
    }//GEN-LAST:event_btnBusProdActionPerformed

    private void txtImpoconsumoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtImpoconsumoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtImpoconsumoKeyReleased

    private void txtImpoconsumoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtImpoconsumoKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtImpoconsumoKeyTyped

    public void calcularUtilidad() {
        BigDecimal totalCosto = big.getMoneda(txtCostoTotal.getText());
        BigDecimal utilidadMinima = Utilidades.convertirBigDecimal(txtMinima.getText());
        BigDecimal utilidadMaxima = Utilidades.convertirBigDecimal(txtMaximo.getText());

        BigDecimal valorUtilidadMinima = totalCosto
                .multiply(big.getBigDecimal(utilidadMinima).divide(big.getBigDecimal(100), 4, RoundingMode.HALF_UP))
                .add(totalCosto);

        BigDecimal valorUtilidadMaxima = totalCosto
                .multiply(big.getBigDecimal(utilidadMaxima).divide(big.getBigDecimal(100), 4, RoundingMode.HALF_UP))
                .add(totalCosto);

        txtUtilidad1.setText(big.setMoneda(valorUtilidadMinima.setScale(0, BigDecimal.ROUND_HALF_DOWN)));
        txtUtilidad2.setText(big.setMoneda(valorUtilidadMaxima.setScale(0, BigDecimal.ROUND_HALF_DOWN)));
    }

    public void calcularFilas() {
        for (int i = 0; i < tblProductos.getRowCount(); i++) {
            BigDecimal cantidad = Utilidades.convertirBigDecimal(tblProductos.getValueAt(i, 2).toString());
            actualizarCantidadEquivalente(i, cantidad);
            tblProductos.setValueAt(Utilidades.formatearCantidad(cantidad), i, 2);
        }
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

    public void cargarProducto(String codigo, String cantidad, int plu) {

        for (int j = 0; j < tblProductos.getRowCount(); j++) {
            if (codigo.equalsIgnoreCase((String) tblProductos.getValueAt(j, 0))) {
                metodos.msgAdvertencia(this, "El producto ya esta cargado");
                tblProductos.setColumnSelectionInterval(3, 3);
                tblProductos.setRowSelectionInterval(j, j);
                tblProductos.editCellAt(j, 3);
                tblProductos.transferFocus();
                txtCodProducto.setText("");
                return;
            }
        }

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
                    seleccionarPLU pluu = new seleccionarPLU(null, true, "bdProductos");
                    pluu.setInstancias(instancias, nodo.getCodigo());
                    pluu.setOpc("diseño");
                    pluu.setVisible(true);
                    return;
                }
            }

            if (instancias.getSql().getProdActivo(nodo.getCodigo())) {
                metodos.msgError(this, "Este producto esta inactivo");
            } else {
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
                    UltimoPonderado ultimoPonderado = servicioActualizacionPonderado.obtenerUltimoPonderado(nodo.getIdSistema());
                    ponderado = ultimoPonderado.getNuevoPonderado();
                    ultimoCosto = ultimoPonderado.getUltimoCosto();
                } catch (SQLException ex) {
                    Logger.getLogger(VistaInventarioInicial.class.getName()).log(Level.SEVERE, null, ex);
                }

                modeloPro.addRow(new Object[]{nodo.getIdSistema(), desc, cantidad, plu, (big.getBigDecimal(cant2).multiply(big.getMoneda(cantidad))),
                    false, big.setMoneda(ponderado), big.setMoneda(ultimoCosto)});

                calcularCostoDiseno();

                tblProductos.setColumnSelectionInterval(2, 2);
                tblProductos.setRowSelectionInterval(tblProductos.getRowCount() - 1, tblProductos.getRowCount() - 1);
                tblProductos.editCellAt(tblProductos.getRowCount() - 1, 2);
                tblProductos.transferFocus();

                txtCodProducto.setText("");
            }
            return;
        }
        ventanaProductos(codigo);

    }

    public void ventanaProductos(String codigo) {
        buscProductos buscar = new buscProductos(instancias.getMenu(), rootPaneCheckingEnabled, false, "", "productos1");
        buscar.setOpc("diseño");
        buscar.setLocationRelativeTo(null);
        instancias.setBusProductos(buscar);
        instancias.setCampoActual(txtCodProducto);
        txtCodProducto.requestFocus();
//        buscar.setInstancia(instancias);
        buscar.noEncontrado(codigo);
        buscar.show();
    }

    public void ventanaProductos2() {
        buscProductos buscar = new buscProductos(instancias.getMenu(), rootPaneCheckingEnabled, false, "desdeCosteo", "productos1");
        buscar.setLocationRelativeTo(null);
        instancias.setBusProductos(buscar);
        instancias.setCampoActual(txtCodigo);
        txtCodigo.requestFocus();
//        buscar.setInstancia(instancias);
        buscar.show();
    }

    public void cargarProductoDiseno(String codigoProducto) {
        ndProducto nodo = instancias.getSql().getDatosProducto(codigoProducto.replace("'", "//"), "bdProductos");

        if (nodo.getCodigo() == null) {
            ventanaProductos2();
            return;
        }

        btnBusProd.setEnabled(true);
        txtCodProducto.setEnabled(true);
        txtCodigo.setEditable(false);
        txtCodigo.setText(nodo.getCodigo());
        txtDescripcion.setText(nodo.getDescripcion());

        try {
            String grupo = instancias.getSql().getDatosGrupoNombre(nodo.getGrupo());
            txtGrupo.setSelectedItem(grupo);
        } catch (Exception e) {
        }

        txtImpoconsumo.setText(nodo.getImpoconsumoVenta());
        txtIva.setText(nodo.getIva());
        txtL1.setText(big.setMoneda(big.getBigDecimal(nodo.getL1())));
        txtL2.setText(big.setMoneda(big.getBigDecimal(nodo.getL2())));
        txtL3.setText(big.setMoneda(big.getBigDecimal(nodo.getL3())));
        txtL4.setText(big.setMoneda(big.getBigDecimal(nodo.getL4())));
        txtPuntoMinimo.setText(nodo.getMinimo());
        txtMedida.setText(nodo.getUnd());
        txtDescripcion.requestFocus();
        btnActualizar.setEnabled(true);
        btnGuardar.setEnabled(false);

        if (nodo.getUsuario().equals(TipoProducto.PRODUCTO_DISENADO.getValue())) {
            chkManejarInventario.setSelected(true);
        } else {
            chkManejarInventario.setSelected(false);
        }

        txtInventario.setText(Utilidades.formatearCantidad(nodo.getFisicoInventario()));
        modeloPro = instancias.getSql().getProductosCosteo(nodo.getIdSistema());
        tblProductos.setModel(modeloPro);

        formatearFilasCosteo();
        configurarColumnasCosteo();
        calcularCostoDiseno();
    }

    private void formatearFilasCosteo() {
        for (int i = 0; i < tblProductos.getRowCount(); i++) {
            tblProductos.setValueAt(big.setMoneda(big.getMoneda(tblProductos.getValueAt(i, 6).toString().replace(".", ","))), i, 6);
            tblProductos.setValueAt(big.setMoneda(big.getMoneda(tblProductos.getValueAt(i, 7).toString().replace(".", ","))), i, 7);
            tblProductos.setValueAt(Utilidades.formatearCantidad(tblProductos.getValueAt(i, 2).toString()), i, 2);
            tblProductos.setValueAt(Utilidades.formatearCantidad(tblProductos.getValueAt(i, 4).toString()), i, 4);

            BigDecimal valor = big.getMoneda(tblProductos.getValueAt(i, 6).toString());
            BigDecimal cantidad = Utilidades.convertirBigDecimal(tblProductos.getValueAt(i, 4).toString());
            BigDecimal total = cantidad.multiply(valor);
            tblProductos.setValueAt(big.setMoneda(total), i, 8);
        }
    }

    private void actualizarCantidadEquivalente(int fila, BigDecimal cantidad) {
        String codigo = tblProductos.getValueAt(fila, 0).toString();
        switch (tblProductos.getValueAt(fila, 3).toString()) {
            case "1":
                tblProductos.setValueAt(Utilidades.formatearCantidad(cantidad), fila, 4);
                break;
            case "2":
                tblProductos.setValueAt(Utilidades.formatearCantidad(cantidad.multiply(factorConversion(codigo, 2))), fila, 4);
                break;
            case "3":
                tblProductos.setValueAt(Utilidades.formatearCantidad(cantidad.multiply(factorConversion(codigo, 3))), fila, 4);
                break;
            case "4":
                tblProductos.setValueAt(Utilidades.formatearCantidad(cantidad.multiply(factorConversion(codigo, 4))), fila, 4);
                break;
        }
    }

    private BigDecimal factorConversion(String codigo, int nivel) {
        ndProducto producto = instancias.getSql().getDatosProducto(codigo, "bdProductos");
        switch (nivel) {
            case 2:
                return big.getMoneda(producto.getCantidad2());
            case 3:
                return big.getMoneda(producto.getCantidad3());
            default:
                return big.getMoneda(producto.getCantidad4());
        }
    }

    private void configurarColumnasCosteo() {
        if (tblProductos.getColumnModel().getColumnCount() <= 0) {
            return;
        }

        tblProductos.getColumnModel().getColumn(0).setMinWidth(0);
        tblProductos.getColumnModel().getColumn(0).setPreferredWidth(0);
        tblProductos.getColumnModel().getColumn(0).setMaxWidth(0);
        tblProductos.getColumnModel().getColumn(2).setMinWidth(40);
        tblProductos.getColumnModel().getColumn(2).setPreferredWidth(75);
        tblProductos.getColumnModel().getColumn(2).setMaxWidth(100);
        tblProductos.getColumnModel().getColumn(3).setMinWidth(0);
        tblProductos.getColumnModel().getColumn(3).setPreferredWidth(0);
        tblProductos.getColumnModel().getColumn(3).setMaxWidth(0);
        tblProductos.getColumnModel().getColumn(4).setMinWidth(0);
        tblProductos.getColumnModel().getColumn(4).setPreferredWidth(0);
        tblProductos.getColumnModel().getColumn(4).setMaxWidth(0);

        if (instancias.getConfiguraciones().isRestaurante()) {
            tblProductos.getColumnModel().getColumn(5).setMinWidth(50);
            tblProductos.getColumnModel().getColumn(5).setPreferredWidth(50);
            tblProductos.getColumnModel().getColumn(5).setMaxWidth(50);
        } else {
            tblProductos.getColumnModel().getColumn(5).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(5).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(5).setMaxWidth(0);
        }

        tblProductos.getColumnModel().getColumn(6).setMinWidth(80);
        tblProductos.getColumnModel().getColumn(6).setPreferredWidth(110);
        tblProductos.getColumnModel().getColumn(6).setMaxWidth(130);
        tblProductos.getColumnModel().getColumn(7).setMinWidth(80);
        tblProductos.getColumnModel().getColumn(7).setPreferredWidth(110);
        tblProductos.getColumnModel().getColumn(7).setMaxWidth(130);
        tblProductos.getColumnModel().getColumn(8).setMinWidth(80);
        tblProductos.getColumnModel().getColumn(8).setPreferredWidth(110);
        tblProductos.getColumnModel().getColumn(8).setMaxWidth(130);
    }

    public void calcularCostoDiseno() {
        BigDecimal total = BigDecimal.ZERO;
        for (int i = 0; i < tblProductos.getRowCount(); i++) {
            BigDecimal cantidad = Utilidades.convertirBigDecimal(tblProductos.getValueAt(i, 2).toString());
            BigDecimal valor = big.getMoneda(tblProductos.getValueAt(i, 6).toString());
            BigDecimal subtotal = cantidad.multiply(valor);
            tblProductos.setValueAt(big.setMoneda(subtotal), i, 8);
            total = total.add(subtotal);
        }

        tblProductos.removeEditor();
        txtCostoTotal.setText(big.setMoneda(total));
        calcularUtilidad();
    }

    public void actualizarGrupos() {

        txtGrupo.removeAllItems();

        txtGrupo.addItem("");

        grupos = instancias.getSql().getRegistrosGrupos();
        for (Object[] grupo : grupos) {
            txtGrupo.addItem(grupo[1]);
        }
    }

    public void cargarProductos(Object[][] productos) {
        this.plu = false;

        for (int i = 0; i < productos.length; i++) {
            String codigo = productos[i][0].toString();
            String cantidad = productos[i][1].toString();
            if (cantidad.equals("0")) {
                cantidad = datos[87].toString();
            }
            cargarProducto(codigo, cantidad, 1);
            KeyEvent x = new KeyEvent(this, WIDTH, WIDTH, WIDTH, KeyEvent.VK_ENTER);
            tblProductosKeyReleased(x);
        }

        tblProductos.removeEditor();
        txtCodProducto.requestFocus();
    }

    private void guardarLineasCosteo(String codigoProducto, String usuario) {
        List<LineaCosteoDiseno> lineas = new ArrayList<>();

        String tipoProducto = chkManejarInventario.isSelected() ? "FACTURA" : "COSTEO";

        for (int i = 0; i < tblProductos.getRowCount(); i++) {
            lineas.add(new LineaCosteoDiseno(
                    tblProductos.getValueAt(i, 0).toString(),
                    obtenerMoneda(i, 2),
                    codigoProducto,
                    usuario,
                    tblProductos.getValueAt(i, 1).toString(),
                    tblProductos.getValueAt(i, 3).toString(),
                    obtenerMoneda(i, 4),
                    Boolean.TRUE.equals(tblProductos.getValueAt(i, 5)),
                    tipoProducto));
        }

        try {
            new DaoDiseno().guardarLineasCosteo(lineas);
        } catch (SQLException ex) {
            metodos.msgError(this, "Hubo un problema al guardar el costeo del producto");
            Logger.getLogger(VistaDiseno.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private BigDecimal obtenerMoneda(int fila, int columna) {
        try {
            return big.getMoneda((String) tblProductos.getValueAt(fila, columna));
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnBusProd;
    private javax.swing.JButton btnBusProd1;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox chkManejarInventario;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JLabel lbCiudad;
    private javax.swing.JLabel lbCupo;
    private javax.swing.JLabel lbCupo1;
    private javax.swing.JLabel lbCupo2;
    private javax.swing.JLabel lbDepartamento;
    private javax.swing.JLabel lbDepartamento2;
    private javax.swing.JLabel lbDepartamento4;
    private javax.swing.JLabel lbEmail;
    private javax.swing.JLabel lbEmail1;
    private javax.swing.JLabel lbFecha;
    private javax.swing.JLabel lbNit;
    private javax.swing.JLabel lbNit7;
    private javax.swing.JLabel lbNit8;
    private javax.swing.JLabel lbRazon;
    private javax.swing.JLabel lbRazon1;
    private javax.swing.JLabel lbTelefono;
    private javax.swing.JPanel pnlFormulario;
    private javax.swing.JPanel pnlInformacionDiseno;
    private javax.swing.JPanel pnlValores;
    private javax.swing.JMenuItem popBorrar;
    private javax.swing.JScrollPane scrFormulario;
    private javax.swing.JScrollPane scrProductos;
    private javax.swing.JTable tblProductos;
    private javax.swing.JTextField txtCodProducto;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtCostoTotal;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JComboBox txtGrupo;
    private javax.swing.JTextField txtImpoconsumo;
    private javax.swing.JTextField txtInventario;
    private javax.swing.JTextField txtIva;
    private javax.swing.JTextField txtL1;
    private javax.swing.JTextField txtL2;
    private javax.swing.JTextField txtL3;
    private javax.swing.JTextField txtL4;
    private javax.swing.JTextField txtMaximo;
    private javax.swing.JTextField txtMedida;
    private javax.swing.JTextField txtMinima;
    private javax.swing.JTextField txtPuntoMinimo;
    private javax.swing.JTextField txtUtilidad1;
    private javax.swing.JTextField txtUtilidad2;
    // End of variables declaration//GEN-END:variables
}
