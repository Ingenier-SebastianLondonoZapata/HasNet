package Modelo.Productos;

import java.util.ArrayList;
import java.util.List;

public class ResultadoImportacionProductos {

    private final boolean conInventario;
    private int filasLeidas;
    private int guardados;
    private final List<FilaImportacionRechazada> errores = new ArrayList<>();
    private final List<FilaImportacionRechazada> repetidos = new ArrayList<>();
    private final List<String> avisos = new ArrayList<>();

    public ResultadoImportacionProductos(boolean conInventario) {
        this.conInventario = conInventario;
    }

    public boolean isConInventario() {
        return conInventario;
    }

    public int getFilasLeidas() {
        return filasLeidas;
    }

    public int getGuardados() {
        return guardados;
    }

    public List<FilaImportacionRechazada> getErrores() {
        return errores;
    }

    public List<FilaImportacionRechazada> getRepetidos() {
        return repetidos;
    }

    public List<String> getAvisos() {
        return avisos;
    }

    public void registrarLeida() {
        filasLeidas++;
    }

    public void registrarGuardado() {
        guardados++;
    }

    public void agregarError(FilaPlantillaProductos fila, String motivo) {
        errores.add(new FilaImportacionRechazada(fila, motivo));
    }

    public void agregarRepetido(FilaPlantillaProductos fila, String motivo) {
        repetidos.add(new FilaImportacionRechazada(fila, motivo));
    }

    public void agregarAviso(String aviso) {
        avisos.add(aviso);
    }
}
