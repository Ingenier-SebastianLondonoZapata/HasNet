package Impresiones.ImpresionesCuadreCaja;

import Impresiones.GeneradorReporteBase;
import clases.Instancias;
import java.util.HashMap;
import java.util.Map;

public class GeneradorReporteCuadreCaja extends GeneradorReporteBase {

    public GeneradorReporteCuadreCaja(Instancias instancias) {
        super(instancias);
    }

    public void verCuadreFiscal(String factura, String info) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("idCuadre", factura);
        parametros.put("info", info);
        parametros.put("urlImagen", logo());
        parametros.put("simbolo", instancias.getSimbolo());
        parametros.put("cadenaDecimales", instancias.getCadenaDecimales());
        ejecutar("cuadreFiscal", parametros);
    }

    public void verCuadreCaja(String factura, String tipo, String info) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("idCuadre", factura);
        parametros.put("hora", instancias.isHora() ? "SI" : "NO");
        parametros.put("urlImagen", logo());
        parametros.put("info", info);
        parametros.put("simbolo", instancias.getSimbolo());
        parametros.put("cadenaDecimales", instancias.getCadenaDecimales());
        ejecutar("cuadreCaja" + tipo, parametros);
    }

    public void verBase(String condicion, String info) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("cliente", condicion);
        parametros.put("urlImagen", logo());
        parametros.put("info", info);
        parametros.put("simbolo", instancias.getSimbolo());
        parametros.put("cadenaDecimales", instancias.getCadenaDecimales());
        ejecutar("base", parametros);
    }
}
