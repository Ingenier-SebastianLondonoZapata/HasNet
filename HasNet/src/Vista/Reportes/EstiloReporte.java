package Vista.Reportes;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public final class EstiloReporte {

    public static final Color FONDO = Color.WHITE;
    public static final Color FONDO_PAGINA = new Color(243, 245, 248);
    public static final Color SECCION = new Color(48, 84, 150);
    public static final Font TITULO_SECCION = new Font("Century Gothic", Font.BOLD, 13);
    public static final int ANCHO_TARJETA = 880;
    public static final int ANCHO_ETIQUETA = 170;
    public static final Color BORDE = new Color(200, 205, 212);
    public static final Color ETIQUETA_SECUNDARIA = new Color(95, 99, 104);
    public static final Color BOTON_GENERAR = new Color(46, 204, 113);
    public static final Color CHIP_FONDO = new Color(232, 240, 254);
    public static final Color CHIP_TEXTO = new Color(25, 70, 140);
    public static final Font TITULO = new Font("Century Gothic", Font.BOLD, 18);
    public static final Font ETIQUETA = new Font("Century Gothic", Font.BOLD, 14);
    public static final Font CAMPO = new Font("Century Gothic", Font.PLAIN, 14);
    public static final Font CHIP = new Font("Century Gothic", Font.PLAIN, 12);
    public static final Font BOTON = new Font("Century Gothic", Font.BOLD, 15);
    public static final int ALTO_CAMPO = 28;
    public static final int ANCHO_CAMPO = 240;
    private static final int TAMANO_ICONO = 18;

    private EstiloReporte() {
    }

    public static JLabel etiqueta(String texto) {
        JLabel etiqueta = new JLabel(texto);
        etiqueta.setFont(ETIQUETA);
        return etiqueta;
    }

    public static JLabel nota(String texto) {
        JLabel nota = new JLabel(texto);
        nota.setFont(CHIP);
        nota.setForeground(ETIQUETA_SECUNDARIA);
        return nota;
    }

    public static JTextField campo() {
        JTextField campo = new JTextField();
        campo.setFont(CAMPO);
        campo.setPreferredSize(new Dimension(ANCHO_CAMPO, ALTO_CAMPO));
        return campo;
    }

    public static <T> JComboBox<T> combo() {
        JComboBox<T> combo = new JComboBox<>();
        combo.setFont(CAMPO);
        combo.setBackground(FONDO);
        combo.setPreferredSize(new Dimension(ANCHO_CAMPO, ALTO_CAMPO));
        return combo;
    }

    public static JCheckBox casilla(String texto) {
        JCheckBox casilla = new JCheckBox(texto);
        casilla.setFont(CAMPO);
        casilla.setBackground(FONDO);
        return casilla;
    }

    public static JRadioButton radio(String texto) {
        JRadioButton radio = new JRadioButton(texto);
        radio.setFont(CAMPO);
        radio.setBackground(FONDO);
        return radio;
    }

    public static JButton botonIcono(String recurso, String textoAlterno, String ayuda) {
        JButton boton = new JButton();
        URL url = EstiloReporte.class.getResource(recurso);
        if (url != null) {
            Image imagen = new ImageIcon(url).getImage().getScaledInstance(TAMANO_ICONO, TAMANO_ICONO, Image.SCALE_SMOOTH);
            boton.setIcon(new ImageIcon(imagen));
        } else {
            boton.setText(textoAlterno);
        }
        boton.setToolTipText(ayuda);
        boton.setFocusable(false);
        boton.setBackground(FONDO);
        boton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        boton.setPreferredSize(new Dimension(ALTO_CAMPO + 6, ALTO_CAMPO));
        return boton;
    }

    public static JButton botonAgregar() {
        return botonIcono("/imagenes/agregar.png", "+", "Agregar al filtro");
    }

    public static JButton botonBuscar() {
        return botonIcono("/imagenes/buscar1.png", "...", "Buscar");
    }

    public static JButton botonGenerar(String texto) {
        JButton boton = new JButton(texto);
        boton.setFont(BOTON);
        boton.setBackground(BOTON_GENERAR);
        boton.setForeground(Color.BLACK);
        boton.setFocusPainted(false);
        boton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        boton.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(BOTON_GENERAR.darker()),
                BorderFactory.createEmptyBorder(8, 18, 8, 18)));
        return boton;
    }
}
