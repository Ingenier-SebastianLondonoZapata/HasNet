package Dao.Terceros;

import Estrategia.AbstractDao;
import Modelo.Terceros.ModeloContacto;
import Modelo.Terceros.ModeloDatosVehiculo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoContactos extends AbstractDao {

    private static final Logger LOGGER = Logger.getLogger(DaoContactos.class.getName());

    private static final String CONSECUTIVO_TERCERO = "TERCERO";
    private static final String PREFIJO_ID_SISTEMA = "TERC-";

    private static final String SQL_CONSULTA = "SELECT idSistema, " + unir(MapeadorContacto.COLUMNAS, ", ")
            + " FROM bdTerceros WHERE Id = ? OR idSistema = ?";

    private static final String SQL_INSERTAR = "INSERT INTO bdTerceros (idSistema, " + unir(MapeadorContacto.COLUMNAS, ", ") + ") VALUES ("
            + signosInterrogacion(MapeadorContacto.COLUMNAS.length + 1) + ")";

    private static final String SQL_ACTUALIZAR = "UPDATE bdTerceros SET " + unir(MapeadorContacto.COLUMNAS, "=?, ") + "=? WHERE idSistema = ?";

    public ModeloDatosVehiculo obtenerDatosVehiculo(String ordenServicio) {
        String sql = "SELECT placa, tipo, modelo, numeroChasis, fechaCompra, marca, km, numeroMotor, color FROM bdOServicio WHERE id = ?";

        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setString(1, ordenServicio);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    ModeloDatosVehiculo datosVehiculo = new ModeloDatosVehiculo();
                    datosVehiculo.setPlaca(rs.getString("placa"));
                    datosVehiculo.setTipoVehiculo(rs.getString("tipo"));
                    datosVehiculo.setModelo(rs.getString("modelo"));
                    datosVehiculo.setNumeroChasis(rs.getString("numeroChasis"));
                    datosVehiculo.setFechaCompra(rs.getString("fechaCompra"));
                    datosVehiculo.setMarca(rs.getString("marca"));
                    datosVehiculo.setNumeroMotor(rs.getString("numeroMotor"));
                    datosVehiculo.setColor(rs.getString("color"));
                    return datosVehiculo;
                } else {
                    System.out.println("No se encontró datos del vehículo con OSERV: " + ordenServicio);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener los datos del vehículo: " + e.getMessage());
        }

        return null;
    }

    public ModeloContacto consultarPorIdentificacion(String identificacion) {
        try (PreparedStatement stmt = getConnection().prepareStatement(SQL_CONSULTA)) {
            stmt.setString(1, identificacion);
            stmt.setString(2, identificacion);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() ? MapeadorContacto.mapear(rs) : null;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al consultar el contacto " + identificacion, e);
            return null;
        }
    }

    public String obtenerSiguienteIdSistema() {
        try {
            return formatearIdSistema(leerConsecutivo(getConnection(), false));
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al consultar el consecutivo de terceros", e);
            return "";
        }
    }

    public boolean guardar(ModeloContacto contacto, List<ModeloDatosVehiculo> vehiculos) {
        Connection conexion = getConnection();
        boolean autoCommitPrevio = true;

        try {
            autoCommitPrevio = conexion.getAutoCommit();
            conexion.setAutoCommit(false);

            int consecutivo = leerConsecutivo(conexion, true);
            contacto.setIdSistema(formatearIdSistema(consecutivo));

            try (PreparedStatement stmt = conexion.prepareStatement(SQL_INSERTAR)) {
                stmt.setString(1, contacto.getIdSistema());
                MapeadorContacto.enlazar(stmt, contacto, 2);
                stmt.executeUpdate();
            }

            insertarVehiculos(conexion, contacto.getIdSistema(), vehiculos);
            actualizarConsecutivo(conexion, consecutivo + 1);

            conexion.commit();
            return true;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al guardar el contacto", e);
            revertir(conexion);
            return false;
        } finally {
            restaurarAutoCommit(conexion, autoCommitPrevio);
        }
    }

    public boolean modificar(ModeloContacto contacto, List<ModeloDatosVehiculo> vehiculos) {
        Connection conexion = getConnection();
        boolean autoCommitPrevio = true;

        try {
            autoCommitPrevio = conexion.getAutoCommit();
            conexion.setAutoCommit(false);

            try (PreparedStatement stmt = conexion.prepareStatement(SQL_ACTUALIZAR)) {
                int siguiente = MapeadorContacto.enlazar(stmt, contacto, 1);
                stmt.setString(siguiente, contacto.getIdSistema());
                stmt.executeUpdate();
            }

            try (PreparedStatement stmt = conexion.prepareStatement("DELETE FROM bdPlacas WHERE usuario = ?")) {
                stmt.setString(1, contacto.getIdSistema());
                stmt.executeUpdate();
            }
            insertarVehiculos(conexion, contacto.getIdSistema(), vehiculos);

            conexion.commit();
            return true;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al modificar el contacto " + contacto.getIdSistema(), e);
            revertir(conexion);
            return false;
        } finally {
            restaurarAutoCommit(conexion, autoCommitPrevio);
        }
    }

    public boolean cambiarEstado(String idSistema, boolean inactivo) {
        try (PreparedStatement stmt = getConnection().prepareStatement("UPDATE bdTerceros SET activo = ? WHERE idSistema = ?")) {
            stmt.setBoolean(1, inactivo);
            stmt.setString(2, idSistema);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al cambiar el estado del contacto " + idSistema, e);
            return false;
        }
    }

    public List<ModeloDatosVehiculo> consultarVehiculos(String idSistema) {
        List<ModeloDatosVehiculo> vehiculos = new ArrayList<>();
        String sql = "SELECT placa, tipo, modelo, marca, color, chasis, motor, fechaCompra FROM bdPlacas WHERE usuario = ?";

        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setString(1, idSistema);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    ModeloDatosVehiculo vehiculo = new ModeloDatosVehiculo();
                    vehiculo.setPlaca(rs.getString("placa"));
                    vehiculo.setTipoVehiculo(rs.getString("tipo"));
                    vehiculo.setModelo(rs.getString("modelo"));
                    vehiculo.setMarca(rs.getString("marca"));
                    vehiculo.setColor(rs.getString("color"));
                    vehiculo.setNumeroChasis(rs.getString("chasis"));
                    vehiculo.setNumeroMotor(rs.getString("motor"));
                    vehiculo.setFechaCompra(rs.getString("fechaCompra"));
                    vehiculos.add(vehiculo);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al consultar los vehículos de " + idSistema, e);
        }
        return vehiculos;
    }

    public String obtenerIdSistemaPorNombre(String nombre) {
        return consultarUnTexto("SELECT idSistema FROM bdTerceros WHERE nombre = ? LIMIT 1", nombre);
    }

    public String obtenerNombrePorIdSistema(String idSistema) {
        return consultarUnTexto("SELECT nombre FROM bdTerceros WHERE idSistema = ? LIMIT 1", idSistema);
    }

    private String consultarUnTexto(String sql, String parametro) {
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setString(1, parametro);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next() && rs.getString(1) != null) {
                    return rs.getString(1);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al ejecutar la consulta: " + sql, e);
        }
        return "";
    }

    private int leerConsecutivo(Connection conexion, boolean bloquear) throws SQLException {
        String sql = "SELECT numero FROM bdConsecutivos WHERE Id = ?" + (bloquear ? " FOR UPDATE" : "");

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, CONSECUTIVO_TERCERO);
            try (ResultSet rs = stmt.executeQuery()) {
                if (!rs.next()) {
                    throw new SQLException("No existe el consecutivo " + CONSECUTIVO_TERCERO);
                }
                return Integer.parseInt(rs.getString(1).trim());
            }
        }
    }

    private void actualizarConsecutivo(Connection conexion, int numero) throws SQLException {
        try (PreparedStatement stmt = conexion.prepareStatement("UPDATE bdConsecutivos SET numero = ? WHERE Id = ?")) {
            stmt.setString(1, String.valueOf(numero));
            stmt.setString(2, CONSECUTIVO_TERCERO);
            stmt.executeUpdate();
        }
    }

    private String formatearIdSistema(int consecutivo) {
        return String.format("%s%08d", PREFIJO_ID_SISTEMA, consecutivo);
    }

    private void insertarVehiculos(Connection conexion, String idSistema, List<ModeloDatosVehiculo> vehiculos) throws SQLException {
        if (vehiculos == null || vehiculos.isEmpty()) {
            return;
        }

        String sql = "INSERT INTO bdPlacas (usuario, placa, tipo, modelo, marca, color, chasis, motor, fechaCompra) VALUES (?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            for (ModeloDatosVehiculo vehiculo : vehiculos) {
                stmt.setString(1, idSistema);
                stmt.setString(2, vehiculo.getPlaca());
                stmt.setString(3, vehiculo.getTipoVehiculo());
                stmt.setString(4, vehiculo.getModelo());
                stmt.setString(5, vehiculo.getMarca());
                stmt.setString(6, vehiculo.getColor());
                stmt.setString(7, vehiculo.getNumeroChasis());
                stmt.setString(8, vehiculo.getNumeroMotor());
                stmt.setString(9, vehiculo.getFechaCompra());
                stmt.addBatch();
            }
            stmt.executeBatch();
        }
    }

    private void revertir(Connection conexion) {
        try {
            conexion.rollback();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "No se pudo revertir la transacción de contactos", e);
        }
    }

    private void restaurarAutoCommit(Connection conexion, boolean autoCommit) {
        try {
            conexion.setAutoCommit(autoCommit);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "No se pudo restaurar el autocommit", e);
        }
    }

    private static String unir(String[] valores, String separador) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < valores.length; i++) {
            if (i > 0) {
                sb.append(separador);
            }
            sb.append(valores[i]);
        }
        return sb.toString();
    }

    private static String signosInterrogacion(int cantidad) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cantidad; i++) {
            sb.append(i == 0 ? "?" : ",?");
        }
        return sb.toString();
    }
}
