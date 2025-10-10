package formularios.Medico;

import clases.Instancias;
import clases.Medico.ndEps;
import clases.Medico.ndEpsPrecargados;
import Utilidades.BaseDatos.SQL;
import clases.big;
import clases.metodosGenerales;
import Modelo.Terceros.ModeloContacto;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;

public class infEps extends javax.swing.JInternalFrame {

    metodosGenerales metodos = new metodosGenerales();
    private Instancias instancias;

    private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension dimBarra = null;
    Object[][] grupos;
    DefaultTableModel modelo;
    String simbolo = "";

    public infEps() {

        initComponents();

        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
        dimBarra = Barra.getPreferredSize();
        Barra.setSize(0, 0);
        Barra.setPreferredSize(new Dimension(0, 0));
        setBorder(null);
        repaint();
        instancias = Instancias.getInstancias();

        simbolo = instancias.getSimbolo();
        modelo = (DefaultTableModel) tblProductos.getModel();
        btnNuevoActionPerformed(null);

        pnlFormulario.registerKeyboardAction(accion("guardar"), "guardar", KeyStroke.getKeyStroke(KeyEvent.VK_G, Event.CTRL_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
        pnlFormulario.registerKeyboardAction(accion("limpiar"), "limpiar", KeyStroke.getKeyStroke(KeyEvent.VK_L, Event.CTRL_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
        pnlFormulario.registerKeyboardAction(accion("modificar"), "modificar", KeyStroke.getKeyStroke(KeyEvent.VK_M, Event.CTRL_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
    }

    private ActionListener accion(final String opc) {
        ActionListener a = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (opc) {
                    case "limpiar":
                        if ((btnNuevo.isEnabled()) && (btnNuevo.isVisible())) {
                            btnNuevoActionPerformed(null);
                        }
                        break;
                    case "guardar":
                        if ((btnGuardar1.isEnabled()) && (btnGuardar1.isVisible())) {
                            btnGuardar1ActionPerformed(null);
                        }
                        break;

                    case "modificar":
                        if ((btnActualizar1.isEnabled()) && (btnActualizar1.isVisible())) {
                            btnActualizar1ActionPerformed(null);
                        }
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
        lbNit10 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lbCambiarTamano = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        txtDato1 = new javax.swing.JTextField();
        txtId = new javax.swing.JTextField();
        lbNit = new javax.swing.JLabel();
        lbRazon3 = new javax.swing.JLabel();
        lbRazon4 = new javax.swing.JLabel();
        txtDato2 = new javax.swing.JTextField();
        btnBuscTerceros = new javax.swing.JButton();
        lbRazon2 = new javax.swing.JLabel();
        txtRazon = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        lbRazon5 = new javax.swing.JLabel();
        txtContrato = new javax.swing.JTextField();
        lbRazon6 = new javax.swing.JLabel();
        cmbTipoId = new javax.swing.JComboBox();
        lbRazon7 = new javax.swing.JLabel();
        txtPlanBeneficios = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        btnActualizar1 = new javax.swing.JButton();
        btnGuardar1 = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProductos = new javax.swing.JTable();

        setTitle("Ayuda Diagnostica");

        pnlFormulario.setBackground(new java.awt.Color(255, 255, 255));

        lbNit10.setFont(new java.awt.Font("Bell MT", 1, 36)); // NOI18N
        lbNit10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit10.setText("REGISTRO CONVENIOS EPS");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel3MouseEntered(evt);
            }
        });

        lbCambiarTamano.setBackground(new java.awt.Color(153, 255, 153));
        lbCambiarTamano.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbCambiarTamano.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/expandir.png"))); // NOI18N
        lbCambiarTamano.setToolTipText("Expandir/Contraer Menu");
        lbCambiarTamano.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        lbCambiarTamano.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbCambiarTamano.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        lbCambiarTamano.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        lbCambiarTamano.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbCambiarTamanoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbCambiarTamanoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbCambiarTamanoMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(lbCambiarTamano)
                .addGap(27, 27, 27))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(lbCambiarTamano)
                .addGap(31, 31, 31))
        );

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        txtDato1.setName("Codigo"); // NOI18N
        txtDato1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDato1ActionPerformed(evt);
            }
        });
        txtDato1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDato1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDato1KeyTyped(evt);
            }
        });

        txtId.setName("Codigo"); // NOI18N
        txtId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIdKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIdKeyTyped(evt);
            }
        });

        lbNit.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbNit.setText("Nit:");
        lbNit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lbNitKeyReleased(evt);
            }
        });

        lbRazon3.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbRazon3.setText("Codigo:");

        lbRazon4.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbRazon4.setText("Convenio:");

        txtDato2.setEditable(false);

        btnBuscTerceros.setBackground(new java.awt.Color(247, 196, 69));
        btnBuscTerceros.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnBuscTerceros.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscTerceros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cliente.png"))); // NOI18N
        btnBuscTerceros.setText("CLIENTES      ");
        btnBuscTerceros.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscTerceros.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnBuscTerceros.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnBuscTerceros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscTercerosActionPerformed(evt);
            }
        });

        lbRazon2.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbRazon2.setText("Razon social:");

        txtRazon.setEditable(false);
        txtRazon.setName("Codigo"); // NOI18N
        txtRazon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtRazonKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRazonKeyTyped(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 13)); // NOI18N
        jLabel1.setText("Los precios podran ser modificados, solo despues de guardar el convenio.");

        lbRazon5.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbRazon5.setText("Numero Contrato:");

        lbRazon6.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbRazon6.setText("Tipo Id:");

        cmbTipoId.setEditable(true);
        cmbTipoId.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "NI", "NIT" }));

        lbRazon7.setFont(new java.awt.Font("Bell MT", 1, 16)); // NOI18N
        lbRazon7.setText("Plan de Beneficios");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lbRazon2, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                        .addComponent(lbRazon4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbNit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbRazon3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(lbRazon5)
                    .addComponent(lbRazon6)
                    .addComponent(lbRazon7))
                .addGap(51, 51, 51)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtId, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtRazon, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtDato1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtPlanBeneficios, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtDato2)
                            .addComponent(cmbTipoId, javax.swing.GroupLayout.Alignment.LEADING, 0, 200, Short.MAX_VALUE)
                            .addComponent(txtContrato, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBuscTerceros, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(txtId)
                        .addGap(8, 8, 8))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(lbNit)
                        .addGap(9, 9, 9)))
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtRazon)
                    .addComponent(lbRazon2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbRazon3)
                    .addComponent(txtDato1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbRazon5)
                    .addComponent(txtContrato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbRazon6)
                    .addComponent(cmbTipoId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbRazon7)
                    .addComponent(txtPlanBeneficios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDato2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbRazon4)
                    .addComponent(jLabel1)
                    .addComponent(btnBuscTerceros))
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        btnActualizar1.setBackground(new java.awt.Color(0, 0, 0));
        btnActualizar1.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnActualizar1.setForeground(new java.awt.Color(255, 255, 255));
        btnActualizar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/actualizar.png"))); // NOI18N
        btnActualizar1.setText("MODIFICAR  ");
        btnActualizar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnActualizar1.setEnabled(false);
        btnActualizar1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnActualizar1.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnActualizar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizar1ActionPerformed(evt);
            }
        });

        btnGuardar1.setBackground(new java.awt.Color(0, 153, 153));
        btnGuardar1.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnGuardar1.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
        btnGuardar1.setText("GUARDAR       ");
        btnGuardar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnGuardar1.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnGuardar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardar1ActionPerformed(evt);
            }
        });
        btnGuardar1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                btnGuardar1KeyReleased(evt);
            }
        });

        btnBuscar.setBackground(new java.awt.Color(204, 102, 255));
        btnBuscar.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar.png"))); // NOI18N
        btnBuscar.setText("BUSCAR        ");
        btnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnBuscar.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnNuevo.setBackground(new java.awt.Color(255, 204, 51));
        btnNuevo.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnNuevo.setForeground(new java.awt.Color(255, 255, 255));
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/nuevo.png"))); // NOI18N
        btnNuevo.setText("NUEVO         ");
        btnNuevo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNuevo.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnNuevo.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBuscar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardar1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnActualizar1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnBuscar)
                        .addComponent(btnNuevo))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnGuardar1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnActualizar1)))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        tblProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Producto", "Precio", "Convenio"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblProductos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblProductosKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblProductos);
        if (tblProductos.getColumnModel().getColumnCount() > 0) {
            tblProductos.getColumnModel().getColumn(0).setMinWidth(100);
            tblProductos.getColumnModel().getColumn(0).setPreferredWidth(100);
            tblProductos.getColumnModel().getColumn(0).setMaxWidth(100);
            tblProductos.getColumnModel().getColumn(2).setMinWidth(150);
            tblProductos.getColumnModel().getColumn(2).setPreferredWidth(150);
            tblProductos.getColumnModel().getColumn(2).setMaxWidth(150);
            tblProductos.getColumnModel().getColumn(3).setMinWidth(150);
            tblProductos.getColumnModel().getColumn(3).setPreferredWidth(150);
            tblProductos.getColumnModel().getColumn(3).setMaxWidth(150);
        }

        javax.swing.GroupLayout pnlFormularioLayout = new javax.swing.GroupLayout(pnlFormulario);
        pnlFormulario.setLayout(pnlFormularioLayout);
        pnlFormularioLayout.setHorizontalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlFormularioLayout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbNit10, javax.swing.GroupLayout.DEFAULT_SIZE, 961, Short.MAX_VALUE))
                    .addGroup(pnlFormularioLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        pnlFormularioLayout.setVerticalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbNit10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        scrFormulario.setViewportView(pnlFormulario);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrFormulario)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(scrFormulario)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtIdKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdKeyTyped

    }//GEN-LAST:event_txtIdKeyTyped

    private void txtIdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            ndEpsPrecargados nodo = instancias.getSql().getDatosEpsPrecargados(txtId.getText());

            if (nodo.getId() == null) {
                ModeloContacto nodoDos = instancias.getSql().getDatosTercero(txtId.getText());

                if (nodoDos.getId() == null) {
                    metodos.msgAdvertencia(this, "El Cliente no existe");
                } else {
                    if (nodoDos.isActivo()) {
                        metodos.msgError(null, "Este cliente esta inactivado");
                        lbNit.requestFocus();
                        return;
                    }
                    txtRazon.setText(nodoDos.getNombre());
                    txtDato1.requestFocus();
                }

            } else {
                cargarEps(txtId.getText());
            }
        } else {
            txtRazon.setText("");
        }
    }//GEN-LAST:event_txtIdKeyReleased

    private void jPanel3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseEntered
        lbCambiarTamano.setVisible(true);
    }//GEN-LAST:event_jPanel3MouseEntered

    private void lbCambiarTamanoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbCambiarTamanoMouseExited
        lbCambiarTamano.setVisible(false);
    }//GEN-LAST:event_lbCambiarTamanoMouseExited

    private void lbCambiarTamanoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbCambiarTamanoMouseEntered
        lbCambiarTamano.setVisible(true);
    }//GEN-LAST:event_lbCambiarTamanoMouseEntered

    private void lbCambiarTamanoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbCambiarTamanoMouseClicked
        boolean seVe = instancias.getMenu().getSeVeElMenu();

        if (seVe) {
            instancias.getMenu().ocultarMenu("");
        } else {
            instancias.getMenu().expandirMenu();
        }

        lbCambiarTamano.setVisible(false);
    }//GEN-LAST:event_lbCambiarTamanoMouseClicked

    private void btnGuardar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar1ActionPerformed
        Object[] campos = {txtId};
        String faltantes = metodos.camposVacios(campos);
        Object[] campos2 = {txtDato1};
        String faltantes2 = metodos.camposVacios(campos2);

        if (txtRazon.getText().equals("")) {
            metodos.msgAdvertencia(this, "Cargue un cliente valido");
            ventanaTerceros("");
            return;
        } else if (!faltantes.equals("")) {
            metodos.msgAdvertencia(this, "No puede continuar, faltan los siguientes campos: " + faltantes);
            return;
        } else if (!faltantes2.equals("")) {
            metodos.msgAdvertencia(this, "Faltan los siguientes campos: " + faltantes2);
        }
        if (metodos.msgPregunta(this, "¿Desea continuar?") == 0) {

            try {
                ndEps nodo = instancias.getSql().getDatosEps(txtId.getText());
                if (nodo.getId() != null) {
                    metodos.msgError(this, "El Eps ya existe");
                    txtId.requestFocus();
                    return;
                }

                Object[] vector = {txtId.getText(), txtDato1.getText(), txtDato2.getText(), "1", txtContrato.getText(),
                    cmbTipoId.getSelectedItem().toString(), txtPlanBeneficios.getText(), big.getMoneda("0"), big.getMoneda("0")};

                nodo = metodos.llenarEps(vector);
                if (!instancias.getSql().agregarEps(nodo)) {
                    metodos.msgError(this, "Hubo un problema al guardar el Eps");
                    return;
                }

                //CAMBIAR CONSECUTIVO INGRESO
                if (!instancias.getSql().aumentarConsecutivo("CONVENIO", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("CONVENIO")[0]) + 1)) {
                    metodos.msgError(this, "Hubo un problema al guardar en el consecutivo del ingreso");
                }

                metodos.msgExito(this, "Eps registrado con éxito");

                btnNuevoActionPerformed(evt);
            } catch (Exception e) {
                metodos.msgError(this, "Hubo un problema al guardar el Eps");
            }
        }
    }//GEN-LAST:event_btnGuardar1ActionPerformed

    private void btnGuardar1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnGuardar1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGuardar1KeyReleased

    private void btnActualizar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizar1ActionPerformed
        Object[] campos = {txtId};
        String faltantes = metodos.camposVacios(campos);
        Object[] campos2 = {txtDato1};
        String faltantes2 = metodos.camposVacios(campos2);
        if (!faltantes.equals("")) {
            metodos.msgAdvertencia(this, "No puede continuar, faltan los siguientes campos: " + faltantes);
            return;
        } else if (!faltantes2.equals("")) {
            metodos.msgAdvertencia(this, "Faltan los siguientes campos: " + faltantes2);
        }
        if (metodos.msgPregunta(this, "¿Desea continuar?") == 0) {

            try {

                Object[] vector = {txtId.getText(), txtDato1.getText(), txtDato2.getText(), instancias.getSql().getDatosEps(txtId.getText()).getDato3(), txtContrato.getText(),
                    cmbTipoId.getSelectedItem().toString(), txtPlanBeneficios.getText(), big.getMoneda("0"), big.getMoneda("0")};

                ndEps nodo = metodos.llenarEps(vector);
                if (!instancias.getSql().modificarEps(nodo)) {
                    metodos.msgError(this, "Hubo un problema al modificar el Eps");
                    return;
                }

                tblProductos.removeEditor();

                int j = modelo.getRowCount();

                for (int i = 0; i < j; i++) {

                    try {
                        modelo.setValueAt(big.setMoneda(big.getMoneda(modelo.getValueAt(i, 3).toString())), i, 3);
                    } catch (Exception e) {
                        modelo.setValueAt("0", i, 3);
                    }

                    if (!instancias.getSql().modificarLista(modelo.getValueAt(i, 0).toString(), txtDato2.getText(), big.getMoneda(modelo.getValueAt(i, 3).toString()).toString())) {
                        metodos.msgError(this, "Hubo un error al modificar el precio de " + modelo.getValueAt(i, 1).toString());
                    }
                }

                metodos.msgExito(this, "Eps modificado con éxito");

                btnNuevoActionPerformed(evt);
            } catch (Exception e) {
                metodos.msgError(this, "Hubo un problema al modificar el Eps");
            }
        }
    }//GEN-LAST:event_btnActualizar1ActionPerformed

    private void txtDato1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDato1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDato1KeyTyped

    private void txtDato1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDato1KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtDato2.requestFocus();
        }
    }//GEN-LAST:event_txtDato1KeyReleased

    private void txtDato1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDato1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDato1ActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        cargarEps(txtId.getText());
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        txtId.setEditable(true);

        for (int x = 0; x < jPanel11.getComponentCount(); x++) {
            if (jPanel11.getComponent(x) instanceof JTextField) {
                JTextField textField = (JTextField) jPanel11.getComponent(x);
                textField.setText("");
            }
        }

        int j = modelo.getRowCount();

        for (int i = 0; i < j; i++) {
            modelo.removeRow(0);
        }

        btnActualizar1.setEnabled(false);
        btnGuardar1.setEnabled(true);
        txtDato2.setText("" + instancias.getSql().getNumConsecutivo("CONVENIO")[0]);

    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnBuscTercerosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscTercerosActionPerformed
        ventanaTerceros("");
    }//GEN-LAST:event_btnBuscTercerosActionPerformed

    private void txtRazonKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRazonKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRazonKeyReleased

    private void txtRazonKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRazonKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRazonKeyTyped

    private void tblProductosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblProductosKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            tblProductos.removeEditor();

            int x = tblProductos.getSelectedRow();

            try {
                tblProductos.setValueAt(big.setMoneda(big.getMoneda(tblProductos.getValueAt(x, 3).toString())), x, 3);

                if (tblProductos.getRowCount() - 1 > x) {
                    tblProductos.editCellAt(x + 1, 3);
                    tblProductos.setRowSelectionInterval(x + 1, x + 1);
                }
            } catch (Exception e) {
                tblProductos.setValueAt(this.simbolo + " 0", x, 3);
                tblProductos.editCellAt(x, 3);
                tblProductos.setRowSelectionInterval(x, x);
            }
            tblProductos.transferFocus();

        }
    }//GEN-LAST:event_tblProductosKeyReleased

    private void lbNitKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lbNitKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtId.requestFocus();
        }
    }//GEN-LAST:event_lbNitKeyReleased

//    public void setInstancias(Instancias instancias) {
//        this.instancias = instancias;
//        btnNuevoActionPerformed(null);
//    }
    public void cargarEps(String Id) {

        ndEps nodo = instancias.getSql().getDatosEps(Id);

        if (nodo.getId() != null) {

            ModeloContacto nodoDos = instancias.getSql().getDatosTercero(txtId.getText());

            if (nodoDos.getId() != null) {
                txtRazon.setText(nodoDos.getNombre());
                if (nodoDos.isActivo()) {
                    metodos.msgError(null, "Este cliente esta inactivado");
                    lbNit.requestFocus();
                    return;
                }
            }

            txtDato1.setText(nodo.getDato1());
            txtDato2.setText(nodo.getDato2());
            txtContrato.setText(nodo.getDato4());
            if (nodo.getDato5().equals("NI")) {
                cmbTipoId.setSelectedIndex(1);
            } else if (nodo.getDato5().equals("NIT")) {
                cmbTipoId.setSelectedIndex(2);
            } else {
                cmbTipoId.setSelectedItem(nodo.getDato5());
            }
            txtPlanBeneficios.setText(nodo.getDato6());
            txtDato1.requestFocus();
            btnActualizar1.setEnabled(true);
            btnGuardar1.setEnabled(false);
            txtId.setEditable(false);

            int x = metodos.msgPregunta(this, "Cargar los precios de convenio igual a precio original\nNota: Recomendable primera vez");

            tblProductos.setModel(instancias.getSql().getProductosConvenio(nodo.getDato2()));
            modelo = (DefaultTableModel) tblProductos.getModel();

            int j = modelo.getRowCount();

            for (int i = 0; i < j; i++) {

                try {
                    modelo.setValueAt(big.setMoneda(big.getBigDecimal(modelo.getValueAt(i, 2))), i, 2);
                } catch (Exception e) {
                    modelo.setValueAt("0", i, 2);
                }

                if (x == 0) {
                    modelo.setValueAt(modelo.getValueAt(i, 2), i, 3);
                } else {
                    try {
                        modelo.setValueAt(big.setMoneda(big.getBigDecimal(modelo.getValueAt(i, 3))), i, 3);
                    } catch (Exception e) {
                        modelo.setValueAt("0", i, 3);
                    }
                }

            }

            return;
        } else {
            ndEpsPrecargados nodo2 = instancias.getSql().getDatosEpsPrecargados(Id);
            txtRazon.setText(nodo2.getNombre());
        }
    }

    public void ventanaTerceros(String nit) {
        buscEpsPrecargadas buscar = new buscEpsPrecargadas(instancias.getMenu(), rootPaneCheckingEnabled);
        buscar.setLocationRelativeTo(null);
        instancias.setBuscEpsPre(buscar);
        instancias.setCampoActual(txtId);
        txtId.requestFocus();
        buscar.noEncontrado(nit);
        buscar.show();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar1;
    private javax.swing.JButton btnBuscTerceros;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnGuardar1;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JComboBox cmbTipoId;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbCambiarTamano;
    private javax.swing.JLabel lbNit;
    private javax.swing.JLabel lbNit10;
    private javax.swing.JLabel lbRazon2;
    private javax.swing.JLabel lbRazon3;
    private javax.swing.JLabel lbRazon4;
    private javax.swing.JLabel lbRazon5;
    private javax.swing.JLabel lbRazon6;
    private javax.swing.JLabel lbRazon7;
    private javax.swing.JPanel pnlFormulario;
    private javax.swing.JScrollPane scrFormulario;
    private javax.swing.JTable tblProductos;
    private javax.swing.JTextField txtContrato;
    private javax.swing.JTextField txtDato1;
    private javax.swing.JTextField txtDato2;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtPlanBeneficios;
    private javax.swing.JTextField txtRazon;
    // End of variables declaration//GEN-END:variables

    private void cargando(String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
