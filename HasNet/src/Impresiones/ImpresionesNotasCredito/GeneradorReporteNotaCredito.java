package Impresiones.ImpresionesNotasCredito;

import Impresiones.GeneradorReporteBase;
import clases.Instancias;
import java.util.HashMap;
import java.util.Map;

public class GeneradorReporteNotaCredito extends GeneradorReporteBase {

    public GeneradorReporteNotaCredito(Instancias instancias) {
        super(instancias);
    }

    public void ver_Nc(String factura, String observaciones, String info) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("factura", factura.replace("NC-", ""));
        parametros.put("numFactura", factura);
        parametros.put("observaciones", observaciones);
        parametros.put("regimen", instancias.getRegimen().equals("") ? "" : "SinIva");
        parametros.put("urlImagen", logo());
        parametros.put("info", info);
        parametros.put("informacionLegalClick", instancias.getReporte().getInformacionLegalClick());
        parametros.put("simbolo", instancias.getSimbolo());
        parametros.put("cadenaDecimales", instancias.getCadenaDecimales());
        ejecutar("nc" + instancias.getTipoImpresion(), parametros);
    }
}
