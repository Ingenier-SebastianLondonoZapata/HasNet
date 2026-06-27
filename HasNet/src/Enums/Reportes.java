package Enums;

public enum Reportes {

    AJUSTES_INVENTARIO("ajustes"),
    AJUSTES_INVENTARIO_POS("posAjustes"),
    AJUSTES_INVENTARIO_DETALLE("ajustesDetalle");

    private final String nombre;

    Reportes(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public static Reportes fromValue(String valor) {
        for (Reportes tipo : values()) {
            if (tipo.nombre.equals(valor)) {
                return tipo;
            }
        }

        throw new IllegalArgumentException("Tabla no soportada: " + valor);
    }
}