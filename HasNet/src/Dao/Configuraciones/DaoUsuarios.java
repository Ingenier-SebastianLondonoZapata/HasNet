package Dao.Configuraciones;

import Estrategia.AbstractDao;
import Modelo.Usuarios.ModeloUsuario;
import Modelo.Usuarios.PermisoUsuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Acceso a bdUsuario para la administración de usuarios.
 *
 * Las columnas de permisos se toman de {@link PermisoUsuario}, así cada
 * permiso se lee y se escribe por su nombre de columna (no por posición) y
 * todas las consultas usan parámetros.
 */
public class DaoUsuarios extends AbstractDao {

    private static final Logger LOGGER = Logger.getLogger(DaoUsuarios.class.getName());

    private static final String TABLA = "bdUsuario";

    public boolean existe(String usuario) {
        try (PreparedStatement stmt = getConnection().prepareStatement(
                "SELECT 1 FROM " + TABLA + " WHERE usuario = ? LIMIT 1")) {
            stmt.setString(1, usuario);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al verificar el usuario " + usuario, e);
            return false;
        }
    }

    /**
     * @return el usuario con sus permisos, o null si no existe.
     */
    public ModeloUsuario consultar(String usuario) {
        String sql = "SELECT nombre, usuario, contra, " + columnasPermisos() + " FROM " + TABLA + " WHERE usuario = ?";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setString(1, usuario);
            try (ResultSet rs = stmt.executeQuery()) {
                if (!rs.next()) {
                    return null;
                }
                ModeloUsuario modelo = new ModeloUsuario();
                modelo.setNombre(rs.getString("nombre"));
                modelo.setUsuario(rs.getString("usuario"));
                modelo.setContra(rs.getString("contra"));
                for (PermisoUsuario permiso : PermisoUsuario.values()) {
                    modelo.setPermiso(permiso, rs.getBoolean(permiso.getColumna()));
                }
                return modelo;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al consultar el usuario " + usuario, e);
            return null;
        }
    }

    public boolean guardar(ModeloUsuario usuario) {
        PermisoUsuario[] permisos = PermisoUsuario.values();
        StringBuilder valores = new StringBuilder("?,?,?");
        for (int i = 0; i < permisos.length; i++) {
            valores.append(",?");
        }
        String sql = "INSERT INTO " + TABLA + " (nombre, usuario, contra, " + columnasPermisos() + ") VALUES (" + valores + ")";

        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            int indice = 1;
            stmt.setString(indice++, usuario.getNombre());
            stmt.setString(indice++, usuario.getUsuario());
            stmt.setString(indice++, usuario.getContra());
            for (PermisoUsuario permiso : permisos) {
                stmt.setBoolean(indice++, usuario.tienePermiso(permiso));
            }
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al guardar el usuario " + usuario.getUsuario(), e);
            return false;
        }
    }

    public boolean modificar(ModeloUsuario usuario) {
        StringBuilder sql = new StringBuilder("UPDATE " + TABLA + " SET nombre = ?, contra = ?");
        for (PermisoUsuario permiso : PermisoUsuario.values()) {
            sql.append(", ").append(permiso.getColumna()).append(" = ?");
        }
        sql.append(" WHERE usuario = ?");

        try (PreparedStatement stmt = getConnection().prepareStatement(sql.toString())) {
            int indice = 1;
            stmt.setString(indice++, usuario.getNombre());
            stmt.setString(indice++, usuario.getContra());
            for (PermisoUsuario permiso : PermisoUsuario.values()) {
                stmt.setBoolean(indice++, usuario.tienePermiso(permiso));
            }
            stmt.setString(indice, usuario.getUsuario());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al modificar el usuario " + usuario.getUsuario(), e);
            return false;
        }
    }

    private String columnasPermisos() {
        StringBuilder columnas = new StringBuilder();
        for (PermisoUsuario permiso : PermisoUsuario.values()) {
            if (columnas.length() > 0) {
                columnas.append(", ");
            }
            columnas.append(permiso.getColumna());
        }
        return columnas.toString();
    }
}
