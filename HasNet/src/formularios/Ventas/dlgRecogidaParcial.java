package formularios.Ventas;

import clases.Instancias;
import clases.big;
import clases.metodosGenerales;
import java.awt.event.KeyEvent;

public class dlgRecogidaParcial extends javax.swing.JDialog {
    
    private metodosGenerales metodos;
    private String opc, simbolo;
    Instancias instancias;
    
    public dlgRecogidaParcial(java.awt.Frame parent, boolean modal, String opc) {
        super(parent, modal);
        initComponents();
        
        instancias = Instancias.getInstancias();
        metodos = new metodosGenerales();
        this.opc = opc;
        this.simbolo = instancias.getSimbolo();
        txtTotal.setText(this.simbolo + " 0");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jButton25 = new javax.swing.JButton();
        jButton24 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        txt50 = new javax.swing.JTextField();
        txt20 = new javax.swing.JTextField();
        txt10 = new javax.swing.JTextField();
        jButton26 = new javax.swing.JButton();
        jButton35 = new javax.swing.JButton();
        jButton36 = new javax.swing.JButton();
        txt5 = new javax.swing.JTextField();
        txt2 = new javax.swing.JTextField();
        txt1 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        txtMil = new javax.swing.JTextField();
        jButton30 = new javax.swing.JButton();
        jButton32 = new javax.swing.JButton();
        txtQuinientos = new javax.swing.JTextField();
        jButton33 = new javax.swing.JButton();
        txtDocientos = new javax.swing.JTextField();
        jButton34 = new javax.swing.JButton();
        txtCien = new javax.swing.JTextField();
        jButton37 = new javax.swing.JButton();
        txtCincuenta = new javax.swing.JTextField();
        lbTelefono3 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        lbTelefono4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jButton25.setBackground(new java.awt.Color(255, 255, 255));
        jButton25.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jButton25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/billete50.png"))); // NOI18N
        jButton25.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jButton25ItemStateChanged(evt);
            }
        });
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });

        jButton24.setBackground(new java.awt.Color(255, 255, 255));
        jButton24.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jButton24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/billete20.jpg"))); // NOI18N
        jButton24.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton24MouseClicked(evt);
            }
        });
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });

        jButton23.setBackground(new java.awt.Color(255, 255, 255));
        jButton23.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jButton23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/billete10.jpg"))); // NOI18N
        jButton23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton23MouseClicked(evt);
            }
        });
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });

        txt50.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        txt50.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt50.setText("0");
        txt50.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt50KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt50KeyTyped(evt);
            }
        });

        txt20.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        txt20.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt20.setText("0");
        txt20.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt20KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt20KeyTyped(evt);
            }
        });

        txt10.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        txt10.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt10.setText("0");
        txt10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt10KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt10KeyTyped(evt);
            }
        });

        jButton26.setBackground(new java.awt.Color(255, 255, 255));
        jButton26.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jButton26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/billete5.jpg"))); // NOI18N
        jButton26.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jButton26ItemStateChanged(evt);
            }
        });
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });

        jButton35.setBackground(new java.awt.Color(255, 255, 255));
        jButton35.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jButton35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/billete2.jpg"))); // NOI18N
        jButton35.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton35MouseClicked(evt);
            }
        });
        jButton35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton35ActionPerformed(evt);
            }
        });

        jButton36.setBackground(new java.awt.Color(255, 255, 255));
        jButton36.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jButton36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/billete1.jpg"))); // NOI18N
        jButton36.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton36MouseClicked(evt);
            }
        });
        jButton36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton36ActionPerformed(evt);
            }
        });

        txt5.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        txt5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt5.setText("0");
        txt5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt5KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt5KeyTyped(evt);
            }
        });

        txt2.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        txt2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt2.setText("0");
        txt2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt2KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt2KeyTyped(evt);
            }
        });

        txt1.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        txt1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt1.setText("0");
        txt1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt1KeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jButton24, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton25, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt50, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                    .addComponent(txt20)
                    .addComponent(txt10))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton36, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton26, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton35, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt5, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                    .addComponent(txt2)
                    .addComponent(txt1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jButton25, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txt50))
                            .addComponent(jButton26, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt5))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton24, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt20)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(txt2))))
                    .addComponent(jButton35, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txt10))
                            .addComponent(jButton36, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(txt1))
                .addGap(7, 7, 7))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        txtMil.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        txtMil.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMil.setText("0");
        txtMil.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMilKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMilKeyTyped(evt);
            }
        });

        jButton30.setBackground(new java.awt.Color(255, 255, 255));
        jButton30.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jButton30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/moneda1000.jpg"))); // NOI18N
        jButton30.setBorder(null);
        jButton30.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton30MouseClicked(evt);
            }
        });
        jButton30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton30ActionPerformed(evt);
            }
        });

        jButton32.setBackground(new java.awt.Color(255, 255, 255));
        jButton32.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jButton32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/moneda500.jpeg"))); // NOI18N
        jButton32.setBorder(null);
        jButton32.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton32MouseClicked(evt);
            }
        });
        jButton32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton32ActionPerformed(evt);
            }
        });

        txtQuinientos.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        txtQuinientos.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtQuinientos.setText("0");
        txtQuinientos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtQuinientosKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtQuinientosKeyTyped(evt);
            }
        });

        jButton33.setBackground(new java.awt.Color(255, 255, 255));
        jButton33.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jButton33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/moneda200.jpg"))); // NOI18N
        jButton33.setBorder(null);
        jButton33.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton33MouseClicked(evt);
            }
        });
        jButton33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton33ActionPerformed(evt);
            }
        });

        txtDocientos.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        txtDocientos.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDocientos.setText("0");
        txtDocientos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDocientosKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDocientosKeyTyped(evt);
            }
        });

        jButton34.setBackground(new java.awt.Color(255, 255, 255));
        jButton34.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jButton34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/moneda100.jpg"))); // NOI18N
        jButton34.setBorder(null);
        jButton34.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton34MouseClicked(evt);
            }
        });
        jButton34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton34ActionPerformed(evt);
            }
        });

        txtCien.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        txtCien.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCien.setText("0");
        txtCien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCienKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCienKeyTyped(evt);
            }
        });

        jButton37.setBackground(new java.awt.Color(255, 255, 255));
        jButton37.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jButton37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/moneda50.jpg"))); // NOI18N
        jButton37.setBorder(null);
        jButton37.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton37MouseClicked(evt);
            }
        });
        jButton37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton37ActionPerformed(evt);
            }
        });

        txtCincuenta.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        txtCincuenta.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCincuenta.setText("0");
        txtCincuenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCincuentaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCincuentaKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(txtMil, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton30))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(txtQuinientos, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton32))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(txtDocientos, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton33)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(txtCien, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton34))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(txtCincuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton37))))
                .addGap(5, 5, 5))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jButton33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDocientos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jButton30, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMil))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jButton32, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtQuinientos)))
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton37, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jButton34, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtCincuenta)
                    .addComponent(txtCien))
                .addGap(12, 12, 12))
        );

        lbTelefono3.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        lbTelefono3.setText("TOTAL:");

        txtTotal.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        txtTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTotal.setText("0");
        txtTotal.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtTotal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTotalKeyReleased(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(46, 204, 113));
        jButton1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jButton1.setText("ACEPTAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        lbTelefono4.setFont(new java.awt.Font("Century Gothic", 1, 30)); // NOI18N
        lbTelefono4.setText("X");
        lbTelefono4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbTelefono4MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lbTelefono3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(216, 216, 216)
                .addComponent(lbTelefono4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbTelefono4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbTelefono3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        getAccessibleContext().setAccessibleName("Recogida Parcial");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (!txtTotal.getText().equals(this.simbolo + " 0")) {
            switch (opc) {
                case "caja":
                    instancias.getSql().agregarRecogidaParcial(new Object[]{instancias.getTerminal(), instancias.getUsuario(),
                        metodos.fechaConsulta(metodosGenerales.fecha()), true},
                            new Object[]{big.getMoneda(txtTotal.getText())});
                    metodos.msgExito(null, "Recogida Parcial registrada con exito");
                    instancias.getCaja().desdeRecogida(txtTotal.getText());
                    break;
                case "cajaBase":
                    instancias.getSql().agregarBaseCuadreDeCaja(new Object[]{instancias.getTerminal(), instancias.getUsuario(),
                        metodos.fechaConsulta(metodosGenerales.fecha()), true, txt50.getText(), txt20.getText(), txt10.getText(), txt5.getText(), txt2.getText(),
                        txt1.getText(), txtMil.getText(), txtQuinientos.getText(), txtDocientos.getText(), txtCien.getText(), txtCincuenta.getText()}, new Object[]{big.getMoneda(txtTotal.getText())});
                    metodos.msgExito(null, "Base registrada con exito");
                    instancias.getCaja().actualizar();
                    break;
            }
            this.dispose();
        } else {
            metodos.msgError(null, "Debe ingresar un valor");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtTotalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalKeyReleased
        if (txtTotal.getText().equals("") || txtTotal.getText().equals(this.simbolo) || txtTotal.getText().equals(this.simbolo + " ")) {
            txtTotal.setText("0");
        }
        
        txtTotal.setText(big.setMoneda(big.getMoneda(txtTotal.getText())));
        txt50.setText("0");
        txt20.setText("0");
        txt10.setText("0");
        txt5.setText("0");
        txt2.setText("0");
        txt1.setText("0");
        txtMil.setText("0");
        txtQuinientos.setText("0");
        txtDocientos.setText("0");
        txtCien.setText("0");
        txtCincuenta.setText("0");
    }//GEN-LAST:event_txtTotalKeyReleased

    private void jButton25ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jButton25ItemStateChanged

    }//GEN-LAST:event_jButton25ItemStateChanged

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
        int cant = 0;
        try {
            cant = Integer.parseInt(txt50.getText());
        } catch (Exception e) {
        }
        
        cant = cant + 1;
        txt50.setText(String.valueOf(cant));
        totalBase();
    }//GEN-LAST:event_jButton25ActionPerformed

    private void jButton24MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton24MouseClicked

    }//GEN-LAST:event_jButton24MouseClicked

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
        int cant = 0;
        try {
            cant = Integer.parseInt(txt20.getText());
        } catch (Exception e) {
        }
        
        cant = cant + 1;
        txt20.setText(String.valueOf(cant));
        totalBase();
    }//GEN-LAST:event_jButton24ActionPerformed

    private void jButton23MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton23MouseClicked

    }//GEN-LAST:event_jButton23MouseClicked

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        int cant = 0;
        try {
            cant = Integer.parseInt(txt10.getText());
        } catch (Exception e) {
        }
        
        cant = cant + 1;
        txt10.setText(String.valueOf(cant));
        totalBase();
    }//GEN-LAST:event_jButton23ActionPerformed

    private void jButton30MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton30MouseClicked

    }//GEN-LAST:event_jButton30MouseClicked

    private void jButton30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton30ActionPerformed
        int cant = 0;
        try {
            cant = Integer.parseInt(txtMil.getText());
        } catch (Exception e) {
        }
        
        cant = cant + 1;
        txtMil.setText(String.valueOf(cant));
        totalBase();
    }//GEN-LAST:event_jButton30ActionPerformed

    private void jButton26ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jButton26ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton26ItemStateChanged

    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed
        int cant = 0;
        try {
            cant = Integer.parseInt(txt5.getText());
        } catch (Exception e) {
        }
        
        cant = cant + 1;
        txt5.setText(String.valueOf(cant));
        totalBase();
    }//GEN-LAST:event_jButton26ActionPerformed

    private void jButton35MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton35MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton35MouseClicked

    private void jButton35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton35ActionPerformed
        int cant = 0;
        try {
            cant = Integer.parseInt(txt2.getText());
        } catch (Exception e) {
        }
        
        cant = cant + 1;
        txt2.setText(String.valueOf(cant));
        totalBase();
    }//GEN-LAST:event_jButton35ActionPerformed

    private void jButton36MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton36MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton36MouseClicked

    private void jButton36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton36ActionPerformed
        int cant = 0;
        try {
            cant = Integer.parseInt(txt1.getText());
        } catch (Exception e) {
        }
        
        cant = cant + 1;
        txt1.setText(String.valueOf(cant));
        totalBase();
    }//GEN-LAST:event_jButton36ActionPerformed

    private void jButton32MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton32MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton32MouseClicked

    private void jButton32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton32ActionPerformed
        int cant = 0;
        try {
            cant = Integer.parseInt(txtQuinientos.getText());
        } catch (Exception e) {
        }
        
        cant = cant + 1;
        txtQuinientos.setText(String.valueOf(cant));
        totalBase();
    }//GEN-LAST:event_jButton32ActionPerformed

    private void jButton33MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton33MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton33MouseClicked

    private void jButton33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton33ActionPerformed
        int cant = 0;
        try {
            cant = Integer.parseInt(txtDocientos.getText());
        } catch (Exception e) {
        }
        
        cant = cant + 1;
        txtDocientos.setText(String.valueOf(cant));
        totalBase();
    }//GEN-LAST:event_jButton33ActionPerformed

    private void jButton34MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton34MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton34MouseClicked

    private void jButton34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton34ActionPerformed
        int cant = 0;
        try {
            cant = Integer.parseInt(txtCien.getText());
        } catch (Exception e) {
        }
        
        cant = cant + 1;
        txtCien.setText(String.valueOf(cant));
        totalBase();
    }//GEN-LAST:event_jButton34ActionPerformed

    private void jButton37MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton37MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton37MouseClicked

    private void jButton37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton37ActionPerformed
        int cant = 0;
        try {
            cant = Integer.parseInt(txtCincuenta.getText());
        } catch (Exception e) {
        }
        
        cant = cant + 1;
        txtCincuenta.setText(String.valueOf(cant));
        totalBase();
    }//GEN-LAST:event_jButton37ActionPerformed

    private void txt50KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt50KeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txt50KeyTyped

    private void txt20KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt20KeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txt20KeyTyped

    private void txt10KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt10KeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txt10KeyTyped

    private void txt1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt1KeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txt1KeyTyped

    private void txt5KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt5KeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txt5KeyTyped

    private void txt2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt2KeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txt2KeyTyped

    private void txtMilKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMilKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtMilKeyTyped

    private void txtQuinientosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQuinientosKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtQuinientosKeyTyped

    private void txtDocientosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDocientosKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtDocientosKeyTyped

    private void txtCienKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCienKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtCienKeyTyped

    private void txtCincuentaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCincuentaKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtCincuentaKeyTyped

    private void lbTelefono4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbTelefono4MouseClicked
        this.dispose();
    }//GEN-LAST:event_lbTelefono4MouseClicked

    private void txt50KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt50KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            int cant = 0;
            try {
                cant = Integer.parseInt(txt50.getText());
            } catch (Exception e) {
            }
            txt50.setText(String.valueOf(cant));
            totalBase();
        }
    }//GEN-LAST:event_txt50KeyReleased

    private void txt20KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt20KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            int cant = 0;
            try {
                cant = Integer.parseInt(txt20.getText());
            } catch (Exception e) {
            }
            txt20.setText(String.valueOf(cant));
            totalBase();
        }
    }//GEN-LAST:event_txt20KeyReleased

    private void txt10KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt10KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            int cant = 0;
            try {
                cant = Integer.parseInt(txt10.getText());
            } catch (Exception e) {
            }
            txt10.setText(String.valueOf(cant));
            totalBase();
        }
    }//GEN-LAST:event_txt10KeyReleased

    private void txt5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt5KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            int cant = 0;
            try {
                cant = Integer.parseInt(txt5.getText());
            } catch (Exception e) {
            }
            txt5.setText(String.valueOf(cant));
            totalBase();
        }
    }//GEN-LAST:event_txt5KeyReleased

    private void txt2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt2KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            int cant = 0;
            try {
                cant = Integer.parseInt(txt2.getText());
            } catch (Exception e) {
            }
            txt2.setText(String.valueOf(cant));
            totalBase();
        }
    }//GEN-LAST:event_txt2KeyReleased

    private void txt1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt1KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            int cant = 0;
            try {
                cant = Integer.parseInt(txt1.getText());
            } catch (Exception e) {
            }
            txt1.setText(String.valueOf(cant));
            totalBase();
        }
    }//GEN-LAST:event_txt1KeyReleased

    private void txtMilKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMilKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            int cant = 0;
            try {
                cant = Integer.parseInt(txtMil.getText());
            } catch (Exception e) {
            }
            txtMil.setText(String.valueOf(cant));
            totalBase();
        }
    }//GEN-LAST:event_txtMilKeyReleased

    private void txtQuinientosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQuinientosKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            int cant = 0;
            try {
                cant = Integer.parseInt(txtQuinientos.getText());
            } catch (Exception e) {
            }
            txtQuinientos.setText(String.valueOf(cant));
            totalBase();
        }
    }//GEN-LAST:event_txtQuinientosKeyReleased

    private void txtDocientosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDocientosKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            int cant = 0;
            try {
                cant = Integer.parseInt(txtDocientos.getText());
            } catch (Exception e) {
            }
            txtDocientos.setText(String.valueOf(cant));
            totalBase();
        }
    }//GEN-LAST:event_txtDocientosKeyReleased

    private void txtCienKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCienKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            int cant = 0;
            try {
                cant = Integer.parseInt(txtCien.getText());
            } catch (Exception e) {
            }
            txtCien.setText(String.valueOf(cant));
            totalBase();
        }
    }//GEN-LAST:event_txtCienKeyReleased

    private void txtCincuentaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCincuentaKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            int cant = 0;
            try {
                cant = Integer.parseInt(txtCincuenta.getText());
            } catch (Exception e) {
            }
            txtCincuenta.setText(String.valueOf(cant));
            totalBase();
        }
    }//GEN-LAST:event_txtCincuentaKeyReleased
    
    public void totalBase() {
        
        int cant50 = 0, cant20 = 0, cant10 = 0, cant5 = 0, cant2 = 0, cant1 = 0, cantMil = 0, cantQuinientos = 0, cantDocientos = 0,
                cantCien = 0, cantCincuenta = 0;
        
        try {
            cant50 = Integer.parseInt(txt50.getText());
        } catch (Exception e) {
        }
        
        try {
            cant20 = Integer.parseInt(txt20.getText());
        } catch (Exception e) {
        }
        
        try {
            cant10 = Integer.parseInt(txt10.getText());
        } catch (Exception e) {
        }
        
        try {
            cant5 = Integer.parseInt(txt5.getText());
        } catch (Exception e) {
        }
        
        try {
            cant2 = Integer.parseInt(txt2.getText());
        } catch (Exception e) {
        }
        
        try {
            cant1 = Integer.parseInt(txt1.getText());
        } catch (Exception e) {
        }
        
        try {
            cantMil = Integer.parseInt(txtMil.getText());
        } catch (Exception e) {
        }
        
        try {
            cantQuinientos = Integer.parseInt(txtQuinientos.getText());
        } catch (Exception e) {
        }
        
        try {
            cantDocientos = Integer.parseInt(txtDocientos.getText());
        } catch (Exception e) {
        }
        
        try {
            cantCien = Integer.parseInt(txtCien.getText());
        } catch (Exception e) {
        }
        
        try {
            cantCincuenta = Integer.parseInt(txtCincuenta.getText());
        } catch (Exception e) {
        }
        
        int total = 0;
        total = (cant50 * 50000) + total;
        total = (cant20 * 20000) + total;
        total = (cant10 * 10000) + total;
        total = (cant5 * 5000) + total;
        total = (cant2 * 2000) + total;
        total = (cant1 * 1000) + total;
        total = (cantMil * 1000) + total;
        total = (cantQuinientos * 500) + total;
        total = (cantDocientos * 200) + total;
        total = (cantCien * 100) + total;
        total = (cantCincuenta * 50) + total;
        txtTotal.setText(big.setMoneda(big.getBigDecimal(String.valueOf(total))));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton32;
    private javax.swing.JButton jButton33;
    private javax.swing.JButton jButton34;
    private javax.swing.JButton jButton35;
    private javax.swing.JButton jButton36;
    private javax.swing.JButton jButton37;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel lbTelefono3;
    private javax.swing.JLabel lbTelefono4;
    private javax.swing.JTextField txt1;
    private javax.swing.JTextField txt10;
    private javax.swing.JTextField txt2;
    private javax.swing.JTextField txt20;
    private javax.swing.JTextField txt5;
    private javax.swing.JTextField txt50;
    private javax.swing.JTextField txtCien;
    private javax.swing.JTextField txtCincuenta;
    private javax.swing.JTextField txtDocientos;
    private javax.swing.JTextField txtMil;
    private javax.swing.JTextField txtQuinientos;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
