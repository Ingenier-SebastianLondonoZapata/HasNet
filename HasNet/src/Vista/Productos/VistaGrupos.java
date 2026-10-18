package Vista.Productos;

import Controlador.Alertas.ControladorAlertas;

import clases.Instancias;
import clases.metodosGenerales;
import formularios.productos.buscGrupos;
import formularios.productos.buscSubGrupos;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class VistaGrupos extends javax.swing.JInternalFrame {

    metodosGenerales metodos = new metodosGenerales();
    private final Instancias instancias;
    private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension dimBarra = null;
    DefaultTableModel modelo;
    TableRowSorter modeloOrdenado;
    String estado = null;

    public VistaGrupos() {

        initComponents();

        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
        dimBarra = Barra.getPreferredSize();
        Barra.setSize(0, 0);
        Barra.setPreferredSize(new Dimension(0, 0));
        setBorder(null);
        repaint();

        instancias = Instancias.getInstancias();
        txtCodigoSubGrupo.setVisible(false);
        btnNuevoActionPerformed(null);
        
        pnlFormulario.registerKeyboardAction(accion("guardar"), "guardar", KeyStroke.getKeyStroke(KeyEvent.VK_G, Event.CTRL_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
        pnlFormulario.registerKeyboardAction(accion("limpiar"), "limpiar", KeyStroke.getKeyStroke(KeyEvent.VK_L, Event.CTRL_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
        pnlFormulario.registerKeyboardAction(accion("productos"), "productos", KeyStroke.getKeyStroke(KeyEvent.VK_P, Event.CTRL_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
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
                        if ((btnGuardar.isEnabled()) && (btnGuardar.isVisible())) {
                            btnGuardarActionPerformed(null);
                        }
                        break;
                    case "productos":
                        if ((btnBuscarGrupo.isEnabled()) && (btnBuscarGrupo.isVisible())) {
                            btnBuscarGrupoActionPerformed(null);
                        }
                        break;

                    case "modificar":
                        if ((btnModificar.isEnabled()) && (btnModificar.isVisible())) {
                            btnModificarActionPerformed(null);
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

        grupoDescuento = new javax.swing.ButtonGroup();
        buttonGroup1 = new javax.swing.ButtonGroup();
        scrFormulario = new javax.swing.JScrollPane();
        pnlFormulario = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        lbNit1 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        lbNit2 = new javax.swing.JLabel();
        descuentoSi = new javax.swing.JRadioButton();
        descuentoNo = new javax.swing.JRadioButton();
        lbVisor = new javax.swing.JLabel();
        visualizarSi = new javax.swing.JRadioButton();
        visualizarNo = new javax.swing.JRadioButton();
        btnBuscarGrupo = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSubGrupos = new javax.swing.JTable();
        txtNombreSubGrupo = new javax.swing.JTextField();
        lbNit6 = new javax.swing.JLabel();
        btnGuardarSubGrupo = new javax.swing.JButton();
        btnLimpiarSubgrupo = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        txtCodigoSubGrupo = new javax.swing.JTextField();
        txtCodigo = new javax.swing.JTextField();

        setTitle("Factura");

        pnlFormulario.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Grupos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 13))); // NOI18N

        lbNit1.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lbNit1.setText("Descripción del grupo:  *");

        txtNombre.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        txtNombre.setName("Nombre Grupo"); // NOI18N

        lbNit2.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbNit2.setText("Permitir descuento:");

        descuentoSi.setBackground(new java.awt.Color(255, 255, 255));
        grupoDescuento.add(descuentoSi);
        descuentoSi.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        descuentoSi.setText("Si");

        descuentoNo.setBackground(new java.awt.Color(255, 255, 255));
        grupoDescuento.add(descuentoNo);
        descuentoNo.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        descuentoNo.setSelected(true);
        descuentoNo.setText("No");

        lbVisor.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbVisor.setText("Visualizar en la factura:");

        visualizarSi.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(visualizarSi);
        visualizarSi.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        visualizarSi.setText("Si");

        visualizarNo.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(visualizarNo);
        visualizarNo.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        visualizarNo.setSelected(true);
        visualizarNo.setText("No");

        btnBuscarGrupo.setBackground(new java.awt.Color(204, 204, 204));
        btnBuscarGrupo.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        btnBuscarGrupo.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscarGrupo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar1.png"))); // NOI18N
        btnBuscarGrupo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnBuscarGrupo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnBuscarGrupo.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnBuscarGrupo.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnBuscarGrupo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarGrupoActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        btnGuardar.setBackground(new java.awt.Color(46, 204, 113));
        btnGuardar.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
        btnGuardar.setText("GUARDAR ");
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnGuardar.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnEliminar.setBackground(new java.awt.Color(241, 148, 138));
        btnEliminar.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/borrar2.png"))); // NOI18N
        btnEliminar.setText("INACTIVAR");
        btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminar.setEnabled(false);
        btnEliminar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnEliminar.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnNuevo.setBackground(new java.awt.Color(204, 204, 204));
        btnNuevo.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/nuevo.png"))); // NOI18N
        btnNuevo.setText("NUEVO    ");
        btnNuevo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNuevo.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnNuevo.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnModificar.setBackground(new java.awt.Color(93, 173, 226));
        btnModificar.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/actualizar.png"))); // NOI18N
        btnModificar.setText("MODIFICAR");
        btnModificar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnModificar.setEnabled(false);
        btnModificar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnModificar.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnModificar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEliminar))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(btnEliminar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbNit2, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNit1)
                    .addComponent(lbVisor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscarGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(descuentoSi)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(descuentoNo))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(visualizarSi)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(visualizarNo)))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnBuscarGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbNit1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbNit2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(descuentoSi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(descuentoNo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(2, 2, 2)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(visualizarSi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(visualizarNo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbVisor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(21, 21, 21))))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Subgrupos relacionados al grupo cargado", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 13))); // NOI18N

        tblSubGrupos.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        tblSubGrupos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Nombre del subgrupo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSubGrupos.setRowHeight(24);
        tblSubGrupos.getTableHeader().setReorderingAllowed(false);
        tblSubGrupos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSubGruposMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSubGrupos);
        if (tblSubGrupos.getColumnModel().getColumnCount() > 0) {
            tblSubGrupos.getColumnModel().getColumn(0).setMinWidth(0);
            tblSubGrupos.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblSubGrupos.getColumnModel().getColumn(0).setMaxWidth(0);
        }

        txtNombreSubGrupo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtNombreSubGrupo.setName("Subgrupo"); // NOI18N
        txtNombreSubGrupo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreSubGrupoKeyReleased(evt);
            }
        });

        lbNit6.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lbNit6.setText("Nombre del subgrupo:");

        btnGuardarSubGrupo.setBackground(new java.awt.Color(46, 204, 113));
        btnGuardarSubGrupo.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        btnGuardarSubGrupo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
        btnGuardarSubGrupo.setText("GUARDAR");
        btnGuardarSubGrupo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardarSubGrupo.setEnabled(false);
        btnGuardarSubGrupo.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnGuardarSubGrupo.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnGuardarSubGrupo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarSubGrupoActionPerformed(evt);
            }
        });

        btnLimpiarSubgrupo.setBackground(new java.awt.Color(204, 204, 204));
        btnLimpiarSubgrupo.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        btnLimpiarSubgrupo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/nuevo.png"))); // NOI18N
        btnLimpiarSubgrupo.setText("NUEVO    ");
        btnLimpiarSubgrupo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLimpiarSubgrupo.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnLimpiarSubgrupo.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnLimpiarSubgrupo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarSubgrupoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lbNit6, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNombreSubGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnLimpiarSubgrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGuardarSubGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGuardarSubGrupo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lbNit6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtNombreSubGrupo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                    .addComponent(btnLimpiarSubgrupo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        txtCodigoSubGrupo.setEditable(false);
        txtCodigoSubGrupo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoSubGrupoKeyReleased(evt);
            }
        });

        txtCodigo.setEditable(false);
        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout pnlFormularioLayout = new javax.swing.GroupLayout(pnlFormulario);
        pnlFormulario.setLayout(pnlFormularioLayout);
        pnlFormularioLayout.setHorizontalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSeparator1)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlFormularioLayout.createSequentialGroup()
                        .addComponent(txtCodigoSubGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        pnlFormularioLayout.setVerticalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(txtCodigoSubGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            .addComponent(scrFormulario)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        Object[] campos = {txtNombre};
        String faltantes = metodos.camposVacios(campos);

        if (!faltantes.equals("")) {
            ControladorAlertas.alert("No puede continuar, faltan los siguientes campos: " + faltantes);
            return;
        }

        if (ControladorAlertas.option("¿Desea continuar?")) {
            if (!instancias.getSql().agregarGrupo(txtCodigo.getText().replace("'", "//"), txtNombre.getText(), "",
                    descuentoSi.isSelected(), visualizarSi.isSelected(), "")) {
                ControladorAlertas.alertFail("Error al guardar el grupo");
                return;
            }

            if (!instancias.getSql().aumentarConsecutivo("GRP", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("GRP")[0]) + 1)) {
                ControladorAlertas.alertFail("Error al guardar en el consecutivo ");
            }
            
            ControladorAlertas.alertSuccess("Grupo registrado con éxito");
            btnGuardar.setEnabled(false);
            btnModificar.setEnabled(true);
            btnEliminar.setEnabled(true);

            cargarTablaSubGrupos(txtCodigo.getText());
            instancias.actualizarGrupos(new Object[]{txtCodigo.getText(), txtNombre.getText()});

            btnNuevoActionPerformed(evt);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        buttonGroup1.clearSelection();
        grupoDescuento.clearSelection();

        btnEliminar.setText("INACTIVAR");

        txtCodigo.setText("GRP-" + instancias.getSql().getNumConsecutivo("GRP")[0]);
        txtNombre.setText("");
        txtNombreSubGrupo.setText("");
        txtCodigoSubGrupo.setText("");
        btnGuardar.setEnabled(true);
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnLimpiarSubgrupo.setEnabled(false);
        tblSubGrupos.removeAll();

        btnGuardarSubGrupo.setBackground(new java.awt.Color(46, 204, 113));
        btnGuardarSubGrupo.setForeground(new java.awt.Color(0, 0, 0));
        btnGuardarSubGrupo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png")));
        btnGuardarSubGrupo.setText("GUARDAR");
        btnGuardarSubGrupo.setEnabled(false);

        DefaultTableModel x = (DefaultTableModel) tblSubGrupos.getModel();

        int i, j = tblSubGrupos.getRowCount();

        for (i = 0; i < j; i++) {
            x.removeRow(0);
        }

//        cargarTablaSubGrupos(txtCodigo.getText());

    }//GEN-LAST:event_btnNuevoActionPerformed

    private void txtCodigoSubGrupoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoSubGrupoKeyReleased

    }//GEN-LAST:event_txtCodigoSubGrupoKeyReleased

    private void btnGuardarSubGrupoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarSubGrupoActionPerformed
        if (estado == null) {
            Object[] campos = {txtNombreSubGrupo};
            String faltantes = metodos.camposVacios(campos);

            if (!faltantes.equals("")) {
                ControladorAlertas.alert("No puede continuar, faltan los siguientes campos: " + faltantes);
                return;
            }

            for (int i = 0; i < tblSubGrupos.getRowCount(); i++) {
                if (tblSubGrupos.getValueAt(i, 1).equals(txtNombreSubGrupo.getText())) {
                    ControladorAlertas.alert("Ya existe el subgrupo.");
                    txtNombreSubGrupo.setText("");
                    return;
                }
            }

            if (ControladorAlertas.option("¿Desea guardar subgrupo?")) {
                if (!instancias.getSql().agregarSubGrupo(txtCodigoSubGrupo.getText().replace("'", "//"), txtNombreSubGrupo.getText(), txtCodigo.getText())) {
                    ControladorAlertas.alertFail("Error al guardar el Subgrupo");
                    return;
                }

                if (!instancias.getSql().aumentarConsecutivo("SUBGRUPO", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("SUBGRUPO")[0]) + 1)) {
                    ControladorAlertas.alertFail("Error al guardar el consecutivo ");
                }
                ControladorAlertas.alertSuccess("Subgrupo registrado con éxito");

            }
        } else {
            Object[] campos = {txtNombre};
            String faltantes = metodos.camposVacios(campos);

            if (!faltantes.equals("")) {
                ControladorAlertas.alert("No puede continuar, faltan los siguientes campos: " + faltantes);
                return;
            }
            if (ControladorAlertas.option("¿Desea modificar el subgrupo?")) {

                if (txtNombreSubGrupo.getText().equals("")) {
                    ControladorAlertas.alert("Debe ingresar un nombre");
                    return;
                }
                if (instancias.getSql().modificarSubGrupo(txtCodigoSubGrupo.getText(), txtNombreSubGrupo.getText(), txtCodigo.getText())) {
                    ControladorAlertas.alertSuccess("Subgrupo modificado con exito");

                } else {
                    ControladorAlertas.alertFail("Error al modificar el subgrupo");
                    return;
                }
            }
        }

        estado = null;
        nuevoSubgrupo();
        cargarTablaSubGrupos(txtCodigo.getText());
    }//GEN-LAST:event_btnGuardarSubGrupoActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        Object[] campos = {txtNombre};
        String faltantes = metodos.camposVacios(campos);

        if (txtCodigo.getText().contains("-0")) {
            ControladorAlertas.alert("Este grupo no se puede modificar");
            return;
        }

        if (!faltantes.equals("")) {
            ControladorAlertas.alert("No puede continuar, faltan los siguientes campos: " + faltantes);
            return;
        }

        if (ControladorAlertas.option("¿Desea continuar?")) {

            if (txtNombre.getText().equals("")) {
                ControladorAlertas.alert("Debe ingresar un nombre");
                return;
            }

            if (instancias.getSql().modificarGrupo(txtCodigo.getText(), txtNombre.getText(), "",
                    descuentoSi.isSelected(), visualizarSi.isSelected(), "")) {
                ControladorAlertas.alertSuccess("Grupo modificado con exito");
            } else {
                ControladorAlertas.alertFail("Error al modificar el grupo");
                return;
            }

            cargarTablaSubGrupos(txtCodigo.getText());
            instancias.actualizarGrupos(new Object[]{txtCodigo.getText(), txtNombre.getText()});
            btnNuevoActionPerformed(evt);
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void tblSubGruposMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSubGruposMouseClicked
        if (evt.getClickCount() == 2) {
            txtCodigoSubGrupo.setText(tblSubGrupos.getValueAt(tblSubGrupos.getSelectedRow(), 0).toString());
            txtNombreSubGrupo.setText(tblSubGrupos.getValueAt(tblSubGrupos.getSelectedRow(), 1).toString());
            estado = "modificar";
//            btnGuardarSubGrupo.setBackground(Color.white);
            btnGuardarSubGrupo.setEnabled(true);
            btnGuardarSubGrupo.setText("MODIFICAR");
            btnGuardarSubGrupo.setBackground(new Color(93, 173, 226));
            btnGuardarSubGrupo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/actualizar.png")));
        }
    }//GEN-LAST:event_tblSubGruposMouseClicked

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        if (btnEliminar.getText().equalsIgnoreCase("ACTIVAR")) {
            if (ControladorAlertas.option("¿Activar este grupo?")) {
                instancias.getSql().activarRegistro(txtCodigo.getText(), "bdGrupo");
                ControladorAlertas.alertSuccess("Grupo activado con éxito");
                btnNuevoActionPerformed(evt);
                instancias.actualizarGrupos(new Object[]{txtCodigo.getText(), txtNombre.getText(), ""});
                return;
            }
        }

        if (ControladorAlertas.option("¿Inactivar este grupo?")) {
            instancias.getSql().inactivarRegistro(txtCodigo.getText(), "bdGrupo");
            ControladorAlertas.alertSuccess("Grupo inactivado con éxito");
            btnNuevoActionPerformed(evt);
        }

        instancias.actualizarGrupos(new Object[]{txtCodigo.getText(), txtNombre.getText(), ""});
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void txtNombreSubGrupoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreSubGrupoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnGuardarSubGrupoActionPerformed(null);
        }
    }//GEN-LAST:event_txtNombreSubGrupoKeyReleased

    private void btnBuscarGrupoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarGrupoActionPerformed
        ventanaGrupos("");
    }//GEN-LAST:event_btnBuscarGrupoActionPerformed

    private void txtCodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cargarGrupo(txtCodigo.getText());
            cargarTablaSubGrupos(txtCodigo.getText());
            nuevoSubgrupo();
        }
    }//GEN-LAST:event_txtCodigoKeyReleased

    private void btnLimpiarSubgrupoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarSubgrupoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLimpiarSubgrupoActionPerformed

    public void ventanaGrupos(String codigo) {
        buscGrupos buscar = new buscGrupos(null, rootPaneCheckingEnabled, "");
        buscar.setLocationRelativeTo(null);
        instancias.setBuscGrupos(buscar);
        instancias.setCampoActual(txtCodigo);
        txtCodigo.requestFocus();
        buscar.noEncontrado(codigo.replace("'", "//"));
        buscar.show();

    }

    public void cargarGrupo(String codigo) {
//        txtNombre.setText(sql.getDatosGrupo(codigo.replace("'", "//")));

        Object[] datos = instancias.getSql().getDatosGrupo(codigo.replace("'", "//"));

        txtNombre.setText(datos[0].toString());

        if ((boolean) datos[1]) {
            descuentoSi.setSelected(true);
        } else {
            descuentoNo.setSelected(true);
        }

        if ((boolean) datos[2]) {
            visualizarSi.setSelected(true);
        } else {
            visualizarNo.setSelected(true);
        }

        if ((Boolean) datos[4]) {
            btnEliminar.setText("ACTIVAR");
            btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/agregar.png")));
        }

        btnGuardar.setEnabled(false);
        btnGuardarSubGrupo.setEnabled(true);
        btnModificar.setEnabled(true);
        btnEliminar.setEnabled(true);
        nuevoSubgrupo();
    }

    public void ventanaSubGrupos(String codigo) {
        buscSubGrupos buscar = new buscSubGrupos(null, rootPaneCheckingEnabled);
        buscar.setLocationRelativeTo(null);
        instancias.setBuscSubGrupos(buscar);
        instancias.setValor(txtCodigo.getText());
        instancias.setCampoActual(txtCodigoSubGrupo);
        txtCodigoSubGrupo.requestFocus();
        buscar.noEncontrado(codigo.replace("'", "//"));
        buscar.show();
    }

    public void cargarSubGrupo(String codigo) {
        txtNombreSubGrupo.setText(instancias.getSql().getDatosSubGrupo(codigo.replace("'", "//")));
    }

    public void nuevoSubgrupo() {
        txtCodigoSubGrupo.setText("SUBGRUPO-" + instancias.getSql().getNumConsecutivo("SUBGRUPO")[0]);
        txtNombreSubGrupo.setText("");
        btnGuardarSubGrupo.setEnabled(true);
        estado = null;

        btnGuardarSubGrupo.setBackground(new java.awt.Color(46, 204, 113));
        btnGuardarSubGrupo.setForeground(new java.awt.Color(0, 0, 0));
        btnGuardarSubGrupo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png")));
        btnGuardarSubGrupo.setText("GUARDAR");
    }

    public void cargarTablaSubGrupos(String codigo) {
        String condicion = "";
        if (!codigo.equals("")) {
            condicion = " where grupo = '" + codigo + "' ";
        }

        tblSubGrupos.setModel(instancias.getSql().getRegistrosSubGrupos(condicion));
        modelo = (DefaultTableModel) tblSubGrupos.getModel();

        modeloOrdenado = new TableRowSorter<>(modelo);
        tblSubGrupos.setRowSorter(modeloOrdenado);
        btnGuardarSubGrupo.setEnabled(true);
        btnLimpiarSubgrupo.setEnabled(true);
        
        if (tblSubGrupos.getColumnModel().getColumnCount() > 0) {
            tblSubGrupos.getColumnModel().getColumn(0).setMinWidth(0);
            tblSubGrupos.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblSubGrupos.getColumnModel().getColumn(0).setMaxWidth(0);
        }
        
        txtCodigoSubGrupo.setText("SUBGRUPO-" + instancias.getSql().getNumConsecutivo("SUBGRUPO")[0]);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarGrupo;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnGuardarSubGrupo;
    private javax.swing.JButton btnLimpiarSubgrupo;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JRadioButton descuentoNo;
    private javax.swing.JRadioButton descuentoSi;
    private javax.swing.ButtonGroup grupoDescuento;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbNit1;
    private javax.swing.JLabel lbNit2;
    private javax.swing.JLabel lbNit6;
    private javax.swing.JLabel lbVisor;
    private javax.swing.JPanel pnlFormulario;
    private javax.swing.JScrollPane scrFormulario;
    private javax.swing.JTable tblSubGrupos;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtCodigoSubGrupo;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNombreSubGrupo;
    private javax.swing.JRadioButton visualizarNo;
    private javax.swing.JRadioButton visualizarSi;
    // End of variables declaration//GEN-END:variables
}
