package Utilidades.ImportacionesExcel;

import java.io.File;
import java.io.IOException;
import jxl.Workbook;
import jxl.read.biff.BiffException;

class LibroExcelXls implements LibroExcel {

    private final Workbook libro;

    LibroExcelXls(File archivo) throws IOException {
        try {
            libro = Workbook.getWorkbook(archivo);
        } catch (BiffException ex) {
            throw new IOException("El archivo no es un Excel válido (.xls o .xlsx)", ex);
        }
    }

    @Override
    public int cantidadHojas() {
        return libro.getNumberOfSheets();
    }

    @Override
    public HojaExcel hoja(int indice) throws IOException {
        if (indice < 0 || indice >= libro.getNumberOfSheets()) {
            throw new IOException("El archivo no tiene la hoja " + (indice + 1));
        }
        return new HojaExcelXls(libro.getSheet(indice));
    }

    @Override
    public void close() {
        libro.close();
    }
}
