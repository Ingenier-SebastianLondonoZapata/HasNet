package formularios.Veterinario;

import clases.Instancias;
import Utilidades.BaseDatos.SQL;
import clases.big;
import clases.metodosGenerales;
import clases.productos.ndProducto;
import formularios.productos.buscProductos;
import java.applet.AudioClip;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;

public class dlgMedicamentosPendientes extends javax.swing.JDialog {

    private metodosGenerales metodos;
    private Instancias instancias;
    DefaultTableModel modelo;

    public dlgMedicamentosPendientes(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        modelo = (DefaultTableModel) tblPendientes.getModel();
        instancias = Instancias.getInstancias();
        metodos = new metodosGenerales();

        txtId.setVisible(false);
        txtIdHosp.setVisible(false);
        txtCod.setVisible(false);

        String hora = metodosGenerales.hora().split(":")[0];
        String minuto = metodosGenerales.hora().split(":")[1];

        int numeroHora = Integer.valueOf(hora);
        int numeroMinuto = Integer.valueOf(minuto);

        Object[][] medicamentosPendientes = null;

        try {
            medicamentosPendientes = instancias.getSql().getMedicamentosPorAplicarVeterinario("");
        } catch (Exception e) {
        }

        if (medicamentosPendientes != null) {
            for (int i = 0; i < medicamentosPendientes.length; i++) {
                if (medicamentosPendientes[i][5] != null) {
                    if (medicamentosPendientes[i][5].equals("Pendiente")) {

                        int horaEstablecida = Integer.parseInt(medicamentosPendientes[i][4].toString().split(":")[0]);
                        int minutoEstablecida = Integer.parseInt(medicamentosPendientes[i][4].toString().split(":")[1]);

                        if (numeroHora > horaEstablecida) {
                            modelo.addRow(new Object[]{medicamentosPendientes[i][0], medicamentosPendientes[i][6],
                                medicamentosPendientes[i][7], medicamentosPendientes[i][1], medicamentosPendientes[i][2],
                                medicamentosPendientes[i][3], medicamentosPendientes[i][4]});
                        } else if (numeroHora == horaEstablecida && numeroMinuto >= minutoEstablecida) {
                            modelo.addRow(new Object[]{medicamentosPendientes[i][0], medicamentosPendientes[i][6],
                                medicamentosPendientes[i][7], medicamentosPendientes[i][1], medicamentosPendientes[i][2],
                                medicamentosPendientes[i][3], medicamentosPendientes[i][4]});
                        }
                    }
                }
            }
        }

        this.getRootPane().registerKeyboardAction(accion("cerrar", this), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel5 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblPendientes = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        lbTitulo = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtNota = new javax.swing.JTextArea();
        btnGuardar = new javax.swing.JButton();
        lbNit2 = new javax.swing.JLabel();
        txtMedicamento = new javax.swing.JTextField();
        lbProducto = new javax.swing.JLabel();
        btnBusProd = new javax.swing.JButton();
        lbProducto1 = new javax.swing.JLabel();
        txtCant = new javax.swing.JTextField();
        txtId = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtPosologia = new javax.swing.JTextArea();
        txtHora = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        txtIdHosp = new javax.swing.JTextField();
        txtCod = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        rdSi = new javax.swing.JRadioButton();
        rdNo = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("CONFIGURACIONES");
        setUndecorated(true);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel7.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("X");
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        tblPendientes.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        tblPendientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "idHospitalización", "Mascota", "Dueño", "Codigo", "Medicamento", "Posologia", "Hora"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPendientes.setInheritsPopupMenu(true);
        tblPendientes.setRowHeight(24);
        tblPendientes.getTableHeader().setReorderingAllowed(false);
        tblPendientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPendientesMouseClicked(evt);
            }
        });
        tblPendientes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblPendientesKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tblPendientes);
        if (tblPendientes.getColumnModel().getColumnCount() > 0) {
            tblPendientes.getColumnModel().getColumn(0).setMinWidth(0);
            tblPendientes.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblPendientes.getColumnModel().getColumn(0).setMaxWidth(0);
            tblPendientes.getColumnModel().getColumn(1).setMinWidth(70);
            tblPendientes.getColumnModel().getColumn(1).setPreferredWidth(125);
            tblPendientes.getColumnModel().getColumn(1).setMaxWidth(170);
            tblPendientes.getColumnModel().getColumn(2).setMinWidth(100);
            tblPendientes.getColumnModel().getColumn(2).setPreferredWidth(200);
            tblPendientes.getColumnModel().getColumn(2).setMaxWidth(250);
            tblPendientes.getColumnModel().getColumn(3).setMinWidth(0);
            tblPendientes.getColumnModel().getColumn(3).setPreferredWidth(0);
            tblPendientes.getColumnModel().getColumn(3).setMaxWidth(0);
            tblPendientes.getColumnModel().getColumn(4).setMinWidth(150);
            tblPendientes.getColumnModel().getColumn(4).setPreferredWidth(250);
            tblPendientes.getColumnModel().getColumn(4).setMaxWidth(350);
            tblPendientes.getColumnModel().getColumn(6).setMinWidth(60);
            tblPendientes.getColumnModel().getColumn(6).setPreferredWidth(60);
            tblPendientes.getColumnModel().getColumn(6).setMaxWidth(60);
        }

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        lbTitulo.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        lbTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitulo.setText(" ");

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        txtNota.setColumns(20);
        txtNota.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        txtNota.setRows(3);
        txtNota.setText("NOTA:");
        jScrollPane3.setViewportView(txtNota);

        btnGuardar.setBackground(new java.awt.Color(46, 204, 113));
        btnGuardar.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
        btnGuardar.setText("GUARDAR ");
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar.setEnabled(false);
        btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnGuardar.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        lbNit2.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lbNit2.setText("Horas establecida:");

        txtMedicamento.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        txtMedicamento.setEnabled(false);
        txtMedicamento.setName("combo"); // NOI18N
        txtMedicamento.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtMedicamentoFocusGained(evt);
            }
        });
        txtMedicamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMedicamentoActionPerformed(evt);
            }
        });
        txtMedicamento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMedicamentoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMedicamentoKeyReleased(evt);
            }
        });

        lbProducto.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lbProducto.setText("Medicamento:");
        lbProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lbProductoKeyReleased(evt);
            }
        });

        btnBusProd.setBackground(new java.awt.Color(204, 204, 204));
        btnBusProd.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnBusProd.setForeground(new java.awt.Color(255, 255, 255));
        btnBusProd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar1.png"))); // NOI18N
        btnBusProd.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnBusProd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBusProd.setEnabled(false);
        btnBusProd.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnBusProd.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnBusProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBusProdActionPerformed(evt);
            }
        });

        lbProducto1.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lbProducto1.setText("Cant:");
        lbProducto1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lbProducto1KeyReleased(evt);
            }
        });

        txtCant.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        txtCant.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCant.setText("1");
        txtCant.setName("combo"); // NOI18N
        txtCant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantActionPerformed(evt);
            }
        });
        txtCant.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCantFocusGained(evt);
            }
        });
        txtCant.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCantKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCantKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantKeyTyped(evt);
            }
        });

        txtId.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        txtId.setName("combo"); // NOI18N
        txtId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdActionPerformed(evt);
            }
        });
        txtId.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtIdFocusGained(evt);
            }
        });
        txtId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtIdKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIdKeyReleased(evt);
            }
        });

        txtPosologia.setColumns(20);
        txtPosologia.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        txtPosologia.setLineWrap(true);
        txtPosologia.setRows(3);
        txtPosologia.setText("POSOLOGIA:");
        txtPosologia.setWrapStyleWord(true);
        txtPosologia.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtPosologia.setEnabled(false);
        jScrollPane4.setViewportView(txtPosologia);

        txtHora.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        txtHora.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtHora.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtHora.setEnabled(false);
        txtHora.setName("combo"); // NOI18N
        txtHora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHoraActionPerformed(evt);
            }
        });
        txtHora.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtHoraFocusGained(evt);
            }
        });
        txtHora.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtHoraKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtHoraKeyReleased(evt);
            }
        });

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        txtIdHosp.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        txtIdHosp.setName("combo"); // NOI18N
        txtIdHosp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdHospActionPerformed(evt);
            }
        });
        txtIdHosp.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtIdHospFocusGained(evt);
            }
        });
        txtIdHosp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtIdHospKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIdHospKeyReleased(evt);
            }
        });

        txtCod.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        txtCod.setName("combo"); // NOI18N
        txtCod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodActionPerformed(evt);
            }
        });
        txtCod.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCodFocusGained(evt);
            }
        });
        txtCod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodKeyReleased(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("¿ APLICAR NUEVAMENTE EL MEDICAMENTO EL DÍA DE MAÑANA ?");

        rdSi.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(rdSi);
        rdSi.setFont(new java.awt.Font("Century Gothic", 0, 36)); // NOI18N
        rdSi.setText("SI");
        rdSi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        rdNo.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(rdNo);
        rdNo.setFont(new java.awt.Font("Century Gothic", 0, 36)); // NOI18N
        rdNo.setText("NO");
        rdNo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 890, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(rdSi, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(rdNo, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rdSi, javax.swing.GroupLayout.PREFERRED_SIZE, 46, Short.MAX_VALUE)
                    .addComponent(rdNo, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addComponent(jScrollPane4)
                    .addComponent(jSeparator1)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(lbProducto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtMedicamento, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(btnBusProd, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24)
                                .addComponent(lbProducto1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCant, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(lbNit2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtHora, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtIdHosp, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCod, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnGuardar)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtHora, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtIdHosp, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCod, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbNit2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtMedicamento, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(lbProducto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBusProd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbProducto1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCant, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(2, 2, 2)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(btnGuardar)
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(lbTitulo)
                .addGap(2, 2, 2)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 30)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("PENDIENTES POR APLICAR");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel7)
                .addGap(2, 2, 2)
                .addComponent(jLabel1)
                .addGap(2, 2, 2)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void tblPendientesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblPendientesKeyReleased

    }//GEN-LAST:event_tblPendientesKeyReleased

    private void tblPendientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPendientesMouseClicked
        int fila = tblPendientes.getSelectedRow();
        txtPosologia.setText("");
        txtHora.setText("");
        txtId.setText("");
        txtMedicamento.setText("");
        txtNota.setText("NOTA: ");
        txtCant.setText("1");
        txtHora.setText(tblPendientes.getValueAt(fila, 6).toString());
        txtPosologia.setText("POSOLOGIA: " + tblPendientes.getValueAt(fila, 5).toString());
        lbTitulo.setText(tblPendientes.getValueAt(fila, 4).toString());
        txtIdHosp.setText(tblPendientes.getValueAt(fila, 0).toString());
        txtCod.setText(tblPendientes.getValueAt(fila, 3).toString());

        txtMedicamento.setEnabled(true);
        btnBusProd.setEnabled(true);
        btnGuardar.setEnabled(true);
    }//GEN-LAST:event_tblPendientesMouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        this.dispose();
    }//GEN-LAST:event_jLabel7MouseClicked

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (txtId.getText().equals("") || txtMedicamento.getText().equals("")) {
            metodos.msgAdvertencia(null, "No ha cargado ningún medicamento");
        }

        if (!rdSi.isSelected() && !rdNo.isSelected()) {
            metodos.msgError(null, "Falta opcion de aplicar medicamento");
            return;
        }

        if (metodos.msgPregunta(null, "¿Desea continuar?") == 0) {
            if (!instancias.getSql().agregarMedicamentosAplicados(txtIdHosp.getText(), txtCod.getText(), lbTitulo.getText(), txtCant.getText(),
                    txtId.getText(), instancias.getUsuario(), metodos.fechaConsulta(metodosGenerales.fecha()), txtHora.getText(), txtNota.getText(),
                    metodos.fechaConsulta(metodosGenerales.fecha()), metodosGenerales.hora())) {
                metodos.msgError(null, "Error al guardar el aplique del medicamento");
            } else {

                if (rdSi.isSelected()) {
                    instancias.getSql().cambiarEstadoDeMedicamento(txtIdHosp.getText(), txtCod.getText(), txtHora.getText());
                } else {
                    instancias.getSql().eliminar_registro("bdMedicamentosProcesosAlertas",
                            " Codigo = '" + txtCod.getText() + "' and hora = '" + txtHora.getText() + "' "
                            + " and idHospitalizacion = '" + txtIdHosp.getText() + "' ");
                }

                String producto = txtId.getText();
                if (!producto.equals("")) {
                    ndProducto nodo = instancias.getSql().getDatosProducto(producto, "bdProductos");
                    Object[] vector = {txtIdHosp.getText(), nodo.getIdSistema(), nodo.getDescripcion(), txtCant.getText(),
                        big.setMoneda(big.getMoneda(nodo.getL1().replace(".", ","))), "1", instancias.getUsuario(),
                        metodos.fechaConsulta(metodosGenerales.fecha()), metodosGenerales.hora()};
                    if (!instancias.getSql().agregarProductosProcesos(vector)) {
                        metodos.msgError(null, "Hubo un problema al guardar los productos de la peluqueria");
                        return;
                    }
                }

                metodos.msgExito(null, "Medicamento aplicado con exito !");

                String hora = metodosGenerales.hora().split(":")[0];
                String minuto = metodosGenerales.hora().split(":")[1];
                int numeroHora = Integer.valueOf(hora);
                int numeroMinuto = Integer.valueOf(minuto);
                Object[][] medicamentosPendientes = null;

                try {
                    medicamentosPendientes = instancias.getSql().getMedicamentosPorAplicarVeterinario("");
                } catch (Exception e) {
                }

                Boolean activarMensaje = false;

                if (medicamentosPendientes != null) {
                    for (int i = 0; i < medicamentosPendientes.length; i++) {
                        if (medicamentosPendientes[i][5] != null) {
                            if (medicamentosPendientes[i][5].equals("Pendiente")) {
                                int horaEstablecida = Integer.parseInt(medicamentosPendientes[i][4].toString().split(":")[0]);
                                int minutoEstablecida = Integer.parseInt(medicamentosPendientes[i][4].toString().split(":")[0]);
                                if (numeroHora >= horaEstablecida && numeroMinuto >= minutoEstablecida) {
                                    activarMensaje = true;
                                    break;
                                }
                            }
                        }
                    }

                    if (activarMensaje) {
                        instancias.getMenu().actualizarMedicamentos(true);
                    } else {
                        instancias.getMenu().actualizarMedicamentos(false);
                    }
                } else {
                    instancias.getMenu().actualizarMedicamentos(false);
                }

                dispose();
            }
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void txtMedicamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMedicamentoActionPerformed

    }//GEN-LAST:event_txtMedicamentoActionPerformed

    private void txtMedicamentoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMedicamentoFocusGained

    }//GEN-LAST:event_txtMedicamentoFocusGained

    private void txtMedicamentoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMedicamentoKeyPressed

    }//GEN-LAST:event_txtMedicamentoKeyPressed

    private void txtMedicamentoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMedicamentoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String codigo = txtMedicamento.getText().replace("'", "//");
            String cant = txtCant.getText();

            ndProducto nodo = null;

            String CodigoProd = "";
            if (codigo.equals("")) {
                CodigoProd = "";
            } else {
                Object[][] listado = instancias.getSql().getCodigosRelacionados(codigo, " where codigo");
                if (listado.length > 0) {
                    codigo = listado[0][0].toString();
                }

                nodo = instancias.getSql().getDatosProducto(codigo, "bdProductos");
                if (nodo.getIdSistema() != null) {
                    CodigoProd = nodo.getIdSistema();
                }
            }

            if (!CodigoProd.equals("")) {
                txtId.setText(nodo.getIdSistema());
                txtMedicamento.setText(nodo.getDescripcion());
            } else {
                ventanaProductos(codigo);
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            btnGuardar.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_MULTIPLY) {
            double cantidad = 1;
            try {
                cantidad = Double.parseDouble(txtMedicamento.getText().replace("*", ""));
            } catch (Exception e) {
            }
            txtCant.setText(String.valueOf(cantidad));
            if (txtCant.getText().substring(txtCant.getText().length() - 1, txtCant.getText().length()).equals("0")) {
                txtCant.setText(txtCant.getText().substring(0, txtCant.getText().length() - 2));
            }
            txtMedicamento.setText("");
        } else {
            txtId.setText("");
        }
    }//GEN-LAST:event_txtMedicamentoKeyReleased

    private void lbProductoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lbProductoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtMedicamento.requestFocus();
        }
    }//GEN-LAST:event_lbProductoKeyReleased

    private void btnBusProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBusProdActionPerformed
        ventanaProductos("");
    }//GEN-LAST:event_btnBusProdActionPerformed

    private void lbProducto1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lbProducto1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_lbProducto1KeyReleased

    private void txtCantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantActionPerformed

    private void txtCantFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCantFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantFocusGained

    private void txtCantKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantKeyPressed

    private void txtCantKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantKeyReleased

    private void txtCantKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtCantKeyTyped

    private void txtIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdActionPerformed

    private void txtIdFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtIdFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdFocusGained

    private void txtIdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdKeyPressed

    private void txtIdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdKeyReleased

    private void txtHoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHoraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHoraActionPerformed

    private void txtHoraFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtHoraFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHoraFocusGained

    private void txtHoraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHoraKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHoraKeyPressed

    private void txtHoraKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHoraKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHoraKeyReleased

    private void txtIdHospActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdHospActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdHospActionPerformed

    private void txtIdHospFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtIdHospFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdHospFocusGained

    private void txtIdHospKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdHospKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdHospKeyPressed

    private void txtIdHospKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdHospKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdHospKeyReleased

    private void txtCodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodActionPerformed

    private void txtCodFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCodFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodFocusGained

    private void txtCodKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodKeyPressed

    private void txtCodKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodKeyReleased

    public void ventanaProductos(String codigo) {
        buscProductos buscar = new buscProductos(null, true, false, "", "productos1");
        buscar.setLocationRelativeTo(null);
        instancias.setBusProductos(buscar);
        instancias.setCampoActual(txtMedicamento);
        txtMedicamento.requestFocus();
        buscar.noEncontrado(codigo.replace("'", "//"));
        buscar.show();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBusProd;
    private javax.swing.JButton btnGuardar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbNit2;
    private javax.swing.JLabel lbProducto;
    private javax.swing.JLabel lbProducto1;
    private javax.swing.JLabel lbTitulo;
    private javax.swing.JRadioButton rdNo;
    private javax.swing.JRadioButton rdSi;
    private javax.swing.JTable tblPendientes;
    private javax.swing.JTextField txtCant;
    private javax.swing.JTextField txtCod;
    private javax.swing.JTextField txtHora;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtIdHosp;
    private javax.swing.JTextField txtMedicamento;
    private javax.swing.JTextArea txtNota;
    private javax.swing.JTextArea txtPosologia;
    // End of variables declaration//GEN-END:variables
}
