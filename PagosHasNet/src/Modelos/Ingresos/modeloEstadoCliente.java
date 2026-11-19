package Modelos.Ingresos;

import java.time.LocalDate;

public class modeloEstadoCliente {

    private String idCliente;
    private String nit;
    private String nombre;
    private int numeroTerminales;
    private int terminalesPagadas;
    private int numeroFEDisponibles;
    private int numeroFEMensuales;
    private LocalDate vigenciaFEMensuales;
    private Integer idPlanMensual;
    private Integer idPlanMensualSiguiente;
    private int diasGabela;
    private java.util.List<String> serviciosEspeciales = new java.util.ArrayList<>();
    private int empresasAdicionales;
    private boolean facturacionElectronica;

    public java.util.List<String> getServiciosEspeciales() {
        return serviciosEspeciales;
    }

    public void setServiciosEspeciales(java.util.List<String> serviciosEspeciales) {
        this.serviciosEspeciales = serviciosEspeciales == null ? new java.util.ArrayList<>() : serviciosEspeciales;
    }

    public int getNumeroServiciosEspeciales() {
        return serviciosEspeciales.size();
    }

    public int getEmpresasAdicionales() {
        return empresasAdicionales;
    }

    public void setEmpresasAdicionales(int empresasAdicionales) {
        this.empresasAdicionales = Math.max(0, empresasAdicionales);
    }

    public boolean isFacturacionElectronica() {
        return facturacionElectronica;
    }

    public void setFacturacionElectronica(boolean facturacionElectronica) {
        this.facturacionElectronica = facturacionElectronica;
    }

    public boolean isPlanMensualVigente(LocalDate hoy) {
        return idPlanMensual != null && vigenciaFEMensuales != null && !vigenciaFEMensuales.isBefore(hoy);
    }

    public int documentosMensualesVigentes(LocalDate hoy) {
        return vigenciaFEMensuales != null && !vigenciaFEMensuales.isBefore(hoy) ? numeroFEMensuales : 0;
    }

    public int documentosDisponibles(LocalDate hoy) {
        return numeroFEDisponibles + documentosMensualesVigentes(hoy);
    }

    public Integer planParaSiguientePeriodo() {
        if (idPlanMensualSiguiente == null) {
            return idPlanMensual;
        }
        return idPlanMensualSiguiente <= 0 ? null : idPlanMensualSiguiente;
    }

    public boolean isCancelacionPlanProgramada() {
        return idPlanMensualSiguiente != null && idPlanMensualSiguiente <= 0;
    }

    public int getTerminalesPendientes() {
        return Math.max(0, numeroTerminales - terminalesPagadas);
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumeroTerminales() {
        return numeroTerminales;
    }

    public void setNumeroTerminales(int numeroTerminales) {
        this.numeroTerminales = numeroTerminales;
    }

    public int getTerminalesPagadas() {
        return terminalesPagadas;
    }

    public void setTerminalesPagadas(int terminalesPagadas) {
        this.terminalesPagadas = terminalesPagadas;
    }

    public int getNumeroFEDisponibles() {
        return numeroFEDisponibles;
    }

    public void setNumeroFEDisponibles(int numeroFEDisponibles) {
        this.numeroFEDisponibles = numeroFEDisponibles;
    }

    public int getNumeroFEMensuales() {
        return numeroFEMensuales;
    }

    public void setNumeroFEMensuales(int numeroFEMensuales) {
        this.numeroFEMensuales = numeroFEMensuales;
    }

    public LocalDate getVigenciaFEMensuales() {
        return vigenciaFEMensuales;
    }

    public void setVigenciaFEMensuales(LocalDate vigenciaFEMensuales) {
        this.vigenciaFEMensuales = vigenciaFEMensuales;
    }

    public Integer getIdPlanMensual() {
        return idPlanMensual;
    }

    public void setIdPlanMensual(Integer idPlanMensual) {
        this.idPlanMensual = idPlanMensual;
    }

    public Integer getIdPlanMensualSiguiente() {
        return idPlanMensualSiguiente;
    }

    public void setIdPlanMensualSiguiente(Integer idPlanMensualSiguiente) {
        this.idPlanMensualSiguiente = idPlanMensualSiguiente;
    }

    public int getDiasGabela() {
        return diasGabela;
    }

    public void setDiasGabela(int diasGabela) {
        this.diasGabela = diasGabela;
    }
}
