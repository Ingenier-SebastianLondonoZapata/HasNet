package Modelo.Tesoreria;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ModeloResumenCuadre {

    private final List<ModeloDocumentoCuadre> documentos = new ArrayList<>();

    private BigDecimal ventasCredito = BigDecimal.ZERO;
    private BigDecimal ventasContado = BigDecimal.ZERO;
    private BigDecimal ventasSistecredito = BigDecimal.ZERO;
    private BigDecimal totalVentas = BigDecimal.ZERO;
    private BigDecimal abonosCartera = BigDecimal.ZERO;
    private BigDecimal abonosPlanSepare = BigDecimal.ZERO;
    private BigDecimal totalAbonos = BigDecimal.ZERO;
    private BigDecimal facturasPlanSepare = BigDecimal.ZERO;
    private BigDecimal cuotasIniciales = BigDecimal.ZERO;
    private BigDecimal ventasDomicilio = BigDecimal.ZERO;
    private BigDecimal productoDomicilio = BigDecimal.ZERO;

    private BigDecimal efectivo = BigDecimal.ZERO;
    private BigDecimal cheque = BigDecimal.ZERO;
    private BigDecimal tarjeta = BigDecimal.ZERO;
    private BigDecimal notasCreditoRecibidas = BigDecimal.ZERO;
    private BigDecimal propinas = BigDecimal.ZERO;
    private BigDecimal base = BigDecimal.ZERO;
    private BigDecimal recogidaPendiente = BigDecimal.ZERO;

    private BigDecimal notasCreditoRealizadas = BigDecimal.ZERO;
    private BigDecimal notasCreditoReembolsadas = BigDecimal.ZERO;
    private BigDecimal gastosRegistrados = BigDecimal.ZERO;

    private int contContado;
    private int contCredito;
    private int contAbonos;
    private int contEfectivo;
    private int contCheque;
    private int contTarjeta;
    private int contNcRealizadas;
    private int contNcRecibidas;
    private int contNcReembolsadas;
    private int contCuotasIniciales;
    private int contGastos;

    private String primeraFactura = "";
    private String ultimaFactura = "";
    private String primerAbono = "";
    private String ultimoAbono = "";

    public List<ModeloDocumentoCuadre> getDocumentos() {
        return documentos;
    }

    public BigDecimal getVentasCredito() {
        return ventasCredito;
    }

    public void setVentasCredito(BigDecimal ventasCredito) {
        this.ventasCredito = ventasCredito;
    }

    public BigDecimal getVentasContado() {
        return ventasContado;
    }

    public void setVentasContado(BigDecimal ventasContado) {
        this.ventasContado = ventasContado;
    }

    public BigDecimal getVentasSistecredito() {
        return ventasSistecredito;
    }

    public void setVentasSistecredito(BigDecimal ventasSistecredito) {
        this.ventasSistecredito = ventasSistecredito;
    }

    public BigDecimal getTotalVentas() {
        return totalVentas;
    }

    public void setTotalVentas(BigDecimal totalVentas) {
        this.totalVentas = totalVentas;
    }

    public BigDecimal getAbonosCartera() {
        return abonosCartera;
    }

    public void setAbonosCartera(BigDecimal abonosCartera) {
        this.abonosCartera = abonosCartera;
    }

    public BigDecimal getAbonosPlanSepare() {
        return abonosPlanSepare;
    }

    public void setAbonosPlanSepare(BigDecimal abonosPlanSepare) {
        this.abonosPlanSepare = abonosPlanSepare;
    }

    public BigDecimal getTotalAbonos() {
        return totalAbonos;
    }

    public void setTotalAbonos(BigDecimal totalAbonos) {
        this.totalAbonos = totalAbonos;
    }

    public BigDecimal getFacturasPlanSepare() {
        return facturasPlanSepare;
    }

    public void setFacturasPlanSepare(BigDecimal facturasPlanSepare) {
        this.facturasPlanSepare = facturasPlanSepare;
    }

    public BigDecimal getCuotasIniciales() {
        return cuotasIniciales;
    }

    public void setCuotasIniciales(BigDecimal cuotasIniciales) {
        this.cuotasIniciales = cuotasIniciales;
    }

    public BigDecimal getVentasDomicilio() {
        return ventasDomicilio;
    }

    public void setVentasDomicilio(BigDecimal ventasDomicilio) {
        this.ventasDomicilio = ventasDomicilio;
    }

    public BigDecimal getProductoDomicilio() {
        return productoDomicilio;
    }

    public void setProductoDomicilio(BigDecimal productoDomicilio) {
        this.productoDomicilio = productoDomicilio;
    }

    public BigDecimal getEfectivo() {
        return efectivo;
    }

    public void setEfectivo(BigDecimal efectivo) {
        this.efectivo = efectivo;
    }

    public BigDecimal getCheque() {
        return cheque;
    }

    public void setCheque(BigDecimal cheque) {
        this.cheque = cheque;
    }

    public BigDecimal getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(BigDecimal tarjeta) {
        this.tarjeta = tarjeta;
    }

    public BigDecimal getNotasCreditoRecibidas() {
        return notasCreditoRecibidas;
    }

    public void setNotasCreditoRecibidas(BigDecimal notasCreditoRecibidas) {
        this.notasCreditoRecibidas = notasCreditoRecibidas;
    }

    public BigDecimal getPropinas() {
        return propinas;
    }

    public void setPropinas(BigDecimal propinas) {
        this.propinas = propinas;
    }

    public BigDecimal getBase() {
        return base;
    }

    public void setBase(BigDecimal base) {
        this.base = base;
    }

    public BigDecimal getRecogidaPendiente() {
        return recogidaPendiente;
    }

    public void setRecogidaPendiente(BigDecimal recogidaPendiente) {
        this.recogidaPendiente = recogidaPendiente;
    }

    public BigDecimal getNotasCreditoRealizadas() {
        return notasCreditoRealizadas;
    }

    public void setNotasCreditoRealizadas(BigDecimal notasCreditoRealizadas) {
        this.notasCreditoRealizadas = notasCreditoRealizadas;
    }

    public BigDecimal getNotasCreditoReembolsadas() {
        return notasCreditoReembolsadas;
    }

    public void setNotasCreditoReembolsadas(BigDecimal notasCreditoReembolsadas) {
        this.notasCreditoReembolsadas = notasCreditoReembolsadas;
    }

    public BigDecimal getNotasCreditoNoReembolsadas() {
        return notasCreditoRealizadas.subtract(notasCreditoReembolsadas);
    }

    public BigDecimal getGastosRegistrados() {
        return gastosRegistrados;
    }

    public void setGastosRegistrados(BigDecimal gastosRegistrados) {
        this.gastosRegistrados = gastosRegistrados;
    }

    public BigDecimal getEfectivoEsperado() {
        return efectivo.subtract(notasCreditoReembolsadas).subtract(gastosRegistrados);
    }

    public int getContContado() {
        return contContado;
    }

    public void setContContado(int contContado) {
        this.contContado = contContado;
    }

    public int getContCredito() {
        return contCredito;
    }

    public void setContCredito(int contCredito) {
        this.contCredito = contCredito;
    }

    public int getContAbonos() {
        return contAbonos;
    }

    public void setContAbonos(int contAbonos) {
        this.contAbonos = contAbonos;
    }

    public int getContEfectivo() {
        return contEfectivo;
    }

    public void setContEfectivo(int contEfectivo) {
        this.contEfectivo = contEfectivo;
    }

    public int getContCheque() {
        return contCheque;
    }

    public void setContCheque(int contCheque) {
        this.contCheque = contCheque;
    }

    public int getContTarjeta() {
        return contTarjeta;
    }

    public void setContTarjeta(int contTarjeta) {
        this.contTarjeta = contTarjeta;
    }

    public int getContNcRealizadas() {
        return contNcRealizadas;
    }

    public void setContNcRealizadas(int contNcRealizadas) {
        this.contNcRealizadas = contNcRealizadas;
    }

    public int getContNcRecibidas() {
        return contNcRecibidas;
    }

    public void setContNcRecibidas(int contNcRecibidas) {
        this.contNcRecibidas = contNcRecibidas;
    }

    public int getContNcReembolsadas() {
        return contNcReembolsadas;
    }

    public void setContNcReembolsadas(int contNcReembolsadas) {
        this.contNcReembolsadas = contNcReembolsadas;
    }

    public int getContCuotasIniciales() {
        return contCuotasIniciales;
    }

    public void setContCuotasIniciales(int contCuotasIniciales) {
        this.contCuotasIniciales = contCuotasIniciales;
    }

    public int getContGastos() {
        return contGastos;
    }

    public void setContGastos(int contGastos) {
        this.contGastos = contGastos;
    }

    public String getPrimeraFactura() {
        return primeraFactura;
    }

    public void setPrimeraFactura(String primeraFactura) {
        this.primeraFactura = primeraFactura;
    }

    public String getUltimaFactura() {
        return ultimaFactura;
    }

    public void setUltimaFactura(String ultimaFactura) {
        this.ultimaFactura = ultimaFactura;
    }

    public String getPrimerAbono() {
        return primerAbono;
    }

    public void setPrimerAbono(String primerAbono) {
        this.primerAbono = primerAbono;
    }

    public String getUltimoAbono() {
        return ultimoAbono;
    }

    public void setUltimoAbono(String ultimoAbono) {
        this.ultimoAbono = ultimoAbono;
    }
}
