package Utilidades.ImportacionesExcel;

import java.time.LocalDate;
import java.time.ZoneOffset;
import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.NumberCell;
import jxl.Sheet;

class HojaExcelXls implements HojaExcel {

    private final Sheet hoja;

    HojaExcelXls(Sheet hoja) {
        this.hoja = hoja;
    }

    @Override
    public int filas() {
        return hoja.getRows();
    }

    @Override
    public int columnas() {
        return hoja.getColumns();
    }

    private Cell celda(int columna, int fila) {
        if (fila < 0 || columna < 0 || fila >= hoja.getRows() || columna >= hoja.getColumns()) {
            return null;
        }
        return hoja.getCell(columna, fila);
    }

    @Override
    public String contenido(int columna, int fila) {
        Cell celda = celda(columna, fila);
        return celda == null || celda.getContents() == null ? "" : celda.getContents();
    }

    @Override
    public Double numero(int columna, int fila) {
        Cell celda = celda(columna, fila);
        if (celda == null || (celda.getType() != CellType.NUMBER && celda.getType() != CellType.NUMBER_FORMULA)) {
            return null;
        }
        return ((NumberCell) celda).getValue();
    }

    @Override
    public LocalDate fecha(int columna, int fila) {
        Cell celda = celda(columna, fila);
        if (celda == null || (celda.getType() != CellType.DATE && celda.getType() != CellType.DATE_FORMULA)) {
            return null;
        }
        return ((DateCell) celda).getDate().toInstant().atZone(ZoneOffset.UTC).toLocalDate();
    }
}
