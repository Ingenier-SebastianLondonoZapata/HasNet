/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.FacturacionElectronica.Salida;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author sebastian.londono
 */
public class ConsultaFacturaElectronicaDTO {

    String prefijo;
    String numero;
    String fecha;
    String cufe;
    String QRCode;
    String PDF;
    String XML;
    String AttachDocument;

    public String getPrefijo() {
        return prefijo;
    }

    public void setPrefijo(String prefijo) {
        this.prefijo = prefijo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCufe() {
        return cufe;
    }

    public void setCufe(String cufe) {
        this.cufe = cufe;
    }

    public String getQRCode() {
        return QRCode;
    }

    public void setQRCode(String QRCode) {
        this.QRCode = QRCode;
    }

    public String getPDF() {
        return PDF;
    }

    public void setPDF(String PDF) {
        this.PDF = PDF;
    }

    public String getXML() {
        return XML;
    }

    public void setXML(String XML) {
        this.XML = XML;
    }

    public String getAttachDocument() {
        return AttachDocument;
    }

    public void setAttachDocument(String AttachDocument) {
        this.AttachDocument = AttachDocument;
    }

    public ConsultaFacturaElectronicaDTO crearModeloFactura(String respuesta) throws JSONException {
        JSONObject datosFactura = new JSONArray(respuesta).getJSONObject(0);
        ConsultaFacturaElectronicaDTO modeloFactura = new ConsultaFacturaElectronicaDTO();
        modeloFactura.setPrefijo(datosFactura.getString("prefijo"));
        modeloFactura.setNumero(datosFactura.getString("numero"));
        modeloFactura.setFecha(datosFactura.getString("fecha"));
        modeloFactura.setCufe(datosFactura.getString("cufe"));
        modeloFactura.setQRCode(datosFactura.getString("QRCode"));
        modeloFactura.setPDF(datosFactura.getString("PDF"));
        modeloFactura.setXML(datosFactura.getString("XML"));
        modeloFactura.setAttachDocument(datosFactura.getString("AttachDocument"));
        return modeloFactura;
    }
}
