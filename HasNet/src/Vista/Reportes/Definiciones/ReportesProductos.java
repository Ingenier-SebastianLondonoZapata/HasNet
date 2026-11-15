package Vista.Reportes.Definiciones;

import Dao.Terceros.DaoBodegas;
import Impresiones.ImpresionesReportes.GeneradorReporteImpuestos;
import Modelo.Contactos.ModeloBodega;
import Modelo.Inventario.FiltroReporteInventario;
import Modelo.Reportes.CondicionSql;
import Modelo.Reportes.OpcionFiltro;
import Modelo.Reportes.SeleccionReporte;
import Procesos.Inventario.Dao.DaoDetalleProducto;
import Procesos.Inventario.Dao.DaoInventario;
import Utilidades.Contactos.Bodegas;
import Vista.Configuraciones.vistaSuperMaestra;
import Vista.Reportes.DefinicionReporte;
import Vista.Reportes.EntradaReporte;
import Vista.Reportes.Filtros.FiltroBusqueda;
import Vista.Reportes.Filtros.FiltroCasillas;
import Vista.Reportes.Filtros.FiltroLista;
import Vista.Reportes.Filtros.FiltroOpcion;
import Vista.Reportes.Filtros.FiltroRango;
import clases.Instancias;
import clases.ndUsuario;
import formularios.productos.infMovimientosSeriales;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class ReportesProductos {

    private static final String ESTADO = "estadoRegistro";
    private static final String NO_ANULADOS = "noAnulados";
    private static final String ANULADOS = "anulados";
    private static final String TODOS = "todos";
    private static final String ESTADO_PRODUCTO = "estadoProducto";
    private static final String ORDEN = "orden";
    private static final String TIPO_INGRESO = "tipoIngreso";
    private static final String TIPO_AJUSTE = "tipoAjuste";
    private static final String ESTADO_INVENTARIO = "estadoInventario";
    private static final String RANGO_INVENTARIO = "rangoInventario";
    private static final String SERIAL = "serial";

    private ReportesProductos() {
    }

    private static Instancias instancias() {
        return Instancias.getInstancias();
    }

    private static ndUsuario usuario() {
        return instancias().getUsuarioLog();
    }

    private static vistaSuperMaestra configuracion() {
        return instancias().getConfiguraciones();
    }

    public static List<EntradaReporte> entradas() {
        return Arrays.asList(
                EntradaReporte.de(() -> usuario().isReporteProductos(), ReportesProductos::productos),
                EntradaReporte.de(() -> usuario().isReporteCompras(), ReportesProductos::compras),
                EntradaReporte.de(() -> usuario().isReporteAjustes(), ReportesProductos::ajustes),
                EntradaReporte.de(() -> usuario().isReporteOrdenCompras(), ReportesProductos::ordenesCompra),
                EntradaReporte.de(() -> usuario().isInventarioInicial(), ReportesProductos::inventario),
                EntradaReporte.de(() -> usuario().isReporteIvasCompras(), ReportesProductos::impuestosCompras),
                EntradaReporte.panel("Reporte de movimientos por serial", () -> usuario().isReporteMovimientoSerial(), () -> {
                    infMovimientosSeriales panel = new infMovimientosSeriales();
                    instancias().setMovimientosSeriales(panel);
                    return panel;
                }));
    }

    private static FiltroOpcion estadoRegistro(boolean conTodos) {
        List<OpcionFiltro> opciones = new ArrayList<>();
        opciones.add(new OpcionFiltro("Realizados", NO_ANULADOS));
        opciones.add(new OpcionFiltro("Anulados", ANULADOS));
        if (conTodos) {
            opciones.add(new OpcionFiltro("Todos", TODOS));
        }
        return FiltroOpcion.de(ESTADO, "Estado", opciones.toArray(new OpcionFiltro[0]));
    }

    private static CondicionSql conEstado(CondicionSql condicion, SeleccionReporte seleccion) {
        String estado = seleccion.valor(ESTADO);
        return condicion
                .ySi(NO_ANULADOS.equals(estado), "anulada = false")
                .ySi(ANULADOS.equals(estado), "anulada = true");
    }

    private static String encabezado(SeleccionReporte seleccion, String sujeto) {
        return seleccion.encabezadoFechas(ANULADOS.equals(seleccion.valor(ESTADO)) ? sujeto + " ANULADOS" : sujeto);
    }

    public static DefinicionReporte productos() {
        return new DefinicionReporte("Reporte de productos")
                .sinFechas()
                .filtro(FiltrosComunes.grupo())
                .filtro(FiltrosComunes.proveedor())
                .filtro(FiltrosComunes.producto())
                .filtro(FiltroLista.de(ESTADO_PRODUCTO, "Estado", new OpcionFiltro("Activos", "false"), new OpcionFiltro("Inactivos", "true")))
                .filtro(FiltroOpcion.de(ORDEN, "Ordenar por", new OpcionFiltro("Código", "Codigo"), new OpcionFiltro("Descripción", "Descripcion"),
                        new OpcionFiltro("Grupo", "GrupoDesc"), new OpcionFiltro("Subgrupo", "SubGrupoDesc"), new OpcionFiltro("Proveedor", "nombreProveedor"))
                        .porDefecto(1))
                .accion("Generar", "Reporte de productos", s -> {
                    String condicion = new CondicionSql()
                            .en("Grupo", s.valores(FiltrosComunes.GRUPO))
                            .en("Proveedor", s.valores(FiltrosComunes.PROVEEDOR))
                            .en("idSistema", s.valores(FiltrosComunes.PRODUCTO))
                            .alguno(s.valores(ESTADO_PRODUCTO), valor -> "activo = " + valor)
                            .y("codigo <> ''")
                            .where();
                    instancias().getReporte().ver_RepProductos(condicion + " ORDER BY " + s.valor(ORDEN) + " ASC", "");
                });
    }

    public static DefinicionReporte compras() {
        return new DefinicionReporte("Reporte de compras")
                .filtro(estadoRegistro(true))
                .filtro(FiltroLista.deValores(TIPO_INGRESO, "Tipo de ingreso", "FACTURA", "DOC. EQUIVALENTE", "OTRO INGRESO"))
                .filtro(FiltrosComunes.proveedor())
                .filtro(FiltrosComunes.producto())
                .filtro(FiltrosComunes.grupo())
                .filtro(FiltrosComunes.terminal())
                .filtro(FiltrosComunes.bodega())
                .nota("El producto y el grupo solo aplican al reporte detallado.")
                .accion("Generar totalizado", "Reporte ingresos corto", s -> instancias().getReporte().ver_RepIngresos2(conEstado(new CondicionSql()
                .en("terminal", s.valores(FiltrosComunes.TERMINAL))
                .en("proveedor", s.valores(FiltrosComunes.PROVEEDOR))
                .en("bodega", s.valores(FiltrosComunes.BODEGA))
                .en("tipo", s.valores(TIPO_INGRESO))
                .entreFechas("fechaFactura", s.getFechaInicial(), s.getFechaFinal()), s)
                .where(), encabezado(s, "Ingresos")))
                .accion("Generar detallado", "Reporte ingresos", s -> instancias().getReporte().ver_ReIngresos(conEstado(new CondicionSql()
                .en("terminal", s.valores(FiltrosComunes.TERMINAL))
                .en("proveedor", s.valores(FiltrosComunes.PROVEEDOR))
                .en("producto", s.valores(FiltrosComunes.PRODUCTO))
                .en("Grupo", s.valores(FiltrosComunes.GRUPO))
                .en("tipo", s.valores(TIPO_INGRESO))
                .entreFechas("fechaFactura", s.getFechaInicial(), s.getFechaFinal()), s)
                .where(), encabezadoConGrupos(s, encabezado(s, "Ingresos"))));
    }

    private static String encabezadoConGrupos(SeleccionReporte seleccion, String encabezado) {
        if (!seleccion.tiene(FiltrosComunes.GRUPO)) {
            return encabezado;
        }
        List<String> grupos = new ArrayList<>();
        for (OpcionFiltro grupo : seleccion.opciones(FiltrosComunes.GRUPO)) {
            grupos.add(grupo.getEtiqueta());
        }
        return encabezado + ", Grupos: " + String.join(", ", grupos);
    }

    public static DefinicionReporte ajustes() {
        return new DefinicionReporte("Reporte de ajustes de inventario")
                .filtro(estadoRegistro(false))
                .filtro(FiltroLista.de(TIPO_AJUSTE, "Tipo de ajuste", new OpcionFiltro("Ajustes de entrada", "Ajustes Entrada"),
                        new OpcionFiltro("Ajustes de salida", "Ajuste Salida")))
                .filtro(FiltrosComunes.producto())
                .filtro(FiltrosComunes.terminal())
                .accion("Generar", "Reporte de ajustes", s -> instancias().getReporte().ver_RepAjuste(conEstado(new CondicionSql()
                .en("terminal", s.valores(FiltrosComunes.TERMINAL))
                .en("tipo", s.valores(TIPO_AJUSTE))
                .en("producto", s.valores(FiltrosComunes.PRODUCTO))
                .entreFechas("fecha", s.getFechaInicial(), s.getFechaFinal()), s)
                .where(), encabezado(s, "Ajustes"), s.tiene(TIPO_AJUSTE)));
    }

    public static DefinicionReporte ordenesCompra() {
        return new DefinicionReporte("Reporte de órdenes de compra")
                .filtro(estadoRegistro(false))
                .filtro(FiltrosComunes.proveedor())
                .filtro(FiltrosComunes.producto())
                .filtro(FiltrosComunes.grupo())
                .filtro(FiltrosComunes.terminal())
                .accion("Generar", "Reporte ordenes de compra", s -> instancias().getReporte().ver_RepOrdenCompra(conEstado(new CondicionSql()
                .en("terminal", s.valores(FiltrosComunes.TERMINAL))
                .en("proveedor", s.valores(FiltrosComunes.PROVEEDOR))
                .en("producto", s.valores(FiltrosComunes.PRODUCTO))
                .en("Grupo", s.valores(FiltrosComunes.GRUPO))
                .entreFechas("fechaFactura", s.getFechaInicial(), s.getFechaFinal()), s)
                .where(), encabezado(s, "Ordenes de compra")));
    }

    private static List<OpcionFiltro> bodegasInternas() {
        List<OpcionFiltro> opciones = new ArrayList<>();
        for (ModeloBodega bodega : new DaoBodegas().listarInternas()) {
            opciones.add(new OpcionFiltro(bodega.getNombre(), bodega.getCodigo()));
        }
        return opciones;
    }

    public static DefinicionReporte inventario() {
        boolean detallados = configuracion().isProductosDetallados();
        return new DefinicionReporte("Reporte de inventario")
                .etiquetaFechas("Fecha de compra")
                .filtro(new FiltroOpcion(FiltrosComunes.BODEGA, "Bodega", ReportesProductos::bodegasInternas)
                        .visibleSi(() -> configuracion().isInventarioBodegas())
                        .siOculto(() -> Collections.singletonList(OpcionFiltro.de(Bodegas.predeterminada()))))
                .filtro(new FiltroCasillas(ESTADO_INVENTARIO, "Estado del inventario",
                        new OpcionFiltro("Sin inventario", FiltroReporteInventario.ESTADO_SIN_INVENTARIO),
                        new OpcionFiltro("Bajo", FiltroReporteInventario.ESTADO_BAJO),
                        new OpcionFiltro("Medio", FiltroReporteInventario.ESTADO_MEDIO),
                        new OpcionFiltro("Alto", FiltroReporteInventario.ESTADO_ALTO)).todasMarcadas().requerido())
                .filtro(FiltroRango.numerico(RANGO_INVENTARIO, "Rango de inventario"))
                .filtro(FiltrosComunes.proveedor())
                .filtro(FiltrosComunes.producto())
                .filtro(FiltrosComunes.grupo())
                .filtro(FiltroBusqueda.textoLibre(SERIAL, "Serial").visibleSi(() -> detallados))
                .filtro(FiltrosComunes.color().visibleSi(() -> detallados))
                .accion("Generar general", "Reporte inventario de productos", s -> instancias().getReporte()
                .verHistorico(DaoInventario.consultaReporteInventario(filtroInventario(s))))
                .accionSi(detallados, "Generar detallado", "Reporte inventario de productos detallado", s -> instancias().getReporte()
                .verInventarioDetallado(DaoDetalleProducto.consultaReporteInventarioDetallado(filtroInventario(s))));
    }

    private static FiltroReporteInventario filtroInventario(SeleccionReporte seleccion) {
        FiltroReporteInventario filtro = new FiltroReporteInventario();
        filtro.setBodega(Bodegas.resolver(seleccion.valor(FiltrosComunes.BODEGA), configuracion().isInventarioBodegas()));
        for (String estado : seleccion.valores(ESTADO_INVENTARIO)) {
            filtro.agregarEstado(estado);
        }
        if (seleccion.tieneRango(RANGO_INVENTARIO)) {
            filtro.setRangoInventario(seleccion.rangoDesde(RANGO_INVENTARIO), seleccion.rangoHasta(RANGO_INVENTARIO));
        }
        if (seleccion.hayFechas()) {
            filtro.setRangoFechas(seleccion.getFechaInicial(), seleccion.getFechaFinal());
        }
        filtro.getProveedores().addAll(seleccion.valores(FiltrosComunes.PROVEEDOR));
        filtro.getProductos().addAll(seleccion.valores(FiltrosComunes.PRODUCTO));
        filtro.getGrupos().addAll(seleccion.valores(FiltrosComunes.GRUPO));
        filtro.getImeis().addAll(seleccion.valores(SERIAL));
        filtro.getColores().addAll(seleccion.valores(FiltrosComunes.COLOR));
        return filtro;
    }

    public static DefinicionReporte impuestosCompras() {
        return new DefinicionReporte("Reporte de impuestos de compra").fechasObligatorias()
                .nota("Compras no anuladas, agrupadas por impuesto y tarifa.")
                .filtro(FiltrosComunes.impuestoReporte())
                .accion("Generar", "Reporte de impuestos de compra", s -> new GeneradorReporteImpuestos(instancias()).compras(s));
    }
}
