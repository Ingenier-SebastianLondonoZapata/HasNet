package Impresiones.ImpresionesSepares;

import Impresiones.GeneradorReporteBase;
import clases.Instancias;
import java.util.HashMap;
import java.util.Map;

public class GeneradorReporteSepare extends GeneradorReporteBase {

    public GeneradorReporteSepare(Instancias instancias) {
        super(instancias);
    }

    public void ver_Separe(String factura, String observaciones, String info, String tipo, String nombreReporte, boolean imprimir) {
        String informacionLegal = instancias.getLegal() == null ? "" : instancias.getLegal();
        String pieDePagina = instancias.getPie() == null ? "" : instancias.getLegal();

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("factura", factura.replace("SEPARE-", ""));
        parametros.put("numFactura", factura);
        parametros.put("urlImagen", logo());
        parametros.put("observaciones", observaciones);
        parametros.put("info", info);
        parametros.put("legal", informacionLegal);
        parametros.put("tipoFact", tipo);
        parametros.put("pie", pieDePagina);
        parametros.put("titulo", "Separe No.");
        parametros.put("informacionLegalClick", instancias.getReporte().getInformacionLegalClick());
        parametros.put("simbolo", instancias.getSimbolo());
        parametros.put("cadenaDecimales", instancias.getCadenaDecimales());
        ejecutar(nombreReporte, parametros);
    }
}
