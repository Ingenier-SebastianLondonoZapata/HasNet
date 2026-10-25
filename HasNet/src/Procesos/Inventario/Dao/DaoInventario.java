package Procesos.Inventario.Dao;

import Enums.Tablas;
import Utilidades.Contactos.Bodegas;
import Estrategia.AbstractDao;
import Modelo.Productos.ModeloProducto;
import Utilidades.BaseDatos.SentenciaSql;
import Utilidades.BaseDatos.ValidadorTabla;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoInventario extends AbstractDao {

    private static final Logger LOGGER = Logger.getLogger(DaoInventario.class.getName());

    public static final String FISICO_COMO_TEXTO = "REPLACE(CAST(CAST(COALESCE(" + Tablas.INVENTARIO.getNombre() + ".fisicoInventario, 0) AS DOUBLE) AS CHAR), '.', ',')";

    public static String unirInventario(String tablaProducto, String bodega) {
        return " LEFT JOIN " + Tablas.INVENTARIO.getNombre() + " ON ((" + Tablas.INVENTARIO.getNombre() + ".idSistema = " + tablaProducto
                + ".idSistema) AND (" + Tablas.INVENTARIO.getNombre() + ".idBodega = '" + Bodegas.resolver(bodega) + "'))";
    }

    public static String consultaReporteInventario(String bodega) {
        String idBodega = Bodegas.resolver(bodega, true);
        String inv = Tablas.INVENTARIO.getNombre();

        return "(SELECT p.idSistema AS Codigo, p.Descripcion AS Descripcion, " + texto("inventario") + " AS inventario, "
                + "CAST(COALESCE(i.inventario, 0) AS SIGNED) AS inv, " + texto("fisicoInventario") + " AS fisicoInventario, "
                + texto("compras") + " AS compras, " + texto("ventas") + " AS ventas, " + texto("notaCredito") + " AS nc, "
                + texto("ajusteEntrada") + " AS ajusteEntrada, " + texto("ajusteSalida") + " AS ajusteSalida, "
                + texto("planSepare") + " AS planSepare, " + texto("pedidos") + " AS pedidos, " + texto("anulacion") + " AS anulacion, "
                + texto("inventarioInicial") + " AS inventarioInicial, " + texto("ajusteInventario") + " AS ajusteInventario, "
                + texto("armado") + " AS armado, " + texto("costeo") + " AS costeo, "
                + "u.nuevoPonderado AS costoUnd, u.ultimoCosto AS ultimoCosto, p.minimo AS minimo, "
                + "IF(CAST(COALESCE(i.fisicoInventario, 0) AS SIGNED) <= 0, 'SIN INVENTARIO', "
                + "IF(CAST(COALESCE(i.fisicoInventario, 0) AS SIGNED) < p.minimo, 'BAJO', "
                + "IF(CAST(COALESCE(i.fisicoInventario, 0) AS SIGNED) = p.minimo, 'MEDIO', 'ALTO'))) AS estado, "
                + "p.L1 AS L1, (SELECT l2.precio FROM " + Tablas.PRODUCTO_LISTAS.getNombre() + " l2 WHERE l2.idSistema = p.idSistema AND l2.numeroLista = 2) AS L2, p.referencia AS referencia, p.ubicacion1 AS ubicacion1, g.nombre AS grupoDesc, "
                + "sg.nombre AS subGrupoDesc, t.nombre AS proveedorNombre, p.grupo AS Grupo, "
                + "(CAST(u.nuevoPonderado AS SIGNED) * CAST(COALESCE(i.inventario, 0) AS SIGNED)) AS costoPTotal, "
                + "(CAST(u.ultimoCosto AS SIGNED) * CAST(COALESCE(i.inventario, 0) AS SIGNED)) AS costoTotal, "
                + texto("ordenServicio") + " AS ordenServicio, p.manejaInventario AS manejaInventario, p.Proveedor AS Proveedor, "
                + "u.fecha AS fecha, p.Codigo AS Codigo1, p.codigoBarras AS codigoBarras, "
                + texto("trasladoInternoEntrada") + " AS trasladoInternoEntrada, " + texto("trasladoInternoSalida") + " AS trasladoInternoSalida, "
                + texto("congelada") + " AS congelada, " + texto("trasladoBod") + " AS trasladoBod, " + texto("enTransito") + " AS enTransito, "
                + texto("notaDebito") + " AS nd "
                + "FROM " + Tablas.PRODUCTOS.getNombre() + " p "
                + "LEFT JOIN " + inv + " i ON i.idSistema = p.idSistema AND i.idBodega = '" + idBodega + "' "
                + "LEFT JOIN bdgrupo g ON g.codigo = p.grupo "
                + "LEFT JOIN bdsubgrupo sg ON sg.codigo = p.Sub_grupo "
                + "LEFT JOIN bdterceros t ON t.id = p.Proveedor "
                + "LEFT JOIN ultimoponderado u ON u.producto = p.idSistema) AS inventario";
    }

    private static String texto(String columna) {
        return "REPLACE(CAST(CAST(COALESCE(i." + columna + ", 0) AS DOUBLE) AS CHAR), '.', ',')";
    }

    public void ejecutarSentencias(Connection conn, List<SentenciaSql> sentencias) throws SQLException {
        for (SentenciaSql sentencia : sentencias) {
            ejecutar(conn, sentencia.getSql(), sentencia.getParametros());
        }
    }

    public void cargarInventario(ModeloProducto producto, String bodega) {
        String idBodega = Bodegas.resolver(bodega);
        if (producto.getIdSistema() == null) {
            return;
        }

        String sql = "SELECT " + ColumnasInventario.lista() + " FROM " + Tablas.INVENTARIO.getNombre() + " WHERE idSistema = ? AND idBodega = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, producto.getIdSistema());
            ps.setString(2, idBodega);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    producto.setInventario(rs.getBigDecimal("inventario"));
                    producto.setFisicoInventario(rs.getBigDecimal("fisicoInventario"));
                    producto.setInventarioInicial(rs.getBigDecimal("inventarioInicial"));
                    producto.setCompras(rs.getBigDecimal("compras"));
                    producto.setVentas(rs.getBigDecimal("ventas"));
                    producto.setArmado(rs.getBigDecimal("armado"));
                    producto.setAnulada(rs.getBigDecimal("anulacion"));
                    producto.setPedidos(rs.getBigDecimal("pedidos"));
                    producto.setPlanSepare(rs.getBigDecimal("planSepare"));
                    producto.setOrdenServicio(rs.getBigDecimal("ordenServicio"));
                    producto.setNotaCredito(rs.getBigDecimal("notaCredito"));
                    producto.setNotaDebito(rs.getBigDecimal("notaDebito"));
                    producto.setAjusteEntrada(rs.getBigDecimal("ajusteEntrada"));
                    producto.setAjusteSalida(rs.getBigDecimal("ajusteSalida"));
                    producto.setAjusteInventario(rs.getBigDecimal("ajusteInventario"));
                    producto.setCosteo(rs.getBigDecimal("costeo"));
                    producto.setCongelada(rs.getBigDecimal("congelada"));
                    producto.setTrasladoBod(rs.getBigDecimal("trasladoBod"));
                    producto.setTrasladoInternoEntrada(rs.getBigDecimal("trasladoInternoEntrada"));
                    producto.setTrasladoInternoSalida(rs.getBigDecimal("trasladoInternoSalida"));
                    producto.setEnTransito(rs.getBigDecimal("enTransito"));
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al consultar el inventario del producto " + producto.getIdSistema() + " en la bodega " + idBodega, e);
        }
    }

    public boolean modificarInventario(String columna, String valor, String idSistema, String bodega) {
        if (!ColumnasInventario.contiene(columna)) {
            throw new IllegalArgumentException("Columna de inventario no permitida: " + columna);
        }

        String idBodega = Bodegas.resolver(bodega);

        String sql = "INSERT INTO " + Tablas.INVENTARIO.getNombre() + " (idSistema, idBodega, " + columna + ") VALUES (?, ?, ?) "
                + "ON DUPLICATE KEY UPDATE " + columna + " = VALUES(" + columna + ")";

        try {
            ejecutar(getConnection(), sql, Arrays.<Object>asList(idSistema, idBodega, new BigDecimal(valor.trim().replace(",", "."))));
            return true;
        } catch (SQLException | NumberFormatException e) {
            LOGGER.log(Level.SEVERE, "Error al actualizar " + columna + " del producto " + idSistema + " en la bodega " + idBodega, e);
            return false;
        }
    }

    public BigDecimal obtenerFisicoInventario(String codigo) {
        String sql = "SELECT i.fisicoInventario FROM " + ValidadorTabla.validar(Tablas.PRODUCTOS.getNombre()) + " p "
                + "INNER JOIN " + Tablas.INVENTARIO.getNombre() + " i ON i.idSistema = p.idSistema AND i.idBodega = ? WHERE p.Codigo = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, Bodegas.predeterminada());
            ps.setString(2, codigo);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next() && rs.getBigDecimal(1) != null) {
                    return rs.getBigDecimal(1);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al consultar el inventario físico del producto " + codigo, e);
        }

        return BigDecimal.ZERO;
    }

    private void ejecutar(Connection conn, String sql, List<Object> parametros) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            for (int i = 0; i < parametros.size(); i++) {
                ps.setObject(i + 1, parametros.get(i));
            }

            ps.executeUpdate();
        }
    }
}
