package clases;

public class ndMaestra {

    private String l1, l2, l3, l4, c1, c2, c3, c4, c5, d1, d2, d3, d4, d5, d6, d7, pie, legal, c6, c7, tituloFactura, diasAlertaResolucion, alertaFechaDias, alertaCantidadNumeracion, alertaPromedioDias;
    private boolean recogida, lector, pvpSinIva, costoSinIva, ventasPredeterminado, mensajeUtilidad, resolucionIgual;
    private boolean generaOrdenMedica;
    private boolean imprimirOrdenMedica, imprimirFacturaOrdenMedica;
    private boolean imprimirCuadreFiscal;
    private boolean ocultarInformacionCliente;
    private boolean visualizarTodasLasFacturas;
    private boolean mostrarInformacionCuadre;
    private boolean consecutivosDiferentes, modificarNombre, modificarPrecio, combinarProductos, impBolsa, turno, consecutivo, hora, pondNegativo, borrarCongelada, facturarSeparado,
            pagosTerceros, reimpresion, diasAutomaticos, ciudadBuscador;

    private String horaInicioAgenda, horaFinAgenda, intervaloAgenda;
    private String descuentoMaximoVentas, valorBolsa;
    private String codigoPrestadorServicio;
    private String tipoPrestadorServicio, otros, domicilios, limite, turno1, informacion, anexoOrdenServicio, foco;

    private boolean copiasFactura, copiasOServicio, copiasCotizacion, copiasPlanSepare, copiasPedido, previsualizarFactura, previsualizarOServicio, previsualizarCotizacion,
            previsualizarPlanSepare, previsualizarPedido, mostrarRetenciones, mostrarImpoconsumo, mostrarDevuelta, pvpImpoconsumo, costoImpoconsumo, impresionPorGrupo,
            previsualizarComanda, previsualizarPrefactura, facturarMesas, soloMesas, borrarMesas;

    private String numFactura, numOServicio, numCotizacion, numPlanSepare, numPedido, impresoraCarta, impresoraMediaCarta, impresoraPos, imprimirCada,
            cantidadEstablecida, filas, columnas, porcPropina, inicioHosp, finHosp, intervalosHosp, copiasComanda, copiasPrefactura, impresoraComanda, impresoraPrefactura,
            diasCobrarMora, porcentajeMora, idCliente, password, numFacturaIncremento, responsabilidadesFiscales, gmail, passwordGmail;

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getPasswordGmail() {
        return passwordGmail;
    }

    public void setPasswordGmail(String passwordGmail) {
        this.passwordGmail = passwordGmail;
    }

    public String getResponsabilidadesFiscales() {
        return responsabilidadesFiscales;
    }

    public void setResponsabilidadesFiscales(String responsabilidadesFiscales) {
        this.responsabilidadesFiscales = responsabilidadesFiscales;
    }

    public String getNumFacturaIncremento() {
        return numFacturaIncremento;
    }

    public void setNumFacturaIncremento(String numFacturaIncremento) {
        this.numFacturaIncremento = numFacturaIncremento;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isBorrarMesas() {
        return borrarMesas;
    }

    public void setBorrarMesas(boolean borrarMesas) {
        this.borrarMesas = borrarMesas;
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

    public boolean isPrevisualizarComanda() {
        return previsualizarComanda;
    }

    public void setPrevisualizarComanda(boolean previsualizarComanda) {
        this.previsualizarComanda = previsualizarComanda;
    }

    public boolean isPrevisualizarPrefactura() {
        return previsualizarPrefactura;
    }

    public void setPrevisualizarPrefactura(boolean previsualizarPrefactura) {
        this.previsualizarPrefactura = previsualizarPrefactura;
    }

    public String getCopiasComanda() {
        return copiasComanda;
    }

    public void setCopiasComanda(String copiasComanda) {
        this.copiasComanda = copiasComanda;
    }

    public String getCopiasPrefactura() {
        return copiasPrefactura;
    }

    public void setCopiasPrefactura(String copiasPrefactura) {
        this.copiasPrefactura = copiasPrefactura;
    }

    public String getImpresoraComanda() {
        return impresoraComanda;
    }

    public void setImpresoraComanda(String impresoraComanda) {
        this.impresoraComanda = impresoraComanda;
    }

    public String getImpresoraPrefactura() {
        return impresoraPrefactura;
    }

    public void setImpresoraPrefactura(String impresoraPrefactura) {
        this.impresoraPrefactura = impresoraPrefactura;
    }

    public boolean isImpresionPorGrupo() {
        return impresionPorGrupo;
    }

    public void setImpresionPorGrupo(boolean impresionPorGrupo) {
        this.impresionPorGrupo = impresionPorGrupo;
    }

    public boolean isPvpImpoconsumo() {
        return pvpImpoconsumo;
    }

    public void setPvpImpoconsumo(boolean pvpImpoconsumo) {
        this.pvpImpoconsumo = pvpImpoconsumo;
    }

    public boolean isCostoImpoconsumo() {
        return costoImpoconsumo;
    }

    public void setCostoImpoconsumo(boolean costoImpoconsumo) {
        this.costoImpoconsumo = costoImpoconsumo;
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

    public String getFoco() {
        return foco;
    }

    public void setFoco(String foco) {
        this.foco = foco;
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

    public boolean isMostrarDevuelta() {
        return mostrarDevuelta;
    }

    public void setMostrarDevuelta(boolean mostrarDevuelta) {
        this.mostrarDevuelta = mostrarDevuelta;
    }

    public String getPorcPropina() {
        return porcPropina;
    }

    public void setPorcPropina(String porcPropina) {
        this.porcPropina = porcPropina;
    }

    public String getFilas() {
        return filas;
    }

    public void setFilas(String filas) {
        this.filas = filas;
    }

    public String getColumnas() {
        return columnas;
    }

    public void setColumnas(String columnas) {
        this.columnas = columnas;
    }

    public String getCantidadEstablecida() {
        return cantidadEstablecida;
    }

    public void setCantidadEstablecida(String cantidadEstablecida) {
        this.cantidadEstablecida = cantidadEstablecida;
    }

    public String getImprimirCada() {
        return imprimirCada;
    }

    public void setImprimirCada(String imprimirCada) {
        this.imprimirCada = imprimirCada;
    }

    public boolean isMostrarRetenciones() {
        return mostrarRetenciones;
    }

    public void setMostrarRetenciones(boolean mostrarRetenciones) {
        this.mostrarRetenciones = mostrarRetenciones;
    }

    public boolean isMostrarImpoconsumo() {
        return mostrarImpoconsumo;
    }

    public void setMostrarImpoconsumo(boolean mostrarImpoconsumo) {
        this.mostrarImpoconsumo = mostrarImpoconsumo;
    }

    public String getImpresoraCarta() {
        return impresoraCarta;
    }

    public void setImpresoraCarta(String impresoraCarta) {
        this.impresoraCarta = impresoraCarta;
    }

    public String getImpresoraMediaCarta() {
        return impresoraMediaCarta;
    }

    public void setImpresoraMediaCarta(String impresoraMediaCarta) {
        this.impresoraMediaCarta = impresoraMediaCarta;
    }

    public String getImpresoraPos() {
        return impresoraPos;
    }

    public void setImpresoraPos(String impresoraPos) {
        this.impresoraPos = impresoraPos;
    }

    public boolean isReimpresion() {
        return reimpresion;
    }

    public void setReimpresion(boolean reimpresion) {
        this.reimpresion = reimpresion;
    }

    public boolean isFacturarSeparado() {
        return facturarSeparado;
    }

    public void setFacturarSeparado(boolean facturarSeparado) {
        this.facturarSeparado = facturarSeparado;
    }

    public boolean isPagosTerceros() {
        return pagosTerceros;
    }

    public void setPagosTerceros(boolean pagosTerceros) {
        this.pagosTerceros = pagosTerceros;
    }

    public boolean isCopiasFactura() {
        return copiasFactura;
    }

    public void setCopiasFactura(boolean copiasFactura) {
        this.copiasFactura = copiasFactura;
    }

    public boolean isCopiasOServicio() {
        return copiasOServicio;
    }

    public void setCopiasOServicio(boolean copiasOServicio) {
        this.copiasOServicio = copiasOServicio;
    }

    public boolean isCopiasCotizacion() {
        return copiasCotizacion;
    }

    public void setCopiasCotizacion(boolean copiasCotizacion) {
        this.copiasCotizacion = copiasCotizacion;
    }

    public boolean isCopiasPlanSepare() {
        return copiasPlanSepare;
    }

    public void setCopiasPlanSepare(boolean copiasPlanSepare) {
        this.copiasPlanSepare = copiasPlanSepare;
    }

    public boolean isCopiasPedido() {
        return copiasPedido;
    }

    public void setCopiasPedido(boolean copiasPedido) {
        this.copiasPedido = copiasPedido;
    }

    public boolean isPrevisualizarOServicio() {
        return previsualizarOServicio;
    }

    public void setPrevisualizarOServicio(boolean previsualizarOServicio) {
        this.previsualizarOServicio = previsualizarOServicio;
    }

    public boolean isPrevisualizarCotizacion() {
        return previsualizarCotizacion;
    }

    public void setPrevisualizarCotizacion(boolean previsualizarCotizacion) {
        this.previsualizarCotizacion = previsualizarCotizacion;
    }

    public boolean isPrevisualizarPlanSepare() {
        return previsualizarPlanSepare;
    }

    public void setPrevisualizarPlanSepare(boolean previsualizarPlanSepare) {
        this.previsualizarPlanSepare = previsualizarPlanSepare;
    }

    public boolean isPrevisualizarPedido() {
        return previsualizarPedido;
    }

    public void setPrevisualizarPedido(boolean previsualizarPedido) {
        this.previsualizarPedido = previsualizarPedido;
    }

    public String getNumFactura() {
        return numFactura;
    }

    public void setNumFactura(String numFactura) {
        this.numFactura = numFactura;
    }

    public String getNumOServicio() {
        return numOServicio;
    }

    public void setNumOServicio(String numOServicio) {
        this.numOServicio = numOServicio;
    }

    public String getNumCotizacion() {
        return numCotizacion;
    }

    public void setNumCotizacion(String numCotizacion) {
        this.numCotizacion = numCotizacion;
    }

    public String getNumPlanSepare() {
        return numPlanSepare;
    }

    public void setNumPlanSepare(String numPlanSepare) {
        this.numPlanSepare = numPlanSepare;
    }

    public String getNumPedido() {
        return numPedido;
    }

    public void setNumPedido(String numPedido) {
        this.numPedido = numPedido;
    }

    public boolean isBorrarCongelada() {
        return borrarCongelada;
    }

    public void setBorrarCongelada(boolean borrarCongelada) {
        this.borrarCongelada = borrarCongelada;
    }

    public boolean isModificarPrecio() {
        return modificarPrecio;
    }

    public void setModificarPrecio(boolean modificarPrecio) {
        this.modificarPrecio = modificarPrecio;
    }

    public String getAnexoOrdenServicio() {
        return anexoOrdenServicio;
    }

    public void setAnexoOrdenServicio(String anexoOrdenServicio) {
        this.anexoOrdenServicio = anexoOrdenServicio;
    }

    public boolean isPondNegativo() {
        return pondNegativo;
    }

    public void setPondNegativo(boolean pondNegativo) {
        this.pondNegativo = pondNegativo;
    }

    public boolean isHora() {
        return hora;
    }

    public void setHora(boolean hora) {
        this.hora = hora;
    }

    public boolean isConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(boolean consecutivo) {
        this.consecutivo = consecutivo;
    }

    public String getInformacion() {
        return informacion;
    }

    public void setInformacion(String informacion) {
        this.informacion = informacion;
    }

    public boolean isTurno() {
        return turno;
    }

    public void setTurno(boolean turno) {
        this.turno = turno;
    }

    public String getTurno1() {
        return turno1;
    }

    public void setTurno1(String turno1) {
        this.turno1 = turno1;
    }

    public boolean isImpBolsa() {
        return impBolsa;
    }

    public void setImpBolsa(boolean impBolsa) {
        this.impBolsa = impBolsa;
    }

    public String getValorBolsa() {
        return valorBolsa;
    }

    public void setValorBolsa(String valorBolsa) {
        this.valorBolsa = valorBolsa;
    }

    public boolean isModificarNombre() {
        return modificarNombre;
    }

    public void setModificarNombre(boolean modificarNombre) {
        this.modificarNombre = modificarNombre;
    }

    public boolean isCombinarProductos() {
        return combinarProductos;
    }

    public void setCombinarProductos(boolean combinarProductos) {
        this.combinarProductos = combinarProductos;
    }

    public String getLimite() {
        return limite;
    }

    public void setLimite(String limite) {
        this.limite = limite;
    }

    public String getDomicilios() {
        return domicilios;
    }

    public void setDomicilios(String domicilios) {
        this.domicilios = domicilios;
    }

    public String getOtros() {
        return otros;
    }

    public void setOtros(String otros) {
        this.otros = otros;
    }

    public String getTipoPrestadorServicio() {
        return tipoPrestadorServicio;
    }

    public void setTipoPrestadorServicio(String tipoPrestadorServicio) {
        this.tipoPrestadorServicio = tipoPrestadorServicio;
    }

    public String getCodigoPrestadorServicio() {
        return codigoPrestadorServicio;
    }

    public void setCodigoPrestadorServicio(String codigoPrestadorServicio) {
        this.codigoPrestadorServicio = codigoPrestadorServicio;
    }

    public String getDescuentoMaximoVentas() {
        return descuentoMaximoVentas;
    }

    public void setDescuentoMaximoVentas(String descuentoMaximoVentas) {
        this.descuentoMaximoVentas = descuentoMaximoVentas;
    }

    public boolean isConsecutivosDiferentes() {
        return consecutivosDiferentes;
    }

    public void setConsecutivosDiferentes(boolean consecutivosDiferentes) {
        this.consecutivosDiferentes = consecutivosDiferentes;
    }

    public boolean isMostrarInformacionCuadre() {
        return mostrarInformacionCuadre;
    }

    public void setMostrarInformacionCuadre(boolean mostrarInformacionCuadre) {
        this.mostrarInformacionCuadre = mostrarInformacionCuadre;
    }

    public boolean isVisualizarTodasLasFacturas() {
        return visualizarTodasLasFacturas;
    }

    public void setVisualizarTodasLasFacturas(boolean visualizarTodasLasFacturas) {
        this.visualizarTodasLasFacturas = visualizarTodasLasFacturas;
    }

    public boolean isOcultarInformacionCliente() {
        return ocultarInformacionCliente;
    }

    public void setOcultarInformacionCliente(boolean ocultarInformacionCliente) {
        this.ocultarInformacionCliente = ocultarInformacionCliente;
    }

    public boolean isImprimirCuadreFiscal() {
        return imprimirCuadreFiscal;
    }

    public void setImprimirCuadreFiscal(boolean imprimirCuadreFiscal) {
        this.imprimirCuadreFiscal = imprimirCuadreFiscal;
    }

    public boolean isPrevisualizarFactura() {
        return previsualizarFactura;
    }

    public void setPrevisualizarFactura(boolean previsualizarFactura) {
        this.previsualizarFactura = previsualizarFactura;
    }

    public String getIntervaloAgenda() {
        return intervaloAgenda;
    }

    public void setIntervaloAgenda(String intervaloAgenda) {
        this.intervaloAgenda = intervaloAgenda;
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

    public boolean isGeneraOrdenMedica() {
        return generaOrdenMedica;
    }

    public void setGeneraOrdenMedica(boolean generaOrdenMedica) {
        this.generaOrdenMedica = generaOrdenMedica;
    }

    public String getDiasCobrarMora() {
        return diasCobrarMora;
    }

    public void setDiasCobrarMora(String diasCobrarMora) {
        this.diasCobrarMora = diasCobrarMora;
    }

    public String getPorcentajeMora() {
        return porcentajeMora;
    }

    public void setPorcentajeMora(String porcentajeMora) {
        this.porcentajeMora = porcentajeMora;
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

    public String getDiasAlertaResolucion() {
        return diasAlertaResolucion;
    }

    public void setDiasAlertaResolucion(String diasAlertaResolucion) {
        this.diasAlertaResolucion = diasAlertaResolucion;
    }

    public boolean isResolucionIgual() {
        return resolucionIgual;
    }

    public void setResolucionIgual(boolean resolucionIgual) {
        this.resolucionIgual = resolucionIgual;
    }

    public boolean isCostoSinIva() {
        return costoSinIva;
    }

    public void setCostoSinIva(boolean costoSinIva) {
        this.costoSinIva = costoSinIva;
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

    public boolean isPvpSinIva() {
        return pvpSinIva;
    }

    public void setPvpSinIva(boolean pvpSinIva) {
        this.pvpSinIva = pvpSinIva;
    }

    public String getTituloFactura() {
        return tituloFactura;
    }

    public void setTituloFactura(String tituloFactura) {
        this.tituloFactura = tituloFactura;
    }

    public boolean isLector() {
        return lector;
    }

    public void setLector(boolean lector) {
        this.lector = lector;
    }

    public boolean isRecogida() {
        return recogida;
    }

    public void setRecogida(boolean recogida) {
        this.recogida = recogida;
    }

    public String getC6() {
        return c6;
    }

    public void setC6(String c6) {
        this.c6 = c6;
    }

    public String getC7() {
        return c7;
    }

    public void setC7(String c7) {
        this.c7 = c7;
    }

    public String getL1() {
        return l1;
    }

    public void setL1(String l1) {
        this.l1 = l1;
    }

    public String getL2() {
        return l2;
    }

    public void setL2(String l2) {
        this.l2 = l2;
    }

    public String getL3() {
        return l3;
    }

    public void setL3(String l3) {
        this.l3 = l3;
    }

    public String getL4() {
        return l4;
    }

    public void setL4(String l4) {
        this.l4 = l4;
    }

    public String getC1() {
        return c1;
    }

    public void setC1(String c1) {
        this.c1 = c1;
    }

    public String getC2() {
        return c2;
    }

    public void setC2(String c2) {
        this.c2 = c2;
    }

    public String getC3() {
        return c3;
    }

    public void setC3(String c3) {
        this.c3 = c3;
    }

    public String getC4() {
        return c4;
    }

    public void setC4(String c4) {
        this.c4 = c4;
    }

    public String getC5() {
        return c5;
    }

    public void setC5(String c5) {
        this.c5 = c5;
    }

    public String getD1() {
        return d1;
    }

    public void setD1(String d1) {
        this.d1 = d1;
    }

    public String getD2() {
        return d2;
    }

    public void setD2(String d2) {
        this.d2 = d2;
    }

    public String getD3() {
        return d3;
    }

    public void setD3(String d3) {
        this.d3 = d3;
    }

    public String getD4() {
        return d4;
    }

    public void setD4(String d4) {
        this.d4 = d4;
    }

    public String getD5() {
        return d5;
    }

    public void setD5(String d5) {
        this.d5 = d5;
    }

    public String getD6() {
        return d6;
    }

    public void setD6(String d6) {
        this.d6 = d6;
    }

    public String getD7() {
        return d7;
    }

    public void setD7(String d7) {
        this.d7 = d7;
    }

    public String getPie() {
        return pie;
    }

    public void setPie(String pie) {
        this.pie = pie;
    }

    public String getLegal() {
        return legal;
    }

    public void setLegal(String legal) {
        this.legal = legal;
    }

}
