package Vista.Reportes.Filtros;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

public class WrapLayout extends FlowLayout {

    public WrapLayout(int alineacion, int separacionH, int separacionV) {
        super(alineacion, separacionH, separacionV);
    }

    @Override
    public Dimension preferredLayoutSize(Container contenedor) {
        return medir(contenedor, true);
    }

    @Override
    public Dimension minimumLayoutSize(Container contenedor) {
        Dimension minimo = medir(contenedor, false);
        minimo.width -= (getHgap() + 1);
        return minimo;
    }

    private Dimension medir(Container contenedor, boolean preferido) {
        synchronized (contenedor.getTreeLock()) {
            int anchoDestino = contenedor.getSize().width;
            Container padre = contenedor;
            while (padre.getSize().width == 0 && padre.getParent() != null) {
                padre = padre.getParent();
            }
            if (anchoDestino == 0) {
                anchoDestino = padre.getSize().width;
            }
            if (anchoDestino == 0) {
                anchoDestino = Integer.MAX_VALUE;
            }

            Insets margenes = contenedor.getInsets();
            int anchoMaximo = anchoDestino - (margenes.left + margenes.right + getHgap() * 2);
            Dimension total = new Dimension(0, 0);
            int anchoFila = 0;
            int altoFila = 0;

            for (Component componente : contenedor.getComponents()) {
                if (!componente.isVisible()) {
                    continue;
                }
                Dimension medida = preferido ? componente.getPreferredSize() : componente.getMinimumSize();
                if (anchoFila + medida.width > anchoMaximo && anchoFila > 0) {
                    agregarFila(total, anchoFila, altoFila);
                    anchoFila = 0;
                    altoFila = 0;
                }
                if (anchoFila != 0) {
                    anchoFila += getHgap();
                }
                anchoFila += medida.width;
                altoFila = Math.max(altoFila, medida.height);
            }
            agregarFila(total, anchoFila, altoFila);

            total.width += margenes.left + margenes.right + getHgap() * 2;
            total.height += margenes.top + margenes.bottom + getVgap() * 2;

            Container scroll = SwingUtilities.getAncestorOfClass(JScrollPane.class, contenedor);
            if (scroll != null && contenedor.isValid()) {
                total.width -= (getHgap() + 1);
            }
            return total;
        }
    }

    private void agregarFila(Dimension total, int anchoFila, int altoFila) {
        total.width = Math.max(total.width, anchoFila);
        if (total.height > 0) {
            total.height += getVgap();
        }
        total.height += altoFila;
    }
}
