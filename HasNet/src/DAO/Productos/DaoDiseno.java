package dao.Productos;

import Enums.Tablas;
import Estrategia.AbstractDao;
import Modelo.Inventario.ComponenteDiscosteo;
import Modelo.Productos.LineaCosteoDiseno;
import Utilidades.BaseDatos.MySql_connection;
import Utilidades.Constantes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoDiseno extends AbstractDao {

    private static final Logger LOGGER = Logger.getLogger(DaoDiseno.class.getName());

    public List<ComponenteDiscosteo> obtenerComponentes(String codigoDiscosteo) throws SQLException {

        String sql = "SELECT producto, cantidad2 FROM " + Tablas.DISCOSTEO.getNombre() + " WHERE codigo = ?";

        List<ComponenteDiscosteo> componentes = new ArrayList<>();

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, codigoDiscosteo);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    componentes.add(new ComponenteDiscosteo(
                            rs.getString("producto"),
                            rs.getBigDecimal("cantidad2")));
                }
            }
        }

        return componentes;
    }

    public void guardarLineasCosteo(List<LineaCosteoDiseno> lineas) throws SQLException {
        if (lineas == null || lineas.isEmpty()) {
            return;
        }

        String sql = "INSERT INTO bdDisCosteo(producto, cantidad, codigo, usuario, plu, cantidad2, descripcion, opcionCambio, tipo) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        Connection conexion = MySql_connection.getInstancia(Constantes.BASE_DATOS_PRINCIPAL).getConnection();
        if (conexion == null) {
            throw new SQLException("No se pudo obtener una conexión a la base de datos");
        }

        try {
            conexion.setAutoCommit(false);

            try (PreparedStatement ps = conexion.prepareStatement(sql)) {
                for (LineaCosteoDiseno linea : lineas) {
                    ps.setString(1, linea.getProducto());
                    ps.setBigDecimal(2, linea.getCantidad());
                    ps.setString(3, linea.getCodigo());
                    ps.setString(4, linea.getUsuario());
                    ps.setString(5, linea.getPlu());
                    ps.setBigDecimal(6, linea.getCantidad2());
                    ps.setString(7, linea.getDescripcion());
                    ps.setBoolean(8, linea.isOpcionCambio());
                    ps.setString(9, linea.getTipo());
                    ps.addBatch();
                }

                ps.executeBatch();
            }

            conexion.commit();
        } catch (SQLException e) {
            revertir(conexion);
            throw e;
        } finally {
            restaurarAutoCommit(conexion);
        }
    }

    private void revertir(Connection conexion) {
        try {
            conexion.rollback();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error al revertir el costeo del diseño", ex);
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
