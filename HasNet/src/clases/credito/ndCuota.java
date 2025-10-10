package clases.credito;

public class ndCuota {
    
    String id, contrato, numCuota, fecha, usuario, fechaAbono, documentoAbono, documentoMora;
    String abonoCapital, abonoInteres, total, mora, totalAbono;
    private String saldoAtrasado;
    private String saldoMora;
    private String documentoAbonoMora;

    public String getDocumentoAbonoMora() {
        return documentoAbonoMora;
    }

    public void setDocumentoAbonoMora(String documentoAbonoMora) {
        this.documentoAbonoMora = documentoAbonoMora;
    }

    public String getSaldoMora() {
        return saldoMora;
    }

    public void setSaldoMora(String saldoMora) {
        this.saldoMora = saldoMora;
    }

    public String getSaldoAtrasado() {
        return saldoAtrasado;
    }

    public void setSaldoAtrasado(String saldoAtrasado) {
        this.saldoAtrasado = saldoAtrasado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContrato() {
        return contrato;
    }

    public void setContrato(String contrato) {
        this.contrato = contrato;
    }

    public String getNumCuota() {
        return numCuota;
    }

    public void setNumCuota(String numCuota) {
        this.numCuota = numCuota;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getFechaAbono() {
        return fechaAbono;
    }

    public void setFechaAbono(String fechaAbono) {
        this.fechaAbono = fechaAbono;
    }

    public String getDocumentoAbono() {
        return documentoAbono;
    }

    public void setDocumentoAbono(String documentoAbono) {
        this.documentoAbono = documentoAbono;
    }

    public String getDocumentoMora() {
        return documentoMora;
    }

    public void setDocumentoMora(String documentoMora) {
        this.documentoMora = documentoMora;
    }

    public String getAbonoCapital() {
        return abonoCapital;
    }

    public void setAbonoCapital(String abonoCapital) {
        this.abonoCapital = abonoCapital;
    }

    public String getAbonoInteres() {
        return abonoInteres;
    }

    public void setAbonoInteres(String abonoInteres) {
        this.abonoInteres = abonoInteres;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getMora() {
        return mora;
    }

    public void setMora(String mora) {
        this.mora = mora;
    }

    public String getTotalAbono() {
        return totalAbono;
    }

    public void setTotalAbono(String totalAbono) {
        this.totalAbono = totalAbono;
    }
    
}
