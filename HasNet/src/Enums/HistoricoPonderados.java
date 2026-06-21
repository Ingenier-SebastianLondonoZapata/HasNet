package Enums;

public enum HistoricoPonderados {

    CREACION_PRODUCTO("Creación de producto"),
    INVENTARIO_INICIAL("Inventario inicial");
    
    private final String nombre;

    HistoricoPonderados(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}