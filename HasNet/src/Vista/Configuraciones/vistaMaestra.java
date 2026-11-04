package Vista.Configuraciones;

import Controlador.Alertas.ControladorAlertas;

import Utilidades.Contactos.Bodegas;

import Dao.Productos.DaoProducto;
import Dao.Terceros.DaoBodegas;
import Modelo.Contactos.ModeloBodega;
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
import java.beans.PropertyVetoException;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class vistaMaestra extends javax.swing.JInternalFrame {

    private DaoProducto daoProducto;
    private DaoBodegas daoBodegas;
    private DaoMaestra daoMaestra;
    private final List<ModeloBodega> bodegasCombo = new ArrayList<>();
    private boolean cargandoBodegas = false;

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

    public vistaMaestra() {

        initComponents();

        if (java.beans.Beans.isDesignTime()) {
            return;
        }

        daoProducto = new DaoProducto();
        daoBodegas = new DaoBodegas();
        daoMaestra = new DaoMaestra();
        instancias = Instancias.getInstancias();
        metodos = new metodosGenerales();

        inicializarTablaImpresoraPorTipo();
        inicializarTablaImpresionDocumentos();
        inicializarTablaInformacionEmpresa();
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

        jPanel12.setVisible(false);

        if (instancias.getConfiguraciones().isLaboratorio()) {
            lbRazon11.setVisible(false);
            txtaCobrar.setVisible(false);
            txtaCobrar1.setVisible(false);
            lbRazon19.setVisible(false);
        }

        this.simbolo = instancias.getSimbolo();

        PrintService[] impresoras = PrintServiceLookup.lookupPrintServices(null, null);
        DefaultTableModel model = (DefaultTableModel) tblImpresoras.getModel();

        if (impresoras.length > 0) {
            for (int i = 0; i < impresoras.length; i++) {
                if (!impresoras[i].toString().split(" : ")[1].equals("Fax")) {
                    model.addRow(new Object[]{impresoras[i].toString().split(" : ")[1]});
                }
            }
        }

        jPanel13.setVisible(false);

        tblBodegasInternas.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    actualizarBotonEstadoBodega();
                }
            }
        });
        cmbBodegas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarBodegaPredeterminada();
            }
        });
        cargarBodegasInternas();

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

        grupoFacturarUtilidadSobrepasada = new javax.swing.ButtonGroup();
        grupoTipoDescuento = new javax.swing.ButtonGroup();
        grupoMostrarUbicacion = new javax.swing.ButtonGroup();
        grupoImprimirRecogida = new javax.swing.ButtonGroup();
        grupoTipoImpresion = new javax.swing.ButtonGroup();
        grupoLector = new javax.swing.ButtonGroup();
        grupoValorConIva = new javax.swing.ButtonGroup();
        grupoCostoConIva = new javax.swing.ButtonGroup();
        grupoVentasContado = new javax.swing.ButtonGroup();
        grupoMensajeUtilidad = new javax.swing.ButtonGroup();
        grupoGeneraOrdenes = new javax.swing.ButtonGroup();
        grupoImpresionResolucion = new javax.swing.ButtonGroup();
        grupoCuadreFiscal = new javax.swing.ButtonGroup();
        grupoOcultarInformacionCliente = new javax.swing.ButtonGroup();
        grupoVisualizarFacturas = new javax.swing.ButtonGroup();
        grupoMostrarInformacionCuadre = new javax.swing.ButtonGroup();
        grupoCombinarProductos = new javax.swing.ButtonGroup();
        grupoModificarNombreProductos = new javax.swing.ButtonGroup();
        grupoPreguntarImpuestoBolsa = new javax.swing.ButtonGroup();
        grupoTurno = new javax.swing.ButtonGroup();
        grupoConsecutivoAdicional = new javax.swing.ButtonGroup();
        grupoMostrarHora = new javax.swing.ButtonGroup();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        popBorrar = new javax.swing.JMenuItem();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        popBorrar1 = new javax.swing.JMenuItem();
        grupoPermitirPonderados = new javax.swing.ButtonGroup();
        grupoModificarPrecioProductos = new javax.swing.ButtonGroup();
        grupoBorrarProductos = new javax.swing.ButtonGroup();
        grupoPrevisualizarPrefactura = new javax.swing.ButtonGroup();
        grupoFacturacionSeparado = new javax.swing.ButtonGroup();
        grupoCopiasFactura = new javax.swing.ButtonGroup();
        grupoCopiasOServicio = new javax.swing.ButtonGroup();
        grupoCopiasCotizacion = new javax.swing.ButtonGroup();
        grupoCopiasPlanSepare = new javax.swing.ButtonGroup();
        grupoCopiasPedido = new javax.swing.ButtonGroup();
        grupoPrevisualizarFactura = new javax.swing.ButtonGroup();
        grupoPrevisualizarOServicio = new javax.swing.ButtonGroup();
        grupoPrevisualizarCotizacion = new javax.swing.ButtonGroup();
        grupoPrevisualizarPlanSepare = new javax.swing.ButtonGroup();
        grupoPrevisualizarPedidos = new javax.swing.ButtonGroup();
        grupoMostrarImpoconsumo = new javax.swing.ButtonGroup();
        grupoPagoTercerosFactura = new javax.swing.ButtonGroup();
        grupoMostrarRetenciones = new javax.swing.ButtonGroup();
        grupoCupoCredito = new javax.swing.ButtonGroup();
        grupoDevuelta = new javax.swing.ButtonGroup();
        grupoDiasPlazo = new javax.swing.ButtonGroup();
        grupoSolicitudesPermisos = new javax.swing.ButtonGroup();
        grupoPVPImpoconsumo = new javax.swing.ButtonGroup();
        grupoCostoImpoconsumo = new javax.swing.ButtonGroup();
        grupoPrevisualizarComanda = new javax.swing.ButtonGroup();
        grupoSoloVisibleMesas = new javax.swing.ButtonGroup();
        grupoFacturarMesas = new javax.swing.ButtonGroup();
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
        pnlDatosEmpresa = new javax.swing.JPanel();
        lbOtroConsecutivo = new javax.swing.JTextField();
        txtNumeroFactosElectronicas = new javax.swing.JTextField();
        jScrollPane19 = new javax.swing.JScrollPane();
        tblInformacionEmpresa = new javax.swing.JTable();
        pnlBodegas = new javax.swing.JPanel();
        jScrollPane15 = new javax.swing.JScrollPane();
        tblBodegasInternas = new javax.swing.JTable();
        btnAgregarBodega = new javax.swing.JButton();
        btnModificarBodega = new javax.swing.JButton();
        btnCambiarEstadoBodega = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        cmbBodegas = new javax.swing.JComboBox();
        jScrollPane21 = new javax.swing.JScrollPane();
        tblConfiguraciones = new javax.swing.JTable();
        jScrollPane22 = new javax.swing.JScrollPane();
        tblConfiguraciones1 = new javax.swing.JTable();
        pnlVariablesYConf = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lbNit3 = new javax.swing.JLabel();
        lbTelefono9 = new javax.swing.JLabel();
        lbTelefono6 = new javax.swing.JLabel();
        lbTelefono10 = new javax.swing.JLabel();
        lbTelefono24 = new javax.swing.JLabel();
        lbTelefono13 = new javax.swing.JLabel();
        lbTelefono14 = new javax.swing.JLabel();
        txtDiasAlerta = new javax.swing.JTextField();
        txtContra = new javax.swing.JPasswordField();
        txtContra2 = new javax.swing.JPasswordField();
        txtCantidadEstablecida = new javax.swing.JTextField();
        rdSiSolicitudesPermisos = new javax.swing.JRadioButton();
        rdNoSolicitudesPermisos = new javax.swing.JRadioButton();
        lectorSi = new javax.swing.JRadioButton();
        lectorNo = new javax.swing.JRadioButton();
        rdSiPvpIva = new javax.swing.JRadioButton();
        rdNoPvpIva = new javax.swing.JRadioButton();
        jPanel13 = new javax.swing.JPanel();
        lbTelefono35 = new javax.swing.JLabel();
        rdTurnoSi = new javax.swing.JRadioButton();
        rdTurnoNo = new javax.swing.JRadioButton();
        txtTurno = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jPanel37 = new javax.swing.JPanel();
        jPanel38 = new javax.swing.JPanel();
        lbTelefono29 = new javax.swing.JLabel();
        lbTelefono59 = new javax.swing.JLabel();
        lbNit4 = new javax.swing.JLabel();
        lbTelefono27 = new javax.swing.JLabel();
        lbTelefono5 = new javax.swing.JLabel();
        lbTelefono48 = new javax.swing.JLabel();
        lbTelefono65 = new javax.swing.JLabel();
        jPanel39 = new javax.swing.JPanel();
        txtLimite = new javax.swing.JTextField();
        txtTituloFactura = new javax.swing.JTextField();
        txtDescuentoMaximo = new javax.swing.JTextField();
        descuentoPorcentaje = new javax.swing.JRadioButton();
        descuentoPeso = new javax.swing.JRadioButton();
        txtPorcPropina = new javax.swing.JTextField();
        cmbFoco = new javax.swing.JComboBox();
        jPanel42 = new javax.swing.JPanel();
        jPanel43 = new javax.swing.JPanel();
        lbTelefono43 = new javax.swing.JLabel();
        lbTelefono69 = new javax.swing.JLabel();
        jPanel44 = new javax.swing.JPanel();
        rdSiBorrarProdMesas = new javax.swing.JRadioButton();
        rdNoBorrarProdMesas = new javax.swing.JRadioButton();
        rdSiBorrarMesas = new javax.swing.JRadioButton();
        rdNoBorrarMesas = new javax.swing.JRadioButton();
        pnlCredito = new javax.swing.JPanel();
        lbTelefono18 = new javax.swing.JLabel();
        lbTelefono19 = new javax.swing.JLabel();
        txtPorcentajeMora = new javax.swing.JTextField();
        txtDiasCobrarMora = new javax.swing.JTextField();
        jPanel16 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblTarjetas = new javax.swing.JTable();
        lbNit11 = new javax.swing.JLabel();
        txtFranquisia = new javax.swing.JTextField();
        lbNit12 = new javax.swing.JLabel();
        lbNit13 = new javax.swing.JLabel();
        txtComision = new javax.swing.JTextField();
        btnGuardar5 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
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
        pnlImpresiones = new javax.swing.JPanel();
        pnlImpresionesGrupo = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblImpresoras1 = new javax.swing.JTable();
        btnGuardar4 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblImpresoras = new javax.swing.JTable();
        btnGuardar3 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        rdImpresionPorGrupo = new javax.swing.JRadioButton();
        btnOcultarImpresiones1 = new javax.swing.JButton();
        jScrollPane16 = new javax.swing.JScrollPane();
        tblImpresionDocumentos = new javax.swing.JTable();
        jScrollPane17 = new javax.swing.JScrollPane();
        tblImpresoraPorTipo = new javax.swing.JTable();
        jScrollPane18 = new javax.swing.JScrollPane();
        tblOpcionesImpresiones = new javax.swing.JTable();
        jScrollPane20 = new javax.swing.JScrollPane();
        tblInformacionImpresion = new javax.swing.JTable();
        jPanel15 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblExamenes = new javax.swing.JTable();
        lbTelefono38 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        pnlAgenda = new javax.swing.JPanel();
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
        jPanel34 = new javax.swing.JPanel();
        cmbDoctores2 = new javax.swing.JComboBox();
        lbCelular4 = new javax.swing.JLabel();
        txtColumnas = new javax.swing.JTextField();
        lbCelular8 = new javax.swing.JLabel();
        txtFilas = new javax.swing.JTextField();
        lbCelular6 = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        tblRestaurante = new javax.swing.JTable();
        txtNumMesas = new javax.swing.JLabel();
        lbCelular10 = new javax.swing.JLabel();
        btnGenerarTabla = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        lbTelefono67 = new javax.swing.JLabel();
        rdSiSoloVisibleMesas = new javax.swing.JRadioButton();
        rdNoSoloVisibleMesas = new javax.swing.JRadioButton();
        lbTelefono73 = new javax.swing.JLabel();
        rdSiFacturarMesas = new javax.swing.JRadioButton();
        rdNoFacturarMesas = new javax.swing.JRadioButton();
        jPanel14 = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        tblMesas = new javax.swing.JTable();
        btnActualizarMesas = new javax.swing.JButton();
        jPanel35 = new javax.swing.JPanel();
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
        pnlFormulario1 = new javax.swing.JPanel();
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
        jPanel12 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtIdProd = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        btnGuardar2 = new javax.swing.JButton();
        btnGuardarMaestra = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        btnResolucion = new javax.swing.JButton();

        popBorrar.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        popBorrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/eliminar-cancelar-icono-4935-16.png"))); // NOI18N
        popBorrar.setText("Borrar");
        popBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                popBorrarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(popBorrar);

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
                {"Nombre", null},
                {"Nit", null},
                {"Teléfono", null},
                {"Régimen", null},
                {"Dirección", null}
            },
            new String [] {
                "Datos de la empresa", "Valor"
            }
        ));
        tblInformacionEmpresa.setRowHeight(24);
        jScrollPane19.setViewportView(tblInformacionEmpresa);

        pnlBodegas.setBackground(new java.awt.Color(255, 255, 255));
        pnlBodegas.setBorder(javax.swing.BorderFactory.createTitledBorder("Bodegas"));

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
        jScrollPane15.setViewportView(tblBodegasInternas);

        btnAgregarBodega.setBackground(new java.awt.Color(46, 204, 113));
        btnAgregarBodega.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        btnAgregarBodega.setText("Agregar bodega");
        btnAgregarBodega.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnAgregarBodega.setEnabled(false);
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
        btnModificarBodega.setEnabled(false);
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
        btnCambiarEstadoBodega.setEnabled(false);
        btnCambiarEstadoBodega.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnCambiarEstadoBodega.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnCambiarEstadoBodega.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCambiarEstadoBodegaActionPerformed(evt);
            }
        });

        jLabel13.setText("Bodega seleccionada por defecto:");

        javax.swing.GroupLayout pnlBodegasLayout = new javax.swing.GroupLayout(pnlBodegas);
        pnlBodegas.setLayout(pnlBodegasLayout);
        pnlBodegasLayout.setHorizontalGroup(
            pnlBodegasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBodegasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBodegasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlBodegasLayout.createSequentialGroup()
                        .addComponent(btnAgregarBodega, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                    .addComponent(btnAgregarBodega, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnModificarBodega, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCambiarEstadoBodega, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(pnlBodegasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbBodegas, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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

        javax.swing.GroupLayout pnlDatosEmpresaLayout = new javax.swing.GroupLayout(pnlDatosEmpresa);
        pnlDatosEmpresa.setLayout(pnlDatosEmpresaLayout);
        pnlDatosEmpresaLayout.setHorizontalGroup(
            pnlDatosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatosEmpresaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDatosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDatosEmpresaLayout.createSequentialGroup()
                        .addComponent(lbOtroConsecutivo, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNumeroFactosElectronicas, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlDatosEmpresaLayout.createSequentialGroup()
                        .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlBodegas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlDatosEmpresaLayout.createSequentialGroup()
                        .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, 517, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane22, javax.swing.GroupLayout.PREFERRED_SIZE, 477, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        pnlDatosEmpresaLayout.setVerticalGroup(
            pnlDatosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatosEmpresaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDatosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbOtroConsecutivo, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addComponent(txtNumeroFactosElectronicas, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDatosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlBodegas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDatosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane22, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE))
                .addContainerGap())
        );

        tabPanel.addTab("Datos Empresa", pnlDatosEmpresa);

        pnlVariablesYConf.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "En General", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 12))); // NOI18N

        lbNit3.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbNit3.setText("Diás Alerta Cartera:");

        lbTelefono9.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTelefono9.setText("Contraseña para control permisos:");

        lbTelefono6.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTelefono6.setText("Confirmar contraseña de control:");

        lbTelefono10.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTelefono10.setText("Cantidad al cargar:");

        lbTelefono24.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTelefono24.setText("Solicitudes de permisos");

        lbTelefono13.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTelefono13.setText("Lector:");

        lbTelefono14.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTelefono14.setText("P.V.P Con Iva:");

        txtDiasAlerta.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtDiasAlerta.setName("CC/NIT"); // NOI18N
        txtDiasAlerta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDiasAlertaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDiasAlertaKeyTyped(evt);
            }
        });

        txtContra.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtContra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtContraActionPerformed(evt);
            }
        });

        txtContra2.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N

        txtCantidadEstablecida.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtCantidadEstablecida.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCantidadEstablecida.setText("0");
        txtCantidadEstablecida.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadEstablecidaKeyTyped(evt);
            }
        });

        rdSiSolicitudesPermisos.setBackground(new java.awt.Color(255, 255, 255));
        grupoSolicitudesPermisos.add(rdSiSolicitudesPermisos);
        rdSiSolicitudesPermisos.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rdSiSolicitudesPermisos.setText("Si");

        rdNoSolicitudesPermisos.setBackground(new java.awt.Color(255, 255, 255));
        grupoSolicitudesPermisos.add(rdNoSolicitudesPermisos);
        rdNoSolicitudesPermisos.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rdNoSolicitudesPermisos.setText("No");
        rdNoSolicitudesPermisos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdNoSolicitudesPermisosActionPerformed(evt);
            }
        });

        lectorSi.setBackground(new java.awt.Color(255, 255, 255));
        grupoLector.add(lectorSi);
        lectorSi.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        lectorSi.setText("Si");

        lectorNo.setBackground(new java.awt.Color(255, 255, 255));
        grupoLector.add(lectorNo);
        lectorNo.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        lectorNo.setText("No");
        lectorNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lectorNoActionPerformed(evt);
            }
        });

        rdSiPvpIva.setBackground(new java.awt.Color(255, 255, 255));
        grupoValorConIva.add(rdSiPvpIva);
        rdSiPvpIva.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rdSiPvpIva.setText("Si");
        rdSiPvpIva.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdSiPvpIvaItemStateChanged(evt);
            }
        });

        rdNoPvpIva.setBackground(new java.awt.Color(255, 255, 255));
        grupoValorConIva.add(rdNoPvpIva);
        rdNoPvpIva.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rdNoPvpIva.setText("No");
        rdNoPvpIva.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdNoPvpIvaItemStateChanged(evt);
            }
        });
        rdNoPvpIva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdNoPvpIvaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbTelefono14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbTelefono13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbTelefono24, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lbTelefono6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbTelefono9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                            .addComponent(lbNit3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbTelefono10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtDiasAlerta, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtContra, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtContra2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCantidadEstablecida, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(56, 56, 56))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(rdSiPvpIva, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                            .addComponent(lectorSi, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(rdSiSolicitudesPermisos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdNoSolicitudesPermisos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lectorNo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(rdNoPvpIva, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(59, 59, 59))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lbNit3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbTelefono9, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtContra, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbTelefono6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtContra2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(txtDiasAlerta, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lbTelefono10, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbTelefono24, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdSiSolicitudesPermisos, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdNoSolicitudesPermisos, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbTelefono13, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lectorSi, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lectorNo, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbTelefono14, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdSiPvpIva, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdNoPvpIva, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(txtCantidadEstablecida, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
        );

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Turnero", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 12))); // NOI18N

        lbTelefono35.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbTelefono35.setText("Turnos:");

        rdTurnoSi.setBackground(new java.awt.Color(255, 255, 255));
        grupoTurno.add(rdTurnoSi);
        rdTurnoSi.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rdTurnoSi.setText("Si");

        rdTurnoNo.setBackground(new java.awt.Color(255, 255, 255));
        grupoTurno.add(rdTurnoNo);
        rdTurnoNo.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rdTurnoNo.setText("No");
        rdTurnoNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdTurnoNoActionPerformed(evt);
            }
        });

        txtTurno.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        txtTurno.setForeground(new java.awt.Color(255, 0, 0));
        txtTurno.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTurno.setName("CC/NIT"); // NOI18N
        txtTurno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTurnoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTurnoKeyTyped(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/actualizar pequeño.png"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbTelefono35)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rdTurnoSi)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rdTurnoNo)
                .addGap(18, 18, 18)
                .addComponent(txtTurno, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtTurno, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(lbTelefono35, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rdTurnoSi, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(rdTurnoNo, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel37.setBackground(new java.awt.Color(255, 255, 255));
        jPanel37.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "En Facturación", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 12))); // NOI18N

        jPanel38.setBackground(new java.awt.Color(255, 255, 255));

        lbTelefono29.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTelefono29.setText("Limite de productos por factura");
        lbTelefono29.setToolTipText("Visualizar toda la información de facturacion, \nreportes y abonos de todas las terminales.");

        lbTelefono59.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTelefono59.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTelefono59.setText("carta y media carta");
        lbTelefono59.setToolTipText("Visualizar toda la información de facturacion, \nreportes y abonos de todas las terminales.");

        lbNit4.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbNit4.setText("Titulo en Factura:");

        lbTelefono27.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTelefono27.setText("Descuento maximo en factura:");
        lbTelefono27.setToolTipText("Visualizar toda la información de facturacion, \nreportes y abonos de todas las terminales.");

        lbTelefono5.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTelefono5.setText("Tipo de descuento:");

        lbTelefono48.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTelefono48.setText("% Propina en la pre-factura:");

        lbTelefono65.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTelefono65.setText("Foco despues de cargar el producto:");
        lbTelefono65.setToolTipText("Visualizar toda la información de facturacion, \nreportes y abonos de todas las terminales.");

        javax.swing.GroupLayout jPanel38Layout = new javax.swing.GroupLayout(jPanel38);
        jPanel38.setLayout(jPanel38Layout);
        jPanel38Layout.setHorizontalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel38Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel38Layout.createSequentialGroup()
                        .addComponent(lbTelefono27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel38Layout.createSequentialGroup()
                        .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbTelefono59, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbTelefono29, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel38Layout.createSequentialGroup()
                        .addComponent(lbNit4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(145, 145, 145))
                    .addComponent(lbTelefono5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbTelefono48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbTelefono65, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)))
        );
        jPanel38Layout.setVerticalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel38Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(lbTelefono29)
                .addGap(2, 2, 2)
                .addComponent(lbTelefono59)
                .addGap(6, 6, 6)
                .addComponent(lbTelefono27, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(lbTelefono48, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(lbNit4, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(lbTelefono5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(lbTelefono65, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );

        jPanel39.setBackground(new java.awt.Color(255, 255, 255));

        txtLimite.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtLimite.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtLimite.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtLimiteKeyTyped(evt);
            }
        });

        txtTituloFactura.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtTituloFactura.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTituloFactura.setName("CC/NIT"); // NOI18N
        txtTituloFactura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTituloFacturaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTituloFacturaKeyTyped(evt);
            }
        });

        txtDescuentoMaximo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtDescuentoMaximo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDescuentoMaximo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDescuentoMaximoKeyTyped(evt);
            }
        });

        descuentoPorcentaje.setBackground(new java.awt.Color(255, 255, 255));
        grupoTipoDescuento.add(descuentoPorcentaje);
        descuentoPorcentaje.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        descuentoPorcentaje.setSelected(true);
        descuentoPorcentaje.setText("%");

        descuentoPeso.setBackground(new java.awt.Color(255, 255, 255));
        grupoTipoDescuento.add(descuentoPeso);
        descuentoPeso.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        descuentoPeso.setText("Valor");

        txtPorcPropina.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtPorcPropina.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPorcPropina.setText("0");
        txtPorcPropina.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPorcPropinaKeyTyped(evt);
            }
        });

        cmbFoco.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        cmbFoco.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Cant", "Valor" }));

        javax.swing.GroupLayout jPanel39Layout = new javax.swing.GroupLayout(jPanel39);
        jPanel39.setLayout(jPanel39Layout);
        jPanel39Layout.setHorizontalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel39Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbFoco, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTituloFactura, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel39Layout.createSequentialGroup()
                        .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtPorcPropina, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtLimite)
                            .addComponent(txtDescuentoMaximo))
                        .addGap(66, 66, 66))
                    .addGroup(jPanel39Layout.createSequentialGroup()
                        .addComponent(descuentoPorcentaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(descuentoPeso, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel39Layout.setVerticalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel39Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(txtLimite, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(txtDescuentoMaximo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(txtPorcPropina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(txtTituloFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(descuentoPorcentaje, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descuentoPeso, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(57, 57, 57)
                .addComponent(cmbFoco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );

        javax.swing.GroupLayout jPanel37Layout = new javax.swing.GroupLayout(jPanel37);
        jPanel37.setLayout(jPanel37Layout);
        jPanel37Layout.setHorizontalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );
        jPanel37Layout.setVerticalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );

        jPanel42.setBackground(new java.awt.Color(255, 255, 255));
        jPanel42.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Congeladas y Mesas ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 12))); // NOI18N

        jPanel43.setBackground(new java.awt.Color(255, 255, 255));

        lbTelefono43.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTelefono43.setText("Borrar productos:");
        lbTelefono43.setToolTipText("Visualizar toda la información de facturacion, \nreportes y abonos de todas las terminales.");

        lbTelefono69.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTelefono69.setText("Borrar mesas:");
        lbTelefono69.setToolTipText("Visualizar toda la información de facturacion, \nreportes y abonos de todas las terminales.");

        javax.swing.GroupLayout jPanel43Layout = new javax.swing.GroupLayout(jPanel43);
        jPanel43.setLayout(jPanel43Layout);
        jPanel43Layout.setHorizontalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel43Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lbTelefono69, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbTelefono43, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE))
                .addContainerGap(120, Short.MAX_VALUE))
        );
        jPanel43Layout.setVerticalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel43Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(lbTelefono43)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbTelefono69)
                .addContainerGap())
        );

        jPanel44.setBackground(new java.awt.Color(255, 255, 255));

        rdSiBorrarProdMesas.setBackground(new java.awt.Color(255, 255, 255));
        grupoBorrarProductos.add(rdSiBorrarProdMesas);
        rdSiBorrarProdMesas.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rdSiBorrarProdMesas.setSelected(true);
        rdSiBorrarProdMesas.setText("Si");
        rdSiBorrarProdMesas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdSiBorrarProdMesasActionPerformed(evt);
            }
        });

        rdNoBorrarProdMesas.setBackground(new java.awt.Color(255, 255, 255));
        grupoBorrarProductos.add(rdNoBorrarProdMesas);
        rdNoBorrarProdMesas.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rdNoBorrarProdMesas.setText("No");
        rdNoBorrarProdMesas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdNoBorrarProdMesasActionPerformed(evt);
            }
        });

        rdSiBorrarMesas.setBackground(new java.awt.Color(255, 255, 255));
        grupoBorrarMesas.add(rdSiBorrarMesas);
        rdSiBorrarMesas.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rdSiBorrarMesas.setText("Si");
        rdSiBorrarMesas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdSiBorrarMesasActionPerformed(evt);
            }
        });

        rdNoBorrarMesas.setBackground(new java.awt.Color(255, 255, 255));
        grupoBorrarMesas.add(rdNoBorrarMesas);
        rdNoBorrarMesas.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rdNoBorrarMesas.setSelected(true);
        rdNoBorrarMesas.setText("No");
        rdNoBorrarMesas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdNoBorrarMesasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel44Layout = new javax.swing.GroupLayout(jPanel44);
        jPanel44.setLayout(jPanel44Layout);
        jPanel44Layout.setHorizontalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel44Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel44Layout.createSequentialGroup()
                        .addComponent(rdSiBorrarProdMesas, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rdNoBorrarProdMesas, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE))
                    .addGroup(jPanel44Layout.createSequentialGroup()
                        .addComponent(rdSiBorrarMesas, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rdNoBorrarMesas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel44Layout.setVerticalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel44Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdSiBorrarProdMesas)
                    .addComponent(rdNoBorrarProdMesas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdSiBorrarMesas)
                    .addComponent(rdNoBorrarMesas)))
        );

        javax.swing.GroupLayout jPanel42Layout = new javax.swing.GroupLayout(jPanel42);
        jPanel42.setLayout(jPanel42Layout);
        jPanel42Layout.setHorizontalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel42Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jPanel43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel42Layout.setVerticalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel42Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel43, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );

        pnlCredito.setBackground(new java.awt.Color(255, 255, 255));
        pnlCredito.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Creditos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 12))); // NOI18N

        lbTelefono18.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTelefono18.setText("Días para cobrar mora Credito:");

        lbTelefono19.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTelefono19.setText("Porcentaje de mora Credito:");

        txtPorcentajeMora.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPorcentajeMoraKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPorcentajeMoraKeyTyped(evt);
            }
        });

        txtDiasCobrarMora.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDiasCobrarMoraKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout pnlCreditoLayout = new javax.swing.GroupLayout(pnlCredito);
        pnlCredito.setLayout(pnlCreditoLayout);
        pnlCreditoLayout.setHorizontalGroup(
            pnlCreditoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCreditoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCreditoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbTelefono18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbTelefono19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCreditoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtDiasCobrarMora)
                    .addComponent(txtPorcentajeMora, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlCreditoLayout.setVerticalGroup(
            pnlCreditoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCreditoLayout.createSequentialGroup()
                .addGroup(pnlCreditoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtDiasCobrarMora)
                    .addComponent(lbTelefono18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCreditoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbTelefono19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtPorcentajeMora))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlVariablesYConfLayout = new javax.swing.GroupLayout(pnlVariablesYConf);
        pnlVariablesYConf.setLayout(pnlVariablesYConfLayout);
        pnlVariablesYConfLayout.setHorizontalGroup(
            pnlVariablesYConfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlVariablesYConfLayout.createSequentialGroup()
                .addContainerGap(44, Short.MAX_VALUE)
                .addGroup(pnlVariablesYConfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlVariablesYConfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlCredito, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        pnlVariablesYConfLayout.setVerticalGroup(
            pnlVariablesYConfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlVariablesYConfLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(pnlVariablesYConfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlVariablesYConfLayout.createSequentialGroup()
                        .addComponent(jPanel37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlVariablesYConfLayout.createSequentialGroup()
                        .addComponent(jPanel42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlCredito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(5, 5, 5))
        );

        tabPanel.addTab("Variables y cofiguraciones", pnlVariablesYConf);

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));

        tblTarjetas.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        tblTarjetas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Franquicia", "Comisión"
            }
        ));
        tblTarjetas.setComponentPopupMenu(jPopupMenu1);
        tblTarjetas.setRowHeight(27);
        jScrollPane7.setViewportView(tblTarjetas);
        if (tblTarjetas.getColumnModel().getColumnCount() > 0) {
            tblTarjetas.getColumnModel().getColumn(1).setMinWidth(60);
            tblTarjetas.getColumnModel().getColumn(1).setPreferredWidth(60);
            tblTarjetas.getColumnModel().getColumn(1).setMaxWidth(60);
        }

        lbNit11.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        lbNit11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit11.setText("TARJETAS DE CREDITO");

        lbNit12.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbNit12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit12.setText("FRANQUICIA:");

        lbNit13.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbNit13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit13.setText("COMISIÓN:");

        btnGuardar5.setBackground(new java.awt.Color(204, 204, 204));
        btnGuardar5.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnGuardar5.setText("CARGAR");
        btnGuardar5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnGuardar5.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnGuardar5.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnGuardar5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardar5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbNit11, javax.swing.GroupLayout.DEFAULT_SIZE, 1026, Short.MAX_VALUE)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel16Layout.createSequentialGroup()
                                    .addComponent(lbNit12)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtFranquisia)
                                    .addGap(18, 18, 18)
                                    .addComponent(lbNit13)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtComision, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(btnGuardar5, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(lbNit11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbNit13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtFranquisia, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                        .addComponent(txtComision, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
                    .addComponent(lbNit12, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(btnGuardar5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(192, Short.MAX_VALUE))
        );

        tabPanel.addTab("Tarjetas", jPanel16);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

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
        grupoGeneraOrdenes.add(rdSiGenerarOrden);
        rdSiGenerarOrden.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rdSiGenerarOrden.setText("SI");

        rdNoGenerarOrden.setBackground(new java.awt.Color(255, 255, 255));
        grupoGeneraOrdenes.add(rdNoGenerarOrden);
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

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlOrdenMedica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(pnlInformacionMedico1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlInformacionMedico2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlOrdenMedica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlInformacionMedico2, javax.swing.GroupLayout.PREFERRED_SIZE, 248, Short.MAX_VALUE)
                    .addComponent(pnlInformacionMedico1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(150, Short.MAX_VALUE))
        );

        tabPanel.addTab("Médico", jPanel7);

        pnlImpresiones.setBackground(new java.awt.Color(255, 255, 255));

        pnlImpresionesGrupo.setBackground(new java.awt.Color(255, 255, 255));
        pnlImpresionesGrupo.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        tblImpresoras1.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
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

        tblImpresoras.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
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

        btnGuardar3.setBackground(new java.awt.Color(46, 204, 113));
        btnGuardar3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnGuardar3.setText("ACTUALIZAR IMPRESORAS");
        btnGuardar3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnGuardar3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnGuardar3.setMargin(null);
        btnGuardar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardar3ActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel9.setText("Impresoras disponibles:");

        rdImpresionPorGrupo.setBackground(new java.awt.Color(255, 255, 255));
        rdImpresionPorGrupo.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rdImpresionPorGrupo.setText("Impresión por grupo");

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
                        .addComponent(rdImpresionPorGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnGuardar3, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGuardar4)))
                .addContainerGap())
        );
        pnlImpresionesGrupoLayout.setVerticalGroup(
            pnlImpresionesGrupoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlImpresionesGrupoLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(pnlImpresionesGrupoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlImpresionesGrupoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnGuardar3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnGuardar4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(rdImpresionPorGrupo))
                .addGap(10, 10, 10)
                .addGroup(pnlImpresionesGrupoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlImpresionesGrupoLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(3, 3, 3)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        btnOcultarImpresiones1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/boton-resta.png"))); // NOI18N
        btnOcultarImpresiones1.setToolTipText("Ctrl+M");
        btnOcultarImpresiones1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOcultarImpresiones1ActionPerformed(evt);
            }
        });

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
                "Tipo Documento", "Impresora por defecto", "Pretederminado"
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
            tblImpresoraPorTipo.getColumnModel().getColumn(2).setMinWidth(90);
            tblImpresoraPorTipo.getColumnModel().getColumn(2).setPreferredWidth(90);
            tblImpresoraPorTipo.getColumnModel().getColumn(2).setMaxWidth(90);
        }

        tblOpcionesImpresiones.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        tblOpcionesImpresiones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Imprimir ubicación en factura", null},
                {"Imprimir hora en factura", null},
                {"Imprimir retenciones en factura", null},
                {"Imprimir impoconsumo en factura", null},
                {"Imprimir recodiga en cuadre de caja", null},
                {"Imprimir cuadre fiscal en cuadre de caja", null}
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

        javax.swing.GroupLayout pnlImpresionesLayout = new javax.swing.GroupLayout(pnlImpresiones);
        pnlImpresiones.setLayout(pnlImpresionesLayout);
        pnlImpresionesLayout.setHorizontalGroup(
            pnlImpresionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlImpresionesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlImpresionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlImpresionesLayout.createSequentialGroup()
                        .addComponent(pnlImpresionesGrupo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnOcultarImpresiones1))
                    .addGroup(pnlImpresionesLayout.createSequentialGroup()
                        .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnlImpresionesLayout.createSequentialGroup()
                        .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 423, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane20, javax.swing.GroupLayout.DEFAULT_SIZE, 597, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlImpresionesLayout.setVerticalGroup(
            pnlImpresionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlImpresionesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlImpresionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane16, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                    .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlImpresionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlImpresionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlImpresionesGrupo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlImpresionesLayout.createSequentialGroup()
                        .addComponent(btnOcultarImpresiones1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        tabPanel.addTab("Manejo de impresiones", pnlImpresiones);

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));

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

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 1026, Short.MAX_VALUE)
                    .addComponent(lbTelefono38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbTelefono38, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 558, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabPanel.addTab("Laboratorio", jPanel15);

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));

        pnlAgenda.setBackground(new java.awt.Color(255, 255, 255));
        pnlAgenda.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Intervalos de la agenda", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 14))); // NOI18N

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

        javax.swing.GroupLayout pnlAgendaLayout = new javax.swing.GroupLayout(pnlAgenda);
        pnlAgenda.setLayout(pnlAgendaLayout);
        pnlAgendaLayout.setHorizontalGroup(
            pnlAgendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAgendaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlAgendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlAgendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmbHoraInicio, 0, 76, Short.MAX_VALUE)
                    .addComponent(cmbIntervalo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbHoraFin, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlAgendaLayout.setVerticalGroup(
            pnlAgendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAgendaLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(pnlAgendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cmbHoraInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlAgendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cmbIntervalo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlAgendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
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
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(cmbDoctores1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addComponent(pnlAgenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 226, Short.MAX_VALUE)))
                        .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlAgenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addComponent(cmbDoctores1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabPanel.addTab("Agenda", jPanel17);

        jPanel34.setBackground(new java.awt.Color(255, 255, 255));

        cmbDoctores2.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        cmbDoctores2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Escoja un usuario..." }));

        lbCelular4.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbCelular4.setText("Cantidad de columnas:");

        txtColumnas.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtColumnas.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtColumnas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtColumnasKeyReleased(evt);
            }
        });

        lbCelular8.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbCelular8.setText("Cantidad de filas:");

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

        lbCelular6.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbCelular6.setText("Vista previa:");

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
        btnGenerarTabla.setText("GENERAR TABLA");
        btnGenerarTabla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarTablaActionPerformed(evt);
            }
        });

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Meseros", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 0, 10))); // NOI18N

        lbTelefono67.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTelefono67.setText("Solo visible mesas:");

        rdSiSoloVisibleMesas.setBackground(new java.awt.Color(255, 255, 255));
        grupoSoloVisibleMesas.add(rdSiSoloVisibleMesas);
        rdSiSoloVisibleMesas.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rdSiSoloVisibleMesas.setSelected(true);
        rdSiSoloVisibleMesas.setText("Si");
        rdSiSoloVisibleMesas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdSiSoloVisibleMesasActionPerformed(evt);
            }
        });

        rdNoSoloVisibleMesas.setBackground(new java.awt.Color(255, 255, 255));
        grupoSoloVisibleMesas.add(rdNoSoloVisibleMesas);
        rdNoSoloVisibleMesas.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rdNoSoloVisibleMesas.setText("No");
        rdNoSoloVisibleMesas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdNoSoloVisibleMesasActionPerformed(evt);
            }
        });

        lbTelefono73.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTelefono73.setText("Facturar mesas:");

        rdSiFacturarMesas.setBackground(new java.awt.Color(255, 255, 255));
        grupoFacturarMesas.add(rdSiFacturarMesas);
        rdSiFacturarMesas.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rdSiFacturarMesas.setSelected(true);
        rdSiFacturarMesas.setText("Si");
        rdSiFacturarMesas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdSiFacturarMesasActionPerformed(evt);
            }
        });

        rdNoFacturarMesas.setBackground(new java.awt.Color(255, 255, 255));
        grupoFacturarMesas.add(rdNoFacturarMesas);
        rdNoFacturarMesas.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rdNoFacturarMesas.setText("No");
        rdNoFacturarMesas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdNoFacturarMesasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbTelefono73, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbTelefono67, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(rdSiSoloVisibleMesas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdNoSoloVisibleMesas))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(rdSiFacturarMesas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdNoFacturarMesas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(rdSiSoloVisibleMesas, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(lbTelefono67, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE))
                    .addComponent(rdNoSoloVisibleMesas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(rdSiFacturarMesas, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(lbTelefono73, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                    .addComponent(rdNoFacturarMesas, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));

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
        btnActualizarMesas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarMesasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnActualizarMesas, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(btnActualizarMesas, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
        jPanel34.setLayout(jPanel34Layout);
        jPanel34Layout.setHorizontalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel34Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lbCelular10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNumMesas, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel34Layout.createSequentialGroup()
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbCelular6)
                            .addGroup(jPanel34Layout.createSequentialGroup()
                                .addComponent(lbCelular4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtColumnas, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(21, 21, 21)
                                .addComponent(lbCelular8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtFilas, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37)
                                .addComponent(btnGenerarTabla))
                            .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 121, Short.MAX_VALUE)
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbDoctores2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel34Layout.setVerticalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel34Layout.createSequentialGroup()
                        .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel34Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(lbCelular8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(lbCelular4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtColumnas)
                            .addComponent(txtFilas)
                            .addComponent(btnGenerarTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbCelular6))
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel34Layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(cmbDoctores2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel34Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNumMesas, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbCelular10, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        tabPanel.addTab("Restaurante", jPanel34);

        jPanel35.setBackground(new java.awt.Color(255, 255, 255));

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

        javax.swing.GroupLayout jPanel35Layout = new javax.swing.GroupLayout(jPanel35);
        jPanel35.setLayout(jPanel35Layout);
        jPanel35Layout.setHorizontalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel35Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pnlAgenda1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel35Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmbDoctores3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(232, 232, 232)))
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(470, Short.MAX_VALUE))
        );
        jPanel35Layout.setVerticalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel35Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(pnlAgenda1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(103, 103, 103)
                .addComponent(cmbDoctores3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(386, Short.MAX_VALUE))
        );

        tabPanel.addTab("Veterinario", jPanel35);

        pnlFormulario1.setBackground(new java.awt.Color(255, 255, 255));

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
                    .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 651, Short.MAX_VALUE)
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
                        .addGap(0, 515, Short.MAX_VALUE)
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
                .addComponent(jScrollPane14, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
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

        javax.swing.GroupLayout pnlFormulario1Layout = new javax.swing.GroupLayout(pnlFormulario1);
        pnlFormulario1.setLayout(pnlFormulario1Layout);
        pnlFormulario1Layout.setHorizontalGroup(
            pnlFormulario1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormulario1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(pnlFormulario1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlFormulario1Layout.createSequentialGroup()
                        .addComponent(jPanel50, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlFormulario1Layout.setVerticalGroup(
            pnlFormulario1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormulario1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jPanel49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlFormulario1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel50, javax.swing.GroupLayout.PREFERRED_SIZE, 510, Short.MAX_VALUE)
                    .addComponent(jPanel47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        tabPanel.addTab("Parqueadero", pnlFormulario1);

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel1.setBackground(new java.awt.Color(102, 204, 255));
        jLabel1.setText("1");

        jLabel5.setBackground(new java.awt.Color(102, 204, 255));
        jLabel5.setText("1");

        txtIdProd.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtIdProd.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(262, 262, 262)
                .addComponent(txtIdProd, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtIdProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        btnGuardar2.setBackground(new java.awt.Color(153, 153, 255));
        btnGuardar2.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnGuardar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/usuarios.png"))); // NOI18N
        btnGuardar2.setText("USUARIOS");
        btnGuardar2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnGuardar2.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnGuardar2.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnGuardar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardar2ActionPerformed(evt);
            }
        });

        btnGuardarMaestra.setBackground(new java.awt.Color(46, 204, 113));
        btnGuardarMaestra.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnGuardarMaestra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
        btnGuardarMaestra.setText("GUARDAR");
        btnGuardarMaestra.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnGuardarMaestra.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnGuardarMaestra.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnGuardarMaestra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarMaestraActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(93, 173, 226));
        jButton1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/actualizar.png"))); // NOI18N
        jButton1.setText("LOGO");
        jButton1.setToolTipText("Montar logo empresarial");
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnResolucion.setBackground(new java.awt.Color(204, 204, 204));
        btnResolucion.setFont(new java.awt.Font("Century Gothic", 0, 10)); // NOI18N
        btnResolucion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/listado.png"))); // NOI18N
        btnResolucion.setText("RESOLUCION");
        btnResolucion.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnResolucion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResolucionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnResolucion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(btnGuardar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnGuardarMaestra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(btnGuardarMaestra)
                .addGap(5, 5, 5)
                .addComponent(jButton1)
                .addGap(5, 5, 5)
                .addComponent(btnGuardar2)
                .addGap(5, 5, 5)
                .addComponent(btnResolucion)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlFormularioLayout = new javax.swing.GroupLayout(pnlFormulario);
        pnlFormulario.setLayout(pnlFormularioLayout);
        pnlFormularioLayout.setHorizontalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlFormularioLayout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tabPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(2, 2, 2)))
                .addContainerGap())
        );
        pnlFormularioLayout.setVerticalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tabPanel)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
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
        boolean registrar = false;

        if (ControladorAlertas.option("¿Desea continuar?")) {

            if (!txtContra.getText().equals(txtContra2.getText())) {
                ControladorAlertas.alert("Las contraseñas no coinciden");
                txtContra.requestFocus();
                return;
            }

            if (instancias.getConfiguraciones().isMedico()) {
                if (rdNoGenerarOrden.isSelected()) {
                    if (txtaCobrar1.getText().equals("")) {
                        ControladorAlertas.alertFail("Falta servicio a cobrar");
                        return;
                    }
                }

                if (rdNoGenerarOrden.isSelected()) {
                    ndCamposOrdenPredeterminada nodo = instancias.getSql().getDatosCamposOrdenPredeterminada("1");
                    if (nodo.getId() == null) {
                        ControladorAlertas.alertFail("No ha predeterminado los campos de la orden");
                        return;
                    }
                }
            }

            String regimen = "", utilidad = "SI", descuento = "porcentaje", ubicacion = "SI", impresion = "", tituloFactura = "Factura de venta No.";

            tblInformacionEmpresa.requestFocus();

            if (!getCeldaBooleanaPorEtiqueta(tblConfiguraciones, "Facturar Utilidad Sobrepasada", 1)) {
                utilidad = "NO";
            }

            if ("Valor".equals(getCeldaTextoPorEtiqueta(tblConfiguraciones1, "Tipo de descuento", 1))) {
                descuento = "peso";
            }

            if (!getCeldaBooleanaPorEtiqueta(tblOpcionesImpresiones, "Imprimir ubicación en factura", 1)) {
                ubicacion = "NO";
            }

            if (getCeldaBooleanaPorEtiqueta(tblImpresoraPorTipo, "Pos", 2)) {
                impresion = "pos";
            } else if (getCeldaBooleanaPorEtiqueta(tblImpresoraPorTipo, "Carta", 2)) {
                impresion = "facturaCompleta";
            } else {
                impresion = "factura";
            }

            String tituloTabla = getCeldaTextoPorEtiqueta(tblConfiguraciones1, "Título en la factura", 1);
            if (!tituloTabla.equals("")) {
                tituloFactura = tituloTabla;
            }

            String impresionPos = getCeldaTextoPorEtiqueta(tblImpresoraPorTipo, "Pos", 1).toLowerCase();

            StringBuilder sbResp = new StringBuilder();
            for (int i = 5; i < tblInformacionEmpresa.getRowCount(); i++) {
                Object checked = tblInformacionEmpresa.getValueAt(i, 1);
                if (Boolean.TRUE.equals(checked)) {
                    if (sbResp.length() > 0) {
                        sbResp.append(", ");
                    }
                    sbResp.append(tblInformacionEmpresa.getValueAt(i, 0).toString());
                }
            }
            String responsabilidades = sbResp.toString();

            ModeloMaestraGeneral general = construirModeloGeneral(regimen, utilidad, descuento, tituloFactura, responsabilidades);
            if (general == null) {
                ControladorAlertas.alertFail("No se pudo cargar la configuración actual");
                return;
            }

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
            impresiones.setImpresoraPos(impresionPos);
            impresiones.setImpresoraMediaCarta(getCeldaTextoPorEtiqueta(tblImpresoraPorTipo, "Media carta", 1));
            impresiones.setImpresoraCarta(getCeldaTextoPorEtiqueta(tblImpresoraPorTipo, "Carta", 1));
            impresiones.setImpresoraComanda(getCeldaTextoPorEtiqueta(tblImpresoraPorTipo, "Comanda", 1));
            impresiones.setImpresoraPrefactura(getCeldaTextoPorEtiqueta(tblImpresoraPorTipo, "Prefactura", 1));
            impresiones.setTipoPredeterminado(impresion);
            impresiones.setMostrarUbicacion(ubicacion);
            impresiones.setImprimirHora(getCeldaBooleanaPorEtiqueta(tblOpcionesImpresiones, "Imprimir hora en factura", 1));
            impresiones.setImprimirRetenciones(getCeldaBooleanaPorEtiqueta(tblOpcionesImpresiones, "Imprimir retenciones en factura", 1));
            impresiones.setImprimirImpoconsumo(getCeldaBooleanaPorEtiqueta(tblOpcionesImpresiones, "Imprimir impoconsumo en factura", 1));
            impresiones.setImprimirRecodiga(getCeldaBooleanaPorEtiqueta(tblOpcionesImpresiones, "Imprimir recodiga en cuadre de caja", 1));
            impresiones.setImprimirCuadreFiscal(getCeldaBooleanaPorEtiqueta(tblOpcionesImpresiones, "Imprimir cuadre fiscal en cuadre de caja", 1));
            impresiones.setImpresionPorGrupo(rdImpresionPorGrupo.isSelected());
            impresiones.setFormatoImpresion(txtNombreFormato.getText());
            impresiones.setPiePagina(getCeldaTextoPorEtiqueta(tblInformacionImpresion, "Pie de página", 1));
            impresiones.setInformacionLegal(getCeldaTextoPorEtiqueta(tblInformacionImpresion, "Información legal", 1));
            impresiones.setAnexoFactura(getCeldaTextoPorEtiqueta(tblInformacionImpresion, "Anexo factura", 1));
            impresiones.setAnexoOrdenServicio(getCeldaTextoPorEtiqueta(tblInformacionImpresion, "Anexo orden de servicio", 1));

            if (!daoMaestra.guardar(general, impresiones)) {
                ControladorAlertas.alertFail("Hubo un problema al guardar los cambios");
                return;
            }

            instancias.getSql().eliminarTodo("bdImpresoras");
            instancias.getSql().eliminarTodo("bdTarjetas");

            if (instancias.getConfiguraciones().isAgenda()) {
                instancias.getSql().eliminarTodo("bdBloqueoAgenda");
                for (int i = 0; i < tblDiasBloqueo.getRowCount(); i++) {
                    String horas = "";

                    try {
                        horas = tblDiasBloqueo.getValueAt(i, 1).toString();
                    } catch (Exception e) {
                        horas = "";
                    }

                    instancias.getSql().agregarFechaBloqueo(tblDiasBloqueo.getValueAt(i, 0).toString(), tblDiasBloqueo.getValueAt(i, 2).toString(),
                            tblDiasBloqueo.getValueAt(i, 3).toString(), tblDiasBloqueo.getValueAt(i, 4).toString(), horas);
                }
            }

            for (int i = 0; i < tblTarjetas.getRowCount(); i++) {
                instancias.getSql().agregarTarjetas(tblTarjetas.getValueAt(i, 0).toString(), tblTarjetas.getValueAt(i, 1).toString());
            }

            for (int i = 1; i < tblImpresoras1.getColumnCount(); i++) {
                try {
                    String nombre = tblImpresoras1.getValueAt(0, i).toString();
                    if (!nombre.equals("")) {
                        instancias.getSql().agregarImpresora(nombre);
                    }
                } catch (Exception e) {
                }
            }

            if (instancias.getConfiguraciones().isRestaurante()) {
                instancias.getSql().eliminarTodo("bdMesas");
                java.util.List<String[]> mesas = panelDisenoMesas.getMesasParaGuardar();
                for (int m = 0; m < mesas.size(); m++) {
                    instancias.getSql().agregarRegistroMesa(mesas.get(m)[0], mesas.get(m)[1]);
                }
            }

            if (instancias.getConfiguraciones().isMedico()) {

                String valor = "0";
                try {
                    ModeloProducto prod = daoProducto.obtenerDatosProducto(txtaCobrar.getText(), Bodegas.predeterminada());
                    valor = prod.getL1();
                } catch (Exception e) {

                }

                ndCamposOrdenPredeterminada nodoMed = instancias.getSql().getDatosCamposOrdenPredeterminada("1");

                if (nodoMed.getId() == null) {
                    registrar = true;
                }

                String tipo = "procedimiento";

                if (rdConsulta.isSelected()) {
                    tipo = "consulta";
                }

                //guardar en ordenServicio
                Object[] vectorMed = {"1", tipo, cmbConcepto.getSelectedItem().toString(), cmbFinalidad.getSelectedItem().toString(),
                    cmbCausa.getSelectedItem().toString(), txtIdProd.getText(),
                    cmbPersonal.getSelectedItem().toString(), actoQuirurgicoDesc(cmbActoQuirurgico.getSelectedItem().toString()), cmbForma.getSelectedItem().toString(),
                    cmbAmbito.getSelectedItem(), cmbCodigoConsulta.getSelectedItem()};

                nodoMed = metodos.llenarCamposOrdenPredeterminada(vectorMed);

                if (registrar) {
                    if (!instancias.getSql().agregarCamposOrdenPredeterminada(nodoMed)) {
                        ControladorAlertas.alertFail("Hubo un problema al guardar la Orden Predeterminada");
                        return;
                    }
                } else {
                    if (!instancias.getSql().modificarCamposOrdenPredeterminada(nodoMed)) {
                        ControladorAlertas.alertFail("Hubo un problema al modificar la Orden Predeterminada");
                        return;
                    }

                }
            }

            if (instancias.getConfiguraciones().isParqueadero()) {

                Boolean egreso;
                Boolean cupo;
                Boolean diasPlazo;
                Boolean cobrarCasco;
                Boolean modificarPrecio;
                BigDecimal cobro = BigDecimal.ZERO;

                if (rbtSi3.isSelected()) {
                    try {
                        cobro = big.getMoneda(txtCosto.getText());
                    } catch (Exception e) {
                        cobro = big.getMoneda(this.simbolo + " 0");
                    }

                    if (cobro.compareTo(BigDecimal.ZERO) <= 0) {
                        ControladorAlertas.alert("El casco debe ser mayor a " + this.simbolo + " 0");
                        return;
                    }
                }

                if (rbtNo.isSelected()) {
                    egreso = false;
                } else {
                    egreso = true;
                }

                if (rbtNo1.isSelected()) {
                    cupo = false;
                } else {
                    cupo = true;
                }

                if (rbtNo2.isSelected()) {
                    diasPlazo = false;
                } else {
                    diasPlazo = true;
                }

                if (rbtNo3.isSelected()) {
                    cobrarCasco = false;
                } else {
                    cobrarCasco = true;
                }

                if (rbtNoModificarValor.isSelected()) {
                    modificarPrecio = false;
                } else {
                    modificarPrecio = true;
                }

                instancias.getSql().eliminarTodo("bdServicios");
                for (int i = 0; i < tblConfig.getRowCount(); i++) {
                    if (!instancias.getSql().agregarServicios(tblConfig.getValueAt(i, 0).toString(), tblConfig.getValueAt(i, 1).toString(), big.getMoneda(tblConfig.getValueAt(i, 2).toString()),
                            tblConfig.getValueAt(i, 3).toString(), tblConfig.getValueAt(i, 4).toString(), big.getMoneda(tblConfig.getValueAt(i, 5).toString()), tblConfig.getValueAt(i, 6).toString(),
                            big.getMoneda(tblConfig.getValueAt(i, 7).toString()), tblConfig.getValueAt(i, 8).toString(), big.getMoneda(tblConfig.getValueAt(i, 9).toString()),
                            tblConfig.getValueAt(i, 10).toString(), big.getMoneda(tblConfig.getValueAt(i, 11).toString()), false, false,
                            tblConfig.getValueAt(i, 14).toString(), tblConfig.getValueAt(i, 15).toString())) {
                        ControladorAlertas.alertFail("Hubo un problema al guardar los cambios");
                        return;
                    }
                }

                String idMaestra = instancias.getSql().getIdMaestraParqueadero();

                System.out.println("vamos a ver: " + idMaestra);

                if (!idMaestra.equals("")) {
                    if (!instancias.getSql().modificarMaestraParqueadero(egreso, "", "", "", "", cupo, diasPlazo, cobrarCasco, cobro, modificarPrecio)) {
                        ControladorAlertas.alertFail("Error al guardar la configuración");
                        return;
                    }
                } else {
                    if (!instancias.getSql().insertarMaestraParqueadero(egreso, "", "", "", "", cupo, diasPlazo, cobrarCasco, cobro, modificarPrecio)) {
                        ControladorAlertas.alertFail("Error al guardar la configuración");
                        return;
                    }
                }

                llenarTablaServicios();
                instancias.getParqueadero().actualizarTablas();
                instancias.getMensualidad().moduloDias();
            }

            if (instancias.getConfiguraciones().isLaboratorio()) {
                instancias.getSql().eliminarTodo("bdExamenesLaboratorio");
                for (int i = 0; i < tblExamenes.getRowCount(); i++) {
                    String codigo;
                    try {
                        codigo = tblExamenes.getValueAt(i, 0).toString();
                    } catch (Exception e) {
                        codigo = "";
                    }

                    String descripcion;
                    try {
                        descripcion = tblExamenes.getValueAt(i, 1).toString();
                    } catch (Exception e) {
                        descripcion = "";
                    }

                    BigDecimal precio;
                    try {
                        precio = big.getMoneda(tblExamenes.getValueAt(i, 2).toString());
                    } catch (Exception e) {
                        precio = BigDecimal.ZERO;
                    }
                    System.out.println("precio " + precio);

                    String codProd;
                    try {
                        codProd = tblExamenes.getValueAt(i, 3).toString();
                    } catch (Exception e) {
                        codProd = "";
                    }

                    instancias.getSql().agregarExamenesLaboratorio(codigo, descripcion, precio, codProd);
                }
            }

            for (int i = 1; i < tblImpresoras1.getRowCount(); i++) {
                try {
                    for (int j = 1; j < tblImpresoras1.getColumnCount(); j++) {
                        boolean siEsta = false;
                        if (tblImpresoras1.getValueAt(i, j).equals("SELECCIONADO")) {
                            siEsta = true;
                        }

                        if (siEsta) {
                            String grupo = tblImpresoras1.getValueAt(i, 0).toString().split(". ")[0];
                            instancias.getSql().modificarImpresoraGrupo(grupo, tblImpresoras1.getValueAt(0, j).toString());
                        }
                    }
                } catch (Exception e) {
                }
            }

            ControladorAlertas.alertSuccess("Cambios guardados con éxito");

            tabPanel.setSelectedIndex(0);

            llenarDatos();

            /* ACTUALIZAMOS MAESTROS EN EL MODULO DE FACTURACION */
            DaoMaestra _daoM = new DaoMaestra();
        DatosMaestra.setearDatosMaestra(_daoM.obtenerGeneral(), _daoM.obtenerImpresiones());

            instancias.getVistaAjusteInventario().consultarMaestros();
            instancias.getTrasladosInternos().consultarMaestros();
            instancias.getReporte().consultarMaestros();

            /* FIN ACTUALIZACION MAESTROS EN EL MODULO DE FACTURACION */
            instancias.getReimpresion().consultarMaestros();
            instancias.getAbonos().consultarMaestros();

            if (instancias.getConfiguraciones().isVeterinaria()) {
                instancias.getHospitalizacion().consultarMaestros();
                instancias.getFormatos().consultarMaestros();
            }

            if (instancias.getConfiguraciones().isLaboratorio()) {
                instancias.getHistoriaLaboratorio().actualizarExamenes();
            }
        }
    }//GEN-LAST:event_btnGuardarMaestraActionPerformed

    private int parseIntSafe(String valor) {
        try {
            return Integer.parseInt(valor == null ? "0" : valor.trim());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private ModeloMaestraGeneral construirModeloGeneral(String regimen, String utilidad, String descuento, String tituloFactura, String responsabilidades) {
        ModeloMaestraGeneral general = daoMaestra.obtenerGeneral();
        if (general == null) return null;
        general.setConfiguracionRegimen(regimen);
        general.setAlertaUtilidad(utilidad);
        general.setTipoDescuento(descuento);
        general.setContrasena(txtContra.getText());
        general.setDiasAlertaCuadre(parseIntSafe(getCeldaTextoPorEtiqueta(tblConfiguraciones1, "Días para alerta de cartera", 1)));
        general.setNitEmpresa(getCeldaTextoPorEtiqueta(tblInformacionEmpresa, "NIT", 1).toUpperCase());
        general.setNombreEmpresa(getCeldaTextoPorEtiqueta(tblInformacionEmpresa, "Nombre", 1).toUpperCase());
        general.setRegimenEmpresa(getCeldaTextoPorEtiqueta(tblInformacionEmpresa, "Régimen", 1).toUpperCase());
        general.setDireccionEmpresa(getCeldaTextoPorEtiqueta(tblInformacionEmpresa, "Dirección", 1).toUpperCase());
        general.setTelefonoEmpresa(getCeldaTextoPorEtiqueta(tblInformacionEmpresa, "Teléfono", 1).toUpperCase());
        general.setUsarLector(getCeldaBooleanaPorEtiqueta(tblConfiguraciones, "Usar lector", 1));
        general.setTituloFactura(tituloFactura);
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
        general.setOcultarInformacionCliente(getCeldaBooleanaPorEtiqueta(tblConfiguraciones, "Validar el cupo del credito", 1));
        general.setMostrarInformacionCuadre(false);
        general.setDescuentoMaximoVentas(parseIntSafe(getCeldaTextoPorEtiqueta(tblConfiguraciones1, "Descuento máximo para aplicar", 1)));
        general.setTipoIdPrestadorServicio(String.valueOf(cmbTipoPrestador.getSelectedItem()));
        general.setLimiteProductosPorFactura(parseIntSafe(getCeldaTextoPorEtiqueta(tblConfiguraciones1, "Límite de productos (carta/media carta)", 1)));
        general.setCombinarProductos(getCeldaBooleanaPorEtiqueta(tblConfiguraciones, "Combinar productos iguales", 1));
        general.setModificarNombre(getCeldaBooleanaPorEtiqueta(tblConfiguraciones, "Modificar nombre de productos", 1));
        general.setTurno(getCeldaBooleanaPorEtiqueta(tblConfiguraciones, "Activar turnos", 1));
        general.setTurnoConsecutivo(parseIntSafe(getCeldaTextoPorEtiqueta(tblConfiguraciones1, "Número de turno", 1)));
        general.setConsecutivoAdicional(getCeldaBooleanaPorEtiqueta(tblConfiguraciones, "Consecutivo adicional en factura", 1));
        general.setModificarPrecio(getCeldaBooleanaPorEtiqueta(tblConfiguraciones, "Modificar precio de productos", 1));
        general.setBorrarCongelada(instancias.getConfiguraciones().isRestaurante() && getCeldaBooleanaPorEtiqueta(tblConfiguraciones, "Borrar productos en mesas", 1));
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
        general.setCiudadBuscador(getCeldaBooleanaPorEtiqueta(tblConfiguraciones, "Módulo de solicitudes de permisos", 1));
        general.setFoco(getCeldaTextoPorEtiqueta(tblConfiguraciones1, "Foco después de cargar el producto", 1));
        general.setFacturarMesas(rdSiFacturarMesas.isSelected());
        general.setSoloMesas(rdSiSoloVisibleMesas.isSelected());
        general.setBorrarMesas(instancias.getConfiguraciones().isRestaurante() && getCeldaBooleanaPorEtiqueta(tblConfiguraciones, "Borrar mesas", 1));
        general.setPassword("");
        general.setResponsabilidadesFiscales(responsabilidades);
        return general;
    }

    private void btnGuardar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar2ActionPerformed
        try {
            instancias.getUsuarios().setSelected(true);
            instancias.getMenu().cambiarTitulo("INFORMACIÓN DEL USUARIO");

        } catch (PropertyVetoException ex) {
            Logger.getLogger(vistaMaestra.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnGuardar2ActionPerformed

    private void txtDiasAlertaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiasAlertaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiasAlertaKeyReleased

    private void txtDiasAlertaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiasAlertaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiasAlertaKeyTyped

    private void lectorNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lectorNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lectorNoActionPerformed

    private void txtTituloFacturaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTituloFacturaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTituloFacturaKeyReleased

    private void txtTituloFacturaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTituloFacturaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTituloFacturaKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            String origen = "";
            try {
                origen = metodos.obtenerRuta3(this, "logoEmpresa");
            } catch (Exception e) {
                return;
            }

            String destino = System.getProperty("user.dir") + "\\imagenes\\conf\\logoEmpresa.png";
//            metodosGenerales.copiarArchivo(origen, destino);
            metodos.montarLogo(origen, destino);
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
            System.out.println(e);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnResolucionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResolucionActionPerformed
        vistaResoluciones resoluciones = new vistaResoluciones(null, true);
        resoluciones.setVisible(true);
    }//GEN-LAST:event_btnResolucionActionPerformed

    private void rdNoPvpIvaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdNoPvpIvaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdNoPvpIvaActionPerformed

    private void txtPorcentajeMoraKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPorcentajeMoraKeyReleased
        if (txtPorcentajeMora.getText().contains(",")) {
            txtPorcentajeMora.setText(txtPorcentajeMora.getText().replace(",", "."));
        }
    }//GEN-LAST:event_txtPorcentajeMoraKeyReleased

    private void txtPorcentajeMoraKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPorcentajeMoraKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtPorcentajeMoraKeyTyped

    private void txtDiasCobrarMoraKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiasCobrarMoraKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtDiasCobrarMoraKeyTyped

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
            txtIdProd.setText("");

        }
    }//GEN-LAST:event_rdNoGenerarOrdenItemStateChanged

    private void rdNoImprimirOrdenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdNoImprimirOrdenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdNoImprimirOrdenActionPerformed

    private void txtCodigoPrestadorServicoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoPrestadorServicoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoPrestadorServicoKeyTyped

    private void txtDescuentoMaximoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescuentoMaximoKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtDescuentoMaximoKeyTyped

    private void btnGuardar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar3ActionPerformed
        PrintService[] impresoras = PrintServiceLookup.lookupPrintServices(null, null);
        DefaultTableModel model = (DefaultTableModel) tblImpresoras.getModel();

        while (tblImpresoras.getRowCount() > 0) {
            model.removeRow(0);
        }

        if (impresoras.length > 0) {
            for (int i = 0; i < impresoras.length; i++) {
                if (!impresoras[i].toString().split(" : ")[1].equals("Fax")) {
                    model.addRow(new Object[]{impresoras[i].toString().split(" : ")[1]});
                }
            }
        }

        DaoMaestra _daoM = new DaoMaestra();
        DatosMaestra.setearDatosMaestra(_daoM.obtenerGeneral(), _daoM.obtenerImpresiones());
        String impresionPos = DatosMaestra.getImpresoraPos() != null ? DatosMaestra.getImpresoraPos() : "";
        String impresionMediaCarta = DatosMaestra.getImpresoraMediaCarta() != null ? DatosMaestra.getImpresoraMediaCarta() : "";
        String impresionCarta = DatosMaestra.getImpresoraCarta() != null ? DatosMaestra.getImpresoraCarta() : "";

        inicializarTablaImpresoraPorTipo();
        setFilaTabla(tblImpresoraPorTipo, "Carta", 1, impresionCarta);
        setFilaTabla(tblImpresoraPorTipo, "Media carta", 1, impresionMediaCarta);
        setFilaTabla(tblImpresoraPorTipo, "Pos", 1, impresionPos);
        setFilaTabla(tblImpresoraPorTipo, "Comanda", 1, DatosMaestra.getImpresoraComanda() != null ? DatosMaestra.getImpresoraComanda() : "");
        setFilaTabla(tblImpresoraPorTipo, "Prefactura", 1, DatosMaestra.getImpresoraPrefactura() != null ? DatosMaestra.getImpresoraPrefactura() : "");
        String l2 = DatosMaestra.getL2() != null ? DatosMaestra.getL2() : "";
        if ("pos".equals(l2)) {
            setFilaTabla(tblImpresoraPorTipo, "Pos", 2, true);
        } else if ("facturaCompleta".equals(l2)) {
            setFilaTabla(tblImpresoraPorTipo, "Carta", 2, true);
        } else {
            setFilaTabla(tblImpresoraPorTipo, "Media carta", 2, true);
        }
    }//GEN-LAST:event_btnGuardar3ActionPerformed

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

    private void txtLimiteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLimiteKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtLimiteKeyTyped

    private void rdTurnoNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdTurnoNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdTurnoNoActionPerformed

    private void txtTurnoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTurnoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTurnoKeyReleased

    private void txtTurnoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTurnoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTurnoKeyTyped

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        instancias.getSql().aumentarTurno(getCeldaTextoPorEtiqueta(tblConfiguraciones1, "Número de turno", 1));
        ControladorAlertas.alertSuccess("¡Turno actualizado correctamente!");
        instancias.getFactura().actualizarVistaConsecutivo();
        instancias.getPedido().actualizarVistaConsecutivo();
    }//GEN-LAST:event_jButton5ActionPerformed

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

    private void btnGuardar5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar5ActionPerformed
        if (txtFranquisia.getText().equals("")) {
            ControladorAlertas.alertFail("Ingrese la franquisia");
            return;
        }

        if (txtComision.getText().equals("")) {
            ControladorAlertas.alertFail("Ingrese la comisión");
            return;
        }

        for (int i = 0; i < tblTarjetas.getRowCount(); i++) {
            if (tblTarjetas.getValueAt(i, 0).toString().equalsIgnoreCase(txtFranquisia.getText())) {
                ControladorAlertas.alertFail("La franquisia ya existe");
                return;
            }
        }

        DefaultTableModel modelo = (DefaultTableModel) tblTarjetas.getModel();
        modelo.addRow(new Object[]{txtFranquisia.getText(), txtComision.getText()});

        ControladorAlertas.alertSuccess("Tarjeta cargada con exito!");

        txtFranquisia.setText("");
        txtComision.setText("");
    }//GEN-LAST:event_btnGuardar5ActionPerformed

    private void popBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popBorrarActionPerformed
        if (tblTarjetas.getSelectedRow() > -1) {

            int fila = tblTarjetas.getSelectedRow();

            DefaultTableModel modelo = (DefaultTableModel) tblTarjetas.getModel();
            modelo.removeRow(fila);
            tblTarjetas.removeEditor();
        } else {
            ControladorAlertas.alert("Seleccione una franquisia");
        }
    }//GEN-LAST:event_popBorrarActionPerformed

    private void tblDiasBloqueoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDiasBloqueoMouseClicked

    }//GEN-LAST:event_tblDiasBloqueoMouseClicked

    private void dtFechaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dtFechaMouseClicked
        if (evt.getClickCount() >= 2) {
            dtFecha.setVisible(false);
            jButton1.setVisible(true);
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

    private void rdSiBorrarProdMesasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdSiBorrarProdMesasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdSiBorrarProdMesasActionPerformed

    private void rdNoBorrarProdMesasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdNoBorrarProdMesasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdNoBorrarProdMesasActionPerformed

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

    private void txtCantidadEstablecidaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadEstablecidaKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtCantidadEstablecidaKeyTyped

    private void txtPorcPropinaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPorcPropinaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPorcPropinaKeyTyped

    private void txtContraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtContraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtContraActionPerformed

    private void rdNoSolicitudesPermisosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdNoSolicitudesPermisosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdNoSolicitudesPermisosActionPerformed

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

    private void btnOcultarImpresiones1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOcultarImpresiones1ActionPerformed
        if (pnlImpresionesGrupo.isVisible()) {
            pnlImpresionesGrupo.setVisible(false);
            btnOcultarImpresiones1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/boton-de-suma.png")));
        } else {
            pnlImpresionesGrupo.setVisible(true);
            btnOcultarImpresiones1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/boton-resta.png")));
        }
    }//GEN-LAST:event_btnOcultarImpresiones1ActionPerformed

    private void rdSiSoloVisibleMesasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdSiSoloVisibleMesasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdSiSoloVisibleMesasActionPerformed

    private void rdNoSoloVisibleMesasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdNoSoloVisibleMesasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdNoSoloVisibleMesasActionPerformed

    private void rdSiFacturarMesasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdSiFacturarMesasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdSiFacturarMesasActionPerformed

    private void rdNoFacturarMesasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdNoFacturarMesasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdNoFacturarMesasActionPerformed

    private void rdSiBorrarMesasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdSiBorrarMesasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdSiBorrarMesasActionPerformed

    private void rdNoBorrarMesasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdNoBorrarMesasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdNoBorrarMesasActionPerformed

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

    private void rdSiPvpIvaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdSiPvpIvaItemStateChanged

    }//GEN-LAST:event_rdSiPvpIvaItemStateChanged

    private void rdNoPvpIvaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdNoPvpIvaItemStateChanged

    }//GEN-LAST:event_rdNoPvpIvaItemStateChanged

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

    /**
     * Las bodegas internas son de la empresa: no cuelgan de ningún contacto y
     * esta pestaña no tiene un Guardar propio, así que se escriben apenas se
     * acepta el modal.
     */
    private void capturarBodega(int fila) {
        VistaBodegaContacto modal = new VistaBodegaContacto(instancias.getMenu(), true);
        modal.preparar(fila < 0 ? null : bodegaDeLaFila(fila), nombresBodegasRegistradas(fila), "", Bodegas.TIPO_INTERNA);
        modal.setVisible(true);

        if (!modal.isAceptado()) {
            return;
        }

        ModeloBodega bodega = modal.getBodega();

        if (fila < 0) {
            bodega.setEstado(Bodegas.ESTADO_ACTIVO);
            bodega.setUsuario(instancias.getUsuario());

            if (daoBodegas.guardar(bodega) == null) {
                ControladorAlertas.alertFail("No fue posible guardar la bodega");
                return;
            }
            ControladorAlertas.alertSuccess("Bodega " + bodega.getCodigo() + " creada con éxito");
        } else {
            if (!daoBodegas.modificar(bodega)) {
                ControladorAlertas.alertFail("No fue posible modificar la bodega");
                return;
            }
            ControladorAlertas.alertSuccess("Bodega modificada con éxito");
        }

        cargarBodegasInternas();
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
        if (!activar && bodega.getCodigo().equals(Bodegas.predeterminada())) {
            ControladorAlertas.alertFail("La bodega " + bodega.getCodigo() + " es la predeterminada del sistema. "
                    + "Seleccione otra bodega por defecto antes de inactivarla");
            return;
        }

        if (!ControladorAlertas.option(activar ? "¿Activar la bodega " + bodega.getCodigo() + "?"
                : "¿Inactivar la bodega " + bodega.getCodigo() + "?")) {
            return;
        }

        bodega.setEstado(Bodegas.estado(activar));

        if (!daoBodegas.modificar(bodega)) {
            ControladorAlertas.alertFail("No fue posible cambiar el estado de la bodega");
            return;
        }

        if (activar) {
            ControladorAlertas.alertSuccess("Bodega activada con éxito");
        } else {
            ControladorAlertas.alert("Bodega inactivada, este registro podrá ser activado posteriormente");
        }
        cargarBodegasInternas();
    }

    private void cargarBodegasInternas() {
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
                    cmbBodegas.addItem(bodega.getCodigo() + " - " + bodega.getNombre());
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
     * Al cambiar la bodega predeterminada se refresca la maestra en memoria,
     * para que el resto del sistema empiece a mover inventario sobre ella sin
     * reiniciar.
     */
    private void guardarBodegaPredeterminada() {
        int indice = cmbBodegas.getSelectedIndex();

        if (cargandoBodegas || indice < 0 || indice >= bodegasCombo.size()) {
            return;
        }

        String codigo = bodegasCombo.get(indice).getCodigo();

        if (codigo.equals(Bodegas.predeterminada())) {
            return;
        }

        if (!new DaoMaestra().actualizarBodega(codigo)) {
            ControladorAlertas.alertFail("No fue posible guardar la bodega predeterminada");
            return;
        }

        DaoMaestra _daoM = new DaoMaestra();
        DatosMaestra.setearDatosMaestra(_daoM.obtenerGeneral(), _daoM.obtenerImpresiones());
        ControladorAlertas.alertSuccess("El sistema trabajará con la bodega " + codigo);
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
            txtIdProd.setText(nodo.getIdSistema());
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
    }

    public void llenarDatos() {
        DaoMaestra _lDao = new DaoMaestra();
        DatosMaestra.setearDatosMaestra(_lDao.obtenerGeneral(), _lDao.obtenerImpresiones());

        inicializarTablaConfiguracionesFacturacion();
        inicializarTablaConfiguraciones1(instancias.getConfiguraciones().isRestaurante(), instancias.getConfiguraciones().isCreditos());

        if (DatosMaestra.getResponsabilidadesRaw() != null && !DatosMaestra.getResponsabilidadesRaw().isEmpty()) {
            String[] responsabilidades = DatosMaestra.getResponsabilidadesRaw().split(", ");
            for (String resp : responsabilidades) {
                if (!resp.isEmpty()) {
                    setFilaTabla(tblInformacionEmpresa, resp.trim(), 1, Boolean.TRUE);
                }
            }
        }

        if (instancias.getConfiguraciones().isRestaurante()) {
            setFilaTabla(tblConfiguraciones, "Borrar mesas", 1, DatosMaestra.isBorrarMesas());
        }

        if (DatosMaestra.isSoloMesas()) {
            rdSiSoloVisibleMesas.setSelected(true);
        } else {
            rdNoSoloVisibleMesas.setSelected(true);
        }

        if (DatosMaestra.isFacturarMesas()) {
            rdSiFacturarMesas.setSelected(true);
        } else {
            rdNoFacturarMesas.setSelected(true);
        }

        // Cargar tblImpresoraPorTipo
        inicializarTablaImpresoraPorTipo();
        setFilaTabla(tblImpresoraPorTipo, "Carta", 1, DatosMaestra.getImpresoraCarta());
        setFilaTabla(tblImpresoraPorTipo, "Media carta", 1, DatosMaestra.getImpresoraMediaCarta());
        setFilaTabla(tblImpresoraPorTipo, "Pos", 1, DatosMaestra.getImpresoraPos());
        setFilaTabla(tblImpresoraPorTipo, "Comanda", 1, DatosMaestra.getImpresoraComanda());
        setFilaTabla(tblImpresoraPorTipo, "Prefactura", 1, DatosMaestra.getImpresoraPrefactura());

        if ("pos".equals(DatosMaestra.getL2())) {
            setFilaTabla(tblImpresoraPorTipo, "Pos", 2, true);
        } else if ("facturaCompleta".equals(DatosMaestra.getL2())) {
            setFilaTabla(tblImpresoraPorTipo, "Carta", 2, true);
        } else {
            setFilaTabla(tblImpresoraPorTipo, "Media carta", 2, true);
        }

        rdImpresionPorGrupo.setSelected(DatosMaestra.isImpresionPorGrupo());

        setFilaTabla(tblConfiguraciones1, "Foco después de cargar el producto", 1, DatosMaestra.getFocoDespuesDeCargarProducto());

        setFilaTabla(tblConfiguraciones, "Validar el cupo del credito", 1, DatosMaestra.isOcultarInformacionCliente());
        setFilaTabla(tblConfiguraciones, "Días de plazo automaticos", 1, DatosMaestra.isCargarDiasPlazoAutomaticamente());

        setFilaTabla(tblConfiguraciones, "Módulo de solicitudes de permisos", 1, DatosMaestra.isCiudadBuscador());

        cmbHoraInicio1.setSelectedItem(DatosMaestra.getInicioHosp());
        cmbHoraFin1.setSelectedItem(DatosMaestra.getFinHosp());
        cmbIntervalo1.setSelectedItem(DatosMaestra.getIntervalosHosp());

        setFilaTabla(tblConfiguraciones, "Mostrar devuelta en factura", 1, DatosMaestra.isMostrarDevuelta());

        if (instancias.getConfiguraciones().isRestaurante()) {
            setFilaTabla(tblConfiguraciones1, "Porcentaje de propina en pre-factura", 1, DatosMaestra.getPorcPropina());
        }
        txtNombreFormato.setText(DatosMaestra.getImprimirCada());

        String columnas = DatosMaestra.getColumnas();
        txtColumnas.setText(columnas);

        String filas = DatosMaestra.getFilas();
        txtFilas.setText(filas);

        if (!columnas.equals("") && !filas.equals("")) {
            btnGenerarTablaActionPerformed(null);
        }

        if (!instancias.getConfiguraciones().isParqueadero()) {
            tabPanel.setEnabledAt(9, false);
        }

        if (!instancias.getConfiguraciones().isVeterinaria()) {
            tabPanel.setEnabledAt(8, false);
        }

        if (instancias.getConfiguraciones().isRestaurante()) {
            Object[][] mesas = instancias.getSql().getPosicionesMesas();
            txtNumMesas.setText(String.valueOf(mesas.length));
            panelDisenoMesas.cargarMesasExistentes(mesas);
        } else {
            tabPanel.setEnabledAt(7, false);
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
            tabPanel.setEnabledAt(6, false);
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
            tabPanel.setEnabledAt(5, false);
        }

        jPanel13.setVisible(false);
        jPanel37.setVisible(false);
        jPanel42.setVisible(false);
        pnlCredito.setVisible(false);

        String infoEmpresa = "";
        if (!DatosMaestra.getD1().equals("")) {
            infoEmpresa += DatosMaestra.getD1();
        }
        if (!DatosMaestra.getD2().equals("")) {
            infoEmpresa += "\n" + DatosMaestra.getD2();
        }
        if (!DatosMaestra.getD3().equals("")) {
            infoEmpresa += "\n" + DatosMaestra.getD3();
        }
        if (!DatosMaestra.getD6().equals("")) {
            infoEmpresa += "\n" + DatosMaestra.getD6();
        }
        if (!DatosMaestra.getD7().equals("")) {
            infoEmpresa += "\n" + DatosMaestra.getD7();
        }

        if (!instancias.getRegimen().equals("")) {
            String datosEmpresa = metodosGenerales.convertToMultiline(infoEmpresa);
            String datosEmpresaReimpresion = infoEmpresa;
            String datosEmpresaCompleto = metodosGenerales.convertToMultiline(infoEmpresa);
            instancias.setDatosEmpresa(datosEmpresa, DatosMaestra.getLegal(), DatosMaestra.getPie(), datosEmpresaCompleto, datosEmpresaReimpresion);
        }

        Object[][] grupos = instancias.getSql().getGruposFactura();
        Object[][] impresoras = instancias.getSql().getImpresoras();
        Object[][] tarjetas = instancias.getSql().getTarjetas();
        Object[][] horasBloqueo = instancias.getSql().getHorasBloqueo();

        DefaultTableModel model = (DefaultTableModel) tblImpresoras1.getModel();
        DefaultTableModel modelo = (DefaultTableModel) tblTarjetas.getModel();
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

        while (tblTarjetas.getRowCount() > 0) {
            modelo.removeRow(0);
        }

        if (tarjetas.length > 0) {
            for (int i = 0; i < tarjetas.length; i++) {
                modelo.addRow(new Object[]{"", ""});
            }

            for (int i = 0; i < tarjetas.length; i++) {
                tblTarjetas.setValueAt(tarjetas[i][0], i, 0);
                tblTarjetas.setValueAt(tarjetas[i][1], i, 1);
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

        setFilaTabla(tblOpcionesImpresiones, "Imprimir impoconsumo en factura", 1, DatosMaestra.isMostrarImpoconsumo());
        setFilaTabla(tblOpcionesImpresiones, "Imprimir retenciones en factura", 1, DatosMaestra.isMostrarRetenciones());
        setFilaTabla(tblOpcionesImpresiones, "Imprimir hora en factura", 1, DatosMaestra.isHora());

        setFilaTabla(tblConfiguraciones, "Facturar sin inventario", 1, DatosMaestra.isFacturarSinInventario());

        // Cargar tblImpresionDocumentos
        inicializarTablaImpresionDocumentos();
        setFilaTabla(tblImpresionDocumentos, "Factura", 1, DatosMaestra.isPrevisualizarFactura());
        setFilaTabla(tblImpresionDocumentos, "Factura", 2, DatosMaestra.isCopiasFactura());
        setFilaTabla(tblImpresionDocumentos, "Factura", 3, DatosMaestra.getNumFactura());

        setFilaTabla(tblImpresionDocumentos, "Orden de servicio", 1, DatosMaestra.isPrevisualizarOServicio());
        setFilaTabla(tblImpresionDocumentos, "Orden de servicio", 2, DatosMaestra.isCopiasOServicio());
        setFilaTabla(tblImpresionDocumentos, "Orden de servicio", 3, DatosMaestra.getNumOServicio());

        setFilaTabla(tblImpresionDocumentos, "Cotización", 1, DatosMaestra.isPrevisualizarCotizacion());
        setFilaTabla(tblImpresionDocumentos, "Cotización", 2, DatosMaestra.isCopiasCotizacion());
        setFilaTabla(tblImpresionDocumentos, "Cotización", 3, DatosMaestra.getNumCotizacion());

        setFilaTabla(tblImpresionDocumentos, "Plan separe", 1, DatosMaestra.isPrevisualizarPlanSepare());
        setFilaTabla(tblImpresionDocumentos, "Plan separe", 2, DatosMaestra.isCopiasPlanSepare());
        setFilaTabla(tblImpresionDocumentos, "Plan separe", 3, DatosMaestra.getNumPlanSepare());

        setFilaTabla(tblImpresionDocumentos, "Pedido", 1, DatosMaestra.isPrevisualizarPedido());
        setFilaTabla(tblImpresionDocumentos, "Pedido", 2, DatosMaestra.isCopiasPedido());
        setFilaTabla(tblImpresionDocumentos, "Pedido", 3, DatosMaestra.getNumPedido());

        setFilaTabla(tblImpresionDocumentos, "Comanda", 1, DatosMaestra.isPrevisualizarComanda());
        setFilaTabla(tblImpresionDocumentos, "Comanda", 2, DatosMaestra.isPreguntarCopiasComanda());
        setFilaTabla(tblImpresionDocumentos, "Comanda", 3, DatosMaestra.getCopiasComanda());

        setFilaTabla(tblImpresionDocumentos, "Prefactura", 1, DatosMaestra.isPrevisualizarPrefactura());
        setFilaTabla(tblImpresionDocumentos, "Prefactura", 2, DatosMaestra.isPreguntarCopiasPrefactura());
        setFilaTabla(tblImpresionDocumentos, "Prefactura", 3, DatosMaestra.getCopiasPrefactura());

        if (instancias.getConfiguraciones().isRestaurante()) {
            setFilaTabla(tblConfiguraciones, "Borrar productos en mesas", 1, DatosMaestra.isBorrarCongelada());
        }

        setFilaTabla(tblConfiguraciones, "Modificar precio de productos", 1, DatosMaestra.isModificarPrecio());
        setFilaTabla(tblConfiguraciones, "Consecutivo adicional en factura", 1, DatosMaestra.isConsecutivoAdicional());

        setFilaTabla(tblConfiguraciones, "Activar turnos", 1, DatosMaestra.isTurnoActivo());

        setFilaTabla(tblConfiguraciones, "Modificar nombre de productos", 1, DatosMaestra.isModificarNombre());
        setFilaTabla(tblConfiguraciones, "Combinar productos iguales", 1, DatosMaestra.isCombinarProductos());

        setFilaTabla(tblConfiguraciones1, "Límite de productos (carta/media carta)", 1, DatosMaestra.getLimite());

        String tipoImpresion = DatosMaestra.getL2();
        if ("pos".equals(tipoImpresion)) {
            instancias.setTipoImpresion("Pos");
        } else {
            instancias.setTipoImpresion("");
        }
        instancias.setImpresion(tipoImpresion);

        instancias.getMenu().abrirCaja();

        try {
            if (DatosMaestra.getL4().equals("NO")) {
                instancias.setCopias(false);
            } else {
                instancias.setCopias(true);
            }
        } catch (Exception e) {
            instancias.setCopias(true);
        }

        instancias.getMenu().actualizarUsuario(instancias.getUsuario());

        try {
            setFilaTabla(tblConfiguraciones, "Facturar Utilidad Sobrepasada", 1, !"NO".equals(DatosMaestra.getC3()));
        } catch (Exception e) {
            setFilaTabla(tblConfiguraciones, "Facturar Utilidad Sobrepasada", 1, true);
        }
        try {
            setFilaTabla(tblConfiguraciones1, "Tipo de descuento", 1, "peso".equals(DatosMaestra.getC4()) ? "Valor" : "Porcentaje");
        } catch (Exception e) {
            setFilaTabla(tblConfiguraciones1, "Tipo de descuento", 1, "Porcentaje");
        }

        instancias.setSegundaClave(DatosMaestra.getC5());
        txtContra.setText(DatosMaestra.getC5());
        txtContra2.setText(DatosMaestra.getC5());

        inicializarTablaInformacionEmpresa();
        setFilaTabla(tblInformacionEmpresa, "Nombre", 1, DatosMaestra.getD2());
        setFilaTabla(tblInformacionEmpresa, "NIT", 1, DatosMaestra.getD1());
        setFilaTabla(tblInformacionEmpresa, "Teléfono", 1, DatosMaestra.getD7());
        setFilaTabla(tblInformacionEmpresa, "Régimen", 1, DatosMaestra.getD3());
        setFilaTabla(tblInformacionEmpresa, "Dirección", 1, DatosMaestra.getD6());
        inicializarTablaInformacionImpresion();
        setFilaTabla(tblInformacionImpresion, "Pie de página", 1, DatosMaestra.getPie());
        setFilaTabla(tblInformacionImpresion, "Información legal", 1, DatosMaestra.getLegal());
        setFilaTabla(tblInformacionImpresion, "Anexo factura", 1, DatosMaestra.getAnexoFacturacion());
        setFilaTabla(tblInformacionImpresion, "Anexo orden de servicio", 1, DatosMaestra.getAnexoOrdenServicio());
        javax.swing.SwingUtilities.invokeLater(() -> ajustarAlturaFilas(tblInformacionImpresion));

        try {
            setFilaTabla(tblOpcionesImpresiones, "Imprimir ubicación en factura", 1, !"NO".equals(DatosMaestra.getC6()));
        } catch (Exception e) {
            setFilaTabla(tblOpcionesImpresiones, "Imprimir ubicación en factura", 1, true);
        }

        setFilaTabla(tblConfiguraciones1, "Días para alerta de cartera", 1, DatosMaestra.getC7());

        setFilaTabla(tblOpcionesImpresiones, "Imprimir recodiga en cuadre de caja", 1, DatosMaestra.isRecogida());
        if (DatosMaestra.isRecogida()) {
            instancias.setImprimirRecogida(true);
        } else {
            instancias.setImprimirRecogida(false);
        }

        setFilaTabla(tblConfiguraciones, "Usar lector", 1, DatosMaestra.isLector());
        instancias.setLector(DatosMaestra.isLector());

        setFilaTabla(tblConfiguraciones1, "Título en la factura", 1, DatosMaestra.getTituloFactura());
        instancias.setTituloFactura(DatosMaestra.getTituloFactura());

        setFilaTabla(tblConfiguraciones, "Precios con impuestos", 1, DatosMaestra.isPvpSinIva());
        instancias.setPvpConIva(DatosMaestra.isPvpSinIva());
        instancias.setCostoConIva(DatosMaestra.isPvpSinIva());

        setFilaTabla(tblConfiguraciones, "Ventas Contado Predeterminado", 1, DatosMaestra.isVentasPredeterminado());
        instancias.setVentasPredeterminado(DatosMaestra.isVentasPredeterminado());
        setFilaTabla(tblConfiguraciones, "Alertar Utilidad Sobrepasada", 1, DatosMaestra.isMensajeUtilidad());
        instancias.setMensajeUtilidad(DatosMaestra.isMensajeUtilidad());

        instancias.setDiasAlertaResolucion(DatosMaestra.getDiasAlertaResolucion());
        instancias.setAlertaFechaDias(DatosMaestra.getAlertaFechaDias().isEmpty() ? "0" : DatosMaestra.getAlertaFechaDias());
        instancias.setAlertaCantidadDias(DatosMaestra.getAlertaCantidadNumeracion().isEmpty() ? "0" : DatosMaestra.getAlertaCantidadNumeracion());
        instancias.setAlertaPromedioDias(DatosMaestra.getAlertaPromedioDias().isEmpty() ? "0" : DatosMaestra.getAlertaPromedioDias());

//        //TERMINALES
//        instancias.getFactura().actualizarResolucion();
        instancias.setUtilidad(getCeldaBooleanaPorEtiqueta(tblConfiguraciones, "Facturar Utilidad Sobrepasada", 1));

        instancias.setUbicacion(getCeldaBooleanaPorEtiqueta(tblOpcionesImpresiones, "Imprimir ubicación en factura", 1));
        instancias.setHora(getCeldaBooleanaPorEtiqueta(tblOpcionesImpresiones, "Imprimir hora en factura", 1));

        if (descuentoPorcentaje.isSelected()) {
            instancias.setDescuento("porcentaje");
        } else {
            instancias.setDescuento("peso");
        }

        String diasCobrarMora = DatosMaestra.getDiasCobrarMora().isEmpty() ? "0" : DatosMaestra.getDiasCobrarMora();
        instancias.setDiasCobrarMora(diasCobrarMora);
        if (instancias.getConfiguraciones().isCreditos()) {
            setFilaTabla(tblConfiguraciones1, "Días para cobrar mora en créditos", 1, diasCobrarMora);
        }

        String porcentajeMora = DatosMaestra.getPorcentajeMora().isEmpty() ? "0" : DatosMaestra.getPorcentajeMora();
        instancias.setPorcentajeMora(porcentajeMora);
        if (instancias.getConfiguraciones().isCreditos()) {
            setFilaTabla(tblConfiguraciones1, "Porcentaje de mora en créditos", 1, porcentajeMora);
        }

        if (DatosMaestra.isGeneraOrdenMedica()) {
            rdSiGenerarOrden.setSelected(true);
            instancias.setGeneraOrdenMedica(true);
        } else {
            rdNoGenerarOrden.setSelected(true);
            instancias.setGeneraOrdenMedica(false);
        }
        if (DatosMaestra.isImprimirOrdenMedica()) {
            rdSiImprimirOrden.setSelected(true);
            instancias.setImprimirOrdenMedica(true);
        } else {
            rdNoImprimirOrden.setSelected(true);
            instancias.setImprimirOrdenMedica(false);
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
            tabPanel.setEnabledAt(3, false);
        }

        if (DatosMaestra.isImprimirFacturaOrdenMedica()) {
            rdSiImprimirFactura.setSelected(true);
            instancias.setImprimirFacturaOrdenMedica(true);
        } else {
            rdNoImprimirFactura.setSelected(true);
            instancias.setImprimirFacturaOrdenMedica(false);
        }

        cmbHoraInicio.setSelectedItem(DatosMaestra.getHoraInicioAgenda());
        cmbHoraFin.setSelectedItem(DatosMaestra.getHoraFinAgenda());
        cmbIntervalo.setSelectedItem(DatosMaestra.getIntervaloAgenda());
        String[] configAgenda = {DatosMaestra.getHoraInicioAgenda(), DatosMaestra.getHoraFinAgenda(), DatosMaestra.getIntervaloAgenda()};
        instancias.setConfigAgenda(configAgenda);

        txtCodigoPrestadorServico.setText(DatosMaestra.getCodigoPrestadorServicio());

        try {
            cmbTipoPrestador.setSelectedItem(DatosMaestra.getTipoPrestadorServicio());
        } catch (Exception e) {
            cmbTipoPrestador.setSelectedIndex(0);
        }

        setFilaTabla(tblOpcionesImpresiones, "Imprimir cuadre fiscal en cuadre de caja", 1, DatosMaestra.isImprimirCuadreFiscal());
        if (DatosMaestra.isImprimirCuadreFiscal()) {
            instancias.setImprimirCuadreFiscal(true);
        } else {
            instancias.setImprimirCuadreFiscal(false);
        }

//        if ((boolean) datos[42]) {
//            rdSiOcultarInformacionCliente.setSelected(true);
//            instancias.setOcultarInformacionCliente(true);
//        } else {
//            rdNoOcultarInformacionCliente.setSelected(true);
//            instancias.setOcultarInformacionCliente(false);
//        }
//        if ((boolean) datos[43]) {
//            instancias.setVisualizarTodasLasFacturas(true);
//            rdSiVisualizarTodasLasFacturas.setSelected(true);
//        } else {
        instancias.setVisualizarTodasLasFacturas(false);
//            rdNoVisualizarTodasLasFacturas.setSelected(true);
//        }
//        if ((boolean) datos[44]) {
//            instancias.setMostrarInformacionCuadre(true);
//            rdSiMostrarInformacionCuadre.setSelected(true);
//        } else {
//            instancias.setMostrarInformacionCuadre(false);
//            rdNoMostrarInformacionCuadre.setSelected(true);
//        }
        // pnlCredito oculto: contenido migrado a tblConfiguraciones1

        if (!instancias.getConfiguraciones().isAgenda()) {
            pnlAgenda.setVisible(false);
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

        try {
            String descMax = DatosMaestra.getDescuentoMaximoVentas();
            if (descMax.equals("") || descMax.equals("0")) {
                instancias.setDescuentoMaximoVentas(big.getBigDecimal("100"));
            } else {
                instancias.setDescuentoMaximoVentas(big.getBigDecimal(descMax));
                setFilaTabla(tblConfiguraciones1, "Descuento máximo para aplicar", 1, descMax);
            }
        } catch (Exception e) {
            instancias.setDescuentoMaximoVentas(big.getBigDecimal("100"));
        }

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

        if (instancias.isVentasPredeterminado()) {
            instancias.getFactura().cargar1010();
            instancias.getCotiza().cargar1010();
        }

        try {
            instancias.getFactura().setCantDias(Integer.parseInt(txtDiasAlerta.getText()) * -1);
            instancias.getNd().setCantDias(Integer.parseInt(txtDiasAlerta.getText()) * -1);
        } catch (Exception e) {
        }

        metodosGenerales.actualizarVariables();
        //VARIABLES TERMINALES

        instancias.getFactura().actualizarConsecutivo(0);
//        instancias.getMesas().actualizarConsecutivo();
//        instancias.getCaja().cargarTabla();
        instancias.getAbonos().cargarTabla();
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
        modelo.addRow(new Object[]{"ZZ /No aplica", Boolean.FALSE});
        modelo.addRow(new Object[]{"O-13 /Gran contribuyente", Boolean.FALSE});
        modelo.addRow(new Object[]{"O-15 /Autorretenedor", Boolean.FALSE});
        modelo.addRow(new Object[]{"O-23 /Agente de retención en el impuesto sobre las ventas", Boolean.FALSE});
        modelo.addRow(new Object[]{"O-47 /Régimen Simple de Tributación – SIMPLE", Boolean.FALSE});

        tblInformacionEmpresa = new javax.swing.JTable(modelo) {
            @Override
            public javax.swing.table.TableCellRenderer getCellRenderer(int row, int column) {
                if (column == 1 && row >= 5) {
                    return getDefaultRenderer(Boolean.class);
                }
                return super.getCellRenderer(row, column);
            }

            @Override
            public javax.swing.table.TableCellEditor getCellEditor(int row, int column) {
                if (column == 1 && row >= 5) {
                    return getDefaultEditor(Boolean.class);
                }
                return super.getCellEditor(row, column);
            }
        };
        tblInformacionEmpresa.setFont(new java.awt.Font("Arial", 0, 16));
        tblInformacionEmpresa.setRowHeight(24);
        jScrollPane19.setViewportView(tblInformacionEmpresa);
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
        tblInformacionImpresion.setRowHeight(70);
        if (tblInformacionImpresion.getColumnModel().getColumnCount() > 0) {
            tblInformacionImpresion.getColumnModel().getColumn(0).setMinWidth(180);
            tblInformacionImpresion.getColumnModel().getColumn(0).setPreferredWidth(180);
            tblInformacionImpresion.getColumnModel().getColumn(0).setMaxWidth(180);
        }
        jScrollPane20.setViewportView(tblInformacionImpresion);
    }

    private void inicializarTablaConfiguracionesFacturacion() {
        inicializarTablaConfiguracionesFacturacion(instancias.getConfiguraciones().isRestaurante());
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
        modelo.addRow(new Object[]{"Consecutivo adicional en factura", false});
        modelo.addRow(new Object[]{"Ventas Contado Predeterminado", false});
        modelo.addRow(new Object[]{"Modificar nombre de productos", false});
        modelo.addRow(new Object[]{"Modificar precio de productos", false});
        modelo.addRow(new Object[]{"Combinar productos iguales", false});
        modelo.addRow(new Object[]{"Días de plazo automaticos", false});
        modelo.addRow(new Object[]{"Facturar Utilidad Sobrepasada", false});
        modelo.addRow(new Object[]{"Alertar Utilidad Sobrepasada", false});
        modelo.addRow(new Object[]{"Facturar sin inventario", false});
        modelo.addRow(new Object[]{"Mostrar devuelta en factura", false});
        modelo.addRow(new Object[]{"Validar el cupo del credito", false});
        modelo.addRow(new Object[]{"Activar turnos", false});
        if (esRestaurante) {
            modelo.addRow(new Object[]{"Borrar productos en mesas", false});
            modelo.addRow(new Object[]{"Borrar mesas", false});
        }
        modelo.addRow(new Object[]{"Módulo de solicitudes de permisos", false});
        modelo.addRow(new Object[]{"Usar lector", false});
        modelo.addRow(new Object[]{"Precios con impuestos", false});

        final int facturarRow = 6, alertarRow = 7;
        modelo.addTableModelListener(e -> {
            if (e.getColumn() == 1 && e.getFirstRow() == facturarRow) {
                boolean facturar = Boolean.TRUE.equals(modelo.getValueAt(facturarRow, 1));
                modelo.setValueAt(!facturar, alertarRow, 1);
            }
        });

        tblConfiguraciones = new javax.swing.JTable(modelo);
        tblConfiguraciones.setFont(new java.awt.Font("Arial", 0, 16));
        tblConfiguraciones.setRowHeight(24);
        if (tblConfiguraciones.getColumnModel().getColumnCount() > 0) {
            tblConfiguraciones.getColumnModel().getColumn(0).setMinWidth(230);
            tblConfiguraciones.getColumnModel().getColumn(0).setPreferredWidth(260);
            tblConfiguraciones.getColumnModel().getColumn(0).setMaxWidth(320);
        }
        jScrollPane21.setViewportView(tblConfiguraciones);
    }

    private void inicializarTablaConfiguraciones1(boolean esRestaurante, boolean esCreditos) {
        final java.util.List<String> etiquetas = new java.util.ArrayList<>();
        final java.util.List<String> tipos = new java.util.ArrayList<>();

        etiquetas.add("Número de turno");                       tipos.add("num");
        etiquetas.add("Título en la factura");                  tipos.add("text");
        etiquetas.add("Límite de productos (carta/media carta)"); tipos.add("num");
        etiquetas.add("Tipo de descuento");                     tipos.add("combo_descuento");
        etiquetas.add("Descuento máximo para aplicar");         tipos.add("int");
        etiquetas.add("Foco después de cargar el producto");    tipos.add("combo_foco");
        etiquetas.add("Cantidad al cargar un producto");        tipos.add("num");
        etiquetas.add("Días para alerta de cartera");           tipos.add("num");
        if (esRestaurante) {
            etiquetas.add("Porcentaje de propina en pre-factura"); tipos.add("num");
        }
        if (esCreditos) {
            etiquetas.add("Días para cobrar mora en créditos"); tipos.add("num");
            etiquetas.add("Porcentaje de mora en créditos");    tipos.add("num");
        }

        final int TIPO_DESC_ROW = 3;
        final int DESC_MAX_ROW  = 4;

        javax.swing.table.DefaultTableModel modelo = new javax.swing.table.DefaultTableModel(
                new String[]{"Configuración", "Valor"}, 0) {
            @Override
            public boolean isCellEditable(int row, int col) {
                if (col == 0) return false;
                if (row == DESC_MAX_ROW) {
                    Object tipo = getValueAt(TIPO_DESC_ROW, 1);
                    return !"Valor".equals(tipo);
                }
                return true;
            }
        };

        for (String et : etiquetas) {
            modelo.addRow(new Object[]{et, ""});
        }

        modelo.addTableModelListener(e -> {
            if (e.getColumn() == 1 && e.getFirstRow() == TIPO_DESC_ROW) {
                modelo.fireTableRowsUpdated(DESC_MAX_ROW, DESC_MAX_ROW);
            }
        });

        tblConfiguraciones1 = new javax.swing.JTable(modelo) {
            @Override
            public javax.swing.table.TableCellEditor getCellEditor(int row, int col) {
                if (col == 1 && row < tipos.size()) {
                    switch (tipos.get(row)) {
                        case "combo_descuento":
                            return new javax.swing.DefaultCellEditor(
                                new javax.swing.JComboBox<>(new String[]{"Porcentaje", "Valor"}));
                        case "combo_foco":
                            return new javax.swing.DefaultCellEditor(
                                new javax.swing.JComboBox<>(new String[]{"Cant", "Valor"}));
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
                        default:
                            return super.getCellEditor(row, col);
                    }
                }
                return super.getCellEditor(row, col);
            }
        };

        tblConfiguraciones1.setFont(new java.awt.Font("Arial", 0, 16));
        tblConfiguraciones1.setRowHeight(28);
        if (tblConfiguraciones1.getColumnModel().getColumnCount() > 0) {
            tblConfiguraciones1.getColumnModel().getColumn(0).setMinWidth(260);
            tblConfiguraciones1.getColumnModel().getColumn(0).setPreferredWidth(300);
            tblConfiguraciones1.getColumnModel().getColumn(0).setMaxWidth(400);
        }
        jScrollPane22.setViewportView(tblConfiguraciones1);
    }

    private void ajustarAlturaFilas(javax.swing.JTable tabla) {
        java.awt.Container viewport = tabla.getParent();
        int tableWidth = (viewport != null && viewport.getWidth() > 0)
                ? viewport.getWidth() : tabla.getWidth();
        int labelWidth = tabla.getColumnModel().getColumn(0).getPreferredWidth();
        if (labelWidth <= 0) {
            labelWidth = 180;
        }
        int valueWidth = tableWidth - labelWidth;
        if (valueWidth < 100) {
            valueWidth = 500;
        }

        javax.swing.JTextArea measure = new javax.swing.JTextArea();
        measure.setLineWrap(true);
        measure.setWrapStyleWord(true);
        measure.setFont(new java.awt.Font("Century Gothic", 0, 14));
        measure.setSize(valueWidth, 1);

        for (int row = 0; row < tabla.getRowCount(); row++) {
            Object val = tabla.getValueAt(row, 1);
            measure.setText(val != null ? val.toString() : "");
            int height = Math.max(measure.getPreferredSize().height, 24);
            tabla.setRowHeight(row, height + 6);
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

    private int getFilaTabla(javax.swing.JTable tabla, String etiqueta) {
        for (int i = 0; i < tabla.getRowCount(); i++) {
            Object val = tabla.getValueAt(i, 0);
            if (etiqueta.equals(val)) {
                return i;
            }
        }
        return -1;
    }

    private void setFilaTabla(javax.swing.JTable tabla, String etiqueta, int columna, Object valor) {
        int fila = getFilaTabla(tabla, etiqueta);
        if (fila >= 0) {
            tabla.setValueAt(valor, fila, columna);
        }
    }

    private boolean getCeldaBooleana(javax.swing.JTable tabla, int fila, int columna) {
        try {
            Object val = tabla.getValueAt(fila, columna);
            return val != null && (Boolean) val;
        } catch (Exception e) {
            return false;
        }
    }

    private String getCeldaTexto(javax.swing.JTable tabla, int fila, int columna) {
        try {
            Object val = tabla.getValueAt(fila, columna);
            return val != null ? val.toString() : "";
        } catch (Exception e) {
            return "";
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizarMesas;
    private javax.swing.JButton btnAgregarBodega;
    private javax.swing.JButton btnBusProd;
    private javax.swing.JButton btnCambiarEstadoBodega;
    private javax.swing.JButton btnGenerarTabla;
    private javax.swing.JButton btnGuardar2;
    private javax.swing.JButton btnGuardar3;
    private javax.swing.JButton btnGuardar4;
    private javax.swing.JButton btnGuardar5;
    private javax.swing.JButton btnGuardar6;
    private javax.swing.JButton btnGuardarMaestra;
    private javax.swing.JButton btnModificarBodega;
    private javax.swing.JButton btnOcultarImpresiones1;
    private javax.swing.JButton btnResolucion;
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
    private javax.swing.JComboBox cmbFoco;
    private javax.swing.JComboBox cmbForma;
    private javax.swing.JComboBox cmbHoraFin;
    private javax.swing.JComboBox cmbHoraFin1;
    private javax.swing.JComboBox cmbHoraInicio;
    private javax.swing.JComboBox cmbHoraInicio1;
    private javax.swing.JComboBox cmbIntervalo;
    private javax.swing.JComboBox cmbIntervalo1;
    private javax.swing.JComboBox cmbPersonal;
    private javax.swing.JComboBox cmbTipoPrestador;
    private javax.swing.JRadioButton descuentoPeso;
    private javax.swing.JRadioButton descuentoPorcentaje;
    private com.toedter.calendar.JCalendar dtFecha;
    private javax.swing.ButtonGroup grupoBorrarMesas;
    private javax.swing.ButtonGroup grupoBorrarProductos;
    private javax.swing.ButtonGroup grupoCobrarCasco;
    private javax.swing.ButtonGroup grupoCombinarProductos;
    private javax.swing.ButtonGroup grupoConsecutivoAdicional;
    private javax.swing.ButtonGroup grupoCopiaSeguridad;
    private javax.swing.ButtonGroup grupoCopiasCotizacion;
    private javax.swing.ButtonGroup grupoCopiasFactura;
    private javax.swing.ButtonGroup grupoCopiasOServicio;
    private javax.swing.ButtonGroup grupoCopiasPedido;
    private javax.swing.ButtonGroup grupoCopiasPlanSepare;
    private javax.swing.ButtonGroup grupoCostoConIva;
    private javax.swing.ButtonGroup grupoCostoImpoconsumo;
    private javax.swing.ButtonGroup grupoCuadreFiscal;
    private javax.swing.ButtonGroup grupoCupoCredito;
    private javax.swing.ButtonGroup grupoDevuelta;
    private javax.swing.ButtonGroup grupoDiasPlazo;
    private javax.swing.ButtonGroup grupoFacturacionSeparado;
    private javax.swing.ButtonGroup grupoFacturarDiasPlazo;
    private javax.swing.ButtonGroup grupoFacturarMesas;
    private javax.swing.ButtonGroup grupoFacturarUtilidadSobrepasada;
    private javax.swing.ButtonGroup grupoGeneraOrdenes;
    private javax.swing.ButtonGroup grupoGenerarEgreso;
    private javax.swing.ButtonGroup grupoImpresionResolucion;
    private javax.swing.ButtonGroup grupoImprimirRecogida;
    private javax.swing.ButtonGroup grupoIngresoSinCupo;
    private javax.swing.ButtonGroup grupoLector;
    private javax.swing.ButtonGroup grupoMensajeUtilidad;
    private javax.swing.ButtonGroup grupoModificarNombreProductos;
    private javax.swing.ButtonGroup grupoModificarPrecioProductos;
    private javax.swing.ButtonGroup grupoModificarValorFinal;
    private javax.swing.ButtonGroup grupoMostrarHora;
    private javax.swing.ButtonGroup grupoMostrarImpoconsumo;
    private javax.swing.ButtonGroup grupoMostrarInformacionCuadre;
    private javax.swing.ButtonGroup grupoMostrarRetenciones;
    private javax.swing.ButtonGroup grupoMostrarUbicacion;
    private javax.swing.ButtonGroup grupoOcultarInformacionCliente;
    private javax.swing.ButtonGroup grupoPVPImpoconsumo;
    private javax.swing.ButtonGroup grupoPagoTercerosFactura;
    private javax.swing.ButtonGroup grupoPermitirPonderados;
    private javax.swing.ButtonGroup grupoPreguntarImpuestoBolsa;
    private javax.swing.ButtonGroup grupoPrevisualizarComanda;
    private javax.swing.ButtonGroup grupoPrevisualizarCotizacion;
    private javax.swing.ButtonGroup grupoPrevisualizarFactura;
    private javax.swing.ButtonGroup grupoPrevisualizarOServicio;
    private javax.swing.ButtonGroup grupoPrevisualizarPedidos;
    private javax.swing.ButtonGroup grupoPrevisualizarPlanSepare;
    private javax.swing.ButtonGroup grupoPrevisualizarPrefactura;
    private javax.swing.ButtonGroup grupoSolicitudesPermisos;
    private javax.swing.ButtonGroup grupoSoloVisibleMesas;
    private javax.swing.ButtonGroup grupoTipoDescuento;
    private javax.swing.ButtonGroup grupoTipoImpresion;
    private javax.swing.ButtonGroup grupoTurno;
    private javax.swing.ButtonGroup grupoValorConIva;
    private javax.swing.ButtonGroup grupoVentasContado;
    private javax.swing.ButtonGroup grupoVisualizarFacturas;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JPanel jPanel44;
    private javax.swing.JPanel jPanel47;
    private javax.swing.JPanel jPanel48;
    private javax.swing.JPanel jPanel49;
    private javax.swing.JPanel jPanel50;
    private javax.swing.JPanel jPanel51;
    private javax.swing.JPanel jPanel52;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JPopupMenu jPopupMenu3;
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
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JLabel lbCelular10;
    private javax.swing.JLabel lbCelular11;
    private javax.swing.JLabel lbCelular13;
    private javax.swing.JLabel lbCelular14;
    private javax.swing.JLabel lbCelular15;
    private javax.swing.JLabel lbCelular16;
    private javax.swing.JLabel lbCelular4;
    private javax.swing.JLabel lbCelular5;
    private javax.swing.JLabel lbCelular6;
    private javax.swing.JLabel lbCelular8;
    private javax.swing.JLabel lbCelular9;
    private javax.swing.JLabel lbNit11;
    private javax.swing.JLabel lbNit12;
    private javax.swing.JLabel lbNit13;
    private javax.swing.JLabel lbNit14;
    private javax.swing.JLabel lbNit3;
    private javax.swing.JLabel lbNit4;
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
    private javax.swing.JLabel lbTelefono10;
    private javax.swing.JLabel lbTelefono13;
    private javax.swing.JLabel lbTelefono14;
    private javax.swing.JLabel lbTelefono18;
    private javax.swing.JLabel lbTelefono19;
    private javax.swing.JLabel lbTelefono20;
    private javax.swing.JLabel lbTelefono21;
    private javax.swing.JLabel lbTelefono24;
    private javax.swing.JLabel lbTelefono27;
    private javax.swing.JLabel lbTelefono29;
    private javax.swing.JLabel lbTelefono35;
    private javax.swing.JLabel lbTelefono38;
    private javax.swing.JLabel lbTelefono43;
    private javax.swing.JLabel lbTelefono48;
    private javax.swing.JLabel lbTelefono5;
    private javax.swing.JLabel lbTelefono59;
    private javax.swing.JLabel lbTelefono6;
    private javax.swing.JLabel lbTelefono65;
    private javax.swing.JLabel lbTelefono67;
    private javax.swing.JLabel lbTelefono69;
    private javax.swing.JLabel lbTelefono73;
    private javax.swing.JLabel lbTelefono9;
    private javax.swing.JRadioButton lectorNo;
    private javax.swing.JRadioButton lectorSi;
    private javax.swing.JPanel pnlAgenda;
    private javax.swing.JPanel pnlAgenda1;
    private javax.swing.JPanel pnlBodegas;
    private javax.swing.JPanel pnlCredito;
    private javax.swing.JPanel pnlDatosEmpresa;
    private javax.swing.JPanel pnlFormulario;
    private javax.swing.JPanel pnlFormulario1;
    private javax.swing.JPanel pnlImpresiones;
    private javax.swing.JPanel pnlImpresionesGrupo;
    private javax.swing.JPanel pnlInformacionMedico1;
    private javax.swing.JPanel pnlInformacionMedico2;
    private javax.swing.JPanel pnlOrdenMedica;
    private javax.swing.JPanel pnlVariablesYConf;
    private javax.swing.JMenuItem popBorrar;
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
    private javax.swing.JRadioButton rdImpresionPorGrupo;
    private javax.swing.JRadioButton rdNoBorrarMesas;
    private javax.swing.JRadioButton rdNoBorrarProdMesas;
    private javax.swing.JRadioButton rdNoFacturarMesas;
    private javax.swing.JRadioButton rdNoGenerarOrden;
    private javax.swing.JRadioButton rdNoImprimirFactura;
    private javax.swing.JRadioButton rdNoImprimirOrden;
    private javax.swing.JRadioButton rdNoPvpIva;
    private javax.swing.JRadioButton rdNoSolicitudesPermisos;
    private javax.swing.JRadioButton rdNoSoloVisibleMesas;
    private javax.swing.JRadioButton rdProcedimiento;
    private javax.swing.JRadioButton rdRedondearCantidades;
    private javax.swing.JRadioButton rdSiBorrarMesas;
    private javax.swing.JRadioButton rdSiBorrarProdMesas;
    private javax.swing.JRadioButton rdSiFacturarMesas;
    private javax.swing.JRadioButton rdSiGenerarOrden;
    private javax.swing.JRadioButton rdSiImprimirFactura;
    private javax.swing.JRadioButton rdSiImprimirOrden;
    private javax.swing.JRadioButton rdSiPvpIva;
    private javax.swing.JRadioButton rdSiSolicitudesPermisos;
    private javax.swing.JRadioButton rdSiSoloVisibleMesas;
    private javax.swing.JRadioButton rdTurnoNo;
    private javax.swing.JRadioButton rdTurnoSi;
    private javax.swing.JScrollPane scrFormulario;
    private javax.swing.JTabbedPane tabPanel;
    private javax.swing.JTable tblBodegasInternas;
    private javax.swing.JTable tblCascos;
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
    private javax.swing.JTable tblRestaurante;
    private javax.swing.JTable tblTarjetas;
    private javax.swing.JTextField txtCantidadEstablecida;
    private javax.swing.JTextField txtCodProducto;
    private javax.swing.JTextField txtCodigoPrestadorServico;
    private javax.swing.JTextField txtColumnas;
    private javax.swing.JTextField txtColumnas1;
    private javax.swing.JTextField txtComision;
    private javax.swing.JPasswordField txtContra;
    private javax.swing.JPasswordField txtContra2;
    private javax.swing.JTextField txtCosto;
    private javax.swing.JTextField txtDescuentoMaximo;
    private javax.swing.JTextField txtDiasAlerta;
    private javax.swing.JTextField txtDiasCobrarMora;
    private javax.swing.JTextField txtFilas;
    private javax.swing.JTextField txtFilas1;
    private javax.swing.JTextField txtFranquisia;
    private javax.swing.JTextField txtIdProd;
    private javax.swing.JTextField txtLimite;
    private javax.swing.JTextField txtNombreFormato;
    private javax.swing.JLabel txtNumMesas;
    private javax.swing.JTextField txtNumeroFactosElectronicas;
    private javax.swing.JTextField txtPorcPropina;
    private javax.swing.JTextField txtPorcentajeMora;
    private javax.swing.JTextField txtTituloFactura;
    private javax.swing.JTextField txtTurno;
    private javax.swing.JTextField txtValor1;
    private javax.swing.JTextField txtaCobrar;
    private javax.swing.JTextField txtaCobrar1;
    // End of variables declaration//GEN-END:variables
}
