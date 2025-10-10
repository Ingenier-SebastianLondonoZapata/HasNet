package clases.Ventas;

public class ndGarantia {

    private boolean anulada, credito;
    private String efectivo, nc, cheque, targeta, total, descuentos, iva, subtotal, rtIva, rtIca, rtFuente, otros, devuelta; // NUMEROS 
    private String id, cliente, vendedor, red, fechaFactura, fechaVencimiento, comprobante, cotizacion, anula, cxc, usuario,
            observaciones, fechaAlerta, terminal, estado, factura, resolucion, modeloContable;
    private String copago, placa, garantia, diasGarantia, rango, terminos, conseMesa, notaAnulacion;

    public String getModeloContable() {
        return modeloContable;
    }

    public void setModeloContable(String modeloContable) {
        this.modeloContable = modeloContable;
    }

    public String getNotaAnulacion() {
        return notaAnulacion;
    }

    public void setNotaAnulacion(String notaAnulacion) {
        this.notaAnulacion = notaAnulacion;
    }

    public String getConseMesa() {
        return conseMesa;
    }

    public void setConseMesa(String conseMesa) {
        this.conseMesa = conseMesa;
    }

    public String getGarantia() {
        return garantia;
    }

    public void setGarantia(String garantia) {
        this.garantia = garantia;
    }

    public String getDiasGarantia() {
        return diasGarantia;
    }

    public void setDiasGarantia(String diasGarantia) {
        this.diasGarantia = diasGarantia;
    }

    public String getRango() {
        return rango;
    }

    public void setRango(String rango) {
        this.rango = rango;
    }

    public String getTerminos() {
        return terminos;
    }

    public void setTerminos(String terminos) {
        this.terminos = terminos;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getCopago() {
        return copago;
    }

    public void setCopago(String copago) {
        this.copago = copago;
    }

    public String getResolucion() {
        return resolucion;
    }

    public void setResolucion(String resolucion) {
        this.resolucion = resolucion;
    }

    public String getFactura() {
        return factura;
    }

    public void setFactura(String factura) {
        this.factura = factura;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDevuelta() {
        return devuelta;
    }

    public void setDevuelta(String devuelta) {
        this.devuelta = devuelta;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public String getFechaAlerta() {
        return fechaAlerta;
    }

    public void setFechaAlerta(String fechaAlerta) {
        this.fechaAlerta = fechaAlerta;
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

    public String getRtIca() {
        return rtIca;
    }

    public void setRtIca(String rtIca) {
        this.rtIca = rtIca;
    }

    public String getRtFuente() {
        return rtFuente;
    }

    public void setRtFuente(String rtFuente) {
        this.rtFuente = rtFuente;
    }

    public String getOtros() {
        return otros;
    }

    public void setOtros(String otros) {
        this.otros = otros;
    }

    public String getCxc() {
        return cxc;
    }

    public void setCxc(String cxc) {
        this.cxc = cxc;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
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

    public String getComprobante() {
        return comprobante;
    }

    public void setComprobante(String comprobante) {
        this.comprobante = comprobante;
    }

    public String getCotizacion() {
        return cotizacion;
    }

    public void setCotizacion(String cotizacion) {
        this.cotizacion = cotizacion;
    }

    public String getAnula() {
        return anula;
    }

    public void setAnula(String anula) {
        this.anula = anula;
    }

    public boolean isAnulada() {
        return anulada;
    }

    public void setAnulada(boolean anulada) {
        this.anulada = anulada;
    }

    public boolean isCredito() {
        return credito;
    }

    public void setCredito(boolean credito) {
        this.credito = credito;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEfectivo() {
        return efectivo;
    }

    public void setEfectivo(String efectivo) {
        this.efectivo = efectivo;
    }

    public String getNc() {
        return nc;
    }

    public void setNc(String nc) {
        this.nc = nc;
    }

    public String getCheque() {
        return cheque;
    }

    public void setCheque(String cheque) {
        this.cheque = cheque;
    }

    public String getTargeta() {
        return targeta;
    }

    public void setTargeta(String targeta) {
        this.targeta = targeta;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public String getRed() {
        return red;
    }

    public void setRed(String red) {
        this.red = red;
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

    public void setFechaVencimiento(String fechaVencimientol) {
        this.fechaVencimiento = fechaVencimientol;
    }

}
