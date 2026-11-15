package Modelo.Reportes;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SeleccionReporte {

    private final Map<String, List<OpcionFiltro>> valores = new LinkedHashMap<>();
    private final Map<String, String> etiquetasFiltros = new LinkedHashMap<>();
    private final Map<String, BigDecimal[]> rangos = new LinkedHashMap<>();
    private String fechaInicial;
    private String fechaFinal;
    private boolean excel;

    public void agregar(String filtro, String etiquetaFiltro, List<OpcionFiltro> opciones) {
        etiquetasFiltros.put(filtro, etiquetaFiltro);
        valores.put(filtro, new ArrayList<>(opciones));
    }

    public void agregarRango(String filtro, String etiquetaFiltro, BigDecimal desde, BigDecimal hasta) {
        etiquetasFiltros.put(filtro, etiquetaFiltro);
        rangos.put(filtro, new BigDecimal[]{desde, hasta});
    }

    public void setFechas(String inicial, String finalInclusive) {
        this.fechaInicial = inicial;
        this.fechaFinal = finalInclusive;
    }

    public void setExcel(boolean excel) {
        this.excel = excel;
    }

    public List<String> valores(String filtro) {
        List<String> resultado = new ArrayList<>();
        for (OpcionFiltro opcion : opciones(filtro)) {
            resultado.add(opcion.getValor());
        }
        return resultado;
    }

    public List<OpcionFiltro> opciones(String filtro) {
        List<OpcionFiltro> lista = valores.get(filtro);
        return lista == null ? Collections.<OpcionFiltro>emptyList() : lista;
    }

    public boolean tiene(String filtro) {
        return !opciones(filtro).isEmpty();
    }

    public boolean contiene(String filtro, String valor) {
        return valores(filtro).contains(valor);
    }

    public String valor(String filtro) {
        List<String> lista = valores(filtro);
        return lista.isEmpty() ? "" : lista.get(0);
    }

    public boolean tieneRango(String filtro) {
        return rangos.containsKey(filtro);
    }

    public BigDecimal rangoDesde(String filtro) {
        return tieneRango(filtro) ? rangos.get(filtro)[0] : null;
    }

    public BigDecimal rangoHasta(String filtro) {
        return tieneRango(filtro) ? rangos.get(filtro)[1] : null;
    }

    public boolean hayFechas() {
        return fechaInicial != null && fechaFinal != null;
    }

    public String getFechaInicial() {
        return fechaInicial;
    }

    public String getFechaFinal() {
        return fechaFinal;
    }

    public boolean isExcel() {
        return excel;
    }

    public String encabezadoFechas(String sujeto) {
        if (!hayFechas()) {
            return sujeto;
        }
        if (fechaInicial.equals(fechaFinal)) {
            return sujeto + " del " + fechaInicial;
        }
        return sujeto + " entre el " + fechaInicial + " y el " + fechaFinal;
    }

    public String resumenFiltros(String... excluidos) {
        List<String> partes = new ArrayList<>();
        List<String> omitir = java.util.Arrays.asList(excluidos);
        for (Map.Entry<String, List<OpcionFiltro>> entrada : valores.entrySet()) {
            if (omitir.contains(entrada.getKey()) || entrada.getValue().isEmpty()) {
                continue;
            }
            List<String> etiquetas = new ArrayList<>();
            for (OpcionFiltro opcion : entrada.getValue()) {
                etiquetas.add(opcion.getEtiqueta());
            }
            partes.add(etiquetasFiltros.get(entrada.getKey()) + ": " + String.join(", ", etiquetas));
        }
        return String.join(" | ", partes);
    }
}
