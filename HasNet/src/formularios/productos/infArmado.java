package formularios.productos;

import Vista.Productos.VistaBuscadorProductos;
import Modelo.Inventario.UltimoPonderado;
import inventario.servicio.ServicioActualizacionPonderado;
import clases.Instancias;
import clases.productos.ndProducto;
import Vista.Productos.VistaInventarioInicial;
import clases.big;
import clases.metodosGenerales;
import formularios.terceros.buscBodegas;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;

public class infArmado extends javax.swing.JInternalFrame {

    private ServicioActualizacionPonderado servicioActualizacionPonderado = new ServicioActualizacionPonderado();

    DefaultTableModel modeloPro;
    metodosGenerales metodos = new metodosGenerales();
    Instancias instancias;
    String codigoPrincipal = "";
    int ser = 0;
    String tipoMovimiento = "";
    String numeroFactura = "";

    DecimalFormat df = new DecimalFormat("#.00");

    //Barra de titulo
    private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension dimBarra = null;

    public infArmado() {
        initComponents();
        modeloPro = (DefaultTableModel) tblProductos.getModel();

        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
        dimBarra = Barra.getPreferredSize();
        Barra.setSize(0, 0);
        Barra.setPreferredSize(new Dimension(0, 0));
        setBorder(null);
        repaint();

        instancias = Instancias.getInstancias();

        if (!instancias.getConfiguraciones().isInventarioBodegas()) {
            lbBodega.setVisible(false);
            btnBusBodega.setVisible(false);
            txtBodega.setVisible(false);
        }

        lbNoFactura.setText(instancias.getSql().getNumConsecutivo("CST")[0].toString());

        txtIdSistema.setVisible(false);
        pnlFormulario.registerKeyboardAction(accion("guardar"), "guardar", KeyStroke.getKeyStroke(KeyEvent.VK_G, Event.CTRL_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
        pnlFormulario.registerKeyboardAction(accion("limpiar"), "limpiar", KeyStroke.getKeyStroke(KeyEvent.VK_L, Event.CTRL_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
        pnlFormulario.registerKeyboardAction(accion("productos"), "productos", KeyStroke.getKeyStroke(KeyEvent.VK_P, Event.CTRL_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
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
        scrFormulario = new javax.swing.JScrollPane();
        pnlFormulario = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lbRazon = new javax.swing.JLabel();
        lbNit = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        txtCodigo = new javax.swing.JTextField();
        lbRazon1 = new javax.swing.JLabel();
        spCantidad = new javax.swing.JSpinner();
        txtValor2 = new javax.swing.JTextField();
        txtValor3 = new javax.swing.JTextField();
        lbNit8 = new javax.swing.JLabel();
        lbNit9 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnBusProd1 = new javax.swing.JButton();
        lbBodega = new javax.swing.JLabel();
        txtBodega = new javax.swing.JTextField();
        btnBusBodega = new javax.swing.JButton();
        btnBuscTerceros2 = new javax.swing.JButton();
        btnAnular = new javax.swing.JButton();
        lbFacturaNo = new javax.swing.JLabel();
        lbNoFactura = new javax.swing.JLabel();
        scrProductos = new javax.swing.JScrollPane();
        tblProductos = new javax.swing.JTable();
        txtCodProducto = new javax.swing.JTextField();
        btnBusProd = new javax.swing.JButton();
        txtIdSistema = new javax.swing.JTextField();

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
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Adobe Arabic", 1, 12))); // NOI18N

        lbRazon.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbRazon.setText("Descripción:");

        lbNit.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit.setText("Codigo: *");
        lbNit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lbNitKeyReleased(evt);
            }
        });

        txtDescripcion.setEditable(false);
        txtDescripcion.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtDescripcion.setName("Descripción"); // NOI18N
        txtDescripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDescripcionKeyReleased(evt);
            }
        });

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

        lbRazon1.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbRazon1.setText("Cantidad:");

        spCantidad.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        spCantidad.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
        spCantidad.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spCantidadStateChanged(evt);
            }
        });

        txtValor2.setEditable(false);
        txtValor2.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtValor2.setText("0");

        txtValor3.setEditable(false);
        txtValor3.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtValor3.setText("0");

        lbNit8.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbNit8.setText("Costo total:");

        lbNit9.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbNit9.setText("Costo Und:");

        btnNuevo.setBackground(new java.awt.Color(204, 204, 204));
        btnNuevo.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
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

        btnGuardar.setBackground(new java.awt.Color(46, 204, 113));
        btnGuardar.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
        btnGuardar.setText("GUARDAR");
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar.setEnabled(false);
        btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnGuardar.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
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

        lbBodega.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbBodega.setText("Bodega: *");
        lbBodega.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lbBodegaKeyReleased(evt);
            }
        });

        txtBodega.setBackground(new java.awt.Color(255, 204, 204));
        txtBodega.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        txtBodega.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtBodega.setText("123-22");
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

        btnBusBodega.setBackground(new java.awt.Color(204, 204, 204));
        btnBusBodega.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnBusBodega.setForeground(new java.awt.Color(255, 255, 255));
        btnBusBodega.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar1.png"))); // NOI18N
        btnBusBodega.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnBusBodega.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBusBodega.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnBusBodega.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnBusBodega.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBusBodegaActionPerformed(evt);
            }
        });

        btnBuscTerceros2.setBackground(new java.awt.Color(247, 220, 111));
        btnBuscTerceros2.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
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

        btnAnular.setBackground(new java.awt.Color(241, 148, 138));
        btnAnular.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
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

        lbFacturaNo.setBackground(new java.awt.Color(204, 204, 204));
        lbFacturaNo.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbFacturaNo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbFacturaNo.setText("Armado No.");
        lbFacturaNo.setOpaque(true);

        lbNoFactura.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbNoFactura.setForeground(new java.awt.Color(255, 0, 0));
        lbNoFactura.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNoFactura.setText("3");
        lbNoFactura.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnBuscTerceros2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnAnular, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE))
                        .addGap(67, 67, 67))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSeparator1)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lbRazon1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(spCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(lbNit8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                                    .addComponent(lbNit9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtValor3, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                                    .addComponent(txtValor2)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(lbNit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbRazon, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbBodega, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbFacturaNo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtBodega)
                                            .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(btnBusProd1, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                                            .addComponent(btnBusBodega, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addComponent(lbNoFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbNoFactura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbFacturaNo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtBodega, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbBodega, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBusBodega, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(lbNit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBusProd1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtDescripcion)
                    .addComponent(lbRazon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(spCantidad, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(lbRazon1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtValor2)
                    .addComponent(lbNit9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbNit8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtValor3))
                .addGap(42, 42, 42)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNuevo)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBuscTerceros2)
                    .addComponent(btnAnular))
                .addContainerGap())
        );

        tblProductos.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        tblProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Descripción", "Cantidad", "plu", "cantidad2", "Cambio", "Costo P.", "Ultimo Costo", "Subtotal", "Cambio"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, false, false, true, true, true, true, true
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
            tblProductos.getColumnModel().getColumn(2).setMinWidth(100);
            tblProductos.getColumnModel().getColumn(2).setPreferredWidth(100);
            tblProductos.getColumnModel().getColumn(2).setMaxWidth(100);
            tblProductos.getColumnModel().getColumn(3).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(3).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(3).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(4).setMinWidth(1);
            tblProductos.getColumnModel().getColumn(4).setPreferredWidth(100);
            tblProductos.getColumnModel().getColumn(4).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(5).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(5).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(5).setMaxWidth(0);
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

        txtCodProducto.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtCodProducto.setEnabled(false);
        txtCodProducto.setName("combo"); // NOI18N
        txtCodProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodProductoActionPerformed(evt);
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
        btnBusProd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBusProd.setEnabled(false);
        btnBusProd.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnBusProd.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnBusProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBusProdActionPerformed(evt);
            }
        });

        txtIdSistema.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtIdSistema.setName("Codigo"); // NOI18N
        txtIdSistema.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtIdSistemaFocusLost(evt);
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

        javax.swing.GroupLayout pnlFormularioLayout = new javax.swing.GroupLayout(pnlFormulario);
        pnlFormulario.setLayout(pnlFormularioLayout);
        pnlFormularioLayout.setHorizontalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdSistema, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrProductos, javax.swing.GroupLayout.DEFAULT_SIZE, 551, Short.MAX_VALUE)
                    .addGroup(pnlFormularioLayout.createSequentialGroup()
                        .addComponent(txtCodProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBusProd, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlFormularioLayout.setVerticalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlFormularioLayout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIdSistema, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlFormularioLayout.createSequentialGroup()
                        .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnBusProd, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                            .addComponent(txtCodProducto))
                        .addGap(5, 5, 5)
                        .addComponent(scrProductos, javax.swing.GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE)))
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
        int fila = tblProductos.getSelectedRow(), i = 2, j = 1;

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            //Si se esta cambiando el campo de cantidad
            if (tblProductos.getSelectedColumn() == 2) {
                txtCodProducto.requestFocus();
            }

            int res = 0;

            try {
                res = big.getBigDecimal(big.getMoneda((String) tblProductos.getValueAt(fila, i))).compareTo(big.getBigDecimal("0"));
            } catch (Exception e) {
                tblProductos.setValueAt("", fila, 4);
                metodos.msgError(this, "Un diseño fijo debe llevar las cantidades");
                return;
            }

            if (res == -1 || String.valueOf(tblProductos.getValueAt(fila, i)).equals("")) {
                tblProductos.setValueAt(j, tblProductos.getSelectedRow(), i);
            }

            BigDecimal cantidad = BigDecimal.ZERO;
            try {
                cantidad = big.getBigDecimal(tblProductos.getValueAt(fila, 2).toString().replace(",", "."));
            } catch (Exception e) {
            }

            String baseUtilizada = obtenerBase();

            if (cantidad.compareTo(BigDecimal.ZERO) == 0) {
                tblProductos.setValueAt("", fila, 2);
                tblProductos.setValueAt("", fila, 4);
            } else {
                switch (tblProductos.getValueAt(fila, 3).toString()) {
                    case "1":
                        tblProductos.setValueAt(big.setNumero(cantidad), fila, 4);
                        break;
                    case "2":
                        tblProductos.setValueAt((cantidad.multiply(big.getMoneda(instancias.getSql().getDatosProducto(tblProductos.getValueAt(fila, 0).toString(), baseUtilizada).getCantidad2()))).toString().replace(".", ","), fila, 4);
                        break;
                    case "3":
                        tblProductos.setValueAt((cantidad.multiply(big.getMoneda(instancias.getSql().getDatosProducto(tblProductos.getValueAt(fila, 0).toString(), baseUtilizada).getCantidad3()))).toString().replace(".", ","), fila, 4);
                        break;
                    case "4":
                        tblProductos.setValueAt((cantidad.multiply(big.getMoneda(instancias.getSql().getDatosProducto(tblProductos.getValueAt(fila, 0).toString(), baseUtilizada).getCantidad4()))).toString().replace(".", ","), fila, 4);
                        break;
                }
                tblProductos.setValueAt(big.setNumero(cantidad), fila, 2);
            }
            costo();
        }
    }//GEN-LAST:event_tblProductosKeyReleased

    private void txtCodProductoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodProductoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String codigo = txtCodProducto.getText();

            String baseUtilizada = obtenerBase();
            cargarProducto(codigo.replace("'", "//"), baseUtilizada);
        }
    }//GEN-LAST:event_txtCodProductoKeyReleased

    private void tblProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductosMouseClicked

    }//GEN-LAST:event_tblProductosMouseClicked

    private void tblProductosMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductosMouseReleased

    }//GEN-LAST:event_tblProductosMouseReleased

    private void txtCodProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodProductoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        tblProductos.removeEditor();

        int xyz = tblProductos.getRowCount();

        if (xyz > 0) {
            for (int i = 0; i < xyz; i++) {
                if (tblProductos.getValueAt(i, 2).equals("0") || tblProductos.getValueAt(i, 2).equals("")) {
                    metodos.msgError(this, "Todos los productos deben de tener cantidad");
                    return;
                }

                tblProductos.setColumnSelectionInterval(0, 0);
                tblProductos.setRowSelectionInterval(i, i);
                KeyEvent x = new KeyEvent(Barra, WIDTH, WIDTH, WIDTH, KeyEvent.VK_ENTER);
                tblProductosKeyReleased(x);
            }
        }

        if (tblProductos.getRowCount() == 0) {
            metodos.msgError(this, "No ha añadido ningun producto");
        } else {

            if (metodos.msgPregunta(this, "¿Desea continuar?") == 0) {
            } else {
                return;
            }

            String doc = "CST-" + instancias.getSql().getNumConsecutivo("CST")[0];
            int cant = 1;
            try {
                cant = Integer.parseInt(spCantidad.getValue().toString().substring(0, spCantidad.getValue().toString().length() - 2));
            } catch (Exception e) {
                cant = Integer.parseInt(spCantidad.getValue().toString());
            }

            if (!instancias.getSql().agregarCosteo(doc, metodos.fechaConsulta(metodosGenerales.fecha()), instancias.getUsuario(), String.valueOf(cant),
                    txtIdSistema.getText(), "COSTEO", big.getMoneda(txtValor3.getText()), txtBodega.getText())) {
                metodos.msgError(this, "Error al guardar el costeo");
                return;
            }

            for (int i = 0; i < tblProductos.getRowCount(); i++) {
                if (!instancias.getSql().agregarCosteoProd(doc, tblProductos.getValueAt(i, 0).toString(),
                        big.getMoneda(tblProductos.getValueAt(i, 4).toString()).toString(),
                        big.getMoneda(tblProductos.getValueAt(i, 4).toString()).multiply(big.getBigDecimal(cant)).toString())) {
                    metodos.msgError(this, "Error al guardar detalle costeo");
                    return;
                }
            }

            String baseUtilizada = obtenerBase();

            ndProducto producto = instancias.getSql().getDatosProducto(txtCodigo.getText(), baseUtilizada);
            double cantidad;
            double fisicoInventario;
            double inventario;

            try {
                cantidad = Double.parseDouble(producto.getCosteo().replace(",", "."));
            } catch (Exception e) {
                cantidad = 0;
            }

            try {
                fisicoInventario = Double.parseDouble(producto.getFisicoInventario().replace(",", "."));
            } catch (Exception e) {
                fisicoInventario = Double.parseDouble(producto.getInventario().replace(",", "."));
            }

            try {
                inventario = Double.parseDouble(producto.getInventario().replace(",", "."));
            } catch (Exception e) {
                inventario = Double.parseDouble(producto.getInventario().replace(",", "."));
            }

            fisicoInventario = fisicoInventario + Double.parseDouble(spCantidad.getValue().toString().replace(",", "."));
            inventario = inventario + Double.parseDouble(spCantidad.getValue().toString().replace(",", "."));
            double total = cantidad + Double.parseDouble(spCantidad.getValue().toString().replace(",", "."));

            String total1 = String.valueOf(df.format(total)).replace(".", ",");
            String fisicoInventario1 = String.valueOf(df.format(fisicoInventario)).replace(".", ",");
            String inventario1 = String.valueOf(df.format(inventario)).replace(".", ",");

            instancias.getSql().modificarInventario("costeo", total1, codigoPrincipal, baseUtilizada);
            instancias.getSql().modificarInventario("fisicoInventario", fisicoInventario1, codigoPrincipal, baseUtilizada);
            instancias.getSql().modificarInventario("inventario", inventario1, codigoPrincipal, baseUtilizada);

            if (!instancias.getSql().aumentarConsecutivo("CST", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("CST")[0]) + 1)) {
                metodos.msgError(this, "Hubo un problema al guardar en el consecutivo");
            }

            String tipo = instancias.getImpresion();
            if (tipo.equals("pos")) {
                tipo = "Pos";
            } else {
                tipo = "";
            }

            if (metodos.msgPregunta(null, "¿Desea imprimir el armado?") == 0) {
                instancias.getReporte().ver_Armado(doc, tipo);
            }

            metodos.msgExito(this, "Armado guardado con exito");
            btnNuevoActionPerformed(evt);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void popBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popBorrarActionPerformed

        if (tblProductos.getSelectedRow() > -1) {
            int fila = tblProductos.getSelectedRow();

            DefaultTableModel modelo = (DefaultTableModel) tblProductos.getModel();
            modelo.removeRow(fila);

            costo();

        } else {
            metodos.msgAdvertencia(this, "Seleccione un producto");
        }
    }//GEN-LAST:event_popBorrarActionPerformed

    private void txtDescripcionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            spCantidad.requestFocus();
        }
    }//GEN-LAST:event_txtDescripcionKeyReleased

    private void txtCodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txtCodigo.getText().equals("")) {
                ventanaProductos2();
                return;
            }

            String baseUtilizada = obtenerBase();
            ndProducto nodo = instancias.getSql().getDatosProducto(txtCodigo.getText().replace("'", "//"), baseUtilizada);
            if (nodo.getIdSistema() == null) {
                btnBusProd.setEnabled(false);
                txtCodProducto.setEnabled(false);
                txtDescripcion.requestFocus();
            } else {
                cargarProductoPrincipal(txtCodigo.getText());
            }
        }
    }//GEN-LAST:event_txtCodigoKeyReleased

    private void txtCodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyTyped

    }//GEN-LAST:event_txtCodigoKeyTyped

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        lbNoFactura.setText(instancias.getSql().getNumConsecutivo("CST")[0].toString());
        txtCodigo.setEditable(true);

        for (int x = 0; x < jPanel2.getComponentCount(); x++) {
            if (jPanel2.getComponent(x) instanceof JTextField) {
                JTextField textField = (JTextField) jPanel2.getComponent(x);
                textField.setText("");
            }
        }

        DefaultTableModel y = (DefaultTableModel) tblProductos.getModel();

        int i, j = tblProductos.getRowCount();

        for (i = 0; i < j; i++) {
            y.removeRow(0);
        }

        txtCodigo.requestFocus();
        btnGuardar.setEnabled(false);
        btnBusProd.setEnabled(false);
        txtCodProducto.setEnabled(false);
        txtBodega.setText("123-22");
        spCantidad.setValue(1);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void spCantidadStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spCantidadStateChanged
        costo();
    }//GEN-LAST:event_spCantidadStateChanged

    private void lbNitKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lbNitKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtCodProducto.requestFocus();
        }
    }//GEN-LAST:event_lbNitKeyReleased

    private void txtCodProductoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodProductoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodProductoKeyPressed

    private void btnBusProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBusProdActionPerformed
        ventanaProductos("");
    }//GEN-LAST:event_btnBusProdActionPerformed

    private void btnBusProd1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBusProd1ActionPerformed
        ventanaProductos2();
    }//GEN-LAST:event_btnBusProd1ActionPerformed

    private void txtIdSistemaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtIdSistemaFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdSistemaFocusLost

    private void txtIdSistemaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdSistemaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdSistemaKeyReleased

    private void txtIdSistemaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdSistemaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdSistemaKeyTyped

    private void lbBodegaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lbBodegaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_lbBodegaKeyReleased

    private void txtBodegaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtBodegaMouseClicked
        if (!txtBodega.getText().equals("")) {
            if (tblProductos.getRowCount() > 0) {
                if (metodos.msgPregunta(null, "¿Limpiar armado?") != 0) {
                    txtCodigo.requestFocus();
                    return;
                } else {
                    btnNuevoActionPerformed(null);
                }
            }
        }
    }//GEN-LAST:event_txtBodegaMouseClicked

    private void txtBodegaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtBodegaMouseEntered

    }//GEN-LAST:event_txtBodegaMouseEntered

    private void txtBodegaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtBodegaMousePressed
        if (!txtBodega.getText().equals("")) {
            if (tblProductos.getRowCount() > 0) {
                if (metodos.msgPregunta(null, "¿Limpiar armado?") != 0) {
                    txtCodigo.requestFocus();
                    return;
                } else {
                    btnNuevoActionPerformed(null);
                }
            }
        }
    }//GEN-LAST:event_txtBodegaMousePressed

    private void txtBodegaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtBodegaMouseReleased
        if (!txtBodega.getText().equals("")) {
            if (tblProductos.getRowCount() > 0) {
                if (metodos.msgPregunta(null, "¿Limpiar armado?") != 0) {
                    txtCodigo.requestFocus();
                    return;
                } else {
                    btnNuevoActionPerformed(null);
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
                txtCodigo.requestFocus();
            }
        } else {
            txtBodega.setText("");
            if (tblProductos.getRowCount() > 0) {
                btnNuevoActionPerformed(null);
            }
        }
    }//GEN-LAST:event_txtBodegaKeyReleased

    private void btnBusBodegaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBusBodegaActionPerformed
        ventanaBodegas("");
    }//GEN-LAST:event_btnBusBodegaActionPerformed

    private void btnBuscTerceros2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscTerceros2ActionPerformed
        String consecutivo = "CST-" + metodos.msgIngresarEnter(null, "Documento a reimprimir");
        if (consecutivo.equals("CST-")) {
            return;
        }

        boolean anulado = false;
        try {
            anulado = instancias.getSql().getDocumentoAnulado("bdCosteo", "Where Id='" + consecutivo + "' ");
        } catch (Exception e) {
            metodos.msgError(null, "El documento no existe");
            return;
        }

        if (anulado) {
            metodos.msgAdvertenciaAjustado(null, "El documento se encuentra anulado");
            return;
        }

        String tipo = instancias.getImpresion();
        if (tipo.equals("pos")) {
            tipo = "Pos";
        } else {
            tipo = "";
        }

        instancias.getReporte().ver_Armado(consecutivo, tipo);
    }//GEN-LAST:event_btnBuscTerceros2ActionPerformed

    private void btnAnularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnularActionPerformed
        String consecutivo = "CST-" + metodos.msgIngresarEnter(null, "Documento a anular");
        if (consecutivo.equals("CST-")) {
            return;
        }

        boolean anulado = false;
        try {
            anulado = instancias.getSql().getDocumentoAnulado("bdCosteo", "Where Id='" + consecutivo + "' ");
        } catch (Exception e) {
            metodos.msgError(null, "El documento no existe");
            return;
        }

        if (anulado) {
            metodos.msgAdvertenciaAjustado(null, "El documento se encuentra anulado");
            return;
        }

        if (metodos.msgPregunta(null, "¿Desea anular el armado?") == 0) {
            Object[][] productos = instancias.getSql().getArmado(consecutivo);

            String baseUtilizada = "";
            try {
                baseUtilizada = instancias.getSql().getBodegaMovimiento(consecutivo, "bdCosteo");
            } catch (Exception e) {
            }

            if ("".equals(baseUtilizada)) {
                baseUtilizada = "123-22";
            }

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

            Object[][] productoFinal = instancias.getSql().getArmado1(consecutivo);
            ndProducto insumo = instancias.getSql().getDatosProducto(productoFinal[0][0].toString(), baseUtilizada);

            double armado;
            double inventario;
            double fisicoInventario;

            try {
                armado = Double.parseDouble(insumo.getCosteo().replace(",", "."));
            } catch (Exception e) {
                armado = 0;
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

            int cantArmada = 0;
            try {
                cantArmada = Integer.parseInt(productoFinal[0][1].toString());
            } catch (Exception e) {
                cantArmada = Integer.parseInt(productoFinal[0][1].toString().substring(0, productoFinal[0][1].toString().length() - 3));
            }

            inventario = inventario - cantArmada;
            fisicoInventario = fisicoInventario - cantArmada;
            double total = armado - cantArmada;

            String total1 = String.valueOf(df.format(total)).replace(".", ",");
            String inventario1 = String.valueOf(df.format(inventario)).replace(".", ",");
            String fisicoInventario1 = String.valueOf(df.format(fisicoInventario)).replace(".", ",");

            instancias.getSql().modificarInventario("costeo", total1, productoFinal[0][0].toString(), baseUtilizada);
            instancias.getSql().modificarInventario("inventario", inventario1, productoFinal[0][0].toString(), baseUtilizada);
            instancias.getSql().modificarInventario("fisicoInventario", fisicoInventario1, productoFinal[0][0].toString(), baseUtilizada);
            if (!instancias.getSql().anularDocumento(consecutivo, "bdCosteo")) {
                metodos.msgError(null, "Error al anular el armado");
                return;
            }

            metodos.msgExito(null, "Armado anulado con éxito");
            btnNuevoActionPerformed(evt);
        }
    }//GEN-LAST:event_btnAnularActionPerformed

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

    public void ventanaBodegas(String nit) {
        buscBodegas buscar = new buscBodegas(instancias.getMenu(), true, "INTERNA");
        buscar.setLocationRelativeTo(null);
        instancias.setBuscBodegas(buscar);
        instancias.setCampoActual(txtBodega);
        txtBodega.requestFocus();
        buscar.noEncontrado(nit);
        buscar.show();
    }

    public void ventanaProductos2() {
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
            metodos.msgAdvertenciaAjustado(this, "Seleccione una bodega...");
            return;
        }

        VistaBuscadorProductos buscar = new VistaBuscadorProductos(instancias.getMenu(), rootPaneCheckingEnabled, false, "desdeCosteo", base);
        buscar.setLocationRelativeTo(null);
        instancias.setBusProductos(buscar);
        instancias.setCampoActual(txtCodigo);
        txtCodigo.requestFocus();
        buscar.show();
    }

    public void cargarProducto(String codigo, String cantidad, String bodega) {

        ndProducto nodo = instancias.getSql().getDatosProducto(codigo, bodega);

        if (nodo.getGrupo() != null) {
            if (nodo.getGrupo().equals("GRP-02")) {
                return;
            }
        }

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

        if (nodo.getIdSistema() != null) {
            if (instancias.getSql().getProdActivo(nodo.getIdSistema())) {
                metodos.msgError(this, "Este producto esta inactivo");
            } else {

                BigDecimal ponderado = BigDecimal.ZERO;
                BigDecimal ultimoCosto = BigDecimal.ZERO;
                try {
                    UltimoPonderado ultimoPonderado = servicioActualizacionPonderado.obtenerUltimoPonderado(nodo.getIdSistema());
                    ponderado = ultimoPonderado.getNuevoPonderado();
                    ultimoCosto = ultimoPonderado.getUltimoCosto();
                } catch (SQLException ex) {
                    Logger.getLogger(VistaInventarioInicial.class.getName()).log(Level.SEVERE, null, ex);
                }

                modeloPro.addRow(new Object[]{nodo.getIdSistema(), nodo.getDescripcion(), cantidad, 1, cantidad,
                    false, big.setMoneda(ponderado), big.setMoneda(ultimoCosto)});

                costo();

                tblProductos.setColumnSelectionInterval(2, 2);
                tblProductos.setRowSelectionInterval(tblProductos.getRowCount() - 1, tblProductos.getRowCount() - 1);
                tblProductos.editCellAt(tblProductos.getRowCount() - 1, 2);
                tblProductos.transferFocus();
                txtCodProducto.setText("");

            }
        }
    }

    public void cargarProducto(String codigo, String bodega) {

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
            if (instancias.getSql().getProdActivo(nodo.getCodigo())) {
                metodos.msgError(this, "Este producto esta inactivo");
            } else {
                BigDecimal ponderado = BigDecimal.ZERO;
                BigDecimal ultimoCosto = BigDecimal.ZERO;
                try {
                    UltimoPonderado ultimoPonderado = servicioActualizacionPonderado.obtenerUltimoPonderado(nodo.getIdSistema());
                    ponderado = ultimoPonderado.getNuevoPonderado();
                    ultimoCosto = ultimoPonderado.getUltimoCosto();
                } catch (SQLException ex) {
                    Logger.getLogger(VistaInventarioInicial.class.getName()).log(Level.SEVERE, null, ex);
                }

                modeloPro.addRow(new Object[]{nodo.getIdSistema(), nodo.getDescripcion(), "1", 1,
                    (big.getBigDecimal(1).multiply(big.getMoneda("1"))),
                    false, big.setMoneda(ponderado), big.setMoneda(ultimoCosto)});

                costo();

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

        VistaBuscadorProductos buscar = new VistaBuscadorProductos(instancias.getMenu(), rootPaneCheckingEnabled, false, "", base);
        buscar.setLocationRelativeTo(null);
        instancias.setBusProductos(buscar);
        instancias.setCampoActual(txtCodProducto);
        txtCodProducto.requestFocus();
//        buscar.setInstancia(instancias);
        buscar.noEncontrado(codigo);
        buscar.show();
    }

    public void cargarCodigo(String cod) {
        txtCodigo.setText(cod);
        txtCodProducto.requestFocus();
    }

    public void cargarProductoPrincipal(String cod) {
        if (cod.equals("")) {
            metodos.msgAdvertencia(this, "Debe ingresar el codigo");
        } else {
            ndProducto nodo = null;

            String codigoProd = "";
            if (cod.equals("")) {
                codigoProd = "";
            } else {
                Object[][] listado = instancias.getSql().getCodigosRelacionados(cod, " where codigo");
                if (listado.length > 0) {
                    cod = listado[0][0].toString();
                }

                String baseUtilizada = obtenerBase();
                nodo = instancias.getSql().getDatosProducto(cod, baseUtilizada);
                if (nodo.getIdSistema() != null) {
                    codigoProd = nodo.getIdSistema();
                }
            }

            if (!codigoProd.equals("")) {

                txtCodigo.setEditable(false);

                codigoPrincipal = nodo.getIdSistema();
                txtCodigo.setText(nodo.getCodigo());
                txtIdSistema.setText(nodo.getIdSistema());

                btnGuardar.setEnabled(true);
                btnBusProd.setEnabled(true);
                txtCodProducto.setEnabled(true);

                txtDescripcion.requestFocus();
                txtDescripcion.setText(nodo.getDescripcion());

                modeloPro = instancias.getSql().getProductosCosteo(nodo.getIdSistema());
                tblProductos.setModel(modeloPro);

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

                for (int i = 0; i < tblProductos.getRowCount(); i++) {
                    tblProductos.setValueAt(tblProductos.getValueAt(i, 2).toString().replace(".", ","), i, 2);
                    tblProductos.setValueAt(big.setMoneda(big.getMoneda(tblProductos.getValueAt(i, 6).toString().replace(".", ","))), i, 6);

                    try {
                        tblProductos.setValueAt(big.setMoneda(big.getMoneda(tblProductos.getValueAt(i, 7).toString().replace(".", ","))), i, 7);
                    } catch (Exception e) {
                        tblProductos.setValueAt(big.setMoneda(big.getMoneda(tblProductos.getValueAt(i, 7).toString().replace(".", ""))), i, 7);
                    }

                    BigDecimal cant, valor, total;
                    valor = big.getMoneda(tblProductos.getValueAt(i, 6).toString());

                    try {
                        cant = big.getMoneda(tblProductos.getValueAt(i, 4).toString());
                    } catch (Exception e) {
                        cant = BigDecimal.ZERO;
                    }

                    total = cant.multiply(valor);
                    tblProductos.setValueAt(big.setMoneda(total), i, 8);
                }

                costo();
                return;
            }

            metodos.msgError(this, "El producto no existe");
        }
    }

    public void costo() {
        tblProductos.removeEditor();

        BigDecimal total = big.getBigDecimal("0");
        for (int i = 0; i < tblProductos.getRowCount(); i++) {

            BigDecimal cant = BigDecimal.ZERO, valor, subtotal;
            valor = big.getMoneda(tblProductos.getValueAt(i, 6).toString());

            try {
                cant = big.getMoneda(tblProductos.getValueAt(i, 2).toString());
            } catch (Exception e) {
            }

            subtotal = cant.multiply(valor);
            tblProductos.setValueAt(big.setMoneda(subtotal), i, 8);
            total = total.add(big.getMoneda(tblProductos.getValueAt(i, 8).toString()));
        }

        BigDecimal cant = big.getBigDecimal(spCantidad.getValue());
        BigDecimal total1 = big.getBigDecimal("0");

        for (int i = 0; i < tblProductos.getRowCount(); i++) {
            total1 = total1.add(big.getMoneda(tblProductos.getValueAt(i, 8).toString()));
        }

        txtValor2.setText(big.setMoneda(total1));
        txtValor3.setText(big.setMoneda(total1.multiply(cant)));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnular;
    private javax.swing.JButton btnBusBodega;
    private javax.swing.JButton btnBusProd;
    private javax.swing.JButton btnBusProd1;
    private javax.swing.JButton btnBuscTerceros2;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbBodega;
    private javax.swing.JLabel lbFacturaNo;
    private javax.swing.JLabel lbNit;
    private javax.swing.JLabel lbNit8;
    private javax.swing.JLabel lbNit9;
    private javax.swing.JLabel lbNoFactura;
    private javax.swing.JLabel lbRazon;
    private javax.swing.JLabel lbRazon1;
    private javax.swing.JPanel pnlFormulario;
    private javax.swing.JMenuItem popBorrar;
    private javax.swing.JScrollPane scrFormulario;
    private javax.swing.JScrollPane scrProductos;
    private javax.swing.JSpinner spCantidad;
    private javax.swing.JTable tblProductos;
    private javax.swing.JTextField txtBodega;
    private javax.swing.JTextField txtCodProducto;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtIdSistema;
    private javax.swing.JTextField txtValor2;
    private javax.swing.JTextField txtValor3;
    // End of variables declaration//GEN-END:variables
}
