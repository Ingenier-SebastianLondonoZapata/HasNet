package Vistas.Menu;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.EventObject;
import java.util.HashMap;
import java.util.Map;
import javax.swing.AbstractCellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.TableCellEditor;

public class EditorCeldaPermisos extends AbstractCellEditor implements TableCellEditor {

    private final Map<Integer, TableCellEditor> editoresPorFila = new HashMap<>();
    private final TableCellEditor editorPorDefecto;
    private TableCellEditor editorActual;

    private final CellEditorListener reenvio = new CellEditorListener() {
        @Override
        public void editingStopped(ChangeEvent e) {
            fireEditingStopped();
        }

        @Override
        public void editingCanceled(ChangeEvent e) {
            fireEditingCanceled();
        }
    };

    public EditorCeldaPermisos(TableCellEditor editorPorDefecto) {
        this.editorPorDefecto = editorPorDefecto;
        this.editorActual = editorPorDefecto;
        editorPorDefecto.addCellEditorListener(reenvio);
    }

    public void asignarEditor(int fila, TableCellEditor editor) {
        editor.addCellEditorListener(reenvio);
        editoresPorFila.put(fila, editor);
    }

    public static TableCellEditor editorLista(Font fuente, String... opciones) {
        JComboBox<String> combo = new JComboBox<>(opciones);
        combo.setFont(fuente);
        return new DefaultCellEditor(combo);
    }

    public static TableCellEditor editorNumerico(Font fuente) {
        JTextField campo = new JTextField();
        campo.setFont(fuente);
        campo.setHorizontalAlignment(JTextField.CENTER);
        campo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent evt) {
                if (!Character.isDigit(evt.getKeyChar())) {
                    evt.consume();
                }
            }
        });
        DefaultCellEditor editor = new DefaultCellEditor(campo);
        editor.setClickCountToStart(1);
        return editor;
    }

    @Override
    public Component getTableCellEditorComponent(JTable tabla, Object valor, boolean seleccionada, int fila, int columna) {
        editorActual = editoresPorFila.getOrDefault(tabla.convertRowIndexToModel(fila), editorPorDefecto);
        return editorActual.getTableCellEditorComponent(tabla, valor, seleccionada, fila, columna);
    }

    @Override
    public Object getCellEditorValue() {
        return editorActual.getCellEditorValue();
    }

    @Override
    public boolean isCellEditable(EventObject evento) {
        return true;
    }

    @Override
    public boolean stopCellEditing() {
        return editorActual.stopCellEditing();
    }

    @Override
    public void cancelCellEditing() {
        editorActual.cancelCellEditing();
    }
}
