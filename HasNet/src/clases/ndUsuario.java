package clases;

public class ndUsuario {

    String nombre, usuario, contra;

    boolean facturas, congeladas, cotizaciones, planSepares, creditos, pedidos, ordenesServicio, anulaciones, reimpresion, reportesVentas, reporteVentasAnuladas, reporteCotizaciones,
            reportePlanSepares, reporteCreditos, reporteOrdenesServicio, reporteIvasVentas, reporteDia, creacionProductos, ordenesCompras, compras, trasladosExternos, trasladosInternos,
            ajustesEntrada, ajustesSalida, grupos, disenos, armados, inventarioInicial, trasladoDetalle, kardex, reporteProductos, reportePrestamos, reporteCompras, reporteAjustes,
            reporteOrdenCompras, reporteInventario, reporteTrasladoInterno, reporteIvasCompras, reporteMovimientoSerial, crearClientes, crearProveedor, crearEmpleado, bodegasInternas,
            clientesPrestamos, showRoom, reporteClientes, reporteProveedor, reporteEmpleados, abonosCxC, abonosCxP, notasCredito, reporteCarteraCxC, reporteAbonosCxC, reporteCarteraCxP,
            reporteAbonosCxP, reporteNotasCredito, codigos, egresos, bancos, cuadreCaja, reporteEgresos, reporteBancos, reporteCuadreCaja, registrarAgenda, reportesAgenda,
            mascotas, historiaClinica, guarderia, peluqueria, hospitalizacion, alertas, reporteMascotas, reporteGuarderia, reportePeluqueria, reporteHospitalizacion, formatos,
            ordenesServicioMedica, historia, formatosMedicas, convenio, notaEnfermaria, medicamentos, reporteOrdenes, reporteFormulas, certificadoEstudiante, certificadoMedico,
            certificadoOyS;

    public boolean isOrdenesServicioMedica() {
        return ordenesServicioMedica;
    }

    public void setOrdenesServicioMedica(boolean ordenesServicioMedica) {
        this.ordenesServicioMedica = ordenesServicioMedica;
    }

    public boolean isHistoria() {
        return historia;
    }

    public void setHistoria(boolean historia) {
        this.historia = historia;
    }

    public boolean isFormatosMedicas() {
        return formatosMedicas;
    }

    public void setFormatosMedicas(boolean formatosMedicas) {
        this.formatosMedicas = formatosMedicas;
    }

    public boolean isConvenio() {
        return convenio;
    }

    public void setConvenio(boolean convenio) {
        this.convenio = convenio;
    }

    public boolean isNotaEnfermaria() {
        return notaEnfermaria;
    }

    public void setNotaEnfermaria(boolean notaEnfermaria) {
        this.notaEnfermaria = notaEnfermaria;
    }

    public boolean isMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(boolean medicamentos) {
        this.medicamentos = medicamentos;
    }

    public boolean isReporteOrdenes() {
        return reporteOrdenes;
    }

    public void setReporteOrdenes(boolean reporteOrdenes) {
        this.reporteOrdenes = reporteOrdenes;
    }

    public boolean isReporteFormulas() {
        return reporteFormulas;
    }

    public void setReporteFormulas(boolean reporteFormulas) {
        this.reporteFormulas = reporteFormulas;
    }

    public boolean isCertificadoEstudiante() {
        return certificadoEstudiante;
    }

    public void setCertificadoEstudiante(boolean certificadoEstudiante) {
        this.certificadoEstudiante = certificadoEstudiante;
    }

    public boolean isCertificadoMedico() {
        return certificadoMedico;
    }

    public void setCertificadoMedico(boolean certificadoMedico) {
        this.certificadoMedico = certificadoMedico;
    }

    public boolean isCertificadoOyS() {
        return certificadoOyS;
    }

    public void setCertificadoOyS(boolean certificadoOyS) {
        this.certificadoOyS = certificadoOyS;
    }

    public boolean isMascotas() {
        return mascotas;
    }

    public void setMascotas(boolean mascotas) {
        this.mascotas = mascotas;
    }

    public boolean isHistoriaClinica() {
        return historiaClinica;
    }

    public void setHistoriaClinica(boolean historiaClinica) {
        this.historiaClinica = historiaClinica;
    }

    public boolean isGuarderia() {
        return guarderia;
    }

    public void setGuarderia(boolean guarderia) {
        this.guarderia = guarderia;
    }

    public boolean isPeluqueria() {
        return peluqueria;
    }

    public void setPeluqueria(boolean peluqueria) {
        this.peluqueria = peluqueria;
    }

    public boolean isHospitalizacion() {
        return hospitalizacion;
    }

    public void setHospitalizacion(boolean hospitalizacion) {
        this.hospitalizacion = hospitalizacion;
    }

    public boolean isAlertas() {
        return alertas;
    }

    public void setAlertas(boolean alertas) {
        this.alertas = alertas;
    }

    public boolean isReporteMascotas() {
        return reporteMascotas;
    }

    public void setReporteMascotas(boolean reporteMascotas) {
        this.reporteMascotas = reporteMascotas;
    }

    public boolean isReporteGuarderia() {
        return reporteGuarderia;
    }

    public void setReporteGuarderia(boolean reporteGuarderia) {
        this.reporteGuarderia = reporteGuarderia;
    }

    public boolean isReportePeluqueria() {
        return reportePeluqueria;
    }

    public void setReportePeluqueria(boolean reportePeluqueria) {
        this.reportePeluqueria = reportePeluqueria;
    }

    public boolean isReporteHospitalizacion() {
        return reporteHospitalizacion;
    }

    public void setReporteHospitalizacion(boolean reporteHospitalizacion) {
        this.reporteHospitalizacion = reporteHospitalizacion;
    }

    public boolean isFormatos() {
        return formatos;
    }

    public void setFormatos(boolean formatos) {
        this.formatos = formatos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    public boolean isFacturas() {
        return facturas;
    }

    public void setFacturas(boolean facturas) {
        this.facturas = facturas;
    }

    public boolean isCongeladas() {
        return congeladas;
    }

    public void setCongeladas(boolean congeladas) {
        this.congeladas = congeladas;
    }

    public boolean isCotizaciones() {
        return cotizaciones;
    }

    public void setCotizaciones(boolean cotizaciones) {
        this.cotizaciones = cotizaciones;
    }

    public boolean isPlanSepares() {
        return planSepares;
    }

    public void setPlanSepares(boolean planSepares) {
        this.planSepares = planSepares;
    }

    public boolean isCreditos() {
        return creditos;
    }

    public void setCreditos(boolean creditos) {
        this.creditos = creditos;
    }

    public boolean isPedidos() {
        return pedidos;
    }

    public void setPedidos(boolean pedidos) {
        this.pedidos = pedidos;
    }

    public boolean isOrdenesServicio() {
        return ordenesServicio;
    }

    public void setOrdenesServicio(boolean ordenesServicio) {
        this.ordenesServicio = ordenesServicio;
    }

    public boolean isAnulaciones() {
        return anulaciones;
    }

    public void setAnulaciones(boolean anulaciones) {
        this.anulaciones = anulaciones;
    }

    public boolean isReimpresion() {
        return reimpresion;
    }

    public void setReimpresion(boolean reimpresion) {
        this.reimpresion = reimpresion;
    }

    public boolean isReportesVentas() {
        return reportesVentas;
    }

    public void setReportesVentas(boolean reportesVentas) {
        this.reportesVentas = reportesVentas;
    }

    public boolean isReporteVentasAnuladas() {
        return reporteVentasAnuladas;
    }

    public void setReporteVentasAnuladas(boolean reporteVentasAnuladas) {
        this.reporteVentasAnuladas = reporteVentasAnuladas;
    }

    public boolean isReporteCotizaciones() {
        return reporteCotizaciones;
    }

    public void setReporteCotizaciones(boolean reporteCotizaciones) {
        this.reporteCotizaciones = reporteCotizaciones;
    }

    public boolean isReportePlanSepares() {
        return reportePlanSepares;
    }

    public void setReportePlanSepares(boolean reportePlanSepares) {
        this.reportePlanSepares = reportePlanSepares;
    }

    public boolean isReporteCreditos() {
        return reporteCreditos;
    }

    public void setReporteCreditos(boolean reporteCreditos) {
        this.reporteCreditos = reporteCreditos;
    }

    public boolean isReporteOrdenesServicio() {
        return reporteOrdenesServicio;
    }

    public void setReporteOrdenesServicio(boolean reporteOrdenesServicio) {
        this.reporteOrdenesServicio = reporteOrdenesServicio;
    }

    public boolean isReporteIvasVentas() {
        return reporteIvasVentas;
    }

    public void setReporteIvasVentas(boolean reporteIvasVentas) {
        this.reporteIvasVentas = reporteIvasVentas;
    }

    public boolean isReporteDia() {
        return reporteDia;
    }

    public void setReporteDia(boolean reporteDia) {
        this.reporteDia = reporteDia;
    }

    public boolean isCreacionProductos() {
        return creacionProductos;
    }

    public void setCreacionProductos(boolean creacionProductos) {
        this.creacionProductos = creacionProductos;
    }

    public boolean isOrdenesCompras() {
        return ordenesCompras;
    }

    public void setOrdenesCompras(boolean ordenesCompras) {
        this.ordenesCompras = ordenesCompras;
    }

    public boolean isCompras() {
        return compras;
    }

    public void setCompras(boolean compras) {
        this.compras = compras;
    }

    public boolean isTrasladosExternos() {
        return trasladosExternos;
    }

    public void setTrasladosExternos(boolean trasladosExternos) {
        this.trasladosExternos = trasladosExternos;
    }

    public boolean isTrasladosInternos() {
        return trasladosInternos;
    }

    public void setTrasladosInternos(boolean trasladosInternos) {
        this.trasladosInternos = trasladosInternos;
    }

    public boolean isAjustesEntrada() {
        return ajustesEntrada;
    }

    public void setAjustesEntrada(boolean ajustesEntrada) {
        this.ajustesEntrada = ajustesEntrada;
    }

    public boolean isAjustesSalida() {
        return ajustesSalida;
    }

    public void setAjustesSalida(boolean ajustesSalida) {
        this.ajustesSalida = ajustesSalida;
    }

    public boolean isGrupos() {
        return grupos;
    }

    public void setGrupos(boolean grupos) {
        this.grupos = grupos;
    }

    public boolean isDisenos() {
        return disenos;
    }

    public void setDisenos(boolean disenos) {
        this.disenos = disenos;
    }

    public boolean isArmados() {
        return armados;
    }

    public void setArmados(boolean armados) {
        this.armados = armados;
    }

    public boolean isInventarioInicial() {
        return inventarioInicial;
    }

    public void setInventarioInicial(boolean inventarioInicial) {
        this.inventarioInicial = inventarioInicial;
    }

    public boolean isTrasladoDetalle() {
        return trasladoDetalle;
    }

    public void setTrasladoDetalle(boolean trasladoDetalle) {
        this.trasladoDetalle = trasladoDetalle;
    }

    public boolean isKardex() {
        return kardex;
    }

    public void setKardex(boolean kardex) {
        this.kardex = kardex;
    }

    public boolean isReporteProductos() {
        return reporteProductos;
    }

    public void setReporteProductos(boolean reporteProductos) {
        this.reporteProductos = reporteProductos;
    }

    public boolean isReportePrestamos() {
        return reportePrestamos;
    }

    public void setReportePrestamos(boolean reportePrestamos) {
        this.reportePrestamos = reportePrestamos;
    }

    public boolean isReporteCompras() {
        return reporteCompras;
    }

    public void setReporteCompras(boolean reporteCompras) {
        this.reporteCompras = reporteCompras;
    }

    public boolean isReporteAjustes() {
        return reporteAjustes;
    }

    public void setReporteAjustes(boolean reporteAjustes) {
        this.reporteAjustes = reporteAjustes;
    }

    public boolean isReporteOrdenCompras() {
        return reporteOrdenCompras;
    }

    public void setReporteOrdenCompras(boolean reporteOrdenCompras) {
        this.reporteOrdenCompras = reporteOrdenCompras;
    }

    public boolean isReporteInventario() {
        return reporteInventario;
    }

    public void setReporteInventario(boolean reporteInventario) {
        this.reporteInventario = reporteInventario;
    }

    public boolean isReporteTrasladoInterno() {
        return reporteTrasladoInterno;
    }

    public void setReporteTrasladoInterno(boolean reporteTrasladoInterno) {
        this.reporteTrasladoInterno = reporteTrasladoInterno;
    }

    public boolean isReporteIvasCompras() {
        return reporteIvasCompras;
    }

    public void setReporteIvasCompras(boolean reporteIvasCompras) {
        this.reporteIvasCompras = reporteIvasCompras;
    }

    public boolean isReporteMovimientoSerial() {
        return reporteMovimientoSerial;
    }

    public void setReporteMovimientoSerial(boolean reporteMovimientoSerial) {
        this.reporteMovimientoSerial = reporteMovimientoSerial;
    }

    public boolean isCrearClientes() {
        return crearClientes;
    }

    public void setCrearClientes(boolean crearClientes) {
        this.crearClientes = crearClientes;
    }

    public boolean isCrearProveedor() {
        return crearProveedor;
    }

    public void setCrearProveedor(boolean crearProveedor) {
        this.crearProveedor = crearProveedor;
    }

    public boolean isCrearEmpleado() {
        return crearEmpleado;
    }

    public void setCrearEmpleado(boolean crearEmpleado) {
        this.crearEmpleado = crearEmpleado;
    }

    public boolean isBodegasInternas() {
        return bodegasInternas;
    }

    public void setBodegasInternas(boolean bodegasInternas) {
        this.bodegasInternas = bodegasInternas;
    }

    public boolean isClientesPrestamos() {
        return clientesPrestamos;
    }

    public void setClientesPrestamos(boolean clientesPrestamos) {
        this.clientesPrestamos = clientesPrestamos;
    }

    public boolean isShowRoom() {
        return showRoom;
    }

    public void setShowRoom(boolean showRoom) {
        this.showRoom = showRoom;
    }

    public boolean isReporteClientes() {
        return reporteClientes;
    }

    public void setReporteClientes(boolean reporteClientes) {
        this.reporteClientes = reporteClientes;
    }

    public boolean isReporteProveedor() {
        return reporteProveedor;
    }

    public void setReporteProveedor(boolean reporteProveedor) {
        this.reporteProveedor = reporteProveedor;
    }

    public boolean isReporteEmpleados() {
        return reporteEmpleados;
    }

    public void setReporteEmpleados(boolean reporteEmpleados) {
        this.reporteEmpleados = reporteEmpleados;
    }

    public boolean isAbonosCxC() {
        return abonosCxC;
    }

    public void setAbonosCxC(boolean abonosCxC) {
        this.abonosCxC = abonosCxC;
    }

    public boolean isAbonosCxP() {
        return abonosCxP;
    }

    public void setAbonosCxP(boolean abonosCxP) {
        this.abonosCxP = abonosCxP;
    }

    public boolean isNotasCredito() {
        return notasCredito;
    }

    public void setNotasCredito(boolean notasCredito) {
        this.notasCredito = notasCredito;
    }

    public boolean isReporteCarteraCxC() {
        return reporteCarteraCxC;
    }

    public void setReporteCarteraCxC(boolean reporteCarteraCxC) {
        this.reporteCarteraCxC = reporteCarteraCxC;
    }

    public boolean isReporteAbonosCxC() {
        return reporteAbonosCxC;
    }

    public void setReporteAbonosCxC(boolean reporteAbonosCxC) {
        this.reporteAbonosCxC = reporteAbonosCxC;
    }

    public boolean isReporteCarteraCxP() {
        return reporteCarteraCxP;
    }

    public void setReporteCarteraCxP(boolean reporteCarteraCxP) {
        this.reporteCarteraCxP = reporteCarteraCxP;
    }

    public boolean isReporteAbonosCxP() {
        return reporteAbonosCxP;
    }

    public void setReporteAbonosCxP(boolean reporteAbonosCxP) {
        this.reporteAbonosCxP = reporteAbonosCxP;
    }

    public boolean isReporteNotasCredito() {
        return reporteNotasCredito;
    }

    public void setReporteNotasCredito(boolean reporteNotasCredito) {
        this.reporteNotasCredito = reporteNotasCredito;
    }

    public boolean isCodigos() {
        return codigos;
    }

    public void setCodigos(boolean codigos) {
        this.codigos = codigos;
    }

    public boolean isEgresos() {
        return egresos;
    }

    public void setEgresos(boolean egresos) {
        this.egresos = egresos;
    }

    public boolean isBancos() {
        return bancos;
    }

    public void setBancos(boolean bancos) {
        this.bancos = bancos;
    }

    public boolean isCuadreCaja() {
        return cuadreCaja;
    }

    public void setCuadreCaja(boolean cuadreCaja) {
        this.cuadreCaja = cuadreCaja;
    }

    public boolean isReporteEgresos() {
        return reporteEgresos;
    }

    public void setReporteEgresos(boolean reporteEgresos) {
        this.reporteEgresos = reporteEgresos;
    }

    public boolean isReporteBancos() {
        return reporteBancos;
    }

    public void setReporteBancos(boolean reporteBancos) {
        this.reporteBancos = reporteBancos;
    }

    public boolean isReporteCuadreCaja() {
        return reporteCuadreCaja;
    }

    public void setReporteCuadreCaja(boolean reporteCuadreCaja) {
        this.reporteCuadreCaja = reporteCuadreCaja;
    }

    public boolean isRegistrarAgenda() {
        return registrarAgenda;
    }

    public void setRegistrarAgenda(boolean registrarAgenda) {
        this.registrarAgenda = registrarAgenda;
    }

    public boolean isReportesAgenda() {
        return reportesAgenda;
    }

    public void setReportesAgenda(boolean reportesAgenda) {
        this.reportesAgenda = reportesAgenda;
    }

}
