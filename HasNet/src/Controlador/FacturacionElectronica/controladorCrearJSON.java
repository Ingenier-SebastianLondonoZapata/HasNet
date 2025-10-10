package Controlador.FacturacionElectronica;

import Modelo.DocumentosElectronicos.ModeloDescuentos;
import Modelo.DocumentosElectronicos.ModeloDetalleProductos;
import Modelo.DocumentosElectronicos.ModeloDetalleImpuestos;
import Modelo.FacturacionElectronica.Entrada.ModeloFacturacionElectronica;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class controladorCrearJSON {

     ModeloDetalleImpuestos detalleImpuestos = new ModeloDetalleImpuestos();
     ModeloDescuentos descuentos = new ModeloDescuentos();
    
    public JSONObject crearJSON(ModeloFacturacionElectronica modeloFacturacion, boolean esNotaDebito, boolean esNotaDebitoPos,
            boolean esNotaCredito, boolean esFacturacionPos, int canal) throws JSONException {

        JSONArray listadoImpuestosFactura = detalleImpuestos.crearArrayImpuestos(modeloFacturacion.getImpuestosFactura());
        JSONArray listadoDetalleProductos = crearArrayDetalleProductos(modeloFacturacion);
        JSONArray listadoDescuentos = descuentos.crearArrayDescuentos(modeloFacturacion.getDescuentosFactura());

        JSONObject jsonFacturacionElectronica = new JSONObject()
                .put("canal", canal)
                .put("dsPrefijo", modeloFacturacion.getDsPrefijo())
                .put("dsNumeroFactura", modeloFacturacion.getDsNumeroFactura())
                .put("dsVendedor", modeloFacturacion.getDsVendedor())
                .put("fechaEmision", modeloFacturacion.getFechaEmision())
                .put("fechaVencimiento", modeloFacturacion.getFechaVencimiento())
                .put("emailAdquiriente", modeloFacturacion.getEmailAdquiriente())
                .put("tipoIdentificacionAdquiriente", modeloFacturacion.getTipoIdentificacionAdquiriente())
                .put("identificacionAdquiriente", modeloFacturacion.getIdentificacionAdquiriente())
                .put("digitoVerificacionAdquirente", modeloFacturacion.getDigitoVerificacionAdquiriente())
                .put("codigoPostalAdquirente", modeloFacturacion.getCodigoPostalAdquirente())
                .put("tipoPersonaAdquiriente", modeloFacturacion.getTipoPersonaAdquiriente())
                .put("nombresAdquiriente", modeloFacturacion.getNombresAdquiriente())
                .put("segundoNombre", modeloFacturacion.getSegundoNombre())
                .put("primerApellido", modeloFacturacion.getPrimerApellido())
                .put("segundoApellido", modeloFacturacion.getSegundoApellido())
                .put("direccionAdquiriente", modeloFacturacion.getDireccionAdquiriente())
                .put("adquirenteResponsable", modeloFacturacion.getAdquirenteResponsable())
                .put("regimenAdquirente", modeloFacturacion.getRegimenAdquirente())
                .put("telefonoAdquiriente", modeloFacturacion.getTelefonoAdquiriente())
                .put("ciudadAdquiriente", new JSONObject()
                        .put("cdDane", modeloFacturacion.getCdDaneCiudad())
                        .put("dsNombre", modeloFacturacion.getDsNombreCiudad())
                        .put("departamento", new JSONObject()
                                .put("cdDane", modeloFacturacion.getCdDaneDepartamento())
                                .put("dsNombre", modeloFacturacion.getDsNombreDepartamento())
                                .put("pais", new JSONObject()
                                        .put("cdIso", modeloFacturacion.getCdIsoPais())
                                        .put("dsNombre", modeloFacturacion.getDsNombrePais()))
                        )
                )
                .put("snDistribucionFisica", modeloFacturacion.getSnDistribucionFisica())
                .put("valorNeto", modeloFacturacion.getValorNeto())
                .put("dsObservacion", modeloFacturacion.getDsObservacion())
                .put("tipoDocumentoElectronico", modeloFacturacion.getTipoDocumentoElectronico())
                .put("dsNumeroReferencia", modeloFacturacion.getDsNumeroReferencia())
                .put("dsPrefijoReferencia", modeloFacturacion.getDsPrefijoReferencia())
                .put("porcentajeIva", modeloFacturacion.getPorcentajeIva())
                .put("porcentajeConsumo", modeloFacturacion.getPorcentajeConsumo())
                .put("dsPorcentajeReteFuente", modeloFacturacion.getDsPorcentajeReteFuente())
                .put("dsRetencionFuente", modeloFacturacion.getDsRetencionFuente())
                .put("dsPorcentajeReteIva", modeloFacturacion.getDsPorcentajeReteIva())
                .put("dsRetencionIva", modeloFacturacion.getDsRetencionIva())
                .put("dsPorcentajeDescuento", modeloFacturacion.getDsPorcentajeDescuento())
                .put("dsDescuento", modeloFacturacion.getDsDescuento())
                .put("valorBaseImponible", modeloFacturacion.getValorBaseImponible())
                .put("valorBrutoMasTributos", modeloFacturacion.getValorBrutoMasTributos())
                .put("descuentoTotal", modeloFacturacion.getDescuentoTotal())
                .put("cargoTotal", modeloFacturacion.getCargoTotal())
                .put("anticipoTotal", modeloFacturacion.getAnticipoTotal())
                .put("valorTotalImpuestoNacionalConsumo", modeloFacturacion.getValorTotalImpuestoConsumo())
                .put("moneda", modeloFacturacion.getMoneda())
                .put("valorBruto", modeloFacturacion.getValorBruto())
                .put("valorIva", modeloFacturacion.getValorIva())
                .put("tipoOperacion", modeloFacturacion.getTipoOperacion())
                .put("cdTipoPlantilla", modeloFacturacion.getCdTipoPlantilla())
                .put("dsResolucionDian", modeloFacturacion.getDsResolucionDian())
                .put("versionDian", modeloFacturacion.getVersionDian())
                .put("responsabilidadesFiscales", modeloFacturacion.getResponsabilidadesFiscales())
                .put("pago", new JSONObject()
                        .put("formaPago", modeloFacturacion.getFormaPago())
                        .put("medioPago", modeloFacturacion.getMedioPago())
                        .put("fechaVencimientoPago", modeloFacturacion.getFechaVencimientoPago())
                        .put("idPago", modeloFacturacion.getIdPago())
                )
                .put("cargosDescuentos", listadoDescuentos)
                .put("impuestosRetenciones", listadoImpuestosFactura)
                .put("detalles", listadoDetalleProductos);

        if (esNotaDebito) {
            jsonFacturacionElectronica.put("facturasReferencia", new JSONArray().put(
                    new JSONObject()
                            .put("prefijo", modeloFacturacion.getPrefijoFacturaReferencia())
                            .put("numero", modeloFacturacion.getNumeroFacturaReferencia())
                            .put("conceptoNotaDebito", modeloFacturacion.getConceptoNotaDebito())
                            .put("descripcion", modeloFacturacion.getDescripcionNotaDebito())));
        }

        if (esNotaDebitoPos) {
            jsonFacturacionElectronica.put("notasCreditoReferencia", new JSONArray().put(
                    new JSONObject()
                            .put("prefijo", modeloFacturacion.getPrefijoFacturaReferencia())
                            .put("numero", modeloFacturacion.getNumeroFacturaReferencia())
                            .put("conceptoNotaDebito", modeloFacturacion.getConceptoNotaDebito())
                            .put("descripcion", modeloFacturacion.getDescripcionNotaDebito())));
        }

        if (esNotaCredito) {
            jsonFacturacionElectronica.put("facturasReferencia", new JSONArray().put(
                    new JSONObject()
                            .put("prefijo", modeloFacturacion.getPrefijoFacturaReferencia())
                            .put("numero", modeloFacturacion.getNumeroFacturaReferencia())
                            .put("cufe", modeloFacturacion.getCufe())
                            .put("conceptoNotaCredito", modeloFacturacion.getConceptoNotaCredito())
                            .put("descripcion", modeloFacturacion.getDescripcionNotaCredito())));
        }

        if (esFacturacionPos) {
            StringBuilder nombreCompletoBuilder = new StringBuilder();
            nombreCompletoBuilder.append(modeloFacturacion.getNombresAdquiriente()).append(" ");
            if (modeloFacturacion.getSegundoNombre() != null && !modeloFacturacion.getSegundoNombre().isEmpty()) {
                nombreCompletoBuilder.append(modeloFacturacion.getSegundoNombre()).append(" ");
            }

            nombreCompletoBuilder.append(modeloFacturacion.getPrimerApellido()).append(" ");
            if (modeloFacturacion.getSegundoApellido() != null && !modeloFacturacion.getSegundoApellido().isEmpty()) {
                nombreCompletoBuilder.append(modeloFacturacion.getSegundoApellido()).append(" ");
            }

            String nombreCompleto = nombreCompletoBuilder.toString();

            jsonFacturacionElectronica
                    .put("beneficiosComprador", new JSONObject()
                            .put("codigo", modeloFacturacion.getIdentificacionAdquiriente())
                            .put("nombresApellidos", nombreCompleto)
                            .put("puntos", "0"))
                    .put("informacionCajaVenta", new JSONObject()
                            .put("placaCaja", modeloFacturacion.getPlacaCaja())
                            .put("ubicacionCaja", modeloFacturacion.getUbicacionCaja())
                            .put("cajero", modeloFacturacion.getCajero())
                            .put("tipoCaja", modeloFacturacion.getTipoCaja())
                            .put("codigoVenta", modeloFacturacion.getDsPrefijo() + "-" + modeloFacturacion.getDsNumeroFactura())
                            .put("subTotal", String.valueOf(modeloFacturacion.getValorBruto()))
                    );
        }

        return jsonFacturacionElectronica;
    }

    private JSONArray crearArrayDetalleProductos(ModeloFacturacionElectronica modeloFacturacion) throws JSONException {

        JSONArray listadoDetalleProductos = new JSONArray();
        for (ModeloDetalleProductos datosProducto : modeloFacturacion.getDetalleProductos()) {

            JSONArray listadoImpuestosProducto = detalleImpuestos.crearArrayImpuestosProducto(datosProducto.getImpuestosProducto());
            JSONArray listaDescuento = descuentos.crearArrayDescuentoProducto(datosProducto.getDescuentoProducto());

            JSONObject registroIndividual = new JSONObject()
                    .put("numeroFactura", datosProducto.getNumeroFactura())
                    .put("codigoArticulo", datosProducto.getCodigoArticulo())
                    .put("estandarProducto", datosProducto.getEstandarProducto())
                    .put("descripcionArticulo", datosProducto.getDescripcionArticulo())
                    .put("notaAdicional", datosProducto.getObservacionDetalle())
                    .put("porcentajeIva", datosProducto.getPorcentajeIva())
                    .put("porcentajeConsumo", datosProducto.getPorcentajeConsumo())
                    .put("cantidad", datosProducto.getCantidad())
                    .put("precioUnitario", datosProducto.getPrecioUnitario())
                    .put("valorTotalArticulo", datosProducto.getValorTotalArticulo())
                    .put("valorIva", datosProducto.getValorIva())
                    .put("unidadMedida", datosProducto.getUnidadMedida())
                    .put("valorTotalBruto", datosProducto.getValorTotalBruto())
                    .put("unidadesEmpaque", datosProducto.getUnidadesEmpaque())
                    .put("valorTotalImpuestosRetenciones", datosProducto.getValorTotalImpuestosRetenciones())
                    .put("codigoVendedor", datosProducto.getCodigoVendedor())
                    .put("cargosDescuentos", listaDescuento)
                    .put("impuestosRetenciones", listadoImpuestosProducto);

            listadoDetalleProductos.put(registroIndividual);
        }

        return listadoDetalleProductos;
    }
}
