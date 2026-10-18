package Modelo.Productos;

public class FilaImportacionRechazada {

    private final FilaPlantillaProductos fila;
    private final String motivo;

    public FilaImportacionRechazada(FilaPlantillaProductos fila, String motivo) {
        this.fila = fila;
        this.motivo = motivo;
    }

    public FilaPlantillaProductos getFila() {
        return fila;
    }

    public String getMotivo() {
        return motivo;
    }
}
