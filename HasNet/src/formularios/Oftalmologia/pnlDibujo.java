package formularios.Oftalmologia;

import clases.Instancias;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import formularios.Oftalmologia.botones;

public class pnlDibujo extends JPanel {

    private Instancias instancias;
    private botones botones[];
    private JTextField actual;

    public JTextField getActual() {
        return actual;
    }

    public void setActual(JTextField actual) {
        this.actual = actual;
    }

    public pnlDibujo() {
        this.setSize(900, 483);
        this.setLayout(null);
        this.repaint();
        botones = new botones[34];
//        botones = new botones[46];
        botones();
    }

    public botones[] getBotones() {
        return botones;
    }

    public String getDatos() {
        String aux = "";
        for (botones b : botones) {
            try {
                if (b.getComboBox2().equals(" ")) {
                    aux = aux + b.getDato() + "&";
                } else {
                    aux = aux + b.getDato() + "&";
                }
            } catch (Exception e) {
                aux = aux + b.getDato() + "&";
            }
        }
        return aux;
    }

    public String getDatos2() {
        String aux = "";
        String dato = "";
        for (botones b : botones) {
            if (b.getDato().equals("0")) {
                dato = "0";
            } else {
                dato = b.getDato();
            }
            aux = aux + dato + "&";
        }
        return aux;
    }

    public void setDatos(String datos) {
        String aux[] = datos.split("&");
        for (int i = 0; i < botones.length; i++) {
            botones[i].setDato(aux[i]);
        }
    }

    public void limpiar() {
        for (int i = 0; i < botones.length; i++) {
            botones[i].setDato("0");
        }
    }

    public void botones() {

        //DOS DE ARRIBA SIN CENTRAL
        botones[0] = new botones(35, 0, 0);
        botones[0].setBounds(86, 108, 40, 40);
        botones[2] = new botones(30, 0, 0);
        botones[2].setBounds(315, 111, 40, 40);
        botones[1] = new botones(30, 0, 0);
        botones[1].setBounds(511, 112, 40, 40);
        botones[3] = new botones(30, 0, 0);
        botones[3].setBounds(737, 114, 40, 40);

        //DOS DE ABAJO SIN CENTRAL
        botones[22] = new botones(35, 0, 0);
        botones[22].setBounds(79, 328, 40, 40);
        botones[23] = new botones(30, 0, 0);
        botones[23].setBounds(319, 334, 40, 40);
        botones[24] = new botones(30, 0, 0);
        botones[24].setBounds(504, 332, 40, 40);
        botones[25] = new botones(30, 0, 0);
        botones[25].setBounds(740, 337, 40, 40);

        //LIDEAS CENTRALES ABAJANDO
        botones[4] = new botones(30, 0, 0);
        botones[4].setBounds(203, 71, 40, 40);
        botones[5] = new botones(30, 0, 0);
        botones[5].setBounds(203, 145, 40, 40);
        botones[7] = new botones(30, 0, 0);
        botones[7].setBounds(202, 299, 40, 40);
        botones[8] = new botones(30, 0, 0);
        botones[8].setBounds(202, 382, 40, 40);
        botones[18] = new botones(30, 0, 0);
        botones[18].setBounds(625, 72, 40, 40);
        botones[19] = new botones(30, 0, 0);
        botones[19].setBounds(625, 147, 40, 40);
        botones[20] = new botones(30, 0, 0);
        botones[20].setBounds(624, 300, 40, 40);
        botones[21] = new botones(30, 0, 0);
        botones[21].setBounds(625, 384, 40, 40);

        //LINEAS CENTRALES
        botones[9] = new botones(30, 0, 0);
        botones[9].setBounds(39, 219, 40, 40);
        botones[10] = new botones(30, 0, 0);
        botones[10].setBounds(122, 219, 40, 40);
        botones[6] = new botones(30, 0, 0);
        botones[6].setBounds(203, 222, 40, 40);
        botones[11] = new botones(30, 0, 0);
        botones[11].setBounds(278, 219, 40, 40);
        botones[12] = new botones(30, 0, 0);
        botones[12].setBounds(363, 220, 40, 40);
        botones[13] = new botones(30, 0, 0);
        botones[13].setBounds(462, 221, 40, 40);
        botones[14] = new botones(30, 0, 0);
        botones[14].setBounds(545, 222, 40, 40);
        botones[15] = new botones(30, 0, 0);
        botones[15].setBounds(625, 224, 40, 40);
        botones[16] = new botones(30, 0, 0);
        botones[16].setBounds(702, 221, 40, 40);
        botones[17] = new botones(30, 0, 0);
        botones[17].setBounds(785, 222, 40, 40);

        //CUADRADO DEL CENTRO       
        botones[26] = new botones(30, 0, 0);
        botones[26].setBounds(144, 165, 40, 40);
        botones[27] = new botones(30, 0, 0);
        botones[27].setBounds(142, 275, 40, 40);
        botones[28] = new botones(30, 0, 0);
        botones[28].setBounds(258, 166, 40, 40);
        botones[29] = new botones(30, 0, 0);
        botones[29].setBounds(258, 275, 40, 40);
        botones[30] = new botones(30, 0, 0);
        botones[30].setBounds(567, 167, 40, 40);
        botones[31] = new botones(30, 0, 0);
        botones[31].setBounds(565, 277, 40, 40);
        botones[32] = new botones(30, 0, 0);
        botones[32].setBounds(681, 168, 40, 40);
        botones[33] = new botones(30, 0, 0);
        botones[33].setBounds(681, 277, 40, 40);

        this.add(botones[0]);
        this.add(botones[1]);
        this.add(botones[2]);
        this.add(botones[3]);
        this.add(botones[4]);
        this.add(botones[5]);
        this.add(botones[6]);
        this.add(botones[7]);
        this.add(botones[8]);
        this.add(botones[9]);
        this.add(botones[10]);
        this.add(botones[11]);
        this.add(botones[12]);
        this.add(botones[13]);
        this.add(botones[14]);
        this.add(botones[15]);
        this.add(botones[16]);
        this.add(botones[17]);
        this.add(botones[18]);
        this.add(botones[19]);
        this.add(botones[20]);
        this.add(botones[21]);
        this.add(botones[22]);
        this.add(botones[23]);
        this.add(botones[24]);
        this.add(botones[25]);
        this.add(botones[26]);
        this.add(botones[27]);
        this.add(botones[28]);
        this.add(botones[29]);
        this.add(botones[30]);
        this.add(botones[31]);
        this.add(botones[32]);
        this.add(botones[33]);

        for (int i = 0; i < botones.length; i++) {
            botones[i].setPanel(this);
        }
    }

    @Override
    public void paint(Graphics g) {
        ImageIcon Img = new ImageIcon(getClass().getResource("/imagenes/Fundacion.png"));
        g.drawImage(Img.getImage(), 0, 0, 900, 483, null);
        setOpaque(false);
        super.paint(g);
    }

    public int cantidad() {
        int cont = 0;
        for (int i = 0; i < botones.length; i++) {
            botones[i].repaint();
            if (!botones[i].getDato().equals("")) {
                cont++;
            }
        }
        return cont;
    }

    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setLayout(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(1000, 1000);
        f.setVisible(true);

        pnlDibujo p = new pnlDibujo();
        p.setBounds(0, 0, 746, 421);

        f.add(p);
    }

    public void setInstancias(Instancias instancias) {
        this.instancias = instancias;

        for (int i = 0; i < botones.length; i++) {
            botones[i].setInstancias(instancias);
        }
    }
}
