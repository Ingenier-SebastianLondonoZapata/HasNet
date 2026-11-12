package Dao.Documentos;

public final class ConsultaCartera {

    public static final String FACTURAS = "d.tipoDocumento IN ('facturacion', 'notaDebito')";
    public static final String ESTADOS_ABONO = "('pend', 'cancelada', '', 'ABONO-REALIZADO')";

    private ConsultaCartera() {
    }

    public static String abonosRealizados(String documento) {
        return "(SELECT SUM(a.valor) FROM bdcxc a WHERE a.factura2 = " + documento + " AND a.tipo = 'ABONO' AND a.estado = 'ABONO-REALIZADO')";
    }

    public static String abonosRealizadosInterno(String idInterno) {
        return "(SELECT SUM(a.valor) FROM bdcxc a WHERE a.factura = " + idInterno + " AND a.tipo = 'ABONO' AND a.estado = 'ABONO-REALIZADO')";
    }

    public static String abonosRegistrados(String idInterno) {
        return "(SELECT SUM(a.valor) FROM bdcxc a WHERE a.factura = " + idInterno + " AND a.tipo = 'ABONO' AND a.estado IN " + ESTADOS_ABONO + ")";
    }

    public static String saldo(String valor, String abonos) {
        return "IF(" + abonos + " IS NULL, " + valor + ", " + valor + " - " + abonos + ")";
    }

    public static String estadoVencimiento(String alerta, String vencimiento) {
        return "IF(CAST(NOW() AS DATE) >= " + alerta + " AND CAST(NOW() AS DATE) < " + vencimiento + ", 'ALERTA', "
                + "IF(CAST(NOW() AS DATE) >= " + vencimiento + ", 'VENCIDA', 'OK'))";
    }

    public static String orden(String alerta, String vencimiento) {
        return "IF(CAST(NOW() AS DATE) >= " + alerta + " AND CAST(NOW() AS DATE) < " + vencimiento + ", '2', "
                + "IF(CAST(NOW() AS DATE) >= " + vencimiento + ", '1', '3'))";
    }

    public static String cuentasPendientes(String condicionDocumento, String tipoCxc, boolean terceroObligatorio, String edad) {
        String abonos = abonosRealizados("d.factura");
        return "SELECT d.factura AS facturaTerm, cx.factura AS factura, t.id AS cliente, t.nombre AS nombre, d.fechaFactura AS fechaFactura, "
                + "d.fechaVencimiento AS fechaVencimiento, cx.valor AS valor, " + saldo("cx.valor", abonos) + " AS saldo, "
                + edad + " AS edadCartera, "
                + estadoVencimiento("d.fechaAlerta", "d.fechaVencimiento") + " AS estadoVencimiento, "
                + orden("d.fechaAlerta", "d.fechaVencimiento") + " AS orden, d.terminal AS terminal, cx.cuotas AS cuotas, "
                + "d.turno AS turno, d.vendedor AS vendedor, d.placa AS placa, CAST(SUBSTR(d.idFactura, 6, 100) AS SIGNED) AS ordenId "
                + "FROM bdDocumentos d "
                + "INNER JOIN bdcxc cx ON cx.factura2 = d.factura "
                + (terceroObligatorio ? "INNER" : "LEFT") + " JOIN bdterceros t ON t.idSistema = d.cliente "
                + "WHERE " + FACTURAS + " AND d.anulada = 0 AND cx.factura <> '0' AND cx.tipo = '" + tipoCxc + "' AND cx.estado = 'PEND' AND (" + condicionDocumento + ")";
    }

    public static String carteraFacturas() {
        return "SELECT d.idFactura AS fact, cx.tipo AS tipo, cx.estado AS estado, cx.recibo AS recibo, cx.plazo AS plazo, cx.vencimiento AS vencimiento, "
                + "d.cliente AS cliente, t.nombre AS nombre, d.fechaFactura AS fechaFactura, d.anulada AS anulada, "
                + saldo("cx.valor", abonosRealizados("d.factura")) + " AS Expr1, d.vendedor AS vendedor, d.idFactura AS factura, d.terminal AS terminal, "
                + "d.factura AS factura2, cx.valor AS valor, t.telefono AS telefono, CAST((cx.vencimiento - CAST(NOW() AS DATE)) AS SIGNED) AS edadCartera, "
                + "IF(CAST(NOW() AS DATE) >= cx.vencimiento, 'VENCIDA', 'OK') AS estadoVenc, d.turno AS turno, d.placa AS placa, t.direccion AS direccion, "
                + "t.ciudad AS ciudad, t.id AS idSistema, d.conseMesa AS conseMesa "
                + "FROM bdcxc cx INNER JOIN bdDocumentos d ON d.factura = cx.factura2 AND " + FACTURAS + " "
                + "LEFT JOIN bdterceros t ON t.idSistema = d.cliente "
                + "WHERE cx.tipo = 'FACT' AND cx.estado <> 'CANCELADA' AND d.anulada = 0";
    }

    public static String carteraPorInterno(String tipoDocumento, String tipoCxc, String cliente, String factura, String idSistema) {
        return "SELECT " + factura + " AS fact, cx.tipo AS tipo, cx.estado AS estado, cx.recibo AS recibo, cx.plazo AS plazo, cx.vencimiento AS vencimiento, "
                + cliente + " AS cliente, t.nombre AS nombre, d.fechaFactura AS fechaFactura, d.anulada AS anulada, "
                + saldo("cx.valor", abonosRealizadosInterno("cx.factura")) + " AS Expr1, d.vendedor AS vendedor, " + factura + " AS factura, "
                + "d.terminal AS terminal, cx.valor AS valor, t.telefono AS telefono, CAST((cx.vencimiento - CURDATE()) AS SIGNED) AS edadCartera, "
                + "IF(NOW() >= cx.vencimiento, 'VENCIDA', 'OK') AS estadoVenc, d.placa AS placa, t.direccion AS direccion, t.ciudad AS ciudad, "
                + idSistema + " AS idSistema "
                + "FROM bdcxc cx INNER JOIN bdDocumentos d ON d.idFactura = cx.factura AND d.tipoDocumento = '" + tipoDocumento + "' "
                + "LEFT JOIN bdterceros t ON t.idSistema = d.cliente "
                + "WHERE cx.tipo = '" + tipoCxc + "' AND cx.estado <> 'CANCELADA' AND d.anulada = 0";
    }

    public static String carteraSepares() {
        return carteraPorInterno("separe", "SEPARE", "t.idSistema", "d.cotizacion", "t.id");
    }

    public static String carteraCuentasCobro() {
        return carteraPorInterno("cuentaCobro", "CUENTACOBRO", "t.id", "cx.factura", "t.idSistema");
    }

    public static String abonos(String condicionDocumento, String turno) {
        return "SELECT d.factura AS factura, t.nombre AS nombre, cx.factura2 AS factura2, d.fechaFactura AS fechaFactura, d.fechaVencimiento AS fechaVencimiento, "
                + turno + " AS turno, d.placa AS placa, cx.valor AS valor, t.id AS cliente, cx.tipo AS tipo, d.anulada AS anulada, cx.estado AS estado, "
                + "t.direccion AS direccion, t.ciudad AS ciudad, t.telefono AS telefono, t.idSistema AS idSistema, cx.recibo AS recibo, "
                + "cx.vencimiento AS vencimiento, d.terminal AS terminal, ab.id AS id, ab.efectivo AS efectivo, ab.cheque AS cheque, ab.nc AS nc, "
                + "ab.rtIva AS rtIva, ab.rtIca AS rtIca, ab.rtFuente AS rtFuente, ab.descuentos AS descuentos, ab.comprobante AS tipoFact, "
                + "ab.targeta AS targeta, ab.otros AS otros "
                + "FROM bdDocumentos d INNER JOIN bdterceros t ON t.idSistema = d.cliente "
                + "INNER JOIN bdcxc cx ON cx.factura2 = d.factura INNER JOIN bdabonos ab ON ab.id = cx.recibo "
                + "WHERE " + condicionDocumento + " AND cx.tipo = 'ABONO'";
    }

    public static String origenAbonos(String sql) {
        String limpio = sql == null ? "" : sql.trim();
        if (limpio.matches("(?i)^repAbonosSepare\\b.*")) {
            return "(" + abonos("d.tipoDocumento = 'separe'", "''") + ") repAbonosSepare" + limpio.substring("repAbonosSepare".length());
        }
        if (limpio.matches("(?i)^repAbonos\\b.*")) {
            return "(" + abonos(FACTURAS, "d.turno") + ") repAbonos" + limpio.substring("repAbonos".length());
        }
        return limpio;
    }

    public static String abonoImpreso() {
        String restante = abonosRealizados("bg.AbonoGeneral");
        return "SELECT ab.id AS id, ab.fecha AS fecha, t.nombre AS nombre, t.id AS cliente, ab.numFactura AS numFactura, ab.totalFactura AS totalFactura, "
                + "ab.comprobante AS comprobante, ab.abonoActual AS abonoActual, ab.abonoLetras AS abonoLetras, ab.abonosAnteriores AS abonosAnteriores, "
                + "pa.cheque AS cheque, pa.targeta AS targeta, pa.efectivo AS efectivo, pa.nc AS nc, pa.rtIva AS rtIva, pa.rtIca AS rtIca, pa.rtFuente AS rtFuente, "
                + "pa.otros AS otros, pa.descuentos AS descuentos, bg.AbonoGeneral AS abonoGeneral, bg.saldoTotal AS saldoTotalGeneral, bg.valor AS valorGeneral, "
                + "bg.enLetra AS enLetra, IF(" + restante + " IS NULL, 0, bg.saldoTotal - " + restante + ") AS saldoFaltante, ab.abono AS abono, SUBSTR(ab.abono, 7) AS abono2 "
                + "FROM bdabono ab LEFT JOIN bdabonos pa ON pa.factura = ab.abono LEFT JOIN bdterceros t ON t.idSistema = ab.cliente "
                + "LEFT JOIN bdabonogeneral bg ON bg.abono = ab.abono "
                + "WHERE ab.abono = $P{abono} "
                + "GROUP BY ab.fecha, t.nombre, t.id, ab.numFactura, ab.totalFactura, ab.comprobante, ab.abonoActual, ab.abonoLetras, ab.abonosAnteriores, "
                + "pa.cheque, pa.targeta, pa.efectivo, pa.nc, pa.rtIva, pa.rtIca, pa.rtFuente, pa.otros, pa.descuentos, bg.AbonoGeneral, bg.saldoTotal, bg.valor, "
                + "saldoFaltante, ab.abono, abono2";
    }

    public static final String EDAD_DIAS = "(TO_DAYS(d.fechaVencimiento) - TO_DAYS(CAST(NOW() AS DATE)))";
    public static final String EDAD_NUMERICA = "CAST((d.fechaVencimiento - CAST(NOW() AS DATE)) AS SIGNED)";
    public static final String CONSECUTIVO_NORMAL = "d.congelada = 'SIN-CONSECUTIVO' OR d.congelada LIKE 'CONG%' OR d.congelada LIKE 'Mesa.%'";
    public static final String CONSECUTIVO_DOMICILIO = "d.congelada = 'DOMICILIO'";
}
