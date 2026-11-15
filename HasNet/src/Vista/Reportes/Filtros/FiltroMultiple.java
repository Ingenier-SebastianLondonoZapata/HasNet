package Vista.Reportes.Filtros;

import Controlador.Alertas.ControladorAlertas;
import Modelo.Reportes.OpcionFiltro;
import Modelo.Reportes.SeleccionReporte;
import Vista.Reportes.EstiloReporte;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

public abstract class FiltroMultiple extends FiltroReporte {

    private final List<OpcionFiltro> seleccionados = new ArrayList<>();
    private JPanel chips;
    private boolean requerido;

    protected FiltroMultiple(String id, String etiqueta) {
        super(id, etiqueta);
    }

    public FiltroMultiple requerido() {
        this.requerido = true;
        return this;
    }

    protected abstract JComponent crearEntrada();

    @Override
    public JComponent crearComponente() {
        JPanel fila = new JPanel(new GridBagLayout());
        fila.setOpaque(false);

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.NORTHWEST;
        c.weightx = 1;
        JComponent entrada = crearEntrada();
        entrada.setMinimumSize(entrada.getPreferredSize());
        fila.add(entrada, c);

        chips = new JPanel(new WrapLayout(FlowLayout.LEFT, 4, 4));
        chips.setOpaque(false);
        c.gridy = 1;
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(0, 0, 0, 0);
        fila.add(chips, c);

        refrescarChips();
        return fila;
    }

    protected void agregar(OpcionFiltro opcion) {
        if (opcion == null || opcion.getValor() == null || opcion.getValor().trim().isEmpty()) {
            return;
        }
        if (seleccionados.contains(opcion)) {
            ControladorAlertas.alert("Ya agregó " + opcion.getEtiqueta() + " en " + getEtiqueta().toLowerCase());
            return;
        }
        seleccionados.add(opcion);
        refrescarChips();
    }

    protected void agregarVarios(List<OpcionFiltro> opciones) {
        boolean cambio = false;
        for (OpcionFiltro opcion : opciones) {
            if (opcion != null && opcion.getValor() != null && !opcion.getValor().trim().isEmpty() && !seleccionados.contains(opcion)) {
                seleccionados.add(opcion);
                cambio = true;
            }
        }
        if (cambio) {
            refrescarChips();
        }
    }

    private void quitar(OpcionFiltro opcion) {
        seleccionados.remove(opcion);
        refrescarChips();
    }

    private void refrescarChips() {
        if (chips == null) {
            return;
        }
        chips.removeAll();
        for (OpcionFiltro opcion : seleccionados) {
            chips.add(crearChip(opcion));
        }
        chips.setVisible(!seleccionados.isEmpty());
        chips.revalidate();
        chips.repaint();
    }

    private JButton crearChip(OpcionFiltro opcion) {
        JButton chip = new JButton(opcion.getEtiqueta() + "  ×");
        chip.setFont(EstiloReporte.CHIP);
        chip.setForeground(EstiloReporte.CHIP_TEXTO);
        chip.setBackground(EstiloReporte.CHIP_FONDO);
        chip.setFocusPainted(false);
        chip.setToolTipText(opcion.getValor() + " - clic para quitar");
        chip.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        chip.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(EstiloReporte.CHIP_FONDO.darker()),
                BorderFactory.createEmptyBorder(3, 8, 3, 8)));
        chip.addActionListener(e -> quitar(opcion));
        return chip;
    }

    @Override
    public String validar() {
        if (requerido && esVisible() && seleccionados.isEmpty()) {
            return "Agregue al menos un valor en " + getEtiqueta().toLowerCase();
        }
        return null;
    }

    @Override
    protected void aplicarVisible(SeleccionReporte seleccion) {
        seleccion.agregar(getId(), getEtiqueta(), seleccionados);
    }
}
