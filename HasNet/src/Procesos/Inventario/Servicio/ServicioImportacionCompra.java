package Procesos.Inventario.Servicio;

import Dao.Productos.DaoProducto;
import Enums.DetalleTipoProducto;
import Modelo.Inventario.LineaImportacionCompra;
import Modelo.Inventario.ResultadoImportacionCompra;
import Modelo.Productos.ModeloProducto;
import Procesos.Inventario.Dao.DaoDetalleProducto;
import Utilidades.Contactos.Bodegas;
import Utilidades.ImportacionesExcel.HojaExcel;
import Utilidades.ImportacionesExcel.LibroExcel;
import Utilidades.Constantes;
import Utilidades.Utilidades;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.Normalizer;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ServicioImportacionCompra {

    public static final String[] COLUMNAS = {"Codigo", "Cantidad", "Costo Unidad", "Imei o Serial", "Color", "Talla", "Lote",
        "Fecha Vencimiento", "Temperatura", "Iva %", "Impoconsumo %", "Descuento %", "Medida"};

    public static final String[] COLUMNAS_BASICAS = {"Codigo", "Cantidad", "Costo Unidad", "Iva %", "Impoconsumo %", "Descuento %", "Medida"};

    private static final int LONGITUD_IMEI = 15;
    private static final BigDecimal CIEN = new BigDecimal("100");

    private static final Map<String, String[]> ALIAS = new HashMap<>();

    static {
        ALIAS.put("Codigo", new String[]{"codigo"});
        ALIAS.put("Cantidad", new String[]{"cantidad"});
        ALIAS.put("CostoUnidad", new String[]{"costounidad", "valorunit", "valorunitario", "costo"});
        ALIAS.put("Iva", new String[]{"iva"});
        ALIAS.put("Impoconsumo", new String[]{"impoconsumo", "impo"});
        ALIAS.put("ImeiSerial", new String[]{"imeioserial", "imeiserial", "imei", "serial"});
        ALIAS.put("Color", new String[]{"color"});
        ALIAS.put("Talla", new String[]{"talla"});
        ALIAS.put("Lote", new String[]{"lote"});
        ALIAS.put("FechaVence", new String[]{"fechavencimiento", "fechavence"});
        ALIAS.put("Temperatura", new String[]{"temperatura"});
        ALIAS.put("Descuento", new String[]{"descuento", "descuentoporcentaje", "desc"});
        ALIAS.put("Medida", new String[]{"medida", "unidadmedida", "unidaddemedida", "unidad"});
    }

    private final boolean productosDetallados;

    private final DaoProducto daoProducto = new DaoProducto();
    private final DaoDetalleProducto daoDetalleProducto = new DaoDetalleProducto();

    private final Map<String, LineaImportacionCompra> lineasPorProducto = new LinkedHashMap<>();
    private final Map<String, ModeloProducto> productosPorCodigo = new HashMap<>();
    private final Set<String> productosRechazados = new HashSet<>();
    private final Set<String> identificadoresVistos = new HashSet<>();
    private final Set<String> detallesVistos = new HashSet<>();
    private final Map<String, String> unidadesMedida = new HashMap<>();

    public ServicioImportacionCompra(boolean productosDetallados) {
        this.productosDetallados = productosDetallados;
    }

    public ResultadoImportacionCompra importar(File archivo, Set<String> idsYaCargados, Set<String> identificadoresYaCargados) throws IOException {
        ResultadoImportacionCompra resultado = new ResultadoImportacionCompra();
        identificadoresVistos.addAll(identificadoresYaCargados);

        try (LibroExcel libro = LibroExcel.abrir(archivo)) {
            HojaExcel hoja = libro.hoja(0);
            Map<String, Integer> columnas = leerEncabezado(hoja, resultado);
            if (resultado.tieneErrores()) {
                return resultado;
            }

            if (columnas.containsKey("Medida") && !cargarUnidadesMedida(resultado)) {
                return resultado;
            }

            for (int fila = 1; fila < hoja.filas(); fila++) {
                if (filaVacia(hoja, fila)) {
                    continue;
                }
                procesarFila(hoja, fila, columnas, idsYaCargados, resultado);
            }
        }

        descartarLineasVacias(resultado);
        resultado.getLineas().addAll(lineasPorProducto.values());
        return resultado;
    }

    private Map<String, Integer> leerEncabezado(HojaExcel hoja, ResultadoImportacionCompra resultado) {
        Map<String, Integer> encontradas = new HashMap<>();
        for (int columna = 0; columna < hoja.columnas(); columna++) {
            encontradas.putIfAbsent(normalizar(hoja.contenido(columna, 0)), columna);
        }

        Map<String, Integer> columnas = new HashMap<>();
        for (Map.Entry<String, String[]> alias : ALIAS.entrySet()) {
            for (String nombre : alias.getValue()) {
                Integer indice = encontradas.get(nombre);
                if (indice != null) {
                    columnas.put(alias.getKey(), indice);
                    break;
                }
            }
        }

        if (!columnas.containsKey("Codigo")) {
            resultado.getErrores().add("El archivo no tiene la columna 'Codigo' en la primera fila. Columnas esperadas: "
                    + String.join(", ", productosDetallados ? COLUMNAS : COLUMNAS_BASICAS));
        }

        return columnas;
    }

    private void procesarFila(HojaExcel hoja, int fila, Map<String, Integer> columnas, Set<String> idsYaCargados, ResultadoImportacionCompra resultado) {
        int numero = fila + 1;
        String codigo = texto(hoja, fila, columnas, "Codigo");
        if (codigo.isEmpty()) {
            resultado.getErrores().add("Fila " + numero + ": falta el código del producto");
            return;
        }

        LineaImportacionCompra linea = obtenerLinea(codigo, numero, idsYaCargados, resultado);
        if (linea == null) {
            return;
        }

        int erroresAntes = resultado.getErrores().size();
        BigDecimal costo = decimal(hoja, fila, columnas, "CostoUnidad", numero, resultado);
        if (costo != null && costo.signum() < 0) {
            resultado.getErrores().add("Fila " + numero + ": el costo no puede ser negativo");
        }
        BigDecimal iva = tarifa(hoja, fila, columnas, "Iva", "IVA", Constantes.TARIFAS_IVAS_PERMITIDOS, numero, resultado);
        BigDecimal impoconsumo = tarifa(hoja, fila, columnas, "Impoconsumo", "impoconsumo", Constantes.TARIFAS_IMPOCONSUMO_PERMITIDOS, numero, resultado);
        BigDecimal descuento = porcentaje(hoja, fila, columnas, "Descuento", "el descuento", numero, resultado);
        String medida = medidaValida(hoja, fila, columnas, numero, resultado);
        if (resultado.getErrores().size() > erroresAntes) {
            return;
        }

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

        if (resultado.getErrores().size() > erroresAntes) {
            return;
        }

        String codigoProducto = linea.getProducto().getCodigo();
        if (costo != null) {
            if (linea.getCostoUnidad() == null) {
                linea.setCostoUnidad(costo);
            } else if (linea.getCostoUnidad().compareTo(costo) != 0) {
                resultado.getAvisos().add("Fila " + numero + ": el producto " + codigoProducto + " tiene un costo distinto (" + costo.toPlainString()
                        + "); se usa el del primer registro (" + linea.getCostoUnidad().toPlainString() + ")");
            }
        }
        if (iva != null) {
            if (linea.getIva() == null) {
                linea.setIva(iva);
            } else if (linea.getIva().compareTo(iva) != 0) {
                resultado.getAvisos().add("Fila " + numero + ": el producto " + codigoProducto + " tiene un IVA distinto (" + iva.toPlainString()
                        + "); se usa el del primer registro (" + linea.getIva().toPlainString() + ")");
            }
        }
        if (impoconsumo != null) {
            if (linea.getImpoconsumo() == null) {
                linea.setImpoconsumo(impoconsumo);
            } else if (linea.getImpoconsumo().compareTo(impoconsumo) != 0) {
                resultado.getAvisos().add("Fila " + numero + ": el producto " + codigoProducto + " tiene un impoconsumo distinto (" + impoconsumo.toPlainString()
                        + "); se usa el del primer registro (" + linea.getImpoconsumo().toPlainString() + ")");
            }
        }
        if (descuento != null) {
            if (linea.getDescuento() == null) {
                linea.setDescuento(descuento);
            } else if (linea.getDescuento().compareTo(descuento) != 0) {
                resultado.getAvisos().add("Fila " + numero + ": el producto " + codigoProducto + " tiene un descuento distinto (" + descuento.toPlainString()
                        + "%); se usa el del primer registro (" + linea.getDescuento().toPlainString() + "%)");
            }
        }
        if (!medida.isEmpty()) {
            if (linea.getMedida() == null) {
                linea.setMedida(medida);
            } else if (!linea.getMedida().equals(medida)) {
                resultado.getAvisos().add("Fila " + numero + ": el producto " + codigoProducto + " tiene una medida distinta (" + medida
                        + "); se usa la del primer registro (" + linea.getMedida() + ")");
            }
        }
    }

    private LineaImportacionCompra obtenerLinea(String codigo, int numero, Set<String> idsYaCargados, ResultadoImportacionCompra resultado) {
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
        LineaImportacionCompra existente = lineasPorProducto.get(id);
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
                motivo = "el producto " + codigo + " ya está cargado en la compra";
            }
        } catch (SQLException ex) {
            motivo = "no se pudo consultar el producto " + codigo + ": " + ex.getMessage();
        }

        if (motivo != null) {
            productosRechazados.add(id);
            resultado.getErrores().add("Fila " + numero + ": " + motivo);
            return null;
        }

        LineaImportacionCompra linea = new LineaImportacionCompra(producto,
                productosDetallados ? DetalleTipoProducto.obtenerTipoProducto(producto.getTipoProducto()) : "");
        lineasPorProducto.put(id, linea);
        return linea;
    }

    private void procesarSinTipo(HojaExcel hoja, int fila, Map<String, Integer> columnas, LineaImportacionCompra linea, int numero, ResultadoImportacionCompra resultado) {
        BigDecimal cantidad = cantidadValida(hoja, fila, columnas, numero, resultado);
        if (cantidad == null) {
            return;
        }

        if (linea.getCantidad().signum() > 0) {
            resultado.getAvisos().add("Fila " + numero + ": el producto " + linea.getProducto().getCodigo()
                    + " está repetido, se combinó sumando la cantidad (" + cantidad.toPlainString() + ")");
        }

        linea.sumarCantidad(cantidad);
    }

    private void procesarSerialOImei(HojaExcel hoja, int fila, Map<String, Integer> columnas, LineaImportacionCompra linea, int numero, ResultadoImportacionCompra resultado) {
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
                    + " está repetido en el archivo o ya está cargado en la compra");
            return;
        }

        if (existeEnInventario(identificador)) {
            resultado.getErrores().add("Fila " + numero + ": el " + etiqueta + " " + identificador + " ya existe en el inventario");
            return;
        }

        String color = "";
        if (!DetalleTipoProducto.SERIAL.getValue().equals(tipo)) {
            color = textoRequerido(hoja, fila, columnas, "Color", "el color", numero, resultado);
            if (color == null) {
                return;
            }
        }

        linea.getDetalles().add(new Object[]{linea.getProducto().getIdSistema(), identificador, "", "", "", BigDecimal.ONE,
            linea.getProducto().getDescripcion(), color, ""});
        linea.sumarCantidad(BigDecimal.ONE);
    }

    private void procesarColorOTalla(HojaExcel hoja, int fila, Map<String, Integer> columnas, LineaImportacionCompra linea, int numero, ResultadoImportacionCompra resultado) {
        String tipo = linea.getTipo();

        String color = "";
        if (!DetalleTipoProducto.TALLA.getValue().equals(tipo)) {
            color = textoRequerido(hoja, fila, columnas, "Color", "el color", numero, resultado);
            if (color == null) {
                return;
            }
        }

        String talla = "";
        if (!DetalleTipoProducto.COLOR.getValue().equals(tipo)) {
            talla = textoRequerido(hoja, fila, columnas, "Talla", "la talla", numero, resultado);
            if (talla == null) {
                return;
            }
        }

        BigDecimal cantidad = cantidadValida(hoja, fila, columnas, numero, resultado);
        if (cantidad == null) {
            return;
        }

        for (Object[] existente : linea.getDetalles()) {
            if (existente[7].equals(color) && existente[8].equals(talla)) {
                existente[5] = ((BigDecimal) existente[5]).add(cantidad);
                linea.sumarCantidad(cantidad);
                resultado.getAvisos().add("Fila " + numero + ": el producto " + linea.getProducto().getCodigo()
                        + " con la misma variante está repetido, se combinó sumando la cantidad");
                return;
            }
        }

        linea.getDetalles().add(new Object[]{linea.getProducto().getIdSistema(), "", "", "", "", cantidad,
            linea.getProducto().getDescripcion(), color, talla});
        linea.sumarCantidad(cantidad);
    }

    private void procesarLote(HojaExcel hoja, int fila, Map<String, Integer> columnas, LineaImportacionCompra linea, int numero, ResultadoImportacionCompra resultado) {
        BigDecimal cantidad = cantidadValida(hoja, fila, columnas, numero, resultado);
        if (cantidad == null) {
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

    private void descartarLineasVacias(ResultadoImportacionCompra resultado) {
        Iterator<LineaImportacionCompra> it = lineasPorProducto.values().iterator();
        while (it.hasNext()) {
            LineaImportacionCompra linea = it.next();
            if (linea.getCantidad().signum() <= 0) {
                resultado.getErrores().add("El producto " + linea.getProducto().getCodigo() + " no se cargó porque ninguna de sus filas es válida");
                it.remove();
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

    private BigDecimal cantidadValida(HojaExcel hoja, int fila, Map<String, Integer> columnas, int numero, ResultadoImportacionCompra resultado) {
        int erroresAntes = resultado.getErrores().size();
        BigDecimal cantidad = decimal(hoja, fila, columnas, "Cantidad", numero, resultado);
        if (resultado.getErrores().size() > erroresAntes) {
            return null;
        }
        if (cantidad == null || cantidad.signum() <= 0) {
            resultado.getErrores().add("Fila " + numero + ": ingrese una cantidad mayor a 0");
            return null;
        }
        return cantidad;
    }

    private BigDecimal porcentaje(HojaExcel hoja, int fila, Map<String, Integer> columnas, String columna, String etiqueta, int numero, ResultadoImportacionCompra resultado) {
        BigDecimal valor = decimal(hoja, fila, columnas, columna, numero, resultado);
        if (valor != null && (valor.signum() < 0 || valor.compareTo(CIEN) > 0)) {
            resultado.getErrores().add("Fila " + numero + ": " + etiqueta + " debe estar entre 0 y 100");
            return null;
        }
        return valor;
    }

    private BigDecimal tarifa(HojaExcel hoja, int fila, Map<String, Integer> columnas, String columna, String etiqueta, List<Integer> permitidas, int numero, ResultadoImportacionCompra resultado) {
        BigDecimal valor = decimal(hoja, fila, columnas, columna, numero, resultado);
        if (valor == null) {
            return null;
        }
        if (valor.stripTrailingZeros().scale() > 0 || !permitidas.contains(valor.intValue())) {
            resultado.getErrores().add("Fila " + numero + ": " + etiqueta + " no válido (" + valor.stripTrailingZeros().toPlainString() + "). Permitidos: " + permitidas);
            return null;
        }
        return BigDecimal.valueOf(valor.intValue());
    }

    private String textoRequerido(HojaExcel hoja, int fila, Map<String, Integer> columnas, String columna, String etiqueta, int numero, ResultadoImportacionCompra resultado) {
        String valor = texto(hoja, fila, columnas, columna).toUpperCase();
        if (valor.isEmpty()) {
            resultado.getErrores().add("Fila " + numero + ": ingrese " + etiqueta);
            return null;
        }
        return valor;
    }

    private String texto(HojaExcel hoja, int fila, Map<String, Integer> columnas, String columna) {
        Integer indice = columnas.get(columna);
        if (indice == null) {
            return "";
        }

        Double valor = hoja.numero(indice, fila);
        if (valor != null) {
            return new BigDecimal(valor).toBigInteger().toString();
        }

        return hoja.contenido(indice, fila).trim();
    }

    private boolean cargarUnidadesMedida(ResultadoImportacionCompra resultado) {
        try {
            for (String unidad : daoProducto.obtenerNombresUnidadesMedida()) {
                unidadesMedida.putIfAbsent(unidad.trim().toUpperCase(), unidad);
            }
            return true;
        } catch (SQLException ex) {
            resultado.getErrores().add("No se pudieron consultar las unidades de medida: " + ex.getMessage());
            return false;
        }
    }

    private String medidaValida(HojaExcel hoja, int fila, Map<String, Integer> columnas, int numero, ResultadoImportacionCompra resultado) {
        Integer indice = columnas.get("Medida");
        String medida = indice == null ? "" : hoja.contenido(indice, fila).trim();
        if (medida.isEmpty()) {
            return "";
        }

        String unidad = unidadesMedida.get(medida.toUpperCase());
        if (unidad == null) {
            resultado.getErrores().add("Fila " + numero + ": la medida '" + medida + "' no existe en las unidades de medida del sistema");
            return null;
        }
        return unidad;
    }

    private BigDecimal decimal(HojaExcel hoja, int fila, Map<String, Integer> columnas, String columna, int numero, ResultadoImportacionCompra resultado) {
        Integer indice = columnas.get(columna);
        if (indice == null || hoja.contenido(indice, fila).trim().isEmpty()) {
            return null;
        }

        Double valor = hoja.numero(indice, fila);
        if (valor != null) {
            return BigDecimal.valueOf(valor);
        }

        String contenido = hoja.contenido(indice, fila).trim();
        if (!contenido.matches("[-+]?[0-9.,]+")) {
            resultado.getErrores().add("Fila " + numero + ": '" + contenido + "' no es un número válido en " + columna);
            return null;
        }

        return Utilidades.convertirBigDecimal(contenido);
    }

    private LocalDate fecha(HojaExcel hoja, int fila, Map<String, Integer> columnas, String columna) {
        Integer indice = columnas.get(columna);
        if (indice == null || hoja.contenido(indice, fila).trim().isEmpty()) {
            return null;
        }

        LocalDate valor = hoja.fecha(indice, fila);
        if (valor != null) {
            return valor;
        }

        return Utilidades.convertirFecha(hoja.contenido(indice, fila).trim());
    }

    private boolean filaVacia(HojaExcel hoja, int fila) {
        for (int columna = 0; columna < hoja.columnas(); columna++) {
            if (!hoja.contenido(columna, fila).trim().isEmpty()) {
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
