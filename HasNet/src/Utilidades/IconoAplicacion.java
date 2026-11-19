package Utilidades;

import java.awt.Image;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.ImageIcon;

public final class IconoAplicacion {

    private static final int[] TAMANOS = {16, 20, 24, 32, 40, 48, 64, 93};
    private static List<Image> imagenes;

    private IconoAplicacion() {
    }

    public static synchronized List<Image> obtener() {
        if (imagenes == null) {
            List<Image> cargadas = new ArrayList<>();
            for (int tamano : TAMANOS) {
                URL recurso = IconoAplicacion.class.getResource("/imagenes/aplicacion/icono_" + tamano + ".png");
                if (recurso != null) {
                    cargadas.add(new ImageIcon(recurso).getImage());
                }
            }
            if (cargadas.isEmpty()) {
                URL original = IconoAplicacion.class.getResource("/imagenes/aplicacion/icono.png");
                if (original != null) {
                    cargadas.add(new ImageIcon(original).getImage());
                }
            }
            imagenes = Collections.unmodifiableList(cargadas);
        }
        return imagenes;
    }

    public static void aplicar(java.awt.Window ventana) {
        List<Image> lista = obtener();
        if (!lista.isEmpty()) {
            ventana.setIconImages(lista);
        }
    }
}
