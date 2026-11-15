package Vista.Reportes;

import Modelo.Reportes.SeleccionReporte;
import Vista.Reportes.Filtros.FiltroFechas;
import Vista.Reportes.Filtros.FiltroReporte;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class DefinicionReporte {

    private final String titulo;
    private final List<FiltroReporte> filtros = new ArrayList<>();
    private final List<AccionReporte> acciones = new ArrayList<>();
    private FiltroFechas fechas = FiltroFechas.rango();
    private String nota = "";

    public DefinicionReporte(String titulo) {
        this.titulo = titulo;
    }

    public DefinicionReporte filtro(FiltroReporte filtro) {
        filtros.add(filtro);
        return this;
    }

    public DefinicionReporte accion(String texto, String nombreArchivo, Consumer<SeleccionReporte> ejecutar) {
        acciones.add(new AccionReporte(texto, nombreArchivo, ejecutar));
        return this;
    }

    public DefinicionReporte accionSi(boolean disponible, String texto, String nombreArchivo, Consumer<SeleccionReporte> ejecutar) {
        return disponible ? accion(texto, nombreArchivo, ejecutar) : this;
    }

    public DefinicionReporte sinFechas() {
        this.fechas = null;
        return this;
    }

    public DefinicionReporte etiquetaFechas(String etiqueta) {
        this.fechas = FiltroFechas.rango(etiqueta);
        return this;
    }

    public DefinicionReporte fechasObligatorias() {
        this.fechas = FiltroFechas.obligatorio();
        return this;
    }

    public DefinicionReporte nota(String nota) {
        this.nota = nota;
        return this;
    }

    public String getTitulo() {
        return titulo;
    }

    public List<FiltroReporte> getFiltros() {
        List<FiltroReporte> todos = new ArrayList<>();
        if (fechas != null) {
            todos.add(fechas);
        }
        todos.addAll(filtros);
        return Collections.unmodifiableList(todos);
    }

    public List<AccionReporte> getAcciones() {
        return Collections.unmodifiableList(acciones);
    }

    public String getNota() {
        return nota;
    }
}
