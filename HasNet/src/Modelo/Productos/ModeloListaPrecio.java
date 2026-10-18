package Modelo.Productos;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ModeloListaPrecio {

    public static final int LISTA_PRINCIPAL = 1;

    public static final int MAXIMO_LISTA = 8;

    private int numeroLista;
    private BigDecimal precio = BigDecimal.ZERO;
    private String codigo;
    private String descripcion;
    private String ubicacion;
    private BigDecimal cantidad = BigDecimal.ONE;
    private boolean plu;

    public ModeloListaPrecio() {
    }

    public ModeloListaPrecio(int numeroLista) {
        this.numeroLista = numeroLista;
    }

    public static ModeloListaPrecio conPrecio(int numeroLista, BigDecimal precio) {
        ModeloListaPrecio lista = new ModeloListaPrecio(numeroLista);
        lista.setPrecio(precio);
        return lista;
    }

    public int getNumeroLista() {
        return numeroLista;
    }

    public void setNumeroLista(int numeroLista) {
        this.numeroLista = numeroLista;
    }

    public String getNombre() {
        return "L" + numeroLista;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio == null ? BigDecimal.ZERO : precio;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = (cantidad == null || cantidad.signum() <= 0) ? BigDecimal.ONE : cantidad;
    }

    public boolean isPlu() {
        return plu;
    }

    public void setPlu(boolean plu) {
        this.plu = plu;
    }

    public boolean esVacia() {
        return precio.signum() == 0 && !plu && esBlanco(codigo) && esBlanco(descripcion) && esBlanco(ubicacion);
    }

    public BigDecimal getUtilidad(BigDecimal costo) {
        return precio.subtract(costo);
    }

    public BigDecimal getPorcentajeUtilidad(BigDecimal costo) {
        if (costo == null || costo.signum() == 0) {
            return BigDecimal.ZERO;
        }

        return getUtilidad(costo).divide(costo, 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100"));
    }

    private static boolean esBlanco(String valor) {
        return valor == null || valor.trim().isEmpty();
    }
}
