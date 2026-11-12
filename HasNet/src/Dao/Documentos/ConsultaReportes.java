package Dao.Documentos;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ConsultaReportes {

    private static final String FECHA_HORA = "TIMESTAMP(d.fechaFactura, COALESCE(d.horaFactura, '00:00:00'))";
    private static final Map<String, String> COLUMNAS_COTIZACIONES = new LinkedHashMap<>();
    private static final Map<String, String> COLUMNAS_ORDENES = new LinkedHashMap<>();

    static {
        COLUMNAS_COTIZACIONES.put("cliente", "d.cliente");
        COLUMNAS_COTIZACIONES.put("producto", "p.idSistema");
        COLUMNAS_COTIZACIONES.put("estadoGeneral", "d.estadoGeneral");
        COLUMNAS_COTIZACIONES.put("fechaFactura", FECHA_HORA);

        COLUMNAS_ORDENES.put("terminal", "d.terminal");
        COLUMNAS_ORDENES.put("cliente", "d.cliente");
        COLUMNAS_ORDENES.put("placa", "o.placa");
        COLUMNAS_ORDENES.put("estadoGeneral", "COALESCE(dd.estadoGeneralLinea, d.estadoGeneral)");
        COLUMNAS_ORDENES.put("fechaFactura", FECHA_HORA);
    }

    public static final String COTIZACIONES = "SELECT " + FECHA_HORA + " AS fechaFactura, d.cliente AS cliente, t.nombre AS nombre, d.vendedor AS vendedor, "
            + "d.observacion AS observacion, p.Codigo AS Codigo, p.Descripcion AS Descripcion, SUM(dd.cant2) AS cantidad, dd.lista AS lista, "
            + "SUM(dd.subtotal) AS subtotal, SUM(dd.descuento) AS descuento, dd.porcIva AS porcIva, SUM(dd.iva) AS iva, SUM(dd.total) AS total, "
            + "SUM(dd.utilidad) AS utilidad, d.subtotalGeneral AS subtotalGeneral, d.descuentoGeneral AS descuentoGeneral, d.otros AS otros, "
            + "d.rtFuente AS rtFuente, d.rtIva AS rtIva, d.ivaGeneral AS ivaGeneral, d.totalGeneral AS totalGeneral, d.efectivoGeneral AS efectivoGeneral, "
            + "d.targetaGeneral AS targetaGeneral, d.chequeGeneral AS chequeGeneral, d.ncGeneral AS ncGeneral, dd.tercero AS tercero, "
            + "dd.utilidad1 AS utilidad1, d.idFactura AS idFactura, d.factura AS id2, d.credito AS credito, d.anulada AS anulada, t.id AS Id, "
            + "p.grupo AS Grupo, p.idSistema AS producto, d.estadoGeneral AS estadoGeneral\n"
            + "FROM bdDocumentos d INNER JOIN bdDocumentosDetalle dd ON dd.idDocumento = d.idDocumento\n"
            + "LEFT JOIN bdproductos p ON p.idSistema = dd.producto LEFT JOIN bdterceros t ON t.idSistema = d.cliente\n"
            + "$P!{cliente}\n"
            + "GROUP BY " + FECHA_HORA + ", d.cliente, t.nombre, d.vendedor, d.observacion, p.Codigo, dd.lista, dd.porcIva, d.subtotalGeneral, "
            + "d.descuentoGeneral, d.otros, d.rtFuente, d.rtIva, d.ivaGeneral, d.totalGeneral, d.efectivoGeneral, d.targetaGeneral, d.chequeGeneral, "
            + "d.ncGeneral, dd.tercero, dd.utilidad1, d.idFactura, d.factura, d.credito, d.anulada, t.id, p.grupo, p.idSistema, d.estadoGeneral\n"
            + "ORDER BY d.idFactura ASC";

    public static final String ORDENES = "SELECT d.idFactura AS idFactura, d.cliente AS cliente, t.nombre AS nombre, d.subtotalGeneral AS subtotalGeneral, "
            + "d.ivaGeneral AS ivaGeneral, d.totalGeneral AS totalGeneral, " + FECHA_HORA + " AS fechaFactura, d.anulada AS anulada, d.rtIva AS rtIva, "
            + "d.rtIca AS rtIca, d.rtFuente AS rtFuente, d.otros AS otros, dd.descuento AS descuento, d.efectivoGeneral AS efectivoGeneral, '' AS nc, "
            + "d.chequeGeneral AS chequeGeneral, d.targetaGeneral AS targetaGeneral, d.credito AS credito, d.bodega AS bodega, o.placa AS placa, "
            + "o.tipo AS tipo, o.modelo AS modelo, o.numeroChasis AS numeroChasis, COALESCE(dd.estadoGeneralLinea, d.estadoGeneral) AS estadoGeneral, "
            + "o.marca AS marca, o.km AS km, d.terminal AS terminal, d.diasGarantia AS diasGarantia, t.id AS idCliente\n"
            + "FROM bdDocumentos d INNER JOIN bdDocumentosDetalle dd ON dd.idDocumento = d.idDocumento\n"
            + "LEFT JOIN bdoservicio o ON o.id = d.idFactura LEFT JOIN bdterceros t ON t.idSistema = d.cliente\n"
            + "$P!{cliente}\n"
            + "GROUP BY d.idFactura, d.cliente, t.nombre, d.subtotalGeneral, d.ivaGeneral, d.totalGeneral, " + FECHA_HORA + ", d.anulada, d.rtIva, "
            + "d.rtIca, d.rtFuente, d.otros, dd.descuento, d.efectivoGeneral, d.chequeGeneral, d.targetaGeneral, d.credito, d.bodega, o.placa, o.tipo, "
            + "o.modelo, o.numeroChasis, COALESCE(dd.estadoGeneralLinea, d.estadoGeneral), o.marca, o.km, d.terminal, d.diasGarantia, t.id\n"
            + "ORDER BY d.idFactura ASC";

    public static final String SEPARES = "SELECT * FROM (SELECT d.idFactura AS id, d.factura AS id2, d.cliente AS cliente, t.nombre AS nombre, "
            + "d.subtotalGeneral AS subtotal, d.ivaGeneral AS iva, d.totalGeneral AS total, d.fechaFactura AS fechaFactura, d.anulada AS anulada, "
            + "d.rtIva AS rtIva, d.rtIca AS rtIca, d.rtFuente AS rtFuente, d.otros AS otros, d.descuentoGeneral AS descuento, d.efectivoGeneral AS efectivo, "
            + "'0' AS nc, d.chequeGeneral AS cheque, d.targetaGeneral AS targeta, d.credito AS credito, d.bodega AS bodega, d.terminal AS terminal, "
            + "t.id AS idTercero FROM bdDocumentos d INNER JOIN bdterceros t ON t.idSistema = d.cliente "
            + "WHERE d.tipoDocumento = 'separe' AND d.anulada = 0) repSeparesDiario\n$P!{cliente}\nORDER BY id ASC";

    public static final String PLANTILLAS = "SELECT * FROM (SELECT d.idFactura AS id, CAST(SUBSTR(d.idFactura, 8, 100) AS SIGNED) AS id2, d.cliente AS cliente, "
            + "t.nombre AS nombre, d.subtotalGeneral AS subtotal, d.ivaGeneral AS iva, d.totalGeneral AS total, d.fechaFactura AS fechaFactura, "
            + "d.anulada AS anulada, d.rtIva AS rtIva, d.rtIca AS rtIca, d.rtFuente AS rtFuente, d.otros AS otros, d.descuentoGeneral AS descuento, "
            + "d.efectivoGeneral AS efectivo, '0' AS nc, d.chequeGeneral AS cheque, d.targetaGeneral AS targeta, d.credito AS credito, d.bodega AS bodega, "
            + "d.periodicidad AS periodicidad, d.inicio AS inicio, d.hasta AS hasta, d.fechaUltimoPago AS fechaUltimoPago, d.cantIncremento AS cantIncremento, "
            + "t.id AS idTercero FROM bdDocumentos d INNER JOIN bdterceros t ON t.idSistema = d.cliente "
            + "WHERE d.tipoDocumento = 'cuentaCobro' AND d.anulada = 0) repPlantillasDiario\n$P!{cliente}\nORDER BY id ASC";

    public static String ordenServicio(String rango) {
        return "SELECT DISTINCT d.idFactura AS idFactura, b.ordenServicio AS ordenServicio, b.nombreParte AS nombreParte, b.inventario AS inventario, "
                + "b.problemasDerecha AS problemasDerecha, b.problemasIzquierda AS problemasIzquierda, b.observaciones AS observaciones, b.num AS num\n"
                + "FROM bddetalleordenservicio b INNER JOIN bdDocumentos d ON d.idFactura = b.ordenServicio AND d.tipoDocumento = 'orden'\n"
                + "WHERE " + rango + " AND d.idFactura = $P{numero}\n"
                + "AND EXISTS (SELECT 1 FROM bdDocumentosDetalle x WHERE x.idDocumento = d.idDocumento)";
    }

    private ConsultaReportes() {
    }

    public static String filtroCotizaciones(String condicion) {
        return filtro("d.tipoDocumento = 'cotizacion'", condicion, COLUMNAS_COTIZACIONES);
    }

    public static String filtroOrdenes(String condicion) {
        return filtro("d.tipoDocumento = 'orden' AND d.anulada = 0", condicion, COLUMNAS_ORDENES);
    }

    public static String impuestos(String condicion, boolean impoconsumo) {
        String porcentaje = impoconsumo ? "porcImpo" : "porcIva";
        Map<String, String> columnas = new LinkedHashMap<>();
        columnas.put("fechaFactura", "d.fechaFactura");
        return "SELECT d.fechaFactura AS fechaFactura, dd." + porcentaje + " AS " + porcentaje + ", SUM(dd." + (impoconsumo ? "impoconsumo" : "iva") + ") AS SumaDeiva, "
                + "SUM(dd.subtotal) AS SumaDesubtotal, SUM(dd.descuento) AS SumaDedescuento, SUM(dd.total) AS SumaDetotal, '' AS producto, '' AS cliente, '' AS vendedor "
                + "FROM bdDocumentos d INNER JOIN bdDocumentosDetalle dd ON dd.idDocumento = d.idDocumento "
                + filtro(ConsultaCartera.FACTURAS + " AND d.anulada = 0", condicion, columnas)
                + " GROUP BY d.fechaFactura, dd." + porcentaje;
    }

    private static String filtro(String base, String condicion, Map<String, String> columnas) {
        String limpia = ConsultaDocumentos.sinWhere(condicion);
        return "WHERE " + base + (limpia.isEmpty() ? "" : " AND (" + traducirNombres(limpia, columnas) + ")");
    }

    public static String traducirNombres(String condicion, Map<String, String> columnas) {
        StringBuilder resultado = new StringBuilder();
        Matcher literal = Pattern.compile("'(?:[^']|'')*'").matcher(condicion);
        int inicio = 0;
        while (literal.find()) {
            resultado.append(reemplazar(condicion.substring(inicio, literal.start()), columnas)).append(literal.group());
            inicio = literal.end();
        }
        resultado.append(reemplazar(condicion.substring(inicio), columnas));
        return resultado.toString();
    }

    private static String reemplazar(String texto, Map<String, String> columnas) {
        String resultado = texto;
        for (Map.Entry<String, String> columna : columnas.entrySet()) {
            resultado = resultado.replaceAll("(?i)(?<![\\w.])" + columna.getKey() + "(?![\\w(])", Matcher.quoteReplacement(columna.getValue()));
        }
        return resultado;
    }
}
