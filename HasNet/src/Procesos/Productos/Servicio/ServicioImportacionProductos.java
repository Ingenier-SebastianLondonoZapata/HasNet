package Procesos.Productos.Servicio;

import Enums.ColumnaPlantillaProductos;
import Enums.DetalleTipoProducto;
import Enums.enumBodegas;
import Modelo.Inventario.DetalleProducto;
import Modelo.Inventario.LineaInventarioInicial;
import Modelo.Productos.FilaPlantillaProductos;
import Modelo.Productos.ModeloListaPrecio;
import Modelo.Productos.ModeloProducto;
import Modelo.Productos.PlantillaProductos;
import Modelo.Productos.ResultadoImportacionProductos;
import Procesos.Inventario.Servicio.ServicioActualizacionPonderado;
import Procesos.Inventario.Servicio.ServicioInventarioInicial;
import Utilidades.Constantes;
import Utilidades.ImportacionesExcel.LectorPlantillaProductos;
import clases.Instancias;
import Dao.Productos.DaoGrupo;
import Dao.Productos.DaoProducto;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServicioImportacionProductos {

    private static final Logger LOGGER = Logger.getLogger(ServicioImportacionProductos.class.getName());

    private static final int LONGITUD_DESCRIPCION = 255;
    private static final String TIPO_NORMAL = "Normal";

    private final Instancias instancias = Instancias.getInstancias();
    private final DaoProducto daoProducto = new DaoProducto();
    private final DaoGrupo daoGrupo = new DaoGrupo();
    private final ServicioInventarioInicial servicioInventarioInicial = new ServicioInventarioInicial();
    private final ServicioActualizacionPonderado servicioPonderado = new ServicioActualizacionPonderado();

    private Map<String, String> unidades;
    private Map<String, String> grupos;
    private List<String[]> subgrupos;

    public ResultadoImportacionProductos importar(File archivo) throws IOException, SQLException {
        PlantillaProductos plantilla = LectorPlantillaProductos.leer(archivo);
        ResultadoImportacionProductos resultado = new ResultadoImportacionProductos(plantilla.isConInventario());

        cargarCatalogos();

        Set<String> clavesVistas = new HashSet<>();
        for (FilaPlantillaProductos fila : plantilla.getFilas()) {
            resultado.registrarLeida();
            procesarFila(fila, clavesVistas, resultado);
        }

        return resultado;
    }

    private void cargarCatalogos() throws SQLException {
        unidades = new HashMap<>();
        for (String unidad : daoProducto.obtenerNombresUnidadesMedida()) {
            unidades.put(unidad.trim().toLowerCase(), unidad);
        }
        grupos = daoGrupo.obtenerCodigosGruposPorNombre();
        subgrupos = daoGrupo.obtenerSubgrupos();
    }

    private void procesarFila(FilaPlantillaProductos fila, Set<String> clavesVistas, ResultadoImportacionProductos resultado) {
        try {
            String codigo = texto(fila.getValor(ColumnaPlantillaProductos.CODIGO));
            String barras = texto(fila.getValor(ColumnaPlantillaProductos.CODIGO_BARRAS));

            if (codigo.isEmpty() && barras.isEmpty()) {
                resultado.agregarError(fila, "Debe ingresar el código o el código de barras");
                return;
            }

            String repetido = null;
            for (String clave : new String[]{codigo, barras}) {
                if (!clave.isEmpty() && !clavesVistas.add(clave.toLowerCase()) && repetido == null) {
                    repetido = clave;
                }
            }

            if (repetido != null) {
                resultado.agregarRepetido(fila, "El código " + repetido + " está repetido en el archivo; solo se guarda el primer registro");
                return;
            }

            String existente = productoExistente(codigo, barras);
            if (existente != null) {
                resultado.agregarRepetido(fila, existente);
                return;
            }

            DatosProducto datos = new DatosProducto();
            List<String> motivos = new ArrayList<>();
            validar(fila, resultado.isConInventario(), datos, motivos);
            if (!motivos.isEmpty()) {
                resultado.agregarError(fila, String.join("; ", motivos));
                return;
            }

            String codigoProducto = codigo.isEmpty() ? barras : codigo;
            String error = guardar(codigoProducto, barras, datos);
            if (error != null) {
                resultado.agregarError(fila, error);
                return;
            }

            resultado.registrarGuardado();
            if (datos.inventarioOmitido != null) {
                resultado.agregarAviso("Fila " + fila.getNumeroFila() + ": el producto " + codigoProducto + " es de tipo " + datos.tipo
                        + " y se creó sin inventario (traía " + datos.inventarioOmitido.stripTrailingZeros().toPlainString()
                        + "); cárguelo desde Inventario inicial");
            }
        } catch (RuntimeException | SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error al importar la fila " + fila.getNumeroFila(), ex);
            resultado.agregarError(fila, "Error inesperado: " + ex.getMessage());
        }
    }

    private String productoExistente(String codigo, String barras) {
        for (String valor : new String[]{codigo, barras}) {
            if (valor.isEmpty()) {
                continue;
            }

            ModeloProducto producto = daoProducto.obtenerDatosProducto(valor, enumBodegas.ID_BODEGA_PRINCIPAL);
            if (producto.getIdSistema() != null) {
                return "El código " + valor + " ya existe en el sistema (producto " + producto.getIdSistema() + ")";
            }
        }

        return null;
    }

    private static class DatosProducto {

        String descripcion;
        String grupo;
        String subgrupo;
        String unidad;
        String tipo;
        int iva;
        int impoconsumo;
        BigDecimal precio;
        BigDecimal inventario = BigDecimal.ZERO;
        BigDecimal costo = BigDecimal.ZERO;
        BigDecimal inventarioOmitido;
    }

    private void validar(FilaPlantillaProductos fila, boolean conInventario, DatosProducto datos, List<String> motivos) {
        datos.descripcion = texto(fila.getValor(ColumnaPlantillaProductos.DESCRIPCION));
        if (datos.descripcion.isEmpty()) {
            motivos.add("Falta la descripción");
        } else if (datos.descripcion.length() > LONGITUD_DESCRIPCION) {
            motivos.add("La descripción supera los " + LONGITUD_DESCRIPCION + " caracteres");
        }

        validarGrupo(texto(fila.getValor(ColumnaPlantillaProductos.GRUPO)), texto(fila.getValor(ColumnaPlantillaProductos.SUBGRUPO)), datos, motivos);

        String unidad = texto(fila.getValor(ColumnaPlantillaProductos.UNIDAD_MEDIDA));
        if (unidad.isEmpty()) {
            motivos.add("Falta la unidad de medida");
        } else if (!unidades.containsKey(unidad.toLowerCase())) {
            motivos.add("La unidad de medida '" + unidad + "' no existe");
        } else {
            datos.unidad = unidades.get(unidad.toLowerCase());
        }

        validarTipo(texto(fila.getValor(ColumnaPlantillaProductos.TIPO_PRODUCTO)), datos, motivos);

        datos.iva = porcentaje(fila.getValor(ColumnaPlantillaProductos.IVA), ColumnaPlantillaProductos.IVA.getEncabezado(),
                Constantes.TARIFAS_IVAS_PERMITIDOS, motivos);
        datos.impoconsumo = porcentaje(fila.getValor(ColumnaPlantillaProductos.IMPOCONSUMO), ColumnaPlantillaProductos.IMPOCONSUMO.getEncabezado(),
                Constantes.TARIFAS_IMPOCONSUMO_PERMITIDOS, motivos);

        BigDecimal precio = numero(fila.getValor(ColumnaPlantillaProductos.LISTA_PRECIO), ColumnaPlantillaProductos.LISTA_PRECIO.getEncabezado(), motivos);
        if (precio != null && precio.signum() <= 0) {
            motivos.add("La lista de precio debe ser mayor a 0");
        } else {
            datos.precio = precio;
        }

        if (conInventario) {
            validarInventario(fila, datos, motivos);
        }
    }

    private void validarGrupo(String grupo, String subgrupo, DatosProducto datos, List<String> motivos) {
        if (grupo.isEmpty()) {
            if (!subgrupo.isEmpty()) {
                motivos.add("Para usar un subgrupo debe indicar el grupo");
            }
            return;
        }

        datos.grupo = grupos.get(grupo.toLowerCase());
        if (datos.grupo == null) {
            motivos.add("El grupo '" + grupo + "' no existe");
            return;
        }

        if (subgrupo.isEmpty()) {
            return;
        }

        for (String[] candidato : subgrupos) {
            if (candidato[1].equals(subgrupo.toLowerCase()) && datos.grupo.equals(candidato[2])) {
                datos.subgrupo = candidato[0];
                return;
            }
        }

        motivos.add("El subgrupo '" + subgrupo + "' no existe en el grupo '" + grupo + "'");
    }

    private void validarTipo(String tipo, DatosProducto datos, List<String> motivos) {
        if (tipo.isEmpty()) {
            motivos.add("Falta el tipo de producto");
            return;
        }

        if (tipo.equalsIgnoreCase(TIPO_NORMAL)) {
            datos.tipo = "";
            return;
        }

        for (DetalleTipoProducto detalle : DetalleTipoProducto.values()) {
            if (detalle.getValue().equalsIgnoreCase(tipo)) {
                datos.tipo = detalle.getValue();
                return;
            }
        }

        motivos.add("El tipo de producto '" + tipo + "' no es válido");
    }

    private void validarInventario(FilaPlantillaProductos fila, DatosProducto datos, List<String> motivos) {
        BigDecimal inventario = numero(fila.getValor(ColumnaPlantillaProductos.INVENTARIO_INICIAL),
                ColumnaPlantillaProductos.INVENTARIO_INICIAL.getEncabezado(), motivos);
        BigDecimal costo = numero(fila.getValor(ColumnaPlantillaProductos.COSTO), ColumnaPlantillaProductos.COSTO.getEncabezado(), motivos);

        if (inventario != null && inventario.signum() < 0) {
            motivos.add("El inventario inicial no puede ser negativo");
        } else if (inventario != null) {
            datos.inventario = inventario;
        }

        if (costo != null && costo.signum() < 0) {
            motivos.add("El costo no puede ser negativo");
        } else if (costo != null) {
            datos.costo = costo;
        }

        if (inventario != null && costo != null && inventario.signum() > 0 && costo.signum() == 0) {
            motivos.add("Con inventario inicial el costo debe ser mayor a 0");
        }

        if (datos.inventario.signum() > 0 && datos.tipo != null && !datos.tipo.isEmpty()) {
            datos.inventarioOmitido = datos.inventario;
            datos.inventario = BigDecimal.ZERO;
        }
    }

    private String guardar(String codigo, String barras, DatosProducto datos) throws SQLException {
        int consecutivo = daoProducto.obtenerConsecutivoProducto();
        String idSistema = "PROD-" + String.format("%08d", consecutivo);

        ModeloProducto producto = armarProducto(idSistema, codigo, barras, datos);
        if (!daoProducto.agregarProducto(producto)) {
            return "No se pudo guardar el producto en la base de datos";
        }

        try {
            daoProducto.actualizarConsecutivoProducto(consecutivo + 1);
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error al actualizar el consecutivo de productos", ex);
            return "El producto " + idSistema + " se creó, pero no se pudo actualizar el consecutivo de productos";
        }

        try {
            servicioPonderado.crearPonderadoInicial(producto, datos.costo, instancias.getUsuario());

            if (datos.inventario.signum() > 0) {
                servicioInventarioInicial.guardar(Collections.singletonList(new LineaInventarioInicial(producto, datos.inventario, datos.costo)),
                        new ArrayList<DetalleProducto>());
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error al completar el producto " + idSistema, ex);
            return "El producto " + idSistema + " se creó, pero falló el costo o inventario inicial (" + ex.getMessage()
                    + "). Cárguelo desde Inventario inicial";
        }

        return null;
    }

    private ModeloProducto armarProducto(String idSistema, String codigo, String barras, DatosProducto datos) {
        ModeloProducto producto = new ModeloProducto();

        producto.setIdSistema(idSistema);
        producto.setCodigo(codigo.replace("'", "//"));
        producto.setCodigoBarras(barras);
        producto.setDescripcion(datos.descripcion);
        producto.setDescripcionLarga("");
        producto.setGrupo(datos.grupo);
        producto.setSubgrupo(datos.subgrupo);
        producto.setProveedor(null);
        producto.setUsuario("ADMIN");
        producto.setTipoProducto(datos.tipo);
        producto.setReferencia("");
        producto.setUnd(datos.unidad);
        producto.setMarca("");
        producto.setTipoProd("");
        producto.setPeso("");
        producto.setPorcentaje("");
        producto.setClaseBuscador(" ");
        producto.setManejaInventario(true);

        producto.setIva(String.valueOf(datos.iva));
        producto.setImpoconsumoVenta(String.valueOf(datos.impoconsumo));
        producto.setCosto(datos.costo.toPlainString());
        producto.setMinimo("0");
        producto.setMinima("20");
        producto.setMaxima("30");

        producto.setPonderado("0");
        producto.setTerminal(instancias.getTerminal());
        producto.setIndVentas("1");
        producto.setLenteMarco("");
        producto.setAdesivo("");
        producto.setColor("");
        producto.setEmpaque("");
        producto.setComposicion("");
        producto.setRmb("");
        producto.setCubicaje("");
        producto.setCodContable("");
        producto.setCodArancel("");
        producto.setCantMedida("");

        producto.setL1(datos.precio.toPlainString());
        producto.setUbicacion1("");
        producto.setListas(new ArrayList<ModeloListaPrecio>());
        return producto;
    }

    private static String texto(Object valor) {
        if (valor == null) {
            return "";
        }

        if (valor instanceof Double) {
            return BigDecimal.valueOf((Double) valor).stripTrailingZeros().toPlainString();
        }

        return valor.toString().trim();
    }

    private BigDecimal numero(Object valor, String campo, List<String> motivos) {
        if (valor == null) {
            motivos.add("Falta el campo " + campo);
            return null;
        }

        if (valor instanceof Double) {
            return BigDecimal.valueOf((Double) valor);
        }

        BigDecimal numero = convertir(valor.toString());
        if (numero == null) {
            motivos.add("'" + valor + "' no es un número válido en " + campo);
        }
        return numero;
    }

    private int porcentaje(Object valor, String campo, List<Integer> permitidos, List<String> motivos) {
        BigDecimal numero = numero(valor, campo, motivos);
        if (numero == null) {
            return 0;
        }

        if (numero.stripTrailingZeros().scale() > 0 || !permitidos.contains(numero.intValue())) {
            motivos.add(campo + " no válido (" + texto(valor) + "). Permitidos: " + permitidos);
            return 0;
        }

        return numero.intValue();
    }

    private static BigDecimal convertir(String contenido) {
        String limpio = contenido.replaceAll("[\\s$]", "");
        if (!limpio.matches("[-+]?[0-9.,]+")) {
            return null;
        }

        int punto = limpio.lastIndexOf('.');
        int coma = limpio.lastIndexOf(',');
        try {
            if (punto >= 0 && coma >= 0) {
                char decimal = punto > coma ? '.' : ',';
                char miles = decimal == '.' ? ',' : '.';
                limpio = limpio.replace(String.valueOf(miles), "").replace(decimal, '.');
            } else if (coma >= 0) {
                limpio = limpio.indexOf(',') == coma ? limpio.replace(',', '.') : limpio.replace(",", "");
            } else if (punto >= 0 && limpio.indexOf('.') != punto) {
                limpio = limpio.replace(".", "");
            }

            return new BigDecimal(limpio);
        } catch (NumberFormatException ex) {
            return null;
        }
    }
}
