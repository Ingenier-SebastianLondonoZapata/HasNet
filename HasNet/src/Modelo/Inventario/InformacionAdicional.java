package Modelo.Inventario;

public class InformacionAdicional {

    private boolean vieneDesdePedido;
    private boolean vieneDesdePlanSepare;
    private boolean vieneDesdeOrdenServicio;

    public boolean isVieneDesdePedido() {
        return vieneDesdePedido;
    }

    public void setVieneDesdePedido(boolean vieneDesdePedido) {
        this.vieneDesdePedido = vieneDesdePedido;
    }

    public boolean isVieneDesdePlanSepare() {
        return vieneDesdePlanSepare;
    }

    public void setVieneDesdePlanSepare(boolean vieneDesdePlanSepare) {
        this.vieneDesdePlanSepare = vieneDesdePlanSepare;
    }

    public boolean isVieneDesdeOrdenServicio() {
        return vieneDesdeOrdenServicio;
    }

    public void setVieneDesdeOrdenServicio(boolean vieneDesdeOrdenServicio) {
        this.vieneDesdeOrdenServicio = vieneDesdeOrdenServicio;
    }

    public InformacionAdicional(boolean vieneDesdePedido, boolean vieneDesdePlanSepare, boolean vieneDesdeOrdenServicio) {
        this.vieneDesdePedido = vieneDesdePedido;
        this.vieneDesdePlanSepare = vieneDesdePlanSepare;
        this.vieneDesdeOrdenServicio = vieneDesdeOrdenServicio;
    }
}
