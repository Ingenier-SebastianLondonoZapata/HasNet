package formularios.productos;

import Vista.Productos.VistaBuscadorProductos;
import Vista.Productos.VistaSeleccionarPLU;
import inventario.vista.VistaMovimientoDetalleProducto;
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
import java.math.RoundingMode;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;

public class infPrestamos extends javax.swing.JInternalFrame implements Vista.Productos.ReceptorProductoSalida {

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

    public infPrestamos() {
        initComponents();
        modeloPro = (DefaultTableModel) tblProductos.getModel();

        instancias = Instancias.getInstancias();

        simbolo = instancias.getSimbolo();
        txtTraslado.setText((String) instancias.getSql().getNumConsecutivo("TRASB")[0]);
        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
        dimBarra = Barra.getPreferredSize();
        Barra.setSize(0, 0);
        Barra.setPreferredSize(new Dimension(0, 0));
        setBorder(null);
        repaint();

        if (instancias.getUsuario().equals("ADMIN")) {
            tblProductos.getColumnModel().getColumn(7).setMinWidth(120);
            tblProductos.getColumnModel().getColumn(7).setPreferredWidth(120);
            tblProductos.getColumnModel().getColumn(7).setMaxWidth(120);
        } else {
            tblProductos.getColumnModel().getColumn(7).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(7).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(7).setMaxWidth(0);
        }

        if (!instancias.getConfiguraciones().isProductosDetallados()) {
            tblProductos.getColumnModel().getColumn(5).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(5).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(5).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(6).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(6).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(6).setMaxWidth(0);
        }

        consultarMaestros();
        txtCant.setText(datos[87].toString());

        pnlFormulario.registerKeyboardAction(accion("guardar"), "guardar", KeyStroke.getKeyStroke(KeyEvent.VK_G, Event.CTRL_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
        pnlFormulario.registerKeyboardAction(accion("limpiar"), "limpiar", KeyStroke.getKeyStroke(KeyEvent.VK_L, Event.CTRL_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
        pnlFormulario.registerKeyboardAction(accion("productos"), "productos", KeyStroke.getKeyStroke(KeyEvent.VK_P, Event.CTRL_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
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
        lbNit4 = new javax.swing.JLabel();
        txtDiasPlazo = new javax.swing.JTextField();
        lbNit3 = new javax.swing.JLabel();
        txtValor = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnReimprimir = new javax.swing.JButton();
        btnAnular = new javax.swing.JButton();
        btnBuscTerceros = new javax.swing.JButton();
        scrProductos = new javax.swing.JScrollPane();
        tblProductos = new javax.swing.JTable();
        lbNit5 = new javax.swing.JLabel();
        txtCodProducto = new javax.swing.JTextField();
        txtDiasPlazoFact = new javax.swing.JTextField();
        lbNit6 = new javax.swing.JLabel();
        txtCant = new javax.swing.JTextField();
        btnBusProd = new javax.swing.JButton();

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
        lbFacturaNo.setText("Prestamo No.");
        lbFacturaNo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lbFacturaNo.setOpaque(true);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Bodega origen", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 14))); // NOI18N

        lbNombre.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbNombre.setText("Nombre:");
        lbNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lbNombreKeyReleased(evt);
            }
        });

        lbNit.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbNit.setText("Nit:     *");

        txtNombreBO.setEditable(false);
        txtNombreBO.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtNombreBO.setName("Nombre"); // NOI18N
        txtNombreBO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreBOActionPerformed(evt);
            }
        });

        txtNitBO.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
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
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNitBO, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                    .addComponent(txtNombreBO))
                .addGap(11, 11, 11))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNitBO)
                    .addComponent(lbNit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNombreBO)
                    .addComponent(lbNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Bodega destino", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 14))); // NOI18N

        lbNombre1.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbNombre1.setText("Nombre:");

        lbNit1.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbNit1.setText("Nit:        *");

        txtNombreBD.setEditable(false);
        txtNombreBD.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtNombreBD.setName("Nombre"); // NOI18N

        txtNitBD.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
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
                .addGap(5, 5, 5)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNitBD, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                    .addComponent(txtNombreBD))
                .addGap(14, 14, 14))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNitBD)
                    .addComponent(lbNit1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNombreBD)
                    .addComponent(lbNombre1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Valores", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 16))); // NOI18N

        lbNit2.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbNit2.setText("Unidades:");

        txtUnidades.setEditable(false);
        txtUnidades.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtUnidades.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtUnidades.setText("0");
        txtUnidades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUnidadesActionPerformed(evt);
            }
        });

        lbNit4.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbNit4.setText("D. Plazo:");

        txtDiasPlazo.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtDiasPlazo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDiasPlazo.setText("0");
        txtDiasPlazo.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtDiasPlazo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDiasPlazoActionPerformed(evt);
            }
        });

        lbNit3.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit3.setText("Valor:");

        txtValor.setEditable(false);
        txtValor.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtValor.setText("0");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbNit4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbNit2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtUnidades, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                    .addComponent(txtDiasPlazo))
                .addGap(18, 18, 18)
                .addComponent(lbNit3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbNit2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtUnidades))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbNit4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtDiasPlazo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(lbNit3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtValor, javax.swing.GroupLayout.Alignment.LEADING)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        btnBuscTerceros.setBackground(new java.awt.Color(204, 204, 204));
        btnBuscTerceros.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnBuscTerceros.setText("PRESTAMOS PENDIENTES");
        btnBuscTerceros.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnBuscTerceros.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscTerceros.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnBuscTerceros.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnBuscTerceros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscTercerosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBuscTerceros, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLimpiar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnReimprimir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAnular, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnBuscTerceros, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLimpiar)
                            .addComponent(btnReimprimir)
                            .addComponent(btnAnular))))
                .addGap(5, 5, 5))
        );

        tblProductos.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        tblProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Descripción", "Valor/Unit", "Cant", "Total", "Detalle", "Lote", "Costo", "idProd", "Nota", "cant2", "plu", "Inv. Inicial", "Inv. Final", "iva", "talla"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true, false, false, false, false, false, true, false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblProductos.setComponentPopupMenu(jPopupMenu1);
        tblProductos.setRowHeight(24);
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
            tblProductos.getColumnModel().getColumn(2).setPreferredWidth(120);
            tblProductos.getColumnModel().getColumn(2).setMaxWidth(150);
            tblProductos.getColumnModel().getColumn(3).setMinWidth(65);
            tblProductos.getColumnModel().getColumn(3).setPreferredWidth(50);
            tblProductos.getColumnModel().getColumn(3).setMaxWidth(80);
            tblProductos.getColumnModel().getColumn(4).setMinWidth(100);
            tblProductos.getColumnModel().getColumn(4).setPreferredWidth(120);
            tblProductos.getColumnModel().getColumn(4).setMaxWidth(150);
            tblProductos.getColumnModel().getColumn(5).setMinWidth(200);
            tblProductos.getColumnModel().getColumn(5).setPreferredWidth(250);
            tblProductos.getColumnModel().getColumn(5).setMaxWidth(280);
            tblProductos.getColumnModel().getColumn(6).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(6).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(6).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(7).setMinWidth(100);
            tblProductos.getColumnModel().getColumn(7).setPreferredWidth(120);
            tblProductos.getColumnModel().getColumn(7).setMaxWidth(150);
            tblProductos.getColumnModel().getColumn(8).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(8).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(8).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(10).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(10).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(10).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(11).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(11).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(11).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(12).setMinWidth(75);
            tblProductos.getColumnModel().getColumn(12).setPreferredWidth(75);
            tblProductos.getColumnModel().getColumn(12).setMaxWidth(120);
            tblProductos.getColumnModel().getColumn(13).setMinWidth(75);
            tblProductos.getColumnModel().getColumn(13).setPreferredWidth(75);
            tblProductos.getColumnModel().getColumn(13).setMaxWidth(120);
            tblProductos.getColumnModel().getColumn(14).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(14).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(14).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(15).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(15).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(15).setMaxWidth(0);
        }

        lbNit5.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbNit5.setText("Cant:");

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

        txtDiasPlazoFact.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        txtDiasPlazoFact.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDiasPlazoFact.setText("0");
        txtDiasPlazoFact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDiasPlazoFactActionPerformed(evt);
            }
        });

        lbNit6.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbNit6.setText("Productos:");

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(lbNit5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCant, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(lbNit6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCodProducto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBusProd, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(scrProductos, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbFacturaNo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTraslado, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(20, 20, 20)
                .addComponent(txtDiasPlazoFact, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(lbFacturaNo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTraslado, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDiasPlazoFact, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5)
                .addComponent(scrProductos, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCodProducto)
                            .addComponent(txtCant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(lbNit5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbNit6, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnBusProd, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );

        javax.swing.GroupLayout pnlFormularioLayout = new javax.swing.GroupLayout(pnlFormulario);
        pnlFormulario.setLayout(pnlFormularioLayout);
        pnlFormularioLayout.setHorizontalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        pnlFormularioLayout.setVerticalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        scrFormulario.setViewportView(pnlFormulario);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrFormulario, javax.swing.GroupLayout.DEFAULT_SIZE, 1047, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrFormulario)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblProductosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblProductosKeyReleased
        BigDecimal valor, cantidad, iva, total, cantFacturar, rest, totalN;

        int fila = tblProductos.getSelectedRow(), i = 3, j = 1;

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            //Si se esta cambiando el campo de cantidad

            if (tblProductos.getSelectedColumn() == 2) {
                tblProductos.setValueAt(big.setMoneda(big.getMoneda(tblProductos.getValueAt(fila, 2).toString())), fila, 2);
                tblProductos.editCellAt(tblProductos.getSelectedRow(), 3);
                tblProductos.setColumnSelectionInterval(3, 3);
                tblProductos.transferFocus();
            } else if (tblProductos.getSelectedColumn() == 3) {
                tblProductos.editCellAt(tblProductos.getSelectedRow(), 9);
                tblProductos.setColumnSelectionInterval(9, 9);
                tblProductos.transferFocus();
            } else if (tblProductos.getSelectedColumn() == 9) {
                txtCodProducto.requestFocus();
            }

            try {
                int res = big.getBigDecimal(big.getMoneda((String) tblProductos.getValueAt(fila, i))).compareTo(big.getBigDecimal("1"));
                if (res == -1 || String.valueOf(tblProductos.getValueAt(fila, i)).equals("")) {
                    tblProductos.setValueAt(j, tblProductos.getSelectedRow(), i);
                }
            } catch (NumberFormatException e) {
                tblProductos.setValueAt(j, tblProductos.getSelectedRow(), i);
            }

            ndProducto nodo = instancias.getSql().getDatosProducto(tblProductos.getValueAt(fila, 0).toString(), "bdProductos");

            valor = big.getMoneda(String.valueOf(tblProductos.getValueAt(fila, 2)));
            cantidad = big.getMoneda(String.valueOf(tblProductos.getValueAt(fila, 3)).replace(".", ","));
            total = valor.multiply(cantidad);

            iva = total.multiply(big.getBigDecimal(nodo.getIva()));
            iva = iva.divide(big.getBigDecimal("100"), 3, RoundingMode.CEILING);
            total = total.add(iva);

            tblProductos.setValueAt(big.setMoneda(total), fila, 4);
            tblProductos.setValueAt(big.setMoneda(iva), fila, 14);

            tblProductos.setValueAt(big.setNumero(cantidad), fila, 3);

            switch (tblProductos.getValueAt(fila, 11).toString()) {
                case "1":
                    tblProductos.setValueAt(cantidad, fila, 10);
                    break;
                case "2":
                    tblProductos.setValueAt(cantidad.multiply(big.getBigDecimal(nodo.getCantidad2())), fila, 10);
                    break;
                case "3":
                    tblProductos.setValueAt(cantidad.multiply(big.getBigDecimal(nodo.getCantidad3())), fila, 10);
                    break;
                case "4":
                    tblProductos.setValueAt(cantidad.multiply(big.getBigDecimal(nodo.getCantidad4())), fila, 10);
                    break;
            }

            if (txtNombreBO.getText().equals("BODEGA PRINCIPAL")) {
                BigDecimal num1 = big.getMoneda(tblProductos.getValueAt(fila, 12).toString());
                BigDecimal num2 = big.getBigDecimal(tblProductos.getValueAt(fila, 3).toString().replace(",", "."));
                BigDecimal totalInv = num1.subtract(num2);
                tblProductos.setValueAt(big.setNumero(totalInv), fila, 13);
            } else {
                BigDecimal num1 = big.getMoneda(tblProductos.getValueAt(fila, 12).toString());
                BigDecimal num2 = big.getBigDecimal(tblProductos.getValueAt(fila, 3).toString().replace(",", "."));
                BigDecimal totalInv = num1.add(num2);
                tblProductos.setValueAt(big.setNumero(totalInv), fila, 13);
            }

            cargarTotales();
        }
    }//GEN-LAST:event_tblProductosKeyReleased

    private void txtCodProductoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodProductoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String codigo = txtCodProducto.getText();
            plu = true;
            cargarProducto(codigo.replace("'", "//"), Utilidades.Utilidades.convertirBigDecimal(txtCant.getText()), 1, "", "", "", "", "", "", "");
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
                txtNitBD.requestFocus();
                return;
            }

            ventanaTerceros(txtNitBO.getText(), txtNitBO);

        } else if (!txtNombreBO.getText().equals("")) {
            txtNombreBO.setText("");
            txtNombreBD.setText("");
            txtNitBD.setText("");
        }
    }//GEN-LAST:event_txtNitBOKeyReleased

    private void tblProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductosMouseClicked

    }//GEN-LAST:event_tblProductosMouseClicked

    private void tblProductosMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductosMouseReleased

    }//GEN-LAST:event_tblProductosMouseReleased

    private void txtNitBDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNitBDKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            if (txtNombreBO.getText().equals("")) {
                metodos.msgError(this, "Debe cargar primero la bodega de origen");
                return;
            }

            if (txtNitBD.getText().equals(txtNitBO.getText()) && !txtNitBD.getText().equals("")) {
                metodos.msgError(this, "La bodega de origen no puede ser igual a la bodega de destino");
                return;
            }

            ndBodega nodo = instancias.getSql().getDatosBodega(txtNitBD.getText());

            if (nodo.getCodigo() != null) {
                txtNombreBD.setText(nodo.getNombre());
                txtCodProducto.requestFocus();
                return;
            }

            ventanaTerceros1(txtNitBD.getText(), txtNitBD);

        } else if (!txtNombreBD.getText().equals("")) {
            txtNombreBD.setText("");
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
        if (btnGuardar.getText().equals("GUARDAR")) {

            if (tblProductos.getRowCount() == 0) {
                metodos.msgError(this, "No ha añadido ningun producto");
            } else {
                Object[] campos = {txtNombreBD, txtNombreBO};
                String faltantes = metodos.camposVacios(campos);

                if (!faltantes.equals("")) {
                    metodos.msgAdvertencia(this, "No puede continuar, faltan los siguientes campos: " + faltantes);
                    return;
                }

                try {
                    if (Integer.parseInt(txtDiasPlazo.getText()) <= 0) {
                        metodos.msgAdvertencia(this, "El prestamo no tiene dias de plazo");
                    }
                } catch (Exception e) {
                    metodos.msgAdvertencia(this, "El prestamo no tiene dias de plazo");
                }

                int xyz = tblProductos.getRowCount();
                if (xyz > 0) {
                    for (int i = 0; i < xyz; i++) {
                        if (tblProductos.getValueAt(i, 3).equals("0")) {
                            metodos.msgError(this, "Las cantidades deben de ser mayor a 0");
                            return;
                        }

                        if (tblProductos.getValueAt(i, 2).equals(this.simbolo + " 0")) {
                            metodos.msgError(this, "Los valores deben de ser mayor a " + this.simbolo + " 0");
                            return;
                        }

                        tblProductos.setColumnSelectionInterval(0, 0);
                        tblProductos.setRowSelectionInterval(i, i);
                        KeyEvent x = new KeyEvent(this, WIDTH, WIDTH, WIDTH, KeyEvent.VK_ENTER);
                        tblProductosKeyReleased(x);
                    }
                }

                //numFactura, cliente, vendedor, red, fechaFactura, fechaVencimiento, 
                //Efectivo, Nc, Cheque, Targeta, Comprobante, Cotizacion, Anula, Anulada, Credito, //total, descuentos, iva, subtotal, cxc, usuario;
                if (metodos.msgPregunta(this, "¿Desea continuar?") == 0) {

                    String factura = "TRASB-" + instancias.getSql().getNumConsecutivo("TRASB")[0];

                    //PROCESO GUARDAR REGISTRO EN CAJA
                    String dias = txtDiasPlazo.getText();
                    if (dias.equals("")) {
                        dias = "0";
                    }

                    Double unidades = 0.0;
                    try {
                        unidades = Double.parseDouble(txtUnidades.getText());
                    } catch (Exception e) {
                        unidades = Double.parseDouble(txtUnidades.getText().replace(".", ""));
                    }

                    Object[] vector = {factura, txtNitBO.getText(), txtNitBD.getText(), metodos.fechaConsulta(metodosGenerales.fecha()), String.valueOf(unidades),
                        big.getMoneda(txtValor.getText()), "", "", instancias.getUsuario(), instancias.getTerminal(), dias, "PENDIENTE",
                        metodosGenerales.hora()};

                    ndTrasladoBodega nodo = metodos.llenarTrasladoBodega(vector);

                    if (!instancias.getSql().agregarTrasladoBodega(nodo)) {
                        metodos.msgError(this, "Hubo un problema al guardar el prestamo");
                        return;
                    }

                    //PROCESO GUARDAR VENTA
                    for (int i = 0; i < tblProductos.getRowCount(); i++) {
                        Object vectVenta[] = {factura, tblProductos.getValueAt(i, 0),
                            big.getBigDecimal(tblProductos.getValueAt(i, 3).toString().replace(",", ".")),
                            big.getMoneda((String) tblProductos.getValueAt(i, 2)), tblProductos.getValueAt(i, 5),
                            tblProductos.getValueAt(i, 9), big.getMoneda((String) tblProductos.getValueAt(i, 4)),
                            tblProductos.getValueAt(i, 6), "PENDIENTE", "", "", tblProductos.getValueAt(i, 8),
                            big.getBigDecimal(tblProductos.getValueAt(i, 10).toString().replace(",", ".")),
                            tblProductos.getValueAt(i, 11), big.getMoneda((String) tblProductos.getValueAt(i, 14))
                        };

                        ndProdTraslBodega nodoTras = metodos.llenarProdTralBodega(vectVenta);

                        if (!instancias.getSql().agregarTrasladoBodega(nodoTras)) {
                            metodos.msgError(this, "Hubo un problema al guardar el prestamo");
                        }

                        /* SACAMOS LA MERCANCIA DE LA BODEGA DE ORIGEN */
                        ndProducto producto = instancias.getSql().getDatosProducto(tblProductos.getValueAt(i, 0).toString(), "bdProductos");

                        String tipoMovimiento = "";
                        if (txtNombreBO.getText().equals("BODEGA PRINCIPAL")) {
                            tipoMovimiento = "Resta";
                        } else {
                            tipoMovimiento = "Suma";
                        }

                        double cantidad;
                        double fisicoInventario;

                        try {
                            cantidad = Double.parseDouble(producto.getTrasladoBod().replace(",", "."));
                        } catch (Exception e) {
                            cantidad = 0;
                        }

                        try {
                            fisicoInventario = Double.parseDouble(producto.getFisicoInventario().replace(",", "."));
                        } catch (Exception e) {
                            fisicoInventario = Double.parseDouble(producto.getInventario().replace(",", "."));
                        }

                        double cant2 = Double.parseDouble(tblProductos.getValueAt(i, 10).toString().replace(",", "."));

                        double total = 0.0;
                        if (tipoMovimiento.equals("Resta")) {
                            total = cantidad + cant2;
                            fisicoInventario = fisicoInventario - cant2;
                        } else {
                            total = cantidad - cant2;
                            fisicoInventario = fisicoInventario + cant2;
                        }

                        String total1 = String.valueOf(df.format(total)).replace(".", ",");
                        String fisicoInventario1 = String.valueOf(df.format(fisicoInventario)).replace(".", ",");

                        instancias.getSql().modificarInventario("trasladoBod", total1, tblProductos.getValueAt(i, 0).toString(), "bdProductos");
                        instancias.getSql().modificarInventario("fisicoInventario", fisicoInventario1, tblProductos.getValueAt(i, 0).toString(), "bdProductos");

                        // DESCONTAR DEL INVENTARIO DETALLADO //
                        if (instancias.getConfiguraciones().isProductosDetallados()) {
                            String cod = "";
                            try {
                                cod = tblProductos.getValueAt(i, 8).toString();
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
                                    instancias.getSql().modificarEstadoDetalleProductos(cod, "PRESTADO");
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
                    if (!instancias.getSql().aumentarConsecutivo("TRASB", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("TRASB")[0]) + 1)) {
                        metodos.msgError(this, "Hubo un problema al guardar en el consecutivo del traslado");
                    }

                    txtTraslado.setText((String) instancias.getSql().getNumConsecutivo("TRASB")[0]);
                    metodos.msgExito(this, "Prestamo exitoso");
                    btnLimpiarActionPerformed(evt);

                    if (metodos.msgPregunta(this, "¿Desea imprimir?") == 0) {

                        String tipo = instancias.getImpresion();
                        if (tipo.equals("pos")) {
                            tipo = "posPrestamos";
                        } else {
                            tipo = "prestamos";
                        }

                        instancias.getReporte().verPrestamos(factura, tipo);
                    }
                }
            }
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        txtCant.setText(datos[87].toString());

        txtCodProducto.setEnabled(true);
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png")));
        btnGuardar.setText("GUARDAR");

        txtDiasPlazo.setText("");
        txtNitBO.setText("");
        txtNombreBO.setText("");
        txtNitBD.setText("");
        txtNombreBD.setText("");

        while (tblProductos.getRowCount() > 0) {
            modeloPro.removeRow(0);
        }

        txtValor.setText(this.simbolo + " 0");
        txtUnidades.setText("0");
        txtNitBO.requestFocus();

        txtTraslado.setText((String) instancias.getSql().getNumConsecutivo("TRASB")[0]);
        txtTraslado.setEditable(false);
        btnGuardar.setEnabled(true);
        btnBusProd.setEnabled(true);
        txtDiasPlazo.setEnabled(true);
        btnGuardar.setEnabled(true);
        btnReimprimir.setEnabled(true);
        btnAnular.setEnabled(true);
        txtNitBO.setEditable(true);
        txtNitBD.setEditable(true);

        popBorrar.setEnabled(true);
        txtCodProducto.setEnabled(true);

        tblProductos.getColumnModel().getColumn(12).setMinWidth(75);
        tblProductos.getColumnModel().getColumn(12).setPreferredWidth(75);
        tblProductos.getColumnModel().getColumn(12).setMaxWidth(120);
        tblProductos.getColumnModel().getColumn(13).setMinWidth(75);
        tblProductos.getColumnModel().getColumn(13).setPreferredWidth(75);
        tblProductos.getColumnModel().getColumn(13).setMaxWidth(120);

        tblProductos.getColumnModel().getColumn(12).setMinWidth(75);
        tblProductos.getColumnModel().getColumn(12).setPreferredWidth(75);
        tblProductos.getColumnModel().getColumn(12).setMaxWidth(120);
        tblProductos.getColumnModel().getColumn(13).setMinWidth(75);
        tblProductos.getColumnModel().getColumn(13).setPreferredWidth(75);
        tblProductos.getColumnModel().getColumn(13).setMaxWidth(120);
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnAnularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnularActionPerformed
        String consecutivo = "TRASB-" + metodos.msgIngresarEnter(this, "Documento a anular");

        if (consecutivo.equals("TRASB-")) {
            return;
        }

        boolean anulado = instancias.getSql().getDocumentoAnulado("bdTrasladosBodegas", "Where id='" + consecutivo + "' ");
        if (anulado) {
            metodos.msgError(null, "El documento ya está anulado");
            return;
        }

        String estadoDoc = instancias.getSql().getEstado("bdTrasladosBodegas", "Where id ='" + consecutivo + "' ");
        if (estadoDoc.equals("FINALIZADO")) {
            metodos.msgAdvertenciaAjustado(null, "El documento ya está finalizado");
            return;
        }

        desdeTraslados(consecutivo);

        if (metodos.msgPregunta(this, "¿Anular este prestamo?") == 0) {

            if (!instancias.getSql().anularDocumento(consecutivo, "bdTrasladosBodegas")) {
                metodos.msgError(this, "Error al anular el prestamos");
            } else {
                instancias.getSql().anulacionPrestamo(consecutivo);
            }

            for (int i = 0; i < tblProductos.getRowCount(); i++) {
                String cod = "";
                try {
                    cod = tblProductos.getValueAt(i, 8).toString();
                } catch (Exception e) {
                }

                if (cod.equals("")) {
                    instancias.getSql().devolucionTraslado1("TRASB-" + txtTraslado.getText(), tblProductos.getValueAt(i, 0).toString(), tblProductos.getValueAt(i, 3).toString(), "devueltos");
                } else {
                    instancias.getSql().devolucionTraslado("TRASB-" + txtTraslado.getText(), cod, tblProductos.getValueAt(i, 3).toString(), "devueltos");
                }

                String tipoMovimiento = "";
                if (txtNombreBO.getText().equals("BODEGA PRINCIPAL")) {
                    tipoMovimiento = "Resta";
                } else {
                    tipoMovimiento = "Suma";
                }

                ndProducto producto = instancias.getSql().getDatosProducto(tblProductos.getValueAt(i, 0).toString(), "bdProductos");
                double cantidad;
                double fisicoInventario;

                try {
                    cantidad = Double.parseDouble(producto.getTrasladoBod().replace(",", "."));
                } catch (Exception e) {
                    cantidad = 0;
                }

                try {
                    fisicoInventario = Double.parseDouble(producto.getFisicoInventario().replace(",", "."));
                } catch (Exception e) {
                    fisicoInventario = Double.parseDouble(producto.getInventario().replace(",", "."));
                }

                double cant2 = Double.parseDouble(tblProductos.getValueAt(i, 10).toString().replace(",", "."));

                double total = 0.0;
                if (tipoMovimiento.equals("Resta")) {
                    total = cantidad - cant2;
                    fisicoInventario = fisicoInventario + cant2;
                } else {
                    total = cantidad + cant2;
                    fisicoInventario = fisicoInventario - cant2;
                }

                String total1 = String.valueOf(df.format(total)).replace(".", ",");
                String fisicoInventario1 = String.valueOf(df.format(fisicoInventario)).replace(".", ",");

                instancias.getSql().modificarInventario("trasladoBod", total1, tblProductos.getValueAt(i, 0).toString(), "bdProductos");
                instancias.getSql().modificarInventario("fisicoInventario", fisicoInventario1, tblProductos.getValueAt(i, 0).toString(), "bdProductos");

                // DESCONTAR DEL INVENTARIO DETALLADO //
                if (!cod.equals("")) {

                    String tipo1 = "";
                    if (producto.getTipoProducto() != null) {
                        if (producto.getTipoProducto().equals("IMEI")) {
                            tipo1 = "Imei";
                        } else if (producto.getTipoProducto().equals("Fecha/Lote")) {
                            tipo1 = "Fecha/Lote";
                        } else if (producto.getTipoProducto().equals("Color")) {
                            tipo1 = "Color";
                        } else if (producto.getTipoProducto().equals("Serial")) {
                            tipo1 = "Serial";
                        } else if (producto.getTipoProducto().equals("Talla")) {
                            tipo1 = "Talla";
                        } else if (producto.getTipoProducto().equals("ColorTalla")) {
                            tipo1 = "ColorTalla";
                        } else if (producto.getTipoProducto().equals("SerialColor")) {
                            tipo1 = "SerialColor";
                        } else {
                            tipo1 = "";
                        }
                    }

                    if (tipo1.equals("Imei") || tipo1.equals("Serial") || tipo1.equals("SerialColor")) {
                        instancias.getSql().modificarEstadoDetalleProductos(cod, "DISPONIBLE");
                    } else {
                        Double cantidadActual = Double.parseDouble(instancias.getSql().getCantidadProductos(cod).replace(",", "."));
                        cantidadActual = cantidadActual + cant2;
                        String cantidadFinal = String.valueOf(df.format(cantidadActual)).replace(".", ",");
                        //instancias.getSql().modificarCantidadesDetalleProductos(cod, cantidadFinal);
                    }
                }
                // FIN DE DESCONTAR DEL INVENTARIO SEPARADO // 
            }
            metodos.msgExito(this, "Prestamo anulado con éxito");
        }
        btnLimpiarActionPerformed(evt);
    }//GEN-LAST:event_btnAnularActionPerformed

    private void btnReimprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReimprimirActionPerformed
        String consecutivo = "TRASB-" + metodos.msgIngresarEnter(this, "Documento a reimprimir");

        if (consecutivo.equals("TRASB-")) {
            return;
        }

        boolean anulado = instancias.getSql().getDocumentoAnulado("bdTrasladosBodegas", "Where id='" + consecutivo + "' ");
        if (anulado) {
            metodos.msgError(null, "El documento esta anulado");
            return;
        }

        String tipo = instancias.getImpresion();
        if (tipo.equals("pos")) {
            tipo = "posPrestamos";
        } else {
            tipo = "prestamos";
        }

        instancias.getReporte().verPrestamos(consecutivo, tipo);
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

    public void facturarTranslado() {
        /* REVISAMOS LOS ITEMS A FACTURAR */
        Object[][] datos = new Object[tblProductos.getRowCount()][6];

        for (int i = 0; i < tblProductos.getRowCount(); i++) {
            datos[i][0] = tblProductos.getValueAt(i, 0);
            datos[i][1] = Double.parseDouble(tblProductos.getValueAt(i, 3).toString().replace(",", "."));
            datos[i][2] = tblProductos.getValueAt(i, 2);
            datos[i][3] = tblProductos.getValueAt(i, 5);
            datos[i][4] = tblProductos.getValueAt(i, 6);
            datos[i][5] = tblProductos.getValueAt(i, 8);
        }

        instancias.getSql().devolucionTraslado("TRASB-" + txtTraslado.getText(), "FACTURACIÓN");

        /* METEMOS LOS ITEMS DE FACTURAR EN UNA MATRIZ */
        Object[][] datos2 = new Object[tblProductos.getRowCount()][6];
        for (int i = 0; i < tblProductos.getRowCount(); i++) {
            datos2[i][0] = datos[i][0];
            datos2[i][1] = datos[i][1];
            datos2[i][2] = datos[i][2];

            datos2[i][3] = datos[i][3];
            datos2[i][4] = datos[i][4];
            datos2[i][5] = datos[i][5];

            String cod = "";
            try {
                cod = tblProductos.getValueAt(i, 8).toString();
            } catch (Exception e) {
            }

            if (cod.equals("")) {
                instancias.getSql().devolucionTraslado1("TRASB-" + txtTraslado.getText(), tblProductos.getValueAt(i, 0).toString(), tblProductos.getValueAt(i, 3).toString(), "facturados");
            } else {
                instancias.getSql().devolucionTraslado("TRASB-" + txtTraslado.getText(), cod, tblProductos.getValueAt(i, 3).toString(), "facturados");
            }

            ndProducto producto = instancias.getSql().getDatosProducto(tblProductos.getValueAt(i, 0).toString(), "bdProductos");
            double cantidad, fisicoInventario, totalInv;

            try {
                cantidad = Double.parseDouble(producto.getTrasladoBod().replace(",", "."));
            } catch (Exception e) {
                cantidad = 0;
            }

            try {
                totalInv = Double.parseDouble(producto.getInventario().replace(",", "."));
            } catch (Exception e) {
                totalInv = 0;
            }

            try {
                fisicoInventario = Double.parseDouble(producto.getFisicoInventario().replace(",", "."));
            } catch (Exception e) {
                fisicoInventario = 0;
            }

            double cant2 = Double.parseDouble(tblProductos.getValueAt(i, 3).toString().replace(",", "."));

            String tipoMovimiento = "";
            if (txtNombreBO.getText().equals("BODEGA PRINCIPAL")) {
                tipoMovimiento = "Suma";
            } else {
                tipoMovimiento = "Resta";
            }

            double total = 0.0;
            double totalFisico = 0.0;
            double totalInv1 = 0.0;

            if (tipoMovimiento.equals("Resta")) {
                total = cantidad;
                totalFisico = fisicoInventario - cant2;
                totalInv1 = totalInv + cant2;
                String totalFisico1 = String.valueOf(df.format(totalFisico)).replace(".", ",");
                String totalInv2 = String.valueOf(df.format(totalInv1)).replace(".", ",");
                instancias.getSql().modificarInventario("fisicoInventario", totalFisico1, tblProductos.getValueAt(i, 0).toString(), "bdProductos");
                instancias.getSql().modificarInventario("inventario", totalInv2, tblProductos.getValueAt(i, 0).toString(), "bdProductos");
            } else {
                total = cantidad - cant2;
            }

            String total1 = String.valueOf(df.format(total)).replace(".", ",");
            instancias.getSql().modificarInventario("trasladoBod", total1, tblProductos.getValueAt(i, 0).toString(), "bdProductos");

        }

        String nit = "";
        if (txtNitBD.getText().split("-")[0].equals("123")) {
            nit = "TERC-" + txtNitBO.getText().split("-")[0];

            instancias.getEgresos().cargarEgreso(nit, big.getMoneda(txtValor.getText()), "PRESTAMO " + txtTraslado.getText(), "PAGO PRESTAMOS",
                    "PAGO DE PRESTAMO", "LOCAL", "PRESTAMO " + txtTraslado.getText(), big.getMoneda("0"), big.getMoneda("0"), big.getMoneda(txtValor.getText()),
                    big.getMoneda("0"), big.getMoneda("0"), BigDecimal.ZERO, BigDecimal.ZERO, "");

        } else {
            nit = "TERC-" + txtNitBD.getText().split("-")[0];
        }

        //instancias.getFactura().desdeTrasladosBodegas(datos2, nit, txtDiasPlazoFact.getText());

        if (saltarPasosTraslados) {
            metodos.msgExito(this, "Prestamo liquidado exitosamente");
        }

        btnLimpiarActionPerformed(null);
        saltarPasosTraslados = true;
    }

    public void devolverTranslado() {

        String factura = "TRASB-" + instancias.getSql().getNumConsecutivo("TRASB")[0];
        cargarTotales();

        //PROCESO GUARDAR REGISTRO EN CAJA
        Object[] vector = {factura, txtNitBD.getText(), txtNitBO.getText(), metodos.fechaConsulta(metodosGenerales.fecha()),
            txtUnidades.getText(), big.getMoneda(txtValor.getText()), "DEVOLUCIÓN", "", instancias.getUsuario(), instancias.getTerminal(), "0",
            "FINALIZADO", metodosGenerales.hora()};

        ndTrasladoBodega nodo = metodos.llenarTrasladoBodega(vector);

        if (!instancias.getSql().agregarTrasladoBodega(nodo)) {
            metodos.msgError(this, "Hubo un problema al guardar el prestamo");
            return;
        }

        //PROCESO GUARDAR VENTA
        for (int i = 0; i < tblProductos.getRowCount(); i++) {
            Object vectVenta[] = {factura, tblProductos.getValueAt(i, 0), big.getBigDecimal(tblProductos.getValueAt(i, 3).toString().replace(",", ".")),
                big.getMoneda((String) tblProductos.getValueAt(i, 2)), tblProductos.getValueAt(i, 5), tblProductos.getValueAt(i, 9),
                big.getMoneda((String) tblProductos.getValueAt(i, 4)), tblProductos.getValueAt(i, 6), "FINALIZADO", "", "",
                tblProductos.getValueAt(i, 8), big.getBigDecimal(tblProductos.getValueAt(i, 10).toString().replace(",", ".")), tblProductos.getValueAt(i, 11),
                big.getMoneda((String) tblProductos.getValueAt(i, 14))
            };

            ndProdTraslBodega nodoTras = metodos.llenarProdTralBodega(vectVenta);
            if (!instancias.getSql().agregarTrasladoBodega(nodoTras)) {
                metodos.msgError(this, "Hubo un problema al guardar el traslado");
            }
        }

        String tipoImpr = instancias.getImpresion();
        if (tipoImpr.equals("pos")) {
            tipoImpr = "posPrestamos";
        } else {
            tipoImpr = "prestamos";
        }

        if (saltarPasosTraslados) {
            if (metodos.msgPregunta(this, "¿Desea imprimir el nuevo prestamo?") == 0) {
                instancias.getReporte().verPrestamos(factura, tipoImpr);
            }
        } else {
            instancias.getReporte().verPrestamos(factura, tipoImpr);
        }

        //CAMBIAR CONSECUTIVO FACTURA
        if (!instancias.getSql().aumentarConsecutivo("TRASB", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("TRASB")[0]) + 1)) {
            metodos.msgError(this, "Hubo un problema al guardar en el consecutivo del traslado");
        }

//                if (metodos.msgPregunta(this, "¿Desea imprimir el nuevo traslado?") == 0) {
//                    instancias.getReporte().verTrasladoBodega(factura);
//                }
        String tipo = "Devuelto";
        instancias.getSql().devolucionTraslado("TRASB-" + txtTraslado.getText(), tipo);

        for (int i = 0; i < tblProductos.getRowCount(); i++) {
            String cod = "";
            try {
                cod = tblProductos.getValueAt(i, 8).toString();
            } catch (Exception e) {
            }

            if (cod.equals("")) {
                instancias.getSql().devolucionTraslado1("TRASB-" + txtTraslado.getText(), tblProductos.getValueAt(i, 0).toString(), tblProductos.getValueAt(i, 3).toString(), "devueltos");
            } else {
                instancias.getSql().devolucionTraslado("TRASB-" + txtTraslado.getText(), cod, tblProductos.getValueAt(i, 3).toString(), "devueltos");
            }

            ndProducto producto = instancias.getSql().getDatosProducto(tblProductos.getValueAt(i, 0).toString(), "bdProductos");
            double cantidad;
            double fisicoInventario;

            try {
                cantidad = Double.parseDouble(producto.getTrasladoBod().replace(",", "."));
            } catch (Exception e) {
                cantidad = 0;
            }

            try {
                fisicoInventario = Double.parseDouble(producto.getFisicoInventario().replace(",", "."));
            } catch (Exception e) {
                fisicoInventario = Double.parseDouble(producto.getInventario().replace(",", "."));
            }

            double cant2 = Double.parseDouble(tblProductos.getValueAt(i, 3).toString().replace(",", "."));

            String tipoMovimiento = "";
            if (txtNombreBO.getText().equals("BODEGA PRINCIPAL")) {
                tipoMovimiento = "Resta";
            } else {
                tipoMovimiento = "Suma";
            }

            double total = 0.0;
            if (tipoMovimiento.equals("Resta")) {
                fisicoInventario = fisicoInventario + cant2;
                total = cantidad - cant2;
            } else {
                fisicoInventario = fisicoInventario - cant2;
                total = cantidad + cant2;
            }

            String total1 = String.valueOf(df.format(total)).replace(".", ",");
            String fisicoInventario1 = String.valueOf(df.format(fisicoInventario)).replace(".", ",");

            instancias.getSql().modificarInventario("trasladoBod", total1, tblProductos.getValueAt(i, 0).toString(), "bdProductos");
            instancias.getSql().modificarInventario("fisicoInventario", fisicoInventario1, tblProductos.getValueAt(i, 0).toString(), "bdProductos");

            // DESCONTAR DEL INVENTARIO DETALLADO //
            if (!cod.equals("")) {
                String tipo1 = "";
                if (producto.getTipoProducto() != null) {
                    if (producto.getTipoProducto().equals("IMEI")) {
                        tipo1 = "Imei";
                    } else if (producto.getTipoProducto().equals("Fecha/Lote")) {
                        tipo1 = "Fecha/Lote";
                    } else if (producto.getTipoProducto().equals("Color")) {
                        tipo1 = "Color";
                    } else if (producto.getTipoProducto().equals("Serial")) {
                        tipo1 = "Serial";
                    } else if (producto.getTipoProducto().equals("Talla")) {
                        tipo1 = "Talla";
                    } else if (producto.getTipoProducto().equals("ColorTalla")) {
                        tipo1 = "ColorTalla";
                    } else if (producto.getTipoProducto().equals("SerialColor")) {
                        tipo1 = "SerialColor";
                    } else {
                        tipo1 = "";
                    }
                }

                if (tipo1.equals("Imei") || tipo1.equals("Serial") || tipo1.equals("SerialColor")) {
                    instancias.getSql().modificarEstadoDetalleProductos(cod, "DISPONIBLE");
                } else {
                    Double cantidadActual = Double.parseDouble(instancias.getSql().getCantidadProductos(cod).replace(",", "."));
                    cantidadActual = cantidadActual + cant2;
                    String cantidadFinal = String.valueOf(df.format(cantidadActual)).replace(".", ",");
                    //instancias.getSql().modificarCantidadesDetalleProductos(cod, cantidadFinal);
                }
            }
            // FIN DE DESCONTAR DEL INVENTARIO SEPARADO // 
        }

        if (saltarPasosTraslados) {
            metodos.msgExito(this, "Prestamo liquidado exitosamente");
        }
        btnLimpiarActionPerformed(null);
        saltarPasosTraslados = true;
    }

    public void facturarTraslados(String conse, String diasPlazo) {
        desdeTraslados(conse);
        txtDiasPlazoFact.setText(diasPlazo);
        saltarPasosTraslados = false;
        facturarTranslado();
    }

    public void facturarTraslados1(String conse) {
        desdeTraslados(conse);
        saltarPasosTraslados = false;
        devolverTranslado();
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

        txtValor.setText(this.simbolo + " 0");
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

    private void txtDiasPlazoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiasPlazoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiasPlazoActionPerformed

    private void txtNombreBOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreBOActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreBOActionPerformed

    private void btnBuscTercerosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscTercerosActionPerformed
        dlgTrasladosPendientes traslados = new dlgTrasladosPendientes(null, true, "");
        traslados.setLocationRelativeTo(null);
        traslados.setVisible(true);
    }//GEN-LAST:event_btnBuscTercerosActionPerformed

    private void txtDiasPlazoFactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiasPlazoFactActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiasPlazoFactActionPerformed

    private void txtCantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantActionPerformed

    private void txtCantKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantKeyReleased

    private void btnBusProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBusProdActionPerformed
        if (txtNombreBO.getText().equals("")) {
            metodos.msgError(this, "Seleccione bodega de origen");
            return;
        }

        ventanaProductos("");
    }//GEN-LAST:event_btnBusProdActionPerformed

    public void desdeTraslados(String traslado) {

        txtTraslado.setText(traslado);

        Object[][] prodTraslados = instancias.getSql().getProductosTraslados(traslado);

        if (prodTraslados[0][0] != null) {
            txtTraslado.setText(prodTraslados[0][0].toString().replace("TRASB-", ""));
            txtUnidades.setText(prodTraslados[0][4].toString());
            txtValor.setText(big.setMoneda(big.getMoneda(prodTraslados[0][5].toString())));
            txtDiasPlazo.setText(prodTraslados[0][6].toString());

            ndBodega nodo = instancias.getSql().getDatosBodega(prodTraslados[0][1].toString());
            if (nodo.getCodigo() != null) {
                txtNitBO.setText(prodTraslados[0][1].toString());
                txtNombreBO.setText(nodo.getNombre());
            }

            ndBodega nodo1 = instancias.getSql().getDatosBodega(prodTraslados[0][2].toString());
            if (nodo1.getCodigo() != null) {
                txtNombreBD.setText(nodo1.getNombre());
                txtNitBD.setText(prodTraslados[0][2].toString());
            }

            DefaultTableModel modelo = (DefaultTableModel) tblProductos.getModel();
            while (tblProductos.getRowCount() > 0) {
                modelo.removeRow(0);
            }

            for (int i = 0; i < prodTraslados.length; i++) {
               // cargarProducto(prodTraslados[i][8].toString(), prodTraslados[i][9].toString(), 1, prodTraslados[i][11].toString(),
                //        prodTraslados[i][14].toString(), prodTraslados[i][15].toString(), "", "", "", "");

                tblProductos.setValueAt(big.setMoneda(big.getMoneda(prodTraslados[i][10].toString().replace(".", ","))), tblProductos.getRowCount() - 1, 2);
                tblProductos.setValueAt(prodTraslados[i][12], tblProductos.getRowCount() - 1, 9);
                tblProductos.removeEditor();

                KeyEvent evento = new KeyEvent(tblProductos, 0, 0, 0, 0);
                evento.setKeyCode(KeyEvent.VK_ENTER);
                tblProductosKeyReleased(evento);
            }

            tblProductos.getColumnModel().getColumn(12).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(12).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(12).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(13).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(13).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(13).setMaxWidth(0);

            tblProductos.removeEditor();
            txtCodProducto.requestFocus();
            txtCodProducto.setEnabled(false);
            popBorrar.setEnabled(false);
            txtNitBO.setEditable(false);
            txtNitBD.setEditable(false);
            btnBusProd.setEnabled(false);
            btnGuardar.setEnabled(false);
            txtDiasPlazo.setEnabled(false);
            btnReimprimir.setEnabled(false);
            btnAnular.setEnabled(false);
        } else {
            metodos.msgError(this, "El traslado no existe");
        }
    }

    public void cargarProducto(String codigo, BigDecimal cantidad, int plu, String imei, String lote, String idProd, String talla, String color,
            String temp, String fechaVence) {

        if (txtNombreBO.getText().equals("")) {
            metodos.msgError(this, "Seleccione bodega de origen");
            return;
        }

        for (int j = 0; j < tblProductos.getRowCount(); j++) {
            String idPro = "";
            try {
                idPro = tblProductos.getValueAt(j, 10).toString();
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

        ndProducto nodo = null;

        String codigoProd = "";
        if (codigo.equals("")) {
            codigoProd = "";
        } else {
            Object[][] listado = instancias.getSql().getCodigosRelacionados(codigo, " where codigo");
            if (listado.length > 0) {
                codigo = listado[0][0].toString();
            }

            nodo = instancias.getSql().getDatosProducto(codigo, "bdProductos");
            if (nodo.getCodigo() != null) {
                codigoProd = nodo.getCodigo();
            }
        }

        if (!codigoProd.equals("")) {
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
                    VistaSeleccionarPLU pluu = new VistaSeleccionarPLU(null, true, "bdProductos");
                    pluu.setInstancias(instancias, nodo.getIdSistema());
                    pluu.setOpc("traslado");
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

            String tipo = Enums.DetalleTipoProducto.obtenerTipoProducto(nodo.getTipoProducto());

            if (!tipo.equals("") && idProd.equals("")) {
                String tipoMov = "";
                if (txtNombreBO.getText().equals("BODEGA PRINCIPAL")) {
                    tipoMov = "Salida";
                } else {
                    tipoMov = "Entrada";
                }

                VistaMovimientoDetalleProducto compraDetallada = new VistaMovimientoDetalleProducto(null, true, nodo, null, tipoMov, "prestamos", BigDecimal.ZERO);
                compraDetallada.setLocationRelativeTo(null);
                compraDetallada.setVisible(true);
                return;
            } else {
                
                BigDecimal costoPonderado = BigDecimal.ZERO;
                try {
                    UltimoPonderado ultimoPonderado = servicioActualizacionPonderado.obtenerUltimoPonderado(nodo.getIdSistema());
                    costoPonderado = ultimoPonderado.getNuevoPonderado();
                } catch (SQLException ex) {
                    Logger.getLogger(VistaInventarioInicial.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                String cant = nodo.getFisicoInventario().replace(".", ",");

                Double res = 0.0;
              /*  if (txtNombreBO.getText().equals("BODEGA PRINCIPAL")) {
                    try {
                        res = Double.parseDouble(cant.replace(",", ".")) - Double.parseDouble(cantidad);
                    } catch (Exception e) {
                        try {
                            res = big.getBigDecimal(cant.replace(",", ".")).subtract(big.getBigDecimal(cantidad.replace(",", "."))).doubleValue();
                        } catch (Exception ex) {
                            res = Double.parseDouble(cant.replace(",", ".")) - Integer.parseInt(cantidad);
                        }
                    }
                } else {
                    try {
                        res = Double.parseDouble(cant.replace(",", ".")) + Double.parseDouble(cantidad);
                    } catch (Exception e) {
                        try {
                            res = big.getBigDecimal(cant.replace(",", ".")).add(big.getBigDecimal(cantidad.replace(",", "."))).doubleValue();
                        } catch (Exception ex) {
                            res = Double.parseDouble(cant.replace(",", ".")) + Integer.parseInt(cantidad);
                        }
                    }
                }*/

                BigDecimal listaPrecio = big.getBigDecimal(nodo.getL1());
                BigDecimal iva = big.getBigDecimal(nodo.getIva());

                if (iva.compareTo(BigDecimal.ZERO) > 0) {
                    iva = listaPrecio.multiply(iva);
                    iva = iva.divide(big.getBigDecimal("100"), 3, RoundingMode.CEILING);
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

                modeloPro.addRow(new Object[]{nodo.getIdSistema(), nodo.getDescripcion(), big.setMoneda(listaPrecio),
                    cantidad, big.setMoneda(big.getBigDecimal(listaPrecio).multiply(big.getBigDecimal(cantidad)).add(iva)), detalle, "", big.setMoneda(costoPonderado), idProd, "",
                    (big.getBigDecimal(cant2).multiply(cantidad)), plu, cant, res, big.setMoneda(iva.multiply(big.getBigDecimal(cantidad))),
                    talla});

                tblProductos.setColumnSelectionInterval(9, 9);
                tblProductos.setRowSelectionInterval(tblProductos.getRowCount() - 1, tblProductos.getRowCount() - 1);
                tblProductos.editCellAt(tblProductos.getRowCount() - 1, 9);
                tblProductos.transferFocus();

                cargarTotales();
                txtCodProducto.setText("");
                txtCant.setText(datos[87].toString());
                return;
            }
        }

        ventanaProductos(codigo);
    }

    public void ventanaProductos(String codigo) {

//        String base = "";
//        if (txtBaseOrigen.getText().equals("bdProductos")) {
//            base = "productos1";
//        } else if (txtBaseOrigen.getText().equals("bdProductosBodega1")) {
//            base = "productos1Bodega1";
//        } else if (txtBaseOrigen.getText().equals("bdProductosBodega2")) {
//            base = "productos1Bodega2";
//        } else if (txtBaseOrigen.getText().equals("bdProductosBodega3")) {
//            base = "productos1Bodega3";
//        } else if (txtBaseOrigen.getText().equals("bdProductosBodega4")) {
//            base = "productos1Bodega4";
//        }
//
//        if (base.equals("")) {
//            metodos.msgError(this, "Debe seleccionar la bodega de origen");
//            return;
//        }
        String tipo = "";
        if (txtNombreBO.getText().equals("BODEGA PRINCIPAL")) {
        } else {
            tipo = "sinSerial";
        }

        VistaBuscadorProductos buscar = new VistaBuscadorProductos(null, true, false, tipo);
        buscar.setOpc("traslado");
        buscar.setLocationRelativeTo(null);
        instancias.setBusProductos(buscar);
        instancias.setCampoActual(txtCodProducto);
        txtCodProducto.requestFocus();
        buscar.noEncontrado(codigo.replace("'", "//"));
        buscar.show();
    }

    public void ventanaTerceros1(String nit, JTextField campo) {

        String tipo = "";
        if (txtNombreBO.getText().equals("BODEGA PRINCIPAL")) {
        } else {
            tipo = "PRINCIPAL";
        }

        buscBodegas buscar = new buscBodegas(instancias.getMenu(), rootPaneCheckingEnabled, tipo);
        buscar.setLocationRelativeTo(null);
        instancias.setBuscBodegas(buscar);
        instancias.setCampoActual(campo);
        campo.requestFocus();
        buscar.noEncontrado(nit);
        buscar.show();
    }

    public void ventanaTerceros(String nit, JTextField campo) {
        buscBodegas buscar = new buscBodegas(instancias.getMenu(), rootPaneCheckingEnabled, "PRESTAMO");
        buscar.setLocationRelativeTo(null);
        instancias.setBuscBodegas(buscar);
        instancias.setCampoActual(campo);
        campo.requestFocus();
        buscar.noEncontrado(nit);
        buscar.show();
    }

    public void cargarTotales() {
        BigDecimal total = BigDecimal.ZERO, cantidad = BigDecimal.ZERO;
        for (int i = 0; i < tblProductos.getRowCount(); i++) {
            total = total.add(big.getMoneda(tblProductos.getValueAt(i, 4).toString()));
            cantidad = cantidad.add(big.getBigDecimal(tblProductos.getValueAt(i, 3).toString().replace(",", ".")));
        }
        txtUnidades.setText(cantidad + "");
        txtValor.setText(big.setMoneda(total));
    }

    public void cargarProductos(Object[][] productos) {
        String cantEstablecida = txtCant.getText();
        for (int i = 0; i < productos.length; i++) {
            String codigo = productos[i][0].toString();
            String cantidad = productos[i][1].toString();
            if (cantidad.equals("0")) {
                cantidad = cantEstablecida;
            }
            cargarProducto(codigo, Utilidades.Utilidades.convertirBigDecimal(cantidad), 1, "", "", "", "", "", "", "");
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnular;
    private javax.swing.JButton btnBusProd;
    private javax.swing.JButton btnBuscTerceros;
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
    private javax.swing.JLabel lbNit4;
    private javax.swing.JLabel lbNit5;
    private javax.swing.JLabel lbNit6;
    private javax.swing.JLabel lbNombre;
    private javax.swing.JLabel lbNombre1;
    private javax.swing.JPanel pnlFormulario;
    private javax.swing.JMenuItem popBorrar;
    private javax.swing.JScrollPane scrFormulario;
    private javax.swing.JScrollPane scrProductos;
    private javax.swing.JTable tblProductos;
    private javax.swing.JTextField txtCant;
    private javax.swing.JTextField txtCodProducto;
    private javax.swing.JTextField txtDiasPlazo;
    private javax.swing.JTextField txtDiasPlazoFact;
    private javax.swing.JTextField txtNitBD;
    private javax.swing.JTextField txtNitBO;
    private javax.swing.JTextField txtNombreBD;
    private javax.swing.JTextField txtNombreBO;
    private javax.swing.JTextField txtTraslado;
    private javax.swing.JTextField txtUnidades;
    private javax.swing.JTextField txtValor;
    // End of variables declaration//GEN-END:variables
}
