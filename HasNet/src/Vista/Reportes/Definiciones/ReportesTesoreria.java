package Vista.Reportes.Definiciones;

import Modelo.Reportes.CondicionSql;
import Modelo.Reportes.OpcionFiltro;
import Vista.Reportes.DefinicionReporte;
import Vista.Reportes.EntradaReporte;
import Vista.Reportes.Filtros.FiltroCasillas;
import Vista.Reportes.Filtros.FiltroLista;
import Vista.Reportes.Filtros.FiltroOpcion;
import clases.Instancias;
import clases.ndUsuario;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class ReportesTesoreria {

    private static final String CONCEPTO = "concepto";
    private static final String TIPO_GASTO = "tipoGasto";
    private static final String ESTADO = "estadoRegistro";
    private static final String ANULADOS = "anulados";

    private ReportesTesoreria() {
    }

    private static Instancias instancias() {
        return Instancias.getInstancias();
    }

    private static ndUsuario usuario() {
        return instancias().getUsuarioLog();
    }

    public static List<EntradaReporte> entradas() {
        return Arrays.asList(
                EntradaReporte.de(() -> usuario().isReporteEgresos(), ReportesTesoreria::gastos),
                EntradaReporte.de(() -> usuario().isReporteCuadreCaja(), ReportesTesoreria::cuadreCaja),
                EntradaReporte.de(() -> usuario().isReporteCuadreCaja(), ReportesTesoreria::basesCuadre),
                EntradaReporte.de(() -> usuario().isReporteCuadreCaja(), ReportesTesoreria::propinas));
    }

    private static List<OpcionFiltro> conceptosEgreso() {
        List<OpcionFiltro> opciones = new ArrayList<>();
        Object[][] conceptos = instancias().getSql().getCodsEgresos();
        if (conceptos == null) {
            return opciones;
        }
        for (Object[] concepto : conceptos) {
            if (concepto[1] != null && concepto[2] != null) {
                opciones.add(new OpcionFiltro(concepto[1].toString(), concepto[2].toString()));
            }
        }
        return opciones;
    }

    public static DefinicionReporte gastos() {
        return new DefinicionReporte("Reporte de gastos")
                .filtro(FiltroOpcion.de(ESTADO, "Estado", new OpcionFiltro("No anulados", "noAnulados"), new OpcionFiltro("Anulados", ANULADOS)))
                .filtro(new FiltroCasillas(TIPO_GASTO, "Tipo de gasto", new OpcionFiltro("General", "GENERAL"), new OpcionFiltro("Local", "LOCAL")))
                .filtro(new FiltroLista(CONCEPTO, "Concepto", ReportesTesoreria::conceptosEgreso))
                .filtro(FiltrosComunes.terceroPorNit(FiltrosComunes.PROVEEDOR, "Proveedor"))
                .filtro(FiltrosComunes.terminal())
                .accion("Generar", "Reporte de egresos", s -> {
                    boolean anulados = ANULADOS.equals(s.valor(ESTADO));
                    List<String> tipos = s.valores(TIPO_GASTO);
                    String condicion = new CondicionSql()
                            .en("terminal", s.valores(FiltrosComunes.TERMINAL))
                            .en("nit", s.valores(FiltrosComunes.PROVEEDOR))
                            .en("codigo", s.valores(CONCEPTO))
                            .entreFechas("fecha", s.getFechaInicial(), s.getFechaFinal())
                            .y(anulados ? "anulada = true" : "anulada = false")
                            .en("tipo", tipos.size() == 1 ? tipos : new ArrayList<>())
                            .where();
                    instancias().getReporte().ver_RepEgresos(condicion, s.encabezadoFechas(anulados ? "Egresos ANULADOS" : "Egresos"));
                });
    }

    public static DefinicionReporte cuadreCaja() {
        return new DefinicionReporte("Reporte de cuadre de caja")
                .filtro(FiltrosComunes.terminal())
                .accion("Generar", "Reporte de cuadre de caja", s -> instancias().getReporte().verRepCuadre(new CondicionSql()
                .en("terminal", s.valores(FiltrosComunes.TERMINAL))
                .entreFechas("fecha", s.getFechaInicial(), s.getFechaFinal())
                .where(), s.encabezadoFechas("Cuadre de caja")));
    }

    public static DefinicionReporte basesCuadre() {
        return new DefinicionReporte("Reporte de bases de cuadre de caja")
                .filtro(FiltrosComunes.usuario())
                .accion("Generar", "Reporte de bases de cuadre de caja", s -> instancias().getReporte().verRepBasesCuadre(new CondicionSql()
                .en("usuario", s.valores(FiltrosComunes.USUARIO))
                .entreFechas("fecha", s.getFechaInicial(), s.getFechaFinal())
                .where(), s.encabezadoFechas("Bases de cuadre de caja")));
    }

    public static DefinicionReporte propinas() {
        return new DefinicionReporte("Reporte de propinas")
                .accion("Generar", "Reporte de propinas", s -> instancias().getReporte().verRepPropinas(new CondicionSql()
                .y("totalPropina > 0")
                .entreFechas("fechaFactura", s.getFechaInicial(), s.getFechaFinal())
                .where(), s.encabezadoFechas("Propinas")));
    }
}
