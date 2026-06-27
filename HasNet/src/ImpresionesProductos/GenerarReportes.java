package ImpresionesProductos;

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

    public void verAjusteInventario(String factura) {
        String nombreReporte = Reportes.AJUSTES_INVENTARIO.getNombre();
        if (esTipoImpresionPos()) {
            nombreReporte = Reportes.AJUSTES_INVENTARIO_POS.getNombre();
        }

        try {
            URL in = this.getClass().getResource("/ImpresionesProductos/" + nombreReporte + ".jasper");
            JasperReport reporte = (JasperReport) JRLoader.loadObject(in);
            Map<String, String> parametros = new HashMap<>();
            parametros.put("factura", factura.replace("TRAS-", ""));
            parametros.put("numFactura", factura);
            parametros.put("info", instancias.getInformacionEmpresa());
            parametros.put("simbolo", instancias.getSimbolo());
            parametros.put("cadenaDecimales", instancias.getCadenaDecimales());
            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancias);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancias);
            barra.show();
        } catch (JRException e) {
            System.out.println(e);
        }
    }

    public void verIngresoDetalle(String factura) {
        String nombreReporte = Reportes.AJUSTES_INVENTARIO_DETALLE.getNombre();

        try {
            URL in = this.getClass().getResource("/ImpresionesProductos/" + nombreReporte + ".jasper");
            JasperReport reporte = (JasperReport) JRLoader.loadObject(in);
            Map<String, String> parametros = new HashMap<>();
            parametros.put("numFactura", factura);
            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, true, instancias);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancias);
            barra.show();
        } catch (JRException e) {
            System.out.println(e);
        }
    }
}
