package Controlador.BarraProceso;

import javax.swing.JFrame;
import javax.swing.JProgressBar;

public class jcThread implements Runnable {

    private JProgressBar jProgressBar;
    private int i = 1;
    private int value = 50;//retardo en milisegundos
    JFrame ventana;
    boolean tengaQueParar;

    /**
     * Constructor de clase
     */
    public jcThread(JProgressBar jProgressBar, int value, JFrame ventana) {
        this.jProgressBar = jProgressBar;
        this.value = value;
        this.ventana = ventana;
        tengaQueParar = false;

    }

    @Override
    public void run() {
        i = 1;
        //mientra el trabajo en paralelo no finalice el jProgressBar continuara su animacion una y otra vez
        while (!tengaQueParar) {
            //si llega al limite 100 comienza otra vez desde 1, sino incrementa i en +1
            i = (i > 100) ? 1 : i + 1;

            if (jProgressBar != null) {
                jProgressBar.setValue(i);
                jProgressBar.repaint();
            }

            //retardo en milisegundos
            try {
                Thread.sleep(this.value);
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }

            //si el trabajo en paralelo a terminado
            if (tengaQueParar) {
                if (jProgressBar != null) {
                    jProgressBar.setValue(100);
                }
                ventana.dispose();
                break;//rompe ciclo     
            }
        }
    }

    public void detener(boolean tengaQueParar) {
        this.tengaQueParar = tengaQueParar;
    }

}
