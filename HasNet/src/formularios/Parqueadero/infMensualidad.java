package formularios.Parqueadero;

import clases.Instancias;
import clases.big;
import clases.metodosGenerales;
import Modelo.Terceros.ModeloContacto;
import formularios.productos.buscProductos;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Calendar;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class infMensualidad extends javax.swing.JInternalFrame {

    metodosGenerales metodos = new metodosGenerales();
    private Instancias instancias;
    private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension dimBarra = null;
    DefaultTableModel modelo;
    Object[][] datos1;
    String simbolo = "";
    TableRowSorter modeloOrdenado;

    public infMensualidad() {
        initComponents();
        this.instancias = Instancias.getInstancias();

        moduloDias();
        cargarTabla();

        simbolo = instancias.getSimbolo();

        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
        dimBarra = Barra.getPreferredSize();
        Barra.setSize(0, 0);
        Barra.setPreferredSize(new Dimension(0, 0));

        setBorder(null);
        repaint();
        dtInicio.setFormat(2);
        dtFinal.setFormat(2);

        pnlInvisible.setVisible(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        buttonGroup1 = new javax.swing.ButtonGroup();
        scrFormulario = new javax.swing.JScrollPane();
        pnlFormulario = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        lbNit11 = new javax.swing.JLabel();
        txtPlaca = new javax.swing.JTextField();
        btnBuscTerceros = new javax.swing.JButton();
        txtNombre = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        lbCupo2 = new javax.swing.JLabel();
        txtServicio = new javax.swing.JTextField();
        dtInicio = new datechooser.beans.DateChooserCombo();
        lbRazon3 = new javax.swing.JLabel();
        btnBusProd = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        txtTotalDias = new javax.swing.JTextField();
        lbCupo1 = new javax.swing.JLabel();
        dtFinal = new datechooser.beans.DateChooserCombo();
        lbRazon4 = new javax.swing.JLabel();
        lbRazon1 = new javax.swing.JLabel();
        txtDias = new javax.swing.JTextField();
        txtValorMes = new javax.swing.JTextField();
        lbCupo3 = new javax.swing.JLabel();
        lbRazon5 = new javax.swing.JLabel();
        txtMeses = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        txtDiasPlazo = new javax.swing.JTextField();
        lbServicio8 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        lbServicio7 = new javax.swing.JLabel();
        txtValor = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        btnLimpiar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnBuscTerceros1 = new javax.swing.JButton();
        pnlInvisible = new javax.swing.JPanel();
        txtCliente = new javax.swing.JTextField();
        txtCod = new javax.swing.JTextField();

        jTextField1.setText("jTextField1");

        setTitle("Factura");

        pnlFormulario.setBackground(new java.awt.Color(255, 255, 255));

        jTable1.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "PLACA", "Cliente", "Fecha Inicio", "Fecha Final", "Restantes", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setRowHeight(24);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setMinWidth(0);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(0);
            jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
            jTable1.getColumnModel().getColumn(5).setMinWidth(70);
            jTable1.getColumnModel().getColumn(5).setPreferredWidth(70);
            jTable1.getColumnModel().getColumn(5).setMaxWidth(70);
            jTable1.getColumnModel().getColumn(6).setMinWidth(0);
            jTable1.getColumnModel().getColumn(6).setPreferredWidth(0);
            jTable1.getColumnModel().getColumn(6).setMaxWidth(0);
        }

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        lbNit11.setFont(new java.awt.Font("Century Gothic", 0, 36)); // NOI18N
        lbNit11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit11.setText("PLACA");

        txtPlaca.setBackground(new java.awt.Color(204, 204, 204));
        txtPlaca.setFont(new java.awt.Font("Century Gothic", 1, 70)); // NOI18N
        txtPlaca.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPlaca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPlacaKeyReleased(evt);
            }
        });

        btnBuscTerceros.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscTerceros.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnBuscTerceros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar1.png"))); // NOI18N
        btnBuscTerceros.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        btnBuscTerceros.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscTerceros.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnBuscTerceros.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnBuscTerceros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscTercerosActionPerformed(evt);
            }
        });

        txtNombre.setEditable(false);
        txtNombre.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtNombre.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNombre.setName("Cliente"); // NOI18N
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreKeyReleased(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detalle", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 0, 12))); // NOI18N

        lbCupo2.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbCupo2.setText("Servicio:");

        txtServicio.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtServicio.setName("Días a pagar"); // NOI18N
        txtServicio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtServicioKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtServicioKeyTyped(evt);
            }
        });

        dtInicio.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 12));

        lbRazon3.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbRazon3.setText("   Inicio:");

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

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        txtTotalDias.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        txtTotalDias.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTotalDias.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtTotalDias.setEnabled(false);
        txtTotalDias.setName("Valor"); // NOI18N
        txtTotalDias.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTotalDiasKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTotalDiasKeyTyped(evt);
            }
        });

        lbCupo1.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lbCupo1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbCupo1.setText("VIGENCIA TOTAL EN DÍAS:");

        dtFinal.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 12));

        lbRazon4.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbRazon4.setText("Fin:");

        lbRazon1.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbRazon1.setText("# días:");

        txtDias.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtDias.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDias.setName("Días a pagar"); // NOI18N
        txtDias.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDiasKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDiasKeyTyped(evt);
            }
        });

        txtValorMes.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtValorMes.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtValorMes.setText("0");
        txtValorMes.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtValorMes.setName("Valor"); // NOI18N
        txtValorMes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtValorMesActionPerformed(evt);
            }
        });
        txtValorMes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtValorMesKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtValorMesKeyTyped(evt);
            }
        });

        lbCupo3.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbCupo3.setText("Valor mes:");

        lbRazon5.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbRazon5.setText("# meses:");

        txtMeses.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtMeses.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMeses.setName("Días a pagar"); // NOI18N
        txtMeses.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMesesKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMesesKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lbRazon5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbCupo3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtMeses)
                            .addComponent(txtValorMes, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbRazon1, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                            .addComponent(lbRazon4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtDias, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                            .addComponent(dtFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(lbCupo1)
                        .addGap(18, 18, 18)
                        .addComponent(txtTotalDias, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbRazon5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMeses, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbRazon1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDias, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lbRazon4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtValorMes, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbCupo3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dtFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbCupo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTotalDias, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(62, 62, 62))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Días de plazo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 0, 12))); // NOI18N

        txtDiasPlazo.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        txtDiasPlazo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDiasPlazo.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtDiasPlazo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDiasPlazoKeyTyped(evt);
            }
        });

        lbServicio8.setBackground(new java.awt.Color(255, 255, 255));
        lbServicio8.setFont(new java.awt.Font("Century Gothic", 1, 10)); // NOI18N
        lbServicio8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbServicio8.setText("A credito");
        lbServicio8.setOpaque(true);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtDiasPlazo, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbServicio8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtDiasPlazo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbServicio8))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "A Pagar", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 0, 12))); // NOI18N

        lbServicio7.setBackground(new java.awt.Color(255, 255, 255));
        lbServicio7.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        lbServicio7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbServicio7.setText("TOTAL:");
        lbServicio7.setOpaque(true);

        txtValor.setFont(new java.awt.Font("Century Gothic", 0, 30)); // NOI18N
        txtValor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtValor.setText("0");
        txtValor.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtValor.setEnabled(false);
        txtValor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtValorKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbServicio7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtValor)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtValor)
                    .addComponent(lbServicio7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 12, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(lbRazon3, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(12, 12, 12)
                                        .addComponent(dtInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(lbCupo2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtServicio)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBusProd, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBusProd, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtServicio)
                        .addComponent(lbCupo2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbRazon3, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(dtInicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        btnLimpiar.setBackground(new java.awt.Color(204, 204, 204));
        btnLimpiar.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/limpiar.png"))); // NOI18N
        btnLimpiar.setText("LIMPIAR ");
        btnLimpiar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLimpiar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnLimpiar.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLimpiar)
                    .addComponent(btnGuardar))
                .addGap(5, 5, 5))
        );

        btnBuscTerceros1.setBackground(new java.awt.Color(204, 204, 204));
        btnBuscTerceros1.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnBuscTerceros1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/actualizar pequeño.png"))); // NOI18N
        btnBuscTerceros1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        btnBuscTerceros1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscTerceros1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnBuscTerceros1.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnBuscTerceros1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscTerceros1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbNit11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtNombre))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscTerceros, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(btnBuscTerceros1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(btnBuscTerceros1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lbNit11)
                .addGap(2, 2, 2)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscTerceros, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNombre)
                .addGap(15, 15, 15)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        txtCod.setName("Días a pagar"); // NOI18N
        txtCod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout pnlInvisibleLayout = new javax.swing.GroupLayout(pnlInvisible);
        pnlInvisible.setLayout(pnlInvisibleLayout);
        pnlInvisibleLayout.setHorizontalGroup(
            pnlInvisibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInvisibleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCod, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlInvisibleLayout.setVerticalGroup(
            pnlInvisibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInvisibleLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlInvisibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlFormularioLayout = new javax.swing.GroupLayout(pnlFormulario);
        pnlFormulario.setLayout(pnlFormularioLayout);
        pnlFormularioLayout.setHorizontalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addGap(3, 3, 3)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlFormularioLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnlInvisible, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );
        pnlFormularioLayout.setVerticalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(pnlFormularioLayout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlInvisible, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
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
            .addGroup(layout.createSequentialGroup()
                .addComponent(scrFormulario, javax.swing.GroupLayout.DEFAULT_SIZE, 661, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyReleased

    }//GEN-LAST:event_txtNombreKeyReleased

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            if (jTable1.getValueAt(i, 1).equals(txtPlaca.getText())) {
                metodos.msgAdvertencia(this, "No puede continuar, esta placa ya tiene una mensualidad vigente");
                return;
            }
        }

        Object[] campos = {txtPlaca, txtValor, txtNombre, txtMeses, txtDias};
        String faltantes = metodos.camposVacios(campos);

        if (!faltantes.equals("")) {
            metodos.msgAdvertencia(this, "No puede continuar, faltan los siguientes campos: " + faltantes);
            return;
        }

        String diasPlazo = "";

        diasPlazo = txtDiasPlazo.getText();

        String factura = "FACT-" + instancias.getSql().getNumConsecutivo("FACT")[0];
        String inicioMensualidad = dtInicio.getText();
        String finMensualidad = dtFinal.getText();
        if (inicioMensualidad.length() == 9) {
            inicioMensualidad = "0" + inicioMensualidad;
        }
        if (finMensualidad.length() == 9) {
            finMensualidad = "0" + finMensualidad;
        }

        Object[] info = new Object[]{txtPlaca.getText(), metodos.fechaConsulta(inicioMensualidad), txtDias.getText(), txtValor.getText(),
            metodos.fechaConsulta(finMensualidad), metodos.fechaConsulta(metodos.sumarFecha(finMensualidad, -5)),
            txtMeses.getText(), txtCod.getText(), txtServicio.getText(), factura};

        if (!instancias.getSql().agregarMensualidad(info)) {
            metodos.msgError(this, "Hubo un error al guardar el registro");
            return;
        }

        double cantMeses = (Double.parseDouble(txtTotalDias.getText()) / 30);
        cantMeses = Math.ceil(cantMeses);

        Object[][] productos = new Object[(int) cantMeses][7];

        int count = 30;
        int resultado = Integer.parseInt(txtTotalDias.getText());
        String despuesde = dtInicio.getText();
        Calendar fecha = dtInicio.getCurrent();

        for (int i = 0; i < cantMeses; i++) {

            resultado = resultado - 30;

            if (resultado >= 0) {
                count = count;
            } else {
                count = (resultado * -1) - 30;
                count = count * -1;
            }

            productos[i][0] = txtCod.getText();
            productos[i][1] = "1";
            productos[i][2] = 1;
            productos[i][3] = despuesde;
            productos[i][4] = metodos.sumarFecha(metodos.fecha(metodos.desdeDate(fecha)), count);
            despuesde = metodos.sumarFecha(metodos.fecha(metodos.desdeDate(fecha)), count);
            fecha = metodos.haciaDate2(metodos.sumarFecha(metodos.fecha(metodos.desdeDate(fecha)), count));

            productos[i][5] = count;
            BigDecimal total, subtotal;

            if (count < 30) {
                subtotal = big.getMoneda(String.valueOf(count)).multiply(big.getMoneda(txtValorMes.getText()));
                total = subtotal.divide(big.getMoneda("30"), 0, RoundingMode.HALF_UP);
                productos[i][6] = String.valueOf(total);
            } else {
                productos[i][6] = big.getMoneda(txtValorMes.getText());
            }
        }

        instancias.getFactura().desdeMensualidad(productos, txtCliente.getText(), diasPlazo, txtPlaca.getText());

        metodos.msgExito(this, "Mensualidad registrada con exito");
        btnLimpiarActionPerformed(evt);
        cargarTabla();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        if (evt.getClickCount() > 1) {
            int fila = jTable1.getSelectedRow();
            cargarTercero(jTable1.getValueAt(fila, 1).toString());
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void txtDiasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiasKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            calcularTotal();
            btnGuardar.requestFocus();
        }
    }//GEN-LAST:event_txtDiasKeyReleased

    private void txtDiasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiasKeyTyped
//        metodos.soloNum(evt);
    }//GEN-LAST:event_txtDiasKeyTyped

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        txtNombre.setText("");
        txtPlaca.setText("");
        txtServicio.setText("");
        txtMeses.setText("");
        txtDias.setText("");
        txtDiasPlazo.setText("");
        txtValor.setText("");
        txtTotalDias.setText("");
        txtValorMes.setText(this.simbolo + " 0");
        dtInicio.setSelectedDate(metodos.haciaDate2(metodosGenerales.fecha()));
        dtFinal.setSelectedDate(metodos.haciaDate2(metodosGenerales.fecha()));
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnBuscTercerosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscTercerosActionPerformed
        ventanaPlacas("");
    }//GEN-LAST:event_btnBuscTercerosActionPerformed

    private void txtMesesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMesesKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            calcularTotal();
            txtDias.requestFocus();
        }
    }//GEN-LAST:event_txtMesesKeyReleased

    private void txtMesesKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMesesKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMesesKeyTyped

    private void txtTotalDiasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalDiasKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalDiasKeyReleased

    private void txtTotalDiasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalDiasKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalDiasKeyTyped

    private void txtValorMesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorMesKeyReleased
        try {
            txtValorMes.setText(big.setMoneda(big.getMoneda(txtValorMes.getText())));
        } catch (NumberFormatException e) {
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtMeses.requestFocus();
        }
    }//GEN-LAST:event_txtValorMesKeyReleased

    private void txtValorMesKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorMesKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValorMesKeyTyped

    private void txtServicioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtServicioKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String codigo = txtServicio.getText().replace("'", "//");

            Object[][] maestra = instancias.getSql().getDatosConfig(codigo);

            if (maestra[0][0] != null) {
                txtCod.setText(maestra[0][0].toString());
                txtServicio.setText(maestra[0][1].toString());
                txtValorMes.setText(big.setMoneda(big.getMoneda(maestra[0][11].toString())));
                txtMeses.requestFocus();
            } else {
                metodos.msgError(this, "El servicio no existe");
                txtServicio.setText("");
                txtValorMes.setText(big.setMoneda(big.getBigDecimal(0)));
                txtCod.setText("");
            }
        }
    }//GEN-LAST:event_txtServicioKeyReleased

    private void txtServicioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtServicioKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtServicioKeyTyped

    private void txtCodKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodKeyReleased

    private void txtCodKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodKeyTyped

    private void txtPlacaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPlacaKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cargarTercero(txtPlaca.getText());
        } else {
            txtValor.setText("");
            txtNombre.setText("");
        }
    }//GEN-LAST:event_txtPlacaKeyReleased

    private void txtValorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorKeyReleased
        try {
            txtValor.setText(big.setMoneda(big.getMoneda(txtValor.getText())));
        } catch (NumberFormatException e) {
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnGuardar.requestFocus();
        }
    }//GEN-LAST:event_txtValorKeyReleased

    private void txtDiasPlazoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiasPlazoKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtDiasPlazoKeyTyped

    private void btnBusProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBusProdActionPerformed
        ventanaProductos("");
    }//GEN-LAST:event_btnBusProdActionPerformed

    private void txtValorMesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtValorMesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValorMesActionPerformed

    private void btnBuscTerceros1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscTerceros1ActionPerformed
        cargarTabla();
    }//GEN-LAST:event_btnBuscTerceros1ActionPerformed

    public void ventanaProductos(String codigo) {
        buscProductos buscar = new buscProductos(null, true, false, "parqueadero", "productos1");
        buscar.setLocationRelativeTo(null);
        instancias.setBusProductos(buscar);
        instancias.setCampoActual(txtServicio);
        buscar.noEncontrado(codigo.replace("'", "//"));
        buscar.show();
    }

    public void moduloDias() {
        System.out.println("entro a cargar los modulossss jmm");
        datos1 = instancias.getSql().getDatosMaestraParqueadero();

        try {
            if (datos1[0][8].equals("1")) {
                jPanel4.setVisible(true);
            } else {
                jPanel4.setVisible(false);
            }
        } catch (Exception e) {
            jPanel4.setVisible(false);
        }
    }

//    public void ventanaProductos(String codigo) {
//        buscProductos1 buscar = new buscProductos1(null, true, false, "mensualidad");
////        buscar.setOpc("factura");
////        buscar.setFactura(this);
//        buscar.setLocationRelativeTo(null);
//        instancias.setBuscProductos(buscar);
//        instancias.setCampoActual(txtServicio);
//        txtServicio.requestFocus();
////        buscar.setInstancia(instancias);
//        buscar.noEncontrado(codigo.replace("'", "//"));
//        buscar.show();
//
//    }
    public void ventanaPlacas(String nit) {
        buscPlaca1 buscar = new buscPlaca1(instancias.getMenu(), true, false);
        buscar.setLocationRelativeTo(null);
        instancias.setBuscPlacas1(buscar);
        instancias.setCampoActual(txtPlaca);
        txtPlaca.requestFocus();
        buscar.noEncontrado(nit);
        buscar.show();
    }

    public void setInstancias(Instancias instancias) {
        this.instancias = instancias;
        cargarTabla();
    }

    public void calcularTotal() {

        //OBTENER NUMERO DE DIAS
        Double meses, dias, total, meses1;

        if (txtMeses.getText().equals("")) {
            meses = 0.0;
            meses1 = 0.0;
        } else {
            meses = Double.parseDouble(txtMeses.getText()) * 30;
            meses1 = Double.parseDouble(txtMeses.getText());
        }

        if (txtDias.getText().equals("")) {
            dias = 0.0;
        } else {
            dias = Double.parseDouble(txtDias.getText());
        }

        total = meses + dias;
        String fecha = metodos.fecha(metodos.desdeDate(dtInicio.getCurrent()));
        dtFinal.setSelectedDate(metodos.haciaDate2(metodos.sumarFecha(fecha, (int) total.doubleValue())));
        txtTotalDias.setText(String.valueOf(total).substring(0, String.valueOf(total).length() - 2));

        Double totalMeses, totalDias, total1;

        totalDias = (dias * Double.parseDouble(txtValorMes.getText().substring(2, txtValorMes.getText().length()).replace(".", ""))) / 30;
        totalMeses = meses1 * Double.parseDouble(txtValorMes.getText().substring(2, txtValorMes.getText().length()).replace(".", ""));
        total1 = totalDias + totalMeses;

        DecimalFormat df = new DecimalFormat("#");
        df.format(total1);
        txtValor.setText(big.setMoneda(big.getBigDecimal(df.format(total1))));
    }

    public void cargarTabla() {
        jTable1.setModel(instancias.getSql().getMensualidades());

        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setMinWidth(0);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(0);
            jTable1.getColumnModel().getColumn(0).setMaxWidth(0);

            jTable1.getColumnModel().getColumn(1).setMinWidth(95);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(95);
            jTable1.getColumnModel().getColumn(1).setMaxWidth(95);

            jTable1.getColumnModel().getColumn(3).setMinWidth(105);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(105);
            jTable1.getColumnModel().getColumn(3).setMaxWidth(105);

            jTable1.getColumnModel().getColumn(4).setMinWidth(105);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(105);
            jTable1.getColumnModel().getColumn(4).setMaxWidth(105);

            jTable1.getColumnModel().getColumn(5).setMinWidth(40);
            jTable1.getColumnModel().getColumn(5).setPreferredWidth(40);
            jTable1.getColumnModel().getColumn(5).setMaxWidth(40);
            jTable1.getColumnModel().getColumn(6).setMinWidth(45);
            jTable1.getColumnModel().getColumn(6).setPreferredWidth(45);
            jTable1.getColumnModel().getColumn(6).setMaxWidth(45);
            jTable1.getColumnModel().getColumn(7).setMinWidth(60);
            jTable1.getColumnModel().getColumn(7).setPreferredWidth(60);
            jTable1.getColumnModel().getColumn(7).setMaxWidth(60);
            jTable1.getColumnModel().getColumn(8).setMinWidth(0);
            jTable1.getColumnModel().getColumn(8).setPreferredWidth(0);
            jTable1.getColumnModel().getColumn(8).setMaxWidth(0);
        }

//        modeloOrdenado.setRowFilter(RowFilter.regexFilter("(?i)" + txtNombre.getText(), 1));
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            jTable1.setValueAt(metodos.fecha(jTable1.getValueAt(i, 4).toString()), i, 4);
            jTable1.setValueAt(metodos.fecha(jTable1.getValueAt(i, 3).toString()), i, 3);
        }
    }

    public void cargarTercero(String Id) {
        Object[][] placas = instancias.getSql().getUsuarioPlaca(Id);

        if (placas.length > 0) {
            ModeloContacto nodo = instancias.getSql().getDatosTercero(placas[0][0].toString());

            if (nodo.getId() != null) {
                txtNombre.setText(nodo.getNombre());
                txtServicio.requestFocus();
                txtCliente.setText(nodo.getIdSistema());
                return;
            }
        } else {
            ventanaPlacas(Id);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBusProd;
    private javax.swing.JButton btnBuscTerceros;
    private javax.swing.JButton btnBuscTerceros1;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.ButtonGroup buttonGroup1;
    private datechooser.beans.DateChooserCombo dtFinal;
    private datechooser.beans.DateChooserCombo dtInicio;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lbCupo1;
    private javax.swing.JLabel lbCupo2;
    private javax.swing.JLabel lbCupo3;
    private javax.swing.JLabel lbNit11;
    private javax.swing.JLabel lbRazon1;
    private javax.swing.JLabel lbRazon3;
    private javax.swing.JLabel lbRazon4;
    private javax.swing.JLabel lbRazon5;
    private javax.swing.JLabel lbServicio7;
    private javax.swing.JLabel lbServicio8;
    private javax.swing.JPanel pnlFormulario;
    private javax.swing.JPanel pnlInvisible;
    private javax.swing.JScrollPane scrFormulario;
    private javax.swing.JTextField txtCliente;
    private javax.swing.JTextField txtCod;
    private javax.swing.JTextField txtDias;
    private javax.swing.JTextField txtDiasPlazo;
    private javax.swing.JTextField txtMeses;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPlaca;
    private javax.swing.JTextField txtServicio;
    private javax.swing.JTextField txtTotalDias;
    private javax.swing.JTextField txtValor;
    private javax.swing.JTextField txtValorMes;
    // End of variables declaration//GEN-END:variables
}
