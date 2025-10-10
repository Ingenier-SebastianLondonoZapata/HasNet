package clases.productos;

public class ndIngresoEx {

    private String id, proveedor, fechaFactura, fechaVencimiento, tipo, concepto, cxp, usuario, terminal, observaciones, fechaCargue,
            cantProd, cantCajas, cantUnidades, estado;
    private String subtotal, descuentos, total, flete, seguro, otros; // NUMEROS 
    private boolean credito;

    public ndIngresoEx() {
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getFechaCargue() {
        return fechaCargue;
    }

    public void setFechaCargue(String fechaCargue) {
        this.fechaCargue = fechaCargue;
    }

    public String getCantProd() {
        return cantProd;
    }

    public void setCantProd(String cantProd) {
        this.cantProd = cantProd;
    }

    public String getCantCajas() {
        return cantCajas;
    }

    public void setCantCajas(String cantCajas) {
        this.cantCajas = cantCajas;
    }

    public String getCantUnidades() {
        return cantUnidades;
    }

    public void setCantUnidades(String cantUnidades) {
        this.cantUnidades = cantUnidades;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }

    public String getDescuentos() {
        return descuentos;
    }

    public void setDescuentos(String descuentos) {
        this.descuentos = descuentos;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getFlete() {
        return flete;
    }

    public void setFlete(String flete) {
        this.flete = flete;
    }

    public String getSeguro() {
        return seguro;
    }

    public void setSeguro(String seguro) {
        this.seguro = seguro;
    }

    public String getOtros() {
        return otros;
    }

    public void setOtros(String otros) {
        this.otros = otros;
    }

    public boolean isCredito() {
        return credito;
    }

    public void setCredito(boolean credito) {
        this.credito = credito;
    }

}
