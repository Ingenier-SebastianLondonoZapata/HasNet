package Utilidades;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class PanelZoomImagen extends JPanel {

    private static final double ZOOM_MINIMO = 0.05;
    private static final double ZOOM_MAXIMO = 8.0;
    private static final double FACTOR_ZOOM = 1.25;

    private final Lienzo lienzo = new Lienzo();
    private final JScrollPane scroll = new JScrollPane(lienzo);
    private final JLabel lbZoom = new JLabel("100%", SwingConstants.CENTER);
    private final JLabel lbMensaje = new JLabel("", SwingConstants.CENTER);
    private final JPanel controles = new JPanel(new FlowLayout(FlowLayout.CENTER, 6, 4));

    private BufferedImage imagen;
    private double zoom = 1.0;
    private boolean ajustarAlMostrar = true;

    public PanelZoomImagen() {
        super(new BorderLayout());
        setBackground(Color.WHITE);

        scroll.setBorder(BorderFactory.createLineBorder(new Color(210, 210, 210)));
        scroll.getViewport().setBackground(new Color(235, 235, 235));
        scroll.getVerticalScrollBar().setUnitIncrement(20);
        scroll.getHorizontalScrollBar().setUnitIncrement(20);
        scroll.setWheelScrollingEnabled(false);

        lbMensaje.setFont(new Font("Century Gothic", Font.PLAIN, 16));
        lbZoom.setPreferredSize(new Dimension(60, 26));

        controles.setBackground(Color.WHITE);
        controles.add(crearBoton("−", "Alejar", () -> cambiarZoom(zoom / FACTOR_ZOOM, null)));
        controles.add(lbZoom);
        controles.add(crearBoton("+", "Acercar", () -> cambiarZoom(zoom * FACTOR_ZOOM, null)));
        controles.add(crearBoton("Ajustar", "Ajustar la foto a la ventana", this::ajustar));
        controles.add(crearBoton("100%", "Tamaño real", () -> cambiarZoom(1.0, null)));

        add(scroll, BorderLayout.CENTER);
        add(controles, BorderLayout.SOUTH);

        instalarEventos();
    }

    public boolean cargar(File archivo) {
        BufferedImage leida = null;
        if (archivo != null && archivo.isFile()) {
            try {
                leida = ImageIO.read(archivo);
            } catch (IOException e) {
                leida = null;
            }
        }
        setImagen(leida, archivo != null && archivo.isFile() ? "No se pudo cargar la foto" : "Sin foto");
        return leida != null;
    }

    public void setImagen(BufferedImage nuevaImagen, String mensajeSinImagen) {
        imagen = nuevaImagen;
        boolean hayImagen = imagen != null && imagen.getWidth() > 0 && imagen.getHeight() > 0;

        remove(scroll);
        remove(lbMensaje);
        if (hayImagen) {
            add(scroll, BorderLayout.CENTER);
            ajustarAlMostrar = true;
            SwingUtilities.invokeLater(this::ajustar);
        } else {
            imagen = null;
            lbMensaje.setText(mensajeSinImagen);
            add(lbMensaje, BorderLayout.CENTER);
        }
        controles.setVisible(hayImagen);
        revalidate();
        repaint();
    }

    public void ajustar() {
        if (imagen == null) {
            return;
        }
        Dimension disponible = scroll.getViewport().getExtentSize();
        if (disponible.width <= 0 || disponible.height <= 0) {
            ajustarAlMostrar = true;
            return;
        }
        ajustarAlMostrar = false;
        double escala = Math.min((double) disponible.width / imagen.getWidth(), (double) disponible.height / imagen.getHeight());
        cambiarZoom(Math.min(1.0, escala), null);
    }

    private void cambiarZoom(double nuevoZoom, Point anclaEnLienzo) {
        if (imagen == null) {
            return;
        }
        nuevoZoom = Math.max(ZOOM_MINIMO, Math.min(ZOOM_MAXIMO, nuevoZoom));

        JViewport viewport = scroll.getViewport();
        Rectangle vista = viewport.getViewRect();
        Point ancla = anclaEnLienzo != null ? anclaEnLienzo
                : new Point(vista.x + vista.width / 2, vista.y + vista.height / 2);
        Point anclaEnVista = new Point(ancla.x - vista.x, ancla.y - vista.y);

        double proporcion = nuevoZoom / zoom;
        zoom = nuevoZoom;
        lbZoom.setText(Math.round(zoom * 100) + "%");

        lienzo.revalidate();
        scroll.validate();

        int x = (int) Math.round(ancla.x * proporcion) - anclaEnVista.x;
        int y = (int) Math.round(ancla.y * proporcion) - anclaEnVista.y;
        Dimension tamano = lienzo.getPreferredSize();
        x = Math.max(0, Math.min(x, tamano.width - vista.width));
        y = Math.max(0, Math.min(y, tamano.height - vista.height));
        viewport.setViewPosition(new Point(x, y));
        lienzo.repaint();
    }

    private void instalarEventos() {
        MouseAdapter raton = new MouseAdapter() {
            private Point inicio;

            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                double nuevo = e.getWheelRotation() < 0 ? zoom * FACTOR_ZOOM : zoom / FACTOR_ZOOM;
                cambiarZoom(nuevo, e.getPoint());
            }

            @Override
            public void mousePressed(MouseEvent e) {
                inicio = e.getLocationOnScreen();
                lienzo.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                inicio = null;
                lienzo.setCursor(Cursor.getDefaultCursor());
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                if (inicio == null) {
                    return;
                }
                Point actual = e.getLocationOnScreen();
                JViewport viewport = scroll.getViewport();
                Point posicion = viewport.getViewPosition();
                Dimension tamano = lienzo.getPreferredSize();
                Dimension extension = viewport.getExtentSize();
                int x = Math.max(0, Math.min(posicion.x - (actual.x - inicio.x), tamano.width - extension.width));
                int y = Math.max(0, Math.min(posicion.y - (actual.y - inicio.y), tamano.height - extension.height));
                viewport.setViewPosition(new Point(x, y));
                inicio = actual;
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    Dimension disponible = scroll.getViewport().getExtentSize();
                    double ajuste = imagen == null ? 1.0 : Math.min(1.0, Math.min(
                            (double) disponible.width / imagen.getWidth(), (double) disponible.height / imagen.getHeight()));
                    if (Math.abs(zoom - ajuste) < 0.001) {
                        cambiarZoom(1.0, e.getPoint());
                    } else {
                        ajustar();
                    }
                }
            }
        };
        lienzo.addMouseListener(raton);
        lienzo.addMouseMotionListener(raton);
        lienzo.addMouseWheelListener(raton);

        scroll.getViewport().addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                if (ajustarAlMostrar) {
                    ajustar();
                } else {
                    lienzo.revalidate();
                    lienzo.repaint();
                }
            }
        });
    }

    public static boolean mostrarEnEtiqueta(JLabel etiqueta, File archivo) {
        BufferedImage foto = null;
        if (archivo != null && archivo.isFile()) {
            try {
                foto = ImageIO.read(archivo);
            } catch (IOException e) {
                foto = null;
            }
        }

        if (foto == null || foto.getWidth() <= 0 || foto.getHeight() <= 0) {
            etiqueta.setIcon(null);
            etiqueta.repaint();
            return false;
        }

        int anchoMax = etiqueta.getWidth() > 0 ? etiqueta.getWidth() : etiqueta.getPreferredSize().width;
        int altoMax = etiqueta.getHeight() > 0 ? etiqueta.getHeight() : etiqueta.getPreferredSize().height;
        double escala = Math.min(1.0, Math.min((double) anchoMax / foto.getWidth(), (double) altoMax / foto.getHeight()));
        int ancho = Math.max(1, (int) Math.round(foto.getWidth() * escala));
        int alto = Math.max(1, (int) Math.round(foto.getHeight() * escala));

        etiqueta.setHorizontalAlignment(SwingConstants.CENTER);
        etiqueta.setVerticalAlignment(SwingConstants.CENTER);
        etiqueta.setIcon(new javax.swing.ImageIcon(foto.getScaledInstance(ancho, alto, java.awt.Image.SCALE_SMOOTH)));
        etiqueta.repaint();
        return true;
    }

    private JButton crearBoton(String texto, String ayuda, Runnable accion) {
        JButton boton = new JButton(texto);
        boton.setToolTipText(ayuda);
        boton.setFocusable(false);
        boton.addActionListener(e -> accion.run());
        return boton;
    }

    private class Lienzo extends JPanel {

        Lienzo() {
            setOpaque(false);
        }

        @Override
        public Dimension getPreferredSize() {
            if (imagen == null) {
                return new Dimension(1, 1);
            }
            Dimension disponible = scroll.getViewport().getExtentSize();
            int ancho = (int) Math.round(imagen.getWidth() * zoom);
            int alto = (int) Math.round(imagen.getHeight() * zoom);
            return new Dimension(Math.max(ancho, disponible.width), Math.max(alto, disponible.height));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (imagen == null) {
                return;
            }
            int ancho = (int) Math.round(imagen.getWidth() * zoom);
            int alto = (int) Math.round(imagen.getHeight() * zoom);
            int x = Math.max(0, (getWidth() - ancho) / 2);
            int y = Math.max(0, (getHeight() - alto) / 2);

            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, zoom >= 2.0
                    ? RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR
                    : RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2.drawImage(imagen, x, y, ancho, alto, null);
            g2.dispose();
        }
    }
}
