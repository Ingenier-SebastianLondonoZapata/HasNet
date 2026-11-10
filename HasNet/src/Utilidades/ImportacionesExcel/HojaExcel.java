package Utilidades.ImportacionesExcel;

import java.time.LocalDate;

public interface HojaExcel {

    int filas();

    int columnas();

    String contenido(int columna, int fila);

    Double numero(int columna, int fila);

    LocalDate fecha(int columna, int fila);
}
