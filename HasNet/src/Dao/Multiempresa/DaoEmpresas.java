package Dao.Multiempresa;

import Modelo.Multiempresa.Empresa;
import Utilidades.BaseDatos.CredencialesBaseDatos;
import Utilidades.BaseDatos.MySql_connection;
import Utilidades.Constantes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoEmpresas {

    private static final Logger LOGGER = Logger.getLogger(DaoEmpresas.class.getName());
    private static final String EMPRESAS = Constantes.TABLA_EMPRESAS;
    private static final String ACCESOS = Constantes.TABLA_USUARIOS_EMPRESA;
    private static final String COLUMNAS = "id, descripcion, base, nit, tipoIdentificacion, estado";

    private static volatile boolean esquemaVerificado;

    public void asegurarEsquema() throws SQLException {
        if (esquemaVerificado) {
            return;
        }
        try (Connection conexion = MySql_connection.crearConexionPrincipal(); Statement sentencia = conexion.createStatement()) {
            sentencia.execute("CREATE TABLE IF NOT EXISTS " + EMPRESAS + " (id VARCHAR(5) NOT NULL, descripcion VARCHAR(100) NULL, "
                    + "base VARCHAR(25) NULL, PRIMARY KEY (id)) ENGINE=InnoDB DEFAULT CHARSET=latin1");
            agregarColumna(conexion, "bdmultiempresa", "nit", "VARCHAR(30) NULL");
            agregarColumna(conexion, "bdmultiempresa", "tipoIdentificacion", "VARCHAR(30) NULL");
            agregarColumna(conexion, "bdmultiempresa", "estado", "TINYINT(1) NOT NULL DEFAULT 1");
            agregarColumna(conexion, "bdmultiempresa", "fechaCreacion", "DATETIME NULL");
            agregarColumna(conexion, "bdmultiempresa", "usuarioCreacion", "VARCHAR(255) NULL");
            sentencia.execute("CREATE TABLE IF NOT EXISTS " + ACCESOS + " (usuario VARCHAR(255) NOT NULL, idEmpresa VARCHAR(5) NOT NULL, "
                    + "PRIMARY KEY (usuario, idEmpresa), KEY idx_bdUsuarioEmpresa_empresa (idEmpresa)) ENGINE=InnoDB DEFAULT CHARSET=utf8");
        }
        esquemaVerificado = true;
    }

    private void agregarColumna(Connection conexion, String tabla, String columna, String definicion) throws SQLException {
        try (PreparedStatement consulta = conexion.prepareStatement("SELECT COUNT(*) FROM information_schema.COLUMNS WHERE TABLE_SCHEMA = DATABASE() "
                + "AND LOWER(TABLE_NAME) = ? AND LOWER(COLUMN_NAME) = ?")) {
            consulta.setString(1, tabla.toLowerCase());
            consulta.setString(2, columna.toLowerCase());
            try (ResultSet resultado = consulta.executeQuery()) {
                if (resultado.next() && resultado.getInt(1) > 0) {
                    return;
                }
            }
        }
        try (Statement sentencia = conexion.createStatement()) {
            sentencia.execute("ALTER TABLE " + EMPRESAS + " ADD COLUMN " + columna + " " + definicion);
        }
    }

    public List<Empresa> listar(boolean soloActivas) {
        List<Empresa> empresas = new ArrayList<>();
        try {
            asegurarEsquema();
            String sql = "SELECT " + COLUMNAS + " FROM " + EMPRESAS + (soloActivas ? " WHERE estado = 1" : "") + " ORDER BY id";
            try (Connection conexion = MySql_connection.crearConexionPrincipal();
                    PreparedStatement stmt = conexion.prepareStatement(sql);
                    ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Empresa empresa = leer(rs);
                    if (!empresa.isPrincipal()) {
                        empresas.add(empresa);
                    }
                }
            }
        } catch (SQLException | IllegalStateException ex) {
            LOGGER.log(Level.SEVERE, "No se pudieron consultar las empresas", ex);
        }
        return empresas;
    }

    public Empresa consultar(String id) {
        if (Empresa.ID_PRINCIPAL.equals(id)) {
            return empresaPrincipal();
        }
        try {
            asegurarEsquema();
            try (Connection conexion = MySql_connection.crearConexionPrincipal();
                    PreparedStatement stmt = conexion.prepareStatement("SELECT " + COLUMNAS + " FROM " + EMPRESAS + " WHERE id = ?")) {
                stmt.setString(1, id);
                try (ResultSet rs = stmt.executeQuery()) {
                    return rs.next() ? leer(rs) : null;
                }
            }
        } catch (SQLException | IllegalStateException ex) {
            LOGGER.log(Level.SEVERE, "No se pudo consultar la empresa " + id, ex);
            return null;
        }
    }

    public Empresa empresaPrincipal() {
        String nombre = null;
        String nit = null;
        try (Connection conexion = MySql_connection.crearConexionPrincipal();
                PreparedStatement stmt = conexion.prepareStatement("SELECT nombreEmpresa, nitEmpresa FROM "
                        + Constantes.tablaPrincipal("bdMaestra") + " ORDER BY Id LIMIT 1");
                ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                nombre = rs.getString("nombreEmpresa");
                nit = rs.getString("nitEmpresa");
            }
        } catch (SQLException | IllegalStateException ex) {
            LOGGER.log(Level.WARNING, "No se pudo consultar el nombre de la empresa principal", ex);
        }
        return Empresa.principal(nombre, nit);
    }

    public List<Empresa> empresasDeUsuario(String usuario) {
        List<Empresa> empresas = new ArrayList<>();
        empresas.add(empresaPrincipal());
        Set<String> permitidas = empresasPermitidas(usuario);
        for (Empresa empresa : listar(true)) {
            if (permitidas.contains(empresa.getId())) {
                empresas.add(empresa);
            }
        }
        return empresas;
    }

    public boolean tieneAcceso(String usuario, Empresa empresa) {
        return empresa != null && (empresa.isPrincipal() || (empresa.isActiva() && empresasPermitidas(usuario).contains(empresa.getId())));
    }

    private Set<String> empresasPermitidas(String usuario) {
        Set<String> ids = new LinkedHashSet<>();
        try {
            asegurarEsquema();
            try (Connection conexion = MySql_connection.crearConexionPrincipal();
                    PreparedStatement stmt = conexion.prepareStatement("SELECT idEmpresa FROM " + ACCESOS + " WHERE usuario = ?")) {
                stmt.setString(1, usuario);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        ids.add(rs.getString(1));
                    }
                }
            }
        } catch (SQLException | IllegalStateException ex) {
            LOGGER.log(Level.SEVERE, "No se pudieron consultar los accesos del usuario " + usuario, ex);
        }
        return ids;
    }

    public Set<String> usuariosConAcceso(String idEmpresa) {
        Set<String> usuarios = new LinkedHashSet<>();
        try {
            asegurarEsquema();
            try (Connection conexion = MySql_connection.crearConexionPrincipal();
                    PreparedStatement stmt = conexion.prepareStatement("SELECT usuario FROM " + ACCESOS + " WHERE idEmpresa = ?")) {
                stmt.setString(1, idEmpresa);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        usuarios.add(rs.getString(1));
                    }
                }
            }
        } catch (SQLException | IllegalStateException ex) {
            LOGGER.log(Level.SEVERE, "No se pudieron consultar los usuarios de la empresa " + idEmpresa, ex);
        }
        return usuarios;
    }

    public boolean guardarAccesos(String idEmpresa, Set<String> usuarios) {
        try {
            asegurarEsquema();
            try (Connection conexion = MySql_connection.crearConexionPrincipal()) {
                conexion.setAutoCommit(false);
                try (PreparedStatement borrar = conexion.prepareStatement("DELETE FROM " + ACCESOS + " WHERE idEmpresa = ?");
                        PreparedStatement insertar = conexion.prepareStatement("INSERT INTO " + ACCESOS + " (usuario, idEmpresa) VALUES (?, ?)")) {
                    borrar.setString(1, idEmpresa);
                    borrar.executeUpdate();
                    for (String usuario : usuarios) {
                        insertar.setString(1, usuario);
                        insertar.setString(2, idEmpresa);
                        insertar.addBatch();
                    }
                    insertar.executeBatch();
                    conexion.commit();
                    return true;
                } catch (SQLException ex) {
                    conexion.rollback();
                    throw ex;
                }
            }
        } catch (SQLException | IllegalStateException ex) {
            LOGGER.log(Level.SEVERE, "No se pudieron guardar los accesos de la empresa " + idEmpresa, ex);
            return false;
        }
    }

    public int contarActivas() {
        return listar(true).size();
    }

    public String siguienteId() throws SQLException {
        asegurarEsquema();
        Set<String> existentes = new LinkedHashSet<>();
        try (Connection conexion = MySql_connection.crearConexionPrincipal();
                PreparedStatement stmt = conexion.prepareStatement("SELECT UPPER(id) FROM " + EMPRESAS);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                existentes.add(rs.getString(1));
            }
        }
        for (int i = 1; i < 1000; i++) {
            String id = String.format("E%03d", i);
            if (!existentes.contains(id)) {
                return id;
            }
        }
        throw new SQLException("No hay identificadores disponibles para nuevas empresas");
    }

    public void registrar(Empresa empresa, String usuarioCreacion) throws SQLException {
        asegurarEsquema();
        try (Connection conexion = MySql_connection.crearConexionPrincipal()) {
            conexion.setAutoCommit(false);
            try (PreparedStatement insertar = conexion.prepareStatement("INSERT INTO " + EMPRESAS
                    + " (id, descripcion, base, nit, tipoIdentificacion, estado, fechaCreacion, usuarioCreacion) VALUES (?, ?, ?, ?, ?, 1, NOW(), ?)");
                    PreparedStatement acceso = conexion.prepareStatement("INSERT IGNORE INTO " + ACCESOS + " (usuario, idEmpresa) VALUES (?, ?)")) {
                insertar.setString(1, empresa.getId());
                insertar.setString(2, empresa.getNombre());
                insertar.setString(3, empresa.getBaseDatos());
                insertar.setString(4, empresa.getNit());
                insertar.setString(5, empresa.getTipoIdentificacion());
                insertar.setString(6, usuarioCreacion);
                insertar.executeUpdate();
                if (usuarioCreacion != null && !usuarioCreacion.isEmpty()) {
                    acceso.setString(1, usuarioCreacion);
                    acceso.setString(2, empresa.getId());
                    acceso.executeUpdate();
                }
                conexion.commit();
            } catch (SQLException ex) {
                conexion.rollback();
                throw ex;
            }
        }
    }

    public boolean actualizar(Empresa empresa) {
        try {
            asegurarEsquema();
            String base = CredencialesBaseDatos.validarNombreBaseDatos(empresa.getBaseDatos());
            try (Connection conexion = MySql_connection.crearConexionPrincipal()) {
                conexion.setAutoCommit(false);
                try (PreparedStatement registro = conexion.prepareStatement("UPDATE " + EMPRESAS
                        + " SET descripcion = ?, nit = ?, tipoIdentificacion = ? WHERE id = ?");
                        PreparedStatement maestra = conexion.prepareStatement("UPDATE `" + base + "`.`bdMaestra` SET nombreEmpresa = ?, nitEmpresa = ?")) {
                    registro.setString(1, empresa.getNombre());
                    registro.setString(2, empresa.getNit());
                    registro.setString(3, empresa.getTipoIdentificacion());
                    registro.setString(4, empresa.getId());
                    registro.executeUpdate();
                    maestra.setString(1, empresa.getNombre());
                    maestra.setString(2, empresa.getNit());
                    maestra.executeUpdate();
                    conexion.commit();
                    return true;
                } catch (SQLException ex) {
                    conexion.rollback();
                    throw ex;
                }
            }
        } catch (SQLException | IllegalStateException | IllegalArgumentException ex) {
            LOGGER.log(Level.SEVERE, "No se pudo actualizar la empresa " + empresa.getId(), ex);
            return false;
        }
    }

    public boolean cambiarEstado(String idEmpresa, boolean activa) {
        try {
            asegurarEsquema();
            try (Connection conexion = MySql_connection.crearConexionPrincipal();
                    PreparedStatement stmt = conexion.prepareStatement("UPDATE " + EMPRESAS + " SET estado = ? WHERE id = ?")) {
                stmt.setBoolean(1, activa);
                stmt.setString(2, idEmpresa);
                return stmt.executeUpdate() > 0;
            }
        } catch (SQLException | IllegalStateException ex) {
            LOGGER.log(Level.SEVERE, "No se pudo cambiar el estado de la empresa " + idEmpresa, ex);
            return false;
        }
    }

    private Empresa leer(ResultSet rs) throws SQLException {
        Empresa empresa = new Empresa();
        empresa.setId(rs.getString("id"));
        empresa.setNombre(rs.getString("descripcion"));
        empresa.setBaseDatos(rs.getString("base"));
        empresa.setNit(rs.getString("nit"));
        empresa.setTipoIdentificacion(rs.getString("tipoIdentificacion"));
        empresa.setActiva(rs.getBoolean("estado"));
        return empresa;
    }
}
