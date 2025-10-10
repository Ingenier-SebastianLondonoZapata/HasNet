package Controlador.BarraProceso;

public class controladorBarraProceso extends Thread {

    private jcThread barraProceso;

    public void setBarra(jcThread barraProceso) {
        this.barraProceso = barraProceso;
    }

    public jcThread getBarra() {
        return barraProceso;
    }
}
