package Vista.Productos;

import Vista.Productos.VistaIngreso;
import clases.IconCellRenderer;
import clases.Instancias;
import clases.big;
import clases.metodosGenerales;
import clases.productos.ndGrupo;
import com.mxrck.autocompleter.TextAutoCompleter;
import Vista.Ventas.VistaFactura;
import formularios.productos.buscGrupos;
import formularios.productos.buscProductos2;
import formularios.productos.nuevoProducto;
import formularios.productos.verLogo;
import java.awt.Image;
import java.util.Vector;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;

public final class VistaBuscadorProductos extends javax.swing.JDialog {

    DefaultTableModel modelo;
    TableRowSorter modeloOrdenado;
    Instancias instancias;
    private String opc, tipo, baseDatos;
    private VistaFactura factura;
    private VistaIngreso ingreso;
    private TextAutoCompleter autoCompletar;
    private String claseBuscador = "";
    int pos = 0;
    Object[] datos;

    public String getClaseBuscador() {
        return claseBuscador;
    }

    public void setClaseBuscador(String claseBuscador) {
        this.claseBuscador = claseBuscador;
    }

    public VistaBuscadorProductos(java.awt.Frame parent, boolean modal, boolean buscarInactivos, String lugar, String base) {
        super(parent, modal);
        initComponents();

        this.setSize(java.awt.Toolkit.getDefaultToolkit().getScreenSize().width, java.awt.Toolkit.getDefaultToolkit().getScreenSize().height);

        tblRegistros.setDefaultRenderer(Object.class, new IconCellRenderer());
        instancias = Instancias.getInstancias();
        datos = instancias.getSql().getDatosMaestra();

        baseDatos = base;
        tipo = lugar;
        autoCompletar = new TextAutoCompleter(txtGrupo);

        cargarTablaProductos(buscarInactivos);

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

    public void cargarTablaProductos(boolean buscarInactivos) {

        //Siempre va a estar con la consulta, si es true osea, necesita buscar tambien los falsos ira vacia y buscara todo.
        String consulta = "";

        if (!buscarInactivos) {
            consulta = "Where activo=false ";
        }

        String base = "bdProductos";
        if (baseDatos.equals("productos1")) {
            base = "bdProductos";
        } else if (baseDatos.equals("productos1bodega1")) {
            base = "bdProductosBodega1";
        } else if (baseDatos.equals("productos1bodega2")) {
            base = "bdProductosBodega2";
        } else if (baseDatos.equals("productos1bodega3")) {
            base = "bdProductosBodega3";
        } else if (baseDatos.equals("productos1bodega4")) {
            base = "bdProductosBodega4";
        }

        //Si tiene alguna clase especial para buscar
        if (claseBuscador.equals("Agenda")) {
            if (consulta.equals("")) {
                consulta = "Where claseBuscador='Agenda' ";
            } else {
                consulta = consulta + "And claseBuscador='Agenda' ";
            }
        }

        String grupo = "";
        if (!txtGrupo.getText().equals("")) {
            grupo = txtGrupo.getText();
            if (consulta.equals("")) {
                consulta = "Where Grupo = '" + grupo + "' ";
            } else {
                consulta = consulta + "And Grupo = '" + grupo + "' ";
            }
        }

        if (tipo.equals("adiciones")) {
            if (consulta.equals("")) {
                consulta = "Where grupo = 'GRP-02' ";
            } else {
                consulta = consulta + "And grupo = 'GRP-02' ";
            }
        }

        if (tipo.equals("peluqueria")) {
            if (consulta.equals("")) {
                consulta = "Where grupo = 'GRP-06' ";
            } else {
                consulta = consulta + "And grupo = 'GRP-06' ";
            }
        }

        if (tipo.equals("desdeCosteo")) {
            if (consulta.equals("")) {
                consulta = "Where " + base + ".Usuario = 'FACTURA' OR " + base + ".Usuario = 'COSTEO' ";
            } else {
                consulta = consulta + "And " + base + ".Usuario = 'FACTURA' OR " + base + ".Usuario = 'COSTEO' ";
            }
        }

        if (tipo.equals("parqueadero")) {
            if (consulta.equals("")) {
                consulta = "Where Grupo = 'GRP-0' ";
            } else {
                consulta = consulta + "And Grupo = 'GRP-0' ";
            }
        }

        if (tipo.equals("lavado")) {
            if (consulta.equals("")) {
                consulta = "Where Grupo = 'GRP-01' ";
            } else {
                consulta = consulta + "And Grupo = 'GRP-01' ";
            }
        }

        if (tipo.equals("showRoom")) {
            if (consulta.equals("")) {
                consulta = "Where Grupo = 'GRP-04' ";
            } else {
                consulta = consulta + "And Grupo = 'GRP-04' ";
            }
        }

        if (tipo.equals("sinArmados")) {
            if (consulta.equals("")) {
                consulta = "Where " + base + ".Usuario = 'ADMIN' ";
            } else {
                consulta = consulta + "And " + base + ".Usuario = 'ADMIN' ";
            }
        }

        if (tipo.equals("sinSerial")) {
            if (consulta.equals("")) {
                consulta = "Where (tipoProducto = '' OR tipoProducto is NULL) && " + base + ".Usuario = 'ADMIN' ";
            } else {
                consulta = consulta + "And (tipoProducto = '' OR tipoProducto is NULL) && " + base + ".Usuario = 'ADMIN'  ";
            }
        }

        String pvp1 = "((ROUND((" + base + ".L1 * ((" + base + ".IVA / 100) + 1)),2) + ROUND((" + base + ".L1 * "
                + "((" + base + ".impoconsumoVenta / 100) + 1)),2)) - " + base + ".L1) AS pvp";
        String pvp2 = "((ROUND((" + base + ".L1 * ((" + base + ".IVA / 100) + 1)), 2) + ROUND((" + base + ".L1 * "
                + "((" + base + ".impoconsumoVenta / 100) + 1)),2)) - " + base + ".L1)";

        if (instancias.isPvpConIva()) {
            pvp1 = base + ".L1 AS L1";
            pvp2 = base + ".L1";
        }

        String from = " (" + base + " LEFT JOIN bdultimoponderado ON ((bdultimoponderado.producto = " + base + ".idSistema))) " + consulta;

        String sentenciaSQL1 = " SELECT " + base + ".Codigo AS Codigo, " + base + ".Descripcion AS Descripcion, "
                + "" + base + ".referencia AS referencia, " + pvp1 + ", " + base + ".fisicoInventario AS inventario, " + base + ".ubicacion1 AS ubicacion1, false as sel, "
                + "'' AS campoVacio, " + base + ".grupo AS Grupo, bdultimoponderado.ultimoCosto AS ultimoCosto, " + base + ".idSistema AS idSistema, '' AS campoVacio1, "
                + "" + base + ".Usuario AS Usuario, " + base + ".manejaInventario AS manejaInventario, " + base + ".plu2 AS plu2, " + base + ".indVentas AS indVentas, "
                + "" + base + ".descripcionLarga AS descripcionLarga FROM " + from + "GROUP BY " + base + ".Codigo, " + base + ".Descripcion, " + base + ".referencia, " + pvp2 + ", "
                + "" + base + ".fisicoInventario, " + base + ".ubicacion1, campoVacio, Grupo, ultimoCosto, idSistema, campoVacio1, Usuario, manejaInventario, plu2, "
                + "indVentas, descripcionLarga ORDER BY Descripcion ";

        DefaultTableModel modelo = instancias.getSql().getRegistrosProductos(instancias.isPvpConIva(), sentenciaSQL1, from);

        tblRegistros.setModel(modelo);

        if (tblRegistros.getColumnModel().getColumnCount() > 0) {
            tblRegistros.getColumnModel().getColumn(0).setMinWidth(125);
            tblRegistros.getColumnModel().getColumn(0).setPreferredWidth(125);
            tblRegistros.getColumnModel().getColumn(0).setMaxWidth(200);
            tblRegistros.getColumnModel().getColumn(2).setMinWidth(90);
            tblRegistros.getColumnModel().getColumn(2).setPreferredWidth(90);
            tblRegistros.getColumnModel().getColumn(2).setMaxWidth(90);
            tblRegistros.getColumnModel().getColumn(3).setMinWidth(70);
            tblRegistros.getColumnModel().getColumn(3).setPreferredWidth(80);
            tblRegistros.getColumnModel().getColumn(3).setMaxWidth(130);
            tblRegistros.getColumnModel().getColumn(4).setMinWidth(50);
            tblRegistros.getColumnModel().getColumn(4).setPreferredWidth(70);
            tblRegistros.getColumnModel().getColumn(4).setMaxWidth(130);
            tblRegistros.getColumnModel().getColumn(5).setMinWidth(75);
            tblRegistros.getColumnModel().getColumn(5).setPreferredWidth(105);
            tblRegistros.getColumnModel().getColumn(5).setMaxWidth(130);
            tblRegistros.getColumnModel().getColumn(6).setMinWidth(30);
            tblRegistros.getColumnModel().getColumn(6).setPreferredWidth(30);
            tblRegistros.getColumnModel().getColumn(6).setMaxWidth(30);
            tblRegistros.getColumnModel().getColumn(7).setMinWidth(45);
            tblRegistros.getColumnModel().getColumn(7).setPreferredWidth(45);
            tblRegistros.getColumnModel().getColumn(7).setMaxWidth(45);
            tblRegistros.getColumnModel().getColumn(8).setMinWidth(0);
            tblRegistros.getColumnModel().getColumn(8).setPreferredWidth(0);
            tblRegistros.getColumnModel().getColumn(8).setMaxWidth(0);

            if (instancias.getUsuario().equals("ADMIN")) {
                tblRegistros.getColumnModel().getColumn(9).setMinWidth(70);
                tblRegistros.getColumnModel().getColumn(9).setPreferredWidth(80);
                tblRegistros.getColumnModel().getColumn(9).setMaxWidth(130);
            } else {
                tblRegistros.getColumnModel().getColumn(9).setMinWidth(0);
                tblRegistros.getColumnModel().getColumn(9).setPreferredWidth(0);
                tblRegistros.getColumnModel().getColumn(9).setMaxWidth(0);
            }

            tblRegistros.getColumnModel().getColumn(10).setMinWidth(0);
            tblRegistros.getColumnModel().getColumn(10).setPreferredWidth(0);
            tblRegistros.getColumnModel().getColumn(10).setMaxWidth(0);
            tblRegistros.getColumnModel().getColumn(11).setMinWidth(30);
            tblRegistros.getColumnModel().getColumn(11).setPreferredWidth(30);
            tblRegistros.getColumnModel().getColumn(11).setMaxWidth(30);
            tblRegistros.getColumnModel().getColumn(12).setMinWidth(0);
            tblRegistros.getColumnModel().getColumn(12).setPreferredWidth(0);
            tblRegistros.getColumnModel().getColumn(12).setMaxWidth(0);
            tblRegistros.getColumnModel().getColumn(13).setMinWidth(0);
            tblRegistros.getColumnModel().getColumn(13).setPreferredWidth(0);
            tblRegistros.getColumnModel().getColumn(13).setMaxWidth(0);
            tblRegistros.getColumnModel().getColumn(14).setMinWidth(40);
            tblRegistros.getColumnModel().getColumn(14).setPreferredWidth(40);
            tblRegistros.getColumnModel().getColumn(14).setMaxWidth(40);
            tblRegistros.getColumnModel().getColumn(15).setMinWidth(0);
            tblRegistros.getColumnModel().getColumn(15).setPreferredWidth(0);
            tblRegistros.getColumnModel().getColumn(15).setMaxWidth(0);
            tblRegistros.getColumnModel().getColumn(16).setMinWidth(0);
            tblRegistros.getColumnModel().getColumn(16).setPreferredWidth(0);
            tblRegistros.getColumnModel().getColumn(16).setMaxWidth(0);

        }

        modelo = (DefaultTableModel) tblRegistros.getModel();
        modeloOrdenado = new TableRowSorter<>(modelo);
        tblRegistros.setRowSorter(modeloOrdenado);

//        txtId.requestFocus();
        Icon icono = null;
        ImageIcon fot = new ImageIcon(getClass().getResource("/imagenes/verLogo.png"));
        icono = new ImageIcon(fot.getImage().getScaledInstance(22, 18, Image.SCALE_DEFAULT));

        Icon icono1 = null;
        ImageIcon fot1 = new ImageIcon(getClass().getResource("/imagenes/PLU.png"));
        icono1 = new ImageIcon(fot1.getImage().getScaledInstance(40, 20, Image.SCALE_DEFAULT));

        for (int i = 0; i < tblRegistros.getRowCount(); i++) {

            if (tblRegistros.getValueAt(i, 4) != null) {
                if (tblRegistros.getValueAt(i, 4).equals(",00")) {
                    tblRegistros.setValueAt("0", i, 4);
                }
            }

            if (tblRegistros.getValueAt(i, 12) != null) {
                if (tblRegistros.getValueAt(i, 12).equals("FACTURA")) {
                    tblRegistros.setValueAt("N/A", i, 4);
                }
            }

            if (tblRegistros.getValueAt(i, 13) != null) {
                if (tblRegistros.getValueAt(i, 13).equals("0")) {
                    tblRegistros.setValueAt("N/A", i, 4);
                }
            }

            if (tblRegistros.getValueAt(i, 14) != null) {
                if (tblRegistros.getValueAt(i, 14).equals("1")) {
                    tblRegistros.setValueAt(new JLabel(icono1), i, 14);
                } else {
                    tblRegistros.setValueAt("", i, 14);
                }
            }

            tblRegistros.setValueAt(big.setMoneda(big.getBigDecimal(tblRegistros.getValueAt(i, 3))), i, 3);

            if (tblRegistros.getValueAt(i, 9) == null) {
                tblRegistros.setValueAt("#ERROR", i, 9);
            } else {
                try {
                    tblRegistros.setValueAt(big.setMoneda(big.getMoneda(tblRegistros.getValueAt(i, 9).toString().replace(".", ","))), i, 9);
                } catch (Exception e) {
                    tblRegistros.setValueAt("SIN COSTO", i, 9);
                }
            }

            tblRegistros.setValueAt(new JLabel(icono), i, 11);
        }

        try {
            tblRegistros.getSelectionModel().setSelectionInterval(1, 1);
        } catch (ArrayIndexOutOfBoundsException exp) {
        }

        txtId.requestFocus();
    }

    public VistaFactura getFactura() {
        return factura;
    }

    public void setFactura(VistaFactura factura) {
        this.factura = factura;
    }

    public VistaIngreso getIngreso() {
        return ingreso;
    }

    public void setIngreso(VistaIngreso ingreso) {
        this.ingreso = ingreso;
    }

    public String getOpc() {
        return opc;
    }

    public void setOpc(String opc) {
        this.opc = opc;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        filtro = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        btnEste4 = new javax.swing.JButton();
        btnEste3 = new javax.swing.JButton();
        lbTelefono = new javax.swing.JLabel();
        txtGrupo = new javax.swing.JTextField();
        lbBuscador1 = new javax.swing.JLabel();
        lbBuscador = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblRegistros = new javax.swing.JTable();
        btnIzquierda = new javax.swing.JButton();
        btnDerecha = new javax.swing.JButton();
        btnEste2 = new javax.swing.JButton();
        btnNuevoProducto = new javax.swing.JButton();
        btnIngresar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Buscador de productos...");
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        btnEste4.setBackground(new java.awt.Color(204, 204, 204));
        btnEste4.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        btnEste4.setText("LIMPIAR FILTRO");
        btnEste4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEste4ActionPerformed(evt);
            }
        });

        btnEste3.setBackground(new java.awt.Color(204, 204, 204));
        btnEste3.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        btnEste3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar1.png"))); // NOI18N
        btnEste3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEste3ActionPerformed(evt);
            }
        });

        lbTelefono.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbTelefono.setText("Grupo:");

        txtGrupo.setBackground(new java.awt.Color(255, 204, 204));
        txtGrupo.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtGrupo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtGrupo.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtGrupo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtGrupoFocusGained(evt);
            }
        });
        txtGrupo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtGrupoKeyReleased(evt);
            }
        });

        lbBuscador1.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbBuscador1.setText("Filtro actual:");

        lbBuscador.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbBuscador.setText("Codigo");

        txtId.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        txtId.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtIdMouseClicked(evt);
            }
        });
        txtId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIdKeyReleased(evt);
            }
        });

        tblRegistros.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        tblRegistros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"001", "Celular", null, null, null, null, null, null, null},
                {"002", "Computador", null, null, null, null, null, null, null},
                {"003", "CPU", null, null, null, null, null, null, null},
                {"004", "Smartphone", null, null, null, null, null, null, null}
            },
            new String [] {
                "Codigo", "Descripcion", "Referencia", "P.V.P", "Inv", "Ubicación", "Seleccionar", "Cantidad", "idSistema"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblRegistros.setRowHeight(20);
        tblRegistros.getTableHeader().setReorderingAllowed(false);
        tblRegistros.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblRegistrosMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tblRegistrosMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblRegistrosMousePressed(evt);
            }
        });
        tblRegistros.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblRegistrosKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblRegistros);
        if (tblRegistros.getColumnModel().getColumnCount() > 0) {
            tblRegistros.getColumnModel().getColumn(6).setMinWidth(80);
            tblRegistros.getColumnModel().getColumn(6).setPreferredWidth(80);
            tblRegistros.getColumnModel().getColumn(6).setMaxWidth(80);
            tblRegistros.getColumnModel().getColumn(8).setMinWidth(0);
            tblRegistros.getColumnModel().getColumn(8).setPreferredWidth(0);
            tblRegistros.getColumnModel().getColumn(8).setMaxWidth(0);
        }

        btnIzquierda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/izquierda1.png"))); // NOI18N
        btnIzquierda.setToolTipText("Teclado, tecla izquierda/Filtro anterior");
        btnIzquierda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIzquierdaActionPerformed(evt);
            }
        });
        btnIzquierda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                btnIzquierdaKeyReleased(evt);
            }
        });

        btnDerecha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/derecha1.png"))); // NOI18N
        btnDerecha.setToolTipText("Teclado, tecla derecha/Filtro siguiente");
        btnDerecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDerechaActionPerformed(evt);
            }
        });
        btnDerecha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                btnDerechaKeyReleased(evt);
            }
        });

        btnEste2.setBackground(new java.awt.Color(204, 204, 204));
        btnEste2.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        btnEste2.setText("UNIFICADOS");
        btnEste2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEste2ActionPerformed(evt);
            }
        });

        btnNuevoProducto.setBackground(new java.awt.Color(46, 204, 113));
        btnNuevoProducto.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        btnNuevoProducto.setText("NUEVO");
        btnNuevoProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoProductoActionPerformed(evt);
            }
        });

        btnIngresar.setBackground(new java.awt.Color(204, 204, 204));
        btnIngresar.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        btnIngresar.setText("INGRESAR");
        btnIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel1.setText("DESCRIPCIÓN LARGA:");

        txtDescripcion.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtDescripcion.setText(" ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(3, 3, 3)
                                .addComponent(txtDescripcion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnEste2, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnNuevoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(3, 3, 3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtId, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEste3)
                        .addGap(2, 2, 2)
                        .addComponent(btnEste4, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 175, Short.MAX_VALUE)
                        .addComponent(lbBuscador1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(lbBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnIzquierda, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDerecha, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lbTelefono, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtGrupo, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnEste3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEste4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnDerecha, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(btnIzquierda, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(lbBuscador, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                        .addComponent(lbBuscador1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(2, 2, 2)
                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE)
                .addGap(2, 2, 2)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtDescripcion))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnNuevoProducto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEste2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnIngresar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarActionPerformed
        txtId.setText("");
        tblRegistros.removeEditor();
        modeloOrdenado.setRowFilter(RowFilter.regexFilter("", 0));

        //REVISAR SI INGRESARON ALGUNA CANTIDAD SIN EL CHULO
        for (int i = 0; i < tblRegistros.getRowCount(); i++) {
            if (!tblRegistros.getValueAt(i, 7).equals("")) {
                tblRegistros.setValueAt(true, i, 6);
            }
        }

        //HACEMOS CONTEO DE CUANTOS PRODUCTOS SELECCIONARON
        int cantSeleccionada = 0;
        for (int i = 0; i < tblRegistros.getRowCount(); i++) {
            if ((Boolean) tblRegistros.getValueAt(i, 6)) {
                cantSeleccionada++;
            }
        }

        //CREAMOS OBJETO TIPO MATRIZ, PARA ASIGNAR CODIGO Y CANTIDAD DE LOS PRODUCTOS SELECCIONADOS
        Object[][] cantidad = new Object[cantSeleccionada][2];

        int ser = 0;
        for (int i = 0; i < tblRegistros.getRowCount(); i++) {
            if ((Boolean) tblRegistros.getValueAt(i, 6)) {
                if (tblRegistros.getValueAt(i, 7).equals("")) {
                    cantidad[ser][0] = tblRegistros.getValueAt(i, 10).toString();
                    cantidad[ser][1] = 0;
                    ser++;
                } else {
                    cantidad[ser][0] = tblRegistros.getValueAt(i, 10).toString();
                    cantidad[ser][1] = tblRegistros.getValueAt(i, 7).toString();
                    ser++;
                }
            }
        }

        //SI HAY PRODUCTOS SELECCIONADOS LOS CARGAMOS.
        if (cantSeleccionada > 0) {
            if (opc == null) {
                opc = "";
            }

            if (opc.equals("")) {
                instancias.setValorCampo((String) tblRegistros.getValueAt(tblRegistros.getSelectedRow(), 10));
                this.dispose();
                metodosGenerales.presionarEnter(instancias.getCampoActual());
            } else {
                switch (opc) {
                    case "factura":
                        this.dispose();
                        factura.cargarProductos1(cantidad);
                        break;
                    case "cotizacion":
                        this.dispose();
                        instancias.getCotiza().cargarProductos1(cantidad);
                        break;
                    case "ingreso":
                        this.dispose();
                        instancias.getIngresos().cargarProductos(cantidad);
                        break;
                    case "ordenCompra":
                        this.dispose();
                        instancias.getOrdenCompra().cargarProductos(cantidad);
                        break;
                    case "traslado":
                        this.dispose();
                        instancias.getPrestamos().cargarProductos(cantidad);
                        break;
                    case "trasladoInterno":
                        this.dispose();
                        instancias.getTrasladosInternos().cargarProductos(cantidad);
                        break;
                    case "ajuste":
                        this.dispose();
                        instancias.getVistaAjusteInventario().cargarProductos(cantidad);
                        break;
                    case "diseño":
                        this.dispose();
                        instancias.getCosteo().cargarProductos(cantidad);
                        break;
                    case "producto":
                        this.dispose();
                        instancias.getProductos().cargarProductos(cantidad);
                        break;
                    case "NotaDebito":
                        this.dispose();
                        instancias.getNd().cargarProductos1(cantidad);
                        break;
                }
            }
        }
    }//GEN-LAST:event_btnIngresarActionPerformed

    private void txtIdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdKeyReleased
        filtrar(evt);
    }//GEN-LAST:event_txtIdKeyReleased

    public void filtrar(java.awt.event.KeyEvent evt) {
        try {
            if ((evt.getKeyCode() == 40 || evt.getKeyCode() == 38)) {
                int aumento = -1;

                if (evt.getKeyCode() == 40) {
                    aumento = 1;
                }

                int fila = tblRegistros.getSelectionModel().getMinSelectionIndex() + aumento;
                try {

                    if (fila == tblRegistros.getRowCount() && aumento == 1) {
                        fila = 0;
                    }

                    tblRegistros.getSelectionModel().setSelectionInterval(fila, fila);
                    tblRegistros.scrollRectToVisible(tblRegistros.getCellRect(tblRegistros.getSelectedRow(), 0, true));

                } catch (ArrayIndexOutOfBoundsException exp) {
                }
                return;
            }

            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                tblRegistros.setValueAt(true, tblRegistros.getSelectedRow(), 6);
                btnIngresarActionPerformed(null);
                return;
            }
        } catch (Exception e) {
        }

        if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (pos == 5) {
                pos = 0;
            } else if (pos == 2) {
                pos = 5;
            } else {
                pos = pos + 1;
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            if (pos == 5) {
                pos = 2;
            } else if (pos == 0) {
                pos = 5;
            } else {
                pos = pos - 1;
            }
        } else {
            pos = 0;
        }

        if (pos == 0) {
            modeloOrdenado.setRowFilter(RowFilter.regexFilter("(?i)" + txtId.getText(), 0));
            lbBuscador.setText("Codigo");
            if (tblRegistros.getRowCount() == 0) {
                modeloOrdenado.setRowFilter(RowFilter.regexFilter("(?i)" + txtId.getText(), 1));
                lbBuscador.setText("Descripción");
                if (tblRegistros.getRowCount() == 0) {
                    modeloOrdenado.setRowFilter(RowFilter.regexFilter("(?i)" + txtId.getText(), 2));
                    lbBuscador.setText("Referencia");
                    if (tblRegistros.getRowCount() == 0) {
                        modeloOrdenado.setRowFilter(RowFilter.regexFilter("(?i)" + txtId.getText(), 5));
                        lbBuscador.setText("Ubicación");

                    }
                }
            }
        } else if (pos == 1) {
            lbBuscador.setText("Descripción");
            modeloOrdenado.setRowFilter(RowFilter.regexFilter("(?i)" + txtId.getText(), 1));
        } else if (pos == 2) {
            lbBuscador.setText("Referencia ");
            modeloOrdenado.setRowFilter(RowFilter.regexFilter("(?i)" + txtId.getText(), 2));
        } else if (pos == 5) {
            lbBuscador.setText("Ubicación");
            modeloOrdenado.setRowFilter(RowFilter.regexFilter("(?i)" + txtId.getText(), 5));
        }

        try {
            tblRegistros.getSelectionModel().setSelectionInterval(0, 0);
        } catch (ArrayIndexOutOfBoundsException exp) {
        }
    }

    private void tblRegistrosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblRegistrosMouseClicked
        int filaSeleccionada = tblRegistros.getSelectedRow();
        int columnaSeleccionada = tblRegistros.getSelectedColumn();

        if (evt.getClickCount() > 1) {
            btnIngresarActionPerformed(null);
        }

        if (evt.getClickCount() > 0) {
            txtDescripcion.setText(tblRegistros.getValueAt(tblRegistros.getSelectedRow(), 16).toString());

            if (columnaSeleccionada == 11) {
                txtId.setText("");
                txtId.requestFocus();
            } else if (columnaSeleccionada == 11) {
                verLogo verLogo = new verLogo(null, rootPaneCheckingEnabled, tblRegistros.getValueAt(tblRegistros.getSelectedRow(), 10).toString());
                verLogo.setLocationRelativeTo(null);
                verLogo.setVisible(true);
            } else {
                boolean estadoColumna = Boolean.parseBoolean(tblRegistros.getValueAt(tblRegistros.getSelectedRow(), 6).toString());
                tblRegistros.setValueAt(!estadoColumna, filaSeleccionada, 6);
                if (estadoColumna) {
                    tblRegistros.setValueAt("", filaSeleccionada, 7);
                } else {
                    if (opc != null && !opc.isEmpty()) {
                        tblRegistros.editCellAt(tblRegistros.getSelectedRow(), 7);
                        tblRegistros.setColumnSelectionInterval(7, 7);
                        tblRegistros.transferFocus();
                        tblRegistros.setValueAt(true, tblRegistros.getSelectedRow(), 6);
                        txtId.setText("");
                    }
                }
            }
        }
    }//GEN-LAST:event_tblRegistrosMouseClicked

    private void tblRegistrosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblRegistrosKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER && tblRegistros.getSelectedColumn() == 7) {
            txtId.requestFocus();
        }

//        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
//            if (tblRegistros.getSelectedRow() > 0) {
//                tblRegistros.setValueAt(true, tblRegistros.getSelectedRow(), 6);
//                btnEsteActionPerformed(null);
//            }
//        }
        if (tblRegistros.getSelectedColumn() == 7) {
            if (!tblRegistros.getValueAt(tblRegistros.getSelectedRow(), 7).equals("")) {
                tblRegistros.setValueAt(true, tblRegistros.getSelectedRow(), 6);
                tblRegistros.removeEditor();
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            if (tblRegistros.getRowCount() > 1) {
                tblRegistros.getSelectionModel().setSelectionInterval(tblRegistros.getSelectionModel().getMinSelectionIndex() - 1, tblRegistros.getSelectionModel().getMinSelectionIndex() - 1);
                tblRegistros.setValueAt(true, tblRegistros.getSelectionModel().getMinSelectionIndex() - 1, tblRegistros.getSelectionModel().getMinSelectionIndex() - 1);
            }

            btnIngresarActionPerformed(null);
        }
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            tblRegistros.scrollRectToVisible(tblRegistros.getCellRect(tblRegistros.getSelectedRow(), 0, true));
        }
    }//GEN-LAST:event_tblRegistrosKeyReleased

    private void txtIdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtIdMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdMouseClicked

    private void btnNuevoProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoProductoActionPerformed
        nuevoProducto nuevo = new nuevoProducto(null, true, new metodosGenerales(), instancias, opc);

        if (opc.equals("ingreso") || opc.equals("ordenCompra")) {
            nuevo.setIngreso(ingreso);
        } else if (opc.equals("factura")) {
            nuevo.setFactura(factura);
        }

        nuevo.setVisible(true);
    }//GEN-LAST:event_btnNuevoProductoActionPerformed

    private void txtGrupoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGrupoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            ndGrupo nodo = instancias.getSql().getDatosGrupo2(txtGrupo.getText());

            if (nodo.getCodigo() != null) {
                cargarTablaProductos(false);
            } else {
                ventanaGrupos(txtGrupo.getText());
            }
        } else {
            txtGrupo.setText("");
        }
    }//GEN-LAST:event_txtGrupoKeyReleased

    private void btnEste2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEste2ActionPerformed
        buscProductos2 buscar = new buscProductos2(null, rootPaneCheckingEnabled);
        buscar.setLocationRelativeTo(null);
        Object[][] datos = instancias.getSql().getDatosProductosUnificados(tblRegistros.getValueAt(tblRegistros.getSelectedRow(), 10).toString());
        buscar.setInstancia(instancias, datos);
        buscar.setBuscador(this);
        buscar.show();
    }//GEN-LAST:event_btnEste2ActionPerformed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed

    }//GEN-LAST:event_formKeyPressed

    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_formKeyReleased

    private void btnDerechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDerechaActionPerformed
        KeyEvent x = new KeyEvent(this, WIDTH, WIDTH, WIDTH, KeyEvent.VK_RIGHT);
        txtIdKeyReleased(x);
    }//GEN-LAST:event_btnDerechaActionPerformed

    private void btnDerechaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnDerechaKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            KeyEvent x = new KeyEvent(this, WIDTH, WIDTH, WIDTH, KeyEvent.VK_RIGHT);
            txtIdKeyReleased(x);
        }
        if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            KeyEvent x = new KeyEvent(this, WIDTH, WIDTH, WIDTH, KeyEvent.VK_LEFT);
            txtIdKeyReleased(x);
        }
    }//GEN-LAST:event_btnDerechaKeyReleased

    private void btnIzquierdaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIzquierdaActionPerformed
        KeyEvent x = new KeyEvent(this, WIDTH, WIDTH, WIDTH, KeyEvent.VK_LEFT);
        txtIdKeyReleased(x);
    }//GEN-LAST:event_btnIzquierdaActionPerformed

    private void btnIzquierdaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnIzquierdaKeyReleased

        if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            KeyEvent x = new KeyEvent(this, WIDTH, WIDTH, WIDTH, KeyEvent.VK_LEFT);
            txtIdKeyReleased(x);
        }
        if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            KeyEvent x = new KeyEvent(this, WIDTH, WIDTH, WIDTH, KeyEvent.VK_RIGHT);
            txtIdKeyReleased(x);
        }
    }//GEN-LAST:event_btnIzquierdaKeyReleased

    private void tblRegistrosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblRegistrosMouseEntered
        int columna = tblRegistros.getSelectedColumn();

        if (evt.getClickCount() >= 1 && columna == 8) {
            txtId.setText("");
            txtId.requestFocus();
        }
    }//GEN-LAST:event_tblRegistrosMouseEntered

    private void tblRegistrosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblRegistrosMousePressed
        int columna = tblRegistros.getSelectedColumn();

        if (evt.getClickCount() >= 1 && columna == 8) {
            txtId.setText("");
            txtId.requestFocus();
        }
    }//GEN-LAST:event_tblRegistrosMousePressed

    private void btnEste3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEste3ActionPerformed
        ventanaGrupos(txtGrupo.getText());
    }//GEN-LAST:event_btnEste3ActionPerformed

    private void btnEste4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEste4ActionPerformed
        txtGrupo.setText("");
        cargarTablaProductos(false);
    }//GEN-LAST:event_btnEste4ActionPerformed

    private void txtGrupoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtGrupoFocusGained
        cargarTablaProductos(false);
    }//GEN-LAST:event_txtGrupoFocusGained

    public void setSeleccionados(String cod) {
        for (int j = 0; j < modelo.getRowCount(); j++) {
            if (modelo.getValueAt(j, 10).equals(cod)) {
                modelo.setValueAt(true, j, 6);
                break;
            }
        }
    }

    public void ventanaGrupos(String codigo) {
        buscGrupos buscar = new buscGrupos(null, true, "Buscador");
        buscar.setLocationRelativeTo(null);
        instancias.setBuscGrupos(buscar);
        instancias.setCampoActual(txtGrupo);
        txtGrupo.requestFocus();
//        cargarTablaProductos(false);
        buscar.show();
    }

    public void setSeleccionados(Vector v) {
        for (int i = 0; i < v.size(); i++) {
            for (int j = 0; j < modelo.getRowCount(); j++) {
                if (modelo.getValueAt(j, 10).equals(v.get(i))) {
                    modelo.setValueAt(true, j, 6);
                    break;
                }
            }
        }
    }

    /**
     *
     * @param instancia
     */
    public void noEncontrado(String codigo) {
        txtId.setText(codigo);
        modeloOrdenado.setRowFilter(RowFilter.regexFilter("(?i)" + codigo, 1));
        try {
            tblRegistros.getSelectionModel().setSelectionInterval(0, 0);
        } catch (ArrayIndexOutOfBoundsException exp) {
        }
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
            java.util.logging.Logger.getLogger(VistaBuscadorProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaBuscadorProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaBuscadorProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaBuscadorProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                VistaBuscadorProductos dialog = new VistaBuscadorProductos(new javax.swing.JFrame(), true, false, null, null);
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
    private javax.swing.JButton btnDerecha;
    private javax.swing.JButton btnEste2;
    private javax.swing.JButton btnEste3;
    private javax.swing.JButton btnEste4;
    private javax.swing.JButton btnIngresar;
    private javax.swing.JButton btnIzquierda;
    private javax.swing.JButton btnNuevoProducto;
    private javax.swing.ButtonGroup filtro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbBuscador;
    private javax.swing.JLabel lbBuscador1;
    private javax.swing.JLabel lbTelefono;
    private javax.swing.JTable tblRegistros;
    private javax.swing.JLabel txtDescripcion;
    private javax.swing.JTextField txtGrupo;
    private javax.swing.JTextField txtId;
    // End of variables declaration//GEN-END:variables
//
//    private void registerKeyboardAction(ActionListener accionTeclas, String cerrar, KeyStroke CTRL_G, int WHEN_IN_FOCUSED_WINDOW) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
}
