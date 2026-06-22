/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Ventas;

import dao.Generales.DaoGenerales;
import Modelo.Ventas.ModeloTablaDocumentos;
import Utilidades.BaseDatos.MySql_connection;
import Utilidades.Constantes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author sebastian.londono
 */
public class DaoReimpresiones {

    DaoGenerales daoGenerales = new DaoGenerales();
    Connection conexion = MySql_connection.getInstancia(Constantes.BASE_DATOS_PRINCIPAL).getConnection();
    
    public List<ModeloTablaDocumentos> obtenerDocumentos(String consulta, String condicionSQL, String tabla) {
        List<ModeloTablaDocumentos> listaDocumentos = new ArrayList<>();
        String camposAgrupar = extraerCamposParaGroupBy(consulta);

        String sql = consulta + "FROM " + tabla + " F "
                + "JOIN bdTerceros T ON F.cliente = T.idSistema "
                + condicionSQL + " "
                + "GROUP BY " + camposAgrupar + " "
                + "ORDER BY F.fechaFactura DESC, F.factura DESC";

        System.out.println("query completo: " + sql);

        try (PreparedStatement stmt = conexion.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                ModeloTablaDocumentos documento = new ModeloTablaDocumentos();
                documento.setIdFactura(rs.getString("idFactura"));
                documento.setFactura(rs.getString("factura"));
                documento.setFechaFactura(rs.getString("fechaFactura"));
                documento.setIdentificadorCliente(rs.getString("id"));
                documento.setNombreCliente(rs.getString("nombre"));
                documento.setVendedor(rs.getString("vendedor"));
                documento.setTotalGeneral(rs.getBigDecimal("totalGeneral"));
                documento.setTerminal(rs.getString("terminal"));

                try {
                    documento.setTurno(rs.getString("turno"));
                } catch (Exception e) {
                }

                try {
                    documento.setTipoFactura(rs.getString("modeloContable"));
                } catch (Exception e) {
                }

                listaDocumentos.add(documento);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener los documentos: " + e.getMessage());
        }

        return listaDocumentos;
    }

    private String extraerCamposParaGroupBy(String consulta) {
        if (consulta == null || consulta.trim().isEmpty()) {
            return "";
        }

        String campos = consulta.replaceFirst("(?i)SELECT", "").trim();
        String[] partes = campos.split(",");

        List<String> camposLimpiados = new ArrayList<>();
        for (String campo : partes) {
            campo = campo.trim();
            if (campo.toUpperCase().contains(" AS ")) {
                campo = campo.substring(0, campo.toUpperCase().indexOf(" AS ")).trim();
            }
            camposLimpiados.add(campo);
        }

        return String.join(", ", camposLimpiados);
    }

    public DefaultTableModel obtenerDetallesDocumentoEnTabla(String tabla, String identificadorDocumento) {
        boolean tieneImpoconsumo = existeColumnaEnTabla(tabla, "impoconsumo");
        boolean tieneIdCosteo = existeColumnaEnTabla(tabla, "idCosteo");
        boolean tieneIdProducto = existeColumnaEnTabla(tabla, "idProd");

        String[] nombreColumnasBD = {
            "Codigo", "descripcion", "lista", "cantidad", "subtotal", "descuento",
            "porcIva", "iva", "impoconsumo", "idProd", "preparacion", "idCosteo",
            "total", "cant2", "idSistema"
        };

        String[] nombreColumnasTabla = {
            "Codigo", "Descripcion", "Valor/Unit", "Cant", "Subtotal", "Descuento",
            "%", "IVA", "INC", "idProd", "preparacion", "idCosteo",
            "Total", "Cant2", "idSistema"
        };

        String impoconsumoSQL = tieneImpoconsumo ? "F.impoconsumo" : "0 AS impoconsumo";
        String idCosteoSQL = tieneIdCosteo ? "F.idCosteo" : "'' AS idCosteo";
        String idProductoSQL = tieneIdProducto ? "F.idProd" : "'' AS idProd";

        String sql = "SELECT P.Codigo, F.descripcion, F.lista, F.cantidad, F.subtotal, F.descuento, "
                + "F.porcIva, F.iva, " + impoconsumoSQL + ", " + idProductoSQL + ", F.preparacion, " + idCosteoSQL + ", "
                + "F.total, F.cant2, P.idSistema "
                + "FROM " + tabla + " F "
                + "INNER JOIN bdProductos P ON F.producto = P.idSistema "
                + "WHERE F.factura = '" + identificadorDocumento + "' ";

        System.out.println("sql productos: " + sql);

        Object[][] datos = daoGenerales.obtenerDatosTabla(nombreColumnasBD, sql);

        return new DefaultTableModel(datos, nombreColumnasTabla) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return true;
            }
        };
    }

    private boolean existeColumnaEnTabla(String tabla, String columna) {
        String sql = "SELECT * FROM " + tabla + " LIMIT 1";
        try (Statement stmt = conexion.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            ResultSetMetaData meta = rs.getMetaData();
            for (int i = 1; i <= meta.getColumnCount(); i++) {
                if (meta.getColumnName(i).equalsIgnoreCase(columna)) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("No existe columna " + columna + " en la tabla " + tabla);
            e.printStackTrace();
        }
        return false;
    }
}
