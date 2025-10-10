package Controlador.FacturacionElectronica;

import Modelo.FacturacionElectronica.Entrada.ModeloConsultaFacturaElectronica;
import Modelo.FacturacionElectronica.Entrada.ModeloDatosFacturaElectronica;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class controladorConsultarFacturaJSON {

    public JSONObject crearJSON(ModeloConsultaFacturaElectronica datos) throws JSONException {
        JSONArray listadoFacturas = crearArrayFacturas(datos);

        JSONObject jsonFacturacionElectronica = new JSONObject()
                .put("tipoIdentificacion", datos.getTipoIdentificacion())
                .put("identificacion", datos.getIdentificacion())
                .put("facturas", listadoFacturas);

        return jsonFacturacionElectronica;
    }

    private JSONArray crearArrayFacturas(ModeloConsultaFacturaElectronica datos) throws JSONException {
        JSONArray listadoFacturas = new JSONArray();
        for (ModeloDatosFacturaElectronica datosFactura : datos.getFacturas()) {
            JSONObject factura = new JSONObject()
                    .put("prefijo", datosFactura.getPrejio())
                    .put("numero", datosFactura.getNumero());

            listadoFacturas.put(factura);
        }

        return listadoFacturas;
    }
}
