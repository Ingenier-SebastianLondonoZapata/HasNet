package Dao.Productos;

import Enums.Tablas;
import Estrategia.AbstractDao;
import Modelo.Productos.LineaAjusteInventario;
import Modelo.Productos.ModeloAjusteInventario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoAjusteInventario extends AbstractDao {

    private static final Logger LOGGER = Logger.getLogger(DaoAjusteInventario.class.getName());
    private static final String SIN_CONEXION = "No se pudo obtener una conexión a la base de datos";

    public void guardar(ModeloAjusteInventario ajuste) throws SQLException {
        Connection conexion = getConnection();
        if (conexion == null) {
            throw new SQLException(SIN_CONEXION);
        }

        try {
            conexion.setAutoCommit(false);
            insertarEncabezado(conexion, ajuste);
            insertarLineas(conexion, ajuste);
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
            borrar(conexion, "DELETE FROM " + Tablas.TRASLADOS_DETALLE.getNombre() + " WHERE traslado = ?", documento);
            borrar(conexion, "DELETE FROM " + Tablas.TRASLADOS.getNombre() + " WHERE id = ?", documento);
            conexion.commit();
        } catch (SQLException e) {
            revertir(conexion);
            throw e;
        } finally {
            restaurarAutoCommit(conexion);
        }
    }

    public ModeloAjusteInventario obtener(String documento) throws SQLException {
        String sql = "SELECT id, origen, fecha, tipo, usuario, terminal, hora, anulada FROM "
                + Tablas.TRASLADOS.getNombre() + " WHERE id = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, documento);

            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    return null;
                }

                ModeloAjusteInventario ajuste = new ModeloAjusteInventario();
                ajuste.setDocumento(rs.getString("id"));
                ajuste.setIdBodega(rs.getString("origen"));
                ajuste.setFecha(rs.getString("fecha"));
                ajuste.setTipo(rs.getString("tipo"));
                ajuste.setUsuario(rs.getString("usuario"));
                ajuste.setTerminal(rs.getString("terminal"));
                ajuste.setHora(rs.getString("hora"));
                ajuste.setAnulado(rs.getBoolean("anulada"));

                cargarLineas(ajuste);

                return ajuste;
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
        String sql = "UPDATE " + Tablas.TRASLADOS.getNombre() + " SET anulada = ? WHERE id = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setBoolean(1, anulada);
            ps.setString(2, documento);
            return ps.executeUpdate() > 0;
        }
    }

    private void cargarLineas(ModeloAjusteInventario ajuste) throws SQLException {
        String sql = "SELECT producto, cantidad, cantidad2, idProd FROM " + Tablas.TRASLADOS_DETALLE.getNombre() + " WHERE traslado = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, ajuste.getDocumento());

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    LineaAjusteInventario linea = new LineaAjusteInventario();
                    linea.setIdSistema(rs.getString("producto"));
                    linea.setCantidad(rs.getBigDecimal("cantidad"));
                    linea.setCantidadInventario(rs.getBigDecimal("cantidad2"));
                    linea.setIdDetalleProducto(rs.getString("idProd"));
                    ajuste.agregarLinea(linea);
                }
            }
        }
    }

    public String obtenerConsecutivo(String idConsecutivo) throws SQLException {
        String sql = "SELECT numero FROM " + Tablas.CONSECUTIVOS.getNombre() + " WHERE Id = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, idConsecutivo);

            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    throw new SQLException("No existe el consecutivo " + idConsecutivo);
                }
                return rs.getString("numero").trim();
            }
        }
    }

    public void actualizarConsecutivo(String idConsecutivo, int numero) throws SQLException {
        String sql = "UPDATE " + Tablas.CONSECUTIVOS.getNombre() + " SET numero = ? WHERE Id = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, String.valueOf(numero));
            ps.setString(2, idConsecutivo);
            ps.executeUpdate();
        }
    }

    private void insertarEncabezado(Connection conexion, ModeloAjusteInventario ajuste) throws SQLException {
        String sql = "INSERT INTO " + Tablas.TRASLADOS.getNombre()
                + " (id, origen, destino, fecha, unidades, tipo, usuario, terminal, hora, valor)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, ajuste.getDocumento());
            ps.setString(2, ajuste.getIdBodega());
            ps.setString(3, ajuste.getIdBodega());
            ps.setString(4, ajuste.getFecha());
            ps.setBigDecimal(5, ajuste.getUnidades());
            ps.setString(6, ajuste.getTipo());
            ps.setString(7, ajuste.getUsuario());
            ps.setString(8, ajuste.getTerminal());
            ps.setString(9, ajuste.getHora());
            ps.setBigDecimal(10, ajuste.getValorTotal());
            ps.executeUpdate();
        }
    }

    private void insertarLineas(Connection conexion, ModeloAjusteInventario ajuste) throws SQLException {
        String sql = "INSERT INTO " + Tablas.TRASLADOS_DETALLE.getNombre()
                + " (traslado, producto, cantidad, observacion, descripcion, plu, cantidad2, idProd, detalle, porcIva, porcImpo,"
                + " lista, total, subtotal, iva, costo, impoconsumo)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            for (LineaAjusteInventario linea : ajuste.getLineas()) {
                ps.setString(1, ajuste.getDocumento());
                ps.setString(2, linea.getIdSistema());
                ps.setBigDecimal(3, linea.getCantidad());
                ps.setString(4, linea.getObservaciones());
                ps.setString(5, linea.getDescripcion());
                ps.setString(6, linea.getPlu());
                ps.setBigDecimal(7, linea.getCantidadInventario());
                ps.setString(8, linea.getIdDetalleProducto());
                ps.setString(9, linea.getDetalle());
                ps.setBigDecimal(10, linea.getPorcentajeIva());
                ps.setBigDecimal(11, linea.getPorcentajeImpoconsumo());
                ps.setBigDecimal(12, linea.getValorUnitario());
                ps.setBigDecimal(13, linea.getTotal());
                ps.setBigDecimal(14, linea.getSubtotal());
                ps.setBigDecimal(15, linea.getIva());
                ps.setBigDecimal(16, linea.getCosto());
                ps.setBigDecimal(17, linea.getImpoconsumo());
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
            LOGGER.log(Level.SEVERE, "Error al revertir el ajuste de inventario", ex);
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
