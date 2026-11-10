package Modelo.Productos;

import java.math.BigDecimal;

public class ProductoBuscador {

    private String codigo;
    private String descripcion;
    private String referencia;
    private String precio;
    private BigDecimal fisicoInventario;
    private String ubicacion;
    private String grupo;
    private String ultimoCosto;
    private String idSistema;
    private String usuario;
    private boolean manejaInventario;
    private boolean tienePlu;
    private String indVentas;
    private String descripcionLarga;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public BigDecimal getFisicoInventario() {
        return fisicoInventario;
    }

    public void setFisicoInventario(BigDecimal fisicoInventario) {
        this.fisicoInventario = fisicoInventario;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getUltimoCosto() {
        return ultimoCosto;
    }

    public void setUltimoCosto(String ultimoCosto) {
        this.ultimoCosto = ultimoCosto;
    }

    public String getIdSistema() {
        return idSistema;
    }

    public void setIdSistema(String idSistema) {
        this.idSistema = idSistema;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public boolean isManejaInventario() {
        return manejaInventario;
    }

    public void setManejaInventario(boolean manejaInventario) {
        this.manejaInventario = manejaInventario;
    }

    public boolean isTienePlu() {
        return tienePlu;
    }

    public void setTienePlu(boolean tienePlu) {
        this.tienePlu = tienePlu;
    }

    public String getIndVentas() {
        return indVentas;
    }

    public void setIndVentas(String indVentas) {
        this.indVentas = indVentas;
    }

    public String getDescripcionLarga() {
        return descripcionLarga;
    }

    public void setDescripcionLarga(String descripcionLarga) {
        this.descripcionLarga = descripcionLarga;
    }
}
