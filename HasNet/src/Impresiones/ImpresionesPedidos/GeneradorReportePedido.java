package Impresiones.ImpresionesPedidos;

import Impresiones.GeneradorReporteBase;
import clases.Instancias;
import java.util.HashMap;
import java.util.Map;

public class GeneradorReportePedido extends GeneradorReporteBase {

    public GeneradorReportePedido(Instancias instancias) {
        super(instancias);
    }

    public void ver_Pedido(String factura, String observaciones, String info, String tipo, String nombreReporte, boolean imprimir) {
        String informacionLegal = instancias.getLegal() == null ? "" : instancias.getLegal();
        String pieDePagina = instancias.getPie() == null ? "" : instancias.getLegal();

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("factura", factura.replace("PEDIDO-", ""));
        parametros.put("numPedido", factura);
        parametros.put("urlImagen", logo());
        parametros.put("observaciones", observaciones);
        parametros.put("info", info);
        parametros.put("legal", informacionLegal);
        parametros.put("tipoFact", tipo);
        parametros.put("pie", pieDePagina);
        parametros.put("titulo", "Pedido No.");
        parametros.put("informacionLegalClick", instancias.getReporte().getInformacionLegalClick());
        parametros.put("simbolo", instancias.getSimbolo());
        parametros.put("cadenaDecimales", instancias.getCadenaDecimales());
        ejecutar(nombreReporte, parametros);
    }

    public void ver_ListadoPedido(String factura, String observaciones, String info) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("factura", factura.replace("PEDIDO-", ""));
        parametros.put("numPedido", factura);
        parametros.put("urlImagen", logo());
        parametros.put("observaciones", observaciones);
        parametros.put("info", info);
        parametros.put("titulo", "Pedido No.");
        parametros.put("informacionLegalClick", instancias.getReporte().getInformacionLegalClick());
        ejecutar("listadoPedido", parametros);
    }
}
