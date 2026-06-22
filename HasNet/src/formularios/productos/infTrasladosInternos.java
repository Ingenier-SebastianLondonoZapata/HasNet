package formularios.productos;

import Modelo.Inventario.UltimoPonderado;
import clases.Instancias;
import clases.big;
import clases.metodosGenerales;
import clases.productos.ndProdTraslBodega;
import clases.productos.ndProducto;
import clases.productos.ndTrasladoBodega;
import clases.terceros.ndBodega;
import Modelo.Terceros.ModeloContacto;
import inventario.servicio.ServicioActualizacionPonderado;
import Vista.Productos.VistaInventarioInicial;
import formularios.infBuscadorCliente;
import formularios.terceros.buscBodegas;
import formularios.terceros.buscClientes;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;

public class infTrasladosInternos extends javax.swing.JInternalFrame {

    private ServicioActualizacionPonderado servicioActualizacionPonderado = new ServicioActualizacionPonderado();

    String simbolo = "";
    DefaultTableModel modeloPro;
    metodosGenerales metodos = new metodosGenerales();
    Instancias instancias;
    Object[] datos;
    DecimalFormat df = new DecimalFormat("#.00");

    //Barra de titulo
    private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension dimBarra = null;
    Boolean saltarPasosTraslados = true;
    private boolean plu = false;

    public boolean isPlu() {
        return plu;
    }

    public void setPlu(boolean plu) {
        this.plu = plu;
    }

    public infTrasladosInternos() {
        initComponents();
        modeloPro = (DefaultTableModel) tblProductos.getModel();

        instancias = Instancias.getInstancias();
        simbolo = instancias.getSimbolo();

        consultarMaestros();
        txtTraslado.setText((String) instancias.getSql().getNumConsecutivo("TRASINT")[0]);
        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
        dimBarra = Barra.getPreferredSize();
        Barra.setSize(0, 0);
        Barra.setPreferredSize(new Dimension(0, 0));
        setBorder(null);
        repaint();

        pnlInvisible.setVisible(false);

        pnlFormulario.registerKeyboardAction(accion("guardar"), "guardar", KeyStroke.getKeyStroke(KeyEvent.VK_G, Event.CTRL_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
        pnlFormulario.registerKeyboardAction(accion("limpiar"), "limpiar", KeyStroke.getKeyStroke(KeyEvent.VK_L, Event.CTRL_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
        pnlFormulario.registerKeyboardAction(accion("productos"), "productos", KeyStroke.getKeyStroke(KeyEvent.VK_P, Event.CTRL_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);

        if (!instancias.getConfiguraciones().isProductosDetallados()) {
            tblProductos.getColumnModel().getColumn(5).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(5).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(5).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(4).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(4).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(4).setMaxWidth(0);
        }
    }

    public void consultarMaestros() {
        datos = instancias.getSql().getDatosMaestra();
    }

    private ActionListener accion(final String opc) {
        ActionListener a = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (opc) {
                    case "limpiar":
                        if ((btnLimpiar.isEnabled()) && (btnLimpiar.isVisible())) {
                            btnLimpiarActionPerformed(null);
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

                }
            }
        };
        return a;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        popBorrar = new javax.swing.JMenuItem();
        scrFormulario = new javax.swing.JScrollPane();
        pnlFormulario = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        txtTraslado = new javax.swing.JTextField();
        lbFacturaNo = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lbNombre = new javax.swing.JLabel();
        lbNit = new javax.swing.JLabel();
        txtNombreBO = new javax.swing.JTextField();
        txtNitBO = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        lbNombre1 = new javax.swing.JLabel();
        lbNit1 = new javax.swing.JLabel();
        txtNombreBD = new javax.swing.JTextField();
        txtNitBD = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        lbNit2 = new javax.swing.JLabel();
        txtUnidades = new javax.swing.JTextField();
        lbNit3 = new javax.swing.JLabel();
        txtValor = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnReimprimir = new javax.swing.JButton();
        btnAnular = new javax.swing.JButton();
        scrProductos = new javax.swing.JScrollPane();
        tblProductos = new javax.swing.JTable();
        lbNit5 = new javax.swing.JLabel();
        txtCodProducto = new javax.swing.JTextField();
        lbNit6 = new javax.swing.JLabel();
        txtCant = new javax.swing.JTextField();
        btnBusProd = new javax.swing.JButton();
        pnlInvisible = new javax.swing.JPanel();
        txtBaseOrigen = new javax.swing.JTextField();
        txtBaseDestino = new javax.swing.JTextField();

        popBorrar.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        popBorrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/eliminar-cancelar-icono-4935-16.png"))); // NOI18N
        popBorrar.setText("Borrar");
        popBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                popBorrarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(popBorrar);

        setTitle("Factura");

        pnlFormulario.setBackground(new java.awt.Color(255, 255, 255));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        txtTraslado.setEditable(false);
        txtTraslado.setBackground(new java.awt.Color(255, 255, 255));
        txtTraslado.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtTraslado.setForeground(new java.awt.Color(255, 0, 0));
        txtTraslado.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTraslado.setText("3");
        txtTraslado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTrasladoKeyReleased(evt);
            }
        });

        lbFacturaNo.setBackground(new java.awt.Color(204, 204, 204));
        lbFacturaNo.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbFacturaNo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbFacturaNo.setText("Traslado No.");
        lbFacturaNo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lbFacturaNo.setOpaque(true);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Bodega origen", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 14))); // NOI18N

        lbNombre.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNombre.setText("Nombre:");
        lbNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lbNombreKeyReleased(evt);
            }
        });

        lbNit.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit.setText("Nit:     *");

        txtNombreBO.setEditable(false);
        txtNombreBO.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        txtNombreBO.setName("Nombre"); // NOI18N
        txtNombreBO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreBOActionPerformed(evt);
            }
        });

        txtNitBO.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        txtNitBO.setName("Nit origen"); // NOI18N
        txtNitBO.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNitBOKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNitBOKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbNit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNitBO, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                    .addComponent(txtNombreBO))
                .addGap(11, 11, 11))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbNit, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNitBO, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNombreBO, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Bodega destino", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 14))); // NOI18N

        lbNombre1.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNombre1.setText("Nombre:");

        lbNit1.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit1.setText("Nit:        *");

        txtNombreBD.setEditable(false);
        txtNombreBD.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        txtNombreBD.setName("Nombre"); // NOI18N

        txtNitBD.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        txtNitBD.setName("Nit destino"); // NOI18N
        txtNitBD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNitBDKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbNombre1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbNit1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNitBD, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                    .addComponent(txtNombreBD))
                .addGap(14, 14, 14))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbNit1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNitBD, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNombreBD, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNombre1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Otro", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 16))); // NOI18N

        lbNit2.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit2.setText("Valor:");

        txtUnidades.setEditable(false);
        txtUnidades.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        txtUnidades.setText("0");
        txtUnidades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUnidadesActionPerformed(evt);
            }
        });

        lbNit3.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit3.setText("Unidades:");

        txtValor.setEditable(false);
        txtValor.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        txtValor.setText("0");
        txtValor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtValorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lbNit3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                        .addComponent(txtUnidades, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lbNit2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtValor)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNit2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(txtUnidades, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(lbNit3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        btnGuardar.setBackground(new java.awt.Color(46, 204, 113));
        btnGuardar.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
        btnGuardar.setText("GUARDAR");
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnGuardar.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnLimpiar.setBackground(new java.awt.Color(204, 204, 204));
        btnLimpiar.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/limpiar.png"))); // NOI18N
        btnLimpiar.setText("LIMPIAR  ");
        btnLimpiar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLimpiar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnLimpiar.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        btnReimprimir.setBackground(new java.awt.Color(247, 220, 111));
        btnReimprimir.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnReimprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/imprimir.png"))); // NOI18N
        btnReimprimir.setText("REIMPRIMIR");
        btnReimprimir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReimprimir.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnReimprimir.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnReimprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReimprimirActionPerformed(evt);
            }
        });

        btnAnular.setBackground(new java.awt.Color(241, 148, 138));
        btnAnular.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnAnular.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/borrar2.png"))); // NOI18N
        btnAnular.setText("ANULAR   ");
        btnAnular.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAnular.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnAnular.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnAnular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnularActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnGuardar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLimpiar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnReimprimir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAnular)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimpiar)
                    .addComponent(btnReimprimir)
                    .addComponent(btnAnular))
                .addGap(5, 5, 5))
        );

        tblProductos.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        tblProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Descripción", "Ultimo Costo", "Cant", "Detalle", "Lote", "idProd", "Nota", "cant2", "plu", "Total", "Inv. Inicial", "Inv. Final"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true, false, false, false, true, true, true, true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblProductos.setComponentPopupMenu(jPopupMenu1);
        tblProductos.setRowHeight(25);
        tblProductos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblProductos.getTableHeader().setReorderingAllowed(false);
        tblProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductosMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblProductosMouseReleased(evt);
            }
        });
        tblProductos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblProductosKeyReleased(evt);
            }
        });
        scrProductos.setViewportView(tblProductos);
        if (tblProductos.getColumnModel().getColumnCount() > 0) {
            tblProductos.getColumnModel().getColumn(0).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(0).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(2).setMinWidth(100);
            tblProductos.getColumnModel().getColumn(2).setPreferredWidth(140);
            tblProductos.getColumnModel().getColumn(2).setMaxWidth(180);
            tblProductos.getColumnModel().getColumn(3).setMinWidth(65);
            tblProductos.getColumnModel().getColumn(3).setPreferredWidth(50);
            tblProductos.getColumnModel().getColumn(3).setMaxWidth(80);
            tblProductos.getColumnModel().getColumn(4).setMinWidth(150);
            tblProductos.getColumnModel().getColumn(4).setPreferredWidth(200);
            tblProductos.getColumnModel().getColumn(4).setMaxWidth(250);
            tblProductos.getColumnModel().getColumn(5).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(5).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(5).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(6).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(6).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(6).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(8).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(8).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(8).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(9).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(9).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(9).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(10).setMinWidth(100);
            tblProductos.getColumnModel().getColumn(10).setPreferredWidth(140);
            tblProductos.getColumnModel().getColumn(10).setMaxWidth(180);
            tblProductos.getColumnModel().getColumn(11).setMinWidth(75);
            tblProductos.getColumnModel().getColumn(11).setPreferredWidth(75);
            tblProductos.getColumnModel().getColumn(11).setMaxWidth(120);
            tblProductos.getColumnModel().getColumn(12).setMinWidth(75);
            tblProductos.getColumnModel().getColumn(12).setPreferredWidth(75);
            tblProductos.getColumnModel().getColumn(12).setMaxWidth(120);
        }

        lbNit5.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit5.setText("PRODUCTOS:");

        txtCodProducto.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        txtCodProducto.setName("combo"); // NOI18N
        txtCodProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodProductoActionPerformed(evt);
            }
        });
        txtCodProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodProductoKeyReleased(evt);
            }
        });

        lbNit6.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit6.setText("Cant:");

        txtCant.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        txtCant.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCant.setText("1");
        txtCant.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtCant.setEnabled(false);
        txtCant.setName("combo"); // NOI18N
        txtCant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantActionPerformed(evt);
            }
        });
        txtCant.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCantKeyReleased(evt);
            }
        });

        btnBusProd.setBackground(new java.awt.Color(204, 204, 204));
        btnBusProd.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnBusProd.setForeground(new java.awt.Color(255, 255, 255));
        btnBusProd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar1.png"))); // NOI18N
        btnBusProd.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnBusProd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBusProd.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnBusProd.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnBusProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBusProdActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scrProductos)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTraslado)
                            .addComponent(lbFacturaNo, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(lbNit6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCant, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(lbNit5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCodProducto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBusProd, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(lbFacturaNo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(txtTraslado, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(5, 5, 5)
                .addComponent(scrProductos, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                .addGap(5, 5, 5)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbNit6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCant)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbNit5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtCodProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnBusProd, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout pnlInvisibleLayout = new javax.swing.GroupLayout(pnlInvisible);
        pnlInvisible.setLayout(pnlInvisibleLayout);
        pnlInvisibleLayout.setHorizontalGroup(
            pnlInvisibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInvisibleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtBaseOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBaseDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlInvisibleLayout.setVerticalGroup(
            pnlInvisibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInvisibleLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(pnlInvisibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBaseOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBaseDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout pnlFormularioLayout = new javax.swing.GroupLayout(pnlFormulario);
        pnlFormulario.setLayout(pnlFormularioLayout);
        pnlFormularioLayout.setHorizontalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlFormularioLayout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(10, 10, 10))
                    .addGroup(pnlFormularioLayout.createSequentialGroup()
                        .addComponent(pnlInvisible, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        pnlFormularioLayout.setVerticalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlInvisible, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
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
            .addGroup(layout.createSequentialGroup()
                .addComponent(scrFormulario)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblProductosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblProductosKeyReleased
        BigDecimal cantidad, valor;

        int fila = tblProductos.getSelectedRow();

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            //Si se esta cambiando el campo de cantidad
            if (tblProductos.getSelectedColumn() == 2) {
                tblProductos.setValueAt(tblProductos.getValueAt(fila, 2).toString(), fila, 2);
                tblProductos.editCellAt(tblProductos.getSelectedRow(), 3);
                tblProductos.setColumnSelectionInterval(3, 3);
                tblProductos.transferFocus();
            } else if (tblProductos.getSelectedColumn() == 3) {
                tblProductos.editCellAt(tblProductos.getSelectedRow(), 7);
                tblProductos.setColumnSelectionInterval(7, 7);
                tblProductos.transferFocus();
            } else if (tblProductos.getSelectedColumn() == 7) {
                txtCodProducto.requestFocus();
            }

            valor = big.getMoneda(String.valueOf(tblProductos.getValueAt(fila, 2)));
            tblProductos.setValueAt(big.setMoneda(valor), fila, 2);

//            try {
//                int res = big.getBigDecimal(big.getMoneda((String) tblProductos.getValueAt(fila, i))).compareTo(big.getBigDecimal("1"));
//                if (res == -1 || String.valueOf(tblProductos.getValueAt(fila, i)).equals("")) {
//                    tblProductos.setValueAt(j, tblProductos.getSelectedRow(), i);
//                }
//            } catch (NumberFormatException e) {
//                tblProductos.setValueAt(j, tblProductos.getSelectedRow(), i);
//            }
            cantidad = big.getMoneda(String.valueOf(tblProductos.getValueAt(fila, 3)).replace(".", ","));
            tblProductos.setValueAt(big.setNumero(cantidad), fila, 3);

            switch (tblProductos.getValueAt(fila, 9).toString()) {
                case "1":
                    tblProductos.setValueAt(cantidad, fila, 8);
                    break;
                case "2":
                    tblProductos.setValueAt(cantidad.multiply(big.getBigDecimal(instancias.getSql().getDatosProducto(tblProductos.getValueAt(fila, 0).toString(), "bdProductos").getCantidad2())), fila, 8);
                    break;
                case "3":
                    tblProductos.setValueAt(cantidad.multiply(big.getBigDecimal(instancias.getSql().getDatosProducto(tblProductos.getValueAt(fila, 0).toString(), "bdProductos").getCantidad3())), fila, 8);
                    break;
                case "4":
                    tblProductos.setValueAt(cantidad.multiply(big.getBigDecimal(instancias.getSql().getDatosProducto(tblProductos.getValueAt(fila, 0).toString(), "bdProductos").getCantidad4())), fila, 8);
                    break;
            }

            tblProductos.setValueAt(Double.valueOf(tblProductos.getValueAt(tblProductos.getSelectedRow(), 11).toString().replace(",", ".")) - Double.valueOf(tblProductos.getValueAt(tblProductos.getSelectedRow(), 3).toString().replace(",", ".")), tblProductos.getSelectedRow(), 12);
            tblProductos.setValueAt(big.setMoneda(valor.multiply(cantidad)), fila, 10);

            cargarTotales();
        }
    }//GEN-LAST:event_tblProductosKeyReleased

    private void txtCodProductoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodProductoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String codigo = txtCodProducto.getText();
            plu = true;
            cargarProducto(codigo.replace("'", "//"), txtCant.getText(), 1, "", "", "", "", "", "", "");
        } else if (evt.getKeyCode() == KeyEvent.VK_MULTIPLY) {
            double cantidad = 1;
            try {
                cantidad = Double.parseDouble(txtCodProducto.getText().replace("*", ""));
            } catch (Exception e) {
            }
            txtCant.setText(String.valueOf(cantidad));
            if (txtCant.getText().substring(txtCant.getText().length() - 1, txtCant.getText().length()).equals("0")) {
                txtCant.setText(txtCant.getText().substring(0, txtCant.getText().length() - 2));
            }
            txtCodProducto.setText("");
        }
    }//GEN-LAST:event_txtCodProductoKeyReleased

    private void txtNitBOKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNitBOKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            if (txtNitBD.getText().equals(txtNitBO.getText()) && !txtNitBD.getText().equals("")) {
                metodos.msgError(this, "La bodega de origen no puede ser igual a la bodega de destino");
                return;
            }

            ndBodega nodo = instancias.getSql().getDatosBodega(txtNitBO.getText());

            if (nodo.getCodigo() != null) {
                txtNombreBO.setText(nodo.getNombre());
                txtBaseOrigen.setText(nodo.getBase());
                txtNitBD.requestFocus();
                return;
            }

            ventanaTerceros(txtNitBO.getText(), txtNitBO);

        } else if (!txtNombreBO.getText().equals("")) {
            txtNombreBO.setText("");
            txtBaseOrigen.setText("");
        }
    }//GEN-LAST:event_txtNitBOKeyReleased

    private void tblProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductosMouseClicked
        if (evt.getClickCount() >= 1 && tblProductos.getSelectedColumn() == 3) {
            ndProducto nodo = instancias.getSql().getDatosProducto(tblProductos.getValueAt(tblProductos.getSelectedRow(), 0).toString(), txtBaseOrigen.getText());
            String tipo = "";
            if (nodo.getTipoProducto() != null) {
                if (nodo.getTipoProducto().equals("IMEI")) {
                    tipo = "Imei";
                } else if (nodo.getTipoProducto().equals("Fecha/Lote")) {
                    tipo = "Fecha/Lote";
                } else if (nodo.getTipoProducto().equals("Color")) {
                    tipo = "Color";
                } else if (nodo.getTipoProducto().equals("Serial")) {
                    tipo = "Serial";
                } else if (nodo.getTipoProducto().equals("Talla")) {
                    tipo = "Talla";
                } else if (nodo.getTipoProducto().equals("ColorTalla")) {
                    tipo = "ColorTalla";
                } else if (nodo.getTipoProducto().equals("SerialColor")) {
                    tipo = "SerialColor";
                } else {
                    tipo = "";
                }
            }

            if (!tipo.equals("")) {
                metodos.msgAdvertenciaAjustado(this, "La cantidad no se puede modificar");
                return;
            }
        }
    }//GEN-LAST:event_tblProductosMouseClicked

    private void tblProductosMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductosMouseReleased
        if (evt.getClickCount() >= 1 && tblProductos.getSelectedColumn() == 3) {
            ndProducto nodo = instancias.getSql().getDatosProducto(tblProductos.getValueAt(tblProductos.getSelectedRow(), 0).toString(), txtBaseOrigen.getText());
            String tipo = "";
            if (nodo.getTipoProducto() != null) {
                if (nodo.getTipoProducto().equals("IMEI")) {
                    tipo = "Imei";
                } else if (nodo.getTipoProducto().equals("Fecha/Lote")) {
                    tipo = "Fecha/Lote";
                } else if (nodo.getTipoProducto().equals("Color")) {
                    tipo = "Color";
                } else if (nodo.getTipoProducto().equals("Serial")) {
                    tipo = "Serial";
                } else if (nodo.getTipoProducto().equals("Talla")) {
                    tipo = "Talla";
                } else if (nodo.getTipoProducto().equals("ColorTalla")) {
                    tipo = "ColorTalla";
                } else if (nodo.getTipoProducto().equals("SerialColor")) {
                    tipo = "SerialColor";
                } else {
                    tipo = "";
                }
            }

            if (!tipo.equals("")) {
                metodos.msgAdvertenciaAjustado(this, "La cantidad no se puede modificar");
                return;
            }
        }
    }//GEN-LAST:event_tblProductosMouseReleased

    private void txtNitBDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNitBDKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            if (txtNitBD.getText().equals(txtNitBO.getText()) && !txtNitBD.getText().equals("")) {
                metodos.msgAdvertenciaAjustado(this, "No pueden ser las mismas bodegas");
                return;
            }

            ndBodega nodo = instancias.getSql().getDatosBodega(txtNitBD.getText());

            if (nodo.getCodigo() != null) {
                txtNombreBD.setText(nodo.getNombre());
                txtBaseDestino.setText(nodo.getBase());
                txtCodProducto.requestFocus();
                return;
            }

            ventanaTerceros(txtNitBD.getText(), txtNitBD);

        } else if (!txtNombreBD.getText().equals("")) {
            txtNombreBD.setText("");
            txtBaseDestino.setText("");
        }
    }//GEN-LAST:event_txtNitBDKeyReleased

    private void txtNitBOKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNitBOKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNitBOKeyPressed

    private void txtUnidadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUnidadesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUnidadesActionPerformed

    private void txtCodProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodProductoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (tblProductos.getRowCount() == 0) {
            metodos.msgError(this, "No ha añadido ningun producto");
        } else {
            Object[] campos = {txtNombreBD, txtNombreBO};
            String faltantes = metodos.camposVacios(campos);

            if (!faltantes.equals("")) {
                metodos.msgAdvertencia(this, "No puede continuar, faltan los siguientes campos: " + faltantes);
                return;
            }

            int xyz = tblProductos.getRowCount();
            if (xyz > 0) {
                for (int i = 0; i < xyz; i++) {
                    if (tblProductos.getValueAt(i, 3).equals("0")) {
                        metodos.msgError(this, "La cantidad debe ser mayor a 0");
                        return;
                    }

                    if (tblProductos.getValueAt(i, 2).equals(this.simbolo + " 0")) {
                        metodos.msgError(this, "Los valores deben ser mayor a " + this.simbolo + " 0");
                        return;
                    }

                    tblProductos.setColumnSelectionInterval(0, 0);
                    tblProductos.setRowSelectionInterval(i, i);
                    KeyEvent x = new KeyEvent(this, WIDTH, WIDTH, WIDTH, KeyEvent.VK_ENTER);
                    tblProductosKeyReleased(x);
                }
            }

            if (metodos.msgPregunta(this, "¿Desea continuar?") == 0) {

                String factura = "TRASINT-" + instancias.getSql().getNumConsecutivo("TRASINT")[0];

                //PROCESO GUARDAR REGISTRO EN CAJA
                Object[] vector = {factura, txtNitBO.getText(), txtNitBD.getText(), metodos.fechaConsulta(metodosGenerales.fecha()),
                    txtUnidades.getText(), big.getMoneda(txtValor.getText()), "", "", instancias.getUsuario(),
                    instancias.getTerminal(), "0", "PENDIENTE", metodosGenerales.hora()};

                ndTrasladoBodega nodo = metodos.llenarTrasladoBodega(vector);

                if (!instancias.getSql().agregarTrasladoInterno(nodo)) {
                    metodos.msgError(this, "Error al guardar el traslado");
                    return;
                }

                //PROCESO GUARDAR VENTA
                for (int i = 0; i < tblProductos.getRowCount(); i++) {
                    Object vectVenta[] = {factura, tblProductos.getValueAt(i, 0), big.getBigDecimal(tblProductos.getValueAt(i, 3).toString().replace(",", ".")),
                        big.getMoneda(tblProductos.getValueAt(i, 2).toString()), tblProductos.getValueAt(i, 4),
                        tblProductos.getValueAt(i, 7), big.getMoneda(tblProductos.getValueAt(i, 10).toString()),
                        tblProductos.getValueAt(i, 5), "PENDIENTE", "", "", tblProductos.getValueAt(i, 6),
                        big.getBigDecimal(tblProductos.getValueAt(i, 8).toString().replace(",", ".")), tblProductos.getValueAt(i, 9), big.getMoneda("0")
                    };

                    ndProdTraslBodega nodoTras = metodos.llenarProdTralBodega(vectVenta);

                    if (!instancias.getSql().agregarTrasladoInterno(nodoTras)) {
                        metodos.msgError(this, "Hubo un problema al guardar el traslado");
                    }

                    /* SACAMOS LA MERCANCIA DE LA BODEGA DE ORIGEN */
                    ndProducto producto = instancias.getSql().getDatosProducto(tblProductos.getValueAt(i, 0).toString(), txtBaseOrigen.getText());
                    double cantidad;
                    double inventario;
                    double fisicoInventario;

                    try {
                        cantidad = Double.parseDouble(producto.getTrasladoInternoSalida().replace(",", "."));
                    } catch (Exception e) {
                        cantidad = 0;
                    }

                    try {
                        inventario = Double.parseDouble(producto.getInventario().replace(",", "."));
                    } catch (Exception e) {
                        inventario = 0;
                    }

                    try {
                        fisicoInventario = Double.parseDouble(producto.getFisicoInventario().replace(",", "."));
                    } catch (Exception e) {
                        fisicoInventario = Double.parseDouble(producto.getInventario().replace(",", "."));
                    }

                    double cant2;
                    try {
                        cant2 = Double.parseDouble(tblProductos.getValueAt(i, 8).toString());
                    } catch (Exception e) {
                        cant2 = Double.parseDouble(tblProductos.getValueAt(i, 8).toString().substring(0, tblProductos.getValueAt(i, 8).toString().length() - 2));
                    }

                    inventario = inventario - cant2;
                    fisicoInventario = fisicoInventario - cant2;
                    double total = cantidad + cant2;

                    String total1 = String.valueOf(df.format(total)).replace(".", ",");
                    String inventario1 = String.valueOf(df.format(inventario)).replace(".", ",");
                    String fisicoInventario1 = String.valueOf(df.format(fisicoInventario)).replace(".", ",");

                    instancias.getSql().modificarInventario("trasladoInternoSalida", total1, tblProductos.getValueAt(i, 0).toString(), txtBaseOrigen.getText());
                    instancias.getSql().modificarInventario("inventario", inventario1, tblProductos.getValueAt(i, 0).toString(), txtBaseOrigen.getText());
                    instancias.getSql().modificarInventario("fisicoInventario", fisicoInventario1, tblProductos.getValueAt(i, 0).toString(), txtBaseOrigen.getText());

                    /* INGRESAMOS LA MERCANCIA A LA BODEGA DE DESTINO */
                    ndProducto producto2 = instancias.getSql().getDatosProducto(tblProductos.getValueAt(i, 0).toString(), txtBaseDestino.getText());
                    double cantidad2;
                    double inventario2;
                    double fisicoInventario2;

                    try {
                        cantidad2 = Double.parseDouble(producto2.getTrasladoInternoEntrada().replace(",", "."));
                    } catch (Exception e) {
                        cantidad2 = 0;
                    }

                    try {
                        inventario2 = Double.parseDouble(producto2.getInventario().replace(",", "."));
                    } catch (Exception e) {
                        inventario2 = 0;
                    }

                    try {
                        fisicoInventario2 = Double.parseDouble(producto2.getFisicoInventario().replace(",", "."));
                    } catch (Exception e) {
                        fisicoInventario2 = Double.parseDouble(producto2.getInventario().replace(",", "."));
                    }

                    double cant3 = Double.parseDouble(tblProductos.getValueAt(i, 8).toString().replace(",", "."));
                    inventario2 = inventario2 + cant3;
                    fisicoInventario2 = fisicoInventario2 + cant3;
                    double total2 = cantidad2 + cant3;

                    String total3 = String.valueOf(df.format(total2)).replace(".", ",");
                    String inventario3 = String.valueOf(df.format(inventario2)).replace(".", ",");
                    String fisicoInventario3 = String.valueOf(df.format(fisicoInventario2)).replace(".", ",");

                    instancias.getSql().modificarInventario("trasladoInternoEntrada", total3, tblProductos.getValueAt(i, 0).toString(), txtBaseDestino.getText());
                    instancias.getSql().modificarInventario("inventario", inventario3, tblProductos.getValueAt(i, 0).toString(), txtBaseDestino.getText());
                    instancias.getSql().modificarInventario("fisicoInventario", fisicoInventario3, tblProductos.getValueAt(i, 0).toString(), txtBaseDestino.getText());

                    if (instancias.getConfiguraciones().isProductosDetallados()) {
                        String cod = "";
                        try {
                            cod = tblProductos.getValueAt(i, 6).toString();
                        } catch (Exception e) {
                        }

                        if (!cod.equals("")) {
                            Object[][] prod = instancias.getSql().getProductosDetalle(cod);
                            String conse = instancias.getSql().getNumConsecutivo("DETALLEPROD")[0].toString();

                            //OJO IMPORTANTE ORGANIZAR ESTA PARTE
                            /*if (!instancias.getSql().agregarDetalladoProducto(conse, prod[0][0].toString(), prod[0][1].toString(),
                                    String.valueOf(df.format(cant2)).replace(".", ","), prod[0][3].toString(), prod[0][4].toString(), prod[0][5].toString(),
                                    prod[0][6].toString(), "DISPONIBLE", factura, metodos.fechaConsulta(metodosGenerales.fecha()), metodosGenerales.hora(),
                                    instancias.getUsuario(), prod[0][7].toString(), prod[0][9].toString(), txtNitBD.getText())) {
                                metodos.msgError(null, "Hubo un problema al guardar el detalle del producto");
                                return;
                            }*/

                            if (!instancias.getSql().aumentarConsecutivo("DETALLEPROD", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("DETALLEPROD")[0]) + 1)) {
                                metodos.msgError(null, "Hubo un problema al guardar en el consecutivo del detalle del producto");
                            }
                        }
                    }
                    /* FIN DE SACAR LA MERCANCIA */

                    // DESCONTAR DEL INVENTARIO DETALLADO //
                    if (instancias.getConfiguraciones().isProductosDetallados()) {
                        String cod = "";
                        try {
                            cod = tblProductos.getValueAt(i, 6).toString();
                        } catch (Exception e) {
                        }

                        if (!cod.equals("")) {

                            String tipo = "";
                            if (producto.getTipoProducto() != null) {
                                if (producto.getTipoProducto().equals("IMEI")) {
                                    tipo = "Imei";
                                } else if (producto.getTipoProducto().equals("Fecha/Lote")) {
                                    tipo = "Fecha/Lote";
                                } else if (producto.getTipoProducto().equals("Color")) {
                                    tipo = "Color";
                                } else if (producto.getTipoProducto().equals("Serial")) {
                                    tipo = "Serial";
                                } else if (producto.getTipoProducto().equals("Talla")) {
                                    tipo = "Talla";
                                } else if (producto.getTipoProducto().equals("ColorTalla")) {
                                    tipo = "ColorTalla";
                                } else if (producto.getTipoProducto().equals("SerialColor")) {
                                    tipo = "SerialColor";
                                } else {
                                    tipo = "";
                                }
                            }

                            if (tipo.equals("Imei") || tipo.equals("Serial") || tipo.equals("SerialColor")) {
                                instancias.getSql().modificarEstadoDetalleProductos(cod, "NO-DISPONIBLE");
                            } else {
                                Double cantidadActual = Double.parseDouble(instancias.getSql().getCantidadProductos(cod).replace(",", "."));
                                cantidadActual = cantidadActual - cant2;
                                String cantidadFinal = String.valueOf(df.format(cantidadActual)).replace(".", ",");
                                //instancias.getSql().modificarCantidadesDetalleProductos(cod, cantidadFinal);
                            }
                        }
                    }
                    // FIN DE DESCONTAR DEL INVENTARIO SEPARADO // 
                }

                //CAMBIAR CONSECUTIVO FACTURA
                if (!instancias.getSql().aumentarConsecutivo("TRASINT", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("TRASINT")[0]) + 1)) {
                    metodos.msgError(this, "Hubo un problema al guardar en el consecutivo del traslado");
                }

                txtTraslado.setText((String) instancias.getSql().getNumConsecutivo("TRASINT")[0]);
                metodos.msgExito(this, "Traslado exitoso");

                btnLimpiarActionPerformed(evt);

                String tipo = instancias.getImpresion();
                if (tipo.equals("pos")) {
                    tipo = "posTraslados";
                } else {
                    tipo = "traslados";
                }

                if (metodos.msgPregunta(this, "¿Desea imprimir?") == 0) {
                    instancias.getReporte().verTraslados(factura, tipo);
                }
            }
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        txtCant.setText(datos[87].toString());
        btnAnular.setEnabled(true);
        txtCodProducto.setEnabled(true);
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png")));
        btnGuardar.setText("GUARDAR");

        txtNitBO.setText("");
        txtNombreBO.setText("");
        txtNitBD.setText("");
        txtNombreBD.setText("");
        txtBaseDestino.setText("");
        txtBaseOrigen.setText("");
        txtValor.setText(this.simbolo + " 0");

        while (tblProductos.getRowCount() > 0) {
            modeloPro.removeRow(0);
        }

        txtUnidades.setText("0");
        txtNitBO.requestFocus();

        txtTraslado.setText((String) instancias.getSql().getNumConsecutivo("TRASINT")[0]);
        txtTraslado.setEditable(false);
        btnGuardar.setEnabled(true);
        btnBusProd.setEnabled(true);
        btnReimprimir.setEnabled(true);
        txtNitBO.setEditable(true);
        txtNitBD.setEditable(true);

        popBorrar.setEnabled(true);
        txtCodProducto.setEnabled(true);
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnReimprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReimprimirActionPerformed
        String consecutivo = "TRASINT-" + metodos.msgIngresarEnter(this, "Documento a reimprimir");

        if (consecutivo.equals("TRASINT-")) {
            return;
        }

        boolean anulado = instancias.getSql().getDocumentoAnulado("bdTrasladosInternos", "Where id='" + consecutivo + "' ");
        if (anulado) {
            metodos.msgError(null, "Este documento se encuentra anulado");
            return;
        }

        String tipo = instancias.getImpresion();
        if (tipo.equals("pos")) {
            tipo = "posTraslados";
        } else {
            tipo = "traslados";
        }

        instancias.getReporte().verTraslados(consecutivo, tipo);
    }//GEN-LAST:event_btnReimprimirActionPerformed

    private void popBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popBorrarActionPerformed
        if (tblProductos.getSelectedRow() > -1) {
            int fila = tblProductos.getSelectedRow();
            String codigo = (String) tblProductos.getValueAt(fila, 0);

            DefaultTableModel modelo = (DefaultTableModel) tblProductos.getModel();
            modelo.removeRow(fila);

            cargarTotales();
        } else {
            metodos.msgAdvertencia(this, "Seleccione un producto");
        }
    }//GEN-LAST:event_popBorrarActionPerformed

    public void ventanaTerceros(String nit) {
        buscClientes buscar = new buscClientes(instancias.getMenu(), rootPaneCheckingEnabled, false, null, "");
        buscar.setLocationRelativeTo(null);
        instancias.setBusClientes(buscar);
        instancias.setCampoActual(txtTraslado);
        txtTraslado.requestFocus();
        buscar.noEncontrado(nit);
        buscar.show();
    }

    private void ventanaTraslados(String cliente) {
        infBuscadorCliente reimpresion = new infBuscadorCliente();
        reimpresion.cargarRegistros(cliente, "traslado", instancias);
    }

    public void limpiarTraslado(int opc) {

        txtNitBO.setText("");
        txtNombreBO.setText("");
        txtNitBD.setText("");
        txtNombreBD.setText("");

        DefaultTableModel y = (DefaultTableModel) tblProductos.getModel();

        int i, j = tblProductos.getRowCount();

        for (i = 0; i < j; i++) {
            y.removeRow(0);
        }

        txtUnidades.setText("0");
        if (opc == 1) {
            txtTraslado.setText("");
        }
    }
    private void txtTrasladoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTrasladoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            ModeloContacto nodo = instancias.getSql().getDatosTercero(txtTraslado.getText());
            if (nodo.getId() != null) {
                ventanaTraslados(nodo.getId());
                return;
            }
            ventanaTerceros(txtTraslado.getText());
        } else {
            limpiarTraslado(0);
        }
    }//GEN-LAST:event_txtTrasladoKeyReleased

    private void lbNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lbNombreKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtCodProducto.requestFocus();
        }
    }//GEN-LAST:event_lbNombreKeyReleased

    private void txtNombreBOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreBOActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreBOActionPerformed

    private void btnAnularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnularActionPerformed
        String consecutivo = "TRASINT-" + metodos.msgIngresarEnter(this, "Documento a anular");

        if (consecutivo.equals("TRASINT-")) {
            return;
        }

        boolean anulado = instancias.getSql().getDocumentoAnulado("bdTrasladosInternos", "Where Id='" + consecutivo + "' ");
        if (anulado) {
            metodos.msgError(null, "Este documento se encuentra anulado");
            return;
        }

        desdeTraslados(consecutivo);

        if (metodos.msgPregunta(this, "¿Anular este traslado?") == 0) {

            for (int i = 0; i < tblProductos.getRowCount(); i++) {

                /* SACAMOS LA MERCANCIA DE LA BODEGA DE ORIGEN */
                ndProducto producto = instancias.getSql().getDatosProducto(tblProductos.getValueAt(i, 0).toString(), txtBaseOrigen.getText());
                double cantidad;
                double inventario;
                double fisicoInventario;

                try {
                    cantidad = Double.parseDouble(producto.getTrasladoInternoSalida().replace(",", "."));
                } catch (Exception e) {
                    cantidad = 0;
                }

                try {
                    inventario = Double.parseDouble(producto.getInventario().replace(",", "."));
                } catch (Exception e) {
                    inventario = 0;
                }

                try {
                    fisicoInventario = Double.parseDouble(producto.getFisicoInventario().replace(",", "."));
                } catch (Exception e) {
                    fisicoInventario = Double.parseDouble(producto.getInventario().replace(",", "."));
                }

                double cant2 = Double.parseDouble(tblProductos.getValueAt(i, 8).toString().replace(",", "."));

                inventario = inventario + cant2;
                fisicoInventario = fisicoInventario + cant2;
                double total = cantidad - cant2;

                String total1 = String.valueOf(df.format(total)).replace(".", ",");
                String inventario1 = String.valueOf(df.format(inventario)).replace(".", ",");
                String fisicoInventario1 = String.valueOf(df.format(fisicoInventario)).replace(".", ",");

                instancias.getSql().modificarInventario("trasladoInternoSalida", total1, tblProductos.getValueAt(i, 0).toString(), txtBaseOrigen.getText());
                instancias.getSql().modificarInventario("inventario", inventario1, tblProductos.getValueAt(i, 0).toString(), txtBaseOrigen.getText());
                instancias.getSql().modificarInventario("fisicoInventario", fisicoInventario1, tblProductos.getValueAt(i, 0).toString(), txtBaseOrigen.getText());
                /* FIN DE SACAR LA MERCANCIA */

                /* INGRESAMOS LA MERCANCIA A LA BODEGA DE DESTINO */
                ndProducto producto2 = instancias.getSql().getDatosProducto(tblProductos.getValueAt(i, 0).toString(), txtBaseDestino.getText());
                double cantidad2;
                double inventario2;
                double fisicoInventario2;

                try {
                    cantidad2 = Double.parseDouble(producto2.getTrasladoInternoEntrada().replace(",", "."));
                } catch (Exception e) {
                    cantidad2 = 0;
                }

                try {
                    inventario2 = Double.parseDouble(producto2.getInventario().replace(",", "."));
                } catch (Exception e) {
                    inventario2 = 0;
                }

                try {
                    fisicoInventario2 = Double.parseDouble(producto2.getFisicoInventario().replace(",", "."));
                } catch (Exception e) {
                    fisicoInventario2 = Double.parseDouble(producto2.getInventario().replace(",", "."));
                }

                double cant3 = Double.parseDouble(tblProductos.getValueAt(i, 8).toString().replace(",", "."));

                inventario2 = inventario2 - cant3;
                fisicoInventario2 = fisicoInventario2 - cant3;
                double total2 = cantidad2 - cant3;

                String total3 = String.valueOf(df.format(total2)).replace(".", ",");
                String inventario3 = String.valueOf(df.format(inventario2)).replace(".", ",");
                String fisicoInventario3 = String.valueOf(df.format(fisicoInventario2)).replace(".", ",");

                instancias.getSql().modificarInventario("trasladoInternoEntrada", total3, tblProductos.getValueAt(i, 0).toString(), txtBaseDestino.getText());
                instancias.getSql().modificarInventario("inventario", inventario3, tblProductos.getValueAt(i, 0).toString(), txtBaseDestino.getText());
                instancias.getSql().modificarInventario("fisicoInventario", fisicoInventario3, tblProductos.getValueAt(i, 0).toString(), txtBaseDestino.getText());
                /* FIN DE SACAR LA MERCANCIA */

                if (instancias.getConfiguraciones().isProductosDetallados()) {
                    String cod = "";
                    try {
                        cod = tblProductos.getValueAt(i, 6).toString();
                    } catch (Exception e) {
                    }

                    if (!cod.equals("")) {
                        String tipo = "";
                        if (producto.getTipoProducto() != null) {
                            if (producto.getTipoProducto().equals("IMEI")) {
                                tipo = "Imei";
                            } else if (producto.getTipoProducto().equals("Fecha/Lote")) {
                                tipo = "Fecha/Lote";
                            } else if (producto.getTipoProducto().equals("Color")) {
                                tipo = "Color";
                            } else if (producto.getTipoProducto().equals("Serial")) {
                                tipo = "Serial";
                            } else if (producto.getTipoProducto().equals("Talla")) {
                                tipo = "Talla";
                            } else if (producto.getTipoProducto().equals("ColorTalla")) {
                                tipo = "ColorTalla";
                            } else if (producto.getTipoProducto().equals("SerialColor")) {
                                tipo = "SerialColor";
                            } else {
                                tipo = "";
                            }
                        }

                        if (tipo.equals("Imei") || tipo.equals("Serial") || tipo.equals("SerialColor")) {
                            instancias.getSql().modificarEstadoDetalleProductos(cod, "DISPONIBLE");
                        } else {
                            Double cantidadActual = Double.parseDouble(instancias.getSql().getCantidadProductos(cod).replace(",", "."));
                            cantidadActual = cantidadActual + cant2;
                            String cantidadFinal = String.valueOf(df.format(cantidadActual)).replace(".", ",");
                            //instancias.getSql().modificarCantidadesDetalleProductos(cod, cantidadFinal);
                        }
                    }
                }
            }

            if (instancias.getConfiguraciones().isProductosDetallados()) {
                instancias.getSql().anularCompraDetalladoInventario(consecutivo);
            }

            if (!instancias.getSql().anularDocumento(consecutivo, "bdTrasladosInternos")) {
                metodos.msgError(this, "Error al anular el traslado");
            }

            metodos.msgExito(this, "Traslado anulado con éxito");
        }
        btnLimpiarActionPerformed(evt);
    }//GEN-LAST:event_btnAnularActionPerformed

    private void txtCantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantActionPerformed

    private void txtCantKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantKeyReleased

    private void btnBusProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBusProdActionPerformed
        ventanaProductos("");
    }//GEN-LAST:event_btnBusProdActionPerformed

    private void txtValorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtValorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValorActionPerformed

    public void desdeTraslados(String traslado) {
        txtTraslado.setText(traslado);

        Object[][] prodTraslados = instancias.getSql().getProductosTrasladosInternos(traslado);

        if (prodTraslados[0][0] != null) {

            txtTraslado.setText(prodTraslados[0][0].toString().replace("TRASB-", ""));
            txtUnidades.setText(prodTraslados[0][4].toString());

            ndBodega nodo = instancias.getSql().getDatosBodega(prodTraslados[0][1].toString());
            if (nodo.getCodigo() != null) {
                txtNitBO.setText(prodTraslados[0][1].toString());
                txtNombreBO.setText(nodo.getNombre());
                txtBaseOrigen.setText(nodo.getBase());
            }

            ndBodega nodo1 = instancias.getSql().getDatosBodega(prodTraslados[0][2].toString());
            if (nodo1.getCodigo() != null) {
                txtNombreBD.setText(nodo1.getNombre());
                txtNitBD.setText(prodTraslados[0][2].toString());
                txtBaseDestino.setText(nodo1.getBase());
            }

            DefaultTableModel modelo = (DefaultTableModel) tblProductos.getModel();
            while (tblProductos.getRowCount() > 0) {
                modelo.removeRow(0);
            }

            for (int i = 0; i < prodTraslados.length; i++) {
                cargarProducto(prodTraslados[i][8].toString(), prodTraslados[i][9].toString(), 1, prodTraslados[i][11].toString(),
                        prodTraslados[i][14].toString(), prodTraslados[i][15].toString(), "", "", "", "");

                tblProductos.setValueAt(big.setMoneda(big.getMoneda(prodTraslados[i][10].toString())), tblProductos.getRowCount() - 1, 2);
                tblProductos.setValueAt(prodTraslados[i][12], tblProductos.getRowCount() - 1, 7);
                tblProductos.removeEditor();

                KeyEvent evento = new KeyEvent(tblProductos, 0, 0, 0, 0);
                evento.setKeyCode(KeyEvent.VK_ENTER);
                tblProductosKeyReleased(evento);
            }

            tblProductos.removeEditor();
            txtCodProducto.requestFocus();
            txtCodProducto.setEnabled(false);
            popBorrar.setEnabled(false);
            txtNitBO.setEditable(false);
            txtNitBD.setEditable(false);
            btnBusProd.setEnabled(false);
            btnGuardar.setEnabled(false);
            btnReimprimir.setEnabled(false);
            btnAnular.setEnabled(false);
        } else {
            metodos.msgError(this, "El traslado no existe");
        }
    }

    public void cargarProducto(String codigo, String cantidad, int plu, String imei, String lote, String idProd, String talla, String color,
            String temp, String fechaVence) {

        if (txtBaseOrigen.getText().equals("")) {
            metodos.msgError(this, "Debe seleccionar la bodega de origen");
            return;
        }

        for (int j = 0; j < tblProductos.getRowCount(); j++) {
            String idPro = "";
            try {
                idPro = tblProductos.getValueAt(j, 5).toString();
            } catch (Exception e) {
            }

            if (!idPro.equals("")) {
                if (idPro.equals(idProd)) {
                    if (!imei.equals("")) {
                        metodos.msgError(this, "El imei '" + imei + "' ya esta cargado.");
                        return;
                    } else {
                        metodos.msgError(this, "Este producto con el lote '" + lote + "' ya se cargó.");
                        return;
                    }
                }
            }
        }

//        for (int j = 0; j < tblProductos.getRowCount(); j++) {
//            if (codigo.equalsIgnoreCase((String) tblProductos.getValueAt(j, 0))) {
//                metodos.msgAdvertencia(this, "El producto ya esta cargado");
//                tblProductos.setColumnSelectionInterval(3, 3);
//                tblProductos.setRowSelectionInterval(j, j);
//                tblProductos.editCellAt(j, 3);
//                tblProductos.transferFocus();
//                txtCodProducto.setText("");
//                return;
//            }
//        }
        ndProducto nodo = null;

        String CodigoProd = "";
        if (codigo.equals("")) {
            CodigoProd = "";
        } else {
            Object[][] listado = instancias.getSql().getCodigosRelacionados(codigo, " where codigo");
            if (listado.length > 0) {
                codigo = listado[0][0].toString();
            }

            nodo = instancias.getSql().getDatosProducto(codigo, txtBaseOrigen.getText());
            if (nodo.getCodigo() != null) {
                CodigoProd = nodo.getCodigo();
            }
        }

        if (!CodigoProd.equals("")) {
            if (instancias.getSql().getProdActivo(nodo.getCodigo())) {
                metodos.msgError(null, "Este producto esta inactivo");
                lbNombre.requestFocus();
                return;
            }

            if (this.plu) {
                this.plu = false;
                int cant = 0;
                if (nodo.isPlu2()) {
                    cant++;
                }
                if (nodo.isPlu3()) {
                    cant++;
                }
                if (nodo.isPlu4()) {
                    cant++;
                }
                if (nodo.getPlu5()) {
                    cant++;
                }
                if (nodo.getPlu6()) {
                    cant++;
                }
                if (nodo.getPlu7()) {
                    cant++;
                }
                if (nodo.getPlu8()) {
                    cant++;
                }
                if (cant > 0) {
                    seleccionarPLU pluu = new seleccionarPLU(null, true, txtBaseOrigen.getText());
                    pluu.setInstancias(instancias, nodo.getCodigo());
                    pluu.setOpc("trasladoInterno");
                    pluu.setVisible(true);
                    return;
                }
            }

            String cant2 = "1";
            String desc = nodo.getDescripcion();
            String lista = nodo.getL1();
            switch (plu) {
                case 2:
                    cant2 = nodo.getCantidad2();
                    desc = nodo.getDescripcion2();
                    lista = nodo.getL2();
                    break;
                case 3:
                    cant2 = nodo.getCantidad3();
                    desc = nodo.getDescripcion3();
                    lista = nodo.getL3();
                    break;
                case 4:
                    cant2 = nodo.getCantidad4();
                    desc = nodo.getDescripcion4();
                    lista = nodo.getL4();
                    break;
                case 5:
                    cant2 = nodo.getCantidad5();
                    desc = nodo.getDescripcion5();
                    lista = nodo.getL5();
                    break;
                case 6:
                    cant2 = nodo.getCantidad6();
                    desc = nodo.getDescripcion6();
                    lista = nodo.getL6();
                    break;
                case 7:
                    cant2 = nodo.getCantidad7();
                    desc = nodo.getDescripcion7();
                    lista = nodo.getL7();
                    break;
                case 8:
                    cant2 = nodo.getCantidad8();
                    desc = nodo.getDescripcion8();
                    lista = nodo.getL8();
                    break;
            }

            String tipo = "";
            if (nodo.getTipoProducto() != null) {
                if (nodo.getTipoProducto().equals("IMEI")) {
                    tipo = "Imei";
                } else if (nodo.getTipoProducto().equals("Fecha/Lote")) {
                    tipo = "Fecha/Lote";
                } else if (nodo.getTipoProducto().equals("Color")) {
                    tipo = "Color";
                } else if (nodo.getTipoProducto().equals("Serial")) {
                    tipo = "Serial";
                } else if (nodo.getTipoProducto().equals("Talla")) {
                    tipo = "Talla";
                } else if (nodo.getTipoProducto().equals("ColorTalla")) {
                    tipo = "ColorTalla";
                } else if (nodo.getTipoProducto().equals("SerialColor")) {
                    tipo = "SerialColor";
                } else {
                    tipo = "";
                }
            }

            if (!tipo.equals("") && idProd.equals("")) {
                dlgCompraDetallada1 compraDetallada = new dlgCompraDetallada1(null, true, tipo, nodo.getIdSistema(), null, "Salida",
                        "trasladoInterno", "bdProductos", txtNitBO.getText());
                compraDetallada.setLocationRelativeTo(null);
                compraDetallada.setVisible(true);
                return;
            } else {

                BigDecimal ultimoCosto = BigDecimal.ZERO;
                try {
                    UltimoPonderado ultimoPonderado = servicioActualizacionPonderado.obtenerUltimoPonderado(nodo.getIdSistema());
                    ultimoCosto = ultimoPonderado.getUltimoCosto();
                } catch (SQLException ex) {
                    Logger.getLogger(VistaInventarioInicial.class.getName()).log(Level.SEVERE, null, ex);
                }

                String cant = nodo.getFisicoInventario().replace(".", ",");
                Double res = 0.0;

                try {
                    res = Double.parseDouble(cant.replace(",", ".")) - Double.parseDouble(cantidad);
                } catch (Exception e) {
                    try {
                        res = big.getBigDecimal(cant.replace(",", ".")).subtract(big.getBigDecimal(cantidad.replace(",", "."))).doubleValue();
                    } catch (Exception ex) {
                        res = Double.parseDouble(cant.replace(",", ".")) - Integer.parseInt(cantidad);
                    }
                }

                String detalle = "";
                if (!imei.equals("")) {
                    detalle = imei;
                }

                if (!color.equals("")) {
                    if (detalle.equals("")) {
                        detalle = color;
                    } else {
                        detalle = detalle + "-" + color;
                    }
                }

                if (!talla.equals("")) {
                    if (detalle.equals("")) {
                        detalle = talla;
                    } else {
                        detalle = detalle + "-" + talla;
                    }
                }

                if (!lote.equals("")) {
                    detalle = lote;
                }

                if (!fechaVence.equals("")) {
                    if (detalle.equals("")) {
                        detalle = fechaVence;
                    } else {
                        detalle = detalle + "-" + fechaVence;
                    }
                }

                modeloPro.addRow(new Object[]{nodo.getIdSistema(), nodo.getDescripcion(), big.setMoneda(ultimoCosto), cantidad, detalle, "", idProd, "",
                    (big.getBigDecimal(cant2).multiply(big.getMoneda(cantidad))), plu, "", cant, res});

                tblProductos.setColumnSelectionInterval(7, 7);
                tblProductos.setRowSelectionInterval(tblProductos.getRowCount() - 1, tblProductos.getRowCount() - 1);
                tblProductos.editCellAt(tblProductos.getRowCount() - 1, 7);
                tblProductos.transferFocus();

                calcularFila(tblProductos.getRowCount() - 1);
                cargarTotales();
                txtCodProducto.setText("");
                txtCant.setText(datos[87].toString());
                return;
            }
        }
        ventanaProductos(codigo);
    }

    public void calcularFila(int fila) {
        BigDecimal valor, cantidad;
        valor = big.getMoneda(String.valueOf(tblProductos.getValueAt(fila, 2)));
        tblProductos.setValueAt(big.setMoneda(valor), fila, 2);
        cantidad = big.getMoneda(String.valueOf(tblProductos.getValueAt(fila, 3)).replace(".", ","));
        tblProductos.setValueAt(big.setNumero(cantidad), fila, 3);
        tblProductos.setValueAt(big.setMoneda(valor.multiply(cantidad)), fila, 10);
    }

    public void ventanaProductos(String codigo) {

        String base = "";
        if (txtBaseOrigen.getText().equals("bdProductosBodega1")) {
            base = "productos1Bodega1";
        } else if (txtBaseOrigen.getText().equals("bdProductosBodega2")) {
            base = "productos1Bodega2";
        } else if (txtBaseOrigen.getText().equals("bdProductosBodega3")) {
            base = "productos1Bodega3";
        } else if (txtBaseOrigen.getText().equals("bdProductosBodega4")) {
            base = "productos1Bodega4";
        } else {
            base = "productos1";
        }

        if (txtNombreBO.getText().equals("")) {
            metodos.msgAdvertenciaAjustado(this, "Seleccionar una bodega");
            return;
        }

        buscProductos buscar = new buscProductos(null, true, false, "", base);
        buscar.setOpc("trasladoInterno");
        buscar.setLocationRelativeTo(null);
        instancias.setBusProductos(buscar);
        instancias.setCampoActual(txtCodProducto);
        txtCodProducto.requestFocus();
//        buscar.setInstancia(instancias);
        buscar.noEncontrado(codigo.replace("'", "//"));
        buscar.show();
    }

    public void ventanaTerceros(String nit, JTextField campo) {
        buscBodegas buscar = new buscBodegas(instancias.getMenu(), rootPaneCheckingEnabled, "INTERNA");
        buscar.setLocationRelativeTo(null);
        instancias.setBuscBodegas(buscar);
        instancias.setCampoActual(campo);
        campo.requestFocus();
        buscar.noEncontrado(nit);
        buscar.show();
    }

    public void cargarTotales() {
        BigDecimal cantidad = BigDecimal.ZERO;
        BigDecimal total = BigDecimal.ZERO;

        for (int i = 0; i < tblProductos.getRowCount(); i++) {
            BigDecimal num = big.getBigDecimal(tblProductos.getValueAt(i, 3).toString().replace(",", "."));
            cantidad = cantidad.add(num);
            total = total.add(big.getMoneda(tblProductos.getValueAt(i, 10).toString().replace(",", ".")));
        }

        txtValor.setText(big.setMoneda(total));
        txtUnidades.setText(cantidad + "");
    }

    public void cargarProductos(Object[][] productos) {
        String cantEstablecida = txtCant.getText();
        for (int i = 0; i < productos.length; i++) {
            String codigo = productos[i][0].toString();
            String cantidad = productos[i][1].toString();
            if (cantidad.equals("0")) {
                cantidad = cantEstablecida;
            }
            cargarProducto(codigo, cantidad, 1, "", "", "", "", "", "", "");
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnular;
    private javax.swing.JButton btnBusProd;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnReimprimir;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JLabel lbFacturaNo;
    private javax.swing.JLabel lbNit;
    private javax.swing.JLabel lbNit1;
    private javax.swing.JLabel lbNit2;
    private javax.swing.JLabel lbNit3;
    private javax.swing.JLabel lbNit5;
    private javax.swing.JLabel lbNit6;
    private javax.swing.JLabel lbNombre;
    private javax.swing.JLabel lbNombre1;
    private javax.swing.JPanel pnlFormulario;
    private javax.swing.JPanel pnlInvisible;
    private javax.swing.JMenuItem popBorrar;
    private javax.swing.JScrollPane scrFormulario;
    private javax.swing.JScrollPane scrProductos;
    private javax.swing.JTable tblProductos;
    private javax.swing.JTextField txtBaseDestino;
    private javax.swing.JTextField txtBaseOrigen;
    private javax.swing.JTextField txtCant;
    private javax.swing.JTextField txtCodProducto;
    private javax.swing.JTextField txtNitBD;
    private javax.swing.JTextField txtNitBO;
    private javax.swing.JTextField txtNombreBD;
    private javax.swing.JTextField txtNombreBO;
    private javax.swing.JTextField txtTraslado;
    private javax.swing.JTextField txtUnidades;
    private javax.swing.JTextField txtValor;
    // End of variables declaration//GEN-END:variables
}
