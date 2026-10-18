package Utilidades.ImportacionesExcel;

import Dao.Productos.DaoGrupo;
import Dao.Productos.DaoProducto;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.List;
import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddressList;

public class PlantillaCreacionProductos implements DescargadorPlantillas.Personalizador {

    private static final int COLUMNA_GRUPO = 3;
    private static final int COLUMNA_SUBGRUPO = 4;
    private static final int COLUMNA_UNIDAD = 5;
    private static final int PRIMERA_FILA_DATOS = 1;
    private static final int ULTIMA_FILA_DATOS = 1001;

    private final DaoGrupo daoGrupo = new DaoGrupo();
    private final DaoProducto daoProducto = new DaoProducto();

    @Override
    public void personalizar(File archivo) throws IOException {
        List<String> grupos;
        List<String> subgrupos;
        List<String> unidades;
        try {
            grupos = daoGrupo.obtenerNombresGrupos();
            subgrupos = daoGrupo.obtenerNombresSubgrupos();
            unidades = daoProducto.obtenerNombresUnidadesMedida();
        } catch (SQLException ex) {
            throw new IOException("No se pudieron consultar los grupos, subgrupos y unidades de medida: " + ex.getMessage(), ex);
        }

        HSSFWorkbook libro;
        try (InputStream in = new FileInputStream(archivo)) {
            libro = new HSSFWorkbook(in);
        }

        HSSFSheet hoja = libro.getSheetAt(0);
        HSSFSheet opciones = libro.getSheetAt(1);

        escribirOpciones(opciones, COLUMNA_GRUPO, "Grupos", grupos);
        escribirOpciones(opciones, COLUMNA_SUBGRUPO, "Subgrupos", subgrupos);
        escribirOpciones(opciones, COLUMNA_UNIDAD, "Unidades de medida", unidades);
        agregarLista(hoja, opciones, COLUMNA_GRUPO, grupos.size(), "Grupo");
        agregarLista(hoja, opciones, COLUMNA_SUBGRUPO, subgrupos.size(), "Subgrupo");
        agregarLista(hoja, opciones, COLUMNA_UNIDAD, unidades.size(), "Unidad de medida");

        try (OutputStream out = new FileOutputStream(archivo)) {
            libro.write(out);
        }
    }

    private void escribirOpciones(HSSFSheet opciones, int columna, String titulo, List<String> valores) {
        celda(opciones, 0, columna).setCellValue(titulo);
        for (int i = 0; i < valores.size(); i++) {
            celda(opciones, i + 1, columna).setCellValue(valores.get(i));
        }
    }

    private HSSFCell celda(HSSFSheet hoja, int fila, int columna) {
        HSSFRow row = hoja.getRow(fila) != null ? hoja.getRow(fila) : hoja.createRow(fila);
        return row.getCell(columna) != null ? row.getCell(columna) : row.createCell(columna);
    }

    private void agregarLista(HSSFSheet hoja, HSSFSheet opciones, int columna, int cantidad, String titulo) {
        if (cantidad == 0) {
            return;
        }

        char letra = (char) ('A' + columna);
        String rango = "'" + opciones.getSheetName() + "'!$" + letra + "$2:$" + letra + "$" + (cantidad + 1);

        HSSFDataValidation validacion = new HSSFDataValidation(
                new CellRangeAddressList(PRIMERA_FILA_DATOS, ULTIMA_FILA_DATOS, columna, columna),
                DVConstraint.createFormulaListConstraint(rango));
        validacion.setSuppressDropDownArrow(false);
        validacion.setEmptyCellAllowed(true);
        validacion.setShowErrorBox(true);
        validacion.createErrorBox(titulo, "Seleccione una opción de la lista.");
        hoja.addValidationData(validacion);
    }
}
