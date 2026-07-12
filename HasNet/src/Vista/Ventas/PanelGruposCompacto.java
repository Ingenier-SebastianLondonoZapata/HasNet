package Vista.Ventas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

public class PanelGruposCompacto extends JPanel {

    public interface ListenerGrupo {
        void grupoSeleccionado(String codigo, String nombre);
    }

    private static class ModeloGrupo {

        final String codigo;
        final String nombre;
        final int colorIdx;
        int x, y, ancho, alto;

        ModeloGrupo(String codigo, String nombre, int colorIdx) {
            this.codigo = codigo;
            this.nombre = nombre;
            this.colorIdx = colorIdx;
        }

        boolean contains(int px, int py) {
            return px >= x && px <= x + ancho && py >= y && py <= y + alto;
        }
    }

    static final int CARD_H = 46;
    private static final int GAP = 3;
    private static final int PADDING = 5;
    private static final int ARC = 8;

    private static final Color[] PALETA = {
        new Color(52, 152, 219),
        new Color(46, 204, 113),
        new Color(231, 76, 60),
        new Color(155, 89, 182),
        new Color(230, 126, 34),
        new Color(26, 188, 156),
        new Color(52, 73, 94),
        new Color(241, 196, 15),};

    private static final Color FONDO_PANEL = new Color(245, 247, 250);
    private static final Font FUENTE = new Font("Century Gothic", Font.BOLD, 15);

    private final List<ModeloGrupo> grupos = new ArrayList<>();
    private ListenerGrupo listener;
    private int hover = -1;

    public PanelGruposCompacto() {
        setBackground(FONDO_PANEL);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                for (ModeloGrupo g : grupos) {
                    if (g.contains(e.getX(), e.getY()) && listener != null) {
                        listener.grupoSeleccionado(g.codigo, g.nombre);
                        break;
                    }
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (hover != -1) {
                    hover = -1;
                    repaint();
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
                if (prev != hover) {
                    repaint();
                }
            }
        });
    }

    public void setListener(ListenerGrupo l) {
        this.listener = l;
    }

    public void setGrupos(Object[][] datos) {
        grupos.clear();
        if (datos == null) {
            return;
        }
        for (int i = 0; i < datos.length; i++) {
            grupos.add(new ModeloGrupo(
                    datos[i][0].toString(),
                    datos[i][1].toString(),
                    i % PALETA.length
            ));
        }
        recalcular();
    }

    public int getTotalHeight() {
        int n = grupos.size();
        return n == 0 ? 0 : PADDING * 2 + n * CARD_H + Math.max(0, n - 1) * GAP;
    }

    private void recalcular() {
        int panelW = getWidth() > 0 ? getWidth() : 300;
        int cardW = panelW - PADDING * 2;
        for (int i = 0; i < grupos.size(); i++) {
            ModeloGrupo g = grupos.get(i);
            g.x = PADDING;
            g.y = PADDING + i * (CARD_H + GAP);
            g.ancho = cardW;
            g.alto = CARD_H;
        }
        int totalH = getTotalHeight();
        setPreferredSize(new Dimension(panelW, Math.max(totalH, CARD_H)));
        revalidate();
        repaint();
    }

    @Override
    public void setBounds(int x, int y, int w, int h) {
        super.setBounds(x, y, w, h);
        recalcular();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        for (int i = 0; i < grupos.size(); i++) {
            dibujar(g2, grupos.get(i), i == hover);
        }
    }

    private void dibujar(Graphics2D g2, ModeloGrupo g, boolean isHover) {
        int x = g.x, y = g.y, w = g.ancho, h = g.alto;

        Color base = PALETA[g.colorIdx];
        Color fondo = isHover ? base.darker() : base;

        // Sombra suave
        g2.setColor(new Color(0, 0, 0, 30));
        g2.fillRoundRect(x + 2, y + 2, w, h, ARC, ARC);

        // Fondo completo con el color del grupo
        g2.setColor(fondo);
        g2.fillRoundRect(x, y, w, h, ARC, ARC);

        // Brillo superior sutil
        g2.setColor(new Color(255, 255, 255, 40));
        g2.fillRoundRect(x, y, w, h / 2, ARC, ARC);

        // Texto en blanco, centrado verticalmente
        g2.setFont(FUENTE);
        g2.setColor(Color.WHITE);
        FontMetrics fm = g2.getFontMetrics();
        int textX = x + 10;
        int textW = w - 18;
        String texto = recortar(fm, g.nombre, textW);
        int textY = y + (h - fm.getHeight()) / 2 + fm.getAscent();
        g2.drawString(texto, textX, textY);
    }

    private String recortar(FontMetrics fm, String texto, int maxW) {
        if (fm.stringWidth(texto) <= maxW) {
            return texto;
        }
        String puntos = "...";
        while (texto.length() > 0 && fm.stringWidth(texto + puntos) > maxW) {
            texto = texto.substring(0, texto.length() - 1);
        }
        return texto + puntos;
    }
}
