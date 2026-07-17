package Vista.Ventas;

import Enums.TipoDocumento;
import Modelo.Ventas.DocumentoSeleccionado;
import clases.Instancias;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;

public class ServicioFacturacionMasiva {

    private final Instancias instancias;

    public ServicioFacturacionMasiva(Instancias instancias) {
        this.instancias = instancias;
    }

    public void procesarIndividual(List<DocumentoSeleccionado> documentos, String tipoCombo,
            int indexComprobante, boolean imprimir) {

        VistaFactura vistaFactura = obtenerVistaFacturaPorTipo(tipoCombo);
        if (vistaFactura == null) {
            JOptionPane.showMessageDialog(null, "Tipo de documento no soportado para conversión: " + tipoCombo,
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String tipoDocumento = obtenerDocumentoPorTipo(tipoCombo);
        List<String> errores = new ArrayList<>();
        int exitosos = 0;

        for (DocumentoSeleccionado doc : documentos) {
            try {
                boolean cargado = vistaFactura.cargarDocumentoParaConversionAFactura(tipoDocumento, doc.getIdDocumento());
                if (!cargado) {
                    errores.add(doc.getIdDocumento() + ": no se pudo cargar el documento.");
                    continue;
                }
                vistaFactura.seleccionarComprobanteParaConversion(indexComprobante);
                vistaFactura.setSaltarPasosFactura(true);
                vistaFactura.ejecutarConversionAFactura(imprimir, doc.getIdDocumento());
                exitosos++;
            } catch (Exception e) {
                errores.add(doc.getIdDocumento() + ": " + e.getMessage());
            }
        }

        mostrarResumen(exitosos, errores);
    }

    public void procesarUnificado(List<DocumentoSeleccionado> documentos, String tipoCombo,
            int indexComprobante, boolean imprimir) {

        VistaFactura vistaFactura = obtenerVistaFacturaPorTipo(tipoCombo);
        if (vistaFactura == null) {
            JOptionPane.showMessageDialog(null, "Tipo de documento no soportado para conversión: " + tipoCombo,
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Map<String, List<DocumentoSeleccionado>> grupos = agruparPorCliente(documentos);

        List<String> errores = new ArrayList<>();
        int exitosos = 0;
        String tipoDocumento = obtenerDocumentoPorTipo(tipoCombo);

        for (Map.Entry<String, List<DocumentoSeleccionado>> entrada : grupos.entrySet()) {
            List<DocumentoSeleccionado> grupo = entrada.getValue();
            DocumentoSeleccionado principal = grupo.get(0);

            try {
                boolean cargado = vistaFactura.cargarDocumentoParaConversionAFactura(tipoDocumento, principal.getIdDocumento());
                if (!cargado) {
                    errores.add(principal.getIdDocumento() + ": no se pudo cargar el documento.");
                    continue;
                }               

                for (int i = 1; i < grupo.size(); i++) {
                    boolean adicionalCargado = vistaFactura.agregarDocumentoParaUnificacion(tipoDocumento, grupo.get(i).getIdDocumento());
                    if (!adicionalCargado) {
                        errores.add(grupo.get(i).getIdDocumento() + ": no se pudo agregar al lote unificado.");
                    }
                }

                vistaFactura.seleccionarComprobanteParaConversion(indexComprobante);
                vistaFactura.setSaltarPasosFactura(true);
                vistaFactura.ejecutarConversionAFactura(imprimir, "");

                for (int i = 0; i < grupo.size(); i++) {
                    vistaFactura.marcarDocumentoOrigenComoConvertido(tipoDocumento, grupo.get(i).getIdDocumento());
                }

                exitosos++;
            } catch (Exception e) {
                errores.add(principal.getIdDocumento() + ": " + e.getMessage());
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

    private VistaFactura obtenerVistaFacturaPorTipo(String tipoCombo) {
        switch (tipoCombo) {
            case "PEDIDOS":
                return instancias.getPedido();
            case "COTIZACIÓN":
                return instancias.getCotiza();
            case "ORDEN DE SERVICIO":
                return instancias.getOrdenServicio();
            default:
                return null;
        }
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
