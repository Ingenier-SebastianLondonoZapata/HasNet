package Impresiones.ImpresionesProductos;

import Enums.Reportes;
import Impresiones.GeneradorReporteBase;
import clases.Instancias;
import java.util.HashMap;
import java.util.Map;

public class GeneradorReporteProducto extends GeneradorReporteBase {

    public GeneradorReporteProducto(Instancias instancias) {
        super(instancias);
    }

    public void verAjusteInventario(String factura) {
        String nombreReporte = esTipoImpresionPos()
                ? Reportes.AJUSTES_INVENTARIO_POS.getNombre()
                : Reportes.AJUSTES_INVENTARIO.getNombre();

        Map<String, String> parametros = new HashMap<>();
        parametros.put("factura", factura.replace("TRAS-", ""));
        parametros.put("numFactura", factura);
        parametros.put("info", instancias.getInformacionEmpresa());
        parametros.put("simbolo", instancias.getSimbolo());
        parametros.put("cadenaDecimales", instancias.getCadenaDecimales());
        ejecutar(nombreReporte, parametros);
    }

    public void verIngresoDetalle(String factura) {
        Map<String, String> parametros = new HashMap<>();
        parametros.put("numFactura", factura);
        ejecutar(Reportes.AJUSTES_INVENTARIO_DETALLE.getNombre(), parametros);
    }
}
