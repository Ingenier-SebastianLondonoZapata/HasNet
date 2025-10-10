/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DocumentoSoporte.Entrada;

import Modelo.DocumentosElectronicos.ModeloDetalleProductos;
import Modelo.DocumentosElectronicos.ModeloDescuentos;
import Modelo.DocumentosElectronicos.ModeloDetalleImpuestos;
import java.math.BigDecimal;

/**
 *
 * @author sebastian.londono
 */
public class ModeloDocumentoSoporte {

    String dsPrefijo;
    String dsNumeroFactura;
    String tipoOperacion;
    String fechaEmision;
    String fechaVencimiento;
    String tipoDocumentoElectronico;
    String dsResolucionDian;

    String emailVendedor;
    String tipoIdentificacionVendedor;
    String identificacionVendedor;
    String digitoVerificacionVendedor;
    String tipoPersonaVendedor;
    String regimenVendedor;
    String nombresVendedor;
    String segundoNombre;
    String primerApellido;
    String segundoApellido;
    String codigoPostalVendedor;
    String direccionVendedor;
    boolean vendedorResponsable;
    String telefonoVendedor;
    String identificadorTributarioVendedor;

    String cdDaneCiudad;
    String dsNombreCiudad;
    String cdDaneDepartamento;
    String dsNombreDepartamento;
    String cdIsoPais;
    String dsNombrePais;
    
    String formaPago;
    String medioPago;
    
    String responsabilidadesFiscales;
    String moneda;
    BigDecimal valorBruto;
    BigDecimal valorBaseImponible;
    BigDecimal valorBrutoMasTributos;
    BigDecimal descuentoTotal;
    BigDecimal cargoTotal;
    BigDecimal valorNeto;
    
    ModeloDetalleImpuestos impuestosCompra;
    ModeloDetalleProductos[] detalleProductos;
    ModeloDescuentos[] descuentosCompra;   
    
    String prefijoNotaCreditoReferencia;
    String numeroNotaCreditoReferencia;
    String conceptoNotaCreditoReferencia;
    String descripcionNotaCreditoReferencia;

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

    public String getTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoOperacion(String tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
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

    public String getTipoDocumentoElectronico() {
        return tipoDocumentoElectronico;
    }

    public void setTipoDocumentoElectronico(String tipoDocumentoElectronico) {
        this.tipoDocumentoElectronico = tipoDocumentoElectronico;
    }

    public String getDsResolucionDian() {
        return dsResolucionDian;
    }

    public void setDsResolucionDian(String dsResolucionDian) {
        this.dsResolucionDian = dsResolucionDian;
    }

    public String getEmailVendedor() {
        return emailVendedor;
    }

    public void setEmailVendedor(String emailVendedor) {
        this.emailVendedor = emailVendedor;
    }

    public String getTipoIdentificacionVendedor() {
        return tipoIdentificacionVendedor;
    }

    public void setTipoIdentificacionVendedor(String tipoIdentificacionVendedor) {
        this.tipoIdentificacionVendedor = tipoIdentificacionVendedor;
    }

    public String getIdentificacionVendedor() {
        return identificacionVendedor;
    }

    public void setIdentificacionVendedor(String identificacionVendedor) {
        this.identificacionVendedor = identificacionVendedor;
    }

    public String getDigitoVerificacionVendedor() {
        return digitoVerificacionVendedor;
    }

    public void setDigitoVerificacionVendedor(String digitoVerificacionVendedor) {
        this.digitoVerificacionVendedor = digitoVerificacionVendedor;
    }

    public String getTipoPersonaVendedor() {
        return tipoPersonaVendedor;
    }

    public void setTipoPersonaVendedor(String tipoPersonaVendedor) {
        this.tipoPersonaVendedor = tipoPersonaVendedor;
    }

    public String getRegimenVendedor() {
        return regimenVendedor;
    }

    public void setRegimenVendedor(String regimenVendedor) {
        this.regimenVendedor = regimenVendedor;
    }

    public String getNombresVendedor() {
        return nombresVendedor;
    }

    public void setNombresVendedor(String nombresVendedor) {
        this.nombresVendedor = nombresVendedor;
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

    public String getCodigoPostalVendedor() {
        return codigoPostalVendedor;
    }

    public void setCodigoPostalVendedor(String codigoPostalVendedor) {
        this.codigoPostalVendedor = codigoPostalVendedor;
    }

    public String getDireccionVendedor() {
        return direccionVendedor;
    }

    public void setDireccionVendedor(String direccionVendedor) {
        this.direccionVendedor = direccionVendedor;
    }

    public boolean isVendedorResponsable() {
        return vendedorResponsable;
    }

    public void setVendedorResponsable(boolean vendedorResponsable) {
        this.vendedorResponsable = vendedorResponsable;
    }

    public String getTelefonoVendedor() {
        return telefonoVendedor;
    }

    public void setTelefonoVendedor(String telefonoVendedor) {
        this.telefonoVendedor = telefonoVendedor;
    }

    public String getIdentificadorTributarioVendedor() {
        return identificadorTributarioVendedor;
    }

    public void setIdentificadorTributarioVendedor(String identificadorTributarioVendedor) {
        this.identificadorTributarioVendedor = identificadorTributarioVendedor;
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

    public String getResponsabilidadesFiscales() {
        return responsabilidadesFiscales;
    }

    public void setResponsabilidadesFiscales(String responsabilidadesFiscales) {
        this.responsabilidadesFiscales = responsabilidadesFiscales;
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

    public BigDecimal getValorNeto() {
        return valorNeto;
    }

    public void setValorNeto(BigDecimal valorNeto) {
        this.valorNeto = valorNeto;
    }

    public ModeloDetalleImpuestos getImpuestosCompra() {
        return impuestosCompra;
    }

    public void setImpuestosCompra(ModeloDetalleImpuestos impuestosCompra) {
        this.impuestosCompra = impuestosCompra;
    }

    public ModeloDetalleProductos[] getDetalleProductos() {
        return detalleProductos;
    }

    public void setDetalleProductos(ModeloDetalleProductos[] detalleProductos) {
        this.detalleProductos = detalleProductos;
    }

    public ModeloDescuentos[] getDescuentosCompra() {
        return descuentosCompra;
    }

    public void setDescuentosFactura(ModeloDescuentos[] descuentosCompra) {
        this.descuentosCompra = descuentosCompra;
    } 

    public String getPrefijoNotaCreditoReferencia() {
        return prefijoNotaCreditoReferencia;
    }

    public void setPrefijoNotaCreditoReferencia(String prefijoNotaCreditoReferencia) {
        this.prefijoNotaCreditoReferencia = prefijoNotaCreditoReferencia;
    }

    public String getNumeroNotaCreditoReferencia() {
        return numeroNotaCreditoReferencia;
    }

    public void setNumeroNotaCreditoReferencia(String numeroNotaCreditoReferencia) {
        this.numeroNotaCreditoReferencia = numeroNotaCreditoReferencia;
    }

    public String getConceptoNotaCreditoReferencia() {
        return conceptoNotaCreditoReferencia;
    }

    public void setConceptoNotaCreditoReferencia(String conceptoNotaCreditoReferencia) {
        this.conceptoNotaCreditoReferencia = conceptoNotaCreditoReferencia;
    }

    public String getDescripcionNotaCreditoReferencia() {
        return descripcionNotaCreditoReferencia;
    }

    public void setDescripcionNotaCreditoReferencia(String descripcionNotaCreditoReferencia) {
        this.descripcionNotaCreditoReferencia = descripcionNotaCreditoReferencia;
    }
}
