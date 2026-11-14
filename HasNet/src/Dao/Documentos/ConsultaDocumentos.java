package Dao.Documentos;

import Enums.TipoDocumento;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ConsultaDocumentos {

    public static final String ORIGEN = " bdDocumentos d INNER JOIN bdDocumentosDetalle dd ON dd.idDocumento = d.idDocumento ";

    private static final Set<String> ENCABEZADO = new HashSet<>(Arrays.asList(
            "factura", "cliente", "vendedor", "efectivoGeneral", "ncGeneral", "chequeGeneral", "targetaGeneral",
            "totalGeneral", "descuentoGeneral", "ivaGeneral", "subtotalGeneral", "impoconsumoGeneral", "comprobante", "cotizacion",
            "anulada", "anula", "credito", "cxc", "usuario", "rtIva", "rtIca", "rtFuente", "otros", "observacion", "terminal",
            "devuelta", "resolucion", "cuadreAnulacion", "usuarioAnula", "copago", "placa", "garantia", "diasGarantia", "terminos",
            "conseMesa", "turno", "mesFacturado", "tarjetaCredito",
            "totalPropina", "porcPropina", "idCosteo", "sisteCredito", "bodega", "modeloContable", "inicio", "hasta",
            "cantIncremento", "fechaUltimoPago", "cantFacturados"));

    private static final Set<String> DETALLE = new HashSet<>(Arrays.asList(
            "producto", "lista", "cantidad", "descuento", "total", "iva", "subtotal", "impoconsumo", "utilidad", "porcDescuento",
            "descripcion", "plu", "cant2", "estado", "porcIva", "porcImpo", "tercero", "utilidad1", "lote",
            "idProd", "costo", "rango", "detalle"));

    private static final Map<String, String> CONSTANTES = new HashMap<>();

    private static final Map<String, String> RENOMBRADAS = new HashMap<>();

    private static final Set<TipoDocumento> FECHA_HORA = EnumSet.of(
            TipoDocumento.COTIZACION, TipoDocumento.PEDIDO, TipoDocumento.ORDER_SERVICIO, TipoDocumento.MESA);

    private static final Set<TipoDocumento> PLU_TEXTO = EnumSet.of(
            TipoDocumento.COTIZACION, TipoDocumento.PEDIDO, TipoDocumento.PLAN_SEPARE, TipoDocumento.MESA, TipoDocumento.CUENTA_COBRO);

    static {
        CONSTANTES.put("anulada1", "CAST(0 AS UNSIGNED)");
        CONSTANTES.put("credito1", "CAST(0 AS UNSIGNED)");
        CONSTANTES.put("anula1", "''");
        CONSTANTES.put("cxc1", "''");
        CONSTANTES.put("usuario1", "''");
        CONSTANTES.put("NC", "''");
        CONSTANTES.put("concepto", "''");

        RENOMBRADAS.put("hora", "d.horaFactura");
        RENOMBRADAS.put("impoGeneral", "d.impoconsumoGeneral");
    }

    private ConsultaDocumentos() {
    }

    public static String columnas(TipoDocumento tipo, String... nombres) {
        List<String> expresiones = new ArrayList<>();
        for (String nombre : nombres) {
            expresiones.add(expresion(tipo, nombre) + " AS " + nombre);
        }
        return String.join(", ", expresiones);
    }

    public static String traducir(TipoDocumento tipo, String sql, String tablaAntigua) {
        Matcher coincidencia = Pattern.compile("(?i)\\b" + Pattern.quote(tablaAntigua) + "\\.(\\w+)").matcher(sql);
        StringBuffer resultado = new StringBuffer();
        while (coincidencia.find()) {
            coincidencia.appendReplacement(resultado, Matcher.quoteReplacement(expresion(tipo, coincidencia.group(1))));
        }
        coincidencia.appendTail(resultado);
        return resultado.toString();
    }

    public static String traducirCondicion(TipoDocumento tipo, String condicion, String tablaAntigua) {
        String traducida = traducir(tipo, condicion, tablaAntigua);
        for (Map.Entry<String, String> renombrada : RENOMBRADAS.entrySet()) {
            traducida = traducida.replaceAll("(?<![\\w.])" + renombrada.getKey() + "(?!\\w)", Matcher.quoteReplacement(renombrada.getValue()));
        }
        return traducida;
    }

    public static String sinWhere(String condicion) {
        String limpia = condicion == null ? "" : condicion.trim();
        return limpia.toLowerCase().startsWith("where") ? limpia.substring(5).trim() : limpia;
    }

    public static String filtroTipos(List<TipoDocumento> tipos) {
        List<String> valores = new ArrayList<>();
        for (TipoDocumento tipo : tipos) {
            valores.add("'" + tipo.getValor() + "'");
        }
        return "d.tipoDocumento IN (" + String.join(", ", valores) + ")";
    }

    public static String filtroTipo(TipoDocumento tipo) {
        return "d.tipoDocumento = '" + tipo.getValor() + "'";
    }

    public static String expresion(TipoDocumento tipo, String nombre) {
        boolean orden = tipo == TipoDocumento.ORDER_SERVICIO;

        switch (nombre) {
            case "Id":
                return "dd.idDetalle";
            case "fechaFactura":
                return FECHA_HORA.contains(tipo) ? "TIMESTAMP(d.fechaFactura, COALESCE(d.horaFactura, '00:00:00'))" : "d.fechaFactura";
            case "fechaVencimiento":
                return FECHA_HORA.contains(tipo) ? "CAST(d.fechaVencimiento AS DATETIME)" : "d.fechaVencimiento";
            case "fechaAlerta":
            case "fechaAnulacion":
                return FECHA_HORA.contains(tipo) || tipo == TipoDocumento.PLAN_SEPARE ? "CAST(d." + nombre + " AS DATETIME)" : "d." + nombre;
            case "hora":
                return "d.horaFactura";
            case "impoGeneral":
                return "d.impoconsumoGeneral";
            case "plu":
                return PLU_TEXTO.contains(tipo) ? "CAST(dd.plu AS CHAR(255))" : "dd.plu";
            case "preparacion":
                return tipo == TipoDocumento.CUENTA_COBRO ? "COALESCE(d.periodicidad, dd.preparacion)" : "dd.preparacion";
            case "notaAnulacion":
                return tipo == TipoDocumento.MESA ? "COALESCE(dd.consecutivoLinea, d.notaAnulacion)" : "d.notaAnulacion";
            case "red":
                return orden ? "COALESCE(dd.redLinea, d.red)" : "d.red";
            case "estadoGeneral":
                return orden ? "COALESCE(dd.estadoGeneralLinea, d.estadoGeneral)" : "d.estadoGeneral";
            case "estado2":
                return orden ? "COALESCE(dd.estado2Linea, d.estado2)" : "d.estado2";
            default:
                break;
        }

        if (CONSTANTES.containsKey(nombre)) {
            return CONSTANTES.get(nombre);
        }
        if (ENCABEZADO.contains(nombre)) {
            return "d." + nombre;
        }
        if (DETALLE.contains(nombre)) {
            return "dd." + nombre;
        }

        throw new IllegalArgumentException("Columna de documento no soportada: " + nombre);
    }
}
