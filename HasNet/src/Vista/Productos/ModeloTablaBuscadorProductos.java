package Vista.Productos;

import javax.swing.table.DefaultTableModel;

public class ModeloTablaBuscadorProductos extends DefaultTableModel {

    public static final int COL_CODIGO = 0;
    public static final int COL_DESCRIPCION = 1;
    public static final int COL_REFERENCIA = 2;
    public static final int COL_PRECIO = 3;
    public static final int COL_INVENTARIO = 4;
    public static final int COL_UBICACION = 5;
    public static final int COL_SELECCION = 6;
    public static final int COL_CANTIDAD = 7;
    public static final int COL_GRUPO = 8;
    public static final int COL_ULTIMO_COSTO = 9;
    public static final int COL_ID_SISTEMA = 10;
    public static final int COL_VER = 11;
    public static final int COL_USUARIO = 12;
    public static final int COL_MANEJA_INVENTARIO = 13;
    public static final int COL_PLU = 14;
    public static final int COL_IND_VENTAS = 15;
    public static final int COL_DESCRIPCION_LARGA = 16;

    private static final String[] COLUMNAS = {
        "Codigo", "Descripcion", "Referencia", "PVP", "Inv", "ubicacion", "Sel", "Cant", "Grupo", "U. Costo", "idSistema", "Ver",
        "Usuario", "manejaInventario", "PLU", "Ind", "descripcionLarga"
    };

    public ModeloTablaBuscadorProductos(Object[][] filas) {
        super(filas, COLUMNAS);
    }

    @Override
    public boolean isCellEditable(int fila, int columna) {
        return columna == COL_SELECCION || columna == COL_CANTIDAD;
    }

    @Override
    public Class<?> getColumnClass(int columna) {
        return columna == COL_SELECCION ? Boolean.class : Object.class;
    }
}
