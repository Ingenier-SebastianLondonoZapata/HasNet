package Controlador.FacturacionElectronica;

import Modelos.JSONCreacion.modeloCreacion;
import org.json.JSONException;
import org.json.JSONObject;

public class controladorCrearJSONCreacion {

    public String crearJSONCreacion(modeloCreacion modeloJsonCreacion) throws JSONException {

        return new JSONObject()
                .put("canal", 37)
                .put("TipoServicio", modeloJsonCreacion.getTipoServicio())
                .put("tipoPersona", modeloJsonCreacion.getTipoPersona())
                .put("tipoIdentificacion", modeloJsonCreacion.getTipoIdentificacion())
                .put("identificacion", modeloJsonCreacion.getIdentificacion())
                .put("nombres", modeloJsonCreacion.getNombres())
                .put("segundoNombre", modeloJsonCreacion.getSegundoNombre())
                .put("primerApellido", modeloJsonCreacion.getPrimerApellido())
                .put("segundoApellido", modeloJsonCreacion.getSegundoApellido())
                .put("direccion", modeloJsonCreacion.getDireccion())
                .put("telefono", modeloJsonCreacion.getTelefono())
                .put("email", modeloJsonCreacion.getEmail())
                .put("emailRemitente", modeloJsonCreacion.getEmailRemitente())
                .put("emailDefecto", modeloJsonCreacion.getEmailDefecto())
                .put("cdDaneCiudad", modeloJsonCreacion.getCdDaneCiudad())
                .put("cdDaneDepartamento", modeloJsonCreacion.getCdDaneDepartamento())
                .put("tipoRegimen", modeloJsonCreacion.getTipoRegimen())
                .put("sitioWeb", modeloJsonCreacion.getSitioWeb())
                .put("codigoPostal", modeloJsonCreacion.getCodigoPostal())
                .put("codigoCIIU", modeloJsonCreacion.getCodigoCIIU())
                .put("codigoObligaciones", modeloJsonCreacion.getCodigoObligaciones())
                .put("codigoTributario", modeloJsonCreacion.getCodigoTributario())
                .put("imagenHeaderIzquierda", modeloJsonCreacion.getImagenHeaderIzquierda())
                .put("identificadorPruebasDIAN", modeloJsonCreacion.getIdentificadorPruebasDIAN()).toString();
    }
}
