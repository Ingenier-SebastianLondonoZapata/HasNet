package Modelo.Documentos;

import Enums.TipoDocumento;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ModeloDocumento {

    private int idDocumento;
    private TipoDocumento tipoDocumento;
    private String factura = "";
    private String cliente = "";
    private String vendedor = "";
    private String red = "";
    private String fechaFactura;
    private String horaFactura;
    private String fechaVencimiento;
    private String fechaAlerta;
    private BigDecimal efectivo = BigDecimal.ZERO;
    private BigDecimal notaCredito = BigDecimal.ZERO;
    private BigDecimal cheque = BigDecimal.ZERO;
    private BigDecimal tarjetaDebito = BigDecimal.ZERO;
    private BigDecimal total = BigDecimal.ZERO;
    private BigDecimal descuento = BigDecimal.ZERO;
    private BigDecimal iva = BigDecimal.ZERO;
    private BigDecimal subtotal = BigDecimal.ZERO;
    private BigDecimal impoconsumo = BigDecimal.ZERO;
    private BigDecimal devuelta = BigDecimal.ZERO;
    private BigDecimal reteIva = BigDecimal.ZERO;
    private BigDecimal reteIca = BigDecimal.ZERO;
    private BigDecimal reteFuente = BigDecimal.ZERO;
    private BigDecimal porcentajeReteFuente = BigDecimal.ZERO;
    private int copago;
    private String comprobante = "";
    private String consecutivo = "";
    private boolean credito;
    private String cxc = "";
    private String usuario = "";
    private String observacion = "";
    private String terminal = "";
    private String estadoGeneral = "";
    private String estado2 = "";
    private String resolucion = "";
    private boolean anulada;
    private String anula = "";
    private String fechaAnulacion;
    private String cuadreAnulacion = "";
    private String usuarioAnula = "";
    private String notaAnulacion = "";
    private String placa = "";
    private String garantia = "";
    private String diasGarantia = "";
    private String terminos = "";
    private String conseMesa = "";
    private String turno = "";
    private String bodega = "";
    private final List<ModeloDetalleDocumento> detalles = new ArrayList<>();

    public ModeloDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public void agregarDetalle(ModeloDetalleDocumento detalle) {
        detalle.setLinea(detalles.size() + 1);
        detalles.add(detalle);
    }

    public List<ModeloDetalleDocumento> getDetalles() {
        return detalles;
    }

    public int getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(int idDocumento) {
        this.idDocumento = idDocumento;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getFactura() {
        return factura;
    }

    public void setFactura(String factura) {
        this.factura = factura;
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

    public String getHoraFactura() {
        return horaFactura;
    }

    public void setHoraFactura(String horaFactura) {
        this.horaFactura = horaFactura;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getFechaAlerta() {
        return fechaAlerta;
    }

    public void setFechaAlerta(String fechaAlerta) {
        this.fechaAlerta = fechaAlerta;
    }

    public BigDecimal getEfectivo() {
        return efectivo;
    }

    public void setEfectivo(BigDecimal efectivo) {
        this.efectivo = efectivo;
    }

    public BigDecimal getNotaCredito() {
        return notaCredito;
    }

    public void setNotaCredito(BigDecimal notaCredito) {
        this.notaCredito = notaCredito;
    }

    public BigDecimal getCheque() {
        return cheque;
    }

    public void setCheque(BigDecimal cheque) {
        this.cheque = cheque;
    }

    public BigDecimal getTarjetaDebito() {
        return tarjetaDebito;
    }

    public void setTarjetaDebito(BigDecimal tarjetaDebito) {
        this.tarjetaDebito = tarjetaDebito;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getDescuento() {
        return descuento;
    }

    public void setDescuento(BigDecimal descuento) {
        this.descuento = descuento;
    }

    public BigDecimal getIva() {
        return iva;
    }

    public void setIva(BigDecimal iva) {
        this.iva = iva;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getImpoconsumo() {
        return impoconsumo;
    }

    public void setImpoconsumo(BigDecimal impoconsumo) {
        this.impoconsumo = impoconsumo;
    }

    public BigDecimal getDevuelta() {
        return devuelta;
    }

    public void setDevuelta(BigDecimal devuelta) {
        this.devuelta = devuelta;
    }

    public BigDecimal getReteIva() {
        return reteIva;
    }

    public void setReteIva(BigDecimal reteIva) {
        this.reteIva = reteIva;
    }

    public BigDecimal getReteIca() {
        return reteIca;
    }

    public void setReteIca(BigDecimal reteIca) {
        this.reteIca = reteIca;
    }

    public BigDecimal getReteFuente() {
        return reteFuente;
    }

    public void setReteFuente(BigDecimal reteFuente) {
        this.reteFuente = reteFuente;
    }

    public BigDecimal getPorcentajeReteFuente() {
        return porcentajeReteFuente;
    }

    public void setPorcentajeReteFuente(BigDecimal porcentajeReteFuente) {
        this.porcentajeReteFuente = porcentajeReteFuente;
    }

    public int getCopago() {
        return copago;
    }

    public void setCopago(int copago) {
        this.copago = copago;
    }

    public String getComprobante() {
        return comprobante;
    }

    public void setComprobante(String comprobante) {
        this.comprobante = comprobante;
    }

    public String getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(String consecutivo) {
        this.consecutivo = consecutivo;
    }

    public boolean isCredito() {
        return credito;
    }

    public void setCredito(boolean credito) {
        this.credito = credito;
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

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public String getEstadoGeneral() {
        return estadoGeneral;
    }

    public void setEstadoGeneral(String estadoGeneral) {
        this.estadoGeneral = estadoGeneral;
    }

    public String getEstado2() {
        return estado2;
    }

    public void setEstado2(String estado2) {
        this.estado2 = estado2;
    }

    public String getResolucion() {
        return resolucion;
    }

    public void setResolucion(String resolucion) {
        this.resolucion = resolucion;
    }

    public boolean isAnulada() {
        return anulada;
    }

    public void setAnulada(boolean anulada) {
        this.anulada = anulada;
    }

    public String getAnula() {
        return anula;
    }

    public void setAnula(String anula) {
        this.anula = anula;
    }

    public String getFechaAnulacion() {
        return fechaAnulacion;
    }

    public void setFechaAnulacion(String fechaAnulacion) {
        this.fechaAnulacion = fechaAnulacion;
    }

    public String getCuadreAnulacion() {
        return cuadreAnulacion;
    }

    public void setCuadreAnulacion(String cuadreAnulacion) {
        this.cuadreAnulacion = cuadreAnulacion;
    }

    public String getUsuarioAnula() {
        return usuarioAnula;
    }

    public void setUsuarioAnula(String usuarioAnula) {
        this.usuarioAnula = usuarioAnula;
    }

    public String getNotaAnulacion() {
        return notaAnulacion;
    }

    public void setNotaAnulacion(String notaAnulacion) {
        this.notaAnulacion = notaAnulacion;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
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

    public String getTerminos() {
        return terminos;
    }

    public void setTerminos(String terminos) {
        this.terminos = terminos;
    }

    public String getConseMesa() {
        return conseMesa;
    }

    public void setConseMesa(String conseMesa) {
        this.conseMesa = conseMesa;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getBodega() {
        return bodega;
    }

    public void setBodega(String bodega) {
        this.bodega = bodega;
    }
}
