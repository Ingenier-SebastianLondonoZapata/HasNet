package Impresiones.ImpresionesReportes;

import Dao.Documentos.ConsultaReporteImpuestos;
import Impresiones.GeneradorReporteBase;
import Modelo.Reportes.SeleccionReporte;
import clases.Instancias;
import java.util.HashMap;
import java.util.Map;

public class GeneradorReporteImpuestos extends GeneradorReporteBase {

    private static final String REPORTE = "/Vista/Ventas/Reportes/repImpuestos";

    public GeneradorReporteImpuestos(Instancias instancias) {
        super(instancias);
    }

    public void ventas(SeleccionReporte seleccion) {
        ejecutar(REPORTE, parametros(seleccion, "REPORTE DE IMPUESTOS DE VENTAS", ConsultaReporteImpuestos.ventas(seleccion)));
    }

    public void compras(SeleccionReporte seleccion) {
        ejecutar(REPORTE, parametros(seleccion, "REPORTE DE IMPUESTOS DE COMPRAS", ConsultaReporteImpuestos.compras(seleccion)));
    }

    private Map<String, Object> parametros(SeleccionReporte seleccion, String titulo, String consulta) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("consulta", consulta);
        parametros.put("titulo", titulo);
        parametros.put("encabezado", seleccion.encabezadoFechas("Documentos"));
        parametros.put("filtros", seleccion.resumenFiltros());
        parametros.put("incluyeIva", ConsultaReporteImpuestos.incluyeIva(seleccion));
        parametros.put("incluyeImpoconsumo", ConsultaReporteImpuestos.incluyeImpoconsumo(seleccion));
        parametros.put("simbolo", instancias.getSimbolo());
        parametros.put("cadenaDecimales", instancias.getCadenaDecimales());
        return parametros;
    }
}
