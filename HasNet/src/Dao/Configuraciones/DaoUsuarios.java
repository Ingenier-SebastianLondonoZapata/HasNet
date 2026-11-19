package Dao.Configuraciones;

import Estrategia.AbstractDao;
import Modelo.Usuarios.ModeloUsuario;
import Modelo.Usuarios.PermisoUsuario;
import Utilidades.Constantes;
import Utilidades.Seguridad.ContrasenasSeguras;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoUsuarios extends AbstractDao {

    private static final Logger LOGGER = Logger.getLogger(DaoUsuarios.class.getName());

    private static final String TABLA = Constantes.TABLA_USUARIOS;

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
            stmt.setString(indice++, ContrasenasSeguras.asegurarHash(usuario.getContra()));
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
        boolean cambiaContra = usuario.getContra() != null && !usuario.getContra().isEmpty();
        StringBuilder sql = new StringBuilder("UPDATE " + TABLA + " SET nombre = ?");
        if (cambiaContra) {
            sql.append(", contra = ?");
        }
        for (PermisoUsuario permiso : PermisoUsuario.values()) {
            sql.append(", ").append(permiso.getColumna()).append(" = ?");
        }
        sql.append(" WHERE usuario = ?");

        try (PreparedStatement stmt = getConnection().prepareStatement(sql.toString())) {
            int indice = 1;
            stmt.setString(indice++, usuario.getNombre());
            if (cambiaContra) {
                stmt.setString(indice++, ContrasenasSeguras.asegurarHash(usuario.getContra()));
            }
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

    public boolean actualizarContra(String usuario, String hash) {
        try (PreparedStatement stmt = getConnection().prepareStatement("UPDATE " + TABLA + " SET contra = ? WHERE usuario = ?")) {
            stmt.setString(1, hash);
            stmt.setString(2, usuario);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al actualizar la contraseña del usuario " + usuario, e);
            return false;
        }
    }

    public String[] estadoSesion(String usuario) {
        try (PreparedStatement stmt = getConnection().prepareStatement("SELECT estado, terminal FROM " + TABLA + " WHERE usuario = ?")) {
            stmt.setString(1, usuario);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new String[]{valor(rs.getString("estado")), valor(rs.getString("terminal"))};
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al consultar el estado de sesión del usuario " + usuario, e);
        }
        return new String[]{"", ""};
    }

    public boolean actualizarEstadoSesion(String usuario, String estado, String terminal) {
        try (PreparedStatement stmt = getConnection().prepareStatement("UPDATE " + TABLA + " SET estado = ?, terminal = ? WHERE usuario = ?")) {
            stmt.setString(1, estado);
            stmt.setString(2, terminal);
            stmt.setString(3, usuario);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al actualizar el estado de sesión del usuario " + usuario, e);
            return false;
        }
    }

    public boolean reservarSesion(String usuario, String terminal, int minutosVencimiento) {
        String sql = "UPDATE " + TABLA + " u SET u.estado = 'ON', u.terminal = ? WHERE u.usuario = ? AND ("
                + "u.estado IS NULL OR u.estado <> 'ON' OR u.terminal IS NULL OR u.terminal = '' OR u.terminal = ? "
                + "OR NOT EXISTS (SELECT 1 FROM " + Constantes.TABLA_TERMINALES + " t WHERE t.terminal = u.terminal "
                + "AND t.ultimaSenal >= NOW() - INTERVAL ? MINUTE))";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setString(1, terminal);
            stmt.setString(2, usuario);
            stmt.setString(3, terminal);
            stmt.setInt(4, minutosVencimiento);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al reservar la sesión del usuario " + usuario, e);
            return false;
        }
    }

    public boolean liberarSesion(String usuario, String terminal) {
        try (PreparedStatement stmt = getConnection().prepareStatement("UPDATE " + TABLA
                + " SET estado = 'OFF', terminal = '' WHERE usuario = ? AND (terminal = ? OR terminal IS NULL OR terminal = '')")) {
            stmt.setString(1, usuario);
            stmt.setString(2, terminal);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al liberar la sesión del usuario " + usuario, e);
            return false;
        }
    }

    public List<String[]> listar() {
        List<String[]> usuarios = new ArrayList<>();
        try (PreparedStatement stmt = getConnection().prepareStatement("SELECT usuario, nombre, estado, terminal FROM " + TABLA + " ORDER BY usuario");
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                usuarios.add(new String[]{valor(rs.getString("usuario")), valor(rs.getString("nombre")),
                    valor(rs.getString("estado")), valor(rs.getString("terminal"))});
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al listar los usuarios", e);
        }
        return usuarios;
    }

    private static String valor(String texto) {
        return texto == null ? "" : texto;
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
