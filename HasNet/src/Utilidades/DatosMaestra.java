package Utilidades;

import Modelo.Maestra.ModeloMaestraGeneral;
import Modelo.Maestra.ModeloMaestraImpresiones;

public class DatosMaestra {

    private static ModeloMaestraGeneral general;
    private static ModeloMaestraImpresiones impresiones;
    private static String responsabilidadesFiscales;

    public static void setearDatosMaestra(ModeloMaestraGeneral g, ModeloMaestraImpresiones i) {
        general = g;
        impresiones = i;
        responsabilidadesFiscales = procesarResponsabilidades(g != null ? g.getResponsabilidadesFiscales() : null);
    }

    public static final String RESPONSABILIDAD_NO_APLICA = "ZZ";

    private static String procesarResponsabilidades(String texto) {
        java.util.LinkedHashSet<String> codigos = new java.util.LinkedHashSet<>();
        if (texto != null) {
            for (String parte : texto.split(",")) {
                String codigo = parte.split("\\s*/")[0].trim().toUpperCase();
                if (!codigo.isEmpty()) {
                    codigos.add(codigo);
                }
            }
        }
        if (codigos.size() > 1) {
            codigos.remove(RESPONSABILIDAD_NO_APLICA);
        }
        if (codigos.isEmpty()) {
            return RESPONSABILIDAD_NO_APLICA;
        }
        return String.join(";", codigos);
    }

    private static String s(String value) {
        return value != null ? value : "";
    }

    public static String getL2() {
        return impresiones != null ? s(impresiones.getTipoPredeterminado()) : "";
    }

    public static String getL4() {
        return impresiones != null ? String.valueOf(impresiones.getNumeroCopiasFactura()) : "0";
    }

    public static String getC2() {
        return general != null ? s(general.getConfiguracionRegimen()) : "";
    }

    public static String getC3() {
        return general != null ? s(general.getAlertaUtilidad()) : "";
    }

    public static String getC4() {
        return general != null ? s(general.getTipoDescuento()) : "";
    }

    public static String getC5() {
        return general != null ? s(general.getContrasena()) : "";
    }

    public static String getD1() {
        return general != null ? s(general.getNitEmpresa()) : "";
    }

    public static String getD2() {
        return general != null ? s(general.getNombreEmpresa()) : "";
    }

    public static String getD3() {
        return general != null ? s(general.getRegimenEmpresa()) : "";
    }

    public static String getD6() {
        return general != null ? s(general.getDireccionEmpresa()) : "";
    }

    public static String getD7() {
        return general != null ? s(general.getTelefonoEmpresa()) : "";
    }

    public static String getPie() {
        return impresiones != null ? s(impresiones.getPiePagina()) : "";
    }

    public static String getLegal() {
        return impresiones != null ? s(impresiones.getInformacionLegal()) : "";
    }

    public static String getC6() {
        return impresiones != null ? s(impresiones.getMostrarUbicacion()) : "SI";
    }

    public static String getC7() {
        return general != null ? String.valueOf(general.getDiasAlertaCuadre()) : "0";
    }

    // ── Alias semánticos ────────────────────────────────────────────────────
    public static final String DESCUENTO_PORCENTAJE = "Porcentaje";
    public static final String DESCUENTO_VALOR = "Valor";

    public static String normalizarTipoDescuento(String tipo) {
        String t = tipo == null ? "" : tipo.trim();
        if (t.equalsIgnoreCase(DESCUENTO_VALOR) || t.equalsIgnoreCase("peso")) {
            return DESCUENTO_VALOR;
        }
        return DESCUENTO_PORCENTAJE;
    }

    public static String getTipoDescuento() {
        return normalizarTipoDescuento(general != null ? general.getTipoDescuento() : "");
    }

    public static boolean isDescuentoPorcentaje() {
        return DESCUENTO_PORCENTAJE.equals(getTipoDescuento());
    }

    public static String getMostrarUbicacionProductos() {
        return impresiones != null ? s(impresiones.getMostrarUbicacion()) : "SI";
    }

    public static String getNombreEmpresa() {
        return general != null ? s(general.getNitEmpresa()) : "";
    }

    public static String getNitEmpresa() {
        return "";
    }

    // ── Factura / POS ───────────────────────────────────────────────────────
    public static boolean isRecogida() {
        return impresiones != null && impresiones.isImprimirRecodiga();
    }

    public static boolean isLector() {
        return general != null && general.isUsarLector();
    }

    public static String getTituloFactura() {
        return general != null ? s(general.getTituloFactura()) : "";
    }

    public static boolean isPvpSinIva() {
        return general != null && general.isPreciosConImpuesto();
    }

    // ── Impuestos incluidos en precios y costos ─────────────────────────────
    // La opción general "Precios con impuestos" indica que los precios de
    // venta y los costos ya traen incluidos el IVA y el impoconsumo.
    /**
     * Opción general "Precios con impuestos": el valor ya incluye IVA e
     * impoconsumo.
     */
    public static boolean isPreciosConImpuestos() {
        return general != null && general.isPreciosConImpuesto();
    }

    /**
     * El precio de venta ya incluye el IVA.
     */
    public static boolean isPrecioVentaConIva() {
        return isPreciosConImpuestos();
    }

    /**
     * El costo de compra ya incluye el IVA.
     */
    public static boolean isCostoConIva() {
        return isPreciosConImpuestos();
    }

    /**
     * El precio de venta ya incluye el impoconsumo.
     */
    public static boolean isPrecioVentaConImpoconsumo() {
        return isPreciosConImpuestos();
    }

    /**
     * El costo de compra ya incluye el impoconsumo.
     */
    public static boolean isCostoConImpoconsumo() {
        return isPreciosConImpuestos();
    }

    // ── Bodega ──────────────────────────────────────────────────────────────
    /**
     * Código de la bodega con la que trabaja el inventario; vacío si no hay
     * una configurada (Bodegas.predeterminada usa entonces la principal).
     */
    public static String getBodega() {
        return general != null ? s(general.getBodega()) : "";
    }

    public static boolean isVentasPredeterminado() {
        return general != null && general.isVentasPredeterminado();
    }

    public static boolean isMensajeUtilidad() {
        return general != null && general.isMensajeUtilidad();
    }

    public static boolean isPrevisualizarFactura() {
        return impresiones != null && impresiones.isPrevisualizarFactura();
    }

    public static boolean isImprimirCuadreFiscal() {
        return impresiones != null && impresiones.isImprimirCuadreFiscal();
    }

    /**
     * "Validar el cupo del crédito": al facturar a crédito se valida el cupo y
     * el plazo del cliente contra su cartera pendiente.
     */
    public static boolean isValidarCupoCredito() {
        return general != null && general.isValidarCupoCredito();
    }

    public static boolean isVisualizarTodasLasFacturas() {
        return general != null && general.isVisualizarTodasLasFacturas();
    }

    public static boolean isMostrarInformacionCuadre() {
        return general != null && general.isMostrarInformacionCuadre();
    }

    public static String getDescuentoMaximoVentas() {
        return general != null ? String.valueOf(general.getDescuentoMaximoVentas()) : "0";
    }

    public static boolean isModificarNombre() {
        return general != null && general.isModificarNombre();
    }

    public static boolean isModificarPrecio() {
        return general != null && general.isModificarPrecio();
    }

    public static boolean isCombinarProductos() {
        return general != null && general.isCombinarProductos();
    }

    public static boolean isBorrarProductos() {
        return general != null && general.isBorrarProductos();
    }

    public static boolean isHora() {
        return impresiones != null && impresiones.isImprimirHora();
    }

    public static String getAnexoFacturacion() {
        return impresiones != null ? s(impresiones.getAnexoFactura()) : "";
    }

    public static String getAnexoOrdenServicio() {
        return impresiones != null ? s(impresiones.getAnexoOrdenServicio()) : "";
    }

    // ── Turno ───────────────────────────────────────────────────────────────
    public static boolean isTurnoActivo() {
        return general != null && general.isTurno();
    }

    public static String getNumeroTurno() {
        if (general == null) {
            return "1";
        }
        int n = general.getTurnoConsecutivo();
        return n > 0 ? String.valueOf(n) : "1";
    }

    public static boolean isConsecutivoAdicional() {
        return general != null && general.isConsecutivoAdicional();
    }

    // ── Inventario / Ventas ─────────────────────────────────────────────────
    public static boolean isFacturarSinInventario() {
        return general != null && general.isFacturarSinInventario();
    }

    public static String getLimite() {
        return general != null ? String.valueOf(general.getLimiteProductosPorFactura()) : "0";
    }

    // ── Cantidades / Foco ───────────────────────────────────────────────────
    public static String getCantidadEstablecidaAlCargar() {
        if (general == null) {
            return "1";
        }
        int c = general.getCantidadEstablecida();
        return c > 0 ? String.valueOf(c) : "1";
    }

    public static String getFocoDespuesDeCargarProducto() {
        if (general == null || general.getFoco() == null) {
            return "Cantidad";
        }
        return general.getFoco();
    }

    public static boolean isCargarDiasPlazoAutomaticamente() {
        return general != null && general.isDiasAutomaticos();
    }

    public static boolean isModuloPermisos() {
        return general != null && general.isModuloPermisos();
    }

    // ── Cartera / Mora ──────────────────────────────────────────────────────
    public static String getDiasCobrarMora() {
        return general != null ? String.valueOf(general.getDiasCobroMora()) : "0";
    }

    public static String getPorcentajeMora() {
        return general != null ? s(general.getPorcentajeMora()) : "";
    }

    // ── Orden médica ────────────────────────────────────────────────────────
    public static boolean isGeneraOrdenMedica() {
        return general != null && general.isGeneraOrdenMedica();
    }

    public static boolean isImprimirOrdenMedica() {
        return general != null && general.isImprimirOrdenMedica();
    }

    public static boolean isImprimirFacturaOrdenMedica() {
        return general != null && general.isImprimirFacturaOrdenMedica();
    }

    // ── Agenda ──────────────────────────────────────────────────────────────
    public static String getHoraInicioAgenda() {
        return general != null ? s(general.getHoraInicioAgenda()) : "";
    }

    public static String getHoraFinAgenda() {
        return general != null ? s(general.getHoraFinAgenda()) : "";
    }

    public static String getIntervaloAgenda() {
        return general != null ? s(general.getIntervaloAgenda()) : "";
    }

    // ── Prestador servicio ──────────────────────────────────────────────────
    public static String getCodigoPrestadorServicio() {
        return general != null ? s(general.getCodPrestadorServicio()) : "";
    }

    public static String getTipoPrestadorServicio() {
        return general != null ? s(general.getTipoIdPrestadorServicio()) : "";
    }

    // ── Copias / Previsualización ────────────────────────────────────────────
    public static boolean isCopiasFactura() {
        return impresiones != null && impresiones.isPreguntarCopiasFactura();
    }

    public static boolean isCopiasOServicio() {
        return impresiones != null && impresiones.isPreguntarCopiasOServicio();
    }

    public static boolean isCopiasCotizacion() {
        return impresiones != null && impresiones.isPreguntarCopiasCotizacion();
    }

    public static boolean isCopiasPlanSepare() {
        return impresiones != null && impresiones.isPreguntarCopiasPlanSepare();
    }

    public static boolean isCopiasPedido() {
        return impresiones != null && impresiones.isPreguntarCopiasPedido();
    }

    public static boolean isPrevisualizarOServicio() {
        return impresiones != null && impresiones.isPrevisualizarOServicio();
    }

    public static boolean isPrevisualizarCotizacion() {
        return impresiones != null && impresiones.isPrevisualizarCotizacion();
    }

    public static boolean isPrevisualizarPlanSepare() {
        return impresiones != null && impresiones.isPrevisualizarPlanSepare();
    }

    public static boolean isPrevisualizarPedido() {
        return impresiones != null && impresiones.isPrevisualizarPedido();
    }

    // ── Numeración documentos ───────────────────────────────────────────────
    public static String getNumFactura() {
        return impresiones != null ? String.valueOf(impresiones.getNumeroCopiasFactura()) : "0";
    }

    public static String getNumOServicio() {
        return impresiones != null ? String.valueOf(impresiones.getNumeroCopiasOrdenServicio()) : "0";
    }

    public static String getNumCotizacion() {
        return impresiones != null ? String.valueOf(impresiones.getNumeroCopiasCotizacion()) : "0";
    }

    public static String getNumPlanSepare() {
        return impresiones != null ? String.valueOf(impresiones.getNumeroCopiasPlanSepare()) : "0";
    }

    public static String getNumPedido() {
        return impresiones != null ? String.valueOf(impresiones.getNumeroCopiasPedido()) : "0";
    }

    // ── Impresoras ──────────────────────────────────────────────────────────
    public static String getImpresoraPos() {
        return impresiones != null ? s(impresiones.getImpresoraPos()) : "";
    }

    public static String getImpresoraMediaCarta() {
        return impresiones != null ? s(impresiones.getImpresoraMediaCarta()) : "";
    }

    public static String getImpresoraCarta() {
        return impresiones != null ? s(impresiones.getImpresoraCarta()) : "";
    }

    public static String getImpresoraComanda() {
        return impresiones != null ? s(impresiones.getImpresoraComanda()) : "";
    }

    public static String getImpresoraPrefactura() {
        return impresiones != null ? s(impresiones.getImpresoraPrefactura()) : "";
    }

    public static String getImprimirCada() {
        return impresiones != null ? s(impresiones.getFormatoImpresion()) : "";
    }

    public static boolean isMostrarImpoconsumo() {
        return impresiones != null && impresiones.isImprimirImpoconsumo();
    }

    public static boolean isMostrarRetenciones() {
        return impresiones != null && impresiones.isImprimirRetenciones();
    }

    // ── Pantalla / UI ────────────────────────────────────────────────────────
    public static String getFilas() {
        return general != null ? String.valueOf(general.getFilas()) : "0";
    }

    public static String getColumnas() {
        return general != null ? String.valueOf(general.getColumnas()) : "0";
    }

    public static String getPorcPropina() {
        return general != null ? String.valueOf(general.getPorcPropina()) : "0";
    }

    public static boolean isMostrarDevuelta() {
        return general != null && general.isMostrarDevuelta();
    }

    public static boolean isImpresionPorGrupo() {
        return impresiones != null && impresiones.isImpresionPorGrupo();
    }

    // ── Hospitalización ─────────────────────────────────────────────────────
    public static String getInicioHosp() {
        return general != null ? s(general.getInicioHosp()) : "";
    }

    public static String getFinHosp() {
        return general != null ? s(general.getFinHosp()) : "";
    }

    public static String getIntervalosHosp() {
        return general != null ? s(general.getIntervalosHosp()) : "";
    }

    // ── Comanda / Prefactura ────────────────────────────────────────────────
    public static String getCopiasComanda() {
        return impresiones != null ? String.valueOf(impresiones.getNumeroCopiasComanda()) : "0";
    }

    public static String getCopiasPrefactura() {
        return impresiones != null ? String.valueOf(impresiones.getNumeroCopiasPrefactura()) : "0";
    }

    public static boolean isPrevisualizarComanda() {
        return impresiones != null && impresiones.isPrevisualizarComanda();
    }

    public static boolean isPrevisualizarPrefactura() {
        return impresiones != null && impresiones.isPrevisualizarPrefactura();
    }

    public static boolean isPreguntarCopiasComanda() {
        return impresiones != null && impresiones.isPreguntarCopiasComanda();
    }

    public static boolean isPreguntarCopiasPrefactura() {
        return impresiones != null && impresiones.isPreguntarCopiasPrefactura();
    }

    // ── Mesas / Restaurante ─────────────────────────────────────────────────
    public static boolean isFacturarMesas() {
        return general != null && general.isFacturarMesas();
    }

    public static boolean isSoloMesas() {
        return general != null && general.isSoloMesas();
    }

    public static boolean isBorrarMesas() {
        return general != null && general.isBorrarMesas();
    }

    // ── Credenciales / Notificaciones ───────────────────────────────────────
    public static String getPassword() {
        return general != null ? s(general.getPassword()) : "";
    }

    // ── Responsabilidades fiscales ──────────────────────────────────────────
    public static String getResponsabilidadesFiscales() {
        return responsabilidadesFiscales;
    }

    public static String getResponsabilidadesRaw() {
        return general != null ? s(general.getResponsabilidadesFiscales()) : "";
    }
}
