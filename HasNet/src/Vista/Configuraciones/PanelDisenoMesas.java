package Vista.Configuraciones;

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

public class PanelDisenoMesas extends JPanel {

    public interface ListenerCambio {
        void onCambioCantidad(int totalMesas);
    }

    private static final int CELL_W  = 120;
    private static final int CELL_H  = 100;
    private static final int GAP     = 12;
    private static final int PADDING = 15;

    private String[][] grid;
    private int filas    = 0;
    private int columnas = 0;
    private int contador = 0;

    private ListenerCambio listener;

    public PanelDisenoMesas() {
        setBackground(new Color(230, 233, 238));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                manejarClick(e.getX(), e.getY());
            }
        });
    }

    public void setListener(ListenerCambio listener) {
        this.listener = listener;
    }

    public void setDimensiones(int filas, int columnas) {
        this.filas    = filas;
        this.columnas = columnas;
        this.grid     = new String[filas][columnas];
        this.contador = 0;

        int w = PADDING * 2 + columnas * CELL_W + Math.max(0, columnas - 1) * GAP;
        int h = PADDING * 2 + filas * CELL_H + Math.max(0, filas - 1) * GAP;
        setPreferredSize(new Dimension(Math.max(w, 300), Math.max(h, 200)));
        revalidate();
        repaint();

        if (listener != null) {
            listener.onCambioCantidad(0);
        }
    }

    /**
     * Carga mesas ya existentes en la base de datos al abrir la configuración.
     * Espera que setDimensiones() haya sido llamado antes.
     * mesas[][0] = "rawFila,columna", mesas[][1] = nombre.
     */
    public void cargarMesasExistentes(Object[][] mesas) {
        if (grid == null) {
            return;
        }
        contador = 0;
        for (int i = 0; i < mesas.length; i++) {
            String[] coords = mesas[i][0].toString().split(",");
            int rawFila = Integer.parseInt(coords[0]);
            int columna = Integer.parseInt(coords[1]);
            int fila = (rawFila - 1) / 2;
            if (fila >= 0 && fila < filas && columna >= 0 && columna < this.columnas) {
                grid[fila][columna] = mesas[i][1].toString();
                contador++;
            }
        }
        repaint();
    }

    /** Devuelve pares {ubicacion_raw, nombre} compatibles con bdMesas. */
    public List<String[]> getMesasParaGuardar() {
        List<String[]> result = new ArrayList<String[]>();
        if (grid == null) {
            return result;
        }
        for (int f = 0; f < filas; f++) {
            for (int c = 0; c < columnas; c++) {
                if (grid[f][c] != null) {
                    // formato heredado: (2*fila+1),columna
                    String ubicacion = (2 * f + 1) + "," + c;
                    result.add(new String[]{ubicacion, grid[f][c]});
                }
            }
        }
        return result;
    }

    private void manejarClick(int px, int py) {
        if (grid == null) {
            return;
        }
        for (int f = 0; f < filas; f++) {
            for (int c = 0; c < columnas; c++) {
                int x = PADDING + c * (CELL_W + GAP);
                int y = PADDING + f * (CELL_H + GAP);
                if (px >= x && px <= x + CELL_W && py >= y && py <= y + CELL_H) {
                    if (grid[f][c] == null) {
                        contador++;
                        grid[f][c] = "Mesa. " + contador;
                    } else {
                        grid[f][c] = null;
                        contador--;
                    }
                    repaint();
                    if (listener != null) {
                        listener.onCambioCantidad(contador);
                    }
                    return;
                }
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,      RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING,         RenderingHints.VALUE_RENDER_QUALITY);

        if (grid == null) {
            return;
        }

        for (int f = 0; f < filas; f++) {
            for (int c = 0; c < columnas; c++) {
                int x = PADDING + c * (CELL_W + GAP);
                int y = PADDING + f * (CELL_H + GAP);
                if (grid[f][c] != null) {
                    dibujarMesaColocada(g2, x, y, grid[f][c]);
                } else {
                    dibujarCeldaVacia(g2, x, y);
                }
            }
        }
    }

    private void dibujarCeldaVacia(Graphics2D g2, int x, int y) {
        // Fondo blanco tenue
        g2.setColor(new Color(248, 250, 252));
        g2.fill(new RoundRectangle2D.Float(x, y, CELL_W, CELL_H, 14, 14));

        // Borde punteado
        float[] dash = {6f, 4f};
        g2.setStroke(new BasicStroke(1.5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 10f, dash, 0f));
        g2.setColor(new Color(175, 188, 205));
        g2.draw(new RoundRectangle2D.Float(x, y, CELL_W, CELL_H, 14, 14));

        // Icono "+"
        g2.setStroke(new BasicStroke(2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2.setColor(new Color(155, 170, 192));
        int cx = x + CELL_W / 2;
        int cy = y + CELL_H / 2 - 8;
        g2.drawLine(cx - 12, cy, cx + 12, cy);
        g2.drawLine(cx, cy - 12, cx, cy + 12);

        // Texto "Agregar mesa"
        g2.setFont(new Font("Century Gothic", Font.PLAIN, 9));
        FontMetrics fm = g2.getFontMetrics();
        g2.setColor(new Color(160, 175, 195));
        String hint = "Agregar mesa";
        g2.drawString(hint, x + (CELL_W - fm.stringWidth(hint)) / 2, y + CELL_H - 12);
    }

    private void dibujarMesaColocada(Graphics2D g2, int x, int y, String nombre) {
        // Sombra
        g2.setColor(new Color(0, 0, 0, 28));
        g2.fill(new RoundRectangle2D.Float(x + 3, y + 3, CELL_W, CELL_H, 14, 14));

        // Gradiente verde
        g2.setPaint(new GradientPaint(x, y, new Color(52, 168, 100), x, y + CELL_H, new Color(34, 139, 75)));
        g2.fill(new RoundRectangle2D.Float(x, y, CELL_W, CELL_H, 14, 14));

        // Brillo superior
        g2.setPaint(new GradientPaint(x, y, new Color(255, 255, 255, 55), x, y + 24, new Color(255, 255, 255, 0)));
        g2.fill(new RoundRectangle2D.Float(x, y, CELL_W, 24, 14, 14));

        // Borde
        g2.setStroke(new BasicStroke(1f));
        g2.setColor(new Color(25, 110, 55, 150));
        g2.draw(new RoundRectangle2D.Float(x, y, CELL_W, CELL_H, 14, 14));

        // Icono de mesa + sillas
        dibujarIcono(g2, x + CELL_W / 2, y + 35);

        // Separador
        g2.setColor(new Color(255, 255, 255, 40));
        g2.drawLine(x + 14, y + 58, x + CELL_W - 14, y + 58);

        // Nombre
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Century Gothic", Font.BOLD, 12));
        FontMetrics fm = g2.getFontMetrics();
        g2.drawString(nombre, x + (CELL_W - fm.stringWidth(nombre)) / 2, y + 74);

        // Pista "Clic para quitar"
        g2.setFont(new Font("Century Gothic", Font.PLAIN, 9));
        fm = g2.getFontMetrics();
        g2.setColor(new Color(195, 255, 215));
        String hint = "Clic para quitar";
        g2.drawString(hint, x + (CELL_W - fm.stringWidth(hint)) / 2, y + 90);
    }

    private void dibujarIcono(Graphics2D g2, int cx, int cy) {
        g2.setColor(new Color(255, 255, 255, 100));
        g2.fillOval(cx - 16, cy - 9, 32, 18);
        g2.setColor(new Color(255, 255, 255, 130));
        g2.fillRoundRect(cx - 7, cy - 19, 14, 10, 5, 5);
        g2.fillRoundRect(cx - 7, cy + 9,  14, 10, 5, 5);
        g2.fillRoundRect(cx - 26, cy - 7, 10, 14, 5, 5);
        g2.fillRoundRect(cx + 16, cy - 7, 10, 14, 5, 5);
    }
}
