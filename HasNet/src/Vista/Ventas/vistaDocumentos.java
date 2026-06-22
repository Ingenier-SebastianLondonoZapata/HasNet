package Vista.Ventas;

import dao.Terceros.DaoTerceros;
import dao.Ventas.DaoReimpresiones;
import Enums.TipoDocumento;
import Enums.enumBodegas;
import Enums.DetalleTipoProducto;
import Modelo.Inventario.DetalleProducto;
import Modelo.Inventario.MovimientoInventario;
import Modelo.Terceros.ModeloDatosVehiculo;
import Utilidades.CambiarColorTablaReimpresionYAnulacion;
import clases.Cartera.ndCxc;
import clases.Instancias;
import clases.Ventas.ndFactura;
import clases.Ventas.ndPlanSepare;
import clases.big;
import clases.metodosGenerales;
import Modelo.Terceros.ModeloContacto;
import Modelo.Ventas.ModeloDatosDocumento;
import Modelo.Ventas.ModeloTablaDocumentos;
import inventario.servicio.ServicioInventario;
import Utilidades.Constantes;
import Utilidades.Fechas;
import Utilidades.Utilidades;
import Vista.Productos.VistaInventarioInicial;
import clases.Ventas.ndCotizacion;
import clases.Ventas.ndOServicio1;
import clases.Ventas.ndPedido;
import Vista.Solicitudes.vistaSolicitarPermisos;
import clases.productos.ndProducto;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class VistaDocumentos extends javax.swing.JInternalFrame {

    private final DaoReimpresiones daoReimpresiones = new DaoReimpresiones();
    private final DaoTerceros daoTerceros = new DaoTerceros();
    private DefaultTableModel modeloTablaDocumentos;

    DecimalFormat df = new DecimalFormat("#.00");

    private Object[] datosMaestra;
    private String NUMERO_DOCUMENTO_SELECCIONADO = "";
    private String BODEGA_SELECCIONADA = "";
    private String CORREO_ELECTRONICO = "";
    private boolean detectarClicABoton = false;
    private ModeloDatosDocumento datosDocumento;

    DefaultTableModel modeloPro;

    metodosGenerales metodos = new metodosGenerales();
    Instancias instancias;
    TableRowSorter modeloOrdenado;
    ndPlanSepare nodo1;
    private String infoEmpresa;

    //Barra de titulo
    private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension dimBarra = null;
    private int posicionFact = 1;

    public boolean isDetectarClicABoton() {
        return detectarClicABoton;
    }

    public void setDetectarClicABoton(boolean detectarClicABoton) {
        this.detectarClicABoton = detectarClicABoton;
    }

    public VistaDocumentos() {
        initComponents();

        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
        dimBarra = Barra.getPreferredSize();
        Barra.setSize(0, 0);
        Barra.setPreferredSize(new Dimension(0, 0));
        setBorder(null);
        repaint();

        tblDocumentos.setDefaultRenderer(Object.class, new CambiarColorTablaReimpresionYAnulacion());

        instancias = Instancias.getInstancias();
        txtVendedor.setText(this.instancias.getUsuario());

        cargarTiposDocumentos();

        tapPanel.setSelectedIndex(1);
        tapPanel.setEnabledAt(0, false);
        tapPanel.setEnabledAt(1, false);

        if (!instancias.getRegimen().equals("")) {
            txtIva.setVisible(false);
            txtTotalIva.setVisible(false);
            txtIva1.setVisible(false);
            txtTotalImpo.setVisible(false);
        }

        filtrar.setSelected(true);
        tapPanel.setSelectedIndex(1);
    }

    private void cargarTiposDocumentos() {
        cmbTipoDocumento.addItem("COTIZACIÓN");

        if (instancias.getConfiguraciones().isSepare()) {
            cmbTipoDocumento.addItem("PLAN SEPARE");
        }

        if (instancias.getConfiguraciones().isPedido()) {
            cmbTipoDocumento.addItem("PEDIDOS");
        }

        if (instancias.getConfiguraciones().isOrdenServicio()) {
            cmbTipoDocumento.addItem("ORDEN DE SERVICIO");
        }

        cmbTipoDocumento.addItem("NOTA DÉBITO");
        cmbTipoDocumento.addItem("FACTURA");
        cmbTipoDocumento.setSelectedItem("FACTURA");
    }

    public void consultarMaestros() {
        datosMaestra = instancias.getSql().getDatosMaestra();

        if ((Boolean) datosMaestra[57]) {
            txtConseManual.setVisible(true);
            lbNit7.setVisible(true);
            tblDocumentos.getColumnModel().getColumn(8).setMinWidth(100);
            tblDocumentos.getColumnModel().getColumn(8).setPreferredWidth(100);
            tblDocumentos.getColumnModel().getColumn(8).setMaxWidth(100);
        } else {
            txtConseManual.setVisible(false);
            lbNit7.setVisible(false);
            tblDocumentos.getColumnModel().getColumn(8).setMinWidth(0);
            tblDocumentos.getColumnModel().getColumn(8).setPreferredWidth(0);
            tblDocumentos.getColumnModel().getColumn(8).setMaxWidth(0);
        }

    }

    public void setTipo() {
        if (instancias.getImpresion().equals("pos")) {
            pos.setSelected(true);
        } else if (instancias.getImpresion().equals("facturaCompleta")) {
            carta.setSelected(true);
        } else {
            mediaCarta.setSelected(true);
        }
    }

    private String determinarTablaSeleccionada() {
        Object seleccion = cmbTipoDocumento.getSelectedItem();
        if (seleccion == null) {
            return "";
        }

        String tipo = seleccion.toString();

        switch (tipo) {
            case "FACTURA":
            case "NOTA DÉBITO":
                return "bdFactura";
            case "PLAN SEPARE":
                return "bdPlanSepare";
            case "COTIZACIÓN":
                return "bdCotizacion";
            case "PEDIDOS":
                return "bdPedido";
            case "ORDEN DE SERVICIO":
                return "bdOServicio1";
            default:
                return "";
        }
    }

    private String determinarConsulta() {
        Object seleccion = cmbTipoDocumento.getSelectedItem();
        if (seleccion == null) {
            return "";
        }

        String tipo = seleccion.toString();

        switch (tipo) {
            case "FACTURA":
            case "NOTA DÉBITO":
                return "SELECT F.idFactura, F.factura, F.fechaFactura, T.id, T.nombre, F.vendedor, F.totalGeneral, F.terminal, F.turno, F.modeloContable ";
            case "PLAN SEPARE":
            case "COTIZACIÓN":
            case "PEDIDOS":
            case "ORDEN DE SERVICIO":
                return "SELECT F.idFactura, F.factura, F.fechaFactura, T.id, T.nombre, F.vendedor, F.totalGeneral, F.terminal ";
            default:
                return "";
        }
    }

    private void agregarCondicionPorTipoDocumento(StringBuilder builder) {
        Object seleccion = cmbTipoDocumento.getSelectedItem();
        if (seleccion == null) {
            return;
        }

        String tipo = seleccion.toString();

        if ("FACTURA".equals(tipo)) {
            builder.append("factura LIKE 'FACT-%' ");
        } else if ("NOTA DÉBITO".equals(tipo)) {
            builder.append("factura LIKE 'ND-%' ");
        }
    }

    private void agregarCondicionPorFechas(StringBuilder builder) {
        if (!filtrar.isSelected()) {
            return;
        }

        String fechaInicio = metodos.desdeDate(dtInicio.getCurrent());
        String fechaFin = metodos.desdeDate(dtFinal.getCurrent());

        if (builder.length() > 0) {
            builder.append("AND ");
        }
        builder.append("fechaFactura >= '")
                .append(fechaInicio)
                .append("' AND fechaFactura <= '")
                .append(fechaFin)
                .append("' ");
    }

    private void agregarCondicionPorTerminal(StringBuilder builder) {
        if (instancias.isVisualizarTodasLasFacturas()) {
            return;
        }

        boolean esAdmin = "ADMIN".equals(instancias.getUsuario());
        if (esAdmin) {
            return;
        }

        if (builder.length() > 0) {
            builder.append("AND ");
        }
        builder.append("F.terminal = '")
                .append(instancias.getTerminal())
                .append("' ");
    }

    private void agregarCondicionAnuladas(StringBuilder builder) {

        boolean soloAnuladas = chkSoloAnuladas.isSelected();
        if (!soloAnuladas) {
            return;
        }

        if (builder.length() > 0) {
            builder.append("AND ");
        }

        builder.append("anulada = true ");
    }

    public void actualizarTablaDocumentos() {
        if (!detectarClicABoton) {
            return;
        }

        consultarMaestros();

        String tabla = determinarTablaSeleccionada();
        String consulta = determinarConsulta();
        if (tabla == null || tabla.isEmpty()) {
            return;
        }

        StringBuilder condicionBuilder = new StringBuilder();

        agregarCondicionPorTipoDocumento(condicionBuilder);
        agregarCondicionPorFechas(condicionBuilder);
        agregarCondicionPorTerminal(condicionBuilder);
        agregarCondicionAnuladas(condicionBuilder);

        String condicion = condicionBuilder.toString().trim();
        if (!condicion.isEmpty()) {
            condicion = "WHERE " + condicion;
        }

        System.out.println("Condiciones documentos: " + condicion);
        List<ModeloTablaDocumentos> documentos = daoReimpresiones.obtenerDocumentos(consulta, condicion, tabla);

        limpiarDatosTabla();
        cargarDocumentosTabla(documentos);
        asginarFiltrosTablaDocumentos();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jPanel3 = new javax.swing.JPanel();
        tapPanel = new javax.swing.JTabbedPane();
        pnlFormulario1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblProductos = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        lbNit = new javax.swing.JLabel();
        txtNit = new javax.swing.JTextField();
        lbNombre = new javax.swing.JLabel();
        lbDireccion = new javax.swing.JLabel();
        lbVendedor = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        txtVendedor = new javax.swing.JTextField();
        lbTelefono = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        btnBuscTerceros1 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        lbSubtotal1 = new javax.swing.JLabel();
        lbSubtotal2 = new javax.swing.JLabel();
        lbSubtotal = new javax.swing.JLabel();
        txtFechaFactura = new javax.swing.JLabel();
        txtVencimiento = new javax.swing.JLabel();
        txtSubTotal = new javax.swing.JTextField();
        lbTotalDescuento = new javax.swing.JLabel();
        txtTotalDescuentos = new javax.swing.JTextField();
        txtIva = new javax.swing.JLabel();
        txtIva1 = new javax.swing.JLabel();
        etiqTotal = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        txtTotalImpo = new javax.swing.JTextField();
        txtTotalIva = new javax.swing.JTextField();
        txtDiasPlazo1 = new javax.swing.JTextField();
        txtDiasPlazo = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        pos = new javax.swing.JRadioButton();
        carta = new javax.swing.JRadioButton();
        mediaCarta = new javax.swing.JRadioButton();
        btnBuscTerceros2 = new javax.swing.JButton();
        btnAnular = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtObservaciones = new javax.swing.JTextArea();
        lblDocumentoAnulado = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDocumentos = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        lbNit2 = new javax.swing.JLabel();
        txtConsecutivo = new javax.swing.JTextField();
        lbNit3 = new javax.swing.JLabel();
        txtIdentificadorCliente = new javax.swing.JTextField();
        lbNit4 = new javax.swing.JLabel();
        txtNombreCliente = new javax.swing.JTextField();
        lbNit7 = new javax.swing.JLabel();
        txtConseManual = new javax.swing.JTextField();
        lbNit6 = new javax.swing.JLabel();
        cmbTipoDocumento = new javax.swing.JComboBox();
        lbNit10 = new javax.swing.JLabel();
        cmbEstadoDocumento = new javax.swing.JComboBox();
        jPanel4 = new javax.swing.JPanel();
        filtrar = new javax.swing.JCheckBox();
        lbNit8 = new javax.swing.JLabel();
        dtInicio = new datechooser.beans.DateChooserCombo();
        lbNit9 = new javax.swing.JLabel();
        dtFinal = new datechooser.beans.DateChooserCombo();
        chkSoloAnuladas = new javax.swing.JCheckBox();

        jMenuItem1.setText("Ascendente");
        jPopupMenu1.add(jMenuItem1);

        jMenuItem2.setText("Descendente");
        jPopupMenu1.add(jMenuItem2);

        setTitle("Factura");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        pnlFormulario1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel5MouseEntered(evt);
            }
        });
        jPanel5.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jPanel5MouseMoved(evt);
            }
        });
        jPanel5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jPanel5FocusGained(evt);
            }
        });
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblProductos.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tblProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblProductos.setRowHeight(24);
        tblProductos.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tblProductos);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Información del cliente", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14))); // NOI18N

        lbNit.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbNit.setText("CC/Nit:");

        txtNit.setEditable(false);
        txtNit.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtNit.setName("CC/NIT"); // NOI18N
        txtNit.setSelectionColor(new java.awt.Color(0, 0, 0));
        txtNit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNitKeyReleased(evt);
            }
        });

        lbNombre.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbNombre.setText("Nombre:");

        lbDireccion.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbDireccion.setText("Dirección:");

        lbVendedor.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbVendedor.setText("Vendedor:");

        txtNombre.setEditable(false);
        txtNombre.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtNombre.setName("Nombre"); // NOI18N
        txtNombre.setSelectionColor(new java.awt.Color(0, 0, 0));

        txtDireccion.setEditable(false);
        txtDireccion.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtDireccion.setName("Direccion"); // NOI18N
        txtDireccion.setSelectionColor(new java.awt.Color(0, 0, 0));

        txtVendedor.setEditable(false);
        txtVendedor.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtVendedor.setName("Vendedor"); // NOI18N
        txtVendedor.setSelectionColor(new java.awt.Color(0, 0, 0));

        lbTelefono.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbTelefono.setText("Telefono:");

        txtTelefono.setEditable(false);
        txtTelefono.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtTelefono.setName("Telefono"); // NOI18N
        txtTelefono.setSelectionColor(new java.awt.Color(0, 0, 0));

        btnBuscTerceros1.setBackground(new java.awt.Color(204, 204, 204));
        btnBuscTerceros1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnBuscTerceros1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/anterior.png"))); // NOI18N
        btnBuscTerceros1.setText("REGRESAR AL LISTADO");
        btnBuscTerceros1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscTerceros1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnBuscTerceros1.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnBuscTerceros1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscTerceros1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(btnBuscTerceros1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbNombre, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbDireccion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbVendedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbNit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addComponent(txtNit, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lbTelefono)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTelefono, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE))
                            .addComponent(txtDireccion)
                            .addComponent(txtNombre)
                            .addComponent(txtVendedor, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(5, 5, 5))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbNit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNit)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbNombre)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtDireccion)
                    .addComponent(lbDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbVendedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBuscTerceros1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8))
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Información de la factura", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14))); // NOI18N

        lbSubtotal1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbSubtotal1.setText("Fecha factura:");

        lbSubtotal2.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbSubtotal2.setText("Vencimiento:");

        lbSubtotal.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbSubtotal.setText("Subtotal sin IVA:");

        txtFechaFactura.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtFechaFactura.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtFechaFactura.setText(" ");
        txtFechaFactura.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtVencimiento.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtVencimiento.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtVencimiento.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtSubTotal.setEditable(false);
        txtSubTotal.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtSubTotal.setText("0");
        txtSubTotal.setSelectionColor(new java.awt.Color(0, 0, 0));

        lbTotalDescuento.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbTotalDescuento.setText("Total descuentos:");

        txtTotalDescuentos.setEditable(false);
        txtTotalDescuentos.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtTotalDescuentos.setText("0");
        txtTotalDescuentos.setSelectionColor(new java.awt.Color(0, 0, 0));

        txtIva.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txtIva.setText("IVA:");

        txtIva1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txtIva1.setText("Impoconsumo:");

        etiqTotal.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        etiqTotal.setText("Total:");

        txtTotal.setEditable(false);
        txtTotal.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtTotal.setText("0");
        txtTotal.setSelectionColor(new java.awt.Color(0, 0, 0));

        txtTotalImpo.setEditable(false);
        txtTotalImpo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtTotalImpo.setText("0");
        txtTotalImpo.setSelectionColor(new java.awt.Color(0, 0, 0));

        txtTotalIva.setEditable(false);
        txtTotalIva.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtTotalIva.setText("0");
        txtTotalIva.setSelectionColor(new java.awt.Color(0, 0, 0));

        txtDiasPlazo1.setEditable(false);
        txtDiasPlazo1.setBackground(new java.awt.Color(255, 255, 255));
        txtDiasPlazo1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txtDiasPlazo1.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtDiasPlazo1.setText("Dias de plazo:");
        txtDiasPlazo1.setBorder(null);
        txtDiasPlazo1.setName("Plazo"); // NOI18N
        txtDiasPlazo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDiasPlazo1ActionPerformed(evt);
            }
        });
        txtDiasPlazo1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDiasPlazo1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDiasPlazo1KeyTyped(evt);
            }
        });

        txtDiasPlazo.setEditable(false);
        txtDiasPlazo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtDiasPlazo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDiasPlazo.setName("Plazo"); // NOI18N
        txtDiasPlazo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDiasPlazoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDiasPlazoKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbSubtotal1, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                            .addComponent(lbSubtotal2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtVencimiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtFechaFactura, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lbSubtotal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(etiqTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtIva, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbTotalDescuento, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                            .addComponent(txtIva1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtDiasPlazo1))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtTotalImpo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                                .addComponent(txtTotalIva, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtSubTotal, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtTotalDescuentos, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtTotal))
                            .addComponent(txtDiasPlazo, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(5, 5, 5))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbSubtotal1, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(txtFechaFactura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbSubtotal2, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(txtVencimiento, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
                .addGap(2, 2, 2)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDiasPlazo1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDiasPlazo, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbSubtotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtSubTotal))
                .addGap(3, 3, 3)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTotalDescuentos)
                    .addComponent(lbTotalDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTotalIva)
                    .addComponent(txtIva, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTotalImpo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIva1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiqTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Reimpresión", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14))); // NOI18N

        pos.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(pos);
        pos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        pos.setText("POS");
        pos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                posActionPerformed(evt);
            }
        });

        carta.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(carta);
        carta.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        carta.setText("CARTA");
        carta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cartaActionPerformed(evt);
            }
        });

        mediaCarta.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(mediaCarta);
        mediaCarta.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        mediaCarta.setSelected(true);
        mediaCarta.setText("MEDIA CARTA");
        mediaCarta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mediaCartaActionPerformed(evt);
            }
        });

        btnBuscTerceros2.setBackground(new java.awt.Color(247, 220, 111));
        btnBuscTerceros2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
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
        btnAnular.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnAnular.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/borrar2.png"))); // NOI18N
        btnAnular.setText("ANULAR REGISTRO");
        btnAnular.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAnular.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnAnular.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnAnular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnularActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnBuscTerceros2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(pos)
                        .addGap(18, 18, 18)
                        .addComponent(carta, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mediaCarta)
                    .addComponent(btnAnular, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pos)
                    .addComponent(carta)
                    .addComponent(mediaCarta))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnBuscTerceros2, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                    .addComponent(btnAnular, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Observaciones", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14))); // NOI18N

        txtObservaciones.setColumns(20);
        txtObservaciones.setLineWrap(true);
        txtObservaciones.setRows(3);
        txtObservaciones.setText("\n");
        txtObservaciones.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtObservaciones.setEnabled(false);
        jScrollPane4.setViewportView(txtObservaciones);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jScrollPane4)
                .addGap(10, 10, 10))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jScrollPane4)
                .addGap(5, 5, 5))
        );

        lblDocumentoAnulado.setBackground(new java.awt.Color(241, 148, 138));
        lblDocumentoAnulado.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lblDocumentoAnulado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDocumentoAnulado.setText("EL DOCUMENTO SE ENCUENTRA ANULADO");
        lblDocumentoAnulado.setOpaque(true);

        javax.swing.GroupLayout pnlFormulario1Layout = new javax.swing.GroupLayout(pnlFormulario1);
        pnlFormulario1.setLayout(pnlFormulario1Layout);
        pnlFormulario1Layout.setHorizontalGroup(
            pnlFormulario1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormulario1Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlFormulario1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDocumentoAnulado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlFormulario1Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addGroup(pnlFormulario1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlFormulario1Layout.createSequentialGroup()
                                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE))
                            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jScrollPane2))
                .addGap(10, 10, 10))
        );
        pnlFormulario1Layout.setVerticalGroup(
            pnlFormulario1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormulario1Layout.createSequentialGroup()
                .addComponent(lblDocumentoAnulado, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(pnlFormulario1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlFormulario1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnlFormulario1Layout.createSequentialGroup()
                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(3, 3, 3)
                            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                .addContainerGap())
        );

        tapPanel.addTab("Vista Previa", pnlFormulario1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        tblDocumentos.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tblDocumentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "FACTURA", "FACTURA", "FECHA", "NIT CLIENTE", "CLIENTE", "VENDEDOR", "TOTAL", "TERMINAL", "CONSE.MANUAL", "TIPO_FACTURA"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDocumentos.setRowHeight(24);
        tblDocumentos.getTableHeader().setReorderingAllowed(false);
        tblDocumentos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDocumentosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDocumentos);
        if (tblDocumentos.getColumnModel().getColumnCount() > 0) {
            tblDocumentos.getColumnModel().getColumn(0).setMinWidth(0);
            tblDocumentos.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblDocumentos.getColumnModel().getColumn(0).setMaxWidth(0);
            tblDocumentos.getColumnModel().getColumn(0).setHeaderValue("FACTURA");
            tblDocumentos.getColumnModel().getColumn(1).setPreferredWidth(120);
            tblDocumentos.getColumnModel().getColumn(1).setMaxWidth(150);
            tblDocumentos.getColumnModel().getColumn(1).setHeaderValue("FACTURA");
            tblDocumentos.getColumnModel().getColumn(2).setMinWidth(85);
            tblDocumentos.getColumnModel().getColumn(2).setPreferredWidth(85);
            tblDocumentos.getColumnModel().getColumn(2).setMaxWidth(85);
            tblDocumentos.getColumnModel().getColumn(2).setHeaderValue("FECHA");
            tblDocumentos.getColumnModel().getColumn(3).setPreferredWidth(120);
            tblDocumentos.getColumnModel().getColumn(3).setMaxWidth(160);
            tblDocumentos.getColumnModel().getColumn(3).setHeaderValue("NIT CLIENTE");
            tblDocumentos.getColumnModel().getColumn(4).setHeaderValue("CLIENTE");
            tblDocumentos.getColumnModel().getColumn(5).setHeaderValue("VENDEDOR");
            tblDocumentos.getColumnModel().getColumn(6).setPreferredWidth(105);
            tblDocumentos.getColumnModel().getColumn(6).setMaxWidth(150);
            tblDocumentos.getColumnModel().getColumn(6).setHeaderValue("TOTAL");
            tblDocumentos.getColumnModel().getColumn(7).setMinWidth(80);
            tblDocumentos.getColumnModel().getColumn(7).setPreferredWidth(80);
            tblDocumentos.getColumnModel().getColumn(7).setMaxWidth(80);
            tblDocumentos.getColumnModel().getColumn(7).setHeaderValue("TERMINAL");
            tblDocumentos.getColumnModel().getColumn(8).setMinWidth(100);
            tblDocumentos.getColumnModel().getColumn(8).setPreferredWidth(100);
            tblDocumentos.getColumnModel().getColumn(8).setMaxWidth(100);
            tblDocumentos.getColumnModel().getColumn(8).setHeaderValue("CONSE.MANUAL");
            tblDocumentos.getColumnModel().getColumn(9).setMinWidth(0);
            tblDocumentos.getColumnModel().getColumn(9).setPreferredWidth(0);
            tblDocumentos.getColumnModel().getColumn(9).setMaxWidth(0);
            tblDocumentos.getColumnModel().getColumn(9).setHeaderValue("TIPO_FACTURA");
        }

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Filtros", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 0, 12))); // NOI18N

        lbNit2.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbNit2.setText("Número del documento:");

        txtConsecutivo.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtConsecutivo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtConsecutivoKeyReleased(evt);
            }
        });

        lbNit3.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbNit3.setText("Cédula o Nit:");

        txtIdentificadorCliente.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtIdentificadorCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIdentificadorClienteKeyReleased(evt);
            }
        });

        lbNit4.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbNit4.setText("Nombre del cliente:");

        txtNombreCliente.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtNombreCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreClienteKeyReleased(evt);
            }
        });

        lbNit7.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbNit7.setText("Consecutivo manual:");

        txtConseManual.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtConseManual.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtConseManualKeyReleased(evt);
            }
        });

        lbNit6.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbNit6.setText("Tipo de documento:");

        cmbTipoDocumento.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        cmbTipoDocumento.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbTipoDocumentoItemStateChanged(evt);
            }
        });

        lbNit10.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbNit10.setText("Estado del documento:");

        cmbEstadoDocumento.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        cmbEstadoDocumento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "FINALIZADO", "PENDIENTE" }));
        cmbEstadoDocumento.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbEstadoDocumentoItemStateChanged(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        filtrar.setBackground(new java.awt.Color(255, 255, 255));
        filtrar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        filtrar.setText("¿Desea aplicar el filtro por rango de fecha?");
        filtrar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                filtrarItemStateChanged(evt);
            }
        });

        lbNit8.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbNit8.setText("Fecha inicial:");

        dtInicio.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 12));
        dtInicio.addCommitListener(new datechooser.events.CommitListener() {
            public void onCommit(datechooser.events.CommitEvent evt) {
                dtInicioOnCommit(evt);
            }
        });

        lbNit9.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbNit9.setText("Fecha final:");

        dtFinal.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 12));
        dtFinal.addCommitListener(new datechooser.events.CommitListener() {
            public void onCommit(datechooser.events.CommitEvent evt) {
                dtFinalOnCommit(evt);
            }
        });

        chkSoloAnuladas.setBackground(new java.awt.Color(255, 255, 255));
        chkSoloAnuladas.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        chkSoloAnuladas.setText("¿Ver solo anulados?");
        chkSoloAnuladas.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkSoloAnuladasItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(filtrar)
                .addGap(18, 18, 18)
                .addComponent(lbNit8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dtInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(lbNit9, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dtFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(chkSoloAnuladas)
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkSoloAnuladas, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(dtFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(lbNit9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(dtInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(lbNit8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(filtrar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbNit2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbNit10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbNit6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbTipoDocumento, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbEstadoDocumento, 0, 142, Short.MAX_VALUE)
                            .addComponent(txtConsecutivo))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbNit7, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                            .addComponent(lbNit4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbNit3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtIdentificadorCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtConseManual, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 559, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(71, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbEstadoDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbNit10, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cmbTipoDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lbNit4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lbNit6, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbNit3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtIdentificadorCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)))
                .addGap(1, 1, 1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtConsecutivo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNit2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbNit7, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                        .addComponent(txtConseManual, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
                .addContainerGap())
        );

        tapPanel.addTab("Listado de facturas", jPanel2);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(tapPanel)
                .addGap(10, 10, 10))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(tapPanel)
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(4, 4, 4))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void posActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_posActionPerformed

    }//GEN-LAST:event_posActionPerformed

    private void cartaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cartaActionPerformed

    }//GEN-LAST:event_cartaActionPerformed

    private void mediaCartaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mediaCartaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mediaCartaActionPerformed

    private void txtNombreClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreClienteKeyReleased
        modeloTablaDocumentos = (DefaultTableModel) tblDocumentos.getModel();
        modeloOrdenado = new TableRowSorter<>(modeloTablaDocumentos);
        tblDocumentos.setRowSorter(modeloOrdenado);

        modeloOrdenado.setRowFilter(RowFilter.regexFilter("(?i)" + txtNombreCliente.getText(), 4));
    }//GEN-LAST:event_txtNombreClienteKeyReleased

    private void txtIdentificadorClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdentificadorClienteKeyReleased
        modeloOrdenado.setRowFilter(RowFilter.regexFilter("(?i)" + txtIdentificadorCliente.getText(), 3));
    }//GEN-LAST:event_txtIdentificadorClienteKeyReleased

    private void txtConsecutivoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtConsecutivoKeyReleased
        modeloOrdenado.setRowFilter(RowFilter.regexFilter("(?i)" + txtConsecutivo.getText(), 1));
        txtNombreCliente.setText("");
        txtIdentificadorCliente.setText("");
        txtConseManual.setText("");

        try {
            int i;
            for (i = 0; i < tblDocumentos.getRowCount(); i++) {
                if (cmbTipoDocumento.getSelectedItem().equals("FACTURA")) {
                    if (tblDocumentos.getValueAt(i, posicionFact).equals("FACT-" + txtConsecutivo.getText())) {
                        tblDocumentos.setColumnSelectionInterval(0, 0);
                        tblDocumentos.setRowSelectionInterval(i, i);
                        tblDocumentos.getSelectionModel().setSelectionInterval(i, i);
                        tblDocumentos.scrollRectToVisible(new Rectangle(tblDocumentos.getCellRect(i, 0, true)));
                        break;
                    }
                } else if (cmbTipoDocumento.getSelectedItem().equals("PLAN SEPARE")) {
                    if (tblDocumentos.getValueAt(i, posicionFact).equals("SEPARE-" + txtConsecutivo.getText())) {
                        tblDocumentos.setColumnSelectionInterval(0, 0);
                        tblDocumentos.setRowSelectionInterval(i, i);
                        tblDocumentos.getSelectionModel().setSelectionInterval(i, i);
                        tblDocumentos.scrollRectToVisible(new Rectangle(tblDocumentos.getCellRect(i, 0, true)));
                        break;
                    }
                }
            }

            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                verInformacionDetalladaDocumento();
            }
        } catch (Exception e) {

        }
    }//GEN-LAST:event_txtConsecutivoKeyReleased

    private void txtConseManualKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtConseManualKeyReleased
        modeloOrdenado.setRowFilter(RowFilter.regexFilter("(?i)" + txtConseManual.getText(), 8));
        txtNombreCliente.setText("");
        txtIdentificadorCliente.setText("");
        txtConsecutivo.setText("");
    }//GEN-LAST:event_txtConseManualKeyReleased

    private void jPanel5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MouseEntered

    }//GEN-LAST:event_jPanel5MouseEntered

    private void jPanel5MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MouseMoved

    }//GEN-LAST:event_jPanel5MouseMoved

    private void jPanel5FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPanel5FocusGained

    }//GEN-LAST:event_jPanel5FocusGained

    private void btnBuscTerceros2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscTerceros2ActionPerformed
        boolean esRestaurante = instancias.getConfiguraciones().isRestaurante();
        boolean esServicioAutomotor = instancias.getConfiguraciones().isServicioAutomotor();

        boolean usaTurno = Boolean.TRUE.equals(datosMaestra[54]);
        boolean usaSegundoNumeroFactura = Boolean.TRUE.equals(datosMaestra[57]);

        String titulo = "";
        if (esRestaurante && usaTurno) {
            titulo = "Turno";
        } else if (!esRestaurante && usaSegundoNumeroFactura) {
            titulo = "Num Fact2";
        }

        boolean mantenerEncabezado = Boolean.TRUE.equals(datosMaestra[80]);
        String encabezado = datosDocumento.isEsAnulado() ? "Reimpresión (Anulada)" : "Reimpresión";
        encabezado = mantenerEncabezado ? encabezado : "";
        String impresoraEstablecida = obtenerImpresoraEstablecida();
        String tipoImpresion = obtenerTipoImpresion();
        String informacionLegal = instancias.getLegal() == null ? "" : instancias.getLegal();
        String pieDePagina = instancias.getPie() == null ? "" : instancias.getLegal();

        String tipoOpcion = cmbTipoDocumento.getSelectedItem().toString();
        switch (tipoOpcion) {
            case "FACTURA":
            case "NOTA DÉBITO": {
                String documento = "FACT-" + NUMERO_DOCUMENTO_SELECCIONADO;
                Boolean notaDebito = cmbTipoDocumento.getSelectedItem().equals("NOTA DÉBITO");
                String verImpoconsumoEnImpresion = datosMaestra[84].toString();
                String verRetencionesEnImpresion = datosMaestra[85].toString();

                boolean usaAgrupada = esRestaurante && Boolean.TRUE.equals(datosMaestra[50]);
                String condicion = usaAgrupada || esRestaurante ? metodos.sentenciaImpresionFactura("agrupada", " WHERE bdFactura.factura = '" + documento + "' ")
                        : metodos.sentenciaImpresionFactura("", " WHERE bdFactura.factura = '" + documento + "' ");

                instancias.getReporte().ver_Factura(txtObservaciones.getText(), infoEmpresa, informacionLegal, encabezado, pieDePagina, tipoImpresion,
                        documento, false, titulo, impresoraEstablecida, verImpoconsumoEnImpresion, verRetencionesEnImpresion, condicion, notaDebito);
                break;
            }

            case "PLAN SEPARE": {
                String documento = "SEPARE-" + NUMERO_DOCUMENTO_SELECCIONADO;
                instancias.getReporte().ver_Separe(documento, txtObservaciones.getText(), infoEmpresa, informacionLegal, encabezado, pieDePagina, tipoImpresion, false);
                break;
            }

            case "COTIZACIÓN": {
                boolean previsualizarCotizacion = (Boolean) datosMaestra[70];
                String documento = "COTI-" + NUMERO_DOCUMENTO_SELECCIONADO;
                instancias.getReporte().ver_Cotiza(documento, txtObservaciones.getText(), instancias.getInformacionEmpresa(), informacionLegal, tipoImpresion, previsualizarCotizacion);
                break;
            }

            case "PEDIDOS": {
                boolean previsualizarPedidos = (Boolean) datosMaestra[72];
                String documento = "PEDIDO-" + NUMERO_DOCUMENTO_SELECCIONADO;
                instancias.getReporte().ver_Pedido(documento, txtObservaciones.getText(), infoEmpresa, informacionLegal, "", pieDePagina, tipoImpresion, previsualizarPedidos);
                break;
            }

            case "ORDEN DE SERVICIO": {
                String documento = "OSERV-" + NUMERO_DOCUMENTO_SELECCIONADO;
                ModeloDatosVehiculo datosVehiculo = daoTerceros.obtenerDatosVehiculo(documento);
                instancias.getReporte().ver_oServicio(documento, txtObservaciones.getText(), false, datosVehiculo.getTipoVehiculo(), esServicioAutomotor ? "" : tipoImpresion);
                break;
            }

            default:
                return;
        }
    }//GEN-LAST:event_btnBuscTerceros2ActionPerformed

    private void tblDocumentosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDocumentosMouseClicked
        Object tipoSeleccionado = cmbTipoDocumento.getSelectedItem();
        if (tipoSeleccionado != null) {
            String tipo = tipoSeleccionado.toString();
            String prefijo = Constantes.obtenerListadoPrefijosDocumento().get(tipo);

            if (prefijo != null && tblDocumentos.getSelectedRow() != -1) {
                String valor = (String) tblDocumentos.getValueAt(tblDocumentos.getSelectedRow(), posicionFact);
                txtConsecutivo.setText(valor.replaceFirst("^" + prefijo, ""));
            }
        }

        if (evt.getClickCount() == 2) {
            verInformacionDetalladaDocumento();
        }
    }//GEN-LAST:event_tblDocumentosMouseClicked

    private void cmbTipoDocumentoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbTipoDocumentoItemStateChanged
        actualizarTablaDocumentos();
    }//GEN-LAST:event_cmbTipoDocumentoItemStateChanged

    private void dtInicioOnCommit(datechooser.events.CommitEvent evt) {//GEN-FIRST:event_dtInicioOnCommit
        actualizarTablaDocumentos();
    }//GEN-LAST:event_dtInicioOnCommit

    private void dtFinalOnCommit(datechooser.events.CommitEvent evt) {//GEN-FIRST:event_dtFinalOnCommit
        actualizarTablaDocumentos();
    }//GEN-LAST:event_dtFinalOnCommit

    private void txtNitKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNitKeyReleased

    }//GEN-LAST:event_txtNitKeyReleased

    private void btnBuscTerceros1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscTerceros1ActionPerformed
        tapPanel.setSelectedIndex(1);
        instancias.getMenu().cambiarTitulo("REIMPRESIÓN DE FACTURAS");
    }//GEN-LAST:event_btnBuscTerceros1ActionPerformed

    private void txtDiasPlazo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiasPlazo1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiasPlazo1ActionPerformed

    private void txtDiasPlazo1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiasPlazo1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiasPlazo1KeyReleased

    private void txtDiasPlazo1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiasPlazo1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiasPlazo1KeyTyped

    private void txtDiasPlazoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiasPlazoKeyReleased

    }//GEN-LAST:event_txtDiasPlazoKeyReleased

    private void txtDiasPlazoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiasPlazoKeyTyped

    }//GEN-LAST:event_txtDiasPlazoKeyTyped

    private void filtrarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_filtrarItemStateChanged
        actualizarTablaDocumentos();
    }//GEN-LAST:event_filtrarItemStateChanged

    private void btnAnularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnularActionPerformed
        if (!instancias.getUsuario().equals("ADMIN")) {
            vistaSolicitarPermisos permisos = new vistaSolicitarPermisos(null, true, "ANULACIÓN FACTURA", "ANULACION", "FACT-" + NUMERO_DOCUMENTO_SELECCIONADO, "anulacion");
            permisos.setLocationRelativeTo(null);
            permisos.setVisible(true);
        } else {
            if (metodos.msgPregunta(this, "¿Desea continuar?") != 0) {
                return;
            } else {
                String nota = metodos.msgIngresar(null, "NOTA: ");
                if (nota == null || nota.isEmpty()) {
                    return;
                }

                anularFactura(nota);
            }
        }
    }//GEN-LAST:event_btnAnularActionPerformed

    private void cmbEstadoDocumentoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbEstadoDocumentoItemStateChanged
        if (cmbEstadoDocumento.getSelectedItem().toString().equals("PENDIENTE")) {
            cmbTipoDocumento.removeItem("FACTURA");
        } else {
            boolean existe = false;
            for (int i = 0; i < cmbTipoDocumento.getItemCount(); i++) {
                if (cmbTipoDocumento.getItemAt(i).equals("FACTURA")) {
                    existe = true;
                    break;
                }
            }

            if (!existe) {
                cmbTipoDocumento.addItem("FACTURA");
                cmbTipoDocumento.setSelectedItem("FACTURA");
            }
        }
    }//GEN-LAST:event_cmbEstadoDocumentoItemStateChanged

    private void chkSoloAnuladasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkSoloAnuladasItemStateChanged
        actualizarTablaDocumentos();
    }//GEN-LAST:event_chkSoloAnuladasItemStateChanged

    /*private void cargarProductosDiscoteo(Map<String, Double> acumulados) {
     Object[][] productos = instancias.getSql().getCantidadesDiscosteo(tblProductos.getValueAt(i, 14).toString());

     for (int k = 0; k < productos.length; k++) {
     String idProducto1 = productos[k][0].toString();
     Double cantidadMovimiento1 = Double.parseDouble(productos[k][1].toString());
     validarProductoYAcumular(acumulados, idProducto1, cantidadMovimiento1);
     }
     }

     private List<MovimientoInventario> generarListadoProductos(String tablaUtilizada) {
     Map<String, Double> acumulados = new HashMap<String, Double>();

     for (int i = 0; i < tblProductos.getRowCount(); i++) {
     String idProducto = tblProductos.getValueAt(i, 14).toString();
     double cantidadMovimiento = Double.parseDouble(tblProductos.getValueAt(i, 13).toString().replace(",", "."));

     ndProducto producto = instancias.getSql().getDatosProducto(tblProductos.getValueAt(i, 14).toString(), tablaUtilizada);

     if (!producto.getUsuario().equals("ADMIN")) {
     String preparacion = tblProductos.getValueAt(i, 10) == null ? "" : tblProductos.getValueAt(i, 10).toString();

     if (preparacion.equals("")) {

     cargarProductosDiscosteo();

     } else {
     String opciones2[];
     String opciones = preparacion.split("; ")[3];
     if (!opciones.equals("")) {
     opciones2 = opciones.split(", ");
     for (int k = 0; k < opciones2.length; k++) {

     String principal = opciones2[k].split("/")[0];
     String codigo = opciones2[k].split("/")[1];
     String cant = opciones2[k].split("/")[2];
     String estado = opciones2[k].split("/")[3];

     if (estado.equals(" true")) {
     validarProductoYAcumular(acumulados, idProducto1, cantidadMovimiento1);
     }
     }
     }

     if (acumulados.containsKey(idProducto)) {
     Double cantidadActual = acumulados.get(idProducto);
     acumulados.put(idProducto, cantidadActual + cantidadMovimiento);
     } else {
     acumulados.put(idProducto, cantidadMovimiento);
     }
     }

                
     List<MovimientoInventario> productosAgrupados = new ArrayList<>();
     for (Map.Entry<String, Double> entry : acumulados.entrySet()) {
     productosAgrupados.add(new MovimientoInventario(entry.getKey(), entry.getValue()));
     }

     return productosAgrupados;
     }
     }
     }

     private void validarProductoYAcumular(Map<String, Double> acumulados, String idProducto, double cantidadMovimiento) {
     if (acumulados.containsKey(idProducto)) {
     Double cantidadActual = acumulados.get(idProducto);
     acumulados.put(idProducto, cantidadActual + cantidadMovimiento);
     } else {
     acumulados.put(idProducto, cantidadMovimiento);
     }
     }

     private String obtenerTipoMovimiento() {
     return "";
     }*/
    public void anularFactura(String nota) {

        if (cmbTipoDocumento.getSelectedItem().equals("PLAN SEPARE")) {
            if (!instancias.getSql().modificarPlanSepareFechaAnulacion(metodos.fechaConsulta(metodosGenerales.fecha()), instancias.getUsuario(), datosDocumento.getIdentificadorFactura(),
                    nota, true, (String) instancias.getSql().getNumConsecutivo("ANULA")[0])) {
                metodos.msgError(this, "Error al agregar la fecha de la anulación");
                return;
            }

            if (!txtDiasPlazo.getText().equals("") || !txtDiasPlazo.getText().equals("0")) {
                if (!instancias.getSql().modificarEstadoCxcFactura(nodo1.getFactura(), "ANULADA")) {
                    metodos.msgError(this, "Error al cambiar el estado de la Cxc");
                    return;
                }
            }
        } else if (cmbTipoDocumento.getSelectedItem().equals("PLANTILLAS")) {
            /*if (!instancias.getSql().modificarPlantillaFechaAnulacion(metodos.fechaConsulta(metodosGenerales.fecha()), instancias.getUsuario(), nodo2.getIdFactura(),
             nota, true, (String) instancias.getSql().getNumConsecutivo("ANULA")[0])) {
             metodos.msgError(this, "Error al agregar la fecha de la anulación");
             return;
             }*/
        } else {
            if (!instancias.getSql().modificarFacturaFechaAnulacion(metodos.fechaConsulta(metodosGenerales.fecha()), instancias.getUsuario(), datosDocumento.getIdentificadorFactura(),
                    nota, true, (String) instancias.getSql().getNumConsecutivo("ANULA")[0])) {
                metodos.msgError(this, "Error al agregar la fecha de la anulación");
                return;
            }

            if (!instancias.getSql().anularFacturaVerificadorFacturas(datosDocumento.getIdentificadorFactura())) {
                metodos.msgError(this, "Error al agregar la fecha de la anulación");
                return;
            }

            //Si fue una factura de cartera, anula esta.
            if (!txtDiasPlazo.getText().equals("") || !txtDiasPlazo.getText().equals("0")) {
                if (!instancias.getSql().modificarEstadoCxcFactura(datosDocumento.getIdentificadorFactura(), "ANULADA")) {
                    metodos.msgError(this, "Error al cambiar el estado de la Cxc");
                    return;
                }
            }
        }

        String baseUtilizada = enumBodegas.obtenerNombreTablaBodega(BODEGA_SELECCIONADA, instancias.getConfiguraciones().isInventarioBodegas());

        /*String tipoMovimiento = obtenerTipoMovimiento();
         List<MovimientoInventario> productos1 = generarListadoProductos(baseUtilizada);
         DatosMovimientoInventario datosMovimiento = new DatosMovimientoInventario(tipoMovimiento, productos1);
         Inventario inventario = new Inventario(datosMovimiento, baseUtilizada);

         try {
         inventario.procesarMovimiento();
         } catch (SQLException ex) {
         Logger.getLogger(vistaDocumentos.class.getName()).log(Level.SEVERE, null, ex);
         }*/
        if (cmbTipoDocumento.getSelectedItem().equals("FACTURA")) {
            for (int i = 0; i < tblProductos.getRowCount(); i++) {

                ndProducto producto = instancias.getSql().getDatosProducto(tblProductos.getValueAt(i, 14).toString(), baseUtilizada);

                String tipoProducto = null == producto.getUsuario() ? "ADMIN" : producto.getUsuario();

                if (!tipoProducto.equals("ADMIN")) {

                    String preparacion = "";
                    try {
                        preparacion = tblProductos.getValueAt(i, 10).toString();
                    } catch (Exception e) {
                    }

                    if (preparacion.equals("")) {
                        Object[][] productos = instancias.getSql().getCantidadesDiscosteo(tblProductos.getValueAt(i, 14).toString());
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
                    } else {
                        String opciones2[];
                        String opciones = preparacion.split("; ")[3];
                        if (!opciones.equals("")) {
                            opciones2 = opciones.split(", ");
                            for (int k = 0; k < opciones2.length; k++) {

                                String principal = opciones2[k].split("/")[0];
                                String codigo = opciones2[k].split("/")[1];
                                String cant = opciones2[k].split("/")[2];
                                String estado = opciones2[k].split("/")[3];

                                if (estado.equals(" true")) {

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

                                    if (!insumo.getGrupo().equals("GRP-02")) {
                                        instancias.getSql().modificarInventario("anulacion", total1, codigo, baseUtilizada);
                                        instancias.getSql().modificarInventario("inventario", inventario1, codigo, baseUtilizada);
                                        instancias.getSql().modificarInventario("fisicoInventario", fisicoInventario1, codigo, baseUtilizada);
                                    }
                                }
                            }
                        }
                    }

                    String idCosteo = "";
                    try {
                        idCosteo = tblProductos.getValueAt(i, 11).toString();
                    } catch (Exception e) {
                    }

                    if (!idCosteo.equals("")) {
                        instancias.getSql().anularDocumento(idCosteo, "bdCosteo");
                    }
                }

                double cantidad;
                double inventario;
                double fisicoInventario;
                double costeo;

                try {
                    cantidad = Double.parseDouble(producto.getAnulada().replace(",", "."));
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

                try {
                    costeo = Double.parseDouble(producto.getCosteo().replace(",", "."));
                } catch (Exception e) {
                    costeo = 0;
                }

                double cantidadTabla = Double.parseDouble(tblProductos.getValueAt(i, 13).toString().replace(",", "."));

                inventario = inventario + cantidadTabla;
                fisicoInventario = fisicoInventario + cantidadTabla;
                costeo = costeo - cantidadTabla;
                double total = cantidad + cantidadTabla;

                String total1 = String.valueOf(df.format(total)).replace(".", ",");
                String inventario1 = String.valueOf(df.format(inventario)).replace(".", ",");
                String fisicoInventario1 = String.valueOf(df.format(fisicoInventario)).replace(".", ",");
                String costeo1 = String.valueOf(df.format(costeo)).replace(".", ",");

                if (producto.getUsuario().equals("ADMIN")) {
                    instancias.getSql().modificarInventario("anulacion", total1, tblProductos.getValueAt(i, 14).toString(), baseUtilizada);
                    instancias.getSql().modificarInventario("inventario", inventario1, tblProductos.getValueAt(i, 14).toString(), baseUtilizada);
                    instancias.getSql().modificarInventario("fisicoInventario", fisicoInventario1, tblProductos.getValueAt(i, 14).toString(), baseUtilizada);
                } else {
                    instancias.getSql().modificarInventario("anulacion", total1, tblProductos.getValueAt(i, 14).toString(), baseUtilizada);
                    instancias.getSql().modificarInventario("costeo", costeo1, tblProductos.getValueAt(i, 14).toString(), baseUtilizada);
                }

                String idProd = "";
                try {
                    idProd = tblProductos.getValueAt(i, 9).toString();
                } catch (Exception e) {
                }

                DetalleTipoProducto.obtenerTipoProducto(producto.getTipoProducto());

                String tipo = "";
                if (producto.getTipoProducto() != null) {
                    if (producto.getTipoProducto().equals("IMEI")) {
                        tipo = "Imei";
                    } else if (producto.getTipoProducto().equals("Fecha/Lote")) {
                        tipo = "Fecha/Lote";
                    } else if (producto.getTipoProducto().equals("Color")) {
                        tipo = "Color";
                    } else if (producto.getTipoProducto().equals("Serial")) {
                        tipo = "Serial";
                    } else if (producto.getTipoProducto().equals("Talla")) {
                        tipo = "Talla";
                    } else if (producto.getTipoProducto().equals("ColorTalla")) {
                        tipo = "ColorTalla";
                    } else if (producto.getTipoProducto().equals("SerialColor")) {
                        tipo = "SerialColor";
                    } else {
                        tipo = "";
                    }
                }

                if (!idProd.equals("")) {
                    if (tipo.equals("Imei") || tipo.equals("Serial") || tipo.equals("SerialColor")) {
                        instancias.getSql().modificarEstadoDetalleProductos(idProd, "DISPONIBLE");
//                        instancias.getSql().anularVentaDetalladoInventario(idProd);
                    } else {
                        BigDecimal cantidadActual = new BigDecimal(instancias.getSql().getCantidadProductos(idProd).replace(",", "."));
                        BigDecimal cantidadTabla1 = new BigDecimal(tblProductos.getValueAt(i, 13).toString().replace(",", "."));
                        BigDecimal cantidadFinal = cantidadActual.add(cantidadTabla1);
                        instancias.getSql().modificarCantidadesDetalleProductos(idProd, cantidadFinal);
                    }
                }
            }
        } else if (cmbTipoDocumento.getSelectedItem().equals("PLAN SEPARE")) {
            TipoDocumento tipoMovimiento = TipoDocumento.ANULAR_PLAN_SEPARE;
            List<DetalleProducto> detallesProductos = new ArrayList<>();
            String tablaUtilizada = enumBodegas.TipoBodega.BODEGA_PRINCIPAL.getNombreTabla();
            List<MovimientoInventario> productos = generarListadoProductos(tablaUtilizada);
            String numeroAnulacion = "Anulación-" + (String) instancias.getSql().getNumConsecutivo("ANULA")[0];
            ServicioInventario servicioInventario = new ServicioInventario(productos, detallesProductos, tipoMovimiento,
                    numeroAnulacion, tablaUtilizada, instancias.getUsuario(), null);

            try {
                servicioInventario.procesarMovimiento();
            } catch (SQLException ex) {
                Logger.getLogger(VistaInventarioInicial.class.getName()).log(Level.SEVERE, null, ex);
                return;
            }
        }

        metodos.msgExito(this, "Registro anulado con éxito");
        tapPanel.setSelectedIndex(1);
        actualizarTablaDocumentos();

        if (!instancias.getSql().aumentarConsecutivo("ANULA", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("ANULA")[0]) + 1)) {
            metodos.msgError(this, "Hubo un problema al guardar en el consecutivo de la anulación");
        }

    }

    private List<MovimientoInventario> generarListadoProductos(String tablaUtilizada) {

        List<MovimientoInventario> movimientos = new ArrayList<>();

        for (int i = 0; i < tblProductos.getRowCount(); i++) {
            ndProducto producto = instancias.getSql().getDatosProducto(obtenerValorTabla(i, 10), tablaUtilizada);
            BigDecimal cantidad = Utilidades.convertirBigDecimal(tblProductos.getValueAt(i, 9).toString());
            String idDetalleProducto = obtenerValorTabla(i, 11);

            MovimientoInventario inventario = new MovimientoInventario(producto, cantidad, BigDecimal.ZERO, idDetalleProducto);
            movimientos.add(inventario);
        }

        return movimientos;
    }

    private String obtenerValorTabla(int row, int col) {
        Object value = tblProductos.getValueAt(row, col);
        return value != null ? value.toString() : "";
    }

    public String obtenerNombreImpresion() {
        String tipoDocumento = String.valueOf(cmbTipoDocumento.getSelectedItem());
        boolean esCarta = carta.isSelected();
        boolean esPos = pos.isSelected();
        int cantidadProductos = tblProductos.getRowCount();
        String regimen = instancias.getRegimen();
        boolean esMedico = instancias.getConfiguraciones().isMedico();

        if (esMedico) {
            return esCarta ? "facturaMedicaCompleta" : "facturaMedica";
        }

        switch (tipoDocumento) {
            case "FACTURA":
            case "NOTA DÉBITO":
                if (esCarta || cantidadProductos > 6) {
                    return "facturaCompleta" + regimen;
                }
                if (esPos) {
                    return "pos" + regimen;
                }
                return "factura" + regimen;

            case "PLAN SEPARE":
                if (esCarta || cantidadProductos > 6) {
                    return "separeCompleta";
                }
                if (esPos) {
                    return "separePos";
                }
                return "separe";

            case "COTIZACIÓN":
                if (esCarta || cantidadProductos > 6) {
                    return "cotizaCompleta";
                }
                if (esPos) {
                    return "cotizaPos";
                }
                return "cotiza";

            case "PEDIDOS":
                if (esCarta || cantidadProductos > 6) {
                    return "pedidoCompleta";
                }
                if (esPos) {
                    return "pedidoPos";
                }
                return "pedido";

            case "ORDEN DE SERVICIO":
                if (esCarta || cantidadProductos > 6) {
                    return "OrdenNormalCompleta";
                }
                if (esPos) {
                    return "OrdenPos";
                }
                return "Orden";

            default:
                return "";
        }
    }

    private String obtenerTipoImpresion() {
        String tipo = obtenerNombreImpresion();
        String configuracionImpresion = instancias.getConfiguraciones().getTipoImpresion();
        String regimen = instancias.getRegimen();

        if ("separePosSinIva".equals(tipo)) {
            tipo = "separePos";
        }

        switch (configuracionImpresion) {
            case "Sin-Codigo":
                tipo += "1";
                break;
            case "Imei":
                tipo += "Imei";
                break;
            case "Con-Codigo":
            default:
                break;
        }

        if (esFacturaMedica(tipo) && !regimen.isEmpty()) {
            tipo += "SinIva";
        }

        return tipo;
    }

    private boolean esFacturaMedica(String tipo) {
        return tipo.startsWith("facturaMedica");
    }

    private String obtenerImpresoraEstablecida() {
        Object valor = null;

        if (pos.isSelected()) {
            valor = datosMaestra[81];
        } else if (mediaCarta.isSelected()) {
            valor = datosMaestra[82];
        } else if (carta.isSelected()) {
            valor = datosMaestra[83];
        }

        return valor != null ? valor.toString() : "";
    }

    private void consultarInformacionDocumento() {
        Object seleccion = cmbTipoDocumento.getSelectedItem();
        if (seleccion == null) {
            return;
        }

        String tipo = seleccion.toString();
        String consecutivo = txtConsecutivo.getText();
        String nombreTitulo;

        switch (tipo) {
            case "FACTURA":
            case "NOTA DÉBITO": {
                String prefijo = tipo.equals("FACTURA") ? "FACT-" : "ND-";
                ndFactura datos = instancias.getSql().getDatosFactura(prefijo + consecutivo);
                if (datos == null) {
                    return;
                }

                nombreTitulo = "REIMPRESIÓN DE " + prefijo + consecutivo;
                datosDocumento = ModeloDatosDocumento.construirModelo(datos, big.getBigDecimal(datos.getImpoGeneral()));
                break;
            }

            case "PLAN SEPARE": {
                ndPlanSepare datos = instancias.getSql().getDatosPlanSepare("SEPARE-" + consecutivo);
                if (datos == null) {
                    return;
                }

                nombreTitulo = "REIMPRESIÓN DE SEPARE-" + consecutivo;
                datosDocumento = ModeloDatosDocumento.construirModelo(datos, BigDecimal.ZERO);
                break;
            }

            case "COTIZACIÓN": {
                ndCotizacion datos = instancias.getSql().getDatosCotizacion("COTI-" + consecutivo);
                if (datos == null) {
                    return;
                }

                nombreTitulo = "REIMPRESIÓN DE COTI-" + consecutivo;
                datosDocumento = ModeloDatosDocumento.construirModelo(datos, BigDecimal.ZERO);
                break;
            }

            case "PEDIDOS": {
                ndPedido datos = instancias.getSql().getDatosPedido("PEDIDO-" + consecutivo);
                if (datos == null) {
                    return;
                }

                nombreTitulo = "REIMPRESIÓN DE PEDIDO-" + consecutivo;
                datosDocumento = ModeloDatosDocumento.construirModelo(datos, BigDecimal.ZERO);
                break;
            }

            case "ORDEN DE SERVICIO": {
                ndOServicio1 datos = instancias.getSql().getDatosOServicio1("OSERV-" + consecutivo);
                if (datos == null) {
                    return;
                }

                nombreTitulo = "REIMPRESIÓN DE OSERV-" + consecutivo;
                datosDocumento = ModeloDatosDocumento.construirModelo(datos, BigDecimal.ZERO);
                break;
            }

            default:
                return;
        }

        instancias.getMenu().cambiarTitulo(nombreTitulo);
    }

    private void llenarProductosDocumento() {
        String tabla = determinarTablaSeleccionada();
        tblProductos.setModel(daoReimpresiones.obtenerDetallesDocumentoEnTabla(tabla, datosDocumento.getIdentificadorFactura()));

        for (int i = 0; i < tblProductos.getRowCount(); i++) {
            tblProductos.setValueAt(big.setMoneda(big.getMoneda(tblProductos.getValueAt(i, 2).toString().replace(".", ","))), i, 2);
            tblProductos.setValueAt(big.setMoneda(big.getMoneda(tblProductos.getValueAt(i, 4).toString().replace(".", ","))), i, 4);
            tblProductos.setValueAt(big.setMoneda(big.getMoneda(tblProductos.getValueAt(i, 5).toString().replace(".", ","))), i, 5);
            tblProductos.setValueAt(big.setMoneda(big.getMoneda(tblProductos.getValueAt(i, 7).toString().replace(".", ","))), i, 7);
            tblProductos.setValueAt(big.setMoneda(big.getMoneda(tblProductos.getValueAt(i, 8).toString().replace(".", ","))), i, 8);
            tblProductos.setValueAt(big.setMoneda(big.getMoneda(tblProductos.getValueAt(i, 12).toString().replace(".", ","))), i, 12);
        }

        if (instancias.getConfiguraciones().getTipoImpresion().equals("Sin-Codigo")) {
            tblProductos.getColumnModel().getColumn(0).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(0).setMaxWidth(0);
        }

        if (!instancias.getRegimen().equals("")) {
            tblProductos.getColumnModel().getColumn(6).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(6).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(6).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(7).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(7).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(7).setMaxWidth(0);
        }

        if (!instancias.getRegimen().equals("")) {
            tblProductos.getColumnModel().getColumn(6).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(6).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(6).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(7).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(7).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(7).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(8).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(8).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(8).setMaxWidth(0);
        } else {
            tblProductos.getColumnModel().getColumn(6).setMinWidth(20);
            tblProductos.getColumnModel().getColumn(6).setPreferredWidth(50);
            tblProductos.getColumnModel().getColumn(6).setMaxWidth(70);
            tblProductos.getColumnModel().getColumn(7).setMinWidth(80);
            tblProductos.getColumnModel().getColumn(7).setPreferredWidth(100);
            tblProductos.getColumnModel().getColumn(7).setMaxWidth(140);
            tblProductos.getColumnModel().getColumn(8).setMinWidth(80);
            tblProductos.getColumnModel().getColumn(8).setPreferredWidth(100);
            tblProductos.getColumnModel().getColumn(8).setMaxWidth(140);
        }

        tblProductos.getColumnModel().getColumn(2).setMinWidth(80);
        tblProductos.getColumnModel().getColumn(2).setPreferredWidth(100);
        tblProductos.getColumnModel().getColumn(2).setMaxWidth(140);
        tblProductos.getColumnModel().getColumn(3).setMinWidth(20);
        tblProductos.getColumnModel().getColumn(3).setPreferredWidth(60);
        tblProductos.getColumnModel().getColumn(3).setMaxWidth(80);
        tblProductos.getColumnModel().getColumn(4).setMinWidth(80);
        tblProductos.getColumnModel().getColumn(4).setPreferredWidth(115);
        tblProductos.getColumnModel().getColumn(4).setMaxWidth(140);
        tblProductos.getColumnModel().getColumn(5).setMinWidth(80);
        tblProductos.getColumnModel().getColumn(5).setPreferredWidth(115);
        tblProductos.getColumnModel().getColumn(5).setMaxWidth(140);
        tblProductos.getColumnModel().getColumn(9).setMinWidth(0);
        tblProductos.getColumnModel().getColumn(9).setPreferredWidth(0);
        tblProductos.getColumnModel().getColumn(9).setMaxWidth(0);
        tblProductos.getColumnModel().getColumn(10).setMinWidth(0);
        tblProductos.getColumnModel().getColumn(10).setPreferredWidth(0);
        tblProductos.getColumnModel().getColumn(10).setMaxWidth(0);
        tblProductos.getColumnModel().getColumn(11).setMinWidth(0);
        tblProductos.getColumnModel().getColumn(11).setPreferredWidth(0);
        tblProductos.getColumnModel().getColumn(11).setMaxWidth(0);
        tblProductos.getColumnModel().getColumn(12).setMinWidth(80);
        tblProductos.getColumnModel().getColumn(12).setPreferredWidth(115);
        tblProductos.getColumnModel().getColumn(12).setMaxWidth(140);
        tblProductos.getColumnModel().getColumn(13).setMinWidth(0);
        tblProductos.getColumnModel().getColumn(13).setPreferredWidth(0);
        tblProductos.getColumnModel().getColumn(13).setMaxWidth(0);
        tblProductos.getColumnModel().getColumn(14).setMinWidth(0);
        tblProductos.getColumnModel().getColumn(14).setPreferredWidth(0);
        tblProductos.getColumnModel().getColumn(14).setMaxWidth(0);
    }

    private void llenarDatosDocumento() {
        if (datosDocumento == null) {
            return;
        }

        txtVendedor.setText(datosDocumento.getVendedor());
        txtFechaFactura.setText(datosDocumento.getFechaFactura());
        txtVencimiento.setText(datosDocumento.getFechaVencimiento());
        txtSubTotal.setText(big.setMoneda(datosDocumento.getSubtotalGeneral()));
        txtTotalDescuentos.setText(big.setMoneda(datosDocumento.getDescuentoGeneral()));
        txtTotalIva.setText(big.setMoneda(datosDocumento.getIvaGeneral()));
        txtTotalImpo.setText(big.setMoneda(datosDocumento.getImpoconsumoGeneral()));
        txtTotal.setText(big.setMoneda(datosDocumento.getTotalGeneral()));
        lblDocumentoAnulado.setVisible(datosDocumento.isEsAnulado());
        btnAnular.setVisible(!datosDocumento.isEsAnulado());

        String bodegaDocumento = datosDocumento.getBodega();
        if (bodegaDocumento == null && bodegaDocumento.isEmpty()) {
            BODEGA_SELECCIONADA = enumBodegas.TipoBodega.BODEGA_PRINCIPAL.getValue();
        } else {
            BODEGA_SELECCIONADA = enumBodegas.obtenerNombreTablaBodega(bodegaDocumento, instancias.getConfiguraciones().isInventarioBodegas());
        }

        String obs = datosDocumento.getObservaciones();
        if (obs != null && !"null".equalsIgnoreCase(obs.trim())) {
            txtObservaciones.setText(obs);
        } else {
            txtObservaciones.setText("");
        }
    }

    private void llenarDatosContacto() {
        ModeloContacto datosContacto = instancias.getSql().getDatosTercero(datosDocumento.getIdentificadorCliente());

        txtNit.setText(datosContacto.getId());
        CORREO_ELECTRONICO = datosContacto.getEmail() == null ? "" : datosContacto.getEmail();
        NUMERO_DOCUMENTO_SELECCIONADO = txtConsecutivo.getText();

        if (datosContacto.getNombre() != null) {
            txtNombre.setText(datosContacto.getNombre());
            txtDireccion.setText(datosContacto.getDireccion());
            txtTelefono.setText(datosContacto.getTelefono());
        }

        try {
            ndCxc nodoCxc = instancias.getSql().getDatosCxc(datosDocumento.getIdentificadorFactura());
            txtDiasPlazo.setText("" + nodoCxc.getPlazo());
        } catch (Exception ex) {
            txtDiasPlazo.setText("0");
        }
    }

    private void verInformacionDetalladaDocumento() {

        consultarInformacionDocumento();
        llenarProductosDocumento();
        llenarDatosDocumento();
        llenarDatosContacto();

        tapPanel.setSelectedIndex(0);
        infoEmpresa = metodosGenerales.convertToMultiline(instancias.getInformacionEmpresaReimpresion() + "\n" + datosDocumento.getResolucion());
    }

    private void limpiarDatosTabla() {
        modeloTablaDocumentos = (DefaultTableModel) tblDocumentos.getModel();
        int j = tblDocumentos.getRowCount();
        for (int i = 0; i < j; i++) {
            modeloTablaDocumentos.removeRow(0);
        }
    }

    private void cargarDocumentosTabla(List<ModeloTablaDocumentos> documentos) {
        for (ModeloTablaDocumentos doc : documentos) {
            modeloTablaDocumentos.addRow(new Object[]{
                doc.getIdFactura(),
                doc.getFactura(),
                Fechas.formatearFecha1((String) doc.getFechaFactura()),
                doc.getIdentificadorCliente(),
                doc.getNombreCliente(),
                doc.getVendedor(),
                big.setMoneda(doc.getTotalGeneral()),
                doc.getTerminal(),
                doc.getTurno(),
                doc.getTipoFactura()
            });
        }
    }

    private void asginarFiltrosTablaDocumentos() {
        tblDocumentos.setAutoCreateRowSorter(true);
        modeloOrdenado = new TableRowSorter<>(modeloTablaDocumentos);
        tblDocumentos.setRowSorter(modeloOrdenado);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnular;
    private javax.swing.JButton btnBuscTerceros1;
    private javax.swing.JButton btnBuscTerceros2;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JRadioButton carta;
    private javax.swing.JCheckBox chkSoloAnuladas;
    private javax.swing.JComboBox cmbEstadoDocumento;
    private javax.swing.JComboBox cmbTipoDocumento;
    private datechooser.beans.DateChooserCombo dtFinal;
    private datechooser.beans.DateChooserCombo dtInicio;
    private javax.swing.JLabel etiqTotal;
    private javax.swing.JCheckBox filtrar;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
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
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lbDireccion;
    private javax.swing.JLabel lbNit;
    private javax.swing.JLabel lbNit10;
    private javax.swing.JLabel lbNit2;
    private javax.swing.JLabel lbNit3;
    private javax.swing.JLabel lbNit4;
    private javax.swing.JLabel lbNit6;
    private javax.swing.JLabel lbNit7;
    private javax.swing.JLabel lbNit8;
    private javax.swing.JLabel lbNit9;
    private javax.swing.JLabel lbNombre;
    private javax.swing.JLabel lbSubtotal;
    private javax.swing.JLabel lbSubtotal1;
    private javax.swing.JLabel lbSubtotal2;
    private javax.swing.JLabel lbTelefono;
    private javax.swing.JLabel lbTotalDescuento;
    private javax.swing.JLabel lbVendedor;
    private javax.swing.JLabel lblDocumentoAnulado;
    private javax.swing.JRadioButton mediaCarta;
    private javax.swing.JPanel pnlFormulario1;
    private javax.swing.JRadioButton pos;
    private javax.swing.JTabbedPane tapPanel;
    private javax.swing.JTable tblDocumentos;
    private javax.swing.JTable tblProductos;
    private javax.swing.JTextField txtConseManual;
    private javax.swing.JTextField txtConsecutivo;
    private javax.swing.JTextField txtDiasPlazo;
    private javax.swing.JTextField txtDiasPlazo1;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JLabel txtFechaFactura;
    private javax.swing.JTextField txtIdentificadorCliente;
    private javax.swing.JLabel txtIva;
    private javax.swing.JLabel txtIva1;
    private javax.swing.JTextField txtNit;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNombreCliente;
    private javax.swing.JTextArea txtObservaciones;
    private javax.swing.JTextField txtSubTotal;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JTextField txtTotalDescuentos;
    private javax.swing.JTextField txtTotalImpo;
    private javax.swing.JTextField txtTotalIva;
    private javax.swing.JLabel txtVencimiento;
    private javax.swing.JTextField txtVendedor;
    // End of variables declaration//GEN-END:variables
}
