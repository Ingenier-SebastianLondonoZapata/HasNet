package Impresiones.ImpresionesLaboratorio;

import Impresiones.GeneradorReporteBase;
import clases.Instancias;
import clases.metodosGenerales;
import java.util.HashMap;
import java.util.Map;

public class GeneradorReporteLaboratorio extends GeneradorReporteBase {

    public GeneradorReporteLaboratorio(Instancias instancias) {
        super(instancias);
    }

    public void ver_Examen(String id, String info, String legal, String pie, String edad, String examen) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("id", id);
        parametros.put("urlImagen", logo());
        parametros.put("info", info);
        parametros.put("legal", legal);
        parametros.put("pie", pie);
        parametros.put("edad", edad);
        parametros.put("fecha", metodosGenerales.fecha());
        parametros.put("informacionLegalClick", instancias.getReporte().getInformacionLegalClick());
        parametros.put("firma", System.getProperty("user.dir") + "//imagenes//firmas//" + instancias.getUsuario() + ".jpg");
        ejecutar(examen, parametros);
    }
}
