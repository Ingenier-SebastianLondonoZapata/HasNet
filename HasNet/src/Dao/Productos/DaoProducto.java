package Dao.Productos;

import Enums.Tablas;
import Utilidades.Contactos.Bodegas;
import Estrategia.AbstractDao;
import Procesos.Inventario.Dao.DaoInventario;
import Modelo.Productos.ModeloListaPrecio;
import Modelo.Productos.ResultadoPluProducto;
import Utilidades.BaseDatos.ValidadorTabla;
import Utilidades.Utilidades;
import Modelo.Productos.ModeloProducto;
import clases.big;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoProducto extends AbstractDao {

    private static final Logger LOGGER = Logger.getLogger(DaoProducto.class.getName());

    private static final String[] COLUMNAS_ESCRITURA = {
        "codigo", "codigoBarras", "Descripcion", "Grupo", "Sub_grupo", "Proveedor", "Usuario", "unidad", "referencia", "ubicacion1",
        "terminal", "claseBuscador", "manejaInventario", "porcentaje", "cubicaje", "peso",
        "codContable", "codArancel", "tipoProducto", "cantMedida", "marca", "tipoProd", "lenteMarco", "adesivo", "color", "empaque",
        "composicion", "rmb", "indVentas", "descripcionLarga"};

    private static final String[] COLUMNAS_NUMERICAS = {
        "IVA", "L1", "minimo", "costo", "minima", "maxima", "ponderado", "impoconsumoVenta"};

    private static final String COLUMNAS_PRODUCTO = "idSistema, codigo, codigoBarras, Descripcion, Grupo, Sub_grupo, Proveedor, IVA, L1, Usuario, minimo, "
            + "unidad, referencia, costo, minima, maxima, ubicacion1, ponderado, terminal, claseBuscador, manejaInventario, porcentaje, "
            + "cubicaje, peso, codContable, codArancel, tipoProducto, cantMedida, marca, tipoProd, lenteMarco, adesivo, color, empaque, "
            + "composicion, rmb, indVentas, impoconsumoVenta, descripcionLarga";

    private static final String COLUMNAS = "p." + COLUMNAS_PRODUCTO.replace(", ", ", p.");

    private final DaoInventario daoInventario = new DaoInventario();
    private final DaoListaPrecio daoListaPrecio = new DaoListaPrecio();

    private static final String[] COLUMNAS_BUSQUEDA = {"Codigo", "codigoBarras", "idSistema"};

    private static final String CONDICION_BUSQUEDA = construirCondicionBusqueda();

    private static String construirCondicionBusqueda() {
        List<String> condiciones = new ArrayList<>();
        for (String columna : COLUMNAS_BUSQUEDA) {
            condiciones.add("p." + columna + " = ?");
        }
        return String.join(" OR ", condiciones);
    }

    public ModeloProducto buscarPorCodigo(String codigo) {
        if (codigo == null || codigo.isEmpty()) {
            return new ModeloProducto();
        }

        return obtenerDatosProducto(obtenerCodigoPadre(codigo), Bodegas.predeterminada());
    }

    private String obtenerCodigoPadre(String codigo) {
        String sql = "SELECT u.productoPadre FROM bdProductosUnificados u "
                + "INNER JOIN bdProductos c ON c.idSistema = u.productoUnificado "
                + "WHERE u.productoUnificado = ? OR c.Codigo = ? OR c.codigoBarras = ? LIMIT 1";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            for (int i = 1; i <= 3; i++) {
                ps.setString(i, codigo);
            }

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString(1);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al resolver el producto unificado del código " + codigo, e);
        }

        return codigo;
    }

    public ModeloProducto obtenerDatosProducto(String codigo, String idBodega) {
        String tabla = ValidadorTabla.validar(Tablas.PRODUCTOS.getNombre());
        String sql = "SELECT " + COLUMNAS + " FROM " + tabla + " p WHERE " + CONDICION_BUSQUEDA
                + " UNION SELECT " + COLUMNAS + " FROM " + tabla + " p INNER JOIN " + Tablas.PRODUCTO_LISTAS.getNombre() + " l ON l.idSistema = p.idSistema WHERE l.codigo = ?";

        ModeloProducto producto = new ModeloProducto();

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            for (int i = 1; i <= COLUMNAS_BUSQUEDA.length + 1; i++) {
                ps.setString(i, codigo);
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    producto = MapeadorProducto.mapear(rs);
                }
            }
            daoInventario.cargarInventario(producto, idBodega);
            daoListaPrecio.cargarListas(producto);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al consultar el producto " + codigo + " en la bodega " + idBodega, e);
        }

        return producto;
    }

    public Object[][] obtenerProductosUnificados(String idProductoPadre) {
        List<String> unificados = new ArrayList<>();

        try (PreparedStatement ps = getConnection().prepareStatement("SELECT productoUnificado FROM bdProductosUnificados WHERE productoPadre = ?")) {
            ps.setString(1, idProductoPadre);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    unificados.add(rs.getString("productoUnificado"));
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al consultar los productos unificados de " + idProductoPadre, e);
        }

        Object[][] filas = new Object[unificados.size()][7];
        for (int i = 0; i < unificados.size(); i++) {
            ModeloProducto unificado = obtenerDatosProducto(unificados.get(i), Bodegas.predeterminada());
            filas[i] = new Object[]{unificados.get(i), unificado.getDescripcion(), unificado.getReferencia(), unificado.getL1(),
                Utilidades.formatearCantidad(unificado.getInventario()), unificado.getUbicacion1(), unificado.getCodigo()};
        }

        return filas;
    }

    public List<String> obtenerNombresUnidadesMedida() throws SQLException {
        List<String> nombres = new ArrayList<>();

        try (PreparedStatement ps = getConnection().prepareStatement(
                "SELECT DISTINCT nombre FROM bdMedida WHERE nombre IS NOT NULL AND nombre <> '' ORDER BY nombre");
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                nombres.add(rs.getString("nombre"));
            }
        }

        return nombres;
    }

    public Object[][] obtenerProductosPorGrupo(String grupo, boolean conImpuestos) {
        String precio = conImpuestos
                ? "L1"
                : "((ROUND((L1 * ((IVA / 100) + 1)), 2) + ROUND((L1 * ((impoconsumoVenta / 100) + 1)), 2)) - L1)";

        String sql = "SELECT Codigo, Descripcion, grupo, " + precio + " AS precio, idSistema FROM "
                + ValidadorTabla.validar(Tablas.PRODUCTOS.getNombre())
                + " WHERE grupo = ? AND activo = false";

        List<Object[]> filas = new ArrayList<>();

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, grupo);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    filas.add(new Object[]{rs.getString("Codigo"), rs.getString("Descripcion"), rs.getString("grupo"),
                        rs.getString("precio"), rs.getString("idSistema")});
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al consultar los productos del grupo " + grupo, e);
        }

        return filas.toArray(new Object[0][]);
    }

    public boolean agregarProducto(ModeloProducto producto) {
        List<String> columnas = new ArrayList<>();
        columnas.add("idSistema");
        columnas.addAll(Arrays.asList(COLUMNAS_ESCRITURA));
        columnas.addAll(Arrays.asList(COLUMNAS_NUMERICAS));

        List<Object> parametros = new ArrayList<>();
        parametros.add(producto.getIdSistema());
        parametros.addAll(valoresEscritura(producto));

        String sql = "INSERT INTO bdProductos (" + String.join(", ", columnas) + ") VALUES ("
                + String.join(", ", Collections.nCopies(columnas.size(), "?")) + ")";

        Connection conn = getConnection();
        try {
            parametros.addAll(valoresNumericos(producto));
            conn.setAutoCommit(false);

            ejecutar(conn, sql, parametros);
            daoListaPrecio.reemplazarListas(conn, producto.getIdSistema(), producto.getListas());

            conn.commit();
            return true;
        } catch (SQLException | NumberFormatException e) {
            LOGGER.log(Level.SEVERE, "Error al crear el producto " + producto.getCodigo() + " en bdProductos", e);
            revertir(conn);
            return false;
        } finally {
            restaurarAutoCommit(conn);
        }
    }

    public boolean modificarProducto(ModeloProducto producto) {
        List<String> asignaciones = new ArrayList<>();
        for (String columna : COLUMNAS_ESCRITURA) {
            asignaciones.add(columna + " = ?");
        }
        for (String columna : COLUMNAS_NUMERICAS) {
            asignaciones.add(columna + " = ?");
        }

        String sql = "UPDATE bdProductos SET " + String.join(", ", asignaciones) + " WHERE idSistema = ?";

        Connection conn = getConnection();
        try {
            List<Object> parametros = new ArrayList<>(valoresEscritura(producto));
            parametros.addAll(valoresNumericos(producto));
            parametros.add(producto.getIdSistema());
            conn.setAutoCommit(false);

            ejecutar(conn, sql, parametros);
            daoListaPrecio.reemplazarListas(conn, producto.getIdSistema(), producto.getListas());

            conn.commit();
            return true;
        } catch (SQLException | NumberFormatException e) {
            LOGGER.log(Level.SEVERE, "Error al modificar el producto " + producto.getIdSistema(), e);
            revertir(conn);
            return false;
        } finally {
            restaurarAutoCommit(conn);
        }
    }

    private void ejecutar(Connection conn, String sql, List<Object> parametros) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            for (int i = 0; i < parametros.size(); i++) {
                ps.setObject(i + 1, parametros.get(i));
            }
            ps.executeUpdate();
        }
    }

    private void revertir(Connection conn) {
        try {
            conn.rollback();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al revertir la transacción del producto", e);
        }
    }

    private void restaurarAutoCommit(Connection conn) {
        try {
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al restaurar el autocommit de la conexión", e);
        }
    }

    private List<Object> valoresEscritura(ModeloProducto n) {
        return Arrays.<Object>asList(
                n.getCodigo(), n.getCodigoBarras(), n.getDescripcion(), n.getGrupo(), n.getSubgrupo(), n.getProveedor(), n.getUsuario(), n.getUnd(),
                n.getReferencia(), n.getUbicacion1(), n.getTerminal(), n.getClaseBuscador(), n.getManejaInventario(), n.getPorcentaje(), n.getCubicaje(), n.getPeso(),
                n.getCodContable(), n.getCodArancel(), n.getTipoProducto(), n.getCantMedida(), n.getMarca(), n.getTipoProd(), n.getLenteMarco(),
                n.getAdesivo(), n.getColor(), n.getEmpaque(), n.getComposicion(), n.getRmb(), n.getIndVentas(), n.getDescripcionLarga());
    }

    private List<Object> valoresNumericos(ModeloProducto n) {
        return Arrays.<Object>asList(
                big.getBigDecimal(n.getIva()), big.getBigDecimal(n.getL1()), big.getBigDecimal(n.getMinimo()), big.getBigDecimal(n.getCosto()), big.getBigDecimal(n.getMinima()),
                big.getBigDecimal(n.getMaxima()), big.getBigDecimal(n.getPonderado()),
                big.getBigDecimal(n.getImpoconsumoVenta()));
    }

    public boolean estaInactivo(String codigo) throws SQLException {
        String sql = "SELECT activo FROM bdProductos WHERE Codigo = ? OR (codigoBarras = ? AND codigoBarras <> '') OR idSistema = ? LIMIT 1";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            for (int i = 1; i <= 3; i++) {
                ps.setString(i, codigo);
            }

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() && rs.getBoolean("activo");
            }
        }
    }

    public int obtenerConsecutivoProducto() throws SQLException {
        try (PreparedStatement ps = getConnection().prepareStatement("SELECT numero FROM bdConsecutivos WHERE Id = 'PROD'");
                ResultSet rs = ps.executeQuery()) {
            if (!rs.next()) {
                throw new SQLException("No existe el consecutivo PROD");
            }

            return Integer.parseInt(rs.getString("numero").trim());
        }
    }

    public void actualizarConsecutivoProducto(int numero) throws SQLException {
        try (PreparedStatement ps = getConnection().prepareStatement("UPDATE bdConsecutivos SET numero = ? WHERE Id = 'PROD' AND numero < ?")) {
            ps.setString(1, String.valueOf(numero));
            ps.setInt(2, numero);
            ps.executeUpdate();
        }
    }

    public boolean productoConPlu(ModeloProducto nodoProducto) {
        return nodoProducto.tienePlu();
    }

    public ResultadoPluProducto obtenerDatosPluProducto(ModeloProducto nodoProducto, int plu) {
        BigDecimal fisicoInventarioActual = nodoProducto.getFisicoInventario();
        ModeloListaPrecio lista = plu > ModeloListaPrecio.LISTA_PRINCIPAL ? nodoProducto.getLista(plu) : null;

        if (lista == null) {
            return new ResultadoPluProducto(
                    BigDecimal.ONE,
                    fisicoInventarioActual,
                    Utilidades.convertirBigDecimal(nodoProducto.getL1()),
                    nodoProducto.getCodigo(),
                    nodoProducto.getDescripcion(),
                    "L1");
        }

        return new ResultadoPluProducto(
                lista.getCantidad(),
                fisicoInventarioActual.divide(lista.getCantidad(), 4, RoundingMode.HALF_UP),
                lista.getPrecio(),
                lista.getCodigo(),
                lista.getDescripcion(),
                lista.getNombre());
    }
}
