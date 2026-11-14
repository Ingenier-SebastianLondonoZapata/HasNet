package Vista.Buscadores;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import javax.swing.table.DefaultTableModel;

public class ConfiguracionBuscadorCatalogo {

    public static final int COLUMNA_OCULTA = 0;

    private final String titulo;
    private final Supplier<DefaultTableModel> cargador;
    private final Map<Integer, Integer> anchosFijos = new LinkedHashMap<>();
    private final List<FiltroColumna> filtros = new ArrayList<>();
    private int columnaFiltro;
    private Integer columnaNoEncontrado;
    private int columnaValor;
    private boolean presionarEnterAlSeleccionar = true;
    private RegistroCatalogo registro;
    private Function<String, String> registroExterno;

    public ConfiguracionBuscadorCatalogo(String titulo, Supplier<DefaultTableModel> cargador) {
        this.titulo = titulo;
        this.cargador = cargador;
    }

    public ConfiguracionBuscadorCatalogo anchoFijo(int columna, int ancho) {
        anchosFijos.put(columna, ancho);
        return this;
    }

    public ConfiguracionBuscadorCatalogo ocultarColumna(int columna) {
        return anchoFijo(columna, COLUMNA_OCULTA);
    }

    public ConfiguracionBuscadorCatalogo filtro(String etiqueta, int columna) {
        if (filtros.isEmpty()) {
            columnaFiltro = columna;
        }
        filtros.add(new FiltroColumna(etiqueta, columna));
        return this;
    }

    public ConfiguracionBuscadorCatalogo columnaFiltro(int columna) {
        this.columnaFiltro = columna;
        return this;
    }

    public ConfiguracionBuscadorCatalogo columnaNoEncontrado(int columna) {
        this.columnaNoEncontrado = columna;
        return this;
    }

    public ConfiguracionBuscadorCatalogo columnaValor(int columna) {
        this.columnaValor = columna;
        return this;
    }

    public ConfiguracionBuscadorCatalogo presionarEnterAlSeleccionar(boolean presionar) {
        this.presionarEnterAlSeleccionar = presionar;
        return this;
    }

    public ConfiguracionBuscadorCatalogo registro(RegistroCatalogo registro) {
        this.registro = registro;
        return this;
    }

    public ConfiguracionBuscadorCatalogo registroExterno(Function<String, String> registroExterno) {
        this.registroExterno = registroExterno;
        return this;
    }

    public String getTitulo() {
        return titulo;
    }

    public DefaultTableModel cargarRegistros() {
        return cargador.get();
    }

    public Map<Integer, Integer> getAnchosFijos() {
        return Collections.unmodifiableMap(anchosFijos);
    }

    public List<FiltroColumna> getFiltros() {
        return Collections.unmodifiableList(filtros);
    }

    public int getColumnaFiltro() {
        return columnaFiltro;
    }

    public Integer getColumnaNoEncontrado() {
        return columnaNoEncontrado;
    }

    public int getColumnaValor() {
        return columnaValor;
    }

    public boolean isPresionarEnterAlSeleccionar() {
        return presionarEnterAlSeleccionar;
    }

    public RegistroCatalogo getRegistro() {
        return registro;
    }

    public boolean permiteRegistrar() {
        return registro != null || registroExterno != null;
    }

    public boolean tieneRegistroExterno() {
        return registroExterno != null;
    }

    public String registrarExterno(String texto) {
        return registroExterno.apply(texto);
    }

    public static class FiltroColumna {

        private final String etiqueta;
        private final int columna;

        public FiltroColumna(String etiqueta, int columna) {
            this.etiqueta = etiqueta;
            this.columna = columna;
        }

        public String getEtiqueta() {
            return etiqueta;
        }

        public int getColumna() {
            return columna;
        }
    }
}
