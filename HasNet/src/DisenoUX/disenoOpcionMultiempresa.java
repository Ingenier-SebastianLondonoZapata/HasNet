package DisenoUX;

import Vista.InicioSesion.vistaInicioSesion;
import Vista.InicioSesion.vistaInicioSesionMultiempresa;
import clases.Instancias;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class disenoOpcionMultiempresa extends JPanel implements MouseListener {

    Instancias instancias;
    private final JLabel textoLabel;
    vistaInicioSesionMultiempresa vistaInicioMultiempresa;

    public disenoOpcionMultiempresa(String descripcion, String baseDatos, vistaInicioSesionMultiempresa ventana) {
        vistaInicioMultiempresa = ventana;
        instancias = Instancias.getInstancias();
        this.setLayout(null);
        this.setSize(520, 80);
        
        textoLabel = new JLabel(descripcion.toUpperCase());
        textoLabel.setName(baseDatos);
        textoLabel.setFont(new Font("Century Gothic", Font.PLAIN, 14));
        textoLabel.setBounds(25, 0, this.getWidth(), this.getHeight());
        this.add(textoLabel);

        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.setBackground(Color.WHITE);
        this.addMouseListener(this);
        this.setBackground(new Color(225, 225, 225));
    }
    

    @Override
    public void mouseClicked(MouseEvent e) {
        instancias.getSql().obtenerConexion(textoLabel.getName(), true);        
        vistaInicioSesion iniciarSesion = new vistaInicioSesion(false);
        vistaInicioMultiempresa.dispose();
        iniciarSesion.show();        
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

    
}
