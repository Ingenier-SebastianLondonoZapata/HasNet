package Enums;

public enum DetalleTipoProducto {

    IMEI("IMEI"),
    FECHA_LOTE("Fecha/Lote"),
    COLOR("Color"),
    SERIAL("Serial"),
    TALLA("Talla"),
    COLOR_TALLA("ColorTalla"),
    SERIAL_COLOR("SerialColor");

    String tipoProducto;

    DetalleTipoProducto(String tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public String getValue() {
        return tipoProducto;
    }

    public static String obtenerTipoProducto(String tipoProducto) {
        for (DetalleTipoProducto tipo : DetalleTipoProducto.values()) {
            if (tipo.getValue().equals(tipoProducto)) {
                return tipo.getValue();
            }
        }
        return "";
    }
    
    public static boolean esTipoDetallado(String tipoProducto) {
        return !obtenerTipoProducto(tipoProducto).isEmpty();
    }
}
