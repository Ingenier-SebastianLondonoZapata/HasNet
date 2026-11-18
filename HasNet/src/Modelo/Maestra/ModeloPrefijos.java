package Modelo.Maestra;

public class ModeloPrefijos {

    String prefijoAbonos, prefijoEgresos, prefijoNotaDebito, prefijoNotaCredito;

    public ModeloPrefijos() {
    }

    public ModeloPrefijos(String prefijoAbonos, String prefijoEgresos, String prefijoNotaDebito, String prefijoNotaCredito) {
        this.prefijoAbonos = prefijoAbonos;
        this.prefijoEgresos = prefijoEgresos;
        this.prefijoNotaDebito = prefijoNotaDebito;
        this.prefijoNotaCredito = prefijoNotaCredito;
    }

    public String getPrefijoAbonos() {
        return prefijoAbonos;
    }

    public void setPrefijoAbonos(String prefijoAbonos) {
        this.prefijoAbonos = prefijoAbonos;
    }

    public String getPrefijoEgresos() {
        return prefijoEgresos;
    }

    public void setPrefijoEgresos(String prefijoEgresos) {
        this.prefijoEgresos = prefijoEgresos;
    }

    public String getPrefijoNotaDebito() {
        return prefijoNotaDebito;
    }

    public void setPrefijoNotaDebito(String prefijoNotaDebito) {
        this.prefijoNotaDebito = prefijoNotaDebito;
    }

    public String getPrefijoNotaCredito() {
        return prefijoNotaCredito;
    }

    public void setPrefijoNotaCredito(String prefijoNotaCredito) {
        this.prefijoNotaCredito = prefijoNotaCredito;
    }
}
