package Vista.Aterrizaje.Menu;

import java.beans.PropertyVetoException;
import java.util.function.Supplier;
import javax.swing.JInternalFrame;

public class OpcionMenu {

    @FunctionalInterface
    public interface AccionMenu {

        void ejecutar() throws PropertyVetoException;
    }

    private static final AccionMenu SIN_ACCION = () -> {
    };

    private final String texto;
    private final Supplier<? extends JInternalFrame> formulario;
    private String titulo;
    private AccionMenu antesDeAbrir = SIN_ACCION;
    private AccionMenu despuesDeAbrir = SIN_ACCION;

    private OpcionMenu(String texto, Supplier<? extends JInternalFrame> formulario) {
        this.texto = texto;
        this.titulo = texto;
        this.formulario = formulario;
    }

    public static OpcionMenu de(String texto, Supplier<? extends JInternalFrame> formulario) {
        return new OpcionMenu(texto, formulario);
    }

    public OpcionMenu titulo(String titulo) {
        this.titulo = titulo;
        return this;
    }

    public OpcionMenu antesDeAbrir(AccionMenu accion) {
        this.antesDeAbrir = accion;
        return this;
    }

    public OpcionMenu despuesDeAbrir(AccionMenu accion) {
        this.despuesDeAbrir = accion;
        return this;
    }

    public String getTexto() {
        return texto;
    }

    public String getTitulo() {
        return titulo;
    }

    public JInternalFrame getFormulario() {
        return formulario.get();
    }

    public boolean tienePermiso() {
        JInternalFrame frame = getFormulario();
        return frame != null && frame.isVisible();
    }

    public AccionMenu getAntesDeAbrir() {
        return antesDeAbrir;
    }

    public AccionMenu getDespuesDeAbrir() {
        return despuesDeAbrir;
    }
}
