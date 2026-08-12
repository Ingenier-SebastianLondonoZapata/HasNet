package Impresiones;

import Vista.BarraProceso.vistaBarraProceso;
import clases.Instancias;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

public abstract class GeneradorReporteBase {

    protected final Instancias instancias;

    protected GeneradorReporteBase(Instancias instancias) {
        this.instancias = instancias;
    }

    protected boolean esTipoImpresionPos() {
        return instancias.getImpresion().equals("pos");
    }

    protected void ejecutar(String nombreReporte, Map<String, ?> parametros) {
        try {
            System.out.println("nombre reporte a imprimir: " + nombreReporte);
            URL in = getClass().getResource(nombreReporte + ".jasper");
            JasperReport reporte = (JasperReport) JRLoader.loadObject(in);
            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancias);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancias);
            barra.show();
        } catch (JRException e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
        }
    }

    protected InputStream logo() {
        try {
            String logo = System.getProperty("user.dir") + "//imagenes//conf//logoEmpresa.png";
            return new FileInputStream(new File(logo));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
