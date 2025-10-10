package formularios.Cartera;

import clases.Cartera.ndCxc;
import clases.Instancias;
import clases.Ventas.ndGarantia;
import clases.big;
import clases.cambiarColorTabla;
import clases.convertirNumeroALetras;
import clases.credito.ndCuota;
import clases.credito.ndPrestamo;
import clases.metodosGenerales;
import Modelo.Terceros.ModeloContacto;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class infAbonos extends javax.swing.JInternalFrame {

    DefaultTableModel modeloPro;
    DefaultTableModel modelo;
    DefaultTableModel modeloPrestamo;
    metodosGenerales metodos = new metodosGenerales();
    Instancias instancias;
    TableRowSorter modeloOrdenado;
    ndGarantia nodo1;
    Object datos[];
    String tipo, simbolo = "";
    Boolean credito = false, saltarPasos = false;
    convertirNumeroALetras convertirNumeroALetras = new convertirNumeroALetras();

    //Barra de titulo
    private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension dimBarra = null;

    private String factura;
    BigDecimal NC = null;

    public infAbonos() {

        initComponents();
        tblRegistros.setDefaultRenderer(Object.class, new cambiarColorTabla(0, 0));
        tblCuotas.setDefaultRenderer(Object.class, new cambiarColorTabla(9, 0));
        tblCartera.setDefaultRenderer(Object.class, new cambiarColorTabla(10, 0));

        instancias = Instancias.getInstancias();

        this.simbolo = instancias.getSimbolo();

        consultarMaestros();
//        cargarTabla();

        if ((Boolean) datos[57]) {
            txtConseManual.setVisible(true);
            lbNit7.setVisible(true);
        } else {
            txtConseManual.setVisible(false);
            lbNit7.setVisible(false);
        }

        if (!instancias.getConfiguraciones().isParqueadero()) {
            txtPlaca.setVisible(false);
            lbPlaca.setVisible(false);
        }

        txtMes.setVisible(false);
        lbMes.setVisible(false);

        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
        dimBarra = Barra.getPreferredSize();
        Barra.setSize(0, 0);
        Barra.setPreferredSize(new Dimension(0, 0));
        setBorder(null);
        repaint();

//        tblRegistros.setAutoCreateRowSorter(true);
//
//        modeloOrdenado = new TableRowSorter<>(modelo);
//        tblRegistros.setRowSorter(modeloOrdenado);
        btnActualizar.setText("VER DATOS");

        jTabbedPane1.setSelectedIndex(1);
        jTabbedPane1.setEnabledAt(0, false);
        jTabbedPane1.setEnabledAt(1, false);
        jTabbedPane1.setEnabledAt(2, false);

        actualizarConsecutivo();

        modeloPrestamo = (DefaultTableModel) tblCuotas.getModel();

        pnlInvisible.setVisible(false);
        btnFacturar.setVisible(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoAbono = new javax.swing.ButtonGroup();
        grupoMedioPagoCreditos = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        scrFormulario = new javax.swing.JScrollPane();
        pnlFormulario = new javax.swing.JPanel();
        pnlDatos = new javax.swing.JPanel();
        lbNit = new javax.swing.JLabel();
        txtNit = new javax.swing.JTextField();
        lbNombre = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        lbValor = new javax.swing.JLabel();
        txtValor = new javax.swing.JTextField();
        pnlAbonos = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel5 = new javax.swing.JPanel();
        btnBuscTerceros1 = new javax.swing.JLabel();
        btnVolver1 = new javax.swing.JLabel();
        txtPendiente = new javax.swing.JTextField();
        lbPendiente = new javax.swing.JLabel();
        txtAbonado = new javax.swing.JTextField();
        lbAbonado = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        rdAbonoFactura = new javax.swing.JRadioButton();
        rdAbonoGeneral = new javax.swing.JRadioButton();
        jPanel9 = new javax.swing.JPanel();
        lbEfectivo = new javax.swing.JLabel();
        txtEfectivo = new javax.swing.JTextField();
        lbEfectivo1 = new javax.swing.JLabel();
        txtIva = new javax.swing.JTextField();
        txtIca = new javax.swing.JTextField();
        lbCheque1 = new javax.swing.JLabel();
        txtCheque = new javax.swing.JTextField();
        lbCheque = new javax.swing.JLabel();
        lbTargeta = new javax.swing.JLabel();
        txtTargeta = new javax.swing.JTextField();
        lbTargeta1 = new javax.swing.JLabel();
        txtFuente = new javax.swing.JTextField();
        txtOtro = new javax.swing.JTextField();
        lbNC1 = new javax.swing.JLabel();
        txtNC = new javax.swing.JTextField();
        lbNC = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lbNC2 = new javax.swing.JLabel();
        txtDescuentos = new javax.swing.JTextField();
        lbTotalAbonos = new javax.swing.JLabel();
        txtTotalAbonos = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        lbComprobante = new javax.swing.JLabel();
        txtComprobante = new javax.swing.JTextField();
        pnlSaldo = new javax.swing.JPanel();
        txtSaldo = new javax.swing.JTextField();
        lbNit24 = new javax.swing.JLabel();
        lbNit25 = new javax.swing.JLabel();
        txtSaldoTotal = new javax.swing.JTextField();
        lbNit26 = new javax.swing.JLabel();
        txtSaldoCuentas = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblCartera = new javax.swing.JTable();
        jPanel11 = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        lbAbono = new javax.swing.JLabel();
        txtNoAbono = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblRegistros = new javax.swing.JTable();
        btnActualizar = new javax.swing.JButton();
        btnBuscar1 = new javax.swing.JButton();
        btnReImprimir = new javax.swing.JButton();
        btnAnular = new javax.swing.JButton();
        lbNit6 = new javax.swing.JLabel();
        cmbTipo = new javax.swing.JComboBox();
        jPanel1 = new javax.swing.JPanel();
        lbNit4 = new javax.swing.JLabel();
        txtNombreFiltro = new javax.swing.JTextField();
        lbVendedor = new javax.swing.JLabel();
        txtVendedorFiltro = new javax.swing.JTextField();
        lbNit3 = new javax.swing.JLabel();
        txtDocumentoFiltro = new javax.swing.JTextField();
        lbNit2 = new javax.swing.JLabel();
        txtConsecutivo = new javax.swing.JTextField();
        lbNit5 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        lbNit7 = new javax.swing.JLabel();
        txtConseManual = new javax.swing.JTextField();
        lbPlaca = new javax.swing.JLabel();
        txtPlaca = new javax.swing.JTextField();
        lbMes = new javax.swing.JLabel();
        txtMes = new javax.swing.JTextField();
        btnFacturar = new javax.swing.JButton();
        txtTotalizado = new javax.swing.JLabel();
        pnlFormulario1 = new javax.swing.JPanel();
        lbNit11 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        txtCelular = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        lbTelefono = new javax.swing.JLabel();
        lbCelular = new javax.swing.JLabel();
        lbRazon = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        lbDireccion = new javax.swing.JLabel();
        lbNit1 = new javax.swing.JLabel();
        txtNombre1 = new javax.swing.JTextField();
        txtId = new javax.swing.JTextField();
        txtTipo = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        lbCelular1 = new javax.swing.JLabel();
        lbRazon1 = new javax.swing.JLabel();
        txtValorAprovado = new javax.swing.JTextField();
        lbDireccion2 = new javax.swing.JLabel();
        txtObservaciones = new javax.swing.JTextField();
        lbTelefono3 = new javax.swing.JLabel();
        txtCuotas = new javax.swing.JTextField();
        lbDireccion3 = new javax.swing.JLabel();
        txtInteres = new javax.swing.JTextField();
        txtFechaDesenvolso = new javax.swing.JTextField();
        lbTelefono5 = new javax.swing.JLabel();
        txtCuotas1 = new javax.swing.JTextField();
        lbNit12 = new javax.swing.JLabel();
        txtContrato = new javax.swing.JTextField();
        lbRazon2 = new javax.swing.JLabel();
        txtVendedor = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCuotas = new javax.swing.JTable();
        lbDireccion4 = new javax.swing.JLabel();
        txtTotalIntereses = new javax.swing.JTextField();
        lbTelefono4 = new javax.swing.JLabel();
        btnBuscTerceros2 = new javax.swing.JButton();
        btnBuscTerceros = new javax.swing.JButton();
        btnGuardar2 = new javax.swing.JButton();
        txtAbonar = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        btnGuardar1 = new javax.swing.JButton();
        pnlInvisible = new javax.swing.JPanel();
        txtFactura = new javax.swing.JTextField();
        cmbListas = new javax.swing.JComboBox();
        txtBodega = new javax.swing.JTextField();

        setResizable(true);
        setTitle("Abono");

        scrFormulario.setBorder(null);

        pnlFormulario.setBackground(new java.awt.Color(255, 255, 255));

        pnlDatos.setBackground(new java.awt.Color(255, 255, 255));
        pnlDatos.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos factura", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 0, 14))); // NOI18N

        lbNit.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit.setText("CC/NIT:");

        txtNit.setEditable(false);
        txtNit.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtNit.setName("CC/NIT"); // NOI18N
        txtNit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNitKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNitKeyTyped(evt);
            }
        });

        lbNombre.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbNombre.setText("Nombre:");

        txtNombre.setEditable(false);
        txtNombre.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtNombre.setName("CC/NIT"); // NOI18N
        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });

        lbValor.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbValor.setText("Valor factura:");

        txtValor.setEditable(false);
        txtValor.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtValor.setName("CC/NIT"); // NOI18N
        txtValor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtValorKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtValorKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout pnlDatosLayout = new javax.swing.GroupLayout(pnlDatos);
        pnlDatos.setLayout(pnlDatosLayout);
        pnlDatosLayout.setHorizontalGroup(
            pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbValor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbNit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtValor, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
                    .addComponent(txtNombre)
                    .addComponent(txtNit))
                .addContainerGap())
        );
        pnlDatosLayout.setVerticalGroup(
            pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatosLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNit)
                    .addComponent(txtNit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNombre)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbValor)
                    .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlAbonos.setBackground(new java.awt.Color(255, 255, 255));
        pnlAbonos.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Forma de pago", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 0, 14))); // NOI18N

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        btnBuscTerceros1.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        btnBuscTerceros1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnBuscTerceros1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/anterior.png"))); // NOI18N
        btnBuscTerceros1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBuscTerceros1MouseClicked(evt);
            }
        });

        btnVolver1.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnVolver1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnVolver1.setText("VOLVER");
        btnVolver1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnVolver1MouseClicked(evt);
            }
        });

        txtPendiente.setEditable(false);
        txtPendiente.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtPendiente.setName("CC/NIT"); // NOI18N
        txtPendiente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPendienteKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPendienteKeyTyped(evt);
            }
        });

        lbPendiente.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbPendiente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbPendiente.setText("Pendiente");

        txtAbonado.setEditable(false);
        txtAbonado.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtAbonado.setName("CC/NIT"); // NOI18N
        txtAbonado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAbonadoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAbonadoKeyTyped(evt);
            }
        });

        lbAbonado.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbAbonado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbAbonado.setText("Abonado");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbAbonado, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAbonado, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtPendiente)
                    .addComponent(lbPendiente, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBuscTerceros1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVolver1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbAbonado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbPendiente))
                        .addGap(2, 2, 2)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPendiente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAbonado, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(btnBuscTerceros1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(btnVolver1)))
                .addGap(5, 5, 5))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        rdAbonoFactura.setBackground(new java.awt.Color(255, 255, 255));
        grupoAbono.add(rdAbonoFactura);
        rdAbonoFactura.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        rdAbonoFactura.setSelected(true);
        rdAbonoFactura.setText("Abono Factura");
        rdAbonoFactura.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdAbonoFacturaItemStateChanged(evt);
            }
        });
        rdAbonoFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdAbonoFacturaActionPerformed(evt);
            }
        });

        rdAbonoGeneral.setBackground(new java.awt.Color(255, 255, 255));
        grupoAbono.add(rdAbonoGeneral);
        rdAbonoGeneral.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        rdAbonoGeneral.setText("Abono General");
        rdAbonoGeneral.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdAbonoGeneralItemStateChanged(evt);
            }
        });
        rdAbonoGeneral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdAbonoGeneralActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rdAbonoFactura)
                .addGap(12, 12, 12)
                .addComponent(rdAbonoGeneral)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdAbonoFactura)
                    .addComponent(rdAbonoGeneral))
                .addGap(5, 5, 5))
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        lbEfectivo.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbEfectivo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbEfectivo.setText("Efectivo");
        lbEfectivo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lbEfectivoKeyReleased(evt);
            }
        });

        txtEfectivo.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtEfectivo.setName("Efectivo"); // NOI18N
        txtEfectivo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtEfectivoFocusGained(evt);
            }
        });
        txtEfectivo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEfectivoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEfectivoKeyTyped(evt);
            }
        });

        lbEfectivo1.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbEfectivo1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbEfectivo1.setText("Rte.Iva");

        txtIva.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtIva.setName("Efectivo"); // NOI18N
        txtIva.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtIvaFocusGained(evt);
            }
        });
        txtIva.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIvaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIvaKeyTyped(evt);
            }
        });

        txtIca.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtIca.setName("Cheque"); // NOI18N
        txtIca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIcaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIcaKeyTyped(evt);
            }
        });

        lbCheque1.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbCheque1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCheque1.setText("Rte.Ica");

        txtCheque.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtCheque.setName("Cheque"); // NOI18N
        txtCheque.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtChequeKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtChequeKeyTyped(evt);
            }
        });

        lbCheque.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbCheque.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCheque.setText("Cheque");

        lbTargeta.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTargeta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTargeta.setText("Tarjeta");

        txtTargeta.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtTargeta.setName("Tarjeta"); // NOI18N
        txtTargeta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTargetaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTargetaKeyTyped(evt);
            }
        });

        lbTargeta1.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTargeta1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTargeta1.setText("Rte.Fuente");

        txtFuente.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtFuente.setName("Tarjeta"); // NOI18N
        txtFuente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFuenteKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFuenteKeyTyped(evt);
            }
        });

        txtOtro.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtOtro.setName("Nota credito"); // NOI18N
        txtOtro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtOtroKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtOtroKeyTyped(evt);
            }
        });

        lbNC1.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbNC1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNC1.setText("Otros");

        txtNC.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtNC.setName("Nota credito"); // NOI18N
        txtNC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNCKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNCKeyTyped(evt);
            }
        });

        lbNC.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbNC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNC.setText("Nota credito");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        lbNC2.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbNC2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNC2.setText("Descuentos");

        txtDescuentos.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtDescuentos.setName("Nota credito"); // NOI18N
        txtDescuentos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDescuentosKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDescuentosKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtDescuentos)
                    .addComponent(lbNC2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(lbNC2)
                .addGap(0, 0, 0)
                .addComponent(txtDescuentos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        lbTotalAbonos.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        lbTotalAbonos.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbTotalAbonos.setText("TOTAL");

        txtTotalAbonos.setEditable(false);
        txtTotalAbonos.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        txtTotalAbonos.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTotalAbonos.setName("CC/NIT"); // NOI18N
        txtTotalAbonos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTotalAbonosKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTotalAbonosKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtEfectivo)
                            .addComponent(lbEfectivo, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCheque)
                            .addComponent(lbCheque, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTargeta)
                            .addComponent(lbTargeta, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNC)
                            .addComponent(lbNC, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtIva, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbEfectivo1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtIca)
                            .addComponent(lbCheque1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbTotalAbonos, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtFuente)
                                    .addComponent(lbTargeta1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtOtro)
                                    .addComponent(lbNC1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtTotalAbonos))))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel9Layout.createSequentialGroup()
                            .addComponent(lbEfectivo)
                            .addGap(0, 0, 0)
                            .addComponent(txtEfectivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel9Layout.createSequentialGroup()
                            .addComponent(lbCheque)
                            .addGap(0, 0, 0)
                            .addComponent(txtCheque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel9Layout.createSequentialGroup()
                            .addComponent(lbTargeta)
                            .addGap(0, 0, 0)
                            .addComponent(txtTargeta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(lbNC)
                        .addGap(0, 0, 0)
                        .addComponent(txtNC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(5, 5, 5)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel9Layout.createSequentialGroup()
                            .addComponent(lbEfectivo1)
                            .addGap(0, 0, 0)
                            .addComponent(txtIva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel9Layout.createSequentialGroup()
                            .addComponent(lbCheque1)
                            .addGap(0, 0, 0)
                            .addComponent(txtIca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel9Layout.createSequentialGroup()
                            .addComponent(lbTargeta1)
                            .addGap(0, 0, 0)
                            .addComponent(txtFuente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(lbNC1)
                        .addGap(0, 0, 0)
                        .addComponent(txtOtro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lbTotalAbonos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtTotalAbonos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        lbComprobante.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbComprobante.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbComprobante.setText("Num. Comprobante:");

        txtComprobante.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtComprobante.setName("Comprobante"); // NOI18N
        txtComprobante.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtComprobanteKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtComprobanteKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbComprobante)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(246, 246, 246))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtComprobante, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(lbComprobante, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout pnlAbonosLayout = new javax.swing.GroupLayout(pnlAbonos);
        pnlAbonos.setLayout(pnlAbonosLayout);
        pnlAbonosLayout.setHorizontalGroup(
            pnlAbonosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAbonosLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(pnlAbonosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSeparator1)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );
        pnlAbonosLayout.setVerticalGroup(
            pnlAbonosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAbonosLayout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pnlSaldo.setBackground(new java.awt.Color(255, 255, 255));

        txtSaldo.setEditable(false);
        txtSaldo.setFont(new java.awt.Font("Century Gothic", 0, 36)); // NOI18N
        txtSaldo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSaldo.setName("CC/NIT"); // NOI18N
        txtSaldo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSaldoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSaldoKeyTyped(evt);
            }
        });

        lbNit24.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lbNit24.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNit24.setText("Saldo Final");

        lbNit25.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lbNit25.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNit25.setText("Saldo");

        txtSaldoTotal.setEditable(false);
        txtSaldoTotal.setFont(new java.awt.Font("Century Gothic", 0, 36)); // NOI18N
        txtSaldoTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSaldoTotal.setName("CC/NIT"); // NOI18N
        txtSaldoTotal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSaldoTotalKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSaldoTotalKeyTyped(evt);
            }
        });

        lbNit26.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lbNit26.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNit26.setText("Saldo Cuentas Seleccionadas");

        txtSaldoCuentas.setEditable(false);
        txtSaldoCuentas.setFont(new java.awt.Font("Century Gothic", 0, 36)); // NOI18N
        txtSaldoCuentas.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSaldoCuentas.setText("0");
        txtSaldoCuentas.setName("CC/NIT"); // NOI18N
        txtSaldoCuentas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSaldoCuentasKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSaldoCuentasKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout pnlSaldoLayout = new javax.swing.GroupLayout(pnlSaldo);
        pnlSaldo.setLayout(pnlSaldoLayout);
        pnlSaldoLayout.setHorizontalGroup(
            pnlSaldoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSaldoLayout.createSequentialGroup()
                .addGroup(pnlSaldoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSaldo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNit24, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSaldoTotal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNit25, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSaldoCuentas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNit26, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 38, Short.MAX_VALUE))
        );
        pnlSaldoLayout.setVerticalGroup(
            pnlSaldoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSaldoLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(lbNit25)
                .addGap(0, 0, 0)
                .addComponent(txtSaldoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(lbNit24)
                .addGap(0, 0, 0)
                .addComponent(txtSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(lbNit26)
                .addGap(0, 0, 0)
                .addComponent(txtSaldoCuentas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        tblCartera.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        tblCartera.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblCartera.setRowHeight(24);
        tblCartera.getTableHeader().setReorderingAllowed(false);
        tblCartera.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCarteraMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tblCarteraMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblCarteraMousePressed(evt);
            }
        });
        jScrollPane3.setViewportView(tblCartera);

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

        btnGuardar.setBackground(new java.awt.Color(46, 204, 113));
        btnGuardar.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
        btnGuardar.setText("GUARDAR  ");
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnGuardar.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(btnGuardar)
                .addGap(3, 3, 3))
        );

        lbAbono.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbAbono.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbAbono.setText("Abono No.");

        txtNoAbono.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        txtNoAbono.setForeground(new java.awt.Color(255, 0, 0));
        txtNoAbono.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtNoAbono.setText("3");
        txtNoAbono.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout pnlFormularioLayout = new javax.swing.GroupLayout(pnlFormulario);
        pnlFormulario.setLayout(pnlFormularioLayout);
        pnlFormularioLayout.setHorizontalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(pnlDatos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlAbonos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNoAbono, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbAbono, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        pnlFormularioLayout.setVerticalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlFormularioLayout.createSequentialGroup()
                        .addComponent(pnlDatos, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlFormularioLayout.createSequentialGroup()
                        .addComponent(lbAbono, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(txtNoAbono, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnlAbonos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE))
        );

        scrFormulario.setViewportView(pnlFormulario);

        jTabbedPane1.addTab("VISTA PREVIA", scrFormulario);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        tblRegistros.setFont(new java.awt.Font("Century Gothic", 0, 17)); // NOI18N
        tblRegistros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NUMERO", "FECHA", "NIT CLIENTE", "VENDEDOR", "ESTADO", "ID", "TERMINAL", "CUOTAS", "Saldo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblRegistros.setRowHeight(24);
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
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblRegistrosMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblRegistros);
        if (tblRegistros.getColumnModel().getColumnCount() > 0) {
            tblRegistros.getColumnModel().getColumn(0).setMinWidth(100);
            tblRegistros.getColumnModel().getColumn(0).setPreferredWidth(100);
            tblRegistros.getColumnModel().getColumn(0).setMaxWidth(100);
            tblRegistros.getColumnModel().getColumn(5).setMinWidth(0);
            tblRegistros.getColumnModel().getColumn(5).setPreferredWidth(0);
            tblRegistros.getColumnModel().getColumn(5).setMaxWidth(0);
            tblRegistros.getColumnModel().getColumn(7).setMinWidth(0);
            tblRegistros.getColumnModel().getColumn(7).setPreferredWidth(0);
            tblRegistros.getColumnModel().getColumn(7).setMaxWidth(0);
        }

        btnActualizar.setBackground(new java.awt.Color(93, 173, 226));
        btnActualizar.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/actualizar.png"))); // NOI18N
        btnActualizar.setText("ACTUALIZAR");
        btnActualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnActualizar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnActualizar.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnBuscar1.setBackground(new java.awt.Color(204, 204, 204));
        btnBuscar1.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        btnBuscar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar.png"))); // NOI18N
        btnBuscar1.setText("VER      ");
        btnBuscar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscar1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnBuscar1.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnBuscar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscar1ActionPerformed(evt);
            }
        });

        btnReImprimir.setBackground(new java.awt.Color(247, 220, 111));
        btnReImprimir.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        btnReImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/imprimir.png"))); // NOI18N
        btnReImprimir.setText("REIMPRIMIR ABONOS");
        btnReImprimir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReImprimir.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnReImprimir.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnReImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReImprimirActionPerformed(evt);
            }
        });

        btnAnular.setBackground(new java.awt.Color(241, 148, 138));
        btnAnular.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        btnAnular.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/borrar2.png"))); // NOI18N
        btnAnular.setText("ANULAR   ");
        btnAnular.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAnular.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnAnular.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnAnular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnularActionPerformed(evt);
            }
        });

        lbNit6.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit6.setText("TIPO:");

        cmbTipo.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        cmbTipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "CARTERA", "PLAN SEPARE", "DOMICILIOS", "NOTA DEBITO" }));
        cmbTipo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbTipoItemStateChanged(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        lbNit4.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit4.setText("CLIENTE:");

        txtNombreFiltro.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtNombreFiltro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreFiltroKeyReleased(evt);
            }
        });

        lbVendedor.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbVendedor.setText("VENDEDOR:");

        txtVendedorFiltro.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtVendedorFiltro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtVendedorFiltroKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtVendedorFiltroKeyTyped(evt);
            }
        });

        lbNit3.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit3.setText("NIT CLIENTE:");

        txtDocumentoFiltro.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtDocumentoFiltro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDocumentoFiltroKeyReleased(evt);
            }
        });

        lbNit2.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit2.setText("NUM-FACT:");

        txtConsecutivo.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtConsecutivo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtConsecutivoKeyReleased(evt);
            }
        });

        lbNit5.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit5.setText("ESTADO:");

        jComboBox1.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TODAS", "VENCIDA", "ALERTA", "OK" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        lbNit7.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit7.setText("OTRO CONSE");

        txtConseManual.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtConseManual.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtConseManualKeyReleased(evt);
            }
        });

        lbPlaca.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbPlaca.setText("PLACA:");

        txtPlaca.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtPlaca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPlacaKeyReleased(evt);
            }
        });

        lbMes.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbMes.setText("MES:");

        txtMes.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtMes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMesKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbVendedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbNit4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtVendedorFiltro, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                    .addComponent(txtNombreFiltro))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbNit3)
                    .addComponent(lbNit2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtDocumentoFiltro, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                    .addComponent(txtConsecutivo))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbNit7)
                    .addComponent(lbNit5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtConseManual)
                    .addComponent(jComboBox1, 0, 117, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbPlaca, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbMes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMes, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lbMes)
                                .addComponent(txtMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lbNit5)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lbNit3)
                                .addComponent(txtDocumentoFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lbPlaca)
                                .addComponent(txtPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lbNit7)
                                .addComponent(txtConseManual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lbNit2)
                                .addComponent(txtConsecutivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbNit4)
                            .addComponent(txtNombreFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbVendedor)
                            .addComponent(txtVendedorFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnFacturar.setBackground(new java.awt.Color(46, 204, 113));
        btnFacturar.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        btnFacturar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
        btnFacturar.setText("FACTURAR");
        btnFacturar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnFacturar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnFacturar.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnFacturar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFacturarActionPerformed(evt);
            }
        });

        txtTotalizado.setFont(new java.awt.Font("Century Gothic", 0, 22)); // NOI18N
        txtTotalizado.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtTotalizado.setText("TOTALIZADO DOMICILIOS: 0");
        txtTotalizado.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(btnReImprimir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 201, Short.MAX_VALUE)
                        .addComponent(btnActualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAnular, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnFacturar, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lbNit6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtTotalizado, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addGap(19, 19, 19))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbNit6)
                            .addComponent(cmbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(txtTotalizado, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAnular, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnReImprimir)
                        .addComponent(btnActualizar))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnBuscar1)
                        .addComponent(btnFacturar)))
                .addGap(15, 15, 15))
        );

        jTabbedPane1.addTab("LISTA", jPanel2);

        pnlFormulario1.setBackground(new java.awt.Color(255, 255, 255));

        lbNit11.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        lbNit11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit11.setText("ABONO A CREDITO");

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos personales", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 0, 12))); // NOI18N

        txtCelular.setEditable(false);
        txtCelular.setName("Celular"); // NOI18N

        txtTelefono.setEditable(false);
        txtTelefono.setName("Teléfono"); // NOI18N

        lbTelefono.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTelefono.setText("Teléfono:   ");

        lbCelular.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbCelular.setText("Celular:");

        lbRazon.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbRazon.setText("Nombre:");

        txtDireccion.setEditable(false);
        txtDireccion.setName("Dirección"); // NOI18N

        lbDireccion.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion.setText("Dirección:  ");

        lbNit1.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit1.setText("Documento:");

        txtNombre1.setEditable(false);
        txtNombre1.setName("Razón social"); // NOI18N

        txtId.setEditable(false);
        txtId.setName("CC/NIT"); // NOI18N
        txtId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIdKeyReleased(evt);
            }
        });

        txtTipo.setEditable(false);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbTelefono, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbNit1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTipo, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                    .addComponent(txtTelefono))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(lbCelular)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtId))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbRazon, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
                    .addComponent(txtNombre1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbRazon, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombre1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNit1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(5, 5, 5))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos Factura", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 0, 12))); // NOI18N

        lbCelular1.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbCelular1.setText("Fecha  credito:");

        lbRazon1.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbRazon1.setText("Valor credito: ");

        txtValorAprovado.setEditable(false);
        txtValorAprovado.setName("Razón social"); // NOI18N
        txtValorAprovado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtValorAprovadoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtValorAprovadoKeyTyped(evt);
            }
        });

        lbDireccion2.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion2.setText("Observaciones:");

        txtObservaciones.setEditable(false);
        txtObservaciones.setName("Dirección"); // NOI18N
        txtObservaciones.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtObservacionesKeyReleased(evt);
            }
        });

        lbTelefono3.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTelefono3.setText("Num. Cuotas:");

        txtCuotas.setEditable(false);
        txtCuotas.setText("0");

        lbDireccion3.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion3.setText("Porc interés:");

        txtInteres.setEditable(false);
        txtInteres.setText("0");

        txtFechaDesenvolso.setEditable(false);
        txtFechaDesenvolso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFechaDesenvolsoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFechaDesenvolsoKeyTyped(evt);
            }
        });

        lbTelefono5.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTelefono5.setText("Num. Cuotas Pagas:");

        txtCuotas1.setEditable(false);
        txtCuotas1.setText("0");

        lbNit12.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit12.setText("No.Credito:");

        txtContrato.setEditable(false);
        txtContrato.setName("Empresa"); // NOI18N

        lbRazon2.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbRazon2.setText("Vendedor:");

        txtVendedor.setEditable(false);
        txtVendedor.setName("Razón social"); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbDireccion2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbNit12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbRazon1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtContrato, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                            .addComponent(txtValorAprovado))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbTelefono3, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                            .addComponent(lbRazon2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(txtCuotas, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(lbDireccion3, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtInteres, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtVendedor))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbTelefono5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbCelular1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(4, 4, 4)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCuotas1, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                            .addComponent(txtFechaDesenvolso)))
                    .addComponent(txtObservaciones))
                .addContainerGap(103, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbNit12, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtFechaDesenvolso, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbCelular1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtVendedor, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtContrato, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                            .addComponent(lbRazon2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(3, 3, 3)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lbRazon1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                            .addComponent(txtValorAprovado, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 2, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtCuotas1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbTelefono3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCuotas, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbDireccion3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                            .addComponent(txtInteres, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbTelefono5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(3, 3, 3)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtObservaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbDireccion2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        tblCuotas.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        tblCuotas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No.Cuota", "Fecha", "Abono", "Fecha pago", "Estado", "Mora", "Valor Cuota", "saldoAtrasado", "saldoMora", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCuotas.setRowHeight(24);
        tblCuotas.getTableHeader().setReorderingAllowed(false);
        tblCuotas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblCuotasKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tblCuotas);

        lbDireccion4.setFont(new java.awt.Font("Bell MT", 1, 36)); // NOI18N
        lbDireccion4.setText("Saldo:");

        txtTotalIntereses.setEditable(false);
        txtTotalIntereses.setFont(new java.awt.Font("Tahoma", 0, 32)); // NOI18N
        txtTotalIntereses.setName("Dirección"); // NOI18N
        txtTotalIntereses.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTotalInteresesKeyReleased(evt);
            }
        });

        lbTelefono4.setFont(new java.awt.Font("Bell MT", 1, 36)); // NOI18N
        lbTelefono4.setText("Abonar");
        lbTelefono4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lbTelefono4KeyReleased(evt);
            }
        });

        btnBuscTerceros2.setBackground(new java.awt.Color(102, 204, 255));
        btnBuscTerceros2.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnBuscTerceros2.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscTerceros2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/anterior.png"))); // NOI18N
        btnBuscTerceros2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscTerceros2.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnBuscTerceros2.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnBuscTerceros2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscTerceros2ActionPerformed(evt);
            }
        });

        btnBuscTerceros.setBackground(new java.awt.Color(204, 204, 204));
        btnBuscTerceros.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnBuscTerceros.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscTerceros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/imprimir.png"))); // NOI18N
        btnBuscTerceros.setText("ESTADO DE CUENTA");
        btnBuscTerceros.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscTerceros.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnBuscTerceros.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnBuscTerceros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscTercerosActionPerformed(evt);
            }
        });

        btnGuardar2.setBackground(new java.awt.Color(0, 153, 153));
        btnGuardar2.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnGuardar2.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/historia.png"))); // NOI18N
        btnGuardar2.setText("CERTIFICADO");
        btnGuardar2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar2.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnGuardar2.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnGuardar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardar2ActionPerformed(evt);
            }
        });

        txtAbonar.setFont(new java.awt.Font("Calibri", 0, 36)); // NOI18N
        txtAbonar.setText("0");
        txtAbonar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAbonarKeyReleased(evt);
            }
        });

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Medio de pago", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 14))); // NOI18N

        jRadioButton2.setBackground(new java.awt.Color(255, 255, 255));
        grupoMedioPagoCreditos.add(jRadioButton2);
        jRadioButton2.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jRadioButton2.setSelected(true);
        jRadioButton2.setText("Efectivo");

        jRadioButton3.setBackground(new java.awt.Color(255, 255, 255));
        grupoMedioPagoCreditos.add(jRadioButton3);
        jRadioButton3.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jRadioButton3.setText("Cheque");

        jRadioButton4.setBackground(new java.awt.Color(255, 255, 255));
        grupoMedioPagoCreditos.add(jRadioButton4);
        jRadioButton4.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jRadioButton4.setText("Nota Crédito");
        jRadioButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton4ActionPerformed(evt);
            }
        });

        jRadioButton1.setBackground(new java.awt.Color(255, 255, 255));
        grupoMedioPagoCreditos.add(jRadioButton1);
        jRadioButton1.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jRadioButton1.setText("Tarjeta");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(37, Short.MAX_VALUE)
                .addComponent(jRadioButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButton4)
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton2)
                    .addComponent(jRadioButton3)
                    .addComponent(jRadioButton4)
                    .addComponent(jRadioButton1))
                .addGap(0, 6, Short.MAX_VALUE))
        );

        btnGuardar1.setBackground(new java.awt.Color(0, 153, 153));
        btnGuardar1.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnGuardar1.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
        btnGuardar1.setText("GUARDAR  ");
        btnGuardar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnGuardar1.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnGuardar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlFormulario1Layout = new javax.swing.GroupLayout(pnlFormulario1);
        pnlFormulario1.setLayout(pnlFormulario1Layout);
        pnlFormulario1Layout.setHorizontalGroup(
            pnlFormulario1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormulario1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFormulario1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlFormulario1Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(pnlFormulario1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnlFormulario1Layout.createSequentialGroup()
                                .addComponent(lbTelefono4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtAbonar))
                            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnlFormulario1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFormulario1Layout.createSequentialGroup()
                                .addComponent(btnGuardar2, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBuscTerceros)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnGuardar1))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFormulario1Layout.createSequentialGroup()
                                .addComponent(lbDireccion4, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(19, 19, 19)
                                .addComponent(txtTotalIntereses, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jScrollPane2)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFormulario1Layout.createSequentialGroup()
                        .addComponent(lbNit11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscTerceros2)))
                .addGap(10, 10, 10))
        );
        pnlFormulario1Layout.setVerticalGroup(
            pnlFormulario1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormulario1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFormulario1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lbNit11, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                    .addComponent(btnBuscTerceros2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                .addGap(10, 10, 10)
                .addGroup(pnlFormulario1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbTelefono4)
                    .addGroup(pnlFormulario1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtAbonar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtTotalIntereses, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbDireccion4)))
                .addGroup(pnlFormulario1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlFormulario1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(pnlFormulario1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnBuscTerceros)
                            .addComponent(btnGuardar2)
                            .addComponent(btnGuardar1)))
                    .addGroup(pnlFormulario1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10))
        );

        jTabbedPane1.addTab("CREDITO", pnlFormulario1);

        txtFactura.setEditable(false);
        txtFactura.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtFactura.setName("CC/NIT"); // NOI18N
        txtFactura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFacturaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFacturaKeyTyped(evt);
            }
        });

        cmbListas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "L1", "L2", "L3", "L4" }));
        cmbListas.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbListasItemStateChanged(evt);
            }
        });
        cmbListas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbListasActionPerformed(evt);
            }
        });

        txtBodega.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtBodega.setName("CC/NIT"); // NOI18N
        txtBodega.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBodegaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBodegaKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout pnlInvisibleLayout = new javax.swing.GroupLayout(pnlInvisible);
        pnlInvisible.setLayout(pnlInvisibleLayout);
        pnlInvisibleLayout.setHorizontalGroup(
            pnlInvisibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInvisibleLayout.createSequentialGroup()
                .addGap(163, 163, 163)
                .addComponent(txtFactura)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbListas, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addComponent(txtBodega)
                .addGap(327, 327, 327))
        );
        pnlInvisibleLayout.setVerticalGroup(
            pnlInvisibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInvisibleLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(pnlInvisibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbListas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBodega, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1)
                    .addComponent(pnlInvisible, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1)
                .addGap(0, 0, 0)
                .addComponent(pnlInvisible, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        modelo = (DefaultTableModel) tblRegistros.getModel();
        modeloOrdenado = new TableRowSorter<>(modelo);
        tblRegistros.setRowSorter(modeloOrdenado);

        String opc = jComboBox1.getSelectedItem().toString();
        if (opc.equals("TODAS")) {
            opc = "";
        }
        modeloOrdenado.setRowFilter(RowFilter.regexFilter("(?i)" + opc, 8));
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void btnBuscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscar1ActionPerformed
        String consecutivo;

        if (tipo.equals("separe")) {
            consecutivo = "SEPARE-" + txtConsecutivo.getText();
        } else if (tipo.equals("cuentaCobro")) {
            consecutivo = "CCOBRO-" + txtConsecutivo.getText();
        } else if (tipo.equals("notadebito")) {
            consecutivo = "ND-" + txtConsecutivo.getText();
        } else {
            consecutivo = "FACT-" + txtConsecutivo.getText();
        }

        factura = "";

        boolean sw = false;
        int i = -1;

        for (i = 0; i < tblRegistros.getRowCount(); i++) {
            if (((String) tblRegistros.getValueAt(i, 0)).equals(consecutivo)) {
                sw = true;
                factura = tblRegistros.getValueAt(i, 0).toString();
                break;
            }
        }

        if (sw) {
            txtFactura.setText(consecutivo);
            txtNit.setText((String) tblRegistros.getValueAt(i, 1));
            txtNombre.setText((String) tblRegistros.getValueAt(i, 2));
            txtNombre1.setText((String) tblRegistros.getValueAt(i, 2));
            txtValor.setText((String) tblRegistros.getValueAt(i, 5));

            //            NOTAS CREDITO
            ModeloContacto nodoTer = instancias.getSql().getDatosTercero(txtNit.getText());
            Object[][] nodo = instancias.getSql().getNcCliente(nodoTer.getIdSistema());
            BigDecimal nc = new BigDecimal("0");

            for (int j = 0; j < nodo.length; j++) {
                nc = nc.add(big.getBigDecimal((String) nodo[j][0]));
            }

            NC = nc;

            Object[][] abonos = instancias.getSql().getRegistrosAbonos(factura);
            BigDecimal totalAbonos = big.getBigDecimal("0");

            for (Object[] abono : abonos) {
                totalAbonos = totalAbonos.add(big.getBigDecimal((abono[2])));
            }

            txtAbonado.setText(big.setMoneda(totalAbonos));
            txtPendiente.setText(big.setMoneda(big.getMoneda(txtValor.getText()).subtract(totalAbonos)));

            txtSaldo.setText(txtPendiente.getText());
            txtSaldoTotal.setText(txtPendiente.getText());
            txtTotalAbonos.setText(this.simbolo + " 0");

            txtEfectivo.setText(this.simbolo + " 0");
            txtCheque.setText(this.simbolo + " 0");
            txtTargeta.setText(this.simbolo + " 0");
            txtNC.setText(this.simbolo + " 0");
            txtIca.setText(this.simbolo + " 0");
            txtIva.setText(this.simbolo + " 0");
            txtFuente.setText(this.simbolo + " 0");
            txtOtro.setText(this.simbolo + " 0");
            txtDescuentos.setText(this.simbolo + " 0");
            txtSaldoCuentas.setText(this.simbolo + " 0");
            txtComprobante.setText("");

            calcularSaldo(txtAbonar);

            if (txtTotalIntereses.getText().equals(this.simbolo + " 0")) {
                txtAbonar.setEditable(false);
            } else {
                txtAbonar.setEditable(true);
            }

            if (tblRegistros.getValueAt(tblRegistros.getSelectedRow(), 10).equals("1")) {
                cargarPrestamo("FACT-" + txtConsecutivo.getText());
                jTabbedPane1.setSelectedIndex(2);
            } else {
                jTabbedPane1.setSelectedIndex(0);

                if (tipo.equals("domicilio") || tipo.equals("")) {

                    if (cmbTipo.getSelectedIndex() == 0) {
                        tblCartera.setModel(instancias.getSql().getRegistrosCxcPorCliente("factura", (String) tblRegistros.getValueAt(i, 1)));
                    } else if (cmbTipo.getSelectedIndex() == 2) {
                        tblCartera.setModel(instancias.getSql().getRegistrosCxcDomicilioPorCliente("factura", (String) tblRegistros.getValueAt(i, 1)));
                    }

                    DefaultTableModel modeloCartera = (DefaultTableModel) tblCartera.getModel();
                    modeloCartera.addColumn("Abonado");
                    modeloCartera.addColumn("Restante");
                    modeloCartera.addColumn("AbonadoAnterior");

                    if (tblCartera.getColumnModel().getColumnCount() > 0) {
                        tblCartera.getColumnModel().getColumn(0).setMinWidth(0);
                        tblCartera.getColumnModel().getColumn(0).setPreferredWidth(0);
                        tblCartera.getColumnModel().getColumn(0).setMaxWidth(0);
                        tblCartera.getColumnModel().getColumn(8).setMinWidth(0);
                        tblCartera.getColumnModel().getColumn(8).setPreferredWidth(0);
                        tblCartera.getColumnModel().getColumn(8).setMaxWidth(0);
                        tblCartera.getColumnModel().getColumn(12).setMinWidth(0);
                        tblCartera.getColumnModel().getColumn(12).setPreferredWidth(0);
                        tblCartera.getColumnModel().getColumn(12).setMaxWidth(0);
                    }

                    for (int j = 0; j < tblCartera.getRowCount(); j++) {
                        tblCartera.setValueAt(metodos.fecha(tblCartera.getValueAt(j, 2).toString()), j, 2);
                        tblCartera.setValueAt(metodos.fecha(tblCartera.getValueAt(j, 3).toString()), j, 3);
                        tblCartera.setValueAt(big.setMonedaExacta(big.getBigDecimal(tblCartera.getValueAt(j, 4).toString())), j, 4);
                        tblCartera.setValueAt(big.setMonedaExacta(big.getBigDecimal(tblCartera.getValueAt(j, 5).toString())), j, 5);
                        tblCartera.setValueAt("", j, 10);
                        tblCartera.setValueAt("", j, 11);
                        tblCartera.setValueAt(big.setMonedaExacta(big.getMoneda(tblCartera.getValueAt(j, 4).toString()).
                                subtract(big.getMoneda(tblCartera.getValueAt(j, 5).toString()))), j, 12);
                    }

                    rdAbonoFactura.setSelected(true);
                    rdAbonoFactura.setEnabled(true);
                    rdAbonoGeneral.setEnabled(true);
                    jScrollPane3.setVisible(true);
                    tblCartera.setVisible(true);

                } else {
                    DefaultTableModel c = (DefaultTableModel) tblCartera.getModel();
                    int h = tblCartera.getRowCount();
                    for (int m = 0; m < h; m++) {
                        c.removeRow(0);
                    }
                    rdAbonoFactura.setSelected(true);
                    rdAbonoFactura.setEnabled(false);
                    rdAbonoGeneral.setEnabled(false);
                    jScrollPane3.setVisible(false);
                    tblCartera.setVisible(false);
                }
            }
        } else {
            metodos.msgAdvertencia(this, "Factura no valida");
        }
    }//GEN-LAST:event_btnBuscar1ActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        cargarTabla();
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void txtDocumentoFiltroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDocumentoFiltroKeyReleased
        modeloOrdenado.setRowFilter(RowFilter.regexFilter("(?i)" + txtDocumentoFiltro.getText(), 1));
        txtVendedorFiltro.setText("");
        txtConsecutivo.setText("");
        txtConseManual.setText("");
        txtPlaca.setText("");
        txtNombreFiltro.setText("");
        txtMes.setText("");
    }//GEN-LAST:event_txtDocumentoFiltroKeyReleased

    private void txtNombreFiltroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreFiltroKeyReleased
        modeloOrdenado.setRowFilter(RowFilter.regexFilter("(?i)" + txtNombreFiltro.getText(), 2));
        txtDocumentoFiltro.setText("");
        txtVendedorFiltro.setText("");
        txtConsecutivo.setText("");
        txtConseManual.setText("");
        txtPlaca.setText("");
        txtMes.setText("");
    }//GEN-LAST:event_txtNombreFiltroKeyReleased

    private void tblRegistrosMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblRegistrosMouseReleased
        calcularDomicilios();
        txtConsecutivo.setText(((String) tblRegistros.getValueAt(tblRegistros.getSelectedRow(), 0)).replaceAll("FACT-", ""));
    }//GEN-LAST:event_tblRegistrosMouseReleased

    private void tblRegistrosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblRegistrosMousePressed
        calcularDomicilios();
    }//GEN-LAST:event_tblRegistrosMousePressed

    private void tblRegistrosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblRegistrosMouseEntered
        calcularDomicilios();
    }//GEN-LAST:event_tblRegistrosMouseEntered

    private void tblRegistrosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblRegistrosMouseClicked
        if (tipo.equals("separe")) {
            txtConsecutivo.setText(((String) tblRegistros.getValueAt(tblRegistros.getSelectedRow(), 0)).replaceAll("SEPARE-", ""));
        } else if (tipo.equals("cuentaCobro")) {
            txtConsecutivo.setText(((String) tblRegistros.getValueAt(tblRegistros.getSelectedRow(), 0)).replaceAll("CCOBRO-", ""));
        } else if (tipo.equals("notadebito")) {
            txtConsecutivo.setText(((String) tblRegistros.getValueAt(tblRegistros.getSelectedRow(), 0)).replaceAll("ND-", ""));
        } else {
            txtConsecutivo.setText(((String) tblRegistros.getValueAt(tblRegistros.getSelectedRow(), 0)).replaceAll("FACT-", ""));
        }

        if (evt.getClickCount() == 2) {
            btnBuscar1ActionPerformed(null);
        }

        calcularDomicilios();
    }//GEN-LAST:event_tblRegistrosMouseClicked

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (rdAbonoFactura.isSelected()) {
            guardar();
        } else if (rdAbonoGeneral.isSelected()) {
            guardarGeneral();
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void txtSaldoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSaldoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSaldoKeyTyped

    private void txtSaldoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSaldoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSaldoKeyReleased

    private void txtOtroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtOtroKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtOtroKeyTyped

    private void txtOtroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtOtroKeyReleased
        if (txtOtro.getText().equals("") || txtOtro.getText().equals(this.simbolo) || txtOtro.getText().equals(this.simbolo + " ")) {
            txtOtro.setText("0");
        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtOtro.requestFocus();
        } else {
            txtOtro.setText(big.setMoneda(big.getMoneda(txtOtro.getText())));
            calcularSaldo(txtOtro);
        }
    }//GEN-LAST:event_txtOtroKeyReleased

    private void txtIcaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIcaKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtIcaKeyTyped

    private void txtIcaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIcaKeyReleased
        if (txtIca.getText().equals("") || txtIca.getText().equals(this.simbolo) || txtIca.getText().equals(this.simbolo + " ")) {
            txtIca.setText("0");
        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtFuente.requestFocus();
        } else {
            txtIca.setText(big.setMoneda(big.getMoneda(txtIca.getText())));
            calcularSaldo(txtIca);
        }
    }//GEN-LAST:event_txtIcaKeyReleased

    private void txtIvaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIvaKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtIvaKeyTyped

    private void txtIvaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIvaKeyReleased
        if (txtIva.getText().equals("") || txtIva.getText().equals(this.simbolo) || txtIva.getText().equals(this.simbolo + " ")) {
            txtIva.setText("0");
        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtIca.requestFocus();
        } else {
            txtIva.setText(big.setMoneda(big.getMoneda(txtIva.getText())));
            calcularSaldo(txtIva);
        }
    }//GEN-LAST:event_txtIvaKeyReleased

    private void txtIvaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtIvaFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIvaFocusGained

    private void txtFuenteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFuenteKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtFuenteKeyTyped

    private void txtFuenteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFuenteKeyReleased
        if (txtFuente.getText().equals("") || txtFuente.getText().equals(this.simbolo) || txtFuente.getText().equals(this.simbolo + " ")) {
            txtFuente.setText("0");
        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtOtro.requestFocus();
        } else {
            txtFuente.setText(big.setMoneda(big.getMoneda(txtFuente.getText())));
            calcularSaldo(txtFuente);
        }
    }//GEN-LAST:event_txtFuenteKeyReleased

    private void txtComprobanteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtComprobanteKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtComprobanteKeyTyped

    private void txtComprobanteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtComprobanteKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtComprobanteKeyReleased

    private void txtTotalAbonosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalAbonosKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalAbonosKeyTyped

    private void txtTotalAbonosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalAbonosKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalAbonosKeyReleased

    private void txtNCKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNCKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtNCKeyTyped

    private void txtNCKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNCKeyReleased
        if (txtNC.getText().equals("") || txtNC.getText().equals(this.simbolo) || txtNC.getText().equals(this.simbolo + " ")) {
            txtNC.setText("0");
        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtComprobante.requestFocus();
        } else {
            if (NC != null) {
                if (NC.compareTo(big.getMoneda(txtNC.getText())) == -1) {
                    txtNC.setText(txtNC.getText().substring(0, txtNC.getText().length() - 1));
                    metodos.msgAdvertencia(this, "Valor no valido, el cliente no cuenta con esta suma");
                    return;
                }

                if (txtNC.getText().equals("") || txtNC.getText().equals(this.simbolo) || txtNC.getText().equals(this.simbolo + " ")) {
                    txtNC.setText(this.simbolo + " 0");
                }

                txtNC.setText(big.setMoneda(big.getMoneda(txtNC.getText())));

            } else {
                txtNC.setText(this.simbolo + " 0");
            }

            txtNC.setText(big.setMoneda(big.getMoneda(txtNC.getText())));
            calcularSaldo(txtNC);
        }
    }//GEN-LAST:event_txtNCKeyReleased

    private void txtTargetaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTargetaKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtTargetaKeyTyped

    private void txtTargetaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTargetaKeyReleased
        if (txtTargeta.getText().equals("") || txtTargeta.getText().equals(this.simbolo) || txtTargeta.getText().equals(this.simbolo + " ")) {
            txtTargeta.setText("0");
        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtNC.requestFocus();
        } else {
            txtTargeta.setText(big.setMoneda(big.getMoneda(txtTargeta.getText())));
            calcularSaldo(txtTargeta);
        }
    }//GEN-LAST:event_txtTargetaKeyReleased

    private void txtChequeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtChequeKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtChequeKeyTyped

    private void txtChequeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtChequeKeyReleased
        if (txtCheque.getText().equals("") || txtCheque.getText().equals(this.simbolo) || txtCheque.getText().equals(this.simbolo + " ")) {
            txtCheque.setText("0");
        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtTargeta.requestFocus();
        } else {
            txtCheque.setText(big.setMoneda(big.getMoneda(txtCheque.getText())));
            calcularSaldo(txtCheque);
        }
    }//GEN-LAST:event_txtChequeKeyReleased

    private void txtEfectivoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEfectivoKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtEfectivoKeyTyped

    private void txtEfectivoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEfectivoKeyReleased
        if (txtEfectivo.getText().equals("") || txtEfectivo.getText().equals(this.simbolo) || txtEfectivo.getText().equals(this.simbolo + " ")) {
            txtEfectivo.setText(this.simbolo + " 0");
        }

        if (big.getMoneda(txtEfectivo.getText()).compareTo(BigDecimal.ZERO) <= 0) {
            if (tblCartera.getRowCount() > 0) {
                tblCartera.setValueAt("", 0, 11);
                tblCartera.setValueAt("", 0, 10);
            }
        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtCheque.requestFocus();
        } else {
            txtEfectivo.setText(big.setMoneda(big.getMoneda(txtEfectivo.getText())));
            calcularSaldo(txtEfectivo);
        }
    }//GEN-LAST:event_txtEfectivoKeyReleased

    private void txtEfectivoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtEfectivoFocusGained

    }//GEN-LAST:event_txtEfectivoFocusGained

    private void txtPendienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPendienteKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPendienteKeyTyped

    private void txtPendienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPendienteKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPendienteKeyReleased

    private void txtAbonadoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAbonadoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAbonadoKeyTyped

    private void txtAbonadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAbonadoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAbonadoKeyReleased

    private void txtValorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtValorKeyTyped

    private void txtValorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValorKeyReleased

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped

    }//GEN-LAST:event_txtNombreKeyTyped

    private void txtNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreKeyReleased

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void txtNitKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNitKeyTyped

    }//GEN-LAST:event_txtNitKeyTyped

    private void txtNitKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNitKeyReleased
        txtFactura.setText(txtNit.getText() + "-" + (int) (Math.random() * 100 + 1));
    }//GEN-LAST:event_txtNitKeyReleased

    private void txtFacturaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFacturaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFacturaKeyTyped

    private void txtFacturaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFacturaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFacturaKeyReleased

    private void cmbListasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbListasActionPerformed

    }//GEN-LAST:event_cmbListasActionPerformed

    private void cmbListasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbListasItemStateChanged

    }//GEN-LAST:event_cmbListasItemStateChanged

    private void btnReImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReImprimirActionPerformed
        String consecutivo = "";
        try {
            consecutivo = "ABONO-" + metodos.msgIngresarEnter(this, "Documento a reimprimir");
        } catch (Exception e) {
            return;
        }

        if (consecutivo.equals("ABONO-")) {
            return;
        }

        nodo1 = instancias.getSql().getDatosAbonos(consecutivo);

        if (nodo1.isAnulada()) {
            metodos.msgError(this, "Este documento se encuentra anulado");
            return;
        }

//        String infoEmpresa = metodosGenerales.convertToMultiline(instancias.getInformacionEmpresaReimpresion() + "\n" + nodo1.getResolucion());
        String infoEmpresa = metodosGenerales.convertToMultiline(instancias.getInformacionEmpresaReimpresion());
        instancias.getReporte().verAbono(consecutivo, "Reimpresión", infoEmpresa);
    }//GEN-LAST:event_btnReImprimirActionPerformed

    private void btnAnularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnularActionPerformed
        String consecutivo = "";
        try {
            consecutivo = "ABONO-" + metodos.msgIngresarEnter(this, "Documento a anular?");
        } catch (Exception e) {
        }

        if (consecutivo.equals("ABONO-")) {
            return;
        }

        boolean anulado = false;
        try {
            anulado = instancias.getSql().getDocumentoAnulado("bdAbonos", "Where factura = '" + consecutivo + "' ");
        } catch (Exception e) {
            metodos.msgError(this, "El abono no existe");
            return;
        }

        if (anulado) {
            metodos.msgAdvertencia(this, "Este documento se encuentra anulado");
            return;
        }

        if (metodos.msgPregunta(this, "¿Desea anular este abono?") == 0) {
            if (!instancias.getSql().anularAbono(consecutivo)) {
                metodos.msgError(this, "Hubo un problema al anular el ingreso");
                return;
            }

            if (!instancias.getSql().modificarRegistroCxcAbono(consecutivo, "ANULADA")) {
                metodos.msgError(this, "Hubo un problema al anular la Cxc");
                return;
            }

            Object[][] facturasAbonos = instancias.getSql().getFacturasAbonos(consecutivo);

            for (int i = 0; i < facturasAbonos.length; i++) {
                System.out.println("Id abono: " + facturasAbonos[i][0].toString());

                String estado = instancias.getSql().estadoCxcPro(facturasAbonos[i][0].toString());
                if (estado.equalsIgnoreCase("CANCELADA")) {
                    if (!instancias.getSql().modificarEstadoCxcFactura(facturasAbonos[i][0].toString(), "PEND")) {
                        metodos.msgError(this, "Error al cambiar el estado: " + facturasAbonos[i][0].toString());
                        return;
                    }

                    if (!instancias.getSql().modificarEstadoCxcFacturaCanceladas(facturasAbonos[i][0].toString(), "")) {
                        metodos.msgError(this, "Error al cambiar el estado del abono");
                        return;
                    }
                }
            }

            ndCxc nodoCxc = instancias.getSql().getDatosCxcRecibo(consecutivo);

            BigDecimal totalAbono = big.getBigDecimal(nodoCxc.getValor());
            Object[][] datos = instancias.getSql().getAbonosCxcCuotas(consecutivo);
            for (int i = 0; i < datos.length; i++) {
                ndCuota nodoCuota = instancias.getSql().getDatosCuota(datos[i][0].toString());
                totalAbono = totalAbono.subtract(big.getBigDecimal(nodoCuota.getTotalAbono()));
                if (totalAbono.compareTo(BigDecimal.ZERO) > 0) {
                    nodoCuota.setTotalAbono("0");
                } else {
                    nodoCuota.setTotalAbono(totalAbono.multiply(BigDecimal.valueOf(-1)).toString());
                }
                if (!instancias.getSql().modificarCuota(nodoCuota)) {
                    metodos.msgError(this, "Se guardo el abono pero hubo un error al cambiar el estado de la cuota");
                    return;
                }
            }

            metodos.msgExito(this, "Abono anulado con éxito");
            cargarTabla();

//            } catch (Exception e) {
//                metodos.msgError(this, "Hubo un problema al anular el abono");
//            }
        }

    }//GEN-LAST:event_btnAnularActionPerformed

    private void txtConsecutivoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtConsecutivoKeyReleased
        modeloOrdenado.setRowFilter(RowFilter.regexFilter("(?i)" + txtConsecutivo.getText(), 0));
        txtDocumentoFiltro.setText("");
        txtVendedorFiltro.setText("");
        txtConseManual.setText("");
        txtPlaca.setText("");
        txtNombreFiltro.setText("");
        txtMes.setText("");
    }//GEN-LAST:event_txtConsecutivoKeyReleased

    private void txtIdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdKeyReleased

    }//GEN-LAST:event_txtIdKeyReleased

    private void txtValorAprovadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorAprovadoKeyReleased

    }//GEN-LAST:event_txtValorAprovadoKeyReleased

    private void txtValorAprovadoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorAprovadoKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtValorAprovadoKeyTyped

    private void txtObservacionesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtObservacionesKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnGuardarActionPerformed(null);
        }
    }//GEN-LAST:event_txtObservacionesKeyReleased

    private void txtFechaDesenvolsoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFechaDesenvolsoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaDesenvolsoKeyReleased

    private void txtFechaDesenvolsoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFechaDesenvolsoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaDesenvolsoKeyTyped

    private void txtTotalInteresesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalInteresesKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalInteresesKeyReleased

    private void btnBuscTerceros2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscTerceros2ActionPerformed
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_btnBuscTerceros2ActionPerformed

    private void btnBuscTercerosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscTercerosActionPerformed
        instancias.getReporte().verEstadoDeCuenta(txtContrato.getText(), instancias.getInformacionEmpresa());
    }//GEN-LAST:event_btnBuscTercerosActionPerformed

    private void btnGuardar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar2ActionPerformed

        instancias.getReporte().verCertificado(metodos.msgIngresar(this, "ESCRIBA AQUI LA ENTIDAD A LA CUAL VA DIRIGIDA EL CERTIFICADO"), txtNombre.getText(), txtId.getText(),
                txtCuotas.getText(), tblCuotas.getValueAt(0, 2).toString(),
                instancias.getSql().getDatosTercero(txtId.getText()).getCiudad(), txtValorAprovado.getText(),
                txtSaldo.getText(), tblRegistros.getToolTipText(), instancias.getInformacionEmpresa());
    }//GEN-LAST:event_btnGuardar2ActionPerformed

    private void txtAbonarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAbonarKeyReleased

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnGuardar1ActionPerformed(null);
        } else {
            if (txtAbonar.getText().equals("") || txtAbonar.getText().equals(this.simbolo) || txtAbonar.getText().equals(this.simbolo + " ")) {
                txtAbonar.setText("0");
            }
//            calcularSaldo(txtAbonar);
            txtAbonar.setText(big.setMoneda(big.getMoneda(txtAbonar.getText())));
        }
    }//GEN-LAST:event_txtAbonarKeyReleased

    private void jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton4ActionPerformed

    private void btnGuardar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar1ActionPerformed
        BigDecimal saldoAbono;
        BigDecimal abonoTotal = BigDecimal.ZERO, abonoTotalMora = BigDecimal.ZERO;
        BigDecimal abonar = big.getMoneda(txtAbonar.getText());

        saldoAbono = abonar;
        int fila = 0;

        //Se hace esto para validar que el valor que se esta abonando no sea mayor que el saldo.
        if (jRadioButton1.isSelected()) {
            txtTargeta.setText(big.setMoneda(abonar));
            calcularSaldo(txtAbonar);
        }
        if (jRadioButton2.isSelected()) {
            txtEfectivo.setText(big.setMoneda(abonar));
            calcularSaldo(txtEfectivo);
        }
        if (jRadioButton3.isSelected()) {
            txtCheque.setText(big.setMoneda(abonar));
            calcularSaldo(txtCheque);
        }
        if (jRadioButton4.isSelected()) {
            txtNC.setText(big.setMoneda(abonar));
            calcularSaldo(txtNC);
        }

        for (int i = 0; i < tblCuotas.getRowCount(); i++) {
            if (tblCuotas.getValueAt(i, 6).toString().equals(this.simbolo + " 0")) {
                fila++;
            } else {
                break;
            }
        }

        try {
            while (saldoAbono.compareTo(BigDecimal.ZERO) != 0) {
                if (!(big.getMoneda(tblCuotas.getValueAt(fila, 5).toString()).toString()).equals("0")) {

                    BigDecimal abonoMora;
                    abonoMora = saldoAbono;
                    saldoAbono = saldoAbono.subtract(big.getMoneda(tblCuotas.getValueAt(fila, 5).toString()));

                    if (saldoAbono.compareTo(BigDecimal.ZERO) == -1 || saldoAbono.compareTo(BigDecimal.ZERO) == 0) {
                        saldoAbono = BigDecimal.ZERO;
                        abonoTotalMora = abonoTotalMora.add(abonoMora);
                    } else {
                        abonoTotalMora = abonoTotalMora.add(big.getMoneda(tblCuotas.getValueAt(fila, 5).toString()));
                    }
                }

                if (saldoAbono.compareTo(BigDecimal.ZERO) > 0) {
                    if (saldoAbono.compareTo(big.getMoneda(tblCuotas.getValueAt(fila, 6).toString()).subtract(big.getMoneda(tblCuotas.getValueAt(fila, 5).toString()))) == -1) {
                        abonoTotal = abonoTotal.add(saldoAbono);
                        saldoAbono = BigDecimal.ZERO;
                    } else {
                        abonoTotal = abonoTotal.add(big.getMoneda(tblCuotas.getValueAt(fila, 6).toString()).subtract(big.getMoneda(tblCuotas.getValueAt(fila, 5).toString())));
                        saldoAbono = saldoAbono.subtract(big.getMoneda(tblCuotas.getValueAt(fila, 6).toString()).subtract(big.getMoneda(tblCuotas.getValueAt(fila, 5).toString())));
                    }
                    fila++;
                }
            }
        } catch (Exception e) {
        }

        saldoAbono = abonar;

        if (jRadioButton1.isSelected()) {
            txtTargeta.setText(big.setMoneda(abonoTotal));
            calcularSaldo(txtTargeta);
        }
        if (jRadioButton2.isSelected()) {
            txtEfectivo.setText(big.setMoneda(abonoTotal));
            calcularSaldo(txtEfectivo);
        }
        if (jRadioButton3.isSelected()) {
            txtCheque.setText(big.setMoneda(abonoTotal));
            calcularSaldo(txtCheque);
        }
        if (jRadioButton4.isSelected()) {
            txtNC.setText(big.setMoneda(abonoTotal));
            calcularSaldo(txtNC);
        }

        String facturaMora = "";
        String documentoAbonoMora = "";
        String abono = "ABONO-" + (String) instancias.getSql().getNumConsecutivo("ABONO")[0];

        if (abonoTotalMora.compareTo(BigDecimal.ZERO) > 0) {
            facturaMora = instancias.getFactura().generarFacturaExterior(txtId.getText(), new String[][]{new String[]{"INT.MORA",
                big.getBigDecimal(abonoTotalMora).toString()}}, "1", false, "", "");
            documentoAbonoMora = guardarAbonoMora(facturaMora, big.getBigDecimal(abonoTotalMora).toString());
        }

        fila = 0;

        for (int i = 0; i < tblCuotas.getRowCount(); i++) {
            if (tblCuotas.getValueAt(i, 6).toString().equals(this.simbolo + " 0")) {
                fila++;
            } else {
                break;
            }
        }

        while (saldoAbono.compareTo(BigDecimal.ZERO) != 0) {
            ndCuota nodo = instancias.getSql().getDatosCuota(txtContrato.getText() + "-" + modeloPrestamo.getValueAt(fila, 0));
            nodo.setUsuario(instancias.getUsuario());
            nodo.setFechaAbono(metodos.fechaConsulta(metodosGenerales.fecha()));

            if (!big.getMoneda(tblCuotas.getValueAt(fila, 5).toString()).equals("0")) {
                BigDecimal abonoMora;
                abonoMora = saldoAbono;
                saldoAbono = saldoAbono.subtract(big.getMoneda(tblCuotas.getValueAt(fila, 5).toString()));

                if (saldoAbono.compareTo(BigDecimal.ZERO) == -1 || saldoAbono.compareTo(BigDecimal.ZERO) == 0) {
                    saldoAbono = BigDecimal.ZERO;
                    nodo.setSaldoMora(big.getBigDecimal(big.getBigDecimal(nodo.getSaldoMora()).add(abonoMora)).toString());

                    try {
                        if (nodo.getDocumentoMora().equals("")) {
                            nodo.setDocumentoMora(facturaMora);
                        } else {
                            nodo.setDocumentoMora(nodo.getDocumentoMora() + ";" + facturaMora);
                        }
                    } catch (Exception e) {
                        nodo.setDocumentoMora(facturaMora);
                    }

                    try {
                        if (nodo.getDocumentoAbonoMora().equals("")) {
                            nodo.setDocumentoAbonoMora(documentoAbonoMora);
                        } else {
                            nodo.setDocumentoAbonoMora(nodo.getDocumentoAbonoMora() + ";" + documentoAbonoMora);
                        }
                    } catch (Exception e) {
                        nodo.setDocumentoAbonoMora(documentoAbonoMora);
                    }

                } else {
                    nodo.setSaldoMora(big.getBigDecimal(big.getBigDecimal(nodo.getSaldoMora()).add(big.getMoneda(tblCuotas.getValueAt(fila, 5).toString()))).toString());

                    try {
                        if (nodo.getDocumentoMora().equals("")) {
                            nodo.setDocumentoMora(facturaMora);
                        } else {
                            nodo.setDocumentoMora(nodo.getDocumentoMora() + ";" + facturaMora);
                        }
                    } catch (Exception e) {
                        nodo.setDocumentoMora(facturaMora);
                    }

                    try {
                        if (nodo.getDocumentoAbonoMora().equals("")) {
                            nodo.setDocumentoAbonoMora(documentoAbonoMora);
                        } else {
                            nodo.setDocumentoAbonoMora(nodo.getDocumentoAbonoMora() + ";" + documentoAbonoMora);
                        }
                    } catch (Exception e) {
                        nodo.setDocumentoAbonoMora(documentoAbonoMora);
                    }
                }
            }

            if (saldoAbono.compareTo(BigDecimal.ZERO) > 0) {
                abonoTotal = abonoTotal.add(saldoAbono);

                try {
                    if (nodo.getDocumentoAbono().equals("")) {
                        nodo.setDocumentoAbono(abono);
                    } else {
                        nodo.setDocumentoAbono(nodo.getDocumentoAbono() + ";" + abono);
                    }
                } catch (Exception e) {
                    nodo.setDocumentoAbono(abono);
                }

                if (saldoAbono.compareTo(big.getMoneda(tblCuotas.getValueAt(fila, 6).toString()).subtract(big.getMoneda(tblCuotas.getValueAt(fila, 5).toString()))) == -1) {
                    nodo.setTotalAbono(saldoAbono.add(big.getBigDecimal(nodo.getTotalAbono())).toString());
                    saldoAbono = BigDecimal.ZERO;
                } else {
                    nodo.setTotalAbono((big.getMoneda(tblCuotas.getValueAt(fila, 6).toString()).subtract(big.getMoneda(tblCuotas.getValueAt(fila, 5).toString())).add(big.getBigDecimal(nodo.getTotalAbono()))).toString());
                    saldoAbono = saldoAbono.subtract(big.getMoneda(tblCuotas.getValueAt(fila, 6).toString())).add(big.getMoneda(tblCuotas.getValueAt(fila, 5).toString()));
                }
            }

            if (tblCuotas.getValueAt(fila, 4).equals("ATRASADA")) {
                nodo.setMora((big.getMoneda(tblCuotas.getValueAt(fila, 5).toString()).add(big.getMoneda(tblCuotas.getValueAt(fila, 8).toString()))).toString());
                if (big.getMoneda(nodo.getSaldoAtrasado()).toString().equals("0")) {
                    nodo.setSaldoAtrasado((big.getMoneda(tblCuotas.getValueAt(fila, 6).toString()).subtract(big.getMoneda(tblCuotas.getValueAt(fila, 5).toString()))).toString());
                }
            }

            instancias.getSql().modificarCuota(nodo);

            fila++;

        }

        if (abonoTotal.compareTo(BigDecimal.ZERO) > 0) {
            credito = true;
            abono = guardar();
        }

        btnActualizarActionPerformed(null);
        jTabbedPane1.setSelectedIndex(1);

        actualizarConsecutivo();
        txtAbonar.setText(this.simbolo + " 0");

//        }

    }//GEN-LAST:event_btnGuardar1ActionPerformed

    private void tblCuotasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblCuotasKeyReleased

        int fila = tblCuotas.getSelectedRow();
        if (tblCuotas.getValueAt(fila, 4).equals("SALDADA") || tblCuotas.getValueAt(fila, 5).toString().equals(this.simbolo + " 0")) {
            return;
        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            if (tblCuotas.getSelectedColumn() == 0) {

                try {
                    int r = tblCuotas.getSelectedRow();
                    tblCuotas.changeSelection(r, 0, false, false);
                    tblCuotas.removeEditor();

                    tblCuotas.editCellAt(r, 5);
                    tblCuotas.setColumnSelectionInterval(5, 5);
                    tblCuotas.transferFocus();
                } catch (Exception e) {
                }

            }

            try {
                tblCuotas.setValueAt(big.setMoneda(big.getMoneda(tblCuotas.getValueAt(fila, 5).toString())), fila, 5);
                tblCuotas.setValueAt(big.setMoneda(big.getMoneda(tblCuotas.getValueAt(fila, 5).toString()).add(big.getMoneda(tblCuotas.getValueAt(fila, 7).toString()))), fila, 6);
            } catch (Exception e) {
                tblCuotas.setValueAt(big.setMoneda(big.getBigDecimal("0")), fila, 5);
            }

            txtAbonar.requestFocus();

        }

    }//GEN-LAST:event_tblCuotasKeyReleased

    private void cmbTipoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbTipoItemStateChanged
        cargarTabla();
    }//GEN-LAST:event_cmbTipoItemStateChanged

    private void txtSaldoTotalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSaldoTotalKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSaldoTotalKeyReleased

    private void txtSaldoTotalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSaldoTotalKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSaldoTotalKeyTyped

    private void lbTelefono4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lbTelefono4KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtAbonar.requestFocus();
        }
    }//GEN-LAST:event_lbTelefono4KeyReleased

    private void rdAbonoFacturaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdAbonoFacturaItemStateChanged
        seleccionarRadio();
    }//GEN-LAST:event_rdAbonoFacturaItemStateChanged

    private void rdAbonoGeneralItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdAbonoGeneralItemStateChanged
        seleccionarRadio();
    }//GEN-LAST:event_rdAbonoGeneralItemStateChanged

    private void lbEfectivoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lbEfectivoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtEfectivo.requestFocus();
        }
    }//GEN-LAST:event_lbEfectivoKeyReleased

    private void rdAbonoFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdAbonoFacturaActionPerformed
        if (rdAbonoFactura.isSelected()) {
            for (int i = 0; i < tblCartera.getRowCount(); i++) {
                tblCartera.setValueAt(false, i, 9);
                tblCartera.setValueAt("", i, 10);
                tblCartera.setValueAt("", i, 11);
            }
        }
    }//GEN-LAST:event_rdAbonoFacturaActionPerformed

    private void tblCarteraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCarteraMouseClicked
        calcularSaldo(txtEfectivo);

        BigDecimal total = BigDecimal.ZERO;
        for (int i = 0; i < tblCartera.getRowCount(); i++) {
            if ((Boolean) tblCartera.getValueAt(i, 9)) {
                total = total.add(big.getMoneda(tblCartera.getValueAt(i, 5).toString()));
            }
        }
        txtSaldoCuentas.setText(big.setMoneda(total));

        if (!(Boolean) tblCartera.getValueAt(tblCartera.getSelectedRow(), 9)) {
            tblCartera.setValueAt("", tblCartera.getSelectedRow(), 11);
            tblCartera.setValueAt("", tblCartera.getSelectedRow(), 10);
        }
    }//GEN-LAST:event_tblCarteraMouseClicked

    private void tblCarteraMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCarteraMouseEntered
//        calcularSaldo(txtEfectivo);
//
//        if (!(Boolean) tblCartera.getValueAt(tblCartera.getSelectedRow(), 9)) {
//            tblCartera.setValueAt("", 0, 11);
//            tblCartera.setValueAt("", 0, 10);
//        }
    }//GEN-LAST:event_tblCarteraMouseEntered

    private void tblCarteraMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCarteraMousePressed
        calcularSaldo(txtEfectivo);

        BigDecimal total = BigDecimal.ZERO;
        for (int i = 0; i < tblCartera.getRowCount(); i++) {
            if ((Boolean) tblCartera.getValueAt(i, 9)) {
                total = total.add(big.getMoneda(tblCartera.getValueAt(i, 5).toString()));
            }
        }
        txtSaldoCuentas.setText(big.setMoneda(total));

        if (!(Boolean) tblCartera.getValueAt(tblCartera.getSelectedRow(), 9)) {
            tblCartera.setValueAt("", tblCartera.getSelectedRow(), 11);
            tblCartera.setValueAt("", tblCartera.getSelectedRow(), 10);
        }
    }//GEN-LAST:event_tblCarteraMousePressed

    private void rdAbonoGeneralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdAbonoGeneralActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdAbonoGeneralActionPerformed

    private void txtConseManualKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtConseManualKeyReleased
        modeloOrdenado.setRowFilter(RowFilter.regexFilter("(?i)" + txtConseManual.getText(), 11));
        txtDocumentoFiltro.setText("");
        txtVendedorFiltro.setText("");
        txtConsecutivo.setText("");
        txtPlaca.setText("");
        txtNombreFiltro.setText("");
        txtMes.setText("");
    }//GEN-LAST:event_txtConseManualKeyReleased

    private void txtVendedorFiltroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtVendedorFiltroKeyReleased
        modeloOrdenado.setRowFilter(RowFilter.regexFilter("(?i)" + txtVendedorFiltro.getText(), 12));
        txtDocumentoFiltro.setText("");
        txtConsecutivo.setText("");
        txtConseManual.setText("");
        txtPlaca.setText("");
        txtNombreFiltro.setText("");
        txtMes.setText("");
    }//GEN-LAST:event_txtVendedorFiltroKeyReleased

    private void txtVendedorFiltroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtVendedorFiltroKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVendedorFiltroKeyTyped

    private void txtPlacaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPlacaKeyReleased
        modeloOrdenado.setRowFilter(RowFilter.regexFilter("(?i)" + txtPlaca.getText(), 13));
        txtDocumentoFiltro.setText("");
        txtVendedorFiltro.setText("");
        txtConsecutivo.setText("");
        txtConseManual.setText("");
        txtNombreFiltro.setText("");
        txtMes.setText("");
    }//GEN-LAST:event_txtPlacaKeyReleased

    private void txtSaldoCuentasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSaldoCuentasKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSaldoCuentasKeyReleased

    private void txtSaldoCuentasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSaldoCuentasKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSaldoCuentasKeyTyped

    private void txtMesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMesKeyReleased
        modeloOrdenado.setRowFilter(RowFilter.regexFilter("(?i)" + txtMes.getText(), 11));
        txtDocumentoFiltro.setText("");
        txtVendedorFiltro.setText("");
        txtConsecutivo.setText("");
        txtConseManual.setText("");
        txtNombreFiltro.setText("");
        txtPlaca.setText("");
    }//GEN-LAST:event_txtMesKeyReleased

    private void btnBuscTerceros1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscTerceros1MouseClicked
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_btnBuscTerceros1MouseClicked

    private void btnVolver1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVolver1MouseClicked
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_btnVolver1MouseClicked

    private void btnFacturarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFacturarActionPerformed
        Boolean entro = false;
        for (int i = 0; i < tblRegistros.getRowCount(); i++) {
            if ((Boolean) tblRegistros.getValueAt(i, 13)) {
                entro = true;
            }
        }

        if (!entro) {
            metodos.msgError(this, "Debe seleccionar un pedido");
            return;
        }

        for (int i = 0; i < tblRegistros.getRowCount(); i++) {
            if ((Boolean) tblRegistros.getValueAt(i, 13)) {
                if (tipo.equals("separe")) {
                    txtConsecutivo.setText(((String) tblRegistros.getValueAt(i, 0)).replaceAll("SEPARE-", ""));
                } else if (tipo.equals("cuentaCobro")) {
                    txtConsecutivo.setText(((String) tblRegistros.getValueAt(i, 0)).replaceAll("CCOBRO-", ""));
                } else {
                    txtConsecutivo.setText(((String) tblRegistros.getValueAt(i, 0)).replaceAll("FACT-", ""));
                }

                btnBuscar1ActionPerformed(evt);
                txtEfectivo.setText(txtSaldoTotal.getText());
                calcularSaldo(txtEfectivo);
                saltarPasos = true;
                btnGuardarActionPerformed(evt);
            }
        }

        metodos.msgExito(this, "Domicilios facturados con exito");
        btnActualizarActionPerformed(evt);
    }//GEN-LAST:event_btnFacturarActionPerformed

    private void txtBodegaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBodegaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBodegaKeyReleased

    private void txtBodegaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBodegaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBodegaKeyTyped

    private void txtDescuentosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescuentosKeyReleased
        if (txtDescuentos.getText().equals("") || txtDescuentos.getText().equals(this.simbolo) || txtDescuentos.getText().equals(this.simbolo + " ")) {
            txtDescuentos.setText("0");
        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtDescuentos.requestFocus();
        } else {
            txtDescuentos.setText(big.setMoneda(big.getMoneda(txtDescuentos.getText())));
            calcularSaldo(txtDescuentos);
        }
    }//GEN-LAST:event_txtDescuentosKeyReleased

    private void txtDescuentosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescuentosKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtDescuentosKeyTyped

    public void seleccionarRadio() {
        if (rdAbonoGeneral.isSelected()) {
            BigDecimal suma = big.getBigDecimal("0");
            for (int i = 0; i < tblCartera.getRowCount(); i++) {
                suma = suma.add(big.getMoneda(tblCartera.getValueAt(i, 5).toString()));
            }
            txtSaldoTotal.setText(big.setMonedaExacta(suma));
            txtSaldo.setText(big.setMonedaExacta(suma));

        } else if (rdAbonoFactura.isSelected()) {
            txtSaldo.setText(txtPendiente.getText());
            txtSaldoTotal.setText(txtPendiente.getText());
        }

        actualizarConsecutivo();
        calcularSaldo(txtEfectivo);
    }

    public void cargarTabla() {
        btnActualizar.setText("ACTUALIZAR");

        if (cmbTipo.getSelectedIndex() == 0) {
            tipo = "";
            lbVendedor.setText("VENDEDOR");
            btnFacturar.setVisible(false);
            txtTotalizado.setVisible(false);
        } else if (cmbTipo.getSelectedIndex() == 1) {
            tipo = "separe";
            btnFacturar.setVisible(false);
            txtTotalizado.setVisible(false);
            lbVendedor.setText("VENDEDOR");
        } else if (cmbTipo.getSelectedIndex() == 2) {
            tipo = "domicilio";
            lbVendedor.setText("DOMICILIARIO");
            btnFacturar.setVisible(true);
            txtTotalizado.setVisible(true);
        } else if (cmbTipo.getSelectedIndex() == 3) {
            tipo = "notadebito";
            lbVendedor.setText("DOMICILIARIO");
            btnFacturar.setVisible(false);
            txtTotalizado.setVisible(false);
        }

        if (cmbTipo.getSelectedIndex() == 3) {
            txtMes.setVisible(true);
            lbMes.setVisible(true);
        } else {
            txtMes.setVisible(false);
            lbMes.setVisible(false);
        }

        modelo = (DefaultTableModel) tblRegistros.getModel();
        int j = tblRegistros.getRowCount();
        for (int i = 0; i < j; i++) {
            modelo.removeRow(0);
        }

        String condicion = "";
        if (tipo.equals("separe")) {
            tblRegistros.setModel(instancias.getSql().getRegistrosCxcSepare("factura"));
        } else if (tipo.equals("domicilio")) {
            tblRegistros.setModel(instancias.getSql().getRegistrosDomicilios("factura"));
        } else if (tipo.equals("notadebito")) {
            condicion = " where tipo = 'NOTADEBITO' ";
            tblRegistros.setModel(instancias.getSql().getRegistrosCxc("facturaTerm", condicion));
        } else {
            condicion = " where tipo = 'FACT' ";
            tblRegistros.setModel(instancias.getSql().getRegistrosCxc("facturaTerm", condicion));
        }

        modelo = (DefaultTableModel) tblRegistros.getModel();
        modeloOrdenado = new TableRowSorter<>(modelo);
        tblRegistros.setRowSorter(modeloOrdenado);

        tblRegistros.getColumnModel().getColumn(0).setMinWidth(100);
        tblRegistros.getColumnModel().getColumn(0).setPreferredWidth(115);
        tblRegistros.getColumnModel().getColumn(0).setMaxWidth(140);

        tblRegistros.getColumnModel().getColumn(1).setMinWidth(75);
        tblRegistros.getColumnModel().getColumn(1).setPreferredWidth(120);
        tblRegistros.getColumnModel().getColumn(1).setMaxWidth(140);

        tblRegistros.getColumnModel().getColumn(3).setMinWidth(90);
        tblRegistros.getColumnModel().getColumn(3).setPreferredWidth(90);
        tblRegistros.getColumnModel().getColumn(3).setMaxWidth(90);
        tblRegistros.getColumnModel().getColumn(4).setMinWidth(90);
        tblRegistros.getColumnModel().getColumn(4).setPreferredWidth(90);
        tblRegistros.getColumnModel().getColumn(4).setMaxWidth(90);

        tblRegistros.getColumnModel().getColumn(5).setMinWidth(80);
        tblRegistros.getColumnModel().getColumn(5).setPreferredWidth(100);
        tblRegistros.getColumnModel().getColumn(5).setMaxWidth(130);

        tblRegistros.getColumnModel().getColumn(6).setMinWidth(80);
        tblRegistros.getColumnModel().getColumn(6).setPreferredWidth(100);
        tblRegistros.getColumnModel().getColumn(6).setMaxWidth(130);

        tblRegistros.getColumnModel().getColumn(7).setMinWidth(80);
        tblRegistros.getColumnModel().getColumn(7).setPreferredWidth(80);
        tblRegistros.getColumnModel().getColumn(7).setMaxWidth(80);

        tblRegistros.getColumnModel().getColumn(8).setMinWidth(90);
        tblRegistros.getColumnModel().getColumn(8).setPreferredWidth(90);
        tblRegistros.getColumnModel().getColumn(8).setMaxWidth(90);

        tblRegistros.getColumnModel().getColumn(9).setMinWidth(0);
        tblRegistros.getColumnModel().getColumn(9).setPreferredWidth(0);
        tblRegistros.getColumnModel().getColumn(9).setMaxWidth(0);

        tblRegistros.getColumnModel().getColumn(10).setMinWidth(0);
        tblRegistros.getColumnModel().getColumn(10).setPreferredWidth(0);
        tblRegistros.getColumnModel().getColumn(10).setMaxWidth(0);

        if (tipo.equals("domicilio")) {
            tblRegistros.getColumnModel().getColumn(11).setMinWidth(0);
            tblRegistros.getColumnModel().getColumn(11).setPreferredWidth(0);
            tblRegistros.getColumnModel().getColumn(11).setMaxWidth(0);
        }

        consultarMaestros();

        try {
            if ((Boolean) datos[61]) {
                txtConseManual.setVisible(true);
                lbNit7.setVisible(true);
                tblRegistros.getColumnModel().getColumn(11).setMaxWidth(100);
            } else {
                txtConseManual.setVisible(false);
                lbNit7.setVisible(false);
                tblRegistros.getColumnModel().getColumn(11).setMinWidth(0);
                tblRegistros.getColumnModel().getColumn(11).setPreferredWidth(0);
                tblRegistros.getColumnModel().getColumn(11).setMaxWidth(0);
            }

            if (instancias.getConfiguraciones().isParqueadero()) {
                txtPlaca.setVisible(true);
                lbPlaca.setVisible(true);
                tblRegistros.getColumnModel().getColumn(13).setMaxWidth(70);
            } else {
                txtPlaca.setVisible(false);
                lbPlaca.setVisible(false);

                if (tipo.equals("domicilio")) {
                    tblRegistros.getColumnModel().getColumn(13).setMinWidth(30);
                    tblRegistros.getColumnModel().getColumn(13).setPreferredWidth(30);
                    tblRegistros.getColumnModel().getColumn(13).setMaxWidth(30);
                } else {
                    tblRegistros.getColumnModel().getColumn(13).setMinWidth(0);
                    tblRegistros.getColumnModel().getColumn(13).setPreferredWidth(0);
                    tblRegistros.getColumnModel().getColumn(13).setMaxWidth(0);
                }
            }
        } catch (Exception e) {
        }

        for (int i = 0; i < tblRegistros.getRowCount(); i++) {
            tblRegistros.setValueAt(metodos.fecha((String) tblRegistros.getValueAt(i, 3)), i, 3);
            tblRegistros.setValueAt(metodos.fecha((String) tblRegistros.getValueAt(i, 4)), i, 4);
            tblRegistros.setValueAt(big.setMoneda(big.getBigDecimal((String) tblRegistros.getValueAt(i, 6))), i, 6);
            tblRegistros.setValueAt(big.setMoneda(big.getBigDecimal((String) tblRegistros.getValueAt(i, 5))), i, 5);

//            if (tipo.equals("separe")) {
//                tblRegistros.setValueAt(((String) tblRegistros.getValueAt(i, 0)).replace("SEPARE-", ""), i, 0);
//            } else {
//                tblRegistros.setValueAt(((String) tblRegistros.getValueAt(i, 0)).replace("FACT-", ""), i, 0);
//            }
        }
    }

    public void consultarMaestros() {
        datos = instancias.getSql().getDatosMaestra();
    }

//    public void calcularSaldo() {
//        BigDecimal abonoActual = big.getMoneda(txtEfectivo.getText()).add(big.getMoneda(txtCheque.getText())).add(big.getMoneda(txtTargeta.getText())).add(big.getMoneda(txtNC.getText())).add(big.getMoneda(txtIva.getText())).add(big.getMoneda(txtIca.getText())).add(big.getMoneda(txtOtro.getText())).add(big.getMoneda(txtFuente.getText()));
//        txtTotalAbonos.setText(big.setMoneda(big.getMoneda(txtAbonado.getText()).add(abonoActual)));
//        txtSaldo.setText(big.setMoneda(big.getMoneda(txtValor.getText()).subtract(big.getMoneda(txtTotalAbonos.getText()))));
//    }
    public BigDecimal acumularValores() {
        BigDecimal abonoActual = big.getMoneda(txtEfectivo.getText()).add(big.getMoneda(txtCheque.getText())).add(big.getMoneda(txtTargeta.getText())).
                add(big.getMoneda(txtNC.getText())).add(big.getMoneda(txtIva.getText())).add(big.getMoneda(txtIca.getText())).
                add(big.getMoneda(txtOtro.getText())).add(big.getMoneda(txtFuente.getText())).add(big.getMoneda(txtDescuentos.getText()));
        return abonoActual;
    }

    public void calcularSaldo(JTextField textbox) {
        if (rdAbonoFactura.isSelected()) {

            if (big.getMoneda(textbox.getText()).compareTo(big.getMoneda(txtPendiente.getText())) == 1) {
                metodos.msgError(this, "La suma excede el valor del saldo");
                textbox.setText(this.simbolo + " 0");
//            textbox.setText(big.setMoneda(big.getMoneda(textbox.getText())));
                txtTotalAbonos.setText(txtAbonado.getText());
                textbox.requestFocus();
                return;
            } else if (acumularValores().compareTo(big.getMoneda(txtPendiente.getText())) == 1) {
                metodos.msgError(this, "La suma excede el valor del saldo");
                textbox.setText(this.simbolo + " 0");

//            textbox.setText(big.setMoneda(big.getMoneda(textbox.getText())));
                txtTotalAbonos.setText(txtAbonado.getText());
                textbox.requestFocus();
                return;
            } else {
                BigDecimal abonoActual = acumularValores();
                txtTotalAbonos.setText(big.setMoneda(big.getMoneda(txtAbonado.getText()).add(abonoActual)));
                txtSaldo.setText(big.setMoneda(big.getMoneda(txtValor.getText()).subtract(big.getMoneda(txtTotalAbonos.getText()))));
            }
        } else {
            BigDecimal abonoActual = acumularValores();
            BigDecimal restante = big.getBigDecimal("0");
            BigDecimal saldo = big.getMoneda(txtSaldoTotal.getText()).subtract(acumularValores());
            if (saldo.compareTo(BigDecimal.ZERO) >= 0) {

                for (int i = 0; i < tblCartera.getRowCount(); i++) {
                    if ((Boolean) tblCartera.getValueAt(i, 9)) {
                        restante = abonoActual.subtract(big.getMoneda(tblCartera.getValueAt(i, 5).toString()));
                        if (restante.compareTo(BigDecimal.ZERO) > 0) {
                            abonoActual = abonoActual.subtract(big.getMoneda(tblCartera.getValueAt(i, 5).toString()));
                            tblCartera.setValueAt(big.setMonedaExacta(big.getMoneda(tblCartera.getValueAt(i, 5).toString())), i, 10);
                            if (abonoActual.compareTo(BigDecimal.ZERO) > 0) {
                                tblCartera.setValueAt(this.simbolo + " 0", i, 11);
                            }
                        } else {
                            tblCartera.setValueAt(big.setMonedaExacta(abonoActual), i, 10);
                            abonoActual = big.getMoneda(tblCartera.getValueAt(i, 5).toString()).subtract(abonoActual);
                            tblCartera.setValueAt(big.setMonedaExacta(abonoActual), i, 11);
                            for (int j = i + 1; j < tblCartera.getRowCount(); j++) {
                                tblCartera.setValueAt("", j, 10);
                                tblCartera.setValueAt("", j, 11);
                            }
                            break;
                        }
                    }

                }

            } else {
                lbEfectivo.requestFocus();
                metodos.msgError(this, "La suma excede el valor del saldo");
                textbox.setText(this.simbolo + " 0");
                for (int i = 0; i < tblCartera.getRowCount(); i++) {
                    tblCartera.setValueAt("", i, 10);
                    tblCartera.setValueAt("", i, 11);
                }
            }
            txtSaldo.setText(big.setMoneda(big.getMoneda(txtSaldoTotal.getText()).subtract(acumularValores())));
        }
    }

    public void cargarCliente(String Id) {

        ModeloContacto nodo = instancias.getSql().getDatosTercero(Id);

        if (nodo.getId() != null) {
            txtNombre.setText(nodo.getNombre());
            txtNombre1.setText(nodo.getNombre());
            txtTelefono.setText(nodo.getTelefono());
            txtCelular.setText(nodo.getCelular());
            txtDireccion.setText(nodo.getDireccion());
            txtTipo.setText(nodo.getTipo());
            txtNombre.requestFocus();

            return;
        }
    }

    public void limpiar() {

        for (int x = 0; x < jPanel6.getComponentCount(); x++) {
            if (jPanel6.getComponent(x) instanceof JTextField) {
                JTextField textField = (JTextField) jPanel6.getComponent(x);
                textField.setText("");
            }
        }
        for (int x = 0; x < jPanel4.getComponentCount(); x++) {
            if (jPanel4.getComponent(x) instanceof JTextField) {
                JTextField textField = (JTextField) jPanel4.getComponent(x);
                textField.setText("");
            }
        }
        txtCuotas.setText("0");
        txtInteres.setText("0");
        txtContrato.setText("");
        int j = tblCuotas.getRowCount();

        for (int i = 0; i < j; i++) {
            modeloPrestamo.removeRow(0);
        }
    }

    public void calcularDomicilios() {
        if (cmbTipo.getSelectedIndex() == 2) {
            BigDecimal total = BigDecimal.ZERO;
            for (int i = 0; i < tblRegistros.getRowCount(); i++) {
                if ((Boolean) tblRegistros.getValueAt(i, 13)) {
                    total = total.add(big.getMoneda(tblRegistros.getValueAt(i, 6).toString()));
                }
            }
            txtTotalizado.setText("TOTALIZADO DOMICILIOS: " + big.setMoneda(total));
        }
    }

    public String guardar() {
        if (txtAbonado.getText().equals(txtTotalAbonos.getText())) {
            metodos.msgAdvertenciaAjustado(this, "No ha ingresado el metodo de pago");
        } else {
            String comprobante = txtComprobante.getText();

            if (comprobante.equals("")) {
                comprobante = "0";
            }

            String prefijo = "", abono2 = "";
            try {
                prefijo = instancias.getIdAbono();
            } catch (Exception e) {
            }

            if (prefijo == null) {
                prefijo = "";
            }

            String abono = "ABONO-" + (String) instancias.getSql().getNumConsecutivo("ABONO")[0];
            abono2 = "ABONO-" + prefijo + instancias.getSql().getNumConsecutivo("ABONO")[0].toString();
            //GUARDAR EN CXC
            Object[] vectCxc = {factura, "ABONO", "ABONO-REALIZADO", abono2, big.getMoneda(txtTotalAbonos.getText()).
                subtract(big.getMoneda(txtAbonado.getText())), comprobante, metodos.fechaConsulta(metodosGenerales.fecha()),
                instancias.getUsuario(), instancias.getTerminal(), false, txtFactura.getText()};

            ndCxc nodoCxc = metodos.llenarCxc(vectCxc);

            if (!instancias.getSql().agregarCxc(nodoCxc)) {
                metodos.msgError(this, "Hubo un problema al guardar el abono en las cuentas por cobrar");
                return "";
            }

            ModeloContacto nodo = instancias.getSql().getDatosTercero(txtNit.getText());

            /* GUARDAR REGISTROS DEL ABONO */
            Object[] infoAbono = {abono, txtFactura.getText(), nodo.getIdSistema(), txtComprobante.getText(), big.getMoneda(txtValor.getText()),
                convertirNumeroALetras.Convertir((big.getMoneda(txtTotalAbonos.getText()).subtract(big.getMoneda(txtAbonado.getText()))).toString()),
                big.getMoneda(txtAbonado.getText()), big.getMoneda(txtSaldo.getText()), metodos.fechaConsulta(metodosGenerales.fecha()),
                big.getMoneda(txtTotalAbonos.getText()).subtract(big.getMoneda(txtAbonado.getText())), abono2
            };

            if (!instancias.getSql().agregarRegistroAbono(infoAbono)) {
                metodos.msgError(this, "Hubo un problema al guardar el abono");
                return "";
            }
            /* FIN REGISTRO */

            Object[] vector = {abono, nodo.getIdSistema(), "", "", metodos.fechaConsulta(metodosGenerales.fecha()),
                metodos.fechaConsulta(metodosGenerales.fecha()), big.getMoneda(txtEfectivo.getText()), big.getMoneda(txtNC.getText()), big.getMoneda(txtCheque.getText()),
                big.getMoneda(txtTargeta.getText()), factura, (String) instancias.getSql().getNumConsecutivo("ABONO")[0], "", false,
                false, big.getMoneda(txtTotalAbonos.getText()).subtract(big.getMoneda(txtAbonado.getText())), big.getMoneda(txtDescuentos.getText()),
                "0", "0", "", instancias.getUsuario(), big.getMoneda(txtIva.getText()), big.getMoneda(txtIca.getText()),
                big.getMoneda(txtFuente.getText()), big.getMoneda(txtOtro.getText()), "", metodos.fechaConsulta(metodosGenerales.fecha()),
                instancias.getTerminal(), 0, "PENDIENTE", abono2, instancias.getResolucion(), "0", "", "", "", "", "", "", "", ""};

            ndGarantia nodoCaja = metodos.llenarAbonos(vector);

            if (!instancias.getSql().agregarAbono(nodoCaja)) {
                metodos.msgError(this, "Hubo un problema al guardar el abono en caja");
                return null;
            }

            String enLetras = "";
            try {
                enLetras = convertirNumeroALetras.Convertir(big.getBigDecimal(acumularValores()).toString());
            } catch (Exception e) {
                enLetras = "";
            }

            String valorNeto = txtValor.getText().substring(2, txtValor.getText().length()).replace(".", "");

            String total = big.setMoneda(big.getBigDecimal(acumularValores()));

            //ABONO GENERAL
            Object[] infoAbonoGeneral = {
                txtFactura.getText(), abono2, metodos.fechaConsulta(metodosGenerales.fecha()), instancias.getUsuario(), nodo.getIdSistema(),
                total, enLetras, big.getMoneda(valorNeto)
            };

            if (!instancias.getSql().agregarAbonoGeneral(infoAbonoGeneral)) {
                metodos.msgError(this, "Hubo un problema al guardar el abono general");
                return "";
            }

            if (big.getMoneda(txtSaldo.getText()).compareTo(BigDecimal.ZERO) <= 0) {

                if (!instancias.getSql().cancelarCxc(factura)) {
                    metodos.msgError(this, "Error al cancelar la cuenta");
                    return null;
                }

                if (tipo.equals("separe")) {
                    metodos.msgExito(this, "Valor saldado");

                    String bodega = "";
                    try {
                        bodega = instancias.getSql().getBodegaSepare(factura);
                    } catch (Exception e) {
                    }

                    if (bodega.equals("")) {
                        bodega = "123-22";
                    }

                    instancias.getFactura().generarFacturaExterior("separe", factura, false, true, bodega);
                }
            }

            //CANCELACION NOTAS CREDITO
            if (!txtNC.getText().equals("") & !txtNC.getText().equals(this.simbolo) & !txtNC.getText().equals(this.simbolo + " ")) {
                Object[][] notas = instancias.getSql().getNcCliente(nodo.getIdSistema());
                BigDecimal nc = big.getMoneda(txtNC.getText());

                for (Object[] nota : notas) {
                    if (nc.compareTo(big.getBigDecimal("0")) == -1) {
                        break;
                    } else if (big.getBigDecimal(nota[0]).compareTo(nc) == -1 || big.getBigDecimal(nota[0]).compareTo(nc) == 0) {
                        nc = nc.subtract(big.getBigDecimal(nota[0]));
                        if (!instancias.getSql().descontarNc((String) nota[1], "0")) {
                            metodos.msgError(this, "Error al modificar el saldo de la nota credito: " + nota[1]);
                        }
                    } else {
                        if (!instancias.getSql().descontarNc((String) nota[1], String.valueOf((big.getBigDecimal(nota[0])).subtract(nc)))) {
                            metodos.msgError(this, "Error al modificar el saldo de la nota credito: " + nota[1]);
                        }
                        nc = nc.subtract(big.getBigDecimal(nota[0]));
                    }
                }
            }

            if (!saltarPasos) {
                metodos.msgExito(this, "Abono exitoso");

                if (credito) {
                    if (metodos.msgPregunta(this, "¿Desea imprimir abono?") == 0) {
                        instancias.getReporte().verAbonoCredito(abono2, "Original", instancias.getInformacionEmpresaCompleto());
                        if (metodos.msgPregunta(this, "¿Desea imprimir copia?") == 0) {
                            instancias.getReporte().verAbonoCredito(abono2, "Copia", instancias.getInformacionEmpresaCompleto());
                        }
                    }
                    credito = false;
                } else {
                    if (metodos.msgPregunta(this, "¿Desea imprimir abono?") == 0) {
                        instancias.getReporte().verAbonoGeneral(abono2, "Original", instancias.getInformacionEmpresaCompleto());
                        if (metodos.msgPregunta(this, "¿Desea imprimir copia?") == 0) {
                            instancias.getReporte().verAbonoGeneral(abono2, "Copia", instancias.getInformacionEmpresaCompleto());
                        }
                    }
                }
            }

            if (!instancias.getSql().aumentarConsecutivo("ABONO", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("ABONO")[0]) + 1)) {
                metodos.msgError(this, "Error al guardar en el consecutivo del abono");
            }

            actualizarConsecutivo();

            if (!saltarPasos) {
                btnActualizarActionPerformed(null);
            }

            saltarPasos = false;
            jTabbedPane1.setSelectedIndex(1);
            return abono;

        }
        return "";
    }

    public void actualizarConsecutivo() {
        String prefijo = "", abono2 = "";
        try {
            prefijo = instancias.getIdAbono();
        } catch (Exception e) {
        }

        if (prefijo == null) {
            prefijo = "";
        }

        abono2 = prefijo + instancias.getSql().getNumConsecutivo("ABONO")[0].toString();

        txtNoAbono.setText(abono2);
    }

    public String guardarGeneral() {

        Boolean esta = false;
        for (int i = 0; i < tblCartera.getRowCount(); i++) {
            if ((Boolean) tblCartera.getValueAt(i, 9)) {
                esta = true;
            }
        }

        if (!esta) {
            metodos.msgError(this, "Seleccione una factura para pagar");
            return null;
        }

        if (acumularValores().compareTo(BigDecimal.ZERO) <= 0) {
            metodos.msgError(this, "No ha añadido ningun valor al abono");
        } else {
            String abono = "ABONO-" + (String) instancias.getSql().getNumConsecutivo("ABONO")[0];

            BigDecimal saldoTotal = big.getBigDecimal("0");

            String factura = "";
            BigDecimal valor1 = BigDecimal.ZERO, valor2 = BigDecimal.ZERO, valor3 = BigDecimal.ZERO, valor4 = BigDecimal.ZERO;

            for (int i = 0; i < tblCartera.getRowCount(); i++) {
                if ((Boolean) tblCartera.getValueAt(i, 9)) {

                    try {
                        valor1 = valor1.add(big.getMoneda(tblCartera.getValueAt(i, 4).toString()));
                    } catch (Exception e) {
                    }

                    try {
                        valor2 = valor2.add(big.getMoneda(tblCartera.getValueAt(i, 10).toString()));
                    } catch (Exception e) {
                    }

                    try {
                        valor3 = valor3.add(big.getMoneda(tblCartera.getValueAt(i, 11).toString()));
                    } catch (Exception e) {
                    }

                    try {
                        valor4 = valor4.add(big.getMoneda(tblCartera.getValueAt(i, 12).toString()));
                    } catch (Exception e) {
                    }

                    factura = factura + tblCartera.getValueAt(i, 1).toString() + ", ";
                }

                if (tblCartera.getValueAt(i, 11).toString().equals("")) {
                    saldoTotal = saldoTotal.add(big.getMoneda(tblCartera.getValueAt(i, 4).toString()));
                } else {
                    saldoTotal = saldoTotal.add(big.getMoneda(tblCartera.getValueAt(i, 11).toString()));
                }
            }

            factura = factura.substring(0, factura.length() - 2);

            String prefijo = "", abono2 = "";
            try {
                prefijo = instancias.getIdAbono();
            } catch (Exception e) {
            }

            if (prefijo == null) {
                prefijo = "";
            }

            abono2 = "ABONO-" + prefijo + instancias.getSql().getNumConsecutivo("ABONO")[0].toString();
            ModeloContacto nodo = instancias.getSql().getDatosTercero(txtNit.getText());

            Object[] infoAbono = {abono, factura, nodo.getIdSistema(), txtComprobante.getText(), valor1, convertirNumeroALetras.Convertir(valor2.toString()),
                valor4, valor3, metodos.fechaConsulta(metodosGenerales.fecha()), valor2, abono2
            };

            if (!instancias.getSql().agregarRegistroAbono(infoAbono)) {
                metodos.msgError(this, "Hubo un problema al guardar el abono");
                return "";
            }

            BigDecimal efectivo = big.getMoneda(txtEfectivo.getText()), nc2 = big.getMoneda(txtNC.getText()),
                    cheque = big.getMoneda(txtCheque.getText()), tarjeta = big.getMoneda(txtTargeta.getText()),
                    rtfuente = big.getMoneda(txtFuente.getText()), otro = big.getMoneda(txtOtro.getText()),
                    ica = big.getMoneda(txtIca.getText()), iva = big.getMoneda(txtIva.getText());

            Object[] vector = {abono, nodo.getIdSistema(), "", "", metodos.fechaConsulta(metodosGenerales.fecha()), metodos.fechaConsulta(metodosGenerales.fecha()),
                efectivo, nc2, cheque, tarjeta, factura, (String) instancias.getSql().getNumConsecutivo("ABONO")[0], "", false,
                false, big.getMoneda(valor2.toString()), big.getMoneda(txtDescuentos.getText()), "0", "0", "", instancias.getUsuario(), iva, ica,
                rtfuente, otro, "", metodos.fechaConsulta(metodosGenerales.fecha()),
                instancias.getTerminal(), 0, "PENDIENTE", abono2, instancias.getResolucion(), "0", "", "", "", "", "", "", "", ""};

            ndGarantia nodoCaja = metodos.llenarAbonos(vector);

            if (!instancias.getSql().agregarAbono(nodoCaja)) {
                metodos.msgError(this, "Error al guardar el abono");
                return null;
            }

            for (int i = 0; i < tblCartera.getRowCount(); i++) {
                if (!tblCartera.getValueAt(i, 10).toString().equals("")) {
                    if ((Boolean) tblCartera.getValueAt(i, 9)) {

                        //GUARDAR EN CXC
                        Object[] vectCxc = {tblCartera.getValueAt(i, 1).toString(), "ABONO", "ABONO-REALIZADO", abono2,
                            big.getMoneda(tblCartera.getValueAt(i, 10).toString()), "0", metodos.fechaConsulta(metodosGenerales.fecha()),
                            instancias.getUsuario(), instancias.getTerminal(), false, tblCartera.getValueAt(i, 1).toString()};
                        ndCxc nodoCxc = metodos.llenarCxc(vectCxc);

                        if (!instancias.getSql().agregarCxc(nodoCxc)) {
                            metodos.msgError(this, "Hubo un problema al guardar el abono");
                            return "";
                        }

                        String enLetras = "";
                        String valor = tblCartera.getValueAt(i, 10).toString();
                        try {
                            enLetras = convertirNumeroALetras.Convertir(big.getMoneda(valor).toString());
                        } catch (Exception e) {
                            enLetras = "";
                        }

                        String valorNeto = tblCartera.getValueAt(i, 4).toString().substring(2, tblCartera.getValueAt(i, 4).toString().length()).replace(".", "");

                        //ABONO GENERAL
                        Object[] infoAbonoGeneral = {tblCartera.getValueAt(i, 1), abono2, metodos.fechaConsulta(metodosGenerales.fecha()), instancias.getUsuario(), nodo.getIdSistema(),
                            tblCartera.getValueAt(i, 10), enLetras, big.getMoneda(valorNeto)
                        };

                        if (!instancias.getSql().agregarAbonoGeneral(infoAbonoGeneral)) {
                            metodos.msgError(this, "Hubo un problema al guardar el abono");
                            return "";
                        }

                        if (big.getMoneda(tblCartera.getValueAt(i, 11).toString()).compareTo(BigDecimal.ZERO) <= 0) {
                            if (!instancias.getSql().cancelarCxc(tblCartera.getValueAt(i, 1).toString())) {
                                metodos.msgError(this, "Error al cancelar la cuenta");
                                return null;
                            }

                            if (tipo.equals("separe")) {
                                if (metodos.msgPregunta(this, "Valor saldado, ¿Desea generar la factura?") == 0) {
                                    instancias.getFactura().generarFacturaExterior("separe", factura, false, true, "123-22");
                                }
                            }
                        }

                        //CANCELACION NOTAS CREDITO
                        if (!txtNC.getText().equals("") & !txtNC.getText().equals(this.simbolo) & !txtNC.getText().equals(this.simbolo + " ")) {
                            Object[][] notas = instancias.getSql().getNcCliente(nodo.getIdSistema());
                            BigDecimal nc = big.getMoneda(txtNC.getText());

                            for (Object[] nota : notas) {
                                if (nc.compareTo(big.getBigDecimal("0")) == -1) {
                                    break;
                                } else if (big.getBigDecimal(nota[0]).compareTo(nc) == -1 || big.getBigDecimal(nota[0]).compareTo(nc) == 0) {
                                    nc = nc.subtract(big.getBigDecimal(nota[0]));
                                    if (!instancias.getSql().descontarNc((String) nota[1], "0")) {
                                        metodos.msgError(this, "Error al modificar el saldo de la nota credito: " + nota[1]);
                                    }
                                } else {
                                    if (!instancias.getSql().descontarNc((String) nota[1], String.valueOf((big.getBigDecimal(nota[0])).subtract(nc)))) {
                                        metodos.msgError(this, "Error al modificar el saldo de la nota credito: " + nota[1]);
                                    }
                                    nc = nc.subtract(big.getBigDecimal(nota[0]));
                                }
                            }
                        }
                    }
                }
            }

            if (metodos.msgPregunta(this, "¿Desea imprimir abono?") == 0) {
                instancias.getReporte().verAbonoGeneral(abono2, "Original", instancias.getInformacionEmpresaCompleto());

                if (metodos.msgPregunta(this, "¿Desea imprimir copia?") == 0) {
                    instancias.getReporte().verAbonoGeneral(abono2, "Copia", instancias.getInformacionEmpresaCompleto());
                }
            }

            if (!instancias.getSql().aumentarConsecutivo("ABONOGENERAL", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("ABONOGENERAL")[0]) + 1)) {
                metodos.msgError(this, "Hubo un problema al guardar en el consecutivo del abono");
            }

            if (!instancias.getSql().aumentarConsecutivo("ABONO", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("ABONO")[0]) + 1)) {
                metodos.msgError(this, "Hubo un problema al guardar en el consecutivo del abono");
            }

            actualizarConsecutivo();

            btnActualizarActionPerformed(null);
            jTabbedPane1.setSelectedIndex(1);
        }

        return "";
    }

    public String guardarAbonoMora(String factura, String valor) {

//                try {
        String comprobante = txtComprobante.getText();

        if (comprobante.equals("")) {
            comprobante = "0";
        }

        String abono = "ABONO-" + (String) instancias.getSql().getNumConsecutivo("ABONO")[0];

        String prefijo = "", abono2 = "";
        try {
            prefijo = instancias.getIdAbono();
        } catch (Exception e) {
        }
        abono2 = "ABONO-" + prefijo + instancias.getSql().getNumConsecutivo("ABONO")[0].toString();

        Object[] infoAbono = {abono, factura, txtNit.getText(), "", big.getBigDecimal(valor), convertirNumeroALetras.Convertir(big.getMoneda(valor).toString()),
            this.simbolo + "0", this.simbolo + "0", metodosGenerales.fechaHora(), this.simbolo + "0", abono2
        };

        if (!instancias.getSql().agregarRegistroAbono(infoAbono)) {
            metodos.msgError(this, "Hubo un problema al guardar el abono");
            return null;
        }

        //GUARDAR EN CXC
        Object[] vectCxc = {factura, "ABONO", "ABONO-REALIZADO", abono,
            big.getMoneda(valor), comprobante, metodos.fechaConsulta(metodosGenerales.fecha()),
            instancias.getUsuario(), instancias.getTerminal(), false, ""};

        ndCxc nodoCxc = metodos.llenarCxc(vectCxc);

        if (!instancias.getSql().agregarCxc(nodoCxc)) {
            metodos.msgError(this, "Hubo un problema al guardar el abono");
            return null;
        }

        BigDecimal tarjeta = big.getBigDecimal("0"), efectivo = big.getBigDecimal("0"), cheque = big.getBigDecimal("0"), nc = big.getBigDecimal("0");

        if (jRadioButton1.isSelected()) {
            tarjeta = big.getBigDecimal(valor);
        }
        if (jRadioButton2.isSelected()) {
            efectivo = big.getBigDecimal(valor);
        }
        if (jRadioButton3.isSelected()) {
            cheque = big.getBigDecimal(valor);
        }
        if (jRadioButton4.isSelected()) {
            nc = big.getBigDecimal(valor);
        }

        Object[] vector = {abono, txtNit.getText(), "", "", metodosGenerales.fecha(), metodosGenerales.fecha(), efectivo, nc, cheque, tarjeta, factura,
            (String) instancias.getSql().getNumConsecutivo("ABONO")[0], "", false, false, big.getMoneda(valor), "0", "0", "0", "", instancias.getUsuario(),
            big.getMoneda("0"), big.getMoneda("0"), big.getMoneda("0"), big.getMoneda("0"), "", metodosGenerales.fecha(), instancias.getTerminal(), 0,
            "PENDIENTE", factura, instancias.getResolucion(), "0", "", "", "", "", "", "", "", ""};

        ndGarantia nodoCaja = metodos.llenarAbonos(vector);

        if (!instancias.getSql().agregarAbono(nodoCaja)) {
            metodos.msgError(this, "Hubo un problema al guardar el abono");
            return null;
        }

//            if (big.getBigDecimal("49").compareTo(big.getMoneda("0")) == 1) {
        if (!instancias.getSql().cancelarCxc(factura)) {
            metodos.msgError(this, "Hubo un problema al cancelar la cuenta");
        }

//            }
//                metodos.msgExito(this, "Abono exitoso");
//        if (metodos.msgPregunta(this, "¿Desea imprimir el abono de mora?") == 0) {
//            instancias.getReporte().verAbonoGeneral(abono, "Original", instancias.getInformacionEmpresaCompleto());
//            if (metodos.msgPregunta(this, "¿Desea imprimir copia del abono de mora?") == 0) {
//                instancias.getReporte().verAbonoGeneral(abono, "Copia", instancias.getInformacionEmpresaCompleto());
//            }
//        }
        if (!instancias.getSql().aumentarConsecutivo("ABONO", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("ABONO")[0]) + 1)) {
            metodos.msgError(this, "Hubo un problema al guardar en el consecutivo del abono");
        }

        actualizarConsecutivo();

//                btnActualizarActionPerformed(null);
////                jTabbedPane1.setSelectedIndex(1);
        return abono;

//                } catch (Exception e) {
//                    metodos.msgError(this, "Hubo un problema al guardar el abono");
//                    return "";
//                }
//        }
    }

    public void cargarPrestamo(String Contrato) {

        limpiar();

        ndPrestamo nodo = instancias.getSql().getDatosPrestamo(Contrato);

        if (nodo.getContrato() != null) {
            txtFactura.setText(nodo.getFactura());
            txtId.setText(nodo.getCliente());
            txtInteres.setText(nodo.getInteres());
            txtFechaDesenvolso.setText(metodos.fecha(nodo.getFechaDesenvolso()));
            txtObservaciones.setText(nodo.getObservaciones());
            txtValorAprovado.setText(big.setMoneda(big.getBigDecimal(nodo.getValorAprovado())));
            txtCuotas.setText(big.setMoneda(big.getBigDecimal(nodo.getCuotas())));

            ndGarantia nodo2 = instancias.getSql().getDatosAbonos(nodo.getFactura());
            txtVendedor.setText(nodo2.getVendedor());
            BigDecimal saldo = big.getBigDecimal(nodo.getTotal());
            txtContrato.setText(nodo.getContrato());
            cargarCliente(txtId.getText());
            Object[][] datos = instancias.getSql().getCuotasPrestamo(nodo.getContrato());
            int abonos = 0, cont = 0;
            for (Object[] dato : datos) {
                String miFecha = metodosGenerales.fecha();
                dato[1] = metodos.fecha(dato[1].toString());
                dato[2] = big.setMonedaExacta(big.getBigDecimal(dato[2]));
                dato[3] = metodos.fecha(dato[3].toString());
                dato[7] = big.setMonedaExacta(big.getBigDecimal(dato[7]));
                dato[5] = big.setMonedaExacta(big.getBigDecimal(dato[5].toString()));
                dato[8] = big.setMoneda(big.getBigDecimal(dato[8].toString()));
                dato[9] = instancias.getSql().estadoCuotasCreditos(nodo.getContrato(), dato[0].toString());
                saldo = saldo.subtract(big.getBigDecimal(dato[6].toString()));

                dato[6] = big.setMonedaExacta(big.getMoneda(dato[2].toString()).subtract(big.getBigDecimal(dato[6].toString())));

                long fecha;
                long diasMora, diasMora2;

                fecha = metodos.restarFecha(metodosGenerales.fecha(), dato[1].toString());

                if (fecha < 0) {

                    if (big.getMoneda(dato[7].toString()).toString().equals("0")) {
                        dato[7] = big.setMonedaExacta(big.getMoneda(dato[6].toString()));
                    } else {
                        dato[7] = big.setMonedaExacta(big.getMoneda(dato[7].toString()));
                    }

                    if (dato[9].toString().equals("PENDIENTE") || dato[9].toString().equals("VENCIDA")) {
//                        if (dato[3].toString().equals(metodos.fecha(nodo.getFecha()))) {
                        int j = 0;
                        fecha = fecha * -1;
                        diasMora = fecha;
                        diasMora2 = fecha;
                        while (diasMora > Long.valueOf(instancias.getDiasCobrarMora())) {
                            miFecha = metodos.sumarMeses(miFecha, -1);
                            fecha = metodos.restarFecha(miFecha, metodosGenerales.fecha());
                            diasMora = diasMora2 - fecha;
                            j++;
                        }
                        if (j > 0) {
                            dato[5] = big.setMonedaExacta((big.getMoneda(dato[7].toString()).multiply(big.getBigDecimal(instancias.getPorcentajeMora()))).divide(big.getBigDecimal("100")));
                            dato[5] = big.setMonedaExacta(big.getMoneda(dato[5].toString()).subtract(big.getMoneda(dato[8].toString())));
                            dato[5] = big.setMonedaExacta(big.getMoneda(dato[5].toString()).multiply(big.getBigDecimal(j)));
                        }
                    } else {
                        dato[5] = big.setMonedaExacta(big.getMoneda(dato[5].toString()).subtract(big.getMoneda(dato[8].toString())));
                    }
                    dato[6] = big.setMonedaExacta(big.getMoneda(dato[6].toString()).add(big.getMoneda(dato[5].toString())));
                    saldo = saldo.add(big.getMoneda(dato[5].toString()));
                }

                if (!dato[6].toString().equals(this.simbolo + " 0")) {
                    dato[3] = "";
                    fecha = metodos.restarFecha(metodosGenerales.fecha(), dato[1].toString());
                    if (fecha >= 0) {
                        dato[4] = "PENDIENTE";
                    } else {
                        dato[4] = "ATRASADA";
                    }
                    abonos++;
                } else {
                    fecha = metodos.restarFecha(dato[3].toString(), dato[1].toString());
                    if (fecha >= 0) {
                        dato[4] = "SALDADA";
                        dato[5] = this.simbolo + " 0";
                    } else {
                        dato[4] = "SALDADA-ATRASADA";
                        dato[5] = this.simbolo + " 0";
                    }
                    cont++;
                }
                modeloPrestamo.addRow(dato);
            }

            txtCuotas1.setText(cont + "");
            txtCuotas.setText(modeloPrestamo.getValueAt(modeloPrestamo.getRowCount() - 1, 0).toString());
            txtTotalIntereses.setText(big.setMoneda(saldo));

            tblRegistros.setToolTipText(abonos + "");

            for (int i = 0; i < tblCuotas.getRowCount(); i++) {
                if (!tblCuotas.getValueAt(i, 7).toString().equals(this.simbolo + " 0")) {
                    ndCuota nodoCuota = instancias.getSql().getDatosCuota(txtContrato.getText() + "-" + modeloPrestamo.getValueAt(i, 0));
                    nodoCuota.setMora((big.getMoneda(tblCuotas.getValueAt(i, 5).toString()).add(big.getMoneda(tblCuotas.getValueAt(i, 8).toString()))).toString());
                    instancias.getSql().modificarCuota(nodoCuota);
                }
            }
            calcularSaldo(txtAbonar);
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAnular;
    private javax.swing.JButton btnBuscTerceros;
    private javax.swing.JLabel btnBuscTerceros1;
    private javax.swing.JButton btnBuscTerceros2;
    private javax.swing.JButton btnBuscar1;
    private javax.swing.JButton btnFacturar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnGuardar1;
    private javax.swing.JButton btnGuardar2;
    private javax.swing.JButton btnReImprimir;
    private javax.swing.JLabel btnVolver1;
    private javax.swing.JComboBox cmbListas;
    private javax.swing.JComboBox cmbTipo;
    private javax.swing.ButtonGroup grupoAbono;
    private javax.swing.ButtonGroup grupoMedioPagoCreditos;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lbAbonado;
    private javax.swing.JLabel lbAbono;
    private javax.swing.JLabel lbCelular;
    private javax.swing.JLabel lbCelular1;
    private javax.swing.JLabel lbCheque;
    private javax.swing.JLabel lbCheque1;
    private javax.swing.JLabel lbComprobante;
    private javax.swing.JLabel lbDireccion;
    private javax.swing.JLabel lbDireccion2;
    private javax.swing.JLabel lbDireccion3;
    private javax.swing.JLabel lbDireccion4;
    private javax.swing.JLabel lbEfectivo;
    private javax.swing.JLabel lbEfectivo1;
    private javax.swing.JLabel lbMes;
    private javax.swing.JLabel lbNC;
    private javax.swing.JLabel lbNC1;
    private javax.swing.JLabel lbNC2;
    private javax.swing.JLabel lbNit;
    private javax.swing.JLabel lbNit1;
    private javax.swing.JLabel lbNit11;
    private javax.swing.JLabel lbNit12;
    private javax.swing.JLabel lbNit2;
    private javax.swing.JLabel lbNit24;
    private javax.swing.JLabel lbNit25;
    private javax.swing.JLabel lbNit26;
    private javax.swing.JLabel lbNit3;
    private javax.swing.JLabel lbNit4;
    private javax.swing.JLabel lbNit5;
    private javax.swing.JLabel lbNit6;
    private javax.swing.JLabel lbNit7;
    private javax.swing.JLabel lbNombre;
    private javax.swing.JLabel lbPendiente;
    private javax.swing.JLabel lbPlaca;
    private javax.swing.JLabel lbRazon;
    private javax.swing.JLabel lbRazon1;
    private javax.swing.JLabel lbRazon2;
    private javax.swing.JLabel lbTargeta;
    private javax.swing.JLabel lbTargeta1;
    private javax.swing.JLabel lbTelefono;
    private javax.swing.JLabel lbTelefono3;
    private javax.swing.JLabel lbTelefono4;
    private javax.swing.JLabel lbTelefono5;
    private javax.swing.JLabel lbTotalAbonos;
    private javax.swing.JLabel lbValor;
    private javax.swing.JLabel lbVendedor;
    private javax.swing.JPanel pnlAbonos;
    private javax.swing.JPanel pnlDatos;
    private javax.swing.JPanel pnlFormulario;
    private javax.swing.JPanel pnlFormulario1;
    private javax.swing.JPanel pnlInvisible;
    private javax.swing.JPanel pnlSaldo;
    private javax.swing.JRadioButton rdAbonoFactura;
    private javax.swing.JRadioButton rdAbonoGeneral;
    private javax.swing.JScrollPane scrFormulario;
    private javax.swing.JTable tblCartera;
    private javax.swing.JTable tblCuotas;
    private javax.swing.JTable tblRegistros;
    private javax.swing.JTextField txtAbonado;
    private javax.swing.JTextField txtAbonar;
    private javax.swing.JTextField txtBodega;
    private javax.swing.JTextField txtCelular;
    private javax.swing.JTextField txtCheque;
    private javax.swing.JTextField txtComprobante;
    private javax.swing.JTextField txtConseManual;
    private javax.swing.JTextField txtConsecutivo;
    private javax.swing.JTextField txtContrato;
    private javax.swing.JTextField txtCuotas;
    private javax.swing.JTextField txtCuotas1;
    private javax.swing.JTextField txtDescuentos;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtDocumentoFiltro;
    private javax.swing.JTextField txtEfectivo;
    private javax.swing.JTextField txtFactura;
    private javax.swing.JTextField txtFechaDesenvolso;
    private javax.swing.JTextField txtFuente;
    private javax.swing.JTextField txtIca;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtInteres;
    private javax.swing.JTextField txtIva;
    private javax.swing.JTextField txtMes;
    private javax.swing.JTextField txtNC;
    private javax.swing.JTextField txtNit;
    private javax.swing.JLabel txtNoAbono;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNombre1;
    private javax.swing.JTextField txtNombreFiltro;
    private javax.swing.JTextField txtObservaciones;
    private javax.swing.JTextField txtOtro;
    private javax.swing.JTextField txtPendiente;
    private javax.swing.JTextField txtPlaca;
    private javax.swing.JTextField txtSaldo;
    private javax.swing.JTextField txtSaldoCuentas;
    private javax.swing.JTextField txtSaldoTotal;
    private javax.swing.JTextField txtTargeta;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtTipo;
    private javax.swing.JTextField txtTotalAbonos;
    private javax.swing.JTextField txtTotalIntereses;
    private javax.swing.JLabel txtTotalizado;
    private javax.swing.JTextField txtValor;
    private javax.swing.JTextField txtValorAprovado;
    private javax.swing.JTextField txtVendedor;
    private javax.swing.JTextField txtVendedorFiltro;
    // End of variables declaration//GEN-END:variables
}
