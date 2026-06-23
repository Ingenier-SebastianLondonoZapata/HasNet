package Enums;

public enum TipoProducto {

    GENERICO("ADMIN"),
    PRODUCTO_DISENADO("FACTURA"),
    PRODUCTO_COSTEADO("COSTEO");

    String tipoProducto;

    TipoProducto(String tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public String getValue() {
        return tipoProducto;
    }

    public static String obtenerTipoProducto(String tipoProducto) {
        for (TipoProducto tipo : TipoProducto.values()) {
            if (tipo.getValue().equals(tipoProducto)) {
                return tipo.getValue();
            }
        }
        return "";
    }
    
}
