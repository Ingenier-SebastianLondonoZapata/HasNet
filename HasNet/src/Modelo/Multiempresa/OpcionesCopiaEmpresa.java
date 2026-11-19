package Modelo.Multiempresa;

public class OpcionesCopiaEmpresa {

    private boolean grupos;
    private boolean productos;
    private boolean contactos;
    private boolean disenos;

    public boolean isGrupos() {
        return grupos || isProductos();
    }

    public void setGrupos(boolean grupos) {
        this.grupos = grupos;
    }

    public boolean isProductos() {
        return productos || disenos;
    }

    public void setProductos(boolean productos) {
        this.productos = productos;
    }

    public boolean isContactos() {
        return contactos;
    }

    public void setContactos(boolean contactos) {
        this.contactos = contactos;
    }

    public boolean isDisenos() {
        return disenos;
    }

    public void setDisenos(boolean disenos) {
        this.disenos = disenos;
    }
}
