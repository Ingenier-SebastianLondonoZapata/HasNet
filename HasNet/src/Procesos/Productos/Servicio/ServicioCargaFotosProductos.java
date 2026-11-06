package Procesos.Productos.Servicio;

import Dao.Productos.DaoProducto;
import Enums.EstadoCargaFoto;
import Modelo.Productos.ProductoImagen;
import Modelo.Productos.ResultadoCargaFoto;
import clases.metodosGenerales;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ServicioCargaFotosProductos {

    private static final List<String> EXTENSIONES = Arrays.asList("jpg", "jpeg", "png", "gif", "bmp");

    private final DaoProducto daoProducto = new DaoProducto();
    private final metodosGenerales metodos = new metodosGenerales();

    public static String[] extensionesPermitidas() {
        return EXTENSIONES.toArray(new String[0]);
    }

    public List<File> listarImagenes(File carpeta, boolean incluirSubcarpetas) throws IOException {
        int profundidad = incluirSubcarpetas ? Integer.MAX_VALUE : 1;

        try (Stream<Path> rutas = Files.walk(carpeta.toPath(), profundidad)) {
            return rutas.filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .filter(this::esImagen)
                    .sorted(Comparator.comparing(File::getName, String.CASE_INSENSITIVE_ORDER))
                    .collect(Collectors.toList());
        }
    }

    public Map<String, ProductoImagen> obtenerIndiceProductos() throws SQLException {
        List<ProductoImagen> productos = daoProducto.obtenerProductosParaImagenes();
        Map<String, ProductoImagen> indice = new HashMap<>();

        for (ProductoImagen producto : productos) {
            registrar(indice, producto.getIdSistema(), producto);
        }
        for (ProductoImagen producto : productos) {
            registrar(indice, producto.getCodigoBarras(), producto);
        }
        for (ProductoImagen producto : productos) {
            registrar(indice, producto.getCodigo(), producto);
        }

        return indice;
    }

    public ResultadoCargaFoto cargar(File archivo, Map<String, ProductoImagen> indice, Set<String> productosCargados, boolean reemplazar) {
        String clave = nombreSinExtension(archivo.getName());
        ProductoImagen producto = indice.get(normalizar(clave));

        if (producto == null) {
            return new ResultadoCargaFoto(archivo.getName(), clave, "", EstadoCargaFoto.PRODUCTO_NO_ENCONTRADO);
        }

        if (!productosCargados.add(producto.getIdSistema())) {
            return resultado(archivo, producto, EstadoCargaFoto.DUPLICADA);
        }

        File destino = rutaFoto(producto.getIdSistema());
        boolean existia = destino.exists();

        if (existia && !reemplazar) {
            return resultado(archivo, producto, EstadoCargaFoto.OMITIDA_EXISTENTE);
        }

        try {
            metodos.montarImagenOriginal(archivo.getAbsolutePath(), destino.getAbsolutePath());
        } catch (Exception e) {
            productosCargados.remove(producto.getIdSistema());
            return resultado(archivo, producto, EstadoCargaFoto.ERROR_GUARDADO);
        }

        return resultado(archivo, producto, existia ? EstadoCargaFoto.REEMPLAZADA : EstadoCargaFoto.CARGADA);
    }

    public File rutaFoto(String idSistema) {
        return Paths.get(System.getProperty("user.dir"), "imagenes", "productos", idSistema + ".jpg").toFile();
    }

    private boolean esImagen(File archivo) {
        String nombre = archivo.getName();
        int punto = nombre.lastIndexOf('.');
        return punto > 0 && EXTENSIONES.contains(nombre.substring(punto + 1).toLowerCase());
    }

    private ResultadoCargaFoto resultado(File archivo, ProductoImagen producto, EstadoCargaFoto estado) {
        return new ResultadoCargaFoto(archivo.getName(), producto.getCodigo(), producto.getDescripcion(), estado);
    }

    private void registrar(Map<String, ProductoImagen> indice, String clave, ProductoImagen producto) {
        if (clave != null && !clave.trim().isEmpty()) {
            indice.put(normalizar(clave), producto);
        }
    }

    private String nombreSinExtension(String nombre) {
        int punto = nombre.lastIndexOf('.');
        return punto > 0 ? nombre.substring(0, punto) : nombre;
    }

    private String normalizar(String valor) {
        return valor.trim().toUpperCase();
    }
}
