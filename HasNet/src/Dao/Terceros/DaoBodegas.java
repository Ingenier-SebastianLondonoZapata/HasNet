package Dao.Terceros;

import Enums.Tablas;
import Estrategia.AbstractDao;
import Modelo.Contactos.ModeloBodega;
import Utilidades.Contactos.Bodegas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoBodegas extends AbstractDao {

    private static final Logger LOGGER = Logger.getLogger(DaoBodegas.class.getName());

    private static final String CONSECUTIVO_BODEGA = "BODEGA";
    private static final String PREFIJO_CODIGO = "BODEGA-";

    private static final String TABLA = Tablas.BODEGAS.getNombre();

    private static final String COLUMNAS = "codigo, contacto, nombre, tipoBodega, estado, usuario";

    private static final String SQL_INSERTAR = "INSERT INTO " + TABLA + " (" + COLUMNAS + ") VALUES (?,?,?,?,?,?)";

    private static final String SQL_ACTUALIZAR = "UPDATE " + TABLA + " SET nombre = ?, estado = ? WHERE codigo = ?";

    public ModeloBodega consultarPorCodigo(String codigo) {
        List<ModeloBodega> bodegas = consultar(" WHERE codigo = ?", codigo);
        return bodegas.isEmpty() ? null : bodegas.get(0);
    }

    public List<ModeloBodega> consultarPorContacto(String idSistemaContacto) {
        return consultar(" WHERE contacto = ? ORDER BY codigo", idSistemaContacto);
    }

    public List<ModeloBodega> listarPorTipo(String tipoBodega) {
        return consultar(" WHERE tipoBodega = ? ORDER BY codigo", tipoBodega);
    }

    public List<ModeloBodega> listarInternas() {
        return listarPorTipo(Bodegas.TIPO_INTERNA);
    }

    public List<ModeloBodega> listarTodas() {
        return consultar(" ORDER BY codigo");
    }

    public boolean existe(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            return false;
        }

        try (PreparedStatement stmt = getConnection().prepareStatement("SELECT 1 FROM " + TABLA + " WHERE codigo = ? LIMIT 1")) {
            stmt.setString(1, codigo.trim());
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al verificar la bodega " + codigo, e);
            return false;
        }
    }

    public String guardar(ModeloBodega bodega) {
        Connection conexion = getConnection();
        boolean autoCommitPrevio = true;

        try {
            autoCommitPrevio = conexion.getAutoCommit();
            conexion.setAutoCommit(false);

            insertar(conexion, texto(bodega.getContacto()), Collections.singletonList(bodega));

            conexion.commit();
            return bodega.getCodigo();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al guardar la bodega " + bodega.getNombre(), e);
            revertir(conexion);
            return null;
        } finally {
            restaurarAutoCommit(conexion, autoCommitPrevio);
        }
    }

    public boolean modificar(ModeloBodega bodega) {
        try {
            actualizar(getConnection(), bodega);
            return true;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al modificar la bodega " + bodega.getCodigo(), e);
            return false;
        }
    }

    void sincronizar(Connection conexion, String idSistemaContacto, List<ModeloBodega> bodegas) throws SQLException {
        if (bodegas == null || bodegas.isEmpty()) {
            return;
        }

        List<ModeloBodega> nuevas = new ArrayList<>();

        for (ModeloBodega bodega : bodegas) {
            if (bodega.esNueva()) {
                nuevas.add(bodega);
            } else {
                actualizar(conexion, bodega);
            }
        }

        insertar(conexion, idSistemaContacto, nuevas);
    }

    private List<ModeloBodega> consultar(String filtro, String... parametros) {
        List<ModeloBodega> bodegas = new ArrayList<>();

        try (PreparedStatement stmt = getConnection().prepareStatement("SELECT " + COLUMNAS + " FROM " + TABLA + filtro)) {
            for (int i = 0; i < parametros.length; i++) {
                stmt.setString(i + 1, parametros[i]);
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    bodegas.add(new ModeloBodega(rs.getString("codigo"), rs.getString("contacto"), rs.getString("nombre"),
                            rs.getString("tipoBodega"), rs.getString("estado"), rs.getString("usuario")));
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al consultar las bodegas" + filtro, e);
        }
        return bodegas;
    }

    private void actualizar(Connection conexion, ModeloBodega bodega) throws SQLException {
        try (PreparedStatement stmt = conexion.prepareStatement(SQL_ACTUALIZAR)) {
            stmt.setString(1, texto(bodega.getNombre()));
            stmt.setString(2, Bodegas.estado(bodega.getEstado()));
            stmt.setString(3, bodega.getCodigo());
            stmt.executeUpdate();
        }
    }

    private void insertar(Connection conexion, String idSistemaContacto, List<ModeloBodega> nuevas) throws SQLException {
        if (nuevas.isEmpty()) {
            return;
        }

        int consecutivo = leerConsecutivo(conexion);

        try (PreparedStatement stmt = conexion.prepareStatement(SQL_INSERTAR)) {
            for (ModeloBodega bodega : nuevas) {
                bodega.setCodigo(PREFIJO_CODIGO + consecutivo);

                if (bodega.getContacto() == null) {
                    bodega.setContacto(idSistemaContacto);
                }

                stmt.setString(1, bodega.getCodigo());
                stmt.setString(2, texto(bodega.getContacto()));
                stmt.setString(3, texto(bodega.getNombre()));
                stmt.setString(4, texto(bodega.getTipoBodega()));
                stmt.setString(5, Bodegas.estado(bodega.getEstado()));
                stmt.setString(6, texto(bodega.getUsuario()));
                stmt.addBatch();

                consecutivo++;
            }
            stmt.executeBatch();
        }

        actualizarConsecutivo(conexion, consecutivo);
    }

    private int leerConsecutivo(Connection conexion) throws SQLException {
        try (PreparedStatement stmt = conexion.prepareStatement("SELECT numero FROM " + Tablas.CONSECUTIVOS.getNombre()
                + " WHERE Id = ? FOR UPDATE")) {
            stmt.setString(1, CONSECUTIVO_BODEGA);
            try (ResultSet rs = stmt.executeQuery()) {
                if (!rs.next()) {
                    throw new SQLException("No existe el consecutivo " + CONSECUTIVO_BODEGA);
                }
                return Integer.parseInt(rs.getString(1).trim());
            }
        }
    }

    private void actualizarConsecutivo(Connection conexion, int numero) throws SQLException {
        try (PreparedStatement stmt = conexion.prepareStatement("UPDATE " + Tablas.CONSECUTIVOS.getNombre()
                + " SET numero = ? WHERE Id = ?")) {
            stmt.setString(1, String.valueOf(numero));
            stmt.setString(2, CONSECUTIVO_BODEGA);
            stmt.executeUpdate();
        }
    }

    private String texto(String valor) {
        return valor == null ? "" : valor;
    }

    private void revertir(Connection conexion) {
        try {
            conexion.rollback();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "No se pudo revertir la transacción de bodegas", e);
        }
    }

    private void restaurarAutoCommit(Connection conexion, boolean autoCommit) {
        try {
            conexion.setAutoCommit(autoCommit);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "No se pudo restaurar el autocommit", e);
        }
    }
}
