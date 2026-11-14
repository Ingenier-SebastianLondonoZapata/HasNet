package Modelo.Documentos;

import Enums.TipoDocumento;
import java.math.BigDecimal;

public class ModeloFactura extends ModeloDocumento {

    private BigDecimal tarjetaCredito = BigDecimal.ZERO;
    private BigDecimal totalPropina = BigDecimal.ZERO;
    private String porcentajePropina = "0";
    private String tipoComprobante = "";
    private String congelada = "";
    private String mesFacturado = "";
    private String idCosteo = "";
    private boolean sisteCredito;

    public ModeloFactura(TipoDocumento tipoDocumento) {
        super(tipoDocumento);
    }

    public BigDecimal getTarjetaCredito() {
        return tarjetaCredito;
    }

    public void setTarjetaCredito(BigDecimal tarjetaCredito) {
        this.tarjetaCredito = tarjetaCredito;
    }

    public BigDecimal getTotalPropina() {
        return totalPropina;
    }

    public void setTotalPropina(BigDecimal totalPropina) {
        this.totalPropina = totalPropina;
    }

    public String getPorcentajePropina() {
        return porcentajePropina;
    }

    public void setPorcentajePropina(String porcentajePropina) {
        this.porcentajePropina = porcentajePropina;
    }

    public String getTipoComprobante() {
        return tipoComprobante;
    }

    public void setTipoComprobante(String tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }

    public String getCongelada() {
        return congelada;
    }

    public void setCongelada(String congelada) {
        this.congelada = congelada;
    }

    public String getMesFacturado() {
        return mesFacturado;
    }

    public void setMesFacturado(String mesFacturado) {
        this.mesFacturado = mesFacturado;
    }

    public String getIdCosteo() {
        return idCosteo;
    }

    public void setIdCosteo(String idCosteo) {
        this.idCosteo = idCosteo;
    }

    public boolean isSisteCredito() {
        return sisteCredito;
    }

    public void setSisteCredito(boolean sisteCredito) {
        this.sisteCredito = sisteCredito;
    }
}
