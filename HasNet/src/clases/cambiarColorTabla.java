package clases;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class cambiarColorTabla extends DefaultTableCellRenderer {

    private int opc;
    private int celda;

    public cambiarColorTabla(int opc, int celda) {
        this.opc = opc;
        this.celda = celda;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (opc == 0) {
            //CARTERA
            try {
                if (table.getValueAt(row, 8).toString().equalsIgnoreCase("OK") && column == 8) {
                    this.setOpaque(true);
                    this.setForeground(new Color(98, 146, 77));
                    this.setBackground(new Color(161, 243, 158));
                } else if (table.getValueAt(row, 8).toString().equalsIgnoreCase("VENCIDA") && column == 8) {
                    this.setOpaque(true);
                    this.setForeground(new Color(156, 0, 6));
                    this.setBackground(new Color(255, 199, 206));
                } else if (table.getValueAt(row, 8).toString().equalsIgnoreCase("ALERTA") && column == 8) {
                    this.setOpaque(true);
                    this.setForeground(new Color(146, 137, 77));
                    this.setBackground(new Color(251, 238, 152));
                } else {
                    this.setOpaque(true);
                    this.setBackground(Color.WHITE);
                    this.setForeground(Color.BLACK);
                }
            } catch (Exception e) {
            }
        } else if (opc == 1) {
            //PLU
            try {
                if (column == 5 && big.getBigDecimal(table.getValueAt(row, 5).toString().replace(",", ".")).compareTo(big.getBigDecimal("1")) == -1) {
                    this.setOpaque(true);
                    this.setForeground(new Color(156, 0, 6));
                    this.setBackground(new Color(255, 199, 206));
                } else if (column == 6 && (big.getBigDecimal(table.getValueAt(row, 6).toString().replace(",", ".")).compareTo(big.getBigDecimal("0")) == -1 || big.getBigDecimal(table.getValueAt(row, 6).toString().replace(",", ".")).compareTo(big.getBigDecimal("0")) == 0)) {
                    this.setOpaque(true);
                    this.setForeground(new Color(146, 137, 77));
                    this.setBackground(new Color(251, 238, 152));
                } else if (column == 6 && big.getBigDecimal(table.getValueAt(row, 6).toString().replace(",", ".")).compareTo(big.getBigDecimal("0")) == 1) {
                    this.setOpaque(true);
                    this.setForeground(new Color(98, 146, 77));
                    this.setBackground(new Color(161, 243, 158));
                } else {
                    this.setOpaque(true);
                    this.setBackground(Color.WHITE);
                    this.setForeground(Color.BLACK);
                }
            } catch (Exception e) {
                this.setOpaque(true);
                this.setBackground(Color.WHITE);
                this.setForeground(Color.BLACK);
            }

        } else if (opc == 2) {
            //AJUSTE INVENTARIO
            if (((table.getValueAt(row, 3).toString().equals("")) && column == 3)) {
                this.setOpaque(true);
                this.setForeground(new Color(156, 0, 6));
                this.setBackground(new Color(255, 199, 206));
            } else {
                this.setOpaque(true);
                this.setBackground(Color.WHITE);
                this.setForeground(Color.BLACK);
            }
        } else if (opc == 3) {
            //FACTURA
            if ((table.getValueAt(row, 3).toString().contains(".") && column == 3) || (table.getValueAt(row, 6).toString().equals("") && column == 6)
                    || (!table.getValueAt(row, 6).toString().contains("$") && column == 6) || (table.getValueAt(row, 7).toString().equals("") && column == 7)
                    || (table.getValueAt(row, 15).toString().equals("ERROR")) && column == 15) {
                this.setOpaque(true);
                this.setForeground(new Color(156, 0, 6));
                this.setBackground(new Color(255, 199, 206));
            } else if (table.getValueAt(row, 16) != null && table.getValueAt(row, 16).equals("REALIZADO")) {
                this.setOpaque(true);
//                this.setForeground(new Color(98, 146, 77));
//                this.setBackground(new Color(161, 243, 158));
                this.setBackground(new Color(204, 204, 204));
            } else {
                this.setOpaque(true);
                this.setBackground(Color.WHITE);
                this.setForeground(Color.BLACK);
            }

        } else if (opc == 4) {
            if (table.getColumnCount() > 3) {
                if (column == 6) {
                    if (table.getValueAt(row, 6).equals("NO ATENDIDO")) {
                        this.setOpaque(true);
                        this.setForeground(new Color(146, 137, 77));
                        this.setBackground(new Color(251, 238, 152));
                    } else {
                        this.setOpaque(true);
                        this.setForeground(new Color(98, 146, 77));
                        this.setBackground(new Color(161, 243, 158));
                    }
                } else {
                    this.setOpaque(true);
                    this.setForeground(Color.BLACK);
                    this.setBackground(Color.WHITE);
                }
            }
        } else if (opc == 5) {
            if (table.getValueAt(row, 0).toString().equalsIgnoreCase("Registrada")) {
                //AMARILLO CLARO
                this.setOpaque(true);
                this.setBackground(new Color(251, 238, 152));
            } else if (table.getValueAt(row, 0).toString().equalsIgnoreCase("Confirmada")) {
                //VERDE MAS CLARO
                this.setOpaque(true);
                this.setBackground(new Color(204, 255, 204));
            } else if (table.getValueAt(row, 0).toString().equalsIgnoreCase("Atendido")) {
                //VERDE 
                this.setOpaque(true);
                this.setBackground(new Color(161, 243, 158));
            } else if (table.getValueAt(row, 0).toString().equalsIgnoreCase("Reagendada")) {
                //MORADO CLARO
                this.setOpaque(true);
                this.setBackground(new Color(233, 172, 234));
            } else if (table.getValueAt(row, 0).toString().equalsIgnoreCase("No vino")) {
                //AZUL CIELO
                this.setOpaque(true);
                this.setBackground(new Color(172, 224, 234));
            } else {
                this.setOpaque(true);
                this.setBackground(new Color(255, 255, 255));
            }
        } else if (opc == 6) {
            if (table.getValueAt(row, 2).toString().equalsIgnoreCase("D") && column == 5) {
                this.setOpaque(true);
                this.setForeground(new Color(98, 146, 77));
                this.setBackground(new Color(161, 243, 158));
            } else {
                this.setOpaque(true);
                this.setBackground(Color.WHITE);
                this.setForeground(Color.BLACK);
            }
        } else if (opc == 7) {
            try {
                if (table.getValueAt(row, 9).toString().equalsIgnoreCase("OK") && column == 9) {
                    this.setOpaque(true);
                    this.setForeground(new Color(98, 146, 77));
                    this.setBackground(new Color(161, 243, 158));
                } else if (table.getValueAt(row, 9).toString().equalsIgnoreCase("VENCIDA") && column == 9) {
                    this.setOpaque(true);
                    this.setForeground(new Color(156, 0, 6));
                    this.setBackground(new Color(255, 199, 206));
                } else if (table.getValueAt(row, 9).toString().equalsIgnoreCase("ALERTA") && column == 9) {
                    this.setOpaque(true);
                    this.setForeground(new Color(146, 137, 77));
                    this.setBackground(new Color(251, 238, 152));
                } else {
                    this.setOpaque(true);
                    this.setBackground(Color.WHITE);
                    this.setForeground(Color.BLACK);
                }
            } catch (Exception e) {
            }
        } else if (opc == 8) {
            //TABLA DE LAS CUOTAS DE LOS ABONOS CREDITOS.
            if (table.getValueAt(row, 9).toString().equalsIgnoreCase("OK") && column == 9) {
                this.setOpaque(true);
//                this.setForeground(new Color(98, 146, 77));
                this.setForeground(new Color(0, 102, 51));
                this.setBackground(new Color(161, 243, 158));
            } else if (table.getValueAt(row, 9).toString().equalsIgnoreCase("VENCIDA") && column == 9) {
                this.setOpaque(true);
                this.setForeground(new Color(156, 0, 6));
                this.setBackground(new Color(255, 199, 206));
            } else if (table.getValueAt(row, 9).toString().equalsIgnoreCase("PENDIENTE") && column == 9) {
                this.setOpaque(true);
                this.setForeground(new Color(146, 137, 77));
                this.setBackground(new Color(251, 238, 152));
            } else {
                this.setOpaque(true);
                this.setBackground(Color.WHITE);
                this.setForeground(Color.BLACK);
            }
        } else if (opc == 9) {
            //CARTERA
            if (table.getValueAt(row, 9).toString().equalsIgnoreCase("SALDADA") || table.getValueAt(row, 9).toString().equalsIgnoreCase("SALDADA-ATRASADA") && column == 9) {
                this.setOpaque(true);
                this.setForeground(new Color(98, 146, 77));
                this.setBackground(new Color(161, 243, 158));
            } else if (table.getValueAt(row, 9).toString().equalsIgnoreCase("ATRASADA") && column == 9) {
                this.setOpaque(true);
                this.setForeground(new Color(156, 0, 6));
                this.setBackground(new Color(255, 199, 206));
            } else if (table.getValueAt(row, 9).toString().equalsIgnoreCase("PENDIENTE") && column == 9) {
                this.setOpaque(true);
                this.setForeground(new Color(146, 137, 77));
                this.setBackground(new Color(251, 238, 152));
            } else {
                this.setOpaque(true);
                this.setBackground(Color.WHITE);
                this.setForeground(Color.BLACK);
            }
        } else if (opc == 10) {
            //CARTERA
            if (table.getValueAt(row, 6).toString().equalsIgnoreCase("OK") && column == 6) {
                this.setOpaque(true);
                this.setForeground(new Color(98, 146, 77));
                this.setBackground(new Color(161, 243, 158));
            } else if (table.getValueAt(row, 6).toString().equalsIgnoreCase("VENCIDA") && column == 6) {
                this.setOpaque(true);
                this.setForeground(new Color(156, 0, 6));
                this.setBackground(new Color(255, 199, 206));
            } else if (table.getValueAt(row, 6).toString().equalsIgnoreCase("ALERTA") && column == 6) {
                this.setOpaque(true);
                this.setForeground(new Color(146, 137, 77));
                this.setBackground(new Color(251, 238, 152));
            } else {
                this.setOpaque(true);
                this.setBackground(Color.WHITE);
                this.setForeground(Color.BLACK);
            }
        } else if (opc == 11) {
            String[] validacion = table.getValueAt(row, column).toString().split(". ");

            if (validacion.length > 0) {
                try {
                    if (table.getValueAt(row, column).toString().length() > 5 && validacion[1] != "Seleccionado" && column == column) {
                        this.setOpaque(true);
                        this.setForeground(new Color(0, 0, 0));
                        this.setBackground(new Color(255, 204, 204));
                    } else {
                        this.setOpaque(true);
                        this.setForeground(new Color(0, 0, 0));
                        this.setBackground(new Color(102, 255, 102));
                    }
                } catch (Exception e) {
                    this.setOpaque(true);
                    this.setForeground(new Color(0, 0, 0));
                    this.setBackground(new Color(241, 148, 138));
                }

                try {
                    if (validacion[1].equals("Seleccionado") && column == column) {
                        this.setOpaque(true);
                        this.setForeground(new Color(0, 0, 0));
                        this.setBackground(new Color(255, 153, 0));
                    }
                } catch (Exception e) {
                }
            }

        } else if (opc == 12) {
            //TABLA DE LAS CUOTAS DE LOS ABONOS CREDITOS.
            try {
                if (table.getValueAt(row, 5).toString().equals(" CAMBIAR") && column == 5) {
                    this.setOpaque(true);
                    this.setForeground(new Color(0, 0, 0));
                    this.setBackground(new Color(161, 243, 158));
                } else if (table.getValueAt(row, 5).toString().equals("   QUITAR")) {
                    this.setOpaque(true);
                    this.setForeground(new Color(0, 0, 0));
                    this.setBackground(new Color(247, 220, 111));

                } else {
                    this.setOpaque(true);
                    this.setBackground(Color.WHITE);
                    this.setForeground(Color.BLACK);
                }
            } catch (Exception e) {
                this.setOpaque(true);
                this.setBackground(Color.WHITE);
                this.setForeground(Color.BLACK);
            }

        } else if (opc == 13) {
            String[] validacion = table.getValueAt(row, column).toString().split(": ");
            if (validacion[0].equals("                    CONG") && column == column) {
                this.setOpaque(true);
                this.setForeground(new Color(0, 0, 0));
                this.setBackground(new Color(204, 204, 204));
            } else if (table.getValueAt(row, column).toString().equals("") && column == column) {
                this.setOpaque(true);
                this.setForeground(new Color(0, 0, 0));
                this.setBackground(new Color(255, 255, 255));
            } else {
                this.setOpaque(true);
                this.setForeground(new Color(0, 0, 0));
                this.setBackground(new Color(242, 244, 244));
            }
        } else if (opc == 14) {
            String validacion = table.getValueAt(row, column).toString();
            if (validacion.equals("SELECCIONADO") && column == column) {
                this.setOpaque(true);
                this.setBackground(new Color(46, 204, 113));
                this.setForeground(new Color(0, 0, 0));
            } else {
                this.setOpaque(true);
                this.setBackground(Color.WHITE);
                this.setForeground(Color.BLACK);
            }
        } else if (opc == 15) {
            if ((table.getValueAt(row, 1).toString().equalsIgnoreCase(metodosGenerales.fecha())) && (column == 1 || column == 2)) {

                this.setForeground(Color.BLACK);
                this.setBackground(new Color(251, 238, 152));
            } else if (Integer.parseInt(table.getValueAt(row, 9).toString()) < 0 && (column == 1 || column == 2)) {
                this.setForeground(Color.BLACK);
                this.setBackground(new Color(255, 199, 206));
            } else {
                this.setOpaque(true);
                this.setBackground(Color.WHITE);
                this.setForeground(Color.BLACK);
            }
        } else if (opc == 16) {
            if (((Boolean) table.getValueAt(row, 2)) && (column == column)) {
                this.setForeground(Color.BLACK);
                this.setBackground(new Color(204, 204, 204));
            } else {
                this.setOpaque(true);
                this.setBackground(Color.WHITE);
                this.setForeground(Color.BLACK);
            }
        } else if (opc == 17) {
            try {
                if (((Boolean) table.getValueAt(row, 2)) && (column == column)) {
                    this.setForeground(Color.BLACK);
                    this.setBackground(new Color(102, 153, 0));
                } else {
                    this.setOpaque(true);
                    this.setBackground(Color.WHITE);
                    this.setForeground(Color.BLACK);
                }
            } catch (Exception e) {
                this.setOpaque(true);
                this.setBackground(Color.WHITE);
                this.setForeground(Color.BLACK);
            }
        } else if (opc == 18) {
            try {
                if (((Boolean) table.getValueAt(row, 2)) && (column == column)) {
                    this.setForeground(Color.BLACK);
                    this.setBackground(new Color(102, 153, 0));
                } else {
                    this.setOpaque(true);
                    this.setBackground(Color.WHITE);
                    this.setForeground(Color.BLACK);
                }
            } catch (Exception e) {
                this.setOpaque(true);
                this.setBackground(Color.WHITE);
                this.setForeground(Color.BLACK);
            }
        } else if (opc == 19) {
            try {
                if (table.getValueAt(row, column).toString().contains("<html>Registrada") && column == column) {
                    //AMARILLO CLARO
                    this.setOpaque(true);
                    this.setBackground(new Color(251, 238, 152));
                } else if (table.getValueAt(row, column).toString().contains("<html>Confirmada") && column == column) {
                    //VERDE MAS CLARO
                    this.setOpaque(true);
                    this.setBackground(new Color(204, 255, 204));
                } else if (table.getValueAt(row, column).toString().contains("<html>Atendido") && column == column) {
                    //VERDE 
                    this.setOpaque(true);
                    this.setBackground(new Color(161, 243, 158));
                } else if (table.getValueAt(row, column).toString().contains("<html>Reagendada") && column == column) {
                    //MORADO CLARO
                    this.setOpaque(true);
                    this.setBackground(new Color(233, 172, 234));
                } else if (table.getValueAt(row, column).toString().contains("<html>No vino") && column == column) {
                    //AZUL CIELO
                    this.setOpaque(true);
                    this.setBackground(new Color(172, 224, 234));
                } else {
                    this.setOpaque(true);
                    this.setBackground(new Color(255, 255, 255));
                }
            } catch (Exception e) {
            }
        } else if (opc == 20) {
            try {
                if (table.getValueAt(row, column).toString().equals("Inhabilitado") && row == 1 && column == 1) {
                    this.setOpaque(true);
                    this.setBackground(new Color(255, 51, 51));
                } else {
                    this.setOpaque(true);
                    this.setBackground(new Color(255, 255, 255));
                }
            } catch (Exception e) {
            }
        } else if (opc == 21) {

        } else if (opc == 22) {
            String validacion = table.getValueAt(row, column).toString();
            if (validacion.contains(".") && column == column) {
                this.setOpaque(true);
                this.setForeground(new Color(0, 0, 0));
                this.setBackground(new Color(204, 204, 204));
            } else {
                this.setOpaque(true);
                this.setBackground(Color.WHITE);
                this.setForeground(Color.BLACK);
            }
        } else if (opc == 23) {
            String validacion = table.getValueAt(row, column).toString();
            if (validacion.contains("Mesa") && column == column) {
                this.setOpaque(true);
                this.setForeground(new Color(0, 0, 0));
                this.setBackground(new Color(204, 204, 204));
            } else {
                this.setOpaque(true);
                this.setBackground(Color.WHITE);
                this.setForeground(Color.BLACK);
            }
        } else if (opc == 24) {
            String validacion = table.getValueAt(row, column).toString();
            if ((validacion.contains(" Devolver") || validacion.contains(" Facturar")) && column == column) {
                this.setOpaque(true);
                this.setForeground(new Color(0, 0, 0));
                this.setBackground(new Color(204, 204, 204));
            } else {
                this.setOpaque(true);
                this.setBackground(Color.WHITE);
                this.setForeground(Color.BLACK);
            }
        } else if (opc == 25) {
            try {
                if ((table.getValueAt(row, column).toString().contains(":")) && column == column) {
                    this.setOpaque(true);
                    this.setForeground(new Color(0, 0, 0));
                    this.setFont(new Font("Century Gothic", Font.BOLD, 16));
                }
            } catch (Exception e) {
            }

            try {
                String validacion = table.getValueAt(row, column).toString();
                if ((validacion.contains("Por aplicar.")) && column == column) {
                    this.setOpaque(true);
                    this.setForeground(new Color(0, 0, 0));
                    this.setBackground(new Color(255, 255, 153));
                } else {
                    this.setOpaque(true);
                    this.setBackground(Color.WHITE);
                    this.setForeground(Color.BLACK);
                }
            } catch (Exception e) {
            }
        } else if (opc == 26) {
            String validacion = table.getValueAt(row, 1).toString();
            if (validacion.equals("") && column == column) {
                this.setOpaque(true);
                this.setForeground(new Color(0, 0, 0));
                this.setBackground(new Color(0, 0, 0));
            } else {
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
