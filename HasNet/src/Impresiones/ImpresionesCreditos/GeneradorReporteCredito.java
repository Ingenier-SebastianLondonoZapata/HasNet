package Impresiones.ImpresionesCreditos;

import Impresiones.GeneradorReporteBase;
import clases.Instancias;
import java.util.HashMap;
import java.util.Map;

public class GeneradorReporteCredito extends GeneradorReporteBase {

    public GeneradorReporteCredito(Instancias instancias) {
        super(instancias);
    }

    public void verCredito(String contrato) {
        String nombreReporte = "credito" + Instancias.getInstancias().getTipoImpresion();

        Map<String, String> parametros = new HashMap<>();
        parametros.put("id", contrato);
        parametros.put("info", instancias.getInformacionEmpresa());
        ejecutar(nombreReporte, parametros);
    }
}
