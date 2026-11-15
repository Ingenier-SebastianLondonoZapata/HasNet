package Utilidades.ImportacionesExcel;

import Dao.Productos.DaoProducto;
import Utilidades.Constantes;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddressList;

public class PlantillaCompra implements DescargadorPlantillas.Personalizador {

    private static final int PRIMERA_FILA_DATOS = 1;
    private static final int ULTIMA_FILA_DATOS = 2500;

    private static final int OPCIONES_MEDIDA = 0;
    private static final int OPCIONES_IVA = 1;
    private static final int OPCIONES_IMPOCONSUMO = 2;

    private final int columnaIva;
    private final int columnaImpoconsumo;
    private final int columnaMedida;
    private final DaoProducto daoProducto = new DaoProducto();

    public PlantillaCompra(boolean productosDetallados) {
        int primera = productosDetallados ? 9 : 3;
        this.columnaIva = primera;
        this.columnaImpoconsumo = primera + 1;
        this.columnaMedida = primera + 3;
    }

    @Override
    public void personalizar(File archivo) throws IOException {
        List<String> unidades;
        try {
            unidades = daoProducto.obtenerNombresUnidadesMedida();
        } catch (SQLException ex) {
            throw new IOException("No se pudieron consultar las unidades de medida: " + ex.getMessage(), ex);
        }
        personalizar(archivo, unidades);
    }

    void personalizar(File archivo, List<String> unidades) throws IOException {
        HSSFWorkbook libro;
        try (InputStream in = new FileInputStream(archivo)) {
            libro = new HSSFWorkbook(in);
        }

        HSSFSheet hoja = libro.getSheetAt(0);
        HSSFSheet opciones = libro.getSheetAt(1);

        escribirOpciones(opciones, OPCIONES_MEDIDA, "Unidades de medida", unidades);
        escribirOpciones(opciones, OPCIONES_IVA, "Iva %", texto(Constantes.TARIFAS_IVAS_PERMITIDOS));
        escribirOpciones(opciones, OPCIONES_IMPOCONSUMO, "Impoconsumo %", texto(Constantes.TARIFAS_IMPOCONSUMO_PERMITIDOS));

        agregarLista(hoja, opciones, columnaMedida, OPCIONES_MEDIDA, unidades.size(), "Medida", "Seleccione una unidad de medida de la lista.");
        agregarLista(hoja, opciones, columnaIva, OPCIONES_IVA, Constantes.TARIFAS_IVAS_PERMITIDOS.size(), "Iva %",
                "Seleccione una tarifa de IVA de la lista: " + Constantes.TARIFAS_IVAS_PERMITIDOS);
        agregarLista(hoja, opciones, columnaImpoconsumo, OPCIONES_IMPOCONSUMO, Constantes.TARIFAS_IMPOCONSUMO_PERMITIDOS.size(), "Impoconsumo %",
                "Seleccione una tarifa de impoconsumo de la lista: " + Constantes.TARIFAS_IMPOCONSUMO_PERMITIDOS);

        try (OutputStream out = new FileOutputStream(archivo)) {
            libro.write(out);
        }
    }

    private List<String> texto(List<Integer> tarifas) {
        List<String> valores = new ArrayList<>();
        for (Integer tarifa : tarifas) {
            valores.add(String.valueOf(tarifa));
        }
        return valores;
    }

    private void escribirOpciones(HSSFSheet opciones, int columna, String titulo, List<String> valores) {
        celda(opciones, 0, columna).setCellValue(titulo);
        for (int i = 0; i < valores.size(); i++) {
            HSSFCell celda = celda(opciones, i + 1, columna);
            try {
                celda.setCellValue(Double.parseDouble(valores.get(i)));
            } catch (NumberFormatException e) {
                celda.setCellValue(valores.get(i));
            }
        }
        opciones.setColumnWidth(columna, 6000);
    }

    private void agregarLista(HSSFSheet hoja, HSSFSheet opciones, int columna, int columnaOpciones, int cantidad, String titulo, String mensaje) {
        if (cantidad == 0) {
            return;
        }

        char letra = (char) ('A' + columnaOpciones);
        String rango = "'" + opciones.getSheetName() + "'!$" + letra + "$2:$" + letra + "$" + (cantidad + 1);
        HSSFDataValidation validacion = new HSSFDataValidation(
                new CellRangeAddressList(PRIMERA_FILA_DATOS, ULTIMA_FILA_DATOS, columna, columna),
                DVConstraint.createFormulaListConstraint(rango));
        validacion.setSuppressDropDownArrow(false);
        validacion.setEmptyCellAllowed(true);
        validacion.setShowErrorBox(true);
        validacion.createErrorBox(titulo, mensaje);
        hoja.addValidationData(validacion);
    }

    private HSSFCell celda(HSSFSheet hoja, int fila, int columna) {
        HSSFRow row = hoja.getRow(fila) != null ? hoja.getRow(fila) : hoja.createRow(fila);
        return row.getCell(columna) != null ? row.getCell(columna) : row.createCell(columna);
    }
}
