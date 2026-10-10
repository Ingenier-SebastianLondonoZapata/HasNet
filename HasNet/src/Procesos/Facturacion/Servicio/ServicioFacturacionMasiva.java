package Procesos.Facturacion.Servicio;

import Enums.TipoDocumento;
import Modelo.FacturacionMasiva.DocumentoSeleccionado;
import Modelo.FacturacionMasiva.ResultadoFacturacion;
import clases.Instancias;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;

public class ServicioFacturacionMasiva {

    private final ServicioFacturacionDocumentos servicioDocumentos;

    public ServicioFacturacionMasiva(Instancias instancias) {
        this.servicioDocumentos = new ServicioFacturacionDocumentos(instancias);
    }

    public void procesarIndividual(List<DocumentoSeleccionado> documentos, String tipoCombo,
            int indexComprobante, boolean imprimir) {

        String tipoDocumento = obtenerDocumentoPorTipo(tipoCombo);
        if (tipoDocumento == null) {
            mostrarTipoNoSoportado(tipoCombo);
            return;
        }

        List<String> errores = new ArrayList<>();
        int exitosos = 0;

        for (DocumentoSeleccionado doc : documentos) {
            ResultadoFacturacion resultado = servicioDocumentos.facturar(tipoDocumento, doc.getIdDocumento(),
                    indexComprobante, imprimir);
            errores.addAll(resultado.getErrores());
            if (resultado.isFacturaGenerada()) {
                exitosos++;
            }
        }

        mostrarResumen(exitosos, errores);
    }

    public void procesarUnificado(List<DocumentoSeleccionado> documentos, String tipoCombo,
            int indexComprobante, boolean imprimir) {

        String tipoDocumento = obtenerDocumentoPorTipo(tipoCombo);
        if (tipoDocumento == null) {
            mostrarTipoNoSoportado(tipoCombo);
            return;
        }

        List<String> errores = new ArrayList<>();
        int exitosos = 0;

        for (List<DocumentoSeleccionado> grupo : agruparPorCliente(documentos).values()) {
            List<String> ids = new ArrayList<>();
            for (DocumentoSeleccionado doc : grupo) {
                ids.add(doc.getIdDocumento());
            }

            ResultadoFacturacion resultado = servicioDocumentos.facturarUnificado(tipoDocumento, ids,
                    indexComprobante, imprimir);
            errores.addAll(resultado.getErrores());
            if (resultado.isFacturaGenerada()) {
                exitosos++;
            }
        }

        mostrarResumen(exitosos, errores);
    }

    private Map<String, List<DocumentoSeleccionado>> agruparPorCliente(List<DocumentoSeleccionado> documentos) {
        Map<String, List<DocumentoSeleccionado>> grupos = new LinkedHashMap<>();
        for (DocumentoSeleccionado doc : documentos) {
            String clave = doc.getNitCliente() != null ? doc.getNitCliente() : "";
            if (!grupos.containsKey(clave)) {
                grupos.put(clave, new ArrayList<DocumentoSeleccionado>());
            }
            grupos.get(clave).add(doc);
        }
        return grupos;
    }

    private String obtenerDocumentoPorTipo(String tipoCombo) {
        switch (tipoCombo) {
            case "PEDIDOS":
                return TipoDocumento.PEDIDO.getValor();
            case "COTIZACIÓN":
                return TipoDocumento.COTIZACION.getValor();
            case "ORDEN DE SERVICIO":
                return TipoDocumento.ORDER_SERVICIO.getValor();
            default:
                return null;
        }
    }

    private void mostrarTipoNoSoportado(String tipoCombo) {
        JOptionPane.showMessageDialog(null, "Tipo de documento no soportado para conversión: " + tipoCombo,
                "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void mostrarResumen(int exitosos, List<String> errores) {
        if (errores.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Proceso completado. Facturas generadas: " + exitosos,
                    "Facturación completada", JOptionPane.INFORMATION_MESSAGE);
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Facturas generadas: ").append(exitosos).append("\n");
            sb.append("Errores (").append(errores.size()).append("):\n");
            for (String error : errores) {
                sb.append("  - ").append(error).append("\n");
            }
            JOptionPane.showMessageDialog(null, sb.toString(),
                    "Facturación con errores", JOptionPane.WARNING_MESSAGE);
        }
    }
}
