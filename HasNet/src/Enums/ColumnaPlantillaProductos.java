package Enums;

public enum ColumnaPlantillaProductos {

    CODIGO("Codigo"),
    CODIGO_BARRAS("Codigo de barras"),
    DESCRIPCION("Descripcion"),
    GRUPO("Grupo"),
    SUBGRUPO("Subgrupo"),
    UNIDAD_MEDIDA("Unidad medida"),
    TIPO_PRODUCTO("Tipo de producto"),
    IVA("% Iva"),
    IMPOCONSUMO("% Impoconsumo"),
    LISTA_PRECIO("Lista de precio"),
    INVENTARIO_INICIAL("Inventario Inicial"),
    COSTO("Costo");

    private final String encabezado;

    ColumnaPlantillaProductos(String encabezado) {
        this.encabezado = encabezado;
    }

    public String getEncabezado() {
        return encabezado;
    }

    public boolean esDeInventario() {
        return this == INVENTARIO_INICIAL || this == COSTO;
    }

    public boolean esObligatoriaEnArchivo() {
        return !esDeInventario() && this != GRUPO && this != SUBGRUPO;
    }

    public static int cantidad(boolean conInventario) {
        return conInventario ? values().length : values().length - 2;
    }
}
