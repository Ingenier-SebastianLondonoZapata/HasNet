package Dao.Terceros;

import Modelo.Terceros.ModeloContacto;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public final class MapeadorContacto {

    private MapeadorContacto() {
    }

    public static final String[] COLUMNAS = {
        "Id", "nombre", "telefono", "celular", "direccion", "ciudad", "email", "departamento", "fecha", "usuario", "tipo",
        "completa", "pNombre", "sNombre", "pApellido", "sApellido", "actividad", "naturaleza", "rut", "activo", "plazo",
        "placas", "pais", "sexo", "estado", "ocupacion", "regimen", "afiliado", "tipoTrabajador", "zona", "nacimiento",
        "sangre", "eps", "terminal", "referido", "vendedor", "lista", "nota", "tipo2", "rango", "categoria", "convenio",
        "convenioActual", "religion", "nombreMadre", "nombrePadre", "nombreResponsable", "parentescoResponsable",
        "telefonoResponsable", "lugarNacimiento", "barrio", "nombreContacto", "cargo", "telefonoContacto", "web", "rnt",
        "tipoProveedor", "ingreso", "retiro", "agendar", "usuarioAsociado", "arl", "ccf", "tipoTercero", "codigoUsuario",
        "responsableIva", "cupo", "salario"
    };

    public static ModeloContacto mapear(ResultSet rs) throws SQLException {
        ModeloContacto c = new ModeloContacto();

        c.setIdSistema(rs.getString("idSistema"));
        c.setId(rs.getString("Id"));
        c.setNombre(rs.getString("nombre"));
        c.setTelefono(rs.getString("telefono"));
        c.setCelular(rs.getString("celular"));
        c.setDireccion(rs.getString("direccion"));
        c.setCiudad(rs.getString("ciudad"));
        c.setEmail(rs.getString("email"));
        c.setDepartamento(rs.getString("departamento"));
        c.setFecha(rs.getString("fecha"));
        c.setUsuario(rs.getString("usuario"));
        c.setTipo(rs.getString("tipo"));
        c.setCompleta(rs.getString("completa"));
        c.setpNombre(rs.getString("pNombre"));
        c.setsNombre(rs.getString("sNombre"));
        c.setpApellido(rs.getString("pApellido"));
        c.setsApellido(rs.getString("sApellido"));
        c.setActividad(rs.getString("actividad"));
        c.setNaturaleza(rs.getString("naturaleza"));
        c.setRut(rs.getString("rut"));
        c.setActivo(rs.getBoolean("activo"));
        c.setPlazo(rs.getString("plazo"));
        c.setPlacas(rs.getString("placas"));
        c.setPais(rs.getString("pais"));
        c.setSexo(rs.getString("sexo"));
        c.setEstado(rs.getString("estado"));
        c.setOcupacion(rs.getString("ocupacion"));
        c.setRegimen(rs.getString("regimen"));
        c.setAfiliado(rs.getString("afiliado"));
        c.setTipoTrabajador(rs.getString("tipoTrabajador"));
        c.setZona(rs.getString("zona"));
        c.setNacimiento(rs.getString("nacimiento"));
        c.setSangre(rs.getString("sangre"));
        c.setEps(rs.getString("eps"));
        c.setTerminal(rs.getString("terminal"));
        c.setReferido(rs.getString("referido"));
        c.setVendedor(rs.getString("vendedor"));
        c.setLista(rs.getString("lista"));
        c.setNota(rs.getString("nota"));
        c.setTipo2(rs.getString("tipo2"));
        c.setRango(rs.getString("rango"));
        c.setCodigoPostal(rs.getString("categoria"));
        c.setConvenio(rs.getString("convenio"));
        c.setConvenioActual(rs.getString("convenioActual"));
        c.setReligion(rs.getString("religion"));
        c.setNombreMadre(rs.getString("nombreMadre"));
        c.setNombrePadre(rs.getString("nombrePadre"));
        c.setNombreResponsable(rs.getString("nombreResponsable"));
        c.setParentescoResponsable(rs.getString("parentescoResponsable"));
        c.setTelefonoResponsable(rs.getString("telefonoResponsable"));
        c.setLugarNacimiento(rs.getString("lugarNacimiento"));
        c.setBarrio(rs.getString("barrio"));
        c.setNombreContacto(rs.getString("nombreContacto"));
        c.setCargo(rs.getString("cargo"));
        c.setTelefonoContacto(rs.getString("telefonoContacto"));
        c.setWeb(rs.getString("web"));
        c.setRnt(rs.getString("rnt"));
        c.setTipoProveedor(rs.getString("tipoProveedor"));
        c.setIngreso(rs.getString("ingreso"));
        c.setRetiro(rs.getString("retiro"));
        c.setAgendar(rs.getBoolean("agendar"));
        c.setUsuarioAsociado(rs.getString("usuarioAsociado"));
        c.setArl(rs.getString("arl"));
        c.setCcf(rs.getString("ccf"));
        c.setTipoTercero(rs.getString("tipoTercero"));
        c.setCodigoUsuario(rs.getString("codigoUsuario"));
        c.setResponsableIva(rs.getBoolean("responsableIva"));
        c.setCupo(rs.getString("cupo"));
        c.setSalario(rs.getString("salario"));

        return c;
    }

    public static int enlazar(PreparedStatement stmt, ModeloContacto c, int i) throws SQLException {
        Object[] valores = {
            c.getId(), c.getNombre(), c.getTelefono(), c.getCelular(), c.getDireccion(), c.getCiudad(), c.getEmail(), c.getDepartamento(),
            c.getFecha(), c.getUsuario(), c.getTipo(), c.getCompleta(), c.getpNombre(), c.getsNombre(), c.getpApellido(), c.getsApellido(),
            c.getActividad(), c.getNaturaleza(), c.getRut(), c.isActivo(), c.getPlazo(), c.getPlacas(), c.getPais(), c.getSexo(),
            c.getEstado(), c.getOcupacion(), c.getRegimen(), c.getAfiliado(), c.getTipoTrabajador(), c.getZona(), c.getNacimiento(),
            c.getSangre(), c.getEps(), c.getTerminal(), c.getReferido(), c.getVendedor(), c.getLista(), c.getNota(), c.getTipo2(),
            c.getRango(), c.getCodigoPostal(), c.getConvenio(), c.getConvenioActual(), c.getReligion(), c.getNombreMadre(),
            c.getNombrePadre(), c.getNombreResponsable(), c.getParentescoResponsable(), c.getTelefonoResponsable(),
            c.getLugarNacimiento(), c.getBarrio(), c.getNombreContacto(), c.getCargo(), c.getTelefonoContacto(), c.getWeb(), c.getRnt(),
            c.getTipoProveedor(), c.getIngreso(), c.getRetiro(), c.isAgendar(), c.getUsuarioAsociado(), c.getArl(), c.getCcf(),
            c.getTipoTercero(), c.getCodigoUsuario(), c.isResponsableIva(), aMonto(c.getCupo()), aMonto(c.getSalario())
        };

        for (Object valor : valores) {
            if (valor == null) {
                stmt.setNull(i++, Types.NULL);
            } else if (valor instanceof Boolean) {
                stmt.setBoolean(i++, (Boolean) valor);
            } else if (valor instanceof BigDecimal) {
                stmt.setBigDecimal(i++, (BigDecimal) valor);
            } else {
                stmt.setString(i++, valor.toString());
            }
        }
        return i;
    }

    private static BigDecimal aMonto(String valor) {
        if (valor == null || valor.trim().isEmpty() || "null".equals(valor)) {
            return BigDecimal.ZERO;
        }
        return new BigDecimal(valor.trim());
    }
}
