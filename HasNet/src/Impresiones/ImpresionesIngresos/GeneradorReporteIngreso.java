package Impresiones.ImpresionesIngresos;

import Impresiones.GeneradorReporteBase;
import clases.Instancias;
import java.util.HashMap;
import java.util.Map;

public class GeneradorReporteIngreso extends GeneradorReporteBase {

    public GeneradorReporteIngreso(Instancias instancias) {
        super(instancias);
    }

    public void verIngreso(String factura, String tipoCompra) {
        String sufijo = instancias.getTipoImpresion();
        if (instancias.getConfiguraciones().getTipoImpresion().equals("Sin-Codigo")) {
            sufijo = sufijo + "1";
        }
        ejecutar("ingreso" + sufijo, parametrosIngreso(factura, tipoCompra));
    }

    public void verIngreso(String factura, String tipoCompra, String tipo) {
        ejecutar("ingreso" + tipo, parametrosIngreso(factura, tipoCompra));
    }

    private Map<String, Object> parametrosIngreso(String factura, String tipoCompra) {
        boolean esIngreso = tipoCompra.equals("ingreso");
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("factura", factura.replace(esIngreso ? "ING-" : "ORDENCOMPRA-", ""));
        parametros.put("numFactura", factura);
        parametros.put("tipoCompra", esIngreso ? "Ingreso" : "Orden Compra");
        parametros.put("simbolo", instancias.getSimbolo());
        parametros.put("cadenaDecimales", instancias.getCadenaDecimales());
        return parametros;
    }
}
