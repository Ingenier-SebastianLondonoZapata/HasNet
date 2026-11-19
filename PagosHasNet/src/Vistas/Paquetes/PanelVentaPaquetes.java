package Vistas.Paquetes;

import Controlador.Alertas.controladorAlertas;
import clases.Instancias;
import clases.SQLIngresos;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class PanelVentaPaquetes extends javax.swing.JPanel {

    private static final int[] COLUMNAS_DOCUMENTOS = {3, 4, 7};
    private TableRowSorter<DefaultTableModel> ordenador;

    public PanelVentaPaquetes() {
        initComponents();
        tblDatos.getTableHeader().setFont(new java.awt.Font("Century Gothic", java.awt.Font.BOLD, 15));
        tblDatos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        configurarColumnasSaldos();
    }

    private SQLIngresos sqlIngresos() {
        return Instancias.getInstancias().getSqlIngresos();
    }

    public void recargar() {
        try {
            DefaultTableModel modelo = (DefaultTableModel) tblDatos.getModel();
            modelo.setRowCount(0);
            for (Object[] fila : sqlIngresos().listarSaldosClientes()) {
                for (int columna : COLUMNAS_DOCUMENTOS) {
                    fila[columna] = clases.big.setNumero(((Number) fila[columna]).longValue());
                }
                modelo.addRow(fila);
            }
            if (ordenador == null) {
                ordenador = new TableRowSorter<>(modelo);
                java.util.Comparator<Object> comparadorNumerico = (a, b) -> Long.compare(
                        Long.parseLong(a.toString().replace(".", "")), Long.parseLong(b.toString().replace(".", "")));
                for (int columna : COLUMNAS_DOCUMENTOS) {
                    ordenador.setComparator(columna, comparadorNumerico);
                }
                tblDatos.setRowSorter(ordenador);
            }
            filtrar();
        } catch (SQLException ex) {
            Logger.getLogger(PanelVentaPaquetes.class.getName()).log(Level.SEVERE, "Error al cargar los paquetes", ex);
            controladorAlertas.alertFail("No se pudo cargar la información de paquetes");
        }
    }

    private void configurarColumnasSaldos() {
        tblDatos.getColumnModel().getColumn(0).setMinWidth(0);
        tblDatos.getColumnModel().getColumn(0).setPreferredWidth(0);
        tblDatos.getColumnModel().getColumn(0).setMaxWidth(0);
        for (int columna : new int[]{3, 4, 5, 7}) {
            tblDatos.getColumnModel().getColumn(columna).setPreferredWidth(140);
            tblDatos.getColumnModel().getColumn(columna).setMaxWidth(170);
        }
    }

    private void filtrar() {
        if (ordenador == null) {
            return;
        }
        String texto = txtBuscar.getText().trim();
        ordenador.setRowFilter(texto.isEmpty() ? null : RowFilter.regexFilter("(?i)" + java.util.regex.Pattern.quote(texto), 1, 2));
    }

    private void venderAlSeleccionado() {
        int fila = tblDatos.getSelectedRow();
        if (fila < 0) {
            controladorAlertas.alert("Seleccione el cliente al que le va a vender el paquete");
            return;
        }
        String idCliente = tblDatos.getModel().getValueAt(tblDatos.convertRowIndexToModel(fila), 0).toString();
        DialogoVentaPaquete dialogo = new DialogoVentaPaquete((java.awt.Frame) SwingUtilities.getWindowAncestor(this), idCliente);
        if (!dialogo.isListo()) {
            return;
        }
        dialogo.setVisible(true);
        if (dialogo.isVentaRealizada()) {
            recargar();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbBuscar = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        btnVender = new javax.swing.JButton();
        lbAyuda = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDatos = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));

        lbBuscar.setFont(new java.awt.Font("Century Gothic", 1, 15)); // NOI18N
        lbBuscar.setText("Buscar cliente:");

        txtBuscar.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        txtBuscar.setToolTipText("Escriba el NIT o el nombre del cliente");
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });

        btnVender.setBackground(new java.awt.Color(46, 204, 113));
        btnVender.setFont(new java.awt.Font("Century Gothic", 1, 15)); // NOI18N
        btnVender.setText("VENDER PAQUETE");
        btnVender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVenderActionPerformed(evt);
            }
        });

        lbAyuda.setFont(new java.awt.Font("Century Gothic", 2, 13)); // NOI18N
        lbAyuda.setForeground(new java.awt.Color(127, 140, 141));
        lbAyuda.setText("Seleccione un cliente y presione VENDER PAQUETE, o haga doble clic sobre él.");

        tblDatos.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        tblDatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NIT", "Cliente", "Sin vencimiento", "Mensuales vigentes", "Vigentes hasta", "Plan mensual", "Total disponibles"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDatos.setRowHeight(26);
        tblDatos.getTableHeader().setReorderingAllowed(false);
        tblDatos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDatosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDatos);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbBuscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnVender, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbAyuda)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbBuscar)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVender, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbAyuda)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        filtrar();
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void btnVenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVenderActionPerformed
        venderAlSeleccionado();
    }//GEN-LAST:event_btnVenderActionPerformed

    private void tblDatosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDatosMouseClicked
        if (evt.getClickCount() > 1) {
            venderAlSeleccionado();
        }
    }//GEN-LAST:event_tblDatosMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnVender;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbAyuda;
    private javax.swing.JLabel lbBuscar;
    private javax.swing.JTable tblDatos;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
