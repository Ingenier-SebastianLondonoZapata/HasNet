package Dao.SoportesMercantiles;

import Estrategia.AbstractDao;
import Modelo.SoportesMercantiles.FiltroSoportesMercantiles;
import Modelo.SoportesMercantiles.RegistroSoporteMercantil;
import Modelo.SoportesMercantiles.ResultadoEventoMercantil;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DaoSoportesMercantiles extends AbstractDao {

    private static final String TABLA = "bdSoportesMercantiles";
    private static final int LIMITE_CONSULTA = 2000;

    public void guardar(RegistroSoporteMercantil registro) throws SQLException {
        String sql = "INSERT INTO " + TABLA + " (cufe, prefijo, folio, nitEmisor, nombreEmisor, fechaEmision, total, evento, estado, descripcion, "
                + "errores, uuidEvento, fechaEvento, origen, archivo, usuario, fechaRegistro) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            int i = 1;
            ps.setString(i++, registro.getCufe());
            ps.setString(i++, registro.getPrefijo());
            ps.setString(i++, registro.getFolio());
            ps.setString(i++, registro.getNitEmisor());
            ps.setString(i++, registro.getNombreEmisor());
            if (registro.getFechaEmision() == null) {
                ps.setNull(i++, Types.DATE);
            } else {
                ps.setDate(i++, Date.valueOf(registro.getFechaEmision()));
            }
            if (registro.getTotal() == null) {
                ps.setNull(i++, Types.DECIMAL);
            } else {
                ps.setBigDecimal(i++, registro.getTotal());
            }
            ps.setString(i++, registro.getEvento());
            ps.setString(i++, registro.getEstado());
            ps.setString(i++, registro.getDescripcion());
            ps.setString(i++, registro.getErrores());
            ps.setString(i++, registro.getUuidEvento());
            ps.setString(i++, registro.getFechaEvento());
            ps.setString(i++, registro.getOrigen());
            ps.setString(i++, registro.getArchivo());
            ps.setString(i++, registro.getUsuario());
            ps.setTimestamp(i, Timestamp.valueOf(registro.getFechaRegistro()));
            ps.executeUpdate();
        }
    }

    public Set<String> eventosCompletados(String cufe) throws SQLException {
        Set<String> eventos = new HashSet<>();
        if (cufe == null || cufe.trim().isEmpty()) {
            return eventos;
        }

        String sql = "SELECT DISTINCT evento FROM " + TABLA + " WHERE cufe = ? AND estado IN (?, ?)";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, cufe.trim());
            ps.setString(2, ResultadoEventoMercantil.EXITOSO);
            ps.setString(3, ResultadoEventoMercantil.YA_REGISTRADO);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    eventos.add(rs.getString("evento"));
                }
            }
        }
        return eventos;
    }

    public List<String> nombresEmisores() throws SQLException {
        List<String> nombres = new ArrayList<>();
        String sql = "SELECT DISTINCT nombreEmisor FROM " + TABLA + " WHERE nombreEmisor IS NOT NULL AND nombreEmisor <> '' ORDER BY nombreEmisor";
        try (PreparedStatement ps = getConnection().prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                nombres.add(rs.getString("nombreEmisor"));
            }
        }
        return nombres;
    }

    public List<RegistroSoporteMercantil> consultar(FiltroSoportesMercantiles filtro) throws SQLException {
        StringBuilder sql = new StringBuilder("SELECT * FROM " + TABLA + " WHERE 1 = 1");
        List<Object> parametros = new ArrayList<>();

        if (filtro.getFechaInicio() != null) {
            sql.append(" AND fechaRegistro >= ?");
            parametros.add(Timestamp.valueOf(filtro.getFechaInicio().atStartOfDay()));
        }
        if (filtro.getFechaFin() != null) {
            sql.append(" AND fechaRegistro < ?");
            parametros.add(Timestamp.valueOf(filtro.getFechaFin().plusDays(1).atStartOfDay()));
        }
        if (!filtro.getPrefijo().isEmpty()) {
            sql.append(" AND prefijo = ?");
            parametros.add(filtro.getPrefijo());
        }
        if (!filtro.getFolio().isEmpty()) {
            sql.append(" AND folio = ?");
            parametros.add(filtro.getFolio());
        }
        if (!filtro.getCufe().isEmpty()) {
            sql.append(" AND cufe = ?");
            parametros.add(filtro.getCufe());
        }
        if (!filtro.getNombreEmisor().isEmpty()) {
            sql.append(" AND nombreEmisor = ?");
            parametros.add(filtro.getNombreEmisor());
        }
        if (!filtro.getNitEmisor().isEmpty()) {
            sql.append(" AND nitEmisor LIKE ?");
            parametros.add(filtro.getNitEmisor() + "%");
        }
        sql.append(" ORDER BY fechaRegistro DESC, idSoporte DESC LIMIT ").append(LIMITE_CONSULTA);

        List<RegistroSoporteMercantil> registros = new ArrayList<>();
        try (PreparedStatement ps = getConnection().prepareStatement(sql.toString())) {
            for (int i = 0; i < parametros.size(); i++) {
                ps.setObject(i + 1, parametros.get(i));
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    registros.add(leer(rs));
                }
            }
        }
        return registros;
    }

    private RegistroSoporteMercantil leer(ResultSet rs) throws SQLException {
        RegistroSoporteMercantil registro = new RegistroSoporteMercantil();
        registro.setId(rs.getInt("idSoporte"));
        registro.setCufe(texto(rs, "cufe"));
        registro.setPrefijo(texto(rs, "prefijo"));
        registro.setFolio(texto(rs, "folio"));
        registro.setNitEmisor(texto(rs, "nitEmisor"));
        registro.setNombreEmisor(texto(rs, "nombreEmisor"));
        Date fechaEmision = rs.getDate("fechaEmision");
        registro.setFechaEmision(fechaEmision == null ? null : fechaEmision.toLocalDate());
        registro.setTotal(rs.getBigDecimal("total"));
        registro.setEvento(texto(rs, "evento"));
        registro.setEstado(texto(rs, "estado"));
        registro.setDescripcion(texto(rs, "descripcion"));
        registro.setErrores(texto(rs, "errores"));
        registro.setUuidEvento(texto(rs, "uuidEvento"));
        registro.setFechaEvento(texto(rs, "fechaEvento"));
        registro.setOrigen(texto(rs, "origen"));
        registro.setArchivo(texto(rs, "archivo"));
        registro.setUsuario(texto(rs, "usuario"));
        Timestamp fechaRegistro = rs.getTimestamp("fechaRegistro");
        registro.setFechaRegistro(fechaRegistro == null ? null : fechaRegistro.toLocalDateTime());
        return registro;
    }

    private static String texto(ResultSet rs, String columna) throws SQLException {
        String valor = rs.getString(columna);
        return valor == null ? "" : valor;
    }
}
