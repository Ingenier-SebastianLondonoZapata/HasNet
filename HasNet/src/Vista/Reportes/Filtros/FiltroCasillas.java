package Vista.Reportes.Filtros;

import Modelo.Reportes.OpcionFiltro;
import Modelo.Reportes.SeleccionReporte;
import Vista.Reportes.EstiloReporte;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class FiltroCasillas extends FiltroReporte {

    private final List<OpcionFiltro> opciones;
    private final List<JCheckBox> casillas = new ArrayList<>();
    private boolean marcadasPorDefecto;
    private boolean requerido;

    public FiltroCasillas(String id, String etiqueta, OpcionFiltro... opciones) {
        super(id, etiqueta);
        this.opciones = Arrays.asList(opciones);
    }

    public FiltroCasillas todasMarcadas() {
        this.marcadasPorDefecto = true;
        return this;
    }

    public FiltroCasillas requerido() {
        this.requerido = true;
        return this;
    }

    @Override
    public JComponent crearComponente() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 4, 0));
        panel.setOpaque(false);
        casillas.clear();
        for (OpcionFiltro opcion : opciones) {
            JCheckBox casilla = EstiloReporte.casilla(opcion.getEtiqueta());
            casilla.setSelected(marcadasPorDefecto);
            casillas.add(casilla);
            panel.add(casilla);
        }
        return panel;
    }

    private List<OpcionFiltro> marcadas() {
        List<OpcionFiltro> resultado = new ArrayList<>();
        for (int i = 0; i < casillas.size(); i++) {
            if (casillas.get(i).isSelected()) {
                resultado.add(opciones.get(i));
            }
        }
        return resultado;
    }

    @Override
    public String validar() {
        if (requerido && esVisible() && marcadas().isEmpty()) {
            return "Seleccione al menos una opción en " + getEtiqueta().toLowerCase();
        }
        return null;
    }

    @Override
    protected void aplicarVisible(SeleccionReporte seleccion) {
        seleccion.agregar(getId(), getEtiqueta(), marcadas());
    }
}
