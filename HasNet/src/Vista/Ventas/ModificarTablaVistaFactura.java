package Vista.Ventas;

import clases.IconCellRenderer;
import java.awt.Color;
import java.awt.Component;
import java.util.Set;
import javax.swing.JTable;

public class ModificarTablaVistaFactura extends IconCellRenderer {

    private static final Color VERDE_PRODUCTO_DISENADO = new Color(210, 245, 208);

    private final int columnaIdSistema;
    private final Set<String> productosDisenados;

    public ModificarTablaVistaFactura(int columnaIdSistema, Set<String> productosDisenados) {
        this.columnaIdSistema = columnaIdSistema;
        this.productosDisenados = productosDisenados;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component componente = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (!isSelected) {
            componente.setBackground(esFilaDeProductoDisenado(table, row) ? VERDE_PRODUCTO_DISENADO : table.getBackground());
        }
        return componente;
    }

    private boolean esFilaDeProductoDisenado(JTable table, int row) {
        Object idSistema = table.getValueAt(row, columnaIdSistema);
        return idSistema != null && productosDisenados.contains(idSistema.toString());
    }
}
