package Vista.Restaurante;

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
import java.awt.Stroke;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

public class PanelMesas extends JPanel {

    public enum TipoPanel { RESTAURANTE, SUPERMERCADO }

    public interface ListenerMesa {
        void mesaSeleccionada(ModeloMesa mesa);
    }

    private static final int CARD_W  = 150;
    private static final int CARD_H  = 130;
    private static final int GAP     = 20;
    private static final int PADDING = 25;

    // Paleta RESTAURANTE — esmeralda/jade + vino tinto
    private static final Color REST_AVAIL_TOP  = new Color(22,  160, 133);   // #16A085 esmeralda
    private static final Color REST_AVAIL_BOT  = new Color(17,  122, 101);   // #117A65 jade oscuro
    private static final Color REST_OCUP_TOP   = new Color(169, 50,  38);    // #A93226 vino tinto
    private static final Color REST_OCUP_BOT   = new Color(123, 36,  28);    // #7B241C vino profundo
    private static final Color REST_BG         = new Color(248, 245, 240);   // crema cálido

    // Paleta SUPERMERCADO
    private static final Color SUPER_AVAIL_TOP = new Color(52,  152, 219);
    private static final Color SUPER_AVAIL_BOT = new Color(41,  128, 185);
    private static final Color SUPER_OCUP_TOP  = new Color(230, 126, 34);
    private static final Color SUPER_OCUP_BOT  = new Color(202, 111, 30);
    private static final Color SUPER_BG        = new Color(232, 244, 253);

    private List<ModeloMesa> mesas = new ArrayList<>();
    private ListenerMesa listener;
    private TipoPanel tipo = TipoPanel.RESTAURANTE;

    public PanelMesas() {
        setBackground(REST_BG);
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

    public void setTipo(TipoPanel tipo) {
        this.tipo = tipo;
        setBackground(tipo == TipoPanel.SUPERMERCADO ? SUPER_BG : REST_BG);
        repaint();
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
            dibujarTarjeta(g2, mesa);
        }
    }

    private void dibujarTarjeta(Graphics2D g2, ModeloMesa mesa) {
        int x = mesa.getPixelX();
        int y = mesa.getPixelY();

        // Sombra difusa
        for (int i = 4; i >= 1; i--) {
            g2.setColor(new Color(0, 0, 0, 12 * i));
            g2.fill(new RoundRectangle2D.Float(x + i, y + i, CARD_W, CARD_H, 18, 18));
        }

        // Colores según tipo y estado
        Color colorTop, colorBot;
        if (tipo == TipoPanel.SUPERMERCADO) {
            colorTop = mesa.isOcupada() ? SUPER_OCUP_TOP : SUPER_AVAIL_TOP;
            colorBot = mesa.isOcupada() ? SUPER_OCUP_BOT : SUPER_AVAIL_BOT;
        } else {
            colorTop = mesa.isOcupada() ? REST_OCUP_TOP : REST_AVAIL_TOP;
            colorBot = mesa.isOcupada() ? REST_OCUP_BOT : REST_AVAIL_BOT;
        }

        // Fondo con gradiente
        g2.setPaint(new GradientPaint(x, y, colorTop, x, y + CARD_H, colorBot));
        g2.fill(new RoundRectangle2D.Float(x, y, CARD_W, CARD_H, 18, 18));

        // Brillo superior
        g2.setPaint(new GradientPaint(x, y, new Color(255, 255, 255, 55), x, y + 28, new Color(255, 255, 255, 0)));
        g2.fill(new RoundRectangle2D.Float(x, y, CARD_W, 28, 18, 18));

        // Borde
        Color borderColor;
        if (tipo == TipoPanel.SUPERMERCADO) {
            borderColor = mesa.isOcupada()
                    ? new Color(160, 78,  18, 160)
                    : new Color(20,  90, 145, 160);
        } else {
            borderColor = mesa.isOcupada()
                    ? new Color(90,  22,  16, 160)   // vino oscuro
                    : new Color(12,  88,  73, 160);   // jade oscuro
        }
        g2.setColor(borderColor);
        g2.setStroke(new BasicStroke(1.2f));
        g2.draw(new RoundRectangle2D.Float(x, y, CARD_W, CARD_H, 18, 18));

        // Icono
        if (tipo == TipoPanel.SUPERMERCADO) {
            dibujarIconoCarrito(g2, x + CARD_W / 2, y + 40);
        } else {
            dibujarIconoRestaurante(g2, x + CARD_W / 2, y + 40);
        }

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
            if (mesa.getTurno() != null && !mesa.getTurno().isEmpty() && Integer.parseInt(mesa.getTurno()) > 0) {
                g2.setFont(new Font("Century Gothic", Font.PLAIN, 10));
                fm = g2.getFontMetrics();
                g2.setColor(tipo == TipoPanel.SUPERMERCADO
                        ? new Color(255, 230, 190)   // durazno cálido
                        : new Color(250, 200, 200));  // rosado vinoso
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
            g2.setColor(tipo == TipoPanel.SUPERMERCADO
                    ? new Color(190, 225, 255)   // azul claro
                    : new Color(179, 229, 220));  // jade claro
            String disp = "Disponible";
            g2.drawString(disp, x + (CARD_W - fm.stringWidth(disp)) / 2, y + 108);
        }
    }

    /** Tenedor y cuchillo — símbolo universal de restaurante. */
    private void dibujarIconoRestaurante(Graphics2D g2, int cx, int cy) {
        g2.setColor(new Color(255, 255, 255, 140));

        // ── TENEDOR (izquierda) ────────────────────────────
        int fx = cx - 12;
        // Tres varillas
        g2.fillRoundRect(fx - 6, cy - 22, 4, 17, 3, 3);
        g2.fillRoundRect(fx - 1, cy - 22, 4, 17, 3, 3);
        g2.fillRoundRect(fx + 4, cy - 22, 4, 17, 3, 3);
        // Cuello que une varillas con la manija
        g2.fillOval(fx - 7, cy - 7, 16, 9);
        // Manija
        g2.fillRoundRect(fx - 3, cy + 1, 6, 21, 4, 4);

        // ── CUCHILLO (derecha) ────────────────────────────
        int kx = cx + 13;
        // Hoja (triángulo: espina recta a la izquierda, filo diagonal a la derecha)
        int[] bx = { kx,      kx + 8, kx };
        int[] by = { cy - 22, cy - 3, cy - 3 };
        g2.fillPolygon(bx, by, 3);
        // Manija
        g2.fillRoundRect(kx - 3, cy + 1, 6, 21, 4, 4);
    }

    /** Carrito de supermercado visto de perfil. */
    private void dibujarIconoCarrito(Graphics2D g2, int cx, int cy) {
        Stroke original = g2.getStroke();
        g2.setStroke(new BasicStroke(2.4f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

        // Cuerpo (trapecio: más ancho abajo para dar perspectiva)
        int[] xBody = { cx - 14, cx + 16, cx + 20, cx - 20 };
        int[] yBody = { cy -  8, cy -  8, cy + 10, cy + 10 };
        g2.setColor(new Color(255, 255, 255, 100));
        g2.fillPolygon(xBody, yBody, 4);

        g2.setColor(new Color(255, 255, 255, 155));
        g2.drawPolygon(xBody, yBody, 4);

        // Mango: barra diagonal desde esquina superior-izquierda del cesto
        g2.drawLine(cx - 14, cy -  8, cx - 25, cy - 20); // diagonal
        g2.drawLine(cx - 25, cy - 20, cx -  8, cy - 20); // empuñadura horizontal

        g2.setStroke(original);

        // Ruedas
        g2.setColor(new Color(255, 255, 255, 160));
        g2.fillOval(cx - 19, cy + 10, 11, 11); // rueda izquierda
        g2.fillOval(cx +  8, cy + 10, 11, 11); // rueda derecha
    }
}
