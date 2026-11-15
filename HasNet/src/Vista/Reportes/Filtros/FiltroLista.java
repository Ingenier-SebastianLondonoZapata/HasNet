package Vista.Reportes.Filtros;

import Modelo.Reportes.OpcionFiltro;
import Vista.Reportes.EstiloReporte;
import java.awt.FlowLayout;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class FiltroLista extends FiltroMultiple {

    private final Supplier<List<OpcionFiltro>> opciones;

    public FiltroLista(String id, String etiqueta, Supplier<List<OpcionFiltro>> opciones) {
        super(id, etiqueta);
        this.opciones = opciones;
    }

    public static FiltroLista de(String id, String etiqueta, OpcionFiltro... opciones) {
        List<OpcionFiltro> lista = Arrays.asList(opciones);
        return new FiltroLista(id, etiqueta, () -> lista);
    }

    public static FiltroLista deValores(String id, String etiqueta, String... valores) {
        OpcionFiltro[] opciones = new OpcionFiltro[valores.length];
        for (int i = 0; i < valores.length; i++) {
            opciones[i] = OpcionFiltro.de(valores[i]);
        }
        return de(id, etiqueta, opciones);
    }

    @Override
    protected JComponent crearEntrada() {
        JPanel entrada = new JPanel(new FlowLayout(FlowLayout.LEFT, 4, 0));
        entrada.setOpaque(false);

        JComboBox<OpcionFiltro> combo = EstiloReporte.combo();
        for (OpcionFiltro opcion : opciones.get()) {
            combo.addItem(opcion);
        }
        entrada.add(combo);

        JButton agregar = EstiloReporte.botonAgregar();
        agregar.addActionListener(e -> agregar((OpcionFiltro) combo.getSelectedItem()));
        entrada.add(agregar);
        return entrada;
    }
}
