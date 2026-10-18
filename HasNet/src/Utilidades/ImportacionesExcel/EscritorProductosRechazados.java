package Utilidades.ImportacionesExcel;

import Enums.ColumnaPlantillaProductos;
import Modelo.Productos.FilaImportacionRechazada;
import java.io.File;
import java.io.IOException;
import java.util.List;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public final class EscritorProductosRechazados {

    public static final String COLUMNA_MOTIVO = "Motivo";

    private EscritorProductosRechazados() {
    }

    public static void escribir(File destino, boolean conInventario, List<FilaImportacionRechazada> filas) throws IOException {
        int columnas = ColumnaPlantillaProductos.cantidad(conInventario);

        WritableWorkbook libro = Workbook.createWorkbook(destino);
        try {
            WritableSheet hoja = libro.createSheet("Hoja1", 0);
            WritableCellFormat negrita = new WritableCellFormat(new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD));

            for (int c = 0; c < columnas; c++) {
                hoja.addCell(new Label(c, 0, ColumnaPlantillaProductos.values()[c].getEncabezado(), negrita));
                hoja.setColumnView(c, c == ColumnaPlantillaProductos.DESCRIPCION.ordinal() ? 40 : 18);
            }
            hoja.addCell(new Label(columnas, 0, COLUMNA_MOTIVO, negrita));
            hoja.setColumnView(columnas, 70);

            int fila = 1;
            for (FilaImportacionRechazada rechazada : filas) {
                Object[] valores = rechazada.getFila().getValores();
                for (int c = 0; c < columnas && c < valores.length; c++) {
                    if (valores[c] instanceof Double) {
                        hoja.addCell(new Number(c, fila, (Double) valores[c]));
                    } else if (valores[c] != null) {
                        hoja.addCell(new Label(c, fila, valores[c].toString()));
                    }
                }
                hoja.addCell(new Label(columnas, fila, "Fila " + rechazada.getFila().getNumeroFila() + ": " + rechazada.getMotivo()));
                fila++;
            }

            libro.write();
        } catch (WriteException ex) {
            throw new IOException("No se pudo escribir el archivo " + destino.getName() + ": " + ex.getMessage(), ex);
        } finally {
            try {
                libro.close();
            } catch (WriteException ex) {
                throw new IOException("No se pudo cerrar el archivo " + destino.getName() + ": " + ex.getMessage(), ex);
            }
        }
    }
}
