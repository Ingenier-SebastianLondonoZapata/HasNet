package Vista.Reportes.Definiciones;

import Modelo.Reportes.CondicionSql;
import Modelo.Reportes.OpcionFiltro;
import Modelo.Reportes.SeleccionReporte;
import Vista.Reportes.DefinicionReporte;
import Vista.Reportes.EntradaReporte;
import Vista.Reportes.Filtros.FiltroBusqueda;
import Vista.Reportes.Filtros.FiltroCasillas;
import Vista.Reportes.Filtros.FiltroLista;
import clases.Instancias;
import clases.ndUsuario;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class ReportesTerceros {

    private static final String TIPO_TERCERO = "tipoTercero";
    private static final String EMPLEADO = "EMPLEADO";
    private static final String ESTADO = "estadoTercero";
    private static final String ACTIVOS = "activos";
    private static final String INACTIVOS = "inactivos";
    private static final String CONTACTO = "contacto";
    private static final String CIUDAD = "ciudad";
    private static final String MES = "mesNacimiento";
    private static final String[] MESES = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre",
        "Octubre", "Noviembre", "Diciembre"};

    private ReportesTerceros() {
    }

    private static Instancias instancias() {
        return Instancias.getInstancias();
    }

    private static ndUsuario usuario() {
        return instancias().getUsuarioLog();
    }

    public static List<EntradaReporte> entradas() {
        return Arrays.asList(EntradaReporte.de(() -> !tiposPermitidos().isEmpty(), ReportesTerceros::contactos),
                EntradaReporte.de(() -> !tiposPermitidos().isEmpty(), ReportesTerceros::cumpleanos));
    }

    private static List<OpcionFiltro> tiposPermitidos() {
        List<OpcionFiltro> tipos = new ArrayList<>();
        if (usuario().isReporteClientes()) {
            tipos.add(new OpcionFiltro("Clientes", "TERCERO"));
            tipos.add(new OpcionFiltro("Proveedores", "PROVEEDOR"));
        }
        if (usuario().isReporteEmpleados()) {
            tipos.add(new OpcionFiltro("Empleados", EMPLEADO));
        }
        return tipos;
    }

    private static List<String> tiposSeleccionados(SeleccionReporte seleccion) {
        if (seleccion.tiene(TIPO_TERCERO)) {
            return seleccion.valores(TIPO_TERCERO);
        }
        List<String> tipos = new ArrayList<>();
        for (OpcionFiltro tipo : tiposPermitidos()) {
            tipos.add(tipo.getValor());
        }
        return tipos;
    }

    private static CondicionSql conEstado(CondicionSql condicion, SeleccionReporte seleccion) {
        boolean activos = seleccion.contiene(ESTADO, ACTIVOS);
        boolean inactivos = seleccion.contiene(ESTADO, INACTIVOS);
        return condicion
                .ySi(activos && !inactivos, "activo = false")
                .ySi(inactivos && !activos, "activo = true");
    }

    public static DefinicionReporte contactos() {
        return new DefinicionReporte("Reporte de contactos")
                .sinFechas()
                .filtro(new FiltroLista(TIPO_TERCERO, "Tipo de contacto", ReportesTerceros::tiposPermitidos))
                .filtro(new FiltroCasillas(ESTADO, "Estado", new OpcionFiltro("Activos", ACTIVOS), new OpcionFiltro("Inactivos", INACTIVOS)))
                .filtro(FiltrosComunes.tercero(CONTACTO, "Contacto", true))
                .filtro(FiltroBusqueda.textoLibre(CIUDAD, "Ciudad"))
                .nota("Sin tipo seleccionado se incluyen todos los permitidos. Los empleados agregan cargo, salario y fechas.")
                .accion("Generar", "Reporte de contactos", s -> {
                    List<String> tipos = tiposSeleccionados(s);
                    String condicion = conEstado(new CondicionSql().en("tipoTercero", tipos), s)
                            .en("idSistema", s.valores(CONTACTO))
                            .en("ciudad", s.valores(CIUDAD))
                            .where();
                    instancias().getReporte().ver_RepClientes(condicion, tipos.contains(EMPLEADO));
                })
                .accion("Generar referidos", "Reporte de referidos", s -> instancias().getReporte().ver_RepReferidos(new CondicionSql()
                .en("referido", s.valores(CONTACTO))
                .where(), "REFERIDOS"));
    }

    private static List<OpcionFiltro> meses() {
        List<OpcionFiltro> meses = new ArrayList<>();
        for (int i = 0; i < MESES.length; i++) {
            meses.add(new OpcionFiltro(MESES[i], String.valueOf(i + 1)));
        }
        return meses;
    }

    public static DefinicionReporte cumpleanos() {
        return new DefinicionReporte("Reporte de cumpleaños")
                .sinFechas()
                .filtro(new FiltroLista(MES, "Mes de nacimiento", ReportesTerceros::meses))
                .filtro(new FiltroLista(TIPO_TERCERO, "Tipo de contacto", ReportesTerceros::tiposPermitidos))
                .filtro(new FiltroCasillas(ESTADO, "Estado", new OpcionFiltro("Activos", ACTIVOS), new OpcionFiltro("Inactivos", INACTIVOS)))
                .nota("Toma la fecha de nacimiento del contacto. Sin mes seleccionado se incluyen todos, ordenados por mes y día.")
                .accion("Generar", "Reporte de cumpleaños", s -> {
                    CondicionSql condicion = conEstado(new CondicionSql().en("tipoTercero", tiposSeleccionados(s)), s)
                            .en("MONTH(nacimiento)", s.valores(MES));
                    instancias().getReporte().verRepAlertasCumpleanos(condicion.estaVacia() ? "" : "AND " + condicion.sinWhere());
                });
    }
}