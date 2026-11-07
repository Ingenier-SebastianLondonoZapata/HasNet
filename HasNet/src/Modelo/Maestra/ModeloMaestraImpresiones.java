package Modelo.Maestra;

public class ModeloMaestraImpresiones {

    private boolean previsualizarFactura, previsualizarOServicio, previsualizarCotizacion, previsualizarPlanSepare,
            previsualizarPedido, previsualizarComanda, previsualizarPrefactura, preguntarCopiasFactura, preguntarCopiasOServicio,
            preguntarCopiasCotizacion, preguntarCopiasPlanSepare, preguntarCopiasPedido, preguntarCopiasComanda, preguntarCopiasPrefactura,
            imprimirHora, imprimirRetenciones, imprimirImpoconsumo, imprimirRecodiga, imprimirCuadreFiscal, impresionPorGrupo;

    private int numeroCopiasFactura = 0, numeroCopiasOrdenServicio = 0, numeroCopiasCotizacion = 0,
            numeroCopiasPlanSepare = 0, numeroCopiasPedido = 0, numeroCopiasComanda = 0, numeroCopiasPrefactura = 0;

    private String impresoraPos, impresoraMediaCarta, impresoraCarta, impresoraComanda, impresoraPrefactura,
            tipoPredeterminado, mostrarUbicacion, formatoImpresion, piePagina, informacionLegal, anexoFactura, anexoOrdenServicio,
            tipoImpresion;

    private static int parsear(String valor) {
        try {
            return Math.max(0, Integer.parseInt(valor == null ? "" : valor.trim()));
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public boolean isPrevisualizarFactura() {
        return previsualizarFactura;
    }

    public void setPrevisualizarFactura(boolean previsualizarFactura) {
        this.previsualizarFactura = previsualizarFactura;
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

    public boolean isPreguntarCopiasFactura() {
        return preguntarCopiasFactura;
    }

    public void setPreguntarCopiasFactura(boolean preguntarCopiasFactura) {
        this.preguntarCopiasFactura = preguntarCopiasFactura;
    }

    public boolean isPreguntarCopiasOServicio() {
        return preguntarCopiasOServicio;
    }

    public void setPreguntarCopiasOServicio(boolean preguntarCopiasOServicio) {
        this.preguntarCopiasOServicio = preguntarCopiasOServicio;
    }

    public boolean isPreguntarCopiasCotizacion() {
        return preguntarCopiasCotizacion;
    }

    public void setPreguntarCopiasCotizacion(boolean preguntarCopiasCotizacion) {
        this.preguntarCopiasCotizacion = preguntarCopiasCotizacion;
    }

    public boolean isPreguntarCopiasPlanSepare() {
        return preguntarCopiasPlanSepare;
    }

    public void setPreguntarCopiasPlanSepare(boolean preguntarCopiasPlanSepare) {
        this.preguntarCopiasPlanSepare = preguntarCopiasPlanSepare;
    }

    public boolean isPreguntarCopiasPedido() {
        return preguntarCopiasPedido;
    }

    public void setPreguntarCopiasPedido(boolean preguntarCopiasPedido) {
        this.preguntarCopiasPedido = preguntarCopiasPedido;
    }

    public boolean isPreguntarCopiasComanda() {
        return preguntarCopiasComanda;
    }

    public void setPreguntarCopiasComanda(boolean preguntarCopiasComanda) {
        this.preguntarCopiasComanda = preguntarCopiasComanda;
    }

    public boolean isPreguntarCopiasPrefactura() {
        return preguntarCopiasPrefactura;
    }

    public void setPreguntarCopiasPrefactura(boolean preguntarCopiasPrefactura) {
        this.preguntarCopiasPrefactura = preguntarCopiasPrefactura;
    }

    public int getNumeroCopiasFactura() {
        return numeroCopiasFactura;
    }

    public void setNumeroCopiasFactura(int n) {
        this.numeroCopiasFactura = Math.max(0, n);
    }

    public void setNumeroCopiasFactura(String s) {
        setNumeroCopiasFactura(parsear(s));
    }

    public int getNumeroCopiasOrdenServicio() {
        return numeroCopiasOrdenServicio;
    }

    public void setNumeroCopiasOrdenServicio(int n) {
        this.numeroCopiasOrdenServicio = Math.max(0, n);
    }

    public void setNumeroCopiasOrdenServicio(String s) {
        setNumeroCopiasOrdenServicio(parsear(s));
    }

    public int getNumeroCopiasCotizacion() {
        return numeroCopiasCotizacion;
    }

    public void setNumeroCopiasCotizacion(int n) {
        this.numeroCopiasCotizacion = Math.max(0, n);
    }

    public void setNumeroCopiasCotizacion(String s) {
        setNumeroCopiasCotizacion(parsear(s));
    }

    public int getNumeroCopiasPlanSepare() {
        return numeroCopiasPlanSepare;
    }

    public void setNumeroCopiasPlanSepare(int n) {
        this.numeroCopiasPlanSepare = Math.max(0, n);
    }

    public void setNumeroCopiasPlanSepare(String s) {
        setNumeroCopiasPlanSepare(parsear(s));
    }

    public int getNumeroCopiasPedido() {
        return numeroCopiasPedido;
    }

    public void setNumeroCopiasPedido(int n) {
        this.numeroCopiasPedido = Math.max(0, n);
    }

    public void setNumeroCopiasPedido(String s) {
        setNumeroCopiasPedido(parsear(s));
    }

    public int getNumeroCopiasComanda() {
        return numeroCopiasComanda;
    }

    public void setNumeroCopiasComanda(int n) {
        this.numeroCopiasComanda = Math.max(0, n);
    }

    public void setNumeroCopiasComanda(String s) {
        setNumeroCopiasComanda(parsear(s));
    }

    public int getNumeroCopiasPrefactura() {
        return numeroCopiasPrefactura;
    }

    public void setNumeroCopiasPrefactura(int n) {
        this.numeroCopiasPrefactura = Math.max(0, n);
    }

    public void setNumeroCopiasPrefactura(String s) {
        setNumeroCopiasPrefactura(parsear(s));
    }

    public String getImpresoraPos() {
        return impresoraPos;
    }

    public void setImpresoraPos(String impresoraPos) {
        this.impresoraPos = impresoraPos;
    }

    public String getImpresoraMediaCarta() {
        return impresoraMediaCarta;
    }

    public void setImpresoraMediaCarta(String impresoraMediaCarta) {
        this.impresoraMediaCarta = impresoraMediaCarta;
    }

    public String getImpresoraCarta() {
        return impresoraCarta;
    }

    public void setImpresoraCarta(String impresoraCarta) {
        this.impresoraCarta = impresoraCarta;
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

    public String getTipoPredeterminado() {
        return tipoPredeterminado;
    }

    public void setTipoPredeterminado(String tipoPredeterminado) {
        this.tipoPredeterminado = tipoPredeterminado;
    }

    public String getMostrarUbicacion() {
        return mostrarUbicacion;
    }

    public void setMostrarUbicacion(String mostrarUbicacion) {
        this.mostrarUbicacion = mostrarUbicacion;
    }

    public String getFormatoImpresion() {
        return formatoImpresion;
    }

    public void setFormatoImpresion(String formatoImpresion) {
        this.formatoImpresion = formatoImpresion;
    }

    public boolean isImprimirHora() {
        return imprimirHora;
    }

    public void setImprimirHora(boolean imprimirHora) {
        this.imprimirHora = imprimirHora;
    }

    public boolean isImprimirRetenciones() {
        return imprimirRetenciones;
    }

    public void setImprimirRetenciones(boolean imprimirRetenciones) {
        this.imprimirRetenciones = imprimirRetenciones;
    }

    public boolean isImprimirImpoconsumo() {
        return imprimirImpoconsumo;
    }

    public void setImprimirImpoconsumo(boolean imprimirImpoconsumo) {
        this.imprimirImpoconsumo = imprimirImpoconsumo;
    }

    public boolean isImprimirRecodiga() {
        return imprimirRecodiga;
    }

    public void setImprimirRecodiga(boolean imprimirRecodiga) {
        this.imprimirRecodiga = imprimirRecodiga;
    }

    public boolean isImprimirCuadreFiscal() {
        return imprimirCuadreFiscal;
    }

    public void setImprimirCuadreFiscal(boolean imprimirCuadreFiscal) {
        this.imprimirCuadreFiscal = imprimirCuadreFiscal;
    }

    public boolean isImpresionPorGrupo() {
        return impresionPorGrupo;
    }

    public void setImpresionPorGrupo(boolean impresionPorGrupo) {
        this.impresionPorGrupo = impresionPorGrupo;
    }

    public String getPiePagina() {
        return piePagina;
    }

    public void setPiePagina(String piePagina) {
        this.piePagina = piePagina;
    }

    public String getInformacionLegal() {
        return informacionLegal;
    }

    public void setInformacionLegal(String informacionLegal) {
        this.informacionLegal = informacionLegal;
    }

    public String getAnexoFactura() {
        return anexoFactura;
    }

    public void setAnexoFactura(String anexoFactura) {
        this.anexoFactura = anexoFactura;
    }

    public String getAnexoOrdenServicio() {
        return anexoOrdenServicio;
    }

    public void setAnexoOrdenServicio(String anexoOrdenServicio) {
        this.anexoOrdenServicio = anexoOrdenServicio;
    }

    public String getTipoImpresion() {
        return tipoImpresion;
    }

    public void setTipoImpresion(String tipoImpresion) {
        this.tipoImpresion = tipoImpresion;
    }
}
