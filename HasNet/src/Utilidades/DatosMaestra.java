package Utilidades;

import Modelo.Maestra.ModeloMaestraGeneral;
import Modelo.Maestra.ModeloMaestraImpresiones;
import java.math.BigDecimal;

/**
 * Única fuente de los datos de la maestra (bdMaestra y bdMaestraImpresiones)
 * para el resto del sistema. Se carga con setearDatosMaestra al iniciar sesión
 * y cada vez que se guarda la maestra.
 */
public class DatosMaestra {

    private static ModeloMaestraGeneral general;
    private static ModeloMaestraImpresiones impresiones;
    private static String responsabilidadesFiscales;

    public static void setearDatosMaestra(ModeloMaestraGeneral g, ModeloMaestraImpresiones i) {
        general = g;
        impresiones = i;
        responsabilidadesFiscales = procesarResponsabilidades(g != null ? g.getResponsabilidadesFiscales() : null);
    }

    private static String s(String value) {
        return value != null ? value : "";
    }

    // ── Empresa ─────────────────────────────────────────────────────────────
    public static String getNombreEmpresa() {
        return general != null ? s(general.getNombreEmpresa()) : "";
    }

    public static String getNitEmpresa() {
        return general != null ? s(general.getNitEmpresa()) : "";
    }

    public static String getRegimenEmpresa() {
        return general != null ? s(general.getRegimenEmpresa()) : "";
    }

    public static String getDireccionEmpresa() {
        return general != null ? s(general.getDireccionEmpresa()) : "";
    }

    public static String getTelefonoEmpresa() {
        return general != null ? s(general.getTelefonoEmpresa()) : "";
    }

    public static String getConfiguracionRegimen() {
        return general != null ? s(general.getConfiguracionRegimen()) : "";
    }

    // ── Responsabilidades fiscales ──────────────────────────────────────────
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

    /**
     * Códigos de responsabilidad fiscal separados por ";" (ZZ si no aplica).
     */
    public static String getResponsabilidadesFiscales() {
        return responsabilidadesFiscales;
    }

    /**
     * Texto tal como está guardado en bdMaestra.
     */
    public static String getResponsabilidadesRaw() {
        return general != null ? s(general.getResponsabilidadesFiscales()) : "";
    }

    // ── Seguridad ───────────────────────────────────────────────────────────
    /**
     * "Contraseña para control y permisos".
     */
    public static String getContrasenaPermisos() {
        return general != null ? s(general.getContrasena()) : "";
    }

    public static String getPassword() {
        return general != null ? s(general.getPassword()) : "";
    }

    /**
     * "Módulo de solicitudes de permisos".
     */
    public static boolean isModuloPermisos() {
        return general != null && general.isModuloPermisos();
    }

    // ── Facturación ─────────────────────────────────────────────────────────
    public static String getTituloFactura() {
        return general != null ? s(general.getTituloFactura()) : "";
    }

    public static boolean isUsarLector() {
        return general != null && general.isUsarLector();
    }

    public static boolean isVentasContadoPredeterminado() {
        return general != null && general.isVentasPredeterminado();
    }

    /**
     * Valor crudo de "alertaUtilidad" ("SI"/"NO").
     */
    public static String getAlertaUtilidad() {
        return general != null ? s(general.getAlertaUtilidad()) : "";
    }

    /**
     * "Facturar Utilidad Sobrepasada": permite facturar aunque se sobrepase
     * la utilidad (todo valor distinto de "NO").
     */
    public static boolean isFacturarUtilidadSobrepasada() {
        return !"NO".equals(getAlertaUtilidad());
    }

    /**
     * "Alertar Utilidad Sobrepasada".
     */
    public static boolean isAlertarUtilidadSobrepasada() {
        return general != null && general.isMensajeUtilidad();
    }

    public static boolean isFacturarSinInventario() {
        return general != null && general.isFacturarSinInventario();
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

    /**
     * "Borrar productos en mesas/congeladas".
     */
    public static boolean isBorrarProductos() {
        return general != null && general.isBorrarProductos();
    }

    public static boolean isConsecutivoAdicional() {
        return general != null && general.isConsecutivoAdicional();
    }

    public static boolean isMostrarDevuelta() {
        return general != null && general.isMostrarDevuelta();
    }

    public static boolean isMostrarInformacionCuadre() {
        return general != null && general.isMostrarInformacionCuadre();
    }

    public static String getLimiteProductosPorFactura() {
        return general != null ? String.valueOf(general.getLimiteProductosPorFactura()) : "0";
    }

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

    // ── Descuentos ──────────────────────────────────────────────────────────
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

    public static boolean isDescuentoValor() {
        return DESCUENTO_VALOR.equals(getTipoDescuento());
    }

    /**
     * Valor guardado de "Descuento máximo para aplicar" ("0" si no hay).
     */
    public static String getDescuentoMaximoVentas() {
        return general != null ? String.valueOf(general.getDescuentoMaximoVentas()) : "0";
    }

    /**
     * Descuento máximo que se puede aplicar en ventas: 100 si no hay un
     * máximo configurado.
     */
    public static BigDecimal getDescuentoMaximoPermitido() {
        int maximo = general != null ? general.getDescuentoMaximoVentas() : 0;
        return maximo > 0 ? BigDecimal.valueOf(maximo) : BigDecimal.valueOf(100);
    }

    // ── Impuestos incluidos en precios y costos ─────────────────────────────
    /**
     * Opción general "Precios con impuestos": los precios de venta y los
     * costos ya traen incluidos el IVA y el impoconsumo.
     */
    public static boolean isPreciosConImpuestos() {
        return general != null && general.isPreciosConImpuesto();
    }

    public static boolean isPrecioVentaConIva() {
        return isPreciosConImpuestos();
    }

    public static boolean isCostoConIva() {
        return isPreciosConImpuestos();
    }

    public static boolean isPrecioVentaConImpoconsumo() {
        return isPreciosConImpuestos();
    }

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

    // ── Crédito / Cartera ───────────────────────────────────────────────────
    /**
     * "Validar el cupo del credito".
     */
    public static boolean isValidarCupoCredito() {
        return general != null && general.isValidarCupoCredito();
    }

    public static boolean isCargarDiasPlazoAutomaticamente() {
        return general != null && general.isDiasAutomaticos();
    }

    public static String getDiasCobrarMora() {
        return general != null ? String.valueOf(general.getDiasCobroMora()) : "0";
    }

    /**
     * Porcentaje de mora en créditos ("0" si no está configurado).
     */
    public static String getPorcentajeMora() {
        String porcentaje = general != null ? s(general.getPorcentajeMora()) : "";
        return porcentaje.trim().isEmpty() ? "0" : porcentaje;
    }

    public static String getDiasAlertaCuadre() {
        return general != null ? String.valueOf(general.getDiasAlertaCuadre()) : "0";
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

    // ── Restaurante / Mesas ─────────────────────────────────────────────────
    public static boolean isFacturarMesas() {
        return general != null && general.isFacturarMesas();
    }

    public static boolean isSoloMesas() {
        return general != null && general.isSoloMesas();
    }

    public static boolean isBorrarMesas() {
        return general != null && general.isBorrarMesas();
    }

    public static String getFilas() {
        return general != null ? String.valueOf(general.getFilas()) : "0";
    }

    public static String getColumnas() {
        return general != null ? String.valueOf(general.getColumnas()) : "0";
    }

    public static String getPorcentajePropina() {
        return general != null ? String.valueOf(general.getPorcPropina()) : "0";
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

    public static String getCodigoPrestadorServicio() {
        return general != null ? s(general.getCodPrestadorServicio()) : "";
    }

    public static String getTipoPrestadorServicio() {
        return general != null ? s(general.getTipoIdPrestadorServicio()) : "";
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

    // ── Hospitalización ─────────────────────────────────────────────────────
    public static String getHoraInicioHospitalizacion() {
        return general != null ? s(general.getInicioHosp()) : "";
    }

    public static String getHoraFinHospitalizacion() {
        return general != null ? s(general.getFinHosp()) : "";
    }

    public static String getIntervaloHospitalizacion() {
        return general != null ? s(general.getIntervalosHosp()) : "";
    }

    // ══ Impresiones (bdMaestraImpresiones) ══════════════════════════════════
    /**
     * Tipo de impresión predeterminado: "pos", "factura" (media carta) o
     * "facturaCompleta" (carta).
     */
    public static String getTipoImpresionPredeterminado() {
        return impresiones != null ? s(impresiones.getTipoPredeterminado()) : "";
    }

    public static boolean isImpresionPos() {
        return "pos".equals(getTipoImpresionPredeterminado());
    }

    /**
     * Sufijo de los reportes según el tipo de impresión: "Pos" para POS,
     * vacío para los demás (por ejemplo "abonoNuevo" + sufijo).
     */
    public static String getSufijoReporteImpresion() {
        return isImpresionPos() ? "Pos" : "";
    }

    public static String getFormatoImpresion() {
        return impresiones != null ? s(impresiones.getFormatoImpresion()) : "";
    }

    public static boolean isImpresionPorGrupo() {
        return impresiones != null && impresiones.isImpresionPorGrupo();
    }

    // ── Textos impresos ─────────────────────────────────────────────────────
    public static String getPiePagina() {
        return impresiones != null ? s(impresiones.getPiePagina()) : "";
    }

    public static String getInformacionLegal() {
        return impresiones != null ? s(impresiones.getInformacionLegal()) : "";
    }

    public static String getAnexoFactura() {
        return impresiones != null ? s(impresiones.getAnexoFactura()) : "";
    }

    public static String getAnexoOrdenServicio() {
        return impresiones != null ? s(impresiones.getAnexoOrdenServicio()) : "";
    }

    public static final String TIPO_IMPRESION_SIN_CODIGO = "Sin-Codigo";
    public static final String TIPO_IMPRESION_CON_CODIGO = "Con-Codigo";
    public static final String TIPO_IMPRESION_SERIAL = "Imei";

    public static String getTipoImpresion() {
        String guardado = impresiones != null ? impresiones.getTipoImpresion() : null;
        clases.Instancias instancias = clases.Instancias.getInstancias();
        boolean productosDetallados = instancias.getConfiguraciones() != null
                && instancias.getConfiguraciones().isProductosDetallados();
        return ajustarTipoImpresion(guardado, productosDetallados);
    }

    public static String ajustarTipoImpresion(String tipoImpresion, boolean productosDetallados) {
        if (productosDetallados) {
            return TIPO_IMPRESION_SERIAL;
        }
        return TIPO_IMPRESION_CON_CODIGO.equals(tipoImpresion) ? TIPO_IMPRESION_CON_CODIGO : TIPO_IMPRESION_SIN_CODIGO;
    }

    // ── Opciones de impresión ───────────────────────────────────────────────
    /**
     * Valor crudo de "Imprimir ubicación en factura" ("SI"/"NO").
     */
    public static String getMostrarUbicacion() {
        return impresiones != null ? s(impresiones.getMostrarUbicacion()) : "SI";
    }

    public static boolean isImprimirUbicacion() {
        return !"NO".equals(getMostrarUbicacion());
    }

    public static boolean isImprimirHora() {
        return impresiones != null && impresiones.isImprimirHora();
    }

    public static boolean isImprimirRetenciones() {
        return impresiones != null && impresiones.isImprimirRetenciones();
    }

    public static boolean isImprimirImpoconsumo() {
        return impresiones != null && impresiones.isImprimirImpoconsumo();
    }

    public static boolean isImprimirRecogida() {
        return impresiones != null && impresiones.isImprimirRecodiga();
    }

    public static boolean isImprimirCuadreFiscal() {
        return impresiones != null && impresiones.isImprimirCuadreFiscal();
    }

    // ── Previsualizar antes de imprimir ─────────────────────────────────────
    public static boolean isPrevisualizarFactura() {
        return impresiones != null && impresiones.isPrevisualizarFactura();
    }

    public static boolean isPrevisualizarOrdenServicio() {
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

    public static boolean isPrevisualizarComanda() {
        return impresiones != null && impresiones.isPrevisualizarComanda();
    }

    public static boolean isPrevisualizarPrefactura() {
        return impresiones != null && impresiones.isPrevisualizarPrefactura();
    }

    // ── Preguntar número de copias ──────────────────────────────────────────
    public static boolean isPreguntarCopiasFactura() {
        return impresiones != null && impresiones.isPreguntarCopiasFactura();
    }

    public static boolean isPreguntarCopiasOrdenServicio() {
        return impresiones != null && impresiones.isPreguntarCopiasOServicio();
    }

    public static boolean isPreguntarCopiasCotizacion() {
        return impresiones != null && impresiones.isPreguntarCopiasCotizacion();
    }

    public static boolean isPreguntarCopiasPlanSepare() {
        return impresiones != null && impresiones.isPreguntarCopiasPlanSepare();
    }

    public static boolean isPreguntarCopiasPedido() {
        return impresiones != null && impresiones.isPreguntarCopiasPedido();
    }

    public static boolean isPreguntarCopiasComanda() {
        return impresiones != null && impresiones.isPreguntarCopiasComanda();
    }

    public static boolean isPreguntarCopiasPrefactura() {
        return impresiones != null && impresiones.isPreguntarCopiasPrefactura();
    }

    // ── Número de copias predeterminado ─────────────────────────────────────
    public static String getNumeroCopiasFactura() {
        return impresiones != null ? String.valueOf(impresiones.getNumeroCopiasFactura()) : "0";
    }

    public static String getNumeroCopiasOrdenServicio() {
        return impresiones != null ? String.valueOf(impresiones.getNumeroCopiasOrdenServicio()) : "0";
    }

    public static String getNumeroCopiasCotizacion() {
        return impresiones != null ? String.valueOf(impresiones.getNumeroCopiasCotizacion()) : "0";
    }

    public static String getNumeroCopiasPlanSepare() {
        return impresiones != null ? String.valueOf(impresiones.getNumeroCopiasPlanSepare()) : "0";
    }

    public static String getNumeroCopiasPedido() {
        return impresiones != null ? String.valueOf(impresiones.getNumeroCopiasPedido()) : "0";
    }

    public static String getNumeroCopiasComanda() {
        return impresiones != null ? String.valueOf(impresiones.getNumeroCopiasComanda()) : "0";
    }

    public static String getNumeroCopiasPrefactura() {
        return impresiones != null ? String.valueOf(impresiones.getNumeroCopiasPrefactura()) : "0";
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
}
