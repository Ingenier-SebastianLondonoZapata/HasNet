package Modelo.Multiempresa;

import Utilidades.Constantes;

public class Empresa {

    public static final String ID_PRINCIPAL = "PRINC";

    private String id;
    private String nombre;
    private String nit;
    private String tipoIdentificacion;
    private String baseDatos;
    private boolean activa = true;

    public static Empresa principal(String nombre, String nit) {
        Empresa empresa = new Empresa();
        empresa.setId(ID_PRINCIPAL);
        empresa.setNombre(nombre == null || nombre.trim().isEmpty() ? "EMPRESA PRINCIPAL" : nombre.trim());
        empresa.setNit(nit);
        empresa.setBaseDatos(Constantes.BASE_DATOS_PRINCIPAL);
        return empresa;
    }

    public boolean isPrincipal() {
        return ID_PRINCIPAL.equals(id) || Constantes.BASE_DATOS_PRINCIPAL.equalsIgnoreCase(baseDatos);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getBaseDatos() {
        return baseDatos;
    }

    public void setBaseDatos(String baseDatos) {
        this.baseDatos = baseDatos;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    @Override
    public String toString() {
        return nombre == null ? "" : nombre;
    }
}
