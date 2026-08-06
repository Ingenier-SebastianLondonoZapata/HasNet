package Modelo.Productos;

import java.math.BigDecimal;

public class ResultadoPluProducto {

    private final BigDecimal cantidadPlu;
    private final BigDecimal cantidadFisicoInventarioActual;
    private final BigDecimal listaPrecio;
    private final String codigoLista;
    private final String descripcion;
    private final String listaPrecioTexto;

    public ResultadoPluProducto(BigDecimal cantidadPlu, BigDecimal cantidadFisicoInventarioActual, BigDecimal listaPrecio, String codigoLista, String descripcion, String listaPrecioTexto) {
        this.cantidadPlu = cantidadPlu;
        this.cantidadFisicoInventarioActual = cantidadFisicoInventarioActual;
        this.listaPrecio = listaPrecio;
        this.codigoLista = codigoLista;
        this.descripcion = descripcion;
        this.listaPrecioTexto = listaPrecioTexto;
    }

    public BigDecimal getCantidadPlu() {
        return cantidadPlu;
    }

    public BigDecimal getCantidadFisicoInventarioActual() {
        return cantidadFisicoInventarioActual;
    }

    public BigDecimal getListaPrecio() {
        return listaPrecio;
    }

    public String getCodigoLista() {
        return codigoLista;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getListaPrecioTexto() {
        return listaPrecioTexto;
    }

}
