package formularios.Parqueadero;

import clases.Instancias;
import clases.Parqueadero.ndParqueadero;
import Utilidades.BaseDatos.SQL;
import clases.big;
import clases.metodosGenerales;
import Modelo.Terceros.ModeloContacto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.KeyStroke;

public class dlgParqueo extends javax.swing.JDialog {

    private Instancias instancias;
    metodosGenerales metodos = new metodosGenerales();
    String codigo1, idParqueadero;
    Object[][] datosMaestraParqueadero;
    int horasTotales;
    Boolean mensualidad = false;

    public dlgParqueo(java.awt.Frame parent, boolean modal, String servicio, String codigo, String parqueadero) {
        super(parent, modal);
        initComponents();

        this.setLocationRelativeTo(null);
        this.setTitle("SERVICIO: " + servicio);

        metodosGenerales metodos = new metodosGenerales();

        instancias = Instancias.getInstancias();
        codigo1 = codigo;
        idParqueadero = parqueadero;

        pnlPago.setVisible(false);
        lbServicio8.setVisible(false);
        txtNombre.setVisible(false);
        txtDiasPlazo.setEnabled(false);
        lbVigencia.setVisible(false);

        if (codigo.equals("PROD-000000000")) {
            btnCascos.setEnabled(true);
        } else {
            btnCascos.setEnabled(false);
        }

        datosMaestraParqueadero = instancias.getSql().getDatosMaestraParqueadero();

        if (datosMaestraParqueadero[0][11].equals("1")) {
            txtTotalPagar.setEnabled(true);
        } else {
            txtTotalPagar.setEnabled(true);
        }

        if (!parqueadero.equals("")) {
            cargarParqueadero(parqueadero);
        }

        txtPlaca.requestFocus();
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

        jPanel1 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        lbNit13 = new javax.swing.JLabel();
        txtPlaca = new javax.swing.JTextField();
        lbServicio8 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        pnlPago = new javax.swing.JPanel();
        txtFechaEntrada = new javax.swing.JTextField();
        txtCascos = new javax.swing.JTextField();
        txtHoraEntrada = new javax.swing.JTextField();
        lbCascos = new javax.swing.JLabel();
        txtFechaSalida = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        lbServicio4 = new javax.swing.JLabel();
        lbServicio5 = new javax.swing.JLabel();
        lbServicio1 = new javax.swing.JLabel();
        lbServicio3 = new javax.swing.JLabel();
        txtTotalPagar = new javax.swing.JTextField();
        lbServicio7 = new javax.swing.JLabel();
        txtHoraSalida = new javax.swing.JTextField();
        txtTotalEstadia = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        lbServicio6 = new javax.swing.JLabel();
        lbVigencia = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        pnlPlazo = new javax.swing.JPanel();
        txtDiasPlazo = new javax.swing.JTextField();
        pnlCascos = new javax.swing.JPanel();
        btnCascos = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ingreso al parqueadero");
        setName(""); // NOI18N
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        lbNit13.setBackground(new java.awt.Color(255, 255, 255));
        lbNit13.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        lbNit13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit13.setText("PLACA");

        txtPlaca.setBackground(new java.awt.Color(204, 204, 204));
        txtPlaca.setFont(new java.awt.Font("Century Gothic", 1, 110)); // NOI18N
        txtPlaca.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPlaca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPlacaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPlacaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPlacaKeyTyped(evt);
            }
        });

        lbServicio8.setBackground(new java.awt.Color(255, 255, 255));
        lbServicio8.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbServicio8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbServicio8.setText("Autorizar a:");
        lbServicio8.setOpaque(true);

        txtNombre.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        txtNombre.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNombre.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreKeyReleased(evt);
            }
        });

        pnlPago.setBackground(new java.awt.Color(255, 255, 255));

        txtFechaEntrada.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        txtFechaEntrada.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFechaEntrada.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtFechaEntrada.setEnabled(false);

        txtCascos.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtCascos.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCascos.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtCascos.setEnabled(false);

        txtHoraEntrada.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        txtHoraEntrada.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtHoraEntrada.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtHoraEntrada.setEnabled(false);

        lbCascos.setBackground(new java.awt.Color(255, 255, 255));
        lbCascos.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbCascos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCascos.setText("CASCOS");
        lbCascos.setOpaque(true);

        txtFechaSalida.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        txtFechaSalida.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFechaSalida.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtFechaSalida.setEnabled(false);

        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));

        lbServicio4.setBackground(new java.awt.Color(255, 255, 255));
        lbServicio4.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbServicio4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbServicio4.setText("Fecha entrada:");
        lbServicio4.setOpaque(true);

        lbServicio5.setBackground(new java.awt.Color(255, 255, 255));
        lbServicio5.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbServicio5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbServicio5.setText("Fecha salida:");
        lbServicio5.setOpaque(true);

        lbServicio1.setBackground(new java.awt.Color(255, 255, 255));
        lbServicio1.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbServicio1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbServicio1.setText("Hora entrada:");
        lbServicio1.setOpaque(true);

        lbServicio3.setBackground(new java.awt.Color(255, 255, 255));
        lbServicio3.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbServicio3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbServicio3.setText("Hora salida:");
        lbServicio3.setOpaque(true);

        txtTotalPagar.setFont(new java.awt.Font("Century Gothic", 0, 30)); // NOI18N
        txtTotalPagar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTotalPagar.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtTotalPagar.setEnabled(false);
        txtTotalPagar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTotalPagarKeyReleased(evt);
            }
        });

        lbServicio7.setBackground(new java.awt.Color(255, 255, 255));
        lbServicio7.setFont(new java.awt.Font("Century Gothic", 1, 30)); // NOI18N
        lbServicio7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbServicio7.setText("A PAGAR:");
        lbServicio7.setOpaque(true);

        txtHoraSalida.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        txtHoraSalida.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtHoraSalida.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtHoraSalida.setEnabled(false);

        txtTotalEstadia.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        txtTotalEstadia.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTotalEstadia.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtTotalEstadia.setEnabled(false);

        jSeparator4.setForeground(new java.awt.Color(0, 0, 0));

        lbServicio6.setBackground(new java.awt.Color(255, 255, 255));
        lbServicio6.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbServicio6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbServicio6.setText("ESTADÍA:");
        lbServicio6.setOpaque(true);

        lbVigencia.setBackground(new java.awt.Color(102, 255, 102));
        lbVigencia.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lbVigencia.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbVigencia.setText("Este vehiculo tiene una mensualidad vigente");
        lbVigencia.setOpaque(true);

        javax.swing.GroupLayout pnlPagoLayout = new javax.swing.GroupLayout(pnlPago);
        pnlPago.setLayout(pnlPagoLayout);
        pnlPagoLayout.setHorizontalGroup(
            pnlPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPagoLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(pnlPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbVigencia, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPagoLayout.createSequentialGroup()
                        .addComponent(lbServicio7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotalPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator3)
                    .addGroup(pnlPagoLayout.createSequentialGroup()
                        .addGroup(pnlPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbServicio5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbServicio4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtFechaEntrada)
                            .addComponent(txtFechaSalida, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbServicio1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbServicio3, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtHoraEntrada)
                            .addComponent(txtHoraSalida, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pnlPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlPagoLayout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(txtCascos, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPagoLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbCascos, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(pnlPagoLayout.createSequentialGroup()
                        .addComponent(lbServicio6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotalEstadia))))
        );
        pnlPagoLayout.setVerticalGroup(
            pnlPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPagoLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(pnlPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPagoLayout.createSequentialGroup()
                        .addGroup(pnlPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtFechaEntrada)
                            .addComponent(lbServicio1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtHoraEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbServicio4)
                            .addComponent(lbCascos, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)
                        .addGroup(pnlPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtFechaSalida)
                                .addGroup(pnlPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lbServicio3)
                                    .addComponent(txtHoraSalida, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(pnlPagoLayout.createSequentialGroup()
                                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3)
                                .addComponent(txtCascos, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(5, 5, 5))
                    .addGroup(pnlPagoLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(lbServicio5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbServicio6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTotalEstadia))
                .addGap(5, 5, 5)
                .addGroup(pnlPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbServicio7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTotalPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbVigencia, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(lbNit13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 609, Short.MAX_VALUE)
                        .addComponent(txtPlaca, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addComponent(lbServicio8)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtNombre))))
                .addGap(10, 10, 10))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbNit13)
                .addGap(5, 5, 5)
                .addComponent(txtPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNombre)
                    .addComponent(lbServicio8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        pnlPlazo.setBackground(new java.awt.Color(255, 255, 255));
        pnlPlazo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Plazo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 0, 12), new java.awt.Color(0, 0, 0))); // NOI18N

        txtDiasPlazo.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        txtDiasPlazo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDiasPlazo.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtDiasPlazo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDiasPlazoActionPerformed(evt);
            }
        });
        txtDiasPlazo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDiasPlazoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDiasPlazoKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout pnlPlazoLayout = new javax.swing.GroupLayout(pnlPlazo);
        pnlPlazo.setLayout(pnlPlazoLayout);
        pnlPlazoLayout.setHorizontalGroup(
            pnlPlazoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPlazoLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(txtDiasPlazo)
                .addGap(5, 5, 5))
        );
        pnlPlazoLayout.setVerticalGroup(
            pnlPlazoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPlazoLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(txtDiasPlazo, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );

        pnlCascos.setBackground(new java.awt.Color(255, 255, 255));
        pnlCascos.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cascos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 0, 12), new java.awt.Color(0, 0, 0))); // NOI18N

        btnCascos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/casco.png"))); // NOI18N
        btnCascos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCascosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlCascosLayout = new javax.swing.GroupLayout(pnlCascos);
        pnlCascos.setLayout(pnlCascosLayout);
        pnlCascosLayout.setHorizontalGroup(
            pnlCascosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCascosLayout.createSequentialGroup()
                .addComponent(btnCascos, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
        );
        pnlCascosLayout.setVerticalGroup(
            pnlCascosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnCascos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlCascos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlPlazo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(pnlPlazo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pnlCascos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtPlacaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPlacaKeyReleased
        txtPlaca.setText(txtPlaca.getText().toUpperCase());

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            validacionEntrada();
        }
    }//GEN-LAST:event_txtPlacaKeyReleased

    private void txtPlacaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPlacaKeyTyped

    }//GEN-LAST:event_txtPlacaKeyTyped

    private void txtPlacaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPlacaKeyPressed
        txtPlaca.setText(txtPlaca.getText().toUpperCase());
    }//GEN-LAST:event_txtPlacaKeyPressed

    private void btnCascosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCascosActionPerformed
        if (txtPlaca.getText().equals("")) {
            metodos.msgAdvertenciaAjustado(null, "No puede continuar, falta la placa");
            return;
        }

        if (txtPlaca.getText().length() < 5) {
            lbServicio1.requestFocus();
            metodos.msgAdvertencia(null, "Placa no válida, verifiquela e intente de nuevo");
            return;
        }

        Object[][] parq = instancias.getSql().getRegistrosParqueaderoAutos(txtPlaca.getText());
        if (parq.length > 0) {
            if (pnlPago.isVisible() == false) {
                metodos.msgAdvertencia(null, "La misma placa ya se encuentra dentro del parqueadero.");
                return;
            }
        }

        dlgCascos cascos = new dlgCascos(null, true, txtPlaca.getText());
        cascos.setLocationRelativeTo(null);
        cascos.setVisible(true);

        if (instancias.getProcesoCompletoCascos()) {
            validacionEntrada();
        }

        instancias.setProcesoCompletoCascos(false);
    }//GEN-LAST:event_btnCascosActionPerformed

    private void txtDiasPlazoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiasPlazoKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtDiasPlazoKeyTyped

    private void txtTotalPagarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalPagarKeyReleased
        if (txtTotalPagar.getText().equals("") || txtTotalPagar.getText().equals("$") || txtTotalPagar.getText().equals("$ ")) {
            txtTotalPagar.setText("0");
        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtTotalPagar.requestFocus();
        } else {
            txtTotalPagar.setText(big.setMoneda(big.getMoneda(txtTotalPagar.getText())));
        }
    }//GEN-LAST:event_txtTotalPagarKeyReleased

    private void txtDiasPlazoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiasPlazoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiasPlazoActionPerformed

    private void txtNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtPlacaKeyReleased(evt);
        }
    }//GEN-LAST:event_txtNombreKeyReleased

    private void txtDiasPlazoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiasPlazoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtPlacaKeyReleased(evt);
        }
    }//GEN-LAST:event_txtDiasPlazoKeyReleased

    public void validacionEntrada() {
        if (txtPlaca.getText().equals("")) {
            metodos.msgAdvertenciaAjustado(null, "No puede continuar, falta la placa");
            return;
        }

        if (txtPlaca.getText().length() < 5) {
            lbServicio1.requestFocus();
            metodos.msgAdvertencia(null, "Placa no válida, verifiquela e intente de nuevo");
            return;
        }

        Object[][] parq = instancias.getSql().getRegistrosParqueaderoAutos(txtPlaca.getText());

        if (parq.length > 0) {

            if (pnlPago.isVisible() == false) {
                metodos.msgAdvertencia(null, "La misma placa ya se encuentra dentro del parqueadero.");
                return;
            }

            if (!mensualidad) {
                String diasPlazo = txtDiasPlazo.getText();
                Object[][] placa = instancias.getSql().getUsuarioPlaca(txtPlaca.getText());

                ModeloContacto nodo2;
                if (placa.length > 0) {
                    nodo2 = instancias.getSql().getDatosTercero(placa[0][0].toString());
                } else {
                    nodo2 = instancias.getSql().getDatosTercero("1010");
                }

                System.out.println("nodo " + nodo2.getId());
                System.out.println("dias plazo " + diasPlazo);

                if (!diasPlazo.equals("") && nodo2.getId().equals("1010")) {
                    metodos.msgError(null, "No puede realizar ventas a credito a este cliente, porque no esta registrado.");
                    txtDiasPlazo.setText("");
                    return;
                }

                if (metodos.msgPregunta(null, "¿Desea continuar") == 0) {
                    /* OBTENEMOS EL PROXIMO CONSECUTIVO PARA RELACIONARLO AL PARQUEO Y PODER REALIZAR LA CONSULTA */
                    String numFactura = instancias.getFactura().desdeParqueadero(nodo2.getId(),
                            new String[][]{new String[]{codigo1, txtTotalPagar.getText()}}, diasPlazo, txtPlaca.getText());

                    if (!instancias.getSql().finalizarParqueadero(idParqueadero, metodos.fechaConsulta(txtFechaSalida.getText()), txtHoraSalida.getText(),
                            String.valueOf(horasTotales), numFactura)) {
                        metodos.msgError(null, "Error al realizar la salida.");
                        return;
                    }

//                    instancias.getReporte().verTirilla2(instancias.getInformacionEmpresa(), instancias.getLegal(), ID, "Original");
                    instancias.getParqueadero().actualizarTablas();
                    this.dispose();
                }
            } else {
                if (!instancias.getSql().finalizarParqueadero(idParqueadero, txtFechaSalida.getText(), txtHoraSalida.getText(),
                        String.valueOf(horasTotales), "")) {
                    metodos.msgError(null, "No fue posible realizar la salida de este vehiculo.");
                    return;
                } else {
                    metodos.msgExito(null, "Parqueo finalizado con exito.");
                }

                instancias.getParqueadero().actualizarTablas();
                this.dispose();
            }
        } else {
            String codigo = "PRQ-" + instancias.getSql().getNumConsecutivo("PRQ")[0];

            Object[] vector = {codigo, txtPlaca.getText(), codigo1, metodos.fechaConsulta(metodosGenerales.fecha()), metodos.fechaConsulta(metodosGenerales.fecha()),
                metodosGenerales.hora(), "", "", "", "", "", txtNombre.getText(), "PENDIENTE", ""
            };

            ndParqueadero nodo = metodos.llenarParqueaderoAutos(vector);

            if (!instancias.getSql().agregarParqueaderoAutos(nodo)) {
                metodos.msgError(null, "Hubo un error al guardar este registro");
                return;
            } else {
                if (!instancias.getSql().aumentarConsecutivo("PRQ", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("PRQ")[0]) + 1)) {
                    metodos.msgError(null, "Hubo un problema al guardar en el consecutivo");
                }

                metodos.msgExito(null, "Parqueo registrado con exito");
                instancias.getParqueadero().actualizarTablas();
                this.dispose();
            }
            instancias.getReporte().verTirilla1(instancias.getInformacionEmpresa(), txtPlaca.getText(), metodosGenerales.fecha(), metodosGenerales.hora());
        }
    }

    public void cargarParqueadero(String parqueadero) {

        BigDecimal totalCascos = big.getBigDecimal("0");

        pnlPago.setVisible(true);
        lbServicio8.setVisible(false);
        txtNombre.setVisible(false);

        if (datosMaestraParqueadero[0][8].equals("1")) {
            pnlPlazo.setVisible(true);
        }

        btnCascos.setEnabled(false);

        Object[][] parqueos = instancias.getSql().getRegistrosParqueaderoAutos1(parqueadero);
        Object[][] datos2 = instancias.getSql().getMensualidadesActivas(parqueos[0][0].toString(), codigo1);

        if (datosMaestraParqueadero[0][9].toString().equals("1")) {
            Object[][] cascos = instancias.getSql().getCascos(parqueos[0][0].toString());
            lbCascos.setText("CASCOS x" + cascos.length);

            String ubicacionesCascos = "";
            for (int i = 0; i < cascos.length; i++) {
                ubicacionesCascos = ubicacionesCascos + cascos[i][0].toString() + ", ";
            }

            if (!ubicacionesCascos.equals("")) {
                ubicacionesCascos = ubicacionesCascos.substring(0, ubicacionesCascos.length() - 2);
            }

            BigDecimal costoCasco = big.getBigDecimal(datosMaestraParqueadero[0][10]);
            totalCascos = costoCasco.multiply(big.getBigDecimal(cascos.length));
            txtCascos.setText(ubicacionesCascos + " = " + big.setMoneda(totalCascos));
        }

        if (datos2.length > 0) {
            if (metodos.restarFecha(metodosGenerales.fecha(), metodos.fecha(datos2[0][0].toString())) >= 0) {
                mensualidad = true;
            }
        }

        if (parqueos.length > 0) {

            txtPlaca.setText(parqueos[0][0].toString());
            txtFechaEntrada.setText(metodos.fecha(parqueos[0][2].toString()));

            if (parqueos[0][1].toString().split(":")[1].length() == 1) {
                txtHoraEntrada.setText(parqueos[0][1].toString().split(":")[0] + ":0" + parqueos[0][1].toString().split(":")[1]);
            } else {
                txtHoraEntrada.setText(parqueos[0][1].toString());
            }

            txtFechaSalida.setText(metodosGenerales.fecha());

            if (metodosGenerales.hora().split(":")[1].length() == 1) {
                txtHoraSalida.setText(metodosGenerales.hora().split(":")[0] + ":0" + metodosGenerales.hora().split(":")[1]);
            } else {
                txtHoraSalida.setText(metodosGenerales.hora());
            }

            String dif = "";
            try {
                dif = calcularDiferencia(metodos.fecha4(txtFechaEntrada.getText()) + " " + txtHoraEntrada.getText() + ":00",
                        metodos.fecha4(txtFechaSalida.getText()) + " " + txtHoraSalida.getText() + ":00");
            } catch (ParseException ex) {
                Logger.getLogger(dlgParqueo.class.getName()).log(Level.SEVERE, null, ex);
            }

//            horasTotales = horas;
            txtTotalEstadia.setText(dif);

            /* Iniciamos a calcular el total */
            if (!mensualidad) {

                Object[][] datos = instancias.getSql().getDatosConfig(codigo1);

                BigDecimal total = BigDecimal.ZERO;
                BigDecimal valorHora = big.getMoneda(datos[0][2].toString());
                int minutosHabiles = Integer.parseInt(datos[0][3].toString());
                int horasAcumuladas = Integer.parseInt(datos[0][4].toString());
                BigDecimal valorAcumulado = big.getMoneda(datos[0][5].toString());
                int horasDia = Integer.parseInt(datos[0][6].toString());
                BigDecimal valorDia = big.getMoneda(datos[0][7].toString());

                int diasTotales = Integer.parseInt(dif.split(", ")[0].replace(" días", ""));
                int horasTotales = Integer.parseInt(dif.split(", ")[1].replace(" horas", ""));
                int minutosTotales = Integer.parseInt(dif.split(", ")[2].replace(" minutos", ""));

                System.out.println("a. " + diasTotales);
                System.out.println("a. " + horasTotales);
                System.out.println("a. " + minutosTotales);

                if (diasTotales == 0 && horasTotales == 0) {
                    if (minutosHabiles <= 15) {
                        total = big.getBigDecimal("0");
                    } else {
                        total = valorHora;
                    }
                } else {
                    if (minutosTotales > 0) {
                        horasTotales = horasTotales + 1;
                    }

                    if (horasTotales >= horasDia) {
                        diasTotales = diasTotales + 1;
                        horasTotales = 0;
                        total = valorDia.multiply(big.getBigDecimal(diasTotales));
                    } else {
                        if (diasTotales == 0) {
                            int diferencia = horasTotales - horasAcumuladas;
                            if (diferencia > 0) {
                                total = valorHora.multiply(big.getBigDecimal(diferencia)).add(valorAcumulado);
                            } else {
                                total = valorAcumulado;
                            }
                        } else {
                            total = valorDia.multiply(big.getBigDecimal(diasTotales));
                            total = valorHora.multiply(big.getBigDecimal(horasTotales)).add(total);
                        }
                    }
                }

                total = total.add(totalCascos);
                txtTotalPagar.setText(big.setMoneda(total));
            } else {
                txtTotalPagar.setText(big.setMoneda(totalCascos));
                lbVigencia.setVisible(true);
            }
        }
    }

    public String calcularDiferencia(String fechaInicial1, String fechaFinal1) throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd H:m:s");

        Date fechaInicial = dateFormat.parse(fechaInicial1);
        Date fechaFinal = dateFormat.parse(fechaFinal1);

        int diferencia = (int) ((fechaFinal.getTime() - fechaInicial.getTime()) / 1000);

        int dias = 0;
        int horas = 0;
        int minutos = 0;
        if (diferencia > 86400) {
            dias = (int) Math.floor(diferencia / 86400);
            diferencia = diferencia - (dias * 86400);
        }
        if (diferencia > 3600) {
            horas = (int) Math.floor(diferencia / 3600);
            diferencia = diferencia - (horas * 3600);
        }
        if (diferencia > 60) {
            minutos = (int) Math.floor(diferencia / 60);
            diferencia = diferencia - (minutos * 60);
        }

        return dias + " días, " + horas + " horas, " + minutos + " minutos";
    }

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(dlgParqueo.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(dlgParqueo.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(dlgParqueo.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dlgParqueo.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                dlgParqueo dialog = new dlgParqueo(new javax.swing.JFrame(), true, null, null, null);
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
    private javax.swing.JButton btnCascos;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JLabel lbCascos;
    private javax.swing.JLabel lbNit13;
    private javax.swing.JLabel lbServicio1;
    private javax.swing.JLabel lbServicio3;
    private javax.swing.JLabel lbServicio4;
    private javax.swing.JLabel lbServicio5;
    private javax.swing.JLabel lbServicio6;
    private javax.swing.JLabel lbServicio7;
    private javax.swing.JLabel lbServicio8;
    private javax.swing.JLabel lbVigencia;
    private javax.swing.JPanel pnlCascos;
    private javax.swing.JPanel pnlPago;
    private javax.swing.JPanel pnlPlazo;
    private javax.swing.JTextField txtCascos;
    private javax.swing.JTextField txtDiasPlazo;
    private javax.swing.JTextField txtFechaEntrada;
    private javax.swing.JTextField txtFechaSalida;
    private javax.swing.JTextField txtHoraEntrada;
    private javax.swing.JTextField txtHoraSalida;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPlaca;
    private javax.swing.JTextField txtTotalEstadia;
    private javax.swing.JTextField txtTotalPagar;
    // End of variables declaration//GEN-END:variables
}
