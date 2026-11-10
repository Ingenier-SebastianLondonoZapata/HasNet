package Utilidades.ImportacionesExcel;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public interface LibroExcel extends AutoCloseable {

    int cantidadHojas();

    HojaExcel hoja(int indice) throws IOException;

    @Override
    void close();

    static LibroExcel abrir(File archivo) throws IOException {
        if (esZip(archivo)) {
            return new LibroExcelXlsx(archivo);
        }
        return new LibroExcelXls(archivo);
    }

    static boolean esZip(File archivo) throws IOException {
        try (InputStream entrada = new BufferedInputStream(new FileInputStream(archivo))) {
            return entrada.read() == 0x50 && entrada.read() == 0x4B;
        }
    }
}
