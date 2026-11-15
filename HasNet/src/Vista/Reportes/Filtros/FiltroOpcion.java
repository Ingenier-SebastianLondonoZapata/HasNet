package Vista.Reportes.Filtros;

import Modelo.Reportes.OpcionFiltro;
import Modelo.Reportes.SeleccionReporte;
import Vista.Reportes.EstiloReporte;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class FiltroOpcion extends FiltroReporte {

    private static final int MAXIMO_RADIOS = 4;

    private final Supplier<List<OpcionFiltro>> opciones;
    private int indicePorDefecto;
    private final List<JRadioButton> radios = new ArrayList<>();
    private JComboBox<OpcionFiltro> combo;
    private List<OpcionFiltro> cargadas = Collections.emptyList();

    public FiltroOpcion(String id, String etiqueta, Supplier<List<OpcionFiltro>> opciones) {
        super(id, etiqueta);
        this.opciones = opciones;
    }

    public static FiltroOpcion de(String id, String etiqueta, OpcionFiltro... opciones) {
        List<OpcionFiltro> lista = Arrays.asList(opciones);
        return new FiltroOpcion(id, etiqueta, () -> lista);
    }

    public FiltroOpcion porDefecto(int indice) {
        this.indicePorDefecto = indice;
        return this;
    }

    @Override
    public JComponent crearComponente() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 4, 0));
        panel.setOpaque(false);
        cargadas = opciones.get();
        radios.clear();
        combo = null;

        if (cargadas.size() > MAXIMO_RADIOS) {
            combo = EstiloReporte.combo();
            for (OpcionFiltro opcion : cargadas) {
                combo.addItem(opcion);
            }
            if (indicePorDefecto < cargadas.size()) {
                combo.setSelectedIndex(indicePorDefecto);
            }
            panel.add(combo);
            return panel;
        }

        ButtonGroup grupo = new ButtonGroup();
        for (int i = 0; i < cargadas.size(); i++) {
            JRadioButton radio = EstiloReporte.radio(cargadas.get(i).getEtiqueta());
            radio.setSelected(i == indicePorDefecto);
            grupo.add(radio);
            radios.add(radio);
            panel.add(radio);
        }
        return panel;
    }

    private OpcionFiltro seleccionada() {
        if (combo != null) {
            return (OpcionFiltro) combo.getSelectedItem();
        }
        for (int i = 0; i < radios.size(); i++) {
            if (radios.get(i).isSelected()) {
                return cargadas.get(i);
            }
        }
        return null;
    }

    @Override
    protected void aplicarVisible(SeleccionReporte seleccion) {
        OpcionFiltro opcion = seleccionada();
        seleccion.agregar(getId(), getEtiqueta(), opcion == null ? Collections.<OpcionFiltro>emptyList() : Collections.singletonList(opcion));
    }
}
