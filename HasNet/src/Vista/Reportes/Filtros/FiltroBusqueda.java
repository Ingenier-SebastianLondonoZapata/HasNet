package Vista.Reportes.Filtros;

import Controlador.Alertas.ControladorAlertas;
import Modelo.Reportes.OpcionFiltro;
import Vista.Reportes.EstiloReporte;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FiltroBusqueda extends FiltroMultiple {

    public interface Buscador {

        void abrir(JTextField campo, String texto);
    }

    public interface BuscadorMultiple {

        void abrir(JTextField campo, String texto, Consumer<List<String>> agregarVarios);
    }

    private static final int SEPARACION = 4;

    private final Function<String, OpcionFiltro> resolutor;
    private final BuscadorMultiple buscador;
    private JTextField campo;

    public FiltroBusqueda(String id, String etiqueta, Function<String, OpcionFiltro> resolutor, Buscador buscador) {
        this(id, etiqueta, resolutor, buscador == null ? null : (BuscadorMultiple) (campo, texto, agregarVarios) -> buscador.abrir(campo, texto));
    }

    public FiltroBusqueda(String id, String etiqueta, Function<String, OpcionFiltro> resolutor, BuscadorMultiple buscador) {
        super(id, etiqueta);
        this.resolutor = resolutor;
        this.buscador = buscador;
    }

    public static FiltroBusqueda textoLibre(String id, String etiqueta) {
        return new FiltroBusqueda(id, etiqueta, texto -> OpcionFiltro.de(texto.trim()), (BuscadorMultiple) null);
    }

    private void agregarValores(List<String> valores) {
        List<OpcionFiltro> opciones = new ArrayList<>();
        for (String valor : valores) {
            if (valor != null && !valor.trim().isEmpty()) {
                opciones.add(resolutor.apply(valor.trim()));
            }
        }
        campo.setText("");
        agregarVarios(opciones);
    }

    @Override
    protected JComponent crearEntrada() {
        JPanel entrada = new JPanel(new FlowLayout(FlowLayout.LEFT, SEPARACION, 0));
        entrada.setOpaque(false);

        campo = EstiloReporte.campo();
        campo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent evento) {
                if (evento.getKeyCode() == KeyEvent.VK_ENTER) {
                    intentarAgregar();
                }
            }
        });
        entrada.add(campo);

        if (buscador != null) {
            JButton buscar = EstiloReporte.botonBuscar();
            buscar.addActionListener(e -> abrirBuscador(campo.getText().trim()));
            int espacio = buscar.getPreferredSize().width + SEPARACION;
            campo.setPreferredSize(new Dimension(EstiloReporte.ANCHO_CAMPO - espacio, EstiloReporte.ALTO_CAMPO));
            entrada.add(buscar);
        }

        JButton agregar = EstiloReporte.botonAgregar();
        agregar.addActionListener(e -> intentarAgregar());
        entrada.add(agregar);
        return entrada;
    }

    private void intentarAgregar() {
        String texto = campo.getText().trim();
        if (texto.isEmpty()) {
            abrirBuscador("");
            return;
        }

        OpcionFiltro opcion = resolutor.apply(texto);
        if (opcion == null) {
            if (buscador != null) {
                abrirBuscador(texto);
            } else {
                ControladorAlertas.alertFail("No se encontró " + texto);
            }
            return;
        }

        campo.setText("");
        agregar(opcion);
    }

    private void abrirBuscador(String texto) {
        if (buscador == null) {
            return;
        }
        campo.requestFocus();
        buscador.abrir(campo, texto, this::agregarValores);
    }
}
