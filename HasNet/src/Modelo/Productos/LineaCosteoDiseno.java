package Modelo.Productos;

import java.math.BigDecimal;

public class LineaCosteoDiseno {

    private final String producto;
    private final BigDecimal cantidad;
    private final String codigo;
    private final String usuario;
    private final String descripcion;
    private final String plu;
    private final BigDecimal cantidad2;
    private final boolean opcionCambio;
    private final String tipo;

    public LineaCosteoDiseno(String producto, BigDecimal cantidad, String codigo, String usuario,
            String descripcion, String plu, BigDecimal cantidad2, boolean opcionCambio, String tipo) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.codigo = codigo;
        this.usuario = usuario;
        this.descripcion = descripcion;
        this.plu = plu;
        this.cantidad2 = cantidad2;
        this.opcionCambio = opcionCambio;
        this.tipo = tipo;
    }

    public String getProducto() {
        return producto;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getPlu() {
        return plu;
    }

    public BigDecimal getCantidad2() {
        return cantidad2;
    }

    public boolean isOpcionCambio() {
        return opcionCambio;
    }

    public String getTipo() {
        return tipo;
    }
}
