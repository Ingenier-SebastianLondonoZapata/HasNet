package formularios.Oftalmologia;

import clases.Instancias;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class botones extends JLabel implements MouseListener {

    private Instancias instancias;
    private int tamano, opc = 0, x, y;
    private String dato;
    private pnlDibujo panel;
    private String comboBox, comboBox2;

    public String getComboBox2() {
        return comboBox2;
    }

    public void setComboBox2(String comboBox2) {
        this.comboBox2 = comboBox2;
    }

    public pnlDibujo getPanel() {
        return panel;
    }

    public void setPanel(pnlDibujo panel) {
        this.panel = panel;
    }

    public botones(int tamano, int x, int y) {
        this.tamano = tamano;
        this.x = x;
        this.y = y;
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.repaint();
        this.setSize(tamano + 2, tamano + 2);
        this.addMouseListener(this);
    }

    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {

        this.dato = dato;

        try {
            opc = Integer.parseInt(dato);
        } catch (Exception e) {
            opc = 0;
        }

        this.repaint();
    }

    public void cargarObservacion() {
        panel.setDatos(panel.getDatos());
    }

    @Override
    public void paint(Graphics g) {

        super.paint(g);
        g.setColor(Color.black);
//        g.setFont(new Font("aasd", Font.BOLD, opciones(0)[0]));

        if (opc == 0) {
            g.drawString(" ", opciones(0)[0], opciones(0)[2]);
        } else {
            if (dato.length() == 1) {
                g.setFont(new Font("aasd", Font.BOLD, opciones(1)[0]));
                g.drawString(dato, opciones(1)[1], opciones(1)[2]);
            } else if (dato.length() == 2) {
                g.setFont(new Font("aasd", Font.BOLD, opciones(2)[0]));
                g.drawString(dato, opciones(2)[1], opciones(2)[2]);
            } else if (dato.length() == 3) {
                g.setFont(new Font("aasd", Font.BOLD, opciones(3)[0]));
                g.drawString(dato, opciones(3)[1], opciones(3)[2]);
            } else if (dato.length() == 4) {
                g.setFont(new Font("aasd", Font.BOLD, opciones(4)[0]));
                g.drawString(dato, opciones(4)[1], opciones(4)[2]);
            } else if (dato.length() == 5) {
                g.setFont(new Font("aasd", Font.BOLD, opciones(5)[0]));
                g.drawString(dato, opciones(5)[1], opciones(5)[2]);
            }
        }

        if (x == 0) {
            g.drawOval(1, 1, tamano, tamano);
        } else {
            g.drawOval(1, 1, x, y);
        }
    }

    public int[] opciones(int op) {

        int opc[] = new int[3];

        if (op == 1) {
            opc[0] = 25;
            opc[1] = 10;
            opc[2] = 25;
        } else if (op == 2) {
            opc[0] = 20;
            opc[1] = 8;
            opc[2] = 25;
        } else if (op == 3) {
            opc[0] = 15;
            opc[1] = 5;
            opc[2] = 25;
        } else if (op == 4) {
            opc[0] = 12;
            opc[1] = 3;
            opc[2] = 25;
        } else if (op == 5) {
            opc[0] = 10;
            opc[1] = 1;
            opc[2] = 25;
        }
        return opc;
    }

    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setLayout(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(500, 500);
        f.setVisible(true);

        botones b = new botones(35, 0, 0);

        b.getPreferredSize();
        b.setBounds(10, 10, b.getWidth(), b.getHeight());
        f.add(b);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        opcionesDibujo o = new opcionesDibujo(null, true);
        o.setLocationRelativeTo(null);
        o.setActual(this);
        o.setVisible(true);
        if (panel.getActual() != null) {
            int cant = panel.cantidad();
            if (dato.equals("") && cant > 0) {
                cant--;
            }
            panel.getActual().setText(cant + "");
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    public void setInstancias(Instancias instancias) {
        this.instancias = instancias;
    }
}
