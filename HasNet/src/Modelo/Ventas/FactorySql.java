/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Ventas;

/**
 *
 * @author sebastian.londono
 */
public class FactorySql {

    private static final String CAMPOS_AGRUPADOS = "...";
    private static final String CAMPOS_SIMPLES = "...";

    private static final String JOINS_COMUNES = "...";

    public String sentenciaImpresionFactura(String tipo, String condicion) {
        String campos = tipo.equals("agrupada") ? CAMPOS_AGRUPADOS : CAMPOS_SIMPLES;
        String query = "SELECT " + campos + " FROM " + JOINS_COMUNES + " " + condicion;

        if (tipo.equals("agrupada")) {
            query += " AND (bdcxc.tipo = 'FACT' OR bdcxc.tipo IS NULL) GROUP BY bdfactura.factura, ...";
        } else {
            query += " GROUP BY bdfactura.idFactura, ... ORDER BY bdfactura.Id";
        }

        return query;
    }
}
