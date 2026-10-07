package Impresiones.ImpresionesOftalmologia;

import Impresiones.GeneradorReporteBase;
import clases.Instancias;
import clases.metodosGenerales;
import java.util.HashMap;
import java.util.Map;

public class GeneradorReporteOftalmologia extends GeneradorReporteBase {

    private static final String LOGO_FUNDACION = "/imagenes/Fundacion1.png";

    public GeneradorReporteOftalmologia(Instancias instancias) {
        super(instancias);
    }

    public void ver_HojaIngreso(String Id, boolean imprimir) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("Id", Id);
        parametros.put("info", instancias.getInformacionEmpresa());
        parametros.put("urlImagen", logo());
        ejecutar("hojaIngreso", parametros, imprimir);
    }

    public void ver_Biometria(String uno, String dos, String tres, String cuatro, String cinco, String seis, String siete, String ocho, String nueve, String diez,
            String once, String doce, String trece, String catorce, String quince, String uno1, String dos1, String tres1, String cuatro1, String cinco1, String seis1,
            String siete1, String ocho1, String nueve1, String diez1, String once1, String doce1, String trece1, String catorce1, String quince1, String Id, boolean imprimir) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("Id", Id);
        parametros.put("uno", uno);
        parametros.put("dos", dos);
        parametros.put("tres", tres);
        parametros.put("cuatro", cuatro);
        parametros.put("cinco", cinco);
        parametros.put("seis", seis);
        parametros.put("siete", siete);
        parametros.put("ocho", ocho);
        parametros.put("nueve", nueve);
        parametros.put("diez", diez);
        parametros.put("once", once);
        parametros.put("doce", doce);
        parametros.put("trece", trece);
        parametros.put("catorce", catorce);
        parametros.put("quince", quince);
        parametros.put("uno1", uno1);
        parametros.put("dos1", dos1);
        parametros.put("tres1", tres1);
        parametros.put("cuatro1", cuatro1);
        parametros.put("cinco1", cinco1);
        parametros.put("seis1", seis1);
        parametros.put("siete1", siete1);
        parametros.put("ocho1", ocho1);
        parametros.put("nueve1", nueve1);
        parametros.put("diez1", diez1);
        parametros.put("once1", once1);
        parametros.put("doce1", doce1);
        parametros.put("trece1", trece1);
        parametros.put("catorce1", catorce1);
        parametros.put("quince1", quince1);
        ejecutar("biometria", parametros, imprimir);
    }

    public void verIncapacidadOf(String numero, String info, String id, String tipo, String nombre, String sexo, String estado, String fecha, String edad) {
        Map<String, Object> parametros = parametrosPaciente(numero, info, id, tipo, nombre, sexo, estado, fecha);
        parametros.put("edad", edad);
        ejecutar("incapacidad", parametros);
    }

    public void verFormulaLentes(String numero, String info) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("numero", numero);
        parametros.put("urlImagen", logo());
        parametros.put("info", info);
        parametros.put("firma", firma());
        parametros.put("usuario", instancias.getUsuarioLog().getNombre());
        ejecutar("formulaLentes", parametros);
    }

    public void ver_Paquimetria(String factura, boolean imprimir) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("factura", factura.replace("PAQUI-", ""));
        parametros.put("numFactura", factura);
        parametros.put("urlImagen", logo());
        parametros.put("urlImagen2", getClass().getResourceAsStream(LOGO_FUNDACION));
        ejecutar("paquimetria1", parametros, imprimir);
    }

    public void verFormulaMedicaOf(String numero, String info, String id, String tipo, String nombre, String sexo, String estado, String edad, String fecha) {
        Map<String, Object> parametros = parametrosPaciente(numero, info, id, tipo, nombre, sexo, estado, fecha);
        parametros.put("edad", edad);
        ejecutar("formulaMedica", parametros);
    }

    public void verAyudaDiagnosticaOf(String numero, String info, String id, String tipo, String nombre, String sexo, String estado) {
        Map<String, Object> parametros = parametrosPaciente(numero, info, id, tipo, nombre, sexo, estado, metodosGenerales.fecha());
        ejecutar("ayudaDiagnostico", parametros);
    }

    private Map<String, Object> parametrosPaciente(String numero, String info, String id, String tipo, String nombre, String sexo,
            String estado, String fecha) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("numero", numero);
        parametros.put("id", id);
        parametros.put("tipo", tipo);
        parametros.put("nombre", nombre);
        parametros.put("sexo", sexo);
        parametros.put("estado", estado);
        parametros.put("fecha", fecha);
        parametros.put("urlImagen", logo());
        parametros.put("info", info);
        parametros.put("firma", firma());
        parametros.put("usuario", instancias.getUsuarioLog().getNombre());
        return parametros;
    }

    private String firma() {
        return System.getProperty("user.dir") + "//imagenes//firmas//" + instancias.getUsuario() + ".jpg";
    }
}
