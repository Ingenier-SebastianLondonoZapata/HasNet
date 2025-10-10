package clases;

import configuracion.dlgAlertaPermiso;
import configuracion.dlgPermisos;

public class msgPermisos extends Thread {

    String id;

    public msgPermisos(String consecutivo) {
        id = consecutivo;
    }

    metodosGenerales metodos = new metodosGenerales();
    Instancias instancias = Instancias.getInstancias();

    @Override
    public void run() {
        String parar = "Nunca";

        while (parar.equals("Nunca")) {
            dlgPermisos permiso = new dlgPermisos(null, true);
            esperarXsegundos(7);
        }
    }

    private void esperarXsegundos(int segundos) {
        try {
            Thread.sleep(segundos * 1000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

}
