package Impresiones;

import Controlador.BarraProceso.controladorBarraProceso;
import Controlador.BarraProceso.jcThread;
import Utilidades.BaseDatos.MySql_connection;
import clases.Instancias;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JExcelApiExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.view.JasperViewer;

public class IniciarReporte extends controladorBarraProceso {

    private final JasperReport reporte;
    private final Map parametros;
    private final boolean imprimir;
    private final boolean conBd;
    private final MySql_connection conexion;
    private final Instancias instancias;
    private jcThread barra;

    public void setBarra(jcThread barra) {
        this.barra = barra;
    }

    public IniciarReporte(Map parametros, JasperReport reporte, boolean imprimir, boolean conBd, Instancias instancias) {
        this.parametros = parametros;
        this.reporte = reporte;
        this.imprimir = imprimir;
        this.conBd = conBd;
        this.conexion = instancias.getSql().getMysql();
        this.instancias = instancias;
    }

    @Override
    public void run() {
        try {
            JasperPrint reporteGenerado = conBd
                    ? JasperFillManager.fillReport(reporte, parametros, conexion.getConnection())
                    : JasperFillManager.fillReport(reporte, parametros, new JREmptyDataSource());

            if (imprimir) {
                JasperPrintManager.printReport(reporteGenerado, false);
            } else if (instancias.getRutaAguardar() != null) {
                exportarExcel();
            } else {
                JasperViewer.viewReport(reporteGenerado, false);
            }

            barra.detener(true);
            instancias.setProgres(null);

        } catch (JRException ex) {
            Logger.getLogger(IniciarReporte.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void exportarExcel() throws JRException {
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conexion.getConnection());
        JExcelApiExporter exporter = new JExcelApiExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, false);
        exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, true);
        exporter.setParameter(JRXlsExporterParameter.IS_IGNORE_CELL_BORDER, false);
        exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, false);

        try (OutputStream outputfile = new FileOutputStream(new File(instancias.getRutaAguardar()))) {
            exporter.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, outputfile);
            exporter.exportReport();
        } catch (IOException ex) {
            Logger.getLogger(IniciarReporte.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            instancias.setRutaAguardar(null);
        }
    }
}
