package Vista.Productos;

import Enums.EstadosDetalleProducto;
import Enums.TipoDocumento;
import Enums.TipoProducto;
import Enums.enumBodegas;
import ImpresionesProductos.GenerarReportes;
import Modelo.Inventario.DetalleProducto;
import Modelo.Inventario.MovimientoInventario;
import Modelo.Inventario.UltimoPonderado;
import Modelo.Productos.ResultadoBusquedaProducto;
import Modelo.Productos.ResultadoPluProducto;
import Utilidades.DatosMaestra;
import inventario.dao.DaoDetalleProducto;
import inventario.servicio.ServicioActualizacionPonderado;
import inventario.servicio.ServicioInventario;
import Utilidades.DetalleProducto.UtilidadesDetalleProducto;
import Utilidades.Utilidades;
import clases.Instancias;
import clases.productos.ndProducto;
import clases.productos.ndTraslado;
import clases.big;
import clases.metodosGenerales;
import clases.productos.ndCompra;
import clases.productos.ndProductoAjustes;
import dao.Productos.DaoProducto;
import inventario.vista.VistaMovimientoDetalleProducto;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class VistaAjusteInventario extends javax.swing.JInternalFrame implements ReceptorDetallado, ReceptorProductoSalida {

    private final ServicioActualizacionPonderado servicioActualizacionPonderado = new ServicioActualizacionPonderado();
    private final DaoDetalleProducto daoDetalleProducto = new DaoDetalleProducto();
    private final DaoProducto daoProducto = new DaoProducto();

    String simbolo = "";
    DefaultTableModel modeloTablaProductos;
    DefaultTableModel modeloPro1;
    Boolean preguntaLimpiar = true;
    metodosGenerales metodos = new metodosGenerales();
    Instancias instancias;
    Object[] datos;

    //Barra de titulo
    private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension dimBarra = null;
    private String datosEmpresa, cantidadTotal;
    private boolean plu = false;
    private boolean saltarPasos = false;

    public boolean isPlu() {
        return plu;
    }

    public void setPlu(boolean plu) {
        this.plu = plu;
    }

    public VistaAjusteInventario() {
        initComponents();
        modeloTablaProductos = (DefaultTableModel) tblProductos.getModel();
        modeloPro1 = (DefaultTableModel) tblDetalle.getModel();

//        tblProductos.setDefaultRenderer(Object.class, new cambiarColorTabla(2, 0));
        instancias = Instancias.getInstancias();
        lbNoFactura.setText((String) instancias.getSql().getNumConsecutivo("TRAS")[0]);

        txtFecha.setText(metodos.fecha());
        simbolo = instancias.getSimbolo();

        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
        dimBarra = Barra.getPreferredSize();
        Barra.setSize(0, 0);
        Barra.setPreferredSize(new Dimension(0, 0));
        setBorder(null);
        repaint();

        consultarMaestros();

        if (instancias.getConfiguraciones().getTipoImpresion().equals("Sin-Codigo")) {
            tblProductos.getColumnModel().getColumn(0).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(0).setMaxWidth(0);
        }

        pnlFormulario.registerKeyboardAction(accion("guardar"), "guardar", KeyStroke.getKeyStroke(KeyEvent.VK_G, Event.CTRL_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
        pnlFormulario.registerKeyboardAction(accion("limpiar"), "limpiar", KeyStroke.getKeyStroke(KeyEvent.VK_L, Event.CTRL_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);

        actualizarColumnasTabla();
        cargarProductosPrecargados();

        pnlInvisible.setVisible(false);
    }

    public void consultarPermiso() {
        if (cmbTipoAjuste.getSelectedIndex() == 0) {
            if (!instancias.getUsuarioLog().isAjustesEntrada()) {
                metodos.msgError(this, "No tiene permisos");
                btnGuardar.setEnabled(false);
                btnLimpiar.setEnabled(false);
                btnReimprimir.setEnabled(false);
                btnAnular.setEnabled(false);
            } else {
                btnGuardar.setEnabled(true);
                btnLimpiar.setEnabled(true);
                btnReimprimir.setEnabled(true);
                btnAnular.setEnabled(true);
            }
        } else {
            if (!instancias.getUsuarioLog().isAjustesSalida()) {
                metodos.msgError(this, "No tiene permisos");
                btnGuardar.setEnabled(false);
                btnLimpiar.setEnabled(false);
                btnReimprimir.setEnabled(false);
                btnAnular.setEnabled(false);
            } else {
                btnGuardar.setEnabled(true);
                btnLimpiar.setEnabled(true);
                btnReimprimir.setEnabled(true);
                btnAnular.setEnabled(true);
            }
        }
    }

    public void consultarMaestros() {
        datos = instancias.getSql().getDatosMaestra();
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
        scrFormulario = new javax.swing.JScrollPane();
        pnlFormulario = new javax.swing.JPanel();
        pnlInvisible = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblDetalle = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        lbProducto = new javax.swing.JLabel();
        txtCodigoProducto = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProductos = new javax.swing.JTable();
        lbProducto1 = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        btnBusProd = new javax.swing.JButton();
        pnlBotones = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnReimprimir = new javax.swing.JButton();
        btnAnular = new javax.swing.JButton();
        lbNit4 = new javax.swing.JLabel();
        cmbTipoAjuste = new javax.swing.JComboBox();
        lbNit3 = new javax.swing.JLabel();
        txtValor = new javax.swing.JTextField();
        lbNit5 = new javax.swing.JLabel();
        lbNoFactura = new javax.swing.JLabel();
        lbNit6 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JLabel();

        popBorrar.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        popBorrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/eliminar-cancelar-icono-4935-16.png"))); // NOI18N
        popBorrar.setText("Borrar");
        popBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                popBorrarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(popBorrar);

        setBackground(new java.awt.Color(255, 255, 255));
        setTitle("Factura");

        pnlFormulario.setBackground(new java.awt.Color(255, 255, 255));

        tblDetalle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cod", "Imei", "Lote", "F.Vence", "Temp", "cant", "descripcion", "color", "talla"
            }
        ));
        jScrollPane3.setViewportView(tblDetalle);

        javax.swing.GroupLayout pnlInvisibleLayout = new javax.swing.GroupLayout(pnlInvisible);
        pnlInvisible.setLayout(pnlInvisibleLayout);
        pnlInvisibleLayout.setHorizontalGroup(
            pnlInvisibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInvisibleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        pnlInvisibleLayout.setVerticalGroup(
            pnlInvisibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlInvisibleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        lbProducto.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbProducto.setText("Producto:");
        lbProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lbProductoKeyReleased(evt);
            }
        });

        txtCodigoProducto.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        txtCodigoProducto.setName("combo"); // NOI18N
        txtCodigoProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoProductoKeyReleased(evt);
            }
        });

        tblProductos.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        tblProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Descripcion", "Valor/Unit", "Cant", "Subtotal", "Iva %", "Iva", "Total", "Observaciones", "plu", "cant2", "Detalle", "Inv. Inicial", "Inv. Final", "idProd", "idSistema", "costo", "porcImpo", "impoconsumo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true, false, true, true, false, true, false, false, false, false, false, false, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblProductos.setComponentPopupMenu(jPopupMenu1);
        tblProductos.setRowHeight(27);
        tblProductos.getTableHeader().setReorderingAllowed(false);
        tblProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductosMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tblProductosMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblProductosMousePressed(evt);
            }
        });
        tblProductos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblProductosKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblProductos);
        if (tblProductos.getColumnModel().getColumnCount() > 0) {
            tblProductos.getColumnModel().getColumn(0).setMinWidth(100);
            tblProductos.getColumnModel().getColumn(0).setPreferredWidth(200);
            tblProductos.getColumnModel().getColumn(0).setMaxWidth(300);
            tblProductos.getColumnModel().getColumn(2).setMinWidth(80);
            tblProductos.getColumnModel().getColumn(2).setPreferredWidth(100);
            tblProductos.getColumnModel().getColumn(2).setMaxWidth(150);
            tblProductos.getColumnModel().getColumn(3).setMinWidth(50);
            tblProductos.getColumnModel().getColumn(3).setPreferredWidth(50);
            tblProductos.getColumnModel().getColumn(3).setMaxWidth(50);
            tblProductos.getColumnModel().getColumn(4).setMinWidth(80);
            tblProductos.getColumnModel().getColumn(4).setPreferredWidth(100);
            tblProductos.getColumnModel().getColumn(4).setMaxWidth(150);
            tblProductos.getColumnModel().getColumn(5).setMinWidth(30);
            tblProductos.getColumnModel().getColumn(5).setPreferredWidth(50);
            tblProductos.getColumnModel().getColumn(5).setMaxWidth(70);
            tblProductos.getColumnModel().getColumn(6).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(6).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(6).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(7).setMinWidth(80);
            tblProductos.getColumnModel().getColumn(7).setPreferredWidth(100);
            tblProductos.getColumnModel().getColumn(7).setMaxWidth(150);
            tblProductos.getColumnModel().getColumn(8).setMinWidth(150);
            tblProductos.getColumnModel().getColumn(8).setPreferredWidth(200);
            tblProductos.getColumnModel().getColumn(8).setMaxWidth(230);
            tblProductos.getColumnModel().getColumn(9).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(9).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(9).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(10).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(10).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(10).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(11).setMinWidth(150);
            tblProductos.getColumnModel().getColumn(11).setPreferredWidth(200);
            tblProductos.getColumnModel().getColumn(11).setMaxWidth(230);
            tblProductos.getColumnModel().getColumn(12).setMinWidth(75);
            tblProductos.getColumnModel().getColumn(12).setPreferredWidth(75);
            tblProductos.getColumnModel().getColumn(12).setMaxWidth(75);
            tblProductos.getColumnModel().getColumn(13).setMinWidth(75);
            tblProductos.getColumnModel().getColumn(13).setPreferredWidth(75);
            tblProductos.getColumnModel().getColumn(13).setMaxWidth(75);
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
        }

        lbProducto1.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbProducto1.setText("Cantidad:");
        lbProducto1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lbProducto1KeyReleased(evt);
            }
        });

        txtCantidad.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        txtCantidad.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCantidad.setText("1");
        txtCantidad.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtCantidad.setEnabled(false);
        txtCantidad.setName("combo"); // NOI18N
        txtCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCantidadKeyReleased(evt);
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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lbProducto1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(lbProducto)
                        .addGap(2, 2, 2)
                        .addComponent(txtCodigoProducto, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
                        .addGap(2, 2, 2)
                        .addComponent(btnBusProd, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(5, 5, 5))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lbProducto1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCantidad, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbProducto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCodigoProducto, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBusProd, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );

        pnlBotones.setBackground(new java.awt.Color(255, 255, 255));
        pnlBotones.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

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

        btnLimpiar.setBackground(new java.awt.Color(204, 204, 204));
        btnLimpiar.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/limpiar.png"))); // NOI18N
        btnLimpiar.setText("LIMPIAR");
        btnLimpiar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLimpiar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnLimpiar.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        btnReimprimir.setBackground(new java.awt.Color(247, 220, 111));
        btnReimprimir.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnReimprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/imprimir.png"))); // NOI18N
        btnReimprimir.setText("REIMPRIMIR");
        btnReimprimir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReimprimir.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnReimprimir.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnReimprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReimprimirActionPerformed(evt);
            }
        });

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

        lbNit4.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbNit4.setText("Tipo ajuste:");

        cmbTipoAjuste.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        cmbTipoAjuste.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ajustes Entrada", "Ajuste Salida" }));
        cmbTipoAjuste.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbTipoAjusteItemStateChanged(evt);
            }
        });

        lbNit3.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbNit3.setText("Valor ajuste:");

        txtValor.setEditable(false);
        txtValor.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtValor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtValor.setText("0");

        lbNit5.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit5.setText("Ajuste #:");

        lbNoFactura.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbNoFactura.setForeground(new java.awt.Color(255, 0, 0));
        lbNoFactura.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNoFactura.setText("3");
        lbNoFactura.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lbNit6.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbNit6.setText("Fecha ajuste:");

        txtFecha.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        txtFecha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtFecha.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout pnlBotonesLayout = new javax.swing.GroupLayout(pnlBotones);
        pnlBotones.setLayout(pnlBotonesLayout);
        pnlBotonesLayout.setHorizontalGroup(
            pnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBotonesLayout.createSequentialGroup()
                .addGroup(pnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBotonesLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(pnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnReimprimir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnLimpiar, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                            .addComponent(btnAnular, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(pnlBotonesLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbNit4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbNit3)
                            .addComponent(lbNit6, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                            .addComponent(lbNit5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(7, 7, 7)
                        .addGroup(pnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtFecha, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbTipoAjuste, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtValor)
                            .addComponent(lbNoFactura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        pnlBotonesLayout.setVerticalGroup(
            pnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBotonesLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(pnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbNit5, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(lbNoFactura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addGroup(pnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbNit6, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(txtFecha, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addGroup(pnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbNit4, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(cmbTipoAjuste, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addGroup(pnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNit3, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(pnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimpiar))
                .addGap(3, 3, 3)
                .addGroup(pnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnReimprimir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAnular, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10))
        );

        javax.swing.GroupLayout pnlFormularioLayout = new javax.swing.GroupLayout(pnlFormulario);
        pnlFormulario.setLayout(pnlFormularioLayout);
        pnlFormularioLayout.setHorizontalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlInvisible, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlFormularioLayout.setVerticalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlInvisible, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(89, Short.MAX_VALUE))
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

    private void txtCodigoProductoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoProductoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            plu = true;
            String codigo = txtCodigoProducto.getText();
            cargarProducto(codigo.replace("'", "//"), Utilidades.convertirBigDecimal(txtCantidad.getText()), 1, "", "", "", "", "", "", "");
        } else if (evt.getKeyCode() == KeyEvent.VK_MULTIPLY) {
            BigDecimal cantidad = BigDecimal.ONE;

            try {
                cantidad = Utilidades.convertirBigDecimal(txtCodigoProducto.getText().replace("*", ""));
            } catch (NumberFormatException e) {
            }

            txtCantidad.setText(String.valueOf(cantidad));
            txtCodigoProducto.setText("");
        }
    }//GEN-LAST:event_txtCodigoProductoKeyReleased

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (tblProductos.getRowCount() == 0) {
            metodos.msgError(this, "No ha añadido ningun producto");
        } else {

            int xyz = tblProductos.getRowCount();
            if (xyz > 0) {
                for (int i = 0; i < xyz; i++) {
                    if (tblProductos.getValueAt(i, 3).equals("0")) {
                        metodos.msgError(this, "Las cantidades deben ser mayor a 0");
                        return;
                    }

                    if (big.getMoneda(tblProductos.getValueAt(i, 2).toString()).compareTo(BigDecimal.ZERO) <= 0) {
                        metodos.msgError(this, "Los valores deben ser mayor a " + this.simbolo + " 0");
                        return;
                    }

                    tblProductos.setColumnSelectionInterval(0, 0);
                    tblProductos.setRowSelectionInterval(i, i);
                    KeyEvent x = new KeyEvent(this, WIDTH, WIDTH, WIDTH, KeyEvent.VK_ENTER);
                    tblProductosKeyReleased(x);
                }
            }

            if (!saltarPasos) {
                if (metodos.msgPregunta(this, "¿Desea continuar?") != 0) {
                    return;
                }
            }

            String factura = "TRAS-" + instancias.getSql().getNumConsecutivo("TRAS")[0];

            //PROCESO GUARDAR REGISTRO EN CAJA
            String origen = "", destino = "";

            if (cmbTipoAjuste.getSelectedIndex() == 0) {
                origen = "123-11";
                destino = "123-22";
            } else {
                origen = "123-22";
                destino = "123-11";
            }

            Object[] vector = {factura, origen, destino, metodos.fechaConsulta(metodosGenerales.fecha()), cantidadTotal,
                big.getMoneda(txtValor.getText()), cmbTipoAjuste.getSelectedItem(),
                instancias.getUsuario(), instancias.getTerminal(), metodosGenerales.hora(), "", ""};

            ndTraslado nodo = metodos.llenarTraslado(vector);

            if (!instancias.getSql().agregarTraslado(nodo)) {
                metodos.msgError(this, "Error al guardar el ajuste");
                return;
            }

            //PROCESO GUARDAR VENTA
            for (int i = 0; i < tblProductos.getRowCount(); i++) {
                String observaciones = cmbTipoAjuste.getSelectedIndex() == 0 ? obtenerValorTabla(i, 8) : obtenerValorTabla(i, 11);

                Object vectVenta[] = {factura, tblProductos.getValueAt(i, 15), tblProductos.getValueAt(i, 3),
                    big.getMoneda((String) tblProductos.getValueAt(i, 2)), big.getMoneda((String) tblProductos.getValueAt(i, 7)), observaciones,
                    tblProductos.getValueAt(i, 1), tblProductos.getValueAt(i, 9) + "", tblProductos.getValueAt(i, 10),
                    tblProductos.getValueAt(i, 14), observaciones, "", "", big.getMoneda((String) tblProductos.getValueAt(i, 4)),
                    big.getMoneda((String) tblProductos.getValueAt(i, 6)), big.getBigDecimal((String) tblProductos.getValueAt(i, 5)),
                    big.getMoneda((String) tblProductos.getValueAt(i, 16)), big.getBigDecimal((String) tblProductos.getValueAt(i, 17)),
                    big.getMoneda((String) tblProductos.getValueAt(i, 18))
                };

                ndProductoAjustes nodoTras = metodos.llenarAjustes(vectVenta);

                if (!instancias.getSql().agregarProductosAjustes(nodoTras)) {
                    metodos.msgError(this, "Error al guardar el ajuste");
                }
            }

            TipoDocumento tipoMovimiento = cmbTipoAjuste.getSelectedIndex() == 0 ? TipoDocumento.AJUSTE_ENTRADA : TipoDocumento.AJUSTE_SALIDA;
            List<DetalleProducto> detallesProductos = cmbTipoAjuste.getSelectedIndex() == 0 ? generarDetallesProductos() : new ArrayList<DetalleProducto>();
            String tablaUtilizada = enumBodegas.TipoBodega.BODEGA_PRINCIPAL.getNombreTabla();
            List<MovimientoInventario> productos = generarListadoProductos(tablaUtilizada);
            ServicioInventario servicioInventario = new ServicioInventario(productos, detallesProductos, tipoMovimiento, factura, tablaUtilizada, instancias.getUsuario(), null);

            try {
                servicioInventario.procesarMovimiento();
            } catch (SQLException ex) {
                Logger.getLogger(VistaInventarioInicial.class.getName()).log(Level.SEVERE, null, ex);
                return;
            }

            //CAMBIAR CONSECUTIVO FACTURA
            if (!instancias.getSql().aumentarConsecutivo("TRAS", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("TRAS")[0]) + 1)) {
                metodos.msgError(this, "Hubo un problema al guardar en el consecutivo del traslado");
            }

            lbNoFactura.setText((String) instancias.getSql().getNumConsecutivo("TRAS")[0]);

            if (!saltarPasos) {
                metodos.msgExito(this, "Ajuste exitoso");
            }

            saltarPasos = false;

            if (metodos.msgPregunta(this, "¿Desea imprimir?") == 0) {
                generarReporte(factura);
            }

            preguntaLimpiar = false;
            btnLimpiarActionPerformed(evt);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        if (preguntaLimpiar) {
            if (metodos.msgPregunta(null, "¿Limpiar el ajuste?") != 0) {
                return;
            }
        }

        preguntaLimpiar = true;

        txtCantidad.setText(datos[87].toString());
        eliminarRegistrosTablas();

        txtValor.setText(this.simbolo + " 0");
        cantidadTotal = "0";
        tblProductos.removeEditor();
        lbNoFactura.setText((String) instancias.getSql().getNumConsecutivo("TRAS")[0]);

        guardarMovimientosPrecargados();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void popBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popBorrarActionPerformed
        if (tblProductos.getSelectedRow() > -1) {
            int fila = tblProductos.getSelectedRow();

            int num = tblDetalle.getRowCount();
            for (int i = num - 1; i >= 0; i--) {
                if (tblDetalle.getValueAt(i, 0).equals(tblProductos.getValueAt(fila, 15))) {
                    modeloPro1.removeRow(i);
                }
            }

            DefaultTableModel modelo = (DefaultTableModel) tblProductos.getModel();
            modelo.removeRow(fila);

            tblProductos.removeEditor();
            cargarTotales();
            guardarMovimientosPrecargados();
        } else {
            metodos.msgAdvertencia(this, "Seleccione un producto");
        }
    }//GEN-LAST:event_popBorrarActionPerformed

    private void btnReimprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReimprimirActionPerformed
        String consecutivo = "TRAS-" + metodos.msgIngresarEnter(this, "Documento a reimprimir");
        if (consecutivo.equals("TRAS-")) {
            return;
        }

        boolean anulado = instancias.getSql().getDocumentoAnulado("bdTraslados", "Where id='" + consecutivo + "' ");

        if (anulado) {
            metodos.msgAdvertencia(this, "Este documento se encuentra anulado");
            return;
        }

        generarReporte(consecutivo);
    }//GEN-LAST:event_btnReimprimirActionPerformed

    private void lbProductoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lbProductoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtCodigoProducto.requestFocus();
        }
    }//GEN-LAST:event_lbProductoKeyReleased

    private void btnAnularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnularActionPerformed
        String consecutivo = "TRAS-" + metodos.msgIngresarEnter(this, "Documento a anular");
        if (consecutivo.equals("TRAS-")) {
            return;
        }

        try {
            boolean anulado = instancias.getSql().getDocumentoAnulado("bdTraslados", "Where id='" + consecutivo + "' ");
            if (anulado) {
                metodos.msgError(this, "El documento se encuentra anulado");
                return;
            }
        } catch (Exception e) {
            metodos.msgError(this, "El documento no existe");
            return;
        }

        if (metodos.msgPregunta(this, "¿Anular este ajuste?") == 0) {

            if (!instancias.getSql().anularDocumento(consecutivo, "bdTraslados")) {
                metodos.msgError(this, "Hubo un problema al anular el ajuste");
                return;
            }

            Object[][] productosAjuste = instancias.getSql().getProductosAjuste(consecutivo);
            String origen = instancias.getSql().tipoAjuste(consecutivo);
            boolean esAjusteEntrada = origen.equals("123-11");

            TipoDocumento tipoMovimiento = esAjusteEntrada ? TipoDocumento.ANULAR_AJUSTE_ENTRADA : TipoDocumento.ANULAR_AJUSTE_SALIDA;
            String tablaUtilizada = enumBodegas.TipoBodega.BODEGA_PRINCIPAL.getNombreTabla();
            List<MovimientoInventario> productos = generarListadoProductos(tablaUtilizada, productosAjuste);
            ServicioInventario servicioInventario = new ServicioInventario(productos, new ArrayList<DetalleProducto>(), tipoMovimiento, consecutivo, tablaUtilizada, instancias.getUsuario(), null);

            try {
                servicioInventario.procesarMovimiento();
            } catch (SQLException ex) {
                Logger.getLogger(VistaInventarioInicial.class.getName()).log(Level.SEVERE, null, ex);
                return;
            }

            metodos.msgExito(this, "Ajuste anulado con éxito");
        }
    }//GEN-LAST:event_btnAnularActionPerformed

    private void cmbTipoAjusteItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbTipoAjusteItemStateChanged
        consultarPermiso();
        actualizarColumnasTabla();

        eliminarRegistrosTablas();
        cargarProductosPrecargados();

        for (int i = 0; i < tblProductos.getRowCount(); i++) {
            calcularFila(i);
        }
    }//GEN-LAST:event_cmbTipoAjusteItemStateChanged

    private void tblProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductosMouseClicked
        if (tblProductos.getSelectedColumn() == 3) {

            if (cmbTipoAjuste.getSelectedIndex() == 0) {
                ndProducto nodo = instancias.getSql().getDatosProducto(tblProductos.getValueAt(tblProductos.getSelectedRow(), 15).toString(), "bdProductos");
                String tipoProducto = Enums.DetalleTipoProducto.obtenerTipoProducto(nodo.getTipoProducto());
                List<DetalleProducto> detallesProductos = generarDetallesProductos();
                String tipoMovimiento = "Entrada";
                String tipoDocumento = TipoDocumento.AJUSTE_ENTRADA.getValor();

                if (!tipoProducto.isEmpty()) {
                    VistaMovimientoDetalleProducto compraDetallada = new VistaMovimientoDetalleProducto(null, true, nodo, detallesProductos, tipoMovimiento, tipoDocumento, BigDecimal.ZERO);
                    compraDetallada.setLocationRelativeTo(null);
                    compraDetallada.setVisible(true);
                }
            } else {
                metodos.msgAdvertenciaAjustado(null, "La cantidad no se puede modificar");
            }
        }
    }//GEN-LAST:event_tblProductosMouseClicked

    private void tblProductosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductosMouseEntered

    }//GEN-LAST:event_tblProductosMouseEntered

    private void tblProductosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductosMousePressed

    }//GEN-LAST:event_tblProductosMousePressed

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
                        String codigoProducto = obtenerValorTabla(filaSeleccionada, 15);
                        if (!existeProductoDetalle(codigoProducto)) {
                            tblProductos.editCellAt(filaSeleccionada, 3);
                            tblProductos.setColumnSelectionInterval(3, 3);
                            tblProductos.transferFocus();
                        } else {
                            txtCodigoProducto.requestFocus();
                        }
                        break;
                    case 3:
                    case 5:
                    case 6:
                        txtCodigoProducto.requestFocus();
                        break;
                    default:
                        break;
                }

                calcularFila(filaSeleccionada);
            } else if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
                popBorrarActionPerformed(null);
            }
        }
    }//GEN-LAST:event_tblProductosKeyReleased

    private void lbProducto1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lbProducto1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_lbProducto1KeyReleased

    private void txtCantidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidadKeyReleased

    private void btnBusProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBusProdActionPerformed
        ventanaProductos("");
    }//GEN-LAST:event_btnBusProdActionPerformed

    private void generarReporte(String consecutivo) {
        GenerarReportes reportes = new GenerarReportes(instancias);
        reportes.verAjusteInventario(consecutivo);

        boolean esAjusteEntrada = instancias.getSql().tipoAjuste(consecutivo).equals("123-11");
        if (instancias.getConfiguraciones().isProductosDetallados() && esAjusteEntrada) {
            reportes.verIngresoDetalle(consecutivo);
        }
    }

    private void eliminarRegistrosTablas() {
        while (tblProductos.getRowCount() > 0) {
            modeloTablaProductos.removeRow(0);
        }

        while (tblDetalle.getRowCount() > 0) {
            modeloPro1.removeRow(0);
        }
    }

    private List<MovimientoInventario> generarListadoProductos(String tablaUtilizada) {

        List<MovimientoInventario> movimientos = new ArrayList<>();

        for (int i = 0; i < tblProductos.getRowCount(); i++) {
            ndProducto producto = instancias.getSql().getDatosProducto(tblProductos.getValueAt(i, 15).toString(), tablaUtilizada);
            BigDecimal cantidad = Utilidades.convertirBigDecimal(tblProductos.getValueAt(i, 10).toString());
            String idDetalleProducto = obtenerValorTabla(i, 14);

            MovimientoInventario inventario = new MovimientoInventario(producto, cantidad, BigDecimal.ZERO, idDetalleProducto);
            movimientos.add(inventario);
        }

        return movimientos;
    }

    private List<MovimientoInventario> generarListadoProductos(String tablaUtilizada, Object[][] productosAjuste) {

        List<MovimientoInventario> movimientos = new ArrayList<>();

        for (Object[] productoAjuste : productosAjuste) {
            ndProducto producto = instancias.getSql().getDatosProducto(productoAjuste[0].toString(), tablaUtilizada);
            BigDecimal cantidad = Utilidades.convertirBigDecimal(productoAjuste[1].toString());
            String idDetalleProducto = productoAjuste[2] != null ? productoAjuste[2].toString() : "";

            MovimientoInventario inventario = new MovimientoInventario(producto, cantidad, BigDecimal.ZERO, idDetalleProducto);
            movimientos.add(inventario);
        }

        return movimientos;
    }

    private String obtenerValorTabla(int row, int col) {
        Object value = tblProductos.getValueAt(row, col);
        return value != null ? value.toString() : "";
    }

    private List<DetalleProducto> generarDetallesProductos() {
        UtilidadesDetalleProducto utilidadesDetalleProducto = new UtilidadesDetalleProducto(tblDetalle);
        return utilidadesDetalleProducto.generarDetallesProductos(EstadosDetalleProducto.DISPONIBLE.getNombre());
    }

    public void eliminarRegistros(String codigo) {
        for (int i = tblDetalle.getRowCount() - 1; i >= 0; i--) {
            if (tblDetalle.getValueAt(i, 0).equals(codigo)) {
                modeloPro1.removeRow(i);
            }
        }

        for (int i = 0; i < tblProductos.getRowCount(); i++) {
            if (tblProductos.getValueAt(i, 15).equals(codigo)) {
                modeloTablaProductos.removeRow(i);
            }
        }
    }

    public void calcularFila(int fila) {
        ndProducto nodo = instancias.getSql().getDatosProducto(tblProductos.getValueAt(fila, 15).toString(), "bdProductos");

        BigDecimal valor = big.getMoneda(tblProductos.getValueAt(fila, 2).toString());
        BigDecimal cantidad = Utilidades.convertirBigDecimal(obtenerValorTabla(fila, 3));
        BigDecimal iva = Utilidades.convertirBigDecimal(obtenerValorTabla(fila, 5));
        BigDecimal impoconsumo = Utilidades.convertirBigDecimal(obtenerValorTabla(fila, 17));
        BigDecimal subtotal = valor.multiply(cantidad);

        tblProductos.setValueAt(big.setMoneda(subtotal), fila, 4);

        BigDecimal totalIva = subtotal.multiply(iva).divide(big.getBigDecimal("100"), 2, RoundingMode.CEILING);
        tblProductos.setValueAt(big.setMoneda(totalIva), fila, 6);

        BigDecimal totalImpoconsumo = subtotal.multiply(impoconsumo).divide(big.getBigDecimal("100"), 2, RoundingMode.CEILING);
        tblProductos.setValueAt(big.setMoneda(totalImpoconsumo), fila, 18);

        BigDecimal total = totalIva.add(totalImpoconsumo).add(subtotal);
        tblProductos.setValueAt(big.setMoneda(total), fila, 7);

        tblProductos.setValueAt(big.setMoneda(valor), fila, 2);
        tblProductos.setValueAt(cantidad, fila, 3);

        int numeroPlu = Integer.parseInt(tblProductos.getValueAt(fila, 9).toString());
        ResultadoPluProducto resultadoPluProducto = daoProducto.obtenerDatosPluProducto(nodo, numeroPlu);
        BigDecimal cantidadPlu = resultadoPluProducto.getCantidadPlu();
        tblProductos.setValueAt(cantidad.multiply(cantidadPlu), fila, 10);

        BigDecimal inventarioActual = Utilidades.convertirBigDecimal(obtenerValorTabla(fila, 12));
        BigDecimal cantidadMovimiento = Utilidades.convertirBigDecimal(obtenerValorTabla(fila, 10));
        BigDecimal inventarioTotal = cmbTipoAjuste.getSelectedIndex() == 0
                ? inventarioActual.add(cantidadMovimiento)
                : inventarioActual.subtract(cantidadMovimiento);

        tblProductos.setValueAt(Utilidades.formatearCantidadVista(inventarioTotal), fila, 13);

        cargarTotales();
        guardarMovimientosPrecargados();
    }

    private void actualizarColumnasTabla() {
        if (instancias.getConfiguraciones().isProductosDetallados() && cmbTipoAjuste.getSelectedIndex() == 1) {
            tblProductos.getColumnModel().getColumn(8).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(8).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(8).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(11).setMinWidth(150);
            tblProductos.getColumnModel().getColumn(11).setPreferredWidth(200);
            tblProductos.getColumnModel().getColumn(11).setMaxWidth(230);
        } else {
            tblProductos.getColumnModel().getColumn(8).setMinWidth(150);
            tblProductos.getColumnModel().getColumn(8).setPreferredWidth(200);
            tblProductos.getColumnModel().getColumn(8).setMaxWidth(230);
            tblProductos.getColumnModel().getColumn(11).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(11).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(11).setMaxWidth(0);
        }
    }

    public void cargarProductosPrecargados() {
        String tipoDocumento = TipoDocumento.AJUSTE_ENTRADA.getValor();
        if (cmbTipoAjuste.getSelectedIndex() == 1) {
            tipoDocumento = TipoDocumento.AJUSTE_SALIDA.getValor();
        }

        List<DetalleProducto> detalles = daoDetalleProducto.obtenerDetalleProductosPrecargados(tipoDocumento);
        for (DetalleProducto detalle : detalles) {
            String fechaFormateada = detalle.getFechaVencimiento() != null
                    ? metodos.fecha(detalle.getFechaVencimiento().toString()) : "";
            modeloPro1.addRow(new Object[]{
                detalle.getProducto(),
                detalle.getImei(),
                detalle.getLote(),
                fechaFormateada,
                detalle.getTemperatura(),
                detalle.getCantidad(),
                detalle.getDescripcion(),
                detalle.getColor(),
                detalle.getTalla()
            });
        }

        Object[][] mat = instancias.getSql().getProductosPrecompra(tipoDocumento, instancias.getUsuario());

        for (Object[] reg : mat) {
            String codigoProducto = reg[0].toString();
            int numeroPlu = Integer.parseInt(reg[3].toString());
            cargarProducto(codigoProducto, Utilidades.convertirBigDecimal(reg[4].toString()), numeroPlu, "Check-seguridad", "", "", "", "", "", "");

            tblProductos.setValueAt(big.setMoneda(big.getBigDecimal(reg[5])), tblProductos.getRowCount() - 1, 2);
            tblProductos.setValueAt(reg[8], tblProductos.getRowCount() - 1, 14);

            String observaciones = reg[9].toString();
            tblProductos.setValueAt(observaciones, tblProductos.getRowCount() - 1, 11);
            tblProductos.setValueAt(observaciones, tblProductos.getRowCount() - 1, 8);

            KeyEvent x = new KeyEvent(this, WIDTH, WIDTH, WIDTH, KeyEvent.VK_ENTER);
            tblProductosKeyReleased(x);
        }
    }

    private void guardarMovimientosPrecargados() {
        String tipoDocumento = cmbTipoAjuste.getSelectedIndex() == 0
                ? TipoDocumento.AJUSTE_ENTRADA.getValor()
                : TipoDocumento.AJUSTE_SALIDA.getValor();
        guardarPreAjustes(tipoDocumento);
        guardarPreAjustesDetalle(tipoDocumento);
    }

    public void guardarPreAjustes(String tipoDocumento) {
        boolean noPuedaGuardar = false;
        while (!noPuedaGuardar) {
            noPuedaGuardar = instancias.getSql().eliminarPrecompra(tipoDocumento, instancias.getUsuario());
        }

        for (int i = 0; i < tblProductos.getRowCount(); i++) {
            String observaciones = cmbTipoAjuste.getSelectedIndex() == 0 ? obtenerValorTabla(i, 8) : obtenerValorTabla(i, 11);

            Object vectCompra[] = {
                tipoDocumento, tblProductos.getValueAt(i, 15), big.getMoneda((String) tblProductos.getValueAt(i, 2)), tblProductos.getValueAt(i, 3), "0",
                big.getMoneda((String) tblProductos.getValueAt(i, 7)), big.getMoneda((String) tblProductos.getValueAt(i, 6)),
                big.getMoneda((String) tblProductos.getValueAt(i, 4)), tblProductos.getValueAt(i, 5), tblProductos.getValueAt(i, 1),
                tblProductos.getValueAt(i, 9) + "", tblProductos.getValueAt(i, 10), big.getMoneda((String) tblProductos.getValueAt(i, 2)),
                tblProductos.getValueAt(i, 14).toString(), "0", "0", instancias.getUsuario(), observaciones
            };

            ndCompra nodoComp = metodos.llenarCompra(vectCompra);
            if (!instancias.getSql().agregarPrecompra(nodoComp)) {
                metodos.msgError(null, "Hubo un problema al guardar la precompra");
            }
        }
    }

    public void guardarPreAjustesDetalle(String tipoDocumento) {
        while (!instancias.getSql().eliminarPrecompraDetalle(tipoDocumento)) {
        }

        List<DetalleProducto> detalles = new ArrayList<>();
        for (int i = 0; i < tblDetalle.getRowCount(); i++) {
            detalles.add(construirDetalleDesdeFilaTabla(i));
        }

        if (!daoDetalleProducto.guardarListaDetallesPrecargados(tipoDocumento, detalles)) {
            metodos.msgError(null, "Error al guardar detalle del ajuste");
        }
    }

    private DetalleProducto construirDetalleDesdeFilaTabla(int fila) {
        String producto = tblDetalle.getValueAt(fila, 0).toString();
        String imei = tblDetalle.getValueAt(fila, 1).toString();
        String lote = tblDetalle.getValueAt(fila, 2).toString();
        String temperatura = tblDetalle.getValueAt(fila, 4).toString();
        String descripcion = tblDetalle.getValueAt(fila, 6).toString();
        String color = tblDetalle.getValueAt(fila, 7).toString();
        String talla = tblDetalle.getValueAt(fila, 8).toString();

        BigDecimal cantidad = Utilidades.convertirBigDecimal(tblDetalle.getValueAt(fila, 5).toString());
        if (cantidad.compareTo(BigDecimal.ZERO) <= 0) {
            cantidad = BigDecimal.ONE;
        }

        String fecha = tblDetalle.getValueAt(fila, 3).toString();
        if (fecha.isEmpty()) {
            fecha = metodosGenerales.fecha();
        }

        return new DetalleProducto(
                null, producto, descripcion, imei, lote, color, talla,
                LocalDate.parse(metodos.fechaConsulta(fecha)), temperatura, null, null, cantidad, null
        );
    }

    private void eliminarProductoDetalladoExistente(String idProd) {
        for (int i = 0; i < tblProductos.getRowCount(); i++) {
            String idProducto = obtenerValorTabla(i, 14);
            if (!idProducto.isEmpty() && idProducto.equals(idProd)) {
                modeloTablaProductos.removeRow(i);
                break;
            }
        }
    }

    private boolean combinarConFilaExistente(ndProducto nodo, int plu, BigDecimal cantidad) {
        for (int i = 0; i < tblProductos.getRowCount(); i++) {
            boolean mismoProducto = nodo.getIdSistema().equals(tblProductos.getValueAt(i, 15).toString());
            boolean mismoPlu = plu == Integer.parseInt(tblProductos.getValueAt(i, 9).toString());
            if (!mismoProducto || !mismoPlu) {
                continue;
            }

            BigDecimal cantidadActual = Utilidades.convertirBigDecimal(tblProductos.getValueAt(i, 3).toString());
            BigDecimal cantidadFinal = cantidadActual.add(cantidad);
            tblProductos.setValueAt(Utilidades.formatearCantidadVista(cantidadFinal), i, 3);

            calcularFila(i);
            txtCodigoProducto.setText("");
            tblProductos.setColumnSelectionInterval(0, 0);
            tblProductos.setRowSelectionInterval(i, i);
            txtCodigoProducto.requestFocus();

            txtCantidad.setText(DatosMaestra.getCantidadEstablecidaAlCargar());
            return true;
        }

        return false;
    }

    public void cargarProducto(String codigo, BigDecimal cantidad, int pluProducto, String imei, String lote, String idProd, String talla, String color,
            String temp, String fechaVence) {

        eliminarProductoDetalladoExistente(idProd);
        tblProductos.removeEditor();

        ResultadoBusquedaProducto resultado = daoProducto.buscarPorCodigo(codigo);
        codigo = resultado.getCodigoResuelto();
        ndProducto nodo = resultado.getProducto();

        if (nodo.getCodigo() == null || nodo.getCodigo().isEmpty()) {
            ventanaProductos(codigo);
            return;
        }

        if (instancias.getSql().getProdActivo(nodo.getCodigo())) {
            metodos.msgError(null, "Este producto esta inactivo");
            lbProducto.requestFocus();
            return;
        }

        if (DatosMaestra.isCombinarProductos() && nodo.getUsuario().equals(TipoProducto.GENERICO.getValue())
                && combinarConFilaExistente(nodo, pluProducto, cantidad)) {
            return;
        }

        String tipo = Enums.DetalleTipoProducto.obtenerTipoProducto(nodo.getTipoProducto());

        String tipoMov, tipoDocumento;
        if (cmbTipoAjuste.getSelectedIndex() == 0) {
            tipoMov = "Entrada";
            tipoDocumento = TipoDocumento.AJUSTE_ENTRADA.getValor();
        } else {
            tipoMov = "Salida";
            tipoDocumento = TipoDocumento.AJUSTE_SALIDA.getValor();
        }

        boolean yaTieneInformacion = !imei.isEmpty() || !lote.isEmpty() || !talla.isEmpty() || !color.isEmpty() || !temp.isEmpty() || !fechaVence.isEmpty();

        if (!tipo.equals("") && idProd.equals("") && !yaTieneInformacion) {
            VistaMovimientoDetalleProducto compraDetallada = new VistaMovimientoDetalleProducto(null, true, nodo, null, tipoMov, tipoDocumento, BigDecimal.ZERO);
            compraDetallada.setLocationRelativeTo(null);
            compraDetallada.setVisible(true);
        } else {
            if (this.plu) {
                this.plu = false;
                if (daoProducto.productoConPlu(nodo)) {
                    VistaSeleccionarPLU pluu = new VistaSeleccionarPLU(null, true, "bdProductos");
                    pluu.setInstancias(instancias, nodo.getCodigo());
                    pluu.setOpc("ajuste");
                    pluu.setVisible(true);
                    return;
                }
            }

            if (imei.equals("Check-seguridad")) {
                imei = "";
            }

            ResultadoPluProducto resultadoPluProducto = daoProducto.obtenerDatosPluProducto(nodo, pluProducto);
            BigDecimal cantidadPlu = resultadoPluProducto.getCantidadPlu();
            String descripcion = resultadoPluProducto.getDescripcion();
            String codigoProducto = resultadoPluProducto.getCodigoLista();

            BigDecimal cantidadFisicoInventarioActual = resultadoPluProducto.getCantidadFisicoInventarioActual();
            BigDecimal res = BigDecimal.ONE;
            if (cmbTipoAjuste.getSelectedIndex() == 0) {
                res = cantidadFisicoInventarioActual.add(cantidad);
            } else {
                res = cantidadFisicoInventarioActual.subtract(cantidad);
            }

            BigDecimal ponderado = BigDecimal.ZERO;
            try {
                UltimoPonderado ultimoPonderado = servicioActualizacionPonderado.obtenerUltimoPonderado(nodo.getIdSistema());
                ponderado = ultimoPonderado.getNuevoPonderado();
            } catch (SQLException ex) {
                Logger.getLogger(VistaInventarioInicial.class.getName()).log(Level.SEVERE, null, ex);
            }

            BigDecimal iva = BigDecimal.ZERO;
            try {
                iva = big.getBigDecimal(nodo.getIva());
            } catch (Exception e) {
            }

            BigDecimal impoconsumo = BigDecimal.ZERO;
            try {
                iva = big.getBigDecimal(nodo.getImpoconsumoVenta());
            } catch (Exception e) {
            }

            BigDecimal subtotal = ponderado.multiply(cantidad);
            BigDecimal totalIva = subtotal.multiply(iva).divide(big.getBigDecimal("100"), 2, RoundingMode.CEILING);
            BigDecimal totalImpoconsumo = subtotal.multiply(impoconsumo).divide(big.getBigDecimal("100"), 2, RoundingMode.CEILING);

            BigDecimal total = subtotal.add(totalIva);

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

            modeloTablaProductos.addRow(new Object[]{
                codigoProducto,
                descripcion,
                big.setMoneda(ponderado),
                Utilidades.formatearCantidadVista(cantidad),
                big.setMoneda(ponderado.multiply(cantidad)),
                nodo.getIva(),
                big.setMoneda(totalIva),
                big.setMoneda(total),
                "",
                pluProducto,
                cantidadPlu,
                detalle,
                Utilidades.formatearCantidadVista(cantidadFisicoInventarioActual),
                Utilidades.formatearCantidadVista(res),
                idProd,
                nodo.getIdSistema(),
                big.setMoneda(ponderado),
                nodo.getImpoconsumoVenta(),
                big.setMoneda(totalImpoconsumo)
            });

            marcarFocoTabla();
        }
    }

    private boolean existeProductoDetalle(String codigoProducto) {
        for (int i = 0; i < tblDetalle.getRowCount(); i++) {
            String producto = tblDetalle.getValueAt(i, 0).toString();
            if (producto.equals(codigoProducto)) {
                return true;
            }
        }

        return false;
    }

    private void marcarFocoTabla() {
        final int ultimaFila = tblProductos.getRowCount() - 1;
        if (ultimaFila < 0) {
            return;
        }

        if (instancias.isLector()) {
            tblProductos.setRowSelectionInterval(ultimaFila, ultimaFila);
            txtCodigoProducto.requestFocus();
        } else {
            int columna = DatosMaestra.getFocoDespuesDeCargarProducto().equals("Valor") ? 2 : 3;
            tblProductos.setColumnSelectionInterval(columna, columna);
            tblProductos.setRowSelectionInterval(ultimaFila, ultimaFila);
            tblProductos.editCellAt(ultimaFila, columna);
            tblProductos.transferFocus();
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                tblProductos.scrollRectToVisible(tblProductos.getCellRect(ultimaFila, 0, true));

                JScrollPane scrollPane = (JScrollPane) SwingUtilities.getAncestorOfClass(JScrollPane.class, tblProductos);
                if (scrollPane != null) {
                    JScrollBar barraVertical = scrollPane.getVerticalScrollBar();
                    barraVertical.setValue(barraVertical.getMaximum());
                }
            }
        });
    }

    public void cargarDetallado(String prod, String imei, String lote, String fechaVence, String temp,
            BigDecimal cantidad, String nombre, String color, String talla) {
        modeloPro1.addRow(new Object[]{prod, imei, lote, fechaVence, temp, cantidad, nombre, color, talla});
    }

    public void ventanaProductos(String codigo) {
        VistaBuscadorProductos buscar = new VistaBuscadorProductos(null, true, false, "", "productos1");
        buscar.setOpc("ajuste");
        buscar.setLocationRelativeTo(null);
        instancias.setBusProductos(buscar);
        instancias.setCampoActual(txtCodigoProducto);
        txtCodigoProducto.requestFocus();
        buscar.noEncontrado(codigo.replace("'", "//"));
        buscar.show();
    }

    public void cargarTotales() {
        BigDecimal total = big.getBigDecimal("0");
        BigDecimal cantidad = big.getBigDecimal("0");

        for (int i = 0; i < tblProductos.getRowCount(); i++) {
            total = total.add(big.getMoneda(String.valueOf(tblProductos.getValueAt(i, 7))));
            cantidad = cantidad.add(big.getMoneda(String.valueOf(tblProductos.getValueAt(i, 3).toString().replace(".", ","))));
        }

        this.cantidadTotal = cantidad + "";
        txtValor.setText(big.setMoneda(total));
    }

    public void setDatosEmpresa(String datos) {
        //lbInfoEmpresa.setText(metodosGenerales.convertToMultiline("Aqui va la\ninformacion \nde la \nempresa"));
        datosEmpresa = datos;
    }

    public void cargarProductos(Object[][] productos) {
        String cantEstablecida = txtCantidad.getText();
        for (Object[] producto : productos) {
            String codigo = producto[0].toString();
            String cantidad = producto[1].toString();
            if (cantidad.equals("0")) {
                cantidad = cantEstablecida;
            }

            this.plu = true;
            cargarProducto(codigo, Utilidades.convertirBigDecimal(cantidad), 1, "", "", "", "", "", "", "");
            calcularFila(tblProductos.getRowCount() - 1);
        }
    }

    public void ajustesExterno(Object[][] productos) {
        cmbTipoAjuste.setSelectedIndex(1);

        for (Object[] prod : productos) {
            cargarProducto(prod[0].toString(), Utilidades.convertirBigDecimal(prod[1].toString()), 1, "", "", "", "", "", "", "");
        }

        int xyz = tblProductos.getRowCount();
        tblProductos.removeEditor();

        if (xyz > 0) {
            for (int i = 0; i < xyz; i++) {
                tblProductos.setColumnSelectionInterval(0, 0);
                tblProductos.setRowSelectionInterval(i, i);
                KeyEvent x = new KeyEvent(this, WIDTH, WIDTH, WIDTH, KeyEvent.VK_ENTER);
                tblProductosKeyReleased(x);
            }
        }

        saltarPasos = true;
        btnGuardarActionPerformed(null);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnular;
    private javax.swing.JButton btnBusProd;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnReimprimir;
    private javax.swing.JComboBox cmbTipoAjuste;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbNit3;
    private javax.swing.JLabel lbNit4;
    private javax.swing.JLabel lbNit5;
    private javax.swing.JLabel lbNit6;
    private javax.swing.JLabel lbNoFactura;
    private javax.swing.JLabel lbProducto;
    private javax.swing.JLabel lbProducto1;
    private javax.swing.JPanel pnlBotones;
    private javax.swing.JPanel pnlFormulario;
    private javax.swing.JPanel pnlInvisible;
    private javax.swing.JMenuItem popBorrar;
    private javax.swing.JScrollPane scrFormulario;
    private javax.swing.JTable tblDetalle;
    private javax.swing.JTable tblProductos;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtCodigoProducto;
    private javax.swing.JLabel txtFecha;
    private javax.swing.JTextField txtValor;
    // End of variables declaration//GEN-END:variables
}
