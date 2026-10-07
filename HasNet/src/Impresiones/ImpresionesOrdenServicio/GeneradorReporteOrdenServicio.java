package Impresiones.ImpresionesOrdenServicio;

import Impresiones.GeneradorReporteBase;
import clases.Instancias;
import java.util.HashMap;
import java.util.Map;

public class GeneradorReporteOrdenServicio extends GeneradorReporteBase {

    public GeneradorReporteOrdenServicio(Instancias instancias) {
        super(instancias);
    }

    public void ver_oServicio(String factura, String observaciones, boolean imprimir, String tipoVehiculo, String tipo1) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("orden", factura);
        parametros.put("numOrden", factura.replace("OSERV-", ""));
        parametros.put("urlImagen", logo());
        parametros.put("observaciones", observaciones);
        parametros.put("info", instancias.getInformacionEmpresa());
        parametros.put("legal", instancias.getLegal());
        parametros.put("pie", instancias.getPie());
        parametros.put("simbolo", instancias.getSimbolo());
        parametros.put("cadenaDecimales", instancias.getCadenaDecimales());
        parametros.put("informacionLegalClick", instancias.getReporte().getInformacionLegalClick());
        ejecutar(tipo1, parametros, imprimir);
    }
}
