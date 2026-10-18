package Modelo.Productos;

import Enums.ColumnaPlantillaProductos;

public class FilaPlantillaProductos {

    private final int numeroFila;
    private final Object[] valores;

    public FilaPlantillaProductos(int numeroFila, Object[] valores) {
        this.numeroFila = numeroFila;
        this.valores = valores;
    }

    public int getNumeroFila() {
        return numeroFila;
    }

    public Object[] getValores() {
        return valores;
    }

    public Object getValor(ColumnaPlantillaProductos columna) {
        return valores[columna.ordinal()];
    }
}
