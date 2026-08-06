package clases;

import clases.Cartera.ndCxc;
import clases.Cartera.ndCxp;
import clases.Cartera.ndNc;
import clases.Egresos.ndEgreso;
import Modelo.Egresos.ModeloDetalleEgreso;
import clases.Laboratorio.ndConsultaLaboratorio;
import clases.Medico.ndAyudaDiagnostica;
import clases.Medico.ndCamposOrdenPredeterminada;
import clases.Medico.ndCertificadoMedico;
import clases.Medico.ndConsultaClinica;
import clases.Medico.ndConvenio;
import clases.Medico.ndEps;
import clases.Medico.ndEpsPrecargados;
import clases.Medico.ndFormulaMedica;
import clases.Medico.ndHistoriaClinica;
import clases.Medico.ndIncapacidad;
import clases.Medico.ndInformacionPrestador;
import clases.Medico.ndMedicamentos;
import clases.Medico.ndNotaEnfermeria;
import clases.Medico.ndOrdenServicio;
import clases.Medico.ndRemision;
import clases.Medico.ndSeguimientoClinica;
import clases.Medico.ndTablaDiagnostica;
import clases.Medico.ndTablaFormula;
import clases.Oftalmologia.ndAyudaDiagnosticaOf;
import clases.Oftalmologia.ndBiometria;
import clases.Oftalmologia.ndFormulaLentes;
import clases.Oftalmologia.ndHojaIngreso;
import clases.Oftalmologia.ndIncapacidadOf;
import clases.Oftalmologia.ndPaquimetria;
import clases.Oftalmologia.ndSeguimientoClinicaOf;
import clases.Oftalmologia.ndTablaFormulaOf;
import clases.Parqueadero.ndLavado;
import clases.Parqueadero.ndParqueadero;
import clases.Ventas.ndCaja;
import clases.Ventas.ndCongelada;
import clases.Ventas.ndCotizacion;
import clases.Ventas.ndFactura;
import clases.Ventas.ndGarantia;
import clases.Ventas.ndNotasCredito;
import clases.Ventas.ndOServicio;
import clases.Ventas.ndOServicio1;
import clases.Ventas.ndPedido;
import clases.Ventas.ndPlanSepare;
import clases.Veterinario.ndConstanteHospitalizacion;
import clases.Veterinario.ndConsulta;
import clases.Veterinario.ndFormulaVeterinaria;
import clases.Veterinario.ndGuarderia;
import clases.Veterinario.ndHistoria;
import clases.Veterinario.ndIngresoHospitalizacion;
import clases.Veterinario.ndMascota;
import clases.Veterinario.ndParqueadero1;
import clases.Veterinario.ndSeguimiento;
import clases.Veterinario.ndTablaMedicamentos;
import clases.credito.ndCuota;
import clases.credito.ndPrestamo;
import clases.productos.ndCompra;
import clases.productos.ndGrupo;
import clases.productos.ndIngreso;
import clases.productos.ndIngresoEx;
import clases.productos.ndInventarioInicial;
import clases.productos.ndProceso;
import clases.productos.ndProdTraslBodega;
import clases.productos.ndProducto;
import clases.productos.ndProductoAjustes;
import clases.productos.ndTraslado;
import clases.productos.ndTrasladoBodega;
import clases.recordatorios.ndRecordatorio;
import clases.terceros.ndBodega;
import clases.terceros.ndEmpleado;
import Modelo.Terceros.ModeloContacto;
import com.toedter.calendar.JDateChooser;
import configuracion.msgAtencion;
import configuracion.msgAtencion1;
import configuracion.msgError;
import configuracion.msgExito;
import configuracion.msgIngresar;
import configuracion.msgIngresar1;
import configuracion.msgPregunta;
import datechooser.beans.DateChooserCombo;
import formularios.Agenda.ndAgenda;
import formularios.Disk;
import Vista.Productos.VistaBuscadorProductos;
import java.awt.AWTException;
import java.awt.Desktop;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.table.TableModel;

import org.apache.commons.codec.binary.Base64;

public class metodosGenerales {

// ---------------------------------------------------ESTRUCTURAS-----------------------------------------------------------------------------------------------
    public static void generarArchivoPlano(String ruta, String info) {
        File archivo = new File(ruta);
        BufferedWriter bw = null;
        try {
            if (archivo.exists()) {
                bw = new BufferedWriter(new FileWriter(archivo));
                bw.write(info);
            } else {
                bw = new BufferedWriter(new FileWriter(archivo));
                bw.write(info);
            }
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(metodosGenerales.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /* INICIO VETERINARIO */
    public Object[] pasarDatosGuarderia(ndGuarderia nodo) {
        Object[] vector = {nodo.getId(), nodo.getMascota(), nodo.getProducto(), nodo.getfIngreso(), nodo.getfSalida(), nodo.gethIngreso(), nodo.gethSalida(),
            nodo.getTotTiempo(), nodo.getTotHoras(), nodo.getTotValor(), nodo.getHoras(), nodo.getDias(), nodo.getObservacion(), nodo.getFactura(),
            nodo.getUsuario(), nodo.getTerminal(), nodo.isActivo(), nodo.getCliente()
        };
        return vector;
    }

    public ndGuarderia llenarGuarderia(Object[] vector) {
        ndGuarderia nodo = new ndGuarderia();
        nodo.setId((String) vector[0]);
        nodo.setMascota((String) vector[1]);
        nodo.setProducto((String) vector[2]);
        nodo.setfIngreso((String) vector[3]);
        nodo.setfSalida((String) vector[4]);
        nodo.sethIngreso((String) vector[5]);
        nodo.sethSalida((String) vector[6]);
        nodo.setTotTiempo((String) vector[7]);
        nodo.setTotHoras((String) vector[8]);
        nodo.setTotValor((String) vector[9]);
        nodo.setHoras((String) vector[10]);
        nodo.setDias((String) vector[11]);
        nodo.setObservacion((String) vector[12]);
        nodo.setFactura((String) vector[13]);
        nodo.setUsuario((String) vector[14]);
        nodo.setTerminal((String) vector[15]);
        nodo.setActivo((boolean) vector[16]);
        nodo.setCliente((String) vector[17]);
        return nodo;
    }

    public ndTablaMedicamentos llenarTablaMedicamentos(Object[] vector) {
        ndTablaMedicamentos nodo = new ndTablaMedicamentos();

        nodo.setIdHospitalizacion((String) vector[0]);
        nodo.setCodigo((String) vector[1]);
        nodo.setMedicamentos((String) vector[2]);
        nodo.setPosologia((String) vector[3]);
        nodo.setHora((String) vector[4]);

        return nodo;
    }

    public Object[] pasarDatosTablaMedicamentos(ndTablaMedicamentos nodo) {
        Object[] vector = {nodo.getIdHospitalizacion(), nodo.getCodigo(), nodo.getMedicamentos(), nodo.getPosologia(), nodo.getHora()};
        return vector;
    }

    public Object[] pasarDatosConstanteHospitalizacion(ndConstanteHospitalizacion nodo) {
        Object[] vector = {nodo.getId(), nodo.getIdHospitalizacion(), nodo.getFecha(), nodo.getHora(), nodo.getUsuario(), nodo.getCardiaca(), nodo.getResp(),
            nodo.getT(), nodo.getMucosas(), nodo.getOrina(), nodo.getDefeca(), nodo.getApetito(), nodo.getInformacion()};
        return vector;
    }

    public ndConstanteHospitalizacion llenarConstantesHopitalizacion(Object[] vector) {
        ndConstanteHospitalizacion nodo = new ndConstanteHospitalizacion();
        nodo.setId((String) vector[0]);
        nodo.setIdHospitalizacion((String) vector[1]);
        nodo.setFecha((String) vector[2]);
        nodo.setHora((String) vector[3]);
        nodo.setUsuario((String) vector[4]);
        nodo.setCardiaca((String) vector[5]);
        nodo.setResp((String) vector[6]);
        nodo.setT((String) vector[7]);
        nodo.setMucosas((String) vector[8]);
        nodo.setOrina((String) vector[9]);
        nodo.setDefeca((String) vector[10]);
        nodo.setApetito((String) vector[11]);
        nodo.setInformacion((String) vector[12]);
        return nodo;
    }

    public Object[] pasarDatosIngresoHospital(ndIngresoHospitalizacion nodo) {
        Object[] vector = {nodo.getId(), nodo.getMascota(), nodo.getProducto(), nodo.getfIngreso(), nodo.getfSalida(), nodo.gethIngreso(), nodo.gethSalida(),
            nodo.getTotTiempo(), nodo.getTotHoras(), nodo.getTotValor(), nodo.getHoras(), nodo.getDias(), nodo.getObservacion(), nodo.getFactura(),
            nodo.getUsuario(), nodo.getTerminal(), nodo.isActivo(), nodo.getCliente()
        };
        return vector;
    }

    public ndIngresoHospitalizacion llenarIngresoHospitalizacion(Object[] vector) {
        ndIngresoHospitalizacion nodo = new ndIngresoHospitalizacion();
        nodo.setId((String) vector[0]);
        nodo.setMascota((String) vector[1]);
        nodo.setProducto((String) vector[2]);
        nodo.setfIngreso((String) vector[3]);
        nodo.setfSalida((String) vector[4]);
        nodo.sethIngreso((String) vector[5]);
        nodo.sethSalida((String) vector[6]);
        nodo.setTotTiempo((String) vector[7]);
        nodo.setTotHoras((String) vector[8]);
        nodo.setTotValor((String) vector[9]);
        nodo.setHoras((String) vector[10]);
        nodo.setDias((String) vector[11]);
        nodo.setObservacion((String) vector[12]);
        nodo.setFactura((String) vector[13]);
        nodo.setUsuario((String) vector[14]);
        nodo.setTerminal((String) vector[15]);
        nodo.setActivo((boolean) vector[16]);
        nodo.setCliente((String) vector[17]);
        return nodo;
    }

    public ndMascota llenarMascota(Object[] vector) {
        ndMascota nodo = new ndMascota();
        nodo.setCliente((String) vector[0]);
        nodo.setCodigo((String) vector[1]);
        nodo.setNombre((String) vector[2]);
        nodo.setExpecie((String) vector[3]);
        nodo.setRaza((String) vector[4]);
        nodo.setGenero((String) vector[5]);
        nodo.setEdad((String) vector[6]);
        nodo.setRestricciones((String) vector[7]);
        nodo.setColor((String) vector[8]);
        nodo.setAlimentacion((String) vector[9]);
        nodo.setObservaciones((String) vector[10]);
        nodo.setFoto((String) vector[11]);
        nodo.setTemperamento((String) vector[12]);
        nodo.setObservacion((String) vector[13]);
        return nodo;
    }

    public Object[] pasarDatosMascotas(ndMascota nodo) {
        Object[] vector = {nodo.getCliente(), nodo.getCodigo(), nodo.getNombre(), nodo.getExpecie(), nodo.getRaza(), nodo.getGenero(), nodo.getEdad(), nodo.getRestricciones(),
            nodo.getColor(), nodo.getAlimentacion(), nodo.getObservaciones(), nodo.getFoto(), nodo.getTemperamento(), nodo.getObservacion()};
        return vector;
    }

    public ndHistoria llenarHistoria(Object[] vector) {
        ndHistoria nodo = new ndHistoria();
        nodo.setId((String) vector[0]);
        nodo.setMascota((String) vector[1]);
        nodo.setMotivo(String.valueOf(vector[2]));
        nodo.setEvolucion(String.valueOf(vector[3]));
        nodo.setEnfermedades(String.valueOf(vector[4]));
        nodo.setTratamientos(String.valueOf(vector[5]));
        nodo.setCampo1(String.valueOf(vector[6]));
        nodo.setCampo2(String.valueOf(vector[7]));
        nodo.setUsuario(String.valueOf(vector[8]));
        nodo.setFecha(String.valueOf(vector[9]));
        return nodo;
    }

    public Object[] pasarDatosHistoria(ndHistoria nodo) {
        Object[] vector = {nodo.getId(), nodo.getMascota(), nodo.getMotivo(), nodo.getEvolucion(), nodo.getEnfermedades(), nodo.getTratamientos(),
            nodo.getCampo1(), nodo.getCampo2(), nodo.getUsuario(), nodo.getFecha()};
        return vector;
    }

    public Object[] pasarDatosConsulta(ndConsulta nodo) {
        Object[] vector = {nodo.getId(), nodo.getHistoria(), nodo.getMotivo(), nodo.getFc(), nodo.getFr(), nodo.getPulso(),
            nodo.getPeso(), nodo.getTllc(), nodo.getTemp(), nodo.getCc(), nodo.getActitud(), nodo.getProblemas(),
            nodo.getDiagnostico(), nodo.getPlan(), nodo.getTratamiento(), nodo.getRecomendacion(), nodo.getUsuario(), nodo.getFecha(),
            nodo.getNervioso(), nodo.getDigestivo(), nodo.getRespiratorio(), nodo.getCardiaco(), nodo.getUrinario(), nodo.getMusculo(),
            nodo.getPiel(), nodo.getOidos(), nodo.getOjos(), nodo.getMucosa(), nodo.getLlenadoCapilar(), nodo.getCaracteristicas(),
            nodo.getHidratacion(), nodo.getNutricional(), nodo.getOrganosSentidos(), nodo.getGanglios(), nodo.getProximoControl(), nodo.getFotos(), nodo.getExamenes(), nodo.getEndocrino()
        };
        return vector;
    }

    public ndConsulta llenarConsulta(Object[] vector) {
        ndConsulta nodo = new ndConsulta();
        nodo.setId((String) vector[0]);
        nodo.setHistoria((String) vector[1]);
        nodo.setMotivo((String) vector[2]);
        nodo.setFc((String) vector[3]);
        nodo.setFr((String) vector[4]);
        nodo.setPulso((String) vector[5]);
        nodo.setPeso((String) vector[6]);
        nodo.setTllc((String) vector[7]);
        nodo.setTemp((String) vector[8]);
        nodo.setCc((String) vector[9]);
        nodo.setActitud((String) vector[10]);
        nodo.setProblemas((String) vector[11]);
        nodo.setDiagnostico((String) vector[12]);
        nodo.setPlan((String) vector[13]);
        nodo.setTratamiento((String) vector[14]);
        nodo.setRecomendacion((String) vector[15]);
        nodo.setUsuario((String) vector[16]);
        nodo.setFecha((String) vector[17]);
        nodo.setNervioso(String.valueOf(vector[18]));
        nodo.setDigestivo(String.valueOf(vector[19]));
        nodo.setRespiratorio(String.valueOf(vector[20]));
        nodo.setCardiaco(String.valueOf(vector[21]));
        nodo.setUrinario(String.valueOf(vector[22]));
        nodo.setMusculo(String.valueOf(vector[23]));
        nodo.setPiel(String.valueOf(vector[24]));
        nodo.setOidos(String.valueOf(vector[25]));
        nodo.setOjos(String.valueOf(vector[26]));
        nodo.setMucosa((String) vector[27]);
        nodo.setLlenadoCapilar((String) vector[28]);
        nodo.setCaracteristicas((String) vector[29]);
        nodo.setHidratacion((String) vector[30]);
        nodo.setNutricional((String) vector[31]);
        nodo.setOrganosSentidos((String) vector[32]);
        nodo.setGanglios((String) vector[33]);
        nodo.setProximoControl((String) vector[34]);
        nodo.setFotos((String) vector[35]);
        nodo.setExamenes((String) vector[36]);
        nodo.setEndocrino((String) vector[37]);
        return nodo;
    }

    public Object[] pasarDatosSeguimiento(ndSeguimiento nodo) {
        Object[] vector = {nodo.getFecha(), nodo.getUsuario(), nodo.getEvolucion(), nodo.getObservaciones(), nodo.getFotos(), nodo.getConsulta(), nodo.getExamen1(), nodo.getExamen2(), nodo.getExamen3(), nodo.getExamen4(),
            nodo.getFc(), nodo.getTllc(), nodo.getFr(), nodo.getT(), nodo.getPulso(), nodo.getCc(), nodo.getPeso(), nodo.getMucosa()};
        return vector;
    }

    public ndSeguimiento llenarSeguimiento(Object[] vector) {
        ndSeguimiento nodo = new ndSeguimiento();
        nodo.setId((String) vector[0]);
        nodo.setFecha((String) vector[1]);
        nodo.setUsuario((String) vector[2]);
        nodo.setEvolucion((String) vector[3]);
        nodo.setObservaciones((String) vector[4]);
        nodo.setFotos((String) vector[5]);
        nodo.setConsulta((String) vector[6]);
        nodo.setExamen1((String) vector[7]);
        nodo.setExamen2((String) vector[8]);
        nodo.setExamen3((String) vector[9]);
        nodo.setExamen4((String) vector[10]);
        nodo.setFc((String) vector[11]);
        nodo.setTllc((String) vector[12]);
        nodo.setFr((String) vector[13]);
        nodo.setT((String) vector[14]);
        nodo.setPulso((String) vector[15]);
        nodo.setCc((String) vector[16]);
        nodo.setPeso((String) vector[17]);
        nodo.setMucosa((String) vector[18]);
        return nodo;
    }

    /* INICIO PARQUEADERO */
    public ndParqueadero1 llenarParqueadero(Object[] vector) {

        ndParqueadero1 nodo = new ndParqueadero1();

        nodo.setId((String) vector[0]);
        nodo.setPlaca((String) vector[1]);
        nodo.setUsuario((String) vector[2]);
        nodo.setfIngreso(String.valueOf(vector[3]));
        nodo.setfSalida(String.valueOf(vector[4]));
        nodo.sethIngreso(String.valueOf(vector[5]));
        nodo.sethSalida(String.valueOf(vector[6]));
        nodo.setHoras(String.valueOf(vector[7]));
        nodo.setValHora(String.valueOf(vector[8]));
        nodo.setTotal(String.valueOf(vector[9]));

        return nodo;
    }

    public Object[] pasarDatosParqueadero(ndParqueadero1 nodo) {

        Object[] vector = {nodo.getId(), nodo.getPlaca(), nodo.getUsuario(), nodo.getfIngreso(), nodo.getfSalida()};

        return vector;
    }

    public Object[] pasarValoresParqueadero(ndParqueadero1 nodo) {

        Object[] vector = {nodo.gethIngreso(), nodo.gethSalida(), nodo.getHoras(), nodo.getValHora(), nodo.getTotal()};

        return vector;
    }

    public Object[] pasarDatosParqueaderoAutos(ndParqueadero nodo) {
        Object[] vector = {nodo.getId(), nodo.getPlaca(), nodo.getProducto(), nodo.getfIngreso(), nodo.getfSalida(),
            nodo.gethIngreso(), nodo.gethSalida(), nodo.getHoras(), nodo.getDias(), nodo.getNoches(), nodo.getFull(),
            nodo.getObservacion(), nodo.getEstado(), nodo.getFactura()};

        return vector;
    }

    public ndParqueadero llenarParqueaderoAutos(Object[] vector) {

        ndParqueadero nodo = new ndParqueadero();

        nodo.setId((String) vector[0]);
        nodo.setPlaca((String) vector[1]);
        nodo.setProducto((String) vector[2]);
        nodo.setfIngreso((String) vector[3]);
        nodo.setfSalida((String) vector[4]);
        nodo.sethIngreso((String) vector[5]);
        nodo.sethSalida((String) vector[6]);
        nodo.setHoras((String) vector[7]);
        nodo.setDias((String) vector[8]);
        nodo.setNoches(String.valueOf(vector[9]));
        nodo.setFull((String) vector[10]);
        nodo.setObservacion((String) vector[11]);
        nodo.setEstado((String) vector[12]);
        nodo.setFactura((String) vector[13]);
        return nodo;
    }

    public Object[] pasarDatosLavado(ndLavado nodo) {

        Object[] vector = {nodo.getId(), nodo.getEmpleado(), nodo.getServicio(), nodo.getUsuario(), nodo.getFecha(), nodo.getFechaRealizado(), nodo.getPlaca(), nodo.getCliente(),
            nodo.getNombre(), nodo.getLiquidado(), nodo.getTipoVehiculo(), nodo.getMarca(), nodo.getoServicio()};

        return vector;
    }

    public ndLavado llenarLavado(Object[] vector) {

        ndLavado nodo = new ndLavado();

        nodo.setId((String) vector[0]);
        nodo.setEmpleado((String) vector[1]);
        nodo.setServicio((String) vector[2]);
        nodo.setUsuario((String) vector[3]);
        nodo.setFecha((String) vector[4]);
        nodo.setFechaRealizado((String) vector[5]);
        nodo.setPlaca((String) vector[6]);
        nodo.setCliente((String) vector[7]);
        nodo.setNombre((String) vector[8]);
        nodo.setLiquidado((String) vector[9]);
        nodo.setTipoVehiculo((String) vector[10]);
        nodo.setMarca((String) vector[11]);
        nodo.setoServicio((String) vector[12]);

        return nodo;
    }

    /* FIN PARQUEADERO */
// MEDICO // MEDICO // MEDICO // MEDICO // MEDICO // MEDICO // MEDICO // MEDICO //
    public ndInformacionPrestador llenarInformacionPrestador(Object[] vector) {

        ndInformacionPrestador nodo = new ndInformacionPrestador();

        nodo.setId((String) vector[0]);
        nodo.setNombre((String) vector[1]);
        nodo.setTipo((String) vector[2]);
        nodo.setNumeroId((String) vector[3]);
        nodo.setCodigoHabilitador((String) vector[4]);
        nodo.setDireccion((String) vector[5]);
        nodo.setIndicativo((String) vector[6]);
        nodo.setNumero((String) vector[7]);
        nodo.setDepartamento((String) vector[8]);
        nodo.setMunicipio((String) vector[9]);
        nodo.setEntidad((String) vector[10]);
        nodo.setCodigoEntidad((String) vector[11]);

        return nodo;
    }

    public Object[] pasarDatosInformacionPrestador(ndInformacionPrestador nodo) {

        Object[] vector = {nodo.getId(), nodo.getNombre(), nodo.getTipo(), nodo.getNumeroId(), nodo.getCodigoHabilitador(), nodo.getDireccion(), nodo.getIndicativo(), nodo.getNumero(),
            nodo.getDepartamento(), nodo.getMunicipio(), nodo.getEntidad(), nodo.getCodigoEntidad()};

        return vector;
    }

    public ndOrdenServicio llenarOrdenServicio(Object[] vector) {

        ndOrdenServicio nodo = new ndOrdenServicio();

        nodo.setId((String) vector[0]);
        nodo.setOrden((String) vector[1]);
        nodo.setUsuario((String) vector[2]);
        nodo.setFecha((String) vector[3]);
        nodo.setPoliza((String) vector[4]);
        nodo.setConcepto((String) vector[5]);
        nodo.setAcobrar((String) vector[6]);
        nodo.setAutorizacion((String) vector[7]);
        nodo.setFinalidad((String) vector[8]);
        nodo.setCausa((String) vector[9]);
        nodo.setAmbito((String) vector[10]);
        nodo.setPersonal((String) vector[11]);
        nodo.setForma((String) vector[12]);
        nodo.setCopago(String.valueOf(vector[13]));
        nodo.setComision(String.valueOf(vector[14]));
        nodo.setDescuento(String.valueOf(vector[15]));
        nodo.setValor(String.valueOf(vector[16]));
        nodo.setCantidad(String.valueOf(vector[17]));
        nodo.setCuota(String.valueOf(vector[18]));
        nodo.setPaciente((String) vector[19]);
        nodo.setCie((String) vector[20]);
        nodo.setCup((String) vector[21]);
        nodo.setFechaSistema((String) vector[22]);
        nodo.setTipo((String) vector[23]);
        nodo.setCodigoIps((String) vector[24]);
        nodo.setCodigoConsecutivo((String) vector[25]);
        nodo.setActoQuirurgico((String) vector[26]);
        nodo.setCantidadRealizada((String) vector[27]);
        nodo.setConsecutivoEps((String) vector[28]);
        nodo.setCodigoConsulta((String) vector[29]);
        nodo.setMedico((String) vector[30]);
        nodo.setHora((String) vector[31]);
        nodo.setConvenio((String) vector[32]);
        return nodo;
    }

    public Object[] pasarDatosCertificadoMedico(ndCertificadoMedico nodo) {

        Object[] vector = {
            nodo.getId(), nodo.getHistoria(), nodo.getResponsable(), nodo.getNombre(), nodo.getParentesco(), nodo.getMotivo(), nodo.getEnfermedad(),
            nodo.getRevision(), nodo.getInspeccion(), nodo.getTemp(), nodo.getPeso(), nodo.getEstatura(), nodo.getFc(), nodo.getFr(), nodo.getPa(),
            nodo.getCefalico(), nodo.getAbdominal(), nodo.getImc(), nodo.getCabeza(), nodo.getOjos(), nodo.getAgudezaVisual(), nodo.getOidos(),
            nodo.getNariz(), nodo.getBoca(), nodo.getCuello(), nodo.getCardioPulmonar(), nodo.getTorax(), nodo.getAbdomen(), nodo.getGenitoUrinario(),
            nodo.getAno(), nodo.getExtremidades(), nodo.getNeurologico(), nodo.getPies(), nodo.getOsteoMuscular(), nodo.getDiagPrincipal(),
            nodo.getDiagRelacionado(), nodo.getDiagRelacionado2(), nodo.getDiagRelacionado3(), nodo.getTratamiento(), nodo.getRecomendaciones(),
            nodo.getCup(), nodo.getUsuario(), nodo.getFecha(), nodo.getPatologicos(), nodo.getQuirugicos(), nodo.getAlergicos(), nodo.getTraumaticos(),
            nodo.getGinecoobstetricos(), nodo.getFamiliares(), nodo.getSocial(), nodo.getMuscular()
        };

        return vector;
    }

    public ndRecordatorio llenarRecordatorio(Object[] vector) {
        ndRecordatorio nodo = new ndRecordatorio();

        nodo.setId((String) vector[0]);
        nodo.setFecha((String) vector[1]);
        nodo.setHora((String) vector[2]);
        nodo.setTipo((String) vector[3]);
        nodo.setTipoTercero((String) vector[4]);
        nodo.setTercero((String) vector[5]);
        nodo.setAsunto((String) vector[6]);
        nodo.setDescripcion((String) vector[7]);
        nodo.setEstado((String) vector[8]);
        nodo.setAlerta((String) vector[9]);
        nodo.setUsuario((String) vector[10]);
        nodo.setFechaRegistro((String) vector[11]);
        return nodo;
    }

    public Object[] pasarDatosRecordatorio(ndRecordatorio nodo) {
        Object[] vector = {nodo.getId(), nodo.getFecha(), nodo.getHora(), nodo.getTipo(), nodo.getTipoTercero(), nodo.getTercero(), nodo.getAsunto(), nodo.getDescripcion(),
            nodo.getEstado(), nodo.getAlerta(), nodo.getUsuario(), nodo.getFechaRegistro()};
        return vector;
    }

    public ndCertificadoMedico llenarCertificadoMedico(Object[] vector) {

        ndCertificadoMedico nodo = new ndCertificadoMedico();

        nodo.setId((String) vector[0]);
        nodo.setHistoria((String) vector[1]);
        nodo.setResponsable((String) vector[2]);
        nodo.setNombre((String) vector[3]);
        nodo.setParentesco((String) vector[4]);
        nodo.setMotivo((String) vector[5]);
        nodo.setEnfermedad((String) vector[6]);
        nodo.setRevision((String) vector[7]);
        nodo.setInspeccion((String) vector[8]);
        nodo.setTemp((String) vector[9]);
        nodo.setPeso((String) vector[10]);
        nodo.setEstatura((String) vector[11]);
        nodo.setFc((String) vector[12]);
        nodo.setFr((String) vector[13]);
        nodo.setPa((String) vector[14]);
        nodo.setCefalico((String) vector[15]);
        nodo.setAbdominal((String) vector[16]);
        nodo.setImc((String) vector[17]);
        nodo.setCabeza((String) vector[18]);
        nodo.setOjos((String) vector[19]);
        nodo.setAgudezaVisual((String) vector[20]);
        nodo.setOidos((String) vector[21]);
        nodo.setNariz((String) vector[22]);
        nodo.setBoca((String) vector[23]);
        nodo.setCuello((String) vector[24]);
        nodo.setCardioPulmonar((String) vector[25]);
        nodo.setTorax((String) vector[26]);
        nodo.setAbdomen((String) vector[27]);
        nodo.setGenitoUrinario((String) vector[28]);
        nodo.setAno((String) vector[29]);
        nodo.setExtremidades((String) vector[30]);
        nodo.setNeurologico((String) vector[31]);
        nodo.setPies((String) vector[32]);
        nodo.setOsteoMuscular((String) vector[33]);
        nodo.setDiagPrincipal((String) vector[34]);
        nodo.setDiagRelacionado((String) vector[35]);
        nodo.setDiagRelacionado2((String) vector[36]);
        nodo.setDiagRelacionado3((String) vector[37]);
        nodo.setTratamiento((String) vector[38]);
        nodo.setRecomendaciones((String) vector[39]);
        nodo.setCup((String) vector[40]);
        nodo.setUsuario((String) vector[41]);
        nodo.setFecha((String) vector[42]);
        nodo.setPatologicos((String) vector[43]);
        nodo.setQuirugicos((String) vector[44]);
        nodo.setAlergicos((String) vector[45]);
        nodo.setTraumaticos((String) vector[46]);
        nodo.setGinecoobstetricos((String) vector[47]);
        nodo.setFamiliares((String) vector[48]);
        nodo.setSocial((String) vector[49]);
        nodo.setMuscular((String) vector[50]);
        return nodo;
    }

    public Object[] pasarDatosOrdenServicio(ndOrdenServicio nodo) {

        Object[] vector = {nodo.getId(), nodo.getOrden(), nodo.getUsuario(), nodo.getFecha(), nodo.getPoliza(), nodo.getConcepto(), nodo.getAcobrar(), nodo.getAutorizacion(),
            nodo.getFinalidad(), nodo.getCausa(), nodo.getAmbito(), nodo.getPersonal(), nodo.getForma(), nodo.getPaciente(), nodo.getCie(), nodo.getCup(), nodo.getFechaSistema(),
            nodo.getTipo(), nodo.getCodigoIps(), nodo.getCodigoConsecutivo(), nodo.getActoQuirurgico(), nodo.getCantidadRealizada(), nodo.getConsecutivoEps(), nodo.getCodigoConsulta(), nodo.getMedico(), nodo.getHora(), nodo.getConvenio()};

        return vector;
    }

    public Object[] pasarValoresOrdenServicio(ndOrdenServicio nodo) {

        Object[] vector = {nodo.getCopago(), nodo.getComision(), nodo.getDescuento(), nodo.getValor(), nodo.getCantidad(), nodo.getCuota()};

        return vector;
    }

    public ndNotaEnfermeria llenarNotaEnfermeria(Object[] vector) {
        ndNotaEnfermeria nodo = new ndNotaEnfermeria();
        nodo.setId((String) vector[0]);
        nodo.setPaciente((String) vector[1]);
        nodo.setFecha((String) vector[2]);
        nodo.setInformacion((String) vector[3]);

        return nodo;
    }

    public Object[] pasarDatosNotaEnfermeria(ndNotaEnfermeria nodo) {

        Object[] vector = {nodo.getId(), nodo.getPaciente(), nodo.getFecha(), nodo.getInformacion()};

        return vector;
    }

    public Object[] pasarDatosIncapacidadOf(ndIncapacidadOf nodo) {
        Object[] vector = {nodo.getId(), nodo.getConsulta(), nodo.getFechainicio(), nodo.getFechavencimiento(), nodo.getCup(), nodo.getDescripcion(),
            nodo.getCie(), nodo.getHistorial()};
        return vector;
    }

    public Object[] pasarValoresIncapacidadOf(ndIncapacidadOf nodo) {
        Object[] vector = {nodo.getDias()};
        return vector;
    }

    public ndIncapacidad llenarIncapacidad(Object[] vector) {

        ndIncapacidad nodo = new ndIncapacidad();

        nodo.setId((String) vector[0]);
        nodo.setConsulta((String) vector[1]);
        nodo.setFechainicio((String) vector[2]);
        nodo.setFechavencimiento((String) vector[3]);
        nodo.setCup((String) vector[4]);
        nodo.setDescripcion((String) vector[5]);
        nodo.setDias(String.valueOf(vector[6]));
        nodo.setCie((String) vector[7]);
        nodo.setFechaRegistro((String) vector[8]);
        nodo.setUsuarioRegistro((String) vector[9]);

        return nodo;
    }

    public ndIncapacidadOf llenarIncapacidadOf(Object[] vector) {

        ndIncapacidadOf nodo = new ndIncapacidadOf();

        nodo.setId((String) vector[0]);
        nodo.setConsulta((String) vector[1]);
        nodo.setFechainicio((String) vector[2]);
        nodo.setFechavencimiento((String) vector[3]);
        nodo.setCup((String) vector[4]);
        nodo.setDescripcion((String) vector[5]);
        nodo.setDias(String.valueOf(vector[6]));
        nodo.setCie((String) vector[7]);
        nodo.setHistorial((String) vector[8]);

        return nodo;
    }

    public ndRemision llenarRemision(Object[] vector) {

        ndRemision nodo = new ndRemision();

        nodo.setId((String) vector[0]);
        nodo.setFecha((String) vector[1]);
        nodo.setTipo((String) vector[2]);
        nodo.setEspecialidad((String) vector[3]);
        nodo.setNombres((String) vector[4]);
        nodo.setApellidos((String) vector[5]);
        nodo.setEdad(String.valueOf(vector[6]));
        nodo.setTipoId((String) vector[7]);
        nodo.setIdentificacion((String) vector[8]);
        nodo.setTelefono((String) vector[9]);
        nodo.setResumen((String) vector[10]);
        nodo.setTratamientos((String) vector[11]);
        nodo.setDiagnosticos((String) vector[12]);
        nodo.setRemision((String) vector[13]);
        nodo.setDiag1((String) vector[14]);
        nodo.setDiag2((String) vector[15]);
        nodo.setNombre1((String) vector[16]);
        nodo.setNombre2((String) vector[17]);
        nodo.setMedico((String) vector[18]);
        nodo.setCedulaMedico((String) vector[19]);
        nodo.setRegistro((String) vector[20]);

        return nodo;
    }

    public Object[] pasarDatosRemision(ndRemision nodo) {

        Object[] vector = {nodo.getId(), nodo.getFecha(), nodo.getTipo(), nodo.getEspecialidad(), nodo.getNombres(), nodo.getApellidos(), nodo.getEdad(),
            nodo.getTipoId(), nodo.getIdentificacion(), nodo.getTelefono(), nodo.getResumen(), nodo.getTratamientos(), nodo.getDiagnosticos(), nodo.getRemision(),
            nodo.getDiag1(), nodo.getDiag2(), nodo.getNombre1(), nodo.getNombre2(), nodo.getMedico(), nodo.getCedulaMedico(), nodo.getRegistro()};

        return vector;
    }

    public Object[] pasarDatosIncapacidad(ndIncapacidad nodo) {

        Object[] vector = {nodo.getId(), nodo.getConsulta(), nodo.getFechainicio(), nodo.getFechavencimiento(), nodo.getCup(),
            nodo.getDescripcion(), nodo.getCie(), nodo.getFechaRegistro(), nodo.getUsuarioRegistro()};

        return vector;
    }

    public Object[] pasarValoresIncapacidad(ndIncapacidad nodo) {

        Object[] vector = {nodo.getDias()};

        return vector;
    }

    public ndTablaDiagnostica llenarTablaDiagnostica(Object[] vector) {

        ndTablaDiagnostica nodo = new ndTablaDiagnostica();

        nodo.setDiagnostico((String) vector[0]);
        nodo.setConcepto((String) vector[1]);
        nodo.setValor(String.valueOf(vector[2]));

        return nodo;
    }

    public Object[] pasarDatosTablaDiagnostica(ndTablaDiagnostica nodo) {

        Object[] vector = {nodo.getDiagnostico(), nodo.getConcepto()};

        return vector;
    }

    public Object[] pasarValoresTablaDiagnostica(ndTablaDiagnostica nodo) {

        Object[] vector = {nodo.getValor()};

        return vector;
    }

    public ndAyudaDiagnostica llenarAyudaDiagnostica(Object[] vector) {

        ndAyudaDiagnostica nodo = new ndAyudaDiagnostica();

        nodo.setId((String) vector[0]);
        nodo.setHistoria((String) vector[1]);
        nodo.setFecha((String) vector[2]);
        nodo.setObservaciones((String) vector[3]);
        nodo.setValor(String.valueOf(vector[4]));
        nodo.setUsuarioRegistro((String) vector[5]);

        return nodo;
    }

    public ndAyudaDiagnosticaOf llenarAyudaDiagnosticaOf(Object[] vector) {

        ndAyudaDiagnosticaOf nodo = new ndAyudaDiagnosticaOf();

        nodo.setId((String) vector[0]);
        nodo.setHistoria((String) vector[1]);
        nodo.setFecha((String) vector[2]);
        nodo.setObservaciones((String) vector[3]);
        nodo.setValor(String.valueOf(vector[4]));

        return nodo;
    }

    public Object[] pasarValoresAyudaDiagnosticaOf(ndAyudaDiagnosticaOf nodo) {
        Object[] vector = {nodo.getValor()};
        return vector;
    }

    public Object[] pasarDatosAyudaDiagnosticaOf(ndAyudaDiagnosticaOf nodo) {
        Object[] vector = {nodo.getId(), nodo.getHistoria(), nodo.getFecha(), nodo.getObservaciones()};
        return vector;
    }

    public Object[] pasarDatosAyudaDiagnostica(ndAyudaDiagnostica nodo) {

        Object[] vector = {nodo.getId(), nodo.getHistoria(), nodo.getFecha(), nodo.getObservaciones(), nodo.getUsuarioRegistro()};

        return vector;
    }

    public Object[] pasarValoresAyudaDiagnostica(ndAyudaDiagnostica nodo) {

        Object[] vector = {nodo.getValor()};

        return vector;
    }

    public ndMedicamentos llenarMedicamentos(Object[] vector) {

        ndMedicamentos nodo = new ndMedicamentos();

        nodo.setId((String) vector[0]);
        nodo.setNombre((String) vector[1]);
        nodo.setRecomendacion((String) vector[2]);
        nodo.setUsuario((String) vector[3]);

        return nodo;
    }

    public Object[] pasarDatosMedicamentos(ndMedicamentos nodo) {

        Object[] vector = {nodo.getNombre(), nodo.getRecomendacion(), nodo.getUsuario()};

        return vector;
    }

    public Object[] pasarDatosMedicamentos2(ndMedicamentos nodo) {

        Object[] vector = {nodo.getId(), nodo.getNombre(), nodo.getRecomendacion()};

        return vector;
    }

    public ndTablaFormula llenarTablaFormula(Object[] vector) {
        ndTablaFormula nodo = new ndTablaFormula();
        nodo.setFormula((String) vector[0]);
        nodo.setPrescripcion((String) vector[1]);
        nodo.setPosologia((String) vector[2]);
        nodo.setValor(String.valueOf(vector[3]));
        nodo.setCantidad((String) vector[4]);
        nodo.setMedida((String) vector[5]);

        return nodo;
    }

    public ndTablaFormulaOf llenarTablaFormulaOf(Object[] vector) {

        ndTablaFormulaOf nodo = new ndTablaFormulaOf();

        nodo.setFormula((String) vector[0]);
        nodo.setPrescripcion((String) vector[1]);
        nodo.setPosologia((String) vector[2]);
        nodo.setValor(String.valueOf(vector[3]));
        nodo.setCantidad((String) vector[4]);
        nodo.setMedida((String) vector[5]);

        return nodo;
    }

    public Object[] pasarDatosTablaFormulaOf(ndTablaFormulaOf nodo) {
        Object[] vector = {nodo.getFormula(), nodo.getPrescripcion(), nodo.getPosologia(), nodo.getCantidad(),
            nodo.getMedida()};
        return vector;
    }

    public Object[] pasarValoresTablaFormulaOf(ndTablaFormulaOf nodo) {
        Object[] vector = {nodo.getValor()};
        return vector;
    }

    public Object[] pasarDatosTablaFormula(ndTablaFormula nodo) {

        Object[] vector = {nodo.getFormula(), nodo.getPrescripcion(), nodo.getPosologia(), nodo.getCantidad(), nodo.getMedida()};

        return vector;
    }

    public Object[] pasarValoresTablaFormula(ndTablaFormula nodo) {

        Object[] vector = {nodo.getValor()};

        return vector;
    }

    public ndFormulaVeterinaria llenarFormulaVeterinaria(Object[] vector) {
        ndFormulaVeterinaria nodo = new ndFormulaVeterinaria();
        nodo.setId((String) vector[0]);
        nodo.setHistoria((String) vector[1]);
        nodo.setFecha((String) vector[2]);
        nodo.setUsuario((String) vector[3]);
        nodo.setValor(String.valueOf(vector[4]));
        nodo.setObservacion((String) vector[5]);
        return nodo;
    }

    public ndFormulaMedica llenarFormulaMedica(Object[] vector) {

        ndFormulaMedica nodo = new ndFormulaMedica();

        nodo.setId((String) vector[0]);
        nodo.setHistoria((String) vector[1]);
        nodo.setFecha((String) vector[2]);
        nodo.setUsuario((String) vector[3]);
        nodo.setValor(String.valueOf(vector[4]));
        nodo.setObservacion((String) vector[5]);
        nodo.setFrecCardiaca((String) vector[6]);
        nodo.setPresionArterial((String) vector[7]);
        nodo.setPeso((String) vector[8]);
        nodo.setFrecRespiratoria((String) vector[9]);
        nodo.setTemperatura((String) vector[10]);
        nodo.setEstatura((String) vector[11]);

        return nodo;
    }

    public Object[] pasarDatosFormulaVeterinaria(ndFormulaVeterinaria nodo) {
        Object[] vector = {nodo.getId(), nodo.getHistoria(), nodo.getFecha(), nodo.getUsuario(), nodo.getObservacion()};
        return vector;
    }

    public Object[] pasarValoresFormulaVeterinaria(ndFormulaVeterinaria nodo) {
        Object[] vector = {nodo.getValor()};
        return vector;
    }

    public Object[] pasarDatosFormulaMedica(ndFormulaMedica nodo) {

        Object[] vector = {nodo.getId(), nodo.getHistoria(), nodo.getFecha(), nodo.getUsuario(), nodo.getObservacion(),
            nodo.getFrecCardiaca(), nodo.getPresionArterial(), nodo.getPeso(), nodo.getFrecRespiratoria(), nodo.getTemperatura(),
            nodo.getEstatura()};

        return vector;
    }

    public Object[] pasarValoresFormulaMedica(ndFormulaMedica nodo) {

        Object[] vector = {nodo.getValor()};

        return vector;
    }

    public ndEps llenarEps(Object[] vector) {

        ndEps nodo = new ndEps();

        nodo.setId((String) vector[0]);
        nodo.setDato1((String) vector[1]);
        nodo.setDato2((String) vector[2]);
        nodo.setDato3((String) vector[3]);
        nodo.setDato4((String) vector[4]);
        nodo.setDato5((String) vector[5]);
        nodo.setDato6((String) vector[6]);
        nodo.setTarifa(String.valueOf(vector[7]));
        nodo.setPorcentaje(String.valueOf(vector[8]));

        return nodo;
    }

    public Object[] pasarDatosEps(ndEps nodo) {
        Object[] vector = {nodo.getId(), nodo.getDato1(), nodo.getDato2(), nodo.getDato3(), nodo.getDato4(), nodo.getDato5(), nodo.getDato6()};
        return vector;
    }

    public Object[] pasarValoresEps(ndEps nodo) {
        Object[] vector = {nodo.getTarifa(), nodo.getPorcentaje()};
        return vector;
    }

    public ndConvenio llenarConvenio(Object[] vector) {
        ndConvenio nodo = new ndConvenio();

        nodo.setNit((String) vector[0]);
        nodo.setConvenio((String) vector[1]);
        nodo.setContrato((String) vector[2]);
        nodo.setFechaFinalizacion((String) vector[3]);
        nodo.setMontoTotal(String.valueOf(vector[4]));
        nodo.setEstado((String) vector[5]);
        nodo.setTipoFactura((String) vector[6]);
        nodo.setPorcentaje((String) vector[7]);

        return nodo;
    }

    public Object[] pasarDatosConvenio(ndConvenio nodo) {
        Object[] vector = {nodo.getNit(), nodo.getConvenio(), nodo.getContrato(), nodo.getFechaFinalizacion(), nodo.getEstado(), nodo.getTipoFactura(), nodo.getPorcentaje()};
        return vector;
    }

    public Object[] pasarValoresConvenio(ndConvenio nodo) {

        Object[] vector = {nodo.getMontoTotal()};

        return vector;
    }

    public ndConsultaClinica llenarConsultaClinica(Object[] vector) {

        ndConsultaClinica nodo = new ndConsultaClinica();

        nodo.setId((String) vector[0]);
        nodo.setHistoria((String) vector[1]);
        nodo.setResponsable((String) vector[2]);
        nodo.setNombre((String) vector[3]);
        nodo.setParentesco((String) vector[4]);
        nodo.setMotivo((String) vector[5]);
        nodo.setT((String) vector[6]);
        nodo.setPeso((String) vector[7]);
        nodo.setTalla((String) vector[8]);
        nodo.setFc((String) vector[9]);
        nodo.setFr((String) vector[10]);
        nodo.setPa((String) vector[11]);
        nodo.setCefalico((String) vector[12]);
        nodo.setAbdominal((String) vector[13]);
        nodo.setFisico1((String) vector[14]);
        nodo.setFisico2((String) vector[15]);
        nodo.setFisico3((String) vector[16]);
        nodo.setProblemas((String) vector[17]);
        nodo.setCie1((String) vector[18]);
        nodo.setCie2((String) vector[19]);
        nodo.setCie3((String) vector[20]);
        nodo.setCie4((String) vector[21]);
        nodo.setTratamiento((String) vector[22]);
        nodo.setRecomendaciones((String) vector[23]);
        nodo.setCup((String) vector[24]);
        nodo.setUsuario((String) vector[25]);
        nodo.setFecha((String) vector[26]);
        nodo.setRevision((String) vector[27]);
        nodo.setCabezaCuello(String.valueOf(vector[28]));
        nodo.setCardioPulmonar(String.valueOf(vector[29]));
        nodo.setAbdomen(String.valueOf(vector[30]));
        nodo.setPelvis(String.valueOf(vector[31]));
        nodo.setExtremidades(String.valueOf(vector[32]));
        nodo.setOrden((String) vector[33]);
        nodo.setOrganosSentidos((String) vector[34]);
        nodo.setMusculoEsqueletico((String) vector[35]);
        nodo.setNeurologico((String) vector[36]);
        nodo.setPielAnexos((String) vector[37]);
        nodo.setFamiliar((String) vector[38]);
        nodo.setFumador((String) vector[39]);
        nodo.setIngesta((String) vector[40]);
        nodo.setAlergias((String) vector[41]);
        nodo.setToxicos((String) vector[42]);
        nodo.setMedicamentos((String) vector[43]);
        nodo.setPatologicos((String) vector[44]);
        nodo.setPsiquiatricos((String) vector[45]);
        nodo.setQuirurgicos((String) vector[46]);
        nodo.setUltimaMenstruacion((String) vector[47]);
        nodo.setUltimaCitologia((String) vector[48]);
        nodo.setResultadosCitologia((String) vector[49]);
        nodo.setEmbarazos((String) vector[50]);
        nodo.setAbortos((String) vector[51]);
        nodo.setPartos((String) vector[52]);
        nodo.setCesarias((String) vector[53]);
        nodo.setHijosVivos((String) vector[54]);
        nodo.setPlanificacion((String) vector[55]);
        nodo.setFotos((String) vector[56]);
        nodo.setExamenes((String) vector[57]);
        nodo.setTelefonoResponsable((String) vector[58]);
        nodo.setEctopicos((String) vector[59]);
        nodo.setCup2((String) vector[60]);
        nodo.setObservacionProcedimientos((String) vector[61]);
        nodo.setOido((String) vector[62]);
        nodo.setVista((String) vector[63]);
        nodo.setNariz((String) vector[64]);
        nodo.setGusto((String) vector[65]);
        nodo.setTacto((String) vector[66]);
        nodo.setMenarca((String) vector[67]);
        nodo.setDiagnosticoEspecializado((String) vector[68]);
        nodo.setNutricionales((String) vector[69]);
        nodo.setTipoDiagnostico((String) vector[70]);
        nodo.setCc((String) vector[71]);
        nodo.setTorax((String) vector[72]);
        nodo.setAbdomen1((String) vector[73]);
        nodo.setExtremidades1((String) vector[74]);
        nodo.setNeurologico1((String) vector[75]);
        nodo.setPielYfaneras((String) vector[76]);
        nodo.setRevisionSistemas((String) vector[77]);
        nodo.setHistorialPersonal((String) vector[78]);
        nodo.setTraumaticos((String) vector[79]);
        nodo.setGluteos((String) vector[80]);
        nodo.setMamas((String) vector[81]);
        return nodo;
    }

    public ndConsultaLaboratorio llenarConsultaLaboratorio(Object[] vector) {

        ndConsultaLaboratorio nodo = new ndConsultaLaboratorio();

        nodo.setId((String) vector[0]);
        nodo.setHistoria((String) vector[1]);
        nodo.setResponsable((String) vector[2]);
        nodo.setNombre((String) vector[3]);
        nodo.setParentesco((String) vector[4]);
        nodo.setMotivo((String) vector[5]);
        nodo.setProblemas((String) vector[6]);
        nodo.setCie1((String) vector[7]);
        nodo.setCie2((String) vector[8]);
        nodo.setCie3((String) vector[9]);
        nodo.setCie4((String) vector[10]);
        nodo.setTratamiento((String) vector[11]);
        nodo.setRecomendaciones((String) vector[12]);
        nodo.setUsuario((String) vector[13]);
        nodo.setFecha((String) vector[14]);
        nodo.setOrden((String) vector[15]);
        nodo.setTelefonoResponsable((String) vector[16]);
        nodo.setDiagnosticoEspecializado((String) vector[17]);
        nodo.setTipoDiagnostico((String) vector[18]);
        return nodo;
    }

    public Object[] pasarDatosConsultaClinica(ndConsultaClinica nodo) {
        Object[] vector = {nodo.getId(), nodo.getHistoria(), nodo.getResponsable(), nodo.getNombre(), nodo.getParentesco(), nodo.getMotivo(), nodo.getT(), nodo.getPeso(), nodo.getTalla(), nodo.getFc(), nodo.getFr(), nodo.getPa(), nodo.getCefalico(), nodo.getAbdominal(), nodo.getFisico1(), nodo.getFisico2(), nodo.getFisico3(), nodo.getProblemas(), nodo.getCie1(), nodo.getCie2(), nodo.getCie3(), nodo.getCie4(), nodo.getTratamiento(), nodo.getRecomendaciones(), nodo.getCup(), nodo.getUsuario(), nodo.getFecha(),
            nodo.getRevision(), nodo.getCabezaCuello(), nodo.getCardioPulmonar(), nodo.getAbdomen(), nodo.getPelvis(), nodo.getExtremidades(),
            nodo.getOrden(), nodo.getOrganosSentidos(), nodo.getMusculoEsqueletico(), nodo.getNeurologico(), nodo.getPielAnexos(),
            nodo.getFamiliar(), nodo.getFumador(), nodo.getIngesta(), nodo.getAlergias(),
            nodo.getToxicos(), nodo.getMedicamentos(), nodo.getPatologicos(),
            nodo.getPsiquiatricos(), nodo.getQuirurgicos(), nodo.getUltimaMenstruacion(), nodo.getUltimaCitologia(),
            nodo.getResultadosCitologia(), nodo.getEmbarazos(), nodo.getAbortos(), nodo.getPartos(), nodo.getCesarias(),
            nodo.getHijosVivos(), nodo.getPlanificacion(), nodo.getFotos(), nodo.getExamenes(),
            nodo.getTelefonoResponsable(), nodo.getEctopicos(), nodo.getCup2(), nodo.getObservacionProcedimientos(), nodo.getOido(), nodo.getVista(),
            nodo.getNariz(), nodo.getGusto(), nodo.getTacto(), nodo.getMenarca(), nodo.getDiagnosticoEspecializado(), nodo.getNutricionales(),
            nodo.getTipoDiagnostico(), nodo.getCc(), nodo.getTorax(), nodo.getAbdomen1(), nodo.getExtremidades1(), nodo.getNeurologico1(),
            nodo.getPielYfaneras(), nodo.getRevisionSistemas(), nodo.getHistorialPersonal(), nodo.getTraumaticos(), nodo.getGluteos(), nodo.getMamas()
        };

        return vector;
    }

    public Object[] pasarDatosConsultaLaboratorio(ndConsultaLaboratorio nodo) {

        Object[] vector = {nodo.getId(), nodo.getHistoria(), nodo.getResponsable(), nodo.getNombre(), nodo.getParentesco(), nodo.getMotivo(),
            nodo.getProblemas(), nodo.getCie1(), nodo.getCie2(), nodo.getCie3(), nodo.getCie4(), nodo.getTratamiento(), nodo.getRecomendaciones(),
            nodo.getUsuario(), nodo.getFecha(), nodo.getOrden(), nodo.getTelefonoResponsable(), nodo.getDiagnosticoEspecializado(),
            nodo.getTipoDiagnostico()};

        return vector;
    }

    public ndHistoriaClinica llenarHistClinica(Object[] vector) {
        ndHistoriaClinica nodo = new ndHistoriaClinica();
        nodo.setId((String) vector[0]);
        nodo.setPaciente((String) vector[1]);
        nodo.setFecha((String) vector[2]);
        nodo.setHistoria((String) vector[3]);
        nodo.setMenstruacion((String) vector[4]);
        nodo.setCitologia((String) vector[5]);
        nodo.setResultadoCitologia((String) vector[6]);
        nodo.setEmbarazos((String) vector[7]);
        nodo.setPartos((String) vector[8]);
        nodo.setAbortos((String) vector[9]);
        nodo.setCesareas((String) vector[10]);
        nodo.setConsecutivos((String) vector[11]);
        nodo.setHijosVivos((String) vector[12]);
        nodo.setPersonal((String) vector[13]);
        nodo.setFamiliar((String) vector[14]);
        return nodo;
    }

    public Object[] pasarDatosHistClinica(ndHistoriaClinica nodo) {
        Object[] vector = {nodo.getId(), nodo.getPaciente(), nodo.getFecha(), nodo.getHistoria(), nodo.getMenstruacion(), nodo.getCitologia(),
            nodo.getResultadoCitologia(), nodo.getEmbarazos(), nodo.getPartos(), nodo.getAbortos(), nodo.getCesareas(), nodo.getConsecutivos(), nodo.getHijosVivos(), nodo.getPersonal(), nodo.getFamiliar()};
        return vector;
    }

    public ndSeguimientoClinica llenarSeguimientoClinica(Object[] vector) {
        ndSeguimientoClinica nodo = new ndSeguimientoClinica();
        nodo.setId((String) vector[0]);
        nodo.setFecha((String) vector[1]);
        nodo.setUsuario((String) vector[2]);
        nodo.setEvolucion((String) vector[3]);
        nodo.setObservaciones((String) vector[4]);
        nodo.setConsulta((String) vector[5]);
        nodo.setExamenesId((String) vector[6]);
        nodo.setExamenesDesc((String) vector[7]);
        nodo.setExamenesUrl((String) vector[8]);
        nodo.setT((String) vector[9]);
        nodo.setPeso((String) vector[10]);
        nodo.setPa((String) vector[11]);
        nodo.setFr((String) vector[12]);
        return nodo;
    }

    public Object[] pasarDatosSeguimientoClinica(ndSeguimientoClinica nodo) {
        Object[] vector = {nodo.getFecha(), nodo.getUsuario(), nodo.getEvolucion(), nodo.getObservaciones(), nodo.getConsulta(),
            nodo.getExamenesId(), nodo.getExamenesDesc(), nodo.getExamenesUrl(), nodo.getT(), nodo.getPeso(), nodo.getPa(), nodo.getFr()};
        return vector;
    }

    public ndCamposOrdenPredeterminada llenarCamposOrdenPredeterminada(Object[] vector) {

        ndCamposOrdenPredeterminada nodo = new ndCamposOrdenPredeterminada();

        nodo.setId((String) vector[0]);
        nodo.setTipo((String) vector[1]);
        nodo.setConcepto((String) vector[2]);
        nodo.setFinalidad((String) vector[3]);
        nodo.setCausaExterna((String) vector[4]);
        nodo.setServicio((String) vector[5]);
        nodo.setPersonal((String) vector[6]);
        nodo.setActoQuirurgico((String) vector[7]);
        nodo.setForma((String) vector[8]);
        nodo.setAmbito((String) vector[9]);
        nodo.setConsulta((String) vector[10]);
        return nodo;
    }

    public Object[] pasarDatosCamposOrdenPredeterminada(ndCamposOrdenPredeterminada nodo) {

        Object[] vector = {nodo.getId(), nodo.getTipo(), nodo.getConcepto(), nodo.getFinalidad(), nodo.getCausaExterna(), nodo.getServicio(),
            nodo.getPersonal(), nodo.getActoQuirurgico(), nodo.getForma(), nodo.getAmbito(), nodo.getConsulta()
        };

        return vector;
    }

    public ndEpsPrecargados llenarEpsPrecargados(Object[] vector) {
        ndEpsPrecargados nodo = new ndEpsPrecargados();

        nodo.setId((String) vector[0]);
        nodo.setNombre((String) vector[1]);
        nodo.setCodigoProteccionSocial((String) vector[2]);
        nodo.setConsecutivo((String) vector[3]);
        nodo.setContrato((String) vector[4]);
        nodo.setTelefono((String) vector[5]);
        nodo.setDireccion((String) vector[6]);

        return nodo;
    }

    public Object[] pasarDatosAgenda(ndAgenda nodo) {
        Object[] vector = {nodo.getId(), nodo.getCliente(), nodo.getFecha(), nodo.getHora(), nodo.getMotivo(), nodo.getaCargo(), nodo.getEstado(),
            nodo.getObservacion(), nodo.getMascota(), nodo.getProxima(), nodo.getDescripcion(), nodo.isGestion()};
        return vector;
    }

    public ndAgenda llenarAgenda(Object[] vector) {
        ndAgenda nodo = new ndAgenda();

        nodo.setId((String) vector[0]);
        nodo.setCliente((String) vector[1]);
        nodo.setFecha((String) vector[2]);
        nodo.setHora((String) vector[3]);
        nodo.setMotivo((String) vector[4]);
        nodo.setaCargo((String) vector[5]);
        nodo.setEstado((String) vector[6]);
        nodo.setObservacion((String) vector[7]);
        nodo.setMascota((String) vector[8]);
        nodo.setProxima((String) vector[9]);
        nodo.setDescripcion((String) vector[10]);

        try {
            nodo.setGestion((boolean) vector[11]);
        } catch (NullPointerException e) {
            nodo.setGestion(false);
        }

        return nodo;
    }

    public ndInventarioInicial llenarInventarioInicial(Object[] vector) {
        ndInventarioInicial nodo = new ndInventarioInicial();
        nodo.setProducto((String) vector[0]);
        nodo.setCosto((String) vector[1]);
        nodo.setConteo1((String) vector[2]);
        nodo.setConteo2((String) vector[3]);
        nodo.setConteo3((String) vector[4]);
        nodo.setTotal((String) vector[5]);
        nodo.setAuditoria1(String.valueOf(vector[6]));
        nodo.setAuditoria2(String.valueOf(vector[7]));
        nodo.setTotalAuditoria(String.valueOf(vector[8]));
        nodo.setFinalizado(String.valueOf(vector[9]));
        nodo.setBodega((String) vector[10]);
        return nodo;
    }

    public Object[] pasarDatosInventarioInicial(ndInventarioInicial nodo) {

        Object[] vector = {nodo.getProducto(), nodo.getCosto(), nodo.getConteo1(), nodo.getConteo2(), nodo.getConteo3(), nodo.getTotal(),
            nodo.getAuditoria1(), nodo.getAuditoria2(), nodo.getTotalAuditoria(), nodo.getFinalizado(), nodo.getBodega()

        };

        return vector;
    }

    public ndCxp llenarCxp(Object[] vector) {

        ndCxp nodo = new ndCxp();

        nodo.setIngreso((String) vector[0]);
        nodo.setTipo((String) vector[1]);
        nodo.setEstado((String) vector[2]);
        nodo.setRecibo((String) vector[3]);
        nodo.setVencimiento((String) vector[4]);
        nodo.setUsuario((String) vector[5]);
        nodo.setValor(String.valueOf(vector[6]));
        nodo.setPlazo(String.valueOf(vector[7]));
        nodo.setTerminal(String.valueOf(vector[8]));

        return nodo;
    }

    public ndGarantia llenarAbonos(Object[] vector) {
        ndGarantia nodo = new ndGarantia();

        nodo.setId((String) vector[0]);
        nodo.setCliente((String) vector[1]);
        nodo.setVendedor((String) vector[2]);
        nodo.setRed((String) vector[3]);
        nodo.setFechaFactura((String) vector[4]);
        nodo.setFechaVencimiento((String) vector[5]);
        nodo.setEfectivo(String.valueOf(vector[6]));
        nodo.setNc(String.valueOf(vector[7]));
        nodo.setCheque(String.valueOf(vector[8]));
        nodo.setTargeta(String.valueOf(vector[9]));
        nodo.setComprobante((String) vector[10]);
        nodo.setCotizacion((String) vector[11]);
        nodo.setAnula((String) vector[12]);
        try {
            nodo.setAnulada((boolean) vector[13]);
            nodo.setCredito((boolean) vector[14]);
        } catch (Exception e) {
            nodo.setAnulada(false);
            nodo.setCredito(false);
        }

        nodo.setTotal(String.valueOf(vector[15]));
        nodo.setDescuentos(String.valueOf(vector[16]));
        nodo.setIva(String.valueOf(vector[17]));
        nodo.setSubtotal(String.valueOf(vector[18]));
        nodo.setCxc((String) vector[19]);
        nodo.setUsuario((String) vector[20]);
        nodo.setRtIva(String.valueOf(vector[21]));
        nodo.setRtIca(String.valueOf(vector[22]));
        nodo.setRtFuente(String.valueOf(vector[23]));
        nodo.setOtros(String.valueOf(vector[24]));
        nodo.setObservaciones(String.valueOf(vector[25]));
        nodo.setFechaAlerta(String.valueOf(vector[26]));
        nodo.setTerminal(String.valueOf(vector[27]));
        nodo.setDevuelta(String.valueOf(vector[28]));
        nodo.setEstado((String) vector[29]);
        nodo.setFactura((String) vector[30]);
        nodo.setResolucion((String) vector[31]);
        nodo.setCopago(String.valueOf(vector[32]));
        nodo.setPlaca((String) vector[33]);
        nodo.setGarantia((String) vector[34]);
        nodo.setDiasGarantia((String) vector[35]);
        nodo.setRango((String) vector[36]);
        nodo.setTerminos((String) vector[37]);
        nodo.setNotaAnulacion((String) vector[38]);
        nodo.setConseMesa((String) vector[39]);
        nodo.setModeloContable((String) vector[40]);
        return nodo;
    }

    public Object[] pasarDatosCxp(ndCxp nodo) {

        Object[] vector = {nodo.getIngreso(), nodo.getTipo(), nodo.getEstado(), nodo.getRecibo(), nodo.getVencimiento(), nodo.getUsuario(), nodo.getTerminal()};

        return vector;
    }

    public Object[] pasarValoresCxp(ndCxp nodo) {

        Object[] vector = {nodo.getValor(), nodo.getPlazo()};

        return vector;
    }

    public ndCuota llenarCuota(Object[] vector) {

        ndCuota nodo = new ndCuota();

        nodo.setId((String) vector[0]);
        nodo.setContrato((String) vector[1]);
        nodo.setNumCuota((String) vector[2]);
        nodo.setFecha((String) vector[3]);
        nodo.setUsuario((String) vector[4]);
        nodo.setFechaAbono((String) vector[5]);
        nodo.setDocumentoAbono((String) vector[6]);
        nodo.setDocumentoMora((String) vector[7]);
        nodo.setAbonoCapital(String.valueOf(vector[8]));
        nodo.setAbonoInteres(String.valueOf(vector[9]));
        nodo.setTotal(String.valueOf(vector[10]));
        nodo.setMora(String.valueOf(vector[11]));
        nodo.setTotalAbono(String.valueOf(vector[12]));
        nodo.setSaldoAtrasado(String.valueOf(vector[13]));
        nodo.setSaldoMora(String.valueOf(vector[14]));
        nodo.setDocumentoAbonoMora((String) vector[15]);

        return nodo;
    }

    public Object[] pasarDatosCuota(ndCuota nodo) {

        Object[] vector = {nodo.getId(), nodo.getContrato(), nodo.getNumCuota(), nodo.getFecha(), nodo.getUsuario(), nodo.getFechaAbono(),
            nodo.getDocumentoAbono(), nodo.getDocumentoMora(), nodo.getDocumentoAbonoMora()};

        return vector;
    }

    public Object[] pasarValoresCuota(ndCuota nodo) {

        Object[] vector = {nodo.getAbonoCapital(), nodo.getAbonoInteres(), nodo.getTotal(), nodo.getMora(), nodo.getTotalAbono(),
            nodo.getSaldoAtrasado(), nodo.getSaldoMora()};

        return vector;
    }

    public ndPrestamo llenarPrestamo(Object[] vector) {

        ndPrestamo nodo = new ndPrestamo();

        nodo.setContrato((String) vector[0]);
        nodo.setFactura((String) vector[1]);
        nodo.setUsuario((String) vector[2]);
        nodo.setFecha((String) vector[3]);
        nodo.setCliente((String) vector[4]);
        nodo.setConvenio((String) vector[5]);
        nodo.setTipoServicio((String) vector[6]);
        nodo.setFechaAprovado((String) vector[7]);
        nodo.setFechaDesenvolso((String) vector[8]);
        nodo.setObservaciones((String) vector[9]);
        nodo.setValorSolicitado(String.valueOf(vector[10]));
        nodo.setValorAprovado(String.valueOf(vector[11]));
        nodo.setCuotas(String.valueOf(vector[12]));
        nodo.setInteres(String.valueOf(vector[13]));
        nodo.setTotalInteres(String.valueOf(vector[14]));
        nodo.setTotal(String.valueOf(vector[15]));
        nodo.setCuotaInicial(String.valueOf(vector[16]));
        nodo.setFactura2((String) vector[17]);

        return nodo;
    }

    public Object[] pasarDatosPrestamo(ndPrestamo nodo) {

        Object[] vector = {nodo.getContrato(), nodo.getFactura(), nodo.getUsuario(), nodo.getFecha(), nodo.getCliente(),
            nodo.getConvenio(), nodo.getTipoServicio(), nodo.getFechaAprovado(), nodo.getFechaDesenvolso(), nodo.getObservaciones(), nodo.getFactura2()};

        return vector;
    }

    public Object[] pasarValoresPrestamo(ndPrestamo nodo) {

        Object[] vector = {nodo.getValorSolicitado(), nodo.getValorAprovado(), nodo.getCuotas(), nodo.getInteres(),
            nodo.getTotalInteres(), nodo.getTotal(), nodo.getCuotaInicial()};

        return vector;
    }

    public ModeloContacto llenarTercero(Object[] vector) {

        ModeloContacto nodo = new ModeloContacto();

        nodo.setIdSistema((String) vector[0]);
        nodo.setId((String) vector[1]);
        nodo.setNombre((String) vector[2]);
        nodo.setTelefono((String) vector[3]);
        nodo.setCelular((String) vector[4]);
        nodo.setDireccion((String) vector[5]);
        nodo.setCiudad((String) vector[6]);
        nodo.setEmail((String) vector[7]);
        nodo.setDepartamento((String) vector[8]);
        nodo.setFecha((String) vector[9]);
        nodo.setUsuario((String) vector[10]);
        nodo.setTipo((String) vector[11]);
        nodo.setCompleta((String) vector[12]);
        nodo.setpNombre((String) vector[13]);
        nodo.setsNombre((String) vector[14]);
        nodo.setpApellido((String) vector[15]);
        nodo.setsApellido((String) vector[16]);
        nodo.setActividad((String) vector[17]);
        nodo.setNaturaleza((String) vector[18]);
        nodo.setRut((String) vector[19]);

        try {
            nodo.setActivo((boolean) vector[20]);
        } catch (NullPointerException e) {
            nodo.setActivo(false);
        }

        nodo.setPlazo((String) vector[21]);
        nodo.setCupo(String.valueOf(vector[22]));
        nodo.setPlacas((String) vector[23]);
        nodo.setPais((String) vector[24]);
        nodo.setSexo((String) vector[25]);
        nodo.setEstado((String) vector[26]);
        nodo.setOcupacion((String) vector[27]);
        nodo.setRegimen((String) vector[28]);
        nodo.setAfiliado((String) vector[29]);
        nodo.setTipoTrabajador((String) vector[30]);
        nodo.setZona((String) vector[31]);
        nodo.setNacimiento((String) vector[32]);
        nodo.setSangre((String) vector[33]);
        nodo.setEps((String) vector[34]);
        nodo.setTerminal((String) vector[35]);
        nodo.setReferido((String) vector[36]);
        nodo.setVendedor((String) vector[37]);
        nodo.setLista((String) vector[38]);
        nodo.setNota((String) vector[39]);
        nodo.setTipo2((String) vector[40]);
        nodo.setRango((String) vector[41]);
        nodo.setCodigoPostal((String) vector[42]);
        nodo.setConvenio((String) vector[43]);
        nodo.setConvenioActual((String) vector[44]);
        nodo.setReligion((String) vector[45]);
        nodo.setNombreMadre((String) vector[46]);
        nodo.setNombrePadre((String) vector[47]);
        nodo.setNombreResponsable((String) vector[48]);
        nodo.setParentescoResponsable((String) vector[49]);
        nodo.setTelefonoResponsable((String) vector[50]);
        nodo.setLugarNacimiento((String) vector[51]);
        nodo.setBarrio((String) vector[52]);
        nodo.setNombreContacto((String) vector[53]);
        nodo.setCargo((String) vector[54]);
        nodo.setTelefonoContacto((String) vector[55]);
        nodo.setWeb((String) vector[56]);
        nodo.setRnt((String) vector[57]);
        nodo.setTipoProveedor((String) vector[58]);
        nodo.setSalario(String.valueOf(vector[59]));
        nodo.setIngreso((String) vector[60]);
        nodo.setRetiro((String) vector[61]);

        try {
            nodo.setAgendar((boolean) vector[62]);
        } catch (NullPointerException e) {
            nodo.setAgendar(false);
        }

        nodo.setUsuarioAsociado((String) vector[63]);
        nodo.setArl((String) vector[64]);
        nodo.setCcf((String) vector[65]);
        nodo.setTipoTercero((String) vector[66]);
        nodo.setCodigoUsuario((String) vector[67]);

        try {
            nodo.setResponsableIva((boolean) vector[68]);
        } catch (NullPointerException e) {
            nodo.setResponsableIva(false);
        }

        return nodo;
    }

    public Object[] pasarDatosTercero(ModeloContacto nodo) {

        Object[] vector = {nodo.getIdSistema(), nodo.getId(), nodo.getNombre(), nodo.getTelefono(), nodo.getCelular(), nodo.getDireccion(), nodo.getCiudad(), nodo.getEmail(),
            nodo.getDepartamento(), nodo.getFecha(), nodo.getUsuario(), nodo.getTipo(), nodo.getCompleta(), nodo.getpNombre(), nodo.getsNombre(), nodo.getpApellido(),
            nodo.getsApellido(), nodo.getActividad(), nodo.getNaturaleza(), nodo.getRut(), nodo.isActivo(), nodo.getPlazo(), nodo.getPlacas(), nodo.getPais(), nodo.getSexo(),
            nodo.getEstado(), nodo.getOcupacion(), nodo.getRegimen(), nodo.getAfiliado(), nodo.getTipoTrabajador(), nodo.getZona(), nodo.getNacimiento(), nodo.getSangre(),
            nodo.getEps(), nodo.getTerminal(), nodo.getReferido(), nodo.getVendedor(), nodo.getLista(), nodo.getNota(), nodo.getTipo2(), nodo.getRango(), nodo.getCodigoPostal(),
            nodo.getConvenio(), nodo.getConvenioActual(), nodo.getReligion(),
            nodo.getNombreMadre(), nodo.getNombrePadre(), nodo.getNombreResponsable(), nodo.getParentescoResponsable(), nodo.getTelefonoResponsable(),
            nodo.getLugarNacimiento(), nodo.getBarrio(), nodo.getNombreContacto(), nodo.getCargo(), nodo.getTelefonoContacto(), nodo.getWeb(), nodo.getRnt(),
            nodo.getTipoProveedor(), nodo.getIngreso(), nodo.getRetiro(), nodo.isAgendar(), nodo.getUsuarioAsociado(), nodo.getArl(), nodo.getCcf(), nodo.getTipoTercero(),
            nodo.getCodigoUsuario(), nodo.isResponsableIva()
        };

        return vector;
    }

    public Object[] pasarValoresTercero(ModeloContacto nodo) {

        Object[] vector = {nodo.getCupo(), nodo.getSalario()};

        return vector;
    }

    public ndGrupo llenarGrupo(Object[] vector) {
        ndGrupo nodo = new ndGrupo();

        nodo.setCodigo((String) vector[0]);
        nodo.setNombre((String) vector[1]);
        nodo.setImagen((String) vector[2]);

        return nodo;
    }

    public ndOServicio llenarOServicio(Object[] vector) {
        ndOServicio nodo = new ndOServicio();
        nodo.setId((String) vector[0]);
        nodo.setPlaca((String) vector[1]);
        nodo.setTipo((String) vector[2]);
        nodo.setModelo((String) vector[3]);
        nodo.setNumeroChasis((String) vector[4]);
        nodo.setFechaCompra((String) vector[5]);
        nodo.setMarca((String) vector[6]);
        nodo.setKm((String) vector[7]);
        nodo.setNumeroMotor((String) vector[8]);
        nodo.setColor((String) vector[9]);
        nodo.setProblema((String) vector[10]);

        return nodo;
    }

    public ndFactura llenarFactura(Object[] vector) {

        ndFactura nodo = new ndFactura();

        nodo.setIdFactura((String) vector[0]);
        nodo.setCliente((String) vector[1]);
        nodo.setVendedor((String) vector[2]);
        nodo.setRed((String) vector[3]);
        nodo.setFechaFactura((String) vector[4]);
        nodo.setFechaVencimiento((String) vector[5]);
        nodo.setEfectivoGeneral(String.valueOf(vector[6]));
        nodo.setNcGeneral(String.valueOf(vector[7]));
        nodo.setChequeGeneral(String.valueOf(vector[8]));
        nodo.setTargetaGeneral(String.valueOf(vector[9]));
        nodo.setTotalGeneral(String.valueOf(vector[10]));
        nodo.setDescuentoGeneral(String.valueOf(vector[11]));
        nodo.setIvaGeneral(String.valueOf(vector[12]));
        nodo.setSubtotalGeneral(String.valueOf(vector[13]));
        nodo.setComprobante((String) vector[14]);
        nodo.setCotizacion((String) vector[15]);

        try {
            nodo.setAnulada((boolean) vector[16]);
        } catch (Exception e) {
            nodo.setAnulada(false);
        }

        nodo.setAnula((String) vector[17]);

        try {
            nodo.setCredito((boolean) vector[18]);
        } catch (Exception e) {
            nodo.setCredito(false);
        }

        nodo.setCxc((String) vector[19]);
        nodo.setUsuario((String) vector[20]);

        nodo.setRtIva(String.valueOf(vector[21]));
        nodo.setRtIca(String.valueOf(vector[22]));
        nodo.setRtFuente(String.valueOf(vector[23]));
        nodo.setOtros(String.valueOf(vector[24]));
        nodo.setObservacion((String) vector[25]);

        try {
            nodo.setAnulada1((boolean) vector[26]);
        } catch (Exception e) {
            nodo.setAnulada1(false);
        }

        nodo.setAnula1((String) vector[27]);

        try {
            nodo.setCredito1((boolean) vector[28]);
        } catch (Exception e) {
            nodo.setCredito1(false);
        }

        nodo.setCxc1((String) vector[29]);
        nodo.setUsuario1((String) vector[30]);
        nodo.setFechaAlerta((String) vector[31]);
        nodo.setTerminal((String) vector[32]);
        nodo.setEstadoGeneral((String) vector[33]);
        nodo.setEstado2((String) vector[34]);
        nodo.setDevuelta(String.valueOf(vector[35]));
        nodo.setFactura((String) vector[36]);
        nodo.setResolucion((String) vector[37]);
        nodo.setFechaAnulacion((String) vector[38]);
        nodo.setCuadreAnulacion((String) vector[39]);
        nodo.setUsuarioAnula((String) vector[40]);
        nodo.setCopago(String.valueOf(vector[41]));
        nodo.setPlaca((String) vector[42]);
        nodo.setGarantia((String) vector[43]);
        nodo.setDiasGarantia((String) vector[44]);
        nodo.setRango((String) vector[45]);
        nodo.setTerminos((String) vector[46]);
        nodo.setNotaAnulacion((String) vector[47]);
        nodo.setConseMesa((String) vector[48]);
        nodo.setProducto((String) vector[49]);
        nodo.setLista(String.valueOf(vector[50]));
        nodo.setCantidad(String.valueOf(vector[51]));
        nodo.setDescuento(String.valueOf(vector[52]));
        nodo.setTotal(String.valueOf(vector[53]));
        nodo.setIva(String.valueOf(vector[54]));
        nodo.setSubtotal(String.valueOf(vector[55]));
        nodo.setNC((String) vector[56]);
        nodo.setUtilidad(String.valueOf(vector[57]));
        nodo.setConcepto((String) vector[58]);
        nodo.setPorcDescuento(String.valueOf(vector[59]));
        nodo.setDescripcion((String) vector[60]);
        nodo.setPlu((String) vector[61]);
        nodo.setCant2(String.valueOf(vector[62]));
        nodo.setEstado((String) vector[63]);
        nodo.setPorcIva(String.valueOf(vector[64]));
        nodo.setTercero((String) vector[65]);
        nodo.setUtilidad1(String.valueOf(vector[66]));
        nodo.setPreparacion((String) vector[67]);
        nodo.setImpuestos(String.valueOf(vector[68]));
        nodo.setTurno((String) vector[69]);
        nodo.setImpoGeneral(String.valueOf(vector[70]));
        nodo.setFranquisia((String) vector[71]);
        nodo.setComision((String) vector[72]);
        nodo.setValorComision(String.valueOf(vector[73]));
        nodo.setTotalFacturaComision(String.valueOf(vector[74]));
        nodo.setImei((String) vector[75]);
        nodo.setLote((String) vector[76]);
        nodo.setIdProd((String) vector[77]);
        nodo.setMesFacturado((String) vector[78]);
        nodo.setTarjetaCredito(String.valueOf(vector[79]));
        nodo.setTotalPropina(String.valueOf(vector[80]));
        nodo.setPorcPropina((String) vector[81]);
        nodo.setIdCosteo((String) vector[82]);
        nodo.setHora((String) vector[83]);
        nodo.setPorcImpo(String.valueOf(vector[84]));
        nodo.setImpoconsumo(String.valueOf(vector[85]));

        try {
            nodo.setSisteCredito((boolean) vector[86]);
        } catch (Exception e) {
            nodo.setSisteCredito(false);
        }

        nodo.setBodega((String) vector[87]);
        nodo.setCosto(String.valueOf(vector[88]));
        nodo.setModeloContable((String) vector[89]);

        return nodo;
    }

    public ndOServicio1 llenarOServicio1(Object[] vector) {

        ndOServicio1 nodo = new ndOServicio1();

        nodo.setIdFactura((String) vector[0]);
        nodo.setCliente((String) vector[1]);
        nodo.setVendedor((String) vector[2]);
        nodo.setRed((String) vector[3]);
        nodo.setFechaFactura((String) vector[4]);
        nodo.setFechaVencimiento((String) vector[5]);
        nodo.setEfectivoGeneral(String.valueOf(vector[6]));
        nodo.setNcGeneral(String.valueOf(vector[7]));
        nodo.setChequeGeneral(String.valueOf(vector[8]));
        nodo.setTargetaGeneral(String.valueOf(vector[9]));
        nodo.setTotalGeneral(String.valueOf(vector[10]));
        nodo.setDescuentoGeneral(String.valueOf(vector[11]));
        nodo.setIvaGeneral(String.valueOf(vector[12]));
        nodo.setSubtotalGeneral(String.valueOf(vector[13]));
        nodo.setComprobante((String) vector[14]);
        nodo.setCotizacion((String) vector[15]);

        try {
            nodo.setAnulada((boolean) vector[16]);
        } catch (Exception e) {
            nodo.setAnulada(false);
        }

        nodo.setAnula((String) vector[17]);

        try {
            nodo.setCredito((boolean) vector[18]);
        } catch (Exception e) {
            nodo.setCredito(false);
        }

        nodo.setCxc((String) vector[19]);
        nodo.setUsuario((String) vector[20]);

        nodo.setRtIva(String.valueOf(vector[21]));
        nodo.setRtIca(String.valueOf(vector[22]));
        nodo.setRtFuente(String.valueOf(vector[23]));
        nodo.setOtros(String.valueOf(vector[24]));
        nodo.setObservacion((String) vector[25]);

        try {
            nodo.setAnulada1((boolean) vector[26]);
        } catch (Exception e) {
            nodo.setAnulada1(false);
        }

        nodo.setAnula1((String) vector[27]);

        try {
            nodo.setCredito1((boolean) vector[28]);
        } catch (Exception e) {
            nodo.setCredito1(false);
        }

        nodo.setCxc1((String) vector[29]);
        nodo.setUsuario1((String) vector[30]);
        nodo.setFechaAlerta((String) vector[31]);
        nodo.setTerminal((String) vector[32]);
        nodo.setEstadoGeneral((String) vector[33]);
        nodo.setEstado2((String) vector[34]);
        nodo.setDevuelta(String.valueOf(vector[35]));
        nodo.setFactura((String) vector[36]);
        nodo.setResolucion((String) vector[37]);
        nodo.setFechaAnulacion((String) vector[38]);
        nodo.setCuadreAnulacion((String) vector[39]);
        nodo.setUsuarioAnula((String) vector[40]);
        nodo.setCopago(String.valueOf(vector[41]));
        nodo.setPlaca((String) vector[42]);
        nodo.setGarantia((String) vector[43]);
        nodo.setDiasGarantia((String) vector[44]);
        nodo.setRango((String) vector[45]);
        nodo.setTerminos((String) vector[46]);
        nodo.setNotaAnulacion((String) vector[47]);
        nodo.setConseMesa((String) vector[48]);
        nodo.setProducto((String) vector[49]);
        nodo.setLista(String.valueOf(vector[50]));
        nodo.setCantidad(String.valueOf(vector[51]));
        nodo.setDescuento(String.valueOf(vector[52]));
        nodo.setTotal(String.valueOf(vector[53]));
        nodo.setIva(String.valueOf(vector[54]));
        nodo.setSubtotal(String.valueOf(vector[55]));
        nodo.setNC((String) vector[56]);
        nodo.setUtilidad(String.valueOf(vector[57]));
        nodo.setConcepto((String) vector[58]);
        nodo.setPorcDescuento(String.valueOf(vector[59]));
        nodo.setDescripcion((String) vector[60]);
        nodo.setPlu((String) vector[61]);
        nodo.setCant2(String.valueOf(vector[62]));
        nodo.setEstado((String) vector[63]);
        nodo.setPorcIva(String.valueOf(vector[64]));
        nodo.setTercero((String) vector[65]);
        nodo.setUtilidad1(String.valueOf(vector[66]));
        nodo.setPreparacion((String) vector[67]);
        nodo.setBodega((String) vector[68]);
        return nodo;
    }

    public ndPlanSepare llenarPlanSepare(Object[] vector) {

        ndPlanSepare nodo = new ndPlanSepare();

        nodo.setIdFactura((String) vector[0]);
        nodo.setCliente((String) vector[1]);
        nodo.setVendedor((String) vector[2]);
        nodo.setRed((String) vector[3]);
        nodo.setFechaFactura((String) vector[4]);
        nodo.setFechaVencimiento((String) vector[5]);
        nodo.setEfectivoGeneral(String.valueOf(vector[6]));
        nodo.setNcGeneral(String.valueOf(vector[7]));
        nodo.setChequeGeneral(String.valueOf(vector[8]));
        nodo.setTargetaGeneral(String.valueOf(vector[9]));
        nodo.setTotalGeneral(String.valueOf(vector[10]));
        nodo.setDescuentoGeneral(String.valueOf(vector[11]));
        nodo.setIvaGeneral(String.valueOf(vector[12]));
        nodo.setSubtotalGeneral(String.valueOf(vector[13]));
        nodo.setComprobante((String) vector[14]);
        nodo.setCotizacion((String) vector[15]);

        try {
            nodo.setAnulada((boolean) vector[16]);
        } catch (Exception e) {
            nodo.setAnulada(false);
        }

        nodo.setAnula((String) vector[17]);

        try {
            nodo.setCredito((boolean) vector[18]);
        } catch (Exception e) {
            nodo.setCredito(false);
        }

        nodo.setCxc((String) vector[19]);
        nodo.setUsuario((String) vector[20]);

        nodo.setRtIva(String.valueOf(vector[21]));
        nodo.setRtIca(String.valueOf(vector[22]));
        nodo.setRtFuente(String.valueOf(vector[23]));
        nodo.setOtros(String.valueOf(vector[24]));
        nodo.setObservacion((String) vector[25]);

        try {
            nodo.setAnulada1((boolean) vector[26]);
        } catch (Exception e) {
            nodo.setAnulada1(false);
        }

        nodo.setAnula1((String) vector[27]);

        try {
            nodo.setCredito1((boolean) vector[28]);
        } catch (Exception e) {
            nodo.setCredito1(false);
        }

        nodo.setCxc1((String) vector[29]);
        nodo.setUsuario1((String) vector[30]);
        nodo.setFechaAlerta((String) vector[31]);
        nodo.setTerminal((String) vector[32]);
        nodo.setEstadoGeneral((String) vector[33]);
        nodo.setEstado2((String) vector[34]);
        nodo.setDevuelta(String.valueOf(vector[35]));
        nodo.setFactura((String) vector[36]);
        nodo.setResolucion((String) vector[37]);
        nodo.setFechaAnulacion((String) vector[38]);
        nodo.setCuadreAnulacion((String) vector[39]);
        nodo.setUsuarioAnula((String) vector[40]);
        nodo.setCopago(String.valueOf(vector[41]));
        nodo.setPlaca((String) vector[42]);
        nodo.setGarantia((String) vector[43]);
        nodo.setDiasGarantia((String) vector[44]);
        nodo.setRango((String) vector[45]);
        nodo.setTerminos((String) vector[46]);
        nodo.setNotaAnulacion((String) vector[47]);
        nodo.setConseMesa((String) vector[48]);
        nodo.setProducto((String) vector[49]);
        nodo.setLista(String.valueOf(vector[50]));
        nodo.setCantidad(String.valueOf(vector[51]));
        nodo.setDescuento(String.valueOf(vector[52]));
        nodo.setTotal(String.valueOf(vector[53]));
        nodo.setIva(String.valueOf(vector[54]));
        nodo.setSubtotal(String.valueOf(vector[55]));
        nodo.setNC((String) vector[56]);
        nodo.setUtilidad(String.valueOf(vector[57]));
        nodo.setConcepto((String) vector[58]);
        nodo.setPorcDescuento(String.valueOf(vector[59]));
        nodo.setDescripcion((String) vector[60]);
        nodo.setPlu((String) vector[61]);
        nodo.setCant2(String.valueOf(vector[62]));
        nodo.setEstado((String) vector[63]);
        nodo.setPorcIva(String.valueOf(vector[64]));
        nodo.setTercero((String) vector[65]);
        nodo.setUtilidad1(String.valueOf(vector[66]));
        nodo.setPreparacion((String) vector[67]);
        nodo.setImei((String) vector[68]);
        nodo.setIdProd((String) vector[69]);
        nodo.setBodega((String) vector[70]);

        return nodo;
    }

    public ndCongelada llenarCongelada(Object[] vector) {

        ndCongelada nodo = new ndCongelada();

        nodo.setIdFactura((String) vector[0]);
        nodo.setCliente((String) vector[1]);
        nodo.setVendedor((String) vector[2]);
        nodo.setRed((String) vector[3]);
        nodo.setFechaFactura((String) vector[4]);
        nodo.setFechaVencimiento((String) vector[5]);
        nodo.setEfectivoGeneral(String.valueOf(vector[6]));
        nodo.setNcGeneral(String.valueOf(vector[7]));
        nodo.setChequeGeneral(String.valueOf(vector[8]));
        nodo.setTargetaGeneral(String.valueOf(vector[9]));
        nodo.setTotalGeneral(String.valueOf(vector[10]));
        nodo.setDescuentoGeneral(String.valueOf(vector[11]));
        nodo.setIvaGeneral(String.valueOf(vector[12]));
        nodo.setSubtotalGeneral(String.valueOf(vector[13]));
        nodo.setComprobante((String) vector[14]);
        nodo.setCotizacion((String) vector[15]);

        try {
            nodo.setAnulada((boolean) vector[16]);
        } catch (Exception e) {
            nodo.setAnulada(false);
        }

        nodo.setAnula((String) vector[17]);

        try {
            nodo.setCredito((boolean) vector[18]);
        } catch (Exception e) {
            nodo.setCredito(false);
        }

        nodo.setCxc((String) vector[19]);
        nodo.setUsuario((String) vector[20]);

        nodo.setRtIva(String.valueOf(vector[21]));
        nodo.setRtIca(String.valueOf(vector[22]));
        nodo.setRtFuente(String.valueOf(vector[23]));
        nodo.setOtros(String.valueOf(vector[24]));
        nodo.setObservacion((String) vector[25]);

        try {
            nodo.setAnulada1((boolean) vector[26]);
        } catch (Exception e) {
            nodo.setAnulada1(false);
        }

        nodo.setAnula1((String) vector[27]);

        try {
            nodo.setCredito1((boolean) vector[28]);
        } catch (Exception e) {
            nodo.setCredito1(false);
        }

        nodo.setCxc1((String) vector[29]);
        nodo.setUsuario1((String) vector[30]);
        nodo.setFechaAlerta((String) vector[31]);
        nodo.setTerminal((String) vector[32]);
        nodo.setEstadoGeneral((String) vector[33]);
        nodo.setEstado2((String) vector[34]);
        nodo.setDevuelta(String.valueOf(vector[35]));
        nodo.setFactura((String) vector[36]);
        nodo.setResolucion((String) vector[37]);
        nodo.setFechaAnulacion((String) vector[38]);
        nodo.setCuadreAnulacion((String) vector[39]);
        nodo.setUsuarioAnula((String) vector[40]);
        nodo.setCopago(String.valueOf(vector[41]));
        nodo.setPlaca((String) vector[42]);
        nodo.setGarantia((String) vector[43]);
        nodo.setDiasGarantia((String) vector[44]);
        nodo.setRango((String) vector[45]);
        nodo.setTerminos((String) vector[46]);
        nodo.setNotaAnulacion((String) vector[47]);
        nodo.setConseMesa((String) vector[48]);
        nodo.setProducto((String) vector[49]);
        nodo.setLista(String.valueOf(vector[50]));
        nodo.setCantidad(String.valueOf(vector[51]));
        nodo.setDescuento(String.valueOf(vector[52]));
        nodo.setTotal(String.valueOf(vector[53]));
        nodo.setIva(String.valueOf(vector[54]));
        nodo.setSubtotal(String.valueOf(vector[55]));
        nodo.setNC((String) vector[56]);
        nodo.setUtilidad(String.valueOf(vector[57]));
        nodo.setConcepto((String) vector[58]);
        nodo.setPorcDescuento(String.valueOf(vector[59]));
        nodo.setDescripcion((String) vector[60]);
        nodo.setPlu((String) vector[61]);
        nodo.setCant2(String.valueOf(vector[62]));
        nodo.setEstado((String) vector[63]);
        nodo.setPorcIva(String.valueOf(vector[64]));
        nodo.setTercero((String) vector[65]);
        nodo.setUtilidad1(String.valueOf(vector[66]));
        nodo.setPreparacion((String) vector[67]);
        nodo.setTurno((String) vector[68]);
        nodo.setImei((String) vector[69]);
        nodo.setIdProd((String) vector[70]);
        nodo.setImpoconsumoGeneral(String.valueOf(vector[71]));
        nodo.setPorcImpo(String.valueOf(vector[72]));
        nodo.setImpoconsumo(String.valueOf(vector[73]));
        nodo.setBodega((String) vector[74]);

        return nodo;
    }

    public ndPedido llenarPedido(Object[] vector) {

        ndPedido nodo = new ndPedido();

        nodo.setIdFactura((String) vector[0]);
        nodo.setCliente((String) vector[1]);
        nodo.setVendedor((String) vector[2]);
        nodo.setRed((String) vector[3]);
        nodo.setFechaFactura((String) vector[4]);
        nodo.setFechaVencimiento((String) vector[5]);
        nodo.setEfectivoGeneral(String.valueOf(vector[6]));
        nodo.setNcGeneral(String.valueOf(vector[7]));
        nodo.setChequeGeneral(String.valueOf(vector[8]));
        nodo.setTargetaGeneral(String.valueOf(vector[9]));
        nodo.setTotalGeneral(String.valueOf(vector[10]));
        nodo.setDescuentoGeneral(String.valueOf(vector[11]));
        nodo.setIvaGeneral(String.valueOf(vector[12]));
        nodo.setSubtotalGeneral(String.valueOf(vector[13]));
        nodo.setComprobante((String) vector[14]);
        nodo.setCotizacion((String) vector[15]);

        try {
            nodo.setAnulada((boolean) vector[16]);
        } catch (Exception e) {
            nodo.setAnulada(false);
        }

        nodo.setAnula((String) vector[17]);

        try {
            nodo.setCredito((boolean) vector[18]);
        } catch (Exception e) {
            nodo.setCredito(false);
        }

        nodo.setCxc((String) vector[19]);
        nodo.setUsuario((String) vector[20]);

        nodo.setRtIva(String.valueOf(vector[21]));
        nodo.setRtIca(String.valueOf(vector[22]));
        nodo.setRtFuente(String.valueOf(vector[23]));
        nodo.setOtros(String.valueOf(vector[24]));
        nodo.setObservacion((String) vector[25]);

        try {
            nodo.setAnulada1((boolean) vector[26]);
        } catch (Exception e) {
            nodo.setAnulada1(false);
        }

        nodo.setAnula1((String) vector[27]);

        try {
            nodo.setCredito1((boolean) vector[28]);
        } catch (Exception e) {
            nodo.setCredito1(false);
        }

        nodo.setCxc1((String) vector[29]);
        nodo.setUsuario1((String) vector[30]);
        nodo.setFechaAlerta((String) vector[31]);
        nodo.setTerminal((String) vector[32]);
        nodo.setEstadoGeneral((String) vector[33]);
        nodo.setEstado2((String) vector[34]);
        nodo.setDevuelta(String.valueOf(vector[35]));
        nodo.setFactura((String) vector[36]);
        nodo.setResolucion((String) vector[37]);
        nodo.setFechaAnulacion((String) vector[38]);
        nodo.setCuadreAnulacion((String) vector[39]);
        nodo.setUsuarioAnula((String) vector[40]);
        nodo.setCopago(String.valueOf(vector[41]));
        nodo.setPlaca((String) vector[42]);
        nodo.setGarantia((String) vector[43]);
        nodo.setDiasGarantia((String) vector[44]);
        nodo.setRango((String) vector[45]);
        nodo.setTerminos((String) vector[46]);
        nodo.setNotaAnulacion((String) vector[47]);
        nodo.setConseMesa((String) vector[48]);
        nodo.setProducto((String) vector[49]);
        nodo.setLista(String.valueOf(vector[50]));
        nodo.setCantidad(String.valueOf(vector[51]));
        nodo.setDescuento(String.valueOf(vector[52]));
        nodo.setTotal(String.valueOf(vector[53]));
        nodo.setIva(String.valueOf(vector[54]));
        nodo.setSubtotal(String.valueOf(vector[55]));
        nodo.setNC((String) vector[56]);
        nodo.setUtilidad(String.valueOf(vector[57]));
        nodo.setConcepto((String) vector[58]);
        nodo.setPorcDescuento(String.valueOf(vector[59]));
        nodo.setDescripcion((String) vector[60]);
        nodo.setPlu((String) vector[61]);
        nodo.setCant2(String.valueOf(vector[62]));
        nodo.setEstado((String) vector[63]);
        nodo.setPorcIva(String.valueOf(vector[64]));
        nodo.setTercero((String) vector[65]);
        nodo.setUtilidad1(String.valueOf(vector[66]));
        nodo.setPreparacion((String) vector[67]);
        nodo.setBodega((String) vector[68]);
        nodo.setIdProd((String) vector[69]);
        nodo.setDetalle((String) vector[70]);

        return nodo;
    }

    public ndCotizacion llenarCotizacion(Object[] vector) {

        ndCotizacion nodo = new ndCotizacion();

        nodo.setIdFactura((String) vector[0]);
        nodo.setCliente((String) vector[1]);
        nodo.setVendedor((String) vector[2]);
        nodo.setRed((String) vector[3]);
        nodo.setFechaFactura((String) vector[4]);
        nodo.setFechaVencimiento((String) vector[5]);
        nodo.setEfectivoGeneral(String.valueOf(vector[6]));
        nodo.setNcGeneral(String.valueOf(vector[7]));
        nodo.setChequeGeneral(String.valueOf(vector[8]));
        nodo.setTargetaGeneral(String.valueOf(vector[9]));
        nodo.setTotalGeneral(String.valueOf(vector[10]));
        nodo.setDescuentoGeneral(String.valueOf(vector[11]));
        nodo.setIvaGeneral(String.valueOf(vector[12]));
        nodo.setSubtotalGeneral(String.valueOf(vector[13]));
        nodo.setComprobante((String) vector[14]);
        nodo.setCotizacion((String) vector[15]);

        try {
            nodo.setAnulada((boolean) vector[16]);
        } catch (Exception e) {
            nodo.setAnulada(false);
        }

        nodo.setAnula((String) vector[17]);

        try {
            nodo.setCredito((boolean) vector[18]);
        } catch (Exception e) {
            nodo.setCredito(false);
        }

        nodo.setCxc((String) vector[19]);
        nodo.setUsuario((String) vector[20]);
        nodo.setRtIva(String.valueOf(vector[21]));
        nodo.setRtIca(String.valueOf(vector[22]));
        nodo.setRtFuente(String.valueOf(vector[23]));
        nodo.setOtros(String.valueOf(vector[24]));
        nodo.setObservacion((String) vector[25]);

        try {
            nodo.setAnulada1((boolean) vector[26]);
        } catch (Exception e) {
            nodo.setAnulada1(false);
        }

        nodo.setAnula1((String) vector[27]);

        try {
            nodo.setCredito1((boolean) vector[28]);
        } catch (Exception e) {
            nodo.setCredito1(false);
        }

        nodo.setCxc1((String) vector[29]);
        nodo.setUsuario1((String) vector[30]);
        nodo.setFechaAlerta((String) vector[31]);
        nodo.setTerminal((String) vector[32]);
        nodo.setEstadoGeneral((String) vector[33]);
        nodo.setEstado2((String) vector[34]);
        nodo.setDevuelta(String.valueOf(vector[35]));
        nodo.setFactura((String) vector[36]);
        nodo.setResolucion((String) vector[37]);
        nodo.setFechaAnulacion((String) vector[38]);
        nodo.setCuadreAnulacion((String) vector[39]);
        nodo.setUsuarioAnula((String) vector[40]);
        nodo.setCopago(String.valueOf(vector[41]));
        nodo.setPlaca((String) vector[42]);
        nodo.setGarantia((String) vector[43]);
        nodo.setDiasGarantia((String) vector[44]);
        nodo.setRango((String) vector[45]);
        nodo.setTerminos((String) vector[46]);
        nodo.setNotaAnulacion((String) vector[47]);
        nodo.setConseMesa((String) vector[48]);
        nodo.setProducto((String) vector[49]);
        nodo.setLista(String.valueOf(vector[50]));
        nodo.setCantidad(String.valueOf(vector[51]));
        nodo.setDescuento(String.valueOf(vector[52]));
        nodo.setTotal(String.valueOf(vector[53]));
        nodo.setIva(String.valueOf(vector[54]));
        nodo.setSubtotal(String.valueOf(vector[55]));
        nodo.setNC((String) vector[56]);
        nodo.setUtilidad(String.valueOf(vector[57]));
        nodo.setConcepto((String) vector[58]);
        nodo.setPorcDescuento(String.valueOf(vector[59]));
        nodo.setDescripcion((String) vector[60]);
        nodo.setPlu((String) vector[61]);
        nodo.setCant2(String.valueOf(vector[62]));
        nodo.setEstado((String) vector[63]);
        nodo.setPorcIva(String.valueOf(vector[64]));
        nodo.setTercero((String) vector[65]);
        nodo.setUtilidad1(String.valueOf(vector[66]));
        nodo.setPreparacion((String) vector[67]);
        nodo.setBodega((String) vector[68]);

        return nodo;
    }

    public ndCaja llenarCaja(Object[] vector) {

        ndCaja nodo = new ndCaja();

        nodo.setId((String) vector[0]);
        nodo.setFecha((String) vector[1]);
        nodo.setHora((String) vector[2]);
        nodo.setEfectivo(String.valueOf(vector[3]));
        nodo.setTarjeta(String.valueOf(vector[4]));
        nodo.setCheque(String.valueOf(vector[5]));
        nodo.setNc(String.valueOf(vector[6]));
        nodo.setCajero(String.valueOf(vector[7]));
        nodo.setTotal(String.valueOf(vector[8]));
        nodo.setSistema(String.valueOf(vector[9]));
        nodo.setDiferencia(String.valueOf(vector[10]));
        nodo.setUsuario((String) vector[11]);
        nodo.setTermianl((String) vector[12]);
        nodo.setGasto(String.valueOf(vector[13]));
        nodo.setRecogida(String.valueOf(vector[14]));
        nodo.setBase(String.valueOf(vector[15]));
        nodo.setTotalVenta(String.valueOf(vector[16]));
        nodo.setTotalCredito(String.valueOf(vector[17]));
        nodo.setTotalContado(String.valueOf(vector[18]));
        nodo.setNcRealizadas(String.valueOf(vector[19]));
        nodo.setEfectivoSistema(String.valueOf(vector[20]));
        nodo.setChequeSistema(String.valueOf(vector[21]));
        nodo.setTarjetaSistema(String.valueOf(vector[22]));
        nodo.setNcSistema(String.valueOf(vector[23]));
        nodo.setContContado((int) vector[24]);
        nodo.setContCredito((int) vector[25]);
        nodo.setContNcRecibidas((int) vector[26]);
        nodo.setContNcRealizadas((int) vector[27]);
        nodo.setContEfectivo((int) vector[28]);
        nodo.setContCheque((int) vector[29]);
        nodo.setContTarjeta((int) vector[30]);
        nodo.setContAbonos((int) vector[31]);
        nodo.setTotalAbonos(String.valueOf(vector[32]));
        nodo.setPrimeraFactura((String) vector[33]);
        nodo.setUltimaFactura((String) vector[34]);
        nodo.setPrimerAbono((String) vector[35]);
        nodo.setUltimoAbono((String) vector[36]);
        nodo.setTotalFactSepares(String.valueOf(vector[37]));
        nodo.setTotalCuotaInicial(String.valueOf(vector[38]));
        nodo.setContCuotaInicial((int) vector[39]);
        nodo.setContGastos((int) vector[40]);
        nodo.setNcReembolsadas(String.valueOf(vector[41]));
        nodo.setContNcReembolsadas((int) vector[42]);
        nodo.setGastosManuales(String.valueOf(vector[43]));
        nodo.setAbonosCartera(String.valueOf(vector[44]));
        nodo.setAbonosSepare(String.valueOf(vector[45]));
        nodo.setVentasDomi(String.valueOf(vector[46]));
        nodo.setItemDomi(String.valueOf(vector[47]));
        nodo.setTotalPropinas(String.valueOf(vector[48]));
        nodo.setSisteCredito(String.valueOf(vector[49]));
        return nodo;
    }

    public ndIngreso llenarIngreso(Object[] vector) {

        ndIngreso nodo = new ndIngreso();

        nodo.setId((String) vector[0]);
        nodo.setProveedor((String) vector[1]);
        nodo.setFechaFactura((String) vector[2]);
        nodo.setFechaVencimiento((String) vector[3]);
        nodo.setTotal(String.valueOf(vector[4]));
        nodo.setDescuentos(String.valueOf(vector[5]));
        nodo.setIva(String.valueOf(vector[6]));
        nodo.setSubtotal(String.valueOf(vector[7]));
        nodo.setTipo(String.valueOf(vector[8]));
        nodo.setConcepto(String.valueOf(vector[9]));
        nodo.setCredito((boolean) vector[10]);
        nodo.setCxp((String) vector[11]);
        nodo.setUsuario((String) vector[12]);
        nodo.setTerminal(String.valueOf(vector[13]));
        nodo.setRtIva(String.valueOf(vector[14]));
        nodo.setRtFuente(String.valueOf(vector[15]));
        nodo.setPorRtFuente(String.valueOf(vector[16]));
        nodo.setObservaciones(String.valueOf(vector[17]));
        nodo.setHora(String.valueOf(vector[18]));
        nodo.setImpoconsumo(String.valueOf(vector[19]));
        nodo.setEstado((String) vector[20]);
        nodo.setIca(String.valueOf(vector[21]));
        nodo.setEfectivo(String.valueOf(vector[22]));
        nodo.setCheque(String.valueOf(vector[23]));
        nodo.setTarjeta(String.valueOf(vector[24]));
        nodo.setDescFinanciero(String.valueOf(vector[25]));
        nodo.setDescProntoPago(String.valueOf(vector[26]));
        nodo.setBodega((String) vector[27]);
        nodo.setModeloContable((String) vector[28]);
        return nodo;
    }

    public ndProducto llenarProducto(Object[] vector) {

        ndProducto nodo = new ndProducto();

        nodo.setIdSistema((String) vector[0]);
        nodo.setCodigo((String) vector[1]);
        nodo.setCodigoBarras((String) vector[2]);
        nodo.setDescripcion((String) vector[3]);
        nodo.setGrupo((String) vector[4]);
        nodo.setSubgrupo((String) vector[5]);
        nodo.setProveedor((String) vector[6]);
        nodo.setIva(String.valueOf(vector[7]));
        nodo.setL1(String.valueOf(vector[8]));
        nodo.setL2(String.valueOf(vector[9]));
        nodo.setL3(String.valueOf(vector[10]));
        nodo.setL4(String.valueOf(vector[11]));
        nodo.setL5(String.valueOf(vector[12]));
        nodo.setL6(String.valueOf(vector[13]));
        nodo.setL7(String.valueOf(vector[14]));
        nodo.setL8(String.valueOf(vector[15]));
        nodo.setUsuario((String) vector[16]);
        nodo.setMinimo(String.valueOf(vector[17]));
        nodo.setUnd(String.valueOf(vector[18]));
        nodo.setReferencia(String.valueOf(vector[19]));
        nodo.setCosto(String.valueOf(vector[20]));
        nodo.setMinima(String.valueOf(vector[21]));
        nodo.setMaxima(String.valueOf(vector[22]));
        nodo.setUbicacion1((String) vector[23]);
        nodo.setDescripcion2((String) vector[24]);
        nodo.setUbicacion2((String) vector[25]);
        nodo.setCantidad2((String) vector[26]);
        nodo.setDescripcion3((String) vector[27]);
        nodo.setUbicacion3((String) vector[28]);
        nodo.setCantidad3((String) vector[29]);
        nodo.setDescripcion4((String) vector[30]);
        nodo.setUbicacion4((String) vector[31]);
        nodo.setCantidad4((String) vector[32]);
        nodo.setDescripcion5((String) vector[33]);
        nodo.setUbicacion5((String) vector[34]);
        nodo.setCantidad5((String) vector[35]);
        nodo.setDescripcion6((String) vector[36]);
        nodo.setUbicacion6((String) vector[37]);
        nodo.setCantidad6((String) vector[38]);
        nodo.setDescripcion7((String) vector[39]);
        nodo.setUbicacion7((String) vector[40]);
        nodo.setCantidad7((String) vector[41]);
        nodo.setDescripcion8((String) vector[42]);
        nodo.setUbicacion8((String) vector[43]);
        nodo.setCantidad8((String) vector[44]);
        try {
            nodo.setPlu2((boolean) vector[45]);
        } catch (Exception e) {
            nodo.setPlu2(false);
        }
        try {
            nodo.setPlu3((boolean) vector[46]);
        } catch (Exception e) {
            nodo.setPlu3(false);
        }
        try {
            nodo.setPlu4((boolean) vector[47]);
        } catch (Exception e) {
            nodo.setPlu4(false);
        }
        try {
            nodo.setPlu5((boolean) vector[48]);
        } catch (Exception e) {
            nodo.setPlu5(false);
        }
        try {
            nodo.setPlu6((boolean) vector[49]);
        } catch (Exception e) {
            nodo.setPlu6(false);
        }
        try {
            nodo.setPlu7((boolean) vector[50]);
        } catch (Exception e) {
            nodo.setPlu7(false);
        }
        try {
            nodo.setPlu8((boolean) vector[51]);
        } catch (Exception e) {
            nodo.setPlu8(false);
        }

        nodo.setIvaC(String.valueOf(vector[52]));
        nodo.setPonderado(String.valueOf(vector[53]));
        nodo.setTerminal(String.valueOf(vector[54]));
        nodo.setInventario(String.valueOf(vector[55]));
        nodo.setClaseBuscador((String) (vector[56]));
        try {
            nodo.setManejaInventario((boolean) vector[57]);
        } catch (Exception e) {
            nodo.setManejaInventario(false);
        }

        nodo.setPorcentaje((String) (vector[58]));
        nodo.setCompras((String) vector[59]);
        nodo.setVentas((String) vector[60]);
        nodo.setNc((String) vector[61]);
        nodo.setAjusteEntrada((String) vector[62]);
        nodo.setAjusteSalida((String) vector[63]);
        nodo.setPlanSepare((String) vector[64]);
        nodo.setPedidos((String) vector[65]);
        nodo.setAnulada((String) vector[66]);
        nodo.setInventarioInicial((String) vector[67]);
        nodo.setAjusteInventario((String) vector[68]);
        nodo.setFisicoInventario((String) vector[69]);
        nodo.setArmado((String) vector[70]);
        nodo.setCosteo((String) vector[71]);
        nodo.setOrdenServicio((String) vector[72]);
        nodo.setCongelada((String) vector[73]);
        nodo.setTrasladoBod((String) vector[74]);
        nodo.setTrasladoInternoEntrada((String) vector[75]);
        nodo.setTrasladoInternoSalida((String) vector[76]);

        try {
            nodo.setImpoconsumo((boolean) vector[77]);
        } catch (Exception e) {
            nodo.setImpoconsumo(false);
        }

        nodo.setDescripcionIngles((String) vector[78]);
        nodo.setCubicaje(((String) vector[79]));
        nodo.setPeso((String) vector[80]);
        nodo.setCodContable((String) vector[81]);
        nodo.setCodArancel((String) vector[82]);
        nodo.setTipoProducto((String) vector[83]);
        nodo.setCantMedida((String) vector[84]);
        nodo.setMarca((String) vector[85]);
        nodo.setTipoProd((String) vector[86]);
        nodo.setEnTransito((String) vector[87]);
        nodo.setCodigo2((String) vector[88]);
        nodo.setCodigo3((String) vector[89]);
        nodo.setCodigo4((String) vector[90]);
        nodo.setCodigo5((String) vector[91]);
        nodo.setCodigo6((String) vector[92]);
        nodo.setCodigo7((String) vector[93]);
        nodo.setCodigo8((String) vector[94]);
        nodo.setLenteMarco((String) vector[95]);
        nodo.setAdesivo((String) vector[96]);
        nodo.setColor((String) vector[97]);
        nodo.setEmpaque((String) vector[98]);
        nodo.setComposicion((String) vector[99]);
        nodo.setRmb((String) vector[100]);
        nodo.setIndVentas((String) vector[101]);
        nodo.setImpoconsumoVenta((String) vector[102]);
        nodo.setImpoconsumoCompra((String) vector[103]);
        nodo.setDescripcionLarga((String) vector[104]);
        nodo.setNotaDebito((String) vector[105]);

        return nodo;
    }

    public ndProceso llenarProceso(Object[] vector) {

        ndProceso nodo = new ndProceso();

        nodo.setCodigo((String) vector[0]);
        nodo.setNombre((String) vector[1]);
        nodo.setUnd((String) vector[2]);
        nodo.setObservaciones((String) vector[3]);

        return nodo;
    }

    public ndMaestra llenarMaestra(Object[] vector) {

        ndMaestra nodo = new ndMaestra();

        nodo.setL1((String) vector[0]);
        nodo.setL2((String) vector[1]);
        nodo.setL4((String) vector[2]);
        nodo.setC1((String) vector[3]);
        nodo.setC2(String.valueOf(vector[4]));
        nodo.setC3(String.valueOf(vector[5]));
        nodo.setC4(String.valueOf(vector[6]));
        nodo.setC5(String.valueOf(vector[7]));
        nodo.setD1(String.valueOf(vector[8]));
        nodo.setD2((String) vector[9]);
        nodo.setD3(String.valueOf(vector[10]));
        nodo.setD4((String) vector[11]);
        nodo.setD5(String.valueOf(vector[12]));
        nodo.setD6((String) vector[13]);
        nodo.setD7(String.valueOf(vector[14]));
        nodo.setPie((String) vector[15]);
        nodo.setLegal(String.valueOf(vector[16]));
        nodo.setC6(String.valueOf(vector[17]));
        nodo.setC7(String.valueOf(vector[18]));
        try {
            nodo.setRecogida((boolean) (vector[19]));
        } catch (Exception e) {
            nodo.setRecogida(false);
        }

        try {
            nodo.setLector((boolean) (vector[20]));
        } catch (Exception e) {
            nodo.setLector(false);
        }

        nodo.setTituloFactura((String) vector[21]);

        try {
            nodo.setPvpSinIva((boolean) (vector[22]));
        } catch (Exception e) {
            nodo.setPvpSinIva(false);
        }

        try {
            nodo.setCostoSinIva((boolean) (vector[23]));
        } catch (Exception e) {
            nodo.setCostoSinIva(false);
        }

        try {
            nodo.setVentasPredeterminado((boolean) (vector[24]));
        } catch (Exception e) {
            nodo.setVentasPredeterminado(false);
        }

        try {
            nodo.setMensajeUtilidad((boolean) (vector[25]));
        } catch (Exception e) {
            nodo.setMensajeUtilidad(false);
        }

        try {
            nodo.setConsecutivosDiferentes((boolean) (vector[26]));
        } catch (Exception e) {
            nodo.setMensajeUtilidad(false);
        }

        try {
            nodo.setResolucionIgual((boolean) (vector[27]));
        } catch (Exception e) {
            nodo.setMensajeUtilidad(false);
        }

        nodo.setDiasAlertaResolucion((String) vector[28]);
        nodo.setAlertaFechaDias((String) vector[29]);
        nodo.setAlertaCantidadNumeracion((String) vector[30]);
        nodo.setAlertaPromedioDias((String) vector[31]);
        nodo.setDiasCobrarMora((String) vector[32]);
        nodo.setPorcentajeMora((String) vector[33]);

        try {
            nodo.setGeneraOrdenMedica((boolean) (vector[34]));
        } catch (Exception e) {
            nodo.setGeneraOrdenMedica(false);
        }

        try {
            nodo.setImprimirOrdenMedica((boolean) (vector[35]));
        } catch (Exception e) {
            nodo.setImprimirOrdenMedica(false);
        }
        try {
            nodo.setImprimirFacturaOrdenMedica((boolean) (vector[36]));
        } catch (Exception e) {
            nodo.setImprimirFacturaOrdenMedica(false);
        }

        nodo.setHoraInicioAgenda((String) vector[37]);
        nodo.setHoraFinAgenda((String) vector[38]);
        nodo.setIntervaloAgenda((String) vector[39]);
        nodo.setCodigoPrestadorServicio((String) vector[40]);

        try {
            nodo.setImprimirCuadreFiscal((boolean) (vector[41]));
        } catch (Exception e) {
            nodo.setImprimirCuadreFiscal(false);
        }

        try {
            nodo.setOcultarInformacionCliente((boolean) (vector[42]));
        } catch (Exception e) {
            nodo.setOcultarInformacionCliente(false);
        }

        try {
            nodo.setVisualizarTodasLasFacturas((boolean) (vector[43]));
        } catch (Exception e) {
            nodo.setVisualizarTodasLasFacturas(false);
        }

        try {
            nodo.setMostrarInformacionCuadre((boolean) (vector[44]));
        } catch (Exception e) {
            nodo.setMostrarInformacionCuadre(false);
        }

        nodo.setDescuentoMaximoVentas((String) vector[45]);
        nodo.setTipoPrestadorServicio((String) vector[46]);
        nodo.setOtros((String) vector[47]);
        nodo.setDomicilios((String) vector[48]);
        nodo.setLimite((String) vector[49]);

        try {
            nodo.setCombinarProductos((boolean) (vector[50]));
        } catch (Exception e) {
            nodo.setCombinarProductos(false);
        }

        try {
            nodo.setModificarNombre((boolean) (vector[51]));
        } catch (Exception e) {
            nodo.setModificarNombre(false);
        }

        try {
            nodo.setImpBolsa((boolean) (vector[52]));
        } catch (Exception e) {
            nodo.setImpBolsa(false);
        }

        nodo.setValorBolsa((String) vector[53]);

        try {
            nodo.setTurno((boolean) (vector[54]));
        } catch (Exception e) {
            nodo.setTurno(false);
        }

        nodo.setTurno1((String) vector[55]);
        nodo.setInformacion((String) vector[56]);

        try {
            nodo.setConsecutivo((boolean) (vector[57]));
        } catch (Exception e) {
            nodo.setConsecutivo(false);
        }

        try {
            nodo.setHora((boolean) (vector[58]));
        } catch (Exception e) {
            nodo.setHora(false);
        }

        try {
            nodo.setPondNegativo((boolean) (vector[59]));
        } catch (Exception e) {
            nodo.setPondNegativo(false);
        }

        nodo.setAnexoOrdenServicio((String) vector[60]);

        try {
            nodo.setModificarPrecio((boolean) (vector[61]));
        } catch (Exception e) {
            nodo.setModificarPrecio(false);
        }

        try {
            nodo.setBorrarCongelada((boolean) (vector[62]));
        } catch (Exception e) {
            nodo.setBorrarCongelada(false);
        }

        // IMPRIMIR
        try {
            nodo.setCopiasFactura((boolean) (vector[63]));
        } catch (Exception e) {
            nodo.setCopiasFactura(false);
        }

        try {
            nodo.setCopiasOServicio((boolean) (vector[64]));
        } catch (Exception e) {
            nodo.setCopiasOServicio(false);
        }

        try {
            nodo.setCopiasCotizacion((boolean) (vector[65]));
        } catch (Exception e) {
            nodo.setCopiasCotizacion(false);
        }

        try {
            nodo.setCopiasPlanSepare((boolean) (vector[66]));
        } catch (Exception e) {
            nodo.setCopiasPlanSepare(false);
        }

        try {
            nodo.setCopiasPedido((boolean) (vector[67]));
        } catch (Exception e) {
            nodo.setCopiasPedido(false);
        }

        // PREVISUALIZAR
        try {
            nodo.setPrevisualizarFactura((boolean) (vector[68]));
        } catch (Exception e) {
            nodo.setPrevisualizarFactura(false);
        }

        try {
            nodo.setPrevisualizarOServicio((boolean) (vector[69]));
        } catch (Exception e) {
            nodo.setPrevisualizarOServicio(false);
        }

        try {
            nodo.setPrevisualizarCotizacion((boolean) (vector[70]));
        } catch (Exception e) {
            nodo.setPrevisualizarCotizacion(false);
        }

        try {
            nodo.setPrevisualizarPlanSepare((boolean) (vector[71]));
        } catch (Exception e) {
            nodo.setPrevisualizarPlanSepare(false);
        }

        try {
            nodo.setPrevisualizarPedido((boolean) (vector[72]));
        } catch (Exception e) {
            nodo.setPrevisualizarPedido(false);
        }

        // NUMERO DE COPIAS
        nodo.setNumFactura((String) vector[73]);
        nodo.setNumOServicio((String) vector[74]);
        nodo.setNumCotizacion((String) vector[75]);
        nodo.setNumPlanSepare((String) vector[76]);
        nodo.setNumPedido((String) vector[77]);

        try {
            nodo.setPagosTerceros((boolean) (vector[78]));
        } catch (Exception e) {
            nodo.setPagosTerceros(false);
        }

        try {
            nodo.setFacturarSeparado((boolean) (vector[79]));
        } catch (Exception e) {
            nodo.setFacturarSeparado(false);
        }

        try {
            nodo.setReimpresion((boolean) (vector[80]));
        } catch (Exception e) {
            nodo.setReimpresion(false);
        }

        nodo.setImpresoraPos((String) vector[81]);
        nodo.setImpresoraMediaCarta((String) vector[82]);
        nodo.setImpresoraCarta((String) vector[83]);

        try {
            nodo.setMostrarImpoconsumo((boolean) (vector[84]));
        } catch (Exception e) {
            nodo.setMostrarImpoconsumo(false);
        }

        try {
            nodo.setMostrarRetenciones((boolean) (vector[85]));
        } catch (Exception e) {
            nodo.setMostrarRetenciones(false);
        }

        nodo.setImprimirCada((String) vector[86]);
        nodo.setCantidadEstablecida((String) vector[87]);
        nodo.setFilas((String) vector[88]);
        nodo.setColumnas((String) vector[89]);
        nodo.setPorcPropina((String) vector[90]);

        try {
            nodo.setMostrarDevuelta((boolean) (vector[91]));
        } catch (Exception e) {
            nodo.setMostrarDevuelta(false);
        }

        nodo.setInicioHosp((String) vector[92]);
        nodo.setFinHosp((String) vector[93]);
        nodo.setIntervalosHosp((String) vector[94]);

        try {
            nodo.setDiasAutomaticos((boolean) (vector[95]));
        } catch (Exception e) {
            nodo.setDiasAutomaticos(false);
        }

        try {
            nodo.setCiudadBuscador((boolean) (vector[96]));
        } catch (Exception e) {
            nodo.setCiudadBuscador(false);
        }

        nodo.setFoco((String) vector[97]);

        try {
            nodo.setPvpImpoconsumo((boolean) (vector[98]));
        } catch (Exception e) {
            nodo.setPvpImpoconsumo(false);
        }

        try {
            nodo.setCostoImpoconsumo((boolean) (vector[99]));
        } catch (Exception e) {
            nodo.setCostoImpoconsumo(false);
        }

        try {
            nodo.setImpresionPorGrupo((boolean) (vector[100]));
        } catch (Exception e) {
            nodo.setImpresionPorGrupo(false);
        }

        nodo.setCopiasComanda((String) vector[101]);
        nodo.setCopiasPrefactura((String) vector[102]);

        try {
            nodo.setPrevisualizarComanda((boolean) (vector[103]));
        } catch (Exception e) {
            nodo.setPrevisualizarComanda(false);
        }

        try {
            nodo.setPrevisualizarPrefactura((boolean) (vector[104]));
        } catch (Exception e) {
            nodo.setPrevisualizarPrefactura(false);
        }

        nodo.setImpresoraComanda((String) vector[105]);
        nodo.setImpresoraPrefactura((String) vector[106]);

        try {
            nodo.setFacturarMesas((boolean) (vector[107]));
        } catch (Exception e) {
            nodo.setFacturarMesas(false);
        }

        try {
            nodo.setSoloMesas((boolean) (vector[108]));
        } catch (Exception e) {
            nodo.setSoloMesas(false);
        }

        try {
            nodo.setBorrarMesas((boolean) (vector[109]));
        } catch (Exception e) {
            nodo.setBorrarMesas(false);
        }

        nodo.setIdCliente((String) vector[110]);
        nodo.setPassword((String) vector[111]);
        nodo.setNumFacturaIncremento((String) vector[112]);
        nodo.setResponsabilidadesFiscales((String) vector[113]);
        nodo.setGmail((String) vector[114]);
        nodo.setPasswordGmail((String) vector[115]);

        return nodo;
    }

    public ndUsuario llenarUsuario(Object[] vector) {

        ndUsuario nodo = new ndUsuario();

        nodo.setNombre((String) vector[0]);
        nodo.setUsuario((String) vector[1]);
        nodo.setContra((String) vector[2]);

        try {
            nodo.setFacturas((boolean) vector[3]);
        } catch (NullPointerException e) {
            nodo.setFacturas(false);
        }

        try {
            nodo.setCongeladas((boolean) vector[4]);
        } catch (NullPointerException e) {
            nodo.setCongeladas(false);
        }

        try {
            nodo.setCotizaciones((boolean) vector[5]);
        } catch (NullPointerException e) {
            nodo.setCotizaciones(false);
        }

        try {
            nodo.setPlanSepares((boolean) vector[6]);
        } catch (NullPointerException e) {
            nodo.setPlanSepares(false);
        }

        try {
            nodo.setCreditos((boolean) vector[7]);
        } catch (NullPointerException e) {
            nodo.setCreditos(false);
        }

        try {
            nodo.setPedidos((boolean) vector[8]);
        } catch (NullPointerException e) {
            nodo.setPedidos(false);
        }

        try {
            nodo.setOrdenesServicio((boolean) vector[9]);
        } catch (NullPointerException e) {
            nodo.setOrdenesServicio(false);
        }

        try {
            nodo.setAnulaciones((boolean) vector[10]);
        } catch (NullPointerException e) {
            nodo.setAnulaciones(false);
        }

        try {
            nodo.setReimpresion((boolean) vector[11]);
        } catch (NullPointerException e) {
            nodo.setReimpresion(false);
        }

        try {
            nodo.setReportesVentas((boolean) vector[12]);
        } catch (NullPointerException e) {
            nodo.setReportesVentas(false);
        }

        try {
            nodo.setReporteVentasAnuladas((boolean) vector[13]);
        } catch (NullPointerException e) {
            nodo.setReporteVentasAnuladas(false);
        }

        try {
            nodo.setReporteCotizaciones((boolean) vector[14]);
        } catch (NullPointerException e) {
            nodo.setReporteCotizaciones(false);
        }

        try {
            nodo.setReportePlanSepares((boolean) vector[15]);
        } catch (NullPointerException e) {
            nodo.setReportePlanSepares(false);
        }

        try {
            nodo.setReporteCreditos((boolean) vector[16]);
        } catch (NullPointerException e) {
            nodo.setReporteCreditos(false);
        }

        try {
            nodo.setReporteOrdenesServicio((boolean) vector[17]);
        } catch (NullPointerException e) {
            nodo.setReporteOrdenesServicio(false);
        }

        try {
            nodo.setReporteIvasVentas((boolean) vector[18]);
        } catch (NullPointerException e) {
            nodo.setReporteIvasVentas(false);
        }

        try {
            nodo.setReporteDia((boolean) vector[19]);
        } catch (NullPointerException e) {
            nodo.setReporteDia(false);
        }

        try {
            nodo.setCreacionProductos((boolean) vector[20]);
        } catch (NullPointerException e) {
            nodo.setCreacionProductos(false);
        }

        try {
            nodo.setOrdenesCompras((boolean) vector[21]);
        } catch (NullPointerException e) {
            nodo.setOrdenesCompras(false);
        }

        try {
            nodo.setCompras((boolean) vector[22]);
        } catch (NullPointerException e) {
            nodo.setCompras(false);
        }

        try {
            nodo.setTrasladosExternos((boolean) vector[23]);
        } catch (NullPointerException e) {
            nodo.setTrasladosExternos(false);
        }

        try {
            nodo.setTrasladosInternos((boolean) vector[24]);
        } catch (NullPointerException e) {
            nodo.setTrasladosInternos(false);
        }

        try {
            nodo.setAjustesEntrada((boolean) vector[25]);
        } catch (NullPointerException e) {
            nodo.setAjustesEntrada(false);
        }

        try {
            nodo.setAjustesSalida((boolean) vector[26]);
        } catch (NullPointerException e) {
            nodo.setAjustesSalida(false);
        }

        try {
            nodo.setGrupos((boolean) vector[27]);
        } catch (NullPointerException e) {
            nodo.setGrupos(false);
        }

        try {
            nodo.setDisenos((boolean) vector[28]);
        } catch (NullPointerException e) {
            nodo.setDisenos(false);
        }

        try {
            nodo.setArmados((boolean) vector[29]);
        } catch (NullPointerException e) {
            nodo.setArmados(false);
        }

        try {
            nodo.setInventarioInicial((boolean) vector[30]);
        } catch (NullPointerException e) {
            nodo.setInventarioInicial(false);
        }

        try {
            nodo.setTrasladoDetalle((boolean) vector[31]);
        } catch (NullPointerException e) {
            nodo.setTrasladoDetalle(false);
        }

        try {
            nodo.setKardex((boolean) vector[32]);
        } catch (NullPointerException e) {
            nodo.setKardex(false);
        }

        try {
            nodo.setReporteProductos((boolean) vector[33]);
        } catch (NullPointerException e) {
            nodo.setReporteProductos(false);
        }

        try {
            nodo.setReportePrestamos((boolean) vector[34]);
        } catch (NullPointerException e) {
            nodo.setReportePrestamos(false);
        }

        try {
            nodo.setReporteCompras((boolean) vector[35]);
        } catch (NullPointerException e) {
            nodo.setReporteCompras(false);
        }

        try {
            nodo.setReporteAjustes((boolean) vector[36]);
        } catch (NullPointerException e) {
            nodo.setReporteAjustes(false);
        }

        try {
            nodo.setReporteOrdenCompras((boolean) vector[37]);
        } catch (NullPointerException e) {
            nodo.setReporteOrdenCompras(false);
        }

        try {
            nodo.setReporteInventario((boolean) vector[38]);
        } catch (NullPointerException e) {
            nodo.setReporteInventario(false);
        }

        try {
            nodo.setReporteTrasladoInterno((boolean) vector[39]);
        } catch (NullPointerException e) {
            nodo.setReporteTrasladoInterno(false);
        }

        try {
            nodo.setReporteIvasCompras((boolean) vector[40]);
        } catch (NullPointerException e) {
            nodo.setReporteIvasCompras(false);
        }

        try {
            nodo.setReporteMovimientoSerial((boolean) vector[41]);
        } catch (NullPointerException e) {
            nodo.setReporteMovimientoSerial(false);
        }

        try {
            nodo.setCrearClientes((boolean) vector[42]);
        } catch (NullPointerException e) {
            nodo.setCrearClientes(false);
        }

        try {
            nodo.setCrearProveedor((boolean) vector[43]);
        } catch (NullPointerException e) {
            nodo.setCrearProveedor(false);
        }

        try {
            nodo.setCrearEmpleado((boolean) vector[44]);
        } catch (NullPointerException e) {
            nodo.setCrearEmpleado(false);
        }

        try {
            nodo.setBodegasInternas((boolean) vector[45]);
        } catch (NullPointerException e) {
            nodo.setBodegasInternas(false);
        }

        try {
            nodo.setClientesPrestamos((boolean) vector[46]);
        } catch (NullPointerException e) {
            nodo.setClientesPrestamos(false);
        }

        try {
            nodo.setShowRoom((boolean) vector[47]);
        } catch (NullPointerException e) {
            nodo.setShowRoom(false);
        }

        try {
            nodo.setReporteClientes((boolean) vector[48]);
        } catch (NullPointerException e) {
            nodo.setReporteClientes(false);
        }

        try {
            nodo.setReporteProveedor((boolean) vector[49]);
        } catch (NullPointerException e) {
            nodo.setReporteProveedor(false);
        }

        try {
            nodo.setReporteEmpleados((boolean) vector[50]);
        } catch (NullPointerException e) {
            nodo.setReporteEmpleados(false);
        }

        try {
            nodo.setAbonosCxC((boolean) vector[51]);
        } catch (NullPointerException e) {
            nodo.setAbonosCxC(false);
        }

        try {
            nodo.setAbonosCxP((boolean) vector[52]);
        } catch (NullPointerException e) {
            nodo.setAbonosCxP(false);
        }

        try {
            nodo.setNotasCredito((boolean) vector[53]);
        } catch (NullPointerException e) {
            nodo.setNotasCredito(false);
        }

        try {
            nodo.setReporteCarteraCxC((boolean) vector[54]);
        } catch (NullPointerException e) {
            nodo.setReporteCarteraCxC(false);
        }

        try {
            nodo.setReporteAbonosCxC((boolean) vector[55]);
        } catch (NullPointerException e) {
            nodo.setCreditos(false);
        }

        try {
            nodo.setReporteCarteraCxP((boolean) vector[56]);
        } catch (NullPointerException e) {
            nodo.setReporteCarteraCxP(false);
        }

        try {
            nodo.setReporteAbonosCxP((boolean) vector[57]);
        } catch (NullPointerException e) {
            nodo.setReporteAbonosCxP(false);
        }

        try {
            nodo.setReporteNotasCredito((boolean) vector[58]);
        } catch (NullPointerException e) {
            nodo.setReporteNotasCredito(false);
        }

        try {
            nodo.setCodigos((boolean) vector[59]);
        } catch (NullPointerException e) {
            nodo.setCodigos(false);
        }

        try {
            nodo.setEgresos((boolean) vector[60]);
        } catch (NullPointerException e) {
            nodo.setEgresos(false);
        }

        try {
            nodo.setBancos((boolean) vector[61]);
        } catch (NullPointerException e) {
            nodo.setBancos(false);
        }

        try {
            nodo.setCuadreCaja((boolean) vector[62]);
        } catch (NullPointerException e) {
            nodo.setCuadreCaja(false);
        }

        try {
            nodo.setReporteEgresos((boolean) vector[63]);
        } catch (NullPointerException e) {
            nodo.setReporteEgresos(false);
        }

        try {
            nodo.setReporteBancos((boolean) vector[64]);
        } catch (NullPointerException e) {
            nodo.setReporteBancos(false);
        }

        try {
            nodo.setReporteCuadreCaja((boolean) vector[65]);
        } catch (NullPointerException e) {
            nodo.setReporteCuadreCaja(false);
        }

        try {
            nodo.setRegistrarAgenda((boolean) vector[66]);
        } catch (NullPointerException e) {
            nodo.setRegistrarAgenda(false);
        }

        try {
            nodo.setReportesAgenda((boolean) vector[67]);
        } catch (NullPointerException e) {
            nodo.setReportesAgenda(false);
        }

        try {
            nodo.setMascotas((boolean) vector[68]);
        } catch (NullPointerException e) {
            nodo.setMascotas(false);
        }

        try {
            nodo.setHistoriaClinica((boolean) vector[69]);
        } catch (NullPointerException e) {
            nodo.setHistoriaClinica(false);
        }

        try {
            nodo.setGuarderia((boolean) vector[70]);
        } catch (NullPointerException e) {
            nodo.setGuarderia(false);
        }

        try {
            nodo.setPeluqueria((boolean) vector[71]);
        } catch (NullPointerException e) {
            nodo.setPeluqueria(false);
        }

        try {
            nodo.setHospitalizacion((boolean) vector[72]);
        } catch (NullPointerException e) {
            nodo.setHospitalizacion(false);
        }

        try {
            nodo.setAlertas((boolean) vector[73]);
        } catch (NullPointerException e) {
            nodo.setAlertas(false);
        }

        try {
            nodo.setReporteMascotas((boolean) vector[74]);
        } catch (NullPointerException e) {
            nodo.setReporteMascotas(false);
        }

        try {
            nodo.setReporteGuarderia((boolean) vector[75]);
        } catch (NullPointerException e) {
            nodo.setReportesAgenda(false);
        }

        try {
            nodo.setReportePeluqueria((boolean) vector[76]);
        } catch (NullPointerException e) {
            nodo.setReportePeluqueria(false);
        }

        try {
            nodo.setReporteHospitalizacion((boolean) vector[77]);
        } catch (NullPointerException e) {
            nodo.setReporteHospitalizacion(false);
        }

        try {
            nodo.setFormatos((boolean) vector[78]);
        } catch (NullPointerException e) {
            nodo.setFormatos(false);
        }

        try {
            nodo.setOrdenesServicioMedica((boolean) vector[79]);
        } catch (NullPointerException e) {
            nodo.setOrdenesServicioMedica(false);
        }

        try {
            nodo.setHistoria((boolean) vector[80]);
        } catch (NullPointerException e) {
            nodo.setHistoria(false);
        }

        try {
            nodo.setFormatosMedicas((boolean) vector[81]);
        } catch (NullPointerException e) {
            nodo.setFormatosMedicas(false);
        }

        try {
            nodo.setConvenio((boolean) vector[82]);
        } catch (NullPointerException e) {
            nodo.setConvenio(false);
        }

        try {
            nodo.setNotaEnfermaria((boolean) vector[83]);
        } catch (NullPointerException e) {
            nodo.setNotaEnfermaria(false);
        }

        try {
            nodo.setMedicamentos((boolean) vector[84]);
        } catch (NullPointerException e) {
            nodo.setMedicamentos(false);
        }

        try {
            nodo.setReporteOrdenes((boolean) vector[85]);
        } catch (NullPointerException e) {
            nodo.setReporteOrdenes(false);
        }

        try {
            nodo.setReporteFormulas((boolean) vector[86]);
        } catch (NullPointerException e) {
            nodo.setReporteFormulas(false);
        }

        try {
            nodo.setCertificadoEstudiante((boolean) vector[87]);
        } catch (NullPointerException e) {
            nodo.setCertificadoEstudiante(false);
        }

        try {
            nodo.setCertificadoMedico((boolean) vector[88]);
        } catch (NullPointerException e) {
            nodo.setCertificadoMedico(false);
        }

        try {
            nodo.setCertificadoOyS((boolean) vector[89]);
        } catch (NullPointerException e) {
            nodo.setCertificadoOyS(false);
        }

        return nodo;
    }

    public ndCompra llenarCompra(Object[] vector) {

        ndCompra nodo = new ndCompra();

        nodo.setIngreso((String) vector[0]);
        nodo.setProducto((String) vector[1]);
        nodo.setValor(String.valueOf(vector[2]));
        
        String aux = vector[3].toString().replace(".", "").replace(",", ".");
        nodo.setCantidad(Double.parseDouble(aux));
        
        nodo.setDescuento(String.valueOf(vector[4]));
        nodo.setTotal(String.valueOf(vector[5]));
        nodo.setIva(String.valueOf(vector[6]));
        nodo.setSubtotal(String.valueOf(vector[7]));
        nodo.setPorIva(Float.parseFloat(vector[8].toString()));
        nodo.setDescripcion((String) vector[9]);
        nodo.setPlu((String) vector[10]);

        String aux2 = vector[11].toString().replace(".", "").replace(",", ".");
        nodo.setCantidad2(Double.parseDouble(aux2));

        nodo.setValorPlu(String.valueOf(vector[12]));
        nodo.setBodega((String) vector[13]);
        nodo.setImpoconsumo(String.valueOf(vector[14]));
        nodo.setPorcImpo(Float.parseFloat(vector[15].toString().replace(",", ".")));
        nodo.setUsuario((String) vector[16]);
        nodo.setTipoDescuento((String) vector[17]);
        return nodo;
    }

    public ndCxc llenarCxc(Object[] vector) {

        ndCxc nodo = new ndCxc();

        nodo.setFactura((String) vector[0]);
        nodo.setTipo((String) vector[1]);
        nodo.setEstado(String.valueOf(vector[2]));
        nodo.setRecibo(String.valueOf(vector[3]));
        nodo.setValor(String.valueOf(vector[4]));
        nodo.setPlazo(Integer.parseInt((String) (vector[5])));
        nodo.setVencimiento((String) vector[6]);
        nodo.setUsuario((String) vector[7]);
        nodo.setTerminal(String.valueOf(vector[8]));
        try {
            nodo.setCuotas((boolean) vector[9]);
        } catch (Exception e) {
            nodo.setCuotas(false);
        }
        nodo.setFactura2((String) vector[10]);

        return nodo;
    }

    public ndBodega llenarBodega(Object[] vector) {

        ndBodega nodo = new ndBodega();

        nodo.setCodigo((String) vector[0]);
        nodo.setNombre((String) vector[1]);
        nodo.setDireccion((String) vector[2]);
        nodo.setTelefono((String) vector[3]);
        nodo.setUsuario((String) vector[4]);
        nodo.setCiudad((String) vector[5]);
        nodo.setAbreviado((String) vector[6]);
        nodo.setFilas((String) vector[7]);
        nodo.setColumnas((String) vector[8]);
        nodo.setTipo((String) vector[9]);
        nodo.setBase((String) vector[10]);

        return nodo;
    }

    public ndNc llenarNc(Object[] vector) {

        ndNc nodo = new ndNc();

        nodo.setNc((String) vector[0]);
        nodo.setCliente((String) vector[1]);
        nodo.setFactura((String) vector[2]);
        nodo.setValor(String.valueOf(vector[3]));
        nodo.setRestante(String.valueOf(vector[4]));
        try {
            nodo.setAnulada((boolean) vector[5]);
        } catch (Exception e) {
            nodo.setAnulada(false);
        }
        nodo.setUsuario((String) vector[6]);
        nodo.setFecha((String) vector[7]);
        nodo.setVendedor((String) vector[8]);
        nodo.setTerminal(String.valueOf(vector[9]));
        nodo.setResolucion((String) vector[10]);
        nodo.setReembolsar((boolean) vector[11]);
        nodo.setHora((String) vector[12]);
        nodo.setBodega((String) vector[13]);
        nodo.setObservaciones((String) vector[14]);

        return nodo;
    }

    public ndTraslado llenarTraslado(Object[] vector) {

        ndTraslado nodo = new ndTraslado();

        nodo.setId((String) vector[0]);
        nodo.setOrigen((String) vector[1]);
        nodo.setDestino(String.valueOf(vector[2]));
        nodo.setFecha(String.valueOf(vector[3]));
        nodo.setUnidades(Double.parseDouble((String) (vector[4])));
        nodo.setValor(String.valueOf(vector[5]));
        nodo.setTipo((String) vector[6]);
        nodo.setUsuario((String) vector[7]);
        nodo.setTerminal(String.valueOf(vector[8]));
        nodo.setHora((String) vector[9]);
        nodo.setBodega((String) vector[10]);
        nodo.setModeloContable((String) vector[11]);
        return nodo;
    }

    public ndTrasladoBodega llenarTrasladoBodega(Object[] vector) {

        ndTrasladoBodega nodo = new ndTrasladoBodega();

        nodo.setId((String) vector[0]);
        nodo.setOrigen((String) vector[1]);
        nodo.setDestino((String) vector[2]);
        nodo.setFecha((String) vector[3]);
        nodo.setUnidades(String.valueOf(vector[4]));
        nodo.setValor(String.valueOf(vector[5]));
        nodo.setTipo((String) vector[6]);
        nodo.setDevolucion((String) vector[7]);
        nodo.setUsuario((String) vector[8]);
        nodo.setTerminal((String) vector[9]);
        nodo.setDiasPlazo((String) vector[10]);
        nodo.setEstado((String) vector[11]);
        nodo.setHora((String) vector[12]);

        return nodo;
    }

    public ndProductoAjustes llenarAjustes(Object[] vector) {
        ndProductoAjustes nodo = new ndProductoAjustes();
        nodo.setTraslado((String) vector[0]);
        nodo.setProducto((String) vector[1]);
        nodo.setCantidad(Double.parseDouble(((vector[2]).toString()).replace(",", ".")));
        nodo.setLista(String.valueOf(vector[3]));
        nodo.setTotal(String.valueOf(vector[4]));
        nodo.setObservaciones((String) vector[5]);
        nodo.setDescripcion((String) vector[6]);
        nodo.setPlu((String) vector[7]);
        nodo.setCantidad2(Double.parseDouble(((vector[8]).toString()).replace(",", ".")));
        nodo.setIdProd((String) vector[9]);
        nodo.setImei((String) vector[10]);
        nodo.setTalla((String) vector[11]);
        nodo.setColor((String) vector[12]);
        nodo.setSubtotal(String.valueOf(vector[13]));
        nodo.setIva(String.valueOf(vector[14]));
        nodo.setPorcIva(Double.parseDouble(((vector[15]).toString()).replace(",", ".")));
        nodo.setCosto(String.valueOf(vector[16]));
        nodo.setPorcImpo(Double.parseDouble(((vector[17]).toString()).replace(",", ".")));
        nodo.setImpoconsumo(String.valueOf(vector[18]));
        return nodo;
    }

    public ndProdTraslBodega llenarProdTralBodega(Object[] vector) {

        ndProdTraslBodega nodo = new ndProdTraslBodega();

        nodo.setTraslado((String) vector[0]);
        nodo.setProducto((String) vector[1]);
        nodo.setCantidad(String.valueOf(vector[2]));
        nodo.setLista(String.valueOf(vector[3]));
        nodo.setImeil((String) vector[4]);
        nodo.setObservaciones((String) vector[5]);
        nodo.setTotal(String.valueOf(vector[6]));
        nodo.setLote((String) vector[7]);
        nodo.setEstado((String) vector[8]);
        nodo.setFacturados((String) vector[9]);
        nodo.setDevueltos((String) vector[10]);
        nodo.setIdProd((String) vector[11]);
        nodo.setCant2(String.valueOf(vector[12]));
        nodo.setPlu(String.valueOf(vector[13]));
        nodo.setIva(String.valueOf(vector[14]));

        return nodo;
    }

    public ndEgreso llenarEgreso(Object[] vector) {

        ndEgreso nodo = new ndEgreso();

        nodo.setId((String) vector[0]);
        nodo.setNit((String) vector[1]);
        nodo.setRazon(String.valueOf(vector[2]));
        nodo.setTelefono(String.valueOf(vector[3]));
        nodo.setDireccion(String.valueOf(vector[4]));
        nodo.setTotal(String.valueOf(vector[5]));
        nodo.setSubtotal(String.valueOf(vector[6]));
        nodo.setIva(String.valueOf(vector[7]));
        nodo.setCred1(String.valueOf(vector[8]));
        nodo.setCred2(String.valueOf(vector[9]));
        nodo.setDeb1(String.valueOf(vector[10]));
        nodo.setDeb2(String.valueOf(vector[11]));
        nodo.setCheque(String.valueOf(vector[12]));
        nodo.setBanco(String.valueOf(vector[13]));
        nodo.setEfectivo(String.valueOf(vector[14]));
        nodo.setFecha(String.valueOf(vector[15]));
        nodo.setDocumento(String.valueOf(vector[16]));
        nodo.setUsuario(String.valueOf(vector[17]));
        nodo.setTerminal(String.valueOf(vector[18]));
        nodo.setEstado(String.valueOf(vector[19]));
        nodo.setTipo(String.valueOf(vector[20]));
        nodo.setIngresoAsociado((String) vector[21]);
        nodo.setEgreso((String) vector[22]);
        nodo.setModeloContable((String) vector[23]);

        return nodo;
    }

    public Object[] pasarDatosCotizacion(ndCotizacion nodo) {

        Object[] vector = {
            nodo.getIdFactura(), nodo.getCliente(), nodo.getVendedor(), nodo.getRed(), nodo.getFechaFactura(), nodo.getFechaVencimiento(), nodo.getComprobante(),
            nodo.getCotizacion(), nodo.isAnulada(), nodo.getAnula(), nodo.isCredito(), nodo.getCxc(), nodo.getUsuario(), nodo.getObservacion(),
            nodo.isAnulada1(), nodo.getAnula1(), nodo.isCredito1(), nodo.getCxc1(), nodo.getUsuario1(), nodo.getFechaAlerta(), nodo.getTerminal(),
            nodo.getEstadoGeneral(), nodo.getEstado2(), nodo.getFactura(), nodo.getResolucion(), nodo.getFechaAnulacion(), nodo.getCuadreAnulacion(),
            nodo.getUsuarioAnula(), nodo.getPlaca(), nodo.getGarantia(), nodo.getDiasGarantia(), nodo.getRango(), nodo.getTerminos(), nodo.getNotaAnulacion(),
            nodo.getConseMesa(), nodo.getProducto(), nodo.getNC(), nodo.getConcepto(), nodo.getDescripcion(), nodo.getPlu(), nodo.getEstado(), nodo.getTercero(),
            nodo.getPreparacion(), nodo.getBodega()};

        return vector;
    }

    public Object[] pasarDatosPedido(ndPedido nodo) {
        Object[] vector = {
            nodo.getIdFactura(), nodo.getCliente(), nodo.getVendedor(), nodo.getRed(), nodo.getFechaFactura(), nodo.getFechaVencimiento(), nodo.getComprobante(),
            nodo.getCotizacion(), nodo.isAnulada(), nodo.getAnula(), nodo.isCredito(), nodo.getCxc(), nodo.getUsuario(), nodo.getObservacion(),
            nodo.isAnulada1(), nodo.getAnula1(), nodo.isCredito1(), nodo.getCxc1(), nodo.getUsuario1(), nodo.getFechaAlerta(), nodo.getTerminal(),
            nodo.getEstadoGeneral(), nodo.getEstado2(), nodo.getFactura(), nodo.getResolucion(), nodo.getFechaAnulacion(), nodo.getCuadreAnulacion(),
            nodo.getUsuarioAnula(), nodo.getPlaca(), nodo.getGarantia(), nodo.getDiasGarantia(), nodo.getRango(), nodo.getTerminos(), nodo.getNotaAnulacion(),
            nodo.getConseMesa(), nodo.getProducto(), nodo.getNC(), nodo.getConcepto(), nodo.getDescripcion(), nodo.getPlu(), nodo.getEstado(), nodo.getTercero(),
            nodo.getPreparacion(), nodo.getBodega(), nodo.getIdProd(), nodo.getDetalle()};

        return vector;
    }

    public Object[] pasarDatosPlanSepare(ndPlanSepare nodo) {

        Object[] vector = {
            nodo.getIdFactura(), nodo.getCliente(), nodo.getVendedor(), nodo.getRed(), nodo.getFechaFactura(), nodo.getFechaVencimiento(), nodo.getComprobante(),
            nodo.getCotizacion(), nodo.isAnulada(), nodo.getAnula(), nodo.isCredito(), nodo.getCxc(), nodo.getUsuario(), nodo.getObservacion(),
            nodo.isAnulada1(), nodo.getAnula1(), nodo.isCredito1(), nodo.getCxc1(), nodo.getUsuario1(), nodo.getFechaAlerta(), nodo.getTerminal(),
            nodo.getEstadoGeneral(), nodo.getEstado2(), nodo.getFactura(), nodo.getResolucion(), nodo.getFechaAnulacion(), nodo.getCuadreAnulacion(),
            nodo.getUsuarioAnula(), nodo.getPlaca(), nodo.getGarantia(), nodo.getDiasGarantia(), nodo.getRango(), nodo.getTerminos(), nodo.getNotaAnulacion(),
            nodo.getConseMesa(), nodo.getProducto(), nodo.getNC(), nodo.getConcepto(), nodo.getDescripcion(), nodo.getPlu(), nodo.getEstado(), nodo.getTercero(),
            nodo.getPreparacion(), nodo.getImei(), nodo.getIdProd(), nodo.getBodega()};

        return vector;
    }

    public Object[] pasarDatosAbonos(ndGarantia nodo) {

        Object[] vector = {nodo.getId(), nodo.getCliente(), nodo.getVendedor(), nodo.getRed(), nodo.getFechaFactura(),
            nodo.getFechaVencimiento(), nodo.getComprobante(), nodo.getCotizacion(), nodo.getAnula(),
            nodo.isAnulada(), nodo.isCredito(), nodo.getCxc(), nodo.getUsuario(), nodo.getObservaciones(), nodo.getFechaAlerta(),
            nodo.getTerminal(), nodo.getEstado(), nodo.getFactura(), nodo.getResolucion(), nodo.getPlaca(), nodo.getGarantia(), nodo.getDiasGarantia(),
            nodo.getRango(), nodo.getTerminos(), nodo.getNotaAnulacion(), nodo.getConseMesa(), nodo.getModeloContable()};

        return vector;
    }

    public Object[] pasarDatosFactura(ndFactura nodo) {

        Object[] vector = {
            nodo.getIdFactura(), nodo.getCliente(), nodo.getVendedor(), nodo.getRed(), nodo.getFechaFactura(), nodo.getFechaVencimiento(),
            nodo.getComprobante(), nodo.getCotizacion(), nodo.isAnulada(), nodo.getAnula(), nodo.isCredito(), nodo.getCxc(), nodo.getUsuario(),
            nodo.getObservacion(), nodo.isAnulada1(), nodo.getAnula1(), nodo.isCredito1(), nodo.getCxc1(), nodo.getUsuario1(), nodo.getFechaAlerta(),
            nodo.getTerminal(), nodo.getEstadoGeneral(), nodo.getEstado2(), nodo.getFactura(), nodo.getResolucion(), nodo.getFechaAnulacion(),
            nodo.getCuadreAnulacion(), nodo.getUsuarioAnula(), nodo.getPlaca(), nodo.getGarantia(), nodo.getDiasGarantia(), nodo.getRango(),
            nodo.getTerminos(), nodo.getNotaAnulacion(), nodo.getConseMesa(), nodo.getProducto(), nodo.getNC(), nodo.getConcepto(), nodo.getDescripcion(),
            nodo.getPlu(), nodo.getEstado(), nodo.getTercero(), nodo.getPreparacion(), nodo.getTurno(), nodo.getFranquisia(), nodo.getComision(),
            nodo.getImei(), nodo.getLote(), nodo.getIdProd(), nodo.getMesFacturado(), nodo.getPorcPropina(), nodo.getIdCosteo(), nodo.getHora(),
            nodo.isSisteCredito(), nodo.getBodega(), nodo.getModeloContable()};
        return vector;
    }

    public Object[] pasarDatosOServicio1(ndOServicio1 nodo) {

        Object[] vector = {
            nodo.getIdFactura(), nodo.getCliente(), nodo.getVendedor(), nodo.getRed(), nodo.getFechaFactura(), nodo.getFechaVencimiento(), nodo.getComprobante(),
            nodo.getCotizacion(), nodo.isAnulada(), nodo.getAnula(), nodo.isCredito(), nodo.getCxc(), nodo.getUsuario(), nodo.getObservacion(),
            nodo.isAnulada1(), nodo.getAnula1(), nodo.isCredito1(), nodo.getCxc1(), nodo.getUsuario1(), nodo.getFechaAlerta(), nodo.getTerminal(),
            nodo.getEstadoGeneral(), nodo.getEstado2(), nodo.getFactura(), nodo.getResolucion(), nodo.getFechaAnulacion(), nodo.getCuadreAnulacion(),
            nodo.getUsuarioAnula(), nodo.getPlaca(), nodo.getGarantia(), nodo.getDiasGarantia(), nodo.getRango(), nodo.getTerminos(), nodo.getNotaAnulacion(),
            nodo.getConseMesa(), nodo.getProducto(), nodo.getNC(), nodo.getConcepto(), nodo.getDescripcion(), nodo.getPlu(), nodo.getEstado(), nodo.getTercero(),
            nodo.getPreparacion(), nodo.getBodega()};

        return vector;
    }

    public Object[] pasarDatosCongelada(ndCongelada nodo) {

        Object[] vector = {
            nodo.getIdFactura(), nodo.getCliente(), nodo.getVendedor(), nodo.getRed(), nodo.getFechaFactura(), nodo.getFechaVencimiento(), nodo.getComprobante(),
            nodo.getCotizacion(), nodo.isAnulada(), nodo.getAnula(), nodo.isCredito(), nodo.getCxc(), nodo.getUsuario(), nodo.getObservacion(),
            nodo.isAnulada1(), nodo.getAnula1(), nodo.isCredito1(), nodo.getCxc1(), nodo.getUsuario1(), nodo.getFechaAlerta(), nodo.getTerminal(),
            nodo.getEstadoGeneral(), nodo.getEstado2(), nodo.getFactura(), nodo.getResolucion(), nodo.getFechaAnulacion(), nodo.getCuadreAnulacion(),
            nodo.getUsuarioAnula(), nodo.getPlaca(), nodo.getGarantia(), nodo.getDiasGarantia(), nodo.getRango(), nodo.getTerminos(), nodo.getNotaAnulacion(),
            nodo.getConseMesa(), nodo.getProducto(), nodo.getNC(), nodo.getConcepto(), nodo.getDescripcion(), nodo.getPlu(), nodo.getEstado(), nodo.getTercero(),
            nodo.getPreparacion(), nodo.getTurno(), nodo.getImei(), nodo.getIdProd(), nodo.getBodega()};

        return vector;
    }

    public Object[] pasarValoresCongelada(ndCongelada nodo) {
        Object[] vector = {
            nodo.getEfectivoGeneral(), nodo.getNcGeneral(), nodo.getChequeGeneral(), nodo.getTargetaGeneral(), nodo.getTotalGeneral(), nodo.getDescuentoGeneral(),
            nodo.getIvaGeneral(), nodo.getSubtotalGeneral(), nodo.getRtIva(), nodo.getRtIca(), nodo.getRtFuente(), nodo.getOtros(), nodo.getDevuelta(),
            nodo.getCopago(), nodo.getLista(), nodo.getCantidad(), nodo.getDescuento(), nodo.getTotal(), nodo.getIva(), nodo.getSubtotal(), nodo.getUtilidad(),
            nodo.getPorcDescuento(), nodo.getCant2(), nodo.getPorcIva(), nodo.getUtilidad1(), nodo.getImpoconsumoGeneral(), nodo.getPorcImpo(), nodo.getImpoconsumo()
        };
        return vector;
    }

    public Object[] pasarValoresGarantia(ndGarantia nodo) {

        Object[] vector = {nodo.getEfectivo(), nodo.getNc(), nodo.getCheque(), nodo.getTargeta(), nodo.getTotal(), nodo.getDescuentos(),
            nodo.getIva(), nodo.getSubtotal(), nodo.getRtIva(), nodo.getRtIca(), nodo.getRtFuente(), nodo.getOtros(), nodo.getDevuelta(), nodo.getCopago()};
        return vector;
    }

    public Object[] pasarValoresFactura(ndFactura nodo) {
        Object[] vector = {
            nodo.getEfectivoGeneral(), nodo.getNcGeneral(), nodo.getChequeGeneral(), nodo.getTargetaGeneral(), nodo.getTotalGeneral(),
            nodo.getDescuentoGeneral(), nodo.getIvaGeneral(), nodo.getSubtotalGeneral(), nodo.getRtIva(), nodo.getRtIca(), nodo.getRtFuente(),
            nodo.getOtros(), nodo.getDevuelta(), nodo.getCopago(), nodo.getLista(), nodo.getCantidad(), nodo.getDescuento(), nodo.getTotal(),
            nodo.getIva(), nodo.getSubtotal(), nodo.getUtilidad(), nodo.getPorcDescuento(), nodo.getCant2(), nodo.getPorcIva(), nodo.getUtilidad1(),
            nodo.getImpuestos(), nodo.getImpoGeneral(), nodo.getValorComision(), nodo.getTotalFacturaComision(), nodo.getTarjetaCredito(),
            nodo.getTotalPropina(), nodo.getPorcImpo(), nodo.getImpoconsumo(), nodo.getCosto()
        };
        return vector;
    }

    public Object[] pasarValoresOServicio1(ndOServicio1 nodo) {

        Object[] vector = {
            nodo.getEfectivoGeneral(), nodo.getNcGeneral(), nodo.getChequeGeneral(), nodo.getTargetaGeneral(), nodo.getTotalGeneral(), nodo.getDescuentoGeneral(),
            nodo.getIvaGeneral(), nodo.getSubtotalGeneral(), nodo.getRtIva(), nodo.getRtIca(), nodo.getRtFuente(), nodo.getOtros(), nodo.getDevuelta(),
            nodo.getCopago(), nodo.getLista(), nodo.getCantidad(), nodo.getDescuento(), nodo.getTotal(), nodo.getIva(), nodo.getSubtotal(), nodo.getUtilidad(),
            nodo.getPorcDescuento(), nodo.getCant2(), nodo.getPorcIva(), nodo.getUtilidad1()
        };
        return vector;
    }

    public Object[] pasarValoresPlanSepare(ndPlanSepare nodo) {
        Object[] vector = {
            nodo.getEfectivoGeneral(), nodo.getNcGeneral(), nodo.getChequeGeneral(), nodo.getTargetaGeneral(), nodo.getTotalGeneral(), nodo.getDescuentoGeneral(),
            nodo.getIvaGeneral(), nodo.getSubtotalGeneral(), nodo.getRtIva(), nodo.getRtIca(), nodo.getRtFuente(), nodo.getOtros(), nodo.getDevuelta(),
            nodo.getCopago(), nodo.getLista(), nodo.getCantidad(), nodo.getDescuento(), nodo.getTotal(), nodo.getIva(), nodo.getSubtotal(), nodo.getUtilidad(),
            nodo.getPorcDescuento(), nodo.getCant2(), nodo.getPorcIva(), nodo.getUtilidad1()
        };
        return vector;
    }

    public Object[] pasarValoresPedido(ndPedido nodo) {

        Object[] vector = {
            nodo.getEfectivoGeneral(), nodo.getNcGeneral(), nodo.getChequeGeneral(), nodo.getTargetaGeneral(), nodo.getTotalGeneral(), nodo.getDescuentoGeneral(),
            nodo.getIvaGeneral(), nodo.getSubtotalGeneral(), nodo.getRtIva(), nodo.getRtIca(), nodo.getRtFuente(), nodo.getOtros(), nodo.getDevuelta(),
            nodo.getCopago(), nodo.getLista(), nodo.getCantidad(), nodo.getDescuento(), nodo.getTotal(), nodo.getIva(), nodo.getSubtotal(), nodo.getUtilidad(),
            nodo.getPorcDescuento(), nodo.getCant2(), nodo.getPorcIva(), nodo.getUtilidad1()
        };
        return vector;
    }

    public Object[] pasarValoresCotizacion(ndCotizacion nodo) {

        Object[] vector = {
            nodo.getEfectivoGeneral(), nodo.getNcGeneral(), nodo.getChequeGeneral(), nodo.getTargetaGeneral(), nodo.getTotalGeneral(), nodo.getDescuentoGeneral(),
            nodo.getIvaGeneral(), nodo.getSubtotalGeneral(), nodo.getRtIva(), nodo.getRtIca(), nodo.getRtFuente(), nodo.getOtros(), nodo.getDevuelta(),
            nodo.getCopago(), nodo.getLista(), nodo.getCantidad(), nodo.getDescuento(), nodo.getTotal(), nodo.getIva(), nodo.getSubtotal(), nodo.getUtilidad(),
            nodo.getPorcDescuento(), nodo.getCant2(), nodo.getPorcIva(), nodo.getUtilidad1()
        };
        return vector;
    }

    public Object[] pasarDatosCaja(ndCaja nodo) {
        Object[] vector = {nodo.getId(), nodo.getFecha(), nodo.getHora(), nodo.getCajero(), nodo.getUsuario(), nodo.getTermianl(),
            nodo.getPrimeraFactura(), nodo.getUltimaFactura(), nodo.getPrimerAbono(), nodo.getUltimoAbono()
        };
        return vector;
    }

    public Object[] pasarValoresCaja(ndCaja nodo) {
        Object[] vector = {nodo.getEfectivo(), nodo.getTarjeta(), nodo.getCheque(), nodo.getNc(), nodo.getTotal(),
            nodo.getSistema(), nodo.getDiferencia(), nodo.getGasto(), nodo.getRecogida(), nodo.getBase(),
            nodo.getTotalVenta(), nodo.getTotalCredito(), nodo.getTotalContado(), nodo.getNcRealizadas(),
            nodo.getEfectivoSistema(), nodo.getChequeSistema(), nodo.getTarjetaSistema(), nodo.getNcSistema(),
            nodo.getContContado(), nodo.getContCredito(), nodo.getContNcRecibidas(), nodo.getContNcRealizadas(),
            nodo.getContEfectivo(), nodo.getContCheque(), nodo.getContTarjeta(), nodo.getContAbonos(), nodo.getTotalAbonos(),
            nodo.getTotalFactSepares(), nodo.getTotalCuotaInicial(), nodo.getContCuotaInicial(), nodo.getContGastos(), nodo.getNcReembolsadas(),
            nodo.getContNcReembolsadas(), nodo.getGastosManuales(), nodo.getAbonosCartera(), nodo.getAbonosSepare(), nodo.getVentasDomi(), nodo.getItemDomi(),
            nodo.getTotalPropinas(), nodo.getSisteCredito()
        };

        return vector;
    }

    public Object[] pasarDatosOServicio(ndOServicio nodo) {
        Object[] vector = {nodo.getId(), nodo.getPlaca(), nodo.getTipo(), nodo.getModelo(), nodo.getNumeroChasis(), nodo.getFechaCompra(), nodo.getMarca(), nodo.getKm(), nodo.getNumeroMotor(), nodo.getColor(), nodo.getProblema()};
        return vector;
    }

    public Object[] pasarDatosCxc(ndCxc nodo) {
        Object[] vector = {nodo.getFactura(), nodo.getTipo(), nodo.getEstado(), nodo.getRecibo(), nodo.getPlazo(),
            nodo.getVencimiento(), nodo.getUsuario(), nodo.getTerminal(), nodo.isCuotas(), nodo.getFactura2()};
        return vector;
    }

    public Object[] pasarValoresCxc(ndCxc nodo) {
        Object[] vector = {nodo.getValor()};
        return vector;
    }

    public ndNotasCredito llenarNotasCredito(Object[] vector) {

        ndNotasCredito nodo = new ndNotasCredito();

        nodo.setFactura((String) vector[0]);
        nodo.setProducto((String) vector[1]);
        nodo.setLista(String.valueOf(vector[2]));
        nodo.setCantidad(Double.parseDouble(((String) vector[3]).replace(",", ".")));
        nodo.setDescuento(String.valueOf(vector[4]));
        nodo.setTotal(String.valueOf(vector[5]));
        nodo.setIva(String.valueOf(vector[6]));
        nodo.setSubtotal(String.valueOf(vector[7]));
        nodo.setNC((String) vector[8]);
        nodo.setUtilidad(String.valueOf(vector[9]));
        nodo.setPorcDescuento((String) vector[10]);
        nodo.setDescripcion((String) vector[11]);
        nodo.setPlu((String) vector[12]);
        nodo.setCant2(Double.parseDouble(((String) vector[13]).replace(",", ".")));
        nodo.setEstado((String) vector[14]);
        nodo.setPorcIva((String) vector[15]);
        nodo.setTercero((String) vector[16]);
        nodo.setUtilidad1(String.valueOf(vector[17]));
        nodo.setPreparacion((String) vector[18]);
        nodo.setConseMesa((String) vector[19]);
        nodo.setImei((String) vector[20]);
        nodo.setIdProd((String) vector[21]);
        nodo.setPorcImpoconsumo((String) vector[22]);
        nodo.setImpoconsumo(String.valueOf(vector[23]));

        return nodo;
    }

    public Object[] pasarDatosNotasCredito(ndNotasCredito nodo) {
        Object[] vector = {nodo.getFactura(), nodo.getProducto(), nodo.getCantidad(), nodo.getNC(), nodo.getDescripcion(),
            nodo.getPlu(), nodo.getCant2(), nodo.getEstado(), nodo.getPorcIva(), nodo.getTercero(), nodo.getPreparacion(),
            nodo.getConseMesa(), nodo.getImei(), nodo.getIdProd(), nodo.getPorcImpoconsumo()};
        return vector;
    }

    public Object[] pasarValoresNotasCredito(ndNotasCredito nodo) {
        Object[] vector = {nodo.getLista(), nodo.getDescuento(), nodo.getTotal(), nodo.getIva(), nodo.getSubtotal(), nodo.getUtilidad(),
            nodo.getPorcDescuento(), nodo.getUtilidad1(), nodo.getImpoconsumo()};
        return vector;
    }

    public Object[] pasarDatosProducto(ndProducto nodo) {
        Object[] vector = {nodo.getIdSistema(), nodo.getCodigo(), nodo.getCodigoBarras(), nodo.getDescripcion(), nodo.getGrupo(), nodo.getSubgrupo(), nodo.getProveedor(),
            nodo.getUsuario(), nodo.getUnd(), nodo.getReferencia(), nodo.getUbicacion1(), nodo.getDescripcion2(), nodo.getUbicacion2(), nodo.getCantidad2(), nodo.getDescripcion3(),
            nodo.getUbicacion3(), nodo.getCantidad3(), nodo.getDescripcion4(), nodo.getUbicacion4(), nodo.getCantidad4(), nodo.getDescripcion5(), nodo.getUbicacion5(),
            nodo.getCantidad5(), nodo.getDescripcion6(), nodo.getUbicacion6(), nodo.getCantidad6(), nodo.getDescripcion7(), nodo.getUbicacion7(), nodo.getCantidad7(),
            nodo.getDescripcion8(), nodo.getUbicacion8(), nodo.getCantidad8(), nodo.isPlu2(), nodo.isPlu3(), nodo.isPlu4(), nodo.getPlu5(), nodo.getPlu6(), nodo.getPlu7(),
            nodo.getPlu8(), nodo.getTerminal(), nodo.getClaseBuscador(), nodo.getManejaInventario(), nodo.getPorcentaje(), nodo.getCompras(), nodo.getVentas(), nodo.getNc(),
            nodo.getAjusteEntrada(), nodo.getAjusteSalida(), nodo.getPlanSepare(), nodo.getPedidos(), nodo.getAnulada(), nodo.getInventarioInicial(), nodo.getAjusteInventario(),
            nodo.getFisicoInventario(), nodo.getArmado(), nodo.getCosteo(), nodo.getOrdenServicio(), nodo.getCongelada(), nodo.getTrasladoBod(), nodo.getTrasladoInternoEntrada(),
            nodo.getTrasladoInternoSalida(), nodo.getImpoconsumo(), nodo.getDescripcionIngles(), nodo.getCubicaje(), nodo.getPeso(), nodo.getCodContable(), nodo.getCodArancel(),
            nodo.getTipoProducto(), nodo.getCantMedida(), nodo.getMarca(), nodo.getTipoProd(), nodo.getEnTransito(), nodo.getCodigo2(), nodo.getCodigo3(),
            nodo.getCodigo4(), nodo.getCodigo5(), nodo.getCodigo6(), nodo.getCodigo7(), nodo.getCodigo8(), nodo.getLenteMarco(), nodo.getAdesivo(), nodo.getColor(),
            nodo.getEmpaque(), nodo.getComposicion(), nodo.getRmb(), nodo.getIndVentas(), nodo.getDescripcionLarga(), nodo.getNotaDebito()};
        return vector;
    }

    public Object[] pasarValoresProducto(ndProducto nodo) {

        Object[] vector = {nodo.getIva(), nodo.getL1(), nodo.getL2(), nodo.getL3(), nodo.getL4(), nodo.getL5(), nodo.getL6(), nodo.getL7(), nodo.getL8(), nodo.getMinimo(),
            nodo.getCosto(), nodo.getMinima(), nodo.getMaxima(), nodo.getIvaC(), nodo.getPonderado(), nodo.getImpoconsumoVenta(), nodo.getImpoconsumoCompra()};

        return vector;
    }

    public Object[] pasarDatosProceso(ndProceso nodo) {

        String[] vector = {nodo.getCodigo(), nodo.getNombre(), nodo.getUnd(), nodo.getObservaciones()};

        return vector;
    }

    public Object[] pasarModUsuario(ndUsuario nodo) {
        Object[] vector = {nodo.getUsuario(), nodo.getNombre(), nodo.getContra(), nodo.isFacturas(), nodo.isCongeladas(), nodo.isCotizaciones(), nodo.isPlanSepares(),
            nodo.isCreditos(), nodo.isPedidos(), nodo.isOrdenesServicio(), nodo.isAnulaciones(), nodo.isReimpresion(), nodo.isReportesVentas(), nodo.isReporteVentasAnuladas(),
            nodo.isReporteCotizaciones(), nodo.isReportePlanSepares(), nodo.isReporteCreditos(), nodo.isReporteOrdenesServicio(), nodo.isReporteIvasVentas(), nodo.isReporteDia(),
            nodo.isCreacionProductos(), nodo.isOrdenesCompras(), nodo.isCompras(), nodo.isTrasladosExternos(), nodo.isTrasladosInternos(), nodo.isAjustesEntrada(),
            nodo.isAjustesSalida(), nodo.isGrupos(), nodo.isDisenos(), nodo.isArmados(), nodo.isInventarioInicial(), nodo.isTrasladoDetalle(), nodo.isKardex(),
            nodo.isReporteProductos(), nodo.isReportePrestamos(), nodo.isReporteCompras(), nodo.isReporteAjustes(), nodo.isReporteOrdenCompras(), nodo.isReporteInventario(),
            nodo.isReporteTrasladoInterno(), nodo.isReporteIvasCompras(), nodo.isReporteMovimientoSerial(), nodo.isCrearClientes(), nodo.isCrearProveedor(), nodo.isCrearEmpleado(),
            nodo.isBodegasInternas(), nodo.isClientesPrestamos(), nodo.isShowRoom(), nodo.isReporteClientes(), nodo.isReporteProveedor(), nodo.isReporteEmpleados(),
            nodo.isAbonosCxC(), nodo.isAbonosCxP(), nodo.isNotasCredito(), nodo.isReporteCarteraCxC(), nodo.isReporteAbonosCxC(), nodo.isReporteCarteraCxP(),
            nodo.isReporteAbonosCxP(), nodo.isReporteNotasCredito(), nodo.isCodigos(), nodo.isEgresos(), nodo.isBancos(), nodo.isCuadreCaja(), nodo.isReporteEgresos(),
            nodo.isReporteBancos(), nodo.isReporteCuadreCaja(), nodo.isRegistrarAgenda(), nodo.isReportesAgenda(), nodo.isMascotas(), nodo.isHistoriaClinica(),
            nodo.isGuarderia(), nodo.isPeluqueria(), nodo.isHospitalizacion(), nodo.isAlertas(), nodo.isReporteMascotas(), nodo.isReporteGuarderia(),
            nodo.isReportePeluqueria(), nodo.isReporteHospitalizacion(), nodo.isFormatos(), nodo.isOrdenesServicioMedica(), nodo.isHistoria(), nodo.isFormatos(),
            nodo.isConvenio(), nodo.isNotaEnfermaria(), nodo.isMedicamentos(), nodo.isReporteOrdenes(), nodo.isReporteFormulas(), nodo.isCertificadoEstudiante(),
            nodo.isCertificadoMedico(), nodo.isCertificadoOyS()};
        return vector;
    }

    public Object[] pasarDatosUsuario(ndUsuario nodo) {
        Object[] vector = {nodo.getUsuario(), nodo.getNombre(), nodo.getContra(), nodo.isFacturas(), nodo.isCongeladas(), nodo.isCotizaciones(), nodo.isPlanSepares(),
            nodo.isCreditos(), nodo.isPedidos(), nodo.isOrdenesServicio(), nodo.isAnulaciones(), nodo.isReimpresion(), nodo.isReportesVentas(), nodo.isReporteVentasAnuladas(),
            nodo.isReporteCotizaciones(), nodo.isReportePlanSepares(), nodo.isReporteCreditos(), nodo.isReporteOrdenesServicio(), nodo.isReporteIvasVentas(), nodo.isReporteDia(),
            nodo.isCreacionProductos(), nodo.isOrdenesCompras(), nodo.isCompras(), nodo.isTrasladosExternos(), nodo.isTrasladosInternos(), nodo.isAjustesEntrada(),
            nodo.isAjustesSalida(), nodo.isGrupos(), nodo.isDisenos(), nodo.isArmados(), nodo.isInventarioInicial(), nodo.isTrasladoDetalle(), nodo.isKardex(),
            nodo.isReporteProductos(), nodo.isReportePrestamos(), nodo.isReporteCompras(), nodo.isReporteAjustes(), nodo.isReporteOrdenCompras(), nodo.isReporteInventario(),
            nodo.isReporteTrasladoInterno(), nodo.isReporteIvasCompras(), nodo.isReporteMovimientoSerial(), nodo.isCrearClientes(), nodo.isCrearProveedor(), nodo.isCrearEmpleado(),
            nodo.isBodegasInternas(), nodo.isClientesPrestamos(), nodo.isShowRoom(), nodo.isReporteClientes(), nodo.isReporteProveedor(), nodo.isReporteEmpleados(),
            nodo.isAbonosCxC(), nodo.isAbonosCxP(), nodo.isNotasCredito(), nodo.isReporteCarteraCxC(), nodo.isReporteAbonosCxC(), nodo.isReporteCarteraCxP(),
            nodo.isReporteAbonosCxP(), nodo.isReporteNotasCredito(), nodo.isCodigos(), nodo.isEgresos(), nodo.isBancos(), nodo.isCuadreCaja(), nodo.isReporteEgresos(),
            nodo.isReporteBancos(), nodo.isReporteCuadreCaja(), nodo.isRegistrarAgenda(), nodo.isReportesAgenda(), nodo.isMascotas(), nodo.isHistoriaClinica(),
            nodo.isGuarderia(), nodo.isPeluqueria(), nodo.isHospitalizacion(), nodo.isAlertas(), nodo.isReporteMascotas(), nodo.isReporteGuarderia(),
            nodo.isReportePeluqueria(), nodo.isReporteHospitalizacion(), nodo.isFormatos(), nodo.isOrdenesServicioMedica(), nodo.isHistoria(), nodo.isFormatosMedicas(),
            nodo.isConvenio(), nodo.isNotaEnfermaria(), nodo.isMedicamentos(), nodo.isReporteOrdenes(), nodo.isReporteFormulas(), nodo.isCertificadoEstudiante(),
            nodo.isCertificadoMedico(), nodo.isCertificadoOyS()};
        return vector;
    }

    public Object[] pasarDatosMaestra(ndMaestra nodo) {

        Object[] vector = {"1", nodo.getL1(), nodo.getL2(), nodo.getL4(), nodo.getC1(),
            nodo.getC2(), nodo.getC3(), nodo.getC4(), nodo.getC5(), nodo.getD1(), nodo.getD2(), nodo.getD3(),
            nodo.getD4(), nodo.getD5(), nodo.getD6(), nodo.getD7(), nodo.getPie(), nodo.getLegal(), nodo.getC6(),
            nodo.getC7(), nodo.isRecogida(), nodo.isLector(), nodo.getTituloFactura(), nodo.isPvpSinIva(),
            nodo.isCostoSinIva(), nodo.isVentasPredeterminado(), nodo.isMensajeUtilidad(), nodo.isConsecutivosDiferentes(),
            nodo.isResolucionIgual(), nodo.getDiasAlertaResolucion(), nodo.getAlertaFechaDias(),
            nodo.getAlertaCantidadNumeracion(), nodo.getAlertaPromedioDias(), nodo.getDiasCobrarMora(), nodo.getPorcentajeMora(),
            nodo.isGeneraOrdenMedica(), nodo.isImprimirOrdenMedica(), nodo.isImprimirFacturaOrdenMedica(), nodo.getHoraInicioAgenda(),
            nodo.getHoraFinAgenda(), nodo.getIntervaloAgenda(), nodo.getCodigoPrestadorServicio(), nodo.isImprimirCuadreFiscal(), nodo.isOcultarInformacionCliente(),
            nodo.isVisualizarTodasLasFacturas(), nodo.isMostrarInformacionCuadre(), nodo.getDescuentoMaximoVentas(), nodo.getTipoPrestadorServicio(),
            nodo.getOtros(), nodo.getDomicilios(), nodo.getLimite(), nodo.isCombinarProductos(), nodo.isModificarNombre(),
            nodo.isImpBolsa(), nodo.getValorBolsa(), nodo.isTurno(), nodo.getTurno1(), nodo.getInformacion(), nodo.isConsecutivo(), nodo.isHora(), nodo.isPondNegativo(),
            nodo.getAnexoOrdenServicio(), nodo.isModificarPrecio(), nodo.isBorrarCongelada(),
            nodo.isCopiasFactura(), nodo.isCopiasOServicio(), nodo.isCopiasCotizacion(), nodo.isCopiasPlanSepare(), nodo.isCopiasPedido(), nodo.isPrevisualizarFactura(),
            nodo.isPrevisualizarOServicio(), nodo.isPrevisualizarCotizacion(), nodo.isPrevisualizarPlanSepare(), nodo.isPrevisualizarPedido(), nodo.getNumFactura(), nodo.getNumOServicio(),
            nodo.getNumCotizacion(), nodo.getNumPlanSepare(), nodo.getNumPedido(), nodo.isPagosTerceros(), nodo.isFacturarSeparado(), nodo.isReimpresion(), nodo.getImpresoraPos(),
            nodo.getImpresoraMediaCarta(), nodo.getImpresoraCarta(), nodo.isMostrarImpoconsumo(), nodo.isMostrarRetenciones(), nodo.getImprimirCada(),
            nodo.getCantidadEstablecida(), nodo.getFilas(), nodo.getColumnas(), nodo.getPorcPropina(), nodo.isMostrarDevuelta(), nodo.getInicioHosp(),
            nodo.getFinHosp(), nodo.getIntervalosHosp(), nodo.isDiasAutomaticos(), nodo.isCiudadBuscador(), nodo.getFoco(),
            nodo.isPvpImpoconsumo(), nodo.isCostoImpoconsumo(), nodo.isImpresionPorGrupo(), nodo.getCopiasComanda(), nodo.getCopiasPrefactura(), nodo.isPrevisualizarComanda(),
            nodo.isPrevisualizarPrefactura(), nodo.getImpresoraComanda(), nodo.getImpresoraPrefactura(), nodo.isFacturarMesas(), nodo.isSoloMesas(), nodo.isBorrarMesas(),
            nodo.getIdCliente(), nodo.getPassword(), nodo.getNumFacturaIncremento(), nodo.getResponsabilidadesFiscales(), nodo.getGmail(), nodo.getPasswordGmail()
        };
        return vector;
    }

    public Object[] pasarDatosBodega(ndBodega nodo) {

        Object[] vector = {nodo.getCodigo(), nodo.getNombre(), nodo.getDireccion(), nodo.getTelefono(), nodo.getUsuario(), nodo.getCiudad(), nodo.getAbreviado(), nodo.getFilas(),
            nodo.getColumnas(), nodo.getTipo()};

        return vector;
    }

    public Object[] pasarDatosIngresoEx(ndIngresoEx nodo) {
        Object[] vector = {nodo.getId(), nodo.getProveedor(), nodo.getFechaFactura(), nodo.getFechaVencimiento(), nodo.getTipo(),
            nodo.getConcepto(), nodo.isCredito(), nodo.getCxp(), nodo.getUsuario(), nodo.getTerminal(), nodo.getObservaciones(),
            nodo.getFechaCargue(), nodo.getCantProd(), nodo.getCantCajas(), nodo.getCantUnidades(), nodo.getEstado()};
        return vector;
    }

    public Object[] pasarDatosIngreso(ndIngreso nodo) {
        Object[] vector = {nodo.getId(), nodo.getProveedor(), nodo.getFechaFactura(),
            nodo.getFechaVencimiento(), nodo.getTipo(), nodo.getConcepto(), nodo.isCredito(),
            nodo.getCxp(), nodo.getUsuario(), nodo.getTerminal(), nodo.getObservaciones(), nodo.getHora(),
            nodo.getEstado(), nodo.getBodega(), nodo.getModeloContable()};
        return vector;
    }

    public Object[] pasarValoresIngresoEx(ndIngresoEx nodo) {
        Object[] vector = {nodo.getSubtotal(), nodo.getDescuentos(), nodo.getTotal(), nodo.getFlete(), nodo.getSeguro(), nodo.getOtros()};
        return vector;
    }

    public Object[] pasarValoresIngreso(ndIngreso nodo) {
        Object[] vector = {nodo.getTotal(), nodo.getDescuentos(), nodo.getIva(), nodo.getSubtotal(), nodo.getRtIva(), nodo.getRtFuente(),
            nodo.getPorRtFuente(), nodo.getImpoconsumo(), nodo.getIca(), nodo.getEfectivo(), nodo.getCheque(), nodo.getTarjeta(),
            nodo.getDescFinanciero(), nodo.getDescProntoPago()};
        return vector;
    }

    public Object[] pasarDatosCompra(ndCompra nodo) {
        Object[] vector = {nodo.getIngreso(), nodo.getProducto(), nodo.getCantidad(), nodo.getPorIva(), nodo.getDescripcion(),
            nodo.getPlu(), nodo.getCantidad2(), nodo.getBodega(), nodo.getUsuario(), nodo.getTipoDescuento()};
        return vector;
    }

    public Object[] pasarValoresCompra(ndCompra nodo) {
        Object[] vector = {nodo.getValor(), nodo.getDescuento(), nodo.getTotal(), nodo.getIva(), nodo.getSubtotal(), nodo.getValorPlu(),
            nodo.getImpoconsumo(), nodo.getPorcImpo()};
        return vector;
    }

    public Object[] pasarDatosNC(ndNc nodo) {
        Object[] vector = {nodo.getNc(), nodo.getCliente(), nodo.getFactura(), nodo.isAnulada(), nodo.getUsuario(), nodo.getFecha(),
            nodo.getVendedor(), nodo.getTerminal(), nodo.getResolucion(), nodo.isReembolsar(), nodo.getHora(), nodo.getBodega(), nodo.getObservaciones()};
        return vector;
    }

    public Object[] pasarValoresNC(ndNc nodo) {
        Object[] vector = {nodo.getValor(), nodo.getRestante()};
        return vector;
    }

    public Object[] pasarDatosTraslado(ndTraslado nodo) {
        Object[] vector = {nodo.getId(), nodo.getOrigen(), nodo.getDestino(), nodo.getFecha(), nodo.getUnidades(), nodo.getTipo(),
            nodo.getUsuario(), nodo.getTerminal(), nodo.getHora(), nodo.getBodega(), nodo.getModeloContable()};
        return vector;
    }

    public Object[] pasarValoresTraslado(ndTraslado nodo) {
        Object[] vector = {nodo.getValor()};
        return vector;
    }

    public Object[] pasarDatosTraslado(ndTrasladoBodega nodo) {
        Object[] vector = {nodo.getId(), nodo.getOrigen(), nodo.getDestino(), nodo.getFecha(), nodo.getUnidades(), nodo.getTipo(), nodo.getDevolucion(),
            nodo.getUsuario(), nodo.getTerminal(), nodo.getDiasPlazo(), nodo.getEstado(), nodo.getHora()};
        return vector;
    }

    public Object[] pasarValoresTraslado(ndTrasladoBodega nodo) {
        Object[] vector = {nodo.getValor()};
        return vector;
    }

//    public Object[] pasarDatosTraslado(ndProdTrasl nodo) {
//        Object[] vector = {nodo.getTraslado(), nodo.getProducto(), nodo.getCantidad(), nodo.getObservaciones(), nodo.getDescripcion(),
//            nodo.getPlu(), nodo.getCantidad2(), nodo.getIdProd(), nodo.getImei(), nodo.getTalla(), nodo.getColor()};
//        return vector;
//    }
    public Object[] pasarDatosProductosAjustes(ndProductoAjustes nodo) {
        Object[] vector = {nodo.getTraslado(), nodo.getProducto(), nodo.getCantidad(), nodo.getObservaciones(), nodo.getDescripcion(),
            nodo.getPlu(), nodo.getCantidad2(), nodo.getIdProd(), nodo.getImei(), nodo.getTalla(), nodo.getColor(), nodo.getPorcIva(),
            nodo.getPorcImpo()};
        return vector;
    }

    public Object[] pasarValoresProductosAjustes(ndProductoAjustes nodo) {
        Object[] vector = {nodo.getLista(), nodo.getTotal(), nodo.getSubtotal(), nodo.getIva(), nodo.getCosto(), nodo.getImpoconsumo()};
        return vector;
    }

//    public Object[] pasarValoresTraslado(ndProdTrasl nodo) {
//        Object[] vector = {nodo.getLista(), nodo.getTotal()};
//        return vector;
//    }
    public Object[] pasarDatosTraslado(ndProdTraslBodega nodo) {
        Object[] vector = {nodo.getTraslado(), nodo.getProducto(), nodo.getImeil(), nodo.getObservaciones(), nodo.getLote(),
            nodo.getEstado(), nodo.getFacturados(), nodo.getDevueltos(), nodo.getIdProd(), nodo.getPlu()};
        return vector;
    }

    public Object[] pasarValoresTraslado(ndProdTraslBodega nodo) {
        Object[] vector = {nodo.getCantidad(), nodo.getLista(), nodo.getTotal(), nodo.getCant2(), nodo.getIva()};
        return vector;
    }

    public Object[] pasarDatosEgreso(ndEgreso nodo) {
        Object[] vector = {nodo.getId(), nodo.getNit(), nodo.getRazon(), nodo.getTelefono(), nodo.getDireccion(),
            nodo.getCred1(), nodo.getCred2(), nodo.getDeb1(), nodo.getDeb2(), nodo.getFecha(),
            nodo.getDocumento(), nodo.getUsuario(), nodo.getTerminal(), nodo.getEstado(), nodo.getTipo(), nodo.getIngresoAsociado(),
            nodo.getEgreso(), nodo.getModeloContable()};
        return vector;
    }

    public Object[] pasarValoresEgreso(ndEgreso nodo) {
        Object[] vector = {nodo.getTotal(), nodo.getCheque(), nodo.getBanco(), nodo.getEfectivo(), nodo.getSubtotal(), nodo.getIva()};
        return vector;
    }

    public Object[] pasarDatosEgreso(ModeloDetalleEgreso nodo) {
        Object[] vector = {nodo.getEgreso(), nodo.getCodigo(), nodo.getDescripcion(), nodo.getFactura(), nodo.getCodigoUsuario()};
        return vector;
    }

    public Object[] pasarValoresEgreso(ModeloDetalleEgreso nodo) {
        Object[] vector = {nodo.getValor(), nodo.getSubtotal(), nodo.getIva()};
        return vector;
    }

    public ndEmpleado llenarEmpleado(Object[] vector) {

        ndEmpleado nodo = new ndEmpleado();

        nodo.setId((String) vector[0]);
        nodo.setNombre((String) vector[1]);
        nodo.setTelefono((String) vector[2]);
        nodo.setCelular((String) vector[3]);
        nodo.setDireccion((String) vector[4]);
        nodo.setCiudad((String) vector[5]);
        nodo.setEmail((String) vector[6]);
        nodo.setDepartamento((String) vector[7]);
        nodo.setFecha((String) vector[8]);
        nodo.setSalario(String.valueOf(vector[9]));
        nodo.setCargo((String) vector[10]);
        nodo.setUsuario((String) vector[11]);

        try {
            nodo.setActivo((boolean) vector[12]);
        } catch (NullPointerException e) {
            nodo.setActivo(false);
        }

        nodo.setIng((String) vector[13]);
        nodo.setRet((String) vector[14]);

        try {
            nodo.setAgendar((boolean) vector[15]);
        } catch (NullPointerException e) {
            nodo.setAgendar(false);
        }

        nodo.setUsuarioAsociado((String) vector[16]);
        nodo.setEps((String) vector[17]);
        nodo.setArl((String) vector[18]);
        nodo.setCcf((String) vector[19]);
        nodo.setFechaCreacion((String) vector[20]);
        return nodo;
    }

    public Object[] pasarDatosEmpleado(ndEmpleado nodo) {

        Object[] vector = {nodo.getId(), nodo.getNombre(), nodo.getTelefono(), nodo.getCelular(), nodo.getDireccion(), nodo.getCiudad(), nodo.getEmail(),
            nodo.getDepartamento(), nodo.getFecha(), nodo.getCargo(), nodo.getUsuario(), nodo.isActivo(), nodo.getIng(), nodo.getRet(), nodo.isAgendar(),
            nodo.getUsuarioAsociado(), nodo.getEps(), nodo.getArl(), nodo.getCcf(), nodo.getFechaCreacion()
        };

        return vector;
    }

    public Object[] pasarValoresEmpleado(ndEmpleado nodo) {

        Object[] vector = {nodo.getSalario()};

        return vector;
    }

// ---------------------------------------------------MENSAJES-----------------------------------------------------------------------------------------------
// ---------------------------------------------------MENSAJES-----------------------------------------------------------------------------------------------
    public void msgExito(JInternalFrame ventana, String mensaje) {
        try {
            if (Instancias.getInstancias().getMenu().getSeVeElMenu()) {
                Instancias.getInstancias().getMenu().activarMensaje("Exito", mensaje);
            } else {
                msgExito ventanaError = new msgExito(null, true, mensaje);
                ventanaError.setVisible(true);
            }
        } catch (Exception e) {
            msgExito ventanaError = new msgExito(null, true, mensaje);
            ventanaError.setVisible(true);
        }
    }

    public void msgError(JInternalFrame ventana, String mensaje) {
        try {
            if (Instancias.getInstancias().getMenu().getSeVeElMenu()) {
                Instancias.getInstancias().getMenu().activarMensaje("Error", mensaje);
            } else {
                msgError ventanaError = new msgError(null, true, mensaje);
                ventanaError.setVisible(true);
            }
        } catch (Exception e) {
            msgError ventanaError = new msgError(null, true, mensaje);
            ventanaError.setVisible(true);
        }
    }

    public void msgAdvertencia(JInternalFrame ventana, String mensaje) {
        try {
            if (Instancias.getInstancias().getMenu().getSeVeElMenu()) {
                Instancias.getInstancias().getMenu().activarMensaje("Atencion", mensaje);
            } else {
                msgAtencion ventanaAtencion = new msgAtencion(null, true, mensaje);
                ventanaAtencion.setVisible(true);
            }
        } catch (Exception e) {
            msgAtencion ventanaAtencion = new msgAtencion(null, true, mensaje);
            ventanaAtencion.setVisible(true);
        }
    }

    public void msgAdvertenciaAjustado(JInternalFrame ventana, String mensaje) {
        try {
            if (Instancias.getInstancias().getMenu().getSeVeElMenu()) {
                Instancias.getInstancias().getMenu().activarMensaje("Atencion", mensaje);
            } else {
                msgAtencion1 ventanaAtencion = new msgAtencion1(null, true, mensaje);
                ventanaAtencion.setVisible(true);
            }
        } catch (Exception e) {
            msgAtencion ventanaAtencion = new msgAtencion(null, true, mensaje);
            ventanaAtencion.setVisible(true);
        }
    }

    public Integer msgPregunta(JInternalFrame ventana, String mensaje) {
        msgPregunta ventanaPregunta = new msgPregunta(null, true, mensaje);
        ventanaPregunta.setVisible(true);
        int tipo = ventanaPregunta.seleccionar();
        return tipo;
    }

    public String msgIngresar(JInternalFrame ventana, String mensaje) {
        msgIngresar ventanaIngresar = new msgIngresar(null, true, mensaje);
        ventanaIngresar.setVisible(true);
        String tipo = ventanaIngresar.seleccionar();
        return tipo;
    }

    public String msgIngresarEnter(JInternalFrame ventana, String mensaje) {
        msgIngresar1 ventanaIngresar = new msgIngresar1(null, true, mensaje);
        ventanaIngresar.setVisible(true);
        String tipo = ventanaIngresar.seleccionar();
        return tipo;
    }

// ---------------------------------------------------CAMPOS-----------------------------------------------------------------------------------------------
// ---------------------------------------------------CAMPOS-----------------------------------------------------------------------------------------------
    public String campoVacio(JTextField campo) {
        if (campo.getText().equals("")) {
            return campo.getName() + ", ";
        }
        return "";
    }

    public String comboVacio(JComboBox combo) {
        if (combo.getSelectedIndex() <= 0) {
            return combo.getName() + ", ";
        }
        return "";
    }

    public String areaVacio(JTextArea campo) {
        if (campo.getText().equals("")) {
            return campo.getName() + ", ";
        }
        return "";
    }

    public String dateChooserVacio(DateChooserCombo campo) {
        if (campo.getText().equals("")) {
            return campo.getAccessibleContext().getAccessibleName() + ", ";
        }
        return "";
    }

    public String jdateChooserVacio(JDateChooser campo) {
        if (campo.getCalendar().equals(null)) {
            return campo.getAccessibleContext().getAccessibleName() + ", ";
        }
        return "";
    }

    public String camposVacios(Object[] campos) {
        String cadena = "";

        for (Object campo : campos) {

            if (campo instanceof JTextField) {
                cadena += campoVacio((JTextField) campo);
            } else if (campo instanceof JTextArea) {
                cadena += areaVacio((JTextArea) campo);
            } else if (campo instanceof JComboBox) {
                cadena += comboVacio((JComboBox) campo);
            } else if (campo instanceof DateChooserCombo) {
                cadena += dateChooserVacio((DateChooserCombo) campo);
            } else if (campo instanceof JDateChooser) {
                cadena += jdateChooserVacio((JDateChooser) campo);
            }
        }

        if (!cadena.equals("")) {
            cadena = cadena.substring(0, cadena.length() - 2);
        }

        return cadena;
    }

    public void limpiarCampos(JTextField[] textos, JComboBox[] combos) {

        for (JTextField campo : textos) {
            campo.setText("");
        }

        for (JComboBox campo : combos) {
            campo.setSelectedIndex(0);
        }
    }

// -----------------------------------------------------RECURSOS---------------------------------------------------------------  
// -----------------------------------------------------RECURSOS---------------------------------------------------------------   
    public static String fecha() {
        Calendar fecha = Calendar.getInstance();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        formato.setCalendar(fecha);
        String resultado = formato.format(fecha.getTime());
        return resultado;
    }

    public static String dia() {
        Calendar fecha = Calendar.getInstance();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        formato.setCalendar(fecha);
        String resultado = (formato.format(fecha.getTime())).substring(0, 2);

        return resultado;
    }

    public static String mes() {
        Calendar fecha = Calendar.getInstance();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        formato.setCalendar(fecha);
        String resultado = (formato.format(fecha.getTime())).substring(3, 5);

        return resultado;
    }

    public String fechaEnLetras(String dato) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date dateObj;
        String resultado = "";

        try {
            dateObj = formato.parse(dato.substring(0, 10).replace('-', '/'));
            Calendar fecha = Calendar.getInstance();
            SimpleDateFormat formato2 = new SimpleDateFormat("EEEE', 'dd 'de' MMMM 'del' yyyy");
            fecha.setTime(dateObj);
            resultado = formato2.format(fecha.getTime());
        } catch (Exception ex) {
            return "";
        }
        return resultado;
    }

    public static String mesEnPalabra() {

        String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        Calendar fecha = Calendar.getInstance();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        formato.setCalendar(fecha);
        String resultado = (formato.format(fecha.getTime())).substring(3, 5);

        return meses[Integer.parseInt(resultado) - 1];
    }

    public static String anho() {
        Calendar fecha = Calendar.getInstance();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        formato.setCalendar(fecha);
        String resultado = (formato.format(fecha.getTime())).substring(6);

        return resultado;
    }

    public static String hora() {
        Calendar calendario = Calendar.getInstance();

        int hora, minutos, segundos;

        hora = calendario.get(Calendar.HOUR_OF_DAY);
        minutos = calendario.get(Calendar.MINUTE);
        segundos = calendario.get(Calendar.SECOND);

        return hora + ":" + minutos;
    }

    public String fechaConsulta(String dato) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date dateObj;
        String resultado = "";

        try {
            dateObj = formato.parse(dato.substring(0, 10).replace('-', '/'));
            Calendar fecha = Calendar.getInstance();
            SimpleDateFormat formato2 = new SimpleDateFormat("yyyy-MM-dd");
            fecha.setTime(dateObj);
            resultado = formato2.format(fecha.getTime());
        } catch (Exception ex) {
            return "";
        }
        return resultado;
    }

    public String fecha(String dato) {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
        Date dateObj;
        String resultado = "";

        try {
            dateObj = formato.parse(dato.substring(0, 10).replace('-', '/'));
            Calendar fecha = Calendar.getInstance();
            SimpleDateFormat formato2 = new SimpleDateFormat("dd/MM/yyyy");
            fecha.setTime(dateObj);
            resultado = formato2.format(fecha.getTime());
        } catch (Exception ex) {
            return "";
        }
        return resultado;
    }

    public String fecha2(String dato) {

        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        Date dateObj;
        String resultado = "";

        try {
            dateObj = formato.parse(dato.substring(0, 10).replace('/', '-'));
            Calendar fecha = Calendar.getInstance();
            SimpleDateFormat formato2 = new SimpleDateFormat("yyyy/MM/dd");
            fecha.setTime(dateObj);
            resultado = formato2.format(fecha.getTime());
        } catch (Exception ex) {
            return "";
        }

        return resultado;
    }

    public String fecha3(String dato) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date dateObj;
        String resultado = "";

        try {
            dateObj = formato.parse(dato.substring(0, 10).replace('-', '/'));
            Calendar fecha = Calendar.getInstance();
            SimpleDateFormat formato2 = new SimpleDateFormat("yyyy/MM/dd");
            fecha.setTime(dateObj);
            resultado = formato2.format(fecha.getTime());
        } catch (Exception ex) {
            return "";
        }
        return resultado;
    }

    public String fecha4(String dato) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date dateObj;
        String resultado = "";
        try {
            dateObj = formato.parse(dato.substring(0, 10).replace('-', '/'));
            Calendar fecha = Calendar.getInstance();
            SimpleDateFormat formato2 = new SimpleDateFormat("yyyy-MM-dd");
            fecha.setTime(dateObj);
            resultado = formato2.format(fecha.getTime());
        } catch (Exception ex) {
            return "";
        }
        return resultado;
    }

    public String desdeDate(Calendar date) {
        String sDia = "", sMes = "", fecha = "";
        int sAnho;

        Calendar cal = date;
        int dia = cal.get(Calendar.DAY_OF_MONTH);
        int mes = cal.get(Calendar.MONTH) + 1;

        if (dia < 10) {
            sDia = "0";
            sDia += dia;
        } else {
            sDia = "" + dia;
        }
        if (mes < 10) {
            sMes = "0";
        }

        sMes += mes;
        sAnho = cal.get(Calendar.YEAR);
        fecha = sAnho + "-" + sMes + "-" + sDia;

        return fecha;
    }

    public String desdeDate2(Calendar date) {
        String sDia = "", sMes = "", fecha = "";
        int sAnho;

        Calendar cal = date;
        int dia = cal.get(Calendar.DAY_OF_MONTH);
        int mes = cal.get(Calendar.MONTH) + 1;

        if (dia < 10) {
            sDia = "0";
            sDia += dia;
        } else {
            sDia = "" + dia;
        }
        if (mes < 10) {
            sMes = "0";
        }

        sMes += mes;
        sAnho = cal.get(Calendar.YEAR);
        fecha = sDia + "/" + sMes + "/" + sAnho;

        return fecha;
    }

    public String sumarFecha(String fechaActual, int dias) {

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date dateObj;
        try {
            dateObj = formato.parse(fechaActual);
            Calendar fecha = Calendar.getInstance();
            fecha.setTime(dateObj);
            fecha.add(Calendar.DATE, dias);
            String resultado = formato.format(fecha.getTime());
            return resultado;

        } catch (ParseException ex) {
            Logger.getLogger(metodosGenerales.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return "error fecha";
    }

    public String sumarMeses(String fechaActual, int meses) {

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date dateObj;
        try {
            dateObj = formato.parse(fechaActual);
            Calendar fecha = Calendar.getInstance();
            fecha.setTime(dateObj);
            fecha.add(Calendar.MONTH, meses);
            String resultado = formato.format(fecha.getTime());
            return resultado;

        } catch (ParseException ex) {
            Logger.getLogger(metodosGenerales.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return "error fecha";
    }

    public long restarFecha(String fechaUno, String fechaDos) {

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date f1, f2;
        try {
            f1 = formato.parse(fechaUno);
            Calendar fecha = Calendar.getInstance();
            fecha.setTime(f1);

            f2 = formato.parse(fechaDos);
            Calendar fecha2 = Calendar.getInstance();
            fecha2.setTime(f2);

            return (fecha2.getTimeInMillis() - fecha.getTimeInMillis()) / (1000 * 60 * 60 * 24);

        } catch (ParseException ex) {
            Logger.getLogger(metodosGenerales.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return 0;
    }

    public String formatoFecha(String dato) {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
        Date dateObj;
        String resultado = "";

        try {
            dateObj = formato.parse(dato.substring(0, 10).replace('-', '/'));
            Calendar fecha = Calendar.getInstance();
            SimpleDateFormat formato2 = new SimpleDateFormat("dd/MM/yyyy");
            fecha.setTime(dateObj);
            resultado = formato2.format(fecha.getTime());
        } catch (Exception ex) {
            return "";
        }

        return resultado;
    }

    /* public String modificarPermisos(String codigo, String regimen, String impoconsumo, String dispositivoUSB, String ordenesServicio, String tipoOrden,
            String creditos, String planSepare, String pedidos, String tipoImpresion, String invBodega, String productosSerial,
            String embarcaciones, String congeladas, String medico, String veterinaria, String parqueadero, String agenda,
            String restaurante, String recordatorios, String laboratorio, String oftalmologia, String facturaLote, String PTM,
            String periodoPrueba, String diasFinalizacionPrueba, String numeroFacturas, String diasDespuesPrueba,
            String info, String actualizarPermisos, String facturacionElectronica, String contable, String pais) {

        String concat = "modificarUsuario/-/" + codigo + "/-/" + regimen + "/-/" + impoconsumo + "/-/" + dispositivoUSB + "/-/" + ordenesServicio
                + "/-/" + tipoOrden + "/-/" + creditos + "/-/" + planSepare + "/-/" + pedidos + "/-/" + tipoImpresion + "/-/" + invBodega
                + "/-/" + productosSerial + "/-/" + embarcaciones + "/-/" + congeladas + "/-/" + medico + "/-/" + veterinaria + "/-/" + parqueadero
                + "/-/" + agenda + "/-/" + restaurante + "/-/" + recordatorios + "/-/" + laboratorio + "/-/" + oftalmologia + "/-/" + facturaLote
                + "/-/" + PTM + "/-/" + periodoPrueba + "/-/" + diasFinalizacionPrueba + "/-/" + numeroFacturas + "/-/" + diasDespuesPrueba + "/-/" + info
                + "/-/" + actualizarPermisos + "/-/" + facturacionElectronica + "/-/" + contable + "/-/" + pais;

        String resultadoFinal = "";
        try {
            //Url de pruebas
//            String postUrl = "https://ujvgudsfe4.execute-api.us-east-2.amazonaws.com/dev/testbd";

            //Url de produccion
            String postUrl = "https://ujvgudsfe4.execute-api.us-east-2.amazonaws.com/prod/suspen-prod";
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpPost post = new HttpPost(postUrl);
            StringEntity postingString = new StringEntity("\"" + concat + "\"");
            post.setEntity(postingString);
            post.addHeader(HTTP.CONTENT_TYPE, "application/json");
            HttpResponse response = httpClient.execute(post);
            InputStream resultado = response.getEntity().getContent();
            resultadoFinal = IOUtils.toString(resultado);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(metodosGenerales.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(metodosGenerales.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultadoFinal;
    }*/

 /*  public String consultarEstado(String codigo) {
        String resultadoFinal = "";
        try {
            //Url de pruebas
//            String postUrl = "https://ujvgudsfe4.execute-api.us-east-2.amazonaws.com/dev/testbd";

            //Url de produccion
            String postUrl = "https://ujvgudsfe4.execute-api.us-east-2.amazonaws.com/prod/suspen-prod";
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpPost post = new HttpPost(postUrl);

            StringEntity postingString = new StringEntity("\"" + codigo + "\"");
            post.setEntity(postingString);
            post.addHeader(HTTP.CONTENT_TYPE, "application/json");

            HttpResponse response = httpClient.execute(post);
            InputStream resultado = response.getEntity().getContent();
            resultadoFinal = IOUtils.toString(resultado);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(metodosGenerales.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(metodosGenerales.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultadoFinal;
    }*/
    public static String EncriptarValidacionUsuario(String valor, String keyPrivate) throws Exception {
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

    public static String DesencriptarFechaBloqueo(String textoEncriptado) throws Exception {
        String secretKey = "yI4z%jIMndKd3N%bj#%f"; //llave para desenciptar datos
        String base64EncryptedString = "";

        try {
            byte[] message = Base64.decodeBase64(textoEncriptado.getBytes("utf-8"));
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
            SecretKey key = new SecretKeySpec(keyBytes, "DESede");

            Cipher decipher = Cipher.getInstance("DESede");
            decipher.init(Cipher.DECRYPT_MODE, key);

            byte[] plainText = decipher.doFinal(message);
            base64EncryptedString = new String(plainText, "UTF-8");

        } catch (Exception ex) {
        }
        return base64EncryptedString;
    }

    public static String encriptarFechaBloqueo(String texto) {
        String secretKey = "yI4z%jIMndKd3N%bj#%f"; //llave para encriptar datos
        String base64EncryptedString = "";

        try {

            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);

            SecretKey key = new SecretKeySpec(keyBytes, "DESede");
            Cipher cipher = Cipher.getInstance("DESede");
            cipher.init(Cipher.ENCRYPT_MODE, key);

            byte[] plainTextBytes = texto.getBytes("utf-8");
            byte[] buf = cipher.doFinal(plainTextBytes);
            byte[] base64Bytes = Base64.encodeBase64(buf);
            base64EncryptedString = new String(base64Bytes);

        } catch (Exception ex) {
        }
        return base64EncryptedString;
    }

    public String sentenciaImpresionFactura(String tipo, String condicion) {
        boolean agrupar = "agrupada".equals(tipo);

        // Campos que varían según agrupación
        String cantidad    = agrupar ? "SUM(bdfactura.cantidad)"    : "bdfactura.cantidad";
        String descuento   = agrupar ? "SUM(bdfactura.descuento)"   : "bdfactura.descuento";
        String total       = agrupar ? "SUM(bdfactura.total)"       : "bdfactura.total";
        String iva         = agrupar ? "SUM(bdfactura.iva)"         : "bdfactura.iva";
        String subtotal    = agrupar ? "sum(bdfactura.subtotal)"    : "bdfactura.subtotal";
        String utilidad    = agrupar ? "SUM(bdfactura.utilidad)"    : "bdfactura.utilidad";
        String cant2       = agrupar ? "SUM(bdfactura.cant2)"       : "bdfactura.cant2";
        String impoconsumo = agrupar ? "SUM(bdfactura.impoconsumo)" : "bdfactura.impoconsumo";

        String selectFijo = "SELECT bdfactura.idFactura AS idFactura, CAST(SUBSTR(bdfactura.idFactura,6,100) AS SIGNED) AS ordenId, "
                + "CAST(SUBSTR(bdterceros.id,1,100) AS SIGNED) AS cliente, bdterceros.ciudad AS ciudad, bdfactura.vendedor AS vendedor, "
                + "bdfactura.red AS red, bdfactura.fechaFactura AS fechaFactura, bdfactura.fechaVencimiento AS fechaVencimiento, "
                + "bdfactura.efectivoGeneral AS efectivoGeneral, bdfactura.ncGeneral AS ncGeneral, "
                + "bdfactura.chequeGeneral AS chequeGeneral, bdfactura.targetaGeneral AS targetaGeneral, "
                + "bdfactura.totalGeneral AS totalGeneral, bdfactura.descuentoGeneral AS descuentoGeneral, "
                + "bdfactura.ivaGeneral AS ivaGeneral, bdfactura.subtotalGeneral AS subtotalGeneral, "
                + "bdfactura.comprobante AS comprobante, bdfactura.cotizacion AS cotizacion, bdfactura.anulada AS anulada, "
                + "bdfactura.anula AS anula, bdfactura.credito AS credito, bdfactura.cxc AS cxc, bdfactura.usuario AS usuario, "
                + "bdfactura.rtIva AS rtIva, bdfactura.rtIca AS rtIca, bdfactura.rtFuente AS rtFuente, "
                + "bdfactura.otros AS otros, bdfactura.observacion AS observacion, bdfactura.anulada1 AS anulada1, "
                + "bdfactura.anula1 AS anula1, bdfactura.credito1 AS credito1, bdfactura.cxc1 AS cxc1, "
                + "bdfactura.usuario1 AS usuario1, bdfactura.fechaAlerta AS fechaAlerta, bdfactura.terminal AS terminal, "
                + "bdfactura.estadoGeneral AS estadoGeneral, bdfactura.estado2 AS estado2, "
                + "bdfactura.devuelta AS devuelta, bdfactura.factura AS factura, bdfactura.resolucion AS resolucion, "
                + "bdfactura.fechaAnulacion AS fechaAnulacion, bdfactura.cuadreAnulacion AS cuadreAnulacion, "
                + "bdfactura.usuarioAnula AS usuarioAnula, bdfactura.copago AS copago, bdfactura.garantia AS garantia, "
                + "bdfactura.diasGarantia AS diasGarantia, bdfactura.rango AS rango, bdfactura.terminos AS terminos, "
                + "bdfactura.notaAnulacion AS notaAnulacion, bdfactura.conseMesa AS conseMesa, "
                + "bdfactura.producto AS producto, bdfactura.lista AS lista, ";

        String selectAgregados = cantidad + " AS cantidad, " + descuento + " AS descuento, "
                + total + " AS total, " + iva + " AS iva, " + subtotal + " AS subtotal, "
                + "bdfactura.NC AS NC, " + utilidad + " AS utilidad, "
                + "bdfactura.concepto AS concepto, bdfactura.porcDescuento AS porcDescuento, "
                + "bdfactura.descripcion AS descripcion, bdfactura.plu AS plu, "
                + cant2 + " AS cant2, "
                + "bdfactura.estado AS estado, bdfactura.porcIva AS porcIva, "
                + "bdfactura.tercero AS tercero, bdfactura.utilidad1 AS utilidad1, ";

        String selectComun = "bdterceros.nombre AS nombre, bdterceros.telefono AS telefono, bdterceros.direccion AS direccion, "
                + "IF(ISNULL(bdprestamo.cuotaInicial),0,bdprestamo.cuotaInicial) AS cuotaInicial2, "
                + "bdfactura.factura AS id2, bdproductos.ubicacion1 AS ubicacion1, bdproductos.referencia AS referencia, "
                + "(bdfactura.subtotalGeneral - bdfactura.descuentoGeneral) AS valor3, "
                + "IF((bdfactura.producto = 'PROD-000000032'),bdfactura.impuesto,'0') AS impuesto, bdcxc.plazo AS plazo, "
                + "bdterceros.nombreContacto AS nombreContacto, bdterceros.cargo AS cargo, bdfactura.turno AS turno, "
                + "bdproductos.grupo AS Grupo, bdterceros.eps AS eps, bdepsprecargados.nombre AS nombreEps, "
                + "bdfactura.Id AS Id, bdfactura.placa AS placa, bdplacas.tipo AS tipo, "
                + "bdparqueaderoautos.fIngreso AS fIngreso, bdparqueaderoautos.fSalida AS fSalida, "
                + "bdparqueaderoautos.hIngreso AS hIngreso, bdparqueaderoautos.hSalida AS hSalida, "
                + "bdparqueaderoautos.horas AS horas, bdfactura.imei AS imei, bddetalleproductos.color AS color, "
                + "bdterceros.idSistema AS idSistema, "
                + impoconsumo + " AS impoconsumo, "
                + "bdproductos.Codigo AS Codigo, bdfactura.totalPropina AS totalPropina, bdfactura.hora AS hora, "
                + "bdfactura.idProd AS idProd, bdgrupo.nombre AS nombreGrupo, "
                + "bdfactura.impoGeneral AS impoGeneral, bdfactura.porcImpo AS porcImpo, bdfactura.bodega AS bodega ";

        String fromJoins = "FROM ((((((((bdfactura LEFT JOIN bdprestamo ON ((bdfactura.idFactura = bdprestamo.factura))) "
                + "LEFT JOIN bdcxc ON ((bdfactura.factura = bdcxc.factura2))) "
                + "LEFT JOIN bdplacas ON ((bdfactura.placa = bdplacas.placa))) "
                + "LEFT JOIN bdparqueaderoautos ON ((bdparqueaderoautos.factura = bdfactura.factura))) "
                + "LEFT JOIN bddetalleproductos ON ((bdfactura.idProd = bddetalleproductos.Id))) "
                + "LEFT JOIN bdproductos ON ((bdfactura.producto = bdproductos.idSistema))) "
                + "LEFT JOIN (bdterceros LEFT JOIN bdepsprecargados ON ((bdepsprecargados.Id = bdterceros.eps))) "
                + "ON ((bdfactura.cliente = bdterceros.idSistema))) "
                + "LEFT JOIN bdgrupo ON ((bdproductos.grupo = bdgrupo.codigo))) ";

        String groupBy = agrupar
                ? "AND (bdcxc.tipo = 'FACT' OR bdcxc.tipo IS NULL) "
                  + "GROUP BY bdfactura.factura, bdfactura.producto, bdfactura.lista, "
                  + "bdfactura.descuento, bdfactura.plu, bdfactura.porcIva, bdfactura.porcImpo"
                : "GROUP BY bdfactura.idFactura,CAST(SUBSTR(bdfactura.idFactura,6,100)AS SIGNED),bdterceros.id,"
                  + "bdterceros.ciudad,bdfactura.vendedor,bdfactura.red,bdfactura.fechaFactura,bdfactura.fechaVencimiento,"
                  + "bdfactura.efectivoGeneral,bdfactura.ncGeneral,bdfactura.chequeGeneral,bdfactura.targetaGeneral,"
                  + "bdfactura.totalGeneral,bdfactura.descuentoGeneral,bdfactura.ivaGeneral,bdfactura.subtotalGeneral,"
                  + "bdfactura.comprobante,bdfactura.cotizacion,bdfactura.anulada,bdfactura.anula,bdfactura.credito,"
                  + "bdfactura.cxc,bdfactura.usuario,bdfactura.rtIva,bdfactura.rtIca,bdfactura.rtFuente,"
                  + "bdfactura.otros,bdfactura.anulada1,bdfactura.anula1,bdfactura.credito1,bdfactura.cxc1,"
                  + "bdfactura.usuario1,bdfactura.fechaAlerta,bdfactura.terminal,bdfactura.estadoGeneral,bdfactura.estado2,"
                  + "bdfactura.devuelta,bdfactura.resolucion,bdfactura.fechaAnulacion,bdfactura.cuadreAnulacion,"
                  + "bdfactura.usuarioAnula,bdfactura.copago,bdfactura.garantia,bdfactura.diasGarantia,bdfactura.rango,"
                  + "bdfactura.conseMesa,bdfactura.producto,bdfactura.lista,bdfactura.cantidad,bdfactura.descuento,"
                  + "bdfactura.total,bdfactura.iva,bdfactura.subtotal,bdfactura.NC,bdfactura.utilidad,"
                  + "bdfactura.porcDescuento,bdfactura.descripcion,bdfactura.plu,bdfactura.cant2,bdfactura.estado,"
                  + "bdfactura.porcIva,bdfactura.tercero,bdfactura.utilidad1,bdterceros.nombre,bdterceros.telefono,"
                  + "bdterceros.direccion,IF(ISNULL(bdprestamo.cuotaInicial),0,bdprestamo.cuotaInicial),"
                  + "bdfactura.factura,bdproductos.ubicacion1,bdproductos.referencia,"
                  + "(bdfactura.subtotalGeneral - bdfactura.descuentoGeneral),"
                  + "IF((bdfactura.producto = 'PROD-000000032'),bdfactura.impuesto,'0'),"
                  + "bdterceros.nombreContacto,bdterceros.cargo,bdfactura.turno,bdproductos.grupo,bdterceros.eps,"
                  + "bdepsprecargados.nombre,bdfactura.Id,bdfactura.placa,bdplacas.tipo,bdparqueaderoautos.fIngreso,"
                  + "bdparqueaderoautos.fSalida,bdparqueaderoautos.hIngreso,bdparqueaderoautos.hSalida,"
                  + "bdparqueaderoautos.horas,bdfactura.imei,bddetalleproductos.color,bdterceros.idSistema,"
                  + "bdfactura.impoconsumo,bdproductos.Codigo,bdgrupo.nombre,bdfactura.impoGeneral,"
                  + "bdfactura.porcImpo,bdfactura.bodega ORDER BY bdfactura.Id";

        return selectFijo + selectAgregados + selectComun + fromJoins + condicion + groupBy;
    }

    public boolean soloNum(KeyEvent evt) {

        if (!Character.isDigit(evt.getKeyChar()) && !Character.toString(evt.getKeyChar()).equals(",") && !Character.toString(evt.getKeyChar()).equals(".")) {
            evt.consume();
            return false;
        }

        return true;
    }

    public void ponerIcono(String imag, JLabel etiq) {
        try {
            Image fot = new ImageIcon(getClass().getResource(imag)).getImage();

            //ImageIcon icono = new ImageIcon(fot.getScaledInstance(etiq.getWidth(), etiq.getHeight(), Image.SCALE_DEFAULT));
            ImageIcon icono = new ImageIcon(fot);
            etiq.setIcon(icono);
        } catch (NullPointerException e) {
            etiq.setIcon(null);
        }
    }

    public static void presionarBoton(JButton campo) {
        Robot robot;

        try {
            campo.requestFocus();
            robot = new Robot();
            robot.delay(200);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        } catch (AWTException ex) {
            Logger.getLogger(VistaBuscadorProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void crearArchivo(String xml, String fact) {
        File directorio = new File(System.getProperty("user.home") + "/Desktop/FacturacionEletronica");
        if (!directorio.exists()) {
            directorio.mkdir();
        }

        String aux = "";
        try {
            File archivo = new File(System.getProperty("user.home") + "/Desktop/FacturacionEletronica/" + fact + ".xml");
            FileWriter escribir = new FileWriter(archivo, true);
            escribir.write(xml);
            escribir.close();
        } catch (Exception e) {
            System.out.println("Error al escribir " + e);
        }
    }

    public static void presionarEnter(JTextField campo) {
        Robot robot;
        try {
            campo.requestFocus();
            robot = new Robot();
            robot.delay(200);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        } catch (AWTException ex) {
            Logger.getLogger(VistaBuscadorProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Calendar haciaDate(String dato) {

        SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
        Date dateObj;

        try {
            dateObj = formato.parse(dato.substring(0, 10).replace('-', '/'));
            Calendar fecha = Calendar.getInstance();
            fecha.setTime(dateObj);
            return fecha;
        } catch (Exception ex) {
            return null;
        }
    }

    public Calendar haciaDate2(String dato) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date dateObj;

        try {
            dateObj = formato.parse(dato.substring(0, 10).replace('-', '/'));
            Calendar fecha = Calendar.getInstance();
            fecha.setTime(dateObj);
            return fecha;
        } catch (Exception ex) {
            return null;
        }
    }

    public static String convertToMultiline(String orig) {
        return "<html><center>" + orig.replaceAll("\\n", "<br />") + "</center></html>";
    }

    public static boolean toBoolean(String x) {
        return x.equals("1");
    }

    public static boolean emailEsCorrecto(String email) {
        String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        // Compiles the given regular expression into a pattern.
        Pattern pattern = Pattern.compile(PATTERN_EMAIL);

        // Match the given input against this pattern
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static Integer calcularEdad(String fecha) {
        Date fechaNac = null;
        try {
            /**
             * Se puede cambiar la mascara por el formato de la fecha que se
             * quiera recibir, por ejemplo año mes día "yyyy-MM-dd" en este caso
             * es día mes año
             */
            fecha = fecha.replace("/", "-");
            fechaNac = new SimpleDateFormat("dd-MM-yyyy").parse(fecha);
        } catch (Exception ex) {
            System.out.println("Error:" + ex);
        }

        Calendar fechaNacimiento = Calendar.getInstance();
        //Se crea un objeto con la fecha actual
        Calendar fechaActual = Calendar.getInstance();
        //Se asigna la fecha recibida a la fecha de nacimiento.
        fechaNacimiento.setTime(fechaNac);
        //Se restan la fecha actual y la fecha de nacimiento
        int anho = fechaActual.get(Calendar.YEAR) - fechaNacimiento.get(Calendar.YEAR);
        int mes = fechaActual.get(Calendar.MONTH) - fechaNacimiento.get(Calendar.MONTH);
        int dia = fechaActual.get(Calendar.DATE) - fechaNacimiento.get(Calendar.DATE);
        //Se ajusta el año dependiendo el mes y el día
        if (mes < 0 || (mes == 0 && dia < 0)) {
            anho--;
        }
        //Regresa la edad en base a la fecha de nacimiento       
        return anho;
    }

    public static String calcularEdad2(String fecha) {
        Date fechaNac = null;
        try {
            /**
             * Se puede cambiar la mascara por el formato de la fecha que se
             * quiera recibir, por ejemplo año mes día "yyyy-MM-dd" en este caso
             * es día mes año
             */
            fecha = fecha.replace("/", "-");
            fechaNac = new SimpleDateFormat("dd-MM-yyyy").parse(fecha);
        } catch (Exception ex) {
            System.out.println("Error:" + ex);
        }
        Calendar fechaNacimiento = Calendar.getInstance();
        //Se crea un objeto con la fecha actual
        Calendar fechaActual = Calendar.getInstance();
        //Se asigna la fecha recibida a la fecha de nacimiento.
        fechaNacimiento.setTime(fechaNac);
        //Se restan la fecha actual y la fecha de nacimiento
        int anho = fechaActual.get(Calendar.YEAR) - fechaNacimiento.get(Calendar.YEAR);
        int mes = fechaActual.get(Calendar.MONTH) - fechaNacimiento.get(Calendar.MONTH);
        int dia = fechaActual.get(Calendar.DATE) - fechaNacimiento.get(Calendar.DATE);
        //Se ajusta el año dependiendo el mes y el día
        if (mes < 0 || (mes == 0 && dia < 0)) {
            anho--;
        }
        //Regresa la edad en base a la fecha de nacimiento       

        if (anho == 0) {
            if (mes >= 1) {
                return mes + "-Meses";
            } else {
                return dia + "-Días";
            }
        }
        return anho + "-Años";
    }

    public static Integer calcularEdadMeses(String fecha) {
        Date fechaNac = null;
        try {
            fecha = fecha.replace("/", "-");
            fechaNac = new SimpleDateFormat("dd-MM-yyyy").parse(fecha);
        } catch (Exception ex) {
            System.out.println("Error:" + ex);
        }
        Calendar fechaNacimiento = Calendar.getInstance();
        //Se crea un objeto con la fecha actual
        Calendar fechaActual = Calendar.getInstance();
        //Se asigna la fecha recibida a la fecha de nacimiento.
        fechaNacimiento.setTime(fechaNac);
        //Se restan la fecha actual y la fecha de nacimiento
        int anho = fechaActual.get(Calendar.YEAR) - fechaNacimiento.get(Calendar.YEAR);
        int mes = fechaActual.get(Calendar.MONTH) - fechaNacimiento.get(Calendar.MONTH);
        int dia = fechaActual.get(Calendar.DATE) - fechaNacimiento.get(Calendar.DATE);
        //Se ajusta el año dependiendo el mes y el día
        if (mes < 0 || (mes == 0 && dia < 0)) {
            anho--;
        }
        if (mes < 0) {
            mes = 12 + mes;
        }
        return mes;
    }

    public String obtenerRuta1(JInternalFrame padre, String nombre) {
        JFileChooser ventana = new JFileChooser("C:\\");
        ventana.setSelectedFile(new File("C:\\" + nombre + ".pdf"));
        int boton = ventana.showDialog(null, "Seleccionar");
        if (boton == 0) {
            return ventana.getSelectedFile().toString();
        }
        return null;
    }

    public String obtenerRuta(JInternalFrame padre, String nombre) {
        JFileChooser ventana = new JFileChooser("C:\\");
        ventana.setSelectedFile(new File("C:\\" + nombre + ".xls"));
        int boton = ventana.showDialog(null, "Seleccionar");
        if (boton == 0) {
            return ventana.getSelectedFile().toString();
        }
        return null;
    }

    public String obtenerRuta2(JInternalFrame padre, String nombre) {
        JFileChooser ventana = new JFileChooser("C:\\");
        ventana.setSelectedFile(new File("C:\\" + nombre + ".accdb"));
        int boton = ventana.showDialog(null, "Seleccionar");
        if (boton == 0) {
            return ventana.getSelectedFile().toString();
        }
        return null;
    }

    public String obtenerRuta4(JInternalFrame padre, String nombre) {
        JFileChooser ventana = new JFileChooser("C:\\");
        ventana.setSelectedFile(new File("C:\\" + nombre + ".sql"));
        int boton = ventana.showDialog(null, "Seleccionar");
        if (boton == 0) {
            return ventana.getSelectedFile().toString();
        }
        return null;
    }

    public String obtenerRuta3(JInternalFrame padre, String nombre) {

        JFileChooser ventana = new JFileChooser("C:\\");
        ImagePreviewPanel preview = new ImagePreviewPanel();
        ventana.setAccessory(preview);
        ventana.addPropertyChangeListener(preview);
        ventana.setSelectedFile(new File("C:\\" + nombre + ".png"));
        int boton = ventana.showDialog(null, "Seleccionar");
        if (boton == 0) {
            return ventana.getSelectedFile().toString();
        }
        return null;
    }

    public static void copiarArchivo(String origen, String destino) {
        try {
            Path FROM = Paths.get(origen);
            Path TO = Paths.get(destino);
            //sobreescribir el fichero de destino, si existe, y copiar
            // los atributos, incluyendo los permisos rwx
            CopyOption[] options = new CopyOption[]{
                StandardCopyOption.REPLACE_EXISTING,
                StandardCopyOption.COPY_ATTRIBUTES
            };
            Files.copy(FROM, TO, options);

        } catch (IOException ex) {
            Logger.getLogger(metodosGenerales.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void abrirArchivo(String ruta) {
        try {
            File objetofile = new File(ruta);
            Desktop.getDesktop().open(objetofile);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public static String fechaHora() {
        Date fecha1 = new Date();
        return fecha1.toLocaleString();
    }

    public static void actualizarVariables() {
        Instancias instancias = Instancias.getInstancias();
        boolean conIva = !instancias.getRegimen().equals("SinIva");

        //FACTURACION
        for (int i = 0; i < 8; i++) {
            JTable tablaFactura = null;
            Object[] ocultarIva = null;

            if (i == 0) {
                //FACTURA
                if (conIva) {
                    ocultarIva = instancias.getFactura().getOcultarIva();
                } else {
                    ocultarIva = instancias.getFactura().getOcultarIva();
                }
                tablaFactura = (JTable) ocultarIva[0];
            } else if ((i == 2)) {
                conIva = !instancias.getRegimen().equals("SinIva");
                //COTIZACION
                if (conIva) {
                    ocultarIva = instancias.getCotiza().getOcultarIva();
                } else {
                    ocultarIva = instancias.getCotiza().getOcultarIva();
                }
                tablaFactura = (JTable) ocultarIva[0];
            } else if ((i == 3)) {
                conIva = !instancias.getRegimen().equals("SinIva");
                //ORDEN
                if (conIva) {
                    ocultarIva = instancias.getOrdenServicio().getOcultarIva();
                } else {
                    ocultarIva = instancias.getOrdenServicio().getOcultarIva();
                }
                tablaFactura = (JTable) ocultarIva[0];
            } else if ((i == 4)) {
                conIva = !instancias.getRegimen().equals("SinIva");
                //PEDIDO
                if (conIva) {
                    ocultarIva = instancias.getPedido().getOcultarIva();
                } else {
                    ocultarIva = instancias.getPedido().getOcultarIva();
                }
                tablaFactura = (JTable) ocultarIva[0];
            } else if ((i == 5)) {
                conIva = !instancias.getRegimen().equals("SinIva");
                //PEDIDO
                if (conIva) {
                    ocultarIva = instancias.getPlanSepare().getOcultarIva();
                } else {
                    ocultarIva = instancias.getPlanSepare().getOcultarIva();
                }
                tablaFactura = (JTable) ocultarIva[0];
            } else if ((i == 6)) {
                conIva = !instancias.getRegimen().equals("SinIva");
                //PEDIDO
                if (conIva) {
                    ocultarIva = instancias.getNd().getOcultarIva();
                } else {
                    ocultarIva = instancias.getNd().getOcultarIva();
                }
                tablaFactura = (JTable) ocultarIva[0];
            } else if ((i == 7)) {
                conIva = !instancias.getRegimen().equals("SinIva");
                //PEDIDO
                if (conIva) {
                    ocultarIva = instancias.getNc().getOcultarIva();
                } else {
                    ocultarIva = instancias.getNc().getOcultarIva();
                }
                tablaFactura = (JTable) ocultarIva[0];
            }

            if (i != 8) {
                if (instancias.getDescuento().equals("peso")) {
                    if (tablaFactura != null) {
                        tablaFactura.getColumnModel().getColumn(6).setMinWidth(100);
                        tablaFactura.getColumnModel().getColumn(6).setPreferredWidth(100);
                        tablaFactura.getColumnModel().getColumn(6).setMaxWidth(100);
                        tablaFactura.getColumnModel().getColumn(5).setMinWidth(0);
                        tablaFactura.getColumnModel().getColumn(5).setPreferredWidth(0);
                        tablaFactura.getColumnModel().getColumn(5).setMaxWidth(0);
                    }
                } else {
                    if (tablaFactura != null) {
                        tablaFactura.getColumnModel().getColumn(5).setMinWidth(50);
                        tablaFactura.getColumnModel().getColumn(5).setPreferredWidth(50);
                        tablaFactura.getColumnModel().getColumn(5).setMaxWidth(50);
                        tablaFactura.getColumnModel().getColumn(6).setMinWidth(0);
                        tablaFactura.getColumnModel().getColumn(6).setPreferredWidth(0);
                        tablaFactura.getColumnModel().getColumn(6).setMaxWidth(0);
                    }
                }

                if (instancias.isPvpConImpoconsumo()) {
                    if (tablaFactura != null) {
                        tablaFactura.getColumnModel().getColumn(8).setMinWidth(0);
                        tablaFactura.getColumnModel().getColumn(8).setPreferredWidth(0);
                        tablaFactura.getColumnModel().getColumn(8).setMaxWidth(0);
                    }
                } else {
                    if (tablaFactura != null) {
                        tablaFactura.getColumnModel().getColumn(8).setMinWidth(80);
                        tablaFactura.getColumnModel().getColumn(8).setPreferredWidth(100);
                        tablaFactura.getColumnModel().getColumn(8).setMaxWidth(130);
                    }
                }

                if (tablaFactura != null) {
                    ocultarItems(conIva, ocultarIva);
                }
            }
        }

        instancias.getProductos().ocultarItems(conIva);
    }

    private static void ocultarItems(boolean ocultar, Object[] ocultarIva) {

        for (int i = 1; i < ocultarIva.length; i++) {
            if (ocultarIva[i] instanceof JTextField) {
                ((JTextField) ocultarIva[i]).setVisible(ocultar);
            }
            if (ocultarIva[i] instanceof JLabel) {
                ((JLabel) ocultarIva[i]).setVisible(ocultar);
            }
            if (ocultarIva[i] instanceof JCheckBox) {
                ((JCheckBox) ocultarIva[i]).setVisible(ocultar);
            }
            if (ocultarIva[i] instanceof JComboBox) {
                ((JComboBox) ocultarIva[i]).setVisible(ocultar);
            }
        }
    }

    public boolean revisarTabla(JTable tabla, int columna, String condicion) {
        for (int i = 0; i < tabla.getRowCount(); i++) {
            if (tabla.getValueAt(i, columna).toString().contains(condicion)) {
                return false;
            }
        }
        return true;
    }

    public void ajuste(TableModelEvent e, Integer opc) {

        //solo si se edita la tabla
        if (e.getType() == TableModelEvent.UPDATE) {

            //se obtiene el modelo
            TableModel modelo = ((TableModel) (e.getSource()));
            int fila = e.getFirstRow();
            int columna = e.getColumn();
            try {
                Object dato = modelo.getValueAt(fila, columna);
                if (columna == 3) {
                    dato = modelo.getValueAt(fila, columna);
                    if (dato.toString().contains(".")) {
                        this.msgError(null, "Ingreso un dato erroneo");
                        return;
                    }
                }
                switch (opc) {
                    case 1:
                        if (columna == 5 && dato.equals("")) {
                            this.msgError(null, "Ingreso un dato erroneo");
                            return;
                        }
                        break;
                    case 2:
                        if (columna == 3 && dato.equals("")) {
                            this.msgError(null, "Ingreso un dato erroneo");
                            return;
                        }
                        break;
                    default:
                        break;
                }
            } catch (Exception ex) {
            }
            return;
        }
    }

    public static String codificarSerial() {
        return Disk.leer();
    }

    public static String letraTerminal(int id) {
        switch (id) {
            case 0:
                return "A";
            case 1:
                return "A";
            case 2:
                return "B";
            case 3:
                return "C";
            case 4:
                return "D";
            case 5:
                return "E";
            case 6:
                return "F";
            case 7:
                return "G";
            case 8:
                return "H";
            case 9:
                return "I";
            case 10:
                return "J";
            case 11:
                return "K";
            case 12:
                return "L";
            case 13:
                return "M";
            case 14:
                return "N";
            case 15:
                return "O";
            case 16:
                return "P";
            case 17:
                return "Q";
            case 18:
                return "R";
            case 19:
                return "S";
            case 20:
                return "T";
            case 21:
                return "U";
            case 22:
                return "V";
            case 23:
                return "W";
            case 24:
                return "X";
            case 25:
                return "Y";
            case 26:
                return "Z";
        }
        return "";
    }

    public String leerArchivo(String archivo) {
        try {
            String cadena;
            FileReader f;

            f = new FileReader(archivo);
            BufferedReader b = new BufferedReader(f);
            while ((cadena = b.readLine()) != null) {
                return cadena;
            }
            try {

            } catch (Exception e) {
            }
            b.close();

        } catch (IOException ex) {
            Logger.getLogger(metodosGenerales.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return "1";
    }

    //Montar una imagen de terceros y recortarla.
    public void montarImagenTerceros(String origen, String destino) {
        //llama el metodo con la ruta de origen, destino y el tamaño que debe tomar la imagen.
        recortarYguardarImagen(origen, destino, 250, 250);
    }

    //Montar la imagen del logo.
    public void montarLogo(String origen, String destino) {
        //llama el metodo con la ruta de origen, destino y el tamaño que debe tomar la imagen.
        recortarYguardarImagen(origen, destino, 280, 150);
    }

    //Montar las firmas
    public void montarFirma(String origen, String destino) {
        //llama el metodo con la ruta de origen, destino y el tamaño que debe tomar la imagen.
        recortarYguardarImagen(origen, destino, 200, 60);
    }

    /*Este método es el de la magia recibe la ruta al archivo original y la ruta donde vamos a guardar la copia*/
    public static void recortarYguardarImagen(String origen, String destino, int anchoMax, int altoMax) {
        BufferedImage imagen = cargarImagen(origen);
        if (imagen.getHeight() > imagen.getWidth()) {
            int heigt = (imagen.getHeight() * anchoMax) / imagen.getWidth();
            imagen = recortar(imagen, anchoMax, heigt);
            int width = (imagen.getWidth() * altoMax) / imagen.getHeight();
            imagen = recortar(imagen, width, altoMax);
        } else {
            int width = (imagen.getWidth() * altoMax) / imagen.getHeight();
            imagen = recortar(imagen, width, altoMax);
            int heigt = (imagen.getHeight() * anchoMax) / imagen.getWidth();
            imagen = recortar(imagen, anchoMax, heigt);
        }
        guardarImagen(imagen, destino);
    }

    /*Este método se utiliza para cargar la imagen de disco*/
    public static BufferedImage cargarImagen(String origen) {
        BufferedImage bimage = null;
        try {
            bimage = ImageIO.read(new File(origen));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bimage;
    }

    /*Este método se utiliza para almacenar la imagen en disco*/
    public static void guardarImagen(BufferedImage bufferedImage, String pathName) {
        try {
            String format = (pathName.endsWith(".png")) ? "png" : "jpg";
            File file = new File(pathName);
            file.getParentFile().mkdirs();
            ImageIO.write(bufferedImage, format, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*Este método se utiliza para redimensionar la imagen */
    public static BufferedImage recortar(BufferedImage bufferedImage, int newW, int newH) {
        int w = bufferedImage.getWidth();
        int h = bufferedImage.getHeight();
        BufferedImage bufim = new BufferedImage(newW, newH, bufferedImage.getType());
        Graphics2D g = bufim.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(bufferedImage, 0, 0, newW, newH, 0, 0, w, h, null);
        g.dispose();
        return bufim;
    }

    /* OFTAMOLOGIA */
    public ndHojaIngreso llenarHojaIngreso(Object[] vector) {

        ndHojaIngreso nodo = new ndHojaIngreso();

        nodo.setId((String) vector[0]);
        nodo.setHistoria((String) vector[1]);
        nodo.setFechaIngreso((String) vector[2]);
        nodo.setIdentificacion((String) vector[3]);
        nodo.setPrimerApellido((String) vector[4]);
        nodo.setSegundoApellido((String) vector[5]);
        nodo.setPrimerNombre((String) vector[6]);
        nodo.setSegundoNombre((String) vector[7]);
        nodo.setFechaNacimiento((String) vector[8]);
        nodo.setEdad(String.valueOf(vector[9]));
        nodo.setSexo((String) vector[10]);
        nodo.setOcupacion((String) vector[11]);
        nodo.setEstadoCivil((String) vector[12]);
        nodo.setDepartamento((String) vector[13]);
        nodo.setMunicipio((String) vector[14]);
        nodo.setZona((String) vector[15]);
        nodo.setDireccion((String) vector[16]);
        nodo.setTelefono((String) vector[17]);
        nodo.setResponsable((String) vector[18]);
        nodo.setTelefono1((String) vector[19]);
        nodo.setParentesco((String) vector[20]);
        nodo.setEnfermedades((String) vector[21]);
        nodo.setOtrasEnfermedades((String) vector[22]);
        nodo.setLentes((String) vector[23]);
        nodo.setEnfermedades1((String) vector[24]);
        nodo.setLentes1((String) vector[25]);
        nodo.setAlergias((String) vector[26]);
        nodo.setOtrasEnfermedadesOculares((String) vector[27]);
        nodo.setOtrasEnfermadesdeGenerales((String) vector[28]);
        nodo.setRefraccionOD((String) vector[29]);
        nodo.setRefraccionOI((String) vector[30]);
        nodo.setClOD((String) vector[31]);
        nodo.setClOI((String) vector[32]);
        nodo.setRefSubjetivoOD((String) vector[33]);
        nodo.setRefSubjetivoOI((String) vector[34]);
        nodo.setAvOD((String) vector[35]);
        nodo.setAvOI((String) vector[36]);
        nodo.setForias((String) vector[37]);
        nodo.setPupilas((String) vector[38]);
        nodo.setMc((String) vector[39]);
        nodo.setTonometriaOD((String) vector[40]);
        nodo.setTonometriaOI((String) vector[41]);
        nodo.setMmhgOD((String) vector[42]);
        nodo.setMmhgOI((String) vector[43]);
        nodo.setBiomicroscopia((String) vector[44]);
        nodo.setFondoDeOjo((String) vector[45]);
        nodo.setCambioProcedimiento((String) vector[46]);
        nodo.setCie((String) vector[47]);
        nodo.setCup((String) vector[48]);
        nodo.setOrden((String) vector[49]);
        nodo.setServicio((String) vector[50]);
        nodo.setCie1((String) vector[51]);
        nodo.setCie2((String) vector[52]);
        nodo.setCie3((String) vector[53]);
        nodo.setCie4((String) vector[54]);
        nodo.setCup1((String) vector[55]);
        nodo.setCup2((String) vector[56]);
        nodo.setCup3((String) vector[57]);
        nodo.setCup4((String) vector[58]);
        nodo.setHora((String) vector[59]);
        nodo.setConducta((String) vector[60]);
        nodo.setAnexos((String) vector[61]);

        return nodo;
    }

    public Object[] pasarDatosHojaIngreso(ndHojaIngreso nodo) {
        Object[] vector = {nodo.getId(), nodo.getHistoria(), nodo.getFechaIngreso(), nodo.getIdentificacion(), nodo.getPrimerApellido(),
            nodo.getSegundoApellido(), nodo.getPrimerNombre(), nodo.getSegundoNombre(), nodo.getFechaNacimiento(), nodo.getEdad(),
            nodo.getSexo(), nodo.getOcupacion(), nodo.getEstadoCivil(), nodo.getDepartamento(), nodo.getMunicipio(), nodo.getZona(),
            nodo.getDireccion(), nodo.getTelefono(), nodo.getResponsable(), nodo.getTelefono1(), nodo.getParentesco(), nodo.getEnfermedades(),
            nodo.getOtrasEnfermedades(), nodo.getLentes(), nodo.getEnfermedades1(), nodo.getLentes1(), nodo.getAlergias(),
            nodo.getOtrasEnfermedadesOculares(), nodo.getOtrasEnfermadesdeGenerales(), nodo.getRefraccionOD(), nodo.getRefraccionOI(),
            nodo.getClOD(), nodo.getClOI(), nodo.getRefSubjetivoOD(), nodo.getRefSubjetivoOI(), nodo.getAvOD(), nodo.getAvOI(),
            nodo.getForias(), nodo.getPupilas(), nodo.getMc(), nodo.getTonometriaOD(), nodo.getTonometriaOI(), nodo.getMmhgOD(), nodo.getMmhgOI(),
            nodo.getBiomicroscopia(), nodo.getFondoDeOjo(), nodo.getCambioProcedimiento(), nodo.getCie(), nodo.getCup(), nodo.getOrden(),
            nodo.getServicio(), nodo.getCie1(), nodo.getCie2(), nodo.getCie3(), nodo.getCie4(), nodo.getCup1(), nodo.getCup2(), nodo.getCup3(), nodo.getCup4(),
            nodo.getHora(), nodo.getConducta(), nodo.getAnexos()
        };
        return vector;
    }

    public Object[] pasarDatosSeguimientoClinicaOf(ndSeguimientoClinicaOf nodo) {

        Object[] vector = {nodo.getFecha(), nodo.getUsuario(), nodo.getEvolucion(), nodo.getFotos(), nodo.getConsulta(), nodo.getExamen()};

        return vector;
    }

    public ndSeguimientoClinicaOf llenarSeguimientoClinicaOf(Object[] vector) {

        ndSeguimientoClinicaOf nodo = new ndSeguimientoClinicaOf();
        nodo.setId((String) vector[0]);
        nodo.setFecha((String) vector[1]);
        nodo.setUsuario((String) vector[2]);
        nodo.setEvolucion((String) vector[3]);
        nodo.setFotos((String) vector[4]);
        nodo.setConsulta((String) vector[5]);
        nodo.setExamen((String) vector[6]);

        return nodo;
    }

    public Object[] pasarDatosFormulaLentes(ndFormulaLentes nodo) {

        Object[] vector = {nodo.getId(), nodo.getFecha(), nodo.getPaciente(), nodo.getOd(), nodo.getOi(), nodo.getAdd(),
            nodo.getBifocal()};

        return vector;
    }

    public Object[] pasarDatosBiometria(ndBiometria nodo) {
        Object[] vector = {nodo.getId(), nodo.getFecha(), nodo.getCedula(), nodo.getNombre(), nodo.getH(), nodo.getV(),
            nodo.getH1(), nodo.getV1(), nodo.getLongitudAxial(), nodo.getOd(), nodo.getOi(), nodo.getH2(), nodo.getV2(),
            nodo.getH3(), nodo.getV3(), nodo.getLongitudaAxial1(), nodo.getOd1(), nodo.getOi1(), nodo.getT1(), nodo.getT2(),
            nodo.getT3(), nodo.getT4(), nodo.getT5(), nodo.getT6(), nodo.getT7(), nodo.getT8(), nodo.getT9(), nodo.getT10(),
            nodo.getT11(), nodo.getT12(), nodo.getT13(), nodo.getT14(), nodo.getT15(), nodo.getT16(), nodo.getT17(),
            nodo.getT18(), nodo.getT19(), nodo.getT20(), nodo.getT21(), nodo.getT22(), nodo.getT23(), nodo.getT24(),
            nodo.getT25(), nodo.getT26(), nodo.getT27(), nodo.getT28(), nodo.getT29(), nodo.getT30()};
        return vector;
    }

    public Object[] pasarDatosPaquimetria(ndPaquimetria nodo) {
        Object[] vector = {nodo.getId(), nodo.getFecha(), nodo.getCedula(), nodo.getNombre(), nodo.getDoctor(),
            nodo.getOjoIzquierdo(), nodo.getZonaIzq(), nodo.getOjoDerecho(), nodo.getZonaDer(), nodo.getDibujo()};
        return vector;
    }

    public ndPaquimetria llenarPaquimetria(Object[] vector) {
        ndPaquimetria nodo = new ndPaquimetria();
        nodo.setId((String) vector[0]);
        nodo.setFecha((String) vector[1]);
        nodo.setCedula((String) vector[2]);
        nodo.setNombre((String) vector[3]);
        nodo.setDoctor((String) vector[4]);
        nodo.setOjoIzquierdo((String) vector[5]);
        nodo.setZonaIzq((String) vector[6]);
        nodo.setOjoDerecho((String) vector[7]);
        nodo.setZonaDer((String) vector[8]);
        nodo.setDibujo((String) vector[9]);
        return nodo;
    }

    public ndBiometria llenarBiometria(Object[] vector) {

        ndBiometria nodo = new ndBiometria();
        nodo.setId((String) vector[0]);
        nodo.setFecha((String) vector[1]);
        nodo.setCedula((String) vector[2]);
        nodo.setNombre((String) vector[3]);
        nodo.setH((String) vector[4]);
        nodo.setV((String) vector[5]);
        nodo.setH1((String) vector[6]);
        nodo.setV1((String) vector[7]);
        nodo.setLongitudAxial((String) vector[8]);
        nodo.setOd((String) vector[9]);
        nodo.setOi((String) vector[10]);
        nodo.setH2((String) vector[11]);
        nodo.setV2((String) vector[12]);
        nodo.setH3((String) vector[13]);
        nodo.setV3((String) vector[14]);
        nodo.setLongitudaAxial1((String) vector[15]);
        nodo.setOd1((String) vector[16]);
        nodo.setOi1((String) vector[17]);
        nodo.setT1((String) vector[18]);
        nodo.setT2((String) vector[19]);
        nodo.setT3((String) vector[20]);
        nodo.setT4((String) vector[21]);
        nodo.setT5((String) vector[22]);
        nodo.setT6((String) vector[23]);
        nodo.setT7((String) vector[24]);
        nodo.setT8((String) vector[25]);
        nodo.setT9((String) vector[26]);
        nodo.setT10((String) vector[27]);
        nodo.setT11((String) vector[28]);
        nodo.setT12((String) vector[29]);
        nodo.setT13((String) vector[30]);
        nodo.setT14((String) vector[31]);
        nodo.setT15((String) vector[32]);
        nodo.setT16((String) vector[33]);
        nodo.setT17((String) vector[34]);
        nodo.setT18((String) vector[35]);
        nodo.setT19((String) vector[36]);
        nodo.setT20((String) vector[37]);
        nodo.setT21((String) vector[38]);
        nodo.setT22((String) vector[39]);
        nodo.setT23((String) vector[40]);
        nodo.setT24((String) vector[41]);
        nodo.setT25((String) vector[42]);
        nodo.setT26((String) vector[43]);
        nodo.setT27((String) vector[44]);
        nodo.setT28((String) vector[45]);
        nodo.setT29((String) vector[46]);
        nodo.setT30((String) vector[47]);

        return nodo;
    }

    public ndFormulaLentes llenarFormulaLentes(Object[] vector) {

        ndFormulaLentes nodo = new ndFormulaLentes();

        nodo.setId((String) vector[0]);
        nodo.setFecha((String) vector[1]);
        nodo.setPaciente((String) vector[2]);
        nodo.setOd((String) vector[3]);
        nodo.setOi((String) vector[4]);
        nodo.setAdd((String) vector[5]);
        nodo.setBifocal((String) vector[6]);

        return nodo;
    }
}
