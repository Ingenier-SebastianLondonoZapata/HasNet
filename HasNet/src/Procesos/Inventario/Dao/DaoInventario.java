package Procesos.Inventario.Dao;

import Enums.Tablas;
import Utilidades.Contactos.Bodegas;
import Estrategia.AbstractDao;
import Modelo.Inventario.FiltroReporteInventario;
import Modelo.Productos.ModeloProducto;
import Utilidades.BaseDatos.FiltrosSql;
import Utilidades.BaseDatos.SentenciaSql;
import Utilidades.Constantes;
import Utilidades.BaseDatos.ValidadorTabla;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

    /**
     * Arma el reporte de inventario directamente sobre bdProductos y bdinventario. Las cantidades
     * viajan como decimales; el formato (máximo Constantes.MAX_DECIMALES_CANTIDAD decimales y sin
     * ceros al final) lo aplica el reporte con Utilidades.formatearCantidad.
     */
    public static String consultaReporteInventario(FiltroReporteInventario filtro) {
        return consultaBase(Bodegas.resolver(filtro.getBodega())) + condiciones(filtro);
    }

    private static String consultaBase(String idBodega) {
        return "(SELECT p.idSistema AS Codigo, p.Codigo AS Codigo1, p.codigoBarras AS codigoBarras, p.Descripcion AS Descripcion, "
                + cantidad("inventario") + ", " + cantidad("fisicoInventario") + ", " + cantidad("inventarioInicial") + ", "
                + cantidad("compras") + ", " + cantidad("ventas") + ", " + cantidad("notaCredito", "nc") + ", "
                + cantidad("notaDebito", "nd") + ", " + cantidad("ajusteEntrada") + ", " + cantidad("ajusteSalida") + ", "
                + cantidad("ajusteInventario") + ", " + cantidad("planSepare") + ", " + cantidad("pedidos") + ", "
                + cantidad("anulacion") + ", " + cantidad("armado") + ", " + cantidad("costeo") + ", "
                + cantidad("ordenServicio") + ", " + cantidad("trasladoInternoEntrada") + ", " + cantidad("trasladoInternoSalida") + ", "
                + cantidad("congelada") + ", " + cantidad("trasladoBod") + ", " + cantidad("enTransito") + ", "
                + "u.nuevoPonderado AS costoUnd, u.ultimoCosto AS ultimoCosto, " + fechaUltimaCompra() + " AS fecha, p.minimo AS minimo, "
                + estado() + " AS estado, "
                + "p.L1 AS L1, (SELECT l2.precio FROM " + Tablas.PRODUCTO_LISTAS.getNombre() + " l2 WHERE l2.idSistema = p.idSistema AND l2.numeroLista = 2) AS L2, "
                + "p.referencia AS referencia, p.ubicacion1 AS ubicacion1, g.nombre AS grupoDesc, sg.nombre AS subGrupoDesc, "
                + "t.nombre AS proveedorNombre, p.grupo AS Grupo, p.Proveedor AS Proveedor, p.manejaInventario AS manejaInventario, "
                + "(COALESCE(u.nuevoPonderado, 0) * COALESCE(i.inventario, 0)) AS costoPTotal, "
                + "(COALESCE(u.ultimoCosto, 0) * COALESCE(i.inventario, 0)) AS costoTotal "
                + "FROM " + Tablas.PRODUCTOS.getNombre() + " p "
                + "LEFT JOIN " + Tablas.INVENTARIO.getNombre() + " i ON i.idSistema = p.idSistema AND i.idBodega = '" + FiltrosSql.escapar(idBodega) + "' "
                + "LEFT JOIN bdgrupo g ON g.codigo = p.grupo "
                + "LEFT JOIN bdsubgrupo sg ON sg.codigo = p.Sub_grupo "
                + "LEFT JOIN bdterceros t ON t.id = p.Proveedor "
                + "LEFT JOIN bdUltimoponderado u ON u.producto = p.idSistema) AS inventario";
    }

    /**
     * bdUltimoPonderado.fecha es texto ('yyyy-MM-dd', y vacío cuando no se pudo parsear), por eso se
     * formatea en SQL en vez de dejar que el reporte intente leerla como java.sql.Date.
     */
    private static String fechaUltimaCompra() {
        return "DATE_FORMAT(u.fecha, '%d/%m/%Y')";
    }

    private static String estado() {
        String fisico = "COALESCE(i.fisicoInventario, 0)";

        return "IF(" + fisico + " <= 0, '" + FiltroReporteInventario.ESTADO_SIN_INVENTARIO + "', "
                + "IF(" + fisico + " < p.minimo, '" + FiltroReporteInventario.ESTADO_BAJO + "', "
                + "IF(" + fisico + " = p.minimo, '" + FiltroReporteInventario.ESTADO_MEDIO + "', '" + FiltroReporteInventario.ESTADO_ALTO + "')))";
    }

    /**
     * Las condiciones usan los alias de la subconsulta, por eso van por fuera de ella.
     */
    private static String condiciones(FiltroReporteInventario filtro) {
        List<String> condiciones = new ArrayList<>();
        condiciones.add("manejaInventario = true");

        if (filtro.tieneRangoInventario()) {
            condiciones.add("(inventario >= " + filtro.getInventarioDesde().toPlainString()
                    + " AND inventario <= " + filtro.getInventarioHasta().toPlainString() + ")");
        }

        if (filtro.tieneRangoFechas()) {
            condiciones.add("(fecha >= '" + FiltrosSql.escapar(filtro.getFechaDesde()) + "' AND fecha <= '" + FiltrosSql.escapar(filtro.getFechaHasta()) + "')");
        }

        FiltrosSql.agregarEn(condiciones, "estado", filtro.getEstados());
        FiltrosSql.agregarEn(condiciones, "Proveedor", filtro.getProveedores());
        FiltrosSql.agregarEn(condiciones, "Codigo", filtro.getProductos());
        FiltrosSql.agregarEn(condiciones, "Grupo", filtro.getGrupos());

        return FiltrosSql.donde(condiciones);
    }

    private static String cantidad(String columna) {
        return cantidad(columna, columna);
    }

    private static String cantidad(String columna, String alias) {
        return "CAST(COALESCE(i." + columna + ", 0) AS DECIMAL(18, " + Constantes.MAX_DECIMALES_CANTIDAD + ")) AS " + alias;
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
