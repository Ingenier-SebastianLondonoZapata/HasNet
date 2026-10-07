package Impresiones.ImpresionesAbonos;

import Impresiones.GeneradorReporteBase;
import clases.Instancias;
import java.util.HashMap;
import java.util.Map;

public class GeneradorReporteAbono extends GeneradorReporteBase {

    public GeneradorReporteAbono(Instancias instancias) {
        super(instancias);
    }

    public void verAbono(String abono, String tipo, String info) {
        ejecutar("abonoNuevo" + instancias.getTipoImpresion(), parametrosAbono(abono, tipo, info));
    }

    public void verAbonoGeneral(String abono, String tipo, String info) {
        ejecutar("abonoNuevo" + instancias.getTipoImpresion(), parametrosAbono(abono, tipo, info));
    }

    private Map<String, Object> parametrosAbono(String abono, String tipo, String info) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("abono", abono);
        parametros.put("info", info);
        parametros.put("tipo", tipo);
        parametros.put("urlImagen", logo());
        parametros.put("simbolo", instancias.getSimbolo());
        parametros.put("cadenaDecimales", instancias.getCadenaDecimales());
        return parametros;
    }
}
