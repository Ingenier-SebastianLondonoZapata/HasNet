package clases;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class FormatoTabla extends DefaultTableCellRenderer{

    private int columna_patron ;

    public FormatoTabla(int Colpatron)
    {
        this.columna_patron = Colpatron;
    }

    @Override
    public Component getTableCellRendererComponent ( JTable table, Object value, boolean selected, boolean focused, int row, int column )
    {        
        setBackground(Color.white);//color de fondo
        table.setForeground(Color.BLACK);//color de texto
        //Si la celda corresponde a una fila con estado FALSE, se cambia el color de fondo a rojo
        switch( table.getValueAt(row,columna_patron).toString()){
            case "1":
                setBackground(new Color(255,102,102));
                break;
            case "2":
                setBackground(new Color(51,153,255));
                break;    
            case "3":
                setBackground(new Color(0,204,51));
                break;
            case "4":
                setBackground(new Color(153,204,255));
                break;
            case "5":
                setBackground(new Color(255,153,204));
                break;
            case "6":
                setBackground(new Color(255,204,153));
                break;
            case "7":
                setBackground(new Color(0,204,204));
                break;
            case "8":
                setBackground(new Color(255,204,0));
                break;
            case "9":
                setBackground(new Color(204,204,0));
                break;
            case "10":
                setBackground(new Color(232,206,195));
                break;
            case "11":
                setBackground(new Color(171,211,138));
                break;
            case "12":
                setBackground(new Color(240,166,130));
                break;
        }

        super.getTableCellRendererComponent(table, value, selected, focused, row, column);
        return this;
 }

}