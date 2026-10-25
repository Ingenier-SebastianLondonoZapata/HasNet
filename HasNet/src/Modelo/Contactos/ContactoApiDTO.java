package Modelo.Contactos;

import org.json.JSONException;
import org.json.JSONObject;

public class ContactoApiDTO {

    private static final String CLAVE_ESTADO = "Estado";
    private static final String CLAVE_DATOS_ADQUIRIENTE = "Datos del adquiriente: ";
    private static final String CLAVE_NOMBRE = "Nombre o Razon social";
    private static final String CLAVE_CORREO = "Correo electronico";

    private final String estado;
    private final String nombre;
    private final String email;

    private ContactoApiDTO(String estado, String nombre, String email) {
        this.estado = estado;
        this.nombre = nombre;
        this.email = email;
    }

    public static ContactoApiDTO desdeRespuesta(String respuesta) throws JSONException {
        JSONObject datosRespuesta = new JSONObject(respuesta);
        String estado = datosRespuesta.optString(CLAVE_ESTADO, "").trim();
        JSONObject datosAdquiriente = datosRespuesta.optJSONObject(CLAVE_DATOS_ADQUIRIENTE);

        if (datosAdquiriente == null) {
            return new ContactoApiDTO(estado, "", "");
        }

        return new ContactoApiDTO(estado,
                datosAdquiriente.optString(CLAVE_NOMBRE, "").trim(),
                datosAdquiriente.optString(CLAVE_CORREO, "").trim());
    }

    public boolean estaVacio() {
        return nombre.isEmpty();
    }

    public String getEstado() {
        return estado;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }
}
