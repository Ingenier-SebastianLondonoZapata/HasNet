package Vista.Reportes.Filtros;

import Modelo.Reportes.OpcionFiltro;
import Modelo.Reportes.SeleccionReporte;
import Utilidades.Utilidades;
import Vista.Reportes.EstiloReporte;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.math.BigDecimal;
import java.util.Arrays;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FiltroRango extends FiltroReporte {

    private final boolean horas;
    private JCheckBox activo;
    private JTextField desde;
    private JTextField hasta;
    private JComboBox<String> horaDesde;
    private JComboBox<String> horaHasta;

    private FiltroRango(String id, String etiqueta, boolean horas) {
        super(id, etiqueta);
        this.horas = horas;
    }

    public static FiltroRango numerico(String id, String etiqueta) {
        return new FiltroRango(id, etiqueta, false);
    }

    public static FiltroRango horas(String id, String etiqueta) {
        return new FiltroRango(id, etiqueta, true);
    }

    @Override
    public JComponent crearComponente() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 4, 0));
        panel.setOpaque(false);

        activo = EstiloReporte.casilla("Filtrar");
        panel.add(activo);
        panel.add(EstiloReporte.nota("desde"));
        if (horas) {
            horaDesde = comboHoras("00:00");
            horaHasta = comboHoras("23:59");
            panel.add(horaDesde);
            panel.add(EstiloReporte.nota("hasta"));
            panel.add(horaHasta);
        } else {
            desde = campoCorto();
            hasta = campoCorto();
            panel.add(desde);
            panel.add(EstiloReporte.nota("hasta"));
            panel.add(hasta);
        }
        return panel;
    }

    private static JTextField campoCorto() {
        JTextField campo = EstiloReporte.campo();
        campo.setPreferredSize(new Dimension(100, EstiloReporte.ALTO_CAMPO));
        return campo;
    }

    private static JComboBox<String> comboHoras(String seleccionada) {
        JComboBox<String> combo = EstiloReporte.combo();
        combo.setPreferredSize(new Dimension(90, EstiloReporte.ALTO_CAMPO));
        for (int hora = 0; hora < 24; hora++) {
            combo.addItem(String.format("%02d:00", hora));
        }
        combo.addItem("23:59");
        combo.setSelectedItem(seleccionada);
        return combo;
    }

    @Override
    public String validar() {
        if (horas || !esVisible() || !activo.isSelected()) {
            return null;
        }
        if (numero(desde) == null || numero(hasta) == null) {
            return "Ingrese el rango completo en " + getEtiqueta().toLowerCase();
        }
        return null;
    }

    private static BigDecimal numero(JTextField campo) {
        String texto = campo.getText().trim();
        if (texto.isEmpty()) {
            return null;
        }
        try {
            return Utilidades.convertirBigDecimal(texto);
        } catch (RuntimeException ex) {
            return null;
        }
    }

    @Override
    protected void aplicarVisible(SeleccionReporte seleccion) {
        if (!activo.isSelected()) {
            return;
        }
        if (horas) {
            seleccion.agregar(getId(), getEtiqueta(), Arrays.asList(
                    OpcionFiltro.de(horaDesde.getSelectedItem() + ":00"),
                    OpcionFiltro.de(horaHasta.getSelectedItem() + ":59")));
        } else {
            seleccion.agregarRango(getId(), getEtiqueta(), numero(desde), numero(hasta));
        }
    }
}
