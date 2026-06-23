package Modelo.Ventas;

public class OpcionPreparacion {

    private static final String PREFIJO_ADICION = "ADICION-";
    private static final String ESTADO_ACTIVO = "true";

    private final String principal;
    private final String codigo;
    private final String cantidad;
    private final String estado;

    public OpcionPreparacion(String principal, String codigo, String cantidad, String estado) {
        this.principal = principal;
        this.codigo = codigo;
        this.cantidad = cantidad;
        this.estado = estado;
    }

    public String getPrincipal() {
        return principal;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getCantidad() {
        return cantidad;
    }

    public String getEstado() {
        return estado;
    }

    public boolean esAdicion() {
        return principal.contains(PREFIJO_ADICION);
    }

    public boolean activa() {
        return ESTADO_ACTIVO.equalsIgnoreCase(estado.trim());
    }
}
