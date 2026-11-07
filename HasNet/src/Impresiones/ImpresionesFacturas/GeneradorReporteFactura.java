package Impresiones.ImpresionesFacturas;

import Dao.Ventas.DaoFactura;
import Impresiones.Documentos.ServicioImpresionDocumentos;
import Utilidades.DatosMaestra;
import Impresiones.GeneradorReporteBase;
import clases.Instancias;
import clases.metodosGenerales;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class GeneradorReporteFactura extends GeneradorReporteBase {

    private static final String IMPRESORA_PREDETERMINADA = "Predeterminada";
    private static final String IMPRESORA_GRUPO_SIN_ASIGNAR = "Sin_impresora";

    public GeneradorReporteFactura(Instancias instancias) {
        super(instancias);
    }

    public void imprimirFactura(DatosImpresionFactura datos, String etiquetaCopia, boolean imprimir) {
        if (datos.getGrupos().isEmpty()) {
            generarFactura(datos, etiquetaCopia, imprimir, datos.getImpresora(), null, false);
            return;
        }

        for (String grupo : datos.getGrupos()) {
            generarFactura(datos, etiquetaCopia, imprimir, impresoraDeGrupo(grupo), grupo, false);
            ServicioImpresionDocumentos.pausar();
        }
    }

    public void ver_Factura(String observaciones, String info, String legal, String tipo, String pie, String nombreReporte,
            String facturaTerm, boolean imprimir, String titulo, String impresora, Boolean notaDebito) {

        DatosImpresionFactura datos = new DatosImpresionFactura()
                .setFactura(facturaTerm)
                .setNombreReporte(nombreReporte)
                .setImpresora(impresora)
                .setTitulo(titulo)
                .setInformacionEmpresa(info)
                .setObservaciones(observaciones)
                .setLegal(legal)
                .setPie(pie);

        generarFactura(datos, tipo, imprimir, impresora, null, Boolean.TRUE.equals(notaDebito));
    }

    private void generarFactura(DatosImpresionFactura datos, String etiquetaCopia, boolean imprimir,
            String impresora, String grupo, boolean notaDebito) {

        String facturaTerm = datos.getFactura();
        String consecutivo = notaDebito ? facturaTerm.replace("ND-", "") : facturaTerm.replace("FACT-", "");

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("factura", consecutivo);
        parametros.put("numFactura", facturaTerm);
        parametros.put("agrupar", instancias.getConfiguraciones().isRestaurante());
        parametros.put("grupo", grupo);
        parametros.put("simbolo", instancias.getSimbolo());
        parametros.put("cadenaDecimales", instancias.getCadenaDecimales());
        parametros.put("urlImagen", logo());
        parametros.put("observaciones", datos.getObservaciones());
        parametros.put("info", datos.getInformacionEmpresa());
        parametros.put("legal", datos.getLegal());
        parametros.put("tipoFact", etiquetaCopia);
        parametros.put("pie", datos.getPie());
        parametros.put("ordenServicio", usaOrdenServicioAutomotor() ? "SI" : "NO");
        parametros.put("titulo2", tituloSegunConsecutivo(datos.getTitulo(), facturaTerm));
        parametros.put("hora", DatosMaestra.isImprimirHora() ? "SI" : "NO");
        parametros.put("titulo", notaDebito ? "Nota Debito No." : DatosMaestra.getTituloFactura());
        parametros.put("informacionLegalClick", instancias.getReporte().getInformacionLegalClick());
        parametros.put("firma", System.getProperty("user.dir") + "//imagenes//firmas//" + instancias.getUsuario() + ".jpg");
        parametros.put("impoconsumo", String.valueOf(DatosMaestra.isImprimirImpoconsumo()));
        parametros.put("retenciones", String.valueOf(DatosMaestra.isImprimirRetenciones()));

        if (IMPRESORA_PREDETERMINADA.equalsIgnoreCase(impresora)) {
            ejecutar(datos.getNombreReporte(), parametros, imprimir);
        } else {
            ejecutarEnImpresora(datos.getNombreReporte(), parametros, imprimir, impresora);
        }
    }

    private String tituloSegunConsecutivo(String titulo, String facturaTerm) {
        if (titulo == null || titulo.trim().isEmpty()) {
            return "";
        }
        return new DaoFactura().obtenerTurnoFactura(facturaTerm).isEmpty() ? "" : titulo;
    }

    private String impresoraDeGrupo(String grupo) {
        if (grupo.isEmpty()) {
            return IMPRESORA_GRUPO_SIN_ASIGNAR;
        }
        Object[] datosGrupo = instancias.getSql().getDatosGrupo(grupo);
        return datosGrupo[5].toString();
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
