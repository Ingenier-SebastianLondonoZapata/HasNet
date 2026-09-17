package Vista.Cartera;

import Controlador.Alertas.ControladorAlertas;
import clases.Instancias;
import clases.big;
import clases.cambiarColorTabla;
import clases.metodosGenerales;
import Modelo.Cartera.AbonoCuenta;
import Modelo.Cartera.MediosPagoAbono;
import Modelo.Terceros.ModeloContacto;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class VistaPagos extends javax.swing.JInternalFrame {

    DefaultTableModel modeloPro;
    DefaultTableModel modelo;
    metodosGenerales metodos = new metodosGenerales();
    Instancias instancias;
    TableRowSorter modeloOrdenado;

    //Barra de titulo
    private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension dimBarra = null;

    //Manejo de lista de precios 
    ArrayList<String[]> precios = new ArrayList<>();
    ArrayList<String> codigos = new ArrayList<>();
    BigDecimal NC = null;
    String info, simbolo;
    Boolean cancelarPago = false;
    FuncionalidadPagos funcionalidadPagos;

    //Columnas de tblCartera
    private static final int COL_INGRESO = 0;
    private static final int COL_FACTURA = 1;
    private static final int COL_VALOR = 6;
    private static final int COL_SALDO = 7;
    private static final int COL_SELECCION = 10;
    private static final int COL_ABONADO = 11;
    private static final int COL_RESTANTE = 12;
    private static final int COL_ABONADO_ANTERIOR = 13;

    public VistaPagos() {
        initComponents();
        tblPagosPendientes.setDefaultRenderer(Object.class, new cambiarColorTabla(7, 0));
        instancias = Instancias.getInstancias();
        funcionalidadPagos = new FuncionalidadPagos(instancias);

        simbolo = instancias.getSimbolo();
        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
        dimBarra = Barra.getPreferredSize();
        Barra.setSize(0, 0);
        Barra.setPreferredSize(new Dimension(0, 0));
        setBorder(null);
        repaint();

        jTabbedPane1.setSelectedIndex(1);
        jTabbedPane1.setEnabledAt(0, false);
        jTabbedPane1.setEnabledAt(1, false);

        pnlInvisible.setVisible(false);
        txtIdSistema.setVisible(false);
        btnActualizarActionPerformed(null);
    }

    public Boolean getCancelarPago() {
        return cancelarPago;
    }

    public void setCancelarPago(Boolean cancelarPago) {
        this.cancelarPago = cancelarPago;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        scrFormulario = new javax.swing.JScrollPane();
        pnlFormulario = new javax.swing.JPanel();
        pnlDatos = new javax.swing.JPanel();
        lbFactura = new javax.swing.JLabel();
        txtFactura = new javax.swing.JTextField();
        lbNit = new javax.swing.JLabel();
        txtNit = new javax.swing.JTextField();
        lbNombre = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        lbValor = new javax.swing.JLabel();
        txtValor = new javax.swing.JTextField();
        lbFactura1 = new javax.swing.JLabel();
        txtIngreso = new javax.swing.JTextField();
        pnlAbonos = new javax.swing.JPanel();
        txtAbonado = new javax.swing.JTextField();
        lbAbonado = new javax.swing.JLabel();
        lbPendiente = new javax.swing.JLabel();
        txtPendiente = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        lbEfectivo = new javax.swing.JLabel();
        txtEfectivo = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        lbCheque = new javax.swing.JLabel();
        txtCheque = new javax.swing.JTextField();
        lbTargeta = new javax.swing.JLabel();
        txtTargeta = new javax.swing.JTextField();
        lbTotalAbonos = new javax.swing.JLabel();
        txtTotalAbonos = new javax.swing.JTextField();
        lbComprobante = new javax.swing.JLabel();
        txtComprobante = new javax.swing.JTextField();
        lbAbono = new javax.swing.JLabel();
        txtNoAbono = new javax.swing.JLabel();
        txtFuente = new javax.swing.JTextField();
        lbCheque1 = new javax.swing.JLabel();
        txtIva = new javax.swing.JTextField();
        lbNC1 = new javax.swing.JLabel();
        lbEfectivo1 = new javax.swing.JLabel();
        lbTargeta1 = new javax.swing.JLabel();
        txtIca = new javax.swing.JTextField();
        txtOtro = new javax.swing.JTextField();
        lbNC2 = new javax.swing.JLabel();
        txtDescFinanciero = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        rdAbonoFactura = new javax.swing.JRadioButton();
        rdAbonoGeneral = new javax.swing.JRadioButton();
        pnlSaldo = new javax.swing.JPanel();
        txtSaldo = new javax.swing.JTextField();
        lbNit24 = new javax.swing.JLabel();
        lbNit25 = new javax.swing.JLabel();
        txtSaldo1 = new javax.swing.JTextField();
        lbNit26 = new javax.swing.JLabel();
        txtSaldoCuentas = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblCartera = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        btnGuardarPago = new javax.swing.JButton();
        btnVolver = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPagosPendientes = new javax.swing.JTable();
        btnActualizar = new javax.swing.JButton();
        btnVerMas = new javax.swing.JButton();
        btnAnular = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        lbNit5 = new javax.swing.JLabel();
        txtBuscadorIngreso = new javax.swing.JTextField();
        txtBuscadorFactura = new javax.swing.JTextField();
        lbNit2 = new javax.swing.JLabel();
        lbNit3 = new javax.swing.JLabel();
        txtBuscadorDocumento = new javax.swing.JTextField();
        lbNit4 = new javax.swing.JLabel();
        txtBuscadorNombre = new javax.swing.JTextField();
        pnlInvisible = new javax.swing.JPanel();
        txtIdSistema = new javax.swing.JTextField();
        cmbListas = new javax.swing.JComboBox();

        setTitle("Factura");

        pnlFormulario.setBackground(new java.awt.Color(255, 255, 255));

        pnlDatos.setBackground(new java.awt.Color(255, 255, 255));
        pnlDatos.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos factura", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 14))); // NOI18N

        lbFactura.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbFactura.setText("No. Factura:");

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

        lbNit.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbNit.setText("CC/NIT:");

        txtNit.setEditable(false);
        txtNit.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtNit.setName("CC/NIT"); // NOI18N
        txtNit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNitActionPerformed(evt);
            }
        });
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

        lbFactura1.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbFactura1.setText("No. Ingreso:");

        txtIngreso.setEditable(false);
        txtIngreso.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtIngreso.setName("CC/NIT"); // NOI18N
        txtIngreso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIngresoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIngresoKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout pnlDatosLayout = new javax.swing.GroupLayout(pnlDatos);
        pnlDatos.setLayout(pnlDatosLayout);
        pnlDatosLayout.setHorizontalGroup(
            pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbFactura1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbValor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbNit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbFactura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtValor, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
                    .addComponent(txtNombre)
                    .addComponent(txtNit)
                    .addComponent(txtFactura)
                    .addComponent(txtIngreso))
                .addContainerGap())
        );
        pnlDatosLayout.setVerticalGroup(
            pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatosLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbFactura1)
                    .addComponent(txtIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbFactura)
                    .addComponent(txtFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
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
                .addGap(5, 5, 5))
        );

        pnlAbonos.setBackground(new java.awt.Color(255, 255, 255));
        pnlAbonos.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Abonos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 18))); // NOI18N

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
        lbAbonado.setText("Abonado:");

        lbPendiente.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbPendiente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbPendiente.setText("Pendiente:");

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

        lbEfectivo.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbEfectivo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbEfectivo.setText("Efectivo");

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

        lbCheque.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbCheque.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCheque.setText("Cheque");

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

        lbTotalAbonos.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbTotalAbonos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTotalAbonos.setText("TOTAL ABONOS");

        txtTotalAbonos.setEditable(false);
        txtTotalAbonos.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
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

        lbComprobante.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbComprobante.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbComprobante.setText("Numero Comprobante");

        txtComprobante.setName("Comprobante"); // NOI18N
        txtComprobante.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtComprobanteKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtComprobanteKeyTyped(evt);
            }
        });

        lbAbono.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbAbono.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbAbono.setText("Pago No.");

        txtNoAbono.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtNoAbono.setForeground(new java.awt.Color(255, 0, 0));
        txtNoAbono.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtNoAbono.setText("3");
        txtNoAbono.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtFuente.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtFuente.setName("Rte.Fuente"); // NOI18N
        txtFuente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFuenteKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFuenteKeyTyped(evt);
            }
        });

        lbCheque1.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbCheque1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCheque1.setText("Rte.Ica");

        txtIva.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtIva.setName("Rte.Iva"); // NOI18N
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

        lbNC1.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbNC1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNC1.setText("Descuento Pago");

        lbEfectivo1.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbEfectivo1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbEfectivo1.setText("Rte.Iva");

        lbTargeta1.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTargeta1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTargeta1.setText("Rte.Fuente");

        txtIca.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtIca.setName("Rte.Ica"); // NOI18N
        txtIca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIcaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIcaKeyTyped(evt);
            }
        });

        txtOtro.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtOtro.setName("Otros"); // NOI18N
        txtOtro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtOtroKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtOtroKeyTyped(evt);
            }
        });

        lbNC2.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbNC2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNC2.setText("Desc Financiero");

        txtDescFinanciero.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtDescFinanciero.setName("Otros"); // NOI18N
        txtDescFinanciero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescFinancieroActionPerformed(evt);
            }
        });
        txtDescFinanciero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDescFinancieroKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDescFinancieroKeyTyped(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        rdAbonoFactura.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdAbonoFactura);
        rdAbonoFactura.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
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
        buttonGroup1.add(rdAbonoGeneral);
        rdAbonoGeneral.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
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

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rdAbonoFactura)
                .addGap(12, 12, 12)
                .addComponent(rdAbonoGeneral)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdAbonoFactura)
                    .addComponent(rdAbonoGeneral))
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout pnlAbonosLayout = new javax.swing.GroupLayout(pnlAbonos);
        pnlAbonos.setLayout(pnlAbonosLayout);
        pnlAbonosLayout.setHorizontalGroup(
            pnlAbonosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2)
            .addComponent(jSeparator1)
            .addGroup(pnlAbonosLayout.createSequentialGroup()
                .addGroup(pnlAbonosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlAbonosLayout.createSequentialGroup()
                        .addComponent(lbTotalAbonos, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTotalAbonos))
                    .addGroup(pnlAbonosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlAbonosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlAbonosLayout.createSequentialGroup()
                                .addComponent(lbComprobante)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbAbono, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNoAbono, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlAbonosLayout.createSequentialGroup()
                                .addGroup(pnlAbonosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(pnlAbonosLayout.createSequentialGroup()
                                        .addGroup(pnlAbonosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtIva)
                                            .addComponent(lbEfectivo1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(pnlAbonosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtIca)
                                            .addComponent(lbCheque1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(pnlAbonosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtFuente)
                                            .addComponent(lbTargeta1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(5, 5, 5)
                                        .addGroup(pnlAbonosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtOtro)
                                            .addComponent(lbNC1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(pnlAbonosLayout.createSequentialGroup()
                                        .addGroup(pnlAbonosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtEfectivo)
                                            .addComponent(lbEfectivo, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(pnlAbonosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtCheque)
                                            .addComponent(lbCheque, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(pnlAbonosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(pnlAbonosLayout.createSequentialGroup()
                                                .addComponent(lbTargeta, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(5, 5, 5))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAbonosLayout.createSequentialGroup()
                                                .addComponent(txtTargeta, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                        .addGroup(pnlAbonosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtDescFinanciero)
                                            .addComponent(lbNC2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
            .addGroup(pnlAbonosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlAbonosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtAbonado)
                    .addComponent(lbAbonado, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlAbonosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbPendiente, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPendiente, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlAbonosLayout.setVerticalGroup(
            pnlAbonosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAbonosLayout.createSequentialGroup()
                .addGroup(pnlAbonosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlAbonosLayout.createSequentialGroup()
                        .addComponent(lbAbonado)
                        .addGap(4, 4, 4)
                        .addComponent(txtAbonado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlAbonosLayout.createSequentialGroup()
                        .addComponent(lbPendiente)
                        .addGap(4, 4, 4)
                        .addComponent(txtPendiente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(pnlAbonosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNC2)
                    .addComponent(lbTargeta)
                    .addComponent(lbCheque)
                    .addComponent(lbEfectivo))
                .addGap(0, 0, 0)
                .addGroup(pnlAbonosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDescFinanciero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTargeta)
                    .addComponent(txtCheque)
                    .addComponent(txtEfectivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(pnlAbonosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlAbonosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(lbEfectivo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbCheque1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbTargeta1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(lbNC1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(pnlAbonosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtOtro)
                    .addComponent(txtFuente)
                    .addComponent(txtIca)
                    .addComponent(txtIva))
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlAbonosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbTotalAbonos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTotalAbonos, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlAbonosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtComprobante, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlAbonosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbAbono)
                        .addComponent(txtNoAbono, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbComprobante, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pnlSaldo.setBackground(new java.awt.Color(255, 255, 255));
        pnlSaldo.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        txtSaldo.setEditable(false);
        txtSaldo.setFont(new java.awt.Font("Century Gothic", 0, 36)); // NOI18N
        txtSaldo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSaldo.setName("CC/NIT"); // NOI18N
        txtSaldo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSaldoActionPerformed(evt);
            }
        });
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
        lbNit24.setText("Saldo total");

        lbNit25.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lbNit25.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNit25.setText("Saldo");

        txtSaldo1.setEditable(false);
        txtSaldo1.setFont(new java.awt.Font("Century Gothic", 0, 36)); // NOI18N
        txtSaldo1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSaldo1.setName("CC/NIT"); // NOI18N
        txtSaldo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSaldo1ActionPerformed(evt);
            }
        });
        txtSaldo1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSaldo1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSaldo1KeyTyped(evt);
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
                .addGap(24, 24, 24)
                .addGroup(pnlSaldoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSaldoCuentas)
                    .addComponent(lbNit26, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
                    .addComponent(txtSaldo, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbNit24, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtSaldo1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbNit25, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(28, 28, 28))
        );
        pnlSaldoLayout.setVerticalGroup(
            pnlSaldoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSaldoLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(lbNit25, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSaldo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(lbNit24, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbNit26)
                .addGap(3, 3, 3)
                .addComponent(txtSaldoCuentas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        tblCartera.setRowHeight(25);
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

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        btnGuardarPago.setBackground(new java.awt.Color(46, 204, 113));
        btnGuardarPago.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        btnGuardarPago.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
        btnGuardarPago.setText("GUARDAR ABONO");
        btnGuardarPago.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardarPago.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnGuardarPago.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnGuardarPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarPagoActionPerformed(evt);
            }
        });

        btnVolver.setBackground(new java.awt.Color(255, 255, 255));
        btnVolver.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        btnVolver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/anterior.png"))); // NOI18N
        btnVolver.setText("VOLVER ");
        btnVolver.setToolTipText("Ctrl+G");
        btnVolver.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVolver.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnVolver.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnVolver)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardarPago)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnVolver, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnGuardarPago, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout pnlFormularioLayout = new javax.swing.GroupLayout(pnlFormulario);
        pnlFormulario.setLayout(pnlFormularioLayout);
        pnlFormularioLayout.setHorizontalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlDatos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlAbonos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 26, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFormularioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        pnlFormularioLayout.setVerticalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlFormularioLayout.createSequentialGroup()
                        .addComponent(pnlAbonos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlFormularioLayout.createSequentialGroup()
                        .addComponent(pnlDatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                .addGap(13, 13, 13))
        );

        scrFormulario.setViewportView(pnlFormulario);

        jTabbedPane1.addTab("VISTA PREVIA", scrFormulario);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        tblPagosPendientes.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        tblPagosPendientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "INGRESO", "FACTURA", "NIT", "NOM. PROVEEDOR", "FECHA DE FACTURA", "VALOR"
            }
        ));
        tblPagosPendientes.setRowHeight(24);
        tblPagosPendientes.getTableHeader().setReorderingAllowed(false);
        tblPagosPendientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPagosPendientesMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tblPagosPendientesMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblPagosPendientesMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblPagosPendientesMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblPagosPendientes);

        btnActualizar.setBackground(new java.awt.Color(93, 173, 226));
        btnActualizar.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/actualizar.png"))); // NOI18N
        btnActualizar.setText("Actualizar pagos pendientes");
        btnActualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnActualizar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnActualizar.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnVerMas.setBackground(new java.awt.Color(204, 204, 204));
        btnVerMas.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnVerMas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar.png"))); // NOI18N
        btnVerMas.setText("Ver más");
        btnVerMas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVerMas.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnVerMas.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnVerMas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerMasActionPerformed(evt);
            }
        });

        btnAnular.setBackground(new java.awt.Color(241, 148, 138));
        btnAnular.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnAnular.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/borrar2.png"))); // NOI18N
        btnAnular.setText("Anular pago");
        btnAnular.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAnular.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnAnular.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnAnular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnularActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Filtros", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 14))); // NOI18N

        lbNit5.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbNit5.setText("Número ingreso:");

        txtBuscadorIngreso.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtBuscadorIngreso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscadorIngresoKeyReleased(evt);
            }
        });

        txtBuscadorFactura.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtBuscadorFactura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscadorFacturaKeyReleased(evt);
            }
        });

        lbNit2.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbNit2.setText("Factura:");

        lbNit3.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbNit3.setText("Nit:");

        txtBuscadorDocumento.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtBuscadorDocumento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscadorDocumentoKeyReleased(evt);
            }
        });

        lbNit4.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbNit4.setText("Nombre:");

        txtBuscadorNombre.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtBuscadorNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscadorNombreKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbNit5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBuscadorIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbNit2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBuscadorFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbNit3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBuscadorDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbNit4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtBuscadorNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(lbNit4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtBuscadorIngreso, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lbNit5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtBuscadorFactura, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbNit2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lbNit3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtBuscadorDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtBuscadorNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(5, 5, 5))))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(btnAnular, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnVerMas, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                        .addComponent(btnAnular, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(btnVerMas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(11, 11, 11))
        );

        jTabbedPane1.addTab("LISTA", jPanel2);

        txtIdSistema.setEditable(false);
        txtIdSistema.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtIdSistema.setName("CC/NIT"); // NOI18N
        txtIdSistema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdSistemaActionPerformed(evt);
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

        cmbListas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "L1", "L2", "L3", "L4" }));

        javax.swing.GroupLayout pnlInvisibleLayout = new javax.swing.GroupLayout(pnlInvisible);
        pnlInvisible.setLayout(pnlInvisibleLayout);
        pnlInvisibleLayout.setHorizontalGroup(
            pnlInvisibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInvisibleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtIdSistema, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbListas, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlInvisibleLayout.setVerticalGroup(
            pnlInvisibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInvisibleLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(pnlInvisibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmbListas)
                    .addComponent(txtIdSistema))
                .addGap(3, 3, 3))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlInvisible, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.LEADING))
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

    private void tblPagosPendientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPagosPendientesMouseClicked
        txtBuscadorIngreso.setText(((String) tblPagosPendientes.getValueAt(tblPagosPendientes.getSelectedRow(), 0)).replaceAll("ING-", ""));
        txtBuscadorFactura.setText(((String) tblPagosPendientes.getValueAt(tblPagosPendientes.getSelectedRow(), 1)).replaceAll("FACT-", ""));

        if (evt.getClickCount() == 2) {
            btnVerMasActionPerformed(null);
        }
    }//GEN-LAST:event_tblPagosPendientesMouseClicked

    private void txtFacturaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFacturaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFacturaKeyReleased

    private void txtFacturaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFacturaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFacturaKeyTyped

    private void txtNitKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNitKeyReleased
        txtFactura.setText(txtNit.getText() + "-" + (int) (Math.random() * 100 + 1));
    }//GEN-LAST:event_txtNitKeyReleased

    private void txtNitKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNitKeyTyped

    }//GEN-LAST:event_txtNitKeyTyped

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void txtNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreKeyReleased

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped

    }//GEN-LAST:event_txtNombreKeyTyped

    private void txtValorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValorKeyReleased

    private void txtValorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtValorKeyTyped

    private void txtAbonadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAbonadoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAbonadoKeyReleased

    private void txtAbonadoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAbonadoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAbonadoKeyTyped

    private void txtPendienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPendienteKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPendienteKeyReleased

    private void txtPendienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPendienteKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPendienteKeyTyped

    private void txtEfectivoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEfectivoKeyReleased
        if (txtEfectivo.getText().equals("") || txtEfectivo.getText().equals(this.simbolo) || txtEfectivo.getText().equals(this.simbolo + " ")) {
            txtEfectivo.setText("0");
        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtCheque.requestFocus();
        } else {
            txtEfectivo.setText(big.setMoneda(big.getMoneda(txtEfectivo.getText())));
            calcularSaldo(txtEfectivo);
        }
    }//GEN-LAST:event_txtEfectivoKeyReleased

    private void txtEfectivoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEfectivoKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtEfectivoKeyTyped

    private void txtChequeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtChequeKeyReleased
        if (txtCheque.getText().equals("") || txtCheque.getText().equals(this.simbolo) || txtCheque.getText().equals(this.simbolo + " ")
                || txtCheque.getText().equals("0") || txtCheque.getText().equals(this.simbolo + "0") || txtCheque.getText().equals(" 0")) {
            txtCheque.setText("0");
        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtTargeta.requestFocus();
        } else {
            txtCheque.setText(big.setMoneda(big.getMoneda(txtCheque.getText())));
            calcularSaldo(txtCheque);
        }
    }//GEN-LAST:event_txtChequeKeyReleased

    private void txtChequeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtChequeKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtChequeKeyTyped

    private void txtTargetaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTargetaKeyReleased
        if (txtTargeta.getText().equals("") || txtTargeta.getText().equals(this.simbolo) || txtTargeta.getText().equals(this.simbolo + " ")
                || txtTargeta.getText().equals("0") || txtTargeta.getText().equals(this.simbolo + "0") || txtTargeta.getText().equals(" 0")) {
            txtTargeta.setText("0");
        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtIva.requestFocus();
        } else {
            txtTargeta.setText(big.setMoneda(big.getMoneda(txtTargeta.getText())));

            calcularSaldo(txtTargeta);

        }
    }//GEN-LAST:event_txtTargetaKeyReleased

    private void txtTargetaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTargetaKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtTargetaKeyTyped

    private void txtTotalAbonosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalAbonosKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalAbonosKeyReleased

    private void txtTotalAbonosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalAbonosKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalAbonosKeyTyped

    private void txtComprobanteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtComprobanteKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtComprobanteKeyReleased

    private void txtComprobanteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtComprobanteKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtComprobanteKeyTyped

    private void txtSaldoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSaldoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSaldoKeyReleased

    private void txtSaldoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSaldoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSaldoKeyTyped

    private void txtEfectivoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtEfectivoFocusGained

    }//GEN-LAST:event_txtEfectivoFocusGained

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        txtNoAbono.setText((String) instancias.getSql().getNumConsecutivo("PAGO")[0]);
        cargarTablaCxp();
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnVerMasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerMasActionPerformed
        String ingreso = "ING-" + txtBuscadorIngreso.getText();
        boolean sw = false;
        int i = -1;

        for (i = 0; i < tblPagosPendientes.getRowCount(); i++) {
            if (((String) tblPagosPendientes.getValueAt(i, 0)).equals(ingreso)) {
                sw = true;
                break;
            }
        }

        if (sw) {
            txtIngreso.setText(tblPagosPendientes.getValueAt(i, 0).toString());
            txtFactura.setText(txtBuscadorFactura.getText());
            txtNit.setText((String) tblPagosPendientes.getValueAt(i, 2));
            txtNombre.setText((String) tblPagosPendientes.getValueAt(i, 3));
            txtValor.setText((String) tblPagosPendientes.getValueAt(i, 6));

            Object[][] abonos = instancias.getSql().getRegistrosPagos(ingreso);
            BigDecimal totalAbonos = big.getBigDecimal("0");

            for (Object[] abono : abonos) {
                totalAbonos = totalAbonos.add(big.getBigDecimal((abono[2])));
            }

            txtAbonado.setText(big.setMoneda(totalAbonos));
            txtPendiente.setText(big.setMoneda(big.getMoneda(txtValor.getText()).subtract(totalAbonos)));
            txtSaldo1.setText(txtPendiente.getText());
            txtSaldo.setText(txtPendiente.getText());
            txtTotalAbonos.setText(this.simbolo + " 0");
            txtEfectivo.setText(this.simbolo + " 0");
            txtCheque.setText(this.simbolo + " 0");
            txtTargeta.setText(this.simbolo + " 0");
            txtIca.setText(this.simbolo + " 0");
            txtIva.setText(this.simbolo + " 0");
            txtFuente.setText(this.simbolo + " 0");
            txtOtro.setText(this.simbolo + " 0");
            txtDescFinanciero.setText(this.simbolo + " 0");
            txtSaldoCuentas.setText(this.simbolo + " 0");

            txtComprobante.setText("");

            calcularSaldo(txtOtro);

            jTabbedPane1.setSelectedIndex(0);
            tblCartera.setModel(instancias.getSql().getRegistrosCxpPorProveedor((String) tblPagosPendientes.getValueAt(i, 2)));
            DefaultTableModel modeloCartera = (DefaultTableModel) tblCartera.getModel();
            modeloCartera.addColumn("Abonado");
            modeloCartera.addColumn("Restante");
            modeloCartera.addColumn("AbonadoAnterior");

            if (tblCartera.getColumnModel().getColumnCount() > 0) {
//                        if (instancias.isConsecutivosDiferentes() || instancias.isPrefijo()) {
//                            tblCartera.getColumnModel().getColumn(0).setMinWidth(0);
//                            tblCartera.getColumnModel().getColumn(0).setPreferredWidth(0);
//                            tblCartera.getColumnModel().getColumn(0).setMaxWidth(0);
//                        } else {
//                            tblCartera.getColumnModel().getColumn(1).setMinWidth(0);
//                            tblCartera.getColumnModel().getColumn(1).setPreferredWidth(0);
//                            tblCartera.getColumnModel().getColumn(1).setMaxWidth(0);
//                        }

//                tblCartera.getColumnModel().getColumn(8).setMinWidth(0);
//                tblCartera.getColumnModel().getColumn(8).setPreferredWidth(0);
//                tblCartera.getColumnModel().getColumn(8).setMaxWidth(0);
                tblCartera.getColumnModel().getColumn(13).setMinWidth(0);
                tblCartera.getColumnModel().getColumn(13).setPreferredWidth(0);
                tblCartera.getColumnModel().getColumn(13).setMaxWidth(0);
            }

            for (int j = 0; j < tblCartera.getRowCount(); j++) {
                tblCartera.setValueAt(metodos.fecha(tblCartera.getValueAt(j, 4).toString()), j, 4);
                tblCartera.setValueAt(metodos.fecha(tblCartera.getValueAt(j, 5).toString()), j, 5);
                tblCartera.setValueAt(big.setMonedaExacta(big.getBigDecimal(tblCartera.getValueAt(j, 6).toString())), j, 6);
                tblCartera.setValueAt(big.setMonedaExacta(big.getBigDecimal(tblCartera.getValueAt(j, 7).toString())), j, 7);
                tblCartera.setValueAt("", j, 11);
                tblCartera.setValueAt("", j, 12);
                tblCartera.setValueAt(big.setMonedaExacta(big.getMoneda(tblCartera.getValueAt(j, 6).toString()).
                        subtract(big.getMoneda(tblCartera.getValueAt(j, 7).toString()))), j, 13);
            }

            tblCartera.setVisible(true);
            rdAbonoFactura.setSelected(true);
            rdAbonoFactura.setEnabled(true);
            rdAbonoGeneral.setEnabled(true);
        } else {
            metodos.msgAdvertencia(this, "Factura no valida");
        }
    }//GEN-LAST:event_btnVerMasActionPerformed

    private void btnGuardarPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarPagoActionPerformed
        if (rdAbonoFactura.isSelected()) {
            guardar();
        } else if (rdAbonoGeneral.isSelected()) {
            guardarGeneral();
        }
    }//GEN-LAST:event_btnGuardarPagoActionPerformed

    private void tblPagosPendientesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPagosPendientesMouseEntered

    }//GEN-LAST:event_tblPagosPendientesMouseEntered

    private void tblPagosPendientesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPagosPendientesMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tblPagosPendientesMousePressed

    private void tblPagosPendientesMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPagosPendientesMouseReleased
        txtBuscadorIngreso.setText(((String) tblPagosPendientes.getValueAt(tblPagosPendientes.getSelectedRow(), 0)).replaceAll("ING-", ""));
        txtBuscadorFactura.setText(((String) tblPagosPendientes.getValueAt(tblPagosPendientes.getSelectedRow(), 1)).replaceAll("FACT-", ""));
    }//GEN-LAST:event_tblPagosPendientesMouseReleased

    private void txtFuenteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFuenteKeyReleased
        if (txtFuente.getText().equals("") || txtFuente.getText().equals(this.simbolo) || txtFuente.getText().equals(this.simbolo + " ")
                || txtFuente.getText().equals("0") || txtFuente.getText().equals(this.simbolo + "0") || txtFuente.getText().equals(" 0")) {
            txtFuente.setText("0");
        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtOtro.requestFocus();
        } else {
            txtFuente.setText(big.setMoneda(big.getMoneda(txtFuente.getText())));
            calcularSaldo(txtFuente);
        }
    }//GEN-LAST:event_txtFuenteKeyReleased

    private void txtFuenteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFuenteKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtFuenteKeyTyped

    private void txtIvaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtIvaFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIvaFocusGained

    private void txtIvaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIvaKeyReleased
        if (txtIva.getText().equals("") || txtIva.getText().equals(this.simbolo) || txtIva.getText().equals(this.simbolo + " ") || txtIva.getText().equals("0")
                || txtIva.getText().equals(this.simbolo + "0") || txtIva.getText().equals(" 0")) {
            txtIva.setText("0");
        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtIca.requestFocus();
        } else {
            txtIva.setText(big.setMoneda(big.getMoneda(txtIva.getText())));
            calcularSaldo(txtIva);
        }
    }//GEN-LAST:event_txtIvaKeyReleased

    private void txtIvaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIvaKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtIvaKeyTyped

    private void txtIcaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIcaKeyReleased
        if (txtIca.getText().equals("") || txtIca.getText().equals(this.simbolo) || txtIca.getText().equals(this.simbolo + " ") || txtIca.getText().equals("0")
                || txtIca.getText().equals(this.simbolo + "0") || txtIca.getText().equals(" 0")) {
            txtIca.setText("0");
        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtFuente.requestFocus();
        } else {
            txtIca.setText(big.setMoneda(big.getMoneda(txtIca.getText())));
            calcularSaldo(txtIca);
        }
    }//GEN-LAST:event_txtIcaKeyReleased

    private void txtIcaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIcaKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtIcaKeyTyped

    private void txtOtroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtOtroKeyReleased
        if (txtOtro.getText().equals("") || txtOtro.getText().equals(this.simbolo) || txtOtro.getText().equals(this.simbolo + " ") || txtOtro.getText().equals("0")
                || txtOtro.getText().equals(this.simbolo + "0") || txtOtro.getText().equals(" 0")) {
            txtOtro.setText("0");
        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtOtro.requestFocus();
        } else {
            txtOtro.setText(big.setMoneda(big.getMoneda(txtOtro.getText())));
            calcularSaldo(txtOtro);
        }
    }//GEN-LAST:event_txtOtroKeyReleased

    private void txtOtroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtOtroKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtOtroKeyTyped

    private void txtNitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNitActionPerformed

    private void txtSaldoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSaldoActionPerformed

    }//GEN-LAST:event_txtSaldoActionPerformed

    private void txtBuscadorDocumentoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscadorDocumentoKeyReleased
        modeloOrdenado.setRowFilter(RowFilter.regexFilter("(?i)" + txtBuscadorDocumento.getText(), 2));
        txtBuscadorFactura.setText("");
        txtBuscadorIngreso.setText("");
        txtBuscadorNombre.setText("");
    }//GEN-LAST:event_txtBuscadorDocumentoKeyReleased

    private void txtBuscadorNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscadorNombreKeyReleased
        modeloOrdenado.setRowFilter(RowFilter.regexFilter("(?i)" + txtBuscadorNombre.getText(), 3));
        txtBuscadorFactura.setText("");
        txtBuscadorIngreso.setText("");
        txtBuscadorDocumento.setText("");
    }//GEN-LAST:event_txtBuscadorNombreKeyReleased

    private void txtBuscadorFacturaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscadorFacturaKeyReleased
        modeloOrdenado.setRowFilter(RowFilter.regexFilter("(?i)" + txtBuscadorFactura.getText(), 1));
        txtBuscadorIngreso.setText("");
        txtBuscadorNombre.setText("");
        txtBuscadorDocumento.setText("");
    }//GEN-LAST:event_txtBuscadorFacturaKeyReleased

    private void txtBuscadorIngresoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscadorIngresoKeyReleased
        modeloOrdenado.setRowFilter(RowFilter.regexFilter("(?i)" + txtBuscadorIngreso.getText(), 0));
        txtBuscadorFactura.setText("");
        txtBuscadorNombre.setText("");
        txtBuscadorDocumento.setText("");

        try {
            int i;
            for (i = 0; i < tblPagosPendientes.getRowCount(); i++) {
                if (tblPagosPendientes.getValueAt(i, 0).equals(txtIngreso.getText())) {
                    tblPagosPendientes.setColumnSelectionInterval(0, 0);
                    tblPagosPendientes.setRowSelectionInterval(i, i);
                    tblPagosPendientes.getSelectionModel().setSelectionInterval(i, i);
                    tblPagosPendientes.scrollRectToVisible(new Rectangle(tblPagosPendientes.getCellRect(i, 0, true)));
                    break;
                }
            }

            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                btnVerMasActionPerformed(null);
            }
        } catch (Exception e) {

        }
    }//GEN-LAST:event_txtBuscadorIngresoKeyReleased

    private void txtIngresoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIngresoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIngresoKeyReleased

    private void txtIngresoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIngresoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIngresoKeyTyped

    private void txtSaldo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSaldo1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSaldo1ActionPerformed

    private void txtSaldo1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSaldo1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSaldo1KeyReleased

    private void txtSaldo1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSaldo1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSaldo1KeyTyped

    private void rdAbonoFacturaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdAbonoFacturaItemStateChanged
        seleccionarRadio();
    }//GEN-LAST:event_rdAbonoFacturaItemStateChanged

    private void rdAbonoFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdAbonoFacturaActionPerformed
        if (rdAbonoFactura.isSelected()) {
            for (int i = 0; i < tblCartera.getRowCount(); i++) {
                tblCartera.setValueAt(false, i, COL_SELECCION);
            }
            limpiarDistribucion();
            txtSaldoCuentas.setText(big.setMoneda(BigDecimal.ZERO));
        }
    }//GEN-LAST:event_rdAbonoFacturaActionPerformed

    private void rdAbonoGeneralItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdAbonoGeneralItemStateChanged
        seleccionarRadio();
    }//GEN-LAST:event_rdAbonoGeneralItemStateChanged

    private void tblCarteraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCarteraMouseClicked
        calcularSaldo(txtEfectivo);
        actualizarSaldoCuentasSeleccionadas();
    }//GEN-LAST:event_tblCarteraMouseClicked

    private void tblCarteraMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCarteraMouseEntered
        calcularSaldo(txtEfectivo);
        actualizarSaldoCuentasSeleccionadas();
    }//GEN-LAST:event_tblCarteraMouseEntered

    private void tblCarteraMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCarteraMousePressed
        calcularSaldo(txtEfectivo);
        actualizarSaldoCuentasSeleccionadas();
    }//GEN-LAST:event_tblCarteraMousePressed

    private void rdAbonoGeneralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdAbonoGeneralActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdAbonoGeneralActionPerformed

    private void btnAnularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnularActionPerformed
        String consecutivo = "PAGO-" + metodos.msgIngresarEnter(this, "Pago a anular");

        if (consecutivo.equals("PAGO-")) {
            return;
        }

        String anulado = "";
        String estadoCuenta = "";
        String egreso = "";
        String id = "";

        try {
            anulado = instancias.getSql().getEstadoPago("Where recibo = '" + consecutivo + "' ");
        } catch (Exception e) {
            metodos.msgError(this, "El pago no existe");
            return;
        }

        if (anulado.equals("ANULADO")) {
            metodos.msgAdvertencia(this, "El documento esta anulado");
            return;
        }

        instancias.getSql().anularDocumento(consecutivo, "bdIngreso");

        try {
            egreso = instancias.getSql().getIdEgreso("Where ingresoAsociado ='" + consecutivo + "' ");
        } catch (Exception e) {
        }

        String mensaje = "";
        if (egreso != "") {
            mensaje = "Anular el pago y egreso #" + egreso + "? ";
        } else {
            mensaje = "¿Desea anular el pago?";
        }

        if (metodos.msgPregunta(this, mensaje) == 0) {

            id = instancias.getSql().getIdCxp("Where recibo ='" + consecutivo + "' ");
            estadoCuenta = instancias.getSql().getEstadoPago("Where ingreso = '" + id + "' and tipo <> 'PAGO' ");

            if (!estadoCuenta.equals("PEND")) {
                instancias.getSql().modificarRegistroCxp(" where ingreso = '" + id + "' and tipo <> 'PAGO' ");
            }

            instancias.getSql().modificarRegistroCxp1(consecutivo, "ANULADO");

            try {
                if (!instancias.getSql().anularDocumento(egreso, "bdEgreso")) {
                    metodos.msgError(this, "Hubo un problema al anular el egreso");
                    return;
                }
            } catch (Exception e) {
            }

            metodos.msgExito(this, "Pago anulado con éxito");
            cargarTablaCxp();
        }
    }//GEN-LAST:event_btnAnularActionPerformed

    private void txtSaldoCuentasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSaldoCuentasKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSaldoCuentasKeyReleased

    private void txtSaldoCuentasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSaldoCuentasKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSaldoCuentasKeyTyped

    private void txtDescFinancieroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescFinancieroKeyReleased
        if (txtDescFinanciero.getText().equals("") || txtDescFinanciero.getText().equals(this.simbolo) || txtDescFinanciero.getText().equals(this.simbolo + " ")
                || txtDescFinanciero.getText().equals("0") || txtDescFinanciero.getText().equals(this.simbolo + "0") || txtDescFinanciero.getText().equals(" 0")) {
            txtDescFinanciero.setText("0");
        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtDescFinanciero.requestFocus();
        } else {
            txtDescFinanciero.setText(big.setMoneda(big.getMoneda(txtDescFinanciero.getText())));
            calcularSaldo(txtDescFinanciero);
        }
    }//GEN-LAST:event_txtDescFinancieroKeyReleased

    private void txtDescFinancieroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescFinancieroKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtDescFinancieroKeyTyped

    private void txtDescFinancieroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescFinancieroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescFinancieroActionPerformed

    private void txtIdSistemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdSistemaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdSistemaActionPerformed

    private void txtIdSistemaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdSistemaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdSistemaKeyReleased

    private void txtIdSistemaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdSistemaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdSistemaKeyTyped

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        instancias.getMenu().cambiarTitulo("ABONOS CUENTAS POR PAGAR");
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_btnVolverActionPerformed

    /**
     * Lee un campo de valor del formulario. Un campo vacío o con un contenido
     * no numérico vale cero.
     */
    private BigDecimal getValorCampo(JTextField campo) {
        try {
            return big.getMoneda(campo.getText());
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }

    /**
     * Medios de pago, retenciones y descuentos digitados en el formulario.
     */
    private MediosPagoAbono getMediosPago() {
        return new MediosPagoAbono(getValorCampo(txtEfectivo), getValorCampo(txtCheque), getValorCampo(txtTargeta),
                BigDecimal.ZERO, getValorCampo(txtIva), getValorCampo(txtIca), getValorCampo(txtFuente),
                getValorCampo(txtOtro), getValorCampo(txtDescFinanciero));
    }

    private String getComprobante() {
        String comprobante = txtComprobante.getText();
        return comprobante.equals("") ? "0" : comprobante;
    }

    private void finalizarPago(String mensaje) {
        metodos.msgExito(this, mensaje);
        jTabbedPane1.setSelectedIndex(1);
        btnActualizarActionPerformed(null);
    }

    public BigDecimal acumularValores() {
        return getMediosPago().getTotalAbono();
    }

    public void guardar() {
        if (txtAbonado.getText().equals(txtTotalAbonos.getText())) {
            ControladorAlertas.alert("Ingrese el método de pago");
            return;
        }

        cancelarPago = false;

        MediosPagoAbono medios = getMediosPago();
        BigDecimal abonadoAnterior = big.getMoneda(txtAbonado.getText());
        BigDecimal valorAbono = big.getMoneda(txtTotalAbonos.getText()).subtract(abonadoAnterior);
        BigDecimal saldoRestante = big.getMoneda(txtSaldo.getText());

        List<AbonoCuenta> cuentas = new ArrayList<>();
        cuentas.add(new AbonoCuenta(txtIngreso.getText(), txtFactura.getText(), big.getMoneda(txtValor.getText()),
                abonadoAnterior, valorAbono, saldoRestante));

        String pago = funcionalidadPagos.obtenerConsecutivoPago();

        funcionalidadPagos.registrarEgreso(txtNit.getText(), medios.getTotalEgreso(false),
                funcionalidadPagos.unirDocumentos(cuentas), funcionalidadPagos.obtenerConcepto(saldoRestante),
                "PAGO-" + txtNoAbono.getText(), medios);

        if (cancelarPago) {
            return;
        }

        ModeloContacto nodo = instancias.getSql().getDatosTercero(txtNit.getText());

        if (!funcionalidadPagos.registrarPago(pago, nodo.getIdSistema(), getComprobante(), cuentas, medios,
                txtIngreso.getText())) {
            return;
        }

        txtNoAbono.setText((String) instancias.getSql().getNumConsecutivo("PAGO")[0]);
        funcionalidadPagos.aumentarConsecutivo("PAGO");

        finalizarPago("Pago Exitoso");
    }

    private boolean carteraCargada() {
        return tblCartera.getColumnCount() > COL_ABONADO_ANTERIOR;
    }

    private boolean estaSeleccionada(int fila) {
        Object seleccion = tblCartera.getValueAt(fila, COL_SELECCION);
        return seleccion instanceof Boolean && (Boolean) seleccion;
    }

    private BigDecimal getValorCelda(int fila, int columna) {
        Object valor = tblCartera.getValueAt(fila, columna);

        if (valor == null || valor.toString().trim().isEmpty()) {
            return BigDecimal.ZERO;
        }

        try {
            return big.getMoneda(valor.toString());
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }

    private boolean tieneAbono(int fila) {
        return getValorCelda(fila, COL_ABONADO).compareTo(BigDecimal.ZERO) > 0;
    }

    private boolean haySeleccion() {
        if (!carteraCargada()) {
            return false;
        }

        for (int i = 0; i < tblCartera.getRowCount(); i++) {
            if (estaSeleccionada(i)) {
                return true;
            }
        }
        return false;
    }

    private void limpiarDistribucion() {
        if (!carteraCargada()) {
            return;
        }

        for (int i = 0; i < tblCartera.getRowCount(); i++) {
            tblCartera.setValueAt("", i, COL_ABONADO);
            tblCartera.setValueAt("", i, COL_RESTANTE);
        }
    }

    public void actualizarSaldoCuentasSeleccionadas() {
        if (!carteraCargada()) {
            return;
        }

        BigDecimal total = BigDecimal.ZERO;

        for (int i = 0; i < tblCartera.getRowCount(); i++) {
            if (estaSeleccionada(i)) {
                total = total.add(getValorCelda(i, COL_SALDO));
            }
        }

        txtSaldoCuentas.setText(big.setMoneda(total));
    }

    private BigDecimal distribuirAbonoGeneral(BigDecimal dineroDisponible) {
        limpiarDistribucion();

        if (!haySeleccion()) {
            return dineroDisponible;
        }

        BigDecimal disponible = dineroDisponible;
        disponible = repartirDinero(disponible, true);
        disponible = repartirDinero(disponible, false);

        return disponible;
    }

    private BigDecimal repartirDinero(BigDecimal dineroDisponible, boolean seleccionadas) {
        BigDecimal disponible = dineroDisponible;

        for (int i = 0; i < tblCartera.getRowCount() && disponible.compareTo(BigDecimal.ZERO) > 0; i++) {
            if (estaSeleccionada(i) != seleccionadas) {
                continue;
            }

            BigDecimal saldo = getValorCelda(i, COL_SALDO);

            if (saldo.compareTo(BigDecimal.ZERO) <= 0) {
                continue;
            }

            BigDecimal abono = disponible.min(saldo);

            tblCartera.setValueAt(big.setMonedaExacta(abono), i, COL_ABONADO);
            tblCartera.setValueAt(big.setMonedaExacta(saldo.subtract(abono)), i, COL_RESTANTE);

            disponible = disponible.subtract(abono);
        }

        return disponible;
    }

    private List<AbonoCuenta> getCuentasConAbono() {
        List<AbonoCuenta> cuentas = new ArrayList<>();

        for (int i = 0; i < tblCartera.getRowCount(); i++) {
            if (!tieneAbono(i)) {
                continue;
            }

            cuentas.add(new AbonoCuenta(
                    tblCartera.getValueAt(i, COL_INGRESO).toString(),
                    tblCartera.getValueAt(i, COL_FACTURA).toString(),
                    getValorCelda(i, COL_VALOR),
                    getValorCelda(i, COL_ABONADO_ANTERIOR),
                    getValorCelda(i, COL_ABONADO),
                    getValorCelda(i, COL_RESTANTE)));
        }

        return cuentas;
    }

    public void guardarGeneral() {

        cancelarPago = false;

        if (!haySeleccion()) {
            ControladorAlertas.alert("Seleccione una factura para pagar");
            return;
        }

        BigDecimal dineroDisponible = acumularValores();

        if (dineroDisponible.compareTo(BigDecimal.ZERO) <= 0) {
            ControladorAlertas.alert("Ingrese el método de pago");
            return;
        }

        BigDecimal sinAplicar = distribuirAbonoGeneral(dineroDisponible);
        actualizarSaldoCuentasSeleccionadas();

        if (sinAplicar.compareTo(BigDecimal.ZERO) > 0) {
            ControladorAlertas.alertFail("No se puede superar el saldo pendiente");
            return;
        }

        List<AbonoCuenta> cuentas = getCuentasConAbono();

        if (cuentas.isEmpty()) {
            ControladorAlertas.alertFail("No se pudo distribuir el valor");
            return;
        }

        MediosPagoAbono medios = getMediosPago();
        String pago = funcionalidadPagos.obtenerConsecutivoPago();
        ModeloContacto nodo = instancias.getSql().getDatosTercero(txtNit.getText());

        funcionalidadPagos.registrarEgreso(nodo.getIdSistema(), medios.getTotalEgreso(true),
                funcionalidadPagos.unirDocumentos(cuentas),
                funcionalidadPagos.obtenerConcepto(big.getMoneda(txtSaldo.getText())),
                "PAGO-" + txtNoAbono.getText(), medios);

        if (cancelarPago) {
            return;
        }

        if (!funcionalidadPagos.registrarPago(pago, nodo.getIdSistema(), getComprobante(), cuentas, medios,
                txtIngreso.getText())) {
            return;
        }

        funcionalidadPagos.aumentarConsecutivo("PAGO");
        funcionalidadPagos.aumentarConsecutivo("ABONOGENERAL");
        finalizarPago("Pago exitoso.");
    }

    public void calcularSaldo(JTextField textbox) {

        if (rdAbonoFactura.isSelected()) {
            if (big.getMoneda(textbox.getText()).compareTo(big.getMoneda(txtPendiente.getText())) == 1) {
                ControladorAlertas.alert("No se puede superar el saldo pendiente");
                textbox.setText(this.simbolo + " 0");
                txtTotalAbonos.setText(txtAbonado.getText());
                textbox.requestFocus();
            } else if (acumularValores().compareTo(big.getMoneda(txtPendiente.getText())) == 1) {
                ControladorAlertas.alert("No se puede superar el saldo pendiente");
                textbox.setText(this.simbolo + " 0");
                txtTotalAbonos.setText(txtAbonado.getText());
                textbox.requestFocus();
            } else {
                BigDecimal abonoActual = acumularValores();
                txtTotalAbonos.setText(big.setMoneda(big.getMoneda(txtAbonado.getText()).add(abonoActual)));
                txtSaldo.setText(big.setMoneda(big.getMoneda(txtValor.getText()).subtract(big.getMoneda(txtTotalAbonos.getText()))));
            }
        } else {
            BigDecimal dineroDisponible = acumularValores();
            BigDecimal saldoTotalCuentas = big.getMoneda(txtSaldo1.getText());

            if (dineroDisponible.compareTo(saldoTotalCuentas) > 0) {
                lbEfectivo.requestFocus();
                ControladorAlertas.alert("No se puede superar el saldo pendiente");
                textbox.setText(this.simbolo + " 0");
                limpiarDistribucion();
            } else {
                distribuirAbonoGeneral(dineroDisponible);
            }

            txtSaldo.setText(big.setMoneda(big.getMoneda(txtSaldo1.getText()).subtract(acumularValores())));
        }
    }

    public void seleccionarRadio() {
        if (rdAbonoGeneral.isSelected()) {

            BigDecimal suma = big.getBigDecimal("0");
            if (carteraCargada()) {
                for (int i = 0; i < tblCartera.getRowCount(); i++) {
                    suma = suma.add(getValorCelda(i, COL_SALDO));
                }
            }
            txtSaldo.setText(big.setMonedaExacta(suma));
            txtSaldo1.setText(big.setMonedaExacta(suma));
            txtNoAbono.setText("" + (String) instancias.getSql().getNumConsecutivo("ABONOGENERAL")[0]);
        } else if (rdAbonoFactura.isSelected()) {
            txtSaldo.setText(txtPendiente.getText());
            txtSaldo1.setText(txtPendiente.getText());
            txtNoAbono.setText("" + (String) instancias.getSql().getNumConsecutivo("ABONO")[0]);

        }
        calcularSaldo(txtEfectivo);
    }

    public void setDatosEmpresa(String info) {
        this.info = info;
    }

    public void cargarTablaCxp() {
        modelo = (DefaultTableModel) tblPagosPendientes.getModel();

        while (tblPagosPendientes.getRowCount() > 0) {
            modelo.removeRow(0);
        }

        tblPagosPendientes.setModel(instancias.getSql().getRegistrosCxp());

        modelo = (DefaultTableModel) tblPagosPendientes.getModel();
        modeloOrdenado = new TableRowSorter<>(modelo);
        tblPagosPendientes.setRowSorter(modeloOrdenado);

        tblPagosPendientes.getColumnModel().getColumn(3).setMinWidth(200);
        tblPagosPendientes.getColumnModel().getColumn(3).setPreferredWidth(300);

        for (int i = 0; i < tblPagosPendientes.getRowCount(); i++) {
            //tblPagosPendientes.setValueAt(((String) tblPagosPendientes.getValueAt(i, 0)).replace("ING-", ""), i, 0);
            tblPagosPendientes.setValueAt(metodos.fecha((String) tblPagosPendientes.getValueAt(i, 4)), i, 4);
            tblPagosPendientes.setValueAt(metodos.fecha((String) tblPagosPendientes.getValueAt(i, 5)), i, 5);
            tblPagosPendientes.setValueAt(big.setMoneda(big.getBigDecimal((String) tblPagosPendientes.getValueAt(i, 6))), i, 6);
            tblPagosPendientes.setValueAt(big.setMoneda(big.getBigDecimal((String) tblPagosPendientes.getValueAt(i, 7))), i, 7);
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAnular;
    private javax.swing.JButton btnGuardarPago;
    private javax.swing.JButton btnVerMas;
    private javax.swing.JButton btnVolver;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cmbListas;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lbAbonado;
    private javax.swing.JLabel lbAbono;
    private javax.swing.JLabel lbCheque;
    private javax.swing.JLabel lbCheque1;
    private javax.swing.JLabel lbComprobante;
    private javax.swing.JLabel lbEfectivo;
    private javax.swing.JLabel lbEfectivo1;
    private javax.swing.JLabel lbFactura;
    private javax.swing.JLabel lbFactura1;
    private javax.swing.JLabel lbNC1;
    private javax.swing.JLabel lbNC2;
    private javax.swing.JLabel lbNit;
    private javax.swing.JLabel lbNit2;
    private javax.swing.JLabel lbNit24;
    private javax.swing.JLabel lbNit25;
    private javax.swing.JLabel lbNit26;
    private javax.swing.JLabel lbNit3;
    private javax.swing.JLabel lbNit4;
    private javax.swing.JLabel lbNit5;
    private javax.swing.JLabel lbNombre;
    private javax.swing.JLabel lbPendiente;
    private javax.swing.JLabel lbTargeta;
    private javax.swing.JLabel lbTargeta1;
    private javax.swing.JLabel lbTotalAbonos;
    private javax.swing.JLabel lbValor;
    private javax.swing.JPanel pnlAbonos;
    private javax.swing.JPanel pnlDatos;
    private javax.swing.JPanel pnlFormulario;
    private javax.swing.JPanel pnlInvisible;
    private javax.swing.JPanel pnlSaldo;
    private javax.swing.JRadioButton rdAbonoFactura;
    private javax.swing.JRadioButton rdAbonoGeneral;
    private javax.swing.JScrollPane scrFormulario;
    private javax.swing.JTable tblCartera;
    private javax.swing.JTable tblPagosPendientes;
    private javax.swing.JTextField txtAbonado;
    private javax.swing.JTextField txtBuscadorDocumento;
    private javax.swing.JTextField txtBuscadorFactura;
    private javax.swing.JTextField txtBuscadorIngreso;
    private javax.swing.JTextField txtBuscadorNombre;
    private javax.swing.JTextField txtCheque;
    private javax.swing.JTextField txtComprobante;
    private javax.swing.JTextField txtDescFinanciero;
    private javax.swing.JTextField txtEfectivo;
    private javax.swing.JTextField txtFactura;
    private javax.swing.JTextField txtFuente;
    private javax.swing.JTextField txtIca;
    private javax.swing.JTextField txtIdSistema;
    private javax.swing.JTextField txtIngreso;
    private javax.swing.JTextField txtIva;
    private javax.swing.JTextField txtNit;
    private javax.swing.JLabel txtNoAbono;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtOtro;
    private javax.swing.JTextField txtPendiente;
    private javax.swing.JTextField txtSaldo;
    private javax.swing.JTextField txtSaldo1;
    private javax.swing.JTextField txtSaldoCuentas;
    private javax.swing.JTextField txtTargeta;
    private javax.swing.JTextField txtTotalAbonos;
    private javax.swing.JTextField txtValor;
    // End of variables declaration//GEN-END:variables
}
