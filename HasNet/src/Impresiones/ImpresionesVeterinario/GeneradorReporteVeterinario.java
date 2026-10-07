package Impresiones.ImpresionesVeterinario;

import Impresiones.GeneradorReporteBase;
import Impresiones.IniciarReporte;
import Vista.BarraProceso.vistaBarraProceso;
import clases.Instancias;
import clases.metodosGenerales;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

public class GeneradorReporteVeterinario extends GeneradorReporteBase {

    public GeneradorReporteVeterinario(Instancias instancias) {
        super(instancias);
    }

    public void ver_Formato(String nombre, String url) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("nombre", nombre);
        parametros.put("urlImagen", logo());
        ejecutarSinBaseDatos(url, parametros);
    }

    public void ver_ImpresionVacunas(String sql) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("cliente", sql);
        parametros.put("urlImagen", logo());
        ejecutar("vacunasMascotas1", parametros);
    }

    public void verNegativa(String nombre, String sexo, String raza, String edad, String codigo,
            String reproductivo, String cedula, String nombreDueno, String telefono, String direccion, String historia) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("info", instancias.getInformacionEmpresa());
        parametros.put("nombre", nombre);
        parametros.put("sexo", sexo);
        parametros.put("raza", raza);
        parametros.put("edad", edad);
        parametros.put("codigo", codigo);
        parametros.put("reproductivo", reproductivo);
        parametros.put("cedula", cedula);
        parametros.put("nombreDueño", nombreDueno);
        parametros.put("telefono", telefono);
        parametros.put("direccion", direccion);
        parametros.put("historia", historia);
        parametros.put("fecha", metodosGenerales.fecha());
        parametros.put("urlImagen", logo());
        parametros.put("firma", firma());
        parametros.put("usuario", instancias.getUsuarioLog().getNombre());
        ejecutarSinBaseDatos("formatoNegativa", parametros);
    }

    public void verAyudaDiagnosticaVeterinaria(String id, String nombre, String sexo, String raza,
            String edad, String codigo, String reproductivo, String cedula, String nombreDueno,
            String telefono, String direccion, String historia) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("info", instancias.getInformacionEmpresa());
        parametros.put("id", id);
        parametros.put("nombre", nombre);
        parametros.put("sexo", sexo);
        parametros.put("raza", raza);
        parametros.put("edad", edad);
        parametros.put("codigo", codigo);
        parametros.put("reproductivo", reproductivo);
        parametros.put("cedula", cedula);
        parametros.put("nombreDueño", nombreDueno);
        parametros.put("telefono", telefono);
        parametros.put("direccion", direccion);
        parametros.put("historia", historia);
        parametros.put("fecha", metodosGenerales.fecha());
        parametros.put("urlImagen", logo());
        parametros.put("firma", firma());
        parametros.put("usuario", instancias.getUsuarioLog().getNombre());
        ejecutar("ayudaDiagnosticoVeterinaria", parametros);
    }

    public void verFormulaQuirurgica(String cedula, String nombre, String historia, String raza, String consecutivo,
            String sexo, String edad, String tipo, String descripcion, String fecha, String tipo1) {
        ejecutar("formulaQuirurgica" + tipo1,
                parametrosHojaClinica(cedula, nombre, historia, raza, consecutivo, sexo, edad, tipo, descripcion, fecha));
    }

    public void verRemisionVeterinaria(String dato, String edad) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("id", dato);
        parametros.put("edad", edad);
        parametros.put("firma", firma());
        parametros.put("usuario", instancias.getUsuario());
        parametros.put("urlImagen", logo());
        ejecutar("remisionVeterinaria", parametros);
    }

    public void verFormulaMedicaVeterinaria(String id, String nombre,
            String sexo, String raza, String edad, String codigo,
            String reproductivo, String cedula, String nombreDueno, String telefono, String direccion,
            String proximoControl, String tipo, String historia) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("info", instancias.getInformacionEmpresa());
        parametros.put("id", id);
        parametros.put("nombre", nombre);
        parametros.put("sexo", sexo);
        parametros.put("raza", raza);
        parametros.put("edad", edad);
        parametros.put("codigo", codigo);
        parametros.put("historia", historia);
        parametros.put("reproductivo", reproductivo);
        parametros.put("cedula", cedula);
        parametros.put("nombreDueño", nombreDueno);
        parametros.put("telefono", telefono);
        parametros.put("direccion", direccion);
        parametros.put("fecha", metodosGenerales.fecha());
        parametros.put("urlImagen", logo());
        parametros.put("firma", firma());
        parametros.put("usuario", instancias.getUsuarioLog().getNombre());
        parametros.put("proximoControl", proximoControl);
        ejecutar("formulaMedicaVeterinaria" + tipo, parametros);
    }

    public void verRemision(String cedula, String nombre, String historia, String raza, String consecutivo,
            String sexo, String edad, String tipo, String descripcion, String fecha, String tipo2) {
        ejecutar("HojaRemision" + tipo2,
                parametrosHojaClinica(cedula, nombre, historia, raza, consecutivo, sexo, edad, tipo, descripcion, fecha));
    }

    public void ver_Hospitalizacion(String IdHosp) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("usuario", instancias.getUsuario());
        parametros.put("id", IdHosp);
        ejecutar("hospitalizacion", parametros);
    }

    private Map<String, Object> parametrosHojaClinica(String cedula, String nombre, String historia, String raza,
            String consecutivo, String sexo, String edad, String tipo, String descripcion, String fecha) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("info", instancias.getInformacionEmpresa());
        parametros.put("id", cedula);
        parametros.put("nombre", nombre);
        parametros.put("historia", historia);
        parametros.put("raza", raza);
        parametros.put("Id", consecutivo);
        parametros.put("sexo", sexo);
        parametros.put("edad", edad);
        parametros.put("tipo", tipo);
        parametros.put("descripcion", descripcion);
        parametros.put("fecha", fecha);
        parametros.put("urlImagen", logo());
        parametros.put("firma", firma());
        parametros.put("usuario", instancias.getUsuarioLog().getNombre());
        return parametros;
    }

    private String firma() {
        return System.getProperty("user.dir") + "//imagenes//firmas//" + instancias.getUsuario() + ".jpg";
    }

    // Estos formatos se llenan sin conexion a la base de datos (conBd = false)
    private void ejecutarSinBaseDatos(String nombreReporte, Map<String, ?> parametros) {
        try {
            URL in = getClass().getResource(nombreReporte + ".jasper");
            JasperReport reporte = (JasperReport) JRLoader.loadObject(in);
            IniciarReporte ini = new IniciarReporte(parametros, reporte, false, false, instancias);
            vistaBarraProceso barra = new vistaBarraProceso(ini, instancias);
            barra.show();
        } catch (JRException e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
        }
    }
}
