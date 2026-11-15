package Vista.Reportes.Filtros;

import Modelo.Reportes.SeleccionReporte;
import Vista.Reportes.EstiloReporte;
import clases.metodosGenerales;
import datechooser.beans.DateChooserCombo;
import java.awt.FlowLayout;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class FiltroFechas extends FiltroReporte {

    public static final String ID = "rangoFechas";

    private final boolean obligatorio;
    private final metodosGenerales metodos = new metodosGenerales();
    private JCheckBox activo;
    private DateChooserCombo inicial;
    private DateChooserCombo fin;

    private FiltroFechas(String etiqueta, boolean obligatorio) {
        super(ID, etiqueta);
        this.obligatorio = obligatorio;
    }

    public static FiltroFechas rango() {
        return new FiltroFechas("Rango de fechas", false);
    }

    public static FiltroFechas rango(String etiqueta) {
        return new FiltroFechas(etiqueta, false);
    }

    public static FiltroFechas obligatorio() {
        return new FiltroFechas("Rango de fechas", true);
    }

    @Override
    public JComponent crearComponente() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 4, 0));
        panel.setOpaque(false);
        activo = EstiloReporte.casilla("Filtrar");
        activo.setSelected(true);
        activo.setEnabled(!obligatorio);
        if (obligatorio) {
            activo.setToolTipText("Este reporte siempre se genera por rango de fechas");
        }
        panel.add(activo);
        inicial = selector();
        fin = selector();
        panel.add(EstiloReporte.nota("Desde"));
        panel.add(inicial);
        panel.add(EstiloReporte.nota("Hasta"));
        panel.add(fin);
        panel.setMinimumSize(panel.getPreferredSize());
        return panel;
    }

    private static DateChooserCombo selector() {
        DateChooserCombo selector = new DateChooserCombo();
        selector.setDateFormat(new SimpleDateFormat("dd/MM/yyyy"));
        selector.setFieldFont(EstiloReporte.CAMPO);
        selector.setSelectedDate(Calendar.getInstance());
        return selector;
    }

    private boolean estaActivo() {
        return obligatorio || activo.isSelected();
    }

    private String fechaInicial() {
        return metodos.desdeDate(inicial.getCurrent());
    }

    private String fechaFinal() {
        return metodos.desdeDate(fin.getCurrent());
    }

    @Override
    public String validar() {
        if (esVisible() && estaActivo() && fechaInicial().compareTo(fechaFinal()) > 0) {
            return "La fecha inicial no puede ser mayor que la final";
        }
        return null;
    }

    @Override
    protected void aplicarVisible(SeleccionReporte seleccion) {
        if (estaActivo()) {
            seleccion.setFechas(fechaInicial(), fechaFinal());
        }
    }
}
