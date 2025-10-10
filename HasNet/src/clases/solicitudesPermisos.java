/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import configuracion.dlgAlertaPermiso;

public class solicitudesPermisos extends Thread {

    metodosGenerales metodos = new metodosGenerales();
    Instancias instancias = Instancias.getInstancias();

    @Override
    public void run() {

        int cantActual = 0, inicio = 1;
        String parar = "Nunca";

        while (parar.equals("Nunca")) {

            if (!instancias.getUsuario().equals("ADMIN")) {
                parar = "SI";
            }

            Object[][] pendientes = new Object[0][0];

            try {
                pendientes = instancias.getSql().getPermisosPendientes(" where estado = 'PENDIENTE'; ");
            } catch (Exception e) {
            }

            if (pendientes.length > 0) {
                if (cantActual == 0 && inicio == 1) {
                    inicio = 0;
//                    metodos.msgAdvertencia(null, "Tiene permisos pendientes");
                } else {
                    if (pendientes.length == cantActual) {
                        System.out.println("Nada nuevo");
                    } else if (pendientes.length < cantActual) {
                        System.out.println("Menos solicitudes");
                    } else if (pendientes.length > cantActual) {
                        dlgAlertaPermiso alertaPermiso = new dlgAlertaPermiso(null, true);
                        alertaPermiso.setLocationRelativeTo(null);
                        alertaPermiso.setVisible(true);
                    }
                }

                instancias.getMenu().actualizarPermisos(pendientes.length, true, true);
                cantActual = pendientes.length;
                esperarXsegundos(7);
            } else {
                inicio = 0;
                cantActual = 0;
                instancias.getMenu().actualizarPermisos(pendientes.length, false, true);
            }
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
