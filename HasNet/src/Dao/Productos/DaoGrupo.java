package Dao.Productos;

import Estrategia.AbstractDao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DaoGrupo extends AbstractDao {

    public List<String> obtenerNombresGrupos() throws SQLException {
        return consultarNombres("SELECT DISTINCT nombre FROM bdGrupo WHERE nombre IS NOT NULL AND nombre <> '' ORDER BY nombre");
    }

    public List<String> obtenerNombresSubgrupos() throws SQLException {
        return consultarNombres("SELECT DISTINCT nombre FROM bdSubGrupo WHERE nombre IS NOT NULL AND nombre <> '' ORDER BY nombre");
    }

    public Map<String, String> obtenerCodigosGruposPorNombre() throws SQLException {
        Map<String, String> codigos = new HashMap<>();

        try (PreparedStatement ps = getConnection().prepareStatement("SELECT codigo, nombre FROM bdGrupo WHERE nombre IS NOT NULL AND nombre <> ''");
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String nombre = rs.getString("nombre").trim().toLowerCase();
                if (!codigos.containsKey(nombre)) {
                    codigos.put(nombre, rs.getString("codigo"));
                }
            }
        }

        return codigos;
    }

    public List<String[]> obtenerSubgrupos() throws SQLException {
        List<String[]> subgrupos = new ArrayList<>();

        try (PreparedStatement ps = getConnection().prepareStatement("SELECT codigo, nombre, grupo FROM bdSubGrupo WHERE nombre IS NOT NULL AND nombre <> ''");
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                subgrupos.add(new String[]{rs.getString("codigo"), rs.getString("nombre").trim().toLowerCase(), rs.getString("grupo")});
            }
        }

        return subgrupos;
    }

    private List<String> consultarNombres(String sql) throws SQLException {
        List<String> nombres = new ArrayList<>();

        try (PreparedStatement ps = getConnection().prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                nombres.add(rs.getString("nombre"));
            }
        }

        return nombres;
    }
}
