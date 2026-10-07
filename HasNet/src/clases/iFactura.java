package clases;

import Impresiones.IniciarImpresion;
import Impresiones.IniciarReporte;
import Vista.BarraProceso.vistaBarraProceso;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigDecimal;
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
    private final String automovil = "/imagenes/Fundacion1.png";
    private final String logo2 = "/imagenes/colposcopia.png";

    private String logo3 = "";
    private String imagenInforme = "";
    private final String firma = "/imagenes/firma.png";
    private Instancias instancia;
    public String informacionLegalReportes = "";
    metodosGenerales metodos = new metodosGenerales();
    Object[] datos = null;

    public void consultarMaestros() {
        datos = instancia.getSql().getDatosMaestra();
    }

    public void setInformacionLegalClick(String informacionLegalClick) {
        this.informacionLegalReportes = informacionLegalClick;
    }

    public String getInformacionLegalClick() {
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
            URL in = this.getClass().getResource("/reportesVeterinario/repAlertasVacunacion.jasper");
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
            URL in = this.getClass().getResource("/reportesVeterinario/reporteFormatos.jasper");
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
            URL in = this.getClass().getResource("/reportesVeterinario/reporteFormatosTipos.jasper");
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
            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

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

            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
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

            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
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

            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
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
            URL in = this.getClass().getResource("/reportesParqueadero/repHistorial.jasper");
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
            URL in = this.getClass().getResource("/reportesParqueadero/pagos.jasper");
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
            URL in = this.getClass().getResource("/reportes/ejemplo/autorizacionServicios.jasper");
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
            URL in = this.getClass().getResource("/reportes/ejemplo/planoFactura.jasper");
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

            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
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

            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
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

            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
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

            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
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

            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
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

            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
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

            IniciarReporte ini = new IniciarReporte(parametros, reporte, imprimir, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    //CLUB Y CUENTA CORRIENTE
    //CONTRATO DE CREDITO
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
            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, false, instancia);
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

            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
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
            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
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

            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
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

            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
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

            IniciarReporte ini = new IniciarReporte(parametros, reporte, true, false, instancia);
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
            parametros.put("sql", sql);
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

    public void abrirCajaRegistradora() {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/reportes/ejemplo/abrirCaja.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map parametros = new HashMap();
            parametros.clear();

            IniciarReporte ini = new IniciarReporte(parametros, reporte, true, true, instancia);
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

            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, false, instancia);
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

            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
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

            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
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

            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
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

            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
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

            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
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

            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
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

            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
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

            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
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

            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
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
            URL in = this.getClass().getResource("/reportesCuadreCaja/repPropinas.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map parametros = new HashMap();
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

            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
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
            URL in = this.getClass().getResource("/reportesVentas/repResumenGerencial.jasper");
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
            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
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
            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
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

            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
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

            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
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
            URL in = this.getClass().getResource("/reportes/ejemplo/estadoDeCuenta.jasper");
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

            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, false, instancia);
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

            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, false, instancia);
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

            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, false, instancia);
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

            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
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

            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
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

            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
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

            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
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
            URL in = this.getClass().getResource("/reportesVeterinario/repMascotas.jasper");
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

            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
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
            URL in = this.getClass().getResource("/reportesVeterinario/repGuarderia.jasper");
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
            URL in = this.getClass().getResource("/reportesVeterinario/repHospitalizacion.jasper");
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

            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
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

            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
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

            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
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

            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
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

            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
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

            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
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

            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
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

            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
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
            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }

    public void ver_RepND(String sql, String encabezado, String tipo) {
        JasperReport reporte;

        try {
            //direccion del archivo JASPER
            URL in = this.getClass().getResource("/reportesNotasDebito/repND" + tipo + ".jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map<String, String> parametros = new HashMap<String, String>();
            parametros.clear();

            String sqlCompleto = sentenciaFacturacionDetallado(sql);
            parametros.put("cliente", sqlCompleto);
            parametros.put("encabezado", encabezado);
            parametros.put("encabezado2", "");
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
            if (tipo.equals("")) {
                metodos.msgError(null, "Hubo un problema al generar el reporte, seleccione un rango de menor tamaño de fecha o genere este como hoja de calculo.");
            }
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

            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
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

            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
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
            URL in = this.getClass().getResource("/ImpresionesProductos/ingresoDetalle.jasper");
            reporte = (JasperReport) JRLoader.loadObject(in);
            //Se crea un objeto HashMap
            Map<String, String> parametros = new HashMap<String, String>();
            parametros.clear();
            parametros.put("numFactura", factura);
            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
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
            parametros.put("informacionLegalClick", informacionLegalReportes);
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());

            IniciarReporte ini = new IniciarReporte(parametros, reporte, imprimir, true, instancia);
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
            parametros.put("informacionLegalClick", informacionLegalReportes);
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());

            IniciarReporte ini = new IniciarReporte(parametros, reporte, imprimir, true, instancia);
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
            parametros.put("informacionLegalClick", informacionLegalReportes);
            parametros.put("simbolo", instancia.getSimbolo());
            parametros.put("cadenaDecimales", instancia.getCadenaDecimales());

            IniciarReporte ini = new IniciarReporte(parametros, reporte, true, false, instancia);
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
            parametros.put("informacionLegalClick", informacionLegalReportes);

            IniciarReporte ini = new IniciarReporte(parametros, reporte, true, false, instancia);
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

            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
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

            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
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

            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
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

            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);

        }
    }

    /* OFTALMOLOGIA */
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

            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
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

            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
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

            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancia);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancia);
            barra.show();

        } catch (JRException E) {
            System.out.println(E);
        }
    }
}

