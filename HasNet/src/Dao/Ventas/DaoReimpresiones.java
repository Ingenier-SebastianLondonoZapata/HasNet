package Dao.Ventas;

import Dao.Documentos.ConsultaDocumentos;
import Dao.Generales.DaoGenerales;
import Enums.TipoDocumento;
import Modelo.Ventas.ModeloTablaDocumentos;
import Utilidades.BaseDatos.MySql_connection;
import Utilidades.Constantes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import javax.swing.table.DefaultTableModel;

public class DaoReimpresiones {

    private static final Set<TipoDocumento> CON_IMPOCONSUMO = EnumSet.of(TipoDocumento.FACTURACION, TipoDocumento.NOTA_DEBITO);
    private static final Set<TipoDocumento> CON_ID_PRODUCTO = EnumSet.of(TipoDocumento.FACTURACION, TipoDocumento.NOTA_DEBITO,
            TipoDocumento.PLAN_SEPARE, TipoDocumento.PEDIDO);
    private static final Set<TipoDocumento> CON_DETALLE = EnumSet.of(TipoDocumento.FACTURACION, TipoDocumento.NOTA_DEBITO,
            TipoDocumento.PLAN_SEPARE, TipoDocumento.PEDIDO);

    private final DaoGenerales daoGenerales = new DaoGenerales();

    private Connection conexion() {
        return MySql_connection.getInstancia(Constantes.BASE_DATOS_PRINCIPAL).getConnection();
    }

    public List<ModeloTablaDocumentos> obtenerDocumentos(List<TipoDocumento> tipos, boolean incluirDatosFactura, String condicion) {
        TipoDocumento tipo = tipos.get(0);
        List<String> columnas = new ArrayList<>(Arrays.asList("factura", "fechaFactura", "vendedor", "totalGeneral", "terminal"));
        if (incluirDatosFactura) {
            columnas.add("turno");
            columnas.add("modeloContable");
        }

        String filtro = ConsultaDocumentos.filtroTipos(tipos);
        String adicional = ConsultaDocumentos.sinWhere(condicion);
        if (!adicional.isEmpty()) {
            filtro += " AND (" + ConsultaDocumentos.traducirCondicion(tipo, adicional, "F") + ")";
        }

        String sql = "SELECT " + ConsultaDocumentos.columnas(tipo, columnas.toArray(new String[0])) + ", T.id AS id, T.nombre AS nombre "
                + "FROM bdDocumentos d JOIN bdTerceros T ON d.cliente = T.idSistema "
                + "WHERE " + filtro + " "
                + "ORDER BY d.fechaFactura DESC, d.factura DESC";

        List<ModeloTablaDocumentos> listaDocumentos = new ArrayList<>();
        try (PreparedStatement stmt = conexion().prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                ModeloTablaDocumentos documento = new ModeloTablaDocumentos();
                documento.setFactura(rs.getString("factura"));
                documento.setFechaFactura(rs.getString("fechaFactura"));
                documento.setIdentificadorCliente(rs.getString("id"));
                documento.setNombreCliente(rs.getString("nombre"));
                documento.setVendedor(rs.getString("vendedor"));
                documento.setTotalGeneral(rs.getBigDecimal("totalGeneral"));
                documento.setTerminal(rs.getString("terminal"));
                if (incluirDatosFactura) {
                    documento.setTurno(rs.getString("turno"));
                    documento.setTipoFactura(rs.getString("modeloContable"));
                }
                listaDocumentos.add(documento);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener los documentos: " + e.getMessage());
        }

        return listaDocumentos;
    }

    public DefaultTableModel obtenerDetallesDocumentoEnTabla(List<TipoDocumento> tipos, String identificadorDocumento) {
        TipoDocumento tipo = tipos.get(0);

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

        String impoconsumoSQL = CON_IMPOCONSUMO.contains(tipo) ? "dd.impoconsumo" : "0";
        String idCosteoSQL = CON_IMPOCONSUMO.contains(tipo) ? "d.idCosteo" : "''";
        String idProductoSQL = CON_ID_PRODUCTO.contains(tipo) ? "dd.idProd" : "''";
        String descripcionSQL = CON_DETALLE.contains(tipo)
                ? "CASE WHEN dd.detalle IS NULL OR TRIM(dd.detalle) = '' "
                + "THEN dd.descripcion ELSE CONCAT(dd.descripcion, ' ', dd.detalle) END"
                : "dd.descripcion";

        String sql = "SELECT P.Codigo AS Codigo, " + descripcionSQL + " AS descripcion, "
                + ConsultaDocumentos.columnas(tipo, "lista", "cantidad", "subtotal", "descuento", "porcIva", "iva") + ", "
                + impoconsumoSQL + " AS impoconsumo, " + idProductoSQL + " AS idProd, "
                + ConsultaDocumentos.columnas(tipo, "preparacion") + ", " + idCosteoSQL + " AS idCosteo, "
                + ConsultaDocumentos.columnas(tipo, "total", "cant2") + ", P.idSistema AS idSistema "
                + "FROM" + ConsultaDocumentos.ORIGEN
                + "INNER JOIN bdProductos P ON dd.producto = P.idSistema "
                + "WHERE " + ConsultaDocumentos.filtroTipos(tipos)
                + " AND d.factura = '" + identificadorDocumento.replace("'", "''") + "' ORDER BY dd.idDetalle";

        Object[][] datos = daoGenerales.obtenerDatosTabla(nombreColumnasBD, sql);

        return new DefaultTableModel(datos, nombreColumnasTabla) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
    }
}
