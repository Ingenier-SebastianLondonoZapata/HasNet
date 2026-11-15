package Modelo.Inventario;

import java.util.ArrayList;
import java.util.List;

public class ResultadoImportacionCompra {

    private final List<LineaImportacionCompra> lineas = new ArrayList<>();
    private final List<String> errores = new ArrayList<>();
    private final List<String> avisos = new ArrayList<>();

    public List<LineaImportacionCompra> getLineas() {
        return lineas;
    }

    public List<String> getErrores() {
        return errores;
    }

    public List<String> getAvisos() {
        return avisos;
    }

    public boolean tieneErrores() {
        return !errores.isEmpty();
    }
}
