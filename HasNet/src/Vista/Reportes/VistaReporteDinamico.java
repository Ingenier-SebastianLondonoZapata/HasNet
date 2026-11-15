package Vista.Reportes;

import Controlador.Alertas.ControladorAlertas;
import Modelo.Reportes.SeleccionReporte;
import Vista.Reportes.Filtros.FiltroReporte;
import clases.Instancias;
import clases.metodosGenerales;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class VistaReporteDinamico extends JPanel {

    private final DefinicionReporte definicion;
    private final Instancias instancias = Instancias.getInstancias();
    private final metodosGenerales metodos = new metodosGenerales();
    private final JPanel tarjeta = new TarjetaReporte();
    private int fila;
    private JRadioButton hojaDeCalculo;

    public VistaReporteDinamico(DefinicionReporte definicion) {
        super(new GridBagLayout());
        this.definicion = definicion;
        setBackground(EstiloReporte.FONDO_PAGINA);

        tarjeta.setBackground(EstiloReporte.FONDO);
        tarjeta.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(EstiloReporte.BORDE),
                BorderFactory.createEmptyBorder(20, 26, 22, 26)));

        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.NORTH;
        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(18, 18, 18, 18);
        add(tarjeta, c);
        construir();
    }

    private void construir() {
        JLabel titulo = new JLabel(definicion.getTitulo());
        titulo.setFont(EstiloReporte.TITULO);
        agregarFilaCompleta(titulo, new Insets(0, 0, 2, 0));
        if (!definicion.getNota().isEmpty()) {
            agregarFilaCompleta(EstiloReporte.nota(definicion.getNota()), new Insets(0, 0, 0, 0));
        }

        agregarSeccion("FILTROS");
        for (FiltroReporte filtro : definicion.getFiltros()) {
            if (filtro.esVisible()) {
                agregarFiltro(filtro.getEtiqueta(), filtro.crearComponente());
            }
        }

        agregarSeccion("SALIDA");
        agregarFiltro("Versión", crearSalida());

        agregarFilaCompleta(separador(), new Insets(18, 0, 16, 0));
        agregarFilaCompleta(crearAcciones(), new Insets(0, 0, 0, 0));
    }

    private void agregarSeccion(String texto) {
        JLabel seccion = new JLabel(texto);
        seccion.setFont(EstiloReporte.TITULO_SECCION);
        seccion.setForeground(EstiloReporte.SECCION);
        seccion.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, EstiloReporte.BORDE),
                BorderFactory.createEmptyBorder(0, 0, 4, 0)));
        agregarFilaCompleta(seccion, new Insets(18, 0, 8, 0));
    }

    private void agregarFiltro(String etiqueta, JComponent componente) {
        GridBagConstraints c = new GridBagConstraints();
        c.gridy = fila++;
        c.gridx = 0;
        c.anchor = GridBagConstraints.NORTHEAST;
        c.insets = new Insets(9, 0, 5, 14);
        JLabel texto = EstiloReporte.etiqueta(etiqueta.endsWith(":") ? etiqueta : etiqueta + ":");
        texto.setHorizontalAlignment(SwingConstants.RIGHT);
        texto.setPreferredSize(new Dimension(EstiloReporte.ANCHO_ETIQUETA, texto.getPreferredSize().height));
        tarjeta.add(texto, c);

        c.gridx = 1;
        c.weightx = 1;
        c.anchor = GridBagConstraints.NORTHWEST;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(4, 0, 4, 0);
        tarjeta.add(componente, c);
    }

    private void agregarFilaCompleta(JComponent componente, Insets margen) {
        GridBagConstraints c = new GridBagConstraints();
        c.gridy = fila++;
        c.gridx = 0;
        c.gridwidth = 2;
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.WEST;
        c.insets = margen;
        tarjeta.add(componente, c);
    }

    private static JSeparator separador() {
        JSeparator separador = new JSeparator();
        separador.setForeground(EstiloReporte.BORDE);
        return separador;
    }

    private JComponent crearSalida() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 4, 0));
        panel.setOpaque(false);
        JRadioButton pantalla = EstiloReporte.radio("En pantalla");
        hojaDeCalculo = EstiloReporte.radio("Hoja de cálculo");
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(pantalla);
        grupo.add(hojaDeCalculo);
        pantalla.setSelected(true);
        panel.add(pantalla);
        panel.add(hojaDeCalculo);
        return panel;
    }

    private JComponent crearAcciones() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 0));
        panel.setOpaque(false);
        for (AccionReporte accion : definicion.getAcciones()) {
            JButton boton = EstiloReporte.botonGenerar(accion.getTexto());
            boton.addActionListener(e -> ejecutar(accion));
            panel.add(boton);
        }
        return panel;
    }

    private void ejecutar(AccionReporte accion) {
        for (FiltroReporte filtro : definicion.getFiltros()) {
            String error = filtro.validar();
            if (error != null) {
                ControladorAlertas.alertFail(error);
                return;
            }
        }

        SeleccionReporte seleccion = new SeleccionReporte();
        for (FiltroReporte filtro : definicion.getFiltros()) {
            filtro.aplicar(seleccion);
        }
        seleccion.setExcel(hojaDeCalculo.isSelected());

        if (!ControladorAlertas.option("¿Está seguro?")) {
            return;
        }

        if (seleccion.isExcel()) {
            String ruta = metodos.obtenerRuta(null, accion.getNombreArchivo() + " " + metodosGenerales.dia() + " de "
                    + metodosGenerales.mesEnPalabra() + " del " + metodosGenerales.anho());
            if (ruta == null) {
                return;
            }
            instancias.setRutaAguardar(ruta);
        }

        try {
            accion.ejecutar(seleccion);
        } catch (RuntimeException ex) {
            instancias.setRutaAguardar(null);
            Logger.getLogger(VistaReporteDinamico.class.getName()).log(Level.SEVERE, null, ex);
            ControladorAlertas.alertFail("No se pudo generar el reporte");
        }
    }

    private static class TarjetaReporte extends JPanel {

        TarjetaReporte() {
            super(new GridBagLayout());
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(EstiloReporte.ANCHO_TARJETA, super.getPreferredSize().height);
        }

        @Override
        public Dimension getMinimumSize() {
            return getPreferredSize();
        }

        @Override
        public Dimension getMaximumSize() {
            return getPreferredSize();
        }
    }
}
