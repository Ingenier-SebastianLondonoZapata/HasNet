package Enums;

public enum EventoMercantil {

    ACUSE_DOCUMENTO("Acuse de recibo"),
    RECIBO_SERVICIO("Recibo del bien o servicio"),
    ACEPTACION_EXPRESA("Aceptación expresa");

    private final String descripcion;

    EventoMercantil(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public static String descripcionDe(String nombre) {
        for (EventoMercantil evento : values()) {
            if (evento.name().equals(nombre)) {
                return evento.descripcion;
            }
        }
        return nombre;
    }
}
