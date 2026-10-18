package Modelo.Productos;

import java.util.ArrayList;
import java.util.List;

public class PlantillaProductos {

    private final boolean conInventario;
    private final List<FilaPlantillaProductos> filas = new ArrayList<>();

    public PlantillaProductos(boolean conInventario) {
        this.conInventario = conInventario;
    }

    public boolean isConInventario() {
        return conInventario;
    }

    public List<FilaPlantillaProductos> getFilas() {
        return filas;
    }
}
