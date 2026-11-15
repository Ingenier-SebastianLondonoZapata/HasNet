package Dao.Documentos;

public final class ConsultaReportes {

    public static final String PLANTILLAS = "SELECT * FROM (SELECT d.factura AS id, CAST(SUBSTRING_INDEX(d.factura, '-', -1) AS SIGNED) AS id2, d.idDocumento AS ordenId, d.cliente AS cliente, "
            + "t.nombre AS nombre, d.subtotalGeneral AS subtotal, d.ivaGeneral AS iva, d.totalGeneral AS total, d.fechaFactura AS fechaFactura, "
            + "d.anulada AS anulada, d.rtIva AS rtIva, d.rtIca AS rtIca, d.rtFuente AS rtFuente, d.otros AS otros, d.descuentoGeneral AS descuento, "
            + "d.efectivoGeneral AS efectivo, '0' AS nc, d.chequeGeneral AS cheque, d.targetaGeneral AS targeta, d.credito AS credito, d.bodega AS bodega, "
            + "d.periodicidad AS periodicidad, d.inicio AS inicio, d.hasta AS hasta, d.fechaUltimoPago AS fechaUltimoPago, d.cantIncremento AS cantIncremento, "
            + "t.id AS idTercero FROM bdDocumentos d INNER JOIN bdterceros t ON t.idSistema = d.cliente "
            + "WHERE d.tipoDocumento = 'cuentaCobro' AND d.anulada = 0) repPlantillasDiario\n$P!{cliente}\nORDER BY ordenId ASC";

    private ConsultaReportes() {
    }
}
