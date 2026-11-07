package Modelo.Productos;

public class ModeloOpcionCatalogo {

    private final String codigo;
    private final String nombre;

    public ModeloOpcionCatalogo(String codigo, String nombre) {
        this.codigo = codigo == null ? "" : codigo.trim();
        this.nombre = nombre == null ? "" : nombre.trim();
    }

    public static ModeloOpcionCatalogo vacia(String texto) {
        return new ModeloOpcionCatalogo("", texto);
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean esVacia() {
        return codigo.isEmpty();
    }

    @Override
    public String toString() {
        return nombre;
    }
}
