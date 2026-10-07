package Impresiones.ImpresionesCotizaciones;

import Impresiones.GeneradorReporteBase;
import clases.Instancias;
import java.util.HashMap;
import java.util.Map;

public class GeneradorReporteCotizacion extends GeneradorReporteBase {

    public GeneradorReporteCotizacion(Instancias instancias) {
        super(instancias);
    }

    public void ver_Cotiza(String factura, String observaciones, String info, String legal, String nRep, boolean imprimir) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("factura", factura.replace("COTI-", ""));
        parametros.put("numFactura", factura);
        parametros.put("urlImagen", logo());
        parametros.put("info", info);
        parametros.put("observaciones", observaciones);
        parametros.put("legal", legal);
        parametros.put("informacionLegalClick", instancias.getReporte().getInformacionLegalClick());
        parametros.put("simbolo", instancias.getSimbolo());
        parametros.put("cadenaDecimales", instancias.getCadenaDecimales());
        ejecutar(nRep, parametros, imprimir);
    }
}
