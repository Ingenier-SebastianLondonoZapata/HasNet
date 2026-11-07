package Vista.Buscadores;

import Utilidades.BaseDatos.SQL;
import clases.Instancias;
import java.awt.Frame;

public final class BuscadoresCatalogo {

    private BuscadoresCatalogo() {
    }

    public static VistaBuscadorCatalogo grupos(Frame parent, boolean modal, boolean presionarEnterAlSeleccionar) {
        ConfiguracionBuscadorCatalogo configuracion = new ConfiguracionBuscadorCatalogo("GRUPOS", () -> sql().getRegistrosGrupos2())
                .filtro("Descripción", 1)
                .filtro("Codigo", 0)
                .anchoFijo(0, 90)
                .presionarEnterAlSeleccionar(presionarEnterAlSeleccionar);
        return new VistaBuscadorCatalogo(parent, modal, configuracion);
    }

    public static VistaBuscadorCatalogo subGrupos(Frame parent, boolean modal) {
        ConfiguracionBuscadorCatalogo configuracion = new ConfiguracionBuscadorCatalogo("Buscador de subgrupos...",
                () -> sql().getRegistrosSubGrupos(condicionGrupo()))
                .filtro("Descripción", 1)
                .filtro("Codigo", 0)
                .anchoFijo(0, 120);
        return new VistaBuscadorCatalogo(parent, modal, configuracion);
    }

    public static VistaBuscadorCatalogo procesos(Frame parent, boolean modal) {
        ConfiguracionBuscadorCatalogo configuracion = new ConfiguracionBuscadorCatalogo("PROCESOS", () -> sql().getRegistrosProcesos())
                .filtro("Nombre", 1)
                .filtro("Codigo", 0)
                .columnaFiltro(0)
                .columnaNoEncontrado(1);
        return new VistaBuscadorCatalogo(parent, modal, configuracion);
    }

    public static VistaBuscadorCatalogo medidas(Frame parent, boolean modal) {
        ConfiguracionBuscadorCatalogo configuracion = new ConfiguracionBuscadorCatalogo("Buscador de Medidas...",
                () -> sql().getRegistrosMedidas(facturaElectronica() ? " where detalle = 1; " : ""))
                .ocultarColumna(1);
        return new VistaBuscadorCatalogo(parent, modal, configuracion);
    }

    public static VistaBuscadorCatalogo colores(Frame parent, boolean modal) {
        ConfiguracionBuscadorCatalogo configuracion = new ConfiguracionBuscadorCatalogo("Buscador de colores...", () -> sql().getRegistrosColores())
                .registro(new RegistroCatalogo()
                        .enMayusculas()
                        .mensajeVacio("Ingrese el color")
                        .existe(valor -> sql().getDatosColores(valor) != null)
                        .mensajeExiste("El color ya existe")
                        .pregunta(valor -> "¿Desea agregar el color ?")
                        .guardar(valor -> sql().agregarColor(valor))
                        .mensajeError("Hubo un error al ingresar el color")
                        .mensajeExito("Color ingresado con exito"));
        return new VistaBuscadorCatalogo(parent, modal, configuracion);
    }

    public static VistaBuscadorCatalogo marcas(Frame parent, boolean modal) {
        ConfiguracionBuscadorCatalogo configuracion = new ConfiguracionBuscadorCatalogo("Buscador de marcas...", () -> sql().getRegistrosMarcas())
                .presionarEnterAlSeleccionar(false)
                .registro(new RegistroCatalogo()
                        .enMayusculas()
                        .mensajeVacio("Debe ingresar una marca")
                        .existe(valor -> sql().getDatosMarcas(valor) != null)
                        .pregunta(valor -> "¿Agregar marca?")
                        .guardar(valor -> sql().agregarMarca(valor))
                        .mensajeError("Hubo un error al ingresar la marca")
                        .mensajeExito("Marca ingresada con exito"));
        return new VistaBuscadorCatalogo(parent, modal, configuracion);
    }

    public static VistaBuscadorCatalogo tallas(Frame parent, boolean modal) {
        ConfiguracionBuscadorCatalogo configuracion = new ConfiguracionBuscadorCatalogo("Tallas...", () -> sql().getRegistrosTallas())
                .registro(new RegistroCatalogo()
                        .enMayusculas()
                        .mensajeVacio("Ingrese la talla")
                        .existe(valor -> sql().getDatosTalla(valor) != null)
                        .mensajeExiste("La talla ya existe")
                        .pregunta(valor -> "¿Agregar la talla como " + valor + "? ")
                        .guardar(valor -> sql().agregarTalla(valor))
                        .mensajeError("Hubo un error al ingresar la talla")
                        .mensajeExito("Talla ingresada con exito"));
        return new VistaBuscadorCatalogo(parent, modal, configuracion);
    }

    public static VistaBuscadorCatalogo tiposProductos(Frame parent, boolean modal) {
        ConfiguracionBuscadorCatalogo configuracion = new ConfiguracionBuscadorCatalogo("Buscador de tipos de productos...",
                () -> sql().getRegistrosTipoProducto())
                .registro(new RegistroCatalogo()
                        .mensajeVacio("Ingrese el tipo de producto")
                        .existe(valor -> sql().getDatosTipoProducto(valor) != null)
                        .pregunta(valor -> "¿Agregar tipo de producto?")
                        .guardar(valor -> sql().agregarTipoProd(valor))
                        .mensajeError("Hubo un error al guardar")
                        .mensajeExito("Tipo de producto ingresado con exito"));
        return new VistaBuscadorCatalogo(parent, modal, configuracion);
    }

    private static SQL sql() {
        return Instancias.getInstancias().getSql();
    }

    private static String condicionGrupo() {
        String grupo = Instancias.getInstancias().getValor();
        return grupo == null || grupo.isEmpty() ? "" : " where grupo = '" + grupo + "' ";
    }

    private static boolean facturaElectronica() {
        return Instancias.getInstancias().getConfiguraciones().isFacturaElectronica();
    }
}
