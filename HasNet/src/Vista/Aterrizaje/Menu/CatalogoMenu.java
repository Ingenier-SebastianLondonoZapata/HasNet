package Vista.Aterrizaje.Menu;

import Utilidades.DatosMaestra;
import Vista.Configuraciones.vistaSuperMaestra;
import clases.Instancias;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CatalogoMenu {

    private final Instancias instancias;
    private final vistaSuperMaestra config;

    public CatalogoMenu(Instancias instancias) {
        this.instancias = instancias;
        this.config = instancias.getConfiguraciones();
    }

    public List<OpcionMenu> opciones(ModuloMenu modulo) {
        if (modulo == null) {
            return Collections.emptyList();
        }
        switch (modulo) {
            case VENTAS:
                return ventas();
            case PRODUCTOS:
                return productos();
            case TERCEROS:
                return terceros();
            case TESORERIA:
                return tesoreria();
            case CARTERA:
                return cartera();
            case AGENDA:
                return agenda();
            case VETERINARIA:
                return veterinaria();
            case MEDICO:
                return medico();
            case OFTALMOLOGIA:
                return oftalmologia();
            case PARQUEADERO:
                return parqueadero();
            case RECORDATORIO:
                return recordatorio();
            case LABORATORIO:
                return laboratorio();
            default:
                return Collections.emptyList();
        }
    }

    private List<OpcionMenu> ventas() {
        Opciones o = new Opciones();
        o.agregar(OpcionMenu.de("FACTURAS", instancias::getFacturaContenedor).titulo("FACTURACIÓN"));
        o.agregarSi(config.isCongeladas(), OpcionMenu.de(config.isRestaurante() ? "MESAS" : "CONGELADAS", instancias::getMesas));
        o.agregarSi(config.isFacturacionLote(), OpcionMenu.de("PLANTILLAS", instancias::getCuentaCobroContenedor));
        o.agregar(OpcionMenu.de("COTIZACIONES", instancias::getCotizaContenedor));
        o.agregarSi(config.isSepare(), OpcionMenu.de("PLAN SEPARES", instancias::getPlanSepareContenedor));
        o.agregarSi(config.isCreditos(), OpcionMenu.de("CRÉDITOS", instancias::getFacturaCreditosContenedor));
        o.agregarSi(config.isPedido(), OpcionMenu.de("PEDIDOS", instancias::getPedidoContenedor));
        o.agregarSi(config.isOrdenServicio(), OpcionMenu.de("ORDENES", instancias::getOrdenServicioContenedor).titulo("ORDEN DE SERVICIO"));
        o.agregarSi(config.isFacturacionLote(), OpcionMenu.de("FACTURA AUTO", instancias::getFacturarLotes).titulo("FACTURACIÓN AUTOMÁTICA"));
        o.agregar(OpcionMenu.de("REPORTES", instancias::getReportesVentas).titulo("REPORTES DE VENTAS"));
        return o.lista();
    }

    private List<OpcionMenu> productos() {
        Opciones o = new Opciones();
        o.agregar(OpcionMenu.de("PRODUCTOS", instancias::getProductos).titulo("CREACIÓN DE PRODUCTO"));
        o.agregar(OpcionMenu.de("GRUPOS", instancias::getGrupos).titulo("CREACIÓN DE GRUPOS Y SUBGRUPOS"));
        o.agregar(OpcionMenu.de("INVENTARIO INICIAL", instancias::getInventarioInicial));
        o.agregar(OpcionMenu.de("DISEÑO Y ARMADOS", instancias::getCosteo)
                .despuesDeAbrir(() -> instancias.getCosteo().seleccionarPestanaDiseno()));
        o.agregar(OpcionMenu.de("ORDEN DE COMPRA", instancias::getOrdenCompraContenedor));
        o.agregar(OpcionMenu.de("COMPRAS", instancias::getIngresoContenedor).titulo("COMPRA"));
        o.agregar(OpcionMenu.de("AJUSTES INVENTARIO", instancias::getVistaAjusteInventario)
                .titulo("AJUSTES DE INVENTARIO")
                .antesDeAbrir(() -> instancias.getVistaAjusteInventario().consultarPermiso()));
        o.agregar(OpcionMenu.de("REPORTES", instancias::getReportesProductos).titulo("REPORTES DE PRODUCTOS"));
        return o.lista();
    }

    private List<OpcionMenu> terceros() {
        Opciones o = new Opciones();
        o.agregarSi(!esModuloSalud(), OpcionMenu.de("CONTACTOS", instancias::getClientes));
        o.agregar(OpcionMenu.de("R.CONTACTOS", instancias::getRepClientes).titulo("REPORTE DE CONTACTOS"));
        o.agregar(OpcionMenu.de("R.EMPLEADOS", instancias::getRepEmpleados).titulo("REPORTE DE EMPLEADOS"));
        return o.lista();
    }

    private List<OpcionMenu> tesoreria() {
        Opciones o = new Opciones();
        o.agregar(OpcionMenu.de("GASTOS", instancias::getEgresos));
        o.agregar(OpcionMenu.de("CUADRE CAJA", instancias::getCaja).titulo("CUADRE DE CAJA"));
        o.agregar(OpcionMenu.de("R.GASTOS", instancias::getRepEgresos).titulo("REPORTE DE GASTOS"));
        o.agregar(OpcionMenu.de("REP.CUADRE", instancias::getRepCuadre).titulo("REPORTE DE CUADRE DE CAJA"));
        return o.lista();
    }

    private List<OpcionMenu> cartera() {
        Opciones o = new Opciones();
        o.agregar(OpcionMenu.de("POR COBRAR", instancias::getAbonos).titulo("ABONOS CUENTAS POR COBRAR"));
        o.agregar(OpcionMenu.de("PARA PAGAR", instancias::getPagos).titulo("ABONOS CUENTAS POR PAGAR"));
        o.agregar(OpcionMenu.de("NOTA CRÉDITO", instancias::getNc).titulo("NOTAS CRÉDITO"));
        o.agregar(OpcionMenu.de("NOTA DÉBITO", instancias::getNdContenedor).titulo("NOTAS DÉBITO"));
        o.agregar(OpcionMenu.de("REPORTES", instancias::getReportesCartera).titulo("REPORTES DE CARTERA"));
        return o.lista();
    }

    private List<OpcionMenu> agenda() {
        Opciones o = new Opciones();
        o.agregar(OpcionMenu.de("AGENDA", instancias::getAgendaConsulta)
                .antesDeAbrir(() -> {
                    instancias.getAgendaConsulta().cargarAgendas(instancias.getMedico());
                    instancias.getAgendaConsulta().cargarAgendas();
                }));
        o.agregar(OpcionMenu.de("REP.AGENDA", instancias::getRepAgenda).titulo("REPORTE DE AGENDA"));
        return o.lista();
    }

    private List<OpcionMenu> veterinaria() {
        Opciones o = new Opciones();
        o.agregar(OpcionMenu.de("MASCOTAS", instancias::getMascotas));
        o.agregar(OpcionMenu.de("H.CLINICA", instancias::getHistoria)
                .titulo("HISTORIA CLÍNICA")
                .despuesDeAbrir(() -> {
                    if (instancias.getHistoria().validarHistoriaCargada()) {
                        instancias.getMascotas().setSelected(true);
                        instancias.getMenu().cambiarTitulo("MASCOTAS");
                    }
                }));
        o.agregar(OpcionMenu.de("R.MASCOTAS", instancias::getRepMascotas).titulo("REPORTE DE MASCOTAS"));
        o.agregar(OpcionMenu.de("GUARDERIA", instancias::getGuarderia));
        o.agregar(OpcionMenu.de("R.GUARDERIA", instancias::getRepGuarderia).titulo("REPORTE DE GUARDERIAS"));
        o.agregar(OpcionMenu.de("PELUQUERIA", instancias::getPeluqueria));
        o.agregar(OpcionMenu.de("R.PELUQUERIA", instancias::getRepPeluqueria).titulo("REPORTE DE PELUQUERIAS"));
        o.agregar(OpcionMenu.de("ING. HOSPIT", instancias::getIngresoHospitalizacion).titulo("INGRESO HOSPITALIZACIÓN"));
        o.agregar(OpcionMenu.de("REP. HOSPIT", instancias::getRepHospitalizacion).titulo("REPORTE DE HOSPITALIZACIÓN"));
        o.agregar(OpcionMenu.de("FORMATOS", instancias::getFormatos).titulo("FORMATOS DE IMPRESIÓN"));
        o.agregar(OpcionMenu.de("ALERTAS", instancias::getAlertasProximas).titulo("ALERTAS VACUNACIÓN"));
        return o.lista();
    }

    private List<OpcionMenu> medico() {
        Opciones o = new Opciones();
        o.agregar(opcionPacientes());
        o.agregarSi(DatosMaestra.isGeneraOrdenMedica(), OpcionMenu.de("ORDEN.SERV", instancias::getOrdenMedica).titulo("ORDEN DE SERVICIO"));
        o.agregar(OpcionMenu.de("HISTORIA", instancias::getHistoriaC).titulo("HISTORIAL PACIENTE"));
        o.agregar(OpcionMenu.de("CONSULTA", instancias::getHistoriaClinica)
                .despuesDeAbrir(() -> instancias.getHistoriaClinica().validarHistoriaCargada()));
        o.agregar(OpcionMenu.de("FORMATOS", instancias::getPlantillasDeTextos));
        o.agregar(OpcionMenu.de("CONVENIO", instancias::getConvenio));
        o.agregar(OpcionMenu.de("N.ENFERMERIA", instancias::getNotaEnfermeria).titulo("NOTA ENFERMERÍA"));
        o.agregar(OpcionMenu.de("MEDICAMENTOS", instancias::getMedicamentos));
        o.agregar(OpcionMenu.de("REPORTES", instancias::getReportesMedicos).titulo("REPORTES MÉDICOS"));
        o.agregar(OpcionMenu.de("RIPS", instancias::getRips).titulo("RIPS MÉDICOS"));
        return o.lista();
    }

    private List<OpcionMenu> laboratorio() {
        Opciones o = new Opciones();
        o.agregar(opcionPacientes().titulo("PACIENTES Y PROVEEDORES"));
        o.agregar(OpcionMenu.de("CONSULTA", instancias::getHistoriaLaboratorio).titulo("CONSULTA LABORATORIO"));
        return o.lista();
    }

    private List<OpcionMenu> oftalmologia() {
        Opciones o = new Opciones();
        o.agregar(opcionPacientes().titulo("PACIENTES Y PROVEEDORES"));
        o.agregar(OpcionMenu.de("CONSULTA", instancias::getHojaIngreso).titulo("CONSULTA OFTALMOLOGÍA"));
        return o.lista();
    }

    private List<OpcionMenu> parqueadero() {
        Opciones o = new Opciones();
        o.agregar(OpcionMenu.de("PARQUEADERO", instancias::getParqueadero));
        o.agregar(OpcionMenu.de("LAVADO", instancias::getLavadero));
        o.agregar(OpcionMenu.de("MENSUALIDAD", instancias::getMensualidad));
        o.agregar(OpcionMenu.de("REP.MENSUAL", instancias::getRepMensualidades).titulo("REPORTE DE MENSUALIDADES"));
        o.agregar(OpcionMenu.de("REP.PAGOS.LAV", instancias::getRepLavadero).titulo("REPORTE DE PAGOS DEL LAVADERO"));
        o.agregar(OpcionMenu.de("REP.PARQUEA", instancias::getRepParqueadero).titulo("REPORTE DE PARQUEADERO"));
        o.agregar(OpcionMenu.de("REP.LAVADOS", instancias::getRepLavadero1).titulo("REPORTE DE LAVADERO"));
        o.agregar(OpcionMenu.de("REP.HISTORIAL", instancias::getRepHistorial).titulo("REPORTE DE HISTORIAL"));
        return o.lista();
    }

    private List<OpcionMenu> recordatorio() {
        Opciones o = new Opciones();
        o.agregar(OpcionMenu.de("RECORDATORIO", instancias::getRecordatorios).titulo("RECORDATORIOS"));
        return o.lista();
    }

    private OpcionMenu opcionPacientes() {
        return OpcionMenu.de("PACIENTES", instancias::getPaciente).titulo("PACIENTES Y PROVEEDORES");
    }

    private boolean esModuloSalud() {
        return config.isMedico() || config.isLaboratorio() || config.isOftalmologia();
    }

    private static class Opciones {

        private final List<OpcionMenu> lista = new ArrayList<>();

        void agregar(OpcionMenu opcion) {
            lista.add(opcion);
        }

        void agregarSi(boolean condicion, OpcionMenu opcion) {
            if (condicion) {
                lista.add(opcion);
            }
        }

        List<OpcionMenu> lista() {
            return lista;
        }
    }
}
