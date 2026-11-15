package Modelo.SoportesMercantiles;

import java.util.ArrayList;
import java.util.List;

public class ResultadoEventoMercantil {

    public static final String EXITOSO = "EXITOSO";
    public static final String YA_REGISTRADO = "YA_REGISTRADO";
    public static final String ERROR = "ERROR";
    public static final String ERROR_DIAN = "ERRORDIAN";
    public static final String ERROR_CONEXION = "ERROR_CONEXION";
    public static final String ERROR_AUTORIZACION = "ERROR_AUTORIZACION";

    private String estado = ERROR;
    private String descripcion = "";
    private final List<String> errores = new ArrayList<>();
    private String prefijoFactura = "";
    private String cufe = "";
    private String uuid = "";
    private String fecha = "";

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado == null || estado.trim().isEmpty() ? ERROR : estado.trim().toUpperCase();
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion == null ? "" : descripcion.trim();
    }

    public List<String> getErrores() {
        return errores;
    }

    public String getPrefijoFactura() {
        return prefijoFactura;
    }

    public void setPrefijoFactura(String prefijoFactura) {
        this.prefijoFactura = prefijoFactura == null ? "" : prefijoFactura.trim();
    }

    public String getCufe() {
        return cufe;
    }

    public void setCufe(String cufe) {
        this.cufe = cufe == null ? "" : cufe.trim();
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? "" : uuid.trim();
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha == null ? "" : fecha.trim();
    }

    public boolean isCompletado() {
        return EXITOSO.equals(estado) || YA_REGISTRADO.equals(estado);
    }

    public boolean detieneProceso() {
        return ERROR_CONEXION.equals(estado) || ERROR_AUTORIZACION.equals(estado);
    }

    public String getErroresTexto() {
        return String.join("\n", errores);
    }

    public String getResumen() {
        if (!errores.isEmpty()) {
            return descripcion.isEmpty() ? errores.get(0) : descripcion + ": " + errores.get(0);
        }
        return descripcion;
    }
}
