package Modelo.Usuarios;

import clases.ndUsuario;
import java.util.EnumMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

/**
 * Convierte entre {@link ModeloUsuario} (permisos por catálogo) y
 * {@link ndUsuario}, la clase que usa el resto del sistema tras iniciar sesión.
 *
 * Cada permiso del catálogo debe registrarse aquí con su campo de ndUsuario.
 */
public final class ConversorUsuario {

    private static final Map<PermisoUsuario, BiConsumer<ndUsuario, Boolean>> ASIGNAR = new EnumMap<>(PermisoUsuario.class);
    private static final Map<PermisoUsuario, Predicate<ndUsuario>> LEER = new EnumMap<>(PermisoUsuario.class);

    static {
        registrar(PermisoUsuario.FACTURAS, ndUsuario::setFacturas, ndUsuario::isFacturas);
        registrar(PermisoUsuario.CONGELADAS, ndUsuario::setCongeladas, ndUsuario::isCongeladas);
        registrar(PermisoUsuario.COTIZACIONES, ndUsuario::setCotizaciones, ndUsuario::isCotizaciones);
        registrar(PermisoUsuario.PLAN_SEPARES, ndUsuario::setPlanSepares, ndUsuario::isPlanSepares);
        registrar(PermisoUsuario.CREDITOS, ndUsuario::setCreditos, ndUsuario::isCreditos);
        registrar(PermisoUsuario.PEDIDOS, ndUsuario::setPedidos, ndUsuario::isPedidos);
        registrar(PermisoUsuario.ORDENES_SERVICIO, ndUsuario::setOrdenesServicio, ndUsuario::isOrdenesServicio);
        registrar(PermisoUsuario.ANULACIONES, ndUsuario::setAnulaciones, ndUsuario::isAnulaciones);
        registrar(PermisoUsuario.REIMPRESION, ndUsuario::setReimpresion, ndUsuario::isReimpresion);
        registrar(PermisoUsuario.REPORTE_VENTAS, ndUsuario::setReportesVentas, ndUsuario::isReportesVentas);
        registrar(PermisoUsuario.REPORTE_VENTAS_ANULADAS, ndUsuario::setReporteVentasAnuladas, ndUsuario::isReporteVentasAnuladas);
        registrar(PermisoUsuario.REPORTE_COTIZACIONES, ndUsuario::setReporteCotizaciones, ndUsuario::isReporteCotizaciones);
        registrar(PermisoUsuario.REPORTE_PLAN_SEPARES, ndUsuario::setReportePlanSepares, ndUsuario::isReportePlanSepares);
        registrar(PermisoUsuario.REPORTE_CREDITOS, ndUsuario::setReporteCreditos, ndUsuario::isReporteCreditos);
        registrar(PermisoUsuario.REPORTE_ORDENES_SERVICIO, ndUsuario::setReporteOrdenesServicio, ndUsuario::isReporteOrdenesServicio);
        registrar(PermisoUsuario.REPORTE_IVAS_VENTAS, ndUsuario::setReporteIvasVentas, ndUsuario::isReporteIvasVentas);
        registrar(PermisoUsuario.REPORTE_DIA, ndUsuario::setReporteDia, ndUsuario::isReporteDia);
        registrar(PermisoUsuario.CREACION_PRODUCTOS, ndUsuario::setCreacionProductos, ndUsuario::isCreacionProductos);
        registrar(PermisoUsuario.ORDENES_COMPRAS, ndUsuario::setOrdenesCompras, ndUsuario::isOrdenesCompras);
        registrar(PermisoUsuario.COMPRAS, ndUsuario::setCompras, ndUsuario::isCompras);
        registrar(PermisoUsuario.TRASLADOS_EXTERNOS, ndUsuario::setTrasladosExternos, ndUsuario::isTrasladosExternos);
        registrar(PermisoUsuario.TRASLADOS_INTERNOS, ndUsuario::setTrasladosInternos, ndUsuario::isTrasladosInternos);
        registrar(PermisoUsuario.AJUSTES_ENTRADA, ndUsuario::setAjustesEntrada, ndUsuario::isAjustesEntrada);
        registrar(PermisoUsuario.AJUSTES_SALIDA, ndUsuario::setAjustesSalida, ndUsuario::isAjustesSalida);
        registrar(PermisoUsuario.GRUPOS, ndUsuario::setGrupos, ndUsuario::isGrupos);
        registrar(PermisoUsuario.DISENOS, ndUsuario::setDisenos, ndUsuario::isDisenos);
        registrar(PermisoUsuario.ARMADOS, ndUsuario::setArmados, ndUsuario::isArmados);
        registrar(PermisoUsuario.INVENTARIO_INICIAL, ndUsuario::setInventarioInicial, ndUsuario::isInventarioInicial);
        registrar(PermisoUsuario.REPORTE_PRODUCTOS, ndUsuario::setReporteProductos, ndUsuario::isReporteProductos);
        registrar(PermisoUsuario.REPORTE_COMPRAS, ndUsuario::setReporteCompras, ndUsuario::isReporteCompras);
        registrar(PermisoUsuario.REPORTE_AJUSTES, ndUsuario::setReporteAjustes, ndUsuario::isReporteAjustes);
        registrar(PermisoUsuario.REPORTE_ORDEN_COMPRAS, ndUsuario::setReporteOrdenCompras, ndUsuario::isReporteOrdenCompras);
        registrar(PermisoUsuario.REPORTE_INVENTARIO, ndUsuario::setReporteInventario, ndUsuario::isReporteInventario);
        registrar(PermisoUsuario.REPORTE_TRASLADO_INTERNO, ndUsuario::setReporteTrasladoInterno, ndUsuario::isReporteTrasladoInterno);
        registrar(PermisoUsuario.REPORTE_TRASLADO_EXTERNO, ndUsuario::setReporteTrasladoExterno, ndUsuario::isReporteTrasladoExterno);
        registrar(PermisoUsuario.REPORTE_IVAS_COMPRAS, ndUsuario::setReporteIvasCompras, ndUsuario::isReporteIvasCompras);
        registrar(PermisoUsuario.REPORTE_MOVIMIENTO_SERIAL, ndUsuario::setReporteMovimientoSerial, ndUsuario::isReporteMovimientoSerial);
        registrar(PermisoUsuario.CREAR_CLIENTES, ndUsuario::setCrearClientes, ndUsuario::isCrearClientes);
        registrar(PermisoUsuario.CREAR_PROVEEDOR, ndUsuario::setCrearProveedor, ndUsuario::isCrearProveedor);
        registrar(PermisoUsuario.CREAR_EMPLEADO, ndUsuario::setCrearEmpleado, ndUsuario::isCrearEmpleado);
        registrar(PermisoUsuario.CLIENTES_PRESTAMOS, ndUsuario::setClientesPrestamos, ndUsuario::isClientesPrestamos);
        registrar(PermisoUsuario.REPORTE_CLIENTES, ndUsuario::setReporteClientes, ndUsuario::isReporteClientes);
        registrar(PermisoUsuario.REPORTE_PROVEEDOR, ndUsuario::setReporteProveedor, ndUsuario::isReporteProveedor);
        registrar(PermisoUsuario.REPORTE_EMPLEADOS, ndUsuario::setReporteEmpleados, ndUsuario::isReporteEmpleados);
        registrar(PermisoUsuario.ABONOS_CXC, ndUsuario::setAbonosCxC, ndUsuario::isAbonosCxC);
        registrar(PermisoUsuario.ABONOS_CXP, ndUsuario::setAbonosCxP, ndUsuario::isAbonosCxP);
        registrar(PermisoUsuario.NOTAS_CREDITO, ndUsuario::setNotasCredito, ndUsuario::isNotasCredito);
        registrar(PermisoUsuario.NOTAS_DEBITO, ndUsuario::setNotasDebito, ndUsuario::isNotasDebito);
        registrar(PermisoUsuario.REPORTE_CARTERA_CXC, ndUsuario::setReporteCarteraCxC, ndUsuario::isReporteCarteraCxC);
        registrar(PermisoUsuario.REPORTE_ABONOS_CXC, ndUsuario::setReporteAbonosCxC, ndUsuario::isReporteAbonosCxC);
        registrar(PermisoUsuario.REPORTE_CARTERA_CXP, ndUsuario::setReporteCarteraCxP, ndUsuario::isReporteCarteraCxP);
        registrar(PermisoUsuario.REPORTE_ABONOS_CXP, ndUsuario::setReporteAbonosCxP, ndUsuario::isReporteAbonosCxP);
        registrar(PermisoUsuario.REPORTE_NOTAS_CREDITO, ndUsuario::setReporteNotasCredito, ndUsuario::isReporteNotasCredito);
        registrar(PermisoUsuario.REPORTE_NOTAS_DEBITO, ndUsuario::setReporteNotasDebito, ndUsuario::isReporteNotasDebito);
        registrar(PermisoUsuario.CODIGOS, ndUsuario::setCodigos, ndUsuario::isCodigos);
        registrar(PermisoUsuario.EGRESOS, ndUsuario::setEgresos, ndUsuario::isEgresos);
        registrar(PermisoUsuario.CUADRE_CAJA, ndUsuario::setCuadreCaja, ndUsuario::isCuadreCaja);
        registrar(PermisoUsuario.REPORTE_EGRESOS, ndUsuario::setReporteEgresos, ndUsuario::isReporteEgresos);
        registrar(PermisoUsuario.REPORTE_CUADRE_CAJA, ndUsuario::setReporteCuadreCaja, ndUsuario::isReporteCuadreCaja);
        registrar(PermisoUsuario.REGISTRAR_AGENDA, ndUsuario::setRegistrarAgenda, ndUsuario::isRegistrarAgenda);
        registrar(PermisoUsuario.REPORTES_AGENDA, ndUsuario::setReportesAgenda, ndUsuario::isReportesAgenda);
        registrar(PermisoUsuario.ORDEN_SERVICIO_MEDICO, ndUsuario::setOrdenesServicioMedica, ndUsuario::isOrdenesServicioMedica);
        registrar(PermisoUsuario.HISTORIA_CLINICA_MEDICO, ndUsuario::setHistoria, ndUsuario::isHistoria);
        registrar(PermisoUsuario.FORMATOS_MEDICO, ndUsuario::setFormatosMedicas, ndUsuario::isFormatosMedicas);
        registrar(PermisoUsuario.CONVENIO, ndUsuario::setConvenio, ndUsuario::isConvenio);
        registrar(PermisoUsuario.NOTA_ENFERMERIA, ndUsuario::setNotaEnfermaria, ndUsuario::isNotaEnfermaria);
        registrar(PermisoUsuario.MEDICAMENTOS, ndUsuario::setMedicamentos, ndUsuario::isMedicamentos);
        registrar(PermisoUsuario.REPORTE_ORDENES_MEDICAS, ndUsuario::setReporteOrdenes, ndUsuario::isReporteOrdenes);
        registrar(PermisoUsuario.REPORTE_FORMULAS, ndUsuario::setReporteFormulas, ndUsuario::isReporteFormulas);
        registrar(PermisoUsuario.CERTIFICADO_ESTUDIANTE, ndUsuario::setCertificadoEstudiante, ndUsuario::isCertificadoEstudiante);
        registrar(PermisoUsuario.CERTIFICADO_MEDICO, ndUsuario::setCertificadoMedico, ndUsuario::isCertificadoMedico);
        registrar(PermisoUsuario.CERTIFICADO_OYS, ndUsuario::setCertificadoOyS, ndUsuario::isCertificadoOyS);
        registrar(PermisoUsuario.MASCOTAS, ndUsuario::setMascotas, ndUsuario::isMascotas);
        registrar(PermisoUsuario.HISTORIA_CLINICA, ndUsuario::setHistoriaClinica, ndUsuario::isHistoriaClinica);
        registrar(PermisoUsuario.GUARDERIA, ndUsuario::setGuarderia, ndUsuario::isGuarderia);
        registrar(PermisoUsuario.PELUQUERIA, ndUsuario::setPeluqueria, ndUsuario::isPeluqueria);
        registrar(PermisoUsuario.HOSPITALIZACION, ndUsuario::setHospitalizacion, ndUsuario::isHospitalizacion);
        registrar(PermisoUsuario.ALERTAS, ndUsuario::setAlertas, ndUsuario::isAlertas);
        registrar(PermisoUsuario.REPORTE_MASCOTAS, ndUsuario::setReporteMascotas, ndUsuario::isReporteMascotas);
        registrar(PermisoUsuario.REPORTE_GUARDERIA, ndUsuario::setReporteGuarderia, ndUsuario::isReporteGuarderia);
        registrar(PermisoUsuario.REPORTE_PELUQUERIA, ndUsuario::setReportePeluqueria, ndUsuario::isReportePeluqueria);
        registrar(PermisoUsuario.REPORTE_HOSPITALIZACION, ndUsuario::setReporteHospitalizacion, ndUsuario::isReporteHospitalizacion);
        registrar(PermisoUsuario.FORMATOS, ndUsuario::setFormatos, ndUsuario::isFormatos);
    }

    private ConversorUsuario() {
    }

    private static void registrar(PermisoUsuario permiso, BiConsumer<ndUsuario, Boolean> asignar, Predicate<ndUsuario> leer) {
        ASIGNAR.put(permiso, asignar);
        LEER.put(permiso, leer);
    }

    public static ndUsuario aNodo(ModeloUsuario modelo) {
        ndUsuario nodo = new ndUsuario();
        nodo.setNombre(modelo.getNombre());
        nodo.setUsuario(modelo.getUsuario());
        nodo.setContra(modelo.getContra());
        for (Map.Entry<PermisoUsuario, BiConsumer<ndUsuario, Boolean>> entrada : ASIGNAR.entrySet()) {
            entrada.getValue().accept(nodo, modelo.tienePermiso(entrada.getKey()));
        }
        return nodo;
    }

    public static ModeloUsuario desdeNodo(ndUsuario nodo) {
        ModeloUsuario modelo = new ModeloUsuario();
        modelo.setNombre(nodo.getNombre());
        modelo.setUsuario(nodo.getUsuario());
        modelo.setContra(nodo.getContra());
        for (Map.Entry<PermisoUsuario, Predicate<ndUsuario>> entrada : LEER.entrySet()) {
            modelo.setPermiso(entrada.getKey(), entrada.getValue().test(nodo));
        }
        return modelo;
    }
}