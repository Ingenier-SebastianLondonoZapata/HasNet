package Vista.Reportes.Filtros;

import Modelo.Reportes.OpcionFiltro;
import Modelo.Reportes.SeleccionReporte;
import java.util.Collections;
import java.util.List;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;
import javax.swing.JComponent;

public abstract class FiltroReporte {

    private final String id;
    private final String etiqueta;
    private BooleanSupplier visible = () -> true;
    private Supplier<List<OpcionFiltro>> valoresSiOculto = Collections::emptyList;

    protected FiltroReporte(String id, String etiqueta) {
        this.id = id;
        this.etiqueta = etiqueta;
    }

    public String getId() {
        return id;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public FiltroReporte visibleSi(BooleanSupplier condicion) {
        this.visible = condicion;
        return this;
    }

    public FiltroReporte siOculto(Supplier<List<OpcionFiltro>> valores) {
        this.valoresSiOculto = valores;
        return this;
    }

    public boolean esVisible() {
        return visible.getAsBoolean();
    }

    public abstract JComponent crearComponente();

    public String validar() {
        return null;
    }

    public void aplicar(SeleccionReporte seleccion) {
        if (esVisible()) {
            aplicarVisible(seleccion);
        } else {
            seleccion.agregar(id, etiqueta, valoresSiOculto.get());
        }
    }

    protected abstract void aplicarVisible(SeleccionReporte seleccion);
}
