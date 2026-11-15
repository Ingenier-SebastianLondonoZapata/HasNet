package Modelo.Reportes;

import java.util.Objects;

public class OpcionFiltro {

    private final String etiqueta;
    private final String valor;

    public OpcionFiltro(String etiqueta, String valor) {
        this.etiqueta = etiqueta;
        this.valor = valor;
    }

    public static OpcionFiltro de(String valor) {
        return new OpcionFiltro(valor, valor);
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public String getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return etiqueta;
    }

    @Override
    public boolean equals(Object otro) {
        if (this == otro) {
            return true;
        }
        if (!(otro instanceof OpcionFiltro)) {
            return false;
        }
        return Objects.equals(valor, ((OpcionFiltro) otro).valor);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(valor);
    }
}
