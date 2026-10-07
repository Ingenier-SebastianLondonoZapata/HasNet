package Impresiones.ImpresionesEgresos;

import Impresiones.GeneradorReporteBase;
import clases.Instancias;
import java.util.HashMap;
import java.util.Map;

public class GeneradorReporteEgreso extends GeneradorReporteBase {

    public GeneradorReporteEgreso(Instancias instancias) {
        super(instancias);
    }

    public void ver_Egreso(String factura, String info, String letras, Boolean imprimir, String tipo) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("factura", factura.replace("EGR-", ""));
        parametros.put("numFactura", factura);
        parametros.put("urlImagen", logo());
        parametros.put("info", info);
        parametros.put("letras", letras);
        parametros.put("simbolo", instancias.getSimbolo());
        parametros.put("cadenaDecimales", instancias.getCadenaDecimales());
        ejecutar("egreso" + tipo, parametros, imprimir);
    }
}
