package Impresiones.ImpresionesTraslados;

import Impresiones.GeneradorReporteBase;
import clases.Instancias;
import java.util.HashMap;
import java.util.Map;

public class GeneradorReporteTraslado extends GeneradorReporteBase {

    public GeneradorReporteTraslado(Instancias instancias) {
        super(instancias);
    }

    public void verAjustes(String factura, String tipo) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("factura", factura.replace("TRAS-", ""));
        parametros.put("numFactura", factura);
        parametros.put("info", instancias.getInformacionEmpresa());
        parametros.put("simbolo", instancias.getSimbolo());
        parametros.put("cadenaDecimales", instancias.getCadenaDecimales());
        ejecutar(tipo, parametros);
    }

    public void verTraslados(String factura, String tipo) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("factura", factura.replace("TRASINT-", ""));
        parametros.put("numFactura", factura);
        parametros.put("simbolo", instancias.getSimbolo());
        parametros.put("cadenaDecimales", instancias.getCadenaDecimales());
        ejecutar(tipo, parametros);
    }

    public void verPrestamos(String factura, String tipo) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("factura", factura.replace("TRASB-", ""));
        parametros.put("numFactura", factura);
        parametros.put("serial", instancias.getConfiguraciones().isProductosDetallados() ? "SI" : "NO");
        parametros.put("simbolo", instancias.getSimbolo());
        parametros.put("cadenaDecimales", instancias.getCadenaDecimales());
        ejecutar(tipo, parametros);
    }
}
