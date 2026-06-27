package Modelo.Ventas;

public class LineaProducto {

    private String codigo;
    private String descripcion;
    private String precio;        // campo "lista" en BD
    private int plu;
    private String cantidad;      // resuelta: cantidad si plu==1, cant2 si no
    private String porcDescuento;
    private String descuento;
    private String rango;         // null cuando no aplica
    private String imei;          // "" cuando no aplica
    private String idProd;        // "" cuando no aplica
    private String preparacion;   // campo "estado" en BD, "" cuando no aplica

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getPrecio() { return precio; }
    public void setPrecio(String precio) { this.precio = precio; }

    public int getPlu() { return plu; }
    public void setPlu(int plu) { this.plu = plu; }

    public String getCantidad() { return cantidad; }
    public void setCantidad(String cantidad) { this.cantidad = cantidad; }

    public String getPorcDescuento() { return porcDescuento; }
    public void setPorcDescuento(String porcDescuento) { this.porcDescuento = porcDescuento; }

    public String getDescuento() { return descuento; }
    public void setDescuento(String descuento) { this.descuento = descuento; }

    public String getRango() { return rango; }
    public void setRango(String rango) { this.rango = rango; }

    public String getImei() { return imei; }
    public void setImei(String imei) { this.imei = imei; }

    public String getIdProd() { return idProd; }
    public void setIdProd(String idProd) { this.idProd = idProd; }

    public String getPreparacion() { return preparacion; }
    public void setPreparacion(String preparacion) { this.preparacion = preparacion; }
}
