/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DocumentosElectronicos;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author sebastian.londono
 */
public class ModeloDescuentos {

    boolean tipo;
    String codigoDescuento;
    String razonDescuento;
    String valorDescuento;
    String valorBase;
    String porcentaje;

    public boolean isTipo() {
        return tipo;
    }

    public void setTipo(boolean tipo) {
        this.tipo = tipo;
    }

    public String getCodigoDescuento() {
        return codigoDescuento;
    }

    public void setCodigoDescuento(String codigoDescuento) {
        this.codigoDescuento = codigoDescuento;
    }

    public String getRazonDescuento() {
        return razonDescuento;
    }

    public void setRazonDescuento(String razonDescuento) {
        this.razonDescuento = razonDescuento;
    }

    public String getValorDescuento() {
        return valorDescuento;
    }

    public void setValorDescuento(String valorDescuento) {
        this.valorDescuento = valorDescuento;
    }

    public String getValorBase() {
        return valorBase;
    }

    public void setValorBase(String valorBase) {
        this.valorBase = valorBase;
    }

    public String getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(String porcentaje) {
        this.porcentaje = porcentaje;
    }
    
    public JSONArray crearArrayDescuentos(ModeloDescuentos[] descuentos) throws JSONException {
        boolean existenDatos = false;
        JSONArray listadoDescuentosFactura = new JSONArray();

        for (ModeloDescuentos datosDescuento : descuentos) {
            if (null != datosDescuento) {
                JSONObject registroIndividual = new JSONObject()
                        .put("tipo", datosDescuento.isTipo())
                        .put("codigoDescuento", datosDescuento.getCodigoDescuento())
                        .put("razon", datosDescuento.getRazonDescuento())
                        .put("valor", datosDescuento.getValorDescuento())
                        .put("porcentaje", datosDescuento.getPorcentaje())
                        .put("valorBase", datosDescuento.getValorBase());
                existenDatos = true;
                listadoDescuentosFactura.put(registroIndividual);
            }
        }

        return existenDatos ? listadoDescuentosFactura : null;
    }
    
    public JSONArray crearArrayDescuentoProducto(ModeloDescuentos[] descuentosProducto) throws JSONException {
        boolean existenDatos = false;
        JSONArray listadoDescuentosFactura = new JSONArray();

        for (ModeloDescuentos datosDescuentos : descuentosProducto) {
            if (null != datosDescuentos) {
                JSONObject registroIndividual = new JSONObject()
                        .put("tipo", datosDescuentos.isTipo())
                        .put("codigoDescuento", datosDescuentos.getCodigoDescuento())
                        .put("razon", datosDescuentos.getRazonDescuento())
                        .put("valor", datosDescuentos.getValorDescuento())
                        .put("porcentaje", datosDescuentos.getPorcentaje())
                        .put("valorBase", datosDescuentos.getValorBase());
                existenDatos = true;
                listadoDescuentosFactura.put(registroIndividual);
            }
        }

        return existenDatos ? listadoDescuentosFactura : null;
    }
}
