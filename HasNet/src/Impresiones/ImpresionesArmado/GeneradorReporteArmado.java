package Impresiones.ImpresionesArmado;

import Impresiones.GeneradorReporteBase;
import clases.Instancias;
import java.util.HashMap;
import java.util.Map;

public class GeneradorReporteArmado extends GeneradorReporteBase {

    public GeneradorReporteArmado(Instancias instancias) {
        super(instancias);
    }

    public void ver_Armado(String factura, String tipo) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("documento", factura.replace("CST-", ""));
        parametros.put("numFactura", factura);
        parametros.put("simbolo", instancias.getSimbolo());
        parametros.put("cadenaDecimales", instancias.getCadenaDecimales());
        ejecutar("costeo" + tipo, parametros);
    }
}
