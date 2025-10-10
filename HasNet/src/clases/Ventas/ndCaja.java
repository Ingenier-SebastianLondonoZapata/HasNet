package clases.Ventas;

public class ndCaja {

    private String id, fecha, hora, cajero, usuario, termianl;
    private String efectivo, tarjeta, cheque, nc, total, sistema, diferencia, gasto, recogida, base;

    private String totalVenta, totalCredito, totalContado, ncRealizadas, efectivoSistema, chequeSistema, tarjetaSistema, ncSistema, gastosManuales,
            abonosCartera, abonosSepare, ventasDomi, itemDomi, totalPropinas, sisteCredito;
    private int contContado, contCredito, contNcRecibidas, contNcRealizadas, contEfectivo, contCheque, contTarjeta, contAbonos;
    private String totalAbonos, primeraFactura, ultimaFactura, primerAbono, ultimoAbono;
    private String totalFactSepares;
    private String totalCuotaInicial;
    private int contCuotaInicial;
    private int contGastos;
    private String ncReembolsadas;
    private int contNcReembolsadas;

    public String getSisteCredito() {
        return sisteCredito;
    }

    public void setSisteCredito(String sisteCredito) {
        this.sisteCredito = sisteCredito;
    }

    public String getTotalPropinas() {
        return totalPropinas;
    }

    public void setTotalPropinas(String totalPropinas) {
        this.totalPropinas = totalPropinas;
    }

    public String getVentasDomi() {
        return ventasDomi;
    }

    public void setVentasDomi(String ventasDomi) {
        this.ventasDomi = ventasDomi;
    }

    public String getItemDomi() {
        return itemDomi;
    }

    public void setItemDomi(String itemDomi) {
        this.itemDomi = itemDomi;
    }

    public String getAbonosCartera() {
        return abonosCartera;
    }

    public void setAbonosCartera(String abonosCartera) {
        this.abonosCartera = abonosCartera;
    }

    public String getAbonosSepare() {
        return abonosSepare;
    }

    public void setAbonosSepare(String abonosSepare) {
        this.abonosSepare = abonosSepare;
    }

    public String getGastosManuales() {
        return gastosManuales;
    }

    public void setGastosManuales(String gastosManuales) {
        this.gastosManuales = gastosManuales;
    }

    public String getNcReembolsadas() {
        return ncReembolsadas;
    }

    public void setNcReembolsadas(String ncReembolsadas) {
        this.ncReembolsadas = ncReembolsadas;
    }

    public int getContNcReembolsadas() {
        return contNcReembolsadas;
    }

    public void setContNcReembolsadas(int contNcReembolsadas) {
        this.contNcReembolsadas = contNcReembolsadas;
    }

    public int getContGastos() {
        return contGastos;
    }

    public void setContGastos(int contGastos) {
        this.contGastos = contGastos;
    }

    public int getContCuotaInicial() {
        return contCuotaInicial;
    }

    public void setContCuotaInicial(int contCuotaInicial) {
        this.contCuotaInicial = contCuotaInicial;
    }

    public String getTotalCuotaInicial() {
        return totalCuotaInicial;
    }

    public void setTotalCuotaInicial(String totalCuotaInicial) {
        this.totalCuotaInicial = totalCuotaInicial;
    }

    public String getTotalFactSepares() {
        return totalFactSepares;
    }

    public void setTotalFactSepares(String totalFactSepares) {
        this.totalFactSepares = totalFactSepares;
    }

    public String getTotalAbonos() {
        return totalAbonos;
    }

    public void setTotalAbonos(String totalAbonos) {
        this.totalAbonos = totalAbonos;
    }

    public String getPrimeraFactura() {
        return primeraFactura;
    }

    public void setPrimeraFactura(String primeraFactura) {
        this.primeraFactura = primeraFactura;
    }

    public String getUltimaFactura() {
        return ultimaFactura;
    }

    public void setUltimaFactura(String ultimaFactura) {
        this.ultimaFactura = ultimaFactura;
    }

    public String getPrimerAbono() {
        return primerAbono;
    }

    public void setPrimerAbono(String primerAbono) {
        this.primerAbono = primerAbono;
    }

    public String getUltimoAbono() {
        return ultimoAbono;
    }

    public void setUltimoAbono(String ultimoAbono) {
        this.ultimoAbono = ultimoAbono;
    }

    public int getContContado() {
        return contContado;
    }

    public void setContContado(int contContado) {
        this.contContado = contContado;
    }

    public int getContCredito() {
        return contCredito;
    }

    public void setContCredito(int contCredito) {
        this.contCredito = contCredito;
    }

    public int getContNcRecibidas() {
        return contNcRecibidas;
    }

    public void setContNcRecibidas(int contNcRecibidas) {
        this.contNcRecibidas = contNcRecibidas;
    }

    public int getContNcRealizadas() {
        return contNcRealizadas;
    }

    public void setContNcRealizadas(int contNcRealizadas) {
        this.contNcRealizadas = contNcRealizadas;
    }

    public int getContEfectivo() {
        return contEfectivo;
    }

    public void setContEfectivo(int contEfectivo) {
        this.contEfectivo = contEfectivo;
    }

    public int getContCheque() {
        return contCheque;
    }

    public void setContCheque(int contCheque) {
        this.contCheque = contCheque;
    }

    public int getContTarjeta() {
        return contTarjeta;
    }

    public void setContTarjeta(int contTarjeta) {
        this.contTarjeta = contTarjeta;
    }

    public int getContAbonos() {
        return contAbonos;
    }

    public void setContAbonos(int contAbonos) {
        this.contAbonos = contAbonos;
    }

    public String getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(String totalVenta) {
        this.totalVenta = totalVenta;
    }

    public String getTotalCredito() {
        return totalCredito;
    }

    public void setTotalCredito(String totalCredito) {
        this.totalCredito = totalCredito;
    }

    public String getTotalContado() {
        return totalContado;
    }

    public void setTotalContado(String totalContado) {
        this.totalContado = totalContado;
    }

    public String getNcRealizadas() {
        return ncRealizadas;
    }

    public void setNcRealizadas(String ncRealizadas) {
        this.ncRealizadas = ncRealizadas;
    }

    public String getEfectivoSistema() {
        return efectivoSistema;
    }

    public void setEfectivoSistema(String efectivoSistema) {
        this.efectivoSistema = efectivoSistema;
    }

    public String getChequeSistema() {
        return chequeSistema;
    }

    public void setChequeSistema(String chequeSistema) {
        this.chequeSistema = chequeSistema;
    }

    public String getTarjetaSistema() {
        return tarjetaSistema;
    }

    public void setTarjetaSistema(String tarjetaSistema) {
        this.tarjetaSistema = tarjetaSistema;
    }

    public String getNcSistema() {
        return ncSistema;
    }

    public void setNcSistema(String ncSistema) {
        this.ncSistema = ncSistema;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getCajero() {
        return cajero;
    }

    public void setCajero(String cajero) {
        this.cajero = cajero;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getEfectivo() {
        return efectivo;
    }

    public void setEfectivo(String efectivo) {
        this.efectivo = efectivo;
    }

    public String getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(String tarjeta) {
        this.tarjeta = tarjeta;
    }

    public String getCheque() {
        return cheque;
    }

    public void setCheque(String cheque) {
        this.cheque = cheque;
    }

    public String getNc() {
        return nc;
    }

    public void setNc(String nc) {
        this.nc = nc;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getSistema() {
        return sistema;
    }

    public void setSistema(String sistema) {
        this.sistema = sistema;
    }

    public String getDiferencia() {
        return diferencia;
    }

    public void setDiferencia(String diferencia) {
        this.diferencia = diferencia;
    }

    public String getTermianl() {
        return termianl;
    }

    public void setTermianl(String termianl) {
        this.termianl = termianl;
    }

    public String getGasto() {
        return gasto;
    }

    public void setGasto(String gasto) {
        this.gasto = gasto;
    }

    public String getRecogida() {
        return recogida;
    }

    public void setRecogida(String recogida) {
        this.recogida = recogida;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

}
