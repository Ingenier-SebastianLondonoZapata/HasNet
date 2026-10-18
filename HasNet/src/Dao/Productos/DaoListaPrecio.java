package Dao.Productos;

import Enums.Tablas;
import Estrategia.AbstractDao;
import Modelo.Productos.ModeloListaPrecio;
import Modelo.Productos.ModeloProducto;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoListaPrecio extends AbstractDao {

    private static final Logger LOGGER = Logger.getLogger(DaoListaPrecio.class.getName());

    private static final String TABLA = Tablas.PRODUCTO_LISTAS.getNombre();

    public void cargarListas(ModeloProducto producto) {
        if (producto.getIdSistema() == null) {
            return;
        }

        producto.setListas(obtenerListas(producto.getIdSistema()));
    }

    public List<ModeloListaPrecio> obtenerListas(String idSistema) {
        String sql = "SELECT numeroLista, precio, codigo, descripcion, ubicacion, cantidad, plu FROM " + TABLA
                + " WHERE idSistema = ? ORDER BY numeroLista";

        List<ModeloListaPrecio> listas = new ArrayList<>();

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, idSistema);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ModeloListaPrecio lista = new ModeloListaPrecio(rs.getInt("numeroLista"));
                    lista.setPrecio(rs.getBigDecimal("precio"));
                    lista.setCodigo(rs.getString("codigo"));
                    lista.setDescripcion(rs.getString("descripcion"));
                    lista.setUbicacion(rs.getString("ubicacion"));
                    lista.setCantidad(rs.getBigDecimal("cantidad"));
                    lista.setPlu(rs.getBoolean("plu"));
                    listas.add(lista);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al consultar las listas de precios del producto " + idSistema, e);
        }

        return listas;
    }

    public void reemplazarListas(Connection conn, String idSistema, Collection<ModeloListaPrecio> listas) throws SQLException {
        try (PreparedStatement borrar = conn.prepareStatement("DELETE FROM " + TABLA + " WHERE idSistema = ?")) {
            borrar.setString(1, idSistema);
            borrar.executeUpdate();
        }

        String sql = "INSERT INTO " + TABLA + " (idSistema, numeroLista, precio, codigo, descripcion, ubicacion, cantidad, plu) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement insertar = conn.prepareStatement(sql)) {
            for (ModeloListaPrecio lista : listas) {
                if (lista.esVacia()) {
                    continue;
                }

                insertar.setString(1, idSistema);
                insertar.setInt(2, lista.getNumeroLista());
                insertar.setBigDecimal(3, lista.getPrecio());
                insertar.setString(4, vacioANulo(lista.getCodigo()));
                insertar.setString(5, vacioANulo(lista.getDescripcion()));
                insertar.setString(6, vacioANulo(lista.getUbicacion()));
                insertar.setBigDecimal(7, lista.getCantidad());
                insertar.setBoolean(8, lista.isPlu());
                insertar.addBatch();
            }

            insertar.executeBatch();
        }
    }


    public List<Integer> obtenerNumerosLista() {
        java.util.SortedSet<Integer> numeros = new java.util.TreeSet<>();
        for (int numero = ModeloListaPrecio.LISTA_PRINCIPAL; numero <= ModeloListaPrecio.MAXIMO_LISTA; numero++) {
            numeros.add(numero);
        }

        try (PreparedStatement ps = getConnection().prepareStatement("SELECT DISTINCT numeroLista FROM " + TABLA + " ORDER BY numeroLista");
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                numeros.add(rs.getInt(1));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al consultar los números de lista de precios en uso", e);
        }

        return new ArrayList<>(numeros);
    }

    public String obtenerProductoConCodigo(String codigo, String idSistemaExcluido) {
        String sql = "SELECT idSistema FROM " + TABLA + " WHERE codigo = ? AND idSistema <> ? LIMIT 1";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, codigo);
            ps.setString(2, idSistemaExcluido == null ? "" : idSistemaExcluido);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString(1);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al validar el código de lista " + codigo, e);
        }

        return null;
    }

    public boolean modificarPrecioLista(String codigoProducto, String lista, String precio) {
        int numero = ModeloProducto.numeroDeLista(lista);

        try {
            BigDecimal valor = new BigDecimal(precio.trim().replace(",", "."));

            if (numero == ModeloListaPrecio.LISTA_PRINCIPAL) {
                try (PreparedStatement ps = getConnection().prepareStatement("UPDATE bdProductos SET L1 = ? WHERE Codigo = ?")) {
                    ps.setBigDecimal(1, valor);
                    ps.setString(2, codigoProducto);
                    ps.executeUpdate();
                }
            } else {
                String sql = "INSERT INTO " + TABLA + " (idSistema, numeroLista, precio) SELECT idSistema, ?, ? FROM bdProductos WHERE Codigo = ? "
                        + "ON DUPLICATE KEY UPDATE precio = VALUES(precio)";
                try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
                    ps.setInt(1, numero);
                    ps.setBigDecimal(2, valor);
                    ps.setString(3, codigoProducto);
                    ps.executeUpdate();
                }
            }

            return true;
        } catch (SQLException | NumberFormatException e) {
            LOGGER.log(Level.SEVERE, "Error al actualizar la lista " + lista + " del producto " + codigoProducto, e);
            return false;
        }
    }

    public String[] obtenerNombresLista(boolean conVacio) {
        List<String> nombres = new ArrayList<>();
        if (conVacio) {
            nombres.add(" ");
        }
        for (Integer numero : obtenerNumerosLista()) {
            nombres.add("L" + numero);
        }

        return nombres.toArray(new String[0]);
    }

    private static String vacioANulo(String valor) {
        return valor == null || valor.trim().isEmpty() ? null : valor;
    }
}
