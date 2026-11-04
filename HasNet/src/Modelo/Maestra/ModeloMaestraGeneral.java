package Modelo.Maestra;

public class ModeloMaestraGeneral {

    private String nitEmpresa, nombreEmpresa, regimenEmpresa, direccionEmpresa, telefonoEmpresa, tituloFactura,
            configuracionRegimen, foco, alertaUtilidad, tipoDescuento, contrasena;

    private String inicioHosp, finHosp, intervalosHosp, horaInicioAgenda, horaFinAgenda, intervaloAgenda, codPrestadorServicio,
            tipoIdPrestadorServicio;

    private int cantidadEstablecida, turnoConsecutivo, diasAlertaCuadre, filas, columnas, porcPropina,
            descuentoMaximoVentas, limiteProductosPorFactura, diasCobroMora;

    private boolean usarLector, preciosConImpuesto, ventasPredeterminado, mensajeUtilidad, facturarSinInventario,
            consecutivosDiferentes, mostrarDevuelta, consecutivoAdicional, combinarProductos, modificarNombre, modificarPrecio,
            facturarMesas, soloMesas, borrarMesas, borrarCongelada, turno, visualizarTodasLasFacturas, mostrarInformacionCuadre;

    private boolean generaOrdenMedica, imprimirOrdenMedica, imprimirFacturaOrdenMedica, diasAutomaticos, ciudadBuscador,
            ocultarInformacionCliente;

    private String alertaFechaDias, alertaCantidadNumeracion, alertaPromedioDias, porcentajeMora,
            password, responsabilidadesFiscales;

    public String getConfiguracionRegimen() {
        return configuracionRegimen;
    }

    public void setConfiguracionRegimen(String configuracionRegimen) {
        this.configuracionRegimen = configuracionRegimen;
    }

    public String getAlertaUtilidad() {
        return alertaUtilidad;
    }

    public void setAlertaUtilidad(String alertaUtilidad) {
        this.alertaUtilidad = alertaUtilidad;
    }

    public String getTipoDescuento() {
        return tipoDescuento;
    }

    public void setTipoDescuento(String tipoDescuento) {
        this.tipoDescuento = tipoDescuento;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public int getDiasAlertaCuadre() {
        return diasAlertaCuadre;
    }

    public void setDiasAlertaCuadre(int diasAlertaCuadre) {
        this.diasAlertaCuadre = diasAlertaCuadre;
    }

    public String getNitEmpresa() {
        return nitEmpresa;
    }

    public void setNitEmpresa(String nitEmpresa) {
        this.nitEmpresa = nitEmpresa;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getRegimenEmpresa() {
        return regimenEmpresa;
    }

    public void setRegimenEmpresa(String regimenEmpresa) {
        this.regimenEmpresa = regimenEmpresa;
    }

    public String getDireccionEmpresa() {
        return direccionEmpresa;
    }

    public void setDireccionEmpresa(String direccionEmpresa) {
        this.direccionEmpresa = direccionEmpresa;
    }

    public String getTelefonoEmpresa() {
        return telefonoEmpresa;
    }

    public void setTelefonoEmpresa(String telefonoEmpresa) {
        this.telefonoEmpresa = telefonoEmpresa;
    }

    public boolean isUsarLector() {
        return usarLector;
    }

    public void setUsarLector(boolean usarLector) {
        this.usarLector = usarLector;
    }

    public boolean isPreciosConImpuesto() {
        return preciosConImpuesto;
    }

    public void setPreciosConImpuesto(boolean preciosConImpuesto) {
        this.preciosConImpuesto = preciosConImpuesto;
    }

    public boolean isVentasPredeterminado() {
        return ventasPredeterminado;
    }

    public void setVentasPredeterminado(boolean ventasPredeterminado) {
        this.ventasPredeterminado = ventasPredeterminado;
    }

    public boolean isMensajeUtilidad() {
        return mensajeUtilidad;
    }

    public void setMensajeUtilidad(boolean mensajeUtilidad) {
        this.mensajeUtilidad = mensajeUtilidad;
    }

    public boolean isConsecutivosDiferentes() {
        return consecutivosDiferentes;
    }

    public void setConsecutivosDiferentes(boolean consecutivosDiferentes) {
        this.consecutivosDiferentes = consecutivosDiferentes;
    }

    public String getTituloFactura() {
        return tituloFactura;
    }

    public void setTituloFactura(String tituloFactura) {
        this.tituloFactura = tituloFactura;
    }

    public String getAlertaFechaDias() {
        return alertaFechaDias;
    }

    public void setAlertaFechaDias(String alertaFechaDias) {
        this.alertaFechaDias = alertaFechaDias;
    }

    public String getAlertaCantidadNumeracion() {
        return alertaCantidadNumeracion;
    }

    public void setAlertaCantidadNumeracion(String alertaCantidadNumeracion) {
        this.alertaCantidadNumeracion = alertaCantidadNumeracion;
    }

    public String getAlertaPromedioDias() {
        return alertaPromedioDias;
    }

    public void setAlertaPromedioDias(String alertaPromedioDias) {
        this.alertaPromedioDias = alertaPromedioDias;
    }

    public int getDiasCobroMora() {
        return diasCobroMora;
    }

    public void setDiasCobroMora(int diasCobroMora) {
        this.diasCobroMora = diasCobroMora;
    }

    public String getPorcentajeMora() {
        return porcentajeMora;
    }

    public void setPorcentajeMora(String porcentajeMora) {
        this.porcentajeMora = porcentajeMora;
    }

    public boolean isGeneraOrdenMedica() {
        return generaOrdenMedica;
    }

    public void setGeneraOrdenMedica(boolean generaOrdenMedica) {
        this.generaOrdenMedica = generaOrdenMedica;
    }

    public boolean isImprimirOrdenMedica() {
        return imprimirOrdenMedica;
    }

    public void setImprimirOrdenMedica(boolean imprimirOrdenMedica) {
        this.imprimirOrdenMedica = imprimirOrdenMedica;
    }

    public boolean isImprimirFacturaOrdenMedica() {
        return imprimirFacturaOrdenMedica;
    }

    public void setImprimirFacturaOrdenMedica(boolean imprimirFacturaOrdenMedica) {
        this.imprimirFacturaOrdenMedica = imprimirFacturaOrdenMedica;
    }

    public String getHoraInicioAgenda() {
        return horaInicioAgenda;
    }

    public void setHoraInicioAgenda(String horaInicioAgenda) {
        this.horaInicioAgenda = horaInicioAgenda;
    }

    public String getHoraFinAgenda() {
        return horaFinAgenda;
    }

    public void setHoraFinAgenda(String horaFinAgenda) {
        this.horaFinAgenda = horaFinAgenda;
    }

    public String getIntervaloAgenda() {
        return intervaloAgenda;
    }

    public void setIntervaloAgenda(String intervaloAgenda) {
        this.intervaloAgenda = intervaloAgenda;
    }

    public String getCodPrestadorServicio() {
        return codPrestadorServicio;
    }

    public void setCodPrestadorServicio(String codPrestadorServicio) {
        this.codPrestadorServicio = codPrestadorServicio;
    }

    public boolean isVisualizarTodasLasFacturas() {
        return visualizarTodasLasFacturas;
    }

    public void setVisualizarTodasLasFacturas(boolean visualizarTodasLasFacturas) {
        this.visualizarTodasLasFacturas = visualizarTodasLasFacturas;
    }

    public boolean isMostrarInformacionCuadre() {
        return mostrarInformacionCuadre;
    }

    public void setMostrarInformacionCuadre(boolean mostrarInformacionCuadre) {
        this.mostrarInformacionCuadre = mostrarInformacionCuadre;
    }

    public int getDescuentoMaximoVentas() {
        return descuentoMaximoVentas;
    }

    public void setDescuentoMaximoVentas(int descuentoMaximoVentas) {
        this.descuentoMaximoVentas = descuentoMaximoVentas;
    }

    public String getTipoIdPrestadorServicio() {
        return tipoIdPrestadorServicio;
    }

    public void setTipoIdPrestadorServicio(String tipoIdPrestadorServicio) {
        this.tipoIdPrestadorServicio = tipoIdPrestadorServicio;
    }

    public int getLimiteProductosPorFactura() {
        return limiteProductosPorFactura;
    }

    public void setLimiteProductosPorFactura(int limiteProductosPorFactura) {
        this.limiteProductosPorFactura = limiteProductosPorFactura;
    }

    public boolean isCombinarProductos() {
        return combinarProductos;
    }

    public void setCombinarProductos(boolean combinarProductos) {
        this.combinarProductos = combinarProductos;
    }

    public boolean isModificarNombre() {
        return modificarNombre;
    }

    public void setModificarNombre(boolean modificarNombre) {
        this.modificarNombre = modificarNombre;
    }

    public boolean isTurno() {
        return turno;
    }

    public void setTurno(boolean turno) {
        this.turno = turno;
    }

    public int getTurnoConsecutivo() {
        return turnoConsecutivo;
    }

    public void setTurnoConsecutivo(int turnoConsecutivo) {
        this.turnoConsecutivo = turnoConsecutivo;
    }

    public boolean isConsecutivoAdicional() {
        return consecutivoAdicional;
    }

    public void setConsecutivoAdicional(boolean consecutivoAdicional) {
        this.consecutivoAdicional = consecutivoAdicional;
    }

    public boolean isModificarPrecio() {
        return modificarPrecio;
    }

    public void setModificarPrecio(boolean modificarPrecio) {
        this.modificarPrecio = modificarPrecio;
    }

    public boolean isBorrarCongelada() {
        return borrarCongelada;
    }

    public void setBorrarCongelada(boolean borrarCongelada) {
        this.borrarCongelada = borrarCongelada;
    }

    public boolean isFacturarSinInventario() {
        return facturarSinInventario;
    }

    public void setFacturarSinInventario(boolean facturarSinInventario) {
        this.facturarSinInventario = facturarSinInventario;
    }

    public int getCantidadEstablecida() {
        return cantidadEstablecida;
    }

    public void setCantidadEstablecida(int cantidadEstablecida) {
        this.cantidadEstablecida = cantidadEstablecida;
    }

    public int getFilas() {
        return filas;
    }

    public void setFilas(int filas) {
        this.filas = filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public void setColumnas(int columnas) {
        this.columnas = columnas;
    }

    public int getPorcPropina() {
        return porcPropina;
    }

    public void setPorcPropina(int porcPropina) {
        this.porcPropina = porcPropina;
    }

    public boolean isMostrarDevuelta() {
        return mostrarDevuelta;
    }

    public void setMostrarDevuelta(boolean mostrarDevuelta) {
        this.mostrarDevuelta = mostrarDevuelta;
    }

    public String getInicioHosp() {
        return inicioHosp;
    }

    public void setInicioHosp(String inicioHosp) {
        this.inicioHosp = inicioHosp;
    }

    public String getFinHosp() {
        return finHosp;
    }

    public void setFinHosp(String finHosp) {
        this.finHosp = finHosp;
    }

    public String getIntervalosHosp() {
        return intervalosHosp;
    }

    public void setIntervalosHosp(String intervalosHosp) {
        this.intervalosHosp = intervalosHosp;
    }

    public boolean isDiasAutomaticos() {
        return diasAutomaticos;
    }

    public void setDiasAutomaticos(boolean diasAutomaticos) {
        this.diasAutomaticos = diasAutomaticos;
    }

    public boolean isCiudadBuscador() {
        return ciudadBuscador;
    }

    public void setCiudadBuscador(boolean ciudadBuscador) {
        this.ciudadBuscador = ciudadBuscador;
    }

    public boolean isOcultarInformacionCliente() {
        return ocultarInformacionCliente;
    }

    public void setOcultarInformacionCliente(boolean ocultarInformacionCliente) {
        this.ocultarInformacionCliente = ocultarInformacionCliente;
    }

    public String getFoco() {
        return foco;
    }

    public void setFoco(String foco) {
        this.foco = foco;
    }

    public boolean isFacturarMesas() {
        return facturarMesas;
    }

    public void setFacturarMesas(boolean facturarMesas) {
        this.facturarMesas = facturarMesas;
    }

    public boolean isSoloMesas() {
        return soloMesas;
    }

    public void setSoloMesas(boolean soloMesas) {
        this.soloMesas = soloMesas;
    }

    public boolean isBorrarMesas() {
        return borrarMesas;
    }

    public void setBorrarMesas(boolean borrarMesas) {
        this.borrarMesas = borrarMesas;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getResponsabilidadesFiscales() {
        return responsabilidadesFiscales;
    }

    public void setResponsabilidadesFiscales(String responsabilidadesFiscales) {
        this.responsabilidadesFiscales = responsabilidadesFiscales;
    }

}
