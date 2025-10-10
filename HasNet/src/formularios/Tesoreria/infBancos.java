package formularios.Tesoreria;

import clases.IconCellRenderer;
import clases.Instancias;
import clases.big;
import clases.convertirNumeroALetras;
import clases.metodosGenerales;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.TreeMap;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class infBancos extends javax.swing.JInternalFrame {

    metodosGenerales metodos = new metodosGenerales();
    private Instancias instancias;
    private Boolean mensaje = true;
    private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension dimBarra = null;
    private TreeMap<String, String[]> codigos;
    convertirNumeroALetras convertirNumeroALetras = new convertirNumeroALetras();
    String ingresoAsociado = "", simbolo = "";
    Boolean desdoLavadero = false;

    public String getIngresoAsociado() {
        return ingresoAsociado;
    }

    public void setIngresoAsociado(String ingresoAsociado) {
        this.ingresoAsociado = ingresoAsociado;
    }

    public Boolean getDesdoLavadero() {
        return desdoLavadero;
    }

    public void setDesdoLavadero(Boolean desdoLavadero) {
        this.desdoLavadero = desdoLavadero;
    }

    public infBancos() {
        initComponents();
        codigos = new TreeMap();

//        tblEntrada.getColumnModel().getColumn(4).setCellRenderer(new WordWrapCellRenderer());
//        tblSalidas.getColumnModel().getColumn(4).setCellRenderer(new WordWrapCellRenderer());
        tblEntrada.setDefaultRenderer(Object.class, new IconCellRenderer());
        tblSalidas.setDefaultRenderer(Object.class, new IconCellRenderer());

        instancias = Instancias.getInstancias();

        simbolo = instancias.getSimbolo();
        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
        dimBarra = Barra.getPreferredSize();
        Barra.setSize(0, 0);
        Barra.setPreferredSize(new Dimension(0, 0));
        setBorder(null);
        repaint();

        llenarBancos();

        pnlFormulario.registerKeyboardAction(accion("guardar"), "guardar", KeyStroke.getKeyStroke(KeyEvent.VK_G, Event.CTRL_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
        pnlFormulario.registerKeyboardAction(accion("limpiar"), "limpiar", KeyStroke.getKeyStroke(KeyEvent.VK_L, Event.CTRL_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
        pnlFormulario.registerKeyboardAction(accion("proveedor"), "proveedor", KeyStroke.getKeyStroke(KeyEvent.VK_P, Event.CTRL_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
        pnlFormulario.registerKeyboardAction(accion("terceros"), "terceros", KeyStroke.getKeyStroke(KeyEvent.VK_T, Event.CTRL_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
    }

    static class WordWrapCellRenderer extends JTextArea implements TableCellRenderer {

        WordWrapCellRenderer() {
            setLineWrap(true);
            setWrapStyleWord(true);
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText(value.toString());
            setSize(table.getColumnModel().getColumn(column).getWidth(), 23);

            if (table.getRowHeight(row) != getPreferredSize().height) {
                table.setRowHeight(row, getPreferredSize().height);
            }

            if (isSelected) {
                setForeground(new Color(0, 0, 0));
                setBackground(table.getSelectionBackground());
                Font font = new Font("Century Gothic", Font.PLAIN, 14);
                setFont(font);
            } else {
                setForeground(new Color(0, 0, 0));
                setBackground(table.getBackground());
                Font font = new Font("Century Gothic", Font.PLAIN, 14);
                setFont(font);
            }
            return this;
        }
    }

    private ActionListener accion(final String opc) {
        ActionListener a = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (opc) {
                    case "limpiar":
//                        if ((btnLimpiar.isEnabled()) && (btnLimpiar.isVisible())) {
//                            btnLimpiarActionPerformed(null);
//                        }
                        break;
                    case "guardar":
                        if ((btnGuardar.isEnabled()) && (btnGuardar.isVisible())) {
                            btnGuardarActionPerformed(null);
                        }
                        break;
//                    case "proveedor":
//                        if ((btnBuscTerceros1.isEnabled()) && (btnBuscTerceros1.isVisible())) {
//                            btnBuscTerceros1ActionPerformed(null);
//                        }
//                        break;
                    case "terceros":
//                        if ((btnBuscTerceros.isEnabled()) && (btnBuscTerceros.isVisible())) {
//                            btnBuscTercerosActionPerformed(null);
//                        }
                        break;
                }
            }
        };
        return a;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrFormulario = new javax.swing.JScrollPane();
        pnlFormulario = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        txtSaldoInicial = new javax.swing.JTextField();
        lbNit3 = new javax.swing.JLabel();
        cmbTipoBanco = new javax.swing.JComboBox();
        lbNit = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        lbNit2 = new javax.swing.JLabel();
        lbNit5 = new javax.swing.JLabel();
        txtNumero = new javax.swing.JTextField();
        lbNit4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        btnGuardar = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        lbNit1 = new javax.swing.JLabel();
        cmbBancos = new javax.swing.JComboBox();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblSalidas = new javax.swing.JTable();
        btnGuardar5 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEntrada = new javax.swing.JTable();
        btnGuardar1 = new javax.swing.JButton();
        lbMonto = new javax.swing.JLabel();
        txtDineroTotal = new javax.swing.JLabel();

        setTitle("Factura");

        pnlFormulario.setBackground(new java.awt.Color(255, 255, 255));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        txtSaldoInicial.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        txtSaldoInicial.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSaldoInicial.setText("0");
        txtSaldoInicial.setName("Nombre del banco"); // NOI18N
        txtSaldoInicial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSaldoInicialKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSaldoInicialKeyTyped(evt);
            }
        });

        lbNit3.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbNit3.setText("Tipo de banco:");
        lbNit3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lbNit3KeyReleased(evt);
            }
        });

        cmbTipoBanco.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        cmbTipoBanco.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione tipo...", "Cuenta de ahorros", "Cuenta corriente" }));

        lbNit.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbNit.setText("Nombre del banco: ");
        lbNit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lbNitKeyReleased(evt);
            }
        });

        txtNombre.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        txtNombre.setName("Nombre del banco"); // NOI18N
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });

        lbNit2.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbNit2.setText("No. Cuenta:     ");
        lbNit2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lbNit2KeyReleased(evt);
            }
        });

        lbNit5.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbNit5.setText("Saldo inicial:");
        lbNit5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lbNit5KeyReleased(evt);
            }
        });

        txtNumero.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        txtNumero.setName("CC/NIT"); // NOI18N
        txtNumero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNumeroKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNumeroKeyTyped(evt);
            }
        });

        lbNit4.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbNit4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit4.setText("Descripción");
        lbNit4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lbNit4KeyReleased(evt);
            }
        });

        txtDescripcion.setColumns(20);
        txtDescripcion.setRows(2);
        jScrollPane3.setViewportView(txtDescripcion);

        btnGuardar.setBackground(new java.awt.Color(46, 204, 113));
        btnGuardar.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
        btnGuardar.setText(" GUARDAR");
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnGuardar.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbNit3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbNit))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNombre)
                            .addComponent(cmbTipoBanco, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbNit2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbNit5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSaldoInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jScrollPane3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGuardar))
                    .addComponent(lbNit4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNit)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNit2)
                    .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmbTipoBanco)
                    .addComponent(lbNit3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbNit5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtSaldoInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lbNit4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        lbNit1.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbNit1.setText("BANCOS REGISTRADOS:");
        lbNit1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lbNit1KeyReleased(evt);
            }
        });

        cmbBancos.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        cmbBancos.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbBancosItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lbNit1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbBancos, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jSeparator1))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbNit1, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(cmbBancos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Salidas"));

        tblSalidas.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        tblSalidas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fecha", "Saldo Inicial", "Salida", "Saldo Final", "Nota", "Tipo", "Recibo", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSalidas.setRowHeight(25);
        tblSalidas.getTableHeader().setReorderingAllowed(false);
        tblSalidas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSalidasMouseClicked(evt);
            }
        });
        tblSalidas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblSalidasKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tblSalidasKeyTyped(evt);
            }
        });
        jScrollPane6.setViewportView(tblSalidas);
        if (tblSalidas.getColumnModel().getColumnCount() > 0) {
            tblSalidas.getColumnModel().getColumn(0).setMinWidth(90);
            tblSalidas.getColumnModel().getColumn(0).setPreferredWidth(90);
            tblSalidas.getColumnModel().getColumn(0).setMaxWidth(90);
            tblSalidas.getColumnModel().getColumn(1).setMinWidth(95);
            tblSalidas.getColumnModel().getColumn(1).setPreferredWidth(95);
            tblSalidas.getColumnModel().getColumn(1).setMaxWidth(95);
            tblSalidas.getColumnModel().getColumn(2).setMinWidth(95);
            tblSalidas.getColumnModel().getColumn(2).setPreferredWidth(95);
            tblSalidas.getColumnModel().getColumn(2).setMaxWidth(95);
            tblSalidas.getColumnModel().getColumn(3).setMinWidth(95);
            tblSalidas.getColumnModel().getColumn(3).setPreferredWidth(95);
            tblSalidas.getColumnModel().getColumn(3).setMaxWidth(95);
            tblSalidas.getColumnModel().getColumn(5).setMinWidth(0);
            tblSalidas.getColumnModel().getColumn(5).setPreferredWidth(0);
            tblSalidas.getColumnModel().getColumn(5).setMaxWidth(0);
            tblSalidas.getColumnModel().getColumn(6).setMinWidth(0);
            tblSalidas.getColumnModel().getColumn(6).setPreferredWidth(0);
            tblSalidas.getColumnModel().getColumn(6).setMaxWidth(0);
            tblSalidas.getColumnModel().getColumn(7).setMinWidth(30);
            tblSalidas.getColumnModel().getColumn(7).setPreferredWidth(30);
            tblSalidas.getColumnModel().getColumn(7).setMaxWidth(30);
        }

        btnGuardar5.setBackground(new java.awt.Color(241, 148, 138));
        btnGuardar5.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnGuardar5.setText("SALIDA DE DINERO");
        btnGuardar5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar5.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnGuardar5.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnGuardar5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardar5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGuardar5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(17, 17, 17))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardar5)
                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Entradas"));

        tblEntrada.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        tblEntrada.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fecha", "Saldo Inicial", "Entrada", "Saldo Final", "Nota", "Tipo", "Recibo", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblEntrada.setRowHeight(25);
        tblEntrada.getTableHeader().setReorderingAllowed(false);
        tblEntrada.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEntradaMouseClicked(evt);
            }
        });
        tblEntrada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblEntradaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tblEntradaKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(tblEntrada);
        if (tblEntrada.getColumnModel().getColumnCount() > 0) {
            tblEntrada.getColumnModel().getColumn(0).setMinWidth(90);
            tblEntrada.getColumnModel().getColumn(0).setPreferredWidth(90);
            tblEntrada.getColumnModel().getColumn(0).setMaxWidth(90);
            tblEntrada.getColumnModel().getColumn(1).setMinWidth(95);
            tblEntrada.getColumnModel().getColumn(1).setPreferredWidth(95);
            tblEntrada.getColumnModel().getColumn(1).setMaxWidth(95);
            tblEntrada.getColumnModel().getColumn(2).setMinWidth(95);
            tblEntrada.getColumnModel().getColumn(2).setPreferredWidth(95);
            tblEntrada.getColumnModel().getColumn(2).setMaxWidth(95);
            tblEntrada.getColumnModel().getColumn(3).setMinWidth(95);
            tblEntrada.getColumnModel().getColumn(3).setPreferredWidth(95);
            tblEntrada.getColumnModel().getColumn(3).setMaxWidth(95);
            tblEntrada.getColumnModel().getColumn(5).setMinWidth(0);
            tblEntrada.getColumnModel().getColumn(5).setPreferredWidth(0);
            tblEntrada.getColumnModel().getColumn(5).setMaxWidth(0);
            tblEntrada.getColumnModel().getColumn(6).setMinWidth(0);
            tblEntrada.getColumnModel().getColumn(6).setPreferredWidth(0);
            tblEntrada.getColumnModel().getColumn(6).setMaxWidth(0);
            tblEntrada.getColumnModel().getColumn(7).setMinWidth(30);
            tblEntrada.getColumnModel().getColumn(7).setPreferredWidth(30);
            tblEntrada.getColumnModel().getColumn(7).setMaxWidth(30);
        }

        btnGuardar1.setBackground(new java.awt.Color(46, 204, 113));
        btnGuardar1.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnGuardar1.setText("ENTRADA DE DINERO");
        btnGuardar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnGuardar1.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnGuardar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGuardar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardar1)
                .addContainerGap())
        );

        lbMonto.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        lbMonto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbMonto.setText("MONTO ACTUAL");

        txtDineroTotal.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        txtDineroTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtDineroTotal.setText("0");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lbMonto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDineroTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDineroTotal)
                    .addComponent(lbMonto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlFormularioLayout = new javax.swing.GroupLayout(pnlFormulario);
        pnlFormulario.setLayout(pnlFormularioLayout);
        pnlFormularioLayout.setHorizontalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15))
        );
        pnlFormularioLayout.setVerticalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(10, 10, 10))
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

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped

    }//GEN-LAST:event_txtNombreKeyTyped

    private void txtNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyReleased

    }//GEN-LAST:event_txtNombreKeyReleased

    private void tblEntradaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblEntradaKeyReleased

    }//GEN-LAST:event_tblEntradaKeyReleased

    private void tblEntradaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblEntradaKeyTyped

    }//GEN-LAST:event_tblEntradaKeyTyped

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (cmbTipoBanco.getSelectedIndex() == 0) {
            metodos.msgError(this, "Seleccione el tipo de banco");
            return;
        }

        Object[] campos = {txtNombre, txtNumero};
        String faltantes = metodos.camposVacios(campos);

        if (!faltantes.equals("")) {
            metodos.msgAdvertencia(this, "No puede continuar, faltan los siguientes campos: " + faltantes);
            return;
        }

        Object[][] bancos = instancias.getSql().getBancos();
        for (int i = 0; i < bancos.length; i++) {
            if (txtNumero.getText().equals(bancos[i][1].toString())) {
                metodos.msgError(this, "El número de cuenta ya existe");
                return;
            }
        }

        Object[] vector = {txtNombre.getText(), cmbTipoBanco.getSelectedItem(), txtNumero.getText(),
            txtDescripcion.getText(), metodos.fechaConsulta(metodosGenerales.fecha()), instancias.getUsuario()};

        Object[] vector1 = {big.getMoneda(txtSaldoInicial.getText())};

        if (!instancias.getSql().agregarBanco(vector, vector1)) {
            metodos.msgError(this, "Hubo un problema al guardar el banco");
            return;
        } else {
            metodos.msgExito(this, "Banco registrado con exito");
            txtNombre.setText("");
            cmbTipoBanco.setSelectedIndex(0);
            txtNumero.setText("");
            txtSaldoInicial.setText("");
            txtDescripcion.setText("");
            llenarBancos();
            instancias.getRepBancos().actualizarBancos();
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void lbNitKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lbNitKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtNombre.requestFocus();
        }
    }//GEN-LAST:event_lbNitKeyReleased

    private void lbNit1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lbNit1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_lbNit1KeyReleased

    private void btnGuardar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar1ActionPerformed
        String nombre = "";
        String cuenta = "";

        try {
            nombre = cmbBancos.getSelectedItem().toString().split(" - ")[0];
            cuenta = cmbBancos.getSelectedItem().toString().split(" - ")[1];
        } catch (Exception e) {
            return;
        }

        dlgMovimientosBanco movimiento = new dlgMovimientosBanco(null, true, nombre, cuenta, big.getMoneda(txtDineroTotal.getText()), "Ingreso", "", BigDecimal.ZERO, "", "");
        movimiento.setLocationRelativeTo(null);
        movimiento.setVisible(true);
    }//GEN-LAST:event_btnGuardar1ActionPerformed

    private void lbNit2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lbNit2KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_lbNit2KeyReleased

    private void lbNit3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lbNit3KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_lbNit3KeyReleased

    private void txtNumeroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumeroKeyReleased

    private void txtNumeroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumeroKeyTyped

    private void lbNit4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lbNit4KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_lbNit4KeyReleased

    private void tblSalidasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblSalidasKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tblSalidasKeyReleased

    private void tblSalidasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblSalidasKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tblSalidasKeyTyped

    private void btnGuardar5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar5ActionPerformed
        String nombre = "";
        String cuenta = "";

        try {
            nombre = cmbBancos.getSelectedItem().toString().split(" - ")[0];
            cuenta = cmbBancos.getSelectedItem().toString().split(" - ")[1];
        } catch (Exception e) {
            return;
        }

        dlgMovimientosBanco movimiento = new dlgMovimientosBanco(null, true, nombre, cuenta, big.getMoneda(txtDineroTotal.getText()), "", "", BigDecimal.ZERO, "", "");
        movimiento.setLocationRelativeTo(null);
        movimiento.setVisible(true);
    }//GEN-LAST:event_btnGuardar5ActionPerformed

    private void cmbBancosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbBancosItemStateChanged
        refrescarDatos();
    }//GEN-LAST:event_cmbBancosItemStateChanged

    private void lbNit5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lbNit5KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_lbNit5KeyReleased

    private void txtSaldoInicialKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSaldoInicialKeyReleased
        if (txtSaldoInicial.getText().equals("") || txtSaldoInicial.getText().equals(this.simbolo) || txtSaldoInicial.getText().equals(this.simbolo + " ")) {
            txtSaldoInicial.setText("0");
        }

        txtSaldoInicial.setText(big.setMoneda(big.getMoneda(txtSaldoInicial.getText())));
    }//GEN-LAST:event_txtSaldoInicialKeyReleased

    private void txtSaldoInicialKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSaldoInicialKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSaldoInicialKeyTyped

    private void tblEntradaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEntradaMouseClicked
        if (evt.getClickCount() > 1) {
            //ruta del archivo en el pc
            String file = tblEntrada.getValueAt(tblEntrada.getSelectedRow(), 6).toString();
            try {
                Runtime.getRuntime().exec("rundll32 SHELL32.DLL,ShellExec_RunDLL " + file);
            } catch (IOException e) {

            }
        }
    }//GEN-LAST:event_tblEntradaMouseClicked

    private void tblSalidasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSalidasMouseClicked
        if (evt.getClickCount() > 1) {
            //ruta del archivo en el pc
            String file = tblSalidas.getValueAt(tblSalidas.getSelectedRow(), 6).toString();
            try {
                Runtime.getRuntime().exec("rundll32 SHELL32.DLL,ShellExec_RunDLL " + file);
            } catch (IOException e) {

            }
        }
    }//GEN-LAST:event_tblSalidasMouseClicked

    public void llenarBancos() {
        cmbBancos.removeAllItems();
        Object[][] bancos = instancias.getSql().getBancos();
        for (int i = 0; i < bancos.length; i++) {
            String nombre = bancos[i][0] + " - " + bancos[i][1];
            cmbBancos.addItem(nombre);
        }
    }

    public void refrescarDatos() {

        DefaultTableModel modelo = (DefaultTableModel) tblEntrada.getModel();
        DefaultTableModel modelo1 = (DefaultTableModel) tblSalidas.getModel();

        while (tblEntrada.getRowCount() > 0) {
            modelo.removeRow(0);
        }

        while (tblSalidas.getRowCount() > 0) {
            modelo1.removeRow(0);
        }

        String monto = "0";
        try {
            monto = instancias.getSql().montoBanco(cmbBancos.getSelectedItem().toString().split(" - ")[1]);
        } catch (Exception e) {
        }

        if (cmbBancos.getSelectedItem() != null) {
            lbMonto.setText("MONTO ACTUAL DEL " + cmbBancos.getSelectedItem().toString().split(" - ")[0]);
            txtDineroTotal.setText(big.setMoneda(big.getMoneda(monto)));
        }

        try {
            Object[][] bancos = instancias.getSql().getMovimientosBancos(cmbBancos.getSelectedItem().toString().split(" - ")[1]);

            for (Object[] banco : bancos) {
                if (banco[5].equals("Entrada")) {
                    modelo.addRow(banco);
                } else {
                    modelo1.addRow(banco);
                }
            }
        } catch (Exception e) {
        }

        Icon icono = null;
        ImageIcon fot = new ImageIcon(getClass().getResource("/imagenes/galeria.png"));
        icono = new ImageIcon(fot.getImage().getScaledInstance(18, 18, Image.SCALE_DEFAULT));

        for (int i = 0; i < tblEntrada.getRowCount(); i++) {
            tblEntrada.setValueAt(metodos.fecha(tblEntrada.getValueAt(i, 0).toString()), i, 0);
            tblEntrada.setValueAt(big.setMoneda(big.getMoneda(tblEntrada.getValueAt(i, 1).toString())), i, 1);
            tblEntrada.setValueAt(big.setMoneda(big.getMoneda(tblEntrada.getValueAt(i, 2).toString())), i, 2);
            tblEntrada.setValueAt(big.setMoneda(big.getMoneda(tblEntrada.getValueAt(i, 3).toString())), i, 3);

            try {
                tblEntrada.setValueAt(tblEntrada.getValueAt(i, 6).toString().split(",")[1], i, 6);
                tblEntrada.setValueAt(new JLabel(icono), i, 7);
            } catch (Exception e) {
                tblEntrada.setValueAt("", i, 6);
                tblEntrada.setValueAt("", i, 7);
            }
        }

        for (int i = 0; i < tblSalidas.getRowCount(); i++) {
            tblSalidas.setValueAt(metodos.fecha(tblSalidas.getValueAt(i, 0).toString()), i, 0);
            tblSalidas.setValueAt(big.setMoneda(big.getMoneda(tblSalidas.getValueAt(i, 1).toString())), i, 1);
            tblSalidas.setValueAt(big.setMoneda(big.getMoneda(tblSalidas.getValueAt(i, 2).toString())), i, 2);
            tblSalidas.setValueAt(big.setMoneda(big.getMoneda(tblSalidas.getValueAt(i, 3).toString())), i, 3);

            try {
                tblSalidas.setValueAt(tblSalidas.getValueAt(i, 6).toString().split(",")[1], i, 6);
                tblSalidas.setValueAt(new JLabel(icono), i, 7);
            } catch (Exception e) {
                tblSalidas.setValueAt("", i, 6);
                tblSalidas.setValueAt("", i, 7);
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnGuardar1;
    private javax.swing.JButton btnGuardar5;
    private javax.swing.JComboBox cmbBancos;
    private javax.swing.JComboBox cmbTipoBanco;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbMonto;
    private javax.swing.JLabel lbNit;
    private javax.swing.JLabel lbNit1;
    private javax.swing.JLabel lbNit2;
    private javax.swing.JLabel lbNit3;
    private javax.swing.JLabel lbNit4;
    private javax.swing.JLabel lbNit5;
    private javax.swing.JPanel pnlFormulario;
    private javax.swing.JScrollPane scrFormulario;
    private javax.swing.JTable tblEntrada;
    private javax.swing.JTable tblSalidas;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JLabel txtDineroTotal;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JTextField txtSaldoInicial;
    // End of variables declaration//GEN-END:variables
}
