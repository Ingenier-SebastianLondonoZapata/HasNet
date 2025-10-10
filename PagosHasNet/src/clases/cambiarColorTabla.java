package clases;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class cambiarColorTabla extends DefaultTableCellRenderer {

    private int opc;

    public cambiarColorTabla(int opc) {
        this.opc = opc;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (opc == 0) {
            try {
                if (table.getValueAt(row, 7).toString().equalsIgnoreCase("0")) {
                    this.setOpaque(true);
                    this.setBackground(new Color(255, 204, 204));
                    this.setForeground(new Color(0, 0, 0));
                } else {
                    this.setOpaque(true);
                    this.setForeground(new Color(0, 0, 0));
                    this.setBackground(new Color(161, 243, 158));
                }
            } catch (Exception e) {
            }
        } else if (opc == 1) {
            try {
                if (table.getValueAt(row, 7).toString().equalsIgnoreCase("OK")) {
                    this.setOpaque(true);
                    this.setBackground(new Color(161, 243, 158));
                    this.setForeground(new Color(0, 0, 0));
                } else if (table.getValueAt(row, 7).toString().equalsIgnoreCase("Vencido")) {
                    this.setOpaque(true);
                    this.setBackground(new Color(255, 91, 91));
                    this.setForeground(new Color(0, 0, 0));
                } else if (table.getValueAt(row, 7).toString().equalsIgnoreCase("Pronto a vencerse")) {
                    this.setOpaque(true);
                    this.setBackground(new Color(255, 231, 175));
                    this.setForeground(new Color(0, 0, 0));
                }
            } catch (Exception e) {
                this.setOpaque(true);
                this.setBackground(Color.WHITE);
                this.setForeground(Color.BLACK);
            }
        } else {
            this.setOpaque(true);
            this.setBackground(Color.WHITE);
            this.setForeground(Color.BLACK);
        }

        return this;
    }
}
