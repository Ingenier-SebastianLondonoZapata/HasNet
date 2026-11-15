package Impresiones.ImpresionesReportes;

import Dao.Documentos.ConsultaReporteDocumentos;
import Impresiones.GeneradorReporteBase;
import Modelo.Reportes.SeleccionReporte;
import clases.Instancias;
import java.util.HashMap;
import java.util.Map;

public class GeneradorReporteDocumentos extends GeneradorReporteBase {

    private static final String TOTALIZADO = "/Vista/Ventas/Reportes/repDocumentosTotalizado";
    private static final String DETALLADO = "/Vista/Ventas/Reportes/repDocumentosDetallado";

    public GeneradorReporteDocumentos(Instancias instancias) {
        super(instancias);
    }

    public void totalizado(SeleccionReporte seleccion, String titulo) {
        ejecutar(TOTALIZADO, parametros(seleccion, titulo, ConsultaReporteDocumentos.totalizado(seleccion)));
    }

    public void detallado(SeleccionReporte seleccion, String titulo) {
        ejecutar(DETALLADO, parametros(seleccion, titulo, ConsultaReporteDocumentos.detallado(seleccion)));
    }

    private Map<String, Object> parametros(SeleccionReporte seleccion, String titulo, String consulta) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("consulta", consulta);
        parametros.put("titulo", titulo);
        parametros.put("encabezado", seleccion.encabezadoFechas("Documentos"));
        parametros.put("filtros", seleccion.resumenFiltros());
        parametros.put("mostrarPlaca", instancias.getConfiguraciones().isServicioAutomotor());
        parametros.put("simbolo", instancias.getSimbolo());
        parametros.put("cadenaDecimales", instancias.getCadenaDecimales());
        return parametros;
    }
}
