package Enums;

public enum EstadoCargaFoto {

    CARGADA("CARGADA", Categoria.CARGADO),
    REEMPLAZADA("REEMPLAZADA", Categoria.CARGADO),
    OMITIDA_EXISTENTE("OMITIDA: EL PRODUCTO YA TENÍA FOTO", Categoria.OMITIDO),
    DUPLICADA("OMITIDA: OTRA IMAGEN YA SE CARGÓ PARA ESTE PRODUCTO", Categoria.OMITIDO),
    PRODUCTO_NO_ENCONTRADO("PRODUCTO NO ENCONTRADO", Categoria.ERRADO),
    ERROR_GUARDADO("NO SE PUDO GUARDAR LA IMAGEN", Categoria.ERRADO);

    public enum Categoria {
        CARGADO, OMITIDO, ERRADO
    }

    private final String descripcion;
    private final Categoria categoria;

    EstadoCargaFoto(String descripcion, Categoria categoria) {
        this.descripcion = descripcion;
        this.categoria = categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Categoria getCategoria() {
        return categoria;
    }
}
