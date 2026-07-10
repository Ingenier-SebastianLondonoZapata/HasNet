package Enums;

public enum EstadosDetalleProducto {

    DISPONIBLE("DISPONIBLE"),
    NO_DISPONIBLE("NO-DISPONIBLE"),
    EN_PEDIDO("PRESTADO"),
    EN_PLAN_SEPARE("SEPARADO"),
    EN_MESA("CONGELADO"),
    ANULADO("ANULADO"),
    EN_TRANSITO("EN-TRANSITO");

    private final String nombre;

    EstadosDetalleProducto(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public static EstadosDetalleProducto fromValue(String valor) {
        for (EstadosDetalleProducto tipo : values()) {
            if (tipo.nombre.equals(valor)) {
                return tipo;
            }
        }

        throw new IllegalArgumentException("Estado no soportado: " + valor);
    }
}
