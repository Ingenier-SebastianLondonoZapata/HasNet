package Modelo.Usuarios;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum PermisoUsuario {

    // ---------------- Ventas ----------------
    FACTURAS("facturas", "Realizar facturas", Modulo.VENTAS),
    CONGELADAS("congeladas", "Realizar congeladas", Modulo.VENTAS),
    COTIZACIONES("cotizaciones", "Realizar cotizaciones", Modulo.VENTAS),
    PLAN_SEPARES("planSepares", "Realizar plan separes", Modulo.VENTAS),
    CREDITOS("creditos", "Realizar créditos", Modulo.VENTAS),
    PEDIDOS("pedidos", "Realizar pedidos", Modulo.VENTAS),
    ORDENES_SERVICIO("ordenesServicio", "Realizar  órdenes de servicio", Modulo.VENTAS),
    ANULACIONES("anulaciones", "Realizar anulaciones", Modulo.VENTAS),
    REIMPRESION("reimpresion", "Generar reimpresiones", Modulo.VENTAS),
    REPORTE_VENTAS("reportesVentas", "Generar reporte de ventas", Modulo.VENTAS),
    REPORTE_VENTAS_ANULADAS("reporteVentasAnuladas", "Generar reporte de ventas anuladas", Modulo.VENTAS),
    REPORTE_COTIZACIONES("reporteCotizaciones", "Generar reporte de cotizaciones", Modulo.VENTAS),
    REPORTE_PLAN_SEPARES("reportePlanSepares", "Generar reporte de plan separes", Modulo.VENTAS),
    REPORTE_CREDITOS("reporteCreditos", "Generar reporte de créditos", Modulo.VENTAS),
    REPORTE_ORDENES_SERVICIO("reporteOrdenesServicio", "Generar reporte de órdenes de servicio", Modulo.VENTAS),
    REPORTE_IVAS_VENTAS("reporteIvasVentas", "Generar reporte de IVAs de ventas", Modulo.VENTAS),
    REPORTE_DIA("reporteDia", "Generar reporte del día", Modulo.VENTAS),
    // ---------------- Productos ----------------
    CREACION_PRODUCTOS("creacionProductos", "Crear productos", Modulo.PRODUCTOS),
    ORDENES_COMPRAS("ordenesCompras", "Realizar órdenes de compra", Modulo.PRODUCTOS),
    COMPRAS("compras", "Realizar compras", Modulo.PRODUCTOS),
    TRASLADOS_EXTERNOS("trasladosExternos", "Realizar traslado externo", Modulo.PRODUCTOS),
    TRASLADOS_INTERNOS("trasladosInternos", "Realizar traslado interno", Modulo.PRODUCTOS),
    AJUSTES_ENTRADA("ajustesEntrada", "Realizar ajuste de entrada", Modulo.PRODUCTOS),
    AJUSTES_SALIDA("ajustesSalida", "Realizar ajuste de salida", Modulo.PRODUCTOS),
    GRUPOS("grupos", "Crear grupos", Modulo.PRODUCTOS),
    DISENOS("disenos", "Crear diseños", Modulo.PRODUCTOS),
    ARMADOS("armados", "Crear armados", Modulo.PRODUCTOS),
    INVENTARIO_INICIAL("inventarioInicial", "Generar inventario inicial", Modulo.PRODUCTOS),
    REPORTE_PRODUCTOS("reporteProductos", "Generar reporte de productos", Modulo.PRODUCTOS),
    REPORTE_COMPRAS("reporteCompras", "Generar reporte de compras", Modulo.PRODUCTOS),
    REPORTE_AJUSTES("reporteAjustes", "Generar reporte de ajustes", Modulo.PRODUCTOS),
    REPORTE_ORDEN_COMPRAS("reporteOrdenCompras", "Generar reporte de órdenes de compra", Modulo.PRODUCTOS),
    REPORTE_INVENTARIO("reporteInventario", "Generar reporte de inventario", Modulo.PRODUCTOS),
    REPORTE_TRASLADO_INTERNO("reporteTrasladoInterno", "Generar reporte de traslados internos", Modulo.PRODUCTOS),
    REPORTE_TRASLADO_EXTERNO("reporteTrasladoExterno", "Generar reporte de traslados externos", Modulo.PRODUCTOS),
    REPORTE_IVAS_COMPRAS("reporteIvasCompras", "Generar reporte de IVAs de compras", Modulo.PRODUCTOS),
    REPORTE_MOVIMIENTO_SERIAL("reporteMovimientoSerial", "Generar reporte de movimientos serial", Modulo.PRODUCTOS),
    // ---------------- Terceros ----------------
    CREAR_CLIENTES("crearClientes", "Crear clientes", Modulo.TERCEROS),
    CREAR_PROVEEDOR("crearProveedor", "Crear proveedor", Modulo.TERCEROS),
    CREAR_EMPLEADO("crearEmpleado", "Crear empleado", Modulo.TERCEROS),
    CLIENTES_PRESTAMOS("clientesPrestamos", "Crear bodegas del cliente", Modulo.TERCEROS),
    REPORTE_CLIENTES("reporteClientes", "Generar reporte de clientes", Modulo.TERCEROS),
    REPORTE_PROVEEDOR("reporteProveedor", "Generar reporte de proveedores", Modulo.TERCEROS),
    REPORTE_EMPLEADOS("reporteEmpleados", "Generar reporte de empleados", Modulo.TERCEROS),
    // ---------------- Cartera ----------------
    ABONOS_CXC("abonosCxC", "Realizar abonos cuentas por cobrar", Modulo.CARTERA),
    ABONOS_CXP("abonosCxP", "Realizar abonos cuentas por pagar", Modulo.CARTERA),
    NOTAS_CREDITO("notasCredito", "Realizar notas crédito", Modulo.CARTERA),
    NOTAS_DEBITO("notasDebito", "Realizar notas débito", Modulo.CARTERA),
    REPORTE_CARTERA_CXC("reporteCarteraCxC", "Generar reporte cartera cuentas por cobrar", Modulo.CARTERA),
    REPORTE_ABONOS_CXC("reporteAbonosCxC", "Generar reporte abonos cuentas por cobrar", Modulo.CARTERA),
    REPORTE_CARTERA_CXP("reporteCarteraCxP", "Generar reporte cartera cuentas por pagar", Modulo.CARTERA),
    REPORTE_ABONOS_CXP("reporteAbonosCxP", "Generar reporte abonos cuentas por pagar", Modulo.CARTERA),
    REPORTE_NOTAS_CREDITO("reporteNotasCredito", "Generar reporte de notas crédito", Modulo.CARTERA),
    REPORTE_NOTAS_DEBITO("reporteNotasDebito", "Generar reporte de notas débito", Modulo.CARTERA),
    // ---------------- Tesorería ----------------
    CODIGOS("codigos", "Crear códigos egresos", Modulo.TESORERIA),
    EGRESOS("egresos", "Realizar egresos", Modulo.TESORERIA),
    CUADRE_CAJA("cuadreCaja", "Realizar cuadre de caja", Modulo.TESORERIA),
    REPORTE_EGRESOS("reporteEgresos", "Generar reporte de egresos", Modulo.TESORERIA),
    REPORTE_CUADRE_CAJA("reporteCuadreCaja", "Generar reporte de cuadre de caja", Modulo.TESORERIA),
    // ---------------- Agenda ----------------
    REGISTRAR_AGENDA("registrarAgenda", "Registrar agenda", Modulo.AGENDA),
    REPORTES_AGENDA("reportesAgenda", "Generar reportes de agenda", Modulo.AGENDA),
    // ---------------- Médico ----------------
    ORDEN_SERVICIO_MEDICO("ordenServicioMedico", "Orden de servicio", Modulo.MEDICO),
    HISTORIA_CLINICA_MEDICO("historiaClinicaMedico", "Historia clínica", Modulo.MEDICO),
    FORMATOS_MEDICO("formatosMedico", "Formatos", Modulo.MEDICO),
    CONVENIO("convenio", "Convenio", Modulo.MEDICO),
    NOTA_ENFERMERIA("notaEnfermeria", "Nota de enfermería", Modulo.MEDICO),
    MEDICAMENTOS("medicamentos", "Medicamentos", Modulo.MEDICO),
    REPORTE_ORDENES_MEDICAS("reporteOrdenesMedicas", "Reporte de órdenes", Modulo.MEDICO),
    REPORTE_FORMULAS("reporteFormulas", "Reporte de fórmulas", Modulo.MEDICO),
    CERTIFICADO_ESTUDIANTE("certificadoEstudiante", "Certificado estudiante", Modulo.MEDICO),
    CERTIFICADO_MEDICO("certificadoMedico", "Certificado médico", Modulo.MEDICO),
    CERTIFICADO_OYS("certificadoOyS", "Certificado OyS", Modulo.MEDICO),
    // ---------------- Veterinaria ----------------
    MASCOTAS("mascotas", "Mascotas", Modulo.VETERINARIA),
    HISTORIA_CLINICA("historiaClinica", "Historia clínica", Modulo.VETERINARIA),
    GUARDERIA("guarderia", "Guardería", Modulo.VETERINARIA),
    PELUQUERIA("peluqueria", "Peluquería", Modulo.VETERINARIA),
    HOSPITALIZACION("hospitalizacion", "Ingreso a hospitalización", Modulo.VETERINARIA),
    ALERTAS("alertas", "Alertas", Modulo.VETERINARIA),
    REPORTE_MASCOTAS("reporteMascotas", "Reporte de mascotas", Modulo.VETERINARIA),
    REPORTE_GUARDERIA("reporteGuarderia", "Reporte de guardería", Modulo.VETERINARIA),
    REPORTE_PELUQUERIA("reportePeluqueria", "Reporte de peluquería", Modulo.VETERINARIA),
    REPORTE_HOSPITALIZACION("reporteHospitalizacion", "Reporte de hospitalización", Modulo.VETERINARIA),
    FORMATOS("formatos", "Formatos", Modulo.VETERINARIA);

    public enum Modulo {
        VENTAS, PRODUCTOS, TERCEROS, CARTERA, TESORERIA, AGENDA, MEDICO, VETERINARIA
    }

    private final String columna;
    private final String etiqueta;
    private final Modulo modulo;

    PermisoUsuario(String columna, String etiqueta, Modulo modulo) {
        this.columna = columna;
        this.etiqueta = etiqueta;
        this.modulo = modulo;
    }

    /**
     * Nombre de la columna en bdUsuario.
     */
    public String getColumna() {
        return columna;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public Modulo getModulo() {
        return modulo;
    }

    public static List<PermisoUsuario> delModulo(Modulo modulo) {
        List<PermisoUsuario> permisos = new ArrayList<>();
        for (PermisoUsuario permiso : values()) {
            if (permiso.modulo == modulo) {
                permisos.add(permiso);
            }
        }
        return Collections.unmodifiableList(permisos);
    }
}
