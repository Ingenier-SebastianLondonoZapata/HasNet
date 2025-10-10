package clases.Cartera;

public class ndCxc {

    private String factura, tipo, estado, recibo, vencimiento, usuario, terminal, factura2;
    private String valor; //NUMEROS
    private int plazo;
    private boolean cuotas;

    public String getFactura2() {
        return factura2;
    }

    public void setFactura2(String factura2) {
        this.factura2 = factura2;
    }

    public boolean isCuotas() {
        return cuotas;
    }

    public void setCuotas(boolean cuotas) {
        this.cuotas = cuotas;
    }

    public String getFactura() {
        return factura;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public void setFactura(String factura) {
        this.factura = factura;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getRecibo() {
        return recibo;
    }

    public void setRecibo(String recibo) {
        this.recibo = recibo;
    }

    public String getVencimiento() {
        return vencimiento;
    }

    public void setVencimiento(String vencimiento) {
        this.vencimiento = vencimiento;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public int getPlazo() {
        return plazo;
    }

    public void setPlazo(int plazo) {
        this.plazo = plazo;
    }

}
