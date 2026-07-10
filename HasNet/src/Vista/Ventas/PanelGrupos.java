package Vista.Ventas;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

public class PanelGrupos extends JPanel {

    public interface ListenerGrupo {
        void grupoSeleccionado(String codigo, String nombre);
    }

    public static class ModeloGrupo {
        private final String codigo;
        private final String nombre;
        private final int colorIdx;
        private int pixelX, pixelY, ancho, alto;

        public ModeloGrupo(String codigo, String nombre, int colorIdx) {
            this.codigo = codigo;
            this.nombre = nombre;
            this.colorIdx = colorIdx;
        }

        public boolean contains(int x, int y) {
            return x >= pixelX && x <= pixelX + ancho && y >= pixelY && y <= pixelY + alto;
        }

        public String getCodigo() { return codigo; }
        public String getNombre() { return nombre; }
    }

    private static final int CARD_W  = 160;
    private static final int CARD_H  = 100;
    private static final int GAP     = 14;
    private static final int PADDING = 18;

    private static final Color[] PALETA = {
        new Color(52,  152, 219),
        new Color(46,  204, 113),
        new Color(231, 76,  60),
        new Color(155, 89,  182),
        new Color(230, 126, 34),
        new Color(26,  188, 156),
        new Color(52,  73,  94),
        new Color(241, 196, 15),
    };

    private List<ModeloGrupo> grupos = new ArrayList<ModeloGrupo>();
    private ListenerGrupo listener;
    private int hover = -1;

    public PanelGrupos() {
        setBackground(new Color(240, 243, 248));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                for (ModeloGrupo g : grupos) {
                    if (g.contains(e.getX(), e.getY()) && listener != null) {
                        listener.grupoSeleccionado(g.getCodigo(), g.getNombre());
                        break;
                    }
                }
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int prev = hover;
                hover = -1;
                for (int i = 0; i < grupos.size(); i++) {
                    if (grupos.get(i).contains(e.getX(), e.getY())) {
                        hover = i;
                        break;
                    }
                }
                if (hover != prev) repaint();
            }
        });
    }

    public void setListener(ListenerGrupo l) {
        this.listener = l;
    }

    public void setGrupos(Object[][] datos) {
        grupos.clear();
        if (datos == null) return;
        for (int i = 0; i < datos.length; i++) {
            String codigo = datos[i][0].toString();
            String nombre = datos[i][1].toString();
            grupos.add(new ModeloGrupo(codigo, nombre, i % PALETA.length));
        }
        recalcularPosiciones();
    }

    private void recalcularPosiciones() {
        // Ancho fijo para 2 columnas; se usa como fallback cuando el panel aún no fue dimensionado
        int anchoFijo2Cols = PADDING * 2 + 2 * CARD_W + GAP;
        int panelW = getWidth() > 0 ? getWidth() : anchoFijo2Cols;

        // Máximo 2 columnas para que siempre quepan completas y el scroll sea vertical
        int cols = Math.min(2, Math.max(1, (panelW - PADDING * 2 + GAP) / (CARD_W + GAP)));
        int rows = grupos.isEmpty() ? 1 : (grupos.size() + cols - 1) / cols;

        for (int i = 0; i < grupos.size(); i++) {
            ModeloGrupo g = grupos.get(i);
            g.pixelX = PADDING + (i % cols) * (CARD_W + GAP);
            g.pixelY = PADDING + (i / cols) * (CARD_H + GAP);
            g.ancho  = CARD_W;
            g.alto   = CARD_H;
        }

        int prefW  = PADDING * 2 + cols * CARD_W + Math.max(0, cols - 1) * GAP;
        int totalH = PADDING * 2 + rows * CARD_H + Math.max(0, rows - 1) * GAP;
        setPreferredSize(new Dimension(prefW, Math.max(totalH, 200)));
        revalidate();
        repaint();
    }

    @Override
    public void setBounds(int x, int y, int w, int h) {
        super.setBounds(x, y, w, h);
        recalcularPosiciones();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,      RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING,         RenderingHints.VALUE_RENDER_QUALITY);

        for (int i = 0; i < grupos.size(); i++) {
            dibujarGrupo(g2, grupos.get(i), i == hover);
        }
    }

    private void dibujarGrupo(Graphics2D g2, ModeloGrupo g, boolean isHover) {
        int x = g.pixelX;
        int y = g.pixelY;

        if (isHover) {
            x -= 3;
            y -= 3;
        }

        Color base   = PALETA[g.colorIdx];
        Color darker = base.darker();

        // Sombra difusa
        for (int i = 4; i >= 1; i--) {
            g2.setColor(new Color(0, 0, 0, 10 * i));
            g2.fill(new RoundRectangle2D.Float(x + i, y + i, CARD_W, CARD_H, 16, 16));
        }

        // Fondo con gradiente
        g2.setPaint(new GradientPaint(x, y, base, x, y + CARD_H, darker));
        g2.fill(new RoundRectangle2D.Float(x, y, CARD_W, CARD_H, 16, 16));

        // Brillo superior
        g2.setPaint(new GradientPaint(x, y, new Color(255, 255, 255, 65), x, y + 22, new Color(255, 255, 255, 0)));
        g2.fill(new RoundRectangle2D.Float(x, y, CARD_W, 22, 16, 16));

        // Borde
        g2.setColor(new Color(0, 0, 0, 35));
        g2.setStroke(new BasicStroke(1f));
        g2.draw(new RoundRectangle2D.Float(x, y, CARD_W, CARD_H, 16, 16));

        // Texto centrado con word-wrap
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Century Gothic", Font.BOLD, 14));
        dibujarTexto(g2, g.getNombre(), x, y, CARD_W, CARD_H);
    }

    private void dibujarTexto(Graphics2D g2, String texto, int x, int y, int w, int h) {
        FontMetrics fm = g2.getFontMetrics();
        String[] palabras = texto.split(" ");
        List<String> lineas = new ArrayList<String>();
        String actual = "";

        for (String palabra : palabras) {
            String prueba = actual.isEmpty() ? palabra : actual + " " + palabra;
            if (fm.stringWidth(prueba) <= w - 20) {
                actual = prueba;
            } else {
                if (!actual.isEmpty()) lineas.add(actual);
                actual = palabra;
            }
        }
        if (!actual.isEmpty()) lineas.add(actual);

        int lineH   = fm.getHeight();
        int totalH  = lineas.size() * lineH;
        int startY  = y + (h - totalH) / 2 + fm.getAscent();

        for (String linea : lineas) {
            int lx = x + (w - fm.stringWidth(linea)) / 2;
            g2.drawString(linea, lx, startY);
            startY += lineH;
        }
    }
}
