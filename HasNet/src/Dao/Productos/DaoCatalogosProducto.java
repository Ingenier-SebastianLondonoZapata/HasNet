package Dao.Productos;

import Modelo.Productos.ModeloOpcionCatalogo;
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

public class DaoCatalogosProducto {

    private static final Logger LOGGER = Logger.getLogger(DaoCatalogosProducto.class.getName());

    public List<ModeloOpcionCatalogo> listarGrupos() {
        return listarOpciones("SELECT codigo, nombre FROM bdGrupo WHERE activo = false ORDER BY nombre", null);
    }

    public List<ModeloOpcionCatalogo> listarSubgrupos(String grupo) {
        if (grupo == null || grupo.trim().isEmpty()) {
            return new ArrayList<>();
        }
        return listarOpciones("SELECT codigo, nombre FROM bdSubGrupo WHERE grupo = ? ORDER BY nombre", grupo.trim());
    }

    public List<String> listarMedidas() {
        List<String> medidas = new ArrayList<>();
        try (PreparedStatement stmt = conexion().prepareStatement("SELECT nombre FROM bdMedida ORDER BY nombre");
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                if (nombre != null && !nombre.trim().isEmpty()) {
                    medidas.add(nombre.trim());
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al listar las unidades de medida", e);
        }
        return medidas;
    }

    private List<ModeloOpcionCatalogo> listarOpciones(String sql, String parametro) {
        List<ModeloOpcionCatalogo> opciones = new ArrayList<>();
        try (PreparedStatement stmt = conexion().prepareStatement(sql)) {
            if (parametro != null) {
                stmt.setString(1, parametro);
            }
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    opciones.add(new ModeloOpcionCatalogo(rs.getString("codigo"), rs.getString("nombre")));
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al listar el catálogo de productos", e);
        }
        return opciones;
    }

    private Connection conexion() {
        return MySql_connection.getInstancia(Constantes.BASE_DATOS_PRINCIPAL).getConnection();
    }
}
