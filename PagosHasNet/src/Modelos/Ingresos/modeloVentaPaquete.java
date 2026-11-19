package Modelos.Ingresos;

import java.math.BigDecimal;
import java.time.LocalDate;

public class modeloVentaPaquete {

    public enum Accion {
        CARGAR_SIN_VENCIMIENTO, ACTIVAR_MENSUAL, PROGRAMAR_CAMBIO, ANULAR_CAMBIO, PROGRAMAR_POR_VENCIDA, YA_TIENE_PLAN
    }

    private final Accion accion;
    private final modeloPlanFacturacion plan;
    private final int meses;
    private final BigDecimal valor;
    private final LocalDate vigencia;
    private final String descripcion;

    public modeloVentaPaquete(Accion accion, modeloPlanFacturacion plan, int meses, BigDecimal valor, LocalDate vigencia, String descripcion) {
        this.accion = accion;
        this.plan = plan;
        this.meses = meses;
        this.valor = valor;
        this.vigencia = vigencia;
        this.descripcion = descripcion;
    }

    public boolean isPermitida() {
        return accion != Accion.YA_TIENE_PLAN;
    }

    public Accion getAccion() {
        return accion;
    }

    public modeloPlanFacturacion getPlan() {
        return plan;
    }

    public int getMeses() {
        return meses;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public LocalDate getVigencia() {
        return vigencia;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
