package Vistas.Menu;

import Controlador.Alertas.controladorAlertas;
import Controlador.FacturacionElectronica.controladorCrearJSONCreacion;
import Controlador.FacturacionElectronica.controladorFacturacionElectronica;
import Modelos.Ingresos.modeloEstadoCliente;
import Modelos.JSONCreacion.modeloCreacion;
import Validaciones.Menu.validacionesMenu;
import Vistas.Buscadores.buscadorClientes;
import Vistas.Pagos.DialogoPagoMensual;
import clases.Instancias;
import clases.cambiarColorTabla;
import clases.metodosGenerales;
import java.awt.event.KeyEvent;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import Modelos.Configuracion.modeloConfiguracion;
import org.json.JSONException;

public class vistaMenu extends javax.swing.JFrame {

    private final controladorAlertas alertas = new controladorAlertas();
    private final controladorCrearJSONCreacion controladorJson = new controladorCrearJSONCreacion();
    private final controladorFacturacionElectronica controladorFacturacion = new controladorFacturacionElectronica();
    private final metodosGenerales metodos;
    private static final int COLUMNA_ESTADO_PAGO = 6;
    private final boolean pestanasListas;
    private static final String PERMISO_REGIMEN = "REGIMEN";
    private static final String PERMISO_DIAS_ALERTA = "DIAS ANTES DE MOSTRAR ALERTA DE BLOQUEO";
    private static final String PERMISO_DIAS_GABELA = "DIAS DE GABELA PARA OMITIR EL BLOQUEO";
    private static final String PERMISO_TERMINALES = "TERMINALES ADICIONALES AL SERVIDOR";
    private static final String PERMISO_ORDENES_SERVICIO = "ORDENES DE SERVICIO";
    private static final String PERMISO_MULTIEMPRESA = "MULTIEMPRESA";
    private static final String PERMISO_EMPRESAS = "EMPRESAS ADICIONALES";
    private static final java.util.List<String> PERMISOS_CONFIGURACION = java.util.Arrays.asList(
            PERMISO_REGIMEN, PERMISO_DIAS_ALERTA, PERMISO_DIAS_GABELA, PERMISO_TERMINALES, PERMISO_ORDENES_SERVICIO, PERMISO_EMPRESAS);
    private static final String PERMISO_FACTURACION_ELECTRONICA = "FACTURACION ELECTRONICA";
    private static final String GRUPO_CONFIGURACION = "CONFIGURACIÓN ESTÁNDAR";
    private static final String GRUPO_MODULOS_ESTANDAR = "MÓDULOS ESTÁNDAR (INCLUIDOS EN LA MENSUALIDAD)";
    private static final String GRUPO_MODULOS_ESPECIALES = "MÓDULOS ESPECIALES (COBRO MENSUAL)";
    private static final String GRUPO_ACCIONES_ESPECIALES = "ACCIONES ESPECIALES";
    private static final String[][] FILAS_PERMISOS = {
        {GRUPO_CONFIGURACION},
        {"RÉGIMEN"},
        {"DÍAS ANTES DE MOSTRAR ALERTA DE BLOQUEO"},
        {"DÍAS DE GABELA PARA OMITIR EL BLOQUEO"},
        {"TERMINALES ADICIONALES AL SERVIDOR"},
        {GRUPO_MODULOS_ESTANDAR},
        {"ÓRDENES DE SERVICIO"},
        {"CRÉDITOS"},
        {"PLAN SEPARE"},
        {"PEDIDOS"},
        {"CONGELADAS"},
        {"AGENDA"},
        {"RECORDATORIOS"},
        {"FACTURACIÓN LOTE"},
        {GRUPO_MODULOS_ESPECIALES},
        {"MÉDICO"},
        {"LABORATORIO"},
        {"OFTALMOLOGÍA"},
        {"VETERINARIO"},
        {"PARQUEADERO"},
        {"RESTAURANTE"},
        {GRUPO_ACCIONES_ESPECIALES},
        {"INVENTARIO POR BODEGAS"},
        {"PRODUCTOS DETALLADOS"},
        {"FACTURACIÓN ELECTRÓNICA"},
        {"PRUEBAS EN FACTURACIÓN ELECTRÓNICA"},
        {"MULTIEMPRESA"},
        {"EMPRESAS ADICIONALES"}
    };
    private static final java.util.Set<String> GRUPOS_PERMISOS = new java.util.HashSet<>(java.util.Arrays.asList(
            GRUPO_CONFIGURACION, GRUPO_MODULOS_ESTANDAR, GRUPO_MODULOS_ESPECIALES, GRUPO_ACCIONES_ESPECIALES));
    private static final java.util.List<String> SERVICIOS_ESPECIALES_MENSUALES = java.util.Arrays.asList(
            "MEDICO", "LABORATORIO", "OFTALMOLOGIA", "VETERINARIO", "PARQUEADERO", "RESTAURANTE",
            "INVENTARIO POR BODEGAS", "PRODUCTOS DETALLADOS");
    private static final String ORDEN_NO = "NO";
    private static final String ORDEN_NORMAL = "ORDEN NORMAL";
    private static final String ORDEN_AUTOMOTOR = "ORDEN AUTOMOTOR";
    private static final String REGIMEN_COMUN = "COMÚN";
    private static final String REGIMEN_SIMPLIFICADO = "SIMPLIFICADO";
    private int terminalesCargadas;
    private boolean facturacionElectronicaCargada;
    private final java.util.Set<String> serviciosEspecialesCargados = new java.util.HashSet<>();
    private int empresasCargadas;
    private Instancias instancias;
    TableRowSorter modeloOrdenado;
    TableRowSorter modeloOrdenado1;
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

        inicializarTablaPermisos();

        java.awt.Font fuenteEditor = new java.awt.Font("Arial", 0, 16);
        EditorCeldaPermisos editorPermisos = new EditorCeldaPermisos(EditorCeldaPermisos.editorLista(fuenteEditor, "SI", "NO"));
        editorPermisos.asignarEditor(filaPermiso(PERMISO_REGIMEN), EditorCeldaPermisos.editorLista(fuenteEditor, REGIMEN_COMUN, REGIMEN_SIMPLIFICADO));
        editorPermisos.asignarEditor(filaPermiso(PERMISO_DIAS_ALERTA), EditorCeldaPermisos.editorNumerico(fuenteEditor));
        editorPermisos.asignarEditor(filaPermiso(PERMISO_DIAS_GABELA), EditorCeldaPermisos.editorNumerico(fuenteEditor));
        editorPermisos.asignarEditor(filaPermiso(PERMISO_TERMINALES), EditorCeldaPermisos.editorNumerico(fuenteEditor));
        editorPermisos.asignarEditor(filaPermiso(PERMISO_EMPRESAS), EditorCeldaPermisos.editorNumerico(fuenteEditor));
        editorPermisos.asignarEditor(filaPermiso(PERMISO_ORDENES_SERVICIO),
                EditorCeldaPermisos.editorLista(fuenteEditor, ORDEN_NO, ORDEN_NORMAL, ORDEN_AUTOMOTOR));
        tblModulos.getColumnModel().getColumn(1).setCellEditor(editorPermisos);

        limpiar();
        cargarDepartamentos();
        actualizarPagos();
        actualizarClientes();
        recargarPestanaActual();
        pestanasListas = true;

        dtFechaInicio.setFormat(2);
        dtFechaInicio.setText(metodosGenerales.fecha());
    }

    public void limpiar() {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtNit1.setText("");
        txtFechaInicio.setText("");
        txtTelefono.setText("");
        terminalesCargadas = 0;
        facturacionElectronicaCargada = false;
        serviciosEspecialesCargados.clear();
        empresasCargadas = 0;

        if (tblModulos.isEditing()) {
            tblModulos.getCellEditor().cancelCellEditing();
        }
        for (int i = 0; i < tblModulos.getRowCount(); i++) {
            Object etiqueta = tblModulos.getValueAt(i, 0);
            if (!esGrupoPermiso(etiqueta) && !PERMISOS_CONFIGURACION.contains(normalizarPermiso(String.valueOf(etiqueta)))) {
                tblModulos.setValueAt("NO", i, 1);
            }
        }
        asignarPermiso(PERMISO_REGIMEN, REGIMEN_COMUN);
        asignarPermiso(PERMISO_DIAS_ALERTA, "0");
        asignarPermiso(PERMISO_DIAS_GABELA, "0");
        asignarPermiso(PERMISO_TERMINALES, "0");
        asignarPermiso(PERMISO_EMPRESAS, "0");
        asignarPermiso(PERMISO_ORDENES_SERVICIO, ORDEN_NO);
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

    public void actualizarPagos() {

        DefaultTableModel modelo1 = (DefaultTableModel) tblPagos.getModel();
        while (tblPagos.getRowCount() > 0) {
            modelo1.removeRow(0);
        }

        Object[][] clientes = instancias.getSql().obtenerPagosPendientes();
        int contador = 0;
        for (Object[] cliente : clientes) {
            modelo1.addRow(cliente);
            String fechaActual = metodosGenerales.fecha();
            String fechaLimite = clientes[contador][3].toString();
            int anterioridad = Integer.parseInt(clientes[contador][4].toString());
            long diferenciaDias = metodos.restarFecha(fechaLimite, fechaActual) * -1;

            if (diferenciaDias < 0) {
                modelo1.setValueAt("Vencido", contador, COLUMNA_ESTADO_PAGO);
            } else if (diferenciaDias <= anterioridad) {
                modelo1.setValueAt("Pronto a vencerse", contador, COLUMNA_ESTADO_PAGO);
            } else {
                modelo1.setValueAt("OK", contador, COLUMNA_ESTADO_PAGO);
            }

            contador++;
        }

        modeloOrdenado1 = new TableRowSorter<>(modelo1);
        tblPagos.setRowSorter(modeloOrdenado1);
        filtrarPagos();
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
        jTabbedPane1 = new javax.swing.JTabbedPane();
        panelTarifas = new Vistas.Tarifas.PanelTarifasPlanes();
        pnlInformacionClientes = new javax.swing.JPanel();
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
        pnlDatos = new javax.swing.JPanel();
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
        pnlPermisos = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        btnBusProd = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        txtNit1 = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txtFechaInicio = new javax.swing.JTextField();
        lbNit9 = new javax.swing.JLabel();
        jScrollPane16 = new javax.swing.JScrollPane();
        tblModulos = new javax.swing.JTable();
        btnLimpiar = new javax.swing.JButton();
        btnGuardarPermisos = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblPagos = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtPagosNit = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtPagosNombre = new javax.swing.JTextField();
        panelVentaPaquetes = new Vistas.Paquetes.PanelVentaPaquetes();
        panelIngresos = new Vistas.Ingresos.PanelIngresos();
        panelReportes = new Vistas.Reportes.PanelReportes();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("HasNet Pagos");
        setBackground(new java.awt.Color(255, 255, 255));
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/icono.png")).getImage());
        setMinimumSize(new java.awt.Dimension(1177, 317));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                formComponentHidden(evt);
            }
        });

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
        jTabbedPane1.addTab("Configuraciones, tarifas y paquetes", panelTarifas);

        pnlInformacionClientes.setBackground(new java.awt.Color(255, 255, 255));

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
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Buscador", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 14))); // NOI18N

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
                .addContainerGap())
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

        pnlDatos.setBackground(new java.awt.Color(255, 255, 255));

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
        btnGuardarCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
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

        javax.swing.GroupLayout pnlDatosLayout = new javax.swing.GroupLayout(pnlDatos);
        pnlDatos.setLayout(pnlDatosLayout);
        pnlDatosLayout.setHorizontalGroup(
            pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatosLayout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDatosLayout.createSequentialGroup()
                        .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lbPNombre, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbRazon, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                            .addComponent(lbTipo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbApellido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbSNombre1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnlDatosLayout.createSequentialGroup()
                                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtPrimerNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                                    .addComponent(txtPrimerApellido)
                                    .addComponent(txtDireccion))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(lbSNombre, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbSapellido, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                                    .addComponent(lbSapellido1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtSegundoApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtSegundoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(pnlDatosLayout.createSequentialGroup()
                                .addComponent(cmbTipoIdentificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lbNit)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtIdentificacion))
                            .addComponent(txtRazonSocial)))
                    .addGroup(pnlDatosLayout.createSequentialGroup()
                        .addComponent(lbDepartamento7, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbObligaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 443, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlDatosLayout.createSequentialGroup()
                        .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lbSapellido3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbSapellido4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                            .addComponent(lbSapellido2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEmailEmisor, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmailErrores, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmailDefecto, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(pnlDatosLayout.createSequentialGroup()
                            .addComponent(lbSapellido8, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtIdentificadorPruebasDian, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlDatosLayout.createSequentialGroup()
                            .addComponent(lbSapellido7, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 443, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlDatosLayout.createSequentialGroup()
                        .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                        .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbTributario, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbCodigoPostal, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtSitioWeb, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cmbCiudad, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbDepartamento, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbNaturaleza, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbTipoRegimen, 0, 239, Short.MAX_VALUE)
                            .addComponent(txtCodigoCiiu)
                            .addComponent(dtFechaInicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(chkClienteFacturacionElectronica, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(chkGenerarRegistroPruebas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnGuardarCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLimpiarClientes, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        pnlDatosLayout.setVerticalGroup(
            pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatosLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlDatosLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbDepartamento8, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbTributario, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlDatosLayout.createSequentialGroup()
                        .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lbNit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtIdentificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lbDepartamento3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbNaturaleza, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbTipoIdentificacion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
                        .addGap(3, 3, 3)
                        .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbRazon, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                            .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtRazonSocial, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                                .addComponent(lbDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cmbDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(3, 3, 3)
                        .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbPNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                            .addComponent(txtPrimerNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                            .addComponent(lbSNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                            .addComponent(txtSegundoNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                            .addComponent(lbCiudad, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                            .addComponent(cmbCiudad))
                        .addGap(3, 3, 3)
                        .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlDatosLayout.createSequentialGroup()
                                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lbApellido, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                                    .addComponent(txtPrimerApellido, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                                    .addComponent(txtSegundoApellido, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                                    .addComponent(lbSapellido, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
                                .addGap(2, 2, 2)
                                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lbDepartamento5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cmbCodigoPostal, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(lbSNombre1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                                        .addComponent(txtDireccion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(lbSapellido1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lbSapellido5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtSitioWeb, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(3, 3, 3)
                        .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lbSapellido2, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                            .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lbSapellido6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtCodigoCiiu, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtEmailErrores, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                        .addGap(3, 3, 3)
                        .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lbSapellido3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbDepartamento6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbTipoRegimen, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(txtEmailEmisor, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)
                        .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbSapellido4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmailDefecto, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlDatosLayout.createSequentialGroup()
                        .addComponent(btnGuardarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLimpiarClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(3, 3, 3)
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbDepartamento7, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbObligaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dtFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbSapellido7, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(chkClienteFacturacionElectronica)))
                .addGap(2, 2, 2)
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbSapellido8, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtIdentificadorPruebasDian, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(chkGenerarRegistroPruebas)))
                .addGap(9, 9, 9))
        );

        javax.swing.GroupLayout pnlInformacionClientesLayout = new javax.swing.GroupLayout(pnlInformacionClientes);
        pnlInformacionClientes.setLayout(pnlInformacionClientesLayout);
        pnlInformacionClientesLayout.setHorizontalGroup(
            pnlInformacionClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlInformacionClientesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlInformacionClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlDatos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap())
        );
        pnlInformacionClientesLayout.setVerticalGroup(
            pnlInformacionClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInformacionClientesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlDatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
                .addGap(5, 5, 5)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        jTabbedPane1.addTab("Creación de clientes", pnlInformacionClientes);

        pnlPermisos.setBackground(new java.awt.Color(255, 255, 255));

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
        btnBusProd.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
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

        lbNit9.setBackground(new java.awt.Color(204, 204, 204));
        lbNit9.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbNit9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit9.setText("Permisos");
        lbNit9.setOpaque(true);

        tblModulos.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        tblModulos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"RÉGIMEN", null},
                {"DÍAS ANTES DE MOSTRAR ALERTA DE BLOQUEO", null},
                {"DÍAS DE GABELA PARA OMITIR EL BLOQUEO", null},
                {"TERMINALES ADICIONALES AL SERVIDOR", null},
                {"ORDENES DE SERVICIO", null},
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
                {"PRUEBAS EN FACTURACIÓN ELECTRÓNICA", null},
                {"MULTIEMPRESA", null},
                {"EMPRESAS ADICIONALES", null}
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
            tblModulos.getColumnModel().getColumn(1).setMinWidth(180);
            tblModulos.getColumnModel().getColumn(1).setPreferredWidth(180);
            tblModulos.getColumnModel().getColumn(1).setMaxWidth(180);
        }

        btnLimpiar.setBackground(new java.awt.Color(255, 204, 204));
        btnLimpiar.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnLimpiar.setText("LIMPIAR");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        btnGuardarPermisos.setBackground(new java.awt.Color(46, 204, 113));
        btnGuardarPermisos.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnGuardarPermisos.setText("GUARDAR");
        btnGuardarPermisos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarPermisosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlPermisosLayout = new javax.swing.GroupLayout(pnlPermisos);
        pnlPermisos.setLayout(pnlPermisosLayout);
        pnlPermisosLayout.setHorizontalGroup(
            pnlPermisosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPermisosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPermisosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPermisosLayout.createSequentialGroup()
                        .addGroup(pnlPermisosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlPermisosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE)
                            .addComponent(txtNombre))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlPermisosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
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
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane16, javax.swing.GroupLayout.DEFAULT_SIZE, 1166, Short.MAX_VALUE)
                    .addComponent(lbNit9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPermisosLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGuardarPermisos, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlPermisosLayout.setVerticalGroup(
            pnlPermisosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPermisosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPermisosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBusProd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(2, 2, 2)
                .addGroup(pnlPermisosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNit1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlPermisosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlPermisosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnlPermisosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(lbNit9, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane16, javax.swing.GroupLayout.DEFAULT_SIZE, 599, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlPermisosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardarPermisos, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Permisos de los clientes", pnlPermisos);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        tblPagos.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        tblPagos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nit", "Nombre", "Fecha Limite", "diasAnterioridad", "IDCliente", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
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
            tblPagos.getColumnModel().getColumn(6).setMinWidth(160);
            tblPagos.getColumnModel().getColumn(6).setPreferredWidth(160);
            tblPagos.getColumnModel().getColumn(6).setMaxWidth(160);
        }

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Buscador", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

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
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtPagosNit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtPagosNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1166, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Registros de pagos", jPanel3);
        jTabbedPane1.addTab("Venta de paquetes de facturación", panelVentaPaquetes);
        jTabbedPane1.addTab("Ingresos", panelIngresos);
        jTabbedPane1.addTab("Reportes", panelReportes);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 461, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentHidden

    }//GEN-LAST:event_formComponentHidden

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized

    }//GEN-LAST:event_formComponentResized

    private void tblPagosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPagosMouseClicked
        int fila = tblPagos.getSelectedRow();
        if (evt.getClickCount() > 1 && fila >= 0) {
            tblPagos.removeEditor();
            int idPago = Integer.parseInt(tblPagos.getValueAt(fila, 0).toString());
            String fechaLimite = tblPagos.getValueAt(fila, 3).toString();
            String idCliente = tblPagos.getValueAt(fila, 5).toString();

            DialogoPagoMensual dialogo = new DialogoPagoMensual(this, idCliente, idPago, fechaLimite);
            if (!dialogo.isListo()) {
                return;
            }
            dialogo.setVisible(true);
            if (dialogo.isPagoRegistrado()) {
                actualizarPagos();
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

    private void filtrarPagos() {
        if (modeloOrdenado1 == null) {
            return;
        }
        java.util.List<RowFilter<Object, Object>> filtros = new java.util.ArrayList<>();
        String nit = txtPagosNit.getText().trim();
        String nombre = txtPagosNombre.getText().trim();
        if (!nit.isEmpty()) {
            filtros.add(RowFilter.regexFilter("(?i)" + java.util.regex.Pattern.quote(nit), 1));
        }
        if (!nombre.isEmpty()) {
            filtros.add(RowFilter.regexFilter("(?i)" + java.util.regex.Pattern.quote(nombre), 2));
        }
        modeloOrdenado1.setRowFilter(filtros.isEmpty() ? null : RowFilter.andFilter(filtros));
    }

    private void txtPagosNitKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPagosNitKeyReleased
        filtrarPagos();
    }//GEN-LAST:event_txtPagosNitKeyReleased

    private void txtPagosNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPagosNombreKeyReleased
        filtrarPagos();
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

            if (!chkGenerarRegistroPruebas.isSelected() && instancias.getSql().existeCliente(txtIdentificacion.getText(), txtRazonSocial.getText())) {
                alertas.alertFail("Ya existe un cliente registrado con la identificación " + txtIdentificacion.getText() + " y esa razón social");
                return;
            }

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
                codigoFinal = generarIdentificadorCliente();

                if (instancias.getSql().agregarCliente(codigoFinal, txtIdentificacion.getText(), cmbTipoIdentificacion.getSelectedItem().toString(),
                        txtRazonSocial.getText(), txtCelular.getText(), fechaInicio)) {

                    if (instancias.getSql().agregarDatosCliente(codigoFinal, modelo)) {
                        if (!instancias.getSql().agregarRegistroPago(codigoFinal, fechaLimite)) {
                            alertas.alertFail("Error al registrar la cuota");
                            return;
                        }

                        try {
                            instancias.getSqlIngresos().registrarVentaLicencia(codigoFinal);
                        } catch (java.sql.SQLException | IllegalStateException ex) {
                            Logger.getLogger(vistaMenu.class.getName()).log(Level.SEVERE, "No se pudo registrar el ingreso de la licencia", ex);
                            alertas.alertFail("La empresa se registró, pero no se pudo registrar el ingreso de la licencia: " + ex.getMessage());
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
        if (pestanasListas) {
            recargarPestanaActual();
        }
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

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked

    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void recargarPestanaActual() {
        java.awt.Component pestana = jTabbedPane1.getSelectedComponent();
        if (pestana == pnlInformacionClientes) {
            actualizarClientes();
        } else if (pestana == jPanel3) {
            actualizarPagos();
        } else if (pestana == panelVentaPaquetes) {
            panelVentaPaquetes.recargar();
        } else if (pestana == panelTarifas) {
            panelTarifas.recargar();
        } else if (pestana == panelIngresos) {
            panelIngresos.recargar();
        } else if (pestana == panelReportes) {
            panelReportes.recargar();
        }
    }

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
        }
    }//GEN-LAST:event_txtPrimerApellidoKeyReleased

    private void txtSegundoNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSegundoNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSegundoNombreActionPerformed

    private void txtSegundoNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSegundoNombreKeyReleased
        txtRazonSocial.setText(txtPrimerNombre.getText() + " " + txtSegundoNombre.getText() + " " + txtPrimerApellido.getText() + " " + txtSegundoApellido.getText());
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtPrimerApellido.requestFocus();
        }
    }//GEN-LAST:event_txtSegundoNombreKeyReleased

    private void txtSegundoNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSegundoNombreKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSegundoNombreKeyTyped

    private void txtSegundoApellidoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSegundoApellidoKeyReleased
        txtRazonSocial.setText(txtPrimerNombre.getText() + " " + txtSegundoNombre.getText() + " " + txtPrimerApellido.getText() + " " + txtSegundoApellido.getText());
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtDireccion.requestFocus();
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

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiar();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnGuardarPermisosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarPermisosActionPerformed
        if (tblModulos.isEditing()) {
            tblModulos.getCellEditor().stopCellEditing();
        }
        int terminales;
        try {
            terminales = enteroPermiso(PERMISO_TERMINALES);
            enteroPermiso(PERMISO_DIAS_ALERTA);
            enteroPermiso(PERMISO_DIAS_GABELA);
            if (enteroPermiso(PERMISO_EMPRESAS) < 0) {
                throw new NumberFormatException("Empresas negativas");
            }
        } catch (NumberFormatException ex) {
            alertas.alert("Los días, el número de terminales y el número de empresas deben ser números enteros positivos");
            return;
        }
        if (moduloActivo(PERMISO_MULTIEMPRESA) && enteroPermiso(PERMISO_EMPRESAS) == 0) {
            alertas.alert("Con MULTIEMPRESA activo indique cuántas EMPRESAS ADICIONALES puede crear el cliente");
            return;
        }

        boolean cobrarInstalacion = false;
        int nuevas = terminales - terminalesCargadas;
        if (nuevas > 0) {
            cobrarInstalacion = alertas.option("Se agregan " + nuevas + " terminal(es). ¿Registrar el cobro de instalación?"
                + " (Responda NO si son terminales que el cliente ya tenía instaladas)");
        }

        modeloConfiguracion modelo = llenarModeloSuperMaestra();
        if (!instancias.getSql().actualizarPermisos(modelo, txtCodigo.getText())) {
            alertas.alertFail("Hubo un problema al guardar");
            return;
        }

        try {
            instancias.getSqlIngresos().actualizarTerminalesContratadas(txtCodigo.getText(), terminales, cobrarInstalacion);
        } catch (java.sql.SQLException | IllegalStateException ex) {
            Logger.getLogger(vistaMenu.class.getName()).log(Level.SEVERE, "Error al actualizar terminales", ex);
            alertas.alertFail("Los permisos se guardaron, pero no se pudo actualizar el número de terminales: " + ex.getMessage());
            return;
        }

        if (!ofrecerCobroServiciosActivados(modelo)) {
            return;
        }

        if (modelo.isFacturaElectronica() && !facturacionElectronicaCargada && !registrarActivacionFacturacionElectronica()) {
            return;
        }

        int especiales = serviciosEspecialesActivos().size();
        int empresas = modelo.getNumeroEmpresas();
        alertas.alertSuccess("Cambios guardados con éxito. Cobro mensual adicional: " + especiales + " módulo(s)/acción(es) especial(es)"
                + (empresas > 0 ? " y " + empresas + " empresa(s) adicional(es)" : ""));
        limpiar();
    }//GEN-LAST:event_btnGuardarPermisosActionPerformed

    private boolean registrarActivacionFacturacionElectronica() {
        String idCliente = txtCodigo.getText();
        try {
            if (instancias.getSqlIngresos().tieneActivacionFacturacionElectronica(idCliente)) {
                return true;
            }
            if (!alertas.option("Se activa la facturación electrónica. ¿Registrar el cobro único de activación?"
                    + " (Responda NO si el cliente ya la había pagado)")) {
                return true;
            }
            instancias.getSqlIngresos().registrarActivacionFacturacionElectronica(idCliente);
            return true;
        } catch (java.sql.SQLException | IllegalStateException ex) {
            Logger.getLogger(vistaMenu.class.getName()).log(Level.SEVERE, "Error al registrar la activación de facturación electrónica", ex);
            alertas.alertFail("Los permisos se guardaron, pero no se pudo registrar el cobro de facturación electrónica: " + ex.getMessage());
            return false;
        }
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
        modelo.setNit(txtNit1.getText());
        modelo.setNombre(txtNombre.getText());
        modelo.setTelefono(txtTelefono.getText());
        modelo.setFechaInicio(txtFechaInicio.getText());

        modelo.setRegimen(REGIMEN_SIMPLIFICADO.equals(valorPermiso(PERMISO_REGIMEN)) ? "SinIva" : "ConIva");
        modelo.setDiasAntesAlertaBloqueo(enteroPermiso(PERMISO_DIAS_ALERTA));
        modelo.setDiasDespuesGabelaBloqueo(enteroPermiso(PERMISO_DIAS_GABELA));

        String ordenes = valorPermiso(PERMISO_ORDENES_SERVICIO);
        modelo.setOrdenServicio(ORDEN_NORMAL.equals(ordenes) || ORDEN_AUTOMOTOR.equals(ordenes));
        modelo.setServicioAutomotor(ORDEN_AUTOMOTOR.equals(ordenes));
        modelo.setCreditos(moduloActivo("CREDITOS"));
        modelo.setSepare(moduloActivo("PLAN SEPARE"));
        modelo.setPedido(moduloActivo("PEDIDOS"));
        modelo.setCongeladas(moduloActivo("CONGELADAS"));
        modelo.setMedico(moduloActivo("MEDICO"));
        modelo.setVeterinaria(moduloActivo("VETERINARIO"));
        modelo.setParqueadero(moduloActivo("PARQUEADERO"));
        modelo.setAgenda(moduloActivo("AGENDA"));
        modelo.setRestaurante(moduloActivo("RESTAURANTE"));
        modelo.setRecordatorios(moduloActivo("RECORDATORIOS"));
        modelo.setLaboratorio(moduloActivo("LABORATORIO"));
        modelo.setOftalmologia(moduloActivo("OFTALMOLOGIA"));
        modelo.setFacturacionLote(moduloActivo("FACTURACION LOTE"));
        modelo.setFacturaElectronica(moduloActivo(PERMISO_FACTURACION_ELECTRONICA));
        modelo.setInventarioBodegas(moduloActivo("INVENTARIO POR BODEGAS"));
        modelo.setProductosSerial(moduloActivo("PRODUCTOS DETALLADOS"));
        modelo.setPruebasFacturacionElectronica(moduloActivo("PRUEBAS EN FACTURACION ELECTRONICA"));
        modelo.setMultiempresa(moduloActivo(PERMISO_MULTIEMPRESA));
        modelo.setNumeroEmpresas(modelo.isMultiempresa() ? enteroPermiso(PERMISO_EMPRESAS) : 0);
        return modelo;
    }

    private static boolean esGrupoPermiso(Object etiqueta) {
        return etiqueta != null && GRUPOS_PERMISOS.contains(etiqueta.toString());
    }

    private void inicializarTablaPermisos() {
        DefaultTableModel modelo = new DefaultTableModel(new String[]{"Descripción", "Opción"}, 0) {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return columna == 1 && !esGrupoPermiso(getValueAt(fila, 0));
            }
        };
        for (String[] fila : FILAS_PERMISOS) {
            modelo.addRow(new Object[]{fila[0], null});
        }
        modelo.addTableModelListener(e -> tblModulos.repaint());
        tblModulos.setModel(modelo);

        final javax.swing.table.DefaultTableCellRenderer rendererNormal = new javax.swing.table.DefaultTableCellRenderer();
        final javax.swing.table.DefaultTableCellRenderer rendererGrupo = new javax.swing.table.DefaultTableCellRenderer();
        javax.swing.table.TableCellRenderer renderer = (tabla, valor, seleccionada, foco, fila, columna) -> {
            Object etiqueta = tabla.getValueAt(fila, 0);
            if (!esGrupoPermiso(etiqueta)) {
                return rendererNormal.getTableCellRendererComponent(tabla, valor, seleccionada, foco, fila, columna);
            }
            rendererGrupo.getTableCellRendererComponent(tabla, columna == 0 ? valor : resumenGrupo(etiqueta.toString()), false, false, fila, columna);
            rendererGrupo.setFont(tabla.getFont().deriveFont(java.awt.Font.BOLD, 14f));
            rendererGrupo.setBackground(new java.awt.Color(222, 230, 239));
            rendererGrupo.setForeground(new java.awt.Color(44, 62, 80));
            rendererGrupo.setHorizontalAlignment(columna == 0 ? javax.swing.SwingConstants.LEFT : javax.swing.SwingConstants.CENTER);
            return rendererGrupo;
        };
        tblModulos.getColumnModel().getColumn(0).setCellRenderer(renderer);
        tblModulos.getColumnModel().getColumn(1).setCellRenderer(renderer);
        tblModulos.getColumnModel().getColumn(1).setMinWidth(180);
        tblModulos.getColumnModel().getColumn(1).setPreferredWidth(180);
        tblModulos.getColumnModel().getColumn(1).setMaxWidth(180);
    }

    private String resumenGrupo(String grupo) {
        if (GRUPO_MODULOS_ESPECIALES.equals(grupo) || GRUPO_ACCIONES_ESPECIALES.equals(grupo)) {
            int activos = 0;
            for (String servicio : SERVICIOS_ESPECIALES_MENSUALES) {
                if (perteneceAGrupo(servicio, grupo) && moduloActivo(servicio)) {
                    activos++;
                }
            }
            return activos + " con cobro";
        }
        return "";
    }

    private boolean perteneceAGrupo(String descripcion, String grupo) {
        String grupoActual = null;
        String buscada = normalizarPermiso(descripcion);
        for (String[] fila : FILAS_PERMISOS) {
            if (GRUPOS_PERMISOS.contains(fila[0])) {
                grupoActual = fila[0];
            } else if (normalizarPermiso(fila[0]).equals(buscada)) {
                return grupo.equals(grupoActual);
            }
        }
        return false;
    }

    private java.util.List<String> serviciosEspecialesActivos() {
        java.util.List<String> activos = new java.util.ArrayList<>();
        for (String servicio : SERVICIOS_ESPECIALES_MENSUALES) {
            if (moduloActivo(servicio)) {
                activos.add(servicio);
            }
        }
        return activos;
    }

    private boolean ofrecerCobroServiciosActivados(modeloConfiguracion modelo) {
        java.util.List<String> nuevos = serviciosEspecialesActivos();
        nuevos.removeAll(serviciosEspecialesCargados);
        int empresasNuevas = Math.max(0, modelo.getNumeroEmpresas() - empresasCargadas);
        if (nuevos.isEmpty() && empresasNuevas == 0) {
            return true;
        }
        String idCliente = txtCodigo.getText();
        try {
            int meses = instancias.getSqlIngresos().mesesPendientesCobertura(idCliente);
            java.math.BigDecimal valor = java.math.BigDecimal.ZERO;
            java.util.List<String> detalle = new java.util.ArrayList<>();
            if (!nuevos.isEmpty()) {
                java.math.BigDecimal tarifa = instancias.getSqlIngresos().obtenerTarifa(clases.SQLIngresos.MENSUALIDAD_SERVICIO_ESPECIAL);
                valor = valor.add(tarifa.multiply(java.math.BigDecimal.valueOf(nuevos.size())));
                detalle.add(nuevos.size() + " servicio(s) especial(es)");
            }
            if (empresasNuevas > 0) {
                java.math.BigDecimal tarifa = instancias.getSqlIngresos().obtenerTarifa(clases.SQLIngresos.MENSUALIDAD_EMPRESA_ADICIONAL);
                valor = valor.add(tarifa.multiply(java.math.BigDecimal.valueOf(empresasNuevas)));
                detalle.add(empresasNuevas + " empresa(s) adicional(es)");
            }
            valor = valor.multiply(java.math.BigDecimal.valueOf(meses));
            if (!alertas.option("Se activaron " + String.join(" y ", detalle) + ". ¿Generar el cobro ahora por " + clases.big.setMoneda(valor)
                    + (meses == 1 ? "" : " (" + meses + " meses)") + "? Si responde NO se cobran desde el siguiente pago")) {
                return true;
            }
            instancias.getSqlIngresos().registrarCobroServiciosActivados(idCliente, nuevos, empresasNuevas);
            return true;
        } catch (java.sql.SQLException | IllegalStateException ex) {
            Logger.getLogger(vistaMenu.class.getName()).log(Level.SEVERE, "Error al generar el cobro de servicios activados", ex);
            alertas.alertFail("Los permisos se guardaron, pero no se pudo generar el cobro de los servicios activados: " + ex.getMessage());
            return false;
        }
    }

    private static String normalizarPermiso(String texto) {
        return java.text.Normalizer.normalize(texto == null ? "" : texto, java.text.Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "").trim().toUpperCase();
    }

    private int filaPermiso(String descripcion) {
        String buscada = normalizarPermiso(descripcion);
        for (int i = 0; i < tblModulos.getRowCount(); i++) {
            if (normalizarPermiso(String.valueOf(tblModulos.getValueAt(i, 0))).equals(buscada)) {
                return i;
            }
        }
        throw new IllegalStateException("La tabla de permisos no tiene la fila \"" + descripcion + "\"");
    }

    private String valorPermiso(String descripcion) {
        Object valor = tblModulos.getValueAt(filaPermiso(descripcion), 1);
        return valor == null ? "" : valor.toString().trim();
    }

    private int enteroPermiso(String descripcion) {
        String valor = valorPermiso(descripcion);
        return valor.isEmpty() ? 0 : Integer.parseInt(valor);
    }

    private void asignarPermiso(String descripcion, Object valor) {
        tblModulos.setValueAt(valor, filaPermiso(descripcion), 1);
    }

    private boolean moduloActivo(String descripcion) {
        return tipoSeleccionTabla(valorPermiso(descripcion));
    }

    private void cargarModulo(String descripcion, boolean activo) {
        asignarPermiso(descripcion, cargarTipoSeleccion(activo));
    }

    public void cargarCliente(String idCliente) {

        modeloConfiguracion modelo = instancias.getSql().obtenerConfiguracionCliente(idCliente);
        String ordenes = ORDEN_NO;
        if (modelo.isOrdenServicio()) {
            ordenes = modelo.isServicioAutomotor() ? ORDEN_AUTOMOTOR : ORDEN_NORMAL;
        }
        asignarPermiso(PERMISO_ORDENES_SERVICIO, ordenes);
        cargarModulo("CREDITOS", modelo.isCreditos());
        cargarModulo("PLAN SEPARE", modelo.isSepare());
        cargarModulo("PEDIDOS", modelo.isPedido());
        cargarModulo("CONGELADAS", modelo.isCongeladas());
        cargarModulo("MEDICO", modelo.isMedico());
        cargarModulo("VETERINARIO", modelo.isVeterinaria());
        cargarModulo("PARQUEADERO", modelo.isParqueadero());
        cargarModulo("AGENDA", modelo.isAgenda());
        cargarModulo("RESTAURANTE", modelo.isRestaurante());
        cargarModulo("RECORDATORIOS", modelo.isRecordatorios());
        cargarModulo("LABORATORIO", modelo.isLaboratorio());
        cargarModulo("OFTALMOLOGIA", modelo.isOftalmologia());
        cargarModulo("FACTURACION LOTE", modelo.isFacturacionLote());
        cargarModulo(PERMISO_FACTURACION_ELECTRONICA, modelo.isFacturaElectronica());
        facturacionElectronicaCargada = modelo.isFacturaElectronica();
        cargarModulo("INVENTARIO POR BODEGAS", modelo.isInventarioBodegas());
        cargarModulo("PRODUCTOS DETALLADOS", modelo.isProductosSerial());
        cargarModulo("PRUEBAS EN FACTURACION ELECTRONICA", modelo.isPruebasFacturacionElectronica());
        cargarModulo(PERMISO_MULTIEMPRESA, modelo.isMultiempresa());
        asignarPermiso(PERMISO_EMPRESAS, String.valueOf(modelo.getNumeroEmpresas()));
        serviciosEspecialesCargados.clear();
        serviciosEspecialesCargados.addAll(serviciosEspecialesActivos());
        empresasCargadas = modelo.isMultiempresa() ? modelo.getNumeroEmpresas() : 0;

        asignarPermiso(PERMISO_REGIMEN, "SinIva".equals(modelo.getRegimen()) ? REGIMEN_SIMPLIFICADO : REGIMEN_COMUN);
        asignarPermiso(PERMISO_DIAS_ALERTA, String.valueOf(modelo.getDiasAntesAlertaBloqueo()));
        asignarPermiso(PERMISO_DIAS_GABELA, String.valueOf(modelo.getDiasDespuesGabelaBloqueo()));

        txtCodigo.setText(idCliente);
        txtNombre.setText(modelo.getNombre());
        txtNit1.setText(modelo.getNit());
        txtTelefono.setText(modelo.getTelefono());
        txtFechaInicio.setText(modelo.getFechaInicio());

        try {
            modeloEstadoCliente estado = instancias.getSqlIngresos().obtenerEstadoCliente(idCliente);
            terminalesCargadas = estado.getNumeroTerminales();
            asignarPermiso(PERMISO_TERMINALES, String.valueOf(terminalesCargadas));
        } catch (java.sql.SQLException | IllegalStateException ex) {
            Logger.getLogger(vistaMenu.class.getName()).log(Level.SEVERE, "Error al consultar las terminales del cliente", ex);
            alertas.alertFail("No se pudo consultar el número de terminales del cliente");
            return;
        }

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

    private static String generarIdentificadorCliente() {
        byte[] bytes = new byte[16];
        new java.security.SecureRandom().nextBytes(bytes);
        StringBuilder hex = new StringBuilder(32);
        for (byte b : bytes) {
            hex.append(String.format("%02x", b));
        }
        return hex.toString();
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
    private javax.swing.JButton btnGuardarCliente;
    private javax.swing.JButton btnGuardarPermisos;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnLimpiarClientes;
    private javax.swing.JCheckBox chkClienteFacturacionElectronica;
    private javax.swing.JCheckBox chkGenerarRegistroPruebas;
    private javax.swing.JComboBox cmbCiudad;
    private javax.swing.JComboBox cmbCodigoPostal;
    private javax.swing.JComboBox cmbDepartamento;
    private javax.swing.JComboBox cmbNaturaleza;
    private javax.swing.JComboBox cmbObligaciones;
    private javax.swing.JComboBox cmbTipoIdentificacion;
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
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
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
    private javax.swing.JLabel lbTipo;
    private Vistas.Ingresos.PanelIngresos panelIngresos;
    private Vistas.Reportes.PanelReportes panelReportes;
    private Vistas.Tarifas.PanelTarifasPlanes panelTarifas;
    private Vistas.Paquetes.PanelVentaPaquetes panelVentaPaquetes;
    private javax.swing.JPanel pnlDatos;
    private javax.swing.JPanel pnlInformacionClientes;
    private javax.swing.JPanel pnlPermisos;
    private javax.swing.JTable tblClientes;
    private javax.swing.JTable tblModulos;
    private javax.swing.JTable tblPagos;
    private javax.swing.JTextField txtCelular;
    private javax.swing.JTextField txtClienteId;
    private javax.swing.JTextField txtClienteNit;
    private javax.swing.JTextField txtClienteNombre;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtCodigoCiiu;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtEmailDefecto;
    private javax.swing.JTextField txtEmailEmisor;
    private javax.swing.JTextField txtEmailErrores;
    private javax.swing.JTextField txtFechaInicio;
    private javax.swing.JTextField txtIdentificacion;
    private javax.swing.JTextField txtIdentificadorPruebasDian;
    private javax.swing.JTextField txtLogo;
    private javax.swing.JTextField txtNit1;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPagosNit;
    private javax.swing.JTextField txtPagosNombre;
    private javax.swing.JTextField txtPrimerApellido;
    private javax.swing.JTextField txtPrimerNombre;
    private javax.swing.JTextField txtRazonSocial;
    private javax.swing.JTextField txtSegundoApellido;
    private javax.swing.JTextField txtSegundoNombre;
    private javax.swing.JTextField txtSitioWeb;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables

}
