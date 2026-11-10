package Modelo.Productos;

import java.util.Objects;

public final class FiltroBuscadorProductos {

    private final boolean incluirInactivos;
    private final String claseBuscador;
    private final String grupo;
    private final String tipoBusqueda;
    private final String idBodega;
    private final boolean precioConIva;

    public FiltroBuscadorProductos(boolean incluirInactivos, String claseBuscador, String grupo, String tipoBusqueda,
            String idBodega, boolean precioConIva) {
        this.incluirInactivos = incluirInactivos;
        this.claseBuscador = normalizar(claseBuscador);
        this.grupo = normalizar(grupo);
        this.tipoBusqueda = normalizar(tipoBusqueda);
        this.idBodega = normalizar(idBodega);
        this.precioConIva = precioConIva;
    }

    private static String normalizar(String valor) {
        return valor == null ? "" : valor.trim();
    }

    public boolean isIncluirInactivos() {
        return incluirInactivos;
    }

    public String getClaseBuscador() {
        return claseBuscador;
    }

    public String getGrupo() {
        return grupo;
    }

    public String getTipoBusqueda() {
        return tipoBusqueda;
    }

    public String getIdBodega() {
        return idBodega;
    }

    public boolean isPrecioConIva() {
        return precioConIva;
    }

    @Override
    public boolean equals(Object otro) {
        if (this == otro) {
            return true;
        }
        if (!(otro instanceof FiltroBuscadorProductos)) {
            return false;
        }
        FiltroBuscadorProductos filtro = (FiltroBuscadorProductos) otro;
        return incluirInactivos == filtro.incluirInactivos
                && precioConIva == filtro.precioConIva
                && claseBuscador.equals(filtro.claseBuscador)
                && grupo.equals(filtro.grupo)
                && tipoBusqueda.equals(filtro.tipoBusqueda)
                && idBodega.equals(filtro.idBodega);
    }

    @Override
    public int hashCode() {
        return Objects.hash(incluirInactivos, claseBuscador, grupo, tipoBusqueda, idBodega, precioConIva);
    }
}
