package Vistas.Menu;

import Controlador.Alertas.controladorAlertas;
import Controlador.FacturacionElectronica.controladorCrearJSONCreacion;
import Controlador.FacturacionElectronica.controladorFacturacionElectronica;
import Modelos.JSONCreacion.modeloCreacion;
import Validaciones.Menu.validacionesMenu;
import Vistas.Buscadores.buscadorClientes;
import clases.Instancias;
import clases.big;
import clases.cambiarColorTabla;
import clases.metodosGenerales;
import java.awt.event.KeyEvent;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import Modelos.Configuracion.modeloConfiguracion;
import org.json.JSONException;

public class vistaMenu extends javax.swing.JFrame {

    private final controladorAlertas alertas = new controladorAlertas();
    private final controladorCrearJSONCreacion controladorJson = new controladorCrearJSONCreacion();
    private final controladorFacturacionElectronica controladorFacturacion = new controladorFacturacionElectronica();
    private int NUMERO_FACTURAS_ELECTRONICAS_DISPONIBLES = 0;
    private final metodosGenerales metodos;
    private Instancias instancias;
    TableRowSorter modeloOrdenado;
    TableRowSorter modeloOrdenado1;
    TableRowSorter modeloOrdenadoPaquetes;
    String codigoFinal = "";

    public vistaMenu() {
        initComponents();

        this.setLocationRelativeTo(null);

        metodos = new metodosGenerales();
        instancias = Instancias.getInstancias();

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                cerrar();
            }
        });

        tblClientes.setDefaultRenderer(Object.class, new cambiarColorTabla(0));
        tblPagos.setDefaultRenderer(Object.class, new cambiarColorTabla(1));

        TableColumn tcr1 = tblModulos.getColumnModel().getColumn(1);
        TableCellEditor tcer1 = new DefaultCellEditor(cmbOpciones);
        tcr1.setCellEditor(tcer1);

        limpiar();
        cargarDepartamentos();
        actualizarPagos();
        actualizarClientes();
        actualizarPaquetes();

        dtFechaInicio.setFormat(2);
        dtFechaInicio.setText(metodosGenerales.fecha());
    }

    public void limpiar() {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtNit1.setText("");
        txtFechaInicio.setText("");
        txtTelefono.setText("");
        txtInformacionLegal.setText("");
        txtDiasAntesBloqueo.setText("0");
        txtDiasGabelaBloqueo.setText("0");
        NUMERO_FACTURAS_ELECTRONICAS_DISPONIBLES = 0;

        for (int i = 0; i < tblModulos.getRowCount(); i++) {
            tblModulos.setValueAt("NO", i, 1);
        }

        btnGuardarPermisos.setEnabled(false);
        txtCodigo.setEnabled(true);
    }

    public void actualizarClientes() {
        DefaultTableModel modelo = (DefaultTableModel) tblClientes.getModel();
        while (tblClientes.getRowCount() > 0) {
            modelo.removeRow(0);
        }

        Object[][] clientes = instancias.getSql().obtenerRegistrosClientes();
        for (Object[] cliente : clientes) {
            modelo.addRow(cliente);
        }

        modeloOrdenado = new TableRowSorter<>(modelo);
        tblClientes.setRowSorter(modeloOrdenado);
    }

    private void cargarDepartamentos() {
        Object[][] dep = instancias.getSql().obtenerDepartamentos();
        cmbDepartamento.removeAllItems();

        if (dep != null) {
            for (int i = 0; i < dep.length; i++) {
                if (null != dep[i][0]) {
                    cmbDepartamento.addItem(dep[i][0].toString().toUpperCase());
                }
            }
        }

        cmbDepartamento.setSelectedItem("ANTIOQUIA");
        consultarMunicipios("ANTIOQUIA");
    }

    private void consultarMunicipios(String departamento) {
        Object[][] municipios = instancias.getSql().obtenerMunicipiosPorDepartamento(departamento);
        cmbCiudad.removeAllItems();
        cmbCiudad.addItem(" ");
        for (int i = 0; i < municipios.length; i++) {
            if (null != municipios[i][0]) {
                cmbCiudad.addItem(municipios[i][0]);
            }
        }
    }

    private void actualizarPaquetes() {
        DefaultTableModel modelo1 = (DefaultTableModel) tblPaquetes.getModel();
        while (tblPaquetes.getRowCount() > 0) {
            modelo1.removeRow(0);
        }

        Object[][] clientes;
        if (cmbTipoPaquete.getSelectedIndex() == 0) {
            clientes = instancias.getSql().obtenerRegistroPaquete();
            tblPaquetes.getColumnModel().getColumn(4).setMinWidth(0);
            tblPaquetes.getColumnModel().getColumn(4).setPreferredWidth(0);
            tblPaquetes.getColumnModel().getColumn(4).setMaxWidth(0);
            tblPaquetes.getColumnModel().getColumn(5).setMinWidth(0);
            tblPaquetes.getColumnModel().getColumn(5).setPreferredWidth(0);
            tblPaquetes.getColumnModel().getColumn(5).setMaxWidth(0);
            tblPaquetes.getColumnModel().getColumn(6).setMinWidth(0);
            tblPaquetes.getColumnModel().getColumn(6).setPreferredWidth(0);
            tblPaquetes.getColumnModel().getColumn(6).setMaxWidth(0);
        } else {
            clientes = instancias.getSql().obtenerHistorialPaquetes();
            tblPaquetes.getColumnModel().getColumn(4).setMinWidth(100);
            tblPaquetes.getColumnModel().getColumn(4).setPreferredWidth(130);
            tblPaquetes.getColumnModel().getColumn(4).setMaxWidth(220);
            tblPaquetes.getColumnModel().getColumn(5).setMinWidth(100);
            tblPaquetes.getColumnModel().getColumn(5).setPreferredWidth(130);
            tblPaquetes.getColumnModel().getColumn(5).setMaxWidth(220);
            tblPaquetes.getColumnModel().getColumn(6).setMinWidth(120);
            tblPaquetes.getColumnModel().getColumn(6).setPreferredWidth(120);
            tblPaquetes.getColumnModel().getColumn(6).setMaxWidth(120);
        }

        int contador = 0;
        for (Object[] cliente : clientes) {
            if (null != cliente[0]) {
                modelo1.addRow(cliente);
                if (cmbTipoPaquete.getSelectedIndex() == 1) {
                    tblPaquetes.setValueAt(big.setMoneda(big.getBigDecimal(tblPaquetes.getValueAt(contador, 4))), contador, 4);
                    tblPaquetes.setValueAt(big.setMoneda(big.getBigDecimal(tblPaquetes.getValueAt(contador, 5))), contador, 5);
                }
            }

            contador++;
        }

        modeloOrdenadoPaquetes = new TableRowSorter<>(modelo1);
        tblPaquetes.setRowSorter(modeloOrdenadoPaquetes);
    }

    public void actualizarPagos() {

        DefaultTableModel modelo1 = (DefaultTableModel) tblPagos.getModel();
        while (tblPagos.getRowCount() > 0) {
            modelo1.removeRow(0);
        }

        Object[][] clientes = instancias.getSql().obtenerRegistroPagos(cmbEstadosPago.getSelectedItem().toString());
        int contador = 0;
        for (Object[] cliente : clientes) {
            modelo1.addRow(cliente);
            String fechaActual = metodosGenerales.fecha();
            String fechaLimite = clientes[contador][3].toString();
            int anterioridad = Integer.parseInt(clientes[contador][4].toString());
            long diferenciaDias = metodos.restarFecha(fechaLimite, fechaActual) * -1;

            if (cmbEstadosPago.getSelectedIndex() == 0) {
                if (diferenciaDias < 0) {
                    tblPagos.setValueAt("Vencido", contador, 7);
                } else if (diferenciaDias <= anterioridad) {
                    tblPagos.setValueAt("Pronto a vencerse", contador, 7);
                } else {
                    tblPagos.setValueAt("OK", contador, 7);
                }
            }

            contador++;
        }

        if (cmbEstadosPago.getSelectedIndex() == 0) {
            tblPagos.getColumnModel().getColumn(6).setMinWidth(0);
            tblPagos.getColumnModel().getColumn(6).setPreferredWidth(0);
            tblPagos.getColumnModel().getColumn(6).setMaxWidth(0);
            tblPagos.getColumnModel().getColumn(7).setMinWidth(160);
            tblPagos.getColumnModel().getColumn(7).setPreferredWidth(160);
            tblPagos.getColumnModel().getColumn(7).setMaxWidth(160);
        } else {
            tblPagos.getColumnModel().getColumn(6).setMinWidth(130);
            tblPagos.getColumnModel().getColumn(6).setPreferredWidth(130);
            tblPagos.getColumnModel().getColumn(6).setMaxWidth(130);
            tblPagos.getColumnModel().getColumn(7).setMinWidth(0);
            tblPagos.getColumnModel().getColumn(7).setPreferredWidth(0);
            tblPagos.getColumnModel().getColumn(7).setMaxWidth(0);
        }

        modeloOrdenado1 = new TableRowSorter<>(modelo1);
        tblPagos.setRowSorter(modeloOrdenado1);
    }

    private void cerrar() {
        if (alertas.option("¿Desea salir?")) {
            System.exit(0);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grpRegimen = new javax.swing.ButtonGroup();
        grpDispositivoUSB = new javax.swing.ButtonGroup();
        grpOrdenesServicio = new javax.swing.ButtonGroup();
        grpTipoOrden = new javax.swing.ButtonGroup();
        grpCreditos = new javax.swing.ButtonGroup();
        grpPlanSepare = new javax.swing.ButtonGroup();
        grpPedidos = new javax.swing.ButtonGroup();
        grpTipoImpresion = new javax.swing.ButtonGroup();
        grpInvBodegas = new javax.swing.ButtonGroup();
        grpProductosSerial = new javax.swing.ButtonGroup();
        grpEmbarcaciones = new javax.swing.ButtonGroup();
        grpCongeladas = new javax.swing.ButtonGroup();
        grpMedico = new javax.swing.ButtonGroup();
        grpVeterinaria = new javax.swing.ButtonGroup();
        grpParqueadero = new javax.swing.ButtonGroup();
        grpAgenda = new javax.swing.ButtonGroup();
        grpRestaurante = new javax.swing.ButtonGroup();
        grpRecordatorios = new javax.swing.ButtonGroup();
        grpLaboratorio = new javax.swing.ButtonGroup();
        grpOftalmologia = new javax.swing.ButtonGroup();
        grpFacturaLote = new javax.swing.ButtonGroup();
        grpPTM = new javax.swing.ButtonGroup();
        grpPrueba = new javax.swing.ButtonGroup();
        grpFEletronica = new javax.swing.ButtonGroup();
        grpContable = new javax.swing.ButtonGroup();
        jPanel6 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        txtClienteId = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtClienteNit = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtClienteNombre = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        lbNit = new javax.swing.JLabel();
        txtIdentificacion = new javax.swing.JTextField();
        lbDepartamento3 = new javax.swing.JLabel();
        cmbNaturaleza = new javax.swing.JComboBox();
        lbTipo = new javax.swing.JLabel();
        cmbTipoIdentificacion = new javax.swing.JComboBox();
        lbRazon = new javax.swing.JLabel();
        txtRazonSocial = new javax.swing.JTextField();
        lbPNombre = new javax.swing.JLabel();
        txtPrimerNombre = new javax.swing.JTextField();
        lbApellido = new javax.swing.JLabel();
        txtPrimerApellido = new javax.swing.JTextField();
        lbSapellido = new javax.swing.JLabel();
        lbSNombre = new javax.swing.JLabel();
        txtSegundoNombre = new javax.swing.JTextField();
        txtSegundoApellido = new javax.swing.JTextField();
        lbSNombre1 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        lbSapellido1 = new javax.swing.JLabel();
        lbSapellido2 = new javax.swing.JLabel();
        txtEmailErrores = new javax.swing.JTextField();
        lbSapellido3 = new javax.swing.JLabel();
        txtEmailEmisor = new javax.swing.JTextField();
        lbSapellido4 = new javax.swing.JLabel();
        txtEmailDefecto = new javax.swing.JTextField();
        lbDepartamento = new javax.swing.JLabel();
        cmbDepartamento = new javax.swing.JComboBox();
        lbCiudad = new javax.swing.JLabel();
        cmbCiudad = new javax.swing.JComboBox();
        lbSapellido5 = new javax.swing.JLabel();
        txtSitioWeb = new javax.swing.JTextField();
        lbDepartamento5 = new javax.swing.JLabel();
        cmbCodigoPostal = new javax.swing.JComboBox();
        lbDepartamento6 = new javax.swing.JLabel();
        cmbTipoRegimen = new javax.swing.JComboBox();
        lbSapellido6 = new javax.swing.JLabel();
        txtCodigoCiiu = new javax.swing.JTextField();
        lbDepartamento7 = new javax.swing.JLabel();
        cmbObligaciones = new javax.swing.JComboBox();
        lbDepartamento8 = new javax.swing.JLabel();
        cmbTributario = new javax.swing.JComboBox();
        lbSapellido7 = new javax.swing.JLabel();
        txtLogo = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        dtFechaInicio = new datechooser.beans.DateChooserCombo();
        txtCelular = new javax.swing.JTextField();
        btnGuardarCliente = new javax.swing.JButton();
        btnLimpiarClientes = new javax.swing.JButton();
        chkClienteFacturacionElectronica = new javax.swing.JCheckBox();
        lbSapellido8 = new javax.swing.JLabel();
        txtIdentificadorPruebasDian = new javax.swing.JTextField();
        chkGenerarRegistroPruebas = new javax.swing.JCheckBox();
        jPanel7 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        btnBusProd = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        txtNit1 = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        jPanel10 = new javax.swing.JPanel();
        btnGuardarPermisos = new javax.swing.JButton();
        jPanel45 = new javax.swing.JPanel();
        lbNit9 = new javax.swing.JLabel();
        jScrollPane16 = new javax.swing.JScrollPane();
        tblModulos = new javax.swing.JTable();
        jPanel46 = new javax.swing.JPanel();
        lbNit10 = new javax.swing.JLabel();
        rdComun = new javax.swing.JRadioButton();
        rdSimplificado = new javax.swing.JRadioButton();
        jPanel47 = new javax.swing.JPanel();
        lbNit11 = new javax.swing.JLabel();
        cmbTipoImpresiones = new javax.swing.JComboBox<>();
        lbTelefono31 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtInformacionLegal = new javax.swing.JTextArea();
        jPanel17 = new javax.swing.JPanel();
        lbNit13 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtDiasAntesBloqueo = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtDiasGabelaBloqueo = new javax.swing.JTextField();
        pnlOculto = new javax.swing.JPanel();
        cmbOpcionesSINO = new javax.swing.JComboBox<>();
        btnLimpiar = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txtFechaInicio = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblPagos = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        txtPagosId = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtPagosNit = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtPagosNombre = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        cmbEstadosPago = new javax.swing.JComboBox<>();
        jPanel8 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        txtNumeroFacturasACargar = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        txtValorUnitario = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        txtValorTotal = new javax.swing.JTextField();
        btnLimpiar1 = new javax.swing.JButton();
        btnGuardarPaquete = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        txtCodigo1 = new javax.swing.JTextField();
        btnBusProd1 = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel24 = new javax.swing.JLabel();
        txtNombre1 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        txtIdentificacion1 = new javax.swing.JTextField();
        cmbTipoPaquete = new javax.swing.JComboBox<>();
        jLabel29 = new javax.swing.JLabel();
        txtDisponibles = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblPaquetes = new javax.swing.JTable();
        jPanel12 = new javax.swing.JPanel();
        txtPagosId1 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtPagosNit1 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtPagosNombre1 = new javax.swing.JTextField();
        cmbOpciones = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("HS.PAGOS");
        setBackground(new java.awt.Color(255, 255, 255));
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/icono.png")).getImage());
        setMinimumSize(new java.awt.Dimension(1177, 317));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                formComponentHidden(evt);
            }
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });

        jPanel6.setBackground(new java.awt.Color(242, 244, 244));

        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });
        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        tblClientes.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nit", "Nombre", "Celular", "Fecha inicio"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblClientes.setRowHeight(24);
        tblClientes.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblClientes);
        if (tblClientes.getColumnModel().getColumnCount() > 0) {
            tblClientes.getColumnModel().getColumn(0).setPreferredWidth(150);
            tblClientes.getColumnModel().getColumn(1).setMinWidth(120);
            tblClientes.getColumnModel().getColumn(1).setPreferredWidth(200);
            tblClientes.getColumnModel().getColumn(1).setMaxWidth(300);
            tblClientes.getColumnModel().getColumn(3).setMinWidth(130);
            tblClientes.getColumnModel().getColumn(3).setPreferredWidth(130);
            tblClientes.getColumnModel().getColumn(3).setMaxWidth(130);
            tblClientes.getColumnModel().getColumn(4).setMinWidth(120);
            tblClientes.getColumnModel().getColumn(4).setPreferredWidth(120);
            tblClientes.getColumnModel().getColumn(4).setMaxWidth(120);
        }

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Buscador", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        txtClienteId.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtClienteId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtClienteIdKeyReleased(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel1.setText("ID:");

        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel2.setText("NIT:");

        txtClienteNit.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtClienteNit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtClienteNitKeyReleased(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel3.setText("NOMBRE:");

        txtClienteNombre.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtClienteNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtClienteNombreKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtClienteId, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtClienteNit, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtClienteNombre)
                .addGap(166, 166, 166))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtClienteId)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtClienteNit)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtClienteNombre))
                .addGap(19, 19, 19))
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        lbNit.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbNit.setText("Identificación:*");
        lbNit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lbNitKeyReleased(evt);
            }
        });

        txtIdentificacion.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtIdentificacion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtIdentificacion.setName("CC/NIT"); // NOI18N
        txtIdentificacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdentificacionActionPerformed(evt);
            }
        });
        txtIdentificacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIdentificacionKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIdentificacionKeyTyped(evt);
            }
        });

        lbDepartamento3.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDepartamento3.setText("Naturaleza: *");

        cmbNaturaleza.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        cmbNaturaleza.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "JURIDICA", "NATURAL" }));

        lbTipo.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTipo.setText("Tipo id: *");

        cmbTipoIdentificacion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "REGISTRO_CIVIL", "TARJETA_IDENTIDAD", "CEDULA_CIUDADANIA", "TARJETA_EXTRANJERIA", "CEDULA_EXTRANJERIA", "NIT", "PASAPORTE", "DOC_ID_EXTRANJERO", "PEP", "NIT_OTRO_PAIS", "PPT", "NUIP" }));
        cmbTipoIdentificacion.setName("Tipo"); // NOI18N

        lbRazon.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbRazon.setText("Razón social: *");

        txtRazonSocial.setEditable(false);
        txtRazonSocial.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtRazonSocial.setName("Razón social"); // NOI18N
        txtRazonSocial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRazonSocialActionPerformed(evt);
            }
        });
        txtRazonSocial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtRazonSocialKeyReleased(evt);
            }
        });

        lbPNombre.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbPNombre.setText("P.Nombre:     ");

        txtPrimerNombre.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtPrimerNombre.setName("Primer nombre"); // NOI18N
        txtPrimerNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPrimerNombreKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrimerNombreKeyTyped(evt);
            }
        });

        lbApellido.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbApellido.setText("P.Apellido:   ");

        txtPrimerApellido.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtPrimerApellido.setName("Primer apellido"); // NOI18N
        txtPrimerApellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrimerApellidoActionPerformed(evt);
            }
        });
        txtPrimerApellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPrimerApellidoKeyReleased(evt);
            }
        });

        lbSapellido.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbSapellido.setText("S.Apellido:");

        lbSNombre.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbSNombre.setText("S.Nombre:");

        txtSegundoNombre.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtSegundoNombre.setName("Segundo nombre"); // NOI18N
        txtSegundoNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSegundoNombreActionPerformed(evt);
            }
        });
        txtSegundoNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSegundoNombreKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSegundoNombreKeyTyped(evt);
            }
        });

        txtSegundoApellido.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtSegundoApellido.setName("Segundo apellido"); // NOI18N
        txtSegundoApellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSegundoApellidoKeyReleased(evt);
            }
        });

        lbSNombre1.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbSNombre1.setText("Dirección:");

        txtDireccion.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtDireccion.setName("Segundo nombre"); // NOI18N
        txtDireccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDireccionActionPerformed(evt);
            }
        });
        txtDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDireccionKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDireccionKeyTyped(evt);
            }
        });

        lbSapellido1.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbSapellido1.setText("Celular:");

        lbSapellido2.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbSapellido2.setText("Email al que llegan los errores:");

        txtEmailErrores.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtEmailErrores.setName("Segundo apellido"); // NOI18N
        txtEmailErrores.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEmailErroresKeyReleased(evt);
            }
        });

        lbSapellido3.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbSapellido3.setText("Email emisor correos:");

        txtEmailEmisor.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtEmailEmisor.setName("Segundo apellido"); // NOI18N
        txtEmailEmisor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailEmisorActionPerformed(evt);
            }
        });
        txtEmailEmisor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEmailEmisorKeyReleased(evt);
            }
        });

        lbSapellido4.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbSapellido4.setText("Email defecto, en caso de rebote:");

        txtEmailDefecto.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtEmailDefecto.setName("Segundo apellido"); // NOI18N
        txtEmailDefecto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEmailDefectoKeyReleased(evt);
            }
        });

        lbDepartamento.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDepartamento.setText("Departamento:*");

        cmbDepartamento.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        cmbDepartamento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "DEPARTAMENTOS" }));
        cmbDepartamento.setName("Departamento"); // NOI18N
        cmbDepartamento.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbDepartamentoItemStateChanged(evt);
            }
        });

        lbCiudad.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbCiudad.setText("Ciudad:  *");

        cmbCiudad.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        cmbCiudad.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " " }));
        cmbCiudad.setName("Ciudad"); // NOI18N
        cmbCiudad.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbCiudadItemStateChanged(evt);
            }
        });

        lbSapellido5.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbSapellido5.setText("Sitio Web:");

        txtSitioWeb.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtSitioWeb.setName("Segundo apellido"); // NOI18N
        txtSitioWeb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSitioWebKeyReleased(evt);
            }
        });

        lbDepartamento5.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDepartamento5.setText("Cod. postal: *");

        cmbCodigoPostal.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        cmbCodigoPostal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " " }));
        cmbCodigoPostal.setName("Ciudad"); // NOI18N
        cmbCodigoPostal.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbCodigoPostalItemStateChanged(evt);
            }
        });

        lbDepartamento6.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDepartamento6.setText("Tipo regimen:");

        cmbTipoRegimen.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        cmbTipoRegimen.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "NO_RESPONSABLE_IVA", "RESPONSABLE_IVA" }));
        cmbTipoRegimen.setName("Ciudad"); // NOI18N
        cmbTipoRegimen.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbTipoRegimenItemStateChanged(evt);
            }
        });

        lbSapellido6.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbSapellido6.setText("Codigo CIIU:");

        txtCodigoCiiu.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtCodigoCiiu.setName("Segundo apellido"); // NOI18N
        txtCodigoCiiu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoCiiuKeyReleased(evt);
            }
        });

        lbDepartamento7.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDepartamento7.setText("Código obligaciones:");

        cmbObligaciones.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        cmbObligaciones.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "O-13 / Gran contribuyente", "O-15 / Autorretenedor", "O-23 / Agente de rentención IVA", "O-47 / Régimen simple de tributación", "R-99-PN / No responsable" }));
        cmbObligaciones.setName("Ciudad"); // NOI18N
        cmbObligaciones.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbObligacionesItemStateChanged(evt);
            }
        });

        lbDepartamento8.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDepartamento8.setText("Código tributario:");

        cmbTributario.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        cmbTributario.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01 / IVA", "04 / INC", "ZA / IVA e INC", "ZZ / No aplica" }));
        cmbTributario.setName("Ciudad"); // NOI18N
        cmbTributario.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbTributarioItemStateChanged(evt);
            }
        });

        lbSapellido7.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbSapellido7.setText("Logo Base64:");

        txtLogo.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtLogo.setName("Segundo apellido"); // NOI18N
        txtLogo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtLogoKeyReleased(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel10.setText("Fecha inicio");

        dtFechaInicio.setFieldFont(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 12));

        txtCelular.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtCelular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCelularActionPerformed(evt);
            }
        });
        txtCelular.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCelularKeyReleased(evt);
            }
        });

        btnGuardarCliente.setBackground(new java.awt.Color(46, 204, 113));
        btnGuardarCliente.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnGuardarCliente.setText("GUARDAR");
        btnGuardarCliente.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnGuardarCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardarCliente.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnGuardarCliente.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnGuardarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarClienteActionPerformed(evt);
            }
        });

        btnLimpiarClientes.setBackground(new java.awt.Color(255, 204, 204));
        btnLimpiarClientes.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnLimpiarClientes.setText("LIMPIAR");
        btnLimpiarClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarClientesActionPerformed(evt);
            }
        });

        chkClienteFacturacionElectronica.setSelected(true);
        chkClienteFacturacionElectronica.setText("Generar Cliente Facturación Electrónica");

        lbSapellido8.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbSapellido8.setText("Identificador pruebas DIAN");

        txtIdentificadorPruebasDian.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtIdentificadorPruebasDian.setName("Segundo apellido"); // NOI18N
        txtIdentificadorPruebasDian.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIdentificadorPruebasDianKeyReleased(evt);
            }
        });

        chkGenerarRegistroPruebas.setText("Generar registro para pruebas");
        chkGenerarRegistroPruebas.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkGenerarRegistroPruebasItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lbPNombre, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbRazon, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                            .addComponent(lbTipo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbApellido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbSNombre1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtPrimerNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                                    .addComponent(txtPrimerApellido)
                                    .addComponent(txtDireccion))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(lbSNombre, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbSapellido, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                                    .addComponent(lbSapellido1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtSegundoApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtSegundoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(cmbTipoIdentificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lbNit)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtIdentificacion))
                            .addComponent(txtRazonSocial)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lbDepartamento7, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbObligaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 443, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lbSapellido3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbSapellido4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                            .addComponent(lbSapellido2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEmailEmisor, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmailErrores, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmailDefecto, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(lbSapellido8, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtIdentificadorPruebasDian, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addComponent(lbSapellido7, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 443, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbSapellido6, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbDepartamento6, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbDepartamento3, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbSapellido5, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbDepartamento5, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbDepartamento8)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbTributario, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbCodigoPostal, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtSitioWeb, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cmbCiudad, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbDepartamento, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbNaturaleza, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbTipoRegimen, 0, 239, Short.MAX_VALUE)
                            .addComponent(txtCodigoCiiu)
                            .addComponent(dtFechaInicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(chkClienteFacturacionElectronica, javax.swing.GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE)
                    .addComponent(chkGenerarRegistroPruebas, javax.swing.GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnGuardarCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                    .addComponent(btnLimpiarClientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbDepartamento8, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbTributario, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lbNit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtIdentificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lbDepartamento3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbNaturaleza, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbTipoIdentificacion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbRazon, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtRazonSocial, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                                .addComponent(lbDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cmbDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbPNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                            .addComponent(txtPrimerNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                            .addComponent(lbSNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                            .addComponent(txtSegundoNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                            .addComponent(lbCiudad, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                            .addComponent(cmbCiudad))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lbApellido, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                                    .addComponent(txtPrimerApellido, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                                    .addComponent(txtSegundoApellido, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                                    .addComponent(lbSapellido, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
                                .addGap(2, 2, 2)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lbDepartamento5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cmbCodigoPostal, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(lbSNombre1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                                        .addComponent(txtDireccion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(lbSapellido1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lbSapellido5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtSitioWeb, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lbSapellido2, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lbSapellido6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtCodigoCiiu, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtEmailErrores, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lbSapellido3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbDepartamento6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbTipoRegimen, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(txtEmailEmisor, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbSapellido4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmailDefecto, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(btnGuardarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLimpiarClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbDepartamento7, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbObligaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dtFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbSapellido7, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(chkClienteFacturacionElectronica)))
                .addGap(2, 2, 2)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbSapellido8, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtIdentificadorPruebasDian, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(chkGenerarRegistroPruebas)))
                .addGap(9, 9, 9))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                .addGap(5, 5, 5)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        jTabbedPane1.addTab("Clientes", jPanel1);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jLabel16.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel16.setText("Codigo:");

        txtCodigo.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtCodigo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCodigo.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoKeyReleased(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel18.setText("Nombre:");

        txtNombre.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtNombre.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNombre.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtNombre.setEnabled(false);
        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreKeyReleased(evt);
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

        jLabel19.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel19.setText("Nit:");

        txtNit1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtNit1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNit1.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtNit1.setEnabled(false);
        txtNit1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNit1KeyReleased(evt);
            }
        });

        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        btnGuardarPermisos.setBackground(new java.awt.Color(46, 204, 113));
        btnGuardarPermisos.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnGuardarPermisos.setText("GUARDAR");
        btnGuardarPermisos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarPermisosActionPerformed(evt);
            }
        });

        jPanel45.setBackground(new java.awt.Color(255, 255, 255));
        jPanel45.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Adobe Arabic", 1, 12), new java.awt.Color(102, 153, 0))); // NOI18N

        lbNit9.setBackground(new java.awt.Color(204, 204, 204));
        lbNit9.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbNit9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit9.setText("Modulos");
        lbNit9.setOpaque(true);

        tblModulos.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        tblModulos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"ORDENES DE SERVICIO", null},
                {"ORDENES DE SERVICIO AUTOMOTOR", null},
                {"CRÉDITOS", null},
                {"PLAN SEPARE", null},
                {"PEDIDOS", null},
                {"CONGELADAS", null},
                {"MEDICO", null},
                {"VETERINARIO", null},
                {"PARQUEADERO", null},
                {"AGENDA", null},
                {"RESTAURANTE", null},
                {"RECORDATORIOS", null},
                {"LABORATORIO", null},
                {"OFTALMOLOGIA", null},
                {"FACTURACIÓN LOTE", null},
                {"FACTURACIÓN ELECTRÓNICA", null},
                {"INVENTARIO POR BODEGAS", null},
                {"PRODUCTOS DETALLADOS", null},
                {"USB PARA DESBLOQUEAR", null},
                {"PRUEBAS EN FACTURACIÓN ELECTRÓNICA", null}
            },
            new String [] {
                "Descripción", "Opción"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblModulos.setRowHeight(28);
        tblModulos.getTableHeader().setReorderingAllowed(false);
        jScrollPane16.setViewportView(tblModulos);
        if (tblModulos.getColumnModel().getColumnCount() > 0) {
            tblModulos.getColumnModel().getColumn(1).setMinWidth(90);
            tblModulos.getColumnModel().getColumn(1).setPreferredWidth(90);
            tblModulos.getColumnModel().getColumn(1).setMaxWidth(90);
        }

        javax.swing.GroupLayout jPanel45Layout = new javax.swing.GroupLayout(jPanel45);
        jPanel45.setLayout(jPanel45Layout);
        jPanel45Layout.setHorizontalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane16, javax.swing.GroupLayout.DEFAULT_SIZE, 589, Short.MAX_VALUE)
            .addComponent(lbNit9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel45Layout.setVerticalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel45Layout.createSequentialGroup()
                .addComponent(lbNit9, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane16, javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE))
        );

        jPanel46.setBackground(new java.awt.Color(255, 255, 255));
        jPanel46.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Adobe Arabic", 1, 12), new java.awt.Color(102, 153, 0))); // NOI18N

        lbNit10.setBackground(new java.awt.Color(204, 204, 204));
        lbNit10.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbNit10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit10.setText("Regimen");
        lbNit10.setOpaque(true);

        rdComun.setBackground(new java.awt.Color(255, 255, 255));
        rdComun.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rdComun.setText("COMÚN");

        rdSimplificado.setBackground(new java.awt.Color(255, 255, 255));
        rdSimplificado.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rdSimplificado.setText("SIMPLIFICADO");

        javax.swing.GroupLayout jPanel46Layout = new javax.swing.GroupLayout(jPanel46);
        jPanel46.setLayout(jPanel46Layout);
        jPanel46Layout.setHorizontalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbNit10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel46Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rdComun)
                .addGap(3, 3, 3)
                .addComponent(rdSimplificado, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel46Layout.setVerticalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel46Layout.createSequentialGroup()
                .addComponent(lbNit10, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdComun)
                    .addComponent(rdSimplificado)))
        );

        jPanel47.setBackground(new java.awt.Color(255, 255, 255));
        jPanel47.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Adobe Arabic", 1, 12), new java.awt.Color(102, 153, 0))); // NOI18N

        lbNit11.setBackground(new java.awt.Color(204, 204, 204));
        lbNit11.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbNit11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit11.setText("Impresiones");
        lbNit11.setOpaque(true);

        cmbTipoImpresiones.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cmbTipoImpresiones.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Con codigo", "Sin codigo", "Tipo serial" }));

        javax.swing.GroupLayout jPanel47Layout = new javax.swing.GroupLayout(jPanel47);
        jPanel47.setLayout(jPanel47Layout);
        jPanel47Layout.setHorizontalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cmbTipoImpresiones, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbNit11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel47Layout.setVerticalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel47Layout.createSequentialGroup()
                .addComponent(lbNit11, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(cmbTipoImpresiones, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
        );

        lbTelefono31.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTelefono31.setText("Información legal impresión:");

        txtInformacionLegal.setColumns(20);
        txtInformacionLegal.setRows(3);
        txtInformacionLegal.setText("Software elaborado por HasNet , Tel: 319 741 58 31");
        jScrollPane4.setViewportView(txtInformacionLegal);

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        lbNit13.setBackground(new java.awt.Color(204, 204, 204));
        lbNit13.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbNit13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit13.setText("Vencimiento de licencia");
        lbNit13.setOpaque(true);

        jLabel20.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel20.setText("A cuantos días antes mostrar alerta de bloqueo:");

        txtDiasAntesBloqueo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDiasAntesBloqueo.setText("0");
        txtDiasAntesBloqueo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDiasAntesBloqueoKeyTyped(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel21.setText("Días de gabela para omitir el bloqueo:");

        txtDiasGabelaBloqueo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDiasGabelaBloqueo.setText("0");
        txtDiasGabelaBloqueo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDiasGabelaBloqueoKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbNit13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDiasAntesBloqueo, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDiasGabelaBloqueo, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addComponent(lbNit13, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtDiasAntesBloqueo, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtDiasGabelaBloqueo)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );

        pnlOculto.setBackground(new java.awt.Color(255, 255, 255));

        cmbOpcionesSINO.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NO", "SI" }));

        javax.swing.GroupLayout pnlOcultoLayout = new javax.swing.GroupLayout(pnlOculto);
        pnlOculto.setLayout(pnlOcultoLayout);
        pnlOcultoLayout.setHorizontalGroup(
            pnlOcultoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlOcultoLayout.createSequentialGroup()
                .addGap(0, 185, Short.MAX_VALUE)
                .addComponent(cmbOpcionesSINO, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlOcultoLayout.setVerticalGroup(
            pnlOcultoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOcultoLayout.createSequentialGroup()
                .addComponent(cmbOpcionesSINO, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 49, Short.MAX_VALUE))
        );

        btnLimpiar.setBackground(new java.awt.Color(255, 204, 204));
        btnLimpiar.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnLimpiar.setText("LIMPIAR");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jPanel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(lbTelefono31)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jPanel46, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addComponent(pnlOculto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGuardarPermisos, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane4)
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel47, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel46, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbTelefono31)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(pnlOculto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnGuardarPermisos, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jPanel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(46, 46, 46))
        );

        jLabel22.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel22.setText("Telefono:");

        txtTelefono.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtTelefono.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTelefono.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtTelefono.setEnabled(false);
        txtTelefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelefonoActionPerformed(evt);
            }
        });
        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyReleased(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel23.setText("Inicio");

        txtFechaInicio.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtFechaInicio.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFechaInicio.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtFechaInicio.setEnabled(false);
        txtFechaInicio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFechaInicioKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator3)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE)
                            .addComponent(txtNombre))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnBusProd, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNit1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBusProd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(2, 2, 2)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNit1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(5, 5, 5)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Permisos", jPanel7);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        tblPagos.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        tblPagos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nit", "Nombre", "Fecha Limite", "diasAnterioridad", "IDCliente", "Fecha Pago", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPagos.setRowHeight(24);
        tblPagos.getTableHeader().setReorderingAllowed(false);
        tblPagos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPagosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblPagos);
        if (tblPagos.getColumnModel().getColumnCount() > 0) {
            tblPagos.getColumnModel().getColumn(0).setMinWidth(0);
            tblPagos.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblPagos.getColumnModel().getColumn(0).setMaxWidth(0);
            tblPagos.getColumnModel().getColumn(1).setMinWidth(120);
            tblPagos.getColumnModel().getColumn(1).setPreferredWidth(300);
            tblPagos.getColumnModel().getColumn(1).setMaxWidth(400);
            tblPagos.getColumnModel().getColumn(3).setMinWidth(130);
            tblPagos.getColumnModel().getColumn(3).setPreferredWidth(130);
            tblPagos.getColumnModel().getColumn(3).setMaxWidth(130);
            tblPagos.getColumnModel().getColumn(4).setMinWidth(0);
            tblPagos.getColumnModel().getColumn(4).setPreferredWidth(0);
            tblPagos.getColumnModel().getColumn(4).setMaxWidth(0);
            tblPagos.getColumnModel().getColumn(5).setMinWidth(0);
            tblPagos.getColumnModel().getColumn(5).setPreferredWidth(0);
            tblPagos.getColumnModel().getColumn(5).setMaxWidth(0);
            tblPagos.getColumnModel().getColumn(6).setMinWidth(130);
            tblPagos.getColumnModel().getColumn(6).setPreferredWidth(130);
            tblPagos.getColumnModel().getColumn(6).setMaxWidth(130);
            tblPagos.getColumnModel().getColumn(7).setMinWidth(160);
            tblPagos.getColumnModel().getColumn(7).setPreferredWidth(160);
            tblPagos.getColumnModel().getColumn(7).setMaxWidth(160);
        }

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Buscador", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        txtPagosId.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtPagosId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPagosIdKeyReleased(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel4.setText("ID:");

        jLabel5.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel5.setText("NIT:");

        txtPagosNit.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtPagosNit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPagosNitKeyReleased(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel6.setText("NOMBRE:");

        txtPagosNombre.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtPagosNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPagosNombreKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPagosId, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPagosNit, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPagosNombre)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtPagosId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtPagosNit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtPagosNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15))
        );

        jLabel11.setText("ESTADOS DE PAGOS:");

        cmbEstadosPago.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PENDIENTE", "PAGADO" }));
        cmbEstadosPago.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbEstadosPagoItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1162, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbEstadosPago, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbEstadosPago, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 569, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Pagos", jPanel3);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel26.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel26.setText("Número de facturas eletrónicas a cargar:");

        txtNumeroFacturasACargar.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtNumeroFacturasACargar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNumeroFacturasACargar.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtNumeroFacturasACargar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNumeroFacturasACargarKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNumeroFacturasACargarKeyTyped(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel27.setText("Valor Unitario:");

        txtValorUnitario.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtValorUnitario.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtValorUnitario.setText("$ 900");
        txtValorUnitario.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtValorUnitario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtValorUnitarioKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtValorUnitarioKeyTyped(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel28.setText("Valor Total:");

        txtValorTotal.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtValorTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtValorTotal.setText("$ 0");
        txtValorTotal.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtValorTotal.setEnabled(false);
        txtValorTotal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtValorTotalKeyReleased(evt);
            }
        });

        btnLimpiar1.setBackground(new java.awt.Color(255, 204, 204));
        btnLimpiar1.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnLimpiar1.setText("LIMPIAR");
        btnLimpiar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiar1ActionPerformed(evt);
            }
        });

        btnGuardarPaquete.setBackground(new java.awt.Color(46, 204, 113));
        btnGuardarPaquete.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnGuardarPaquete.setText("GUARDAR");
        btnGuardarPaquete.setEnabled(false);
        btnGuardarPaquete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarPaqueteActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel17.setText("Codigo:");

        txtCodigo1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtCodigo1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCodigo1.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtCodigo1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigo1KeyReleased(evt);
            }
        });

        btnBusProd1.setBackground(new java.awt.Color(204, 204, 204));
        btnBusProd1.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnBusProd1.setForeground(new java.awt.Color(255, 255, 255));
        btnBusProd1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar1.png"))); // NOI18N
        btnBusProd1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnBusProd1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBusProd1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnBusProd1.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnBusProd1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBusProd1ActionPerformed(evt);
            }
        });

        jSeparator4.setForeground(new java.awt.Color(0, 0, 0));

        jLabel24.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel24.setText("Nombre:");

        txtNombre1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtNombre1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNombre1.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtNombre1.setEnabled(false);
        txtNombre1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombre1ActionPerformed(evt);
            }
        });
        txtNombre1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombre1KeyReleased(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel25.setText("Identificación:");

        txtIdentificacion1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtIdentificacion1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtIdentificacion1.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtIdentificacion1.setEnabled(false);
        txtIdentificacion1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIdentificacion1KeyReleased(evt);
            }
        });

        cmbTipoPaquete.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CLIENTES CON FACTURAS", "HISTORIAL DE PAQUETES" }));
        cmbTipoPaquete.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbTipoPaqueteItemStateChanged(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel29.setText("Disponibles:");

        txtDisponibles.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtDisponibles.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDisponibles.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtDisponibles.setEnabled(false);
        txtDisponibles.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDisponiblesKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCodigo1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBusProd1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNombre1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtIdentificacion1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDisponibles, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNumeroFacturasACargar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(80, 80, 80)
                                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtValorUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(55, 55, 55)
                                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jSeparator4))
                        .addContainerGap())
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 395, Short.MAX_VALUE)
                        .addComponent(btnLimpiar1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGuardarPaquete, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 213, Short.MAX_VALUE)
                        .addComponent(cmbTipoPaquete, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtCodigo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBusProd1, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtNombre1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(txtIdentificacion1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDisponibles, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtNumeroFacturasACargar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtValorUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(0, 50, Short.MAX_VALUE)
                        .addComponent(cmbTipoPaquete, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnGuardarPaquete, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLimpiar1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
        );

        tblPaquetes.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        tblPaquetes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nit", "Nombre", "Cant Facturas", "Valor", "Valor Total", "Fecha"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPaquetes.setRowHeight(24);
        tblPaquetes.getTableHeader().setReorderingAllowed(false);
        tblPaquetes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPaquetesMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblPaquetes);
        if (tblPaquetes.getColumnModel().getColumnCount() > 0) {
            tblPaquetes.getColumnModel().getColumn(0).setMinWidth(0);
            tblPaquetes.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblPaquetes.getColumnModel().getColumn(0).setMaxWidth(0);
            tblPaquetes.getColumnModel().getColumn(1).setMinWidth(60);
            tblPaquetes.getColumnModel().getColumn(1).setPreferredWidth(120);
            tblPaquetes.getColumnModel().getColumn(3).setMinWidth(100);
            tblPaquetes.getColumnModel().getColumn(3).setPreferredWidth(130);
            tblPaquetes.getColumnModel().getColumn(3).setMaxWidth(220);
            tblPaquetes.getColumnModel().getColumn(4).setMinWidth(100);
            tblPaquetes.getColumnModel().getColumn(4).setPreferredWidth(130);
            tblPaquetes.getColumnModel().getColumn(4).setMaxWidth(220);
            tblPaquetes.getColumnModel().getColumn(5).setMinWidth(100);
            tblPaquetes.getColumnModel().getColumn(5).setPreferredWidth(130);
            tblPaquetes.getColumnModel().getColumn(5).setMaxWidth(220);
            tblPaquetes.getColumnModel().getColumn(6).setMinWidth(120);
            tblPaquetes.getColumnModel().getColumn(6).setPreferredWidth(120);
            tblPaquetes.getColumnModel().getColumn(6).setMaxWidth(120);
        }

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Buscador", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        txtPagosId1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtPagosId1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPagosId1KeyReleased(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel7.setText("ID:");

        jLabel8.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel8.setText("NIT:");

        txtPagosNit1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtPagosNit1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPagosNit1KeyReleased(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel9.setText("NOMBRE:");

        txtPagosNombre1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtPagosNombre1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPagosNombre1KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPagosId1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPagosNit1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPagosNombre1)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtPagosId1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtPagosNit1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtPagosNombre1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3)
                    .addComponent(jPanel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Paquetes facturación", jPanel8);

        cmbOpciones.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SI", "NO" }));
        cmbOpciones.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbOpcionesItemStateChanged(evt);
            }
        });
        cmbOpciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbOpcionesMouseClicked(evt);
            }
        });
        cmbOpciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbOpcionesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1)
                    .addComponent(cmbOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jTabbedPane1)
                .addGap(0, 0, 0)
                .addComponent(cmbOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentHidden

    }//GEN-LAST:event_formComponentHidden

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized

    }//GEN-LAST:event_formComponentResized

    private void cmbOpcionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbOpcionesMouseClicked

    }//GEN-LAST:event_cmbOpcionesMouseClicked

    private void cmbOpcionesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbOpcionesItemStateChanged

    }//GEN-LAST:event_cmbOpcionesItemStateChanged

    private void tblPagosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPagosMouseClicked
        if (evt.getClickCount() > 1 && cmbEstadosPago.getSelectedIndex() == 0) {
            if (alertas.option("¿Esta seguro de realizar pago?")) {
                tblPagos.removeEditor();
                modeloOrdenado1.setRowFilter(RowFilter.regexFilter("", 0));

                if (instancias.getSql().cambiarEstadoCuota(Integer.parseInt(tblPagos.getValueAt(tblPagos.getSelectedRow(), 0).toString()))) {
                    String fechaLimite = tblPagos.getValueAt(tblPagos.getSelectedRow(), 3).toString();
                    String nuevaFechaLimite = metodos.sumarMeses(fechaLimite, 1);
                    if (!instancias.getSql().agregarRegistroPago(tblPagos.getValueAt(tblPagos.getSelectedRow(), 5).toString(), nuevaFechaLimite)) {
                        alertas.alertFail("Error al registrar la cuota");
                        return;
                    } else {
                        alertas.alertSuccess("Pago registrado con exito");
                        actualizarPagos();
                    }
                }
            }
        }
    }//GEN-LAST:event_tblPagosMouseClicked

    private void txtClienteIdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClienteIdKeyReleased
        modeloOrdenado.setRowFilter(RowFilter.regexFilter("(?i)" + txtClienteId.getText(), 0));
        txtClienteNit.setText("");
        txtClienteNombre.setText("");
    }//GEN-LAST:event_txtClienteIdKeyReleased

    private void txtClienteNitKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClienteNitKeyReleased
        modeloOrdenado.setRowFilter(RowFilter.regexFilter("(?i)" + txtClienteNit.getText(), 1));
        txtClienteId.setText("");
        txtClienteNombre.setText("");
    }//GEN-LAST:event_txtClienteNitKeyReleased

    private void txtClienteNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClienteNombreKeyReleased
        modeloOrdenado.setRowFilter(RowFilter.regexFilter("(?i)" + txtClienteNombre.getText(), 2));
        txtClienteId.setText("");
        txtClienteNit.setText("");
    }//GEN-LAST:event_txtClienteNombreKeyReleased

    private void txtPagosIdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPagosIdKeyReleased
        modeloOrdenado1.setRowFilter(RowFilter.regexFilter("(?i)" + txtPagosId.getText(), 1));
        txtPagosNit.setText("");
        txtPagosNombre.setText("");
    }//GEN-LAST:event_txtPagosIdKeyReleased

    private void txtPagosNitKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPagosNitKeyReleased
        modeloOrdenado1.setRowFilter(RowFilter.regexFilter("(?i)" + txtPagosNit.getText(), 2));
        txtPagosNombre.setText("");
        txtPagosId.setText("");
    }//GEN-LAST:event_txtPagosNitKeyReleased

    private void txtPagosNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPagosNombreKeyReleased
        modeloOrdenado1.setRowFilter(RowFilter.regexFilter("(?i)" + txtPagosNombre.getText(), 3));
        txtPagosId.setText("");
        txtPagosNit.setText("");
    }//GEN-LAST:event_txtPagosNombreKeyReleased

    private void txtCelularKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCelularKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCelularKeyReleased

    private void btnGuardarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarClienteActionPerformed
        validacionesMenu validacionesMenu = new validacionesMenu();
        modeloCreacion modelo = llenarModeloCreacion();

        if (!validacionesMenu.validaciones(modelo)) {
            return;
        }

        if (alertas.option("¿Desea continuar?")) {

            if (chkGenerarRegistroPruebas.isSelected()) {
                if (!crearYEnviarJSON(modelo, false)) {
                    alertas.alert("Error al crear emisor de facturación");
                    return;
                } else {
                    alertas.alertSuccess("Empresa registrada con exito en pruebas");
                    chkGenerarRegistroPruebas.setSelected(false);
                    chkClienteFacturacionElectronica.setSelected(true);
                }
            } else {
                if (chkClienteFacturacionElectronica.isSelected()) {
                    if (!crearYEnviarJSON(modelo, true)) {
                        alertas.alert("Error al crear emisor de facturación");
                        return;
                    }
                }

                String fechaInicio = metodos.desdeDate2(dtFechaInicio.getCurrent());
                String fechaLimite = metodos.sumarMeses(fechaInicio, 1);

                if (instancias.getSql().agregarCliente(codigoFinal, txtIdentificacion.getText(), cmbTipoIdentificacion.getSelectedItem().toString(),
                        txtRazonSocial.getText(), txtCelular.getText(), fechaInicio)) {

                    if (instancias.getSql().agregarDatosCliente(codigoFinal, modelo)) {
                        if (!instancias.getSql().agregarRegistroPago(codigoFinal, fechaLimite)) {
                            alertas.alertFail("Error al registrar la cuota");
                            return;
                        }

                        alertas.alertSuccess("Empresa registrada con exito");
                        btnLimpiarClientesActionPerformed(evt);
                        actualizarPagos();
                        actualizarClientes();
                    }
                }
            }
        }
    }//GEN-LAST:event_btnGuardarClienteActionPerformed

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged

    }//GEN-LAST:event_jTabbedPane1StateChanged

    private void txtCodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txtCodigo.getText().equals("")) {
                ventanaTerceros("");
            } else {
                cargarCliente(txtCodigo.getText());
            }
        } else {
            limpiar();
        }
    }//GEN-LAST:event_txtCodigoKeyReleased

    private void txtNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreKeyReleased

    private void btnBusProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBusProdActionPerformed
        ventanaTerceros("");
    }//GEN-LAST:event_btnBusProdActionPerformed

    private void txtNit1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNit1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNit1KeyReleased

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void btnGuardarPermisosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarPermisosActionPerformed
        modeloConfiguracion modelo = llenarModeloSuperMaestra();
        if (!instancias.getSql().actualizarPermisos(modelo, txtCodigo.getText())) {
            alertas.alertFail("Hubo un problema al guardar");
            return;
        }

        alertas.alertSuccess("Cambios guardados con éxito");
        limpiar();
    }//GEN-LAST:event_btnGuardarPermisosActionPerformed

    private void txtDiasAntesBloqueoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiasAntesBloqueoKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtDiasAntesBloqueoKeyTyped

    private void txtDiasGabelaBloqueoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiasGabelaBloqueoKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtDiasGabelaBloqueoKeyTyped

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        switch (jTabbedPane1.getSelectedIndex()) {
            case 0:
                actualizarClientes();
                break;
            case 2:
                actualizarPagos();
                break;
            case 3:
                actualizarPaquetes();
                break;
            default:
                break;
        }
    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void cmbEstadosPagoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbEstadosPagoItemStateChanged
        actualizarPagos();
    }//GEN-LAST:event_cmbEstadosPagoItemStateChanged

    private void cmbOpcionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbOpcionesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbOpcionesActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiar();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void txtTelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoActionPerformed

    private void txtTelefonoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoKeyReleased

    private void txtFechaInicioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFechaInicioKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaInicioKeyReleased

    private void lbNitKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lbNitKeyReleased
        txtIdentificacion.requestFocus();
    }//GEN-LAST:event_lbNitKeyReleased

    private void txtIdentificacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdentificacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdentificacionActionPerformed

    private void txtIdentificacionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdentificacionKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtPrimerNombre.requestFocus();
        } else {
            calcularCodigo();
        }
    }//GEN-LAST:event_txtIdentificacionKeyReleased

    private void txtIdentificacionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdentificacionKeyTyped

    }//GEN-LAST:event_txtIdentificacionKeyTyped

    private void txtRazonSocialKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRazonSocialKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtTelefono.requestFocus();
        }
    }//GEN-LAST:event_txtRazonSocialKeyReleased

    private void txtPrimerNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrimerNombreKeyReleased
        txtRazonSocial.setText(txtPrimerNombre.getText() + " " + txtSegundoNombre.getText() + " " + txtPrimerApellido.getText() + " " + txtSegundoApellido.getText());
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtSegundoNombre.requestFocus();
        } else {
            calcularCodigo();
        }
    }//GEN-LAST:event_txtPrimerNombreKeyReleased

    private void txtPrimerNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrimerNombreKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrimerNombreKeyTyped

    private void txtPrimerApellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrimerApellidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrimerApellidoActionPerformed

    private void txtPrimerApellidoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrimerApellidoKeyReleased
        txtRazonSocial.setText(txtPrimerNombre.getText() + " " + txtSegundoNombre.getText() + " " + txtPrimerApellido.getText() + " " + txtSegundoApellido.getText());
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtSegundoApellido.requestFocus();
        } else {
            calcularCodigo();
        }
    }//GEN-LAST:event_txtPrimerApellidoKeyReleased

    private void txtSegundoNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSegundoNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSegundoNombreActionPerformed

    private void txtSegundoNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSegundoNombreKeyReleased
        txtRazonSocial.setText(txtPrimerNombre.getText() + " " + txtSegundoNombre.getText() + " " + txtPrimerApellido.getText() + " " + txtSegundoApellido.getText());
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtPrimerApellido.requestFocus();
        } else {
            calcularCodigo();
        }
    }//GEN-LAST:event_txtSegundoNombreKeyReleased

    private void txtSegundoNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSegundoNombreKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSegundoNombreKeyTyped

    private void txtSegundoApellidoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSegundoApellidoKeyReleased
        txtRazonSocial.setText(txtPrimerNombre.getText() + " " + txtSegundoNombre.getText() + " " + txtPrimerApellido.getText() + " " + txtSegundoApellido.getText());
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtDireccion.requestFocus();
        } else {
            calcularCodigo();
        }
    }//GEN-LAST:event_txtSegundoApellidoKeyReleased

    private void txtDireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDireccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDireccionActionPerformed

    private void txtDireccionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDireccionKeyReleased

    private void txtDireccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDireccionKeyTyped

    private void txtEmailErroresKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailErroresKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailErroresKeyReleased

    private void txtEmailEmisorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailEmisorKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailEmisorKeyReleased

    private void txtEmailDefectoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailDefectoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailDefectoKeyReleased

    private void cmbDepartamentoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbDepartamentoItemStateChanged
        try {
            consultarMunicipios(cmbDepartamento.getSelectedItem().toString());
        } catch (Exception e) {
        }
    }//GEN-LAST:event_cmbDepartamentoItemStateChanged

    private void cmbCiudadItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbCiudadItemStateChanged
        Object[][] codigosPostales = instancias.getSql().obtenerCodigosPostales(cmbDepartamento.getSelectedItem().toString(), cmbCiudad.getSelectedItem().toString());
        cmbCodigoPostal.removeAllItems();
        cmbCodigoPostal.addItem(" ");

        for (int i = 0; i < codigosPostales.length; i++) {
            if (null != codigosPostales[i][0]) {
                cmbCodigoPostal.addItem(codigosPostales[i][0]);
            }
        }
    }//GEN-LAST:event_cmbCiudadItemStateChanged

    private void txtSitioWebKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSitioWebKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSitioWebKeyReleased

    private void cmbCodigoPostalItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbCodigoPostalItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbCodigoPostalItemStateChanged

    private void cmbTipoRegimenItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbTipoRegimenItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbTipoRegimenItemStateChanged

    private void txtEmailEmisorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailEmisorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailEmisorActionPerformed

    private void txtCodigoCiiuKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoCiiuKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoCiiuKeyReleased

    private void cmbObligacionesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbObligacionesItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbObligacionesItemStateChanged

    private void cmbTributarioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbTributarioItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbTributarioItemStateChanged

    private void txtLogoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLogoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLogoKeyReleased

    private void txtCelularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCelularActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCelularActionPerformed

    private void txtRazonSocialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRazonSocialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRazonSocialActionPerformed

    private void txtCodigo1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigo1KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txtCodigo1.getText().equals("")) {
                buscadorClientePaquetes("");
            } else {
                cargarClientePaquete(txtCodigo1.getText());
            }
        } else {
            limpiar();
        }
    }//GEN-LAST:event_txtCodigo1KeyReleased

    private void btnBusProd1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBusProd1ActionPerformed
        buscadorClientePaquetes("");
    }//GEN-LAST:event_btnBusProd1ActionPerformed

    private void txtNombre1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombre1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombre1ActionPerformed

    private void txtNombre1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombre1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombre1KeyReleased

    private void txtIdentificacion1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdentificacion1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdentificacion1KeyReleased

    private void txtNumeroFacturasACargarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroFacturasACargarKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnGuardarPaquete.requestFocus();
        } else {
            calcularTotalPaquete();
        }
    }//GEN-LAST:event_txtNumeroFacturasACargarKeyReleased

    private void txtValorUnitarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorUnitarioKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnGuardarPaquete.requestFocus();
        } else {
            txtValorUnitario.setText(big.setMoneda(big.getMoneda(txtValorUnitario.getText())));
            calcularTotalPaquete();
        }
    }//GEN-LAST:event_txtValorUnitarioKeyReleased

    private void txtValorTotalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorTotalKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValorTotalKeyReleased

    private void btnLimpiar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiar1ActionPerformed
        txtDisponibles.setText("0");
        txtNumeroFacturasACargar.setText("0");
        txtValorUnitario.setText("$ 900");
        txtValorTotal.setText("$ 0");
        txtCodigo1.setText("");
        txtNombre1.setText("");
        txtIdentificacion1.setText("");
        btnGuardarPaquete.setEnabled(false);
        txtCodigo1.setEnabled(true);
        txtCodigo1.requestFocus();
    }//GEN-LAST:event_btnLimpiar1ActionPerformed

    private void btnGuardarPaqueteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarPaqueteActionPerformed
        if (txtNumeroFacturasACargar.getText().equals("")) {
            alertas.alert("Debe ingresar la cantidad");
            return;
        }

        if (Integer.parseInt(txtNumeroFacturasACargar.getText()) == 0) {
            alertas.alert("Debe ingresar la cantidad");
            return;
        }

        if (alertas.option("¿Desea continuar?")) {
            if (instancias.getSql().agregarRegistroPaquete(txtCodigo1.getText(), Integer.parseInt(txtNumeroFacturasACargar.getText()),
                    Integer.parseInt(big.getMoneda(txtValorUnitario.getText()).toString()), Integer.parseInt(big.getMoneda(txtValorTotal.getText()).toString()),
                    metodosGenerales.fecha())) {

                int totalFacturasDisponibles = Integer.parseInt(txtNumeroFacturasACargar.getText()) + Integer.parseInt(txtDisponibles.getText());
                if (!instancias.getSql().actualizarPaquete(txtCodigo1.getText(), totalFacturasDisponibles)) {
                    alertas.alertFail("Error al registrar la cuota");
                    return;
                }

                alertas.alertSuccess("Paquete registrado con exito");
                btnLimpiar1ActionPerformed(evt);
                actualizarPaquetes();
            }
        }
    }//GEN-LAST:event_btnGuardarPaqueteActionPerformed

    private void tblPaquetesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPaquetesMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblPaquetesMouseClicked

    private void txtPagosId1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPagosId1KeyReleased
        modeloOrdenadoPaquetes.setRowFilter(RowFilter.regexFilter("(?i)" + txtPagosId1.getText(), 0));
        txtPagosNit1.setText("");
        txtPagosNombre1.setText("");
    }//GEN-LAST:event_txtPagosId1KeyReleased

    private void txtPagosNit1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPagosNit1KeyReleased
        modeloOrdenadoPaquetes.setRowFilter(RowFilter.regexFilter("(?i)" + txtPagosNit1.getText(), 1));
        txtPagosNombre1.setText("");
        txtPagosId1.setText("");
    }//GEN-LAST:event_txtPagosNit1KeyReleased

    private void txtPagosNombre1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPagosNombre1KeyReleased
        modeloOrdenadoPaquetes.setRowFilter(RowFilter.regexFilter("(?i)" + txtPagosNombre1.getText(), 2));
        txtPagosId1.setText("");
        txtPagosNit1.setText("");
    }//GEN-LAST:event_txtPagosNombre1KeyReleased

    private void txtNumeroFacturasACargarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroFacturasACargarKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtNumeroFacturasACargarKeyTyped

    private void txtValorUnitarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorUnitarioKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtValorUnitarioKeyTyped

    private void cmbTipoPaqueteItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbTipoPaqueteItemStateChanged
        actualizarPaquetes();
    }//GEN-LAST:event_cmbTipoPaqueteItemStateChanged

    private void txtDisponiblesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDisponiblesKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDisponiblesKeyReleased

    private void btnLimpiarClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarClientesActionPerformed
        txtIdentificacion.setText("");
        txtRazonSocial.setText("");
        txtPrimerNombre.setText("");
        txtSegundoNombre.setText("");
        txtPrimerApellido.setText("");
        txtSegundoApellido.setText("");

        txtDireccion.setText("");
        txtCelular.setText("");
        txtEmailErrores.setText("");
        txtEmailEmisor.setText("");
        txtEmailDefecto.setText("");
        txtLogo.setText("");
        txtCodigoCiiu.setText("");
        txtSitioWeb.setText("");

        cmbObligaciones.setSelectedIndex(0);
        cmbNaturaleza.setSelectedIndex(0);
        cmbDepartamento.setSelectedIndex(0);
        cmbCodigoPostal.setSelectedIndex(0);
        cmbTipoRegimen.setSelectedIndex(0);
        cmbTributario.setSelectedIndex(0);

        chkClienteFacturacionElectronica.setSelected(true);
        chkGenerarRegistroPruebas.setSelected(false);
    }//GEN-LAST:event_btnLimpiarClientesActionPerformed

    private void txtIdentificadorPruebasDianKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdentificadorPruebasDianKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdentificadorPruebasDianKeyReleased

    private void chkGenerarRegistroPruebasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkGenerarRegistroPruebasItemStateChanged
        chkClienteFacturacionElectronica.setSelected(false);
    }//GEN-LAST:event_chkGenerarRegistroPruebasItemStateChanged

    private void calcularTotalPaquete() {
        txtValorTotal.setText(big.setMoneda(big.getMoneda(txtValorUnitario.getText()).multiply(big.getBigDecimal(txtNumeroFacturasACargar.getText()))));
    }

    private boolean crearYEnviarJSON(modeloCreacion modelo, boolean conURLProduccion) {
        String JSONCreacion = "";
        try {
            JSONCreacion = controladorJson.crearJSONCreacion(modelo);
            System.out.println("JSON Emisor: " + JSONCreacion);
        } catch (JSONException ex) {
            System.err.println("Error al crear el JSON: " + ex);
            return false;
        }

        if (!JSONCreacion.equals("")) {
            try {
                if (controladorFacturacion.enviarJSONCreacionEmisor(JSONCreacion, conURLProduccion)) {
                    return true;
                }
            } catch (Exception ex) {
                System.err.println("Hubo un error al enviar el JSON de la creación del emisor: " + ex);
                return false;
            }
        }

        return false;
    }

    private modeloCreacion llenarModeloCreacion() {

        modeloCreacion modelo = new modeloCreacion();
        modelo.setTipoServicio("FACTURACION");
        modelo.setTipoPersona(cmbNaturaleza.getSelectedItem().toString());
        modelo.setTipoIdentificacion(cmbTipoIdentificacion.getSelectedItem().toString());
        modelo.setIdentificacion(txtIdentificacion.getText());
        modelo.setNombres(txtPrimerNombre.getText());
        modelo.setSegundoNombre(txtSegundoNombre.getText());
        modelo.setPrimerApellido(txtPrimerApellido.getText());
        modelo.setSegundoApellido(txtSegundoApellido.getText());
        modelo.setDireccion(txtDireccion.getText());
        modelo.setTelefono(txtCelular.getText());
        modelo.setEmail(txtEmailErrores.getText());
        modelo.setEmailRemitente(txtEmailEmisor.getText());
        modelo.setEmailDefecto(txtEmailDefecto.getText());

        if (cmbCiudad.getSelectedIndex() > 0) {
            Object[][] datosDepartamentoYCiudad = instancias.getSql().obtenerCodigoLugar(cmbDepartamento.getSelectedItem().toString(), cmbCiudad.getSelectedItem().toString());

            String cdDaneCiudad = datosDepartamentoYCiudad[0][1].toString();
            modelo.setCdDaneCiudad(cdDaneCiudad.substring(2, cdDaneCiudad.length()));
            modelo.setCdDaneDepartamento(datosDepartamentoYCiudad[0][0].toString());
        } else {
            modelo.setCdDaneCiudad("");
            modelo.setCdDaneDepartamento("");
        }

        modelo.setTipoRegimen(cmbTipoRegimen.getSelectedItem().toString());
        modelo.setSitioWeb(txtSitioWeb.getText());
        modelo.setCodigoPostal(cmbCodigoPostal.getSelectedItem().toString());
        modelo.setCodigoCIIU(txtCodigoCiiu.getText());
        modelo.setCodigoObligaciones(cmbObligaciones.getSelectedItem().toString().split(" / ")[0]);

        String codigoTributario = cmbTributario.getSelectedItem().toString().split(" / ")[1];
        if (codigoTributario.equals("No aplica")) {
            codigoTributario = "NO_APLICA";
        }
        modelo.setCodigoTributario(codigoTributario);
        modelo.setImagenHeaderIzquierda(txtLogo.getText());
        modelo.setIdentificadorPruebasDIAN(txtIdentificadorPruebasDian.getText());
        return modelo;
    }

    private String cargarTipoSeleccion(boolean tipoSeleccionada) {
        String opcionSeleccionada = "NO";
        if (tipoSeleccionada) {
            opcionSeleccionada = "SI";
        }

        return opcionSeleccionada;
    }

    private boolean tipoSeleccionTabla(String tipoSeleccionada) {
        boolean opcionSeleccionada = false;
        if ("SI".equals(tipoSeleccionada)) {
            opcionSeleccionada = true;
        }

        return opcionSeleccionada;
    }

    private modeloConfiguracion llenarModeloSuperMaestra() {
        modeloConfiguracion modelo = new modeloConfiguracion();
        String regimenSistema = "ConIva", tipoImpresion;
        if (rdSimplificado.isSelected()) {
            regimenSistema = "SinIva";
        }

        if (cmbTipoImpresiones.getSelectedItem().equals("Con codigo")) {
            tipoImpresion = "Con-Codigo";
        } else if (cmbTipoImpresiones.getSelectedItem().equals("Sin codigo")) {
            tipoImpresion = "Sin-Codigo";
        } else {
            tipoImpresion = "Imei";
        }

        modelo.setNit(txtNit1.getText());
        modelo.setNombre(txtNombre.getText());
        modelo.setTelefono(txtTelefono.getText());
        modelo.setFechaInicio(txtFechaInicio.getText());

        modelo.setRegimen(regimenSistema);
        modelo.setOrdenServicio(tipoSeleccionTabla(tblModulos.getValueAt(0, 1).toString()));
        modelo.setServicioAutomotor(tipoSeleccionTabla(tblModulos.getValueAt(1, 1).toString()));
        modelo.setCreditos(tipoSeleccionTabla(tblModulos.getValueAt(2, 1).toString()));
        modelo.setSepare(tipoSeleccionTabla(tblModulos.getValueAt(3, 1).toString()));
        modelo.setPedido(tipoSeleccionTabla(tblModulos.getValueAt(4, 1).toString()));
        modelo.setCongeladas(tipoSeleccionTabla(tblModulos.getValueAt(5, 1).toString()));
        modelo.setMedico(tipoSeleccionTabla(tblModulos.getValueAt(6, 1).toString()));
        modelo.setVeterinaria(tipoSeleccionTabla(tblModulos.getValueAt(7, 1).toString()));
        modelo.setParqueadero(tipoSeleccionTabla(tblModulos.getValueAt(8, 1).toString()));
        modelo.setAgenda(tipoSeleccionTabla(tblModulos.getValueAt(9, 1).toString()));
        modelo.setRestaurante(tipoSeleccionTabla(tblModulos.getValueAt(10, 1).toString()));
        modelo.setRecordatorios(tipoSeleccionTabla(tblModulos.getValueAt(11, 1).toString()));
        modelo.setLaboratorio(tipoSeleccionTabla(tblModulos.getValueAt(12, 1).toString()));
        modelo.setOftalmologia(tipoSeleccionTabla(tblModulos.getValueAt(13, 1).toString()));
        modelo.setFacturacionLote(tipoSeleccionTabla(tblModulos.getValueAt(14, 1).toString()));
        modelo.setFacturaElectronica(tipoSeleccionTabla(tblModulos.getValueAt(15, 1).toString()));
        modelo.setInventarioBodegas(tipoSeleccionTabla(tblModulos.getValueAt(16, 1).toString()));
        modelo.setProductosSerial(tipoSeleccionTabla(tblModulos.getValueAt(17, 1).toString()));
        modelo.setUsb(tipoSeleccionTabla(tblModulos.getValueAt(18, 1).toString()));
        modelo.setPruebasFacturacionElectronica(tipoSeleccionTabla(tblModulos.getValueAt(19, 1).toString()));

        modelo.setInformacionLegal(txtInformacionLegal.getText());
        modelo.setTipoImpresion(tipoImpresion);
        modelo.setDiasAntesAlertaBloqueo(Integer.parseInt(txtDiasAntesBloqueo.getText()));
        modelo.setDiasDespuesGabelaBloqueo(Integer.parseInt(txtDiasAntesBloqueo.getText()));
        modelo.setNumeroFacturasElectronicasDisponibles(NUMERO_FACTURAS_ELECTRONICAS_DISPONIBLES);
        return modelo;
    }

    public void cargarClientePaquete(String idCliente) {
        limpiar();
        modeloConfiguracion modelo = instancias.getSql().obtenerConfiguracionCliente(idCliente);
        txtCodigo1.setText(idCliente);
        txtDisponibles.setText(String.valueOf(modelo.getNumeroFacturasElectronicasDisponibles()));
        txtNombre1.setText(modelo.getNombre());
        txtIdentificacion1.setText(modelo.getNit());
        btnGuardarPaquete.setEnabled(true);
        txtCodigo1.setEnabled(false);
        txtNumeroFacturasACargar.requestFocus();
    }

    public void cargarCliente(String idCliente) {

        modeloConfiguracion modelo = instancias.getSql().obtenerConfiguracionCliente(idCliente);
        tblModulos.setValueAt(cargarTipoSeleccion(modelo.isOrdenServicio()), 0, 1);
        tblModulos.setValueAt(cargarTipoSeleccion(modelo.isServicioAutomotor()), 1, 1);
        tblModulos.setValueAt(cargarTipoSeleccion(modelo.isCreditos()), 2, 1);
        tblModulos.setValueAt(cargarTipoSeleccion(modelo.isSepare()), 3, 1);
        tblModulos.setValueAt(cargarTipoSeleccion(modelo.isPedido()), 4, 1);
        tblModulos.setValueAt(cargarTipoSeleccion(modelo.isCongeladas()), 5, 1);
        tblModulos.setValueAt(cargarTipoSeleccion(modelo.isMedico()), 6, 1);
        tblModulos.setValueAt(cargarTipoSeleccion(modelo.isVeterinaria()), 7, 1);
        tblModulos.setValueAt(cargarTipoSeleccion(modelo.isParqueadero()), 8, 1);
        tblModulos.setValueAt(cargarTipoSeleccion(modelo.isAgenda()), 9, 1);
        tblModulos.setValueAt(cargarTipoSeleccion(modelo.isRestaurante()), 10, 1);
        tblModulos.setValueAt(cargarTipoSeleccion(modelo.isRecordatorios()), 11, 1);
        tblModulos.setValueAt(cargarTipoSeleccion(modelo.isLaboratorio()), 12, 1);
        tblModulos.setValueAt(cargarTipoSeleccion(modelo.isOftalmologia()), 13, 1);
        tblModulos.setValueAt(cargarTipoSeleccion(modelo.isFacturacionLote()), 14, 1);
        tblModulos.setValueAt(cargarTipoSeleccion(modelo.isFacturaElectronica()), 15, 1);
        tblModulos.setValueAt(cargarTipoSeleccion(modelo.isInventarioBodegas()), 16, 1);
        tblModulos.setValueAt(cargarTipoSeleccion(modelo.isProductosSerial()), 17, 1);
        tblModulos.setValueAt(cargarTipoSeleccion(modelo.isUsb()), 18, 1);
        tblModulos.setValueAt(cargarTipoSeleccion(modelo.isPruebasFacturacionElectronica()), 19, 1);

        if (modelo.getRegimen().equals("SinIva")) {
            rdSimplificado.setSelected(true);
        } else {
            rdComun.setSelected(true);
        }

        if (modelo.getTipoImpresion().equals("Con-Codigo")) {
            cmbTipoImpresiones.setSelectedIndex(0);
        } else if (modelo.getTipoImpresion().equals("Sin-Codigo")) {
            cmbTipoImpresiones.setSelectedIndex(1);
        } else {
            cmbTipoImpresiones.setSelectedIndex(2);
        }

        txtCodigo.setText(idCliente);
        txtNombre.setText(modelo.getNombre());
        txtNit1.setText(modelo.getNit());
        txtTelefono.setText(modelo.getTelefono());
        txtFechaInicio.setText(modelo.getFechaInicio());
        txtDiasAntesBloqueo.setText(modelo.getDiasAntesAlertaBloqueo() + "");
        txtDiasGabelaBloqueo.setText(modelo.getDiasDespuesGabelaBloqueo() + "");

        if (null == modelo.getInformacionLegal() || "".equals(modelo.getInformacionLegal())) {
            txtInformacionLegal.setText("Software elaborado por HasNet, Tel: 319 741 58 31");
        } else {
            txtInformacionLegal.setText(modelo.getInformacionLegal() + "");
        }

        NUMERO_FACTURAS_ELECTRONICAS_DISPONIBLES = modelo.getNumeroFacturasElectronicasDisponibles();
        btnGuardarPermisos.setEnabled(true);
        txtCodigo.setEnabled(false);
    }

    public void ventanaTerceros(String nit) {
        buscadorClientes buscar = new buscadorClientes(instancias.getMenu(), true, this, "PERMISOS");
        buscar.setLocationRelativeTo(null);
        instancias.setBusClientes(buscar);
        txtCodigo.requestFocus();
        buscar.noEncontrado(nit);
        buscar.show();
    }

    public void buscadorClientePaquetes(String nit) {
        buscadorClientes buscar = new buscadorClientes(instancias.getMenu(), true, this, "PAQUETE");
        buscar.setLocationRelativeTo(null);
        instancias.setBusClientes(buscar);
        txtCodigo1.requestFocus();
        buscar.noEncontrado(nit);
        buscar.show();
    }

    public void calcularCodigo() {
        String hashEncriptado = txtIdentificacion.getText() + "--" + txtRazonSocial.getText();
        try {
            hashEncriptado = Encriptar(hashEncriptado, "yI4z%jIMndKd3N%bj#%f");
        } catch (Exception ex) {
            Logger.getLogger(vistaMenu.class.getName()).log(Level.SEVERE, null, ex);
        }

        codigoFinal = hashEncriptado;
    }

    public static String Encriptar(String valor, String keyPrivate) throws Exception {
        String resultado = null;
        //Se obtienen los bytes de la clave privada   
        byte[] keyBytes = keyPrivate.getBytes();
        Key key = new SecretKeySpec(keyBytes, 0, keyBytes.length, "HmacMD5");
        Mac mac = null;

        //Se obtiene el algoritmo de encriptacion   
        try {
            mac = Mac.getInstance("HmacMD5");
            mac.init(key);
        } catch (InvalidKeyException e) {
            throw new Exception(e.getMessage(), e);
        } catch (NoSuchAlgorithmException e) {
            throw new Exception(e.getMessage(), e);
        }      //Se encripta el valor enviado   

        byte[] valorEncriptado = mac.doFinal(valor.getBytes());

        //Cadena con los digitos correspondientes en hexadecimal   
        String digitosHexadecimales = "0123456789abcdef";
        StringBuilder stringBuilder = new StringBuilder(valorEncriptado.length * 2);

        for (int cx = 0; cx < valorEncriptado.length; cx++) {
            int hn = ((int) (valorEncriptado[cx]) & 0x00ff) / 16;
            int ln = ((int) (valorEncriptado[cx]) & 0x000f);
            stringBuilder.append(digitosHexadecimales.charAt(hn));
            stringBuilder.append(digitosHexadecimales.charAt(ln));
        }

        resultado = stringBuilder.toString();
        return resultado;
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

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new vistaMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBusProd;
    private javax.swing.JButton btnBusProd1;
    private javax.swing.JButton btnGuardarCliente;
    private javax.swing.JButton btnGuardarPaquete;
    private javax.swing.JButton btnGuardarPermisos;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnLimpiar1;
    private javax.swing.JButton btnLimpiarClientes;
    private javax.swing.JCheckBox chkClienteFacturacionElectronica;
    private javax.swing.JCheckBox chkGenerarRegistroPruebas;
    private javax.swing.JComboBox cmbCiudad;
    private javax.swing.JComboBox cmbCodigoPostal;
    private javax.swing.JComboBox cmbDepartamento;
    private javax.swing.JComboBox<String> cmbEstadosPago;
    private javax.swing.JComboBox cmbNaturaleza;
    private javax.swing.JComboBox cmbObligaciones;
    private javax.swing.JComboBox cmbOpciones;
    private javax.swing.JComboBox<String> cmbOpcionesSINO;
    private javax.swing.JComboBox cmbTipoIdentificacion;
    private javax.swing.JComboBox<String> cmbTipoImpresiones;
    private javax.swing.JComboBox<String> cmbTipoPaquete;
    private javax.swing.JComboBox cmbTipoRegimen;
    private javax.swing.JComboBox cmbTributario;
    private datechooser.beans.DateChooserCombo dtFechaInicio;
    private javax.swing.ButtonGroup grpAgenda;
    private javax.swing.ButtonGroup grpCongeladas;
    private javax.swing.ButtonGroup grpContable;
    private javax.swing.ButtonGroup grpCreditos;
    private javax.swing.ButtonGroup grpDispositivoUSB;
    private javax.swing.ButtonGroup grpEmbarcaciones;
    private javax.swing.ButtonGroup grpFEletronica;
    private javax.swing.ButtonGroup grpFacturaLote;
    private javax.swing.ButtonGroup grpInvBodegas;
    private javax.swing.ButtonGroup grpLaboratorio;
    private javax.swing.ButtonGroup grpMedico;
    private javax.swing.ButtonGroup grpOftalmologia;
    private javax.swing.ButtonGroup grpOrdenesServicio;
    private javax.swing.ButtonGroup grpPTM;
    private javax.swing.ButtonGroup grpParqueadero;
    private javax.swing.ButtonGroup grpPedidos;
    private javax.swing.ButtonGroup grpPlanSepare;
    private javax.swing.ButtonGroup grpProductosSerial;
    private javax.swing.ButtonGroup grpPrueba;
    private javax.swing.ButtonGroup grpRecordatorios;
    private javax.swing.ButtonGroup grpRegimen;
    private javax.swing.ButtonGroup grpRestaurante;
    private javax.swing.ButtonGroup grpTipoImpresion;
    private javax.swing.ButtonGroup grpTipoOrden;
    private javax.swing.ButtonGroup grpVeterinaria;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel45;
    private javax.swing.JPanel jPanel46;
    private javax.swing.JPanel jPanel47;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lbApellido;
    private javax.swing.JLabel lbCiudad;
    private javax.swing.JLabel lbDepartamento;
    private javax.swing.JLabel lbDepartamento3;
    private javax.swing.JLabel lbDepartamento5;
    private javax.swing.JLabel lbDepartamento6;
    private javax.swing.JLabel lbDepartamento7;
    private javax.swing.JLabel lbDepartamento8;
    private javax.swing.JLabel lbNit;
    private javax.swing.JLabel lbNit10;
    private javax.swing.JLabel lbNit11;
    private javax.swing.JLabel lbNit13;
    private javax.swing.JLabel lbNit9;
    private javax.swing.JLabel lbPNombre;
    private javax.swing.JLabel lbRazon;
    private javax.swing.JLabel lbSNombre;
    private javax.swing.JLabel lbSNombre1;
    private javax.swing.JLabel lbSapellido;
    private javax.swing.JLabel lbSapellido1;
    private javax.swing.JLabel lbSapellido2;
    private javax.swing.JLabel lbSapellido3;
    private javax.swing.JLabel lbSapellido4;
    private javax.swing.JLabel lbSapellido5;
    private javax.swing.JLabel lbSapellido6;
    private javax.swing.JLabel lbSapellido7;
    private javax.swing.JLabel lbSapellido8;
    private javax.swing.JLabel lbTelefono31;
    private javax.swing.JLabel lbTipo;
    private javax.swing.JPanel pnlOculto;
    private javax.swing.JRadioButton rdComun;
    private javax.swing.JRadioButton rdSimplificado;
    private javax.swing.JTable tblClientes;
    private javax.swing.JTable tblModulos;
    private javax.swing.JTable tblPagos;
    private javax.swing.JTable tblPaquetes;
    private javax.swing.JTextField txtCelular;
    private javax.swing.JTextField txtClienteId;
    private javax.swing.JTextField txtClienteNit;
    private javax.swing.JTextField txtClienteNombre;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtCodigo1;
    private javax.swing.JTextField txtCodigoCiiu;
    private javax.swing.JTextField txtDiasAntesBloqueo;
    private javax.swing.JTextField txtDiasGabelaBloqueo;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtDisponibles;
    private javax.swing.JTextField txtEmailDefecto;
    private javax.swing.JTextField txtEmailEmisor;
    private javax.swing.JTextField txtEmailErrores;
    private javax.swing.JTextField txtFechaInicio;
    private javax.swing.JTextField txtIdentificacion;
    private javax.swing.JTextField txtIdentificacion1;
    private javax.swing.JTextField txtIdentificadorPruebasDian;
    private javax.swing.JTextArea txtInformacionLegal;
    private javax.swing.JTextField txtLogo;
    private javax.swing.JTextField txtNit1;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNombre1;
    private javax.swing.JTextField txtNumeroFacturasACargar;
    private javax.swing.JTextField txtPagosId;
    private javax.swing.JTextField txtPagosId1;
    private javax.swing.JTextField txtPagosNit;
    private javax.swing.JTextField txtPagosNit1;
    private javax.swing.JTextField txtPagosNombre;
    private javax.swing.JTextField txtPagosNombre1;
    private javax.swing.JTextField txtPrimerApellido;
    private javax.swing.JTextField txtPrimerNombre;
    private javax.swing.JTextField txtRazonSocial;
    private javax.swing.JTextField txtSegundoApellido;
    private javax.swing.JTextField txtSegundoNombre;
    private javax.swing.JTextField txtSitioWeb;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtValorTotal;
    private javax.swing.JTextField txtValorUnitario;
    // End of variables declaration//GEN-END:variables

}
