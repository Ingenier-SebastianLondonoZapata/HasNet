package clases;

import Controlador.BarraProceso.controladorBarraProceso;
import Controlador.BarraProceso.jcThread;
import Utilidades.BaseDatos.MySql_connection;
import Vista.BarraProceso.vistaBarraProceso;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.HashPrintServiceAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.PrintServiceAttributeSet;
import javax.print.attribute.standard.OrientationRequested;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JExcelApiExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class iFactura {

    String imagenInforme1 = "";

    public String getImagenInforme1() {
        return imagenInforme1;
    }

    public void setImagenInforme1(String imagenInforme1) {
        this.imagenInforme1 = imagenInforme1;
    }

    private final String logo = System.getProperty("user.dir") + "//imagenes//conf//logoEmpresa.png";
    private final String automovil = "/imagenes/Fundacion1.png";
    private final String logo2 = "/imagenes/colposcopia.png";

    private String logo3 = "";
    private String imagenInforme = "";
    private final String firma = "/imagenes/firma.png";
    private Instancias instancia;
    public String informacionLegalClick = "";
    metodosGenerales metodos = new metodosGenerales();
    Object[] datos = null;

    public void consultarMaestros() {
        datos = instancia.getSql().getDatosMaestra();
    }

    public void setInformacionLegalClick(String informacionLegalClick) {
        this.informacionLegalClick = informacionLegalClick;
    }

    public iFactura(Instancias instancia) {
        this.instancia = instancia;
        consultarMaestros();
    }

    public void setFirma() {
        logo3 = System.getProperty("user.dir") + "//imagenes//firmas//" + instancia.getUsuario() + ".jpg";
    }

    public void setImagenInforme() {
        imagenInforme = System.getProperty("user.dir") + "//imagenes//terceros//" + imagenInforme1 + ".jpg";
    }

    /* INICIO EL VETERINARIO */
    public void verRepAlertasVacunas(String sql) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/reportesVeterinario/repAlertasVacunacion.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("consulta", sql);
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());
            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void ver_reporteFormatosClientes(String formato, String tercero) {
        JasperReport reporte;
        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/reportesVeterinario/reporteFormatos.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap 
            Map<String, String> parametros = new HashMap<String, String>();
            parametros.clear();
            parametros.put("formato", formato);
            parametros.put("tercero", tercero);
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());

            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void ver_reporteFormatosTipos(String formato, String tercero) {
        JasperReport reporte;
        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/reportesVeterinario/reporteFormatosTipos.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap 
            Map<String, String> parametros = new HashMap<String, String>();
            parametros.clear();
            parametros.put("formato", formato);
            parametros.put("tercero", tercero);
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());
            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void ver_Formato(String nombre, String url) {
        JasperReport reporte;
        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/impresionesVeterinario/" + url + ".jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("nombre", nombre);
            parametros.put("urlImagen", this.getClass().getResourceAsStream(logo));

            Iniciar ini = new Iniciar(parametros, reporte, false, false, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void ver_ImpresionVacunas(String sql) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/impresionesVeterinario/vacunasMascotas1.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("cliente", sql);
            parametros.put("urlImagen", logo());
            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void verNegativa(String nombre, String sexo, String raza, String edad, String codigo,
            String reproductivo, String cedula, String nombreDueno, String telefono, String direccion, String historia) {

        JasperReport reporte;
        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/impresionesVeterinario/formatoNegativa.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("info", instancia.getInformacionEmpresa());
            parametros.put("nombre", nombre);
            parametros.put("sexo", sexo);
            parametros.put("raza", raza);
            parametros.put("edad", edad);
            parametros.put("codigo", codigo);
            parametros.put("reproductivo", reproductivo);
            parametros.put("cedula", cedula);
            parametros.put("nombreDueño", nombreDueno);
            parametros.put("telefono", telefono);
            parametros.put("direccion", direccion);
            parametros.put("historia", historia);
            parametros.put("fecha", metodosGenerales.fecha());
            parametros.put("urlImagen", logo());
            parametros.put("firma", logo3);
            parametros.put("usuario", instancia.getUsuarioLog().getNombre());
            Iniciar ini = new Iniciar(parametros, reporte, false, false, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();
        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void verRepPeluqueria(String sql, String enca, String tipo) {
        JasperReport reporte;
        try {
            //direccion del archivo JASPER
            System.out.println("repPeluqueria" + tipo);
            URL in = this.getClass().getResource("/reportesVeterinario/repPeluqueria" + tipo + ".jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("cliente", sql);
            parametros.put("enca", enca);
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());
            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void verAyudaDiagnosticaVeterinaria(String id, String nombre, String sexo, String raza,
            String edad, String codigo, String reproductivo, String cedula, String nombreDueno,
            String telefono, String direccion, String historia) {
        JasperReport reporte;
        try {
            URL in = this.getClass().getResource("/impresionesVeterinario/ayudaDiagnosticoVeterinaria.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map parametros = new HashMap();
            parametros.clear();

            parametros.put("info", instancia.getInformacionEmpresa());
            parametros.put("id", id);
            parametros.put("nombre", nombre);
            parametros.put("sexo", sexo);
            parametros.put("raza", raza);
            parametros.put("edad", edad);
            parametros.put("codigo", codigo);
            parametros.put("reproductivo", reproductivo);
            parametros.put("cedula", cedula);
            parametros.put("nombreDueño", nombreDueno);
            parametros.put("telefono", telefono);
            parametros.put("direccion", direccion);
            parametros.put("historia", historia);
            parametros.put("fecha", metodosGenerales.fecha());
            parametros.put("urlImagen", logo());
            parametros.put("firma", logo3);
            parametros.put("usuario", instancia.getUsuarioLog().getNombre());

            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void verFormulaQuirurgica(String cedula, String nombre, String historia, String raza, String consecutivo,
            String sexo, String edad, String tipo, String descripcion, String fecha, String tipo1) {

        JasperReport reporte;
        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/impresionesVeterinario/formulaQuirurgica" + tipo1 + ".jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("info", instancia.getInformacionEmpresa());
            parametros.put("id", cedula);
            parametros.put("nombre", nombre);
            parametros.put("historia", historia);
            parametros.put("raza", raza);
            parametros.put("Id", consecutivo);
            parametros.put("sexo", sexo);
            parametros.put("edad", edad);
            parametros.put("tipo", tipo);
            parametros.put("descripcion", descripcion);
            parametros.put("fecha", fecha);
            parametros.put("urlImagen", logo());
            parametros.put("firma", logo3);
            parametros.put("usuario", instancia.getUsuarioLog().getNombre());

            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void verRemisionVeterinaria(String dato, String edad) {
        JasperReport reporte;
        try {
            URL in = this.getClass().getResource("/impresionesVeterinario/remisionVeterinaria.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("id", dato);
            parametros.put("edad", edad);
            parametros.put("firma", logo3);
            parametros.put("usuario", instancia.getUsuario());
            parametros.put("urlImagen", logo());

            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void verFormulaMedicaVeterinaria(String id, String nombre,
            String sexo, String raza, String edad, String codigo,
            String reproductivo, String cedula, String nombreDueno, String telefono, String direccion,
            String proximoControl, String tipo, String historia) {

        JasperReport reporte;
        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/impresionesVeterinario/formulaMedicaVeterinaria" + tipo + ".jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("info", instancia.getInformacionEmpresa());
            parametros.put("id", id);
            parametros.put("nombre", nombre);
            parametros.put("sexo", sexo);
            parametros.put("raza", raza);
            parametros.put("edad", edad);
            parametros.put("codigo", codigo);
            parametros.put("historia", historia);
            parametros.put("reproductivo", reproductivo);
            parametros.put("cedula", cedula);
            parametros.put("nombreDueño", nombreDueno);
            parametros.put("telefono", telefono);
            parametros.put("direccion", direccion);
            parametros.put("fecha", metodosGenerales.fecha());
            parametros.put("urlImagen", logo());
            parametros.put("firma", logo3);
            parametros.put("usuario", instancia.getUsuarioLog().getNombre());
            parametros.put("proximoControl", proximoControl);

            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void verRemision(String cedula, String nombre, String historia, String raza, String consecutivo,
            String sexo, String edad, String tipo, String descripcion, String fecha, String tipo2) {
        JasperReport reporte;
        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/impresionesVeterinario/HojaRemision" + tipo2 + ".jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("info", instancia.getInformacionEmpresa());
            parametros.put("id", cedula);
            parametros.put("nombre", nombre);
            parametros.put("historia", historia);
            parametros.put("raza", raza);
            parametros.put("Id", consecutivo);
            parametros.put("sexo", sexo);
            parametros.put("edad", edad);
            parametros.put("tipo", tipo);
            parametros.put("descripcion", descripcion);
            parametros.put("fecha", fecha);
            parametros.put("urlImagen", logo());
            parametros.put("firma", logo3);
            parametros.put("usuario", instancia.getUsuarioLog().getNombre());

            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    /* INICIO PARQUEADERO */
    public void ver_RepMensualidades(String sql, String encabezado, String tipo) {
        JasperReport reporte;
        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/reportesParqueadero/repMensualidad" + tipo + ".jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map<String, String> parametros = new HashMap<String, String>();
            parametros.clear();
            parametros.put("cliente", sql);
            parametros.put("encabezado", encabezado);
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());

            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void ver_RepParqueadero(String sql, String encabezado, String tipo) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/reportesParqueadero/repParqueadero.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map<String, String> parametros = new HashMap<String, String>();
            parametros.clear();
            parametros.put("cliente", sql);
            parametros.put("encabezado", encabezado);
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());

            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void verRepLavado(String sql, String encabezado, String tipo) {
        JasperReport reporte;
        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/reportesParqueadero/repLavado" + tipo + ".jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map<String, String> parametros = new HashMap<String, String>();
            parametros.clear();
            parametros.put("cliente", sql);
            parametros.put("encabezado", encabezado);
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());

            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void ver_RepFormulas(String sql, String encabezado, String tipo) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/impresionesMedico/repFormulas" + tipo + ".jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map<String, String> parametros = new HashMap<String, String>();
            parametros.clear();
            parametros.put("cliente", sql);
            parametros.put("encabezado", encabezado);

            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void verLiquidacionLavado(String sql, String encabezado, String tipo) {
        JasperReport reporte;
        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/reportesParqueadero/liquidacionLavado.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map<String, String> parametros = new HashMap<String, String>();
            parametros.clear();
            parametros.put("cliente", sql);
            parametros.put("encabezado", encabezado);
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());

            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void verHistorial(String sql, String encabezado, String tipo) {
        JasperReport reporte;
        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/reportesParqueadero/repHistorial.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map<String, String> parametros = new HashMap<String, String>();
            parametros.clear();
            parametros.put("consulta", sql);
            parametros.put("encabezado", encabezado);
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());

            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void verPagos(String info) {
        JasperReport reporte;
        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/reportesParqueadero/pagos.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("cliente", info);
            parametros.put("urlImagen", this.getClass().getResourceAsStream(logo));
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());

            Iniciar ini = new Iniciar(parametros, reporte, true, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();
        } catch (JRException E) {
            System.out.println(E);
        }
    }

    /* FIN PARQUEADERO*/
    // MEDICO // MEDICO // MEDICO // MEDICO // MEDICO // MEDICO // MEDICO // MEDICO //
    public void verAutorizacionServicios(String dato) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/reportes/ejemplo/autorizacionServicios.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("id", dato);
            parametros.put("firma", logo3);
            parametros.put("usuario", instancia.getUsuarioLog().getNombre());
            parametros.put("urlImagen", logo());

            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void detalleCargo(String sql, String nombre, String nit, String direccion, String factura) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/reportes/ejemplo/detalleCargo.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map<String, String> parametros = new HashMap<String, String>();
            parametros.clear();
            parametros.put("cliente", sql);
            parametros.put("nombre", nombre);
            parametros.put("nit", nit);
            parametros.put("direccion", direccion);
            parametros.put("factura", factura);

            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void planoFactura(String sql, String factura, String uno, String dos) {
        JasperReport reporte;
        System.out.println("select * from planoFactura " + sql);
        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/reportes/ejemplo/planoFactura.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map<String, String> parametros = new HashMap<String, String>();
            parametros.clear();
            parametros.put("cliente", sql);
            parametros.put("factura", factura);
            parametros.put("uno", uno);
            parametros.put("dos", dos);

            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void verNotaEnfermeria(String numero, String info, String id, String tipo, String nombre, String sexo, String estado, String edad, String fecha, boolean imprimir) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/impresionesMedico/notaEnfermeria.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("numero", numero);
            parametros.put("id", id);
            parametros.put("tipo", tipo);
            parametros.put("nombre", nombre);
            parametros.put("sexo", sexo);
            parametros.put("estado", estado);
            parametros.put("edad", edad);
            parametros.put("fecha", fecha);
            parametros.put("urlImagen", logo());
            parametros.put("info", info);
            parametros.put("firma", logo3);
            parametros.put("usuario", instancia.getUsuarioLog().getNombre());

            Iniciar ini = new Iniciar(parametros, reporte, imprimir, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void verCita(String numero, String info) {
        JasperReport reporte;
        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/reportes/ejemplo/comprobanteCita.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("numero", numero);
            parametros.put("info", info);
            parametros.put("urlImagen", logo());

            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void verRepCitasMedicas(String sql, String enca, String tipo, String medico) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/reportesAgenda/repCitas" + tipo + ".jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("cliente", sql);
            parametros.put("enca", enca);
            parametros.put("medico", medico);

            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

//    public void verRepCitasMedicas(String sql, String enca, String tipo) {
//        JasperReport reporte;
//
//        try {
//            //direccion del archivo JASPER
//            URL in = this.getClass().getResource("/reportes/ejemplo/repCitas" + tipo + ".jasper");
//            reporte = (JasperReport) JRLoader.loadObject(in);
//            //Se crea un objeto HashMap
//            Map parametros = new HashMap();
//            parametros.clear();
//            parametros.put("cliente", sql);
//            parametros.put("enca", enca);
//
//            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
//            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
//            barra.show();
//
//        } catch (JRException E) {
//            System.out.println(E);
//        }
//    }
    //REPORTE DE CREDITO
    public void ver_RepCreditos(String sql, String encabezado, String tipo) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/reportesCreditos/repCreditos.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map<String, String> parametros = new HashMap<String, String>();
            parametros.clear();
            parametros.put("cliente", sql);
            parametros.put("encabezado", encabezado);

            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    //REPORTES DE LOS SEPARES
    public void ver_RepSepares(String sql, String encabezado, String tipo) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/reportesPlanSepare/repSepares" + tipo + ".jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map<String, String> parametros = new HashMap<String, String>();
            parametros.clear();
            parametros.put("cliente", sql);
            parametros.put("encabezado", encabezado);
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());

            if (instancia.getRegimen().equals("")) {
                parametros.put("regimen", "");
            } else {
                parametros.put("regimen", "SinIva");
            }

            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void ver_RepSepares2(String sql, String encabezado, String tipo) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/reportesPlanSepare/repSepares_1" + tipo + ".jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map<String, String> parametros = new HashMap<String, String>();
            parametros.clear();
            parametros.put("cliente", sql);
            parametros.put("encabezado", encabezado);
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());

            if (instancia.getRegimen().equals("")) {
                parametros.put("regimen", "");
            } else {
                parametros.put("regimen", "SinIva");
            }

            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    //REPORTE PLANTILLAS
    public void ver_RepPlantillas(String sql, String encabezado, String tipo) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/reportesPlantillas/repPlantillas" + tipo + ".jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map<String, String> parametros = new HashMap<String, String>();
            parametros.clear();
            parametros.put("cliente", sql);
            parametros.put("encabezado", encabezado);
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());

            if (instancia.getRegimen().equals("")) {
                parametros.put("regimen", "");
            } else {
                parametros.put("regimen", "SinIva");
            }

            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();
        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void ver_RepPlantillas2(String sql, String encabezado, String tipo) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/reportesPlantillas/repPlantillas_1" + tipo + ".jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map<String, String> parametros = new HashMap<String, String>();
            parametros.clear();
            parametros.put("cliente", sql);
            parametros.put("encabezado", encabezado);
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());

            if (instancia.getRegimen().equals("")) {
                parametros.put("regimen", "");
            } else {
                parametros.put("regimen", "SinIva");
            }

            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    //PRODUCTOS DEL PEDIDO
    public void totalProductosPedidos(String Id, String consulta, String info, boolean imprimir) {
        JasperReport reporte;
        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/reportes/ejemplo/totalProductosPedidos.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("Id", Id);
            parametros.put("info", info);
            parametros.put("urlImagen", logo());
            parametros.put("consulta", consulta);

            Iniciar ini = new Iniciar(parametros, reporte, imprimir, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void ver_Separe(String factura, String observaciones, String info, String legal, String tipo, String pie, String nRep, boolean imprimir) {
        JasperReport reporte;
        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/impresionesSepares/" + nRep + ".jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("factura", factura.replace("SEPARE-", ""));
            parametros.put("numFactura", factura);
            parametros.put("urlImagen", logo());
            parametros.put("observaciones", observaciones);
            parametros.put("info", info);
            parametros.put("legal", legal);
            parametros.put("tipoFact", tipo);
            parametros.put("pie", pie);
            parametros.put("titulo", "Separe No.");
            parametros.put("informacionLegalClick", informacionLegalClick);
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());

            Iniciar ini = new Iniciar(parametros, reporte, imprimir, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void ver_ListadoPedido(String factura, String observaciones, String info) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/impresionesPedido/listadoPedido.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("factura", factura.replace("PEDIDO-", ""));
            parametros.put("numPedido", factura);
            parametros.put("urlImagen", logo());
            parametros.put("observaciones", observaciones);
            parametros.put("info", info);
            parametros.put("titulo", "Pedido No.");
            parametros.put("informacionLegalClick", informacionLegalClick);

            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void ver_Pedido(String factura, String observaciones, String info, String legal, String tipo, String pie, String nRep, boolean imprimir) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/impresionesPedido/" + nRep + ".jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("factura", factura.replace("PEDIDO-", ""));
            parametros.put("numPedido", factura);
            parametros.put("urlImagen", logo());
            parametros.put("observaciones", observaciones);
            parametros.put("info", info);
            parametros.put("legal", legal);
            parametros.put("tipoFact", tipo);
            parametros.put("pie", pie);
            parametros.put("titulo", "Pedido No.");
            parametros.put("informacionLegalClick", informacionLegalClick);
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());

            Iniciar ini = new Iniciar(parametros, reporte, imprimir, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    //CLUB Y CUENTA CORRIENTE
    //CONTRATO DE CREDITO
    public void verPrestamo(String contrato, String info) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/reportesCreditos/credito" + Instancias.getInstancias().getTipoImpresion() + ".jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("id", contrato);
//            parametros.put("urlImagen", this.getClass().getResourceAsStream(firma));
            parametros.put("info", info);
            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void verEstadoDeCuenta(String contrato, String info) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/reportes/ejemplo/estadoDeCuenta.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("id", contrato);
            parametros.put("urlImagen", this.getClass().getResourceAsStream(firma));
            parametros.put("info", info);
            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void verCertificado(String anombre, String cliente, String documento,
            String cuotas, String valor, String ciudad, String total, String saldo, String deuda, String info) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/reportes/ejemplo/certificadoDeuda.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("anombre", anombre);
            parametros.put("cliente", cliente);
            parametros.put("documento", documento);
            parametros.put("cuotas", cuotas);
            parametros.put("valor", valor);
            parametros.put("valor2", big.setMoneda(big.getBigDecimal(valor.replace("$", "").replace(".", "").replace(" ", "")).multiply(big.getBigDecimal(deuda))));
            parametros.put("ciudad", ciudad);
            parametros.put("total", total);
            parametros.put("saldo", saldo);
            parametros.put("deuda", deuda);
            parametros.put("fecha", metodosGenerales.fecha());
            parametros.put("urlImagen", this.getClass().getResourceAsStream(firma));
            parametros.put("info", info);
            Iniciar ini = new Iniciar(parametros, reporte, false, false, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void ver_RepOrden(String sql, String encabezado, String tipo) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/reportesOrdenesDeServicio/repOrden" + tipo + ".jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map<String, String> parametros = new HashMap<String, String>();
            parametros.clear();
            parametros.put("cliente", sql);
            parametros.put("encabezado", encabezado);
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());

            if (instancia.getRegimen().equals("")) {
                parametros.put("regimen", "");
            } else {
                parametros.put("regimen", "SinIva");
            }

            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void verRepAlertasCumpleanos(String sql, String tipo) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/reportes/ejemplo/repAlertasCumpleaños" + tipo + ".jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("consulta", sql);
            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void ver_RepOrden2(String sql, String encabezado, String tipo) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/reportesOrdenesDeServicio/repOrden_1" + tipo + ".jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map<String, String> parametros = new HashMap<String, String>();
            parametros.clear();
            parametros.put("cliente", sql);
            parametros.put("encabezado", encabezado);
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());

            if (instancia.getRegimen().equals("")) {
                parametros.put("regimen", "");
            } else {
                parametros.put("regimen", "SinIva");
            }

            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void ver_oServicio(String factura, String observaciones, boolean imprimir, String tipoVehiculo, String tipo1) {
        JasperReport reporte;

        System.out.println("Nombre impresion orden servicio: " + tipo1);
        
        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/impresionesOrdenServicio/" + tipo1 + ".jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("orden", factura);
            parametros.put("numOrden", factura.replace("OSERV-", ""));
            parametros.put("urlImagen", logo());
            parametros.put("observaciones", observaciones);
            parametros.put("info", instancia.getInformacionEmpresa());
            parametros.put("legal", instancia.getLegal());
            parametros.put("pie", instancia.getPie());
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());
            parametros.put("informacionLegalClick", informacionLegalClick);

            Iniciar ini = new Iniciar(parametros, reporte, imprimir, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void verInventario() {
        JasperReport reporte;
        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/reportes/ejemplo/inventario.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map<String, String> parametros = new HashMap();
            parametros.clear();

            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void verPago(String ingreso, String proveedor, String nombre, String valFactura, String comprobante, String letras, String abono, String actual, String anteriores, String banco,
            String tarjeta, String efectivo, String saldo, String fecha, String info, String tipo, String nc, String iva, String ica, String fuente, String otros) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/reportes/ejemplo/pago.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("factura", ingreso);
            parametros.put("cliente", proveedor);
            parametros.put("nombre", nombre);
            parametros.put("valFactura", valFactura);
            parametros.put("comprobante", comprobante);
            parametros.put("letras", letras);
            parametros.put("abono", abono);
            parametros.put("actual", actual);
            parametros.put("anteriores", anteriores);
            parametros.put("banco", banco);
            parametros.put("tarjeta", tarjeta);
            parametros.put("efectivo", efectivo);
            parametros.put("saldo", saldo);
            parametros.put("fecha", fecha);
            parametros.put("info", info);
            parametros.put("tipo", tipo);
            parametros.put("urlImagen", logo());
            parametros.put("iva", iva);
            parametros.put("ica", ica);
            parametros.put("fuente", fuente);
            parametros.put("otros", otros);

            Iniciar ini = new Iniciar(parametros, reporte, true, false, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void ver_RepPagos(String total, String tipo) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/reportesPagos/repPagos" + tipo + ".jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map<String, String> parametros = new HashMap();
            parametros.clear();
            parametros.put("total", total);
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());

            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    private InputStream logo() {
        try {
            return new FileInputStream(new File(logo));

        } catch (FileNotFoundException ex) {
            Logger.getLogger(iFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void verCertificadoMedico(String info, String id, String edad, String tipo) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/impresionesMedico/certificadoMedico" + tipo + ".jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("info", info);
            parametros.put("id", id);
            parametros.put("edad", edad);
            parametros.put("urlImagen", logo());
//            parametros.put("urlImagen", this.getClass().getResourceAsStream(locoCertificado));
            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void ver_Examen(String id, String info, String legal, String pie, String edad, String examen) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/impresionesLaboratorio/" + examen + ".jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("id", id);
            parametros.put("urlImagen", logo());
            parametros.put("info", info);
            parametros.put("legal", legal);
            parametros.put("pie", pie);
            parametros.put("edad", edad);
            parametros.put("fecha", metodosGenerales.fecha());
            parametros.put("informacionLegalClick", informacionLegalClick);
            parametros.put("firma", logo3);

            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void ver_Factura(String observaciones, String info, String legal, String tipo, String pie, String nRep, String facturaTerm,
            boolean imprimir, String titulo, String impresora, String verImpo, String verReten, String condicion, Boolean nd) {
        JasperReport reporte;

        String hora = "NO", orden = "NO";
        if (instancia.isHora()) {
            hora = "SI";
        } else {
            hora = "NO";
        }

        if (instancia.getConfiguraciones().isOrdenServicio()) {
            if (instancia.getConfiguraciones().isServicioAutomotor()) {
                orden = "SI";
            }
        }

        System.out.println("Nombre reporte: " + nRep);

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/impresionesFacturas/" + nRep + ".jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("factura", facturaTerm.replace("FACT-", ""));
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());
            parametros.put("numFactura", condicion);
            parametros.put("urlImagen", logo());
            parametros.put("observaciones", observaciones);
            parametros.put("info", info);
            parametros.put("legal", legal);
            parametros.put("tipoFact", tipo);
            parametros.put("pie", pie);
            parametros.put("ordenServicio", orden);
            parametros.put("titulo2", titulo);
            parametros.put("hora", hora);

            if (nd) {
                parametros.put("titulo", "Nota Debito No.");
            } else {
                parametros.put("titulo", instancia.getTituloFactura());
            }

            parametros.put("informacionLegalClick", informacionLegalClick);
            parametros.put("firma", logo3);
            parametros.put("impoconsumo", verImpo);
            parametros.put("retenciones", verReten);

            if (impresora.equalsIgnoreCase("Predeterminada")) {
                Iniciar ini = new Iniciar(parametros, reporte, imprimir, true, instancia);
                vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
                barra.show();
            } else {
                IniciarImpresion ini = new IniciarImpresion(parametros, reporte, imprimir, true, instancia, impresora);
                vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
                barra.show();
            }
        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void ver_Hospitalizacion(String IdHosp) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/impresionesVeterinario/hospitalizacion.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("usuario", instancia.getUsuario());
            parametros.put("id", IdHosp);
            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void ver_Indicador(String tipoIndicador, String sql) {
        JasperReport reporte;
        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/indicadores/" + tipoIndicador + ".jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("urlImagen", logo());
            parametros.put("sql", sql);
            parametros.put("info", instancia.getInformacionEmpresaCompleto());
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());
            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();
        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void ver_Comanda(String condicion, String factura, String observaciones, String facturaTerm, String mesa,
            Boolean previsualizar, String impresora, String vendedor) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/impresionesFacturas/comanda.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("factura", facturaTerm.replace("FACT-", ""));
            parametros.put("numFactura", factura);
            parametros.put("observaciones", observaciones);
            parametros.put("hora", metodosGenerales.fechaHora());
            parametros.put("vendedor", vendedor);
            parametros.put("mesa", mesa);
            parametros.put("sql", condicion);

//            Iniciar ini = new Iniciar(parametros, reporte, !previsualizar, true, instancia);
            IniciarImpresion ini = new IniciarImpresion(parametros, reporte, !previsualizar, true, instancia, impresora);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void ver_PrefacturaVenta(String condicion, String factura, String observaciones, String facturaTerm, String mesa, String info,
            BigDecimal propina, Boolean previsualizar, String impresora) {
        JasperReport reporte;

        String tipo = "";
        if (!instancia.getRegimen().equals("")) {
            tipo = "SinIva";
        }

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/impresionesFacturas/preFactura" + tipo + ".jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("factura", facturaTerm.replace("FACT-", ""));
            parametros.put("numFactura", factura);
            parametros.put("observaciones", observaciones);
            parametros.put("hora", metodosGenerales.fechaHora());
            parametros.put("info", info);
            parametros.put("sql", condicion);
            parametros.put("porcPropina", propina);
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());
//            Iniciar ini = new Iniciar(parametros, reporte, !previsualizar, true, instancia);
            IniciarImpresion ini = new IniciarImpresion(parametros, reporte, !previsualizar, true, instancia, impresora);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();
        } catch (JRException E) {
            System.out.println(E);
        }
    }

    private InputStream imagenInforme() {
        File FL = new File(imagenInforme);
        FileInputStream foto = null;

        try {
            foto = new FileInputStream(FL);
        } catch (Exception e) {
            return null;
        }

        return foto;
    }

    public void ver_informePaciente1(String id) {
        JasperReport reporte;
        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/impresionesMedico/informePaciente.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("ID", id);
            parametros.put("urlImagen", logo());
            parametros.put("usuario", instancia.getUsuarioLog().getNombre());
            parametros.put("firma", logo3);

            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();
        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void ver_informePaciente(String id) {

        InputStream logo = null;

        try {
            logo = imagenInforme();
        } catch (Exception e) {
        }

        JasperReport reporte;
        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/impresionesMedico/informePaciente.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("ID", id);
            parametros.put("urlImagen", logo());

            try {
                parametros.put("ImagenTercero", logo);
            } catch (Exception e) {
            }

            parametros.put("usuario", instancia.getUsuarioLog().getNombre());
            parametros.put("firma", logo3);

            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void abrirCajaRegistradora() {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/reportes/ejemplo/abrirCaja.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map parametros = new HashMap();
            parametros.clear();

            Iniciar ini = new Iniciar(parametros, reporte, true, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void ver_RecogidaParcial(String info, String fecha, String usuario, String terminal, String valor, String valorAntes) {
        JasperReport reporte;
        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/reportes/ejemplo/recogidaParcial.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("fecha", fecha);
            parametros.put("urlImagen", logo());
            parametros.put("usuario", usuario);
            parametros.put("info", info);
            parametros.put("terminal", terminal);
            parametros.put("valor", valor);
            parametros.put("valorAntes", valorAntes);

            Iniciar ini = new Iniciar(parametros, reporte, false, false, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void ver_ubicacion(String factura, boolean imprimir) {
        JasperReport reporte;
        System.out.println("factura " + factura);
        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/impresionesFacturas/ubicacion.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("factura", factura.replace("FACT-", ""));
            parametros.put("numFactura", factura);
            parametros.put("urlImagen", logo());

            Iniciar ini = new Iniciar(parametros, reporte, imprimir, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void verRepMedico(String sql, String tipo) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/impresionesMedico/repOrdenes" + tipo + ".jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map<String, String> parametros = new HashMap<String, String>();
            parametros.clear();
            parametros.put("cliente", sql);

            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void verHistoria(String dato, String edad) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/impresionesMedico/historia.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("id", dato);
            parametros.put("edad", edad);
            parametros.put("firma", logo3);
            parametros.put("usuario", instancia.getUsuarioLog().getNombre());
            parametros.put("urlImagen", logo());

            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void verRemision1(String dato, String edad) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/reportes/ejemplo/remision1.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("id", dato);
            parametros.put("edad", edad);
            parametros.put("firma", logo3);
            parametros.put("usuario", instancia.getUsuarioLog().getNombre());
            parametros.put("urlImagen", logo());

            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void verRemision(String dato, String edad) {
        JasperReport reporte;

        String firma = instancia.getSql().getUsuarioOrden(dato);
        logo3 = System.getProperty("user.dir") + "//imagenes//firmas//" + firma + ".jpg";

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/impresionesMedico/remision.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("id", dato);
            parametros.put("edad", edad);
            parametros.put("firma", logo3);
            parametros.put("usuario", firma);
            parametros.put("urlImagen", logo());

            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void ver_ReIngresos(String sql, String encabezado, String tipo) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/reportesCompras/repIngresos" + tipo + ".jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map<String, String> parametros = new HashMap<String, String>();
            parametros.clear();
            parametros.put("cliente", sql);
            parametros.put("encabezado", encabezado);
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());

            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void ver_RepIngresos2(String sql, String encabezado, String tipo) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/reportesCompras/repIngresos_1" + tipo + ".jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map<String, String> parametros = new HashMap<String, String>();
            parametros.clear();
            parametros.put("cliente", sql);
            parametros.put("encabezado", encabezado);
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());

            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void ver_RepOrdenCompra(String sql, String encabezado, String tipo) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/reportesCompras/repOrdenCompra" + tipo + ".jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map<String, String> parametros = new HashMap<String, String>();
            parametros.clear();
            parametros.put("cliente", sql);
            parametros.put("encabezado", encabezado);
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());

            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void verFormato(String vehiculo, int numero) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/reportes/ejemplo/formato" + numero + ".jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map<String, String> parametros = new HashMap<String, String>();
            parametros.clear();
            parametros.put("vehiculo", vehiculo);
            parametros.put("dia", metodosGenerales.dia());
            parametros.put("mes", metodosGenerales.mes());
            parametros.put("mesLetras", metodosGenerales.mesEnPalabra());
            parametros.put("anho", metodosGenerales.anho());

            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void verInforme(String id, int num) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/reportes/ejemplo/informe" + num + ".jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map<String, String> parametros = new HashMap<String, String>();
            parametros.clear();
            parametros.put("id", id);

            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void verFormato5(String[] datos) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/reportes/ejemplo/formato5.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map<String, String> parametros = new HashMap<String, String>();
            parametros.clear();
            parametros.put("vehiculo", datos[0]);
            parametros.put("dia", datos[1]);
            parametros.put("mes", datos[2]);
            parametros.put("anho", datos[3]);

            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void verHistorico(String sql, String tipo) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/reportesInventario/inventario" + tipo + ".jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map<String, String> parametros = new HashMap();
            parametros.clear();
            parametros.put("cliente", sql);
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());

            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();
        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void verInventarioDetallado(String sql, String tipo) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/reportesInventario/inventarioDetalle" + tipo + ".jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map<String, String> parametros = new HashMap();
            parametros.clear();
            parametros.put("cliente", sql);
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());

            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();
        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void verRepCuadre(String sql, String encabezado, String tipo) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/reportesCuadreCaja/repCuadreCaja" + tipo + ".jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map<String, String> parametros = new HashMap<String, String>();
            parametros.clear();
            parametros.put("cliente", sql);
            parametros.put("encabezado", encabezado);
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());

            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void verRepPropinas(String sql, String encabezado) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/reportesCuadreCaja/repPropinas.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("cliente", sql);
            parametros.put("encabezado", encabezado);
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());

            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void verRepBasesCuadre(String sql, String encabezado) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/reportesCuadreCaja/repBaseCaja.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("cliente", sql);
            parametros.put("encabezado", encabezado);
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());
//            parametros.put("50mil", mostradorBilletes(mil50));
//            parametros.put("20mil", mostradorBilletes(mil20));
//            parametros.put("10mil", mostradorBilletes(mil10));
//            parametros.put("5mil", mostradorBilletes(mil5));
//            parametros.put("2mil", mostradorBilletes(mil2));
//            parametros.put("1mil", mostradorBilletes(mil1));
//            parametros.put("Mil", mostradorBilletes(Mil));
//            parametros.put("500", mostradorBilletes(pesos500));
//            parametros.put("200", mostradorBilletes(pesos200));
//            parametros.put("100", mostradorBilletes(pesos100));
//            parametros.put("50", mostradorBilletes(pesos50));

            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void ver_AZ(String sql, String tipo, String mensaje) {
        JasperReport reporte;
        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/reportesVentas/repAZ" + tipo + ".jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map<String, String> parametros = new HashMap<String, String>();
            parametros.clear();
            parametros.put("cliente", sql);
            parametros.put("mensaje", mensaje);
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());
            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void ver_ResumenGerencial() {
        JasperReport reporte;
        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/reportesVentas/repResumenGerencial.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map<String, String> parametros = new HashMap<String, String>();
            parametros.clear();
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());

            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void ver_RepIvaVentas(String tipo, String encabezado) {
        JasperReport reporte;
        try {
            URL in = this.getClass().getResource("/reportesVentas/repIvas" + tipo + ".jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            Map<String, String> parametros = new HashMap<String, String>();
            parametros.clear();
            parametros.put("encabezado", encabezado);
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());
            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();
        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void ver_Interfase(String encabezado) {
        JasperReport reporte;
        try {
            URL in = this.getClass().getResource("/reportesContables/interfasexls.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            Map<String, String> parametros = new HashMap<String, String>();
            parametros.clear();
            parametros.put("encabezado", encabezado);
            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();
        } catch (JRException E) {
            System.out.println(E);
        }
    }

    private String sentenciaFacturacionDetallado(String condicion) {
        String sentencia = "SELECT bdfactura.fechaFactura AS fechaFactura, bdfactura.cliente AS cliente, bdterceros.nombre AS nombre, bdfactura.vendedor AS vendedor, "
                + "bdfactura.observacion AS observacion, bdproductos.Codigo AS Codigo, bdfactura.descripcion AS Descripcion, bdfactura.imei AS imei, "
                + "SUM(bdfactura.cant2) AS cantidad, bdfactura.lista AS lista, SUM(bdfactura.subtotal) AS subtotal, SUM(bdfactura.descuento) AS descuento, "
                + "bdfactura.porcIva AS porcIva, SUM(bdfactura.iva) AS iva, SUM(bdfactura.impoconsumo) AS impoconsumo, SUM(bdfactura.total) AS total, "
                + "SUM(bdfactura.utilidad) AS utilidad, bdfactura.subtotalGeneral AS subtotalGeneral, bdfactura.descuentoGeneral AS descuentoGeneral, bdfactura.otros AS otros, "
                + "bdfactura.rtFuente AS rtFuente, bdfactura.rtIva AS rtIva, bdfactura.ivaGeneral AS ivaGeneral, bdfactura.impuesto AS impuesto, bdfactura.porcImpo AS porcImpo, "
                + "bdfactura.impoGeneral AS impoGeneral, bdfactura.totalGeneral AS totalGeneral, bdfactura.efectivoGeneral AS efectivoGeneral, "
                + "bdfactura.targetaGeneral AS targetaGeneral, bdfactura.chequeGeneral AS chequeGeneral, bdfactura.ncGeneral AS ncGeneral, bdfactura.tercero AS tercero, "
                + "bdfactura.utilidad1 AS utilidad1, bdfactura.idFactura AS idFactura, bdfactura.factura AS id2, bdfactura.credito AS credito, bdfactura.anulada AS anulada, "
                + "bdterceros.id AS Id, bdproductos.grupo AS Grupo, bdproductos.idSistema AS producto, bdfactura.hora AS hora, bdfactura.tarjetaCredito AS tarjetaCredito, "
                + "bdfactura.sisteCredito AS sisteCredito "
                + "FROM ((bdfactura LEFT JOIN bdproductos ON ((bdfactura.producto = bdproductos.idSistema))) LEFT JOIN bdterceros ON ((bdfactura.cliente = bdterceros.idSistema))) "
                + condicion + " GROUP BY bdfactura.fechaFactura, bdfactura.cliente, bdterceros.nombre, bdfactura.vendedor, bdfactura.observacion, bdproductos.Codigo, bdfactura.imei, "
                + "bdfactura.lista, bdfactura.porcIva, bdfactura.subtotalGeneral, bdfactura.descuentoGeneral, bdfactura.otros, bdfactura.rtFuente, bdfactura.rtIva, "
                + "bdfactura.ivaGeneral, bdfactura.impuesto, bdfactura.totalGeneral, bdfactura.efectivoGeneral, bdfactura.targetaGeneral, bdfactura.chequeGeneral, "
                + "bdfactura.ncGeneral, bdfactura.impoGeneral, bdfactura.porcImpo, bdfactura.tercero, bdfactura.utilidad1, bdfactura.idFactura, bdfactura.factura, "
                + "bdfactura.credito, bdfactura.anulada, bdterceros.id, bdproductos.grupo, bdproductos.idSistema, bdfactura.hora, bdfactura.tarjetaCredito, "
                + "bdfactura.sisteCredito ORDER BY bdfactura.Id";
        return sentencia;
    }

    private String sentenciaFacturacionTotalizado(String condicion) {
        String sentencia = " SELECT bdfactura.idFactura AS idFactura, CAST(SUBSTR(bdfactura.idFactura,6,100) AS SIGNED) AS ordenId, bdterceros.nombre AS nombre, "
                + "bdterceros.id AS cliente, bdfactura.vendedor AS vendedor, bdfactura.red AS red, bdfactura.fechaVencimiento AS fechaVencimiento, "
                + "bdfactura.efectivoGeneral AS efectivoGeneral, bdfactura.ncGeneral AS ncGeneral, bdfactura.chequeGeneral AS chequeGeneral, "
                + "bdfactura.targetaGeneral AS targetaGeneral, bdfactura.totalGeneral AS totalGeneral, bdfactura.descuentoGeneral AS descuentoGeneral, "
                + "bdfactura.ivaGeneral AS ivaGeneral, bdfactura.subtotalGeneral AS subtotalGeneral, bdfactura.comprobante AS comprobante, bdfactura.cotizacion AS cotizacion, "
                + "bdfactura.anulada AS anulada, bdfactura.anula AS anula, bdfactura.credito AS credito, bdfactura.cxc AS cxc, bdfactura.usuario AS usuario, "
                + "bdfactura.rtIva AS rtIva, bdfactura.rtIca AS rtIca, bdfactura.rtFuente AS rtFuente, bdfactura.otros AS otros, bdfactura.observacion AS observacion, "
                + "bdfactura.anulada1 AS anulada1, bdfactura.anula1 AS anula1, bdfactura.credito1 AS credito1, bdfactura.cxc1 AS cxc1, bdfactura.usuario1 AS usuario1,"
                + "bdfactura.fechaAlerta AS fechaAlerta, bdfactura.terminal AS terminal, bdfactura.estadoGeneral AS estadoGeneral, bdfactura.estado2 AS estado2, "
                + "bdfactura.devuelta AS devuelta, bdfactura.factura AS factura, bdfactura.resolucion AS resolucion, bdfactura.fechaAnulacion AS fechaAnulacion, "
                + "bdfactura.cuadreAnulacion  AS cuadreAnulacion, bdfactura.usuarioAnula AS usuarioAnula, bdfactura.placa AS placa, bdfactura.garantia AS garantia, "
                + "bdfactura.diasGarantia AS diasGarantia, bdfactura.rango AS rango, bdfactura.terminos AS terminos, bdfactura.notaAnulacion AS notaAnulacion, "
                + "bdfactura.conseMesa AS conseMesa, bdfactura.factura AS id2, bdfactura.copago AS copago, bdprestamo.cuotaInicial AS cuotaInicial2, "
                + "CAST(bdfactura.fechaFactura AS DATE) AS fechaFactura, bdfactura.turno AS turno, bdfactura.impuesto AS impuesto,  bdterceros.idSistema AS idSistema, "
                + "CAST(bdfactura.fechaFactura AS DATE) AS fechaFactura1, bdfactura.impoGeneral AS impoconsumo, bdfactura.tarjetaCredito AS tarjetaCredito, "
                + "bdfactura.franquisia AS franquisia, bdfactura.hora AS hora, bdfactura.valorComision AS valorComision, bdfactura.totalPropina AS totalPropina, "
                + "bdfactura.sisteCredito AS sisteCredito, bdfactura.bodega AS bodega "
                + "FROM ((bdfactura LEFT JOIN bdprestamo ON ((bdfactura.idFactura = bdprestamo.factura))) LEFT JOIN bdterceros ON ((bdfactura.cliente = bdterceros.idSistema))) "
                + condicion + " GROUP BY bdfactura.idFactura, CAST(SUBSTR(bdfactura.idFactura,6,100)AS SIGNED), bdterceros.nombre, bdterceros.id, bdfactura.vendedor, bdfactura.red, "
                + "bdfactura.fechaVencimiento, bdfactura.efectivoGeneral,bdfactura.ncGeneral, bdfactura.chequeGeneral, bdfactura.targetaGeneral, bdfactura.totalGeneral, "
                + "bdfactura.descuentoGeneral, bdfactura.ivaGeneral, bdfactura.subtotalGeneral, bdfactura.comprobante, bdfactura.cotizacion, bdfactura.anulada, bdfactura.anula,"
                + "bdfactura.credito, bdfactura.cxc, bdfactura.usuario, bdfactura.rtIva, bdfactura.rtIca, bdfactura.rtFuente, bdfactura.otros, bdfactura.anulada1, bdfactura.anula1,"
                + "bdfactura.credito1, bdfactura.cxc1, bdfactura.usuario1, bdfactura.fechaAlerta, bdfactura.terminal, bdfactura.estadoGeneral, bdfactura.estado2, bdfactura.devuelta,"
                + "bdfactura.resolucion, bdfactura.fechaAnulacion, bdfactura.cuadreAnulacion, bdfactura.usuarioAnula, bdfactura.placa, bdfactura.garantia, bdfactura.diasGarantia,"
                + "bdfactura.rango, bdfactura.conseMesa, bdfactura.factura, bdfactura.copago, bdprestamo.cuotaInicial, bdfactura.turno, bdfactura.impuesto, bdterceros.idSistema, "
                + "CAST(FORMAT(bdfactura.fechaFactura, 'yyyy/mm/dd')AS DATE), bdfactura.impoGeneral, CAST(FORMAT(bdfactura.fechaFactura,'yyyy/mm/dd')AS DATE), "
                + "bdfactura.tarjetaCredito, bdfactura.franquisia, bdfactura.hora, bdfactura.valorComision, bdfactura.totalPropina, bdfactura.sisteCredito, bdfactura.bodega "
                + "ORDER BY CAST(SUBSTR(bdfactura.idFactura,6,100)AS SIGNED)";
        return sentencia;
    }

    public void ver_RepVentas(String sql, String encabezado, String tipo, String encabezado2) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/reportesVentas/repVentasDetalle" + tipo + ".jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map<String, String> parametros = new HashMap<String, String>();
            parametros.clear();

            String sqlCompleto = sentenciaFacturacionDetallado(sql);
            parametros.put("cliente", sqlCompleto);
            parametros.put("encabezado", encabezado);
            parametros.put("encabezado2", encabezado2);
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());

            if (instancia.getRegimen().equals("")) {
                parametros.put("regimen", "");
            } else {
                parametros.put("regimen", "SinIva");
            }

            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
            if (tipo.equals("")) {
                metodos.msgError(null, "Hubo un problema al generar el reporte, seleccione un rango de menor tamaño de fecha o genere este como hoja de calculo.");
                return;
            }
        }
    }

    public void ver_RepVentas2(String sql, String encabezado, String tipo, String encabezado2) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/reportesVentas/repVentasTotalizado" + tipo + ".jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map<String, String> parametros = new HashMap<String, String>();
            parametros.clear();

            String sqlCompleto = sentenciaFacturacionTotalizado(sql);
            System.out.println("yo no soy brujo hermano: " + sqlCompleto);

            parametros.put("cliente", sqlCompleto);
            parametros.put("encabezado", encabezado);
            parametros.put("encabezado2", encabezado2);
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());

            if (instancia.getRegimen().equals("")) {
                parametros.put("regimen", "");
            } else {
                parametros.put("regimen", "SinIva");
            }

            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();
        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void verPrestamo(String contrato) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/reportes/ejemplo/prestamo.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map<String, String> parametros = new HashMap<String, String>();
            parametros.clear();
            parametros.put("id", contrato);
            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void verEstadoDeCuenta(String contrato) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/reportes/ejemplo/estadoDeCuenta.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map<String, String> parametros = new HashMap<String, String>();
            parametros.clear();
            parametros.put("id", contrato);
            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void verCertificado(String anombre, String cliente, String documento,
            String cuotas, String valor, String ciudad, String total, String saldo, String deuda) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/reportes/ejemplo/certificadoDeuda.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map<String, String> parametros = new HashMap<String, String>();
            parametros.clear();
            parametros.put("anombre", anombre);
            parametros.put("cliente", cliente);
            parametros.put("documento", documento);
            parametros.put("cuotas", cuotas);
            parametros.put("valor", valor);
            parametros.put("ciudad", ciudad);
            parametros.put("total", total);
            parametros.put("saldo", saldo);
            parametros.put("deuda", deuda);

            Iniciar ini = new Iniciar(parametros, reporte, false, false, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void verCertificado2(String anombre, String cliente, String documento,
            String cuotas, String valor, String ciudad, String total, String saldo, String deuda) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/reportes/ejemplo/certificadoPazYSalvo.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map<String, String> parametros = new HashMap<String, String>();
            parametros.clear();
            parametros.put("anombre", anombre);
            parametros.put("cliente", cliente);
            parametros.put("documento", documento);
            parametros.put("cuotas", cuotas);
            parametros.put("valor", valor);
            parametros.put("ciudad", ciudad);
            parametros.put("total", total);
            parametros.put("saldo", saldo);
            parametros.put("deuda", deuda);

            Iniciar ini = new Iniciar(parametros, reporte, false, false, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void verAbono(String abono, String tipo, String info) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/impresionesAbonos/abonoNuevo" + Instancias.getInstancias().getTipoImpresion() + ".jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("abono", abono);
            parametros.put("info", info);
            parametros.put("tipo", tipo);
            parametros.put("urlImagen", logo());
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());

            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void verAbonoGeneral(String abono, String tipo, String info) {
        JasperReport reporte;
        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/impresionesAbonos/abonoNuevo" + instancia.getTipoImpresion() + ".jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("abono", abono);
            parametros.put("info", info);
            parametros.put("tipo", tipo);
            parametros.put("urlImagen", logo());
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());

            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void verAbonoCredito(String abono, String tipo, String info) {
        JasperReport reporte;
        try {
            //direccion del archivo JASPER
//            URL in = this.getClass().getResource("/reportes/ejemplo/abonoGeneral" + Instancias.getInstancias().getTipoImpresion() + ".jasper");
            URL in = this.getClass().getResource("/reportes/ejemplo/abonoNuevo2" + instancia.getTipoImpresion() + ".jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("abono", abono);
            parametros.put("info", info);
            parametros.put("tipo", tipo);
            parametros.put("urlImagen", logo());

            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void verAbonoGeneral1(String abono, String tipo, String info) {
        JasperReport reporte;
        try {
            //direccion del archivo JASPER
//            URL in = this.getClass().getResource("/reportes/ejemplo/abonoGeneral" + Instancias.getInstancias().getTipoImpresion() + ".jasper");
            URL in = this.getClass().getResource("/reportes/ejemplo/abonoNuevo1.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("abono", abono);
            parametros.put("info", info);
            parametros.put("tipo", tipo);
            parametros.put("urlImagen", logo());

            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void verCuadreFiscal(String factura, String info) {
        JasperReport reporte;
        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/impresionesCuadreCaja/cuadreFiscal.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("idCuadre", factura);
            parametros.put("info", info);
            parametros.put("urlImagen", logo());
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());

            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void verCuadreCaja(String factura, String tipo, String info) {
        JasperReport reporte;

        String hora = "NO";
        if (instancia.isHora()) {
            hora = "SI";
        } else {
            hora = "NO";
        }

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/impresionesCuadreCaja/cuadreCaja" + tipo + ".jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("idCuadre", factura);
            parametros.put("hora", hora);
            parametros.put("urlImagen", logo());
            parametros.put("info", info);
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());
            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void verBase(String condicion, String info) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/impresionesCuadreCaja/base.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("cliente", condicion);
            parametros.put("urlImagen", logo());
            parametros.put("info", info);
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());
            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void verCaja(String fecha, String cajero, String hora, String sFact, String sEfect, String sTarj, String sCheq, String sNc, String sAboC, String sTotal, String uFact, String uEfect, String uTarj, String uCheq, String uNc, String uAboC, String uTotal, String difer, String estCuad, String gastos, String recogida, String base) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/reportes/ejemplo/caja" + Instancias.getInstancias().getTipoImpresion() + ".jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("fecha", fecha);
            parametros.put("cajero", cajero);
            parametros.put("hora", hora);
            parametros.put("sFact", sFact);
            parametros.put("sEfect", sEfect);
            parametros.put("sTarj", sTarj);
            parametros.put("sCheq", sCheq);
            parametros.put("sNc", sNc);
            parametros.put("sAboC", sAboC);
            parametros.put("sTotal", sTotal);
            parametros.put("uFact", uFact);
            parametros.put("uEfect", uEfect);
            parametros.put("gastos", uTarj);
            parametros.put("uCheq", uCheq);
            parametros.put("uNc", uNc);
            parametros.put("uAboC", uAboC);
            parametros.put("uTotal", uTotal);
            parametros.put("difer", difer);
            parametros.put("estCuad", estCuad);
            parametros.put("uTarj", gastos);
            parametros.put("recogida", recogida);
            parametros.put("base", base);

            Iniciar ini = new Iniciar(parametros, reporte, false, false, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void ver_RepCotizas(String sql, String encabezado, String tipo) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/reportesCotizaciones/repCotizaciones" + tipo + ".jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap 
            Map<String, String> parametros = new HashMap<String, String>();
            parametros.clear();
            parametros.put("cliente", sql);
            parametros.put("encabezado", encabezado);
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());

            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void ver_RepEgresos(String sql, String encabezado, String tipo) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/reportesTesoreria/repEgresos" + tipo + ".jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map<String, String> parametros = new HashMap<String, String>();
            parametros.clear();
            parametros.put("cliente", sql);
            parametros.put("encabezado", encabezado);
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());

            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();
        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void ver_RepBancos(String sql, String encabezado, String tipo) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/reportesTesoreria/repBancos" + tipo + ".jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map<String, String> parametros = new HashMap<String, String>();
            parametros.clear();
            parametros.put("cliente", sql);
            parametros.put("encabezado", encabezado);
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());

            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void ver_RepAdjuntosBancos(String sql, String encabezado) {
        JasperReport reporte;

        String ruta = System.getProperty("user.dir") + "\\imagenes\\recibos\\";

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/reportesTesoreria/repArchivosAdjuntos.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map<String, String> parametros = new HashMap<String, String>();
            parametros.clear();
            parametros.put("cliente", sql);
            parametros.put("encabezado", encabezado);
            parametros.put("ruta", ruta);
            parametros.put("informacionLegalClick", informacionLegalClick);
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());

            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void ver_RepAbonosCxp(String tipo, String sql, String encabezado) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/reportesAbonos/repAbonosCxp" + tipo + ".jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map<String, String> parametros = new HashMap();
            parametros.clear();
            parametros.put("encabezado", encabezado);
            parametros.put("sql", sql);
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());

            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void ver_RepAbonos(String tipo, String sql, String encabezado) {
        JasperReport reporte;

        String conseManual = "";
        if ((Boolean) datos[57]) {
            conseManual = "SI";
        } else {
            conseManual = "NO";
        }

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/reportesAbonos/repAbonos" + tipo + ".jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map<String, String> parametros = new HashMap();
            parametros.clear();
            parametros.put("encabezado", encabezado);
            parametros.put("sql", sql);
            parametros.put("conseManual", conseManual);
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());

            if (instancia.getRegimen().equals("")) {
                parametros.put("regimen", "");
            } else {
                parametros.put("regimen", "SinIva");
            }

            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void ver_RepAnulas(String sql, String encabezado, String tipo) {
        JasperReport reporte;
        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/reportesVentas/repAnuladas" + tipo + ".jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map<String, String> parametros = new HashMap<String, String>();
            parametros.clear();
            parametros.put("cliente", sql);
            parametros.put("encabezado", encabezado);
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());

            if (instancia.getRegimen().equals("")) {
                parametros.put("regimen", "");
            } else {
                parametros.put("regimen", "SinIva");
            }

            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void ver_RepMascotas(String sql) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/reportesVeterinario/repMascotas.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map<String, String> parametros = new HashMap<String, String>();
            parametros.clear();
            parametros.put("cliente", sql);
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());

            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void ver_RepVacunas(String sql) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/reportes/ejemplo/vacunas.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map<String, String> parametros = new HashMap<String, String>();
            parametros.clear();
            parametros.put("cliente", sql);

            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void ver_RepPeluqueria(String sql) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/reportes/ejemplo/repPeluqueria.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map<String, String> parametros = new HashMap<String, String>();
            parametros.clear();
            parametros.put("cliente", sql);

            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void ver_RepGuarderia(String sql) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/reportesVeterinario/repGuarderia.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map<String, String> parametros = new HashMap<String, String>();
            parametros.clear();
            parametros.put("cliente", sql);
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());

            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void ver_RepHospitalizacion(String sql) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/reportesVeterinario/repHospitalizacion.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map<String, String> parametros = new HashMap<String, String>();
            parametros.clear();
            parametros.put("cliente", sql);
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());
            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void ver_RepProductos(String sql, String encabezado, String tipo) {
        JasperReport reporte;
        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/reportesProductos/repProductos" + tipo + ".jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map<String, String> parametros = new HashMap<String, String>();
            parametros.clear();
            parametros.put("cliente", sql);
            parametros.put("encabezado", encabezado);
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());

            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void ver_RepProductosComunSumandoIva(String sql, String encabezado, String tipo) {
        JasperReport reporte;
        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/reportesProductos/repProductosComunDividiendoIva" + tipo + ".jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map<String, String> parametros = new HashMap<String, String>();
            parametros.clear();
            parametros.put("cliente", sql);
            parametros.put("encabezado", encabezado);
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());

            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void ver_RepProductosComunDiviendoIva(String sql, String encabezado, String tipo) {
        JasperReport reporte;
        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/reportesProductos/repProductosComun" + tipo + ".jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map<String, String> parametros = new HashMap<String, String>();
            parametros.clear();
            parametros.put("cliente", sql);
            parametros.put("encabezado", encabezado);
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());

            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void ver_RepEmpleados(String consulta, String tipo) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/reportes/terceros/repEmpleados" + tipo + ".jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map<String, String> parametros = new HashMap();
            parametros.clear();
            parametros.put("cliente", consulta);
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());

            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void ver_RepClientes(String consulta, String tipo) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/reportes/terceros/repClientes" + tipo + ".jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map<String, String> parametros = new HashMap();
            parametros.clear();
            parametros.put("cliente", consulta);

            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void ver_RepReferidos(String sql, String encabezado, String tipo) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/reportes/terceros/repReferidos" + tipo + ".jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map<String, String> parametros = new HashMap();
            parametros.clear();
            parametros.put("cliente", sql);
            parametros.put("encabezado", encabezado);

            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void ver_RepCarteraCuotas(String total, String tipo) {
        JasperReport reporte;
        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/reportes/ejemplo/repCarteraCuotas" + tipo + ".jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map<String, String> parametros = new HashMap();
            parametros.clear();
            parametros.put("total", total);

            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void ver_Repcartera(String total, String tipo, String tipoRep) {
        JasperReport reporte;

        String conseManual = "";

        if ((Boolean) datos[57]) {
            conseManual = "SI";
        } else {
            conseManual = "NO";
        }

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/reportesCartera/" + tipoRep + tipo + ".jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map<String, String> parametros = new HashMap();
            parametros.clear();
            parametros.put("total", total);
            parametros.put("conseManual", conseManual);
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());

            if (instancia.getRegimen().equals("")) {
                parametros.put("regimen", "");
            } else {
                parametros.put("regimen", "SinIva");
            }

            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void ver_RepNC(String tipo, String sql, String enca) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/reportesNotasCredito/repNC" + tipo + ".jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map<String, String> parametros = new HashMap();
            parametros.clear();
            parametros.put("cliente", sql);
            parametros.put("enca", enca);
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());

            if (instancia.getRegimen().equals("")) {
                parametros.put("regimen", "");
            } else {
                parametros.put("regimen", "SinIva");
            }
            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void ver_RepTraslados(String sql, String encabezado, String tipo) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/reportesPrestamo/repTraslados" + tipo + ".jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map<String, String> parametros = new HashMap<String, String>();
            parametros.clear();
            parametros.put("cliente", sql);
            parametros.put("encabezado", encabezado);
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());

            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void ver_RepTrasladosInternos(String sql, String encabezado, String tipo) {
        JasperReport reporte;
        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/reportesTrasladosInternos/repTraslados" + tipo + ".jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map<String, String> parametros = new HashMap<String, String>();
            parametros.clear();
            parametros.put("cliente", sql);
            parametros.put("encabezado", encabezado);
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());

            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();
        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void ver_RepAjuste(String sql, String encabezado, Boolean totalizar) {
        JasperReport reporte;

        String resp = "No";
        if (totalizar) {
            resp = "Si";
        }

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/reportesAjustes/repAjuste.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map<String, String> parametros = new HashMap<String, String>();
            parametros.clear();
            parametros.put("cliente", sql);
            parametros.put("encabezado", encabezado);
            parametros.put("totalizar", resp);
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());

            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void ver_Cotiza(String factura, String observaciones, String info, String legal, String nRep, boolean imprimir) {
        JasperReport reporte;

        System.out.println("tipo cotizante a imprimir: " + nRep);

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/impresionesCotizaciones/" + nRep + ".jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("factura", factura.replace("COTI-", ""));
            parametros.put("numFactura", factura);
            parametros.put("urlImagen", logo());
            parametros.put("info", info);
            parametros.put("observaciones", observaciones);
            parametros.put("legal", legal);
            parametros.put("informacionLegalClick", informacionLegalClick);
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());

            Iniciar ini = new Iniciar(parametros, reporte, imprimir, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();
        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void ver_Armado(String factura, String tipo) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/impresionesArmado/costeo" + tipo + ".jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map<String, String> parametros = new HashMap<String, String>();
            parametros.clear();
            parametros.put("documento", factura.replace("CST-", ""));
            parametros.put("numFactura", factura);
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());

            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void verIngresoDetalle(String factura) {
        JasperReport reporte;
        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/impresionesIngresos/ingresoDetalle.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map<String, String> parametros = new HashMap<String, String>();
            parametros.clear();
            parametros.put("numFactura", factura);
            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void verIngreso(String factura, String tipoCompra) {
        JasperReport reporte;

        if (tipoCompra.equals("ingreso")) {
            tipoCompra = "Ingreso";
        } else {
            tipoCompra = "Orden Compra";
        }

        String url = Instancias.getInstancias().getTipoImpresion();

        if (instancia.getConfiguraciones().getTipoImpresion().equals("Sin-Codigo")) {
            url = url + "1";
        }

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/impresionesIngresos/ingreso" + url + ".jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map<String, String> parametros = new HashMap<String, String>();
            parametros.clear();
            if (tipoCompra.equals("Ingreso")) {
                parametros.put("factura", factura.replace("ING-", ""));
            } else {
                parametros.put("factura", factura.replace("ORDENCOMPRA-", ""));
            }

            parametros.put("numFactura", factura);
            parametros.put("tipoCompra", tipoCompra);
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());

            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void verIngreso(String factura, String tipoCompra, String tipo) {
        JasperReport reporte;

        if (tipoCompra.equals("ingreso")) {
            tipoCompra = "Ingreso";
        } else {
            tipoCompra = "Orden Compra";
        }

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/impresionesIngresos/ingreso" + tipo + ".jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map<String, String> parametros = new HashMap<String, String>();
            parametros.clear();
            if (tipoCompra.equals("Ingreso")) {
                parametros.put("factura", factura.replace("ING-", ""));
            } else {
                parametros.put("factura", factura.replace("ORDENCOMPRA-", ""));
            }

            parametros.put("numFactura", factura);
            parametros.put("tipoCompra", tipoCompra);
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());

            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void verAjustes(String factura, String tipo) {
        JasperReport reporte;
        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/impresionesTraslados/" + tipo + ".jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map<String, String> parametros = new HashMap<String, String>();
            parametros.clear();
            parametros.put("factura", factura.replace("TRAS-", ""));
            parametros.put("numFactura", factura);
            parametros.put("info", instancia.getInformacionEmpresa());
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());

            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void verTraslados(String factura, String tipo) {
        JasperReport reporte;
        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/impresionesTraslados/" + tipo + ".jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map<String, String> parametros = new HashMap<String, String>();
            parametros.clear();
            parametros.put("factura", factura.replace("TRASINT-", ""));
            parametros.put("numFactura", factura);
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());
            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();
        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void verPrestamos(String factura, String tipo) {

        String serial = "NO";
        if (instancia.getConfiguraciones().isProductosDetallados()) {
            serial = "SI";
        }

        JasperReport reporte;
        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/impresionesTraslados/" + tipo + ".jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map<String, String> parametros = new HashMap<String, String>();
            parametros.clear();
            parametros.put("factura", factura.replace("TRASB-", ""));
            parametros.put("numFactura", factura);
            parametros.put("serial", serial);
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());
            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();
        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void ver_Nc(String factura, String observaciones, String info) {
        JasperReport reporte;
        try {

            System.out.println("lady: " + Instancias.getInstancias().getTipoImpresion());
            System.out.println("lady: " + factura);

            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/impresionesNotasCredito/nc" + Instancias.getInstancias().getTipoImpresion() + ".jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("factura", factura.replace("NC-", ""));
            parametros.put("numFactura", factura);
            parametros.put("observaciones", observaciones);
            if (instancia.getRegimen().equals("")) {
                parametros.put("regimen", "");
            } else {
                parametros.put("regimen", "SinIva");
            }
            parametros.put("urlImagen", logo());
            parametros.put("info", info);
            parametros.put("informacionLegalClick", informacionLegalClick);
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());

            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void ver_RepProdImagenes(String sql, String info, Boolean imprimir) {
        JasperReport reporte;
        String ruta = System.getProperty("user.dir") + "\\imagenes\\productos\\";
        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/reportesInventario/repProdImagenes.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("cliente", sql);
            parametros.put("urlImagen", logo());
            parametros.put("ruta", ruta);
            parametros.put("info", info);
            parametros.put("informacionLegalClick", informacionLegalClick);
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());

            Iniciar ini = new Iniciar(parametros, reporte, imprimir, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void ver_RepClienteVentaProd(String factura, String info, Boolean imprimir, String sql) {
        JasperReport reporte;
        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/reportesInventario/repClienteVentaProd.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("factura", factura.replace("DETALLE-", ""));
            parametros.put("numFactura", factura);
            parametros.put("urlImagen", logo());
            parametros.put("sqlFecha", sql);
            parametros.put("info", info);
            parametros.put("informacionLegalClick", informacionLegalClick);
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());

            Iniciar ini = new Iniciar(parametros, reporte, imprimir, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void ver_Egreso(String factura, String info, String letras, Boolean imprimir, String tipo) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/impresionesEgresos/egreso" + tipo + ".jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("factura", factura.replace("EGR-", ""));
            parametros.put("numFactura", factura);
            parametros.put("urlImagen", logo());
            parametros.put("info", info);
            parametros.put("letras", letras);
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());

            Iniciar ini = new Iniciar(parametros, reporte, imprimir, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void verTirilla1(String info, String placa, String fecha, String hora) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/reportesParqueadero/tirilla1.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("info", info);
            parametros.put("placa", placa);
            parametros.put("fecha", fecha);
            parametros.put("hora", hora);
            parametros.put("informacionLegalClick", informacionLegalClick);
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());

            Iniciar ini = new Iniciar(parametros, reporte, true, false, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void verTirilla2(String info, String placa, String fecha, String hora, String fecha2, String hora2, String factura, String tiempo, String cobrar, String valHora, String tipo, String total) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/reportes/ejemplo/tirilla2.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("info", info);
            parametros.put("placa", placa);
            parametros.put("fecha", fecha);
            parametros.put("hora", hora);
            parametros.put("fecha2", fecha2);
            parametros.put("hora2", hora2);
            parametros.put("factura", factura);
            parametros.put("tiempo", tiempo);
            parametros.put("cobrar", cobrar);
            parametros.put("valHora", valHora);
            parametros.put("tipo", tipo);
            parametros.put("total", total);
            parametros.put("informacionLegalClick", informacionLegalClick);

            Iniciar ini = new Iniciar(parametros, reporte, true, false, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void verOrdenServicio(String numero) {
        JasperReport reporte;
        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/impresionesMedico/ordenServicio.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("numero", numero);
            parametros.put("informacionLegalClick", informacionLegalClick);
            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();
        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void verFormulaMedica(String numero, String info, String usuario, String tipo) {
        JasperReport reporte;
        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/impresionesMedico/formulaMedica" + tipo + ".jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("numero", numero);
            parametros.put("urlImagen", logo());
            parametros.put("info", info);
            parametros.put("firma", System.getProperty("user.dir") + "//imagenes//firmas//" + usuario + ".jpg");
            parametros.put("usuario", instancia.getSql().getNombreEmpleadoUsuario(usuario));
            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();
        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void verAyudaDiagnostica(String numero, String info, String usuario, String tipo) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/impresionesMedico/ayudaDiagnostico" + tipo + ".jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("numero", numero);
            parametros.put("urlImagen", logo());
            parametros.put("info", info);
            parametros.put("firma", System.getProperty("user.dir") + "//imagenes//firmas//" + usuario + ".jpg");
            parametros.put("usuario", instancia.getSql().getNombreEmpleadoUsuario(usuario));
            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void verIncapacidad(String numero, String info, String usuario, String tipo) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/impresionesMedico/incapacidad" + tipo + ".jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("numero", numero);
            parametros.put("urlImagen", logo());
            parametros.put("info", info);
            parametros.put("firma", System.getProperty("user.dir") + "//imagenes//firmas//" + usuario + ".jpg");
            parametros.put("usuario", instancia.getSql().getNombreEmpleadoUsuario(usuario));
            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();
        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void verRemision(String numero, String info, String usuario, String tipo) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/impresionesMedico/remisionMedico" + tipo + ".jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("numero", numero);
            parametros.put("urlImagen", logo());
            parametros.put("info", info);
            parametros.put("firma", System.getProperty("user.dir") + "//imagenes//firmas//" + usuario + ".jpg");
            parametros.put("usuario", instancia.getSql().getNombreEmpleadoUsuario(usuario));
            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();
        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void verContraremision(String numero, String info, String usuario, String tipo) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/impresionesMedico/contraremisionMedico" + tipo + ".jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("numero", numero);
            parametros.put("urlImagen", logo());
            parametros.put("info", info);
            parametros.put("firma", System.getProperty("user.dir") + "//imagenes//firmas//" + usuario + ".jpg");
            parametros.put("usuario", instancia.getSql().getNombreEmpleadoUsuario(usuario));
            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();
        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void verEcografiaMamaria(String numero, String info, String id, String tipo, String nombre, String sexo, String estado) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/reportes/ejemplo/ecografiaMamaria.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("numero", numero);
            parametros.put("id", id);
            parametros.put("tipo", tipo);
            parametros.put("nombre", nombre);
            parametros.put("sexo", sexo);
            parametros.put("estado", estado);
            parametros.put("fecha", metodosGenerales.fecha());
            parametros.put("urlImagen", logo());
            parametros.put("info", info);
            parametros.put("usuario", instancia.getUsuarioLog().getNombre());

            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void verDispositivo(String numero, String info, String id, String tipo, String nombre, String sexo, String estado) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/reportes/ejemplo/dispositivo.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("numero", numero);
            parametros.put("id", id);
            parametros.put("tipo", tipo);
            parametros.put("nombre", nombre);
            parametros.put("sexo", sexo);
            parametros.put("estado", estado);
            parametros.put("fecha", metodosGenerales.fecha());
            parametros.put("urlImagen", logo());
            parametros.put("info", info);
            parametros.put("usuario", instancia.getUsuarioLog().getNombre());

            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void verBiopsia(String numero, String info, String id, String tipo, String nombre, String sexo, String estado, String tRep) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/reportes/ejemplo/" + tRep + ".jasper");

            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("numero", numero);
            parametros.put("id", id);
            parametros.put("tipo", tipo);
            parametros.put("nombre", nombre);
            parametros.put("sexo", sexo);
            parametros.put("estado", estado);
            parametros.put("fecha", metodosGenerales.fecha());
            parametros.put("urlImagen", logo());
            parametros.put("info", info);
            parametros.put("usuario", instancia.getUsuarioLog().getNombre());

            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void verColposcopia(String numero, String info, String id, String tipo, String nombre, String sexo, String estado) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/reportes/ejemplo/colposcopia.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("numero", numero);
            parametros.put("id", id);
            parametros.put("tipo", tipo);
            parametros.put("nombre", nombre);
            parametros.put("sexo", sexo);
            parametros.put("estado", estado);
            parametros.put("fecha", metodosGenerales.fecha());
            parametros.put("urlImagen", logo());
            parametros.put("urlImagen2", logo());
            parametros.put("info", info);
            parametros.put("usuario", instancia.getUsuarioLog().getNombre());

            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);

        }
    }

    /* OFTALMOLOGIA */
    public void ver_HojaIngreso(String Id, boolean imprimir) {
        JasperReport reporte;
        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/impresionesOftalmologia/hojaIngreso.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("Id", Id);
            parametros.put("info", instancia.getInformacionEmpresa());
            parametros.put("urlImagen", this.getClass().getResourceAsStream(logo));
            Iniciar ini = new Iniciar(parametros, reporte, imprimir, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void ver_Biometria(String uno, String dos, String tres, String cuatro, String cinco, String seis, String siete, String ocho, String nueve, String diez,
            String once, String doce, String trece, String catorce, String quince, String uno1, String dos1, String tres1, String cuatro1, String cinco1, String seis1,
            String siete1, String ocho1, String nueve1, String diez1, String once1, String doce1, String trece1, String catorce1, String quince1, String Id, boolean imprimir) {
        JasperReport reporte;
        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/impresionesOftalmologia/biometria.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("Id", Id);
            parametros.put("uno", uno);
            parametros.put("dos", dos);
            parametros.put("tres", tres);
            parametros.put("cuatro", cuatro);
            parametros.put("cinco", cinco);
            parametros.put("seis", seis);
            parametros.put("siete", siete);
            parametros.put("ocho", ocho);
            parametros.put("nueve", nueve);
            parametros.put("diez", diez);
            parametros.put("once", once);
            parametros.put("doce", doce);
            parametros.put("trece", trece);
            parametros.put("catorce", catorce);
            parametros.put("quince", quince);
            parametros.put("uno1", uno1);
            parametros.put("dos1", dos1);
            parametros.put("tres1", tres1);
            parametros.put("cuatro1", cuatro1);
            parametros.put("cinco1", cinco1);
            parametros.put("seis1", seis1);
            parametros.put("siete1", siete1);
            parametros.put("ocho1", ocho1);
            parametros.put("nueve1", nueve1);
            parametros.put("diez1", diez1);
            parametros.put("once1", once1);
            parametros.put("doce1", doce1);
            parametros.put("trece1", trece1);
            parametros.put("catorce1", catorce1);
            parametros.put("quince1", quince1);
            Iniciar ini = new Iniciar(parametros, reporte, imprimir, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void verIncapacidadOf(String numero, String info, String id, String tipo, String nombre, String sexo, String estado, String fecha, String edad) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/impresionesOftalmologia/incapacidad.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("numero", numero);
            parametros.put("id", id);
            parametros.put("tipo", tipo);
            parametros.put("nombre", nombre);
            parametros.put("sexo", sexo);
            parametros.put("estado", estado);
            parametros.put("fecha", fecha);
            parametros.put("urlImagen", this.getClass().getResourceAsStream(logo));
            parametros.put("info", info);
            parametros.put("edad", edad);
            parametros.put("firma", logo3);
            parametros.put("usuario", instancia.getUsuarioLog().getNombre());

            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void verIncapacidad(String numero, String info, String id, String tipo, String nombre, String sexo, String estado, String fecha, String edad) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/reportes/ejemplo/incapacidad.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("numero", numero);
            parametros.put("id", id);
            parametros.put("tipo", tipo);
            parametros.put("nombre", nombre);
            parametros.put("sexo", sexo);
            parametros.put("estado", estado);
            parametros.put("fecha", fecha);
            parametros.put("urlImagen", this.getClass().getResourceAsStream(logo));
            parametros.put("info", info);
            parametros.put("edad", edad);
            parametros.put("firma", logo3);
            parametros.put("usuario", instancia.getUsuarioLog().getNombre());

            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void verFormulaLentes(String numero, String info) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/impresionesOftalmologia/formulaLentes.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("numero", numero);
            parametros.put("urlImagen", this.getClass().getResourceAsStream(logo));
            parametros.put("info", info);
            parametros.put("firma", logo3);
            parametros.put("usuario", instancia.getUsuarioLog().getNombre());

            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void ver_Paquimetria(String factura, boolean imprimir) {
        JasperReport reporte;
        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/impresionesOftalmologia/paquimetria1.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("factura", factura.replace("PAQUI-", ""));
            parametros.put("numFactura", factura);
            parametros.put("urlImagen", this.getClass().getResourceAsStream(logo));
            parametros.put("urlImagen2", this.getClass().getResourceAsStream(automovil));
            Iniciar ini = new Iniciar(parametros, reporte, imprimir, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();
        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void verFormulaMedicaOf(String numero, String info, String id, String tipo, String nombre, String sexo, String estado, String edad, String fecha) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/impresionesOftalmologia/formulaMedica.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("numero", numero);
            parametros.put("id", id);
            parametros.put("tipo", tipo);
            parametros.put("nombre", nombre);
            parametros.put("sexo", sexo);
            parametros.put("edad", edad);
            parametros.put("estado", estado);
            parametros.put("fecha", fecha);
            parametros.put("urlImagen", this.getClass().getResourceAsStream(logo));
            parametros.put("info", info);
            parametros.put("firma", logo3);
            parametros.put("usuario", instancia.getUsuarioLog().getNombre());

            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void verFormulaMedica(String numero, String info, String id, String tipo, String nombre, String sexo, String estado, String edad,
            String fecha) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/reportes/ejemplo/formulaMedica.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("numero", numero);
            parametros.put("id", id);
            parametros.put("tipo", tipo);
            parametros.put("nombre", nombre);
            parametros.put("sexo", sexo);
            parametros.put("edad", edad);
            parametros.put("estado", estado);
            parametros.put("fecha", fecha);
            parametros.put("urlImagen", this.getClass().getResourceAsStream(logo));
            parametros.put("info", info);
            parametros.put("firma", logo3);
            parametros.put("usuario", instancia.getUsuarioLog().getNombre());

            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void verAyudaDiagnosticaOf(String numero, String info, String id, String tipo, String nombre, String sexo, String estado) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/impresionesOftalmologia/ayudaDiagnostico.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("numero", numero);
            parametros.put("id", id);
            parametros.put("tipo", tipo);
            parametros.put("nombre", nombre);
            parametros.put("sexo", sexo);
            parametros.put("estado", estado);
            parametros.put("fecha", metodosGenerales.fecha());
            parametros.put("urlImagen", this.getClass().getResourceAsStream(logo));
            parametros.put("info", info);
            parametros.put("firma", logo3);
            parametros.put("usuario", instancia.getUsuarioLog().getNombre());

            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void verAyudaDiagnostica(String numero, String info, String id, String tipo, String nombre, String sexo, String estado) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/reportes/ejemplo/ayudaDiagnostico.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("numero", numero);
            parametros.put("id", id);
            parametros.put("tipo", tipo);
            parametros.put("nombre", nombre);
            parametros.put("sexo", sexo);
            parametros.put("estado", estado);
            parametros.put("fecha", metodosGenerales.fecha());
            parametros.put("urlImagen", this.getClass().getResourceAsStream(logo));
            parametros.put("info", info);
            parametros.put("firma", logo3);
            parametros.put("usuario", instancia.getUsuarioLog().getNombre());

            Iniciar ini = new Iniciar(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }
}

class Iniciar extends controladorBarraProceso {

    Boolean crearPDF = false, crearPDF1 = false;
    String factura;
    JasperPrint reporte_view;
    JasperReport reporte;
    Map parametros;
    boolean imprimir;

    MySql_connection conexion;
    boolean conBd;
    Instancias instancias;
    jcThread barra;

    public void setFactura(String factura) {
        this.factura = factura;
    }

    public void setCrearPDF1(Boolean crearPDF1) {
        this.crearPDF1 = crearPDF1;
    }

    public void setCrearPDF(Boolean crearPDF) {
        this.crearPDF = crearPDF;
    }

    public void setBarra(jcThread barra) {
        this.barra = barra;
    }

    Iniciar(Map parametros, JasperReport reporte, boolean imprimir, boolean conBd, Instancias instancias) {
        this.parametros = parametros;
        this.reporte = reporte;
        this.imprimir = imprimir;
        this.conBd = conBd;
        conexion = instancias.getSql().getMysql();
        this.instancias = instancias;
    }

    public void run() {
        try {

            if (conBd) {
                reporte_view = JasperFillManager.fillReport(reporte, parametros, conexion.getConnection());
            } else {
                reporte_view = JasperFillManager.fillReport(reporte, parametros, new JREmptyDataSource());
            }

            if (crearPDF) {
                JasperExportManager.exportReportToPdfFile(reporte_view, System.getProperty("user.home") + "\\Fact.pdf");
            } else if (crearPDF1) {
                JasperExportManager.exportReportToPdfFile(reporte_view, System.getProperty("user.home") + "/desktop/Facturas ClickingPOS/" + factura + ".pdf");
            } else {
                if (imprimir) {
                    JasperPrintManager.printReport(reporte_view, false);
                } else {
                    if (instancias.getRutaAguardar() == null) {
                        JasperViewer.viewReport(reporte_view, false);
                    } else {

                        //JRDocxExporter exporter2 = new JRDocxExporter();
                        JExcelApiExporter exporter = new JExcelApiExporter();
                        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conexion.getConnection());
                        OutputStream outputfile = null;

                        try {
                            outputfile = new FileOutputStream(new File(instancias.getRutaAguardar()));
                            exporter.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, outputfile);
                            //exporter2.setParameter(JRExporterParameter.OUTPUT_STREAM, outputfile);
                        } catch (Exception e) {

                        }
                        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                        /*exporter2.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                         exporter2.exportReport();*/
                        exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, false);
                        exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, true);
                        exporter.setParameter(JRXlsExporterParameter.IS_IGNORE_CELL_BORDER, false);
                        exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, false);
                        exporter.exportReport();

                        try {
                            outputfile.close();
                        } catch (IOException ex) {
                            Logger.getLogger(Iniciar.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        /*
                         try {
                         Process p = Runtime.getRuntime().exec("rundll32 SHELL32.DLL,ShellExec_RunDLL " + new FileOutputStream(new File(instancias.getRutaAguardar())));
                         } catch (Exception e) {
                         System.out.println("Error al abrir el archivo " + outputfile + "\n" + e.getMessage());
                         }
                         */
                        instancias.setRutaAguardar(null);
                    }
                }
            }

            barra.detener(true);
            instancias.setProgres(null);

        } catch (JRException ex) {
            Logger.getLogger(Iniciar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

class IniciarImpresion extends controladorBarraProceso {

    Boolean crearPDF1 = false, crearPDF = false;
    JasperPrint reporte_view;
    JasperReport reporte;
    Map parametros;
    boolean imprimir;
    String impresoraEstablecida = "", factura;

    MySql_connection conexion;
    boolean conBd;
    Instancias instancias;
    jcThread barra;

    public void setFactura(String factura) {
        this.factura = factura;
    }

    public void setBarra(jcThread barra) {
        this.barra = barra;
    }

    public void setCrearPDF1(Boolean crearPDF1) {
        this.crearPDF1 = crearPDF1;
    }

    public void setCrearPDF(Boolean crearPDF) {
        this.crearPDF = crearPDF;
    }

    IniciarImpresion(Map parametros, JasperReport reporte, boolean imprimir, boolean conBd, Instancias instancias, String impresora) {
        this.parametros = parametros;
        this.reporte = reporte;
        this.imprimir = imprimir;
        this.conBd = conBd;
        conexion = instancias.getSql().getMysql();
        this.instancias = instancias;
        impresoraEstablecida = impresora;
    }

    public void run() {
        try {

            if (conBd) {
                reporte_view = JasperFillManager.fillReport(reporte, parametros, conexion.getConnection());
            } else {
                reporte_view = JasperFillManager.fillReport(reporte, parametros, new JREmptyDataSource());
            }

            if (crearPDF) {
                JasperExportManager.exportReportToPdfFile(reporte_view, System.getProperty("user.home") + "\\Fact.pdf");
            } else if (crearPDF1) {
                JasperExportManager.exportReportToPdfFile(reporte_view, System.getProperty("user.home") + "/desktop/Facturas ClickingPOS/" + factura + ".pdf");
            } else {
                if (imprimir) {
                    // buscamos la impresora seleccionada en las impresoras indicadas
                    PrinterJob job = PrinterJob.getPrinterJob();
                    PrintService[] servicios = PrintServiceLookup.lookupPrintServices(null, null);

                    // luego de instalar la imrpesora vuelvo a buscar
                    job = PrinterJob.getPrinterJob();
                    servicios = PrintServiceLookup.lookupPrintServices(null, null);

                    Boolean existe = false;
                    for (int i = 0; i < servicios.length; i++) {
                        String servicio = servicios[i].toString().split(" : ")[1];
                        if (servicio.toUpperCase().contains("\\\\SERVIDOR\\ " + impresoraEstablecida.toUpperCase())
                                || servicio.toUpperCase().contains(impresoraEstablecida.toUpperCase())) {

                            try {
                                job.setPrintService(servicios[i]);
                            } catch (PrinterException ex) {
                                Logger.getLogger(Iniciar.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            //configuramos la impresion
                            PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
                            //              printRequestAttributeSet.add(new Copies(1));
                            printRequestAttributeSet.add(OrientationRequested.PORTRAIT);

                            PrintServiceAttributeSet printServiceAttributeSet = new HashPrintServiceAttributeSet();

                            // configuramos el servicio de impresion
                            JRPrintServiceExporter exporter = new JRPrintServiceExporter();

                            exporter.setParameter(JRExporterParameter.JASPER_PRINT, reporte_view);
                            exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE, servicios[i]);
                            exporter.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET, printRequestAttributeSet);
                            exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE_ATTRIBUTE_SET, printServiceAttributeSet);
                            exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.FALSE);
                            exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.FALSE);

                            // imprimos el informe
                            exporter.exportReport();
                            existe = true;
                            break;
                        }
                    }

                    if (!existe) {
                        JasperPrintManager.printReport(reporte_view, false);
                    }
                } else {
                    if (instancias.getRutaAguardar() == null) {
                        JasperViewer.viewReport(reporte_view, false);
                    } else {

                        //JRDocxExporter exporter2 = new JRDocxExporter();
                        JExcelApiExporter exporter = new JExcelApiExporter();
                        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conexion.getConnection());
                        OutputStream outputfile = null;
                        try {
                            outputfile = new FileOutputStream(new File(instancias.getRutaAguardar()));
                            exporter.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, outputfile);
                            //exporter2.setParameter(JRExporterParameter.OUTPUT_STREAM, outputfile);
                        } catch (Exception e) {

                        }
                        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                        /*exporter2.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                         exporter2.exportReport();*/
                        exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, false);
                        exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, true);
                        exporter.setParameter(JRXlsExporterParameter.IS_IGNORE_CELL_BORDER, false);
                        exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, false);
                        exporter.exportReport();

                        try {
                            outputfile.close();
                        } catch (IOException ex) {
                            Logger.getLogger(Iniciar.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        /*
                         try {
                         Process p = Runtime.getRuntime().exec("rundll32 SHELL32.DLL,ShellExec_RunDLL " + new FileOutputStream(new File(instancias.getRutaAguardar())));
                         } catch (Exception e) {
                         System.out.println("Error al abrir el archivo " + outputfile + "\n" + e.getMessage());
                         }
                         */
                        instancias.setRutaAguardar(null);
                    }
                }
            }

            barra.detener(true);
            instancias.setProgres(null);

        } catch (JRException ex) {
            Logger.getLogger(Iniciar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
