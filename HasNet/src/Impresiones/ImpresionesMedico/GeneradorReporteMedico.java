package Impresiones.ImpresionesMedico;

import Impresiones.GeneradorReporteBase;
import clases.Instancias;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class GeneradorReporteMedico extends GeneradorReporteBase {

    public GeneradorReporteMedico(Instancias instancias) {
        super(instancias);
    }

    public void ver_RepFormulas(String sql, String encabezado, String tipo) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("cliente", sql);
        parametros.put("encabezado", encabezado);
        ejecutar("repFormulas" + tipo, parametros);
    }

    public void verNotaEnfermeria(String numero, String info, String id, String tipo, String nombre, String sexo, String estado,
            String edad, String fecha, boolean imprimir) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("numero", numero);
        parametros.put("id", id);
        parametros.put("tipo", tipo);
        parametros.put("nombre", nombre);
        parametros.put("sexo", sexo);
        parametros.put("estado", estado);
        parametros.put("edad", edad);
        parametros.put("fecha", fecha);
        parametros.put("urlImagen", logo());
        parametros.put("info", info);
        parametros.put("firma", firmaUsuario(instancias.getUsuario()));
        parametros.put("usuario", instancias.getUsuarioLog().getNombre());
        ejecutar("notaEnfermeria", parametros, imprimir);
    }

    public void verCertificadoMedico(String info, String id, String edad, String tipo) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("info", info);
        parametros.put("id", id);
        parametros.put("edad", edad);
        parametros.put("urlImagen", logo());
        ejecutar("certificadoMedico" + tipo, parametros);
    }

    public void ver_informePaciente1(String id) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("ID", id);
        parametros.put("urlImagen", logo());
        parametros.put("usuario", instancias.getUsuarioLog().getNombre());
        parametros.put("firma", firmaUsuario(instancias.getUsuario()));
        ejecutar("informePaciente", parametros);
    }

    public void ver_informePaciente(String id) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("ID", id);
        parametros.put("urlImagen", logo());
        parametros.put("ImagenTercero", imagenTercero());
        parametros.put("usuario", instancias.getUsuarioLog().getNombre());
        parametros.put("firma", firmaUsuario(instancias.getUsuario()));
        ejecutar("informePaciente", parametros);
    }

    public void verRepMedico(String sql, String tipo) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("cliente", sql);
        ejecutar("repOrdenes" + tipo, parametros);
    }

    public void verHistoria(String dato, String edad) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("id", dato);
        parametros.put("edad", edad);
        parametros.put("firma", firmaUsuario(instancias.getUsuario()));
        parametros.put("usuario", instancias.getUsuarioLog().getNombre());
        parametros.put("urlImagen", logo());
        ejecutar("historia", parametros);
    }

    /**
     * Remision de la historia: la firma y el usuario salen del usuario que
     * registro la orden.
     */
    public void verRemision(String dato, String edad) {
        String usuarioOrden = instancias.getSql().getUsuarioOrden(dato);

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("id", dato);
        parametros.put("edad", edad);
        parametros.put("firma", firmaUsuario(usuarioOrden));
        parametros.put("usuario", usuarioOrden);
        parametros.put("urlImagen", logo());
        ejecutar("remision", parametros);
    }

    public void verOrdenServicio(String numero) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("numero", numero);
        parametros.put("informacionLegalClick", instancias.getReporte().getInformacionLegalClick());
        ejecutar("ordenServicio", parametros);
    }

    public void verFormulaMedica(String numero, String info, String usuario, String tipo) {
        ejecutarOrdenMedica("formulaMedica" + tipo, numero, info, usuario);
    }

    public void verAyudaDiagnostica(String numero, String info, String usuario, String tipo) {
        ejecutarOrdenMedica("ayudaDiagnostico" + tipo, numero, info, usuario);
    }

    public void verIncapacidad(String numero, String info, String usuario, String tipo) {
        ejecutarOrdenMedica("incapacidad" + tipo, numero, info, usuario);
    }

    public void verRemision(String numero, String info, String usuario, String tipo) {
        ejecutarOrdenMedica("remisionMedico" + tipo, numero, info, usuario);
    }

    public void verContraremision(String numero, String info, String usuario, String tipo) {
        ejecutarOrdenMedica("contraremisionMedico" + tipo, numero, info, usuario);
    }

    private void ejecutarOrdenMedica(String nombreReporte, String numero, String info, String usuario) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("numero", numero);
        parametros.put("urlImagen", logo());
        parametros.put("info", info);
        parametros.put("firma", firmaUsuario(usuario));
        parametros.put("usuario", instancias.getSql().getNombreEmpleadoUsuario(usuario));
        ejecutar(nombreReporte, parametros);
    }

    private String firmaUsuario(String usuario) {
        return System.getProperty("user.dir") + "//imagenes//firmas//" + usuario + ".jpg";
    }

    private InputStream imagenTercero() {
        try {
            String ruta = System.getProperty("user.dir") + "//imagenes//terceros//" + instancias.getReporte().getImagenInforme1() + ".jpg";
            return new FileInputStream(new File(ruta));
        } catch (Exception e) {
            return null;
        }
    }
}
