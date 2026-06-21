package Enums;

public enum TipoProducto {

    IMEI("IMEI"),
    FECHA_LOTE("Fecha/Lote"),
    COLOR("Color"),
    SERIAL("Serial"),
    TALLA("Talla"),
    COLOR_TALLA("ColorTalla"),
    SERIAL_COLOR("SerialColor");

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
