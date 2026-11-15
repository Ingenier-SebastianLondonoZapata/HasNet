package Vista.Reportes;

import java.util.function.BooleanSupplier;
import java.util.function.Supplier;
import javax.swing.JComponent;

public class EntradaReporte {

    private final String titulo;
    private final BooleanSupplier permiso;
    private final Supplier<JComponent> panel;

    private EntradaReporte(String titulo, BooleanSupplier permiso, Supplier<JComponent> panel) {
        this.titulo = titulo;
        this.permiso = permiso;
        this.panel = panel;
    }

    public static EntradaReporte de(BooleanSupplier permiso, Supplier<DefinicionReporte> definicion) {
        DefinicionReporte muestra = definicion.get();
        return new EntradaReporte(muestra.getTitulo(), permiso, () -> new VistaReporteDinamico(definicion.get()));
    }

    public static EntradaReporte panel(String titulo, BooleanSupplier permiso, Supplier<JComponent> panel) {
        return new EntradaReporte(titulo, permiso, panel);
    }

    public String getTitulo() {
        return titulo;
    }

    public boolean tienePermiso() {
        return permiso.getAsBoolean();
    }

    public JComponent crearPanel() {
        return panel.get();
    }

    @Override
    public String toString() {
        return titulo;
    }
}
