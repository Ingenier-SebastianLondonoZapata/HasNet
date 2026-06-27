package Modelo.Ventas;

public class CabeceraDocumento {

    private String clienteId;
    private String vendedor;
    private String subtotal;
    private String totalDescuentos;
    private String totalIva;
    private String total;
    private String observacion;
    private String bodega;
    private String estadoGeneral;
    private String placaReal;     // solo ORDER_SERVICIO, null en otros tipos

    public String getClienteId() { return clienteId; }
    public void setClienteId(String clienteId) { this.clienteId = clienteId; }

    public String getVendedor() { return vendedor; }
    public void setVendedor(String vendedor) { this.vendedor = vendedor; }

    public String getSubtotal() { return subtotal; }
    public void setSubtotal(String subtotal) { this.subtotal = subtotal; }

    public String getTotalDescuentos() { return totalDescuentos; }
    public void setTotalDescuentos(String totalDescuentos) { this.totalDescuentos = totalDescuentos; }

    public String getTotalIva() { return totalIva; }
    public void setTotalIva(String totalIva) { this.totalIva = totalIva; }

    public String getTotal() { return total; }
    public void setTotal(String total) { this.total = total; }

    public String getObservacion() { return observacion; }
    public void setObservacion(String observacion) { this.observacion = observacion; }

    public String getBodega() { return bodega; }
    public void setBodega(String bodega) { this.bodega = bodega; }

    public String getEstadoGeneral() { return estadoGeneral; }
    public void setEstadoGeneral(String estadoGeneral) { this.estadoGeneral = estadoGeneral; }

    public String getPlacaReal() { return placaReal; }
    public void setPlacaReal(String placaReal) { this.placaReal = placaReal; }
}
