package formularios;

import clases.ImagePreviewPanel;
import clases.Instancias;
import clases.metodosGenerales;
import clases.ndUsuario;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class infUsuarios extends javax.swing.JInternalFrame {

    metodosGenerales metodos = new metodosGenerales();
    private Instancias instancias;
    private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension dimBarra = null;
    String origen = "", destino = "";

    public infUsuarios() {
        initComponents();

        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
        dimBarra = Barra.getPreferredSize();
        Barra.setSize(0, 0);
        Barra.setPreferredSize(new Dimension(0, 0));
        setBorder(null);
        repaint();

        lbFoto.setIcon(null);
        btnNuevoActionPerformed(null);

        instancias = Instancias.getInstancias();
//        if (instancias.getHistoriaClinica() != null) {
//            jPanel8.setVisible(false);
//        }

        if (!instancias.getConfiguraciones().isAgenda()) {
            grupo.setEnabledAt(5, false);
        }

        if (!instancias.getConfiguraciones().isMedico()) {
            grupo.setEnabledAt(6, false);
        }

        if (!instancias.getConfiguraciones().isVeterinaria()) {
            grupo.setEnabledAt(7, false);
        }

        jPanel8.setVisible(false);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoFacturas = new javax.swing.ButtonGroup();
        grupoCongeladas = new javax.swing.ButtonGroup();
        grupoCotizaciones = new javax.swing.ButtonGroup();
        grupoPlanSepares = new javax.swing.ButtonGroup();
        grupoCreditos = new javax.swing.ButtonGroup();
        grupoPedidos = new javax.swing.ButtonGroup();
        grupoOrdenesServicio = new javax.swing.ButtonGroup();
        grupoAnulaciones = new javax.swing.ButtonGroup();
        grupoReimpresiones = new javax.swing.ButtonGroup();
        grupoReporteVentas = new javax.swing.ButtonGroup();
        grupoReporteVentasAnuladas = new javax.swing.ButtonGroup();
        grupoReporteCotizaciones = new javax.swing.ButtonGroup();
        grupoReportePlanSepares = new javax.swing.ButtonGroup();
        grupoReporteCreditos = new javax.swing.ButtonGroup();
        grupoReporteOrdenesServicio = new javax.swing.ButtonGroup();
        grupoReporteIvasVentas = new javax.swing.ButtonGroup();
        grupoReporteDia = new javax.swing.ButtonGroup();
        grupoCreacionProductos = new javax.swing.ButtonGroup();
        grupoOrdenCompras = new javax.swing.ButtonGroup();
        grupoCompras = new javax.swing.ButtonGroup();
        grupoTrasladoExterno = new javax.swing.ButtonGroup();
        grupoTrasladoInterno = new javax.swing.ButtonGroup();
        grupoAjusteEntrada = new javax.swing.ButtonGroup();
        grupoAjusteSalida = new javax.swing.ButtonGroup();
        grupoGrupos = new javax.swing.ButtonGroup();
        grupoDiseños = new javax.swing.ButtonGroup();
        grupoArmados = new javax.swing.ButtonGroup();
        grupoInventarioInicial = new javax.swing.ButtonGroup();
        grupoTrasladoDetalle = new javax.swing.ButtonGroup();
        grupoKardex = new javax.swing.ButtonGroup();
        grupoReporteProductos = new javax.swing.ButtonGroup();
        grupoReportePrestamos = new javax.swing.ButtonGroup();
        grupoReporteCompras = new javax.swing.ButtonGroup();
        grupoReporteAjustes = new javax.swing.ButtonGroup();
        grupoReporteOrdenCompras = new javax.swing.ButtonGroup();
        grupoReporteInventario = new javax.swing.ButtonGroup();
        grupoReporteTrasladosInterno = new javax.swing.ButtonGroup();
        grupoReporteIvasCompras = new javax.swing.ButtonGroup();
        grupoReporteMovimientosSerial = new javax.swing.ButtonGroup();
        grupoCrearClientes = new javax.swing.ButtonGroup();
        grupoCrearProveedor = new javax.swing.ButtonGroup();
        grupoCrearEmpleado = new javax.swing.ButtonGroup();
        grupoBodegasInternas = new javax.swing.ButtonGroup();
        grupoClientesPrestamos = new javax.swing.ButtonGroup();
        grupoShowRoom = new javax.swing.ButtonGroup();
        grupoReporteCliente = new javax.swing.ButtonGroup();
        grupoReporteProveedor = new javax.swing.ButtonGroup();
        grupoReporteEmpleados = new javax.swing.ButtonGroup();
        grupoAbonosCuentasxCobrar = new javax.swing.ButtonGroup();
        grupoAbonosCuentasxPagar = new javax.swing.ButtonGroup();
        grupoNotasCredito = new javax.swing.ButtonGroup();
        grupoReporteCarteraCxC = new javax.swing.ButtonGroup();
        grupoReporteAbonosCarteraCxC = new javax.swing.ButtonGroup();
        grupoReporteCarteraCxP = new javax.swing.ButtonGroup();
        grupoReporteAbonosCxP = new javax.swing.ButtonGroup();
        grupoReporteNotasCreditos = new javax.swing.ButtonGroup();
        grupoCodigos = new javax.swing.ButtonGroup();
        grupoEgresos = new javax.swing.ButtonGroup();
        grupoBancos = new javax.swing.ButtonGroup();
        grupoCuadreCaja = new javax.swing.ButtonGroup();
        grupoReporteEgresos = new javax.swing.ButtonGroup();
        grupoReporteBancos = new javax.swing.ButtonGroup();
        grupoReporteCuadreCaja = new javax.swing.ButtonGroup();
        grupoRegistrarAgenda = new javax.swing.ButtonGroup();
        grupoReporteAgenda = new javax.swing.ButtonGroup();
        grupoMascotas = new javax.swing.ButtonGroup();
        grupoHistoriaClinica = new javax.swing.ButtonGroup();
        grupoGuarderia = new javax.swing.ButtonGroup();
        grupoPeluqueria = new javax.swing.ButtonGroup();
        grupoHospitalizacion = new javax.swing.ButtonGroup();
        grupoAlertas = new javax.swing.ButtonGroup();
        grupoReporteMascotas = new javax.swing.ButtonGroup();
        grupoReporteGuarderia = new javax.swing.ButtonGroup();
        grupoReportePeluqueria = new javax.swing.ButtonGroup();
        grupoReporteHospitalizacion = new javax.swing.ButtonGroup();
        grupoFormatos = new javax.swing.ButtonGroup();
        grupoOrdenServicioMedica = new javax.swing.ButtonGroup();
        grupoHistoriaClinicaMedica = new javax.swing.ButtonGroup();
        grupoFormatosMedica = new javax.swing.ButtonGroup();
        grupoConvenio = new javax.swing.ButtonGroup();
        grupoNotaEnfermeria = new javax.swing.ButtonGroup();
        grupoMedicamentos = new javax.swing.ButtonGroup();
        grupoReporteOrdenesMedica = new javax.swing.ButtonGroup();
        grupoReporteFormulas = new javax.swing.ButtonGroup();
        grupoCertificadoEstudiante = new javax.swing.ButtonGroup();
        grupoCertificadoMedico = new javax.swing.ButtonGroup();
        grupoCertificadoOyS = new javax.swing.ButtonGroup();
        jPanel10 = new javax.swing.JPanel();
        grupo = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        siFacturas = new javax.swing.JRadioButton();
        noFacturas = new javax.swing.JRadioButton();
        siCongeladas = new javax.swing.JRadioButton();
        noCongeladas = new javax.swing.JRadioButton();
        siCotizaciones = new javax.swing.JRadioButton();
        noCotizaciones = new javax.swing.JRadioButton();
        siPlanSepares = new javax.swing.JRadioButton();
        noPlanSepares = new javax.swing.JRadioButton();
        siCreditos = new javax.swing.JRadioButton();
        noCreditos = new javax.swing.JRadioButton();
        siPedidos = new javax.swing.JRadioButton();
        noPedidos = new javax.swing.JRadioButton();
        siOrdenServicio = new javax.swing.JRadioButton();
        noOrdenServicio = new javax.swing.JRadioButton();
        siAnulaciones = new javax.swing.JRadioButton();
        noAnulaciones = new javax.swing.JRadioButton();
        siReimpresion = new javax.swing.JRadioButton();
        noReimpresion = new javax.swing.JRadioButton();
        jPanel13 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        siReporteVentas = new javax.swing.JRadioButton();
        noReporteVentas = new javax.swing.JRadioButton();
        siReporteVentasAnuladas = new javax.swing.JRadioButton();
        noReporteVentasAnuladas = new javax.swing.JRadioButton();
        siReporteCotizaciones = new javax.swing.JRadioButton();
        noReporteCotizaciones = new javax.swing.JRadioButton();
        siReportePlanSepares = new javax.swing.JRadioButton();
        noReportePlanSepares = new javax.swing.JRadioButton();
        siReporteCreditos = new javax.swing.JRadioButton();
        noReporteCreditos = new javax.swing.JRadioButton();
        siReporteOrdenServicio = new javax.swing.JRadioButton();
        noReporteOrdenServicio = new javax.swing.JRadioButton();
        siReporteIvasVentas = new javax.swing.JRadioButton();
        noReporteIvasVentas = new javax.swing.JRadioButton();
        siReporteDia = new javax.swing.JRadioButton();
        noReporteDia = new javax.swing.JRadioButton();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel3 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        siCrearProductos = new javax.swing.JRadioButton();
        noCrearProductos = new javax.swing.JRadioButton();
        siOrdenCompra = new javax.swing.JRadioButton();
        noOrdenCompra = new javax.swing.JRadioButton();
        siCompras = new javax.swing.JRadioButton();
        noCompras = new javax.swing.JRadioButton();
        siTrasladoExterno = new javax.swing.JRadioButton();
        noTrasladoExterno = new javax.swing.JRadioButton();
        siTrasladoInterno = new javax.swing.JRadioButton();
        noTrasladoInterno = new javax.swing.JRadioButton();
        siAjusteSalida = new javax.swing.JRadioButton();
        noAjusteSalida = new javax.swing.JRadioButton();
        siGrupos = new javax.swing.JRadioButton();
        noGrupos = new javax.swing.JRadioButton();
        siDiseños = new javax.swing.JRadioButton();
        noDiseños = new javax.swing.JRadioButton();
        siArmados = new javax.swing.JRadioButton();
        noArmados = new javax.swing.JRadioButton();
        siInventarioInicial = new javax.swing.JRadioButton();
        noInventarioInicial = new javax.swing.JRadioButton();
        siTrasladoDetalle = new javax.swing.JRadioButton();
        noTrasladoDetalle = new javax.swing.JRadioButton();
        siKardex = new javax.swing.JRadioButton();
        noKardex = new javax.swing.JRadioButton();
        siAjusteEntrada = new javax.swing.JRadioButton();
        noAjusteEntrada = new javax.swing.JRadioButton();
        jPanel17 = new javax.swing.JPanel();
        jLabel63 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        siReporteProductos = new javax.swing.JRadioButton();
        noReporteProductos = new javax.swing.JRadioButton();
        siReportePrestamos = new javax.swing.JRadioButton();
        noReportePrestamos = new javax.swing.JRadioButton();
        siReporteCompras = new javax.swing.JRadioButton();
        noReporteCompras = new javax.swing.JRadioButton();
        siReporteAjustes = new javax.swing.JRadioButton();
        noReporteAjustes = new javax.swing.JRadioButton();
        noReporteMovimientosSerial = new javax.swing.JRadioButton();
        siReporteMovimientosSerial = new javax.swing.JRadioButton();
        siReporteIvasCompras = new javax.swing.JRadioButton();
        noReporteIvasCompras = new javax.swing.JRadioButton();
        siReporteTrasladoInterno = new javax.swing.JRadioButton();
        noReporteTrasladoInterno = new javax.swing.JRadioButton();
        siReporteInventario = new javax.swing.JRadioButton();
        noReporteInventario = new javax.swing.JRadioButton();
        siReporteOrdenCompra = new javax.swing.JRadioButton();
        noReporteOrdenCompra = new javax.swing.JRadioButton();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        siCrearCliente = new javax.swing.JRadioButton();
        noCrearCliente = new javax.swing.JRadioButton();
        siCrearProveedor = new javax.swing.JRadioButton();
        noCrearProveedor = new javax.swing.JRadioButton();
        siCrearEmpleado = new javax.swing.JRadioButton();
        noCrearEmpleado = new javax.swing.JRadioButton();
        siBodegaInterna = new javax.swing.JRadioButton();
        noBodegaInterna = new javax.swing.JRadioButton();
        siClientesPrestamos = new javax.swing.JRadioButton();
        noClientesPrestamos = new javax.swing.JRadioButton();
        siShowRoom = new javax.swing.JRadioButton();
        noShowRoom = new javax.swing.JRadioButton();
        jPanel21 = new javax.swing.JPanel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        siReporteCliente = new javax.swing.JRadioButton();
        noReporteCliente = new javax.swing.JRadioButton();
        siReporteProveedor = new javax.swing.JRadioButton();
        noReporteProveedor = new javax.swing.JRadioButton();
        siReporteEmpleado = new javax.swing.JRadioButton();
        noReporteEmpleado = new javax.swing.JRadioButton();
        jSeparator3 = new javax.swing.JSeparator();
        jPanel5 = new javax.swing.JPanel();
        jLabel56 = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        jLabel53 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        siAbonosCxc = new javax.swing.JRadioButton();
        noAbonosCxc = new javax.swing.JRadioButton();
        siAbonosCxP = new javax.swing.JRadioButton();
        noAbonosCxP = new javax.swing.JRadioButton();
        siNotasCredito = new javax.swing.JRadioButton();
        noNotasCredito = new javax.swing.JRadioButton();
        jPanel25 = new javax.swing.JPanel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        jPanel26 = new javax.swing.JPanel();
        siReporteCarteraCxC = new javax.swing.JRadioButton();
        noReporteCarteraCxC = new javax.swing.JRadioButton();
        siReporteAbonosCxC = new javax.swing.JRadioButton();
        noReporteAbonosCxC = new javax.swing.JRadioButton();
        siReporteCarteraCxP = new javax.swing.JRadioButton();
        noReporteCarteraCxP = new javax.swing.JRadioButton();
        siReporteAbonosCxP = new javax.swing.JRadioButton();
        noReporteAbonosCxP = new javax.swing.JRadioButton();
        siReporteNotasCredito = new javax.swing.JRadioButton();
        noReporteNotasCredito = new javax.swing.JRadioButton();
        jSeparator4 = new javax.swing.JSeparator();
        jPanel6 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        jPanel28 = new javax.swing.JPanel();
        siCodigos = new javax.swing.JRadioButton();
        noCodigos = new javax.swing.JRadioButton();
        siEgresos = new javax.swing.JRadioButton();
        noEgresos = new javax.swing.JRadioButton();
        siBancos = new javax.swing.JRadioButton();
        noBancos = new javax.swing.JRadioButton();
        siCuadreCaja = new javax.swing.JRadioButton();
        noCuadreCaja = new javax.swing.JRadioButton();
        jPanel29 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        jPanel30 = new javax.swing.JPanel();
        siReporteEgresos = new javax.swing.JRadioButton();
        noReporteEgresos = new javax.swing.JRadioButton();
        siReporteBancos = new javax.swing.JRadioButton();
        noReporteBancos = new javax.swing.JRadioButton();
        siReporteCuadreCaja = new javax.swing.JRadioButton();
        noReporteCuadreCaja = new javax.swing.JRadioButton();
        jSeparator5 = new javax.swing.JSeparator();
        jPanel9 = new javax.swing.JPanel();
        jLabel68 = new javax.swing.JLabel();
        jPanel31 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jPanel32 = new javax.swing.JPanel();
        siRegistrarAgenda = new javax.swing.JRadioButton();
        noRegistrarAgenda = new javax.swing.JRadioButton();
        jPanel33 = new javax.swing.JPanel();
        siReporteAgenda = new javax.swing.JRadioButton();
        noReporteAgenda = new javax.swing.JRadioButton();
        jPanel34 = new javax.swing.JPanel();
        jLabel67 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jPanel8 = new javax.swing.JPanel();
        jLabel48 = new javax.swing.JLabel();
        jPanel39 = new javax.swing.JPanel();
        siOrdenServicioMedica = new javax.swing.JRadioButton();
        noOrdenServicioMedica = new javax.swing.JRadioButton();
        siHistoriaClinicaMedica = new javax.swing.JRadioButton();
        noHistoriaClinicaMedica = new javax.swing.JRadioButton();
        siFormatos = new javax.swing.JRadioButton();
        noFormatos = new javax.swing.JRadioButton();
        siConvenio = new javax.swing.JRadioButton();
        noConvenio = new javax.swing.JRadioButton();
        siNotaEnfermeria = new javax.swing.JRadioButton();
        noNotaEnfermeria = new javax.swing.JRadioButton();
        siMedicamentos = new javax.swing.JRadioButton();
        noMedicamentos = new javax.swing.JRadioButton();
        jPanel40 = new javax.swing.JPanel();
        jLabel95 = new javax.swing.JLabel();
        jLabel97 = new javax.swing.JLabel();
        jLabel102 = new javax.swing.JLabel();
        jLabel103 = new javax.swing.JLabel();
        jLabel105 = new javax.swing.JLabel();
        jLabel106 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        jPanel41 = new javax.swing.JPanel();
        jLabel96 = new javax.swing.JLabel();
        jLabel107 = new javax.swing.JLabel();
        jLabel108 = new javax.swing.JLabel();
        jLabel109 = new javax.swing.JLabel();
        jLabel110 = new javax.swing.JLabel();
        jPanel42 = new javax.swing.JPanel();
        siReportesOrdenesMedicas = new javax.swing.JRadioButton();
        noReportesOrdenesMedicas = new javax.swing.JRadioButton();
        siReporteFormulas = new javax.swing.JRadioButton();
        noReporteFormulas = new javax.swing.JRadioButton();
        siCertificadoEstudiante = new javax.swing.JRadioButton();
        noCertificadoEstudiante = new javax.swing.JRadioButton();
        siCertificadoMedico = new javax.swing.JRadioButton();
        noCertificadoMedico = new javax.swing.JRadioButton();
        siCertificadoOyS = new javax.swing.JRadioButton();
        noCertificadoOyS = new javax.swing.JRadioButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel66 = new javax.swing.JLabel();
        jPanel35 = new javax.swing.JPanel();
        jLabel89 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        jLabel93 = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        jPanel36 = new javax.swing.JPanel();
        siMascotas = new javax.swing.JRadioButton();
        noMascotas = new javax.swing.JRadioButton();
        siHClinica = new javax.swing.JRadioButton();
        noHClinica = new javax.swing.JRadioButton();
        siGuarderia = new javax.swing.JRadioButton();
        noGuarderia = new javax.swing.JRadioButton();
        siPeluqueria = new javax.swing.JRadioButton();
        noPeluqueria = new javax.swing.JRadioButton();
        siHospitalizacion = new javax.swing.JRadioButton();
        noHospitalizacion = new javax.swing.JRadioButton();
        siAlertas = new javax.swing.JRadioButton();
        noAlertas = new javax.swing.JRadioButton();
        jPanel37 = new javax.swing.JPanel();
        jLabel98 = new javax.swing.JLabel();
        jLabel99 = new javax.swing.JLabel();
        jLabel100 = new javax.swing.JLabel();
        jLabel101 = new javax.swing.JLabel();
        jLabel104 = new javax.swing.JLabel();
        jPanel38 = new javax.swing.JPanel();
        siReporteMascotas = new javax.swing.JRadioButton();
        noReporteMascotas = new javax.swing.JRadioButton();
        siReporteGuarderia = new javax.swing.JRadioButton();
        noReporteGuarderia = new javax.swing.JRadioButton();
        siReportePeluqueria = new javax.swing.JRadioButton();
        noReportePeluqueria = new javax.swing.JRadioButton();
        siReporteHospitalizacion = new javax.swing.JRadioButton();
        noReporteHospitalizacion = new javax.swing.JRadioButton();
        siReporteFormatos = new javax.swing.JRadioButton();
        noReporteFormatos = new javax.swing.JRadioButton();
        jSeparator7 = new javax.swing.JSeparator();
        pnlUsuario = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
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
        btnOcultarInfoCliente = new javax.swing.JButton();

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        grupo.setBackground(new java.awt.Color(255, 255, 255));
        grupo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        grupo.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        grupo.setOpaque(true);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel12.setBackground(new java.awt.Color(204, 204, 204));
        jLabel12.setFont(new java.awt.Font("Century Gothic", 1, 30)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Ventas");
        jLabel12.setOpaque(true);

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

        jLabel8.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel8.setText("Facturas");

        jLabel18.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel18.setText("Congeladas");

        jLabel20.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel20.setText("Cotizaciones");

        jLabel22.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel22.setText("Plan separes");

        jLabel50.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel50.setText("Creditos");

        jLabel60.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel60.setText("Pedidos");

        jLabel61.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel61.setText("Ordenes de servicio ");

        jLabel62.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel62.setText("Anulaciones");

        jLabel24.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel24.setText("Reimpresión");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel62, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel61, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel60, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel50, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));

        siFacturas.setBackground(new java.awt.Color(255, 255, 255));
        grupoFacturas.add(siFacturas);
        siFacturas.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        siFacturas.setText("Si");
        siFacturas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siFacturasActionPerformed(evt);
            }
        });

        noFacturas.setBackground(new java.awt.Color(255, 255, 255));
        grupoFacturas.add(noFacturas);
        noFacturas.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        noFacturas.setText("No");
        noFacturas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noFacturasActionPerformed(evt);
            }
        });

        siCongeladas.setBackground(new java.awt.Color(255, 255, 255));
        grupoCongeladas.add(siCongeladas);
        siCongeladas.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        siCongeladas.setText("Si");
        siCongeladas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siCongeladasActionPerformed(evt);
            }
        });

        noCongeladas.setBackground(new java.awt.Color(255, 255, 255));
        grupoCongeladas.add(noCongeladas);
        noCongeladas.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        noCongeladas.setText("No");
        noCongeladas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noCongeladasActionPerformed(evt);
            }
        });

        siCotizaciones.setBackground(new java.awt.Color(255, 255, 255));
        grupoCotizaciones.add(siCotizaciones);
        siCotizaciones.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        siCotizaciones.setText("Si");
        siCotizaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siCotizacionesActionPerformed(evt);
            }
        });

        noCotizaciones.setBackground(new java.awt.Color(255, 255, 255));
        grupoCotizaciones.add(noCotizaciones);
        noCotizaciones.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        noCotizaciones.setText("No");
        noCotizaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noCotizacionesActionPerformed(evt);
            }
        });

        siPlanSepares.setBackground(new java.awt.Color(255, 255, 255));
        grupoPlanSepares.add(siPlanSepares);
        siPlanSepares.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        siPlanSepares.setText("Si");
        siPlanSepares.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siPlanSeparesActionPerformed(evt);
            }
        });

        noPlanSepares.setBackground(new java.awt.Color(255, 255, 255));
        grupoPlanSepares.add(noPlanSepares);
        noPlanSepares.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        noPlanSepares.setText("No");
        noPlanSepares.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noPlanSeparesActionPerformed(evt);
            }
        });

        siCreditos.setBackground(new java.awt.Color(255, 255, 255));
        grupoCreditos.add(siCreditos);
        siCreditos.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        siCreditos.setText("Si");
        siCreditos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siCreditosActionPerformed(evt);
            }
        });

        noCreditos.setBackground(new java.awt.Color(255, 255, 255));
        grupoCreditos.add(noCreditos);
        noCreditos.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        noCreditos.setText("No");
        noCreditos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noCreditosActionPerformed(evt);
            }
        });

        siPedidos.setBackground(new java.awt.Color(255, 255, 255));
        grupoPedidos.add(siPedidos);
        siPedidos.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        siPedidos.setText("Si");
        siPedidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siPedidosActionPerformed(evt);
            }
        });

        noPedidos.setBackground(new java.awt.Color(255, 255, 255));
        grupoPedidos.add(noPedidos);
        noPedidos.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        noPedidos.setText("No");
        noPedidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noPedidosActionPerformed(evt);
            }
        });

        siOrdenServicio.setBackground(new java.awt.Color(255, 255, 255));
        grupoOrdenesServicio.add(siOrdenServicio);
        siOrdenServicio.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        siOrdenServicio.setText("Si");
        siOrdenServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siOrdenServicioActionPerformed(evt);
            }
        });

        noOrdenServicio.setBackground(new java.awt.Color(255, 255, 255));
        grupoOrdenesServicio.add(noOrdenServicio);
        noOrdenServicio.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        noOrdenServicio.setText("No");
        noOrdenServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noOrdenServicioActionPerformed(evt);
            }
        });

        siAnulaciones.setBackground(new java.awt.Color(255, 255, 255));
        grupoAnulaciones.add(siAnulaciones);
        siAnulaciones.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        siAnulaciones.setText("Si");
        siAnulaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siAnulacionesActionPerformed(evt);
            }
        });

        noAnulaciones.setBackground(new java.awt.Color(255, 255, 255));
        grupoAnulaciones.add(noAnulaciones);
        noAnulaciones.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        noAnulaciones.setText("No");
        noAnulaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noAnulacionesActionPerformed(evt);
            }
        });

        siReimpresion.setBackground(new java.awt.Color(255, 255, 255));
        grupoReimpresiones.add(siReimpresion);
        siReimpresion.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        siReimpresion.setText("Si");
        siReimpresion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siReimpresionActionPerformed(evt);
            }
        });

        noReimpresion.setBackground(new java.awt.Color(255, 255, 255));
        grupoReimpresiones.add(noReimpresion);
        noReimpresion.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        noReimpresion.setText("No");
        noReimpresion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noReimpresionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(siReimpresion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(siOrdenServicio, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(siFacturas, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                            .addComponent(siCotizaciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(siCongeladas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(siCreditos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(siPedidos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(siPlanSepares, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(siAnulaciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(noAnulaciones, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                    .addComponent(noOrdenServicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(noPlanSepares, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(noCongeladas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(noFacturas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(noCotizaciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(noCreditos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(noPedidos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(noReimpresion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(siFacturas, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(noFacturas, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(siCongeladas, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(noCongeladas, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(noCotizaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(siCotizaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(siPlanSepares, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(noPlanSepares, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(noCreditos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(siCreditos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(siPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(noPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(siOrdenServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(noOrdenServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(siAnulaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(noAnulaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(siReimpresion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(noReimpresion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));

        jLabel17.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel17.setText("Reporte de ventas");

        jLabel21.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel21.setText("Reporte de ventas anuladas");

        jLabel23.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel23.setText("Reporte de cotizaciones");

        jLabel59.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel59.setText("Reporte de plan separes");

        jLabel70.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel70.setText("Reporte de ordenes servicio");

        jLabel71.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel71.setText("Reporte de ivas ventas");

        jLabel69.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel69.setText("Reporte de creditos");

        jLabel72.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel72.setText("Reporte del día");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel59, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel69, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel70, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel71, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 5, Short.MAX_VALUE))
                    .addComponent(jLabel72, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));

        siReporteVentas.setBackground(new java.awt.Color(255, 255, 255));
        grupoReporteVentas.add(siReporteVentas);
        siReporteVentas.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        siReporteVentas.setText("Si");
        siReporteVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siReporteVentasActionPerformed(evt);
            }
        });

        noReporteVentas.setBackground(new java.awt.Color(255, 255, 255));
        grupoReporteVentas.add(noReporteVentas);
        noReporteVentas.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        noReporteVentas.setText("No");
        noReporteVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noReporteVentasActionPerformed(evt);
            }
        });

        siReporteVentasAnuladas.setBackground(new java.awt.Color(255, 255, 255));
        grupoReporteVentasAnuladas.add(siReporteVentasAnuladas);
        siReporteVentasAnuladas.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        siReporteVentasAnuladas.setText("Si");
        siReporteVentasAnuladas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siReporteVentasAnuladasActionPerformed(evt);
            }
        });

        noReporteVentasAnuladas.setBackground(new java.awt.Color(255, 255, 255));
        grupoReporteVentasAnuladas.add(noReporteVentasAnuladas);
        noReporteVentasAnuladas.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        noReporteVentasAnuladas.setText("No");
        noReporteVentasAnuladas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noReporteVentasAnuladasActionPerformed(evt);
            }
        });

        siReporteCotizaciones.setBackground(new java.awt.Color(255, 255, 255));
        grupoReporteCotizaciones.add(siReporteCotizaciones);
        siReporteCotizaciones.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        siReporteCotizaciones.setText("Si");
        siReporteCotizaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siReporteCotizacionesActionPerformed(evt);
            }
        });

        noReporteCotizaciones.setBackground(new java.awt.Color(255, 255, 255));
        grupoReporteCotizaciones.add(noReporteCotizaciones);
        noReporteCotizaciones.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        noReporteCotizaciones.setText("No");
        noReporteCotizaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noReporteCotizacionesActionPerformed(evt);
            }
        });

        siReportePlanSepares.setBackground(new java.awt.Color(255, 255, 255));
        grupoReportePlanSepares.add(siReportePlanSepares);
        siReportePlanSepares.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        siReportePlanSepares.setText("Si");
        siReportePlanSepares.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siReportePlanSeparesActionPerformed(evt);
            }
        });

        noReportePlanSepares.setBackground(new java.awt.Color(255, 255, 255));
        grupoReportePlanSepares.add(noReportePlanSepares);
        noReportePlanSepares.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        noReportePlanSepares.setText("No");
        noReportePlanSepares.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noReportePlanSeparesActionPerformed(evt);
            }
        });

        siReporteCreditos.setBackground(new java.awt.Color(255, 255, 255));
        grupoReporteCreditos.add(siReporteCreditos);
        siReporteCreditos.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        siReporteCreditos.setText("Si");
        siReporteCreditos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siReporteCreditosActionPerformed(evt);
            }
        });

        noReporteCreditos.setBackground(new java.awt.Color(255, 255, 255));
        grupoReporteCreditos.add(noReporteCreditos);
        noReporteCreditos.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        noReporteCreditos.setText("No");
        noReporteCreditos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noReporteCreditosActionPerformed(evt);
            }
        });

        siReporteOrdenServicio.setBackground(new java.awt.Color(255, 255, 255));
        grupoReporteOrdenesServicio.add(siReporteOrdenServicio);
        siReporteOrdenServicio.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        siReporteOrdenServicio.setText("Si");
        siReporteOrdenServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siReporteOrdenServicioActionPerformed(evt);
            }
        });

        noReporteOrdenServicio.setBackground(new java.awt.Color(255, 255, 255));
        grupoReporteOrdenesServicio.add(noReporteOrdenServicio);
        noReporteOrdenServicio.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        noReporteOrdenServicio.setText("No");
        noReporteOrdenServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noReporteOrdenServicioActionPerformed(evt);
            }
        });

        siReporteIvasVentas.setBackground(new java.awt.Color(255, 255, 255));
        grupoReporteIvasVentas.add(siReporteIvasVentas);
        siReporteIvasVentas.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        siReporteIvasVentas.setText("Si");
        siReporteIvasVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siReporteIvasVentasActionPerformed(evt);
            }
        });

        noReporteIvasVentas.setBackground(new java.awt.Color(255, 255, 255));
        grupoReporteIvasVentas.add(noReporteIvasVentas);
        noReporteIvasVentas.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        noReporteIvasVentas.setText("No");
        noReporteIvasVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noReporteIvasVentasActionPerformed(evt);
            }
        });

        siReporteDia.setBackground(new java.awt.Color(255, 255, 255));
        grupoReporteDia.add(siReporteDia);
        siReporteDia.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        siReporteDia.setText("Si");
        siReporteDia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siReporteDiaActionPerformed(evt);
            }
        });

        noReporteDia.setBackground(new java.awt.Color(255, 255, 255));
        grupoReporteDia.add(noReporteDia);
        noReporteDia.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        noReporteDia.setText("No");
        noReporteDia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noReporteDiaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(siReporteCotizaciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(siReportePlanSepares, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(siReporteCreditos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(siReporteOrdenServicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(siReporteIvasVentas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(siReporteDia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(siReporteVentasAnuladas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(siReporteVentas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 4, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(noReporteDia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(noReporteIvasVentas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(noReporteOrdenServicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(noReporteCreditos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(noReporteVentasAnuladas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(noReporteVentas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(noReporteCotizaciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(noReportePlanSepares, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(siReporteVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(noReporteVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(siReporteVentasAnuladas, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(noReporteVentasAnuladas, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(siReporteCotizaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(noReporteCotizaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(noReportePlanSepares, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(siReportePlanSepares, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(noReporteCreditos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(siReporteCreditos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(noReporteOrdenServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(siReporteOrdenServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(noReporteIvasVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(siReporteIvasVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(noReporteDia, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(siReporteDia, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel12)
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1))
                .addContainerGap(157, Short.MAX_VALUE))
        );

        grupo.addTab("Ventas", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel13.setBackground(new java.awt.Color(204, 204, 204));
        jLabel13.setFont(new java.awt.Font("Century Gothic", 1, 30)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Productos");
        jLabel13.setOpaque(true);

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));

        jLabel9.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel9.setText("Creación Productos");

        jLabel30.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel30.setText("Orden de Compras");

        jLabel28.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel28.setText("Compras");

        jLabel49.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel49.setText("Traslado Externo");

        jLabel43.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel43.setText("Traslado Interno");

        jLabel29.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel29.setText("Ajustes");

        jLabel40.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel40.setText("Grupos");

        jLabel73.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel73.setText("Diseños");

        jLabel74.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel74.setText("Armados");

        jLabel27.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel27.setText("Inventario Inicial");

        jLabel31.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel31.setText("Traslado detalle");

        jLabel32.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel32.setText("Kardex");

        jLabel87.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel87.setText("   - A. Entrada");

        jLabel88.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel88.setText("   - A. Salida");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel87, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel73, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel74, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel43, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                            .addComponent(jLabel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel88, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel9)
                .addGap(7, 7, 7)
                .addComponent(jLabel30)
                .addGap(7, 7, 7)
                .addComponent(jLabel28)
                .addGap(7, 7, 7)
                .addComponent(jLabel49)
                .addGap(7, 7, 7)
                .addComponent(jLabel43)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel29)
                .addGap(0, 0, 0)
                .addComponent(jLabel87)
                .addGap(0, 0, 0)
                .addComponent(jLabel88)
                .addGap(18, 18, 18)
                .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jLabel73)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel74)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jLabel31)
                .addGap(2, 2, 2)
                .addComponent(jLabel32)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));

        siCrearProductos.setBackground(new java.awt.Color(255, 255, 255));
        grupoCreacionProductos.add(siCrearProductos);
        siCrearProductos.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        siCrearProductos.setSelected(true);
        siCrearProductos.setText("Si");
        siCrearProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siCrearProductosActionPerformed(evt);
            }
        });

        noCrearProductos.setBackground(new java.awt.Color(255, 255, 255));
        grupoCreacionProductos.add(noCrearProductos);
        noCrearProductos.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        noCrearProductos.setText("No");
        noCrearProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noCrearProductosActionPerformed(evt);
            }
        });

        siOrdenCompra.setBackground(new java.awt.Color(255, 255, 255));
        grupoOrdenCompras.add(siOrdenCompra);
        siOrdenCompra.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        siOrdenCompra.setSelected(true);
        siOrdenCompra.setText("Si");
        siOrdenCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siOrdenCompraActionPerformed(evt);
            }
        });

        noOrdenCompra.setBackground(new java.awt.Color(255, 255, 255));
        grupoOrdenCompras.add(noOrdenCompra);
        noOrdenCompra.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        noOrdenCompra.setText("No");
        noOrdenCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noOrdenCompraActionPerformed(evt);
            }
        });

        siCompras.setBackground(new java.awt.Color(255, 255, 255));
        grupoCompras.add(siCompras);
        siCompras.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        siCompras.setSelected(true);
        siCompras.setText("Si");
        siCompras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siComprasActionPerformed(evt);
            }
        });

        noCompras.setBackground(new java.awt.Color(255, 255, 255));
        grupoCompras.add(noCompras);
        noCompras.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        noCompras.setText("No");
        noCompras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noComprasActionPerformed(evt);
            }
        });

        siTrasladoExterno.setBackground(new java.awt.Color(255, 255, 255));
        grupoTrasladoExterno.add(siTrasladoExterno);
        siTrasladoExterno.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        siTrasladoExterno.setSelected(true);
        siTrasladoExterno.setText("Si");
        siTrasladoExterno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siTrasladoExternoActionPerformed(evt);
            }
        });

        noTrasladoExterno.setBackground(new java.awt.Color(255, 255, 255));
        grupoTrasladoExterno.add(noTrasladoExterno);
        noTrasladoExterno.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        noTrasladoExterno.setText("No");
        noTrasladoExterno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noTrasladoExternoActionPerformed(evt);
            }
        });

        siTrasladoInterno.setBackground(new java.awt.Color(255, 255, 255));
        grupoTrasladoInterno.add(siTrasladoInterno);
        siTrasladoInterno.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        siTrasladoInterno.setSelected(true);
        siTrasladoInterno.setText("Si");
        siTrasladoInterno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siTrasladoInternoActionPerformed(evt);
            }
        });

        noTrasladoInterno.setBackground(new java.awt.Color(255, 255, 255));
        grupoTrasladoInterno.add(noTrasladoInterno);
        noTrasladoInterno.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        noTrasladoInterno.setText("No");
        noTrasladoInterno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noTrasladoInternoActionPerformed(evt);
            }
        });

        siAjusteSalida.setBackground(new java.awt.Color(255, 255, 255));
        grupoAjusteSalida.add(siAjusteSalida);
        siAjusteSalida.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        siAjusteSalida.setSelected(true);
        siAjusteSalida.setText("Si");
        siAjusteSalida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siAjusteSalidaActionPerformed(evt);
            }
        });

        noAjusteSalida.setBackground(new java.awt.Color(255, 255, 255));
        grupoAjusteSalida.add(noAjusteSalida);
        noAjusteSalida.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        noAjusteSalida.setText("No");
        noAjusteSalida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noAjusteSalidaActionPerformed(evt);
            }
        });

        siGrupos.setBackground(new java.awt.Color(255, 255, 255));
        grupoGrupos.add(siGrupos);
        siGrupos.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        siGrupos.setSelected(true);
        siGrupos.setText("Si");
        siGrupos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siGruposActionPerformed(evt);
            }
        });

        noGrupos.setBackground(new java.awt.Color(255, 255, 255));
        grupoGrupos.add(noGrupos);
        noGrupos.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        noGrupos.setText("No");
        noGrupos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noGruposActionPerformed(evt);
            }
        });

        siDiseños.setBackground(new java.awt.Color(255, 255, 255));
        grupoDiseños.add(siDiseños);
        siDiseños.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        siDiseños.setSelected(true);
        siDiseños.setText("Si");
        siDiseños.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siDiseñosActionPerformed(evt);
            }
        });

        noDiseños.setBackground(new java.awt.Color(255, 255, 255));
        grupoDiseños.add(noDiseños);
        noDiseños.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        noDiseños.setText("No");
        noDiseños.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noDiseñosActionPerformed(evt);
            }
        });

        siArmados.setBackground(new java.awt.Color(255, 255, 255));
        grupoArmados.add(siArmados);
        siArmados.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        siArmados.setSelected(true);
        siArmados.setText("Si");
        siArmados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siArmadosActionPerformed(evt);
            }
        });

        noArmados.setBackground(new java.awt.Color(255, 255, 255));
        grupoArmados.add(noArmados);
        noArmados.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        noArmados.setText("No");
        noArmados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noArmadosActionPerformed(evt);
            }
        });

        siInventarioInicial.setBackground(new java.awt.Color(255, 255, 255));
        grupoInventarioInicial.add(siInventarioInicial);
        siInventarioInicial.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        siInventarioInicial.setSelected(true);
        siInventarioInicial.setText("Si");
        siInventarioInicial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siInventarioInicialActionPerformed(evt);
            }
        });

        noInventarioInicial.setBackground(new java.awt.Color(255, 255, 255));
        grupoInventarioInicial.add(noInventarioInicial);
        noInventarioInicial.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        noInventarioInicial.setText("No");
        noInventarioInicial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noInventarioInicialActionPerformed(evt);
            }
        });

        siTrasladoDetalle.setBackground(new java.awt.Color(255, 255, 255));
        grupoTrasladoDetalle.add(siTrasladoDetalle);
        siTrasladoDetalle.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        siTrasladoDetalle.setSelected(true);
        siTrasladoDetalle.setText("Si");
        siTrasladoDetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siTrasladoDetalleActionPerformed(evt);
            }
        });

        noTrasladoDetalle.setBackground(new java.awt.Color(255, 255, 255));
        grupoTrasladoDetalle.add(noTrasladoDetalle);
        noTrasladoDetalle.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        noTrasladoDetalle.setText("No");
        noTrasladoDetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noTrasladoDetalleActionPerformed(evt);
            }
        });

        siKardex.setBackground(new java.awt.Color(255, 255, 255));
        grupoKardex.add(siKardex);
        siKardex.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        siKardex.setSelected(true);
        siKardex.setText("Si");
        siKardex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siKardexActionPerformed(evt);
            }
        });

        noKardex.setBackground(new java.awt.Color(255, 255, 255));
        grupoKardex.add(noKardex);
        noKardex.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        noKardex.setText("No");
        noKardex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noKardexActionPerformed(evt);
            }
        });

        siAjusteEntrada.setBackground(new java.awt.Color(255, 255, 255));
        grupoAjusteEntrada.add(siAjusteEntrada);
        siAjusteEntrada.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        siAjusteEntrada.setSelected(true);
        siAjusteEntrada.setText("Si");
        siAjusteEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siAjusteEntradaActionPerformed(evt);
            }
        });

        noAjusteEntrada.setBackground(new java.awt.Color(255, 255, 255));
        grupoAjusteEntrada.add(noAjusteEntrada);
        noAjusteEntrada.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        noAjusteEntrada.setText("No");
        noAjusteEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noAjusteEntradaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(siTrasladoInterno, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(siArmados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(siDiseños, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(siTrasladoExterno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(siCrearProductos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(siCompras, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(siOrdenCompra, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(siAjusteSalida, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(siGrupos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(siInventarioInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(siTrasladoDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(siKardex, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(siAjusteEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(noAjusteEntrada, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(noKardex, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(noTrasladoDetalle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(noInventarioInicial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(noAjusteSalida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(noCompras, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(noOrdenCompra, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(noCrearProductos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(noTrasladoExterno, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(noTrasladoInterno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(noGrupos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(noDiseños, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(noArmados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(siCrearProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(noCrearProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(siOrdenCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(noOrdenCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(siCompras, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(noCompras, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(siTrasladoExterno, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(noTrasladoExterno, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(siTrasladoInterno, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(noTrasladoInterno, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(noAjusteEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(siAjusteEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(noAjusteSalida, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(siAjusteSalida, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(noGrupos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(siGrupos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(noDiseños, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(siDiseños, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(noArmados, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(siArmados, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(noInventarioInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(siInventarioInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(noTrasladoDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(siTrasladoDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(noKardex, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(siKardex, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));

        jLabel63.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel63.setText("Reporte de Productos");

        jLabel26.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel26.setText("Reporte de Prestamos");

        jLabel64.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel64.setText("Reporte de Compras");

        jLabel65.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel65.setText("Reporte de Ajustes");

        jLabel75.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel75.setText("Reporte de Orden de Compras");

        jLabel76.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel76.setText("Reporte de Inventario");

        jLabel77.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel77.setText("Reporte de Traslados interno");

        jLabel78.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel78.setText("Reporte de Ivas de Compras");

        jLabel79.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel79.setText("Reporte de movimientos serial");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel63, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                            .addComponent(jLabel64, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel65, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel75, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel76, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel77, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel78, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel79, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jLabel64)
                .addGap(3, 3, 3)
                .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jLabel76, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jLabel78, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));

        siReporteProductos.setBackground(new java.awt.Color(255, 255, 255));
        grupoReporteProductos.add(siReporteProductos);
        siReporteProductos.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        siReporteProductos.setSelected(true);
        siReporteProductos.setText("Si");
        siReporteProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siReporteProductosActionPerformed(evt);
            }
        });

        noReporteProductos.setBackground(new java.awt.Color(255, 255, 255));
        grupoReporteProductos.add(noReporteProductos);
        noReporteProductos.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        noReporteProductos.setText("No");
        noReporteProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noReporteProductosActionPerformed(evt);
            }
        });

        siReportePrestamos.setBackground(new java.awt.Color(255, 255, 255));
        grupoReportePrestamos.add(siReportePrestamos);
        siReportePrestamos.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        siReportePrestamos.setSelected(true);
        siReportePrestamos.setText("Si");
        siReportePrestamos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siReportePrestamosActionPerformed(evt);
            }
        });

        noReportePrestamos.setBackground(new java.awt.Color(255, 255, 255));
        grupoReportePrestamos.add(noReportePrestamos);
        noReportePrestamos.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        noReportePrestamos.setText("No");
        noReportePrestamos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noReportePrestamosActionPerformed(evt);
            }
        });

        siReporteCompras.setBackground(new java.awt.Color(255, 255, 255));
        grupoReporteCompras.add(siReporteCompras);
        siReporteCompras.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        siReporteCompras.setSelected(true);
        siReporteCompras.setText("Si");
        siReporteCompras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siReporteComprasActionPerformed(evt);
            }
        });

        noReporteCompras.setBackground(new java.awt.Color(255, 255, 255));
        grupoReporteCompras.add(noReporteCompras);
        noReporteCompras.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        noReporteCompras.setText("No");
        noReporteCompras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noReporteComprasActionPerformed(evt);
            }
        });

        siReporteAjustes.setBackground(new java.awt.Color(255, 255, 255));
        grupoReporteAjustes.add(siReporteAjustes);
        siReporteAjustes.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        siReporteAjustes.setSelected(true);
        siReporteAjustes.setText("Si");
        siReporteAjustes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siReporteAjustesActionPerformed(evt);
            }
        });

        noReporteAjustes.setBackground(new java.awt.Color(255, 255, 255));
        grupoReporteAjustes.add(noReporteAjustes);
        noReporteAjustes.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        noReporteAjustes.setText("No");
        noReporteAjustes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noReporteAjustesActionPerformed(evt);
            }
        });

        noReporteMovimientosSerial.setBackground(new java.awt.Color(255, 255, 255));
        grupoReporteMovimientosSerial.add(noReporteMovimientosSerial);
        noReporteMovimientosSerial.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        noReporteMovimientosSerial.setText("No");
        noReporteMovimientosSerial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noReporteMovimientosSerialActionPerformed(evt);
            }
        });

        siReporteMovimientosSerial.setBackground(new java.awt.Color(255, 255, 255));
        grupoReporteMovimientosSerial.add(siReporteMovimientosSerial);
        siReporteMovimientosSerial.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        siReporteMovimientosSerial.setSelected(true);
        siReporteMovimientosSerial.setText("Si");
        siReporteMovimientosSerial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siReporteMovimientosSerialActionPerformed(evt);
            }
        });

        siReporteIvasCompras.setBackground(new java.awt.Color(255, 255, 255));
        grupoReporteIvasCompras.add(siReporteIvasCompras);
        siReporteIvasCompras.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        siReporteIvasCompras.setSelected(true);
        siReporteIvasCompras.setText("Si");
        siReporteIvasCompras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siReporteIvasComprasActionPerformed(evt);
            }
        });

        noReporteIvasCompras.setBackground(new java.awt.Color(255, 255, 255));
        grupoReporteIvasCompras.add(noReporteIvasCompras);
        noReporteIvasCompras.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        noReporteIvasCompras.setText("No");
        noReporteIvasCompras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noReporteIvasComprasActionPerformed(evt);
            }
        });

        siReporteTrasladoInterno.setBackground(new java.awt.Color(255, 255, 255));
        grupoReporteTrasladosInterno.add(siReporteTrasladoInterno);
        siReporteTrasladoInterno.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        siReporteTrasladoInterno.setSelected(true);
        siReporteTrasladoInterno.setText("Si");
        siReporteTrasladoInterno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siReporteTrasladoInternoActionPerformed(evt);
            }
        });

        noReporteTrasladoInterno.setBackground(new java.awt.Color(255, 255, 255));
        grupoReporteTrasladosInterno.add(noReporteTrasladoInterno);
        noReporteTrasladoInterno.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        noReporteTrasladoInterno.setText("No");
        noReporteTrasladoInterno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noReporteTrasladoInternoActionPerformed(evt);
            }
        });

        siReporteInventario.setBackground(new java.awt.Color(255, 255, 255));
        grupoReporteInventario.add(siReporteInventario);
        siReporteInventario.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        siReporteInventario.setSelected(true);
        siReporteInventario.setText("Si");
        siReporteInventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siReporteInventarioActionPerformed(evt);
            }
        });

        noReporteInventario.setBackground(new java.awt.Color(255, 255, 255));
        grupoReporteInventario.add(noReporteInventario);
        noReporteInventario.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        noReporteInventario.setText("No");
        noReporteInventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noReporteInventarioActionPerformed(evt);
            }
        });

        siReporteOrdenCompra.setBackground(new java.awt.Color(255, 255, 255));
        grupoReporteOrdenCompras.add(siReporteOrdenCompra);
        siReporteOrdenCompra.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        siReporteOrdenCompra.setSelected(true);
        siReporteOrdenCompra.setText("Si");
        siReporteOrdenCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siReporteOrdenCompraActionPerformed(evt);
            }
        });

        noReporteOrdenCompra.setBackground(new java.awt.Color(255, 255, 255));
        grupoReporteOrdenCompras.add(noReporteOrdenCompra);
        noReporteOrdenCompra.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        noReporteOrdenCompra.setText("No");
        noReporteOrdenCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noReporteOrdenCompraActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(siReporteOrdenCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(siReporteMovimientosSerial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(siReporteIvasCompras, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(siReporteAjustes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(siReporteProductos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(siReporteCompras, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(siReportePrestamos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(siReporteInventario, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(siReporteTrasladoInterno, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(noReporteInventario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(noReporteCompras, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(noReportePrestamos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(noReporteProductos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(noReporteAjustes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(noReporteOrdenCompra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(noReporteTrasladoInterno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(noReporteIvasCompras, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(noReporteMovimientosSerial))))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(siReporteProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(noReporteProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(siReportePrestamos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(noReportePrestamos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(siReporteCompras, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(noReporteCompras, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(siReporteAjustes, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(noReporteAjustes, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(siReporteOrdenCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(noReporteOrdenCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(noReporteInventario, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(siReporteInventario, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(noReporteTrasladoInterno, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(siReporteTrasladoInterno, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(noReporteIvasCompras, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(siReporteIvasCompras, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(noReporteMovimientosSerial, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(siReporteMovimientosSerial, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                        .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel13)
                .addGap(5, 5, 5)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        grupo.addTab("Productos", jPanel3);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel15.setBackground(new java.awt.Color(204, 204, 204));
        jLabel15.setFont(new java.awt.Font("Century Gothic", 1, 30)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Terceros");
        jLabel15.setOpaque(true);

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));

        jLabel11.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel11.setText("Crear Clientes");

        jLabel45.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel45.setText("Crear Proveedor");

        jLabel44.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel44.setText("Crear Empleado");

        jLabel42.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel42.setText("Bodegas Internas");

        jLabel80.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel80.setText("Clientes Prestamos");

        jLabel81.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel81.setText("Show Room");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel80, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel81, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jLabel80, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jLabel81, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));

        siCrearCliente.setBackground(new java.awt.Color(255, 255, 255));
        grupoCrearClientes.add(siCrearCliente);
        siCrearCliente.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        siCrearCliente.setSelected(true);
        siCrearCliente.setText("Si");
        siCrearCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siCrearClienteActionPerformed(evt);
            }
        });

        noCrearCliente.setBackground(new java.awt.Color(255, 255, 255));
        grupoCrearClientes.add(noCrearCliente);
        noCrearCliente.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        noCrearCliente.setText("No");
        noCrearCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noCrearClienteActionPerformed(evt);
            }
        });

        siCrearProveedor.setBackground(new java.awt.Color(255, 255, 255));
        grupoCrearProveedor.add(siCrearProveedor);
        siCrearProveedor.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        siCrearProveedor.setSelected(true);
        siCrearProveedor.setText("Si");
        siCrearProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siCrearProveedorActionPerformed(evt);
            }
        });

        noCrearProveedor.setBackground(new java.awt.Color(255, 255, 255));
        grupoCrearProveedor.add(noCrearProveedor);
        noCrearProveedor.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        noCrearProveedor.setText("No");
        noCrearProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noCrearProveedorActionPerformed(evt);
            }
        });

        siCrearEmpleado.setBackground(new java.awt.Color(255, 255, 255));
        grupoCrearEmpleado.add(siCrearEmpleado);
        siCrearEmpleado.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        siCrearEmpleado.setSelected(true);
        siCrearEmpleado.setText("Si");
        siCrearEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siCrearEmpleadoActionPerformed(evt);
            }
        });

        noCrearEmpleado.setBackground(new java.awt.Color(255, 255, 255));
        grupoCrearEmpleado.add(noCrearEmpleado);
        noCrearEmpleado.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        noCrearEmpleado.setText("No");
        noCrearEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noCrearEmpleadoActionPerformed(evt);
            }
        });

        siBodegaInterna.setBackground(new java.awt.Color(255, 255, 255));
        grupoBodegasInternas.add(siBodegaInterna);
        siBodegaInterna.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        siBodegaInterna.setSelected(true);
        siBodegaInterna.setText("Si");
        siBodegaInterna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siBodegaInternaActionPerformed(evt);
            }
        });

        noBodegaInterna.setBackground(new java.awt.Color(255, 255, 255));
        grupoBodegasInternas.add(noBodegaInterna);
        noBodegaInterna.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        noBodegaInterna.setText("No");
        noBodegaInterna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noBodegaInternaActionPerformed(evt);
            }
        });

        siClientesPrestamos.setBackground(new java.awt.Color(255, 255, 255));
        grupoClientesPrestamos.add(siClientesPrestamos);
        siClientesPrestamos.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        siClientesPrestamos.setSelected(true);
        siClientesPrestamos.setText("Si");
        siClientesPrestamos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siClientesPrestamosActionPerformed(evt);
            }
        });

        noClientesPrestamos.setBackground(new java.awt.Color(255, 255, 255));
        grupoClientesPrestamos.add(noClientesPrestamos);
        noClientesPrestamos.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        noClientesPrestamos.setText("No");
        noClientesPrestamos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noClientesPrestamosActionPerformed(evt);
            }
        });

        siShowRoom.setBackground(new java.awt.Color(255, 255, 255));
        grupoShowRoom.add(siShowRoom);
        siShowRoom.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        siShowRoom.setSelected(true);
        siShowRoom.setText("Si");
        siShowRoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siShowRoomActionPerformed(evt);
            }
        });

        noShowRoom.setBackground(new java.awt.Color(255, 255, 255));
        grupoShowRoom.add(noShowRoom);
        noShowRoom.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        noShowRoom.setText("No");
        noShowRoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noShowRoomActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel20Layout.createSequentialGroup()
                            .addComponent(siCrearCliente)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(noCrearCliente))
                        .addGroup(jPanel20Layout.createSequentialGroup()
                            .addComponent(siCrearProveedor)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(noCrearProveedor))
                        .addGroup(jPanel20Layout.createSequentialGroup()
                            .addComponent(siCrearEmpleado)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(noCrearEmpleado))
                        .addGroup(jPanel20Layout.createSequentialGroup()
                            .addComponent(siBodegaInterna)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(noBodegaInterna)))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(siClientesPrestamos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(noClientesPrestamos))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(siShowRoom)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(noShowRoom)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(siCrearCliente)
                    .addComponent(noCrearCliente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(siCrearProveedor)
                    .addComponent(noCrearProveedor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(siCrearEmpleado)
                    .addComponent(noCrearEmpleado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(siBodegaInterna)
                    .addComponent(noBodegaInterna))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(siClientesPrestamos)
                    .addComponent(noClientesPrestamos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(siShowRoom)
                    .addComponent(noShowRoom))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));

        jLabel46.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel46.setText("Reporte de Cliente");

        jLabel47.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel47.setText("Reporte de Proveedor");

        jLabel41.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel41.setText("Reporte de Empleados");

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                    .addComponent(jLabel46, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel22.setBackground(new java.awt.Color(255, 255, 255));

        siReporteCliente.setBackground(new java.awt.Color(255, 255, 255));
        grupoReporteCliente.add(siReporteCliente);
        siReporteCliente.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        siReporteCliente.setSelected(true);
        siReporteCliente.setText("Si");
        siReporteCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siReporteClienteActionPerformed(evt);
            }
        });

        noReporteCliente.setBackground(new java.awt.Color(255, 255, 255));
        grupoReporteCliente.add(noReporteCliente);
        noReporteCliente.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        noReporteCliente.setText("No");
        noReporteCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noReporteClienteActionPerformed(evt);
            }
        });

        siReporteProveedor.setBackground(new java.awt.Color(255, 255, 255));
        grupoReporteProveedor.add(siReporteProveedor);
        siReporteProveedor.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        siReporteProveedor.setSelected(true);
        siReporteProveedor.setText("Si");
        siReporteProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siReporteProveedorActionPerformed(evt);
            }
        });

        noReporteProveedor.setBackground(new java.awt.Color(255, 255, 255));
        grupoReporteProveedor.add(noReporteProveedor);
        noReporteProveedor.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        noReporteProveedor.setText("No");
        noReporteProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noReporteProveedorActionPerformed(evt);
            }
        });

        siReporteEmpleado.setBackground(new java.awt.Color(255, 255, 255));
        grupoReporteEmpleados.add(siReporteEmpleado);
        siReporteEmpleado.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        siReporteEmpleado.setSelected(true);
        siReporteEmpleado.setText("Si");
        siReporteEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siReporteEmpleadoActionPerformed(evt);
            }
        });

        noReporteEmpleado.setBackground(new java.awt.Color(255, 255, 255));
        grupoReporteEmpleados.add(noReporteEmpleado);
        noReporteEmpleado.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        noReporteEmpleado.setText("No");
        noReporteEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noReporteEmpleadoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addComponent(siReporteEmpleado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(noReporteEmpleado))
                    .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                            .addComponent(siReporteCliente)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(noReporteCliente))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                            .addComponent(siReporteProveedor)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(noReporteProveedor))))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(siReporteCliente)
                    .addComponent(noReporteCliente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(siReporteProveedor)
                    .addComponent(noReporteProveedor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(siReporteEmpleado)
                    .addComponent(noReporteEmpleado))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                        .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel15)
                .addGap(5, 5, 5)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel22, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator3))
                .addContainerGap(206, Short.MAX_VALUE))
        );

        grupo.addTab("Terceros", jPanel4);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel56.setBackground(new java.awt.Color(204, 204, 204));
        jLabel56.setFont(new java.awt.Font("Century Gothic", 1, 30)); // NOI18N
        jLabel56.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel56.setText("Cartera");
        jLabel56.setOpaque(true);

        jPanel23.setBackground(new java.awt.Color(255, 255, 255));

        jLabel53.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel53.setText("Abonos Cuentas x Cobrar");

        jLabel57.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel57.setText("Abonos Cuentas x Pagar");

        jLabel52.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel52.setText("Notas Credito");

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel53, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel57, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel52, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel24.setBackground(new java.awt.Color(255, 255, 255));

        siAbonosCxc.setBackground(new java.awt.Color(255, 255, 255));
        grupoAbonosCuentasxCobrar.add(siAbonosCxc);
        siAbonosCxc.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        siAbonosCxc.setSelected(true);
        siAbonosCxc.setText("Si");
        siAbonosCxc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siAbonosCxcActionPerformed(evt);
            }
        });

        noAbonosCxc.setBackground(new java.awt.Color(255, 255, 255));
        grupoAbonosCuentasxCobrar.add(noAbonosCxc);
        noAbonosCxc.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        noAbonosCxc.setText("No");
        noAbonosCxc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noAbonosCxcActionPerformed(evt);
            }
        });

        siAbonosCxP.setBackground(new java.awt.Color(255, 255, 255));
        grupoAbonosCuentasxPagar.add(siAbonosCxP);
        siAbonosCxP.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        siAbonosCxP.setSelected(true);
        siAbonosCxP.setText("Si");
        siAbonosCxP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siAbonosCxPActionPerformed(evt);
            }
        });

        noAbonosCxP.setBackground(new java.awt.Color(255, 255, 255));
        grupoAbonosCuentasxPagar.add(noAbonosCxP);
        noAbonosCxP.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        noAbonosCxP.setText("No");
        noAbonosCxP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noAbonosCxPActionPerformed(evt);
            }
        });

        siNotasCredito.setBackground(new java.awt.Color(255, 255, 255));
        grupoNotasCredito.add(siNotasCredito);
        siNotasCredito.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        siNotasCredito.setSelected(true);
        siNotasCredito.setText("Si");
        siNotasCredito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siNotasCreditoActionPerformed(evt);
            }
        });

        noNotasCredito.setBackground(new java.awt.Color(255, 255, 255));
        grupoNotasCredito.add(noNotasCredito);
        noNotasCredito.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        noNotasCredito.setText("No");
        noNotasCredito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noNotasCreditoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addComponent(siAbonosCxc)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(noAbonosCxc))
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addComponent(siAbonosCxP)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(noAbonosCxP))
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addComponent(siNotasCredito)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(noNotasCredito)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(siAbonosCxc)
                    .addComponent(noAbonosCxc))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(siAbonosCxP)
                    .addComponent(noAbonosCxP))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(siNotasCredito)
                    .addComponent(noNotasCredito))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel25.setBackground(new java.awt.Color(255, 255, 255));

        jLabel54.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel54.setText("Reporte cartera Cuentas x Cobrar");

        jLabel55.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel55.setText("Reporte abonos Cuentas x Cobrar");

        jLabel58.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel58.setText("Reporte cartera Cuentas x Pagar");

        jLabel82.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel82.setText("Reporte abonos Cuentas x Pagar");

        jLabel83.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel83.setText("Reporte Notas Creditos");

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel54, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel55, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel58, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel82, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel83, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jLabel82, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jLabel83, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(84, Short.MAX_VALUE))
        );

        jPanel26.setBackground(new java.awt.Color(255, 255, 255));

        siReporteCarteraCxC.setBackground(new java.awt.Color(255, 255, 255));
        grupoReporteCarteraCxC.add(siReporteCarteraCxC);
        siReporteCarteraCxC.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        siReporteCarteraCxC.setSelected(true);
        siReporteCarteraCxC.setText("Si");
        siReporteCarteraCxC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siReporteCarteraCxCActionPerformed(evt);
            }
        });

        noReporteCarteraCxC.setBackground(new java.awt.Color(255, 255, 255));
        grupoReporteCarteraCxC.add(noReporteCarteraCxC);
        noReporteCarteraCxC.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        noReporteCarteraCxC.setText("No");
        noReporteCarteraCxC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noReporteCarteraCxCActionPerformed(evt);
            }
        });

        siReporteAbonosCxC.setBackground(new java.awt.Color(255, 255, 255));
        grupoReporteAbonosCarteraCxC.add(siReporteAbonosCxC);
        siReporteAbonosCxC.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        siReporteAbonosCxC.setSelected(true);
        siReporteAbonosCxC.setText("Si");
        siReporteAbonosCxC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siReporteAbonosCxCActionPerformed(evt);
            }
        });

        noReporteAbonosCxC.setBackground(new java.awt.Color(255, 255, 255));
        grupoReporteAbonosCarteraCxC.add(noReporteAbonosCxC);
        noReporteAbonosCxC.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        noReporteAbonosCxC.setText("No");
        noReporteAbonosCxC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noReporteAbonosCxCActionPerformed(evt);
            }
        });

        siReporteCarteraCxP.setBackground(new java.awt.Color(255, 255, 255));
        grupoReporteCarteraCxP.add(siReporteCarteraCxP);
        siReporteCarteraCxP.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        siReporteCarteraCxP.setSelected(true);
        siReporteCarteraCxP.setText("Si");
        siReporteCarteraCxP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siReporteCarteraCxPActionPerformed(evt);
            }
        });

        noReporteCarteraCxP.setBackground(new java.awt.Color(255, 255, 255));
        grupoReporteCarteraCxP.add(noReporteCarteraCxP);
        noReporteCarteraCxP.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        noReporteCarteraCxP.setText("No");
        noReporteCarteraCxP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noReporteCarteraCxPActionPerformed(evt);
            }
        });

        siReporteAbonosCxP.setBackground(new java.awt.Color(255, 255, 255));
        grupoReporteAbonosCxP.add(siReporteAbonosCxP);
        siReporteAbonosCxP.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        siReporteAbonosCxP.setSelected(true);
        siReporteAbonosCxP.setText("Si");
        siReporteAbonosCxP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siReporteAbonosCxPActionPerformed(evt);
            }
        });

        noReporteAbonosCxP.setBackground(new java.awt.Color(255, 255, 255));
        grupoReporteAbonosCxP.add(noReporteAbonosCxP);
        noReporteAbonosCxP.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        noReporteAbonosCxP.setText("No");
        noReporteAbonosCxP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noReporteAbonosCxPActionPerformed(evt);
            }
        });

        siReporteNotasCredito.setBackground(new java.awt.Color(255, 255, 255));
        grupoReporteNotasCreditos.add(siReporteNotasCredito);
        siReporteNotasCredito.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        siReporteNotasCredito.setSelected(true);
        siReporteNotasCredito.setText("Si");
        siReporteNotasCredito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siReporteNotasCreditoActionPerformed(evt);
            }
        });

        noReporteNotasCredito.setBackground(new java.awt.Color(255, 255, 255));
        grupoReporteNotasCreditos.add(noReporteNotasCredito);
        noReporteNotasCredito.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        noReporteNotasCredito.setText("No");
        noReporteNotasCredito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noReporteNotasCreditoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel26Layout.createSequentialGroup()
                            .addComponent(siReporteCarteraCxC)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(noReporteCarteraCxC))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel26Layout.createSequentialGroup()
                            .addComponent(siReporteAbonosCxC)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(noReporteAbonosCxC)))
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addComponent(siReporteCarteraCxP)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(noReporteCarteraCxP))
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addComponent(siReporteAbonosCxP)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(noReporteAbonosCxP))
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addComponent(siReporteNotasCredito)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(noReporteNotasCredito)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(siReporteCarteraCxC, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(noReporteCarteraCxC, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(siReporteAbonosCxC, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(noReporteAbonosCxC, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(siReporteCarteraCxP, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(noReporteCarteraCxP, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(siReporteAbonosCxP, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(noReporteAbonosCxP, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(siReporteNotasCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(noReporteNotasCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jSeparator4.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel56, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel56)
                .addGap(5, 5, 5)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator4))
                .addContainerGap(209, Short.MAX_VALUE))
        );

        grupo.addTab("Cartera", jPanel5);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jLabel14.setBackground(new java.awt.Color(204, 204, 204));
        jLabel14.setFont(new java.awt.Font("Century Gothic", 1, 30)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Tesoreria");
        jLabel14.setOpaque(true);

        jPanel27.setBackground(new java.awt.Color(255, 255, 255));

        jLabel10.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel10.setText("Codigos");

        jLabel37.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel37.setText("Egresos");

        jLabel36.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel36.setText("Bancos");

        jLabel84.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel84.setText("Cuadre de Caja");

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel84, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jLabel84, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel28.setBackground(new java.awt.Color(255, 255, 255));

        siCodigos.setBackground(new java.awt.Color(255, 255, 255));
        grupoCodigos.add(siCodigos);
        siCodigos.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        siCodigos.setSelected(true);
        siCodigos.setText("Si");
        siCodigos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siCodigosActionPerformed(evt);
            }
        });

        noCodigos.setBackground(new java.awt.Color(255, 255, 255));
        grupoCodigos.add(noCodigos);
        noCodigos.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        noCodigos.setText("No");
        noCodigos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noCodigosActionPerformed(evt);
            }
        });

        siEgresos.setBackground(new java.awt.Color(255, 255, 255));
        grupoEgresos.add(siEgresos);
        siEgresos.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        siEgresos.setSelected(true);
        siEgresos.setText("Si");
        siEgresos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siEgresosActionPerformed(evt);
            }
        });

        noEgresos.setBackground(new java.awt.Color(255, 255, 255));
        grupoEgresos.add(noEgresos);
        noEgresos.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        noEgresos.setText("No");
        noEgresos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noEgresosActionPerformed(evt);
            }
        });

        siBancos.setBackground(new java.awt.Color(255, 255, 255));
        grupoBancos.add(siBancos);
        siBancos.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        siBancos.setSelected(true);
        siBancos.setText("Si");
        siBancos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siBancosActionPerformed(evt);
            }
        });

        noBancos.setBackground(new java.awt.Color(255, 255, 255));
        grupoBancos.add(noBancos);
        noBancos.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        noBancos.setText("No");
        noBancos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noBancosActionPerformed(evt);
            }
        });

        siCuadreCaja.setBackground(new java.awt.Color(255, 255, 255));
        grupoCuadreCaja.add(siCuadreCaja);
        siCuadreCaja.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        siCuadreCaja.setSelected(true);
        siCuadreCaja.setText("Si");
        siCuadreCaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siCuadreCajaActionPerformed(evt);
            }
        });

        noCuadreCaja.setBackground(new java.awt.Color(255, 255, 255));
        grupoCuadreCaja.add(noCuadreCaja);
        noCuadreCaja.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        noCuadreCaja.setText("No");
        noCuadreCaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noCuadreCajaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel28Layout.createSequentialGroup()
                            .addComponent(siCodigos)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(noCodigos))
                        .addGroup(jPanel28Layout.createSequentialGroup()
                            .addComponent(siEgresos)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(noEgresos))
                        .addGroup(jPanel28Layout.createSequentialGroup()
                            .addComponent(siBancos)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(noBancos)))
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addComponent(siCuadreCaja)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(noCuadreCaja)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(siCodigos)
                    .addComponent(noCodigos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(siEgresos)
                    .addComponent(noEgresos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(siBancos)
                    .addComponent(noBancos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(siCuadreCaja)
                    .addComponent(noCuadreCaja))
                .addContainerGap(97, Short.MAX_VALUE))
        );

        jPanel29.setBackground(new java.awt.Color(255, 255, 255));

        jLabel16.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel16.setText("Reporte de Egresos");

        jLabel85.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel85.setText("Reporte de Bancos");

        jLabel86.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel86.setText("Reporte de Cuadre de Caja");

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel85, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel86, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jLabel85, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jLabel86, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel30.setBackground(new java.awt.Color(255, 255, 255));

        siReporteEgresos.setBackground(new java.awt.Color(255, 255, 255));
        grupoReporteEgresos.add(siReporteEgresos);
        siReporteEgresos.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        siReporteEgresos.setSelected(true);
        siReporteEgresos.setText("Si");
        siReporteEgresos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siReporteEgresosActionPerformed(evt);
            }
        });

        noReporteEgresos.setBackground(new java.awt.Color(255, 255, 255));
        grupoReporteEgresos.add(noReporteEgresos);
        noReporteEgresos.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        noReporteEgresos.setText("No");
        noReporteEgresos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noReporteEgresosActionPerformed(evt);
            }
        });

        siReporteBancos.setBackground(new java.awt.Color(255, 255, 255));
        grupoReporteBancos.add(siReporteBancos);
        siReporteBancos.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        siReporteBancos.setSelected(true);
        siReporteBancos.setText("Si");
        siReporteBancos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siReporteBancosActionPerformed(evt);
            }
        });

        noReporteBancos.setBackground(new java.awt.Color(255, 255, 255));
        grupoReporteBancos.add(noReporteBancos);
        noReporteBancos.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        noReporteBancos.setText("No");
        noReporteBancos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noReporteBancosActionPerformed(evt);
            }
        });

        siReporteCuadreCaja.setBackground(new java.awt.Color(255, 255, 255));
        grupoReporteCuadreCaja.add(siReporteCuadreCaja);
        siReporteCuadreCaja.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        siReporteCuadreCaja.setSelected(true);
        siReporteCuadreCaja.setText("Si");
        siReporteCuadreCaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siReporteCuadreCajaActionPerformed(evt);
            }
        });

        noReporteCuadreCaja.setBackground(new java.awt.Color(255, 255, 255));
        grupoReporteCuadreCaja.add(noReporteCuadreCaja);
        noReporteCuadreCaja.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        noReporteCuadreCaja.setText("No");
        noReporteCuadreCaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noReporteCuadreCajaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel30Layout.createSequentialGroup()
                        .addComponent(siReporteEgresos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(noReporteEgresos))
                    .addGroup(jPanel30Layout.createSequentialGroup()
                        .addComponent(siReporteBancos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(noReporteBancos))
                    .addGroup(jPanel30Layout.createSequentialGroup()
                        .addComponent(siReporteCuadreCaja)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(noReporteCuadreCaja)))
                .addContainerGap(10, Short.MAX_VALUE))
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(siReporteEgresos)
                    .addComponent(noReporteEgresos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(siReporteBancos)
                    .addComponent(noReporteBancos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(siReporteCuadreCaja)
                    .addComponent(noReporteCuadreCaja))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jSeparator5.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                        .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                        .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel14)
                .addGap(5, 5, 5)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator5))
                .addContainerGap(224, Short.MAX_VALUE))
        );

        grupo.addTab("Tesoreria", jPanel6);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        jLabel68.setBackground(new java.awt.Color(204, 204, 204));
        jLabel68.setFont(new java.awt.Font("Century Gothic", 1, 30)); // NOI18N
        jLabel68.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel68.setText("Agenda");
        jLabel68.setOpaque(true);

        jPanel31.setBackground(new java.awt.Color(255, 255, 255));

        jLabel34.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel34.setText("Registrar Agenda");

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel32.setBackground(new java.awt.Color(255, 255, 255));

        siRegistrarAgenda.setBackground(new java.awt.Color(255, 255, 255));
        grupoRegistrarAgenda.add(siRegistrarAgenda);
        siRegistrarAgenda.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        siRegistrarAgenda.setText("Si");
        siRegistrarAgenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siRegistrarAgendaActionPerformed(evt);
            }
        });

        noRegistrarAgenda.setBackground(new java.awt.Color(255, 255, 255));
        grupoRegistrarAgenda.add(noRegistrarAgenda);
        noRegistrarAgenda.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        noRegistrarAgenda.setText("No");
        noRegistrarAgenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noRegistrarAgendaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(siRegistrarAgenda)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(noRegistrarAgenda)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(siRegistrarAgenda)
                    .addComponent(noRegistrarAgenda))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel33.setBackground(new java.awt.Color(255, 255, 255));

        siReporteAgenda.setBackground(new java.awt.Color(255, 255, 255));
        grupoReporteAgenda.add(siReporteAgenda);
        siReporteAgenda.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        siReporteAgenda.setText("Si");
        siReporteAgenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siReporteAgendaActionPerformed(evt);
            }
        });

        noReporteAgenda.setBackground(new java.awt.Color(255, 255, 255));
        grupoReporteAgenda.add(noReporteAgenda);
        noReporteAgenda.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        noReporteAgenda.setText("No");
        noReporteAgenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noReporteAgendaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(siReporteAgenda)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(noReporteAgenda)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(siReporteAgenda)
                    .addComponent(noReporteAgenda))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel34.setBackground(new java.awt.Color(255, 255, 255));

        jLabel67.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel67.setText("Reportes Agenda");

        javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
        jPanel34.setLayout(jPanel34Layout);
        jPanel34Layout.setHorizontalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel34Layout.setVerticalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(62, Short.MAX_VALUE))
        );

        jSeparator6.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator6.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel68, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 111, Short.MAX_VALUE)
                        .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 115, Short.MAX_VALUE)
                        .addComponent(jPanel34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel68)
                .addGap(5, 5, 5)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel33, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel34, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator6))
                .addContainerGap(364, Short.MAX_VALUE))
        );

        grupo.addTab("Agenda", jPanel9);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jLabel48.setBackground(new java.awt.Color(204, 204, 204));
        jLabel48.setFont(new java.awt.Font("Century Gothic", 1, 30)); // NOI18N
        jLabel48.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel48.setText("Medico");
        jLabel48.setOpaque(true);

        jPanel39.setBackground(new java.awt.Color(255, 255, 255));

        siOrdenServicioMedica.setBackground(new java.awt.Color(255, 255, 255));
        grupoOrdenServicioMedica.add(siOrdenServicioMedica);
        siOrdenServicioMedica.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        siOrdenServicioMedica.setSelected(true);
        siOrdenServicioMedica.setText("Si");
        siOrdenServicioMedica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siOrdenServicioMedicaActionPerformed(evt);
            }
        });

        noOrdenServicioMedica.setBackground(new java.awt.Color(255, 255, 255));
        grupoOrdenServicioMedica.add(noOrdenServicioMedica);
        noOrdenServicioMedica.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        noOrdenServicioMedica.setText("No");
        noOrdenServicioMedica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noOrdenServicioMedicaActionPerformed(evt);
            }
        });

        siHistoriaClinicaMedica.setBackground(new java.awt.Color(255, 255, 255));
        grupoHistoriaClinicaMedica.add(siHistoriaClinicaMedica);
        siHistoriaClinicaMedica.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        siHistoriaClinicaMedica.setSelected(true);
        siHistoriaClinicaMedica.setText("Si");
        siHistoriaClinicaMedica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siHistoriaClinicaMedicaActionPerformed(evt);
            }
        });

        noHistoriaClinicaMedica.setBackground(new java.awt.Color(255, 255, 255));
        grupoHistoriaClinicaMedica.add(noHistoriaClinicaMedica);
        noHistoriaClinicaMedica.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        noHistoriaClinicaMedica.setText("No");
        noHistoriaClinicaMedica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noHistoriaClinicaMedicaActionPerformed(evt);
            }
        });

        siFormatos.setBackground(new java.awt.Color(255, 255, 255));
        grupoFormatosMedica.add(siFormatos);
        siFormatos.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        siFormatos.setSelected(true);
        siFormatos.setText("Si");
        siFormatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siFormatosActionPerformed(evt);
            }
        });

        noFormatos.setBackground(new java.awt.Color(255, 255, 255));
        grupoFormatosMedica.add(noFormatos);
        noFormatos.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        noFormatos.setText("No");
        noFormatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noFormatosActionPerformed(evt);
            }
        });

        siConvenio.setBackground(new java.awt.Color(255, 255, 255));
        grupoConvenio.add(siConvenio);
        siConvenio.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        siConvenio.setSelected(true);
        siConvenio.setText("Si");
        siConvenio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siConvenioActionPerformed(evt);
            }
        });

        noConvenio.setBackground(new java.awt.Color(255, 255, 255));
        grupoConvenio.add(noConvenio);
        noConvenio.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        noConvenio.setText("No");
        noConvenio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noConvenioActionPerformed(evt);
            }
        });

        siNotaEnfermeria.setBackground(new java.awt.Color(255, 255, 255));
        grupoNotaEnfermeria.add(siNotaEnfermeria);
        siNotaEnfermeria.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        siNotaEnfermeria.setSelected(true);
        siNotaEnfermeria.setText("Si");
        siNotaEnfermeria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siNotaEnfermeriaActionPerformed(evt);
            }
        });

        noNotaEnfermeria.setBackground(new java.awt.Color(255, 255, 255));
        grupoNotaEnfermeria.add(noNotaEnfermeria);
        noNotaEnfermeria.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        noNotaEnfermeria.setText("No");
        noNotaEnfermeria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noNotaEnfermeriaActionPerformed(evt);
            }
        });

        siMedicamentos.setBackground(new java.awt.Color(255, 255, 255));
        grupoMedicamentos.add(siMedicamentos);
        siMedicamentos.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        siMedicamentos.setSelected(true);
        siMedicamentos.setText("Si");
        siMedicamentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siMedicamentosActionPerformed(evt);
            }
        });

        noMedicamentos.setBackground(new java.awt.Color(255, 255, 255));
        grupoMedicamentos.add(noMedicamentos);
        noMedicamentos.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        noMedicamentos.setText("No");
        noMedicamentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noMedicamentosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel39Layout = new javax.swing.GroupLayout(jPanel39);
        jPanel39.setLayout(jPanel39Layout);
        jPanel39Layout.setHorizontalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel39Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel39Layout.createSequentialGroup()
                        .addComponent(siOrdenServicioMedica)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(noOrdenServicioMedica))
                    .addGroup(jPanel39Layout.createSequentialGroup()
                        .addComponent(siHistoriaClinicaMedica)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(noHistoriaClinicaMedica))
                    .addGroup(jPanel39Layout.createSequentialGroup()
                        .addComponent(siFormatos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(noFormatos))
                    .addGroup(jPanel39Layout.createSequentialGroup()
                        .addComponent(siConvenio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(noConvenio))
                    .addGroup(jPanel39Layout.createSequentialGroup()
                        .addComponent(siNotaEnfermeria)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(noNotaEnfermeria))
                    .addGroup(jPanel39Layout.createSequentialGroup()
                        .addComponent(siMedicamentos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(noMedicamentos)))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel39Layout.setVerticalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel39Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(siOrdenServicioMedica)
                    .addComponent(noOrdenServicioMedica))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(siHistoriaClinicaMedica)
                    .addComponent(noHistoriaClinicaMedica))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(siFormatos)
                    .addComponent(noFormatos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(siConvenio)
                    .addComponent(noConvenio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(siNotaEnfermeria)
                    .addComponent(noNotaEnfermeria))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(siMedicamentos)
                    .addComponent(noMedicamentos))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel40.setBackground(new java.awt.Color(255, 255, 255));

        jLabel95.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel95.setText("Orden de servicio");

        jLabel97.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel97.setText("Historia clinica");

        jLabel102.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel102.setText("Formatos");

        jLabel103.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel103.setText("Convenio");

        jLabel105.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel105.setText("Nota enfermeria");

        jLabel106.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel106.setText("Medicamentos");

        javax.swing.GroupLayout jPanel40Layout = new javax.swing.GroupLayout(jPanel40);
        jPanel40.setLayout(jPanel40Layout);
        jPanel40Layout.setHorizontalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel40Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel95, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel97, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel102, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel103, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel105, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel106, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel40Layout.setVerticalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel40Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel95, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel97, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel102, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel103, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel105, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel106, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        jSeparator8.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator8.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jPanel41.setBackground(new java.awt.Color(255, 255, 255));

        jLabel96.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel96.setText("Reportes ordenes");

        jLabel107.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel107.setText("Reportes formulas");

        jLabel108.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel108.setText("Certificado estudiante");

        jLabel109.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel109.setText("Certificado medico");

        jLabel110.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel110.setText("Certificado OyS");

        javax.swing.GroupLayout jPanel41Layout = new javax.swing.GroupLayout(jPanel41);
        jPanel41.setLayout(jPanel41Layout);
        jPanel41Layout.setHorizontalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel41Layout.createSequentialGroup()
                        .addComponent(jLabel107, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel96, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel41Layout.createSequentialGroup()
                        .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel108, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                            .addComponent(jLabel109, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addComponent(jLabel110, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel41Layout.setVerticalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel96, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel107, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel108)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel109)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel110, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel42.setBackground(new java.awt.Color(255, 255, 255));

        siReportesOrdenesMedicas.setBackground(new java.awt.Color(255, 255, 255));
        grupoReporteOrdenesMedica.add(siReportesOrdenesMedicas);
        siReportesOrdenesMedicas.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        siReportesOrdenesMedicas.setSelected(true);
        siReportesOrdenesMedicas.setText("Si");
        siReportesOrdenesMedicas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siReportesOrdenesMedicasActionPerformed(evt);
            }
        });

        noReportesOrdenesMedicas.setBackground(new java.awt.Color(255, 255, 255));
        grupoReporteOrdenesMedica.add(noReportesOrdenesMedicas);
        noReportesOrdenesMedicas.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        noReportesOrdenesMedicas.setText("No");
        noReportesOrdenesMedicas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noReportesOrdenesMedicasActionPerformed(evt);
            }
        });

        siReporteFormulas.setBackground(new java.awt.Color(255, 255, 255));
        grupoReporteFormulas.add(siReporteFormulas);
        siReporteFormulas.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        siReporteFormulas.setSelected(true);
        siReporteFormulas.setText("Si");
        siReporteFormulas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siReporteFormulasActionPerformed(evt);
            }
        });

        noReporteFormulas.setBackground(new java.awt.Color(255, 255, 255));
        grupoReporteFormulas.add(noReporteFormulas);
        noReporteFormulas.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        noReporteFormulas.setText("No");
        noReporteFormulas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noReporteFormulasActionPerformed(evt);
            }
        });

        siCertificadoEstudiante.setBackground(new java.awt.Color(255, 255, 255));
        grupoCertificadoEstudiante.add(siCertificadoEstudiante);
        siCertificadoEstudiante.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        siCertificadoEstudiante.setSelected(true);
        siCertificadoEstudiante.setText("Si");
        siCertificadoEstudiante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siCertificadoEstudianteActionPerformed(evt);
            }
        });

        noCertificadoEstudiante.setBackground(new java.awt.Color(255, 255, 255));
        grupoCertificadoEstudiante.add(noCertificadoEstudiante);
        noCertificadoEstudiante.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        noCertificadoEstudiante.setText("No");
        noCertificadoEstudiante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noCertificadoEstudianteActionPerformed(evt);
            }
        });

        siCertificadoMedico.setBackground(new java.awt.Color(255, 255, 255));
        grupoCertificadoMedico.add(siCertificadoMedico);
        siCertificadoMedico.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        siCertificadoMedico.setSelected(true);
        siCertificadoMedico.setText("Si");
        siCertificadoMedico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siCertificadoMedicoActionPerformed(evt);
            }
        });

        noCertificadoMedico.setBackground(new java.awt.Color(255, 255, 255));
        grupoCertificadoMedico.add(noCertificadoMedico);
        noCertificadoMedico.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        noCertificadoMedico.setText("No");
        noCertificadoMedico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noCertificadoMedicoActionPerformed(evt);
            }
        });

        siCertificadoOyS.setBackground(new java.awt.Color(255, 255, 255));
        grupoCertificadoOyS.add(siCertificadoOyS);
        siCertificadoOyS.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        siCertificadoOyS.setSelected(true);
        siCertificadoOyS.setText("Si");
        siCertificadoOyS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siCertificadoOySActionPerformed(evt);
            }
        });

        noCertificadoOyS.setBackground(new java.awt.Color(255, 255, 255));
        grupoCertificadoOyS.add(noCertificadoOyS);
        noCertificadoOyS.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        noCertificadoOyS.setText("No");
        noCertificadoOyS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noCertificadoOySActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel42Layout = new javax.swing.GroupLayout(jPanel42);
        jPanel42.setLayout(jPanel42Layout);
        jPanel42Layout.setHorizontalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel42Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel42Layout.createSequentialGroup()
                        .addComponent(siReportesOrdenesMedicas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(noReportesOrdenesMedicas))
                    .addGroup(jPanel42Layout.createSequentialGroup()
                        .addComponent(siReporteFormulas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(noReporteFormulas))
                    .addGroup(jPanel42Layout.createSequentialGroup()
                        .addComponent(siCertificadoEstudiante)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(noCertificadoEstudiante))
                    .addGroup(jPanel42Layout.createSequentialGroup()
                        .addComponent(siCertificadoMedico)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(noCertificadoMedico))
                    .addGroup(jPanel42Layout.createSequentialGroup()
                        .addComponent(siCertificadoOyS)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(noCertificadoOyS)))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel42Layout.setVerticalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel42Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(siReportesOrdenesMedicas)
                    .addComponent(noReportesOrdenesMedicas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(siReporteFormulas)
                    .addComponent(noReporteFormulas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(siCertificadoEstudiante)
                    .addComponent(noCertificadoEstudiante))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(siCertificadoMedico)
                    .addComponent(noCertificadoMedico))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(siCertificadoOyS)
                    .addComponent(noCertificadoOyS))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jPanel40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                        .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                        .addComponent(jPanel41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel48)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator8)
                    .addComponent(jPanel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(206, Short.MAX_VALUE))
        );

        grupo.addTab("Medico", jPanel8);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jLabel66.setBackground(new java.awt.Color(204, 204, 204));
        jLabel66.setFont(new java.awt.Font("Century Gothic", 1, 30)); // NOI18N
        jLabel66.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel66.setText("Veterinaria");
        jLabel66.setOpaque(true);

        jPanel35.setBackground(new java.awt.Color(255, 255, 255));

        jLabel89.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel89.setText("Mascotas");

        jLabel90.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel90.setText("Historia Clinica");

        jLabel91.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel91.setText("Guarderia");

        jLabel92.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel92.setText("Peluqueria");

        jLabel93.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel93.setText("Ing. Hospitalización");

        jLabel94.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel94.setText("Alertas");

        javax.swing.GroupLayout jPanel35Layout = new javax.swing.GroupLayout(jPanel35);
        jPanel35.setLayout(jPanel35Layout);
        jPanel35Layout.setHorizontalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel94, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel93, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel92, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel91, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel90, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel89, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel35Layout.setVerticalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel89, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jLabel90, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jLabel91, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jLabel92, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jLabel93, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jLabel94, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(104, Short.MAX_VALUE))
        );

        jPanel36.setBackground(new java.awt.Color(255, 255, 255));

        siMascotas.setBackground(new java.awt.Color(255, 255, 255));
        grupoMascotas.add(siMascotas);
        siMascotas.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        siMascotas.setText("Si");
        siMascotas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siMascotasActionPerformed(evt);
            }
        });

        noMascotas.setBackground(new java.awt.Color(255, 255, 255));
        grupoMascotas.add(noMascotas);
        noMascotas.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        noMascotas.setSelected(true);
        noMascotas.setText("No");
        noMascotas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noMascotasActionPerformed(evt);
            }
        });

        siHClinica.setBackground(new java.awt.Color(255, 255, 255));
        grupoHistoriaClinica.add(siHClinica);
        siHClinica.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        siHClinica.setText("Si");
        siHClinica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siHClinicaActionPerformed(evt);
            }
        });

        noHClinica.setBackground(new java.awt.Color(255, 255, 255));
        grupoHistoriaClinica.add(noHClinica);
        noHClinica.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        noHClinica.setSelected(true);
        noHClinica.setText("No");
        noHClinica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noHClinicaActionPerformed(evt);
            }
        });

        siGuarderia.setBackground(new java.awt.Color(255, 255, 255));
        grupoGuarderia.add(siGuarderia);
        siGuarderia.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        siGuarderia.setText("Si");
        siGuarderia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siGuarderiaActionPerformed(evt);
            }
        });

        noGuarderia.setBackground(new java.awt.Color(255, 255, 255));
        grupoGuarderia.add(noGuarderia);
        noGuarderia.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        noGuarderia.setSelected(true);
        noGuarderia.setText("No");
        noGuarderia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noGuarderiaActionPerformed(evt);
            }
        });

        siPeluqueria.setBackground(new java.awt.Color(255, 255, 255));
        grupoPeluqueria.add(siPeluqueria);
        siPeluqueria.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        siPeluqueria.setText("Si");
        siPeluqueria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siPeluqueriaActionPerformed(evt);
            }
        });

        noPeluqueria.setBackground(new java.awt.Color(255, 255, 255));
        grupoPeluqueria.add(noPeluqueria);
        noPeluqueria.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        noPeluqueria.setSelected(true);
        noPeluqueria.setText("No");
        noPeluqueria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noPeluqueriaActionPerformed(evt);
            }
        });

        siHospitalizacion.setBackground(new java.awt.Color(255, 255, 255));
        grupoHospitalizacion.add(siHospitalizacion);
        siHospitalizacion.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        siHospitalizacion.setText("Si");
        siHospitalizacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siHospitalizacionActionPerformed(evt);
            }
        });

        noHospitalizacion.setBackground(new java.awt.Color(255, 255, 255));
        grupoHospitalizacion.add(noHospitalizacion);
        noHospitalizacion.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        noHospitalizacion.setSelected(true);
        noHospitalizacion.setText("No");
        noHospitalizacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noHospitalizacionActionPerformed(evt);
            }
        });

        siAlertas.setBackground(new java.awt.Color(255, 255, 255));
        grupoAlertas.add(siAlertas);
        siAlertas.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        siAlertas.setText("Si");
        siAlertas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siAlertasActionPerformed(evt);
            }
        });

        noAlertas.setBackground(new java.awt.Color(255, 255, 255));
        grupoAlertas.add(noAlertas);
        noAlertas.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        noAlertas.setSelected(true);
        noAlertas.setText("No");
        noAlertas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noAlertasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel36Layout = new javax.swing.GroupLayout(jPanel36);
        jPanel36.setLayout(jPanel36Layout);
        jPanel36Layout.setHorizontalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel36Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel36Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(siMascotas, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                            .addComponent(siGuarderia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(siHClinica, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(siHospitalizacion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(siAlertas, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(siPeluqueria, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(noPeluqueria, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                    .addComponent(noHClinica, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(noMascotas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(noGuarderia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(noHospitalizacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(noAlertas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel36Layout.setVerticalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(siMascotas, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(noMascotas, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(siHClinica, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(noHClinica, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(noGuarderia, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(siGuarderia, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(siPeluqueria, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(noPeluqueria, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(noHospitalizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(siHospitalizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(siAlertas, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(noAlertas, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel37.setBackground(new java.awt.Color(255, 255, 255));

        jLabel98.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel98.setText("Reporte de mascotas");

        jLabel99.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel99.setText("Reporte de guarderia");

        jLabel100.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel100.setText("Reporte de peluqueria");

        jLabel101.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel101.setText("Reporte de hospitalización");

        jLabel104.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel104.setText("Formatos");

        javax.swing.GroupLayout jPanel37Layout = new javax.swing.GroupLayout(jPanel37);
        jPanel37.setLayout(jPanel37Layout);
        jPanel37Layout.setHorizontalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel99, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel98, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel100, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel101, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
                    .addComponent(jLabel104, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel37Layout.setVerticalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel98, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jLabel99, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jLabel100, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jLabel101, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jLabel104, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel38.setBackground(new java.awt.Color(255, 255, 255));

        siReporteMascotas.setBackground(new java.awt.Color(255, 255, 255));
        grupoReporteMascotas.add(siReporteMascotas);
        siReporteMascotas.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        siReporteMascotas.setText("Si");
        siReporteMascotas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siReporteMascotasActionPerformed(evt);
            }
        });

        noReporteMascotas.setBackground(new java.awt.Color(255, 255, 255));
        grupoReporteMascotas.add(noReporteMascotas);
        noReporteMascotas.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        noReporteMascotas.setSelected(true);
        noReporteMascotas.setText("No");
        noReporteMascotas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noReporteMascotasActionPerformed(evt);
            }
        });

        siReporteGuarderia.setBackground(new java.awt.Color(255, 255, 255));
        grupoReporteGuarderia.add(siReporteGuarderia);
        siReporteGuarderia.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        siReporteGuarderia.setText("Si");
        siReporteGuarderia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siReporteGuarderiaActionPerformed(evt);
            }
        });

        noReporteGuarderia.setBackground(new java.awt.Color(255, 255, 255));
        grupoReporteGuarderia.add(noReporteGuarderia);
        noReporteGuarderia.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        noReporteGuarderia.setSelected(true);
        noReporteGuarderia.setText("No");
        noReporteGuarderia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noReporteGuarderiaActionPerformed(evt);
            }
        });

        siReportePeluqueria.setBackground(new java.awt.Color(255, 255, 255));
        grupoReportePeluqueria.add(siReportePeluqueria);
        siReportePeluqueria.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        siReportePeluqueria.setText("Si");
        siReportePeluqueria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siReportePeluqueriaActionPerformed(evt);
            }
        });

        noReportePeluqueria.setBackground(new java.awt.Color(255, 255, 255));
        grupoReportePeluqueria.add(noReportePeluqueria);
        noReportePeluqueria.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        noReportePeluqueria.setSelected(true);
        noReportePeluqueria.setText("No");
        noReportePeluqueria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noReportePeluqueriaActionPerformed(evt);
            }
        });

        siReporteHospitalizacion.setBackground(new java.awt.Color(255, 255, 255));
        grupoReporteHospitalizacion.add(siReporteHospitalizacion);
        siReporteHospitalizacion.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        siReporteHospitalizacion.setText("Si");
        siReporteHospitalizacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siReporteHospitalizacionActionPerformed(evt);
            }
        });

        noReporteHospitalizacion.setBackground(new java.awt.Color(255, 255, 255));
        grupoReporteHospitalizacion.add(noReporteHospitalizacion);
        noReporteHospitalizacion.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        noReporteHospitalizacion.setSelected(true);
        noReporteHospitalizacion.setText("No");
        noReporteHospitalizacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noReporteHospitalizacionActionPerformed(evt);
            }
        });

        siReporteFormatos.setBackground(new java.awt.Color(255, 255, 255));
        grupoFormatos.add(siReporteFormatos);
        siReporteFormatos.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        siReporteFormatos.setText("Si");
        siReporteFormatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siReporteFormatosActionPerformed(evt);
            }
        });

        noReporteFormatos.setBackground(new java.awt.Color(255, 255, 255));
        grupoFormatos.add(noReporteFormatos);
        noReporteFormatos.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        noReporteFormatos.setSelected(true);
        noReporteFormatos.setText("No");
        noReporteFormatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noReporteFormatosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel38Layout = new javax.swing.GroupLayout(jPanel38);
        jPanel38.setLayout(jPanel38Layout);
        jPanel38Layout.setHorizontalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel38Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(siReportePeluqueria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(siReporteHospitalizacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(siReporteFormatos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(siReporteGuarderia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(siReporteMascotas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 4, Short.MAX_VALUE)
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(noReporteFormatos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(noReporteGuarderia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(noReporteMascotas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(noReportePeluqueria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(noReporteHospitalizacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10))
        );
        jPanel38Layout.setVerticalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel38Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(siReporteMascotas, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(noReporteMascotas, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(siReporteGuarderia, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(noReporteGuarderia, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(siReportePeluqueria, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(noReportePeluqueria, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(noReporteHospitalizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(siReporteHospitalizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(noReporteFormatos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(siReporteFormatos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jSeparator7.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator7.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel66, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jPanel35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                        .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                        .addComponent(jPanel37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel66)
                .addGap(5, 5, 5)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel37, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel38, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator7))
                .addContainerGap(162, Short.MAX_VALUE))
        );

        grupo.addTab("Veterinaria", jPanel7);

        pnlUsuario.setBackground(new java.awt.Color(255, 255, 255));
        pnlUsuario.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel1.setFont(new java.awt.Font("Century Gothic", 0, 22)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Usuario:");

        jLabel3.setFont(new java.awt.Font("Century Gothic", 0, 22)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Contraseña:");

        jLabel4.setFont(new java.awt.Font("Century Gothic", 0, 22)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Confirmarción");

        jLabel5.setFont(new java.awt.Font("Century Gothic", 0, 22)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Nombre:");

        txtNombre.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtNombre.setName("Nombre"); // NOI18N

        txtUsuario.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtUsuario.setName("Usuario"); // NOI18N
        txtUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUsuarioKeyReleased(evt);
            }
        });

        txtContra.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtContra.setName("Contraseña"); // NOI18N

        txtContra2.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
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
                .addContainerGap()
                .addGroup(pnlUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlUsuarioLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(pnlUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlUsuarioLayout.createSequentialGroup()
                        .addGroup(pnlUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(pnlUsuarioLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(pnlUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(5, 5, 5)))
                .addGroup(pnlUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNombre)
                    .addComponent(txtUsuario)
                    .addComponent(txtContra)
                    .addComponent(txtContra2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbFoto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlUsuarioLayout.setVerticalGroup(
            pnlUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUsuarioLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(pnlUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtUsuario)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(4, 4, 4)
                .addGroup(pnlUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNombre))
                .addGap(4, 4, 4)
                .addGroup(pnlUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtContra)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(4, 4, 4)
                .addGroup(pnlUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtContra2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(lbFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );

        pnlBotones.setBackground(new java.awt.Color(255, 255, 255));

        btnNuevo.setBackground(new java.awt.Color(204, 204, 204));
        btnNuevo.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/nuevo.png"))); // NOI18N
        btnNuevo.setText("NUEVO         ");
        btnNuevo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNuevo.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnNuevo.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnActualizar.setBackground(new java.awt.Color(93, 173, 226));
        btnActualizar.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/actualizar.png"))); // NOI18N
        btnActualizar.setText("MODIFICAR  ");
        btnActualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnActualizar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnActualizar.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnGuardar.setBackground(new java.awt.Color(46, 204, 113));
        btnGuardar.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
        btnGuardar.setText("GUARDAR       ");
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnGuardar.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnListarUsuarios.setBackground(new java.awt.Color(247, 220, 111));
        btnListarUsuarios.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnListarUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/listado.png"))); // NOI18N
        btnListarUsuarios.setText("LISTADO    ");
        btnListarUsuarios.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
                    .addComponent(btnNuevo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                    .addComponent(btnActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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

        btnOcultarInfoCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/boton-resta.png"))); // NOI18N
        btnOcultarInfoCliente.setToolTipText("Ctrl+M");
        btnOcultarInfoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOcultarInfoClienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(grupo)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(pnlUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnOcultarInfoCliente)))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(pnlUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pnlBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(btnOcultarInfoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grupo, javax.swing.GroupLayout.PREFERRED_SIZE, 553, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        txtUsuario.setEditable(true);

        for (int x = 0; x < pnlUsuario.getComponentCount(); x++) {
            if (pnlUsuario.getComponent(x) instanceof JTextField) {
                JTextField textField = (JTextField) pnlUsuario.getComponent(x);
                textField.setText("");
            }
        }

        for (int x = 0; x < pnlUsuario.getComponentCount(); x++) {
            if (pnlUsuario.getComponent(x) instanceof JPasswordField) {
                JPasswordField textField = (JPasswordField) pnlUsuario.getComponent(x);
                textField.setText("");
            }
        }

        origen = "";
        destino = "";

        noFacturas.setSelected(true);
        noCongeladas.setSelected(true);
        noCotizaciones.setSelected(true);
        noPlanSepares.setSelected(true);
        noCreditos.setSelected(true);
        noPedidos.setSelected(true);
        noOrdenServicio.setSelected(true);
        noAnulaciones.setSelected(true);
        noReimpresion.setSelected(true);
        noReporteVentas.setSelected(true);
        noReporteVentasAnuladas.setSelected(true);
        noReporteCotizaciones.setSelected(true);
        noReportePlanSepares.setSelected(true);
        noReporteCreditos.setSelected(true);
        noReporteOrdenServicio.setSelected(true);
        noReporteIvasVentas.setSelected(true);
        noReporteDia.setSelected(true);
        noCrearProductos.setSelected(true);
        noOrdenCompra.setSelected(true);
        noCompras.setSelected(true);
        noTrasladoExterno.setSelected(true);
        noTrasladoInterno.setSelected(true);
        noAjusteEntrada.setSelected(true);
        noAjusteSalida.setSelected(true);
        noGrupos.setSelected(true);
        noDiseños.setSelected(true);
        noArmados.setSelected(true);
        noInventarioInicial.setSelected(true);
        noTrasladoDetalle.setSelected(true);
        noKardex.setSelected(true);
        noReporteProductos.setSelected(true);
        noReportePrestamos.setSelected(true);
        noReporteCompras.setSelected(true);
        noReporteAjustes.setSelected(true);
        noReporteOrdenCompra.setSelected(true);
        noReporteInventario.setSelected(true);
        noReporteTrasladoInterno.setSelected(true);
        noReporteIvasCompras.setSelected(true);
        noReporteMovimientosSerial.setSelected(true);
        noCrearCliente.setSelected(true);
        noCrearProveedor.setSelected(true);
        noCrearEmpleado.setSelected(true);
        noBodegaInterna.setSelected(true);
        noClientesPrestamos.setSelected(true);
        noShowRoom.setSelected(true);
        noReporteCliente.setSelected(true);
        noReporteProveedor.setSelected(true);
        noReporteEmpleado.setSelected(true);
        noAbonosCxc.setSelected(true);
        noAbonosCxP.setSelected(true);
        noNotasCredito.setSelected(true);
        noReporteCarteraCxC.setSelected(true);
        noReporteAbonosCxC.setSelected(true);
        noReporteCarteraCxP.setSelected(true);
        noReporteAbonosCxP.setSelected(true);
        noReporteNotasCredito.setSelected(true);
        noCodigos.setSelected(true);
        noEgresos.setSelected(true);
        noBancos.setSelected(true);
        noCuadreCaja.setSelected(true);
        noReporteEgresos.setSelected(true);
        noReporteBancos.setSelected(true);
        noReporteCuadreCaja.setSelected(true);
        noRegistrarAgenda.setSelected(true);
        noReporteAgenda.setSelected(true);

        noMascotas.setSelected(true);
        noHClinica.setSelected(true);
        noGuarderia.setSelected(true);
        noPeluqueria.setSelected(true);
        noHospitalizacion.setSelected(true);
        noAlertas.setSelected(true);
        noReporteMascotas.setSelected(true);
        noReporteGuarderia.setSelected(true);
        noReportePeluqueria.setSelected(true);
        noReporteHospitalizacion.setSelected(true);
        noReporteFormatos.setSelected(true);

        noOrdenServicioMedica.setSelected(true);
        noHistoriaClinicaMedica.setSelected(true);
        noConvenio.setSelected(true);
        noFormatos.setSelected(true);
        noNotaEnfermeria.setSelected(true);
        noMedicamentos.setSelected(true);
        noReportesOrdenesMedicas.setSelected(true);
        noReporteFormulas.setSelected(true);
        noCertificadoEstudiante.setSelected(true);
        noCertificadoMedico.setSelected(true);
        noCertificadoOyS.setSelected(true);

        lbFoto.setIcon(null);
        lbFoto.repaint();
        txtUsuario.requestFocus();
        btnActualizar.setEnabled(false);
        btnGuardar.setEnabled(true);
        grupo.setSelectedIndex(0);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        Object[] campos = {txtNombre, txtUsuario, txtContra, txtContra2};
        String faltantes = metodos.camposVacios(campos);

        if (!faltantes.equals("")) {
            metodos.msgAdvertencia(null, "No puede continuar, faltan los siguientes campos: " + faltantes);
            grupo.setSelectedIndex(0);
            return;
        } else if (!txtContra.getText().equals(txtContra2.getText())) {
            metodos.msgAdvertencia(null, "Las contraseñas no coinciden");
            txtContra.requestFocus();
            grupo.setSelectedIndex(0);
            return;
        }

        if (metodos.msgPregunta(null, "¿Desea continuar?") == 0) {

            Object[] vector = {txtNombre.getText(), txtUsuario.getText(), txtContra.getText(),
                siFacturas.isSelected(), siCongeladas.isSelected(), siCotizaciones.isSelected(), siPlanSepares.isSelected(), siCreditos.isSelected(),
                siPedidos.isSelected(), siOrdenServicio.isSelected(), siAnulaciones.isSelected(), siReimpresion.isSelected(), siReporteVentas.isSelected(),
                siReporteVentasAnuladas.isSelected(), siReporteCotizaciones.isSelected(), siReportePlanSepares.isSelected(), siReporteCreditos.isSelected(),
                siReporteOrdenServicio.isSelected(), siReporteIvasVentas.isSelected(), siReporteDia.isSelected(), siCrearProductos.isSelected(), siOrdenCompra.isSelected(),
                siCompras.isSelected(), siTrasladoExterno.isSelected(), siTrasladoInterno.isSelected(), siAjusteEntrada.isSelected(), siAjusteSalida.isSelected(),
                siGrupos.isSelected(), siDiseños.isSelected(), siArmados.isSelected(), siInventarioInicial.isSelected(), siTrasladoDetalle.isSelected(), siKardex.isSelected(),
                siReporteProductos.isSelected(), siReportePrestamos.isSelected(), siReporteCompras.isSelected(), siReporteAjustes.isSelected(), siReporteOrdenCompra.isSelected(),
                siReporteInventario.isSelected(), siReporteTrasladoInterno.isSelected(), siReporteIvasCompras.isSelected(), siReporteMovimientosSerial.isSelected(),
                siCrearCliente.isSelected(), siCrearProveedor.isSelected(), siCrearEmpleado.isSelected(), siBodegaInterna.isSelected(), siClientesPrestamos.isSelected(),
                siShowRoom.isSelected(), siReporteCliente.isSelected(), siReporteProveedor.isSelected(), siReporteEmpleado.isSelected(), siAbonosCxc.isSelected(),
                siAbonosCxP.isSelected(), siNotasCredito.isSelected(), siReporteCarteraCxC.isSelected(), siReporteAbonosCxC.isSelected(), siReporteCarteraCxP.isSelected(),
                siReporteAbonosCxP.isSelected(), siReporteNotasCredito.isSelected(), siCodigos.isSelected(), siEgresos.isSelected(), siBancos.isSelected(), siCuadreCaja.isSelected(),
                siReporteEgresos.isSelected(), siReporteBancos.isSelected(), siReporteCuadreCaja.isSelected(), siRegistrarAgenda.isSelected(), siReporteAgenda.isSelected(),
                siMascotas.isSelected(), siHClinica.isSelected(), siGuarderia.isSelected(), siPeluqueria.isSelected(), siHospitalizacion.isSelected(), siAlertas.isSelected(),
                siReporteMascotas.isSelected(), siReporteGuarderia.isSelected(), siReportePeluqueria.isSelected(), siReporteHospitalizacion.isSelected(), siReporteFormatos.isSelected(),
                siOrdenServicioMedica.isSelected(), siHistoriaClinicaMedica.isSelected(), siFormatos.isSelected(), siConvenio.isSelected(), siNotaEnfermeria.isSelected(),
                siMedicamentos.isSelected(), siReportesOrdenesMedicas.isSelected(), siReporteFormulas.isSelected(), siCertificadoEstudiante.isSelected(), siCertificadoMedico.isSelected(),
                siCertificadoOyS.isSelected()
            };

            ndUsuario nodo = metodos.llenarUsuario(vector);

            if (!instancias.getSql().modificarUsuario(nodo)) {
                metodos.msgError(null, "Error al modificar el usuario");
                return;
            }

            if (!origen.equals("")) {
                metodos.montarFirma(origen, destino);
            } else if (lbFoto.getIcon() == null) {
                String origen = System.getProperty("user.dir") + "\\imagenes\\firmas\\ADMIN.jpg";
                String destino = System.getProperty("user.dir") + "\\imagenes\\firmas\\" + txtUsuario.getText() + ".jpg";
                metodosGenerales.copiarArchivo(origen, destino);

            }

            metodos.msgExito(null, "Usuario modificado con éxito");

            btnNuevoActionPerformed(evt);
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        Object[] campos = {txtNombre, txtUsuario, txtContra, txtContra2};
        String faltantes = metodos.camposVacios(campos);

        if (!faltantes.equals("")) {
            metodos.msgAdvertencia(null, "No puede continuar, faltan los siguientes campos: " + faltantes);
            grupo.setSelectedIndex(0);
            return;
        } else if (!txtContra.getText().equals(txtContra2.getText())) {
            metodos.msgAdvertencia(null, "Las contraseñas no coinciden");
            txtContra.requestFocus();
            grupo.setSelectedIndex(0);
            return;
        }

        if (metodos.msgPregunta(null, "¿Desea continuar?") == 0) {

            ndUsuario nodo = instancias.getSql().getDatosUsuario(txtUsuario.getText());

            if (nodo.getUsuario() != null) {
                metodos.msgError(null, "El usuario ya existe");
                txtUsuario.requestFocus();
                return;
            }

            Object[] vector = {txtUsuario.getText(), txtNombre.getText(), txtContra.getText(),
                siFacturas.isSelected(), siCongeladas.isSelected(), siCotizaciones.isSelected(), siPlanSepares.isSelected(), siCreditos.isSelected(),
                siPedidos.isSelected(), siOrdenServicio.isSelected(), siAnulaciones.isSelected(), siReimpresion.isSelected(), siReporteVentas.isSelected(),
                siReporteVentasAnuladas.isSelected(), siReporteCotizaciones.isSelected(), siReportePlanSepares.isSelected(), siReporteCreditos.isSelected(),
                siReporteOrdenServicio.isSelected(), siReporteIvasVentas.isSelected(), siReporteDia.isSelected(), siCrearProductos.isSelected(), siOrdenCompra.isSelected(),
                siCompras.isSelected(), siTrasladoExterno.isSelected(), siTrasladoInterno.isSelected(), siAjusteEntrada.isSelected(), siAjusteSalida.isSelected(),
                siGrupos.isSelected(), siDiseños.isSelected(), siArmados.isSelected(), siInventarioInicial.isSelected(), siTrasladoDetalle.isSelected(), siKardex.isSelected(),
                siReporteProductos.isSelected(), siReportePrestamos.isSelected(), siReporteCompras.isSelected(), siReporteAjustes.isSelected(), siReporteOrdenCompra.isSelected(),
                siReporteInventario.isSelected(), siReporteTrasladoInterno.isSelected(), siReporteIvasCompras.isSelected(), siReporteMovimientosSerial.isSelected(),
                siCrearCliente.isSelected(), siCrearProveedor.isSelected(), siCrearEmpleado.isSelected(), siBodegaInterna.isSelected(), siClientesPrestamos.isSelected(),
                siShowRoom.isSelected(), siReporteCliente.isSelected(), siReporteProveedor.isSelected(), siReporteEmpleado.isSelected(), siAbonosCxc.isSelected(),
                siAbonosCxP.isSelected(), siNotasCredito.isSelected(), siReporteCarteraCxC.isSelected(), siReporteAbonosCxC.isSelected(), siReporteCarteraCxP.isSelected(),
                siReporteAbonosCxP.isSelected(), siReporteNotasCredito.isSelected(), siCodigos.isSelected(), siEgresos.isSelected(), siBancos.isSelected(), siCuadreCaja.isSelected(),
                siReporteEgresos.isSelected(), siReporteBancos.isSelected(), siReporteCuadreCaja.isSelected(), siRegistrarAgenda.isSelected(), siReporteAgenda.isSelected(),
                siMascotas.isSelected(), siHClinica.isSelected(), siGuarderia.isSelected(), siPeluqueria.isSelected(), siHospitalizacion.isSelected(), siAlertas.isSelected(),
                siReporteMascotas.isSelected(), siReporteGuarderia.isSelected(), siReportePeluqueria.isSelected(), siReporteHospitalizacion.isSelected(), siReporteFormatos.isSelected(),
                siOrdenServicioMedica.isSelected(), siHistoriaClinicaMedica.isSelected(), siFormatos.isSelected(), siConvenio.isSelected(), siNotaEnfermeria.isSelected(),
                siMedicamentos.isSelected(), siReportesOrdenesMedicas.isSelected(), siReporteFormulas.isSelected(), siCertificadoEstudiante.isSelected(), siCertificadoMedico.isSelected(),
                siCertificadoOyS.isSelected()};

            nodo = metodos.llenarUsuario(vector);

            if (!instancias.getSql().agregarUsuario(nodo)) {
                metodos.msgError(null, "Hubo un problema al guardar el usuario");
                return;
            }
            if (!origen.equals("")) {
                metodos.montarFirma(origen, destino);
            } else {
//                if (lbFoto.getIcon() == null) {
                String origen = System.getProperty("user.dir") + "\\imagenes\\firmas\\ADMIN.jpg";
                String destino = System.getProperty("user.dir") + "\\imagenes\\firmas\\" + txtUsuario.getText() + ".jpg";
                metodosGenerales.copiarArchivo(origen, destino);

            }

            metodos.msgExito(null, "Usuario registrado con éxito");

            btnNuevoActionPerformed(evt);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void siFacturasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siFacturasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siFacturasActionPerformed

    private void noFacturasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noFacturasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noFacturasActionPerformed

    private void noReporteVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noReporteVentasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noReporteVentasActionPerformed

    private void siReporteVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siReporteVentasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siReporteVentasActionPerformed

    private void noCongeladasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noCongeladasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noCongeladasActionPerformed

    private void siCongeladasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siCongeladasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siCongeladasActionPerformed

    private void noCotizacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noCotizacionesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noCotizacionesActionPerformed

    private void siCotizacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siCotizacionesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siCotizacionesActionPerformed

    private void noReporteVentasAnuladasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noReporteVentasAnuladasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noReporteVentasAnuladasActionPerformed

    private void siReporteVentasAnuladasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siReporteVentasAnuladasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siReporteVentasAnuladasActionPerformed

    private void noPlanSeparesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noPlanSeparesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noPlanSeparesActionPerformed

    private void siPlanSeparesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siPlanSeparesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siPlanSeparesActionPerformed

    private void noReporteCotizacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noReporteCotizacionesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noReporteCotizacionesActionPerformed

    private void siReporteCotizacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siReporteCotizacionesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siReporteCotizacionesActionPerformed

    private void noReimpresionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noReimpresionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noReimpresionActionPerformed

    private void siReimpresionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siReimpresionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siReimpresionActionPerformed

    private void noCrearProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noCrearProductosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noCrearProductosActionPerformed

    private void siCrearProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siCrearProductosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siCrearProductosActionPerformed

    private void siOrdenCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siOrdenCompraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siOrdenCompraActionPerformed

    private void siAjusteSalidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siAjusteSalidaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siAjusteSalidaActionPerformed

    private void noAjusteSalidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noAjusteSalidaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noAjusteSalidaActionPerformed

    private void noOrdenCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noOrdenCompraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noOrdenCompraActionPerformed

    private void siComprasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siComprasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siComprasActionPerformed

    private void noComprasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noComprasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noComprasActionPerformed

    private void noCrearClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noCrearClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noCrearClienteActionPerformed

    private void siCrearClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siCrearClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siCrearClienteActionPerformed

    private void siReporteEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siReporteEmpleadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siReporteEmpleadoActionPerformed

    private void noReporteEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noReporteEmpleadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noReporteEmpleadoActionPerformed

    private void noReporteClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noReporteClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noReporteClienteActionPerformed

    private void siReporteClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siReporteClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siReporteClienteActionPerformed

    private void siBodegaInternaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siBodegaInternaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siBodegaInternaActionPerformed

    private void noBodegaInternaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noBodegaInternaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noBodegaInternaActionPerformed

    private void siCrearProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siCrearProveedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siCrearProveedorActionPerformed

    private void siTrasladoInternoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siTrasladoInternoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siTrasladoInternoActionPerformed

    private void noTrasladoInternoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noTrasladoInternoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noTrasladoInternoActionPerformed

    private void noCrearProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noCrearProveedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noCrearProveedorActionPerformed

    private void siCrearEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siCrearEmpleadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siCrearEmpleadoActionPerformed

    private void noCrearEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noCrearEmpleadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noCrearEmpleadoActionPerformed

    private void noReporteProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noReporteProveedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noReporteProveedorActionPerformed

    private void siReporteProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siReporteProveedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siReporteProveedorActionPerformed

    private void noAbonosCxcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noAbonosCxcActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noAbonosCxcActionPerformed

    private void siAbonosCxcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siAbonosCxcActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siAbonosCxcActionPerformed

    private void noReporteCarteraCxCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noReporteCarteraCxCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noReporteCarteraCxCActionPerformed

    private void siReporteCarteraCxCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siReporteCarteraCxCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siReporteCarteraCxCActionPerformed

    private void siAbonosCxPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siAbonosCxPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siAbonosCxPActionPerformed

    private void noAbonosCxPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noAbonosCxPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noAbonosCxPActionPerformed

    private void siNotasCreditoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siNotasCreditoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siNotasCreditoActionPerformed

    private void noNotasCreditoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noNotasCreditoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noNotasCreditoActionPerformed

    private void noReporteAbonosCxCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noReporteAbonosCxCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noReporteAbonosCxCActionPerformed

    private void siReporteAbonosCxCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siReporteAbonosCxCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siReporteAbonosCxCActionPerformed

    private void noCodigosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noCodigosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noCodigosActionPerformed

    private void siCodigosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siCodigosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siCodigosActionPerformed

    private void siEgresosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siEgresosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siEgresosActionPerformed

    private void noEgresosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noEgresosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noEgresosActionPerformed

    private void siBancosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siBancosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siBancosActionPerformed

    private void noBancosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noBancosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noBancosActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        JFileChooser chooser = new JFileChooser();
        ImagePreviewPanel preview = new ImagePreviewPanel();
        chooser.setAccessory(preview);
        chooser.addPropertyChangeListener(preview);

        FileNameExtensionFilter filtroImagen = new FileNameExtensionFilter("JPG", "jpg");
        chooser.setFileFilter(filtroImagen);

        int respuesta = chooser.showSaveDialog(this);

        if (respuesta == JFileChooser.APPROVE_OPTION) {
            Path FROM = Paths.get(chooser.getSelectedFile().getAbsolutePath());
            Path TO = Paths.get(System.getProperty("user.dir") + "\\imagenes\\firmas\\" + txtUsuario.getText() + ".jpg");

            //sobreescribir el fichero de destino, si existe, y copiar
            // los atributos, incluyendo los permisos rwx
//            CopyOption[] options = new CopyOption[]{
//                StandardCopyOption.REPLACE_EXISTING,
//                StandardCopyOption.COPY_ATTRIBUTES
//            };
            try {
//                Files.copy(FROM, TO, options);
                origen = FROM.toString();
                destino = TO.toString();

                ImageIcon fot = new ImageIcon(chooser.getSelectedFile().getAbsolutePath());
//                Icon icono = new ImageIcon(fot.getImage().getScaledInstance(200, 60, Image.SCALE_DEFAULT));
                Icon icono = new ImageIcon(fot.getImage().getScaledInstance(lbFoto.getWidth(), lbFoto.getHeight(), Image.SCALE_DEFAULT));
                lbFoto.setIcon(icono);
                this.repaint();

            } catch (Exception ex) {
                metodos.msgError(this, "Hubo un error al cargar el archivo");
            }

        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void siReporteAbonosCxPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siReporteAbonosCxPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siReporteAbonosCxPActionPerformed

    private void noReporteAbonosCxPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noReporteAbonosCxPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noReporteAbonosCxPActionPerformed

    private void siReporteCarteraCxPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siReporteCarteraCxPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siReporteCarteraCxPActionPerformed

    private void noReporteCarteraCxPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noReporteCarteraCxPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noReporteCarteraCxPActionPerformed

    private void siCreditosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siCreditosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siCreditosActionPerformed

    private void noCreditosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noCreditosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noCreditosActionPerformed

    private void siReportePlanSeparesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siReportePlanSeparesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siReportePlanSeparesActionPerformed

    private void noReportePlanSeparesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noReportePlanSeparesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noReportePlanSeparesActionPerformed

    private void siPedidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siPedidosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siPedidosActionPerformed

    private void noPedidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noPedidosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noPedidosActionPerformed

    private void siOrdenServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siOrdenServicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siOrdenServicioActionPerformed

    private void noOrdenServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noOrdenServicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noOrdenServicioActionPerformed

    private void siAnulacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siAnulacionesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siAnulacionesActionPerformed

    private void noAnulacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noAnulacionesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noAnulacionesActionPerformed

    private void siTrasladoExternoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siTrasladoExternoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siTrasladoExternoActionPerformed

    private void noTrasladoExternoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noTrasladoExternoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noTrasladoExternoActionPerformed

    private void noRegistrarAgendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noRegistrarAgendaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noRegistrarAgendaActionPerformed

    private void siRegistrarAgendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siRegistrarAgendaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siRegistrarAgendaActionPerformed

    private void siReporteAgendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siReporteAgendaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siReporteAgendaActionPerformed

    private void noReporteAgendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noReporteAgendaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noReporteAgendaActionPerformed

    private void siReporteCreditosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siReporteCreditosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siReporteCreditosActionPerformed

    private void noReporteCreditosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noReporteCreditosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noReporteCreditosActionPerformed

    private void siReporteOrdenServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siReporteOrdenServicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siReporteOrdenServicioActionPerformed

    private void noReporteOrdenServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noReporteOrdenServicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noReporteOrdenServicioActionPerformed

    private void siReporteIvasVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siReporteIvasVentasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siReporteIvasVentasActionPerformed

    private void noReporteIvasVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noReporteIvasVentasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noReporteIvasVentasActionPerformed

    private void txtUsuarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cargarUsuario(txtUsuario.getText());
        }
    }//GEN-LAST:event_txtUsuarioKeyReleased

    private void btnListarUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListarUsuariosActionPerformed
        buscUsuarios buscar = new buscUsuarios(null, rootPaneCheckingEnabled);
        buscar.setLocationRelativeTo(null);
        instancias.setBuscarUsuarios(buscar);
        instancias.setCampoActual(txtUsuario);
        txtUsuario.requestFocus();
        buscar.noEncontrado(txtUsuario.getText());
        buscar.show();
    }//GEN-LAST:event_btnListarUsuariosActionPerformed

    private void siReporteDiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siReporteDiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siReporteDiaActionPerformed

    private void noReporteDiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noReporteDiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noReporteDiaActionPerformed

    private void siGruposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siGruposActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siGruposActionPerformed

    private void noGruposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noGruposActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noGruposActionPerformed

    private void siDiseñosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siDiseñosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siDiseñosActionPerformed

    private void noDiseñosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noDiseñosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noDiseñosActionPerformed

    private void siArmadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siArmadosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siArmadosActionPerformed

    private void noArmadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noArmadosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noArmadosActionPerformed

    private void siInventarioInicialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siInventarioInicialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siInventarioInicialActionPerformed

    private void noInventarioInicialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noInventarioInicialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noInventarioInicialActionPerformed

    private void siTrasladoDetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siTrasladoDetalleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siTrasladoDetalleActionPerformed

    private void noTrasladoDetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noTrasladoDetalleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noTrasladoDetalleActionPerformed

    private void siKardexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siKardexActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siKardexActionPerformed

    private void noKardexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noKardexActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noKardexActionPerformed

    private void siReporteProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siReporteProductosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siReporteProductosActionPerformed

    private void noReporteProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noReporteProductosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noReporteProductosActionPerformed

    private void siReportePrestamosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siReportePrestamosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siReportePrestamosActionPerformed

    private void noReportePrestamosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noReportePrestamosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noReportePrestamosActionPerformed

    private void siReporteComprasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siReporteComprasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siReporteComprasActionPerformed

    private void noReporteComprasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noReporteComprasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noReporteComprasActionPerformed

    private void siReporteAjustesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siReporteAjustesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siReporteAjustesActionPerformed

    private void noReporteAjustesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noReporteAjustesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noReporteAjustesActionPerformed

    private void noReporteMovimientosSerialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noReporteMovimientosSerialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noReporteMovimientosSerialActionPerformed

    private void siReporteMovimientosSerialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siReporteMovimientosSerialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siReporteMovimientosSerialActionPerformed

    private void siReporteIvasComprasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siReporteIvasComprasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siReporteIvasComprasActionPerformed

    private void noReporteIvasComprasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noReporteIvasComprasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noReporteIvasComprasActionPerformed

    private void siReporteTrasladoInternoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siReporteTrasladoInternoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siReporteTrasladoInternoActionPerformed

    private void noReporteTrasladoInternoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noReporteTrasladoInternoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noReporteTrasladoInternoActionPerformed

    private void siReporteInventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siReporteInventarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siReporteInventarioActionPerformed

    private void noReporteInventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noReporteInventarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noReporteInventarioActionPerformed

    private void siReporteOrdenCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siReporteOrdenCompraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siReporteOrdenCompraActionPerformed

    private void noReporteOrdenCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noReporteOrdenCompraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noReporteOrdenCompraActionPerformed

    private void siClientesPrestamosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siClientesPrestamosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siClientesPrestamosActionPerformed

    private void noClientesPrestamosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noClientesPrestamosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noClientesPrestamosActionPerformed

    private void siShowRoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siShowRoomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siShowRoomActionPerformed

    private void noShowRoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noShowRoomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noShowRoomActionPerformed

    private void siReporteNotasCreditoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siReporteNotasCreditoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siReporteNotasCreditoActionPerformed

    private void noReporteNotasCreditoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noReporteNotasCreditoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noReporteNotasCreditoActionPerformed

    private void siCuadreCajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siCuadreCajaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siCuadreCajaActionPerformed

    private void noCuadreCajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noCuadreCajaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noCuadreCajaActionPerformed

    private void siReporteEgresosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siReporteEgresosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siReporteEgresosActionPerformed

    private void noReporteEgresosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noReporteEgresosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noReporteEgresosActionPerformed

    private void siReporteBancosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siReporteBancosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siReporteBancosActionPerformed

    private void noReporteBancosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noReporteBancosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noReporteBancosActionPerformed

    private void siReporteCuadreCajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siReporteCuadreCajaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siReporteCuadreCajaActionPerformed

    private void noReporteCuadreCajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noReporteCuadreCajaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noReporteCuadreCajaActionPerformed

    private void siAjusteEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siAjusteEntradaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siAjusteEntradaActionPerformed

    private void noAjusteEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noAjusteEntradaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noAjusteEntradaActionPerformed

    private void btnOcultarInfoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOcultarInfoClienteActionPerformed
        if (pnlBotones.isVisible()) {
            pnlBotones.setVisible(false);
            pnlUsuario.setVisible(false);
            btnOcultarInfoCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/boton-de-suma.png")));
        } else {
            pnlBotones.setVisible(true);
            pnlUsuario.setVisible(true);
            btnOcultarInfoCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/boton-resta.png")));
        }
    }//GEN-LAST:event_btnOcultarInfoClienteActionPerformed

    private void siMascotasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siMascotasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siMascotasActionPerformed

    private void noMascotasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noMascotasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noMascotasActionPerformed

    private void siHClinicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siHClinicaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siHClinicaActionPerformed

    private void noHClinicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noHClinicaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noHClinicaActionPerformed

    private void siGuarderiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siGuarderiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siGuarderiaActionPerformed

    private void noGuarderiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noGuarderiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noGuarderiaActionPerformed

    private void siPeluqueriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siPeluqueriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siPeluqueriaActionPerformed

    private void noPeluqueriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noPeluqueriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noPeluqueriaActionPerformed

    private void siHospitalizacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siHospitalizacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siHospitalizacionActionPerformed

    private void noHospitalizacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noHospitalizacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noHospitalizacionActionPerformed

    private void siAlertasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siAlertasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siAlertasActionPerformed

    private void noAlertasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noAlertasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noAlertasActionPerformed

    private void siReporteMascotasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siReporteMascotasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siReporteMascotasActionPerformed

    private void noReporteMascotasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noReporteMascotasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noReporteMascotasActionPerformed

    private void siReporteGuarderiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siReporteGuarderiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siReporteGuarderiaActionPerformed

    private void noReporteGuarderiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noReporteGuarderiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noReporteGuarderiaActionPerformed

    private void siReportePeluqueriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siReportePeluqueriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siReportePeluqueriaActionPerformed

    private void noReportePeluqueriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noReportePeluqueriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noReportePeluqueriaActionPerformed

    private void siReporteHospitalizacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siReporteHospitalizacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siReporteHospitalizacionActionPerformed

    private void noReporteHospitalizacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noReporteHospitalizacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noReporteHospitalizacionActionPerformed

    private void siReporteFormatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siReporteFormatosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siReporteFormatosActionPerformed

    private void noReporteFormatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noReporteFormatosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noReporteFormatosActionPerformed

    private void siOrdenServicioMedicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siOrdenServicioMedicaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siOrdenServicioMedicaActionPerformed

    private void noOrdenServicioMedicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noOrdenServicioMedicaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noOrdenServicioMedicaActionPerformed

    private void siReportesOrdenesMedicasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siReportesOrdenesMedicasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siReportesOrdenesMedicasActionPerformed

    private void noReportesOrdenesMedicasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noReportesOrdenesMedicasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noReportesOrdenesMedicasActionPerformed

    private void siHistoriaClinicaMedicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siHistoriaClinicaMedicaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siHistoriaClinicaMedicaActionPerformed

    private void noHistoriaClinicaMedicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noHistoriaClinicaMedicaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noHistoriaClinicaMedicaActionPerformed

    private void siFormatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siFormatosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siFormatosActionPerformed

    private void noFormatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noFormatosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noFormatosActionPerformed

    private void siConvenioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siConvenioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siConvenioActionPerformed

    private void noConvenioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noConvenioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noConvenioActionPerformed

    private void siNotaEnfermeriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siNotaEnfermeriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siNotaEnfermeriaActionPerformed

    private void noNotaEnfermeriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noNotaEnfermeriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noNotaEnfermeriaActionPerformed

    private void siMedicamentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siMedicamentosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siMedicamentosActionPerformed

    private void noMedicamentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noMedicamentosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noMedicamentosActionPerformed

    private void siReporteFormulasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siReporteFormulasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siReporteFormulasActionPerformed

    private void noReporteFormulasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noReporteFormulasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noReporteFormulasActionPerformed

    private void siCertificadoEstudianteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siCertificadoEstudianteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siCertificadoEstudianteActionPerformed

    private void noCertificadoEstudianteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noCertificadoEstudianteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noCertificadoEstudianteActionPerformed

    private void siCertificadoMedicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siCertificadoMedicoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siCertificadoMedicoActionPerformed

    private void noCertificadoMedicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noCertificadoMedicoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noCertificadoMedicoActionPerformed

    private void siCertificadoOySActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siCertificadoOySActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siCertificadoOySActionPerformed

    private void noCertificadoOySActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noCertificadoOySActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noCertificadoOySActionPerformed

    public void cargarUsuario(String usuario) {

        if (usuario.equals("")) {
            metodos.msgAdvertencia(null, "Debe ingresar el usuario");
        } else {

            ndUsuario nodo = instancias.getSql().getDatosUsuario(usuario);

            if (nodo.getUsuario() != null) {
                txtNombre.setText(nodo.getNombre());
                txtUsuario.setEditable(false);
                txtContra.setText(nodo.getContra());
                txtContra2.setText(nodo.getContra());

                siFacturas.setSelected(nodo.isFacturas());
                siCongeladas.setSelected(nodo.isCongeladas());
                siCotizaciones.setSelected(nodo.isCotizaciones());
                siPlanSepares.setSelected(nodo.isPlanSepares());
                siCreditos.setSelected(nodo.isCreditos());
                siPedidos.setSelected(nodo.isPedidos());
                siOrdenServicio.setSelected(nodo.isOrdenesServicio());
                siAnulaciones.setSelected(nodo.isAnulaciones());
                siReimpresion.setSelected(nodo.isReimpresion());
                siReporteVentas.setSelected(nodo.isReportesVentas());
                siReporteVentasAnuladas.setSelected(nodo.isReporteVentasAnuladas());
                siReporteCotizaciones.setSelected(nodo.isReporteCotizaciones());
                siReportePlanSepares.setSelected(nodo.isReportePlanSepares());
                siReporteCreditos.setSelected(nodo.isReporteCreditos());
                siReporteOrdenServicio.setSelected(nodo.isReporteOrdenesServicio());
                siReporteIvasVentas.setSelected(nodo.isReporteIvasVentas());
                siReporteDia.setSelected(nodo.isReporteDia());
                siCrearProductos.setSelected(nodo.isCreacionProductos());
                siOrdenCompra.setSelected(nodo.isOrdenesCompras());
                siCompras.setSelected(nodo.isCompras());
                siTrasladoExterno.setSelected(nodo.isTrasladosExternos());
                siTrasladoInterno.setSelected(nodo.isTrasladosInternos());
                siAjusteEntrada.setSelected(nodo.isAjustesEntrada());
                siAjusteSalida.setSelected(nodo.isAjustesSalida());
                siGrupos.setSelected(nodo.isGrupos());
                siDiseños.setSelected(nodo.isDisenos());
                siArmados.setSelected(nodo.isArmados());
                siInventarioInicial.setSelected(nodo.isInventarioInicial());
                siTrasladoDetalle.setSelected(nodo.isTrasladoDetalle());
                siKardex.setSelected(nodo.isKardex());
                siReporteProductos.setSelected(nodo.isReporteProductos());
                siReportePrestamos.setSelected(nodo.isReportePrestamos());
                siReporteCompras.setSelected(nodo.isReporteCompras());
                siReporteAjustes.setSelected(nodo.isReporteAjustes());
                siReporteOrdenCompra.setSelected(nodo.isReporteOrdenCompras());
                siReporteInventario.setSelected(nodo.isReporteInventario());
                siReporteTrasladoInterno.setSelected(nodo.isReporteTrasladoInterno());
                siReporteIvasCompras.setSelected(nodo.isReporteIvasCompras());
                siReporteMovimientosSerial.setSelected(nodo.isReporteMovimientoSerial());
                siCrearCliente.setSelected(nodo.isCrearClientes());
                siCrearProveedor.setSelected(nodo.isCrearProveedor());
                siCrearEmpleado.setSelected(nodo.isCrearEmpleado());
                siBodegaInterna.setSelected(nodo.isBodegasInternas());
                siClientesPrestamos.setSelected(nodo.isClientesPrestamos());
                siShowRoom.setSelected(nodo.isShowRoom());
                siReporteCliente.setSelected(nodo.isReporteClientes());
                siReporteProveedor.setSelected(nodo.isReporteProveedor());
                siReporteEmpleado.setSelected(nodo.isReporteEmpleados());
                siAbonosCxc.setSelected(nodo.isAbonosCxC());
                siAbonosCxP.setSelected(nodo.isAbonosCxP());
                siNotasCredito.setSelected(nodo.isNotasCredito());
                siReporteCarteraCxC.setSelected(nodo.isReporteCarteraCxC());
                siReporteAbonosCxC.setSelected(nodo.isReporteAbonosCxC());
                siReporteCarteraCxP.setSelected(nodo.isReporteCarteraCxP());
                siReporteAbonosCxP.setSelected(nodo.isReporteAbonosCxP());
                siReporteNotasCredito.setSelected(nodo.isReporteNotasCredito());
                siCodigos.setSelected(nodo.isCodigos());
                siEgresos.setSelected(nodo.isEgresos());
                siBancos.setSelected(nodo.isBancos());
                siCuadreCaja.setSelected(nodo.isCuadreCaja());
                siReporteEgresos.setSelected(nodo.isReporteEgresos());
                siReporteBancos.setSelected(nodo.isReporteBancos());
                siReporteCuadreCaja.setSelected(nodo.isReporteCuadreCaja());
                siRegistrarAgenda.setSelected(nodo.isRegistrarAgenda());
                siReporteAgenda.setSelected(nodo.isReportesAgenda());
                siMascotas.setSelected(nodo.isMascotas());
                siHClinica.setSelected(nodo.isHistoriaClinica());
                siGuarderia.setSelected(nodo.isGuarderia());
                siPeluqueria.setSelected(nodo.isPeluqueria());
                siHospitalizacion.setSelected(nodo.isHospitalizacion());
                siAlertas.setSelected(nodo.isAlertas());
                siReporteMascotas.setSelected(nodo.isReporteMascotas());
                siReporteGuarderia.setSelected(nodo.isReporteGuarderia());
                siReportePeluqueria.setSelected(nodo.isReportePeluqueria());
                siReporteHospitalizacion.setSelected(nodo.isReporteHospitalizacion());
                siReporteFormatos.setSelected(nodo.isFormatos());

                siOrdenServicioMedica.setSelected(nodo.isOrdenesServicioMedica());
                siHistoriaClinicaMedica.setSelected(nodo.isHistoria());
                siFormatos.setSelected(nodo.isFormatosMedicas());
                siConvenio.setSelected(nodo.isConvenio());
                siNotaEnfermeria.setSelected(nodo.isNotaEnfermaria());
                siMedicamentos.setSelected(nodo.isMedicamentos());
                siReporteFormulas.setSelected(nodo.isReporteFormulas());
                siReportesOrdenesMedicas.setSelected(nodo.isReporteOrdenesServicio());
                siCertificadoEstudiante.setSelected(nodo.isCertificadoEstudiante());
                siCertificadoMedico.setSelected(nodo.isCertificadoMedico());
                siCertificadoOyS.setSelected(nodo.isCertificadoOyS());

                txtNombre.requestFocus();
                btnActualizar.setEnabled(true);
                btnGuardar.setEnabled(false);

                jPanel8.setVisible(false);

                try {

                    ImageIcon fot = new ImageIcon(System.getProperty("user.dir") + "\\imagenes\\firmas\\" + txtUsuario.getText() + ".jpg");
                    Icon icono = new ImageIcon(fot.getImage().getScaledInstance(lbFoto.getWidth(), lbFoto.getHeight(), Image.SCALE_DEFAULT));
                    lbFoto.setIcon(icono);
                    this.repaint();

                } catch (Exception ex) {
                }

                return;
            }

            metodos.msgAdvertenciaAjustado(null, "El usuario no existe");
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnListarUsuarios;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnOcultarInfoCliente;
    private javax.swing.JTabbedPane grupo;
    private javax.swing.ButtonGroup grupoAbonosCuentasxCobrar;
    private javax.swing.ButtonGroup grupoAbonosCuentasxPagar;
    private javax.swing.ButtonGroup grupoAjusteEntrada;
    private javax.swing.ButtonGroup grupoAjusteSalida;
    private javax.swing.ButtonGroup grupoAlertas;
    private javax.swing.ButtonGroup grupoAnulaciones;
    private javax.swing.ButtonGroup grupoArmados;
    private javax.swing.ButtonGroup grupoBancos;
    private javax.swing.ButtonGroup grupoBodegasInternas;
    private javax.swing.ButtonGroup grupoCertificadoEstudiante;
    private javax.swing.ButtonGroup grupoCertificadoMedico;
    private javax.swing.ButtonGroup grupoCertificadoOyS;
    private javax.swing.ButtonGroup grupoClientesPrestamos;
    private javax.swing.ButtonGroup grupoCodigos;
    private javax.swing.ButtonGroup grupoCompras;
    private javax.swing.ButtonGroup grupoCongeladas;
    private javax.swing.ButtonGroup grupoConvenio;
    private javax.swing.ButtonGroup grupoCotizaciones;
    private javax.swing.ButtonGroup grupoCreacionProductos;
    private javax.swing.ButtonGroup grupoCrearClientes;
    private javax.swing.ButtonGroup grupoCrearEmpleado;
    private javax.swing.ButtonGroup grupoCrearProveedor;
    private javax.swing.ButtonGroup grupoCreditos;
    private javax.swing.ButtonGroup grupoCuadreCaja;
    private javax.swing.ButtonGroup grupoDiseños;
    private javax.swing.ButtonGroup grupoEgresos;
    private javax.swing.ButtonGroup grupoFacturas;
    private javax.swing.ButtonGroup grupoFormatos;
    private javax.swing.ButtonGroup grupoFormatosMedica;
    private javax.swing.ButtonGroup grupoGrupos;
    private javax.swing.ButtonGroup grupoGuarderia;
    private javax.swing.ButtonGroup grupoHistoriaClinica;
    private javax.swing.ButtonGroup grupoHistoriaClinicaMedica;
    private javax.swing.ButtonGroup grupoHospitalizacion;
    private javax.swing.ButtonGroup grupoInventarioInicial;
    private javax.swing.ButtonGroup grupoKardex;
    private javax.swing.ButtonGroup grupoMascotas;
    private javax.swing.ButtonGroup grupoMedicamentos;
    private javax.swing.ButtonGroup grupoNotaEnfermeria;
    private javax.swing.ButtonGroup grupoNotasCredito;
    private javax.swing.ButtonGroup grupoOrdenCompras;
    private javax.swing.ButtonGroup grupoOrdenServicioMedica;
    private javax.swing.ButtonGroup grupoOrdenesServicio;
    private javax.swing.ButtonGroup grupoPedidos;
    private javax.swing.ButtonGroup grupoPeluqueria;
    private javax.swing.ButtonGroup grupoPlanSepares;
    private javax.swing.ButtonGroup grupoRegistrarAgenda;
    private javax.swing.ButtonGroup grupoReimpresiones;
    private javax.swing.ButtonGroup grupoReporteAbonosCarteraCxC;
    private javax.swing.ButtonGroup grupoReporteAbonosCxP;
    private javax.swing.ButtonGroup grupoReporteAgenda;
    private javax.swing.ButtonGroup grupoReporteAjustes;
    private javax.swing.ButtonGroup grupoReporteBancos;
    private javax.swing.ButtonGroup grupoReporteCarteraCxC;
    private javax.swing.ButtonGroup grupoReporteCarteraCxP;
    private javax.swing.ButtonGroup grupoReporteCliente;
    private javax.swing.ButtonGroup grupoReporteCompras;
    private javax.swing.ButtonGroup grupoReporteCotizaciones;
    private javax.swing.ButtonGroup grupoReporteCreditos;
    private javax.swing.ButtonGroup grupoReporteCuadreCaja;
    private javax.swing.ButtonGroup grupoReporteDia;
    private javax.swing.ButtonGroup grupoReporteEgresos;
    private javax.swing.ButtonGroup grupoReporteEmpleados;
    private javax.swing.ButtonGroup grupoReporteFormulas;
    private javax.swing.ButtonGroup grupoReporteGuarderia;
    private javax.swing.ButtonGroup grupoReporteHospitalizacion;
    private javax.swing.ButtonGroup grupoReporteInventario;
    private javax.swing.ButtonGroup grupoReporteIvasCompras;
    private javax.swing.ButtonGroup grupoReporteIvasVentas;
    private javax.swing.ButtonGroup grupoReporteMascotas;
    private javax.swing.ButtonGroup grupoReporteMovimientosSerial;
    private javax.swing.ButtonGroup grupoReporteNotasCreditos;
    private javax.swing.ButtonGroup grupoReporteOrdenCompras;
    private javax.swing.ButtonGroup grupoReporteOrdenesMedica;
    private javax.swing.ButtonGroup grupoReporteOrdenesServicio;
    private javax.swing.ButtonGroup grupoReportePeluqueria;
    private javax.swing.ButtonGroup grupoReportePlanSepares;
    private javax.swing.ButtonGroup grupoReportePrestamos;
    private javax.swing.ButtonGroup grupoReporteProductos;
    private javax.swing.ButtonGroup grupoReporteProveedor;
    private javax.swing.ButtonGroup grupoReporteTrasladosInterno;
    private javax.swing.ButtonGroup grupoReporteVentas;
    private javax.swing.ButtonGroup grupoReporteVentasAnuladas;
    private javax.swing.ButtonGroup grupoShowRoom;
    private javax.swing.ButtonGroup grupoTrasladoDetalle;
    private javax.swing.ButtonGroup grupoTrasladoExterno;
    private javax.swing.ButtonGroup grupoTrasladoInterno;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
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
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JLabel lbFoto;
    private javax.swing.JRadioButton noAbonosCxP;
    private javax.swing.JRadioButton noAbonosCxc;
    private javax.swing.JRadioButton noAjusteEntrada;
    private javax.swing.JRadioButton noAjusteSalida;
    private javax.swing.JRadioButton noAlertas;
    private javax.swing.JRadioButton noAnulaciones;
    private javax.swing.JRadioButton noArmados;
    private javax.swing.JRadioButton noBancos;
    private javax.swing.JRadioButton noBodegaInterna;
    private javax.swing.JRadioButton noCertificadoEstudiante;
    private javax.swing.JRadioButton noCertificadoMedico;
    private javax.swing.JRadioButton noCertificadoOyS;
    private javax.swing.JRadioButton noClientesPrestamos;
    private javax.swing.JRadioButton noCodigos;
    private javax.swing.JRadioButton noCompras;
    private javax.swing.JRadioButton noCongeladas;
    private javax.swing.JRadioButton noConvenio;
    private javax.swing.JRadioButton noCotizaciones;
    private javax.swing.JRadioButton noCrearCliente;
    private javax.swing.JRadioButton noCrearEmpleado;
    private javax.swing.JRadioButton noCrearProductos;
    private javax.swing.JRadioButton noCrearProveedor;
    private javax.swing.JRadioButton noCreditos;
    private javax.swing.JRadioButton noCuadreCaja;
    private javax.swing.JRadioButton noDiseños;
    private javax.swing.JRadioButton noEgresos;
    private javax.swing.JRadioButton noFacturas;
    private javax.swing.JRadioButton noFormatos;
    private javax.swing.JRadioButton noGrupos;
    private javax.swing.JRadioButton noGuarderia;
    private javax.swing.JRadioButton noHClinica;
    private javax.swing.JRadioButton noHistoriaClinicaMedica;
    private javax.swing.JRadioButton noHospitalizacion;
    private javax.swing.JRadioButton noInventarioInicial;
    private javax.swing.JRadioButton noKardex;
    private javax.swing.JRadioButton noMascotas;
    private javax.swing.JRadioButton noMedicamentos;
    private javax.swing.JRadioButton noNotaEnfermeria;
    private javax.swing.JRadioButton noNotasCredito;
    private javax.swing.JRadioButton noOrdenCompra;
    private javax.swing.JRadioButton noOrdenServicio;
    private javax.swing.JRadioButton noOrdenServicioMedica;
    private javax.swing.JRadioButton noPedidos;
    private javax.swing.JRadioButton noPeluqueria;
    private javax.swing.JRadioButton noPlanSepares;
    private javax.swing.JRadioButton noRegistrarAgenda;
    private javax.swing.JRadioButton noReimpresion;
    private javax.swing.JRadioButton noReporteAbonosCxC;
    private javax.swing.JRadioButton noReporteAbonosCxP;
    private javax.swing.JRadioButton noReporteAgenda;
    private javax.swing.JRadioButton noReporteAjustes;
    private javax.swing.JRadioButton noReporteBancos;
    private javax.swing.JRadioButton noReporteCarteraCxC;
    private javax.swing.JRadioButton noReporteCarteraCxP;
    private javax.swing.JRadioButton noReporteCliente;
    private javax.swing.JRadioButton noReporteCompras;
    private javax.swing.JRadioButton noReporteCotizaciones;
    private javax.swing.JRadioButton noReporteCreditos;
    private javax.swing.JRadioButton noReporteCuadreCaja;
    private javax.swing.JRadioButton noReporteDia;
    private javax.swing.JRadioButton noReporteEgresos;
    private javax.swing.JRadioButton noReporteEmpleado;
    private javax.swing.JRadioButton noReporteFormatos;
    private javax.swing.JRadioButton noReporteFormulas;
    private javax.swing.JRadioButton noReporteGuarderia;
    private javax.swing.JRadioButton noReporteHospitalizacion;
    private javax.swing.JRadioButton noReporteInventario;
    private javax.swing.JRadioButton noReporteIvasCompras;
    private javax.swing.JRadioButton noReporteIvasVentas;
    private javax.swing.JRadioButton noReporteMascotas;
    private javax.swing.JRadioButton noReporteMovimientosSerial;
    private javax.swing.JRadioButton noReporteNotasCredito;
    private javax.swing.JRadioButton noReporteOrdenCompra;
    private javax.swing.JRadioButton noReporteOrdenServicio;
    private javax.swing.JRadioButton noReportePeluqueria;
    private javax.swing.JRadioButton noReportePlanSepares;
    private javax.swing.JRadioButton noReportePrestamos;
    private javax.swing.JRadioButton noReporteProductos;
    private javax.swing.JRadioButton noReporteProveedor;
    private javax.swing.JRadioButton noReporteTrasladoInterno;
    private javax.swing.JRadioButton noReporteVentas;
    private javax.swing.JRadioButton noReporteVentasAnuladas;
    private javax.swing.JRadioButton noReportesOrdenesMedicas;
    private javax.swing.JRadioButton noShowRoom;
    private javax.swing.JRadioButton noTrasladoDetalle;
    private javax.swing.JRadioButton noTrasladoExterno;
    private javax.swing.JRadioButton noTrasladoInterno;
    private javax.swing.JPanel pnlBotones;
    private javax.swing.JPanel pnlUsuario;
    private javax.swing.JRadioButton siAbonosCxP;
    private javax.swing.JRadioButton siAbonosCxc;
    private javax.swing.JRadioButton siAjusteEntrada;
    private javax.swing.JRadioButton siAjusteSalida;
    private javax.swing.JRadioButton siAlertas;
    private javax.swing.JRadioButton siAnulaciones;
    private javax.swing.JRadioButton siArmados;
    private javax.swing.JRadioButton siBancos;
    private javax.swing.JRadioButton siBodegaInterna;
    private javax.swing.JRadioButton siCertificadoEstudiante;
    private javax.swing.JRadioButton siCertificadoMedico;
    private javax.swing.JRadioButton siCertificadoOyS;
    private javax.swing.JRadioButton siClientesPrestamos;
    private javax.swing.JRadioButton siCodigos;
    private javax.swing.JRadioButton siCompras;
    private javax.swing.JRadioButton siCongeladas;
    private javax.swing.JRadioButton siConvenio;
    private javax.swing.JRadioButton siCotizaciones;
    private javax.swing.JRadioButton siCrearCliente;
    private javax.swing.JRadioButton siCrearEmpleado;
    private javax.swing.JRadioButton siCrearProductos;
    private javax.swing.JRadioButton siCrearProveedor;
    private javax.swing.JRadioButton siCreditos;
    private javax.swing.JRadioButton siCuadreCaja;
    private javax.swing.JRadioButton siDiseños;
    private javax.swing.JRadioButton siEgresos;
    private javax.swing.JRadioButton siFacturas;
    private javax.swing.JRadioButton siFormatos;
    private javax.swing.JRadioButton siGrupos;
    private javax.swing.JRadioButton siGuarderia;
    private javax.swing.JRadioButton siHClinica;
    private javax.swing.JRadioButton siHistoriaClinicaMedica;
    private javax.swing.JRadioButton siHospitalizacion;
    private javax.swing.JRadioButton siInventarioInicial;
    private javax.swing.JRadioButton siKardex;
    private javax.swing.JRadioButton siMascotas;
    private javax.swing.JRadioButton siMedicamentos;
    private javax.swing.JRadioButton siNotaEnfermeria;
    private javax.swing.JRadioButton siNotasCredito;
    private javax.swing.JRadioButton siOrdenCompra;
    private javax.swing.JRadioButton siOrdenServicio;
    private javax.swing.JRadioButton siOrdenServicioMedica;
    private javax.swing.JRadioButton siPedidos;
    private javax.swing.JRadioButton siPeluqueria;
    private javax.swing.JRadioButton siPlanSepares;
    private javax.swing.JRadioButton siRegistrarAgenda;
    private javax.swing.JRadioButton siReimpresion;
    private javax.swing.JRadioButton siReporteAbonosCxC;
    private javax.swing.JRadioButton siReporteAbonosCxP;
    private javax.swing.JRadioButton siReporteAgenda;
    private javax.swing.JRadioButton siReporteAjustes;
    private javax.swing.JRadioButton siReporteBancos;
    private javax.swing.JRadioButton siReporteCarteraCxC;
    private javax.swing.JRadioButton siReporteCarteraCxP;
    private javax.swing.JRadioButton siReporteCliente;
    private javax.swing.JRadioButton siReporteCompras;
    private javax.swing.JRadioButton siReporteCotizaciones;
    private javax.swing.JRadioButton siReporteCreditos;
    private javax.swing.JRadioButton siReporteCuadreCaja;
    private javax.swing.JRadioButton siReporteDia;
    private javax.swing.JRadioButton siReporteEgresos;
    private javax.swing.JRadioButton siReporteEmpleado;
    private javax.swing.JRadioButton siReporteFormatos;
    private javax.swing.JRadioButton siReporteFormulas;
    private javax.swing.JRadioButton siReporteGuarderia;
    private javax.swing.JRadioButton siReporteHospitalizacion;
    private javax.swing.JRadioButton siReporteInventario;
    private javax.swing.JRadioButton siReporteIvasCompras;
    private javax.swing.JRadioButton siReporteIvasVentas;
    private javax.swing.JRadioButton siReporteMascotas;
    private javax.swing.JRadioButton siReporteMovimientosSerial;
    private javax.swing.JRadioButton siReporteNotasCredito;
    private javax.swing.JRadioButton siReporteOrdenCompra;
    private javax.swing.JRadioButton siReporteOrdenServicio;
    private javax.swing.JRadioButton siReportePeluqueria;
    private javax.swing.JRadioButton siReportePlanSepares;
    private javax.swing.JRadioButton siReportePrestamos;
    private javax.swing.JRadioButton siReporteProductos;
    private javax.swing.JRadioButton siReporteProveedor;
    private javax.swing.JRadioButton siReporteTrasladoInterno;
    private javax.swing.JRadioButton siReporteVentas;
    private javax.swing.JRadioButton siReporteVentasAnuladas;
    private javax.swing.JRadioButton siReportesOrdenesMedicas;
    private javax.swing.JRadioButton siShowRoom;
    private javax.swing.JRadioButton siTrasladoDetalle;
    private javax.swing.JRadioButton siTrasladoExterno;
    private javax.swing.JRadioButton siTrasladoInterno;
    private javax.swing.JPasswordField txtContra;
    private javax.swing.JPasswordField txtContra2;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
