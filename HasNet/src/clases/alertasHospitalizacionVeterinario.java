package clases;

import formularios.Veterinario.dlgMedicamentosPendientes;
import java.applet.AudioClip;

public class alertasHospitalizacionVeterinario extends Thread {

    metodosGenerales metodos = new metodosGenerales();
    Instancias instancias = Instancias.getInstancias();

    @Override
    public void run() {

        int cantActual = 0, inicio = 1;
        String parar = "Nunca", hora = "", minuto;

        while (parar.equals("Nunca")) {

            hora = metodosGenerales.hora().split(":")[0];
            minuto = metodosGenerales.hora().split(":")[1];

            int numeroHora = Integer.valueOf(hora);
            int numeroMinuto = Integer.valueOf(minuto);

            Object[][] medicamentosPendientes = null;

            try {
                medicamentosPendientes = instancias.getSql().getMedicamentosPorAplicarVeterinario("");
            } catch (Exception e) {
            }

            Boolean activarMensaje = false;

            if (medicamentosPendientes != null) {
                for (int i = 0; i < medicamentosPendientes.length; i++) {
                    if (medicamentosPendientes[i][5] != null) {

                        int horaEstablecida = Integer.parseInt(medicamentosPendientes[i][4].toString().split(":")[0]);
                        int minutoEstablecida = Integer.parseInt(medicamentosPendientes[i][4].toString().split(":")[0]);

                        if (medicamentosPendientes[i][5].equals("Pendiente")) {
                            if (numeroHora >= horaEstablecida && numeroMinuto >= minutoEstablecida) {
                                activarMensaje = true;
                                break;
                            }
                        } else if (medicamentosPendientes[i][5].equals("Realizado")) {

                            if (numeroHora <= horaEstablecida && numeroMinuto <= minutoEstablecida) {
                                System.out.println("cambiar estadosss");
                                instancias.getSql().cambiarEstadoDeMedicamento();
                                break;
                            }
                        }
                    }
                }

                if (activarMensaje) {
                    AudioClip sonido;
                    sonido = java.applet.Applet.newAudioClip(getClass().getResource("/formularios/Veterinario/alert.wav"));
                    sonido.play();

                    instancias.getMenu().actualizarMedicamentos(true);
                    dlgMedicamentosPendientes medicamentosPend = new dlgMedicamentosPendientes(null, true);
                    medicamentosPend.setLocationRelativeTo(null);
                    medicamentosPend.setVisible(true);
                }

                cantActual = medicamentosPendientes.length;
                esperarXsegundos(600);
            } else {
                inicio = 0;
                cantActual = 0;
//                instancias.getMenu().actualizarMedicamentos(medicamentosPendientes.length, false, true);
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
