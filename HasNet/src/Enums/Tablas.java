package Enums;

public enum Tablas {

    PONDERADO("bdPonderado"),
    ULTIMO_PONDERADO("bdUltimoPonderado"),
    CONSECUTIVOS("bdConsecutivos"),
    DETALLE_PRODUCTO("bdDetalleProductos"),
    DISCOSTEO("bdDisCosteo"),
    PRECARGAR_DETALLE("bdPreCompraDetalle");

    private final String nombre;

    Tablas(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public static Tablas fromValue(String valor) {
        for (Tablas tipo : values()) {
            if (tipo.nombre.equals(valor)) {
                return tipo;
            }
        }

        throw new IllegalArgumentException("Tabla no soportada: " + valor);
    }
}