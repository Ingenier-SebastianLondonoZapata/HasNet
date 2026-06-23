package Vista.Configuraciones;

import clases.Instancias;
import clases.Medico.ndCamposOrdenPredeterminada;
import clases.big;
import clases.cambiarColorTabla;
import clases.metodosGenerales;
import clases.ndMaestra;
import clases.productos.ndProducto;
import configuracion.dlgFinSoporte;
import formularios.dlgPedirSuperContrasena;
import formularios.productos.buscProductos;
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
import javax.swing.table.DefaultTableModel;

public class vistaMaestra extends javax.swing.JInternalFrame {

    metodosGenerales metodos = new metodosGenerales();
    private Instancias instancias = Instancias.getInstancias();
    private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension dimBarra = null;
    int teclasPresionadas = 0;
    String turno, valor, url, simbolo = "";

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public vistaMaestra() {

        initComponents();
        tblRestaurante.setDefaultRenderer(Object.class, new cambiarColorTabla(22, 0));
        tblImpresoras1.setDefaultRenderer(Object.class, new cambiarColorTabla(14, 0));
        tblDiasBloqueo.setDefaultRenderer(Object.class, new cambiarColorTabla(20, 0));

        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
        dimBarra = Barra.getPreferredSize();
        Barra.setSize(0, 0);
        Barra.setPreferredSize(new Dimension(0, 0));
        setBorder(null);
        repaint();

        jPanel12.setVisible(false);

        if (instancias.getConfiguraciones().isLaboratorio()) {
            lbRazon11.setVisible(false);
            txtaCobrar.setVisible(false);
            txtaCobrar1.setVisible(false);
            txtValor.setVisible(false);
            lbRazon19.setVisible(false);
        }

        if (!instancias.getConfiguraciones().isProductosDetallados()) {
            lbAnexo.setVisible(false);
            lbAnexo1.setVisible(false);
            txtAnexoFactura.setVisible(false);
            jScrollPane5.setVisible(false);
            jScrollPane9.setVisible(false);
            txtAnexoOrden.setVisible(false);
        }

        if (!instancias.getConfiguraciones().isOrdenServicio()) {
            lbAnexo.setVisible(false);
            lbAnexo1.setVisible(false);
            txtAnexoFactura.setVisible(false);
            jScrollPane5.setVisible(false);
            jScrollPane9.setVisible(false);
            txtAnexoOrden.setVisible(false);
        }

        this.simbolo = instancias.getSimbolo();

        PrintService[] impresoras = PrintServiceLookup.lookupPrintServices(null, null);
        DefaultTableModel model = (DefaultTableModel) tblImpresoras.getModel();

        cmbTipoCarta.removeAllItems();
        cmbTipoMediaCarta.removeAllItems();
        cmbTipoPos.removeAllItems();
        cmbComanda.removeAllItems();
        cmbPrefactura.removeAllItems();

        cmbTipoCarta.addItem("Predeterminada");
        cmbTipoMediaCarta.addItem("Predeterminada");
        cmbTipoPos.addItem("Predeterminada");
        cmbComanda.addItem("Predeterminada");
        cmbPrefactura.addItem("Predeterminada");

        if (impresoras.length > 0) {
            for (int i = 0; i < impresoras.length; i++) {
                if (!impresoras[i].toString().split(" : ")[1].equals("Fax")) {
                    cmbTipoCarta.addItem(impresoras[i].toString().split(" : ")[1]);
                    cmbTipoMediaCarta.addItem(impresoras[i].toString().split(" : ")[1]);
                    cmbTipoPos.addItem(impresoras[i].toString().split(" : ")[1]);
                    cmbComanda.addItem(impresoras[i].toString().split(" : ")[1]);
                    cmbPrefactura.addItem(impresoras[i].toString().split(" : ")[1]);
                }
            }
        }

        if (instancias.getConfiguraciones().isRestaurante()) {

            jPanel13.setVisible(true);
//            lbTelefono37.setVisible(false);
//            rdSiConsecutivo.setVisible(false);
//            rdNoConsecutivo.setVisible(false);
        } else {
            lbComanda.setVisible(false);
            lbPrefactura.setVisible(false);
            cmbComanda.setVisible(false);
            cmbPrefactura.setVisible(false);
            pnlRestaurante.setVisible(false);
            jPanel13.setVisible(false);
//            lbTelefono37.setVisible(true);
//            rdSiConsecutivo.setVisible(true);
//            rdNoConsecutivo.setVisible(true);
        }

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
        jPanel6 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        txtRegimen = new javax.swing.JTextField();
        lbTelefono1 = new javax.swing.JLabel();
        lbRazon1 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtNit = new javax.swing.JTextField();
        lbTelefono2 = new javax.swing.JLabel();
        lbRazon2 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        lbNit6 = new javax.swing.JLabel();
        lbNit1 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        cmbMisResponsabilidades = new javax.swing.JComboBox();
        lbMisResponsabilidades = new javax.swing.JLabel();
        lbResponsabilidades = new javax.swing.JLabel();
        cmbResponsabilidades = new javax.swing.JComboBox();
        btnAggResponsabilidades = new javax.swing.JButton();
        btnAggResponsabilidades1 = new javax.swing.JButton();
        lbNit2 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        lbTelefono3 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        lbNit7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtPiePagina = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtLegal = new javax.swing.JTextArea();
        lbNit8 = new javax.swing.JLabel();
        lbAnexo1 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtAnexoFactura = new javax.swing.JTextArea();
        lbAnexo = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        txtAnexoOrden = new javax.swing.JTextArea();
        lbOtroConsecutivo = new javax.swing.JTextField();
        txtNumeroFactosElectronicas = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lbNit3 = new javax.swing.JLabel();
        lbTelefono9 = new javax.swing.JLabel();
        lbTelefono6 = new javax.swing.JLabel();
        lbTelefono10 = new javax.swing.JLabel();
        lbTelefono75 = new javax.swing.JLabel();
        lbTelefono24 = new javax.swing.JLabel();
        lbTelefono13 = new javax.swing.JLabel();
        lbTelefono74 = new javax.swing.JLabel();
        lbTelefono14 = new javax.swing.JLabel();
        lbTelefono15 = new javax.swing.JLabel();
        lbTelefono25 = new javax.swing.JLabel();
        lbTelefono26 = new javax.swing.JLabel();
        txtDiasAlerta = new javax.swing.JTextField();
        txtContra = new javax.swing.JPasswordField();
        txtContra2 = new javax.swing.JPasswordField();
        txtCantidadEstablecida = new javax.swing.JTextField();
        txtNumFactIncremento = new javax.swing.JTextField();
        rdSiSolicitudesPermisos = new javax.swing.JRadioButton();
        rdNoSolicitudesPermisos = new javax.swing.JRadioButton();
        rdCopiasSi = new javax.swing.JRadioButton();
        rdCopiasNo = new javax.swing.JRadioButton();
        lectorSi = new javax.swing.JRadioButton();
        lectorNo = new javax.swing.JRadioButton();
        rdSiPvpIva = new javax.swing.JRadioButton();
        rdNoPvpIva = new javax.swing.JRadioButton();
        rdSiCostoIva = new javax.swing.JRadioButton();
        rdNoCostoIva = new javax.swing.JRadioButton();
        rdSiPvpImpo = new javax.swing.JRadioButton();
        rdNoPvpImpo = new javax.swing.JRadioButton();
        rdSiCostoImpo = new javax.swing.JRadioButton();
        rdNoCostoImpo = new javax.swing.JRadioButton();
        jPanel13 = new javax.swing.JPanel();
        lbTelefono35 = new javax.swing.JLabel();
        rdTurnoSi = new javax.swing.JRadioButton();
        rdTurnoNo = new javax.swing.JRadioButton();
        txtTurno = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jPanel37 = new javax.swing.JPanel();
        jPanel38 = new javax.swing.JPanel();
        lbTelefono32 = new javax.swing.JLabel();
        lbTelefono40 = new javax.swing.JLabel();
        lbTelefono31 = new javax.swing.JLabel();
        lbTelefono29 = new javax.swing.JLabel();
        lbTelefono59 = new javax.swing.JLabel();
        lbNit4 = new javax.swing.JLabel();
        lbTelefono4 = new javax.swing.JLabel();
        lbTelefono17 = new javax.swing.JLabel();
        lbTelefono16 = new javax.swing.JLabel();
        lbTelefono37 = new javax.swing.JLabel();
        lbTelefono27 = new javax.swing.JLabel();
        lbTelefono47 = new javax.swing.JLabel();
        lbTelefono46 = new javax.swing.JLabel();
        lbTelefono5 = new javax.swing.JLabel();
        lbTelefono33 = new javax.swing.JLabel();
        lbTelefono48 = new javax.swing.JLabel();
        lbTelefono63 = new javax.swing.JLabel();
        lbTelefono64 = new javax.swing.JLabel();
        lbTelefono65 = new javax.swing.JLabel();
        lbTelefono66 = new javax.swing.JLabel();
        jPanel39 = new javax.swing.JPanel();
        rdSiModificarNombre = new javax.swing.JRadioButton();
        rdNoModificarNombre = new javax.swing.JRadioButton();
        rdSiModificarPrecio = new javax.swing.JRadioButton();
        rdNoModificarPrecio = new javax.swing.JRadioButton();
        rdSiCombinar = new javax.swing.JRadioButton();
        rdNoCombinar = new javax.swing.JRadioButton();
        txtLimite = new javax.swing.JTextField();
        txtTituloFactura = new javax.swing.JTextField();
        utilidadSi = new javax.swing.JRadioButton();
        utilidadNo = new javax.swing.JRadioButton();
        rdSiMensajeUtilidad = new javax.swing.JRadioButton();
        rdNoMensajeUtilidad = new javax.swing.JRadioButton();
        rdSiVentasPredeterminado = new javax.swing.JRadioButton();
        rdNoVentasPredeterminado = new javax.swing.JRadioButton();
        rdSiConsecutivo = new javax.swing.JRadioButton();
        rdNoConsecutivo = new javax.swing.JRadioButton();
        txtDescuentoMaximo = new javax.swing.JTextField();
        rdSiFacturarSinInventario = new javax.swing.JRadioButton();
        rdNoFacturarSinInventario = new javax.swing.JRadioButton();
        rdSiPagoTerceros = new javax.swing.JRadioButton();
        rdNoPagoTerceros = new javax.swing.JRadioButton();
        descuentoPorcentaje = new javax.swing.JRadioButton();
        descuentoPeso = new javax.swing.JRadioButton();
        rdSiBolsa = new javax.swing.JRadioButton();
        rdNoBolsa = new javax.swing.JRadioButton();
        txtValor = new javax.swing.JTextField();
        txtPorcPropina = new javax.swing.JTextField();
        rdSiDevuelta = new javax.swing.JRadioButton();
        rdNoDevuelta = new javax.swing.JRadioButton();
        rdSiDiasAutomaticos = new javax.swing.JRadioButton();
        rdNoDiasAutomaticos = new javax.swing.JRadioButton();
        cmbFoco = new javax.swing.JComboBox();
        rdSiCupoCredito = new javax.swing.JRadioButton();
        rdNoCupoCredito = new javax.swing.JRadioButton();
        jPanel40 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        lbTelefono39 = new javax.swing.JLabel();
        jPanel41 = new javax.swing.JPanel();
        rdSiPondNegativo = new javax.swing.JRadioButton();
        rdNoPondNegativo = new javax.swing.JRadioButton();
        jPanel42 = new javax.swing.JPanel();
        jPanel43 = new javax.swing.JPanel();
        lbTelefono43 = new javax.swing.JLabel();
        lbNit = new javax.swing.JLabel();
        lbTelefono69 = new javax.swing.JLabel();
        jPanel44 = new javax.swing.JPanel();
        rdSiBorrarProdMesas = new javax.swing.JRadioButton();
        rdNoBorrarProdMesas = new javax.swing.JRadioButton();
        txtL1 = new javax.swing.JTextField();
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
        jPanel8 = new javax.swing.JPanel();
        pnlImpresionesGrupo = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblImpresoras1 = new javax.swing.JTable();
        btnGuardar4 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblImpresoras = new javax.swing.JTable();
        btnGuardar3 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        rdImpresionPorGrupo = new javax.swing.JRadioButton();
        pnlImpresiones = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        lbTelefono11 = new javax.swing.JLabel();
        lbTelefono30 = new javax.swing.JLabel();
        lbTelefono36 = new javax.swing.JLabel();
        lbTelefono50 = new javax.swing.JLabel();
        lbTelefono52 = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        txtCantFactura = new javax.swing.JTextField();
        txtCantOServicio = new javax.swing.JTextField();
        txtCantCotizacion = new javax.swing.JTextField();
        txtCantPlanSepare = new javax.swing.JTextField();
        txtCantPedidos = new javax.swing.JTextField();
        jPanel22 = new javax.swing.JPanel();
        rdPreguntaFacturaNO = new javax.swing.JRadioButton();
        rdPreguntaOServicioNO = new javax.swing.JRadioButton();
        rdPreguntaCotizacionNO = new javax.swing.JRadioButton();
        rdPreguntaPlanSepareNO = new javax.swing.JRadioButton();
        rdPreguntaPedidosNO = new javax.swing.JRadioButton();
        rdPreguntaFacturaSI = new javax.swing.JRadioButton();
        rdPreguntaOServicioSI = new javax.swing.JRadioButton();
        rdPreguntaCotizacionSI = new javax.swing.JRadioButton();
        rdPreguntaPlanSepareSI = new javax.swing.JRadioButton();
        rdPreguntaPedidosSI = new javax.swing.JRadioButton();
        jPanel23 = new javax.swing.JPanel();
        lbTelefono28 = new javax.swing.JLabel();
        lbTelefono44 = new javax.swing.JLabel();
        lbTelefono45 = new javax.swing.JLabel();
        lbTelefono49 = new javax.swing.JLabel();
        lbTelefono51 = new javax.swing.JLabel();
        jPanel30 = new javax.swing.JPanel();
        jPanel31 = new javax.swing.JPanel();
        lbTelefono41 = new javax.swing.JLabel();
        lbTelefono42 = new javax.swing.JLabel();
        lbTelefono8 = new javax.swing.JLabel();
        lbTelefono22 = new javax.swing.JLabel();
        lbTelefono23 = new javax.swing.JLabel();
        lbTelefono58 = new javax.swing.JLabel();
        lbTelefono60 = new javax.swing.JLabel();
        lbTelefono61 = new javax.swing.JLabel();
        lbTelefono62 = new javax.swing.JLabel();
        jPanel32 = new javax.swing.JPanel();
        ubicacionSi = new javax.swing.JRadioButton();
        ubicacionNo = new javax.swing.JRadioButton();
        horaSi = new javax.swing.JRadioButton();
        horaNo = new javax.swing.JRadioButton();
        recogidaSi = new javax.swing.JRadioButton();
        recogidaNo = new javax.swing.JRadioButton();
        rdSiImprimirCuadreFiscal = new javax.swing.JRadioButton();
        rdNoImprimirCuadreFiscal = new javax.swing.JRadioButton();
        mostrarRetencionesSi = new javax.swing.JRadioButton();
        mostrarRetencionesNo = new javax.swing.JRadioButton();
        mostrarImpoconsumoSi = new javax.swing.JRadioButton();
        mostrarImpoconsumoNo = new javax.swing.JRadioButton();
        jPanel19 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        rdPrevisualizarFacturaNO = new javax.swing.JRadioButton();
        rdPrevisualizarOServicioNO = new javax.swing.JRadioButton();
        rdPrevisualizarCotizacionNO = new javax.swing.JRadioButton();
        rdPrevisualizarPlanSepareNO = new javax.swing.JRadioButton();
        rdPrevisualizarPedidosNO = new javax.swing.JRadioButton();
        rdPrevisualizarFacturaSI = new javax.swing.JRadioButton();
        rdPrevisualizarOServicioSI = new javax.swing.JRadioButton();
        rdPrevisualizarCotizacionSI = new javax.swing.JRadioButton();
        rdPrevisualizarPlanSepareSI = new javax.swing.JRadioButton();
        rdPrevisualizarPedidosSI = new javax.swing.JRadioButton();
        jPanel25 = new javax.swing.JPanel();
        lbTelefono53 = new javax.swing.JLabel();
        lbTelefono54 = new javax.swing.JLabel();
        lbTelefono55 = new javax.swing.JLabel();
        lbTelefono56 = new javax.swing.JLabel();
        lbTelefono57 = new javax.swing.JLabel();
        jPanel33 = new javax.swing.JPanel();
        cmbTipoCarta = new javax.swing.JComboBox();
        cmbTipoMediaCarta = new javax.swing.JComboBox();
        cmbTipoPos = new javax.swing.JComboBox();
        carta = new javax.swing.JRadioButton();
        mediaCarta = new javax.swing.JRadioButton();
        pos = new javax.swing.JRadioButton();
        cmbComanda = new javax.swing.JComboBox();
        cmbPrefactura = new javax.swing.JComboBox();
        lbComanda = new javax.swing.JLabel();
        lbPrefactura = new javax.swing.JLabel();
        pnlRestaurante = new javax.swing.JPanel();
        lbTelefono68 = new javax.swing.JLabel();
        lbTelefono70 = new javax.swing.JLabel();
        rdSiPrevisualizarComanda = new javax.swing.JRadioButton();
        rdNoPrevisualizarComanda = new javax.swing.JRadioButton();
        lbTelefono71 = new javax.swing.JLabel();
        txtCantCopiasComanda = new javax.swing.JTextField();
        rdSiPrevisualizarPrefactura = new javax.swing.JRadioButton();
        rdNoPrevisualizarPrefactura = new javax.swing.JRadioButton();
        lbTelefono72 = new javax.swing.JLabel();
        txtCantCopiasPrefactura = new javax.swing.JTextField();
        btnOcultarImpresiones = new javax.swing.JButton();
        btnOcultarImpresiones1 = new javax.swing.JButton();
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
        lbTelefono7 = new javax.swing.JLabel();
        txtDiasAlerta1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtIdProd = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        btnGuardar2 = new javax.swing.JButton();
        btnGuardar1 = new javax.swing.JButton();
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

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Adobe Arabic", 1, 12), new java.awt.Color(102, 153, 0))); // NOI18N

        txtRegimen.setName("Teléfono"); // NOI18N
        txtRegimen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtRegimenKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRegimenKeyTyped(evt);
            }
        });

        lbTelefono1.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTelefono1.setText("REGIMEN:");

        lbRazon1.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbRazon1.setText("NOMBRE:");

        txtNombre.setName("Razón social"); // NOI18N
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreKeyReleased(evt);
            }
        });

        txtNit.setName("CC/NIT"); // NOI18N
        txtNit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNitKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNitKeyTyped(evt);
            }
        });

        lbTelefono2.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTelefono2.setText("TELEFONO/PBX");

        lbRazon2.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbRazon2.setText("DIRECCIÓN:");

        txtDireccion.setName("Razón social"); // NOI18N
        txtDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDireccionKeyReleased(evt);
            }
        });

        txtTelefono.setName("Teléfono"); // NOI18N
        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyTyped(evt);
            }
        });

        lbNit6.setBackground(new java.awt.Color(204, 204, 204));
        lbNit6.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbNit6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit6.setText("Datos de la empresa");
        lbNit6.setOpaque(true);

        lbNit1.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbNit1.setText("NIT:");

        lbMisResponsabilidades.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbMisResponsabilidades.setText("MIS RESPONSABILIDADES FISCALES:");

        lbResponsabilidades.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbResponsabilidades.setText("RESPONSABILIDADES FISCALES:");

        cmbResponsabilidades.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione una responsabilidad fiscal...", "ZZ /No aplica", "O-13 /Gran contribuyente", "O-15 /Autorretenedor", "O-23 /Agente de retención en el impuesto sobre las ventas", "O-47 /Régimen Simple de Tributación – SIMPLE" }));

        btnAggResponsabilidades.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        btnAggResponsabilidades.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/boton-de-suma.png"))); // NOI18N
        btnAggResponsabilidades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAggResponsabilidadesActionPerformed(evt);
            }
        });

        btnAggResponsabilidades1.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        btnAggResponsabilidades1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/boton-resta.png"))); // NOI18N
        btnAggResponsabilidades1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAggResponsabilidades1ActionPerformed(evt);
            }
        });

        lbNit2.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbNit2.setText("E-MAIL OBLIGATORIO GMAIL:");

        txtEmail.setName("Razón social"); // NOI18N
        txtEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEmailKeyReleased(evt);
            }
        });

        lbTelefono3.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTelefono3.setText("CONTRASEÑA:");

        txtPassword.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbNit6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator3)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lbResponsabilidades, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbResponsabilidades, 0, 657, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAggResponsabilidades, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lbNit1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbTelefono1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbRazon1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbRazon2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtRegimen, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(txtNombre)
                                .addGap(18, 18, 18)
                                .addComponent(lbTelefono2, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtNit)
                            .addComponent(txtDireccion)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbMisResponsabilidades, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbNit2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(txtEmail)
                                .addGap(18, 18, 18)
                                .addComponent(lbTelefono3, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(cmbMisResponsabilidades, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAggResponsabilidades1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(lbNit6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbRazon1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNit, javax.swing.GroupLayout.PREFERRED_SIZE, 25, Short.MAX_VALUE))
                .addGap(2, 2, 2)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbTelefono2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbNit1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNombre)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtRegimen)
                    .addComponent(lbTelefono1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtDireccion)
                    .addComponent(lbRazon2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbTelefono3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbNit2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtEmail)
                    .addComponent(txtPassword))
                .addGap(2, 2, 2)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmbMisResponsabilidades)
                    .addComponent(lbMisResponsabilidades, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAggResponsabilidades1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmbResponsabilidades)
                    .addComponent(lbResponsabilidades, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAggResponsabilidades, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lbNit7.setBackground(new java.awt.Color(204, 204, 204));
        lbNit7.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbNit7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit7.setText("Pie de pagina");
        lbNit7.setOpaque(true);

        txtPiePagina.setColumns(20);
        txtPiePagina.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtPiePagina.setLineWrap(true);
        txtPiePagina.setRows(5);
        jScrollPane2.setViewportView(txtPiePagina);

        txtLegal.setColumns(20);
        txtLegal.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtLegal.setLineWrap(true);
        txtLegal.setRows(3);
        txtLegal.setText("\n");
        jScrollPane1.setViewportView(txtLegal);

        lbNit8.setBackground(new java.awt.Color(204, 204, 204));
        lbNit8.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbNit8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit8.setText("Legal");
        lbNit8.setOpaque(true);

        lbAnexo1.setBackground(new java.awt.Color(204, 204, 204));
        lbAnexo1.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbAnexo1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbAnexo1.setText("Anexo Factura");
        lbAnexo1.setOpaque(true);

        txtAnexoFactura.setColumns(20);
        txtAnexoFactura.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtAnexoFactura.setLineWrap(true);
        txtAnexoFactura.setRows(3);
        jScrollPane5.setViewportView(txtAnexoFactura);

        lbAnexo.setBackground(new java.awt.Color(204, 204, 204));
        lbAnexo.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbAnexo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbAnexo.setText("Anexo Orden Servicio");
        lbAnexo.setOpaque(true);

        txtAnexoOrden.setColumns(20);
        txtAnexoOrden.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtAnexoOrden.setLineWrap(true);
        txtAnexoOrden.setRows(3);
        jScrollPane9.setViewportView(txtAnexoOrden);

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

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbAnexo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbNit7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5))
                        .addGap(7, 7, 7)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbAnexo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbNit8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane9)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(lbOtroConsecutivo, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNumeroFactosElectronicas, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbOtroConsecutivo, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addComponent(txtNumeroFactosElectronicas, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNit7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNit8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE))
                .addGap(20, 20, 20)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbAnexo1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbAnexo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                    .addComponent(jScrollPane5))
                .addGap(24, 24, 24))
        );

        tabPanel.addTab("Datos Empresa", jPanel6);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

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

        lbTelefono75.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTelefono75.setText("% incremento fact automatica:");

        lbTelefono24.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTelefono24.setText("Solicitudes de permisos");

        lbTelefono13.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTelefono13.setText("Lector:");

        lbTelefono74.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTelefono74.setText("Pregunta copia de seguridad");

        lbTelefono14.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTelefono14.setText("P.V.P Con Iva:");

        lbTelefono15.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTelefono15.setText("Costo Con Iva:");

        lbTelefono25.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTelefono25.setText("P.V.P Con Impoconsumo");

        lbTelefono26.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTelefono26.setText("Costo con Impoconsumo");

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

        txtNumFactIncremento.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtNumFactIncremento.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNumFactIncremento.setText("0");
        txtNumFactIncremento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNumFactIncrementoKeyTyped(evt);
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

        rdCopiasSi.setBackground(new java.awt.Color(255, 255, 255));
        grupoCopiaSeguridad.add(rdCopiasSi);
        rdCopiasSi.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rdCopiasSi.setText("Si");

        rdCopiasNo.setBackground(new java.awt.Color(255, 255, 255));
        grupoCopiaSeguridad.add(rdCopiasNo);
        rdCopiasNo.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rdCopiasNo.setText("No");
        rdCopiasNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdCopiasNoActionPerformed(evt);
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

        rdSiCostoIva.setBackground(new java.awt.Color(255, 255, 255));
        grupoCostoConIva.add(rdSiCostoIva);
        rdSiCostoIva.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rdSiCostoIva.setText("Si");
        rdSiCostoIva.setEnabled(false);

        rdNoCostoIva.setBackground(new java.awt.Color(255, 255, 255));
        grupoCostoConIva.add(rdNoCostoIva);
        rdNoCostoIva.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rdNoCostoIva.setText("No");
        rdNoCostoIva.setEnabled(false);

        rdSiPvpImpo.setBackground(new java.awt.Color(255, 255, 255));
        grupoPVPImpoconsumo.add(rdSiPvpImpo);
        rdSiPvpImpo.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rdSiPvpImpo.setText("Si");
        rdSiPvpImpo.setEnabled(false);

        rdNoPvpImpo.setBackground(new java.awt.Color(255, 255, 255));
        grupoPVPImpoconsumo.add(rdNoPvpImpo);
        rdNoPvpImpo.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rdNoPvpImpo.setText("No");
        rdNoPvpImpo.setEnabled(false);

        rdSiCostoImpo.setBackground(new java.awt.Color(255, 255, 255));
        grupoCostoImpoconsumo.add(rdSiCostoImpo);
        rdSiCostoImpo.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rdSiCostoImpo.setText("Si");
        rdSiCostoImpo.setEnabled(false);

        rdNoCostoImpo.setBackground(new java.awt.Color(255, 255, 255));
        grupoCostoImpoconsumo.add(rdNoCostoImpo);
        rdNoCostoImpo.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rdNoCostoImpo.setText("No");
        rdNoCostoImpo.setEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbTelefono26, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbTelefono15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbTelefono14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbTelefono13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbTelefono75, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbTelefono24, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lbTelefono6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbTelefono9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
                            .addComponent(lbNit3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbTelefono10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(lbTelefono74, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbTelefono25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtNumFactIncremento)
                            .addComponent(txtDiasAlerta, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtContra, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtContra2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCantidadEstablecida, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(56, 56, 56))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(rdSiCostoImpo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                            .addComponent(rdSiPvpImpo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(rdSiCostoIva, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(rdSiPvpIva, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lectorSi, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(rdCopiasSi, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(rdSiSolicitudesPermisos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdNoSolicitudesPermisos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(rdCopiasNo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lectorNo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(rdNoPvpIva, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(rdNoCostoIva, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(rdNoPvpImpo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(rdNoCostoImpo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                        .addGap(3, 3, 3)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbTelefono75, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNumFactIncremento, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbTelefono24, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdSiSolicitudesPermisos, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdNoSolicitudesPermisos, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbTelefono74, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdCopiasSi, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdCopiasNo, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbTelefono13, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lectorSi, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lectorNo, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbTelefono14, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdSiPvpIva, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdNoPvpIva, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lbTelefono15, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(rdNoCostoIva, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(rdSiCostoIva, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(txtCantidadEstablecida, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbTelefono25, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rdSiPvpImpo, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rdNoPvpImpo, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTelefono26, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdSiCostoImpo, javax.swing.GroupLayout.PREFERRED_SIZE, 22, Short.MAX_VALUE)
                    .addComponent(rdNoCostoImpo, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
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

        lbTelefono32.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTelefono32.setText("Modificar nombre de productos:");
        lbTelefono32.setToolTipText("Visualizar toda la información de facturacion, \nreportes y abonos de todas las terminales.");

        lbTelefono40.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTelefono40.setText("Modificar precio de productos:");
        lbTelefono40.setToolTipText("Visualizar toda la información de facturacion, \nreportes y abonos de todas las terminales.");

        lbTelefono31.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTelefono31.setText("Combinar productos iguales:");
        lbTelefono31.setToolTipText("Visualizar toda la información de facturacion, \nreportes y abonos de todas las terminales.");

        lbTelefono29.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTelefono29.setText("Limite de productos por factura");
        lbTelefono29.setToolTipText("Visualizar toda la información de facturacion, \nreportes y abonos de todas las terminales.");

        lbTelefono59.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTelefono59.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTelefono59.setText("carta y media carta");
        lbTelefono59.setToolTipText("Visualizar toda la información de facturacion, \nreportes y abonos de todas las terminales.");

        lbNit4.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbNit4.setText("Titulo en Factura:");

        lbTelefono4.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTelefono4.setText("Facturar Utilidad Sobrepasada:");

        lbTelefono17.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTelefono17.setText("Alertar Utilidad Sobrepasada:");

        lbTelefono16.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTelefono16.setText("Ventas Contado Predeterminado:");

        lbTelefono37.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTelefono37.setText("Consecutivo adicional en factura:");
        lbTelefono37.setToolTipText("Visualizar toda la información de facturacion, \nreportes y abonos de todas las terminales.");

        lbTelefono27.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTelefono27.setText("Descuento maximo en factura:");
        lbTelefono27.setToolTipText("Visualizar toda la información de facturacion, \nreportes y abonos de todas las terminales.");

        lbTelefono47.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTelefono47.setText("Facturar sin inventario:");
        lbTelefono47.setToolTipText("Visualizar toda la información de facturacion, \nreportes y abonos de todas las terminales.");

        lbTelefono46.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTelefono46.setText("Pago a terceros en factura:");
        lbTelefono46.setToolTipText("Visualizar toda la información de facturacion, \nreportes y abonos de todas las terminales.");

        lbTelefono5.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTelefono5.setText("Tipo de descuento:");

        lbTelefono33.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTelefono33.setText("Preguntar por imp. bolsa");
        lbTelefono33.setToolTipText("Visualizar toda la información de facturacion, \nreportes y abonos de todas las terminales.");

        lbTelefono48.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTelefono48.setText("% Propina en la pre-factura:");

        lbTelefono63.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTelefono63.setText("Mostrar devuelta en factura:");
        lbTelefono63.setToolTipText("Visualizar toda la información de facturacion, \nreportes y abonos de todas las terminales.");

        lbTelefono64.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTelefono64.setText("Días de plazo automaticos:");
        lbTelefono64.setToolTipText("Visualizar toda la información de facturacion, \nreportes y abonos de todas las terminales.");

        lbTelefono65.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTelefono65.setText("Foco despues de cargar el producto:");
        lbTelefono65.setToolTipText("Visualizar toda la información de facturacion, \nreportes y abonos de todas las terminales.");

        lbTelefono66.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTelefono66.setText("Validar el cupo del credito:");
        lbTelefono66.setToolTipText("Visualizar toda la información de facturacion, \nreportes y abonos de todas las terminales.");

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
                        .addComponent(lbTelefono16, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel38Layout.createSequentialGroup()
                        .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lbTelefono32, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
                                .addComponent(lbTelefono40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbTelefono31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lbTelefono59, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbTelefono29, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel38Layout.createSequentialGroup()
                        .addComponent(lbNit4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(145, 145, 145))
                    .addComponent(lbTelefono4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbTelefono17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbTelefono37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbTelefono47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbTelefono46, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbTelefono5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbTelefono33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbTelefono48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbTelefono63, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbTelefono64, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbTelefono65, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbTelefono66, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbTelefono37, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(lbTelefono16, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(lbTelefono32)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbTelefono40)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbTelefono31)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbTelefono64)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbTelefono4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(lbTelefono17, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(lbTelefono47, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(lbTelefono46, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(lbTelefono5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(lbTelefono33, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbTelefono63, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(lbTelefono65, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(lbTelefono66)
                .addContainerGap())
        );

        jPanel39.setBackground(new java.awt.Color(255, 255, 255));

        rdSiModificarNombre.setBackground(new java.awt.Color(255, 255, 255));
        grupoModificarNombreProductos.add(rdSiModificarNombre);
        rdSiModificarNombre.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rdSiModificarNombre.setText("Si");
        rdSiModificarNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdSiModificarNombreActionPerformed(evt);
            }
        });

        rdNoModificarNombre.setBackground(new java.awt.Color(255, 255, 255));
        grupoModificarNombreProductos.add(rdNoModificarNombre);
        rdNoModificarNombre.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rdNoModificarNombre.setText("No");
        rdNoModificarNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdNoModificarNombreActionPerformed(evt);
            }
        });

        rdSiModificarPrecio.setBackground(new java.awt.Color(255, 255, 255));
        grupoModificarPrecioProductos.add(rdSiModificarPrecio);
        rdSiModificarPrecio.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rdSiModificarPrecio.setText("Si");
        rdSiModificarPrecio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdSiModificarPrecioActionPerformed(evt);
            }
        });

        rdNoModificarPrecio.setBackground(new java.awt.Color(255, 255, 255));
        grupoModificarPrecioProductos.add(rdNoModificarPrecio);
        rdNoModificarPrecio.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rdNoModificarPrecio.setText("No");
        rdNoModificarPrecio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdNoModificarPrecioActionPerformed(evt);
            }
        });

        rdSiCombinar.setBackground(new java.awt.Color(255, 255, 255));
        grupoCombinarProductos.add(rdSiCombinar);
        rdSiCombinar.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rdSiCombinar.setText("Si");
        rdSiCombinar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdSiCombinarActionPerformed(evt);
            }
        });

        rdNoCombinar.setBackground(new java.awt.Color(255, 255, 255));
        grupoCombinarProductos.add(rdNoCombinar);
        rdNoCombinar.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rdNoCombinar.setText("No");
        rdNoCombinar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdNoCombinarActionPerformed(evt);
            }
        });

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

        utilidadSi.setBackground(new java.awt.Color(255, 255, 255));
        grupoFacturarUtilidadSobrepasada.add(utilidadSi);
        utilidadSi.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        utilidadSi.setText("Si");
        utilidadSi.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                utilidadSiItemStateChanged(evt);
            }
        });

        utilidadNo.setBackground(new java.awt.Color(255, 255, 255));
        grupoFacturarUtilidadSobrepasada.add(utilidadNo);
        utilidadNo.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        utilidadNo.setText("No");
        utilidadNo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                utilidadNoItemStateChanged(evt);
            }
        });
        utilidadNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                utilidadNoActionPerformed(evt);
            }
        });

        rdSiMensajeUtilidad.setBackground(new java.awt.Color(255, 255, 255));
        grupoMensajeUtilidad.add(rdSiMensajeUtilidad);
        rdSiMensajeUtilidad.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rdSiMensajeUtilidad.setSelected(true);
        rdSiMensajeUtilidad.setText("Si");

        rdNoMensajeUtilidad.setBackground(new java.awt.Color(255, 255, 255));
        grupoMensajeUtilidad.add(rdNoMensajeUtilidad);
        rdNoMensajeUtilidad.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rdNoMensajeUtilidad.setText("No");
        rdNoMensajeUtilidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdNoMensajeUtilidadActionPerformed(evt);
            }
        });

        rdSiVentasPredeterminado.setBackground(new java.awt.Color(255, 255, 255));
        grupoVentasContado.add(rdSiVentasPredeterminado);
        rdSiVentasPredeterminado.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rdSiVentasPredeterminado.setText("Si");

        rdNoVentasPredeterminado.setBackground(new java.awt.Color(255, 255, 255));
        grupoVentasContado.add(rdNoVentasPredeterminado);
        rdNoVentasPredeterminado.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rdNoVentasPredeterminado.setText("No");
        rdNoVentasPredeterminado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdNoVentasPredeterminadoActionPerformed(evt);
            }
        });

        rdSiConsecutivo.setBackground(new java.awt.Color(255, 255, 255));
        grupoConsecutivoAdicional.add(rdSiConsecutivo);
        rdSiConsecutivo.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rdSiConsecutivo.setText("Si");
        rdSiConsecutivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdSiConsecutivoActionPerformed(evt);
            }
        });

        rdNoConsecutivo.setBackground(new java.awt.Color(255, 255, 255));
        grupoConsecutivoAdicional.add(rdNoConsecutivo);
        rdNoConsecutivo.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rdNoConsecutivo.setText("No");
        rdNoConsecutivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdNoConsecutivoActionPerformed(evt);
            }
        });

        txtDescuentoMaximo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtDescuentoMaximo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDescuentoMaximo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDescuentoMaximoKeyTyped(evt);
            }
        });

        rdSiFacturarSinInventario.setBackground(new java.awt.Color(255, 255, 255));
        grupoFacturacionSeparado.add(rdSiFacturarSinInventario);
        rdSiFacturarSinInventario.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rdSiFacturarSinInventario.setText("Si");
        rdSiFacturarSinInventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdSiFacturarSinInventarioActionPerformed(evt);
            }
        });

        rdNoFacturarSinInventario.setBackground(new java.awt.Color(255, 255, 255));
        grupoFacturacionSeparado.add(rdNoFacturarSinInventario);
        rdNoFacturarSinInventario.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rdNoFacturarSinInventario.setText("No");
        rdNoFacturarSinInventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdNoFacturarSinInventarioActionPerformed(evt);
            }
        });

        rdSiPagoTerceros.setBackground(new java.awt.Color(255, 255, 255));
        grupoPagoTercerosFactura.add(rdSiPagoTerceros);
        rdSiPagoTerceros.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rdSiPagoTerceros.setText("Si");
        rdSiPagoTerceros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdSiPagoTercerosActionPerformed(evt);
            }
        });

        rdNoPagoTerceros.setBackground(new java.awt.Color(255, 255, 255));
        grupoPagoTercerosFactura.add(rdNoPagoTerceros);
        rdNoPagoTerceros.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rdNoPagoTerceros.setText("No");
        rdNoPagoTerceros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdNoPagoTercerosActionPerformed(evt);
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

        rdSiBolsa.setBackground(new java.awt.Color(255, 255, 255));
        grupoPreguntarImpuestoBolsa.add(rdSiBolsa);
        rdSiBolsa.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rdSiBolsa.setText("Si");
        rdSiBolsa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdSiBolsaActionPerformed(evt);
            }
        });

        rdNoBolsa.setBackground(new java.awt.Color(255, 255, 255));
        grupoPreguntarImpuestoBolsa.add(rdNoBolsa);
        rdNoBolsa.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rdNoBolsa.setText("No");
        rdNoBolsa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdNoBolsaActionPerformed(evt);
            }
        });

        txtValor.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtValor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtValorActionPerformed(evt);
            }
        });
        txtValor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtValorKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtValorKeyTyped(evt);
            }
        });

        txtPorcPropina.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtPorcPropina.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPorcPropina.setText("0");
        txtPorcPropina.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPorcPropinaKeyTyped(evt);
            }
        });

        rdSiDevuelta.setBackground(new java.awt.Color(255, 255, 255));
        grupoDevuelta.add(rdSiDevuelta);
        rdSiDevuelta.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rdSiDevuelta.setText("Si");
        rdSiDevuelta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdSiDevueltaActionPerformed(evt);
            }
        });

        rdNoDevuelta.setBackground(new java.awt.Color(255, 255, 255));
        grupoDevuelta.add(rdNoDevuelta);
        rdNoDevuelta.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rdNoDevuelta.setText("No");
        rdNoDevuelta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdNoDevueltaActionPerformed(evt);
            }
        });

        rdSiDiasAutomaticos.setBackground(new java.awt.Color(255, 255, 255));
        grupoDiasPlazo.add(rdSiDiasAutomaticos);
        rdSiDiasAutomaticos.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rdSiDiasAutomaticos.setText("Si");
        rdSiDiasAutomaticos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdSiDiasAutomaticosActionPerformed(evt);
            }
        });

        rdNoDiasAutomaticos.setBackground(new java.awt.Color(255, 255, 255));
        grupoDiasPlazo.add(rdNoDiasAutomaticos);
        rdNoDiasAutomaticos.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rdNoDiasAutomaticos.setText("No");
        rdNoDiasAutomaticos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdNoDiasAutomaticosActionPerformed(evt);
            }
        });

        cmbFoco.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        cmbFoco.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Cant", "Valor" }));

        rdSiCupoCredito.setBackground(new java.awt.Color(255, 255, 255));
        grupoCupoCredito.add(rdSiCupoCredito);
        rdSiCupoCredito.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rdSiCupoCredito.setText("Si");
        rdSiCupoCredito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdSiCupoCreditoActionPerformed(evt);
            }
        });

        rdNoCupoCredito.setBackground(new java.awt.Color(255, 255, 255));
        grupoCupoCredito.add(rdNoCupoCredito);
        rdNoCupoCredito.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rdNoCupoCredito.setText("No");
        rdNoCupoCredito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdNoCupoCreditoActionPerformed(evt);
            }
        });

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
                            .addGroup(jPanel39Layout.createSequentialGroup()
                                .addComponent(rdSiBolsa)
                                .addGap(2, 2, 2)
                                .addComponent(rdNoBolsa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel39Layout.createSequentialGroup()
                                .addComponent(rdSiPagoTerceros)
                                .addGap(2, 2, 2)
                                .addComponent(rdNoPagoTerceros, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel39Layout.createSequentialGroup()
                                .addComponent(rdSiFacturarSinInventario)
                                .addGap(2, 2, 2)
                                .addComponent(rdNoFacturarSinInventario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel39Layout.createSequentialGroup()
                                .addComponent(rdSiConsecutivo)
                                .addGap(2, 2, 2)
                                .addComponent(rdNoConsecutivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel39Layout.createSequentialGroup()
                                .addComponent(rdSiVentasPredeterminado)
                                .addGap(2, 2, 2)
                                .addComponent(rdNoVentasPredeterminado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel39Layout.createSequentialGroup()
                                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(utilidadSi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(rdSiMensajeUtilidad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(utilidadNo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(rdNoMensajeUtilidad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel39Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(rdSiModificarNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(rdSiModificarPrecio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(rdSiCombinar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(rdSiDiasAutomaticos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(rdNoModificarPrecio, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(rdNoModificarNombre, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(rdNoCombinar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(rdNoDiasAutomaticos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(txtLimite)
                            .addComponent(txtDescuentoMaximo)
                            .addGroup(jPanel39Layout.createSequentialGroup()
                                .addComponent(rdSiDevuelta)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rdNoDevuelta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel39Layout.createSequentialGroup()
                        .addComponent(rdSiCupoCredito)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rdNoCupoCredito)
                        .addGap(0, 0, Short.MAX_VALUE))
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
                .addGap(5, 5, 5)
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdSiConsecutivo, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdNoConsecutivo, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdSiVentasPredeterminado, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdNoVentasPredeterminado, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(rdNoModificarNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rdSiModificarNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(2, 2, 2)
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(rdSiModificarPrecio)
                    .addComponent(rdNoModificarPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rdSiCombinar)
                    .addComponent(rdNoCombinar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rdNoDiasAutomaticos)
                    .addComponent(rdSiDiasAutomaticos))
                .addGap(5, 5, 5)
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(utilidadSi, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(utilidadNo, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdSiMensajeUtilidad, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdNoMensajeUtilidad, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdSiFacturarSinInventario, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdNoFacturarSinInventario, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdSiPagoTerceros, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdNoPagoTerceros, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(descuentoPorcentaje, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descuentoPeso, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rdSiBolsa, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rdNoBolsa, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdSiDevuelta, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdNoDevuelta, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmbFoco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rdNoCupoCredito)
                    .addComponent(rdSiCupoCredito))
                .addContainerGap())
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

        jPanel40.setBackground(new java.awt.Color(255, 255, 255));
        jPanel40.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "En Cuadre de Caja", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 12))); // NOI18N

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));

        lbTelefono39.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTelefono39.setText("Permitir ponderados negativos:");
        lbTelefono39.setToolTipText("Visualizar toda la información de facturacion, \nreportes y abonos de todas las terminales.");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbTelefono39, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(lbTelefono39, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3))
        );

        jPanel41.setBackground(new java.awt.Color(255, 255, 255));

        rdSiPondNegativo.setBackground(new java.awt.Color(255, 255, 255));
        grupoPermitirPonderados.add(rdSiPondNegativo);
        rdSiPondNegativo.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rdSiPondNegativo.setText("Si");
        rdSiPondNegativo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdSiPondNegativoActionPerformed(evt);
            }
        });

        rdNoPondNegativo.setBackground(new java.awt.Color(255, 255, 255));
        grupoPermitirPonderados.add(rdNoPondNegativo);
        rdNoPondNegativo.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rdNoPondNegativo.setText("No");
        rdNoPondNegativo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdNoPondNegativoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel41Layout = new javax.swing.GroupLayout(jPanel41);
        jPanel41.setLayout(jPanel41Layout);
        jPanel41Layout.setHorizontalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rdSiPondNegativo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rdNoPondNegativo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel41Layout.setVerticalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdSiPondNegativo, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdNoPondNegativo, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        javax.swing.GroupLayout jPanel40Layout = new javax.swing.GroupLayout(jPanel40);
        jPanel40.setLayout(jPanel40Layout);
        jPanel40Layout.setHorizontalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel40Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel40Layout.setVerticalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel40Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3))
        );

        jPanel42.setBackground(new java.awt.Color(255, 255, 255));
        jPanel42.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Congeladas y Mesas ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 12))); // NOI18N

        jPanel43.setBackground(new java.awt.Color(255, 255, 255));

        lbTelefono43.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTelefono43.setText("Borrar productos:");
        lbTelefono43.setToolTipText("Visualizar toda la información de facturacion, \nreportes y abonos de todas las terminales.");

        lbNit.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbNit.setText("Numero de mesas:");

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
                    .addComponent(lbTelefono43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbNit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(120, Short.MAX_VALUE))
        );
        jPanel43Layout.setVerticalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel43Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(lbNit)
                .addGap(5, 5, 5)
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

        txtL1.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtL1.setName("CC/NIT"); // NOI18N
        txtL1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtL1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtL1KeyTyped(evt);
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
                    .addComponent(txtL1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel44Layout.createSequentialGroup()
                        .addComponent(rdSiBorrarProdMesas, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rdNoBorrarProdMesas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel44Layout.createSequentialGroup()
                        .addComponent(rdSiBorrarMesas, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rdNoBorrarMesas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel44Layout.setVerticalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel44Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(txtL1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
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
                    .addComponent(jPanel43, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
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

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlCredito, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jPanel37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jPanel40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(jPanel42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlCredito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(5, 5, 5))
        );

        tabPanel.addTab("Variables y cofiguraciones", jPanel5);

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
        btnGuardar5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
                    .addComponent(lbNit11, javax.swing.GroupLayout.DEFAULT_SIZE, 1017, Short.MAX_VALUE)
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
                .addContainerGap(290, Short.MAX_VALUE))
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
                    .addComponent(pnlInformacionMedico2, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
                    .addComponent(pnlInformacionMedico1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(244, Short.MAX_VALUE))
        );

        tabPanel.addTab("Médico", jPanel7);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

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
        btnGuardar4.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnGuardar4.setText("BORRAR IMPRESORA");
        btnGuardar4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
        btnGuardar3.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnGuardar3.setText("ACTUALIZAR IMPRESORAS");
        btnGuardar3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar3.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
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
                        .addComponent(rdImpresionPorGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnGuardar3, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGuardar4))
                    .addGroup(pnlImpresionesGrupoLayout.createSequentialGroup()
                        .addGroup(pnlImpresionesGrupoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane4)))
                .addContainerGap())
        );
        pnlImpresionesGrupoLayout.setVerticalGroup(
            pnlImpresionesGrupoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlImpresionesGrupoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlImpresionesGrupoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdImpresionPorGrupo)
                    .addComponent(btnGuardar3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlImpresionesGrupoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlImpresionesGrupoLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(3, 3, 3)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        pnlImpresiones.setBackground(new java.awt.Color(255, 255, 255));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Número de copias", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 0, 12))); // NOI18N

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));

        lbTelefono11.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbTelefono11.setText("Cant:");

        lbTelefono30.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbTelefono30.setText("Cant:");

        lbTelefono36.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbTelefono36.setText("Cant:");

        lbTelefono50.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbTelefono50.setText("Cant:");

        lbTelefono52.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbTelefono52.setText("Cant:");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lbTelefono52, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbTelefono50, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbTelefono30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbTelefono11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbTelefono36, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(lbTelefono11)
                .addGap(5, 5, 5)
                .addComponent(lbTelefono30)
                .addGap(5, 5, 5)
                .addComponent(lbTelefono36)
                .addGap(4, 4, 4)
                .addComponent(lbTelefono50)
                .addGap(4, 4, 4)
                .addComponent(lbTelefono52)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));

        txtCantFactura.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtCantFactura.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtCantOServicio.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtCantOServicio.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtCantCotizacion.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtCantCotizacion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCantCotizacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantCotizacionActionPerformed(evt);
            }
        });

        txtCantPlanSepare.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtCantPlanSepare.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtCantPedidos.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtCantPedidos.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCantPlanSepare, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(txtCantPedidos, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtCantCotizacion, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtCantOServicio)
                    .addComponent(txtCantFactura, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(3, 3, 3))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(txtCantFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(txtCantOServicio)
                .addGap(3, 3, 3)
                .addComponent(txtCantCotizacion)
                .addGap(3, 3, 3)
                .addComponent(txtCantPlanSepare)
                .addGap(3, 3, 3)
                .addComponent(txtCantPedidos)
                .addGap(5, 5, 5))
        );

        jPanel22.setBackground(new java.awt.Color(255, 255, 255));

        rdPreguntaFacturaNO.setBackground(new java.awt.Color(255, 255, 255));
        grupoCopiasFactura.add(rdPreguntaFacturaNO);
        rdPreguntaFacturaNO.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rdPreguntaFacturaNO.setText("No");
        rdPreguntaFacturaNO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdPreguntaFacturaNOActionPerformed(evt);
            }
        });

        rdPreguntaOServicioNO.setBackground(new java.awt.Color(255, 255, 255));
        grupoCopiasOServicio.add(rdPreguntaOServicioNO);
        rdPreguntaOServicioNO.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rdPreguntaOServicioNO.setText("No");
        rdPreguntaOServicioNO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdPreguntaOServicioNOActionPerformed(evt);
            }
        });

        rdPreguntaCotizacionNO.setBackground(new java.awt.Color(255, 255, 255));
        grupoCopiasCotizacion.add(rdPreguntaCotizacionNO);
        rdPreguntaCotizacionNO.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rdPreguntaCotizacionNO.setText("No");
        rdPreguntaCotizacionNO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdPreguntaCotizacionNOActionPerformed(evt);
            }
        });

        rdPreguntaPlanSepareNO.setBackground(new java.awt.Color(255, 255, 255));
        grupoCopiasPlanSepare.add(rdPreguntaPlanSepareNO);
        rdPreguntaPlanSepareNO.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rdPreguntaPlanSepareNO.setText("No");
        rdPreguntaPlanSepareNO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdPreguntaPlanSepareNOActionPerformed(evt);
            }
        });

        rdPreguntaPedidosNO.setBackground(new java.awt.Color(255, 255, 255));
        grupoCopiasPedido.add(rdPreguntaPedidosNO);
        rdPreguntaPedidosNO.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rdPreguntaPedidosNO.setText("No");
        rdPreguntaPedidosNO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdPreguntaPedidosNOActionPerformed(evt);
            }
        });

        rdPreguntaFacturaSI.setBackground(new java.awt.Color(255, 255, 255));
        grupoCopiasFactura.add(rdPreguntaFacturaSI);
        rdPreguntaFacturaSI.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rdPreguntaFacturaSI.setText("Si");
        rdPreguntaFacturaSI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdPreguntaFacturaSIActionPerformed(evt);
            }
        });

        rdPreguntaOServicioSI.setBackground(new java.awt.Color(255, 255, 255));
        grupoCopiasOServicio.add(rdPreguntaOServicioSI);
        rdPreguntaOServicioSI.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rdPreguntaOServicioSI.setText("Si");
        rdPreguntaOServicioSI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdPreguntaOServicioSIActionPerformed(evt);
            }
        });

        rdPreguntaCotizacionSI.setBackground(new java.awt.Color(255, 255, 255));
        grupoCopiasCotizacion.add(rdPreguntaCotizacionSI);
        rdPreguntaCotizacionSI.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rdPreguntaCotizacionSI.setText("Si");
        rdPreguntaCotizacionSI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdPreguntaCotizacionSIActionPerformed(evt);
            }
        });

        rdPreguntaPlanSepareSI.setBackground(new java.awt.Color(255, 255, 255));
        grupoCopiasPlanSepare.add(rdPreguntaPlanSepareSI);
        rdPreguntaPlanSepareSI.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rdPreguntaPlanSepareSI.setText("Si");
        rdPreguntaPlanSepareSI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdPreguntaPlanSepareSIActionPerformed(evt);
            }
        });

        rdPreguntaPedidosSI.setBackground(new java.awt.Color(255, 255, 255));
        grupoCopiasPedido.add(rdPreguntaPedidosSI);
        rdPreguntaPedidosSI.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rdPreguntaPedidosSI.setText("Si");
        rdPreguntaPedidosSI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdPreguntaPedidosSIActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addComponent(rdPreguntaFacturaSI, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(2, 2, 2))
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addComponent(rdPreguntaOServicioSI, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(rdPreguntaPlanSepareSI, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(rdPreguntaCotizacionSI, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addComponent(rdPreguntaPedidosSI, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(rdPreguntaPedidosNO, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rdPreguntaFacturaNO, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rdPreguntaOServicioNO, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rdPreguntaCotizacionNO, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rdPreguntaPlanSepareNO, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdPreguntaFacturaNO, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdPreguntaFacturaSI, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdPreguntaOServicioNO, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdPreguntaOServicioSI, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdPreguntaCotizacionNO, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdPreguntaCotizacionSI, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdPreguntaPlanSepareNO, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdPreguntaPlanSepareSI, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdPreguntaPedidosNO, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdPreguntaPedidosSI, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel23.setBackground(new java.awt.Color(255, 255, 255));

        lbTelefono28.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTelefono28.setText("Preguntar en Factura:");

        lbTelefono44.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTelefono44.setText("Preguntar en O.Servicio:");

        lbTelefono45.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTelefono45.setText("Preguntar en Cotización:");

        lbTelefono49.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTelefono49.setText("Preguntar en Plan Separe:");

        lbTelefono51.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTelefono51.setText("Preguntar en Pedidos:");

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lbTelefono51, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbTelefono49, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbTelefono45, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbTelefono44, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbTelefono28, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(lbTelefono28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5)
                .addComponent(lbTelefono44)
                .addGap(5, 5, 5)
                .addComponent(lbTelefono45)
                .addGap(4, 4, 4)
                .addComponent(lbTelefono49)
                .addGap(4, 4, 4)
                .addComponent(lbTelefono51)
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jPanel30.setBackground(new java.awt.Color(255, 255, 255));
        jPanel30.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Opciones", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jPanel31.setBackground(new java.awt.Color(255, 255, 255));

        lbTelefono41.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTelefono41.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTelefono41.setText("Imprimir y mostrar");

        lbTelefono42.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTelefono42.setText("Imprimir hora en factura:");

        lbTelefono8.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTelefono8.setText("Imprimir Recogida:");

        lbTelefono22.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTelefono22.setText("Imprimir C. Fiscal:");

        lbTelefono23.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTelefono23.setText("Imprimir retenciones en");

        lbTelefono58.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTelefono58.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTelefono58.setText("Imprimir impoconsumo");

        lbTelefono60.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTelefono60.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTelefono60.setText("Ubicación en factura:");

        lbTelefono61.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTelefono61.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTelefono61.setText("factura");

        lbTelefono62.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTelefono62.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTelefono62.setText("en factura");

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbTelefono58, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbTelefono23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbTelefono8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbTelefono41, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbTelefono42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbTelefono22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbTelefono60, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbTelefono62, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(lbTelefono61, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel31Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(lbTelefono41, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lbTelefono60, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(lbTelefono42)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbTelefono23)
                .addGap(0, 0, 0)
                .addComponent(lbTelefono61, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(lbTelefono58)
                .addGap(0, 0, 0)
                .addComponent(lbTelefono62, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbTelefono8)
                .addGap(3, 3, 3)
                .addComponent(lbTelefono22)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel32.setBackground(new java.awt.Color(255, 255, 255));

        ubicacionSi.setBackground(new java.awt.Color(255, 255, 255));
        grupoMostrarUbicacion.add(ubicacionSi);
        ubicacionSi.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        ubicacionSi.setSelected(true);
        ubicacionSi.setText("Si");

        ubicacionNo.setBackground(new java.awt.Color(255, 255, 255));
        grupoMostrarUbicacion.add(ubicacionNo);
        ubicacionNo.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        ubicacionNo.setText("No");
        ubicacionNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ubicacionNoActionPerformed(evt);
            }
        });

        horaSi.setBackground(new java.awt.Color(255, 255, 255));
        grupoMostrarHora.add(horaSi);
        horaSi.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        horaSi.setSelected(true);
        horaSi.setText("Si");

        horaNo.setBackground(new java.awt.Color(255, 255, 255));
        grupoMostrarHora.add(horaNo);
        horaNo.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        horaNo.setText("No");
        horaNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                horaNoActionPerformed(evt);
            }
        });

        recogidaSi.setBackground(new java.awt.Color(255, 255, 255));
        grupoImprimirRecogida.add(recogidaSi);
        recogidaSi.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        recogidaSi.setSelected(true);
        recogidaSi.setText("Si");

        recogidaNo.setBackground(new java.awt.Color(255, 255, 255));
        grupoImprimirRecogida.add(recogidaNo);
        recogidaNo.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        recogidaNo.setText("No");
        recogidaNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recogidaNoActionPerformed(evt);
            }
        });

        rdSiImprimirCuadreFiscal.setBackground(new java.awt.Color(255, 255, 255));
        grupoCuadreFiscal.add(rdSiImprimirCuadreFiscal);
        rdSiImprimirCuadreFiscal.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rdSiImprimirCuadreFiscal.setSelected(true);
        rdSiImprimirCuadreFiscal.setText("Si");

        rdNoImprimirCuadreFiscal.setBackground(new java.awt.Color(255, 255, 255));
        grupoCuadreFiscal.add(rdNoImprimirCuadreFiscal);
        rdNoImprimirCuadreFiscal.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rdNoImprimirCuadreFiscal.setText("No");
        rdNoImprimirCuadreFiscal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdNoImprimirCuadreFiscalActionPerformed(evt);
            }
        });

        mostrarRetencionesSi.setBackground(new java.awt.Color(255, 255, 255));
        grupoMostrarRetenciones.add(mostrarRetencionesSi);
        mostrarRetencionesSi.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        mostrarRetencionesSi.setSelected(true);
        mostrarRetencionesSi.setText("Si");

        mostrarRetencionesNo.setBackground(new java.awt.Color(255, 255, 255));
        grupoMostrarRetenciones.add(mostrarRetencionesNo);
        mostrarRetencionesNo.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        mostrarRetencionesNo.setText("No");
        mostrarRetencionesNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mostrarRetencionesNoActionPerformed(evt);
            }
        });

        mostrarImpoconsumoSi.setBackground(new java.awt.Color(255, 255, 255));
        grupoMostrarImpoconsumo.add(mostrarImpoconsumoSi);
        mostrarImpoconsumoSi.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        mostrarImpoconsumoSi.setSelected(true);
        mostrarImpoconsumoSi.setText("Si");

        mostrarImpoconsumoNo.setBackground(new java.awt.Color(255, 255, 255));
        grupoMostrarImpoconsumo.add(mostrarImpoconsumoNo);
        mostrarImpoconsumoNo.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        mostrarImpoconsumoNo.setText("No");
        mostrarImpoconsumoNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mostrarImpoconsumoNoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel32Layout.createSequentialGroup()
                        .addComponent(ubicacionSi, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(ubicacionNo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel32Layout.createSequentialGroup()
                        .addComponent(mostrarRetencionesSi)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(mostrarRetencionesNo, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel32Layout.createSequentialGroup()
                        .addComponent(mostrarImpoconsumoSi, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(mostrarImpoconsumoNo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel32Layout.createSequentialGroup()
                        .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(horaSi)
                            .addGroup(jPanel32Layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addComponent(horaNo))
                            .addGroup(jPanel32Layout.createSequentialGroup()
                                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(recogidaSi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(rdSiImprimirCuadreFiscal))
                                .addGap(7, 7, 7)
                                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(rdNoImprimirCuadreFiscal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(recogidaNo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel32Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ubicacionSi, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(ubicacionNo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(6, 6, 6)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(horaSi)
                    .addComponent(horaNo))
                .addGap(10, 10, 10)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mostrarRetencionesNo, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mostrarRetencionesSi, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mostrarImpoconsumoSi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(mostrarImpoconsumoNo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(recogidaSi)
                    .addComponent(recogidaNo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdSiImprimirCuadreFiscal)
                    .addComponent(rdNoImprimirCuadreFiscal))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel30Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                    .addComponent(jPanel32, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));
        jPanel19.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Previsualizar", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 0, 12))); // NOI18N

        jPanel24.setBackground(new java.awt.Color(255, 255, 255));

        rdPrevisualizarFacturaNO.setBackground(new java.awt.Color(255, 255, 255));
        grupoPrevisualizarFactura.add(rdPrevisualizarFacturaNO);
        rdPrevisualizarFacturaNO.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rdPrevisualizarFacturaNO.setText("No");
        rdPrevisualizarFacturaNO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdPrevisualizarFacturaNOActionPerformed(evt);
            }
        });

        rdPrevisualizarOServicioNO.setBackground(new java.awt.Color(255, 255, 255));
        grupoPrevisualizarOServicio.add(rdPrevisualizarOServicioNO);
        rdPrevisualizarOServicioNO.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rdPrevisualizarOServicioNO.setText("No");
        rdPrevisualizarOServicioNO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdPrevisualizarOServicioNOActionPerformed(evt);
            }
        });

        rdPrevisualizarCotizacionNO.setBackground(new java.awt.Color(255, 255, 255));
        grupoPrevisualizarCotizacion.add(rdPrevisualizarCotizacionNO);
        rdPrevisualizarCotizacionNO.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rdPrevisualizarCotizacionNO.setText("No");
        rdPrevisualizarCotizacionNO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdPrevisualizarCotizacionNOActionPerformed(evt);
            }
        });

        rdPrevisualizarPlanSepareNO.setBackground(new java.awt.Color(255, 255, 255));
        grupoPrevisualizarPlanSepare.add(rdPrevisualizarPlanSepareNO);
        rdPrevisualizarPlanSepareNO.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rdPrevisualizarPlanSepareNO.setText("No");
        rdPrevisualizarPlanSepareNO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdPrevisualizarPlanSepareNOActionPerformed(evt);
            }
        });

        rdPrevisualizarPedidosNO.setBackground(new java.awt.Color(255, 255, 255));
        grupoPrevisualizarPedidos.add(rdPrevisualizarPedidosNO);
        rdPrevisualizarPedidosNO.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rdPrevisualizarPedidosNO.setText("No");
        rdPrevisualizarPedidosNO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdPrevisualizarPedidosNOActionPerformed(evt);
            }
        });

        rdPrevisualizarFacturaSI.setBackground(new java.awt.Color(255, 255, 255));
        grupoPrevisualizarFactura.add(rdPrevisualizarFacturaSI);
        rdPrevisualizarFacturaSI.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rdPrevisualizarFacturaSI.setText("Si");
        rdPrevisualizarFacturaSI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdPrevisualizarFacturaSIActionPerformed(evt);
            }
        });

        rdPrevisualizarOServicioSI.setBackground(new java.awt.Color(255, 255, 255));
        grupoPrevisualizarOServicio.add(rdPrevisualizarOServicioSI);
        rdPrevisualizarOServicioSI.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rdPrevisualizarOServicioSI.setText("Si");
        rdPrevisualizarOServicioSI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdPrevisualizarOServicioSIActionPerformed(evt);
            }
        });

        rdPrevisualizarCotizacionSI.setBackground(new java.awt.Color(255, 255, 255));
        grupoPrevisualizarCotizacion.add(rdPrevisualizarCotizacionSI);
        rdPrevisualizarCotizacionSI.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rdPrevisualizarCotizacionSI.setText("Si");
        rdPrevisualizarCotizacionSI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdPrevisualizarCotizacionSIActionPerformed(evt);
            }
        });

        rdPrevisualizarPlanSepareSI.setBackground(new java.awt.Color(255, 255, 255));
        grupoPrevisualizarPlanSepare.add(rdPrevisualizarPlanSepareSI);
        rdPrevisualizarPlanSepareSI.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rdPrevisualizarPlanSepareSI.setText("Si");
        rdPrevisualizarPlanSepareSI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdPrevisualizarPlanSepareSIActionPerformed(evt);
            }
        });

        rdPrevisualizarPedidosSI.setBackground(new java.awt.Color(255, 255, 255));
        grupoPrevisualizarPedidos.add(rdPrevisualizarPedidosSI);
        rdPrevisualizarPedidosSI.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rdPrevisualizarPedidosSI.setText("Si");
        rdPrevisualizarPedidosSI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdPrevisualizarPedidosSIActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addComponent(rdPrevisualizarFacturaSI, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(2, 2, 2))
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addComponent(rdPrevisualizarOServicioSI, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(rdPrevisualizarPlanSepareSI, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(rdPrevisualizarCotizacionSI, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addComponent(rdPrevisualizarPedidosSI, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(rdPrevisualizarPedidosNO, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rdPrevisualizarFacturaNO, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rdPrevisualizarOServicioNO, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rdPrevisualizarCotizacionNO, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rdPrevisualizarPlanSepareNO, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdPrevisualizarFacturaNO, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdPrevisualizarFacturaSI, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdPrevisualizarOServicioNO, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdPrevisualizarOServicioSI, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdPrevisualizarCotizacionNO, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdPrevisualizarCotizacionSI, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdPrevisualizarPlanSepareNO, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdPrevisualizarPlanSepareSI, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdPrevisualizarPedidosNO, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdPrevisualizarPedidosSI, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel25.setBackground(new java.awt.Color(255, 255, 255));

        lbTelefono53.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTelefono53.setText("Factura:");

        lbTelefono54.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTelefono54.setText("O.Servicio:");

        lbTelefono55.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTelefono55.setText("Cotización:");

        lbTelefono56.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTelefono56.setText("Plan Separe:");

        lbTelefono57.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTelefono57.setText("Pedidos:");

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lbTelefono57, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbTelefono56, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbTelefono55, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbTelefono54, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbTelefono53, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(lbTelefono53, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(lbTelefono54)
                .addGap(5, 5, 5)
                .addComponent(lbTelefono55)
                .addGap(4, 4, 4)
                .addComponent(lbTelefono56)
                .addGap(4, 4, 4)
                .addComponent(lbTelefono57)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel33.setBackground(new java.awt.Color(255, 255, 255));
        jPanel33.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Impresoras", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 0, 10))); // NOI18N

        cmbTipoCarta.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N

        cmbTipoMediaCarta.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        cmbTipoMediaCarta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTipoMediaCartaActionPerformed(evt);
            }
        });

        cmbTipoPos.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N

        carta.setBackground(new java.awt.Color(255, 255, 255));
        grupoTipoImpresion.add(carta);
        carta.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        carta.setText("Carta");
        carta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cartaActionPerformed(evt);
            }
        });

        mediaCarta.setBackground(new java.awt.Color(255, 255, 255));
        grupoTipoImpresion.add(mediaCarta);
        mediaCarta.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        mediaCarta.setText("M. Carta");
        mediaCarta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mediaCartaActionPerformed(evt);
            }
        });

        pos.setBackground(new java.awt.Color(255, 255, 255));
        grupoTipoImpresion.add(pos);
        pos.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        pos.setText("Pos");
        pos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                posActionPerformed(evt);
            }
        });

        cmbComanda.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N

        cmbPrefactura.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N

        lbComanda.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        lbComanda.setText("Comanda:");

        lbPrefactura.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        lbPrefactura.setText("Prefactura:");

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel33Layout.createSequentialGroup()
                        .addComponent(mediaCarta)
                        .addGap(6, 6, 6))
                    .addGroup(jPanel33Layout.createSequentialGroup()
                        .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(carta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbComanda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel33Layout.createSequentialGroup()
                                .addComponent(lbPrefactura, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbPrefactura, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbComanda, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel33Layout.createSequentialGroup()
                        .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbTipoMediaCarta, 0, 312, Short.MAX_VALUE)
                            .addComponent(cmbTipoCarta, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbTipoPos, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmbTipoCarta)
                    .addComponent(carta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(2, 2, 2)
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mediaCarta)
                    .addComponent(cmbTipoMediaCarta, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbTipoPos, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pos))
                .addGap(3, 3, 3)
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmbComanda, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(lbComanda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmbPrefactura)
                    .addComponent(lbPrefactura, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2))
        );

        pnlRestaurante.setBackground(new java.awt.Color(255, 255, 255));
        pnlRestaurante.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Impresiones restaurante", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 0, 10))); // NOI18N

        lbTelefono68.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTelefono68.setText("Previsualizar comanda:");

        lbTelefono70.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTelefono70.setText("Previsualizar prefactura:");

        rdSiPrevisualizarComanda.setBackground(new java.awt.Color(255, 255, 255));
        grupoPrevisualizarComanda.add(rdSiPrevisualizarComanda);
        rdSiPrevisualizarComanda.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rdSiPrevisualizarComanda.setSelected(true);
        rdSiPrevisualizarComanda.setText("Si");
        rdSiPrevisualizarComanda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdSiPrevisualizarComandaActionPerformed(evt);
            }
        });

        rdNoPrevisualizarComanda.setBackground(new java.awt.Color(255, 255, 255));
        grupoPrevisualizarComanda.add(rdNoPrevisualizarComanda);
        rdNoPrevisualizarComanda.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rdNoPrevisualizarComanda.setText("No");
        rdNoPrevisualizarComanda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdNoPrevisualizarComandaActionPerformed(evt);
            }
        });

        lbTelefono71.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTelefono71.setText("Número copias:");

        txtCantCopiasComanda.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtCantCopiasComanda.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCantCopiasComanda.setText("0");
        txtCantCopiasComanda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantCopiasComandaKeyTyped(evt);
            }
        });

        rdSiPrevisualizarPrefactura.setBackground(new java.awt.Color(255, 255, 255));
        grupoPrevisualizarPrefactura.add(rdSiPrevisualizarPrefactura);
        rdSiPrevisualizarPrefactura.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rdSiPrevisualizarPrefactura.setSelected(true);
        rdSiPrevisualizarPrefactura.setText("Si");
        rdSiPrevisualizarPrefactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdSiPrevisualizarPrefacturaActionPerformed(evt);
            }
        });

        rdNoPrevisualizarPrefactura.setBackground(new java.awt.Color(255, 255, 255));
        grupoPrevisualizarPrefactura.add(rdNoPrevisualizarPrefactura);
        rdNoPrevisualizarPrefactura.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rdNoPrevisualizarPrefactura.setText("No");
        rdNoPrevisualizarPrefactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdNoPrevisualizarPrefacturaActionPerformed(evt);
            }
        });

        lbTelefono72.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTelefono72.setText("Número copias:");

        txtCantCopiasPrefactura.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtCantCopiasPrefactura.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCantCopiasPrefactura.setText("0");
        txtCantCopiasPrefactura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantCopiasPrefacturaKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout pnlRestauranteLayout = new javax.swing.GroupLayout(pnlRestaurante);
        pnlRestaurante.setLayout(pnlRestauranteLayout);
        pnlRestauranteLayout.setHorizontalGroup(
            pnlRestauranteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRestauranteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlRestauranteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlRestauranteLayout.createSequentialGroup()
                        .addComponent(lbTelefono68, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdSiPrevisualizarComanda, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(rdNoPrevisualizarComanda, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
                        .addGap(27, 27, 27)
                        .addComponent(lbTelefono71, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCantCopiasComanda, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlRestauranteLayout.createSequentialGroup()
                        .addComponent(lbTelefono70, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdSiPrevisualizarPrefactura, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(rdNoPrevisualizarPrefactura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(27, 27, 27)
                        .addComponent(lbTelefono72, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCantCopiasPrefactura, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlRestauranteLayout.setVerticalGroup(
            pnlRestauranteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRestauranteLayout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addGroup(pnlRestauranteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbTelefono71, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(rdSiPrevisualizarComanda, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlRestauranteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbTelefono68)
                        .addComponent(rdNoPrevisualizarComanda, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCantCopiasComanda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(2, 2, 2)
                .addGroup(pnlRestauranteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlRestauranteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lbTelefono72, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(rdSiPrevisualizarPrefactura, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(pnlRestauranteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdNoPrevisualizarPrefactura, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCantCopiasPrefactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lbTelefono70))
                .addGap(2, 2, 2))
        );

        javax.swing.GroupLayout pnlImpresionesLayout = new javax.swing.GroupLayout(pnlImpresiones);
        pnlImpresiones.setLayout(pnlImpresionesLayout);
        pnlImpresionesLayout.setHorizontalGroup(
            pnlImpresionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlImpresionesLayout.createSequentialGroup()
                .addGroup(pnlImpresionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, 423, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlImpresionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlImpresionesLayout.createSequentialGroup()
                        .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnlRestaurante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlImpresionesLayout.setVerticalGroup(
            pnlImpresionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlImpresionesLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(pnlImpresionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlImpresionesLayout.createSequentialGroup()
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlImpresionesLayout.createSequentialGroup()
                        .addGroup(pnlImpresionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel19, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4)
                        .addComponent(pnlRestaurante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(5, 5, 5))
        );

        btnOcultarImpresiones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/boton-resta.png"))); // NOI18N
        btnOcultarImpresiones.setToolTipText("Ctrl+M");
        btnOcultarImpresiones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOcultarImpresionesActionPerformed(evt);
            }
        });

        btnOcultarImpresiones1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/boton-resta.png"))); // NOI18N
        btnOcultarImpresiones1.setToolTipText("Ctrl+M");
        btnOcultarImpresiones1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOcultarImpresiones1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlImpresionesGrupo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlImpresiones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnOcultarImpresiones)
                    .addComponent(btnOcultarImpresiones1))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlImpresiones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOcultarImpresiones))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlImpresionesGrupo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(btnOcultarImpresiones1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        tabPanel.addTab("Impresión", jPanel8);

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
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 1017, Short.MAX_VALUE)
                    .addComponent(lbTelefono38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbTelefono38, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
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
                .addComponent(jScrollPane8)
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
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
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 485, Short.MAX_VALUE)
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
                .addContainerGap(451, Short.MAX_VALUE))
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
                .addContainerGap(471, Short.MAX_VALUE))
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
        btnGuardar6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
                    .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 612, Short.MAX_VALUE)
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
        btnBusProd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
                        .addGap(0, 496, Short.MAX_VALUE)
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
                .addComponent(jScrollPane14, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
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
                    .addComponent(jPanel50, javax.swing.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
                    .addComponent(jPanel47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        tabPanel.addTab("Parqueadero", pnlFormulario1);

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        lbTelefono7.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbTelefono7.setText("Dias Alerta Resolucion: ");

        txtDiasAlerta1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtDiasAlerta1.setHorizontalAlignment(javax.swing.JTextField.CENTER);

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
                .addContainerGap()
                .addComponent(lbTelefono7)
                .addGap(18, 18, 18)
                .addComponent(txtDiasAlerta1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtIdProd, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 708, Short.MAX_VALUE)
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
                    .addComponent(lbTelefono7)
                    .addComponent(txtDiasAlerta1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        btnGuardar2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar2.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnGuardar2.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnGuardar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardar2ActionPerformed(evt);
            }
        });

        btnGuardar1.setBackground(new java.awt.Color(46, 204, 113));
        btnGuardar1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnGuardar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
        btnGuardar1.setText("GUARDAR");
        btnGuardar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnGuardar1.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnGuardar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardar1ActionPerformed(evt);
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
            .addComponent(btnGuardar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(btnGuardar1)
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
                        .addGap(8, 8, 8)
                        .addComponent(tabPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlFormularioLayout.setVerticalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tabPanel)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrFormulario)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtL1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtL1KeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtL1KeyTyped

    private void txtL1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtL1KeyReleased

    }//GEN-LAST:event_txtL1KeyReleased

    private void txtRegimenKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRegimenKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRegimenKeyReleased

    private void txtRegimenKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRegimenKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRegimenKeyTyped

    private void txtNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreKeyReleased

    private void txtNitKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNitKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNitKeyReleased

    private void txtNitKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNitKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNitKeyTyped

    private void txtDireccionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDireccionKeyReleased

    private void txtTelefonoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoKeyReleased

    private void txtTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoKeyTyped

    private void btnGuardar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar1ActionPerformed
        boolean registrar = false;

        if (metodos.msgPregunta(this, "¿Desea continuar?") == 0) {

            if (!txtContra.getText().equals(txtContra2.getText())) {
                metodos.msgAdvertencia(null, "Las contraseñas no coinciden");
                txtContra.requestFocus();
                return;
            }

            if (instancias.getConfiguraciones().isMedico()) {
                if (rdNoGenerarOrden.isSelected()) {
                    if (txtaCobrar1.getText().equals("")) {
                        metodos.msgError(null, "Falta servicio a cobrar");
                        return;
                    }
                }

//                if (rdNoGenerarOrden.isSelected()) {
//                    ndCamposOrdenPredeterminada nodo = instancias.getSql().getDatosCamposOrdenPredeterminada("1");
//                    if (nodo.getId() == null) {
//                        metodos.msgError(null, "No ha predeterminado los campos de la orden");
//                        return;
//                    }
//                }
            }

            String vendedores = "", regimen = "", utilidad = "SI", descuento = "porcentaje", ubicacion = "SI", impresion = "", cantCopias = "", tituloFactura = "Factura de venta No.";

//                if (simplificado.isSelected()) {
//                    regimen = "SinIva";
//                }
            lbTelefono2.requestFocus();
            if (utilidadNo.isSelected()) {
                utilidad = "NO";
            }

            if (descuentoPeso.isSelected()) {
                descuento = "peso";
            }

            if (ubicacionNo.isSelected()) {
                ubicacion = "NO";
            }

            try {
                cantCopias = Integer.parseInt(txtCantFactura.getText()) + "";
            } catch (Exception e) {
            }

            if (pos.isSelected()) {
                impresion = "pos";
            } else if (carta.isSelected()) {
                impresion = "facturaCompleta";
            } else {
                impresion = "factura";
            }

            if (!txtTituloFactura.getText().equals("")) {
                tituloFactura = txtTituloFactura.getText();
            }

            Object[] datos = instancias.getSql().getDatosMaestra();
            ndMaestra nodo2 = metodos.llenarMaestra(datos);

            String impresionPos = cmbTipoPos.getSelectedItem().toString().toLowerCase();

            String responsabilidades = "";
            for (int i = 0; i < cmbMisResponsabilidades.getItemCount(); i++) {
                responsabilidades = responsabilidades + cmbMisResponsabilidades.getItemAt(i) + ", ";
            }

            if (!responsabilidades.equals("")) {
                responsabilidades = responsabilidades.substring(0, responsabilidades.length() - 2);
            }

            Object[] vector = {txtL1.getText(), impresion, cantCopias, vendedores, regimen, utilidad, descuento, txtContra.getText(), txtNit.getText().toUpperCase(),
                txtNombre.getText().toUpperCase(), txtRegimen.getText().toUpperCase(), nodo2.getD4(), nodo2.getD5(), txtDireccion.getText().toUpperCase(),
                txtTelefono.getText().toUpperCase(), txtPiePagina.getText(), txtLegal.getText(), ubicacion, txtDiasAlerta.getText(), recogidaSi.isSelected(),
                lectorSi.isSelected(), tituloFactura, rdSiPvpIva.isSelected(), rdSiCostoIva.isSelected(), rdSiVentasPredeterminado.isSelected(),
                rdSiMensajeUtilidad.isSelected(), nodo2.isConsecutivosDiferentes(), nodo2.isResolucionIgual(), txtDiasAlerta1.getText(), nodo2.getAlertaFechaDias(),
                nodo2.getAlertaCantidadNumeracion(), nodo2.getAlertaPromedioDias(), txtDiasCobrarMora.getText(), txtPorcentajeMora.getText(), rdSiGenerarOrden.isSelected(),
                rdSiImprimirOrden.isSelected(), rdSiImprimirFactura.isSelected(), cmbHoraInicio.getSelectedItem(), cmbHoraFin.getSelectedItem(), cmbIntervalo.getSelectedItem(),
                txtCodigoPrestadorServico.getText(), rdSiImprimirCuadreFiscal.isSelected(), rdSiCupoCredito.isSelected(), rdCopiasSi.isSelected(),
                false, txtDescuentoMaximo.getText().replace(",", "."), cmbTipoPrestador.getSelectedItem(), "0",
                "6", txtLimite.getText(), rdSiCombinar.isSelected(), rdSiModificarNombre.isSelected(), rdSiBolsa.isSelected(), txtValor.getText(),
                rdTurnoSi.isSelected(), txtTurno.getText(), txtAnexoFactura.getText(), rdSiConsecutivo.isSelected(), horaSi.isSelected(), rdSiPondNegativo.isSelected(),
                txtAnexoOrden.getText(), rdSiModificarPrecio.isSelected(), rdSiBorrarProdMesas.isSelected(), rdPreguntaFacturaSI.isSelected(), rdPreguntaOServicioSI.isSelected(),
                rdPreguntaCotizacionSI.isSelected(), rdPreguntaPlanSepareSI.isSelected(), rdPreguntaPedidosSI.isSelected(), rdPrevisualizarFacturaSI.isSelected(),
                rdPrevisualizarOServicioSI.isSelected(), rdPrevisualizarCotizacionSI.isSelected(), rdPrevisualizarPlanSepareSI.isSelected(), rdPrevisualizarPedidosSI.isSelected(),
                txtCantFactura.getText(), txtCantOServicio.getText(), txtCantCotizacion.getText(), txtCantPlanSepare.getText(), txtCantPedidos.getText(), rdSiPagoTerceros.isSelected(),
                rdSiFacturarSinInventario.isSelected(), true, impresionPos, cmbTipoMediaCarta.getSelectedItem(), cmbTipoCarta.getSelectedItem(),
                mostrarImpoconsumoSi.isSelected(), mostrarRetencionesSi.isSelected(), txtNombreFormato.getText(), txtCantidadEstablecida.getText(),
                txtFilas.getText(), txtColumnas.getText(), txtPorcPropina.getText(), rdSiDevuelta.isSelected(), cmbHoraInicio1.getSelectedItem(),
                cmbHoraFin1.getSelectedItem(), cmbIntervalo1.getSelectedItem(), rdSiDiasAutomaticos.isSelected(), rdSiSolicitudesPermisos.isSelected(),
                cmbFoco.getSelectedItem(), rdSiPvpImpo.isSelected(), rdSiCostoImpo.isSelected(), rdImpresionPorGrupo.isSelected(),
                txtCantCopiasComanda.getText(), txtCantCopiasPrefactura.getText(), rdSiPrevisualizarComanda.isSelected(), rdSiPrevisualizarPrefactura.isSelected(),
                cmbComanda.getSelectedItem(), cmbPrefactura.getSelectedItem(), rdSiFacturarMesas.isSelected(), rdSiSoloVisibleMesas.isSelected(),
                rdSiBorrarMesas.isSelected(), "", "", txtNumFactIncremento.getText(), responsabilidades,
                txtEmail.getText(), txtPassword.getText()
            };

            ndMaestra nodo = metodos.llenarMaestra(vector);

            if (!instancias.getSql().modificarMaestra(nodo)) {
                metodos.msgError(this, "Hubo un problema al guardar los cambios");
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
                for (int i = 0; i < tblRestaurante.getRowCount(); i++) {
                    for (int j = 0; j < tblRestaurante.getColumnCount(); j++) {
                        String campo = "";
                        try {
                            campo = tblRestaurante.getValueAt(i, j).toString();
                        } catch (Exception e) {
                        }
                        if (!campo.equals("")) {
                            instancias.getSql().agregarRegistroMesa(i + "," + j, campo);
                        }
                    }
                }
            }

            if (instancias.getConfiguraciones().isMedico()) {

                String valor = "0";
                try {
                    ndProducto prod = instancias.getSql().getDatosProducto(txtaCobrar.getText(), "bdProductos");
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
                        metodos.msgError(null, "Hubo un problema al guardar la Orden Predeterminada");
                        return;
                    }
                } else {
                    if (!instancias.getSql().modificarCamposOrdenPredeterminada(nodoMed)) {
                        metodos.msgError(null, "Hubo un problema al modificar la Orden Predeterminada");
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
                        metodos.msgAdvertenciaAjustado(this, "El casco debe ser mayor a " + this.simbolo + " 0");
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
                        metodos.msgError(this, "Hubo un problema al guardar los cambios");
                        return;
                    }
                }

                String idMaestra = instancias.getSql().getIdMaestraParqueadero();

                System.out.println("vamos a ver: " + idMaestra);

                if (!idMaestra.equals("")) {
                    if (!instancias.getSql().modificarMaestraParqueadero(egreso, "", "", "", "", cupo, diasPlazo, cobrarCasco, cobro, modificarPrecio)) {
                        metodos.msgError(this, "Error al guardar la configuración");
                        return;
                    }
                } else {
                    if (!instancias.getSql().insertarMaestraParqueadero(egreso, "", "", "", "", cupo, diasPlazo, cobrarCasco, cobro, modificarPrecio)) {
                        metodos.msgError(this, "Error al guardar la configuración");
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

            metodos.msgExito(this, "Cambios guardados con éxito");

            tabPanel.setSelectedIndex(0);

            llenarDatos();

            /* ACTUALIZAMOS MAESTROS EN EL MODULO DE FACTURACION */
            instancias.getuInt().consultarMaestros();
            instancias.getPrestamos().consultarMaestros();
            instancias.getTrasladosInternos().consultarMaestros();
            instancias.getFactura().consultarMaestros();
            instancias.getCuentaCobro().consultarMaestros();
            instancias.getCotiza().consultarMaestros();
            instancias.getOrdenServicio().consultarMaestros();
            instancias.getPedido().consultarMaestros();
            instancias.getReporte().consultarMaestros();

            if (instancias.getConfiguraciones().isCongeladas()) {
                instancias.getMesas().consultarMaestros();
                instancias.getMesa().getPnlFactura().consultarMaestros();
                instancias.getMesa1().consultarMaestros();
            }

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
    }//GEN-LAST:event_btnGuardar1ActionPerformed

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

    private void utilidadNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_utilidadNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_utilidadNoActionPerformed

    private void recogidaNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recogidaNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_recogidaNoActionPerformed

    private void mediaCartaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mediaCartaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mediaCartaActionPerformed

    private void posActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_posActionPerformed

    }//GEN-LAST:event_posActionPerformed

    private void cartaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cartaActionPerformed

    }//GEN-LAST:event_cartaActionPerformed

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
            metodos.msgExito(this, "Imagen cargada con exito");
        } catch (Exception e) {
            metodos.msgError(this, "Error al guardar el logo");
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

    private void rdNoVentasPredeterminadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdNoVentasPredeterminadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdNoVentasPredeterminadoActionPerformed

    private void rdNoMensajeUtilidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdNoMensajeUtilidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdNoMensajeUtilidadActionPerformed

    private void utilidadNoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_utilidadNoItemStateChanged
        if (utilidadNo.isSelected()) {
            rdSiMensajeUtilidad.setSelected(true);
        } else {
            rdNoMensajeUtilidad.setSelected(true);
        }
    }//GEN-LAST:event_utilidadNoItemStateChanged

    private void utilidadSiItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_utilidadSiItemStateChanged
        if (utilidadNo.isSelected()) {
            rdSiMensajeUtilidad.setSelected(true);
        } else {
            rdNoMensajeUtilidad.setSelected(true);
        }
    }//GEN-LAST:event_utilidadSiItemStateChanged

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

    private void rdNoImprimirCuadreFiscalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdNoImprimirCuadreFiscalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdNoImprimirCuadreFiscalActionPerformed

    private void txtCodigoPrestadorServicoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoPrestadorServicoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoPrestadorServicoKeyTyped

    private void txtDescuentoMaximoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescuentoMaximoKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtDescuentoMaximoKeyTyped

    private void btnGuardar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar3ActionPerformed
        cmbTipoCarta.removeAllItems();
        cmbTipoMediaCarta.removeAllItems();
        cmbTipoPos.removeAllItems();

        cmbTipoCarta.addItem("Predeterminada");
        cmbTipoMediaCarta.addItem("Predeterminada");
        cmbTipoPos.addItem("Predeterminada");

        PrintService[] impresoras = PrintServiceLookup.lookupPrintServices(null, null);
        DefaultTableModel model = (DefaultTableModel) tblImpresoras.getModel();

        while (tblImpresoras.getRowCount() > 0) {
            model.removeRow(0);
        }

        System.out.println("impresorassss " + impresoras.length);
        if (impresoras.length > 0) {
            for (int i = 0; i < impresoras.length; i++) {
                if (!impresoras[i].toString().split(" : ")[1].equals("Fax")) {
                    model.addRow(new Object[]{impresoras[i].toString().split(" : ")[1]});
                }
            }

            for (int i = 0; i < impresoras.length; i++) {
                if (!impresoras[i].toString().split(" : ")[1].equals("Fax")) {
                    cmbTipoCarta.addItem(impresoras[i].toString().split(" : ")[1]);
                    cmbTipoMediaCarta.addItem(impresoras[i].toString().split(" : ")[1]);
                    cmbTipoPos.addItem(impresoras[i].toString().split(" : ")[1]);
                }
            }
        }

        Object[] datos = instancias.getSql().getDatosMaestra();
        String impresionPos, impresionMediaCarta, impresionCarta;

        try {
            impresionPos = datos[81].toString();
        } catch (Exception e) {
            impresionPos = "";
        }
        try {
            impresionMediaCarta = datos[82].toString();
        } catch (Exception e) {
            impresionMediaCarta = "";
        }
        try {
            impresionCarta = datos[83].toString();
        } catch (Exception e) {
            impresionCarta = "";
        }

        cmbTipoPos.setSelectedItem(impresionPos);
        cmbTipoMediaCarta.setSelectedItem(impresionMediaCarta);
        cmbTipoCarta.setSelectedItem(impresionCarta);
    }//GEN-LAST:event_btnGuardar3ActionPerformed

    private void btnGuardar4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar4ActionPerformed
        int columna;
        try {
            columna = tblImpresoras1.getSelectedColumn();
        } catch (Exception e) {
            metodos.msgError(this, "Seleccione una impresora");
            return;
        }

        if (columna == 0) {
            metodos.msgError(this, "Esta columna no se puede eliminar");
            return;
        } else if (columna == 1) {
            metodos.msgError(this, "Esta impresora no se puede eliminar");
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
                    metodos.msgError(this, "Esta impresora ya esta cargada!");
                    return;
                }
            }

            if (pos == 0) {
                metodos.msgError(this, "Espacio insuficiente.");
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
            metodos.msgError(this, "Seleccione un grupo.");
            return;
        }

        if (tblImpresoras1.getValueAt(0, tblImpresoras1.getSelectedColumn()).equals("") || tblImpresoras1.getValueAt(0, tblImpresoras1.getSelectedColumn()) == null) {
            metodos.msgError(this, "Impresora no válida.");
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

    private void rdSiCombinarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdSiCombinarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdSiCombinarActionPerformed

    private void rdNoCombinarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdNoCombinarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdNoCombinarActionPerformed

    private void rdSiModificarNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdSiModificarNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdSiModificarNombreActionPerformed

    private void rdNoModificarNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdNoModificarNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdNoModificarNombreActionPerformed

    private void rdSiBolsaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdSiBolsaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdSiBolsaActionPerformed

    private void rdNoBolsaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdNoBolsaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdNoBolsaActionPerformed

    private void txtValorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtValorKeyTyped

    private void txtValorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorKeyReleased

    }//GEN-LAST:event_txtValorKeyReleased

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
        instancias.getSql().aumentarTurno(txtTurno.getText());
        metodos.msgExito(this, "¡Turno actualizado correctamente!");
        instancias.getFactura().consultarMaestros();
        instancias.getPedido().consultarMaestros();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void rdSiConsecutivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdSiConsecutivoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdSiConsecutivoActionPerformed

    private void rdNoConsecutivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdNoConsecutivoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdNoConsecutivoActionPerformed

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

    private void ubicacionNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ubicacionNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ubicacionNoActionPerformed

    private void horaNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_horaNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_horaNoActionPerformed

    private void btnGuardar5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar5ActionPerformed
        if (txtFranquisia.getText().equals("")) {
            metodos.msgError(this, "Ingrese la franquisia");
            return;
        }

        if (txtComision.getText().equals("")) {
            metodos.msgError(this, "Ingrese la comisión");
            return;
        }

        for (int i = 0; i < tblTarjetas.getRowCount(); i++) {
            if (tblTarjetas.getValueAt(i, 0).toString().equalsIgnoreCase(txtFranquisia.getText())) {
                metodos.msgError(this, "La franquisia ya existe");
                return;
            }
        }

        DefaultTableModel modelo = (DefaultTableModel) tblTarjetas.getModel();
        modelo.addRow(new Object[]{txtFranquisia.getText(), txtComision.getText()});

        metodos.msgExito(this, "Tarjeta cargada con exito!");

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
            metodos.msgAdvertencia(null, "Seleccione una franquisia");
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
            metodos.msgAdvertencia(null, "Seleccione una fecha");
        }
    }//GEN-LAST:event_popBorrar1ActionPerformed

    private void rdSiPondNegativoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdSiPondNegativoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdSiPondNegativoActionPerformed

    private void rdNoPondNegativoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdNoPondNegativoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdNoPondNegativoActionPerformed

    private void rdSiModificarPrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdSiModificarPrecioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdSiModificarPrecioActionPerformed

    private void rdNoModificarPrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdNoModificarPrecioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdNoModificarPrecioActionPerformed

    private void rdSiBorrarProdMesasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdSiBorrarProdMesasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdSiBorrarProdMesasActionPerformed

    private void rdNoBorrarProdMesasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdNoBorrarProdMesasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdNoBorrarProdMesasActionPerformed

    private void rdPreguntaFacturaNOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdPreguntaFacturaNOActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdPreguntaFacturaNOActionPerformed

    private void rdPreguntaOServicioNOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdPreguntaOServicioNOActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdPreguntaOServicioNOActionPerformed

    private void rdPreguntaCotizacionNOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdPreguntaCotizacionNOActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdPreguntaCotizacionNOActionPerformed

    private void rdPreguntaPlanSepareNOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdPreguntaPlanSepareNOActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdPreguntaPlanSepareNOActionPerformed

    private void txtCantCotizacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantCotizacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantCotizacionActionPerformed

    private void rdPreguntaPedidosNOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdPreguntaPedidosNOActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdPreguntaPedidosNOActionPerformed

    private void rdPreguntaFacturaSIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdPreguntaFacturaSIActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdPreguntaFacturaSIActionPerformed

    private void rdPreguntaOServicioSIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdPreguntaOServicioSIActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdPreguntaOServicioSIActionPerformed

    private void rdPreguntaCotizacionSIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdPreguntaCotizacionSIActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdPreguntaCotizacionSIActionPerformed

    private void rdPreguntaPlanSepareSIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdPreguntaPlanSepareSIActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdPreguntaPlanSepareSIActionPerformed

    private void rdPreguntaPedidosSIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdPreguntaPedidosSIActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdPreguntaPedidosSIActionPerformed

    private void rdPrevisualizarFacturaNOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdPrevisualizarFacturaNOActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdPrevisualizarFacturaNOActionPerformed

    private void rdPrevisualizarOServicioNOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdPrevisualizarOServicioNOActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdPrevisualizarOServicioNOActionPerformed

    private void rdPrevisualizarCotizacionNOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdPrevisualizarCotizacionNOActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdPrevisualizarCotizacionNOActionPerformed

    private void rdPrevisualizarPlanSepareNOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdPrevisualizarPlanSepareNOActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdPrevisualizarPlanSepareNOActionPerformed

    private void rdPrevisualizarPedidosNOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdPrevisualizarPedidosNOActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdPrevisualizarPedidosNOActionPerformed

    private void rdPrevisualizarFacturaSIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdPrevisualizarFacturaSIActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdPrevisualizarFacturaSIActionPerformed

    private void rdPrevisualizarOServicioSIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdPrevisualizarOServicioSIActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdPrevisualizarOServicioSIActionPerformed

    private void rdPrevisualizarCotizacionSIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdPrevisualizarCotizacionSIActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdPrevisualizarCotizacionSIActionPerformed

    private void rdPrevisualizarPlanSepareSIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdPrevisualizarPlanSepareSIActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdPrevisualizarPlanSepareSIActionPerformed

    private void rdPrevisualizarPedidosSIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdPrevisualizarPedidosSIActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdPrevisualizarPedidosSIActionPerformed

    private void rdSiPagoTercerosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdSiPagoTercerosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdSiPagoTercerosActionPerformed

    private void rdNoPagoTercerosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdNoPagoTercerosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdNoPagoTercerosActionPerformed

    private void tblHorasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHorasMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblHorasMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (cmbDoctores.getSelectedIndex() == 0) {
            metodos.msgError(this, "Escoja un usuario");
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

    private void rdSiFacturarSinInventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdSiFacturarSinInventarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdSiFacturarSinInventarioActionPerformed

    private void rdNoFacturarSinInventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdNoFacturarSinInventarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdNoFacturarSinInventarioActionPerformed

    private void cmbTipoMediaCartaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTipoMediaCartaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbTipoMediaCartaActionPerformed

    private void mostrarImpoconsumoNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mostrarImpoconsumoNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mostrarImpoconsumoNoActionPerformed

    private void mostrarRetencionesNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mostrarRetencionesNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mostrarRetencionesNoActionPerformed

    private void txtColumnasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtColumnasKeyReleased

    }//GEN-LAST:event_txtColumnasKeyReleased

    private void txtFilasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFilasKeyReleased

    }//GEN-LAST:event_txtFilasKeyReleased

    private void txtFilasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFilasKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtFilasKeyTyped

    private void tblRestauranteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblRestauranteMouseClicked
        if (evt.getClickCount() >= 1) {
            int fila = tblRestaurante.getSelectedRow();
            if (fila % 2 == 0) {

            } else {
                String valor = tblRestaurante.getValueAt(tblRestaurante.getSelectedRow(), tblRestaurante.getSelectedColumn()).toString();
                int num = Integer.parseInt(txtNumMesas.getText());

                if (valor.equals("")) {
                    num = num + 1;
                    tblRestaurante.setValueAt("Mesa. " + num, tblRestaurante.getSelectedRow(), tblRestaurante.getSelectedColumn());
                    txtNumMesas.setText(String.valueOf(num));
                } else {
                    tblRestaurante.setValueAt("", tblRestaurante.getSelectedRow(), tblRestaurante.getSelectedColumn());
                    num = num - 1;
                    txtNumMesas.setText(String.valueOf(num));
                }
            }
        }
    }//GEN-LAST:event_tblRestauranteMouseClicked

    private void btnGenerarTablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarTablaActionPerformed
//        if (tblRestaurante.getRowCount() > 0) {
//            if (metodos.msgPregunta(this, "Se perderan las mesas establecidas, ¿Desea contuniar? ") == 0) {
//            } else {
//                return;
//            }
//        }

        int columnas, filas;
        try {
            columnas = Integer.parseInt(txtColumnas.getText());
        } catch (Exception e) {
            metodos.msgError(null, "Ingrese el número de columnas");
            return;
        }

        try {
            filas = Integer.parseInt(txtFilas.getText());
        } catch (Exception e) {
            metodos.msgError(null, "Ingrese el número de filas.");
            return;
        }

        cargarColumna();
        cargarFila();

        for (int i = 0; i < tblRestaurante.getRowCount(); i++) {
            if (i % 2 == 0) {
                tblRestaurante.setRowHeight(i, 1);
            } else {
                tblRestaurante.setRowHeight(i, 30);
            }
        }

        txtNumMesas.setText("0");
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

    private void rdSiDevueltaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdSiDevueltaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdSiDevueltaActionPerformed

    private void rdNoDevueltaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdNoDevueltaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdNoDevueltaActionPerformed

    private void txtValorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtValorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValorActionPerformed

    private void rdSiDiasAutomaticosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdSiDiasAutomaticosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdSiDiasAutomaticosActionPerformed

    private void rdNoDiasAutomaticosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdNoDiasAutomaticosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdNoDiasAutomaticosActionPerformed

    private void rdNoSolicitudesPermisosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdNoSolicitudesPermisosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdNoSolicitudesPermisosActionPerformed

    private void rdSiCupoCreditoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdSiCupoCreditoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdSiCupoCreditoActionPerformed

    private void rdNoCupoCreditoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdNoCupoCreditoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdNoCupoCreditoActionPerformed

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

    private void rdSiPrevisualizarComandaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdSiPrevisualizarComandaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdSiPrevisualizarComandaActionPerformed

    private void rdNoPrevisualizarComandaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdNoPrevisualizarComandaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdNoPrevisualizarComandaActionPerformed

    private void rdSiPrevisualizarPrefacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdSiPrevisualizarPrefacturaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdSiPrevisualizarPrefacturaActionPerformed

    private void rdNoPrevisualizarPrefacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdNoPrevisualizarPrefacturaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdNoPrevisualizarPrefacturaActionPerformed

    private void txtCantCopiasComandaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantCopiasComandaKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtCantCopiasComandaKeyTyped

    private void txtCantCopiasPrefacturaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantCopiasPrefacturaKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtCantCopiasPrefacturaKeyTyped

    private void btnOcultarImpresionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOcultarImpresionesActionPerformed
        if (pnlImpresiones.isVisible()) {
            pnlImpresiones.setVisible(false);
            btnOcultarImpresiones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/boton-de-suma.png")));
        } else {
            pnlImpresiones.setVisible(true);
            btnOcultarImpresiones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/boton-resta.png")));
        }
    }//GEN-LAST:event_btnOcultarImpresionesActionPerformed

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
            if (metodos.msgPregunta(null, "¿Desea quitar mesero?") == 0) {
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

    private void rdCopiasNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdCopiasNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdCopiasNoActionPerformed

    private void txtNumFactIncrementoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumFactIncrementoKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtNumFactIncrementoKeyTyped

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
                        metodos.msgError(null, "Solo se permiten números positivos.");
                        tblConfig.setValueAt("10", fila, 3);
                        return;
                    }

                    if (Integer.parseInt(tblConfig.getValueAt(fila, 3).toString()) > 30) {
                        metodos.msgError(null, "Solo se permiten entre los primeros 30 minutos.");
                        tblConfig.setValueAt("10", fila, 3);
                        return;
                    }
                } catch (Exception e) {
                    metodos.msgError(null, "Solo se permiten números.");
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
                        metodos.msgError(null, "Solo se permiten números positivos.");
                        tblConfig.setValueAt("10", fila, 4);
                        return;
                    }

                    if (Integer.parseInt(tblConfig.getValueAt(fila, 4).toString()) > 30) {
                        metodos.msgError(null, "Solo se permiten entre los primeros 30 minutos.");
                        tblConfig.setValueAt("10", fila, 4);
                        return;
                    }
                } catch (Exception e) {
                    metodos.msgError(null, "Solo se permiten números.");
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
                        metodos.msgError(null, "Solo se permiten números positivos.");
                        tblConfig.setValueAt("8", fila, 6);
                        return;
                    }

                    if (Integer.parseInt(tblConfig.getValueAt(fila, 6).toString()) > 23) {
                        metodos.msgError(null, "Son 23 horas como máximo.");
                        tblConfig.setValueAt("8", fila, 6);
                        return;
                    }
                } catch (Exception e) {
                    metodos.msgError(null, "Solo se permiten números.");
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
                        metodos.msgError(null, "Solo se permiten números positivos.");
                        tblConfig.setValueAt("8", fila, 8);
                        return;
                    }

                    if (Integer.parseInt(tblConfig.getValueAt(fila, 8).toString()) > 23) {
                        metodos.msgError(null, "Son 23 horas como máximo.");
                        tblConfig.setValueAt("8", fila, 8);
                        return;
                    }
                } catch (Exception e) {
                    metodos.msgError(null, "Solo se permiten números.");
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
                        metodos.msgError(null, "Solo se permiten números positivos.");
                        tblConfig.setValueAt("24", fila, 10);
                        return;
                    }

                    if (Integer.parseInt(tblConfig.getValueAt(fila, 10).toString()) > 24) {
                        metodos.msgError(null, "Son 24 horas como máximo.");
                        tblConfig.setValueAt("24", fila, 10);
                        return;
                    }
                } catch (Exception e) {
                    metodos.msgError(null, "Solo se permiten números.");
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
                        metodos.msgError(null, "Solo se permiten números positivos.");
                        tblConfig.setValueAt("20", fila, 14);
                        return;
                    }
                } catch (Exception e) {
                    metodos.msgError(null, "Solo se permiten números.");
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
            btnGuardar1.requestFocus();
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
        metodos.msgAdvertencia(this, "Se va a perder todos los registros del estante de cascos");

        if (metodos.msgPregunta(this, "¿Desea continuar?") == 0) {

            int columnas, filas;
            try {
                columnas = Integer.parseInt(txtColumnas1.getText());
            } catch (Exception e) {
                metodos.msgError(null, "Numero de columnas invalido.");
                return;
            }

            try {
                filas = Integer.parseInt(txtFilas1.getText());
            } catch (Exception e) {
                metodos.msgError(null, "Numero de filas invalido.");
                return;
            }

            instancias.getSql().eliminarTodo("bdCascos");
            for (int i = 0; i < Integer.parseInt(txtFilas1.getText()); i++) {
                for (int j = 0; j < Integer.parseInt(txtColumnas1.getText()); j++) {
                    if (!instancias.getSql().agregarCasco(tblCascos.getValueAt(j, i).toString())) {
                        metodos.msgError(this, "Error al guardar los espacios de los cascos");
                        return;
                    }
                }
            }

            if (!instancias.getSql().modificarMaestraParqueadero(String.valueOf(filas), String.valueOf(columnas))) {
                metodos.msgError(this, "Error al guardar la configuración");
                return;
            }

            metodos.msgExito(this, "Cambios guardados con éxito");
        }
    }//GEN-LAST:event_btnGuardar6ActionPerformed

    private void popBorrar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popBorrar2ActionPerformed
        if (tblConfig.getSelectedRow() > -1) {
            int fila = tblConfig.getSelectedRow();

            DefaultTableModel modelo = (DefaultTableModel) tblConfig.getModel();
            modelo.removeRow(fila);

            tblConfig.removeEditor();
        } else {
            metodos.msgAdvertencia(null, "Seleccione un servicio");
        }
    }//GEN-LAST:event_popBorrar2ActionPerformed

    private void btnAggResponsabilidadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAggResponsabilidadesActionPerformed
        if (cmbResponsabilidades.getSelectedIndex() == 0) {
            metodos.msgAdvertenciaAjustado(null, "Seleccione una responsabilidad");
            return;
        }

        for (int i = 0; i < cmbMisResponsabilidades.getItemCount(); i++) {
            if (cmbMisResponsabilidades.getItemAt(i).equals(cmbResponsabilidades.getSelectedItem())) {
                metodos.msgAdvertenciaAjustado(null, "Ya cargó esta responsabilidad");
                break;
            }
        }

        cmbMisResponsabilidades.addItem(cmbResponsabilidades.getSelectedItem());
        cmbResponsabilidades.setSelectedIndex(0);
        cmbMisResponsabilidades.setSelectedIndex(cmbMisResponsabilidades.getItemCount() - 1);
    }//GEN-LAST:event_btnAggResponsabilidadesActionPerformed

    private void btnAggResponsabilidades1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAggResponsabilidades1ActionPerformed
        cmbMisResponsabilidades.removeItem(cmbMisResponsabilidades.getSelectedItem());
    }//GEN-LAST:event_btnAggResponsabilidades1ActionPerformed

    private void txtEmailKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailKeyReleased

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
        if (rdSiPvpIva.isSelected()) {
            rdSiPvpImpo.setSelected(true);
            rdSiCostoIva.setSelected(true);
            rdSiCostoImpo.setSelected(true);
        }
    }//GEN-LAST:event_rdSiPvpIvaItemStateChanged

    private void rdNoPvpIvaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdNoPvpIvaItemStateChanged
        if (rdNoPvpIva.isSelected()) {
            rdNoPvpImpo.setSelected(true);
            rdNoCostoIva.setSelected(true);
            rdNoCostoImpo.setSelected(true);
        }
    }//GEN-LAST:event_rdNoPvpIvaItemStateChanged

    public void cargarProducto(String codigo) {
        ndProducto nodo = null;

        String CodigoProd = "";
        if (codigo.equals("")) {
            CodigoProd = "";
        } else {
            Object[][] listado = instancias.getSql().getCodigosRelacionados(codigo, " where codigo");
            if (listado.length > 0) {
                codigo = listado[0][0].toString();
            }

            nodo = instancias.getSql().getDatosProducto(codigo, "bdProductos");
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

        ndProducto nodo = null;

        String CodigoProd = "";
        if (codigo.equals("")) {
            CodigoProd = "";
        } else {
            Object[][] listado = instancias.getSql().getCodigosRelacionados(codigo, " where codigo");
            if (listado.length > 0) {
                codigo = listado[0][0].toString();
            }

            nodo = instancias.getSql().getDatosProducto(codigo, "bdProductos");
            if (nodo.getIdSistema() != null) {
                CodigoProd = nodo.getIdSistema();
            }
        }

        if (!CodigoProd.equals("")) {
            for (int i = 0; i < tblConfig.getRowCount(); i++) {
                if (nodo.getIdSistema().equals(tblConfig.getValueAt(i, 0))) {
                    metodos.msgAdvertenciaAjustado(null, "Este servicio ya esta cargado");
                    txtCodProducto.setText("");
                    return;
                }
            }

            if (instancias.getSql().getProdActivo(nodo.getCodigo())) {
                metodos.msgError(null, "Este producto esta inactivo");
                btnGuardar1.requestFocus();
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
        buscProductos buscar = new buscProductos(null, true, false, "parqueadero", "productos1");
        buscar.setLocationRelativeTo(null);
        instancias.setBusProductos(buscar);
        instancias.setCampoActual(txtCodProducto);
        txtCodProducto.requestFocus();
        buscar.noEncontrado(codigo.replace("'", "//"));
        buscar.show();
    }

    public void ventanaProductos(String codigo) {
        buscProductos buscar = new buscProductos(null, rootPaneCheckingEnabled, false, "", "productos1");
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
            metodos.msgAdvertenciaAjustado(null, "Ingrese la cantidad de filas");
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
            metodos.msgAdvertenciaAjustado(null, "Ingrese la cantidad de columnas");
            return;
        }

        if (columnas > 26) {
            metodos.msgError(null, "Son 26 columnas como máximo.");
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
        DefaultTableModel model = (DefaultTableModel) tblRestaurante.getModel();

        int columnas = Integer.parseInt(txtColumnas.getText());
        if (columnas > 26) {
            metodos.msgError(null, "Son 26 columnas como máximo.");
            txtColumnas.setText("");
            return;
        }

        tblRestaurante.setModel(new javax.swing.table.DefaultTableModel(new Object[][]{}, new String[]{
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
        }) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false,
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });

        tblRestaurante.setRowHeight(40);
        tblRestaurante.getTableHeader().setResizingAllowed(false);
        tblRestaurante.getTableHeader().setReorderingAllowed(false);
        jScrollPane11.setViewportView(tblRestaurante);

        for (int i = columnas; i < 26; i++) {
            if (tblRestaurante.getColumnModel().getColumnCount() > 0) {
                tblRestaurante.getColumnModel().getColumn(i).setMinWidth(0);
                tblRestaurante.getColumnModel().getColumn(i).setPreferredWidth(0);
                tblRestaurante.getColumnModel().getColumn(i).setMaxWidth(0);
            }
        }
    }

    public void cargarFila() {
        DefaultTableModel model = (DefaultTableModel) tblRestaurante.getModel();

        int filas = Integer.parseInt(txtFilas.getText());

        while (tblRestaurante.getRowCount() > 0) {
            model.removeRow(0);
        }

        filas = filas * 2;

        for (int i = 1; i <= filas; i++) {
            model.addRow(new Object[]{"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""});
        }
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
        txtTurno.setText(turno);
    }

    public void llenarDatos() {

        Object[] datos = instancias.getSql().getDatosMaestra();

        cmbMisResponsabilidades.removeAllItems();

        if (datos[113] != null) {
            String[] responsabilidades = datos[113].toString().split(", ");
            for (int i = 0; i < responsabilidades.length; i++) {
                if (!"".equals(responsabilidades[i])) {
                    cmbMisResponsabilidades.addItem(responsabilidades[i]);
                }
            }
        }

        String passGmail;
        try {
            passGmail = datos[115].toString();
        } catch (Exception e) {
            passGmail = "";
        }
        txtPassword.setText(passGmail);

        String gmail;
        try {
            gmail = datos[114].toString();
        } catch (Exception e) {
            gmail = "";
        }
        txtEmail.setText(gmail);

        if ((Boolean) datos[109]) {
            rdSiBorrarMesas.setSelected(true);
        } else {
            rdNoBorrarMesas.setSelected(true);
        }

        if ((Boolean) datos[108]) {
            rdSiSoloVisibleMesas.setSelected(true);
        } else {
            rdNoSoloVisibleMesas.setSelected(true);
        }

        if ((Boolean) datos[107]) {
            rdSiFacturarMesas.setSelected(true);
        } else {
            rdNoFacturarMesas.setSelected(true);
        }

        String impresoraPrefactura;
        try {
            impresoraPrefactura = datos[106].toString();
        } catch (Exception e) {
            impresoraPrefactura = "";
        }
        cmbPrefactura.setSelectedItem(impresoraPrefactura);

        String impresoraComanda;
        try {
            impresoraComanda = datos[105].toString();
        } catch (Exception e) {
            impresoraComanda = "";
        }
        cmbComanda.setSelectedItem(impresoraComanda);

        if ((Boolean) datos[104]) {
            rdSiPrevisualizarPrefactura.setSelected(true);
        } else {
            rdNoPrevisualizarPrefactura.setSelected(true);
        }

        if ((Boolean) datos[103]) {
            rdSiPrevisualizarComanda.setSelected(true);
        } else {
            rdNoPrevisualizarComanda.setSelected(true);
        }

        String copiasPrefactura;
        try {
            copiasPrefactura = datos[102].toString();
        } catch (Exception e) {
            copiasPrefactura = "";
        }
        txtCantCopiasPrefactura.setText(copiasPrefactura);

        String copiasComanda;
        try {
            copiasComanda = datos[101].toString();
        } catch (Exception e) {
            copiasComanda = "0";
        }
        txtCantCopiasComanda.setText(copiasComanda);

        String numFacturaIncremento;
        try {
            numFacturaIncremento = datos[112].toString();
        } catch (Exception e) {
            numFacturaIncremento = "0";
        }
        txtNumFactIncremento.setText(numFacturaIncremento);

        if ((Boolean) datos[100]) {
            rdImpresionPorGrupo.setSelected(true);
        } else {
            rdImpresionPorGrupo.setSelected(false);
        }

        if ((Boolean) datos[99]) {
            instancias.setCostoConImpoconsumo(true);
            rdSiCostoImpo.setSelected(true);
        } else {
            instancias.setCostoConImpoconsumo(false);
            rdNoCostoImpo.setSelected(true);
        }

        if ((Boolean) datos[98]) {
            instancias.setPvpConImpoconsumo(true);
            rdSiPvpImpo.setSelected(true);
        } else {
            instancias.setPvpConImpoconsumo(false);
            rdNoPvpImpo.setSelected(true);
        }

        cmbFoco.setSelectedItem(datos[97]);

        if ((Boolean) datos[42]) {
            rdSiCupoCredito.setSelected(true);
        } else {
            rdNoCupoCredito.setSelected(true);
        }

        if ((Boolean) datos[95]) {
            rdSiDiasAutomaticos.setSelected(true);
        } else {
            rdNoDiasAutomaticos.setSelected(true);
        }

        if ((Boolean) datos[96]) {
            rdSiSolicitudesPermisos.setSelected(true);
        } else {
            rdNoSolicitudesPermisos.setSelected(true);
        }

        cmbHoraInicio1.setSelectedItem(datos[92]);
        cmbHoraFin1.setSelectedItem(datos[93]);
        cmbIntervalo1.setSelectedItem(datos[94]);

        if ((Boolean) datos[43]) {
            rdCopiasSi.setSelected(true);
        } else {
            rdCopiasNo.setSelected(true);
        }

        if ((Boolean) datos[91]) {
            rdSiDevuelta.setSelected(true);
        } else {
            rdNoDevuelta.setSelected(true);
        }

        String porcPropina;
        try {
            porcPropina = datos[90].toString();
        } catch (Exception e) {
            porcPropina = "";
        }
        txtPorcPropina.setText(porcPropina);

        String titulo;
        try {
            titulo = datos[86].toString();
        } catch (Exception e) {
            titulo = "";
        }
        txtNombreFormato.setText(titulo);

        String columnas;
        try {
            columnas = datos[89].toString();
        } catch (Exception e) {
            columnas = "";
        }
        txtColumnas.setText(columnas);

        String filas;
        try {
            filas = datos[88].toString();
        } catch (Exception e) {
            filas = "";
        }
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
            for (int i = 0; i < mesas.length; i++) {
                int x = Integer.parseInt(mesas[i][0].toString().split(",")[0]);
                int y = Integer.parseInt(mesas[i][0].toString().split(",")[1]);
                tblRestaurante.setValueAt(mesas[i][1].toString(), x, y);
            }
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

        if (instancias.getConfiguraciones().isRestaurante()) {
            jPanel13.setVisible(true);
//            
//            lbTelefono37.setVisible(false);
//            rdSiConsecutivo.setVisible(false);
//            rdNoConsecutivo.setVisible(false);
        } else {
            jPanel13.setVisible(false);
//            lbTelefono37.setVisible(true);
//            rdSiConsecutivo.setVisible(true);
//            rdNoConsecutivo.setVisible(true);
        }

        String infoEmpresa = "";

        try {
            if (!datos[8].equals("")) {
                infoEmpresa = infoEmpresa + "" + (String) datos[8] + "";
            }
        } catch (Exception e) {
        }

        try {
            if (!datos[9].equals("")) {
                infoEmpresa = infoEmpresa + "\n" + (String) datos[9];
            }
        } catch (Exception e) {
        }

        try {
            if (!datos[10].equals("")) {
                infoEmpresa = infoEmpresa + "\n" + (String) datos[10];
            }
        } catch (Exception e) {
        }

        try {
            if (!datos[13].equals("")) {
                infoEmpresa = infoEmpresa + "\n" + (String) datos[13];
            }
        } catch (Exception e) {
        }

        try {
            if (!datos[14].equals("")) {
                infoEmpresa = infoEmpresa + "\n" + (String) datos[14];
            }
        } catch (Exception e) {
        }

        if (!instancias.getRegimen().equals("")) {
            String datosEmpresa = metodosGenerales.convertToMultiline(infoEmpresa);
            String datosEmpresaReimpresion = infoEmpresa;
            String datosEmpresaCompleto = metodosGenerales.convertToMultiline(infoEmpresa + "" + "");
            instancias.setDatosEmpresa(datosEmpresa, (String) datos[16], (String) datos[15], datosEmpresaCompleto, datosEmpresaReimpresion);
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
            horaIni = Integer.parseInt(datos[37].toString());
            horaFin = Integer.parseInt(datos[38].toString());
            intervalo = Integer.parseInt(datos[39].toString());
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

        if (instancias.getConfiguraciones().isRestaurante()) {
            lbNit.setVisible(false);
            txtL1.setVisible(false);
        } else {
            lbNit.setVisible(true);
            txtL1.setVisible(true);
            lbNit.setText("Número de congeladas");
        }

        /* CARGAMOS LA IMPRESORA SEGUN EL TIPO */
        String impresionPos, impresionMediaCarta, impresionCarta;

        try {
            impresionPos = datos[81].toString();
        } catch (Exception e) {
            impresionPos = "";
        }
        try {
            impresionMediaCarta = datos[82].toString();
        } catch (Exception e) {
            impresionMediaCarta = "";
        }
        try {
            impresionCarta = datos[83].toString();
        } catch (Exception e) {
            impresionCarta = "";
        }

        cmbTipoPos.setSelectedItem(impresionPos);
        cmbTipoMediaCarta.setSelectedItem(impresionMediaCarta);
        cmbTipoCarta.setSelectedItem(impresionCarta);
        /* FIN ANEXO */

 /* ANEXO DE LA ORDEN DE SERVICIO */
        String anexoOrden;
        try {
            anexoOrden = datos[60].toString();
        } catch (Exception e) {
            anexoOrden = "";
        }
        txtAnexoOrden.setText(anexoOrden);
        /* FIN ANEXO */

        String diasAlertaResolucion;
        try {
            diasAlertaResolucion = datos[28].toString();
        } catch (Exception e) {
            diasAlertaResolucion = "";
        }
        txtDiasAlerta1.setText(diasAlertaResolucion);

        String informacion;
        try {
            informacion = datos[56].toString();
        } catch (Exception e) {
            informacion = "";
        }
        txtAnexoFactura.setText(informacion);

//        String imprimirCada;
//        try {
//            imprimirCada = datos[86].toString();
//        } catch (Exception e) {
//            imprimirCada = "";
//        }
//        txtSegundos.setText(imprimirCada);
        String cantidadEstablecida;
        try {
            cantidadEstablecida = datos[87].toString();
        } catch (Exception e) {
            cantidadEstablecida = "";
        }
        txtCantidadEstablecida.setText(cantidadEstablecida);

        String turno;
        try {
            turno = datos[55].toString();
        } catch (Exception e) {
            turno = "1";
        }
        txtTurno.setText(turno);

        if ((Boolean) datos[84]) {
            mostrarImpoconsumoSi.setSelected(true);
        } else {
            mostrarImpoconsumoNo.setSelected(true);
        }

        if ((Boolean) datos[85]) {
            mostrarRetencionesSi.setSelected(true);
        } else {
            mostrarRetencionesNo.setSelected(true);
        }

        if ((Boolean) datos[79]) {
            rdSiFacturarSinInventario.setSelected(true);
        } else {
            rdNoFacturarSinInventario.setSelected(true);
        }

        if ((Boolean) datos[78]) {
            rdSiPagoTerceros.setSelected(true);
        } else {
            rdNoPagoTerceros.setSelected(true);
        }

        //IMPRIMIR
        if ((Boolean) datos[63]) {
            rdPreguntaFacturaSI.setSelected(true);
        } else {
            rdPreguntaFacturaNO.setSelected(true);
        }

//        if ((Boolean) datos[80]) {
//            reimpresionSi.setSelected(true);
//        } else {
//            reimpresionNo.setSelected(true);
//        }
        if ((Boolean) datos[64]) {
            rdPreguntaOServicioSI.setSelected(true);
        } else {
            rdPreguntaOServicioNO.setSelected(true);
        }

        if ((Boolean) datos[65]) {
            rdPreguntaCotizacionSI.setSelected(true);
        } else {
            rdPreguntaCotizacionNO.setSelected(true);
        }

        if ((Boolean) datos[66]) {
            rdPreguntaPlanSepareSI.setSelected(true);
        } else {
            rdPreguntaPlanSepareNO.setSelected(true);
        }

        if ((Boolean) datos[67]) {
            rdPreguntaPedidosSI.setSelected(true);
        } else {
            rdPreguntaPedidosSI.setSelected(true);
        }
        // FIN DEL IMPRIMIR

        // PREVISUALIZAR
        if ((Boolean) datos[68]) {
            rdPrevisualizarFacturaSI.setSelected(true);
        } else {
            rdPrevisualizarFacturaNO.setSelected(true);
        }

        if ((Boolean) datos[69]) {
            rdPrevisualizarOServicioSI.setSelected(true);
        } else {
            rdPrevisualizarOServicioNO.setSelected(true);
        }

        if ((Boolean) datos[70]) {
            rdPrevisualizarCotizacionSI.setSelected(true);
        } else {
            rdPrevisualizarCotizacionNO.setSelected(true);
        }

        if ((Boolean) datos[71]) {
            rdPrevisualizarPlanSepareSI.setSelected(true);
        } else {
            rdPrevisualizarPlanSepareNO.setSelected(true);
        }

        if ((Boolean) datos[72]) {
            rdPrevisualizarPedidosSI.setSelected(true);
        } else {
            rdPrevisualizarPedidosNO.setSelected(true);
        }
        // FIN DEL PREVISUALIZAR

        // COPIAS
        String copiasFactura, copiasOServicio, copiasCotizacion, copiasPlanSepare, copiasPedidos;
        try {
            copiasFactura = datos[73].toString();
        } catch (Exception e) {
            copiasFactura = "0";
        }

        try {
            copiasOServicio = datos[74].toString();
        } catch (Exception e) {
            copiasOServicio = "0";
        }

        try {
            copiasCotizacion = datos[75].toString();
        } catch (Exception e) {
            copiasCotizacion = "0";
        }

        try {
            copiasPlanSepare = datos[76].toString();
        } catch (Exception e) {
            copiasPlanSepare = "0";
        }

        try {
            copiasPedidos = datos[77].toString();
        } catch (Exception e) {
            copiasPedidos = "0";
        }

        txtCantFactura.setText(copiasFactura);
        txtCantOServicio.setText(copiasOServicio);
        txtCantCotizacion.setText(copiasCotizacion);
        txtCantPlanSepare.setText(copiasPlanSepare);
        txtCantPedidos.setText(copiasPedidos);
        //FIN COPIAS

        if ((Boolean) datos[62]) {
            rdSiBorrarProdMesas.setSelected(true);
        } else {
            rdNoBorrarProdMesas.setSelected(true);
        }

        if ((Boolean) datos[61]) {
            rdSiModificarPrecio.setSelected(true);
        } else {
            rdNoModificarPrecio.setSelected(true);
        }

        if ((Boolean) datos[57]) {
            rdSiConsecutivo.setSelected(true);
        } else {
            rdNoConsecutivo.setSelected(true);
        }

        if ((Boolean) datos[58]) {
            horaSi.setSelected(true);
        } else {
            horaNo.setSelected(true);
        }

        if ((Boolean) datos[54]) {
            rdTurnoSi.setSelected(true);
        } else {
            rdTurnoNo.setSelected(true);
        }

        if ((Boolean) datos[59]) {
            rdSiPondNegativo.setSelected(true);
        } else {
            rdNoPondNegativo.setSelected(true);
        }

        String valorBolsa;
        try {
            valorBolsa = datos[53].toString();
        } catch (Exception e) {
            valorBolsa = "0";
        }
        txtValor.setText(valorBolsa);

        if ((Boolean) datos[52]) {
            rdSiBolsa.setSelected(true);
        } else {
            rdNoBolsa.setSelected(true);
        }

        if ((Boolean) datos[51]) {
            rdSiModificarNombre.setSelected(true);
        } else {
            rdNoModificarNombre.setSelected(true);
        }

        if ((Boolean) datos[50]) {
            rdSiCombinar.setSelected(true);
        } else {
            rdNoCombinar.setSelected(true);
        }

        txtLimite.setText((String) datos[49]);

        txtL1.setText((String) datos[0]);
        try {
            if (datos[1].toString().equals("pos")) {
                pos.setSelected(true);
                instancias.setTipoImpresion("Pos");
            } else if (datos[1].toString().equals("facturaCompleta")) {
                carta.setSelected(true);
                instancias.setTipoImpresion("");
            } else {
                mediaCarta.setSelected(true);
                instancias.setTipoImpresion("");
            }
            instancias.setImpresion(datos[1].toString());
        } catch (Exception e) {
            instancias.setTipoImpresion("");
            instancias.setImpresion("factura");
        }
        instancias.getMenu().abrirCaja();

        try {
            if (datos[2].equals("NO")) {
                instancias.setCopias(false);
            } else {
                instancias.setCopias(true);
            }
        } catch (Exception e) {
            instancias.setCopias(true);
        }

        instancias.getMenu().actualizarUsuario(instancias.getUsuario());

//        try {
//            Integer cant = Integer.parseInt(datos[3].toString());
//            instancias.setCantCopias(cant);
//            txtCantFactura.setText(cant + "");
//        } catch (Exception e) {
//            instancias.setCantCopias(null);
//        }
        try {
            if (datos[5].equals("NO")) {
                utilidadNo.setSelected(true);
            } else {
                utilidadSi.setSelected(true);
            }
        } catch (Exception e) {
            utilidadSi.setSelected(true);
        }
        try {
            if (datos[6].equals("peso")) {
                descuentoPeso.setSelected(true);
            } else {
                descuentoPorcentaje.setSelected(true);
            }
        } catch (Exception e) {
            descuentoPorcentaje.setSelected(true);
        }

        instancias.setSegundaClave(datos[7].toString());
        txtContra.setText(datos[7].toString());
        txtContra2.setText(datos[7].toString());

        txtNit.setText((String) datos[8]);
        txtNombre.setText((String) datos[9]);
        txtRegimen.setText((String) datos[10]);

        txtDireccion.setText((String) datos[13]);
        txtTelefono.setText((String) datos[14]);
        txtPiePagina.setText((String) datos[15]);
        txtLegal.setText((String) datos[16]);

        try {
            if (datos[17].equals("NO")) {
                ubicacionNo.setSelected(true);
            } else {
                ubicacionSi.setSelected(true);
            }
        } catch (Exception e) {
            ubicacionSi.setSelected(true);
        }

        txtDiasAlerta.setText((String) datos[18]);

        if ((boolean) datos[19]) {
            recogidaSi.setSelected(true);
            instancias.setImprimirRecogida(true);
        } else {
            recogidaNo.setSelected(true);
            instancias.setImprimirRecogida(false);
        }

        if ((boolean) datos[20]) {
            lectorSi.setSelected(true);
            instancias.setLector(true);
        } else {
            lectorNo.setSelected(true);
            instancias.setLector(false);
        }

        txtTituloFactura.setText((String) datos[21]);
        instancias.setTituloFactura((String) datos[21]);

        if ((boolean) datos[22]) {
            rdSiPvpIva.setSelected(true);
            instancias.setPvpConIva(true);
        } else {
            rdNoPvpIva.setSelected(true);
            instancias.setPvpConIva(false);
        }

        if ((boolean) datos[23]) {
            rdSiCostoIva.setSelected(true);
            instancias.setCostoConIva(true);
        } else {
            rdNoCostoIva.setSelected(true);
            instancias.setCostoConIva(false);
        }

        if ((boolean) datos[24]) {
            rdSiVentasPredeterminado.setSelected(true);
            instancias.setVentasPredeterminado(true);
        } else {
            rdNoVentasPredeterminado.setSelected(true);
            instancias.setVentasPredeterminado(false);
        }

        if ((boolean) datos[25]) {
            rdSiMensajeUtilidad.setSelected(true);
            instancias.setMensajeUtilidad(true);
        } else {
            rdNoMensajeUtilidad.setSelected(true);
            instancias.setMensajeUtilidad(false);
        }

//        if ((boolean) datos[26]) {
//            instancias.setConsecutivosDiferentes(true);
//        } else {
//            instancias.setConsecutivosDiferentes(false);
//        }
        instancias.setDiasAlertaResolucion((String) datos[28]);

        try {
            instancias.setAlertaFechaDias((String) datos[29]);
        } catch (Exception e) {
            instancias.setAlertaFechaDias("0");
        }

        try {
            instancias.setAlertaCantidadDias((String) datos[30]);
        } catch (Exception e) {
            instancias.setAlertaCantidadDias("0");
        }
        try {
            instancias.setAlertaPromedioDias((String) datos[31]);
        } catch (Exception e) {
            instancias.setAlertaPromedioDias("0");
        }

//        //TERMINALES
//        instancias.getFactura().actualizarResolucion();
        if (utilidadSi.isSelected()) {
            instancias.setUtilidad(true);
        } else {
            instancias.setUtilidad(false);
        }

        if (ubicacionSi.isSelected()) {
            instancias.setUbicacion(true);
        } else {
            instancias.setUbicacion(false);
        }

        if (horaSi.isSelected()) {
            instancias.setHora(true);
        } else {
            instancias.setHora(false);
        }

        if (descuentoPorcentaje.isSelected()) {
            instancias.setDescuento("porcentaje");
        } else {
            instancias.setDescuento("peso");
        }

        try {
            if (datos[32].toString().equals("")) {
                datos[32] = "0";
            }
            instancias.setDiasCobrarMora((String) datos[32]);
        } catch (Exception e) {
            instancias.setDiasCobrarMora("0");
        }

        try {
            if (datos[33].toString().equals("")) {
                datos[33] = "0";
            }
            instancias.setPorcentajeMora((String) datos[33]);
        } catch (Exception e) {
            instancias.setPorcentajeMora("0");
        }

        txtDiasCobrarMora.setText(datos[32].toString());
        txtPorcentajeMora.setText(datos[33].toString());

        if ((boolean) datos[34]) {
            rdSiGenerarOrden.setSelected(true);
            instancias.setGeneraOrdenMedica(true);
        } else {
            rdNoGenerarOrden.setSelected(true);
            instancias.setGeneraOrdenMedica(false);
        }
        if ((boolean) datos[35]) {
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

        if ((boolean) datos[36]) {
            rdSiImprimirFactura.setSelected(true);
            instancias.setImprimirFacturaOrdenMedica(true);
        } else {
            rdNoImprimirFactura.setSelected(true);
            instancias.setImprimirFacturaOrdenMedica(false);
        }

        cmbHoraInicio.setSelectedItem(datos[37]);
        cmbHoraFin.setSelectedItem(datos[38]);
        cmbIntervalo.setSelectedItem(datos[39]);
        String[] configAgenda = {datos[37].toString(), datos[38].toString(), datos[39].toString()};
        instancias.setConfigAgenda(configAgenda);

        txtCodigoPrestadorServico.setText(datos[40].toString());

        try {
            cmbTipoPrestador.setSelectedItem(datos[46].toString());
        } catch (Exception e) {
            cmbTipoPrestador.setSelectedIndex(0);

        }

//        if ((boolean) datos[42]) {
//            rdPrevisualizarFacturaSI.setSelected(true);
//            instancias.setPrevisualizarFactura(false);
//        } else {
//            rdPrevisualizarNO.setSelected(true);
//            instancias.setPrevisualizarFactura(true);
//        }
        if ((boolean) datos[41]) {
            rdSiImprimirCuadreFiscal.setSelected(true);
            instancias.setImprimirCuadreFiscal(true);
        } else {
            rdNoImprimirCuadreFiscal.setSelected(true);
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
        if (!instancias.getConfiguraciones().isCreditos()) {
            pnlCredito.setVisible(false);
        }

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
            if (datos[45].toString().equals("")) {
                instancias.setDescuentoMaximoVentas(big.getBigDecimal("100"));
            } else {
                instancias.setDescuentoMaximoVentas(big.getBigDecimal(datos[45]));
                txtDescuentoMaximo.setText(datos[45].toString());
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

        instancias.getFactura().setTipo("facturacion");
//        instancias.getMesas().setTipo("facturacion");

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

        instancias.getNc().setVendedores(vendedores);
        instancias.getNd().setVendedores(vendedores);
        instancias.getCuentaCobro().setVendedores(vendedores);
        instancias.getFactura().setVendedores(vendedores);
        instancias.getCotiza().setVendedores(vendedores);
        instancias.getFacturaCreditos().setVendedores(vendedores);
        instancias.getPedido().setVendedores(vendedores);
        instancias.getPlanSepare().setVendedores(vendedores);
        instancias.getClientes().setVendedores(vendedores);
        instancias.getRepNC().setVendedores(vendedores);

        if (instancias.getConfiguraciones().isMedico()) {
            instancias.getPaciente().setVendedores(vendedores);
        }

        if (instancias.getConfiguraciones().isServicioAutomotor()) {
            instancias.getOrdenServicio().setVendedores(mecanicos);
        } else {
            instancias.getOrdenServicio().setVendedores(tecnicos);
        }

        instancias.getRepCartera().llenarDatos(vendedores);

        try {
            instancias.getMesa1().setDomiciliarios(domiciliarios);
            instancias.getMesa1().setVendedores(vendedores);
        } catch (Exception e) {
        }

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizarMesas;
    private javax.swing.JButton btnAggResponsabilidades;
    private javax.swing.JButton btnAggResponsabilidades1;
    private javax.swing.JButton btnBusProd;
    private javax.swing.JButton btnGenerarTabla;
    private javax.swing.JButton btnGuardar1;
    private javax.swing.JButton btnGuardar2;
    private javax.swing.JButton btnGuardar3;
    private javax.swing.JButton btnGuardar4;
    private javax.swing.JButton btnGuardar5;
    private javax.swing.JButton btnGuardar6;
    private javax.swing.JButton btnOcultarImpresiones;
    private javax.swing.JButton btnOcultarImpresiones1;
    private javax.swing.JButton btnResolucion;
    private javax.swing.JRadioButton carta;
    private javax.swing.JComboBox cmbActoQuirurgico;
    private javax.swing.JComboBox cmbAmbito;
    private javax.swing.JComboBox cmbCausa;
    private javax.swing.JComboBox cmbCodigoConsulta;
    private javax.swing.JComboBox cmbComanda;
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
    private javax.swing.JComboBox cmbMisResponsabilidades;
    private javax.swing.JComboBox cmbPersonal;
    private javax.swing.JComboBox cmbPrefactura;
    private javax.swing.JComboBox cmbResponsabilidades;
    private javax.swing.JComboBox cmbTipoCarta;
    private javax.swing.JComboBox cmbTipoMediaCarta;
    private javax.swing.JComboBox cmbTipoPos;
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
    private javax.swing.JRadioButton horaNo;
    private javax.swing.JRadioButton horaSi;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JPanel jPanel44;
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
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JPopupMenu jPopupMenu3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lbAnexo;
    private javax.swing.JLabel lbAnexo1;
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
    private javax.swing.JLabel lbComanda;
    private javax.swing.JLabel lbMisResponsabilidades;
    private javax.swing.JLabel lbNit;
    private javax.swing.JLabel lbNit1;
    private javax.swing.JLabel lbNit11;
    private javax.swing.JLabel lbNit12;
    private javax.swing.JLabel lbNit13;
    private javax.swing.JLabel lbNit14;
    private javax.swing.JLabel lbNit2;
    private javax.swing.JLabel lbNit3;
    private javax.swing.JLabel lbNit4;
    private javax.swing.JLabel lbNit6;
    private javax.swing.JLabel lbNit7;
    private javax.swing.JLabel lbNit8;
    private javax.swing.JTextField lbOtroConsecutivo;
    private javax.swing.JLabel lbPrefactura;
    private javax.swing.JLabel lbRazon;
    private javax.swing.JLabel lbRazon1;
    private javax.swing.JLabel lbRazon11;
    private javax.swing.JLabel lbRazon13;
    private javax.swing.JLabel lbRazon14;
    private javax.swing.JLabel lbRazon15;
    private javax.swing.JLabel lbRazon19;
    private javax.swing.JLabel lbRazon2;
    private javax.swing.JLabel lbRazon23;
    private javax.swing.JLabel lbRazon25;
    private javax.swing.JLabel lbRazon28;
    private javax.swing.JLabel lbRazon4;
    private javax.swing.JLabel lbRazon5;
    private javax.swing.JLabel lbResponsabilidades;
    private javax.swing.JLabel lbTelefono1;
    private javax.swing.JLabel lbTelefono10;
    private javax.swing.JLabel lbTelefono11;
    private javax.swing.JLabel lbTelefono13;
    private javax.swing.JLabel lbTelefono14;
    private javax.swing.JLabel lbTelefono15;
    private javax.swing.JLabel lbTelefono16;
    private javax.swing.JLabel lbTelefono17;
    private javax.swing.JLabel lbTelefono18;
    private javax.swing.JLabel lbTelefono19;
    private javax.swing.JLabel lbTelefono2;
    private javax.swing.JLabel lbTelefono20;
    private javax.swing.JLabel lbTelefono21;
    private javax.swing.JLabel lbTelefono22;
    private javax.swing.JLabel lbTelefono23;
    private javax.swing.JLabel lbTelefono24;
    private javax.swing.JLabel lbTelefono25;
    private javax.swing.JLabel lbTelefono26;
    private javax.swing.JLabel lbTelefono27;
    private javax.swing.JLabel lbTelefono28;
    private javax.swing.JLabel lbTelefono29;
    private javax.swing.JLabel lbTelefono3;
    private javax.swing.JLabel lbTelefono30;
    private javax.swing.JLabel lbTelefono31;
    private javax.swing.JLabel lbTelefono32;
    private javax.swing.JLabel lbTelefono33;
    private javax.swing.JLabel lbTelefono35;
    private javax.swing.JLabel lbTelefono36;
    private javax.swing.JLabel lbTelefono37;
    private javax.swing.JLabel lbTelefono38;
    private javax.swing.JLabel lbTelefono39;
    private javax.swing.JLabel lbTelefono4;
    private javax.swing.JLabel lbTelefono40;
    private javax.swing.JLabel lbTelefono41;
    private javax.swing.JLabel lbTelefono42;
    private javax.swing.JLabel lbTelefono43;
    private javax.swing.JLabel lbTelefono44;
    private javax.swing.JLabel lbTelefono45;
    private javax.swing.JLabel lbTelefono46;
    private javax.swing.JLabel lbTelefono47;
    private javax.swing.JLabel lbTelefono48;
    private javax.swing.JLabel lbTelefono49;
    private javax.swing.JLabel lbTelefono5;
    private javax.swing.JLabel lbTelefono50;
    private javax.swing.JLabel lbTelefono51;
    private javax.swing.JLabel lbTelefono52;
    private javax.swing.JLabel lbTelefono53;
    private javax.swing.JLabel lbTelefono54;
    private javax.swing.JLabel lbTelefono55;
    private javax.swing.JLabel lbTelefono56;
    private javax.swing.JLabel lbTelefono57;
    private javax.swing.JLabel lbTelefono58;
    private javax.swing.JLabel lbTelefono59;
    private javax.swing.JLabel lbTelefono6;
    private javax.swing.JLabel lbTelefono60;
    private javax.swing.JLabel lbTelefono61;
    private javax.swing.JLabel lbTelefono62;
    private javax.swing.JLabel lbTelefono63;
    private javax.swing.JLabel lbTelefono64;
    private javax.swing.JLabel lbTelefono65;
    private javax.swing.JLabel lbTelefono66;
    private javax.swing.JLabel lbTelefono67;
    private javax.swing.JLabel lbTelefono68;
    private javax.swing.JLabel lbTelefono69;
    private javax.swing.JLabel lbTelefono7;
    private javax.swing.JLabel lbTelefono70;
    private javax.swing.JLabel lbTelefono71;
    private javax.swing.JLabel lbTelefono72;
    private javax.swing.JLabel lbTelefono73;
    private javax.swing.JLabel lbTelefono74;
    private javax.swing.JLabel lbTelefono75;
    private javax.swing.JLabel lbTelefono8;
    private javax.swing.JLabel lbTelefono9;
    private javax.swing.JRadioButton lectorNo;
    private javax.swing.JRadioButton lectorSi;
    private javax.swing.JRadioButton mediaCarta;
    private javax.swing.JRadioButton mostrarImpoconsumoNo;
    private javax.swing.JRadioButton mostrarImpoconsumoSi;
    private javax.swing.JRadioButton mostrarRetencionesNo;
    private javax.swing.JRadioButton mostrarRetencionesSi;
    private javax.swing.JPanel pnlAgenda;
    private javax.swing.JPanel pnlAgenda1;
    private javax.swing.JPanel pnlCredito;
    private javax.swing.JPanel pnlFormulario;
    private javax.swing.JPanel pnlFormulario1;
    private javax.swing.JPanel pnlImpresiones;
    private javax.swing.JPanel pnlImpresionesGrupo;
    private javax.swing.JPanel pnlInformacionMedico1;
    private javax.swing.JPanel pnlInformacionMedico2;
    private javax.swing.JPanel pnlOrdenMedica;
    private javax.swing.JPanel pnlRestaurante;
    private javax.swing.JMenuItem popBorrar;
    private javax.swing.JMenuItem popBorrar1;
    private javax.swing.JMenuItem popBorrar2;
    private javax.swing.JRadioButton pos;
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
    private javax.swing.JRadioButton rdCopiasNo;
    private javax.swing.JRadioButton rdCopiasSi;
    private javax.swing.JRadioButton rdImpresionPorGrupo;
    private javax.swing.JRadioButton rdNoBolsa;
    private javax.swing.JRadioButton rdNoBorrarMesas;
    private javax.swing.JRadioButton rdNoBorrarProdMesas;
    private javax.swing.JRadioButton rdNoCombinar;
    private javax.swing.JRadioButton rdNoConsecutivo;
    private javax.swing.JRadioButton rdNoCostoImpo;
    private javax.swing.JRadioButton rdNoCostoIva;
    private javax.swing.JRadioButton rdNoCupoCredito;
    private javax.swing.JRadioButton rdNoDevuelta;
    private javax.swing.JRadioButton rdNoDiasAutomaticos;
    private javax.swing.JRadioButton rdNoFacturarMesas;
    private javax.swing.JRadioButton rdNoFacturarSinInventario;
    private javax.swing.JRadioButton rdNoGenerarOrden;
    private javax.swing.JRadioButton rdNoImprimirCuadreFiscal;
    private javax.swing.JRadioButton rdNoImprimirFactura;
    private javax.swing.JRadioButton rdNoImprimirOrden;
    private javax.swing.JRadioButton rdNoMensajeUtilidad;
    private javax.swing.JRadioButton rdNoModificarNombre;
    private javax.swing.JRadioButton rdNoModificarPrecio;
    private javax.swing.JRadioButton rdNoPagoTerceros;
    private javax.swing.JRadioButton rdNoPondNegativo;
    private javax.swing.JRadioButton rdNoPrevisualizarComanda;
    private javax.swing.JRadioButton rdNoPrevisualizarPrefactura;
    private javax.swing.JRadioButton rdNoPvpImpo;
    private javax.swing.JRadioButton rdNoPvpIva;
    private javax.swing.JRadioButton rdNoSolicitudesPermisos;
    private javax.swing.JRadioButton rdNoSoloVisibleMesas;
    private javax.swing.JRadioButton rdNoVentasPredeterminado;
    private javax.swing.JRadioButton rdPreguntaCotizacionNO;
    private javax.swing.JRadioButton rdPreguntaCotizacionSI;
    private javax.swing.JRadioButton rdPreguntaFacturaNO;
    private javax.swing.JRadioButton rdPreguntaFacturaSI;
    private javax.swing.JRadioButton rdPreguntaOServicioNO;
    private javax.swing.JRadioButton rdPreguntaOServicioSI;
    private javax.swing.JRadioButton rdPreguntaPedidosNO;
    private javax.swing.JRadioButton rdPreguntaPedidosSI;
    private javax.swing.JRadioButton rdPreguntaPlanSepareNO;
    private javax.swing.JRadioButton rdPreguntaPlanSepareSI;
    private javax.swing.JRadioButton rdPrevisualizarCotizacionNO;
    private javax.swing.JRadioButton rdPrevisualizarCotizacionSI;
    private javax.swing.JRadioButton rdPrevisualizarFacturaNO;
    private javax.swing.JRadioButton rdPrevisualizarFacturaSI;
    private javax.swing.JRadioButton rdPrevisualizarOServicioNO;
    private javax.swing.JRadioButton rdPrevisualizarOServicioSI;
    private javax.swing.JRadioButton rdPrevisualizarPedidosNO;
    private javax.swing.JRadioButton rdPrevisualizarPedidosSI;
    private javax.swing.JRadioButton rdPrevisualizarPlanSepareNO;
    private javax.swing.JRadioButton rdPrevisualizarPlanSepareSI;
    private javax.swing.JRadioButton rdProcedimiento;
    private javax.swing.JRadioButton rdRedondearCantidades;
    private javax.swing.JRadioButton rdSiBolsa;
    private javax.swing.JRadioButton rdSiBorrarMesas;
    private javax.swing.JRadioButton rdSiBorrarProdMesas;
    private javax.swing.JRadioButton rdSiCombinar;
    private javax.swing.JRadioButton rdSiConsecutivo;
    private javax.swing.JRadioButton rdSiCostoImpo;
    private javax.swing.JRadioButton rdSiCostoIva;
    private javax.swing.JRadioButton rdSiCupoCredito;
    private javax.swing.JRadioButton rdSiDevuelta;
    private javax.swing.JRadioButton rdSiDiasAutomaticos;
    private javax.swing.JRadioButton rdSiFacturarMesas;
    private javax.swing.JRadioButton rdSiFacturarSinInventario;
    private javax.swing.JRadioButton rdSiGenerarOrden;
    private javax.swing.JRadioButton rdSiImprimirCuadreFiscal;
    private javax.swing.JRadioButton rdSiImprimirFactura;
    private javax.swing.JRadioButton rdSiImprimirOrden;
    private javax.swing.JRadioButton rdSiMensajeUtilidad;
    private javax.swing.JRadioButton rdSiModificarNombre;
    private javax.swing.JRadioButton rdSiModificarPrecio;
    private javax.swing.JRadioButton rdSiPagoTerceros;
    private javax.swing.JRadioButton rdSiPondNegativo;
    private javax.swing.JRadioButton rdSiPrevisualizarComanda;
    private javax.swing.JRadioButton rdSiPrevisualizarPrefactura;
    private javax.swing.JRadioButton rdSiPvpImpo;
    private javax.swing.JRadioButton rdSiPvpIva;
    private javax.swing.JRadioButton rdSiSolicitudesPermisos;
    private javax.swing.JRadioButton rdSiSoloVisibleMesas;
    private javax.swing.JRadioButton rdSiVentasPredeterminado;
    private javax.swing.JRadioButton rdTurnoNo;
    private javax.swing.JRadioButton rdTurnoSi;
    private javax.swing.JRadioButton recogidaNo;
    private javax.swing.JRadioButton recogidaSi;
    private javax.swing.JScrollPane scrFormulario;
    private javax.swing.JTabbedPane tabPanel;
    private javax.swing.JTable tblCascos;
    private javax.swing.JTable tblConfig;
    private javax.swing.JTable tblDiasBloqueo;
    private javax.swing.JTable tblExamenes;
    private javax.swing.JTable tblHoras;
    private javax.swing.JTable tblImpresoras;
    private javax.swing.JTable tblImpresoras1;
    private javax.swing.JTable tblMesas;
    private javax.swing.JTable tblRestaurante;
    private javax.swing.JTable tblTarjetas;
    private javax.swing.JTextArea txtAnexoFactura;
    private javax.swing.JTextArea txtAnexoOrden;
    private javax.swing.JTextField txtCantCopiasComanda;
    private javax.swing.JTextField txtCantCopiasPrefactura;
    private javax.swing.JTextField txtCantCotizacion;
    private javax.swing.JTextField txtCantFactura;
    private javax.swing.JTextField txtCantOServicio;
    private javax.swing.JTextField txtCantPedidos;
    private javax.swing.JTextField txtCantPlanSepare;
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
    private javax.swing.JTextField txtDiasAlerta1;
    private javax.swing.JTextField txtDiasCobrarMora;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtFilas;
    private javax.swing.JTextField txtFilas1;
    private javax.swing.JTextField txtFranquisia;
    private javax.swing.JTextField txtIdProd;
    private javax.swing.JTextField txtL1;
    private javax.swing.JTextArea txtLegal;
    private javax.swing.JTextField txtLimite;
    private javax.swing.JTextField txtNit;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNombreFormato;
    private javax.swing.JTextField txtNumFactIncremento;
    private javax.swing.JLabel txtNumMesas;
    private javax.swing.JTextField txtNumeroFactosElectronicas;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextArea txtPiePagina;
    private javax.swing.JTextField txtPorcPropina;
    private javax.swing.JTextField txtPorcentajeMora;
    private javax.swing.JTextField txtRegimen;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtTituloFactura;
    private javax.swing.JTextField txtTurno;
    private javax.swing.JTextField txtValor;
    private javax.swing.JTextField txtValor1;
    private javax.swing.JTextField txtaCobrar;
    private javax.swing.JTextField txtaCobrar1;
    private javax.swing.JRadioButton ubicacionNo;
    private javax.swing.JRadioButton ubicacionSi;
    private javax.swing.JRadioButton utilidadNo;
    private javax.swing.JRadioButton utilidadSi;
    // End of variables declaration//GEN-END:variables
}
