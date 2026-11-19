package Vista.BarraProceso;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JComponent;
import javax.swing.Timer;

public class TextoCargando extends JComponent {

    private static final Color COLOR_TEXTO = new Color(51, 51, 51);
    private static final Color COLOR_MARCA = new Color(0, 150, 214);
    private static final Color COLOR_PISTA = new Color(0, 150, 214, 40);
    private static final int ALTURA_OLA = 7;
    private static final int ALTURA_BARRA = 4;
    private static final int ESPACIO_BARRA = 10;

    private String texto = "CARGANDO";
    private final Timer temporizador;
    private long inicio = System.currentTimeMillis();

    public TextoCargando() {
        setFont(new Font("Century Gothic", Font.BOLD, 30));
        setOpaque(false);
        temporizador = new Timer(30, e -> repaint());
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto == null ? "" : texto;
        revalidate();
        repaint();
    }

    @Override
    public void addNotify() {
        super.addNotify();
        inicio = System.currentTimeMillis();
        temporizador.start();
    }

    @Override
    public void removeNotify() {
        temporizador.stop();
        super.removeNotify();
    }

    @Override
    public Dimension getPreferredSize() {
        if (isPreferredSizeSet()) {
            return super.getPreferredSize();
        }
        FontMetrics metricas = getFontMetrics(getFont());
        int ancho = metricas.stringWidth(texto) + metricas.stringWidth("...") * 2 + 20;
        int alto = metricas.getHeight() + ALTURA_OLA + ESPACIO_BARRA + ALTURA_BARRA + 8;
        return new Dimension(ancho, alto);
    }

    private static Color mezclar(Color a, Color b, double proporcion) {
        double p = Math.max(0, Math.min(1, proporcion));
        return new Color((int) (a.getRed() + (b.getRed() - a.getRed()) * p),
                (int) (a.getGreen() + (b.getGreen() - a.getGreen()) * p),
                (int) (a.getBlue() + (b.getBlue() - a.getBlue()) * p),
                (int) (a.getAlpha() + (b.getAlpha() - a.getAlpha()) * p));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        try {
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
            g2.setFont(getFont());
            FontMetrics metricas = g2.getFontMetrics();

            double segundos = (System.currentTimeMillis() - inicio) / 1000.0;
            int anchoTexto = metricas.stringWidth(texto);
            int anchoPuntos = metricas.stringWidth("...");
            int altoBloque = metricas.getHeight() + ALTURA_OLA + ESPACIO_BARRA + ALTURA_BARRA;
            int x = (getWidth() - anchoTexto) / 2;
            int superior = Math.max(0, (getHeight() - altoBloque) / 2);
            int base = superior + ALTURA_OLA + metricas.getAscent();

            for (int i = 0; i < texto.length(); i++) {
                String letra = String.valueOf(texto.charAt(i));
                double onda = Math.max(0, Math.sin(segundos * 5 - i * 0.55));
                g2.setColor(mezclar(COLOR_TEXTO, COLOR_MARCA, onda));
                g2.drawString(letra, x, (float) (base - onda * ALTURA_OLA));
                x += metricas.stringWidth(letra);
            }

            int puntos = (int) (segundos * 2.5) % 4;
            g2.setColor(COLOR_MARCA);
            g2.drawString("...".substring(0, puntos), x, base);

            int anchoPista = anchoTexto;
            int inicioPista = (getWidth() - anchoPista) / 2;
            int yBarra = base + metricas.getDescent() + ESPACIO_BARRA;
            g2.setStroke(new BasicStroke(ALTURA_BARRA, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g2.setColor(COLOR_PISTA);
            g2.drawLine(inicioPista, yBarra, inicioPista + anchoPista, yBarra);

            double avance = (segundos * 0.7) % 1.0;
            double suave = avance < 0.5 ? 2 * avance * avance : 1 - Math.pow(-2 * avance + 2, 2) / 2;
            int anchoSegmento = anchoPista / 3;
            int inicioSegmento = inicioPista - anchoSegmento + (int) ((anchoPista + anchoSegmento) * suave);
            int desde = Math.max(inicioPista, inicioSegmento);
            int hasta = Math.min(inicioPista + anchoPista, inicioSegmento + anchoSegmento);
            if (hasta > desde) {
                g2.setColor(COLOR_MARCA);
                g2.drawLine(desde, yBarra, hasta, yBarra);
            }
        } finally {
            g2.dispose();
        }
    }
}
