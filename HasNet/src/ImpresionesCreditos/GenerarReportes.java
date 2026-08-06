package ImpresionesCreditos;

import Enums.Reportes;
import Vista.BarraProceso.vistaBarraProceso;
import Impresiones.IniciarReporte;
import clases.Instancias;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

public class GenerarReportes {

    private final Instancias instancias;

    public GenerarReportes(Instancias instancias) {
        this.instancias = instancias;
    }

    public boolean esTipoImpresionPos() {
        return instancias.getImpresion().equals("pos");
    }

    public void verCredito(String contrato) {
        String nombreReporte = "credito" + Instancias.getInstancias().getTipoImpresion();

        try {
            URL in = this.getClass().getResource("/ImpresionesCreditos/" + nombreReporte + ".jasper");
            JasperReport reporte = (JasperReport) JRLoader.loadObject(in);
            Map<String, String> parametros = new HashMap<>();
            parametros.put("id", contrato);
            parametros.put("info", instancias.getInformacionEmpresa());
            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancias);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancias);
            barra.show();
        } catch (JRException e) {
            System.out.println(e);
        }
    }
}
