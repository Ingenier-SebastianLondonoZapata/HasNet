package Dao.Documentos;

import Modelo.Reportes.CondicionSql;
import Modelo.Reportes.SeleccionReporte;
import java.util.Arrays;
import java.util.List;

public final class ConsultaReporteDocumentos {

    public static final String TIPO = "tipoDocumento";
    public static final String ANULACION = "anulacion";
    public static final String ESTADO = "estado";
    public static final String FORMA_PAGO = "formaPago";
    public static final String IMPUESTOS = "impuestos";
    public static final String CLIENTE = "cliente";
    public static final String VENDEDOR = "vendedor";
    public static final String TERMINAL = "terminal";
    public static final String BODEGA = "bodega";
    public static final String PLACA = "placa";
    public static final String PRODUCTO = "producto";
    public static final String GRUPO = "grupo";
    public static final String HORA = "hora";

    public static final String VIGENTES = "vigentes";
    public static final String ANULADAS = "anuladas";
    public static final String TODAS = "todas";

    public static final String CONTADO = "contado";
    public static final String CREDITO = "credito";
    public static final String SISTECREDITO = "sistecredito";

    public static final String CON_IMPOCONSUMO = "impoconsumo";
    public static final String CON_IVA = "iva";
    public static final String CON_AMBOS = "ambos";
    public static final String EXENTAS = "exentas";

    public static final List<String> TIPOS_VENTA = Arrays.asList("facturacion", "notaDebito");

    private static final String ETIQUETA_TIPO = "CASE d.tipoDocumento WHEN 'facturacion' THEN 'FACTURA' WHEN 'notaDebito' THEN 'NOTA DÉBITO' "
            + "WHEN 'cotizacion' THEN 'COTIZACIÓN' WHEN 'pedido' THEN 'PEDIDO' WHEN 'separe' THEN 'SEPARE' WHEN 'orden' THEN 'ORDEN' "
            + "WHEN 'cuentaCobro' THEN 'CUENTA COBRO' ELSE UPPER(d.tipoDocumento) END";

    private static final String ETIQUETA_FORMA_PAGO = "CASE WHEN d.sisteCredito = 1 THEN 'SISTECRÉDITO' WHEN d.credito = 1 THEN 'CRÉDITO' ELSE 'CONTADO' END";

    private static final String ETIQUETA_ESTADO = "CASE WHEN d.anulada = 1 THEN 'ANULADA' WHEN d.tipoDocumento IN ('cotizacion', 'orden') "
            + "THEN UPPER(COALESCE(NULLIF(TRIM(d.estadoGeneral), ''), 'PENDIENTE')) ELSE 'VIGENTE' END";

    private static final String NOMBRE_VENDEDOR = "CASE WHEN d.vendedor LIKE 'Seleccione%' THEN '' ELSE d.vendedor END";

    private static final String IMPOCONSUMO_DETALLE = "(SELECT COALESCE(SUM(di.impoconsumo), 0) FROM bdDocumentosDetalle di WHERE di.idDocumento = d.idDocumento)";

    private static final String ABONADO = "CASE WHEN d.credito = 1 OR d.tipoDocumento = 'separe' THEN COALESCE("
            + ConsultaCartera.abonosRealizados("d.factura") + ", 0) ELSE 0 END";

    private ConsultaReporteDocumentos() {
    }

    public static String totalizado(SeleccionReporte seleccion) {
        CondicionSql condicion = condicionDocumento(seleccion);
        CondicionSql lineas = new CondicionSql()
                .en("dx.producto", seleccion.valores(PRODUCTO))
                .en("px.grupo", seleccion.valores(GRUPO));
        if (!lineas.estaVacia()) {
            condicion.y("EXISTS (SELECT 1 FROM bdDocumentosDetalle dx LEFT JOIN bdproductos px ON px.idSistema = dx.producto "
                    + "WHERE dx.idDocumento = d.idDocumento AND " + lineas.sinWhere() + ")");
        }

        return "SELECT d.idDocumento AS ordenId, d.factura AS factura, " + ETIQUETA_TIPO + " AS tipo, d.fechaFactura AS fechaFactura, "
                + "CAST(d.horaFactura AS CHAR) AS hora, COALESCE(t.id, d.cliente) AS nit, t.nombre AS nombre, " + NOMBRE_VENDEDOR + " AS vendedor, "
                + ETIQUETA_FORMA_PAGO + " AS formaPago, " + ETIQUETA_ESTADO + " AS estado, "
                + "COALESCE(d.subtotalGeneral, 0) AS subtotal, COALESCE(d.descuentoGeneral, 0) AS descuento, COALESCE(d.ivaGeneral, 0) AS iva, "
                + IMPOCONSUMO_DETALLE + " AS impoconsumo, COALESCE(d.rtFuente, 0) AS rtFuente, COALESCE(d.rtIva, 0) AS rtIva, "
                + "COALESCE(d.totalGeneral, 0) AS total, COALESCE(d.efectivoGeneral, 0) AS efectivo, COALESCE(d.targetaGeneral, 0) AS tarjetaDebito, "
                + "COALESCE(d.tarjetaCredito, 0) AS tarjetaCredito, COALESCE(d.chequeGeneral, 0) AS cheque, COALESCE(d.ncGeneral, 0) AS notaCredito, "
                + ABONADO + " AS abonado, "
                + "CASE WHEN d.credito = 1 OR d.tipoDocumento = 'separe' THEN COALESCE(d.totalGeneral, 0) - " + ABONADO + " ELSE 0 END AS saldo, "
                + "d.terminal AS terminal, d.bodega AS bodega, d.placa AS placa "
                + "FROM bdDocumentos d LEFT JOIN bdterceros t ON t.idSistema = d.cliente "
                + condicion.where() + " ORDER BY d.idDocumento";
    }

    public static String detallado(SeleccionReporte seleccion) {
        CondicionSql condicion = condicionDocumento(seleccion)
                .en("dd.producto", seleccion.valores(PRODUCTO))
                .en("p.grupo", seleccion.valores(GRUPO));

        return "SELECT d.idDocumento AS ordenId, d.factura AS factura, " + ETIQUETA_TIPO + " AS tipo, d.fechaFactura AS fechaFactura, "
                + "CAST(d.horaFactura AS CHAR) AS hora, COALESCE(t.id, d.cliente) AS nit, t.nombre AS nombre, " + NOMBRE_VENDEDOR + " AS vendedor, "
                + ETIQUETA_FORMA_PAGO + " AS formaPago, " + ETIQUETA_ESTADO + " AS estado, d.placa AS placa, d.observacion AS observacion, "
                + "COALESCE(p.Codigo, dd.producto) AS codigo, dd.descripcion AS descripcion, dd.detalle AS detalle, COALESCE(dd.cant2, 0) AS cantidad, "
                + "COALESCE(dd.lista, 0) AS precio, COALESCE(dd.subtotal, 0) AS subtotal, COALESCE(dd.descuento, 0) AS descuento, "
                + "COALESCE(dd.porcIva, 0) AS porcIva, COALESCE(dd.iva, 0) AS iva, COALESCE(dd.impoconsumo, 0) AS impoconsumo, "
                + "COALESCE(dd.total, 0) AS total, COALESCE(dd.utilidad, 0) AS utilidad "
                + "FROM bdDocumentos d INNER JOIN bdDocumentosDetalle dd ON dd.idDocumento = d.idDocumento "
                + "LEFT JOIN bdproductos p ON p.idSistema = dd.producto LEFT JOIN bdterceros t ON t.idSistema = d.cliente "
                + condicion.where() + " ORDER BY d.idDocumento, dd.linea, dd.idDetalle";
    }

    private static CondicionSql condicionDocumento(SeleccionReporte seleccion) {
        List<String> tipos = seleccion.tiene(TIPO) ? seleccion.valores(TIPO) : TIPOS_VENTA;
        String anulacion = seleccion.tiene(ANULACION) ? seleccion.valor(ANULACION) : VIGENTES;

        CondicionSql condicion = new CondicionSql()
                .en("d.tipoDocumento", tipos)
                .ySi(VIGENTES.equals(anulacion), "d.anulada = 0")
                .ySi(ANULADAS.equals(anulacion), "d.anulada = 1")
                .en("d.estadoGeneral", seleccion.valores(ESTADO))
                .alguno(seleccion.valores(FORMA_PAGO), ConsultaReporteDocumentos::condicionFormaPago)
                .alguno(seleccion.valores(IMPUESTOS), ConsultaReporteDocumentos::condicionImpuestos)
                .en("d.cliente", seleccion.valores(CLIENTE))
                .en("d.vendedor", seleccion.valores(VENDEDOR))
                .en("d.terminal", seleccion.valores(TERMINAL))
                .en("d.bodega", seleccion.valores(BODEGA))
                .en("d.placa", seleccion.valores(PLACA));

        if (seleccion.hayFechas()) {
            String columnaFecha = ANULADAS.equals(anulacion) ? "COALESCE(d.fechaAnulacion, d.fechaFactura)" : "d.fechaFactura";
            condicion.entreFechas(columnaFecha, seleccion.getFechaInicial(), seleccion.getFechaFinal());
        }

        List<String> horas = seleccion.valores(HORA);
        if (horas.size() == 2) {
            condicion.y("d.horaFactura BETWEEN " + CondicionSql.texto(horas.get(0)) + " AND " + CondicionSql.texto(horas.get(1)));
        }
        return condicion;
    }

    private static String condicionFormaPago(String forma) {
        switch (forma) {
            case CONTADO:
                return "d.credito = 0 AND d.sisteCredito = 0";
            case CREDITO:
                return "d.credito = 1 AND d.sisteCredito = 0";
            case SISTECREDITO:
                return "d.sisteCredito = 1";
            default:
                return null;
        }
    }

    private static String condicionImpuestos(String impuesto) {
        switch (impuesto) {
            case CON_IMPOCONSUMO:
                return IMPOCONSUMO_DETALLE + " > 0";
            case CON_IVA:
                return "COALESCE(d.ivaGeneral, 0) > 0";
            case CON_AMBOS:
                return "COALESCE(d.ivaGeneral, 0) > 0 AND " + IMPOCONSUMO_DETALLE + " > 0";
            case EXENTAS:
                return "COALESCE(d.ivaGeneral, 0) <= 0 AND " + IMPOCONSUMO_DETALLE + " <= 0";
            default:
                return null;
        }
    }
}
