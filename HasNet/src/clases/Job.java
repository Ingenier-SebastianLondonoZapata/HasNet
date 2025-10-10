package clases;

/**
 * @web http://www.jc-mouse.net
 * @author Mouse
 */
public class Job implements Runnable {

    public static boolean band = false;
    public static int x;
    Thread tiempo = null;

    /**
     * Constructor de clase
     * @param tiempo
     */
    public Job(Thread tiempo) {
        this.tiempo = tiempo;
    }

    @Override
    public void run() {

        band = false;

        while (tiempo.isAlive()){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }

        band = true;
    }

}
