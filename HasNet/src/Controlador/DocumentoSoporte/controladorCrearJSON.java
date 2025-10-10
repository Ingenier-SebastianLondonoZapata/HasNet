package Controlador.DocumentoSoporte;

import Modelo.DocumentoSoporte.Entrada.ModeloDocumentoSoporte;
import Modelo.DocumentosElectronicos.ModeloDescuentos;
import Modelo.DocumentosElectronicos.ModeloDetalleProductos;
import Modelo.DocumentosElectronicos.ModeloDetalleImpuestos;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class controladorCrearJSON {

    ModeloDetalleImpuestos detalleImpuestos = new ModeloDetalleImpuestos();
    ModeloDescuentos descuentos = new ModeloDescuentos();
    
    public JSONObject crearJSON(ModeloDocumentoSoporte modeloDocumentoSoporte, boolean esNotaCredito, int canal) throws JSONException {

        JSONArray listadoImpuestosFactura = detalleImpuestos.crearArrayImpuestos(modeloDocumentoSoporte.getImpuestosCompra());
        JSONArray listadoDetalleProductos = crearArrayDetalleProductos(modeloDocumentoSoporte);
        JSONArray listadoDescuentos = descuentos.crearArrayDescuentos(modeloDocumentoSoporte.getDescuentosCompra());

        JSONObject jsonFacturacionElectronica = new JSONObject()
                .put("canal", canal)
                .put("dsPrefijo", modeloDocumentoSoporte.getDsPrefijo())
                .put("dsNumeroFactura", modeloDocumentoSoporte.getDsNumeroFactura())
                .put("tipoOperacion", modeloDocumentoSoporte.getTipoOperacion())
                .put("fechaEmision", modeloDocumentoSoporte.getFechaEmision())
                .put("fechaVencimiento", modeloDocumentoSoporte.getFechaVencimiento())
                .put("tipoDocumentoElectronico", modeloDocumentoSoporte.getTipoDocumentoElectronico())
                .put("dsResolucionDian", modeloDocumentoSoporte.getDsResolucionDian())
                
                .put("emailVendedor", modeloDocumentoSoporte.getEmailVendedor())
                .put("tipoIdentificacionVendedor", modeloDocumentoSoporte.getTipoIdentificacionVendedor())
                .put("identificacionVendedor", modeloDocumentoSoporte.getIdentificacionVendedor())
                .put("digitoVerificacionVendedor", modeloDocumentoSoporte.getDigitoVerificacionVendedor())
                .put("tipoPersonaVendedor", modeloDocumentoSoporte.getTipoPersonaVendedor())
                .put("regimenVendedor", modeloDocumentoSoporte.getRegimenVendedor())
                .put("nombresVendedor", modeloDocumentoSoporte.getNombresVendedor())
                .put("segundoNombre", modeloDocumentoSoporte.getSegundoNombre())
                .put("primerApellido", modeloDocumentoSoporte.getPrimerApellido())
                .put("segundoApellido", modeloDocumentoSoporte.getSegundoApellido())
                .put("codigoPostalVendedor", modeloDocumentoSoporte.getCodigoPostalVendedor())
                .put("direccionVendedor", modeloDocumentoSoporte.getDireccionVendedor())
                .put("vendedorResponsable", modeloDocumentoSoporte.isVendedorResponsable())
                .put("telefonoVendedor", modeloDocumentoSoporte.getTelefonoVendedor())
                .put("identificadorTributarioVendedor", modeloDocumentoSoporte.getIdentificadorTributarioVendedor())
                                
                .put("ciudadVendedor", new JSONObject()
                        .put("cdDane", modeloDocumentoSoporte.getCdDaneCiudad())
                        .put("dsNombre", modeloDocumentoSoporte.getDsNombreCiudad())
                        .put("departamento", new JSONObject()
                                .put("cdDane", modeloDocumentoSoporte.getCdDaneDepartamento())
                                .put("dsNombre", modeloDocumentoSoporte.getDsNombreDepartamento())
                                .put("pais", new JSONObject()
                                        .put("cdIso", modeloDocumentoSoporte.getCdIsoPais())
                                        .put("dsNombre", modeloDocumentoSoporte.getDsNombrePais()))
                        )
                )
                
                .put("pago", new JSONObject()
                        .put("formaPago", modeloDocumentoSoporte.getFormaPago())
                        .put("medioPago", modeloDocumentoSoporte.getMedioPago())
                )
                
                .put("responsabilidadesFiscales", modeloDocumentoSoporte.getResponsabilidadesFiscales())
                .put("moneda", modeloDocumentoSoporte.getMoneda())
                .put("valorBruto", modeloDocumentoSoporte.getValorBruto())
                .put("valorBaseImponible", modeloDocumentoSoporte.getValorBaseImponible())
                .put("valorBrutoMasTributos", modeloDocumentoSoporte.getValorBrutoMasTributos())
                .put("descuentoTotal", modeloDocumentoSoporte.getDescuentoTotal())
                .put("cargoTotal", modeloDocumentoSoporte.getCargoTotal())
                .put("valorNeto", modeloDocumentoSoporte.getValorNeto())
                
                .put("cargosDescuentos", listadoDescuentos)      
                .put("impuestosRetenciones", listadoImpuestosFactura)
                .put("detalles", listadoDetalleProductos);
                
        if (esNotaCredito) {
            jsonFacturacionElectronica.put("facturasReferencia", new JSONArray().put(
                    new JSONObject()
                            .put("prefijo", modeloDocumentoSoporte.getPrefijoNotaCreditoReferencia())
                            .put("numero", modeloDocumentoSoporte.getNumeroNotaCreditoReferencia())
                            .put("conceptoNotaDebito", modeloDocumentoSoporte.getConceptoNotaCreditoReferencia())
                            .put("descripcion", modeloDocumentoSoporte.getDescripcionNotaCreditoReferencia())));
        }

        return jsonFacturacionElectronica;
    }

    private JSONArray crearArrayDetalleProductos(ModeloDocumentoSoporte modeloDocumentoSoporte) throws JSONException {

        JSONArray listadoDetalleProductos = new JSONArray();
        for (ModeloDetalleProductos datosProducto : modeloDocumentoSoporte.getDetalleProductos()) {

            JSONArray listadoImpuestosProducto = detalleImpuestos.crearArrayImpuestosProducto(datosProducto.getImpuestosProducto());
            JSONArray listaDescuento = descuentos.crearArrayDescuentoProducto(datosProducto.getDescuentoProducto());

            JSONObject registroIndividual = new JSONObject()
                    .put("numeroFactura", datosProducto.getNumeroFactura())
                    .put("estandarProducto", datosProducto.getEstandarProducto())
                    .put("nota", datosProducto.getObservacionDetalle())
                    .put("unidadMedida", datosProducto.getUnidadMedida())
                    .put("valorTotalBruto", datosProducto.getValorTotalBruto())
                    
                    .put("periodoFactura", new JSONObject()
                        .put("fechaInicio", datosProducto.getFechaInicio())
                        .put("codigoGeneracion", datosProducto.getCodigoGeneracion())
                    )
                    
                    .put("descripcionArticulo", datosProducto.getDescripcionArticulo())
                    .put("unidadesEmpaque", datosProducto.getUnidadesEmpaque())
                    .put("codigoArticulo", datosProducto.getCodigoArticulo())
                    .put("precioUnitario", datosProducto.getPrecioUnitario())
                    .put("cantidad", datosProducto.getCantidad())
                    
                    .put("cargosDescuentos", listaDescuento)
                    .put("impuestosRetenciones", listadoImpuestosProducto);

            listadoDetalleProductos.put(registroIndividual);
        }

        return listadoDetalleProductos;
    }    
}
