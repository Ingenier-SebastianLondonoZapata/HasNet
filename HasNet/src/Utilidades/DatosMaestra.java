package Utilidades;

import clases.ndMaestra;

public class DatosMaestra {

    private static ndMaestra maestra;
    private static String responsabilidadesFiscales;

    public static void setearDatosMaestra(ndMaestra datos) {
        maestra = datos;
        responsabilidadesFiscales = procesarResponsabilidades(datos.getResponsabilidadesFiscales());
    }

    private static String procesarResponsabilidades(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            return "";
        }
        String[] partes = texto.trim().split(", ");
        StringBuilder sb = new StringBuilder();
        for (String parte : partes) {
            String codigo = parte.split("\\s*/")[0].trim();
            if (!codigo.isEmpty()) {
                if (sb.length() > 0) {
                    sb.append(';');
                }
                sb.append(codigo);
            }
        }
        return sb.toString();
    }

    // ── Encabezado empresa ──────────────────────────────────────────────────
    public static String getL1() { return maestra.getL1(); }
    public static String getL2() { return maestra.getL2(); }
    public static String getL4() { return maestra.getL4(); }
    public static String getC1() { return maestra.getC1(); }
    public static String getC2() { return maestra.getC2(); }
    public static String getC3() { return maestra.getC3(); }
    public static String getC4() { return maestra.getC4(); }
    public static String getC5() { return maestra.getC5(); }
    public static String getD1() { return maestra.getD1(); }
    public static String getD2() { return maestra.getD2(); }
    public static String getD3() { return maestra.getD3(); }
    public static String getD4() { return maestra.getD4(); }
    public static String getD5() { return maestra.getD5(); }
    public static String getD6() { return maestra.getD6(); }
    public static String getD7() { return maestra.getD7(); }
    public static String getPie() { return maestra.getPie(); }
    public static String getLegal() { return maestra.getLegal(); }
    public static String getC6() { return maestra.getC6(); }
    public static String getC7() { return maestra.getC7(); }

    // ── Alias semánticos para compatibilidad ────────────────────────────────
    /** Tipo de descuento (columna c4) */
    public static String getTipoDescuento() { return maestra.getC4(); }
    /** Mostrar ubicación de productos (columna c6) */
    public static String getMostrarUbicacionProductos() { return maestra.getC6(); }
    /** Nombre de la empresa (columna d1) */
    public static String getNombreEmpresa() { return maestra.getD1(); }
    /** NIT de la empresa (columna c1) */
    public static String getNitEmpresa() { return maestra.getC1(); }

    // ── Factura / POS ───────────────────────────────────────────────────────
    public static boolean isRecogida() { return maestra.isRecogida(); }
    public static boolean isLector() { return maestra.isLector(); }
    public static String getTituloFactura() { return maestra.getTituloFactura(); }
    public static boolean isPvpSinIva() { return maestra.isPvpSinIva(); }
    public static boolean isCostoSinIva() { return maestra.isCostoSinIva(); }
    public static boolean isVentasPredeterminado() { return maestra.isVentasPredeterminado(); }
    public static boolean isMensajeUtilidad() { return maestra.isMensajeUtilidad(); }
    public static boolean isConsecutivosDiferentes() { return maestra.isConsecutivosDiferentes(); }
    public static boolean isResolucionIgual() { return maestra.isResolucionIgual(); }
    public static boolean isPrevisualizarFactura() { return maestra.isPrevisualizarFactura(); }
    public static boolean isImprimirCuadreFiscal() { return maestra.isImprimirCuadreFiscal(); }
    public static boolean isOcultarInformacionTercero() { return maestra.isOcultarInformacionCliente(); }
    public static boolean isOcultarInformacionCliente() { return maestra.isOcultarInformacionCliente(); }
    public static boolean isVisualizarTodasLasFacturas() { return maestra.isVisualizarTodasLasFacturas(); }
    public static boolean isMostrarInformacionCuadre() { return maestra.isMostrarInformacionCuadre(); }
    public static String getDescuentoMaximoVentas() { return maestra.getDescuentoMaximoVentas(); }
    public static boolean isModificarNombre() { return maestra.isModificarNombre(); }
    public static boolean isModificarPrecio() { return maestra.isModificarPrecio(); }
    public static boolean isCombinarProductos() { return maestra.isCombinarProductos(); }
    public static boolean isImpBolsa() { return maestra.isImpBolsa(); }
    public static String getValorBolsa() { return maestra.getValorBolsa(); }
    public static boolean isBorrarCongelada() { return maestra.isBorrarCongelada(); }
    public static boolean isPondNegativo() { return maestra.isPondNegativo(); }
    public static boolean isHora() { return maestra.isHora(); }
    public static String getAnexoFacturacion() { return maestra.getAnexoFacturacion(); }
    public static String getAnexoOrdenServicio() { return maestra.getAnexoOrdenServicio(); }

    // ── Turno ───────────────────────────────────────────────────────────────
    public static boolean isTurnoActivo() { return maestra.isTurno(); }
    public static String getNumeroTurno() { return maestra.getTurno1() != null ? maestra.getTurno1() : "1"; }
    /** Consecutivo adicional por turno */
    public static boolean isConsecutivoAdicional() { return maestra.isConsecutivo(); }

    // ── Inventario / Ventas ─────────────────────────────────────────────────
    public static boolean isFacturarSinInventario() { return maestra.isFacturarSeparado(); }
    public static boolean isPagosTerceros() { return maestra.isPagosTerceros(); }
    public static boolean isReimpresion() { return maestra.isReimpresion(); }
    public static String getOtros() { return maestra.getOtros(); }
    public static String getDomicilios() { return maestra.getDomicilios(); }
    public static String getLimite() { return maestra.getLimite(); }

    // ── Cantidades / Foco ───────────────────────────────────────────────────
    public static String getCantidadEstablecidaAlCargar() {
        return maestra.getCantidadEstablecida() != null ? maestra.getCantidadEstablecida() : "1";
    }
    public static String getFocoDespuesDeCargarProducto() {
        return maestra.getFoco() != null ? maestra.getFoco() : "Cant";
    }
    public static boolean isCargarDiasPlazoAutomaticamente() { return maestra.isDiasAutomaticos(); }
    public static boolean isCiudadBuscador() { return maestra.isCiudadBuscador(); }

    // ── Alertas resolución ──────────────────────────────────────────────────
    public static String getDiasAlertaResolucion() { return maestra.getDiasAlertaResolucion(); }
    public static String getAlertaFechaDias() { return maestra.getAlertaFechaDias(); }
    public static String getAlertaCantidadNumeracion() { return maestra.getAlertaCantidadNumeracion(); }
    public static String getAlertaPromedioDias() { return maestra.getAlertaPromedioDias(); }

    // ── Cartera / Mora ──────────────────────────────────────────────────────
    public static String getDiasCobrarMora() { return maestra.getDiasCobrarMora(); }
    public static String getPorcentajeMora() { return maestra.getPorcentajeMora(); }

    // ── Orden médica ────────────────────────────────────────────────────────
    public static boolean isGeneraOrdenMedica() { return maestra.isGeneraOrdenMedica(); }
    public static boolean isImprimirOrdenMedica() { return maestra.isImprimirOrdenMedica(); }
    public static boolean isImprimirFacturaOrdenMedica() { return maestra.isImprimirFacturaOrdenMedica(); }

    // ── Agenda ──────────────────────────────────────────────────────────────
    public static String getHoraInicioAgenda() { return maestra.getHoraInicioAgenda(); }
    public static String getHoraFinAgenda() { return maestra.getHoraFinAgenda(); }
    public static String getIntervaloAgenda() { return maestra.getIntervaloAgenda(); }

    // ── Prestador servicio ──────────────────────────────────────────────────
    public static String getCodigoPrestadorServicio() { return maestra.getCodigoPrestadorServicio(); }
    public static String getTipoPrestadorServicio() { return maestra.getTipoPrestadorServicio(); }

    // ── Copias / Previsualización ────────────────────────────────────────────
    public static boolean isCopiasFactura() { return maestra.isCopiasFactura(); }
    public static boolean isCopiasOServicio() { return maestra.isCopiasOServicio(); }
    public static boolean isCopiasCotizacion() { return maestra.isCopiasCotizacion(); }
    public static boolean isCopiasPlanSepare() { return maestra.isCopiasPlanSepare(); }
    public static boolean isCopiasPedido() { return maestra.isCopiasPedido(); }
    public static boolean isPrevisualizarOServicio() { return maestra.isPrevisualizarOServicio(); }
    public static boolean isPrevisualizarCotizacion() { return maestra.isPrevisualizarCotizacion(); }
    public static boolean isPrevisualizarPlanSepare() { return maestra.isPrevisualizarPlanSepare(); }
    public static boolean isPrevisualizarPedido() { return maestra.isPrevisualizarPedido(); }

    // ── Numeración documentos ───────────────────────────────────────────────
    public static String getNumFactura() { return maestra.getNumFactura(); }
    public static String getNumOServicio() { return maestra.getNumOServicio(); }
    public static String getNumCotizacion() { return maestra.getNumCotizacion(); }
    public static String getNumPlanSepare() { return maestra.getNumPlanSepare(); }
    public static String getNumPedido() { return maestra.getNumPedido(); }
    public static String getNumFacturaIncremento() { return maestra.getNumFacturaIncremento(); }

    // ── Impresoras ──────────────────────────────────────────────────────────
    public static String getImpresoraPos() { return maestra.getImpresoraPos(); }
    public static String getImpresoraMediaCarta() { return maestra.getImpresoraMediaCarta(); }
    public static String getImpresoraCarta() { return maestra.getImpresoraCarta(); }
    public static String getImpresoraComanda() { return maestra.getImpresoraComanda(); }
    public static String getImpresoraPrefactura() { return maestra.getImpresoraPrefactura(); }
    public static String getImprimirCada() { return maestra.getImprimirCada(); }
    public static boolean isMostrarImpoconsumo() { return maestra.isMostrarImpoconsumo(); }
    public static boolean isMostrarRetenciones() { return maestra.isMostrarRetenciones(); }

    // ── Pantalla / UI ────────────────────────────────────────────────────────
    public static String getFilas() { return maestra.getFilas(); }
    public static String getColumnas() { return maestra.getColumnas(); }
    public static String getPorcPropina() { return maestra.getPorcPropina(); }
    public static boolean isMostrarDevuelta() { return maestra.isMostrarDevuelta(); }
    public static boolean isPvpImpoconsumo() { return maestra.isPvpImpoconsumo(); }
    public static boolean isCostoImpoconsumo() { return maestra.isCostoImpoconsumo(); }
    public static boolean isImpresionPorGrupo() { return maestra.isImpresionPorGrupo(); }

    // ── Hospitalización ─────────────────────────────────────────────────────
    public static String getInicioHosp() { return maestra.getInicioHosp(); }
    public static String getFinHosp() { return maestra.getFinHosp(); }
    public static String getIntervalosHosp() { return maestra.getIntervalosHosp(); }

    // ── Comanda / Prefactura ────────────────────────────────────────────────
    public static String getCopiasComanda() { return maestra.getCopiasComanda(); }
    public static String getCopiasPrefactura() { return maestra.getCopiasPrefactura(); }
    public static boolean isPrevisualizarComanda() { return maestra.isPrevisualizarComanda(); }
    public static boolean isPrevisualizarPrefactura() { return maestra.isPrevisualizarPrefactura(); }

    // ── Mesas / Restaurante ─────────────────────────────────────────────────
    public static boolean isFacturarMesas() { return maestra.isFacturarMesas(); }
    public static boolean isSoloMesas() { return maestra.isSoloMesas(); }
    public static boolean isBorrarMesas() { return maestra.isBorrarMesas(); }

    // ── Credenciales / Notificaciones ───────────────────────────────────────
    public static String getIdUsuario() { return maestra.getIdCliente(); }
    public static String getPassword() { return maestra.getPassword(); }
    public static String getGmail() { return maestra.getGmail(); }
    public static String getPassGmail() { return maestra.getPasswordGmail(); }

    // ── Responsabilidades fiscales ──────────────────────────────────────────
    /** Valor procesado para facturación electrónica (e.g. "Gran Contribuyente;Autoretenedor") */
    public static String getResponsabilidadesFiscales() { return responsabilidadesFiscales; }
    /** Valor original de la BD (e.g. "Gran Contribuyente / 01, Autoretenedor / 02") */
    public static String getResponsabilidadesRaw() { return maestra.getResponsabilidadesFiscales(); }
}
