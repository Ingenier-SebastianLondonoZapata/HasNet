package Dao.Documentos;

import Modelo.Reportes.CondicionSql;
import Modelo.Reportes.SeleccionReporte;
import java.util.ArrayList;
import java.util.List;

public final class ConsultaReporteImpuestos {

    public static final String IMPUESTO = "impuesto";
    public static final String IVA = "iva";
    public static final String IMPOCONSUMO = "impoconsumo";
    public static final String AMBOS = "ambos";

    private ConsultaReporteImpuestos() {
    }

    public static boolean incluyeIva(SeleccionReporte seleccion) {
        return !IMPOCONSUMO.equals(seleccion.valor(IMPUESTO));
    }

    public static boolean incluyeImpoconsumo(SeleccionReporte seleccion) {
        return !IVA.equals(seleccion.valor(IMPUESTO));
    }

    public static String ventas(SeleccionReporte seleccion) {
        Origen origen = new Origen("bdDocumentos d INNER JOIN bdDocumentosDetalle l ON l.idDocumento = d.idDocumento",
                "d.idDocumento", "d.fechaFactura", ConsultaCartera.FACTURAS + " AND d.anulada = 0",
                "l.porcIva", "l.iva", "l.porcImpo", "l.impoconsumo");
        return consulta(seleccion, origen);
    }

    public static String compras(SeleccionReporte seleccion) {
        Origen origen = new Origen("bdingreso d INNER JOIN bdcompra l ON l.ingreso = d.id",
                "d.id", "d.fechaFactura", "d.id LIKE 'ING-%' AND d.anulada = 0 AND COALESCE(d.tipo, '') <> 'INVENTARIO INICIAL'",
                "l.porIva", "l.iva", "l.porcImpo", "l.impoconsumo");
        return consulta(seleccion, origen);
    }

    private static String consulta(SeleccionReporte seleccion, Origen origen) {
        String condicion = new CondicionSql()
                .y(origen.base)
                .entreFechas(origen.fecha, seleccion.getFechaInicial(), seleccion.getFechaFinal())
                .sinWhere();

        List<String> partes = new ArrayList<>();
        if (incluyeIva(seleccion)) {
            partes.add(seccion(origen, "IVA", 1, origen.porcentajeIva, origen.valorIva, condicion
                    + " AND NOT (COALESCE(" + origen.porcentajeIva + ", 0) = 0 AND COALESCE(" + origen.porcentajeImpo + ", 0) > 0)"));
        }
        if (incluyeImpoconsumo(seleccion)) {
            partes.add(seccion(origen, "IMPOCONSUMO", 2, origen.porcentajeImpo, origen.valorImpo,
                    condicion + " AND COALESCE(" + origen.porcentajeImpo + ", 0) > 0"));
        }
        return String.join(" UNION ALL ", partes) + " ORDER BY orden, tarifa";
    }

    private static String seccion(Origen origen, String nombre, int orden, String porcentaje, String valor, String condicion) {
        return "SELECT '" + nombre + "' AS impuesto, " + orden + " AS orden, "
                + "CAST(COALESCE(" + porcentaje + ", 0) AS DECIMAL(10,2)) AS tarifa, "
                + "COUNT(DISTINCT " + origen.documento + ") AS documentos, "
                + "COALESCE(SUM(l.subtotal), 0) AS subtotal, COALESCE(SUM(l.descuento), 0) AS descuento, "
                + "COALESCE(SUM(l.subtotal), 0) - COALESCE(SUM(l.descuento), 0) AS base, "
                + "COALESCE(SUM(" + valor + "), 0) AS valorImpuesto, "
                + "COALESCE(SUM(l.subtotal), 0) - COALESCE(SUM(l.descuento), 0) + COALESCE(SUM(" + valor + "), 0) AS total "
                + "FROM " + origen.tablas + " WHERE " + condicion
                + " GROUP BY CAST(COALESCE(" + porcentaje + ", 0) AS DECIMAL(10,2))";
    }

    private static final class Origen {

        final String tablas;
        final String documento;
        final String fecha;
        final String base;
        final String porcentajeIva;
        final String valorIva;
        final String porcentajeImpo;
        final String valorImpo;

        Origen(String tablas, String documento, String fecha, String base, String porcentajeIva, String valorIva,
                String porcentajeImpo, String valorImpo) {
            this.tablas = tablas;
            this.documento = documento;
            this.fecha = fecha;
            this.base = base;
            this.porcentajeIva = porcentajeIva;
            this.valorIva = valorIva;
            this.porcentajeImpo = porcentajeImpo;
            this.valorImpo = valorImpo;
        }
    }
}
