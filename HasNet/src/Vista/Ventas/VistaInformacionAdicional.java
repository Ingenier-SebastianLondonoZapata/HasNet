package Vista.Ventas;

import Controlador.Alertas.ControladorAlertas;

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
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class VistaInformacionAdicional extends javax.swing.JDialog {

    BigDecimal NC, valorTotal, valorNeto;
    String cliente, simbolo;
    private boolean soloInformacion;
    private javax.swing.JTextField txtDiasPlazo;
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

    public VistaInformacionAdicional(java.awt.Frame parent, boolean modal, BigDecimal valor, Instancias instancias, String cliente, BigDecimal valorNeto) {
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
        pnlInformacionAdicional.setVisible(false);
    }

    public VistaInformacionAdicional(java.awt.Frame parent, boolean modal, BigDecimal valor, Instancias instancias, String cliente, BigDecimal valorNeto, JPanel panelInformacion) {
        this(parent, modal, valor, instancias, cliente, valorNeto, panelInformacion, null);
    }

    public VistaInformacionAdicional(java.awt.Frame parent, boolean modal, BigDecimal valor, Instancias instancias, String cliente, BigDecimal valorNeto,
            JPanel panelInformacion, javax.swing.JTextField campoDiasPlazo) {
        this(parent, modal, valor, instancias, cliente, valorNeto);
        instalarPanelInformacion(panelInformacion);
        vincularDiasPlazo(campoDiasPlazo);
        pack();
        setLocationRelativeTo(null);
    }

    public VistaInformacionAdicional(java.awt.Frame parent, JPanel panelInformacion) {
        super(parent, true);
        initComponents();
        this.soloInformacion = true;

        JPanel contenido = new JPanel(new java.awt.BorderLayout());
        contenido.setBackground(Color.WHITE);
        contenido.setBorder(javax.swing.BorderFactory.createLineBorder(Color.BLACK, 2));
        contenido.add(panelInformacion, java.awt.BorderLayout.CENTER);

        btnGuardar.setText("ACEPTAR");
        JPanel botones = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 10, 10));
        botones.setBackground(Color.WHITE);
        btnGuardar.setPreferredSize(new java.awt.Dimension(141, 40));
        botones.add(btnGuardar);
        contenido.add(botones, java.awt.BorderLayout.SOUTH);

        setContentPane(contenido);
        this.getRootPane().registerKeyboardAction(accion("FINALIZAR", this), KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        pack();
        setLocationRelativeTo(null);
    }

    private void instalarPanelInformacion(JPanel panelInformacion) {
        pnlInformacionAdicional.removeAll();
        pnlInformacionAdicional.add(panelInformacion, java.awt.BorderLayout.CENTER);
        pnlInformacionAdicional.setVisible(true);
    }

    private void vincularDiasPlazo(javax.swing.JTextField campoDiasPlazo) {
        txtDiasPlazo = campoDiasPlazo;
        if (txtDiasPlazo == null) {
            return;
        }

        txtDiasPlazo.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                actualizarEstadoPagos();
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                actualizarEstadoPagos();
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                actualizarEstadoPagos();
            }
        });
        actualizarEstadoPagos();
    }

    private boolean esVentaACredito() {
        if (txtDiasPlazo == null) {
            return false;
        }
        try {
            return Integer.parseInt(txtDiasPlazo.getText().trim()) > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void conservarPropina() {
        if (!instancias.getConfiguraciones().isRestaurante()) {
            return;
        }

        String porcPropina = "0";
        try {
            porcPropina = String.valueOf(Double.parseDouble(txtPorcPropina.getText().replace(",", ".")));
        } catch (NumberFormatException e) {
        }

        instancias.setPorcPropina(porcPropina);
        instancias.setPropina(big.getMoneda(txtPropina.getText()));
        instancias.setTotalPropina(big.getMoneda(txtTotalPropina.getText()));
    }

    private void actualizarEstadoPagos() {
        boolean habilitar = !esVentaACredito();

        if (!habilitar) {
            String cero = this.simbolo + " 0";
            txtValor.setText(cero);
            txtNC.setText(cero);
            txtCheque.setText(cero);
            txtTarjetaDebito.setText(cero);
            txtTarjetaCredito.setText(cero);
            cmbTarjetas.setSelectedIndex(0);
        }

        txtValor.setEnabled(habilitar);
        txtNC.setEnabled(habilitar);
        txtCheque.setEnabled(habilitar);
        txtTarjetaDebito.setEnabled(habilitar);
        txtTarjetaCredito.setEnabled(habilitar);
        cmbTarjetas.setEnabled(habilitar);
        btnPagarTodo.setEnabled(habilitar);

        calcularEfectivo();

        if (!habilitar) {
            Color gris = new Color(230, 230, 230);
            txtValor.setBackground(gris);
            txtNC.setBackground(gris);
            txtCheque.setBackground(gris);
            txtTarjetaDebito.setBackground(gris);
            txtTarjetaCredito.setBackground(gris);
        }
    }

    public ActionListener accion(final String opc, final JDialog ventana) {
        ActionListener a = new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                switch (opc) {
                    case "FINALIZAR":
                        btnGuardarActionPerformed(null);
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
        btnGuardar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        btnCancelar = new javax.swing.JButton();
        pnlPropina = new javax.swing.JPanel();
        lbCheque4 = new javax.swing.JLabel();
        txtPorcPropina = new javax.swing.JTextField();
        lbCheque5 = new javax.swing.JLabel();
        txtPropina = new javax.swing.JTextField();
        pnlInformacionAdicional = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lbEfectivo = new javax.swing.JLabel();
        txtEfectivo = new javax.swing.JTextField();
        lbEtiqueta = new javax.swing.JLabel();
        txtValor = new javax.swing.JTextField();
        btnPagarTodo = new javax.swing.JButton();
        lbNC = new javax.swing.JLabel();
        lbCheque2 = new javax.swing.JLabel();
        txtNC = new javax.swing.JTextField();
        txtCheque = new javax.swing.JTextField();
        lbCheque7 = new javax.swing.JLabel();
        txtTarjetaDebito = new javax.swing.JTextField();
        lbCheque8 = new javax.swing.JLabel();
        cmbTarjetas = new javax.swing.JComboBox();
        txtTarjetaCredito = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        txtTotalDinero = new javax.swing.JTextField();
        lbCheque1 = new javax.swing.JLabel();
        lbEtiqueta1 = new javax.swing.JLabel();
        lbCheque6 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        txtTotalPropina = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        lbCheque9 = new javax.swing.JLabel();
        lbEtiqueta2 = new javax.swing.JLabel();
        txtTotalGeneral = new javax.swing.JTextField();
        txtCambio = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Forma de pago");
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        btnGuardar.setBackground(new java.awt.Color(131, 225, 71));
        btnGuardar.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnGuardar.setText("GUARDAR ");
        btnGuardar.setBorder(null);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        btnCancelar.setBackground(new java.awt.Color(255, 178, 2));
        btnCancelar.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnCancelar.setText("CANCELAR");
        btnCancelar.setBorder(null);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        pnlPropina.setBackground(new java.awt.Color(255, 255, 255));
        pnlPropina.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Agregar Propina", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 13))); // NOI18N

        lbCheque4.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
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

        lbCheque5.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lbCheque5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
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
                .addGap(5, 5, 5)
                .addComponent(lbCheque4, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPorcPropina, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbCheque5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPropina, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlPropinaLayout.setVerticalGroup(
            pnlPropinaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPropinaLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(pnlPropinaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbCheque4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlPropinaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtPorcPropina, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbCheque5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtPropina, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(5, 5, 5))
        );

        pnlInformacionAdicional.setLayout(new java.awt.BorderLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        lbEfectivo.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lbEfectivo.setText("Efectivo:");

        txtEfectivo.setEditable(false);
        txtEfectivo.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtEfectivo.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtEfectivo.setText("0");
        txtEfectivo.setName("Efectivo"); // NOI18N
        txtEfectivo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEfectivoKeyReleased(evt);
            }
        });

        lbEtiqueta.setFont(new java.awt.Font("Arial", 1, 30)); // NOI18N
        lbEtiqueta.setText("PAGA:");

        txtValor.setBackground(new java.awt.Color(255, 204, 204));
        txtValor.setFont(new java.awt.Font("Arial", 0, 19)); // NOI18N
        txtValor.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtValor.setText("0");
        txtValor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtValorKeyReleased(evt);
            }
        });

        btnPagarTodo.setBackground(new java.awt.Color(255, 255, 255));
        btnPagarTodo.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnPagarTodo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pagarTotal.png"))); // NOI18N
        btnPagarTodo.setBorder(null);
        btnPagarTodo.setBorderPainted(false);
        btnPagarTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPagarTodoActionPerformed(evt);
            }
        });

        lbNC.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lbNC.setText("Nota crédito:");

        lbCheque2.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lbCheque2.setText("Cheque:");

        txtNC.setBackground(new java.awt.Color(255, 204, 204));
        txtNC.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
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
        txtCheque.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
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

        lbCheque7.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lbCheque7.setText("Tarjeta débito:");

        txtTarjetaDebito.setBackground(new java.awt.Color(255, 204, 204));
        txtTarjetaDebito.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
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

        lbCheque8.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lbCheque8.setText("Tarjeta crédito:");

        cmbTarjetas.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

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

        txtTotalDinero.setEditable(false);
        txtTotalDinero.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
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

        lbCheque1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lbCheque1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbCheque1.setText("Total Dinero:");

        lbEtiqueta1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lbEtiqueta1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbEtiqueta1.setText("Total Factura:");

        lbCheque6.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lbCheque6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbCheque6.setText("Total Propina:");

        txtTotal.setEditable(false);
        txtTotal.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTotal.setText("0");
        txtTotal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTotalKeyReleased(evt);
            }
        });

        txtTotalPropina.setEditable(false);
        txtTotalPropina.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
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

        lbCheque9.setFont(new java.awt.Font("Arial", 1, 28)); // NOI18N
        lbCheque9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbCheque9.setText("TOTAL:");

        lbEtiqueta2.setFont(new java.awt.Font("Arial", 1, 28)); // NOI18N
        lbEtiqueta2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbEtiqueta2.setText("CAMBIO:");

        txtTotalGeneral.setEditable(false);
        txtTotalGeneral.setFont(new java.awt.Font("Arial", 0, 19)); // NOI18N
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

        txtCambio.setEditable(false);
        txtCambio.setFont(new java.awt.Font("Arial", 0, 19)); // NOI18N
        txtCambio.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtCambio.setText("0");
        txtCambio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCambioKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbEtiqueta2, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtCambio, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(lbCheque9, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                                    .addComponent(txtTotalGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(lbCheque6, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtTotalPropina))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(lbEtiqueta1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtTotal))
                                .addComponent(jSeparator2)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(cmbTarjetas, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtTarjetaCredito))
                                .addComponent(lbCheque8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(lbCheque7, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                                        .addComponent(lbCheque2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lbNC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lbEtiqueta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lbEfectivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(txtCheque, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtNC, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtValor, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                                            .addComponent(txtEfectivo))
                                        .addComponent(txtTarjetaDebito, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(lbCheque1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtTotalDinero, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(3, 3, 3)
                        .addComponent(btnPagarTodo)))
                .addGap(5, 5, 5))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtEfectivo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbEfectivo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPagarTodo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbEtiqueta)
                    .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbNC, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNC, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbCheque2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCheque))
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbCheque7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTarjetaDebito))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbCheque8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbTarjetas, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(txtTarjetaCredito))
                .addGap(10, 10, 10)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTotalDinero)
                    .addComponent(lbCheque1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbEtiqueta1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTotalPropina, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(lbCheque6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTotalGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbCheque9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbEtiqueta2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCambio))
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlInformacionAdicional, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlPropina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(5, 5, 5))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(pnlInformacionAdicional, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlPropina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        btnGuardar.getAccessibleContext().setAccessibleDescription("Ctrl+F5");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtValorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnGuardarActionPerformed(null);
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
                ControladorAlertas.alert("Valor no valido, el cliente no cuenta con esta suma");
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

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (soloInformacion) {
            this.dispose();
            return;
        }

        if (esVentaACredito()) {
            new FuncionalidadVentas().reiniciarPagos(instancias);
            conservarPropina();
            instancias.setCancelarFactura(false);
            this.dispose();
            return;
        }

        if (big.getMoneda(txtTotalDinero.getText()).compareTo(BigDecimal.ZERO) <= 0
                && big.getMoneda(txtTotalGeneral.getText()).compareTo(BigDecimal.ZERO) != 0) {
            ControladorAlertas.alertFail("Debe ingresar el metodo de pago");
            lbEtiqueta1.requestFocus();
            return;
        }

        String franquisia = "";
        try {
            franquisia = cmbTarjetas.getSelectedItem().toString();
        } catch (Exception e) {
        }

        if (big.getMoneda(txtTarjetaCredito.getText()).compareTo(BigDecimal.ZERO) > 0 && franquisia.equals("")) {
            ControladorAlertas.alert("Debe seleccionar la franquisia");
            return;
        }

        if (big.getMoneda(txtCambio.getText()).compareTo(BigDecimal.ZERO) < 0) {
            ControladorAlertas.alert("Valor insuficiente");
            lbEtiqueta1.requestFocus();
            return;
        }

        Double porcPropina = 0.0;
        if (instancias.getConfiguraciones().isRestaurante()) {
            try {
                porcPropina = Double.parseDouble(txtPorcPropina.getText().replace(",", "."));
            } catch (Exception e) {
                ControladorAlertas.alertFail("Porcentaje de propina incorrecto. ");
                txtPorcPropina.requestFocus();
                return;
            }

            if (porcPropina < 0) {
                ControladorAlertas.alertFail("Ingrese un porcentaje válido. ");
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
                        ControladorAlertas.alertFail("Hubo un problema al modificar el saldo de la nota credito: " + nota[1]);
                    }
                } else {
                    if (!instancias.getSql().descontarNc((String) nota[1], String.valueOf((big.getBigDecimal(nota[0])).subtract(nc)))) {
                        ControladorAlertas.alertFail("Hubo un problema al modificar el saldo de la nota credito: " + nota[1]);
                    }
                    nc = nc.subtract(big.getBigDecimal(nota[0]));
                }
            }
        }

        instancias.setCancelarFactura(false);
        this.dispose();
    }//GEN-LAST:event_btnGuardarActionPerformed

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

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        instancias.setCancelarFactura(true);
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

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

    private void btnPagarTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPagarTodoActionPerformed
        txtValor.setText(big.setMoneda(big.getMoneda(txtEfectivo.getText()).add(big.getMoneda(txtTotalPropina.getText()))));
        calcularEfectivo();
    }//GEN-LAST:event_btnPagarTodoActionPerformed

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
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                VistaInformacionAdicional dialog = new VistaInformacionAdicional(new javax.swing.JFrame(), true, new BigDecimal(BigInteger.ZERO), null, null, new BigDecimal(BigInteger.ZERO));
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
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnPagarTodo;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cmbTarjetas;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
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
    private javax.swing.JLabel lbNC;
    private javax.swing.JPanel pnlInformacionAdicional;
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
