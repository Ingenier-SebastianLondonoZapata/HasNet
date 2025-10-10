/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.FacturacionElectronica.Entrada;

/**
 *
 * @author sebastian.londono
 */
public class ModeloConsultaFacturaElectronica {

    String tipoIdentificacion;
    String identificacion;
    
    ModeloDatosFacturaElectronica[] facturas;

    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public ModeloDatosFacturaElectronica[] getFacturas() {
        return facturas;
    }

    public void setFacturas(ModeloDatosFacturaElectronica[] facturas) {
        this.facturas = facturas;
    }
    
}
