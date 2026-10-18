package Utilidades.ImportacionesExcel;

import Enums.ColumnaPlantillaProductos;
import Modelo.Productos.FilaPlantillaProductos;
import Modelo.Productos.PlantillaProductos;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jxl.Cell;
import jxl.CellType;
import jxl.NumberCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public final class LectorPlantillaProductos {

    private LectorPlantillaProductos() {
    }

    public static PlantillaProductos leer(File archivo) throws IOException {
        Workbook libro;
        try {
            libro = Workbook.getWorkbook(archivo);
        } catch (BiffException ex) {
            throw new IOException("El archivo no es un Excel 97-2003 (.xls) válido", ex);
        }

        try {
            Sheet hoja = libro.getSheet(0);
            Map<ColumnaPlantillaProductos, Integer> columnas = leerEncabezado(hoja);
            PlantillaProductos plantilla = new PlantillaProductos(columnas.containsKey(ColumnaPlantillaProductos.INVENTARIO_INICIAL));

            for (int fila = 1; fila < hoja.getRows(); fila++) {
                Object[] valores = leerFila(hoja, fila, columnas, plantilla.isConInventario());
                if (!vacia(valores)) {
                    plantilla.getFilas().add(new FilaPlantillaProductos(fila + 1, valores));
                }
            }

            return plantilla;
        } finally {
            libro.close();
        }
    }

    private static Map<ColumnaPlantillaProductos, Integer> leerEncabezado(Sheet hoja) throws IOException {
        Map<String, Integer> porNombre = new HashMap<>();
        for (int columna = 0; columna < hoja.getColumns(); columna++) {
            porNombre.put(normalizar(hoja.getCell(columna, 0).getContents()), columna);
        }

        Map<ColumnaPlantillaProductos, Integer> columnas = new EnumMap<>(ColumnaPlantillaProductos.class);
        List<String> faltantes = new ArrayList<>();
        for (ColumnaPlantillaProductos columna : ColumnaPlantillaProductos.values()) {
            Integer indice = porNombre.get(normalizar(columna.getEncabezado()));
            if (indice != null) {
                columnas.put(columna, indice);
            } else if (columna.esObligatoriaEnArchivo()) {
                faltantes.add(columna.getEncabezado());
            }
        }

        if (columnas.containsKey(ColumnaPlantillaProductos.INVENTARIO_INICIAL) != columnas.containsKey(ColumnaPlantillaProductos.COSTO)) {
            faltantes.add(columnas.containsKey(ColumnaPlantillaProductos.INVENTARIO_INICIAL)
                    ? ColumnaPlantillaProductos.COSTO.getEncabezado() : ColumnaPlantillaProductos.INVENTARIO_INICIAL.getEncabezado());
        }

        if (!faltantes.isEmpty()) {
            throw new IOException("El archivo no tiene las columnas de la plantilla en la primera fila. Faltan: " + String.join(", ", faltantes));
        }

        return columnas;
    }

    private static Object[] leerFila(Sheet hoja, int fila, Map<ColumnaPlantillaProductos, Integer> columnas, boolean conInventario) {
        Object[] valores = new Object[ColumnaPlantillaProductos.cantidad(conInventario)];
        for (ColumnaPlantillaProductos columna : ColumnaPlantillaProductos.values()) {
            Integer indice = columnas.get(columna);
            if (indice == null) {
                continue;
            }

            Cell celda = hoja.getCell(indice, fila);
            String contenido = celda.getContents().trim();
            if (contenido.isEmpty()) {
                continue;
            }

            if (celda.getType() == CellType.NUMBER) {
                double numero = ((NumberCell) celda).getValue();
                boolean esCodigo = columna == ColumnaPlantillaProductos.CODIGO || columna == ColumnaPlantillaProductos.CODIGO_BARRAS;
                valores[columna.ordinal()] = esCodigo ? (Object) new BigDecimal(numero).toBigInteger().toString() : (Object) numero;
            } else {
                valores[columna.ordinal()] = contenido;
            }
        }

        return valores;
    }

    private static boolean vacia(Object[] valores) {
        for (Object valor : valores) {
            if (valor != null) {
                return false;
            }
        }
        return true;
    }

    private static String normalizar(String valor) {
        String sinTildes = Normalizer.normalize(valor == null ? "" : valor, Normalizer.Form.NFD).replaceAll("\\p{M}", "");
        return sinTildes.toLowerCase().replaceAll("[^a-z0-9]", "");
    }
}
