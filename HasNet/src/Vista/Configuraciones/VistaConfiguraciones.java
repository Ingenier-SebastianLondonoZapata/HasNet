package Vista.Configuraciones;

import Controlador.Alertas.ControladorAlertas;

import Utilidades.Contactos.Bodegas;

import Dao.Configuraciones.DaoResoluciones;
import Dao.Configuraciones.DaoUsuarios;
import Dao.Productos.DaoProducto;
import Modelo.Usuarios.ModeloUsuario;
import Modelo.Usuarios.PermisoUsuario;
import formularios.buscUsuarios;
import Dao.Terceros.DaoBodegas;
import Modelo.Contactos.ModeloBodega;
import Modelo.Maestra.ModeloPrefijos;
import Modelo.Maestra.ModeloResolucion;
import Utilidades.Fechas;
import Vista.Contactos.VistaBodegaContacto;
import java.util.ArrayList;
import java.util.List;

import Utilidades.DatosMaestra;
import clases.Instancias;
import Dao.Configuraciones.DaoMaestra;
import Modelo.Maestra.ModeloMaestraGeneral;
import Modelo.Maestra.ModeloMaestraImpresiones;
import clases.Medico.ndCamposOrdenPredeterminada;
import clases.big;
import clases.cambiarColorTabla;
import clases.metodosGenerales;
import Modelo.Productos.ModeloProducto;
import configuracion.dlgFinSoporte;
import formularios.dlgPedirSuperContrasena;
import Vista.Productos.VistaBuscadorProductos;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class VistaConfiguraciones extends javax.swing.JInternalFrame {

    private DaoProducto daoProducto;
    private DaoBodegas daoBodegas;
    private DaoMaestra daoMaestra;
    private DaoResoluciones daoResoluciones;
    private final List<ModeloBodega> bodegasCombo = new ArrayList<>();
    private boolean cargandoBodegas = false;

    private String idProductoOrdenServicio = "";

    metodosGenerales metodos;
    private Instancias instancias;
    private JComponent Barra;
    int teclasPresionadas = 0;
    String turno, valor, url, simbolo = "";
    private PanelDisenoMesas panelDisenoMesas;

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public VistaConfiguraciones() {

        initComponents();

        if (java.beans.Beans.isDesignTime()) {
            return;
        }

        daoProducto = new DaoProducto();
        daoBodegas = new DaoBodegas();
        daoMaestra = new DaoMaestra();
        daoResoluciones = new DaoResoluciones();
        instancias = Instancias.getInstancias();
        metodos = new metodosGenerales();

        inicializarTablaImpresoraPorTipo();
        inicializarTablaImpresionDocumentos();
        inicializarTablaInformacionEmpresa();
        inicializarTablaResponsabilidadesFiscales();
        inicializarTablaInformacionImpresion();
        inicializarTablaConfiguracionesFacturacion();
        inicializarTablaConfiguraciones1(instancias.getConfiguraciones().isRestaurante(), instancias.getConfiguraciones().isCreditos());

        tblImpresoras1.setDefaultRenderer(Object.class, new cambiarColorTabla(14, 0));
        tblDiasBloqueo.setDefaultRenderer(Object.class, new cambiarColorTabla(20, 0));

        panelDisenoMesas = new PanelDisenoMesas();
        panelDisenoMesas.setListener(new PanelDisenoMesas.ListenerCambio() {
            public void onCambioCantidad(int totalMesas) {
                txtNumMesas.setText(String.valueOf(totalMesas));
            }
        });
        jScrollPane11.setViewportView(panelDisenoMesas);

        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
        Barra.setSize(0, 0);
        Barra.setPreferredSize(new Dimension(0, 0));
        setBorder(null);
        repaint();

        if (instancias.getConfiguraciones().isLaboratorio()) {
            lbRazon11.setVisible(false);
            txtaCobrar.setVisible(false);
            txtaCobrar1.setVisible(false);
            lbRazon19.setVisible(false);
        }

        this.simbolo = instancias.getSimbolo();

        actualizarImpresorasDisponibles();
        inicializarVistaLogo();
        agregarScrollPestanaImpresiones();
        inicializarImpresionPorGrupo();

        tblBodegasInternas.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    actualizarBotonEstadoBodega();
                }
            }
        });

        ocultarColumna(tblBodegasInternas, 0);
        cargarBodegasInternas();

        tblComprobantes.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    actualizarBotonEstadoResolucion();
                }
            }
        });
        cargarPrefijos();
        cargarTablaResoluciones();
        inicializarUsuarios();

        this.registerKeyboardAction(accion("primera"), "primera", KeyStroke.getKeyStroke(KeyEvent.VK_A, Event.CTRL_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
        this.registerKeyboardAction(accion("segunda"), "segunda", KeyStroke.getKeyStroke(KeyEvent.VK_M, Event.CTRL_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
        this.registerKeyboardAction(accion("finSoporte"), "finSoporte", KeyStroke.getKeyStroke(KeyEvent.VK_F, Event.CTRL_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
    }

    private ActionListener accion(final String opc) {
        ActionListener a = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (opc) {
                    case "finSoporte":
                        dlgFinSoporte finSoporte = new dlgFinSoporte();
                        finSoporte.setVisible(true);
                        break;
                    case "primera":
                        if (teclasPresionadas == 0) {
                            teclasPresionadas = 1;
                        } else {
                            teclasPresionadas = 0;
                        }
                        break;
                    case "segunda":
                        if (teclasPresionadas == 1) {
                            teclasPresionadas = 2;
                        } else if (teclasPresionadas == 2) {
                            new dlgPedirSuperContrasena(null, true, "").setVisible(true);
                            teclasPresionadas = 0;
                        } else {
                            teclasPresionadas = 0;
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

        jPopupMenu2 = new javax.swing.JPopupMenu();
        popBorrar1 = new javax.swing.JMenuItem();
        grupoPrevisualizarComanda = new javax.swing.ButtonGroup();
        grupoBorrarMesas = new javax.swing.ButtonGroup();
        grupoCopiaSeguridad = new javax.swing.ButtonGroup();
        grupoGenerarEgreso = new javax.swing.ButtonGroup();
        grupoIngresoSinCupo = new javax.swing.ButtonGroup();
        grupoFacturarDiasPlazo = new javax.swing.ButtonGroup();
        grupoModificarValorFinal = new javax.swing.ButtonGroup();
        grupoCobrarCasco = new javax.swing.ButtonGroup();
        jPopupMenu3 = new javax.swing.JPopupMenu();
        popBorrar2 = new javax.swing.JMenuItem();
        scrFormulario = new javax.swing.JScrollPane();
        pnlFormulario = new javax.swing.JPanel();
        tabPanel = new javax.swing.JTabbedPane();
        pnlUsuarios = new javax.swing.JPanel();
        tapPanelPermisos = new javax.swing.JTabbedPane();
        pnlPermisosVentas = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane25 = new javax.swing.JScrollPane();
        tblPermisosVentas = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane26 = new javax.swing.JScrollPane();
        tblPermisosProductos = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        scrPermisosTerceros = new javax.swing.JScrollPane();
        tblPermisosTerceros = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jLabel56 = new javax.swing.JLabel();
        scrPermisosCartera = new javax.swing.JScrollPane();
        tblPermisosCartera = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        scrPermisosTesoreria = new javax.swing.JScrollPane();
        tblPermisosTesoreria = new javax.swing.JTable();
        jPanel33 = new javax.swing.JPanel();
        jLabel68 = new javax.swing.JLabel();
        scrPermisosAgenda = new javax.swing.JScrollPane();
        tblPermisosAgenda = new javax.swing.JTable();
        jPanel38 = new javax.swing.JPanel();
        jLabel51 = new javax.swing.JLabel();
        scrPermisosMedico = new javax.swing.JScrollPane();
        tblPermisosMedico = new javax.swing.JTable();
        jPanel43 = new javax.swing.JPanel();
        jLabel66 = new javax.swing.JLabel();
        scrPermisosVeterinaria = new javax.swing.JScrollPane();
        tblPermisosVeterinaria = new javax.swing.JTable();
        pnlUsuario = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel111 = new javax.swing.JLabel();
        jLabel112 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtUsuario = new javax.swing.JTextField();
        txtContra = new javax.swing.JPasswordField();
        txtContra2 = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        lbFoto = new javax.swing.JLabel();
        pnlBotones = new javax.swing.JPanel();
        btnNuevo = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnListarUsuarios = new javax.swing.JButton();
        pnlDatosEmpresa = new javax.swing.JPanel();
        lbOtroConsecutivo = new javax.swing.JTextField();
        txtNumeroFactosElectronicas = new javax.swing.JTextField();
        jScrollPane19 = new javax.swing.JScrollPane();
        tblInformacionEmpresa = new javax.swing.JTable();
        jScrollPane21 = new javax.swing.JScrollPane();
        tblConfiguraciones = new javax.swing.JTable();
        jScrollPane22 = new javax.swing.JScrollPane();
        tblConfiguraciones1 = new javax.swing.JTable();
        jScrollPane23 = new javax.swing.JScrollPane();
        tblResponsabilidadesFiscales = new javax.swing.JTable();
        pnlResoluciones = new javax.swing.JPanel();
        pnlBodegas = new javax.swing.JPanel();
        jScrollPane15 = new javax.swing.JScrollPane();
        tblBodegasInternas = new javax.swing.JTable();
        btnAgregarBodega = new javax.swing.JButton();
        btnModificarBodega = new javax.swing.JButton();
        btnCambiarEstadoBodega = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        cmbBodegas = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblComprobantes = new javax.swing.JTable();
        btnAgregarResolucion = new javax.swing.JButton();
        btnModificarResolucion = new javax.swing.JButton();
        btnCambiarEstadoResolucion = new javax.swing.JButton();
        pnlPrefijos = new javax.swing.JPanel();
        jScrollPane24 = new javax.swing.JScrollPane();
        tblPrefijos = new javax.swing.JTable();
        pnlImpresiones = new javax.swing.JPanel();
        pnlImpresionesGrupo = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblImpresoras1 = new javax.swing.JTable();
        btnGuardar4 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblImpresoras = new javax.swing.JTable();
        btnActualizarImpresoras = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane16 = new javax.swing.JScrollPane();
        tblImpresionDocumentos = new javax.swing.JTable();
        jScrollPane17 = new javax.swing.JScrollPane();
        tblImpresoraPorTipo = new javax.swing.JTable();
        jScrollPane18 = new javax.swing.JScrollPane();
        tblOpcionesImpresiones = new javax.swing.JTable();
        jScrollPane20 = new javax.swing.JScrollPane();
        tblInformacionImpresion = new javax.swing.JTable();
        btnActualizarLogo = new javax.swing.JButton();
        pnlLogo = new javax.swing.JPanel();
        pnlRestaurante = new javax.swing.JPanel();
        cmbDoctores2 = new javax.swing.JComboBox();
        lbCelular4 = new javax.swing.JLabel();
        txtColumnas = new javax.swing.JTextField();
        lbCelular8 = new javax.swing.JLabel();
        txtFilas = new javax.swing.JTextField();
        jScrollPane11 = new javax.swing.JScrollPane();
        tblRestaurante = new javax.swing.JTable();
        txtNumMesas = new javax.swing.JLabel();
        lbCelular10 = new javax.swing.JLabel();
        btnGenerarTabla = new javax.swing.JButton();
        jScrollPane12 = new javax.swing.JScrollPane();
        tblMesas = new javax.swing.JTable();
        btnActualizarMesas = new javax.swing.JButton();
        pnlMedico = new javax.swing.JPanel();
        pnlInformacionMedico2 = new javax.swing.JPanel();
        lbRazon4 = new javax.swing.JLabel();
        lbRazon = new javax.swing.JLabel();
        lbRazon13 = new javax.swing.JLabel();
        cmbConcepto = new javax.swing.JComboBox();
        cmbAmbito = new javax.swing.JComboBox();
        cmbCausa = new javax.swing.JComboBox();
        lbRazon25 = new javax.swing.JLabel();
        cmbActoQuirurgico = new javax.swing.JComboBox();
        lbRazon28 = new javax.swing.JLabel();
        cmbCodigoConsulta = new javax.swing.JComboBox();
        pnlOrdenMedica = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtCodigoPrestadorServico = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        lbTelefono20 = new javax.swing.JLabel();
        lbTelefono21 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cmbTipoPrestador = new javax.swing.JComboBox();
        rdSiImprimirOrden = new javax.swing.JRadioButton();
        rdNoImprimirOrden = new javax.swing.JRadioButton();
        rdSiImprimirFactura = new javax.swing.JRadioButton();
        rdNoImprimirFactura = new javax.swing.JRadioButton();
        rdSiGenerarOrden = new javax.swing.JRadioButton();
        rdNoGenerarOrden = new javax.swing.JRadioButton();
        pnlInformacionMedico1 = new javax.swing.JPanel();
        lbRazon23 = new javax.swing.JLabel();
        lbRazon5 = new javax.swing.JLabel();
        lbRazon11 = new javax.swing.JLabel();
        lbRazon19 = new javax.swing.JLabel();
        lbRazon14 = new javax.swing.JLabel();
        lbRazon15 = new javax.swing.JLabel();
        rdProcedimiento = new javax.swing.JRadioButton();
        rdConsulta = new javax.swing.JRadioButton();
        cmbFinalidad = new javax.swing.JComboBox();
        txtaCobrar = new javax.swing.JTextField();
        txtValor1 = new javax.swing.JTextField();
        cmbForma = new javax.swing.JComboBox();
        cmbPersonal = new javax.swing.JComboBox();
        txtaCobrar1 = new javax.swing.JTextField();
        pnlLaboratorio = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblExamenes = new javax.swing.JTable();
        lbTelefono38 = new javax.swing.JLabel();
        pnlAgenda = new javax.swing.JPanel();
        pnlIntervalosAgenda = new javax.swing.JPanel();
        cmbIntervalo = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cmbHoraFin = new javax.swing.JComboBox();
        cmbHoraInicio = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        cmbDoctores1 = new javax.swing.JComboBox();
        jPanel28 = new javax.swing.JPanel();
        dtFecha = new com.toedter.calendar.JCalendar();
        jScrollPane10 = new javax.swing.JScrollPane();
        tblHoras = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        cmbDoctores = new javax.swing.JComboBox();
        jPanel29 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblDiasBloqueo = new javax.swing.JTable();
        pnlVeterinario = new javax.swing.JPanel();
        pnlAgenda1 = new javax.swing.JPanel();
        cmbIntervalo1 = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        cmbHoraFin1 = new javax.swing.JComboBox();
        cmbHoraInicio1 = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        cmbDoctores3 = new javax.swing.JComboBox();
        jPanel1 = new javax.swing.JPanel();
        txtNombreFormato = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        rdRedondearCantidades = new javax.swing.JRadioButton();
        pnlParqueadero = new javax.swing.JPanel();
        jPanel47 = new javax.swing.JPanel();
        jScrollPane13 = new javax.swing.JScrollPane();
        tblCascos = new javax.swing.JTable();
        jPanel48 = new javax.swing.JPanel();
        lbCelular5 = new javax.swing.JLabel();
        lbNit14 = new javax.swing.JLabel();
        txtColumnas1 = new javax.swing.JTextField();
        lbCelular9 = new javax.swing.JLabel();
        txtFilas1 = new javax.swing.JTextField();
        btnGuardar6 = new javax.swing.JButton();
        jPanel49 = new javax.swing.JPanel();
        jScrollPane14 = new javax.swing.JScrollPane();
        tblConfig = new javax.swing.JTable();
        txtCodProducto = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        btnBusProd = new javax.swing.JButton();
        jPanel50 = new javax.swing.JPanel();
        jPanel51 = new javax.swing.JPanel();
        lbCelular11 = new javax.swing.JLabel();
        rbtNo = new javax.swing.JRadioButton();
        rbtSi = new javax.swing.JRadioButton();
        lbCelular13 = new javax.swing.JLabel();
        rbtNo1 = new javax.swing.JRadioButton();
        rbtSi1 = new javax.swing.JRadioButton();
        lbCelular14 = new javax.swing.JLabel();
        lbCelular15 = new javax.swing.JLabel();
        rbtNo2 = new javax.swing.JRadioButton();
        rbtSi2 = new javax.swing.JRadioButton();
        rbtNo3 = new javax.swing.JRadioButton();
        rbtSi3 = new javax.swing.JRadioButton();
        txtCosto = new javax.swing.JTextField();
        lbCelular16 = new javax.swing.JLabel();
        rbtNoModificarValor = new javax.swing.JRadioButton();
        rbtSiModificarValor = new javax.swing.JRadioButton();
        jPanel52 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        btnGuardarMaestra = new javax.swing.JButton();

        popBorrar1.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        popBorrar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/eliminar-cancelar-icono-4935-16.png"))); // NOI18N
        popBorrar1.setText("Borrar");
        popBorrar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                popBorrar1ActionPerformed(evt);
            }
        });
        jPopupMenu2.add(popBorrar1);

        popBorrar2.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        popBorrar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/eliminar-cancelar-icono-4935-16.png"))); // NOI18N
        popBorrar2.setText("Borrar");
        popBorrar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                popBorrar2ActionPerformed(evt);
            }
        });
        jPopupMenu3.add(popBorrar2);

        setTitle("Factura");

        pnlFormulario.setBackground(new java.awt.Color(255, 255, 255));

        tabPanel.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N

        pnlUsuarios.setBackground(new java.awt.Color(255, 255, 255));

        tapPanelPermisos.setBackground(new java.awt.Color(255, 255, 255));
        tapPanelPermisos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tapPanelPermisos.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        tapPanelPermisos.setOpaque(true);

        pnlPermisosVentas.setBackground(new java.awt.Color(255, 255, 255));

        jLabel14.setBackground(new java.awt.Color(204, 204, 204));
        jLabel14.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Permisos ventas");
        jLabel14.setOpaque(true);

        tblPermisosVentas.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        tblPermisosVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Acción", "Permiso"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPermisosVentas.setRowHeight(24);
        tblPermisosVentas.getTableHeader().setReorderingAllowed(false);
        jScrollPane25.setViewportView(tblPermisosVentas);
        if (tblPermisosVentas.getColumnModel().getColumnCount() > 0) {
            tblPermisosVentas.getColumnModel().getColumn(1).setMinWidth(80);
            tblPermisosVentas.getColumnModel().getColumn(1).setPreferredWidth(80);
            tblPermisosVentas.getColumnModel().getColumn(1).setMaxWidth(80);
        }

        javax.swing.GroupLayout pnlPermisosVentasLayout = new javax.swing.GroupLayout(pnlPermisosVentas);
        pnlPermisosVentas.setLayout(pnlPermisosVentasLayout);
        pnlPermisosVentasLayout.setHorizontalGroup(
            pnlPermisosVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPermisosVentasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPermisosVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane25, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlPermisosVentasLayout.setVerticalGroup(
            pnlPermisosVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPermisosVentasLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane25, javax.swing.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
                .addContainerGap())
        );

        tapPanelPermisos.addTab("Ventas", pnlPermisosVentas);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel19.setBackground(new java.awt.Color(204, 204, 204));
        jLabel19.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Permisos productos");
        jLabel19.setOpaque(true);

        tblPermisosProductos.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        tblPermisosProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Acción", "Permiso"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPermisosProductos.setRowHeight(24);
        tblPermisosProductos.getTableHeader().setReorderingAllowed(false);
        jScrollPane26.setViewportView(tblPermisosProductos);
        if (tblPermisosProductos.getColumnModel().getColumnCount() > 0) {
            tblPermisosProductos.getColumnModel().getColumn(1).setMinWidth(80);
            tblPermisosProductos.getColumnModel().getColumn(1).setPreferredWidth(80);
            tblPermisosProductos.getColumnModel().getColumn(1).setMaxWidth(80);
        }

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane26, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane26, javax.swing.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
                .addContainerGap())
        );

        tapPanelPermisos.addTab("Productos", jPanel5);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jLabel33.setBackground(new java.awt.Color(204, 204, 204));
        jLabel33.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setText("Permisos contactos");
        jLabel33.setOpaque(true);

        tblPermisosTerceros.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        tblPermisosTerceros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Acción", "Permiso"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPermisosTerceros.setRowHeight(24);
        tblPermisosTerceros.getTableHeader().setReorderingAllowed(false);
        scrPermisosTerceros.setViewportView(tblPermisosTerceros);
        if (tblPermisosTerceros.getColumnModel().getColumnCount() > 0) {
            tblPermisosTerceros.getColumnModel().getColumn(1).setMinWidth(80);
            tblPermisosTerceros.getColumnModel().getColumn(1).setPreferredWidth(80);
            tblPermisosTerceros.getColumnModel().getColumn(1).setMaxWidth(80);
        }

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scrPermisosTerceros, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(scrPermisosTerceros, javax.swing.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
                .addContainerGap())
        );

        tapPanelPermisos.addTab("Contactos", jPanel6);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jLabel56.setBackground(new java.awt.Color(204, 204, 204));
        jLabel56.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel56.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel56.setText("Permisos cartera");
        jLabel56.setOpaque(true);

        tblPermisosCartera.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        tblPermisosCartera.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Acción", "Permiso"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPermisosCartera.setRowHeight(24);
        tblPermisosCartera.getTableHeader().setReorderingAllowed(false);
        scrPermisosCartera.setViewportView(tblPermisosCartera);
        if (tblPermisosCartera.getColumnModel().getColumnCount() > 0) {
            tblPermisosCartera.getColumnModel().getColumn(1).setMinWidth(80);
            tblPermisosCartera.getColumnModel().getColumn(1).setPreferredWidth(80);
            tblPermisosCartera.getColumnModel().getColumn(1).setMaxWidth(80);
        }

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel56, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scrPermisosCartera, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(scrPermisosCartera, javax.swing.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
                .addContainerGap())
        );

        tapPanelPermisos.addTab("Cartera", jPanel7);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jLabel35.setBackground(new java.awt.Color(204, 204, 204));
        jLabel35.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel35.setText("Permisos tesoreria");
        jLabel35.setOpaque(true);

        tblPermisosTesoreria.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        tblPermisosTesoreria.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Acción", "Permiso"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPermisosTesoreria.setRowHeight(24);
        tblPermisosTesoreria.getTableHeader().setReorderingAllowed(false);
        scrPermisosTesoreria.setViewportView(tblPermisosTesoreria);
        if (tblPermisosTesoreria.getColumnModel().getColumnCount() > 0) {
            tblPermisosTesoreria.getColumnModel().getColumn(1).setMinWidth(80);
            tblPermisosTesoreria.getColumnModel().getColumn(1).setPreferredWidth(80);
            tblPermisosTesoreria.getColumnModel().getColumn(1).setMaxWidth(80);
        }

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scrPermisosTesoreria, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(scrPermisosTesoreria, javax.swing.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
                .addContainerGap())
        );

        tapPanelPermisos.addTab("Tesoreria", jPanel8);

        jPanel33.setBackground(new java.awt.Color(255, 255, 255));

        jLabel68.setBackground(new java.awt.Color(204, 204, 204));
        jLabel68.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel68.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel68.setText("Permisos agenda");
        jLabel68.setOpaque(true);

        tblPermisosAgenda.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        tblPermisosAgenda.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Acción", "Permiso"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPermisosAgenda.setRowHeight(24);
        tblPermisosAgenda.getTableHeader().setReorderingAllowed(false);
        scrPermisosAgenda.setViewportView(tblPermisosAgenda);
        if (tblPermisosAgenda.getColumnModel().getColumnCount() > 0) {
            tblPermisosAgenda.getColumnModel().getColumn(1).setMinWidth(80);
            tblPermisosAgenda.getColumnModel().getColumn(1).setPreferredWidth(80);
            tblPermisosAgenda.getColumnModel().getColumn(1).setMaxWidth(80);
        }

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel68, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scrPermisosAgenda, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(scrPermisosAgenda, javax.swing.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
                .addContainerGap())
        );

        tapPanelPermisos.addTab("Agenda", jPanel33);

        jPanel38.setBackground(new java.awt.Color(255, 255, 255));

        jLabel51.setBackground(new java.awt.Color(204, 204, 204));
        jLabel51.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel51.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel51.setText("Permisos médico");
        jLabel51.setOpaque(true);

        tblPermisosMedico.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        tblPermisosMedico.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Acción", "Permiso"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPermisosMedico.setRowHeight(24);
        tblPermisosMedico.getTableHeader().setReorderingAllowed(false);
        scrPermisosMedico.setViewportView(tblPermisosMedico);
        if (tblPermisosMedico.getColumnModel().getColumnCount() > 0) {
            tblPermisosMedico.getColumnModel().getColumn(1).setMinWidth(80);
            tblPermisosMedico.getColumnModel().getColumn(1).setPreferredWidth(80);
            tblPermisosMedico.getColumnModel().getColumn(1).setMaxWidth(80);
        }

        javax.swing.GroupLayout jPanel38Layout = new javax.swing.GroupLayout(jPanel38);
        jPanel38.setLayout(jPanel38Layout);
        jPanel38Layout.setHorizontalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel38Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel51, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scrPermisosMedico, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel38Layout.setVerticalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel38Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(scrPermisosMedico, javax.swing.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
                .addContainerGap())
        );

        tapPanelPermisos.addTab("Medico", jPanel38);

        jPanel43.setBackground(new java.awt.Color(255, 255, 255));

        jLabel66.setBackground(new java.awt.Color(204, 204, 204));
        jLabel66.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel66.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel66.setText("Permisos veterinaria");
        jLabel66.setOpaque(true);

        tblPermisosVeterinaria.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        tblPermisosVeterinaria.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Acción", "Permiso"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPermisosVeterinaria.setRowHeight(24);
        tblPermisosVeterinaria.getTableHeader().setReorderingAllowed(false);
        scrPermisosVeterinaria.setViewportView(tblPermisosVeterinaria);
        if (tblPermisosVeterinaria.getColumnModel().getColumnCount() > 0) {
            tblPermisosVeterinaria.getColumnModel().getColumn(1).setMinWidth(80);
            tblPermisosVeterinaria.getColumnModel().getColumn(1).setPreferredWidth(80);
            tblPermisosVeterinaria.getColumnModel().getColumn(1).setMaxWidth(80);
        }

        javax.swing.GroupLayout jPanel43Layout = new javax.swing.GroupLayout(jPanel43);
        jPanel43.setLayout(jPanel43Layout);
        jPanel43Layout.setHorizontalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel43Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel66, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scrPermisosVeterinaria, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel43Layout.setVerticalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel43Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(scrPermisosVeterinaria, javax.swing.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
                .addContainerGap())
        );

        tapPanelPermisos.addTab("Veterinaria", jPanel43);

        pnlUsuario.setBackground(new java.awt.Color(255, 255, 255));
        pnlUsuario.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Información del usuario", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 14))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Usuario:");

        jLabel5.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Contraseña:");

        jLabel111.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel111.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel111.setText("Confirmación:");

        jLabel112.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel112.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel112.setText("Nombre:");

        txtNombre.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtNombre.setName("Nombre"); // NOI18N

        txtUsuario.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtUsuario.setName("Usuario"); // NOI18N
        txtUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUsuarioKeyReleased(evt);
            }
        });

        txtContra.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtContra.setName("Contraseña"); // NOI18N

        txtContra2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtContra2.setName("Confirmar Contraseña"); // NOI18N

        jButton1.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jButton1.setText("FIRMA");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        lbFoto.setBackground(new java.awt.Color(204, 204, 204));
        lbFoto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbFoto.setMaximumSize(new java.awt.Dimension(200, 200));
        lbFoto.setMinimumSize(new java.awt.Dimension(200, 200));
        lbFoto.setName(""); // NOI18N
        lbFoto.setOpaque(true);
        lbFoto.setPreferredSize(new java.awt.Dimension(200, 200));
        lbFoto.setRequestFocusEnabled(false);

        javax.swing.GroupLayout pnlUsuarioLayout = new javax.swing.GroupLayout(pnlUsuario);
        pnlUsuario.setLayout(pnlUsuarioLayout);
        pnlUsuarioLayout.setHorizontalGroup(
            pnlUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlUsuarioLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(pnlUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlUsuarioLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(lbFoto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlUsuarioLayout.createSequentialGroup()
                        .addGroup(pnlUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(pnlUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel112, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel111, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtContra2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtContra, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                .addComponent(txtNombre, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtUsuario, javax.swing.GroupLayout.Alignment.TRAILING)))))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        pnlUsuarioLayout.setVerticalGroup(
            pnlUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUsuarioLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(pnlUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE))
                .addGap(4, 4, 4)
                .addGroup(pnlUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel112, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE))
                .addGap(4, 4, 4)
                .addGroup(pnlUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtContra, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE))
                .addGap(4, 4, 4)
                .addGroup(pnlUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel111, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                    .addComponent(txtContra2, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(lbFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
        );

        pnlBotones.setBackground(new java.awt.Color(255, 255, 255));

        btnNuevo.setBackground(new java.awt.Color(204, 204, 204));
        btnNuevo.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/nuevo.png"))); // NOI18N
        btnNuevo.setText("LIMPIAR");
        btnNuevo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnNuevo.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnNuevo.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnActualizar.setBackground(new java.awt.Color(93, 173, 226));
        btnActualizar.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/actualizar.png"))); // NOI18N
        btnActualizar.setText("MODIFICAR");
        btnActualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnActualizar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnActualizar.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnGuardar.setBackground(new java.awt.Color(46, 204, 113));
        btnGuardar.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
        btnGuardar.setText("GUARDAR");
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnGuardar.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnListarUsuarios.setBackground(new java.awt.Color(247, 220, 111));
        btnListarUsuarios.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        btnListarUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/listado.png"))); // NOI18N
        btnListarUsuarios.setText("LISTADO");
        btnListarUsuarios.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnListarUsuarios.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnListarUsuarios.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnListarUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListarUsuariosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlBotonesLayout = new javax.swing.GroupLayout(pnlBotones);
        pnlBotones.setLayout(pnlBotonesLayout);
        pnlBotonesLayout.setHorizontalGroup(
            pnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBotonesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5)
                .addGroup(pnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnListarUsuarios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlBotonesLayout.setVerticalGroup(
            pnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBotonesLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(pnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNuevo)
                    .addComponent(btnGuardar))
                .addGap(3, 3, 3)
                .addGroup(pnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnListarUsuarios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout pnlUsuariosLayout = new javax.swing.GroupLayout(pnlUsuarios);
        pnlUsuarios.setLayout(pnlUsuariosLayout);
        pnlUsuariosLayout.setHorizontalGroup(
            pnlUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUsuariosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tapPanelPermisos))
        );
        pnlUsuariosLayout.setVerticalGroup(
            pnlUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUsuariosLayout.createSequentialGroup()
                .addGroup(pnlUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlUsuariosLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(pnlUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlUsuariosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tapPanelPermisos)))
                .addGap(10, 10, 10))
        );

        tabPanel.addTab("Gestión de usuarios", pnlUsuarios);

        pnlDatosEmpresa.setBackground(new java.awt.Color(255, 255, 255));

        lbOtroConsecutivo.setEditable(false);
        lbOtroConsecutivo.setBackground(new java.awt.Color(255, 255, 255));
        lbOtroConsecutivo.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbOtroConsecutivo.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        lbOtroConsecutivo.setText("Número de facturas electronicas disponibles:");
        lbOtroConsecutivo.setBorder(null);
        lbOtroConsecutivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lbOtroConsecutivoActionPerformed(evt);
            }
        });
        lbOtroConsecutivo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lbOtroConsecutivoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                lbOtroConsecutivoKeyTyped(evt);
            }
        });

        txtNumeroFactosElectronicas.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        txtNumeroFactosElectronicas.setForeground(new java.awt.Color(255, 0, 0));
        txtNumeroFactosElectronicas.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNumeroFactosElectronicas.setText("0");
        txtNumeroFactosElectronicas.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtNumeroFactosElectronicas.setDisabledTextColor(new java.awt.Color(255, 0, 0));
        txtNumeroFactosElectronicas.setEnabled(false);
        txtNumeroFactosElectronicas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNumeroFactosElectronicasKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNumeroFactosElectronicasKeyTyped(evt);
            }
        });

        tblInformacionEmpresa.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        tblInformacionEmpresa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Nombre", null}
            },
            new String [] {
                "Datos de la empresa", "Valor"
            }
        ));
        tblInformacionEmpresa.setRowHeight(24);
        jScrollPane19.setViewportView(tblInformacionEmpresa);
        if (tblInformacionEmpresa.getColumnModel().getColumnCount() > 0) {
            tblInformacionEmpresa.getColumnModel().getColumn(0).setMinWidth(110);
            tblInformacionEmpresa.getColumnModel().getColumn(0).setPreferredWidth(110);
            tblInformacionEmpresa.getColumnModel().getColumn(0).setMaxWidth(110);
        }

        tblConfiguraciones.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        tblConfiguraciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Consecutivo adicional en factura:", null}
            },
            new String [] {
                "Datos de la empresa", "Valor"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblConfiguraciones.setRowHeight(24);
        jScrollPane21.setViewportView(tblConfiguraciones);

        tblConfiguraciones1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        tblConfiguraciones1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Consecutivo adicional en factura:", null}
            },
            new String [] {
                "Datos de la empresa", "Valor"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblConfiguraciones1.setRowHeight(24);
        jScrollPane22.setViewportView(tblConfiguraciones1);

        tblResponsabilidadesFiscales.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        tblResponsabilidadesFiscales.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"No aplica", null}
            },
            new String [] {
                "Datos de la empresa", "Valor"
            }
        ));
        tblResponsabilidadesFiscales.setRowHeight(24);
        jScrollPane23.setViewportView(tblResponsabilidadesFiscales);

        javax.swing.GroupLayout pnlDatosEmpresaLayout = new javax.swing.GroupLayout(pnlDatosEmpresa);
        pnlDatosEmpresa.setLayout(pnlDatosEmpresaLayout);
        pnlDatosEmpresaLayout.setHorizontalGroup(
            pnlDatosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatosEmpresaLayout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addGroup(pnlDatosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlDatosEmpresaLayout.createSequentialGroup()
                        .addComponent(lbOtroConsecutivo, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNumeroFactosElectronicas, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlDatosEmpresaLayout.createSequentialGroup()
                        .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 522, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane23, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(pnlDatosEmpresaLayout.createSequentialGroup()
                        .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        pnlDatosEmpresaLayout.setVerticalGroup(
            pnlDatosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatosEmpresaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDatosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbOtroConsecutivo, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addComponent(txtNumeroFactosElectronicas, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDatosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane23, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                    .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(5, 5, 5)
                .addGroup(pnlDatosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane22, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                    .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(10, 10, 10))
        );

        tabPanel.addTab("Datos Empresa", pnlDatosEmpresa);

        pnlResoluciones.setBackground(new java.awt.Color(255, 255, 255));

        pnlBodegas.setBackground(new java.awt.Color(255, 255, 255));
        pnlBodegas.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Bodegas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 14))); // NOI18N

        tblBodegasInternas.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        tblBodegasInternas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Nombre bodega", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblBodegasInternas.setRowHeight(24);
        jScrollPane15.setViewportView(tblBodegasInternas);

        btnAgregarBodega.setBackground(new java.awt.Color(46, 204, 113));
        btnAgregarBodega.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        btnAgregarBodega.setText("Agregar bodega");
        btnAgregarBodega.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnAgregarBodega.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnAgregarBodega.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnAgregarBodega.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarBodegaActionPerformed(evt);
            }
        });

        btnModificarBodega.setBackground(new java.awt.Color(93, 173, 226));
        btnModificarBodega.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        btnModificarBodega.setText("Modificar bodega");
        btnModificarBodega.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnModificarBodega.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnModificarBodega.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnModificarBodega.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarBodegaActionPerformed(evt);
            }
        });

        btnCambiarEstadoBodega.setBackground(new java.awt.Color(241, 148, 138));
        btnCambiarEstadoBodega.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        btnCambiarEstadoBodega.setText("Inactivar bodega");
        btnCambiarEstadoBodega.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnCambiarEstadoBodega.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnCambiarEstadoBodega.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnCambiarEstadoBodega.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCambiarEstadoBodegaActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel13.setText("Bodega por defecto:");

        cmbBodegas.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        javax.swing.GroupLayout pnlBodegasLayout = new javax.swing.GroupLayout(pnlBodegas);
        pnlBodegas.setLayout(pnlBodegasLayout);
        pnlBodegasLayout.setHorizontalGroup(
            pnlBodegasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBodegasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBodegasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlBodegasLayout.createSequentialGroup()
                        .addComponent(btnAgregarBodega, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(btnModificarBodega)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCambiarEstadoBodega))
                    .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(pnlBodegasLayout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbBodegas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlBodegasLayout.setVerticalGroup(
            pnlBodegasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBodegasLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(pnlBodegasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbBodegas, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlBodegasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregarBodega, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnModificarBodega, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCambiarEstadoBodega, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Resoluciones", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 14))); // NOI18N

        tblComprobantes.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        tblComprobantes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Descripción resolución", "Tipo de resolución", "Diseño", "Número resolución", "Prefijo", "Fecha inicio", "Fecha final", "# Del", "# Hasta", "# Actual", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblComprobantes.setRowHeight(24);
        tblComprobantes.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblComprobantes.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblComprobantes);

        btnAgregarResolucion.setBackground(new java.awt.Color(46, 204, 113));
        btnAgregarResolucion.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        btnAgregarResolucion.setText("Agregar resolución");
        btnAgregarResolucion.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnAgregarResolucion.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnAgregarResolucion.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnAgregarResolucion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarResolucionActionPerformed(evt);
            }
        });

        btnModificarResolucion.setBackground(new java.awt.Color(93, 173, 226));
        btnModificarResolucion.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        btnModificarResolucion.setText("Modificar resolución");
        btnModificarResolucion.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnModificarResolucion.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnModificarResolucion.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnModificarResolucion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarResolucionActionPerformed(evt);
            }
        });

        btnCambiarEstadoResolucion.setBackground(new java.awt.Color(241, 148, 138));
        btnCambiarEstadoResolucion.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        btnCambiarEstadoResolucion.setText("Inactivar resolución");
        btnCambiarEstadoResolucion.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnCambiarEstadoResolucion.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnCambiarEstadoResolucion.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnCambiarEstadoResolucion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCambiarEstadoResolucionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 893, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnAgregarResolucion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnModificarResolucion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCambiarEstadoResolucion)))
                .addGap(5, 5, 5))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregarResolucion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnModificarResolucion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCambiarEstadoResolucion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6))
        );

        pnlPrefijos.setBackground(new java.awt.Color(255, 255, 255));
        pnlPrefijos.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Prefijos", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14))); // NOI18N
        pnlPrefijos.setToolTipText("");

        tblPrefijos.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        tblPrefijos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Abonos", null},
                {"Egresos", null},
                {"Notas crédito", null},
                {"Notas débito", null}
            },
            new String [] {
                "Documento", "Prefijo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPrefijos.setRowHeight(24);
        tblPrefijos.getTableHeader().setReorderingAllowed(false);
        jScrollPane24.setViewportView(tblPrefijos);

        javax.swing.GroupLayout pnlPrefijosLayout = new javax.swing.GroupLayout(pnlPrefijos);
        pnlPrefijos.setLayout(pnlPrefijosLayout);
        pnlPrefijosLayout.setHorizontalGroup(
            pnlPrefijosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPrefijosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane24, javax.swing.GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlPrefijosLayout.setVerticalGroup(
            pnlPrefijosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPrefijosLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jScrollPane24, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlResolucionesLayout = new javax.swing.GroupLayout(pnlResoluciones);
        pnlResoluciones.setLayout(pnlResolucionesLayout);
        pnlResolucionesLayout.setHorizontalGroup(
            pnlResolucionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlResolucionesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlResolucionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlResolucionesLayout.createSequentialGroup()
                        .addComponent(pnlBodegas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlPrefijos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlResolucionesLayout.setVerticalGroup(
            pnlResolucionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlResolucionesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlResolucionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlBodegas, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlPrefijos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        tabPanel.addTab("Resoluciones, prefijos y bodegas", pnlResoluciones);

        pnlImpresiones.setBackground(new java.awt.Color(255, 255, 255));

        pnlImpresionesGrupo.setBackground(new java.awt.Color(255, 255, 255));
        pnlImpresionesGrupo.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        tblImpresoras1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        tblImpresoras1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Grupos", "Impresora 1", "Impresora 2", "Impresora 3", "Impresora 4", "Impresora 5"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblImpresoras1.setRowHeight(27);
        tblImpresoras1.getTableHeader().setReorderingAllowed(false);
        tblImpresoras1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblImpresoras1MouseClicked(evt);
            }
        });
        tblImpresoras1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblImpresoras1KeyReleased(evt);
            }
        });
        jScrollPane4.setViewportView(tblImpresoras1);

        btnGuardar4.setBackground(new java.awt.Color(241, 148, 138));
        btnGuardar4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnGuardar4.setText("BORRAR IMPRESORA");
        btnGuardar4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnGuardar4.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnGuardar4.setMargin(null);
        btnGuardar4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardar4ActionPerformed(evt);
            }
        });

        tblImpresoras.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        tblImpresoras.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblImpresoras.setRowHeight(27);
        tblImpresoras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblImpresorasMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblImpresoras);

        btnActualizarImpresoras.setBackground(new java.awt.Color(46, 204, 113));
        btnActualizarImpresoras.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnActualizarImpresoras.setText("ACTUALIZAR IMPRESORAS");
        btnActualizarImpresoras.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnActualizarImpresoras.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnActualizarImpresoras.setMargin(null);
        btnActualizarImpresoras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarImpresorasActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel9.setText("Impresoras disponibles:");

        javax.swing.GroupLayout pnlImpresionesGrupoLayout = new javax.swing.GroupLayout(pnlImpresionesGrupo);
        pnlImpresionesGrupo.setLayout(pnlImpresionesGrupoLayout);
        pnlImpresionesGrupoLayout.setHorizontalGroup(
            pnlImpresionesGrupoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlImpresionesGrupoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlImpresionesGrupoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlImpresionesGrupoLayout.createSequentialGroup()
                        .addGroup(pnlImpresionesGrupoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane4))
                    .addGroup(pnlImpresionesGrupoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnActualizarImpresoras, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGuardar4)))
                .addContainerGap())
        );
        pnlImpresionesGrupoLayout.setVerticalGroup(
            pnlImpresionesGrupoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlImpresionesGrupoLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(pnlImpresionesGrupoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnActualizarImpresoras, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardar4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(pnlImpresionesGrupoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlImpresionesGrupoLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(3, 3, 3)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
                .addContainerGap())
        );

        tblImpresionDocumentos.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        tblImpresionDocumentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Factura", null, null, null},
                {"Orden de servicio", null, null, null},
                {"Cotización", null, null, null},
                {"Plan separe", null, null, null},
                {"Pedido", null, null, null},
                {"Comanda", null, null, null},
                {"Prefactura", null, null, null}
            },
            new String [] {
                "Tipo Documento", "Previsualizar en pantalla", "Preguntar copias", "# copias"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblImpresionDocumentos.setRowHeight(24);
        jScrollPane16.setViewportView(tblImpresionDocumentos);
        if (tblImpresionDocumentos.getColumnModel().getColumnCount() > 0) {
            tblImpresionDocumentos.getColumnModel().getColumn(0).setMinWidth(140);
            tblImpresionDocumentos.getColumnModel().getColumn(0).setPreferredWidth(140);
            tblImpresionDocumentos.getColumnModel().getColumn(0).setMaxWidth(140);
            tblImpresionDocumentos.getColumnModel().getColumn(2).setMinWidth(120);
            tblImpresionDocumentos.getColumnModel().getColumn(2).setPreferredWidth(120);
            tblImpresionDocumentos.getColumnModel().getColumn(2).setMaxWidth(120);
            tblImpresionDocumentos.getColumnModel().getColumn(3).setMinWidth(70);
            tblImpresionDocumentos.getColumnModel().getColumn(3).setPreferredWidth(70);
            tblImpresionDocumentos.getColumnModel().getColumn(3).setMaxWidth(70);
        }

        tblImpresoraPorTipo.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        tblImpresoraPorTipo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Carta", null, null},
                {"Media carta", null, null},
                {"Pos", null, null},
                {"Comanda", null, null},
                {"Prefactura", null, null}
            },
            new String [] {
                "Tipo Documento", "Impresora por defecto", "Por defecto"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblImpresoraPorTipo.setRowHeight(24);
        jScrollPane17.setViewportView(tblImpresoraPorTipo);
        if (tblImpresoraPorTipo.getColumnModel().getColumnCount() > 0) {
            tblImpresoraPorTipo.getColumnModel().getColumn(0).setMinWidth(120);
            tblImpresoraPorTipo.getColumnModel().getColumn(0).setPreferredWidth(120);
            tblImpresoraPorTipo.getColumnModel().getColumn(0).setMaxWidth(120);
            tblImpresoraPorTipo.getColumnModel().getColumn(2).setMinWidth(70);
            tblImpresoraPorTipo.getColumnModel().getColumn(2).setPreferredWidth(70);
            tblImpresoraPorTipo.getColumnModel().getColumn(2).setMaxWidth(70);
        }

        tblOpcionesImpresiones.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        tblOpcionesImpresiones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Imprimir ubicación en factura", null},
                {"Imprimir hora en factura", null},
                {"Imprimir retenciones en factura", null},
                {"Imprimir impoconsumo en factura", null},
                {"Imprimir recodiga en cuadre de caja", null},
                {"Imprimir cuadre fiscal en cuadre de caja", null},
                {"Impresión por grupo", null}
            },
            new String [] {
                "Opción", "Valor"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblOpcionesImpresiones.setRowHeight(24);
        jScrollPane18.setViewportView(tblOpcionesImpresiones);
        if (tblOpcionesImpresiones.getColumnModel().getColumnCount() > 0) {
            tblOpcionesImpresiones.getColumnModel().getColumn(1).setMinWidth(80);
            tblOpcionesImpresiones.getColumnModel().getColumn(1).setPreferredWidth(80);
            tblOpcionesImpresiones.getColumnModel().getColumn(1).setMaxWidth(80);
        }

        tblInformacionImpresion.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        tblInformacionImpresion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Pie de página", null},
                {"Información legal", null},
                {"Anexo factura", null},
                {"Anexo orden de servicio", null}
            },
            new String [] {
                "Información impresión", "Valor"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblInformacionImpresion.setRowHeight(24);
        jScrollPane20.setViewportView(tblInformacionImpresion);
        if (tblInformacionImpresion.getColumnModel().getColumnCount() > 0) {
            tblInformacionImpresion.getColumnModel().getColumn(0).setMinWidth(180);
            tblInformacionImpresion.getColumnModel().getColumn(0).setPreferredWidth(180);
            tblInformacionImpresion.getColumnModel().getColumn(0).setMaxWidth(180);
        }

        btnActualizarLogo.setBackground(new java.awt.Color(93, 173, 226));
        btnActualizarLogo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnActualizarLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/actualizar.png"))); // NOI18N
        btnActualizarLogo.setText("CAMBIAR LOGO");
        btnActualizarLogo.setToolTipText("Montar logo empresarial");
        btnActualizarLogo.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnActualizarLogo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarLogoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlLogoLayout = new javax.swing.GroupLayout(pnlLogo);
        pnlLogo.setLayout(pnlLogoLayout);
        pnlLogoLayout.setHorizontalGroup(
            pnlLogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlLogoLayout.setVerticalGroup(
            pnlLogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlImpresionesLayout = new javax.swing.GroupLayout(pnlImpresiones);
        pnlImpresiones.setLayout(pnlImpresionesLayout);
        pnlImpresionesLayout.setHorizontalGroup(
            pnlImpresionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlImpresionesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlImpresionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlImpresionesGrupo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlImpresionesLayout.createSequentialGroup()
                        .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlImpresionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(pnlLogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnActualizarLogo, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnlImpresionesLayout.createSequentialGroup()
                        .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 423, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane20, javax.swing.GroupLayout.DEFAULT_SIZE, 571, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlImpresionesLayout.setVerticalGroup(
            pnlImpresionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlImpresionesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlImpresionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                    .addComponent(jScrollPane17, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(pnlImpresionesLayout.createSequentialGroup()
                        .addComponent(pnlLogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(0, 0, 0)
                        .addComponent(btnActualizarLogo)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlImpresionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane18, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                    .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlImpresionesGrupo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabPanel.addTab("Manejo de impresiones", pnlImpresiones);

        pnlRestaurante.setBackground(new java.awt.Color(255, 255, 255));

        cmbDoctores2.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        cmbDoctores2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Escoja un usuario..." }));

        lbCelular4.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbCelular4.setText("Columnas:");

        txtColumnas.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtColumnas.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtColumnas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtColumnasKeyReleased(evt);
            }
        });

        lbCelular8.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbCelular8.setText("Filas:");

        txtFilas.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtFilas.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFilas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFilasKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFilasKeyTyped(evt);
            }
        });

        tblRestaurante.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        tblRestaurante.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblRestaurante.setRowHeight(25);
        tblRestaurante.getTableHeader().setResizingAllowed(false);
        tblRestaurante.getTableHeader().setReorderingAllowed(false);
        tblRestaurante.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblRestauranteMouseClicked(evt);
            }
        });
        jScrollPane11.setViewportView(tblRestaurante);
        if (tblRestaurante.getColumnModel().getColumnCount() > 0) {
            tblRestaurante.getColumnModel().getColumn(2).setHeaderValue("C");
            tblRestaurante.getColumnModel().getColumn(3).setHeaderValue("D");
            tblRestaurante.getColumnModel().getColumn(4).setHeaderValue("E");
            tblRestaurante.getColumnModel().getColumn(5).setHeaderValue("F");
            tblRestaurante.getColumnModel().getColumn(6).setHeaderValue("G");
            tblRestaurante.getColumnModel().getColumn(7).setHeaderValue("H");
            tblRestaurante.getColumnModel().getColumn(8).setHeaderValue("I");
            tblRestaurante.getColumnModel().getColumn(9).setHeaderValue("J");
            tblRestaurante.getColumnModel().getColumn(10).setHeaderValue("K");
            tblRestaurante.getColumnModel().getColumn(11).setHeaderValue("L");
            tblRestaurante.getColumnModel().getColumn(12).setHeaderValue("M");
            tblRestaurante.getColumnModel().getColumn(13).setHeaderValue("N");
            tblRestaurante.getColumnModel().getColumn(14).setHeaderValue("O");
            tblRestaurante.getColumnModel().getColumn(15).setHeaderValue("P");
            tblRestaurante.getColumnModel().getColumn(16).setHeaderValue("Q");
            tblRestaurante.getColumnModel().getColumn(17).setHeaderValue("R");
            tblRestaurante.getColumnModel().getColumn(18).setHeaderValue("S");
            tblRestaurante.getColumnModel().getColumn(19).setHeaderValue("T");
            tblRestaurante.getColumnModel().getColumn(20).setHeaderValue("U");
            tblRestaurante.getColumnModel().getColumn(21).setHeaderValue("V");
            tblRestaurante.getColumnModel().getColumn(22).setHeaderValue("W");
            tblRestaurante.getColumnModel().getColumn(23).setHeaderValue("X");
            tblRestaurante.getColumnModel().getColumn(24).setHeaderValue("Y");
            tblRestaurante.getColumnModel().getColumn(25).setHeaderValue("Z");
        }

        txtNumMesas.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        txtNumMesas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtNumMesas.setText("0");

        lbCelular10.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbCelular10.setText("# MESAS SELECCIONADAS:");

        btnGenerarTabla.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        btnGenerarTabla.setText("GENERAR");
        btnGenerarTabla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarTablaActionPerformed(evt);
            }
        });

        tblMesas.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        tblMesas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mesa", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblMesas.setRowHeight(25);
        tblMesas.getTableHeader().setResizingAllowed(false);
        tblMesas.getTableHeader().setReorderingAllowed(false);
        tblMesas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMesasMouseClicked(evt);
            }
        });
        jScrollPane12.setViewportView(tblMesas);

        btnActualizarMesas.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        btnActualizarMesas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/actualizar pequeño.png"))); // NOI18N
        btnActualizarMesas.setText("MESAS OCUPADAS");
        btnActualizarMesas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarMesasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlRestauranteLayout = new javax.swing.GroupLayout(pnlRestaurante);
        pnlRestaurante.setLayout(pnlRestauranteLayout);
        pnlRestauranteLayout.setHorizontalGroup(
            pnlRestauranteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRestauranteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlRestauranteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlRestauranteLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lbCelular10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNumMesas, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6))
                    .addGroup(pnlRestauranteLayout.createSequentialGroup()
                        .addGroup(pnlRestauranteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane11)
                            .addGroup(pnlRestauranteLayout.createSequentialGroup()
                                .addComponent(lbCelular4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtColumnas, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(21, 21, 21)
                                .addComponent(lbCelular8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtFilas, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnGenerarTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(292, 292, 292)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlRestauranteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnActualizarMesas, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(cmbDoctores2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlRestauranteLayout.setVerticalGroup(
            pnlRestauranteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRestauranteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlRestauranteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlRestauranteLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE))
                    .addGroup(pnlRestauranteLayout.createSequentialGroup()
                        .addGroup(pnlRestauranteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnActualizarMesas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlRestauranteLayout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(lbCelular8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(lbCelular4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtColumnas, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtFilas, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnGenerarTabla, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
                        .addGroup(pnlRestauranteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlRestauranteLayout.createSequentialGroup()
                                .addGap(103, 103, 103)
                                .addComponent(cmbDoctores2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(pnlRestauranteLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlRestauranteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNumMesas, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbCelular10, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        tabPanel.addTab("Restaurante", pnlRestaurante);

        pnlMedico.setBackground(new java.awt.Color(255, 255, 255));

        pnlInformacionMedico2.setBackground(new java.awt.Color(255, 255, 255));
        pnlInformacionMedico2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        lbRazon4.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbRazon4.setText("Concepto:");

        lbRazon.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbRazon.setText("Causa Externa:");

        lbRazon13.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbRazon13.setText("Ambito Procedimiento:");

        cmbConcepto.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        cmbConcepto.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Consultas", "Procedimientos de diagnósticos", "Procedimientos terapéuticos no quirúrgicos", "Procedimientos terapéuticos quirúrgicos", "Procedimientos de promoción y prevención", "Estancias", "Honorarios", "Derechos de sala", "Materiales e insumos", "Banco de sangre", "Prótesis y órtesis", "Medicamentos POS", "Medicamentos no POS", "Traslado de pacientes" }));
        cmbConcepto.setName("Concepto"); // NOI18N
        cmbConcepto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbConceptoKeyReleased(evt);
            }
        });

        cmbAmbito.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        cmbAmbito.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ambulatorio", "Hospitalario", "De urgencia" }));
        cmbAmbito.setName("Ambito Procedimiento"); // NOI18N
        cmbAmbito.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbAmbitoKeyReleased(evt);
            }
        });

        cmbCausa.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        cmbCausa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Enfermedad general", "Accidente de trabajo", "Accidente de tránsito", "Accidente rábico", "Accidente ofídico", "Otro tipo de accidente", "Evento catastrófico", "Lesión por agresión", "Lesión auto infligida", "Sospecha de maltrato físico", "Sospecha de abuso sexual", "Sospecha de violencia sexual", "Sospecha de maltrato emocional", "Enfermedad profesional", "Otra" }));
        cmbCausa.setName("Causa Externa"); // NOI18N
        cmbCausa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbCausaKeyReleased(evt);
            }
        });

        lbRazon25.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbRazon25.setText("Acto quirurgico:");

        cmbActoQuirurgico.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        cmbActoQuirurgico.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Unico o unilateral", "Múltiple o bilateral, misma vía, diferente especialidad", "Múltiple o bilateral, misma vía, igual especialidad", "Múltiple o bilateral, diferente vía, diferente especialidad", "Múltiple o bilateral, diferente vía, igual especialidad" }));

        lbRazon28.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbRazon28.setText("Codigo Consulta:");

        cmbCodigoConsulta.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        cmbCodigoConsulta.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Consulta de Medicina General electiva", "Consulta de Medicina Especializada electiva", "Consulta de Medicina General de control", "Consulta de Medicina Especializada de control" }));
        cmbCodigoConsulta.setName("Forma"); // NOI18N
        cmbCodigoConsulta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbCodigoConsultaKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout pnlInformacionMedico2Layout = new javax.swing.GroupLayout(pnlInformacionMedico2);
        pnlInformacionMedico2.setLayout(pnlInformacionMedico2Layout);
        pnlInformacionMedico2Layout.setHorizontalGroup(
            pnlInformacionMedico2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInformacionMedico2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlInformacionMedico2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lbRazon28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbRazon4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbRazon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbRazon25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbRazon13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlInformacionMedico2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbActoQuirurgico, javax.swing.GroupLayout.Alignment.TRAILING, 0, 0, Short.MAX_VALUE)
                    .addComponent(cmbCausa, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbConcepto, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbAmbito, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbCodigoConsulta, 0, 316, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlInformacionMedico2Layout.setVerticalGroup(
            pnlInformacionMedico2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInformacionMedico2Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(pnlInformacionMedico2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbRazon4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbConcepto, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(pnlInformacionMedico2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbRazon, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbCausa, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(pnlInformacionMedico2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbRazon25, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbActoQuirurgico, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(pnlInformacionMedico2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbRazon13, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbAmbito, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlInformacionMedico2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlInformacionMedico2Layout.createSequentialGroup()
                        .addComponent(lbRazon28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(95, 95, 95))
                    .addGroup(pnlInformacionMedico2Layout.createSequentialGroup()
                        .addComponent(cmbCodigoConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pnlOrdenMedica.setBackground(new java.awt.Color(255, 255, 255));
        pnlOrdenMedica.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ordenes de Servicio", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 0, 12))); // NOI18N

        jLabel7.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel7.setText("Cod Prestador Servicio:");

        txtCodigoPrestadorServico.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoPrestadorServicoKeyTyped(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel8.setText("Tipo Prestador de Servicio:");

        lbTelefono20.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lbTelefono20.setText("Preguntar Imprimir Orden:");

        lbTelefono21.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lbTelefono21.setText("Preguntar Imprimir Factura:");

        jLabel6.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel6.setText("¿Generar Ordenes?");

        cmbTipoPrestador.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "CC", "NI", "CE", "PA" }));
        cmbTipoPrestador.setName("Intervalo"); // NOI18N

        rdSiImprimirOrden.setBackground(new java.awt.Color(255, 255, 255));
        rdSiImprimirOrden.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rdSiImprimirOrden.setSelected(true);
        rdSiImprimirOrden.setText("Si");

        rdNoImprimirOrden.setBackground(new java.awt.Color(255, 255, 255));
        rdNoImprimirOrden.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rdNoImprimirOrden.setText("No");
        rdNoImprimirOrden.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdNoImprimirOrdenActionPerformed(evt);
            }
        });

        rdSiImprimirFactura.setBackground(new java.awt.Color(255, 255, 255));
        rdSiImprimirFactura.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rdSiImprimirFactura.setSelected(true);
        rdSiImprimirFactura.setText("Si");

        rdNoImprimirFactura.setBackground(new java.awt.Color(255, 255, 255));
        rdNoImprimirFactura.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rdNoImprimirFactura.setText("No");

        rdSiGenerarOrden.setBackground(new java.awt.Color(255, 255, 255));
        rdSiGenerarOrden.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rdSiGenerarOrden.setText("SI");

        rdNoGenerarOrden.setBackground(new java.awt.Color(255, 255, 255));
        rdNoGenerarOrden.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rdNoGenerarOrden.setText("NO");
        rdNoGenerarOrden.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdNoGenerarOrdenItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout pnlOrdenMedicaLayout = new javax.swing.GroupLayout(pnlOrdenMedica);
        pnlOrdenMedica.setLayout(pnlOrdenMedicaLayout);
        pnlOrdenMedicaLayout.setHorizontalGroup(
            pnlOrdenMedicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOrdenMedicaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlOrdenMedicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbTelefono20, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbTelefono21, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlOrdenMedicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCodigoPrestadorServico, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlOrdenMedicaLayout.createSequentialGroup()
                        .addComponent(rdSiGenerarOrden)
                        .addGap(2, 2, 2)
                        .addComponent(rdNoGenerarOrden))
                    .addGroup(pnlOrdenMedicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(cmbTipoPrestador, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnlOrdenMedicaLayout.createSequentialGroup()
                            .addComponent(rdSiImprimirOrden)
                            .addGap(5, 5, 5)
                            .addComponent(rdNoImprimirOrden, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(pnlOrdenMedicaLayout.createSequentialGroup()
                            .addComponent(rdSiImprimirFactura)
                            .addGap(5, 5, 5)
                            .addComponent(rdNoImprimirFactura))))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        pnlOrdenMedicaLayout.setVerticalGroup(
            pnlOrdenMedicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOrdenMedicaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlOrdenMedicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCodigoPrestadorServico, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(pnlOrdenMedicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmbTipoPrestador, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(21, 21, 21)
                .addGroup(pnlOrdenMedicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlOrdenMedicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rdSiImprimirOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rdNoImprimirOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbTelefono20, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(pnlOrdenMedicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlOrdenMedicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rdSiImprimirFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rdNoImprimirFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbTelefono21, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(pnlOrdenMedicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlOrdenMedicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rdSiGenerarOrden)
                        .addComponent(rdNoGenerarOrden))
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pnlInformacionMedico1.setBackground(new java.awt.Color(255, 255, 255));
        pnlInformacionMedico1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        lbRazon23.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbRazon23.setText("Tipo:");

        lbRazon5.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbRazon5.setText("Finalidad:");

        lbRazon11.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbRazon11.setText("Descripción Servicio:");

        lbRazon19.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbRazon19.setText("Valor:");

        lbRazon14.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbRazon14.setText("Personal que atiende:");

        lbRazon15.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbRazon15.setText("Forma:");

        rdProcedimiento.setBackground(new java.awt.Color(255, 255, 255));
        rdProcedimiento.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rdProcedimiento.setText("Procedimiento");
        rdProcedimiento.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdProcedimientoItemStateChanged(evt);
            }
        });
        rdProcedimiento.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rdProcedimientoStateChanged(evt);
            }
        });

        rdConsulta.setBackground(new java.awt.Color(255, 255, 255));
        rdConsulta.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rdConsulta.setSelected(true);
        rdConsulta.setText("Consulta");
        rdConsulta.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdConsultaItemStateChanged(evt);
            }
        });

        cmbFinalidad.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        cmbFinalidad.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "No aplica", "Atención del parto (puerperio)", "Atención del recién nacido", "Atención en planificación familiar", "Detección de alteraciones de crecimiento y desarrollo del menor de diez años", "Detección de alteración del desarrollo joven", "Detección de alteraciones del embarazo", "Detección de alteraciones del adulto", "Detección de alteraciones de agudeza visual", "Detección de enfermedad profesional", "Diagnóstico", "Terapéutico", "Protección específica", "Detección temprana de enfermedad general", "Detección temprana de enfermedad profesional" }));
        cmbFinalidad.setName("Finalidad"); // NOI18N
        cmbFinalidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbFinalidadKeyReleased(evt);
            }
        });

        txtaCobrar.setName("Cobro"); // NOI18N
        txtaCobrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtaCobrarActionPerformed(evt);
            }
        });
        txtaCobrar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtaCobrarKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtaCobrarKeyTyped(evt);
            }
        });

        txtValor1.setEditable(false);
        txtValor1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtValor1.setName("Cantidad"); // NOI18N
        txtValor1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtValor1ActionPerformed(evt);
            }
        });
        txtValor1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtValor1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtValor1KeyTyped(evt);
            }
        });

        cmbForma.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        cmbForma.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Consulta externa o programada", "Urgencias", "Remitido", "Nacido en la institución" }));
        cmbForma.setName("Forma"); // NOI18N
        cmbForma.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbFormaKeyReleased(evt);
            }
        });

        cmbPersonal.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        cmbPersonal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Médico (a) general", "Médico (A) especialista", "Enfermera (o)", "Auxiliar de enfermería", "Otro" }));
        cmbPersonal.setName("Personal"); // NOI18N
        cmbPersonal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbPersonalKeyReleased(evt);
            }
        });

        txtaCobrar1.setEditable(false);
        txtaCobrar1.setName("Cobro"); // NOI18N
        txtaCobrar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtaCobrar1ActionPerformed(evt);
            }
        });
        txtaCobrar1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtaCobrar1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtaCobrar1KeyTyped(evt);
            }
        });

        javax.swing.GroupLayout pnlInformacionMedico1Layout = new javax.swing.GroupLayout(pnlInformacionMedico1);
        pnlInformacionMedico1.setLayout(pnlInformacionMedico1Layout);
        pnlInformacionMedico1Layout.setHorizontalGroup(
            pnlInformacionMedico1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInformacionMedico1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlInformacionMedico1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlInformacionMedico1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(lbRazon23, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbRazon5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbRazon11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbRazon19, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(lbRazon15, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbRazon14, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInformacionMedico1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbFinalidad, 0, 0, Short.MAX_VALUE)
                    .addComponent(cmbPersonal, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbForma, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlInformacionMedico1Layout.createSequentialGroup()
                        .addGroup(pnlInformacionMedico1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlInformacionMedico1Layout.createSequentialGroup()
                                .addComponent(rdProcedimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rdConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtValor1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 25, Short.MAX_VALUE))
                    .addGroup(pnlInformacionMedico1Layout.createSequentialGroup()
                        .addComponent(txtaCobrar, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtaCobrar1)))
                .addGap(13, 13, 13))
        );
        pnlInformacionMedico1Layout.setVerticalGroup(
            pnlInformacionMedico1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInformacionMedico1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlInformacionMedico1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbRazon23, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdProcedimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(pnlInformacionMedico1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbRazon5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbFinalidad, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlInformacionMedico1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbRazon11, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlInformacionMedico1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtaCobrar, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtaCobrar1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlInformacionMedico1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbRazon19, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtValor1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlInformacionMedico1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbRazon14, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlInformacionMedico1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbRazon15, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbForma, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlMedicoLayout = new javax.swing.GroupLayout(pnlMedico);
        pnlMedico.setLayout(pnlMedicoLayout);
        pnlMedicoLayout.setHorizontalGroup(
            pnlMedicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMedicoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMedicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlOrdenMedica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlMedicoLayout.createSequentialGroup()
                        .addComponent(pnlInformacionMedico1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlInformacionMedico2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlMedicoLayout.setVerticalGroup(
            pnlMedicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMedicoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlOrdenMedica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMedicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlInformacionMedico2, javax.swing.GroupLayout.PREFERRED_SIZE, 248, Short.MAX_VALUE)
                    .addComponent(pnlInformacionMedico1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabPanel.addTab("Médico", pnlMedico);

        pnlLaboratorio.setBackground(new java.awt.Color(255, 255, 255));

        tblExamenes.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        tblExamenes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, "ACIDO URICO (AU)", null, "EX1"},
                {null, "ANTIESTREPTOLISINA (ASO)", null, "EX2"},
                {null, "CANNABINOIDES (MARIHUANA)", null, "EX3"},
                {null, "DETERMINACION DE COCAINA", null, "EX4"},
                {null, "COPROLOGICO", null, "EX5"},
                {null, "COPROGRAMA", null, "EX6"},
                {null, "COLESTEROL TOTAL", null, "EX7"},
                {null, "TRIGLICERIDOS", null, "EX8"},
                {null, "CITOQUIMICO DE ORINA", null, "EX9"},
                {null, "CREATININA", null, "EX10"},
                {null, "UREA", null, "EX11"},
                {null, "BUN", null, "EX12"},
                {null, "GLICEMIA EN AYUNAS", null, "EX13"},
                {null, "HEMOGLOBINA GLICOSILADA A1c (HbA1c)", null, "EX14"},
                {null, "HEMOCLASIFICACIÓN", null, "EX15"},
                {null, "FACTOR REUMATOIDE (RF)", null, "EX16"},
                {null, "PROTEINA C REACTIVA (PCR)", null, "EX17"},
                {null, "HEMOGRAMA COMPLETO TIPO IV METODO SEMIAUTOMATICO", null, "EX18"},
                {null, "SEROLOGIA (V.D.R.L. - USR)", null, "EX19"},
                {null, "TIEMPO DE PROTROMBINA (TP)", null, "EX20"},
                {null, "TIEMPO PARCIAL DE TROMBOPLASTINA (TPT)", null, "EX21"},
                {null, "SANGRE OCULTA EN MATERIA FECAL (HEMOGLOBINA HUMANA ESPECIFICA)", null, "EX22"},
                {null, "KOH", null, "EX23"},
                {null, "PERFIL LIPIDICO", null, "EX24"},
                {null, "PRUEBA INMUNOLOGICA DE EMBARAZO", null, "EX25"},
                {null, "DIRECTO", null, "EX26"},
                {null, "GRAM", null, "EX27"}
            },
            new String [] {
                "CUPS", "Descripción", "Valor", "CodProd"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblExamenes.setRowHeight(26);
        tblExamenes.getTableHeader().setReorderingAllowed(false);
        tblExamenes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblExamenesKeyReleased(evt);
            }
        });
        jScrollPane6.setViewportView(tblExamenes);
        if (tblExamenes.getColumnModel().getColumnCount() > 0) {
            tblExamenes.getColumnModel().getColumn(0).setMinWidth(150);
            tblExamenes.getColumnModel().getColumn(0).setPreferredWidth(150);
            tblExamenes.getColumnModel().getColumn(0).setMaxWidth(150);
            tblExamenes.getColumnModel().getColumn(2).setMinWidth(100);
            tblExamenes.getColumnModel().getColumn(2).setPreferredWidth(100);
            tblExamenes.getColumnModel().getColumn(2).setMaxWidth(100);
            tblExamenes.getColumnModel().getColumn(3).setMinWidth(0);
            tblExamenes.getColumnModel().getColumn(3).setPreferredWidth(0);
            tblExamenes.getColumnModel().getColumn(3).setMaxWidth(0);
        }

        lbTelefono38.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        lbTelefono38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTelefono38.setText("EXAMENES");

        javax.swing.GroupLayout pnlLaboratorioLayout = new javax.swing.GroupLayout(pnlLaboratorio);
        pnlLaboratorio.setLayout(pnlLaboratorioLayout);
        pnlLaboratorioLayout.setHorizontalGroup(
            pnlLaboratorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLaboratorioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlLaboratorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 915, Short.MAX_VALUE)
                    .addComponent(lbTelefono38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlLaboratorioLayout.setVerticalGroup(
            pnlLaboratorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLaboratorioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbTelefono38, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabPanel.addTab("Laboratorio", pnlLaboratorio);

        pnlAgenda.setBackground(new java.awt.Color(255, 255, 255));

        pnlIntervalosAgenda.setBackground(new java.awt.Color(255, 255, 255));
        pnlIntervalosAgenda.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Intervalos de la agenda", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 14))); // NOI18N

        cmbIntervalo.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        cmbIntervalo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "10", "20", "30", "40", "50", "60" }));
        cmbIntervalo.setName("Intervalo"); // NOI18N

        jLabel4.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel4.setText("Tiempo de Intervalo:");

        jLabel3.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel3.setText("Hora Fin Agenda:");

        cmbHoraFin.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        cmbHoraFin.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbHoraFin.setName("Hora Fin"); // NOI18N

        cmbHoraInicio.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        cmbHoraInicio.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbHoraInicio.setName("Hora Inicio"); // NOI18N

        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel2.setText("Hora Inicio Agenda:");

        javax.swing.GroupLayout pnlIntervalosAgendaLayout = new javax.swing.GroupLayout(pnlIntervalosAgenda);
        pnlIntervalosAgenda.setLayout(pnlIntervalosAgendaLayout);
        pnlIntervalosAgendaLayout.setHorizontalGroup(
            pnlIntervalosAgendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlIntervalosAgendaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlIntervalosAgendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlIntervalosAgendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmbHoraInicio, 0, 76, Short.MAX_VALUE)
                    .addComponent(cmbIntervalo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbHoraFin, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlIntervalosAgendaLayout.setVerticalGroup(
            pnlIntervalosAgendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlIntervalosAgendaLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(pnlIntervalosAgendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cmbHoraInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlIntervalosAgendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cmbIntervalo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlIntervalosAgendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cmbHoraFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        cmbDoctores1.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        cmbDoctores1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Escoja un usuario..." }));

        jPanel28.setBackground(new java.awt.Color(255, 255, 255));

        dtFecha.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        dtFecha.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        dtFecha.setWeekOfYearVisible(false);
        dtFecha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dtFechaMouseClicked(evt);
            }
        });
        dtFecha.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dtFechaPropertyChange(evt);
            }
        });

        tblHoras.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        tblHoras.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Hora", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoras.setComponentPopupMenu(jPopupMenu2);
        tblHoras.setRowHeight(24);
        tblHoras.getTableHeader().setReorderingAllowed(false);
        tblHoras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHorasMouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(tblHoras);
        if (tblHoras.getColumnModel().getColumnCount() > 0) {
            tblHoras.getColumnModel().getColumn(0).setMinWidth(50);
            tblHoras.getColumnModel().getColumn(0).setPreferredWidth(70);
            tblHoras.getColumnModel().getColumn(0).setMaxWidth(100);
        }

        jButton3.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jButton3.setText("AGREGAR");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        cmbDoctores.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        cmbDoctores.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Escoja un usuario..." }));

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(dtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addComponent(cmbDoctores, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(dtFecha, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbDoctores, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );

        jPanel29.setBackground(new java.awt.Color(255, 255, 255));

        tblDiasBloqueo.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        tblDiasBloqueo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Día Bloqueado", "Horas", "Usuario bloqueado", "Nota", "id"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDiasBloqueo.setComponentPopupMenu(jPopupMenu2);
        tblDiasBloqueo.setRowHeight(24);
        tblDiasBloqueo.getTableHeader().setReorderingAllowed(false);
        tblDiasBloqueo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDiasBloqueoMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tblDiasBloqueo);
        if (tblDiasBloqueo.getColumnModel().getColumnCount() > 0) {
            tblDiasBloqueo.getColumnModel().getColumn(0).setMinWidth(90);
            tblDiasBloqueo.getColumnModel().getColumn(0).setPreferredWidth(90);
            tblDiasBloqueo.getColumnModel().getColumn(0).setMaxWidth(90);
            tblDiasBloqueo.getColumnModel().getColumn(1).setMinWidth(25);
            tblDiasBloqueo.getColumnModel().getColumn(1).setPreferredWidth(120);
            tblDiasBloqueo.getColumnModel().getColumn(1).setMaxWidth(150);
            tblDiasBloqueo.getColumnModel().getColumn(2).setMinWidth(100);
            tblDiasBloqueo.getColumnModel().getColumn(2).setPreferredWidth(250);
            tblDiasBloqueo.getColumnModel().getColumn(2).setMaxWidth(300);
            tblDiasBloqueo.getColumnModel().getColumn(4).setMinWidth(0);
            tblDiasBloqueo.getColumnModel().getColumn(4).setPreferredWidth(0);
            tblDiasBloqueo.getColumnModel().getColumn(4).setMaxWidth(0);
        }

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane8)
                .addContainerGap())
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlAgendaLayout = new javax.swing.GroupLayout(pnlAgenda);
        pnlAgenda.setLayout(pnlAgendaLayout);
        pnlAgendaLayout.setHorizontalGroup(
            pnlAgendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAgendaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlAgendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlAgendaLayout.createSequentialGroup()
                        .addGroup(pnlAgendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlAgendaLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(cmbDoctores1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(pnlAgendaLayout.createSequentialGroup()
                                .addComponent(pnlIntervalosAgenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 226, Short.MAX_VALUE)))
                        .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlAgendaLayout.setVerticalGroup(
            pnlAgendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAgendaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlAgendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlIntervalosAgenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addComponent(cmbDoctores1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabPanel.addTab("Agenda", pnlAgenda);

        pnlVeterinario.setBackground(new java.awt.Color(255, 255, 255));

        pnlAgenda1.setBackground(new java.awt.Color(255, 255, 255));
        pnlAgenda1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Intervalos de aplicación medicamentos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 0, 14))); // NOI18N

        cmbIntervalo1.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        cmbIntervalo1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "10", "20", "30", "40", "50", "60" }));
        cmbIntervalo1.setName("Intervalo"); // NOI18N

        jLabel10.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel10.setText("Tiempo de Intervalo:");

        jLabel11.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel11.setText("Hora Fin Agenda:");

        cmbHoraFin1.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        cmbHoraFin1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25" }));
        cmbHoraFin1.setName("Hora Fin"); // NOI18N

        cmbHoraInicio1.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        cmbHoraInicio1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbHoraInicio1.setName("Hora Inicio"); // NOI18N

        jLabel12.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel12.setText("Hora Inicio Agenda:");

        javax.swing.GroupLayout pnlAgenda1Layout = new javax.swing.GroupLayout(pnlAgenda1);
        pnlAgenda1.setLayout(pnlAgenda1Layout);
        pnlAgenda1Layout.setHorizontalGroup(
            pnlAgenda1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAgenda1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlAgenda1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(pnlAgenda1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmbHoraInicio1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbIntervalo1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbHoraFin1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        pnlAgenda1Layout.setVerticalGroup(
            pnlAgenda1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAgenda1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(pnlAgenda1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(cmbHoraInicio1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlAgenda1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cmbIntervalo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlAgenda1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(cmbHoraFin1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        cmbDoctores3.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        cmbDoctores3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Escoja un usuario..." }));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Titulo de los formatos", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 0, 14))); // NOI18N

        txtNombreFormato.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtNombreFormato.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNombreFormato.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreFormatoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreFormatoKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtNombreFormato, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(txtNombreFormato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hospitalización", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 0, 12))); // NOI18N

        rdRedondearCantidades.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rdRedondearCantidades.setText("Redondear cantidades");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rdRedondearCantidades, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rdRedondearCantidades)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlVeterinarioLayout = new javax.swing.GroupLayout(pnlVeterinario);
        pnlVeterinario.setLayout(pnlVeterinarioLayout);
        pnlVeterinarioLayout.setHorizontalGroup(
            pnlVeterinarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlVeterinarioLayout.createSequentialGroup()
                .addGroup(pnlVeterinarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlVeterinarioLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pnlAgenda1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlVeterinarioLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmbDoctores3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(232, 232, 232)))
                .addGroup(pnlVeterinarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlVeterinarioLayout.setVerticalGroup(
            pnlVeterinarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlVeterinarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlVeterinarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlVeterinarioLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(pnlAgenda1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(103, 103, 103)
                .addComponent(cmbDoctores3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(202, Short.MAX_VALUE))
        );

        tabPanel.addTab("Veterinario", pnlVeterinario);

        pnlParqueadero.setBackground(new java.awt.Color(255, 255, 255));

        jPanel47.setBackground(new java.awt.Color(255, 255, 255));
        jPanel47.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        tblCascos.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        tblCascos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCascos.setRowHeight(25);
        tblCascos.getTableHeader().setResizingAllowed(false);
        tblCascos.getTableHeader().setReorderingAllowed(false);
        jScrollPane13.setViewportView(tblCascos);

        jPanel48.setBackground(new java.awt.Color(255, 255, 255));

        lbCelular5.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbCelular5.setText("Columnas:");

        lbNit14.setFont(new java.awt.Font("Century Gothic", 1, 22)); // NOI18N
        lbNit14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit14.setText("CASCOS");

        txtColumnas1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtColumnas1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtColumnas1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtColumnas1KeyReleased(evt);
            }
        });

        lbCelular9.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbCelular9.setText("Filas:");

        txtFilas1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtFilas1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFilas1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFilas1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFilas1KeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel48Layout = new javax.swing.GroupLayout(jPanel48);
        jPanel48.setLayout(jPanel48Layout);
        jPanel48Layout.setHorizontalGroup(
            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel48Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbNit14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel48Layout.createSequentialGroup()
                .addComponent(lbCelular5)
                .addGap(3, 3, 3)
                .addComponent(txtColumnas1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(lbCelular9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFilas1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(135, 135, 135))
        );
        jPanel48Layout.setVerticalGroup(
            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel48Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbNit14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbCelular5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbCelular9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtColumnas1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtFilas1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(5, 5, 5))
        );

        btnGuardar6.setBackground(new java.awt.Color(46, 204, 113));
        btnGuardar6.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnGuardar6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
        btnGuardar6.setText("GUARDAR");
        btnGuardar6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnGuardar6.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnGuardar6.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnGuardar6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardar6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel47Layout = new javax.swing.GroupLayout(jPanel47);
        jPanel47.setLayout(jPanel47Layout);
        jPanel47Layout.setHorizontalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel47Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel47Layout.createSequentialGroup()
                        .addComponent(jPanel48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(1, 1, 1))
                    .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel47Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnGuardar6, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel47Layout.setVerticalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel47Layout.createSequentialGroup()
                .addComponent(jPanel48, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardar6)
                .addContainerGap())
        );

        jPanel49.setBackground(new java.awt.Color(255, 255, 255));
        jPanel49.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        tblConfig.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        tblConfig.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Servicio", "Valor Hora", "Min 1 Hora", "Horas acum", "Valor acumulado", "Horas día", "Valor día", "No. Horas", "Día completo", "No. Horas", "Mensualidad", "Entrada", "Salida", "Cupo parq", "Hrs. Dia Adiccion"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, true, true, true, true, true, true, true, true, true, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblConfig.setComponentPopupMenu(jPopupMenu3);
        tblConfig.setRowHeight(20);
        tblConfig.getTableHeader().setReorderingAllowed(false);
        tblConfig.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblConfigMouseClicked(evt);
            }
        });
        tblConfig.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblConfigKeyReleased(evt);
            }
        });
        jScrollPane14.setViewportView(tblConfig);
        if (tblConfig.getColumnModel().getColumnCount() > 0) {
            tblConfig.getColumnModel().getColumn(0).setMinWidth(0);
            tblConfig.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblConfig.getColumnModel().getColumn(0).setMaxWidth(0);
            tblConfig.getColumnModel().getColumn(2).setMinWidth(110);
            tblConfig.getColumnModel().getColumn(2).setPreferredWidth(110);
            tblConfig.getColumnModel().getColumn(2).setMaxWidth(110);
            tblConfig.getColumnModel().getColumn(3).setMinWidth(70);
            tblConfig.getColumnModel().getColumn(3).setPreferredWidth(70);
            tblConfig.getColumnModel().getColumn(3).setMaxWidth(70);
            tblConfig.getColumnModel().getColumn(4).setMinWidth(80);
            tblConfig.getColumnModel().getColumn(4).setPreferredWidth(80);
            tblConfig.getColumnModel().getColumn(4).setMaxWidth(80);
            tblConfig.getColumnModel().getColumn(5).setMinWidth(110);
            tblConfig.getColumnModel().getColumn(5).setPreferredWidth(110);
            tblConfig.getColumnModel().getColumn(5).setMaxWidth(110);
            tblConfig.getColumnModel().getColumn(6).setMinWidth(65);
            tblConfig.getColumnModel().getColumn(6).setPreferredWidth(65);
            tblConfig.getColumnModel().getColumn(6).setMaxWidth(65);
            tblConfig.getColumnModel().getColumn(7).setMinWidth(110);
            tblConfig.getColumnModel().getColumn(7).setPreferredWidth(110);
            tblConfig.getColumnModel().getColumn(7).setMaxWidth(110);
            tblConfig.getColumnModel().getColumn(8).setMinWidth(0);
            tblConfig.getColumnModel().getColumn(8).setPreferredWidth(0);
            tblConfig.getColumnModel().getColumn(8).setMaxWidth(0);
            tblConfig.getColumnModel().getColumn(9).setMinWidth(0);
            tblConfig.getColumnModel().getColumn(9).setPreferredWidth(0);
            tblConfig.getColumnModel().getColumn(9).setMaxWidth(0);
            tblConfig.getColumnModel().getColumn(10).setMinWidth(0);
            tblConfig.getColumnModel().getColumn(10).setPreferredWidth(0);
            tblConfig.getColumnModel().getColumn(10).setMaxWidth(0);
            tblConfig.getColumnModel().getColumn(11).setMinWidth(100);
            tblConfig.getColumnModel().getColumn(11).setPreferredWidth(100);
            tblConfig.getColumnModel().getColumn(11).setMaxWidth(100);
            tblConfig.getColumnModel().getColumn(12).setMinWidth(0);
            tblConfig.getColumnModel().getColumn(12).setPreferredWidth(0);
            tblConfig.getColumnModel().getColumn(12).setMaxWidth(0);
            tblConfig.getColumnModel().getColumn(13).setMinWidth(0);
            tblConfig.getColumnModel().getColumn(13).setPreferredWidth(0);
            tblConfig.getColumnModel().getColumn(13).setMaxWidth(0);
            tblConfig.getColumnModel().getColumn(14).setMinWidth(80);
            tblConfig.getColumnModel().getColumn(14).setPreferredWidth(80);
            tblConfig.getColumnModel().getColumn(14).setMaxWidth(80);
            tblConfig.getColumnModel().getColumn(15).setMinWidth(0);
            tblConfig.getColumnModel().getColumn(15).setPreferredWidth(0);
            tblConfig.getColumnModel().getColumn(15).setMaxWidth(0);
        }

        txtCodProducto.setName("combo"); // NOI18N
        txtCodProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodProductoKeyReleased(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel15.setText("CARGAR NUEVO SERVICIO:");

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

        javax.swing.GroupLayout jPanel49Layout = new javax.swing.GroupLayout(jPanel49);
        jPanel49.setLayout(jPanel49Layout);
        jPanel49Layout.setHorizontalGroup(
            jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel49Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane14)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel49Layout.createSequentialGroup()
                        .addGap(0, 404, Short.MAX_VALUE)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCodProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBusProd, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel49Layout.setVerticalGroup(
            jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel49Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jScrollPane14, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                .addGap(5, 5, 5)
                .addGroup(jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCodProducto, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                    .addComponent(btnBusProd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(13, 13, 13))
        );

        jPanel50.setBackground(new java.awt.Color(255, 255, 255));
        jPanel50.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jPanel51.setBackground(new java.awt.Color(255, 255, 255));

        lbCelular11.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbCelular11.setText("Generar egreso automático:");

        grupoGenerarEgreso.add(rbtNo);
        rbtNo.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        rbtNo.setSelected(true);
        rbtNo.setText("No");

        grupoGenerarEgreso.add(rbtSi);
        rbtSi.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        rbtSi.setText("Si");

        lbCelular13.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbCelular13.setText("Permitir ingreso sin cupo:");

        grupoIngresoSinCupo.add(rbtNo1);
        rbtNo1.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        rbtNo1.setSelected(true);
        rbtNo1.setText("No");

        grupoIngresoSinCupo.add(rbtSi1);
        rbtSi1.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        rbtSi1.setText("Si");

        lbCelular14.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbCelular14.setText("Facturar a días de plazo:");

        lbCelular15.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbCelular15.setText("Cobrar por casco:");

        grupoFacturarDiasPlazo.add(rbtNo2);
        rbtNo2.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        rbtNo2.setSelected(true);
        rbtNo2.setText("No");

        grupoFacturarDiasPlazo.add(rbtSi2);
        rbtSi2.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        rbtSi2.setText("Si");
        rbtSi2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtSi2ActionPerformed(evt);
            }
        });

        grupoCobrarCasco.add(rbtNo3);
        rbtNo3.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        rbtNo3.setSelected(true);
        rbtNo3.setText("No");
        rbtNo3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rbtNo3ItemStateChanged(evt);
            }
        });

        grupoCobrarCasco.add(rbtSi3);
        rbtSi3.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        rbtSi3.setText("Si");
        rbtSi3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rbtSi3ItemStateChanged(evt);
            }
        });

        txtCosto.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtCosto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCosto.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtCosto.setEnabled(false);
        txtCosto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCostoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCostoKeyTyped(evt);
            }
        });

        lbCelular16.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbCelular16.setText("Modificar valor final:");

        grupoModificarValorFinal.add(rbtNoModificarValor);
        rbtNoModificarValor.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        rbtNoModificarValor.setSelected(true);
        rbtNoModificarValor.setText("No");

        grupoModificarValorFinal.add(rbtSiModificarValor);
        rbtSiModificarValor.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        rbtSiModificarValor.setText("Si");

        javax.swing.GroupLayout jPanel51Layout = new javax.swing.GroupLayout(jPanel51);
        jPanel51.setLayout(jPanel51Layout);
        jPanel51Layout.setHorizontalGroup(
            jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel51Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbCelular15, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel51Layout.createSequentialGroup()
                        .addGroup(jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbCelular14, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbCelular13, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbCelular11, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbCelular16, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4)
                        .addGroup(jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel51Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel51Layout.createSequentialGroup()
                                        .addComponent(rbtNo1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(rbtSi1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel51Layout.createSequentialGroup()
                                        .addComponent(rbtNo)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(rbtSi, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel51Layout.createSequentialGroup()
                                        .addComponent(rbtNo2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(rbtSi2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel51Layout.createSequentialGroup()
                                .addGroup(jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtCosto, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel51Layout.createSequentialGroup()
                                        .addComponent(rbtNoModificarValor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(rbtSiModificarValor, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel51Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(rbtNo3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(rbtSi3)))
                                .addGap(2, 2, 2)))))
                .addContainerGap())
        );
        jPanel51Layout.setVerticalGroup(
            jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel51Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtNo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbCelular11)
                    .addComponent(rbtSi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbCelular13)
                    .addGroup(jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(rbtSi1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rbtNo1, javax.swing.GroupLayout.Alignment.LEADING)))
                .addGap(3, 3, 3)
                .addGroup(jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbCelular14)
                    .addGroup(jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(rbtSi2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rbtNo2, javax.swing.GroupLayout.Alignment.LEADING)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCelular16)
                    .addComponent(rbtNoModificarValor)
                    .addComponent(rbtSiModificarValor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCelular15)
                    .addComponent(rbtNo3)
                    .addComponent(rbtSi3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCosto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        jPanel52.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel52Layout = new javax.swing.GroupLayout(jPanel52);
        jPanel52.setLayout(jPanel52Layout);
        jPanel52Layout.setHorizontalGroup(
            jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 245, Short.MAX_VALUE)
        );
        jPanel52Layout.setVerticalGroup(
            jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 158, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel50Layout = new javax.swing.GroupLayout(jPanel50);
        jPanel50.setLayout(jPanel50Layout);
        jPanel50Layout.setHorizontalGroup(
            jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel50Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel50Layout.createSequentialGroup()
                        .addComponent(jPanel52, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel50Layout.createSequentialGroup()
                        .addGap(0, 10, Short.MAX_VALUE)
                        .addComponent(jPanel51, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10))))
        );
        jPanel50Layout.setVerticalGroup(
            jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel50Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel51, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(638, 638, 638)
                .addComponent(jPanel52, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlParqueaderoLayout = new javax.swing.GroupLayout(pnlParqueadero);
        pnlParqueadero.setLayout(pnlParqueaderoLayout);
        pnlParqueaderoLayout.setHorizontalGroup(
            pnlParqueaderoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlParqueaderoLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(pnlParqueaderoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlParqueaderoLayout.createSequentialGroup()
                        .addComponent(jPanel50, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlParqueaderoLayout.setVerticalGroup(
            pnlParqueaderoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlParqueaderoLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jPanel49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlParqueaderoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel50, javax.swing.GroupLayout.PREFERRED_SIZE, 686, Short.MAX_VALUE)
                    .addComponent(jPanel47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        tabPanel.addTab("Parqueadero", pnlParqueadero);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        btnGuardarMaestra.setBackground(new java.awt.Color(46, 204, 113));
        btnGuardarMaestra.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnGuardarMaestra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
        btnGuardarMaestra.setText("GUARDAR DATOS MAESTRA");
        btnGuardarMaestra.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnGuardarMaestra.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnGuardarMaestra.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnGuardarMaestra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarMaestraActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnGuardarMaestra, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(btnGuardarMaestra)
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout pnlFormularioLayout = new javax.swing.GroupLayout(pnlFormulario);
        pnlFormulario.setLayout(pnlFormularioLayout);
        pnlFormularioLayout.setHorizontalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tabPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 940, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10))
        );
        pnlFormularioLayout.setVerticalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 497, Short.MAX_VALUE)
                .addContainerGap())
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
                .addGap(0, 0, 0)
                .addComponent(scrFormulario)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void btnGuardarMaestraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarMaestraActionPerformed
        if (ControladorAlertas.option("¿Desea continuar?")) {
            guardarMaestra();
        }
    }//GEN-LAST:event_btnGuardarMaestraActionPerformed

    private void guardarMaestra() {
        if (!validacionesIniciales()) {
            return;
        }

        // Saca el foco de las tablas para que se confirme la celda que se esté editando
        tblInformacionEmpresa.requestFocus();

        ModeloMaestraGeneral general = construirModeloGeneral();
        if (general == null) {
            ControladorAlertas.alertFail("No se pudo cargar la configuración actual");
            return;
        }

        ModeloMaestraImpresiones impresiones = construirModeloImpresiones();

        if (!daoMaestra.guardar(general, impresiones)) {
            ControladorAlertas.alertFail("Hubo un problema al guardar los cambios");
            return;
        }

        instancias.getSql().eliminarTodo("bdImpresoras");
        guardarBloqueosAgenda();
        guardarImpresoras();
        guardarMesas();

        if (!guardarOpcionesMedico()) {
            return;
        }

        if (!guardarOpcionesParqueadero()) {
            return;
        }

        guardarExamenesLaboratorio();
        guardarImpresorasPorGrupo();

        if (!guardarCambiosResoluciones() || !guardarCambiosBodegas() || !guardarPrefijos()) {
            return;
        }
        cargarTablaResoluciones();
        cargarBodegasInternas();

        ControladorAlertas.alertSuccess("Cambios guardados con éxito");
        tabPanel.setSelectedComponent(pnlDatosEmpresa);
        llenarDatos();
        actualizarMaestrosEnModulos();
    }

    private ModeloMaestraImpresiones construirModeloImpresiones() {
        ModeloMaestraImpresiones impresiones = new ModeloMaestraImpresiones();

        impresiones.setPrevisualizarFactura(getCeldaBooleanaPorEtiqueta(tblImpresionDocumentos, "Factura", 1));
        impresiones.setPrevisualizarOServicio(getCeldaBooleanaPorEtiqueta(tblImpresionDocumentos, "Orden de servicio", 1));
        impresiones.setPrevisualizarCotizacion(getCeldaBooleanaPorEtiqueta(tblImpresionDocumentos, "Cotización", 1));
        impresiones.setPrevisualizarPlanSepare(getCeldaBooleanaPorEtiqueta(tblImpresionDocumentos, "Plan separe", 1));
        impresiones.setPrevisualizarPedido(getCeldaBooleanaPorEtiqueta(tblImpresionDocumentos, "Pedido", 1));
        impresiones.setPrevisualizarComanda(getCeldaBooleanaPorEtiqueta(tblImpresionDocumentos, "Comanda", 1));
        impresiones.setPrevisualizarPrefactura(getCeldaBooleanaPorEtiqueta(tblImpresionDocumentos, "Prefactura", 1));

        impresiones.setPreguntarCopiasFactura(getCeldaBooleanaPorEtiqueta(tblImpresionDocumentos, "Factura", 2));
        impresiones.setPreguntarCopiasOServicio(getCeldaBooleanaPorEtiqueta(tblImpresionDocumentos, "Orden de servicio", 2));
        impresiones.setPreguntarCopiasCotizacion(getCeldaBooleanaPorEtiqueta(tblImpresionDocumentos, "Cotización", 2));
        impresiones.setPreguntarCopiasPlanSepare(getCeldaBooleanaPorEtiqueta(tblImpresionDocumentos, "Plan separe", 2));
        impresiones.setPreguntarCopiasPedido(getCeldaBooleanaPorEtiqueta(tblImpresionDocumentos, "Pedido", 2));
        impresiones.setPreguntarCopiasComanda(getCeldaBooleanaPorEtiqueta(tblImpresionDocumentos, "Comanda", 2));
        impresiones.setPreguntarCopiasPrefactura(getCeldaBooleanaPorEtiqueta(tblImpresionDocumentos, "Prefactura", 2));

        impresiones.setNumeroCopiasFactura(getCeldaTextoPorEtiqueta(tblImpresionDocumentos, "Factura", 3));
        impresiones.setNumeroCopiasOrdenServicio(getCeldaTextoPorEtiqueta(tblImpresionDocumentos, "Orden de servicio", 3));
        impresiones.setNumeroCopiasCotizacion(getCeldaTextoPorEtiqueta(tblImpresionDocumentos, "Cotización", 3));
        impresiones.setNumeroCopiasPlanSepare(getCeldaTextoPorEtiqueta(tblImpresionDocumentos, "Plan separe", 3));
        impresiones.setNumeroCopiasPedido(getCeldaTextoPorEtiqueta(tblImpresionDocumentos, "Pedido", 3));
        impresiones.setNumeroCopiasComanda(getCeldaTextoPorEtiqueta(tblImpresionDocumentos, "Comanda", 3));
        impresiones.setNumeroCopiasPrefactura(getCeldaTextoPorEtiqueta(tblImpresionDocumentos, "Prefactura", 3));

        impresiones.setImpresoraPos(getCeldaTextoPorEtiqueta(tblImpresoraPorTipo, "Pos", 1).toLowerCase());
        impresiones.setImpresoraMediaCarta(getCeldaTextoPorEtiqueta(tblImpresoraPorTipo, "Media carta", 1));
        impresiones.setImpresoraCarta(getCeldaTextoPorEtiqueta(tblImpresoraPorTipo, "Carta", 1));
        impresiones.setImpresoraComanda(getCeldaTextoPorEtiqueta(tblImpresoraPorTipo, "Comanda", 1));
        impresiones.setImpresoraPrefactura(getCeldaTextoPorEtiqueta(tblImpresoraPorTipo, "Prefactura", 1));
        impresiones.setTipoPredeterminado(obtenerTipoImpresionPredeterminado());

        impresiones.setMostrarUbicacion(getCeldaBooleanaPorEtiqueta(tblOpcionesImpresiones, "Imprimir ubicación en factura", 1) ? "SI" : "NO");
        impresiones.setImprimirHora(getCeldaBooleanaPorEtiqueta(tblOpcionesImpresiones, "Imprimir hora en factura", 1));
        impresiones.setImprimirRetenciones(getCeldaBooleanaPorEtiqueta(tblOpcionesImpresiones, "Imprimir retenciones en factura", 1));
        impresiones.setImprimirImpoconsumo(getCeldaBooleanaPorEtiqueta(tblOpcionesImpresiones, "Imprimir impoconsumo en factura", 1));
        impresiones.setImprimirRecodiga(getCeldaBooleanaPorEtiqueta(tblOpcionesImpresiones, "Imprimir recodiga en cuadre de caja", 1));
        impresiones.setImprimirCuadreFiscal(getCeldaBooleanaPorEtiqueta(tblOpcionesImpresiones, "Imprimir cuadre fiscal en cuadre de caja", 1));
        impresiones.setImpresionPorGrupo(isImpresionPorGrupoActiva());
        impresiones.setFormatoImpresion(txtNombreFormato.getText());

        impresiones.setPiePagina(getCeldaTextoPorEtiqueta(tblInformacionImpresion, "Pie de página", 1));
        impresiones.setInformacionLegal(getCeldaTextoPorEtiqueta(tblInformacionImpresion, "Información legal", 1));
        impresiones.setAnexoFactura(getCeldaTextoPorEtiqueta(tblInformacionImpresion, "Anexo factura", 1));
        impresiones.setAnexoOrdenServicio(getCeldaTextoPorEtiqueta(tblInformacionImpresion, "Anexo orden de servicio", 1));
        return impresiones;
    }

    private String obtenerTipoImpresionPredeterminado() {
        if (getCeldaBooleanaPorEtiqueta(tblImpresoraPorTipo, "Pos", 2)) {
            return "pos";
        }
        if (getCeldaBooleanaPorEtiqueta(tblImpresoraPorTipo, "Carta", 2)) {
            return "facturaCompleta";
        }
        return "factura";
    }

    private String obtenerTituloFactura() {
        String tituloTabla = getCeldaTextoPorEtiqueta(tblConfiguraciones1, "Título en la factura", 1);
        return tituloTabla.equals("") ? "Factura de venta No." : tituloTabla;
    }

    private void guardarBloqueosAgenda() {
        if (!instancias.getConfiguraciones().isAgenda()) {
            return;
        }

        instancias.getSql().eliminarTodo("bdBloqueoAgenda");
        for (int i = 0; i < tblDiasBloqueo.getRowCount(); i++) {
            String horas = textoCeldaOVacio(tblDiasBloqueo, i, 1);

            instancias.getSql().agregarFechaBloqueo(tblDiasBloqueo.getValueAt(i, 0).toString(), tblDiasBloqueo.getValueAt(i, 2).toString(),
                    tblDiasBloqueo.getValueAt(i, 3).toString(), tblDiasBloqueo.getValueAt(i, 4).toString(), horas);
        }
    }

    private void guardarImpresoras() {
        for (int columna = 1; columna < tblImpresoras1.getColumnCount(); columna++) {
            String nombre = textoCeldaOVacio(tblImpresoras1, 0, columna);
            if (!nombre.equals("")) {
                instancias.getSql().agregarImpresora(nombre);
            }
        }
    }

    private void guardarMesas() {
        if (!instancias.getConfiguraciones().isRestaurante()) {
            return;
        }

        instancias.getSql().eliminarTodo("bdMesas");
        for (String[] mesa : panelDisenoMesas.getMesasParaGuardar()) {
            instancias.getSql().agregarRegistroMesa(mesa[0], mesa[1]);
        }
    }

    private boolean guardarOpcionesMedico() {
        if (!instancias.getConfiguraciones().isMedico()) {
            return true;
        }

        boolean esNuevo = instancias.getSql().getDatosCamposOrdenPredeterminada("1").getId() == null;
        String tipo = rdConsulta.isSelected() ? "consulta" : "procedimiento";

        Object[] vectorMed = {"1", tipo, cmbConcepto.getSelectedItem().toString(), cmbFinalidad.getSelectedItem().toString(),
            cmbCausa.getSelectedItem().toString(), idProductoOrdenServicio,
            cmbPersonal.getSelectedItem().toString(), actoQuirurgicoDesc(cmbActoQuirurgico.getSelectedItem().toString()), cmbForma.getSelectedItem().toString(),
            cmbAmbito.getSelectedItem(), cmbCodigoConsulta.getSelectedItem()};

        ndCamposOrdenPredeterminada nodoMed = metodos.llenarCamposOrdenPredeterminada(vectorMed);

        if (esNuevo) {
            if (!instancias.getSql().agregarCamposOrdenPredeterminada(nodoMed)) {
                ControladorAlertas.alertFail("Hubo un problema al guardar la Orden Predeterminada");
                return false;
            }
        } else if (!instancias.getSql().modificarCamposOrdenPredeterminada(nodoMed)) {
            ControladorAlertas.alertFail("Hubo un problema al modificar la Orden Predeterminada");
            return false;
        }
        return true;
    }

    private boolean guardarOpcionesParqueadero() {
        if (!instancias.getConfiguraciones().isParqueadero()) {
            return true;
        }

        BigDecimal cobroCasco = BigDecimal.ZERO;
        if (rbtSi3.isSelected()) {
            cobroCasco = obtenerCobroCasco();
            if (cobroCasco.compareTo(BigDecimal.ZERO) <= 0) {
                ControladorAlertas.alert("El casco debe ser mayor a " + this.simbolo + " 0");
                return false;
            }
        }

        boolean egreso = !rbtNo.isSelected();
        boolean cupo = !rbtNo1.isSelected();
        boolean diasPlazo = !rbtNo2.isSelected();
        boolean cobrarCasco = !rbtNo3.isSelected();
        boolean modificarPrecio = !rbtNoModificarValor.isSelected();

        if (!guardarServiciosParqueadero()) {
            ControladorAlertas.alertFail("Hubo un problema al guardar los cambios");
            return false;
        }

        boolean configuracionGuardada = instancias.getSql().getIdMaestraParqueadero().equals("")
                ? instancias.getSql().insertarMaestraParqueadero(egreso, "", "", "", "", cupo, diasPlazo, cobrarCasco, cobroCasco, modificarPrecio)
                : instancias.getSql().modificarMaestraParqueadero(egreso, "", "", "", "", cupo, diasPlazo, cobrarCasco, cobroCasco, modificarPrecio);

        if (!configuracionGuardada) {
            ControladorAlertas.alertFail("Error al guardar la configuración");
            return false;
        }

        llenarTablaServicios();
        instancias.getParqueadero().actualizarTablas();
        instancias.getMensualidad().moduloDias();
        return true;
    }

    private BigDecimal obtenerCobroCasco() {
        try {
            return big.getMoneda(txtCosto.getText());
        } catch (Exception e) {
            return big.getMoneda(this.simbolo + " 0");
        }
    }

    private boolean guardarServiciosParqueadero() {
        instancias.getSql().eliminarTodo("bdServicios");
        for (int i = 0; i < tblConfig.getRowCount(); i++) {
            if (!instancias.getSql().agregarServicios(tblConfig.getValueAt(i, 0).toString(), tblConfig.getValueAt(i, 1).toString(), big.getMoneda(tblConfig.getValueAt(i, 2).toString()),
                    tblConfig.getValueAt(i, 3).toString(), tblConfig.getValueAt(i, 4).toString(), big.getMoneda(tblConfig.getValueAt(i, 5).toString()), tblConfig.getValueAt(i, 6).toString(),
                    big.getMoneda(tblConfig.getValueAt(i, 7).toString()), tblConfig.getValueAt(i, 8).toString(), big.getMoneda(tblConfig.getValueAt(i, 9).toString()),
                    tblConfig.getValueAt(i, 10).toString(), big.getMoneda(tblConfig.getValueAt(i, 11).toString()), false, false,
                    tblConfig.getValueAt(i, 14).toString(), tblConfig.getValueAt(i, 15).toString())) {
                return false;
            }
        }
        return true;
    }

    private void guardarExamenesLaboratorio() {
        if (!instancias.getConfiguraciones().isLaboratorio()) {
            return;
        }

        instancias.getSql().eliminarTodo("bdExamenesLaboratorio");
        for (int i = 0; i < tblExamenes.getRowCount(); i++) {
            String codigo = textoCeldaOVacio(tblExamenes, i, 0);
            String descripcion = textoCeldaOVacio(tblExamenes, i, 1);
            BigDecimal precio = monedaCeldaOCero(tblExamenes, i, 2);
            String codigoProducto = textoCeldaOVacio(tblExamenes, i, 3);

            instancias.getSql().agregarExamenesLaboratorio(codigo, descripcion, precio, codigoProducto);
        }
    }

    private void guardarImpresorasPorGrupo() {
        for (int fila = 1; fila < tblImpresoras1.getRowCount(); fila++) {
            try {
                for (int columna = 1; columna < tblImpresoras1.getColumnCount(); columna++) {
                    if (tblImpresoras1.getValueAt(fila, columna).equals("SELECCIONADO")) {
                        String grupo = tblImpresoras1.getValueAt(fila, 0).toString().split(". ")[0];
                        instancias.getSql().modificarImpresoraGrupo(grupo, tblImpresoras1.getValueAt(0, columna).toString());
                    }
                }
            } catch (Exception e) {
            }
        }
    }

    private void actualizarMaestrosEnModulos() {
        DaoMaestra _daoM = new DaoMaestra();
        DatosMaestra.setearDatosMaestra(_daoM.obtenerGeneral(), _daoM.obtenerImpresiones());

        instancias.getVistaAjusteInventario().consultarMaestros();
        instancias.getTrasladosInternos().consultarMaestros();
        instancias.getReporte().consultarMaestros();
        instancias.getReimpresion().consultarMaestros();
        instancias.getAbonos().consultarMaestros();

        // Buzón de solicitudes de permiso según "Módulo de solicitudes de permisos"
        if (instancias.getMenu() != null) {
            instancias.getMenu().activarFuncionSolicitudes();
        }

        // Consecutivo adicional / turno (restaurante) en las vistas de facturación
        actualizarConsecutivoEnVista(instancias::getFactura);
        actualizarConsecutivoEnVista(instancias::getFacturaCreditos);
        actualizarConsecutivoEnVista(instancias::getPedido);

        if (instancias.getConfiguraciones().isVeterinaria()) {
            instancias.getHospitalizacion().consultarMaestros();
            instancias.getFormatos().consultarMaestros();
        }

        if (instancias.getConfiguraciones().isLaboratorio()) {
            instancias.getHistoriaLaboratorio().actualizarExamenes();
        }
    }

    private void actualizarConsecutivoEnVista(java.util.function.Supplier<Vista.Ventas.VistaFactura> vista) {
        try {
            Vista.Ventas.VistaFactura factura = vista.get();
            if (factura != null) {
                factura.actualizarVistaConsecutivo();
            }
        } catch (NullPointerException e) {
            // El módulo no está cargado: tomará la configuración al abrirse
        }
    }

    private String textoCeldaOVacio(javax.swing.JTable tabla, int fila, int columna) {
        try {
            return tabla.getValueAt(fila, columna).toString();
        } catch (Exception e) {
            return "";
        }
    }

    private BigDecimal monedaCeldaOCero(javax.swing.JTable tabla, int fila, int columna) {
        try {
            return big.getMoneda(tabla.getValueAt(fila, columna).toString());
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }

    private int parseIntSafe(String valor) {
        try {
            return Integer.parseInt(valor == null ? "0" : valor.trim());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private ModeloMaestraGeneral construirModeloGeneral() {
        if (tblResponsabilidadesFiscales.isEditing()) {
            tblResponsabilidadesFiscales.getCellEditor().stopCellEditing();
        }

        ModeloMaestraGeneral general = daoMaestra.obtenerGeneral();
        if (general == null) {
            return null;
        }
        general.setConfiguracionRegimen("");
        general.setAlertaUtilidad(getCeldaBooleanaPorEtiqueta(tblConfiguraciones, "Facturar Utilidad Sobrepasada", 1) ? "SI" : "NO");
        general.setTipoDescuento(DatosMaestra.normalizarTipoDescuento(getCeldaTextoPorEtiqueta(tblConfiguraciones1, "Tipo de descuento", 1)));
        general.setContrasena(getCeldaTextoPorEtiqueta(tblConfiguraciones1, "Contraseña para control y permisos", 1));
        general.setDiasAlertaCuadre(parseIntSafe(getCeldaTextoPorEtiqueta(tblConfiguraciones1, "Días para alerta de cartera", 1)));
        general.setNitEmpresa(getCeldaTextoPorEtiqueta(tblInformacionEmpresa, "NIT", 1).toUpperCase());
        general.setNombreEmpresa(getCeldaTextoPorEtiqueta(tblInformacionEmpresa, "Nombre", 1).toUpperCase());
        general.setRegimenEmpresa(getCeldaTextoPorEtiqueta(tblInformacionEmpresa, "Régimen", 1).toUpperCase());
        general.setDireccionEmpresa(getCeldaTextoPorEtiqueta(tblInformacionEmpresa, "Dirección", 1).toUpperCase());
        general.setTelefonoEmpresa(getCeldaTextoPorEtiqueta(tblInformacionEmpresa, "Teléfono", 1).toUpperCase());
        general.setUsarLector(getCeldaBooleanaPorEtiqueta(tblConfiguraciones, OPCION_USAR_LECTOR, 1));
        general.setTituloFactura(obtenerTituloFactura());
        general.setPreciosConImpuesto(getCeldaBooleanaPorEtiqueta(tblConfiguraciones, "Precios con impuestos", 1));
        general.setVentasPredeterminado(getCeldaBooleanaPorEtiqueta(tblConfiguraciones, "Ventas Contado Predeterminado", 1));
        general.setMensajeUtilidad(getCeldaBooleanaPorEtiqueta(tblConfiguraciones, "Alertar Utilidad Sobrepasada", 1));
        general.setDiasCobroMora(parseIntSafe(instancias.getConfiguraciones().isCreditos() ? getCeldaTextoPorEtiqueta(tblConfiguraciones1, "Días para cobrar mora en créditos", 1) : "0"));
        general.setPorcentajeMora(instancias.getConfiguraciones().isCreditos() ? getCeldaTextoPorEtiqueta(tblConfiguraciones1, "Porcentaje de mora en créditos", 1) : "0");
        general.setGeneraOrdenMedica(rdSiGenerarOrden.isSelected());
        general.setImprimirOrdenMedica(rdSiImprimirOrden.isSelected());
        general.setImprimirFacturaOrdenMedica(rdSiImprimirFactura.isSelected());
        general.setHoraInicioAgenda(String.valueOf(cmbHoraInicio.getSelectedItem()));
        general.setHoraFinAgenda(String.valueOf(cmbHoraFin.getSelectedItem()));
        general.setIntervaloAgenda(String.valueOf(cmbIntervalo.getSelectedItem()));
        general.setCodPrestadorServicio(txtCodigoPrestadorServico.getText());
        general.setValidarCupoCredito(getCeldaBooleanaPorEtiqueta(tblConfiguraciones, "Validar el cupo del credito", 1));
        general.setMostrarInformacionCuadre(false);
        general.setDescuentoMaximoVentas(isTipoDescuentoPorcentaje() ? parseIntSafe(getCeldaTextoPorEtiqueta(tblConfiguraciones1, "Descuento máximo para aplicar", 1)) : 0);
        general.setTipoIdPrestadorServicio(String.valueOf(cmbTipoPrestador.getSelectedItem()));
        general.setLimiteProductosPorFactura(parseIntSafe(getCeldaTextoPorEtiqueta(tblConfiguraciones1, "Límite de productos (carta/media carta)", 1)));
        general.setCombinarProductos(getCeldaBooleanaPorEtiqueta(tblConfiguraciones, "Combinar productos iguales", 1));
        general.setModificarNombre(getCeldaBooleanaPorEtiqueta(tblConfiguraciones, "Modificar nombre de productos", 1));
        general.setTurno(getCeldaBooleanaPorEtiqueta(tblConfiguraciones, "Activar turnos", 1));
        general.setTurnoConsecutivo(isTurnoActivo() ? parseIntSafe(getCeldaTextoPorEtiqueta(tblConfiguraciones1, "Número de turno", 1)) : 0);
        // En restaurante la opción no se muestra ni aplica: se guarda desactivada
        general.setConsecutivoAdicional(getCeldaBooleanaPorEtiqueta(tblConfiguraciones, OPCION_CONSECUTIVO_ADICIONAL, 1));
        general.setModificarPrecio(getCeldaBooleanaPorEtiqueta(tblConfiguraciones, OPCION_MODIFICAR_PRECIO, 1));

        boolean opcionBorrarProductos = instancias.getConfiguraciones().isRestaurante()
                ? getCeldaBooleanaPorEtiqueta(tblConfiguraciones, "Borrar productos en mesas", 1)
                : getCeldaBooleanaPorEtiqueta(tblConfiguraciones, "Borrar productos en congeladas", 1);

        general.setBorrarProductos(opcionBorrarProductos);
        general.setFacturarSinInventario(getCeldaBooleanaPorEtiqueta(tblConfiguraciones, "Facturar sin inventario", 1));
        general.setCantidadEstablecida(parseIntSafe(getCeldaTextoPorEtiqueta(tblConfiguraciones1, "Cantidad al cargar un producto", 1)));
        general.setFilas(parseIntSafe(txtFilas.getText()));
        general.setColumnas(parseIntSafe(txtColumnas.getText()));
        general.setPorcPropina(parseIntSafe(instancias.getConfiguraciones().isRestaurante() ? getCeldaTextoPorEtiqueta(tblConfiguraciones1, "Porcentaje de propina en pre-factura", 1) : "0"));
        general.setMostrarDevuelta(getCeldaBooleanaPorEtiqueta(tblConfiguraciones, "Mostrar devuelta en factura", 1));
        general.setInicioHosp(String.valueOf(cmbHoraInicio1.getSelectedItem()));
        general.setFinHosp(String.valueOf(cmbHoraFin1.getSelectedItem()));
        general.setIntervalosHosp(String.valueOf(cmbIntervalo1.getSelectedItem()));
        general.setDiasAutomaticos(getCeldaBooleanaPorEtiqueta(tblConfiguraciones, "Días de plazo automaticos", 1));
        general.setModuloPermisos(getCeldaBooleanaPorEtiqueta(tblConfiguraciones, "Módulo de solicitudes de permisos", 1));
        // Sin modificar precio, el foco solo puede ir a la cantidad
        general.setFoco(isModificarPrecioActivo()
                ? getCeldaTextoPorEtiqueta(tblConfiguraciones1, OPCION_FOCO_PRODUCTO, 1)
                : FOCO_CANTIDAD);
        general.setFacturarMesas(valorOpcionMeseros(OPCION_FACTURAR_MESAS_MESEROS, DatosMaestra.isFacturarMesas()));
        general.setSoloMesas(valorOpcionMeseros(OPCION_SOLO_MESAS_MESEROS, DatosMaestra.isSoloMesas()));

        boolean opcionBorrarDocumento = instancias.getConfiguraciones().isRestaurante()
                ? getCeldaBooleanaPorEtiqueta(tblConfiguraciones, "Borrar mesas", 1)
                : getCeldaBooleanaPorEtiqueta(tblConfiguraciones, "Borrar congeladas", 1);

        general.setBorrarMesas(opcionBorrarDocumento);
        general.setPassword("");
        general.setResponsabilidadesFiscales(obtenerResponsabilidadesFiscalesSeleccionadas());
        return general;
    }

    private void btnActualizarLogoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarLogoActionPerformed
        try {
            String origen = "";
            try {
                origen = metodos.obtenerRuta3(this, "logoEmpresa");
            } catch (Exception e) {
                return;
            }
            if (origen == null) {
                // El usuario canceló la selección
                return;
            }

            String destino = RUTA_LOGO_EMPRESA;
//            metodosGenerales.copiarArchivo(origen, destino);
            metodos.montarLogo(origen, destino);
            mostrarLogoEmpresa();
            ControladorAlertas.alertSuccess("Imagen cargada con exito");
        } catch (Exception e) {
            ControladorAlertas.alertFail("Error al guardar el logo");
            try {
                String origen = System.getProperty("user.dir") + "\\imagenes\\conf\\logoEmpresa2.png";
                String destino = System.getProperty("user.dir") + "\\imagenes\\conf\\logoEmpresa.png";
//                metodosGenerales.copiarArchivo(origen, destino);
                metodos.montarLogo(origen, destino);
            } catch (Exception ex) {
            }
            mostrarLogoEmpresa();
            System.out.println(e);
        }
    }//GEN-LAST:event_btnActualizarLogoActionPerformed

    private static final String RUTA_LOGO_EMPRESA = System.getProperty("user.dir") + "\\imagenes\\conf\\logoEmpresa.png";

    private VistaPreviaLogo vistaPreviaLogo;

    private void inicializarVistaLogo() {
        vistaPreviaLogo = new VistaPreviaLogo();
        pnlLogo.removeAll();
        pnlLogo.setLayout(new java.awt.BorderLayout());
        pnlLogo.add(vistaPreviaLogo, java.awt.BorderLayout.CENTER);
        mostrarLogoEmpresa();
    }

    private void mostrarLogoEmpresa() {
        if (vistaPreviaLogo == null) {
            return;
        }
        java.awt.image.BufferedImage logo = null;
        java.io.File archivo = new java.io.File(RUTA_LOGO_EMPRESA);
        if (archivo.isFile()) {
            try {
                logo = javax.imageio.ImageIO.read(archivo);
            } catch (java.io.IOException e) {
                logo = null;
            }
        }
        vistaPreviaLogo.setImagen(logo);
    }

    private static class VistaPreviaLogo extends javax.swing.JComponent {

        private static final int MARGEN = 6;

        private java.awt.image.BufferedImage imagen;
        private java.awt.image.BufferedImage escalada;

        void setImagen(java.awt.image.BufferedImage imagen) {
            this.imagen = imagen;
            this.escalada = null;
            repaint();
        }

        @Override
        protected void paintComponent(java.awt.Graphics g) {
            super.paintComponent(g);
            int anchoDisponible = getWidth() - MARGEN * 2;
            int altoDisponible = getHeight() - MARGEN * 2;

            java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
            try {
                g2.setRenderingHint(java.awt.RenderingHints.KEY_TEXT_ANTIALIASING,
                        java.awt.RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                if (imagen == null) {
                    g2.setColor(java.awt.Color.GRAY);
                    g2.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 13));
                    String texto = "Sin logo";
                    java.awt.FontMetrics fm = g2.getFontMetrics();
                    g2.drawString(texto, (getWidth() - fm.stringWidth(texto)) / 2,
                            (getHeight() + fm.getAscent() - fm.getDescent()) / 2);
                    return;
                }
                if (anchoDisponible <= 0 || altoDisponible <= 0) {
                    return;
                }

                double escala = Math.min(1.0, Math.min(
                        (double) anchoDisponible / imagen.getWidth(),
                        (double) altoDisponible / imagen.getHeight()));
                int ancho = Math.max(1, (int) Math.round(imagen.getWidth() * escala));
                int alto = Math.max(1, (int) Math.round(imagen.getHeight() * escala));

                if (escalada == null || escalada.getWidth() != ancho || escalada.getHeight() != alto) {
                    escalada = escalar(imagen, ancho, alto);
                }
                g2.drawImage(escalada, (getWidth() - ancho) / 2, (getHeight() - alto) / 2, null);
            } finally {
                g2.dispose();
            }
        }

        private static java.awt.image.BufferedImage escalar(java.awt.image.BufferedImage origen, int ancho, int alto) {
            java.awt.image.BufferedImage actual = aArgb(origen);
            int w = actual.getWidth();
            int h = actual.getHeight();
            if (w == ancho && h == alto) {
                return actual;
            }
            // Reducción por pasos de la mitad: evita el aspecto pixelado de
            // reducir una imagen grande en un solo paso
            do {
                w = Math.max(ancho, w / 2);
                h = Math.max(alto, h / 2);
                java.awt.image.BufferedImage paso = new java.awt.image.BufferedImage(w, h, java.awt.image.BufferedImage.TYPE_INT_ARGB);
                java.awt.Graphics2D g = paso.createGraphics();
                g.setRenderingHint(java.awt.RenderingHints.KEY_INTERPOLATION,
                        java.awt.RenderingHints.VALUE_INTERPOLATION_BICUBIC);
                g.setRenderingHint(java.awt.RenderingHints.KEY_RENDERING,
                        java.awt.RenderingHints.VALUE_RENDER_QUALITY);
                g.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING,
                        java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
                g.drawImage(actual, 0, 0, w, h, null);
                g.dispose();
                actual = paso;
            } while (w != ancho || h != alto);
            return actual;
        }

        private static java.awt.image.BufferedImage aArgb(java.awt.image.BufferedImage origen) {
            if (origen.getType() == java.awt.image.BufferedImage.TYPE_INT_ARGB) {
                return origen;
            }
            java.awt.image.BufferedImage copia = new java.awt.image.BufferedImage(
                    origen.getWidth(), origen.getHeight(), java.awt.image.BufferedImage.TYPE_INT_ARGB);
            java.awt.Graphics2D g = copia.createGraphics();
            g.drawImage(origen, 0, 0, null);
            g.dispose();
            return copia;
        }
    }

    private void rdNoGenerarOrdenItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdNoGenerarOrdenItemStateChanged
        if (rdNoGenerarOrden.isSelected()) {
            rdConsulta.setEnabled(true);
            rdProcedimiento.setEnabled(true);
            txtaCobrar.setEnabled(true);
            cmbFinalidad.setEnabled(true);
            cmbForma.setEnabled(true);
            cmbPersonal.setEnabled(true);
            cmbConcepto.setEnabled(true);
            cmbCausa.setEnabled(true);
            cmbActoQuirurgico.setEnabled(true);
            cmbAmbito.setEnabled(true);
            cmbCodigoConsulta.setEnabled(true);
        } else {
            rdConsulta.setEnabled(false);
            rdProcedimiento.setEnabled(false);
            txtaCobrar.setEnabled(false);
            cmbFinalidad.setEnabled(false);
            cmbForma.setEnabled(false);
            cmbPersonal.setEnabled(false);
            cmbConcepto.setEnabled(false);
            cmbCausa.setEnabled(false);
            cmbActoQuirurgico.setEnabled(false);
            cmbAmbito.setEnabled(false);
            cmbCodigoConsulta.setEnabled(false);

            txtaCobrar.setText("");
            txtaCobrar1.setText("");
            txtValor1.setText("");
            this.idProductoOrdenServicio = "";

        }
    }//GEN-LAST:event_rdNoGenerarOrdenItemStateChanged

    private void rdNoImprimirOrdenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdNoImprimirOrdenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdNoImprimirOrdenActionPerformed

    private void txtCodigoPrestadorServicoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoPrestadorServicoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoPrestadorServicoKeyTyped

    private void btnActualizarImpresorasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarImpresorasActionPerformed
        actualizarImpresorasDisponibles();
    }//GEN-LAST:event_btnActualizarImpresorasActionPerformed

    private static final String OPCION_IMPRESION_POR_GRUPO = "Impresión por grupo";

    private void inicializarImpresionPorGrupo() {
        if (!instancias.getConfiguraciones().isRestaurante()) {
            // La impresión por grupo solo aplica para restaurante
            int fila = getFilaTabla(tblOpcionesImpresiones, OPCION_IMPRESION_POR_GRUPO);
            if (fila >= 0) {
                ((DefaultTableModel) tblOpcionesImpresiones.getModel()).removeRow(fila);
            }
            mostrarPanelImpresionPorGrupo(false);
            return;
        }
        tblOpcionesImpresiones.getModel().addTableModelListener(e -> {
            int fila = getFilaTabla(tblOpcionesImpresiones, OPCION_IMPRESION_POR_GRUPO);
            if (fila >= 0 && e.getFirstRow() <= fila && fila <= e.getLastRow()
                    && (e.getColumn() == 1 || e.getColumn() == javax.swing.event.TableModelEvent.ALL_COLUMNS)) {
                aplicarEstadoImpresionPorGrupo();
            }
        });
        tabPanel.addChangeListener(e -> {
            java.awt.Component pestana = tabPanel.getSelectedComponent();
            if (pestana != null && javax.swing.SwingUtilities.isDescendingFrom(pnlImpresiones, pestana)
                    && isImpresionPorGrupoActiva()) {
                actualizarImpresorasDisponibles();
            }
        });
        mostrarPanelImpresionPorGrupo(isImpresionPorGrupoActiva());
    }

    private boolean isImpresionPorGrupoActiva() {
        return getCeldaBooleanaPorEtiqueta(tblOpcionesImpresiones, OPCION_IMPRESION_POR_GRUPO, 1);
    }

    private void aplicarEstadoImpresionPorGrupo() {
        boolean activa = isImpresionPorGrupoActiva();
        mostrarPanelImpresionPorGrupo(activa);
        if (activa) {
            actualizarImpresorasDisponibles();
        }
    }

    private void mostrarPanelImpresionPorGrupo(boolean visible) {
        pnlImpresionesGrupo.setVisible(visible);
        pnlImpresiones.revalidate();
        pnlImpresiones.repaint();
    }

    private void agregarScrollPestanaImpresiones() {
        int indice = tabPanel.indexOfComponent(pnlImpresiones);
        if (indice < 0) {
            return;
        }
        PanelDesplazable contenedor = new PanelDesplazable();
        javax.swing.JScrollPane scroll = new javax.swing.JScrollPane(contenedor);
        tabPanel.setComponentAt(indice, scroll);
        contenedor.add(pnlImpresiones, java.awt.BorderLayout.CENTER);
        // El JTabbedPane oculta los paneles de pestañas no seleccionadas; al
        // moverlo al scroll hay que volver a hacerlo visible.
        pnlImpresiones.setVisible(true);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        scroll.getHorizontalScrollBar().setUnitIncrement(16);
    }

    private class PanelDesplazable extends javax.swing.JPanel implements javax.swing.Scrollable {

        private static final int ALTO_EXTRA_IMPRESION_GRUPO = 200;

        PanelDesplazable() {
            super(new java.awt.BorderLayout());
        }

        @Override
        public Dimension getPreferredSize() {
            Dimension tamano = super.getPreferredSize();
            if (pnlImpresionesGrupo.isVisible()) {
                tamano = new Dimension(tamano.width, tamano.height + ALTO_EXTRA_IMPRESION_GRUPO);
            }
            return tamano;
        }

        @Override
        public Dimension getPreferredScrollableViewportSize() {
            // No se pide tamaño propio: la pestaña toma el tamaño de las demás
            // (la ventana está diseñada pequeña) y el contenido que no quepa se
            // recorre con la barra de desplazamiento.
            return new Dimension(0, 0);
        }

        @Override
        public int getScrollableUnitIncrement(java.awt.Rectangle visible, int orientacion, int direccion) {
            return 16;
        }

        @Override
        public int getScrollableBlockIncrement(java.awt.Rectangle visible, int orientacion, int direccion) {
            return orientacion == javax.swing.SwingConstants.VERTICAL ? visible.height : visible.width;
        }

        @Override
        public boolean getScrollableTracksViewportWidth() {
            return getParent() != null && getParent().getWidth() >= getMinimumSize().width;
        }

        @Override
        public boolean getScrollableTracksViewportHeight() {
            return getParent() != null && getParent().getHeight() >= getPreferredSize().height;
        }
    }

    private void actualizarImpresorasDisponibles() {
        PrintService[] impresoras = PrintServiceLookup.lookupPrintServices(null, null);
        DefaultTableModel model = (DefaultTableModel) tblImpresoras.getModel();

        while (tblImpresoras.getRowCount() > 0) {
            model.removeRow(0);
        }

        for (PrintService impresora : impresoras) {
            String nombre = impresora.toString().split(" : ")[1];
            if (!nombre.equals("Fax")) {
                model.addRow(new Object[]{nombre});
            }
        }
    }

    private void btnGuardar4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar4ActionPerformed
        int columna;
        try {
            columna = tblImpresoras1.getSelectedColumn();
        } catch (Exception e) {
            ControladorAlertas.alertFail("Seleccione una impresora");
            return;
        }

        if (columna == 0) {
            ControladorAlertas.alertFail("Esta columna no se puede eliminar");
            return;
        } else if (columna == 1) {
            ControladorAlertas.alertFail("Esta impresora no se puede eliminar");
            return;
        }

        for (int i = 0; i < tblImpresoras1.getRowCount(); i++) {
            tblImpresoras1.setValueAt("", i, tblImpresoras1.getSelectedColumn());
        }
    }//GEN-LAST:event_btnGuardar4ActionPerformed

    private void tblImpresorasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblImpresorasMouseClicked
        if (evt.getClickCount() > 1) {
            int pos;
            if (tblImpresoras1.getValueAt(0, 1).equals("")) {
                pos = 1;
            } else if (tblImpresoras1.getValueAt(0, 2).equals("")) {
                pos = 2;
            } else if (tblImpresoras1.getValueAt(0, 3).equals("")) {
                pos = 3;
            } else if (tblImpresoras1.getValueAt(0, 4).equals("")) {
                pos = 4;
            } else if (tblImpresoras1.getValueAt(0, 5).equals("")) {
                pos = 5;
            } else {
                pos = 0;
            }

            String impresora = tblImpresoras.getValueAt(tblImpresoras.getSelectedRow(), 0).toString();
            for (int i = 1; i < tblImpresoras1.getColumnCount(); i++) {
                String nombre = tblImpresoras1.getValueAt(0, i).toString();
                if (impresora.equals(nombre)) {
                    ControladorAlertas.alertFail("Esta impresora ya esta cargada!");
                    return;
                }
            }

            if (pos == 0) {
                ControladorAlertas.alertFail("Espacio insuficiente.");
                return;
            } else {
                tblImpresoras1.setValueAt(tblImpresoras.getValueAt(tblImpresoras.getSelectedRow(), 0), 0, pos);
            }
        }
    }//GEN-LAST:event_tblImpresorasMouseClicked

    private void tblImpresoras1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblImpresoras1KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
            for (int i = 0; i < tblImpresoras1.getRowCount(); i++) {
                tblImpresoras1.setValueAt("", i, tblImpresoras1.getSelectedColumn());
            }
        }
    }//GEN-LAST:event_tblImpresoras1KeyReleased

    private void tblImpresoras1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblImpresoras1MouseClicked
        int fila;

        try {
            fila = tblImpresoras1.getSelectedRow();
        } catch (Exception e) {
            ControladorAlertas.alertFail("Seleccione un grupo.");
            return;
        }

        if (tblImpresoras1.getValueAt(0, tblImpresoras1.getSelectedColumn()).equals("") || tblImpresoras1.getValueAt(0, tblImpresoras1.getSelectedColumn()) == null) {
            ControladorAlertas.alertFail("Impresora no válida.");
            return;
        }

        if (fila == 0) {
            return;
        }

        for (int i = 1; i < tblImpresoras1.getColumnCount(); i++) {
            tblImpresoras1.setValueAt("", tblImpresoras1.getSelectedRow(), i);
        }

        tblImpresoras1.setValueAt("SELECCIONADO", tblImpresoras1.getSelectedRow(), tblImpresoras1.getSelectedColumn());
    }//GEN-LAST:event_tblImpresoras1MouseClicked

    private void tblExamenesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblExamenesKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (tblExamenes.getSelectedColumn() == 2) {
                BigDecimal num;
                try {
                    num = big.getBigDecimal(tblExamenes.getValueAt(tblExamenes.getSelectedRow(), 2));
                } catch (Exception e) {
                    num = BigDecimal.ZERO;
                }
                tblExamenes.setValueAt(big.setMoneda(num), tblExamenes.getSelectedRow(), 2);
            }
        }
    }//GEN-LAST:event_tblExamenesKeyReleased

    private void tblDiasBloqueoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDiasBloqueoMouseClicked

    }//GEN-LAST:event_tblDiasBloqueoMouseClicked

    private void dtFechaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dtFechaMouseClicked
        if (evt.getClickCount() >= 2) {
            dtFecha.setVisible(false);
            btnActualizarLogo.setVisible(true);
        }
    }//GEN-LAST:event_dtFechaMouseClicked

    private void dtFechaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dtFechaPropertyChange
        //        cargarAgendas(lbTitulo.getText().substring(10, lbTitulo.getText().length()));
    }//GEN-LAST:event_dtFechaPropertyChange

    private void popBorrar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popBorrar1ActionPerformed
        if (tblDiasBloqueo.getSelectedRow() > -1) {

            int fila = tblDiasBloqueo.getSelectedRow();

            DefaultTableModel modelo = (DefaultTableModel) tblDiasBloqueo.getModel();
            modelo.removeRow(fila);
            tblDiasBloqueo.removeEditor();
        } else {
            ControladorAlertas.alert("Seleccione una fecha");
        }
    }//GEN-LAST:event_popBorrar1ActionPerformed

    private void tblHorasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHorasMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblHorasMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (cmbDoctores.getSelectedIndex() == 0) {
            ControladorAlertas.alertFail("Escoja un usuario");
            return;
        }

        DefaultTableModel modelo = (DefaultTableModel) tblDiasBloqueo.getModel();

        String horas = "";
        for (int i = 0; i < tblHoras.getRowCount(); i++) {
            if ((Boolean) tblHoras.getValueAt(i, 1)) {
                horas = horas + tblHoras.getValueAt(i, 0) + ", ";
            }
        }

        if (horas.length() > 0) {
            horas = horas.substring(0, horas.length() - 2);
        }

        modelo.addRow(new Object[]{metodos.desdeDate(dtFecha.getCalendar()), horas, cmbDoctores.getSelectedItem(), "", cmbDoctores1.getItemAt(cmbDoctores.getSelectedIndex())});

        for (int i = 0; i < tblHoras.getRowCount(); i++) {
            tblHoras.setValueAt(false, i, 1);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtColumnasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtColumnasKeyReleased

    }//GEN-LAST:event_txtColumnasKeyReleased

    private void txtFilasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFilasKeyReleased

    }//GEN-LAST:event_txtFilasKeyReleased

    private void txtFilasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFilasKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtFilasKeyTyped

    private void tblRestauranteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblRestauranteMouseClicked
        // delegado a PanelDisenoMesas.ListenerCambio
    }//GEN-LAST:event_tblRestauranteMouseClicked

    private void btnGenerarTablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarTablaActionPerformed
        int columnas, filas;
        try {
            columnas = Integer.parseInt(txtColumnas.getText());
        } catch (Exception e) {
            ControladorAlertas.alertFail("Ingrese el número de columnas");
            return;
        }
        if (columnas < 1 || columnas > 26) {
            ControladorAlertas.alertFail("Las columnas deben estar entre 1 y 26.");
            return;
        }

        try {
            filas = Integer.parseInt(txtFilas.getText());
        } catch (Exception e) {
            ControladorAlertas.alertFail("Ingrese el número de filas.");
            return;
        }
        if (filas < 1) {
            ControladorAlertas.alertFail("Ingrese al menos 1 fila.");
            return;
        }

        panelDisenoMesas.setDimensiones(filas, columnas);
    }//GEN-LAST:event_btnGenerarTablaActionPerformed

    private void cmbConceptoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbConceptoKeyReleased

    }//GEN-LAST:event_cmbConceptoKeyReleased

    private void cmbAmbitoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbAmbitoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbPersonal.requestFocus();
        }
    }//GEN-LAST:event_cmbAmbitoKeyReleased

    private void cmbFinalidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbFinalidadKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbCausa.requestFocus();
        }
    }//GEN-LAST:event_cmbFinalidadKeyReleased

    private void cmbCausaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbCausaKeyReleased

    }//GEN-LAST:event_cmbCausaKeyReleased

    private void cmbPersonalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbPersonalKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbForma.requestFocus();
        }
    }//GEN-LAST:event_cmbPersonalKeyReleased

    private void txtaCobrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtaCobrarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtaCobrarActionPerformed

    private void txtaCobrarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtaCobrarKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cargarProducto(txtaCobrar.getText());
        } else {
            txtaCobrar1.setText("");
            txtValor1.setText("");
        }
    }//GEN-LAST:event_txtaCobrarKeyReleased

    private void txtaCobrarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtaCobrarKeyTyped

    }//GEN-LAST:event_txtaCobrarKeyTyped

    private void cmbFormaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbFormaKeyReleased

    }//GEN-LAST:event_cmbFormaKeyReleased

    private void txtValor1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtValor1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValor1ActionPerformed

    private void txtValor1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValor1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValor1KeyReleased

    private void txtValor1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValor1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValor1KeyTyped

    private void rdProcedimientoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rdProcedimientoStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_rdProcedimientoStateChanged

    private void rdProcedimientoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdProcedimientoItemStateChanged
        /*        if (rdProcedimiento.isSelected()) {
         cmbActoQuirurgico.setEnabled(true);
         } else {
         cmbActoQuirurgico.setEnabled(false);
         }*/
    }//GEN-LAST:event_rdProcedimientoItemStateChanged

    private void rdConsultaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdConsultaItemStateChanged
        /*        if (rdConsulta.isSelected()) {
         cmbActoQuirurgico.setEnabled(false);
         txtDiagnostico.setEnabled(false);
         txtProcedimiento.setEnabled(false);
         txtAutorizacion.setEnabled(true);
         } else {
         cmbActoQuirurgico.setEnabled(true);
         txtDiagnostico.setEnabled(true);
         txtProcedimiento.setEnabled(true);
         txtAutorizacion.setEnabled(true);
         }*/
    }//GEN-LAST:event_rdConsultaItemStateChanged

    private void txtaCobrar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtaCobrar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtaCobrar1ActionPerformed

    private void txtaCobrar1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtaCobrar1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtaCobrar1KeyReleased

    private void txtaCobrar1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtaCobrar1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtaCobrar1KeyTyped

    private void cmbCodigoConsultaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbCodigoConsultaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbCodigoConsultaKeyReleased

    private void txtNombreFormatoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreFormatoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreFormatoKeyReleased

    private void txtNombreFormatoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreFormatoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreFormatoKeyTyped


    private void tblMesasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMesasMouseClicked
        if (evt.getClickCount() > 1) {
            if (ControladorAlertas.option("¿Desea quitar mesero?")) {
                instancias.getSql().cambiarEstadoMesa(tblMesas.getValueAt(tblMesas.getSelectedRow(), 0).toString(), "DISPONIBLE");
                btnActualizarMesasActionPerformed(null);
            }
        }
    }//GEN-LAST:event_tblMesasMouseClicked

    private void btnActualizarMesasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarMesasActionPerformed
        Object[][] mesas = instancias.getSql().getMesasOcupadas();

        DefaultTableModel modelo = (DefaultTableModel) tblMesas.getModel();

        while (tblMesas.getRowCount() > 0) {
            modelo.removeRow(0);
        }

        for (int i = 0; i < mesas.length; i++) {
            modelo.addRow(new Object[]{mesas[i][0], mesas[i][1]});
        }
    }//GEN-LAST:event_btnActualizarMesasActionPerformed

    private void txtColumnas1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtColumnas1KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cargarColumna1();
            cargarFila1();
        }
    }//GEN-LAST:event_txtColumnas1KeyReleased

    private void txtFilas1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFilas1KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cargarFila1();
        }
    }//GEN-LAST:event_txtFilas1KeyReleased

    private void txtFilas1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFilas1KeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtFilas1KeyTyped

    private void tblConfigMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblConfigMouseClicked
        int columna = tblConfig.getSelectedColumn();
        int fila = tblConfig.getSelectedRow();
        boolean validacion = true;

        if (columna == 12) {
            validacion = false;
            tblConfig.setValueAt(false, tblConfig.getSelectedRow(), 13);
            tblConfig.setValueAt(true, tblConfig.getSelectedRow(), 12);
        }

        if (columna == 13) {
            validacion = false;
            tblConfig.setValueAt(false, tblConfig.getSelectedRow(), 12);
            tblConfig.setValueAt(true, tblConfig.getSelectedRow(), 13);
        }

        if (validacion) {
            tblConfig.setValueAt(true, tblConfig.getSelectedRow(), 12);
            tblConfig.setValueAt(false, tblConfig.getSelectedRow(), 13);
        }

        validacion = true;

        if ((Boolean) tblConfig.getValueAt(fila, 13) && (Boolean) tblConfig.getValueAt(fila, 12)) {
            tblConfig.setValueAt(true, tblConfig.getSelectedRow(), 12);
            tblConfig.setValueAt(false, tblConfig.getSelectedRow(), 13);
        }

        if (!(Boolean) tblConfig.getValueAt(fila, 13) && !(Boolean) tblConfig.getValueAt(fila, 12)) {
            tblConfig.setValueAt(true, tblConfig.getSelectedRow(), 12);
            tblConfig.setValueAt(false, tblConfig.getSelectedRow(), 13);
        }
    }//GEN-LAST:event_tblConfigMouseClicked

    private void tblConfigKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblConfigKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            int fila = tblConfig.getSelectedRow();

            if (tblConfig.getSelectedColumn() == 2) {
                if (tblConfig.getValueAt(fila, 2).toString().equalsIgnoreCase("") || tblConfig.getValueAt(fila, 2).toString().equalsIgnoreCase("0")) {
                    tblConfig.setValueAt(big.setMoneda(big.getBigDecimal("0")), fila, 2);
                }

                try {
                    tblConfig.setValueAt(big.setMoneda(big.getMoneda(tblConfig.getValueAt(fila, 2).toString())), fila, 2);
                } catch (Exception e) {
                    tblConfig.setValueAt(big.setMoneda(big.getBigDecimal("0")), fila, 2);
                }

                tblConfig.editCellAt(tblConfig.getSelectedRow(), 3);
                tblConfig.setColumnSelectionInterval(3, 3);
                tblConfig.transferFocus();
            } else if (tblConfig.getSelectedColumn() == 3) {
                if (tblConfig.getValueAt(fila, 3).toString().equalsIgnoreCase("") || tblConfig.getValueAt(fila, 3).toString().equalsIgnoreCase("0")) {
                    tblConfig.setValueAt("10", fila, 3);
                }

                try {
                    if (Integer.parseInt(tblConfig.getValueAt(fila, 3).toString()) < 0) {
                        ControladorAlertas.alertFail("Solo se permiten números positivos.");
                        tblConfig.setValueAt("10", fila, 3);
                        return;
                    }

                    if (Integer.parseInt(tblConfig.getValueAt(fila, 3).toString()) > 30) {
                        ControladorAlertas.alertFail("Solo se permiten entre los primeros 30 minutos.");
                        tblConfig.setValueAt("10", fila, 3);
                        return;
                    }
                } catch (Exception e) {
                    ControladorAlertas.alertFail("Solo se permiten números.");
                    tblConfig.setValueAt("10", fila, 3);
                    return;
                }

                tblConfig.editCellAt(tblConfig.getSelectedRow(), 4);
                tblConfig.setColumnSelectionInterval(4, 4);
                tblConfig.transferFocus();
            } else if (tblConfig.getSelectedColumn() == 4) {
                if (tblConfig.getValueAt(fila, 4).toString().equalsIgnoreCase("") || tblConfig.getValueAt(fila, 4).toString().equalsIgnoreCase("0")) {
                    tblConfig.setValueAt("10", fila, 4);
                }

                try {
                    if (Integer.parseInt(tblConfig.getValueAt(fila, 4).toString()) < 0) {
                        ControladorAlertas.alertFail("Solo se permiten números positivos.");
                        tblConfig.setValueAt("10", fila, 4);
                        return;
                    }

                    if (Integer.parseInt(tblConfig.getValueAt(fila, 4).toString()) > 30) {
                        ControladorAlertas.alertFail("Solo se permiten entre los primeros 30 minutos.");
                        tblConfig.setValueAt("10", fila, 4);
                        return;
                    }
                } catch (Exception e) {
                    ControladorAlertas.alertFail("Solo se permiten números.");
                    tblConfig.setValueAt("10", fila, 4);
                    return;
                }

                tblConfig.editCellAt(tblConfig.getSelectedRow(), 5);
                tblConfig.setColumnSelectionInterval(5, 5);
                tblConfig.transferFocus();
            } else if (tblConfig.getSelectedColumn() == 5) {
                if (tblConfig.getValueAt(fila, 5).toString().equalsIgnoreCase("") || tblConfig.getValueAt(fila, 5).toString().equalsIgnoreCase("0")) {
                    tblConfig.setValueAt(big.setMoneda(big.getBigDecimal("0")), fila, 5);
                }

                try {
                    tblConfig.setValueAt(big.setMoneda(big.getMoneda(tblConfig.getValueAt(fila, 5).toString())), fila, 5);
                } catch (Exception e) {
                    tblConfig.setValueAt(big.setMoneda(big.getBigDecimal("0")), fila, 5);
                }

                tblConfig.editCellAt(tblConfig.getSelectedRow(), 6);
                tblConfig.setColumnSelectionInterval(6, 6);
                tblConfig.transferFocus();
            } else if (tblConfig.getSelectedColumn() == 6) {
                if (tblConfig.getValueAt(fila, 6).toString().equalsIgnoreCase("") || tblConfig.getValueAt(fila, 6).toString().equalsIgnoreCase("0")) {
                    tblConfig.setValueAt("8", fila, 6);
                }

                try {
                    if (Integer.parseInt(tblConfig.getValueAt(fila, 6).toString()) < 0) {
                        ControladorAlertas.alertFail("Solo se permiten números positivos.");
                        tblConfig.setValueAt("8", fila, 6);
                        return;
                    }

                    if (Integer.parseInt(tblConfig.getValueAt(fila, 6).toString()) > 23) {
                        ControladorAlertas.alertFail("Son 23 horas como máximo.");
                        tblConfig.setValueAt("8", fila, 6);
                        return;
                    }
                } catch (Exception e) {
                    ControladorAlertas.alertFail("Solo se permiten números.");
                    tblConfig.setValueAt("8", fila, 6);
                    return;
                }

                tblConfig.editCellAt(tblConfig.getSelectedRow(), 7);
                tblConfig.setColumnSelectionInterval(7, 7);
                tblConfig.transferFocus();
            } else if (tblConfig.getSelectedColumn() == 7) {
                if (tblConfig.getValueAt(fila, 7).toString().equalsIgnoreCase("") || tblConfig.getValueAt(fila, 7).toString().equalsIgnoreCase("0")) {
                    tblConfig.setValueAt(big.setMoneda(big.getBigDecimal("0")), fila, 7);
                }

                try {
                    tblConfig.setValueAt(big.setMoneda(big.getMoneda(tblConfig.getValueAt(fila, 7).toString())), fila, 7);
                } catch (Exception e) {
                    tblConfig.setValueAt(big.setMoneda(big.getBigDecimal("0")), fila, 7);
                }

                tblConfig.editCellAt(tblConfig.getSelectedRow(), 8);
                tblConfig.setColumnSelectionInterval(8, 8);
                tblConfig.transferFocus();
            } else if (tblConfig.getSelectedColumn() == 8) {
                if (tblConfig.getValueAt(fila, 8).toString().equalsIgnoreCase("") || tblConfig.getValueAt(fila, 8).toString().equalsIgnoreCase("0")) {
                    tblConfig.setValueAt("8", fila, 8);
                }

                try {
                    if (Integer.parseInt(tblConfig.getValueAt(fila, 8).toString()) < 0) {
                        ControladorAlertas.alertFail("Solo se permiten números positivos.");
                        tblConfig.setValueAt("8", fila, 8);
                        return;
                    }

                    if (Integer.parseInt(tblConfig.getValueAt(fila, 8).toString()) > 23) {
                        ControladorAlertas.alertFail("Son 23 horas como máximo.");
                        tblConfig.setValueAt("8", fila, 8);
                        return;
                    }
                } catch (Exception e) {
                    ControladorAlertas.alertFail("Solo se permiten números.");
                    tblConfig.setValueAt("8", fila, 8);
                    return;
                }

                tblConfig.editCellAt(tblConfig.getSelectedRow(), 9);
                tblConfig.setColumnSelectionInterval(9, 9);
                tblConfig.transferFocus();
            } else if (tblConfig.getSelectedColumn() == 9) {
                if (tblConfig.getValueAt(fila, 9).toString().equalsIgnoreCase("") || tblConfig.getValueAt(fila, 9).toString().equalsIgnoreCase("0")) {
                    tblConfig.setValueAt(big.setMoneda(big.getBigDecimal("0")), fila, 9);
                }

                try {
                    tblConfig.setValueAt(big.setMoneda(big.getMoneda(tblConfig.getValueAt(fila, 9).toString())), fila, 9);
                } catch (Exception e) {
                    tblConfig.setValueAt(big.setMoneda(big.getBigDecimal("0")), fila, 9);
                }

                tblConfig.editCellAt(tblConfig.getSelectedRow(), 10);
                tblConfig.setColumnSelectionInterval(10, 10);
                tblConfig.transferFocus();
            } else if (tblConfig.getSelectedColumn() == 10) {
                if (tblConfig.getValueAt(fila, 10).toString().equalsIgnoreCase("") || tblConfig.getValueAt(fila, 10).toString().equalsIgnoreCase("0")) {
                    tblConfig.setValueAt("24", fila, 10);
                }

                try {
                    if (Integer.parseInt(tblConfig.getValueAt(fila, 10).toString()) < 0) {
                        ControladorAlertas.alertFail("Solo se permiten números positivos.");
                        tblConfig.setValueAt("24", fila, 10);
                        return;
                    }

                    if (Integer.parseInt(tblConfig.getValueAt(fila, 10).toString()) > 24) {
                        ControladorAlertas.alertFail("Son 24 horas como máximo.");
                        tblConfig.setValueAt("24", fila, 10);
                        return;
                    }
                } catch (Exception e) {
                    ControladorAlertas.alertFail("Solo se permiten números.");
                    tblConfig.setValueAt("24", fila, 10);
                    return;
                }

                tblConfig.editCellAt(tblConfig.getSelectedRow(), 11);
                tblConfig.setColumnSelectionInterval(11, 11);
                tblConfig.transferFocus();
            } else if (tblConfig.getSelectedColumn() == 11) {
                if (tblConfig.getValueAt(fila, 11).toString().equalsIgnoreCase("") || tblConfig.getValueAt(fila, 11).toString().equalsIgnoreCase("0")) {
                    tblConfig.setValueAt(big.setMoneda(big.getBigDecimal("0")), fila, 11);
                }

                try {
                    tblConfig.setValueAt(big.setMoneda(big.getMoneda(tblConfig.getValueAt(fila, 11).toString())), fila, 11);
                } catch (Exception e) {
                    tblConfig.setValueAt(big.setMoneda(big.getBigDecimal("0")), fila, 11);
                }

                tblConfig.editCellAt(tblConfig.getSelectedRow(), 14);
                tblConfig.setColumnSelectionInterval(14, 14);
                tblConfig.transferFocus();
            } else if (tblConfig.getSelectedColumn() == 14) {
                if (tblConfig.getValueAt(fila, 14).toString().equalsIgnoreCase("") || tblConfig.getValueAt(fila, 14).toString().equalsIgnoreCase("0")) {
                    tblConfig.setValueAt("20", fila, 14);
                }

                try {
                    if (Integer.parseInt(tblConfig.getValueAt(fila, 14).toString()) < 0) {
                        ControladorAlertas.alertFail("Solo se permiten números positivos.");
                        tblConfig.setValueAt("20", fila, 14);
                        return;
                    }
                } catch (Exception e) {
                    ControladorAlertas.alertFail("Solo se permiten números.");
                    tblConfig.setValueAt("20", fila, 14);
                    return;
                }
                tblConfig.editCellAt(tblConfig.getSelectedRow(), 15);
                tblConfig.setColumnSelectionInterval(15, 15);
                tblConfig.transferFocus();
            }
        }
    }//GEN-LAST:event_tblConfigKeyReleased

    private void txtCodProductoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodProductoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String codigo = txtCodProducto.getText().replace("'", "//");
            cargarProducto1(codigo);
        } else if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            btnGuardarMaestra.requestFocus();
        }
    }//GEN-LAST:event_txtCodProductoKeyReleased

    private void btnBusProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBusProdActionPerformed
        ventanaProductos1("");
    }//GEN-LAST:event_btnBusProdActionPerformed

    private void rbtSi2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtSi2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbtSi2ActionPerformed

    private void rbtNo3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rbtNo3ItemStateChanged
        if (rbtNo3.isSelected()) {
            txtCosto.setEnabled(false);
            txtCosto.setText("");
        } else {
            txtCosto.setEnabled(true);
        }
    }//GEN-LAST:event_rbtNo3ItemStateChanged

    private void rbtSi3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rbtSi3ItemStateChanged
        if (rbtNo3.isSelected()) {
            txtCosto.setEnabled(false);
            txtCosto.setText("");
        } else {
            txtCosto.setEnabled(true);
        }
    }//GEN-LAST:event_rbtSi3ItemStateChanged

    private void txtCostoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCostoKeyReleased
        if (txtCosto.getText().equals("") || txtCosto.getText().equals(this.simbolo) || txtCosto.getText().equals(this.simbolo + " ")) {
            txtCosto.setText("0");
        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtCosto.requestFocus();
        } else {
            txtCosto.setText(big.setMoneda(big.getMoneda(txtCosto.getText())));
        }
    }//GEN-LAST:event_txtCostoKeyReleased

    private void txtCostoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCostoKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtCostoKeyTyped

    private void btnGuardar6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar6ActionPerformed
        ControladorAlertas.alert("Se va a perder todos los registros del estante de cascos");

        if (ControladorAlertas.option("¿Desea continuar?")) {

            int columnas, filas;
            try {
                columnas = Integer.parseInt(txtColumnas1.getText());
            } catch (Exception e) {
                ControladorAlertas.alertFail("Numero de columnas invalido.");
                return;
            }

            try {
                filas = Integer.parseInt(txtFilas1.getText());
            } catch (Exception e) {
                ControladorAlertas.alertFail("Numero de filas invalido.");
                return;
            }

            instancias.getSql().eliminarTodo("bdCascos");
            for (int i = 0; i < Integer.parseInt(txtFilas1.getText()); i++) {
                for (int j = 0; j < Integer.parseInt(txtColumnas1.getText()); j++) {
                    if (!instancias.getSql().agregarCasco(tblCascos.getValueAt(j, i).toString())) {
                        ControladorAlertas.alertFail("Error al guardar los espacios de los cascos");
                        return;
                    }
                }
            }

            if (!instancias.getSql().modificarMaestraParqueadero(String.valueOf(filas), String.valueOf(columnas))) {
                ControladorAlertas.alertFail("Error al guardar la configuración");
                return;
            }

            ControladorAlertas.alertSuccess("Cambios guardados con éxito");
        }
    }//GEN-LAST:event_btnGuardar6ActionPerformed

    private void popBorrar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popBorrar2ActionPerformed
        if (tblConfig.getSelectedRow() > -1) {
            int fila = tblConfig.getSelectedRow();

            DefaultTableModel modelo = (DefaultTableModel) tblConfig.getModel();
            modelo.removeRow(fila);

            tblConfig.removeEditor();
        } else {
            ControladorAlertas.alert("Seleccione un servicio");
        }
    }//GEN-LAST:event_popBorrar2ActionPerformed


    private void lbOtroConsecutivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lbOtroConsecutivoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lbOtroConsecutivoActionPerformed

    private void lbOtroConsecutivoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lbOtroConsecutivoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_lbOtroConsecutivoKeyReleased

    private void lbOtroConsecutivoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lbOtroConsecutivoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_lbOtroConsecutivoKeyTyped

    private void txtNumeroFactosElectronicasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroFactosElectronicasKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumeroFactosElectronicasKeyReleased

    private void txtNumeroFactosElectronicasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroFactosElectronicasKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumeroFactosElectronicasKeyTyped

    private void btnAgregarBodegaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarBodegaActionPerformed
        capturarBodega(-1);
    }//GEN-LAST:event_btnAgregarBodegaActionPerformed

    private void btnModificarBodegaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarBodegaActionPerformed
        if (tblBodegasInternas.getSelectedRow() < 0) {
            ControladorAlertas.alert("Seleccione una bodega");
            return;
        }
        capturarBodega(tblBodegasInternas.getSelectedRow());
    }//GEN-LAST:event_btnModificarBodegaActionPerformed

    private void btnCambiarEstadoBodegaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCambiarEstadoBodegaActionPerformed
        cambiarEstadoBodegaSeleccionada();
    }//GEN-LAST:event_btnCambiarEstadoBodegaActionPerformed

    private void btnAgregarResolucionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarResolucionActionPerformed
        capturarResolucion(-1);
    }//GEN-LAST:event_btnAgregarResolucionActionPerformed

    private void btnModificarResolucionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarResolucionActionPerformed
        if (tblComprobantes.getSelectedRow() < 0) {
            ControladorAlertas.alert("Seleccione una resolución");
            return;
        }
        capturarResolucion(tblComprobantes.getSelectedRow());
    }//GEN-LAST:event_btnModificarResolucionActionPerformed

    private void btnCambiarEstadoResolucionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCambiarEstadoResolucionActionPerformed
        cambiarEstadoResolucionSeleccionada();
    }//GEN-LAST:event_btnCambiarEstadoResolucionActionPerformed


    private void txtUsuarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cargarUsuario(txtUsuario.getText().trim());
        }
    }//GEN-LAST:event_txtUsuarioKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        seleccionarFirmaUsuario();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        limpiarUsuario();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        guardarUsuario(false);
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        guardarUsuario(true);
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnListarUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListarUsuariosActionPerformed
        buscUsuarios buscar = new buscUsuarios(null, rootPaneCheckingEnabled);
        buscar.setLocationRelativeTo(null);
        instancias.setBuscarUsuarios(buscar);
        instancias.setCampoActual(txtUsuario);
        txtUsuario.requestFocus();
        buscar.noEncontrado(txtUsuario.getText());
        buscar.show();
    }//GEN-LAST:event_btnListarUsuariosActionPerformed

    private static final String CARPETA_FIRMAS = System.getProperty("user.dir") + "\\imagenes\\firmas\\";
    private static final String FIRMA_POR_DEFECTO = CARPETA_FIRMAS + "ADMIN.jpg";

    private DaoUsuarios daoUsuarios;

    private final java.util.Map<PermisoUsuario.Modulo, javax.swing.JTable> tablasPermisos
            = new java.util.EnumMap<>(PermisoUsuario.Modulo.class);

    private final java.util.Map<PermisoUsuario.Modulo, java.util.List<PermisoUsuario>> permisosVisibles
            = new java.util.EnumMap<>(PermisoUsuario.Modulo.class);

    private ModeloUsuario usuarioCargado;

    private String firmaSeleccionada = "";

    private void inicializarUsuarios() {
        daoUsuarios = new DaoUsuarios();

        tablasPermisos.put(PermisoUsuario.Modulo.VENTAS, tblPermisosVentas);
        tablasPermisos.put(PermisoUsuario.Modulo.PRODUCTOS, tblPermisosProductos);
        tablasPermisos.put(PermisoUsuario.Modulo.TERCEROS, tblPermisosTerceros);
        tablasPermisos.put(PermisoUsuario.Modulo.CARTERA, tblPermisosCartera);
        tablasPermisos.put(PermisoUsuario.Modulo.TESORERIA, tblPermisosTesoreria);
        tablasPermisos.put(PermisoUsuario.Modulo.AGENDA, tblPermisosAgenda);
        tablasPermisos.put(PermisoUsuario.Modulo.MEDICO, tblPermisosMedico);
        tablasPermisos.put(PermisoUsuario.Modulo.VETERINARIA, tblPermisosVeterinaria);
        for (java.util.Map.Entry<PermisoUsuario.Modulo, javax.swing.JTable> entrada : tablasPermisos.entrySet()) {
            configurarTablaPermisos(entrada.getValue(), entrada.getKey());
        }

        // Pestañas de permisos de módulos que la empresa no tiene
        if (!instancias.getConfiguraciones().isAgenda()) {
            quitarPestanaPermisos(jPanel33);
        }
        if (!instancias.getConfiguraciones().isMedico()) {
            quitarPestanaPermisos(jPanel38);
        }
        if (!instancias.getConfiguraciones().isVeterinaria()) {
            quitarPestanaPermisos(jPanel43);
        }

        // Los usuarios tienen sus propios botones: en esa pestaña no aplica
        // el GUARDAR de la maestra
        tabPanel.addChangeListener(e -> actualizarBotonGuardarMaestra());
        actualizarBotonGuardarMaestra();

        limpiarUsuario();
    }

    private void actualizarBotonGuardarMaestra() {
        btnGuardarMaestra.setEnabled(tabPanel.getSelectedComponent() != pnlUsuarios);
    }

    private void quitarPestanaPermisos(java.awt.Component panel) {
        int indice = tapPanelPermisos.indexOfComponent(panel);
        if (indice >= 0) {
            tapPanelPermisos.removeTabAt(indice);
        }
    }

    private void configurarTablaPermisos(javax.swing.JTable tabla, PermisoUsuario.Modulo modulo) {
        javax.swing.table.DefaultTableModel modelo = new javax.swing.table.DefaultTableModel(
                new Object[]{"Acción", "Permiso"}, 0) {
            @Override
            public Class<?> getColumnClass(int columna) {
                return columna == 1 ? Boolean.class : String.class;
            }

            @Override
            public boolean isCellEditable(int fila, int columna) {
                return columna == 1;
            }
        };
        java.util.List<PermisoUsuario> visibles = new java.util.ArrayList<>();
        for (PermisoUsuario permiso : PermisoUsuario.delModulo(modulo)) {
            if (permisoDisponible(permiso)) {
                visibles.add(permiso);
                modelo.addRow(new Object[]{permiso.getEtiqueta(), Boolean.FALSE});
            }
        }
        permisosVisibles.put(modulo, visibles);
        tabla.setModel(modelo);
        tabla.getTableHeader().setReorderingAllowed(false);

        javax.swing.table.TableColumn columnaPermiso = tabla.getColumnModel().getColumn(1);
        columnaPermiso.setMinWidth(80);
        columnaPermiso.setPreferredWidth(80);
        columnaPermiso.setMaxWidth(80);
    }

    private boolean permisoDisponible(PermisoUsuario permiso) {
        switch (permiso) {
            case CONGELADAS:
                return instancias.getConfiguraciones().isCongeladas();
            case PLAN_SEPARES:
            case REPORTE_PLAN_SEPARES:
                return instancias.getConfiguraciones().isSepare();
            case CREDITOS:
            case REPORTE_CREDITOS:
                return instancias.getConfiguraciones().isCreditos();
            case PEDIDOS:
                return instancias.getConfiguraciones().isPedido();
            case ORDENES_SERVICIO:
            case REPORTE_ORDENES_SERVICIO:
                return instancias.getConfiguraciones().isOrdenServicio();
            // ---- Productos ----
            case TRASLADOS_INTERNOS:
            case TRASLADOS_EXTERNOS:
            case REPORTE_TRASLADO_INTERNO:
            case REPORTE_TRASLADO_EXTERNO:
                // Los traslados mueven inventario entre bodegas
                return instancias.getConfiguraciones().isInventarioBodegas();
            case REPORTE_MOVIMIENTO_SERIAL:
                // "Productos serial" en la configuración general
                return instancias.getConfiguraciones().isProductosDetallados();
            default:
                return true;
        }
    }

    private void limpiarUsuario() {
        detenerEdicionPermisos();
        usuarioCargado = null;
        txtUsuario.setEditable(true);
        txtUsuario.setText("");
        txtNombre.setText("");
        txtContra.setText("");
        txtContra2.setText("");
        for (PermisoUsuario permiso : PermisoUsuario.values()) {
            setPermiso(permiso, false);
        }
        firmaSeleccionada = "";
        lbFoto.setIcon(null);
        lbFoto.repaint();
        btnActualizar.setEnabled(false);
        btnGuardar.setEnabled(true);
        if (tapPanelPermisos.getTabCount() > 0) {
            tapPanelPermisos.setSelectedIndex(0);
        }
        txtUsuario.requestFocus();
    }

    private void cargarUsuario(String usuario) {
        if (usuario.isEmpty()) {
            ControladorAlertas.alert("Debe ingresar el usuario");
            return;
        }

        ModeloUsuario modelo = daoUsuarios.consultar(usuario);
        if (modelo == null) {
            ControladorAlertas.alert("El usuario no existe");
            return;
        }

        detenerEdicionPermisos();
        usuarioCargado = modelo;
        txtUsuario.setText(modelo.getUsuario());
        txtUsuario.setEditable(false);
        txtNombre.setText(modelo.getNombre());
        txtContra.setText(modelo.getContra());
        txtContra2.setText(modelo.getContra());
        for (PermisoUsuario permiso : PermisoUsuario.values()) {
            setPermiso(permiso, modelo.tienePermiso(permiso));
        }

        firmaSeleccionada = "";
        mostrarFirma(rutaFirma(modelo.getUsuario()));

        btnActualizar.setEnabled(true);
        btnGuardar.setEnabled(false);
        txtNombre.requestFocus();
    }

    private void guardarUsuario(boolean nuevo) {
        if (!validarDatosUsuario()) {
            return;
        }
        if (!ControladorAlertas.option("¿Desea continuar?")) {
            return;
        }

        ModeloUsuario usuario = capturarUsuario();

        if (nuevo) {
            if (daoUsuarios.existe(usuario.getUsuario())) {
                ControladorAlertas.alertFail("El usuario ya existe");
                txtUsuario.requestFocus();
                return;
            }
            if (!daoUsuarios.guardar(usuario)) {
                ControladorAlertas.alertFail("Hubo un problema al guardar el usuario");
                return;
            }
        } else if (!daoUsuarios.modificar(usuario)) {
            ControladorAlertas.alertFail("Error al modificar el usuario");
            return;
        }

        guardarFirmaUsuario(usuario.getUsuario());
        ControladorAlertas.alertSuccess(nuevo ? "Usuario registrado con éxito" : "Usuario modificado con éxito");
        limpiarUsuario();
    }

    private boolean validarDatosUsuario() {
        Object[] campos = {txtNombre, txtUsuario, txtContra, txtContra2};
        String faltantes = metodos.camposVacios(campos);

        if (!faltantes.equals("")) {
            ControladorAlertas.alert("No puede continuar, faltan los siguientes campos: " + faltantes);
            return false;
        }
        if (!String.valueOf(txtContra.getPassword()).equals(String.valueOf(txtContra2.getPassword()))) {
            ControladorAlertas.alert("Las contraseñas no coinciden");
            txtContra.requestFocus();
            return false;
        }
        return true;
    }

    private ModeloUsuario capturarUsuario() {
        detenerEdicionPermisos();
        ModeloUsuario usuario = new ModeloUsuario();
        usuario.setUsuario(txtUsuario.getText().trim());
        usuario.setNombre(txtNombre.getText().trim());
        usuario.setContra(String.valueOf(txtContra.getPassword()));
        for (PermisoUsuario permiso : PermisoUsuario.values()) {
            usuario.setPermiso(permiso, getPermiso(permiso));
        }
        return usuario;
    }

    private void setPermiso(PermisoUsuario permiso, boolean concedido) {
        int fila = permisosVisibles.get(permiso.getModulo()).indexOf(permiso);
        if (fila >= 0) {
            tablasPermisos.get(permiso.getModulo()).getModel().setValueAt(concedido, fila, 1);
        }
    }

    private boolean getPermiso(PermisoUsuario permiso) {
        int fila = permisosVisibles.get(permiso.getModulo()).indexOf(permiso);
        if (fila < 0) {
            return usuarioCargado != null && usuarioCargado.tienePermiso(permiso);
        }
        return Boolean.TRUE.equals(tablasPermisos.get(permiso.getModulo()).getModel().getValueAt(fila, 1));
    }

    private void detenerEdicionPermisos() {
        for (javax.swing.JTable tabla : tablasPermisos.values()) {
            if (tabla.isEditing()) {
                tabla.getCellEditor().stopCellEditing();
            }
        }
    }

    // ---------------- Firma del usuario ----------------
    private String rutaFirma(String usuario) {
        return CARPETA_FIRMAS + usuario + ".jpg";
    }

    private void seleccionarFirmaUsuario() {
        javax.swing.JFileChooser selector = new javax.swing.JFileChooser();
        clases.ImagePreviewPanel vistaPrevia = new clases.ImagePreviewPanel();
        selector.setAccessory(vistaPrevia);
        selector.addPropertyChangeListener(vistaPrevia);
        selector.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Imágenes (JPG, PNG)", "jpg", "jpeg", "png"));

        if (selector.showOpenDialog(this) != javax.swing.JFileChooser.APPROVE_OPTION) {
            return;
        }
        String ruta = selector.getSelectedFile().getAbsolutePath();
        if (!mostrarFirma(ruta)) {
            ControladorAlertas.alertFail("Hubo un error al cargar el archivo");
            return;
        }
        firmaSeleccionada = ruta;
    }

    private void guardarFirmaUsuario(String usuario) {
        String destino = rutaFirma(usuario);
        if (!firmaSeleccionada.isEmpty()) {
            metodos.montarFirma(firmaSeleccionada, destino);
        } else if (!new java.io.File(destino).isFile()) {
            metodosGenerales.copiarArchivo(FIRMA_POR_DEFECTO, destino);
        }
    }

    private boolean mostrarFirma(String ruta) {
        lbFoto.setIcon(null);
        java.io.File archivo = new java.io.File(ruta);
        if (!archivo.isFile()) {
            return false;
        }
        java.awt.image.BufferedImage imagen;
        try {
            imagen = javax.imageio.ImageIO.read(archivo);
        } catch (java.io.IOException e) {
            return false;
        }
        if (imagen == null) {
            return false;
        }

        int ancho = lbFoto.getWidth() > 0 ? lbFoto.getWidth() : lbFoto.getPreferredSize().width;
        int alto = lbFoto.getHeight() > 0 ? lbFoto.getHeight() : lbFoto.getPreferredSize().height;
        if (ancho > 0 && alto > 0) {
            double escala = Math.min(1.0, Math.min((double) ancho / imagen.getWidth(), (double) alto / imagen.getHeight()));
            int nuevoAncho = Math.max(1, (int) Math.round(imagen.getWidth() * escala));
            int nuevoAlto = Math.max(1, (int) Math.round(imagen.getHeight() * escala));
            imagen = metodosGenerales.redimensionarConCalidad(imagen, nuevoAncho, nuevoAlto, true);
        }
        lbFoto.setIcon(new javax.swing.ImageIcon(imagen));
        lbFoto.repaint();
        return true;
    }

    // Etiquetas de la columna "Documento" de tblPrefijos
    private static final String PREFIJO_ABONOS = "Abonos";
    private static final String PREFIJO_EGRESOS = "Egresos";
    private static final String PREFIJO_NOTAS_CREDITO = "Notas crédito";
    private static final String PREFIJO_NOTAS_DEBITO = "Notas débito";

    private boolean guardarPrefijos() {
        if (tblPrefijos.isEditing()) {
            tblPrefijos.getCellEditor().stopCellEditing();
        }

        String abonos = prefijoDe(PREFIJO_ABONOS);
        String egresos = prefijoDe(PREFIJO_EGRESOS);
        String notasCredito = prefijoDe(PREFIJO_NOTAS_CREDITO);
        String notasDebito = prefijoDe(PREFIJO_NOTAS_DEBITO);

        ModeloPrefijos datosPrefijos = new ModeloPrefijos(abonos, egresos, notasDebito, notasCredito, "TERM-1");
        if (!daoResoluciones.modificarPrefijos(datosPrefijos)) {
            ControladorAlertas.alertFail("No fue posible guardar los prefijos");
            return false;
        }

        instancias.setIdNC(notasCredito);
        instancias.setIdND(notasDebito);
        instancias.setIdAbono(abonos);
        instancias.setIdEgreso(egresos);
        return true;
    }

    private String prefijoDe(String documento) {
        return getCeldaTextoPorEtiqueta(tblPrefijos, documento, 1);
    }

    private void cargarPrefijos() {
        ModeloPrefijos prefijos = daoResoluciones.obtenerPrefijos();
        if (prefijos == null) {
            return;
        }

        mostrarPrefijo(PREFIJO_NOTAS_CREDITO, prefijos.getPrefijoNotaCredito());
        mostrarPrefijo(PREFIJO_NOTAS_DEBITO, prefijos.getPrefijoNotaDebito());
        mostrarPrefijo(PREFIJO_ABONOS, prefijos.getPrefijoAbonos());
        mostrarPrefijo(PREFIJO_EGRESOS, prefijos.getPrefijoEgresos());
    }

    private void mostrarPrefijo(String documento, String prefijo) {
        if (prefijo != null) {
            setFilaTabla(tblPrefijos, documento, 1, prefijo);
        }
    }


    private final java.util.Map<Integer, ModeloResolucion> resolucionesPendientes = new java.util.LinkedHashMap<>();
    private final java.util.Map<Integer, Boolean> estadosResolucionPendientes = new java.util.LinkedHashMap<>();
    private int siguienteIdResolucionTemporal = -1;

    private void capturarResolucion(int fila) {
        ModeloResolucion actual = null;

        if (fila >= 0) {
            int id = idResolucionDeLaFila(fila);
            ModeloResolucion pendiente = resolucionesPendientes.get(id);
            if (pendiente != null) {
                actual = copiaParaModal(pendiente);
            } else {
                actual = daoResoluciones.obtenerInformacionResolucion(id);
                if (actual == null) {
                    ControladorAlertas.alertFail("No fue posible cargar la resolución seleccionada");
                    return;
                }
            }
        }

        VistaResolucion modal = new VistaResolucion(instancias.getMenu(), true);
        modal.preparar(actual, instancias.getConfiguraciones().isFacturaElectronica());
        modal.setVisible(true);

        if (!modal.isAceptado()) {
            return;
        }

        ModeloResolucion resolucion = modal.getResolucion();
        DefaultTableModel modelo = (DefaultTableModel) tblComprobantes.getModel();

        if (fila < 0) {
            resolucion.setIdResolucion(siguienteIdResolucionTemporal--);
            modelo.addRow(filaResolucion(resolucion, DaoResoluciones.ESTADO_ACTIVA));
            fila = modelo.getRowCount() - 1;
        } else {
            Object[] valores = filaResolucion(resolucion, valorCeldaResolucion(fila, 11));
            for (int columna = 0; columna < valores.length; columna++) {
                modelo.setValueAt(valores[columna], fila, columna);
            }
        }
        resolucionesPendientes.put(resolucion.getIdResolucion(), resolucion);

        tblComprobantes.setRowSelectionInterval(fila, fila);
        actualizarBotonEstadoResolucion();
    }

    private void cambiarEstadoResolucionSeleccionada() {
        int fila = tblComprobantes.getSelectedRow();

        if (fila < 0) {
            ControladorAlertas.alert("Seleccione una resolución");
            return;
        }

        boolean activar = !esResolucionActiva(fila);
        String descripcion = valorCeldaResolucion(fila, 1);

        if (!ControladorAlertas.option(activar ? "¿Activar la resolución " + descripcion + "?"
                : "¿Inactivar la resolución " + descripcion + "?")) {
            return;
        }

        estadosResolucionPendientes.put(idResolucionDeLaFila(fila), activar);
        tblComprobantes.setValueAt(activar ? DaoResoluciones.ESTADO_ACTIVA : DaoResoluciones.ESTADO_INACTIVA, fila, 11);
        actualizarBotonEstadoResolucion();
    }

    private Object[] filaResolucion(ModeloResolucion r, String estado) {
        return new Object[]{r.getIdResolucion(), r.getDescripcionResolucion(), r.getTipoResolucion(), r.getDisenho(),
            r.getNumeroResolucion(), r.getPrefijo(), fechaPantalla(r.getFechaInicio()), fechaPantalla(r.getFechaFinal()),
            r.getNumeracionDel(), r.getNumeracionHasta(), r.getConsecutivo(), estado};
    }

    private String fechaPantalla(String fechaModal) {
        return fechaModal == null ? "" : fechaModal.replace('-', '/');
    }

    private ModeloResolucion copiaParaModal(ModeloResolucion r) {
        ModeloResolucion copia = new ModeloResolucion(r.getIdResolucion(), r.getDescripcionResolucion(), r.getTipoResolucion(),
                r.getDisenho(), r.getNumeroResolucion(), r.getPrefijo(), fechaBaseDatos(r.getFechaInicio()),
                fechaBaseDatos(r.getFechaFinal()), r.getNumeracionDel(), r.getNumeracionHasta());
        copia.setConsecutivo(r.getConsecutivo());
        return copia;
    }

    private String fechaBaseDatos(String ddMMyyyy) {
        if (ddMMyyyy == null || ddMMyyyy.length() < 10) {
            return ddMMyyyy;
        }
        String[] partes = ddMMyyyy.substring(0, 10).split("-");
        return partes.length == 3 ? partes[2] + "-" + partes[1] + "-" + partes[0] : ddMMyyyy;
    }

    private boolean hayCambiosResoluciones() {
        return !resolucionesPendientes.isEmpty() || !estadosResolucionPendientes.isEmpty();
    }

    private boolean guardarCambiosResoluciones() {
        if (!hayCambiosResoluciones()) {
            return true;
        }
        if (!daoResoluciones.guardarCambios(resolucionesPendientes.values(), estadosResolucionPendientes)) {
            ControladorAlertas.alertFail("No fue posible guardar las resoluciones");
            return false;
        }
        refrescarResolucionesEnDocumentos();
        return true;
    }

    private void cargarTablaResoluciones() {
        resolucionesPendientes.clear();
        estadosResolucionPendientes.clear();
        siguienteIdResolucionTemporal = -1;
        tblComprobantes.setModel(daoResoluciones.obtenerResolucionesEnTabla());

        // La base de datos entrega yyyy-MM-dd; en pantalla se muestra dd/MM/yyyy
        for (int i = 0; i < tblComprobantes.getRowCount(); i++) {
            tblComprobantes.setValueAt(Fechas.formatearFecha1(valorCeldaResolucion(i, 6)), i, 6);
            tblComprobantes.setValueAt(Fechas.formatearFecha1(valorCeldaResolucion(i, 7)), i, 7);
        }

        ocultarColumnaResolucion(0);
        fijarAnchoColumnaResolucion(3, 60);
        fijarAnchoColumnaResolucion(5, 60);
        fijarAnchoColumnaResolucion(6, 90);
        fijarAnchoColumnaResolucion(7, 90);
        fijarAnchoColumnaResolucion(8, 80);
        fijarAnchoColumnaResolucion(9, 80);
        fijarAnchoColumnaResolucion(10, 70);
        fijarAnchoColumnaResolucion(11, 80);

        if (!instancias.getConfiguraciones().isFacturaElectronica()) {
            ocultarColumnaResolucion(3);
        }

        actualizarBotonEstadoResolucion();
    }

    private void ocultarColumnaResolucion(int columna) {
        fijarAnchoColumnaResolucion(columna, 0);
    }

    private void quitarPestana(java.awt.Component panel) {
        int indice = tabPanel.indexOfComponent(panel);
        if (indice >= 0) {
            tabPanel.removeTabAt(indice);
        }
    }

    private void ocultarColumna(javax.swing.JTable tabla, int columna) {
        javax.swing.table.TableColumn columnaTabla = tabla.getColumnModel().getColumn(columna);
        columnaTabla.setMinWidth(0);
        columnaTabla.setPreferredWidth(0);
        columnaTabla.setMaxWidth(0);
        columnaTabla.setResizable(false);
    }

    private void fijarAnchoColumnaResolucion(int columna, int ancho) {
        tblComprobantes.getColumnModel().getColumn(columna).setMinWidth(ancho);
        tblComprobantes.getColumnModel().getColumn(columna).setPreferredWidth(ancho);
        tblComprobantes.getColumnModel().getColumn(columna).setMaxWidth(ancho);
    }

    private void refrescarResolucionesEnDocumentos() {
        instancias.getFactura().actualizarTablaResoluciones();
        instancias.getIngresos().actualizarTablaResoluciones();
    }

    private int idResolucionDeLaFila(int fila) {
        return Integer.parseInt(valorCeldaResolucion(fila, 0));
    }

    private boolean esResolucionActiva(int fila) {
        return DaoResoluciones.ESTADO_ACTIVA.equals(valorCeldaResolucion(fila, 11));
    }

    private String valorCeldaResolucion(int fila, int columna) {
        Object valor = tblComprobantes.getValueAt(fila, columna);
        return valor == null ? "" : valor.toString();
    }

    private void actualizarBotonEstadoResolucion() {
        int fila = tblComprobantes.getSelectedRow();
        boolean inactiva = fila >= 0 && !esResolucionActiva(fila);
        btnCambiarEstadoResolucion.setText(inactiva ? "Activar resolución" : "Inactivar resolución");
    }

    // ---------------- Bodegas: cambios pendientes ----------------
    // Agregar, modificar e inactivar solo cambian la tabla; se escriben en la
    // base de datos al GUARDAR la maestra (guardarCambiosBodegas).
    private static final String PREFIJO_BODEGA_NUEVA = "NUEVA-";

    /**
     * Bodegas nuevas (código temporal NUEVA-n) o modificadas, por código.
     */
    private final java.util.Map<String, ModeloBodega> bodegasPendientes = new java.util.LinkedHashMap<>();
    private int siguienteBodegaTemporal = 1;

    private void capturarBodega(int fila) {
        VistaBodegaContacto modal = new VistaBodegaContacto(instancias.getMenu(), true);
        modal.preparar(fila < 0 ? null : bodegaDeLaFila(fila), nombresBodegasRegistradas(fila), "", Bodegas.TIPO_INTERNA);
        modal.setVisible(true);

        if (!modal.isAceptado()) {
            return;
        }

        ModeloBodega bodega = modal.getBodega();
        DefaultTableModel modelo = (DefaultTableModel) tblBodegasInternas.getModel();

        if (fila < 0) {
            bodega.setCodigo(PREFIJO_BODEGA_NUEVA + siguienteBodegaTemporal++);
            bodega.setEstado(Bodegas.ESTADO_ACTIVO);
            modelo.addRow(new Object[]{bodega.getCodigo(), bodega.getNombre(), Bodegas.etiqueta(true)});
            fila = modelo.getRowCount() - 1;
        } else {
            modelo.setValueAt(bodega.getNombre(), fila, 1);
        }
        bodegasPendientes.put(valorCeldaBodega(fila, 0), bodegaDeLaFila(fila));

        tblBodegasInternas.setRowSelectionInterval(fila, fila);
        actualizarBotonEstadoBodega();
    }

    private boolean esBodegaNueva(String codigo) {
        return codigo.startsWith(PREFIJO_BODEGA_NUEVA);
    }

    private boolean hayCambiosBodegas() {
        int indice = cmbBodegas.getSelectedIndex();
        boolean cambioPredeterminada = indice >= 0 && indice < bodegasCombo.size()
                && !bodegasCombo.get(indice).getCodigo().equals(Bodegas.predeterminada());
        return !bodegasPendientes.isEmpty() || cambioPredeterminada;
    }

    /**
     * Escribe las bodegas pendientes y la bodega por defecto.
     */
    private boolean guardarCambiosBodegas() {
        if (!bodegasPendientes.isEmpty()) {
            java.util.List<ModeloBodega> bodegas = new java.util.ArrayList<>();
            for (ModeloBodega pendiente : bodegasPendientes.values()) {
                // Validación contra la base de datos por si otra terminal
                // registró el mismo nombre mientras tanto
                String codigoActual = esBodegaNueva(pendiente.getCodigo()) ? "" : pendiente.getCodigo();
                if (daoBodegas.existeNombre(pendiente.getNombre(), Bodegas.TIPO_INTERNA, codigoActual)) {
                    ControladorAlertas.alertFail("Ya existe una bodega con el nombre " + pendiente.getNombre());
                    return false;
                }

                ModeloBodega bodega = new ModeloBodega(codigoActual, "", pendiente.getNombre(), Bodegas.TIPO_INTERNA,
                        pendiente.getEstado(), instancias.getUsuario());
                bodegas.add(bodega);
            }
            if (!daoBodegas.guardarCambiosInternas(bodegas)) {
                ControladorAlertas.alertFail("No fue posible guardar las bodegas");
                return false;
            }
        }

        return guardarBodegaPredeterminada();
    }

    private void cambiarEstadoBodegaSeleccionada() {
        int fila = tblBodegasInternas.getSelectedRow();

        if (fila < 0) {
            ControladorAlertas.alert("Seleccione una bodega");
            return;
        }

        ModeloBodega bodega = bodegaDeLaFila(fila);

        if (bodega.esPrincipal()) {
            ControladorAlertas.alertFail("La bodega principal no se puede inactivar");
            return;
        }

        boolean activar = !bodega.estaActiva();

        // Si se inactivara la bodega con la que trabaja el sistema, el inventario quedaria apuntando a una bodega muerta
        int indicePredeterminada = cmbBodegas.getSelectedIndex();
        boolean esPredeterminadaElegida = indicePredeterminada >= 0 && indicePredeterminada < bodegasCombo.size()
                && bodegasCombo.get(indicePredeterminada).getCodigo().equals(bodega.getCodigo());
        if (!activar && (bodega.getCodigo().equals(Bodegas.predeterminada()) || esPredeterminadaElegida)) {
            ControladorAlertas.alertFail("La bodega " + bodega.getCodigo() + " es la predeterminada del sistema. "
                    + "Seleccione otra bodega por defecto antes de inactivarla");
            return;
        }

        if (!ControladorAlertas.option(activar ? "¿Activar la bodega " + bodega.getCodigo() + "?"
                : "¿Inactivar la bodega " + bodega.getCodigo() + "?")) {
            return;
        }

        tblBodegasInternas.setValueAt(Bodegas.etiqueta(activar), fila, 2);
        bodegasPendientes.put(bodega.getCodigo(), bodegaDeLaFila(fila));
        actualizarBotonEstadoBodega();
    }

    private void cargarBodegasInternas() {
        bodegasPendientes.clear();
        siguienteBodegaTemporal = 1;
        cargandoBodegas = true;
        try {
            DefaultTableModel modelo = (DefaultTableModel) tblBodegasInternas.getModel();
            modelo.setRowCount(0);

            bodegasCombo.clear();
            cmbBodegas.removeAllItems();

            for (ModeloBodega bodega : daoBodegas.listarInternas()) {
                modelo.addRow(new Object[]{bodega.getCodigo(), bodega.getNombre(), Bodegas.etiqueta(bodega.estaActiva())});

                // Una bodega inactiva no se puede elegir como predeterminada
                if (bodega.estaActiva()) {
                    bodegasCombo.add(bodega);
                    cmbBodegas.addItem(bodega.getNombre());
                }
            }

            seleccionarBodegaPredeterminada(Bodegas.predeterminada());
        } finally {
            cargandoBodegas = false;
        }
        actualizarBotonEstadoBodega();
    }

    private void seleccionarBodegaPredeterminada(String codigo) {
        for (int i = 0; i < bodegasCombo.size(); i++) {
            if (bodegasCombo.get(i).getCodigo().equalsIgnoreCase(codigo)) {
                cmbBodegas.setSelectedIndex(i);
                return;
            }
        }
        cmbBodegas.setSelectedIndex(bodegasCombo.isEmpty() ? -1 : 0);
    }

    /**
     * Guarda la bodega elegida en "Bodega por defecto" (se llama al GUARDAR la
     * maestra) y refresca la maestra en memoria, para que el resto del sistema
     * empiece a mover inventario sobre ella sin reiniciar.
     */
    private boolean guardarBodegaPredeterminada() {
        int indice = cmbBodegas.getSelectedIndex();

        if (indice < 0 || indice >= bodegasCombo.size()) {
            return true;
        }

        String codigo = bodegasCombo.get(indice).getCodigo();

        if (codigo.equals(Bodegas.predeterminada())) {
            return true;
        }

        if (!new DaoMaestra().actualizarBodega(codigo)) {
            ControladorAlertas.alertFail("No fue posible guardar la bodega predeterminada");
            return false;
        }

        DaoMaestra _daoM = new DaoMaestra();
        DatosMaestra.setearDatosMaestra(_daoM.obtenerGeneral(), _daoM.obtenerImpresiones());
        return true;
    }

    /**
     * La columna de estado muestra una etiqueta, pero el modelo viaja con el
     * 1/0 que guarda la base de datos.
     */
    private ModeloBodega bodegaDeLaFila(int fila) {
        return new ModeloBodega(valorCeldaBodega(fila, 0), "", valorCeldaBodega(fila, 1),
                Bodegas.TIPO_INTERNA, Bodegas.estado(valorCeldaBodega(fila, 2)), instancias.getUsuario());
    }

    private List<String> nombresBodegasRegistradas(int filaOmitida) {
        List<String> nombres = new ArrayList<>();

        for (int fila = 0; fila < tblBodegasInternas.getRowCount(); fila++) {
            if (fila != filaOmitida) {
                nombres.add(valorCeldaBodega(fila, 1));
            }
        }
        return nombres;
    }

    private String valorCeldaBodega(int fila, int columna) {
        Object valor = tblBodegasInternas.getValueAt(fila, columna);
        return valor == null ? "" : valor.toString();
    }

    private void actualizarBotonEstadoBodega() {
        int fila = tblBodegasInternas.getSelectedRow();
        boolean inactiva = fila >= 0 && !Bodegas.esEstadoActivo(valorCeldaBodega(fila, 2));
        btnCambiarEstadoBodega.setText(inactiva ? "Activar bodega" : "Inactivar bodega");
    }

    public void cargarProducto(String codigo) {
        ModeloProducto nodo = null;

        String CodigoProd = "";
        if (codigo.equals("")) {
            CodigoProd = "";
        } else {
            nodo = daoProducto.obtenerDatosProducto(codigo, Bodegas.predeterminada());
            if (nodo.getIdSistema() != null) {
                CodigoProd = nodo.getIdSistema();
            }
        }

        if (!CodigoProd.equals("")) {
            txtValor1.setText(big.setMoneda(big.getBigDecimal(nodo.getL1())));
            valor = txtValor1.getText();
            txtaCobrar1.setText(nodo.getDescripcion());
            this.idProductoOrdenServicio = nodo.getIdSistema();
            txtaCobrar.setText(nodo.getCodigo());
        } else {
            ventanaProductos(codigo);
        }
    }

    public void cargarProducto1(String codigo) {

        ModeloProducto nodo = null;

        String CodigoProd = "";
        if (codigo.equals("")) {
            CodigoProd = "";
        } else {
            nodo = daoProducto.obtenerDatosProducto(codigo, Bodegas.predeterminada());
            if (nodo.getIdSistema() != null) {
                CodigoProd = nodo.getIdSistema();
            }
        }

        if (!CodigoProd.equals("")) {
            for (int i = 0; i < tblConfig.getRowCount(); i++) {
                if (nodo.getIdSistema().equals(tblConfig.getValueAt(i, 0))) {
                    ControladorAlertas.alert("Este servicio ya esta cargado");
                    txtCodProducto.setText("");
                    return;
                }
            }

            if (instancias.getSql().getProdActivo(nodo.getCodigo())) {
                ControladorAlertas.alertFail("Este producto esta inactivo");
                btnGuardarMaestra.requestFocus();
                return;
            }

            DefaultTableModel config = (DefaultTableModel) tblConfig.getModel();

            config.addRow(new Object[]{codigo, nodo.getDescripcion(), big.setMonedaExacta(big.getBigDecimal(nodo.getL1())),
                "10", "10", this.simbolo + " 0", "8", this.simbolo + " 0", "8", this.simbolo + " 0", "24", this.simbolo + " 0", false, false, "20", "8"});
            txtCodProducto.setText("");
            tblConfig.scrollRectToVisible(tblConfig.getCellRect(tblConfig.getRowCount() - 1, 0, true));
            tblConfig.setColumnSelectionInterval(0, 0);
            tblConfig.setRowSelectionInterval(config.getRowCount() - 1, config.getRowCount() - 1);

            return;
        }
        ventanaProductos1(codigo);
    }

    public void ventanaProductos1(String codigo) {
        VistaBuscadorProductos buscar = new VistaBuscadorProductos(null, true, false, "parqueadero");
        buscar.setLocationRelativeTo(null);
        instancias.setBusProductos(buscar);
        instancias.setCampoActual(txtCodProducto);
        txtCodProducto.requestFocus();
        buscar.noEncontrado(codigo.replace("'", "//"));
        buscar.show();
    }

    public void ventanaProductos(String codigo) {
        VistaBuscadorProductos buscar = new VistaBuscadorProductos(null, rootPaneCheckingEnabled, false, "");
        buscar.setLocationRelativeTo(null);
        instancias.setBusProductos(buscar);
        instancias.setCampoActual(txtaCobrar);
        txtaCobrar.requestFocus();
        buscar.noEncontrado(codigo.replace("'", "//"));
        buscar.show();
    }

    public void cargarFila1() {
        DefaultTableModel model = (DefaultTableModel) tblCascos.getModel();

        int filas;

        try {
            filas = Integer.parseInt(txtFilas1.getText());
        } catch (Exception e) {
            ControladorAlertas.alert("Ingrese la cantidad de filas");
            return;
        }

        while (tblCascos.getRowCount() > 0) {
            model.removeRow(0);
        }

        for (int i = 1; i <= filas; i++) {
            model.addRow(new Object[]{"A" + i, "B" + i, "C" + i, "D" + i, "E" + i, "F" + i, "G" + i, "H" + i, "I" + i, "J" + i, "K" + i, "L" + i, "M" + i, "N" + i, "O" + i, "P" + i, "Q" + i, "R" + i, "S" + i, "T" + i, "U" + i, "V" + i, "W" + i, "X" + i, "Y" + i, "Z" + i});
        }
    }

    public void cargarColumna1() {
        DefaultTableModel model = (DefaultTableModel) tblCascos.getModel();

        int columnas;
        try {
            columnas = Integer.parseInt(txtColumnas1.getText());
        } catch (Exception e) {
            ControladorAlertas.alert("Ingrese la cantidad de columnas");
            return;
        }

        if (columnas > 26) {
            ControladorAlertas.alertFail("Son 26 columnas como máximo.");
            txtColumnas1.setText("");
            return;
        }

        tblCascos.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        tblCascos.setRowHeight(20);
        tblCascos.getTableHeader().setResizingAllowed(false);
        tblCascos.getTableHeader().setReorderingAllowed(false);
        jScrollPane13.setViewportView(tblCascos);

        for (int i = columnas; i < 26; i++) {
            if (tblCascos.getColumnModel().getColumnCount() > 0) {
                tblCascos.getColumnModel().getColumn(i).setMinWidth(0);
                tblCascos.getColumnModel().getColumn(i).setPreferredWidth(0);
                tblCascos.getColumnModel().getColumn(i).setMaxWidth(0);
            }
        }
    }

    public void cargarColumna() {
        // sin efecto: el panel se genera con btnGenerarTabla
    }

    public void cargarFila() {
        // sin efecto: el panel se genera con btnGenerarTabla
    }

    public void llenarTablaServicios() {

        Object[][] datos = instancias.getSql().getDatosConfig();
        Object[][] datos1 = instancias.getSql().getDatosMaestraParqueadero();

        DefaultTableModel model = (DefaultTableModel) tblConfig.getModel();

        while (tblConfig.getRowCount() > 0) {
            model.removeRow(0);
        }

        if (datos.length > 0) {
            for (int i = 0; i < datos.length; i++) {

                model.addRow(new Object[]{"", "", "",
                    "0", "0", this.simbolo + " 0", "0", this.simbolo + " 0", "0", this.simbolo + " 0", "0", this.simbolo + " 0", false, false, "0", "0"});

                tblConfig.setValueAt(datos[i][0], i, 0);
                tblConfig.setValueAt(datos[i][1], i, 1);
                tblConfig.setValueAt(big.setMoneda(big.getBigDecimal(datos[i][2])), i, 2);
                tblConfig.setValueAt(datos[i][3], i, 3);
                tblConfig.setValueAt(datos[i][4], i, 4);
                tblConfig.setValueAt(big.setMoneda(big.getBigDecimal(datos[i][5])), i, 5);
                tblConfig.setValueAt(datos[i][6], i, 6);
                tblConfig.setValueAt(big.setMoneda(big.getBigDecimal(datos[i][7])), i, 7);
                tblConfig.setValueAt(datos[i][8], i, 8);
                tblConfig.setValueAt(big.setMoneda(big.getBigDecimal(datos[i][9])), i, 9);
                tblConfig.setValueAt(datos[i][10], i, 10);
                tblConfig.setValueAt(big.setMoneda(big.getBigDecimal(datos[i][11])), i, 11);

                if (datos[i][12].toString().equals("1")) {
                    tblConfig.setValueAt(true, i, 12);
                } else {
                    tblConfig.setValueAt(false, i, 12);
                }

                if (datos[i][13].toString().equals("1")) {
                    tblConfig.setValueAt(true, i, 13);
                } else {
                    tblConfig.setValueAt(false, i, 13);
                }

                tblConfig.setValueAt(datos[i][14], i, 14);
                tblConfig.setValueAt(datos[i][15], i, 15);
            }

            if (datos1.length > 0) {

                txtColumnas1.setText(datos1[0][1].toString());
                cargarColumna1();
                txtFilas1.setText(datos1[0][0].toString());
                cargarFila1();

                String[] inicioDelDia = datos1[0][3].toString().split(":");
                String[] finDelDia = datos1[0][4].toString().split(":");
                String[] inicioDeLaNoche = datos1[0][5].toString().split(":");
                String[] finDeLaNoche = datos1[0][6].toString().split(":");

                if (datos1[0][2].equals("1")) {
                    rbtSi.setSelected(true);
                    rbtNo.setSelected(false);
                } else {
                    rbtSi.setSelected(false);
                    rbtNo.setSelected(true);
                }

                if (datos1[0][7].equals("1")) {
                    rbtSi1.setSelected(true);
                    rbtNo1.setSelected(false);
                } else {
                    rbtSi1.setSelected(false);
                    rbtNo1.setSelected(true);
                }

                if (datos1[0][8].equals("1")) {
                    rbtSi2.setSelected(true);
                    rbtNo2.setSelected(false);
                } else {
                    rbtSi2.setSelected(false);
                    rbtNo2.setSelected(true);
                }

                if (datos1[0][11].equals("1")) {
                    rbtSiModificarValor.setSelected(true);
                    rbtNoModificarValor.setSelected(false);
                } else {
                    rbtSiModificarValor.setSelected(false);
                    rbtNoModificarValor.setSelected(true);
                }

                if (datos1[0][9].equals("1")) {
                    rbtSi3.setSelected(true);
                    txtCosto.setEnabled(true);
                    rbtNo3.setSelected(false);
                } else {
                    rbtSi3.setSelected(false);
                    rbtNo3.setSelected(true);
                }

                try {
                    txtCosto.setText(big.setMoneda(big.getBigDecimal(datos1[0][10].toString())));
                } catch (Exception e) {
                    txtCosto.setText(big.setNumero(BigDecimal.ZERO));
                }
            }
        }
    }

    public void actualizarTurno() {
        setFilaTabla(tblConfiguraciones1, "Número de turno", 1, turno);
        aplicarEstadoNumeroTurno();
    }

    public void llenarDatos() {
        DaoMaestra _lDao = new DaoMaestra();
        DatosMaestra.setearDatosMaestra(_lDao.obtenerGeneral(), _lDao.obtenerImpresiones());

        inicializarTablaConfiguracionesFacturacion();
        inicializarTablaConfiguraciones1(instancias.getConfiguraciones().isRestaurante(), instancias.getConfiguraciones().isCreditos());

        // Tabla independiente: no la reinicia inicializarTablaInformacionEmpresa()
        cargarResponsabilidadesFiscales(DatosMaestra.getResponsabilidadesRaw());

        setFilaTabla(tblConfiguraciones, OPCION_SOLO_MESAS_MESEROS, 1, DatosMaestra.isSoloMesas());
        setFilaTabla(tblConfiguraciones, OPCION_FACTURAR_MESAS_MESEROS, 1, DatosMaestra.isFacturarMesas());

        // Cargar tblImpresoraPorTipo
        inicializarTablaImpresoraPorTipo();
        setFilaTabla(tblImpresoraPorTipo, "Carta", 1, DatosMaestra.getImpresoraCarta());
        setFilaTabla(tblImpresoraPorTipo, "Media carta", 1, DatosMaestra.getImpresoraMediaCarta());
        setFilaTabla(tblImpresoraPorTipo, "Pos", 1, DatosMaestra.getImpresoraPos());
        setFilaTabla(tblImpresoraPorTipo, "Comanda", 1, DatosMaestra.getImpresoraComanda());
        setFilaTabla(tblImpresoraPorTipo, "Prefactura", 1, DatosMaestra.getImpresoraPrefactura());

        if ("pos".equals(DatosMaestra.getTipoImpresionPredeterminado())) {
            setFilaTabla(tblImpresoraPorTipo, "Pos", 2, true);
        } else if ("facturaCompleta".equals(DatosMaestra.getTipoImpresionPredeterminado())) {
            setFilaTabla(tblImpresoraPorTipo, "Carta", 2, true);
        } else {
            setFilaTabla(tblImpresoraPorTipo, "Media carta", 2, true);
        }

        setFilaTabla(tblOpcionesImpresiones, OPCION_IMPRESION_POR_GRUPO, 1, DatosMaestra.isImpresionPorGrupo());

        setFilaTabla(tblConfiguraciones1, OPCION_FOCO_PRODUCTO, 1, DatosMaestra.getFocoDespuesDeCargarProducto());

        setFilaTabla(tblConfiguraciones, "Validar el cupo del credito", 1, DatosMaestra.isValidarCupoCredito());
        setFilaTabla(tblConfiguraciones, "Días de plazo automaticos", 1, DatosMaestra.isCargarDiasPlazoAutomaticamente());

        setFilaTabla(tblConfiguraciones, "Módulo de solicitudes de permisos", 1, DatosMaestra.isModuloPermisos());

        cmbHoraInicio1.setSelectedItem(DatosMaestra.getHoraInicioHospitalizacion());
        cmbHoraFin1.setSelectedItem(DatosMaestra.getHoraFinHospitalizacion());
        cmbIntervalo1.setSelectedItem(DatosMaestra.getIntervaloHospitalizacion());

        setFilaTabla(tblConfiguraciones, "Mostrar devuelta en factura", 1, DatosMaestra.isMostrarDevuelta());

        if (instancias.getConfiguraciones().isRestaurante()) {
            setFilaTabla(tblConfiguraciones1, "Porcentaje de propina en pre-factura", 1, DatosMaestra.getPorcentajePropina());
        }
        txtNombreFormato.setText(DatosMaestra.getFormatoImpresion());

        String columnas = DatosMaestra.getColumnas();
        txtColumnas.setText(columnas);

        String filas = DatosMaestra.getFilas();
        txtFilas.setText(filas);

        if (!columnas.equals("") && !filas.equals("")) {
            btnGenerarTablaActionPerformed(null);
        }

        if (!instancias.getConfiguraciones().isParqueadero()) {
            quitarPestana(pnlParqueadero);
        }

        if (!instancias.getConfiguraciones().isVeterinaria()) {
            quitarPestana(pnlVeterinario);
        }

        if (instancias.getConfiguraciones().isRestaurante()) {
            Object[][] mesas = instancias.getSql().getPosicionesMesas();
            txtNumMesas.setText(String.valueOf(mesas.length));
            panelDisenoMesas.cargarMesasExistentes(mesas);
        } else {
            quitarPestana(pnlRestaurante);
        }

        cmbDoctores.removeAllItems();
        cmbDoctores1.removeAllItems();
        if (instancias.getConfiguraciones().isAgenda()) {
            cmbDoctores.addItem("Seleccione el usuario...");
            cmbDoctores1.addItem("Seleccione el usuario...");
            Object[][] empleados2 = instancias.getSql().getEmpleadosNombreAgenda();
            for (int i = 0; i < empleados2.length; i++) {
                cmbDoctores.addItem(empleados2[i][0]);
                cmbDoctores1.addItem(empleados2[i][1]);
            }
            cmbDoctores.addItem("TODOS");
            cmbDoctores1.addItem("");
        } else {
            quitarPestana(pnlAgenda);
        }

        if (instancias.getConfiguraciones().isLaboratorio()) {
            Object[][] examenes = instancias.getSql().getExamenesLaboratorio();
            for (int i = 0; i < examenes.length; i++) {
                tblExamenes.setValueAt(examenes[i][0], i, 0);
                tblExamenes.setValueAt(examenes[i][1], i, 1);
                tblExamenes.setValueAt(big.setMoneda(big.getMoneda(examenes[i][2].toString())), i, 2);
                tblExamenes.setValueAt(examenes[i][3], i, 3);
            }
        } else {
            quitarPestana(pnlLaboratorio);
        }

        Object[][] grupos = instancias.getSql().getGruposFactura();
        Object[][] impresoras = instancias.getSql().getImpresoras();
        Object[][] horasBloqueo = instancias.getSql().getHorasBloqueo();

        DefaultTableModel model = (DefaultTableModel) tblImpresoras1.getModel();
        DefaultTableModel modelo1 = (DefaultTableModel) tblDiasBloqueo.getModel();
        DefaultTableModel modelo2 = (DefaultTableModel) tblHoras.getModel();

        while (tblDiasBloqueo.getRowCount() > 0) {
            modelo1.removeRow(0);
        }

        // CARGAR FECHAS EN LA TABLA
        String x, y;
        int horaIni = 6;
        int horaFin = 20;
        int minInic = 00;
        int intervalo = 10;
        int contador = 0;

        try {
            horaIni = Integer.parseInt(DatosMaestra.getHoraInicioAgenda());
            horaFin = Integer.parseInt(DatosMaestra.getHoraFinAgenda());
            intervalo = Integer.parseInt(DatosMaestra.getIntervaloAgenda());
        } catch (Exception e) {
        }

        while (horaIni < horaFin) {
            if (minInic == 0) {
                x = horaIni + ":" + minInic + "0";
            } else {
                x = horaIni + ":" + minInic;
            }

            Object[] titulosTabla = new Object[tblHoras.getColumnCount()];
            modelo2.addRow(new Object[]{x, false});

            contador = contador + intervalo;
            if (contador > 59) {
                horaIni = horaIni + 1;
                contador = contador - 60;
                minInic = contador;
            } else {
                minInic = contador;
            }
        }
        // FIN 

        if (horasBloqueo.length > 0) {

            for (int i = 0; i < horasBloqueo.length; i++) {
                modelo1.addRow(new Object[]{horasBloqueo[i][0], horasBloqueo[i][4], horasBloqueo[i][1], horasBloqueo[i][2], horasBloqueo[i][3]});
            }

            for (int i = 0; i < horasBloqueo.length; i++) {
                tblDiasBloqueo.setValueAt(metodos.fecha(tblDiasBloqueo.getValueAt(i, 0).toString()), i, 0);
            }
        }

        while (tblImpresoras1.getRowCount() > 0) {
            model.removeRow(0);
        }

        if (grupos.length > 0) {
            for (int i = 0; i < grupos.length + 1; i++) {
                model.addRow(new Object[]{"", "", "", "", "", ""});
            }
            for (int i = 0; i < grupos.length; i++) {
                tblImpresoras1.setValueAt(grupos[i][0] + ". " + grupos[i][1], i + 1, 0);
            }
        }

        if (tblImpresoras1.getRowCount() > 0) {
            tblImpresoras1.setValueAt("IMP. PREDETERMINADA", 0, 1);
            if (impresoras.length > 0) {
                for (int i = 1; i < impresoras.length; i++) {
                    tblImpresoras1.setValueAt(impresoras[i][0], 0, i + 1);
                }
            }
        }

        for (int i = 0; i < grupos.length; i++) {
            try {
                if (grupos[i][3] == null || grupos[i][3].equals("")) {
                    tblImpresoras1.setValueAt("SELECCIONADO", i + 1, 1);
                } else {
                    for (int j = 1; j < tblImpresoras1.getColumnCount(); j++) {
                        if (tblImpresoras1.getValueAt(0, j).equals(grupos[i][3])) {
                            tblImpresoras1.setValueAt("SELECCIONADO", i + 1, j);
                        }
                    }
                }
            } catch (Exception e) {
            }
        }

        /* FIN ANEXO */
        setFilaTabla(tblConfiguraciones1, "Cantidad al cargar un producto", 1, DatosMaestra.getCantidadEstablecidaAlCargar());
        setFilaTabla(tblConfiguraciones1, "Número de turno", 1, DatosMaestra.getNumeroTurno());

        setFilaTabla(tblOpcionesImpresiones, "Imprimir impoconsumo en factura", 1, DatosMaestra.isImprimirImpoconsumo());
        setFilaTabla(tblOpcionesImpresiones, "Imprimir retenciones en factura", 1, DatosMaestra.isImprimirRetenciones());
        setFilaTabla(tblOpcionesImpresiones, "Imprimir hora en factura", 1, DatosMaestra.isImprimirHora());

        setFilaTabla(tblConfiguraciones, "Facturar sin inventario", 1, DatosMaestra.isFacturarSinInventario());

        // Cargar tblImpresionDocumentos
        inicializarTablaImpresionDocumentos();
        setFilaTabla(tblImpresionDocumentos, "Factura", 1, DatosMaestra.isPrevisualizarFactura());
        setFilaTabla(tblImpresionDocumentos, "Factura", 2, DatosMaestra.isPreguntarCopiasFactura());
        setFilaTabla(tblImpresionDocumentos, "Factura", 3, DatosMaestra.getNumeroCopiasFactura());

        setFilaTabla(tblImpresionDocumentos, "Orden de servicio", 1, DatosMaestra.isPrevisualizarOrdenServicio());
        setFilaTabla(tblImpresionDocumentos, "Orden de servicio", 2, DatosMaestra.isPreguntarCopiasOrdenServicio());
        setFilaTabla(tblImpresionDocumentos, "Orden de servicio", 3, DatosMaestra.getNumeroCopiasOrdenServicio());

        setFilaTabla(tblImpresionDocumentos, "Cotización", 1, DatosMaestra.isPrevisualizarCotizacion());
        setFilaTabla(tblImpresionDocumentos, "Cotización", 2, DatosMaestra.isPreguntarCopiasCotizacion());
        setFilaTabla(tblImpresionDocumentos, "Cotización", 3, DatosMaestra.getNumeroCopiasCotizacion());

        setFilaTabla(tblImpresionDocumentos, "Plan separe", 1, DatosMaestra.isPrevisualizarPlanSepare());
        setFilaTabla(tblImpresionDocumentos, "Plan separe", 2, DatosMaestra.isPreguntarCopiasPlanSepare());
        setFilaTabla(tblImpresionDocumentos, "Plan separe", 3, DatosMaestra.getNumeroCopiasPlanSepare());

        setFilaTabla(tblImpresionDocumentos, "Pedido", 1, DatosMaestra.isPrevisualizarPedido());
        setFilaTabla(tblImpresionDocumentos, "Pedido", 2, DatosMaestra.isPreguntarCopiasPedido());
        setFilaTabla(tblImpresionDocumentos, "Pedido", 3, DatosMaestra.getNumeroCopiasPedido());

        setFilaTabla(tblImpresionDocumentos, "Comanda", 1, DatosMaestra.isPrevisualizarComanda());
        setFilaTabla(tblImpresionDocumentos, "Comanda", 2, DatosMaestra.isPreguntarCopiasComanda());
        setFilaTabla(tblImpresionDocumentos, "Comanda", 3, DatosMaestra.getNumeroCopiasComanda());

        setFilaTabla(tblImpresionDocumentos, "Prefactura", 1, DatosMaestra.isPrevisualizarPrefactura());
        setFilaTabla(tblImpresionDocumentos, "Prefactura", 2, DatosMaestra.isPreguntarCopiasPrefactura());
        setFilaTabla(tblImpresionDocumentos, "Prefactura", 3, DatosMaestra.getNumeroCopiasPrefactura());

        String nombreBorrarDocumento = instancias.getConfiguraciones().isRestaurante() ? "Borrar mesas" : "Borrar congeladas";
        setFilaTabla(tblConfiguraciones, nombreBorrarDocumento, 1, DatosMaestra.isBorrarMesas());

        String nombreBorrarProductos = instancias.getConfiguraciones().isRestaurante() ? "Borrar productos en mesas" : "Borrar productos en congeladas";
        setFilaTabla(tblConfiguraciones, nombreBorrarProductos, 1, DatosMaestra.isBorrarProductos());

        setFilaTabla(tblConfiguraciones, OPCION_MODIFICAR_PRECIO, 1, DatosMaestra.isModificarPrecio());
        aplicarEstadoFocoProducto();
        setFilaTabla(tblConfiguraciones, OPCION_CONSECUTIVO_ADICIONAL, 1, DatosMaestra.isConsecutivoAdicional());
        setFilaTabla(tblConfiguraciones, "Activar turnos", 1, DatosMaestra.isTurnoActivo());
        aplicarEstadoNumeroTurno();
        setFilaTabla(tblConfiguraciones, "Modificar nombre de productos", 1, DatosMaestra.isModificarNombre());
        setFilaTabla(tblConfiguraciones, "Combinar productos iguales", 1, DatosMaestra.isCombinarProductos());
        setFilaTabla(tblConfiguraciones1, "Límite de productos (carta/media carta)", 1, DatosMaestra.getLimiteProductosPorFactura());

        instancias.getMenu().abrirCaja();

        instancias.getMenu().actualizarUsuario(instancias.getUsuario());

        try {
            setFilaTabla(tblConfiguraciones, "Facturar Utilidad Sobrepasada", 1, !"NO".equals(DatosMaestra.getAlertaUtilidad()));
        } catch (Exception e) {
            setFilaTabla(tblConfiguraciones, "Facturar Utilidad Sobrepasada", 1, true);
        }

        setFilaTabla(tblConfiguraciones1, "Tipo de descuento", 1, DatosMaestra.getTipoDescuento());

        setFilaTabla(tblConfiguraciones1, "Contraseña para control y permisos", 1, DatosMaestra.getContrasenaPermisos());

        inicializarTablaInformacionEmpresa();
        setFilaTabla(tblInformacionEmpresa, "Nombre", 1, DatosMaestra.getNombreEmpresa());
        setFilaTabla(tblInformacionEmpresa, "NIT", 1, DatosMaestra.getNitEmpresa());
        setFilaTabla(tblInformacionEmpresa, "Teléfono", 1, DatosMaestra.getTelefonoEmpresa());
        setFilaTabla(tblInformacionEmpresa, "Régimen", 1, DatosMaestra.getRegimenEmpresa());
        setFilaTabla(tblInformacionEmpresa, "Dirección", 1, DatosMaestra.getDireccionEmpresa());
        inicializarTablaInformacionImpresion();
        setFilaTabla(tblInformacionImpresion, "Pie de página", 1, DatosMaestra.getPiePagina());
        setFilaTabla(tblInformacionImpresion, "Información legal", 1, DatosMaestra.getInformacionLegal());
        setFilaTabla(tblInformacionImpresion, "Anexo factura", 1, DatosMaestra.getAnexoFactura());
        setFilaTabla(tblInformacionImpresion, "Anexo orden de servicio", 1, DatosMaestra.getAnexoOrdenServicio());
        javax.swing.SwingUtilities.invokeLater(() -> ajustarAlturaFilas(tblInformacionImpresion));

        try {
            setFilaTabla(tblOpcionesImpresiones, "Imprimir ubicación en factura", 1, !"NO".equals(DatosMaestra.getMostrarUbicacion()));
        } catch (Exception e) {
            setFilaTabla(tblOpcionesImpresiones, "Imprimir ubicación en factura", 1, true);
        }

        setFilaTabla(tblConfiguraciones1, "Días para alerta de cartera", 1, DatosMaestra.getDiasAlertaCuadre());

        setFilaTabla(tblOpcionesImpresiones, "Imprimir recodiga en cuadre de caja", 1, DatosMaestra.isImprimirRecogida());
        setFilaTabla(tblConfiguraciones, OPCION_USAR_LECTOR, 1, DatosMaestra.isUsarLector());
        setFilaTabla(tblConfiguraciones1, "Título en la factura", 1, DatosMaestra.getTituloFactura());
        setFilaTabla(tblConfiguraciones, "Precios con impuestos", 1, DatosMaestra.isPreciosConImpuestos());
        setFilaTabla(tblConfiguraciones, "Ventas Contado Predeterminado", 1, DatosMaestra.isVentasContadoPredeterminado());
        setFilaTabla(tblConfiguraciones, "Alertar Utilidad Sobrepasada", 1, DatosMaestra.isAlertarUtilidadSobrepasada());

        String diasCobrarMora = DatosMaestra.getDiasCobrarMora();
        String porcentajeMora = DatosMaestra.getPorcentajeMora();

        if (instancias.getConfiguraciones().isCreditos()) {
            setFilaTabla(tblConfiguraciones1, "Días para cobrar mora en créditos", 1, diasCobrarMora);
            setFilaTabla(tblConfiguraciones1, "Porcentaje de mora en créditos", 1, porcentajeMora);
        }

        if (DatosMaestra.isGeneraOrdenMedica()) {
            rdSiGenerarOrden.setSelected(true);
        } else {
            rdNoGenerarOrden.setSelected(true);
        }
        if (DatosMaestra.isImprimirOrdenMedica()) {
            rdSiImprimirOrden.setSelected(true);
        } else {
            rdNoImprimirOrden.setSelected(true);
        }

        if (instancias.getConfiguraciones().isMedico() || instancias.getConfiguraciones().isLaboratorio() || instancias.getConfiguraciones().isOftalmologia()) {
            try {

                ndCamposOrdenPredeterminada nodo = instancias.getSql().getDatosCamposOrdenPredeterminada("1");

                if (nodo.getTipo().equalsIgnoreCase("consulta")) {
                    rdConsulta.setSelected(true);
                } else {
                    rdProcedimiento.setSelected(true);
                }

                cmbConcepto.setSelectedItem(nodo.getConcepto());
                cmbFinalidad.setSelectedItem(nodo.getFinalidad());
                cmbCausa.setSelectedItem(nodo.getCausaExterna());
                cmbPersonal.setSelectedItem(nodo.getPersonal());
                cmbActoQuirurgico.setSelectedItem(actoQuirurgicoCod(nodo.getActoQuirurgico()));
                cmbForma.setSelectedItem(nodo.getForma());
                cmbAmbito.setSelectedItem(nodo.getAmbito());
                cmbCodigoConsulta.setSelectedItem(nodo.getConsulta());
                txtaCobrar.setText(nodo.getServicio());

                if (!rdSiGenerarOrden.isSelected()) {
                    cargarProducto(txtaCobrar.getText());
                }

            } catch (Exception e) {
                System.out.println("No ha predeterminado campos de la orden");
            }
        } else {
            quitarPestana(pnlMedico);
        }

        if (DatosMaestra.isImprimirFacturaOrdenMedica()) {
            rdSiImprimirFactura.setSelected(true);
        } else {
            rdNoImprimirFactura.setSelected(true);
        }

        cmbHoraInicio.setSelectedItem(DatosMaestra.getHoraInicioAgenda());
        cmbHoraFin.setSelectedItem(DatosMaestra.getHoraFinAgenda());
        cmbIntervalo.setSelectedItem(DatosMaestra.getIntervaloAgenda());

        txtCodigoPrestadorServico.setText(DatosMaestra.getCodigoPrestadorServicio());

        try {
            cmbTipoPrestador.setSelectedItem(DatosMaestra.getTipoPrestadorServicio());
        } catch (Exception e) {
            cmbTipoPrestador.setSelectedIndex(0);
        }

        setFilaTabla(tblOpcionesImpresiones, "Imprimir cuadre fiscal en cuadre de caja", 1, DatosMaestra.isImprimirCuadreFiscal());

        if (!instancias.getConfiguraciones().isAgenda()) {
            pnlIntervalosAgenda.setVisible(false);
        }

        if (instancias.getConfiguraciones().isMedico() || instancias.getConfiguraciones().isOftalmologia() || instancias.getConfiguraciones().isLaboratorio()) {
            pnlOrdenMedica.setVisible(true);
        } else {
            pnlOrdenMedica.setVisible(false);
        }

        if (instancias.getConfiguraciones().isLaboratorio()) {
            pnlOrdenMedica.setVisible(true);
            jLabel6.setVisible(false);
            rdSiGenerarOrden.setVisible(false);
            rdNoGenerarOrden.setVisible(false);
        }

        // El máximo efectivo (100 si no hay) lo da DatosMaestra.getDescuentoMaximoPermitido()
        String descMax = DatosMaestra.getDescuentoMaximoVentas();
        setFilaTabla(tblConfiguraciones1, "Descuento máximo para aplicar", 1,
                descMax == null || descMax.trim().isEmpty() ? "0" : descMax.trim());
        aplicarEstadoDescuentoMaximo();

        llenarTablaServicios();

        //ACTUALIZAR CON INSTANCIAS
        //actualizarVariables();
        /*String nombre = "";
         try {
         nombre = instancias.getSql().getNombreEmpleadoUsuario(instancias.getUsuarioLog().getUsuario());
         } catch (Exception e) {
         }

         llenarVendedores(nombre);*/
        instancias.getSql().usuarioActivo("ON", instancias.getTerminal());
    }

    private String actoQuirurgicoCod(String dato) {
        String aux = "";
        switch (dato) {
            case "1":
                return "Unico o unilateral ";
            case "2":
                return "Múltiple o bilateral, misma vía, diferente especialidad";
            case "3":
                return "Múltiple o bilateral, misma vía, igual especialidad";
            case "4":
                return "Múltiple o bilateral, diferente vía, diferente especialidad";
            case "5":
                return "Múltiple o bilateral, diferente vía, igual especialidad";
        }
        return aux;
    }

    public void actualizarVariables() {
        System.out.println("Actualizando variables");

        //FACTURA
        instancias.getFactura().setTipo();

        //COTIZACION
        instancias.getCotiza().setTipo();

        //ORDEN
        instancias.getOrdenServicio().setTipo();

        //REIMPRESION
        instancias.getReimpresion().setTipo();

        //PEDIDO
        instancias.getPedido().setTipo();

        //SEPARE
        instancias.getPlanSepare().setTipo();

        //CREDITO
        instancias.getFacturaCreditos().setTipo();

        instancias.getIngresos().actualizarVariables();

        if (DatosMaestra.isVentasContadoPredeterminado()) {
            instancias.getFactura().cargar1010();
            instancias.getCotiza().cargar1010();
        }

        metodosGenerales.actualizarVariables();
    }

    private String actoQuirurgicoDesc(String dato) {
        String aux = "";
        switch (dato) {
            case "Unico o unilateral":
                return "1";
            case "Múltiple o bilateral, misma vía, diferente especialidad":
                return "2";
            case "Múltiple o bilateral, misma vía, igual especialidad":
                return "3";
            case "Múltiple o bilateral, diferente vía, diferente especialidad":
                return "4";
            case "Múltiple o bilateral, diferente vía, igual especialidad":
                return "5";
        }
        return aux;
    }

    public void llenarVendedores(String vendedor) {

        String[] vendedores;
        String[] domiciliarios;

        Object[][] vendedoresMatriz = instancias.getSql().getVendedores();
        Object[][] domiciliariosMatriz = instancias.getSql().getDomiciliarios();
        Object[][] listadoMecanicos = instancias.getSql().getMecanicos();
        Object[][] listadoTecnicos = instancias.getSql().getTecnicos();

        String[] tecnicos = new String[listadoTecnicos.length + 1];
        tecnicos[0] = "Seleccione un técnico";
        for (int i = 0; i < listadoTecnicos.length; i++) {
            tecnicos[i + 1] = listadoTecnicos[i][0].toString();
        }

        String[] mecanicos = new String[listadoMecanicos.length + 1];
        mecanicos[0] = "Seleccione un mecánico";
        for (int i = 0; i < listadoMecanicos.length; i++) {
            mecanicos[i + 1] = listadoMecanicos[i][0].toString();
        }

        Boolean existe = false;
        for (int i = 0; i < vendedoresMatriz.length; i++) {
            if (vendedor.equals(vendedoresMatriz[i][0])) {
                existe = true;
            }
        }

        if (existe) {
            vendedores = new String[1];
            for (int i = 0; i < vendedoresMatriz.length; i++) {
                if (vendedor.equals(vendedoresMatriz[i][0])) {
                    vendedores[0] = vendedoresMatriz[i][0].toString();
                }
            }
        } else {
            vendedores = new String[vendedoresMatriz.length + 1];
            vendedores[0] = "Seleccione un vendedor";
            for (int i = 0; i < vendedoresMatriz.length; i++) {
                vendedores[i + 1] = vendedoresMatriz[i][0].toString();
            }
        }

        domiciliarios = new String[domiciliariosMatriz.length];
        for (int i = 0; i < domiciliariosMatriz.length; i++) {
            domiciliarios[i] = domiciliariosMatriz[i][0].toString();
        }

        instancias.setVendedores(vendedores);

        instancias.getNc().setVendedores(vendedores);
        instancias.getNd().setVendedores(vendedores);
        instancias.getCuentaCobro().setVendedores(vendedores);
        instancias.getFactura().setVendedores(vendedores);
        instancias.getCotiza().setVendedores(vendedores);
        instancias.getFacturaCreditos().setVendedores(vendedores);
        instancias.getPedido().setVendedores(vendedores);
        instancias.getPlanSepare().setVendedores(vendedores);
        instancias.getClientes().setVendedores(vendedores);

        if (instancias.getConfiguraciones().isMedico()) {
            instancias.getPaciente().setVendedores(vendedores);
        }

        if (instancias.getConfiguraciones().isServicioAutomotor()) {
            instancias.getOrdenServicio().setVendedores(mecanicos);
        } else {
            instancias.getOrdenServicio().setVendedores(tecnicos);
        }

        try {
            instancias.getMesa1().setDomiciliarios(domiciliarios);
            instancias.getMesa1().setVendedores(vendedores);
        } catch (Exception e) {
        }

    }

    private void inicializarTablaInformacionEmpresa() {
        javax.swing.table.DefaultTableModel modelo = new javax.swing.table.DefaultTableModel(
                new String[]{"Campo", "Valor"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return col == 1;
            }
        };
        modelo.addRow(new Object[]{"Nombre", ""});
        modelo.addRow(new Object[]{"NIT", ""});
        modelo.addRow(new Object[]{"Teléfono", ""});
        modelo.addRow(new Object[]{"Régimen", ""});
        modelo.addRow(new Object[]{"Dirección", ""});

        tblInformacionEmpresa = new javax.swing.JTable(modelo);
        tblInformacionEmpresa.setFont(new java.awt.Font("Arial", 0, 16));
        tblInformacionEmpresa.setRowHeight(24);
        // Columna "Campo" con ancho fijo de 110px
        javax.swing.table.TableColumn columnaCampo = tblInformacionEmpresa.getColumnModel().getColumn(0);
        columnaCampo.setMinWidth(110);
        columnaCampo.setPreferredWidth(110);
        columnaCampo.setMaxWidth(110);

        jScrollPane19.setViewportView(tblInformacionEmpresa);
    }

    private static final String[][] RESPONSABILIDADES_FISCALES = {
        {"ZZ", "No aplica"},
        {"O-13", "Gran contribuyente"},
        {"O-15", "Autorretenedor"},
        {"O-23", "Agente de retención en el impuesto sobre las ventas"},
        {"O-47", "Régimen Simple de Tributación – SIMPLE"}
    };
    private static final String RESP_NO_APLICA = DatosMaestra.RESPONSABILIDAD_NO_APLICA;
    private static final int RESP_COL_CODIGO = 0, RESP_COL_DESCRIPCION = 1, RESP_COL_APLICA = 2;
    private boolean cargandoResponsabilidades = false;

    private void inicializarTablaResponsabilidadesFiscales() {
        final javax.swing.table.DefaultTableModel modelo = new javax.swing.table.DefaultTableModel(
                new String[]{"Código", "Responsabilidad fiscal", "Aplica"}, 0) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return col == RESP_COL_APLICA;
            }

            @Override
            public Class getColumnClass(int col) {
                return col == RESP_COL_APLICA ? Boolean.class : String.class;
            }
        };
        for (String[] resp : RESPONSABILIDADES_FISCALES) {
            modelo.addRow(new Object[]{resp[0], resp[1], Boolean.FALSE});
        }

        // "ZZ /No aplica" es excluyente con las demás y es el valor por defecto
        modelo.addTableModelListener(e -> {
            int fila = e.getFirstRow();
            if (cargandoResponsabilidades || e.getColumn() != RESP_COL_APLICA || fila < 0 || fila != e.getLastRow()) {
                return;
            }
            if (!Boolean.TRUE.equals(modelo.getValueAt(fila, RESP_COL_APLICA))) {
                // Si se desmarca la última, vuelve a "No aplica"
                marcarNoAplicaSiNingunaSeleccionada();
                return;
            }
            boolean esNoAplica = RESP_NO_APLICA.equals(modelo.getValueAt(fila, RESP_COL_CODIGO));
            for (int i = 0; i < modelo.getRowCount(); i++) {
                if (i == fila) {
                    continue;
                }
                boolean otraEsNoAplica = RESP_NO_APLICA.equals(modelo.getValueAt(i, RESP_COL_CODIGO));
                if ((esNoAplica || otraEsNoAplica) && Boolean.TRUE.equals(modelo.getValueAt(i, RESP_COL_APLICA))) {
                    modelo.setValueAt(Boolean.FALSE, i, RESP_COL_APLICA);
                }
            }
        });

        tblResponsabilidadesFiscales.setModel(modelo);
        // El código no se muestra: se quita la columna de la vista (sigue en el modelo)
        tblResponsabilidadesFiscales.removeColumn(tblResponsabilidadesFiscales.getColumnModel().getColumn(RESP_COL_CODIGO));
        javax.swing.table.TableColumn colAplica = tblResponsabilidadesFiscales.getColumnModel().getColumn(1);
        colAplica.setMinWidth(60);
        colAplica.setPreferredWidth(60);
        colAplica.setMaxWidth(70);

        // Las responsabilidades largas se muestran en varias líneas y la fila
        // toma el alto del texto (se recalcula cuando cambia el ancho)
        tblResponsabilidadesFiscales.getColumnModel().getColumn(0).setCellRenderer(crearRendererResponsabilidad());
        tblResponsabilidadesFiscales.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {
                ajustarAltoFilasResponsabilidades();
            }
        });

        // Solo aplica para facturación electrónica
        jScrollPane23.setVisible(instancias.getConfiguraciones().isFacturaElectronica());

        cargarResponsabilidadesFiscales(null);
    }

    /**
     * Dibuja el texto de la responsabilidad con ajuste de línea, usando la
     * misma fuente de la tabla.
     */
    private static javax.swing.table.TableCellRenderer crearRendererResponsabilidad() {
        final javax.swing.JTextArea area = crearAreaResponsabilidad();
        return new javax.swing.table.TableCellRenderer() {
            @Override
            public java.awt.Component getTableCellRendererComponent(javax.swing.JTable tabla, Object valor,
                    boolean seleccionada, boolean foco, int fila, int columna) {
                area.setFont(tabla.getFont());
                area.setText(valor == null ? "" : valor.toString());
                area.setBackground(seleccionada ? tabla.getSelectionBackground() : tabla.getBackground());
                area.setForeground(seleccionada ? tabla.getSelectionForeground() : tabla.getForeground());
                return area;
            }
        };
    }

    private static javax.swing.JTextArea crearAreaResponsabilidad() {
        javax.swing.JTextArea area = new javax.swing.JTextArea();
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setOpaque(true);
        area.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 2, 1, 2));
        return area;
    }

    /**
     * Ajusta el alto de cada fila al texto de "Responsabilidad fiscal" según el
     * ancho actual de la columna. Las filas de una sola línea conservan el alto
     * normal de la tabla.
     */
    private void ajustarAltoFilasResponsabilidades() {
        int ancho = tblResponsabilidadesFiscales.getColumnModel().getColumn(0).getWidth();
        if (ancho <= 0) {
            return;
        }
        int altoMinimo = tblResponsabilidadesFiscales.getRowHeight();
        javax.swing.JTextArea medida = crearAreaResponsabilidad();
        medida.setFont(tblResponsabilidadesFiscales.getFont());

        for (int fila = 0; fila < tblResponsabilidadesFiscales.getRowCount(); fila++) {
            Object valor = tblResponsabilidadesFiscales.getValueAt(fila, 0);
            medida.setText(valor == null ? "" : valor.toString());
            medida.setSize(ancho, Short.MAX_VALUE);
            int alto = Math.max(altoMinimo, medida.getPreferredSize().height + 2);
            if (tblResponsabilidadesFiscales.getRowHeight(fila) != alto) {
                tblResponsabilidadesFiscales.setRowHeight(fila, alto);
            }
        }
    }

    private void cargarResponsabilidadesFiscales(String guardadas) {
        javax.swing.table.TableModel modelo = tblResponsabilidadesFiscales.getModel();
        java.util.Set<String> codigos = new java.util.HashSet<>();
        if (guardadas != null) {
            for (String parte : guardadas.split(",")) {
                String codigo = parte.split("\\s*/")[0].trim();
                if (!codigo.isEmpty()) {
                    codigos.add(codigo.toUpperCase());
                }
            }
        }
        // ZZ junto con otras es inválido: prevalecen las demás
        if (codigos.size() > 1) {
            codigos.remove(RESP_NO_APLICA);
        }
        cargandoResponsabilidades = true;
        try {
            for (int i = 0; i < modelo.getRowCount(); i++) {
                String codigo = String.valueOf(modelo.getValueAt(i, RESP_COL_CODIGO));
                modelo.setValueAt(codigos.contains(codigo), i, RESP_COL_APLICA);
            }
        } finally {
            cargandoResponsabilidades = false;
        }
        marcarNoAplicaSiNingunaSeleccionada();
    }

    private void marcarNoAplicaSiNingunaSeleccionada() {
        javax.swing.table.TableModel modelo = tblResponsabilidadesFiscales.getModel();
        int filaNoAplica = -1;
        for (int i = 0; i < modelo.getRowCount(); i++) {
            if (Boolean.TRUE.equals(modelo.getValueAt(i, RESP_COL_APLICA))) {
                return;
            }
            if (RESP_NO_APLICA.equals(modelo.getValueAt(i, RESP_COL_CODIGO))) {
                filaNoAplica = i;
            }
        }
        if (filaNoAplica >= 0) {
            modelo.setValueAt(Boolean.TRUE, filaNoAplica, RESP_COL_APLICA);
        }
    }

    private String obtenerResponsabilidadesFiscalesSeleccionadas() {
        javax.swing.table.TableModel modelo = tblResponsabilidadesFiscales.getModel();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < modelo.getRowCount(); i++) {
            if (Boolean.TRUE.equals(modelo.getValueAt(i, RESP_COL_APLICA))) {
                if (sb.length() > 0) {
                    sb.append(", ");
                }
                sb.append(modelo.getValueAt(i, RESP_COL_CODIGO)).append(" /").append(modelo.getValueAt(i, RESP_COL_DESCRIPCION));
            }
        }
        return sb.toString();
    }

    private void inicializarTablaInformacionImpresion() {
        javax.swing.table.DefaultTableModel modelo = new javax.swing.table.DefaultTableModel(
                new String[]{"Información impresión", "Valor"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return col == 1;
            }
        };
        modelo.addRow(new Object[]{"Pie de página", ""});
        modelo.addRow(new Object[]{"Información legal", ""});
        if (instancias.getConfiguraciones().isProductosDetallados()) {
            modelo.addRow(new Object[]{"Anexo factura", ""});
        }
        if (instancias.getConfiguraciones().isOrdenServicio()) {
            modelo.addRow(new Object[]{"Anexo orden de servicio", ""});
        }

        final javax.swing.JTextArea taRend = new javax.swing.JTextArea();
        taRend.setLineWrap(true);
        taRend.setWrapStyleWord(true);
        taRend.setFont(new java.awt.Font("Century Gothic", 0, 14));
        taRend.setOpaque(true);

        javax.swing.table.TableCellRenderer renderer = (table, value, isSelected, hasFocus, row, col) -> {
            taRend.setText(value != null ? value.toString() : "");
            taRend.setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
            taRend.setForeground(isSelected ? table.getSelectionForeground() : table.getForeground());
            return taRend;
        };

        final javax.swing.JTextArea taEdit = new javax.swing.JTextArea();
        taEdit.setLineWrap(true);
        taEdit.setWrapStyleWord(true);
        taEdit.setFont(new java.awt.Font("Century Gothic", 0, 14));

        javax.swing.table.TableCellEditor editor = new javax.swing.DefaultCellEditor(new javax.swing.JTextField()) {
            @Override
            public java.awt.Component getTableCellEditorComponent(javax.swing.JTable table, Object value,
                    boolean isSelected, int row, int column) {
                taEdit.setText(value != null ? value.toString() : "");
                return taEdit;
            }

            @Override
            public Object getCellEditorValue() {
                return taEdit.getText();
            }

            @Override
            public boolean stopCellEditing() {
                fireEditingStopped();
                return true;
            }
        };

        tblInformacionImpresion = new javax.swing.JTable(modelo) {
            @Override
            public javax.swing.table.TableCellRenderer getCellRenderer(int row, int column) {
                if (column == 1) {
                    return renderer;
                }
                return super.getCellRenderer(row, column);
            }

            @Override
            public javax.swing.table.TableCellEditor getCellEditor(int row, int column) {
                if (column == 1) {
                    return editor;
                }
                return super.getCellEditor(row, column);
            }
        };
        tblInformacionImpresion.setFont(new java.awt.Font("Arial", 0, 16));
        tblInformacionImpresion.setRowHeight(24);
        if (tblInformacionImpresion.getColumnModel().getColumnCount() > 0) {
            tblInformacionImpresion.getColumnModel().getColumn(0).setMinWidth(180);
            tblInformacionImpresion.getColumnModel().getColumn(0).setPreferredWidth(180);
            tblInformacionImpresion.getColumnModel().getColumn(0).setMaxWidth(180);
        }
        // El alto de las filas depende del ancho real de la columna "Valor":
        // se recalcula cuando la tabla cambia de tamaño o se edita un valor.
        final javax.swing.JTable tablaInformacion = tblInformacionImpresion;
        tablaInformacion.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {
                ajustarAlturaFilas(tablaInformacion);
            }
        });
        modelo.addTableModelListener(e
                -> javax.swing.SwingUtilities.invokeLater(() -> ajustarAlturaFilas(tablaInformacion)));
        jScrollPane20.setViewportView(tblInformacionImpresion);
    }

    private void inicializarTablaConfiguracionesFacturacion() {
        inicializarTablaConfiguracionesFacturacion(instancias.getConfiguraciones().isRestaurante());
    }

    private static final String OPCION_SOLO_MESAS_MESEROS = "Activar solo mesas para meseros";
    private static final String OPCION_FACTURAR_MESAS_MESEROS = "Habilitar facturar mesas para meseros";
    private static final String OPCION_CONSECUTIVO_ADICIONAL = "Consecutivo adicional en factura";
    private static final String OPCION_MODIFICAR_PRECIO = "Modificar precio de productos";
    private static final String OPCION_FOCO_PRODUCTO = "Foco después de cargar el producto";
    private static final String FOCO_CANTIDAD = "Cantidad";
    private static final String OPCION_USAR_LECTOR = "Usar lector";

    /**
     * Las opciones de meseros solo están en la tabla si es restaurante; si no
     * lo es se conserva el valor que ya tenía la maestra.
     */
    private boolean valorOpcionMeseros(String opcion, boolean valorActual) {
        if (getFilaTabla(tblConfiguraciones, opcion) < 0) {
            return valorActual;
        }
        return getCeldaBooleanaPorEtiqueta(tblConfiguraciones, opcion, 1);
    }

    private void inicializarTablaConfiguracionesFacturacion(boolean esRestaurante) {
        javax.swing.table.DefaultTableModel modelo = new javax.swing.table.DefaultTableModel(
                new String[]{"Configuración", "Valor"}, 0) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return col == 1;
            }

            @Override
            public Class getColumnClass(int col) {
                return col == 1 ? Boolean.class : Object.class;
            }
        };
        // Consecutivo adicional: solo aplica cuando no es restaurante
        if (!esRestaurante) {
            modelo.addRow(new Object[]{OPCION_CONSECUTIVO_ADICIONAL, false});
        }
        modelo.addRow(new Object[]{"Ventas Contado Predeterminado", false});
        modelo.addRow(new Object[]{"Modificar nombre de productos", false});
        modelo.addRow(new Object[]{OPCION_MODIFICAR_PRECIO, false});
        modelo.addRow(new Object[]{"Combinar productos iguales", false});
        modelo.addRow(new Object[]{"Días de plazo automaticos", false});
        modelo.addRow(new Object[]{"Facturar Utilidad Sobrepasada", false});
        modelo.addRow(new Object[]{"Alertar Utilidad Sobrepasada", false});
        modelo.addRow(new Object[]{"Facturar sin inventario", false});
        modelo.addRow(new Object[]{"Mostrar devuelta en factura", false});
        modelo.addRow(new Object[]{"Validar el cupo del credito", false});
        modelo.addRow(new Object[]{"Activar turnos", false});

        String identificador = esRestaurante ? "mesas" : "congeladas";
        modelo.addRow(new Object[]{"Borrar productos en " + identificador, false});
        modelo.addRow(new Object[]{"Borrar " + identificador, false});

        modelo.addRow(new Object[]{"Módulo de solicitudes de permisos", false});
        modelo.addRow(new Object[]{OPCION_USAR_LECTOR, false});
        modelo.addRow(new Object[]{"Precios con impuestos", false});

        // Opciones de meseros: solo aplican para restaurante
        if (esRestaurante) {
            modelo.addRow(new Object[]{OPCION_SOLO_MESAS_MESEROS, true});
            modelo.addRow(new Object[]{OPCION_FACTURAR_MESAS_MESEROS, true});
        }

        // Las filas se identifican por su etiqueta: según la configuración algunas
        // no se agregan y los índices cambian.
        modelo.addTableModelListener(e -> {
            if (e.getColumn() != 1 || e.getFirstRow() < 0 || e.getFirstRow() >= modelo.getRowCount()) {
                return;
            }
            Object etiqueta = modelo.getValueAt(e.getFirstRow(), 0);
            if ("Activar turnos".equals(etiqueta)) {
                aplicarEstadoNumeroTurno();
            } else if (OPCION_MODIFICAR_PRECIO.equals(etiqueta) || OPCION_USAR_LECTOR.equals(etiqueta)) {
                aplicarEstadoFocoProducto();
            }
        });

        tblConfiguraciones = new javax.swing.JTable(modelo);
        tblConfiguraciones.setFont(new java.awt.Font("Arial", 0, 16));
        tblConfiguraciones.setRowHeight(24);
        if (tblConfiguraciones.getColumnModel().getColumnCount() > 0) {
            // "Configuración" toma el espacio restante; "Valor" queda fija en 60px
            tblConfiguraciones.getColumnModel().getColumn(0).setMinWidth(230);
            tblConfiguraciones.getColumnModel().getColumn(0).setPreferredWidth(260);
            tblConfiguraciones.getColumnModel().getColumn(1).setMinWidth(60);
            tblConfiguraciones.getColumnModel().getColumn(1).setPreferredWidth(60);
            tblConfiguraciones.getColumnModel().getColumn(1).setMaxWidth(60);
        }
        jScrollPane21.setViewportView(tblConfiguraciones);
    }

    private void inicializarTablaConfiguraciones1(boolean esRestaurante, boolean esCreditos) {
        final java.util.List<String> etiquetas = new java.util.ArrayList<>();
        final java.util.List<String> tipos = new java.util.ArrayList<>();

        etiquetas.add("Número de turno");
        tipos.add("int");
        etiquetas.add("Título en la factura");
        tipos.add("text");
        etiquetas.add("Límite de productos (carta/media carta)");
        tipos.add("num");
        etiquetas.add("Tipo de descuento");
        tipos.add("combo_descuento");
        etiquetas.add("Descuento máximo para aplicar");
        tipos.add("int");
        etiquetas.add(OPCION_FOCO_PRODUCTO);
        tipos.add("combo_foco");
        etiquetas.add("Cantidad al cargar un producto");
        tipos.add("num");
        etiquetas.add("Días para alerta de cartera");
        tipos.add("num");
        etiquetas.add("Contraseña para control y permisos");
        tipos.add("password");
        if (esRestaurante) {
            etiquetas.add("Porcentaje de propina en pre-factura");
            tipos.add("num");
        }
        if (esCreditos) {
            etiquetas.add("Días para cobrar mora en créditos");
            tipos.add("num");
            etiquetas.add("Porcentaje de mora en créditos");
            tipos.add("num");
        }

        javax.swing.table.DefaultTableModel modelo = new javax.swing.table.DefaultTableModel(
                new String[]{"Configuración", "Valor"}, 0) {
            @Override
            public boolean isCellEditable(int row, int col) {
                if (col == 0) {
                    return false;
                }
                if ("Número de turno".equals(getValueAt(row, 0))) {
                    return isTurnoActivo();
                }
                if ("Descuento máximo para aplicar".equals(getValueAt(row, 0))) {
                    return isTipoDescuentoPorcentaje();
                }
                if (OPCION_FOCO_PRODUCTO.equals(getValueAt(row, 0))) {
                    // Con lector el foco siempre vuelve al código del producto
                    return isModificarPrecioActivo() && !isUsarLectorActivo();
                }
                return true;
            }
        };

        for (String et : etiquetas) {
            modelo.addRow(new Object[]{et, ""});
        }

        modelo.addTableModelListener(e -> {
            if (e.getColumn() == 1 && e.getFirstRow() >= 0 && e.getFirstRow() < modelo.getRowCount()
                    && "Tipo de descuento".equals(modelo.getValueAt(e.getFirstRow(), 0))) {
                aplicarEstadoDescuentoMaximo();
            }
        });

        tblConfiguraciones1 = new javax.swing.JTable(modelo) {
            @Override
            public java.awt.Component prepareRenderer(javax.swing.table.TableCellRenderer renderer, int row, int col) {
                java.awt.Component c = super.prepareRenderer(renderer, row, col);
                int filaModelo = convertRowIndexToModel(row);
                if (col == 1 && filaModelo < tipos.size() && "password".equals(tipos.get(filaModelo))
                        && c instanceof javax.swing.JLabel) {
                    // Nunca se muestra la contraseña en claro: se enmascara con puntos
                    Object valor = getModel().getValueAt(filaModelo, 1);
                    int largo = valor == null ? 0 : valor.toString().length();
                    ((javax.swing.JLabel) c).setText(new String(new char[largo]).replace('\0', '•'));
                }
                if (isCellSelected(row, col)) {
                    return c;
                }
                // El renderer es compartido entre columnas: siempre se restablecen
                // los colores para no heredar el gris de otra celda.
                boolean bloqueada = col == 1 && !getModel().isCellEditable(convertRowIndexToModel(row), 1);
                c.setBackground(bloqueada ? new java.awt.Color(230, 230, 230) : getBackground());
                c.setForeground(bloqueada ? java.awt.Color.GRAY : getForeground());
                return c;
            }

            @Override
            public javax.swing.table.TableCellEditor getCellEditor(int row, int col) {
                // "row" es de la vista: con filas ocultas no coincide con el modelo
                int filaModelo = convertRowIndexToModel(row);
                if (col == 1 && filaModelo < tipos.size()) {
                    switch (tipos.get(filaModelo)) {
                        case "combo_descuento":
                            return new javax.swing.DefaultCellEditor(
                                    new javax.swing.JComboBox<>(new String[]{"Porcentaje", "Valor"}));
                        case "combo_foco":
                            return new javax.swing.DefaultCellEditor(
                                    new javax.swing.JComboBox<>(new String[]{"Cantidad", "Valor"}));
                        case "num": {
                            javax.swing.JTextField tf = new javax.swing.JTextField();
                            tf.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                            tf.addKeyListener(new java.awt.event.KeyAdapter() {
                                @Override
                                public void keyTyped(java.awt.event.KeyEvent e) {
                                    char c = e.getKeyChar();
                                    if (!Character.isDigit(c) && c != java.awt.event.KeyEvent.VK_BACK_SPACE && c != '.') {
                                        e.consume();
                                    }
                                }
                            });
                            return new javax.swing.DefaultCellEditor(tf);
                        }
                        case "int": {
                            javax.swing.JTextField tf = new javax.swing.JTextField();
                            tf.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                            tf.addKeyListener(new java.awt.event.KeyAdapter() {
                                @Override
                                public void keyTyped(java.awt.event.KeyEvent e) {
                                    char c = e.getKeyChar();
                                    if (!Character.isDigit(c) && c != java.awt.event.KeyEvent.VK_BACK_SPACE) {
                                        e.consume();
                                    }
                                }
                            });
                            return new javax.swing.DefaultCellEditor(tf);
                        }
                        case "password": {
                            javax.swing.JPasswordField pf = new javax.swing.JPasswordField();
                            pf.setFont(getFont());
                            return new javax.swing.DefaultCellEditor(pf);
                        }
                        default:
                            return super.getCellEditor(row, col);
                    }
                }
                return super.getCellEditor(row, col);
            }
        };

        // Filas que no aplican según la configuración se ocultan (no se ponen en gris)
        filtroConfiguraciones1 = new javax.swing.table.TableRowSorter<>(modelo);
        for (int columna = 0; columna < modelo.getColumnCount(); columna++) {
            filtroConfiguraciones1.setSortable(columna, false);
        }
        filtroConfiguraciones1.setRowFilter(new javax.swing.RowFilter<javax.swing.table.TableModel, Integer>() {
            @Override
            public boolean include(javax.swing.RowFilter.Entry<? extends javax.swing.table.TableModel, ? extends Integer> entry) {
                return esFilaVisibleConfiguraciones1(entry.getStringValue(0));
            }
        });
        tblConfiguraciones1.setRowSorter(filtroConfiguraciones1);

        tblConfiguraciones1.setFont(new java.awt.Font("Arial", 0, 16));
        tblConfiguraciones1.setRowHeight(28);
        if (tblConfiguraciones1.getColumnModel().getColumnCount() > 0) {
            tblConfiguraciones1.getColumnModel().getColumn(0).setMinWidth(260);
            tblConfiguraciones1.getColumnModel().getColumn(0).setPreferredWidth(300);
            tblConfiguraciones1.getColumnModel().getColumn(0).setMaxWidth(400);
        }
        jScrollPane22.setViewportView(tblConfiguraciones1);
    }

    private javax.swing.table.TableRowSorter<javax.swing.table.TableModel> filtroConfiguraciones1;

    /**
     * Filas de tblConfiguraciones1 que solo se muestran cuando aplican:
     * - "Número de turno": con "Activar turnos".
     * - "Foco después de cargar el producto": sin "Usar lector". Sin "Modificar
     *   precio" sigue visible, bloqueada en "Cantidad".
     * - "Descuento máximo para aplicar": con tipo de descuento "Porcentaje".
     */
    private boolean esFilaVisibleConfiguraciones1(String etiqueta) {
        if ("Número de turno".equals(etiqueta)) {
            return isTurnoActivo();
        }
        if (OPCION_FOCO_PRODUCTO.equals(etiqueta)) {
            return !isUsarLectorActivo();
        }
        if ("Descuento máximo para aplicar".equals(etiqueta)) {
            return isTipoDescuentoPorcentaje();
        }
        return true;
    }

    /**
     * El filtro depende de otra tabla (tblConfiguraciones): se vuelve a aplicar
     * cuando cambian esas opciones.
     */
    private void refrescarFilasVisiblesConfiguraciones1() {
        if (filtroConfiguraciones1 == null) {
            return;
        }
        // Se difiere: puede llamarse desde un listener del mismo modelo, antes de
        // que el filtro haya procesado el cambio.
        javax.swing.SwingUtilities.invokeLater(() -> {
            if (filtroConfiguraciones1 != null) {
                filtroConfiguraciones1.sort();
            }
        });
    }

    private boolean isModificarPrecioActivo() {
        return tblConfiguraciones != null && getCeldaBooleanaPorEtiqueta(tblConfiguraciones, OPCION_MODIFICAR_PRECIO, 1);
    }

    private boolean isUsarLectorActivo() {
        return tblConfiguraciones != null && getCeldaBooleanaPorEtiqueta(tblConfiguraciones, OPCION_USAR_LECTOR, 1);
    }

    /**
     * "Foco después de cargar el producto":
     * - Sin "Modificar precio de productos" no puede ir al valor: queda fijo en
     *   "Cantidad" y la celda se bloquea.
     * - Con "Usar lector" no aplica (el foco vuelve al código del producto): la
     *   fila se oculta y conserva su valor.
     */
    private void aplicarEstadoFocoProducto() {
        if (tblConfiguraciones1 == null) {
            return;
        }
        boolean modificarPrecio = isModificarPrecioActivo();
        if (!modificarPrecio || isUsarLectorActivo()) {
            cancelarEdicionFila(tblConfiguraciones1, getFilaTabla(tblConfiguraciones1, OPCION_FOCO_PRODUCTO));
        }
        if (!modificarPrecio) {
            setFilaTabla(tblConfiguraciones1, OPCION_FOCO_PRODUCTO, 1, FOCO_CANTIDAD);
        }
        refrescarFilasVisiblesConfiguraciones1();
        tblConfiguraciones1.repaint();
    }

    private boolean isTurnoActivo() {
        return tblConfiguraciones != null && getCeldaBooleanaPorEtiqueta(tblConfiguraciones, "Activar turnos", 1);
    }

    /**
     * "Número de turno" solo se muestra con "Activar turnos" en Si (inicia en 1
     * si estaba en 0); de lo contrario se oculta y se fuerza a 0.
     */
    private void aplicarEstadoNumeroTurno() {
        if (tblConfiguraciones1 == null) {
            return;
        }
        if (!isTurnoActivo()) {
            cancelarEdicionFila(tblConfiguraciones1, getFilaTabla(tblConfiguraciones1, "Número de turno"));
            setFilaTabla(tblConfiguraciones1, "Número de turno", 1, "0");
        } else if (parseIntSafe(getCeldaTextoPorEtiqueta(tblConfiguraciones1, "Número de turno", 1)) <= 0) {
            // Al activar turnos, el consecutivo inicia en 1
            setFilaTabla(tblConfiguraciones1, "Número de turno", 1, "1");
        }
        refrescarFilasVisiblesConfiguraciones1();
        tblConfiguraciones1.repaint();
    }

    private boolean isTipoDescuentoPorcentaje() {
        return tblConfiguraciones1 != null && DatosMaestra.DESCUENTO_PORCENTAJE.equals(
                DatosMaestra.normalizarTipoDescuento(getCeldaTextoPorEtiqueta(tblConfiguraciones1, "Tipo de descuento", 1)));
    }

    /**
     * "Descuento máximo para aplicar" solo es editable con tipo de descuento
     * "Porcentaje"; con "Valor" se fuerza a 0 (sin límite).
     */
    private void aplicarEstadoDescuentoMaximo() {
        if (tblConfiguraciones1 == null) {
            return;
        }
        if (!isTipoDescuentoPorcentaje()) {
            cancelarEdicionFila(tblConfiguraciones1, getFilaTabla(tblConfiguraciones1, "Descuento máximo para aplicar"));
            setFilaTabla(tblConfiguraciones1, "Descuento máximo para aplicar", 1, "0");
        }
        refrescarFilasVisiblesConfiguraciones1();
        tblConfiguraciones1.repaint();
    }

    /**
     * Ajusta el alto de cada fila al texto de la columna "Valor", usando el
     * ancho real de esa columna. Si la tabla aún no se ha mostrado (ancho 0) no
     * hace nada: se recalcula cuando la tabla toma su tamaño.
     */
    private void ajustarAlturaFilas(javax.swing.JTable tabla) {
        int anchoValor = tabla.getColumnModel().getColumn(1).getWidth();
        if (anchoValor <= 0) {
            return;
        }

        javax.swing.JTextArea medida = new javax.swing.JTextArea();
        medida.setLineWrap(true);
        medida.setWrapStyleWord(true);
        medida.setFont(new java.awt.Font("Century Gothic", 0, 14));

        for (int fila = 0; fila < tabla.getRowCount(); fila++) {
            Object valor = tabla.getValueAt(fila, 1);
            medida.setText(valor != null ? valor.toString() : "");
            medida.setSize(anchoValor, Short.MAX_VALUE);
            int alto = Math.max(medida.getPreferredSize().height + 4, 24);
            if (tabla.getRowHeight(fila) != alto) {
                tabla.setRowHeight(fila, alto);
            }
        }
    }

    private void inicializarTablaImpresoraPorTipo() {
        javax.swing.table.DefaultTableModel modelo = new javax.swing.table.DefaultTableModel(
                new String[]{"Tipo Documento", "Impresora", "Predeterminada"}, 0
        ) {
            Class[] types = new Class[]{Object.class, Object.class, Boolean.class};

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int row, int col) {
                if (col == 0) {
                    return false;
                }
                if (col == 2) {
                    Object tipo = getValueAt(row, 0);
                    return "Carta".equals(tipo) || "Media carta".equals(tipo) || "Pos".equals(tipo);
                }
                return true;
            }

            @Override
            public void setValueAt(Object valor, int fila, int columna) {
                super.setValueAt(valor, fila, columna);
                if (columna == 2 && Boolean.TRUE.equals(valor)) {
                    for (int i = 0; i < getRowCount(); i++) {
                        if (i != fila) {
                            super.setValueAt(false, i, 2);
                        }
                    }
                    fireTableDataChanged();
                }
            }
        };
        modelo.addRow(new Object[]{"Carta", null, false});
        modelo.addRow(new Object[]{"Media carta", null, false});
        modelo.addRow(new Object[]{"Pos", null, false});
        if (instancias.getConfiguraciones().isRestaurante()) {
            modelo.addRow(new Object[]{"Comanda", null, null});
            modelo.addRow(new Object[]{"Prefactura", null, null});
        }
        tblImpresoraPorTipo.setModel(modelo);
        tblImpresoraPorTipo.getColumnModel().getColumn(0).setMinWidth(120);
        tblImpresoraPorTipo.getColumnModel().getColumn(0).setPreferredWidth(120);
        tblImpresoraPorTipo.getColumnModel().getColumn(0).setMaxWidth(120);
        tblImpresoraPorTipo.getColumnModel().getColumn(2).setMinWidth(70);
        tblImpresoraPorTipo.getColumnModel().getColumn(2).setPreferredWidth(70);
        tblImpresoraPorTipo.getColumnModel().getColumn(2).setMaxWidth(70);
        refrescarEditorImpresoraPorTipo();
    }

    private void inicializarTablaImpresionDocumentos() {
        javax.swing.table.DefaultTableModel modelo = new javax.swing.table.DefaultTableModel(
                new String[]{"Tipo Documento", "Previsualizar", "Preguntar copias", "# copias"}, 0
        ) {
            Class[] types = new Class[]{Object.class, Boolean.class, Boolean.class, Object.class};

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int row, int col) {
                return col != 0;
            }
        };
        modelo.addRow(new Object[]{"Factura", null, null, null});
        if (instancias.getConfiguraciones().isOrdenServicio()) {
            modelo.addRow(new Object[]{"Orden de servicio", null, null, null});
        }
        modelo.addRow(new Object[]{"Cotización", null, null, null});
        if (instancias.getConfiguraciones().isSepare()) {
            modelo.addRow(new Object[]{"Plan separe", null, null, null});
        }
        if (instancias.getConfiguraciones().isPedido()) {
            modelo.addRow(new Object[]{"Pedido", null, null, null});
        }
        if (instancias.getConfiguraciones().isRestaurante()) {
            modelo.addRow(new Object[]{"Comanda", null, null, null});
            modelo.addRow(new Object[]{"Prefactura", null, null, null});
        }
        tblImpresionDocumentos.setModel(modelo);
        tblImpresionDocumentos.getColumnModel().getColumn(0).setMinWidth(130);
        tblImpresionDocumentos.getColumnModel().getColumn(0).setPreferredWidth(130);
        tblImpresionDocumentos.getColumnModel().getColumn(0).setMaxWidth(130);
        tblImpresionDocumentos.getColumnModel().getColumn(3).setMinWidth(70);
        tblImpresionDocumentos.getColumnModel().getColumn(3).setPreferredWidth(70);
        tblImpresionDocumentos.getColumnModel().getColumn(3).setMaxWidth(70);
    }

    private void refrescarEditorImpresoraPorTipo() {
        javax.swing.JComboBox<String> cmbImpresora = new javax.swing.JComboBox<>();
        cmbImpresora.addItem("Predeterminada");
        PrintService[] impresoras = PrintServiceLookup.lookupPrintServices(null, null);
        for (PrintService imp : impresoras) {
            try {
                String nombre = imp.toString().split(" : ")[1];
                if (!nombre.equals("Fax")) {
                    cmbImpresora.addItem(nombre);
                }
            } catch (Exception e) {
            }
        }
        tblImpresoraPorTipo.getColumnModel().getColumn(1).setCellEditor(new javax.swing.DefaultCellEditor(cmbImpresora));
    }

    // Los métodos por etiqueta trabajan sobre el MODELO (índices del modelo), no
    // sobre la vista: así siguen funcionando con filas ocultas por un filtro.
    private int getFilaTabla(javax.swing.JTable tabla, String etiqueta) {
        javax.swing.table.TableModel modelo = tabla.getModel();
        for (int i = 0; i < modelo.getRowCount(); i++) {
            Object val = modelo.getValueAt(i, 0);
            if (etiqueta.equals(val)) {
                return i;
            }
        }
        return -1;
    }

    private void setFilaTabla(javax.swing.JTable tabla, String etiqueta, int columna, Object valor) {
        int fila = getFilaTabla(tabla, etiqueta);
        if (fila >= 0) {
            tabla.getModel().setValueAt(valor, fila, columna);
        }
    }

    private boolean getCeldaBooleana(javax.swing.JTable tabla, int fila, int columna) {
        try {
            Object val = tabla.getModel().getValueAt(fila, columna);
            return val != null && (Boolean) val;
        } catch (Exception e) {
            return false;
        }
    }

    private String getCeldaTexto(javax.swing.JTable tabla, int fila, int columna) {
        try {
            Object val = tabla.getModel().getValueAt(fila, columna);
            return val != null ? val.toString() : "";
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Cancela la edición si se está editando la fila (del modelo) indicada.
     */
    private void cancelarEdicionFila(javax.swing.JTable tabla, int filaModelo) {
        if (filaModelo >= 0 && tabla.isEditing()
                && tabla.convertRowIndexToModel(tabla.getEditingRow()) == filaModelo) {
            tabla.getCellEditor().cancelCellEditing();
        }
    }

    private boolean getCeldaBooleanaPorEtiqueta(javax.swing.JTable tabla, String etiqueta, int columna) {
        int fila = getFilaTabla(tabla, etiqueta);
        if (fila < 0) {
            return false;
        }
        return getCeldaBooleana(tabla, fila, columna);
    }

    private String getCeldaTextoPorEtiqueta(javax.swing.JTable tabla, String etiqueta, int columna) {
        int fila = getFilaTabla(tabla, etiqueta);
        if (fila < 0) {
            return "";
        }
        return getCeldaTexto(tabla, fila, columna);
    }

    private boolean validacionesIniciales() {
        if (tblConfiguraciones1.isEditing()) {
            tblConfiguraciones1.getCellEditor().stopCellEditing();
        }

        if (instancias.getConfiguraciones().isMedico()) {
            if (rdNoGenerarOrden.isSelected()) {
                if (txtaCobrar1.getText().equals("")) {
                    ControladorAlertas.alertFail("Falta servicio a cobrar");
                    return false;
                }
            }

            if (rdNoGenerarOrden.isSelected()) {
                ndCamposOrdenPredeterminada nodo = instancias.getSql().getDatosCamposOrdenPredeterminada("1");
                if (nodo.getId() == null) {
                    ControladorAlertas.alertFail("No ha predeterminado los campos de la orden");
                    return false;
                }
            }
        }

        if (isTurnoActivo() && parseIntSafe(getCeldaTextoPorEtiqueta(tblConfiguraciones1, "Número de turno", 1)) <= 0) {
            ControladorAlertas.alertFail("Con turnos activos, el número de turno debe ser mayor a 0");
            return false;
        }

        return true;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnActualizarImpresoras;
    private javax.swing.JButton btnActualizarLogo;
    private javax.swing.JButton btnActualizarMesas;
    private javax.swing.JButton btnAgregarBodega;
    private javax.swing.JButton btnAgregarResolucion;
    private javax.swing.JButton btnBusProd;
    private javax.swing.JButton btnCambiarEstadoBodega;
    private javax.swing.JButton btnCambiarEstadoResolucion;
    private javax.swing.JButton btnGenerarTabla;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnGuardar4;
    private javax.swing.JButton btnGuardar6;
    private javax.swing.JButton btnGuardarMaestra;
    private javax.swing.JButton btnListarUsuarios;
    private javax.swing.JButton btnModificarBodega;
    private javax.swing.JButton btnModificarResolucion;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JComboBox cmbActoQuirurgico;
    private javax.swing.JComboBox cmbAmbito;
    private javax.swing.JComboBox cmbBodegas;
    private javax.swing.JComboBox cmbCausa;
    private javax.swing.JComboBox cmbCodigoConsulta;
    private javax.swing.JComboBox cmbConcepto;
    private javax.swing.JComboBox cmbDoctores;
    private javax.swing.JComboBox cmbDoctores1;
    private javax.swing.JComboBox cmbDoctores2;
    private javax.swing.JComboBox cmbDoctores3;
    private javax.swing.JComboBox cmbFinalidad;
    private javax.swing.JComboBox cmbForma;
    private javax.swing.JComboBox cmbHoraFin;
    private javax.swing.JComboBox cmbHoraFin1;
    private javax.swing.JComboBox cmbHoraInicio;
    private javax.swing.JComboBox cmbHoraInicio1;
    private javax.swing.JComboBox cmbIntervalo;
    private javax.swing.JComboBox cmbIntervalo1;
    private javax.swing.JComboBox cmbPersonal;
    private javax.swing.JComboBox cmbTipoPrestador;
    private com.toedter.calendar.JCalendar dtFecha;
    private javax.swing.ButtonGroup grupoBorrarMesas;
    private javax.swing.ButtonGroup grupoCobrarCasco;
    private javax.swing.ButtonGroup grupoCopiaSeguridad;
    private javax.swing.ButtonGroup grupoFacturarDiasPlazo;
    private javax.swing.ButtonGroup grupoGenerarEgreso;
    private javax.swing.ButtonGroup grupoIngresoSinCupo;
    private javax.swing.ButtonGroup grupoModificarValorFinal;
    private javax.swing.ButtonGroup grupoPrevisualizarComanda;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JPanel jPanel47;
    private javax.swing.JPanel jPanel48;
    private javax.swing.JPanel jPanel49;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel50;
    private javax.swing.JPanel jPanel51;
    private javax.swing.JPanel jPanel52;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JPopupMenu jPopupMenu3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane22;
    private javax.swing.JScrollPane jScrollPane23;
    private javax.swing.JScrollPane jScrollPane24;
    private javax.swing.JScrollPane jScrollPane25;
    private javax.swing.JScrollPane jScrollPane26;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JLabel lbCelular10;
    private javax.swing.JLabel lbCelular11;
    private javax.swing.JLabel lbCelular13;
    private javax.swing.JLabel lbCelular14;
    private javax.swing.JLabel lbCelular15;
    private javax.swing.JLabel lbCelular16;
    private javax.swing.JLabel lbCelular4;
    private javax.swing.JLabel lbCelular5;
    private javax.swing.JLabel lbCelular8;
    private javax.swing.JLabel lbCelular9;
    private javax.swing.JLabel lbFoto;
    private javax.swing.JLabel lbNit14;
    private javax.swing.JTextField lbOtroConsecutivo;
    private javax.swing.JLabel lbRazon;
    private javax.swing.JLabel lbRazon11;
    private javax.swing.JLabel lbRazon13;
    private javax.swing.JLabel lbRazon14;
    private javax.swing.JLabel lbRazon15;
    private javax.swing.JLabel lbRazon19;
    private javax.swing.JLabel lbRazon23;
    private javax.swing.JLabel lbRazon25;
    private javax.swing.JLabel lbRazon28;
    private javax.swing.JLabel lbRazon4;
    private javax.swing.JLabel lbRazon5;
    private javax.swing.JLabel lbTelefono20;
    private javax.swing.JLabel lbTelefono21;
    private javax.swing.JLabel lbTelefono38;
    private javax.swing.JPanel pnlAgenda;
    private javax.swing.JPanel pnlAgenda1;
    private javax.swing.JPanel pnlBodegas;
    private javax.swing.JPanel pnlBotones;
    private javax.swing.JPanel pnlDatosEmpresa;
    private javax.swing.JPanel pnlFormulario;
    private javax.swing.JPanel pnlImpresiones;
    private javax.swing.JPanel pnlImpresionesGrupo;
    private javax.swing.JPanel pnlInformacionMedico1;
    private javax.swing.JPanel pnlInformacionMedico2;
    private javax.swing.JPanel pnlIntervalosAgenda;
    private javax.swing.JPanel pnlLaboratorio;
    private javax.swing.JPanel pnlLogo;
    private javax.swing.JPanel pnlMedico;
    private javax.swing.JPanel pnlOrdenMedica;
    private javax.swing.JPanel pnlParqueadero;
    private javax.swing.JPanel pnlPermisosVentas;
    private javax.swing.JPanel pnlPrefijos;
    private javax.swing.JPanel pnlResoluciones;
    private javax.swing.JPanel pnlRestaurante;
    private javax.swing.JPanel pnlUsuario;
    private javax.swing.JPanel pnlUsuarios;
    private javax.swing.JPanel pnlVeterinario;
    private javax.swing.JMenuItem popBorrar1;
    private javax.swing.JMenuItem popBorrar2;
    private javax.swing.JRadioButton rbtNo;
    private javax.swing.JRadioButton rbtNo1;
    private javax.swing.JRadioButton rbtNo2;
    private javax.swing.JRadioButton rbtNo3;
    private javax.swing.JRadioButton rbtNoModificarValor;
    private javax.swing.JRadioButton rbtSi;
    private javax.swing.JRadioButton rbtSi1;
    private javax.swing.JRadioButton rbtSi2;
    private javax.swing.JRadioButton rbtSi3;
    private javax.swing.JRadioButton rbtSiModificarValor;
    private javax.swing.JRadioButton rdConsulta;
    private javax.swing.JRadioButton rdNoGenerarOrden;
    private javax.swing.JRadioButton rdNoImprimirFactura;
    private javax.swing.JRadioButton rdNoImprimirOrden;
    private javax.swing.JRadioButton rdProcedimiento;
    private javax.swing.JRadioButton rdRedondearCantidades;
    private javax.swing.JRadioButton rdSiGenerarOrden;
    private javax.swing.JRadioButton rdSiImprimirFactura;
    private javax.swing.JRadioButton rdSiImprimirOrden;
    private javax.swing.JScrollPane scrFormulario;
    private javax.swing.JScrollPane scrPermisosAgenda;
    private javax.swing.JScrollPane scrPermisosCartera;
    private javax.swing.JScrollPane scrPermisosMedico;
    private javax.swing.JScrollPane scrPermisosTerceros;
    private javax.swing.JScrollPane scrPermisosTesoreria;
    private javax.swing.JScrollPane scrPermisosVeterinaria;
    private javax.swing.JTabbedPane tabPanel;
    private javax.swing.JTabbedPane tapPanelPermisos;
    private javax.swing.JTable tblBodegasInternas;
    private javax.swing.JTable tblCascos;
    private javax.swing.JTable tblComprobantes;
    private javax.swing.JTable tblConfig;
    private javax.swing.JTable tblConfiguraciones;
    private javax.swing.JTable tblConfiguraciones1;
    private javax.swing.JTable tblDiasBloqueo;
    private javax.swing.JTable tblExamenes;
    private javax.swing.JTable tblHoras;
    private javax.swing.JTable tblImpresionDocumentos;
    private javax.swing.JTable tblImpresoraPorTipo;
    private javax.swing.JTable tblImpresoras;
    private javax.swing.JTable tblImpresoras1;
    private javax.swing.JTable tblInformacionEmpresa;
    private javax.swing.JTable tblInformacionImpresion;
    private javax.swing.JTable tblMesas;
    private javax.swing.JTable tblOpcionesImpresiones;
    private javax.swing.JTable tblPermisosAgenda;
    private javax.swing.JTable tblPermisosCartera;
    private javax.swing.JTable tblPermisosMedico;
    private javax.swing.JTable tblPermisosProductos;
    private javax.swing.JTable tblPermisosTerceros;
    private javax.swing.JTable tblPermisosTesoreria;
    private javax.swing.JTable tblPermisosVentas;
    private javax.swing.JTable tblPermisosVeterinaria;
    private javax.swing.JTable tblPrefijos;
    private javax.swing.JTable tblResponsabilidadesFiscales;
    private javax.swing.JTable tblRestaurante;
    private javax.swing.JTextField txtCodProducto;
    private javax.swing.JTextField txtCodigoPrestadorServico;
    private javax.swing.JTextField txtColumnas;
    private javax.swing.JTextField txtColumnas1;
    private javax.swing.JPasswordField txtContra;
    private javax.swing.JPasswordField txtContra2;
    private javax.swing.JTextField txtCosto;
    private javax.swing.JTextField txtFilas;
    private javax.swing.JTextField txtFilas1;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNombreFormato;
    private javax.swing.JLabel txtNumMesas;
    private javax.swing.JTextField txtNumeroFactosElectronicas;
    private javax.swing.JTextField txtUsuario;
    private javax.swing.JTextField txtValor1;
    private javax.swing.JTextField txtaCobrar;
    private javax.swing.JTextField txtaCobrar1;
    // End of variables declaration//GEN-END:variables
}
