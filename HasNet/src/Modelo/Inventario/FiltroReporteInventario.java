package Modelo.Inventario;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Criterios escogidos en la pantalla de reportes de inventario. La vista solo llena este filtro y el
 * DAO es el que arma la consulta.
 */
public class FiltroReporteInventario {

    public static final String ESTADO_SIN_INVENTARIO = "SIN INVENTARIO";
    public static final String ESTADO_BAJO = "BAJO";
    public static final String ESTADO_MEDIO = "MEDIO";
    public static final String ESTADO_ALTO = "ALTO";

    private String bodega;
    private final List<String> estados = new ArrayList<>();
    private final List<String> proveedores = new ArrayList<>();
    private final List<String> productos = new ArrayList<>();
    private final List<String> grupos = new ArrayList<>();
    private final List<String> imeis = new ArrayList<>();
    private final List<String> colores = new ArrayList<>();
    private BigDecimal inventarioDesde;
    private BigDecimal inventarioHasta;
    private String fechaDesde;
    private String fechaHasta;

    public String getBodega() {
        return bodega;
    }

    public void setBodega(String bodega) {
        this.bodega = bodega;
    }

    public List<String> getEstados() {
        return estados;
    }

    public void agregarEstado(String estado) {
        estados.add(estado);
    }

    public List<String> getProveedores() {
        return proveedores;
    }

    public List<String> getProductos() {
        return productos;
    }

    public List<String> getGrupos() {
        return grupos;
    }

    public List<String> getImeis() {
        return imeis;
    }

    public List<String> getColores() {
        return colores;
    }

    public BigDecimal getInventarioDesde() {
        return inventarioDesde;
    }

    public BigDecimal getInventarioHasta() {
        return inventarioHasta;
    }

    public void setRangoInventario(BigDecimal desde, BigDecimal hasta) {
        this.inventarioDesde = desde;
        this.inventarioHasta = hasta;
    }

    public boolean tieneRangoInventario() {
        return inventarioDesde != null && inventarioHasta != null;
    }

    public String getFechaDesde() {
        return fechaDesde;
    }

    public String getFechaHasta() {
        return fechaHasta;
    }

    public void setRangoFechas(String desde, String hasta) {
        this.fechaDesde = desde;
        this.fechaHasta = hasta;
    }

    public boolean tieneRangoFechas() {
        return !estaVacio(fechaDesde) && !estaVacio(fechaHasta);
    }

    private static boolean estaVacio(String valor) {
        return valor == null || valor.trim().isEmpty();
    }
}
