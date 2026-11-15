package Vista.Reportes;

import Modelo.Reportes.SeleccionReporte;
import java.util.function.Consumer;

public class AccionReporte {

    private final String texto;
    private final String nombreArchivo;
    private final Consumer<SeleccionReporte> ejecutar;

    public AccionReporte(String texto, String nombreArchivo, Consumer<SeleccionReporte> ejecutar) {
        this.texto = texto;
        this.nombreArchivo = nombreArchivo;
        this.ejecutar = ejecutar;
    }

    public String getTexto() {
        return texto;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void ejecutar(SeleccionReporte seleccion) {
        ejecutar.accept(seleccion);
    }
}
