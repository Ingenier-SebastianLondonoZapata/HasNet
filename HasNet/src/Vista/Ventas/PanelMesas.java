package Vista.Ventas;

import Modelo.Ventas.ModeloMesa;
import clases.big;
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
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

public class PanelMesas extends JPanel {

    public interface ListenerMesa {
        void mesaSeleccionada(ModeloMesa mesa);
    }

    private static final int CARD_W = 150;
    private static final int CARD_H = 130;
    private static final int GAP    = 20;
    private static final int PADDING = 25;

    private List<ModeloMesa> mesas = new ArrayList<>();
    private ListenerMesa listener;

    public PanelMesas() {
        setBackground(new Color(230, 233, 238));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                for (ModeloMesa mesa : mesas) {
                    if (mesa.contains(e.getX(), e.getY())) {
                        if (listener != null) {
                            listener.mesaSeleccionada(mesa);
                        }
                        break;
                    }
                }
            }
        });
    }

    public void setListener(ListenerMesa listener) {
        this.listener = listener;
    }

    public void setDimensiones(int filas, int columnas) {
        int w = PADDING * 2 + columnas * CARD_W + Math.max(0, columnas - 1) * GAP;
        int h = PADDING * 2 + filas * CARD_H + Math.max(0, filas - 1) * GAP;
        setPreferredSize(new Dimension(Math.max(w, 300), Math.max(h, 200)));
        revalidate();
    }

    public void setMesas(List<ModeloMesa> mesas) {
        this.mesas = mesas;
        for (ModeloMesa mesa : mesas) {
            int px = PADDING + mesa.getColumna() * (CARD_W + GAP);
            int py = PADDING + mesa.getFila() * (CARD_H + GAP);
            mesa.setPixelX(px);
            mesa.setPixelY(py);
            mesa.setAncho(CARD_W);
            mesa.setAlto(CARD_H);
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,      RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING,         RenderingHints.VALUE_RENDER_QUALITY);

        for (ModeloMesa mesa : mesas) {
            dibujarMesa(g2, mesa);
        }
    }

    private void dibujarMesa(Graphics2D g2, ModeloMesa mesa) {
        int x = mesa.getPixelX();
        int y = mesa.getPixelY();

        // Sombra difusa
        for (int i = 4; i >= 1; i--) {
            g2.setColor(new Color(0, 0, 0, 12 * i));
            g2.fill(new RoundRectangle2D.Float(x + i, y + i, CARD_W, CARD_H, 18, 18));
        }

        // Fondo con gradiente según estado
        Color colorTop, colorBot;
        if (mesa.isOcupada()) {
            colorTop = new Color(231, 76, 60);
            colorBot = new Color(192, 57, 43);
        } else {
            colorTop = new Color(46, 204, 113);
            colorBot = new Color(39, 174, 96);
        }
        g2.setPaint(new GradientPaint(x, y, colorTop, x, y + CARD_H, colorBot));
        g2.fill(new RoundRectangle2D.Float(x, y, CARD_W, CARD_H, 18, 18));

        // Brillo superior sutil
        g2.setPaint(new GradientPaint(x, y, new Color(255, 255, 255, 55), x, y + 28, new Color(255, 255, 255, 0)));
        g2.fill(new RoundRectangle2D.Float(x, y, CARD_W, 28, 18, 18));

        // Borde
        g2.setColor(mesa.isOcupada() ? new Color(140, 35, 25, 160) : new Color(25, 120, 60, 160));
        g2.setStroke(new BasicStroke(1.2f));
        g2.draw(new RoundRectangle2D.Float(x, y, CARD_W, CARD_H, 18, 18));

        // Icono de mesa
        dibujarIconoMesa(g2, x + CARD_W / 2, y + 40);

        // Línea separadora
        g2.setColor(new Color(255, 255, 255, 45));
        g2.setStroke(new BasicStroke(1f));
        g2.drawLine(x + 18, y + 70, x + CARD_W - 18, y + 70);

        // Textos
        g2.setColor(Color.WHITE);
        String nombre = mesa.getNombre();

        if (mesa.isOcupada()) {
            // Nombre
            g2.setFont(new Font("Century Gothic", Font.BOLD, 13));
            FontMetrics fm = g2.getFontMetrics();
            g2.drawString(nombre, x + (CARD_W - fm.stringWidth(nombre)) / 2, y + 86);

            // Total
            g2.setFont(new Font("Century Gothic", Font.PLAIN, 11));
            fm = g2.getFontMetrics();
            String totalStr = big.setMonedaExacta(mesa.getTotal());
            g2.drawString(totalStr, x + (CARD_W - fm.stringWidth(totalStr)) / 2, y + 102);

            // Turno
            if (mesa.getTurno() != null && !mesa.getTurno().isEmpty()) {
                g2.setFont(new Font("Century Gothic", Font.PLAIN, 10));
                fm = g2.getFontMetrics();
                g2.setColor(new Color(255, 210, 200));
                String turnoStr = "Turno " + mesa.getTurno();
                g2.drawString(turnoStr, x + (CARD_W - fm.stringWidth(turnoStr)) / 2, y + 118);
            }
        } else {
            // Nombre
            g2.setFont(new Font("Century Gothic", Font.BOLD, 13));
            FontMetrics fm = g2.getFontMetrics();
            g2.drawString(nombre, x + (CARD_W - fm.stringWidth(nombre)) / 2, y + 90);

            // Estado
            g2.setFont(new Font("Century Gothic", Font.PLAIN, 10));
            fm = g2.getFontMetrics();
            g2.setColor(new Color(195, 255, 218));
            String disp = "Disponible";
            g2.drawString(disp, x + (CARD_W - fm.stringWidth(disp)) / 2, y + 108);
        }
    }

    private void dibujarIconoMesa(Graphics2D g2, int cx, int cy) {
        // Superficie de la mesa (óvalo)
        g2.setColor(new Color(255, 255, 255, 100));
        g2.fillOval(cx - 22, cy - 12, 44, 24);

        // Sillas (rectángulos redondeados alrededor de la mesa)
        g2.setColor(new Color(255, 255, 255, 130));
        g2.fillRoundRect(cx - 9,  cy - 27, 18, 14, 6, 6); // arriba
        g2.fillRoundRect(cx - 9,  cy + 13, 18, 14, 6, 6); // abajo
        g2.fillRoundRect(cx - 35, cy - 9,  14, 18, 6, 6); // izquierda
        g2.fillRoundRect(cx + 21, cy - 9,  14, 18, 6, 6); // derecha
    }
}
