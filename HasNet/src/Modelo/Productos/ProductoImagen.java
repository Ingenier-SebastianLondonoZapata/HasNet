package Modelo.Productos;

public class ProductoImagen {

    private final String idSistema;
    private final String codigo;
    private final String codigoBarras;
    private final String descripcion;

    public ProductoImagen(String idSistema, String codigo, String codigoBarras, String descripcion) {
        this.idSistema = idSistema;
        this.codigo = codigo;
        this.codigoBarras = codigoBarras;
        this.descripcion = descripcion;
    }

    public String getIdSistema() {
        return idSistema;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
