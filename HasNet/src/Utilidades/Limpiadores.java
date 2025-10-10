/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author sebastian.londono
 */
public class Limpiadores {
    
    public static void limpiarPanel(JPanel panel) {
        for (int x = 0; x < panel.getComponentCount(); x++) {
            try {
                if (panel.getComponent(x) instanceof JTextField) {
                    JTextField textField = (JTextField) panel.getComponent(x);
                    textField.setText("");
                } else if (panel.getComponent(x) instanceof JComboBox) {
                    JComboBox comboBox = (JComboBox) panel.getComponent(x);
                    comboBox.setSelectedIndex(0);
                } else if (panel.getComponent(x) instanceof JTextArea) {
                    JTextArea textArea = (JTextArea) panel.getComponent(x);
                    textArea.setText("");
                }
            } catch (Exception e) {
            }
        }
    }
    
    
}
