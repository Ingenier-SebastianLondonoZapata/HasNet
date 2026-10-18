package Vista.Alertas;

import java.awt.Font;
import java.awt.FontMetrics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;

final class AjustadorTextoAlerta {

    private static final int TAMANO_MINIMO = 11;
    private static final String SUSPENSIVOS = "…";

    private AjustadorTextoAlerta() {
    }

    static void ajustar(JLabel etiqueta, String texto, int ancho, int alto) {
        String mensaje = texto == null ? "" : texto.trim();
        Font base = etiqueta.getFont();

        for (int tamano = base.getSize(); tamano >= TAMANO_MINIMO; tamano--) {
            Font fuente = base.deriveFont((float) tamano);
            FontMetrics metricas = etiqueta.getFontMetrics(fuente);
            List<String> lineas = partir(mensaje, metricas, ancho);

            if (lineas.size() * metricas.getHeight() <= alto || tamano == TAMANO_MINIMO) {
                int maximoLineas = Math.max(1, alto / metricas.getHeight());
                if (lineas.size() > maximoLineas) {
                    lineas = recortar(lineas, maximoLineas, metricas, ancho);
                }
                etiqueta.setFont(fuente);
                etiqueta.setText(aHtml(lineas));
                break;
            }
        }

        etiqueta.setToolTipText("<html><body style='width:400px'>" + escapar(mensaje) + "</body></html>");
    }

    private static List<String> partir(String texto, FontMetrics metricas, int ancho) {
        List<String> lineas = new ArrayList<>();

        for (String parrafo : texto.split("\\r?\\n")) {
            StringBuilder actual = new StringBuilder();

            for (String palabra : parrafo.trim().split("\\s+")) {
                if (palabra.isEmpty()) {
                    continue;
                }

                String candidata = actual.length() == 0 ? palabra : actual + " " + palabra;
                if (metricas.stringWidth(candidata) <= ancho) {
                    actual = new StringBuilder(candidata);
                    continue;
                }

                if (actual.length() > 0) {
                    lineas.add(actual.toString());
                    actual = new StringBuilder();
                }

                String resto = palabra;
                while (metricas.stringWidth(resto) > ancho) {
                    int corte = resto.length() - 1;
                    while (corte > 1 && metricas.stringWidth(resto.substring(0, corte)) > ancho) {
                        corte--;
                    }
                    lineas.add(resto.substring(0, corte));
                    resto = resto.substring(corte);
                }
                actual.append(resto);
            }

            lineas.add(actual.toString());
        }

        return lineas;
    }

    private static List<String> recortar(List<String> lineas, int maximo, FontMetrics metricas, int ancho) {
        List<String> recortadas = new ArrayList<>(lineas.subList(0, maximo));
        String ultima = recortadas.get(maximo - 1);
        while (!ultima.isEmpty() && metricas.stringWidth(ultima + SUSPENSIVOS) > ancho) {
            ultima = ultima.substring(0, ultima.length() - 1);
        }
        recortadas.set(maximo - 1, ultima + SUSPENSIVOS);
        return recortadas;
    }

    private static String aHtml(List<String> lineas) {
        StringBuilder html = new StringBuilder("<html><div style='text-align:center'>");
        for (int i = 0; i < lineas.size(); i++) {
            if (i > 0) {
                html.append("<br>");
            }
            html.append(escapar(lineas.get(i)));
        }
        return html.append("</div></html>").toString();
    }

    private static String escapar(String texto) {
        return texto.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
    }
}
