package Vista.Tesoreria;

import formularios.Ventas.*;
import clases.Instancias;
import clases.Ventas.ndCaja;
import clases.big;
import clases.metodosGenerales;
import clases.productos.ndProducto;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import javax.swing.JComponent;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class VistaCuadreCaja extends javax.swing.JInternalFrame {

    Instancias instancias;
    metodosGenerales metodos = new metodosGenerales();
    private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();

    DefaultTableModel modelo;
    int contContado = 0, contCredito = 0, contAbonos = 0, contEfectivo = 0, contCheque = 0, contTarjeta = 0,
            contNcRealizadas = 0, contNcRecibidas = 0, contCuotasIniciales = 0, contGastos = 0, contNcReembolsadas = 0;
    String primeraFactura = "", ultimaFactura = "", primerAbono = "", ultimoAbono = "";
    boolean calcular;
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

        modelo = (DefaultTableModel) tblDocumentos1.getModel();

        Object[][] usuarios = instancias.getSql().getUsuarios();

        for (Object[] usuario : usuarios) {
            cmbUsuarios.addItem(usuario[0]);
        }

        //pnlControl.setVisible(false);
        cmbUsuarios.setSelectedItem(instancias.getUsuario());
        if (instancias.getUsuario().equals("ADMIN")) {
            calcular = true;
            //pnlControl.setVisible(true);
//            cargarTabla();
        } else {
            btnReimprimir.setVisible(false);
            calcular = false;
            limpiarTablasCuadreCaja();
        }
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
        tarjetaPend = new javax.swing.JTextField();
        ncPend = new javax.swing.JTextField();
        lbNit8 = new javax.swing.JLabel();
        chequePend = new javax.swing.JTextField();
        lbNit9 = new javax.swing.JLabel();
        efectivoPend = new javax.swing.JTextField();
        lbNit10 = new javax.swing.JLabel();
        lbNit11 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        mensaje = new javax.swing.JLabel();
        cuadre = new javax.swing.JTextField();
        btnActualizar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnReimprimir = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        lbNit19 = new javax.swing.JLabel();
        cmbUsuarios = new javax.swing.JComboBox();
        btnGuardar1 = new javax.swing.JButton();
        btnGuardarBase = new javax.swing.JButton();
        btnReimprimir1 = new javax.swing.JButton();
        lbNit12 = new javax.swing.JLabel();
        lbConsecutivo = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        lbNit18 = new javax.swing.JLabel();
        dtInicio = new datechooser.beans.DateChooserCombo();
        scrFormulario = new javax.swing.JScrollPane();
        pnlDetalleCuadreCaja = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDocumentos1 = new javax.swing.JTable();

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
                {"Facturas plan separe", null},
                {"Cuotas iniciales", null},
                {"Ventas a domicilio", null},
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

        tarjetaPend.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        tarjetaPend.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tarjetaPend.setText("0");
        tarjetaPend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tarjetaPendActionPerformed(evt);
            }
        });
        tarjetaPend.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tarjetaPendKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tarjetaPendKeyTyped(evt);
            }
        });

        ncPend.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        ncPend.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        ncPend.setText("0");
        ncPend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ncPendActionPerformed(evt);
            }
        });
        ncPend.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ncPendKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ncPendKeyTyped(evt);
            }
        });

        lbNit8.setBackground(new java.awt.Color(255, 255, 255));
        lbNit8.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lbNit8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNit8.setText("Nota Crédito");
        lbNit8.setOpaque(true);

        chequePend.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        chequePend.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        chequePend.setText("0");
        chequePend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chequePendActionPerformed(evt);
            }
        });
        chequePend.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                chequePendKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                chequePendKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                chequePendKeyTyped(evt);
            }
        });

        lbNit9.setBackground(new java.awt.Color(255, 255, 255));
        lbNit9.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lbNit9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNit9.setText("Cheque");
        lbNit9.setOpaque(true);

        efectivoPend.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        efectivoPend.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        efectivoPend.setText("0");
        efectivoPend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                efectivoPendActionPerformed(evt);
            }
        });
        efectivoPend.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                efectivoPendKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                efectivoPendKeyTyped(evt);
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
                    .addComponent(ncPend, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tarjetaPend, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chequePend, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(efectivoPend, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE))
                .addGap(10, 10, 10))
        );
        pnlValoresActualesLayout.setVerticalGroup(
            pnlValoresActualesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlValoresActualesLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(pnlValoresActualesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(efectivoPend)
                    .addComponent(lbNit10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(1, 1, 1)
                .addGroup(pnlValoresActualesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(chequePend)
                    .addComponent(lbNit9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(1, 1, 1)
                .addGroup(pnlValoresActualesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tarjetaPend)
                    .addComponent(lbNit7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(1, 1, 1)
                .addGroup(pnlValoresActualesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ncPend)
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

        cuadre.setEditable(false);
        cuadre.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        cuadre.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        cuadre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cuadreActionPerformed(evt);
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
                        .addComponent(cuadre)
                        .addComponent(mensaje, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)))
                .addGap(10, 10, 10))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(mensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(cuadre, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        btnGuardar1.setBackground(new java.awt.Color(204, 204, 204));
        btnGuardar1.setFont(new java.awt.Font("Calibri", 1, 15)); // NOI18N
        btnGuardar1.setText("GUARDAR RECOGIDA PARCIAL");
        btnGuardar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnGuardar1.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnGuardar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardar1ActionPerformed(evt);
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(btnGuardarBase, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnReimprimir1))
                            .addComponent(btnGuardar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
                        .addComponent(btnGuardar1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        dtInicio.setFieldFont(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 14));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(lbNit18, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(dtInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbNit18, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dtInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

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
                        .addGroup(pnlTotalizadosCuaddreCajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblBannerEgresos, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(20, 20, 20)
                .addComponent(lblBannerResumen, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2))
        );

        jTabbedPane1.addTab("Totalizados del cuadre de caja", pnlTotalizadosCuaddreCaja);

        pnlDetalleCuadreCaja.setBackground(new java.awt.Color(255, 255, 255));

        tblDocumentos1.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        tblDocumentos1.setModel(new javax.swing.table.DefaultTableModel(
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
        tblDocumentos1.setEnabled(false);
        tblDocumentos1.setRowHeight(25);
        tblDocumentos1.getTableHeader().setResizingAllowed(false);
        tblDocumentos1.getTableHeader().setReorderingAllowed(false);
        tblDocumentos1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDocumentos1MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblDocumentos1MousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblDocumentos1);
        if (tblDocumentos1.getColumnModel().getColumnCount() > 0) {
            tblDocumentos1.getColumnModel().getColumn(0).setMinWidth(100);
            tblDocumentos1.getColumnModel().getColumn(0).setPreferredWidth(140);
            tblDocumentos1.getColumnModel().getColumn(0).setMaxWidth(170);
            tblDocumentos1.getColumnModel().getColumn(4).setResizable(false);
            tblDocumentos1.getColumnModel().getColumn(5).setResizable(false);
            tblDocumentos1.getColumnModel().getColumn(6).setMinWidth(0);
            tblDocumentos1.getColumnModel().getColumn(6).setPreferredWidth(0);
            tblDocumentos1.getColumnModel().getColumn(6).setMaxWidth(0);
            tblDocumentos1.getColumnModel().getColumn(8).setMinWidth(80);
            tblDocumentos1.getColumnModel().getColumn(8).setPreferredWidth(80);
            tblDocumentos1.getColumnModel().getColumn(8).setMaxWidth(80);
            tblDocumentos1.getColumnModel().getColumn(9).setMinWidth(0);
            tblDocumentos1.getColumnModel().getColumn(9).setPreferredWidth(0);
            tblDocumentos1.getColumnModel().getColumn(9).setMaxWidth(0);
            tblDocumentos1.getColumnModel().getColumn(10).setMinWidth(180);
            tblDocumentos1.getColumnModel().getColumn(10).setPreferredWidth(180);
            tblDocumentos1.getColumnModel().getColumn(10).setMaxWidth(180);
            tblDocumentos1.getColumnModel().getColumn(11).setMinWidth(0);
            tblDocumentos1.getColumnModel().getColumn(11).setPreferredWidth(0);
            tblDocumentos1.getColumnModel().getColumn(11).setMaxWidth(0);
            tblDocumentos1.getColumnModel().getColumn(12).setMinWidth(0);
            tblDocumentos1.getColumnModel().getColumn(12).setPreferredWidth(0);
            tblDocumentos1.getColumnModel().getColumn(12).setMaxWidth(0);
            tblDocumentos1.getColumnModel().getColumn(13).setMinWidth(100);
            tblDocumentos1.getColumnModel().getColumn(13).setPreferredWidth(130);
            tblDocumentos1.getColumnModel().getColumn(13).setMaxWidth(160);
            tblDocumentos1.getColumnModel().getColumn(14).setMinWidth(0);
            tblDocumentos1.getColumnModel().getColumn(14).setPreferredWidth(0);
            tblDocumentos1.getColumnModel().getColumn(14).setMaxWidth(0);
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE)
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

    private void tarjetaPendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tarjetaPendActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tarjetaPendActionPerformed

    private void ncPendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ncPendActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ncPendActionPerformed

    private void chequePendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chequePendActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chequePendActionPerformed

    private void efectivoPendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_efectivoPendActionPerformed
        if (efectivoPend.getText().equals("") || efectivoPend.getText().equals(this.simbolo) || efectivoPend.getText().equals(this.simbolo + " ")) {
            efectivoPend.setText("0");
        }

        efectivoPend.setText(big.setMoneda(big.getMoneda(efectivoPend.getText())));
        calcularTotal();
    }//GEN-LAST:event_efectivoPendActionPerformed

    private void chequePendKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chequePendKeyReleased
        if (chequePend.getText().equals("") || chequePend.getText().equals(this.simbolo) || chequePend.getText().equals(this.simbolo + " ")) {
            chequePend.setText("0");
        }

        chequePend.setText(big.setMoneda(big.getMoneda(chequePend.getText())));
        calcularTotal();
    }//GEN-LAST:event_chequePendKeyReleased

    private void tarjetaPendKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tarjetaPendKeyReleased
        if (tarjetaPend.getText().equals("") || tarjetaPend.getText().equals(this.simbolo) || tarjetaPend.getText().equals(this.simbolo + " ")) {
            tarjetaPend.setText("0");
        }

        tarjetaPend.setText(big.setMoneda(big.getMoneda(tarjetaPend.getText())));
        calcularTotal();
    }//GEN-LAST:event_tarjetaPendKeyReleased

    private void ncPendKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ncPendKeyReleased
        if (ncPend.getText().equals("") || ncPend.getText().equals(this.simbolo) || ncPend.getText().equals(this.simbolo + " ")) {
            ncPend.setText("0");
        }

        ncPend.setText(big.setMoneda(big.getMoneda(ncPend.getText())));
        calcularTotal();
    }//GEN-LAST:event_ncPendKeyReleased

    private void efectivoPendKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_efectivoPendKeyReleased
        if (efectivoPend.getText().equals("") || efectivoPend.getText().equals(this.simbolo) || efectivoPend.getText().equals(this.simbolo + " ")) {
            efectivoPend.setText("0");
        }

        efectivoPend.setText(big.setMoneda(big.getMoneda(efectivoPend.getText())));
        calcularTotal();
    }//GEN-LAST:event_efectivoPendKeyReleased

    private void efectivoPendKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_efectivoPendKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_efectivoPendKeyTyped

    private void chequePendKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chequePendKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_chequePendKeyPressed

    private void chequePendKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chequePendKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_chequePendKeyTyped

    private void tarjetaPendKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tarjetaPendKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_tarjetaPendKeyTyped

    private void ncPendKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ncPendKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_ncPendKeyTyped

    private void cuadreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cuadreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cuadreActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        Object[] campos = {efectivoPend, chequePend, tarjetaPend, ncPend};
        String faltantes = metodos.camposVacios(campos);
        boolean aux = calcular;

        /*if (txtVenta.getText().equals("$ 0")) {
         metodos.msgAdvertencia(this, "No se puede guardar con ventas en $ 0");
         return;
         }*/
        if (!faltantes.equals("")) {
            metodos.msgAdvertencia(this, "Faltan los siguientes campos: " + faltantes);
        }

//        if (mensaje2.isVisible()) {
//            metodos.msgAdvertencia(this, "Hay algunos productos sin precio ponderado y no habra una utilidad válida!");
//        }
        Object[] productosFacturas = new Object[tblDocumentos1.getRowCount()];
        int ser = 0;
        Boolean entro = false;

        Object[][] congeladas = instancias.getSql().getDatosCongelada();
        if (congeladas.length > 0) {
            if (instancias.getConfiguraciones().isRestaurante()) {
                metodos.msgAdvertenciaAjustado(this, "¡Hay mesas sin finalizar!");
                return;
            } else {
                metodos.msgAdvertenciaAjustado(this, "¡Hay congeladas sin finalizar!");
                return;
            }
        }

//        if (!(Boolean) sql.getDatosMaestra()[59]) {
//            for (int i = 0; i < tblDocumentos1.getRowCount(); i++) {
//                Object[][] datos = sql.getRegistrosPrefacturas(tblDocumentos1.getValueAt(i, 0).toString());
//                for (int j = 0; j < datos.length; j++) {
//                    double ponderado;
//                    Object[] datosProducto = sql.getUltimoPonderado(datos[j][0].toString());
//                    ponderado = Double.parseDouble(datosProducto[4].toString().replace(",", "."));
//                    if (ponderado < 0) {
//                        metodos.msgError(this, "¡El producto " + datos[j][1].toString() + " tiene ponderado negativo y no se puede continar!");
//                        return;
//                    }
//                }
//            }
//        } else {
//            Iniciar2 ini = new Iniciar2();
//            cargando barra = new cargando(ini, Instancias.getInstancias());
//            barra.show();
//            this.barra2 = ini.getBarra();
//
//            for (int i = 0; i < tblDocumentos1.getRowCount(); i++) {
//                Object[][] datos = sql.getRegistrosPrefacturas(tblDocumentos1.getValueAt(i, 0).toString());
//                for (int j = 0; j < datos.length; j++) {
//                    double ponderado;
//                    Object[] datosProducto = sql.getUltimoPonderado(datos[j][0].toString());
//                    System.out.println("producto " + datos[j][0].toString());
//                    System.out.println("ponderado " + datosProducto[4].toString());
//                    ponderado = Double.parseDouble(datosProducto[4].toString().replace(",", "."));
//                    if (ponderado < 0) {
//                        productosFacturas[ser] = datos[j][0].toString();
//                        entro = true;
//                        ser++;
//                    }
//                }
//            }
//            this.barra2.detener(true);
//        }
//        if (entro) {
//            if (metodos.msgPregunta(this, "¿Ponderados malos. ¿Desea revisarlos?") == 0) {
//                dlgPonderadoNegativo ponderadoNegativo = new dlgPonderadoNegativo(null, true, productosFacturas);
//                ponderadoNegativo.setVisible(true);
//                return;
//            }
//        }
        if (metodos.msgPregunta(this, "¿Desea continuar?") == 0) {

            if (!instancias.getUsuario().equals("ADMIN")) {
                calcular = true;
                cargarTabla();
            }

            String documento = "CUADRE-" + instancias.getSql().getNumConsecutivo("CUADRE")[0];
            String usuario = instancias.getUsuario();
            String fecha = metodos.desdeDate(dtInicio.getCurrent());

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
            BigDecimal totalFacturas = big.getMoneda(tblIngresos.getValueAt(3, 1).toString());
            BigDecimal totalAbonosCartera = big.getMoneda(tblIngresos.getValueAt(5, 1).toString());
            BigDecimal totalAbonosPlanSepare = big.getMoneda(tblIngresos.getValueAt(6, 1).toString());
            BigDecimal totalAbonos = big.getMoneda(tblIngresos.getValueAt(7, 1).toString());
            BigDecimal totalFacturaPlanSepare = big.getMoneda(tblIngresos.getValueAt(9, 1).toString());
            BigDecimal totalCuotasIniciales = big.getMoneda(tblIngresos.getValueAt(10, 1).toString());
            BigDecimal totalVentasDomicilio = big.getMoneda(tblIngresos.getValueAt(11, 1).toString());
            BigDecimal totalProductoDomicilio = big.getMoneda(tblIngresos.getValueAt(12, 1).toString());

            BigDecimal totalNotasCreditoReembolsadas = big.getMoneda(tblSalidas.getValueAt(0, 1).toString());
            BigDecimal totalNotasCredito = big.getMoneda(tblSalidas.getValueAt(2, 1).toString());

            BigDecimal totalGastosRegistrados = big.getMoneda(tblSalidas.getValueAt(4, 1).toString());
            BigDecimal totalGastosNoRegistrados = big.getMoneda(tblSalidas.getValueAt(5, 1).toString());

            Object[] vector = {documento, fecha, metodosGenerales.hora(), big.getMoneda(efectivoPend.getText()),
                big.getMoneda(tarjetaPend.getText()), big.getMoneda(chequePend.getText()), big.getMoneda(ncPend.getText()), "cajero",
                big.getMoneda(txtTotal.getText()), totalFacturas.add(totalAbonos), big.getMoneda(cuadre.getText()),
                usuario, instancias.getTerminal(), totalGastosRegistrados, recogidaPendiente,
                base, totalFacturas, totalFacturasCredito,
                totalFacturasContado, totalNotasCredito, totalEfectivo, totalCheque, totalTarjeta,
                totalNotasCreditoPagadas, contContado, contCredito, contNcRecibidas,
                contNcRealizadas, contEfectivo, contCheque, contTarjeta, contAbonos,
                totalAbonos, primeraFactura, ultimaFactura, primerAbono, ultimoAbono, totalFacturaPlanSepare,
                totalCuotasIniciales, contCuotasIniciales, contGastos, totalNotasCreditoReembolsadas, contNcReembolsadas,
                totalGastosNoRegistrados, totalAbonosCartera, totalAbonosPlanSepare,
                totalVentasDomicilio, totalProductoDomicilio, totalPropinas, totalFacturasSistecredito
            };

            ndCaja nodo = metodos.llenarCaja(vector);

            if (!instancias.getSql().agregarCuadreCaja(nodo)) {
                metodos.msgError(this, "Hubo un problema al guardar el cuadre");
                return;
            }

            for (int i = 0; i < tblDocumentos1.getRowCount(); i++) {
                String documentoTabla = tblDocumentos1.getValueAt(i, 0).toString();
                if (documentoTabla.contains("NC")) {
                    if (!instancias.getSql().modificarRedNc((String) modelo.getValueAt(i, 0), documento)) {
                        metodos.msgError(this, "Hubo un problema al cambiar el estado del cuadre NC.");
                    }
                }
            }

            int registros = tblDocumentos1.getRowCount();
            for (int i = 0; i < registros; i++) {
                boolean actualizo = false;

                String tipo = tblDocumentos1.getValueAt(i, 0).toString().split("-")[0];

                if (tipo.equals("SEPARE")) {
                    actualizo = instancias.getSql().modificarCuadreSepare((String) modelo.getValueAt(i, 0), documento, "REALIZADO");
                } else if (tipo.equals("FACT")) {
                    actualizo = instancias.getSql().modificarCuadreFactura((String) modelo.getValueAt(i, 0), documento, "REALIZADO");
                } else if (tipo.equals("ABONO")) {
                    actualizo = instancias.getSql().modificarCuadreAbonos((String) modelo.getValueAt(i, 0), documento, "REALIZADO");
                } else if (tipo.equals("NC")) {
                    actualizo = instancias.getSql().modificarCuadreNotasCredito((String) modelo.getValueAt(i, 0), documento, "REALIZADO");
                }

                if (!actualizo) {
                    metodos.msgError(this, "Error al actualizar estado de la factura");
                }
            }

            while (tblDocumentos1.getRowCount() > 0) {
                modelo.removeRow(0);
            }

            String fechaGastos = "";
            int contAnuladas;
            try {
                fechaGastos = " ((fechaAnulacion)= '" + metodos.desdeDate(dtInicio.getCurrent()) + "') ";
                contAnuladas = instancias.getSql().contadorFacturasAnuladas(fechaGastos, instancias.getUsuario());
            } catch (Exception e) {
                contAnuladas = 0;
            }

            if (contAnuladas > 0) {
                Object[][] facturaAnulada = instancias.getSql().facturasAnuladasCuadre(fechaGastos, instancias.getUsuario());
                for (Object[] facturaAnulada1 : facturaAnulada) {
                    instancias.getSql().modificarCuadreAnulacion(facturaAnulada1[0].toString(), documento);
                }
            }

            if (!instancias.getSql().modificarEstadoEgreso(metodos.desdeDate(dtInicio.getCurrent()), "REALIZADO")) {
                metodos.msgError(this, "Error al modificar la recogida.");
            }

            System.out.println("no creo que aqui");
            if (!instancias.getSql().desactivarRecogidaParcial()) {
                metodos.msgError(this, "Error al desactivar la recogida.");
            }

            System.out.println("uy zonas como asi x2");
            if (instancias.getUsuario().equals("ADMIN")) {
                if (!instancias.getSql().desactivarBasesDeCaja(metodos.desdeDate(dtInicio.getCurrent()))) {
                    metodos.msgError(this, "Error al modificar la recogida");
                }
                if (!instancias.getSql().asignarCuadreABase(documento, metodos.desdeDate(dtInicio.getCurrent()))) {
                    metodos.msgError(this, "Hubo un problema al desactivar la recogida parcial, llamar a soporte tecnico.");
                }
            } else {
                System.out.println("uy zonas como asi");
                if (!instancias.getSql().desactivarBasesDeCaja(metodos.desdeDate(dtInicio.getCurrent()), "")) {
                    metodos.msgError(this, "Hubo un problema al desactivar la recogida parcial, llamar a soporte tecnico.");
                }
                System.out.println("que gono");
                if (!instancias.getSql().asignarCuadreABase(documento, metodos.desdeDate(dtInicio.getCurrent()), "")) {
                    metodos.msgError(this, "Hubo un problema al desactivar la recogida parcial, llamar a soporte tecnico.");
                }
                System.out.println("uy zonas como asi fakll");
            }

            if (!instancias.getSql().aumentarConsecutivo("CUADRE", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("CUADRE")[0]) + 1)) {
                metodos.msgError(this, "Hubo un problema al guardar en el consecutivo del cuadre");
            }

            metodos.msgExito(this, "Cuadre exitoso");

            lbConsecutivo.setText((String) instancias.getSql().getNumConsecutivo("CUADRE")[0]);

            btnLimpiarActionPerformed(evt);

            String tipoImpresion = "";
            if (instancias.getImpresion().equals("pos")) {
                tipoImpresion = "Pos";
            }

            System.out.println("cuadre de caja");
            instancias.getReporte().verCuadreCaja(documento, tipoImpresion, instancias.getInformacionEmpresa());

            System.out.println("fallo");

            if (instancias.isImprimirCuadreFiscal()) {
                instancias.getReporte().verCuadreFiscal(documento, instancias.getInformacionEmpresa());
            }
            calcular = aux;
            System.out.println("fiscal");
//            instancias.getReporte().verCaja(fecha, usuario, metodosGenerales.hora(), String.valueOf(registros),
//                    txtEfectivoSistema.getText(), txtTarjetaSistema.getText(), txtChequeSistema.getText(), txtNcSistema.getText(), txtAbonos.getText(),
//                    txtVenta.getText(), String.valueOf(tblDocumentos1.getRowCount()), efectivoPend.getText(), gastosPend.getText(), chequePend.getText(),
//                    ncPend.getText(), txtAbonos.getText(), txtTotal.getText(), cuadre.getText(), mensaje.getText(), tarjetaPend.getText(), recogidaPend.getText(), "0");
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiarTablasCuadreCaja();

        contContado = 0;
        contCredito = 0;
        contAbonos = 0;
        contEfectivo = 0;
        contCheque = 0;
        contTarjeta = 0;
        contNcRealizadas = 0;
        contNcRecibidas = 0;
        contNcReembolsadas = 0;
        calcularTotal();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        cargarTabla();
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnGuardar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar1ActionPerformed
        if (!instancias.getUsuario().equals("ADMIN")) {
            dlgPedirContrasena contrasena = new dlgPedirContrasena(null, true, "caja");
            contrasena.setLocationRelativeTo(null);
            contrasena.setVisible(true);
        } else {
            dlgRecogidaParcial parcial = new dlgRecogidaParcial(null, true, "caja");
            parcial.setLocationRelativeTo(null);
            parcial.setVisible(true);
        }

    }//GEN-LAST:event_btnGuardar1ActionPerformed

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
        cargarTabla();
    }//GEN-LAST:event_cmbUsuariosItemStateChanged

    private void cmbUsuariosFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbUsuariosFocusGained
        if (!instancias.getUsuario().equals("ADMIN")) {
            dtInicio.requestFocus();
            metodos.msgError(this, "No tiene permisos para abrir esta lista");
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
        instancias.getReporte().verCuadreCaja(consecutivo, tipoImpresion, instancias.getInformacionEmpresa());

        if (instancias.isImprimirCuadreFiscal()) {
            instancias.getReporte().verCuadreFiscal(consecutivo, instancias.getInformacionEmpresa());
        }
    }//GEN-LAST:event_btnReimprimirActionPerformed

    private void btnReimprimir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReimprimir1ActionPerformed
        if (instancias.getUsuario().equals("ADMIN")) {
            instancias.getReporte().verBase(" where fecha = '" + metodos.desdeDate(dtInicio.getCurrent()) + "' ;", instancias.getInformacionEmpresa());
        } else {
            instancias.getReporte().verBase(" where usuario ='" + Instancias.getInstancias().getUsuario() + "'  and fecha = '" + metodos.desdeDate(dtInicio.getCurrent()) + "' ;", instancias.getInformacionEmpresa());
        }
    }//GEN-LAST:event_btnReimprimir1ActionPerformed

    private void cmbUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbUsuariosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbUsuariosActionPerformed

    private void tblDocumentos1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDocumentos1MouseClicked

    }//GEN-LAST:event_tblDocumentos1MouseClicked

    private void tblDocumentos1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDocumentos1MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tblDocumentos1MousePressed

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

    public void actualizar() {
        btnActualizarActionPerformed(null);
    }

    public void desdeCajaPendiente(String usuario, String fecha) {
        cmbUsuarios.setSelectedItem(usuario);
        dtInicio.setSelectedDate(metodos.haciaDate(metodos.fecha3(fecha)));
//        cargarTabla();
    }

    public void calcularTotal() {

        if (calcular || instancias.isMostrarInformacionCuadre()) {
            BigDecimal recogidaPendiente = big.getMoneda(tblValoresEsperados.getValueAt(6, 1).toString());
            BigDecimal total = big.getMoneda(efectivoPend.getText()).add(big.getMoneda(chequePend.getText())
                    .add(big.getMoneda(tarjetaPend.getText())).add(big.getMoneda(ncPend.getText()))).add(recogidaPendiente);

            txtTotal.setText(big.setMoneda(total));

            BigDecimal totalCuotasIniciales = big.getMoneda(tblIngresos.getValueAt(10, 1).toString());

            BigDecimal totalEfectivo = big.getMoneda(tblValoresEsperados.getValueAt(1, 1).toString());
            BigDecimal totalCheque = big.getMoneda(tblValoresEsperados.getValueAt(2, 1).toString());
            BigDecimal totalTarjeta = big.getMoneda(tblValoresEsperados.getValueAt(3, 1).toString());
            BigDecimal totalNotasCredito = big.getMoneda(tblValoresEsperados.getValueAt(4, 1).toString());
            BigDecimal base = big.getMoneda(tblValoresEsperados.getValueAt(5, 1).toString());
            BigDecimal totalPropinas = big.getMoneda(tblValoresEsperados.getValueAt(7, 1).toString());

            BigDecimal sumCuadre = totalEfectivo.add(totalCheque).add(totalTarjeta).add(totalNotasCredito)
                    .add(totalCuotasIniciales).add(totalPropinas).add(base);

            tblValoresEsperados.setValueAt(big.setMoneda(sumCuadre), 0, 1);

            cuadre.setText(big.setMoneda(total.subtract(sumCuadre)));

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
    }

    public void cargarTabla() {

        if (calcular || instancias.isMostrarInformacionCuadre()) {
            contContado = 0;
            contCredito = 0;
            contAbonos = 0;
            contEfectivo = 0;
            contCheque = 0;
            contTarjeta = 0;
            contNcRealizadas = 0;
            contNcRecibidas = 0;
            contNcReembolsadas = 0;
            contCuotasIniciales = 0;
            contGastos = 0;

            int j = tblDocumentos1.getRowCount();
            for (int i = 0; i < j; i++) {
                modelo.removeRow(0);
            }

            String fecha1 = metodos.desdeDate2(dtInicio.getCurrent());
            fecha1 = metodos.fechaConsulta(fecha1);

            String fecha = " fechaFactura = '" + fecha1 + "' ";
            String fechaGastos1 = " (((fecha) = '" + fecha1 + "') And ((fecha)<= '" + fecha1 + "' ))";

            String usuario = instancias.getUsuario();
            if (instancias.getUsuario().equals("ADMIN")) {
                usuario = cmbUsuarios.getSelectedItem().toString();
            }

            Object[][] facturas;
            Object[][] separes;
            Object[][] abonos;
            Object[][] notasCredito;
            Object[][] domicilios;

            BigDecimal totalGastosRegistrados;
            if (usuario.equals("ADMIN")) {
                facturas = instancias.getSql().getRegistrosFacturaAdmin(instancias.getTerminal(), fecha);
                separes = instancias.getSql().getRegistrosSepareAdmin(instancias.getTerminal(), fecha);
                abonos = instancias.getSql().getRegistrosAbonoAdmin(instancias.getTerminal(), fecha);
                notasCredito = instancias.getSql().getRegistrosNotasCreditoAdmin(instancias.getTerminal(), fecha);

                totalGastosRegistrados = big.getBigDecimal(instancias.getSql().gastosEgresosAdmin(fechaGastos1));
                contGastos = Integer.parseInt(instancias.getSql().gastosEgresosContAdmin(fechaGastos1));
            } else {
                facturas = instancias.getSql().getRegistrosFactura(instancias.getTerminal(), usuario, fecha);
                separes = instancias.getSql().getRegistrosPlanSepare(instancias.getTerminal(), usuario, fecha);
                abonos = instancias.getSql().getRegistrosAbono(instancias.getTerminal(), usuario, fecha);
                notasCredito = instancias.getSql().getRegistrosNotasCredito(instancias.getTerminal(), usuario, fecha);

                totalGastosRegistrados = big.getBigDecimal(instancias.getSql().gastosEgresos(fechaGastos1, usuario));
                contGastos = Integer.parseInt(instancias.getSql().gastosEgresosCont(fechaGastos1, usuario));
            }

            tblSalidas.setValueAt(big.setMoneda(totalGastosRegistrados), 4, 1);

            ndProducto nodo = instancias.getSql().getDatosProducto("DOMI", "bdProductos");
            domicilios = instancias.getSql().getItemDomicilio(nodo.getIdSistema(), fecha);

            BigDecimal sumPropinas = big.getBigDecimal("0"), sumFacturas = big.getBigDecimal("0"), sumDomicilios = big.getBigDecimal("0"), sumAbonos = big.getBigDecimal("0"),
                    sumEfectivo = big.getBigDecimal("0"), sumTarjeta = big.getBigDecimal("0"), sumNc = big.getBigDecimal("0"),
                    sumCheque = big.getBigDecimal("0"), sumCredito = big.getBigDecimal("0"), sumSisteCredito = big.getBigDecimal("0"), sumContado = big.getBigDecimal("0"),
                    sumNcReembolzadas = big.getBigDecimal("0"), sumNcRealizadas = big.getBigDecimal("0"),
                    sumFacturasSepare = big.getBigDecimal("0"),
                    recogidaPendiente = big.getBigDecimal(instancias.getSql().getTotalRecogida(fechaGastos1, usuario)),
                    base = big.getBigDecimal(instancias.getSql().getTotalBaseCuadreCaja(fechaGastos1, usuario)), sumCuotaInicial = big.getBigDecimal("0"),
                    sumItemDomi = big.getBigDecimal("0");

            for (int i = 0; i < domicilios.length; i++) {
                sumItemDomi = sumItemDomi.add(big.getBigDecimal(domicilios[i][0]));
            }

            for (int i = 0; i < facturas.length; i++) {
                modelo.addRow(facturas[i]);

                if (tblDocumentos1.getValueAt(i, 7) == null) {
                    tblDocumentos1.setValueAt(this.simbolo + " 0", i, 7);
                }

                if (i == 0) {
                    primeraFactura = facturas[i][0].toString();
                }

                if (facturas[i][12].toString().equals("DOMICILIO")) {
                    sumDomicilios = sumDomicilios.add(big.getBigDecimal(facturas[i][5]));
                }

                if (facturas[i][10].toString().equals("1")) {
                    if (tblDocumentos1.getValueAt(i, 14) != null) {
                        if (tblDocumentos1.getValueAt(i, 14).toString().equals("1")) {
                            sumSisteCredito = sumSisteCredito.add(big.getBigDecimal(facturas[i][5]));
                        } else {
                            sumCredito = sumCredito.add(big.getBigDecimal(facturas[i][5]));
                        }
                    } else {
                        sumCredito = sumCredito.add(big.getBigDecimal(facturas[i][5]));
                    }
                    contCredito++;
                } else {
                    sumContado = sumContado.add(big.getBigDecimal(facturas[i][5]));
                    contContado++;
                }

                BigDecimal cuotaInicial = BigDecimal.ZERO;

                try {
                    cuotaInicial = big.getBigDecimal(facturas[i][7]);
                } catch (Exception e) {
                    cuotaInicial = big.getBigDecimal("0");
                }

                try {
                    if (cuotaInicial.compareTo(BigDecimal.ZERO) > 0) {
                        contCuotasIniciales++;
                    }
                } catch (Exception e) {
                }

                sumFacturas = sumFacturas.add(big.getBigDecimal(facturas[i][5]));
                sumPropinas = sumPropinas.add(big.getBigDecimal(facturas[i][13]));

                try {
                    sumCuotaInicial = sumCuotaInicial.add(cuotaInicial);
                } catch (Exception e) {
                }

                try {
                    sumEfectivo = sumEfectivo.add(cuotaInicial);
                } catch (Exception e) {
                }

                sumEfectivo = sumEfectivo.add(big.getBigDecimal(facturas[i][1]));

                if (big.getBigDecimal(facturas[i][1]).compareTo(BigDecimal.ZERO) > 0) {
                    contEfectivo++;
                }

                sumTarjeta = sumTarjeta.add(big.getBigDecimal(facturas[i][2]));
                sumTarjeta = sumTarjeta.add(big.getBigDecimal(facturas[i][11]));
                if (big.getBigDecimal(facturas[i][2]).compareTo(BigDecimal.ZERO) > 0) {
                    contTarjeta++;
                }
                if (big.getBigDecimal(facturas[i][11]).compareTo(BigDecimal.ZERO) > 0) {
                    contTarjeta++;
                }

                sumCheque = sumCheque.add(big.getBigDecimal(facturas[i][3]));
                if (big.getBigDecimal(facturas[i][3]).compareTo(BigDecimal.ZERO) > 0) {
                    contCheque++;
                }

                sumNc = sumNc.add(big.getBigDecimal(facturas[i][4]));
                if (big.getBigDecimal(facturas[i][4]).compareTo(BigDecimal.ZERO) > 0) {
                    contNcRecibidas++;
                }
            }

            for (int i = 0; i < separes.length; i++) {
                sumFacturasSepare = sumFacturasSepare.add(big.getBigDecimal(separes[i][5]));
                modelo.addRow(separes[i]);
            }

            for (int i = 0; i < abonos.length; i++) {
                contAbonos++;
                modelo.addRow(abonos[i]);
                sumAbonos = sumAbonos.add(big.getBigDecimal(abonos[i][5]));
                sumEfectivo = sumEfectivo.add(big.getBigDecimal(abonos[i][1]));
                if (big.getBigDecimal(abonos[i][1]).compareTo(BigDecimal.ZERO) > 0) {
                    contEfectivo++;
                }
                sumTarjeta = sumTarjeta.add(big.getBigDecimal(abonos[i][2]));
                if (big.getBigDecimal(abonos[i][2]).compareTo(BigDecimal.ZERO) > 0) {
                    contTarjeta++;
                }
                sumNc = sumNc.add(big.getBigDecimal(abonos[i][4]));
                if (big.getBigDecimal(abonos[i][4]).compareTo(BigDecimal.ZERO) > 0) {
                    contNcRecibidas++;
                }
                sumCheque = sumCheque.add(big.getBigDecimal(abonos[i][3]));
                if (big.getBigDecimal(abonos[i][3]).compareTo(BigDecimal.ZERO) > 0) {
                    contCheque++;
                }
            }

            for (int i = 0; i < notasCredito.length; i++) {
                modelo.addRow(notasCredito[i]);
                sumNcRealizadas = sumNcRealizadas.add(big.getBigDecimal(notasCredito[i][4]));
                contNcRealizadas++;

                if (!notasCredito[i][10].equals("0")) {
                    sumNcReembolzadas = sumNcReembolzadas.add(big.getBigDecimal(notasCredito[i][4]));
                    contNcReembolsadas++;
                }

//                if (big.getBigDecimal(notasCredito[i][4]).compareTo(BigDecimal.ZERO) > 0) {
//                    sumNcReembolzadas = sumNcReembolzadas.add(big.getBigDecimal(notasCredito[i][4]));
//                    contNcReembolsadas++;
//                }
//                
//                if (big.getBigDecimal(notasCredito[i][1]).compareTo(BigDecimal.ZERO) > 0) {
//                    sumNcRealizadas = sumNcRealizadas.add(big.getBigDecimal(notasCredito[i][1]));
//                    contNcRealizadas++;
//                }
            }

            BigDecimal abonosCartera = BigDecimal.ZERO, abonosSepare = BigDecimal.ZERO;
            for (int i = 0; i < tblDocumentos1.getRowCount(); i++) {

                if (tblDocumentos1.getValueAt(i, 0).toString().contains("ABONO")) {
                    if (tblDocumentos1.getValueAt(i, 9).toString().contains("SEPARE")) {
                        abonosSepare = abonosSepare.add(big.getBigDecimal(tblDocumentos1.getValueAt(i, 5).toString()));
                    } else {
                        abonosCartera = abonosCartera.add(big.getBigDecimal(tblDocumentos1.getValueAt(i, 5).toString()));
                    }
                }

                try {
                    if (tblDocumentos1.getValueAt(i, 10).equals("0")) {
                        if (tblDocumentos1.getValueAt(i, 0).toString().contains("NC-")) {
                            tblDocumentos1.setValueAt("No Reembolzada", i, 10);
                        } else {
                            tblDocumentos1.setValueAt("Contado", i, 10);
                        }
                    } else {
                        if (tblDocumentos1.getValueAt(i, 0).toString().contains("NC-")) {
                            tblDocumentos1.setValueAt("Reembolzada", i, 10);
                        } else {

                            if (tblDocumentos1.getValueAt(i, 14) != null) {
                                if (tblDocumentos1.getValueAt(i, 14).toString().equals("1")) {
                                    tblDocumentos1.setValueAt("Sistecrédito", i, 10);
                                } else {
                                    tblDocumentos1.setValueAt("Crédito", i, 10);
                                }
                            } else {
                                tblDocumentos1.setValueAt("Crédito", i, 10);
                            }
                        }
                    }
                } catch (Exception e) {
                    tblDocumentos1.setValueAt("", i, 10);
                }

                if (tblDocumentos1.getValueAt(i, 11) == null) {
                    tblDocumentos1.setValueAt(big.setMoneda(big.getBigDecimal((String) tblDocumentos1.getValueAt(i, 2))), i, 2);
                } else {
                    tblDocumentos1.setValueAt(big.setMoneda(big.getBigDecimal((String) tblDocumentos1.getValueAt(i, 2)).
                            add(big.getBigDecimal((String) tblDocumentos1.getValueAt(i, 11)))), i, 2);
                }

                if (tblDocumentos1.getValueAt(i, 13) != null) {
                    if (tblDocumentos1.getValueAt(i, 13).toString().equals("")) {
                        tblDocumentos1.setValueAt(this.simbolo + " 0", i, 13);
                    } else {
                        tblDocumentos1.setValueAt(big.setMoneda(big.getBigDecimal((String) tblDocumentos1.getValueAt(i, 13))), i, 13);
                    }
                } else {
                    tblDocumentos1.setValueAt(this.simbolo + " 0", i, 13);
                }

                tblDocumentos1.setValueAt(big.setMoneda(big.getBigDecimal((String) tblDocumentos1.getValueAt(i, 1))), i, 1);
                tblDocumentos1.setValueAt(big.setMoneda(big.getBigDecimal((String) tblDocumentos1.getValueAt(i, 3))), i, 3);
                tblDocumentos1.setValueAt(big.setMoneda(big.getBigDecimal((String) tblDocumentos1.getValueAt(i, 4))), i, 4);
                tblDocumentos1.setValueAt(big.setMoneda(big.getBigDecimal((String) tblDocumentos1.getValueAt(i, 5))), i, 5);

                try {
                    tblDocumentos1.setValueAt(big.setMoneda(big.getBigDecimal((String) tblDocumentos1.getValueAt(i, 7))), i, 7);
                } catch (Exception e) {
                    tblDocumentos1.setValueAt(this.simbolo + " 0", i, 7);
                }
            }

            TableRowSorter modeloOrdenado;
            modeloOrdenado = new TableRowSorter<>(modelo);
            tblDocumentos1.setRowSorter(modeloOrdenado);
            boolean abonoSi = false, facturaSi = false;

            for (int i = 0; i < facturas.length; i++) {
                facturaSi = true;
            }

            for (int i = 0; i < abonos.length; i++) {
                abonoSi = true;
            }

            if (facturaSi) {
                modeloOrdenado.setRowFilter(RowFilter.regexFilter("(?i)" + "FACT", 0));
                primeraFactura = tblDocumentos1.getValueAt(0, 0).toString();
                ultimaFactura = tblDocumentos1.getValueAt(tblDocumentos1.getRowCount() - 1, 0).toString();
            }

            if (abonoSi) {
                modeloOrdenado.setRowFilter(RowFilter.regexFilter("(?i)" + "ABONO", 0));
                primerAbono = tblDocumentos1.getValueAt(0, 0).toString();
                ultimoAbono = tblDocumentos1.getValueAt(tblDocumentos1.getRowCount() - 1, 0).toString();
            }

            modeloOrdenado.setRowFilter(RowFilter.regexFilter("(?i)" + "", 0));

            tblIngresos.setValueAt(big.setMoneda(sumCredito), 0, 1);
            tblIngresos.setValueAt(big.setMoneda(sumContado), 1, 1);
            tblIngresos.setValueAt(big.setMoneda(sumSisteCredito), 2, 1);
            tblIngresos.setValueAt(big.setMoneda(sumFacturas), 3, 1);

            tblIngresos.setValueAt(big.setMoneda(abonosCartera), 5, 1);
            tblIngresos.setValueAt(big.setMoneda(abonosSepare), 6, 1);
            tblIngresos.setValueAt(big.setMoneda(sumAbonos), 7, 1);

            tblIngresos.setValueAt(big.setMoneda(sumFacturasSepare), 9, 1);
            tblIngresos.setValueAt(big.setMoneda(sumCuotaInicial), 10, 1);
            tblIngresos.setValueAt(big.setMoneda(sumDomicilios), 11, 1);
            tblIngresos.setValueAt(big.setMoneda(sumItemDomi), 12, 1);

            BigDecimal subtotalGastos = sumEfectivo.subtract(sumNcReembolzadas).subtract(totalGastosRegistrados);
            tblValoresEsperados.setValueAt(big.setMoneda(subtotalGastos), 1, 1);
            tblValoresEsperados.setValueAt(big.setMoneda(sumCheque), 2, 1);
            tblValoresEsperados.setValueAt(big.setMoneda(sumTarjeta), 3, 1);
            tblValoresEsperados.setValueAt(big.setMoneda(sumNc), 4, 1);
            tblValoresEsperados.setValueAt(big.setMoneda(base), 5, 1);
            tblValoresEsperados.setValueAt(big.setMoneda(recogidaPendiente), 6, 1);
            tblValoresEsperados.setValueAt(big.setMoneda(sumPropinas), 7, 1);

            tblSalidas.setValueAt(big.setMoneda(sumNcReembolzadas), 0, 1);
            tblSalidas.setValueAt(big.setMoneda(sumNcRealizadas), 2, 1);

            BigDecimal ncNoReembolsadas = sumNcRealizadas.subtract(sumNcReembolzadas);
            tblSalidas.setValueAt(big.setMoneda(ncNoReembolsadas), 1, 1);

            calcularTotal();
        }
    }

    public void desdeRecogida(String valor) {

        if (instancias.isImprimirRecogida()) {
            instancias.getReporte().ver_RecogidaParcial(instancias.getInformacionEmpresa(), metodosGenerales.fecha(), instancias.getUsuario(), instancias.getTerminal(), valor,
                    tblValoresEsperados.getValueAt(6, 1).toString());
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
        tblIngresos.setValueAt(this.simbolo + " 0", 10, 1);
        tblIngresos.setValueAt(this.simbolo + " 0", 11, 1);
        tblIngresos.setValueAt(this.simbolo + " 0", 12, 1);

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
    private javax.swing.JButton btnGuardar1;
    private javax.swing.JButton btnGuardarBase;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnReimprimir;
    private javax.swing.JButton btnReimprimir1;
    private javax.swing.JTextField chequePend;
    private javax.swing.JComboBox cmbUsuarios;
    private javax.swing.JTextField cuadre;
    private datechooser.beans.DateChooserCombo dtInicio;
    private javax.swing.JTextField efectivoPend;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
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
    private javax.swing.JLabel lblBannerIngresos;
    private javax.swing.JLabel lblBannerResumen;
    private javax.swing.JLabel mensaje;
    private javax.swing.JTextField ncPend;
    private javax.swing.JPanel pnlDetalleCuadreCaja;
    private javax.swing.JPanel pnlTotalizadosCuaddreCaja;
    private javax.swing.JPanel pnlValoresActuales;
    private javax.swing.JScrollPane scrFormulario;
    private javax.swing.JTextField tarjetaPend;
    private javax.swing.JTable tblDocumentos1;
    private javax.swing.JTable tblIngresos;
    private javax.swing.JTable tblSalidas;
    private javax.swing.JTable tblValoresEsperados;
    private javax.swing.JLabel txtTotal;
    // End of variables declaration//GEN-END:variables
}
