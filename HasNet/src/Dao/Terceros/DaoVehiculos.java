package Dao.Terceros;

import Estrategia.AbstractDao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoVehiculos extends AbstractDao {

    private static final Logger LOGGER = Logger.getLogger(DaoVehiculos.class.getName());

    public List<String> listarTiposVehiculo() {
        return listarNombres("bdTipoVehiculo");
    }

    public List<String> listarMarcas() {
        return listarNombres("bdMarca");
    }

    public List<String> listarColores() {
        return listarNombres("bdColores");
    }

    public boolean agregarTipoVehiculo(String nombre) {
        return agregarNombre("bdTipoVehiculo", nombre);
    }

    public boolean agregarMarca(String nombre) {
        return agregarNombre("bdMarca", nombre);
    }

    public boolean agregarColor(String nombre) {
        return agregarNombre("bdColores", nombre);
    }

    public boolean existePlacaDeOtroContacto(String placa, String idSistema) {
        String sql = "SELECT 1 FROM bdPlacas WHERE placa = ? AND (usuario IS NULL OR usuario <> ?) LIMIT 1";

        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setString(1, placa);
            stmt.setString(2, idSistema == null ? "" : idSistema);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al verificar la placa " + placa, e);
            return false;
        }
    }

    private List<String> listarNombres(String tabla) {
        List<String> nombres = new ArrayList<>();
        String sql = "SELECT nombre FROM " + tabla + " WHERE nombre IS NOT NULL AND nombre <> '' ORDER BY nombre";

        try (PreparedStatement stmt = getConnection().prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                nombres.add(rs.getString(1));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al consultar los registros de " + tabla, e);
        }
        return nombres;
    }

    private boolean agregarNombre(String tabla, String nombre) {
        try (PreparedStatement stmt = getConnection().prepareStatement("INSERT INTO " + tabla + " (nombre) VALUES (?)")) {
            stmt.setString(1, nombre);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al agregar " + nombre + " en " + tabla, e);
            return false;
        }
    }
}
