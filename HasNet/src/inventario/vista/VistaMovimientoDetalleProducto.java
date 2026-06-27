package inventario.vista;

import Enums.DetalleTipoProducto;
import Enums.TipoDocumento;
import Modelo.Inventario.DetalleProducto;
import Vista.Productos.ReceptorDetallado;
import Vista.Productos.ReceptorProductoSalida;
import Vista.Productos.VistaAjusteInventario;
import Vista.Productos.VistaIngreso;
import Vista.Productos.VistaInventarioInicial;
import Vista.Ventas.VistaFactura;
import clases.Instancias;
import Utilidades.Utilidades;
import clases.metodosGenerales;
import clases.productos.ndProducto;
import formularios.productos.buscColores;
import formularios.productos.buscTallas;
import java.awt.Color;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.table.TableColumn;

public final class VistaMovimientoDetalleProducto extends javax.swing.JDialog {

    Instancias instancias;
    metodosGenerales metodos = new metodosGenerales();
    String prodOficial = "";
    String tipoDocumento = "";
    String tipoMovimiento = "";
    String tipoProducto = "";
    BigDecimal valorUnitarioDigitado;
    TableRowSorter modeloOrdenado;

    public VistaMovimientoDetalleProducto(java.awt.Frame parent, boolean modal, ndProducto producto, List<DetalleProducto> detallesProductos,
            String tipoMovimiento, String tipoDocumento, BigDecimal valorUnitario) {
        super(parent, modal);
        initComponents();

        this.getRootPane().registerKeyboardAction(accion("cerrar", this), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
        instancias = Instancias.getInstancias();

        this.setTitle("Detallado");

        DefaultTableModel modelo = (DefaultTableModel) tblDetalle.getModel();
        modeloOrdenado = new TableRowSorter<>(modelo);
        tblDetalle.setRowSorter(modeloOrdenado);

        this.prodOficial = producto.getIdSistema();
        this.tipoProducto = producto.getTipoProducto();
        this.tipoMovimiento = tipoMovimiento;
        this.tipoDocumento = tipoDocumento;
        this.valorUnitarioDigitado = valorUnitario;

        lbTitulo.setText(producto.getDescripcion());

        if (this.tipoMovimiento.equals("Entrada")) {
            configurarEntrada(tipoProducto, detallesProductos, modelo);
        } else {
            configurarSalida(tipoProducto, prodOficial, modelo);
        }
    }

    /**
     * Configura columnas, encabezados, filtros y filas para un movimiento de
     * Entrada.
     */
    private void configurarEntrada(String tipoProd, List<DetalleProducto> detallesProductos, DefaultTableModel modelo) {
        if (detallesProductos == null) {
            detallesProductos = new java.util.ArrayList<>();
        }

        ocultarColumna(4);
        ocultarColumna(5);

        if (DetalleTipoProducto.esSerialOImei(tipoProd)) {
            pnlColor.setVisible(false);
            pnlFechaLote.setVisible(false);

            for (DetalleProducto detalle : detallesProductos) {
                modelo.addRow(new Object[]{detalle.getImei(), detalle.getColor(), "", BigDecimal.ONE, false, "", ""});
            }

            tituloColumna(0, "Imei");
            ocultarColumna(2);
            ocultarColumna(3);

            if (esTipo(tipoProd, DetalleTipoProducto.IMEI)) {
                lbFiltro1.setText("Imei:");
            } else {
                tituloColumna(0, "Serial");
                lbFiltro1.setText("Serial:");
                lbImei.setText("Serial:");
            }

            tituloColumna(1, "Color");
            tblDetalle.getTableHeader().repaint();

            if (esTipo(tipoProd, DetalleTipoProducto.SERIAL)) {
                lbFiltro2.setVisible(false);
                txtFiltro2.setVisible(false);
                txtColor.setVisible(false);
                lbColor1.setVisible(false);
                ocultarColumna(1);
            }

            lbFiltro2.setText("Color:");
            lbFiltro3.setVisible(false);
            txtFiltro3.setVisible(false);
            txtImei.requestFocus();
        } else if (DetalleTipoProducto.esColorOTalla(tipoProd)) {
            lbFiltro1.setText("Color:");
            lbFiltro2.setText("Talla:");

            txtColor1.requestFocus();

            tituloColumna(0, "Color");
            tituloColumna(1, "Talla");

            if (esTipo(tipoProd, DetalleTipoProducto.COLOR)) {
                lbTalla.setVisible(false);
                txtTalla.setVisible(false);
                lbFiltro2.setVisible(false);
                txtFiltro2.setVisible(false);
                lbFiltro3.setVisible(false);
                txtFiltro3.setVisible(false);
                ocultarColumna(1);
            } else if (esTipo(tipoProd, DetalleTipoProducto.COLOR_TALLA)) {
                lbFiltro3.setVisible(false);
                txtFiltro3.setVisible(false);
            } else {
                tituloColumna(0, "Talla");
                lbFiltro1.setText("Talla:");
                lbFiltro2.setVisible(false);
                txtFiltro2.setVisible(false);
                lbFiltro3.setVisible(false);
                txtFiltro3.setVisible(false);
                lbColor.setVisible(false);
                txtColor1.setVisible(false);
                ocultarColumna(1);
                txtTalla.requestFocus();
            }

            tblDetalle.getTableHeader().repaint();

            for (DetalleProducto detalle : detallesProductos) {
                if (esTipo(tipoProd, DetalleTipoProducto.TALLA)) {
                    modelo.addRow(new Object[]{detalle.getTalla(), "", "", Utilidades.formatearCantidadVista(detalle.getCantidad()), false, "", ""});
                } else {
                    modelo.addRow(new Object[]{detalle.getColor(), detalle.getTalla(), "", Utilidades.formatearCantidadVista(detalle.getCantidad()), false, "", ""});
                }
            }

            pnlFechaLote.setVisible(false);
            pnlImei.setVisible(false);
            ocultarColumna(2);
        } else if (esTipo(tipoProd, DetalleTipoProducto.FECHA_LOTE)) {
            tituloColumna(0, "Lote");
            tituloColumna(1, "Fecha Vence");
            tituloColumna(2, "Temperatura");
            tblDetalle.getTableHeader().repaint();

            lbFiltro1.setText("Lote:");
            lbFiltro2.setText("Fecha V:");
            lbFiltro3.setText("Temp:");

            for (DetalleProducto detalle : detallesProductos) {
                modelo.addRow(new Object[]{detalle.getLote(), detalle.getFechaVencimiento(), detalle.getTemperatura(),
                    Utilidades.formatearCantidadVista(detalle.getCantidad()), false, "", ""});
            }

            pnlImei.setVisible(false);
            pnlColor.setVisible(false);
            dtFVence.requestFocus();
        }
    }

    /**
     * Configura columnas, encabezados, filtros y filas para un movimiento de
     * Salida.
     */
    private void configurarSalida(String tipoProd, String producto, DefaultTableModel modelo) {
        Object[][] existencias = instancias.getSql().obtenerProductosDetallePorProducto(producto);

        pnlColor.setVisible(false);
        pnlFechaLote.setVisible(false);
        pnlImei.setVisible(false);

        if (DetalleTipoProducto.esSerialOImei(tipoProd)) {
            if (esTipo(tipoProd, DetalleTipoProducto.SERIAL) || esTipo(tipoProd, DetalleTipoProducto.SERIAL_COLOR)) {
                tituloColumna(0, "Serial");
            } else {
                tituloColumna(0, "Imei");
            }

            tituloColumna(1, "Color");
            tblDetalle.getTableHeader().repaint();

            for (Object[] reg : existencias) {
                modelo.addRow(new Object[]{reg[3], reg[7], "", "1", false, "", reg[8]});
            }

            if (esTipo(tipoProd, DetalleTipoProducto.SERIAL)) {
                lbFiltro2.setVisible(false);
                txtFiltro2.setVisible(false);
                txtColor.setVisible(false);
                lbColor1.setVisible(false);
                ocultarColumna(1);
            }

            ocultarColumna(2);
            ocultarColumna(3);
            ocultarColumna(5);

            if (esTipo(tipoProd, DetalleTipoProducto.IMEI)) {
                lbFiltro1.setText("Imei:");
            } else {
                lbFiltro1.setText("Serial:");
            }

            lbFiltro2.setText("Color:");
            lbFiltro3.setVisible(false);
            txtFiltro3.setVisible(false);
        } else if (DetalleTipoProducto.esColorOTalla(tipoProd)) {
            tituloColumna(0, "Color");
            tituloColumna(1, "Talla");

            lbFiltro1.setText("Color:");
            lbFiltro2.setText("Talla:");

            if (esTipo(tipoProd, DetalleTipoProducto.TALLA)) {
                tituloColumna(0, "Talla");
                for (Object[] reg : existencias) {
                    modelo.addRow(new Object[]{reg[9], "", "", Utilidades.formatearCantidadVista(reg[2].toString()), false, "", reg[8]});
                }
            } else {
                for (Object[] reg : existencias) {
                    modelo.addRow(new Object[]{reg[7], reg[9], "", Utilidades.formatearCantidadVista(reg[2].toString()), false, "", reg[8]});
                }
            }

            tblDetalle.getTableHeader().repaint();

            if (esTipo(tipoProd, DetalleTipoProducto.TALLA)) {
                lbFiltro1.setText("Talla:");
                lbFiltro2.setVisible(false);
                txtFiltro2.setVisible(false);
            } else if (esTipo(tipoProd, DetalleTipoProducto.COLOR)) {
                lbFiltro2.setVisible(false);
                txtFiltro2.setVisible(false);
            }

            if (!esTipo(tipoProd, DetalleTipoProducto.COLOR_TALLA)) {
                ocultarColumna(1);
            }

            ocultarColumna(2);
            lbFiltro3.setVisible(false);
            txtFiltro3.setVisible(false);
        } else if (esTipo(tipoProd, DetalleTipoProducto.FECHA_LOTE)) {
            tituloColumna(0, "Lote");
            tituloColumna(1, "Fecha Vence");
            tituloColumna(2, "Temperatura");

            lbFiltro1.setText("Lote:");
            lbFiltro2.setText("Fecha V:");
            lbFiltro3.setText("Temp:");

            for (Object[] reg : existencias) {
                modelo.addRow(new Object[]{reg[4], reg[5], reg[6], Utilidades.formatearCantidadVista(reg[2].toString()), false, "", reg[8]});
            }
        }
    }

    /**
     * Oculta una columna de tblDetalle (ancho 0).
     */
    private void ocultarColumna(int columna) {
        TableColumn col = tblDetalle.getColumnModel().getColumn(columna);
        col.setMinWidth(0);
        col.setPreferredWidth(0);
        col.setMaxWidth(0);
    }

    private String obtenerValorTabla(int row, int col) {
        Object value = tblDetalle.getValueAt(row, col);
        return value != null ? value.toString() : "";
    }

    /**
     * Cambia el encabezado de una columna de tblDetalle.
     */
    private void tituloColumna(int columna, String texto) {
        tblDetalle.getTableHeader().getColumnModel().getColumn(columna).setHeaderValue(texto);
    }

    /**
     * Indica si el tipo de producto coincide con el del enum. Se compara sin
     * distinguir mayúsculas porque el valor puede llegar como "IMEI"
     * (getTipoProducto) o "Imei" (mapeado por algunos llamadores).
     */
    private static boolean esTipo(String valor, DetalleTipoProducto tipo) {
        return tipo.getValue().equals(valor);
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        filtro = new javax.swing.ButtonGroup();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        popBorrar = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDetalle = new javax.swing.JTable();
        lbTitulo = new javax.swing.JLabel();
        btnCargarRegistros = new javax.swing.JButton();
        pnlImei = new javax.swing.JPanel();
        lbImei = new javax.swing.JLabel();
        txtImei = new javax.swing.JTextField();
        btnCargarImei = new javax.swing.JButton();
        lbColor1 = new javax.swing.JLabel();
        txtColor = new javax.swing.JTextField();
        pnlBuscadorColorTalla = new javax.swing.JPanel();
        lbFiltro1 = new javax.swing.JLabel();
        txtFiltro1 = new javax.swing.JTextField();
        lbFiltro2 = new javax.swing.JLabel();
        txtFiltro2 = new javax.swing.JTextField();
        lbFiltro3 = new javax.swing.JLabel();
        txtFiltro3 = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        pnlColor = new javax.swing.JPanel();
        lbColor = new javax.swing.JLabel();
        txtColor1 = new javax.swing.JTextField();
        btnCargarColor = new javax.swing.JButton();
        txtCant = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        lbTalla = new javax.swing.JLabel();
        txtTalla = new javax.swing.JTextField();
        pnlFechaLote = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtLote = new javax.swing.JTextField();
        btnCargarFecha = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtTemp = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtCant1 = new javax.swing.JTextField();
        dtFVence = new com.toedter.calendar.JDateChooser();

        popBorrar.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        popBorrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/eliminar-cancelar-icono-4935-16.png"))); // NOI18N
        popBorrar.setText("Borrar");
        popBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                popBorrarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(popBorrar);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("ANULACIÓN");
        setResizable(false);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        tblDetalle.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        tblDetalle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Detalle 1", "Detalle 2", "Detalle 3", "Cant", "Sel", "Cant", "Codigo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDetalle.setComponentPopupMenu(jPopupMenu1);
        tblDetalle.setRowHeight(30);
        tblDetalle.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblDetalle);
        if (tblDetalle.getColumnModel().getColumnCount() > 0) {
            tblDetalle.getColumnModel().getColumn(3).setMinWidth(50);
            tblDetalle.getColumnModel().getColumn(3).setPreferredWidth(100);
            tblDetalle.getColumnModel().getColumn(3).setMaxWidth(120);
            tblDetalle.getColumnModel().getColumn(4).setMinWidth(40);
            tblDetalle.getColumnModel().getColumn(4).setPreferredWidth(40);
            tblDetalle.getColumnModel().getColumn(4).setMaxWidth(40);
            tblDetalle.getColumnModel().getColumn(5).setMinWidth(50);
            tblDetalle.getColumnModel().getColumn(5).setPreferredWidth(50);
            tblDetalle.getColumnModel().getColumn(5).setMaxWidth(60);
            tblDetalle.getColumnModel().getColumn(6).setMinWidth(0);
            tblDetalle.getColumnModel().getColumn(6).setPreferredWidth(0);
            tblDetalle.getColumnModel().getColumn(6).setMaxWidth(0);
        }

        lbTitulo.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitulo.setText("DETALLADO DEL PRODUCTO:");

        btnCargarRegistros.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        btnCargarRegistros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ventasIco.png"))); // NOI18N
        btnCargarRegistros.setText("Cargar");
        btnCargarRegistros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarRegistrosActionPerformed(evt);
            }
        });

        pnlImei.setBackground(new java.awt.Color(255, 255, 255));

        lbImei.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lbImei.setText("Imei:");

        txtImei.setBackground(new java.awt.Color(255, 255, 204));
        txtImei.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtImei.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtImei.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtImeiKeyReleased(evt);
            }
        });

        btnCargarImei.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        btnCargarImei.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/aceptar1.png"))); // NOI18N
        btnCargarImei.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarImeiActionPerformed(evt);
            }
        });

        lbColor1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lbColor1.setText("Color:");

        txtColor.setBackground(new java.awt.Color(255, 204, 204));
        txtColor.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtColor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtColor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtColorKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout pnlImeiLayout = new javax.swing.GroupLayout(pnlImei);
        pnlImei.setLayout(pnlImeiLayout);
        pnlImeiLayout.setHorizontalGroup(
            pnlImeiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlImeiLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(lbImei, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtImei, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(lbColor1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtColor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCargarImei, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlImeiLayout.setVerticalGroup(
            pnlImeiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlImeiLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(pnlImeiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnCargarImei, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(pnlImeiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbImei, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtImei, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbColor1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(5, 5, 5))
        );

        pnlBuscadorColorTalla.setBackground(new java.awt.Color(255, 255, 255));
        pnlBuscadorColorTalla.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Buscador", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 0, 10))); // NOI18N

        lbFiltro1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lbFiltro1.setText("Filtro 1:");

        txtFiltro1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtFiltro1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFiltro1KeyReleased(evt);
            }
        });

        lbFiltro2.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lbFiltro2.setText("Filtro 2:");

        txtFiltro2.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtFiltro2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFiltro2KeyReleased(evt);
            }
        });

        lbFiltro3.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lbFiltro3.setText("Filtro 3:");

        txtFiltro3.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtFiltro3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFiltro3KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout pnlBuscadorColorTallaLayout = new javax.swing.GroupLayout(pnlBuscadorColorTalla);
        pnlBuscadorColorTalla.setLayout(pnlBuscadorColorTallaLayout);
        pnlBuscadorColorTallaLayout.setHorizontalGroup(
            pnlBuscadorColorTallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBuscadorColorTallaLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(lbFiltro1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFiltro1)
                .addGap(22, 22, 22)
                .addComponent(lbFiltro2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFiltro2)
                .addGap(21, 21, 21)
                .addComponent(lbFiltro3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFiltro3)
                .addContainerGap())
        );
        pnlBuscadorColorTallaLayout.setVerticalGroup(
            pnlBuscadorColorTallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBuscadorColorTallaLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(pnlBuscadorColorTallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtFiltro3, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbFiltro3)
                    .addComponent(txtFiltro2, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbFiltro2)
                    .addComponent(txtFiltro1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbFiltro1))
                .addGap(5, 5, 5))
        );

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        pnlColor.setBackground(new java.awt.Color(255, 255, 255));

        lbColor.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lbColor.setText("Color:");

        txtColor1.setBackground(new java.awt.Color(255, 204, 204));
        txtColor1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtColor1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtColor1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtColor1ActionPerformed(evt);
            }
        });
        txtColor1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtColor1KeyReleased(evt);
            }
        });

        btnCargarColor.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        btnCargarColor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/aceptar1.png"))); // NOI18N
        btnCargarColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarColorActionPerformed(evt);
            }
        });

        txtCant.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtCant.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCant.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCantKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantKeyTyped(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel6.setText("Cant:");

        lbTalla.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lbTalla.setText("Talla:");

        txtTalla.setBackground(new java.awt.Color(255, 204, 204));
        txtTalla.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtTalla.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTalla.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTallaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTallaKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout pnlColorLayout = new javax.swing.GroupLayout(pnlColor);
        pnlColor.setLayout(pnlColorLayout);
        pnlColorLayout.setHorizontalGroup(
            pnlColorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlColorLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(lbColor, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtColor1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbTalla)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTalla, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCant, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCargarColor, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlColorLayout.setVerticalGroup(
            pnlColorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlColorLayout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(pnlColorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnCargarColor, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlColorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(pnlColorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtColor1)
                            .addComponent(txtCant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTalla)
                            .addComponent(lbTalla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(lbColor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(4, 4, 4))
        );

        pnlFechaLote.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel1.setText("F. Vence:");

        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel2.setText("Lote:");

        txtLote.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtLote.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtLote.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtLoteKeyReleased(evt);
            }
        });

        btnCargarFecha.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        btnCargarFecha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/aceptar1.png"))); // NOI18N
        btnCargarFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarFechaActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel3.setText("Cant:");

        txtTemp.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtTemp.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTemp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTempKeyReleased(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel4.setText("Temp°:");

        txtCant1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtCant1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCant1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCant1KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout pnlFechaLoteLayout = new javax.swing.GroupLayout(pnlFechaLote);
        pnlFechaLote.setLayout(pnlFechaLoteLayout);
        pnlFechaLoteLayout.setHorizontalGroup(
            pnlFechaLoteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFechaLoteLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(pnlFechaLoteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlFechaLoteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(dtFVence, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                    .addComponent(txtLote))
                .addGap(18, 18, 18)
                .addGroup(pnlFechaLoteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlFechaLoteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTemp)
                    .addComponent(txtCant1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnCargarFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlFechaLoteLayout.setVerticalGroup(
            pnlFechaLoteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFechaLoteLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(pnlFechaLoteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCargarFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlFechaLoteLayout.createSequentialGroup()
                        .addGroup(pnlFechaLoteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCant1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dtFVence, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)
                        .addGroup(pnlFechaLoteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTemp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtLote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(3, 3, 3))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCargarRegistros, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator1)
                    .addComponent(lbTitulo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlBuscadorColorTalla, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                    .addComponent(pnlImei, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlColor, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlFechaLote, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(25, 25, 25))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(lbTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(pnlBuscadorColorTalla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
                .addGap(2, 2, 2)
                .addComponent(pnlFechaLote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(pnlColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(pnlImei, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCargarRegistros)
                .addGap(14, 14, 14))
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

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed

    }//GEN-LAST:event_formKeyPressed

    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_formKeyReleased

    private void btnCargarRegistrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarRegistrosActionPerformed
        ndProducto productoOficial = instancias.getSql().getDatosProducto(prodOficial, "bdProductos");

        if (tipoMovimiento.equals("Entrada")) {
            if (!validarEntrada()) {
                return;
            }
            procesarEntrada(productoOficial);
        } else {
            if (!validarSalida()) {
                return;
            }
            procesarSalida(productoOficial);
        }

        this.dispose();
    }//GEN-LAST:event_btnCargarRegistrosActionPerformed

    private boolean validarEntrada() {
        if (tblDetalle.getRowCount() == 0) {
            metodos.msgAdvertenciaAjustado(null, "Debe ingresar almenos un registro");
            txtImei.requestFocus();
            return false;
        }

        for (int i = 0; i < tblDetalle.getRowCount(); i++) {
            if (DetalleTipoProducto.esSerialOImei(tipoProducto)) {
                String id = "";
                try {
                    id = instancias.getSql().imeiExistente(obtenerValorTabla(i, 0));
                } catch (Exception e) {
                }
                if (id != null && !id.equals("")) {
                    String etiqueta = esTipo(tipoProducto, DetalleTipoProducto.IMEI) ? "imei" : "serial";
                    metodos.msgError(null, "El " + etiqueta + " '" + obtenerValorTabla(i, 0) + "' ya existe !");
                    return false;
                }
            } else {
                BigDecimal cantidad;

                try {
                    cantidad = Utilidades.convertirBigDecimal(obtenerValorTabla(i, 3));
                } catch (Exception e) {
                    seleccionarCeldaError(i, 1);
                    metodos.msgError(null, "Cantidad invalida");
                    return false;
                }

                if (cantidad.compareTo(BigDecimal.ZERO) <= 0) {
                    seleccionarCeldaError(i, 1);
                    metodos.msgError(null, "Cantidad debe ser mayor a 0");
                    return false;
                }
            }
        }
        return true;
    }

    private boolean validarSalida() {
        for (int i = 0; i < tblDetalle.getRowCount(); i++) {
            if (!(Boolean) tblDetalle.getValueAt(i, 4)) {
                continue;
            }
            if (!DetalleTipoProducto.esSerialOImei(tipoProducto)) {
                BigDecimal cantidad;
                try {
                    cantidad = Utilidades.convertirBigDecimal(obtenerValorTabla(i, 5));
                } catch (Exception e) {
                    seleccionarCeldaError(i, 1);
                    metodos.msgAdvertenciaAjustado(null, "La cantidad es inválida");
                    return false;
                }

                if (cantidad.compareTo(BigDecimal.ZERO) <= 0) {
                    seleccionarCeldaError(i, 1);
                    metodos.msgAdvertenciaAjustado(null, "La cantidad debe ser mayor a 0");
                    return false;
                }

                BigDecimal stock = Utilidades.convertirBigDecimal(obtenerValorTabla(i, 3));
                if (stock.compareTo(cantidad) < 0) {
                    seleccionarCeldaError(i, 1);
                    metodos.msgAdvertenciaAjustado(null, "¡Cantidades insuficientes!");
                    return false;
                }
            }
        }
        return true;
    }

    private void seleccionarCeldaError(int fila, int columna) {
        tblDetalle.setColumnSelectionInterval(columna, columna);
        tblDetalle.setRowSelectionInterval(fila, fila);
        tblDetalle.editCellAt(fila, columna);
        tblDetalle.transferFocus();
    }

    private void procesarEntrada(ndProducto productoOficial) {
        if (this.tipoDocumento.equals(TipoDocumento.COMPRA.getValor()) || this.tipoDocumento.equals(TipoDocumento.ORDEN_COMPRA.getValor())) {
            VistaIngreso vista = this.tipoDocumento.equals(TipoDocumento.COMPRA.getValor())
                    ? instancias.getIngresos()
                    : instancias.getOrdenCompra();
            cargarDetalleEnVista(vista, productoOficial);
        } else if (tipoDocumento.equals(TipoDocumento.AJUSTE_ENTRADA.getValor())) {
            cargarDetalleEnVista(instancias.getVistaAjusteInventario(), productoOficial);
        } else if (tipoDocumento.equals(TipoDocumento.INVENTARIO_INICIAL.getValor())) {
            cargarDetalleInventarioInicial(productoOficial);
        }
    }

    private BigDecimal cargarFilasDetalle(String idProducto, String descripcion, ReceptorDetallado receptor) {
        BigDecimal cantidadTotal = BigDecimal.ZERO;

        if (DetalleTipoProducto.esSerialOImei(tipoProducto)) {
            for (int i = 0; i < tblDetalle.getRowCount(); i++) {
                cantidadTotal = cantidadTotal.add(BigDecimal.ONE);
                receptor.cargarDetallado(idProducto, obtenerValorTabla(i, 0), "", "", "",
                        BigDecimal.ONE, descripcion, obtenerValorTabla(i, 1), "");
            }
        } else if (DetalleTipoProducto.esColorOTalla(tipoProducto)) {
            for (int i = 0; i < tblDetalle.getRowCount(); i++) {
                BigDecimal cantidad = Utilidades.convertirBigDecimal(obtenerValorTabla(i, 3));
                cantidadTotal = cantidadTotal.add(cantidad);
                if (esTipo(tipoProducto, DetalleTipoProducto.TALLA)) {
                    receptor.cargarDetallado(idProducto, "", "", "", "", cantidad, descripcion, "", obtenerValorTabla(i, 0));
                } else {
                    receptor.cargarDetallado(idProducto, "", "", "", "", cantidad, descripcion, obtenerValorTabla(i, 0), obtenerValorTabla(i, 1));
                }
            }
        } else if (esTipo(tipoProducto, DetalleTipoProducto.FECHA_LOTE)) {
            for (int i = 0; i < tblDetalle.getRowCount(); i++) {
                BigDecimal cantidad = Utilidades.convertirBigDecimal(obtenerValorTabla(i, 3));
                cantidadTotal = cantidadTotal.add(cantidad);
                receptor.cargarDetallado(idProducto, "", obtenerValorTabla(i, 0), obtenerValorTabla(i, 1),
                        obtenerValorTabla(i, 2), cantidad, descripcion, "", "");
            }
        }
        return cantidadTotal;
    }

    private void cargarDetalleEnVista(VistaAjusteInventario vista, ndProducto productoOficial) {
        vista.eliminarRegistros(productoOficial.getIdSistema());
        BigDecimal cantidadTotal = cargarFilasDetalle(productoOficial.getIdSistema(), productoOficial.getDescripcion(), vista);
        vista.cargarProducto1(productoOficial.getIdSistema(), cantidadTotal, 1);
    }

    private void cargarDetalleEnVista(VistaIngreso vista, ndProducto productoOficial) {
        vista.eliminarRegistros(productoOficial.getIdSistema());
        BigDecimal cantidadTotal = cargarFilasDetalle(productoOficial.getIdSistema(), productoOficial.getDescripcion(), vista);
        vista.cargarProductoDesdeDetalleProducto(productoOficial.getIdSistema(), cantidadTotal, this.valorUnitarioDigitado);
    }

    private void cargarDetalleInventarioInicial(ndProducto productoOficial) {
        VistaInventarioInicial invInicial = instancias.getInventarioInicial();
        invInicial.eliminarRegistros();
        BigDecimal cantidadTotal = cargarFilasDetalle(productoOficial.getIdSistema(), productoOficial.getDescripcion(), invInicial);
        invInicial.cargarProducto1(Utilidades.formatearCantidadVista(cantidadTotal));
    }

    private void procesarSalida(ndProducto productoOficial) {
        for (int i = 0; i < tblDetalle.getRowCount(); i++) {
            if (!(Boolean) tblDetalle.getValueAt(i, 4)) {
                continue;
            }
            despacharFilaSalida(i, productoOficial);
        }
    }

    private void despacharFilaSalida(int i, ndProducto productoOficial) {
        String cantidad = obtenerValorTabla(i, 5).replace(",", ".");
        String cod = obtenerValorTabla(i, 6);
        String imei = obtenerValorTabla(i, 0);
        String color = obtenerValorTabla(i, 1);
        String lote = obtenerValorTabla(i, 0);
        String temp = obtenerValorTabla(i, 2);
        String fechaV = obtenerValorTabla(i, 1);
        String id = productoOficial.getIdSistema();

        VistaFactura vistaDocumento = obtenerVistaDocumento();
        if (vistaDocumento != null) {
            if (DetalleTipoProducto.esSerialOImei(tipoProducto)) {
                vistaDocumento.cargarProducto(id, "1", 1, imei, "", cod, false, "", color, "", "", "");
            } else if (DetalleTipoProducto.esColorOTalla(tipoProducto)) {
                vistaDocumento.cargarProducto(id, cantidad, 1, "", "", cod, false, color, imei, "", "", "");
            } else if (esTipo(tipoProducto, DetalleTipoProducto.FECHA_LOTE)) {
                vistaDocumento.cargarProducto(id, cantidad, 1, "", lote, cod, false, "", lote, temp, fechaV, "");
            }
            return;
        }

        ReceptorProductoSalida receptor = obtenerReceptorSalida();
        if (receptor != null) {
            if (DetalleTipoProducto.esSerialOImei(tipoProducto)) {
                receptor.cargarProducto(id, "1", 1, imei, "", cod, "", color, "", "");
            } else if (DetalleTipoProducto.esColorOTalla(tipoProducto)) {
                receptor.cargarProducto(id, cantidad, 1, "", "", cod, color, imei, "", "");
            } else if (esTipo(tipoProducto, DetalleTipoProducto.FECHA_LOTE)) {
                receptor.cargarProducto(id, cantidad, 1, "", lote, cod, "", lote, temp, fechaV);
            }
        }
    }

    private VistaFactura obtenerVistaDocumento() {
        if (tipoDocumento.equals(TipoDocumento.FACTURACION.getValor())) return instancias.getFactura();
        if (tipoDocumento.equals(TipoDocumento.PLAN_SEPARE.getValor())) return instancias.getPlanSepare();
        if (tipoDocumento.equals(TipoDocumento.MESA.getValor())) return instancias.getMesa1();
        if (tipoDocumento.equals(TipoDocumento.PEDIDO.getValor())) return instancias.getPedido();
        return null;
    }

    private ReceptorProductoSalida obtenerReceptorSalida() {
        if (tipoDocumento.equals(TipoDocumento.AJUSTE_SALIDA.getValor())) return instancias.getVistaAjusteInventario();
        if (tipoDocumento.equals("trasladoInterno")) return instancias.getTrasladosInternos();
        if (tipoDocumento.equals("prestamos")) return instancias.getPrestamos();
        return null;
    }

    private void btnCargarImeiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarImeiActionPerformed
        DefaultTableModel modelo = (DefaultTableModel) tblDetalle.getModel();

        String imei, color;
        imei = txtImei.getText();
        color = txtColor.getText();

        if (color.equals("")) {
            if (esTipo(tipoProducto, DetalleTipoProducto.IMEI) || esTipo(tipoProducto, DetalleTipoProducto.SERIAL_COLOR)) {
                metodos.msgAdvertenciaAjustado(null, "¡ Ingrese el color !");
                return;
            }
        }

        if (imei.equals("")) {
            if (esTipo(tipoProducto, DetalleTipoProducto.IMEI)) {
                metodos.msgAdvertenciaAjustado(null, "¡ Ingrese el imei !");
                return;
            } else {
                metodos.msgAdvertenciaAjustado(null, "¡ Ingrese el serial !");
                return;
            }
        }

        if (esTipo(tipoProducto, DetalleTipoProducto.IMEI)) {
            if (imei.length() != 15) {
                metodos.msgAdvertenciaAjustado(null, "Imei errado, verifique el número");
                return;
            }
        }

        for (int i = 0; i < tblDetalle.getRowCount(); i++) {
            if (imei.equals(obtenerValorTabla(i, 0))) {
                if (esTipo(tipoProducto, DetalleTipoProducto.IMEI)) {
                    metodos.msgAdvertenciaAjustado(null, "Este imei ya esta cargado");
                    return;
                } else {
                    metodos.msgAdvertenciaAjustado(null, "Este serial ya esta cargado");
                    return;
                }
            }
        }

        modelo.addRow(new Object[]{imei, color, "", 1, false, "", ""});
        txtImei.setText("");
        txtColor.setText("");
        txtImei.requestFocus();

        if (esTipo(tipoProducto, DetalleTipoProducto.IMEI)) {
            txtImei.setBackground(new Color(255, 255, 204));
        }
    }//GEN-LAST:event_btnCargarImeiActionPerformed

    private void popBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popBorrarActionPerformed
        if (tblDetalle.getSelectedRow() > -1) {

            int fila = tblDetalle.getSelectedRow();

            DefaultTableModel modelo = (DefaultTableModel) tblDetalle.getModel();
            modelo.removeRow(fila);

            tblDetalle.removeEditor();
        } else {
            metodos.msgAdvertencia(null, "Seleccione un registro");
        }
    }//GEN-LAST:event_popBorrarActionPerformed

    private void txtImeiKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtImeiKeyReleased
        if (esTipo(tipoProducto, DetalleTipoProducto.IMEI)) {
            if (txtImei.getText().length() != 15) {
                txtImei.setBackground(new Color(255, 255, 204));
            } else {
                txtImei.setBackground(new Color(153, 255, 153));
            }
        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (esTipo(tipoProducto, DetalleTipoProducto.SERIAL)) {
                btnCargarImeiActionPerformed(null);
            } else {
                txtColor.requestFocus();
            }
        }
    }//GEN-LAST:event_txtImeiKeyReleased

    private void txtFiltro1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFiltro1KeyReleased
        modeloOrdenado.setRowFilter(RowFilter.regexFilter("(?i)" + txtFiltro1.getText(), 0));
    }//GEN-LAST:event_txtFiltro1KeyReleased

    private void txtColorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtColorKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!txtColor.getText().equals("")) {
                btnCargarImeiActionPerformed(null);
            } else {
                ventanaColores(txtColor.getText());
            }
        } else {
            txtColor.setText("");
        }
    }//GEN-LAST:event_txtColorKeyReleased

    private void txtFiltro2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFiltro2KeyReleased
        modeloOrdenado.setRowFilter(RowFilter.regexFilter("(?i)" + txtFiltro2.getText(), 1));
    }//GEN-LAST:event_txtFiltro2KeyReleased

    private void txtFiltro3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFiltro3KeyReleased
        modeloOrdenado.setRowFilter(RowFilter.regexFilter("(?i)" + txtFiltro3.getText(), 2));
    }//GEN-LAST:event_txtFiltro3KeyReleased

    private void txtColor1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtColor1KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!txtColor1.getText().equals("")) {
                if (esTipo(tipoProducto, DetalleTipoProducto.COLOR)) {
                    txtCant.requestFocus();
                } else {
                    txtTalla.requestFocus();
                }
            } else {
                ventanaColores1(txtColor1.getText());
            }
        } else {
            txtColor1.setText("");
        }
    }//GEN-LAST:event_txtColor1KeyReleased

    private void btnCargarColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarColorActionPerformed
        BigDecimal cantidad = Utilidades.convertirBigDecimal(txtCant.getText());
        if (cantidad.compareTo(BigDecimal.ZERO) <= 0) {
            metodos.msgAdvertenciaAjustado(null, "Ingrese la cantidad");
            return;
        }

        if (esTipo(tipoProducto, DetalleTipoProducto.TALLA)) {
            if (txtTalla.getText().equals("")) {
                metodos.msgAdvertenciaAjustado(null, "¡Ingrese una talla!");
                return;
            } else {
                for (int i = 0; i < tblDetalle.getRowCount(); i++) {
                    if (txtTalla.getText().equals(obtenerValorTabla(i, 0))) {
                        BigDecimal cantidadActual = Utilidades.convertirBigDecimal(obtenerValorTabla(i, 3));
                        BigDecimal cantidadTotal = cantidadActual.add(cantidad);
                        tblDetalle.setValueAt(Utilidades.formatearCantidadVista(cantidadTotal), i, 3);
                        txtCant.setText("");
                        txtColor1.setText("");
                        txtTalla.setText("");
                        txtTalla.requestFocus();
                        return;
                    }
                }
            }
        }

        if (esTipo(tipoProducto, DetalleTipoProducto.COLOR)) {
            if (txtColor1.getText().equals("")) {
                metodos.msgAdvertenciaAjustado(null, "¡Ingrese un color!");
                return;
            } else {
                for (int i = 0; i < tblDetalle.getRowCount(); i++) {
                    if (txtColor1.getText().equals(obtenerValorTabla(i, 0))) {
                        BigDecimal cantidadActual = Utilidades.convertirBigDecimal(obtenerValorTabla(i, 3));
                        BigDecimal cantidadTotal = cantidadActual.add(cantidad);
                        tblDetalle.setValueAt(Utilidades.formatearCantidadVista(cantidadTotal), i, 3);
                        txtCant.setText("");
                        txtColor1.setText("");
                        txtTalla.setText("");
                        txtColor1.requestFocus();
                        return;
                    }
                }
            }
        }

        if (esTipo(tipoProducto, DetalleTipoProducto.COLOR_TALLA)) {
            if (txtTalla.getText().equals("")) {
                metodos.msgAdvertenciaAjustado(null, "¡Ingrese una talla!");
                return;
            }

            if (txtColor1.getText().equals("")) {
                metodos.msgAdvertenciaAjustado(null, "¡Ingrese un color!");
                return;
            }

            for (int i = 0; i < tblDetalle.getRowCount(); i++) {
                if (txtColor1.getText().equals(obtenerValorTabla(i, 0)) && txtTalla.getText().equals(obtenerValorTabla(i, 1))) {
                    BigDecimal cantidadActual = Utilidades.convertirBigDecimal(obtenerValorTabla(i, 3));
                    BigDecimal cantidadTotal = cantidadActual.add(cantidad);
                    tblDetalle.setValueAt(Utilidades.formatearCantidadVista(cantidadTotal), i, 3);
                    txtCant.setText("");
                    txtColor1.setText("");
                    txtTalla.setText("");
                    txtColor1.requestFocus();
                    return;
                }
            }
        }

        DefaultTableModel modelo = (DefaultTableModel) tblDetalle.getModel();

        if (esTipo(tipoProducto, DetalleTipoProducto.TALLA)) {
            modelo.addRow(new Object[]{txtTalla.getText(), "", "", Utilidades.formatearCantidadVista(cantidad), false, "", ""});
        } else {
            modelo.addRow(new Object[]{txtColor1.getText(), txtTalla.getText(), "", Utilidades.formatearCantidadVista(cantidad), false, "", ""});
        }

        txtCant.setText("");
        txtColor1.setText("");
        txtTalla.setText("");
        txtColor1.requestFocus();
    }//GEN-LAST:event_btnCargarColorActionPerformed

    private void txtCantKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnCargarColorActionPerformed(null);
        }
    }//GEN-LAST:event_txtCantKeyReleased

    private void txtColor1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtColor1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtColor1ActionPerformed

    private void txtCantKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtCantKeyTyped

    private void txtTallaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTallaKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!txtTalla.getText().equals("")) {
                txtCant.requestFocus();
            } else {
                ventanaTallas(txtTalla.getText());
            }
        } else {
            txtTalla.setText("");
        }
    }//GEN-LAST:event_txtTallaKeyReleased

    private void txtTallaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTallaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTallaKeyTyped

    private void btnCargarFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarFechaActionPerformed
        DefaultTableModel modelo = (DefaultTableModel) tblDetalle.getModel();
        BigDecimal cantidad = Utilidades.convertirBigDecimal(txtCant1.getText());
        if (cantidad.compareTo(BigDecimal.ZERO) <= 0) {
            metodos.msgAdvertenciaAjustado(null, "¡Ingrese la cantidad!");
            return;
        }

        String lote = txtLote.getText();
        String temperatura = txtTemp.getText();
        String fechaVencimiento;

        try {
            fechaVencimiento = metodos.desdeDate(dtFVence.getCalendar());
        } catch (Exception e) {
            metodos.msgAdvertenciaAjustado(null, "Falta fecha de vencimiento");
            return;
        }

        modelo.addRow(new Object[]{lote, fechaVencimiento, temperatura, Utilidades.formatearCantidadVista(cantidad)});

        txtCant1.setText("");
        txtLote.setText("");
        txtTemp.setText("");
        dtFVence.setCalendar(null);
    }//GEN-LAST:event_btnCargarFechaActionPerformed

    private void txtCant1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCant1KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtLote.requestFocus();
        }
    }//GEN-LAST:event_txtCant1KeyReleased

    private void txtLoteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLoteKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtTemp.requestFocus();
        }
    }//GEN-LAST:event_txtLoteKeyReleased

    private void txtTempKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTempKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnCargarFechaActionPerformed(null);
        }
    }//GEN-LAST:event_txtTempKeyReleased

    public void ventanaTallas(String nit) {
        buscTallas buscar = new buscTallas(instancias.getMenu(), rootPaneCheckingEnabled);
        buscar.setLocationRelativeTo(null);
        instancias.setBuscTallas(buscar);
        instancias.setCampoActual(txtTalla);
        txtTalla.requestFocus();
        buscar.setInstancia(instancias);
        buscar.noEncontrado(nit);
        buscar.show();
    }

    public void ventanaColores(String nit) {
        buscColores buscar = new buscColores(instancias.getMenu(), rootPaneCheckingEnabled);
        buscar.setLocationRelativeTo(null);
        instancias.setBuscColores(buscar);
        instancias.setCampoActual(txtColor);
        txtColor.requestFocus();
        buscar.setInstancia(instancias);
        buscar.noEncontrado(nit);
        buscar.show();
    }

    public void ventanaColores1(String nit) {
        buscColores buscar = new buscColores(instancias.getMenu(), rootPaneCheckingEnabled);
        buscar.setLocationRelativeTo(null);
        instancias.setBuscColores(buscar);
        instancias.setCampoActual(txtColor1);
        txtColor1.requestFocus();
        buscar.setInstancia(instancias);
        buscar.noEncontrado(nit);
        buscar.show();
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VistaMovimientoDetalleProducto.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaMovimientoDetalleProducto.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaMovimientoDetalleProducto.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaMovimientoDetalleProducto.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                VistaMovimientoDetalleProducto dialog = new VistaMovimientoDetalleProducto(new javax.swing.JFrame(), true, null, null, "", "", BigDecimal.ZERO);
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
    private javax.swing.JButton btnCargarColor;
    private javax.swing.JButton btnCargarFecha;
    private javax.swing.JButton btnCargarImei;
    private javax.swing.JButton btnCargarRegistros;
    private com.toedter.calendar.JDateChooser dtFVence;
    private javax.swing.ButtonGroup filtro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbColor;
    private javax.swing.JLabel lbColor1;
    private javax.swing.JLabel lbFiltro1;
    private javax.swing.JLabel lbFiltro2;
    private javax.swing.JLabel lbFiltro3;
    private javax.swing.JLabel lbImei;
    private javax.swing.JLabel lbTalla;
    private javax.swing.JLabel lbTitulo;
    private javax.swing.JPanel pnlBuscadorColorTalla;
    private javax.swing.JPanel pnlColor;
    private javax.swing.JPanel pnlFechaLote;
    private javax.swing.JPanel pnlImei;
    private javax.swing.JMenuItem popBorrar;
    private javax.swing.JTable tblDetalle;
    private javax.swing.JTextField txtCant;
    private javax.swing.JTextField txtCant1;
    private javax.swing.JTextField txtColor;
    private javax.swing.JTextField txtColor1;
    private javax.swing.JTextField txtFiltro1;
    private javax.swing.JTextField txtFiltro2;
    private javax.swing.JTextField txtFiltro3;
    private javax.swing.JTextField txtImei;
    private javax.swing.JTextField txtLote;
    private javax.swing.JTextField txtTalla;
    private javax.swing.JTextField txtTemp;
    // End of variables declaration//GEN-END:variables

}
