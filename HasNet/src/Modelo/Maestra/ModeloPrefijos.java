/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Maestra;

/**
 *
 * @author sebastian.londono
 */
public class ModeloPrefijos {

    String prefijoAbonos, prefijoEgresos, prefijoNotaDebito, prefijoNotaCredito, terminal;

    public ModeloPrefijos() {
        //DO NOTHING
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

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public ModeloPrefijos(String prefijoAbonos, String prefijoEgresos, String prefijoNotaDebito, String prefijoNotaCredito, String terminal) {
        this.prefijoAbonos = prefijoAbonos;
        this.prefijoEgresos = prefijoEgresos;
        this.prefijoNotaDebito = prefijoNotaDebito;
        this.prefijoNotaCredito = prefijoNotaCredito;
        this.terminal = terminal;
    }
}
