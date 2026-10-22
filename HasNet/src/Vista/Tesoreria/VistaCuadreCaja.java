package Vista.Tesoreria;

import Controlador.Alertas.ControladorAlertas;

import Impresiones.ImpresionesCuadreCaja.GeneradorReporteCuadreCaja;
import formularios.Ventas.*;
import clases.Instancias;
import clases.Ventas.ndCaja;
import clases.big;
import clases.metodosGenerales;
import Modelo.Tesoreria.ModeloDocumentoCuadre;
import Modelo.Tesoreria.ModeloResumenCuadre;
import Procesos.Tesoreria.Servicio.ServicioCuadreCaja;
import Validaciones.Tesoreria.squemaCuadreCaja;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class VistaCuadreCaja extends javax.swing.JInternalFrame {

    private final ServicioCuadreCaja servicioCuadreCaja = new ServicioCuadreCaja();
    private final squemaCuadreCaja squemaCuadreCaja = new squemaCuadreCaja();

    Instancias instancias;
    metodosGenerales metodos = new metodosGenerales();
    private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();

    DefaultTableModel modeloDocumento;
    int contadorFacturasContado = 0, contadorFacturasCredito = 0, contadorAbonos = 0, contadorEfectivo = 0, contadorCheque = 0, contTarjeta = 0,
            contNcRealizadas = 0, contNcRecibidas = 0, contCuotasIniciales = 0, contGastos = 0, contNcReembolsadas = 0;
    String primeraFactura = "", ultimaFactura = "", primerAbono = "", ultimoAbono = "";
    String simbolo = "";

    public VistaCuadreCaja() {
        initComponents();

        instancias = Instancias.getInstancias();

        simbolo = instancias.getSimbolo();
        lbConsecutivo.setText("" + instancias.getSql().getNumConsecutivo("CUADRE")[0]);

        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
        Barra.setSize(0, 0);
        Barra.setPreferredSize(new Dimension(0, 0));
        setBorder(null);
        repaint();

        modeloDocumento = (DefaultTableModel) tblDocumentos.getModel();
        tblDocumentos.setRowSorter(new TableRowSorter<>(modeloDocumento));

        Object[][] usuarios = instancias.getSql().getUsuarios();

        for (Object[] usuario : usuarios) {
            cmbUsuarios.addItem(usuario[0]);
        }

        cmbUsuarios.setSelectedItem(instancias.getUsuario());
        if (!instancias.getUsuario().equals("ADMIN")) {
            btnReimprimir.setVisible(false);
        }

        btnActualizarActionPerformed(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        pnlTotalizadosCuaddreCaja = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblIngresos = new javax.swing.JTable();
        lblBannerIngresos = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblSalidas = new javax.swing.JTable();
        lblBannerEgresos = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblValoresEsperados = new javax.swing.JTable();
        lblBannerEgresos1 = new javax.swing.JLabel();
        lblBannerResumen = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        pnlValoresActuales = new javax.swing.JPanel();
        lbNit7 = new javax.swing.JLabel();
        txtTarjeta = new javax.swing.JTextField();
        txtNotaCredito = new javax.swing.JTextField();
        lbNit8 = new javax.swing.JLabel();
        txtCheque = new javax.swing.JTextField();
        lbNit9 = new javax.swing.JLabel();
        txtEfectivo = new javax.swing.JTextField();
        lbNit10 = new javax.swing.JLabel();
        lbNit11 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        mensaje = new javax.swing.JLabel();
        txtIndicadorValores = new javax.swing.JTextField();
        btnActualizar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnReimprimir = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        lbNit19 = new javax.swing.JLabel();
        cmbUsuarios = new javax.swing.JComboBox();
        btnGuardarRecogidaParcial = new javax.swing.JButton();
        btnGuardarBase = new javax.swing.JButton();
        btnReimprimir1 = new javax.swing.JButton();
        lbNit12 = new javax.swing.JLabel();
        lbConsecutivo = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        lbNit18 = new javax.swing.JLabel();
        dtFechaCuadre = new datechooser.beans.DateChooserCombo();
        lblBannerEgresos2 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblInformativos = new javax.swing.JTable();
        scrFormulario = new javax.swing.JScrollPane();
        pnlDetalleCuadreCaja = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDocumentos = new javax.swing.JTable();

        setTitle("Factura");

        pnlTotalizadosCuaddreCaja.setBackground(new java.awt.Color(255, 255, 255));

        tblIngresos.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        tblIngresos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Ventas a crédito", null},
                {"Ventas a contado", null},
                {"Ventas a sistecrédito", null},
                {"TOTAL VENTAS", null},
                {null, null},
                {"Abonos cartera", null},
                {"Abonos plan separe", null},
                {"TOTAL ABONOS", null},
                {null, null},
                {"Cuotas iniciales", null}
            },
            new String [] {
                "Descripción", "Valor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblIngresos.setRowHeight(24);
        jScrollPane2.setViewportView(tblIngresos);
        if (tblIngresos.getColumnModel().getColumnCount() > 0) {
            tblIngresos.getColumnModel().getColumn(1).setMinWidth(90);
            tblIngresos.getColumnModel().getColumn(1).setPreferredWidth(120);
            tblIngresos.getColumnModel().getColumn(1).setMaxWidth(150);
        }

        lblBannerIngresos.setBackground(new java.awt.Color(46, 204, 113));
        lblBannerIngresos.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        lblBannerIngresos.setForeground(new java.awt.Color(255, 255, 255));
        lblBannerIngresos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBannerIngresos.setText("INGRESOS  -  Aumentan la caja");
        lblBannerIngresos.setOpaque(true);

        tblSalidas.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        tblSalidas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Notas crédito reembolsadas", null},
                {"Notas crédito no reembolsadas", null},
                {"TOTAL NOTAS CRÉDITOS", null},
                {null, null},
                {"TOTAL GASTOS", null}
            },
            new String [] {
                "Descripción", "Valor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSalidas.setRowHeight(24);
        tblSalidas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblSalidasKeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(tblSalidas);
        if (tblSalidas.getColumnModel().getColumnCount() > 0) {
            tblSalidas.getColumnModel().getColumn(1).setMinWidth(90);
            tblSalidas.getColumnModel().getColumn(1).setPreferredWidth(120);
            tblSalidas.getColumnModel().getColumn(1).setMaxWidth(150);
        }

        lblBannerEgresos.setBackground(new java.awt.Color(192, 57, 43));
        lblBannerEgresos.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        lblBannerEgresos.setForeground(new java.awt.Color(255, 255, 255));
        lblBannerEgresos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBannerEgresos.setText("EGRESOS  -  Disminuyen la caja");
        lblBannerEgresos.setToolTipText("");
        lblBannerEgresos.setOpaque(true);

        tblValoresEsperados.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        tblValoresEsperados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"TOTAL ESPERADO", null},
                {"EFECTIVO", null},
                {"CHEQUE", null},
                {"TARJETA", null},
                {"NC PAGADAS", null},
                {"BASE", null},
                {"RECOGIDA PARCIAL", null},
                {"PROPINAS", null}
            },
            new String [] {
                "Descripción", "Valor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblValoresEsperados.setRowHeight(24);
        jScrollPane4.setViewportView(tblValoresEsperados);
        if (tblValoresEsperados.getColumnModel().getColumnCount() > 0) {
            tblValoresEsperados.getColumnModel().getColumn(1).setMinWidth(90);
            tblValoresEsperados.getColumnModel().getColumn(1).setPreferredWidth(120);
            tblValoresEsperados.getColumnModel().getColumn(1).setMaxWidth(150);
        }

        lblBannerEgresos1.setBackground(new java.awt.Color(255, 178, 2));
        lblBannerEgresos1.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        lblBannerEgresos1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBannerEgresos1.setText("ESPERADO EN CAJA");
        lblBannerEgresos1.setToolTipText("");
        lblBannerEgresos1.setOpaque(true);

        lblBannerResumen.setBackground(new java.awt.Color(44, 62, 80));
        lblBannerResumen.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        lblBannerResumen.setForeground(new java.awt.Color(255, 255, 255));
        lblBannerResumen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBannerResumen.setText("RESUMEN DEL CUADRE");
        lblBannerResumen.setOpaque(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        pnlValoresActuales.setBackground(new java.awt.Color(255, 255, 255));
        pnlValoresActuales.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "CONTEO DINERO", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 1, 16))); // NOI18N

        lbNit7.setBackground(new java.awt.Color(255, 255, 255));
        lbNit7.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lbNit7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNit7.setText("Tarjeta");
        lbNit7.setOpaque(true);

        txtTarjeta.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        txtTarjeta.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTarjeta.setText("$ 0");
        txtTarjeta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTarjetaActionPerformed(evt);
            }
        });
        txtTarjeta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTarjetaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTarjetaKeyTyped(evt);
            }
        });

        txtNotaCredito.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        txtNotaCredito.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtNotaCredito.setText("$ 0");
        txtNotaCredito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNotaCreditoActionPerformed(evt);
            }
        });
        txtNotaCredito.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNotaCreditoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNotaCreditoKeyTyped(evt);
            }
        });

        lbNit8.setBackground(new java.awt.Color(255, 255, 255));
        lbNit8.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lbNit8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNit8.setText("Nota Crédito");
        lbNit8.setOpaque(true);

        txtCheque.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        txtCheque.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtCheque.setText("$ 0");
        txtCheque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtChequeActionPerformed(evt);
            }
        });
        txtCheque.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtChequeKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtChequeKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtChequeKeyTyped(evt);
            }
        });

        lbNit9.setBackground(new java.awt.Color(255, 255, 255));
        lbNit9.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lbNit9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNit9.setText("Cheque");
        lbNit9.setOpaque(true);

        txtEfectivo.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        txtEfectivo.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtEfectivo.setText("$ 0");
        txtEfectivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEfectivoActionPerformed(evt);
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

        lbNit10.setBackground(new java.awt.Color(255, 255, 255));
        lbNit10.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lbNit10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNit10.setText("Efectivo");
        lbNit10.setOpaque(true);

        lbNit11.setBackground(new java.awt.Color(255, 255, 255));
        lbNit11.setFont(new java.awt.Font("Century Gothic", 1, 22)); // NOI18N
        lbNit11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNit11.setText("TOTAL:");
        lbNit11.setOpaque(true);

        txtTotal.setBackground(new java.awt.Color(255, 255, 255));
        txtTotal.setFont(new java.awt.Font("Century Gothic", 1, 22)); // NOI18N
        txtTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtTotal.setText("$ 0");
        txtTotal.setOpaque(true);

        javax.swing.GroupLayout pnlValoresActualesLayout = new javax.swing.GroupLayout(pnlValoresActuales);
        pnlValoresActuales.setLayout(pnlValoresActualesLayout);
        pnlValoresActualesLayout.setHorizontalGroup(
            pnlValoresActualesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlValoresActualesLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(pnlValoresActualesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbNit11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbNit7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbNit9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbNit10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbNit8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(pnlValoresActualesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtNotaCredito, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTarjeta, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCheque, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtEfectivo, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE))
                .addGap(10, 10, 10))
        );
        pnlValoresActualesLayout.setVerticalGroup(
            pnlValoresActualesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlValoresActualesLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(pnlValoresActualesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtEfectivo)
                    .addComponent(lbNit10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(1, 1, 1)
                .addGroup(pnlValoresActualesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtCheque)
                    .addComponent(lbNit9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(1, 1, 1)
                .addGroup(pnlValoresActualesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTarjeta)
                    .addComponent(lbNit7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(1, 1, 1)
                .addGroup(pnlValoresActualesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNotaCredito)
                    .addComponent(lbNit8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addGroup(pnlValoresActualesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNit11, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DIFERENCIA", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 1, 16))); // NOI18N

        mensaje.setBackground(new java.awt.Color(204, 255, 204));
        mensaje.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        mensaje.setForeground(new java.awt.Color(0, 102, 0));
        mensaje.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mensaje.setText("ATENCION TIENE UN FALTANTE");
        mensaje.setOpaque(true);

        txtIndicadorValores.setEditable(false);
        txtIndicadorValores.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtIndicadorValores.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtIndicadorValores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIndicadorValoresActionPerformed(evt);
            }
        });

        btnActualizar.setBackground(new java.awt.Color(93, 173, 226));
        btnActualizar.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/actualizar.png"))); // NOI18N
        btnActualizar.setText("ACTUALIZAR ");
        btnActualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnActualizar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnActualizar.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnGuardar.setBackground(new java.awt.Color(46, 204, 113));
        btnGuardar.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
        btnGuardar.setText("GUARDAR");
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnGuardar.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnReimprimir.setBackground(new java.awt.Color(247, 220, 111));
        btnReimprimir.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
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

        btnLimpiar.setBackground(new java.awt.Color(204, 204, 204));
        btnLimpiar.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/limpiar.png"))); // NOI18N
        btnLimpiar.setText("LIMPIAR       ");
        btnLimpiar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLimpiar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnLimpiar.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnLimpiar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnActualizar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnReimprimir, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtIndicadorValores)
                        .addComponent(mensaje, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)))
                .addGap(10, 10, 10))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(mensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(txtIndicadorValores, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnActualizar)
                    .addComponent(btnGuardar))
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnLimpiar, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                    .addComponent(btnReimprimir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "CAJERO", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 16))); // NOI18N

        lbNit19.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbNit19.setText("Usuario:");

        cmbUsuarios.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        cmbUsuarios.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbUsuariosItemStateChanged(evt);
            }
        });
        cmbUsuarios.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cmbUsuariosFocusGained(evt);
            }
        });
        cmbUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbUsuariosActionPerformed(evt);
            }
        });

        btnGuardarRecogidaParcial.setBackground(new java.awt.Color(204, 204, 204));
        btnGuardarRecogidaParcial.setFont(new java.awt.Font("Calibri", 1, 15)); // NOI18N
        btnGuardarRecogidaParcial.setText("GUARDAR RECOGIDA PARCIAL");
        btnGuardarRecogidaParcial.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardarRecogidaParcial.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnGuardarRecogidaParcial.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnGuardarRecogidaParcial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarRecogidaParcialActionPerformed(evt);
            }
        });

        btnGuardarBase.setBackground(new java.awt.Color(204, 204, 204));
        btnGuardarBase.setFont(new java.awt.Font("Calibri", 1, 15)); // NOI18N
        btnGuardarBase.setText("GUARDAR BASE");
        btnGuardarBase.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardarBase.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnGuardarBase.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnGuardarBase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarBaseActionPerformed(evt);
            }
        });

        btnReimprimir1.setBackground(new java.awt.Color(247, 220, 111));
        btnReimprimir1.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnReimprimir1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/imprimir.png"))); // NOI18N
        btnReimprimir1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReimprimir1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnReimprimir1.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnReimprimir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReimprimir1ActionPerformed(evt);
            }
        });

        lbNit12.setBackground(new java.awt.Color(204, 204, 204));
        lbNit12.setFont(new java.awt.Font("Calibri", 1, 15)); // NOI18N
        lbNit12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit12.setText("Cuadre No.");
        lbNit12.setOpaque(true);

        lbConsecutivo.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        lbConsecutivo.setForeground(new java.awt.Color(255, 0, 0));
        lbConsecutivo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbConsecutivo.setText("1");
        lbConsecutivo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lbNit19, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbUsuarios, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbNit12, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                            .addComponent(lbConsecutivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(btnGuardarBase, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnReimprimir1))
                            .addComponent(btnGuardarRecogidaParcial, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbNit19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbUsuarios, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnGuardarRecogidaParcial, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnReimprimir1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnGuardarBase, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lbNit12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(lbConsecutivo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(44, 44, 44))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(pnlValoresActuales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 203, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pnlValoresActuales, javax.swing.GroupLayout.PREFERRED_SIZE, 203, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        lbNit18.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbNit18.setText("Fecha del cuadre caja:");

        dtFechaCuadre.setFieldFont(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 14));
        dtFechaCuadre.addSelectionChangedListener(new datechooser.events.SelectionChangedListener() {
            public void onSelectionChange(datechooser.events.SelectionChangedEvent evt) {
                dtFechaCuadreOnSelectionChange(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(lbNit18, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(dtFechaCuadre, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbNit18, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dtFechaCuadre, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        lblBannerEgresos2.setBackground(new java.awt.Color(36, 90, 129));
        lblBannerEgresos2.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        lblBannerEgresos2.setForeground(new java.awt.Color(255, 255, 255));
        lblBannerEgresos2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBannerEgresos2.setText("DATOS INFORMATIVOS  -  No mueven la caja");
        lblBannerEgresos2.setToolTipText("");
        lblBannerEgresos2.setOpaque(true);

        tblInformativos.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        tblInformativos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Plan separes generados", null},
                {"Ventas por domicilio", null},
                {"Producto domicilio", null}
            },
            new String [] {
                "Descripción", "Valor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblInformativos.setRowHeight(24);
        tblInformativos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblInformativosKeyReleased(evt);
            }
        });
        jScrollPane5.setViewportView(tblInformativos);
        if (tblInformativos.getColumnModel().getColumnCount() > 0) {
            tblInformativos.getColumnModel().getColumn(1).setMinWidth(90);
            tblInformativos.getColumnModel().getColumn(1).setPreferredWidth(120);
            tblInformativos.getColumnModel().getColumn(1).setMaxWidth(150);
        }

        javax.swing.GroupLayout pnlTotalizadosCuaddreCajaLayout = new javax.swing.GroupLayout(pnlTotalizadosCuaddreCaja);
        pnlTotalizadosCuaddreCaja.setLayout(pnlTotalizadosCuaddreCajaLayout);
        pnlTotalizadosCuaddreCajaLayout.setHorizontalGroup(
            pnlTotalizadosCuaddreCajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTotalizadosCuaddreCajaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlTotalizadosCuaddreCajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblBannerResumen, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlTotalizadosCuaddreCajaLayout.createSequentialGroup()
                        .addGroup(pnlTotalizadosCuaddreCajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblBannerIngresos, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(pnlTotalizadosCuaddreCajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblBannerEgresos, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                            .addComponent(lblBannerEgresos2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(10, 10, 10)
                        .addGroup(pnlTotalizadosCuaddreCajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblBannerEgresos1, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        pnlTotalizadosCuaddreCajaLayout.setVerticalGroup(
            pnlTotalizadosCuaddreCajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTotalizadosCuaddreCajaLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(pnlTotalizadosCuaddreCajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblBannerIngresos, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(lblBannerEgresos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblBannerEgresos1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addGroup(pnlTotalizadosCuaddreCajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlTotalizadosCuaddreCajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(pnlTotalizadosCuaddreCajaLayout.createSequentialGroup()
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblBannerEgresos2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(5, 5, 5)
                .addComponent(lblBannerResumen, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2))
        );

        jTabbedPane1.addTab("Totalizados del cuadre de caja", pnlTotalizadosCuaddreCaja);

        pnlDetalleCuadreCaja.setBackground(new java.awt.Color(255, 255, 255));

        tblDocumentos.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        tblDocumentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Documento", "Efectivo", "Tarjeta", "Cheque", "NC", "TOTAL", "id", "Cuota Inicial", "Terminal", ".-.", "Forma Pago", "TarjetaCredito", "Lugar", "Propina", "sisteCredito"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, true, true, true, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDocumentos.setEnabled(false);
        tblDocumentos.setRowHeight(25);
        tblDocumentos.getTableHeader().setResizingAllowed(false);
        tblDocumentos.getTableHeader().setReorderingAllowed(false);
        tblDocumentos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDocumentosMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblDocumentosMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblDocumentos);
        if (tblDocumentos.getColumnModel().getColumnCount() > 0) {
            tblDocumentos.getColumnModel().getColumn(0).setMinWidth(100);
            tblDocumentos.getColumnModel().getColumn(0).setPreferredWidth(140);
            tblDocumentos.getColumnModel().getColumn(0).setMaxWidth(170);
            tblDocumentos.getColumnModel().getColumn(4).setResizable(false);
            tblDocumentos.getColumnModel().getColumn(5).setResizable(false);
            tblDocumentos.getColumnModel().getColumn(6).setMinWidth(0);
            tblDocumentos.getColumnModel().getColumn(6).setPreferredWidth(0);
            tblDocumentos.getColumnModel().getColumn(6).setMaxWidth(0);
            tblDocumentos.getColumnModel().getColumn(8).setMinWidth(80);
            tblDocumentos.getColumnModel().getColumn(8).setPreferredWidth(80);
            tblDocumentos.getColumnModel().getColumn(8).setMaxWidth(80);
            tblDocumentos.getColumnModel().getColumn(9).setMinWidth(0);
            tblDocumentos.getColumnModel().getColumn(9).setPreferredWidth(0);
            tblDocumentos.getColumnModel().getColumn(9).setMaxWidth(0);
            tblDocumentos.getColumnModel().getColumn(10).setMinWidth(180);
            tblDocumentos.getColumnModel().getColumn(10).setPreferredWidth(180);
            tblDocumentos.getColumnModel().getColumn(10).setMaxWidth(180);
            tblDocumentos.getColumnModel().getColumn(11).setMinWidth(0);
            tblDocumentos.getColumnModel().getColumn(11).setPreferredWidth(0);
            tblDocumentos.getColumnModel().getColumn(11).setMaxWidth(0);
            tblDocumentos.getColumnModel().getColumn(12).setMinWidth(0);
            tblDocumentos.getColumnModel().getColumn(12).setPreferredWidth(0);
            tblDocumentos.getColumnModel().getColumn(12).setMaxWidth(0);
            tblDocumentos.getColumnModel().getColumn(13).setMinWidth(100);
            tblDocumentos.getColumnModel().getColumn(13).setPreferredWidth(130);
            tblDocumentos.getColumnModel().getColumn(13).setMaxWidth(160);
            tblDocumentos.getColumnModel().getColumn(14).setMinWidth(0);
            tblDocumentos.getColumnModel().getColumn(14).setPreferredWidth(0);
            tblDocumentos.getColumnModel().getColumn(14).setMaxWidth(0);
        }

        javax.swing.GroupLayout pnlDetalleCuadreCajaLayout = new javax.swing.GroupLayout(pnlDetalleCuadreCaja);
        pnlDetalleCuadreCaja.setLayout(pnlDetalleCuadreCajaLayout);
        pnlDetalleCuadreCajaLayout.setHorizontalGroup(
            pnlDetalleCuadreCajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetalleCuadreCajaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1046, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlDetalleCuadreCajaLayout.setVerticalGroup(
            pnlDetalleCuadreCajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetalleCuadreCajaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 659, Short.MAX_VALUE)
                .addContainerGap())
        );

        scrFormulario.setViewportView(pnlDetalleCuadreCaja);

        jTabbedPane1.addTab("Detalle del cuadre de caja", scrFormulario);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jTabbedPane1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtTarjetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTarjetaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTarjetaActionPerformed

    private void txtNotaCreditoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNotaCreditoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNotaCreditoActionPerformed

    private void txtChequeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtChequeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtChequeActionPerformed

    private void txtEfectivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEfectivoActionPerformed
        if (txtEfectivo.getText().equals("") || txtEfectivo.getText().equals(this.simbolo) || txtEfectivo.getText().equals(this.simbolo + " ")) {
            txtEfectivo.setText("0");
        }

        txtEfectivo.setText(big.setMoneda(big.getMoneda(txtEfectivo.getText())));
        calcularTotal();
    }//GEN-LAST:event_txtEfectivoActionPerformed

    private void txtChequeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtChequeKeyReleased
        if (txtCheque.getText().equals("") || txtCheque.getText().equals(this.simbolo) || txtCheque.getText().equals(this.simbolo + " ")) {
            txtCheque.setText("0");
        }

        txtCheque.setText(big.setMoneda(big.getMoneda(txtCheque.getText())));
        calcularTotal();
    }//GEN-LAST:event_txtChequeKeyReleased

    private void txtTarjetaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTarjetaKeyReleased
        if (txtTarjeta.getText().equals("") || txtTarjeta.getText().equals(this.simbolo) || txtTarjeta.getText().equals(this.simbolo + " ")) {
            txtTarjeta.setText("0");
        }

        txtTarjeta.setText(big.setMoneda(big.getMoneda(txtTarjeta.getText())));
        calcularTotal();
    }//GEN-LAST:event_txtTarjetaKeyReleased

    private void txtNotaCreditoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNotaCreditoKeyReleased
        if (txtNotaCredito.getText().equals("") || txtNotaCredito.getText().equals(this.simbolo) || txtNotaCredito.getText().equals(this.simbolo + " ")) {
            txtNotaCredito.setText("0");
        }

        txtNotaCredito.setText(big.setMoneda(big.getMoneda(txtNotaCredito.getText())));
        calcularTotal();
    }//GEN-LAST:event_txtNotaCreditoKeyReleased

    private void txtEfectivoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEfectivoKeyReleased
        if (txtEfectivo.getText().equals("") || txtEfectivo.getText().equals(this.simbolo) || txtEfectivo.getText().equals(this.simbolo + " ")) {
            txtEfectivo.setText("0");
        }

        txtEfectivo.setText(big.setMoneda(big.getMoneda(txtEfectivo.getText())));
        calcularTotal();
    }//GEN-LAST:event_txtEfectivoKeyReleased

    private void txtEfectivoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEfectivoKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtEfectivoKeyTyped

    private void txtChequeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtChequeKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtChequeKeyPressed

    private void txtChequeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtChequeKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtChequeKeyTyped

    private void txtTarjetaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTarjetaKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtTarjetaKeyTyped

    private void txtNotaCreditoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNotaCreditoKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtNotaCreditoKeyTyped

    private void txtIndicadorValoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIndicadorValoresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIndicadorValoresActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        cargarTotalesCuadreCaja();

        BigDecimal totalFacturas = big.getMoneda(tblIngresos.getValueAt(3, 1).toString());
        int congeladas = instancias.getSql().getDatosCongelada().length;

        if (!squemaCuadreCaja.validacionesCuadre(totalFacturas, big.getMoneda(txtEfectivo.getText()),
                big.getMoneda(txtCheque.getText()), big.getMoneda(txtTarjeta.getText()), big.getMoneda(txtNotaCredito.getText()), congeladas, instancias.getConfiguraciones().isRestaurante())) {
            return;
        }

        if (ControladorAlertas.option("¿Desea continuar?")) {
            String documento = "CUADRE-" + instancias.getSql().getNumConsecutivo("CUADRE")[0];
            String usuario = instancias.getUsuario();
            String fecha = metodos.desdeDate(dtFechaCuadre.getCurrent());

            if (instancias.getUsuario().equals("ADMIN")) {
                usuario = cmbUsuarios.getSelectedItem().toString();
            }

            //PROCESO GUARDAR REGISTRO EN CAJA
            BigDecimal base = big.getMoneda(tblValoresEsperados.getValueAt(5, 1).toString());
            BigDecimal totalEfectivo = big.getMoneda(tblValoresEsperados.getValueAt(1, 1).toString()).add(base);
            BigDecimal totalCheque = big.getMoneda(tblValoresEsperados.getValueAt(2, 1).toString());
            BigDecimal totalTarjeta = big.getMoneda(tblValoresEsperados.getValueAt(3, 1).toString());
            BigDecimal totalNotasCreditoPagadas = big.getMoneda(tblValoresEsperados.getValueAt(4, 1).toString());
            BigDecimal recogidaPendiente = big.getMoneda(tblValoresEsperados.getValueAt(6, 1).toString());
            BigDecimal totalPropinas = big.getMoneda(tblValoresEsperados.getValueAt(7, 1).toString());

            BigDecimal totalFacturasCredito = big.getMoneda(tblIngresos.getValueAt(0, 1).toString());
            BigDecimal totalFacturasContado = big.getMoneda(tblIngresos.getValueAt(1, 1).toString());
            BigDecimal totalFacturasSistecredito = big.getMoneda(tblIngresos.getValueAt(2, 1).toString());
            BigDecimal totalAbonosCartera = big.getMoneda(tblIngresos.getValueAt(5, 1).toString());
            BigDecimal totalAbonosPlanSepare = big.getMoneda(tblIngresos.getValueAt(6, 1).toString());
            BigDecimal totalAbonos = big.getMoneda(tblIngresos.getValueAt(7, 1).toString());
            BigDecimal totalCuotasIniciales = big.getMoneda(tblIngresos.getValueAt(9, 1).toString());

            BigDecimal totalFacturaPlanSepare = big.getMoneda(tblInformativos.getValueAt(0, 1).toString());
            BigDecimal totalVentasDomicilio = big.getMoneda(tblInformativos.getValueAt(1, 1).toString());
            BigDecimal totalProductoDomicilio = big.getMoneda(tblInformativos.getValueAt(2, 1).toString());

            BigDecimal totalNotasCreditoReembolsadas = big.getMoneda(tblSalidas.getValueAt(0, 1).toString());
            BigDecimal totalNotasCredito = big.getMoneda(tblSalidas.getValueAt(2, 1).toString());

            BigDecimal totalGastosRegistrados = big.getMoneda(tblSalidas.getValueAt(4, 1).toString());
            BigDecimal totalGastosNoRegistrados = BigDecimal.ZERO;

            Object[] vector = {documento, fecha, metodosGenerales.hora(), big.getMoneda(txtEfectivo.getText()),
                big.getMoneda(txtTarjeta.getText()), big.getMoneda(txtCheque.getText()), big.getMoneda(txtNotaCredito.getText()), "cajero",
                big.getMoneda(txtTotal.getText()), totalFacturas.add(totalAbonos), big.getMoneda(txtIndicadorValores.getText()),
                usuario, instancias.getTerminal(), totalGastosRegistrados, recogidaPendiente,
                base, totalFacturas, totalFacturasCredito,
                totalFacturasContado, totalNotasCredito, totalEfectivo, totalCheque, totalTarjeta,
                totalNotasCreditoPagadas, contadorFacturasContado, contadorFacturasCredito, contNcRecibidas,
                contNcRealizadas, contadorEfectivo, contadorCheque, contTarjeta, contadorAbonos,
                totalAbonos, primeraFactura, ultimaFactura, primerAbono, ultimoAbono, totalFacturaPlanSepare,
                totalCuotasIniciales, contCuotasIniciales, contGastos, totalNotasCreditoReembolsadas, contNcReembolsadas,
                totalGastosNoRegistrados, totalAbonosCartera, totalAbonosPlanSepare,
                totalVentasDomicilio, totalProductoDomicilio, totalPropinas, totalFacturasSistecredito
            };

            ndCaja nodo = metodos.llenarCaja(vector);

            if (!instancias.getSql().agregarCuadreCaja(nodo)) {
                ControladorAlertas.alertFail("Hubo un problema al guardar el cuadre");
                return;
            }

            String fechaConsulta = metodos.fechaConsulta(metodos.desdeDate2(dtFechaCuadre.getCurrent()));
            if (!servicioCuadreCaja.marcarDocumentosCuadrados(documento, fechaConsulta, obtenerUsuarioCuadre())) {
                ControladorAlertas.alertFail("Error al actualizar el estado de los documentos del cuadre, llamar a soporte técnico.");
                return;
            }

            modeloDocumento.setRowCount(0);

            if (!instancias.getSql().aumentarConsecutivo("CUADRE", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("CUADRE")[0]) + 1)) {
                ControladorAlertas.alertFail("Hubo un problema al guardar en el consecutivo del cuadre");
            }

            ControladorAlertas.alertSuccess("Cuadre exitoso");

            lbConsecutivo.setText((String) instancias.getSql().getNumConsecutivo("CUADRE")[0]);

            btnLimpiarActionPerformed(evt);
            limpiarTablasCuadreCaja();

            String tipoImpresion = "";
            if (instancias.getImpresion().equals("pos")) {
                tipoImpresion = "Pos";
            }

            new GeneradorReporteCuadreCaja(instancias).verCuadreCaja(documento, tipoImpresion, instancias.getInformacionEmpresa());

            if (instancias.isImprimirCuadreFiscal()) {
                new GeneradorReporteCuadreCaja(instancias).verCuadreFiscal(documento, instancias.getInformacionEmpresa());
            }
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        txtEfectivo.setText("$ 0");
        txtCheque.setText("$ 0");
        txtTarjeta.setText("$ 0");
        txtNotaCredito.setText("$ 0");
        txtTotal.setText("$ 0");
        txtIndicadorValores.setText("$ 0");
        mensaje.setBackground(new java.awt.Color(204, 255, 204));
        mensaje.setForeground(new java.awt.Color(0, 102, 0));
        mensaje.setText("CUADRE DE CAJA CORRECTO");
        contadorFacturasContado = 0;
        contadorFacturasCredito = 0;
        contadorAbonos = 0;
        contadorEfectivo = 0;
        contadorCheque = 0;
        contTarjeta = 0;
        contNcRealizadas = 0;
        contNcRecibidas = 0;
        contNcReembolsadas = 0;
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        cargarTotalesCuadreCaja();
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnGuardarRecogidaParcialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarRecogidaParcialActionPerformed
        if (!instancias.getUsuario().equals("ADMIN")) {
            dlgPedirContrasena contrasena = new dlgPedirContrasena(null, true, "caja");
            contrasena.setLocationRelativeTo(null);
            contrasena.setVisible(true);
        } else {
            dlgRecogidaParcial parcial = new dlgRecogidaParcial(null, true, "caja");
            parcial.setLocationRelativeTo(null);
            parcial.setVisible(true);
        }

    }//GEN-LAST:event_btnGuardarRecogidaParcialActionPerformed

    private void btnGuardarBaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarBaseActionPerformed
        if (!instancias.getUsuario().equals("ADMIN")) {
            dlgPedirContrasena contrasena = new dlgPedirContrasena(null, true, "cajaBase");
            contrasena.setLocationRelativeTo(null);
            contrasena.setVisible(true);
        } else {
            dlgRecogidaParcial parcial = new dlgRecogidaParcial(null, true, "cajaBase");
            parcial.setLocationRelativeTo(null);
            parcial.setVisible(true);
        }
    }//GEN-LAST:event_btnGuardarBaseActionPerformed

    private void cmbUsuariosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbUsuariosItemStateChanged
        cargarTotalesCuadreCaja();
    }//GEN-LAST:event_cmbUsuariosItemStateChanged

    private void cmbUsuariosFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbUsuariosFocusGained
        if (!instancias.getUsuario().equals("ADMIN")) {
            dtFechaCuadre.requestFocus();
            ControladorAlertas.alertFail("No tiene permisos para abrir esta lista");
        }
    }//GEN-LAST:event_cmbUsuariosFocusGained

    private void btnReimprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReimprimirActionPerformed
        String consecutivo = "CUADRE-" + metodos.msgIngresarEnter(this, "Cuadre a reimprimir");

        if (consecutivo.equals("CUADRE-")) {
            return;
        }

        String tipoImpresion = "";
        if (instancias.getImpresion().equals("pos")) {
            tipoImpresion = "Pos";
        }
        new GeneradorReporteCuadreCaja(instancias).verCuadreCaja(consecutivo, tipoImpresion, instancias.getInformacionEmpresa());

        if (instancias.isImprimirCuadreFiscal()) {
            new GeneradorReporteCuadreCaja(instancias).verCuadreFiscal(consecutivo, instancias.getInformacionEmpresa());
        }
    }//GEN-LAST:event_btnReimprimirActionPerformed

    private void btnReimprimir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReimprimir1ActionPerformed
        if (instancias.getUsuario().equals("ADMIN")) {
            new GeneradorReporteCuadreCaja(instancias).verBase(" where fecha = '" + metodos.desdeDate(dtFechaCuadre.getCurrent()) + "' ;", instancias.getInformacionEmpresa());
        } else {
            new GeneradorReporteCuadreCaja(instancias).verBase(" where usuario ='" + Instancias.getInstancias().getUsuario() + "'  and fecha = '" + metodos.desdeDate(dtFechaCuadre.getCurrent()) + "' ;", instancias.getInformacionEmpresa());
        }
    }//GEN-LAST:event_btnReimprimir1ActionPerformed

    private void cmbUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbUsuariosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbUsuariosActionPerformed

    private void tblDocumentosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDocumentosMouseClicked

    }//GEN-LAST:event_tblDocumentosMouseClicked

    private void tblDocumentosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDocumentosMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tblDocumentosMousePressed

    private void tblSalidasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblSalidasKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Object objecto = tblSalidas.getValueAt(5, 1);
            if (objecto == null || objecto.toString().isEmpty()) {
                return;
            }

            BigDecimal totalGastoNoRegistrado = big.getMoneda(tblSalidas.getValueAt(5, 1).toString());
            tblSalidas.setValueAt(big.setMoneda(totalGastoNoRegistrado), 5, 1);
            calcularTotal();
        }
    }//GEN-LAST:event_tblSalidasKeyReleased

    private void tblInformativosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblInformativosKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tblInformativosKeyReleased

    private void dtFechaCuadreOnSelectionChange(datechooser.events.SelectionChangedEvent evt) {//GEN-FIRST:event_dtFechaCuadreOnSelectionChange
        cargarTotalesCuadreCaja();
    }//GEN-LAST:event_dtFechaCuadreOnSelectionChange

    public void actualizar() {
        btnActualizarActionPerformed(null);
    }

    public void desdeCajaPendiente(String usuario, String fecha) {
        cmbUsuarios.setSelectedItem(usuario);
        dtFechaCuadre.setSelectedDate(metodos.haciaDate(metodos.fecha3(fecha)));
//        cargarTabla();
    }

    public void calcularTotal() {
        /*if (!instancias.getUsuario().equals("ADMIN") && !instancias.isMostrarInformacionCuadre()) {
         return;
         }*/

        BigDecimal recogidaPendiente = big.getMoneda(tblValoresEsperados.getValueAt(6, 1).toString());
        BigDecimal total = big.getMoneda(txtEfectivo.getText()).add(big.getMoneda(txtCheque.getText())
                .add(big.getMoneda(txtTarjeta.getText())).add(big.getMoneda(txtNotaCredito.getText()))).add(recogidaPendiente);

        txtTotal.setText(big.setMoneda(total));

        BigDecimal totalCuotasIniciales = big.getMoneda(tblIngresos.getValueAt(9, 1).toString());

        BigDecimal totalEfectivo = big.getMoneda(tblValoresEsperados.getValueAt(1, 1).toString());
        BigDecimal totalCheque = big.getMoneda(tblValoresEsperados.getValueAt(2, 1).toString());
        BigDecimal totalTarjeta = big.getMoneda(tblValoresEsperados.getValueAt(3, 1).toString());
        BigDecimal totalNotasCredito = big.getMoneda(tblValoresEsperados.getValueAt(4, 1).toString());
        BigDecimal base = big.getMoneda(tblValoresEsperados.getValueAt(5, 1).toString());
        BigDecimal totalPropinas = big.getMoneda(tblValoresEsperados.getValueAt(7, 1).toString());

        BigDecimal sumCuadre = totalEfectivo.add(totalCheque).add(totalTarjeta).add(totalNotasCredito)
                .add(totalCuotasIniciales).add(totalPropinas).add(base);

        tblValoresEsperados.setValueAt(big.setMoneda(sumCuadre), 0, 1);

        txtIndicadorValores.setText(big.setMoneda(total.subtract(sumCuadre)));

        int comparacion = total.compareTo(sumCuadre);

        if (comparacion == -1) {
            mensaje.setBackground(new java.awt.Color(255, 204, 204));
            mensaje.setForeground(new java.awt.Color(153, 0, 0));
            mensaje.setText("ATENCION TIENE UN FALTANTE");
        } else if (comparacion == 1) {
            mensaje.setBackground(new java.awt.Color(255, 255, 204));
            mensaje.setForeground(new java.awt.Color(255, 153, 0));
            mensaje.setText("ATENCION TIENE UN SOBRANTE");
        } else {
            mensaje.setBackground(new java.awt.Color(204, 255, 204));
            mensaje.setForeground(new java.awt.Color(0, 102, 0));
            mensaje.setText("CUADRE DE CAJA CORRECTO");
        }
    }

    public void cargarTotalesCuadreCaja() {
        /*if (!instancias.getUsuario().equals("ADMIN") && !instancias.isMostrarInformacionCuadre()) {
         return;
         }*/

        String fecha = metodos.fechaConsulta(metodos.desdeDate2(dtFechaCuadre.getCurrent()));
        ModeloResumenCuadre resumen = servicioCuadreCaja.calcular(fecha, obtenerUsuarioCuadre());

        mostrarDocumentos(resumen.getDocumentos());
        mostrarTotales(resumen);
        guardarContadores(resumen);

        calcularTotal();
    }

    /**
     * Usuario cuyo cuadre se consulta: el de la sesión, o el elegido en la
     * lista si quien cuadra es ADMIN. Retorna null cuando se debe consolidar a
     * todos los usuarios (ADMIN sin filtrar por usuario).
     */
    private String obtenerUsuarioCuadre() {
        String usuario = instancias.getUsuario();
        if (usuario.equals("ADMIN")) {
            usuario = cmbUsuarios.getSelectedItem().toString();
        }
        return usuario.equals("ADMIN") ? null : usuario;
    }

    private void mostrarDocumentos(List<ModeloDocumentoCuadre> documentos) {
        modeloDocumento.setRowCount(0);

        for (ModeloDocumentoCuadre documento : documentos) {
            boolean esFactura = documento.getTipo() == ModeloDocumentoCuadre.Tipo.FACTURA;

            modeloDocumento.addRow(new Object[]{
                documento.getId(),
                big.setMoneda(documento.getEfectivo()),
                big.setMoneda(documento.getTarjeta().add(documento.getTarjetaCredito())),
                big.setMoneda(documento.getCheque()),
                big.setMoneda(documento.getNotaCredito()),
                big.setMoneda(documento.getTotal()),
                documento.getReferencia(),
                big.setMoneda(documento.getCuotaInicial()),
                documento.getTerminal(),
                documento.getComprobante(),
                obtenerFormaPago(documento),
                esFactura ? big.setMoneda(documento.getTarjetaCredito()) : null,
                esFactura ? documento.getLugar() : null,
                big.setMoneda(documento.getPropina()),
                esFactura ? (documento.isSisteCredito() ? "1" : "0") : null
            });
        }
    }

    private String obtenerFormaPago(ModeloDocumentoCuadre documento) {
        switch (documento.getTipo()) {
            case FACTURA:
                if (!documento.isCredito()) {
                    return "Contado";
                }
                return documento.isSisteCredito() ? "Sistecrédito" : "Crédito";
            case NOTA_CREDITO:
                return documento.isReembolsada() ? "Reembolzada" : "No Reembolzada";
            default:
                return "";
        }
    }

    private void mostrarTotales(ModeloResumenCuadre resumen) {
        tblIngresos.setValueAt(big.setMoneda(resumen.getVentasCredito()), 0, 1);
        tblIngresos.setValueAt(big.setMoneda(resumen.getVentasContado()), 1, 1);
        tblIngresos.setValueAt(big.setMoneda(resumen.getVentasSistecredito()), 2, 1);
        tblIngresos.setValueAt(big.setMoneda(resumen.getTotalVentas()), 3, 1);

        tblIngresos.setValueAt(big.setMoneda(resumen.getAbonosCartera()), 5, 1);
        tblIngresos.setValueAt(big.setMoneda(resumen.getAbonosPlanSepare()), 6, 1);
        tblIngresos.setValueAt(big.setMoneda(resumen.getTotalAbonos()), 7, 1);

        tblIngresos.setValueAt(big.setMoneda(resumen.getCuotasIniciales()), 9, 1);

        tblInformativos.setValueAt(big.setMoneda(resumen.getFacturasPlanSepare()), 0, 1);
        tblInformativos.setValueAt(big.setMoneda(resumen.getVentasDomicilio()), 1, 1);
        tblInformativos.setValueAt(big.setMoneda(resumen.getProductoDomicilio()), 2, 1);

        tblValoresEsperados.setValueAt(big.setMoneda(resumen.getEfectivoEsperado()), 1, 1);
        tblValoresEsperados.setValueAt(big.setMoneda(resumen.getCheque()), 2, 1);
        tblValoresEsperados.setValueAt(big.setMoneda(resumen.getTarjeta()), 3, 1);
        tblValoresEsperados.setValueAt(big.setMoneda(resumen.getNotasCreditoRecibidas()), 4, 1);
        tblValoresEsperados.setValueAt(big.setMoneda(resumen.getBase()), 5, 1);
        tblValoresEsperados.setValueAt(big.setMoneda(resumen.getRecogidaPendiente()), 6, 1);
        tblValoresEsperados.setValueAt(big.setMoneda(resumen.getPropinas()), 7, 1);

        tblSalidas.setValueAt(big.setMoneda(resumen.getNotasCreditoReembolsadas()), 0, 1);
        tblSalidas.setValueAt(big.setMoneda(resumen.getNotasCreditoNoReembolsadas()), 1, 1);
        tblSalidas.setValueAt(big.setMoneda(resumen.getNotasCreditoRealizadas()), 2, 1);
        tblSalidas.setValueAt(big.setMoneda(resumen.getGastosRegistrados()), 4, 1);
    }

    /**
     * Conserva los contadores y consecutivos que usa btnGuardar para registrar
     * el cuadre.
     */
    private void guardarContadores(ModeloResumenCuadre resumen) {
        contadorFacturasContado = resumen.getContContado();
        contadorFacturasCredito = resumen.getContCredito();
        contadorAbonos = resumen.getContAbonos();
        contadorEfectivo = resumen.getContEfectivo();
        contadorCheque = resumen.getContCheque();
        contTarjeta = resumen.getContTarjeta();
        contNcRealizadas = resumen.getContNcRealizadas();
        contNcRecibidas = resumen.getContNcRecibidas();
        contNcReembolsadas = resumen.getContNcReembolsadas();
        contCuotasIniciales = resumen.getContCuotasIniciales();
        contGastos = resumen.getContGastos();

        primeraFactura = resumen.getPrimeraFactura();
        ultimaFactura = resumen.getUltimaFactura();
        primerAbono = resumen.getPrimerAbono();
        ultimoAbono = resumen.getUltimoAbono();
    }

    public void desdeRecogida(String valor) {

        if (instancias.isImprimirRecogida()) {
            new GeneradorReporteCuadreCaja(instancias).verRecogidaParcial(valor, tblValoresEsperados.getValueAt(6, 1).toString());
        }

        btnActualizarActionPerformed(null);
    }

    private void limpiarTablasCuadreCaja() {
        tblIngresos.setValueAt(this.simbolo + " 0", 0, 1);
        tblIngresos.setValueAt(this.simbolo + " 0", 1, 1);
        tblIngresos.setValueAt(this.simbolo + " 0", 2, 1);
        tblIngresos.setValueAt(this.simbolo + " 0", 3, 1);
        tblIngresos.setValueAt(this.simbolo + " 0", 5, 1);
        tblIngresos.setValueAt(this.simbolo + " 0", 6, 1);
        tblIngresos.setValueAt(this.simbolo + " 0", 7, 1);
        tblIngresos.setValueAt(this.simbolo + " 0", 9, 1);

        tblInformativos.setValueAt(this.simbolo + " 0", 0, 1);
        tblInformativos.setValueAt(this.simbolo + " 0", 1, 1);
        tblInformativos.setValueAt(this.simbolo + " 0", 2, 1);

        tblSalidas.setValueAt(this.simbolo + " 0", 0, 1);
        tblSalidas.setValueAt(this.simbolo + " 0", 1, 1);
        tblSalidas.setValueAt(this.simbolo + " 0", 2, 1);
        tblSalidas.setValueAt(this.simbolo + " 0", 4, 1);

        tblValoresEsperados.setValueAt(this.simbolo + " 0", 0, 1);
        tblValoresEsperados.setValueAt(this.simbolo + " 0", 1, 1);
        tblValoresEsperados.setValueAt(this.simbolo + " 0", 2, 1);
        tblValoresEsperados.setValueAt(this.simbolo + " 0", 3, 1);
        tblValoresEsperados.setValueAt(this.simbolo + " 0", 4, 1);
        tblValoresEsperados.setValueAt(this.simbolo + " 0", 5, 1);
        tblValoresEsperados.setValueAt(this.simbolo + " 0", 6, 1);
        tblValoresEsperados.setValueAt(this.simbolo + " 0", 7, 1);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnGuardarBase;
    private javax.swing.JButton btnGuardarRecogidaParcial;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnReimprimir;
    private javax.swing.JButton btnReimprimir1;
    private javax.swing.JComboBox cmbUsuarios;
    private datechooser.beans.DateChooserCombo dtFechaCuadre;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lbConsecutivo;
    private javax.swing.JLabel lbNit10;
    private javax.swing.JLabel lbNit11;
    private javax.swing.JLabel lbNit12;
    private javax.swing.JLabel lbNit18;
    private javax.swing.JLabel lbNit19;
    private javax.swing.JLabel lbNit7;
    private javax.swing.JLabel lbNit8;
    private javax.swing.JLabel lbNit9;
    private javax.swing.JLabel lblBannerEgresos;
    private javax.swing.JLabel lblBannerEgresos1;
    private javax.swing.JLabel lblBannerEgresos2;
    private javax.swing.JLabel lblBannerIngresos;
    private javax.swing.JLabel lblBannerResumen;
    private javax.swing.JLabel mensaje;
    private javax.swing.JPanel pnlDetalleCuadreCaja;
    private javax.swing.JPanel pnlTotalizadosCuaddreCaja;
    private javax.swing.JPanel pnlValoresActuales;
    private javax.swing.JScrollPane scrFormulario;
    private javax.swing.JTable tblDocumentos;
    private javax.swing.JTable tblInformativos;
    private javax.swing.JTable tblIngresos;
    private javax.swing.JTable tblSalidas;
    private javax.swing.JTable tblValoresEsperados;
    private javax.swing.JTextField txtCheque;
    private javax.swing.JTextField txtEfectivo;
    private javax.swing.JTextField txtIndicadorValores;
    private javax.swing.JTextField txtNotaCredito;
    private javax.swing.JTextField txtTarjeta;
    private javax.swing.JLabel txtTotal;
    // End of variables declaration//GEN-END:variables
}
