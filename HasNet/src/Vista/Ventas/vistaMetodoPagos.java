package Vista.Ventas;

import clases.Instancias;
import clases.big;
import clases.metodosGenerales;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.KeyStroke;

public class VistaMetodoPagos extends javax.swing.JDialog {

    BigDecimal NC, valorTotal, valorNeto;
    String cliente, simbolo;
    Instancias instancias = Instancias.getInstancias();
    metodosGenerales metodos = new metodosGenerales();

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public BigDecimal getNC() {
        return NC;
    }

    public void setNC(BigDecimal NC) {
        this.NC = NC;
    }

    public VistaMetodoPagos(java.awt.Frame parent, boolean modal, BigDecimal valor, Instancias instancias, String cliente, BigDecimal valorNeto) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.valorTotal = valor;
        this.valorNeto = valorNeto;

        instancias = Instancias.getInstancias();
        this.simbolo = instancias.getSimbolo();

        txtValor.setText(this.simbolo + " 0");
        txtNC.setText(this.simbolo + " 0");
        txtCheque.setText(this.simbolo + " 0");
        txtTarjetaDebito.setText(this.simbolo + " 0");
        txtTarjetaCredito.setText(this.simbolo + " 0");
        txtTotalPropina.setText(this.simbolo + " 0");
        txtTotalGeneral.setText(this.simbolo + " 0");
        txtPropina.setText(this.simbolo + " 0");

        txtTotal.setText(big.setMoneda(valor));
        txtEfectivo.setText(big.setMoneda(valor));
        txtValor.requestFocus();
        this.cliente = cliente;

        BigDecimal nc = new BigDecimal("0");
        try {
            Object[][] nodo = instancias.getSql().getNcCliente(cliente);
            for (int i = 0; i < nodo.length; i++) {
                nc = nc.add(big.getBigDecimal((String) nodo[i][0]));
            }
        } catch (Exception e) {
        }

        this.NC = nc;

        if (instancias.getConfiguraciones().isRestaurante()) {
            pnlPropina.setVisible(true);
        } else {
            pnlPropina.setVisible(false);
        }

        instancias.getSql().cambiarEstFact("ON");
        Object[][] tarjetas = instancias.getSql().getTarjetas();

        cmbTarjetas.removeAllItems();
        cmbTarjetas.addItem("");
        for (int i = 0; i < tarjetas.length; i++) {
            cmbTarjetas.addItem(tarjetas[i][0]);
        }
        calcularEfectivo();

        this.getRootPane().registerKeyboardAction(accion("FINALIZAR", this), KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    public ActionListener accion(final String opc, final JDialog ventana) {
        ActionListener a = new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                switch (opc) {
                    case "FINALIZAR":
                        jButton2ActionPerformed(null);
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
        jPanel1 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        pnlNumerico = new javax.swing.JPanel();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        lbEtiqueta3 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jButton21 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        jButton24 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        txtEfectivo = new javax.swing.JTextField();
        lbEfectivo = new javax.swing.JLabel();
        lbEtiqueta = new javax.swing.JLabel();
        txtValor = new javax.swing.JTextField();
        lbNC = new javax.swing.JLabel();
        txtNC = new javax.swing.JTextField();
        txtCheque = new javax.swing.JTextField();
        cmbTarjetas = new javax.swing.JComboBox();
        lbCheque2 = new javax.swing.JLabel();
        txtTarjetaDebito = new javax.swing.JTextField();
        txtTarjetaCredito = new javax.swing.JTextField();
        lbCheque7 = new javax.swing.JLabel();
        lbCheque8 = new javax.swing.JLabel();
        jButton32 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        lbCheque1 = new javax.swing.JLabel();
        lbEtiqueta2 = new javax.swing.JLabel();
        txtTotalDinero = new javax.swing.JTextField();
        txtCambio = new javax.swing.JTextField();
        lbEtiqueta1 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        txtTotalPropina = new javax.swing.JTextField();
        lbCheque6 = new javax.swing.JLabel();
        lbCheque9 = new javax.swing.JLabel();
        txtTotalGeneral = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jButton1 = new javax.swing.JButton();
        pnlPropina = new javax.swing.JPanel();
        lbCheque4 = new javax.swing.JLabel();
        txtPorcPropina = new javax.swing.JTextField();
        lbCheque5 = new javax.swing.JLabel();
        txtPropina = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Forma de pago");
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));

        jButton2.setBackground(new java.awt.Color(0, 204, 102));
        jButton2.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jButton2.setText("GUARDAR ");
        jButton2.setBorder(null);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        pnlNumerico.setBackground(new java.awt.Color(255, 255, 255));

        jButton14.setBackground(new java.awt.Color(255, 255, 255));
        jButton14.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jButton14.setText("$ 2.000");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton15.setBackground(new java.awt.Color(255, 255, 255));
        jButton15.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jButton15.setText("$ 1.000");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jButton16.setBackground(new java.awt.Color(255, 255, 255));
        jButton16.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jButton16.setText("$ 5.000");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jButton17.setBackground(new java.awt.Color(255, 255, 255));
        jButton17.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jButton17.setText("$ 10.000");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jButton18.setBackground(new java.awt.Color(255, 255, 255));
        jButton18.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jButton18.setText("$ 50.000");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jButton19.setBackground(new java.awt.Color(255, 255, 255));
        jButton19.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jButton19.setText("$ 20.000");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        jButton20.setBackground(new java.awt.Color(255, 255, 255));
        jButton20.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jButton20.setText("$ 100.000");
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        lbEtiqueta3.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        lbEtiqueta3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbEtiqueta3.setText("INGRESE PAGO");

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator2.setFont(new java.awt.Font("Centaur", 0, 24)); // NOI18N

        jButton21.setBackground(new java.awt.Color(255, 255, 255));
        jButton21.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jButton21.setText("$ 50");
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });

        jButton22.setBackground(new java.awt.Color(255, 255, 255));
        jButton22.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jButton22.setText("$ 100");
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });

        jButton23.setBackground(new java.awt.Color(255, 255, 255));
        jButton23.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jButton23.setText("$ 200");
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });

        jButton24.setBackground(new java.awt.Color(255, 255, 255));
        jButton24.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jButton24.setText("$ 500");
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlNumericoLayout = new javax.swing.GroupLayout(pnlNumerico);
        pnlNumerico.setLayout(pnlNumericoLayout);
        pnlNumericoLayout.setHorizontalGroup(
            pnlNumericoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNumericoLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(pnlNumericoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlNumericoLayout.createSequentialGroup()
                        .addGroup(pnlNumericoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButton23, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton21, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlNumericoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbEtiqueta3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlNumericoLayout.createSequentialGroup()
                        .addGroup(pnlNumericoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButton19, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlNumericoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(5, 5, 5))
        );
        pnlNumericoLayout.setVerticalGroup(
            pnlNumericoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNumericoLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(lbEtiqueta3)
                .addGap(5, 5, 5)
                .addGroup(pnlNumericoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(2, 2, 2)
                .addGroup(pnlNumericoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(2, 2, 2)
                .addGroup(pnlNumericoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(2, 2, 2)
                .addGroup(pnlNumericoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addGroup(pnlNumericoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        txtEfectivo.setEditable(false);
        txtEfectivo.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        txtEfectivo.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtEfectivo.setText("0");
        txtEfectivo.setName("Efectivo"); // NOI18N
        txtEfectivo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEfectivoKeyReleased(evt);
            }
        });

        lbEfectivo.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbEfectivo.setText("Efectivo:");

        lbEtiqueta.setFont(new java.awt.Font("Century Gothic", 1, 30)); // NOI18N
        lbEtiqueta.setText("PAGA:");

        txtValor.setBackground(new java.awt.Color(255, 204, 204));
        txtValor.setFont(new java.awt.Font("Century Gothic", 0, 28)); // NOI18N
        txtValor.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtValor.setText("0");
        txtValor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtValorKeyReleased(evt);
            }
        });

        lbNC.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbNC.setText("N.C:");

        txtNC.setBackground(new java.awt.Color(255, 204, 204));
        txtNC.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        txtNC.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtNC.setText("0");
        txtNC.setName("N.C"); // NOI18N
        txtNC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNCKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNCKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNCKeyTyped(evt);
            }
        });

        txtCheque.setBackground(new java.awt.Color(255, 204, 204));
        txtCheque.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        txtCheque.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtCheque.setText("0");
        txtCheque.setName("Cheque"); // NOI18N
        txtCheque.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtChequeKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtChequeKeyTyped(evt);
            }
        });

        cmbTarjetas.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        lbCheque2.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbCheque2.setText("Cheque:");

        txtTarjetaDebito.setBackground(new java.awt.Color(255, 204, 204));
        txtTarjetaDebito.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        txtTarjetaDebito.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTarjetaDebito.setText("0");
        txtTarjetaDebito.setName("Cheque"); // NOI18N
        txtTarjetaDebito.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTarjetaDebitoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTarjetaDebitoKeyTyped(evt);
            }
        });

        txtTarjetaCredito.setBackground(new java.awt.Color(255, 204, 204));
        txtTarjetaCredito.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        txtTarjetaCredito.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTarjetaCredito.setText("0");
        txtTarjetaCredito.setName("Cheque"); // NOI18N
        txtTarjetaCredito.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTarjetaCreditoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTarjetaCreditoKeyTyped(evt);
            }
        });

        lbCheque7.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbCheque7.setText("Tarjeta debito:");

        lbCheque8.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbCheque8.setText("Tarjeta credito:");

        jButton32.setBackground(new java.awt.Color(255, 255, 255));
        jButton32.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jButton32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pagarTotal.png"))); // NOI18N
        jButton32.setBorder(null);
        jButton32.setBorderPainted(false);
        jButton32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton32ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbCheque2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbNC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbEtiqueta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbEfectivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtEfectivo)
                            .addComponent(txtValor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                            .addComponent(txtNC, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtCheque, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lbCheque8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbTarjetas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbCheque7, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTarjetaCredito, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                            .addComponent(txtTarjetaDebito))))
                .addGap(3, 3, 3)
                .addComponent(jButton32)
                .addGap(2, 2, 2))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtEfectivo)
                    .addComponent(lbEfectivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(1, 1, 1)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbEtiqueta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(2, 2, 2)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNC, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCheque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbCheque2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTarjetaDebito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbCheque7, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addComponent(lbCheque8)
                .addGap(0, 0, 0)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTarjetaCredito, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(cmbTarjetas))
                .addGap(3, 3, 3))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        lbCheque1.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lbCheque1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbCheque1.setText("Total Dinero:");

        lbEtiqueta2.setFont(new java.awt.Font("Century Gothic", 1, 30)); // NOI18N
        lbEtiqueta2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbEtiqueta2.setText("CAMBIO:");

        txtTotalDinero.setEditable(false);
        txtTotalDinero.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        txtTotalDinero.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTotalDinero.setText("0");
        txtTotalDinero.setName("Cheque"); // NOI18N
        txtTotalDinero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTotalDineroKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTotalDineroKeyTyped(evt);
            }
        });

        txtCambio.setEditable(false);
        txtCambio.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        txtCambio.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtCambio.setText("0");
        txtCambio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCambioKeyReleased(evt);
            }
        });

        lbEtiqueta1.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lbEtiqueta1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbEtiqueta1.setText("Total Factura:");

        txtTotal.setEditable(false);
        txtTotal.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        txtTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTotal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTotalKeyReleased(evt);
            }
        });

        txtTotalPropina.setEditable(false);
        txtTotalPropina.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        txtTotalPropina.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTotalPropina.setText("0");
        txtTotalPropina.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtTotalPropina.setEnabled(false);
        txtTotalPropina.setName("Cheque"); // NOI18N
        txtTotalPropina.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTotalPropinaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTotalPropinaKeyTyped(evt);
            }
        });

        lbCheque6.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lbCheque6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbCheque6.setText("Total Propina:");

        lbCheque9.setFont(new java.awt.Font("Century Gothic", 1, 30)); // NOI18N
        lbCheque9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbCheque9.setText("TOTAL:");

        txtTotalGeneral.setEditable(false);
        txtTotalGeneral.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        txtTotalGeneral.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTotalGeneral.setText("0");
        txtTotalGeneral.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtTotalGeneral.setEnabled(false);
        txtTotalGeneral.setName("Cheque"); // NOI18N
        txtTotalGeneral.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTotalGeneralKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTotalGeneralKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lbCheque9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbCheque6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbEtiqueta1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbCheque1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbEtiqueta2, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTotalDinero)
                    .addComponent(txtTotalPropina)
                    .addComponent(txtTotal)
                    .addComponent(txtTotalGeneral, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                    .addComponent(txtCambio, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(0, 35, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTotalDinero)
                    .addComponent(lbCheque1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbEtiqueta1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTotalPropina, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbCheque6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(6, 6, 6)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbCheque9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTotalGeneral))
                .addGap(3, 3, 3)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbEtiqueta2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCambio))
                .addGap(8, 8, 8))
        );

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jButton1.setBackground(new java.awt.Color(255, 153, 153));
        jButton1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jButton1.setText("CANCELAR");
        jButton1.setBorder(null);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        pnlPropina.setBackground(new java.awt.Color(255, 255, 255));
        pnlPropina.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Propina", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 0, 12))); // NOI18N

        lbCheque4.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lbCheque4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCheque4.setText("En %:");

        txtPorcPropina.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        txtPorcPropina.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPorcPropina.setText("0");
        txtPorcPropina.setName("Cheque"); // NOI18N
        txtPorcPropina.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPorcPropinaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPorcPropinaKeyTyped(evt);
            }
        });

        lbCheque5.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lbCheque5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCheque5.setText("En Valor:");

        txtPropina.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        txtPropina.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPropina.setName("Cheque"); // NOI18N
        txtPropina.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPropinaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPropinaKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout pnlPropinaLayout = new javax.swing.GroupLayout(pnlPropina);
        pnlPropina.setLayout(pnlPropinaLayout);
        pnlPropinaLayout.setHorizontalGroup(
            pnlPropinaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPropinaLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlPropinaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbCheque5, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                    .addComponent(lbCheque4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlPropinaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPorcPropina, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPropina, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlPropinaLayout.setVerticalGroup(
            pnlPropinaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPropinaLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(pnlPropinaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCheque4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPorcPropina, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlPropinaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCheque5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPropina, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(81, 81, 81))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlNumerico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlPropina, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(63, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(pnlNumerico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pnlPropina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 5, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jButton2.getAccessibleContext().setAccessibleDescription("Ctrl+F5");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtValorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorKeyReleased

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jButton2ActionPerformed(null);
        } else {
            if (txtValor.getText().equals("") || txtValor.getText().equals(this.simbolo) || txtValor.getText().equals(this.simbolo + " ")) {
                txtValor.setText("0");
            }

            txtValor.setText(big.setMoneda(big.getMoneda(txtValor.getText())));
            calcularEfectivo();
        }
    }//GEN-LAST:event_txtValorKeyReleased

    private void txtTotalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalKeyReleased

    private void txtCambioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCambioKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCambioKeyReleased

    private void txtChequeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtChequeKeyReleased
        if (txtCheque.getText().equals("") || txtCheque.getText().equals(this.simbolo) || txtCheque.getText().equals(this.simbolo + " ")) {
            txtCheque.setText("0");
        }

        txtCheque.setText(big.setMonedaExacta(big.getMoneda(txtCheque.getText())));

        calcularEfectivo();
        if (big.getMoneda(txtTotalDinero.getText()).compareTo(valorTotal.add(big.getMoneda(txtTotalPropina.getText()))) == 1) {
            txtCheque.setText(big.setMoneda(big.getMoneda(txtCheque.getText().substring(0, txtCheque.getText().length() - 1))));
        }
        calcularEfectivo();
    }//GEN-LAST:event_txtChequeKeyReleased

    private void txtChequeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtChequeKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtChequeKeyTyped

    private void txtNCKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNCKeyPressed

    }//GEN-LAST:event_txtNCKeyPressed

    private void txtNCKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNCKeyReleased
        if (NC != null) {
            if (NC.compareTo(big.getMoneda(txtNC.getText())) == -1) {
                txtNC.setText(txtNC.getText().substring(0, txtNC.getText().length() - 1));
                metodos.msgAdvertencia(null, "Valor no valido, el cliente no cuenta con esta suma");
                txtNC.setText(this.simbolo + " 0");
                return;
            }

            if (txtNC.getText().equals("") || txtNC.getText().equals(this.simbolo) || txtNC.getText().equals(this.simbolo + " ")) {
                txtNC.setText(this.simbolo + " 0");
            }

            txtNC.setText(big.setMonedaExacta(big.getMoneda(txtNC.getText())));

            calcularEfectivo();
            if (big.getMoneda(txtTotalDinero.getText()).compareTo(valorTotal.add(big.getMoneda(txtTotalPropina.getText()))) == 1) {
                txtNC.setText(big.setMoneda(big.getMoneda(txtNC.getText().substring(0, txtNC.getText().length() - 1))));
            }
            calcularEfectivo();
        } else {
            txtNC.setText(this.simbolo + " 0");
        }
    }//GEN-LAST:event_txtNCKeyReleased

    private void txtNCKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNCKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtNCKeyTyped

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (big.getMoneda(txtTotalDinero.getText()).compareTo(BigDecimal.ZERO) <= 0
                && big.getMoneda(txtTotalGeneral.getText()).compareTo(BigDecimal.ZERO) != 0) {
            metodos.msgError(null, "Debe ingresar el metodo de pago");
            lbEtiqueta1.requestFocus();
            return;
        }

        String franquisia = "";
        try {
            franquisia = cmbTarjetas.getSelectedItem().toString();
        } catch (Exception e) {
        }

        if (big.getMoneda(txtTarjetaCredito.getText()).compareTo(BigDecimal.ZERO) > 0 && franquisia.equals("")) {
            metodos.msgAdvertenciaAjustado(null, "Debe seleccionar la franquisia");
            return;
        }

        if (big.getMoneda(txtCambio.getText()).compareTo(BigDecimal.ZERO) < 0) {
            metodos.msgAdvertenciaAjustado(null, "Valor insuficiente");
            lbEtiqueta1.requestFocus();
            return;
        }

        Double porcPropina = 0.0;
        if (instancias.getConfiguraciones().isRestaurante()) {
            try {
                porcPropina = Double.parseDouble(txtPorcPropina.getText().replace(",", "."));
            } catch (Exception e) {
                metodos.msgError(null, "Porcentaje de propina incorrecto. ");
                txtPorcPropina.requestFocus();
                return;
            }

            if (porcPropina < 0) {
                metodos.msgError(null, "Ingrese un porcentaje válido. ");
                txtPorcPropina.requestFocus();
                return;
            }
        }

        instancias.setEfectivoDevuelta(big.getMoneda(txtEfectivo.getText()));
        instancias.setNcDevuelta(big.getMoneda(txtNC.getText()));
        instancias.setTarjetaDevuelta(big.getMoneda(txtTarjetaDebito.getText()));
        instancias.setTarjetaCredito(big.getMoneda(txtTarjetaCredito.getText()));
        instancias.setChequeDevuelta(big.getMoneda(txtCheque.getText()));
        instancias.setPorcPropina(String.valueOf(porcPropina));
        instancias.setPropina(big.getMoneda(txtPropina.getText()));
        instancias.setTotalPropina(big.getMoneda(txtTotalPropina.getText()));

        instancias.setFranquisia(franquisia);

        String comision = "";
        try {
            comision = instancias.getSql().getComisionTarjeta(franquisia);
        } catch (Exception e) {
        }
        instancias.setComision(comision);

        BigDecimal total = BigDecimal.ZERO, valorComision = BigDecimal.ZERO, totalFinal = BigDecimal.ZERO;
        if (!comision.equals("")) {
            total = big.getMoneda(txtTotal.getText());
            valorComision = big.getMoneda(txtTotal.getText()).multiply(big.getMoneda("0,0" + comision));
            totalFinal = total.subtract(valorComision);
        }

        instancias.setValorComision(valorComision);
        instancias.setTotalFacturaComision(totalFinal);

        if (big.getMoneda(txtCambio.getText()).compareTo(BigDecimal.ZERO) == - 1) {
            instancias.setDevuelta(big.getBigDecimal("0"));
        } else {
            instancias.setDevuelta(big.getMoneda(txtCambio.getText()));
        }

        if (!txtNC.getText().equals("") & !txtNC.getText().equals(this.simbolo) & !txtNC.getText().equals(this.simbolo + " ")) {
            Object[][] notas = instancias.getSql().getNcCliente(cliente);
            BigDecimal nc = big.getMoneda(txtNC.getText());

            for (Object[] nota : notas) {
                if (nc.compareTo(big.getBigDecimal("0")) == -1) {
                    break;
                } else if (big.getBigDecimal(nota[0]).compareTo(nc) == -1 || big.getBigDecimal(nota[0]).compareTo(nc) == 0) {
                    nc = nc.subtract(big.getBigDecimal(nota[0]));
                    if (!instancias.getSql().descontarNc((String) nota[1], "0")) {
                        metodos.msgError(null, "Hubo un problema al modificar el saldo de la nota credito: " + nota[1]);
                    }
                } else {
                    if (!instancias.getSql().descontarNc((String) nota[1], String.valueOf((big.getBigDecimal(nota[0])).subtract(nc)))) {
                        metodos.msgError(null, "Hubo un problema al modificar el saldo de la nota credito: " + nota[1]);
                    }
                    nc = nc.subtract(big.getBigDecimal(nota[0]));
                }
            }
        }

        instancias.setCancelarFactura(false);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtEfectivoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEfectivoKeyReleased
        if (txtEfectivo.getText().equals("") || txtEfectivo.getText().equals(this.simbolo) || txtEfectivo.getText().equals(this.simbolo + " ")) {
            txtEfectivo.setText("0");
        }

        txtEfectivo.setText(big.setMoneda(big.getMoneda(txtEfectivo.getText())));
        calcularEfectivo();
    }//GEN-LAST:event_txtEfectivoKeyReleased

    private void txtTotalDineroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalDineroKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalDineroKeyReleased

    private void txtTotalDineroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalDineroKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalDineroKeyTyped

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        txtValor.setText(big.setMoneda(big.getMoneda("2000")));
        calcularEfectivo();
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        txtValor.setText(big.setMoneda(big.getMoneda("1000").add(big.getMoneda(txtValor.getText()))));
        calcularEfectivo();
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        txtValor.setText(big.setMoneda(big.getMoneda("5000").add(big.getMoneda(txtValor.getText()))));
        calcularEfectivo();
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        txtValor.setText(big.setMoneda(big.getMoneda("10000").add(big.getMoneda(txtValor.getText()))));
        calcularEfectivo();
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        txtValor.setText(big.setMoneda(big.getMoneda("50000").add(big.getMoneda(txtValor.getText()))));
        calcularEfectivo();
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        txtValor.setText(big.setMoneda(big.getMoneda("20000").add(big.getMoneda(txtValor.getText()))));
        calcularEfectivo();
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        txtValor.setText(big.setMoneda(big.getMoneda("100000").add(big.getMoneda(txtValor.getText()))));
        calcularEfectivo();
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        instancias.setCancelarFactura(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtTarjetaDebitoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTarjetaDebitoKeyReleased
        if (txtTarjetaDebito.getText().equals("") || txtTarjetaDebito.getText().equals(this.simbolo) || txtTarjetaDebito.getText().equals(this.simbolo + " ")) {
            txtTarjetaDebito.setText("0");
        }

        txtTarjetaDebito.setText(big.setMoneda(big.getMoneda(txtTarjetaDebito.getText())));
        calcularEfectivo();

        if (big.getMoneda(txtTotalDinero.getText()).compareTo(valorTotal.add(big.getMoneda(txtTotalPropina.getText()))) == 1) {
            txtTarjetaDebito.setText(big.setMoneda(big.getMoneda(txtTarjetaDebito.getText().substring(0, txtTarjetaDebito.getText().length() - 1))));
        }
        calcularEfectivo();
    }//GEN-LAST:event_txtTarjetaDebitoKeyReleased

    private void txtTarjetaDebitoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTarjetaDebitoKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtTarjetaDebitoKeyTyped

    private void txtTarjetaCreditoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTarjetaCreditoKeyReleased
        if (txtTarjetaCredito.getText().equals("") || txtTarjetaCredito.getText().equals(this.simbolo) || txtTarjetaCredito.getText().equals(this.simbolo + " ")) {
            txtTarjetaCredito.setText("0");
        }

        txtTarjetaCredito.setText(big.setMoneda(big.getMoneda(txtTarjetaCredito.getText())));

        calcularEfectivo();

        if (big.getMoneda(txtTotalDinero.getText()).compareTo(valorTotal.add(big.getMoneda(txtTotalPropina.getText()))) == 1) {
            txtTarjetaCredito.setText(big.setMoneda(big.getMoneda(txtTarjetaCredito.getText().substring(0, txtTarjetaCredito.getText().length() - 1))));
        }
        calcularEfectivo();
    }//GEN-LAST:event_txtTarjetaCreditoKeyReleased

    private void txtTarjetaCreditoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTarjetaCreditoKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtTarjetaCreditoKeyTyped

    private void txtPorcPropinaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPorcPropinaKeyReleased
        if (!txtPorcPropina.getText().equals("")) {
            BigDecimal porc = BigDecimal.valueOf(Double.parseDouble(txtPorcPropina.getText().replace(",", ".")));
            BigDecimal total = porc.multiply(valorNeto).divide(big.getBigDecimal("100"));
            txtPropina.setText(this.simbolo + " 0");
            txtTotalPropina.setText(big.setMonedaExacta(total));
            calcularEfectivo();
        }
    }//GEN-LAST:event_txtPorcPropinaKeyReleased

    private void txtPorcPropinaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPorcPropinaKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtPorcPropinaKeyTyped

    private void txtPropinaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPropinaKeyReleased
        if (txtPropina.getText().equals("") || txtPropina.getText().equals(this.simbolo) || txtPropina.getText().equals(this.simbolo + " ")) {
            txtPropina.setText("0");
        }
        txtPropina.setText(big.setMonedaExacta(big.getMoneda(txtPropina.getText())));
        txtTotalPropina.setText(big.setMonedaExacta(big.getMoneda(txtPropina.getText())));
        txtPorcPropina.setText("0");
        calcularEfectivo();
    }//GEN-LAST:event_txtPropinaKeyReleased

    private void txtPropinaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPropinaKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtPropinaKeyTyped

    private void txtTotalPropinaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalPropinaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalPropinaKeyReleased

    private void txtTotalPropinaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalPropinaKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtTotalPropinaKeyTyped

    private void txtTotalGeneralKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalGeneralKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalGeneralKeyReleased

    private void txtTotalGeneralKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalGeneralKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalGeneralKeyTyped

    private void jButton32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton32ActionPerformed
        txtValor.setText(big.setMoneda(big.getMoneda(txtEfectivo.getText()).add(big.getMoneda(txtTotalPropina.getText()))));
        calcularEfectivo();
    }//GEN-LAST:event_jButton32ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        txtValor.setText(big.setMoneda(big.getMoneda("50").add(big.getMoneda(txtValor.getText()))));
        calcularEfectivo();
    }//GEN-LAST:event_jButton21ActionPerformed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        txtValor.setText(big.setMoneda(big.getMoneda("100").add(big.getMoneda(txtValor.getText()))));
        calcularEfectivo();
    }//GEN-LAST:event_jButton22ActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        txtValor.setText(big.setMoneda(big.getMoneda("200").add(big.getMoneda(txtValor.getText()))));
        calcularEfectivo();
    }//GEN-LAST:event_jButton23ActionPerformed

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
        txtValor.setText(big.setMoneda(big.getMoneda("500").add(big.getMoneda(txtValor.getText()))));
        calcularEfectivo();
    }//GEN-LAST:event_jButton24ActionPerformed

    public void calcularEfectivo() {
        BigDecimal efectivo, otrosPagos, total, totalPago;

        if (txtValor.getText().equals("") || txtValor.getText().equals(this.simbolo)) {
            txtValor.setText("0");
        }

        total = big.getMoneda(txtTotal.getText());

        otrosPagos = (big.getMoneda(txtNC.getText()).add(big.getMoneda(txtCheque.getText())))
                .add(big.getMoneda(txtTarjetaCredito.getText())).add(big.getMoneda(txtTarjetaDebito.getText()));

        efectivo = total.subtract(otrosPagos);
        totalPago = otrosPagos.add(big.getMoneda(txtValor.getText()));

        int res = efectivo.compareTo(big.getBigDecimal("0"));

        if (res == 1) {
            txtEfectivo.setText(big.setMoneda(efectivo));
        } else {
            txtEfectivo.setText(this.simbolo + " 0");
        }

        txtTotalDinero.setText(big.setMoneda(big.getBigDecimal(totalPago)));

        BigDecimal valorTotal = this.valorTotal.add(big.getMoneda(txtTotalPropina.getText()));
        txtCambio.setText(big.setMoneda(totalPago.subtract(valorTotal)));
        txtTotalGeneral.setText(big.setMoneda(valorTotal));

        if (totalPago.subtract(valorTotal).compareTo(BigDecimal.ZERO) < 0) {
            txtValor.setBackground(new Color(255, 153, 153));
            txtNC.setBackground(new Color(255, 153, 153));
            txtCheque.setBackground(new Color(255, 153, 153));
            txtTarjetaCredito.setBackground(new Color(255, 153, 153));
            txtTarjetaDebito.setBackground(new Color(255, 153, 153));
        } else {
            txtValor.setBackground(new Color(153, 255, 153));
            txtNC.setBackground(new Color(153, 255, 153));
            txtCheque.setBackground(new Color(153, 255, 153));
            txtTarjetaCredito.setBackground(new Color(153, 255, 153));
            txtTarjetaDebito.setBackground(new Color(153, 255, 153));
        }
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
            java.util.logging.Logger.getLogger(VistaMetodoPagos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaMetodoPagos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaMetodoPagos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaMetodoPagos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                VistaMetodoPagos dialog = new VistaMetodoPagos(new javax.swing.JFrame(), true, new BigDecimal(BigInteger.ZERO), null, null, new BigDecimal(BigInteger.ZERO));
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);

                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cmbTarjetas;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton32;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lbCheque1;
    private javax.swing.JLabel lbCheque2;
    private javax.swing.JLabel lbCheque4;
    private javax.swing.JLabel lbCheque5;
    private javax.swing.JLabel lbCheque6;
    private javax.swing.JLabel lbCheque7;
    private javax.swing.JLabel lbCheque8;
    private javax.swing.JLabel lbCheque9;
    private javax.swing.JLabel lbEfectivo;
    private javax.swing.JLabel lbEtiqueta;
    private javax.swing.JLabel lbEtiqueta1;
    private javax.swing.JLabel lbEtiqueta2;
    private javax.swing.JLabel lbEtiqueta3;
    private javax.swing.JLabel lbNC;
    private javax.swing.JPanel pnlNumerico;
    private javax.swing.JPanel pnlPropina;
    private javax.swing.JTextField txtCambio;
    private javax.swing.JTextField txtCheque;
    private javax.swing.JTextField txtEfectivo;
    private javax.swing.JTextField txtNC;
    private javax.swing.JTextField txtPorcPropina;
    private javax.swing.JTextField txtPropina;
    private javax.swing.JTextField txtTarjetaCredito;
    private javax.swing.JTextField txtTarjetaDebito;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JTextField txtTotalDinero;
    private javax.swing.JTextField txtTotalGeneral;
    private javax.swing.JTextField txtTotalPropina;
    private javax.swing.JTextField txtValor;
    // End of variables declaration//GEN-END:variables
}
