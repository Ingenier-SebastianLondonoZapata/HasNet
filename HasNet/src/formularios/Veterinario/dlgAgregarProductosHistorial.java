package formularios.Veterinario;

import Modelo.Inventario.UltimoPonderado;
import clases.Instancias;
import clases.Veterinario.ndMascota;
import clases.big;
import clases.metodosGenerales;
import clases.productos.ndProdTraslBodega;
import clases.productos.ndProducto;
import Modelo.Terceros.ModeloContacto;
import inventario.servicio.ServicioActualizacionPonderado;
import Vista.Productos.VistaInventarioInicial;
import Vista.Productos.VistaBuscadorProductos;
import inventario.vista.VistaMovimientoDetalleProducto;
import Vista.Productos.VistaSeleccionarPLU;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;

public class dlgAgregarProductosHistorial extends javax.swing.JDialog {

    private ServicioActualizacionPonderado servicioActualizacionPonderado = new ServicioActualizacionPonderado();

    Object[] datos;
    private Instancias instancias;
    metodosGenerales metodos = new metodosGenerales();
    DefaultTableModel modeloPro;
    DefaultTableModel modeloPro1;
    private boolean plu = false;
    String consecutivo = "", simbolo = "";

    public dlgAgregarProductosHistorial(java.awt.Frame parent, boolean modal, String conse, String cliente, String mascota) {
        super(parent, modal);
        initComponents();

        this.setLocationRelativeTo(null);
        this.setTitle("Productos adicionales al servicio...");
        instancias = Instancias.getInstancias();

        consultarMaestros();
        modeloPro = (DefaultTableModel) tblProductos.getModel();
        modeloPro1 = (DefaultTableModel) tblViejo.getModel();
        consecutivo = conse;

        cargarCliente(cliente);
        cargarMascota(mascota);

        Object[][] productos = instancias.getSql().getProductosProductosServiciosAdicionales(conse);

        if (productos != null) {
            for (int i = 0; i < productos.length; i++) {
                cargarProducto1(productos[i][0].toString(), productos[i][1].toString(), productos[i][3].toString(),
                        productos[i][6].toString(), productos[i][7].toString(), 1);

                tblViejo.setValueAt(big.setMoneda(big.getBigDecimal(productos[i][2].toString())), i, 2);
                tblViejo.setValueAt(productos[i][4], i, 9);

                KeyEvent evento = new KeyEvent(tblViejo, 0, 0, 0, 0);
                evento.setKeyCode(KeyEvent.VK_ENTER);
                tblViejoKeyReleased(evento);
            }
        }

        tblViejo.removeEditor();
        tblViejo.setEnabled(false);

        this.simbolo = instancias.getSimbolo();
        this.getRootPane().registerKeyboardAction(accion("Cerrar", this), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    public ActionListener accion(final String opc, final JDialog ventana) {
        ActionListener a = new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                switch (opc) {
                    case "Cerrar":
                        btnSalirActionPerformed(null);
                        break;
                }
            }
        };
        return a;

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grpEncabezados = new javax.swing.ButtonGroup();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        popBorrar = new javax.swing.JMenuItem();
        pnlFormulario = new javax.swing.JPanel();
        lbNit5 = new javax.swing.JLabel();
        scrProductos = new javax.swing.JScrollPane();
        tblProductos = new javax.swing.JTable();
        txtCant = new javax.swing.JTextField();
        lbNit = new javax.swing.JLabel();
        txtCodProducto = new javax.swing.JTextField();
        btnBusProd = new javax.swing.JButton();
        lbTitulo5 = new javax.swing.JLabel();
        scrProductos1 = new javax.swing.JScrollPane();
        tblViejo = new javax.swing.JTable();
        lbNit8 = new javax.swing.JLabel();
        txtValor = new javax.swing.JLabel();
        lbTitulo6 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        lbNit2 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        lbRazon1 = new javax.swing.JLabel();
        txtNombrePropietario = new javax.swing.JTextField();
        lbNit3 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        lbNit1 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        lbRazon = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        lbDireccion6 = new javax.swing.JLabel();
        txtRaza = new javax.swing.JTextField();
        lbDireccion5 = new javax.swing.JLabel();
        txtGenero = new javax.swing.JTextField();

        popBorrar.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        popBorrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/eliminar-cancelar-icono-4935-16.png"))); // NOI18N
        popBorrar.setText("Borrar");
        popBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                popBorrarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(popBorrar);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        pnlFormulario.setBackground(new java.awt.Color(255, 255, 255));
        pnlFormulario.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pnlFormulario.setPreferredSize(new java.awt.Dimension(835, 801));

        lbNit5.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbNit5.setText("Cant:");

        tblProductos.setFont(new java.awt.Font("Century Gothic", 0, 22)); // NOI18N
        tblProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Descripción", "Valor/Unit", "Cant", "Total", "Serial", "Lote", "Costo", "idProd", "Nota", "cant2", "plu", "Inv. Inicial", "Inv. Final", "iva"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true, false, false, false, false, false, true, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblProductos.setComponentPopupMenu(jPopupMenu1);
        tblProductos.setRowHeight(27);
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
            tblProductos.getColumnModel().getColumn(2).setMaxWidth(180);
            tblProductos.getColumnModel().getColumn(3).setMinWidth(25);
            tblProductos.getColumnModel().getColumn(3).setPreferredWidth(60);
            tblProductos.getColumnModel().getColumn(3).setMaxWidth(100);
            tblProductos.getColumnModel().getColumn(4).setMinWidth(100);
            tblProductos.getColumnModel().getColumn(4).setPreferredWidth(120);
            tblProductos.getColumnModel().getColumn(4).setMaxWidth(180);
            tblProductos.getColumnModel().getColumn(5).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(5).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(5).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(6).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(6).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(6).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(7).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(7).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(7).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(8).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(8).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(8).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(9).setMinWidth(60);
            tblProductos.getColumnModel().getColumn(9).setPreferredWidth(300);
            tblProductos.getColumnModel().getColumn(9).setMaxWidth(500);
            tblProductos.getColumnModel().getColumn(10).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(10).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(10).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(11).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(11).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(11).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(12).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(12).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(12).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(13).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(13).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(13).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(14).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(14).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(14).setMaxWidth(0);
        }

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

        lbNit.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbNit.setText("Productos:");

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

        lbTitulo5.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lbTitulo5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbTitulo5.setText("Historial de compras");

        tblViejo.setFont(new java.awt.Font("Century Gothic", 0, 22)); // NOI18N
        tblViejo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Descripción", "Valor/Unit", "Cant", "Total", "Serial", "Lote", "Costo", "idProd", "Nota", "cant2", "plu", "Inv. Inicial", "Inv. Final", "iva"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true, false, false, false, false, false, true, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblViejo.setRowHeight(27);
        tblViejo.getTableHeader().setReorderingAllowed(false);
        tblViejo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblViejoMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblViejoMouseReleased(evt);
            }
        });
        tblViejo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblViejoKeyReleased(evt);
            }
        });
        scrProductos1.setViewportView(tblViejo);
        if (tblViejo.getColumnModel().getColumnCount() > 0) {
            tblViejo.getColumnModel().getColumn(0).setMinWidth(0);
            tblViejo.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblViejo.getColumnModel().getColumn(0).setMaxWidth(0);
            tblViejo.getColumnModel().getColumn(2).setMinWidth(100);
            tblViejo.getColumnModel().getColumn(2).setPreferredWidth(120);
            tblViejo.getColumnModel().getColumn(2).setMaxWidth(180);
            tblViejo.getColumnModel().getColumn(3).setMinWidth(25);
            tblViejo.getColumnModel().getColumn(3).setPreferredWidth(60);
            tblViejo.getColumnModel().getColumn(3).setMaxWidth(100);
            tblViejo.getColumnModel().getColumn(4).setMinWidth(100);
            tblViejo.getColumnModel().getColumn(4).setPreferredWidth(120);
            tblViejo.getColumnModel().getColumn(4).setMaxWidth(180);
            tblViejo.getColumnModel().getColumn(5).setMinWidth(0);
            tblViejo.getColumnModel().getColumn(5).setPreferredWidth(0);
            tblViejo.getColumnModel().getColumn(5).setMaxWidth(0);
            tblViejo.getColumnModel().getColumn(6).setMinWidth(0);
            tblViejo.getColumnModel().getColumn(6).setPreferredWidth(0);
            tblViejo.getColumnModel().getColumn(6).setMaxWidth(0);
            tblViejo.getColumnModel().getColumn(7).setMinWidth(0);
            tblViejo.getColumnModel().getColumn(7).setPreferredWidth(0);
            tblViejo.getColumnModel().getColumn(7).setMaxWidth(0);
            tblViejo.getColumnModel().getColumn(8).setMinWidth(0);
            tblViejo.getColumnModel().getColumn(8).setPreferredWidth(0);
            tblViejo.getColumnModel().getColumn(8).setMaxWidth(0);
            tblViejo.getColumnModel().getColumn(9).setMinWidth(60);
            tblViejo.getColumnModel().getColumn(9).setPreferredWidth(300);
            tblViejo.getColumnModel().getColumn(9).setMaxWidth(500);
            tblViejo.getColumnModel().getColumn(10).setMinWidth(0);
            tblViejo.getColumnModel().getColumn(10).setPreferredWidth(0);
            tblViejo.getColumnModel().getColumn(10).setMaxWidth(0);
            tblViejo.getColumnModel().getColumn(11).setMinWidth(0);
            tblViejo.getColumnModel().getColumn(11).setPreferredWidth(0);
            tblViejo.getColumnModel().getColumn(11).setMaxWidth(0);
            tblViejo.getColumnModel().getColumn(12).setMinWidth(0);
            tblViejo.getColumnModel().getColumn(12).setPreferredWidth(0);
            tblViejo.getColumnModel().getColumn(12).setMaxWidth(0);
            tblViejo.getColumnModel().getColumn(13).setMinWidth(0);
            tblViejo.getColumnModel().getColumn(13).setPreferredWidth(0);
            tblViejo.getColumnModel().getColumn(13).setMaxWidth(0);
            tblViejo.getColumnModel().getColumn(14).setMinWidth(0);
            tblViejo.getColumnModel().getColumn(14).setPreferredWidth(0);
            tblViejo.getColumnModel().getColumn(14).setMaxWidth(0);
        }

        lbNit8.setFont(new java.awt.Font("Century Gothic", 1, 28)); // NOI18N
        lbNit8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbNit8.setText("TOTAL:");

        txtValor.setFont(new java.awt.Font("Century Gothic", 1, 28)); // NOI18N
        txtValor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtValor.setText("0");

        lbTitulo6.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        lbTitulo6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitulo6.setText("X");
        lbTitulo6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbTitulo6MouseClicked(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jButton2.setBackground(new java.awt.Color(0, 204, 102));
        jButton2.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jButton2.setText("GUARDAR ");
        jButton2.setBorder(null);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        btnSalir.setBackground(new java.awt.Color(255, 153, 153));
        btnSalir.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnSalir.setText("CANCELAR");
        btnSalir.setBorder(null);
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(312, 312, 312)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Propietario", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 0, 14))); // NOI18N

        lbNit2.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit2.setText("Cedula:");

        txtId.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtId.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtId.setEnabled(false);
        txtId.setName("Codigo"); // NOI18N
        txtId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIdKeyReleased(evt);
            }
        });

        lbRazon1.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbRazon1.setText("Nombre:");

        txtNombrePropietario.setEditable(false);
        txtNombrePropietario.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtNombrePropietario.setName("Nombre"); // NOI18N
        txtNombrePropietario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombrePropietarioActionPerformed(evt);
            }
        });
        txtNombrePropietario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombrePropietarioKeyReleased(evt);
            }
        });

        lbNit3.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbNit3.setText("Telefono:");

        txtTelefono.setEditable(false);
        txtTelefono.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtTelefono.setName("Codigo"); // NOI18N
        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbRazon1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbNit2, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lbNit3, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTelefono))
                    .addComponent(txtNombrePropietario, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtId)
                        .addComponent(lbNit3, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                        .addComponent(txtTelefono))
                    .addComponent(lbNit2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbRazon1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombrePropietario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mascota", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 0, 14))); // NOI18N

        lbNit1.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit1.setText("Codigo:");

        txtCodigo.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtCodigo.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtCodigo.setEnabled(false);
        txtCodigo.setName("Codigo"); // NOI18N
        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoKeyReleased(evt);
            }
        });

        lbRazon.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbRazon.setText("Nombre:");

        txtNombre.setEditable(false);
        txtNombre.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtNombre.setName("Nombre"); // NOI18N
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreKeyReleased(evt);
            }
        });

        lbDireccion6.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion6.setText("Raza:");

        txtRaza.setEditable(false);
        txtRaza.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtRaza.setName("Edad"); // NOI18N
        txtRaza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRazaActionPerformed(evt);
            }
        });
        txtRaza.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtRazaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRazaKeyTyped(evt);
            }
        });

        lbDireccion5.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion5.setText("Genero:");

        txtGenero.setEditable(false);
        txtGenero.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtGenero.setName("Edad"); // NOI18N
        txtGenero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGeneroActionPerformed(evt);
            }
        });
        txtGenero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtGeneroKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtGeneroKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbRazon, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
                    .addComponent(lbNit1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbDireccion5, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
                    .addComponent(lbDireccion6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtRaza, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                    .addComponent(txtGenero))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbNit1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbDireccion6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtRaza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(3, 3, 3)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lbDireccion5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbRazon, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtGenero))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlFormularioLayout = new javax.swing.GroupLayout(pnlFormulario);
        pnlFormulario.setLayout(pnlFormularioLayout);
        pnlFormularioLayout.setHorizontalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFormularioLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lbTitulo6, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrProductos)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbTitulo5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scrProductos1)
                    .addComponent(jSeparator1)
                    .addGroup(pnlFormularioLayout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlFormularioLayout.createSequentialGroup()
                        .addComponent(lbNit5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCant, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lbNit, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCodProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBusProd, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnlFormularioLayout.createSequentialGroup()
                        .addGap(521, 521, 521)
                        .addComponent(lbNit8, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtValor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(25, 25, 25))
        );
        pnlFormularioLayout.setVerticalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addComponent(lbTitulo6, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCodProducto)
                        .addComponent(txtCant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbNit5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbNit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBusProd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(lbTitulo5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrProductos1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbNit8)
                    .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlFormulario, javax.swing.GroupLayout.DEFAULT_SIZE, 951, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlFormulario, javax.swing.GroupLayout.PREFERRED_SIZE, 682, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductosMouseClicked

    }//GEN-LAST:event_tblProductosMouseClicked

    private void tblProductosMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductosMouseReleased

    }//GEN-LAST:event_tblProductosMouseReleased

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

            cargarTotales();
        }
    }//GEN-LAST:event_tblProductosKeyReleased

    private void txtCantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantActionPerformed

    private void txtCantKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantKeyReleased

    private void txtCodProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodProductoActionPerformed

    private void txtCodProductoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodProductoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String codigo = txtCodProducto.getText();
            plu = true;
            cargarProducto(codigo.replace("'", "//"), txtCant.getText(), "", "", "", 1);
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

    private void btnBusProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBusProdActionPerformed
        ventanaProductos("");
    }//GEN-LAST:event_btnBusProdActionPerformed

    private void tblViejoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblViejoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblViejoMouseClicked

    private void tblViejoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblViejoMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tblViejoMouseReleased

    private void tblViejoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblViejoKeyReleased
        BigDecimal valor, cantidad, iva, total, cantFacturar, rest, totalN;

        int fila = tblViejo.getSelectedRow(), i = 3, j = 1;

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            //Si se esta cambiando el campo de cantidad

            if (tblViejo.getSelectedColumn() == 2) {
                tblViejo.setValueAt(big.setMoneda(big.getMoneda(tblViejo.getValueAt(fila, 2).toString())), fila, 2);
                tblViejo.editCellAt(tblViejo.getSelectedRow(), 3);
                tblViejo.setColumnSelectionInterval(3, 3);
                tblViejo.transferFocus();
            } else if (tblViejo.getSelectedColumn() == 3) {
                tblViejo.editCellAt(tblViejo.getSelectedRow(), 9);
                tblViejo.setColumnSelectionInterval(9, 9);
                tblViejo.transferFocus();
            } else if (tblViejo.getSelectedColumn() == 9) {
                txtCodProducto.requestFocus();
            }

            try {
                int res = big.getBigDecimal(big.getMoneda((String) tblViejo.getValueAt(fila, i))).compareTo(big.getBigDecimal("1"));
                if (res == -1 || String.valueOf(tblViejo.getValueAt(fila, i)).equals("")) {
                    tblViejo.setValueAt(j, tblViejo.getSelectedRow(), i);
                }
            } catch (NumberFormatException e) {
                tblViejo.setValueAt(j, tblViejo.getSelectedRow(), i);
            }

            ndProducto nodo = instancias.getSql().getDatosProducto(tblViejo.getValueAt(fila, 0).toString(), "bdProductos");

            valor = big.getMoneda(String.valueOf(tblViejo.getValueAt(fila, 2)));
            cantidad = big.getMoneda(String.valueOf(tblViejo.getValueAt(fila, 3)).replace(".", ","));
            total = valor.multiply(cantidad);

            iva = total.multiply(big.getBigDecimal(nodo.getIva()));
            iva = iva.divide(big.getBigDecimal("100"), 3, RoundingMode.CEILING);
            total = total.add(iva);

            tblViejo.setValueAt(big.setMoneda(total), fila, 4);
            tblViejo.setValueAt(big.setMoneda(iva), fila, 14);

            tblViejo.setValueAt(big.setNumero(cantidad), fila, 3);

            switch (tblViejo.getValueAt(fila, 11).toString()) {
                case "1":
                    tblViejo.setValueAt(cantidad, fila, 10);
                    break;
                case "2":
                    tblViejo.setValueAt(cantidad.multiply(big.getBigDecimal(nodo.getCantidad2())), fila, 10);
                    break;
                case "3":
                    tblViejo.setValueAt(cantidad.multiply(big.getBigDecimal(nodo.getCantidad3())), fila, 10);
                    break;
                case "4":
                    tblViejo.setValueAt(cantidad.multiply(big.getBigDecimal(nodo.getCantidad4())), fila, 10);
                    break;
            }

            cargarTotales();
        }
    }//GEN-LAST:event_tblViejoKeyReleased

    private void lbTitulo6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbTitulo6MouseClicked
        this.dispose();
    }//GEN-LAST:event_lbTitulo6MouseClicked

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (tblProductos.getRowCount() == 0) {
            metodos.msgError(null, "No ha añadido ningun producto");
        } else {

            int xyz = tblProductos.getRowCount();
            if (xyz > 0) {
                for (int i = 0; i < xyz; i++) {
                    if (tblProductos.getValueAt(i, 3).equals("0")) {
                        metodos.msgError(null, "Las cantidades de los productos deben de ser mayor a 0");
                        return;
                    }

                    if (tblProductos.getValueAt(i, 2).equals(this.simbolo + " 0")) {
                        metodos.msgError(null, "Los valores de los productos deben de ser mayor a " + this.simbolo + " 0");
                        return;
                    }

                    tblProductos.setColumnSelectionInterval(0, 0);
                    tblProductos.setRowSelectionInterval(i, i);
                    KeyEvent x = new KeyEvent(this, WIDTH, WIDTH, WIDTH, KeyEvent.VK_ENTER);
                    tblProductosKeyReleased(x);
                }
            }

            if (metodos.msgPregunta(null, "¿Desea continuar?") == 0) {

                //PROCESO GUARDAR VENTA
                for (int i = 0; i < tblProductos.getRowCount(); i++) {

                    Object vectVenta[] = {consecutivo, tblProductos.getValueAt(i, 0),
                        big.getBigDecimal(tblProductos.getValueAt(i, 3).toString().replace(",", ".")),
                        big.getMoneda((String) tblProductos.getValueAt(i, 2)), tblProductos.getValueAt(i, 5),
                        tblProductos.getValueAt(i, 9), big.getMoneda((String) tblProductos.getValueAt(i, 4)),
                        tblProductos.getValueAt(i, 6), "PENDIENTE", "", "", tblProductos.getValueAt(i, 8),
                        big.getBigDecimal(tblProductos.getValueAt(i, 10).toString().replace(",", ".")), tblProductos.getValueAt(i, 11),
                        big.getMoneda((String) tblProductos.getValueAt(i, 14))
                    };

                    ndProdTraslBodega nodoTras = metodos.llenarProdTralBodega(vectVenta);

                    if (!instancias.getSql().agregarProductosAdicionales(nodoTras)) {
                        metodos.msgError(null, "Hubo un problema al guardar el prestamo");
                    }
                }

                metodos.msgExito(null, "Productos guardados exitoso");
                this.dispose();
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtCodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cargarMascota(txtCodigo.getText());
        } else {
            txtNombrePropietario.setText("");
            txtTelefono.setText("");
            txtCodigo.setText("");
            txtRaza.setText("");
            txtNombre.setText("");
            txtGenero.setText("");
        }
    }//GEN-LAST:event_txtCodigoKeyReleased

    private void txtNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

        }
    }//GEN-LAST:event_txtNombreKeyReleased

    private void txtRazaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRazaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRazaActionPerformed

    private void txtRazaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRazaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRazaKeyReleased

    private void txtRazaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRazaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRazaKeyTyped

    private void txtGeneroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGeneroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGeneroActionPerformed

    private void txtGeneroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGeneroKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGeneroKeyReleased

    private void txtGeneroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGeneroKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGeneroKeyTyped

    private void txtNombrePropietarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombrePropietarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombrePropietarioActionPerformed

    private void txtNombrePropietarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombrePropietarioKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombrePropietarioKeyReleased

    private void txtTelefonoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoKeyReleased

    private void txtTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoKeyTyped

    private void txtIdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cargarCliente(txtId.getText());
        } else {
            txtCodigo.setEnabled(true);
            txtNombrePropietario.setText("");
            txtTelefono.setText("");
            txtCodigo.setText("");
            txtRaza.setText("");
            txtNombre.setText("");
            txtGenero.setText("");
        }
    }//GEN-LAST:event_txtIdKeyReleased

    private void popBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popBorrarActionPerformed
        if (tblProductos.getSelectedRow() > -1) {
            int fila = tblProductos.getSelectedRow();
            DefaultTableModel modelo = (DefaultTableModel) tblProductos.getModel();
            modelo.removeRow(fila);
            tblProductos.removeEditor();
            cargarTotales();
        } else {
            metodos.msgAdvertencia(null, "Seleccione un producto");
        }
    }//GEN-LAST:event_popBorrarActionPerformed

    public void cargarCliente(String nit) {

        ModeloContacto nodo = instancias.getSql().getDatosTercero(nit);

        if (nodo.getId() != null) {
            txtId.setText(nodo.getId());
            txtTelefono.setText(nodo.getTelefono());
            txtNombrePropietario.setText(nodo.getNombre());
            txtCodigo.setText(nodo.getId());
        }
    }

    public void cargarProducto(String codigo, String cantidad, String imei, String lote, String idProd, int plu) {

        for (int j = 0; j < tblProductos.getRowCount(); j++) {
            String idPro = "";
            try {
                idPro = tblProductos.getValueAt(j, 10).toString();
            } catch (Exception e) {
            }

            if (!idPro.equals("")) {
                if (idPro.equals(idProd)) {
                    if (!imei.equals("")) {
                        metodos.msgError(null, "El imei '" + imei + "' ya esta cargado.");
                        return;
                    } else {
                        metodos.msgError(null, "Este producto con el lote '" + lote + "' ya se cargó.");
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
                lbNit.requestFocus();
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
                    pluu.setInstancias(instancias, nodo.getCodigo());
                    pluu.setOpc("trasladoBodega");
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
            try {
                if (nodo.getTipoProducto().equals("IMEI")) {
                    tipo = "Imei";
                } else if (nodo.getTipoProducto().equals("Fecha/Lote")) {
                    tipo = "Fecha/Lote";
                } else if (nodo.getTipoProducto().equals("Color")) {
                    tipo = "Color";
                } else if (nodo.getTipoProducto().equals("Serial")) {
                    tipo = "Serial";
                } else {
                    tipo = "";
                }
            } catch (Exception e) {
            }

            if (!tipo.equals("") && imei.equals("") && lote.equals("")) {
                VistaMovimientoDetalleProducto compraDetallada = new VistaMovimientoDetalleProducto(null, true, nodo, null, "Salida", "traslados", BigDecimal.ZERO);
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

                BigDecimal listaPrecio = big.getBigDecimal(nodo.getL1());
                BigDecimal iva = big.getBigDecimal(nodo.getIva());

                if (iva.compareTo(BigDecimal.ZERO) > 0) {
                    iva = listaPrecio.multiply(iva);
                    iva = iva.divide(big.getBigDecimal("100"), 3, RoundingMode.CEILING);
                }

                modeloPro.addRow(new Object[]{nodo.getIdSistema(), nodo.getDescripcion(), big.setMoneda(listaPrecio),
                    cantidad, big.setMoneda(big.getBigDecimal(listaPrecio).multiply(big.getBigDecimal(cantidad)).add(iva)), imei, "", big.setMoneda(costoPonderado), idProd, "",
                    (big.getBigDecimal(cant2).multiply(big.getMoneda(cantidad))), plu, "", "", big.setMoneda(iva.multiply(big.getBigDecimal(cantidad)))});

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

    public void cargarProducto1(String codigo, String cantidad, String imei, String lote, String idProd, int plu) {

        for (int j = 0; j < tblViejo.getRowCount(); j++) {
            String idPro = "";
            try {
                idPro = tblViejo.getValueAt(j, 10).toString();
            } catch (Exception e) {
            }

            if (!idPro.equals("")) {
                if (idPro.equals(idProd)) {
                    if (!imei.equals("")) {
                        metodos.msgError(null, "El imei '" + imei + "' ya esta cargado.");
                        return;
                    } else {
                        metodos.msgError(null, "Este producto con el lote '" + lote + "' ya se cargó.");
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
                lbNit.requestFocus();
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
                    pluu.setInstancias(instancias, nodo.getCodigo());
                    pluu.setOpc("trasladoBodega");
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
            try {
                if (nodo.getTipoProducto().equals("IMEI")) {
                    tipo = "Imei";
                } else if (nodo.getTipoProducto().equals("Fecha/Lote")) {
                    tipo = "Fecha/Lote";
                } else if (nodo.getTipoProducto().equals("Color")) {
                    tipo = "Color";
                } else if (nodo.getTipoProducto().equals("Serial")) {
                    tipo = "Serial";
                } else {
                    tipo = "";
                }
            } catch (Exception e) {
            }

            if (!tipo.equals("") && imei.equals("") && lote.equals("")) {
                VistaMovimientoDetalleProducto compraDetallada = new VistaMovimientoDetalleProducto(null, true, nodo, null, "Salida", "traslados", BigDecimal.ZERO);
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

                BigDecimal listaPrecio = big.getBigDecimal(nodo.getL1());
                BigDecimal iva = big.getBigDecimal(nodo.getIva());

                if (iva.compareTo(BigDecimal.ZERO) > 0) {
                    iva = listaPrecio.multiply(iva);
                    iva = iva.divide(big.getBigDecimal("100"), 3, RoundingMode.CEILING);
                }

                modeloPro1.addRow(new Object[]{nodo.getIdSistema(), nodo.getDescripcion(), big.setMoneda(listaPrecio),
                    cantidad, big.setMoneda(big.getBigDecimal(listaPrecio).multiply(big.getBigDecimal(cantidad)).add(iva)), imei, "", big.setMoneda(costoPonderado), idProd, "",
                    (big.getBigDecimal(cant2).multiply(big.getMoneda(cantidad))), plu, "", "", big.setMoneda(iva.multiply(big.getBigDecimal(cantidad)))});

                tblViejo.setColumnSelectionInterval(9, 9);
                tblViejo.setRowSelectionInterval(tblViejo.getRowCount() - 1, tblViejo.getRowCount() - 1);
//                tblViejo.editCellAt(tblViejo.getRowCount() - 1, 9);
                tblViejo.transferFocus();

                cargarTotales();
                txtCodProducto.setText("");
                txtCant.setText(datos[87].toString());
                return;
            }
        }

        ventanaProductos(codigo);
    }

    public void ventanaProductos(String codigo) {
        VistaBuscadorProductos buscar = new VistaBuscadorProductos(null, true, false, "", "productos1");
        buscar.setLocationRelativeTo(null);
        instancias.setBusProductos(buscar);
        instancias.setCampoActual(txtCodProducto);
        txtCodProducto.requestFocus();
        buscar.noEncontrado(codigo.replace("'", "//"));
        buscar.show();
    }

    public void cargarMascota(String nit) {
        ndMascota nodo = instancias.getSql().getDatosMascota(nit);
        if (nodo.getCodigo() != null) {
            txtCodigo.setEnabled(false);
            txtCodigo.setText(nodo.getCodigo());
            txtNombre.setText(nodo.getNombre());
            txtRaza.setText(nodo.getRaza());
            txtGenero.setText(nodo.getGenero());
        }
    }

    public void cargarTotales() {
        BigDecimal total = BigDecimal.ZERO;
        for (int i = 0; i < tblProductos.getRowCount(); i++) {
            total = total.add(big.getMoneda(tblProductos.getValueAt(i, 4).toString()));
        }
        for (int i = 0; i < tblViejo.getRowCount(); i++) {
            total = total.add(big.getMoneda(tblViejo.getValueAt(i, 4).toString()));
        }
        txtValor.setText(big.setMoneda(total));
    }

    public void consultarMaestros() {
        datos = instancias.getSql().getDatosMaestra();
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
            java.util.logging.Logger.getLogger(dlgAgregarProductosHistorial.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(dlgAgregarProductosHistorial.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(dlgAgregarProductosHistorial.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dlgAgregarProductosHistorial.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                dlgAgregarProductosHistorial dialog = new dlgAgregarProductosHistorial(new javax.swing.JFrame(), true, "", "", "");
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
    private javax.swing.JButton btnBusProd;
    private javax.swing.JButton btnSalir;
    private javax.swing.ButtonGroup grpEncabezados;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbDireccion5;
    private javax.swing.JLabel lbDireccion6;
    private javax.swing.JLabel lbNit;
    private javax.swing.JLabel lbNit1;
    private javax.swing.JLabel lbNit2;
    private javax.swing.JLabel lbNit3;
    private javax.swing.JLabel lbNit5;
    private javax.swing.JLabel lbNit8;
    private javax.swing.JLabel lbRazon;
    private javax.swing.JLabel lbRazon1;
    private javax.swing.JLabel lbTitulo5;
    private javax.swing.JLabel lbTitulo6;
    private javax.swing.JPanel pnlFormulario;
    private javax.swing.JMenuItem popBorrar;
    private javax.swing.JScrollPane scrProductos;
    private javax.swing.JScrollPane scrProductos1;
    private javax.swing.JTable tblProductos;
    private javax.swing.JTable tblViejo;
    private javax.swing.JTextField txtCant;
    private javax.swing.JTextField txtCodProducto;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtGenero;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNombrePropietario;
    private javax.swing.JTextField txtRaza;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JLabel txtValor;
    // End of variables declaration//GEN-END:variables
}
