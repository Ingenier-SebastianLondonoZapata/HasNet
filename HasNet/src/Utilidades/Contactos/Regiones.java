package Utilidades.Contactos;

import clases.Instancias;
import javax.swing.JComboBox;

public final class Regiones {

    public static final String DEPARTAMENTO_PREDETERMINADO = "ANTIOQUIA";
    private static final String SIN_SELECCION = " ";

    private Regiones() {
    }

    public static void cargarDepartamentos(JComboBox combo) {
        combo.removeAllItems();
        for (Object[] fila : filas(Instancias.getInstancias().getSql().getDepartamentos())) {
            if (fila[0] != null) {
                combo.addItem(fila[0].toString().toUpperCase());
            }
        }
        combo.setSelectedItem(DEPARTAMENTO_PREDETERMINADO);
    }

    public static void cargarCiudades(JComboBox combo, Object departamento) {
        combo.removeAllItems();
        combo.addItem(SIN_SELECCION);
        if (estaVacio(departamento)) {
            return;
        }
        for (Object[] fila : filas(Instancias.getInstancias().getSql().getMunicipios(departamento.toString()))) {
            if (fila[0] != null) {
                combo.addItem(fila[0]);
            }
        }
    }

    public static void cargarCodigosPostales(JComboBox combo, Object departamento, Object ciudad) {
        combo.removeAllItems();
        combo.addItem(SIN_SELECCION);
        if (estaVacio(departamento) || estaVacio(ciudad)) {
            return;
        }
        for (Object[] fila : filas(Instancias.getInstancias().getSql().getCodigosPostales(departamento.toString(), ciudad.toString()))) {
            if (fila[0] != null) {
                combo.addItem(fila[0]);
            }
        }
        if (combo.getItemCount() > 1) {
            combo.setSelectedIndex(1);
        }
    }

    private static Object[][] filas(Object[][] datos) {
        return datos == null ? new Object[0][] : datos;
    }

    private static boolean estaVacio(Object valor) {
        return valor == null || valor.toString().trim().isEmpty();
    }
}
