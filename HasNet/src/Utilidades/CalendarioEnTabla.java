/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

import datechooser.beans.DateChooserCombo;
import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author sebastian.londono
 */
public class CalendarioEnTabla {

    public static class incluirCalendarioEnTabla extends AbstractCellEditor implements TableCellEditor {

        private final DateChooserCombo dateChooser = new DateChooserCombo();

        @Override
        public Object getCellEditorValue() {
            return dateChooser.getSelectedDate().getTime();
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, final Object value,
                boolean isSelected, int row, int column) {

            if (value instanceof Date) {
                GregorianCalendar cal = new GregorianCalendar();
                cal.setTime((Date) value);
                dateChooser.setSelectedDate(cal);
                /*dateChooser.setDateFormat(new SimpleDateFormat("dd/MM/yyyy"));
                dateChooser.setSelectedDate(new java.util.GregorianCalendar() {
                    {
                        setTime((Date) value);
                    }
                });*/
            }
            return dateChooser;
        }
    }

    // Renderer que muestra la fecha con el formato deseado
    public static class formatoFechaEnTabla extends DefaultTableCellRenderer {

        private final SimpleDateFormat formatter;

        public formatoFechaEnTabla(String pattern) {
            this.formatter = new SimpleDateFormat(pattern);
        }

        @Override
        protected void setValue(Object value) {
            if (value instanceof Date) {
                setText(formatter.format((Date) value));
            } else {
                super.setValue(value);
            }
        }
    }
}
