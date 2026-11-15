package Vista.Reportes.Definiciones;

import Impresiones.ImpresionesReportes.GeneradorReporteDocumentos;
import Impresiones.ImpresionesReportes.GeneradorReporteImpuestos;
import Impresiones.ImpresionesReportes.GeneradorReportesCalculados;
import Modelo.Reportes.CondicionSql;
import Modelo.Reportes.OpcionFiltro;
import Modelo.Reportes.SeleccionReporte;
import Vista.Configuraciones.vistaSuperMaestra;
import Vista.Reportes.DefinicionReporte;
import Vista.Reportes.EntradaReporte;
import Vista.Reportes.Filtros.FiltroLista;
import Vista.Reportes.Filtros.FiltroOpcion;
import Vista.Reportes.Filtros.FiltroRango;
import clases.Instancias;
import clases.ndUsuario;
import formularios.Ventas.infIndicadoresVentas;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static Dao.Documentos.ConsultaReporteDocumentos.*;

public final class ReportesVentas {

    private ReportesVentas() {
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
                EntradaReporte.de(ReportesVentas::puedeVerDocumentos, ReportesVentas::documentos),
                EntradaReporte.de(() -> usuario().isReporteIvasVentas(), ReportesVentas::impuestos),
                EntradaReporte.de(() -> usuario().isReporteDia(), ReportesVentas::resumenDia),
                EntradaReporte.de(() -> configuracion().isFacturacionLote(), ReportesVentas::plantillas),
                EntradaReporte.panel("Indicadores de ventas", () -> true, () -> {
                    infIndicadoresVentas panel = new infIndicadoresVentas();
                    instancias().setIndicadoresVentas(panel);
                    return panel;
                }));
    }

    private static boolean puedeVerDocumentos() {
        return !tiposPermitidos().isEmpty();
    }

    private static List<OpcionFiltro> tiposPermitidos() {
        ndUsuario usuario = usuario();
        vistaSuperMaestra configuracion = configuracion();
        List<OpcionFiltro> tipos = new ArrayList<>();
        if (usuario.isReportesVentas() || usuario.isReporteCreditos() || usuario.isReporteVentasAnuladas()) {
            tipos.add(new OpcionFiltro("Facturas", "facturacion"));
        }
        if (usuario.isReporteCotizaciones()) {
            tipos.add(new OpcionFiltro("Cotizaciones", "cotizacion"));
        }
        if (usuario.isReportesVentas() && configuracion.isPedido()) {
            tipos.add(new OpcionFiltro("Pedidos", "pedido"));
        }
        if (usuario.isReportePlanSepares() && configuracion.isSepare()) {
            tipos.add(new OpcionFiltro("Separes", "separe"));
        }
        if (usuario.isReporteOrdenesServicio() && configuracion.isOrdenServicio()) {
            tipos.add(new OpcionFiltro("Órdenes de servicio", "orden"));
        }
        return tipos;
    }

    private static boolean manejaImpuestos() {
        return !"SinIva".equals(instancias().getRegimen());
    }

    private static List<OpcionFiltro> opcionesAnulacion() {
        List<OpcionFiltro> opciones = new ArrayList<>();
        opciones.add(new OpcionFiltro("Vigentes", VIGENTES));
        if (usuario().isReporteVentasAnuladas()) {
            opciones.add(new OpcionFiltro("Anuladas", ANULADAS));
            opciones.add(new OpcionFiltro("Todas", TODAS));
        }
        return opciones;
    }

    public static DefinicionReporte documentos() {
        DefinicionReporte definicion = new DefinicionReporte("Reporte de documentos de venta")
                .nota("Ventas, cotizaciones, pedidos, separes y órdenes. Las anuladas se filtran por la fecha de anulación.")
                .filtro(new FiltroLista(TIPO, "Tipo de documento", ReportesVentas::tiposPermitidos));
        return conFiltrosDeDocumentos(definicion)
                .accion("Generar totalizado", "Reporte de documentos totalizado", s -> generador().totalizado(conTiposPorDefecto(s), titulo(s)))
                .accion("Generar detallado", "Reporte de documentos detallado", s -> generador().detallado(conTiposPorDefecto(s), titulo(s)));
    }

    public static DefinicionReporte conFiltrosDeDocumentos(DefinicionReporte definicion) {
        return definicion
                .filtro(new FiltroOpcion(ANULACION, "Anulación", ReportesVentas::opcionesAnulacion))
                .filtro(FiltroLista.de(ESTADO, "Estado", new OpcionFiltro("Pendiente", "PENDIENTE"), new OpcionFiltro("Realizado", "REALIZADO")))
                .filtro(FiltroLista.de(FORMA_PAGO, "Forma de pago", new OpcionFiltro("Contado", CONTADO),
                        new OpcionFiltro("Crédito", CREDITO), new OpcionFiltro("Sistecrédito", SISTECREDITO)))
                .filtro(FiltroLista.de(IMPUESTOS, "Impuestos", new OpcionFiltro("Con IVA", CON_IVA),
                        new OpcionFiltro("Con impoconsumo", CON_IMPOCONSUMO), new OpcionFiltro("Con IVA e impoconsumo", CON_AMBOS),
                        new OpcionFiltro("Exentas", EXENTAS)).visibleSi(ReportesVentas::manejaImpuestos))
                .filtro(FiltrosComunes.cliente())
                .filtro(FiltrosComunes.vendedorYDomiciliario())
                .filtro(FiltrosComunes.producto())
                .filtro(FiltrosComunes.grupo())
                .filtro(FiltrosComunes.terminal())
                .filtro(FiltrosComunes.bodega())
                .filtro(FiltrosComunes.placa().visibleSi(() -> configuracion().isServicioAutomotor() || configuracion().isParqueadero()))
                .filtro(FiltroRango.horas(HORA, "Hora"));
    }

    public static String sufijoAnulacion(SeleccionReporte seleccion) {
        return ANULADAS.equals(seleccion.valor(ANULACION)) ? " ANULADAS" : "";
    }

    private static GeneradorReporteDocumentos generador() {
        return new GeneradorReporteDocumentos(instancias());
    }

    private static SeleccionReporte conTiposPorDefecto(SeleccionReporte seleccion) {
        if (!seleccion.tiene(TIPO)) {
            seleccion.agregar(TIPO, "Tipo de documento", tiposPermitidos());
        }
        return seleccion;
    }

    private static String titulo(SeleccionReporte seleccion) {
        List<String> nombres = new ArrayList<>();
        for (OpcionFiltro tipo : seleccion.opciones(TIPO)) {
            nombres.add(tipo.getEtiqueta());
        }
        boolean todos = nombres.size() == tiposPermitidos().size();
        String titulo = todos ? "REPORTE DE DOCUMENTOS DE VENTA" : "REPORTE DE " + String.join(", ", nombres).toUpperCase();
        return titulo + sufijoAnulacion(seleccion);
    }

    public static DefinicionReporte impuestos() {
        return new DefinicionReporte("Reporte de impuestos de ventas").fechasObligatorias()
                .nota("Facturas y notas débito vigentes, agrupadas por impuesto y tarifa.")
                .filtro(FiltrosComunes.impuestoReporte())
                .accion("Generar", "Reporte de impuestos de venta", s -> new GeneradorReporteImpuestos(instancias()).ventas(s));
    }

    public static DefinicionReporte resumenDia() {
        return new DefinicionReporte("Reportes del día").fechasObligatorias()
                .accion("Resumen A-Z", "Reporte del dia", s -> instancias().getReporte().ver_AZ(
                " " + CondicionSql.texto(s.getFechaInicial()) + " AND " + CondicionSql.texto(s.getFechaFinal()) + " ",
                "Desde " + s.getFechaInicial() + " hasta " + s.getFechaFinal()))
                .accion("Resumen gerencial", "Resumen gerencial", s -> new GeneradorReportesCalculados(instancias()).resumenGerencial(s));
    }

    public static DefinicionReporte plantillas() {
        return new DefinicionReporte("Reporte de plantillas")
                .filtro(FiltroLista.deValores("periodicidad", "Periodicidad", "MENSUAL", "ANUAL"))
                .filtro(FiltrosComunes.cliente())
                .filtro(FiltrosComunes.producto())
                .filtro(FiltrosComunes.terminal())
                .accion("Generar totales", "Reporte de plantillas corto", s -> instancias().getReporte().ver_RepPlantillas2(
                new CondicionSql()
                        .en("periodicidad", s.valores("periodicidad"))
                        .en("cliente", s.valores(FiltrosComunes.CLIENTE))
                        .entreFechas("fechaFactura", s.getFechaInicial(), s.getFechaFinal())
                        .where(), s.encabezadoFechas("Plantillas")))
                .accion("Generar detallado", "Reporte de plantillas", s -> instancias().getReporte().ver_RepPlantillas(
                new CondicionSql()
                        .en("terminal", s.valores(FiltrosComunes.TERMINAL))
                        .en("cliente", s.valores(FiltrosComunes.CLIENTE))
                        .en("producto", s.valores(FiltrosComunes.PRODUCTO))
                        .entreFechas("fechaFactura", s.getFechaInicial(), s.getFechaFinal())
                        .where(), s.encabezadoFechas("Plantillas")));
    }
}
