package formularios.productos;

import clases.ImagePreviewPanel;
import clases.Instancias;
import Utilidades.BaseDatos.SQL;
import clases.metodosGenerales;
import clases.productos.ndGrupo;
import clases.productos.ndProducto;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class infGrupos extends javax.swing.JInternalFrame {

    metodosGenerales metodos = new metodosGenerales();
    private Instancias instancias;
    private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension dimBarra = null;
    DefaultTableModel modelo;
    TableRowSorter modeloOrdenado;
    String estado = null;

    public infGrupos() {

        initComponents();

        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
        dimBarra = Barra.getPreferredSize();
        Barra.setSize(0, 0);
        Barra.setPreferredSize(new Dimension(0, 0));
        setBorder(null);
        repaint();

        instancias = Instancias.getInstancias();
        txtCodigoSubGrupo.setVisible(false);
        lbFoto.setToolTipText("");
        btnNuevoActionPerformed(null);

//        if (instancias.getConfiguraciones().isRestaurante()) {
//            lbVisor.setVisible(true);
//            visualizarNo.setVisible(true);
//            visualizarSi.setVisible(true);
//        } else {
//            lbVisor.setVisible(false);
//            visualizarNo.setVisible(false);
//            visualizarSi.setVisible(false);
//        }

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
                        if ((btnBusProd.isEnabled()) && (btnBusProd.isVisible())) {
                            btnBusProdActionPerformed(null);
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
        lbFoto = new javax.swing.JLabel();
        lbNit = new javax.swing.JLabel();
        lbNit1 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        btnImagen = new javax.swing.JButton();
        lbNit2 = new javax.swing.JLabel();
        descuentoSi = new javax.swing.JRadioButton();
        descuentoNo = new javax.swing.JRadioButton();
        lbVisor = new javax.swing.JLabel();
        visualizarSi = new javax.swing.JRadioButton();
        visualizarNo = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnBusProd = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSubGrupos = new javax.swing.JTable();
        txtNombreSubGrupo = new javax.swing.JTextField();
        lbNit6 = new javax.swing.JLabel();
        btnGuardarSubGrupo = new javax.swing.JButton();
        btnNuevoSubGrupo = new javax.swing.JButton();
        txtCodigoSubGrupo = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();

        setTitle("Factura");

        pnlFormulario.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        lbFoto.setBackground(new java.awt.Color(204, 204, 204));
        lbFoto.setMaximumSize(new java.awt.Dimension(200, 200));
        lbFoto.setMinimumSize(new java.awt.Dimension(200, 200));
        lbFoto.setName(""); // NOI18N
        lbFoto.setOpaque(true);
        lbFoto.setPreferredSize(new java.awt.Dimension(200, 200));
        lbFoto.setRequestFocusEnabled(false);

        lbNit.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit.setText("Codigo:");

        lbNit1.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit1.setText("Grupo:  *");

        txtCodigo.setEditable(false);
        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoKeyReleased(evt);
            }
        });

        txtNombre.setName("Nombre Grupo"); // NOI18N

        btnImagen.setBackground(new java.awt.Color(204, 204, 204));
        btnImagen.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnImagen.setForeground(new java.awt.Color(255, 255, 255));
        btnImagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscarInfo.png"))); // NOI18N
        btnImagen.setToolTipText("Subir");
        btnImagen.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnImagen.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnImagen.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImagenActionPerformed(evt);
            }
        });

        lbNit2.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit2.setText("Descuento:");

        descuentoSi.setBackground(new java.awt.Color(255, 255, 255));
        grupoDescuento.add(descuentoSi);
        descuentoSi.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        descuentoSi.setText("Si");

        descuentoNo.setBackground(new java.awt.Color(255, 255, 255));
        grupoDescuento.add(descuentoNo);
        descuentoNo.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        descuentoNo.setSelected(true);
        descuentoNo.setText("No");

        lbVisor.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbVisor.setText("Visualizar en la factura:");

        visualizarSi.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(visualizarSi);
        visualizarSi.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        visualizarSi.setText("Si");

        visualizarNo.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(visualizarNo);
        visualizarNo.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        visualizarNo.setText("No");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        btnNuevo.setBackground(new java.awt.Color(204, 204, 204));
        btnNuevo.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/nuevo.png"))); // NOI18N
        btnNuevo.setText("NUEVO       ");
        btnNuevo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNuevo.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnNuevo.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnGuardar.setBackground(new java.awt.Color(46, 204, 113));
        btnGuardar.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
        btnGuardar.setText("GUARDAR    ");
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnGuardar.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnModificar.setBackground(new java.awt.Color(93, 173, 226));
        btnModificar.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
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

        btnBusProd.setBackground(new java.awt.Color(247, 220, 111));
        btnBusProd.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnBusProd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/listado.png"))); // NOI18N
        btnBusProd.setText("LISTADO    ");
        btnBusProd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBusProd.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnBusProd.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnBusProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBusProdActionPerformed(evt);
            }
        });

        btnEliminar.setBackground(new java.awt.Color(241, 148, 138));
        btnEliminar.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBusProd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBusProd, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(lbFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lbVisor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbNit2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(descuentoSi)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(descuentoNo))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(visualizarSi)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(visualizarNo))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(lbNit1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbNit, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(6, 6, 6)))
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbNit, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbNit1, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE))
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbNit2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(descuentoSi)
                        .addComponent(descuentoNo)))
                .addGap(4, 4, 4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(visualizarSi)
                        .addComponent(visualizarNo))
                    .addComponent(lbVisor))
                .addGap(5, 5, 5))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        tblSubGrupos.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        tblSubGrupos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Nombre SubGrupo"
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
            tblSubGrupos.getColumnModel().getColumn(0).setMinWidth(120);
            tblSubGrupos.getColumnModel().getColumn(0).setPreferredWidth(120);
            tblSubGrupos.getColumnModel().getColumn(0).setMaxWidth(120);
        }

        txtNombreSubGrupo.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtNombreSubGrupo.setName("Subgrupo"); // NOI18N
        txtNombreSubGrupo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreSubGrupoKeyReleased(evt);
            }
        });

        lbNit6.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit6.setText("Subgrupo:");

        btnGuardarSubGrupo.setBackground(new java.awt.Color(46, 204, 113));
        btnGuardarSubGrupo.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
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

        btnNuevoSubGrupo.setBackground(new java.awt.Color(204, 204, 204));
        btnNuevoSubGrupo.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnNuevoSubGrupo.setForeground(new java.awt.Color(255, 255, 255));
        btnNuevoSubGrupo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/limpiar.png"))); // NOI18N
        btnNuevoSubGrupo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNuevoSubGrupo.setEnabled(false);
        btnNuevoSubGrupo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnNuevoSubGrupo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnNuevoSubGrupo.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnNuevoSubGrupo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoSubGrupoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 562, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lbNit6, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNombreSubGrupo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNuevoSubGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGuardarSubGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbNit6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtNombreSubGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnNuevoSubGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnGuardarSubGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        txtCodigoSubGrupo.setEditable(false);
        txtCodigoSubGrupo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoSubGrupoKeyReleased(evt);
            }
        });

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout pnlFormularioLayout = new javax.swing.GroupLayout(pnlFormulario);
        pnlFormulario.setLayout(pnlFormularioLayout);
        pnlFormularioLayout.setHorizontalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSeparator1)
                    .addGroup(pnlFormularioLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlFormularioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtCodigoSubGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlFormularioLayout.setVerticalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCodigoSubGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        scrFormulario.setViewportView(pnlFormulario);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrFormulario, javax.swing.GroupLayout.DEFAULT_SIZE, 679, Short.MAX_VALUE)
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
            metodos.msgAdvertencia(this, "No puede continuar, faltan los siguientes campos: " + faltantes);
            return;
        }

        if (metodos.msgPregunta(this, "¿Desea continuar?") == 0) {
            if (!instancias.getSql().agregarGrupo(txtCodigo.getText().replace("'", "//"), txtNombre.getText(), lbFoto.getToolTipText(),
                    descuentoSi.isSelected(), visualizarSi.isSelected(), "")) {
                metodos.msgError(this, "Error al guardar el grupo");
                return;
            }

            if (!instancias.getSql().aumentarConsecutivo("GRP", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("GRP")[0]) + 1)) {
                metodos.msgError(this, "Error al guardar en el consecutivo ");
            }
            metodos.msgExito(this, "Grupo registrado con éxito");

            instancias.actualizarGrupos(new Object[]{txtCodigo.getText(), txtNombre.getText(), lbFoto.getToolTipText()});
//            btnNuevoActionPerformed(evt);
            btnGuardar.setEnabled(false);
            btnModificar.setEnabled(true);
            btnImagen.setEnabled(true);
            btnEliminar.setEnabled(true);
            cargarTablaSubGrupos(txtCodigo.getText());

            btnNuevoActionPerformed(evt);

            instancias.getFactura().cargarTablaRestaurante();
            instancias.getMesa1().cargarTablaRestaurante();
            instancias.getPedido().cargarTablaRestaurante();
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        buttonGroup1.clearSelection();
        grupoDescuento.clearSelection();

        btnEliminar.setText("INACTIVAR");

        txtCodigo.setText("GRP-" + instancias.getSql().getNumConsecutivo("GRP")[0]);
        lbFoto.setIcon(null);
        lbFoto.repaint();
        txtNombre.setText("");
        lbFoto.setToolTipText("");
        txtNombreSubGrupo.setText("");
        txtCodigoSubGrupo.setText("");
        btnGuardar.setEnabled(true);
        btnModificar.setEnabled(false);
        btnImagen.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnNuevoSubGrupo.setEnabled(false);
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

    private void btnBusProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBusProdActionPerformed
        ventanaGrupos("");
    }//GEN-LAST:event_btnBusProdActionPerformed

    private void btnImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImagenActionPerformed

        JFileChooser chooser = new JFileChooser();
        ImagePreviewPanel preview = new ImagePreviewPanel();
        chooser.setAccessory(preview);
        chooser.addPropertyChangeListener(preview);

        FileNameExtensionFilter filtroImagen = new FileNameExtensionFilter("JPG, PNG & GIF", "jpg", "png", "gif");
        chooser.setFileFilter(filtroImagen);

        int respuesta = chooser.showSaveDialog(this);

        if (respuesta == JFileChooser.APPROVE_OPTION) {
            Path FROM = Paths.get(chooser.getSelectedFile().getAbsolutePath());
            String id = "";

            if (lbFoto.getToolTipText().equalsIgnoreCase("")) {
                id = Arrays.toString(instancias.getSql().getNumConsecutivo("IMG"));
                lbFoto.setToolTipText(id);

                if (!instancias.getSql().aumentarConsecutivo("IMG", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("IMG")[0]) + 1)) {
                    metodos.msgError(this, "Hubo un problema al guardar el codigo de la imagen");
                }
            } else {
                id = lbFoto.getToolTipText();
            }

            Path TO = Paths.get(System.getProperty("user.dir") + "\\imagenes\\grupos\\IMG-" + id + ".jpg");

            //sobreescribir el fichero de destino, si existe, y copiar
            // los atributos, incluyendo los permisos rwx
            CopyOption[] options = new CopyOption[]{
                StandardCopyOption.REPLACE_EXISTING,
                StandardCopyOption.COPY_ATTRIBUTES
            };

            try {
                Files.copy(FROM, TO, options);
                ImageIcon fot = new ImageIcon(chooser.getSelectedFile().getAbsolutePath());
                Icon icono = new ImageIcon(fot.getImage().getScaledInstance(lbFoto.getWidth(), lbFoto.getHeight(), Image.SCALE_DEFAULT));
                lbFoto.setIcon(icono);
                this.repaint();
            } catch (Exception ex) {
                metodos.msgError(this, "Hubo un error al cargar el archivo");
            }

        }
    }//GEN-LAST:event_btnImagenActionPerformed

    private void txtCodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cargarGrupo(txtCodigo.getText());
            cargarTablaSubGrupos(txtCodigo.getText());
            nuevoSubgrupo();
        }
    }//GEN-LAST:event_txtCodigoKeyReleased

    private void txtCodigoSubGrupoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoSubGrupoKeyReleased

    }//GEN-LAST:event_txtCodigoSubGrupoKeyReleased

    private void btnGuardarSubGrupoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarSubGrupoActionPerformed
        if (estado == null) {
            Object[] campos = {txtNombreSubGrupo};
            String faltantes = metodos.camposVacios(campos);

            if (!faltantes.equals("")) {
                metodos.msgAdvertencia(this, "No puede continuar, faltan los siguientes campos: " + faltantes);
                return;
            }

            for (int i = 0; i < tblSubGrupos.getRowCount(); i++) {
                if (tblSubGrupos.getValueAt(i, 1).equals(txtNombreSubGrupo.getText())) {
                    metodos.msgAdvertenciaAjustado(this, "Ya existe el subgrupo.");
                    txtNombreSubGrupo.setText("");
                    return;
                }
            }

            if (metodos.msgPregunta(this, "¿Desea guardar subgrupo?") == 0) {
                if (!instancias.getSql().agregarSubGrupo(txtCodigoSubGrupo.getText().replace("'", "//"), txtNombreSubGrupo.getText(), txtCodigo.getText())) {
                    metodos.msgError(this, "Error al guardar el Subgrupo");
                    return;
                }

                if (!instancias.getSql().aumentarConsecutivo("SUBGRUPO", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("SUBGRUPO")[0]) + 1)) {
                    metodos.msgError(this, "Error al guardar el consecutivo ");
                }
                metodos.msgExito(this, "Subgrupo registrado con éxito");

            }
        } else {
            Object[] campos = {txtNombre};
            String faltantes = metodos.camposVacios(campos);

            if (!faltantes.equals("")) {
                metodos.msgAdvertencia(this, "No puede continuar, faltan los siguientes campos: " + faltantes);
                return;
            }
            if (metodos.msgPregunta(this, "¿Desea modificar el subgrupo?") == 0) {

                if (txtNombreSubGrupo.getText().equals("")) {
                    metodos.msgAdvertenciaAjustado(this, "Debe ingresar un nombre");
                    return;
                }
                if (instancias.getSql().modificarSubGrupo(txtCodigoSubGrupo.getText(), txtNombreSubGrupo.getText(), txtCodigo.getText())) {
                    metodos.msgExito(this, "Subgrupo modificado con exito");

                } else {
                    metodos.msgError(this, "Error al modificar el subgrupo");
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
            metodos.msgAdvertenciaAjustado(this, "Este grupo no se puede modificar");
            return;
        }

        if (!faltantes.equals("")) {
            metodos.msgAdvertencia(this, "No puede continuar, faltan los siguientes campos: " + faltantes);
            return;
        }

        if (metodos.msgPregunta(this, "¿Desea continuar?") == 0) {

            if (txtNombre.getText().equals("")) {
                metodos.msgAdvertenciaAjustado(this, "Debe ingresar un nombre");
                return;
            }

            if (instancias.getSql().modificarGrupo(txtCodigo.getText(), txtNombre.getText(), lbFoto.getToolTipText(),
                    descuentoSi.isSelected(), visualizarSi.isSelected(), "")) {
                metodos.msgExito(this, "Grupo modificado con exito");
            } else {
                metodos.msgError(this, "Error al modificar el grupo");
                return;
            }
//            btnNuevoActionPerformed(null);
            cargarTablaSubGrupos(txtCodigo.getText());

            instancias.actualizarGrupos(new Object[]{txtCodigo.getText(), txtNombre.getText(), lbFoto.getToolTipText()});

            instancias.getFactura().cargarTablaRestaurante();
            instancias.getMesa1().cargarTablaRestaurante();
            instancias.getPedido().cargarTablaRestaurante();

            btnNuevoActionPerformed(evt);
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnNuevoSubGrupoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoSubGrupoActionPerformed
        nuevoSubgrupo();
    }//GEN-LAST:event_btnNuevoSubGrupoActionPerformed

    private void tblSubGruposMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSubGruposMouseClicked
        if (evt.getClickCount() == 2) {
            txtCodigoSubGrupo.setText(tblSubGrupos.getValueAt(tblSubGrupos.getSelectedRow(), 0).toString());
            txtNombreSubGrupo.setText(tblSubGrupos.getValueAt(tblSubGrupos.getSelectedRow(), 1).toString());
            estado = "modificar";
//            btnGuardarSubGrupo.setBackground(Color.white);
            btnGuardarSubGrupo.setEnabled(true);
            btnGuardarSubGrupo.setText("Modificar");
            btnGuardarSubGrupo.setBackground(new Color(93, 173, 226));
            btnGuardarSubGrupo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/actualizar.png")));
        }
    }//GEN-LAST:event_tblSubGruposMouseClicked

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        if (btnEliminar.getText().equalsIgnoreCase("ACTIVAR")) {
            if (metodos.msgPregunta(this, "¿Activar este grupo?") == 0) {
                instancias.getSql().activarRegistro(txtCodigo.getText(), "bdGrupo");
                metodos.msgExito(this, "Grupo activado con éxito");
                btnNuevoActionPerformed(evt);
                instancias.actualizarGrupos(new Object[]{txtCodigo.getText(), txtNombre.getText(), lbFoto.getToolTipText()});
                return;
            }
        }

        if (metodos.msgPregunta(this, "¿Inactivar este grupo?") == 0) {
            instancias.getSql().inactivarRegistro(txtCodigo.getText(), "bdGrupo");
            metodos.msgExito(this, "Grupo inactivado con éxito");
            btnNuevoActionPerformed(evt);
        }

        instancias.getFactura().cargarTablaRestaurante();
        instancias.getMesa1().cargarTablaRestaurante();
        instancias.getPedido().cargarTablaRestaurante();
        instancias.actualizarGrupos(new Object[]{txtCodigo.getText(), txtNombre.getText(), lbFoto.getToolTipText()});
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void txtNombreSubGrupoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreSubGrupoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnGuardarSubGrupoActionPerformed(null);
        }
    }//GEN-LAST:event_txtNombreSubGrupoKeyReleased

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
        btnImagen.setEnabled(true);
        btnEliminar.setEnabled(true);
        nuevoSubgrupo();

        try {
            String examen = instancias.getSql().imagenGrupo(txtCodigo.getText());

            if (!examen.equalsIgnoreCase("")) {
                ImageIcon fot = new ImageIcon(System.getProperty("user.dir") + "\\imagenes\\grupos\\IMG-" + examen + ".jpg");
                Icon icono = new ImageIcon(fot.getImage().getScaledInstance(lbFoto.getWidth(), lbFoto.getHeight(), Image.SCALE_DEFAULT));
                lbFoto.setIcon(icono);
                lbFoto.setToolTipText(examen);
                this.repaint();
            } else {
                lbFoto.setIcon(null);
                this.repaint();
            }
        } catch (Exception e) {
            lbFoto.setIcon(null);
            this.repaint();
        }

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
        btnNuevoSubGrupo.setEnabled(true);
        if (tblSubGrupos.getColumnModel().getColumnCount() > 0) {
            tblSubGrupos.getColumnModel().getColumn(0).setMinWidth(120);
            tblSubGrupos.getColumnModel().getColumn(0).setPreferredWidth(120);
            tblSubGrupos.getColumnModel().getColumn(0).setMaxWidth(120);
        }
        txtCodigoSubGrupo.setText("SUBGRUPO-" + instancias.getSql().getNumConsecutivo("SUBGRUPO")[0]);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBusProd;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnGuardarSubGrupo;
    private javax.swing.JButton btnImagen;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnNuevoSubGrupo;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JRadioButton descuentoNo;
    private javax.swing.JRadioButton descuentoSi;
    private javax.swing.ButtonGroup grupoDescuento;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbFoto;
    private javax.swing.JLabel lbNit;
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
