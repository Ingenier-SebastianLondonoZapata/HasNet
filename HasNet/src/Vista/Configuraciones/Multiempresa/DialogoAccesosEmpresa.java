package Vista.Configuraciones.Multiempresa;

import Controlador.Alertas.ControladorAlertas;
import Dao.Configuraciones.DaoUsuarios;
import Dao.Multiempresa.DaoEmpresas;
import Modelo.Multiempresa.Empresa;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Window;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DialogoAccesosEmpresa extends JDialog {

    private static final Font FUENTE = new Font("Arial", Font.PLAIN, 15);

    private final Empresa empresa;
    private final DaoEmpresas daoEmpresas = new DaoEmpresas();
    private final DefaultTableModel modelo = new DefaultTableModel(new Object[]{"Acceso", "Usuario", "Nombre"}, 0) {
        @Override
        public Class<?> getColumnClass(int columna) {
            return columna == 0 ? Boolean.class : String.class;
        }

        @Override
        public boolean isCellEditable(int fila, int columna) {
            return columna == 0;
        }
    };
    private final JTable tabla = new JTable(modelo);

    public DialogoAccesosEmpresa(Window propietario, Empresa empresa) {
        super(propietario, "Accesos a " + empresa.getNombre(), ModalityType.APPLICATION_MODAL);
        this.empresa = empresa;
        construir();
        cargar();
        pack();
        setLocationRelativeTo(propietario);
    }

    private void construir() {
        JLabel descripcion = new JLabel("Marque los usuarios que pueden iniciar sesión en " + empresa.getNombre());
        descripcion.setFont(new Font("Arial", Font.PLAIN, 14));
        descripcion.setBorder(BorderFactory.createEmptyBorder(12, 14, 8, 14));

        tabla.setFont(FUENTE);
        tabla.setRowHeight(24);
        tabla.getTableHeader().setReorderingAllowed(false);
        tabla.getColumnModel().getColumn(0).setMaxWidth(70);
        JScrollPane desplazamiento = new JScrollPane(tabla);
        desplazamiento.setPreferredSize(new Dimension(460, 280));
        desplazamiento.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(0, 14, 0, 14), desplazamiento.getBorder()));

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setFont(FUENTE);
        btnGuardar.setBackground(new Color(46, 204, 113));
        btnGuardar.addActionListener(e -> guardar());
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setFont(FUENTE);
        btnCancelar.addActionListener(e -> dispose());

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 12));
        botones.add(btnCancelar);
        botones.add(btnGuardar);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(descripcion, BorderLayout.NORTH);
        getContentPane().add(desplazamiento, BorderLayout.CENTER);
        getContentPane().add(botones, BorderLayout.SOUTH);
    }

    private void cargar() {
        Set<String> conAcceso = daoEmpresas.usuariosConAcceso(empresa.getId());
        for (String[] usuario : new DaoUsuarios().listar()) {
            modelo.addRow(new Object[]{conAcceso.contains(usuario[0]), usuario[0], usuario[1]});
        }
    }

    private void guardar() {
        if (tabla.isEditing()) {
            tabla.getCellEditor().stopCellEditing();
        }
        Set<String> seleccionados = new LinkedHashSet<>();
        for (int i = 0; i < modelo.getRowCount(); i++) {
            if (Boolean.TRUE.equals(modelo.getValueAt(i, 0))) {
                seleccionados.add(modelo.getValueAt(i, 1).toString());
            }
        }
        if (daoEmpresas.guardarAccesos(empresa.getId(), seleccionados)) {
            ControladorAlertas.alertSuccess("Accesos guardados");
            dispose();
        } else {
            ControladorAlertas.alertFail("No se pudieron guardar los accesos");
        }
    }
}
