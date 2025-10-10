package clases.productos;

public class ndIngreso {

    private String id, proveedor, fechaFactura, hora, fechaVencimiento, cxp, usuario, tipo, concepto, terminal, observaciones, fechaCargue, estado, modeloContable;
    private String total, descuentos, iva, subtotal, rtIva, rtFuente, porRtFuente, impoconsumo, ica, efectivo, cheque, tarjeta, descFinanciero,
            descProntoPago, bodega; // NUMEROS 
    private boolean credito;

    public ndIngreso() {

    }

    public String getModeloContable() {
        return modeloContable;
    }

    public void setModeloContable(String modeloContable) {
        this.modeloContable = modeloContable;
    }

    public String getBodega() {
        return bodega;
    }

    public void setBodega(String bodega) {
        this.bodega = bodega;
    }

    public String getIca() {
        return ica;
    }

    public void setIca(String ica) {
        this.ica = ica;
    }

    public String getEfectivo() {
        return efectivo;
    }

    public void setEfectivo(String efectivo) {
        this.efectivo = efectivo;
    }

    public String getCheque() {
        return cheque;
    }

    public void setCheque(String cheque) {
        this.cheque = cheque;
    }

    public String getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(String tarjeta) {
        this.tarjeta = tarjeta;
    }

    public String getDescFinanciero() {
        return descFinanciero;
    }

    public void setDescFinanciero(String descFinanciero) {
        this.descFinanciero = descFinanciero;
    }

    public String getDescProntoPago() {
        return descProntoPago;
    }

    public void setDescProntoPago(String descProntoPago) {
        this.descProntoPago = descProntoPago;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getImpoconsumo() {
        return impoconsumo;
    }

    public void setImpoconsumo(String impoconsumo) {
        this.impoconsumo = impoconsumo;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getFechaCargue() {
        return fechaCargue;
    }

    public void setFechaCargue(String fechaCargue) {
        this.fechaCargue = fechaCargue;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getRtIva() {
        return rtIva;
    }

    public void setRtIva(String rtIva) {
        this.rtIva = rtIva;
    }

    public String getRtFuente() {
        return rtFuente;
    }

    public void setRtFuente(String rtFuente) {
        this.rtFuente = rtFuente;
    }

    public String getPorRtFuente() {
        return porRtFuente;
    }

    public void setPorRtFuente(String porRtFuente) {
        this.porRtFuente = porRtFuente;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getFechaFactura() {
        return fechaFactura;
    }

    public void setFechaFactura(String fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getCxp() {
        return cxp;
    }

    public void setCxp(String cxp) {
        this.cxp = cxp;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getDescuentos() {
        return descuentos;
    }

    public void setDescuentos(String descuentos) {
        this.descuentos = descuentos;
    }

    public String getIva() {
        return iva;
    }

    public void setIva(String iva) {
        this.iva = iva;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }

    public boolean isCredito() {
        return credito;
    }

    public void setCredito(boolean credito) {
        this.credito = credito;
    }

}
