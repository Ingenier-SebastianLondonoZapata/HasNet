/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DocumentosElectronicos;

import clases.big;
import java.math.BigDecimal;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author sebastian.londono
 */
public class ModeloDetalleImpuestos {

    Object[][] impuestosIvas;
    Object[][] impuestosImpoconsumo;
    Object[][] impuestosReteIva;
    Object[][] impuestosReteFuente;

    public Object[][] getImpuestosIvas() {
        return impuestosIvas;
    }

    public void setImpuestosIvas(Object[][] impuestosIvas) {
        this.impuestosIvas = impuestosIvas;
    }

    public Object[][] getImpuestosImpoconsumo() {
        return impuestosImpoconsumo;
    }

    public void setImpuestosImpoconsumo(Object[][] impuestosImpoconsumo) {
        this.impuestosImpoconsumo = impuestosImpoconsumo;
    }

    public Object[][] getImpuestosReteIva() {
        return impuestosReteIva;
    }

    public void setImpuestosReteIva(Object[][] impuestosReteIva) {
        this.impuestosReteIva = impuestosReteIva;
    }

    public Object[][] getImpuestosReteFuente() {
        return impuestosReteFuente;
    }

    public void setImpuestosReteFuente(Object[][] impuestosReteFuente) {
        this.impuestosReteFuente = impuestosReteFuente;
    }

    public JSONArray crearArrayImpuestos(ModeloDetalleImpuestos detalleImpuestos) throws JSONException {

        JSONArray listadoImpuestosFactura = new JSONArray();
        JSONArray jsonImpuestosIvas = new JSONArray();
        JSONArray jsonImpuestosImpoconsumo = new JSONArray();
        JSONArray jsonImpuestosReteIva = new JSONArray();
        JSONArray jsonImpuestosReteFuente = new JSONArray();

        BigDecimal totalIva = BigDecimal.ZERO;
        BigDecimal totalImpoconsumo = BigDecimal.ZERO;
        BigDecimal totalReteIva = BigDecimal.ZERO;
        BigDecimal totalReteFuente = BigDecimal.ZERO;

        //Calculamos los totales
        for (Object[] impuestosIva : detalleImpuestos.getImpuestosIvas()) {
            totalIva = totalIva.add(big.getBigDecimal(impuestosIva[1]));
        }

        for (Object[] impuestosImpoconsumo : detalleImpuestos.getImpuestosImpoconsumo()) {
            totalImpoconsumo = totalImpoconsumo.add(big.getBigDecimal(impuestosImpoconsumo[1]));
        }

        if (detalleImpuestos.getImpuestosReteIva().length > 0 && null != detalleImpuestos.getImpuestosReteIva()[0][1]) {
            totalReteIva = big.getBigDecimal(detalleImpuestos.getImpuestosReteIva()[0][1]);
        }

        if (detalleImpuestos.getImpuestosReteFuente().length > 0 && null != detalleImpuestos.getImpuestosReteFuente()[0][1]) {
            totalReteFuente = big.getBigDecimal(detalleImpuestos.getImpuestosReteFuente()[0][1]);
        }

        //Creacion de arrays de los impuestos
        for (int i = 0; i < detalleImpuestos.getImpuestosIvas().length; i++) {
            jsonImpuestosIvas.put(crearDetalleImpuesto(detalleImpuestos.getImpuestosIvas()[i]));
        }

        for (int i = 0; i < detalleImpuestos.getImpuestosImpoconsumo().length; i++) {
            jsonImpuestosImpoconsumo.put(crearDetalleImpuesto(detalleImpuestos.getImpuestosImpoconsumo()[i]));
        }

        for (int i = 0; i < detalleImpuestos.getImpuestosReteIva().length; i++) {
            jsonImpuestosReteIva.put(crearDetalleImpuesto(detalleImpuestos.getImpuestosReteIva()[i]));
        }

        for (int i = 0; i < detalleImpuestos.getImpuestosReteFuente().length; i++) {
            jsonImpuestosReteFuente.put(crearDetalleImpuesto(detalleImpuestos.getImpuestosReteFuente()[i]));
        }

        JSONObject registroIndividual = new JSONObject();
        if (totalIva.compareTo(BigDecimal.ZERO) > 0) {
            registroIndividual = new JSONObject()
                    .put("valorTotal", totalIva)
                    .put("subtotales", jsonImpuestosIvas);
            listadoImpuestosFactura.put(registroIndividual);
        }

        if (totalImpoconsumo.compareTo(BigDecimal.ZERO) > 0) {
            registroIndividual = new JSONObject()
                    .put("valorTotal", totalImpoconsumo)
                    .put("subtotales", jsonImpuestosImpoconsumo);
            listadoImpuestosFactura.put(registroIndividual);
        }

        if (totalReteIva.compareTo(BigDecimal.ZERO) > 0) {
            registroIndividual = new JSONObject()
                    .put("valorTotal", totalReteIva)
                    .put("subtotales", jsonImpuestosReteIva);
            listadoImpuestosFactura.put(registroIndividual);
        }

        if (totalReteFuente.compareTo(BigDecimal.ZERO) > 0) {
            registroIndividual = new JSONObject()
                    .put("valorTotal", totalReteFuente)
                    .put("subtotales", jsonImpuestosReteFuente);
            listadoImpuestosFactura.put(registroIndividual);
        }

        return listadoImpuestosFactura;
    }

    private JSONObject crearDetalleImpuesto(Object[] datosImpuesto) throws JSONException {
        return new JSONObject().put("valorBase", datosImpuesto[0])
                .put("valorImpuestoRetencion", datosImpuesto[1])
                .put("porcentaje", datosImpuesto[2])
                .put("tributo", datosImpuesto[3]);
    }

    public JSONArray crearArrayImpuestosProducto(Object[][] impuestosProducto) throws JSONException {
        JSONArray listadoImpuestosFactura = new JSONArray();
        for (Object[] datosImpuesto : impuestosProducto) {
            if (null != datosImpuesto[3]) {
                JSONObject registroIndividual = new JSONObject()
                        .put("valorTotal", datosImpuesto[1])
                        .put("subtotales", new JSONArray().put(
                                new JSONObject()
                                        .put("valorBase", datosImpuesto[0])
                                        .put("valorImpuestoRetencion", datosImpuesto[1])
                                        .put("porcentaje", datosImpuesto[2])
                                        .put("tributo", datosImpuesto[3]))
                        );
                listadoImpuestosFactura.put(registroIndividual);
            }
        }

        return listadoImpuestosFactura;
    }
}
