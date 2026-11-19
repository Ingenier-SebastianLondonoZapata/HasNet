package clases;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import jxl.Workbook;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.NumberFormat;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public final class ExportadorExcel {

    private ExportadorExcel() {
    }

    public static void exportar(File archivo, String titulo, String[] encabezados, List<Object[]> filas, List<String[]> totales)
            throws IOException, WriteException {
        WritableWorkbook libro = Workbook.createWorkbook(archivo);
        try {
            WritableSheet hoja = libro.createSheet("Reporte", 0);

            WritableCellFormat formatoTitulo = new WritableCellFormat(new WritableFont(WritableFont.ARIAL, 14, WritableFont.BOLD));
            WritableCellFormat formatoEncabezado = new WritableCellFormat(new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD));
            formatoEncabezado.setBackground(Colour.GREY_25_PERCENT);
            WritableCellFormat formatoMoneda = new WritableCellFormat(new NumberFormat("$ #,##0"));
            WritableCellFormat formatoNegrita = new WritableCellFormat(new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD));

            hoja.addCell(new Label(0, 0, titulo, formatoTitulo));
            for (int columna = 0; columna < encabezados.length; columna++) {
                hoja.addCell(new Label(columna, 2, encabezados[columna], formatoEncabezado));
                hoja.setColumnView(columna, 18);
            }

            int fila = 3;
            for (Object[] datos : filas) {
                for (int columna = 0; columna < datos.length; columna++) {
                    Object valor = datos[columna];
                    if (valor instanceof BigDecimal) {
                        hoja.addCell(new jxl.write.Number(columna, fila, ((BigDecimal) valor).doubleValue(), formatoMoneda));
                    } else if (valor instanceof Number) {
                        hoja.addCell(new jxl.write.Number(columna, fila, ((Number) valor).doubleValue()));
                    } else {
                        hoja.addCell(new Label(columna, fila, valor == null ? "" : valor.toString()));
                    }
                }
                fila++;
            }

            fila++;
            if (totales != null) {
                for (String[] total : totales) {
                    hoja.addCell(new Label(0, fila, total[0], formatoNegrita));
                    hoja.addCell(new Label(1, fila, total[1], formatoNegrita));
                    fila++;
                }
            }
            libro.write();
        } finally {
            libro.close();
        }
    }
}
