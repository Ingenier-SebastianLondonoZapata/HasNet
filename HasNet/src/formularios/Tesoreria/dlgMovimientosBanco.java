package formularios.Tesoreria;

import clases.ImagePreviewPanel;
import clases.Instancias;
import Utilidades.BaseDatos.SQL;
import clases.big;
import clases.metodosGenerales;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;

public class dlgMovimientosBanco extends javax.swing.JDialog {

    private Instancias instancias;
    DefaultTableModel modelo;
    String factura, lugarDesde, simbolo = "";
    metodosGenerales metodos = new metodosGenerales();
    public Path FROM2;
    Boolean saltarPasos = false;

    public dlgMovimientosBanco(java.awt.Frame parent, boolean modal, String cuenta, String numero, BigDecimal monto, String entrada,
            String desde, BigDecimal valor, String nota, String lugar) {
        super(parent, modal);
        initComponents();

        instancias = Instancias.getInstancias();

        pnlInvisible.setVisible(false);

        if (entrada.equals("Ingreso")) {
            cmbBancos.setSelectedIndex(0);
        } else {
            cmbBancos.setSelectedIndex(1);
        }

        simbolo = instancias.getSimbolo();
        lugarDesde = lugar;

        if (desde.equals("")) {
            jPanel5.setVisible(false);
            cmbBancos.setEnabled(true);
            txtCuenta.setVisible(true);
            txtNumero.setVisible(true);
        } else {
            jPanel5.setVisible(true);
            cmbBancos.setEnabled(false);
            txtCuenta.setVisible(false);
            txtNumero.setVisible(false);

            txtNota.setText(nota);
            txtNota.setEnabled(false);

            cmbBancos1.removeAllItems();
            cmbBancos1.addItem("Escoja un banco...");
            Object[][] bancos = instancias.getSql().getBancos();
            for (int i = 0; i < bancos.length; i++) {
                String nombre = bancos[i][0] + " - " + bancos[i][1];
                cmbBancos1.addItem(nombre);
            }

            txtValor.setText(big.setMoneda(valor));
            txtValor.setEnabled(false);

            calcularTotal();
        }

        this.setLocationRelativeTo(null);
        this.setTitle("Movimientos");
        metodosGenerales metodos = new metodosGenerales();

        jLabel8.setVisible(false);
        txtNumero.setText("Cuenta número: " + numero);
        txtCuenta.setText(cuenta);
        txtSaldoActual.setText(big.setMoneda(monto));

//        this.getRootPane().registerKeyboardAction(accion("cerrar", this), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
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

        jPopupMenu1 = new javax.swing.JPopupMenu();
        popBorrar = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        txtNumero = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lbNit1 = new javax.swing.JLabel();
        cmbBancos = new javax.swing.JComboBox();
        txtCuenta = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtSaldoActual = new javax.swing.JLabel();
        txtMovimiento = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtValor = new javax.swing.JTextField();
        jButton9 = new javax.swing.JButton();
        jScrollPane11 = new javax.swing.JScrollPane();
        tblRecibos = new javax.swing.JTable();
        lbNit4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtNota = new javax.swing.JTextArea();
        btnGuardar = new javax.swing.JButton();
        txtTotal = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        lbNit3 = new javax.swing.JLabel();
        cmbBancos1 = new javax.swing.JComboBox();
        txtNumero1 = new javax.swing.JLabel();
        pnlInvisible = new javax.swing.JPanel();
        lbNit9 = new javax.swing.JLabel();
        txtNombreAnexoConsulta = new javax.swing.JTextField();

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
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtNumero.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        txtNumero.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtNumero.setText("Cuenta número:");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        lbNit1.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbNit1.setText("TIPO DE MOVIMIENTO");
        lbNit1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lbNit1KeyReleased(evt);
            }
        });

        cmbBancos.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        cmbBancos.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ENTRADA DE DINERO", "SALIDA DE DINERO" }));
        cmbBancos.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbBancosItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbNit1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmbBancos, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(103, 103, 103))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmbBancos)
                    .addComponent(lbNit1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );

        txtCuenta.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        txtCuenta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtCuenta.setText("123");

        jLabel4.setFont(new java.awt.Font("Century Gothic", 1, 30)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("MOVIMIENTO");

        txtSaldoActual.setFont(new java.awt.Font("Century Gothic", 1, 30)); // NOI18N
        txtSaldoActual.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtSaldoActual.setText("0");

        txtMovimiento.setFont(new java.awt.Font("Century Gothic", 1, 30)); // NOI18N
        txtMovimiento.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtMovimiento.setText("0");

        jLabel7.setFont(new java.awt.Font("Century Gothic", 1, 30)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("SALDO FINAL");

        jLabel8.setFont(new java.awt.Font("Century Gothic", 1, 30)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("-");

        jLabel9.setFont(new java.awt.Font("Century Gothic", 1, 30)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("SALDO ACTUAL");

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 30)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("VALOR:");

        txtValor.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        txtValor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtValor.setText("0");
        txtValor.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtValor.setName("CC/NIT"); // NOI18N
        txtValor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtValorKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtValorKeyTyped(evt);
            }
        });

        jButton9.setBackground(new java.awt.Color(204, 204, 204));
        jButton9.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscarInfo.png"))); // NOI18N
        jButton9.setText("ADJUNTO");
        jButton9.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        tblRecibos.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblRecibos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre del archivo", "url"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblRecibos.setComponentPopupMenu(jPopupMenu1);
        tblRecibos.setRowHeight(19);
        tblRecibos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblRecibosMouseClicked(evt);
            }
        });
        jScrollPane11.setViewportView(tblRecibos);
        if (tblRecibos.getColumnModel().getColumnCount() > 0) {
            tblRecibos.getColumnModel().getColumn(1).setMinWidth(0);
            tblRecibos.getColumnModel().getColumn(1).setPreferredWidth(0);
            tblRecibos.getColumnModel().getColumn(1).setMaxWidth(0);
        }

        lbNit4.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbNit4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit4.setText("Observaciones");
        lbNit4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lbNit4KeyReleased(evt);
            }
        });

        txtNota.setColumns(20);
        txtNota.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtNota.setLineWrap(true);
        txtNota.setRows(2);
        txtNota.setWrapStyleWord(true);
        txtNota.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jScrollPane1.setViewportView(txtNota);

        btnGuardar.setBackground(new java.awt.Color(46, 204, 113));
        btnGuardar.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
        btnGuardar.setText("GUARDAR ");
        btnGuardar.setToolTipText("Ctrl+G");
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnGuardar.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        btnGuardar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                btnGuardarKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(186, 186, 186)
                        .addComponent(jLabel1)
                        .addGap(30, 30, 30)
                        .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbNit4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jScrollPane1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnGuardar))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jScrollPane11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton9)))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtValor)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(23, 23, 23)
                .addComponent(lbNit4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addGap(14, 14, 14))
        );

        txtTotal.setFont(new java.awt.Font("Century Gothic", 1, 30)); // NOI18N
        txtTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtTotal.setText("0");

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        lbNit3.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbNit3.setText("CUENTA:");
        lbNit3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lbNit3KeyReleased(evt);
            }
        });

        cmbBancos1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        cmbBancos1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbBancos1ItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbNit3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmbBancos1, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(156, 156, 156))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmbBancos1)
                    .addComponent(lbNit3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );

        txtNumero1.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        txtNumero1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtNumero1.setText("X");
        txtNumero1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtNumero1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 568, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addGap(31, 31, 31)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addComponent(jLabel8)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtSaldoActual, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtMovimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(txtCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(14, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(txtNumero1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(txtNumero1)
                .addGap(0, 0, 0)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtNumero)
                    .addComponent(txtCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSaldoActual, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(txtMovimiento, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(15, 15, 15)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12))
        );

        lbNit9.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit9.setText("Nombre:");

        txtNombreAnexoConsulta.setEditable(false);
        txtNombreAnexoConsulta.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        javax.swing.GroupLayout pnlInvisibleLayout = new javax.swing.GroupLayout(pnlInvisible);
        pnlInvisible.setLayout(pnlInvisibleLayout);
        pnlInvisibleLayout.setHorizontalGroup(
            pnlInvisibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInvisibleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbNit9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNombreAnexoConsulta)
                .addGap(189, 189, 189))
        );
        pnlInvisibleLayout.setVerticalGroup(
            pnlInvisibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInvisibleLayout.createSequentialGroup()
                .addGroup(pnlInvisibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNombreAnexoConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNit9, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlInvisible, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(pnlInvisible, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        try {
            String cuenta = cmbBancos1.getSelectedItem().toString();
            if (cuenta.equals("Escoja un banco...")) {
                metodos.msgError(null, "Seleccione un banco");
                return;
            }
        } catch (Exception e) {
        }

        BigDecimal num = BigDecimal.ZERO;

        try {
            num = big.getMoneda(txtValor.getText());
        } catch (Exception e) {
        }

        if (num.compareTo(BigDecimal.ZERO) <= 0) {
            metodos.msgError(null, "Ingrese el valor del movimiento");
            txtValor.requestFocus();
            return;
        }

        String tipo;
        if (cmbBancos.getSelectedIndex() == 0) {
            tipo = "Entrada";
        } else {
            tipo = "Salida";
        }

        String numero;
        String cuenta;
        if (jPanel5.isVisible()) {
            numero = cmbBancos1.getSelectedItem().toString().split(" - ")[1];
            cuenta = cmbBancos1.getSelectedItem().toString().split(" - ")[0];
        } else {
            numero = txtNumero.getText().replace("Cuenta número: ", "");
            cuenta = txtCuenta.getText();
        }

        String recibo = "";
        for (int j = 0; j < tblRecibos.getRowCount(); j++) {
            recibo = recibo + tblRecibos.getValueAt(j, 0) + "," + tblRecibos.getValueAt(j, 1) + ";";
        }
        if (!recibo.equals("")) {
            recibo = recibo.substring(0, recibo.length() - 1);
        }

        String doc = "";
        try {
            doc = tblRecibos.getValueAt(0, 0).toString();
        } catch (Exception e) {
        }

        String consecutivoMovimiento = "MOVBANC-" + instancias.getSql().getNumConsecutivo("MOVBANC")[0];

        Object[] vector = {consecutivoMovimiento, cuenta, numero, txtNota.getText(), instancias.getUsuario(),
            metodos.fechaConsulta(metodosGenerales.fecha()), tipo, recibo, metodosGenerales.hora(), doc};
        Object[] vector1 = {big.getMoneda(txtSaldoActual.getText()), big.getMoneda(txtValor.getText()), big.getMoneda(txtTotal.getText())};

        if (!instancias.getSql().agregarMovimientoBanco(vector, vector1)) {
            metodos.msgError(null, "Hubo un problema al guardar el movimiento");
            return;
        } else {
            if (!saltarPasos) {
                metodos.msgExito(null, "Movimiento registrado con exito");
            }

            instancias.getSql().aumentarConsecutivo("MOVBANC", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("MOVBANC")[0]) + 1);

            if (lugarDesde.equals("movEgreso")) {
                instancias.getEgresos().setConsecutivoBanco(consecutivoMovimiento);
            }
            instancias.getSql().modificarMontoBanco(big.getMoneda(txtTotal.getText()), numero);
            instancias.getBancos().refrescarDatos();
            this.dispose();
        }

        saltarPasos = false;
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnGuardarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnGuardarKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnGuardarActionPerformed(null);
        }
    }//GEN-LAST:event_btnGuardarKeyReleased

    private void lbNit1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lbNit1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_lbNit1KeyReleased

    private void txtValorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorKeyReleased
        if (txtValor.getText().equals("") || txtValor.getText().equals(this.simbolo) || txtValor.getText().equals(this.simbolo + " ")) {
            txtValor.setText("0");
        }

        txtValor.setText(big.setMoneda(big.getMoneda(txtValor.getText())));
        if (cmbBancos1.getSelectedIndex() != 0) {
            calcularTotal();
        }
    }//GEN-LAST:event_txtValorKeyReleased

    private void txtValorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValorKeyTyped

    private void cmbBancosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbBancosItemStateChanged
        simbolo = instancias.getSimbolo();
        if (cmbBancos.getSelectedIndex() == 0) {
            jLabel8.setVisible(false);
        } else {
            jLabel8.setVisible(true);
        }

        txtValor.setText(this.simbolo + " 0");
        txtMovimiento.setText(this.simbolo + " 0");
        txtTotal.setText(this.simbolo + " 0");
    }//GEN-LAST:event_cmbBancosItemStateChanged

    private void lbNit3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lbNit3KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_lbNit3KeyReleased

    private void cmbBancos1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbBancos1ItemStateChanged
        String monto = "0";

        try {
            monto = instancias.getSql().montoBanco(cmbBancos1.getSelectedItem().toString().split(" - ")[1]);
        } catch (Exception e) {
        }

        txtSaldoActual.setText(big.setMoneda(big.getMoneda(monto)));
        btnGuardar.setEnabled(true);

        calcularTotal();
    }//GEN-LAST:event_cmbBancos1ItemStateChanged

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        if (tblRecibos.getRowCount() == 1) {
            metodos.msgError(null, "Solo se puede un archivo por movimiento");
            return;
        }

        try {
            JFileChooser chooser = new JFileChooser();
            ImagePreviewPanel preview = new ImagePreviewPanel();
            chooser.setAccessory(preview);
            chooser.addPropertyChangeListener(preview);

            int respuesta = chooser.showSaveDialog(this);

            if (respuesta == JFileChooser.APPROVE_OPTION) {
                FROM2 = Paths.get(chooser.getSelectedFile().getAbsolutePath());
            }
            txtNombreAnexoConsulta.setText(chooser.getSelectedFile().getName());
        } catch (Exception e) {
        }

        if (txtNombreAnexoConsulta.getText().contains(",") || txtNombreAnexoConsulta.getText().contains(";")) {
            metodos.msgError(null, "El nombre del archivo no puede contener comas ',' ni ';' ");
            return;
        }

        String id = "";

        id = instancias.getSql().getNumConsecutivo("RECIBO")[0] + "-" + txtNombreAnexoConsulta.getText();

        //        Path TO = Paths.get(System.getProperty("user.dir") + "\\imagenes\\medico\\IMG-" + id + ".jpg");
        Path TO = Paths.get(System.getProperty("user.dir") + "\\imagenes\\recibos\\" + id);

        //sobreescribir el fichero de destino, si existe, y copiar
        // los atributos, incluyendo los permisos rwx
        CopyOption[] options = new CopyOption[]{
            StandardCopyOption.REPLACE_EXISTING,
            StandardCopyOption.COPY_ATTRIBUTES
        };

        try {
            Files.copy(FROM2, TO, options);
        } catch (Exception ex) {
            metodos.msgError(null, "Hubo un error al cargar el archivo");
            return;
        }

        if (!instancias.getSql().aumentarConsecutivo("RECIBO", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("RECIBO")[0]) + 1)) {
            metodos.msgError(null, "Hubo un problema al guardar el codigo de la imagen");
        }

        DefaultTableModel modeloTabla = (DefaultTableModel) tblRecibos.getModel();
        modeloTabla.addRow(new Object[]{id, TO.toString()});
        txtNombreAnexoConsulta.setText("");
        FROM2 = null;

    }//GEN-LAST:event_jButton9ActionPerformed

    private void tblRecibosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblRecibosMouseClicked

        if (evt.getClickCount() > 1) {
            //ruta del archivo en el pc
            String file = tblRecibos.getValueAt(tblRecibos.getSelectedRow(), 1).toString();
            try {
                Runtime.getRuntime().exec("rundll32 SHELL32.DLL,ShellExec_RunDLL " + file);
            } catch (IOException e) {

            }
        }
    }//GEN-LAST:event_tblRecibosMouseClicked

    private void lbNit4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lbNit4KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_lbNit4KeyReleased

    private void popBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popBorrarActionPerformed
        if (tblRecibos.getSelectedRow() > -1) {
            int fila = tblRecibos.getSelectedRow();
            DefaultTableModel modelo = (DefaultTableModel) tblRecibos.getModel();
            modelo.removeRow(fila);
            tblRecibos.removeEditor();
        } else {
            metodos.msgAdvertencia(null, "Seleccione un archivo");
        }
    }//GEN-LAST:event_popBorrarActionPerformed

    private void txtNumero1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNumero1MouseClicked
        if (lugarDesde.equals("movEgreso")) {
            instancias.getEgresos().setCancelarEgreso(true);
        } else if (lugarDesde.equals("movCompra")) {
            instancias.getEgresos().setCancelarEgreso(true);
            instancias.getEgresos().limpiar();

            instancias.getIngresos().setCancelarCompra(true);
        } else if (lugarDesde.equals("movPago")) {
            instancias.getEgresos().setCancelarEgreso(true);
            instancias.getEgresos().limpiar();

            instancias.getPagos().setCancelarPago(true);
        }

        this.dispose();
    }//GEN-LAST:event_txtNumero1MouseClicked

    public void realizarMovimientos() {
        saltarPasos = true;
        cmbBancos1.setSelectedItem("BANCO-TARJETA - 01");
        btnGuardarActionPerformed(null);
    }

    public void cerrar() {
        System.exit(0);
    }

    public void calcularTotal() {

        System.out.println("txtva: " + txtValor.getText());

        BigDecimal valor = big.getMoneda(txtValor.getText());
        BigDecimal actual = big.getMoneda(txtSaldoActual.getText());
        BigDecimal total;

        if (cmbBancos.getSelectedIndex() == 0) {
            total = actual.add(valor);
        } else {

            jLabel8.setVisible(true);
            if (actual.compareTo(valor) < 0) {
                if (cmbBancos1.getSelectedIndex() != 0) {
                    metodos.msgError(null, "El valor supera al monto disponible");
                    btnGuardar.setEnabled(false);
                }
            }

            total = actual.subtract(valor);
        }

        txtMovimiento.setText(big.setMoneda(valor));
        txtTotal.setText(big.setMoneda(total));
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
            java.util.logging.Logger.getLogger(dlgMovimientosBanco.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(dlgMovimientosBanco.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(dlgMovimientosBanco.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dlgMovimientosBanco.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                dlgMovimientosBanco dialog = new dlgMovimientosBanco(new javax.swing.JFrame(), true, "", "", BigDecimal.ZERO, "", "", BigDecimal.ZERO, "", "");
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {

                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox cmbBancos;
    private javax.swing.JComboBox cmbBancos1;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lbNit1;
    private javax.swing.JLabel lbNit3;
    private javax.swing.JLabel lbNit4;
    private javax.swing.JLabel lbNit9;
    private javax.swing.JPanel pnlInvisible;
    private javax.swing.JMenuItem popBorrar;
    private javax.swing.JTable tblRecibos;
    private javax.swing.JLabel txtCuenta;
    private javax.swing.JLabel txtMovimiento;
    private javax.swing.JTextField txtNombreAnexoConsulta;
    private javax.swing.JTextArea txtNota;
    private javax.swing.JLabel txtNumero;
    private javax.swing.JLabel txtNumero1;
    private javax.swing.JLabel txtSaldoActual;
    private javax.swing.JLabel txtTotal;
    private javax.swing.JTextField txtValor;
    // End of variables declaration//GEN-END:variables
}
