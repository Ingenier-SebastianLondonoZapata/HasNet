package Impresiones.ImpresionesFacturas;

import Impresiones.GeneradorReporteBase;
import clases.Instancias;
import clases.metodosGenerales;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class GeneradorReporteFactura extends GeneradorReporteBase {

    private static final String IMPRESORA_PREDETERMINADA = "Predeterminada";

    public GeneradorReporteFactura(Instancias instancias) {
        super(instancias);
    }

    public void ver_Factura(String observaciones, String info, String legal, String tipo, String pie, String nombreReporte,
            String facturaTerm, boolean imprimir, String titulo, String impresora, String verImpo, String verReten,
            String condicion, Boolean notaDebito) {
        
        System.out.println("condicion: " + condicion);
        
        Map<String, Object> parametros = new HashMap<>();
        
        String consecutivo = notaDebito ? facturaTerm.replace("ND-", "") : facturaTerm.replace("FACT-", "");
        parametros.put("factura", consecutivo);
        parametros.put("simbolo", instancias.getSimbolo());
        parametros.put("cadenaDecimales", instancias.getCadenaDecimales());
        parametros.put("numFactura", condicion);
        parametros.put("urlImagen", logo());
        parametros.put("observaciones", observaciones);
        parametros.put("info", info);
        parametros.put("legal", legal);
        parametros.put("tipoFact", tipo);
        parametros.put("pie", pie);
        parametros.put("ordenServicio", usaOrdenServicioAutomotor() ? "SI" : "NO");
        parametros.put("titulo2", titulo);
        parametros.put("hora", instancias.isHora() ? "SI" : "NO");
        parametros.put("titulo", notaDebito ? "Nota Debito No." : instancias.getTituloFactura());
        parametros.put("informacionLegalClick", instancias.getReporte().getInformacionLegalClick());
        parametros.put("firma", System.getProperty("user.dir") + "//imagenes//firmas//" + instancias.getUsuario() + ".jpg");
        parametros.put("impoconsumo", verImpo);
        parametros.put("retenciones", verReten);

        if (IMPRESORA_PREDETERMINADA.equalsIgnoreCase(impresora)) {
            ejecutar(nombreReporte, parametros, imprimir);
        } else {
            ejecutarEnImpresora(nombreReporte, parametros, imprimir, impresora);
        }
    }

    public void ver_PrefacturaVenta(String condicion, String factura, String observaciones, String facturaTerm, String mesa,
            String info, BigDecimal propina, Boolean previsualizar, String impresora) {
        String sufijo = instancias.getRegimen().equals("") ? "" : "SinIva";

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("factura", facturaTerm.replace("FACT-", ""));
        parametros.put("numFactura", factura);
        parametros.put("observaciones", observaciones);
        parametros.put("hora", metodosGenerales.fechaHora());
        parametros.put("info", info);
        parametros.put("sql", condicion);
        parametros.put("porcPropina", propina);
        parametros.put("simbolo", instancias.getSimbolo());
        parametros.put("cadenaDecimales", instancias.getCadenaDecimales());
        ejecutarEnImpresora("preFactura" + sufijo, parametros, !previsualizar, impresora);
    }

    public void ver_Ubicacion(String factura, boolean imprimir) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("factura", factura.replace("FACT-", ""));
        parametros.put("numFactura", factura);
        parametros.put("urlImagen", logo());
        ejecutar("ubicacion", parametros, imprimir);
    }

    private boolean usaOrdenServicioAutomotor() {
        return instancias.getConfiguraciones().isOrdenServicio() && instancias.getConfiguraciones().isServicioAutomotor();
    }
}
