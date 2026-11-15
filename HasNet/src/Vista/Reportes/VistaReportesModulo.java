package Vista.Reportes;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Scrollable;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicInternalFrameUI;

public class VistaReportesModulo extends JInternalFrame {

    private final JComboBox<Object> cmbReportes = EstiloReporte.combo();
    private final PanelDesplazable contenedor = new PanelDesplazable();

    public VistaReportesModulo(String titulo, List<EntradaReporte> entradas) {
        setTitle(titulo);
        ocultarBarraTitulo();
        construir(entradas);
        pack();
    }

    private void ocultarBarraTitulo() {
        JComponent barra = ((BasicInternalFrameUI) getUI()).getNorthPane();
        if (barra != null) {
            barra.setSize(0, 0);
            barra.setPreferredSize(new Dimension(0, 0));
        }
        setBorder(null);
    }

    private void construir(List<EntradaReporte> entradas) {
        JPanel raiz = new JPanel(new BorderLayout());
        raiz.setBackground(EstiloReporte.FONDO);

        JPanel selector = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 10));
        selector.setBackground(EstiloReporte.FONDO);
        selector.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, EstiloReporte.BORDE));
        selector.add(EstiloReporte.etiqueta("Reporte:"));

        cmbReportes.setFont(EstiloReporte.TITULO);
        cmbReportes.setPreferredSize(new Dimension(380, 34));
        cmbReportes.addItem("Seleccione un reporte");
        for (EntradaReporte entrada : entradas) {
            if (entrada.tienePermiso()) {
                cmbReportes.addItem(entrada);
            }
        }
        cmbReportes.addActionListener(e -> mostrarSeleccionado());
        selector.add(cmbReportes);
        raiz.add(selector, BorderLayout.NORTH);

        contenedor.setBackground(EstiloReporte.FONDO_PAGINA);
        JScrollPane desplazamiento = new JScrollPane(contenedor);
        desplazamiento.getViewport().setBackground(EstiloReporte.FONDO_PAGINA);
        desplazamiento.getHorizontalScrollBar().setUnitIncrement(16);
        desplazamiento.setBorder(null);
        desplazamiento.getVerticalScrollBar().setUnitIncrement(16);
        raiz.add(desplazamiento, BorderLayout.CENTER);

        setContentPane(raiz);
    }

    private void mostrarSeleccionado() {
        contenedor.removeAll();
        Object seleccionado = cmbReportes.getSelectedItem();
        if (seleccionado instanceof EntradaReporte) {
            contenedor.add(((EntradaReporte) seleccionado).crearPanel(), BorderLayout.CENTER);
        }
        contenedor.revalidate();
        contenedor.repaint();
    }

    private static class PanelDesplazable extends JPanel implements Scrollable {

        PanelDesplazable() {
            super(new BorderLayout());
        }

        @Override
        public Dimension getPreferredScrollableViewportSize() {
            return getPreferredSize();
        }

        @Override
        public int getScrollableUnitIncrement(Rectangle visible, int orientacion, int direccion) {
            return 16;
        }

        @Override
        public int getScrollableBlockIncrement(Rectangle visible, int orientacion, int direccion) {
            return orientacion == SwingConstants.VERTICAL ? visible.height : visible.width;
        }

        @Override
        public boolean getScrollableTracksViewportWidth() {
            return getParent() != null && getParent().getWidth() >= getMinimumSize().width;
        }

        @Override
        public boolean getScrollableTracksViewportHeight() {
            return getParent() != null && getParent().getHeight() > getPreferredSize().height;
        }
    }
}
