package clases;

import Dao.Documentos.ConsultaCartera;
import Dao.Documentos.ConsultaFactura;
import Dao.Documentos.ConsultaVista;

import Utilidades.DatosMaestra;
import Impresiones.IniciarReporte;
import Vista.BarraProceso.vistaBarraProceso;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

public class iFactura {

    String imagenInforme1 = "";

    public String getImagenInforme1() {
        return imagenInforme1;
    }

    public void setImagenInforme1(String imagenInforme1) {
        this.imagenInforme1 = imagenInforme1;
    }

    private final String logo = System.getProperty("user.dir") + "//imagenes//conf//logoEmpresa.png";
    private final String automovil = "/imagenes/impresion/Fundacion1.png";
    private final String logo2 = "/imagenes/impresion/colposcopia.png";

    private String logo3 = "";
    private String imagenInforme = "";
    private final String firma = "/imagenes/firma.png";
    private Instancias instancia;
    public String informacionLegalReportes = "";
    metodosGenerales metodos = new metodosGenerales();
    public void consultarMaestros() {
    }

    public void setInformacionLegalHasNet(String informacionLegalHasNet) {
        this.informacionLegalReportes = informacionLegalHasNet;
    }

    public String getInformacionLegalHasNet() {
        return informacionLegalReportes;
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
            URL in = this.getClass().getResource("/Vista/Veterinario/Reportes/repAlertasVacunacion.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("consulta", sql);
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());
            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
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
            URL in = this.getClass().getResource("/Vista/Veterinario/Reportes/reporteFormatos.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap 
            Map<String, String> parametros = new HashMap<String, String>();
            parametros.clear();
            parametros.put("formato", formato);
            parametros.put("tercero", tercero);
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());

            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
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
            URL in = this.getClass().getResource("/Vista/Veterinario/Reportes/reporteFormatosTipos.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap 
            Map<String, String> parametros = new HashMap<String, String>();
            parametros.clear();
            parametros.put("formato", formato);
            parametros.put("tercero", tercero);
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());
            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void verRepPeluqueria(String sql, String enca) {
        JasperReport reporte;
        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/Vista/Veterinario/Reportes/repPeluqueria.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("cliente", sql);
            parametros.put("encabezado", enca);
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());
            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void ver_RepMensualidades(String sql, String encabezado) {
        JasperReport reporte;
        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/Vista/Parqueadero/Reportes/repMensualidad.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map<String, String> parametros = new HashMap<String, String>();
            parametros.clear();
            parametros.put("cliente", sql);
            parametros.put("encabezado", encabezado);
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());

            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void ver_RepParqueadero(String sql, String encabezado) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/Vista/Parqueadero/Reportes/repParqueadero.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map<String, String> parametros = new HashMap<String, String>();
            parametros.clear();
            parametros.put("cliente", sql);
            parametros.put("encabezado", encabezado);
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());

            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void verRepLavado(String sql, String encabezado) {
        JasperReport reporte;
        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/Vista/Parqueadero/Reportes/repLavado.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map<String, String> parametros = new HashMap<String, String>();
            parametros.clear();
            parametros.put("cliente", sql);
            parametros.put("encabezado", encabezado);
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());

            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void verLiquidacionLavado(String sql, String encabezado) {
        JasperReport reporte;
        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/Vista/Parqueadero/Reportes/liquidacionLavado.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map<String, String> parametros = new HashMap<String, String>();
            parametros.clear();
            parametros.put("cliente", sql);
            parametros.put("encabezado", encabezado);
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());

            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
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
            URL in = this.getClass().getResource("/Vista/Parqueadero/Reportes/repHistorial.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map<String, String> parametros = new HashMap<String, String>();
            parametros.clear();
            parametros.put("consulta", sql);
            parametros.put("encabezado", encabezado);
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());

            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
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
            URL in = this.getClass().getResource("/Vista/Parqueadero/Reportes/pagos.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("cliente", info);
            parametros.put("urlImagen", this.getClass().getResourceAsStream(logo));
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());

            IniciarReporte ini = new IniciarReporte(parametros, reporte, true, true, instancia);
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
            URL in = this.getClass().getResource("/Vista/Medico/Reportes/autorizacionServicios.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("id", dato);
            parametros.put("firma", logo3);
            parametros.put("usuario", instancia.getUsuarioLog().getNombre());
            parametros.put("urlImagen", logo());

            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
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
            URL in = this.getClass().getResource("/Vista/Medico/Reportes/detalleCargo.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map<String, String> parametros = new HashMap<String, String>();
            parametros.clear();
            parametros.put("cliente", sql);
            parametros.put("nombre", nombre);
            parametros.put("nit", nit);
            parametros.put("direccion", direccion);
            parametros.put("factura", factura);

            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
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
            URL in = this.getClass().getResource("/Vista/Medico/Reportes/planoFactura.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map<String, String> parametros = new HashMap<String, String>();
            parametros.clear();
            parametros.put("cliente", sql);
            parametros.put("factura", factura);
            parametros.put("uno", uno);
            parametros.put("dos", dos);

            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void verRepCitasMedicas(String sql, String enca, String medico) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/Vista/Agenda/Reportes/repCitas.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("cliente", sql);
            parametros.put("enca", enca);
            parametros.put("medico", medico);

            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
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
//            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
//            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
//            barra.show();
//
//        } catch (JRException E) {
//            System.out.println(E);
//        }
//    }
    //REPORTE DE CREDITO
    public void ver_RepCreditos(String sql, String encabezado) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/Vista/Cartera/Reportes/repCreditos.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map<String, String> parametros = new HashMap<String, String>();
            parametros.clear();
            parametros.put("cliente", sql);
            parametros.put("encabezado", encabezado);

            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    //REPORTE PLANTILLAS
    public void ver_RepPlantillas(String sql, String encabezado) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/Vista/Ventas/Reportes/repPlantillas.jasper");
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

            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();
        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void ver_RepPlantillas2(String sql, String encabezado) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/Vista/Ventas/Reportes/repPlantillas_1.jasper");
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

            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    //PRODUCTOS DEL PEDIDO

    //CLUB Y CUENTA CORRIENTE
    //CONTRATO DE CREDITO
    public void verEstadoDeCuenta(String contrato, String info) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/Vista/Cartera/Reportes/estadoDeCuenta.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("id", contrato);
            parametros.put("urlImagen", this.getClass().getResourceAsStream(firma));
            parametros.put("info", info);
            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
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
            URL in = this.getClass().getResource("/Vista/Cartera/Reportes/certificadoDeuda.jasper");
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
            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, false, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void verRepAlertasCumpleanos(String sql) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/Vista/Contactos/Reportes/repAlertasCumpleanos.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("consulta", sql);
            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void ver_RepPagos(String total) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/Vista/Cartera/Reportes/repPagos.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map<String, String> parametros = new HashMap();
            parametros.clear();
            parametros.put("total", total);
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());

            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
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
            parametros.put("sql", ConsultaFactura.filtro(ConsultaFactura.y(sql, "factura.anulada = 0")));
            parametros.put("info", instancia.getInformacionEmpresaCompleto());
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());
            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
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

    public void verRemision1(String dato, String edad) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/Vista/Medico/Reportes/remision1.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("id", dato);
            parametros.put("edad", edad);
            parametros.put("firma", logo3);
            parametros.put("usuario", instancia.getUsuarioLog().getNombre());
            parametros.put("urlImagen", logo());

            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void ver_ReIngresos(String sql, String encabezado) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/Vista/Productos/Reportes/repIngresos.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map<String, String> parametros = new HashMap<String, String>();
            parametros.clear();
            parametros.put("cliente", sql);
            parametros.put("encabezado", encabezado);
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());

            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void ver_RepIngresos2(String sql, String encabezado) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/Vista/Productos/Reportes/repIngresos_1.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map<String, String> parametros = new HashMap<String, String>();
            parametros.clear();
            parametros.put("cliente", sql);
            parametros.put("encabezado", encabezado);
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());

            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void ver_RepOrdenCompra(String sql, String encabezado) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/Vista/Productos/Reportes/repOrdenCompra.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map<String, String> parametros = new HashMap<String, String>();
            parametros.clear();
            parametros.put("cliente", sql);
            parametros.put("encabezado", encabezado);
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());

            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void verHistorico(String sql) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/Vista/Productos/Reportes/inventario.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map<String, String> parametros = new HashMap();
            parametros.clear();
            parametros.put("cliente", sql);
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());

            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();
        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void verInventarioDetallado(String sql) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/Vista/Productos/Reportes/inventarioDetalle.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map<String, String> parametros = new HashMap();
            parametros.clear();
            parametros.put("cliente", sql);
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());

            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();
        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void verRepCuadre(String sql, String encabezado) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/Vista/Tesoreria/Reportes/repCuadreCaja.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map<String, String> parametros = new HashMap<String, String>();
            parametros.clear();
            parametros.put("cliente", sql);
            parametros.put("encabezado", encabezado);
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());

            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
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
            URL in = this.getClass().getResource("/Vista/Tesoreria/Reportes/repPropinas.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("cliente", ConsultaVista.FACTURACION.filtro(sql));
            parametros.put("encabezado", encabezado);
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());

            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
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
            URL in = this.getClass().getResource("/Vista/Tesoreria/Reportes/repBaseCaja.jasper");
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

            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void ver_AZ(String sql, String mensaje) {
        JasperReport reporte;
        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/Vista/Ventas/Reportes/repAZ.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map<String, String> parametros = new HashMap<String, String>();
            parametros.clear();
            parametros.put("cliente", sql);
            parametros.put("mensaje", mensaje);
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());
            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
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
            URL in = this.getClass().getResource("/Vista/Ventas/Reportes/repResumenGerencial.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map<String, String> parametros = new HashMap<String, String>();
            parametros.clear();
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());

            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
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
            URL in = this.getClass().getResource("/Vista/Cartera/Reportes/estadoDeCuenta.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map<String, String> parametros = new HashMap<String, String>();
            parametros.clear();
            parametros.put("id", contrato);
            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
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
            URL in = this.getClass().getResource("/Vista/Cartera/Reportes/certificadoDeuda.jasper");
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

            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, false, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void ver_RepEgresos(String sql, String encabezado) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/Vista/Tesoreria/Reportes/repEgresos.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map<String, String> parametros = new HashMap<String, String>();
            parametros.clear();
            parametros.put("cliente", sql);
            parametros.put("encabezado", encabezado);
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());

            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();
        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void ver_RepAbonosCxp(String sql, String encabezado) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/Vista/Cartera/Reportes/repAbonosCxp.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map<String, String> parametros = new HashMap();
            parametros.clear();
            parametros.put("encabezado", encabezado);
            parametros.put("sql", sql);
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());

            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void ver_RepAbonos(String sql, String encabezado) {
        JasperReport reporte;

        String conseManual = "";
        if (DatosMaestra.isConsecutivoAdicional()) {
            conseManual = "SI";
        } else {
            conseManual = "NO";
        }

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/Vista/Cartera/Reportes/repAbonos.jasper");
            sql = ConsultaCartera.origenAbonos(sql);
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

            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
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
            URL in = this.getClass().getResource("/Vista/Veterinario/Reportes/repMascotas.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map<String, String> parametros = new HashMap<String, String>();
            parametros.clear();
            parametros.put("cliente", sql);
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());

            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
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
            URL in = this.getClass().getResource("/Vista/Veterinario/Reportes/repGuarderia.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map<String, String> parametros = new HashMap<String, String>();
            parametros.clear();
            parametros.put("cliente", sql);
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());

            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
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
            URL in = this.getClass().getResource("/Vista/Veterinario/Reportes/repHospitalizacion.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map<String, String> parametros = new HashMap<String, String>();
            parametros.clear();
            parametros.put("cliente", sql);
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());
            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void ver_RepProductos(String sql, String encabezado) {
        JasperReport reporte;
        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/Vista/Productos/Reportes/repProductos.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map<String, String> parametros = new HashMap<String, String>();
            parametros.clear();
            parametros.put("cliente", sql);
            parametros.put("encabezado", encabezado);
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());

            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void ver_RepClientes(String consulta, boolean mostrarEmpleado) {
        JasperReport reporte;

        try {
            URL in = this.getClass().getResource("/Vista/Contactos/Reportes/repClientes.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("cliente", consulta);
            parametros.put("mostrarEmpleado", mostrarEmpleado);
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());

            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void ver_RepReferidos(String sql, String encabezado) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/Vista/Contactos/Reportes/repReferidos.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map<String, String> parametros = new HashMap();
            parametros.clear();
            parametros.put("cliente", sql);
            parametros.put("encabezado", encabezado);

            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void ver_Repcartera(String total, String tipoRep) {
        JasperReport reporte;

        String conseManual = "";

        if (DatosMaestra.isConsecutivoAdicional()) {
            conseManual = "SI";
        } else {
            conseManual = "NO";
        }

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/Vista/Cartera/Reportes/" + tipoRep + ".jasper");
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

            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void ver_RepNC(String sql, String enca) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/Vista/Cartera/Reportes/repNC.jasper");
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
            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
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
            URL in = this.getClass().getResource("/Vista/Productos/Reportes/repAjuste.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map<String, String> parametros = new HashMap<String, String>();
            parametros.clear();
            parametros.put("cliente", sql);
            parametros.put("encabezado", encabezado);
            parametros.put("totalizar", resp);
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());

            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
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
            URL in = this.getClass().getResource("/Vista/Parqueadero/Reportes/tirilla1.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map parametros = new HashMap();
            parametros.clear();
            parametros.put("info", info);
            parametros.put("placa", placa);
            parametros.put("fecha", fecha);
            parametros.put("hora", hora);
            parametros.put("informacionLegalHasNet", informacionLegalReportes);
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());

            IniciarReporte ini = new IniciarReporte(parametros, reporte, true, false, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    /* OFTALMOLOGIA */
}

