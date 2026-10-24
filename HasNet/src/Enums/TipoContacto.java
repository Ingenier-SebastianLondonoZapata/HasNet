package Enums;

public enum TipoContacto {

    TERCERO("TERCERO"),
    PROVEEDOR("PROVEEDOR"),
    EMPLEADO("EMPLEADO");

    private final String valor;

    TipoContacto(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    public static TipoContacto desdeValor(String valor) {
        if (valor != null) {
            for (TipoContacto tipo : values()) {
                if (tipo.valor.equalsIgnoreCase(valor.trim())) {
                    return tipo;
                }
            }
        }
        return TERCERO;
    }
}
