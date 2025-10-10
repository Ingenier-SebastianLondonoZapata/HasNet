package Utilidades;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class CambiarColorTablaReimpresionYAnulacion extends DefaultTableCellRenderer {

    public CambiarColorTablaReimpresionYAnulacion() {
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (table.getValueAt(row, 9) != null && Constantes.esFacturacionElectronica(table.getValueAt(row, 9).toString())) {
            this.setOpaque(true);
            this.setForeground(Color.BLACK);
            this.setBackground(new Color(161, 243, 158));
        } else {
            this.setOpaque(true);
            this.setBackground(Color.WHITE);
            this.setForeground(Color.BLACK);
        }

        return this;
    }
}
