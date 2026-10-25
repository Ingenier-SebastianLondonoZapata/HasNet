package Modelo.Contactos;

import Utilidades.Contactos.Bodegas;

public class ModeloBodega {

    private String codigo, contacto, nombre, tipoBodega, estado, usuario;

    public ModeloBodega() {
        //DO NOTHING
    }

    public ModeloBodega(String codigo, String contacto, String nombre, String tipoBodega, String estado, String usuario) {
        this.codigo = codigo;
        this.contacto = contacto;
        this.nombre = nombre;
        this.tipoBodega = tipoBodega;
        this.estado = estado;
        this.usuario = usuario;
    }

    public boolean esNueva() {
        return codigo == null || codigo.trim().isEmpty();
    }

    public boolean estaActiva() {
        return Bodegas.esEstadoActivo(estado);
    }

    public boolean esPrincipal() {
        return Bodegas.esPrincipal(codigo);
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoBodega() {
        return tipoBodega;
    }

    public void setTipoBodega(String tipoBodega) {
        this.tipoBodega = tipoBodega;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
