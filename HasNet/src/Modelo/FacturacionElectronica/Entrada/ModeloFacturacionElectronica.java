/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.FacturacionElectronica.Entrada;

import Modelo.DocumentosElectronicos.ModeloDetalleProductos;
import Modelo.DocumentosElectronicos.ModeloDescuentos;
import Modelo.DocumentosElectronicos.ModeloDetalleImpuestos;
import java.math.BigDecimal;

/**
 *
 * @author sebastian.londono
 */
public class ModeloFacturacionElectronica {

    String dsPrefijo;
    String dsNumeroFactura;
    String cufe;
    String dsVendedor;
    String fechaEmision;
    String fechaVencimiento;
    String emailAdquiriente;
    String tipoIdentificacionAdquiriente;
    String identificacionAdquiriente;
    String digitoVerificacionAdquiriente;
    String codigoPostalAdquirente;
    String tipoPersonaAdquiriente;
    String nombresAdquiriente;
    String segundoNombre;
    String primerApellido;
    String segundoApellido;
    String direccionAdquiriente;
    boolean adquirenteResponsable;
    String regimenAdquirente;
    String telefonoAdquiriente;

    String cdDaneCiudad;
    String dsNombreCiudad;
    String cdDaneDepartamento;
    String dsNombreDepartamento;
    String cdIsoPais;
    String dsNombrePais;

    String snDistribucionFisica;
    BigDecimal valorNeto;
    String dsObservacion;
    String tipoDocumentoElectronico;
    String dsNumeroReferencia;
    String dsPrefijoReferencia;
    BigDecimal porcentajeIva;
    BigDecimal porcentajeConsumo;
    BigDecimal dsPorcentajeReteFuente;
    BigDecimal dsRetencionFuente;
    BigDecimal dsPorcentajeReteIva;
    BigDecimal dsRetencionIva;
    BigDecimal dsPorcentajeDescuento;
    BigDecimal dsDescuento;
    BigDecimal valorBaseImponible;
    BigDecimal valorBrutoMasTributos;
    BigDecimal descuentoTotal;
    BigDecimal cargoTotal;
    BigDecimal anticipoTotal;
    BigDecimal valorTotalImpuestoConsumo;
    String moneda;
    BigDecimal valorBruto;
    BigDecimal valorIva;
    String tipoOperacion;
    int cdTipoPlantilla;
    String dsResolucionDian;
    String versionDian;
    String responsabilidadesFiscales;

    String formaPago;
    String medioPago;
    String fechaVencimientoPago;
    String idPago;

    String prefijoFacturaReferencia;
    String numeroFacturaReferencia;
    String conceptoNotaDebito;
    String conceptoNotaCredito;
    String descripcionNotaDebito;
    String descripcionNotaCredito;

    String placaCaja;
    String ubicacionCaja;
    String cajero;
    String tipoCaja;

    ModeloDetalleImpuestos impuestosFactura;
    ModeloDetalleProductos[] detalleProductos;
    ModeloDescuentos[] descuentosFactura;

    public String getCufe() {
        return cufe;
    }

    public void setCufe(String cufe) {
        this.cufe = cufe;
    }

    public int getCdTipoPlantilla() {
        return cdTipoPlantilla;
    }

    public void setCdTipoPlantilla(int cdTipoPlantilla) {
        this.cdTipoPlantilla = cdTipoPlantilla;
    }

    public String getPlacaCaja() {
        return placaCaja;
    }

    public void setPlacaCaja(String placaCaja) {
        this.placaCaja = placaCaja;
    }

    public String getUbicacionCaja() {
        return ubicacionCaja;
    }

    public void setUbicacionCaja(String ubicacionCaja) {
        this.ubicacionCaja = ubicacionCaja;
    }

    public String getCajero() {
        return cajero;
    }

    public void setCajero(String cajero) {
        this.cajero = cajero;
    }

    public String getTipoCaja() {
        return tipoCaja;
    }

    public void setTipoCaja(String tipoCaja) {
        this.tipoCaja = tipoCaja;
    }

    public String getPrefijoFacturaReferencia() {
        return prefijoFacturaReferencia;
    }

    public void setPrefijoFacturaReferencia(String prefijoFacturaReferencia) {
        this.prefijoFacturaReferencia = prefijoFacturaReferencia;
    }

    public String getNumeroFacturaReferencia() {
        return numeroFacturaReferencia;
    }

    public void setNumeroFacturaReferencia(String numeroFacturaReferencia) {
        this.numeroFacturaReferencia = numeroFacturaReferencia;
    }

    public String getDsVendedor() {
        return dsVendedor;
    }

    public void setDsVendedor(String dsVendedor) {
        this.dsVendedor = dsVendedor;
    }

    public String getConceptoNotaDebito() {
        return conceptoNotaDebito;
    }

    public void setConceptoNotaDebito(String conceptoNotaDebito) {
        this.conceptoNotaDebito = conceptoNotaDebito;
    }

    public String getDescripcionNotaDebito() {
        return descripcionNotaDebito;
    }

    public void setDescripcionNotaDebito(String descripcionNotaDebito) {
        this.descripcionNotaDebito = descripcionNotaDebito;
    }

    public String getDsPrefijo() {
        return dsPrefijo;
    }

    public void setDsPrefijo(String dsPrefijo) {
        this.dsPrefijo = dsPrefijo;
    }

    public String getDsNumeroFactura() {
        return dsNumeroFactura;
    }

    public void setDsNumeroFactura(String dsNumeroFactura) {
        this.dsNumeroFactura = dsNumeroFactura;
    }

    public String getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(String fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getEmailAdquiriente() {
        return emailAdquiriente;
    }

    public void setEmailAdquiriente(String emailAdquiriente) {
        this.emailAdquiriente = emailAdquiriente;
    }

    public String getTipoIdentificacionAdquiriente() {
        return tipoIdentificacionAdquiriente;
    }

    public void setTipoIdentificacionAdquiriente(String tipoIdentificacionAdquiriente) {
        this.tipoIdentificacionAdquiriente = tipoIdentificacionAdquiriente;
    }

    public String getIdentificacionAdquiriente() {
        return identificacionAdquiriente;
    }

    public void setIdentificacionAdquiriente(String identificacionAdquiriente) {
        this.identificacionAdquiriente = identificacionAdquiriente;
    }

    public String getDigitoVerificacionAdquiriente() {
        return digitoVerificacionAdquiriente;
    }

    public void setDigitoVerificacionAdquiriente(String digitoVerificacionAdquiriente) {
        this.digitoVerificacionAdquiriente = digitoVerificacionAdquiriente;
    }

    public String getCodigoPostalAdquirente() {
        return codigoPostalAdquirente;
    }

    public void setCodigoPostalAdquirente(String codigoPostalAdquirente) {
        this.codigoPostalAdquirente = codigoPostalAdquirente;
    }

    public String getTipoPersonaAdquiriente() {
        return tipoPersonaAdquiriente;
    }

    public void setTipoPersonaAdquiriente(String tipoPersonaAdquiriente) {
        this.tipoPersonaAdquiriente = tipoPersonaAdquiriente;
    }

    public String getNombresAdquiriente() {
        return nombresAdquiriente;
    }

    public void setNombresAdquiriente(String nombresAdquiriente) {
        this.nombresAdquiriente = nombresAdquiriente;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getDireccionAdquiriente() {
        return direccionAdquiriente;
    }

    public void setDireccionAdquiriente(String direccionAdquiriente) {
        this.direccionAdquiriente = direccionAdquiriente;
    }

    public boolean getAdquirenteResponsable() {
        return adquirenteResponsable;
    }

    public void setAdquirenteResponsable(boolean adquirenteResponsable) {
        this.adquirenteResponsable = adquirenteResponsable;
    }

    public String getRegimenAdquirente() {
        return regimenAdquirente;
    }

    public void setRegimenAdquirente(String regimenAdquirente) {
        this.regimenAdquirente = regimenAdquirente;
    }

    public String getTelefonoAdquiriente() {
        return telefonoAdquiriente;
    }

    public void setTelefonoAdquiriente(String telefonoAdquiriente) {
        this.telefonoAdquiriente = telefonoAdquiriente;
    }

    public String getCdDaneCiudad() {
        return cdDaneCiudad;
    }

    public void setCdDaneCiudad(String cdDaneCiudad) {
        this.cdDaneCiudad = cdDaneCiudad;
    }

    public String getDsNombreCiudad() {
        return dsNombreCiudad;
    }

    public void setDsNombreCiudad(String dsNombreCiudad) {
        this.dsNombreCiudad = dsNombreCiudad;
    }

    public String getCdDaneDepartamento() {
        return cdDaneDepartamento;
    }

    public void setCdDaneDepartamento(String cdDaneDepartamento) {
        this.cdDaneDepartamento = cdDaneDepartamento;
    }

    public String getDsNombreDepartamento() {
        return dsNombreDepartamento;
    }

    public void setDsNombreDepartamento(String dsNombreDepartamento) {
        this.dsNombreDepartamento = dsNombreDepartamento;
    }

    public String getCdIsoPais() {
        return cdIsoPais;
    }

    public void setCdIsoPais(String cdIsoPais) {
        this.cdIsoPais = cdIsoPais;
    }

    public String getDsNombrePais() {
        return dsNombrePais;
    }

    public void setDsNombrePais(String dsNombrePais) {
        this.dsNombrePais = dsNombrePais;
    }

    public String getSnDistribucionFisica() {
        return snDistribucionFisica;
    }

    public void setSnDistribucionFisica(String snDistribucionFisica) {
        this.snDistribucionFisica = snDistribucionFisica;
    }

    public BigDecimal getValorNeto() {
        return valorNeto;
    }

    public void setValorNeto(BigDecimal valorNeto) {
        this.valorNeto = valorNeto;
    }

    public String getDsObservacion() {
        return dsObservacion;
    }

    public void setDsObservacion(String dsObservacion) {
        this.dsObservacion = dsObservacion;
    }

    public String getTipoDocumentoElectronico() {
        return tipoDocumentoElectronico;
    }

    public void setTipoDocumentoElectronico(String tipoDocumentoElectronico) {
        this.tipoDocumentoElectronico = tipoDocumentoElectronico;
    }

    public String getDsNumeroReferencia() {
        return dsNumeroReferencia;
    }

    public void setDsNumeroReferencia(String dsNumeroReferencia) {
        this.dsNumeroReferencia = dsNumeroReferencia;
    }

    public String getDsPrefijoReferencia() {
        return dsPrefijoReferencia;
    }

    public void setDsPrefijoReferencia(String dsPrefijoReferencia) {
        this.dsPrefijoReferencia = dsPrefijoReferencia;
    }

    public BigDecimal getPorcentajeIva() {
        return porcentajeIva;
    }

    public void setPorcentajeIva(BigDecimal porcentajeIva) {
        this.porcentajeIva = porcentajeIva;
    }

    public BigDecimal getPorcentajeConsumo() {
        return porcentajeConsumo;
    }

    public void setPorcentajeConsumo(BigDecimal porcentajeConsumo) {
        this.porcentajeConsumo = porcentajeConsumo;
    }

    public BigDecimal getDsPorcentajeReteFuente() {
        return dsPorcentajeReteFuente;
    }

    public void setDsPorcentajeReteFuente(BigDecimal dsPorcentajeReteFuente) {
        this.dsPorcentajeReteFuente = dsPorcentajeReteFuente;
    }

    public BigDecimal getDsRetencionFuente() {
        return dsRetencionFuente;
    }

    public void setDsRetencionFuente(BigDecimal dsRetencionFuente) {
        this.dsRetencionFuente = dsRetencionFuente;
    }

    public BigDecimal getDsPorcentajeReteIva() {
        return dsPorcentajeReteIva;
    }

    public void setDsPorcentajeReteIva(BigDecimal dsPorcentajeReteIva) {
        this.dsPorcentajeReteIva = dsPorcentajeReteIva;
    }

    public BigDecimal getDsRetencionIva() {
        return dsRetencionIva;
    }

    public void setDsRetencionIva(BigDecimal dsRetencionIva) {
        this.dsRetencionIva = dsRetencionIva;
    }

    public BigDecimal getDsPorcentajeDescuento() {
        return dsPorcentajeDescuento;
    }

    public void setDsPorcentajeDescuento(BigDecimal dsPorcentajeDescuento) {
        this.dsPorcentajeDescuento = dsPorcentajeDescuento;
    }

    public BigDecimal getDsDescuento() {
        return dsDescuento;
    }

    public void setDsDescuento(BigDecimal dsDescuento) {
        this.dsDescuento = dsDescuento;
    }

    public BigDecimal getValorBaseImponible() {
        return valorBaseImponible;
    }

    public void setValorBaseImponible(BigDecimal valorBaseImponible) {
        this.valorBaseImponible = valorBaseImponible;
    }

    public BigDecimal getValorBrutoMasTributos() {
        return valorBrutoMasTributos;
    }

    public void setValorBrutoMasTributos(BigDecimal valorBrutoMasTributos) {
        this.valorBrutoMasTributos = valorBrutoMasTributos;
    }

    public BigDecimal getDescuentoTotal() {
        return descuentoTotal;
    }

    public void setDescuentoTotal(BigDecimal descuentoTotal) {
        this.descuentoTotal = descuentoTotal;
    }

    public BigDecimal getCargoTotal() {
        return cargoTotal;
    }

    public void setCargoTotal(BigDecimal cargoTotal) {
        this.cargoTotal = cargoTotal;
    }

    public BigDecimal getAnticipoTotal() {
        return anticipoTotal;
    }

    public void setAnticipoTotal(BigDecimal anticipoTotal) {
        this.anticipoTotal = anticipoTotal;
    }

    public BigDecimal getValorTotalImpuestoConsumo() {
        return valorTotalImpuestoConsumo;
    }

    public void setValorTotalImpuestoConsumo(BigDecimal valorTotalImpuestoConsumo) {
        this.valorTotalImpuestoConsumo = valorTotalImpuestoConsumo;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public BigDecimal getValorBruto() {
        return valorBruto;
    }

    public void setValorBruto(BigDecimal valorBruto) {
        this.valorBruto = valorBruto;
    }

    public BigDecimal getValorIva() {
        return valorIva;
    }

    public void setValorIva(BigDecimal valorIva) {
        this.valorIva = valorIva;
    }

    public String getTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoOperacion(String tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    public String getDsResolucionDian() {
        return dsResolucionDian;
    }

    public void setDsResolucionDian(String dsResolucionDian) {
        this.dsResolucionDian = dsResolucionDian;
    }

    public String getVersionDian() {
        return versionDian;
    }

    public void setVersionDian(String versionDian) {
        this.versionDian = versionDian;
    }

    public String getResponsabilidadesFiscales() {
        return responsabilidadesFiscales;
    }

    public void setResponsabilidadesFiscales(String responsabilidadesFiscales) {
        this.responsabilidadesFiscales = responsabilidadesFiscales;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public String getMedioPago() {
        return medioPago;
    }

    public void setMedioPago(String medioPago) {
        this.medioPago = medioPago;
    }

    public String getFechaVencimientoPago() {
        return fechaVencimientoPago;
    }

    public void setFechaVencimientoPago(String fechaVencimientoPago) {
        this.fechaVencimientoPago = fechaVencimientoPago;
    }

    public String getIdPago() {
        return idPago;
    }

    public void setIdPago(String idPago) {
        this.idPago = idPago;
    }

    public ModeloDetalleImpuestos getImpuestosFactura() {
        return impuestosFactura;
    }

    public void setImpuestosFactura(ModeloDetalleImpuestos impuestosFactura) {
        this.impuestosFactura = impuestosFactura;
    }

    public ModeloDetalleProductos[] getDetalleProductos() {
        return detalleProductos;
    }

    public void setDetalleProductos(ModeloDetalleProductos[] detalleProductos) {
        this.detalleProductos = detalleProductos;
    }

    public ModeloDescuentos[] getDescuentosFactura() {
        return descuentosFactura;
    }

    public void setDescuentosFactura(ModeloDescuentos[] descuentosFactura) {
        this.descuentosFactura = descuentosFactura;
    }

    public String getConceptoNotaCredito() {
        return conceptoNotaCredito;
    }

    public void setConceptoNotaCredito(String conceptoNotaCredito) {
        this.conceptoNotaCredito = conceptoNotaCredito;
    }

    public String getDescripcionNotaCredito() {
        return descripcionNotaCredito;
    }

    public void setDescripcionNotaCredito(String descripcionNotaCredito) {
        this.descripcionNotaCredito = descripcionNotaCredito;
    }
}
