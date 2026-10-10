package Procesos.Facturacion.Vista;

import Procesos.Facturacion.Servicio.ServicioFacturacionDocumentos;

import Modelo.Maestra.ModeloResolucion;
import java.awt.Component;
import java.awt.Frame;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class SelectorComprobanteFacturacion {

    private SelectorComprobanteFacturacion() {
    }

    public static int solicitar(Component padre, ServicioFacturacionDocumentos servicio, List<String> nitsClientes) {
        List<ModeloResolucion> resoluciones = servicio.obtenerComprobantes();

        if (resoluciones.isEmpty()) {
            JOptionPane.showMessageDialog(padre,
                    "No hay comprobantes de facturación configurados.",
                    "Sin comprobantes", JOptionPane.ERROR_MESSAGE);
            return -1;
        }

        String[] opciones = new String[resoluciones.size()];
        for (int i = 0; i < resoluciones.size(); i++) {
            String desc = resoluciones.get(i).getDescripcionResolucion();
            opciones[i] = desc != null ? desc : "Comprobante " + (i + 1);
        }

        JComboBox<String> comboComprobantes = new JComboBox<>(opciones);
        Frame frame = padre != null ? (Frame) SwingUtilities.getWindowAncestor(padre) : null;
        int resultado = JOptionPane.showConfirmDialog(frame, comboComprobantes,
                "Seleccione el tipo de comprobante", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        if (resultado != JOptionPane.OK_OPTION) {
            return -1;
        }

        int indexSeleccionado = comboComprobantes.getSelectedIndex();

        String error = servicio.validarComprobanteParaClientes(indexSeleccionado, nitsClientes);
        if (error != null) {
            JOptionPane.showMessageDialog(padre, error, "Facturación electrónica no permitida", JOptionPane.WARNING_MESSAGE);
            return -1;
        }

        return indexSeleccionado;
    }
}
