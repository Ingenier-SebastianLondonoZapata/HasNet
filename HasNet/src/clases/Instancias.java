package clases;

import Vista.Productos.VistaAjusteInventario;
import Vista.Productos.VistaInventarioInicial;
import Vista.Aterrizaje.vistaMenu;
import Vista.Ventas.VistaFactura;
import Vista.Productos.ordenCompra;
import Vista.Productos.ingreso;
import Vista.Productos.VistaIngreso;
import Vista.Tesoreria.vistaCodigosEgresos;
import Vista.Tesoreria.vistaEgresos;
import Vista.Terceros.vistaContactos;
import Vista.Ventas.infMesas;
import Vista.Ventas.vistaAnulacion;
import Vista.Ventas.VistaDocumentos;
import Vista.Cartera.VistaNotaDebito;
import Vista.Cartera.vistaNotaCredito;
import Vista.Configuraciones.vistaMaestra;
import Modelo.Maestra.modeloConfiguracion;
import Utilidades.BaseDatos.SQL;
import Vista.Configuraciones.vistaSuperMaestra;
import Controlador.BarraProceso.jcThread;
import Utilidades.BaseDatos.SQL1;
import Utilidades.Constantes;
import Vista.Productos.VistaProductos;
import formularios.Parqueadero.*;

import formularios.*;
import configuracion.*;
import formularios.Agenda.*;
import formularios.Cartera.*;
import formularios.Tesoreria.*;
import formularios.Genericos.*;
import formularios.Medico.*;
import formularios.Ventas.*;
import formularios.creditos.*;
import formularios.productos.*;
import formularios.recordatorios.*;
import formularios.terceros.*;
import formularios.Veterinario.*;
import formularios.Labotario.*;
import formularios.Oftalmologia.*;
import java.math.BigDecimal;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Instancias {

    private static Instancias instancias;

    private SQL sql;
    private SQL1 sqlPagos;

    Instancias() {
        menu = null;
        factura = null;
        campoActual = null;

        sql = new SQL();
        sql.obtenerConexion(Constantes.BASE_DATOS_PRINCIPAL, false);

        sqlPagos = new SQL1();
        sqlPagos.obtenerConexion();

        reporte = new iFactura(this);
    }

    public static void cargarConfiguraciones() {
        SQL sql = new SQL();
        instancias.setConfigFactura(new SQL().getDatosConfiguracion());
    }

    public static Instancias getInstancias() {
        if (instancias == null) {
            instancias = new Instancias();
        }
        return instancias;
    }

    public SQL1 getSqlPagos() {
        return sqlPagos;
    }

    private String prodCambio;
    private Double cantCambio;

    Boolean cancelarFactura = false;

    private JFrame ingreso;
    private vistaMenu menu;

    private vistaAnulacion anula;
    private JTextField campoActual;

    private String valor;
    private String titulo;
    private String usuario;

    private vistaMaestra maestra;

    //REPORTES SIN METODOS ESPECIALES 
    private JInternalFrame repMascotas, repAbonos, repAbonosCxp, invBodegas, repClientes, repBodegas, repEmpleados;

    private infUsuarios usuarios;
    private ndUsuario usuarioLog;
    private infCosteo costeo;
    private String tFactura;
    private infArmado armado;
    private infProcesos procesos;
    private buscProcesos buscProcesos;
    private iFactura reporte;
    private jcThread progres;
    private String rutaAguardar;
    private infEmpresas empresas;
    private infRecordatorios recordatorios;
    private buscServicios buscServicios;

    private infRepCuadre repCuadre;
    private infReportesProductos reportesProductos;
    private buscGrupos buscGrupos;
    private buscSubGrupos buscSubGrupos;
    private String regimen;
    private String terminal;
    private String descuento;

    private String segundaClave;
    private BigDecimal devuelta = BigDecimal.ZERO;
    private String informacionEmpresa;

    private String tipoImpresion;
    private String impresion;

    private formularios.Ventas.infOrdenServicio ordenServicio;
    private String tituloFactura;

    private String diasAlertaResolucion;
    private String diasRestantesResolucion;
    private String alertaFechaDias;
    private String alertaCantidadDias;
    private String alertaPromedioDias;
    public String resolucion;
    private String informacionEmpresaCompleto;
    private String informacionEmpresaReimpresion;

    private infAlertasCumpleanos alertas;
    private String diasCobrarMora;
    private String porcentajeMora;
    private buscReporteFacturaPedido reporteFacturaPedido;
    private dlgPagosProveedores pagosProveedores;
    private buscHorasAgenda buscarHorasAgenda;
    private String[] configAgenda;
    private infInfoAgenda infoAgenda;
    private infRepAgenda repAgenda;
    private infRepFormulas repFormulaMedica;

    private buscAyudaDiagnostico buscAyudaDiagnostico;
    private infHistoriaMedica historiaClinica;
    private infAyudaDiagnostico ayudaDiagnostica;
    private buscEpsPrecargadas buscEpsPre;
    private buscMedicamentos buscMedicamentos;
    private infHistoriaC historiaC;
    private infFormulaMedica formulaMedica;
    private infIncapacidad incapacidad;
    private infRemision remision;
    private infContraremision contraremision;
    private buscAntecedentes buscAntecedentes;

    private infAutorizacionServicios autorizacionServicios;

    private buscOcupaciones buscarOcupaciones;

    private buscPlantillaDeTextos buscPlantillaDeTextos;
    private JTextArea campoActualTextArea;
    private infRepOrdenCompra repOrdenCompra;

    private buscUsuarios buscarUsuarios;
    private buscProblemas buscarProblemas;

    private infPlantillas plantillas;

    /* DEVOLUCIÓN DEL DINERO */
    private BigDecimal efectivoDevuelta = big.getBigDecimal("0"), tarjetaDevuelta = big.getBigDecimal("0"),
            chequeDevuelta = big.getBigDecimal("0"), ncDevuelta = big.getBigDecimal("0"), valorComision = big.getBigDecimal("0"),
            totalFacturaComision = big.getBigDecimal("0"), totalPropina = big.getBigDecimal("0"), tarjetaCredito = big.getBigDecimal("0"),
            propina = big.getBigDecimal("0");

    /* OPCIONES DE MAESTRA */
    private BigDecimal descuentoMaximoVentas;
    private String franquisia = "", comision = "", porcPropina = "", pie, legal;
    private boolean imprimirCuadreFiscal, ocultarInformacionCliente, visualizarTodasLasFacturas, mostrarInformacionCuadre, generaOrdenMedica, imprimirOrdenMedica,
            imprimirFacturaOrdenMedica, copias, lector, ventasPredeterminado, mensajeUtilidad, pvpConIva, costoConIva, pvpConImpoconsumo, costoConImpoconsumo,
            imprimirRecogida, utilidad, ubicacion, hora;

    /* PREFIJOS DOCUMENTOS */
    private String idAbono, idEgreso, idNC, idND;

    /* INICIO VETERINARIO */
    private buscHoraHospitalizacion buscHorasHospitalizacion;
    private infRemisionVeterinaria remisionVeterinaria;
    private infNotaMedicaVeterinaria notaMedicaVeterinaria;
    private infMascotas mascotas;
    private infGuarderia guarderia;
    private infAyudaDiagnosticoVeterinaria ayudaDiagnosticoVeterinaria;
    private infFormulaMedicaVeterinaria formulaMedicaVeterinaria;
    private infQuirurgica quirurgica;
    private infHistoria historia;
    private infIngresoHospitalizacion ingresoHospitalizacion;
    private infHospitalizacion hospitalizacion;
    private buscRazas buscRazas;
    private buscTemperamento buscTemperamento;
    private buscMascotas buscMascotas;
    private buscAyudaDiagnosticoVeterinaria buscAyudaDiagnosticoVeterinaria;
    private buscMedicamentosVeterinario buscMedicamentosVeterinario;
    private infPeluqueria peluqueria;
    private infPeluqueriaInfo infoPeluqueria;
    private buscHorasPeluqueria buscarHorasPeluqueria;
    private infRepPeluqueria repPeluqueria;
    private infRepGuarderia repGuarderia;
    private infRepHospitalizacion repHospitalizacion;
    private infFormatos formatos;
    private infAlertasProximas alertasProximas;
    private infRepAlertasProximas repAlertasProximas;

    /* INICIO MEDICO */
    private infFormatosDeTextos plantillasDeTextos;
    private buscCups buscCups;
    private infRips rips;
    private buscConvenio buscConvenio;
    private infEps eps;
    private infCertificadoMedico certificadoMedico;
    private infCertificadoMedicoEstudiante certificadoMedicoEstudiante;
    private infCertificadoMedicoOys certificadoMedicoOys;
    private infReportesMedico reportesMedicos;
    private infOrdenServicioMedico ordenMedica;
    private infConvenio convenio;
    private infNotaEnfermeria notaEnfermeria;
    private infMedicamentos medicamentos;
    private infRepOrdenesMedicas repMedico;

    /* INICIO TERCEROS */
    private vistaContactos clientes;
    private vistaContactos paciente;
    private infBodegas bodegas;
    private infBodegasClientes bodegasClientes;
    private buscBodegas buscBodegas;
    private buscClientes busClientes;
    private buscEmpleados buscEmpleados;
    private infEmpleados empleados;

    /* INICIO VENTAS */
    private infPreparacion preparacion;
    private infResumenDia resumenDia;
    private infIndicadoresVentas indicadoresVentas;
    private infFacturarLotes facturarLotes;
    private infCaja caja;
    private infPedido pedido;
    private infPlanSepare planSepare;
    private infFacturaCreditos facturaCreditos;
    private infFactura factura;
    private infCuentaCobro cuentaCobro;
    private infReportesVentas reportesVentas;
    private infRepCreditos repCreditos;
    private infRepSepares repSepares;
    private infRepVentas repVentas;
    private infRepIvas repIvas;
    private infRepCotizas repCotizas;
    private infRepCuentaCobro repCuentaCobro;
    private infMesa mesa;
    private infRepAnulas repAnulas;
    private infCotiza cotiza;
    private infMesas mesas;
    private infMesas1 mesas1;
    private buscTipoVehiculo buscTipoVehiculo;
    private VistaDocumentos reimpresion;

    /* INICIO PRODUCTOS */
    private infInventario inventario;
    private VistaProductos productos;
    private infGrupos grupos;
    private infKardexProductos kardexProductos;
    private buscProductos buscProductos;
    private buscSeriales buscSeriales;
    private buscColores buscColores;
    private buscTallas buscTallas;
    private buscMedidas buscMedidas;
    private buscMarcas buscMarcas;
    private buscTiposProductos buscTiposProductos;
    private infPrestamos prestamos;
    private infTrasladosInternos trasladosInternos;
    private infRepProductos repProductos;
    private infRepIvasCompras repIvasCompras;
    private infMovimientosSeriales movimientosSeriales;
    private infRepIngresos repIngresos;
    private infRepAjustes repAjustes;
    private infRepTraslados repTraslados;
    private infRepTrasladosInternos repTrasladosInternos;
    private VistaAjusteInventario uInt;
    private VistaInventarioInicial inventarioInicial;
    private ingreso ingresos;
    private ordenCompra ordenCompra;

    /* INICIO TESORERIA */
    private vistaEgresos egresos;
    private vistaCodigosEgresos codigosEgresos;
    private infRepEgresos repEgresos;
    private buscEgresos buscarEgresos;
    private infBancos bancos;
    private infRepBancos repBancos;

    /* INICIO LABORATORIO */
    private infAcidoUrico acidoUrico;
    private infAntiestreptolisina antiestreptosilina;
    private infCannabinoides cannabinoides;
    private infGlicemia glicemia;
    private infPerfil perfil;
    private infProteina proteina;
    private infSerologia serologia;
    private infDirecto directos;
    private infTiempos tiempos;
    private infAcidos acidos;
    private infEmbarazo embarazo;
    private infKoh koh;
    private infCreatinina creatinina;
    private infHemograma hemograma;
    private infSangreOculta sangreOculta;
    private infHemoglobina hemoglobina;
    private infCitoquimico citoquimico;
    private infCocaina cocaina;
    private infCoprologico coprologico;
    private infCoprograma coprograma;
    private infColesterol colesterol;
    private infHemoclasificacion hemoclasificacion;
    private infHistoriaLaboratorio historiaLaboratorio;

    /* INICIO PARQUEADERO */
    private buscPlacas buscPlacas;
    private buscPlaca1 buscPlacas1;
    private infParqueadero parqueadero;
    private infLavadero lavadero;
    private infMensualidad mensualidad;
    private infRepMensualidades repMensualidades;
    private infRepLavadero repLavadero;
    private infRepLavadero1 repLavadero1;
    private infRepParqueadero repParqueadero;
    private infRepHistorial repHistorial;
    private Boolean procesoCompletoCascos = false;

    /* INICIO OFTALMOLOGIA */
    private infAyudaDiagnosticoOf ayudaDiagnosticoOf;
    private infBiometria biometria;
    private infFormulaLentesOf formulaLentes;
    private infFormulaMedicaOf formulaMedicaOf;
    private infHojaIngreso hojaIngreso;
    private infIncapacidadOf incapacidadOf;
    private infPaquimetria paquimetria;
    private String cita;

    /* INICIO CARTERA */
    private vistaNotaCredito nc;
    private VistaNotaDebito nd;
    private infRepNc repNC;
    private infPagos pagos;
    private infRepPagos repPagos;
    private infRepCartera repCartera;
    private infAbonos abonos;

    /* INICIO AGENDA */
    private infAgendaConsulta agendaConsulta;
    private busAyudaDiagnostico ayudaDiagnostico;
    private buscMedicamentosOf buscMedicamentos1;
    private String medico;

    /* INICIO CONFIGURACIONES */
    private modeloConfiguracion configFactura;

    /* TIPO MONEDA */
    private String simbolo;
    private String descripcionSimbolo;
    private String cadenaDecimales;

    /* DATOS FACTURACION ELECTRONICA */
    private String nitEmisor;
    private String tipoDocumentoEmisor;
    private String tokenEmisor;
    private int canalFacturacion;
    private boolean pruebasFacturacionElectronica = false;
    private boolean validacionesCorrectas = false;

    public String getTipoDocumentoEmisor() {
        return tipoDocumentoEmisor;
    }

    public void setTipoDocumentoEmisor(String tipoDocumentoEmisor) {
        this.tipoDocumentoEmisor = tipoDocumentoEmisor;
    }

    public int getCanalFacturacion() {
        return canalFacturacion;
    }

    public void setCanalFacturacion(int canalFacturacion) {
        this.canalFacturacion = canalFacturacion;
    }

    public boolean isPruebasFacturacionElectronica() {
        return pruebasFacturacionElectronica;
    }

    public void setEsPruebasFacturacionElectronica(boolean esPruebasFacturacionElectronica) {
        this.pruebasFacturacionElectronica = esPruebasFacturacionElectronica;
    }

    public boolean isValidacionesCorrectas() {
        return validacionesCorrectas;
    }

    public void setValidacionesCorrectas(boolean validacionesCorrectas) {
        this.validacionesCorrectas = validacionesCorrectas;
    }

    public String getDescripcionSimbolo() {
        return descripcionSimbolo;
    }

    public void setDescripcionSimbolo(String descripcionSimbolo) {
        this.descripcionSimbolo = descripcionSimbolo;
    }

    public String getNitEmisor() {
        return nitEmisor;
    }

    public void setNitEmisor(String nitEmisor) {
        this.nitEmisor = nitEmisor;
    }

    public String getTokenEmisor() {
        return tokenEmisor;
    }

    public void setTokenEmisor(String tokenEmisor) {
        this.tokenEmisor = tokenEmisor;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    public String getCadenaDecimales() {
        return cadenaDecimales;
    }

    public void setCadenaDecimales(String cadenaDecimales) {
        this.cadenaDecimales = cadenaDecimales;
    }

    public String getIdNC() {
        return idNC;
    }

    public void setIdNC(String idNC) {
        this.idNC = idNC;
    }

    public String getIdND() {
        return idND;
    }

    public void setIdND(String idND) {
        this.idND = idND;
    }

    public VistaNotaDebito getNd() {
        return nd;
    }

    public void setNd(VistaNotaDebito nd) {
        this.nd = nd;
    }

    public infRepCuentaCobro getRepCuentaCobro() {
        return repCuentaCobro;
    }

    public void setRepCuentaCobro(infRepCuentaCobro repCuentaCobro) {
        this.repCuentaCobro = repCuentaCobro;
    }

    public Boolean getProcesoCompletoCascos() {
        return procesoCompletoCascos;
    }

    public void setProcesoCompletoCascos(Boolean procesoCompletoCascos) {
        this.procesoCompletoCascos = procesoCompletoCascos;
    }

    public infPlantillas getPlantillas() {
        return plantillas;
    }

    public void setPlantillas(infPlantillas plantillas) {
        this.plantillas = plantillas;
    }

    public boolean isPvpConImpoconsumo() {
        return pvpConImpoconsumo;
    }

    public void setPvpConImpoconsumo(boolean pvpConImpoconsumo) {
        this.pvpConImpoconsumo = pvpConImpoconsumo;
    }

    public boolean isCostoConImpoconsumo() {
        return costoConImpoconsumo;
    }

    public void setCostoConImpoconsumo(boolean costoConImpoconsumo) {
        this.costoConImpoconsumo = costoConImpoconsumo;
    }

    public infRepAlertasProximas getRepAlertasProximas() {
        return repAlertasProximas;
    }

    public void setRepAlertasProximas(infRepAlertasProximas repAlertasProximas) {
        this.repAlertasProximas = repAlertasProximas;
    }

    public infAlertasProximas getAlertasProximas() {
        return alertasProximas;
    }

    public void setAlertasProximas(infAlertasProximas alertasProximas) {
        this.alertasProximas = alertasProximas;
    }

    public infFormatos getFormatos() {
        return formatos;
    }

    public void setFormatos(infFormatos formatos) {
        this.formatos = formatos;
    }

    public infRepHospitalizacion getRepHospitalizacion() {
        return repHospitalizacion;
    }

    public void setRepHospitalizacion(infRepHospitalizacion repHospitalizacion) {
        this.repHospitalizacion = repHospitalizacion;
    }

    public infReportesMedico getReportesMedicos() {
        return reportesMedicos;
    }

    public void setReportesMedicos(infReportesMedico reportesMedicos) {
        this.reportesMedicos = reportesMedicos;
    }

    public infRepPeluqueria getRepPeluqueria() {
        return repPeluqueria;
    }

    public void setRepPeluqueria(infRepPeluqueria repPeluqueria) {
        this.repPeluqueria = repPeluqueria;
    }

    public infRepGuarderia getRepGuarderia() {
        return repGuarderia;
    }

    public void setRepGuarderia(infRepGuarderia repGuarderia) {
        this.repGuarderia = repGuarderia;
    }

    public buscTallas getBuscTallas() {
        return buscTallas;
    }

    public void setBuscTallas(buscTallas buscTallas) {
        this.buscTallas = buscTallas;
    }

    public buscHoraHospitalizacion getBuscHorasHospitalizacion() {
        return buscHorasHospitalizacion;
    }

    public void setBuscHorasHospitalizacion(buscHoraHospitalizacion buscHorasHospitalizacion) {
        this.buscHorasHospitalizacion = buscHorasHospitalizacion;
    }

    public infGuarderia getGuarderia() {
        return guarderia;
    }

    public void setGuarderia(infGuarderia guarderia) {
        this.guarderia = guarderia;
    }

    public buscTipoVehiculo getBuscTipoVehiculo() {
        return buscTipoVehiculo;
    }

    public void setBuscTipoVehiculo(buscTipoVehiculo buscTipoVehiculo) {
        this.buscTipoVehiculo = buscTipoVehiculo;
    }

    public infPeluqueriaInfo getInfoPeluqueria() {
        return infoPeluqueria;
    }

    public void setInfoPeluqueria(infPeluqueriaInfo infoPeluqueria) {
        this.infoPeluqueria = infoPeluqueria;
    }

    public infPeluqueria getPeluqueria() {
        return peluqueria;
    }

    public void setPeluqueria(infPeluqueria peluqueria) {
        this.peluqueria = peluqueria;
    }

    public infIndicadoresVentas getIndicadoresVentas() {
        return indicadoresVentas;
    }

    public void setIndicadoresVentas(infIndicadoresVentas indicadoresVentas) {
        this.indicadoresVentas = indicadoresVentas;
    }

    public buscSeriales getBuscSeriales() {
        return buscSeriales;
    }

    public void setBuscSeriales(buscSeriales buscSeriales) {
        this.buscSeriales = buscSeriales;
    }

    public infMovimientosSeriales getMovimientosSeriales() {
        return movimientosSeriales;
    }

    public void setMovimientosSeriales(infMovimientosSeriales movimientosSeriales) {
        this.movimientosSeriales = movimientosSeriales;
    }

    public infIngresoHospitalizacion getIngresoHospitalizacion() {
        return ingresoHospitalizacion;
    }

    public void setIngresoHospitalizacion(infIngresoHospitalizacion ingresoHospitalizacion) {
        this.ingresoHospitalizacion = ingresoHospitalizacion;
    }

    public infNotaMedicaVeterinaria getNotaMedicaVeterinaria() {
        return notaMedicaVeterinaria;
    }

    public void setNotaMedicaVeterinaria(infNotaMedicaVeterinaria notaMedicaVeterinaria) {
        this.notaMedicaVeterinaria = notaMedicaVeterinaria;
    }

    public buscMedicamentosVeterinario getBuscMedicamentosVeterinario() {
        return buscMedicamentosVeterinario;
    }

    public void setBuscMedicamentosVeterinario(buscMedicamentosVeterinario buscMedicamentosVeterinario) {
        this.buscMedicamentosVeterinario = buscMedicamentosVeterinario;
    }

    public buscAyudaDiagnosticoVeterinaria getBuscAyudaDiagnosticoVeterinaria() {
        return buscAyudaDiagnosticoVeterinaria;
    }

    public void setBuscAyudaDiagnosticoVeterinaria(buscAyudaDiagnosticoVeterinaria buscAyudaDiagnosticoVeterinaria) {
        this.buscAyudaDiagnosticoVeterinaria = buscAyudaDiagnosticoVeterinaria;
    }

    public infRemisionVeterinaria getRemisionVeterinaria() {
        return remisionVeterinaria;
    }

    public void setRemisionVeterinaria(infRemisionVeterinaria remisionVeterinaria) {
        this.remisionVeterinaria = remisionVeterinaria;
    }

    public infAyudaDiagnosticoVeterinaria getAyudaDiagnosticoVeterinaria() {
        return ayudaDiagnosticoVeterinaria;
    }

    public void setAyudaDiagnosticoVeterinaria(infAyudaDiagnosticoVeterinaria ayudaDiagnosticoVeterinaria) {
        this.ayudaDiagnosticoVeterinaria = ayudaDiagnosticoVeterinaria;
    }

    public infFormulaMedicaVeterinaria getFormulaMedicaVeterinaria() {
        return formulaMedicaVeterinaria;
    }

    public void setFormulaMedicaVeterinaria(infFormulaMedicaVeterinaria formulaMedicaVeterinaria) {
        this.formulaMedicaVeterinaria = formulaMedicaVeterinaria;
    }

    public infQuirurgica getQuirurgica() {
        return quirurgica;
    }

    public void setQuirurgica(infQuirurgica quirurgica) {
        this.quirurgica = quirurgica;
    }

    public BigDecimal getPropina() {
        return propina;
    }

    public void setPropina(BigDecimal propina) {
        this.propina = propina;
    }

    public BigDecimal getTotalPropina() {
        return totalPropina;
    }

    public void setTotalPropina(BigDecimal totalPropina) {
        this.totalPropina = totalPropina;
    }

    public BigDecimal getTarjetaCredito() {
        return tarjetaCredito;
    }

    public void setTarjetaCredito(BigDecimal tarjetaCredito) {
        this.tarjetaCredito = tarjetaCredito;
    }

    public String getPorcPropina() {
        return porcPropina;
    }

    public void setPorcPropina(String porcPropina) {
        this.porcPropina = porcPropina;
    }

    public buscMascotas getBuscMascotas() {
        return buscMascotas;
    }

    public void setBuscMascotas(buscMascotas buscMascotas) {
        this.buscMascotas = buscMascotas;
    }

    public infHospitalizacion getHospitalizacion() {
        return hospitalizacion;
    }

    public void setHospitalizacion(infHospitalizacion hospitalizacion) {
        this.hospitalizacion = hospitalizacion;
    }

    public infHistoria getHistoria() {
        return historia;
    }

    public void setHistoria(infHistoria historia) {
        this.historia = historia;
    }

    public buscTemperamento getBuscTemperamento() {
        return buscTemperamento;
    }

    public void setBuscTemperamento(buscTemperamento buscTemperamento) {
        this.buscTemperamento = buscTemperamento;
    }

    public buscRazas getBuscRazas() {
        return buscRazas;
    }

    public void setBuscRazas(buscRazas buscRazas) {
        this.buscRazas = buscRazas;
    }

    public String getProdCambio() {
        return prodCambio;
    }

    public void setProdCambio(String prodCambio) {
        this.prodCambio = prodCambio;
    }

    public Double getCantCambio() {
        return cantCambio;
    }

    public void setCantCambio(Double cantCambio) {
        this.cantCambio = cantCambio;
    }

    public infResumenDia getResumenDia() {
        return resumenDia;
    }

    public void setResumenDia(infResumenDia resumenDia) {
        this.resumenDia = resumenDia;
    }

    public infMesas1 getMesas1() {
        return mesas1;
    }

    public void setMesas1(infMesas1 mesas1) {
        this.mesas1 = mesas1;
    }

    public infRepIvasCompras getRepIvasCompras() {
        return repIvasCompras;
    }

    public void setRepIvasCompras(infRepIvasCompras repIvasCompras) {
        this.repIvasCompras = repIvasCompras;
    }

    public Boolean getCancelarFactura() {
        return cancelarFactura;
    }

    public void setCancelarFactura(Boolean cancelarFactura) {
        this.cancelarFactura = cancelarFactura;
    }

    public infRepIvas getRepIvas() {
        return repIvas;
    }

    public void setRepIvas(infRepIvas repIvas) {
        this.repIvas = repIvas;
    }

    public buscTiposProductos getBuscTiposProductos() {
        return buscTiposProductos;
    }

    public void setBuscTiposProductos(buscTiposProductos buscTiposProductos) {
        this.buscTiposProductos = buscTiposProductos;
    }

    public infRepTrasladosInternos getRepTrasladosInternos() {
        return repTrasladosInternos;
    }

    public void setRepTrasladosInternos(infRepTrasladosInternos repTrasladosInternos) {
        this.repTrasladosInternos = repTrasladosInternos;
    }

    public buscMarcas getBuscMarcas() {
        return buscMarcas;
    }

    public void setBuscMarcas(buscMarcas buscMarcas) {
        this.buscMarcas = buscMarcas;
    }

    public buscColores getBuscColores() {
        return buscColores;
    }

    public void setBuscColores(buscColores buscColores) {
        this.buscColores = buscColores;
    }

    public buscMedidas getBuscMedidas() {
        return buscMedidas;
    }

    public void setBuscMedidas(buscMedidas buscMedidas) {
        this.buscMedidas = buscMedidas;
    }

    public infInventario getInventario() {
        return inventario;
    }

    public void setInventario(infInventario inventario) {
        this.inventario = inventario;
    }

    public infRepAjustes getRepAjustes() {
        return repAjustes;
    }

    public void setRepAjustes(infRepAjustes repAjustes) {
        this.repAjustes = repAjustes;
    }

    public infFacturarLotes getFacturarLotes() {
        return facturarLotes;
    }

    public void setFacturarLotes(infFacturarLotes facturarLotes) {
        this.facturarLotes = facturarLotes;
    }

    public infTrasladosInternos getTrasladosInternos() {
        return trasladosInternos;
    }

    public void setTrasladosInternos(infTrasladosInternos trasladosInternos) {
        this.trasladosInternos = trasladosInternos;
    }

    public infBodegasClientes getBodegasClientes() {
        return bodegasClientes;
    }

    public void setBodegasClientes(infBodegasClientes bodegasClientes) {
        this.bodegasClientes = bodegasClientes;
    }

    public infKardexProductos getKardexProductos() {
        return kardexProductos;
    }

    public void setKardexProductos(infKardexProductos kardexProductos) {
        this.kardexProductos = kardexProductos;
    }

    public infRepHistorial getRepHistorial() {
        return repHistorial;
    }

    public void setRepHistorial(infRepHistorial repHistorial) {
        this.repHistorial = repHistorial;
    }

    public buscMedicamentosOf getBuscMedicamentos1() {
        return buscMedicamentos1;
    }

    public void setBuscMedicamentos1(buscMedicamentosOf buscMedicamentos1) {
        this.buscMedicamentos1 = buscMedicamentos1;
    }

    public busAyudaDiagnostico getAyudaDiagnostico() {
        return ayudaDiagnostico;
    }

    public void setAyudaDiagnostico(busAyudaDiagnostico ayudaDiagnostico) {
        this.ayudaDiagnostico = ayudaDiagnostico;
    }

    public String getMedico() {
        return medico;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }

    public BigDecimal getValorComision() {
        return valorComision;
    }

    public void setValorComision(BigDecimal valorComision) {
        this.valorComision = valorComision;
    }

    public BigDecimal getTotalFacturaComision() {
        return totalFacturaComision;
    }

    public void setTotalFacturaComision(BigDecimal totalFacturaComision) {
        this.totalFacturaComision = totalFacturaComision;
    }

    public String getFranquisia() {
        return franquisia;
    }

    public void setFranquisia(String franquisia) {
        this.franquisia = franquisia;
    }

    public String getComision() {
        return comision;
    }

    public void setComision(String comision) {
        this.comision = comision;
    }

    public String getCita() {
        return cita;
    }

    public void setCita(String cita) {
        this.cita = cita;
    }

    public infAyudaDiagnosticoOf getAyudaDiagnosticoOf() {
        return ayudaDiagnosticoOf;
    }

    public void setAyudaDiagnosticoOf(infAyudaDiagnosticoOf ayudaDiagnosticoOf) {
        this.ayudaDiagnosticoOf = ayudaDiagnosticoOf;
    }

    public infBiometria getBiometria() {
        return biometria;
    }

    public void setBiometria(infBiometria biometria) {
        this.biometria = biometria;
    }

    public infFormulaLentesOf getFormulaLentes() {
        return formulaLentes;
    }

    public void setFormulaLentes(infFormulaLentesOf formulaLentes) {
        this.formulaLentes = formulaLentes;
    }

    public infFormulaMedicaOf getFormulaMedicaOf() {
        return formulaMedicaOf;
    }

    public void setFormulaMedicaOf(infFormulaMedicaOf formulaMedicaOf) {
        this.formulaMedicaOf = formulaMedicaOf;
    }

    public infHojaIngreso getHojaIngreso() {
        return hojaIngreso;
    }

    public void setHojaIngreso(infHojaIngreso hojaIngreso) {
        this.hojaIngreso = hojaIngreso;
    }

    public infIncapacidadOf getIncapacidadOf() {
        return incapacidadOf;
    }

    public void setIncapacidadOf(infIncapacidadOf incapacidadOf) {
        this.incapacidadOf = incapacidadOf;
    }

    public infPaquimetria getPaquimetria() {
        return paquimetria;
    }

    public void setPaquimetria(infPaquimetria paquimetria) {
        this.paquimetria = paquimetria;
    }

    public infRepBancos getRepBancos() {
        return repBancos;
    }

    public void setRepBancos(infRepBancos repBancos) {
        this.repBancos = repBancos;
    }

    public infBancos getBancos() {
        return bancos;
    }

    public void setBancos(infBancos bancos) {
        this.bancos = bancos;
    }

    public infHistoriaLaboratorio getHistoriaLaboratorio() {
        return historiaLaboratorio;
    }

    public void setHistoriaLaboratorio(infHistoriaLaboratorio historiaLaboratorio) {
        this.historiaLaboratorio = historiaLaboratorio;
    }

    public infEmbarazo getEmbarazo() {
        return embarazo;
    }

    public void setEmbarazo(infEmbarazo embarazo) {
        this.embarazo = embarazo;
    }

    public infPerfil getPerfil() {
        return perfil;
    }

    public void setPerfil(infPerfil perfil) {
        this.perfil = perfil;
    }

    public infGlicemia getGlicemia() {
        return glicemia;
    }

    public void setGlicemia(infGlicemia glicemia) {
        this.glicemia = glicemia;
    }

    public infProteina getProteina() {
        return proteina;
    }

    public void setProteina(infProteina proteina) {
        this.proteina = proteina;
    }

    public infKoh getKoh() {
        return koh;
    }

    public void setKoh(infKoh koh) {
        this.koh = koh;
    }

    public infSerologia getSerologia() {
        return serologia;
    }

    public void setSerologia(infSerologia serologia) {
        this.serologia = serologia;
    }

    public infHemoclasificacion getHemoclasificacion() {
        return hemoclasificacion;
    }

    public void setHemoclasificacion(infHemoclasificacion hemoclasificacion) {
        this.hemoclasificacion = hemoclasificacion;
    }

    public infHemograma getHemograma() {
        return hemograma;
    }

    public void setHemograma(infHemograma hemograma) {
        this.hemograma = hemograma;
    }

    public infCreatinina getCreatinina() {
        return creatinina;
    }

    public void setCreatinina(infCreatinina creatinina) {
        this.creatinina = creatinina;
    }

    public infDirecto getDirectos() {
        return directos;
    }

    public void setDirectos(infDirecto directos) {
        this.directos = directos;
    }

    public infSangreOculta getSangreOculta() {
        return sangreOculta;
    }

    public infTiempos getTiempos() {
        return tiempos;
    }

    public void setTiempos(infTiempos tiempos) {
        this.tiempos = tiempos;
    }

    public void setSangreOculta(infSangreOculta sangreOculta) {
        this.sangreOculta = sangreOculta;
    }

    public infAcidos getAcidos() {
        return acidos;
    }

    public void setAcidos(infAcidos acidos) {
        this.acidos = acidos;
    }

    public infHemoglobina getHemoglobina() {
        return hemoglobina;
    }

    public void setHemoglobina(infHemoglobina hemoglobina) {
        this.hemoglobina = hemoglobina;
    }

    public infCitoquimico getCitoquimico() {
        return citoquimico;
    }

    public void setCitoquimico(infCitoquimico citoquimico) {
        this.citoquimico = citoquimico;
    }

    public String getIdEgreso() {
        return idEgreso;
    }

    public void setIdEgreso(String idEgreso) {
        this.idEgreso = idEgreso;
    }

    public infRepFormulas getRepFormulaMedica() {
        return repFormulaMedica;
    }

    public void setRepFormulaMedica(infRepFormulas repFormulaMedica) {
        this.repFormulaMedica = repFormulaMedica;
    }

    public infContraremision getContraremision() {
        return contraremision;
    }

    public void setContraremision(infContraremision contraremision) {
        this.contraremision = contraremision;
    }

    public infColesterol getColesterol() {
        return colesterol;
    }

    public void setColesterol(infColesterol colesterol) {
        this.colesterol = colesterol;
    }

    public infCoprologico getCoprologico() {
        return coprologico;
    }

    public void setCoprologico(infCoprologico coprologico) {
        this.coprologico = coprologico;
    }

    public infCocaina getCocaina() {
        return cocaina;
    }

    public void setCocaina(infCocaina cocaina) {
        this.cocaina = cocaina;
    }

    public infRepTraslados getRepTraslados() {
        return repTraslados;
    }

    public void setRepTraslados(infRepTraslados repTraslados) {
        this.repTraslados = repTraslados;
    }

    public infCannabinoides getCannabinoides() {
        return cannabinoides;
    }

    public void setCannabinoides(infCannabinoides cannabinoides) {
        this.cannabinoides = cannabinoides;
    }

    public infAntiestreptolisina getAntiestreptosilina() {
        return antiestreptosilina;
    }

    public void setAntiestreptosilina(infAntiestreptolisina antiestreptosilina) {
        this.antiestreptosilina = antiestreptosilina;
    }

    public infRecordatorios getRecordatorios() {
        return recordatorios;
    }

    public void setRecordatorios(infRecordatorios recordatorios) {
        this.recordatorios = recordatorios;
    }

    public infCertificadoMedico getCertificadoMedico() {
        return certificadoMedico;
    }

    public void setCertificadoMedico(infCertificadoMedico certificadoMedico) {
        this.certificadoMedico = certificadoMedico;
    }

    public infCertificadoMedicoOys getCertificadoMedicoOys() {
        return certificadoMedicoOys;
    }

    public void setCertificadoMedicoOys(infCertificadoMedicoOys certificadoMedicoOys) {
        this.certificadoMedicoOys = certificadoMedicoOys;
    }

    public infCertificadoMedicoEstudiante getCertificadoMedicoEstudiante() {
        return certificadoMedicoEstudiante;
    }

    public void setCertificadoMedicoEstudiante(infCertificadoMedicoEstudiante certificadoMedicoEstudiante) {
        this.certificadoMedicoEstudiante = certificadoMedicoEstudiante;
    }

    public infRemision getRemision() {
        return remision;
    }

    public void setRemision(infRemision remision) {
        this.remision = remision;
    }

    public buscProductos getBusProductos() {
        return buscProductos;
    }

    public infReportesProductos getReportesProductos() {
        return reportesProductos;
    }

    public void setReportesProductos(infReportesProductos reportesProductos) {
        this.reportesProductos = reportesProductos;
    }

    public void setBusProductos(buscProductos buscProductos) {
        this.buscProductos = buscProductos;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public infMesa getMesa() {
        return mesa;
    }

    public infRepProductos getRepProductos() {
        return repProductos;
    }

    public void setRepProductos(infRepProductos repProductos) {
        this.repProductos = repProductos;
    }

    public VistaFactura getMesa1() {
        return mesa.getPnlFactura();
    }

    public void setMesa(infMesa mesa) {
        this.mesa = mesa;
    }

    public infPreparacion getPreparacion() {
        return preparacion;
    }

    public void setPreparacion(infPreparacion preparacion) {
        this.preparacion = preparacion;
    }

    public dlgPagosProveedores getPagosProveedores() {
        return pagosProveedores;
    }

    public void setPagosProveedores(dlgPagosProveedores pagosProveedores) {
        this.pagosProveedores = pagosProveedores;
    }

    public buscPlaca1 getBuscPlacas1() {
        return buscPlacas1;
    }

    public infAcidoUrico getAcidoUrico() {
        return acidoUrico;
    }

    public void setAcidoUrico(infAcidoUrico acidoUrico) {
        this.acidoUrico = acidoUrico;
    }

    public void setBuscPlacas1(buscPlaca1 buscPlacas1) {
        this.buscPlacas1 = buscPlacas1;
    }

    public buscPlacas getBuscPlacas() {
        return buscPlacas;
    }

    public void setBuscPlacas(buscPlacas buscPlacas) {
        this.buscPlacas = buscPlacas;
    }

    public infParqueadero getParqueadero() {
        return parqueadero;
    }

    public void setParqueadero(infParqueadero parqueadero) {
        this.parqueadero = parqueadero;
    }

    public infLavadero getLavadero() {
        return lavadero;
    }

    public void setLavadero(infLavadero lavadero) {
        this.lavadero = lavadero;
    }

    public infMensualidad getMensualidad() {
        return mensualidad;
    }

    public void setMensualidad(infMensualidad mensualidad) {
        this.mensualidad = mensualidad;
    }

    public infRepMensualidades getRepMensualidades() {
        return repMensualidades;
    }

    public void setRepMensualidades(infRepMensualidades repMensualidades) {
        this.repMensualidades = repMensualidades;
    }

    public infRepLavadero getRepLavadero() {
        return repLavadero;
    }

    public void setRepLavadero(infRepLavadero repLavadero) {
        this.repLavadero = repLavadero;
    }

    public infRepLavadero1 getRepLavadero1() {
        return repLavadero1;
    }

    public void setRepLavadero1(infRepLavadero1 repLavadero1) {
        this.repLavadero1 = repLavadero1;
    }

    public buscProblemas getBuscarProblemas() {
        return buscarProblemas;
    }

    public void setBuscarProblemas(buscProblemas buscarProblemas) {
        this.buscarProblemas = buscarProblemas;
    }

    public infRepParqueadero getRepParqueadero() {
        return repParqueadero;
    }

    public void setRepParqueadero(infRepParqueadero repParqueadero) {
        this.repParqueadero = repParqueadero;
    }

    /* FIN PARQUEADERO */
    public BigDecimal getDescuentoMaximoVentas() {
        return descuentoMaximoVentas;
    }

    public void setDescuentoMaximoVentas(BigDecimal descuentoMaximoVentas) {
        this.descuentoMaximoVentas = descuentoMaximoVentas;
    }

    public boolean isVisualizarTodasLasFacturas() {
        return visualizarTodasLasFacturas;
    }

    public boolean isMostrarInformacionCuadre() {
        return mostrarInformacionCuadre;
    }

    public void setMostrarInformacionCuadre(boolean mostrarInformacionCuadre) {
        this.mostrarInformacionCuadre = mostrarInformacionCuadre;
    }

    public void setVisualizarTodasLasFacturas(boolean visualizarTodasLasFacturas) {
        this.visualizarTodasLasFacturas = visualizarTodasLasFacturas;
    }

    public buscUsuarios getBuscarUsuarios() {
        return buscarUsuarios;
    }

    public void setBuscarUsuarios(buscUsuarios buscarUsuarios) {
        this.buscarUsuarios = buscarUsuarios;
    }

    public VistaInventarioInicial getInventarioInicial() {
        return inventarioInicial;
    }

    public boolean isOcultarInformacionCliente() {
        return ocultarInformacionCliente;
    }

    public void setOcultarInformacionCliente(boolean ocultarInformacionCliente) {
        this.ocultarInformacionCliente = ocultarInformacionCliente;
    }

    public void setInventarioInicial(VistaInventarioInicial inventarioInicial) {
        this.inventarioInicial = inventarioInicial;
    }

    public boolean isImprimirCuadreFiscal() {
        return imprimirCuadreFiscal;
    }

    public infCoprograma getCoprograma() {
        return coprograma;
    }

    public void setCoprograma(infCoprograma coprograma) {
        this.coprograma = coprograma;
    }

    public void setImprimirCuadreFiscal(boolean imprimirCuadreFiscal) {
        this.imprimirCuadreFiscal = imprimirCuadreFiscal;
    }

    public buscEgresos getBuscarEgresos() {
        return buscarEgresos;
    }

    public void setBuscarEgresos(buscEgresos buscarEgresos) {
        this.buscarEgresos = buscarEgresos;
    }

    public infRepOrdenCompra getRepOrdenCompra() {
        return repOrdenCompra;
    }

    public void setRepOrdenCompra(infRepOrdenCompra repOrdenCompra) {
        this.repOrdenCompra = repOrdenCompra;
    }

    public JTextArea getCampoActualTextArea() {
        return campoActualTextArea;
    }

    public void setCampoActualTextArea(JTextArea campoActualTextArea) {
        this.campoActualTextArea = campoActualTextArea;
    }

    public buscPlantillaDeTextos getBuscPlantillaDeTextos() {
        return buscPlantillaDeTextos;
    }

    public void setBuscPlantillaDeTextos(buscPlantillaDeTextos buscPlantillaDeTextos) {
        this.buscPlantillaDeTextos = buscPlantillaDeTextos;
    }

    public infFormatosDeTextos getPlantillasDeTextos() {
        return plantillasDeTextos;
    }

    public void setPlantillasDeTextos(infFormatosDeTextos plantillasDeTextos) {
        this.plantillasDeTextos = plantillasDeTextos;
    }

    public buscOcupaciones getBuscarOcupaciones() {
        return buscarOcupaciones;
    }

    public infAgendaConsulta getAgendaConsulta() {
        return agendaConsulta;
    }

    public void setAgendaConsulta(infAgendaConsulta agendaConsulta) {
        this.agendaConsulta = agendaConsulta;
    }

    public void setBuscarOcupaciones(buscOcupaciones buscarOcupaciones) {
        this.buscarOcupaciones = buscarOcupaciones;
    }

    public boolean isImprimirFacturaOrdenMedica() {
        return imprimirFacturaOrdenMedica;
    }

    public void setImprimirFacturaOrdenMedica(boolean imprimirFacturaOrdenMedica) {
        this.imprimirFacturaOrdenMedica = imprimirFacturaOrdenMedica;
    }

    public vistaContactos getPaciente() {
        return paciente;
    }

    public void setPaciente(vistaContactos paciente) {
        this.paciente = paciente;
    }

    public infRepOrdenesMedicas getRepMedico() {
        return repMedico;
    }

    public void setRepMedico(infRepOrdenesMedicas repMedico) {
        this.repMedico = repMedico;
    }

    public infEps getEps() {
        return eps;
    }

    public void setEps(infEps eps) {
        this.eps = eps;
    }

    public infMedicamentos getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(infMedicamentos medicamentos) {
        this.medicamentos = medicamentos;
    }

    public infNotaEnfermeria getNotaEnfermeria() {
        return notaEnfermeria;
    }

    public void setNotaEnfermeria(infNotaEnfermeria notaEnfermeria) {
        this.notaEnfermeria = notaEnfermeria;
    }

    public infConvenio getConvenio() {
        return convenio;
    }

    public void setConvenio(infConvenio convenio) {
        this.convenio = convenio;
    }

    public infRips getRips() {
        return rips;
    }

    public void setRips(infRips rips) {
        this.rips = rips;
    }

    public infOrdenServicioMedico getOrdenMedica() {
        return ordenMedica;
    }

    public void setOrdenMedica(infOrdenServicioMedico ordenMedica) {
        this.ordenMedica = ordenMedica;
    }

    public buscConvenio getBuscConvenio() {
        return buscConvenio;
    }

    public void setBuscConvenio(buscConvenio buscConvenio) {
        this.buscConvenio = buscConvenio;
    }

    public infAutorizacionServicios getAutorizacionServicios() {
        return autorizacionServicios;
    }

    public void setAutorizacionServicios(infAutorizacionServicios autorizacionServicios) {
        this.autorizacionServicios = autorizacionServicios;
    }

    public boolean isGeneraOrdenMedica() {
        return generaOrdenMedica;
    }

    public boolean isImprimirOrdenMedica() {
        return imprimirOrdenMedica;
    }

    public void setImprimirOrdenMedica(boolean imprimirOrdenMedica) {
        this.imprimirOrdenMedica = imprimirOrdenMedica;
    }

    public void setGeneraOrdenMedica(boolean generaOrdenMedica) {
        this.generaOrdenMedica = generaOrdenMedica;
    }

    public buscAntecedentes getBuscAntecedentes() {
        return buscAntecedentes;
    }

    public void setBuscAntecedentes(buscAntecedentes buscAntecedentes) {
        this.buscAntecedentes = buscAntecedentes;
    }

    public buscCies getBuscCies() {
        return buscCies;
    }

    public void setBuscCies(buscCies buscCies) {
        this.buscCies = buscCies;
    }

    public buscCups getBuscCups() {
        return buscCups;
    }

    public void setBuscCups(buscCups buscCups) {
        this.buscCups = buscCups;
    }
    private buscCies buscCies;

    public infIncapacidad getIncapacidad() {
        return incapacidad;
    }

    public void setIncapacidad(infIncapacidad incapacidad) {
        this.incapacidad = incapacidad;
    }

    public infFormulaMedica getFormulaMedica() {
        return formulaMedica;
    }

    public void setFormulaMedica(infFormulaMedica formulaMedica) {
        this.formulaMedica = formulaMedica;
    }

    public infHistoriaC getHistoriaC() {
        return historiaC;
    }

    public void setHistoriaC(infHistoriaC historiaC) {
        this.historiaC = historiaC;
    }

    public buscMedicamentos getBuscMedicamentos() {
        return buscMedicamentos;
    }

    public void setBuscMedicamentos(buscMedicamentos buscMedicamentos) {
        this.buscMedicamentos = buscMedicamentos;
    }

    public buscEpsPrecargadas getBuscEpsPre() {
        return buscEpsPre;
    }

    public void setBuscEpsPre(buscEpsPrecargadas buscEpsPre) {
        this.buscEpsPre = buscEpsPre;
    }

    public infAyudaDiagnostico getAyudaDiagnostica() {
        return ayudaDiagnostica;
    }

    public void setAyudaDiagnostica(infAyudaDiagnostico ayudaDiagnostica) {
        this.ayudaDiagnostica = ayudaDiagnostica;
    }

    public infHistoriaMedica getHistoriaClinica() {
        return historiaClinica;
    }

    public void setHistoriaClinica(infHistoriaMedica historiaClinica) {
        this.historiaClinica = historiaClinica;
    }

    public buscAyudaDiagnostico getBuscAyudaDiagnostico() {
        return buscAyudaDiagnostico;
    }

    public void setBuscAyudaDiagnostico(buscAyudaDiagnostico buscAyudaDiagnostico) {
        this.buscAyudaDiagnostico = buscAyudaDiagnostico;
    }

    public infRepAgenda getRepAgenda() {
        return repAgenda;
    }

    public void setRepAgenda(infRepAgenda repAgenda) {
        this.repAgenda = repAgenda;
    }

    public String[] getConfigAgenda() {
        return configAgenda;
    }

    public infInfoAgenda getInfoAgenda() {
        return infoAgenda;
    }

    public void setInfoAgenda(infInfoAgenda infoAgenda) {
        this.infoAgenda = infoAgenda;
    }

    public void setConfigAgenda(String[] configAgenda) {
        this.configAgenda = configAgenda;
    }

    public buscHorasPeluqueria getBuscarHorasPeluqueria() {
        return buscarHorasPeluqueria;
    }

    public void setBuscarHorasPeluqueria(buscHorasPeluqueria buscarHorasPeluqueria) {
        this.buscarHorasPeluqueria = buscarHorasPeluqueria;
    }

    public buscHorasAgenda getBuscarHorasAgenda() {
        return buscarHorasAgenda;
    }

    public void setBuscarHorasAgenda(buscHorasAgenda buscarHorasAgenda) {
        this.buscarHorasAgenda = buscarHorasAgenda;
    }

    public infRepAnulas getRepAnulas() {
        return repAnulas;
    }

    public void setRepAnulas(infRepAnulas repAnulas) {
        this.repAnulas = repAnulas;
    }

    public infRepCotizas getRepCotizas() {
        return repCotizas;
    }

    public void setRepCotizas(infRepCotizas repCotizas) {
        this.repCotizas = repCotizas;
    }

    public infRepVentas getRepVentas() {
        return repVentas;
    }

    public void setRepVentas(infRepVentas repVentas) {
        this.repVentas = repVentas;
    }

    public infRepCreditos getRepCreditos() {
        return repCreditos;
    }

    public void setRepCreditos(infRepCreditos repCreditos) {
        this.repCreditos = repCreditos;
    }

    public infRepSepares getRepSepares() {
        return repSepares;
    }

    public void setRepSepares(infRepSepares repSepares) {
        this.repSepares = repSepares;
    }

    public infReportesVentas getReportesVentas() {
        return reportesVentas;
    }

    public void setReportesVentas(infReportesVentas reportesVentas) {
        this.reportesVentas = reportesVentas;
    }

    public VistaFactura getPlanSepare() {
        return planSepare.getPnlFactura();
    }

    public infPlanSepare getPlanSepareContenedor() {
        return planSepare;
    }

    public void setPlanSepare(infPlanSepare planSepare) {
        this.planSepare = planSepare;
    }

    public infPedido getPedidoContenedor() {
        return pedido;
    }

    public VistaFactura getPedido() {
        return pedido.getPnlFactura();
    }

    public void setPedido(infPedido pedido) {
        this.pedido = pedido;
    }

    public buscReporteFacturaPedido getReporteFacturaPedido() {
        return reporteFacturaPedido;
    }

    public void setReporteFacturaPedido(buscReporteFacturaPedido reporteFacturaPedido) {
        this.reporteFacturaPedido = reporteFacturaPedido;
    }

    public String getDiasCobrarMora() {
        return diasCobrarMora;
    }

    public void setDiasCobrarMora(String diasCobrarMora) {
        this.diasCobrarMora = diasCobrarMora;
    }

    public String getPorcentajeMora() {
        return porcentajeMora;
    }

    public void setPorcentajeMora(String porcentajeMora) {
        this.porcentajeMora = porcentajeMora;
    }

    public infAlertasCumpleanos getAlertas() {
        return alertas;
    }

    public void setAlertas(infAlertasCumpleanos alertas) {
        this.alertas = alertas;
    }

    public BigDecimal getEfectivoDevuelta() {
        return efectivoDevuelta;
    }

    public void setEfectivoDevuelta(BigDecimal efectivoDevuelta) {
        this.efectivoDevuelta = efectivoDevuelta;
    }

    public BigDecimal getTarjetaDevuelta() {
        return tarjetaDevuelta;
    }

    public void setTarjetaDevuelta(BigDecimal tarjetaDevuelta) {
        this.tarjetaDevuelta = tarjetaDevuelta;
    }

    public BigDecimal getChequeDevuelta() {
        return chequeDevuelta;
    }

    public void setChequeDevuelta(BigDecimal chequeDevuelta) {
        this.chequeDevuelta = chequeDevuelta;
    }

    public BigDecimal getNcDevuelta() {
        return ncDevuelta;
    }

    public void setNcDevuelta(BigDecimal ncDevuelta) {
        this.ncDevuelta = ncDevuelta;
    }

    public String getInformacionEmpresaReimpresion() {
        return informacionEmpresaReimpresion;
    }

    public void setInformacionEmpresaReimpresion(String informacionEmpresaReimpresion) {
        this.informacionEmpresaReimpresion = informacionEmpresaReimpresion;
    }

    public String getInformacionEmpresaCompleto() {
        return informacionEmpresaCompleto;
    }

    public void setInformacionEmpresaCompleto(String informacionEmpresaCompleto) {
        this.informacionEmpresaCompleto = informacionEmpresaCompleto;
    }

    public String getResolucion() {
        return resolucion;
    }

    public void setResolucion(String resolucion) {
        this.resolucion = resolucion;
    }

    public modeloConfiguracion getConfigFactura() {
        return configFactura;
    }

    public void setConfigFactura(modeloConfiguracion configFactura) {
        this.configFactura = configFactura;
    }

    private vistaSuperMaestra configuraciones;

    public vistaSuperMaestra getConfiguraciones() {
        return configuraciones;
    }

    public void setConfiguraciones(vistaSuperMaestra configuraciones) {
        this.configuraciones = configuraciones;
    }

    private infRepOrden repOrden;

    public infRepOrden getRepOrden() {
        return repOrden;
    }

    public void setRepOrden(infRepOrden repOrden) {
        this.repOrden = repOrden;
    }

    public String getAlertaFechaDias() {
        return alertaFechaDias;
    }

    public void setAlertaFechaDias(String alertaFechaDias) {
        this.alertaFechaDias = alertaFechaDias;
    }

    public String getAlertaCantidadDias() {
        return alertaCantidadDias;
    }

    public void setAlertaCantidadDias(String alertaCantidadDias) {
        this.alertaCantidadDias = alertaCantidadDias;
    }

    public String getAlertaPromedioDias() {
        return alertaPromedioDias;
    }

    public void setAlertaPromedioDias(String alertaPromedioDias) {
        this.alertaPromedioDias = alertaPromedioDias;
    }

    public String getDiasRestantesResolucion() {
        return diasRestantesResolucion;
    }

    public void setDiasRestantesResolucion(String diasRestantesResolucion) {
        this.diasRestantesResolucion = diasRestantesResolucion;
    }

    public String getDiasAlertaResolucion() {
        return diasAlertaResolucion;
    }

    public void setDiasAlertaResolucion(String diasAlertaResolucion) {
        this.diasAlertaResolucion = diasAlertaResolucion;
    }

    public boolean isPvpConIva() {
        return pvpConIva;
    }

    public void setPvpConIva(boolean pvpConIva) {
        this.pvpConIva = pvpConIva;
    }

    public boolean isCostoConIva() {
        return costoConIva;
    }

    public void setCostoConIva(boolean costoConIva) {
        this.costoConIva = costoConIva;
    }

    public boolean isVentasPredeterminado() {
        return ventasPredeterminado;
    }

    public void setVentasPredeterminado(boolean ventasPredeterminado) {
        this.ventasPredeterminado = ventasPredeterminado;
    }

    public boolean isMensajeUtilidad() {
        return mensajeUtilidad;
    }

    public void setMensajeUtilidad(boolean mensajeUtilidad) {
        this.mensajeUtilidad = mensajeUtilidad;
    }

    public String getTituloFactura() {
        return tituloFactura;
    }

    public void setTituloFactura(String tituloFactura) {
        this.tituloFactura = tituloFactura;
    }

    public formularios.Ventas.infOrdenServicio getOrdenServicioContenedor() {
        return ordenServicio;
    }

    public String getIdAbono() {
        return idAbono;
    }

    public void setIdAbono(String idAbono) {
        this.idAbono = idAbono;
    }

    public VistaFactura getOrdenServicio() {
        return ordenServicio.getPnlFactura();
    }

    public void setOrdenServicio(formularios.Ventas.infOrdenServicio ordenServicio) {
        this.ordenServicio = ordenServicio;
    }

    public boolean isLector() {
        return lector;
    }

    public void setLector(boolean lector) {
        this.lector = lector;
    }

    public String getPie() {
        return pie;
    }

    public void setPie(String pie) {
        this.pie = pie;
    }

    public String getLegal() {
        return legal;
    }

    public void setLegal(String legal) {
        this.legal = legal;
    }

    public boolean isCopias() {
        return copias;
    }

    public void setCopias(boolean copias) {
        this.copias = copias;
    }

    public String getImpresion() {
        return impresion;
    }

    public void setImpresion(String impresion) {
        this.impresion = impresion;
    }

    public String getTipoImpresion() {
        return tipoImpresion;
    }

    public void setTipoImpresion(String tipoImpresion) {
        this.tipoImpresion = tipoImpresion;
    }

    public boolean isImprimirRecogida() {
        return imprimirRecogida;
    }

    public void setImprimirRecogida(boolean imprimirRecogida) {
        this.imprimirRecogida = imprimirRecogida;
    }

    public String getInformacionEmpresa() {
        return informacionEmpresa;
    }

    public void setInfomacionEmpresa(String infomacionEmpresa) {
        this.informacionEmpresa = infomacionEmpresa;
    }

    public boolean isHora() {
        return hora;
    }

    public void setHora(boolean hora) {
        this.hora = hora;
    }

    public boolean isUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(boolean ubicacion) {
        this.ubicacion = ubicacion;
    }

    public BigDecimal getDevuelta() {
        return devuelta;
    }

    public void setDevuelta(BigDecimal devuelta) {
        this.devuelta = devuelta;
    }

    public String getSegundaClave() {
        return segundaClave;
    }

    public void setSegundaClave(String segundaClave) {
        this.segundaClave = segundaClave;
    }

    public String getDescuento() {
        return descuento;
    }

    public void setDescuento(String descuento) {
        this.descuento = descuento;
    }

    public boolean isUtilidad() {
        return utilidad;
    }

    public void setUtilidad(boolean utilidad) {
        this.utilidad = utilidad;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public String getRegimen() {
        return regimen;
    }

    public void setRegimen(String regimen) {
        this.regimen = regimen;
    }

    public buscGrupos getBuscGrupos() {
        return buscGrupos;
    }

    public void setBuscGrupos(buscGrupos buscGrupos) {
        this.buscGrupos = buscGrupos;
    }

    public infPagos getPagos() {
        return pagos;
    }

    public void setPagos(infPagos pagos) {
        this.pagos = pagos;
    }

    public infRepPagos getRepPagos() {
        return repPagos;
    }

    public void setRepPagos(infRepPagos repPagos) {
        this.repPagos = repPagos;
    }

    public void setDatosEmpresa(String dato, String legal, String pie, String datosCompletos, String datosReimpresion) {
        this.informacionEmpresa = dato;
        this.pie = pie;
        this.legal = legal;
        this.informacionEmpresaCompleto = datosCompletos;
        this.informacionEmpresaReimpresion = datosReimpresion;
    }

    public infRepCuadre getRepCuadre() {
        return repCuadre;
    }

    public void setRepCuadre(infRepCuadre repCuadre) {
        this.repCuadre = repCuadre;
    }

    public infMesas getMesas() {
        return mesas;
    }

    public void setMesas(infMesas mesas) {
        this.mesas = mesas;
    }

    public infPrestamos getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(infPrestamos prestamos) {
        this.prestamos = prestamos;
    }

    public buscServicios getBuscServicios() {
        return buscServicios;
    }

    public void setBuscServicios(buscServicios buscServicios) {
        this.buscServicios = buscServicios;
    }

    public infEmpresas getEmpresas() {
        return empresas;
    }

    public void setEmpresas(infEmpresas empresas) {
        this.empresas = empresas;
    }

    public infGrupos getGrupos() {
        return grupos;
    }

    public void setGrupos(infGrupos grupos) {
        this.grupos = grupos;
    }

    public String getRutaAguardar() {
        return rutaAguardar;
    }

    public void setRutaAguardar(String rutaAguardar) {
        this.rutaAguardar = rutaAguardar;
    }

    public jcThread getProgres() {
        return progres;
    }

    public void setProgres(jcThread progres) {
        this.progres = progres;
    }

    public buscEmpleados getBuscEmpleados() {
        return buscEmpleados;
    }

    public void setBuscEmpleados(buscEmpleados buscEmpleados) {
        this.buscEmpleados = buscEmpleados;
    }

    public iFactura getReporte() {
        return reporte;
    }

    public void setReporte(iFactura reporte) {
        this.reporte = reporte;
    }

    public infMascotas getMascotas() {
        return mascotas;
    }

    public String gettFactura() {
        return tFactura;
    }

    public infArmado getArmado() {
        return armado;
    }

    public buscProcesos getBuscProcesos() {
        return buscProcesos;
    }

    public void setBuscProcesos(buscProcesos buscProcesos) {
        this.buscProcesos = buscProcesos;
    }

    public SQL getSql() {
        return sql;
    }

    public void setArmado(infArmado armado) {
        this.armado = armado;
    }

    public void settFactura(String tFactura) {
        this.tFactura = tFactura;
    }

    public infCosteo getCosteo() {
        return costeo;
    }

    public void setCosteo(infCosteo costeo) {
        this.costeo = costeo;
    }

    public void setMascotas(infMascotas mascotas) {
        this.mascotas = mascotas;
    }

    public JInternalFrame getRepMascotas() {
        return repMascotas;
    }

    public void setRepMascotas(JInternalFrame repMascotas) {
        this.repMascotas = repMascotas;
    }

    public vistaCodigosEgresos getCodigosEgresos() {
        return codigosEgresos;
    }

    public infRepCartera getRepCartera() {
        return repCartera;
    }

    public void setRepCartera(infRepCartera repCartera) {
        this.repCartera = repCartera;
    }

    public infUsuarios getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(infUsuarios usuarios) {
        this.usuarios = usuarios;
    }

    public JInternalFrame getRepAbonosCxp() {
        return repAbonosCxp;
    }

    public void setRepAbonosCxp(JInternalFrame repAbonosCxp) {
        this.repAbonosCxp = repAbonosCxp;
    }

    public JInternalFrame getRepAbonos() {
        return repAbonos;
    }

    public void setRepAbonos(JInternalFrame repAbonos) {
        this.repAbonos = repAbonos;
    }

    public infProcesos getProcesos() {
        return procesos;
    }

    public void setProcesos(infProcesos procesos) {
        this.procesos = procesos;
    }

    public ndUsuario getUsuarioLog() {
        return usuarioLog;
    }

    public infRepEgresos getRepEgresos() {
        return repEgresos;
    }

    public void setRepEgresos(infRepEgresos repEgresos) {
        this.repEgresos = repEgresos;
    }

    public void setUsuarioLog(ndUsuario usuarioLog) {
        this.usuarioLog = usuarioLog;
    }

    public void setCodigosEgresos(vistaCodigosEgresos codigosEgresos) {
        this.codigosEgresos = codigosEgresos;
    }

    public vistaMaestra getMaestra() {
        return maestra;
    }

    public void setMaestra(vistaMaestra maestra) {
        this.maestra = maestra;
    }

    public infCaja getCaja() {
        return caja;
    }

    public VistaDocumentos getReimpresion() {
        return reimpresion;
    }

    public void setReimpresion(VistaDocumentos reimpresion) {
        this.reimpresion = reimpresion;
    }

    public void setCaja(infCaja caja) {
        this.caja = caja;
    }

    public infRepIngresos getRepIngresos() {
        return repIngresos;
    }

    public void setRepIngresos(infRepIngresos repIngresos) {
        this.repIngresos = repIngresos;
    }

    public JInternalFrame getInvBodegas() {
        return invBodegas;
    }

    public void setInvBodegas(JInternalFrame invBodegas) {
        this.invBodegas = invBodegas;
    }

    public JInternalFrame getRepClientes() {
        return repClientes;
    }

    public void setRepClientes(JInternalFrame repClientes) {
        this.repClientes = repClientes;
    }

    public JInternalFrame getRepBodegas() {
        return repBodegas;
    }

    public void setRepBodegas(JInternalFrame repBodegas) {
        this.repBodegas = repBodegas;
    }

    public JInternalFrame getRepEmpleados() {
        return repEmpleados;
    }

    public void setRepEmpleados(JInternalFrame repEmpleados) {
        this.repEmpleados = repEmpleados;
    }

    public VistaAjusteInventario getuInt() {
        return uInt;
    }

    public void setuInt(VistaAjusteInventario uInt) {
        this.uInt = uInt;
    }

    public infEmpleados getEmpleados() {
        return empleados;
    }

    public void setEmpleados(infEmpleados empleados) {
        this.empleados = empleados;
    }

    public vistaEgresos getEgresos() {
        return egresos;
    }

    public vistaNotaCredito getNc() {
        return nc;
    }

    public buscBodegas getBuscBodegas() {
        return buscBodegas;
    }

    public void setBuscBodegas(buscBodegas buscBodegas) {
        this.buscBodegas = buscBodegas;
    }

    public void setNc(vistaNotaCredito nc) {
        this.nc = nc;
    }

    public void setEgresos(vistaEgresos egresos) {
        this.egresos = egresos;
    }

    public VistaIngreso getOrdenCompra() {
        return ordenCompra.getPnlIngreso();
    }

    public ordenCompra getOrdenCompraContenedor() {
        return ordenCompra;
    }

    public void setOrdenCompra(ordenCompra ordenCompra) {
        this.ordenCompra = ordenCompra;
    }

    public VistaIngreso getIngresos() {
        return ingresos.getPnlIngreso();
    }

    public void setIngresos(ingreso ingresos) {
        this.ingresos = ingresos;
    }

    public ingreso getIngresoContenedor() {
        return ingresos;
    }

    public infAbonos getAbonos() {
        return abonos;
    }

    public void setAbonos(infAbonos abonos) {
        this.abonos = abonos;
    }

    public VistaFactura getCotiza() {
        return cotiza.getPnlFactura();
    }

    public infCotiza getCotizaContenedor() {
        return cotiza;
    }

    public void setCotiza(infCotiza cotiza) {
        this.cotiza = cotiza;
    }

    public VistaProductos getProductos() {
        return productos;
    }

    public void setProductos(VistaProductos productos) {
        this.productos = productos;
    }

    public infBodegas getBodegas() {
        return bodegas;
    }

    public void setBodegas(infBodegas bodegas) {
        this.bodegas = bodegas;
    }

    public vistaAnulacion getAnula() {
        return anula;
    }

    public void setAnula(vistaAnulacion anula) {
        this.anula = anula;
    }

    public vistaContactos getClientes() {
        return clientes;
    }

    public void setClientes(vistaContactos clientes) {
        this.clientes = clientes;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public buscClientes getBusClientes() {
        return busClientes;
    }

    public void setBusClientes(buscClientes busClientes) {
        this.busClientes = busClientes;
    }

    public vistaMenu getMenu() {
        return menu;
    }

    public void setMenu(vistaMenu menu) {
        this.menu = menu;
    }

    public JFrame getIngreso() {
        return ingreso;
    }

    public void setIngreso(JFrame ingreso) {
        this.ingreso = ingreso;
    }

    public VistaFactura getFacturaCreditos() {
        return facturaCreditos.getPnlFactura();
    }

    public infFacturaCreditos getFacturaCreditosContenedor() {
        return facturaCreditos;
    }

    public void setFacturaCreditos(infFacturaCreditos facturaCreditos) {
        this.facturaCreditos = facturaCreditos;
    }

    public VistaFactura getFactura() {
        return factura.getPnlFactura();
    }

    public infFactura getFacturaContenedor() {
        return factura;
    }

    public void setFactura(infFactura factura) {
        this.factura = factura;
    }

    public VistaFactura getCuentaCobro() {
        return cuentaCobro.getPnlFactura();
    }

    public infCuentaCobro getCuentaCobroContenedor() {
        return cuentaCobro;
    }

    public void setCuentaCobro(infCuentaCobro cuentaCobro) {
        this.cuentaCobro = cuentaCobro;
    }

    public JTextField getCampoActual() {
        return campoActual;
    }

    public void setCampoActual(JTextField campoActual) {
        this.campoActual = campoActual;
    }

    public void setValorCampo(String valor) {
        campoActual.setText(valor);
    }

    public void setValorCampoTextArea(String valor) {
        campoActualTextArea.setText(valor);
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    public infRepNc getRepNC() {
        return repNC;
    }

    public void setRepNC(infRepNc repNC) {
        this.repNC = repNC;
    }

    public buscSubGrupos getBuscSubGrupos() {
        return buscSubGrupos;
    }

    public void setBuscSubGrupos(buscSubGrupos buscSubGrupos) {
        this.buscSubGrupos = buscSubGrupos;
    }

    public void actualizarGrupos(Object[] dato) {
        getProductos().actualizarGrupos();
        getCosteo().actualizarGrupos();
    }
}
