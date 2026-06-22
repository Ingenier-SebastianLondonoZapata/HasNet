package formularios.Cartera;

import dao.InicioSesion.DaoInicioSesion;
import Modelo.InicioSesion.Terminal;
import formularios.terceros.buscClientes;
import clases.Instancias;
import Modelo.Terceros.ModeloContacto;
import clases.metodosGenerales;
import formularios.Parqueadero.buscPlacas;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

public class infRepCartera extends javax.swing.JInternalFrame {

    private final DaoInicioSesion daoInicioSesion = new DaoInicioSesion();
    metodosGenerales metodos = new metodosGenerales();
    private Instancias instancias;
    private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension dimBarra = null;

    public infRepCartera() {

        initComponents();
        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
        dimBarra = Barra.getPreferredSize();
        Barra.setSize(0, 0);
        Barra.setPreferredSize(new Dimension(0, 0));
        setBorder(null);
        repaint();

        instancias = Instancias.getInstancias();

        List<Terminal> terminales = daoInicioSesion.obtenerTerminales();
        for (Terminal terminal : terminales) {
            cmbTerminal.addItem(terminal.getTerminal());
        }

        btnCuotas.setVisible(false);

        if (instancias.getConfiguraciones().isFacturacionLote()) {
            rdCuentasCobro.setVisible(true);
        } else {
            rdCuentasCobro.setVisible(false);
        }

        if (instancias.getConfiguraciones().isParqueadero()) {
            lbPlaca.setVisible(true);
            txtPlaca.setVisible(true);
            btnAggPlaca.setVisible(true);
            btnRemPlaca.setVisible(true);
            cmbPlaca.setVisible(true);
        } else {
            lbPlaca.setVisible(false);
            txtPlaca.setVisible(false);
            btnAggPlaca.setVisible(false);
            btnRemPlaca.setVisible(false);
            cmbPlaca.setVisible(false);
        }

        if (!instancias.getUsuario().equals("ADMIN")) {
            btnAggTerminal.setVisible(false);
            btnRemTerminal.setVisible(false);
            lbTerminal.setVisible(false);
            cmbTerminal.setVisible(false);
            cmbTerminal2.setVisible(false);
        }

        /*        String[] aux = instancias.getMaestra().vendedores();
         llenarDatos(aux[0],aux[1],aux[2],aux[3],aux[4]);*/
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        scrFormulario = new javax.swing.JScrollPane();
        pnlFormulario = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        lbNit9 = new javax.swing.JLabel();
        dtInicio = new datechooser.beans.DateChooserCombo();
        dtFinal = new datechooser.beans.DateChooserCombo();
        rdFecha = new javax.swing.JRadioButton();
        lbNit12 = new javax.swing.JLabel();
        btnImpresa = new javax.swing.JRadioButton();
        btnExcel = new javax.swing.JRadioButton();
        jPanel1 = new javax.swing.JPanel();
        btnCuotas = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        rdCartera = new javax.swing.JRadioButton();
        rdPlanSepares = new javax.swing.JRadioButton();
        rdDomicilios = new javax.swing.JRadioButton();
        rdCuentasCobro = new javax.swing.JRadioButton();
        jPanel8 = new javax.swing.JPanel();
        lbNit1 = new javax.swing.JLabel();
        txtCliente = new javax.swing.JTextField();
        lbNit4 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        lbTerminal = new javax.swing.JLabel();
        cmbTerminal = new javax.swing.JComboBox();
        lbPlaca = new javax.swing.JLabel();
        txtPlaca = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        btnAggCliente = new javax.swing.JButton();
        btnRemCliente = new javax.swing.JButton();
        btnAggCliente1 = new javax.swing.JButton();
        btnRemCliente1 = new javax.swing.JButton();
        btnAggTerminal = new javax.swing.JButton();
        btnRemTerminal = new javax.swing.JButton();
        btnAggPlaca = new javax.swing.JButton();
        btnRemPlaca = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        cmbCientes = new javax.swing.JComboBox();
        cmbVendedor1 = new javax.swing.JComboBox();
        cmbTerminal2 = new javax.swing.JComboBox();
        cmbPlaca = new javax.swing.JComboBox();

        setTitle("Factura");

        pnlFormulario.setBackground(new java.awt.Color(255, 255, 255));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        lbNit9.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lbNit9.setText("Fecha final");

        dtInicio.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 12));

        dtFinal.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 12));

        rdFecha.setBackground(new java.awt.Color(255, 255, 255));
        rdFecha.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        rdFecha.setText("Fecha inicial");

        lbNit12.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lbNit12.setText("Versión:");

        btnImpresa.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(btnImpresa);
        btnImpresa.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnImpresa.setSelected(true);
        btnImpresa.setText("En pantalla");
        btnImpresa.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnImpresa.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        btnExcel.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(btnExcel);
        btnExcel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnExcel.setText("Hoja de calculo");
        btnExcel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnExcel.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rdFecha)
                    .addComponent(lbNit12))
                .addGap(11, 11, 11)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnImpresa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dtInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(35, 35, 35)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(lbNit9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dtFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnExcel))
                .addContainerGap(166, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(dtInicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnImpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbNit9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(rdFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dtFinal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(lbNit12))
                            .addComponent(btnExcel))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        btnCuotas.setBackground(new java.awt.Color(46, 204, 113));
        btnCuotas.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        btnCuotas.setText("Generar por Cuotas");
        btnCuotas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCuotas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCuotasActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(46, 204, 113));
        jButton6.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jButton6.setText("Generar");
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCuotas)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCuotas, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE))
                .addGap(15, 15, 15))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        rdCartera.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup2.add(rdCartera);
        rdCartera.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        rdCartera.setSelected(true);
        rdCartera.setText("CARTERA");

        rdPlanSepares.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup2.add(rdPlanSepares);
        rdPlanSepares.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        rdPlanSepares.setText("PLAN SEPARES");

        rdDomicilios.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup2.add(rdDomicilios);
        rdDomicilios.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        rdDomicilios.setText("DOMICILIOS");

        rdCuentasCobro.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup2.add(rdCuentasCobro);
        rdCuentasCobro.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        rdCuentasCobro.setText("CUENTAS DE COBRO");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(rdCartera)
                .addGap(5, 5, 5)
                .addComponent(rdPlanSepares)
                .addGap(5, 5, 5)
                .addComponent(rdDomicilios)
                .addGap(10, 10, 10)
                .addComponent(rdCuentasCobro, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdCartera)
                    .addComponent(rdPlanSepares)
                    .addComponent(rdDomicilios)
                    .addComponent(rdCuentasCobro))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        lbNit1.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit1.setText("Cliente");

        txtCliente.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtClienteKeyReleased(evt);
            }
        });

        lbNit4.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit4.setText("Vendedor:");

        lbTerminal.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbTerminal.setText("Terminal");

        cmbTerminal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        lbPlaca.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbPlaca.setText("Placa");

        txtPlaca.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtPlaca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPlacaKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbNit1, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
                    .addComponent(txtCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
                    .addComponent(lbNit4, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
                    .addComponent(jComboBox1, 0, 236, Short.MAX_VALUE)
                    .addComponent(lbTerminal, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
                    .addComponent(cmbTerminal, 0, 236, Short.MAX_VALUE)
                    .addComponent(lbPlaca, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
                    .addComponent(txtPlaca, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(lbNit1)
                .addGap(0, 0, 0)
                .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbNit4)
                .addGap(0, 0, 0)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(lbTerminal)
                .addGap(0, 0, 0)
                .addComponent(cmbTerminal, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(lbPlaca)
                .addGap(0, 0, 0)
                .addComponent(txtPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        btnAggCliente.setBackground(new java.awt.Color(204, 204, 204));
        btnAggCliente.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnAggCliente.setForeground(new java.awt.Color(255, 255, 255));
        btnAggCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/agregar.png"))); // NOI18N
        btnAggCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAggCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAggClienteActionPerformed(evt);
            }
        });

        btnRemCliente.setBackground(new java.awt.Color(204, 204, 204));
        btnRemCliente.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnRemCliente.setForeground(new java.awt.Color(153, 0, 0));
        btnRemCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/borrar2.png"))); // NOI18N
        btnRemCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRemCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemClienteActionPerformed(evt);
            }
        });

        btnAggCliente1.setBackground(new java.awt.Color(204, 204, 204));
        btnAggCliente1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnAggCliente1.setForeground(new java.awt.Color(255, 255, 255));
        btnAggCliente1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/agregar.png"))); // NOI18N
        btnAggCliente1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAggCliente1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAggCliente1ActionPerformed(evt);
            }
        });

        btnRemCliente1.setBackground(new java.awt.Color(204, 204, 204));
        btnRemCliente1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnRemCliente1.setForeground(new java.awt.Color(153, 0, 0));
        btnRemCliente1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/borrar2.png"))); // NOI18N
        btnRemCliente1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRemCliente1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemCliente1ActionPerformed(evt);
            }
        });

        btnAggTerminal.setBackground(new java.awt.Color(204, 204, 204));
        btnAggTerminal.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnAggTerminal.setForeground(new java.awt.Color(255, 255, 255));
        btnAggTerminal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/agregar.png"))); // NOI18N
        btnAggTerminal.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAggTerminal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAggTerminalActionPerformed(evt);
            }
        });

        btnRemTerminal.setBackground(new java.awt.Color(204, 204, 204));
        btnRemTerminal.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnRemTerminal.setForeground(new java.awt.Color(153, 0, 0));
        btnRemTerminal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/borrar2.png"))); // NOI18N
        btnRemTerminal.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRemTerminal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemTerminalActionPerformed(evt);
            }
        });

        btnAggPlaca.setBackground(new java.awt.Color(204, 204, 204));
        btnAggPlaca.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnAggPlaca.setForeground(new java.awt.Color(255, 255, 255));
        btnAggPlaca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/agregar.png"))); // NOI18N
        btnAggPlaca.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAggPlaca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAggPlacaActionPerformed(evt);
            }
        });

        btnRemPlaca.setBackground(new java.awt.Color(204, 204, 204));
        btnRemPlaca.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnRemPlaca.setForeground(new java.awt.Color(153, 0, 0));
        btnRemPlaca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/borrar2.png"))); // NOI18N
        btnRemPlaca.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRemPlaca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemPlacaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(btnAggCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRemCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(btnAggCliente1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRemCliente1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(btnAggTerminal, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRemTerminal, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(11, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAggPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRemPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAggCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRemCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(36, 36, 36)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAggCliente1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRemCliente1))
                .addGap(50, 50, 50)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAggTerminal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRemTerminal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAggPlaca, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRemPlaca))
                .addGap(19, 19, 19))
        );

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

        cmbCientes.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbCientes, 0, 214, Short.MAX_VALUE)
                    .addComponent(cmbVendedor1, 0, 214, Short.MAX_VALUE)
                    .addComponent(cmbTerminal2, 0, 214, Short.MAX_VALUE)
                    .addComponent(cmbPlaca, 0, 214, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(cmbCientes, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(cmbVendedor1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(cmbTerminal2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cmbPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout pnlFormularioLayout = new javax.swing.GroupLayout(pnlFormulario);
        pnlFormulario.setLayout(pnlFormularioLayout);
        pnlFormularioLayout.setHorizontalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addContainerGap(36, Short.MAX_VALUE)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlFormularioLayout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        pnlFormularioLayout.setVerticalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        scrFormulario.setViewportView(pnlFormulario);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(scrFormulario)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(scrFormulario)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

//    public void llenarDatos(String Vendedores) {
//        jComboBox1.removeAllItems();
//        String[] vendedor = Vendedores.split(";");
//        for (int i = 0; i < vendedor.length; i++) {
//            jComboBox1.addItem(vendedor[i]);
//        }
//    }
    public void llenarDatos(String[] vendedor) {
        jComboBox1.removeAllItems();
        for (int i = 0; i < vendedor.length; i++) {
            jComboBox1.addItem(vendedor[i]);
        }
    }

    private void txtClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClienteKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cargarCliente(txtCliente.getText());
        }
    }//GEN-LAST:event_txtClienteKeyReleased

    private void txtPlacaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPlacaKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txtPlaca.getText().equals("")) {
                ventanaPlacas("", "");
            } else {
                btnAggPlacaActionPerformed(null);
            }
        }
    }//GEN-LAST:event_txtPlacaKeyReleased

    private void btnAggClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAggClienteActionPerformed
        if (txtCliente.getText().equals("")) {
            metodos.msgError(null, "Cargue primero un cliente");
        } else {
            if (yaEsta(cmbCientes, txtCliente.getText())) {
                metodos.msgAdvertencia(null, "Ya cargo este cliente");
            } else {
                cmbCientes.addItem(txtCliente.getText());
                cmbCientes.setSelectedItem(txtCliente.getText());
            }
            txtCliente.setText("");
        }
    }//GEN-LAST:event_btnAggClienteActionPerformed

    private void btnRemClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemClienteActionPerformed
        cmbCientes.removeItem(cmbCientes.getSelectedItem());
    }//GEN-LAST:event_btnRemClienteActionPerformed

    private void btnAggCliente1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAggCliente1ActionPerformed
        if (!jComboBox1.getSelectedItem().equals("")) {
            for (int i = 0; i < cmbVendedor1.getItemCount(); i++) {
                if (cmbVendedor1.getItemAt(i).equals(jComboBox1.getSelectedItem())) {
                    JOptionPane.showMessageDialog(null, "Este vendedor ya ha sido agregado");
                    return;
                }
            }
            cmbVendedor1.addItem(jComboBox1.getSelectedItem());
        }
    }//GEN-LAST:event_btnAggCliente1ActionPerformed

    private void btnRemCliente1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemCliente1ActionPerformed
        cmbVendedor1.removeItem(cmbVendedor1.getSelectedItem());
    }//GEN-LAST:event_btnRemCliente1ActionPerformed

    private void btnAggTerminalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAggTerminalActionPerformed
        if (!jComboBox1.getSelectedItem().equals("")) {
            for (int i = 0; i < cmbTerminal2.getItemCount(); i++) {
                if (cmbTerminal2.getItemAt(i).equals(cmbTerminal.getSelectedItem())) {
                    JOptionPane.showMessageDialog(null, "Esta terminal ya ha sido agregada");
                    return;
                }
            }
            cmbTerminal2.addItem(cmbTerminal.getSelectedItem());
        }
    }//GEN-LAST:event_btnAggTerminalActionPerformed

    private void btnRemTerminalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemTerminalActionPerformed
        if (cmbTerminal2.getItemCount() == 0) {
            metodos.msgError(this, "No hay ninguna terminal seleccioanda");
            return;
        }
        cmbTerminal2.removeItem(cmbTerminal2.getSelectedItem());
    }//GEN-LAST:event_btnRemTerminalActionPerformed

    private void btnAggPlacaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAggPlacaActionPerformed
        if (txtPlaca.getText().equals("")) {
            metodos.msgError(null, "Cargue primero una placa");
        } else {

            if (yaEsta(cmbPlaca, txtPlaca.getText())) {
                metodos.msgAdvertencia(null, "Ya cargo esta placa");
            } else {
                cmbPlaca.addItem(txtPlaca.getText());
                cmbPlaca.setSelectedItem(txtPlaca.getText());
            }
            txtPlaca.setText("");
        }
    }//GEN-LAST:event_btnAggPlacaActionPerformed

    private void btnRemPlacaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemPlacaActionPerformed
        cmbPlaca.removeItem(cmbPlaca.getSelectedItem());
    }//GEN-LAST:event_btnRemPlacaActionPerformed

    private void btnCuotasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCuotasActionPerformed
        if (metodos.msgPregunta(null, "¿Esta seguro?") == 0) {
//            String sql = "where";
            String sql = "";

            if (cmbCientes.getItemCount() > 0) {
                sql = sql + "where (";

                for (int i = 0; i < cmbCientes.getItemCount(); i++) {
                    sql = sql + " cliente='" + cmbCientes.getItemAt(i) + "' OR";
                }

                sql = sql.substring(0, sql.length() - 2) + ") AND";
//                sql = sql.substring(0, sql.length() - 2) + ") ";
            }

            String ini = metodos.desdeDate(dtInicio.getCurrent()), fin = metodos.desdeDate(dtFinal.getCurrent());

//            sql = sql + " ((fecha)<=#" + fin + "#)";
            if (sql.equals("")) {
                sql = sql + "where";
            }
            sql = sql + " (((fechaPrestamo)>=#" + ini + "#) And ((fechaPrestamo)<=#" + fin + "#))";

            System.out.println(sql);

//             sql = sql + " ((fechaPrestamo)>=#" + "01/04/2014" + "# And (fechaPrestamo)<=#" + "4/5/2014" + "#)";
            dtInicio.setFormat(0);
            dtFinal.setFormat(0);

            dtInicio.setFormat(2);
            dtFinal.setFormat(2);

            String tipo = "";

            if (btnExcel.isSelected()) {
                tipo = "xls";
                instancias.setRutaAguardar(metodos.obtenerRuta(this, "Reporte cartera por cuotas " + metodosGenerales.dia() + " de " + metodosGenerales.mesEnPalabra() + " del " + metodosGenerales.anho()));
            }

//            instancias.getReporte().ver_RepcarteraCuotas(sql, tipo);
            instancias.getReporte().ver_RepCarteraCuotas(sql, tipo);

        }
    }//GEN-LAST:event_btnCuotasActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        if (metodos.msgPregunta(null, "¿Esta seguro?") == 0) {
            String sql = "where";

            if (rdDomicilios.isSelected()) {
                sql = sql + " conseMesa <> 'SIN-CONSECUTIVO' AND";
            } else if (rdCartera.isSelected()) {
                sql = sql + " conseMesa = 'SIN-CONSECUTIVO' AND";
            }

            if (instancias.getUsuario().equals("ADMIN")) {
                if (cmbTerminal2.getItemCount() > 0) {
                    sql = sql + " (";

                    for (int i = 0; i < cmbTerminal2.getItemCount(); i++) {
                        sql = sql + " terminal ='" + cmbTerminal2.getItemAt(i) + "' OR";
                    }
                    sql = sql.substring(0, sql.length() - 2) + ") AND";
                }
            } else {
                if (!instancias.isVisualizarTodasLasFacturas()) {
                    sql = sql + " terminal ='" + instancias.getTerminal() + "' AND";
                }
            }

            if (cmbCientes.getItemCount() > 0) {
                sql = sql + " (";
                for (int i = 0; i < cmbCientes.getItemCount(); i++) {
                    sql = sql + " cliente ='" + cmbCientes.getItemAt(i) + "' OR";
                }
                sql = sql.substring(0, sql.length() - 2) + ") AND";
            }

            if (cmbVendedor1.getItemCount() > 0) {
                sql = sql + " (";

                for (int i = 0; i < cmbVendedor1.getItemCount(); i++) {
                    sql = sql + " vendedor ='" + cmbVendedor1.getItemAt(i) + "' OR";
                }

                sql = sql.substring(0, sql.length() - 2) + ") AND";
            }

            if (cmbPlaca.getItemCount() > 0) {
                sql = sql + " (";
                for (int i = 0; i < cmbPlaca.getItemCount(); i++) {
                    sql = sql + " placa ='" + cmbPlaca.getItemAt(i) + "' OR";
                }
                sql = sql.substring(0, sql.length() - 2) + ") AND";
            }

            if (rdFecha.isSelected()) {
                String ini = metodos.desdeDate(dtInicio.getCurrent()), fin = metodos.desdeDate(dtFinal.getCurrent());
                fin = metodos.sumarFecha(metodos.fecha(fin), 1);
                fin = metodos.fechaConsulta(fin);
                sql = sql + " (((fechaFactura)>='" + ini + "') And ((fechaFactura)<='" + fin + "'))    ";
            }

            System.out.println(sql);
            if (sql.equals("where")) {
                sql = "";
            } else {
                sql = sql.substring(0, sql.length() - 4);
            }

            System.out.println(sql);

            String tipo = "";
            if (btnExcel.isSelected()) {
                tipo = "xls";
                instancias.setRutaAguardar(metodos.obtenerRuta(this, "Reporte cartera " + metodosGenerales.dia() + " de " + metodosGenerales.mesEnPalabra() + " del " + metodosGenerales.anho()));
            }

            String tipoReporte = "";
            if (rdCartera.isSelected()) {
                tipoReporte = "repCartera";
            } else if (rdPlanSepares.isSelected()) {
                tipoReporte = "repCarteraSepares";
            } else if (rdDomicilios.isSelected()) {
                tipoReporte = "repCarteraDomicilios";
            } else if (rdCuentasCobro.isSelected()) {
                tipoReporte = "repCarteraCuentaCobro";
            }

            instancias.getReporte().ver_Repcartera(sql, tipo, tipoReporte);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    public void cargarCliente(String nit) {
        ModeloContacto nodo = instancias.getSql().getDatosTercero(nit);
        if (nodo.getId() != null) {
            txtCliente.setText(nodo.getIdSistema());
            btnAggClienteActionPerformed(null);
            return;
        }
        ventanaTerceros(nit);
    }

    public void ventanaTerceros(String nit) {
        buscClientes buscar = new buscClientes(instancias.getMenu(), rootPaneCheckingEnabled, false, null, "");
        buscar.setOpc("factura");
        buscar.setLocationRelativeTo(null);
        instancias.setBusClientes(buscar);
        instancias.setCampoActual(txtCliente);
        txtCliente.requestFocus();
        buscar.noEncontrado(nit);
        buscar.show();
    }

    public void ventanaPlacas(String nit, String condi) {
        buscPlacas buscar = new buscPlacas(instancias.getMenu(), rootPaneCheckingEnabled, condi);
        buscar.setLocationRelativeTo(null);
        buscar.setInstancia(instancias);
        instancias.setBuscPlacas(buscar);
        instancias.setCampoActual(txtPlaca);
        txtPlaca.requestFocus();
        buscar.noEncontrado(nit);
        buscar.show();
    }

    public boolean yaEsta(JComboBox combo, String dato) {
        boolean sw = false;
        for (int i = 0; i < combo.getItemCount(); i++) {
            if (((String) combo.getItemAt(i)).equals(dato)) {
                return true;
            }
        }
        return sw;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAggCliente;
    private javax.swing.JButton btnAggCliente1;
    private javax.swing.JButton btnAggPlaca;
    private javax.swing.JButton btnAggTerminal;
    private javax.swing.JButton btnCuotas;
    private javax.swing.JRadioButton btnExcel;
    private javax.swing.JRadioButton btnImpresa;
    private javax.swing.JButton btnRemCliente;
    private javax.swing.JButton btnRemCliente1;
    private javax.swing.JButton btnRemPlaca;
    private javax.swing.JButton btnRemTerminal;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox cmbCientes;
    private javax.swing.JComboBox cmbPlaca;
    private javax.swing.JComboBox cmbTerminal;
    private javax.swing.JComboBox cmbTerminal2;
    private javax.swing.JComboBox cmbVendedor1;
    private datechooser.beans.DateChooserCombo dtFinal;
    private datechooser.beans.DateChooserCombo dtInicio;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JLabel lbNit1;
    private javax.swing.JLabel lbNit12;
    private javax.swing.JLabel lbNit4;
    private javax.swing.JLabel lbNit9;
    private javax.swing.JLabel lbPlaca;
    private javax.swing.JLabel lbTerminal;
    private javax.swing.JPanel pnlFormulario;
    private javax.swing.JRadioButton rdCartera;
    private javax.swing.JRadioButton rdCuentasCobro;
    private javax.swing.JRadioButton rdDomicilios;
    private javax.swing.JRadioButton rdFecha;
    private javax.swing.JRadioButton rdPlanSepares;
    private javax.swing.JScrollPane scrFormulario;
    private javax.swing.JTextField txtCliente;
    private javax.swing.JTextField txtPlaca;
    // End of variables declaration//GEN-END:variables
}
