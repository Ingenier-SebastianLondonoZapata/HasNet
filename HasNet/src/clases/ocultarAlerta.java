package clases;

public class ocultarAlerta extends Thread {

    metodosGenerales metodos = new metodosGenerales();
    Instancias instancias = Instancias.getInstancias();

    @Override
    public void run() {
        esperarXsegundos(5);
        instancias.getMenu().desactivarMensaje();
    }

    private void esperarXsegundos(int segundos) {
        try {
            Thread.sleep(segundos * 1000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

}
