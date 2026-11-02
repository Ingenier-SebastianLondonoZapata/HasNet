package Dao.Productos;

import Enums.Tablas;
import Estrategia.AbstractDao;
import Modelo.Productos.LineaArmado;
import Modelo.Productos.ModeloArmado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoArmado extends AbstractDao {

    private static final Logger LOGGER = Logger.getLogger(DaoArmado.class.getName());
    private static final String SIN_CONEXION = "No se pudo obtener una conexión a la base de datos";

    public void guardar(ModeloArmado armado) throws SQLException {
        Connection conexion = getConnection();
        if (conexion == null) {
            throw new SQLException(SIN_CONEXION);
        }

        try {
            conexion.setAutoCommit(false);
            insertarEncabezado(conexion, armado);
            insertarLineas(conexion, armado);
            conexion.commit();
        } catch (SQLException e) {
            revertir(conexion);
            throw e;
        } finally {
            restaurarAutoCommit(conexion);
        }
    }

    public void eliminar(String documento) throws SQLException {
        Connection conexion = getConnection();
        if (conexion == null) {
            throw new SQLException(SIN_CONEXION);
        }

        try {
            conexion.setAutoCommit(false);
            borrar(conexion, "DELETE FROM " + Tablas.ARMADO_PRODUCTO.getNombre() + " WHERE documento = ?", documento);
            borrar(conexion, "DELETE FROM " + Tablas.ARMADO.getNombre() + " WHERE Id = ?", documento);
            conexion.commit();
        } catch (SQLException e) {
            revertir(conexion);
            throw e;
        } finally {
            restaurarAutoCommit(conexion);
        }
    }

    public ModeloArmado obtener(String documento) throws SQLException {
        String sql = "SELECT Id, fecha, usuario, producto, tipo, bodega, unidades, total, anulada FROM "
                + Tablas.ARMADO.getNombre() + " WHERE Id = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, documento);

            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    return null;
                }

                ModeloArmado armado = new ModeloArmado();
                armado.setDocumento(rs.getString("Id"));
                armado.setFecha(rs.getString("fecha"));
                armado.setUsuario(rs.getString("usuario"));
                armado.setProducto(rs.getString("producto"));
                armado.setTipo(rs.getString("tipo"));
                armado.setIdBodega(rs.getString("bodega"));
                armado.setUnidades(rs.getBigDecimal("unidades"));
                armado.setCostoTotal(rs.getBigDecimal("total"));
                armado.setAnulado(rs.getBoolean("anulada"));

                cargarLineas(armado);

                return armado;
            }
        }
    }

    public boolean anular(String documento) throws SQLException {
        return marcarAnulada(documento, true);
    }

    public boolean reactivar(String documento) throws SQLException {
        return marcarAnulada(documento, false);
    }

    private boolean marcarAnulada(String documento, boolean anulada) throws SQLException {
        String sql = "UPDATE " + Tablas.ARMADO.getNombre() + " SET anulada = ? WHERE Id = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setBoolean(1, anulada);
            ps.setString(2, documento);
            return ps.executeUpdate() > 0;
        }
    }

    private void cargarLineas(ModeloArmado armado) throws SQLException {
        String sql = "SELECT producto, cantidad, total FROM " + Tablas.ARMADO_PRODUCTO.getNombre() + " WHERE documento = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, armado.getDocumento());

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    armado.agregarLinea(new LineaArmado(
                            rs.getString("producto"),
                            rs.getBigDecimal("cantidad"),
                            rs.getBigDecimal("total")));
                }
            }
        }
    }

    private void insertarEncabezado(Connection conexion, ModeloArmado armado) throws SQLException {
        String sql = "INSERT INTO " + Tablas.ARMADO.getNombre()
                + " (Id, fecha, usuario, producto, tipo, bodega, unidades, total) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, armado.getDocumento());
            ps.setString(2, armado.getFecha());
            ps.setString(3, armado.getUsuario());
            ps.setString(4, armado.getProducto());
            ps.setString(5, armado.getTipo());
            ps.setString(6, armado.getIdBodega());
            ps.setBigDecimal(7, armado.getUnidades());
            ps.setBigDecimal(8, armado.getCostoTotal());
            ps.executeUpdate();
        }
    }

    private void insertarLineas(Connection conexion, ModeloArmado armado) throws SQLException {
        if (armado.getLineas().isEmpty()) {
            return;
        }

        String sql = "INSERT INTO " + Tablas.ARMADO_PRODUCTO.getNombre()
                + " (documento, producto, cantidad, total) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            for (LineaArmado linea : armado.getLineas()) {
                ps.setString(1, armado.getDocumento());
                ps.setString(2, linea.getProducto());
                ps.setBigDecimal(3, linea.getCantidadUnitaria());
                ps.setBigDecimal(4, linea.getCantidadTotal());
                ps.addBatch();
            }

            ps.executeBatch();
        }
    }

    private void borrar(Connection conexion, String sql, String documento) throws SQLException {
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, documento);
            ps.executeUpdate();
        }
    }

    private void revertir(Connection conexion) {
        try {
            conexion.rollback();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error al revertir el armado", ex);
        }
    }

    private void restaurarAutoCommit(Connection conexion) {
        try {
            conexion.setAutoCommit(true);
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error al restaurar el autocommit de la conexión", ex);
        }
    }
}
