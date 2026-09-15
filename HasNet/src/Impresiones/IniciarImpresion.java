package Impresiones;

import Controlador.BarraProceso.controladorBarraProceso;
import Controlador.BarraProceso.jcThread;
import Impresiones.IniciarReporte;
import Utilidades.BaseDatos.MySql_connection;
import clases.Instancias;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JExcelApiExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.view.JasperViewer;

public class IniciarImpresion extends controladorBarraProceso {

    JasperPrint reporte_view;
    JasperReport reporte;
    Map parametros;
    boolean imprimir;
    String impresoraEstablecida = "";

    MySql_connection conexion;
    boolean conBd;
    Instancias instancias;
    jcThread barra;

    public void setBarra(jcThread barra) {
        this.barra = barra;
    }

    public IniciarImpresion(Map parametros, JasperReport reporte, boolean imprimir, boolean conBd, Instancias instancias, String impresora) {
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
                            Logger.getLogger(IniciarReporte.class.getName()).log(Level.SEVERE, null, ex);
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
                    JExcelApiExporter exporter = new JExcelApiExporter();
                    JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conexion.getConnection());
                    OutputStream outputfile = null;

                    try {
                        outputfile = new FileOutputStream(new File(instancias.getRutaAguardar()));
                        exporter.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, outputfile);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(IniciarImpresion.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                    exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, false);
                    exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, true);
                    exporter.setParameter(JRXlsExporterParameter.IS_IGNORE_CELL_BORDER, false);
                    exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, false);
                    exporter.exportReport();

                    try {
                        outputfile.close();
                    } catch (IOException ex) {
                        Logger.getLogger(IniciarReporte.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    instancias.setRutaAguardar(null);
                }
            }

            barra.detener(true);
            instancias.setProgres(null);

        } catch (JRException ex) {
            Logger.getLogger(IniciarReporte.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
