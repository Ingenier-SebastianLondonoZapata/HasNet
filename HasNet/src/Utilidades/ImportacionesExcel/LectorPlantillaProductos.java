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

public final class LectorPlantillaProductos {

    private LectorPlantillaProductos() {
    }

    public static PlantillaProductos leer(File archivo) throws IOException {
        try (LibroExcel libro = LibroExcel.abrir(archivo)) {
            HojaExcel hoja = libro.hoja(0);
            Map<ColumnaPlantillaProductos, Integer> columnas = leerEncabezado(hoja);
            PlantillaProductos plantilla = new PlantillaProductos(columnas.containsKey(ColumnaPlantillaProductos.INVENTARIO_INICIAL));

            for (int fila = 1; fila < hoja.filas(); fila++) {
                Object[] valores = leerFila(hoja, fila, columnas, plantilla.isConInventario());
                if (!vacia(valores)) {
                    plantilla.getFilas().add(new FilaPlantillaProductos(fila + 1, valores));
                }
            }

            return plantilla;
        }
    }

    private static Map<ColumnaPlantillaProductos, Integer> leerEncabezado(HojaExcel hoja) throws IOException {
        Map<String, Integer> porNombre = new HashMap<>();
        for (int columna = 0; columna < hoja.columnas(); columna++) {
            porNombre.put(normalizar(hoja.contenido(columna, 0)), columna);
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

    private static Object[] leerFila(HojaExcel hoja, int fila, Map<ColumnaPlantillaProductos, Integer> columnas, boolean conInventario) {
        Object[] valores = new Object[ColumnaPlantillaProductos.cantidad(conInventario)];
        for (ColumnaPlantillaProductos columna : ColumnaPlantillaProductos.values()) {
            Integer indice = columnas.get(columna);
            if (indice == null) {
                continue;
            }

            String contenido = hoja.contenido(indice, fila).trim();
            if (contenido.isEmpty()) {
                continue;
            }

            Double numero = hoja.numero(indice, fila);
            if (numero != null) {
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
