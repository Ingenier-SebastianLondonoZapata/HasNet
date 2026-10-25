package Procesos.Inventario.Servicio;

import Enums.DetalleTipoProducto;
import Utilidades.Contactos.Bodegas;
import Modelo.Inventario.LineaImportacionInventarioInicial;
import Modelo.Inventario.ResultadoImportacionInventarioInicial;
import Modelo.Productos.ModeloProducto;
import Utilidades.Utilidades;
import Procesos.Inventario.Dao.DaoDetalleProducto;
import Dao.Productos.DaoProducto;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.Normalizer;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.NumberCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ServicioImportacionInventarioInicial {

    public static final String[] COLUMNAS = {"Codigo", "Cantidad", "CostoUnidad", "ImeiSerial", "Color", "Talla", "Lote", "FechaVence", "Temperatura"};

    public static final String[] COLUMNAS_BASICAS = {"Codigo", "Cantidad", "CostoUnidad"};

    private static final int LONGITUD_IMEI = 15;

    /** Con productos detallados se leen los campos de detalle; si no, solo código, cantidad y costo unidad. */
    private final boolean productosDetallados;

    private final DaoProducto daoProducto = new DaoProducto();
    private final DaoDetalleProducto daoDetalleProducto = new DaoDetalleProducto();
    private final ServicioInventarioInicial servicioInventarioInicial = new ServicioInventarioInicial();

    private final Map<String, LineaImportacionInventarioInicial> lineasPorProducto = new LinkedHashMap<>();
    private final Map<String, ModeloProducto> productosPorCodigo = new HashMap<>();
    private final Set<String> productosRechazados = new HashSet<>();
    private final Set<String> identificadoresVistos = new HashSet<>();
    private final Set<String> detallesVistos = new HashSet<>();

    public ServicioImportacionInventarioInicial(boolean productosDetallados) {
        this.productosDetallados = productosDetallados;
    }

    public ResultadoImportacionInventarioInicial importar(File archivo, Set<String> idsYaCargados, Set<String> identificadoresYaCargados) throws IOException, BiffException {
        ResultadoImportacionInventarioInicial resultado = new ResultadoImportacionInventarioInicial();
        identificadoresVistos.addAll(identificadoresYaCargados);

        Workbook libro = Workbook.getWorkbook(archivo);
        try {
            Sheet hoja = libro.getSheet(0);
            Map<String, Integer> columnas = leerEncabezado(hoja, resultado);
            if (resultado.tieneErrores()) {
                return resultado;
            }

            for (int fila = 1; fila < hoja.getRows(); fila++) {
                if (filaVacia(hoja, fila)) {
                    continue;
                }
                procesarFila(hoja, fila, columnas, idsYaCargados, resultado);
            }
        } finally {
            libro.close();
        }

        validarTotales(resultado);
        resultado.getLineas().addAll(lineasPorProducto.values());
        return resultado;
    }

    private Map<String, Integer> leerEncabezado(Sheet hoja, ResultadoImportacionInventarioInicial resultado) {
        Map<String, Integer> columnas = new HashMap<>();
        for (int columna = 0; columna < hoja.getColumns(); columna++) {
            columnas.put(normalizar(hoja.getCell(columna, 0).getContents()), columna);
        }

        for (String requerida : new String[]{"Codigo"}) {
            if (!columnas.containsKey(normalizar(requerida))) {
                resultado.getErrores().add("El archivo no tiene la columna '" + requerida + "' en la primera fila. Columnas esperadas: "
                        + String.join(", ", productosDetallados ? COLUMNAS : COLUMNAS_BASICAS));
            }
        }

        return columnas;
    }

    private void procesarFila(Sheet hoja, int fila, Map<String, Integer> columnas, Set<String> idsYaCargados, ResultadoImportacionInventarioInicial resultado) {
        int numero = fila + 1;
        String codigo = texto(hoja, fila, columnas, "Codigo");
        if (codigo.isEmpty()) {
            resultado.getErrores().add("Fila " + numero + ": falta el código del producto");
            return;
        }

        LineaImportacionInventarioInicial linea = obtenerLinea(codigo, numero, idsYaCargados, resultado);
        if (linea == null) {
            return;
        }

        BigDecimal costo = decimal(hoja, fila, columnas, "CostoUnidad", numero, resultado);
        if (costo != null && costo.signum() < 0) {
            resultado.getErrores().add("Fila " + numero + ": el costo no puede ser negativo");
            return;
        }

        int erroresAntes = resultado.getErrores().size();
        String tipo = linea.getTipo();
        if (tipo.isEmpty()) {
            procesarSinTipo(hoja, fila, columnas, linea, numero, resultado);
        } else if (DetalleTipoProducto.esSerialOImei(tipo)) {
            procesarSerialOImei(hoja, fila, columnas, linea, numero, resultado);
        } else if (DetalleTipoProducto.esColorOTalla(tipo)) {
            procesarColorOTalla(hoja, fila, columnas, linea, numero, resultado);
        } else {
            procesarLote(hoja, fila, columnas, linea, numero, resultado);
        }

        if (costo == null || resultado.getErrores().size() > erroresAntes) {
            return;
        }
        if (linea.getCostoUnidad() == null) {
            linea.setCostoUnidad(costo);
        } else if (linea.getCostoUnidad().compareTo(costo) != 0) {
            resultado.getAvisos().add("Fila " + numero + ": el producto " + codigo + " tiene un costo distinto (" + costo.toPlainString()
                    + "); se usa el del primer registro (" + linea.getCostoUnidad().toPlainString() + ")");
        }
    }

    private LineaImportacionInventarioInicial obtenerLinea(String codigo, int numero, Set<String> idsYaCargados, ResultadoImportacionInventarioInicial resultado) {
        ModeloProducto producto = productosPorCodigo.get(codigo);
        if (producto == null) {
            producto = daoProducto.obtenerDatosProducto(codigo, Bodegas.predeterminada());
            productosPorCodigo.put(codigo, producto);
        }

        if (producto.getIdSistema() == null) {
            if (productosRechazados.add(codigo)) {
                resultado.getErrores().add("Fila " + numero + ": el producto " + codigo + " no existe");
            }
            return null;
        }

        String id = producto.getIdSistema();
        LineaImportacionInventarioInicial existente = lineasPorProducto.get(id);
        if (existente != null) {
            return existente;
        }

        if (productosRechazados.contains(id)) {
            return null;
        }

        String motivo = null;
        try {
            if (daoProducto.estaInactivo(id)) {
                motivo = "el producto " + codigo + " está inactivo";
            } else if (idsYaCargados.contains(id)) {
                motivo = "el producto " + codigo + " ya está cargado en la pantalla";
            } else if (servicioInventarioInicial.tieneInventarioInicial(id)) {
                motivo = "el producto " + codigo + " ya tiene inventario inicial";
            }
        } catch (SQLException ex) {
            motivo = "no se pudo consultar el producto " + codigo + ": " + ex.getMessage();
        }

        if (motivo != null) {
            productosRechazados.add(id);
            resultado.getErrores().add("Fila " + numero + ": " + motivo);
            return null;
        }

        LineaImportacionInventarioInicial linea = new LineaImportacionInventarioInicial(producto,
                productosDetallados ? DetalleTipoProducto.obtenerTipoProducto(producto.getTipoProducto()) : "");
        lineasPorProducto.put(id, linea);
        return linea;
    }

    private void procesarSinTipo(Sheet hoja, int fila, Map<String, Integer> columnas, LineaImportacionInventarioInicial linea, int numero, ResultadoImportacionInventarioInicial resultado) {
        BigDecimal cantidad = decimal(hoja, fila, columnas, "Cantidad", numero, resultado);
        if (cantidad == null || cantidad.signum() <= 0) {
            resultado.getErrores().add("Fila " + numero + ": ingrese una cantidad mayor a 0");
            return;
        }

        if (linea.getCantidad().signum() > 0) {
            resultado.getAvisos().add("Fila " + numero + ": el producto " + linea.getProducto().getCodigo()
                    + " está repetido, se combinó sumando la cantidad (" + cantidad.toPlainString() + ")");
        }

        linea.sumarCantidad(cantidad);
    }

    private void procesarSerialOImei(Sheet hoja, int fila, Map<String, Integer> columnas, LineaImportacionInventarioInicial linea, int numero, ResultadoImportacionInventarioInicial resultado) {
        String tipo = linea.getTipo();
        boolean esImei = DetalleTipoProducto.IMEI.getValue().equals(tipo);
        String etiqueta = esImei ? "imei" : "serial";

        String identificador = texto(hoja, fila, columnas, "ImeiSerial");
        if (identificador.isEmpty()) {
            resultado.getErrores().add("Fila " + numero + ": ingrese el " + etiqueta);
            return;
        }

        if (esImei && identificador.length() != LONGITUD_IMEI) {
            resultado.getErrores().add("Fila " + numero + ": imei errado (" + identificador + "), debe tener " + LONGITUD_IMEI + " dígitos");
            return;
        }

        if (!identificadoresVistos.add(identificador)) {
            resultado.getErrores().add("Fila " + numero + ": el " + etiqueta + " " + identificador
                    + " está repetido en el archivo o ya está cargado en la tabla");
            return;
        }

        if (existeEnInventario(identificador)) {
            resultado.getErrores().add("Fila " + numero + ": el " + etiqueta + " " + identificador + " ya existe en el inventario");
            return;
        }

        String color = "";
        boolean requiereColor = !DetalleTipoProducto.SERIAL.getValue().equals(tipo);
        if (requiereColor) {
            color = colorValido(hoja, fila, columnas, numero, resultado);
            if (color == null) {
                return;
            }
        }

        linea.getDetalles().add(new Object[]{linea.getProducto().getIdSistema(), identificador, "", "", "", BigDecimal.ONE,
            linea.getProducto().getDescripcion(), color, ""});
        linea.sumarCantidad(BigDecimal.ONE);
    }

    private void procesarColorOTalla(Sheet hoja, int fila, Map<String, Integer> columnas, LineaImportacionInventarioInicial linea, int numero, ResultadoImportacionInventarioInicial resultado) {
        String tipo = linea.getTipo();
        boolean usaColor = !DetalleTipoProducto.TALLA.getValue().equals(tipo);
        boolean usaTalla = !DetalleTipoProducto.COLOR.getValue().equals(tipo);

        String color = "";
        if (usaColor) {
            color = colorValido(hoja, fila, columnas, numero, resultado);
            if (color == null) {
                return;
            }
        }

        String talla = "";
        if (usaTalla) {
            talla = tallaValida(hoja, fila, columnas, numero, resultado);
            if (talla == null) {
                return;
            }
        }

        BigDecimal cantidad = decimal(hoja, fila, columnas, "Cantidad", numero, resultado);
        if (cantidad == null || cantidad.signum() <= 0) {
            resultado.getErrores().add("Fila " + numero + ": ingrese una cantidad mayor a 0");
            return;
        }

        for (Object[] existente : linea.getDetalles()) {
            if (existente[7].equals(color) && existente[8].equals(talla)) {
                existente[5] = ((BigDecimal) existente[5]).add(cantidad);
                linea.sumarCantidad(cantidad);
                String variante = (color.isEmpty() ? "" : " con color '" + color + "'")
                        + (talla.isEmpty() ? "" : (color.isEmpty() ? " con" : " y") + " talla '" + talla + "'");
                resultado.getAvisos().add("Fila " + numero + ": el producto " + linea.getProducto().getCodigo() + variante
                        + " está repetido, se combinó sumando la cantidad");
                return;
            }
        }

        linea.getDetalles().add(new Object[]{linea.getProducto().getIdSistema(), "", "", "", "", cantidad,
            linea.getProducto().getDescripcion(), color, talla});
        linea.sumarCantidad(cantidad);
    }

    private void procesarLote(Sheet hoja, int fila, Map<String, Integer> columnas, LineaImportacionInventarioInicial linea, int numero, ResultadoImportacionInventarioInicial resultado) {
        BigDecimal cantidad = decimal(hoja, fila, columnas, "Cantidad", numero, resultado);
        if (cantidad == null || cantidad.signum() <= 0) {
            resultado.getErrores().add("Fila " + numero + ": ingrese una cantidad mayor a 0");
            return;
        }

        LocalDate fechaVence = fecha(hoja, fila, columnas, "FechaVence");
        if (fechaVence == null) {
            resultado.getErrores().add("Fila " + numero + ": falta o es inválida la fecha de vencimiento (use dd/MM/yyyy)");
            return;
        }

        String lote = texto(hoja, fila, columnas, "Lote");
        String temperatura = texto(hoja, fila, columnas, "Temperatura");

        String clave = linea.getProducto().getIdSistema() + "|" + lote + "|" + fechaVence + "|" + temperatura;
        if (!detallesVistos.add(clave)) {
            resultado.getErrores().add("Fila " + numero + ": el lote " + lote + " con esa fecha de vencimiento está repetido en el archivo");
            return;
        }

        linea.getDetalles().add(new Object[]{linea.getProducto().getIdSistema(), "", lote, fechaVence.toString(), temperatura, cantidad,
            linea.getProducto().getDescripcion(), "", ""});
        linea.sumarCantidad(cantidad);
    }

    private void validarTotales(ResultadoImportacionInventarioInicial resultado) {
        Iterator<LineaImportacionInventarioInicial> it = lineasPorProducto.values().iterator();
        while (it.hasNext()) {
            LineaImportacionInventarioInicial linea = it.next();
            String codigo = linea.getProducto().getCodigo();

            if (linea.getCantidad().signum() <= 0) {
                resultado.getErrores().add("El producto " + codigo + " no se cargó porque ninguna de sus filas es válida");
                it.remove();
                continue;
            }

            if (linea.getCostoUnidad() == null) {
                try {
                    linea.setCostoUnidad(servicioInventarioInicial.obtenerCostoSugerido(linea.getProducto().getIdSistema()));
                } catch (SQLException ex) {
                    resultado.getErrores().add("El producto " + codigo + " no se cargó: no se pudo consultar el último costo");
                    it.remove();
                }
            }
        }
    }

    private boolean existeEnInventario(String imeiOSerial) {
        try {
            return daoDetalleProducto.existeImeiOSerialEnInventario(imeiOSerial);
        } catch (SQLException e) {
            return false;
        }
    }

    private String colorValido(Sheet hoja, int fila, Map<String, Integer> columnas, int numero, ResultadoImportacionInventarioInicial resultado) {
        String color = texto(hoja, fila, columnas, "Color").toUpperCase();
        if (color.isEmpty()) {
            resultado.getErrores().add("Fila " + numero + ": ingrese el color");
            return null;
        }

        return color;
    }

    private String tallaValida(Sheet hoja, int fila, Map<String, Integer> columnas, int numero, ResultadoImportacionInventarioInicial resultado) {
        String talla = texto(hoja, fila, columnas, "Talla").toUpperCase();
        if (talla.isEmpty()) {
            resultado.getErrores().add("Fila " + numero + ": ingrese la talla");
            return null;
        }

        return talla;
    }

    private Cell celda(Sheet hoja, int fila, Map<String, Integer> columnas, String columna) {
        Integer indice = columnas.get(normalizar(columna));
        return indice == null ? null : hoja.getCell(indice, fila);
    }

    private String texto(Sheet hoja, int fila, Map<String, Integer> columnas, String columna) {
        Cell celda = celda(hoja, fila, columnas, columna);
        if (celda == null) {
            return "";
        }

        if (celda.getType() == CellType.NUMBER) {
            return new BigDecimal(((NumberCell) celda).getValue()).toBigInteger().toString();
        }

        return celda.getContents().trim();
    }

    private BigDecimal decimal(Sheet hoja, int fila, Map<String, Integer> columnas, String columna, int numero, ResultadoImportacionInventarioInicial resultado) {
        Cell celda = celda(hoja, fila, columnas, columna);
        if (celda == null || celda.getContents().trim().isEmpty()) {
            return null;
        }

        if (celda.getType() == CellType.NUMBER) {
            return BigDecimal.valueOf(((NumberCell) celda).getValue());
        }

        String contenido = celda.getContents().trim();
        if (!contenido.matches("[-+]?[0-9.,]+")) {
            resultado.getErrores().add("Fila " + numero + ": '" + contenido + "' no es un número válido en " + columna);
            return null;
        }

        return Utilidades.convertirBigDecimal(contenido);
    }

    private LocalDate fecha(Sheet hoja, int fila, Map<String, Integer> columnas, String columna) {
        Cell celda = celda(hoja, fila, columnas, columna);
        if (celda == null || celda.getContents().trim().isEmpty()) {
            return null;
        }

        if (celda.getType() == CellType.DATE) {
            return ((DateCell) celda).getDate().toInstant().atZone(ZoneOffset.UTC).toLocalDate();
        }

        return Utilidades.convertirFecha(celda.getContents().trim());
    }

    private boolean filaVacia(Sheet hoja, int fila) {
        for (int columna = 0; columna < hoja.getColumns(); columna++) {
            if (!hoja.getCell(columna, fila).getContents().trim().isEmpty()) {
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
