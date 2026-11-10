package Dao.Productos;

import Enums.Tablas;
import Estrategia.AbstractDao;
import Modelo.Productos.FiltroBuscadorProductos;
import Modelo.Productos.ProductoBuscador;
import Utilidades.Contactos.Bodegas;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DaoBuscadorProductos extends AbstractDao {

    private static final String CLASE_AGENDA = "Agenda";
    private static final String USUARIO_ADMIN = "ADMIN";
    private static final String USUARIO_FACTURA = "FACTURA";
    private static final String USUARIO_COSTEO = "COSTEO";

    private static final Map<String, String> GRUPO_POR_TIPO_BUSQUEDA = new HashMap<>();

    static {
        GRUPO_POR_TIPO_BUSQUEDA.put("adiciones", "GRP-02");
        GRUPO_POR_TIPO_BUSQUEDA.put("peluqueria", "GRP-06");
        GRUPO_POR_TIPO_BUSQUEDA.put("parqueadero", "GRP-0");
        GRUPO_POR_TIPO_BUSQUEDA.put("lavado", "GRP-01");
        GRUPO_POR_TIPO_BUSQUEDA.put("showRoom", "GRP-04");
    }

    public List<ProductoBuscador> buscar(FiltroBuscadorProductos filtro) throws SQLException {
        List<String> condiciones = new ArrayList<>();
        List<Object> parametros = new ArrayList<>();
        parametros.add(Bodegas.resolver(filtro.getIdBodega()));
        agregarCondiciones(filtro, condiciones, parametros);

        String sql = "SELECT p.Codigo, p.Descripcion, p.referencia, " + expresionPrecio(filtro.isPrecioConIva()) + " AS precio, "
                + "i.fisicoInventario, p.ubicacion1, p.grupo, u.ultimoCosto, p.idSistema, p.Usuario, p.manejaInventario, "
                + "EXISTS(SELECT 1 FROM " + Tablas.PRODUCTO_LISTAS.getNombre() + " l WHERE l.idSistema = p.idSistema AND l.plu = 1) AS tienePlu, "
                + "p.indVentas, p.descripcionLarga "
                + "FROM " + Tablas.PRODUCTOS.getNombre() + " p "
                + "LEFT JOIN " + Tablas.ULTIMO_PONDERADO.getNombre() + " u ON u.producto = p.idSistema "
                + "LEFT JOIN " + Tablas.INVENTARIO.getNombre() + " i ON i.idSistema = p.idSistema AND i.idBodega = ? "
                + donde(condiciones)
                + "ORDER BY p.Descripcion";

        List<ProductoBuscador> productos = new ArrayList<>();
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            for (int i = 0; i < parametros.size(); i++) {
                ps.setObject(i + 1, parametros.get(i));
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    productos.add(mapear(rs));
                }
            }
        }

        return productos;
    }

    private void agregarCondiciones(FiltroBuscadorProductos filtro, List<String> condiciones, List<Object> parametros) {
        if (!filtro.isIncluirInactivos()) {
            condiciones.add("p.activo = false");
        }

        if (CLASE_AGENDA.equals(filtro.getClaseBuscador())) {
            condiciones.add("p.claseBuscador = ?");
            parametros.add(CLASE_AGENDA);
        }

        if (!filtro.getGrupo().isEmpty()) {
            condiciones.add("p.grupo = ?");
            parametros.add(filtro.getGrupo());
        }

        String tipoBusqueda = filtro.getTipoBusqueda();
        String grupoTipo = GRUPO_POR_TIPO_BUSQUEDA.get(tipoBusqueda);
        if (grupoTipo != null) {
            condiciones.add("p.grupo = ?");
            parametros.add(grupoTipo);
        }

        switch (tipoBusqueda) {
            case "desdeCosteo":
                condiciones.add("(p.Usuario = ? OR p.Usuario = ?)");
                parametros.add(USUARIO_FACTURA);
                parametros.add(USUARIO_COSTEO);
                break;
            case "sinArmados":
                condiciones.add("p.Usuario = ?");
                parametros.add(USUARIO_ADMIN);
                break;
            case "sinSerial":
                condiciones.add("(p.tipoProducto = '' OR p.tipoProducto IS NULL)");
                condiciones.add("p.Usuario = ?");
                parametros.add(USUARIO_ADMIN);
                break;
            default:
                break;
        }
    }

    private static String expresionPrecio(boolean precioConIva) {
        if (precioConIva) {
            return "p.L1";
        }
        return "((ROUND(p.L1 * ((p.IVA / 100) + 1), 2) + ROUND(p.L1 * ((p.impoconsumoVenta / 100) + 1), 2)) - p.L1)";
    }

    private static String donde(List<String> condiciones) {
        return condiciones.isEmpty() ? "" : "WHERE " + String.join(" AND ", condiciones) + " ";
    }

    private static ProductoBuscador mapear(ResultSet rs) throws SQLException {
        ProductoBuscador producto = new ProductoBuscador();
        producto.setCodigo(rs.getString("Codigo"));
        producto.setDescripcion(rs.getString("Descripcion"));
        producto.setReferencia(rs.getString("referencia"));
        producto.setPrecio(rs.getString("precio"));
        producto.setFisicoInventario(rs.getBigDecimal("fisicoInventario"));
        producto.setUbicacion(rs.getString("ubicacion1"));
        producto.setGrupo(rs.getString("grupo"));
        producto.setUltimoCosto(rs.getString("ultimoCosto"));
        producto.setIdSistema(rs.getString("idSistema"));
        producto.setUsuario(rs.getString("Usuario"));
        producto.setManejaInventario(manejaInventario(rs.getString("manejaInventario")));
        producto.setTienePlu(rs.getBoolean("tienePlu"));
        producto.setIndVentas(rs.getString("indVentas"));
        producto.setDescripcionLarga(rs.getString("descripcionLarga"));
        return producto;
    }

    private static boolean manejaInventario(String valor) {
        return valor == null || !(valor.equals("0") || valor.equalsIgnoreCase("false"));
    }
}
