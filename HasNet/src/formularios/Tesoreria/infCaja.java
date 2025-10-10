package formularios.Tesoreria;

import formularios.Ventas.*;
import clases.Instancias;
import clases.Ventas.ndCaja;
import clases.big;
import clases.metodosGenerales;
import clases.productos.ndProducto;
import java.awt.Dimension;
import java.math.BigDecimal;
import javax.swing.JComponent;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class infCaja extends javax.swing.JInternalFrame {

    Instancias instancias;
    metodosGenerales metodos = new metodosGenerales();
    private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension dimBarra = null;

    DefaultTableModel modelo;
    int contContado = 0, contCredito = 0, contAbonos = 0, contEfectivo = 0, contCheque = 0, contTarjeta = 0,
            contNcRealizadas = 0, contNcRecibidas = 0, contCuotasIniciales = 0, contGastos = 0, contNcReembolsadas = 0;
    String primeraFactura = "", ultimaFactura = "", primerAbono = "", ultimoAbono = "";
    boolean calcular;
    String simbolo = "";

    public infCaja() {
        initComponents();

        instancias = Instancias.getInstancias();

        simbolo = instancias.getSimbolo();
        lbConsecutivo.setText("" + instancias.getSql().getNumConsecutivo("CUADRE")[0]);

        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
        dimBarra = Barra.getPreferredSize();
        Barra.setSize(0, 0);
        Barra.setPreferredSize(new Dimension(0, 0));
        setBorder(null);
        repaint();

        modelo = (DefaultTableModel) tblDocumentos1.getModel();

        Object[][] usuarios = instancias.getSql().getUsuarios();

        for (Object[] usuario : usuarios) {
            cmbUsuarios.addItem(usuario[0]);
        }

        pnlControl.setVisible(false);
        mensaje1.setVisible(false);

        cmbUsuarios.setSelectedItem(instancias.getUsuario());
        if (instancias.getUsuario().equals("ADMIN")) {
            calcular = true;
            pnlControl.setVisible(true);
//            cargarTabla();

            Object[][] registros = instancias.getSql().getBancosPendientes();
            if (registros.length > 0) {
                mensaje1.setVisible(true);
            }
        } else {
            btnReimprimir.setVisible(false);
            calcular = false;
            txtVenta.setText(this.simbolo + " 0");
            txtAbonos.setText(this.simbolo + " 0");
            txtContado.setText(this.simbolo + " 0");
            txtSisteCredito.setText(this.simbolo + " 0");
            txtCredito.setText(this.simbolo + " 0");
            txtNcReembolsadas.setText(this.simbolo + " 0");
            txtNcRealizadas.setText(this.simbolo + " 0");
            txtFactSepares.setText(this.simbolo + " 0");
            txtCuotasIniciales.setText(this.simbolo + " 0");
            txtEfectivoSistema.setText(this.simbolo + " 0");
            txtChequeSistema.setText(this.simbolo + " 0");
            txtTarjetaSistema.setText(this.simbolo + " 0");
            txtNcSistema.setText(this.simbolo + " 0");
            txtBaseSistema.setText(this.simbolo + " 0");
            recogidaPend.setText(this.simbolo + " 0");
        }

        Object[][] datos = instancias.getSql().getUltimosPonderados();
        Boolean entro = false;
        for (int i = 0; i < datos.length; i++) {
            if (datos[i][1] == null) {
                metodos.msgAdvertenciaAjustado(this, "Unos productos estan sin ponderado");
                break;
            } else {
                if (big.getMoneda(datos[i][1].toString()).compareTo(BigDecimal.ZERO) <= 0) {
                    entro = true;
                    break;
                }
            }

        }
        if (entro) {
            mensaje2.setVisible(true);
        } else {
            mensaje2.setVisible(false);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrFormulario = new javax.swing.JScrollPane();
        pnlFormulario = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDocumentos1 = new javax.swing.JTable();
        mensaje1 = new javax.swing.JLabel();
        mensaje2 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        lbNit7 = new javax.swing.JLabel();
        tarjetaPend = new javax.swing.JTextField();
        ncPend = new javax.swing.JTextField();
        lbNit8 = new javax.swing.JLabel();
        chequePend = new javax.swing.JTextField();
        lbNit9 = new javax.swing.JLabel();
        efectivoPend = new javax.swing.JTextField();
        lbNit10 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        jSeparator5 = new javax.swing.JSeparator();
        lbNit11 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        mensaje = new javax.swing.JLabel();
        cuadre = new javax.swing.JTextField();
        btnActualizar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnReimprimir = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        pnlControl = new javax.swing.JPanel();
        lbNit6 = new javax.swing.JLabel();
        lbNit5 = new javax.swing.JLabel();
        lbNit4 = new javax.swing.JLabel();
        lbNit3 = new javax.swing.JLabel();
        lbNit16 = new javax.swing.JLabel();
        lbNit15 = new javax.swing.JLabel();
        txtEfectivoSistema = new javax.swing.JLabel();
        txtChequeSistema = new javax.swing.JLabel();
        txtTarjetaSistema = new javax.swing.JLabel();
        txtNcSistema = new javax.swing.JLabel();
        txtBaseSistema = new javax.swing.JLabel();
        recogidaPend = new javax.swing.JLabel();
        lbNit30 = new javax.swing.JLabel();
        txtTotalPropinas = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        lbNit21 = new javax.swing.JLabel();
        lbNit22 = new javax.swing.JLabel();
        lbNit27 = new javax.swing.JLabel();
        lbNit28 = new javax.swing.JLabel();
        txtItemDomi = new javax.swing.JLabel();
        txtVentasDomicilios = new javax.swing.JLabel();
        txtFactSepares = new javax.swing.JLabel();
        txtCuotasIniciales = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jPanel6 = new javax.swing.JPanel();
        lbNit24 = new javax.swing.JLabel();
        lbNit14 = new javax.swing.JLabel();
        lbNit23 = new javax.swing.JLabel();
        txtGastosDelDia = new javax.swing.JTextField();
        gastosPend = new javax.swing.JLabel();
        txtTotalGastos = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        lbNit = new javax.swing.JLabel();
        lbNit1 = new javax.swing.JLabel();
        lbNit13 = new javax.swing.JLabel();
        txtVenta = new javax.swing.JLabel();
        txtCredito = new javax.swing.JLabel();
        txtContado = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        lbNit31 = new javax.swing.JLabel();
        txtSisteCredito = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        lbNit2 = new javax.swing.JLabel();
        lbNit25 = new javax.swing.JLabel();
        lbNit26 = new javax.swing.JLabel();
        txtAbonos = new javax.swing.JLabel();
        txtAbonosCartera = new javax.swing.JLabel();
        txtAbonosSepare = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jPanel5 = new javax.swing.JPanel();
        lbNit17 = new javax.swing.JLabel();
        lbNit20 = new javax.swing.JLabel();
        lbNit29 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        txtNcRealizadas = new javax.swing.JLabel();
        txtNcReembolsadas = new javax.swing.JLabel();
        txtNcNoReembolsadas = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        lbNit18 = new javax.swing.JLabel();
        dtInicio = new datechooser.beans.DateChooserCombo();
        lbNit19 = new javax.swing.JLabel();
        cmbUsuarios = new javax.swing.JComboBox();
        lbNit12 = new javax.swing.JLabel();
        lbConsecutivo = new javax.swing.JLabel();
        btnGuardar2 = new javax.swing.JButton();
        btnGuardar1 = new javax.swing.JButton();
        btnReimprimir1 = new javax.swing.JButton();

        setTitle("Factura");

        pnlFormulario.setBackground(new java.awt.Color(255, 255, 255));

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

        mensaje1.setBackground(new java.awt.Color(255, 255, 0));
        mensaje1.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        mensaje1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mensaje1.setText("ATENCIÓN TIENE CUADRES PENDIENTES PARA INGRESAR A BANCOS");
        mensaje1.setOpaque(true);
        mensaje1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mensaje1MouseClicked(evt);
            }
        });

        mensaje2.setBackground(new java.awt.Color(255, 255, 0));
        mensaje2.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        mensaje2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mensaje2.setText("ATENCIÓN ALGUNOS PRODUCTOS TIENEN PONDERADO EN 0 Ó NEGATIVO ");
        mensaje2.setOpaque(true);
        mensaje2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mensaje2MouseClicked(evt);
            }
        });

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "TIENE", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Century Gothic", 1, 24))); // NOI18N

        lbNit7.setBackground(new java.awt.Color(255, 255, 255));
        lbNit7.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbNit7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNit7.setText("TARJETA");
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
        lbNit8.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbNit8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNit8.setText("NC");
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
        lbNit9.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbNit9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNit9.setText("CHEQUE");
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
        lbNit10.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbNit10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNit10.setText("EFECTIVO");
        lbNit10.setOpaque(true);

        txtTotal.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        txtTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTotal.setText("0");
        txtTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalActionPerformed(evt);
            }
        });

        lbNit11.setBackground(new java.awt.Color(255, 255, 255));
        lbNit11.setFont(new java.awt.Font("Century Gothic", 1, 22)); // NOI18N
        lbNit11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNit11.setText("TOTAL:");
        lbNit11.setOpaque(true);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbNit7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbNit9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbNit10, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                            .addComponent(lbNit8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chequePend)
                            .addComponent(tarjetaPend)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(efectivoPend, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(ncPend)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(lbNit11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(5, 5, 5)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator5))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(efectivoPend)
                    .addComponent(lbNit10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(1, 1, 1)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(chequePend)
                    .addComponent(lbNit9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(1, 1, 1)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tarjetaPend)
                    .addComponent(lbNit7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(1, 1, 1)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ncPend)
                    .addComponent(lbNit8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbNit11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 24))); // NOI18N

        mensaje.setBackground(new java.awt.Color(204, 255, 204));
        mensaje.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        mensaje.setForeground(new java.awt.Color(0, 102, 0));
        mensaje.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mensaje.setText("ATENCION TIENE UN FALTANTE");
        mensaje.setOpaque(true);

        cuadre.setEditable(false);
        cuadre.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
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
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(mensaje, javax.swing.GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
                            .addComponent(cuadre)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnLimpiar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnActualizar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnReimprimir, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(mensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(cuadre, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnActualizar)
                    .addComponent(btnGuardar))
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(btnReimprimir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pnlControl.setBackground(new java.awt.Color(255, 255, 255));
        pnlControl.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DEBE TENER / MEDIOS DE PAGO", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri", 1, 14))); // NOI18N

        lbNit6.setBackground(new java.awt.Color(255, 255, 255));
        lbNit6.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        lbNit6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit6.setText("TARJETA");
        lbNit6.setOpaque(true);

        lbNit5.setBackground(new java.awt.Color(255, 255, 255));
        lbNit5.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        lbNit5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit5.setText("NC PAGADAS");
        lbNit5.setOpaque(true);
        lbNit5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbNit5MouseClicked(evt);
            }
        });

        lbNit4.setBackground(new java.awt.Color(255, 255, 255));
        lbNit4.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        lbNit4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit4.setText("CHEQUE");
        lbNit4.setOpaque(true);

        lbNit3.setBackground(new java.awt.Color(255, 255, 255));
        lbNit3.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        lbNit3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit3.setText("EFECTIVO");
        lbNit3.setOpaque(true);

        lbNit16.setBackground(new java.awt.Color(255, 255, 255));
        lbNit16.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        lbNit16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit16.setText("BASE");
        lbNit16.setOpaque(true);

        lbNit15.setBackground(new java.awt.Color(255, 255, 255));
        lbNit15.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        lbNit15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit15.setText("RECOGIDA PARCIAL");
        lbNit15.setOpaque(true);

        txtEfectivoSistema.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        txtEfectivoSistema.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtEfectivoSistema.setText("0");

        txtChequeSistema.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        txtChequeSistema.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtChequeSistema.setText("0");

        txtTarjetaSistema.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        txtTarjetaSistema.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtTarjetaSistema.setText("0");

        txtNcSistema.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        txtNcSistema.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtNcSistema.setText("0");

        txtBaseSistema.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        txtBaseSistema.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtBaseSistema.setText("0");

        recogidaPend.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        recogidaPend.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        recogidaPend.setText("0");

        lbNit30.setBackground(new java.awt.Color(255, 255, 255));
        lbNit30.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        lbNit30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit30.setText("PROPINAS");
        lbNit30.setOpaque(true);

        txtTotalPropinas.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        txtTotalPropinas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtTotalPropinas.setText("0");

        javax.swing.GroupLayout pnlControlLayout = new javax.swing.GroupLayout(pnlControl);
        pnlControl.setLayout(pnlControlLayout);
        pnlControlLayout.setHorizontalGroup(
            pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlControlLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlControlLayout.createSequentialGroup()
                        .addGroup(pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lbNit3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                            .addComponent(lbNit4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbNit6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTarjetaSistema, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtChequeSistema, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtEfectivoSistema, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(pnlControlLayout.createSequentialGroup()
                        .addGroup(pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lbNit16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbNit5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE))
                        .addGroup(pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlControlLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtBaseSistema, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(pnlControlLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(txtNcSistema, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(pnlControlLayout.createSequentialGroup()
                        .addComponent(lbNit15, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(recogidaPend, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE))
                    .addGroup(pnlControlLayout.createSequentialGroup()
                        .addComponent(lbNit30, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotalPropinas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlControlLayout.setVerticalGroup(
            pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlControlLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbNit3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtEfectivoSistema, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addGroup(pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbNit4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtChequeSistema, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addGroup(pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbNit6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTarjetaSistema, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addGroup(pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbNit5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNcSistema, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addGroup(pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbNit16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtBaseSistema, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addGroup(pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(recogidaPend, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbNit15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addGroup(pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTotalPropinas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbNit30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(8, 8, 8))
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Otros"));

        lbNit21.setBackground(new java.awt.Color(255, 255, 255));
        lbNit21.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lbNit21.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNit21.setText("Facturas Separes");
        lbNit21.setOpaque(true);

        lbNit22.setBackground(new java.awt.Color(255, 255, 255));
        lbNit22.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lbNit22.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNit22.setText("Cuotas Iniciales");
        lbNit22.setOpaque(true);

        lbNit27.setBackground(new java.awt.Color(255, 255, 255));
        lbNit27.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lbNit27.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNit27.setText("Item domicilio:");
        lbNit27.setOpaque(true);

        lbNit28.setBackground(new java.awt.Color(255, 255, 255));
        lbNit28.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lbNit28.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNit28.setText("Ventas a domicilio:");
        lbNit28.setOpaque(true);

        txtItemDomi.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        txtItemDomi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtItemDomi.setText("0");

        txtVentasDomicilios.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        txtVentasDomicilios.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtVentasDomicilios.setText("0");

        txtFactSepares.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        txtFactSepares.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtFactSepares.setText("0");

        txtCuotasIniciales.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        txtCuotasIniciales.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtCuotasIniciales.setText("0");

        jSeparator6.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbNit28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbNit27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbNit22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbNit21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtItemDomi, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                            .addComponent(txtVentasDomicilios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtFactSepares, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtCuotasIniciales, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jSeparator6)
                        .addContainerGap())))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNit21)
                    .addComponent(txtFactSepares, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(4, 4, 4)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNit22)
                    .addComponent(txtCuotasIniciales))
                .addGap(5, 5, 5)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNit28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtVentasDomicilios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(4, 4, 4)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNit27)
                    .addComponent(txtItemDomi))
                .addGap(76, 76, 76))
        );

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlControl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlControl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Gastos"));

        lbNit24.setBackground(new java.awt.Color(255, 255, 255));
        lbNit24.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        lbNit24.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNit24.setText("TOTAL GASTOS");
        lbNit24.setOpaque(true);

        lbNit14.setBackground(new java.awt.Color(255, 255, 255));
        lbNit14.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lbNit14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNit14.setText("Registrados");
        lbNit14.setOpaque(true);

        lbNit23.setBackground(new java.awt.Color(255, 255, 255));
        lbNit23.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lbNit23.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNit23.setText("No registrados");
        lbNit23.setOpaque(true);

        txtGastosDelDia.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        txtGastosDelDia.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtGastosDelDia.setText("0");
        txtGastosDelDia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGastosDelDiaActionPerformed(evt);
            }
        });
        txtGastosDelDia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtGastosDelDiaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtGastosDelDiaKeyTyped(evt);
            }
        });

        gastosPend.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        gastosPend.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gastosPend.setText("0");

        txtTotalGastos.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        txtTotalGastos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtTotalGastos.setText("0");

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lbNit23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbNit24, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbNit14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtGastosDelDia, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                                    .addComponent(gastosPend, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTotalGastos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addGap(6, 6, 6))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNit24, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTotalGastos, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNit14)
                    .addComponent(gastosPend))
                .addGap(3, 3, 3)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNit23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtGastosDelDia))
                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Ventas"));

        lbNit.setBackground(new java.awt.Color(255, 255, 255));
        lbNit.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        lbNit.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNit.setText("TOTAL VENTAS");
        lbNit.setOpaque(true);

        lbNit1.setBackground(new java.awt.Color(255, 255, 255));
        lbNit1.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lbNit1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNit1.setText("Ventas a credito");
        lbNit1.setOpaque(true);

        lbNit13.setBackground(new java.awt.Color(255, 255, 255));
        lbNit13.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lbNit13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNit13.setText("Ventas a contado");
        lbNit13.setOpaque(true);

        txtVenta.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        txtVenta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtVenta.setText("0");

        txtCredito.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        txtCredito.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtCredito.setText("0");

        txtContado.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        txtContado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtContado.setText("0");

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        lbNit31.setBackground(new java.awt.Color(255, 255, 255));
        lbNit31.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lbNit31.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNit31.setText("Ventas a sistecredito");
        lbNit31.setOpaque(true);

        txtSisteCredito.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        txtSisteCredito.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtSisteCredito.setText("0");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jSeparator1)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lbNit31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbNit1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbNit13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbNit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtCredito, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                            .addComponent(txtVenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtContado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtSisteCredito, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbNit, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(txtVenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbNit1)
                    .addComponent(txtCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtContado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbNit13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbNit31)
                    .addComponent(txtSisteCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Abonos"));

        lbNit2.setBackground(new java.awt.Color(255, 255, 255));
        lbNit2.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        lbNit2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNit2.setText("TOTAL ABONOS");
        lbNit2.setOpaque(true);

        lbNit25.setBackground(new java.awt.Color(255, 255, 255));
        lbNit25.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lbNit25.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNit25.setText("Cartera");
        lbNit25.setOpaque(true);

        lbNit26.setBackground(new java.awt.Color(255, 255, 255));
        lbNit26.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lbNit26.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNit26.setText("Plan separe");
        lbNit26.setOpaque(true);

        txtAbonos.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        txtAbonos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtAbonos.setText("0");

        txtAbonosCartera.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        txtAbonosCartera.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtAbonosCartera.setText("0");

        txtAbonosSepare.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        txtAbonosSepare.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtAbonosSepare.setText("0");

        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jSeparator3)
                        .addContainerGap())
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(lbNit2, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAbonos, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lbNit26, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                            .addComponent(lbNit25, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtAbonosCartera, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtAbonosSepare, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbNit2, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(txtAbonos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNit25, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAbonosCartera, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNit26)
                    .addComponent(txtAbonosSepare, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Notas credito"));

        lbNit17.setBackground(new java.awt.Color(255, 255, 255));
        lbNit17.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        lbNit17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit17.setText("TOTAL NC");
        lbNit17.setOpaque(true);

        lbNit20.setBackground(new java.awt.Color(255, 255, 255));
        lbNit20.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lbNit20.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNit20.setText("Reembolsadas");
        lbNit20.setOpaque(true);

        lbNit29.setBackground(new java.awt.Color(255, 255, 255));
        lbNit29.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lbNit29.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNit29.setText("No reembolsadas");
        lbNit29.setOpaque(true);

        jSeparator4.setForeground(new java.awt.Color(0, 0, 0));

        txtNcRealizadas.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        txtNcRealizadas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtNcRealizadas.setText("0");

        txtNcReembolsadas.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        txtNcReembolsadas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtNcReembolsadas.setText("0");

        txtNcNoReembolsadas.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        txtNcNoReembolsadas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtNcNoReembolsadas.setText("0");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lbNit29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbNit20, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbNit17, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNcRealizadas, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNcReembolsadas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNcNoReembolsadas, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNit17, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNcRealizadas, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNcReembolsadas)
                    .addComponent(lbNit20))
                .addGap(3, 3, 3)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNit29)
                    .addComponent(txtNcNoReembolsadas))
                .addContainerGap())
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        lbNit18.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbNit18.setText("Fecha:");

        dtInicio.setFieldFont(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 14));

        lbNit19.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbNit19.setText("Usuario:");

        cmbUsuarios.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        cmbUsuarios.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbUsuariosItemStateChanged(evt);
            }
        });
        cmbUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbUsuariosActionPerformed(evt);
            }
        });
        cmbUsuarios.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cmbUsuariosFocusGained(evt);
            }
        });

        lbNit12.setBackground(new java.awt.Color(204, 204, 204));
        lbNit12.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        lbNit12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit12.setText("Cuadre No.");
        lbNit12.setOpaque(true);

        lbConsecutivo.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        lbConsecutivo.setForeground(new java.awt.Color(255, 0, 0));
        lbConsecutivo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbConsecutivo.setText("1");
        lbConsecutivo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnGuardar2.setBackground(new java.awt.Color(46, 204, 113));
        btnGuardar2.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnGuardar2.setText("BASE");
        btnGuardar2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar2.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnGuardar2.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnGuardar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardar2ActionPerformed(evt);
            }
        });

        btnGuardar1.setBackground(new java.awt.Color(241, 148, 138));
        btnGuardar1.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnGuardar1.setText("RECOGIDA");
        btnGuardar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnGuardar1.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnGuardar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardar1ActionPerformed(evt);
            }
        });

        btnReimprimir1.setBackground(new java.awt.Color(247, 220, 111));
        btnReimprimir1.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnReimprimir1.setText("IMPR. BASE ");
        btnReimprimir1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReimprimir1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnReimprimir1.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnReimprimir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReimprimir1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbNit18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dtInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbNit19, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardar2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardar1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnReimprimir1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbNit12, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbConsecutivo, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbConsecutivo, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(lbNit19, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dtInicio, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbNit18, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbNit12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbUsuarios, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGuardar2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnGuardar1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnReimprimir1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout pnlFormularioLayout = new javax.swing.GroupLayout(pnlFormulario);
        pnlFormulario.setLayout(pnlFormularioLayout);
        pnlFormularioLayout.setHorizontalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(mensaje2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(mensaje1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addGroup(pnlFormularioLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 2, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlFormularioLayout.setVerticalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(mensaje1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(mensaje2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                .addGap(3, 3, 3)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        scrFormulario.setViewportView(pnlFormulario);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(scrFormulario)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrFormulario)
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
            BigDecimal totalEfectivo = big.getMoneda(txtEfectivoSistema.getText()).add(big.getMoneda(txtBaseSistema.getText()));

            Object[] vector = {documento, fecha, metodosGenerales.hora(), big.getMoneda(efectivoPend.getText()),
                big.getMoneda(tarjetaPend.getText()), big.getMoneda(chequePend.getText()), big.getMoneda(ncPend.getText()), "cajero",
                big.getMoneda(txtTotal.getText()), big.getMoneda(txtVenta.getText()).add(big.getMoneda(txtAbonos.getText())),
                big.getMoneda(cuadre.getText()),
                usuario, instancias.getTerminal(), big.getMoneda(gastosPend.getText()), big.getMoneda(recogidaPend.getText()),
                big.getMoneda(txtBaseSistema.getText()), big.getMoneda(txtVenta.getText()), big.getMoneda(txtCredito.getText()),
                big.getMoneda(txtContado.getText()), big.getMoneda(txtNcRealizadas.getText()),
                totalEfectivo, big.getMoneda(txtChequeSistema.getText()), big.getMoneda(txtTarjetaSistema.getText()),
                big.getMoneda(txtNcSistema.getText()), contContado, contCredito, contNcRecibidas,
                contNcRealizadas, contEfectivo, contCheque, contTarjeta, contAbonos,
                big.getMoneda(txtAbonos.getText()), primeraFactura, ultimaFactura, primerAbono, ultimoAbono, big.getMoneda(txtFactSepares.getText()),
                big.getMoneda(txtCuotasIniciales.getText()), contCuotasIniciales, contGastos, big.getMoneda(txtNcReembolsadas.getText()), contNcReembolsadas,
                big.getMoneda(txtGastosDelDia.getText()), big.getMoneda(txtAbonosCartera.getText()), big.getMoneda(txtAbonosSepare.getText()),
                big.getMoneda(txtVentasDomicilios.getText()), big.getMoneda(txtItemDomi.getText()), big.getMoneda(txtTotalPropinas.getText()),
                big.getMoneda(txtSisteCredito.getText())
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

            if (big.getMoneda(txtGastosDelDia.getText()).compareTo(BigDecimal.ZERO) > 0) {
                instancias.getEgresos().cargarEgreso("1010", big.getMoneda(txtGastosDelDia.getText()), "", "PAGOS PROVEEDORES",
                        "GASTOS MANUALES DEL CUADRE DE CAJA N° " + documento.replace("CUADRE-", ""), "LOCAL", documento, big.getMoneda("0"),
                        big.getMoneda(txtGastosDelDia.getText()), BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, "");
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

            if (big.getMoneda(tarjetaPend.getText()).compareTo(BigDecimal.ZERO) > 0) {
                System.out.println("entro a guardar en banco");
                dlgMovimientosBanco movimiento = new dlgMovimientosBanco(null, true, "", "", BigDecimal.ZERO, "Ingreso", "CUADRE", big.getMoneda(tarjetaPend.getText()),
                        "Ventas por tarjeta del cuadre de caja del " + dtInicio.getText(), "");
                movimiento.realizarMovimientos();
            }

            if (instancias.getUsuario().equals("ADMIN")) {
                if (big.getMoneda(efectivoPend.getText()).compareTo(BigDecimal.ZERO) > 0) {
                    if (metodos.msgPregunta(this, "¿Ingresar efectivo al banco?") == 0) {
                        dlgMovimientosBanco movimiento = new dlgMovimientosBanco(null, true, "", "", BigDecimal.ZERO, "Ingreso", "CUADRE",
                                big.getMoneda(efectivoPend.getText()), "Cuadre de caja del " + dtInicio.getText(), "");
                        movimiento.setLocationRelativeTo(null);
                        movimiento.setVisible(true);
                    }
                }
            } else {

                System.out.println("no puso ninguna bandera mero pato");
                Object[] letras = {"Cuadre de caja del " + dtInicio.getText(), instancias.getUsuario(),
                    metodos.fechaConsulta(metodosGenerales.fechaHora()), "ENTRADA"};
                Object[] valores = {big.getMoneda(efectivoPend.getText())};

                instancias.getSql().agregarMovimientoBancoPendiente(letras, valores);
                System.out.println(" mero pato");
            }

            lbConsecutivo.setText((String) instancias.getSql().getNumConsecutivo("CUADRE")[0]);

            txtVenta.setText(this.simbolo + " 0");
            txtAbonos.setText(this.simbolo + " 0");
            txtEfectivoSistema.setText(this.simbolo + " 0");
            txtTotalPropinas.setText(this.simbolo + " 0");
            txtTarjetaSistema.setText(this.simbolo + " 0");
            txtNcSistema.setText(this.simbolo + " 0");
            txtChequeSistema.setText(this.simbolo + " 0");
            txtBaseSistema.setText(this.simbolo + " 0");
            txtContado.setText(this.simbolo + " 0");
            txtSisteCredito.setText(this.simbolo + " 0");
            txtCredito.setText(this.simbolo + " 0");
            txtNcReembolsadas.setText(this.simbolo + " 0");
            txtNcRealizadas.setText(this.simbolo + " 0");
            txtFactSepares.setText(this.simbolo + " 0");
            txtCuotasIniciales.setText(this.simbolo + " 0");
            recogidaPend.setText(this.simbolo + " 0");
            txtGastosDelDia.setText(this.simbolo + " 0");

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
        tarjetaPend.setText(this.simbolo + " 0");
        efectivoPend.setText(this.simbolo + " 0");
        chequePend.setText(this.simbolo + " 0");
        ncPend.setText(this.simbolo + " 0");
        gastosPend.setText(this.simbolo + " 0");
        recogidaPend.setText(this.simbolo + " 0");
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
        Object[][] datos = instancias.getSql().getUltimosPonderados();
        Boolean entro = false;

        for (int i = 0; i < datos.length; i++) {
            BigDecimal total = BigDecimal.ZERO;
            if (datos[i][1] == null) {
                total = BigDecimal.ZERO;
            } else {
                total = big.getMoneda(datos[i][1].toString());
            }

            if (total.compareTo(BigDecimal.ZERO) <= 0) {
                entro = true;
                break;
            }
        }

        if (entro) {
            mensaje2.setVisible(true);
        } else {
            mensaje2.setVisible(false);
        }

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

    private void btnGuardar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar2ActionPerformed
        if (!instancias.getUsuario().equals("ADMIN")) {
            dlgPedirContrasena contrasena = new dlgPedirContrasena(null, true, "cajaBase");
            contrasena.setLocationRelativeTo(null);
            contrasena.setVisible(true);
        } else {
            dlgRecogidaParcial parcial = new dlgRecogidaParcial(null, true, "cajaBase");
            parcial.setLocationRelativeTo(null);
            parcial.setVisible(true);
        }
    }//GEN-LAST:event_btnGuardar2ActionPerformed

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

    private void mensaje1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mensaje1MouseClicked
        dlgMovimientosPendientes movimiento = new dlgMovimientosPendientes(null, true);
        movimiento.setLocationRelativeTo(null);
        movimiento.setVisible(true);
    }//GEN-LAST:event_mensaje1MouseClicked

    private void lbNit5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbNit5MouseClicked

    }//GEN-LAST:event_lbNit5MouseClicked

    private void mensaje2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mensaje2MouseClicked
        dlgPonderadoNegativo ponderadoNegativo = new dlgPonderadoNegativo(null, true, null);
        ponderadoNegativo.setVisible(true);
    }//GEN-LAST:event_mensaje2MouseClicked

    private void txtGastosDelDiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGastosDelDiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGastosDelDiaActionPerformed

    private void txtGastosDelDiaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGastosDelDiaKeyReleased
        if (txtGastosDelDia.getText().equals("") || txtGastosDelDia.getText().equals(this.simbolo) || txtGastosDelDia.getText().equals(this.simbolo + " ")) {
            txtGastosDelDia.setText("0");
        }

        txtGastosDelDia.setText(big.setMoneda(big.getMoneda(txtGastosDelDia.getText())));
        calcularTotal();
    }//GEN-LAST:event_txtGastosDelDiaKeyReleased

    private void txtGastosDelDiaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGastosDelDiaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGastosDelDiaKeyTyped

    private void btnReimprimir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReimprimir1ActionPerformed
        if (instancias.getUsuario().equals("ADMIN")) {
            instancias.getReporte().verBase(" where fecha = '" + metodos.desdeDate(dtInicio.getCurrent()) + "' ;", instancias.getInformacionEmpresa());
        } else {
            instancias.getReporte().verBase(" where usuario ='" + Instancias.getInstancias().getUsuario() + "'  and fecha = '" + metodos.desdeDate(dtInicio.getCurrent()) + "' ;", instancias.getInformacionEmpresa());
        }
    }//GEN-LAST:event_btnReimprimir1ActionPerformed

    private void txtTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalActionPerformed

    private void cmbUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbUsuariosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbUsuariosActionPerformed

    private void tblDocumentos1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDocumentos1MouseClicked

    }//GEN-LAST:event_tblDocumentos1MouseClicked

    private void tblDocumentos1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDocumentos1MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tblDocumentos1MousePressed

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

            txtTotalGastos.setText(big.setMoneda(big.getMoneda(gastosPend.getText()).add(big.getMoneda(txtGastosDelDia.getText()))));

            BigDecimal total = big.getMoneda(efectivoPend.getText()).add(big.getMoneda(chequePend.getText())
                    .add(big.getMoneda(tarjetaPend.getText())).add(big.getMoneda(ncPend.getText())))
                    .add(big.getMoneda(recogidaPend.getText())).add(big.getMoneda(txtGastosDelDia.getText()));
            txtTotal.setText(big.setMoneda(total));

            BigDecimal sumCuadre = big.getMoneda(txtEfectivoSistema.getText()).add(big.getMoneda(txtChequeSistema.getText())).add(big.getMoneda(txtTarjetaSistema.getText())).
                    add(big.getMoneda(txtNcSistema.getText())).add(big.getMoneda(txtCuotasIniciales.getText())).add(big.getMoneda(txtTotalPropinas.getText()))
                    .add(big.getMoneda(txtBaseSistema.getText()));

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

        if (instancias.getUsuario().equals("ADMIN")) {
            Object[][] registros = instancias.getSql().getBancosPendientes();
            if (registros.length > 0) {
                mensaje1.setVisible(true);
            } else {
                mensaje1.setVisible(false);
            }
        }

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
            String fecha2 = metodos.fecha2(metodos.sumarFecha(metodos.fecha(metodos.desdeDate(dtInicio.getCurrent())), 1));

            fecha1 = metodos.fechaConsulta(fecha1);
            fecha2 = metodos.fechaConsulta(metodos.fecha(fecha2));

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

            if (usuario.equals("ADMIN")) {
                facturas = instancias.getSql().getRegistrosFacturaAdmin(instancias.getTerminal(), fecha);
                separes = instancias.getSql().getRegistrosSepareAdmin(instancias.getTerminal(), fecha);
                abonos = instancias.getSql().getRegistrosAbonoAdmin(instancias.getTerminal(), fecha);
                notasCredito = instancias.getSql().getRegistrosNotasCreditoAdmin(instancias.getTerminal(), fecha);

                try {
                    gastosPend.setText(big.setMoneda(big.getBigDecimal(instancias.getSql().gastosEgresosAdmin(fechaGastos1))));
                    contGastos = Integer.parseInt(instancias.getSql().gastosEgresosContAdmin(fechaGastos1));
                } catch (Exception ex) {
                    gastosPend.setText(big.setMoneda(BigDecimal.ZERO));
                }
            } else {
                facturas = instancias.getSql().getRegistrosFactura(instancias.getTerminal(), usuario, fecha);
                separes = instancias.getSql().getRegistrosPlanSepare(instancias.getTerminal(), usuario, fecha);
                abonos = instancias.getSql().getRegistrosAbono(instancias.getTerminal(), usuario, fecha);
                notasCredito = instancias.getSql().getRegistrosNotasCredito(instancias.getTerminal(), usuario, fecha);

                try {
                    gastosPend.setText(big.setMoneda(big.getBigDecimal(instancias.getSql().gastosEgresos(fechaGastos1, usuario))));
                    contGastos = Integer.parseInt(instancias.getSql().gastosEgresosCont(fechaGastos1, usuario));
                } catch (Exception ex) {
                    gastosPend.setText(big.setMoneda(BigDecimal.ZERO));
                }
            }

            txtTotalGastos.setText(big.setMoneda(big.getMoneda(gastosPend.getText()).add(big.getMoneda(txtGastosDelDia.getText()))));

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

//            txtVenta.setText(big.setMoneda(sumFacturas.subtract(sumNcRealizadas)));
            txtAbonosCartera.setText(big.setMoneda(abonosCartera));
            txtAbonosSepare.setText(big.setMoneda(abonosSepare));
            txtItemDomi.setText(big.setMoneda(sumItemDomi));
            txtVenta.setText(big.setMoneda(sumFacturas));
            txtTotalPropinas.setText(big.setMoneda(sumPropinas));
            txtCredito.setText(big.setMoneda(sumCredito));
            txtSisteCredito.setText(big.setMoneda(sumSisteCredito));
            txtContado.setText(big.setMoneda(sumContado));
            txtAbonos.setText(big.setMoneda(sumAbonos));
            txtVentasDomicilios.setText(big.setMoneda(sumDomicilios));

            BigDecimal subtotalGastos = BigDecimal.ZERO;
            subtotalGastos = big.getMoneda(gastosPend.getText()).add(big.getMoneda(txtGastosDelDia.getText()));
            subtotalGastos = sumEfectivo.subtract(sumNcReembolzadas).subtract(subtotalGastos);

            txtEfectivoSistema.setText(big.setMoneda(subtotalGastos));
            txtTarjetaSistema.setText(big.setMoneda(sumTarjeta));
            txtNcSistema.setText(big.setMoneda(sumNc));
            txtChequeSistema.setText(big.setMoneda(sumCheque));
            recogidaPend.setText(big.setMoneda(recogidaPendiente));
            txtNcReembolsadas.setText(big.setMoneda(sumNcReembolzadas));
//            txtNcRealizadas.setText(big.setMoneda(sumNcRealizadas.add(sumNcReembolzadas)));
            txtNcRealizadas.setText(big.setMoneda(sumNcRealizadas));
            txtFactSepares.setText(big.setMoneda(sumFacturasSepare));
            txtCuotasIniciales.setText(big.setMoneda(sumCuotaInicial));
            txtBaseSistema.setText(big.setMoneda(base));

            BigDecimal ncNoReembolsadas = big.getMoneda(txtNcRealizadas.getText()).subtract(big.getMoneda(txtNcReembolsadas.getText()));
            txtNcNoReembolsadas.setText(big.setMoneda(ncNoReembolsadas));

            calcularTotal();
        }
    }

    public void desdeRecogida(String valor) {

        if (instancias.isImprimirRecogida()) {
            instancias.getReporte().ver_RecogidaParcial(instancias.getInformacionEmpresa(), metodosGenerales.fecha(), instancias.getUsuario(), instancias.getTerminal(), valor, recogidaPend.getText());
        }

        btnActualizarActionPerformed(null);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnGuardar1;
    private javax.swing.JButton btnGuardar2;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnReimprimir;
    private javax.swing.JButton btnReimprimir1;
    private javax.swing.JTextField chequePend;
    private javax.swing.JComboBox cmbUsuarios;
    private javax.swing.JTextField cuadre;
    private datechooser.beans.DateChooserCombo dtInicio;
    private javax.swing.JTextField efectivoPend;
    private javax.swing.JLabel gastosPend;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JLabel lbConsecutivo;
    private javax.swing.JLabel lbNit;
    private javax.swing.JLabel lbNit1;
    private javax.swing.JLabel lbNit10;
    private javax.swing.JLabel lbNit11;
    private javax.swing.JLabel lbNit12;
    private javax.swing.JLabel lbNit13;
    private javax.swing.JLabel lbNit14;
    private javax.swing.JLabel lbNit15;
    private javax.swing.JLabel lbNit16;
    private javax.swing.JLabel lbNit17;
    private javax.swing.JLabel lbNit18;
    private javax.swing.JLabel lbNit19;
    private javax.swing.JLabel lbNit2;
    private javax.swing.JLabel lbNit20;
    private javax.swing.JLabel lbNit21;
    private javax.swing.JLabel lbNit22;
    private javax.swing.JLabel lbNit23;
    private javax.swing.JLabel lbNit24;
    private javax.swing.JLabel lbNit25;
    private javax.swing.JLabel lbNit26;
    private javax.swing.JLabel lbNit27;
    private javax.swing.JLabel lbNit28;
    private javax.swing.JLabel lbNit29;
    private javax.swing.JLabel lbNit3;
    private javax.swing.JLabel lbNit30;
    private javax.swing.JLabel lbNit31;
    private javax.swing.JLabel lbNit4;
    private javax.swing.JLabel lbNit5;
    private javax.swing.JLabel lbNit6;
    private javax.swing.JLabel lbNit7;
    private javax.swing.JLabel lbNit8;
    private javax.swing.JLabel lbNit9;
    private javax.swing.JLabel mensaje;
    private javax.swing.JLabel mensaje1;
    private javax.swing.JLabel mensaje2;
    private javax.swing.JTextField ncPend;
    private javax.swing.JPanel pnlControl;
    private javax.swing.JPanel pnlFormulario;
    private javax.swing.JLabel recogidaPend;
    private javax.swing.JScrollPane scrFormulario;
    private javax.swing.JTextField tarjetaPend;
    private javax.swing.JTable tblDocumentos1;
    private javax.swing.JLabel txtAbonos;
    private javax.swing.JLabel txtAbonosCartera;
    private javax.swing.JLabel txtAbonosSepare;
    private javax.swing.JLabel txtBaseSistema;
    private javax.swing.JLabel txtChequeSistema;
    private javax.swing.JLabel txtContado;
    private javax.swing.JLabel txtCredito;
    private javax.swing.JLabel txtCuotasIniciales;
    private javax.swing.JLabel txtEfectivoSistema;
    private javax.swing.JLabel txtFactSepares;
    private javax.swing.JTextField txtGastosDelDia;
    private javax.swing.JLabel txtItemDomi;
    private javax.swing.JLabel txtNcNoReembolsadas;
    private javax.swing.JLabel txtNcRealizadas;
    private javax.swing.JLabel txtNcReembolsadas;
    private javax.swing.JLabel txtNcSistema;
    private javax.swing.JLabel txtSisteCredito;
    private javax.swing.JLabel txtTarjetaSistema;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JLabel txtTotalGastos;
    private javax.swing.JLabel txtTotalPropinas;
    private javax.swing.JLabel txtVenta;
    private javax.swing.JLabel txtVentasDomicilios;
    // End of variables declaration//GEN-END:variables
}
