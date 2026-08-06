package Modelo.Solicitudes;

public class AccionesPermisos {

    boolean accionLimpiar;
    boolean accionDescuento;
    boolean accionBorrarProducto;

    public boolean isAccionLimpiar() {
        return accionLimpiar;
    }

    public boolean isAccionDescuento() {
        return accionDescuento;
    }

    public boolean isAccionBorrarProducto() {
        return accionBorrarProducto;
    }

    public AccionesPermisos(boolean esAccionLimpiar, boolean accionDescuento, boolean accionBorrarProducto) {
        this.accionLimpiar = esAccionLimpiar;
        this.accionDescuento = accionDescuento;
        this.accionBorrarProducto = accionBorrarProducto;
    }
}
