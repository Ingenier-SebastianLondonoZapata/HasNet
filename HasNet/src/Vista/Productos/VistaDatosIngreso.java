package Vista.Productos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

public class VistaDatosIngreso extends JDialog {

    private boolean confirmado = false;

    public VistaDatosIngreso(Frame parent, JPanel panelDatos, String titulo, boolean solicitarConfirmacion) {
        super(parent, titulo, true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel contenido = new JPanel(new BorderLayout());
        contenido.setBackground(Color.WHITE);
        contenido.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        panelDatos.setVisible(true);
        contenido.add(panelDatos, BorderLayout.CENTER);

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        botones.setBackground(Color.WHITE);

        JButton btnAceptar = crearBoton(solicitarConfirmacion ? "GUARDAR" : "ACEPTAR", "/imagenes/guardar.png", new Color(46, 204, 113));
        btnAceptar.addActionListener(e -> confirmar());

        if (solicitarConfirmacion) {
            JButton btnCancelar = crearBoton("CANCELAR", "/imagenes/borrar2.png", new Color(241, 148, 138));
            btnCancelar.addActionListener(e -> dispose());
            botones.add(btnCancelar);
        }
        botones.add(btnAceptar);
        contenido.add(botones, BorderLayout.SOUTH);

        setContentPane(contenido);
        registrarAtajos();
        getRootPane().setDefaultButton(null);
        pack();
        setLocationRelativeTo(null);
    }

    public boolean mostrar() {
        setVisible(true);
        return confirmado;
    }

    private void confirmar() {
        confirmado = true;
        dispose();
    }

    private JButton crearBoton(String texto, String icono, Color fondo) {
        JButton boton = new JButton(texto);
        boton.setBackground(fondo);
        boton.setFont(new Font("Century Gothic", Font.BOLD, 12));
        boton.setHorizontalTextPosition(SwingConstants.LEFT);
        boton.setPreferredSize(new Dimension(141, 40));
        try {
            boton.setIcon(new ImageIcon(getClass().getResource(icono)));
        } catch (Exception e) {
        }
        return boton;
    }

    private void registrarAtajos() {
        JComponent raiz = getRootPane();
        raiz.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "cancelar");
        raiz.getActionMap().put("cancelar", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        raiz.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_DOWN_MASK), "confirmar");
        raiz.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0), "confirmar");
        raiz.getActionMap().put("confirmar", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmar();
            }
        });
    }
}
