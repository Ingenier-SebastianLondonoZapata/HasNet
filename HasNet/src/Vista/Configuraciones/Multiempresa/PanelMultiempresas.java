package Vista.Configuraciones.Multiempresa;

import Controlador.Alertas.ControladorAlertas;
import Dao.Multiempresa.DaoEmpresas;
import Modelo.Maestra.ConfiguracionSistema;
import Modelo.Multiempresa.Empresa;
import clases.Instancias;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Window;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class PanelMultiempresas extends JPanel {

    private static final Font FUENTE = new Font("Arial", Font.PLAIN, 16);
    private static final Font FUENTE_TITULO = new Font("Arial", Font.PLAIN, 14);

    private final DaoEmpresas daoEmpresas = new DaoEmpresas();
    private final DefaultTableModel modelo = new DefaultTableModel(new Object[]{"Código", "Empresa", "NIT", "Estado"}, 0) {
        @Override
        public boolean isCellEditable(int fila, int columna) {
            return false;
        }
    };
    private final JTable tabla = new JTable(modelo);
    private final JLabel lbCupo = new JLabel();
    private final JButton btnAgregar = boton("Agregar empresa", new Color(46, 204, 113));
    private final JButton btnModificar = boton("Modificar empresa", new Color(93, 173, 226));
    private final JButton btnEstado = boton("Inactivar empresa", new Color(241, 148, 138));
    private final JButton btnAccesos = boton("Accesos de usuarios", new Color(245, 176, 65));
    private final List<Empresa> empresas = new ArrayList<>();

    public PanelMultiempresas() {
        setLayout(new BorderLayout(0, 6));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(null, "Multiempresas", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, FUENTE_TITULO),
                BorderFactory.createEmptyBorder(5, 6, 6, 6)));

        lbCupo.setFont(new Font("Arial", Font.PLAIN, 14));
        add(lbCupo, BorderLayout.NORTH);

        tabla.setFont(FUENTE);
        tabla.setRowHeight(24);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabla.getTableHeader().setReorderingAllowed(false);
        tabla.getColumnModel().getColumn(0).setPreferredWidth(60);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(200);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(110);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(70);
        tabla.getSelectionModel().addListSelectionListener(e -> actualizarBotones());
        JScrollPane desplazamiento = new JScrollPane(tabla);
        desplazamiento.setPreferredSize(new Dimension(460, 120));
        add(desplazamiento, BorderLayout.CENTER);

        JPanel botones = new JPanel(new GridLayout(2, 2, 6, 6));
        botones.setOpaque(false);
        botones.add(btnAgregar);
        botones.add(btnModificar);
        botones.add(btnEstado);
        botones.add(btnAccesos);
        add(botones, BorderLayout.SOUTH);

        btnAgregar.addActionListener(e -> agregar());
        btnModificar.addActionListener(e -> modificar());
        btnEstado.addActionListener(e -> cambiarEstado());
        btnAccesos.addActionListener(e -> accesos());

        cargar();
    }

    private static JButton boton(String texto, Color color) {
        JButton boton = new JButton(texto);
        boton.setFont(FUENTE);
        boton.setBackground(color);
        boton.setPreferredSize(new Dimension(200, 30));
        return boton;
    }

    public final void cargar() {
        empresas.clear();
        empresas.addAll(daoEmpresas.listar(false));
        modelo.setRowCount(0);
        for (Empresa empresa : empresas) {
            modelo.addRow(new Object[]{empresa.getId(), empresa.getNombre(), empresa.getNit(), empresa.isActiva() ? "ACTIVA" : "INACTIVA"});
        }
        long activas = empresas.stream().filter(Empresa::isActiva).count();
        lbCupo.setText("Empresas adicionales activas: " + activas + " de " + cupo());
        actualizarBotones();
    }

    private int cupo() {
        ConfiguracionSistema configuracion = Instancias.getInstancias().getConfiguraciones();
        return configuracion == null ? 0 : configuracion.getNumeroEmpresas();
    }

    private boolean hayCupo() {
        return empresas.stream().filter(Empresa::isActiva).count() < cupo();
    }

    private void actualizarBotones() {
        Empresa seleccionada = seleccionada();
        btnAgregar.setEnabled(hayCupo());
        btnModificar.setEnabled(seleccionada != null);
        btnAccesos.setEnabled(seleccionada != null && seleccionada.isActiva());
        btnEstado.setEnabled(seleccionada != null);
        btnEstado.setText(seleccionada != null && !seleccionada.isActiva() ? "Activar empresa" : "Inactivar empresa");
    }

    private Empresa seleccionada() {
        int fila = tabla.getSelectedRow();
        return fila < 0 ? null : empresas.get(tabla.convertRowIndexToModel(fila));
    }

    private Window ventana() {
        return SwingUtilities.getWindowAncestor(this);
    }

    private void agregar() {
        if (!hayCupo()) {
            ControladorAlertas.alert("Alcanzó el número de empresas contratadas (" + cupo() + "). Comuníquese con el proveedor para ampliarlo.");
            return;
        }
        DialogoEmpresa dialogo = new DialogoEmpresa(ventana(), null);
        dialogo.setVisible(true);
        if (dialogo.isGuardado()) {
            cargar();
        }
    }

    private void modificar() {
        Empresa empresa = seleccionada();
        if (empresa == null) {
            ControladorAlertas.alert("Seleccione una empresa");
            return;
        }
        DialogoEmpresa dialogo = new DialogoEmpresa(ventana(), empresa);
        dialogo.setVisible(true);
        if (dialogo.isGuardado()) {
            cargar();
        }
    }

    private void cambiarEstado() {
        Empresa empresa = seleccionada();
        if (empresa == null) {
            ControladorAlertas.alert("Seleccione una empresa");
            return;
        }
        boolean activar = !empresa.isActiva();
        if (activar && !hayCupo()) {
            ControladorAlertas.alert("Alcanzó el número de empresas contratadas (" + cupo() + "). Comuníquese con el proveedor para ampliarlo.");
            return;
        }
        Empresa actual = Instancias.getInstancias().getEmpresaActual();
        if (!activar && actual != null && empresa.getId().equals(actual.getId())) {
            ControladorAlertas.alert("No puede inactivar la empresa en la que inició sesión");
            return;
        }
        String mensaje = activar ? "¿Desea activar la empresa " + empresa.getNombre() + "?"
                : "¿Desea inactivar la empresa " + empresa.getNombre() + "? Sus datos se conservan, pero nadie podrá iniciar sesión en ella.";
        if (!ControladorAlertas.option(mensaje)) {
            return;
        }
        if (daoEmpresas.cambiarEstado(empresa.getId(), activar)) {
            ControladorAlertas.alertSuccess(activar ? "Empresa activada" : "Empresa inactivada");
            cargar();
        } else {
            ControladorAlertas.alertFail("No se pudo cambiar el estado de la empresa");
        }
    }

    private void accesos() {
        Empresa empresa = seleccionada();
        if (empresa == null) {
            ControladorAlertas.alert("Seleccione una empresa");
            return;
        }
        new DialogoAccesosEmpresa(ventana(), empresa).setVisible(true);
    }
}
