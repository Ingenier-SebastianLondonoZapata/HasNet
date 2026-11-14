package Impresiones.ImpresionesPedidos;

import Impresiones.GeneradorReporteBase;
import clases.Instancias;
import java.util.HashMap;
import java.util.Map;

public class GeneradorReportePedido extends GeneradorReporteBase {

    public GeneradorReportePedido(Instancias instancias) {
        super(instancias);
    }

    public void ver_ListadoPedido(String factura, String observaciones, String info) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("factura", factura.replace("PEDIDO-", ""));
        parametros.put("numPedido", factura);
        parametros.put("urlImagen", logo());
        parametros.put("observaciones", observaciones);
        parametros.put("info", info);
        parametros.put("titulo", "Pedido No.");
        parametros.put("informacionLegalHasNet", instancias.getReporte().getInformacionLegalHasNet());
        ejecutar("listadoPedido", parametros);
    }
}
