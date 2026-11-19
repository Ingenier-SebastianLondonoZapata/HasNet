package Modelos.Ingresos;

import clases.big;
import java.math.BigDecimal;

public class modeloPlanFacturacion {

    public static final String TIPO_MENSUAL = "MENSUAL";
    public static final String TIPO_FIJO = "FIJO";
    public static final int ID_CANCELAR_PLAN_MENSUAL = -1;

    private int idPlan;
    private String nombre;
    private String tipo;
    private int numeroDocumentos;
    private BigDecimal precio = BigDecimal.ZERO;
    private boolean activo = true;

    public modeloPlanFacturacion() {
    }

    public modeloPlanFacturacion(int idPlan, String nombre, String tipo, int numeroDocumentos, BigDecimal precio, boolean activo) {
        this.idPlan = idPlan;
        this.nombre = nombre;
        this.tipo = tipo;
        this.numeroDocumentos = numeroDocumentos;
        this.precio = precio;
        this.activo = activo;
    }

    public static modeloPlanFacturacion opcionCancelarPlanMensual() {
        return new modeloPlanFacturacion(ID_CANCELAR_PLAN_MENSUAL, "CANCELAR PLAN MENSUAL ACTUAL", TIPO_MENSUAL, 0, BigDecimal.ZERO, true);
    }

    public boolean isMensual() {
        return TIPO_MENSUAL.equals(tipo);
    }

    public int getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(int idPlan) {
        this.idPlan = idPlan;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getNumeroDocumentos() {
        return numeroDocumentos;
    }

    public void setNumeroDocumentos(int numeroDocumentos) {
        this.numeroDocumentos = numeroDocumentos;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        if (idPlan == ID_CANCELAR_PLAN_MENSUAL) {
            return nombre;
        }
        return nombre + " - " + (isMensual() ? "Mensual" : "Sin vencimiento") + " - " + numeroDocumentos + " docs - " + big.setMoneda(precio);
    }
}
