package Utilidades.ImportacionesExcel;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

class HojaExcelXlsx implements HojaExcel {

    private final Map<Integer, Map<Integer, Celda>> celdas = new HashMap<>();
    private int filas;
    private int columnas;

    private static class Celda {

        private final String contenido;
        private final Double numero;
        private final LocalDate fecha;

        Celda(String contenido, Double numero, LocalDate fecha) {
            this.contenido = contenido;
            this.numero = numero;
            this.fecha = fecha;
        }
    }

    void agregar(int fila, int columna, String contenido, Double numero, LocalDate fecha) {
        if (fila < 0 || columna < 0 || contenido == null || contenido.isEmpty()) {
            return;
        }
        celdas.computeIfAbsent(fila, f -> new HashMap<>()).put(columna, new Celda(contenido, numero, fecha));
        filas = Math.max(filas, fila + 1);
        columnas = Math.max(columnas, columna + 1);
    }

    private Celda celda(int columna, int fila) {
        Map<Integer, Celda> celdasFila = celdas.get(fila);
        return celdasFila == null ? null : celdasFila.get(columna);
    }

    @Override
    public int filas() {
        return filas;
    }

    @Override
    public int columnas() {
        return columnas;
    }

    @Override
    public String contenido(int columna, int fila) {
        Celda celda = celda(columna, fila);
        return celda == null ? "" : celda.contenido;
    }

    @Override
    public Double numero(int columna, int fila) {
        Celda celda = celda(columna, fila);
        return celda == null ? null : celda.numero;
    }

    @Override
    public LocalDate fecha(int columna, int fila) {
        Celda celda = celda(columna, fila);
        return celda == null ? null : celda.fecha;
    }
}
