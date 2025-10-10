package clases;

import Vistas.Buscadores.buscadorClientes;
import Vistas.Menu.vistaMenu;

public class Instancias {

    private static Instancias instancias;
    private SQL sql;
    private vistaMenu menu;
    private buscadorClientes busClientes;
    private jcThread progres;

    Instancias() {
        sql = new SQL();
    }

    public static Instancias getInstancias() {
        if (instancias == null) {
            instancias = new Instancias();
        }
        return instancias;
    }

    public jcThread getProgres() {
        return progres;
    }

    public void setProgres(jcThread progres) {
        this.progres = progres;
    }

    public buscadorClientes getBusClientes() {
        return busClientes;
    }

    public void setBusClientes(buscadorClientes busClientes) {
        this.busClientes = busClientes;
    }

    public vistaMenu getMenu() {
        return menu;
    }

    public void setMenu(vistaMenu menu) {
        this.menu = menu;
    }

    public SQL getSql() {
        return sql;
    }

    public void setSql(SQL sql) {
        this.sql = sql;
    }
}
