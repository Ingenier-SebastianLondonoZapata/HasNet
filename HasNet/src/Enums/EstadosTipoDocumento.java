package Enums;

public enum EstadosTipoDocumento {

    PENDIENTE("PENDIENTE"),
    DISPONIBLE("DISPONIBLE"),
    FACTURADA("FACTURADA"),
    ANULADA("ANULADA");

    private final String nombre;

    EstadosTipoDocumento(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public static EstadosTipoDocumento fromValue(String valor) {
        for (EstadosTipoDocumento tipo : values()) {
            if (tipo.nombre.equals(valor)) {
                return tipo;
            }
        }

        throw new IllegalArgumentException("Estado no soportado: " + valor);
    }
}
