package Modelo.Tesoreria;

import java.math.BigDecimal;

public class ModeloDocumentoCuadre {

    public enum Tipo {
        FACTURA, PLAN_SEPARE, ABONO, NOTA_CREDITO, EGRESO
    }

    private final Tipo tipo;
    private String id = "";
    private String referencia = "";
    private BigDecimal efectivo = BigDecimal.ZERO;
    private BigDecimal tarjeta = BigDecimal.ZERO;
    private BigDecimal tarjetaCredito = BigDecimal.ZERO;
    private BigDecimal cheque = BigDecimal.ZERO;
    private BigDecimal notaCredito = BigDecimal.ZERO;
    private BigDecimal total = BigDecimal.ZERO;
    private BigDecimal cuotaInicial = BigDecimal.ZERO;
    private BigDecimal propina = BigDecimal.ZERO;
    private String terminal = "";
    private String comprobante = "";
    private String lugar = "";
    private boolean credito;
    private boolean sisteCredito;
    private boolean reembolsada;

    public ModeloDocumentoCuadre(Tipo tipo) {
        this.tipo = tipo;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public BigDecimal getEfectivo() {
        return efectivo;
    }

    public void setEfectivo(BigDecimal efectivo) {
        this.efectivo = efectivo;
    }

    public BigDecimal getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(BigDecimal tarjeta) {
        this.tarjeta = tarjeta;
    }

    public BigDecimal getTarjetaCredito() {
        return tarjetaCredito;
    }

    public void setTarjetaCredito(BigDecimal tarjetaCredito) {
        this.tarjetaCredito = tarjetaCredito;
    }

    public BigDecimal getCheque() {
        return cheque;
    }

    public void setCheque(BigDecimal cheque) {
        this.cheque = cheque;
    }

    public BigDecimal getNotaCredito() {
        return notaCredito;
    }

    public void setNotaCredito(BigDecimal notaCredito) {
        this.notaCredito = notaCredito;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getCuotaInicial() {
        return cuotaInicial;
    }

    public void setCuotaInicial(BigDecimal cuotaInicial) {
        this.cuotaInicial = cuotaInicial;
    }

    public BigDecimal getPropina() {
        return propina;
    }

    public void setPropina(BigDecimal propina) {
        this.propina = propina;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public String getComprobante() {
        return comprobante;
    }

    public void setComprobante(String comprobante) {
        this.comprobante = comprobante;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public boolean isCredito() {
        return credito;
    }

    public void setCredito(boolean credito) {
        this.credito = credito;
    }

    public boolean isSisteCredito() {
        return sisteCredito;
    }

    public void setSisteCredito(boolean sisteCredito) {
        this.sisteCredito = sisteCredito;
    }

    public boolean isReembolsada() {
        return reembolsada;
    }

    public void setReembolsada(boolean reembolsada) {
        this.reembolsada = reembolsada;
    }
}
