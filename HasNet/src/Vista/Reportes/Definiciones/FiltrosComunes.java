package Vista.Reportes.Definiciones;

import Dao.InicioSesion.DaoInicioSesion;
import Dao.Documentos.ConsultaReporteImpuestos;
import Dao.Productos.DaoProducto;
import Vista.Reportes.Filtros.FiltroOpcion;
import Dao.Terceros.DaoBodegas;
import Modelo.Contactos.ModeloBodega;
import Modelo.Contactos.ModeloContacto;
import Modelo.InicioSesion.Terminal;
import Modelo.Productos.ModeloProducto;
import Modelo.Reportes.OpcionFiltro;
import Utilidades.Contactos.Bodegas;
import Vista.Buscadores.BuscadoresCatalogo;
import Vista.Buscadores.VistaBuscadorCatalogo;
import Vista.Contactos.VistaBuscadorContactos;
import Vista.Productos.VistaBuscadorProductos;
import Vista.Reportes.Filtros.FiltroBusqueda;
import Vista.Reportes.Filtros.FiltroLista;
import Vista.Reportes.Filtros.FiltroReporte;
import clases.Instancias;
import clases.productos.ndGrupo;
import formularios.terceros.buscBodegas;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public final class FiltrosComunes {

    public static final String CLIENTE = "cliente";
    public static final String PROVEEDOR = "proveedor";
    public static final String PRODUCTO = "producto";
    public static final String GRUPO = "grupo";
    public static final String BODEGA = "bodega";
    public static final String VENDEDOR = "vendedor";
    public static final String TERMINAL = "terminal";
    public static final String PLACA = "placa";
    public static final String COLOR = "color";
    public static final String USUARIO = "usuario";

    private FiltrosComunes() {
    }

    private static Instancias instancias() {
        return Instancias.getInstancias();
    }

    public static boolean esAdministrador() {
        return "ADMIN".equals(instancias().getUsuario());
    }

    public static FiltroBusqueda tercero(String id, String etiqueta, boolean buscarInactivos) {
        return new FiltroBusqueda(id, etiqueta, FiltrosComunes::resolverTercero, buscadorContactos(buscarInactivos));
    }

    public static FiltroBusqueda terceroPorNit(String id, String etiqueta) {
        return new FiltroBusqueda(id, etiqueta, texto -> {
            ModeloContacto contacto = instancias().getSql().getDatosTercero(texto);
            if (contacto == null || contacto.getId() == null) {
                return null;
            }
            return new OpcionFiltro(textoVisible(contacto.getNombre(), contacto.getId()), contacto.getId());
        }, buscadorContactos(true));
    }

    private static FiltroBusqueda.Buscador buscadorContactos(boolean buscarInactivos) {
        return (campo, texto) -> {
            VistaBuscadorContactos buscador = new VistaBuscadorContactos(instancias().getMenu(), true, buscarInactivos, null, "");
            buscador.setLocationRelativeTo(null);
            instancias().setBusClientes(buscador);
            instancias().setCampoActual(campo);
            buscador.noEncontrado(texto);
            buscador.setVisible(true);
        };
    }

    public static FiltroBusqueda cliente() {
        return tercero(CLIENTE, "Cliente", false);
    }

    public static FiltroBusqueda proveedor() {
        return tercero(PROVEEDOR, "Proveedor", true);
    }

    private static OpcionFiltro resolverTercero(String texto) {
        ModeloContacto contacto = instancias().getSql().getDatosTercero(texto);
        if (contacto == null || contacto.getId() == null) {
            return null;
        }
        return new OpcionFiltro(textoVisible(contacto.getNombre(), contacto.getIdSistema()), contacto.getIdSistema());
    }

    public static FiltroBusqueda producto() {
        DaoProducto daoProducto = new DaoProducto();
        return new FiltroBusqueda(PRODUCTO, "Producto", texto -> {
            ModeloProducto producto = daoProducto.obtenerDatosProducto(texto, Bodegas.predeterminada());
            if (producto == null || producto.getIdSistema() == null) {
                return null;
            }
            return new OpcionFiltro(textoVisible(producto.getDescripcion(), producto.getCodigo()), producto.getIdSistema());
        }, (campo, texto, agregarVarios) -> {
            VistaBuscadorProductos buscador = new VistaBuscadorProductos(instancias().getMenu(), true, false, "");
            buscador.setLocationRelativeTo(null);
            instancias().setBusProductos(buscador);
            instancias().setCampoActual(campo);
            buscador.setAlSeleccionarVarios(agregarVarios);
            buscador.noEncontrado(texto);
            buscador.setVisible(true);
        });
    }

    public static FiltroBusqueda grupo() {
        return new FiltroBusqueda(GRUPO, "Grupo", texto -> {
            ndGrupo grupo = instancias().getSql().getDatosGrupo2(texto.replace("'", "//"));
            if (grupo == null || grupo.getCodigo() == null) {
                return null;
            }
            return new OpcionFiltro(textoVisible(grupo.getNombre(), grupo.getCodigo()), grupo.getCodigo());
        }, (campo, texto) -> abrirCatalogo(BuscadoresCatalogo.grupos(instancias().getMenu(), true, true), campo, texto));
    }

    public static FiltroBusqueda color() {
        return new FiltroBusqueda(COLOR, "Color", texto -> OpcionFiltro.de(texto.trim()),
                (campo, texto) -> abrirCatalogo(BuscadoresCatalogo.colores(instancias().getMenu(), true), campo, texto));
    }

    public static FiltroBusqueda placa() {
        return new FiltroBusqueda(PLACA, "Placa", texto -> OpcionFiltro.de(texto.trim().toUpperCase()),
                (campo, texto) -> abrirCatalogo(BuscadoresCatalogo.placas(instancias().getMenu(), true, "", false), campo, texto));
    }

    private static void abrirCatalogo(VistaBuscadorCatalogo buscador, javax.swing.JTextField campo, String texto) {
        buscador.setLocationRelativeTo(null);
        instancias().setCampoActual(campo);
        buscador.noEncontrado(texto);
        buscador.setVisible(true);
    }

    public static FiltroReporte bodega() {
        return new FiltroBusqueda(BODEGA, "Bodega", FiltrosComunes::resolverBodega, (campo, texto) -> {
            buscBodegas buscador = new buscBodegas(instancias().getMenu(), true, "INTERNA");
            buscador.setLocationRelativeTo(null);
            instancias().setBuscBodegas(buscador);
            instancias().setCampoActual(campo);
            buscador.noEncontrado(texto);
            buscador.setVisible(true);
        }).visibleSi(() -> instancias().getConfiguraciones().isInventarioBodegas());
    }

    private static OpcionFiltro resolverBodega(String texto) {
        String buscado = texto.trim();
        for (ModeloBodega bodega : new DaoBodegas().listarInternas()) {
            if (buscado.equalsIgnoreCase(bodega.getCodigo()) || buscado.equalsIgnoreCase(bodega.getNombre())) {
                return new OpcionFiltro(textoVisible(bodega.getNombre(), bodega.getCodigo()), bodega.getCodigo());
            }
        }
        return null;
    }

    private static String textoVisible(String descripcion, String respaldo) {
        return descripcion == null || descripcion.trim().isEmpty() ? respaldo : descripcion.trim();
    }

    public static FiltroReporte terminal() {
        return new FiltroLista(TERMINAL, "Terminal", () -> {
            List<OpcionFiltro> opciones = new ArrayList<>();
            for (Terminal terminal : new DaoInicioSesion().obtenerTerminales()) {
                opciones.add(OpcionFiltro.de(terminal.getTerminal()));
            }
            return opciones;
        }).visibleSi(FiltrosComunes::esAdministrador)
                .siOculto(() -> Collections.singletonList(OpcionFiltro.de(instancias().getTerminal())));
    }

    public static FiltroLista vendedor() {
        return new FiltroLista(VENDEDOR, "Vendedor", () -> opcionesDeTexto(instancias().getVendedores()));
    }

    public static FiltroLista vendedorYDomiciliario() {
        return new FiltroLista(VENDEDOR, "Vendedor", () -> {
            Set<String> nombres = new LinkedHashSet<>();
            agregarPrimeraColumna(nombres, instancias().getSql().getVendedores());
            agregarPrimeraColumna(nombres, instancias().getSql().getDomiciliarios());
            return opcionesDeTexto(nombres.toArray(new String[0]));
        });
    }

    public static FiltroOpcion impuestoReporte() {
        return FiltroOpcion.de(ConsultaReporteImpuestos.IMPUESTO, "Impuesto",
                new OpcionFiltro("IVA e impoconsumo", ConsultaReporteImpuestos.AMBOS),
                new OpcionFiltro("Solo IVA", ConsultaReporteImpuestos.IVA),
                new OpcionFiltro("Solo impoconsumo", ConsultaReporteImpuestos.IMPOCONSUMO));
    }

    public static FiltroLista usuario() {
        return new FiltroLista(USUARIO, "Usuario", () -> {
            Set<String> nombres = new LinkedHashSet<>();
            agregarPrimeraColumna(nombres, instancias().getSql().getUsuarios());
            return opcionesDeTexto(nombres.toArray(new String[0]));
        });
    }

    private static void agregarPrimeraColumna(Set<String> destino, Object[][] filas) {
        if (filas == null) {
            return;
        }
        for (Object[] fila : filas) {
            if (fila.length > 0 && fila[0] != null) {
                destino.add(fila[0].toString());
            }
        }
    }

    public static List<OpcionFiltro> opcionesDeTexto(String[] valores) {
        List<OpcionFiltro> opciones = new ArrayList<>();
        if (valores == null) {
            return opciones;
        }
        for (String valor : valores) {
            if (valor != null && !valor.trim().isEmpty()) {
                opciones.add(OpcionFiltro.de(valor.trim()));
            }
        }
        return opciones;
    }
}
