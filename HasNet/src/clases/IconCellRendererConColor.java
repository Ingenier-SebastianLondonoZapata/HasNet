package clases;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class IconCellRendererConColor extends DefaultTableCellRenderer {

    String validacion = "";

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (value instanceof JLabel) {
            JLabel label = (JLabel) value;
            label.setOpaque(true);

            String valor = table.getValueAt(row, column).toString();
            if (valor.contains("- $")) {
                label.setFont(new Font("Century Gothic", Font.BOLD, 16));
                label.setBackground(new Color(242, 244, 244));
            } else {
                label.setFont(new Font("Century Gothic", Font.PLAIN, 18));
                label.setBackground(new Color(242, 244, 244));
            }

            label.setBackground(new Color(242, 244, 244));
            fillColor(table, label, isSelected);
            return label;
        } else {
            return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        }
    }

    public void fillColor(JTable t, JLabel l, boolean isSelected) {
        this.setForeground(new Color(0, 0, 0));
        this.setBackground(new Color(242, 244, 244));
    }
}
