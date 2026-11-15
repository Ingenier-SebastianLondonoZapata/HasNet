package Vista.Reportes.Definiciones;

import Impresiones.ImpresionesReportes.GeneradorReporteDocumentos;
import Modelo.Reportes.CondicionSql;
import Modelo.Reportes.OpcionFiltro;
import Modelo.Reportes.SeleccionReporte;
import Vista.Configuraciones.vistaSuperMaestra;
import Vista.Reportes.DefinicionReporte;
import Vista.Reportes.EntradaReporte;
import Vista.Reportes.Filtros.FiltroLista;
import Vista.Reportes.Filtros.FiltroOpcion;
import clases.Instancias;
import clases.ndUsuario;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static Dao.Documentos.ConsultaReporteDocumentos.TIPO;

public final class ReportesCartera {

    private static final String TIPO_CARTERA = "tipoCartera";
    private static final String ORIGEN = "origen";
    private static final String ESTADO = "estadoRegistro";
    private static final String REEMBOLSO = "reembolso";
    private static final String NO_ANULADOS = "noAnulados";
    private static final String ANULADOS = "anulados";

    private ReportesCartera() {
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
                EntradaReporte.de(() -> usuario().isReporteCarteraCxC(), ReportesCartera::cuentasPendientes),
                EntradaReporte.de(() -> usuario().isReporteAbonosCxC(), ReportesCartera::abonosCuentas),
                EntradaReporte.de(() -> usuario().isReporteCreditos(), ReportesCartera::creditosPorCuotas),
                EntradaReporte.de(() -> usuario().isReporteCarteraCxP(), ReportesCartera::pagosPendientes),
                EntradaReporte.de(() -> usuario().isReporteAbonosCxP(), ReportesCartera::abonosPagos),
                EntradaReporte.de(() -> usuario().isReporteNotasCredito(), ReportesCartera::notasCredito),
                EntradaReporte.de(() -> usuario().isReporteNotasCredito(), ReportesCartera::notasDebito));
    }

    private static FiltroOpcion estadoRegistro(String etiquetaVigentes) {
        return FiltroOpcion.de(ESTADO, "Estado", new OpcionFiltro(etiquetaVigentes, NO_ANULADOS), new OpcionFiltro("Anulados", ANULADOS));
    }

    private static boolean anulados(SeleccionReporte seleccion) {
        return ANULADOS.equals(seleccion.valor(ESTADO));
    }

    private static List<OpcionFiltro> tiposCartera() {
        List<OpcionFiltro> tipos = new ArrayList<>();
        tipos.add(new OpcionFiltro("Cartera", "repCartera"));
        tipos.add(new OpcionFiltro("Plan separes", "repCarteraSepares"));
        tipos.add(new OpcionFiltro("Domicilios", "repCarteraDomicilios"));
        if (configuracion().isFacturacionLote()) {
            tipos.add(new OpcionFiltro("Cuentas de cobro", "repCarteraCuentaCobro"));
        }
        return tipos;
    }

    public static DefinicionReporte cuentasPendientes() {
        return new DefinicionReporte("Reporte de cuentas pendientes")
                .etiquetaFechas("Fecha de factura")
                .filtro(new FiltroOpcion(TIPO_CARTERA, "Tipo de cartera", ReportesCartera::tiposCartera))
                .filtro(FiltrosComunes.cliente())
                .filtro(FiltrosComunes.vendedor())
                .filtro(FiltrosComunes.terminal())
                .filtro(FiltrosComunes.placa().visibleSi(() -> configuracion().isParqueadero()))
                .accion("Generar", "Reporte cartera", s -> {
                    String tipo = s.valor(TIPO_CARTERA);
                    String condicion = new CondicionSql()
                            .ySi("repCarteraDomicilios".equals(tipo), "conseMesa <> 'SIN-CONSECUTIVO'")
                            .ySi("repCartera".equals(tipo), "conseMesa = 'SIN-CONSECUTIVO'")
                            .en("terminal", s.valores(FiltrosComunes.TERMINAL))
                            .en("cliente", s.valores(FiltrosComunes.CLIENTE))
                            .en("vendedor", s.valores(FiltrosComunes.VENDEDOR))
                            .en("placa", s.valores(FiltrosComunes.PLACA))
                            .entreFechas("fechaFactura", s.getFechaInicial(), s.getFechaFinal())
                            .where();
                    instancias().getReporte().ver_Repcartera(condicion, tipo);
                });
    }

    public static DefinicionReporte abonosCuentas() {
        return new DefinicionReporte("Reporte de abonos de cuentas")
                .nota("Se filtra por la fecha del abono.")
                .filtro(FiltroOpcion.de(ORIGEN, "Abonos de", new OpcionFiltro("Créditos", "repAbonos"), new OpcionFiltro("Separes", "repAbonosSepare")))
                .filtro(estadoRegistro("No anulados"))
                .filtro(FiltrosComunes.cliente())
                .filtro(FiltrosComunes.terminal().visibleSi(() -> true).siOculto(Collections::emptyList))
                .filtro(FiltrosComunes.placa().visibleSi(() -> configuracion().isParqueadero()))
                .accion("Generar", "Reporte abonos", s -> {
                    String condicion = new CondicionSql()
                            .en("terminal", s.valores(FiltrosComunes.TERMINAL))
                            .en("idSistema", s.valores(FiltrosComunes.CLIENTE))
                            .en("placa", s.valores(FiltrosComunes.PLACA))
                            .entreFechas("vencimiento", s.getFechaInicial(), s.getFechaFinal())
                            .y(anulados(s) ? "anulada = true" : "anulada = false")
                            .where();
                    instancias().getReporte().ver_RepAbonos(s.valor(ORIGEN) + " " + condicion,
                            s.encabezadoFechas(anulados(s) ? "Abonos ANULADOS" : "Abonos"));
                });
    }

    public static DefinicionReporte creditosPorCuotas() {
        return new DefinicionReporte("Reporte de créditos por cuotas")
                .nota("Contratos de crédito con su valor aprobado, cuotas abonadas y atrasadas.")
                .filtro(FiltrosComunes.cliente())
                .filtro(FiltrosComunes.vendedor())
                .accion("Generar", "Reporte de creditos", s -> instancias().getReporte().ver_RepCreditos(new CondicionSql()
                .en("cliente", s.valores(FiltrosComunes.CLIENTE))
                .en("vendedor", s.valores(FiltrosComunes.VENDEDOR))
                .entreFechas("fechaFactura", s.getFechaInicial(), s.getFechaFinal())
                .where(), s.encabezadoFechas("Créditos")));
    }

    public static DefinicionReporte pagosPendientes() {
        return new DefinicionReporte("Reporte de pagos pendientes")
                .sinFechas()
                .filtro(FiltrosComunes.proveedor())
                .accion("Generar", "Reporte de Pagos", s -> instancias().getReporte().ver_RepPagos(new CondicionSql()
                .en("idSistema", s.valores(FiltrosComunes.PROVEEDOR))
                .where()));
    }

    public static DefinicionReporte abonosPagos() {
        return new DefinicionReporte("Reporte de abonos de pagos")
                .nota("Se filtra por la fecha del abono.")
                .filtro(estadoRegistro("No anulados"))
                .filtro(FiltrosComunes.proveedor())
                .filtro(FiltrosComunes.terminal())
                .accion("Generar", "Reporte abonos de pagos", s -> instancias().getReporte().ver_RepAbonosCxp(new CondicionSql()
                .en("terminal", s.valores(FiltrosComunes.TERMINAL))
                .en("proveedor", s.valores(FiltrosComunes.PROVEEDOR))
                .entreFechas("vencimiento", s.getFechaInicial(), s.getFechaFinal())
                .y(anulados(s) ? "estado = 'ANULADO'" : "estado = 'PEND' OR estado = 'CANCELADA'")
                .where(), s.encabezadoFechas(anulados(s) ? "Pagos ANULADOS" : "Pagos")));
    }

    public static DefinicionReporte notasCredito() {
        return new DefinicionReporte("Reporte de notas crédito")
                .filtro(FiltroOpcion.de(REEMBOLSO, "Reembolso", new OpcionFiltro("Todas", ""), new OpcionFiltro("Reembolsadas", "reembolsadas"),
                        new OpcionFiltro("No reembolsadas", "noReembolsadas"), new OpcionFiltro("Efectivas", "efectivas")))
                .filtro(estadoRegistro("No anuladas"))
                .filtro(FiltrosComunes.cliente())
                .filtro(FiltrosComunes.vendedor())
                .filtro(FiltrosComunes.terminal())
                .accion("Generar", "Reporte Notas Credito", s -> {
                    String reembolso = s.valor(REEMBOLSO);
                    String condicion = new CondicionSql()
                            .en("terminal", s.valores(FiltrosComunes.TERMINAL))
                            .en("idSistema", s.valores(FiltrosComunes.CLIENTE))
                            .en("vendedor", s.valores(FiltrosComunes.VENDEDOR))
                            .ySi("reembolsadas".equals(reembolso), "reembolsar = true")
                            .ySi("noReembolsadas".equals(reembolso), "reembolsar = false AND restante >= 1")
                            .ySi("efectivas".equals(reembolso), "reembolsar = false AND restante <= 0")
                            .y(anulados(s) ? "anulada = true" : "anulada = false")
                            .entreFechas("fecha", s.getFechaInicial(), s.getFechaFinal())
                            .where();
                    String encabezado = reembolso.isEmpty() ? "" : s.opciones(REEMBOLSO).get(0).getEtiqueta().toUpperCase();
                    instancias().getReporte().ver_RepNC(condicion, encabezado);
                });
    }

    public static DefinicionReporte notasDebito() {
        DefinicionReporte definicion = new DefinicionReporte("Reporte de notas débito")
                .nota("Notas débito de cartera. Las anuladas se filtran por la fecha de anulación.");
        return ReportesVentas.conFiltrosDeDocumentos(definicion)
                .accion("Generar totalizado", "Reporte de notas debito totalizado",
                        s -> new GeneradorReporteDocumentos(instancias()).totalizado(soloNotasDebito(s), tituloNotasDebito(s)))
                .accion("Generar detallado", "Reporte de notas debito detallado",
                        s -> new GeneradorReporteDocumentos(instancias()).detallado(soloNotasDebito(s), tituloNotasDebito(s)));
    }

    private static SeleccionReporte soloNotasDebito(SeleccionReporte seleccion) {
        seleccion.agregar(TIPO, "Tipo de documento", Collections.singletonList(new OpcionFiltro("Notas débito", "notaDebito")));
        return seleccion;
    }

    private static String tituloNotasDebito(SeleccionReporte seleccion) {
        return "REPORTE DE NOTAS DÉBITO" + ReportesVentas.sufijoAnulacion(seleccion);
    }
}
